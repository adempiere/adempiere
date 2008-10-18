//-------------------------------------------------------------------------------------
// advanced checkout scripts
var cartLines = null;
var cartIndex = null;
var DISCOUNT_LIMIT = null;
var OVERRIDE_PRICE_LIMIT = null;
var IS_ERROR = false;
var orderType = null;
var tangoGreen1 = null;
var tangoGreen2 = null;
var tangoBlue1 = null;
var tangoBlue2 = null;

var initScreen = function(){
	DISCOUNT_LIMIT = getDiscountAllowed();
	OVERRIDE_PRICE_LIMIT = overrideLimit;
	
	orderType = $('orderType').value;
			
	//initDiscount();
	initPIN();
	addBehaviourToCart();
	
	//move between cart lines
	shortcut.add("Ctrl+Up", function(){
		if(cartIndex > 0)
		{
			cartIndex = cartIndex -1;
			var line = cartLines[cartIndex];
			line.select();	
		}			
	});
	
	shortcut.add("Ctrl+Down", function(){
		if(cartIndex < cartLines.length - 1)
		{
			cartIndex = cartIndex + 1;
			var line = cartLines[cartIndex];
			line.select();	
		}			
	});
	
	shortcut.add("Ctrl+Enter", function(){simulateOnClick($('applyBtn'))});
	shortcut.add("Ctrl+Space", function(){simulateOnClick($('overrideDiscountLimitBtn'))});
	shortcut.add("Ctrl+Backspace", function(){simulateOnClick($('cancelBtn'))});
	
	$('applyBtn').onclick = function(){
		if(!this.disabled)
		{
			document.forms[0].submit();
		}
		else
		{
			showErrorMessage('Please fix the errors.','grandTotal');			
		}
	};
	
	$('overrideDiscountLimitBtn').onclick = function(e){
			showDisableMask();
			popPanel($('PINPanel'));
			$('PIN').focus();
	};
	
	//initMask();
};

var resetTotals = function(){
	cartLines[cartLines.length - 1].reset();
	
	var total = 0.0;
	
	for(var i=0; i<cartLines.length-1;i++)
	{
		total += parseFloat(cartLines[i].actualprice);
	}
	
	cartLines[cartLines.length - 1].price.value = new Number(total).toFixed(2);
	cartLines[cartLines.length - 1].discount.value = '0.00';
};

var resetLines = function(){
	for(var i=0; i<cartLines.length-1;i++)
	{
		cartLines[i].price.value = new Number(cartLines[i].actualprice).toFixed(2);
		cartLines[i].discount.value = '0.00';
		cartLines[i].inclPrice.value = new Number(cartLines[i].listPrice.value).toFixed(2);
		cartLines[i].isDiscOnInclUnitPrice.value = 'false';
		cartLines[i].isDiscOnPerc.value = 'false';
		cartLines[i].isDiscOnTotal.value = 'false';
		cartLines[i].validate();
	}
};

var isGood = function(){
	for(var i=0; i<cartLines.length;i++)
	{
		var line = cartLines[i];
		if(line.errormsg.length > 0)
		{
			return false;
		}
	}	
	return true;
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
				discountLimit = result.discountLimit;
				overrideLimit = result.overrideLimit;
				
				DISCOUNT_LIMIT = discountLimit;
				OVERRIDE_PRICE_LIMIT = overrideLimit;
				
				$('discountLimit').innerHTML = discountLimit + '';
				$('overrideLimit').innerHTML = overrideLimit + '';
				$('PINPanel').pin.value = '';
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
		hidePanel($('PINPanel'));			
		hideDisableMask();
	};
};

function addBehaviourToCart()
{
	var rows = new Array();
	var count = 1;
	
	while(true)
	{
		var row = $('row' + count);
		if(row == null) break;
		
		rows.push(row);
		count ++;
	}
	
	for(var i=0; i<rows.length; i++)
	{
		var tr = rows[i];
		var productId = tr.getAttribute('productId');
		var qty = tr.getAttribute('qty');
		
		tr.style.cursor = 'pointer';
		tr.productId = productId;
		tr.qty = qty;
		tr.errormsg = '';
		
		var inputs = tr.getElementsByTagName('input');
				
		var inclPrice = inputs[1];
		var taxRate = inputs[2];
		var discount = inputs[3];
		var price = inputs[4];
		var isDiscOnInclUnitPrice = inputs[6];
		var discInclUnitPrice = inputs[7];
		var isDiscOnPerc = inputs[8];
		var isDiscOnTotal = inputs[9];
		var listPrice = inputs[10];
		
		discount.tr = tr;
		price.tr = tr;
		inclPrice.tr = tr;
		
		/*if (i == rows.length-1)
		{
			var isDiscOnTotalPer = $('discountOnTotalPercent').value;
			var isDiscOnTotal = $('discountOnTotal').value;
			
			if (isDiscOnTotalPer)
			{
				price.value = new Number(inclPrice.value) * (1 - new Number(discount.value)/100);
			}
			else if (isDiscOnTotal)
			{
				discount.value = new Number(((inclPrice.value - price.value)/inclPrice.value) * 100);
			}
		}
		*/
		tr.discount = discount;
		tr.price = price;
		tr.inclPrice = inclPrice;
		tr.isDiscOnInclUnitPrice = isDiscOnInclUnitPrice;
		tr.discInclUnitPrice = discInclUnitPrice;
		tr.isDiscOnPerc = isDiscOnPerc;
		tr.isDiscOnTotal = isDiscOnTotal;
		tr.listPrice = listPrice;
		
		tr.initDiscount = discount.value;
		tr.initTaxRate = taxRate.value;
		tr.initPrice = price.value;
		tr.actualprice = ((price.value/(100 - discount.value))*100);		
		//-----------------------------------------------------------------------------------------//
		inclPrice.onkeyup = function(e){
			if (!validateNumberKey(e.keyCode)) return;
			
			var newprice = this.value;
			var realUnitprice = this.tr.listPrice.value;
			var initdiscount = this.tr.initDiscount;	
			
			this.tr.price.value = new Number(newprice * this.tr.qty).toFixed(2);
			this.tr.discount.value = new Number(((realUnitprice - newprice)/realUnitprice)*100).toFixed(2);
			this.tr.validate();
			
			this.tr.isDiscOnInclUnitPrice.value = 'true';
			this.tr.isDiscOnPerc.value = 'false';
			this.tr.isDiscOnTotal.value = 'false';
			this.tr.discInclUnitPrice.value = new Number(newprice).toFixed(2);
			
			$('discountOnTotalPercent').value='false';
			$('discountOnTotal').value='false';
				
			if (this.tr.priceLimit)
			{
				resetTotals();
			}			
		};
		
		discount.onkeyup = function(e){			
			if (!validateNumberKey(e.keyCode)) return;
			
			var dis = this.value;
			var initprice = this.tr.initPrice;
			var initdiscount = this.tr.initDiscount			
			var oldprice = ((initprice/(100 - initdiscount))*100);				
			var newprice = ((100 - dis)/100)*oldprice;
			this.tr.price.value = new Number(newprice).toFixed(2);		
			this.tr.inclPrice.value = new Number(newprice / this.tr.qty).toFixed(2);	
			this.tr.validate();
			
			this.tr.isDiscOnInclUnitPrice.value = 'false';
			this.tr.isDiscOnPerc.value = 'true';
			this.tr.isDiscOnTotal.value = 'false';
			
			if (this.tr.priceLimit)
			{
				$('discountOnTotalPercent').value='false';
				$('discountOnTotal').value='false';
				resetTotals();
			}
			else
			{
				$('discountOnTotalPercent').value='true';
				$('discountOnTotal').value='false';
				resetLines();
			}
		};	
				
		price.onkeyup = function(e){
			if (!validateNumberKey(e.keyCode)) return;
			
			var newprice = this.value;
			var initprice = this.tr.initPrice;
			var initdiscount = this.tr.initDiscount			
			var oldprice = ((initprice/(100 - initdiscount))*100);			
			var dis = ((oldprice - newprice)/oldprice)*100;
			this.tr.discount.value = new Number(dis).toFixed(2);
			this.tr.inclPrice.value = new Number(newprice / this.tr.qty).toFixed(2);
			this.tr.validate();
			
			this.tr.isDiscOnInclUnitPrice.value = 'false';
			this.tr.isDiscOnPerc.value = 'false';
			this.tr.isDiscOnTotal.value = 'true';
			
			if (this.tr.priceLimit)
			{
				$('discountOnTotalPercent').value='false';
				$('discountOnTotal').value='false';
				resetTotals();
			}
			else
			{
				$('discountOnTotalPercent').value='false';
				$('discountOnTotal').value='true';
				resetLines();
			}
		};		
		//-----------------------------------------------------------------------------------------//
		
		if (inputs.length > 5)
		{
			tr.priceLimit = inputs[5].value;
			inputs[5].tr = tr;
		}
						
		tr.select = function(e){
					
			for(var j=0; j<cartLines.length-1; j++)
			{
				if(j%2 == 0)
				{
					cartLines[j].className = 'evenRow';
				}
				else
				{
					cartLines[j].className = 'oddRow';
				}
			}
						
			cartLines[j].className = 'itemsTotal';
									
			this.className = 'highlight';
			this.discount.focus();
			
			if (this.priceLimit)	
			{
				$('priceLimit').innerHTML = this.priceLimit;
			}
			
			this.validate();				
			
		};	
		
		tr.reset = function(){
			this.price.value = this.initPrice;
			this.discount.value = this.initDiscount;
			this.errormsg = '';
		};
		
		tr.onclick = function(e){
			
			for(var j=0; j<cartLines.length-1; j++)
			{
				if(j%2 == 0)
				{
					cartLines[j].className = 'evenRow';
				}
				else
				{
					cartLines[j].className = 'oddRow';
				}
			}
						
			cartLines[j].className = 'itemsTotal';
			
			this.className = 'highlight';
			
			if (this.priceLimit)	
			{
				$('priceLimit').innerHTML = this.priceLimit;
			}	
			
			this.validate();					
			
		};
		
		tr.validate = function(){		
		
			this.errormsg = '';
			var bgcolor = '';
			Element.hide('errormsg');
		
			//check price limit
			if (!OVERRIDE_PRICE_LIMIT)
				if(this.price.value - this.priceLimit < 0.0 )
				{
					this.errormsg = 'Price Limit Exceeded!';
					bgcolor = '#FF0000';
					Element.show('errormsg');
				}
			
			//check discount limit
			if (this.discount.value > DISCOUNT_LIMIT)
			{
				this.errormsg = 'Discount Limit Exceeded!';
				bgcolor = '#FF0000';
				Element.show('errormsg');
			}
			
			//check discount
			if (this.discount.value < 0)
			{
				this.errormsg = 'Discount cannot be negative!';
				bgcolor = '#FF0000';
				Element.show('errormsg');
			}			
			
			$('errormsg').innerHTML = this.errormsg;
			
			this.price.style.backgroundColor = bgcolor;
			this.discount.style.backgroundColor = bgcolor;
			
			if (isGood())
			{
				$('total').innerHTML = $('curSymbole').value + ' ' + $('grandTotal').value;
				enableApplyBtn();
			}
			else
			{
				disableApplyBtn();
			}
		};				
	}//for
		
	cartLines = rows;
	cartIndex = rows.length - 1;	
	
	if (cartLines.length > 0)
	{
		var line = cartLines[cartIndex];
		line.select();	
		
	}		
	
}

function enableApplyBtn()
{
	$('applyBtn').disabled = false;
	$('applyBtn').style.backgroundColor = '#000';
	$('applyBtn').style.cursor = 'pointer';
	
}

function disableApplyBtn()
{
	$('applyBtn').disabled = true;
	$('applyBtn').style.backgroundColor = '#CCC';
	$('applyBtn').style.cursor = 'none';
}

function validateNumberKey(keyCode)
{
	/*if(keyCode == 0 || keyCode == 46) return true;
	
	if(keyCode >= 48 && keyCode <=57) return true;
	
	return false;
	*/
	if (keyCode <48 || ( keyCode > 57 && keyCode < 96) || keyCode > 105)
	{
		if (keyCode != 46 && keyCode != 110 && keyCode != 8 && keyCode!= 190)
		{
			return false;
		}		
	}
	return true;
}

function initMask()
{
	var xx = $$('.numeric');	
	for(var i=0; i<xx.length;i++)		
	{
		var x = xx[i];
		x.onkeypress = function(e){
			return validateNumberKey(e.charCode);
		};
	}

}


function refreshCart()
{
	var rows = new Array();
	var count = 1;
	
	while(true)
	{
		var row = $('row' + count);
		if(row == null) break;
		
		rows.push(row);
		count ++;
	}
	
	for(var i=0; i<rows.length; i++)
	{
		var tr = rows[i];
		tr.validate();
	}
}
Event.observe(window,'load',initScreen,false);