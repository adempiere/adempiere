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


<!-getCustomerReturnFormPOSOrder.jsp->
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.Constants" %>


<%@ page import="org.posterita.struts.pos.POSGoodsAction" %>
<%@ page import="org.posterita.beans.ProductBean" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="org.posterita.lib.UdiConstants" %>
<%@ page import="org.compiere.model.MOrder" %>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/dcs.tld" prefix="dcs" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<bean:define id="title"><pos:message textOnly="true" key="smenu.invoke.customer.returned.order"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>   
<%@ include file="/jsp/include/errors.jsp" %> 
	  						   	
<html:form action="/GetCustomerReturnFromPOSOrderAction" focus="documentNo">
<html:hidden property="action" value="createCustomerReturnFromPOSOrder"/>

<table class="main">			
<tr>
	<td>
		<table class="view">
		<tr>	
			<td>
				<label><pos:message key="DocumentNo"/>:</label>  			
			</td>
			<td>
				<html:text property="documentNo" styleClass="text"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<html:submit accesskey="i" styleClass="blank smallbutton"><pos:message key="invoke" textOnly="true"/></html:submit>
			</td>
		</tr>	
		</table>
	</td>
</tr>	
<!--  
<tr>
	<td>&nbsp;</td>
</tr>					
<tr>					  
	<td align="center">				
		<span>
		<span class="shortcutkey">
			<label>Invoke-</label>
			<label class="red">Alt-I</label>
		</span>			
		</span>	
	</td>		   		
</tr>
-->	
</table>
</html:form>


<script>		
	$focus('orderId');
</script>   
 
<%@ include file="/jsp/include/posFooter.jsp" %>      
   
