package org.adempiere.pipo;

import org.compiere.model.PO;
import org.xml.sax.helpers.AttributesImpl;

public class AttributeFiller {

	private AttributesImpl atts = null;
	private PO po = null;
	
	/**
	 * Will clear attributes !!!
	 * @param _atts
	 */
	public AttributeFiller(AttributesImpl attributes){
		attributes.clear();
		atts = attributes;
		po = null;
	}
	
	/**
	 * Will clear attributes !!!
	 * @param _atts
	 */
	public AttributeFiller(AttributesImpl attributes, PO poToAutoFill){
		attributes.clear();
		atts = attributes;
		
		po = poToAutoFill;
	}
	
	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void addUnchecked(String name, String value){

		atts.addAttribute("", "", name, "CDATA", value);
	}

	/**
	 * 
	 * @param name
	 * @param stringValue
	 */
	public void addString(String name, String stringValue){

		atts.addAttribute("", "", name, "CDATA", stringValue != null ? stringValue : "");

	}

	/**
	 * 
	 * @param name
	 * @param boolValue
	 */
	public void addBoolean(String name, boolean boolValue){

		atts.addAttribute("", "", name, "CDATA", boolValue == true ? "true" : "false");
	}
	
	
	/**
	 * 
	 * @param name
	 * @param stringValue
	 */
	public void add(String columnName){
		
		Object value = po.get_Value(columnName);
		
		if(value == null){
			
			atts.addAttribute("", "", columnName, "CDATA", "");
			return;
		}
		
		if(value instanceof String){
			atts.addAttribute("", "", columnName, "CDATA", (String)value);
			
		}else if(value instanceof Boolean) {
			atts.addAttribute("", "", columnName, "CDATA",  (Boolean)value == true ? "true" : "false");
			
		}else if(value instanceof Integer) {
			atts.addAttribute("", "", columnName, "CDATA",  value.toString());
			
		}else{
			
			throw new IllegalArgumentException("Add your own type implementation here.");
		}
	}
	
	
	/**
	 * 
	 *
	 */
	public void addIsActive(){
		
		atts.addAttribute("", "", "IsActive", "CDATA",  (Boolean)po.isActive() == true ? "true" : "false");
	}

	/**
	 * 
	 * @return
	 */
	public AttributesImpl getAttributes(){

		return atts;
	}
}
