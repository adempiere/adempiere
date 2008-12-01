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
 * @author shameem
--%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<html:form action="/InventoryCartAction" enctype="multipart/form-data" styleId="importForm">
<html:hidden property="action" value="addStockSheets"/>						
<table class="main">
<tr>
	<td align="right">
 	<html:file property="file" styleId="file" styleClass="text"/>
	</td> 
</tr>                      
<tr>
	<td align="right">
	<html:submit styleClass="upbutton floatLeft" styleId="importCsvBtn">
		IMPORT
	</html:submit>
	</td>
</tr>	
</table>	
</html:form>

<table class="display" border="1"> 
  <tr>
    <th><pos:message key="barcode"/></th>
    <th><pos:message key="qty"/></th>
  </tr>
  <tr>
    <td>6090018000250</td>
    <td>25</td>
  </tr>
  <tr>
    <td>7714739680121</td>
    <td>20</td>
  </tr>
</table>