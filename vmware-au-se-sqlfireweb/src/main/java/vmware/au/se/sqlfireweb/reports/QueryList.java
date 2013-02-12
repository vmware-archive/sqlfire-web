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
