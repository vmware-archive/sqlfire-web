package vmware.au.se.sqlfireweb.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.sql.PreparedStatement;
import java.text.DecimalFormat;

import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;

import vmware.au.se.sqlfireweb.beans.CommandResult;

public class QueryUtil 
{
	  static public Result runQuery (Connection conn, String query, int maxrows) throws SQLException
	  {
	    Statement stmt  = null;
	    ResultSet rset  = null;
	    Result    res   = null;
	    
	    try
	    {
	      stmt = conn.createStatement();
	      rset = stmt.executeQuery(query);
	      
	      /* 
	       * Convert the ResultSet to a 
	       * Result object that can be used with JSTL tags 
	       */
	      if (maxrows == -1)
	      {
	        res = ResultSupport.toResult(rset);
	      }
	      else
	      {
	        res = ResultSupport.toResult(rset, maxrows);
	      }
	    }
	    finally
	    {
          JDBCUtil.close(stmt);
          JDBCUtil.close(rset);
	    }
	    
	    return res;
	  }
	  
	  static public int runQueryCount (Connection conn, String query) throws SQLException
	  {
	    Statement stmt  = null;
	    ResultSet rset  = null;
	    int count = 0;
	    
	    try
	    {
	      stmt = conn.createStatement();
	      rset = stmt.executeQuery("select count(*) from (" + query + ") as \"Count\"");
	      rset.next();
	      count = rset.getInt(1);
	    }
	    catch (SQLException se)
	    {
	      // do nothing if we can't get count.
	    }
	    finally
	    {
	          JDBCUtil.close(stmt);
	          JDBCUtil.close(rset);
	    }
	    
	    return count;
	  }
	  
	  static public CommandResult runCommitOrRollback (Connection conn, boolean commit, String elapsedTime) throws SQLException
	  {
		  CommandResult res = new CommandResult();
		  
		  try
		  {
			  long start = System.currentTimeMillis();
			  
			  if (commit)
			  {
				  conn.commit(); 
				  res.setCommand("commit");
			  }
			  else
			  {
				  conn.rollback();
				  res.setCommand("rollback");
			  }
			  
		      long end = System.currentTimeMillis();
		      double timeTaken = new Double(end - start).doubleValue();
		      DecimalFormat df = new DecimalFormat("#.##");
		      
		      if (elapsedTime.equals("Y"))
		      {
		    	  res.setElapsedTime("" + df.format(timeTaken));
		      }
		      
		      res.setRows(0);
		      res.setMessage("SUCCESS");
		      
		  }
		  catch (SQLException se)
		  {
		      // we don't want to stop it running we just need the error
		      res.setMessage(se.getMessage());
		      res.setRows(-1);
		  }
		  
		  return res;
		  
	  }
	  
	  static public CommandResult runCommand (Connection conn, String command, String elapsedTime) throws SQLException
	  {
	    CommandResult res = new CommandResult();

	    Statement         stmt    = null;
	    
	    res.setCommand(command);

	    try
	    {
	      stmt = conn.createStatement();   

	      long start = System.currentTimeMillis();
	      int rowsAffected = stmt.executeUpdate(command); 
	      long end = System.currentTimeMillis();
	      
	      double timeTaken = new Double(end - start).doubleValue();
	      DecimalFormat df = new DecimalFormat("#.##");
	      
	      res.setRows(rowsAffected);
	      
	      // no need to commit it's auto commit already as it's DDL statement.
	      res.setCommand(command);
	      res.setMessage("SUCCESS");
	      
	      if (elapsedTime.equals("Y"))
	      {
	    	  res.setElapsedTime("" + df.format(timeTaken));
	      }
	    }
	    catch (SQLException se)
	    {
	      // we don't want to stop it running we just need the error
	      res.setMessage(se.getMessage());
	      res.setRows(-1);
	    }
	    finally 
	    {
	      JDBCUtil.close(stmt);
	    }
	    
	    return res;
	  }
	  
	  static public Map<String, String> populateSchemaMap(Connection conn, Map<String, String> schemaMap, String schema) throws SQLException
	  {
		  String sql = 
				  "select object_type, count(*) " +
				  "from (select tablename, 'Table' as OBJECT_TYPE FROM SYS.SYSTABLES t, sys.sysschemas s WHERE s.schemaid = t.schemaid and t.TABLESCHEMANAME = ? and t.tabletype != 'V') x " +  
				  "group by object_type " +
				  "union " + 
				  "select object_type, count(*) " +
				  "from (select v.tableid, 'View' as OBJECT_TYPE FROM SYS.SYSVIEWS v, SYS.SYSSCHEMAS s, sys.systables t WHERE s.schemaid = v.compilationschemaid and t.tableid = v.tableid AND s.schemaname = ?) y " + 
				  "group by object_type " +
				  "union " +
				  "select object_type, count(*) " + 
				  "from (select constraintname, 'Constraint' as OBJECT_TYPE FROM sys.systables t, sys.sysconstraints c, sys.sysschemas s where s.schemaid = c.schemaid and t.tableid = c.tableid and s.schemaname = ?) z " +
				  "group by object_type " +
				  "union " + 
				  "select object_type, count(*) " + 
				  "from (select index, 'Index' as OBJECT_TYPE FROM sys.indexes where \"SCHEMA\" = ?) z " +
				  // add when using latest version
				  //"from (select indexname, 'Index' as OBJECT_TYPE FROM sys.indexes where schemaname = ?) z " +
				  "group by object_type " + 
				  "union " +
				  "select object_type, count(*) " +  
				  "from (select triggerid, 'Trigger' as OBJECT_TYPE from SYS.SYSTRIGGERS t, sys.SYSSCHEMAS s where s.schemaid = t.schemaid and s.schemaname = ?) bb " +
				  "group by object_type " +
				  "union " +
				  "select object_type, count(*) " +
				  "from (select alias, 'Procedure' as OBJECT_TYPE from SYS.SYSALIASES a, sys.SYSSCHEMAS s where s.schemaid = a.schemaid and a.aliastype='P' and s.schemaname = ?) cc " +
				  "group by object_type " + 
				  "union " +
				  "select object_type, count(*) " +
				  "from (select alias, 'Function' as OBJECT_TYPE from SYS.SYSALIASES a, sys.SYSSCHEMAS s where s.schemaid = a.schemaid and a.aliastype='F' and s.schemaname = ?) dd " +
				  "group by object_type " +
				  "union " +
				  "select object_type, count(*) from (select 1, 'Diskstore' as OBJECT_TYPE from SYS.SYSDISKSTORES) ee " +
				  "group by object_type " + 
				  "union " +
				  "select object_type, count(*) " +
				  "from (select 1, 'AsyncEventList' as OBJECT_TYPE from SYS.ASYNCEVENTLISTENERS) ff " +
				  "group by object_type " + 
				  "union " +
				  "select object_type, count(*) " +
				  "from (select 1, 'Sender' as OBJECT_TYPE from SYS.GATEWAYSENDERS) gg " +
				  "group by object_type " +
				  "union " +
				  "select object_type, count(*) " +
				  "from (select 1, 'Receiver' as OBJECT_TYPE from SYS.GATEWAYRECEIVERS) hh " +
				  "group by object_type " + 
				  "union " +
				  "select object_type, count(*) " +
				  "from (select 1, 'Type' as OBJECT_TYPE from sys.sysaliases WHERE  aliasschemaname = ? and aliastype = 'A') ii " +
				  "group by object_type";
		
		  PreparedStatement pstmt = null;
		  ResultSet rset = null;
		  Map<String, String> schemaMapLocal = schemaMap;
		  
		  try
		  {
			  pstmt = conn.prepareStatement(sql);
			  pstmt.setString(1, schema);
			  pstmt.setString(2, schema);
			  pstmt.setString(3, schema);
			  pstmt.setString(4, schema);
			  pstmt.setString(5, schema);
			  pstmt.setString(6, schema);
			  // add back in when index fixed
			  pstmt.setString(7, schema); 
			  pstmt.setString(8, schema);
			  
			  rset = pstmt.executeQuery();
			  while (rset.next())
			  {
				  schemaMapLocal.put(rset.getString(1).trim(), rset.getString(2));
			  }
			  
			  //System.out.println(schemaMapLocal);
		  }
		  finally
		  {
			  JDBCUtil.close(pstmt);
		  }
		  
		  return schemaMapLocal;
	  }
}
