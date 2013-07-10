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
