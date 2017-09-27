package org.compiere.model;

import java.util.Properties;

/**
 * Set Get Object interface
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 */
public interface SetGetModel
{
	public Properties getCtx();
	public String get_TrxName();
	//
	public int get_Table_ID();
	public String get_TableName();
	//
	public boolean set_AttrValue(String name, Object value);
	public Object get_AttrValue(String name);
	public boolean is_AttrValueChanged(String ColumnName);
}
