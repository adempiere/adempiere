/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.									  *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.spin.grid;

import java.util.Properties;

import org.compiere.model.GridField;
import org.eevolution.grid.BrowserCallOutEngine;
import org.eevolution.grid.BrowserRow;


/** 
 * 	Callout for set and unset all rows of browser
 *  @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class BrowseAxisColumnSelection extends BrowserCallOutEngine {

	/**
	 * 
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
	public String selectAllRows(Properties ctx,  int WindowNo, BrowserRow row, GridField field, Object value, Object oldValue, int currentRow, int currentColumn) {
		row.getBrowserFields()
			.entrySet()
			.stream()
			.filter(fieldToFind -> fieldToFind.getValue().getAxis_Column_ID() != 0)
			.forEach(fieldToSet -> {
				row.setValue(fieldToSet.getValue().getAD_View_Column().getColumnName(), field.getValue());
			});
		return "";
	}
}
