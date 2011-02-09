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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.util.DisplayType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/*
 * Represents PO column data for a migration step
 */
public class MMigrationData extends X_AD_MigrationData {

	public MMigrationData(Properties ctx, int AD_MigrationData_ID,
			String trxName) {
		super(ctx, AD_MigrationData_ID, trxName);
	}

	public MMigrationData(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public MMigrationData(MMigrationStep parent) {
		this(parent.getCtx(), 0, parent.get_TrxName());
		setAD_MigrationStep_ID(parent.getAD_MigrationStep_ID());
	}

	public Node toXmlNode(MMigrationStep parent, Document document) {
		MColumn column = (MColumn) getAD_Column();
		Element data = document.createElement("Data");
		data.setAttribute("Column", column.getColumnName());
		data.setAttribute("AD_Column_ID", Integer.toString(getAD_Column_ID()));
		if ( !parent.getAction().equals(MMigrationStep.ACTION_Insert) )
		{
			if ( isOldNull() )
				data.setAttribute("isOldNull", "true");
			else
				data.setAttribute("oldValue", getOldValue());
		}
		if ( isNewNull() || getNewValue() == null )
			data.setAttribute("isNewNull", "true");
		else
			data.appendChild(document.createTextNode(getNewValue()));
		
		return data;
	}

	/*
	 * Create MMigration data from an xml <Data/> node
	 */
	public static void fromXmlNode(MMigrationStep parent, Node item) {
		MMigrationData data = new MMigrationData(parent);
		Element element = (Element) item;
		data.setIsOldNull("true".equals(element.getAttribute("isOldNull")));
		data.setOldValue(element.getAttribute("oldValue"));
		data.setAD_Column_ID(Integer.parseInt(element.getAttribute("AD_Column_ID")));
		data.setIsNewNull("true".equals(element.getAttribute("isNewNull")));
		data.setNewValue(element.getTextContent());
		data.saveEx();
	}

}
