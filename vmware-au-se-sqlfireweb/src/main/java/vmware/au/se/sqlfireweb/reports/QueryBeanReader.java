package vmware.au.se.sqlfireweb.reports;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;


public class QueryBeanReader 
{
	  protected static Logger logger = Logger.getLogger("controller");
	  private static QueryBeanReader instance = null;
	  private ApplicationContext appCtx = null;
	  
	  static
	  {
	    instance = new QueryBeanReader();  
	  }
	  


	  private QueryBeanReader()
	  {
	      appCtx = new ClassPathXmlApplicationContext("query-beans.xml");
	      logger.info("query-beans.xml has been read");
	  }
	  
	  public static QueryBeanReader getInstance()
	  {
	    return instance;
	  }
 
	  public QueryList getQueryListBean (String name)
	  {
	    QueryList ql = null;
	    
	    ql = (QueryList) appCtx.getBean(name);
	    
	    return ql;
	  }
	  
	  public Query getQueryBean (String id)
	  {
	    Query q = null;
	    
	    q = (Query) appCtx.getBean(id);
	    return q;
	  }
	  
	  public String[] getQueryListBeans ()
	  {
	    String[] queryListBeans = appCtx.getBeanNamesForType(QueryList.class);

	    return queryListBeans;
	  }
}
