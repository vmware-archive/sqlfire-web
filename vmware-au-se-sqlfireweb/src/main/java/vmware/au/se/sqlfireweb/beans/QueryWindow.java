package vmware.au.se.sqlfireweb.beans;

public class QueryWindow 
{
	private String query;
	private String queryCount;
	private String elapsedTime;
	
	public QueryWindow()
	{	
	}
	
	public QueryWindow(String query, String queryCount, String elapsedTime) 
	{
		super();
		this.query = query;
		this.queryCount = queryCount;
		this.elapsedTime = elapsedTime;
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

	
	@Override
	public String toString() {
		return "QueryWindow [query=" + query + ", queryCount=" + queryCount
				+ ", elapsedTime=" + elapsedTime + "]";
	}
  
	
}
