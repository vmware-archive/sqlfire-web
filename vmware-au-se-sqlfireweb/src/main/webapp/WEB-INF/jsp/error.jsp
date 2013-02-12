<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/isqlfire.css" />
<link rel="stylesheet" type="text/css" href="../css/print.css" media="print" />
<title>SQLFire*Web Error Page</title>
</head>
<body>
<h2>SQLFire*Web Error Page</h2>

<div class="error">
A Fatal Runtime Exception Has Occurred : ${exception.message}
</div>

<br />
<b>Error - ${exception.message} </b><br />
<c:forEach items="${exception.stackTrace}" var="x">
    ${x}
    <br />
</c:forEach>

<br />

<jsp:include page="footer.jsp" flush="true" />

</body>
</html>