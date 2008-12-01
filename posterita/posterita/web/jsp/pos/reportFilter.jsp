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

<%@ include file="/jsp/include/posHeader.jsp" %>	   
<%@ include file="/jsp/include/errors.jsp" %> 

<html:form action="/POSSalesReportAction">
<table width="100%" border="0" cellpadding="5">	
	<tr>		
		<td>
			<table align="left">
				<tr>
					<td>
						<pos:message key="report.filter.settings"/>
					</td>					
				</tr>
				<tr>
					<td>
						<fieldset>
						<legend><pos:message key="filter.type"/>:</legend>
						<select onchange="setFilter(this)">
							<option value="fixed" selected><pos:message key="fixed"/></option>
							<option value="custom"><pos:message key="custom"/></option>
						</select>
						</fieldset>
					</td>					
				</tr>
				<tr>
					<td>
						<pos:message key="date.range"/>:
						<div id="fixedDatePanel">
						<fieldset>
						<legend><pos:message key="fixed"/></legend>
						<select>
							<option value="today" selected><pos:message key="today"/></option>
							<option value="today"><pos:message key="current.week"/></option>
							<option value="today"><pos:message key="last.2.weeks"/></option>
							<option value="today"><pos:message key="last.3.weeks"/></option>
							<option value="today"><pos:message key="current.month"/></option>
							<option value="today"><pos:message key="last.3.months"/></option>
							<option value="today"><pos:message key="last.6.month"/></option>
							<option value="today"><pos:message key="current.year"/></option>
						</select>
						</fieldset>
						</div>
						
						<div id="customDatePanel">
						<fieldset>
						<legend>Custom</legend>
						<html:hidden property="fromDate" value=""/>
						<html:hidden property="toDate" value=""/>
						
						<table border="0" cellpadding="2" cellspacing="0" align="center">
						<%@ include file="/jsp/include/errors.jsp" %>		
							<tr>
								<td>
									<table align="center">
										<tr>
											<td width="40px">
												<font class="bolddarkgraysmall"><pos:message key="date.from"/>: </font>
											</td>
											
											<td>
												<html:select property="startDay" styleClass="daySelectBox" disabled="true">			
													<%@ include file="/jsp/include/dayList.jsp" %>
												</html:select>	
																														
												<html:select property="startMonth" styleClass="monthSelectBox"  disabled="true">			
													<%@ include file="/jsp/include/monthList.jsp" %>
												</html:select>			
																														
												<html:select property="startYear" styleClass="yearSelectBox"  disabled="true">	
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
												<font class="bolddarkgraysmall"><pos:message key="to"/>: </font>
											</td>
											
											<td>
												<html:select property="endDay" styleClass="daySelectBox"  disabled="true">			
													<%@ include file="/jsp/include/dayList.jsp" %>
												</html:select>	
																																		
												<html:select property="endMonth" styleClass="monthSelectBox"  disabled="true">			
													<%@ include file="/jsp/include/monthList.jsp" %>
												</html:select>			
																																		
												<html:select property="endYear" styleClass="yearSelectBox"  disabled="true">	
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
			</table>
		</td>
	</tr>
	<tr>	
		<td>
			<table> 				
				<tr>
					<td valign="middle">						
						<img src="images/pos/samplechart1.jpg"/>
					</td>					
				</tr>
			</table>
		</td>
	<tr>
</table>
</html:form>
<script>
var p2 = document.getElementById('customDatePanel');
p2.style.display = "none";

function setFilter(select)
{
	var p1 = document.getElementById('fixedDatePanel');
	var p2 = document.getElementById('customDatePanel');
		
	var index = select.selectedIndex;
	try
	{
		if(index > 0)
		{			
			p1.style.display = "none";
			p2.style.display = "inline";
		}
		else
		{
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