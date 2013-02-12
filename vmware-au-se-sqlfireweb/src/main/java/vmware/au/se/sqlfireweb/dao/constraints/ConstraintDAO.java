package vmware.au.se.sqlfireweb.dao.constraints;

import java.util.List;

import vmware.au.se.sqlfireweb.main.Result;
import vmware.au.se.sqlfireweb.main.SqlFireException;

public interface ConstraintDAO 
{
	public List<Constraint> retrieveConstraintList(String schema, String search, String userKey) throws SqlFireException;
	
	public Result simpleconstraintCommand (String schemaName, String tableName, String constraintName, String type, String userKey) throws SqlFireException;
}
