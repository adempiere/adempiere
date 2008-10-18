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


<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>
<fieldset>
	<legend><pos:message key="TenderType" /></legend>
	<div style="height:160px">
	<table border="0" cellpadding="5">
		<tr>
			<td>
				<table>
				<tr>
					<td>
						<html:button property="btn" styleClass="cash smallbutton" onclick="return setTrxType('Cash');" accesskey="s">&nbsp;</html:button>
					</td>
				  
					<td>
						<html:button property="btn" styleClass="card smallbutton" onclick="return setTrxType('Card');" accesskey="r">&nbsp;</html:button>
				   	</td>
					
					<td>
						<html:button property="btn" styleClass="cheque smallbutton" onclick="return setTrxType('Cheque');" accesskey="k">&nbsp;</html:button>
				   	</td>
				</tr>
				</table>
			</td>								   		 	
		</tr>
		<tr>
			<td>										
				<html:hidden property="trxType"/>
				<table>
					<tbody id="cashNoTxt">
					<tr>
						<td style="width:180px"><label><pos:message key="payment.by" /></label></td>
						<td>&nbsp;</td>
					</tr>
					</tbody>
					<tbody id="cardNoTxt">
					<tr>
						<td style="width:180px"><label><pos:message key="card.no" /></label></td>
						<td><html:text property="creditCardNumber" styleClass="text"/></td>
					</tr>
					</tbody>
					<tbody id="chequeNoTxt">
					<tr>
						<td style="width:180px"><label><pos:message key="cheque.no" /></label></td>
						<td><html:text property="chequeNo" styleClass="text"/></td>
					</tr>
					</tbody>
				</table>										
			</td>
		</tr>	
	</table>
	</div>
</fieldset>	
<script>
function setTrxType(type)
	{
		//toConsole(type);
		
		document.forms[0].trxType.value = type;
		
		var divCash = $('cashNoTxt');
		var divCard = $('cardNoTxt');
		var divCheque = $('chequeNoTxt');
		
		Element.hide(divCash);
		Element.hide(divCard);
		Element.hide(divCheque);
		
		if(type == 'Cash')
		{
			Element.show(divCash);
		}			
			
		if(type == 'Card')
		{
			Element.show(divCard);
			$focus('creditCardNumber');
		}
			
		if(type == 'Cheque')
		{
			Element.show(divCheque);
			$focus('chequeNo');
		}
		
		return false;
	}
	
	try
	{
		Element.hide($('cashNoTxt'));
		Element.hide($('cardNoTxt'));
		Element.hide($('chequeNoTxt'));
		
		if(document.forms[0].trxType.value)
			setTrxType(document.forms[0].trxType.value);
		else
			setTrxType('Cash');
	}
	catch (e)
	{
		//toConsole(e);
	}
</script>