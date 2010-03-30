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
 * @author Vishee
--%>


		<tr>
			<td>
				<table width="100%" border="0" cellspacing="0" class="orderheader">
					<tr>
			  			<td width=50%>
			  				<table>
			  					<tr>
			  						<td valign="top">
			  							<bean:write name="<%=Constants.WEB_DOCUMENT_HEADER_BEAN%>" property="to"/>
			  						</td>
			  						<td> 
			  							<c:out value='${me.name}'/>
			  							<br>			  						
  										<c:out value='${meLocation.address1}'/>
  										<br>
  										<c:out value='${meLocation.city}'/>
  										<br>
  										<c:out value='${meLocation.country}'/>
  									</td>
  								</tr>
			  				</table>				  				
			  			</td>
			  			
						<td align="right">
							<table class="view">
								<tr>
									<td valign="top">
										
										<bean:define id="partner" name="<%=Constants.YOU%>" type="org.compiere.model.MBPartner"/>

										<% String partnerId = partner.get_ID() + ""; %>
			
										
										<bean:write name="<%=Constants.WEB_DOCUMENT_HEADER_BEAN%>" property="from"/>
									</td>								
									<td width="200px">
										<html:link href="POSCustomerAction.do?action=viewPOSCustomer&bpartnerId=<%= partnerId %>">
										<c:out value='${you.name}'/>
										<c:out value='${you.name2}'/>
										</html:link>
										
										<br>									
  										<c:out value='${youLocation.address1}'/>
  										<br>
  										<c:out value='${youLocation.city}'/>
  										<br>
  										<c:out value='${youLocation.country}'/>
  										
  									</td>
  								</tr>							
  							</table>
			  															
			  			</td>
			  			
			  		</tr>
			  	</table>
			</td>			
  		</tr>