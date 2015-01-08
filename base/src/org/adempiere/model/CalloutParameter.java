/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2003-2011 e-Evolution Consultants. All Rights Reserved.      *
 * Copyright (C) 2003-2011 Victor Pérez Juárez 								  * 
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
 * Contributor(s): Victor Pérez Juárez  (victor.perez@e-evolution.com)		  *
 * Sponsors: e-Evolution Consultants (http://www.e-evolution.com/)            *
 *****************************************************************************/
package org.adempiere.model;

import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.I_AD_Process_Para;
import org.compiere.model.MProcess;
import org.compiere.model.MTable;
import org.compiere.model.M_Element;
import org.compiere.util.DisplayType;

/**
 * CalloutParameter Callout
 * 
 * @author Victor Perez www.e-evolution.com
 *  <li>FR [ 3426134 ] Add the Reference ,FieldLength, Reference Value
 * 	https://sourceforge.net/tracker/?func=detail&aid=3426134&group_id=176962&atid=879335
 */
public class CalloutParameter extends CalloutEngine {
	/**
	 * Set the default values from Element
	 * 
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 */
	public String element(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {
		Integer AD_Element_ID = (Integer) value;
		if (AD_Element_ID == null || AD_Element_ID <= 0)
			return "";
		I_AD_Process_Para para = GridTabWrapper.create(mTab,
				I_AD_Process_Para.class);
		MTable table = MTable.get(ctx, para.Table_ID);
		MProcess process = MProcess.get(ctx, para.getAD_Process_ID());
		para.setEntityType(process.getEntityType());
		M_Element element = new M_Element(ctx, AD_Element_ID, null);
		if (element.getAD_Reference_ID() == DisplayType.ID) {
			String columnName = table.get_TableName() + "_ID";
			if (!columnName.equals(element.getColumnName())) {
				para.setAD_Reference_ID(DisplayType.TableDir);
			}
		}

		if (para.getColumnName() == null || para.getColumnName().length() <= 0)
			para.setColumnName(element.getColumnName());
		if (para.getFieldLength() <= 0)
			para.setFieldLength(element.getFieldLength());
		if (para.getAD_Reference_ID() <= 0)
			para.setAD_Reference_ID(element.getAD_Reference_ID());
		if (para.getAD_Reference_Value_ID() <= 0)
			para.setAD_Reference_Value_ID(element.getAD_Reference_Value_ID());
		if (para.getName() == null || para.getName().length() <= 0)
			para.setName(element.getName());
		if (para.getDescription() == null
				|| para.getDescription().length() <= 0)
			para.setDescription(element.getDescription());
		if (para.getHelp() == null || para.getHelp().length() <= 0)
			para.setHelp(element.getHelp());
		return "";
	}
}
