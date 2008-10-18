var rTimer;
var sds ;

TimeTick();

function TimeTick()
{	
	var d = new Date();
	
	var sds =  new Date();
	
	var hours = d.getHours();
	var minutes = d.getMinutes();
	var seconds = d.getSeconds();
	var month = d.getMonth()+1;
	var date= d.getDate();
	var year = d.getFullYear();
	
	
	hours = (hours < 10)? '0'+hours : hours;
	minutes = (minutes < 10)? '0'+minutes : minutes;
	seconds = (seconds < 10)? '0'+seconds : seconds;
	date =(date <10) ? '0'+date : date;
	month =(month<10) ? '0'+ month : month;
	
	
	$('timer').innerHTML= date+"/"+ month +"/"+year+" "+hours+":"+minutes+":"+seconds;
	if(rTimer)
	{
		clearTimeout(rTimer);
	}

	rTimer = setTimeout('TimeTick()', 1000);
}


