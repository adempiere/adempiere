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
 * Contributor(s): Carlos Ruiz - globalqss                                    *
 *****************************************************************************/
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.model.GenericPO;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.compiere.wf.MWFNode;
import org.compiere.wf.MWFNodeNext;
import org.compiere.wf.MWorkflow;

/**
 *	Persistent Table Model
 * <p>
 * Change log:
 * <ul>
 * <li>2007-02-01 - teo_sarca - [ 1648850 ] MTable.getClass works incorrect for table "Fact_Acct"
 * </ul>
 * <ul>
 * <li>2007-08-30 - vpj-cd - [ 1784588 ] Use ModelPackage of EntityType to Find Model Class
 * </ul>
 *  @author Jorg Janke
 *  @author Teo Sarca, teo.sarca@gmail.com
 *  		<li>BF [ 3017117 ] MTable.getClass returns bad class
 *  			https://sourceforge.net/tracker/?func=detail&aid=3017117&group_id=176962&atid=879332
 *  		<li>BF [ 3133032 ] Adempiere is not loading classes from org.compiere.report
 *  			https://sourceforge.net/tracker/?func=detail&aid=3133032&group_id=176962&atid=879332
 *  @author victor.perez@e-evoluton.com, www.e-evolution.com
 *  		<li>FR [ 3426137 ] Smart Browser
 * 			https://sourceforge.net/tracker/?func=detail&aid=3426137&group_id=176962&atid=879335 
 *  		<li>FR [ 3426233 ] New Table should create the required columns
 * 			https://sourceforge.net/tracker/?func=detail&aid=3426233&group_id=176962&atid=879335
 *	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *			<li> FR [ 94 ] "IsDocument" flag in table for create default columns
 *			@see https://github.com/adempiere/adempiere/issues/94
 *			<li> BR [ 304 ] Is Document Attribute in table create columns with bad size
 *			@see https://github.com/adempiere/adempiere/issues/304
 *			<a href="https://github.com/adempiere/adempiere/issues/657">
 * 			@see FR [ 657 ] The tables mark like IsDocument is deleteable</a>
 * 			<a href="https://github.com/adempiere/adempiere/issues/884">
 * 			@see FR [ 884 ] Recent Items in Dashboard (Add new functionality)</a>
 *
 *	@author Trifon Trifon
 *			<li> FR [ 356 ] Decrease verbosity of SQL statement closing lines.
 *			@see https://github.com/adempiere/adempiere/issues/356
 *	
 */
public class MTable extends X_AD_Table
{

	private static final long serialVersionUID = -2367316254623142732L;

	/**	Cache						*/
	private static CCache<Integer,MTable> s_cache = new CCache<Integer,MTable>("AD_Table", 20);
	private static CCache<String,Class<?>> s_classCache = new CCache<String,Class<?>>("PO_Class", 20);

	/**	Columns				*/
	private List<MColumn>	columns = null;
	
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MTable.class);
	
	/**	Packages for Model Classes	*/
	private static final String[]	s_packages = new String[] {

		"org.compiere.model", "org.compiere.wf", 
		"org.compiere.report", // teo_sarca BF[3133032]
		"org.compiere.print", "org.compiere.impexp",
		"compiere.model",			//	globalqss allow compatibility with other plugins 	
		"adempiere.model",			//	Extensions
		"org.adempiere.model"
	};
	
	/**	Special Classes				*/
	private static final String[]	s_special = new String[] {
		"AD_Element", "org.compiere.model.M_Element",
		"AD_Registration", "org.compiere.model.M_Registration",
		//	Yamel Senih [ 9223372036854775807 ]
		//	Change to Default
//		"AD_Tree", "org.compiere.model.MTree_Base",
		//	End Yamel Senih
		"R_Category", "org.compiere.model.MRequestCategory",
		"GL_Category", "org.compiere.model.MGLCategory",
		"K_Category", "org.compiere.model.MKCategory",
		"C_ValidCombination", "org.compiere.model.MAccount",
		"C_Phase", "org.compiere.model.MProjectTypePhase",
		"C_Task", "org.compiere.model.MProjectTypeTask",
		"AD_View_Column", "org.adempiere.model.MViewColumn",
		"AD_View","org.adempiere.model.MView",
		"AD_View_Definition","org.adempiere.model.MViewDefinition",
		"AD_Browse","org.adempiere.model.MBrowse",
		"AD_Browse_Field","org.adempiere.model.MBrowseField",
		"T_Selection","org.adempiere.model.X_T_Selection"
	//	AD_Attribute_Value, AD_TreeNode
	};

	/**
	 * 	Get Table from Cache
	 *	@param ctx context
	 *	@param AD_Table_ID id
	 *	@return MTable
	 */
	public static MTable get (Properties ctx, int AD_Table_ID)
	{
		Integer key = new Integer (AD_Table_ID);
		MTable retValue = s_cache.get (key);
		if (retValue != null && retValue.getCtx() == ctx) {
			return retValue;
		}
		retValue = new MTable (ctx, AD_Table_ID, null);
		if (retValue.get_ID () != 0) {
			s_cache.put (key, retValue);
		}
		return retValue;
	}	//	get

	/**
	 * 	Get Table from Cache
	 *	@param ctx context
	 *	@param tableName case insensitive table name
	 *	@return Table
	 */
    public static MTable get (Properties ctx, String tableName)
	{
		if (tableName == null)
			return null;
		Iterator<MTable> it = s_cache.values().iterator();
		while (it.hasNext())
		{
			MTable retValue = it.next();
			if (tableName.equalsIgnoreCase(retValue.getTableName()) 
					&& retValue.getCtx() == ctx 
				) 
			{
				return retValue;
		}
		}
		//
		MTable retValue = null;
		String sql = "SELECT * FROM AD_Table WHERE UPPER(TableName)=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setString(1, tableName.toUpperCase());
			rs = pstmt.executeQuery ();
			if (rs.next ())
				retValue = new MTable (ctx, rs, null);
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		if (retValue != null)
		{
			Integer key = new Integer (retValue.getAD_Table_ID());
			s_cache.put (key, retValue);
		}
		return retValue;
	}	//	get
	
	/**
	 * 	Get Table Name
	 *	@param ctx context
	 *	@param AD_Table_ID table
	 *	@return tavle name
	 */
	public static String getTableName (Properties ctx, int AD_Table_ID)
	{
		return MTable.get(ctx, AD_Table_ID).getTableName();
	}	//	getTableName


	/**
	 * 	Get Persistence Class for Table
	 *	@param tableName table name
	 *	@return class or null
	 */
	public static Class<?> getClass (String tableName)
	{
		//	Not supported
		if (tableName == null || tableName.endsWith("_Trl"))
			return null;
		
		//check cache
		Class<?> cache = s_classCache.get(tableName);
		if (cache != null) 
		{
			//Object.class indicate no generated PO class for tableName
			if (cache.equals(Object.class))
				return null;
			else
				return cache;
		}

		MTable table = MTable.get(Env.getCtx(), tableName);
		String entityType = table.getEntityType();
		
		//	Import Tables (Name conflict)
		//  Import Tables doesn't manage model M classes, just X_
		if (tableName.startsWith("I_"))
		{
			MEntityType et = MEntityType.get(Env.getCtx(), entityType);
			String etmodelpackage = et.getModelPackage();
			if (etmodelpackage == null || MEntityType.ENTITYTYPE_Dictionary.equals(entityType))
				etmodelpackage = "org.compiere.model"; // fallback for dictionary or empty model package on entity type
			Class<?> clazz = getPOclass(etmodelpackage + ".X_" + tableName, tableName);
			if (clazz != null)
			{
				s_classCache.put(tableName, clazz);
				return clazz;
			}
			s_log.warning("No class for table: " + tableName);
			return null;
		}
		
		//	Special Naming
		for (int i = 0; i < s_special.length; i++)
		{
			if (s_special[i++].equals(tableName))
			{
				Class<?> clazz = getPOclass(s_special[i], tableName);
				if (clazz != null)
				{
					s_classCache.put(tableName, clazz);
					return clazz;
				}
				break;
			}
		}
		
		//begin [ 1784588 ] Use ModelPackage of EntityType to Find Model Class - vpj-cd
		if (!MEntityType.ENTITYTYPE_Dictionary.equals(entityType))
		{
			MEntityType et = MEntityType.get(Env.getCtx(), entityType);
			String etmodelpackage = et.getModelPackage();
			if (etmodelpackage != null)
			{						
				Class<?> clazz = null;
				clazz = getPOclass(etmodelpackage + ".M" + Util.replace(tableName, "_", ""), tableName);
				if (clazz != null) {
					s_classCache.put(tableName, clazz);
					return clazz;
				}
				//Allows extend core clase based original table
				clazz = getPOclass(etmodelpackage + ".M" + tableName.substring(tableName.indexOf("_") + 1 ), tableName);
				if (clazz != null) {
					s_classCache.put(tableName, clazz);
					return clazz;
				}
				clazz = getPOclass(etmodelpackage + ".X_" + tableName, tableName);
				if (clazz != null) {
					s_classCache.put(tableName, clazz);
					return clazz;
				}
				s_log.warning("No class for table with it entity: " + tableName);					
			}
		}	
		//end [ 1784588 ] 

		//	Strip table name prefix (e.g. AD_) Customizations are 3/4
		String className = tableName;
		int index = className.indexOf('_');
		if (index > 0)
		{
			if (index < 3)		//	AD_, A_
				 className = className.substring(index+1);
		}
		//	Remove underlines
		className = Util.replace(className, "_", "");
	
		//	Search packages
		for (int i = 0; i < s_packages.length; i++)
		{
			StringBuffer name = new StringBuffer(s_packages[i]).append(".M").append(className);
			Class<?> clazz = getPOclass(name.toString(), tableName);
			if (clazz != null)
			{
				s_classCache.put(tableName, clazz);
				return clazz;
			}
		}
		
		
		//	Adempiere Extension
		Class<?> clazz = getPOclass("adempiere.model.X_" + tableName, tableName);
		if (clazz != null)
		{
			s_classCache.put(tableName, clazz);
			return clazz;
		}
		
		//hengsin - allow compatibility with compiere plugins
		//Compiere Extension
		clazz = getPOclass("compiere.model.X_" + tableName, tableName);
		if (clazz != null)
		{
			s_classCache.put(tableName, clazz);
			return clazz;
		}

		//	Default
		clazz = getPOclass("org.compiere.model.X_" + tableName, tableName);
		if (clazz != null)
		{
			s_classCache.put(tableName, clazz);
			return clazz;
		}

		//Object.class to indicate no PO class for tableName
		s_classCache.put(tableName, Object.class);
		return null;
	}	//	getClass
	
	/**
	 * Get PO class
	 * @param className fully qualified class name
	 * @return class or null
	 * @deprecated Use {@link #getPOclass(String, String)}
	 */
	private static Class<?> getPOclass (String className)
	{
		return getPOclass(className, null);
	}
	
	/**
	 * Get PO class
	 * @param className fully qualified class name
	 * @param tableName Optional. If specified, the loaded class will be validated for that table name
	 * @return class or null
	 */
	private static Class<?> getPOclass (String className, String tableName)
	{
		try
		{
			Class<?> clazz = Class.forName(className);
			// Validate if the class is for specified tableName
			if (tableName != null)
			{
				String classTableName = clazz.getField("Table_Name").get(null).toString();
				if (!tableName.equals(classTableName))
				{
					s_log.finest("Invalid class for table: " + className+" (tableName="+tableName+", classTableName="+classTableName+")");
					return null;
				}
			}
			//	Make sure that it is a PO class
			Class<?> superClazz = clazz.getSuperclass();
			while (superClazz != null)
			{
				if (superClazz == PO.class)
				{
					s_log.fine("Use: " + className);
					return clazz;
				}
				superClazz = superClazz.getSuperclass();
			}
		}
		catch (Exception e)
		{
		}
		s_log.finest("Not found: " + className);
		return null;
	}	//	getPOclass

	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param AD_Table_ID id
	 *	@param trxName transaction
	 */
	public MTable (Properties ctx, int AD_Table_ID, String trxName)
	{
		super (ctx, AD_Table_ID, trxName);
		if (AD_Table_ID == 0)
		{
		//	setName (null);
		//	setTableName (null);
			setAccessLevel (ACCESSLEVEL_SystemOnly);	// 4
			setEntityType (ENTITYTYPE_UserMaintained);	// U
			setIsChangeLog (false);
			setIsDeleteable (false);
			setIsHighVolume (false);
			setIsSecurityEnabled (false);
			setIsView (false);	// N
			setReplicationType (REPLICATIONTYPE_Local);
		}	
	}	//	MTable

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MTable (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MTable
	
	/**
	 * 	Get Columns
	 *	@param requery requery
	 *	@return array of columns
	 */
	public MColumn[] getColumns (boolean requery) {
		List<MColumn> columnList = getColumnsAsList(requery);
		MColumn[] columnArray = new MColumn[columnList.size()];
		columns.toArray (columnArray);
		return columnArray;
	}	//	getColumns

	/**
	 * Get Column As List, by default it not re-query
	 * @return
	 */
	public List<MColumn> getColumnsAsList() {
		return getColumnsAsList(false);
	}
	
	//@Trifon
	public List<MColumn> getColumnsAsList(boolean requery) {
		if (columns != null
				&& columns.size() != 0
				&& !requery) {
			return columns;
		}
		//	Default find
		columns = new Query(getCtx(), I_AD_Column.Table_Name, "AD_Table_ID = ?", get_TrxName())
				.setParameters(getAD_Table_ID())
				.setOrderBy(I_AD_Column.COLUMNNAME_ColumnName)
				.list();
		//	Default Return
		return columns;
	}
	
	/**
	 * 	Get Column
	 *	@param columnName (case insensitive)
	 *	@return column if found
	 */
	public MColumn getColumn (String columnName) {
		if (columnName == null || columnName.isEmpty() )
			return null;

		if (columns == null
				|| columns.size() == 0) {
			getColumns(false);
		}
		//
		for (MColumn column : columns) {
			if (columnName.equalsIgnoreCase(column.getColumnName()))
				return column;
		}
		return null;
	}	//	getColumn
	
	/**
	 * 	Table has a single Key
	 *	@return true if table has single key column
	 */
	public boolean isSingleKey()
	{
		String[] keys = getKeyColumns();
		return keys.length == 1;
	}	//	isSingleKey
	
	/**
	 * 	Get Key Columns of Table
	 *	@return key columns
	 */
	public String[] getKeyColumns() {
		getColumns(false);
		ArrayList<String> list = new ArrayList<String>();
		//
		for (MColumn column : columns) {
			if (column.isKey())
				return new String[]{column.getColumnName()};
			if (column.isParent())
				list.add(column.getColumnName());
		}
		String[] retValue = new String[list.size()];
		retValue = list.toArray(retValue);
		return retValue;
	}	//	getKeyColumns
	
	/**************************************************************************
	 * 	Get PO Class Instance
	 *	@param Record_ID record
	 *	@param trxName
	 *	@return PO for Record or null
	 */
	public PO getPO (int Record_ID, String trxName)
	{
		String tableName = getTableName();
		if (Record_ID != 0 && !isSingleKey())
		{
			log.log(Level.WARNING, "(id) - Multi-Key " + tableName);
			return null;
		}
		Class<?> clazz = getClass(tableName);
		if (clazz == null)
		{
			//log.log(Level.WARNING, "(id) - Class not found for " + tableName);
			//return null;
			log.log(Level.INFO, "Using GenericPO for " + tableName);
			GenericPO po = new GenericPO(tableName, getCtx(), new Integer(Record_ID), trxName);
			return po;
		}
		
		boolean errorLogged = false;
		try
		{
			Constructor<?> constructor = null;
			try
			{
				constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
			}
			catch (Exception e)
			{
				String msg = e.getMessage();
				if (msg == null)
					msg = e.toString();
				log.warning("No transaction Constructor for " + clazz + " (" + msg + ")");
			}
			
			PO po = (PO)constructor.newInstance(new Object[] {getCtx(), new Integer(Record_ID), trxName});
			if (po != null && po.get_ID() != Record_ID && Record_ID > 0)
				return null;
			return po;
		}
		catch (Exception e)
		{
			if (e.getCause() != null)
			{
				Throwable t = e.getCause();
				log.log(Level.SEVERE, "(id) - Table=" + tableName + ",Class=" + clazz, t);
				errorLogged = true;
				if (t instanceof Exception)
					log.saveError("Error", (Exception)e.getCause());
				else
					log.saveError("Error", "Table=" + tableName + ",Class=" + clazz);
			}
			else
			{
				log.log(Level.SEVERE, "(id) - Table=" + tableName + ",Class=" + clazz, e);
				errorLogged = true;
				log.saveError("Error", "Table=" + tableName + ",Class=" + clazz);
			}
		}
		if (!errorLogged)
			log.log(Level.SEVERE, "(id) - Not found - Table=" + tableName 
				+ ", Record_ID=" + Record_ID);
		return null;
	}	//	getPO
	
	/**
	 * 	Get PO Class Instance
	 *	@param rs result set
	 *	@param trxName transaction
	 *	@return PO for Record or null
	 */
	public PO getPO (ResultSet rs, String trxName)
	{
		String tableName = getTableName();
		Class<?> clazz = getClass(tableName);
		if (clazz == null)
		{
			//log.log(Level.SEVERE, "(rs) - Class not found for " + tableName);
			//return null;
			log.log(Level.INFO, "Using GenericPO for " + tableName);
			GenericPO po = new GenericPO(tableName, getCtx(), rs, trxName);
			return po;
		}
		
		boolean errorLogged = false;
		try
		{
			Constructor<?> constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, ResultSet.class, String.class});
			PO po = (PO)constructor.newInstance(new Object[] {getCtx(), rs, trxName});
			return po;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "(rs) - Table=" + tableName + ",Class=" + clazz, e);
			errorLogged = true;
			log.saveError("Error", "Table=" + tableName + ",Class=" + clazz);
		}
		if (!errorLogged)
			log.log(Level.SEVERE, "(rs) - Not found - Table=" + tableName);
		return null;
	}	//	getPO

	/**
	 * 	Get PO Class Instance
	 *	@param whereClause where clause
	 *	@param trxName transaction
	 *	@return PO for Record or null
	 */
	public PO getPO (String whereClause, String trxName)
	{
		return getPO(whereClause, null, trxName);
	}	//	getPO
	
	/**
	 * Get PO class instance
	 * @param whereClause
	 * @param params
	 * @param trxName
	 * @return
	 */
	public PO getPO(String whereClause, Object[] params, String trxName)
	{
		if (whereClause == null || whereClause.length() == 0)
			return null;
		//
		PO po = null;
		POInfo info = POInfo.getPOInfo(getCtx(), getAD_Table_ID(), trxName);
		if (info == null) return null;
		StringBuffer sqlBuffer = info.buildSelect();
		sqlBuffer.append(" WHERE ").append(whereClause);
		String sql = sqlBuffer.toString(); 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement (sql, trxName);
			if (params != null && params.length > 0) 
			{
				for (int i = 0; i < params.length; i++)
				{
					pstmt.setObject(i+1, params[i]);
				}
			}
			rs = pstmt.executeQuery ();
			if (rs.next ()) {
				po = getPO(rs, trxName);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, sql, e);
			log.saveError("Error", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return po;
	}
	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		if ((isView() || isDocument()) 
				&& isDeleteable())
			setIsDeleteable(false);
		//
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
		// Skip the ogic while migration process is executed
		if(isDirectLoad())
			return success;

		//	Sync Table ID
		if (newRecord)
		{
			createMandatoryColumns();
			//	Create Standard columns for Documents
			createMandatoryDocumentColumns();
			
			MSequence seq = MSequence.get(getCtx(), getTableName(), get_TrxName());
			if (seq == null || seq.get_ID() == 0)
				MSequence.createTableSequence(getCtx(), getTableName(), get_TrxName());
		}
		else
		{
			//	Create Standard columns for Documents
			if(is_ValueChanged(COLUMNNAME_IsDocument)) {
				createMandatoryDocumentColumns();
			}
			
			// Find or create the associated sequence and 
			// Check if the database table name changed.
			String oldTableName = (String) get_ValueOld(MTable.COLUMNNAME_TableName);

			// Find the sequence based on the old TableName. If the table name hasn't 
			// changed, nothing needs be done.
			MSequence seq = MSequence.get(getCtx(), oldTableName, get_TrxName());
			if (seq == null || seq.get_ID() == 0)
			{
				// Not found.  Check if a sequence exists with the new name.
				seq = MSequence.get(getCtx(), getTableName(), get_TrxName());
				if (seq == null || seq.get_ID() == 0)
				{
					// No sequence matches the old or new table name.  
					// Create a new sequence using the new name.
					MSequence.createTableSequence(getCtx(), getTableName(), get_TrxName());
				} // else, sequence with the current tablename exists. Do nothing.
			}
			// A sequence with the old TableName exists. Check if it needs to be updated.
			else if (!seq.getName().equals(getTableName()))
			{
				seq.setName(getTableName());
				seq.setDescription("Table " + getTableName());
				seq.saveEx();
			}
		}	
		
		return success;
	}	//	afterSave
	
	/**
	 * 	Get SQL Create
	 *	@return create table DDL
	 */
	public String getSQLCreate()
	{
		StringBuffer sb = new StringBuffer("CREATE TABLE ")
			.append(getTableName()).append(" (");
		//
		boolean hasPK = false;
		boolean hasParents = false;
		StringBuffer constraints = new StringBuffer();
		getColumns(true);
		for (int i = 0; i < columns.size(); i++) {
			MColumn column = columns.get(i);
			String colSQL = column.getSQLDDL();
			if ( colSQL != null )
			{
				if (i > 0)
					sb.append(", ");
					sb.append(column.getSQLDDL());
			}
			else // virtual column
				continue;
			//
			if (column.isKey())
				hasPK = true;
			if (column.isParent())
				hasParents = true;
			String constraint = column.getConstraint(getTableName());
			if (constraint != null && constraint.length() > 0)
				constraints.append(", ").append(constraint);
		}
		//	Multi Column PK 
		if (!hasPK && hasParents)
		{
			StringBuffer cols = new StringBuffer();
			for (MColumn column : columns) {
				if (!column.isParent())
					continue;
				if (cols.length() > 0)
					cols.append(", ");
				cols.append(column.getColumnName());
			}
			sb.append(", CONSTRAINT ")
				.append(getTableName()).append("_Key PRIMARY KEY (")
				.append(cols).append(")");
		}

		sb.append(constraints)
			.append(")");
		return sb.toString();
	}	//	getSQLCreate
	
	// globalqss
	/**
	 * 	Grant independence to GenerateModel from AD_Table_ID
	 *	@param String tableName
	 *	@return int retValue
	 */
	public static int getTable_ID(String tableName) {
		int retValue = 0;
		String SQL = "SELECT AD_Table_ID FROM AD_Table WHERE tablename = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(SQL, null);
			pstmt.setString(1, tableName);
			rs = pstmt.executeQuery();
			if (rs.next())
				retValue = rs.getInt(1);
		} catch (Exception e) {
			s_log.log(Level.SEVERE, SQL, e);
			retValue = -1;
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}

	/**
	 * Create query to retrieve one or more PO.
	 * @param whereClause
	 * @param trxName
	 * @return Query
	 */
	public Query createQuery(String whereClause, String trxName) 
	{
		return new Query(this.getCtx(), this, whereClause, trxName);
	}
	
	/*
	 * Create Mandatory Fields
	 */
	public void createMandatoryColumns()
	{		
		MColumn column = null;
		//M_Element.get(getCtx(),COLUMNNAME_AD_Client_ID);
		
		column = new MColumn(this, COLUMNNAME_AD_Client_ID	, 22 , DisplayType.TableDir , "@#AD_Client_ID@");
		column.setUpdateable(false);
		column.setAD_Val_Rule_ID(129);
		column.saveEx();
		column = new MColumn(this, COLUMNNAME_AD_Org_ID	, 22 , DisplayType.TableDir , "@#AD_Org_ID@");
		column.setUpdateable(true);
		column.setAD_Val_Rule_ID(104);
		column.saveEx();
		column = new MColumn(this, COLUMNNAME_IsActive	, 1 , DisplayType.YesNo , "Y");
		column.setUpdateable(true);
		column.saveEx();
		column = new MColumn(this, COLUMNNAME_Created	, 7 , DisplayType.DateTime , "");
		column.saveEx();		
		column = new MColumn(this, COLUMNNAME_Updated	, 7 , DisplayType.DateTime , "");
		column.saveEx();
		column = new MColumn(this, COLUMNNAME_CreatedBy	, 22 , DisplayType.Table, "");
		column.setAD_Reference_Value_ID(110);
		column.saveEx();
		column = new MColumn(this, COLUMNNAME_UpdatedBy	, 22 , DisplayType.Table, "");
		column.setAD_Reference_Value_ID(110);
		column.saveEx();
		column = new MColumn(this, COLUMNNAME_UUID, 36 , DisplayType.String, "");
		column.setIsMandatory(false);
		column.saveEx();
		if(!isView())
		{	
			if(getTableName().endsWith("_Trl") || getTableName().endsWith("_Access"))
				return;
			
			M_Element element = M_Element.get(getCtx(), getTableName()+"_ID", get_TrxName());
			if(element != null)
				return;				
			element = new M_Element(getCtx(), 0 , get_TrxName());
			element.setColumnName(getTableName()+"_ID");
			element.setName(getName() + " ID");
			element.setPrintName(getName() + " ID");
			element.setEntityType(getEntityType());
			element.saveEx();
			
			column = new MColumn(this, element.getColumnName(), 22 , DisplayType.ID, "");
			column.setAD_Element_ID(element.get_ID());
			column.setIsKey(true);
			column.setUpdateable(false);
			column.setIsMandatory(true);
			column.saveEx();
		}
	}
	
	/**
	 * Create Standard columns for tables marks like Document
	 */
	private void createMandatoryDocumentColumns() {
		//	Yamel Senih, 2015-11-14
		//	Add Default Columns for Document Tables
		if(isDocument()) {
			//	Document Type
			MColumn column = null;
			String columnName = "C_DocType_ID";
			if(MColumn.getColumn_ID(getTableName(), columnName) <= 0) {
				column = new MColumn(this, columnName, 22, DisplayType.TableDir, "");
				column.setIsMandatory(true);
				column.setUpdateable(false);
				column.setIsSelectionColumn(true);
				column.saveEx();
			}
			//	Document No
			columnName = "DocumentNo";
			if(MColumn.getColumn_ID(getTableName(), columnName) <= 0) {
				column = new MColumn(this, columnName, 60, DisplayType.String, "");
				column.setIsMandatory(true);
				column.setUpdateable(false);
				column.setIsSelectionColumn(true);
				column.setIsIdentifier(true);
				column.setSeqNo(1);
				column.saveEx();
			}
			//	Document Date
			columnName = "DateDoc";
			if(MColumn.getColumn_ID(getTableName(), columnName) <= 0) {
				column = new MColumn(this, columnName, 7, DisplayType.Date, "@#Date@");
				column.setIsMandatory(true);
				column.setUpdateable(false);
				column.setIsSelectionColumn(true);
				column.saveEx();
			}
			//	Processed
			columnName = "Processed";
			if(MColumn.getColumn_ID(getTableName(), columnName) <= 0) {
				column = new MColumn(this, columnName, 1, DisplayType.YesNo, "N");
				column.setIsMandatory(true);
				column.setUpdateable(false);
				column.saveEx();
			}
			//	Processing
			columnName = "Processing";
			if(MColumn.getColumn_ID(getTableName(), columnName) <= 0) {
				column = new MColumn(this, columnName, 1, DisplayType.YesNo, "N");
				column.setIsMandatory(true);
				column.setUpdateable(true);
				column.setIsAlwaysUpdateable(true);
				column.saveEx();
			}
			//	Approved
			columnName = "IsApproved";
			if(MColumn.getColumn_ID(getTableName(), columnName) <= 0) {
				column = new MColumn(this, columnName, 1, DisplayType.YesNo, "N");
				column.setIsMandatory(true);
				column.setUpdateable(false);
				column.saveEx();
			}
			//	Document Description
			columnName = "Description";
			if(MColumn.getColumn_ID(getTableName(), columnName) <= 0) {
				column = new MColumn(this, columnName, 255, DisplayType.Text, "");
				column.setIsMandatory(false);
				column.setUpdateable(true);
				column.setIsAlwaysUpdateable(true);
				column.setIsSelectionColumn(true);
				column.saveEx();
			}
			//	Document Status
			columnName = "DocStatus";
			if(MColumn.getColumn_ID(getTableName(), columnName) <= 0) {
				column = new MColumn(this, columnName, 2, DisplayType.List, "DR");
				column.setIsMandatory(true);
				column.setUpdateable(false);
				column.setAD_Reference_Value_ID(131);
				column.setIsSelectionColumn(true);
				column.setIsIdentifier(true);
				column.setSeqNo(2);
				column.saveEx();
			}
			//	Document Action
			columnName = "DocAction";
			if(MColumn.getColumn_ID(getTableName(), columnName) <= 0) {
				column = new MColumn(this, columnName, 2, DisplayType.Button, "CO");
				column.setIsMandatory(true);
				column.setUpdateable(false);
				column.setAD_Reference_Value_ID(135);
				column.setAD_Process_ID(getworkFlowProcess());
				column.saveEx();
			}
		}
		//	End Yamel Senih
	}
	
	/**
	 * Get / Create Process and Work Flow
	 * @return
	 */
	private int getworkFlowProcess() {
		//	Search or create Work Flow
		MWorkflow workFlow = MWorkflow
				.getWorkFlowFromDocumentTable(getCtx(), getAD_Table_ID(), get_TrxName());
		//	validate null
		if(workFlow == null) {
			workFlow = new MWorkflow(getCtx(), 0, get_TrxName());
			workFlow.setValue("Process_" + getName());
			workFlow.setName(workFlow.getValue());
			workFlow.setDescription("(Standard Process_" + getName() + ")");
			workFlow.setWorkflowType(X_AD_Workflow.WORKFLOWTYPE_DocumentProcess);
			workFlow.setAD_Table_ID(getAD_Table_ID());
			workFlow.setAccessLevel(getAccessLevel());
			workFlow.setEntityType(getEntityType());
			workFlow.setPublishStatus(X_AD_Workflow.PUBLISHSTATUS_Test);
			workFlow.setAuthor("ADempiere");
			workFlow.setDuration(1);
			workFlow.saveEx();
			//	Create Work Flow Node for (Start)
			MWFNode wfNode_Start = new MWFNode(workFlow, "(Start)", "(Start)");
			wfNode_Start.setDescription(workFlow.getName());
			wfNode_Start.setEntityType(getEntityType());
			wfNode_Start.setJoinElement(X_AD_WF_Node.JOINELEMENT_XOR);
			wfNode_Start.setSplitElement(X_AD_WF_Node.SPLITELEMENT_XOR);
			wfNode_Start.setAction(X_AD_WF_Node.ACTION_WaitSleep);
			wfNode_Start.saveEx();
			//	Set Start node in Workflow
			workFlow.setAD_WF_Node_ID(wfNode_Start.getAD_WF_Node_ID());
			workFlow.saveEx();
			//	Create Work Flow Node for (DocAuto)
			MWFNode wfNode_Auto = new MWFNode(workFlow, "(DocAuto)", "(DocAuto)");
			wfNode_Auto.setDescription(workFlow.getName());
			wfNode_Auto.setEntityType(getEntityType());
			wfNode_Auto.setJoinElement(X_AD_WF_Node.JOINELEMENT_XOR);
			wfNode_Auto.setSplitElement(X_AD_WF_Node.SPLITELEMENT_XOR);
			wfNode_Auto.setAction(X_AD_WF_Node.ACTION_DocumentAction);
			wfNode_Auto.setDocAction(X_AD_WF_Node.DOCACTION_None);
			wfNode_Auto.saveEx();
			//	Create Work Flow Node for (DocPrepare)
			MWFNode wfNode_Prepare = new MWFNode(workFlow, "(DocPrepare)", "(DocPrepare)");
			wfNode_Prepare.setDescription(workFlow.getName());
			wfNode_Prepare.setEntityType(getEntityType());
			wfNode_Prepare.setJoinElement(X_AD_WF_Node.JOINELEMENT_XOR);
			wfNode_Prepare.setSplitElement(X_AD_WF_Node.SPLITELEMENT_XOR);
			wfNode_Prepare.setAction(X_AD_WF_Node.ACTION_DocumentAction);
			wfNode_Prepare.setDocAction(X_AD_WF_Node.DOCACTION_Prepare);
			wfNode_Prepare.saveEx();
			//	Create Work Flow Node for (DocComplete)
			MWFNode wfNode_Complete = new MWFNode(workFlow, "(DocComplete)", "(DocComplete)");
			wfNode_Complete.setDescription(workFlow.getName());
			wfNode_Complete.setEntityType(getEntityType());
			wfNode_Complete.setJoinElement(X_AD_WF_Node.JOINELEMENT_XOR);
			wfNode_Complete.setSplitElement(X_AD_WF_Node.SPLITELEMENT_XOR);
			wfNode_Complete.setAction(X_AD_WF_Node.ACTION_DocumentAction);
			wfNode_Complete.setDocAction(X_AD_WF_Node.DOCACTION_Complete);
			wfNode_Complete.saveEx();
			//	Create Transition For Start Node
			//	For Start
			MWFNodeNext wfNodeNext = new MWFNodeNext(wfNode_Start, wfNode_Prepare.getAD_WF_Node_ID());
			wfNodeNext.setDescription("(Standard Approval)");
			wfNodeNext.setEntityType(getEntityType());
			wfNodeNext.setSeqNo(10);
			wfNodeNext.setIsStdUserWorkflow(true);
			wfNodeNext.saveEx();
			//	For DocAuto
			wfNodeNext = new MWFNodeNext(wfNode_Start, wfNode_Auto.getAD_WF_Node_ID());
			wfNodeNext.setDescription("(Standard Transition)");
			wfNodeNext.setEntityType(getEntityType());
			wfNodeNext.setSeqNo(100);
			wfNodeNext.setIsStdUserWorkflow(false);
			wfNodeNext.saveEx();
			//	Create Transition For Prepare Node
			//	For DocComplete
			wfNodeNext = new MWFNodeNext(wfNode_Prepare, wfNode_Complete.getAD_WF_Node_ID());
			wfNodeNext.setDescription("(Standard Transition)");
			wfNodeNext.setEntityType(getEntityType());
			wfNodeNext.setSeqNo(100);
			wfNodeNext.setIsStdUserWorkflow(false);
			wfNodeNext.saveEx();
		}
		//	Create Standard Process for Document Action
		int m_AD_Process_ID = MProcess.getProcess_ID(getTableName() + "_Process", get_TrxName());
		if(m_AD_Process_ID <= 0) {
			MProcess workFlowProcess = new MProcess(getCtx(), 0, get_TrxName());
			workFlowProcess.setValue(getTableName() + "_Process");
			workFlowProcess.setName("Process " + getName());
			workFlowProcess.setEntityType(getEntityType());
			workFlowProcess.setAccessLevel(getAccessLevel());
			workFlowProcess.setAD_Workflow_ID(workFlow.getAD_Workflow_ID());
			workFlowProcess.saveEx();
			//	Default Return
			return workFlowProcess.getAD_Process_ID();
		}
		//	Default Return
		return m_AD_Process_ID;
	}

	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer ("MTable[");
		sb.append (get_ID()).append ("-").append (getTableName()).append ("]");
		return sb.toString ();
	}	//	toString

}	//	MTable