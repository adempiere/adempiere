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
			<label><pos:message key="date.to"/>:</label>
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
			<html:hidden property="toDate"/>
			<input type="hidden" id="endDate">
			<button id="endDateBtn" style="background-image: url('images/calendar_icon.png');height: 22px;width: 22px"></button>
		</td>
		
		<td>
			<html:select property="endHour">	
				<%@ include file="/jsp/include/hourList.jsp" %>
			</html:select>
			<pos:message key="time.hour"/>
			<html:select property="endMinute">	
				<%@ include file="/jsp/include/minuteList.jsp" %>
			</html:select>
			<pos:message key="time.minute"/>
		</td>
	</tr>
</table>
<script>
	//addRequiredLibrary('js/js-calendar.js');
	
	function setEndDate(calendar)
	{
		var y = calendar.date.getFullYear();
		var m = calendar.date.getMonth();
		var d = calendar.date.getDate();
		
		$FElement('endDay').options[d-1].selected = true;
		$FElement('endMonth').options[m].selected = true;
		
		var options = $FElement('endYear').options;
		
		for(var i = 0; i < options.length; i++)
		{
			if(options[i].value == y)
			{
				$FElement('endYear').options[i].selected = true;
				break;
			}
		}
		
		updateEndDate();		
		
	}
	
	function updateEndDate()
	{
		var d = $FElement('endDay').selectedIndex + 1;
		var m = $FElement('endMonth').selectedIndex + 1;
		var y = $FElement('endYear').selectedIndex;
		y = $FElement('endYear').options[y].value;
		
		d = (d > 9) ? d : ('0' + d);
		m = (m > 9) ? m : ('0' + m);
		
		$('endDate').value = d + '/' + m + '/' + y;
		$FElement('toDate').value = $('endDate').value;		
	}
	
	function initEndCalendar()
	{
		$FElement('endDay').onchange = updateEndDate;
		$FElement('endMonth').onchange = updateEndDate;
		$FElement('endYear').onchange = updateEndDate;
		
		Calendar.setup(
			{ 	inputField : "endDate",
				ifFormat   : "%d/%m/%Y",
				showTime   : true,	
				button	   : "endDateBtn",
				onUpdate   : setEndDate
			}
		);
		
		$('endDate').value = $FElement('toDate').value;
	}	
	
	Event.observe(window,'load',initEndCalendar,false);
</script>