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
import org.compiere.model.I_AD_Column;
import org.compiere.model.MColumn;

/**
 * CalloutColumn Callout
 * 
 * @author Victor Perez www.e-evolution.com
 *  <li>FR [ 3426134 ] Add the Reference ,FieldLength, Reference Value
 * 	https://sourceforge.net/tracker/?func=detail&aid=3426134&group_id=176962&atid=879335
 */
public class CalloutColumn extends CalloutEngine {
	public String element(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {
		Integer AD_Element_ID = (Integer) value;
		if (AD_Element_ID == null || AD_Element_ID <= 0)
			return "";
		I_AD_Column column = GridTabWrapper.create(mTab, I_AD_Column.class);
		MColumn.setAD_Column(ctx, column, null);
		return "";
	}
}
