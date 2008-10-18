<%@ page import="org.posterita.util.TmkPrinterConstants" %>
<%--
 *  Product: Posterita Web-Based POS (an Adempiere Plugin)
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
 * @author Praveen Beekoo
--%>



<!-- viewPreferences.jsp --> 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<bean:define id="title"><pos:message key="smenu.preferences" textOnly="true"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>



<div style="width:40%">
<form id="preferences">
<div class="space"></div>
<div>
	<fieldset>
	
	<legend><pos:message key="search.results" textOnly="true"/></legend>
	
	<div>
	<pos:message key="display" textOnly="true"/> 
	<select id="display" name="display">
		<option value="10">10</option>
		<option value="20">20</option>
		<option value="50">50</option>
		<option value="100">100</option>
	</select>
	<pos:message key="records.per.page"/>.
	
	</div>
	<br/>
	
	<div>	
	Minimum characters before searching starts
		<input id="minChars" name="minChars" type="text" maxlength="1" value="1">
	</div>
	
	</fieldset>
</div>

<div class="space"></div>
<div>
	<fieldset>
	<legend><pos:message key="preference.theme"/></legend>
	<pos:message key="select" /> : 
	<select id="theme" name="theme">
		<option value="default"><pos:message key="default" textOnly="true"/></option>
	</select>
	</fieldset>
</div>
<div class="space"></div>
<div>
	<fieldset>
	<legend><pos:message key="remote.printing" /> </legend>
	<pos:message key="enable.printing" />
	<div>
	<logic:present cookie="preference.printing">
	<logic:equal cookie="preference.printing" property="value" value="true">
		<pos:message key="yes" /><input type="radio" name="printing" value="true" checked="checked">	
		<pos:message key="no" /><input type="radio" name="printing" value="false">
	</logic:equal>
	<logic:notEqual cookie="preference.printing" property="value" value="true">
		<pos:message key="yes" /><input type="radio" name="printing" value="true">	
		<pos:message key="no" /><input type="radio" name="printing" value="false" checked="checked">
	</logic:notEqual>
	</logic:present>
	<logic:notPresent cookie="preference.printing">
		<pos:message key="yes" /><input type="radio" name="printing" value="true">	
		<pos:message key="no" /><input type="radio" name="printing" value="false" checked="checked">
	</logic:notPresent>
	</div>
	<br />
	<pos:message key="enable.cashdrawer" />
	<div>
	<logic:present cookie="preference.cashdrawer">
	<logic:equal cookie="preference.cashdrawer" property="value" value="true">
		<pos:message key="yes" /><input type="radio" name="cashdrawer" value="true" checked="checked">	
		<pos:message key="no" /><input type="radio" name="cashdrawer" value="false">
	</logic:equal>
	<logic:notEqual cookie="preference.cashdrawer" property="value" value="true">
		<pos:message key="yes" /><input type="radio" name="cashdrawer" value="true">	
		<pos:message key="no" /><input type="radio" name="cashdrawer" value="false" checked="checked">
	</logic:notEqual>
	</logic:present>
	<logic:notPresent cookie="preference.cashdrawer">
		<pos:message key="yes" /><input type="radio" name="cashdrawer" value="true">	
		<pos:message key="no" /><input type="radio" name="cashdrawer" value="false" checked="checked">
	</logic:notPresent>
	
	</div>
	<div class="space"></div>
	<pos:message key="printer.name" />: 
		<applet archive="<%= basePath + "applets/printOrderApplet.jar"%>" code="org.posterita.core.PrintOrderApplet.class" width="0" height="0"></applet>
		<select id="printer" width="150">
			<option value="default">Default</option>
		</select>
	<div class="space"></div>
	<pos:message key="printer.type"/>
		<select id="printerType">
			<%
				out.print("<option value=\""+ TmkPrinterConstants.SLIP_PRINTER_9PIN + "\"");
				out.print("selected=\"selected\"");
				out.print(">" + TmkPrinterConstants.SLIP_PRINTER_9PIN + "</option>");
				out.print("<option value=\""+ TmkPrinterConstants.SLIP_PRINTER_THERMAL + "\"");
				out.print(">"+ TmkPrinterConstants.SLIP_PRINTER_THERMAL +"</option>");
				out.print("<option value=\""+ TmkPrinterConstants.NORMAL_PRINTER + "\"");
				out.print(">"+ TmkPrinterConstants.NORMAL_PRINTER +"</option>");
			%>
		</select>
	</fieldset>
</div>
<div class="space"></div>
<div align="right">
	<input id="savebtn" type="button" class="newbutton" value="<pos:message key='save' textOnly='true'/>">		
</div>
</form>	
</div>		
<%@ include file="/jsp/include/posFooter.jsp" %>
<script>
var display = null;
var theme = null;
var printing = null;
var printer = null;
var printerType = null;
var minChars = null;

function initForm()
{
	var opts = $('display').options;
	for(var i=0;i<opts.length;i++)
	{
		if(display == opts[i].value){
			opts[i].selected = 'selected';
			break;
		}
	}
	
	opts = $('theme').options;
	for(var j=0;j<opts.length;j++)
	{
		if(theme == opts[j].value){
			opts[i].selected = 'selected';
			break;
		}
	}
	
	opts = $('printerType').options;
	for(var k=0;k<opts.length;k++)
	{
		if(printerType == opts[k].value){
			opts[k].selected = 'selected';
			break;
		}
	}		
	
	var printerOptions = "";
	
	try
	{
		var printers = document.applets[0].getPrintersForJS();
		var printerList = printers.split("###");
		for (i = 0; i < printerList.length; i++)
		{	
			printerOptions += "<option value='" + printerList[i] + "' ";
			
			if (printerList[i] == printer)
			{
				printerOptions += "selected='selected'";
			}
			printerOptions += ">" + printerList[i] + "</option>";
		}
	}
	catch (e)
	{
		printerOptions = "<option value='error'>Error</option>";
	}
	
	$('printer').innerHTML = printerOptions;
	$('minChars').value = minChars;	
}
	
function loadPreferences()
{
	display = readCookie('preference.display');
	theme = readCookie('preference.display');
	printing = readCookie('preference.printing');
	printer = readCookie('preference.printer');	
	printerType = readCookie('preference.printerType');
	cashdrawer = readCookie('preference.cashdrawer');
	minChars = readCookie('preference.minChars');
	
	toConsole('Printer Type:' + printerType);	
	
	
	display = (display)?display:'10';
	theme = (theme)?theme:'default';
	printing = (printing)?printing:'false';
	printer = (printer)?printer:'default';
	printerType = (printerType)?printerType: '<%=TmkPrinterConstants.SLIP_PRINTER_9PIN%>';
	cashdrawer = (cashdrawer)?cashdrawer:'false';
	minChars = (minChars)?minChars:'1';	
}

function getCheckedElementValue(name)
{
	var retval;
	var elements = document.getElementsByName(name);
	for(var i=0;i<elements.length;i++){
		if(elements[i].checked)
		{
			retval = elements[i].value;
			break;
		}
	}
	
	return retval;
}

function savePreferences()
{
	//------------------------------
	display 	= Form.Element.getValue('display');
	theme 		= Form.Element.getValue('theme');
	printer 	= Form.Element.getValue('printer');
	printerType = Form.Element.getValue('printerType');
	minChars 	= Form.Element.getValue('minChars');	
	
	printing = getCheckedElementValue('printing');
	cashdrawer = getCheckedElementValue('cashdrawer');
	
	createCookie('preference.display'	,display	,3650);
	createCookie('preference.theme'		,theme		,3650);
	createCookie('preference.printing'	,printing	,3650);
	createCookie('preference.printer'	,printer	,3650);	
	createCookie('preference.printerType'	,printerType	,3650);	
	createCookie('preference.cashdrawer'	,cashdrawer	,3650);	
	createCookie('preference.minChars'	,minChars	,3650);	
	
	alert('Preferences saved');
}



function init()
{
	$('savebtn').onclick = savePreferences;
	loadPreferences();
	initForm();		
}

Event.observe(window,'load',init,false);
</script> 