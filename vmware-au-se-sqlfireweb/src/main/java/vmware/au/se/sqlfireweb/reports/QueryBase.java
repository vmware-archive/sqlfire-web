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
