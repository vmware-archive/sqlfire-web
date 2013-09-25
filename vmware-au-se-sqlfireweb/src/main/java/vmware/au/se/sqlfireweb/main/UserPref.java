/*
  	Copyright (c) 2013 GoPivotal, Inc. All Rights Reserved.

	This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; only version 2 of the License, and no
    later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

	The full text of the GPL is provided in the COPYING file.
*/
package vmware.au.se.sqlfireweb.main;

public class UserPref 
{
	private int recordsToDisplay;
	private int maxRecordsinSQLQueryWindow;
	private String autoCommit;
	private int historySize;
	
	public UserPref()
	{
	   recordsToDisplay = 20;
	   maxRecordsinSQLQueryWindow = 5000;
	   autoCommit = "N";
	   historySize = 50;
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

	
	public int getHistorySize() {
		return historySize;
	}

	public void setHistorySize(int historySize) {
		this.historySize = historySize;
	}

	@Override
	public String toString() {
		return "UserPref [recordsToDisplay=" + recordsToDisplay
				+ ", maxRecordsinSQLQueryWindow=" + maxRecordsinSQLQueryWindow
				+ ", autoCommit=" + autoCommit + ", historySize=" + historySize
				+ "]";
	}
	
	
	
}
