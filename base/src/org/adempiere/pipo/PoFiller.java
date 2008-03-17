package org.adempiere.pipo;

import org.compiere.model.PO;
import org.xml.sax.Attributes;


public class PoFiller{

	PO po = null;
	Attributes atts = null;
	
	public PoFiller(PO po, Attributes atts){
		
		this.po = po;
		
		this.atts = atts;
	}
	
	public void setString(String columnName){
		
		String value = atts.getValue(columnName);
	    
		value = "".equals(value) ? null : value;
		
		po.set_ValueOfColumn(columnName, value);
	}
	
	public void setBoolean(String columnName){
		
		String value = atts.getValue(columnName);
	    
		boolean bool = "true".equals(value) ? true : false;
		
		po.set_ValueOfColumn(columnName, bool);
	}
}
