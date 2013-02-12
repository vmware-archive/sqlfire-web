<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<base target="frame_content" />
<link rel="stylesheet" type="text/css" href="css/theme_left.css" />
<link rel="stylesheet" type="text/css" href="css/print.css" media="print" />
</head>
<body id="body_leftFrame">

<b><i>${sessionScope.schema} Schema </i></b>
<p />
<div id="left_tableList">
 <ul id="subel0">
  <li>
	<a href="sqlfireweb/createtable">
	 <img class="icon" width="16" height="16" src="./themes/original/img/b_newtbl.png" alt="Add Table" title="Add Table" />
	 Create Table
	</a>
  </li>
 </ul>
</div>

<hr />

<div id="left_tableList">
 <ul id="subel0">
  <li>
    <a href="sqlfireweb/query" title="SQL Worksheet">
      <img class="icon" src="./themes/original/img/b_sql.png" width="16" height="16" alt="SQL Worksheet" />
      Worksheet
    </a>
  </li>
  <li>
    <a href="sqlfireweb/tables" title="View Tables">
      <img class="icon" src="./themes/original/img/s_tbl.png" width="16" height="16" alt="View Tables" />
      Tables (${sessionScope.schemaMap['Table']})
    </a>
   </li>
   <li>
    <a href="sqlfireweb/indexes" title="View Indexes">
      <img class="icon" src="./themes/original/img/b_index.png" width="16" height="16" alt="View Indexes" />
      Indexes (${sessionScope.schemaMap['Index']})
    </a>
   </li>
   <li>
    <a href="sqlfireweb/views" title="View Views">
      <img class="icon" src="./themes/original/img/b_views.png" width="16" height="16" alt="View Views" />
      Views (${sessionScope.schemaMap['View']})
    </a>
   </li>
   <li>
    <a href="sqlfireweb/constraints" title="View Constraints">
      <img class="icon" src="./themes/original/img/constraints.png" width="16" height="16" alt="View Constraints" />
      Constraints (${sessionScope.schemaMap['Constraint']})
    </a>
   </li>
   <li>
    <a href="sqlfireweb/triggers" title="View Triggers">
      <img class="icon" src="./themes/original/img/b_trigger.png" width="16" height="16" alt="View Triggers" />
      Triggers (${sessionScope.schemaMap['Trigger']})
    </a>
   </li>
   <li>
    <a href="sqlfireweb/procs?procType=P" title="View Stored Procedures">
      <img class="icon" src="./themes/original/img/b_sqldoc.png" width="16" height="16" alt="View Stored Procedures" />
      Procedures (${sessionScope.schemaMap['Procedure']})
    </a>
   </li>
   <li>
    <a href="sqlfireweb/procs?procType=F" title="View Stored Functions">
      <img class="icon" src="./themes/original/img/b_sqldoc.png" width="16" height="16" alt="View Stored Functions" />
      Functions (${sessionScope.schemaMap['Function']})
    </a>
   </li>
   <li>
    <a href="sqlfireweb/types" title="View User Defined Types">
      <img class="icon" src="./themes/original/img/b_types.png" width="16" height="16" alt="View User Defined Types" />
      Types (${sessionScope.schemaMap['Type']})
    </a>
   </li>
   <li>
    <a href="sqlfireweb/diskstores" title="View Diskstores">
      <img class="icon" src="./themes/original/img/b_disks.png" width="16" height="16" alt="View Diskstores" />
      Disk Stores (${sessionScope.schemaMap['Diskstore']})
    </a>
   </li>
   <li>
    <a href="sqlfireweb/asyncevent" title="View Async Event Listeners">
      <img class="icon" src="./themes/original/img/b_async.png" width="16" height="16" alt="View Async Event Listeners" />
      Async Event Listeners (${sessionScope.schemaMap['AsyncEventList']})
    </a>
   </li>
   <li>
    <a href="sqlfireweb/gatewaysenders" title="View Gateway Senders">
      <img class="icon" src="./themes/original/img/b_sdb.png" width="16" height="16" alt="View Gateway Sender" />
      Gateway Senders (${sessionScope.schemaMap['Sender']})
    </a>
   </li>
    <li>
    <a href="sqlfireweb/gatewayreceivers" title="View Gateway Reciever">
      <img class="icon" src="./themes/original/img/b_sdb.png" width="16" height="16" alt="View Gateway Reciever" />
      Gateway Receivers (${sessionScope.schemaMap['Receiver']})
    </a>
   </li>
   <li>
    <a href="sqlfireweb/displayqueryreports" title="View Reports">
      <img class="icon" src="./themes/original/img/b_props.png" width="16" height="16" alt="View Reports" />
      Reports
    </a>
   </li>
   <li>
   	<a href="sqlfireweb/logout" target="_top" title="Logoff">
	  <img class="icon" src="./themes/original/img/s_loggoff.png" width="16" height="16" alt="Logoff" />
	  Disconnect
	</a> 
   </li>   
 </ul>
</div>

<p />
<a href="sqlfireweb/refresh" title="Refresh List" target="_top">
  <img class="icon" src="./themes/original/img/Refresh List.png"  alt="Refresh List" />
</a>

<br />

<div id="selflink" class="print_ignore">
  <font size="-2">
  Ver 1.1 - &copy; 2013. All Rights Reserved <br />
  <a href="mailto:papicella@vmware.com">Pas Apicella</a>
  </font>
</div>

<div id="pmalogo">
  <img src="./themes/original/img/SQLFireWeb1.png" />
</div>

</body>
</html>