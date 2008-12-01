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


<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.Constants" %>


<%@ page import="org.posterita.struts.pos.POSOrderAction" %>
<%@ page import="org.posterita.beans.ProductBean" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="org.posterita.lib.UdiConstants" %>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/dcs.tld" prefix="dcs" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>


<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/tabTop.jsp" %><pos:message key="pos.order"/><%@ include file="/jsp/include/tabBottom.jsp" %>	   
<%@ include file="/jsp/include/errors.jsp" %> 
	  						   

<html:form action="/GetPOSPaymentDetailsAction">
<html:hidden property="action" value=""/>

<tr>			   		
	<td>
		<table cellpadding="5" border="0" cellpadding="10" cellspacing="0">
			<tr>
				<td>
					<label><pos:message key="search.customer"/>:</label>
					<html:hidden property="bpartnerId"/>
				</td>
				<td width="75%">
					<%@ include file="/jsp/include/searchCustomerPanel.jsp" %>
				</td>			
			</tr>
			<tr>
				<td align="right">
					<html:button property="btn" styleClass="bigbtn" onclick="createCustomer()" accesskey="n">
						<pos:message key="new.customer"/>
					</html:button>		
				</td>
			</tr>	
		</table>
	</td>		
</tr>
	
<tr>
	<td>&nbsp;</td>
</tr>
<tr>
	<td>
		<%@ include file="/jsp/pos/shoppingCart.jsp" %>
	</td>
</tr>  
   		
<tr>
	<td>&nbsp;</td>
</tr>	
      		
<tr>     
	<td>
		<html:button property="btn" styleClass="cntbtn" onclick="getPaymentDetails()" accesskey="c">
	   	 <pos:message key="continue"/>
	   	</html:button>				 	
   	</td>	
</tr> 
<tr>
	<td>&nbsp;</td>
</tr>					
<tr>					  
	<td align="center">
		<table border="0" cellpadding="10">
			<tr>
				<td>
					<span class="shortcutkey"><pos:message key="new.customer"/> - Alt-N</span>
				</td>
				<td>
					<span class="shortcutkey"><pos:message key="search"/> - Alt-S</span>
				</td>
				<td>
					<span class="shortcutkey"><pos:message key="continue"/> - Alt-C</span>
				</td>				
								
			</tr>
		</table> 		   	 	  	
	</td>		   		
</tr>	  	
</html:form>


<script>
//function declaration
function getPaymentDetails()
{
	setAction(document.forms[0],'GetPOSPaymentDetailsAction.do','getPOSPaymentDetails');
}

function createCustomer()
{
	window.location = "InitCreatePOSCustomer.do?action=initCreatePOSCustomer";
}

function getExistingCustomers()
{
	setAction(document.forms[0],'GetExistingPOSCustomersAction.do','getExistingPOSCustomers');
}

$('customerQuery').focus();
</script>
       		
<%@ include file="/jsp/include/posFooter.jsp" %>	
