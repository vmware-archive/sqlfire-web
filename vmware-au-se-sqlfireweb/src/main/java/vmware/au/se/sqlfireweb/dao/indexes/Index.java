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
package vmware.au.se.sqlfireweb.dao.indexes;

public class Index 
{
	private String index;
	private String unique;
	private String table;
	private String indexType;
	private String columnsAndOrder;
	private String schemaName;
	private String caseSensitive;
	
	
	public Index(String index, String unique, String table, String indexType,
			String columnsAndOrder, String schemaName, String caseSensitive) {
		super();
		this.index = index;
		this.unique = unique;
		this.table = table;
		this.indexType = indexType;
		this.columnsAndOrder = columnsAndOrder;
		this.schemaName = schemaName;
		this.caseSensitive = caseSensitive;
	}
	
	public String getIndex() {
		return index;
	}
	
	public void setIndex(String index) {
		this.index = index;
	}
	
	public String getUnique() {
		return unique;
	}
	
	public void setUnique(String unique) {
		this.unique = unique;
	}
	
	public String getTable() {
		return table;
	}
	
	public void setTable(String table) {
		this.table = table;
	}
	
	public String getIndexType() {
		return indexType;
	}
	public void setIndexType(String indexType) {
		this.indexType = indexType;
	}
	
	public String getColumnsAndOrder() {
		return columnsAndOrder;
	}
	
	public void setColumnsAndOrder(String columnsAndOrder) {
		this.columnsAndOrder = columnsAndOrder;
	}

	
	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	
	public String getCaseSensitive() {
		return caseSensitive;
	}

	public void setCaseSensitive(String caseSensitive) {
		this.caseSensitive = caseSensitive;
	}

	@Override
	public String toString() {
		return "Index [index=" + index + ", unique=" + unique + ", table="
				+ table + ", indexType=" + indexType + ", columnsAndOrder="
				+ columnsAndOrder + ", schemaName=" + schemaName
				+ ", caseSensitive=" + caseSensitive + "]";
	}
	
	
	
}
