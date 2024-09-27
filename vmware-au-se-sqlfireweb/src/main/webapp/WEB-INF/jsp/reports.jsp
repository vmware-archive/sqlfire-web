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
<title><fmt:message key="sqlfireweb.appname" />eb Reports</title>
</head>
<body>

<h2><fmt:message key="sqlfireweb.appname" /> Reports</h2>

<jsp:include page="toolbar.jsp" flush="true" />

<div class="notice">
Click on query icon to execute a query
</div>

<br />

<c:set var="i" value="1" />

<!-- Display header of query categories -->
<c:forEach var="row" items="${headerNames}">
  <c:if test="${i != 1}">
    &nbsp;|&nbsp;
  </c:if>
  <a href="#${i}">${row}</a>
  <c:set var="i" value="${i + 1}" />
</c:forEach>

<p />
<c:set var="i" value="1" />

<c:if test="${!empty queries}">
    <c:forEach var="entry" items="${queries}">
     <c:set var="map" value="${entry}" />
     <a name="${i}"></a>
     <table id="table_results" class="data">
       <thead>
        <tr>
         <th> Action </th>
         <th> Description </th>
        </tr>
       </thead>
       <tbody>
         <c:forEach var="item" items="${map}">
           <tr class="odd">
            <td align="center">
              <a href="executequeryreport?beanId=${item.key}" title="Execute Query">
               <img 
                 class="icon" 
                 src="../themes/original/img/b_sql.png" 
                 width="16" 
                 height="16" 
                 alt="Run Report" />
              </a> 
            </td>
            <td>${item.value}</td>
           </tr>
         </c:forEach>
      </tbody>
    </table>
    <br />
    <c:set var="i" value="${i + 1}" />
    </c:forEach>
  <p />
</c:if>

<br />
<jsp:include page="footer.jsp" flush="true" />

</body>
</html>