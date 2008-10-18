function setStartDate(calendar)
	{
		var y = calendar.date.getFullYear();
		var m = calendar.date.getMonth();
		var d = calendar.date.getDate();
		
		$FElement('startDay').options[d-1].selected = true;
		$FElement('startMonth').options[m].selected = true;
		
		var options = $FElement('startYear').options;
		
		for(var i = 0; i < options.length; i++)
		{
			if(options[i].value == y)
			{
				$FElement('startYear').options[i].selected = true;
				break;
			}
		}
		
		updateStartDate();		
		
	}
	
	function updateStartDate()
	{
		var d = $FElement('startDay').selectedIndex + 1;
		var m = $FElement('startMonth').selectedIndex + 1;
		var y = $FElement('startYear').selectedIndex;
		y = $FElement('startYear').options[y].value;
		
		d = (d > 9) ? d : ('0' + d);
		m = (m > 9) ? m : ('0' + m);
		
		$('startDate').value = d + '/' + m + '/' + y;
		$FElement('fromDate').value = $('startDate').value;
	}
	
	function initStartCalendar()
	{
		$FElement('startDay').onchange = updateStartDate;
		$FElement('startMonth').onchange = updateStartDate;
		$FElement('startYear').onchange = updateStartDate;
		
		Calendar.setup(
			{ 	inputField : "startDate",
				ifFormat   : "%d/%m/%Y",
				showTime   : true,	
				button	   : "startDateBtn",
				onUpdate   : setStartDate
			}
		);
		
		$('startDate').value = $FElement('fromDate').value;
	}