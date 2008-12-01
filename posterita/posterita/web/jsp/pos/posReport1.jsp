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

<bean:define id="title"><pos:message textOnly="true" key="sales.reports"/></bean:define>
<%@ include file="/jsp/include/posHeader.jsp" %> 
<%@ include file="/jsp/include/errors.jsp" %>  

 
<html:form action="/CustomPOSReportAction">
<html:hidden property="action" value="getCustomReport"/>
<bean:define id="fromDate" name="SalesReportForm" property="fromDate"/>
<bean:define id="toDate" name="SalesReportForm" property="toDate"/>

<html:hidden property="fromDate" value="<%=fromDate+""%>"/>
<html:hidden property="toDate" value="<%=toDate+""%>"/>

<table class="content" border="1">
	<tr>
		<td valign="top">
			<pos:message key="account.type"/>:<br>
			<html:select property="accountId" styleClass="text">
				<html:option value="41000"><pos:message key="sales"/></html:option>	
			</html:select>
		</td>
		<td valign="top" style="width:100px">
			<fieldset>
			<legend><pos:message key="date.range"/></legend>
			<html:select property="dateRange" onchange="setFilter()">
				<html:option value="<%=Constants.FIXED_DATE_RANGE%>"><pos:message key="fixed"/></html:option>
				<html:option value="<%=Constants.CUSTOM_DATE_RANGE%>"><pos:message key="custom"/></html:option>
			</html:select>
			</fieldset>
		</td>
		<td valign="top" rowspan="2" style="height:120px;width:500px" align="center">				
			<div id="fixedDatePanel">
				<fieldset style="height:120px;width:500px">
				<legend><pos:message key="fixed"/></legend>
				<label><pos:message key="select.range"/>:</label>
				<html:select property="timePeriod">
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
					
			<div id="customDatePanel">
				<fieldset style="height:120px;width:500px">
				<legend><pos:message key="custom"/></legend>					
				<table class="main">					
					<tr>
						<td>
							<table align="center">
								<tr>
									<td width="40px">
										<label><pos:message key="from"/>:</label>
									</td>
									
									<td>
										<html:select property="startDay" styleClass="daySelectBox">			
											<%@ include file="/jsp/include/dayList.jsp" %>
										</html:select>	
																												
										<html:select property="startMonth" styleClass="monthSelectBox">			
											<%@ include file="/jsp/include/monthList.jsp" %>
										</html:select>			
																												
										<html:select property="startYear" styleClass="yearSelectBox">	
											<%@ include file="/jsp/include/yearList.jsp" %>
										</html:select>
									</td>
									
									<td>
										<input name="popcal"  
											onclick="if(this.blur)this.blur();
											var fm=this.form;
											gfPop.fPopFromDate(fm.startDay,fm.startMonth,fm.startYear,fm.fromDate,fm.toDate)" type="button" value="...">
									</td>
									<td>
										<html:select property="startHour">	
											<%@ include file="/jsp/include/hourList.jsp" %>
										</html:select>
										h
										<html:select property="startMinute">	
											<%@ include file="/jsp/include/minuteList.jsp" %>
										</html:select>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					
					<tr>
						<td>
							<table align="center">
								<tr>
									<td width="40px">
										<label><pos:message key="to"/>:</label>
									</td>
									
									<td>
										<html:select property="endDay" styleClass="daySelectBox">			
											<%@ include file="/jsp/include/dayList.jsp" %>
										</html:select>	
																																
										<html:select property="endMonth" styleClass="monthSelectBox">			
											<%@ include file="/jsp/include/monthList.jsp" %>
										</html:select>			
																																
										<html:select property="endYear" styleClass="yearSelectBox">	
											<%@ include file="/jsp/include/yearList.jsp" %>
										</html:select>
									</td>
									
									<td>
										<input name="popcal1"  
											onclick="if(this.blur)this.blur();
													 var frm=this.form;
													 gfPop.fPopToDate(frm.endDay,frm.endMonth,frm.endYear,frm.toDate,frm.fromDate)" type="button" value="...">
									</td>
									
									<td>
										<html:select property="endHour">	
											<%@ include file="/jsp/include/hourList.jsp" %>
										</html:select>
										h
										<html:select property="endMinute">	
											<%@ include file="/jsp/include/minuteList.jsp" %>
										</html:select>
									</td>
								</tr>
							</table>
						</td>
					</tr>							
					<iframe width=174 height=189 name="gToday:normal:jsp/popCalendar/agenda.js" id="gToday:normal:jsp/popCalendar/agenda.js" src="jsp/popCalendar/ipopeng.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0px;">
					</iframe>
			   	</table>
			   	</fieldset>					
			   	</div>	
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<pos:message key="IsGroupBy"/>:<br>
			<html:select property="salesGroup">
				<html:options collection="<%=Constants.SALESGROUP_LIST%>" property="value" labelProperty="key"/>	
			</html:select>
		</td>
		<td>&nbsp;</td>		
	</tr>
	<tr>
		<td colspan="3">
			<div style="border: solid 1px black;display:inline;">
			<table border="1" width="150px">
				<tr>
					<td>
						<html:button property="chartType" styleClass="piechart smallbutton">&nbsp;</html:button>
						<img src="images/pos/charts/piechart_icon.gif">
					</td>
				</tr>
				<tr>
					<td>
						<html:button property="chartType" styleClass="barchart smallbutton">&nbsp;</html:button>
						<img src="images/pos/charts/barchart_icon.gif">
					</td>
				</tr>
				<tr>
					<td>
						<html:button property="chartType" styleClass="timeseries smallbutton">&nbsp;</html:button>
						<img src="images/pos/charts/timeseries_icon.gif">
					</td>
				</tr>
				<tr>
					<td>
						<html:submit property="chartType" value="PieChart" styleClass="tabular smallbutton">&nbsp;</html:submit>
						<img src="images/pos/charts/tabular_icon.gif">
					</td>
				</tr>
			</table>	
			</div>	
					
			<div style="border: solid 1px black;display:inline;">
				<logic:present name="<%=Constants.REPORT_URL%>">
				<bean:define id="imgSrc" name="<%=Constants.REPORT_URL%>"/>
				<img src="<%=imgSrc%>"/>
				<% request.getSession().removeAttribute(Constants.REPORT_URL); %>	
				</logic:present>
				
				<logic:present name="<%=Constants.TABULAR_REPORT_DATA%>">					
				<bean:define id="reportData" name="<%=Constants.TABULAR_REPORT_DATA%>"/>
				<bean:define id="csvFile" name="<%=Constants.CSV_FILE%>"/>					
				<%=reportData%>	
				<br>
				<p align="right">
					<a href="<%=csvFile%>"><pos:message key="save.as.csv"/></a>
					<% request.getSession().removeAttribute(Constants.TABULAR_REPORT_DATA); %>	
				</p>			
				</logic:present>					
			</div>			
		</td>
	</tr>	
</table>

</html:form>
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
<%@ include file="/jsp/include/posFooter.jsp" %>