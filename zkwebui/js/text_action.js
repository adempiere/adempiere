
function Text_action()
{

	
	this.textEvent = textEvent;
	

	function textEvent(calcTextId, key, keyBoardType)
	{
		try
		{
			var entry = key;
        	var calcText = document.getElementById(calcTextId);

        	if(calcText.selectionStart != calcText.selectionEnd){
				
				        // initial position of the cursor
				        start = calcText.selectionStart;

				        //	Replace all content
				        calcText.value = calcText.value.substr(0, calcText.selectionStart) +
				        entry +	calcText.value.substr(calcText.selectionEnd, calcText.value.length);

				        // 	Move the cursor to the end position
				        calcText.selectionStart = start + entry.length;
				        calcText.selectionEnd = start + entry.length;
				        
				        calcText.focus();
		        
			}else {
				if ( entry != "null" && entry!="" )
				{	
					if(keyBoardType!="true"){
						// Set Key value
						if ( key != null )
							calcText.value= calcText.value + entry ;
				        calcText.focus();
					}
					else {
						// Set Number value
						if ( entry=="." )
							{
							calcText.value=calcText.value + entry;
							}
						else if ( entry=="," )
						{
							calcText.value=calcText.value + entry;
						}
						else if ( entry=="C" )
						{
							calcText.value="0";
						}
						else {
							try
							{
								if(calcText.value == "0")
									var textbox = "";
								else
									var textbox = calcText.value;
								var number = parseInt(entry);		// test if number
								if ( number >= 0 && number <= 9 )
								{
									calcText.value=textbox+number;
								}
								// greater than 9, add to existing
								else 
								{
									if(textbox == "")
										calcText.value=parseInt(number);
									else
										calcText.value=parseInt(textbox)+parseInt(number);
								}
		
							}
							catch (err)
							{
								// ignore non-numbers
							}
						}
				        calcText.focus();
					}	
				}
			}
		}
		catch (err)
		{
		}
	}
	

	
}

var text_action = new Text_action();