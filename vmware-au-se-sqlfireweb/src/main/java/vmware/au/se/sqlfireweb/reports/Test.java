package vmware.au.se.sqlfireweb.reports;

import java.util.Arrays;
import java.util.List;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    QueryBeanReader reader = QueryBeanReader.getInstance();
	    List<String> qlBeans = Arrays.asList(reader.getQueryListBeans());
	    
	    for (String beanName: qlBeans)
	    {
	      System.out.println("--");
	      QueryList ql = reader.getQueryListBean(beanName);
	      System.out.println(ql.getDescription());
	      System.out.println(ql);   
	    }

	}

}
