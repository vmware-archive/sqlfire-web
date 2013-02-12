package vmware.au.se.sqlfireweb.dao.stored;

public class ProcedureParameter 
{
	private int parameterId;
	private String columnName;
	private String typeName;
	
	public ProcedureParameter(int parameterId, String columnName, String typeName) 
	{
		super();
		this.parameterId = parameterId;
		this.columnName = columnName;
		this.typeName = typeName;
	}
	
	public ProcedureParameter()
	{
	}

	public int getParameterId() {
		return parameterId;
	}

	public void setParameterId(int parameterId) {
		this.parameterId = parameterId;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public String toString() {
		return "ProcedureParameter [parameterId=" + parameterId
				+ ", columnName=" + columnName + ", typeName=" + typeName + "]";
	}
	
}
