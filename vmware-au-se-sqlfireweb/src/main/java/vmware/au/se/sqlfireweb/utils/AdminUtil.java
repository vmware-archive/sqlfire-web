/*
  	Copyright (c) 2013 GoPivotal, Inc. All Rights Reserved.

	This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; only version 2 of the License, and no
    later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

	The full text of the GPL is provided in the COPYING file.
*/
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
	   return conn;
	}

	static public Connection getNewConnection (String url) throws SQLException
	{
	   DriverManager.registerDriver(new ClientDriver());
	   Connection conn = DriverManager.getConnection(url);
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
