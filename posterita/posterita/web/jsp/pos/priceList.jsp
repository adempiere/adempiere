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
 * @author Praveen
--%>



<!--priceList.jsp-->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.*" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/POSLogin.do"/>
</logic:notPresent>

<bean:define id="title"><pos:message textOnly="true" key="M_PriceList_ID"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 

		<table class="main">
		<html:form action="/POSPriceListAction">
		<html:hidden property="action" value="initPriceList"/>			
			<tr>
				<td align="right">
					<pos:message key="search"/>:
					<html:text property="searchText"></html:text>
					<html:button property="button" styleClass="search smallbutton" onclick="setActionWithNoDupRequest(this.form,'initPriceList')">&nbsp;</html:button>
				</td>
			</tr>
			<tr>
				<td align="right">
					<input type="button" class="selectall smallbutton" onclick="selectAllProductIds(this.form)" value="&nbsp;"/>
					
				</td>
			</tr>
		</html:form>
		</table>
		
		<html:form action="/POSPriceListAction">
		<html:hidden property="action" value="initEditPriceList"/>
		<table class="display" border="1">			
			<tr>
				<th>
					<pos:message key="ProductName"/>
				</th>
				<th>
					<pos:message key="Price"/>
				</th>
				<th>
					&nbsp;
				</th>
			</tr>
			
			<logic:iterate indexId="count" name="<%=Constants.PRICE_LIST%>" id="product">
				<%
					String style = "label";
					if((count.intValue() % 2) == 0)
						style = "contentname";
				%>
				<bean:define id="productId" name="product" property="productId"/>
				<tr>
					<td class=<%=style%>>
						<bean:write name="product" property="productName"/>
					</td>
					<td class=<%=style%>>
						<bean:write name="product" property="priceStandard"/>
					</td>
					<td class=<%=style%>>
						
						<html:multibox property="productIds" value="<%=productId.toString()%>">
						</html:multibox>
					
					</td>		
				</tr>
			</logic:iterate>
		</table>
		<table class="main">
			<tr>
				<td align="right">
					<html:submit><pos:message key="edit.price"/></html:submit>
				</td>
			</tr>
		</table>
		</html:form>
<%@include file="/jsp/include/posFooter.jsp"%>