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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/isqlfire.css" />
<link rel="stylesheet" type="text/css" href="../css/print.css" media="print" />
<script src="../js/functions.js" type="text/javascript"></script>
<SCRIPT language=Javascript>
   <!--
   function isNumberKey(evt)
   {
      var charCode = (evt.which) ? evt.which : event.keyCode
      if (charCode > 31 && (charCode < 48 || charCode > 57))
         return false;

      return true;
   }
   //-->
</SCRIPT>
<title><fmt:message key="sqlfireweb.appname" /> Create Index</title>
</head>
<body>
<h2><fmt:message key="sqlfireweb.appname" /> Create Index</h2>

<jsp:include page="toolbar.jsp" flush="true" />

<c:choose>
 <c:when test="${not empty columns}">
	<div class="notice">
		Creating index for table ${tabName}
	</div> 
	<br />
		
	<form action="createindex" method="POST" name="tablesForm" id="tablesForm">
	Index Name: 
	<input type="TEXT" name="idxName" value="newindex" size="20" maxlength="30" />
	&nbsp;
	Schema:  
	<input type="TEXT" name="schemaName" value="${tableSchemaName}" readonly="readonly" />
	&nbsp;
	Table:  
	<input type="TEXT" name="tabName" value="${tabName}" readonly="readonly" />
	&nbsp;  
	Unique:
	<select name="unique">
	  <option value="N" selected="selected">N</option>
	  <option value="Y">Y</option>
	</select>
	<p />
	
	<table id="table_columns" class="data">
	<thead>
	  <tr>
	   <th></th>
	   <th>Column</th>
	   <th>Order</th>
	   <th>Position</th>
	  </tr>
	</thead>
	<tbody>
	<c:forEach var="entry" varStatus="loop" items="${columns}">
	 <tr class="${((loop.index % 2) == 0) ? 'even' : 'odd'}">
		 <td align="center">
		     <input type="checkbox" 
		            name="selected_column[]"
		            value="${entry['columnName']}_${loop.index + 1}"
		            id="checkbox_tbl_${loop.index + 1}" />
		  </td>
		  <td align="center">${entry.columnName}</td>
		  <td>
		  	<select name="idxOrder[]">
	  			<option value="ASC" selected="selected">ASC</option>
	  			<option value="DESC">DESC</option>
			</select>
		  </td>
		  <td align="center">
		  	<input type="TEXT" name="position[]" size="4" maxlength="100" onkeypress="return isNumberKey(event);" />
		  </td>
	  </tr>
	</c:forEach>
	</tbody>
	</table>
	<br />
	<input type="submit" value="Create" name="pSubmit" />
	
	</form> 
 </c:when>
 <c:otherwise>
	<c:if test="${!empty result}">
	<fieldset>
	 <legend>Result</legend>
	 <table class="formlayout">
	  <tr>
	   <td align="right">Command:</td>
	   <td>${result.command} </td>
	  </tr>
	  <tr>
	   <td align="right">Message:</td>
	   <td> 
	    <font color="${result.message == 'SUCCESS' ? 'green' : 'red'}">
	      ${result.message}
	    </font>
	   </td>
	  </tr>
	 </table>
	</fieldset>
	<br />
	</c:if> 
 </c:otherwise>
</c:choose>


<jsp:include page="footer.jsp" flush="true" />

</body>
</html>