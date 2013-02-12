<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SQLFire*Web - Current JDBC Connections</title>
<link rel="stylesheet" type="text/css" href="../css/isqlfire.css" />
<link rel="stylesheet" type="text/css" href="../css/print.css" media="print" />
</head>
<body>
<h2>Current SQLFire*Web JDBC Connections</h2>

<jsp:include page="toolbar.jsp" flush="true" />

<div class="notice">
Total of ${conmapsize} SQLFire*Web JDBC Connections
</div>
<br />

<table id="table_results" class="data">
 <thead>
   <tr>
    <th>No</th>
    <th>Key</th>
    <th>JDBC URL</th>
    <th>Schema</th>
    <th>Connected At</th>
   </tr>
 </thead>
 <tbody>
    <c:set var="i" value="0" />
	<c:forEach var="entry" varStatus="loop" items="${conmap}">
  	  <tr class="${((loop.index % 2) == 0) ? 'even' : 'odd'}">
  	   <c:set var="i" value="${i + 1}" />
  	   <td>${i}</td>
  	   <td>
  	     <img src="../themes/original/img/key.png" width="16" height="16" alt="${entry.key}" title="${entry.key}"/>
  	   </td>
  	   <td>${entry.value.url}</td>
  	   <td>${entry.value.schema}</td>
  	   <td>${entry.value.connectedAt}</td>
	</c:forEach>
 </tbody>
</table>

<br />

<jsp:include page="footer.jsp" flush="true" />

</body>
</html>