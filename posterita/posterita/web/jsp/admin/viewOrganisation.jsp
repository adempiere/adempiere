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
 *  @author sendy
--%>

<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/errors.jsp" %>
<!-- page contents -->
<div class="scrollpane">
	<table class="display sortable" border="1" width="900px" >
		<tr>
			<th class="string"><span title="Name" name="help" tooltip="Name">Organisation Name</span></th>
		    <th class="string" nowrap="nowrap"><span title="Address" name="address" tooltip="Address">Address</span></th>
	   	    <th class="string" nowrap="nowrap"><span title="Active" name="help" tooltip="Active">Active</span></th>   
	   	    <th nowrap="nowrap"><span title="Update Details " name="help" tooltip="Update Details ">Update Details </span></th>
		</tr> 	
	
		<logic:present  name="<%=Constants.ORG_ID %>" >
		<logic:iterate id="org" name="<%=Constants.ORG_ID %>" type="org.posterita.beans.OrgBean">
		<tr>
			<td class = "label">
				<bean:write name="org" property="orgName"/>
			</td>
			<td class = "label">
				<bean:write name="org" property="address1"/>
				<bean:write name="org" property="address2"/>
			</td>
			<td class = "label">
				<bean:write name="org" property="isActive"/>
			</td>
			<td  align="right" class = "label">
				<html:link href="">
					<img src="images/tango/accessories-text-editor.png"  border="0"> 
				</html:link>
				<html:link href="">
					<img src="images/tango/edit-find.png"  border="0"> 
				</html:link>
				<html:link href="">
					<img src="images/tango/edit-redo.png"  border="0"> 
				</html:link>			
				<html:link href="">
					<img src="images/tango/edit-undo.png"  border="0"> 
				</html:link>
		 	</td>	 	
		</tr>
		</logic:iterate>
		</logic:present>
	</table>
</div>
<%@ include file="/jsp/include/posFooter.jsp" %>