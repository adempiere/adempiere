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
<!-- bPartnerCreated.jsp -->
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.*" %>
<%@ page import ="org.posterita.Constants" %>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/posterita.tld" prefix="posterita"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title"><pos:message key="C_BPartner_ID" textOnly="true"/></bean:define>
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
   			            	<pos:message key="Name"/>
   			              </td>
   			              <td>
								<bean:write name="<%=Constants.BUSINESSPARTNER%>" property="partnerName"/>
								&nbsp;<bean:write name="<%=Constants.BUSINESSPARTNER%>" property="name2"/>
						  </td>
					   </tr>
					   
					   <tr>
   			              <td no wrap">	
   			            	<pos:message key="address1" />
   			              </td>
   			              <td>
								<bean:write name="<%=Constants.BUSINESSPARTNER%>" property="address1"/>
						  </td>
					   </tr>

					   <tr>
   			              <td no wrap">	
   			            	<pos:message key="address2" />
   			              </td>
   			              <td>
								<bean:write name="<%=Constants.BUSINESSPARTNER%>" property="address2"/>
						  </td>
					   </tr>
					   					   
					   <tr>
   			              <td no wrap">	
   			            	<pos:message key="city" />
   			              </td>
   			              <td>
								<bean:write name="<%=Constants.BUSINESSPARTNER%>" property="city"/>
						  </td>
					   </tr>
					   
					   <tr>
   			              <td no wrap">	
   			            	<pos:message key="postalAddr" />
   			              </td>
   			              <td>
								<bean:write name="<%=Constants.BUSINESSPARTNER%>" property="postalAddress"/>
						  </td>
					   </tr>
					   
					   <tr>
   			              <td no wrap">	
   			            	<pos:message key="phone" />
   			              </td>
   			              <td>
								<bean:write name="<%=Constants.BUSINESSPARTNER%>" property="phone"/>
						  </td>
					   </tr>
					   
					   <tr>
   			              <td no wrap">	
   			            	<pos:message key="fax" />
   			              </td>
   			              <td>
								<bean:write name="<%=Constants.BUSINESSPARTNER%>" property="fax"/>
						  </td>
					   </tr>
					   
					   <tr>
				  			<td><pos:message key="isCustomer" /></td> 
							<td no wrap>
								<logic:equal name="<%=Constants.BUSINESSPARTNER%>" property="isCustomer" value="true"> 
								Yes
								</logic:equal>
								<logic:equal name="<%=Constants.BUSINESSPARTNER%>" property="isCustomer" value="false"> 
								No
								</logic:equal>						
				    		</td>
			   			</tr>
			   			
			   			<tr>
				  			<td><pos:message key="isVendor" /></td> 
							<td no wrap>
								<logic:equal name="<%=Constants.BUSINESSPARTNER%>" property="isVendor" value="true"> 
								Yes
								</logic:equal>
								<logic:equal name="<%=Constants.BUSINESSPARTNER%>" property="isVendor" value="false"> 
								No
								</logic:equal>						
				    		</td>
			   			</tr>
			   			
			   			<tr>
				  			<td><pos:message key="isEmployee" /></td> 
							<td no wrap>
								<logic:equal name="<%=Constants.BUSINESSPARTNER%>" property="isEmployee" value="true"> 
								Yes
								</logic:equal>
								<logic:equal name="<%=Constants.BUSINESSPARTNER%>" property="isEmployee" value="false"> 
								No
								</logic:equal>						
				    		</td>
			   			</tr>
			   			
			   			<tr>
				  			<td><pos:message key="isSalesRep" /></td> 
							<td no wrap> 
								<logic:equal name="<%=Constants.BUSINESSPARTNER%>" property="isSalesRep" value="true"> 
								Yes
								</logic:equal>
								<logic:equal name="<%=Constants.BUSINESSPARTNER%>" property="isSalesRep" value="false"> 
								No
								</logic:equal>						
				    		</td>
			   			</tr>
			   			<%--
			   			<tr>
						    <td>
						        <bean:message key="region"/>
						    </td>
						    <td no wrap>
						        <posterita:regionName name="<%=Constants.BUSINESSPARTNER%>" scope="session" property="regionId"/>
						    </td>
						 </tr>
						--%>
				  </table>
				</td>
			</tr>		
	</table>
	
	    									 					 
<%@ include file="/jsp/include/posFooter.jsp" %>