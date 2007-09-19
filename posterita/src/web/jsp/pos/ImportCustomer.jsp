<%--
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2007  Posterita Ltd
 *  This file is part of POSterita
 *  
 *  POSterita is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * @author Martine
--%>


<!-- importCustomer.jsp -->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.*" %>
<%@ page import="org.posterita.struts.pos.ImportPOSProductAction" %>
<%@ page import="org.compiere.process.DocumentEngine" %>
<%@ page import="org.posterita.struts.pos.ImportCustomerAction" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title">Import Customer</bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 

<html:form action="/ImportCustomerAction" enctype="multipart/form-data">
<html:hidden property="action" value="importPOSCustomer"/>						
<table class="main">
	<tr>
		<td align="right">
	 	<html:file property="file" styleClass="text"/>
		</td> 
	</tr>                       
	<tr>
		<td align="right">
		<html:submit styleClass="cntbtn">
			Import
		</html:submit>
		</td>
	</tr>	
</table>	
</html:form>

<div>
<%
	String cvsURL = request.getContextPath() + "/jsp/pos/importTemplate.csv";
	String csvURL1=request.getContextPath() + "/jsp/pos/importGarmentTemplate.csv";
%>
<font class="greencolor">
This utility is to import a list of Customers from a csv file into the system, the csv file should look like the one shown below including the header:
</font>
</div>
<br>
<table class="display" border="1"> 
  <tr>
    <th>Name</th>
    <th>First Name</th>
    <th>Address1</th>
    <th>City</th>
    <th>Email</th>
    <th>Phone</th>
    
  </tr>
  
  
</table>

<script>
$focus('file');
</script>	    									 					 
<%@ include file="/jsp/include/posFooter.jsp" %>
