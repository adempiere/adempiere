//scripts for Posterita Ordering Screen
var product_id = null;
var qty = null;
var discountLimit = null;
var paymentPanels = null;
var activePanel = null;
var cartLines = null;
var cartIndex = null;
var orderType = null;
var priceList = null;
var orderType = null;
var maxRows = 15;

var ORDER_TYPES = { 
		POS_ORDER : 'POS Order',
		POS_GOOD_RECEIVE_NOTE : 'POS Goods Received Note'
	};

var initScreen = function(){
	priceList = $('priceListId').value;
	orderType = $('orderType').value;
	
	//set search element
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
	initCartBtn();
	initAjaxResponder();
	initShortcuts();
	
	calcLabels();
	
	searchElement.focus();
	
	$('barcode').focus();
	var barcode = $('barcode');
	barcode.onkeyup = function(e){
		if(e.keyCode == Event.KEY_RETURN)
		{
			if(this.value != null && this.value != "")
			{
				addToCart(this.value);
				this.value = "";
			}
			
		}
	};
	
	$('qty').onkeyup = function(e){
		if(e.keyCode == Event.KEY_RETURN)
		{
			//update qty
			updateQty(product_id, this.value);
		}
	};
	
	$('openDrawerBtn').onclick = function(e){
		openCashDrawer();
	};		
	
	$('clearCartBtn').onclick = function(e){
		clearCart();
	};	
	
	// Price List Button
	$('changePriceList').onclick = function(e){
		if(cartLines == null || cartLines.length == 0)
		{
			showErrorMessage('Cannot change Price List. Shopping cart is empty!');
		}
		else
		{
			loadPriceLists();
		}
	};
	
	// Print Barcode Button
	$('printBarcodeBtn').onclick = function(e){
		if(cartLines == null || cartLines.length == 0)
		{
			showErrorMessage('Shopping cart is empty!');
		}
		else
		{
			printProductBarcode();
		}
	};
	
	//add behaviour to exixting cart
	addBehaviourToCart();
};

var initShortcuts = function(){
	shortcut.add("Ctrl+Enter", function(e){simulateOnClick($('checkoutBtn'))});
	
	shortcut.add("F1", function(e){$('barcode').select();});
	shortcut.add("F2", function(e){$('productQuery').select();});
	shortcut.add("F3", function(e){$('description').select();});
	shortcut.add("F4", function(e){$('qty').select();});

	
	// Barcode Btn
	shortcut.add("Ctrl+B", function(e){simulateOnClick($('printBarcodeBtn'))});
	shortcut.add("Ctrl+M", function(){simulateOnClick($('menuBtn'))});
	shortcut.add("Ctrl+Space", function(){
		//simulateOnClick($('openDrawerBtn'));
		showErrorMessage('Opening Cash Drawer');
		
	});
	shortcut.add("Ctrl+Delete", function(){
		simulateOnClick($('clearCartBtn'));		
	});
	shortcut.add("Ctrl+S", function(){simulateOnClick($('shortcutBtn'))});
	
	//move between cart lines
	shortcut.add("Ctrl+Up", function(){
		if(cartIndex > 0)
		{
			cartIndex = cartIndex -1;
			var line = cartLines[cartIndex];
			scrollCart();
			simulateOnClick(line);	
		}			
	});
	
	shortcut.add("Ctrl+Down", function(){
		if(cartIndex < cartLines.length - 1)
		{
			cartIndex = cartIndex + 1;
			var line = cartLines[cartIndex];
			scrollCart();
			simulateOnClick(line);	
		}			
	});
	
	//incrementing and decrementing qty
	shortcut.add("Ctrl+Right", function(){
		simulateOnClick($('plusBtn'));
	});
	
	shortcut.add("Ctrl+Left", function(){
		simulateOnClick($('minusBtn'));
	});
	
	shortcut.add("Ctrl+Backspace", function(){
		simulateOnClick($('deleteBtn'));
	});
	
	shortcut.add("Ctrl+P", function(){
		simulateOnClick($('printBarcodeBtn'));
	});
	
};

var showResult = function(element, update){ 
      if(!update.style.position || update.style.position=='absolute') {
        update.style.position = 'absolute';
        Position.clone(element, update, {setHeight: false, offsetTop: element.offsetHeight});
        Position.clone($('maintop'), update, {setHeight: false, setTop: false, offsetTop: element.offsetHeight});             
      }      
      
      update.style.display = 'block';
      update.style.opacity = '1.0';
      
    };


var initProductSearch = function(){						
						
	// autocomplete for search by product name					
	var productAutocompleter = new Ajax.Autocompleter('productQuery','productSearchResult','SearchProductsAction.do',{								
		paramName:'productName',
		parameters : 'priceListId=' + priceList,
		minChars:1,								
		frequency:1.0,
		afterUpdateElement: afterAutocomplete,
		onShow : showResult												
		});
		
	productAutocompleter.xyz = true;		
	$('productQuery').Autocompleter = productAutocompleter;	
	
	
	// autocomplete for search by product description					
	var productAutocompleter2 = new Ajax.Autocompleter('description','productSearchByDescriptionResult','SearchProductsAction.do',{								
		paramName:'description',
		parameters : 'priceListId=' + priceList,
		minChars:1,								
		frequency:1.0,
		afterUpdateElement: afterAutocomplete,
		onShow : showResult													
		});
		
	$('description').Autocompleter = productAutocompleter2;
};

var afterAutocomplete = function(e1,e2){													
												
	var barcode = e2.getAttribute('barcode');
	if(barcode=='null')
	{
		barcode = "";
		$('productQuery').value = '';		
		alert('Product has no barcode!!');
		return false;
	}
	var productId = e2.getAttribute('productId');													
	//$('barcode').value = barcode;
	$('productId').value= productId;

	var description = e2.getAttribute('description');
	//$('description').value = description;
	
	incrementCart(productId);
	
	//reset focus to search input
	searchElement.focus();	
	
	//show product details
	getProductInfo(productId);	
	
	//set global product 
	product_id = productId;	
	
	$('description').value = "";
	$('productQuery').value = "";						
																																									
};

function loadPriceLists()
{
	var url = 'PriceListAction.do';
	var pars = 'action=loadPriceLists';	
			
	var myAjax = new Ajax.Request( url, 
	{ 
		method: 'get', 
		parameters: pars, 
		onSuccess: showPriceLists, 
		onFailure: reportError
	});
}

function showPriceLists(request)
{
	
	try
	{
		var response = request.responseText;
		$('priceListName').innerHTML = response;	
		$('priceLists').innerHTML = '<div class="buttonLeft" id="changePriceList" onclick="savePriceList()">SAVE</div>';
	}
	catch(e)
	{
		showErrorMessage(e);
	}
}

function savePriceList()
{
	
	try
	{
		$('changePriceList').innerHTML  = 'Please Wait...';
		var priceListSelection = document.getElementById('priceList');
		var priceListID = priceListSelection.options[priceListSelection.selectedIndex].value;
		var productId = product_id;
		
		priceList = priceListID;
		$('priceListId').value = priceListID;
		var url = 'POSProductAction2.do';
		var pars = 'action=getProductDetails&productId='+productId+'&priceListId='+priceListID;
				
		var myAjax = new Ajax.Request( url, 
		{ 
			method: 'get', 
			parameters: pars, 
			onSuccess: showProductInfo,
			onComplete: reloadBarcodeCart(productId, priceListID),
			onFailure: reportError
		});
		
		
		
		var priceListName = priceListSelection.options[priceListSelection.selectedIndex].text;
		$('priceListName').innerHTML = priceListName;
		$('priceLists').innerHTML = '<div class="buttonLeft" id="changePriceList" onclick="loadPriceLists()">CHANGE</div>';
	}
	catch(e)
	{
		showErrorMessage(e);
	}
}

//Reload shopping cart
function reloadBarcodeCart(productId, priceListID)
{
	try
	{				

		var url = 'AddToBarcodeAction.do';
		var pars = 'action=reloadBarcodeCart&productId='+productId+'&priceListId='+priceListID;
		
		var myAjax = new Ajax.Request( url, 
		{ 
			method: 'get', 
			parameters: pars, 
			onSuccess: refreshShoppingCart, 
			onFailure: reportShoppingCartError
		});	
		
	}
	catch(e)
	{
		toConsole(e);
	}		

}


function getProductInfo(productId)
{
	var url = 'POSProductAction2.do';
	var pars = 'action=getProductDetails&productId='+productId+'&priceListId='+priceList;
				
	var myAjax = new Ajax.Request( url, 
	{ 
		method: 'get', 
		parameters: pars, 
		onSuccess: showProductInfo, 
		onFailure: reportError
	});
}

function showProductInfo(request)
{
	//
	try
	{
		var response = request.responseText;
		var product = eval('(' + response + ')');
		
		var salePriceIncVat = new Number(product.priceStd) * ((new Number(product.taxRate) + new Number(100))/new Number(100));
		
		//display product info in right column
		$('product.name').innerHTML = product.name;	
		$('product.barcode').innerHTML = product.barcode;
		$('product.taxCategory').innerHTML = product.taxCategory;
		$('product.description').innerHTML = product.description;
		
		if($('isProductName').checked == true && $('isProductDescription').checked == true)
		{
			splitDescription(product.name + ' ' + product.description);
		}
		else
		{
			checkPrintOptions();	
		}
		
		//$('barcode.product.name').innerHTML = product.name;
		$('product.stock.qty').innerHTML = product.stockQty;
		$('product.price.std').innerHTML = new Number(product.priceStd).toFixed(2);
		$('barcode.product.price.std').innerHTML = new Number(salePriceIncVat).toFixed(2);
		$('terminal.currency.sysmbol').innerHTML = product.currSymbol;
		$('product.price.limit').innerHTML = new Number(product.priceLimit).toFixed(2);
		$('product.price.list').innerHTML = new Number(product.priceList).toFixed(2);
		
		if(product.barcode != 'null')
		{
			$('product.image').src = 'BarcodeAction.do?action=getBarcodeImage&barCode= ' + product.barcode;
		}
		else
		{
			$('product.image').src = 'images/pos/blankimage.png';
		}
		calcLabels();
	}
	catch(e)
	{
		showErrorMessage(e);
	}
}

function reportError(request)
{
	alert("Ooops some error has occured while communicating with the server");
}

function splitDescription(description)
{
	var desc = description;
    var temp = description.split(" ");
    var title = " ";
    var subtitle = " ";
	var str = "";
	var i, n;
	
	if(desc.length >= 23)
	{
		for(i=0; i<temp.length; i++)
   		{
   			str = str + temp[i];
   			str = str + " ";
   		    
   		    if(str.length >= 23)
   		    {
   		        for(n=0; n<i; n++)
   		        {
   		            title += temp[n] + " ";
   		        }
   		        
   		       subtitle = description.substring(title.length-1, description.length);
   		       break;
   		    }
   		}
   	}
	else
	{
	    subtitle = description;
	    title = "";
	}
	
	if(title != 'null')
	{
		$('barcode.product.description1').innerHTML = title;
	}
	
	if(subtitle != 'null')
	{
		$('barcode.product.description2').innerHTML = subtitle;
	}
	
}

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

var initCartBtn = function(){
	var plus = $('plusBtn');
	var minus = $('minusBtn');
	var del = $('deleteBtn');
	
	plus.style.cursor = 'pointer';
	minus.style.cursor = 'pointer';
	del.style.cursor = 'pointer';
	
	plus.onclick = function(e){
		if(product_id != null)
		incrementCart(product_id);
		$('barcode').focus();
	};
	
	minus.onclick = function(e){
		if(product_id != null)
		decrementCart(product_id);
		$('barcode').focus();
	};
	
	del.onclick = function(e){
		if(product_id != null)
		deleteItemFromCart(product_id);
		$('barcode').focus();
	};
};



var initAjaxResponder = function(){
		
	Ajax.Responders.register({
	  onCreate: function() {
	    $('indicator').style.display = 'block';
	  },
	  onComplete: function() {
	    $('indicator').style.display = 'none';
	  }
	});
};


function scrollCart()
{
	var shoppingCart = $('shoppingCart');	
	
	if(cartIndex > maxRows)
	{
		shoppingCart.scrollTop = shoppingCart.scrollHeight;
	}
	else
	{
		shoppingCart.scrollTop = 0;
	}
}

function calcLabels()
{
	var numLabels = $('cartTotal').innerHTML;
	var index = numLabels.toString().indexOf('.')
	
	if( index != -1)
	{
		numLabels = numLabels.substring(0, index);
	}
			
	$('total').innerHTML = 'No of Labels: ' + numLabels;
}

Event.observe(window,'load',initScreen,false);