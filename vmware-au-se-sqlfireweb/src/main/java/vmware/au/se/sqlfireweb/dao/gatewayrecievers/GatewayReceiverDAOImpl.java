package vmware.au.se.sqlfireweb.dao.gatewayrecievers;

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

public class GatewayReceiverDAOImpl implements GatewayReceiverDAO
{
	protected static Logger logger = Logger.getLogger("controller");
	
	public List<GatewayReceiver> retrieveGatewayReceiverList(String schema,
			String search, String userKey) throws SqlFireException 
	{
		// TODO Auto-generated method stub
	    Connection        conn = null;
	    PreparedStatement stmt = null;
	    ResultSet         rset = null;
	    List<GatewayReceiver>  gatewayreceivers = null;
	    String            srch = null;
	    
	    try
	    {
	      conn = AdminUtil.getConnection(userKey);
	      stmt = conn.prepareStatement(Constants.USER_GATEWAY_RECEIVERS);
	      if (search == null)
	        srch = "%";
	      else
	        srch = "%" + search.toUpperCase() + "%";
	      
	      stmt.setString(1, srch);  
	      rset = stmt.executeQuery();

	      gatewayreceivers = makeGatewayReceiverListFromResultSet(rset);
	    }
	    catch (SQLException se)
	    {
	      logger.debug("Error retrieving all gateway receivers with search string = " + search);
	      throw new SqlFireException(se);
	    }
	    catch (Exception ex)
	    {
	      logger.debug("Error retrieving all gateway receievers with search string = " + search);
	      throw new SqlFireException(ex);      
	    }
	    finally
	    {
	      // close all resources
	      JDBCUtil.close(rset);
	      JDBCUtil.close(stmt);
	    }
	    
	    return gatewayreceivers;
	}

	public Result simplegatewayReceiverCommand(String id, String type, String userKey) throws SqlFireException 
	{
		// TODO Auto-generated method stub
	    String            command = null;
	    Result            res     = null;

	    if (type != null)
	    {
	      if (type.equalsIgnoreCase("DROP"))
	      {
	        command = String.format(Constants.DROP_GATEWAY_RECEIVER, id);
	        res = ISQLFireDAOUtil.runCommand(command, userKey);
	      }
	    }
	    
	    return res;
	}
	
	private List<GatewayReceiver> makeGatewayReceiverListFromResultSet (ResultSet rset) throws SQLException
	{
		List<GatewayReceiver> gatewayrecievers = new ArrayList<GatewayReceiver>();
		
		while (rset.next())
		{
			GatewayReceiver gr = new GatewayReceiver(rset.getString(1),
					                		  rset.getString(2),
					                		  rset.getString(3),
					                		  rset.getString(4));
			gatewayrecievers.add(gr);
		}
		
		return gatewayrecievers;
		
	}

	public javax.servlet.jsp.jstl.sql.Result getGatewayRecieverInfo
		(String id, String userKey) throws SqlFireException 
	{
		Connection        conn = null;
		javax.servlet.jsp.jstl.sql.Result res = null;
		
		try 
		{
			conn = AdminUtil.getConnection(userKey);
			res = QueryUtil.runQuery
					(conn, String.format(Constants.VIEW_ALL_GATEWAY_RECEIVERS_COLUMNS, id), -1);
		} 
		catch (Exception e) 
		{
		      logger.debug("Error retrieving all gateway reciever info for " + id);
		      throw new SqlFireException(e); 
		}
		
		return res;
	}
}
