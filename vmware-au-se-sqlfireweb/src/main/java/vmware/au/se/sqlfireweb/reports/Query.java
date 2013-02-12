package vmware.au.se.sqlfireweb.reports;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.jsp.jstl.sql.Result;

import vmware.au.se.sqlfireweb.main.SqlFireException;

public interface Query
{
  public Result invoke (Connection conn) throws SqlFireException, SQLException;
  public void setQueryDescription(String queryDescription);
  public void setQuery (String query);
  
}
