function ad_deferRenderBorderLayout(uuid, timeout) {
	var meta = zkau.getMeta($e(uuid));
	if (meta) {
		zk.setVisible(zkau.getMeta($e(uuid)).el, false, false);
		setTimeout("_ad_deferBDL('"+uuid+"')", timeout);				
	}
}		

function _ad_deferBDL(uuid) {			
	zk.setVisible(zkau.getMeta($e(uuid)).el, true, false);
	zk.beforeSizeAt();
	zk.onSizeAt();
	zkau.getMeta($e(uuid)).render();
}
