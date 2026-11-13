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
import java.sql.Timestamp;

import org.adempiere.core.domains.models.I_AD_Element;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Util;

/**
 * Builds the portable SQL fragments used only by migration script logging.
 *
 * @author Carlos Parada, cparada@erpya.com, https://erpya.com
 */
final class MigrationSQLBuilder {

	private final PO po;
	private final POInfo info;
	private final boolean enabled;

	MigrationSQLBuilder(PO po, POInfo info) {
		this.po = po;
		this.info = info;
		this.enabled = PO.isLogMigrationScript(po != null ? po.getCtx() : null);
	}

	boolean isCustomPrimaryKey(int index, Object value) {
		String[] keys = po.get_KeyColumns();
		return keys.length == 1 && keys[0].endsWith("_ID")
				&& keys[0].equals(info.getColumnName(index))
				&& info.getColumnIndex(I_AD_Element.COLUMNNAME_UUID) >= 0
				&& !Util.isEmpty(po.get_ValueAsString(I_AD_Element.COLUMNNAME_UUID))
				&& value instanceof Integer
				&& MigrationUUIDResolver.isCustomId(((Integer)value).intValue());
	}

	String getWhereClause() {
		if (info.getColumnIndex(I_AD_Element.COLUMNNAME_UUID) < 0)
			return po.get_WhereClause(true);
		Object oldUUID = po.get_ValueOld(I_AD_Element.COLUMNNAME_UUID);
		String uuid = oldUUID == null ? po.get_ValueAsString(I_AD_Element.COLUMNNAME_UUID)
				: oldUUID.toString();
		String[] keys = po.get_KeyColumns();
		if (keys.length == 1 && keys[0].endsWith("_ID")
				&& MigrationUUIDResolver.isCustomId(po.get_ID()) && !Util.isEmpty(uuid))
			return I_AD_Element.COLUMNNAME_UUID + "=" + DB.TO_STRING(uuid);
		return po.get_WhereClause(true);
	}

	String getInsertKeySQL() {
		return "nextidfunc((SELECT AD_Sequence_ID FROM AD_Sequence"
				+ " WHERE UPPER(Name)=UPPER(" + DB.TO_STRING(info.getTableName()) + ")"
				+ " AND IsTableID='Y'),'N')";
	}

	String getUpdateSQLValue(int index, Object value) {
		if (value instanceof String && ((String)value).length() == 0
				&& info.getColumnClass(index) != Boolean.class)
			return DB.TO_STRING((String)value);
		return getSQLValue(index, value);
	}

	String getSQLValue(int index, Object value) {
		if (value == null || value == Null.NULL)
			return "NULL";
		if (value instanceof Integer) {
			MigrationUUIDResolver.Reference reference = enabled ? MigrationUUIDResolver.resolveReference(
					po, info, index, ((Integer)value).intValue()) : null;
			if (reference != null) {
				String match = " FROM " + reference.getTableName() + " WHERE "
						+ I_AD_Element.COLUMNNAME_UUID + "=" + DB.TO_STRING(reference.getUUID());
				// A missing destination UUID must fail the migration instead of
				// silently writing NULL into a nullable foreign key.
				return "COALESCE((SELECT " + reference.getKeyColumn() + match + "),"
						+ "(SELECT 1 / COUNT(*)" + match + "))";
			}
			return po.encrypt(index, value).toString();
		}
		if (value instanceof BigDecimal)
			return po.encrypt(index, value).toString();
		Class<?> columnClass = info.getColumnClass(index);
		if (columnClass == Boolean.class) {
			boolean booleanValue = value instanceof Boolean
					? ((Boolean)value).booleanValue() : "Y".equals(value);
			return po.encrypt(index, booleanValue ? "'Y'" : "'N'").toString();
		}
		if (value instanceof Timestamp)
			return DB.TO_DATE((Timestamp)po.encrypt(index, value),
					info.getColumnDisplayType(index) == DisplayType.Date);
		if (columnClass == String.class)
			return po.encrypt(index, DB.TO_STRING(value.toString())).toString();
		return po.saveNewSpecial(value, index);
	}
}
