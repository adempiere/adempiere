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

<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.businesslogic.performanceanalysis.ReportDateManager" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<html:hidden property="fromDate" />
<html:hidden property="toDate" />

<table width="100%" border="0">
	<tr>
		<td valign="top">
			<fieldset>
			<legend><pos:message key="date.range"/></legend>
			<html:select property="dateRange" onchange="setFilter()">
				<html:option value="<%=Constants.FIXED_DATE_RANGE%>"><pos:message key="fixed"/></html:option>
				<html:option value="<%=Constants.CUSTOM_DATE_RANGE%>"><pos:message key="custom"/></html:option>
			</html:select>
			</fieldset>
		</td>
		
		<td valign="top" align="center" rowspan="3">				
			<div id="fixedDatePanel" style="display:none">
			<fieldset >
			<legend><pos:message key="fixed"/></legend>
			<font class="bolddarkgraysmall"><pos:message key="select.range"/>:</font>
			<html:select property="timePeriod" onchange="submit()">
				<html:option value="<%=ReportDateManager.TODAY%>"><pos:message key="today"/></html:option>
				<html:option value="<%=ReportDateManager.CURRENT_WEEK%>"><pos:message key="current.week"/></html:option>
				<html:option value="<%=ReportDateManager.LAST_2WEEKS%>"><pos:message key="last.2.weeks"/></html:option>
				<html:option value="<%=ReportDateManager.LAST_3WEEKS%>"><pos:message key="last.3.weeks"/></html:option>
				<html:option value="<%=ReportDateManager.CURRENT_MONTH%>"><pos:message key="current.month"/></html:option>
				<html:option value="<%=ReportDateManager.LAST_3MONTHS%>"><pos:message key="last.3.months"/></html:option>
				<html:option value="<%=ReportDateManager.LAST_6MONTHS%>"><pos:message key="last.6.month"/></html:option>
				<html:option value="<%=ReportDateManager.CURRENT_YEAR%>"><pos:message key="current.year"/></html:option>
			</html:select>
			</fieldset>
			</div>
			
			<div id="customDatePanel" style="display:none">
			<fieldset style="height:90px">
			<legend><pos:message key="custom"/></legend>					
			<table border="0" cellpadding="1" cellspacing="0" align="center" width="100%">						
				<tr>
					<td valign="top">
						<table align="center">							
							<tr>
								<td>
									<%@ include file="/jsp/include/startCalendar.jsp" %>
								</td>								
							</tr>
							<tr>
								<td>
									<%@ include file="/jsp/include/endCalendar.jsp" %>
								</td>								
							</tr>
						</table>
					</td>
				</tr>
		   	</table>
		   	</fieldset>					   	
		   	</div>	
		</td>				
	</tr>
	<tr>
		<td>
			<html:hidden property="chartType" value="Tabular"/>
			<html:submit styleClass="smallbutton refresh">&nbsp;</html:submit>
		</td>
	</tr>
	<tr>
		<td colspan="3"><hr></td>
	</tr>			
	<tr>
		<td colspan="3" valign="top">
		<div>					
			<logic:present name="<%=Constants.TABULAR_REPORT_DATA%>">					
			<bean:define id="reportData" name="<%=Constants.TABULAR_REPORT_DATA%>"/>
			<bean:define id="csvFile" name="<%=Constants.CSV_FILE%>"/>
			<bean:define id="pdfFile" name="<%=Constants.PDF_FILE%>"/>		
							
			<%=reportData%>	
			<br>
			<p align="right">
				<a href="<%=csvFile%>">
					<input type="image" src="./images/iconCSV.jpg" height=""/>
				</a>
				<a href="<%=pdfFile%>">
					<input type="image" src="./images/pdf_icon_small.gif"/>
				</a>
			</p>
			<%-- <bean:define id="pdfFile" name="<%=Constants.PDF_FILE%>"/>
			<p align="right">
				<bean:define id="pdfFile" name="<%=Constants.PDF_FILE%>"/>
				<a href="<%=pdfFile%>">
					<html:button property="btn" styleClass="saveaspdf smallbutton">&nbsp;</html:button>
				</a>
			</p>
			--%>						
			<% request.getSession().removeAttribute(Constants.TABULAR_REPORT_DATA); %>				
			</logic:present>					
		</div>	
		</td>
	</tr>			
</table>
	
<script>
//initialising GUI components

setFilter();
function setFilter()
{
	var select = document.getElementsByName('dateRange')[0];
	
	var p1 = document.getElementById('fixedDatePanel');
	var p2 = document.getElementById('customDatePanel');
	
	toConsole("p1-->" + p1);
	toConsole("p2-->" + p2);
	
	var index = select.selectedIndex;
	toConsole("Selected Index :" + index);
	
	toConsole("p1 display-->" + p1.style.display);
	toConsole("p2 display-->" + p2.style.display);
	
	try
	{
		if(index > 0)
		{
			toConsole("Hiding p1");
			p1.style.display = "none";
			p2.style.display = "inline";
		}
		else
		{
			toConsole("Hiding p2");
			p1.style.display = "inline";
			p2.style.display = "none";
		}
	}
	catch (e)
	{
		toConsole(e);
	}
	
}

</script>