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

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.core.domains.models.I_AD_Column;
import org.adempiere.core.domains.models.I_AD_Table;
import org.adempiere.core.domains.models.X_AD_Column;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.Util;

/**
 *	Persistent Column Model
 *	
 *  @author Jorg Janke
 *  @author victor.perez@e-evolution.com, www.e-evolution.com
 *  	<li>FR [ 3426134 ] Add the Reference ,FieldLength, Reference Value
 * 		https://sourceforge.net/tracker/?func=detail&aid=3426134&group_id=176962&atid=879335
 * 		<li> Add method that valid if a column is encrypted
 *  @version $Id: MColumn.java,v 1.6 2006/08/09 05:23:49 jjanke Exp $
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *  	<li> BR [ 9223372036854775807 ] Lookup for search view not show button
 *  	<li> Add default length to Yes No Display Type
 *  	@see https://adempiere.atlassian.net/browse/ADEMPIERE-447
 *  	<li> BR [ 185 ] Fixed error with validation in beforeSave method for MColumn 
 *  	@see https://github.com/adempiere/adempiere/issues/185
 *  	<a href="https://github.com/adempiere/adempiere/issues/655">
 * 		@see FR [ 655 ] Bad validation for sequence of identifier columns</a>
 * 		<a href="https://github.com/adempiere/adempiere/issues/1072">
 * 		@see BR [ 1072 ] Synchronize Column is unnecessary when it is not apply for DB</a>
 * 		<a href="https://github.com/adempiere/adempiere/issues/922">
 * 		@see FR [ 922 ] Is Allow Copy in model</a>
 *  @author mckayERP www.mckayERP.com
 *  	<li> FR [ <a href="https://github.com/adempiere/adempiere/issues/213">#213</a> ] Support for application dictionary changes 
 *  		 and configurable automatic syncing with the database
 *  @author Edwin Betancourt EdwinBetanc0ut@outlook.com
 *  	<li> <a href="https://github.com/adempiere/adempiere/issues/3363">
 * 		@see BR [ 3363 ] Length in 0 of column, prevents to register values in that column</a>
 */
public class MColumn extends X_AD_Column
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 3455817869952578951L;

	/**
	 * A system configuration entry that can be used to turn off the 
	 * database auto sync which defaults to on ('Y').  Set the value to something
	 * other than 'Y' to disable the autosync.
	 */
	public static final String SYSCONFIG_DATABASE_AUTO_SYNC="DATABASE_AUTO_SYNC";

	public static final String MSG_CannotSyncVirtualColumn = "@MColumn_CannotSyncVirtualColumn@";
	public static final String MSG_CannotSyncView = "@MColumn_CannotSyncView@";
	public static final String MSG_SyncErrorColumnAlreadyExists = "@MColumn_SyncError_ColumnWithThatNameAlreadyExists@";
	public static final String MSG_NoSQLNOChangesMade = "@MColumn_NoSQLNoChangesMade@";  
	public static final String MSG_ColumnAlreadyExists = "@MColumn_ColumnAlreadyExists@";
	public static final String MSG_ErrorSyncingColumn = "@MColumn_SyncError@";
	public static final String MSG_ColumnPartOfKeyButNotKeyOrParentInAD = "@MColumn_ColumnPartOfKeyConstraintButIsNotAKeyOrParentLink@";
	public static final String MSG_ColumnDoesNotExistAndWillBeAdded = "@MColumn_ColumnDoesNotExistAndWillBeAdded@";
	public static final String MSG_ColumnNameChanged = "@MColumn_ColumnNameChanged@";
	public static final String MSG_DataTypeChanged = "@MColumn_ColumnDataTypeChanged@";
	public static final String MSG_ManadatoryValueChanged = "@MColumn_ColumnMandatorySettingChanged@";
	public static final String MSG_DefaultValueChanged = "@MColumn_ColumnDefaultValueChanged@";
	public static final String MSG_SQLSuccessfullyApplied = "@MColumn_ChangesSuccessfullyApplied@";
	
	/**
     * Get if id column is Encrypted
     * @param columnId
     * @return true or false
     */
    public static boolean isEncrypted (int columnId)
    {
        final String sql = "SELECT IsEncrypted FROM AD_Column WHERE AD_Column_ID = ?";
        return "Y".equals(DB.getSQLValueString(null , sql , columnId));
    }

	/**
	 * Set default base on AD_Element FR [ 3426134 ]
	 * @param ctx context
	 * @param column AD Column
	 * @param trxName transaction Name
	 * @return I_AD_Column
	 */
	public static I_AD_Column setAD_Column(Properties ctx ,I_AD_Column column , String trxName)
	{
		MTable table = (MTable) column.getAD_Table();
		M_Element element =  new M_Element(ctx, column.getAD_Element_ID() , trxName);
		if(element.getAD_Reference_ID() == DisplayType.ID)
		{
			String columnName = table.getTableName()+"_ID";
            String tableDir = element.getColumnName().replace("_ID", "");
			if(!columnName.equals(element.getColumnName()) && MTable.getTable_ID(tableDir) > 0)
			{
				column.setAD_Reference_ID(DisplayType.TableDir);
			}
		}

		String entityType = column.getAD_Table().getEntityType();
		if (entityType == null)
			throw  new AdempiereException("@EntityType@ @@AD_Table_ID@ @NotFound@");

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
		if(column.getHelp() == null || column.getHelp().length() <= 0)
			column.setHelp(element.getHelp());
		return column;	
	}
	/**
	 * 	Get MColumn from Cache
	 *	@param ctx context
	 * 	@param AD_Column_ID id
	 *	@return MColumn
	 */
	public static MColumn get (Properties ctx, int AD_Column_ID)
	{
		Integer key = Integer.valueOf(AD_Column_ID);
		MColumn retValue = (MColumn) s_cache.get (key);
		if (retValue != null)
			return retValue;
		retValue = new MColumn (ctx, AD_Column_ID, null);
		if (retValue.get_ID () != 0)
			s_cache.put (key, retValue);
		return retValue;
	}	//	get

	/**
	 * 	Get Column Name
	 *	@param ctx context
	 *	@param AD_Column_ID id
	 *	@return Column Name or null
	 */
	public static String getColumnName (Properties ctx, int AD_Column_ID)
	{
		MColumn col = MColumn.get(ctx, AD_Column_ID);
		if (col.get_ID() == 0)
			return null;
		return col.getColumnName();
	}	//	getColumnName
	
	/**	Cache						*/
	private static CCache<Integer,MColumn>	s_cache	= new CCache<Integer,MColumn>("AD_Column", 20);
	
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MColumn.class);

	/** A flag used to indicate that the column is being added to a new table. */
	private boolean isNewTable = false;
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param AD_Column_ID
	 *	@param trxName transaction
	 */
	public MColumn (Properties ctx, int AD_Column_ID, String trxName)
	{
		super (ctx, AD_Column_ID, trxName);
		if (AD_Column_ID == 0)
		{
		//	setAD_Element_ID (0);
		//	setAD_Reference_ID (0);
		//	setColumnName (null);
		//	setName (null);
		//	setEntityType (null);	// U
			setIsAlwaysUpdateable (false);	// N
			setIsEncrypted (false);
			setIsIdentifier (false);
			setIsKey (false);
			setIsMandatory (false);
			setIsParent (false);
			setIsSelectionColumn (false);
			setIsTranslated (false);
			setIsUpdateable (true);	// Y
			setVersion (Env.ZERO);
		}
	}	//	MColumn

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MColumn (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MColumn
	
	/**
	 * 	Parent Constructor
	 *	@param parent table
	 */
	public MColumn (MTable parent)
	{
		this (parent.getCtx(), 0, parent.get_TrxName());
		setClientOrg(parent);
		setAD_Table_ID (parent.getAD_Table_ID());
		setEntityType(parent.getEntityType());
	}	//	MColumn
	

	/**
	 * create new column FR [ 3426134 ]
	 * @param parent
	 * @param columnName
	 * @param AD_Element_ID
	 * @param length
	 * @param AD_Reference
	 * @param defaultValue
	 */
	public MColumn (MTable parent, String columnName, int length , int AD_Reference , String defaultValue)
	{
		this (parent.getCtx(), 0, parent.get_TrxName());
		setClientOrg(parent);
		setAD_Table_ID (parent.getAD_Table_ID());
		setEntityType(parent.getEntityType());
		setColumnName(columnName);	
		M_Element AD_Element = M_Element.get(getCtx(),columnName);
		if(AD_Element != null )
		{	
			setAD_Element_ID(AD_Element.get_ID());
		}	
		setName(columnName);
		setIsActive(true);
		setVersion(Env.ONE);
		setIsMandatory(true);
		setIsAllowLogging(true);
		setFieldLength(length);
		setAD_Reference_ID(AD_Reference);
		setDefaultValue(defaultValue);
		setUpdateable(false);
	}	//	MColumn
	
	/**
	 * 	Is Standard Column
	 *	@return true for AD_Client_ID, etc.
	 */
	public boolean isStandardColumn()
	{
		String columnName = getColumnName();
		if (columnName.equals("AD_Client_ID") 
			|| columnName.equals("AD_Org_ID")
			|| columnName.equals("IsActive")
			|| columnName.startsWith("Created")
			|| columnName.startsWith("Updated") )
			return true;
		
		return false;
	}	//	isStandardColumn
	
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
	 * 	Is the Column Encrypted?
	 *	@return true if encrypted
	 */
	public boolean isEncrypted()
	{
		String s = getIsEncrypted();
		return "Y".equals(s);
	}	//	isEncrypted
	
	/**
	 * 	Set Encrypted
	 *	@param IsEncrypted encrypted
	 */
	public void setIsEncrypted (boolean IsEncrypted)
	{
		setIsEncrypted (IsEncrypted ? "Y" : "N");
	}	//	setIsEncrypted
	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		//set column default based in element when is a new column FR [ 3426134 ]
		if(newRecord) {
			if(!isDirectLoad()) {
				setAD_Column(getCtx(), this, get_TrxName());
				//Create Document No or Value sequence
				if ("DocumentNo".equals(getColumnName()) || !isDirectLoad() && "Value".equals(getColumnName())) {
					String tableName = MTable.getTableName(getCtx(), getAD_Table_ID());
					final String whereClause = MSequence.COLUMNNAME_AD_Client_ID + "=? AND " + MSequence.COLUMNNAME_Name + "=? ";
					//	Sequence for DocumentNo/Value
					Arrays.stream(MClient.getAll(getCtx())).forEach( client -> {
						MSequence sequence = new Query(getCtx(), MSequence.Table_Name, whereClause, get_TrxName())
								.setParameters(client.getAD_Client_ID() , MSequence.PREFIX_DOCSEQ + tableName )
								.first();
						if (sequence == null || sequence.getAD_Sequence_ID() <= 0) {
							sequence = new MSequence(getCtx(), client.getAD_Client_ID(), tableName , get_TrxName());
							sequence.saveEx();
						}
					});
				}
				setIsAllowCopy(MColumn.isAllowCopy(getColumnName(), getAD_Reference_ID()));
			}
		} else {
			//	Set field Length
			setFieldLength(DisplayType.getDBDataLength(this));
		}
		
		//	BR [ 9223372036854775807 ]
		//  Skip the validation if this is a Direct Load (from a migration) or the Element is changing.
		//  TODO This may cause problems with packin - the table may not be visible causing validLookup to
		//  throw an exception
		if (!isDirectLoad() 
		    && (this.get_Value(MColumn.COLUMNNAME_AD_Element_ID).equals(get_ValueOld(MColumn.COLUMNNAME_AD_Element_ID))))
			validLookup(getColumnName(), getAD_Reference_ID(), getAD_Reference_Value_ID());
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
		//	Encrypted
		if (isEncrypted()) 
		{
			int dt = getAD_Reference_ID();
			if (isKey() || isParent() || isStandardColumn()
				|| isVirtualColumn() || isIdentifier() || isTranslated()
				|| DisplayType.isLookup(dt) || DisplayType.isLOB(dt)
				|| "DocumentNo".equalsIgnoreCase(getColumnName())
				|| "Value".equalsIgnoreCase(getColumnName())
				|| "Name".equalsIgnoreCase(getColumnName()))
			{
				log.warning("Encryption not sensible - " + getColumnName());
				setIsEncrypted(false);
			}
		}	
		
		//	Sync Terminology
		if ((newRecord || is_ValueChanged ("AD_Element_ID")) 
			&& getAD_Element_ID() != 0)
		{
			M_Element element = new M_Element (getCtx(), getAD_Element_ID(), get_TrxName());
			if(element.is_new()) {
				throw new AdempiereException("@AD_Element_ID@ " + getAD_Element_ID () + " @NotFound@");
			}
			setColumnName (element.getColumnName());
			setName (element.getName());
			setDescription (element.getDescription());
			setHelp (element.getHelp());
		}
		
		//  In beforeSave, determine if a sync of the database is required.
		//  The sync is performed in the after save or via a process.  
		//  To not duplicate columns on name change when syncing by a process,
		//  a copy of the old name is required, if it changed.
		//  Important: Can't call getAD_Table() if we just made a change to AD_Table
		//  so test for the table ID before testing getAD_Table().isView()
		if (!isVirtualColumn() 
			&& (get_Table_ID() == I_AD_Table.Table_ID || get_Table_ID() == I_AD_Column.Table_ID || !this.getAD_Table().isView()) 
			&& !isDirectLoad()
				&& (newRecord  
				|| is_ValueChanged(MColumn.COLUMNNAME_ColumnName)
				|| is_ValueChanged(MColumn.COLUMNNAME_AD_Reference_ID)
				|| is_ValueChanged(MColumn.COLUMNNAME_FieldLength)
				|| is_ValueChanged(MColumn.COLUMNNAME_IsMandatory)
				|| is_ValueChanged(MColumn.COLUMNNAME_IsKey)
				|| is_ValueChanged(MColumn.COLUMNNAME_DefaultValue)
				|| is_ValueChanged(MColumn.COLUMNNAME_ColumnSQL)
				|| is_ValueChanged(MColumn.COLUMNNAME_IsParent))) 
		{
			if (isRequiresSync())
			{
				// We already have a change prepared.
				if (getColumnName().equals(getNameOldValue()))
				{
					//  We had a change ready and the user has changed the name back
					//  to what it was.  We can cancel the name change but we can't cancel
					//  the sync as the other columns may have changed.
					setNameOldValue(null);

				}
			}
			else
			{
				setRequiresSync(true);
				setNameOldValue((String) this.get_ValueOld(MColumn.COLUMNNAME_ColumnName));
			}
		}

		return true;
	}	//	beforeSave
	
	/**
	 * Verify if is a lookup valid
	 * @param p_ColumnName
	 * @param p_AD_Reference_ID
	 * @param p_AD_Reference_Value_ID
	 * @return
	 */
	public static void validLookup(String p_ColumnName, int p_AD_Reference_ID, int p_AD_Reference_Value_ID) {

		//	Valid 
		if(p_ColumnName == null
				||p_ColumnName.trim().length() == 0
				|| !DisplayType.isLookup(p_AD_Reference_ID)) {
			return;
		} else {
			String m_TableName = p_ColumnName.replace("_ID", "");
			//	BR [ 185 ]
			if(p_AD_Reference_ID == DisplayType.TableDir) {
				if(!p_ColumnName.endsWith("_ID"))
					throw new AdempiereException("@Reference@ @of@ @ColumnName@ @NotValid@");
				//	Valid Table
				MTable table = MTable.get(Env.getCtx(), m_TableName);
				//	Valid Exists table
				if(table == null)
					throw new AdempiereException("@AD_Table_ID@ @NotFound@");
			} else if(p_AD_Reference_ID == DisplayType.Table
					|| p_AD_Reference_ID == DisplayType.Search) {
				if(p_AD_Reference_Value_ID == 0
						&& !M_Element.isLookupColumnName(p_ColumnName, p_AD_Reference_ID))
					throw new AdempiereException("@AD_Reference_Value_ID@ @IsMandatory@");
			} else if(p_AD_Reference_ID == DisplayType.List) {
				if(p_AD_Reference_Value_ID == 0) {
					throw new AdempiereException("@AD_Reference_Value_ID@ @IsMandatory@");
				}
			}
		}
	}
	
	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		//  #213 - AutoSync - check if we should auto-sync the column and table.
		//  Autosync is controlled by a System Config entry.  The other sync 
		//  options are to use the button in the Column tab or the Table and Column
		//  window or the Sync All Tables and Columns process in the Application
		//  Dictionary window.
		if (isRequiresSync() && isAutoSync())
		{

			syncDatabase();
					
		}
		
		//	Update Fields
		if (!newRecord)
		{
			if (   is_ValueChanged(MColumn.COLUMNNAME_Name)
				|| is_ValueChanged(MColumn.COLUMNNAME_Description)
				|| is_ValueChanged(MColumn.COLUMNNAME_Help)
				) {
				StringBuffer whereClause = new StringBuffer("AD_Column_ID=? ")
												.append(" AND IsCentrallyMaintained=? ");
				List<Object> parameters = new ArrayList<>();
				parameters.add(this.getAD_Column_ID());
				parameters.add(true);
				List<MField> fields = new Query(getCtx(), MField.Table_Name, whereClause.toString(), get_TrxName())
						.setParameters(parameters)
						.list();
				int no = 0;
				for (MField field: fields)
				{
					field.setName(getName());
					field.setDescription(getDescription());
					field.setHelp(getHelp());
					field.saveEx();
					no++;
				}
				//	
				log.fine("afterSave - Fields updated #" + no);
			}
		}
		return success;
	}	//	afterSave
	
	/**
	 *  Get SQL Add command, a series of separated SQL statements that will add 
	 *  this column to the given table. Adding a new primary key to a table with 
	 *  existing records through this method may cause errors and should be done
	 *  in stages to add the column, set the key values and finally apply the 
	 *  constraint.    
	 *  
	 *	@param table the target table
	 *	@return sql
	 */
	public String getSQLAdd (MTable table)
	{
		if ( isVirtualColumn() )
			return null;
		
		StringBuffer sql = null;
		
		//  #213 - enable autosync
		//  If the table is new or the column can be null, it can be added with a single statement
		if (!isMandatory() || isNewTable) {
			sql = new StringBuffer ("ALTER TABLE ")
				.append(table.getTableName())
				.append(" ADD ").append(getSQLDDL());
		}
		else { // isMandatory() && !isNewTable
			//  If the table is not new (it has existing records) and the column is mandatory
			//  then the new column values should be set to the defaults in the existing 
			//  records.  This takes three statements:
			//   1. Add the column to the table
			//   2. Set the default value in all records
			//   3. Make the column mandatory
			
			// SQL to add the column
			sql = new StringBuffer ("ALTER TABLE ")
				.append(table.getTableName())
				.append(" ADD ").append(getColumnName())
				.append(" ").append(getSQLDataType());
				//	Inline Constraint
				if (getAD_Reference_ID() == DisplayType.YesNo)
					sql.append(" CHECK (").append(getColumnName()).append(" IN ('Y','N'))");
			sql.append(DB.SQLSTATEMENT_SEPARATOR);
			
			//  Set the default value for new records
			String defaultValue = getDefaultValueSQL();
			sql.append("ALTER TABLE ")
			.append(table.getTableName())
			.append(" MODIFY ").append(getColumnName())
			.append(" ").append(getSQLDataType())
			.append(" DEFAULT ").append(defaultValue)
			.append(DB.SQLSTATEMENT_SEPARATOR);

			//  Set the default value in all existing records
			if (defaultValue != null && defaultValue.length() > 0 && !defaultValue.equals("NULL"))
			{
				StringBuffer sqlSet = new StringBuffer("UPDATE ")
					.append(table.getTableName())
					.append(" SET ").append(getColumnName())
					.append("=").append(defaultValue)
					.append(" WHERE ").append(getColumnName()).append(" IS NULL");
				sql.append(sqlSet).append(DB.SQLSTATEMENT_SEPARATOR);
			}

			//  Set the column to Not Null - makes it mandatory
			sql.append("ALTER TABLE ")
			.append(table.getTableName())
			.append(" MODIFY ").append(getColumnName())
			.append(" NOT NULL")
			.append(DB.SQLSTATEMENT_SEPARATOR);

		}

		//  Finally, add the constraint, if any.  The constraints should 
		//  only apply to primary keys.  Note that adding a new primary 
		//  key column to a table with existing records will likely require 
		//  some manual effort to define the key values before the constraint
		//  can be applied.
		String constraint = getConstraint(table.getTableName());
		if (constraint != null && constraint.length() > 0) {
			sql.append("ALTER TABLE ")
			.append(table.getTableName())
			.append(" ADD ").append(constraint);
		}

		return sql.toString();
	}	//	getSQLAdd

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
			sql.append(" DEFAULT ").append(getDefaultValueSQL());

		//	Inline Constraint
		if (getAD_Reference_ID() == DisplayType.YesNo)
			sql.append(" CHECK (").append(getColumnName()).append(" IN ('Y','N'))");

		//	Null
		if (isMandatory())
			sql.append(" NOT NULL");
		return sql.toString();
	}	//	getSQLDDL	
	
	/**
	 * Get a string representing the default value of this column for use in 
	 * sql statements
	 * @return a string suitable for use as a default value in an SQL statement
	 */
	private String getDefaultValueSQL() {
		//	Default
		String defaultValue = getDefaultValue();
		if (defaultValue != null && defaultValue.length() > 0)
		{
			
			if (defaultValue.indexOf('@') != -1		//	no variables
				|| defaultValue.startsWith("#")		//	no context - eg. #AD_Client_ID
				|| ((DisplayType.isID(getAD_Reference_ID()) && defaultValue.equals("-1") ) ) )  // not for ID's with default -1
			{
				defaultValue = "NULL";
			}
			else if (DisplayType.isText(getAD_Reference_ID()) 
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
		}
		else
		{
			// default not defined
			defaultValue = "NULL";
		}
		return defaultValue;
	}

	/**
	 *  Get SQL Modify command
	 *  @param table table
	 *  @param setNullOption generate null / not null statement
	 *  @return sql separated by ;
	 */
	public String getSQLModify (MTable table, boolean setNullOption)
	{
		return getSQLModify(table, null, setNullOption);
	}
	
	/**
	 *  Get SQL Modify command
	 *  @param table table
	 *  @param oldColumnName the oldColumnName or null if there is no change.
	 *  @param setNullOption generate null / not null statement
	 *  @return sql separated by ;
	 */
	public String getSQLModify (MTable table, String oldColumnName, boolean setNullOption)
	{
		StringBuffer sql = new StringBuffer();
		if (oldColumnName != null) {
			// Rename the column in the database
			sql = new StringBuffer("ALTER TABLE ")
					.append(table.getTableName())
					.append(" RENAME COLUMN ").append(oldColumnName).append(" TO ")
					.append(getColumnName())
					.append(DB.SQLSTATEMENT_SEPARATOR);
		}

		//  TODO handle changes to the constraints and defaults on key columns.  
		//  Modifying the defaults on key columns requires the drop of the constraint.  
		//  For now, just rename ID columns and return, ignoring other changes.
		if (this.isKey() || (getColumnName().endsWith("_ID") 
				&& getColumnName().replace("_ID", "").equals(table.get_TableName()))) {
			if (sql.length() == 0)
				return null;
			return sql.toString();
		}

		StringBuffer sqlBase = new StringBuffer("ALTER TABLE ");
		if(DB.isOracle() && "CLOB".equals(getSQLDataType())) {

			// ALTER TABLE <TABLE_NAME> ADD <COLUMN_NAME>_T CLOB;
			sqlBase.append(table.getTableName())
					.append(" ADD ").append(getColumnName()).append("_T CLOB")
					.append(DB.SQLSTATEMENT_SEPARATOR);

			// UPDATE <TABLE_NAME> SET <COLUMN_NAME>_T = <COLUMN_NAME>;
			sqlBase.append("UPDATE ").append(table.getTableName()).append(" SET ")
					.append(getColumnName()).append("_T = ")
					.append(getColumnName()).append(DB.SQLSTATEMENT_SEPARATOR);

			// ALTER TABLE <TABLE_NAME> DROP(<COLUMN_NAME>);
			sqlBase.append("ALTER TABLE ").append(table.getTableName()).append(" DROP(")
					.append(getColumnName()).append(")")
					.append(DB.SQLSTATEMENT_SEPARATOR);

			// ALTER TABLE <TABLE_NAME> RENAME COLUMN <COLUMN_NAME>_T TO <COLUMN_NAME>;
			sqlBase.append("ALTER TABLE ").append(table.getTableName()).append(" RENAME COLUMN ")
					.append(getColumnName()).append("_T TO ").append(getColumnName())
					.append(DB.SQLSTATEMENT_SEPARATOR);

			//	Default
			String defaultValue = getDefaultValueSQL();
			sqlBase.append(" ALTER TABLE ")
					.append(table.getTableName())
					.append(" MODIFY ")
					.append(getColumnName())
					.append(" DEFAULT ")
					.append(defaultValue)
					.append(DB.SQLSTATEMENT_SEPARATOR);

			sql.append(sqlBase);
		} else {

			// For non ID columns, we can manage defaults and other stuff
			sqlBase.append(table.getTableName())
					.append(" MODIFY ").append(getColumnName());
			//	Default
			String defaultValue = getDefaultValueSQL();
			sql.append(sqlBase).append(" ").append(getSQLDataType())
					.append(" DEFAULT ").append(defaultValue);

			//	Constraint
			//  TODO - rename inline constraints?

			//	Null Values - set to the default everywhere the value is currently null
			if (isMandatory() && defaultValue != null && defaultValue.length() > 0 && !defaultValue.equals("NULL")) {
				StringBuffer sqlSet = new StringBuffer("UPDATE ")
						.append(table.getTableName())
						.append(" SET ").append(getColumnName())
						.append("=").append(defaultValue)
						.append(" WHERE ").append(getColumnName()).append(" IS NULL");
				sql.append(DB.SQLSTATEMENT_SEPARATOR).append(sqlSet);
			}

			//	Null
			if (setNullOption)  // TODO Fails if there is a constraint on this column.
			{
				//  This may fail if the column has a calculated default and there are currently null values
				//  in the column.  Solution is to set the default to a specific value, sync then reset to
				//  the calculated default or manage the update via the database directly.
				StringBuffer sqlNull = new StringBuffer(sqlBase);
				if (isMandatory())
					sqlNull.append(" NOT NULL");
				else
					sqlNull.append(" NULL");
				sql.append(DB.SQLSTATEMENT_SEPARATOR).append(sqlNull);
			}
		}
		//
		return sql.toString();
	}	//	getSQLModify

	/**
	 * 	Get SQL Data Type
	 *	@return e.g. NVARCHAR2(60)
	 */
	public String getSQLDataType()
	{
		String columnName = getColumnName();
		int dt = getAD_Reference_ID();
		return DisplayType.getSQLDataType (dt, columnName, getFieldLength(), getAD_Reference_Value_ID());
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
	 * 	String Representation
	 *	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer ("MColumn[");
		sb.append (get_ID()).append ("-").append (getColumnName()).append ("]");
		return sb.toString ();
	}	//	toString

	/**
	 * Get Column ID
	 * @param tableName
	 * @param columnName
     * @return
     */
	public static int getColumn_ID(String tableName,String columnName) {
		int m_table_id = MTable.getTable_ID(tableName);
		if (m_table_id == 0)
			return 0;
			
		int retValue = 0;
		String SQL = "SELECT AD_Column_ID FROM AD_Column WHERE AD_Table_ID = ?  AND columnname = ?";
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(SQL, null);
			pstmt.setInt(1, m_table_id);
			pstmt.setString(2, columnName);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				retValue = rs.getInt(1);
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			s_log.log(Level.SEVERE, SQL, e);
			retValue = -1;
		}
		return retValue;
	}
	//end vpj-cd e-evolution
	
	/**
	* Get Table Id for a column
	* @param ctx context
	* @param AD_Column_ID id
	* @param trxName transaction
	* @return MColumn
	*/
	public static int getTable_ID(Properties ctx, int AD_Column_ID, String trxName)
	{
		String sqlStmt = "SELECT AD_Table_ID FROM AD_Column WHERE AD_Column_ID=?";
		return DB.getSQLValue(trxName, sqlStmt, AD_Column_ID);
	}

	/**
	 * Sync this column with the database
	 * @return Error Message or SQL used to make the change
	 */
	public String syncDatabase()
	{
		return syncDatabase(null);
	}
	
	public String syncDatabase(Connection conn) {
		return syncDatabase(conn, false);
	}
	
	public String syncDatabase(Connection conn, boolean isOnlyReport) {
	
		s_log.config("");
				
		//  Flag if we should close the connection.
		boolean closeConn = false;
		
		boolean errorOccured = true;

		String returnMessage = "";

		// Shouldn't happen - see beforeSave - but in case
		if (this.isVirtualColumn())
		{
			markSyncComplete();
			return MSG_CannotSyncVirtualColumn;  
		} 
		
		// The table has to be in the cache or a new table will be created
		// so load it explicitly from the cache and not with new MTable ...
		MTable table = MTable.get(getCtx(), getAD_Table_ID());
		if (table.get_ID() == 0)
			throw new AdempiereException("@NotFound@ @AD_Table_ID@ " + getAD_Table_ID());
		
		// Shouldn't happen - see beforeSave
		if (table.isView())
		{
			markSyncComplete();
			return MSG_CannotSyncView; 
		}
				
		table.set_TrxName(get_TrxName());  // otherwise table.getSQLCreate may miss current column
		
		//	Find Column in Database
		ResultSet rs= null;
		try {

			if (this.getAD_Table_ID() == MColumn.Table_ID
				|| this.getAD_Table_ID() == MTable.Table_ID)
			{
				conn = DB.getConnectionRO();
				closeConn = true;
			}
			else if (conn == null)
			{
				if (get_TrxName() != null && !get_TrxName().isEmpty())
				{
					conn = Trx.get(get_TrxName(), true).getConnection();
					//  Will be closed when the transaction is closed.
				}
				else
				{
					conn = DB.getConnectionRO();
					closeConn = true;
				}
			}
			
			//  Sync the table
			returnMessage = table.syncDatabase(conn, isOnlyReport);
			if (MColumn.MSG_NoSQLNOChangesMade.equals(returnMessage))
				returnMessage = "";
			else
				returnMessage = table.getTableName() + ": " + returnMessage + "<br>";
			
			// Reload in case the table sync changed something.
			this.load(get_TrxName());

			//  Get the old column name. This could have been set
			//  some time ago.
			String oldColumnName = getNameOldValue();

			if (oldColumnName != null && oldColumnName.equalsIgnoreCase(getColumnName())){ // There is no name change
				oldColumnName = null;
			}

			//  The query of database metadata will not see tables or columns changed/added in 
			//  the current transaction. For table name changes, the change in primary 
			//  key column name is done within the MTable model.
			DatabaseMetaData md = conn.getMetaData();
			String catalog = DB.getDatabase().getCatalog();
			String schema = DB.getDatabase().getSchema();
			String tableName = table.getTableName();
			if (md.storesUpperCaseIdentifiers())
			{
				tableName = tableName.toUpperCase();
			}
			else if (md.storesLowerCaseIdentifiers())
			{
				tableName = tableName.toLowerCase();
			}
			String sql = null;
			Boolean oldColumnExists = false;
			Boolean currentColumnExists = false;
			Boolean currentNotNull = false;
			int currentDataType = 0;
			int currentColumnSize = 0;
			String currentTypeName = null;
			String currentColumnDefault = null;
			String columnDefault = getDefaultValueSQL();
			//
			//  Find the table.  Tables can be new with no columns assigned.
			//  This is a problem if the table name changed in this transaction - 
			//  the table will not be found and a new table will be created.
			isNewTable = true;
			rs = md.getTables(catalog, schema, tableName, null);
			while (rs.next())
			{
				isNewTable = false;
			}
			rs.close();
			
			//  The table should have been sync'd already so isNewTable should
			//  be false.  In case, test and create it.
			if (isNewTable) {
				sql = table.getSQLCreate ();
			}
			else {
				rs = md.getColumns(catalog, schema, tableName, null);
				while (rs.next())
				{
					String columnName = rs.getString ("COLUMN_NAME");
					
					if (oldColumnName != null && columnName.equalsIgnoreCase(oldColumnName)) {
						oldColumnExists = true;
					}
					
					if (columnName.equalsIgnoreCase(getColumnName())) {
						currentColumnExists = true;
						currentNotNull = DatabaseMetaData.columnNoNulls == rs.getInt("NULLABLE");
						//	update existing column
						currentDataType = rs.getInt("DATA_TYPE");
						currentTypeName = rs.getString("TYPE_NAME");
						currentColumnSize = rs.getInt("COLUMN_SIZE");
						currentColumnDefault = rs.getString("COLUMN_DEF");
					}
				}
				rs.close();
				rs = null;

				// Check if the column is part of the primary key of the table
				boolean isDatabaseKey = false;
				rs = md.getPrimaryKeys(catalog, schema, tableName);
				while (rs.next())
				{
					if(this.getColumnName().equalsIgnoreCase(rs.getString(4)))
					{
						isDatabaseKey = true;
						break;
					}
				}
				
				if (isDatabaseKey && !isKey() && !isParent())
				{
					returnMessage += MSG_ColumnPartOfKeyButNotKeyOrParentInAD + "<br>";
				}

				//	No existing column
				if (!oldColumnExists && !currentColumnExists) {
					returnMessage += MSG_ColumnDoesNotExistAndWillBeAdded + "<br>";
					sql = getSQLAdd(table);
				}
				// Name change or no name change
				else if (oldColumnExists && !currentColumnExists || !oldColumnExists && currentColumnExists) {
					//  Update the current column
					//  Check if we really need to do this.
					//  Note that the mandatory column implies the database column
					//  should be not null. The default value can be null, if using
					//  a calculated value for example.  There may be errors if 
					//  changing a non mandatory column to mandatory if the table
					//  already has rows with null values in this column.  In this
					//  case, the null values can be set to a non null value by setting
					//  the default logic to a static value before syncing the column.
					//  Then change the default logic to the calculated equation and 
					//  sync again.
					boolean withDefault = (columnDefault != null && columnDefault.length() > 0 && !columnDefault.equals("NULL"));
					if (oldColumnName != null) {
						returnMessage += MSG_ColumnNameChanged + " " + oldColumnName + " -> " + getColumnName() + "<br>";
					}
					if (!DisplayType.isSameType(this, currentDataType, currentColumnSize)) {
						
						returnMessage += MSG_DataTypeChanged + " " + currentTypeName + "(" + currentColumnSize + ") -> " 
											+ getSQLDataType() + "<br>";
					}
					if ((isMandatory() != currentNotNull)) {
						returnMessage += MSG_ManadatoryValueChanged + " " + currentNotNull + " -> " + isMandatory() + "<br>";
					}
					if (isMandatory() && withDefault 		//  If mandatory with a default, the default should match the database
									&& currentColumnDefault != null 
									&& !currentColumnDefault.startsWith(columnDefault)) {
						returnMessage += MSG_DefaultValueChanged + " " + currentColumnDefault + " -> " + columnDefault + "<br>";
					}
					
 					if(oldColumnName != null
 							|| !DisplayType.isSameType(this, currentDataType, currentColumnSize)
							|| (isMandatory() != currentNotNull) 	//  If the column is not mandatory the database column should allow null
							|| (isMandatory() && withDefault 		//  If mandatory with a default, the default should match the database
									&& currentColumnDefault != null 
									&& !currentColumnDefault.startsWith(columnDefault))) {  // TODO - what about Y/N fields?
						
						log.info(tableName + "." + getColumnName() + ": currentDataType: " + currentDataType + ", currentColumnSize: " + currentColumnSize
								+ ", isMandatory(): " + isMandatory() + ", currentNotNull: " + currentNotNull
								+ ", currentColumnDefault: " + currentColumnDefault + ", columnDefault: " + columnDefault);
						sql = getSQLModify(table, null, isMandatory() != currentNotNull); // Can return a null string
					}
				}
				// Both exist - which is a problem - so throw an error
				else if (oldColumnExists && currentColumnExists) {
					throw new AdempiereException(Msg.translate(getCtx(), MSG_SyncErrorColumnAlreadyExists) + " " + oldColumnName + "->" + getColumnName());
				}
			}			
			
			String trxName = get_TrxName();
			
			if ( sql != null )
			{
				returnMessage += "SQL: " + sql;

				if (!isOnlyReport)
				{
					try {
						log.info("Syncing column " + this.getColumnName() + ": " + sql);
						log.info("Transaction: " + trxName + ", get_TrxName(): " + get_TrxName());
						CLogger.resetLast();
						DB.executeUpdateMultiple(sql, false, trxName);
						DB.commit(true, trxName);
						Exception error = CLogger.retrieveException();
						if (error == null)
						{
							returnMessage += "<br>" + MSG_SQLSuccessfullyApplied;
							errorOccured = false;
						}
						else
						{
							returnMessage += "<br>" + "@Error@ " + MSG_ErrorSyncingColumn + " " + error.toString();
						}
					}
					catch (Exception e) 
					{
					
						log.severe("Manual intervention required: Cannot sync column: " + getColumnName());
						returnMessage +=  "<br>" + "@Error@: " + MSG_ErrorSyncingColumn + " " + tableName + "." + getColumnName(); 
						returnMessage += "<br>" + e.getLocalizedMessage();
					}
					
					if (getAD_Table_ID() == I_AD_Column.Table_ID || getAD_Table_ID() == I_AD_Table.Table_ID)
					{
						//  #2428 - Need to explicitly identify the columns in AD_Table and AD_Column
						s_log.config("There has been a change in the AD_Table or AD_Column structure.  Resetting the columns in POInfo");
						POInfo.resetPOInfoColumnList();
					}
				
					// Remove the old table definition from cache 
					POInfo.removeFromCache(getAD_Table_ID());
					
				}
				//	Throw message
				if(errorOccured) {
					throw new DBException(returnMessage);
				}
			}
			else
			{
				returnMessage += MSG_NoSQLNOChangesMade; 
			}
		} 
		catch (SQLException|DBException e ) {
			if (e.getMessage()!= null && e.getMessage().contains("already exists")) {  // TODO will this work in other languages?
				returnMessage +=  MSG_ColumnAlreadyExists; 
			}
			else {	
				throw new AdempiereException(e);
			}
		}
		finally {
			DB.close(rs);
			if (closeConn && conn != null) {
				try {
					conn.close();
				} catch (Exception e) {}
				conn = null;
			}
		}

		//  Success.  Remove the sync flags from this record
		if (!isOnlyReport && !errorOccured)
			markSyncComplete();

		return returnMessage;

	}

	/**
	 * Verify if a columnName is suggested for selection column
	 * @param columnName
	 * @param caseSensitive
	 * @return
	 */
	public static boolean isSuggestSelectionColumn(String columnName, boolean caseSensitive) {
		if (Util.isEmpty(columnName, true))
			return false;
		//
        if (columnName.equals("Value") || (!caseSensitive && columnName.equalsIgnoreCase("Value")))
            return true;
        else if (columnName.equals("Name") || (!caseSensitive && columnName.equalsIgnoreCase("Name")))
            return true;
        else if (columnName.equals("DocumentNo") || (!caseSensitive && columnName.equalsIgnoreCase("DocumentNo")))
            return true;
        else if (columnName.equals("Description") || (!caseSensitive && columnName.equalsIgnoreCase("Description")))
            return true;
        else if (columnName.indexOf("Name") != -1
        		|| (!caseSensitive && columnName.toUpperCase().indexOf("Name".toUpperCase()) != -1) )
            return true;
        else
        	return false;
	}
	
	/**
	 * Verify if the value changed required a synchronizing on DB
	 * @param columnName
	 * @return
	 */
	public static boolean isForSynchronizeColumn(String columnName) {
		if (Util.isEmpty(columnName, true))
			return false;
		//	Verify
		return columnName.equals(I_AD_Column.COLUMNNAME_IsMandatory)
				|| columnName.equals(I_AD_Column.COLUMNNAME_IsParent)
				|| columnName.equals(I_AD_Column.COLUMNNAME_ColumnName)
				|| columnName.equals(I_AD_Column.COLUMNNAME_AD_Reference_ID)
				|| columnName.equals(I_AD_Column.COLUMNNAME_FieldLength)
				|| columnName.equals(I_AD_Column.COLUMNNAME_IsKey)
				|| columnName.equals(I_AD_Column.COLUMNNAME_DefaultValue);
	}
	
	/**
	 * Verify if column name and display type allow copy
	 * @param ctx
	 * @param p_AD_Column_ID
	 * @return
	 */
	public static boolean isAllowCopy(Properties ctx, int p_AD_Column_ID) {
		MColumn column = MColumn.get(ctx, p_AD_Column_ID);
		//	Set values from column
		if(column != null) {
			//	for Allow copy
			isAllowCopy(column.getColumnName(), column.getAD_Reference_ID());
			//	
			return true;
		}
		//	Default return
		return false;
	}
	
	/**
	 * Verify if is allow copy of column
	 * @param columnName
	 * @param referenceId
	 * @return
	 */
	public static boolean isAllowCopy(String columnName, int referenceId) {
		if(DisplayType.ID == referenceId
				|| DisplayType.Location == referenceId
				|| M_Element.isReservedColumnName(columnName)) {
			return false;
		}
		//	Default
		return true;
	}

	/**
	 * Determine the minimum allowable value for the KeyColumn.  Columns that end
	 * in "_ID" typically can have values of null or > 0.  In a few tables, the value
	 * "0" is valid.
	 * @param columnName
	 * @return
	 */
	public static Integer getKeyColumnFirstValue(String columnName) {

		Integer firstOK = null;
		
		if (columnName.endsWith("_ID"))
		{
			//	check special column  TODO hard-coded. Add to AD_Column to make this configurable
			if (columnName.equals("AD_Client_ID") || columnName.equals("AD_Org_ID")
				|| columnName.equals("Record_ID") || columnName.equals("C_DocType_ID")
				|| columnName.equals("Node_ID") || columnName.equals("AD_Role_ID")
				|| columnName.equals("M_AttributeSet_ID") || columnName.equals("M_AttributeSetInstance_ID")
// Coming soon	|| columnName.equals("M_MPolicyTicket_ID")
				|| columnName.equals("M_Warehouse_ID")) {
				firstOK = Integer.valueOf(0);
			}
			else
				firstOK = Integer.valueOf(1);
		}
		return firstOK;
	}
	
	/**
	 * Check if the column changes/additions should be sync'd automatically or by the 
	 * {@link org.compiere.process.ColumnSync} process. New tables are added along
	 * with the columns.  Table name changes are always synchronized automatically.
	 * <p>The auto sync can be configured by setting a system configurator for 
	 * {@link #SYSCONFIG_DATABASE_AUTO_SYNC} to something other the 'N', the default.
	 * @return true if the sync is automatic.
	 */
	public static boolean isAutoSync() {
		return !"N".equals(MSysConfig.getValue(SYSCONFIG_DATABASE_AUTO_SYNC,"N",Env.getAD_Client_ID(Env.getCtx())));
	}

	/**
	 * Set the flags for isSyncRequired and NameOldValue when the sync is complete 
	 * or not required. 
	 */
	public void markSyncComplete() {
		if (isRequiresSync() || getNameOldValue() != null)
		{
			setIsDirectLoad(true);
			set_ValueNoCheck(COLUMNNAME_RequiresSync, Boolean.valueOf(false));
			set_ValueNoCheck(COLUMNNAME_NameOldValue, null);
			saveEx();
		}			
	}

	
}	//	MColumn
