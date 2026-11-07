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

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.core.domains.models.X_AD_MigrationData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Represents PO column data for a migration step.
 *
 * @author Carlos Parada, cparada@erpya.com, ERPyA https://erpya.com
 */
public class MMigrationData extends X_AD_MigrationData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3597076848116587021L;

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
			else {
				MigrationUUIDResolver.Reference reference = MigrationUUIDResolver.decode(getOldValue());
				if (reference != null) {
					data.setAttribute("oldReferenceTable", reference.getTableName());
					data.setAttribute("oldReferenceUUID", reference.getUUID());
				} else
					data.setAttribute("oldValue", getOldValue());
			}
		}
		if ( isNewNull() || getNewValue() == null )
			data.setAttribute("isNewNull", "true");
		else {
			MigrationUUIDResolver.Reference reference = MigrationUUIDResolver.decode(getNewValue());
			if (reference != null) {
				data.setAttribute("referenceTable", reference.getTableName());
				data.setAttribute("referenceUUID", reference.getUUID());
			} else
				data.appendChild(document.createTextNode(getNewValue()));
		}
		
		return data;
	}

	/*
	 * Create MMigration data from an xml <Data/> node
	 */
	public static void fromXmlNode(MMigrationStep parent, Node item) {
		MMigrationData data = new MMigrationData(parent);
		Element element = (Element) item;
		data.setIsOldNull("true".equals(element.getAttribute("isOldNull")));
		if (!element.getAttribute("oldReferenceUUID").isEmpty())
			data.setOldValue("@UUID@" + element.getAttribute("oldReferenceTable") + ":"
					+ element.getAttribute("oldReferenceUUID"));
		else
			data.setOldValue(element.getAttribute("oldValue"));
		MTable table = MTable.get(parent.getCtx(), parent.getAD_Table_ID());
		int columnId = table == null ? 0 : MColumn.getColumn_ID(table.getTableName(),
				element.getAttribute("Column"));
		if (columnId <= 0) {
			int sourceColumnId = Integer.parseInt(element.getAttribute("AD_Column_ID"));
			MColumn sourceColumn = MColumn.get(parent.getCtx(), sourceColumnId);
			if (sourceColumn == null || table == null
					|| sourceColumn.getAD_Table_ID() != table.getAD_Table_ID()
					|| !element.getAttribute("Column").equals(sourceColumn.getColumnName()))
				throw new IllegalStateException("Cannot resolve migration column "
						+ element.getAttribute("Column") + " in "
						+ (table == null ? "unknown table" : table.getTableName()));
			columnId = sourceColumnId;
		}
		data.setAD_Column_ID(columnId);
		data.setIsNewNull("true".equals(element.getAttribute("isNewNull")));
		if (!element.getAttribute("referenceUUID").isEmpty())
			data.setNewValue("@UUID@" + element.getAttribute("referenceTable") + ":"
					+ element.getAttribute("referenceUUID"));
		else
			data.setNewValue(element.getTextContent());
		data.saveEx();
	}

	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		if (this.getAD_Client_ID() > 0)
			this.setAD_Client_ID(0); // Migrations are always owned by System
		if (this.getAD_Org_ID() > 0)
			this.setAD_Org_ID(0);
		return true;
	}	//	beforeSave

}
