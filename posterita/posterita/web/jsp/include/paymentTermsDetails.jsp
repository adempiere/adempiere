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

<tr>
		<td>
			<label><pos:message key="PaymentTerm"/></label><mandatory>*</mandatory>
		</td>
		
		<td colspan="3">
			<html:text  property="paymentTermName" styleClass="text"/>
			<html:hidden  property="paymentTermId"/>
		</td>
		
	</tr>
	<tbody id="netDays">
	<tr>		
		<td>
			<label><pos:message key="NetDays"/></label><mandatory>*</mandatory>
		</td>
		<td colspan="3">
			<html:text  property="netDays" styleClass="text" style="width:100%"/>		    
		</td>
	</tr>
	</tbody>
		<tr>		
			<td>
				<label><pos:message key="IsDueFixed"/></label>
			</td>
			<td colspan="3">
				 <html:checkbox  property="fixedDueDate" value="true"/>
			</td>
		</tr>
		<tbody id="fixDueDate">
		<tr>		
			<td>
				<label><pos:message key="FixMonthDay"/></label><mandatory>*</mandatory>
			</td>
			<td colspan="3">
				<html:text  property="fixedMonthDay" styleClass="text" style="width:100%"/>		    
			</td>
		</tr>
		
		<tr>		
			<td>
				<label><pos:message key="FixMonthOffset"/></label><mandatory>*</mandatory>
			</td>
			<td colspan="3">
				<html:text  property="fixedMonthOffset" styleClass="text" style="width:100%"/>		    
			</td>
		</tr>
		<tr>		
			<td>
				<label><pos:message key="FixMonthCutoff"/></label><mandatory>*</mandatory>
			</td>
			<td colspan="3">
				<html:text  property="fiedMonthCutoff" styleClass="text" style="width:100%"/>		    
			</td>
		</tr>
		</tbody>
		<tr>		
			<td>
				<label><pos:message key="AfterDelivery"/></label>
			</td>
			<td colspan="3">
				<html:checkbox  property="afterDelivery" value="true"/>	    
			</td>
		</tr>
	
       <tr>		
			<td>
				<label><pos:message key="IsActive"/></label>
			</td>
			<td colspan="3">
				 <html:checkbox  property="isActive" value="true"/>
			</td>
		</tr>



		<tr>		
			<td>
				<label><pos:message key="DiscountDays"/></label>
			</td>
			<td colspan="3">
				<html:text  property="discountDay1" styleClass="text" style="width:100%"/>		    
			</td>
		</tr>
		
		<tr>		
			<td>
				<label><pos:message key="Discount"/></label>
			</td>
			<td colspan="3">
				<html:text  property="discountAmt1" styleClass="text" style="width:100%"/>		    
			</td>
		</tr>
		
		<tr>		
			<td>
				<label><pos:message key="DiscountDays2"/></label>
			</td>
			<td colspan="3">
				<html:text  property="discountDay2" styleClass="text" style="width:100%"/>		    
			</td>
		</tr>
		
		<tr>		
			<td>
				<label><pos:message key="Discount2"/></label>
			</td>
			<td colspan="3">
				<html:text  property="discountAmt2" styleClass="text" style="width:100%"/>		    
			</td>
		</tr>	
		
	