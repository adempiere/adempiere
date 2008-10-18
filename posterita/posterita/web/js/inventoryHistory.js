//scripts for Posterita Inventory History
var product_id = null;
var priceList = null;
var searchElement = null;
var defaultPriceList = null;

var initHistory = function(){
	priceList = $('priceListId').value;
	
	defaultPriceList = priceList;
	
	var searchProductBy = $('searchProductBy').value;
	if(searchProductBy == 'name')
	{
		searchElement = $('productQuery');
	}
	else
	{
		searchElement = $(searchProductBy);
	}
	
	
	initProductSearch();
	
	searchElement.focus();
	
	var barcode = $('barcode');
	barcode.onkeyup = function(e){
		if(e.keyCode == Event.KEY_RETURN)
		{
			if(this.value != null && this.value != "")
			{				
				redirect('ViewInventoryHistoryAction.do?action=searchProductInInventory&barCode=' + this.value);
			}
			
		}
	};
};

var showProduct = function(element, update)
{ 
	  update.style.display = 'block';
      update.style.opacity = '1.0';
      
      if(!update.style.position || update.style.position=='absolute') {
        update.style.position = 'absolute';
        Position.clone(element, update, {setHeight: false, offsetTop: element.offsetHeight});
        Position.clone($('maintop'), update, {setHeight: false, setTop: false, offsetTop: element.offsetHeight});             
      }      
      
    };


var initProductSearch = function(){						
						
	// autocomplete for search by product name					
	var productAutocompleter = new Ajax.Autocompleter('productQuery','productSearchResult','SearchProductsAction.do',{								
		paramName:'productName',
		parameters : 'priceListId=' + priceList,
		minChars:1,								
		frequency:1.0,
		afterUpdateElement: afterAutocomplete2,
		onShow : showProduct												
		});
		
	productAutocompleter.xyz = true;		
	$('productQuery').Autocompleter = productAutocompleter;	
	
	
	// autocomplete for search by product description					
	var productAutocompleter2 = new Ajax.Autocompleter('description','productSearchByDescriptionResult','SearchProductsAction.do',{								
		paramName:'description',
		parameters : 'priceListId=' + priceList,
		minChars:1,								
		frequency:1.0,
		afterUpdateElement: afterAutocomplete2,
		onShow : showProduct													
		});
		
	$('description').Autocompleter = productAutocompleter2;
};

var afterAutocomplete2 = function(e1,e2){
											
												
	var barcode = e2.getAttribute('barcode');
	if(barcode=='null')
	{
		barcode = "";
	}
	var productId = e2.getAttribute('productId');
	
	if(productId == null)
	{
		$('description').value = "";
		$('productQuery').value = "";
		
		return;
	}
														
	//$('barcode').value = barcode;
	$('productId').value= productId;
	
	var description = e2.getAttribute('description');
	//$('description').value = description;
	
	redirect('ViewInventoryHistoryAction.do?action=searchProductInInventory&productId=' + productId);
	
	//reset focus to search input
	searchElement.focus();	
	
	//set global product 
	product_id = productId;	
	
	$('description').value = "";
	$('productQuery').value = "";				
																																									
};

function reportError(request)
{
	alert("Some error has occured while communicating with the server");
};

function convertString2Unicode(s)
{
	 var uniString = "", hexVal, uniChar;
	
	 for(var i = 0; i < s.length; ++i)
	 {
		 //Convert char to hex
		 //hexVal = Number(s.charCodeAt(i)).toString(16);
		 hexVal = Number(s.charCodeAt(i)).toString(10);
		
		 //Convert to unicode by making sure hex is 4 chars long, padding with 0's if less
		 //uniChar = "\\u" + ("000" + hexVal).match(/.{4}$/)[0];
		 uniChar = "&#" + ("000" + hexVal).match(/.{4}$/)[0] +";";
		
		 uniString += uniChar;
	 }
	if(hexVal < 255) 
		return s;
		
 	return uniString;
}

function redirect(url)
{
	window.location = url;
}

Event.observe(window,'load',initHistory,false);