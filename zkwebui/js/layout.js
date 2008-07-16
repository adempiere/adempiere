function ad_deferRenderBorderLayout(uuid, timeout) {
	var meta = zkau.getMeta($e(uuid));
	if (meta) {
		setTimeout("_ad_deferBDL('"+uuid+"')", timeout);				
	}
}		

function _ad_deferBDL(uuid) {			
	zk.beforeSizeAt();
	zk.onSizeAt();
	zkau.getMeta($e(uuid)).render();
}
