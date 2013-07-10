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

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.jsp.jstl.sql.Result;

import vmware.au.se.sqlfireweb.main.SqlFireException;
import vmware.au.se.sqlfireweb.utils.QueryUtil;

public abstract class QueryBase implements Query 
{

	  private String queryDescription;
	  private String query;
	  
	  public QueryBase()
	  {
	    super();
	  }

	  public void setQueryDescription(String queryDescription)
	  {
	    this.queryDescription = queryDescription;
	  }

	  public String getQueryDescription()
	  {
	    return queryDescription;
	  }

	  public void setQuery(String query)
	  {
	    this.query = query;
	  }

	  public String getQuery()
	  {
	    return query;
	  }
	  
	  public Result invoke(Connection conn) throws SqlFireException, SQLException
	  {
	    Result res = null;
	    res= QueryUtil.runQuery(conn, query, -1);
	    return res;
	  }

}
