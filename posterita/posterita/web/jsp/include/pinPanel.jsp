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

<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>
<div id="divBack"></div>
<div id="PINPanel">
	<table border="0" width="100%" height="100%">
		<tr>
			<td valign="top" align="left">
				<img src="images/tango/system-password.png" >
				<pos:message key="please.enter.pin" textOnly="true"/><br>
			</td>
		</tr>
		<tr>
			<td>
				<input type="password" class="text" id="PIN" style="width:100%">
			</td>
		</tr>
		<tr>
			<td align="right">
			<div id="closeBtn">
				<form>
					<input type="button" value="OK" onclick="validatePIN()">
					<input type="button" value="Cancel" onclick="hidePINPanel()">
				</form>
			</div>
			</td>
		</tr>
	</table>
</div>
