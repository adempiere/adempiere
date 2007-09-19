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

			<table width="55%" align="center">
				<tr>
					<td>
						<center><font class="darkgray"><bean:message key="login.loginForm.message"/></font></center>
					</td>
				</tr>
			</table>
				<table width="270" align="center" cellpadding="3" cellspacing="0" border="0">
			 		<tr>
			 			<td colspan="2" align="center">
							
					  	</td>	
					</tr>
					<tr>
						<td class="bolddarkgray" align="center" width="210"><bean:message key="login.home.username"/></td>
						<td align="right"><html:text property="username"/></td>
					</tr>
					<tr>
						<td class="bolddarkgray" align="center"><bean:message key="login.home.password"/></td>
						<td align="right"><html:password property="password"/></td>
					</tr>

					<tr>
						<td colspan="2" align="right">
							<button type="submit" class="invisibleBtn"> 
								<img src="images/pos/button-login.gif" border="0"/> 
							</button>
						
			     			
			     			
			     		</td>
					 </tr>
				</table>
	    
