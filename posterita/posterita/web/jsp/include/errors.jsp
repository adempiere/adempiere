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
	<div align="left">
		<logic:messagesPresent>
		<div class="spacer"></div>  
		<div id="errorbox">
		<html:messages id="error">
		<div class="errormsg">
			<bean:write name="error" filter="false"/>
		</div>		
		</html:messages>
		</div>
		<div class="space"></div>
		</logic:messagesPresent>
		
		<logic:messagesPresent message="true">
		<div class="spacer"></div>  
		<div id="msgbox">
		<html:messages id="msg" message="true">
			<div><bean:write name="msg" filter="false"/></div>
		</html:messages> 
		</div>
		<div class="spacer"></div>  
	    </logic:messagesPresent>
    </div>

