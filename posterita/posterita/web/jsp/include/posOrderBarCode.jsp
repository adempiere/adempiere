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
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>
   			<tr>
		   		<td>
		   			<label><pos:message key="barcode"/>:</label>
		   		</td>		
		   		<td>
		   			<html:text property="barCode" accesskey="b" styleClass="text"/>
		   			<html:hidden property="productId" value=""/>
		   		</td>
		   		
		   		<td>
		   			<label><pos:message key="Qty"/>:</label>
		   		</td>		
		   		<td>
		   			<html:text property="quantity" accesskey="q" styleClass="text"/>
		   		</td> 
		   		
		   		<%--<td class="buttoncell"  align="right">
		   			<html:button property="btn" styleClass="addtocart bigbutton" onclick="addToCart()" accesskey="a">&nbsp</html:button>		   				   			
				</td>
				--%>  		
			</tr>