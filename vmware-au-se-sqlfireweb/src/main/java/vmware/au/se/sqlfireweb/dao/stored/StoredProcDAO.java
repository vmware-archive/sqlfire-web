package vmware.au.se.sqlfireweb.dao.stored;

import java.util.List;

import vmware.au.se.sqlfireweb.main.Result;
import vmware.au.se.sqlfireweb.main.SqlFireException;

public interface StoredProcDAO 
{
	public List<StoredProc> retrieveProcList(String schema, String search, String procType, String userKey) throws SqlFireException;
	
	public Result simpleprocCommand (String schemaName, String procName, String type, String procType, String userKey) throws SqlFireException;

	public List<ProcedureParameter> describeProcedure (String schemaName, String procName, String userKey) throws SqlFireException;
}
