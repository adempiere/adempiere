package org.adempiere.pipo;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.adempiere.core.domains.models.I_AD_Element;
import org.compiere.model.PO;
import org.xml.sax.helpers.AttributesImpl;

public class AttributeFiller {

	private AttributesImpl atts = null;
	private PO po = null;
	
	/**
	 * @param attributes
	 */
	public AttributeFiller(AttributesImpl attributes){
		atts = attributes;
		po = null;
	}
	
	/**
	 * @param attributes
	 * @param poToAutoFill
	 */
	public AttributeFiller(AttributesImpl attributes, PO poToAutoFill){
		atts = attributes;
		po = poToAutoFill;
	}
	
	/**
	 * Clean values
	 */
	public void cleanValues() {
		if(atts != null) {
			atts.clear();
		}
		po = null;
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
	 * Add int value
	 * @param name
	 * @param value
	 */
	public void addInt(String name, int value){
		atts.addAttribute("", "", name, "CDATA",  String.valueOf(value));
	}
	
	/**
	 * Add int value
	 * @param name
	 * @param value
	 */
	public void addLong(String name, long value){
		atts.addAttribute("", "", name, "CDATA",  String.valueOf(value));
	}
	
	/**
	 * Add Last Updated
	 */
	public void addLastUpdatedTime() {
		long time = 0;
		if(po.getUpdated() != null) {
			time = po.getUpdated().getTime();
		}
		addLong(getLastUpdatedTimeAttribute(), time);
	}
	
	/**
	 * Get UUID Attribute from name
	 * @param name
	 * @return
	 */
	public static String getUUIDAttribute(String name) {
		return (name + I_AD_Element.COLUMNNAME_UUID);
	}
	
	/**
	 * get Last Updated Time Attribute
	 * @return
	 */
	public static String getLastUpdatedTimeAttribute() {
		return "Last_" + I_AD_Element.COLUMNNAME_Updated;
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
	public void add(String columnName) {
		add(columnName, false);
	}
	
	/**
	 * Add UUID for record
	 */
	public void addUUID() {
		addString(getUUIDAttribute(po.get_TableName()), po.get_ValueAsString(I_AD_Element.COLUMNNAME_UUID));
	}
	
	/**
	 * Add UUID from reference
	 * @param name
	 * @param uuid
	 */
	public void addUUID(String name, String uuid) {
		addString(getUUIDAttribute(name), uuid);
	}
	
	/**
	 * Validate also ID
	 * @param columnName
	 * @param onlyValidId
	 */
	public void add(String columnName, boolean onlyValidId) {
		Object value = po.get_Value(columnName);
		if(value == null){
			atts.addAttribute("", "", columnName, "CDATA", "");
			return;
		}
		if(value instanceof String) {
			atts.addAttribute("", "", columnName, "CDATA", (String)value);	
		} else if(value instanceof Boolean) {
			atts.addAttribute("", "", columnName, "CDATA",  (Boolean)value == true ? "true" : "false");
			
		} else if(value instanceof Integer) {
			if(onlyValidId) {
				int intValue = Integer.parseInt(value.toString());
				if(intValue > 0) {
					atts.addAttribute("", "", columnName, "CDATA",  value.toString());
				} else {
					atts.addAttribute("", "", columnName, "CDATA", "");
				}
			} else {
				atts.addAttribute("", "", columnName, "CDATA",  value.toString());
			}
		} else if(value instanceof BigDecimal) { 
			atts.addAttribute("", "", columnName, "CDATA",  value.toString());
		} else if(value instanceof Timestamp) {
			atts.addAttribute("", "", columnName, "CDATA",  String.valueOf(((Timestamp)value).getTime()));
		} else {
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
	public AttributesImpl getAttributes() {
		return atts;
	}
}
