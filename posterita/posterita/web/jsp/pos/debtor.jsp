<%--
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2008  Posterita Ltd
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
--%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>	
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>
<%@page import="org.compiere.model.MBPartner"%>
<%@page import="org.posterita.core.TmkJSPEnv"%>
<%@page import="java.util.Properties"%>

<%@ include file="/jsp/include/posHeader.jsp" %>
<!-- page contents -->
<!-- <div id="indicator"><img src="images/pos/indicator.gif"/> Please wait...</div> -->
<table width="100%" border="1" cellspacing="0" cellpadding="0" height="400px">
  <tr>
    <td width="75%" valign="top">
    <div style="padding: 4px;border-bottom: solid #000 1px">
    	<h2><pos:message key="debtor"/></h2>
    	<input type="text" id="customerQuery" style="width:400px;border:solid #000 1px"/>
	    <div id="customerSearchResult" class="autocomplete"></div>
	    <input type="text" name="bpartnerId" id="bpartnerId"/>	    
	</div>
    </td>
    <td width="25%" valign="top">
    <div style="padding: 4px">
    	&nbsp;
    </div>
    </td>
   </tr>
</table>

<script>
var bpartner = $('bpartnerId');
var searchResultHandler = function(e1,e2){
																															
		if(e2.value != '-1')
		{						
			if(e2.value)
			{							
				bpartner.value = e2.value;
			}
			else
			{							
				bpartner.value = '';
			}					
		}
		else
		{						
			bpartner.value = "";						
		}//if
							
		$('customerQuery').value = "";												
};	

$('customerQuery').Autocompleter = new Ajax.Autocompleter('customerQuery','customerSearchResult','SearchCustomerAction.do',{
paramName:'customerQuery',
frequency:TROTTLE_TIME,
afterUpdateElement:searchResultHandler});
</script>
<%@ include file="/jsp/include/posFooter.jsp" %>