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
package vmware.au.se.sqlfireweb.dao.indexes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import vmware.au.se.sqlfireweb.dao.ISQLFireDAOUtil;
import vmware.au.se.sqlfireweb.dao.indexes.Constants;
import vmware.au.se.sqlfireweb.main.Result;
import vmware.au.se.sqlfireweb.main.SqlFireException;
import vmware.au.se.sqlfireweb.utils.AdminUtil;
import vmware.au.se.sqlfireweb.utils.JDBCUtil;

public class IndexDAOImpl implements IndexDAO
{
	protected static Logger logger = Logger.getLogger("controller");
	
	public List<Index> retrieveIndexList(String schema, String search, String userKey) throws SqlFireException 
	{
	    Connection        conn = null;
	    PreparedStatement stmt = null;
	    ResultSet         rset = null;
	    List<Index>       indexes = null;
	    String            srch = null;
	    
	    try
	    {
	      conn = AdminUtil.getConnection(userKey);
	      stmt = conn.prepareStatement(Constants.USER_INDEXES);
	      if (search == null)
	        srch = "%";
	      else
	        srch = "%" + search.toUpperCase() + "%";
	      
	      stmt.setString(1, schema);
	      stmt.setString(2, srch);  
	      rset = stmt.executeQuery();

	      indexes = makeIndexListFromResultSet(rset);
	    }
	    catch (SQLException se)
	    {
	      logger.debug("Error retrieving all indexes with search string = " + search);
	      throw new SqlFireException(se);
	    }
	    catch (Exception ex)
	    {
	      logger.debug("Error retrieving all indexes with search string = " + search);
	      throw new SqlFireException(ex);      
	    }
	    finally
	    {
	      // close all resources
	      JDBCUtil.close(rset);
	      JDBCUtil.close(stmt);
	    }
	    
	    return indexes;
	}

	public Result simpleindexCommand(String schemaName, String indexName, String type, String userKey) throws SqlFireException 
	{
	    String            command = null;
	    Result            res     = null;

	    if (type != null)
	    {
	      if (type.equalsIgnoreCase("DROP"))
	      {
	        command = String.format(Constants.DROP_INDEX, schemaName, indexName);
	      }
	    }
	    
	    res = ISQLFireDAOUtil.runCommand(command, userKey);
	    
	    return res;
	}



	public List<IndexColumn> retrieveIndexColumns
		(String schemaName, String tableName, String userKey) throws SqlFireException 
	{
	    Connection        conn = null;
	    PreparedStatement stmt = null;
	    ResultSet         rset = null;
	    List<IndexColumn> columns = null;
	    
	    try
	    {
	      conn = AdminUtil.getConnection(userKey);
	      stmt = conn.prepareStatement(Constants.VIEW_TABLE_COLUMNS);
	      
	      stmt.setString(1, schemaName);
	      stmt.setString(2, tableName);  
	      rset = stmt.executeQuery();

	      columns =  makeIndexColumnListFromResultSet(rset);
	    }
	    catch (SQLException se)
	    {
	      logger.debug("Error retrieving all indexe columns for table = " + tableName);
	      throw new SqlFireException(se);
	    }
	    catch (Exception ex)
	    {
	      logger.debug("Error retrieving all indexe columns for table = " + tableName);
	      throw new SqlFireException(ex);      
	    }
	    finally
	    {
	      // close all resources
	      JDBCUtil.close(rset);
	      JDBCUtil.close(stmt);
	    }
	    
	    return columns;
	}

	private List<Index> makeIndexListFromResultSet (ResultSet rset) throws SQLException
	{
		List<Index> indexes = new ArrayList<Index>();
		
		while (rset.next())
		{
			Index index = new Index(rset.getString(1),
					                rset.getString(2),
					                rset.getString(3),
					                rset.getString(4),
					                rset.getString(5),
					                rset.getString(6));
			indexes.add(index);
		}
		
		return indexes;
		
	}

	private List<IndexColumn> makeIndexColumnListFromResultSet (ResultSet rset) throws SQLException
	{
		List<IndexColumn> columns = new ArrayList<IndexColumn>();
		
		while (rset.next())
		{
			IndexColumn idxCol = new IndexColumn(rset.getString(1));
			columns.add(idxCol);
		}
		
		return columns;
		
	}
}
