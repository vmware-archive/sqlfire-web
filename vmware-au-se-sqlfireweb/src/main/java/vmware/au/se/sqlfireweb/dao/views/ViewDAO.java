package vmware.au.se.sqlfireweb.dao.views;

import java.util.List;

import vmware.au.se.sqlfireweb.main.Result;
import vmware.au.se.sqlfireweb.main.SqlFireException;

public interface ViewDAO 
{
	public List<View> retrieveViewList(String schema, String search, String userKey) throws SqlFireException;
	
	public Result simpleviewCommand (String schemaName, String viewName, String type, String userKey) throws SqlFireException;
}
