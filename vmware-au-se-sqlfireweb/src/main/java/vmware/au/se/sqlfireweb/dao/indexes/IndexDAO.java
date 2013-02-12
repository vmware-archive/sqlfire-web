package vmware.au.se.sqlfireweb.dao.indexes;

import java.util.List;

import vmware.au.se.sqlfireweb.main.Result;
import vmware.au.se.sqlfireweb.main.SqlFireException;

public interface IndexDAO 
{
	public List<Index> retrieveIndexList(String schema, String search, String userKey) throws SqlFireException;
	
	public Result simpleindexCommand (String schemaName, String indexName, String type, String userKey) throws SqlFireException;

    public List<IndexColumn> retrieveIndexColumns (String schemaName, String tableName, String userKey) throws SqlFireException;
}
