function ad_deferRenderBorderLayout(uuid, timeout) {
	var meta = zk.Widget.$(uuid);
	if (meta) {
		setTimeout("_ad_deferBDL('"+uuid+"')", timeout);				
	}
}		

function _ad_deferBDL(uuid) {			
	var cmp = zk.Widget.$(uuid);
	if (cmp) { cmp.resize(); }	
}

function _ad_closeBuble(link) {
	var parent = link.parentsUntil("simileAjax-bubble-contentContainer");
	var btn = parent.next();
	btn.click();
}

function scrollToRow(uuid){  
	 var cmp = zk.Widget.$(uuid);  
	 if (cmp) {
	    cmp.scrollIntoView();
	 	cmp.focus();
	 }
}
