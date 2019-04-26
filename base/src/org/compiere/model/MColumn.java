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

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
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
 */
public class MColumn extends X_AD_Column
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 3455817869952578951L;

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
		Integer key = new Integer (AD_Column_ID);
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
			setAD_Column(getCtx(), this, get_TrxName());
			//Create Document No or Value sequence
			if (!isDirectLoad() && "DocumentNo".equals(getColumnName()) || !isDirectLoad() && "Value".equals(getColumnName()))
			{
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
			//	For Is Allow Copy
			if(!isDirectLoad()) {
				setIsAllowCopy(MColumn.isAllowCopy(getColumnName(), getAD_Reference_ID()));
			}
		}
		//	Set field Length
		setFieldLength(DisplayType.getDBDataLength(this));
		
		//	BR [ 9223372036854775807 ]
		//  Skip the validation if this is a Direct Load (from a migration) or the Element is changing.
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
	 * 	Get SQL Add command
	 *	@param table table
	 *	@return sql
	 */
	public String getSQLAdd (MTable table)
	{
		if ( isVirtualColumn() )
			return null;
		
		StringBuffer sql = new StringBuffer ("ALTER TABLE ")
			.append(table.getTableName())
			.append(" ADD ").append(getSQLDDL());
		String constraint = getConstraint(table.getTableName());
		if (constraint != null && constraint.length() > 0) {
			sql.append(DB.SQLSTATEMENT_SEPARATOR).append("ALTER TABLE ")
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
	 * Get Default Value for SQL
	 * @return
	 */
	private String getDefaultValueSQL() {
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
			//	
			return defaultValue;
		}
		//	default
		return null;
	}
	
	/**
	 * 	Get SQL Modify command
	 *	@param table table
	 *	@param setNullOption generate null / not null statement
	 *	@return sql separated by ;
	 */
	public String getSQLModify (MTable table, boolean setNullOption) {
		StringBuffer sql = new StringBuffer();
		StringBuffer sqlBase = new StringBuffer ("ALTER TABLE ")
			.append(table.getTableName())
			.append(" MODIFY ").append(getColumnName());
		
		//	Default
		StringBuffer sqlDefault = new StringBuffer(sqlBase)
			.append(" ").append(getSQLDataType());
		String defaultValue = getDefaultValueSQL();
		if (defaultValue != null) {
			sqlDefault.append(" DEFAULT ").append(defaultValue);
		} else {
			if (!isMandatory()) {
				sqlDefault.append(" DEFAULT NULL ");
			}
		}
		sql.append(sqlDefault);
		
		//	Constraint

		//	Null Values
		if (isMandatory() && defaultValue != null && defaultValue.length() > 0)
		{
			StringBuffer sqlSet = new StringBuffer("UPDATE ")
				.append(table.getTableName())
				.append(" SET ").append(getColumnName())
				.append("=").append(defaultValue)
				.append(" WHERE ").append(getColumnName()).append(" IS NULL");
			sql.append(DB.SQLSTATEMENT_SEPARATOR).append(sqlSet);
		}
		
		//	Null
		if (setNullOption)
		{
			StringBuffer sqlNull = new StringBuffer(sqlBase);
			if (isMandatory())
				sqlNull.append(" NOT NULL");
			else
				sqlNull.append(" NULL");
			sql.append(DB.SQLSTATEMENT_SEPARATOR).append(sqlNull);
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
	 * 	get Column ID
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
	 * @return
	 */
	public String syncDatabase()
	{

		MTable table = new MTable(getCtx(), getAD_Table_ID(), get_TrxName());
		if (table.isView())
			return "Cannot sync view";
		table.set_TrxName(get_TrxName());  // otherwise table.getSQLCreate may miss current column
		if (table.get_ID() == 0)
			throw new AdempiereException("@NotFound@ @AD_Table_ID@ " + getAD_Table_ID());

		//	Find Column in Database
		Connection conn = null;
		try {
			conn = DB.getConnectionRO();
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
			int noColumns = 0;
			boolean existsColumn = false;
			String sql = null;
			//
			ResultSet rs = md.getColumns(catalog, schema, tableName, null);
			while (rs.next())
			{
				noColumns++;
				String columnName = rs.getString ("COLUMN_NAME");
				if (!columnName.equalsIgnoreCase(getColumnName()))
					continue;
				//	
				existsColumn = true;
				//	update existing column
				boolean currentNotNull = DatabaseMetaData.columnNoNulls == rs.getInt("NULLABLE");
				int currentDataType = rs.getInt("DATA_TYPE");
				int currentColumnSize = rs.getInt("COLUMN_SIZE");
				String currentColumnDef = rs.getString("COLUMN_DEF");
				String columnDef = getDefaultValueSQL();
				if(!DisplayType.isSameType(this, currentDataType, currentColumnSize)
						|| isMandatory() != currentNotNull
						|| (currentColumnDef != null 
							&& columnDef != null 
							&& !currentColumnDef.startsWith(columnDef))) {
					sql = getSQLModify(table, isMandatory() != currentNotNull);
				}
				break;
			}
			rs.close();
			rs = null;

			//	No Table
			if (noColumns == 0)
				sql = table.getSQLCreate ();
			//	No existing column
			else if (sql == null
					&& !existsColumn)
				sql = getSQLAdd(table);
			
			if ( sql == null )
				return "OK";
			
			if (sql.indexOf(DB.SQLSTATEMENT_SEPARATOR) == -1)
			{
				DB.executeUpdateEx(sql, null);
			}
			else
			{
				String statements[] = sql.split(DB.SQLSTATEMENT_SEPARATOR);
				for (int i = 0; i < statements.length; i++)
				{
					DB.executeUpdateEx(statements[i], null);
				}
			}
			
			// Remove the old table definition from cache 
			POInfo.removeFromCache(getAD_Table_ID());
			return sql;

		} 
		catch (SQLException e) {
			throw new AdempiereException(e);
		}
		finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {}
			}
		}
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
	
}	//	MColumn
