package vmware.au.se.sqlfireweb.dao.types;

import java.util.List;

import vmware.au.se.sqlfireweb.main.Result;
import vmware.au.se.sqlfireweb.main.SqlFireException;

public interface TypeDAO 
{
	public List<Type> retrieveTypeList(String schema, String search, String userKey) throws SqlFireException;
	
	public Result simpletypeCommand (String schemaName, String typeName, String type, String userKey) throws SqlFireException;

}
