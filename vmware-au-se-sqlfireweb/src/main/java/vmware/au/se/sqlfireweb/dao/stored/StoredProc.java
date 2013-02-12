package vmware.au.se.sqlfireweb.dao.stored;

public class StoredProc 
{
	
	private String schemaName;
    private String name;
    private String javaClassName;
  
  	public StoredProc()
  	{
  	}

	public StoredProc(String schemaName , String name, String javaClassName) 
	{
		super();
		this.schemaName = schemaName;
		this.name = name;
		this.javaClassName = javaClassName;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getJavaClassName() {
		return javaClassName;
	}
	
	public void setJavaClassName(String javaClassName) {
		this.javaClassName = javaClassName;
	}

	
	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	@Override
	public String toString() {
		return "StoredProc [schemaName=" + schemaName + ", name=" + name
				+ ", javaClassName=" + javaClassName + "]";
	}
  
	
  
}
