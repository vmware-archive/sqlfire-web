package vmware.au.se.sqlfireweb.dao.asyncevent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import vmware.au.se.sqlfireweb.dao.ISQLFireDAOUtil;
import vmware.au.se.sqlfireweb.dao.asyncevent.Constants;
import vmware.au.se.sqlfireweb.main.Result;
import vmware.au.se.sqlfireweb.main.SqlFireException;
import vmware.au.se.sqlfireweb.utils.AdminUtil;
import vmware.au.se.sqlfireweb.utils.JDBCUtil;
import vmware.au.se.sqlfireweb.utils.QueryUtil;

public class AsynceventDAOImpl implements AsynceventDAO
{
	protected static Logger logger = Logger.getLogger("controller");
	
	public List<Asyncevent> retrieveAsynceventList(String schema, String search, String userKey) throws SqlFireException 
	{
	    Connection        conn = null;
	    PreparedStatement stmt = null;
	    ResultSet         rset = null;
	    List<Asyncevent>  asyncevents = null;
	    String            srch = null;
	    
	    try
	    {
	      conn = AdminUtil.getConnection(userKey);
	      stmt = conn.prepareStatement(Constants.USER_ASYNCEVENTS);
	      if (search == null)
	        srch = "%";
	      else
	        srch = "%" + search.toUpperCase() + "%";
	      
	      stmt.setString(1, srch);  
	      rset = stmt.executeQuery();

	      asyncevents = makeAsynceventListFromResultSet(rset);
	    }
	    catch (SQLException se)
	    {
	      logger.debug("Error retrieving all async events with search string = " + search);
	      throw new SqlFireException(se);
	    }
	    catch (Exception ex)
	    {
	      logger.debug("Error retrieving all async events with search string = " + search);
	      throw new SqlFireException(ex);      
	    }
	    finally
	    {
	      // close all resources
	      JDBCUtil.close(rset);
	      JDBCUtil.close(stmt);
	    }
	    
	    return asyncevents;
	}

	public Result simpleasynceventCommand(String asyncEvent, String type, String userKey) throws SqlFireException 
	{
		
	    String            command = null;
	    Result            res     = null;

	    if (type != null)
	    {
	      if (type.equalsIgnoreCase("DROP"))
	      {
	        command = String.format(Constants.DROP_ASYNC, asyncEvent);
	      }
	      else if (type.equalsIgnoreCase("START"))
	      {
	    	command = String.format(Constants.START_ASYNC, asyncEvent);
	      }
	      else if ((type.equalsIgnoreCase("STOP")))
	      {
	    	command = String.format(Constants.STOP_ASYNC, asyncEvent);  
	      }
	    }
	    
	    res = ISQLFireDAOUtil.runCommand(command, userKey);
	    
	    return res;
	}
	
	private List<Asyncevent> makeAsynceventListFromResultSet (ResultSet rset) throws SQLException
	{
		List<Asyncevent> asyncevents = new ArrayList<Asyncevent>();
		
		while (rset.next())
		{
			Asyncevent index = new Asyncevent(rset.getString(1),
					                		  rset.getString(2),
					                		  rset.getString(3),
					                		  rset.getString(4));
			asyncevents.add(index);
		}
		
		return asyncevents;
		
	}

	public javax.servlet.jsp.jstl.sql.Result getAsynEventInfo(
			String asyncEvent, String userKey) throws SqlFireException 
	{
		Connection        conn = null;
		javax.servlet.jsp.jstl.sql.Result res = null;
		
		try 
		{
			conn = AdminUtil.getConnection(userKey);
			res = QueryUtil.runQuery
					(conn, String.format(Constants.VIEW_ALL_USER_ASYNCEVENT_INFO, asyncEvent), -1);
		} 
		catch (Exception e) 
		{
		      logger.debug("Error retrieving all async event info info for " + asyncEvent);
		      throw new SqlFireException(e); 
		}
		
		return res;
	}
}
