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
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
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
<title><fmt:message key="sqlfireweb.appname" /> History</title>
</head>
<body>

<h2><fmt:message key="sqlfireweb.appname" /> History</h2>

<jsp:include page="toolbar.jsp" flush="true" />

<div class="notice">
Found ${historysize} command(s).
</div>
<br />

<c:if test="${historyremoved != null}">
  <div class="success">
    ${historyremoved}
  </div>
</c:if>

<br />
<a href="history?histAction=CLEAR">Clear History</a>
<p />

<table id="table_results" class="data">
 <thead>
   <tr>
    <th>#</th>
    <th>SQL</th>
    <th>Run</th>
   </tr>
 </thead>
 <tbody>
    <c:set var="i" value="0" />
	<c:forEach var="entry" varStatus="loop" items="${historyList}">
	  <c:set var="i" value="${i + 1}" />
  	  <tr class="${((loop.index % 2) == 0) ? 'even' : 'odd'}">
		<td>${i}</td>
		<td align="left">${entry}</td>
		<td>
		   <a href="executequery?query=${entry}">
             <img class="icon" width="16" height="16" src="../themes/original/img/b_sql.png" alt="Run" title="Run" />
           </a>
		</td>
	  </tr>
	</c:forEach>
 </tbody>
</table>
<br />

<br />

<jsp:include page="footer.jsp" flush="true" />

</body>
</html>