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

<!-- bPartnerDetails.jsp -->
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.*" %>
<%@ page import ="org.posterita.Constants" %>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/posterita.tld" prefix="posterita"%>

<bean:define id="title"><bean:message key="bpartner.details"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %> 

<table width="100%" border="0" cellpadding="5" cellspacing="1" align="center">
			<tr>
				<td> 
					
				</td>
			</tr>
			
			<tr>
				<td>			    			
				    <table width="300" border="0">
		           	   <tr>
   			              <td no wrap">	
   			            	<bean:message key="bpartner.name"/>
   			              </td>
   			              <td>
								<bean:write name="<%=Constants.BUSINESSPARTNERSDETAILS%>" property="partnerName"/>
								&nbsp;<bean:write name="<%=Constants.BUSINESSPARTNERSDETAILS%>" property="name2"/>
						  </td>
					   </tr>
					   
					   <tr>
   			              <td no wrap">	
   			            	<bean:message key="bPartner.address1"/>
   			              </td>
   			              <td>
								<bean:write name="<%=Constants.BUSINESSPARTNERSDETAILS%>" property="address1"/>
						  </td>
					   </tr>
					   
					   <tr>
   			              <td no wrap">	
   			            	<bean:message key="bPartner.address2"/>
   			              </td>
   			              <td>
								<bean:write name="<%=Constants.BUSINESSPARTNERSDETAILS%>" property="address2"/>
						  </td>
					   </tr>					   
					   
					   <tr>
   			              <td no wrap">	
   			            	<bean:message key="city"/>
   			              </td>
   			              <td>
								<bean:write name="<%=Constants.BUSINESSPARTNERSDETAILS%>" property="city"/>
						  </td>
					   </tr>
					   
					   <tr>
   			              <td no wrap">	
   			            	<bean:message key="postaladdress"/>
   			              </td>
   			              <td>
								<bean:write name="<%=Constants.BUSINESSPARTNERSDETAILS%>" property="postalAddress"/>
						  </td>
					   </tr>
					   
					   <tr>
   			              <td no wrap">	
   			            		<bean:message key="bPartner.phone"/>:
   			              </td>
   			              <td>
								<bean:write name="<%=Constants.BUSINESSPARTNERSDETAILS%>" property="phone"/>
						  </td>
					   </tr>
					   
					   <tr>
   			              <td no wrap">	
   			            		<bean:message key="bPartner.fax"/>:
   			              </td>
   			              <td>
								<bean:write name="<%=Constants.BUSINESSPARTNERSDETAILS%>" property="fax"/>
						  </td>
					   </tr>
					   
					 <tr>
						  <td><bean:message key="bPartner.isActive"/></td>
						    <td>
							   <logic:equal name="<%=Constants.BUSINESSPARTNERSDETAILS%>" property="isActive" value="true"> 
								Yes
							   </logic:equal>
							   <logic:equal name="<%=Constants.BUSINESSPARTNERSDETAILS%>" property="isActive" value="false"> 
								No
							   </logic:equal>						
						    </td>
						 </tr>
						 
						 <tr>
						  <td><bean:message key="bPartner.isCustomer"/></td>
						    <td>
							   <logic:equal name="<%=Constants.BUSINESSPARTNERSDETAILS%>" property="isCustomer" value="true"> 
								Yes
							   </logic:equal>
							   <logic:equal name="<%=Constants.BUSINESSPARTNERSDETAILS%>" property="isCustomer" value="false"> 
								No
							   </logic:equal>						
						    </td>
						 </tr>
						 
						 <tr>
						  <td><bean:message key="bPartner.isEmployee"/></td>
						    <td>
							   <logic:equal name="<%=Constants.BUSINESSPARTNERSDETAILS%>" property="isEmployee" value="true"> 
								Yes
							   </logic:equal>
							   <logic:equal name="<%=Constants.BUSINESSPARTNERSDETAILS%>" property="isEmployee" value="false"> 
								No
							   </logic:equal>						
						    </td>
						 </tr>
						 
						 <tr>
						  <td><bean:message key="bPartner.isVendor"/></td>
						    <td>
							   <logic:equal name="<%=Constants.BUSINESSPARTNERSDETAILS%>" property="isVendor" value="true"> 
								Yes
							   </logic:equal>
							   <logic:equal name="<%=Constants.BUSINESSPARTNERSDETAILS%>" property="isVendor" value="false"> 
								No
							   </logic:equal>						
						    </td>
						 </tr>
						 
						 <tr>
						  <td><bean:message key="bPartner.isSalesRep"/></td>
						    <td>
							   <logic:equal name="<%=Constants.BUSINESSPARTNERSDETAILS%>" property="isSalesRep" value="true"> 
								Yes
							   </logic:equal>
							   <logic:equal name="<%=Constants.BUSINESSPARTNERSDETAILS%>" property="isSalesRep" value="false"> 
								No
							   </logic:equal>						
						    </td>
						 </tr>
			   			
				  </table>
				  <table width="100%">
					<td align="right">
											
					</td>
				  </table>
				</td>
			</tr>		
	</table>
	
	    									 					 
<%@ include file="/jsp/include/posFooter.jsp" %>