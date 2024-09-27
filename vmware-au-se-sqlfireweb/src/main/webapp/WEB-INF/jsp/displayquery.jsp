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
<title><fmt:message key="sqlfireweb.appname" /> Query Result</title>
</head>
<body>
<h2>${queryDescription}</h2>

<jsp:include page="toolbar.jsp" flush="true" />

<c:if test="${!empty paramMap}">
 <c:if test="${empty queryResults}">
  <div class="warning">
    Parameters must be set prior to executing this query
  </div>
 </c:if>
<form method="get" action="executequeryreport">
<input type="hidden" name="beanId" value="${param.beanId}" />
<fieldset>
 <legend>Query Parameter Form</legend>
 <table class="formlayout">
  <c:forEach var="item" items="${paramMap}">
    <tr>
     <td align="right">${item.value}</td>
     <td>
       <input type="text" name="${item.key}" />
     </td>
    </tr>
  </c:forEach>
 </table>
 </fieldset>
  <fieldset class="tblFooters">
    <input type="submit" value="Execute" name="pSubmit" />
    <input type="reset" value="Reset" />
  </fieldset>
 </form>
</c:if>

<c:choose>
  <c:when test="${!empty queryResults}">
   <p />
    <div class="success">
      Query completed successfully
    </div> 
   <br />
   <table id="table_results" class="data">
    <thead>
      <tr>
       <c:forEach var="columnName" items="${queryResults.columnNames}">
        <th><c:out value="${columnName}"/></th>
      </c:forEach>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="row" varStatus="loop" items="${queryResults.rows}">
        <tr class="${((loop.index % 2) == 0) ? 'even' : 'odd'}">
          <c:forEach var="columnName" items="${queryResults.columnNames}">
            <td><c:out value="${row[columnName]}"/></td>
          </c:forEach>           
         </tr>
      </c:forEach>  
    </tbody>
   </table>
  </c:when>
</c:choose>

<br />
<jsp:include page="footer.jsp" flush="true" />

</body>
</html>