package org.adempiere.webui.component;

public class ListboxFactory {

	public static Listbox newDropdownListbox() {
		Listbox listbox = new Listbox();
		listbox.setMold("select");
		return listbox;
	}
	
	public static WListbox newDataTable() {
		WListbox dataTable = new WListbox();
		dataTable.setWidth("100%");
		dataTable.setHeight("100%");
		dataTable.setFixedLayout(true);
		dataTable.setVflex(true);
		
		return dataTable;
	}
}
