//scripts for Posterita Ordering Screen
var product_id = null;
//var qty = null;
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
var DISCOUNT_LIMIT = null;
var OVERRIDE_PRICE_LIMIT = null;
var ISDISCOUNTONTOTAL = null;
var ISDISCOUNTUPTOLIMITPRICE = null;
var LIMIT_PRICE = null;
var TOTAL_LIMIT_PRICE = null;
var TOTAL_LIST_PRICE = null;
var GRAND_TOTAL = null;
var LIST_PRICE = null;
var ISTAXINCLUDED = null;
var TAX_RATE = null;
var ERROR_MSG = null;
var ORIG_TOTAL = null;
var ORIG_TOTAL_LINES = null;
var itemRowHeight = null;
var totalAmount = null;
var maxRows = 15;
var done = false;
var isQuickDiscount = false;
var isDiscountFormOpen = false;

var ORDER_TYPES = { 
	POS_ORDER : 'POS Order',
	CUSTOMER_RETURNED_ORDER : 'Customer Returned Order',
	POS_GOODS_RECEIVE_NOTE : 'POS Goods Receive Note',		
	POS_GOODS_RETURNED_NOTE : 'POS Goods Returned Note'
};
	
var PAYMENT_RULES = {
	PAYMENT_RULE_CARD : 'Card',
	PAYMENT_RULE_CASH : 'Cash',
	PAYMENT_RULE_CHEQUE : 'Cheque',
	PAYMENT_RULE_CREDIT : 'Credit',
	PAYMENT_RULE_MIXED : 'Mixed'
};

var SO_CREDIT_STATUS = {
	CreditStop 		: "S",
	CreditHold 		: "H",
	CreditWatch 	: "W",
	NoCreditCheck 	: "X",
	CreditOK 		: "O"
};

var BP = {
	id : -1,
	name : null,
	openBalance : 0,
	creditStatus : null,
	creditLimit : null,
	creditAvailable : null,
	priceList : null,
	priceListName : null,
	checkCreditStatus : function(){
		if(this.creditStatus == SO_CREDIT_STATUS.CreditOK)
		if(this.creditLimit > 0) return true;
		
		return false;
	}
};

var BP_CREDIT_STATUS = SO_CREDIT_STATUS.CreditOK;

var initScreen = function(){
	DISCOUNT_LIMIT = getDiscountAllowed();
	OVERRIDE_PRICE_LIMIT = overrideLimit;
	ISDISCOUNTUPTOLIMITPRICE = isDiscountUptoLimitPrice;
	ISDISCOUNTONTOTAL = isDiscountAllowedOnTotal;
	
	
	if(!ISDISCOUNTUPTOLIMITPRICE)
	{
		$('discountUptoLimitPrice').hide();
	}
	
	if(!OVERRIDE_PRICE_LIMIT)
	{
		$('overridePriceLimit').hide();
	}
	
	if(!ISDISCOUNTONTOTAL)
	{
		$('discountAllowedOnTotal').hide();
		$('linesTotal').hide();
	}
	
	if(DISCOUNT_LIMIT == 0)
	{
		$('userDiscLimit').hide();
	}
	
	//reset user pin
	$('resetPinBtn').hide();
	
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
	initPIN();
	initShortcuts();
	initPaymentBtns();	
	initSystemBtns();
	
	//set payment rule
	if(tenderType == null)
	{
		tenderType = PAYMENT_RULES.PAYMENT_RULE_CASH;
		simulateOnClick($('cashBtn'));
	}
	else
	{
		simulateOnClick($(tenderType + 'Btn'));
	}
	
	$('total').innerHTML = $('cartTotal').innerHTML;
	
	
	searchElement.focus();
	
	var barcode = $('barcode');
	barcode.focus();
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
	
	var incPrice = $('incPrice');
	incPrice.onkeyup = function(e){
		
		ERROR_MSG = null;
		var DISC_PERC = null;
		
		var discount = LIST_PRICE - this.value;
		DISC_PERC = new Number((discount / LIST_PRICE)*100).toFixed(2);
		$('disc').value = DISC_PERC;
		
		if (isNaN(this.value))
		{
			showErrorMessage('Invalid Price Entered: ' + newPrice, this);
			this.value = LIST_PRICE;
			return;
		}
		
		var newPrice = parseFloat(this.value);
		
		if(this.value == "")
		{
			newPrice = 0;
		}
		
				
		var totalAmt = $('qty').value * newPrice;
		$('totalAmount').value = new Number(totalAmt).toFixed(2);
		
		$('discountOnTotal').value = $('isDiscOnTotal').value = 'false';
		$('isDiscOnInclUnitPrice').value = 'true';
		$('isDiscOnPerc').value = 'false';
		
		validateDiscount(parseFloat(newPrice), parseFloat(DISC_PERC));		
			
	};
	
	var disc = $('disc');
	disc.onkeyup = function(e){
		
		ERROR_MSG = null;
		var DISC_PERC = null;
	
		DISC_PERC = this.value;
		$('disc').value = DISC_PERC;
		
		var discAmt = new Number((DISC_PERC / 100) * LIST_PRICE);
		var newPrice = new Number(LIST_PRICE - discAmt).toFixed(2);
		$('incPrice').value = newPrice;
		var newTotal = new Number(((100 - DISC_PERC) / 100) * (LIST_PRICE * $('qty').value)).toFixed(2);
		$('totalAmount').value = newTotal;
		
		$('discountOnTotal').value = $('isDiscOnTotal').value = 'false';
		$('isDiscOnInclUnitPrice').value = 'true';
		$('isDiscOnPerc').value = 'false';
		
		validateDiscount(newPrice, DISC_PERC);		
	};	
	
	
	var totalAmount = $('totalAmount');
	totalAmount.onkeyup = function(e){
		
		ERROR_MSG = null;
		var DISC_PERC = null;
		var qty = $('qty').value;
		var total = this.value;
		var totalPriceLimit = LIMIT_PRICE * qty;
		var newPrice = new Number(total/qty).toFixed(2);
		$('incPrice').value = newPrice;
		
		var disount = LIST_PRICE - newPrice;
		DISC_PERC = new Number((discount/LIST_PRICE) * 100).toFixed(2);
		$('disc').value = DISC_PERC;	
		
		$('discountOnTotal').value = $('isDiscOnTotal').value = 'false';
		$('isDiscOnInclUnitPrice').value = 'true';
		$('isDiscOnPerc').value = 'false';
		
		validateDiscount(newPrice, DISC_PERC);		
	};
	
	var GrandTotal = $('grandTotal');
	GrandTotal.onkeyup = function(e)
	{
		var totalLines = this.value;
		var ERROR_MSG = null;
		
		if((ISDISCOUNTUPTOLIMITPRICE == true) && (OVERRIDE_PRICE_LIMIT == false))
		{
			if((totalLines > TOTAL_LIST_PRICE) && (ORIG_TOTAL_LINES != totalLines))
			{
				ERROR_MSG = "DISCOUNTED TOTAL EXCEEDS TOTAL LINES!!!";
			}
			if((totalLines < TOTAL_LIMIT_PRICE) && (ORIG_TOTAL_LINES != totalLines))
			{
				ERROR_MSG = "TOTAL DISCOUNT EXCEEDED!!!";
			}
	
			if((totalLines > TOTAL_LIST_PRICE) && (totalLines < TOTAL_LIMIT_PRICE))
				ERROR_MSG = null;
		}
		
		if((ISDISCOUNTUPTOLIMITPRICE == false) && (OVERRIDE_PRICE_LIMIT == true))
		{
			if(DISCOUNT_LIMIT > 0)
			{
				var discount = TOTAL_LIST_PRICE - totalLines;
				DISC_PERC = new Number((discount/TOTAL_LIST_PRICE)*100).toFixed(2);
				
				if(DISC_PERC > DISCOUNT_LIMIT)
				{
					ERROR_MSG = "DISCOUNT LIMIT EXCEEDED!!!";
				}
				
				discAmt = totalLines - GRAND_TOTAL; 
				if(discAmt > 0)
				{
					ERROR_MSG = "DISCOUNT CANNOT BE NAGATIVE";
				}
				
			}
			
			if(DISCOUNT_LIMIT == 0)
			{
				ERROR_MSG = "CANNOT APPLY DISCOUNT!!!<br>NO DISCOUNT PERCENTAGE SET";
			}	
		}
		
		if((ISDISCOUNTUPTOLIMITPRICE == false) && (OVERRIDE_PRICE_LIMIT == false))
		{
			if(DISCOUNT_LIMIT > 0)
			{
				if((totalLines < TOTAL_LIMIT_PRICE) && (ORIG_TOTAL_LINES != totalLines))
				{
					ERROR_MSG = "CANNOT OVERRIDE LIMIT PRICE!!!";
				}
				
				if((totalLines > TOTAL_LIST_PRICE) && (ORIG_TOTAL_LINES != totalLines))
				{
					ERROR_MSG = "DISCOUNTED TOTAL EXCEEDS TOTAL LINES!!!";
				}
			}
			
			if(DISCOUNT_LIMIT == 0)
			{
				ERROR_MSG = "CANNOT APPLY DISCOUNT!!!<br>NO DISCOUNT PERCENTAGE SET";
			}	
		}
		
		if((ISDISCOUNTUPTOLIMITPRICE == true) && (OVERRIDE_PRICE_LIMIT == true))
		{
			$('discountUptoLimitPrice').className = "error";
			$('overridePriceLimit').className = "error";
			ERROR_MSG = "DISCOUNT ERROR!! CANNOT PROCESS YOUR REQUEST!!!";
		}
		
		if(ERROR_MSG == null)
		{
			if(ORIG_TOTAL_LINES == totalLines)
			{
				$('applyDiscBtn').disabled = true;
			}
			else
			{
				$('applyDiscBtn').disabled = false;
			}
		}
		else
		{
			$('applyDiscBtn').disabled = true;
		}
		
		$('errorMsg').innerHTML = ERROR_MSG;
		
		$('discountOnTotal').value = $('isDiscOnTotal').value = 'true';
		$('isDiscOnInclUnitPrice').value = 'false';
		$('isDiscOnPerc').value = 'false';
			
	};
		
	
	$('qty').onkeyup = function(e){
		if(e.keyCode == Event.KEY_RETURN)
		{
			//update qty
			done = true;
			updateQty(product_id, this.value);
		}
	};
	
	$('checkoutBtn').onclick = function(e){
		initSystemBtns();
		this.className = 'selected';
		if(cartLines == null || cartLines.length == 0)
		{
			showErrorMessage('Shopping cart is empty!', 'productQuery');
		}		
		else
		{
			if(orderType == ORDER_TYPES.CUSTOMER_RETURNED_ORDER)
			{
				$('creditPanel').show();
			}
			else if(orderType == ORDER_TYPES.POS_GOODS_RETURNED_NOTE)
			{
				$('creditPanel').show();
			}
			else
			{
				activePanel.show();
			}
			
		}
	};
	
	$('openDrawerBtn').onclick = function(e){
		initSystemBtns();
		this.className = 'selected';
		openCashDrawer();
	};
	
	$('discountBtn').showDiscount = $('discountBtn').onclick
	$('discountBtn').onclick = function(){
		initSystemBtns();
		this.className = 'selected';
		if(cartLines == null || cartLines.length == 0)
		{
			showErrorMessage('Shopping cart is empty!', 'productQuery');
		}
		else
		{
			this.showDiscount();
		}
		
	};
	
	$('clearCartBtn').onclick = function(e){
		initSystemBtns();
		this.className = 'selected';
		cartIndex = null;
		cartLines = null;
		clearCart();
	};	
	
	$('quickDiscountBtn').onclick = function(e){
		initSystemBtns();
		this.className = 'selected';
		var QuickDiscountForm = $('QuickDiscountForm');
	
		if(cartLines == null || cartLines.length == 0)
		{
			isQuickDiscount = false;
			QuickDiscountForm.disable();
			showErrorMessage('Shopping cart is empty!', 'barcode');
		}
		else
		{
			if(!OVERRIDE_PRICE_LIMIT && !ISDISCOUNTUPTOLIMITPRICE && !ISDISCOUNTONTOTAL && DISCOUNT_LIMIT == 0)
			{
				isQuickDiscount = false;
				QuickDiscountForm.disable();
				$('errorMsg').innerHTML = "DISCOUNT NOT ALLOWED!!!";
				showErrorMessage('You are not allowed to give discount!', 'barcode');
			}
			else
			{
				isQuickDiscount = true;
				QuickDiscountForm.enable();
				
			}
			
		}
		
		if(isQuickDiscount)
		{
			if(isDiscountFormOpen)
			{
				closeDiscount();
			}
			else
			{			
				quickDiscount();
			}
		}
		else
		{
			closeDiscount();
		}
		
	};
	
	$('applyDiscBtn').onclick = function(e){
		if(cartLines == null || cartLines.length == 0)
		{
			showErrorMessage('Shopping cart is empty!', 'productQuery');
		}
		else
		{
			applyDiscount();
		}
	};
		
	$('changePinBtn').onclick = function(e){
			
			var isOverlay = $('PINPanel').style.display;
			
			if(isOverlay == 'none' || isOverlay == '')
			{
				popUp();
			}
			showDisableMask();
			popPanel($('PINPanel'));
			$('PIN').focus();
	};
	
	//add behaviour to existing cart
	addBehaviourToCart();	
	
	$('barcode').focus();
	showErrors();
};

var initPIN = function(){
	var pinPanel = $('PINPanel');
	var inputs = pinPanel.getElementsByTagName('input');
	
	pinPanel.pin = inputs[0];
	pinPanel.ok = inputs[1];
	pinPanel.cancel = inputs[2];
	
	var validatePIN = function()
	{
		if($('PINPanel').pin.value == null || $('PINPanel').pin.value == '')
		{
			return;
		}
		
		var url = 'AddToPOSShoppingCartAction.do';
		var pars = 'action=validatePIN&pin=' + $('PINPanel').pin.value;
		
		var refreshDiscountLimit = function(request){
			var response = request.responseText;
			var result = eval('(' + response + ')');
			
			if(result.error)
			{
				$('PINPanel').pin.value = '';
				showErrorMessage(result.error, 'PIN');
			}
			else
			{
				roleId = result.roleId;
				discountLimit = result.discountLimit;
				overrideLimit = result.overrideLimit;
				discountOnTotal = result.discountOnTotal;
				discountUptoPriceLimit = result.discountUptoPriceLimit;
				
				DISCOUNT_LIMIT = discountLimit;
				OVERRIDE_PRICE_LIMIT = overrideLimit;
				ISDISCOUNTONTOTAL = discountOnTotal;
				ISDISCOUNTUPTOLIMITPRICE = discountUptoPriceLimit;
				
				if(!ISDISCOUNTUPTOLIMITPRICE)
				{
					$('discountUptoLimitPrice').hide();
				}
				else
				{
					$('discountUptoLimitPrice').show();
				}
				
				if(!OVERRIDE_PRICE_LIMIT)
				{
					$('overridePriceLimit').hide();
				}
				else
				{
					$('overridePriceLimit').show();
				}
				
				if(!ISDISCOUNTONTOTAL)
				{
					$('discountAllowedOnTotal').hide();
				}
				else
				{
					$('discountAllowedOnTotal').show();
				}
				
				if(DISCOUNT_LIMIT == 0)
				{
					$('userDiscLimit').hide();
				}
				else
				{
					$('userDiscLimit').show();
				}
				
				$('isDiscountUptoLimitPrice').innerHTML = discountUptoPriceLimit + '';
				$('overrideLimit').innerHTML = overrideLimit + '';
				$('isDiscountOnTotal').innerHTML = discountOnTotal + '';
				$('discountLimit').innerHTML = discountLimit + '%';
				$('roleId').value = roleId;
				$('PINPanel').pin.value = '';
				
				$('resetPinBtn').show();
				$('changePinBtn').hide();
				popUp();
				hidePanel($('PINPanel'));			
				hideDisableMask();
				refreshCart();
			}
		};
	
		var myAjax = new Ajax.Request( url, 
		{ 
			method: 'get', 
			parameters: pars, 
			onSuccess: refreshDiscountLimit, 
			onFailure: reportShoppingCartError
		});	
	}
	
	pinPanel.ok.onclick = validatePIN;
	
	pinPanel.pin.onkeyup = function(e){
		if(e.keyCode == Event.KEY_RETURN)
		{
			validatePIN();
		}
	};
	
	pinPanel.cancel.onclick = function(e){
		$('PINPanel').pin.value = '';
		popUp();
		hidePanel($('PINPanel'));			
		hideDisableMask();
	};
};

function resetUser(roleId)
{
	var url = 'AddToPOSShoppingCartAction.do';
	var pars = 'action=resetUser&roleId=' + roleId;
	var myAjax = new Ajax.Request( url, 
	{ 
		method: 'get', 
		parameters: pars, 
		onSuccess: refreshUser, 
		onFailure: reportShoppingCartError
	});
	
}

function refreshUser(request)
{
	var response = request.responseText;
	var result = eval('(' + response + ')');
	
	if(result.error)
	{
		showErrorMessage(result.error, 'barcode');
	}
	else
	{
		roleId = result.roleId;
		discountLimit = result.discountLimit;
		overrideLimit = result.overrideLimit;
		discountOnTotal = result.discountOnTotal;
		discountUptoPriceLimit = result.discountUptoPriceLimit;
		
		DISCOUNT_LIMIT = discountLimit;
		OVERRIDE_PRICE_LIMIT = overrideLimit;
		ISDISCOUNTONTOTAL = discountOnTotal;
		ISDISCOUNTUPTOLIMITPRICE = discountUptoPriceLimit;
		
		$('isDiscountUptoLimitPrice').innerHTML = discountUptoPriceLimit + '';
		$('overrideLimit').innerHTML = overrideLimit + '';
		$('isDiscountOnTotal').innerHTML = discountOnTotal + '';
		$('discountLimit').innerHTML = discountLimit + '%';
		$('roleId').value = roleId;
		$('PINPanel').pin.value = '';
	
		$('changePinBtn').show();
		$('resetPinBtn').hide();

		refreshCart();
	}
}

var initShortcuts = function()
{
	shortcut.add("Ctrl+Space", function(e){simulateOnClick($('checkoutBtn'))});
	
	shortcut.add("F1", function(e){$('barcode').select();});
	shortcut.add("F2", function(e){$('productQuery').select();});
	shortcut.add("F3", function(e){$('description').select();});
	shortcut.add("F4", function(e){$('qty').select();});

	shortcut.add("F5", function(e){$('customerQuery').select();});	
	shortcut.add("F6", function(e){simulateOnClick($('discountBtn'))});	
	
	// Barcode Btn
	shortcut.add("Ctrl+B", function(e){simulateOnClick($('barcodeBtn'))});
	
	// Quick Discount
	shortcut.add("F8", function(e){simulateOnClick($('quickDiscountBtn'))});
	//payment btns
	shortcut.add("F9", function(e){simulateOnClick($('cashBtn'))});	
	shortcut.add("F10", function(e){simulateOnClick($('cardBtn'))});
	shortcut.add("F11", function(e){simulateOnClick($('chequeBtn'))});
	shortcut.add("F12", function(e){simulateOnClick($('mixBtn'))});
	
	shortcut.add("Ctrl+M", function(){simulateOnClick($('menuBtn'))});
	shortcut.add("Ctrl+D", function(){
		//simulateOnClick($('openDrawerBtn'));
		showErrorMessage('Opening Cash Drawer');
		
	});
	shortcut.add("Ctrl+Delete", function(){
		simulateOnClick($('clearCartBtn'));		
	});
	shortcut.add("Ctrl+P", function(){simulateOnClick($('changePinBtn'))});
	shortcut.add("Ctrl+R", function(){simulateOnClick($('resetPinBtn'))});
	shortcut.add("Ctrl+S", function(){simulateOnClick($('shortcutBtn'))});
	
	shortcut.add("Ctrl+C", function(){simulateOnClick($('creditBtn'))});
	
	//move between cart lines
	shortcut.add("Ctrl+Up", function(){
		closeDiscount();
		if(cartIndex > 0)
		{
			cartIndex = cartIndex -1;
			var line = cartLines[cartIndex];
			scrollCart();			
			simulateOnClick(line);	
		}			
	});
	
	shortcut.add("Ctrl+Down", function(){
		closeDiscount();
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
		closeDiscount();
		simulateOnClick($('plusBtn'));
	});
	
	shortcut.add("Ctrl+Left", function(){
		closeDiscount();
		simulateOnClick($('minusBtn'));
	});
	
	shortcut.add("Ctrl+Backspace", function(){
		closeDiscount();
		simulateOnClick($('deleteBtn'));
	});
	
	//Enable create product for GRN
	if(orderType == ORDER_TYPES.POS_GOODS_RECEIVE_NOTE)
	{
		shortcut.add("Alt+P", function(){CreateProduct.showPopup(0);});
		shortcut.add("Alt+U", function(){
			if(product_id != null)
			CreateProduct.showPopup(product_id);
			});
		
		shortcut.add("Esc", function(){
			CreateProduct.hidePopup();
		});
	}	
	
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
		$('m_productId').value = product.id;
		$('product.name').innerHTML = product.name;	
		$('product.barcode').innerHTML = product.barcode;
		$('product.taxCategory').innerHTML = product.taxCategory;
		$('product.description').innerHTML = product.description;
		$('product.stock.qty').innerHTML = product.stockQty;
		TAX_RATE = $('taxRate').value = new Number(product.taxRate).toFixed(2);		
		var taxPercentage = new Number(1 + (TAX_RATE / 100));
		
		ISTAXINCLUDED = product.isTaxIncluded;
		
		if(ISTAXINCLUDED == "false")
		{
			LIMIT_PRICE = $('priceLimit').innerHTML = new Number(product.priceLimit * taxPercentage).toFixed(2);
			LIST_PRICE = new Number(product.priceList * taxPercentage).toFixed(2);
			ORIG_TOTAL = new Number($('qty').value * product.priceList * taxPercentage).toFixed(2);
		}
		else
		{
			LIMIT_PRICE = $('priceLimit').innerHTML = new Number(product.priceLimit).toFixed(2);
			LIST_PRICE = new Number(product.priceList).toFixed(2);
			ORIG_TOTAL = new Number($('qty').value * product.priceList).toFixed(2);
		}		
		
		$('product.price.list').innerHTML = LIST_PRICE;
		
		if(ISDISCOUNTUPTOLIMITPRICE)
		{
			$('product.price.limit').innerHTML = LIMIT_PRICE;
		}
		
		$('product.image').src = 'images/newUI/productpreview.gif';
		
	}
	catch(e)
	{
		showErrorMessage(e);
	}
}

function reportError(request)
{
	alert("Some error has occured while communicating with the server");
	var w = window.open("","Error");
	var d = w.document;
	d.write(request.responseText);
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
	if(orderType == ORDER_TYPES.POS_GOODS_RECEIVE_NOTE || 
	orderType == ORDER_TYPES.POS_GOODS_RETURNED_NOTE)
	{
		var bpartner = $('bpartnerId').value;
		if(bpartner == null || bpartner == "")
		{ 
			showErrorMessage("Please choose a vendor", 'customerQuery');
			//$('bpartnerId').focus();
			
			return false;
		}
	}
	
	if(isCustomerCompulsory)
	{
		var bpartner = $('bpartnerId').value;
		if(bpartner == null || bpartner == "")
		{ 
			showErrorMessage("Please choose a customer", 'customerQuery');
			//$('bpartnerId').focus();
			
			return false;
		}
	}
	
	if(orderType == ORDER_TYPES.CREDIT_ORDER || orderType == ORDER_TYPES.POS_ORDER)
	if($('tenderType').value == PAYMENT_RULES.PAYMENT_RULE_CREDIT)
	{
		var bpartner = $('bpartnerId').value;
		if(bpartner == null || bpartner == "")
		{ 
			showErrorMessage("Please choose a customer", 'customerQuery');
			//$('bpartnerId').focus();
			
			return false;
		}
		
		if(BP.creditStatus == SO_CREDIT_STATUS.NoCreditCheck)
		{
			showErrorMessage("Please set credit status for customer", 'customerQuery');			
			return false;
		}
		
		if(BP.creditLimit == 0)
		{
			showErrorMessage("Please set credit limit for customer", 'customerQuery');			
			return false;
		}
		
		if(BP.openBalance > BP.creditLimit)	
		{
			showErrorMessage("Credit Limit has been exceed!", 'customerQuery');			
			return false;
		}
		
		if(BP.creditStatus != SO_CREDIT_STATUS.CreditOK)
		{
			var errmsg = 'Invalid credit status! ';
			
			if(BP.creditStatus == SO_CREDIT_STATUS.CreditHold)
			{
				errmsg += 'Credit Hold';
			}
			
			if(BP.creditStatus == SO_CREDIT_STATUS.CreditStop)
			{
				errmsg += 'Credit Stop';
			}
			
			if(BP.creditStatus == SO_CREDIT_STATUS.CreditWatch)
			{
				errmsg += 'Credit Watch';
			}
			
			showErrorMessage(errmsg, 'customerQuery');
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
	parameters : 'orderType=' + orderType, // if credit order, do not validate for standard customer
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
						if(e2.value)
						{
							bpartner.value = e2.value;							
						}
						else
						{
							bpartner.value = '';							
						}
						
						$('customer.code').innerHTML = bpartner.value;
						var creditLimit = e2.getAttribute('creditLimit');
						var creditAvailable = e2.getAttribute('creditAvailable');
						var openBalance = e2.getAttribute('openBalance');
						var creditStatus = e2.getAttribute('creditStatus');
						var name = e2.getAttribute('name');
						
						if(bpartnerName)
						{
							BP.id = bpartner.value;
							BP.name = name;
							BP.priceList = e2.getAttribute('priceList');
							BP.priceListName = e2.getAttribute('pricelistName');
							BP.creditStatus = creditStatus;
							BP.creditAvailable = parseFloat(creditAvailable);
							BP.creditLimit = parseFloat(creditLimit);							
							BP.openBalance = parseFloat(openBalance);						
							
							$('customerCode').style.display = 'table-row';
							$('customerName').style.display = 'table-row';
							$('creditAvailable').style.display = 'table-row';
							$('creditLimit').style.display = 'table-row';
							$('openBalance').style.display = 'table-row';
							
							$('customer.name').innerHTML = name;	
							$('credit.available').innerHTML = creditAvailable;
							$('credit.limit').innerHTML = creditLimit;
							$('open.balance').innerHTML = openBalance;
							
							BP_CREDIT_STATUS = creditStatus;
							
							if (e2.getAttribute('priceList') && parseInt(e2.getAttribute('priceList')) > 0)
							{
								priceList = e2.getAttribute('priceList');	
								$('priceListId').value = priceList;	
								$('terminal').innerHTML = e2.getAttribute('pricelistName');			
							}			
							else 
							{
								priceList = defaultPriceList;
								$('terminal').innerHTML = defaultPriceListName;
							}
							if(cartLines != null && cartLines.length != 0)
							{
								//reload shopping cart for partner
								if(oldPriceList != priceList)
								{
									setShoppingCartPriceList();
									oldPriceList = priceList;
								}	
							}
							$('productQuery').Autocompleter.options.defaultParams='priceListId=' + priceList;
							saveBPartnerInfo(bpartner.value, name, creditLimit, priceList);										
						}//if
					}
					else
					{
						bpartner.value = "";	
						if(bpartnerName)
						{
							bpartnerName.value = "";														
						}//if
					}//if
					
					
					$('customerQuery').value = "";		
					
					searchElement.focus();
			}												
																	
		});

}

function saveBPartnerInfo(bPartnerValue, name, creditLimit, priceListId)
{	
	try
	{				
		var url = 'CheckoutAction.do';
		var pars = 'action=saveBPartnerInfo&bPartnerId='+bPartnerValue +'&name='+name+'&creditLimit='+creditLimit+'&priceListId='+priceListId;
		
		var myAjax = new Ajax.Request( url, 
		{ 
			method: 'get', 
			parameters: pars
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
		
		if(cartLines == null || cartLines.length == 0)
		{
			resetDetails();
		}
				
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



//----------------------------------------------------------------------------


var hidePaymentPanels = function(){
	paymentPanels.each(hidePanel);
};

var initOkCancel = function(panel){
	var inputs = panel.getElementsByTagName('input');
	
	if(inputs.length > 2)
	{
		var length = inputs.length;
		var ok = inputs[length-2];	
		var cancel = inputs[length-1];	
		
		ok.panel = panel;
		cancel.panel = panel;
		
		ok.onclick = function(){this.panel.validate();};
		cancel.onclick = function()
		{		
			hidePanel(this.panel); 
		};
		
		shortcut.add("Esc", function()
		{
			simulateOnClick(cancel);
		});
		
	}	
	
};

// add behaviour to different payment panels
var initCashPanel = function(){
	var cashPanel = $('cashPanel');
			
	if(orderType == ORDER_TYPES.POS_GOODS_RECEIVE_NOTE)
	{
		$('writeOffAmt').style.display = 'none';
		$('actualPaymentAmt').style.display = 'none';
	}
			
	$('amountTendered').focus();
	
	$('amountTendered').onkeypress = function(e){
		
		if(e.keyCode == Event.KEY_RETURN)
		{
			if(this.value == null || this.value == '')
			{
				showErrorMessage('Enter an amount!', 'amountTendered');
				return;
			}
			
			$('cashPanel').validate();			
		}
	}
	$('amountTendered').onkeyup = function(e){
									 	
		var amount = getCartTotal();
		var amountRefunded = null;
		var writeOffAmt = 0;
		
		var paymentAmt = new Number(amount).toFixed(2);
		if(orderType == ORDER_TYPES.POS_ORDER)
		{
			var roundOffFactor = $('roundOffFactor').value;
			var writeOffAmt = 0;
			
			if (roundOffFactor != null && roundOffFactor != 0)
			{
				var multiple = amount/roundOffFactor;
				var index1 = multiple.toString().indexOf('.');
				if(index1 == -1)
				{
					writeOffAmt = 0;
				}
				else
				{
					multiple = parseInt(multiple);
					writeOffAmt = amount - (multiple * roundOffFactor);
					writeOffAmt = writeOffAmt.toFixed(2);
				}
			}
			paymentAmt = new Number(amount - writeOffAmt).toFixed(2);
			$('writeOffCash').innerHTML = new Number(writeOffAmt).toFixed(2);
			$('paymentCash').innerHTML = paymentAmt;
		}
				
		amountRefunded = this.value - paymentAmt;
		//Round amountRefunded to two decimal places if its greater than 2 decimal places
		var amtstr = new Number(amountRefunded).toString();
		var index = amtstr.indexOf('.');
		
		var noOfDecimalPlaces = amountRefunded.toString().length - index;
		if(index != -1 && noOfDecimalPlaces > 2)
		{
			$('amountRefunded').innerHTML = Math.round(amountRefunded * 100) / 100;
		}
		else
		{
			$('amountRefunded').innerHTML = amountRefunded;
		}	
		
	};
	
	cashPanel.validate =function(){			
		var amount = getCartTotal();	
		var amountTendered = new Number($('amountTendered').value);
		var writeOff = $('writeOffCash').innerHTML;
		var writeOffAmt = new Number(writeOff);
		
		if (isNaN(amountTendered))
		{
			showErrorMessage('Please enter a numeric value', 'amountTendered');
			return;
		}
		if (isNaN(writeOffAmt))
		{
			writeOffAmt = 0;
		}
		var total = amountTendered + writeOffAmt;
				
		if(amount > total)
		{
			showErrorMessage('Amount tendered is less that total amount', 'amountTendered');
		}
		else
		{
			$FElement('tenderType').value = 'cash';
			$FElement('amountTendered').value = $('amountTendered').value;
			$FElement('amountRefunded').value = new Number(amountTendered - amount).toFixed(2);
			
			hidePanel(this);
			
			checkout();
			
		}
	};//cashPanel.validate
	
	cashPanel.show = function(){
		hidePaymentPanels();
		popPanel(this);
		$('cashTotal').innerHTML = $('total').innerHTML;	
		$('amountTendered').focus();
	};
	
	cashPanel.reset = function(){
		$('amountTendered').value = '';
		$('amountRefunded').innerHTML = '';
	};
};

var initCardPanel = function(){
	var cardPanel = $('cardPanel');

	$('cardNo').onkeypress = function(e){
	
		if(e.keyCode == Event.KEY_RETURN)
		{	
			if(this.value == null || this.value == '')
			{
				showErrorMessage('Enter Credit Card Number!','cardNo');
				return;
			}
					
			$('cardPanel').validate();			
		}
	};
	
	cardPanel.validate = function(){
		//validate credit card no
		var cardNo = $('cardNo').value;
		
		if(cardNo == null || cardNo == '')
		{
			showErrorMessage('Enter Credit Card Number!','cardNo');
		}
		else
		{
			$FElement('tenderType').value = 'card';
			$FElement('cardNo').value = cardNo;
			
			hidePanel(this);
			
			checkout();
		}
	};
	
	cardPanel.show = function(){
		hidePaymentPanels();
		popPanel(this);
		$('cardTotal').innerHTML = $('total').innerHTML;	
		$('cardList').focus();
	};
	
	cardPanel.reset = function(){
		$('cardNo').value = '';
		$('cardList').selectedIndex = 0;
	};
};

var initChequePanel = function(){
	var chequePanel = $('chequePanel');
		
	$('chequeNo').onkeypress = function(e){
	
		if(e.keyCode == Event.KEY_RETURN)
		{	
			if(this.value == null || this.value == '')
			{
				showErrorMessage('Enter Cheque Number!','chequeNo');
				return;
			}
					
			$('chequePanel').validate();			
		}
	};
	
	chequePanel.validate = function(){
		//validate cheque no
		var chequeNo = $('chequeNo').value;
		
		if(chequeNo == null || chequeNo == '')
		{
			showErrorMessage('Enter Cheque Number!');
		}
		else
		{
			$FElement('tenderType').value = 'cheque';
			$FElement('chequeNo').value = chequeNo;
			
			hidePanel(this);
			
			checkout();
		}
	};
	
	chequePanel.show = function(){
		hidePaymentPanels();
		popPanel(this);
		$('chequeTotal').innerHTML = $('total').innerHTML;	
		$('chequeNo').focus();
	};
	
	chequePanel.reset = function(){
		$('chequeNo').value = '';
	};
};

var initMixPanel = function(){
	var mixPanel = $('mixPanel');

	var bgColorError = '#FF0000';
	var bgColorNormal = '#FFFFFF';
	var cardAmount;
	var cashAmount;
	var chequeAmount;
	
	var inputs = mixPanel.getElementsByTagName('input');
	
	$('mix_cashAmt').onkeyup = function(e){
		var total = getCartTotal();
		
		if (this.value <= total && this.value >= 0)
		{
			cashAmount = new Number(this.value);
			cardAmount = new Number($('mix_cardAmt').value);
			chequeAmount = new Number($('mix_chequeAmt').value);
			
			var diff = (cashAmount + cardAmount + chequeAmount) - total;
			
			if (diff < 0)
			{
				cardAmount = total - (cashAmount + chequeAmount);
			}
			if (diff > 0)
			{
				var cardAmountDiff = cardAmount - diff;
				
				if (cardAmountDiff > 0)
				{
					cardAmount = cardAmountDiff;
				}
				else
				{
					chequeAmount = chequeAmount + cardAmountDiff;
					cardAmount = 0;
				}
			} 
			$('mix_cardAmt').value = new Number(cardAmount).toFixed(2);
			$('mix_chequeAmt').value = new Number(chequeAmount).toFixed(2);
			this.style.backgroundColor = bgColorNormal;
			inputs[5].disabled = false;
		}
		else
		{
			this.style.backgroundColor = bgColorError;
			inputs[5].disabled = true;
		}
	};
	
	$('mix_chequeAmt').onkeyup = function(e){
		var total = getCartTotal();
		
		if (this.value <= total && this.value >= 0)
		{
			 chequeAmount = new Number(this.value);
			 cashAmount = new Number($('mix_cashAmt').value);
			 cardAmount = new Number($('mix_cardAmt').value);
			 var diff = (chequeAmount + cashAmount + cardAmount) - total;
			 
			 if (diff < 0)
			 {
			 	cardAmount = total - (chequeAmount + cashAmount);
			 }
			 if (diff > 0)
			 {
			 	var cardAmountDiff = cardAmount - diff;
			 	if (cardAmountDiff > 0)
			 	{
			 		cardAmount = cardAmountDiff;
			 	}
			 	else
			 	{
			 		cashAmount = cashAmount + cardAmountDiff;
			 		cardAmount = 0;
			 	}
			 } 
			 $('mix_cardAmt').value = new Number(cardAmount).toFixed(2);
			 $('mix_cashAmt').value = new Number(cashAmount).toFixed(2);
			 this.style.backgroundColor = bgColorNormal;
			 inputs[5].disabled = false;
		}
		else
		{
			this.style.backgroundColor = bgColorError;
			inputs[5].disabled = true;
		}
	};
	
	$('mix_cardAmt').onkeyup = function(e){
		var total = getCartTotal();
		
		if(this.value <= total && this.value >= 0)
		{
			chequeAmount = new Number($('mix_chequeAmt').value);
			cashAmount = new Number($('mix_cashAmt').value);
			cardAmount = new Number(this.value);
			
			var diff = (cardAmount + cashAmount + chequeAmount) - total;
			
			if (diff < 0)
			{
				chequeAmount = total - (cardAmount + cashAmount);
			}
			if (diff > 0)
			{
				var chequeAmountDiff = chequeAmount - diff;
				
				if (chequeAmountDiff > 0)
				{
					chequeAmount = chequeAmountDiff;
				}
				else
				{
					cashAmount = cashAmount + chequeAmountDiff;
					chequeAmount = 0;	
				}
			}
			 $('mix_chequeAmt').value = new Number(chequeAmount).toFixed(2);
			 $('mix_cashAmt').value = new Number(cashAmount).toFixed(2);
			 this.style.backgroundColor = bgColorNormal;
			 inputs[5].disabled = false;
		}
		else
		{
			this.style.backgroundColor = bgColorError;
			inputs[5].disabled = true;
		}
	};
	
	$('mix_cashAmt').onkeypress = function(e){
	
		if(e.keyCode == Event.KEY_RETURN)
		{	
			mixPanel.validate();			
		}
	};
	
	$('mix_chequeAmt').onkeypress = function(e){
	
		if(e.keyCode == Event.KEY_RETURN)
		{	
			mixPanel.validate();			
		}
	};
	
	$('mix_cardAmt').onkeypress = function(e){
	
		if(e.keyCode == Event.KEY_RETURN)
		{	
			mixPanel.validate();			
		}
	};
	
	$('mix_chequeNo').onkeypress = function(e){
	
		if(e.keyCode == Event.KEY_RETURN)
		{	
			mixPanel.validate();			
		}
	};
	
	$('mix_cardNo').onkeypress = function(e){
	
		if(e.keyCode == Event.KEY_RETURN)
		{	
			mixPanel.validate();			
		}
	};
	
	mixPanel.validate = function(){
		//validate net amt
		if (inputs[5].disabled == true)
		{
			return;
		}
		
		var total = getCartTotal();
		
		var cashAmt = $('mix_cashAmt').value;		
		var chequeAmt = $('mix_chequeAmt').value;
		var chequeNo = $('mix_chequeNo').value;		
		var cardAmt = $('mix_cardAmt').value;
		var cardNo = $('mix_cardNo').value;
		
		if((cashAmt == null || cashAmt == '') && (chequeAmt == null || chequeAmt == '') && (cardAmt == null || cardAmt == ''))
		{
			showErrorMessage('Please enter an amount', 'mix_cashAmt');
		}
		else if((chequeNo == null || chequeNo == '') && (chequeAmt.length > 0))
		{
			showErrorMessage('Enter Cheque Number!', 'mix_chequeNo');
		}
		else if(chequeNo.length > 0 && (chequeAmt == null || chequeAmt == '') && (chequeAmt != 0))
		{
			showErrorMessage('Enter Cheque Amount!', 'mix_chequeAmt');
		}
		else if(cardNo.length > 0 && (cardAmt == null || cardAmt == ''))
		{
			showErrorMessage('Enter Card Amount!', 'mix_cardAmt');
		}
		else if((cardNo == null || cardNo == '') && (cardAmt.length > 0) && (cardAmt != 0))
		{
			showErrorMessage('Enter Card Number!', 'mix_cardNo');
		}
		else
		{
			var total1 = new Number(0);
			var cashAmt = new Number($('mix_cashAmt').value);		
			var chequeAmt = new Number($('mix_chequeAmt').value);
			var cardAmt = new Number($('mix_cardAmt').value);
			
			if (!isNaN(cashAmt))
			{
				total1 = total1 + cashAmt;
			}
			if (!isNaN(cardAmt))
			{
				total1 = total1 + cardAmt;
			}
			if (!isNaN(chequeAmt))
			{
				total1 = total1 + chequeAmt;
			}
			
			if(total1 < total)
			{
				showErrorMessage('Amount tendered is less than total amount!', 'mix_cashAmt');
			}
			else
			{
				$FElement('tenderType').value = 'mixed';
				$FElement('chequeNo').value = chequeNo;
				$FElement('chequeAmt').value = chequeAmt;
				$FElement('cardNo').value = cardNo;
				$FElement('cardAmt').value = cardAmt;
				$FElement('cashAmt').value = cashAmt;
				
				hidePanel(this);
				
				checkout();
			}
			
		}
	};
	
	mixPanel.show = function(){
		hidePaymentPanels();
		popPanel(this);
		$('mixTotal').innerHTML = $('total').innerHTML;			
				
		$('mix_cashAmt').value = getCartTotal();
		$('mix_cashAmt').select();	
		
	};
	
	mixPanel.reset = function(){
		$('mix_cashAmt').value = '';		
		$('mix_chequeAmt').value = '';
		$('mix_chequeNo').value = '';		
		$('mix_cardAmt').value = '';
		$('mix_cardNo').value = '';
	};
};

var initCreditPanel = function(){
	var creditPanel = $('creditPanel');
	
	creditPanel.show = function(){
		$('isCustomerCompulsory').value = 'true';
		isCustomerCompulsory = 'true';
		
		if (!creditPanel.validate())
		{
			creditPanel.reset();
			return;
		}
		checkout();
	};
	
	creditPanel.reset = function(){
		if ($('orderType').value == ORDER_TYPES.POS_ORDER)
		{
			$('isCustomerCompulsory').value = 'false';
		}		
	};
	
	creditPanel.validate = function(){
		return validateBp();
	};
	
};


var initPaymentPanels = function(){
	paymentPanels = [$('cashPanel'), $('cardPanel'), $('chequePanel'), $('mixPanel'), $('creditPanel')];
	paymentPanels = $A(paymentPanels);
	paymentPanels.each(initOkCancel);

	initCashPanel();
	initCardPanel();
	initChequePanel();
	initMixPanel();	
	initCreditPanel();
};

var initPaymentBtns = function(){

	initPaymentPanels();	
	
	paymentbtns = new Array();
	paymentbtns.push($('cashBtn'));
	paymentbtns.push($('cardBtn'));
	paymentbtns.push($('chequeBtn'));
	paymentbtns.push($('mixBtn'));
	paymentbtns.push($('creditBtn'));
	paymentbtns = $A(paymentbtns);
	
	for(var i=0; i<paymentbtns.length; i++)
	{
		var btn = paymentbtns[i];
		
		btn.panel = paymentPanels[i];
		
		btn.onclick = function(e){
			if (this == $('cashBtn'))
				$('tenderType').value = PAYMENT_RULES.PAYMENT_RULE_CASH;
			else if (this == $('cardBtn'))
				$('tenderType').value = PAYMENT_RULES.PAYMENT_RULE_CARD;
			else if (this == $('chequeBtn'))
				$('tenderType').value = PAYMENT_RULES.PAYMENT_RULE_CHEQUE;
			else if (this == $('mixBtn'))
				$('tenderType').value = PAYMENT_RULES.PAYMENT_RULE_MIXED;
			else if (this == $('creditBtn'))
				 $('tenderType').value = PAYMENT_RULES.PAYMENT_RULE_CREDIT;
					
			for(var i=0; i<paymentbtns.length; i++)
			{
				paymentbtns[i].className = 'pointer';
			}
			
			this.className = 'selected';
			activePanel = this.panel;				
		};
	}
	
};


function getCartTotal()
{
	var amtStr = $('total').innerHTML;
	amtStr = amtStr.strip();
	amtStr = amtStr.replace(',', "")
	
	var amount = parseFloat(amtStr);
	while(isNaN(amount))
	{
		if(amtStr.length == 0) return 0.0;
		
		amtStr = amtStr.slice(1);
		amount = parseFloat(amtStr);
	}
	
	return amount;
}

function initSystemBtns()
{
	
	systemBtns = new Array();
	
	systemBtns.push($('checkoutBtn'));
	systemBtns.push($('discountBtn'));
	systemBtns.push($('openDrawerBtn'));
	systemBtns.push($('clearCartBtn'));
	systemBtns.push($('quickDiscountBtn'));
	systemBtns.push($('resetPinBtn'));
	systemBtns.push($('changePinBtn'));

	systemBtns = $A(systemBtns);
			
	for(var i=0; i<systemBtns.length; i++)
	{
		systemBtns[i].className = 'pointer';
	}
}

var CreateProduct = {
	popup : null,
	form : null,
	updateCart : false,
	visible : false,
	
	createPopup : function(productId){
		var div = document.createElement('div');
		div.innerHTML = "<div id='frameHandle'><a href='javascript:void(0);' onclick='CreateProduct.hidePopup()'>Close</a></div>" +
				"<iframe src='POSProductAction3.do;jsessionid=" + sessionId + "?action=getCreateProductForm&productId=" + productId +
				"' width='100%' height='100%' frameborder='0'>Please wait .....</iframe>";
		div.style.border = 'solid 2px #333';
		div.style.width = '800px';
		div.style.height = '700px';
		div.style.position = 'absolute';
		div.style.zIndex = '1000';
		div.style.backgroundColor = '#FFF';	
		div.style.visibility = 'hidden';
		document.body.appendChild(div);	
		this.popup = div;		
		popPanel(this.popup);
	},
		
	showPopup : function(productId){
		if(this.visible) return;		
		this.updateCart = (productId != 0); 
		this.createPopup(productId);
		this.visible = true;	
	},
	
	hidePopup : function(){
		hidePanel(this.popup);
		if(this.updateCart)
		{
			updateQty(product_id, $('qty').value);
			this.updateCart = false;
		}
		this.visible = false;
	}
};


function resetDetails()
{
	$('product.name').innerHTML = "";	
	$('product.barcode').innerHTML = "";
	$('product.taxCategory').innerHTML = "";
	$('product.description').innerHTML = "";
	$('product.stock.qty').innerHTML = "";
	$('product.price.list').innerHTML = "";
	$('product.price.limit').innerHTML = "";
	$('product.price.std').innerHTML = "";
	$('priceLimit').innerHTML = "";
	$('qty').value = "";
	$('incPrice').value = "";
	$('disc').value = "";
	$('totalAmount').value = "";
	$('grandTotal').value = "";
	$('customer.code').innerHTML = "";
	$('customer.name').innerHTML = "";	
	$('credit.available').innerHTML = "";
	$('credit.limit').innerHTML = "";
	$('open.balance').innerHTML = "";
	$('barcode').value = "";
	$('productQuery').value = "";
	$('description').value = "";
	
	$('customerCode').hide();
	$('customerName').hide();
	$('creditAvailable').hide();
	$('creditLimit').hide();
	$('openBalance').hide();
	$('barcode').focus();
	closeDiscount();
	
}

function quickDiscount()
{
	isQuickDiscount = true;
	isDiscountFormOpen = true;
	$('customerContainer').hide();
	$('paymentContainer').hide();
	$('editProductContainer').hide();
	$('quickDiscountDetails').show();
	$('quickDiscountDetails').show();
	$('checkoutContainer').hide();
	$('incPrice').activate();
}

function closeDiscount()
{
	isQuickDiscount = false;
	isDiscountFormOpen = false;
	$('customerContainer').show();
	$('paymentContainer').show();
	$('editProductContainer').show();
	$('quickDiscountDetails').hide();
	$('quickDiscountDetails').hide();
	$('checkoutContainer').show();
	$('barcode').focus();
}

function validateDiscount(newPrice, DISC_PERC)
{
	ERROR_MSG = null;	
	
	if((ISDISCOUNTUPTOLIMITPRICE == true) && (OVERRIDE_PRICE_LIMIT == false))
	{
		if(newPrice < LIMIT_PRICE)
		{
			ERROR_MSG = "DISCOUNT AMOUNT EXCEEDED!!!";
		}
		if(newPrice > LIST_PRICE)
		{
			ERROR_MSG = "YOU CANNOT EXCEED THE LIST PRICE!!!"
		}
	}
	
	if((ISDISCOUNTUPTOLIMITPRICE == false) && (OVERRIDE_PRICE_LIMIT == true))
	{
		if(DISCOUNT_LIMIT > 0)
		{
			if(DISC_PERC > DISCOUNT_LIMIT)
			{
				ERROR_MSG = "DISCOUNT LIMIT EXCEEDED!!!";
			}
		}
		
		if(DISC_PERC < 0)
		{
			ERROR_MSG = "DISCOUNT CANNOT BE NEGATIVE!!!";
		}
		
		if(DISCOUNT_LIMIT == 0)
		{
			ERROR_MSG = "CANNOT APPLY DISCOUNT!!!<br>NO DISCOUNT PERCENTAGE SET";
		}	
	}
	
	if((ISDISCOUNTUPTOLIMITPRICE == false) && (OVERRIDE_PRICE_LIMIT == false))
	{
		if(DISCOUNT_LIMIT > 0)
		{
			if(DISC_PERC > DISCOUNT_LIMIT)
			{
				ERROR_MSG = "DISCOUNT LIMIT EXCEEDED!!!";
			}
			
			if(DISC_PERC < 0)
			{
				ERROR_MSG = "DISCOUNT CANNOT BE NEGATIVE!!!";
			}
			
			if(newPrice < LIMIT_PRICE)
			{
				ERROR_MSG = "CANNOT OVER RIDE LIMIT PRICE!!!";
			}
		}
		
		if(DISCOUNT_LIMIT == 0)
		{
			ERROR_MSG = "CANNOT APPLY DISCOUNT!!!<br>NO DISCOUNT PERCENTAGE SET";
		}	
	}
	
	if((ISDISCOUNTUPTOLIMITPRICE == true) && (OVERRIDE_PRICE_LIMIT == true))
	{
		$('discountUptoLimitPrice').className = "error";
		$('overridePriceLimit').className = "error";
		ERROR_MSG = "DISCOUNT ERROR!! CANNOT PROCESS YOUR REQUEST!!!"
	}
		
	if(ERROR_MSG == null)
	{
		$('applyDiscBtn').disabled = false;
	}
	else
	{
		$('applyDiscBtn').disabled = true;
	}
	
	$('errorMsg').innerHTML = ERROR_MSG;	
}

function applyDiscount()
{
	var incPrice = $('incPrice').value;
	var discPerc = $('disc').value;
	var totalAmount = $('totalAmount').value;
	
	if(incPrice == null || incPrice == "")
	{
		showErrorMessage('Inc Price cannot be null!', 'incPrice');
		return ;
	}
	
	if(discPerc == null || discPerc == "")
	{
		showErrorMessage('Discount % cannot be null!', 'disc');
		return ;
	}
	
	if(totalAmount == null || totalAmount == "")
	{
		showErrorMessage('Total amount cannot be null!', 'totalAmount');
		return ;
	}
	
	var form = $('QuickDiscountForm');	
	form.request({
		onComplete: refreshQuickDiscount		
	});
	
}

function refreshQuickDiscount(request)
{
	var response = request.responseText;
	var result = eval('(' + response + ')');
	
	if(result.error)
	{
		showErrorMessage(result.error, 'barcode');
		closeDiscount();
	}
	
	if(result.status == "OK")
	{
		reloadCart(); 
		closeDiscount();
	}
}

function trim(stringToTrim) 
{
	return stringToTrim.replace(/^\s+|\s+$/g,"");
}


function redirect(url)
{
	window.location = url;
}

function popUp()
{
	var check = $('pop_overlay').style.display;
	if(check == 'none' || check == '')
	{
		$('pop_overlay').style.display = 'block';
		$('pop_container').style.display = 'block';
	}
	else
	{
		$('pop_overlay').style.display = 'none';
		$('pop_container').style.display = 'none';
	}
	
}

Event.observe(window,'load',initScreen,false);