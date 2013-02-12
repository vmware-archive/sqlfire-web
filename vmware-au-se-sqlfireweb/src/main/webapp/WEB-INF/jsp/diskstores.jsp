<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
  // ]]>
</script>
<script src="../js/functions.js" type="text/javascript"></script>
<title>SQLFire*Web - Diskstores</title>
</head>
<body>

<h2>SQLFire*Web Diskstores</h2>

<jsp:include page="toolbar.jsp" flush="true" />

<div class="notice">
Found ${records} diskstore(s).
</div>

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

<c:if test="${!empty arrayresult}">
<fieldset>
 <legend>Multi Submit Results</legend>
 <table class="formlayout">
  <c:forEach var="result" items="${arrayresult}">
    <tr>
     <td align="right">Command:</td>
     <td> ${result.command} </td>
    </tr>
    <tr>
     <td align="right">Message:</td>
     <td> 
      <font color="${result.message == 'SUCCESS' ? 'green' : 'red'}">
        ${result.message}
      </font>
     </td>
    </tr>
  </c:forEach>
 </table>
</fieldset>
<br />
</c:if>

<form action="diskstores" method="POST">
   <b>Filter Disk Store Name: </b>
   <input type="TEXT" name="search" value="${search}" />
   <input type="image" src="../themes/original/img/Search.png" />
</form>

<!-- Display previous/next set links -->
<c:if test="${estimatedrecords > sessionScope.prefs.recordsToDisplay}"> &nbsp; | &nbsp;
  <c:if test="${startAtIndex != 0}">
    <a href="diskstores?search=${param.search}&startAtIndex=${(startAtIndex - sessionScope.prefs.recordsToDisplay)}&endAtIndex=${startAtIndex}">
      <img src="../themes/original/img/Previous.png" border="0" />
    </a>
    &nbsp;
  </c:if>
  <c:if test="${estimatedrecords != endAtIndex}">
    <a href="diskstores?search=${param.search}&startAtIndex=${endAtIndex}&endAtIndex=${endAtIndex + sessionScope.prefs.recordsToDisplay}">
      <img src="../themes/original/img/Next.png" border="0" />
    </a>
  </c:if>
  &nbsp; <font color="Purple">Current Set [${startAtIndex + 1} - ${endAtIndex}] </font>
</c:if>

<p />

<form method="post" action="diskstores" name="tablesForm" id="tablesForm">
<table id="table_results" class="data">
 <thead>
   <tr>
    <th></th>
    <th>Name</th>
    <th>Directory</th>
    <th>Action</th>
   </tr>
 </thead>
 <tbody>
	<c:forEach var="entry" varStatus="loop" items="${diskstores}">
  	  <tr class="${((loop.index % 2) == 0) ? 'even' : 'odd'}">
		  <td align="center">
		      <input type="checkbox" 
		             name="selected_ds[]"
		             value="${entry['name']}"
		             id="checkbox_ds_${loop.index + 1}" />
		   </td>
		   <td align="center">${entry.name}</td>
	  	   <td align="center">${entry.directory}</td>
	  	   <td align="left">
	  	    <c:choose>
	    		<c:when test="${entry['name'] == 'SQLF-DD-DISKSTORE'}"></c:when>
	    		<c:when test="${entry['name'] == 'SQLF-DEFAULT-DISKSTORE'}"></c:when>
	    		<c:otherwise>
			        <a href="diskstores?dsName=${entry['name']}&dsAction=DROP" onclick="return confirmLink(this, 'DROP DISKSTORE ${entry['name']}?')">
		             <img class="icon" width="16" height="16" src="../themes/original/img/b_drop.png" alt="Drop Disk Store" title="Drop Disk Store" />
		            </a>   
		            &nbsp;
	    		</c:otherwise>	  	    
	  	    </c:choose>          
	  	   </td>
  	   </tr>
	</c:forEach>
 </tbody>
</table>

<div class="clearfloat">
<img class="selectallarrow" src="../themes/original/img/arrow_ltr.png"
    width="38" height="22" alt="With selected:" />
<a href="diskstores"
    onclick="if (setCheckboxes('table_results', 'true')) return false;">
    Check All</a>

/
<a href="diskstores"
    onclick="if (unMarkAllRows('tablesForm')) return false;">
    Uncheck All</a>

<select name="submit_mult" onchange="this.form.submit();" style="margin: 0 3em 0 3em;">
    <option value="With selected:" selected="selected">With selected:</option>
    <option value="Drop" >Drop</option>
</select>


<script type="text/javascript">
<!--
// Fake js to allow the use of the <noscript> tag
//-->
</script>
<noscript>
    <input type="submit" value="Go" />
</noscript>
</div>

</form>

<br />

<jsp:include page="footer.jsp" flush="true" />

</body>
</html>