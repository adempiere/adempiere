<%--
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2008  Posterita Ltd
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
 *  @author sendy
--%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>	
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>
<%@page import="org.posterita.Constants"%>

<%@page import="org.posterita.struts.pos.POSReportAction"%>
<bean:define id="title">Stock Sales Report</bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %>
<!-- page contents -->
<!-- page contents -->

<div class="scrollpane">

<html:form action="/StockSalesReportAction">

<html:hidden property="action" value="<%=POSReportAction.GET_STOCK_SALES_REPORT%>"/>
	<fieldset style="width:80%">
			<legend><pos:message key="product.details"/></legend>
				 <div>
				 <label> <pos:message key="name"/></label>
				 <label style="margin-left:55px">&nbsp;</label><%@ include file="/jsp/include/searchProductPanel3.jsp" %>   
				 <html:hidden property="productId"/>
				 </div>
				 <div>&nbsp;</div>
				 <div>
				 	<label> <pos:message key="description"/></label>
				 	<label style="margin-left:29px"><html:text property="description"></html:text></label>
			 	</div>
			 	<div>&nbsp;</div>
			 	<div> 
			 		<label> <pos:message key="barcode"/></label>
			 		<label style="margin-left:52px"><html:text property="barCode"></html:text></label>
		 		</div>
				<div>&nbsp;</div>
				<div>
					<label>
							<pos:message key="smenu.organisation"/>
					</label>
					<label style="margin-left:30px">
					<html:select property="orgId" styleClass="text">
						<html:options collection="<%=Constants.USER_ORGS%>" property="key" labelProperty="name"/>
					</html:select>
					</label>
				</div>	
				<div>&nbsp;</div>
				<table border="0" cellpadding="0" cellspacing="0" align="left" >						
					<tr>
						<td valign="top">
							<table align="left">							
								<tr>
									<td>
										<%@ include file="/jsp/include/startCalendar.jsp" %>
									</td>								
								</tr>
								<tr>
									<td>
										<%@ include file="/jsp/include/endCalendar.jsp" %>
									</td>								
								</tr>
							</table>
						</td>
					</tr>
			   	</table>	
	</fieldset>		
		<table style="width:15%">
			<tr>
			<td>
				<html:hidden property="chartType" value="Tabular"/>
				<html:submit styleClass="smallbutton refresh">&nbsp;</html:submit>
				
			</td>
			<td align = "left">
			<html:hidden property="chartType" value="Tabular"/>
				<input type="button" value="Clear" class="newbutton" onclick="get('StockSalesReportAction.do?action=clearStockSalesReport')">
			</td>
			</tr>
		</table>
	<div>					
	
		<logic:present name="<%=Constants.STOCK_SALES_REPORT_DATA%>">					
			<bean:define id="stockReportData" name="<%=Constants.STOCK_SALES_REPORT_DATA%>"/>
							
			<%=stockReportData%>	
		</logic:present>					
	</div>				
</html:form>
</div>

<%@ include file="/jsp/include/posFooter.jsp" %>