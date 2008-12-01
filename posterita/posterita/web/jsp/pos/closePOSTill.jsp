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
 * @author Praveen, Alok
--%>

<!--closePOSTill.jsp-->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.*" %>
<%@ page import="org.posterita.struts.pos.CashBookAction" %>
<%@ page import="org.posterita.businesslogic.POSTerminalManager" %>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/posterita.tld" prefix="posterita"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>


<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title"><pos:message textOnly="true" key="close.till"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>	   
<%@ include file="/jsp/include/errors.jsp" %>
<!-- Check currency type -->

<logic:equal name="<%= Constants.CURRENCY_SYMBOLE %>" value="Rs">
<table>
	<tr>
		<td valign="top">
			<fieldset>
			<legend><pos:message key="coin"/></legend>
			<table>
				<tr>
					<td><pos:message key="1_cent"/></td>
					<td><input name="denomination" type="text" class="medium text" factor="0.01"></td>
				</tr>
				<tr>
					<td><pos:message key="5_cents" /></td>
					<td><input name="denomination" type="text" class="medium text" factor="0.05"></td>
				</tr>
				<tr>
					<td><pos:message key="20_cents"/></td>
					<td><input name="denomination" type="text" class="medium text" factor="0.20"></td>
				</tr>
				<tr>
					<td><pos:message key="50_cents"/></td>
					<td><input name="denomination" type="text" class="medium text" factor="0.50"></td>
				</tr>
				<tr>
					<td><pos:message key="1_rupee"/></td>
					<td><input name="denomination" type="text" class="medium text" factor="1.00"></td>
				</tr>
				<tr>
					<td><pos:message key="5_rupees"/></td>
					<td><input name="denomination" type="text" class="medium text" factor="5.00"></td>
				</tr>
				<tr>
					<td><pos:message key="10_rupees"/></td>
					<td><input name="denomination" type="text" class="medium text" factor="10.00"></td>
				</tr>
			</table>
		</fieldset>
		</td>			
		<td valign="top">
			<fieldset>
			<legend><pos:message key="notes"/></legend>
			<table>
				<tr>
					<td><pos:message key="25_rupees"/></td>
					<td><input name="denomination" type="text" class="medium text" factor="25.00"></td>
				</tr>
				<tr>
					<td><pos:message key="50_rupees"/></td>
					<td><input name="denomination" type="text" class="medium text" factor="50.00"></td>
				</tr>
				<tr>
					<td><pos:message key="100_rupees"/></td>
					<td><input name="denomination" type="text" class="medium text" factor="100.00"></td>
				</tr>
				<tr>
					<td><pos:message key="200_rupees"/></td>
					<td><input name="denomination" type="text" class="medium text" factor="200.00"></td>
				</tr>
				<tr>
					<td><pos:message key="500_rupees"/></td>
					<td><input name="denomination" type="text" class="medium text" factor="500.00"></td>
				</tr>
				<tr>
					<td><pos:message key="1000_rupees"/></td>
					<td><input name="denomination" type="text" class="medium text" factor="1000.00"></td>
				</tr>
				<tr>
					<td><pos:message key="2000_rupees"/></td>
					<td><input name="denomination" type="text" class="medium text" factor="2000.00"></td>
				</tr>					
			</table>
		</fieldset>
		</td>
	</tr>
</table>
</logic:equal>
<logic:equal name="<%= Constants.CURRENCY_SYMBOLE %>" value="$">
<!-- American currency -->
<table>
	<tr>
		<td valign="top">
			<fieldset>
			<legend><pos:message key="coin"/></legend>
			<table>
				<tr>
					<td><pos:message key="1_cent"/></td>
					<td><input name="denomination" type="text" class="medium text" factor="0.01"></td>
				</tr>
				<tr>
					<td><pos:message key="5_cents"/></td>
					<td><input name="denomination" type="text" class="medium text" factor="0.05"></td>
				</tr>
				<tr>
					<td><pos:message key="10_cents"/></td>
					<td><input name="denomination" type="text" class="medium text" factor="0.10"></td>
				</tr>
				<tr>
					<td><pos:message key="25_cents"/></td>
					<td><input name="denomination" type="text" class="medium text" factor="0.25"></td>
				</tr>
			</table>
		</fieldset>
		</td>			
		<td valign="top">
			<fieldset>
			<legend><pos:message key="notes"/></legend>
			<table>
				<tr>
					<td><pos:message key="1_dollar"/></td>
					<td><input name="denomination" type="text" class="medium text" factor="1.00"></td>
				</tr>
				<tr>
					<td><pos:message key="5_dollars"/></td>
					<td><input name="denomination" type="text" class="medium text" factor="5.00"></td>
				</tr>
				<tr>
					<td><pos:message key="10_dollars"/></td>
					<td><input name="denomination" type="text" class="medium text" factor="10.00"></td>
				</tr>
				<tr>
					<td><pos:message key="20_dollars"/></td>
					<td><input name="denomination" type="text" class="medium text" factor="20.00"></td>
				</tr>
				<tr>
					<td><pos:message key="50_dollars"/></td>
					<td><input name="denomination" type="text" class="medium text" factor="50.00"></td>
				</tr>
				<tr>
					<td><pos:message key="100_dollars"/></td>
					<td><input name="denomination" type="text" class="medium text" factor="100.00"></td>
				</tr>					
			</table>
		</fieldset>
		</td>
	</tr>
</table>
</logic:equal>
<html:form action="/ClosePOSTillAction" focus="transferAmount">
	<html:hidden property="action" value="<%=CashBookAction.CLOSE_TILL%>" />	
		<table border="0" align="left" >
		  <tr>
		  </tr>
		    <%
				String styleClass = "label";
			%>	
		  <tr>
		  		<td class="<%=styleClass%>">
		  			<label><pos:message  key="cash.amount"/>:</label>			    	
			    </td>
			    
			    <td class="<%=styleClass%>">
			    	<html:text property="transferAmount" styleClass="text"/>
			    	<img src="images/pos/helpIcon2.gif" name="help" tooltip="Cash Amount including float amount)">
			    </td>
		 </tr>
		 <tr>	
		 		 <td class="<%=styleClass%>" colspan="1">
			    	<label><pos:message  key="card.amount"/>:</label>
			    </td>
			        
			    <td class="<%=styleClass%>">
			    	<html:text property="cardTotal" styleClass="text"/>
			    	<img src="images/pos/helpIcon2.gif" name="help" tooltip="">
			    </td>
		</tr>
		
		<tr>	
				<td class="<%=styleClass%>">
			    	<label><pos:message  key="cheque.amount"/>:</label>
			    </td>
			        
			    <td class="<%=styleClass%>" colspan="1">
			    	<html:text property="chequeTotal" styleClass="text"/>
			    	<img src="images/pos/helpIcon2.gif" name="help" tooltip="">
			    </td>
		</tr>
		<tr>
			<td class="<%=styleClass%>">
				<label><pos:message  key="BeginningBalance"/>:</label>
			</td>
				
			<td class="<%=styleClass%>">
		    	<html:text name="<%=Constants.CURRENT_TILL_AMOUNT_POS%>" property="beginingBalance" styleClass="text"/>
		    	<img src="images/pos/helpIcon2.gif" name="help" tooltip="<pos:message textOnly="true" key="float.amt.change"/>">
		    </td>
		</tr>
		<tr>
			<td colspan="2">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<label>
					<pos:message  key="cash.to.transfer"/>: <strong><span id="cashToTransfer">0</span></strong>
				</label>
			</td>
		</tr>
		
		<tr>	    
			   <td colspan="2" align="right"> 
			   <br>
			    <html:submit styleClass="submit smallbutton">
					&nbsp;
				</html:submit>
			  </td>	
	    </tr>
		  
		</table>
</html:form> 
<script>
var cashToTransfer  = $('cashToTransfer');
var transferAmount  = $FElement('transferAmount');
var beginingBalance = $FElement('beginingBalance');
var denomination    = document.getElementsByName('denomination');
//setting initial amount
beginingBalance.initialAmount = beginingBalance.value;

transferAmount.onkeyup = function(){
	var c = new Number(this.value - beginingBalance.value).toFixed(2);
	cashToTransfer.innerHTML =  c.toString();
};

beginingBalance.onkeyup = function(){
	var c = new Number(transferAmount.value - this.value).toFixed(2);
	cashToTransfer.innerHTML =  c.toString();
};

var calculateCashAmount = function(){
	var amount = parseFloat('0.0');
	
	for(var i=0; i<denomination.length; i++)
	{
		var factor = parseFloat(denomination[i].getAttribute('factor'));
		amount += (denomination[i].value * factor);
	}
	
	transferAmount.value = amount;
	var c = new Number(transferAmount.value - beginingBalance.value).toFixed(2);
	cashToTransfer.innerHTML =  c.toString();
};

for(var j=0; j<denomination.length; j++)
{
	
	denomination[j].onkeyup = calculateCashAmount;
}

calculateCashAmount();
</script>
<%@ include file="/jsp/include/posFooter.jsp" %>