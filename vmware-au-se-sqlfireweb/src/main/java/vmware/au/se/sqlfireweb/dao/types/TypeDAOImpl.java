package vmware.au.se.sqlfireweb.dao.types;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import vmware.au.se.sqlfireweb.dao.ISQLFireDAOUtil;
import vmware.au.se.sqlfireweb.dao.types.Constants;
import vmware.au.se.sqlfireweb.main.Result;
import vmware.au.se.sqlfireweb.main.SqlFireException;
import vmware.au.se.sqlfireweb.utils.AdminUtil;
import vmware.au.se.sqlfireweb.utils.JDBCUtil;

public class TypeDAOImpl implements TypeDAO 
{
	protected static Logger logger = Logger.getLogger("controller");
	
	public List<Type> retrieveTypeList(String schema, String search, String userKey) throws SqlFireException 
	{
	    Connection        conn = null;
	    PreparedStatement stmt = null;
	    ResultSet         rset = null;
	    List<Type>        types = null;
	    String            srch = null;
	    
	    try
	    {
	      conn = AdminUtil.getConnection(userKey);
	      stmt = conn.prepareStatement(Constants.USER_TYPES);
	      if (search == null)
	        srch = "%";
	      else
	        srch = "%" + search.toUpperCase() + "%";
	      
	      stmt.setString(1, schema);
	      stmt.setString(2, srch);  
	      rset = stmt.executeQuery();

	      types = makeTypeListFromResultSet(rset);
	    }
	    catch (SQLException se)
	    {
	      logger.debug("Error retrieving all types with search string = " + search);
	      throw new SqlFireException(se);
	    }
	    catch (Exception ex)
	    {
	      logger.debug("Error retrieving all types with search string = " + search);
	      throw new SqlFireException(ex);      
	    }
	    finally
	    {
	      // close all resources
	      JDBCUtil.close(rset);
	      JDBCUtil.close(stmt);
	    }
	    
	    return types;
	}

	public Result simpletypeCommand(String schemaName, String typeName, String type, String userKey) throws SqlFireException 
	{
	    String            command = null;
	    Result            res     = null;

	    if (type != null)
	    {
	      if (type.equalsIgnoreCase("DROP"))
	      {
	        command = String.format(Constants.DROP_TYPE, schemaName, typeName);
	      }
	    }
	    
	    res = ISQLFireDAOUtil.runCommand(command, userKey);
	    
	    return res;
	}

	private List<Type> makeTypeListFromResultSet (ResultSet rset) throws SQLException
	{
		List<Type> types = new ArrayList<Type>();
		
		while (rset.next())
		{
			Type type = new Type(rset.getString(1),
					             rset.getString(2),
					             rset.getString(3));
			types.add(type);
		}
		
		return types;
		
	}

}
