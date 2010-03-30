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


<!-- viewPOSCustomer.jsp -->
<%@ page import="org.posterita.struts.customer.CustomerAction" %>
<%@ page import="org.compiere.model.MBPartner" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos" %>

<bean:define id="title"><pos:message key="customer.info" textOnly="true"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 

  
<table align="left" cellpadding="5" width="100%">
	<tr>
		<td>
			<fieldset style="height:120px">
				<legend><pos:message key="IsMenuContact"/></legend>
					<table>
						<tr>
							<td><label><pos:message key="Name"/>:</label></td>
							<td>
								<bean:write name="<%=Constants.CUSTOMER_DETAILS%>" property="partnerName"/>
							</td>
						</tr>
						<tr>
							<td><label><pos:message key="Name2"/>:</label></td>
							<td>
								<bean:write name="<%=Constants.CUSTOMER_DETAILS%>" property="surname"/>
							</td>
						</tr>
						<tr>
							<td><label><pos:message key="BirthDate"/>:</label></td>
							<td>
								<bean:write name="<%=Constants.CUSTOMER_DETAILS%>" property="birthdate" />
							</td>
						</tr>
					</table>
			</fieldset>
		</td>
		<td>
		<fieldset style="height:120px">
				<legend><pos:message key="credit.details"/></legend>
				<table>
					
				<tr>
				<td>
					<label><pos:message key="SO_CreditLimit"/>:</label>
				</td>
				<td>
					<bean:write name="<%=Constants.CUSTOMER_DETAILS%>" property="creditLimit"/>						
				</td>
				</tr>
				<tr>
				
				<td><label><pos:message key="SOCreditStatus"/>:</label></td>
				<logic:equal name="<%=Constants.CUSTOMER_DETAILS%>" property="creditStatus" value="<%=MBPartner.SOCREDITSTATUS_NoCreditCheck%>">
					<td>
						<pos:message key="credit.check"/>				
					</td>
				</logic:equal>
				
				<logic:equal name="<%=Constants.CUSTOMER_DETAILS%>" property="creditStatus" value="<%=MBPartner.SOCREDITSTATUS_CreditHold%>">
					<td>
						<pos:message key="credit.hold"/>			
					</td>
				</logic:equal>
				
				<logic:equal name="<%=Constants.CUSTOMER_DETAILS%>" property="creditStatus" value="<%=MBPartner.SOCREDITSTATUS_CreditOK%>">
					<td>
						<pos:message key="credit.ok"/>				
					</td>
				</logic:equal>
				<logic:equal name="<%=Constants.CUSTOMER_DETAILS%>" property="creditStatus" value="<%=MBPartner.SOCREDITSTATUS_CreditStop%>">
					<td>
						<pos:message key="credit.stop"/>						
					</td>
				</logic:equal>
				<logic:equal name="<%=Constants.CUSTOMER_DETAILS%>" property="creditStatus" value="<%=MBPartner.SOCREDITSTATUS_CreditWatch%>">
					<td>
						<pos:message key="credit.watch"/>			
					</td>
				</logic:equal>
				</tr>	
				
				<tr>
				
				<td><label><pos:message key="C_PaymentTerm_ID"/>:</label></td>
				<td>
					<bean:write name="<%=Constants.CUSTOMER_DETAILS%>" property="paymentTermName"/>						
				</td>
				</tr>
				</table>
			</fieldset>
		</td>
	</tr>
	<tr>
		<td>
			<fieldset style="height:130px">
				<legend><pos:message key="address"/></legend>
				<table>
					
				<tr>
			
				<td><label><pos:message key="address1"/>:</label></td>
				<td>
					<bean:write name="<%=Constants.CUSTOMER_DETAILS%>" property="address1"/>
				</td>
				</tr>
				<tr>
				<td><label><pos:message key="address2"/>:</label></td>
				<td>
					<bean:write name="<%=Constants.CUSTOMER_DETAILS%>" property="address2"/>
				</td>
				</tr>
				<tr>			
				<td><label><pos:message key="City"/>:</label></td>
				<td>
					<bean:write name="<%=Constants.CUSTOMER_DETAILS%>" property="city"/>
				</td>
				</tr>
				<tr>			
				<td><label><pos:message key="EMail"/>:</label></td>
				<td>
					<bean:write name="<%=Constants.CUSTOMER_DETAILS%>" property="email"/>	
				</td>
				</tr>
				</table>
				</fieldset>
				</td>
				<td>
				<fieldset style="height:130px">
				<legend><pos:message key="Phone"/></legend>
				<table>
				
				<tr>			
				<td><label><pos:message key="Phone"/>:</label></td>
				<td>
					<bean:write name="<%=Constants.CUSTOMER_DETAILS%>" property="phone"/>				
				</td>
				</tr>
			
				<tr>			
				<td><label><pos:message key="Phone2"/>:</label></td>
				<td><bean:write name="<%=Constants.CUSTOMER_DETAILS%>" property="phone2"/></td>
				</tr>

				<tr>			
				<td><label><bean:message key="bPartner.fax"/>:</label></td>
				<td>
					<bean:write name="<%=Constants.CUSTOMER_DETAILS%>" property="fax"/>						
				</td>
				</tr>
			</table>
			</fieldset>
			</td>
			</tr>
</table>
<tr>
<td>
<table align="left" cellpadding="5" width="100%">
			<tr>
			<td>
			
						<fieldset>
							<legend><pos:message key="Info"/></legend>
							<table>
								<tr>
								<td>
									<bean:define id="bpartnerId" name="<%=Constants.CUSTOMER_DETAILS%>" property="bpartnerId"/>
									<bean:define id="partnerName" name="<%=Constants.CUSTOMER_DETAILS%>" property="partnerName"/>
								
									<html:link href="<%="GetBpartnerPaymentStatus.do?action=getBpartnerPaymentStatus&forward=/ViewPOSCustomer.do&bpartnerId=" + bpartnerId+"&partnerName="+partnerName%>">
										<pos:message key="settle.payment"/>
									</html:link>
								
								</td>
								</tr>
								<tr>
								<td>	
									<html:link href="<%="ViewBPartnerTrxDetailsAction.do?action=getBpartnerTrxDetails&forward=/ViewPOSCustomer.do&isCustomer=true"+"&bpartnerId=" + bpartnerId %>">
											<pos:message key="view.info"/>
									</html:link>
								</td>
								</tr>
							</table>
						</fieldset>
					
			</td>
			</tr>

</table>
<div align="right">

	<html:button property="button" styleClass="blank smallbutton" onclick="<%= "get('POSCustomerAction.do?action=initEditPOSCustomer&bpartnerId=" + bpartnerId + "')" %>">Edit</html:button>
	<html:button property="button" styleClass="blank smallbutton" onclick="get('InitCreatePOSCustomer.do?action=initCreatePOSCustomer')">Add New</html:button>	
	<html:button property="button" styleClass="blank smallbutton" onclick="get('ViewAllCustomers.do')">Back</html:button>
</div>

				
<%@ include file="/jsp/include/posFooter.jsp" %>
	   
	
												