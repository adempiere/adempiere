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
 * @author Martine
--%>


<!-- importCustomer.jsp -->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.*" %>
<%@ page import="org.posterita.struts.pos.ImportPOSProductAction" %>
<%@ page import="org.compiere.process.DocumentEngine" %>
<%@ page import="org.posterita.struts.pos.ImportCustomerAction" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title">Import Customer</bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 

<html:form action="/ImportCustomerAction" enctype="multipart/form-data">
<html:hidden property="action" value="importPOSCustomer"/>						
<table class="main">
	<tr>
		<td align="right">
	 	<html:file property="file" styleClass="text"/>
		</td> 
	</tr>                       
	<tr>
		<td align="right">
		<html:submit styleClass="cntbtn">
			Import
		</html:submit>
		</td>
	</tr>	
</table>	
</html:form>

<div>
<%
	String cvsURL = request.getContextPath() + "/jsp/pos/importTemplate.csv";
	String csvURL1=request.getContextPath() + "/jsp/pos/importGarmentTemplate.csv";
%>
<div id="importFormat">
	<font class="greencolor">
	This utility is to import a list of Customers from a csv file into the system, the csv file should look like the one shown below including the header:
	</font>
	<br>
	<br>
	<table class="display" border="1"> 
	  <tr>
	  	<th>Account No</th>
	    <th>Name</th>
	    <th>Address1</th>
	    <th>Address2</th>
	    <th>Address3</th>
	    <th>Postal Code</th>
	    <th>Str Address1</th>
	    <th>Str Address2</th>
	    <th>Str Address3</th>
	    <th>Contact</th>
	    <th>Phone</th>
	    <th>Fax</th>
	  </tr>
	  <tr>
	  	<td colspan="12">
	  		&nbsp;
	  	</td>
	  </tr>
	  <tr>
	    <th>Payment Term</th>
	    <th>Credit Lmt</th>
	    <th>BF Balance</th>
	    <th>Total Owe</th>
	    <th>Sales Rep</th>
	    <th>PriceList</th>
	    <th>Tax No</th>
	    <th>Bank</th>
	    <th>Branch</th>
	    <th>Bank Acc No</th>
	    <th>Email</th>
	    <th>Mobile</th>
	  </tr>  
	  
	</table>
</div>
 
<logic:notEmpty name="<%=Constants.CUSTOMER_CREATED%>">

<script>
	$('importFormat').style.display = 'none';
</script>

	<div id="firstTable">
		<div id="remainingDetailsBtn" class="button"><span>View Remaining Details</span></div><br/><br><br>
		<table class="display sortable" border="1" id="111">
			<tr>
				<th>Account No</th>
			    <th>Name</th>
			    <th>Address1</th>
			    <th>Address2</th>
			    <th>Postal Code</th>
			    <th>Str Address1</th>
			    <th>Str Address2</th>
			    <th>Address3</th>
			    <th>Contact</th>
			    <th>Phone</th>
			    <th>Fax</th>
			</tr>
			<logic:iterate indexId="count"  id="element" name="<%=Constants.CUSTOMER_CREATED%>" type="org.posterita.beans.CustomerBean">
				<%
				String styleClass = "label";
				if ((count.intValue()%2) != 0)
				styleClass = "contentname";
				
				%>
				<tr>
					<td class="<%=styleClass%>"><bean:write name="element" property="custIdNumber"/></td>
					<td class="<%=styleClass%>"><bean:write name="element" property="partnerName"/></td>
					<td class="<%=styleClass%>"><bean:write name="element" property="address1"/></td>
					<td class="<%=styleClass%>"><bean:write name="element" property="address2"/></td>
					<td class="<%=styleClass%>"><bean:write name="element" property="postalCode"/></td>
					<td class="<%=styleClass%>"><bean:write name="element" property="postalAddress"/></td>
					<td class="<%=styleClass%>"><bean:write name="element" property="postalAddress1"/></td>
					<td class="<%=styleClass%>"><bean:write name="element" property="city"/></td>
					<td class="<%=styleClass%>"><bean:write name="element" property="username"/></td>
					<td class="<%=styleClass%>"><bean:write name="element" property="phone"/></td>
					<td class="<%=styleClass%>"><bean:write name="element" property="fax"/></td>
				</tr>
			</logic:iterate>
		</table>
		<br/><div id="remainingDetailsBtn1" class="button"><span>View Remaining Details</span></div><br/><br/><br/>	
	</logic:notEmpty>
</div>

<logic:notEmpty name="<%=Constants.CUSTOMER_CREATED%>">
	<div id="secondTable" style="display:none">
		<div id="switchBack" class="button"><span>Switch Back</span></div><br/><br/><br/>
		<table class="display sortable" border="1" id="111">
			<tr>
				<th>Name</th>
				<th>Payment Term</th>
			    <th>Credit Lmt</th>
			    <th>Total Open Bal</th>
			    <th>PriceList</th>
			    <th>Sales Rep</th>
			    <th>Tax No</th>
			    <th>Bank</th>
			    <th>Branch</th>
			    <th>Bank Acc No</th>
			    <th>Email</th>
			    <th>Mobile</th>
				
			</tr>
			<logic:iterate indexId="count"  id="element" name="<%=Constants.CUSTOMER_CREATED%>" type="org.posterita.beans.CustomerBean">
				<%
				String styleClass = "label";
				if ((count.intValue()%2) != 0)
				styleClass = "contentname";
				
				%>
				<tr>
					<td class="<%=styleClass%>"><bean:write name="element" property="partnerName"/></td>
					<td class="<%=styleClass%>"><bean:write name="element" property="paymentTermName"/></td>
					<td class="<%=styleClass%>"><bean:write name="element" property="creditLimit"/></td>
					<td class="<%=styleClass%>"><bean:write name="element" property="totalOpenBalance"/></td>
					<td class="<%=styleClass%>"><bean:write name="element" property="priceListName"/></td>
					<td class="<%=styleClass%>"><bean:write name="element" property="saleRepName"/></td>
					<td class="<%=styleClass%>"><bean:write name="element" property="taxNo"/></td>
					<td class="<%=styleClass%>"><bean:write name="element" property="bankName"/></td>
					<td class="<%=styleClass%>"><bean:write name="element" property="branch"/></td>
					<td class="<%=styleClass%>"><bean:write name="element" property="accountNo"/></td>
					<td class="<%=styleClass%>"><bean:write name="element" property="email"/></td>
					<td class="<%=styleClass%>"><bean:write name="element" property="mobile"/></td>
				</tr>
			  </logic:iterate>
		</table>
		<br/><div id="switchBack1" class="button"><span>Switch Back</span></div><br/><br/><br/>
	</div>
</logic:notEmpty>
<script>
$focus('file');

function init()
{		
	var btn1 = $('remainingDetailsBtn');
	if(btn1 != null)
	{
		btn1.onclick = function(e)
		{
			$('firstTable').style.display = 'none';
			$('secondTable').style.display = 'block';
		};
		
		$('remainingDetailsBtn1').onclick = function(e)
		{
			$('firstTable').style.display = 'none';
			$('secondTable').style.display = 'block';
		};
	}
	
	var btn2 =  $('switchBack');
	
	if(btn2 != null)
	{
		btn2.onclick = function(e)
		{
			$('firstTable').style.display = 'block';
			$('secondTable').style.display = 'none';
		};	
		
		$('switchBack1').onclick = function(e)
		{
			$('firstTable').style.display = 'block';
			$('secondTable').style.display = 'none';
		}
	}
}


Event.observe(window,'load',init,false);
</script>	    									 					 
<%@ include file="/jsp/include/posFooter.jsp" %>
