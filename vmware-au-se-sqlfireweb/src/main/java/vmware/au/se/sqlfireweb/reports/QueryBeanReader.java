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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


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
