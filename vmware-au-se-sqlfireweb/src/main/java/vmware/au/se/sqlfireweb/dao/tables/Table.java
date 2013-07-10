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
package vmware.au.se.sqlfireweb.dao.tables;

public class Table 
{
	public String schemaName;
	public String tableName;
	public String dataPolicy;
	public String serverGroups;
	public String loader;
	public String writer;
	
	public Table() {	
	}
	
	public Table(String schemaName, String tableName, String dataPolicy, String serverGroups,
			String loader, String writer) {
		super();
		this.schemaName = schemaName;
		this.tableName = tableName;
		this.dataPolicy = dataPolicy;
		this.serverGroups = serverGroups;
		this.loader = loader;
		this.writer = writer;
	}


	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getDataPolicy() {
		return dataPolicy;
	}

	public void setDataPolicy(String dataPolicy) {
		this.dataPolicy = dataPolicy;
	}

	public String getServerGroups() {
		return serverGroups;
	}

	public void setServerGroups(String serverGroups) {
		this.serverGroups = serverGroups;
	}

	public String getLoader() {
		return loader;
	}

	public void setLoader(String loader) {
		this.loader = loader;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	
	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	@Override
	public String toString() {
		return "Table [schemaName=" + schemaName + ", tableName=" + tableName
				+ ", dataPolicy=" + dataPolicy + ", serverGroups="
				+ serverGroups + ", loader=" + loader + ", writer=" + writer
				+ "]";
	}
	
}
