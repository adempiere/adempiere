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
 * @author Alok
--%>


<!-- importPOSProducts.jsp -->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.*" %>
<%@ page import="org.posterita.struts.pos.ImportPOSProductAction" %>
<%@ page import="org.compiere.process.DocumentEngine" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title"><pos:element textOnly="true" columnName="I_Product_ID"/></bean:define>
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
		<pos:element textOnly="true" columnName="import.list"/>
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
<pos:element columnName="import.product.message"/>:
</font>
</div>
<br>
<table class="display" border="1"> 
  <tr>
    <th><pos:element columnName="C_RevenueRecognition_ID"/></th>
    <th><pos:element columnName="barcode"/></th>
    <th><pos:element columnName="Name"/></th>
    <th><pos:element columnName="purchase.price"/></th>
    <th><pos:element columnName="marked.price"/></th>
    <th><pos:element columnName="discounted.price"/></th>
    <th><pos:element columnName="PriceLimit"/></th>
    <th><pos:element columnName="vat"/></th>
    <th><pos:element columnName="stock.in.hand"/></th>
  </tr>
  <tr>
    <td>Tshirt</td>
    <td>6090018000250</td>
    <td>Tshirt Red XL</td>
    <td>300</td>
    <td>400</td>
    <td>380</td>
    <td>350</td>
    <td>0</td>
    <td>25</td>
  </tr>
  <tr>
    <td>Trousers</td>
    <td>7714739680121</td>
    <td>black cotton L</td>
    <td>350</td>
    <td>450</td>
    <td>430</td>
    <td>410</td>
    <td>15</td>
    <td>20</td>
  </tr>
  <tr>
  	<td align="center" colspan="4">
  		<b><a href="<%=cvsURL%>"><pos:element columnName="normal.template"/></a></b>
  	</td>
  	<td align="center" colspan="5">
  		<b><a href="<%=csvURL1%>"><pos:element columnName="import.product.message"/>Garment Template</a></b>
  	</td>
  </tr>
</table>

<script>
$focus('file');
</script>	    									 					 
<%@ include file="/jsp/include/posFooter.jsp" %>
