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


<table align="center">
	<tr>
		<td width="60px">
			<label><pos:message key="date.from"/>:</label>
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
			<html:hidden property="fromDate"/>
			<input type="hidden" id="startDate">
			<button id="startDateBtn" style="background-image: url('images/calendar_icon.png');height: 22px;width: 22px"></button>
		</td>
		
		<td>
			<html:select property="startHour">	
				<%@ include file="/jsp/include/hourList.jsp" %>
			</html:select>
			<pos:message key="time.hour"/>
			<html:select property="startMinute">	
				<%@ include file="/jsp/include/minuteList.jsp" %>
			</html:select>
			<pos:message key="time.minute"/>
		</td>
	</tr>
</table>
<script>
	<%@ include file="/js/js-date.js" %>
		
	Event.observe(window,'load',initStartCalendar,false);
</script>