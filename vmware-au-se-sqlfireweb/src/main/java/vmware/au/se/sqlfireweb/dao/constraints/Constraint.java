package vmware.au.se.sqlfireweb.dao.constraints;

public class Constraint 
{
	private String schemaName;
	private String tableName;
	private String constraintName;
	private String type;
	private String state;
	
	public Constraint()
	{
	}
	
	public Constraint(String schemaName, String tableName, String constraintName, String type,
			String state) 
	{
		super();
		this.schemaName = schemaName;
		this.tableName = tableName;
		this.constraintName = constraintName;
		this.type = type;
		this.state = state;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getConstraintName() {
		return constraintName;
	}

	public void setConstraintName(String constraintName) {
		this.constraintName = constraintName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	@Override
	public String toString() {
		return "Constraint [schemaName=" + schemaName + ", tableName="
				+ tableName + ", constraintName=" + constraintName + ", type="
				+ type + ", state=" + state + "]";
	}
	
	
}
