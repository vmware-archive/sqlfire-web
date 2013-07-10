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
package vmware.au.se.sqlfireweb.utils;

import java.sql.Connection;

public class SQLFireJDBCConnection 
{
	private Connection conn;
	private String url;
	private String connectedAt;
	private String schema;

	public SQLFireJDBCConnection(Connection conn, String url,
			String connectedAt, String schema) {
		super();
		this.conn = conn;
		this.url = url;
		this.connectedAt = connectedAt;
		this.schema = schema;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getConnectedAt() {
		return connectedAt;
	}

	public void setConnectedAt(String connectedAt) {
		this.connectedAt = connectedAt;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}
	
}
