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
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
input:focus, textarea:focus {
background-color: #D0DCE0;
} 
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="sqlfireweb.appname" /> - Login Page</title>
<link rel="stylesheet" type="text/css" href="../css/isqlfire.css" />
<link rel="stylesheet" type="text/css" href="../css/print.css" media="print" />
</head>
<body>

<h2><fmt:message key="sqlfireweb.appname" /> Login Page</h2>

<div class="notice">
Supports SQLFire 1.1.x
</div>

<c:if test="${!empty error}">
  <br />
  <div class="error">
	Unable to login into SQLFire cluster due to the following error <p /> ${error}
  </div>
</c:if>

<font color="darkblue">
	<p>
	 Please ensure you provide a login schema in order to set the correct schema. Leaving this
	 out will default to the "APP" schema.
	</p>
</font>

<form:form method="post" action="login" modelAttribute="loginAttribute" target="_top">
<fieldset>
 <legend>Simple Login Form</legend>
 <table class="formlayout">
  <tr>
   <td align="right">Username:</td>
   <td><form:input type="text" path="username" maxlength="30" size="60" value=""/></td>
  </tr>
  <tr>
   <td align="right">Password:</td>
   <td><form:input type="password" path="password" maxlength="30" size="60" value=""/></td>
  </tr>
  <tr>
   <td align="right">Url:</td>
   <td><form:input type="text" path="url" maxlength="250" size="60" value="jdbc:sqlfire://localhost:1527/" /></td>
  </tr>
 </table>
</fieldset>
<fieldset class="tblFooters">
  <input type="submit" value="Login" />
</fieldset>
</form:form>

<jsp:include page="footer.jsp" flush="true" />

</body>
</html>