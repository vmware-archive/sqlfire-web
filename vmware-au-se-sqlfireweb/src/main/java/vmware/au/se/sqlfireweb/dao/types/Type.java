package vmware.au.se.sqlfireweb.dao.types;

public class Type 
{
	private String schemaName;
	private String typeName;
	private String javaClassName;
	
	public Type()
	{	
	}
	
	public Type(String schemaName, String typeName, String javaClassName) {
		super();
		this.schemaName = schemaName;
		this.typeName = typeName;
		this.javaClassName = javaClassName;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getJavaClassName() {
		return javaClassName;
	}

	public void setJavaClassName(String javaClassName) {
		this.javaClassName = javaClassName;
	}

	@Override
	public String toString() {
		return "Type [schemaName=" + schemaName + ", typeName=" + typeName
				+ ", javaClassName=" + javaClassName + "]";
	}
	
}
