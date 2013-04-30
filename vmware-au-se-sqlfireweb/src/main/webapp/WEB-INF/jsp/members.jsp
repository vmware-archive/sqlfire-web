<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/isqlfire.css" />
<link rel="stylesheet" type="text/css" href="../css/print.css" media="print" />
<script type="text/javascript">
  // <![CDATA[
  
  // js form validation stuff
  var confirmMsg  = 'Do you really want to ';
  // ]]>
</script>
<script src="../js/functions.js" type="text/javascript"></script>
<title>SQLFire*Web - Members</title>
</head>
<body>

<h2>SQLFire*Web Members</h2>

<jsp:include page="toolbar.jsp" flush="true" />

<div class="notice">
Found ${records} member(s).
</div>
<br />

<table id="table_results" class="data">
 <thead>
   <tr>
    <th>Id</th>
    <th>Status</th>
    <th>Hostdata</th>
    <th>ISelder</th>
    <th>Host</th>
    <th>Pid</th>
    <th>Port</th>
    <th>Locator</th>
    <th>Server Groups</th>
   </tr>
 </thead>
 <tbody>
	<c:forEach var="entry" varStatus="loop" items="${members}">
  	  <tr class="${((loop.index % 2) == 0) ? 'even' : 'odd'}">
		   <td align="center">${entry.id}</td>
	  	   <td align="center">
	  	     <c:choose>
	  	     	<c:when test="${entry.status == 'RUNNING'}">
	  	     		<font color="green">
	  	     		  ${entry.status}
	  	     		</font>
	  	     	</c:when>
	  	     	<c:otherwise>
	  	     		<font color="red">
	  	     		  ${entry.status}
	  	     		</font>
	  	     	</c:otherwise>
	  	     </c:choose>	  	   
	  	   </td>
	  	   <td align="center">
	  	     <c:choose>
	  	     	<c:when test="${entry.hostdata == '1'}">
	  	     		YES
	  	     	</c:when>
	  	     	<c:otherwise>
	  	     		NO
	  	     	</c:otherwise>
	  	     </c:choose>
	  	   </td>
	  	   <td align="center">${entry.iselder}</td>
	  	   <td align="center">${entry.host}</td>
	  	   <td align="center">${entry.pid}</td>
	  	   <td align="center">${entry.port}</td>
	  	   <td align="center">${entry.locator}</td>
	  	   <td align="center">${entry.serverGroups}</td>
  	   </tr>
	</c:forEach>
 </tbody>
</table>

<br />

<jsp:include page="footer.jsp" flush="true" />

</body>
</html>