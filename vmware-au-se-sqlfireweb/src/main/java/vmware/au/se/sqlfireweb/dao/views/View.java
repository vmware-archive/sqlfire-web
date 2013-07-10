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
