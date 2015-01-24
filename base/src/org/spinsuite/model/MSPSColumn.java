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
 * Copyright (C) 2003-2013 E.R.P. Consultores y Asociados.                    *
 * All Rights Reserved.                                                       *
 * Contributor(s): Dixon Martinez www.erpconsultoresyasociados.com            *
 *****************************************************************************/
package org.spinsuite.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MTable;
import org.compiere.model.M_Element;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Msg;

/**
 * @author Dixon Martinez
 * @author <a href="mailto:yamelsenih@gmail.com">Yamel Senih</a>
 *  <li>Add support to SQLite Script Crate
 */
public class MSPSColumn extends X_SPS_Column {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8081251048073987582L;
	
	/**	Logger	*/
	private static CLogger s_log = CLogger.getCLogger (MSPSColumn.class);

	/**
	 * *** Class Constructor ***
	 * @author Dixon Martinez 08/02/2013, 17:21:47
	 * @param ctx
	 * @param SPS_SyncColumn_ID
	 * @param trxName
	 */
	public MSPSColumn(Properties ctx, int SPS_SyncColumn_ID, String trxName) {
		super(ctx, SPS_SyncColumn_ID, trxName);
	}

	/**
	 * *** Class Constructor ***
	 * @author Dixon Martinez 08/02/2013, 17:21:47
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MSPSColumn(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * create new column FR [ 3426134 ]
	 * @param parent
	 * @param columnName
	 * @param AD_Element_ID
	 * @param length
	 * @param AD_Reference
	 * @param defaultValue
	 */
	public MSPSColumn (MSPSTable parent, String columnName, int length , int AD_Reference , String defaultValue)
	{
		this (parent.getCtx(), 0, parent.get_TrxName());
		setClientOrg(parent);
		setSPS_Table_ID(parent.getSPS_Table_ID());
		setEntityType(parent.getEntityType());
		setColumnName(columnName);	
		M_Element AD_Element = M_Element.get(getCtx(),columnName);
		if(AD_Element != null )
		{	
			setAD_Element_ID(AD_Element.get_ID());
		}	
		setName(columnName);
		setIsActive(true);
		setIsMandatory(true);
		setFieldLength(length);
		setAD_Reference_ID(AD_Reference);
		setDefaultValue(defaultValue);
		setUpdateable(false);
	}	//	MColumn
	
	
	/**
	 * 	Parent Constructor
	 *	@param parent table
	 */
	public MSPSColumn (MSPSTable parent)
	{
		this (parent.getCtx(), 0, parent.get_TrxName());
		setClientOrg(parent);
		setSPS_Table_ID(parent.getSPS_Table_ID());
	}
	
	/**
	 * Set default base on AD_Element FR [ 3426134 ]
	 * @param ctx context
	 * @param column AD Column
	 * @param trxName transaction Name
	 * @return I_AD_Column
	 */
	public static I_SPS_Column setSPS_Column(Properties ctx ,I_SPS_Column column , String trxName)
	{
		MSPSTable m_SPS_Table = (MSPSTable) column.getSPS_Table();
		
		M_Element element =  new M_Element(ctx, column.getAD_Element_ID() , trxName);
		if(element.getAD_Reference_ID() == DisplayType.ID)
		{
			String columnName = m_SPS_Table.get_TableName()+"_ID";
			if(!columnName.equals(element.getColumnName()) )
			{
				column.setAD_Reference_ID(DisplayType.TableDir);
			}
		}

		String entityType = column.getSPS_Table().getEntityType();
		if(!MTable.ENTITYTYPE_Dictionary.equals(entityType))
			column.setEntityType(entityType);
		
		if(column.getColumnName() == null || column.getColumnName().length() <= 0)
			column.setColumnName(element.getColumnName());	
		if(column.getFieldLength() <= 0 )
			column.setFieldLength(element.getFieldLength());
		if(column.getAD_Reference_ID() <= 0)	
			column.setAD_Reference_ID(element.getAD_Reference_ID());
		if(column.getAD_Reference_Value_ID() <= 0)
			column.setAD_Reference_Value_ID(element.getAD_Reference_Value_ID());
		if(column.getName() == null || column.getName().length() <= 0)
			column.setName(element.getName());
		if(column.getDescription() == null || column.getDescription().length() <= 0)
			column.setDescription(element.getDescription());
		/*if(column.getHelp() == null || column.getHelp().length() <= 0)
			column.setHelp(element.getHelp());*/
		if(column.getColumnName().equals("Name") || column.getColumnName().equals("Value"))
		{	
			column.setIsIdentifier(true);
			int seqNo = DB.getSQLValue(trxName,"SELECT MAX(SeqNo) FROM SPS_Column "+
					"WHERE SPS_Table_ID=?"+
					" AND IsIdentifier='Y'",column.getSPS_Table_ID());
			column.setSeqNo(seqNo + 1);
		}
		return column;	
	}
	
	/**
	 * 	Get SQL DDL
	 *	@return columnName datataype ..
	 */
	public String getSQLDDL()
	{
		if (isVirtualColumn())
			return null;
		
		StringBuffer sql = new StringBuffer (getColumnName())
			.append(" ").append(getSQLDataType());

		//	Default
		String defaultValue = getDefaultValue();
		if (defaultValue != null 
				&& defaultValue.length() > 0
				&& defaultValue.indexOf('@') == -1		//	no variables
				&& ( ! (DisplayType.isID(getAD_Reference_ID()) && defaultValue.equals("-1") ) ) )  // not for ID's with default -1
		{
			if (DisplayType.isText(getAD_Reference_ID()) 
					|| getAD_Reference_ID() == DisplayType.List
					|| getAD_Reference_ID() == DisplayType.YesNo
					// Two special columns: Defined as Table but DB Type is String 
					|| getColumnName().equals("EntityType") || getColumnName().equals("AD_Language")
					|| (getAD_Reference_ID() == DisplayType.Button &&
							!(getColumnName().endsWith("_ID"))))
			{
				if (!defaultValue.startsWith("'") && !defaultValue.endsWith("'"))
					defaultValue = DB.TO_STRING(defaultValue);
			}
			sql.append(" DEFAULT ").append(defaultValue);
		}
		else
		{
			if (! isMandatory())
				sql.append(" DEFAULT NULL ");
			defaultValue = null;
		}

		//	Inline Constraint
		if (getAD_Reference_ID() == DisplayType.YesNo)
			sql.append(" CHECK (").append(getColumnName()).append(" IN ('Y','N'))");

		//	Null
		if (isMandatory())
			sql.append(" NOT NULL");
		return sql.toString();
	}	//	getSQLDDL
	
	/**
	 * 	Is Virtual Column
	 *	@return true if virtual column
	 */
	public boolean isVirtualColumn()
	{
		String s = getColumnSQL();
		return s != null && s.length() > 0;
	}	//	isVirtualColumn

	/**
	 * 	Get SQL Data Type
	 *	@return e.g. NVARCHAR(60)
	 */
	public String getSQLDataType()
	{
		String columnName = getColumnName();
		int dt = getAD_Reference_ID();
		return getSQLDataType (dt, columnName, getFieldLength());
	}	//	getSQLDataType
	
	/**
	 * 	Get Table Constraint
	 *	@param tableName table name
	 *	@return table constraint
	 */
	public String getConstraint(String tableName)
	{
		if (isKey()) {
			String constraintName;
			if (tableName.length() > 26)
				// Oracle restricts object names to 30 characters
				constraintName = tableName.substring(0, 26) + "_Key";
			else
				constraintName = tableName + "_Key";
			return "CONSTRAINT " + constraintName + " PRIMARY KEY (" + getColumnName() + ")";
		}
		/**
		if (getAD_Reference_ID() == DisplayType.TableDir 
			|| getAD_Reference_ID() == DisplayType.Search)
			return "CONSTRAINT " ADTable_ADTableTrl
				+ " FOREIGN KEY (" + getColumnName() + ") REFERENCES "
				+ AD_Table(AD_Table_ID) ON DELETE CASCADE
		**/
		
		return "";
	}	//	getConstraint
	
	/**
	 * 	Get SQL DataType origin from "DisplayType"
	 * 	@author <a href="mailto:yamelsenih@gmail.com">Yamel Senih</a>
	 *  <li>Add support to SQLite
	 *	@param displayType AD_Reference_ID
	 *	@param columnName name
	 *	@param fieldLength length
	 *	@return SQL Data Type in Oracle Notation
	 */
	public static String getSQLDataType (int displayType, String columnName, int fieldLength)
	{
		if (columnName.equals("EntityType")
			|| columnName.equals ("AD_Language"))
			return "VARCHAR(" + fieldLength + ")";
		//	ID
		if (DisplayType.isID(displayType))
		{
			if (displayType == DisplayType.Image 	//	FIXTHIS
				&& columnName.equals("BinaryData"))
				return "BLOB";
			//	ID, CreatedBy/UpdatedBy, Acct
			else if (columnName.endsWith("_ID") 
				|| columnName.endsWith("tedBy") 
				|| columnName.endsWith("_Acct") )
				return "NUMERIC(10)";
			else if (fieldLength < 4)
				return "CHAR(" + fieldLength + ")";
			else	//	EntityType, AD_Language	fallback
				return "VARCHAR(" + fieldLength + ")";
		}
		//
		if (displayType == DisplayType.Integer)
			return "NUMBERIC(10)";
		if (DisplayType.isDate(displayType))
			return "TIMESTAMP";
		if (DisplayType.isNumeric(displayType))
			return "NUMBER";
		if (displayType == DisplayType.Binary)
			return "TEXT";
		if (displayType == DisplayType.TextLong 
			|| (displayType == DisplayType.Text && fieldLength >= 4000))
			return "TEXT";
		if (displayType == DisplayType.YesNo)
			return "CHAR(1)";
		if (displayType == DisplayType.List) {
			if (fieldLength == 1)
				return "CHAR(" + fieldLength + ")";
			else
				return "VARCHAR(" + fieldLength + ")";			
		}
		if (displayType == DisplayType.Color) // this condition is never reached - filtered above in isID
		{
			if (columnName.endsWith("_ID"))
				return "NUMBER(10)";
			else
				return "CHAR(" + fieldLength + ")";
		}
		if (displayType == DisplayType.Button)
		{
			if (columnName.endsWith("_ID"))
				return "NUMBERIC(10)";
			else
				return "CHAR(" + fieldLength + ")";
		}
		if (!DisplayType.isText(displayType))
			s_log.severe("Unhandled Data Type = " + displayType);
				
		return "VARCHAR(" + fieldLength + ")";
	}	//	getSQLDataType
	
	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		//set column default based in element when is a new column FR [ 3426134 ]
		if(newRecord)
			setSPS_Column(getCtx(), this, get_TrxName());

		int displayType = getAD_Reference_ID();
		if (DisplayType.isLOB(displayType))	//	LOBs are 0
		{
			if (getFieldLength() != 0)
				setFieldLength(0);
		}
		else if (getFieldLength() == 0) 
		{
			if (DisplayType.isID(displayType))
				setFieldLength(10);
			else if (DisplayType.isNumeric (displayType))
				setFieldLength(14);
			else if (DisplayType.isDate (displayType))
				setFieldLength(7);
			else if(DisplayType.YesNo == displayType)
				setFieldLength(1);
			else 
			{
				log.saveError("FillMandatory", Msg.getElement(getCtx(), "FieldLength"));
				return false;
			}
		}
		
		/** Views are not updateable
		UPDATE AD_Column c
		SET IsUpdateable='N', IsAlwaysUpdateable='N'
		WHERE AD_Table_ID IN (SELECT AD_Table_ID FROM AD_Table WHERE IsView='Y')
		**/
		
		//	Virtual Column
		if (isVirtualColumn())
		{
			if (isMandatory())
				setIsMandatory(false);
			if (isUpdateable())
				setIsUpdateable(false);
		}
		//	Updateable
		if (isParent() || isKey())
			setIsUpdateable(false);
		if (isAlwaysUpdateable() && !isUpdateable())
			setIsAlwaysUpdateable(false);
		//	Sync Terminology
		if ((newRecord || is_ValueChanged ("AD_Element_ID")) 
			&& getAD_Element_ID() != 0)
		{
			M_Element element = new M_Element (getCtx(), getAD_Element_ID (), get_TrxName());
			setColumnName (element.getColumnName());
			setName (element.getName());
			setDescription (element.getDescription());
			//setHelp (element.getHelp());
		}
		//	Yamel Senih 2014-10-24 20:06:17
		//	Set Column reference
		if(getAD_Column_ID() == 0) {
			//	Get Column Reference
			int m_AD_Column_ID = DB.getSQLValue(get_TrxName(), "SELECT c.AD_Column_ID "
					+ "FROM AD_Column c "
					+ "INNER JOIN SPS_Table t ON(t.AD_Table_ID = c.AD_Table_ID) "
					+ "WHERE t.SPS_Table_ID = ? "
					+ "AND c.ColumnName = ?", getSPS_Table_ID(), getColumnName());
			//	Set Column Reference
			if(m_AD_Column_ID > 0)
				setAD_Column_ID(m_AD_Column_ID);
		}
		return true;
	}	//	beforeSave

	
	
	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		//	Update Fields
		if (!newRecord)
		{
			if (   is_ValueChanged(MSPSColumn.COLUMNNAME_Name)
				|| is_ValueChanged(MSPSColumn.COLUMNNAME_Description)
				//|| is_ValueChanged(MSPSColumn.COLUMNNAME_Help)
				) {
				StringBuffer sql = new StringBuffer("UPDATE SPS_Field SET Name=")
					.append(DB.TO_STRING(getName()))
					.append(", Description=").append(DB.TO_STRING(getDescription()))
					//.append(", Help=").append(DB.TO_STRING(getHelp()))
					.append(" WHERE SPS_Column_ID=").append(get_ID())
					.append(" AND IsCentrallyMaintained='Y'");
				int no = DB.executeUpdate(sql.toString(), get_TrxName());
				log.fine("afterSave - Fields updated #" + no);
			}
		}
		return success;
	}	//	afterSave
	
}
