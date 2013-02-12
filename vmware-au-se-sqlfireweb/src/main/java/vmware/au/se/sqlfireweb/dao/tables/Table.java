package vmware.au.se.sqlfireweb.dao.tables;

public class Table 
{
	public String schemaName;
	public String tableName;
	public String dataPolicy;
	public String serverGroups;
	public String loader;
	public String writer;
	
	public Table() {	
	}
	
	public Table(String schemaName, String tableName, String dataPolicy, String serverGroups,
			String loader, String writer) {
		super();
		this.schemaName = schemaName;
		this.tableName = tableName;
		this.dataPolicy = dataPolicy;
		this.serverGroups = serverGroups;
		this.loader = loader;
		this.writer = writer;
	}


	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getDataPolicy() {
		return dataPolicy;
	}

	public void setDataPolicy(String dataPolicy) {
		this.dataPolicy = dataPolicy;
	}

	public String getServerGroups() {
		return serverGroups;
	}

	public void setServerGroups(String serverGroups) {
		this.serverGroups = serverGroups;
	}

	public String getLoader() {
		return loader;
	}

	public void setLoader(String loader) {
		this.loader = loader;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	
	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	@Override
	public String toString() {
		return "Table [schemaName=" + schemaName + ", tableName=" + tableName
				+ ", dataPolicy=" + dataPolicy + ", serverGroups="
				+ serverGroups + ", loader=" + loader + ", writer=" + writer
				+ "]";
	}
	
}
