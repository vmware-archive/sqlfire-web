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
package vmware.au.se.sqlfireweb.dao.stored;

public class StoredProc 
{
	
	private String schemaName;
    private String name;
    private String javaClassName;
  
  	public StoredProc()
  	{
  	}

	public StoredProc(String schemaName , String name, String javaClassName) 
	{
		super();
		this.schemaName = schemaName;
		this.name = name;
		this.javaClassName = javaClassName;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getJavaClassName() {
		return javaClassName;
	}
	
	public void setJavaClassName(String javaClassName) {
		this.javaClassName = javaClassName;
	}

	
	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	@Override
	public String toString() {
		return "StoredProc [schemaName=" + schemaName + ", name=" + name
				+ ", javaClassName=" + javaClassName + "]";
	}
  
	
  
}
