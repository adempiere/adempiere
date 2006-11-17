/****************************************************************************
 * Adempiere (c) Jorg Janke - All rights reseverd
 * $Id: window.js,v 1.1 2006/04/21 18:03:35 jjanke Exp $
 *
 * Web UI Window Utilities
 ***************************************************************************/

/****************************************************************************
 *	Text constants
 */
var deleteText = "ConfirmDelete";


/****************************************************************************
 *	Field Update
 ***************************************************************************/
function fieldUpdate(e)
{
	if (!top.WCmd)	//	no cmd frame
		return;

	if (!e) e = window.event;
	//	alert('FieldUpdate ' + e.name + '=' + e.value);

	//	update info and submit
	top.WCmd.document.fieldUpdate.formName.value = e.form.name; //e.document.forms[0].name;
	top.WCmd.document.fieldUpdate.fieldName.value = e.name;
	top.WCmd.document.fieldUpdate.fieldValue.value = e.value;
	top.WCmd.document.fieldUpdate.submit();
}	//	fieldUpdate

/**
 *	Create Initial Command Window
 */
function createWCmd()
{
	if (!top.WCmd)	//	no cmd frame
		return;
	// write to the command window.
	var d = top.WCmd.document;
	d.open();
	d.writeln('<form name="fieldUpdate" method="post" action="/adempiere/WFieldUpdate">');
	d.writeln('<input type="hidden" name="formName" value="x">');
	d.writeln('<input type="hidden" name="fieldName" value="x">');
	d.writeln('<input type="hidden" name="fieldValue" value="x">');
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
function startPopup (targetCmd)
{
    parent.document.getElementById("framesetWindow").rows="300,*";
    parent.WPopUp.location = '/adempiere/' + targetCmd;
    return false;   //  do not submit page
}   //  startPopup

/**
 *  Close PopUp
 */
function closePopup ()
{
    parent.document.getElementById("framesetWindow").rows="0,*";
    return true;   //  do submit page
}   //  closePopUp

/**
 *	Lookup - get FormName and ColumnName and submit to WLookup
 */
function startLookup (columnName)
{
	var url = "WLookup?ColumnName=" + columnName;
//	alert (url);
	return startPopup(url);
}	//	startLookup

/**
 *	Account - get FormName and ColumnName and submit to WAccount
 */
function startAccount (columnName)
{
	var url = "WAccount?ColumnName=" + columnName;
//	alert (url);
	return startPopup(url);
}	//	startAccount

/**
 *	Location - get FormName and ColumnName and submit to WLocation
 */
function startLocation (columnName)
{
	var url = "WLocation?ColumnName=" + columnName;
//	alert (url);
	return startPopup(url);
}	//	startLocation


/****************************************************************************
 *	Field Updated - submit
 */
function startUpdate (column)
{
	column.form.ChangedColumn.value=column.name;
    column.form.submit();
}	//	startUpdate

/****************************************************************************
 *	Process Button
 */
function startButton (column)
{
	column.form.ChangedColumn.value=column.name;
    column.form.submit();
}	//	startButton
