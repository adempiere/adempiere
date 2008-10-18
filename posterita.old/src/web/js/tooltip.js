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


//some methods for tooltip
function showTooltip(e)
{
	//Remove previous tooltips
	if($('tooltipdiv'))
	{
		document.body.removeChild($('tooltipdiv'));
	}
	
	var tip = this.getAttribute('tooltip');		
	if(!tip)
	{
		return;
	}
	
	var tooltip = document.createElement('div');
	var newAttr = document.createAttribute('id');
	newAttr.nodeValue = "tooltipdiv"
	tooltip.setAttributeNode(newAttr);
	
	tooltip.onclick = function(){
		document.body.removeChild(this);
	};
		
	var event = window.event || e;
	
	var xcoor = Event.pointerX(event);
	var ycoor = Event.pointerY(event);
	
	//tooltip.style.left = 10 + xcoor + "px";
	//tooltip.style.top  = 10 + ycoor + "px";
	tooltip.style.visibility = 'hidden';
	tooltip.className = "tooltip";
	tooltip.innerHTML = tip;	
	document.body.appendChild(tooltip);	
	
	var sWidth = getViewportWidth();
	var sHeigth = getViewportHeight();
	
	var ttLeft = 10 + xcoor;
	var ttTop = 10 + ycoor;
	var ttWidth = tooltip.scrollWidth;
	var ttHeight = tooltip.scrollHeight;
	
	if((ttLeft+ttWidth)>sWidth){
		ttLeft = sWidth - (10 + ttWidth);
	}
	
	if((ttTop+ttHeight)>sHeigth){
		ttTop = sHeigth - (10 + ttHeight);
	}
	
	tooltip.style.left = ttLeft + "px";
	tooltip.style.top  = ttTop + "px";
	tooltip.style.visibility = 'visible';
	
	//toConsole('Width:' + tooltip.scrollWidth);
	//toConsole('Height:' + tooltip.scrollHeight);
	
}

var initTooltip = function()
{
	var help = document.getElementsByName('help');
	for(var i=0; i<help.length;i++)
	{
		help[i].onclick = showTooltip;
		help[i].style.cursor = "pointer"; 
	}
	
};

function getViewportHeight() {
	if (window.innerHeight!=window.undefined) return window.innerHeight;
	if (document.compatMode=='CSS1Compat') return document.documentElement.clientHeight;
	if (document.body) return document.body.clientHeight; 
	return window.undefined; 
}

function getViewportWidth() {
	if (window.innerWidth!=window.undefined) return window.innerWidth; 
	if (document.compatMode=='CSS1Compat') return document.documentElement.clientWidth; 
	if (document.body) return document.body.clientWidth; 
	return window.undefined; 
}

//initialising
Event.observe(window,'load',initTooltip,false);