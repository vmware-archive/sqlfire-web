package vmware.au.se.sqlfireweb.dao.views;

public class View 
{
	public String schemaName;
	public String viewName;
	public String dataPolicy;
	public String definition;
	
	public View()
	{	
	}
	
	public View(String schemaName, String viewName, String dataPolicy, String definition) 
	{
		super();
		this.schemaName = schemaName;
		this.viewName = viewName;
		this.dataPolicy = dataPolicy;
		this.definition = definition;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public String getDataPolicy() {
		return dataPolicy;
	}

	public void setDataPolicy(String dataPolicy) {
		this.dataPolicy = dataPolicy;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	
	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	@Override
	public String toString() {
		return "View [schemaName=" + schemaName + ", viewName=" + viewName
				+ ", dataPolicy=" + dataPolicy + ", definition=" + definition
				+ "]";
	}
	
	
	
}
