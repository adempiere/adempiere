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

<html:hidden property="bpartnerId"/>
<html:hidden property="userId"/>
<table align="left" cellpadding="5">
	<tr>
		<td>
			<fieldset>
				<legend><pos:message key="customer"/></legend>
				<table>
				<tr>			
					<td><label><pos:message key="Name"/></label><mandatory>*</mandatory></td>
					<td><html:text property="partnerName" tabindex="1" styleClass="text"/></td>
				</tr>
				<tr>
					<td><label><pos:message key="customer.code"/></label><mandatory>*</mandatory></td>
					<td><html:text property="surname" tabindex="2"  styleClass="text"/></td>
				</tr>
				<tr>
					<td><label><pos:message key="BirthDate"/></label></td>
					<td>
						<html:select property="startDay" styleClass="daySelectBox"  >			
							<%@ include file="/jsp/include/dayList.jsp" %>
						</html:select>
						<html:select property="startMonth" styleClass="monthSelectBox">			
							<%@ include file="/jsp/include/monthList.jsp" %>
						</html:select>																				
						<html:select property="startYear" styleClass="yearSelectBox">	
							<%@ include file="/jsp/include/year.jsp" %>
						</html:select>
					</td>
					<td>
						<html:hidden property="birthdate"/>
							<input type="hidden" id="startDate">
						<button id="startDateBtn" style="background-image: url('images/calendar_icon.png');height: 22px;width: 22px"></button>
					</td>
				</tr>
				
				<tr>
					<td>
						<label><pos:message key="organisation.name"/>:</label><mandatory>*</mandatory>
					</td>
				
					<td>
						<html:select property="orgId" styleClass="text">
							<html:options collection="<%=Constants.USER_ORGS%>" property="key" labelProperty="name"/>
					 	</html:select>
					</td>
				</tr>	
				<tr>
					<td>
						<label><pos:message key="price.list"/>:</label><mandatory>*</mandatory>
					</td>
					<td>
						<html:select property="priceListId" styleClass="text" >
						<html:option value=""></html:option>
							<html:options collection="<%=Constants.USER_SALES_PRICE_LISTS%>" property="key" labelProperty="name" />
				 		</html:select>
					</td>
				</tr>
														
				</table>
				</fieldset>
				</td>
				<td valign="top">
				
				<fieldset class="createcustomer">
				<legend>Credit Details</legend>
				<table>
					<tr>			
					<td><label><pos:message key="SOCreditStatus"/></label><mandatory>*</mandatory></td>
					<td>
						<html:select property="creditStatus" tabindex="3" styleClass="text">
						<html:option value="<%=MBPartner.SOCREDITSTATUS_NoCreditCheck%>"><%=Constants.NO_CREDIT_CHECK%></html:option>
						<html:option value="<%=MBPartner.SOCREDITSTATUS_CreditOK%>"><%=Constants.CREDIT_OK%></html:option>
						<html:option value="<%=MBPartner.SOCREDITSTATUS_CreditStop%>"><%=Constants.CREDIT_STOP%></html:option>
						
				  		</html:select>
				 	</td>  
					</tr>
				
					<tr>			
						<td><label><pos:message key="SO_CreditLimit"/></label></td>
						<td><html:text property="creditLimit" tabindex="4" styleClass="text"/></td>
					</tr>
					<tr>			
					<td><label><pos:message key="C_PaymentTerm_ID"/></label></td>
					<td>
						<html:select property="paymentTermId" tabindex="3" styleClass="text">
											<html:option value=""><pos:message key="select"/></html:option>
											<html:options collection="<%= Constants.PAYMENT_TERM %>" property="paymentTermId" labelProperty="paymentTermName"/>																																	
					  	</html:select>
					</td>  
					</tr>
				</table>
				</fieldset>
				</td>
				</tr>
				<tr>
				<td valign="top">
				<fieldset class="createcustomer">
				<legend><pos:message key="address"/></legend>
				<table>
					<tr>
			
						<td><label><pos:message key="Address1"/></label><mandatory>*</mandatory></td>
						<td><html:text property="address1" tabindex="5" styleClass="text"/></td>
					</tr>
					<tr>
						<td><label><pos:message key="Address2"/></label></td>
						<td><html:text property="address2" tabindex="6" styleClass="text"/></td>
					</tr>
					<tr>			
						<td><label><pos:message key="City"/></label></td>
						<td><html:text property="city" tabindex="7" styleClass="text"/></td>
					</tr>
					<tr>			
						<td><label><pos:message key="Email"/></label></td>
						<td><html:text property="email" tabindex="8" styleClass="text"/></td>
					</tr>
				</table>
				</fieldset>
				</td>
				<td valign="top">
				<fieldset class="createcustomer">
				<legend><pos:message key="phone"/></legend>
				<table>
					<tr>			
						<td><label><pos:message key="phone"/>:</label></td>
						<td><html:text property="phone" tabindex="9" styleClass="text"/></td>
					</tr>
					<tr>			
						<td><label><pos:message key="phone2"/>:</label></td>
						<td><html:text property="phone2" tabindex="10" styleClass="text"/></td>
					</tr>
					<tr>			
						<td><label><pos:message key="mobile"/>:</label></td>
						<td><html:text property="mobile" tabindex="11" styleClass="text"/></td>
					</tr>
					<tr>			
						<td><label><pos:message key="fax"/>:</label></td>
						<td><html:text property="fax" tabindex="12" styleClass="text"/></td>
					</tr>
					
				</table>
				</fieldset class="createcustomer">
				</td>
				</tr>
				
					<tr>
					<td></td>
					<td align="right">
							<html:button property="button" styleClass="newbutton" onclick="submit()" tabindex="16" accesskey="s">
							Save
	        				</html:button>	        				
				</td>
			</tr>
</table>
<script>
	addRequiredLibrary('js/js-calendar.js');
	
	function setStartDate(calendar)
	{
		var y = calendar.date.getFullYear();
		var m = calendar.date.getMonth();
		var d = calendar.date.getDate();
		
		$FElement('startDay').options[d-1].selected = true;
		$FElement('startMonth').options[m].selected = true;
		
		var options = $FElement('startYear').options;
		
		for(var i = 0; i < options.length; i++)
		{
			if(options[i].value == y)
			{
				$FElement('startYear').options[i].selected = true;
				break;
			}
		}
		
		updateStartDate();		
		
	}
	
	function updateStartDate()
	{
		var d = $FElement('startDay').selectedIndex + 1;
		var m = $FElement('startMonth').selectedIndex + 1;
		var y = $FElement('startYear').selectedIndex;
		y = $FElement('startYear').options[y].value;
		
		d = (d > 9) ? d : ('0' + d);
		m = (m > 9) ? m : ('0' + m);
		
		$('startDate').value = d + '/' + m + '/' + y;
		$FElement('birthdate').value = $('startDate').value;
	}
	
	function initStartCalendar()
	{
		$FElement('startDay').onchange = updateStartDate;
		$FElement('startMonth').onchange = updateStartDate;
		$FElement('startYear').onchange = updateStartDate;
		
		Calendar.setup(
			{ 	inputField : "startDate",
				ifFormat   : "%d/%m/%Y",
				showTime   : true,	
				button	   : "startDateBtn",
				onUpdate   : setStartDate
			}
		);
		
		$('startDate').value = $FElement('birthdate').value;
	}
		
	Event.observe(window,'load',initStartCalendar,false);
</script>
</td>
</tr>
<tr>
<td>


