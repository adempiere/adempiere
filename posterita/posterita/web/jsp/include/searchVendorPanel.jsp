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



						<input type="text" style="width:100%" id="customerQuery" accesskey="s"/>
						<div id="customerSearchResult" class="autocomplete"></div>
						<script>						
							new Ajax.Autocompleter('customerQuery','customerSearchResult','SearchVendorAction.do',{
							paramName:'customerQuery',
							frequency:TROTTLE_TIME,
							afterUpdateElement:function(e1,e2){
												var bpartner = document.getElementsByName('bpartnerId')[0];
												var vendorName = document.getElementsByName('partnerName')[0];
												if(e2.value != '-1'){
													bpartner.value = e2.value;
													vendorName.value = e2.getAttribute('name');
													
													document.forms[0].submit();
												}else{
													bpartner.value = "";
													vendorName.value = "";
												}//if
																																													
												}//function												
							});
						</script>