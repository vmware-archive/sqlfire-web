package vmware.au.se.sqlfireweb.dao.diskstores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import vmware.au.se.sqlfireweb.dao.ISQLFireDAOUtil;
import vmware.au.se.sqlfireweb.dao.diskstores.Constants;
import vmware.au.se.sqlfireweb.main.Result;
import vmware.au.se.sqlfireweb.main.SqlFireException;
import vmware.au.se.sqlfireweb.utils.AdminUtil;
import vmware.au.se.sqlfireweb.utils.JDBCUtil;

public class DiskStoreDAOImpl implements DiskStoreDAO 
{

	protected static Logger logger = Logger.getLogger("controller");
	
	public List<DiskStore> retrieveDiskStoreList
		(String schema, String search, String userKey) throws SqlFireException 
	{
	    Connection        conn = null;
	    PreparedStatement stmt = null;
	    ResultSet         rset = null;
	    List<DiskStore>   dsks = null;
	    String            srch = null;
	    
	    try
	    {
	      conn = AdminUtil.getConnection(userKey);
	      stmt = conn.prepareStatement(Constants.USER_DISKSTORES);
	      if (search == null)
	        srch = "%";
	      else
	        srch = "%" + search.toUpperCase() + "%";
	      
	      stmt.setString(1, srch);  
	      rset = stmt.executeQuery();

	      dsks = makeDiskStoreListFromResultSet(rset);
	    }
	    catch (SQLException se)
	    {
	      logger.debug("Error retrieving all diskstores with search string = " + search);
	      throw new SqlFireException(se);
	    }
	    catch (Exception ex)
	    {
	      logger.debug("Error retrieving all diskstores with search string = " + search);
	      throw new SqlFireException(ex);      
	    }
	    finally
	    {
	      // close all resources
	      JDBCUtil.close(rset);
	      JDBCUtil.close(stmt);
	    }
	    
	    return dsks;
	}

	public Result simplediskStoreCommand
		(String diskStoreName, String type, String userKey) throws SqlFireException 
	{
	    String            command = null;
	    Result            res     = null;

	    if (type != null)
	    {
	      if (type.equalsIgnoreCase("DROP"))
	      {
	        command = String.format(Constants.DROP_DISKSTORE, diskStoreName);
	      }
	    }
	    
	    res = ISQLFireDAOUtil.runCommand(command, userKey);
	    
	    return res;
	}
	
	private List<DiskStore> makeDiskStoreListFromResultSet (ResultSet rset) throws SQLException
	{
		List<DiskStore> dsks = new ArrayList<DiskStore>();
		
		while (rset.next())
		{
			DiskStore ds = new DiskStore(rset.getString(1), rset.getString(2));
			dsks.add(ds);
		}
		
		return dsks;
		
	}

	public List<DiskStore> retrieveDiskStoreForCreateList(String userKey) throws SqlFireException 
	{
	    Connection        conn = null;
	    Statement 		  stmt = null;
	    ResultSet         rset = null;
	    List<DiskStore>   dsks = null;
	    
	    try
	    {
	      conn = AdminUtil.getConnection(userKey);
	      stmt = conn.createStatement();
	      rset = stmt.executeQuery(Constants.USER_DISKSTORES_FOR_CREATE);

	      dsks = makeDiskStoreListFromResultSet(rset);
	    }
	    catch (SQLException se)
	    {
	      logger.debug("Error retrieving all diskstores for create");
	      throw new SqlFireException(se);
	    }
	    catch (Exception ex)
	    {
	      logger.debug("Error retrieving all diskstores for create");
	      throw new SqlFireException(ex);      
	    }
	    finally
	    {
	      // close all resources
	      JDBCUtil.close(rset);
	      JDBCUtil.close(stmt);
	    }
	    
	    return dsks;
	}

}
