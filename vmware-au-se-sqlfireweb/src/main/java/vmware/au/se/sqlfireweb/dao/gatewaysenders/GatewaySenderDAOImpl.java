package vmware.au.se.sqlfireweb.dao.gatewaysenders;

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
import vmware.au.se.sqlfireweb.utils.QueryUtil;

public class GatewaySenderDAOImpl implements GatewaySenderDAO
{
	protected static Logger logger = Logger.getLogger("controller");

	public List<GatewaySender> retrieveGatewaySenderList(String schema,
			String search, String userKey) throws SqlFireException 
	{
		// TODO Auto-generated method stub
	    Connection        conn = null;
	    PreparedStatement stmt = null;
	    ResultSet         rset = null;
	    List<GatewaySender>  gatewayssenders = null;
	    String            srch = null;
	    
	    try
	    {
	      conn = AdminUtil.getConnection(userKey);
	      stmt = conn.prepareStatement(Constants.USER_GATEWAY_SENDERS);
	      if (search == null)
	        srch = "%";
	      else
	        srch = "%" + search.toUpperCase() + "%";
	      
	      stmt.setString(1, srch);  
	      rset = stmt.executeQuery();

	      gatewayssenders = makeGatewaySenderListFromResultSet(rset);
	    }
	    catch (SQLException se)
	    {
	      logger.debug("Error retrieving all gateway senders with search string = " + search);
	      throw new SqlFireException(se);
	    }
	    catch (Exception ex)
	    {
	      logger.debug("Error retrieving all gateway senders with search string = " + search);
	      throw new SqlFireException(ex);      
	    }
	    finally
	    {
	      // close all resources
	      JDBCUtil.close(rset);
	      JDBCUtil.close(stmt);
	    }
	    
	    return gatewayssenders;
	}

	public Result simplegatewaySenderCommand(String senderId, String type, String userKey) throws SqlFireException 
	{
		// TODO Auto-generated method stub
	    String            command = null;
	    Result            res     = null;

	    if (type != null)
	    {
	      if (type.equalsIgnoreCase("DROP"))
	      {
	        command = String.format(Constants.DROP_GATEWAY_SENDER, senderId);
	        res = ISQLFireDAOUtil.runCommand(command, userKey);
	      }
	      else if (type.equalsIgnoreCase("STOP"))
	      {
	    	command = String.format(Constants.STOP_GATEWAY_SENDER, senderId);  
	    	res = ISQLFireDAOUtil.runStoredCommand(command, userKey);
	      }
	      else if (type.equalsIgnoreCase("START"))
	      {
	    	command = String.format(Constants.START_GATEWAY_SENDER, senderId);  
	    	res = ISQLFireDAOUtil.runStoredCommand(command, userKey);
	      }
	    }
	    
	    
	    
	    return res;
	}

	private List<GatewaySender> makeGatewaySenderListFromResultSet (ResultSet rset) throws SQLException
	{
		List<GatewaySender> gatewaysenders = new ArrayList<GatewaySender>();
		
		while (rset.next())
		{
			GatewaySender gs = new GatewaySender(rset.getString(1),
					                		  rset.getString(2),
					                		  rset.getString(3),
					                		  rset.getString(4));
			gatewaysenders.add(gs);
		}
		
		return gatewaysenders;
		
	}

	public javax.servlet.jsp.jstl.sql.Result getGatewaySenderInfo
		(String senderId, String userKey) throws SqlFireException 
	{
		Connection        conn = null;
		javax.servlet.jsp.jstl.sql.Result res = null;
		
		try 
		{
			conn = AdminUtil.getConnection(userKey);
			res = QueryUtil.runQuery
					(conn, String.format(Constants.VIEW_ALL_USER_GATEWAY_SENDER_INFO, senderId), -1);
		} 
		catch (Exception e) 
		{
		      logger.debug("Error retrieving all gateway sender info info for " + senderId);
		      throw new SqlFireException(e); 
		}
		
		return res;
	}
}
