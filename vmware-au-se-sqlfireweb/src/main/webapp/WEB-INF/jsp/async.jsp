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
<title>SQLFire*Web Async Event Listeners</title>
</head>
<body>

<h2>SQLFire*Web Async Event Listeners</h2>

<jsp:include page="toolbar.jsp" flush="true" />

<div class="notice">
Found ${records} async event listener(s).
</div>

<c:if test="${!empty allAsyncEventInfoResult}">
 <h3>${asyncevent} Async Event information</h3>
 <table id="table_results" class="data">
  <thead>
    <tr>
      <th>Column Name</th>
      <th>Value</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="row" varStatus="loop" items="${allAsyncEventInfoResult.rows}">
        <c:forEach var="columnName" items="${allAsyncEventInfoResult.columnNames}">
         <tr class="${((loop.index % 2) == 0) ? 'even' : 'odd'}">
          <td align="right">${columnName}</td>
          <td align="left"><c:out value="${row[columnName]}"/></td>
         </tr>
        </c:forEach>           
       </tr>
    </c:forEach>  
  </tbody>
 </table>
 <br />
</c:if>

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

<form action="asyncevent" method="POST">
   <b>Filter Async Event Name </b>
   <input type="TEXT" name="search" value="${search}" />
   <input type="image" src="../themes/original/img/Search.png" />
</form>

<!-- Display previous/next set links -->
<c:if test="${estimatedrecords > sessionScope.prefs.recordsToDisplay}"> &nbsp; | &nbsp;
  <c:if test="${startAtIndex != 0}">
    <a href="asyncevent?search=${param.search}&selectedSchema=${chosenSchema}&startAtIndex=${(startAtIndex - sessionScope.prefs.recordsToDisplay)}&endAtIndex=${startAtIndex}">
      <img src="../themes/original/img/Previous.png" border="0" />
    </a>
    &nbsp;
  </c:if>
  <c:if test="${estimatedrecords != endAtIndex}">
    <a href="asyncevent?search=${param.search}&selectedSchema=${chosenSchema}&startAtIndex=${endAtIndex}&endAtIndex=${endAtIndex + sessionScope.prefs.recordsToDisplay}">
      <img src="../themes/original/img/Next.png" border="0" />
    </a>
  </c:if>
  &nbsp; <font color="Purple">Current Set [${startAtIndex + 1} - ${endAtIndex}] </font>
</c:if>

<p />

<form method="post" action="asyncevent" name="tablesForm" id="tablesForm">
<table id="table_results" class="data">
 <thead>
   <tr>
    <th></th>
    <th>Name</th>
    <th>Listener Class</th>
    <th>Server Group</th>
    <th>Is Started?</th>
    <th>Action</th>
   </tr>
 </thead>
 <tbody>
	<c:forEach var="entry" varStatus="loop" items="${asyncevents}">
  	  <tr class="${((loop.index % 2) == 0) ? 'even' : 'odd'}">
		  <td align="center">
		      <input type="checkbox" 
		             name="selected_async[]"
		             value="${entry['name']}"
		             id="checkbox_async_${loop.index + 1}" />
		   </td>
	  	   <td align="center">${entry.name}</td>
	  	   <td align="center">${entry.listenerClass}</td>
	  	   <td align="center">${entry.serverGroup}</td>
	  	   <td align="center">
	  	     <c:choose>
	  	       <c:when test="${entry.isStarted == '1'}">
	  	         <font color="green">YES</font>
	  	       </c:when>
	  	       <c:otherwise>
	  	         <font color="red">NO</font>
	  	       </c:otherwise>
	  	     </c:choose>
	  	   </td>
	  	   <td align="center">
    		<a href="asyncevent?asyncName=${entry['name']}&asyncAction=DROP" onclick="return confirmLink(this, 'DROP ASYNC EVENT LISTENER ${entry['name']}?')">
             <img class="icon" width="16" height="16" src="../themes/original/img/b_drop.png" alt="Drop Async Event Listener" title="Drop Async Event Listener" />
            </a>&nbsp;  
    		<a href="asyncevent?asyncName=${entry['name']}&asyncAction=START" onclick="return confirmLink(this, 'START ASYNC EVENT LISTENER ${entry['name']}?')">
             <img class="icon" width="16" height="16" src="../themes/original/img/s_success.png" alt="Start Async Event Listener" title="Start Async Event Listener" />
            </a>&nbsp;   
    		<a href="asyncevent?asyncName=${entry['name']}&asyncAction=STOP" onclick="return confirmLink(this, 'STOP ASYNC EVENT LISTENER ${entry['name']}?')">
             <img class="icon" width="16" height="16" src="../themes/original/img/s_error.png" alt="Stop Async Event Listener" title="Stop Async Event Listener" />
            </a>&nbsp; 
    		<a href="asyncevent?asyncName=${entry['name']}&asyncAction=ALLASYNCEVENTINFO">
             <img class="icon" width="16" height="16" src="../themes/original/img/b_tblexport.png" alt="View Async Event Listener Info" title="View Async Event Listener Info" />
            </a>&nbsp;        
	  	   </td>
  	   </tr>
	</c:forEach>
 </tbody>
</table>

<div class="clearfloat">
<img class="selectallarrow" src="../themes/original/img/arrow_ltr.png"
    width="38" height="22" alt="With selected:" />
<a href="asyncevent"
    onclick="if (setCheckboxes('table_results', 'true')) return false;">
    Check All</a>

/
<a href="asyncevent"
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