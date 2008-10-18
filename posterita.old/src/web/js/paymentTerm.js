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


//scripts for payment term
var netDays 	= null;
var fixDate 	= null;
var fixDay 		= null;
var fixCutOff 	= null;
var fixOffset 	= null;

function initPaymentScreen(){	
	fixDate 	= $FElement('fixedDueDate');
	
	netDays 	= $FElement('netDays');
	fixDay 		= $FElement('fixedMonthDay');
	fixCutOff 	= $FElement('fiedMonthCutoff');
	fixOffset 	= $FElement('fixedMonthOffset');
	
	showHideDates();
	fixDate.onclick = showHideDates;
	
}

function showHideDates(){
	if(fixDate.checked){
		netDays.disabled 	= true;
		fixDay.disabled 	= false;
		fixCutOff.disabled 	= false;
		fixOffset.disabled 	= false;
	}
	else{
		netDays.disabled 	= false;
		fixDay.disabled 	= true;
		fixCutOff.disabled 	= true;
		fixOffset.disabled 	= true;
	}
}

//registering event
Event.observe(window,'load',initPaymentScreen,false);
