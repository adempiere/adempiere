<input type="text" style="width:100%" id="customerQuery" accesskey="s"/>
<div id="customerSearchResult" class="autocomplete"></div>
<script>						
	new Ajax.Autocompleter('customerQuery','customerSearchResult','SearchVendorAction.do',{
	paramName:'customerQuery',
	frequency:TROTTLE_TIME,
	afterUpdateElement:function(e1,e2){
						var bpartner = document.getElementsByName('bpartnerId')[0];
						var vendorName = document.getElementsByName('partnerName')[0];
						if(e2.value != '-1'){
							bpartner.value = e2.value;
							vendorName.value = e2.getAttribute('name');
						}else{
							bpartner.value = "";
							vendorName.value = "";
						}//if
																																							
						}//function												
	});
</script>