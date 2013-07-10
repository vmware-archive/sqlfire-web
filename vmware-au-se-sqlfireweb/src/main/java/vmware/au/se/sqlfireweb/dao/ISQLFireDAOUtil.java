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
package vmware.au.se.sqlfireweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vmware.au.se.sqlfireweb.main.Result;
import vmware.au.se.sqlfireweb.main.SqlFireException;
import vmware.au.se.sqlfireweb.utils.AdminUtil;
import vmware.au.se.sqlfireweb.utils.JDBCUtil;

public class ISQLFireDAOUtil 
{
	  static public Result runCommand (String command, String userKey) throws SqlFireException
	  {
	    Result res = new Result();
	    Connection        conn    = null;
	    Statement         stmt    = null;
	    
	    res.setCommand(command);

	    try
	    {
	      conn = AdminUtil.getConnection(userKey);
	      stmt = conn.createStatement();   

	      stmt.execute(command);
	      // no need to commit it's auto commit already as it's DDL statement.
	      res.setCommand(command);
	      res.setMessage("SUCCESS");
	    }
	    catch (SQLException se)
	    {
	      // we don't want to stop it running we just need the error
	      res.setMessage(se.getMessage());
	    }
	    catch (Exception ex)
	    {
	      throw new SqlFireException(ex);   
	    }
	    finally 
	    {
	      JDBCUtil.close(stmt);
	    }
	    
	    return res;
	  }

	  static public Result runStoredCommand (String command, String userKey) throws SqlFireException
	  {
	    Result res = new Result();
	    Connection        			conn    = null;
	    PreparedStatement         	stmt    = null;
	    
	    res.setCommand(command);

	    try
	    {
	      conn = AdminUtil.getConnection(userKey);
	      stmt = conn.prepareCall(command);
	      stmt.execute();
	      
	      // no need to commit it's auto commit already as it's DDL statement.
	      res.setCommand(command);
	      res.setMessage("SUCCESS");
	    }
	    catch (SQLException se)
	    {
	      // we don't want to stop it running we just need the error
	      res.setMessage(se.getMessage());
	    }
	    catch (Exception ex)
	    {
	      throw new SqlFireException(ex);   
	    }
	    finally 
	    {
	      JDBCUtil.close(stmt);
	    }
	    
	    return res;
	  }
	  
	  static public List<String> getAllSchemas (String userKey) throws SqlFireException
	  {
		  List<String> schemas = new ArrayList<String>();
		  Connection        conn    = null;
		  Statement         stmt    = null;
		  ResultSet 		rset = null;
		  String sql = "select schemaname from sys.sysschemas order by 1";
	      try
	      {
	    	  conn = AdminUtil.getConnection(userKey);
	    	  stmt = conn.createStatement();  
	    	  rset = stmt.executeQuery(sql);
	    	  
	    	  while (rset.next())
	    	  {
	    		  schemas.add(rset.getString(1));
	    	  }
	      }
	      catch (Exception ex)
	      {
	    	  throw new SqlFireException(ex);   
	      }
	      finally 
	      {
	    	  JDBCUtil.close(stmt);
	      }
	      
		  return schemas;
		  
	  }
}
