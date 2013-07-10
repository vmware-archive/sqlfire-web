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

import java.util.List;

import vmware.au.se.sqlfireweb.main.Result;
import vmware.au.se.sqlfireweb.main.SqlFireException;

public interface TableDAO 
{
	public List<Table> retrieveTableList(String schema, String search, String userKey) throws SqlFireException;
	
	public Result simpletableCommand (String schemaName, String tableName, String type, String userKey) throws SqlFireException;
	
	public String generateLoadScript (String schema, String tableName) throws SqlFireException;
	
	public javax.servlet.jsp.jstl.sql.Result getDataLocations (String schema, String tableName, String userKey) throws SqlFireException;
	
	public String viewPartitionAttrs (String schema, String tableName, String userKey) throws SqlFireException;
	
	public javax.servlet.jsp.jstl.sql.Result getExpirationEvictionAttrs (String schema, String tableName, String userKey) throws SqlFireException;

	public javax.servlet.jsp.jstl.sql.Result getAllTableInfo (String schema, String tableName, String userKey) throws SqlFireException;
	
	public javax.servlet.jsp.jstl.sql.Result getTableStructure (String schema, String tableName, String userKey) throws SqlFireException;
	
	public javax.servlet.jsp.jstl.sql.Result getMemoryUsage (String schema, String tableName, String userKey) throws SqlFireException;
}
