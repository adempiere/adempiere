//autocomplete for revenue recognition
copyValueInto('revenueRecognition','revenue_recognition');
new Ajax.Autocompleter('revenue_recognition','revenue_recognitionDiv','SearchPOSGarmentAttributes.do',{
							paramName:'revenue_recognition',
							frequency:TROTTLE_TIME,
							afterUpdateElement:function(e1,e2){
												$('revenue_recognition').value = e1.value;																					
												}												
							});
							

function $FF(elementName)
{
	return document.getElementsByName(elementName)[0];
}

function copyValueInto(e1,e2)
{
	toConsole("Copying " + e1 + " into " + e2);
	
	try
	{
		$FF(e2).value = $FF(e1).value;
	}
	catch(e)
	{
		toConsole("Copying failed!");
		toConsole(e);		
	}
	
			
}

function setValues(form)
{	
	try
	{
		toConsole('Going to submit form');
		copyValueInto('revenue_recognition','revenueRecognition');	
	
		form.submit();
	}
	catch(e)
	{
		toConsole("Submit failed!");
		toConsole(e);
		
		toConsole(form);		
	}
}

//------------------------------------------------------
// Validation of barcode & product name using AJAX
//------------------------------------------------------
function validateBarcode()
{
	if($FF('barCode').initialValue == $FF('barCode').value) return;
	
	var barcode = $FF('barCode').value;		
		
	var url = 'ValidatePOSProductAction.do';
	var pars = 'action=validateProductBarcode&barCode=' + barcode;
	
	var myAjax = new Ajax.Request( url, 
	{ 
		method: 'get', 
		parameters: pars, 
		onSuccess: doesBarcodeExists, 
		onFailure: reportError
	});
}

function validateProductName()
{
	if($FF('productName').initialValue == $FF('productName').value) return;
	
	var productName = $FF('productName').value;	
	var url = 'ValidatePOSProductAction.do';
	var pars = 'action=validateProductName&productName=' + productName;
	
	var myAjax = new Ajax.Request( url, 
	{ 
		method: 'get', 
		parameters: pars, 
		onSuccess: doesProductExists, 
		onFailure: reportError
	});
}

function doesBarcodeExists(request)
{
	try
	{
		var response = request.responseText;
				
		if(response == 'true')
		{
			Element.show('barcodeError');
			$FF('barCode').className = 'error';			
		}
		else
		{
			Element.hide('barcodeError');
			$FF('barCode').className = 'text';			
		}
		
	}
	catch(e)
	{
		toConsole(e);
	}	
}

function doesProductExists(request)
{
	try
	{
		var response = request.responseText;
						
		if(response == 'true')
		{
			Element.show('productError');
			$FF('productName').className = 'error';			
		}
		else
		{
			Element.hide('productError');
			$FF('productName').className = 'text';			
		}
		
	}
	catch(e)
	{
		toConsole(e);
	}	
}

function reportError(request)
{
	alert('Some error occured while communicating with the server. Please try again.');
}

//------------------------------------------------------
var barcode 					= $FF('barCode');
var productName 				= $FF('productName');
var taxCategory					= $FF('taxCategoryId');

var purchasePriceStandard 		= $FF('purchasePriceStandard');
var salesPriceStandard 			= $FF('salesPriceStandard');
var salesPriceList 				= $FF('salesPriceList');
var salesPriceLimit 			= $FF('salesPriceLimit');

var purchasePriceStandardTax	= $FF('purchasePriceStandardTax');
var salesPriceStandardTax 		= $FF('salesPriceStandardTax');
var salesPriceListTax 			= $FF('salesPriceListTax');
var salesPriceLimitTax 			= $FF('salesPriceLimitTax');

/**
 * Calculates price including tax
 * @param 	price without tax
 * @return 	price with tax
 */
function getPriceIncludingTax(price)
{
	var tax = getTax();
	var priceWithoutTax = price || 0;		
	var priceWithTax = ((100+tax)/100)*priceWithoutTax;
		
	priceWithTax = new Number(priceWithTax).toFixed(2);
	
	return priceWithTax;
	
}//getPriceIncludingTax(price)

/**
 * Calculates price excluding tax
 * @param 	price with tax
 * @return 	price without tax
 */
function getPriceExcludingTax(price)
{
	var tax = getTax();
	var priceWithTax = price || 0;			
	var priceWithoutTax = (100*priceWithTax)/(100+tax);		
		
	priceWithoutTax = new Number(priceWithoutTax).toFixed(2);
	
	return priceWithoutTax;
	
}//getPriceExcludingTax(price)

/**
 * Gets the current tax
 */
function getTax()
{
	var select = taxCategory;	
	var index = select.selectedIndex;
	
	if(index == -1) index = 0;
	
	var option = select.options[select.selectedIndex];
	var v = option.label || option.text;
	
	var s = v.split('%');
	
	if(s.length < 2) return 0;
	
	var tax = parseFloat(s[1]);	
	
	return tax;	
	
}//getTax()

/*
function setPriceIncludingTax()
{
	purchasePriceStandardTax.value 	= getPriceIncludingTax(purchasePriceStandard.value);
	salesPriceLimitTax.value 		= getPriceIncludingTax(salesPriceLimit.value);
	salesPriceListTax.value			= getPriceIncludingTax(salesPriceList.value);
	salesPriceStandardTax.value 	= getPriceIncludingTax(salesPriceStandard.value);
	
}//setPriceIncludingTax()

//--------------------------------------------------------------------------
// Initialising components & adding behaviour
//--------------------------------------------------------------------------

//update all prices
taxCategory.onchange = setPriceIncludingTax;


purchasePriceStandard.onkeyup = function(e){	
	purchasePriceStandardTax.value = getPriceIncludingTax(this.value);
};
purchasePriceStandardTax.onkeyup = function(e){
	purchasePriceStandard.value = getPriceExcludingTax(this.value);
};

salesPriceLimit.onkeyup = function(e){
	salesPriceLimitTax.value = getPriceIncludingTax(this.value);
};
salesPriceLimitTax.onkeyup = function(e){
	salesPriceLimit.value = getPriceExcludingTax(this.value);
};

salesPriceStandard.onkeyup = function(e){
	salesPriceStandardTax.value = getPriceIncludingTax(this.value);
};
salesPriceStandardTax.onkeyup = function(e){
	salesPriceStandard.value = getPriceExcludingTax(this.value);
};

salesPriceList.onkeyup = function(e){
	salesPriceListTax.value		= getPriceIncludingTax(this.value);
	
	//overwrite the other prices
	salesPriceLimit.value 		= this.value;	
	salesPriceStandard.value 	= this.value;	
	salesPriceLimitTax.value 	= getPriceIncludingTax(this.value);	
	salesPriceStandardTax.value = getPriceIncludingTax(this.value);	
};

salesPriceListTax.onkeyup = function(e){
	salesPriceList.value = getPriceExcludingTax(this.value)	;
};

barcode.onblur = validateBarcode;
barcode.initialValue = barcode.value;
barcode.onkeyup = function(e)
{
	try
	{
		if(this.initialValue == this.value) return;
		
		var event = e || window.event;
	
		if(event.keyCode == 13)
		{
			toConsole('validating barcode');
			validateBarcode();
		}
	}
	catch (e)
	{
		toConsole(e);
	}
};

productName.onblur = validateProductName;
productName.initialValue = productName.value;
productName.onkeyup = function(e)
{
	try
	{
		if(this.initialValue == this.value) return;
		
		var event = e || window.event;
	
		if(event.keyCode == 13)
		{
			toConsole('validating product name');
			validateProductName();
		}
	}
	catch (e)
	{
		toConsole(e);
	}
};

$('barcodeError').style.display = 'none';
$('productError').style.display = 'none';

setPriceIncludingTax();
*/