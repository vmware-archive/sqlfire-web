package vmware.au.se.sqlfireweb.dao.tables;

import java.util.List;

import vmware.au.se.sqlfireweb.main.Result;
import vmware.au.se.sqlfireweb.main.SqlFireException;

public interface TableDAO 
{
	public List<Table> retrieveTableList(String schema, String search, String userKey) throws SqlFireException;
	
	public Result simpletableCommand (String schemaName, String tableName, String type, String userKey) throws SqlFireException;
	
	public String generateLoadScript (String schema, String tableName) throws SqlFireException;
	
	public javax.servlet.jsp.jstl.sql.Result getDataLocations (String schema, String tableName, String userKey) throws SqlFireException;
	
	public String viewPartitionAttrs (String schema, String tableName, String userKey) throws SqlFireException;
	
	public javax.servlet.jsp.jstl.sql.Result getExpirationEvictionAttrs (String schema, String tableName, String userKey) throws SqlFireException;

	public javax.servlet.jsp.jstl.sql.Result getAllTableInfo (String schema, String tableName, String userKey) throws SqlFireException;
	
	public javax.servlet.jsp.jstl.sql.Result getTableStructure (String schema, String tableName, String userKey) throws SqlFireException;
	
}
