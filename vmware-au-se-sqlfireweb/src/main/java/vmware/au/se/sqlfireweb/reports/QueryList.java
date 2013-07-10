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

import java.util.Map;
import java.util.Set;

public class QueryList
{
  private Map queryList;
  private String description;
  
  public QueryList()
  {  
  }
  
  public void setQueryList(Map queryList)
  {
    this.queryList = queryList;
  }

  public Map getQueryList()
  {
    return queryList;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public String getDescription()
  {
    return description;
  }

  @Override
  public String toString()
  {
    StringBuffer sb = new StringBuffer();
    
    sb.append("QueryList : paramMap keys " + queryList.keySet() + "\n");;
    sb.append("Data \n");
    Set<String> keys = queryList.keySet();
    
    for (String key: keys)
    {
      sb.append("key: " + key + " , value: " + queryList.get(key) + "\n");
    }
    
    return sb.toString();
  }
}
