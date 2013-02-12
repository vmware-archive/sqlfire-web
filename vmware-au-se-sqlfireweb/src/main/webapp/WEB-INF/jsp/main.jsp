<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>SQLFire*Web</title>
  <link rel="stylesheet" type="text/css" href="css/isqlfire.css" />
  <link rel="stylesheet" type="text/css" href="css/print.css" media="print" />
</head>
<frameset cols="200,*" rows="*" id="mainFrameset">
  <frame 
    frameborder="0" 
    id="frame_navigation"
    src="<%=request.getContextPath()%>/navigation.jsp"
    name="frame_navigation" />
  <frame 
    frameborder="0" 
    id="frame_content"
    src="<%=request.getContextPath()%>/welcome.jsp"
    name="frame_content" />
  <noframes>
    <body>
        <p>SQLFire*Web is more friendly with a <b>frames-capable</b> browser.</p>
    </body>
  </noframes>
</frameset>
</html>