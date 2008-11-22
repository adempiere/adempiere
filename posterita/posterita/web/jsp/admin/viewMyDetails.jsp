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
 * @author tamak
--%>

<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.UDIBean" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>


<tiles:insert page="/jsp/include/headerTableTop.jsp">
  	<tiles:put name="title"><bean:message key="admin.viewMyDetails.title"/></tiles:put>
</tiles:insert>

		<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
			<tr>
				<td colspan=2>
					<%@ include file="/jsp/include/tabTop.jsp" %><bean:message key="admin.viewMyDetails.title"/><%@ include file="/jsp/include/tabBottom.jsp" %>
				</td>
			</tr>
			
			<tr><td valign="bottom" align="right" bgcolor="#F6F6F6" width="40%"><img src="images/man2_lr.jpg"> </td>
				<td bgcolor="#F6F6F6" align="left">			    			
				    <table width="250" height="250" cellpadding="2"  cellspacing="0" border="0" align="left">
				    	<tr bgcolor="#ECEAEA">
				    		<td>
				    			<font class="bolddarkgraysmall"> <bean:message key="user.username"/> </font> 
							</td>			    			
						    <td>
						    	<c:out value='${webUserInfo.user.name}'/>						    						    				    			
						    </td>			    			
						</tr>
						<tr>
				    		<td>
				    			<font class="bolddarkgraysmall"> <bean:message key="user.role"/> </font> 
							</td>			    			
						    <td >
						    	<bean:write name="webUserInfo" property="roleName"/>						    						    				    			
						    </td>			    			
						</tr>						    		
						<tr bgcolor="#ECEAEA">	    			  			
						    <td>
						    	<font class="bolddarkgraysmall"> <bean:message key="admin.viewMyDetails.email"/> </font>
						    </td>
						    <td>
						    	<c:out value='${webUserInfo.user.email}'/>
						    </td>			    			
						</tr>
						    		
						<tr>	
							<td>	  
								<font class="bolddarkgraysmall"><bean:message key="admin.viewMyDetails.dealerName"/> </font>
							</td>
						    <td>
								<c:out value ='${webUserInfo.orgName}'/>		
							</td>					  	   
						</tr>
								  	
						<tr bgcolor="#ECEAEA">	
							<td> 		  
								<font class="bolddarkgraysmall"><bean:message key="admin.viewMyDetails.dealerCode"/></font>
							</td>					  	    
							<td>
								<c:out value='${webUserInfo.location.address4}'/>
							</td>
						</tr>
								  	
						<tr>	
							<td>		  
								<font class="bolddarkgraysmall"> <bean:message key="admin.viewMyDetails.phone"/> </font>
							</td>					  	    
							<td>
								<c:out value='${webUserInfo.user.phone}'/>
							</td>
						</tr>
						
						<tr bgcolor="#ECEAEA">	
							<td> 		  
								<font class="bolddarkgraysmall"> <bean:message key="user.cellphone"/></font>
							</td>					  	    
							<td>
								<c:out value='${webUserInfo.user.phone2}'/>
							</td>
						</tr>
								  	
						<tr>				
							<td>		  
								<font class="bolddarkgraysmall"><bean:message key="admin.viewMyDetails.fax"/></font>
							</td>				  	    
							<td>
								<c:out value='${webUserInfo.user.fax}'/>
							</td>
						</tr>
								  	    
						<tr bgcolor="#ECEAEA">	
							<td valign="top" nowrap> <br>		  
								<font class="bolddarkgraysmall"><bean:message key="admin.viewMyDetails.deliveryAddress"/></font>
							</td>					  	    
							<td valign="top"> <br>
								<c:out value='${webUserInfo.location.address1}'/><br>
								<c:out value='${webUserInfo.location.city}'/><br>
								<c:out value='${webUserInfo.location.country}'/>
							</td>
						</tr>
								  	
					</table>					
				</td>			  	    
		   	</tr>
	   	</table>
		      
		   		   
		<%@ include file="/jsp/include/footerTableBottom.jsp" %>				