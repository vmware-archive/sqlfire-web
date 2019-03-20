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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
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