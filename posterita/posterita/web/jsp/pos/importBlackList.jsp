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


<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.*" %>
<%@ page import="org.posterita.struts.pos.ImportPOSProductAction" %>
<%@ page import="org.compiere.process.DocumentEngine" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title"><pos:message textOnly="true" key="import.black.listed"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 

<html:form action="/ImportPOSProductsAction" enctype="multipart/form-data">
<html:hidden property="action" value="importPOSProducts"/>						
<table class="main">
<tr>
	<td align="right">
 	<html:file property="file" styleClass="text"/>
	</td> 
</tr>                       
<tr>
	<td align="right">
	<html:submit styleClass="cntbtn">
		<pos:message key="import.list"/>
	</html:submit>
	</td>
</tr>	
</table>	
</html:form>

<div>
<%
	String cvsURL = request.getContextPath() + "/jsp/pos/importTemplate.csv";
%>
<font class="greencolor">
<pos:message key="import.list"/>
<pos:message key="import.blacklisted.message1"/>
</font>
</div>
<br>
<table class="display" border="1"> 
  <tr>
    <th><pos:message key="BankName"/></th>
    <th><pos:message key="cheque.no"/></th>
    
  </tr>
  <tr>
    <td>MCB</td>
    <td>6090018000250</td>
  </tr>
  <tr>
    <td>MCB</td>
    <td>7714739680121</td>
  </tr>
  <tr>
  	<td align="center" colspan="4">
  		<b><a href="<%=cvsURL%>"><pos:message key="normal.template"/></a></b>
  	</td>
  	
  </tr>
</table>

<script>
$focus('file');
</script>	    									 					 
<%@ include file="/jsp/include/posFooter.jsp" %>