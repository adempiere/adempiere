/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.util.Optional;
import java.util.Properties;

/**
 *	Document Type Callouts
 *	
 *  @author Carlos Parada
 *  Callout for document Type Window
 */
public class CalloutDocType extends CalloutEngine
{
	/**
	 * Set Document Base Type
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 */
	public String docBaseType (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		String docBaseType = "";
		Integer C_DocBaseType_ID = (Integer)value;
		if (C_DocBaseType_ID != null 
				&& C_DocBaseType_ID.intValue() > 0)
			docBaseType = Optional.ofNullable(MDocBaseType.get(C_DocBaseType_ID).getDocBaseType()).orElse("");

		mTab.setValue(MDocType.COLUMNNAME_DocBaseType, docBaseType);
		return "";
	}	//	docBaseType

	
}	//	CalloutDocType
