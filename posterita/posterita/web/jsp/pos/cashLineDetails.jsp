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
 * @author Ashley
--%>

<%@ page import="org.posterita.Constants" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

			<logic:present name="<%= Constants.CASH_LINE_DETAILS%>">
				<table class="display" border="1">
					<tr>
						<th width="200px"><pos:message key="CashType"/></th>
						<th><pos:message key="description"/></th>
						<th width="180px"><pos:message key="Created"/></th>
						<th class="numeric" width="150px"><pos:message key="Amt"/></th>
					</tr>
					<logic:present name="<%=Constants.CASH_LINE_DETAILS%>">
					<logic:iterate id="element" indexId="count" name="<%=Constants.CASH_LINE_DETAILS %>" type="org.posterita.beans.CashLineBean">
						<tr>	
							<%
								String styleClass = "label";
								if ((count.intValue()%2) != 0)
									styleClass = "contentname";
							%>
							<td>
							
							<%
								Integer invoiceId = element.getInvoiceId();
								String cashTypeLink = "";
								if(invoiceId.intValue() == 0)
									cashTypeLink = element.getCashTypeName();
								else
								{
									cashTypeLink = "<a href='ViewInvoiceAction.do?action=viewOrder&documentId=" + invoiceId.toString() + "'>";
									cashTypeLink += element.getCashTypeName();
									cashTypeLink += "</a>";
								}
							%>
							
							<%= cashTypeLink%>
							
							</td>
							<td>
								<bean:write name="element" property="description"/>
							</td>
							<td>
								<fmt:formatDate value='${element.dateCreated}'  type="both" dateStyle="short" timeStyle="medium"/>
							</td>
							<td align="right">
								<fmt:formatNumber value='${element.amount}' type="currency" currencySymbol='${currSymbole}'/>
							</td>
						</tr>
					</logic:iterate>
					</logic:present>
				</table>
			</logic:present>