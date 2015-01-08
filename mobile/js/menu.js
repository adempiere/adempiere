var isIE = (navigator.userAgent.indexOf("MSIE") != -1);
						
/****************************************************************************
 * Compiere (c) Jorg Janke - All rights reseverd
 * $Id: menu.js,v 1.1 2009/04/15 11:27:37 vinhpt Exp $
 *
 * Menu - show/hide sub-menues - tested in IE 6 end Mozila 1.7
 ***************************************************************************/
function changeMenu (evt) 
{
	evt = (evt) ? evt : (window.event) ? window.event : "";
	//	get LI tag
	var liTag;
	if (isIE)
		liTag = evt.srcElement;
	else
		liTag = evt.target;
	evt.cancelBubble = true;
		
	if (liTag.nodeName != 'LI')
		return;
	//	get nested UL tag (first child is the text)
	var ulTag = liTag.firstChild.nextSibling;
	/**
	alert ('event=' + evt 
		+ '\n - li=' + liTag.nodeName + "-" + liTag.id
	//	+ '\n - sibling=' + liTag.nextSibling + "-" + liTag.nextSibling.nodeName + "--" + liTag.nextSibling.id
	//	+ '\n - child=' + liTag.firstChild + "-" + liTag.firstChild.nodeName + "--" + liTag.firstChild.id
	//	+ '\n - child-sibling=' + liTag.firstChild.nextSibling + "-" + liTag.firstChild.nextSibling.nodeName + "--" + liTag.firstChild.nextSibling.id
		+ '\n - ul=' + ulTag.nodeName + "-" + ulTag.id
		);
	/* **/
	if (!ulTag || ulTag.nodeName != 'UL')
		return;

	if (ulTag.style.display=="none") 
	{
		ulTag.style.display="";
		liTag.style.listStyleImage="url(/adempiere/images/mOpen.gif)";	
	} 
	else
	{
		ulTag.style.display="none";
		liTag.style.listStyleImage="url(/adempiere/images/mClosed.gif)";	
	}
	
}	// 	changeMenu

var oldItem=null;
var flag=true;

// Expands enough nodes to expose an LI with a given ID
function searchMenu(treeId,str,evt,target) {
	if (evt.keyCode==13||evt.keyCode==10) {
		if ((evt.ctrlKey||evt.altKey||evt.shiftKey) && oldItem!=null) {
			window.open(oldItem.firstChild.href,target);
		} else {
			var ul = document.getElementById(treeId);
			if (ul != null) {
				deselectAll();
				var o = expandCollapseList(ul,str);
				if (o!=null) {
					if (o.scrollIntoView) o.scrollIntoView(false);
					selectItem(o.firstChild);
					oldItem = o;
				}
			}
		}
	}
}

// Performs 3 functions:
// a) Expand all nodes
// b) Collapse all nodes
// c) Expand all nodes to reach a certain ID
function expandCollapseList(ul,str) {
	if (!ul.childNodes || ul.childNodes.length==0) return null;
	// Iterate LIs
	for (var itemi=0;itemi<ul.childNodes.length;itemi++) {
		var item = ul.childNodes[itemi];
		if (item != null && item.firstChild != null) {
			var fitem = item.firstChild;
			if (flag && fitem.nodeName == "A" && fitem.innerHTML.match(str) != null) {
				flag = false;
				return item;
			}
			if (item == oldItem) flag=true;
		}
		if (item.nodeName == "LI") {
			// Iterate things in this LI
			for (var sitemi=0;sitemi<item.childNodes.length;sitemi++) {
				var sitem = item.childNodes[sitemi];
				if (sitem.nodeName=="UL") {
					var o = expandCollapseList(sitem,str);
					if (o!=null) {
						sitem.style.display="";
						item.style.listStyleImage="url(/adempiere/images/mOpen.gif)";
						return o;
					}
				}
			}
		}
	}
}

function selectItem(obj)
{
   if (document.selection) 
   {
      var range = document.body.createTextRange();
      range.moveToElementText(obj);
      range.select();
   }
   else if (window.getSelection) 
   {
      var range = document.createRange();
      range.selectNode(obj);
      window.getSelection().addRange(range);
   }
}

function deselectAll() 
{
   if (document.selection)
             document.selection.empty();
   else if (window.getSelection)
              window.getSelection().removeAllRanges();
} 