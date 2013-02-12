<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
input:focus, textarea:focus {
background-color: #D0DCE0;
} 
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SQLFire*Web - Login Page</title>
<link rel="stylesheet" type="text/css" href="../css/isqlfire.css" />
<link rel="stylesheet" type="text/css" href="../css/print.css" media="print" />
</head>
<body>

<h2>SQLFire*Web Login Page</h2>

<div class="notice">
Supports SQLFire 1.0.x
</div>

<c:if test="${!empty error}">
  <br />
  <div class="error">
	Unable to login into SQLFire cluster due to the following error <p /> ${error}
  </div>
  <p />
</c:if>

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