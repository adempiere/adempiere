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
 * @author Sendy
--%>					

<%@page import="org.posterita.Constants"%>
<% 
	String minChars = "1";	
%>					


<logic:present cookie="preference.minChars">
	<bean:cookie id="minCharsCookie" name="preference.minChars"/>
	<% 
		if (minCharsCookie.getValue() != null || !minCharsCookie.getValue().equals(""))
			minChars = minCharsCookie.getValue();
	%>					
</logic:present>




						
						<input type="text" class="text searchBox" style="width:60%" id="productQuery" accesskey="p" />
						<div id="productSearchResult" class="autocomplete"></div>
						
						<script>
						function convertString2Unicode(s)
						{
							 var uniString = "", hexVal, uniChar;
							
							 for(var i = 0; i < s.length; ++i)
							 {
								 //Convert char to hex
								 //hexVal = Number(s.charCodeAt(i)).toString(16);
								 hexVal = Number(s.charCodeAt(i)).toString(10);
								
								 //Convert to unicode by making sure hex is 4 chars long, padding with 0's if less
								 //uniChar = "\\u" + ("000" + hexVal).match(/.{4}$/)[0];
								 uniChar = "&#" + ("000" + hexVal).match(/.{4}$/)[0] +";";
								
								 uniString += uniChar;
							 }
							if(hexVal < 255) 
								return s;
								
						 	return uniString;
						}						
						
						
							var productAutocompleter = new Ajax.Autocompleter('productQuery','productSearchResult','SearchProductsInStockAction.do',{								
								paramName:'productName',
								minChars:<%=minChars%>,								
								frequency:1.0,
								afterUpdateElement:function(e1,e2){													
												
													var barcode = e2.getAttribute('barcode');
													if(barcode=='null')
													{
														barcode = "";
													}
													var productId = e2.getAttribute('productId');													
													document.getElementsByName('barCode')[0].value = barcode;
													document.getElementsByName('productId')[0].value= productId;
													
													addToCart();													
																																									
													},
								});
								
							$('productQuery').Autocompleter = productAutocompleter;
						</script>					