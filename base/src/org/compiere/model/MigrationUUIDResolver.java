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

import java.util.Properties;

import org.adempiere.core.domains.models.I_AD_Chart;
import org.adempiere.core.domains.models.I_AD_Element;
import org.adempiere.core.domains.models.I_AD_Image;
import org.adempiere.core.domains.models.I_C_Location;
import org.adempiere.core.domains.models.I_C_ValidCombination;
import org.adempiere.core.domains.models.I_M_AttributeSetInstance;
import org.adempiere.core.domains.models.I_M_Locator;
import org.adempiere.core.domains.models.I_S_ResourceAssignment;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Util;

/**
 * Resolves installation-local record identifiers through their UUID.
 *
 * This class contains the portable identity rules used by Application Dictionary
 * migrations stored in AD_MigrationData.
 *
 * @author Carlos Parada, cparada@erpya.com, ERPyA https://erpya.com
 */
public final class MigrationUUIDResolver {

	/** AD_SysConfig key controlling the first installation-local ID. */
	public static final String SYSCONFIG_CUSTOM_ID_THRESHOLD = "MIGRATION_UUID_CUSTOM_ID_THRESHOLD";
	private static final int DEFAULT_CUSTOM_ID_THRESHOLD = 1000000;
	private static final String REFERENCE_PREFIX = "@UUID@";
	private static final char REFERENCE_SEPARATOR = ':';

	private MigrationUUIDResolver() {
	}

	public static int getCustomIdThreshold() {
		int threshold = MSysConfig.getIntValue(SYSCONFIG_CUSTOM_ID_THRESHOLD, DEFAULT_CUSTOM_ID_THRESHOLD);
		return threshold > 0 ? threshold : DEFAULT_CUSTOM_ID_THRESHOLD;
	}

	public static boolean isCustomId(int id) {
		return id >= getCustomIdThreshold();
	}

	/**
	 * Holds a portable reference to a single-key record, identified by table and UUID.
	 *
	 * @author Carlos Parada, cparada@erpya.com, ERPyA https://erpya.com
	 */
	public static final class Reference {
		private final String tableName;
		private final String keyColumn;
		private final String uuid;

		private Reference(String tableName, String keyColumn, String uuid) {
			this.tableName = tableName;
			this.keyColumn = keyColumn;
			this.uuid = uuid;
		}

		public String getTableName() {
			return tableName;
		}

		public String getKeyColumn() {
			return keyColumn;
		}

		public String getUUID() {
			return uuid;
		}
	}

	/** Resolve a custom numeric value in a PO column to its referenced UUID. */
	public static Reference resolveReference(PO po, POInfo info, int index, int recordId) {
		if (!isCustomId(recordId))
			return null;
		String tableName = getReferenceTable(po, info, index);
		if (Util.isEmpty(tableName))
			return null;

		MTable table = MTable.get(po.getCtx(), tableName);
		if (table == null || table.getColumn(I_AD_Element.COLUMNNAME_UUID) == null)
			return null;
		String[] keys = table.getKeyColumns();
		if (keys == null || keys.length != 1)
			return null;

		String uuid = DB.getSQLValueString(po.get_TrxName(),
				"SELECT " + I_AD_Element.COLUMNNAME_UUID
				+ " FROM " + table.getTableName()
				+ " WHERE " + keys[0] + "=?", recordId);
		if (Util.isEmpty(uuid))
			return null;
		return new Reference(table.getTableName(), keys[0], uuid);
	}

	/** Encode a custom foreign key for storage in AD_MigrationData. */
	public static String toPortableDataValue(PO po, POInfo info, int index, Object value) {
		if (!(value instanceof Integer))
			return value == null ? null : value.toString();
		Reference reference = resolveReference(po, info, index, ((Integer)value).intValue());
		return reference == null ? value.toString() : encode(reference);
	}

	/** Resolve an encoded AD_MigrationData value to the destination-local ID. */
	public static String resolvePortableDataValue(Properties ctx, String trxName,
			MColumn column, String value) {
		Reference reference = decode(value);
		if (reference == null)
			return value;
		int id = findIdByUUID(ctx, trxName, reference.getTableName(), reference.getUUID());
		if (id <= 0)
			throw new IllegalStateException("No record found for " + reference.getTableName()
					+ ".UUID=" + reference.getUUID() + " referenced by "
					+ column.getColumnName());
		return Integer.toString(id);
	}

	public static String encode(Reference reference) {
		return REFERENCE_PREFIX + reference.getTableName()
				+ REFERENCE_SEPARATOR + reference.getUUID();
	}

	public static Reference decode(String value) {
		if (value == null || !value.startsWith(REFERENCE_PREFIX))
			return null;
		int separator = value.indexOf(REFERENCE_SEPARATOR, REFERENCE_PREFIX.length());
		if (separator <= REFERENCE_PREFIX.length() || separator == value.length() - 1)
			return null;
		String tableName = value.substring(REFERENCE_PREFIX.length(), separator);
		String uuid = value.substring(separator + 1);
		return new Reference(tableName, null, uuid);
	}

	public static int findIdByUUID(Properties ctx, String trxName, String tableName, String uuid) {
		if (Util.isEmpty(tableName) || Util.isEmpty(uuid))
			return -1;
		MTable table = MTable.get(ctx, tableName);
		if (table == null || table.getColumn(I_AD_Element.COLUMNNAME_UUID) == null)
			return -1;
		String[] keys = table.getKeyColumns();
		if (keys == null || keys.length != 1)
			return -1;
		return DB.getSQLValue(trxName,
				"SELECT " + keys[0] + " FROM " + table.getTableName()
				+ " WHERE " + I_AD_Element.COLUMNNAME_UUID + "=?", uuid);
	}

	private static String getReferenceTable(PO po, POInfo info, int index) {
		String columnName = info.getColumnName(index);
		if ("Record_ID".equalsIgnoreCase(columnName)) {
			int tableIdIndex = info.getColumnIndex("AD_Table_ID");
			if (tableIdIndex >= 0) {
				MTable table = MTable.get(po.getCtx(), po.get_ValueAsInt(tableIdIndex));
				return table == null ? null : table.getTableName();
			}
			return null;
		}
		if ("Node_ID".equalsIgnoreCase(columnName)
				|| "Parent_ID".equalsIgnoreCase(columnName)) {
			int treeIndex = info.getColumnIndex("AD_Tree_ID");
			if (treeIndex < 0)
				return null;
			int treeId = po.get_ValueAsInt(treeIndex);
			String tableName = DB.getSQLValueString(po.get_TrxName(),
					"SELECT t.TableName FROM AD_Tree tr"
					+ " INNER JOIN AD_Table t ON (t.AD_Table_ID=tr.AD_Table_ID)"
					+ " WHERE tr.AD_Tree_ID=?", treeId);
			if (!Util.isEmpty(tableName))
				return tableName;
			String treeType = DB.getSQLValueString(po.get_TrxName(),
					"SELECT TreeType FROM AD_Tree WHERE AD_Tree_ID=?", treeId);
			return MTree.getSourceTableName(treeType);
		}

		int displayType = info.getColumnDisplayType(index);
		MColumn column = MColumn.get(po.getCtx(), info.getColumn(index).AD_Column_ID);
		if (column == null)
			return null;
		if ((displayType == DisplayType.TableDir
				|| (displayType == DisplayType.Search && column.getAD_Reference_Value_ID() == 0))
				&& columnName.endsWith("_ID"))
			return columnName.substring(0, columnName.length() - 3);
		if (displayType == DisplayType.Table || displayType == DisplayType.Search)
			return DB.getSQLValueString(po.get_TrxName(),
					"SELECT t.TableName FROM AD_Ref_Table rt"
					+ " INNER JOIN AD_Table t ON (t.AD_Table_ID=rt.AD_Table_ID)"
					+ " WHERE rt.AD_Reference_ID=?", column.getAD_Reference_Value_ID());
		if (displayType == DisplayType.Location)
			return I_C_Location.Table_Name;
		if (displayType == DisplayType.Locator)
			return I_M_Locator.Table_Name;
		if (displayType == DisplayType.Account)
			return I_C_ValidCombination.Table_Name;
		if (displayType == DisplayType.Assignment)
			return I_S_ResourceAssignment.Table_Name;
		if (displayType == DisplayType.PAttribute)
			return I_M_AttributeSetInstance.Table_Name;
		if (displayType == DisplayType.Image)
			return I_AD_Image.Table_Name;
		if (displayType == DisplayType.Chart)
			return I_AD_Chart.Table_Name;
		return null;
	}
}
