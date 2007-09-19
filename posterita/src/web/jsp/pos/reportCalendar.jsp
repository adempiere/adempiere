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
<%@ page import="org.posterita.beans.UDIBean" %>
<%@ page import="java.util.*" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>	
<%@ taglib uri="/WEB-INF/pos.tld" prefix="pos"%>

<logic:notPresent name="<%=WebUserInfo.NAME%>" scope="session">
	<jsp:forward page="/LoginHome.do"/>
</logic:notPresent>


<%@ include file="/jsp/include/posHeader.jsp" %>
<%@ include file="/jsp/include/tabTop.jsp" %><pos:element textOnly="true" columnName="view.report"/><%@ include file="/jsp/include/tabBottom.jsp" %>	   
<%@ include file="/jsp/include/errors.jsp" %> 

<html:form action="<%=actionPath%>">
<html:hidden property="action" value="<%=action%>"/>
<html:hidden property="fromDate" value=""/>
<html:hidden property="toDate" value=""/>

		<table width="100%" border="0" cellpadding="5" cellspacing="1" align="center">
		<%@ include file="/jsp/include/errors.jsp" %>		
			<tr>
				<td>
					<table align="center">
						<tr>
							<td width="40px">
								<font class="bolddarkgraysmall"><pos:element columnName="date.from"/>: </font>
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
								<font class="bolddarkgraysmall"> <pos:element columnName="to"/>: </font>
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
			
			<tr>				
				<td align="center">
										
					<img src="images/spacer.gif" width="210" height="1" ><html:submit styleClass="cntbtn">
						<pos:element columnName="view.report"/>
					</html:submit>
				</td>
				
				<td>
					&nbsp;
				</td>
			</tr>
			</html:form>
			
			<iframe width=174 height=189 name="gToday:normal:jsp/popCalendar/agenda.js" id="gToday:normal:jsp/popCalendar/agenda.js" src="jsp/popCalendar/ipopeng.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0px;">
			</iframe>
	   	</table>
 
		<%@ include file="/jsp/include/posFooter.jsp" %>	