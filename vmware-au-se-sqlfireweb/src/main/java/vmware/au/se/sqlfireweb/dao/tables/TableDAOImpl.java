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
package vmware.au.se.sqlfireweb.dao.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import vmware.au.se.sqlfireweb.dao.ISQLFireDAOUtil;
import vmware.au.se.sqlfireweb.main.Result;
import vmware.au.se.sqlfireweb.main.SqlFireException;
import vmware.au.se.sqlfireweb.utils.AdminUtil;
import vmware.au.se.sqlfireweb.utils.JDBCUtil;
import vmware.au.se.sqlfireweb.utils.QueryUtil;

public class TableDAOImpl implements TableDAO
{
	protected static Logger logger = Logger.getLogger("controller");
	
	public List<Table> retrieveTableList(String schema, String search, String userKey) throws SqlFireException 
	{
	    Connection        conn = null;
	    PreparedStatement stmt = null;
	    ResultSet         rset = null;
	    List<Table>       tbls = null;
	    String            srch = null;
	    
	    try
	    {
	      conn = AdminUtil.getConnection(userKey);
	      stmt = conn.prepareStatement(Constants.USER_TABLES);
	      if (search == null)
	        srch = "%";
	      else
	        srch = "%" + search.toUpperCase() + "%";
	      
	      stmt.setString(1, schema);
	      stmt.setString(2, srch);  
	      rset = stmt.executeQuery();

	      tbls = makeTableListFromResultSet(rset);
	    }
	    catch (SQLException se)
	    {
	      logger.debug("Error retrieving all tables with search string = " + search);
	      throw new SqlFireException(se);
	    }
	    catch (Exception ex)
	    {
	      logger.debug("Error retrieving all tables with search string = " + search);
	      throw new SqlFireException(ex);      
	    }
	    finally
	    {
	      // close all resources
	      JDBCUtil.close(rset);
	      JDBCUtil.close(stmt);
	    }
	    
	    return tbls;
	}
	
	public Result simpletableCommand(String schemaName, String tableName, String type, String userKey) throws SqlFireException 
	{
	    String            command = null;
	    Result            res     = null;

	    if (type != null)
	    {
	      if (type.equalsIgnoreCase("DROP"))
	      {
	        command = String.format(Constants.DROP_TABLE, schemaName, tableName);
	      }
	      else if (type.equalsIgnoreCase("EMPTY"))
	      {
	        command = String.format(Constants.TRUNCATE_TABLE, schemaName, tableName);
	      }
	    }
	    
	    res = ISQLFireDAOUtil.runCommand(command, userKey);
	    
	    return res;
	}

	private List<Table> makeTableListFromResultSet (ResultSet rset) throws SQLException
	{
		List<Table> tbls = new ArrayList<Table>();
		
		while (rset.next())
		{
			Table table = new Table(rset.getString(1),
					              rset.getString(2),
					              rset.getString(4),
					              rset.getString(3),
					              rset.getString(5),
					              rset.getString(6));
			tbls.add(table);
		}
		
		return tbls;
		
	}

	public String generateLoadScript(String schema, String tableName) throws SqlFireException 
	{
		// TODO Auto-generated method stub
		String loadSQL = String.format(Constants.LOAD_TABLE_SCRIPT, schema, tableName, tableName);
				
		return loadSQL;
	}

	public javax.servlet.jsp.jstl.sql.Result getDataLocations
		(String schema, String tableName, String userKey) throws SqlFireException 
	{
		Connection        conn = null;
		javax.servlet.jsp.jstl.sql.Result res = null;
		
		try 
		{
			conn = AdminUtil.getConnection(userKey);
			res = QueryUtil.runQuery
					(conn, String.format(Constants.TABLE_DATA_LOCATION, schema, tableName), -1);
		} 
		catch (Exception e) 
		{
		      logger.debug("Error retrieving table data locations");
		      throw new SqlFireException(e); 
		}
		
		return res;
	}

	public String viewPartitionAttrs
		(String schema, String tableName, String userKey) throws SqlFireException 
	{
		Connection        conn = null;
		String res = null;
	    PreparedStatement stmt = null;
	    ResultSet         rset = null;
	    
		try 
		{
			conn = AdminUtil.getConnection(userKey);
		    stmt = conn.prepareStatement(Constants.VIEW_PARTITION_ATTRS);
		   
		    stmt.setString(1, schema);
		    stmt.setString(2, tableName);  
		    rset = stmt.executeQuery();
		    if (rset != null)
		    {
		    	rset.next();
		    	res = rset.getString(1);
		    }
		}
		catch (Exception e) 
		{
		      logger.debug("Error retrieving table partition attributes");
		      throw new SqlFireException(e); 
		}
		finally
		{
			JDBCUtil.close(stmt);
			JDBCUtil.close(rset);
		}
		
		return res;
	}

	public javax.servlet.jsp.jstl.sql.Result getExpirationEvictionAttrs
		(String schema, String tableName, String userKey) throws SqlFireException 
	{
		Connection        conn = null;
		javax.servlet.jsp.jstl.sql.Result res = null;
		
		try 
		{
			conn = AdminUtil.getConnection(userKey);
			res = QueryUtil.runQuery
					(conn, String.format(Constants.VIEW_EVICTION_EXPIRATION_ATTRS, schema, tableName), -1);
		} 
		catch (Exception e) 
		{
		      logger.debug("Error retrieving eviction / expiration attributes for table " + tableName);
		      throw new SqlFireException(e); 
		}
		
		return res;
	}

	public javax.servlet.jsp.jstl.sql.Result getAllTableInfo
		(String schema, String tableName, String userKey) throws SqlFireException 
	{
		Connection        conn = null;
		javax.servlet.jsp.jstl.sql.Result res = null;
		
		try 
		{
			conn = AdminUtil.getConnection(userKey);
			res = QueryUtil.runQuery
					(conn, String.format(Constants.VIEW_ALL_TABLE_COLUMNS, schema, tableName), -1);
		} 
		catch (Exception e) 
		{
		      logger.debug("Error retrieving info for table " + tableName);
		      throw new SqlFireException(e); 
		}
		
		return res;
	}

	public javax.servlet.jsp.jstl.sql.Result getTableStructure
		(String schema, String tableName, String userKey) throws SqlFireException 
	{
		Connection        conn = null;
		javax.servlet.jsp.jstl.sql.Result res = null;
		
		try 
		{
			conn = AdminUtil.getConnection(userKey);
			res = QueryUtil.runQuery
					(conn, String.format(Constants.VIEW_TABLE_STRUCTURE, schema, tableName), -1);
		} 
		catch (Exception e) 
		{
		      logger.debug("Error retrieving structure for table " + tableName);
		      throw new SqlFireException(e); 
		}
		
		return res;
	}

	@Override
	public javax.servlet.jsp.jstl.sql.Result getMemoryUsage
		(String schema, String tableName, String userKey) throws SqlFireException 
	{
		
		Connection        conn = null;
		javax.servlet.jsp.jstl.sql.Result res = null;
		
		try 
		{
			conn = AdminUtil.getConnection(userKey);
			res = QueryUtil.runQuery
					(conn, String.format(Constants.TABLE_MEMORY_USAGE, schema, tableName), -1);
		} 
		catch (Exception e) 
		{
		      logger.debug("Error retrieving memory usage for table " + tableName);
		      throw new SqlFireException(e); 
		}
		
		return res;
	}
}
