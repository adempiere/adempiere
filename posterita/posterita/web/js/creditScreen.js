//scripts for Posterita Ordering Screen
var product_id = null;
var qty = null;
var discountLimit = null;
var paymentPanels = null;
var activePanel = null;
var cartLines = null;
var cartIndex = null;
var orderType = null;
var tenderType = null;
var priceList = null;
var oldPriceList = -1;
var searchElement = null;
var isCustomerCompulsory = false;
var defaultPriceList = null;
var defaultPriceListName = null;

var ORDER_TYPES = { 
		POS_ORDER : 'POS Order',
		POS_GOOD_RECEIVE_NOTE : 'POS Goods Received Note',
		CREDIT_ORDER : 'Credit Order'
	};

var initScreen = function(){
	//set order type
	orderType = $('orderType').value;
	//set tender type
	tenderType = $('tenderType').value;
	//set priceList
	priceList = $('priceListId').value;
	
	//set default data for price list
	defaultPriceList = priceList;
	defaultPriceListName = $('terminal').innerHTML;
	
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
	
	//set is customer compulsory
	if($('isCustomerCompulsory').value == 'true')
	{
		isCustomerCompulsory = true;
	}
	
	initProductSearch();
	initSearchCustomer();
	initCartBtn();
	initAjaxResponder();
	initShortcuts();
	//initPaymentBtns();	
	
	//set payment rule
	//simulateOnClick($(tenderType + 'Btn'));
	
	$('total').innerHTML = $('cartTotal').innerHTML;
	
	
	searchElement.focus();
	
	var barcode = $('barcode');
	barcode.onkeyup = function(e){
		if(e.keyCode == Event.KEY_RETURN)
		{
			if(this.value != null && this.value != "")
			{				
				if(!validateBp())	
				{
					return;
				}
				
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
	
	$('checkoutBtn').onclick = function(e){
		if(cartLines == null || cartLines.length == 0)
		{
			showErrorMessage('Shopping cart is empty!');
		}
		else
		{
			checkout();//shoppingcart.js
		}
	};
	
	
	/*
	$('openDrawerBtn').onclick = function(e){
		openCashDrawer();
	};
	*/
	
	$('clearCartBtn').onclick = function(e){
		clearCart();
	};	
	
	$('discountBtn').showDiscount = $('discountBtn').onclick
	$('discountBtn').onclick = function(){
		
		if(cartLines == null || cartLines.length == 0)
		{
			showErrorMessage('Shopping cart is empty!');
		}
		else
		{
			this.showDiscount();
		}
		
	};
	
	
	//add behaviour to exixting cart
	addBehaviourToCart();
	try
	{
		showErrors();
	}
	catch(e){};
};

var initShortcuts = function(){
	shortcut.add("Ctrl+Space", function(e){simulateOnClick($('checkoutBtn'))});
	
	shortcut.add("F1", function(e){$('barcode').select();});
	shortcut.add("F2", function(e){$('productQuery').select();});
	shortcut.add("F3", function(e){$('description').select();});
	shortcut.add("F4", function(e){$('qty').select();});
	
	shortcut.add("F5", function(e){$('customerQuery').select();});	
	shortcut.add("F6", function(e){simulateOnClick($('discountBtn'))});	
	
	// Barcode Btn
	shortcut.add("Ctrl+B", function(e){simulateOnClick($('barcodeBtn'))});
	
	//payment btns
	//shortcut.add("F9", function(e){simulateOnClick($('cashBtn'))});	
	//shortcut.add("F10", function(e){simulateOnClick($('cardBtn'))});
	//shortcut.add("F11", function(e){simulateOnClick($('chequeBtn'))});
	//shortcut.add("F12", function(e){simulateOnClick($('mixBtn'))});
	
	shortcut.add("Ctrl+M", function(){simulateOnClick($('menuBtn'))});
	
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
			simulateOnClick(line);	
		}			
	});
	
	shortcut.add("Ctrl+Down", function(){
		if(cartIndex < cartLines.length - 1)
		{
			cartIndex = cartIndex + 1;
			var line = cartLines[cartIndex];
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

	if(!validateBp())	
	{
		return;
	}												
												
	var barcode = e2.getAttribute('barcode');
	if(barcode=='null')
	{
		barcode = "";
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
		
		//display product info in right column
		$('product.name').innerHTML = product.name;	
		$('product.barcode').innerHTML = product.barcode;
		$('product.taxCategory').innerHTML = product.taxCategory;
		$('product.description').innerHTML = product.description;
		$('product.stock.qty').innerHTML = product.stockQty;
		$('product.price.std').innerHTML = new Number(product.priceStd).toFixed(2);
		$('product.price.limit').innerHTML = new Number(product.priceLimit).toFixed(2);
		$('product.price.list').innerHTML = new Number(product.priceList).toFixed(2);
		
		$('product.image').src = 'images/pos/blankimage.png';
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

// returns true if bp is valid
var validateBp = function(){
	if(orderType == ORDER_TYPES.POS_GOOD_RECEIVE_NOTE)
	{
		var bpartner = $('bpartnerId').value;
		if(bpartner == null || bpartner == "")
		{ 
			showErrorMessage("Please choose a vendor",'customerQuery');			
			return false;
		}
	}
	
	if(orderType == ORDER_TYPES.CREDIT_ORDER)
	{
		var bpartner = $('bpartnerId').value;
		if(bpartner == null || bpartner == "")
		{ 
			showErrorMessage("Please choose a customer",'customerQuery');			
			return false;
		}
	}
	
	if(isCustomerCompulsory)
	{
		var bpartner = $('bpartnerId').value;
		if(bpartner == null || bpartner == "")
		{ 
			showErrorMessage("Please choose a customer");
			$('bpartnerId').focus();
			
			return false;
		}
	}
	
	return true;
};

var initSearchCustomer = function(){

	//validateBp(); // validates bp on load
		
	// auto complete for customer
	$('customerQuery').Autocompleter = new Ajax.Autocompleter('customerQuery','customerSearchResult','SearchCustomerAction.do',{
	paramName:'customerQuery',
	frequency:TROTTLE_TIME,	
	parameters : 'orderType=' + orderType,
	onShow:function(element, update){ 
      if(!update.style.position || update.style.position=='absolute') {
        update.style.position = 'absolute';
        Position.clone(element, update, {setHeight: false, offsetTop: element.offsetHeight});        
      }      
      update.style.display = 'block';      
    },
	afterUpdateElement:function(e1,e2){
					var bpartner = $('bpartnerId');
					var bpartnerName = $('customer.name');																							
					
																											
					if(e2.value != '-1')
					{
						toConsole('1');
						if(e2.value)
						{
							toConsole('1.1.1');
							bpartner.value = e2.value;							
						}
						else
						{
							toConsole('1.1.2');
							bpartner.value = '';							
						}
						
						$('customer.code').innerHTML = bpartner.value;
						var creditLimit = e2.getAttribute('creditLimit');
						var name = e2.getAttribute('name');
						setBPartner(bpartner.value, name, creditLimit);
						if(bpartnerName)
						{
							toConsole('1.2');
							$('customer.name').innerHTML = name;	
							$('credit.available').innerHTML = creditLimit;
							
							x = e2.getAttribute('priceList');
							if (parseInt(x) > 0)
							{
								$('terminal').innerHTML = e2.getAttribute('pricelistName');									
								$('priceListId').value = x;
								priceList = x;
							}
							else 
							{
								priceList = defaultPriceList;
								$('terminal').innerHTML = defaultPriceListName;
							}
							
							//reload shopping cart for partner
							if(oldPriceList != priceList)
							{
								setShoppingCartPriceList();
								oldPriceList = priceList;
							}	
							
							$('productQuery').Autocompleter.options.defaultParams='priceListId=' + priceList;															
						}//if
					}
					else
					{
						toConsole('2');
						
						bpartner.value = "";	
						if(bpartnerName)
						{
							toConsole('2.1');
							bpartnerName.value = "";														
						}//if
					}//if
					
					
					$('customerQuery').value = "";		
					
					searchElement.focus();																		
																								
			}												
																	
		});

}

function setBPartner(bPartnerValue, name, creditLimit)
{	
	try
	{				
		var url = 'CheckoutAction.do';
		var pars = 'action=setBPartner&bPartnerId='+bPartnerValue +'&name='+name+'&creditLimit='+creditLimit;
		
		var myAjax = new Ajax.Request( url, 
		{ 
			method: 'get', 
			parameters: pars, 
		});	
		
	}
	catch(e)
	{
		toConsole(e);
	}	
	return;	
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
		searchElement.focus();
	};
	
	minus.onclick = function(e){
		if(product_id != null)
		decrementCart(product_id);
		searchElement.focus();
	};
	
	del.onclick = function(e){
		if(product_id != null)
		deleteItemFromCart(product_id);
		searchElement.focus();
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

Event.observe(window,'load',initScreen,false);