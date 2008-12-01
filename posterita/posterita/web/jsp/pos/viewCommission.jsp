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


<!--viewCommission.jsp-->
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.beans.ProductBean" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="org.posterita.lib.UdiConstants" %>
<%@ page import="org.posterita.struts.pos.CommissionAction" %>



<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos" %>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title"><pos:message key="C_Commission_ID" textOnly="true"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 

<table class="main">
	<tr>
		<td>		
			<%@ include file="/jsp/include/errors.jsp" %>	 
							   	
			<html:form action="/ViewCommissionAction">
			<html:hidden property="action" value="<%=CommissionAction.VIEW_COMMISSION %>"/>					
								
				<table class="display scrollpane sortable" id="1111" border="1">
					<tr>
						<th><pos:message key="Name"/></th>
						<th><pos:message key="cal.period.curr"/></th>
						<th><pos:message key="TotalAmt"/></th>
						<th><pos:message key="AmtSubtract"/></th>
						<th><pos:message key="CommissionAmt"/></th>
						<th><pos:message key="Qty"/></th>
						<th><pos:message key="FrequencyType"/></th>
						<th><pos:message key="doc.basis.type"/></th>
						<th><pos:message key="Rate"/>%</th>
					</tr>
						<bean:define id="symbol" name="<%=Constants.CURRENCY_SYMBOLE%>"/>							
						<logic:present name="<%=Constants.COMMISSION_AMT%>">
							<logic:iterate id="element" indexId="count" name="<%=Constants.COMMISSION_AMT %>" type="org.posterita.beans.CommissionBean">
								<tr>	
										<%
											String styleClass = "label";
											if ((count.intValue()%2) != 0)
												styleClass = "contentname";
										%>
									
														
									   <td class=<%=styleClass%> >
											<bean:write name="element" property="commissionLineName"/>
									   </td>
									   
									   <td class=<%=styleClass%> >
											<bean:write name="element" property="periodAndCurrencyDesc"/>
									   </td>
									   <td class="<%=styleClass%>">
									   <html:link href="<%="ViewCommissionAction.do?action=viewCommissionDetails&commissionAmtId="+element.getCommissionAmtId() %>">
											<bean:define id="convertedAmt" name="element" property="convertedAmt"/>
											<fmt:formatNumber value='${convertedAmt}' maxFractionDigits="2" type="currency" currencySymbol='${symbol}'/>	
										</html:link>
									   </td>
									   <td class=<%=styleClass%>>
											<bean:define id="subtractAmt" name="element" property="subtractAmt"/>
											<fmt:formatNumber value='${subtractAmt}' maxFractionDigits="2" type="currency" currencySymbol='${symbol}'/>	
									   </td>						
									   <td class=<%=styleClass%>>
											<bean:define id="commissionAmt" name="element" property="commissionAmt"/>
											<fmt:formatNumber value='${commissionAmt}' maxFractionDigits="2" type="currency" currencySymbol='${symbol}'/>	
									   </td>
									   <td class=<%=styleClass%>>
											<bean:write name="element" property="actualQty"/>
									   </td>
									   </td>						
									   <td class=<%=styleClass%>>
											<bean:write name="element" property="frequencyType"/>
									   </td>
									    <td class=<%=styleClass%>>
											<bean:write name="element" property="docBasisType"/>
									   </td>
									    <td class=<%=styleClass%>>
											<bean:write name="element" property="amtMultiplier"/>
									   </td>									   					
								</tr>
							</logic:iterate>    
						</logic:present>
				</table>							
			</html:form>
		</td>
	</tr>			
</table>
<div align="right">
	<bean:define id="reportURL" name="<%=Constants.REPORT_URL%>"/>
	<a href="<%=reportURL%>"><pos:message key="download.csv"/></a>	
</div>					
	    									 					 
<%@ include file="/jsp/include/posFooter.jsp" %>
