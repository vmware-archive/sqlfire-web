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
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
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
<title><fmt:message key="sqlfireweb.appname" /> Tables</title>
</head>
<body>

<h2><fmt:message key="sqlfireweb.appname" /> Tables</h2>

<jsp:include page="toolbar.jsp" flush="true" />

<div class="notice">
Found ${records} table(s). &nbsp; &nbsp;
<a href="createtable">
 <img class="icon" width="16" height="16" src="../themes/original/img/b_newtbl.png" alt="Add Table" title="Add Table" />
 Create Table
</a>
</div>

<c:if test="${!empty dataLocationResults}">
 <h3>Data locations for ${tablename} table </h3>
 <table id="table_results" class="data">
  <thead>
    <tr>
     <c:forEach var="columnName" items="${dataLocationResults.columnNames}">
      <th><c:out value="${columnName}"/></th>
    </c:forEach>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="row" varStatus="loop" items="${dataLocationResults.rows}">
      <tr class="odd">
        <c:forEach var="columnName" items="${dataLocationResults.columnNames}">
          <td align="center"><c:out value="${row[columnName]}"/></td>
        </c:forEach>           
       </tr>
    </c:forEach>  
  </tbody>
 </table>
 <br />
</c:if>

<c:if test="${!empty expirationEvictionResult}">
 <h3>Expiration / Eviction Attributes for ${tablename} table </h3>
 <table id="table_results" class="data">
  <thead>
    <tr>
     <c:forEach var="columnName" items="${expirationEvictionResult.columnNames}">
      <th><c:out value="${columnName}"/></th>
    </c:forEach>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="row" varStatus="loop" items="${expirationEvictionResult.rows}">
      <tr class="odd">
        <c:forEach var="columnName" items="${expirationEvictionResult.columnNames}">
          <td align="center"><c:out value="${row[columnName]}"/></td>
        </c:forEach>           
       </tr>
    </c:forEach>  
  </tbody>
 </table>
 <br />
</c:if>

<c:if test="${!empty tableStructure}">
 <h3>${tablename} Structure </h3>
 <table id="table_results" class="data">
  <thead>
    <tr>
     <c:forEach var="columnName" items="${tableStructure.columnNames}">
      <th><c:out value="${columnName}"/></th>
    </c:forEach>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="row" varStatus="loop" items="${tableStructure.rows}">
      <tr class="odd">
        <c:forEach var="columnName" items="${tableStructure.columnNames}">
          <td align="center"><c:out value="${row[columnName]}"/></td>
        </c:forEach>           
       </tr>
    </c:forEach>  
  </tbody>
 </table>
 <br />
</c:if>

<c:if test="${!empty allTableInfoResult}">
 <h3>${tablename} table information</h3>
 <table id="table_results" class="data">
  <thead>
    <tr>
      <th>Column Name</th>
      <th>Value</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="row" varStatus="loop" items="${allTableInfoResult.rows}">
        <c:forEach var="columnName" items="${allTableInfoResult.columnNames}">
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

<c:if test="${!empty tableMemoryUsage}">
 <h3>Memory Usage for ${tablename} table </h3>
 <table id="table_results" class="data">
  <thead>
    <tr>
     <c:forEach var="columnName" items="${tableMemoryUsage.columnNames}">
      <th><c:out value="${columnName}"/></th>
    </c:forEach>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="row" varStatus="loop" items="${tableMemoryUsage.rows}">
      <tr class="odd">
        <c:forEach var="columnName" items="${tableMemoryUsage.columnNames}">
          <td align="center"><c:out value="${row[columnName]}"/></td>
        </c:forEach>           
       </tr>
    </c:forEach>  
  </tbody>
 </table>
 <br />
</c:if>

<c:if test="${!empty parAttrResult}">
 <h3>Partition Attributes for ${tablename} table </h3>
 <table id="table_results" class="data">
   <tbody>
     <tr class="odd">
      <td>${parAttrResult}</td>
     </tr>
   </tbody>
 </table>
 <br />
</c:if>

<c:if test="${!empty loadscriptsql}">
 <h3>Load script for ${tablename} table </h3>
 <table id="table_results" class="data">
   <tbody>
     <tr class="odd">
      <td><pre>${loadscriptsql}</pre></td>
     </tr>
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

<form action="tables" method="POST">
   <b>Filter Table Name: </b>
   <input type="TEXT" name="search" value="${search}" />
   <b>Schema : </b>
   <select name="selectedSchema">
	   <c:forEach var="row" items="${schemas}">
	   	<c:choose>
	   		<c:when test="${row == chosenSchema}">
	   		  <option value="${row}" selected="selected">${row}</option>
	   		</c:when>
	   		<c:otherwise>
	   		  <option value="${row}">${row}</option>
	   		</c:otherwise>
	   	</c:choose>
	   </c:forEach>
   </select>
   <input type="image" src="../themes/original/img/Search.png" />
</form>

<!-- Display previous/next set links -->
<c:if test="${estimatedrecords > sessionScope.prefs.recordsToDisplay}"> &nbsp; | &nbsp;
  <c:if test="${startAtIndex != 0}">
    <a href="tables?search=${param.search}&selectedSchema=${chosenSchema}&startAtIndex=${(startAtIndex - sessionScope.prefs.recordsToDisplay)}&endAtIndex=${startAtIndex}">
      <img src="../themes/original/img/Previous.png" border="0" />
    </a>
    &nbsp;
  </c:if>
  <c:if test="${estimatedrecords != endAtIndex}">
    <a href="tables?search=${param.search}&selectedSchema=${chosenSchema}&startAtIndex=${endAtIndex}&endAtIndex=${endAtIndex + sessionScope.prefs.recordsToDisplay}">
      <img src="../themes/original/img/Next.png" border="0" />
    </a>
  </c:if>
  &nbsp; <font color="Purple">Current Set [${startAtIndex + 1} - ${endAtIndex}] </font>
</c:if>

<p />

<form method="post" action="tables" name="tablesForm" id="tablesForm">
<input type="hidden" name="selectedSchema" value="${chosenSchema}" />
<table id="table_results" class="data">
 <thead>
   <tr>
    <th></th>
    <th>Schema</th>
    <th>Name</th>
    <th>Data Policy</th>
    <th>Server Groups</th>
    <th>Loader</th>
    <th>Writer</th>
    <th>Action</th>
   </tr>
 </thead>
 <tbody>
	<c:forEach var="entry" varStatus="loop" items="${tables}">
  	  <tr class="${((loop.index % 2) == 0) ? 'even' : 'odd'}">
		  <td align="center">
		      <input type="checkbox" 
		             name="selected_tbl[]"
		             value="${entry['tableName']}"
		             id="checkbox_tbl_${loop.index + 1}" />
		   </td>
		   <td align="center">${entry.schemaName}</td>
	  	   <td align="center">${entry.tableName}</td>
	  	   <td align="center">${entry.dataPolicy}</td>
	  	   <td align="center">${entry.serverGroups}</td>
	  	   <td align="center">${entry.loader}</td>
	  	   <td align="center">${entry.writer}</td>
	  	   <td align="left">
    		<a href="executequery?query=select * from ${chosenSchema}.${entry['tableName']}">
             <img class="icon" width="16" height="16" src="../themes/original/img/b_views.png" alt="View Data" title="View Data" />
            </a>&nbsp;
    		<a href="tables?tabName=${entry['tableName']}&tabAction=DROP&selectedSchema=${chosenSchema}" onclick="return confirmLink(this, 'DROP TABLE ${entry['tableName']}?')">
             <img class="icon" width="16" height="16" src="../themes/original/img/b_drop.png" alt="Drop Table" title="Drop Table" />
            </a>&nbsp;  
    		<a href="tables?tabName=${entry['tableName']}&tabAction=EMPTY&selectedSchema=${chosenSchema}" onclick="return confirmLink(this, 'TRUNCATE TABLE ${entry['tableName']}?')">
             <img class="icon" width="16" height="16" src="../themes/original/img/b_empty.png" alt="Truncate Table" title="Truncate Table" />
            </a>&nbsp;  
    		<a href="tables?tabName=${entry['tableName']}&tabAction=STRUCTURE&selectedSchema=${chosenSchema}">
             <img class="icon" width="16" height="16" src="../themes/original/img/b_tbloptimize.png" alt="View Table Structure" title="View Table Structure" />
            </a>&nbsp;
    		<a href="tables?tabName=${entry['tableName']}&tabAction=MEMORYUSAGE&selectedSchema=${chosenSchema}">
             <img class="icon" width="16" height="16" src="../themes/original/img/b_tblimport.png" alt="View Memory Usage of table" title="View Memory Usage of table" />
            </a>&nbsp;  
    		<a href="tables?tabName=${entry['tableName']}&tabAction=DATALOCATIONS&selectedSchema=${chosenSchema}">
             <img class="icon" width="16" height="16" src="../themes/original/img/b_relations.png" alt="View Data Location Members" title="View Data Location Members" />
            </a>&nbsp;
    		<a href="tables?tabName=${entry['tableName']}&tabAction=EXPIRATION-EVICTION&selectedSchema=${chosenSchema}">
             <img class="icon" width="16" height="16" src="../themes/original/img/b_tblanalyse.png" alt="View Expiration / Eviction Attributes" title="View Expiration / Eviction Attributes" />
            </a>&nbsp;
    		<a href="tables?tabName=${entry['tableName']}&tabAction=ALLTABLEINFO&selectedSchema=${chosenSchema}">
             <img class="icon" width="16" height="16" src="../themes/original/img/b_tblexport.png" alt="View All Table Information" title="View All Table Information" />
            </a>&nbsp;
    		<a href="createindex?tabName=${entry['tableName']}&schemaName=${chosenSchema}">
             <img class="icon" width="16" height="16" src="../themes/original/img/add16.gif" alt="Create Index" title="Create Index" />
            </a>&nbsp;
            <c:if test="${fn:endsWith(entry.dataPolicy,'PARTITION')}">
	    		<a href="tables?tabName=${entry['tableName']}&tabAction=PARTITIONATTRS&selectedSchema=${chosenSchema}">
	             <img class="icon" width="16" height="16" src="../themes/original/img/s_vars.png" alt="View Partition Attributes" title="View Partition Attributes" />
	            </a>            
            </c:if>
                 
	  	   </td>
  	   </tr>
	</c:forEach>
 </tbody>
</table>

<div class="clearfloat">
<img class="selectallarrow" src="../themes/original/img/arrow_ltr.png"
    width="38" height="22" alt="With selected:" />
<a href="tables?selectedSchema=${chosenSchema}"
    onclick="if (setCheckboxes('table_results', 'true')) return false;">
    Check All</a>

/
<a href="tables?selectedSchema=${chosenSchema}"
    onclick="if (unMarkAllRows('tablesForm')) return false;">
    Uncheck All</a>

<select name="submit_mult" onchange="this.form.submit();" style="margin: 0 3em 0 3em;">
    <option value="With selected:" selected="selected">With selected:</option>
    <option value="Drop" >Drop</option>
    <option value="Empty" >Truncate</option>
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