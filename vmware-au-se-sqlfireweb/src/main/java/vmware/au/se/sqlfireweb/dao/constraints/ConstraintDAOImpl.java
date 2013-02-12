package vmware.au.se.sqlfireweb.dao.constraints;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import vmware.au.se.sqlfireweb.dao.ISQLFireDAOUtil;
import vmware.au.se.sqlfireweb.main.Result;
import vmware.au.se.sqlfireweb.main.SqlFireException;
import vmware.au.se.sqlfireweb.utils.AdminUtil;
import vmware.au.se.sqlfireweb.utils.JDBCUtil;

public class ConstraintDAOImpl implements ConstraintDAO 
{

	protected static Logger logger = Logger.getLogger("controller");
	
	public List<Constraint> retrieveConstraintList(String schema, String search, String userKey) throws SqlFireException 
	{
	    Connection        conn = null;
	    PreparedStatement stmt = null;
	    ResultSet         rset = null;
	    List<Constraint>  cons = null;
	    String            srch = null;
	    
	    try
	    {
	      conn = AdminUtil.getConnection(userKey);
	      stmt = conn.prepareStatement(Constants.USER_CONSTRAINTS);
	      if (search == null)
	        srch = "%";
	      else
	        srch = "%" + search.toUpperCase() + "%";
	      
	      stmt.setString(1, schema);
	      stmt.setString(2, srch);  
	      rset = stmt.executeQuery();

	      cons = makeConstraintListFromResultSet(rset);
	    }
	    catch (SQLException se)
	    {
	      logger.debug("Error retrieving all constraints with search string = " + search);
	      throw new SqlFireException(se);
	    }
	    catch (Exception ex)
	    {
	      logger.debug("Error retrieving all tables with search string = " + search);
	      throw new SqlFireException(ex);      
	    }
	    finally
	    {
	      // close all resources
	      JDBCUtil.close(rset);
	      JDBCUtil.close(stmt);
	    }
	    
	    return cons;
	}

	public Result simpleconstraintCommand(String schemaName, String tableName, String constraintName, String type, String userKey) throws SqlFireException 
	{
	    String            command = null;
	    Result            res     = null;

	    if (type != null)
	    {
	      if (type.equalsIgnoreCase("DROP"))
	      {
	        command = String.format(Constants.DROP_CONSTRAINT, schemaName, tableName, constraintName);
	      }
	    }
	    
	    res = ISQLFireDAOUtil.runCommand(command, userKey);
	    
	    return res;
	}

	private List<Constraint> makeConstraintListFromResultSet (ResultSet rset) throws SQLException
	{
		List<Constraint> cons = new ArrayList<Constraint>();
		
		while (rset.next())
		{
			Constraint con = new Constraint(rset.getString(1),
											rset.getString(2),
					              			rset.getString(3),
					              			rset.getString(4),
					              			rset.getString(5));
			cons.add(con);
		}
		
		return cons;
		
	}
}
