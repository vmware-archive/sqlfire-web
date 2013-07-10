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
package vmware.au.se.sqlfireweb.reports;

import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.jstl.sql.Result;

import javax.servlet.jsp.jstl.sql.ResultSupport;

import vmware.au.se.sqlfireweb.main.SqlFireException;
import vmware.au.se.sqlfireweb.utils.JDBCUtil;


public class ParameterQuery extends QueryBase 
{
	protected static Logger logger = Logger.getLogger("controller");

	  private Map paramMap;
	  private Map paramValues;
	  
	  @Override
	  public Result invoke(Connection conn) throws SqlFireException, SQLException
	  {
		logger.info("ParamaterQuery - invoke called");
	    Result res = null;
	    PreparedStatement pstmt = null;
	    ResultSet rset  = null;

	    try
	    {
	      pstmt = conn.prepareStatement(getQuery());

	      // TODO: read params, create PreparedStatement, set variables, runquery
	      @SuppressWarnings("unchecked")
		  Set<String> keys = getParamMap().keySet();
	      int i = 0;
	      for (String param: keys)
	      {
	    	i++;
	        pstmt.setString(Integer.parseInt(param), (String) paramValues.get(param));
	      }

	      rset = pstmt.executeQuery();
	      res = ResultSupport.toResult(rset);
	    }
	    finally
	    {
	      // close all resources
	      JDBCUtil.close(rset);
	      JDBCUtil.close(pstmt);
	    }
	    
	    return res;
	  }

	  public void setParamMap(Map paramMap)
	  {
	    this.paramMap = paramMap;
	  }

	  public Map getParamMap()
	  {
	    return paramMap;
	  }

	  @Override
	  public String getQuery()
	  {
	    return super.getQuery();
	  }

	  @Override
	  public String getQueryDescription()
	  {
	    return super.getQueryDescription();
	  }

	  public void setParamValues(Map paramValues)
	  {
	    this.paramValues = paramValues;
	  }

	  public Map getParamValues()
	  {
	    return paramValues;
	  }
}
