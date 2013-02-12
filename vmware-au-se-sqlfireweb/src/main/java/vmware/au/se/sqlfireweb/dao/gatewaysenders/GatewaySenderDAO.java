package vmware.au.se.sqlfireweb.dao.gatewaysenders;

import java.util.List;

import vmware.au.se.sqlfireweb.main.Result;
import vmware.au.se.sqlfireweb.main.SqlFireException;

public interface GatewaySenderDAO 
{
	public List<GatewaySender> retrieveGatewaySenderList(String schema, String search, String userKey) throws SqlFireException;
	
	public Result simplegatewaySenderCommand (String senderId, String type, String userKey) throws SqlFireException;
	
	public javax.servlet.jsp.jstl.sql.Result getGatewaySenderInfo (String senderId, String userKey) throws SqlFireException;
}
