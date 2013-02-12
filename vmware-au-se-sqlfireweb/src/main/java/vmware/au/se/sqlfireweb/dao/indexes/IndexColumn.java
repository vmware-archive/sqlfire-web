package vmware.au.se.sqlfireweb.dao.indexes;

public class IndexColumn 
{
	private String columnName;

	public IndexColumn(String columnName) 
	{
		super();
		this.columnName = columnName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	@Override
	public String toString() {
		return "IndexColumn [columnName=" + columnName + "]";
	}  
  
}
