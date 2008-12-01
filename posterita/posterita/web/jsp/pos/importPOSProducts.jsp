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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title"><pos:message textOnly="true" key="I_Product_ID"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %>
<logic:present name="<%=Constants.IMPORT_FAIL_CSV_FILE%>" scope="session">
<bean:define id="csvFile" name="<%=Constants.IMPORT_FAIL_CSV_FILE%>"/>		
<br>
<p align="right">
	<a href="<%=csvFile%>">
		<html:button property="btn" styleClass="saveascsv smallbutton">&nbsp;</html:button>
	</a>
</p>
</logic:present>

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
		<label><pos:message key="sales.price.list"/>:</label>
	
		<html:select property="salesPriceListId" styleClass="text" >
			<html:option value=""></html:option>
			<html:options collection="<%=Constants.USER_SALES_PRICE_LISTS%>" property="key" labelProperty="name" />
 		</html:select>
	</td>
</tr>        
<tr>
	<td align="right">
		<label><pos:message key="purchase.price.list"/>:</label>
	
		<html:select property="purchasePriceListId" styleClass="text" >
			<html:option value=""></html:option>
			<html:options collection="<%=Constants.USER_PURCHASE_PRICE_LISTS%>" property="key" labelProperty="name" />
 		</html:select>
	</td>
</tr>        
<tr>
	<td align="right">
	<html:submit styleClass="cntbtn">
		<pos:message textOnly="true" key="import.list"/>
	</html:submit>
	</td>
</tr>	
</table>	
</html:form>

<div>
<%
	String cvsURL = request.getContextPath() + "/import/importTemplate.csv";
	String csvURL1=request.getContextPath() + "/import/importGarmentTemplate.csv";
%>
<font class="greencolor">
<pos:message key="import.product.message"/>:
</font>
</div>
<br>
<table class="display" border="1"> 
  <tr>
    <th><pos:message key="C_RevenueRecognition_ID"/></th>
    <th><pos:message key="barcode"/></th>
    <th><pos:message key="Name"/></th>
    <th><pos:message key="description"/></th>
    <th><pos:message key="uom"/></th>
    <th><pos:message key="purchase.price"/></th>
    <th><pos:message key="marked.price"/></th>
    <th><pos:message key="discounted.price"/></th>
    <th><pos:message key="PriceLimit"/></th>
    <th><pos:message key="vat"/></th>
    <th><pos:message key="stock.in.hand"/></th>
  </tr>
  <tr>
    <td>Tshirt</td>
    <td>6090018000250</td>
    <td>Tshirt Red XL</td>
    <td>Tshirt Red XL</td>
    <td>One</td>
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
    <td>black cotton L</td>
    <td>Each</td>
    <td>350</td>
    <td>450</td>
    <td>430</td>
    <td>410</td>
    <td>15</td>
    <td>20</td>
  </tr>
  <tr>
  	<td align="center" colspan="5">
  		<b><a href="<%=cvsURL%>"><pos:message key="normal.template"/></a></b>
  	</td>
  	<td align="center" colspan="6">
  		<b><a href="<%=csvURL1%>"><pos:message key="import.product.message"/>Garment Template</a></b>
  	</td>
  </tr>
</table>
<script>
$focus('file');
</script>
<%@ include file="/jsp/include/posFooter.jsp" %>
