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

<!-- completedCustomerReturnOrder.jsp -->

<%@ page import="org.posterita.Constants" %>
<%@ page import="org.compiere.model.MOrder" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="java.util.ArrayList" %> 


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/dcs.tld" prefix="dcs" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title"><bean:write name="<%= Constants.ORDER_TITLE %>"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 
<table class="main">
 <bean:define id="currSymbole" name="<%= Constants.CURRENCY_SYMBOLE %>"/>
  	<tr>
  		<td>
  			<table class="main">
				<tr>		
					<td align="right">
						<a href="javascript:showDocumentPDF(<c:out value='${morder._ID}'/>);">
							<img style="cursor:pointer" src="images/ico_printer.gif" border="0"/>
						</a>
					</td>		
				</tr>
			</table>
			
			<div id="printDiv">
				<%@ include file="/jsp/pos/orderHeader.jsp" %> 
				<% String orderlines = Constants.CUSTOMER_RETURN_ORDER_LINES; %>
				<%@ include file="/jsp/pos/completePOSShoppingCart.jsp" %>  			
  			</div>  			
  			
  		<!--
  			<table width="100%" border="0" cellpadding="5" cellspacing="0">
	  			<tr>
					<td align="right">	
						<input type="button" class="newnote bigbutton" accesskey="n" onclick="createNewCustomerReturnNote()"/>
						</form>
					</td>
				</tr>

				
				<tr>					  
					<td align="center">
						<table border="0" cellpadding="10">
							<tr>
								<td>
									<label><pos:message key="new.customer.return.order"/> -</label><label class="red">Alt-N</label>
								</td>														
							</tr>
						</table> 		   	 	  	
					</td>		   		
				</tr>
				
  			</table>  
  		-->				  			
  		</td>
  	</tr>
 </table>
  	
	<script>
	function createNewCustomerReturnNote()
	{
		window.location = "CustomerReturnOrder.do";
	}
	</script>
<%@ include file="/jsp/include/printOrderApplet.jsp" %>
<%@ include file="/jsp/include/posFooter.jsp" %>
