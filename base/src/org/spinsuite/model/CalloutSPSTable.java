/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2013 E.R.P. Consultores y Asociados.                    *
 * All Rights Reserved.                                                       *
 * Contributor(s): Dixon Martinez www.erpconsultoresyasociados.com            *
 *****************************************************************************/
package org.spinsuite.model;

import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MColumn;
import org.compiere.model.MTable;

/**
 * @author Dixon Martinez
 *
 */
public class CalloutSPSTable extends CalloutEngine {

	/**
	 * 
	 * @author Dixon Martinez 08/02/2013, 16:27:59
	 * @author <a href="mailto:yamelsenih@gmail.com">Yamel Senih</a> 11/02/2013, 23:49:08
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * @return String
	 */
	public String copyAttFromTable (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value){
		if (isCalloutActive() || value == null)
			return "";
		Integer m_AD_Table_ID = (Integer) value;
		MTable mTable = new MTable(ctx, m_AD_Table_ID, null);
		mTab.setValue("TableName", mTable.getTableName());
		mTab.setValue("Name", mTable.getName());
		mTab.setValue("Description", mTable.getDescription());
		mTab.setValue("IsView", mTable.isView());
		mTab.setValue("IsDeleteable", mTable.isDeleteable());
		mTab.setValue("AccessLevel", mTable.getAccessLevel());
		
		return "";
	}
	
	/**
	 * 
	 * @author Dixon Martinez 08/02/2013, 16:28:04
	 * @author <a href="mailto:yamelsenih@gmail.com">Yamel Senih</a> 11/02/2013, 23:49:08
	 * <li> Add IsKey and IsParent Set Values
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * @return String
	 */
	
	public String copyAttFromColumn (Properties ctx, int IsAlwaysUpdateable, GridTab mTab, GridField mField, Object value){
		if (isCalloutActive() || value == null)
			return "";
		Integer m_Column_ID = (Integer) value;
		MColumn mColumn = new MColumn(ctx, m_Column_ID, null);
		mTab.setValue("AD_Element_ID", mColumn.getAD_Element_ID());
		mTab.setValue("ColumnName", mColumn.getColumnName());
		mTab.setValue("ColumnSQL", mColumn.getColumnSQL());
		mTab.setValue("Description", mColumn.getDescription());
		mTab.setValue("AD_Reference_ID", mColumn.getAD_Reference_ID());
		mTab.setValue("AD_Reference_Value_ID", mColumn.getAD_Reference_Value_ID());
		mTab.setValue("AD_Val_Rule_ID", mColumn.getAD_Val_Rule_ID());
		mTab.setValue("Callout", mColumn.getCallout());		
		mTab.setValue("DefaultValue", mColumn.getDefaultValue());
		mTab.setValue("FieldLength", mColumn.getFieldLength());
		mTab.setValue("IsAlwaysUpdateable", mColumn.isAlwaysUpdateable());
		mTab.setValue("IsEncrypted", mColumn.isEncrypted());
		mTab.setValue("IsIdentifier", mColumn.isIdentifier());
		mTab.setValue("IsMandatory", mColumn.isMandatory());
		mTab.setValue("IsSelectionColumn", mColumn.isSelectionColumn());		
		mTab.setValue("IsKey", mColumn.isKey());
		mTab.setValue("IsParent", mColumn.isParent());
		mTab.setValue("IsUpdateable", mColumn.isUpdateable());
		mTab.setValue("Name", mColumn.getName());
		mTab.setValue("SeqNo", mColumn.getSeqNo());
		mTab.setValue("ValueMax", mColumn.getValueMax());
		mTab.setValue("ValueMin", mColumn.getValueMin());
		
		
		return "";
	}
	
	

}
