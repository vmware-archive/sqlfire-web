package vmware.au.se.sqlfireweb.dao.gatewayrecievers;

import java.util.List;

import vmware.au.se.sqlfireweb.main.Result;
import vmware.au.se.sqlfireweb.main.SqlFireException;

public interface GatewayReceiverDAO 
{
	public List<GatewayReceiver> retrieveGatewayReceiverList(String schema, String search, String userKey) throws SqlFireException;
	
	public Result simplegatewayReceiverCommand (String id, String type, String userKey) throws SqlFireException;
	
	public javax.servlet.jsp.jstl.sql.Result getGatewayRecieverInfo (String id, String userKey) throws SqlFireException;
}
