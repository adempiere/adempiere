/*
 * Name:		DBObject_Table_Column.java
 * Description:	columns in a table
 * Created:		Feb 7, 2009
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 * 
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/DBObject_Table_Column.java
 * FileOwner:	spc.dvp
 * FilePerms:	0644
 *
 */

/**
 * This file is part of Adempiere ERP Bazaar
 * http://www.adempiere.org
 * 
 * Copyright (C) Stefan Christians
 * Copyright (C) Contributors
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * Contributors:
 * - Stefan Christians
 * 
 * Sponsors:
 * - K.K. Alice
 * 
 */

package com.kkalice.adempiere.migrate;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;

/**
 * columns in a table
 * @author Stefan Christians
 */
public class DBObject_Table_Column extends DBObjectDefinition {
	
	/** table name */
	private String m_table = null;
	/** column type */
	private String m_type = null;
	/** column size */
	private int m_size = 0;
	/** decimal digits */
	private int m_precision = 0;
	/** default value */
	private String m_default = null;
	/** column is nullable */
	private boolean m_isNullable = false;
	
	/** customization level */
	private Integer m_customizationLevel = null;
	/** element ID */
	private Integer m_elementID = null;
	/** column is new in target */
	private boolean m_isNew = false;

	/**
	 * constructor for columns
	 * @param parent the calling connection
	 * @param name the name of this column
	 * @param sequence the ordinal sequence of this column
	 */
	public DBObject_Table_Column(DBConnection parent, String name, int sequence) {
		super(parent, name, sequence);
	}

	/**
	 * initializes the definition of this column
	 * @param tableName name of the table
	 * @param columnType data type of this column
	 * @param columnSize size of this column
	 * @param columnPrecision scale of this column
	 * @param columnDefault default value for this column
	 * @param columnNullable column can be NULL
	 */
	public void initializeDefinition(String tableName, String columnType, int columnSize, int columnPrecision, String columnDefault, boolean columnNullable) {
		
		m_table = tableName;
		m_type = columnType;
		m_size = columnSize;
		m_precision = columnPrecision;
		m_default = columnDefault;
		m_isNullable = columnNullable;
		
		s_logger.log(Level.FINEST, toString());
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectDefinition#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(m_table).append("[")
			.append(m_sequence).append("] ")
			.append(m_name).append(": ")
			.append(m_type).append(" (")
			.append(m_size).append(",")
			.append(m_precision)
			.append(") - default=").append(m_default)
			.append(", nullable=").append(m_isNullable);
		return sb.toString();
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return m_type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		m_type = type;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return m_size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		m_size = size;
	}

	/**
	 * @return the precision
	 */
	public int getPrecision() {
		return m_precision;
	}

	/**
	 * @param precision the precision to set
	 */
	public void setPrecision(int precision) {
		m_precision = precision;
	}

	/**
	 * @return the default
	 */
	public String getDefault() {
		return m_default;
	}

	/**
	 * @param default1 the default to set
	 */
	public void setDefault(String default1) {
		m_default = default1;
	}

	/**
	 * @return the isNullable
	 */
	public boolean isNullable() {
		return m_isNullable;
	}
	
	/**
	 * @return the complement of isNullable 
	 */
	public boolean isNotNullable() {
		return !m_isNullable;
	}
	
	/**
	 * @param isNullable the isNullable to set
	 */
	public void setNullable(boolean isNullable) {
		m_isNullable = isNullable;
	}

	/**
	 * Whether or not this column is a customization
	 * @return this object is customized
	 */
	@SuppressWarnings("static-access")
	public boolean isCustomized() { 

		if (m_customizationLevel==null) {
			
			// just for initializing m_elementID
			getElementID();
			
			// default is not customized
			m_customizationLevel = Integer.valueOf(s_parameters.CUSTOMNONE);
			
			// first check whether the column name starts with a custom prefix
			if (m_parent.isCustomPrefix(m_name)) {
				m_customizationLevel = Integer.valueOf(s_parameters.CUSTOMPREFIXED);
			} else {
				// otherwise check if it is marked as customization in the application dictionary
				if (m_parent.isObjectExists("AD_COLUMN", m_parent.getTables()) && m_parent.isObjectExists("AD_TABLE", m_parent.getTables())) {
					String sql = s_dbEngine.sqlAD_getTableColumnEntityType(m_parent.getVendor(), m_parent.getCatalog(), m_parent.getSchema(), m_table, m_name);
					Statement stmt = m_parent.setStatement();
					ResultSet rs = m_parent.executeQuery(stmt, sql);
					if (m_parent.getResultSetNext(rs)) {
						String s = m_parent.getResultSetString(rs, "ENTITY_TYPE");
						if(m_parent.isCustomEntityType(s))
							m_customizationLevel = Integer.valueOf(s_parameters.CUSTOMMARKED);
					}
					m_parent.releaseResultSet(rs);
					m_parent.releaseStatement(stmt);
				}
			}
		}
		
		if (m_customizationLevel.intValue() > s_parameters.CUSTOMNONE)
			return true;
		else
			return false;
	}
	
	// customization level of this column
	public int getCustomizationLevel() {
		if (m_customizationLevel==null)
			isCustomized();
		return m_customizationLevel.intValue();
	}

	/**
	 * synchronize this column with metadata from source column
	 * @param sourceColumn reference column from the source database
	 * @return whether or not the synchronization was successful
	 */
	public boolean synchronize (DBObject_Table_Column sourceColumn) {
		boolean success = true;

		String vendor = m_parent.getVendor();
		String catalog = m_parent.getCatalog();
		String schema = m_parent.getSchema();

		String sql = null;
		Statement stmt = m_parent.setStatement();

		// check for changes in name
		String sourceName = sourceColumn.getName();
		String targetName = m_name;
		if (! targetName.equalsIgnoreCase(sourceName)) {
			sql = s_dbEngine.sqlObjectDetail_renameColumn(vendor, catalog, schema, m_table, m_name, sourceName);
			if (m_parent.executeUpdate(stmt, sql, false, false) == null)
				success = false;
			targetName = sourceName;
			setName(targetName);
		}

		// check for changes in type
		String sourceType = s_dbEngine.translateDataType(sourceColumn.getParent().getVendor(), vendor, sourceColumn.getType(), sourceColumn.getSize(), sourceColumn.getPrecision());
		String targetType = s_dbEngine.translateDataType(vendor, vendor, m_type, m_size, m_precision);
		if (! targetType.equals(sourceType)) {
			// create temporary column of new type to hold values 
			sql = s_dbEngine.sqlObjectDetail_createTemporaryColumn(vendor, catalog, schema, m_table, sourceType);
			if (m_parent.executeUpdate(stmt, sql, false, false) == null)
				success = false;
			// transfer data to temporary column (should automatically convert to new type by database engine)
			sql = s_dbEngine.sqlObjectDetail_saveTemporaryColumn(vendor, catalog, schema, m_table, m_name, sourceType);
			if (m_parent.executeUpdate(stmt, sql, false, false) == null)
				success = false;
			// make original column nullable
			if (! isNullable()) {
				sql = s_dbEngine.sqlObjectDetail_setColumnNullable(vendor, catalog, schema, m_table, m_name);
				if (m_parent.executeUpdate(stmt, sql, false, false) == null)
					success = false;
				setNullable(true);
			}
			// remove default value from original column
			if (m_default!=null) {
				sql = s_dbEngine.sqlObjectDetail_dropColumnDefault(vendor, catalog, schema, m_table, m_name);
				if (m_parent.executeUpdate(stmt, sql, false, false) == null)
					success = false;
				setDefault(null);
			}
			// erase data in original column
			sql = s_dbEngine.sqlObjectDetail_eraseColumn(vendor, catalog, schema, m_table, m_name);
			if (m_parent.executeUpdate(stmt, sql, false, false) == null)
				success = false;
			// change data type of original column
			sql = s_dbEngine.sqlObjectDetail_modifyColumnType(vendor, catalog, schema, m_table, m_name, sourceType);
			if (m_parent.executeUpdate(stmt, sql, false, false) == null)
				success = false;
			setType(sourceColumn.getType());
			setSize(sourceColumn.getSize());
			setPrecision(sourceColumn.getPrecision());
			// restore converted data from temporary column
			sql = s_dbEngine.sqlObjectDetail_restoreTemporaryColumn(vendor, catalog, schema, m_table, m_name);
			if (m_parent.executeUpdate(stmt, sql, false, false) == null)
				success = false;
			// drop temporary column
			sql = s_dbEngine.sqlObjectDetail_dropTemporaryColumn(vendor, catalog, schema, m_table);
			if (m_parent.executeUpdate(stmt, sql, false, false) == null)
				success = false;
		}

		// check for changes in default value
		String sourceDefault = sourceColumn.getDefault();
		if (sourceDefault!=null)
			sourceDefault = s_dbEngine.translateExpression(sourceColumn.getParent().getVendor(), vendor, sourceDefault).trim();
		String targetDefault = m_default;
		if (targetDefault!=null)
			targetDefault = targetDefault.trim();
		if (sourceDefault==null) {
			// drop default for this column
			if (targetDefault!=null) {
				sql = s_dbEngine.sqlObjectDetail_dropColumnDefault(vendor, catalog, schema, m_table, m_name);
				if (m_parent.executeUpdate(stmt, sql, false, false) == null)
					success = false;
				setDefault(null);
			}
		} else {
			if (targetDefault==null || ! targetDefault.equals(sourceDefault)) {
				// set new default for this column
				sql = s_dbEngine.sqlObjectDetail_setColumnDefault(vendor, catalog, schema, m_table, m_name, sourceDefault);
				if (m_parent.executeUpdate(stmt, sql, false, false) == null)
					success = false;
				setDefault(sourceDefault);
			}
		}

		// check for changes in nullable constraint
		if (sourceColumn.isNullable() && this.isNotNullable()) {
			// make column nullable
			sql = s_dbEngine.sqlObjectDetail_setColumnNullable(vendor, catalog, schema, m_table, m_name);
			if (m_parent.executeUpdate(stmt, sql, false, false) == null)
				success = false;
			setNullable(true);
		} else if(sourceColumn.isNotNullable() && this.isNullable()) {
			// ensure that there are no columns containing null
			sql = s_dbEngine.sqlObjectDetail_prepareColumnNotNullable(vendor, catalog, schema, m_table, m_name, m_type, m_default);
			if (m_parent.executeUpdate(stmt, sql, false, false) == null)
				success = false;
			// make column not nullable
			sql = s_dbEngine.sqlObjectDetail_dropColumnNullable(vendor, catalog, schema, m_table, m_name);
			if (m_parent.executeUpdate(stmt, sql, false, false) == null)
				success = false;
			setNullable(false);
		}

		m_parent.releaseStatement(stmt);

		return success;
	}

	/**
	 * find out which identifier this column has in the application dictionary
	 * @return the AD_Element_ID (or 0)
	 */
	public int getElementID() {

		if (m_elementID==null) {

			// default is 0
			m_elementID = Integer.valueOf(0);

			// load element_ID from application dictionary
			if (m_parent.isObjectExists("AD_COLUMN", m_parent.getTables()) && m_parent.isObjectExists("AD_TABLE", m_parent.getTables())) {
				String sql = s_dbEngine.sqlAD_getTableColumnElement(m_parent.getVendor(), m_parent.getCatalog(), m_parent.getSchema(), m_table, m_name);
				Statement stmt = m_parent.setStatement();
				ResultSet rs = m_parent.executeQuery(stmt, sql);
				if (m_parent.getResultSetNext(rs)) {
					m_elementID = Integer.valueOf(m_parent.getResultSetInt(rs, "COLUMN_ELEMENT"));
				}
				m_parent.releaseResultSet(rs);
				m_parent.releaseStatement(stmt);
			}
		}

		return m_elementID.intValue();
	}
	
	/**
	 * drop this column
	 * @return whether or not the column was successfully dropped
	 */
	public boolean drop() {
		boolean result = true;

		String sql = s_dbEngine.sqlObjectDetail_dropColumn(m_parent.getVendor(), m_parent.getCatalog(), m_parent.getSchema(), m_table, m_name);
		Statement stmt = m_parent.setStatement();
		if (m_parent.executeUpdate(stmt, sql, false, false) == null)
			result = false;
		m_parent.releaseStatement(stmt);

		return result;
	}

	/**
	 * @return the table
	 */
	public String getTable() {
		return m_table;
	}

	/**
	 * @return this is a new column which was added to target
	 */
	public boolean isNew() {
		return m_isNew;
	}
	
	/**
	 * set whether this is a new column which was added to target
	 * @param isNew this is a new column
	 */
	public void setNew(boolean isNew) {
		m_isNew = isNew;
	}
	
}
