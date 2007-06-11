/****************************************************************************
 * Compiere (c) Jorg Janke - All rights reseverd
 * $Id: wstore.js,v 1.5 2006/05/23 22:39:03 mdeaelfweald Exp $
 *
 * Web Store Scripts
 ***************************************************************************/

var mandatory = "Enter mandatory:";

/**
 *	Is field empty ?
 *  Returns true if field is empty
 */
function isEmpty (value)
{
	if (value == null)
		return true;
	if (value == "")
		return true;
	for (var i = 0; i < value.length; i++)
	{
		var c = value.charAt(i);
		if ((c != ' ' && c != '\n' && c != '\t'))
			return false;
	}
	return true;
}



function checkCreditCard(field)
{
	window.alert(field.name + "=" + field.value);
	return true;
}

function checkExpDate(field)
{
	window.alert(field.name + "=" + field.value);
	return true;
}

function checkABA (field)
{
	window.alert(field.name + "=" + field.value);
	return true;
}

function checkBAcct (field)
{
	window.alert(field.name + "=" + field.value);
	return true;
}
function checkChknum (field)
{
	window.alert(field.name + "=" + field.value);
	return true;
}

function checkDL (field)
{
	window.alert(field.name + "=" + field.value);
	return true;
}

function checkField (field)
{
	window.alert(field.name + "=" + field.value);
	return true;
}


/**
 * 	Test mandatory fields for lookup
 */
function checkLookup (field)
{
	window.alert (field);
	var f = field.form;
	window.alert (f);
	if (!isEmpty(f.EMAIL.value) && !isEmpty(f.password.value))
		return true;
	var msg = mandatory;
	if (isEmpty(f.EMAIL.value))
		mandatory += "\n - " + f.EMAIL.title;
	if (isEmpty(f.password.value))
		mandatory += "\n - " + f.password.title;
	window.alert(mandatory);
	return false;
}

var statusInfo = '';

/****************************************************************************
 * 	Check form
 *	- onSubmit="submitForm(this, new Array ('Name','..'));"
 */
function checkForm (formObj, requiredFields)
{
	statusInfo += 'checkForm:' + formObj.name + '[' + requiredFields.length + ']';
	if (formObj.nodeName == 'FORM')
	{
		if (formObj.Submit)
		{
			formObj.Submit.disabled=true;
			statusInfo += '(' + formObj.Submit.name + ')';
		}
	}
	else
	{
		formObj = formObj.form;
		if (formObj == null | formObj.nodeName != 'FORM')
		{
			alert ('invalid submitter');
			return false;
		}
		statusInfo += '->' + formObj.name;
	}
	window.status=statusInfo;
	
	var alertMsg = "";
	//	check required fields
	if (requiredFields)
	{
		for (i=0; i<requiredFields.length; i++)
		{
			formElemLength = eval ("formObj." + requiredFields[i] + ".value.length");
			if (formElemLength == 0)
				alertMsg += "- " + requiredFields[i] + "\n";
		}
	}
	//	show Error Message
	if (alertMsg.length)
	{
		if (formObj.Submit)
			formObj.Submit.disabled=false;
		alertMsg = mandatory + "\n" + alertMsg;
		alert (alertMsg);
		return false;
	}
	//	Switch on processing
	if (document.getElementById('submitDiv'))
    {
		document.getElementById('submitDiv').style.display='none';
		document.getElementById('processingDiv').style.display='inline';
	}
	//
	statusInfo += ' done - ';
//	window.status=statusInfo;
	return true;
}	//	submitForm

/**
 * Pop up Window
 */
function popUp(url) 
{
	sealWin=window.open(url,"win",'toolbar=0,location=0,directories=0,status=1,menubar=1,scrollbars=1,resizable=1,width=500,height=450');
	self.name = "mainWin";
}

/**
 * Javascript Query String functionality
 */
this.queryString = new Array();
if(this.location)
{
	var index = (this.location+"").indexOf("?");
	if(index != -1)
	{
		var qs = (this.location+"").substring(index+1);
		var qsArray = qs.split("&");
		for(var i=0; i<qsArray.length; i++)
		{
			var keyValue = qsArray[i];
			index = keyValue.indexOf('=');
			if(index != -1)
			{
				queryString[unescape(keyValue.substring(0,index))]=unescape(keyValue.substring(index+1));
			}
		}
	}
}

/**
 * Multiplex Windows OnLoad
 */
onLoadListeners = new Array();
addOnLoadListener=function(listener)
{
	onLoadListeners[onLoadListeners.length]=listener;
}
window.onload=function()
{
	for(var i=0; i<onLoadListeners.length; i++)
	{
		var listener = onLoadListeners[i];
		listener.call();
	}
}

AJAX = new Array(); // namespace declaration

/**
 * AJAX Status Notification
 */
AJAX.displayStatus=function(message)
{
	document.getElementById("status").innerHTML = message;
	document.getElementById("status").style.display=("inline");
}
AJAX.hideStatus=function()
{
	document.getElementById("status").style.display=("none");
}
AJAX.timeoutStatus=function(message, timeout)
{
	AJAX.displayStatus(message);
	setTimeout("AJAX.hideStatus()", timeout);
}

/**
 * AJAX: Derived from 'Ajax in Action'
 */
AJAX.READY_STATES=new Array("UNITIALIZED","LOADING","LOADED","INTERACTIVE","COMPLETE");
AJAX.READY_STATE_UNINITIALIZED=0;
AJAX.READY_STATE_LOADING=1;
AJAX.READY_STATE_LOADED=2;
AJAX.READY_STATE_INTERACTIVE=3;
AJAX.READY_STATE_COMPLETE=4;
AJAX.AjaxLoader=function(url, onload, onerror, HttpMethod, HttpParams)
{
	this.url = url;
	this.request = null;
	this.onload = onload;
	this.onerror=(onerror)?onerror:this.defaultError;
	this.HttpMethod = (HttpMethod)?HttpMethod:"GET";
    this.HttpParams = null;
    if(HttpParams != null)
    {
        for(var key in HttpParams)
        {
            if(this.HttpParams == null)
                this.HttpParams = "";
            else
                this.HttpParams = this.HttpParams + "&";

            this.HttpParams = this.HttpParams + key + "=" + HttpParams[key];
        }
    }

	this.loadXMLDoc(url);
}
AJAX.AjaxLoader.prototype={
	loadXMLDoc:function(url)
	{
        var loader=this;

        try {
            netscape.security.PrivilegeManager.enablePrivilege("UniversalBrowserRead");
        } catch (e) {
            //alert("Permission UniversalBrowserRead denied.");
        }
        
        try{
            loader.request = new XMLHttpRequest();
        }catch(e1){
            try{
                loader.request = new ActiveXObject("Msxml2.XMLHTTP");
            }catch(e2){
                try{
                    loader.request = new ActiveXObject("Microsoft.XMLHTTP");
                }catch(e3){
                    alert("AJAX Not Supported!");
                }
            }
        }

		if(!loader.request)
		{
			AJAX.displayStatus("Unable to create XMLHttpRequest");
			return;
		}

		try{
			AJAX.displayStatus("Loading...");
			this.request.onreadystatechange=function(){
				loader.onReadyState.call(loader);
			}

            if( (loader.HttpParams != null) && (loader.HttpMethod == 'GET'))
            {
                url = url + "?" + loader.HttpParams;
                loader.HttpParams = null;
            }

            loader.request.open(loader.HttpMethod, url, true);//true is asynchronous
            loader.request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            loader.request.send(loader.HttpParams);

			AJAX.hideStatus();
		}catch(err){
			AJAX.timeoutStatus("ERROR...", 3000);
			loader.onerror.call(loader);
		}
	},
    onReadyState:function()
	{
		var request=this.request;
		var ready=request.readyState;
		if(ready != AJAX.READY_STATE_COMPLETE) return;

		var httpStatus=request.status;
		if(httpStatus==200 || httpStatus==0)
		{
			this.onload.call(this);
		}else{
			this.onerror.call(this);
		}
	},
	defaultError:function()
	{
        alert("error fetching data!"
            +"\n\nreadyState: "+this.request.readyState
            +"\nstatus: "+AJAX.READY_STATES[this.request.status]+" ["+this.request.status+"]"
            +"\nheaders: " + this.request.getAllResponseHeaders()
        );
	}
}

/**
 * Change height of floating pieces
 */
function resizeContent()
{
	var menu = document.getElementById("menu");
	var content = document.getElementById("content");
	var vendor = document.getElementById("vendorUse");
	var main = document.getElementById("main");

	// determine tallest
	var height = Math.max(menu.offsetHeight, Math.max(content.offsetHeight, vendor.offsetHeight));

	// stretch to fit
	if(menu.offsetHeight != height)
		menu.style.height=height+"px";
	// hack to ensure that padding, border, et al are taken into account
	if(menu.offsetHeight > height)
		menu.style.height = height - (menu.offsetHeight - height) + "px";
		
	if(content.offsetHeight != height)
		content.style.height=height+"px";
	// hack to ensure that padding, border, et al are taken into account
	if(content.offsetHeight > height)
		content.style.height = height - (content.offsetHeight - height) + "px";

	if(vendor.offsetHeight != height)
		vendor.style.height = height+"px";
	// hack to ensure that padding, border, et al are taken into account
	if(vendor.offsetHeight > height)
		vendor.style.height = height - (vendor.offsetHeight - height) + "px";

	if(main.offsetHeight != height)
		main.style.height = height+"px";
	// hack to ensure that padding, border, et al are taken into account
	if(main.offsetHeight > height)
		main.style.height = height - (main.offsetHeight - height) + "px";
}
addOnLoadListener(resizeContent);

function updateSelect(ajax, xmlTagName, selectId)
{
//    alert("updateSelect("+xmlTagName+","+selectId+"): " + ajax.request.responseText);
    var xml = ajax.request.responseXML;
    var error = xml.getElementsByTagName("error")[0];
    if(error)
    {
        AJAX.timeoutStatus(error.textContent, 3000);
        return;
    }

    var    xmlOptions = xml.getElementsByTagName(xmlTagName)[0];
       if(!xmlOptions)
       {
           AJAX.timeoutStatus("Unable to read response", 3000);
           return;
       }

		// first we remove existing entries
		var htmlSelect = document.getElementById(selectId);
		while(htmlSelect.options.length > 0)
		{
			htmlSelect.removeChild(htmlSelect.options[0]);
		}

       for(var i=0; i<xmlOptions.childNodes.length; i++)
       {
           var xmlOption = xmlOptions.childNodes[i];

           var attrs = xmlOption.attributes;
           if(!attrs) continue; // otherwise Firefox bails with more children than IE

           var htmlOption = document.createElement("option");

           var attrId = attrs.getNamedItem('id');
           if(attrId != null) htmlOption.setAttribute('value', attrId.value);

           var attrSelected = attrs.getNamedItem('selected');
           if(attrSelected != null) htmlOption.setAttribute('selected', 'selected');

           var name = xmlOption.firstChild.data;
           htmlOption.appendChild(document.createTextNode(name));
           htmlSelect.appendChild(htmlOption);
       }
}

/**
 * Location
 */
function changeCountry(countrySelectId)
{
    var countrySelect = document.getElementById(countrySelectId);
    var countryOption = countrySelect.options[countrySelect.selectedIndex];
    var countryId = countryOption.attributes.getNamedItem('value').value;

    /**
     * call locationServlet
     * param: cmd = 'regions'
     * param: country = countryId
     *
     * get back:
     *  <regions country='countryId'>
     *      <region id='regionID'>AL</region>
     *      <region id='regionID'>AK</region>
     *      <region id='regionID' selected='true'>OR</region>
     *  </regions>
     */

   var params = new Array();
    params['cmd']='regions';
    params['country']=countryId;
    //params['selected']=regionId;
    var loader = new AJAX.AjaxLoader("locationServlet", changeCountryCallback, null, "GET", params);
}

function changeCountryCallback()
{
	updateSelect(this, "regions", "ID_C_Region_ID");
}