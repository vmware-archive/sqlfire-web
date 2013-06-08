package vmware.au.se.sqlfireweb.beans;

public class QueryWindow 
{
	private String query;
	private String queryCount;
	private String elapsedTime;
	private String explainPlan;
	private String showMember;
	 
	public QueryWindow()
	{	
	
	}
	
	public QueryWindow(String query, String queryCount, String elapsedTime, String showMember) 
	{
		super();
		this.query = query;
		this.queryCount = queryCount;
		this.elapsedTime = elapsedTime;
		this.showMember = showMember;
	}

	
	public String getQuery() {
		return query;
	}


	public void setQuery(String query) {
		this.query = query;
	}


	public String getQueryCount() {
		return queryCount;
	}


	public void setQueryCount(String queryCount) {
		this.queryCount = queryCount;
	}


	public String getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(String elapsedTime) {
		this.elapsedTime = elapsedTime; 
	}

	
	public String getExplainPlan() {
		return explainPlan;
	}

	public void setExplainPlan(String explainPlan) {
		this.explainPlan = explainPlan;
	}

	
	public String getShowMember() {
		return showMember;
	}

	public void setShowMember(String showMember) {
		this.showMember = showMember;
	}

	@Override
	public String toString() {
		return "QueryWindow [query=" + query + ", queryCount=" + queryCount
				+ ", elapsedTime=" + elapsedTime + ", explainPlan="
				+ explainPlan + ", showMember=" + showMember + "]";
	}
  
	
}
