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



<!-- posReport.jsp -->
<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.*" %>
<%@ page import="org.posterita.struts.pos.POSOrderAction" %>
<%@ page import="org.posterita.businesslogic.performanceanalysis.ReportDateManager" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>

<bean:define id="title"><pos:message textOnly="true" key="smenu.performance.analysis.report"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %> 
<%@ include file="/jsp/include/errors.jsp" %>  

 
<html:form action="/CustomPOSReportAction">
<html:hidden property="action" value="getCustomReport"/>
<bean:define id="fromDate" name="SalesReportForm" property="fromDate"/>
<bean:define id="toDate" name="SalesReportForm" property="toDate"/>

<html:hidden property="fromDate" value="<%=fromDate+""%>"/>
<html:hidden property="toDate" value="<%=toDate+""%>"/>
<html:hidden property="chartType"/>

<!--custom report table-->
		
<table class="main" border="0">
	<tr>		
		<td width="180px">
			<fieldset>
				<legend><pos:message key="account.type"/></legend>
				<html:select property="accountId" styleClass="text">
					<html:option value="12110"><pos:message key="accounts.receivable"/></html:option>
					<html:option value="41000"><pos:message key="trade.revenue"/></html:option>	
					<html:option value="51100"><pos:message key="cogs"/></html:option>
					<html:option value="99999"><pos:message key="profit.margin"/></html:option>	
					<html:option value="14120"><pos:message key="asset"/></html:option>
					<html:option value="12610"><pos:message key="tax.credit"/></html:option>
					<html:option value="21610"><pos:message key="tax.due"/></html:option>
				</html:select>
			</fieldset>
		</td>
		
		<td>
			<table class="view" width="100%">
				<tr>
					<td>
						<fieldset>
							<legend><pos:message key="grouped.by"/></legend>
							<html:select property="salesGroup" styleClass="text">
								<html:options collection="<%=Constants.SALESGROUP_LIST%>" property="value" labelProperty="key"/>	
							</html:select>
						</fieldset>
					</td>
					<td>
						<fieldset>
							<legend><pos:message key="view.by"/></legend>
							<html:select property="priceQtyFilter" styleClass="text">
								<html:options collection="<%=Constants.PRICEQTY_FILTER%>" property="value" labelProperty="key"/>	
							</html:select>
						</fieldset>
					</td>
				</tr>
			</table>
		</td>				
	</tr>		
	<tr>
		<td valign="top" width="180px">
			<fieldset>
			<legend><pos:message key="date.range"/></legend>
			<html:select property="dateRange" styleClass="text">
				<html:option value="<%=Constants.FIXED_DATE_RANGE%>"><pos:message key="fixed"/></html:option>
				<html:option value="<%=Constants.CUSTOM_DATE_RANGE%>"><pos:message key="custom"/></html:option>
			</html:select>
			</fieldset>
		</td>
		<td valign="top" align="center" rowspan="2">				
			<div id="fixedDatePanel" style="display:none">
			<fieldset style="height:130px">
			<legend><pos:message key="fixed"/></legend>
			<font class="bolddarkgraysmall"> <pos:message key="custom"/>: </font>
			<html:select property="timePeriod" styleClass="text">
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
			<fieldset style="height:130px">
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
			<html:submit styleClass="smallbutton refresh">&nbsp;</html:submit>			
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<html:button property="pieChartBtn"   styleClass="smallbutton piechart"   onclick="setChartType('PieChart');">&nbsp;</html:button>
			<html:button property="barChartBtn"   styleClass="smallbutton barchart"   onclick="setChartType('BarChart');">&nbsp;</html:button>
			<html:button property="timeseriesBtn" styleClass="smallbutton timeseries" onclick="setChartType('TimeSeries');">&nbsp;</html:button>
			<html:button property="tabularBtn"    styleClass="smallbutton tabular"    onclick="setChartType('Tabular');">&nbsp;</html:button>	
		</td>
	</tr>
	<logic:present name="<%=Constants.DISPLAY_REPORT%>">	
	<tr>
		<td colspan="2">
		<div align="center">
			<logic:present name="<%=Constants.REPORT_URL%>">
			<bean:define scope="request" id="reportFound" value="true"/>
			<bean:define id="imgSrc" name="<%=Constants.REPORT_URL%>"/>
			<img src="<%=imgSrc%>"/>
			<% request.getSession().removeAttribute(Constants.REPORT_URL); %>	
			</logic:present>
									
			<logic:present name="<%=Constants.TABULAR_REPORT_DATA%>">	
			<bean:define scope="request" id="reportFound" value="true"/>				
			<bean:define id="reportData" name="<%=Constants.TABULAR_REPORT_DATA%>"/>
			<bean:define id="csvFile" name="<%=Constants.CSV_FILE%>"/>
			<div id="tabularReport1">	
			<div align="right">
				<a href="javascript:void(0);" onclick="Element.hide('tabularReport1');Element.show('tabularReport2');">Without date grouping</a>
			</div>					
			<%=reportData%>	
			<br>
			<p align="right">
				<a href="<%=csvFile%>">
					<html:button property="btn" styleClass="saveascsv smallbutton">&nbsp;</html:button>
				</a>
			</p>	
			</div>				
			<% request.getSession().removeAttribute(Constants.TABULAR_REPORT_DATA); %>				
			</logic:present>
			
			<logic:present name="<%=Constants.TABULAR_REPORT_DATA2%>">	
			<bean:define scope="request" id="reportFound" value="true"/>				
			<bean:define id="reportData2" name="<%=Constants.TABULAR_REPORT_DATA2%>"/>
			<bean:define id="csvFile2" name="<%=Constants.CSV_FILE2%>"/>
			
			<div id="tabularReport2" style="display:none">
			<div align="right">
				<a href="javascript:void(0);" onclick="Element.hide('tabularReport2');Element.show('tabularReport1');">With date grouping</a>
			</div>					
			<%=reportData2%>	
			<br>
			<p align="right">
				<a href="<%=csvFile2%>">
					<html:button property="btn" styleClass="saveascsv smallbutton">&nbsp;</html:button>
				</a>
			</p>
			</div>						
			
			<% request.getSession().removeAttribute(Constants.TABULAR_REPORT_DATA2); %>				
			</logic:present>							
		</div>		
		</td>		
	</tr>
	</logic:present>				
</table>

<!--End of custom table-->
</html:form>
<script>
//initialising GUI components

var accountId 		= $FElement('accountId');
var chartType 		= $FElement('chartType');
var dateRange		= $FElement('dateRange');
var priceQtyFilter 	= $FElement('priceQtyFilter');
var salesGroup		= $FElement('salesGroup');

var pieChartBtn		= $FElement('pieChartBtn');
var barChartBtn		= $FElement('barChartBtn');
var timeseriesBtn	= $FElement('timeseriesBtn');
var tabularBtn		= $FElement('tabularBtn');



function initialize()
{
	dateRange.onchange = setDateRange;	
	accountId.onchange = initializeAccountType;
	
	initializeAccountType();
	setDateRange();
}

function setDateRange()
{
	if(dateRange.selectedIndex > 0)
	{
		Element.hide('fixedDatePanel');
		Element.show('customDatePanel');				
	}
	else
	{			
		Element.show('fixedDatePanel');
		Element.hide('customDatePanel');
	}
}

function setChartType(type)
{
	chartType.value = type;	
	chartType.form.submit();
}

function initializeAccountType()
{
	var index = accountId.selectedIndex;
				
	if(index == -1)
		return;
	
	var accountValue = accountId.options[index].value;	
	
	var taxCredit 	= 12610;
	var taxDue 		= 21610;		
	
	if((accountValue == taxCredit)||(accountValue == taxDue))
	{
		chartType.value = 'Tabular';
		priceQtyFilter.disabled = true;
		salesGroup.disabled = true;
		
		pieChartBtn.disabled = true;
		barChartBtn.disabled = true;
		timeseriesBtn.disabled = true;
		
		pieChartBtn.style.cursor = 'not-allowed';
		barChartBtn.style.cursor = 'not-allowed';
		timeseriesBtn.style.cursor = 'not-allowed';
		
	}
	else
	{
		priceQtyFilter.disabled = false;
		salesGroup.disabled = false;
		
		pieChartBtn.disabled = false;
		barChartBtn.disabled = false;
		timeseriesBtn.disabled = false;
		
		pieChartBtn.style.cursor = 'pointer';
		barChartBtn.style.cursor = 'pointer';
		timeseriesBtn.style.cursor = 'pointer';
	}
}

initialize();
</script>
<%@ include file="/jsp/include/posFooter.jsp" %>