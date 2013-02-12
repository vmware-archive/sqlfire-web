package vmware.au.se.sqlfireweb.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class ConnectionManager 
{

	protected static Logger logger = Logger.getLogger("controller");
	private Map<String,SQLFireJDBCConnection> conList = new HashMap<String,SQLFireJDBCConnection>();
	private static ConnectionManager instance = null;
	
	static
	{
		instance = new ConnectionManager();
	}
  
	private ConnectionManager()
	{
		// Exists only to defeat instantiation.
	}

	public static ConnectionManager getInstance() throws Exception
	{     
		return instance;
	}
	  
	public void addConnection (SQLFireJDBCConnection conn, String key)  
	{
	    conList.put(key, conn);
	    logger.debug("Connection added with key " + key);
	}
	
	public Connection getConnection (String key)
	{
		return conList.get(key).getConn();
	}
	  
	public void updateConnection(Connection conn, String key)
	{
		SQLFireJDBCConnection sqlfireConn = conList.get(key);
		sqlfireConn.setConn(conn);
		conList.put(key, sqlfireConn);
		logger.debug("Connection updated with key " + key);
	}
	
	public void removeConnection(String key) throws SQLException
	{
		if (conList.containsKey(key))
		{
		    Connection conn = getConnection(key); 
		    if (conn != null)
		    {
		      conn.close();
		      conn = null;
		    }
		    
		    conList.remove(key);
		    logger.debug("Connection removed with key " + key);
		}
		else
		{
			logger.debug("No connection with key " + key + " exists");
		}
	}
	  
	public Map <String,SQLFireJDBCConnection> getConnectionMap() 
	{
		return conList; 
	}
	  
	public int getConnectionListSize ()
	{
		return conList.size();
	}
	  
	public String displayMap ()
	{
	    StringBuffer sb = new StringBuffer();
	    
	    sb.append("-- Current Connection List --\n\n");
	    sb.append("Size = " + getConnectionListSize() + "\n\n");
		for (String key : conList.keySet())
		{
		  sb.append(String.format("Key %s, Connection %s\n", key, getConnection(key)));
		}
	  
	    return sb.toString(); 
	}
}
