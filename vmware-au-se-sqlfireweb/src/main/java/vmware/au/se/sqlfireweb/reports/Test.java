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
