/****************************************************************************
 * Compiere (c) Jorg Janke - All rights reseverd
 * $Id: standard.js,v 1.1 2009/04/15 11:27:37 vinhpt Exp $
 *
 * General Header Script shared by Web UI and WebStore
 ***************************************************************************/
 
var cvs = '$Id: standard.js,v 1.1 2009/04/15 11:27:37 vinhpt Exp $';
var isIE = (navigator.userAgent.indexOf("MSIE") != -1);


/********************************************************************************
 *	List Browser Info
 */
function diag_window() 
{
	var info = 'Window Info:\n\n'; 
	for (var prop in self)
		info += prop + '=' + self[prop] + ' - ';

	info += '\n\nTop Window Info:\n\n'; 
	for (var prop in top)
		info += prop + '=' + top[prop] + ' - ';

	info += '\n\nOpener Window Info:\n\n';
	for (var prop in opener)
		info += prop + '=' + opener[prop] + ' - ';
	//	info += prop + ' - ';

	alert (info);
}	//	diag_window

function diag_navigator() 
{
	var info = 'Navigator Info:\n\n'; 
	for (var prop in navigator)
		info += prop + '= ' + navigator[prop] + '\n';
	
	info += '\n\n' + cvs;
	alert (info);
}	//	diag_navigator

/**
 *	List Document Elements
 */
function diag_document() 
{
	var info = 'Document Title=' + document.title 
		+ '\nLocation=' + document.location
		+ ', URL=' + document.URL;
	
	info += '\n\nProperties:\n';
	for (prop in document)
		info += prop + ' - ';
	
	info += '\n\nParent Window:\n';
	for (var prop in document.parentWindow)
		info += prop + '=' + document.parentWindow[prop] + ' - ';
	alert (info); 
}	//	diag_document

/**
 *	Request Info
 */
function diag_request()
{
	var info = 'Document Request Info:\n\n';
	
	info += 'Title: ' + document.title + '\n';
	info += 'Domain: ' + document.domain + '\n';
	info += 'URL: ' + document.URL + '\n';
	info += 'URL Encoded: ' + document.URLEncoded + '\n';
	info += 'Protocol: ' + document.protocol + '\n';
	info += 'Referrer: ' + document.referrer + '\n';
	info += 'Updated: ' + document.lastModified + '\n';
	
	alert (info);
}	//	diag_request

/**
 *	Form info
 */
function diag_form ()
{
	// Open the new window.
	var w = window.open("", "diag_form", 
		"menubar=yes,scrollbars=yes,resizable=yes," +
		"width=600,height=300");

	w.document.open();
	w.document.writeln("<h1>Form Details</h1>" 
		+ "<h2>" + document.title + "</h2>"
		+ "<p>Number of forms: " + document.forms.length + "</p>");


	//	for all frames
	for (var i = 0; i < document.forms.length; i++)
	{
		var myForm = document.forms[i];
		w.document.writeln("<h3>Form " + i + ": " + myForm.name + "</h3>");
		//	List Elements
		for (var j = 0; j < myForm.elements.length; j++)
		{
			var myElement = myForm.elements[j];
			w.document.writeln ("<h4>" + myElement.name + ": " 
				+ myElement.type + ", Value=" + myElement.value + "</h4>");
			for (var prop in myElement)
				w.document.writeln (" - " + prop + "=" + myElement[prop]);
			// list attributes
			w.document.writeln ('<br><b>Attributes:</b>');
			for (prop in myElement.attributes)
				w.document.writeln (' - ' + prop + '=' + myElement.attributes[prop]);
		}	//	all elements
	}	//	all frames

	w.document.close();
}	//	diag_form

/**
 *	Show Source
 */
function diag_source()
{
	// Open the new window.
	var w = window.open("", "diag_source", 
		"dependent=yes,menubar=yes,scrollbars=yes,resizable=yes," +
		"width=600,height=300");

	w.document.open ();
	var myTitle = "Show Source: " + document.title;
	w.document.writeln("<html><head><title>" + myTitle + "</title></head>");
	w.document.writeln("<body><h2>" + myTitle + "</h2>");
	//	Convert to HTML
  var elementList = getElementsByTagName(document);
	var text = getOuterHTML(elementList[0]);
	if (text.length == 0)
		text = getOuterHTML(elementList[1]);
	text = text.replace(/&/g, "&amp;");
	text = text.replace(/[ ]/g, "&nbsp;");
	text = text.replace(/\x22/g, "&quot;");	// "
	//	change < to {{
	text = text.replace(/</g, "{{");
	//	change > to }}
	text = text.replace(/>/g, "}}");
	//	change {{ i.e. "<" to <font ..>
	text = text.replace(/\{\{/g, "<font color='blue'>&lt;");

	//	change }} i.e. ">" to </font>
	text = text.replace(/}}/g, "&gt;</font>");
	text = text.replace(/[\n]/g, "<br>");
	//
	w.document.writeln(text);
	//
	w.document.writeln("</body></html>");
	w.document.close();
}	//	diag_source


/**
 *	Error handler - generates an HTML form to report the error
 */
function report_error(msg, url, line)
{
	var w = window.open ("", "error",			//	
		"resizable,status,width=625,height=400");	//	features
	var d = w.document;
	d.open();

	//	Output 
	d.write('<div align="center">');
	d.write('<h1>Java Error</h1>');

	d.write('<form action="mailto:info@adempiere.org" method="post" enctype="text/plain">');
	d.write('<input type="submit" value="Report Error">');
	d.write('<input type="button" value="Ignore Error" onClick="self.close()">');

	d.write('<div align="right">');
	
	d.write('<br>Message: <input size=50 name="msg" value="' + msg + '">');
	d.write('<br>Document: <input size=50 name="url" value="' + url + '">');
	d.write('<br>Line: <input size=50 name="line" value="' + line + '">');
	d.write('<br>Browser: <input size=50 name="browser" value="' + navigator.userAgent + '">');

	d.write('</div>');
	d.write('</form>');
	//
	d.close();
	return true;
}	//	report_error
//self.onerror = report_error;


/****************************************************************************
 *	Show Loading - base=directory where to find the css
 *	- Rewrite field with id ticker with more dots
 */
function showLoadingWindow (base)
{
	var d = parent.WWindow.document;
	d.open();
	//	Content
	d.write('<link href="' + base + 'standard.css" rel="stylesheet">');
	d.write('<h1 id="ticker">.</h1>');
	//	Script
	d.write('<script>var tickNo=1;');
	d.write('function tick() { var info = "<h1 id=\'ticker\'>.";');
	d.write('for (var i = 0; i < tickNo; i++) info += " .";');
	d.write('info += "</h1>"; tickNo++; setOuterHTML(getElementById(\'ticker\'), info); }');
	d.write('setInterval("tick();", 1500); </script>');
	//	Fini
	d.close();
	return true;		//	follow the link
}	//	showLoadingWindow
//
function showLoadingMenu(base)
{
	parent.resizeFrame('5,*');	
	var d = parent.WMenu.document;
	d.open();
	//	Content
	d.write('<link href="' + base + 'standard.css" rel="stylesheet">');
	d.write('<h1 id="ticker">.</h1>');
	//	Script
	d.write('<script>var tickNo=1;');
	d.write('function tick() { var info = "<h1 id=\'ticker\'>.";');
	d.write('for (var i = 0; i < tickNo; i++) info += " .";');
	d.write('info += "</h1>"; tickNo++; setOuterHTML(getElementById(\'ticker\'), info); }');
	d.write('setInterval("tick();", 1500); </script>');
	//	Fini
	d.close();	
	return true;		//	follow the link
}	//	showLoadingMenu


function openPWindow (url)
{
	var w = window.open(url, "location", 
		"dependent=yes,scrollbars=yes,resizable=yes,width=600,height=300");
	w.focus();
	return false;	//	do not submit page
}


/****************************************************************************
 *	Hide Elements with ID
 */
function hide (idname)
{
	el = getElementById(idname);
	if (el != null)
	{
		el.style.display = 'none';	
	}
}	//	hide

/**
 *	Show Elements with ID
 */
function show (idname)
{
	el = getElementById(idname);
	if (el != null)
	{
		el.style.display = '';
	}
}	//	show

/**
 *	Toggle Hide/Show Elements with ID
 */
function toggle (idname)
{
	el = getElementById(idname);
	if (el != null && typeof(el.style) != 'undefined')
	{
		if (el.style.display == 'none')
			el.style.display = '';
		else
			el.style.display = 'none';
	}
}	//	toggle

/** Provide document.all compatible methods for 
 *  Mozilla and other browsers which implement 
 *  W3C DOM Level 1 document.getElementById
 * 
 *  Author Bob Clary bc@bclary.com
 *  February 8, 2004
 */
function getElementById(id)
{
	if (!id)
	{
		return null;
	}

	if (typeof(document.getElementById) != 'undefined')
	{
		return document.getElementById(id);
	}

	if (typeof(document.all) != 'undefined')
	{
		return document.all[id];
	}
	return null;
}	// getElementById(id)

/** Provide HTMLElement.all.item/HTMLElement.all.tags compatible 
 *  methods for Mozilla and other browsers which implement 
 *  W3C DOM Level 1 getElementsByTagName
 * 
 *  Author Bob Clary bc@bclary.com
 *  February 8, 2004
 */
function getElementsByTagName(elm, tagname)
{
	if (!elm)
	{
		return [];
	}

	if (!tagname)
	{
		tagname = '*';
	}

	if (typeof(elm.getElementsByTagName) != 'undefined')
	{
		return elm.getElementsByTagName(tagname);
  	}

	if (typeof(elm.all) != 'undefined')
	{
    	return elm.all[tagname];
	}

	return [];
}	// getElementsByTagName(elm, tagname)

/** Provide HTMLElement.outerHTML compatible methods for 
 *  Mozilla and other browsers which implement HTMLElement.innerHTML 
 *  but not HTMLElement.outerHTML
 * 
 *  Author Bob Clary bc@bclary.com
 *  February 8, 2004
 */
function getOuterHTML(elm)
{
  if (!elm)
  {
    return '';
  }

  if (typeof(elm.outerHTML) == 'string')
  {
    return elm.outerHTML;
  }

  var attrList   = elm.attributes;
  var attrLength = attrList.length;
  var outer      = '<' + elm.tagName;

  if (attrLength > 0)
  {
    outer += ' ';
  }

  for (var i = 0; i < attrLength; i++)
  {
    var attr  = attrList[i];
    var value = attr.value;
    value     = value.replace(/"/g, '\\"');
    value     = value.replace(/'/g, "\\'");
    outer    += attr.name + '="' + value + '" ';
  }
  outer = outer + '>' + elm.innerHTML + '<\/' + elm.tagName + '>';
  return outer;
} // getOuterHTML(elm)

function setOuterHTML(elm, value)
{
  if (!elm)
  {
    return;
  }

  if (typeof(elm.outerHTML) == 'string')
  {
    elm.outerHTML = value;
    return;
  }

  // the remainder requires ability to createElements,
  // set style properties, and innerHTML
  if (typeof(document.createElement) == 'undefined' ||
      typeof(elm.style) == 'undefined' ||
      typeof(elm.innerHTML) != 'string')
  {
    return;
  }

  // hide the element to be replaced
  elm.style.display = 'none';

  // create a temporary element to place the
  // html using innerHTML to obtain the DOM
  // for the HTML
  var tempElm = document.createElement('div');
  tempElm.innerHTML = value;

  // insert the new HTML's DOM before the element
  var tempChildList = tempElm.childNodes;
  var tempChildLength = tempChildList.length;
  var parent        = elm.parentNode;

  for (var i = 0; i < tempChildLength; i++)
  {
    parent.insertBefore(tempChildList[i], elm);
  }

  // remove the original element 
  parent.removeChild(elm);
} // setOuterHTML(elm, value)

/* */
