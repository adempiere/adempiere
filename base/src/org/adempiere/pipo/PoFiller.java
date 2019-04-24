/*************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                              *
 * This program is free software; you can redistribute it and/or modify it    		 *
 * under the terms version 2 or later of the GNU General Public License as published *
 * by the Free Software Foundation. This program is distributed in the hope   		 *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 		 *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           		 *
 * See the GNU General Public License for more details.                       		 *
 * You should have received a copy of the GNU General Public License along    		 *
 * with this program; if not, write to the Free Software Foundation, Inc.,    		 *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     		 *
 * For the text or an alternative of this public license, you may reach us    		 *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved. *
 * Contributor(s): Yamel Senih www.erpya.com				  		                 *
 *************************************************************************************/
package org.adempiere.pipo;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.Util;
import org.xml.sax.Attributes;

/**
 * Filler for PO from attributes
 * @author Original Gen Sing Low
 *
 */
public class PoFiller{

	/**	PO Entity	*/
	private PO entity = null;
	private Attributes atts = null;
	private POInfo poInfo = null;
	
	/**
	 * standard constructor
	 * @param entity
	 * @param atts
	 */
	public PoFiller(PO entity, Attributes atts){
		this.entity = entity;
		this.atts = atts;
		poInfo = POInfo.getPOInfo(entity.getCtx(), entity.get_Table_ID(), entity.get_TrxName());
	}
	
	/**
	 * get String
	 * @param columnName
	 */
	public void setString(String columnName){
		String value = atts.getValue(columnName);
		value = "".equals(value) ? null : value;
		entity.set_ValueOfColumn(columnName, value);
	}
	
	/**
	 * Set a boolean
	 * @param columnName
	 */
	public void setBoolean(String columnName){
		String value = atts.getValue(columnName);
		boolean bool = "true".equals(value) ? true : false;
		entity.set_ValueOfColumn(columnName, bool);
	}
	
	/**
	 * Set value from attribute
	 * @param columnName
	 */
	public void setAttribute(String columnName) {
		String value = atts.getValue(columnName);
		String originalValue = value;
		if (Util.isEmpty(value, true)) {
			value = null;
		} else {
			value = value.trim();
		}
		int index = poInfo.getColumnIndex(columnName);
		boolean isMandatory = poInfo.isColumnMandatory(index);
		boolean isKeyColumn = poInfo.isKey(index);
		if(isMandatory
				&& Util.isEmpty(value)) {
			if(!isKeyColumn) {
				entity.set_ValueOfColumn(columnName, entity.getDefaultValue(index));
			}
			return;
		}
		//	
		Class<?> clazz = poInfo.getColumnClass(index);
		if (value == null) {
			entity.set_ValueOfColumn(columnName, null);
		} else if (clazz == BigDecimal.class) {
			entity.set_ValueOfColumn(columnName, new BigDecimal(value));
		} else if (clazz == Integer.class) {
			entity.set_ValueOfColumn(columnName, new BigDecimal(value).intValueExact());
		} else if (clazz == String.class) {
			entity.set_ValueOfColumn(columnName, originalValue);
		} else if (clazz == Boolean.class) {
			entity.set_ValueOfColumn(columnName, Boolean.valueOf(value));
		} else if (clazz == Timestamp.class) {
			Timestamp ts = Timestamp.valueOf(value);
			entity.set_ValueOfColumn(columnName, ts);
		} else {
			throw new AdempiereException("Class not supported - " + clazz);
		}
	}
	
	@Override
	public String toString() {
		return "PO = " + entity +
				", Attributes = " + atts;
	}
}
