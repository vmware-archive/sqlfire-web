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

  <!--
  function isNumberKey(evt)
  {
     var charCode = (evt.which) ? evt.which : event.keyCode
     if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;

     return true;
  }
  //-->
  
  // ]]>
</script>
<script src="../js/functions.js" type="text/javascript"></script>
<title><fmt:message key="sqlfireweb.appname" /> Preferences</title>
</head>
<body>

<h2><fmt:message key="sqlfireweb.appname" /> Preferences</h2>

<jsp:include page="toolbar.jsp" flush="true" />

<div class="notice">
Specify at runtime the preferences you wish to use across display pages within SQLFire*Web
</div>

<c:if test="${saved != null}">
  <br />
  <div class="success">
    ${saved}
  </div>
</c:if>

<form method="post" action="preferences">
<fieldset>
 <legend>User Preferences Form</legend>
 <table class="formlayout">
  <tr>
   <td align="right">Records To Display for Schema Objects:</td>
   <td><input type="text" name="recordsToDisplay" size="10" value="${userPref.recordsToDisplay}" onkeypress="return isNumberKey(event)"/></td>
  </tr>
  <tr>
   <td align="right">Records To Display in SQL Worksheet:</td>
   <td><input type="text" name="maxRecordsinSQLQueryWindow" size="10" value="${userPref.maxRecordsinSQLQueryWindow}" onkeypress="return isNumberKey(event)" /></td>
  </tr>
  <tr>
   <td align="right">Connection Auto Commit:</td>
   <td>
     <select name="autoCommit">
      <c:choose>
       <c:when test="${userPref.autoCommit == 'Y'}">
         <option value="Y" selected="selected">Y</option>
         <option value="N">N</option>
       </c:when>
       <c:otherwise>
         <option value="N" selected="selected">N</option>
         <option value="Y">Y</option>       
       </c:otherwise>
      </c:choose>
     </select>    
   </td>
  </tr>
 </table>
</fieldset>
<fieldset class="tblFooters">
  <input type="submit" value="Save Preferences" name="pSubmit" />
  <input type="reset" value="Reset" />
</fieldset>
</form>
<br />

<jsp:include page="footer.jsp" flush="true" />

</body>
</html>