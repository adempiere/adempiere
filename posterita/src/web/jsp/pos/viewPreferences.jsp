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

<bean:define id="title"><pos:element columnName="smenu.preferences" textOnly="true"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %>



<div style="width:40%">
<form id="preferences">
<div class="space"></div>
<div>
	<fieldset>
	
	<legend><pos:element columnName="search.results" textOnly="true"/></legend>
	<pos:element columnName="display" textOnly="true"/> 
	<select id="display" name="display">
		<option value="10">10</option>
		<option value="20">20</option>
		<option value="50">50</option>
		<option value="100">100</option>
	</select>
	<pos:element columnName="records.per.page"/>.
	</fieldset>
</div>
<div class="space"></div>
<div>
	<fieldset>
	<legend><pos:element columnName="preference.theme"/></legend>
	<pos:element columnName="select" /> : 
	<select id="theme" name="theme">
		<option value="default"><pos:element columnName="default" textOnly="true"/></option>
	</select>
	</fieldset>
</div>
<div class="space"></div>
<div>
	<fieldset>
	<legend><pos:element columnName="remote.printing" /> </legend>
	<pos:element columnName="enable.printing" />
	<div>
	<logic:present cookie="preference.printing">
	<logic:equal cookie="preference.printing" property="value" value="true">
		<pos:element columnName="yes" /><input type="radio" name="printing" value="true" checked="checked">	
		<pos:element columnName="no" /><input type="radio" name="printing" value="false">
	</logic:equal>
	<logic:notEqual cookie="preference.printing" property="value" value="true">
		<pos:element columnName="yes" /><input type="radio" name="printing" value="true">	
		<pos:element columnName="no" /><input type="radio" name="printing" value="false" checked="checked">
	</logic:notEqual>
	</logic:present>
	<logic:notPresent cookie="preference.printing">
		<pos:element columnName="yes" /><input type="radio" name="printing" value="true">	
		<pos:element columnName="no" /><input type="radio" name="printing" value="false" checked="checked">
	</logic:notPresent>
	</div>
	<div class="space"></div>
	<pos:element columnName="printer.name" />: <input id="printer" name="printer" type="text" value="default">
	<div class="space"></div>
	</fieldset>
</div>
<div class="space"></div>
<div align="right">
	<input id="savebtn" type="button" class="newbutton" value="<pos:element columnName='save' textOnly='true'/>">		
</div>
</form>	
</div>		
<%@ include file="/jsp/include/posFooter.jsp" %>
<script>
var display = null;
var theme = null;
var printing = null;
var printer = null;

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
	
	$('printer').value = printer;	
}
	
function loadPreferences()
{
	display = readCookie('preference.display');
	theme = readCookie('preference.display');
	printing = readCookie('preference.printing');
	printer = readCookie('preference.printer');	
	
	display = (display)?display:'10';
	theme = (theme)?theme:'default';
	printing = (printing)?printing:'false';
	printer = (printer)?printer:'default';
}

function savePreferences()
{
	//------------------------------
	display 	= Form.Element.getValue('display');
	theme 		= Form.Element.getValue('theme');
	printer 	= Form.Element.getValue('printer');
	
	var elements = document.getElementsByName('printing');
	for(var i=0;i<elements.length;i++){
		if(elements[i].checked)
		{
			printing = elements[i].value;
			break;
		}
	}
	
	createCookie('preference.display'	,display	,7);
	createCookie('preference.theme'		,theme		,7);
	createCookie('preference.printing'	,printing	,365);
	createCookie('preference.printer'	,printer	,7);	
	
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