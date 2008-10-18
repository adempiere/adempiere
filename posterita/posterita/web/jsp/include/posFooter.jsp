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
								
</div>
<!-- end of page content -->
<!-- start of page footer -->		
<div id="footerContainer">
	<table width="100%" cellspacing="0px" cellpadding="0px">
		<tr>
			<td width="100%">
				<div class="footer">
					<div class="floatLeft">
						<pos:message textOnly="true" key="footer.copyright"/>
						Posterita <%=java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)%>
						<b><pos:message key="pos.version" textOnly="true"/>&nbsp;1.7.3</b>
					</div>
		 		</div>
			<td>
			<!-- 
			<td width="25%">
				<div class="footer">
					<div class="floatRight">
					 	<form action="https://www.paypal.com/cgi-bin/webscr" method="post">
							<input type="hidden" name="cmd" value="_donations">
							<input type="hidden" name="business" value="fredtsang@hotmail.com">
							<input type="hidden" name="item_name" value="Posterita Donation">
							<input type="hidden" name="no_shipping" value="0">
							<input type="hidden" name="no_note" value="1">
							<input type="hidden" name="currency_code" value="USD">
							<input type="hidden" name="tax" value="0">
							<input type="hidden" name="lc" value="US">
							<input type="hidden" name="bn" value="PP-DonationsBF">
							<input type="image" src="https://www.paypal.com/en_GB/i/btn/btn_donate_LG.gif" border="0" name="submit" alt="Make payments with PayPal - it's fast, free and secure!">
							<img alt="" border="0" src="https://www.paypal.com/en_GB/i/scr/pixel.gif" width="1" height="1">
						</form>
					</div>
				</div>
			</td>
			-->
		</tr>
	</table>
</div>
<!-- end of page footer -->	
</body>
</html>
