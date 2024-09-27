<%--
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
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="sqlfireweb.appname" /> - Welcome Page</title>
<link rel="stylesheet" type="text/css" href="css/isqlfire.css" />
<link rel="stylesheet" type="text/css" href="css/print.css" media="print" />
</head>
<body>

<h2>SQLFire*Web - Welcome Page</h2>

<a href="sqlfireweb/home" target="_top" title="Home">
  <img class="icon" src="./themes/original/img/b_home.png" width="16" height="16" alt="Home" />
  Home Page
</a>&nbsp; | &nbsp;
<a href="sqlfireweb/preferences" title="Preferences">
  <img class="icon" src="./themes/original/img/b_props.png" width="16" height="16" alt="SQLFire Preferences" />
  Preferences
</a>&nbsp; | &nbsp;
<a href="https://pubs.vmware.com/vfabric53/index.jsp?topic=/com.vmware.vfabric.sqlfire.1.1/about_users_guide.html" title="SQLFire Documentation" target="_top">
  <img class="icon" src="./themes/original/img/b_docs.png" width="16" height="16" alt="SQLFire doco" />
  SQLFire Documentation
</a>&nbsp; | &nbsp;
<a href="sqlfireweb/viewconmap" title="View Connection Map">
  <img class="icon" src="./themes/original/img/Connection.gif" width="16" height="16" alt="View Connections Map" />
  Connection Map
</a>
<p />

<div class="success">
Connected to SQLFire using JDBC URL <b>${sessionScope.url}</b> 
</div>

<p>
SQLFire is an in-memory distributed SQL database. In the category of NewSQL databases, SQLFire 
delivers dynamic scalability and high performance for data-intensive modern applications.
</p>

SQLFire*Web is web based interface allowing multiple users to access SQLFire which includes features as follows.

<br />
<font color="darkblue">
	<ul>
	  <li>Browse schema objects + SQLFire schema objects</li>
	  <li>View the partitioning strategies of tables</li>
	  <li>View data distribution across members and SQL queries</li>
	  <li>Create Table/Index Wizard</li>
	  <li>View memory Usage for Tables/Indexes</li>
	  <li>Manipulate multiple schema objects at once</li>
	  <li>Run any SQL query, DML, DDL</li>
	  <li>Load/Execute SQL Files</li>
	  <li>Stop/Start Gateway senders</li>
	  <li>Stop/Start Async Event Listeners</li>
	  <li>Add your own dynamic SQL Reports to extend query capabilities</li>
	</ul> 
</font>

<p />

<jsp:include page="footer.html" flush="true" />

</body>
</html>