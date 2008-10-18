//scripts for Posterita Ordering Screen
var product_id = null;
var cartLines = null;
var cartIndex = null;
var priceList = null;
var movementId = null;
var description = null;
var reference = null;
var oldPriceList = -1;
var searchElement = null;
var defaultPriceList = null;
var defaultPriceListName = null;
var ISTAXINCLUDED = null;
var TAX_RATE = null;
var ERROR_MSG = null;
var itemRowHeight = null;
var totalAmount = null;
var maxRows = 15;
var done = false;
var STOCKQTY = null;
var orgFromId = null;
var orgToId = null;

var initScreen = function(){
	priceList = $('priceListId').value;
	movementId = $('movementId').value;
	
	var orgFrom = $('orgFromId');
	orgFromId = orgFrom[orgFrom.selectedIndex].value;
	
	var orgTo = $('orgToId');
	orgToId = orgTo[orgTo.selectedIndex].value;
	
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
		
	initProductSearch();;
	initCartBtn();
	initAjaxResponder();
	initShortcuts();
	initSystemBtns();
		
	searchElement.focus();
	
	var barcode = $('barcode');
	barcode.focus();
	barcode.onkeyup = function(e){
		if(e.keyCode == Event.KEY_RETURN)
		{
			if(this.value != null && this.value != "")
			{				
				done = true;
								
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
			return false;
		}
	};
	
	
	$('clearCartBtn').onclick = function(e){
		initSystemBtns();
		this.className = 'selected';
		clearCart();
	};
	
	$('saveBtn').onclick = function(e){
		initSystemBtns();
		this.className = 'selected';
		
		if(cartLines == null || cartLines.length == 0)
		{
			showErrorMessage('Shopping cart is empty!', 'productQuery');
			return;
		}
		
		if(orgFromId == orgToId)
		{
			showErrorMessage('You cannot transfer stocks to the same organisation', 'orgFromId');
			return;
		}
		else
		{
			saveMMovement();
		}
	};	
	
	$('completeBtn').onclick = function(e){
		initSystemBtns();
		this.className = 'selected';
		
		if(cartLines == null || cartLines.length == 0)
		{
			showErrorMessage('Shopping cart is empty!', 'productQuery');
			return;
		}
		
		if(orgFromId == orgToId)
		{
			showErrorMessage('You cannot transfer stocks to the same organisation', 'orgFromId');
			return;
		}
		else
		{
			$('StockMovementForm').submit();
		}
	};
	
	$('newStockBtn').onclick = function(e){
		initSystemBtns();
		this.className = 'selected';

		redirect("StockMovementAction.do?action=createNewStockTransfer");		
	};
	
	//add behaviour to existing cart
	addBehaviourToCart();	
};

var initShortcuts = function()
{
	shortcut.add("Ctrl+Space", function(e){simulateOnClick($('checkoutBtn'))});
	
	shortcut.add("F1", function(e){$('barcode').select();});
	shortcut.add("F2", function(e){$('productQuery').select();});
	shortcut.add("F3", function(e){$('description').select();});
	shortcut.add("F4", function(e){$('qty').select();});
	
	shortcut.add("Ctrl+M", function(){simulateOnClick($('menuBtn'))});

	shortcut.add("Ctrl+Delete", function(){
		simulateOnClick($('clearCartBtn'));		
	});
	
	shortcut.add("Ctrl+R", function()
		{
			$('reference').focus();
		});
	
	shortcut.add("Ctrl+S", function(){simulateOnClick($('saveBtn'))});
	shortcut.add("Ctrl+C", function(){simulateOnClick($('completeBtn'))});
	
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
		$('product.name').innerHTML = product.name;	
		$('product.barcode').innerHTML = product.barcode;
		$('product.taxCategory').innerHTML = product.taxCategory;
		$('product.description').innerHTML = product.description;
		STOCKQTY = $('product.stock.qty').innerHTML = product.stockQty;
		TAX_RATE = new Number(product.taxRate).toFixed(2);		
		var taxPercentage = new Number(1 + (TAX_RATE / 100));
		
		ISTAXINCLUDED = product.isTaxIncluded;
		
		if(ISTAXINCLUDED == "false")
		{
			$('product.price.list').innerHTML = new Number(product.priceList * taxPercentage).toFixed(2);
		}
		else
		{
			$('product.price.list').innerHTML = new Number(product.priceList).toFixed(2);
		}		

		$('product.units.pack').innerHTML = product.unitsPerPack;
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

function initSystemBtns()
{
	
	systemBtns = new Array();
	
	systemBtns.push($('completeBtn'));
	systemBtns.push($('clearCartBtn'));
	systemBtns.push($('saveBtn'));
	systemBtns.push($('newStockBtn'));

	systemBtns = $A(systemBtns);
			
	for(var i=0; i<systemBtns.length; i++)
	{
		systemBtns[i].className = 'pointer';
	}
}

function resetDetails()
{
	$('product.name').innerHTML = "";	
	$('product.barcode').innerHTML = "";
	$('product.taxCategory').innerHTML = "";
	$('product.description').innerHTML = "";
	$('product.stock.qty').innerHTML = "";
	$('product.price.list').innerHTML = "";
	$('product.units.pack').innerHTML = "";
	$('qty').value = "";
	$('barcode').value = "";
	$('productQuery').value = "";
	$('description').value = "";
	$('reference').value = "";
	$('docNo').innerHTML = "";
	$('barcode').focus();	
}


function trim(stringToTrim) 
{
	return stringToTrim.replace(/^\s+|\s+$/g,"");
}


function redirect(url)
{
	window.location = url;
}

function saveMMovement()
{
	if(cartLines == null || cartLines.length == 0)
	{		
		//Do nothing
		return;
	}
	else
	{
		Modalbox.show('<div align="center">Please Wait...<br><img src="images/loader.gif" /><p></div>', {title: 'Saving Inventory Move', width: 800});
		Modalbox.deactivate();
		
		reference = $('reference').value;		
		var url = 'StockMovementAction.do';
		var pars = 'action=createMMovement&movementId='+movementId+'&priceListId='+priceList+'&description='+reference+'&orgFromId='+orgFromId+'&orgToId='+orgToId;
					
		var myAjax = new Ajax.Request( url, 
		{ 
			method: 'get', 
			parameters: pars,
			onSuccess: showMovementInfo,
			onFailure: reportError
		});
	}
}

function showMovementInfo(request)
{
	//
	try
	{
		if (window.Modalbox)
	    	Modalbox.hide();
	    	
		var response = request.responseText;
		var movementInfo = eval('(' + response + ')');
		
		//display info in right column
		$('docNo').innerHTML = movementInfo.docNo;
		
		if(movementInfo.description == null || movementInfo.description == 'null' || movementInfo.description == "")
		{
			$('reference').value = "";
		}
		else
		{
			$('reference').value = movementInfo.description;
		}			
		
		$('mainTitle').innerHTML = "STATUS: " + movementInfo.docStatus;
		$('movementId').value = movementId = movementInfo.movementId;

	}
	catch(e)
	{
		showErrorMessage(e);
	}
}
function setOrgFromTo()
{
	var orgFrom = $('orgFromId');
	orgFromId = orgFrom[orgFrom.selectedIndex].value;
	
	var orgTo = $('orgToId');
	orgToId = orgTo[orgTo.selectedIndex].value;
	
	if(orgFromId == orgToId)
	{
		showErrorMessage('You cannot transfer stocks to the same organisation', 'orgFromId');
	}
}

Event.observe(window,'load',initScreen,false);