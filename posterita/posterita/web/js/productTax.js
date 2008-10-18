// script for calculating excl and incl prices for Posterita products

var taxRateFactor = 1.0;

var updateTax = function(e)
{
	var taxCategoryId = document.forms[0].taxCategoryId.value;
	var url = "TaxAction.do";
	var pars = "action=getTaxRate&taxCategoryId="+ taxCategoryId;
	
	var myAjax = new Ajax.Request( url, 
	{ 
		method: 'get', 
		parameters: pars, 
		onSuccess: setTaxRate, 
		onFailure: reportError
	});	
};

var setTaxRate = function(request)
{
	try
	{
		var response = request.responseText;
		var taxRate = parseFloat(response);
		taxRateFactor = new Number((taxRate/100)+1).toFixed(2);
		loadPriceListBehaviour();
	}
	catch(e)
	{}
};

function reportError(request)
{
	alert("Some error has occured while communicating with the server");
}

var loadPriceListBehaviour = function(){
	
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
		var inputs = tr.getElementsByTagName('input');
		
		var stdPrice = inputs[0];
		var stdPriceIncl = inputs[1];
		var listPrice = null;
		var listPriceIncl = null;
		var limitPrice = null;
		var limitPriceIncl = null;
		
		stdPrice.tr = tr;
		stdPriceIncl.tr = tr;
		
		tr.stdPrice = stdPrice;
		tr.stdPriceIncl = stdPriceIncl;
		
		tr.stdPriceIncl.value = calculatePrice(stdPrice.value, false);
		
		stdPrice.onkeyup = function(e){
			this.tr.stdPriceIncl.value = calculatePrice(this.value, false) 			
		};
		
		stdPriceIncl.onkeyup = function(e){
			this.tr.stdPrice.value = calculatePrice(this.value, true);
		};
		
		if (inputs.length > 2 && inputs.length <= 6)
		{
			listPrice = inputs[2];
			listPriceIncl = inputs[3];
			limitPrice = inputs[4];
			limitPriceIncl = inputs[5];
			
			listPrice.tr = tr;
			listPriceIncl.tr = tr;
			limitPrice.tr = tr;
			limitPriceIncl.tr = tr;
			
			tr.listPrice = listPrice;
			tr.listPriceIncl = listPriceIncl;
			tr.limitPrice = limitPrice;
			tr.limitPriceIncl = limitPriceIncl;	
			
			tr.listPriceIncl.value = calculatePrice(listPrice.value, false);
			tr.limitPriceIncl.value = calculatePrice(limitPrice.value, false);
			
			listPrice.onkeyup = function(){
				this.tr.listPriceIncl.value = calculatePrice(this.value, false);
			};
			
			listPriceIncl.onkeyup = function(){
				this.tr.listPrice.value = calculatePrice(this.value, true);
			};
			
			limitPrice.onkeyup = function(){
				this.tr.limitPriceIncl.value = calculatePrice(this.value, false);
			};
			
			limitPriceIncl.onkeyup = function(){
				this.tr.limitPrice.value = calculatePrice(this.value, true);		
			};
		}
	}
};

var calculatePrice = function(value, isTaxIncluded)
{
	if (isTaxIncluded)
	{
		return new Number(value / taxRateFactor).toFixed(2);
	}
	else
	{
		return new Number(value * taxRateFactor).toFixed(2);	
	}
};

Event.observe(window, 'load', updateTax, false);