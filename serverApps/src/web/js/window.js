/****************************************************************************
 * Compiere (c) Jorg Janke - All rights reseverd
 * $Id: window.js,v 1.1 2009/04/15 11:27:37 vinhpt Exp $
 *
 * Web UI Window Utilities
 ***************************************************************************/

/****************************************************************************
 *	Text constants
 */
var deleteText = "ConfirmDelete";
/****************************************************************************
 *	Popup Menu variables
 */
var parentopener;
/****************************************************************************
 *	Field Update
 ***************************************************************************/
function fieldUpdate(e)
{

	if (!top.WCmd) //{		no cmd frame	
			return;

		//if (!top.myiframe.WCmd){
		//	return;
		//}		
		//else{
		//	var d = top.myiframe.WCmd.document;
		//	var path = "top.myiframe.";
		//}
	//}
	//else{
		var d = top.WCmd.document;
		var path = "top.";
	//}//


	//if (!e) e = window.event;


	//	update info and submit
	//var d = top.WCmd.document;
	//alert("field Name "+e.name+" Field Value "+e.value);

	d.fieldUpdate.formName.value = e.form.name; //e.document.forms[0].name;
	d.fieldUpdate.fieldName.value = e.name;
	d.fieldUpdate.fieldValue.value = e.value;
	d.fieldUpdate.location.value = path;
	d.fieldUpdate.submit();
}	//	fieldUpdate

/**
 *	Create Initial Command Window
 */
function createWCmd()
{
	
	if (!top.WCmd)//{		no cmd frame
			return;

		//if (!top.myiframe.WCmd){
		//	return;
		//}		
		//else{
		//	var d = top.myiframe.WCmd.document;
		//	var path = "top.myiframe.";
		//}
	//}
	//else{
		var d = top.WCmd.document;
		var path = "top.";
	//}
	// write to the command window.

	d.open();
	d.writeln('<form name="fieldUpdate" method="post" action="/adempiere/WFieldUpdate">');
	d.writeln('<input type="hidden" name="formName" value="x">');
	d.writeln('<input type="hidden" name="fieldName" value="x">');
	d.writeln('<input type="hidden" name="fieldValue" value="x">');
	d.writeln('<input type="hidden" name="location" value=path>');
	d.writeln('</form>');
	d.close();
}	//	createWCmd
//	Execute it
createWCmd();


/****************************************************************************
 *	Dynamic Display
 *	- for form: WForm
 *	- changing field should have onChange="dynDisplay" to trigger evaluation
 *	- changed field should have document.WForm.field.displayLogic='expression'	
 */
function dynDisplay()
{
	var el = document.WForm.elements;
	var info = "dynDisplay:";
	//	for all fields
	for (var i = 0; i < el.length; i++)
	{
		//	do we have displayLogic ?
		var dLogic = el[i].displayLogic;		
		if (typeof dLogic == "string" && dLogic.length > 0)
		{
			fieldName = el[i].name;
			if (evaluate(dLogic))
			{
				show(fieldName+"L");
				show(fieldName+"F");
				show(fieldName+"B");
				info += " show:" + fieldName;
			}
			else
			{
				hide(fieldName+"L");
				hide(fieldName+"F");
				hide(fieldName+"B");
				info += " hide:" + fieldName;
			}
		}	//	we have displayLogic
	}	//	for all fields
	window.status = info;
}	//	dynDisplay

/**
 *	Evaluate Display Logic
 *	>> |& <<
 */
function evaluate (dLogic)
{
	var pos1 = dLogic.indexOf('&');
	var pos2 = dLogic.indexOf('|');

	//	only a tuple	
	if (pos1 == pos2)
	{
		return evaluateTuple(dLogic);
	}

	//	and: &
	else if (pos1 > pos2)
	{
		tuples = dLogic.split('&');
		return evaluateTuple(tuples[0]) && evaluate(dLogic.substring(pos1+1));
	}

	//	or: |
	else
	{
		tuples = dLogic.split('|');
		return evaluateTuple(tuples[0]) || evaluate(dLogic.substring(pos2+1));
	}
}	//	evaluate

/**
 *	evaluate tuple 'x = y' or x ^ y or x ! y
 *	>> =!^ <<
 */
function evaluateTuple(myValue)
{
	//	Equals
	var tuples = myValue.split('=');
	if (tuples.length == 2)
		return getRealValue(tuples[0]) == getRealValue(tuples[1]);
	//	Not Equals
	tuples = myValue.split('^');
	if (tuples.length == 2)
		return getRealValue(tuples[0]) != getRealValue(tuples[1]);
	tuples = myValue.split('!');
	if (tuples.length == 2)
		return getRealValue(tuples[0]) != getRealValue(tuples[1]);
	//
	alert ('Error: evaluateTuple="' + myValue + '" invalid.');
	return false;
}	//	evaluateTuple

/**
 *	get (variable) value
 */
function getRealValue (myValue)
{
	var pos1 = myValue.indexOf('@');
	var pos2 = myValue.indexOf('@', pos1+1);

	//	Constant - remove blanks an '"
	if (pos1 == pos2)
		return myValue.replace(/['" ]/g, "");
	
	//	Variable
	var variable = myValue.substring(pos1+1, pos2);
	for (var i = 0; i < document.WForm.elements.length; i++)
	{
		if (document.WForm.elements[i].name == variable)
			return document.WForm.elements[i].value;
	}
	//	Nothing found
	return "";
}	//	getRealValue


/****************************************************************************
 *  Open PopUp with Attachment Info
 */
function popUp(URL,name) {
	day = new Date();
	var id = day.getTime();
	var callWindow = window;
	
	var openwindow = eval("page" + id + " = window.open(URL, '" + name + "', 'toolbar=0,scrollbars=1,location=0,statusbar=1,menubar=0,resizable=1,width=600,height=400,left = 212,top = 234');");
	openwindow.opener = callWindow;
}

/****************************************************************************
 *  Start PopUp
 */
function startPopup (targetCmd)
{

	var url = targetCmd;
	return popUp(url,targetCmd);
}   //  startPopup

/****************************************************************************
 *  Close PopUp
 */
function closePopup ()
{
    parent.document.getElementById("framesetWindow").rows="0,*";
    return true;   //  do submit page
}   //  closePopUp
/****************************************************************************
 *	Lookup - get FormName and ColumnName and submit to WLookup
 */
function startLookup (columnName, processid, page)
{

	var url = "WLookup?ColumnName=" + columnName+"&AD_Process_ID="+processid+"&page="+page	
	return popUp(url,columnName);
}	//	startLookup
/****************************************************************************
 *	Lookup - get FormName and ColumnName and submit to WLookup
 */
function startZoom (TableID, RecordID)
{
	var url = "WWindow?AD_Table_ID=" + TableID+"&AD_Record_ID="+RecordID;
	//parent.WWindow.location = '/adempiere/' + url;
	popUp('/adempiere/' + url, 'WTable' + TableID);
	return false;   //  do not submit page

}	//	startZoom 
/****************************************************************************
 *	Account - get FormName and ColumnName and submit to WAccount
 */
function startAccount (columnName)
{
	var url = "WAccount?ColumnName=" + columnName;
	return popUp(url,columnName);
}	//	startAccount

/****************************************************************************
 *	Location - get FormName and ColumnName and submit to WLocation
 */
function startLocation (columnName)
{
	var url = "WLocation?ColumnName=" + columnName;
	return popUp(url,columnName);
}	//	startLocation

/****************************************************************************
 *	Field Updated - submit
 */
function startUpdate (column)
{
	//alert(column);
	column.form.ChangedColumn.value=column.name;
    	column.form.submit();
}	//	startUpdate

/****************************************************************************
 *	Lookup Field Updated - submit
 */
function startLookUpdate(column, name1, value1, name2, value2)
{	/*	
	browser = navigator.appName
	
	if(browser=="Netscape" ){
		var d = window.parentopener.document;
	}
	else{
		var d = opener.document;
	}*/
	
	var d = opener.document;
	window.close();	
	d.getElementById(name2).focus();
	d.getElementById(name1).value =value1;
	d.getElementById(name2).value =value2;	
}	//	startLookUpdate


/****************************************************************************
 *	Process Button
 */
function startButton (processID, windowID, recordID, tableID, columnName)
{
	var url = "WProcess?AD_Process_ID=" + processID + "&AD_Window_ID="+windowID+
	"&AD_Record_ID="+recordID+"&AD_Table_ID="+tableID+"&columnName="+columnName;
	return popUp(url,columnName);

}	//	startButton

/****************************************************************************
 *	start Value Preference Button
 */

function startValuePref(displayType, displayData, value, attributedisplay, attribute,
	userID, orgID, clientID, windowID)
{
	var url = "WValuePreference?DisplayType=" + displayType + "&DisplayValue="+displayData+
	"&Value="+value+"&DisplayAtrribute="+attributedisplay+"&Attribute="+attribute+
	"&AD_User_ID="+userID+"&AD_Org_ID="+orgID+"&AD_Client_ID="+clientID+"&AD_Window_ID="+windowID;
	//alert(url);
	return popUp(url,attribute);

}	//	startValuePref
/****************************************************************************
 *	Update Value Preference Button
 */

function updateValuePref(action)
{
Form = document.forms[0];
	//alert(action);
	document.valuepreference.PostAction.value=action;
    	Form.submit();
}	//	startValuePref

/****************************************************************************
 *	Process Toolbar Button
 */
function SubmitForm(pValue, pAction, pType)
{
Form = document.forms[0];
if (pType=='toolbar')
	{
	document.WForm.PCommand.value= pValue;
	if (pAction == 'reset')
		Form.reset();
	else if (pValue== 'Delete'){
		if(confirm('Do you want to delete the record?')){
			Form.submit();
		}
	}
	else
		Form.submit();
	}
if (pType=='tab')
	{
	document.WForm.PTab.value= pValue;
	Form.submit();
	}
}
/****************************************************************************
 *	Process Calendar
 */

var oldLink = null;
// code to change the active stylesheet
function setActiveStyleSheet(link, title) {
  var i, a, main;
  for(i=0; (a = document.getElementsByTagName("link")[i]); i++) {
    if(a.getAttribute("rel").indexOf("style") != -1 && a.getAttribute("title")) {
      a.disabled = true;
      if(a.getAttribute("title") == title) a.disabled = false;
    }
  }
  if (oldLink) oldLink.style.fontWeight = 'normal';
  oldLink = link;
  link.style.fontWeight = 'bold';
  return false;
}

// This function gets called when the end-user clicks on some date.
function selected(cal, date) {
  cal.sel.value = date; // just update the date in the input field.
  if (cal.dateClicked && (cal.sel.id == "sel1" || cal.sel.id == "sel3"))
    // if we add this call we close the calendar on single-click.
    // just to exemplify both cases, we are using this only for the 1st
    // and the 3rd field, while 2nd and 4th will still require double-click.
    cal.callCloseHandler();
}

// And this gets called when the end-user clicks on the _selected_ date,
// or clicks on the "Close" button.  It just hides the calendar without
// destroying it.
function closeHandler(cal) {
  cal.hide();                        // hide the calendar
//  cal.destroy();
  _dynarch_popupCalendar = null;
}

// This function shows the calendar under the element having the given id.
// It takes care of catching "mousedown" signals on document and hiding the
// calendar if the click was outside.
function showCalendar(id, format, showsTime, showsOtherMonths) {	
  var el = document.getElementById(id);
  if (_dynarch_popupCalendar != null) {	
    // we already have some calendar created
    _dynarch_popupCalendar.hide();                 // so we hide it first.
  } else {	
    // first-time call, create the calendar.
    var cal = new Calendar(1, null, selected, closeHandler);
    // uncomment the following line to hide the week numbers
    // cal.weekNumbers = false;
	cal.singleClick = true;
    if (typeof showsTime == "string") {
      cal.showsTime = true;
      cal.time24 = (showsTime == "24");
    }
    if (showsOtherMonths) {
      cal.showsOtherMonths = true;
    }
    _dynarch_popupCalendar = cal;                  // remember it in the global var
    cal.setRange(1900, 2070);        // min/max year allowed.
    cal.create();
  }
  _dynarch_popupCalendar.setDateFormat(format);    // set the specified date format
  _dynarch_popupCalendar.parseDate(el.value);      // try to parse the text in field
  _dynarch_popupCalendar.sel = el;                 // inform it what input field we use

  // the reference element that we pass to showAtElement is the button that
  // triggers the calendar.  In this example we align the calendar bottom-right
  // to the button.   
  _dynarch_popupCalendar.showAtElement(el, "Br");        // show the calendar

  return false;
}

var MINUTE = 60 * 1000;
var HOUR = 60 * MINUTE;
var DAY = 24 * HOUR;
var WEEK = 7 * DAY;

/***********************************************
* AnyLink CSS Menu script- © Dynamic Drive DHTML code library (www.dynamicdrive.com)
* This notice MUST stay intact for legal use
* Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
***********************************************/

var disappeardelay=250  //menu disappear speed onMouseout (in miliseconds)
var enableanchorlink=0 //Enable or disable the anchor link when clicked on? (1=e, 0=d)
var hidemenu_onclick=1 //hide menu when user clicks within menu? (1=yes, 0=no)

/////No further editting needed

var ie5=document.all
var ns6=document.getElementById&&!document.all

function getposOffset(what, offsettype){
var totaloffset=(offsettype=="left")? what.offsetLeft : what.offsetTop;
var parentEl=what.offsetParent;
while (parentEl!=null){
totaloffset=(offsettype=="left")? totaloffset+parentEl.offsetLeft : totaloffset+parentEl.offsetTop;
parentEl=parentEl.offsetParent;
}
return totaloffset;
}

function showhide(obj, e, visible, hidden){
if (ie5||ns6)
dropmenuobj.style.left=dropmenuobj.style.top=-500
if (e.type=="click" && obj.visibility==hidden || e.type=="mouseover")
obj.visibility=visible
else if (e.type=="click")
obj.visibility=hidden
}

function iecompattest(){
return (document.compatMode && document.compatMode!="BackCompat")? document.documentElement : document.body
}

function clearbrowseredge(obj, whichedge){
var edgeoffset=0
if (whichedge=="rightedge"){
var windowedge=ie5 && !window.opera? iecompattest().scrollLeft+iecompattest().clientWidth-15 : window.pageXOffset+window.innerWidth-15
dropmenuobj.contentmeasure=dropmenuobj.offsetWidth
if (windowedge-dropmenuobj.x < dropmenuobj.contentmeasure)
edgeoffset=dropmenuobj.contentmeasure-obj.offsetWidth
}
else{
var topedge=ie5 && !window.opera? iecompattest().scrollTop : window.pageYOffset
var windowedge=ie5 && !window.opera? iecompattest().scrollTop+iecompattest().clientHeight-15 : window.pageYOffset+window.innerHeight-18
dropmenuobj.contentmeasure=dropmenuobj.offsetHeight
if (windowedge-dropmenuobj.y < dropmenuobj.contentmeasure){ //move up?
edgeoffset=dropmenuobj.contentmeasure+obj.offsetHeight
if ((dropmenuobj.y-topedge)<dropmenuobj.contentmeasure) //up no good either?
edgeoffset=dropmenuobj.y+obj.offsetHeight-topedge
}
}
return edgeoffset
}

function dropdownmenu(obj, e, dropmenuID){
if (window.event) event.cancelBubble=true
else if (e.stopPropagation) e.stopPropagation()
if (typeof dropmenuobj!="undefined") //hide previous menu
dropmenuobj.style.visibility="hidden"
clearhidemenu()
if (ie5||ns6){
obj.onmouseout=delayhidemenu
dropmenuobj=document.getElementById(dropmenuID)
if (hidemenu_onclick) dropmenuobj.onclick=function(){dropmenuobj.style.visibility='hidden'}
dropmenuobj.onmouseover=clearhidemenu
dropmenuobj.onmouseout=ie5? function(){ dynamichide(event)} : function(event){ dynamichide(event)}
showhide(dropmenuobj.style, e, "visible", "hidden")
dropmenuobj.x=getposOffset(obj, "left")
dropmenuobj.y=getposOffset(obj, "top")
dropmenuobj.style.left=dropmenuobj.x-clearbrowseredge(obj, "rightedge")+"px"
dropmenuobj.style.top=dropmenuobj.y-clearbrowseredge(obj, "bottomedge")+obj.offsetHeight+"px"
}
return clickreturnvalue()
}

function clickreturnvalue(){
if ((ie5||ns6) && !enableanchorlink) return false
else return true
}

function contains_ns6(a, b) {
while (b.parentNode)
if ((b = b.parentNode) == a)
return true;
return false;
}

function dynamichide(e){
if (ie5&&!dropmenuobj.contains(e.toElement))
delayhidemenu()
else if (ns6&&e.currentTarget!= e.relatedTarget&& !contains_ns6(e.currentTarget, e.relatedTarget))
delayhidemenu()
}

function delayhidemenu(){
delayhide=setTimeout("dropmenuobj.style.visibility='hidden'",disappeardelay)
}

function clearhidemenu(){
if (typeof delayhide!="undefined")
clearTimeout(delayhide)
}



