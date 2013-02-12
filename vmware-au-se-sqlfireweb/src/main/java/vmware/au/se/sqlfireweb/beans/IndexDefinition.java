package vmware.au.se.sqlfireweb.beans;

public class IndexDefinition 
{
	  private String columnName;
	  private String orderType;
	
	  public IndexDefinition(String columnName, String orderType) {
		super();
		this.columnName = columnName;
		this.orderType = orderType;
	  }

	public String getColumnName() {
		return columnName;
	}
	
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
	public String getOrderType() {
		return orderType;
	}
	
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	@Override
	public String toString() {
		return "IndexDefinition [columnName=" + columnName + ", orderType="
				+ orderType + "]";
	}
  
	
  
}
