
function Calc() 
{
	this.validate = validate;
	this.clear = clear;
	this.clearAll = clearAll;
	this.evaluate = evaluate;
	this.append = append;
	
	function validate(displayTextId, calcTextId, integral, e)
	{
	     var key;

	     if(window.event)
	          key = e.keyCode; //IE
	     else
	          key = e.which;   //Firefox
	          
	     if(key == 13 || key == 61) // Enter, =
	     {
	     	evaluate(displayTextId, calcTextId);
	        return false;
	     }
	     else if (key == 0) // control, delete, ...
	     {	
	     	return true;
	     }
	     else if (key == 8) // backspace
	     {
	     	return true;
	     }
	     else if (key >= 17 && key <= 20) // Control
	     {	
	     	return true;
	     }
	     else if (key == 32) // space 
	     {
	     	return true;
	     }
	     else if (key >= 48 && key <= 57) // 0 - 9
	     {
	     	return true;
	     }
	     else if (key == 42 || key == 43 || key == 45 || key == 47) // *, +, -, /
	     {
	     	return true;	
	     }
	     else if ( key == 46 && !integral)
	     {
	     	return true;
	     }
	     else
	     {
	     	return false;
	     }
	}
	
	function clearAll(calcTextId)
	{
		try
		{
			var calcText = document.getElementById(calcTextId);
			calcText.value = "";
		}
		catch (err)
		{
		}
	}
	
	function clear(calcTextId)
	{
		try
		{
			var calcText = document.getElementById(calcTextId);
			var val = calcText.value;
			if (val != "")
			{
				val = val.substring(0, val.length - 1);
				calcText.value = val;
			}
		}
		catch (err)
		{
		}
			
	}
	
	function evaluate(displayTextId, calcTextId)
	{
		try
		{
			var calcText = document.getElementById(calcTextId);
			calcText.value = eval(calcText.value);
			
			var displayText = document.getElementById(displayTextId);
			
			if (!displayText.readOnly && calcText.value != 'undefined')
			{
				displayText.value = calcText.value;
			}
		}
	   	catch (err)
	   	{
	   	}
	}
	
	function append(calcTextId, val)
	{
		var calcText = document.getElementById(calcTextId);
		calcText.value += val;
	}
}

var calc = new Calc();