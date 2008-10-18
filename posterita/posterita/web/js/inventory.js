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
var inventoryId = null;
var description = null;
var oldPriceList = -1;
var searchElement = null;
var isCustomerCompulsory = false;
var defaultPriceList = null;
var defaultPriceListName = null;
var upload_number = 2;
var itemRowHeight = null;
var maxRows = 15;

var initScreen = function(){
	priceList = $('priceListId').value;
	inventoryId = $('inventoryId').value;
	description = $('invdescription').value;
	$('importBtn').style.display = 'none';
	$('saveCsvBtn').style.display = 'none';
	$('reloatCartBtn').style.display = 'none';
		
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
		
	$('saveBtn').onclick = function(e){
		if(cartLines == null || cartLines.length == 0)
		{
			showErrorMessage('Shopping cart is empty!');
		}
		else
		{
			autoSave();
		}
	};
	
	$('completeBtn').onclick = function(e){
		if(cartLines == null || cartLines.length == 0)
		{
			showErrorMessage('Shopping cart is empty!');
		}
		else
		{
			$('inventoryForm').submit();
		}
	};
	
	$('saveCsvBtn').onclick = function(e){
		if(cartLines == null || cartLines.length == 0)
		{
			showErrorMessage('Shopping cart is empty!');
		}
		else
		{
			generateCsv();
		}
	};
		
	initProductSearch();
	initCartBtn();
	initAjaxResponder();
	initShortcuts();
		
	$('total').innerHTML = $('cartTotalCount').innerHTML;
	
	
	searchElement.focus();
	
	var barcode = $('barcode');
	barcode.onkeyup = function(e){
		if(e.keyCode == Event.KEY_RETURN)
		{
			if(this.value != null && this.value != "")
			{								
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
	
	$('importBtn').onclick = function(e){
		Modalbox.show('importPOSStock.do', {title: 'import pos stock sheets', width: 800}); 
		return false;
	};
		
		
	$('clearCartBtn').onclick = function(e){
		newCart();	
		
	};	
	
	//add behaviour to exixting cart
	addBehaviourToCart();
};

var initShortcuts = function(){
	shortcut.add("Ctrl+Space", function(e){simulateOnClick($('checkoutBtn'))});
	
	shortcut.add("F1", function(e){$('barcode').select();});
	shortcut.add("F2", function(e){$('productQuery').select();});
	shortcut.add("F3", function(e){$('description').select();});
	shortcut.add("F4", function(e){$('qty').select();});
	
	shortcut.add("F5", function(e){simulateOnClick($('saveBtn'))});	
	shortcut.add("F6", function(e){simulateOnClick($('searchBtn'))});	
	
	shortcut.add("Ctrl+P", function(e){simulateOnClick($('printBtn'))});
	
	//payment btns
	shortcut.add("F7", function(e){simulateOnClick($('importBtn'))});
	shortcut.add("F8", function(e){simulateOnClick($('csvBtn'))});
	shortcut.add("F9", function(e){simulateOnClick($('saveCsvBtn'))});	
	shortcut.add("F10", function(e){simulateOnClick($('reloatCartBtn'))});
	
	shortcut.add("Ctrl+M", function(){simulateOnClick($('menuBtn'))});
	shortcut.add("Ctrl+N", function(){newCart();  return;});
	shortcut.add("Ctrl+R", function(){editDescription(); return;});
	shortcut.add("Ctrl+C", function(){simulateOnClick($('completeBtn'))});
	shortcut.add("Ctrl+D", function(){
		//simulateOnClick($('openDrawerBtn'));
		showErrorMessage('Opening Cash Drawer');
		
	});
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

function autoSave()
{
	if(cartLines == null || cartLines.length == 0)
	{		
		//Do nothing
		return;
	}
	else
	{
		Modalbox.show('<div align="center">Please Wait...<br><img src="images/loader.gif" /><p></div>', {title: 'Saving Inventory', width: 800});
		Modalbox.deactivate();
				
		var url = 'InventoryCartAction.do';
		var pars = 'action=autoSaveInventoryLines&inventoryId='+inventoryId+'&priceListId='+priceList+'&description='+description;
					
		var myAjax = new Ajax.Request( url, 
		{ 
			method: 'get', 
			parameters: pars, 
			onSuccess: showInventoryInfo, 
			onFailure: reportError
		});
	}
}



function showInventoryInfo(request)
{
	//
	try
	{
		if (window.Modalbox)
	    	Modalbox.hide();
	    	
		var response = request.responseText;
		var invInfo = eval('(' + response + ')');
		
		//display info in right column
		$('inventory.docno').innerHTML = invInfo.docNo;	
		$('inventory.description').innerHTML = invInfo.description;
		$('inventory.status').innerHTML = invInfo.docStatus;
		$('inventoryId').value = inventoryId = invInfo.inventoryID;

	}
	catch(e)
	{
		showErrorMessage(e);
	}
}


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
		$('product.stock.qtyBook').innerHTML = product.stockQty;
		$('product.price.list').innerHTML = new Number(product.priceList).toFixed(2);
		
		$('product.total.lines').innerHTML = cartLines.length;
		$('product.total.count').innerHTML = $('cartTotalCount').innerHTML;
		$('product.total.book').innerHTML = $('cartTotalBook').innerHTML;
		$('product.total.csv').innerHTML = $('cartTotalCsv').innerHTML;
		$('product.discrepancy').innerHTML = $('cartTotalCount').innerHTML - $('cartTotalBook').innerHTML;
		
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
						var name = e2.getAttribute('name');
						
						if(bpartnerName)
						{
							$('customer.name').innerHTML = name;	
							$('credit.available').innerHTML = creditLimit==null?0:creditLimit;
							
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
		
		if(cartLines == null || cartLines.length == 0)
		{
			resetInfo();
		}
		
		searchElement.focus();
	};
	
	del.onclick = function(e){
		if(product_id != null)
		deleteItemFromCart(product_id);
		
		if(cartLines == null || cartLines.length == 0)
		{
			resetInfo();
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
	
	//amtStr = parseFloat(amtStr);	
	//var amount = new Number(amtStr);
	
	return amount;
}

function displaySortPanel()
{
	$('csvBtn').style.display = 'none';
	$('saveCsvBtn').style.display = 'block';
	$('saveBtn').style.display = 'none';
	$('clearCartBtn').style.display = 'none';
	$('reloatCartBtn').style.display = 'none';
	$('completeBtn').style.display = 'none';
	$('newBtn').style.display = 'none';
	$('cartSearch').style.display = 'none';
	$('editProductContainer').style.display = 'none';
	$('sortPanel').style.display = 'block';
    $('searchBtn').innerHTML = 'SWITCH BACK';
    $('filter').value = '';
    $('filter').focus();
    $('searchBtn').onclick = function() { switchBack(); };
}

function switchBack()
{
	$('csvBtn').style.display = 'block';
	$('saveCsvBtn').style.display = 'none';
	$('saveBtn').style.display = 'block';
	$('clearCartBtn').style.display = 'block';
	$('completeBtn').style.display = 'block';
	$('cartSearch').style.display = 'none';
	$('sortPanel').style.display = 'none';
	$('editProductContainer').style.display = 'block';
	$('cartSearch').style.display = 'block';
	$('newBtn').style.display = 'block';
	$('reloatCartBtn').style.display = 'none';
    $('searchBtn').innerHTML = 'SEARCH';
    $('filter').value = '';
    $('barcode').value = '';
    $('barcode').focus();
    $('searchBtn').onclick = function() { displaySortPanel(); };
    reloadCart();

}

function showCsvImport()
{
	$('importBtn').style.display = 'block';
	$('saveCsvBtn').style.display = 'block';
	$('menuBtn').style.display = 'none';
	$('clearCartBtn').style.display = 'none';
	$('printBtn').style.display = 'none';
	$('completeBtn').style.display = 'none';
	$('searchBtn').style.display = 'none';
	$('reloatCartBtn').style.display = 'block';
	$('newBtn').style.display = 'none';
	$('csvBtn').innerHTML = 'BACK';
   	$('csvBtn').onclick = function() { goBack(); };	
}

function diplayCsvOptions()
{
	$('importBtn').style.display = 'block';
	$('saveCsvBtn').style.display = 'block';
	$('menuBtn').style.display = 'none';
	$('clearCartBtn').style.display = 'none';
	$('printBtn').style.display = 'none';
	$('completeBtn').style.display = 'none';
	$('searchBtn').style.display = 'none';
	$('newBtn').style.display = 'none';
	$('reloatCartBtn').style.display = 'block';
	$('csvBtn').innerHTML = 'BACK';
   	$('csvBtn').onclick = function() { goBack(); };	
   	
	
}

function goBack()
{
	$('importBtn').style.display = 'none';
	$('saveCsvBtn').style.display = 'none';
	$('menuBtn').style.display = 'block';
	$('clearCartBtn').style.display = 'block';
	$('printBtn').style.display = 'block';
	$('completeBtn').style.display = 'block';
	$('searchBtn').style.display = 'block';
	$('newBtn').style.display = 'block';
	$('reloatCartBtn').style.display = 'none';
	$('csvBtn').innerHTML = 'CSV';
   	$('csvBtn').onclick = function() { diplayCsvOptions(); };	
}
function addPaging()
{
	//<![CDATA[
	var inv_Props = 	{
						grid: false,
						paging: true,
						paging_length: 15,
						rows_counter: true,
						loader: true,
						loader_text: "Filtering data..."
				};
	setFilterGrid( "inventoryCart",inv_Props,-1 );
	//]]>	
}

function trim(sString)
{
	while (sString.substring(0,1) == ' ')
	{
		sString = sString.substring(1, sString.length);
	}
	while (sString.substring(sString.length-1, sString.length) == ' ')
	{
		sString = sString.substring(0,sString.length-1);
	}
	return sString;
}

function editDescription()
{
	description = $('inventory.description').innerHTML;
	var userInput = prompt("Change inventory description", description)
	
	if(userInput == 'null' || userInput == '' || userInput == null)
	{
		userInput = description;
		$('inventory.description').innerHTML = userInput;
	}
	else
	{
		description = userInput;
		$('inventory.description').innerHTML = userInput;
	}
	
	return false;
	
}

function addUploadField()
{ 

	var tbl = $('upload');
	var lastRow = tbl.rows.length;
	// if there's no header row in the table, then iteration = lastRow + 1
	var iteration = lastRow;
	var row = tbl.insertRow(1);
	row.className = 'upload';
	  
	// right cell
	var cellRight = row.insertCell(0);
	cellRight.className = "tdUpload"
	var el = document.createElement('input');
	el.type = 'file';
	el.name = 'file' + iteration;
	el.id = 'file' + iteration;
	el.size = 40;
	
	cellRight.appendChild(el);
	
	var div = document.createElement('div')
	div.id = 'attachment'+iteration;
	
	var i = iteration - 1;
	
	if($('attachment'+i).innerHTML != '')
	{
		$('attachment'+i).innerHTML = '';
		$('attachment'+i).className = '';
		
	}
	
	
	div.className = 'button floatLeft';
	div.innerHTML = 'Add another file'
	div.onclick = function()
	{
		validateRow();
	}
	 cellRight.appendChild(div);
	
	var span=document.createElement('span');
	span.id = 'progressfile'+iteration;
	
	cellRight.appendChild(span);
	
	cellRight.focus();

}

function validate(obj)
{	
	var sfile = obj.value;
	
	if(sfile == "" || sfile == null || sfile == 'null')
		return false;
	
	var fileType = sfile.substring(sfile.length -3, sfile.length);
 	if(fileType.toLowerCase() != 'csv')
	{
		alert('Only csv file is allowed!!!');
		obj.value='';
		obj.focus();
		return false;
	}
	else
	{
		obj.disabled = true;
		uploadStockSheets(sfile);
	} 

}

function addFileInput() 
{
 	var d = document.createElement("div");
 	var file = document.createElement("input");
 	file.setAttribute("type", "file");
 	file.setAttribute("name", "attachment"+upload_number);
 	d.appendChild(file);
 	document.getElementById("moreUploads").appendChild(d);
 	upload_number++;
}


function uploadStockSheets(form) 
{
 	$('importStock').request({
 		contentType: 'multipart/form-data',
  		method: 'get',
  		onSuccess: refreshShoppingCart, 
		onFailure: reportShoppingCartError
	});
	
	return false;
}

function generateCsv()
{
	var url = 'InventoryCartAction.do?action=generateInventoryCSV';				
	window.location = url;
}


function addCSV(file)
{
	$('uploadHeader').style.display="block";
	$('uploadCsv').style.display="block";
	var tbl = $('csv');
	var lastRow = tbl.rows.length;
	// if there's no header row in the table, then iteration = lastRow + 1
	var iteration = lastRow;
	var row = tbl.insertRow(1);
	row.className = 'upload';
	// right cell
	var cellRight = row.insertCell(0);
	cellRight.className = "tdUpload"
	cellRight.innerHTML = "Adempiere.properties";

}

function validateRow()
{
	var tbl = document.getElementById('upload');
    var lastRow = tbl.rows.length - 1;
    
    if(lastRow == 10)
    {
    	alert('Maximum file to upload reached.');
    	return false;
    }
    
    var i;
    for (i=1; i<=lastRow; i++) 
    {
      var aRow = document.getElementById('file' + i);
      if (aRow.value.length <= 0) 
      {
        alert('Row ' + i + ' is empty');
        return false;
      }
      else
      {
      	addUploadField();
      	addTable();
      }
    }  
}

function redirect(url)
{
	window.location = url;
}

function resetInfo()
{
	$('product.name').innerHTML = "";	
	$('product.barcode').innerHTML = "";
	$('product.taxCategory').innerHTML = "";
	$('product.description').innerHTML = "";
	$('product.stock.qtyBook').innerHTML = "";
	$('product.price.list').innerHTML = "";
	
	$('product.total.lines').innerHTML = "";
	$('product.total.count').innerHTML = "";
	$('product.total.book').innerHTML = "";
	$('product.total.csv').innerHTML = "";
	$('product.discrepancy').innerHTML = "";
	
}

function newCart()
{
		var answer = confirm("Click OK if you want to save the inventory cart and start a new one.\nClick CANCEL to only clear the cart.\nNote: Clearing the cart will erase all the data. ")
		if (answer)
		{
			if(cartLines == null || cartLines.length == 0)
			{		
				redirect('InventoryCartAction.do?action=newInventoryCart');
			}
			else
			{
				Modalbox.show('<div align="center">Please Wait...<br><img src="images/loader.gif" /><p></div>', {title: 'Saving Inventory', width: 800});
				Modalbox.deactivate();
						
				var url = 'InventoryCartAction.do';
				var pars = 'action=autoSaveInventoryLines&inventoryId='+inventoryId+'&priceListId='+priceList+'&description='+description;
							
				var myAjax = new Ajax.Request( url, 
				{ 
					method: 'get', 
					parameters: pars, 
					onSuccess: function(){redirect('InventoryCartAction.do?action=newInventoryCart');}, 
					onFailure: reportError
				});
				
			}
				
		}
		else
		{
			clearCart();
		}
}

Event.observe(window,'load',initScreen,false);