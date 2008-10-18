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
 * @author Vishee
--%>


<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.WebUserInfo" %>
<%@ page import="org.compiere.process.DocumentEngine" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/dcs.tld" prefix="dcs" %>


<bean:define id="title">Shipment</bean:define>

<%@ include file="/jsp/include/posHeader.jsp" %> 


<html:form action="/ChangeMInOutStatusAction" onsubmit="return getAcceptPermission()">	

	<table class="view" width="100%">
        
        <%@ include file="/jsp/include/errors.jsp" %>
        
		<div align="right">
			<a href="javascript:showShipmentDocumentPDF(<c:out value='${minout._ID}'/>);">
				<img style="cursor:pointer" src="images/ico_printer.gif" border="0"/>
			</a>
		</div>    
        
		<%@ include file="/jsp/pos/webDocumentHeader.jsp" %>


  		<tr>
  			<td>
				
				<table width="100%" border="0 cellspacing="0" cellspacing="0" class="orderHeader">
			   		
  		   			<tr>
			   			<td width="50%" class="label">
			  				Shipment Ref No: <bean:write name="<%=Constants.MINOUT%>" property="m_InOut_ID"/>
			  				<br>
			  				Doc Status: <dcs:orderStatus name="<%=Constants.MINOUT%>" property="docStatus"/>
			  				<div class="space"></div>	
			  				<div class="space"></div>					
							Tracking No:
							<logic:equal name="<%=Constants.MINOUT%>" property="docStatus" value="<%=DocumentEngine.STATUS_Drafted%>">
								<html:text name="<%=Constants.MINOUT%>" property="trackingNo"/> 
							</logic:equal>	
							<logic:equal name="<%=Constants.MINOUT%>" property="docStatus" value="<%=DocumentEngine.STATUS_Completed%>">
								<bean:write name="<%=Constants.MINOUT%>" property="trackingNo"/> 
							</logic:equal>					
						</td>
					
			  		</tr>
			   					   					   				   		
			  	</table>
			  </td>			
   		</tr>

  		
		<tr class="white">
  			<td valign="top">
				<table class="cart">
			     				
			  		<tr>
			  		    <th colspan="1" align="left" >Name</th>
					   	<th colspan="1" align="left">Qty</th>
			  		</tr>

	   				<logic:iterate name="<%=Constants.MINOUT_LINES_COLLECTION%>" id="element" type="org.posterita.beans.WebMinOutLineBean">
	   					
	   				<tr>
	   					 <td class="label">
	   						<bean:write name="element" property="productName"/>
	   					</td>	
	   				
	   					<td class="label" width="25%">
	   						<bean:write name="element" property="qtyOrdered"/>
	   					</td>	
	   				</tr>
	   				</logic:iterate>
				   				
	 			</table>
			</td>
		</tr>
		
		<tr>
	    	<td>
				<table width="100%" border="0" cellpadding="5" cellspacing="0">
					<tr>
						<td align="right">
							<table>
								<tr>
									<logic:present name="<%=Constants.SIMPLE_COMMAND%>">
							    	<logic:iterate name="<%=Constants.SIMPLE_COMMAND%>" id="simpleElement" type="java.lang.String">								    	
								    	<td align="right">
											<html:submit property="submit" value="<%=simpleElement%>" styleClass="button"/>
										</td>
							    	</logic:iterate>
							    	</logic:present>
							    	
							    	<logic:present name="<%=Constants.COMPLEX_COMMAND%>">
							    	<logic:iterate name="<%=Constants.COMPLEX_COMMAND%>" id="complexElement" type="java.lang.String">								    	
								    	<td align="right">
											<html:submit property="submit" value="<%=complexElement%>" styleClass="button"/>
										</td>
							    	</logic:iterate>
							    	</logic:present>
							    	
							    	</html:form>		
								</tr>
							</table>	
						</td>	
					</tr>
				</table>	
			</td>
		</tr>
		
	</table>

<%@ include file="/jsp/include/posFooter.jsp" %>
