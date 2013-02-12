package vmware.au.se.sqlfireweb.dao.triggers;

import java.util.List;

import vmware.au.se.sqlfireweb.main.Result;
import vmware.au.se.sqlfireweb.main.SqlFireException;

public interface TriggerDAO 
{
	public List<Trigger> retrieveTriggerList(String schema, String search, String userKey) throws SqlFireException;
	
	public Result simpletriggerCommand (String schemaName, String triggerName, String type, String userKey) throws SqlFireException;
	
	public javax.servlet.jsp.jstl.sql.Result getAllTriggerInfo (String schema, String triggerId, String userKey) throws SqlFireException;
}
