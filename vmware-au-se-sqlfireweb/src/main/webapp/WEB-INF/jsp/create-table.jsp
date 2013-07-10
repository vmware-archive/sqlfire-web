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
<title><fmt:message key="sqlfireweb.appname" /> Create Table</title>
</head>
<body>

<h2><fmt:message key="sqlfireweb.appname" /> Create Table</h2>

<jsp:include page="toolbar.jsp" flush="true" />

<c:choose>
 <c:when test="${not empty numColumns}">
<form action="createtable" method="POST">
Table Name: 
<input type="TEXT" name="tabName" value="${sessionScope.tabName}" />
&nbsp; - &nbsp; Add 
<input type="TEXT" name="numColumns" size="4" value="1" onkeypress="return isNumberKey(event);" />
<input type="submit" name="pSubmit" value="Column(s)" />

<p />
<h3>Table Structure</h3>

<c:choose>
  <c:when test="${numColumns > 0}">
	 <table id="table_columns" class="data">
	 <thead>
	   <tr>
	    <th>Name</th>
	    <th>Type</th>
	    <th>Precision</th>
	    <th>Default</th>
	    <th>Not Null?</th>
	    <th>PK?</th>
	    <th>Auto Inc?</th>
	   </tr>
	 </thead>
	  <c:forEach var="row" varStatus="loop" begin="1" end="${numColumns}" step="1">
	   <tr class="${((loop.index % 2) == 0) ? 'even' : 'odd'}">	  
		 <td><input type="text" name="column_name[]" size="15" maxlength="30" /></td>
		 <td>
		   <select name="column_type[]">
		     <option value="INT">INT</option>
		     <option value="BIGINT">BIGINT</option>
		     <option value="BLOB">BLOB</option>
		     <option value="CHAR">CHAR</option>
		     <option value="CHAR FOR BIT DATA">CHAR FOR BIT DATA</option>
		     <option value="CLOB">CLOB</option>
		     <option value="DATE">DATE</option>
		     <option value="DECIMAL">DECIMAL</option>
		     <option value="DOUBLE">DOUBLE</option>
		     <option value="DOUBLE PRECISION">DOUBLE PRECISION</option>
		     <option value="FLOAT">FLOAT</option>
		     <option value="INTEGER">INTEGER</option>
		     <option value="LONG VARCHAR">LONG VARCHAR</option>
		     <option value="LONG VARCHAR FOR BIT DATA">LONG VARCHAR FOR BIT DATA</option>
		     <option value="NUMERIC">NUMERIC</option>
		     <option value="REAL">REAL</option>
		     <option value="SMALLINT">SMALLINT</option>
		     <option value="TIME">TIME</option>
		     <option value="TIMESTAMP">TIMESTAMP</option>
		     <option value="VARCHAR">VARCHAR</option>
		     <option value="VARCHAR FOR BIT DATA">VARCHAR FOR BIT DATA</option>
		     <option value="XML">XML</option>
		     <!-- user defined types go here -->
		     <c:forEach var="entry" varStatus="loop" items="${types}">
		       <option value="${entry.typeName}">${entry.typeName}</option>
		     </c:forEach>
		   </select>
		 </td>
		 <td><input type="text" name="column_precision[]" size="5" maxlength="30" onkeypress="return isNumberKey(event);"/></td>
		 <td><input type="text" name="column_default_value[]" size="10" maxlength="30" /></td>
		 <td>
		   <select name="column_selected_null[]">
		     <option value="N" selected="selected">N</option>
		     <option value="Y">Y</option>
		   </select>
		 </td>
		 <td>
		   <select name="column_selected_primary_key[]">
		     <option value="N" selected="selected">N</option>
		     <option value="Y">Y</option>
		   </select>
		 </td> 
		 <td>
		   <select name="column_selected_auto_increment[]">
		     <option value="N" selected="selected">N</option>
		     <option value="Y">Y</option>
		   </select>
		 </td> 
	   </tr>
	  </c:forEach>
	 </table> 
	 <h3>Additional Table Properties</h3>
	 <table>
	  <thead>
	    <tr>
	      <th colspan="3">Additional Table Properties</th>
	    </tr>
	  </thead>
	  <tbody>
	   <tr class="odd">
	     <td align="right">Policy</td>
	     <td>
		   <select name="dataPolicy">
		     <option value="REPLICATE" selected="selected">REPLICATE</option>
		     <option value="PARTITION">PARTITION</option>
		   </select>
	     </td>
	     <td>&nbsp;</td>
	   </tr>
	   <tr class="even">
	     <td align="right">Disk Store</td>
	     <td>
	       <select name="diskStore">
	         <option value=""></option>
	         <c:forEach var="entry" varStatus="loop" items="${diskstores}">
	         	<option value="${entry['name']}">${entry['name']}</option>
	         </c:forEach>
	       </select>
	     </td>	 
	     <td>Leave blank to use default DiskStore</td> 
	   </tr>
	   <tr class="odd">
	     <td align="right">Persistent?</td>
	     <td>
		   <select name="persistant">
		   	 <option value="N" selected="selected">N</option>
		     <option value="Y">Y</option>
		   </select>		   
	     </td>	  
	     <td>Persist data to disk</td> 
	   </tr>
	   <tr class="even">
	     <td align="right">Persistence Type</td>
	     <td>
		   <select name="persistenceType">
		   	 <option value="" selected="selected"></option>
		     <option value="ASYNCHRONOUS">ASYNCHRONOUS</option>
		     <option value="SYNCHRONOUS">SYNCHRONOUS</option>
		   </select>		   
	     </td>	 
	     <td>How data should be written to the DiskStore</td>  
	   </tr>
	   <tr class="odd">
	     <td align="right">Server Groups</td>
	     <td>
		   <input type="text" name="serverGroups" size="20" maxlength="100" />
	     </td>	
	     <td>Eg: MYGROUP or OrdersDB, OrdersReplicationGrp</td>   
	   </tr>
	   <tr class="even">
	     <td align="right">Partition By</td>
	     <td>
		   <input type="text" name="partitionBy" size="20" maxlength="200" />
	     </td>	
	     <td>Eg: by column(employee_id)</td>   
	   </tr>
	   <tr class="odd">
	     <td align="right">Colocate With</td>
	     <td>
		   <input type="text" name="colocateWith" size="20" maxlength="50" />
	     </td>	 
	     <td>Eg: EMP</td>  
	   </tr>
	   <tr class="even">
	     <td align="right">Redundancy</td>
	     <td>
		   <input type="text" name="redundancy" size="20" maxlength="1" onkeypress="return isNumberKey(event);"/>
	     </td>	  
	     <td>Eg: 2</td> 
	   </tr>
	   <tr class="odd">
	     <td align="right">Additional Parameters</td>
	     <td>
		   <input type="text" name="other" size="20" maxlength="200" />
	     </td>	  
	     <td>Eg: EVICTION BY LRUCOUNT 5 EVICTACTION OVERFLOW</td> 
	   </tr>
	  </tbody>
	 </table> 
	 <br />
	 <input type="submit" value="Create" name="pSubmit" />
	 <input type="submit" value="Show SQL" name="pSubmit" />
	 <input type="submit" value="Save to File" name="pSubmit" />
  </c:when>
  <c:otherwise>
	<div class="notice">
		Add columns to get started above. Adding columns will remove current values in the form
	</div>  
  </c:otherwise>
</c:choose>
</form> 
 </c:when>
 <c:otherwise>
 	<c:if test="${!empty sql}">
 	 <div class="success">
		Successfully generated SQL 
	 </div>
	 <h3>Create table SQL </h3>
	 <table id="table_results" class="data">
	   <tbody>
	     <tr class="odd">
	      <td><pre>${sql}</pre></td>
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
 </c:otherwise>
</c:choose>

<br />

<jsp:include page="footer.jsp" flush="true" />

</body>
</html>