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

<%@ page import="org.posterita.Constants" %>

<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>
<table class="view">	
	<tr>
		<td>
			<label><pos:message key="C_Tax_ID"/></label><mandatory>*</mandatory>
		</td>
		
		<td colspan="3">
			<html:text property="taxName" styleClass="text"/>
		</td>
	</tr>
	<tr>		
		<td>
			<label><pos:message key="Rate"/></label><mandatory>*</mandatory>
		</td>
		<td colspan="3">
			<html:text  property="taxRate" styleClass="text" style="width:100%"/>		    
		</td>
	</tr>
	
	<tr>		
		<td>
			<label><pos:message key="Description"/></label>
		</td>
		<td colspan="3">
			<html:text  property="description" styleClass="text" style="width:100%"/>		    
		</td>
	</tr>
	
    <tr>		
		<td>
			<label><pos:message key="IsTaxExempt"/></label>
		</td>
		<td colspan="3">
			 <html:checkbox  property="isTaxExempted" value="true"/>
		</td>
	</tr>
		
	<tr>
		<td>
			<label><pos:message key="organisation.name"/>:</label><mandatory>*</mandatory>
		</td>
	
		<td>
			<html:select property="orgId" styleClass="text">
				<html:options collection="<%=Constants.USER_ORGS%>" property="key" labelProperty="name"/>
		 	</html:select>
		</td>
	</tr>		
	    
	<tr>	
	<td>&nbsp;</td>
	 <td align="right">
		<html:submit styleClass="save smallbutton">
			&nbsp;
		</html:submit>	
	 </td>
	 <td colspan="2">&nbsp;</td>	
	</tr>  	
</table>	
