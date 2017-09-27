package org.compiere.model;

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
 * Copyright (C) 2003-2015 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpcya.com                                 *
 *****************************************************************************/
import java.math.BigDecimal;
import java.util.Properties;

import org.compiere.util.Env;
import org.eevolution.grid.BrowserCallOutEngine;
import org.eevolution.grid.BrowserRow;

/**
 * 	Create From Callout
 * 	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 327 ] Create From in M_InOut change to Smart Browse
 *		@see https://github.com/adempiere/adempiere/issues/327
 */
public class BrowserCalloutCreateFrom extends BrowserCallOutEngine {

	/**
	 * Change movement quantity from quantity and UOM
	 * @param ctx
	 * @param WindowNo
	 * @param row
	 * @param field
	 * @param value
	 * @param oldValue
	 * @param currentRow
	 * @param currentColumn
	 * @return
	 */
	public String quantity(Properties ctx,  int WindowNo, BrowserRow row, 
			GridField field, Object value, Object oldValue,int currentRow, int currentColumn) {
		//	Get Values
		Integer productId = (Integer) row.getValue("CF_M_Product_ID");
		Integer uomId = (Integer) row.getValue("CF_C_UOM_ID");
		BigDecimal quantity = (BigDecimal) row.getValue("CF_QtyEntered");
		//	Validate
		if(productId == null
				|| productId.intValue() == 0
				|| uomId == null
				|| uomId.intValue() == 0
				|| quantity == null)
			return "";
		//	Convert
		BigDecimal movementQuantity = MUOMConversion.convertProductFrom(ctx, productId, uomId, quantity);
		//	Set Default
		if(movementQuantity == null)
			movementQuantity = Env.ZERO;
		//	Set Value
		row.setValue("CF_MovementQty", movementQuantity);
		return "";
	}
}
