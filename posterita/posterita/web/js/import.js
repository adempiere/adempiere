var initScreen = function(){

$('importCsvBtn').onclick = function(e){
		if($(file).value == "" || $(file).value == null || $(file).value == 'null')
		{
			showErrorMessage('Please enter a csv file!');
		}
		else
		{
			$('importForm').submit();
		}
	};

initShortcuts();
addBehaviourToCart();

}

var initShortcuts = function(){
	
	shortcut.add("F12", function(e){simulateOnClick($('importCsvBtn'))});
	shortcut.add("F11", function(e){simulateOnClick($('file'))});
}

Event.observe(window,'load',initShortcuts,false);