package vmware.au.se.sqlfireweb.reports;

import org.apache.log4j.Logger;

import vmware.au.se.sqlfireweb.main.SqlFireException;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.jsp.jstl.sql.Result;

public class GenericQuery extends QueryBase 
{
	  protected static Logger logger = Logger.getLogger("controller");
	
	  @Override
	  public Result invoke(Connection conn) throws SqlFireException, SQLException
	  {
	    logger.info("GenericQuery - invoke called");
	    return super.invoke(conn);
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
}
