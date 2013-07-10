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
package vmware.au.se.sqlfireweb.dao.stored;

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

public class StoredProcDAOImpl implements StoredProcDAO
{
	protected static Logger logger = Logger.getLogger("controller");
	
	public List<StoredProc> retrieveProcList(String schema, String search, String procType, String userKey) throws SqlFireException 
	{
	    Connection        	conn = null;
	    PreparedStatement 	stmt = null;
	    ResultSet         	rset = null;
	    List<StoredProc>    procs = null;
	    String            	srch = null;
	    
	    try
	    {
	      conn = AdminUtil.getConnection(userKey);
	      stmt = conn.prepareStatement(Constants.USER_STORED_CODE);
	      if (search == null)
	        srch = "%";
	      else
	        srch = "%" + search.toUpperCase() + "%";
	      
	      stmt.setString(1, procType);
	      stmt.setString(2, schema);
	      stmt.setString(3, srch);
	      rset = stmt.executeQuery();

	      procs = makeProcListFromResultSet(rset);
	    }
	    catch (SQLException se)
	    {
	      logger.debug("Error retrieving all procs with search string = " + search);
	      throw new SqlFireException(se);
	    }
	    catch (Exception ex)
	    {
	      logger.debug("Error retrieving all procs with search string = " + search);
	      throw new SqlFireException(ex);      
	    }
	    finally
	    {
	      // close all resources
	      JDBCUtil.close(rset);
	      JDBCUtil.close(stmt);
	    }
	    
	    return procs;
	}

	public Result simpleprocCommand(String schemaName, String procName, String type, String procType, String userKey) throws SqlFireException 
	{
	    String            command = null;
	    Result            res     = null;

	    if (type != null)
	    {
	      if (type.equalsIgnoreCase("DROP"))
	      {
	        command = 
	        		String.format(Constants.DROP_STORED_CODE, procType.toUpperCase(), 
	        												  schemaName,
	        				                                  procName);
	      }
	    }
	    
	    res = ISQLFireDAOUtil.runCommand(command, userKey);
	    
	    return res;
	}

	private List<StoredProc> makeProcListFromResultSet (ResultSet rset) throws SQLException
	{
		List<StoredProc> procs = new ArrayList<StoredProc>();
		
		while (rset.next())
		{
			StoredProc storedProc = 
					new StoredProc(rset.getString(1), rset.getString(2), rset.getString(3));
			procs.add(storedProc);
		}
		
		return procs;
		
	}

	public List<ProcedureParameter> describeProcedure(String schemaName, String procName, String userKey) throws SqlFireException 
	{
		List<ProcedureParameter> procParams = new ArrayList<ProcedureParameter>();

	    Connection        	conn = null;
	    ResultSet         	rset = null;
	   
	    
	    try
	    {
	      conn = AdminUtil.getConnection(userKey);
		  rset = conn.getMetaData().getProcedureColumns
				   (null, schemaName.toUpperCase(), procName.toUpperCase(), "%");
			
		  while (rset.next())
		  {
			  ProcedureParameter param = 
					  new ProcedureParameter(rset.getInt("PARAMETER_ID"),
							  				 rset.getString("COLUMN_NAME"),
							  				 rset.getString("TYPE_NAME"));
			
			  procParams.add(param);
		  }	     
	    }
	    catch (SQLException se)
	    {
	      logger.debug("Error retrieving all parameters for procedure with name " + procName);
	      throw new SqlFireException(se);
	    }
	    catch (Exception ex)
	    {
	      logger.debug("Error retrieving all parameters for procedure with name " + procName);
	      throw new SqlFireException(ex);      
	    }
	    finally
	    {
	      // close all resources
	      JDBCUtil.close(rset);
	    }
	    
		// TODO Auto-generated method stub
		return procParams;
	}
	
}
