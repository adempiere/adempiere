<!--createAPPayment.jsp-->
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

<!-- createPayment.jsp -->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.struts.pos.PaymentAction" %>
<%@ page import="org.compiere.model.MProduct" %>
<%@ page import="org.posterita.beans.ProductBean" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="org.posterita.lib.UdiConstants" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title"><pos:message textOnly="true" key="smenu.create.unallocated.payment.id"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 
						   	
<html:form action="/CreateUnallocatedAPPaymentAction">
<html:hidden property="action" value="<%=PaymentAction.CREATE_PAYMENT%>"/>
<html:hidden property="isSoTrx" value="false"/>

<table class="view">
	<tr>		
		<td colspan="2">
			<fieldset>
			<label><pos:message key="Amount"/></label>
			<html:text property="amount"/>
			</fieldset>
		</td>		
	</tr>	
	<tr>
		<td>
			<%@ include file="/jsp/include/vendorInfoPanel.jsp" %>
		</td>			
		<td>
			<%@ include file="/jsp/include/tenderPanel.jsp" %>
		</td>		
	</tr>	
	<tr>	
	 <td colspan="2" align="right">
		<html:submit styleClass="save smallbutton">
			&nbsp;
		</html:submit>	
	 </td>
	 
	</tr>  	
</table>
</html:form> 	
<script>
function createCustomer()
	{
		window.location = "InitCreatePOSCustomer.do?action=initCreatePOSCustomer&creatingFromOrder=createPayment";
	}
</script>						
<%@ include file="/jsp/include/posFooter.jsp" %>
