/**
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2007  Posterita Ltd
 *  This file is part of POSterita
 *  
 *  POSterita is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

/**
	@author praveen
 */


//------------------------------------------------------
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

setPriceIncludingTax();