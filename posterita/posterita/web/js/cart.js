var cartLines = new Array();
var cartIndex = 0;
var maxRows = 10;
var itemId = 0;
var editItemUrl = null;
var createItemUrl = null;
var importItemsUrl = null;
var itemDetailsUrl = null;
var itemDetailsPars = null;

	
function addCartStyle()
{
	var index = 0;
		
	var trs = document.getElementsByTagName('tr');
	
	for(var i=0; i<trs.length; i++)
	{
		index = trs[i].id.indexOf('row');
		
		if(index != -1)
		{
			cartLines.push(trs[i]);		
		}
	
	}
	
	if(cartLines.length > 0)
	{
		cartLines[0].className = 'highlight';
		itemId = cartLines[0].getAttribute('itemId');
		setItemEditURL(itemId);
		getItemDetails(getItemDetailsUrl(), getItemDetailsPars(), itemId);
	}
		
	for(var j=0; j<cartLines.length; j++)
	{
		cartLines[j].index = j;
		cartLines[j].style.cursor = 'pointer';
				
		cartLines[j].onclick = function(e){
			itemId = this.getAttribute('itemId');
			setItemEditURL(itemId);
			getItemDetails(getItemDetailsUrl(), getItemDetailsPars(), itemId);
						
			for(var k=0; k<cartLines.length; k++)
			{
				if(k%2 == 0)
				{
					cartLines[k].className = 'oddRow';
				}
				else
				{
					cartLines[k].className = 'evenRow';
				}
				
			}
			
			this.className = 'highlight';
			cartIndex = this.index;	
		};
	}
}

function initShortcuts()
{
	
	shortcut.add("Ctrl+Up", function(){
			
			if(cartIndex > 0)
			{
				cartIndex = cartIndex -1;
				var line = cartLines[cartIndex];
				scrollCartUp();			
				simulateOnClick(line);	
			}			
		});
		
	shortcut.add("Ctrl+Down", function(){
			
			if(cartIndex != cartLines.length - 1)
			{
				cartIndex = cartIndex + 1;
				var line = cartLines[cartIndex];
				scrollCartDown();
				simulateOnClick(line);
			}
	});
	
	shortcut.add("Ctrl+E", function(){
		
		if(itemId != 0 && itemId != null)
		{
			get(getEditItemUrl() + itemId);;			
		}
	});
	
	shortcut.add("Ctrl+N", function(){
		
		get(getCreateItemUrl());
	});
	
	shortcut.add("Ctrl+I", function(){
		
		get(getImportItemsUrl());
	});
	
	shortcut.add("F1", function(e){
		
		$('searchField1').select();
	});
	
	shortcut.add("F2", function(e){
	
		$('searchField2').select();
	});
	
	shortcut.add("F3", function(e){
	
		$('searchField3').select();
	});
	
	shortcut.add("Ctrl+M", function(){
	
		simulateOnClick($('backButn'));
	});
	
	shortcut.add("Ctrl+D", function(){
	
		//simulateOnClick($('openDrawerBtn'));
		showErrorMessage('Opening Cash Drawer');
	});
	
	shortcut.add("Ctrl+Delete", function(){
	
		simulateOnClick($('clearCartBtn'));		
	});
	
	shortcut.add("F4", function(){
		
		document.forms[0].submit();
	});
	
	shortcut.add("Ctrl+Left", function(){
	
		simulateOnClick($('previousItem'));	
	});
	
	shortcut.add("Ctrl+Right", function(){
	
		simulateOnClick($('nextItem'));		
	});
};


function scrollCartDown()
{
	var cart = $('cart');	
	
	if(cartIndex >= maxRows && (cartIndex%maxRows == 0))
	{
		cart.scrollTop = cart.scrollTop + 340;
	}
}

function scrollCartUp()
{
	var cart = $('cart');
	
	if(cartIndex >= maxRows && (cartIndex%maxRows == 0))
	{
		cart.scrollTop = cart.scrollTop - 340;
	}
}
	
function getItemDetails(url, pars, itemId)
{
	pars = pars + itemId;
	var myAjax = new Ajax.Request( url, 
	{ 
		method: 'get', 
		parameters: pars, 
		onSuccess: showItemDetails, 
		onFailure: reportError
	});
}

var initItemNavigation = function(){

	$('nextItem').onclick = function(e){
		
		cartIndex = cartIndex + 1;
		scrollCartDown();
		simulateOnClick(cartLines[cartIndex]);
		
	};
	
	$('previousItem').onclick = function(e){
	
		cartIndex = cartIndex - 1;
		scrollCartUp();
		simulateOnClick(cartLines[cartIndex]);
	};
};

function reportError(request)
{
	alert('Oops! Some errors occured.');
	var win = window.open();
	win.document.write(request.responseText);
	win.document.close();
	
}

function clearCart(url, pars)
{	
	var myAjax = new Ajax.Request( url, 
	{ 
		method: 'get', 
		parameters: pars, 
		onSuccess: resetDetails, 
		onFailure: reportError
	});
}

function get(url)
{	
	window.location = url;
}

function setItemEditURL(itemId)
{
	var url = getEditItemUrl() + itemId;
	$('editItem').href = url;
}
