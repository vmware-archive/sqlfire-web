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
<link rel="stylesheet" type="text/css" href="../css/isqlfire.css" />
<link rel="stylesheet" type="text/css" href="../css/print.css" media="print" />
<script type="text/javascript">
  // <![CDATA[
  
  // js form validation stuff
  var confirmMsg  = 'Do you really want to ';
  // ]]>
</script>
<script src="../js/functions.js" type="text/javascript"></script>
<title><fmt:message key="sqlfireweb.appname" /> - Members</title>
</head>
<body>

<h2><fmt:message key="sqlfireweb.appname" /> Members</h2>

<jsp:include page="toolbar.jsp" flush="true" />

<div class="notice">
Found ${records} member(s).
</div>
<br />

<c:if test="${!empty allMemberInfoResult}">
 <h3>${memberid} Member information</h3>
 <table id="table_results" class="data">
  <thead>
    <tr>
      <th>Column Name</th>
      <th>Value</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="row" varStatus="loop" items="${allMemberInfoResult.rows}">
        <c:forEach var="columnName" items="${allMemberInfoResult.columnNames}">
         <tr class="${((loop.index % 2) == 0) ? 'even' : 'odd'}">
          <td align="right">${columnName}</td>
          <td align="left"><c:out value="${row[columnName]}"/></td>
         </tr>
        </c:forEach>           
       </tr>
    </c:forEach>  
  </tbody>
 </table>
 <br />
</c:if>

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
    <th>Action</th>
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
	  	   <td align="center">
    		<a href="members?memberId=${entry['id']}&memberAction=ALLMEMBEREVENTINFO">
             <img class="icon" width="16" height="16" src="../themes/original/img/b_tblexport.png" alt="View Member Info" title="View Member Info" />
            </a>&nbsp;   	  	   
	  	   </td>
  	   </tr>
	</c:forEach>
 </tbody>
</table>

<br />

<jsp:include page="footer.jsp" flush="true" />

</body>
</html>