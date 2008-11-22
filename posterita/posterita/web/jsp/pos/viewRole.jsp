<%--
 *  Product: Posterita Web-Based POS (an Adempiere Plugin)
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
 * @author Praveen Beekoo
--%>


<!--viewRole.jsp-->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.compiere.model.MOrg" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<bean:define id="title"><pos:message key="view.role" textOnly="true"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
									
	<table class="display">
	<tr>
		<td><strong><pos:message key="Name"/> :</strong><bean:write name="<%= Constants.ROLE %>" property="name"/></td>
	</tr>
	<tr>
		<td><strong><pos:message key="discount.upto.price.limit"/> :</strong><bean:write name="<%= Constants.ROLE %>" property="isDiscountUptoLimitPrice"/></td>
	</tr>
	<tr>
		<td><strong><pos:message key="over.ride.limit.price"/> :</strong><bean:write name="<%= Constants.ROLE %>" property="isOverwritePriceLimit"/></td>
	</tr>
	<tr>
		<td><strong><pos:message key="userDiscount"/> :</strong><bean:write name="<%= Constants.ROLE %>" property="userDiscount"/></td>
	</tr>
	<tr>
		<td><strong><pos:message key="discount.allowed.total"/> :</strong><bean:write name="<%= Constants.ROLE %>" property="isDiscountAllowedOnTotal"/></td>
	</tr>
	<tr>
		<td><hr></td>					
	</tr>
	<tr>
		<td><strong><pos:message key="menus"/></strong></td>					
	</tr>
	<tr>
    	<td>
			<logic:present name="<%= Constants.ROLE_MENUS%>">
				<table class="display">
					<logic:iterate id="element" indexId="count" name="<%= Constants.ROLE_MENUS%>" type="org.posterita.core.MenuItem">
		   				<tr>
		   					<td class="contentName">
	   							<strong><pos:message key="<%=element.getName()%>"  textOnly="true"/></strong>
	   						</td>
	   					</tr>
   						<logic:equal name="element" property="hasSubMenu" value="true">
   							<logic:iterate id="ele" collection="<%=element.getSubMenus()%>" type="org.posterita.core.MenuItem">
							<tr>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<pos:message key="<%=ele.getName()%>"  textOnly="true"/>
								</td>
							</tr>
							</logic:iterate>  						
	   						
   						</logic:equal>
	   											   		
		   			</logic:iterate>
			   	</table>
			</logic:present>
   		</td>
	</tr>		
	</table>
						
<%@ include file="/jsp/include/posFooter.jsp" %>
	   
	
												