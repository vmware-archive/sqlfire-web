package vmware.au.se.sqlfireweb.dao.asyncevent;

import java.util.List;

import vmware.au.se.sqlfireweb.main.Result;
import vmware.au.se.sqlfireweb.main.SqlFireException;

public interface AsynceventDAO 
{
	public List<Asyncevent> retrieveAsynceventList(String schema, String search, String userKey) throws SqlFireException;
	
	public Result simpleasynceventCommand (String asyncEvent, String type, String userKey) throws SqlFireException;
	
	public javax.servlet.jsp.jstl.sql.Result getAsynEventInfo (String asyncEvent, String userKey) throws SqlFireException;
}
