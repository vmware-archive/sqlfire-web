package vmware.au.se.sqlfireweb.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.vmware.sqlfire.jdbc.ClientDriver;

public class AdminUtil 
{
	protected static Logger logger = Logger.getLogger("controller");

	static public Connection getNewConnection 
	(String url,
	 String username,
	 String password) throws SQLException
	{
	   DriverManager.registerDriver(new ClientDriver());
	   Connection conn = DriverManager.getConnection(url,username,password);
	   conn.setAutoCommit(true);
	   return conn;
	}

	static public Connection getNewConnection (String url) throws SQLException
	{
	   DriverManager.registerDriver(new ClientDriver());
	   Connection conn = DriverManager.getConnection(url);
	   conn.setAutoCommit(true);
	   return conn;
	}
	
	/*
	 * Get connection from ConnectionManager conList Map
	 */
	static public Connection getConnection(String userKey) throws Exception
	{
		Connection conn = null;
		ConnectionManager cm = null;
    
		cm = ConnectionManager.getInstance();
		conn = cm.getConnection(userKey);
    
		return conn;
	}
	
	static public Map<String, String> getSchemaMap ()
	{
		Map<String, String> schemaMap = new HashMap<String, String>();
		
		schemaMap.put("Table", "0");
		schemaMap.put("Index", "0");
		schemaMap.put("View", "0");
		schemaMap.put("Constraint", "0");
		schemaMap.put("Trigger", "0");
		schemaMap.put("Procedure", "0");
		schemaMap.put("Function", "0");
		schemaMap.put("Diskstore", "0");
		schemaMap.put("AsyncEventList", "0");
		schemaMap.put("Sender", "0");
		schemaMap.put("Receiver", "0");
		schemaMap.put("Type", "0");
		
		return schemaMap;
	}
}
