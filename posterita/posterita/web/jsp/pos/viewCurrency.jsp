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
 * @author Servansingh 
--%>



<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.beans.CurrencyBean" %>
<%@ page import="org.compiere.model.MOrg" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<bean:define id="title"><pos:message key="view.role" textOnly="true"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp"%>
		
			<logic:present name="<%= Constants.CURRENCY%>">
				<table class="display">
					<tr>
	   					<td class="contentName">
   							<strong><pos:message property="isoCode" key="isoCode" textOnly="true"/></strong>
   						 <pos:message key="ISO Code"></pos:message>
   							<html:text property="isoCode"></html:text>
   						</td>
   						<td class="contentName">
   							<strong><pos:message property="isoCode" key="isoCode" textOnly="true"/></strong>
   						 
   						</td>
   					</tr>
   					<tr>
	   					<td class="contentName">
   							<strong><pos:message property="curSymbol" key="" textOnly="true"/></strong>
   						</td>
   					</tr>
   					<tr>
	   					<td class="contentName">
   							<strong><pos:message property="description"  textOnly="true"/></strong>
   						</td>
   					</tr>
   					<tr>
	   					<td class="contentName">
   							<strong><pos:message property="stdPrecision"  textOnly="true"/></strong>
   						</td>
   					</tr>
   					<tr>
	   					<td class="contentName">
   							<strong><pos:message property="roundOffFactor"  textOnly="true"/></strong>
   						</td>
   					</tr>
   				</table>
			</logic:present>
   							
<%@ include file="/jsp/include/posFooter.jsp" %>
	   
	
												