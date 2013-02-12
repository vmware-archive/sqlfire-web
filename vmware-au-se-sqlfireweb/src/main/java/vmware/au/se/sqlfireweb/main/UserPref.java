package vmware.au.se.sqlfireweb.main;

public class UserPref 
{
	private int recordsToDisplay;
	private int maxRecordsinSQLQueryWindow;
	private String autoCommit;
	
	public UserPref()
	{
	   recordsToDisplay = 20;
	   maxRecordsinSQLQueryWindow = 5000;
	   autoCommit = "Y";
	}
	
	public UserPref(int recordsToDisplay, int maxRecordsinSQLQueryWindow) 
	{
		this.recordsToDisplay = recordsToDisplay;
		this.maxRecordsinSQLQueryWindow = maxRecordsinSQLQueryWindow;
	}

	public int getRecordsToDisplay() {
		return recordsToDisplay;
	}

	public void setRecordsToDisplay(int recordsToDisplay) {
		this.recordsToDisplay = recordsToDisplay;
	}

	public int getMaxRecordsinSQLQueryWindow() {
		return maxRecordsinSQLQueryWindow;
	}

	public void setMaxRecordsinSQLQueryWindow(int maxRecordsinSQLQueryWindow) {
		this.maxRecordsinSQLQueryWindow = maxRecordsinSQLQueryWindow;
	}

	public String getAutoCommit() {
		return autoCommit;
	}

	public void setAutoCommit(String autoCommit) {
		this.autoCommit = autoCommit;
	}

	@Override
	public String toString() {
		return "UserPref [recordsToDisplay=" + recordsToDisplay
				+ ", maxRecordsinSQLQueryWindow=" + maxRecordsinSQLQueryWindow
				+ ", autoCommit=" + autoCommit + "]";
	}
	
	
	
}
