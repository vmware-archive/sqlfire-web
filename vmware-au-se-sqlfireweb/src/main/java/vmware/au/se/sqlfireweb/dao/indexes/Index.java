package vmware.au.se.sqlfireweb.dao.indexes;

public class Index 
{
	private String index;
	private String unique;
	private String table;
	private String indexType;
	private String columnsAndOrder;
	private String schemaName;
	
	
	public Index(String index, String unique, String table, String indexType,
			String columnsAndOrder, String schemaName) {
		super();
		this.index = index;
		this.unique = unique;
		this.table = table;
		this.indexType = indexType;
		this.columnsAndOrder = columnsAndOrder;
		this.schemaName = schemaName;
	}
	
	public String getIndex() {
		return index;
	}
	
	public void setIndex(String index) {
		this.index = index;
	}
	
	public String getUnique() {
		return unique;
	}
	
	public void setUnique(String unique) {
		this.unique = unique;
	}
	
	public String getTable() {
		return table;
	}
	
	public void setTable(String table) {
		this.table = table;
	}
	
	public String getIndexType() {
		return indexType;
	}
	public void setIndexType(String indexType) {
		this.indexType = indexType;
	}
	
	public String getColumnsAndOrder() {
		return columnsAndOrder;
	}
	
	public void setColumnsAndOrder(String columnsAndOrder) {
		this.columnsAndOrder = columnsAndOrder;
	}

	
	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	@Override
	public String toString() {
		return "Index [index=" + index + ", unique=" + unique + ", table="
				+ table + ", indexType=" + indexType + ", columnsAndOrder="
				+ columnsAndOrder + ", schemaName=" + schemaName + "]";
	}
	
	
	
}
