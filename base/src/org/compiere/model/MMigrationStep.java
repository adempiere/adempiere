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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author paul
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> BR [ 425 ] Table ad_reportview_trl with wrong primary key
 *		@see https://github.com/adempiere/adempiere/issues/425
 *		<li> BR [ 440 ] Translation tables does not support rollback in Migration
 *		@see https://github.com/adempiere/adempiere/issues/440
 *		<a href="https://github.com/adempiere/adempiere/issues/673">
 * 		@see FR [ 673 ] Model Migration don't load current value for Multi-Key records</a>
 * 		<a href="https://github.com/adempiere/adempiere/issues/1072">
 * 		@see BR [ 1072 ] Synchronize Column is unnecessary when it is not apply for DB</a>
 *  @author Michael McKay, mckayERP@gmail.com
 *  	<li><a href="https://github.com/adempiere/adempiere/issues/2914">#2914</a>: Force delete of an entity when
 *  		rolling back a migration step
 */
public class MMigrationStep extends X_AD_MigrationStep {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6002302731217174562L;
	private List<MMigrationData> m_migrationData;
	private MMigration parent;
	/** Logger */
	private static CLogger log = CLogger
			.getCLogger(MMigrationStep.class);

	private boolean apply = true;
	/**	Is All Migration for Apply	*/
	private boolean isAllMigration = false;

	/**
	 * @param ctx
	 * @param AD_MigrationStep_ID
	 * @param trxName
	 */
	public MMigrationStep(Properties ctx, int AD_MigrationStep_ID,
			String trxName) {
		super(ctx, AD_MigrationStep_ID, trxName);
		getData();
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MMigrationStep(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		getData();
	}
	
	public MMigrationStep(MMigration parent) {
		this(parent.getCtx(), 0, parent.get_TrxName());
		setAD_Migration_ID(parent.getAD_Migration_ID());
		this.parent = parent;
	}

	public String toString() {
		MMigration parent = (MMigration) getAD_Migration();
		return parent.toString() + "; Step: " + getSeqNo() + "; Type: " + getStepType() + ". ";
	}
	/**
	 * Create a PO migration step from PO event.
	 * Called from PO saveNew(), saveUpdate(), delete()
	 * @param m_migration
	 * @param po
	 * @param info
	 * @param event (insert, update, delete)
	 */
	public MMigrationStep(MMigration m_migration, PO po, POInfo info, String event) {
		this(po.getCtx(), 0, po.get_TrxName());
		setAD_Migration_ID(m_migration.getAD_Migration_ID());
		set_TrxName(po.get_TrxName());
		setStepType(MMigrationStep.STEPTYPE_ApplicationDictionary);
		setAction(event);
		setAD_Table_ID(po.get_Table_ID());
		setRecord_ID(po.get_ID());  // In a multi-key table - this will be the one of the IDs.
		setStatusCode(MMigrationStep.STATUSCODE_Applied);
		setApply(MMigrationStep.APPLY_Rollback);
		
		String sql = "SELECT COALESCE(max(SeqNo),0) + 10 FROM AD_MigrationStep"
			+ " WHERE AD_Migration_ID = " + getAD_Migration_ID();
		int seqNo = DB.getSQLValue(get_TrxName(), sql);
		setSeqNo(seqNo);
		
		saveEx(po.get_TrxName());
		
		int size = po.get_ColumnCount();
		for (int i = 0; i < size; i++)
		{
			Object value = po.get_Value(i);
			
			//  Determine if the column is one of several in a multi-key table.
			//  If so, all the keys and their values have to be saved in the
			//  newValue column regardless of whether they changed or not.
			boolean isMultiKeyColumn = false;
			if ( po.get_KeyColumns().length > 1 ) 
			{
				for (int j = 0; j < po.get_KeyColumns().length; j++)
				{
					String name = po.get_KeyColumns()[j];
					if (name.equals(info.getColumnName(i)))
					{
						isMultiKeyColumn = true;
						break;
					}
				}
			}
			
			if (  !info.isEncrypted(i)		//	not encrypted
				&& !info.isVirtualColumn(i)	//	no virtual column
				)
			{
				MMigrationData data = new MMigrationData(this);
				data.setAD_Column_ID(info.getColumn(i).AD_Column_ID);
				// reference data (old value) on delete/update
				if ( event.equals(MMigrationStep.ACTION_Delete) 
						|| ( event.equals(MMigrationStep.ACTION_Update) && po.is_ValueChanged(i)
						|| isMultiKeyColumn)
				)
				{
					if ( po.get_ValueOld(i) == null )
						data.setIsOldNull(true);
					else
						data.setOldValue(po.get_ValueOld(i).toString());
					data.saveEx();
				}
				// save new value
				if ( event.equals(MMigrationStep.ACTION_Insert) 
						|| ( event.equals(MMigrationStep.ACTION_Update) && po.is_ValueChanged(i)) 
						|| isMultiKeyColumn)
				{
					if (value == null && info.getDefaultLogic(i) != null && !info.isColumnMandatory(i)) {
						Object defaultValue = po.getDefaultValue(i);
						if (defaultValue != null)
							data.setNewValue(defaultValue.toString());
						else
							data.setNewValue(null);
					}
					else if (value == null)
						data.setIsNewNull(true);
					else
						data.setNewValue(value.toString());
					data.saveEx();
				}
			}
		}
	}

	/**
	 * Apply or rollback the migration step.  If the step has been applied and the action is
	 * "Rollback", then rollback the step.  Otherwise, the step will be applied.  For backwards
	 * compatibility, validation checking of ID columns is turned off.  The parent status is
	 * updated following the action.
	 * 
	 * @return A string indicating the results of the action.
	 */
	public String apply() {

		// This function can be called by the migration window, step tab for both
		// rollback and apply.  Determine if we need to apply or rollback the step
		if ( MMigrationStep.STATUSCODE_Applied.equals(getStatusCode())
			&& MMigrationStep.APPLY_Rollback.equals(getApply()) )
		{
			apply = false;
			return rollback();
		}
		
		String retval = this.toString();

		if ( MMigrationStep.STATUSCODE_Applied.equals(getStatusCode()) )
		{
			if ( !MMigrationStep.APPLY_Rollback.equals(getApply()) )
			{
				// Fix the control - should read rollback.
				setApply(MMigrationStep.APPLY_Rollback);
				saveEx();
			}
			log.log(Level.CONFIG, "Migration step already applied: " + this);
			return retval + "Already applied";
		}

		// Flag that a script is in progress to shut off some validation checks
		Env.setContext(getCtx(), "MigrationStepApplyInProgress", "Y");

		log.log(Level.CONFIG, "Applying migration step: " + this.toString());

		if ( MMigrationStep.STEPTYPE_SQLStatement.equals(getStepType()) )
			retval += applySQL(!apply);
		else if ( MMigrationStep.STEPTYPE_ApplicationDictionary.equals(getStepType()) )
			retval +=  applyPO();
		else
		{
			bailout("Unknown step type.");
		}
		// Unset flag that a script is in progress to shut off some validation checks
		Env.setContext(getCtx(), "MigrationStepApplyInProgress", "");

		log.log(Level.CONFIG, retval);
		getParent().updateStatus();
		getParent().save();
		return retval;
	}

	/**
	 * Bailout
	 * @param error
	 */
	private void bailout(String error) {
		bailout(error, null);
	}

	/**
	 * Bailout
	 * @param error
	 * @param e
	 */
	private void bailout(String error, Exception e) {
        setErrorMsg(error);
        setStatusCode(MMigrationStep.STATUSCODE_Failed);
		setApply(apply ? MMigrationStep.APPLY_Apply : MMigrationStep.APPLY_Rollback);
        saveEx();

        if (apply)
			Env.setContext(getCtx(), "MigrationStepApplyInProgress", "");
        else
    		Env.setContext(getCtx(), "MigrationStepRollbackInProgress", "");
        
		getParent().updateStatus();
		getParent().save();

		log.warning(this.toString() + " " + getErrorMsg());
		if (e != null)
	        throw new AdempiereException(this.toString() + getErrorMsg(), e);
		else
			throw new AdempiereException(this.toString() + getErrorMsg());
	}

	/**
	 * Rollback
	 * @return
	 */
	public String rollback() {

		String retCode = this.toString();

		if ( !MMigrationStep.STATUSCODE_Applied.equals(getStatusCode()) )
			return retCode + "Not applied, no rollback required";
		
		// Flag that a script is in progress to shut off some validation checks
		Env.setContext(getCtx(), "MigrationStepRollbackInProgress", "Y");
		
		log.log(Level.CONFIG, "Rolling back migration step: " + this);


		if (  MMigrationStep.STEPTYPE_SQLStatement.equals(getStepType()) )
			retCode += applySQL(true);
		else if ( MMigrationStep.STEPTYPE_ApplicationDictionary.equals(getStepType()) )
			retCode += rollbackPO();
		else
		{
			bailout("Unknown step type");
		}
		
		// Unset the Flag that a script is in progress to shut off some validation checks
		Env.setContext(getCtx(), "MigrationStepRollbackInProgress", "");

		log.log(Level.CONFIG, retCode);
		getParent().updateStatus();
		getParent().save();
		return retCode;
	}

	/**
	 * Apply SQL
	 * @param rollback
	 * @return
	 */
	private String applySQL(boolean rollback) {

		String sqlStatements = rollback ? getRollbackStatement() : getSQLStatement();
		Boolean isParse = true;
		
		// For backward compatibility, check if the parse column has been added by the migration yet.
		// The parse column was added to AD_MigrationStep just prior to release 3.8.0.
		if (this.get_ColumnIndex(MMigrationStep.COLUMNNAME_Parse) >= 0)
			isParse = isParse();
		
		boolean ignore = sqlStatements == null 
				|| sqlStatements.trim().length() == 0 
				|| sqlStatements.equals(";");

		// Apply the sql if the database is supported.  Otherwise, mark the step 
		// applied/rolled-back but take no action.
        if ((getDBType().equals(MMigrationStep.DBTYPE_AllDatabaseTypes)
		    || (DB.isOracle() && getDBType().equals(MMigrationStep.DBTYPE_Oracle))
		    || (DB.isPostgreSQL() && getDBType().equals(MMigrationStep.DBTYPE_Postgres)))
		    && !ignore) 
        {
        	//	Synchronize column first
        	getParent().syncColumn();
        	//	
		     Connection conn = DB.getConnectionRW();
		     Statement stmt = null;
		     try {
		
		         conn.setAutoCommit(false);
		         stmt = conn.createStatement();
		
		         //  Parse the statement based on semi-colons
		         if (isParse) {
		             StringTokenizer tokens = new StringTokenizer(sqlStatements, ";");
		             while(tokens.hasMoreTokens()) {
		                 final String sql = tokens.nextToken().trim();
		                 if (sql != null && sql.length() > 0 )
		                    stmt.addBatch(sql);
		             }
		             stmt.executeBatch();
		         } 
			     else {  // Don't parse.  Assume its a single statement.
			         stmt.executeUpdate(sqlStatements);
			     }
		
		         conn.commit();
		         setStatusCode(rollback ? MMigrationStep.STATUSCODE_Unapplied : MMigrationStep.STATUSCODE_Applied);
		         setApply(rollback ? MMigrationStep.APPLY_Apply : MMigrationStep.APPLY_Rollback);
		         setErrorMsg(null);
		         conn.close();
		     } catch (SQLException e) {
		         SQLException ne = e.getNextException();
		
		         // Try to close the connection.  If not failing on error and there are
		         // many errors, we could be left with a lot of hanging connections.
		         try {
		             conn.rollback();
		             conn.close();
		         } catch (SQLException se) {
		             ;  // all out of luck
		         }
		         
		         setErrorMsg(rollback ? "Rollback failed. ":"Application failed. ");
		         if (ne != null) {
		             setErrorMsg(getErrorMsg() + ne.toString());
		         }
		         setErrorMsg(getErrorMsg() + "\n" + e.toString());
		
		         setApply(rollback ? MMigrationStep.APPLY_Rollback : MMigrationStep.APPLY_Apply);
		         if (!rollback)
		             setStatusCode(MMigrationStep.STATUSCODE_Failed);
		
		         
				log.severe(this.toString() + " " + getErrorMsg());
				throw new AdempiereException(this.toString() + " " + getErrorMsg(), e);

		     } finally {
		         DB.close(stmt);
		         saveEx(null);
		     }
		 }
        else 
        {
	         setStatusCode(rollback ? MMigrationStep.STATUSCODE_Unapplied : MMigrationStep.STATUSCODE_Applied);
	         setApply(rollback ? MMigrationStep.APPLY_Apply : MMigrationStep.APPLY_Rollback);
	         setErrorMsg(null);
	         saveEx(null);
        }
		return rollback ? "successfully rolled back" : "successfully applied";
	}
	
	/**
	 * Convert string representation to appropriate object type
	 * for column
	 * @param column
	 * @param value
	 * @return
	 */
	private Object stringToObject(MColumn column, String value) {
		if ( value == null )
			return null;
		
		if ( DisplayType.isText(column.getAD_Reference_ID()) 
				|| column.getAD_Reference_ID() == DisplayType.List  
				|| column.getColumnName().equals("EntityType") 
				|| column.getColumnName().equals("AD_Language")) {
			return value;
		}
		else if ( DisplayType.isNumeric(column.getAD_Reference_ID()) ){
			return new BigDecimal(value);
		}
		else if (DisplayType.isID(column.getAD_Reference_ID()) ) {
			return Integer.valueOf(value);
		}	
		else if (DisplayType.YesNo == column.getAD_Reference_ID() ) {
			return "true".equalsIgnoreCase(value);
		}
		else if (DisplayType.Button == column.getAD_Reference_ID() && column.getAD_Reference_Value_ID() == 0) {
			return "true".equalsIgnoreCase(value) ? "Y" : "N";
		}
		else if (DisplayType.Button == column.getAD_Reference_ID() && column.getAD_Reference_Value_ID() != 0) {
			return value;
		}
		else if (DisplayType.isDate(column.getAD_Reference_ID())) {
			return Timestamp.valueOf(value);
		}
	//Binary,  Radio, RowID, Image not supported
		else 
			return null;
	}
	
	/**
	 * Get PO from Keys
	 * @param table
	 * @return
	 */
	private PO getPO(MTable table) {
		//	Get PO from key or keys
		PO po = null;
		if (table.isSingleKey() && getRecord_ID() > 0) {
			po = table.getPO( getRecord_ID(), get_TrxName() );
		} else {
			String where = "";
			ArrayList<Object> params = new ArrayList<Object>();
			boolean first = true;
			
			List<MMigrationData> keys = getKeyData();
			for (MMigrationData key : keys) { 
				if ( first )
					first = false;
				else
					where += " AND ";
				
				MColumn column = (MColumn) key.getAD_Column();
                if(column == null)
                    continue;

				where += column.getColumnName() + " = ? ";
				
				params.add(stringToObject(column, key.getNewValue()));	
			}
			//	Get PO from Query
			po = new Query(getCtx(), table, where, get_TrxName())
			.setParameters(params)
			.firstOnly();
		}
		//	Return PO
		return po;
	}

	/**
	 * Apply Migration
	 * @return
	 */
	private String applyPO() {
		boolean syncColumnImmediately = false;
		if ( getAD_Table_ID() == 0 )
		{
			bailout("No table defined");
		}

		try {
			MTable table = MTable.get(getCtx(), getAD_Table_ID());
			PO po = null;
			//reset cache of persistence object
			POInfo.removeFromCache(getAD_Table_ID());
			//	Get PO from table
			po = getPO(table);
			//	
			if (po == null && getAction().equals(MMigrationStep.ACTION_Insert)) {
				po = table.getPO(0, get_TrxName());
				po.set_ValueNoCheck(po.get_KeyColumns()[0], getRecord_ID());
			} else if(po == null 
					&& table.getTableName().endsWith("_Trl")) {
				setStatusCode(MMigrationStep.STATUSCODE_Applied);
				setApply(MMigrationStep.APPLY_Rollback);
				setErrorMsg(null);
				saveEx();
				return "successfully applied";
			} else if (po == null) // Action other than insert
			{
				// The PO has not been set and we aren't inserting a new record - something is wrong.
				bailout("Step " + getSeqNo() + ", Record " + getRecord_ID() + " was not found in table " + table.getName() + " (" + table.get_ID() + ").");
			}
			//	Set flag for direct load from migration
			po.setIsDirectLoad(true);
			boolean syncColumn = po.is_new();
			boolean isColumn = po instanceof MColumn;
			for (MMigrationData data : m_migrationData) {
				if (!data.isActive())
					continue;

				MColumn column = (MColumn) data.getAD_Column();
				if(column == null)
					continue;

				// TODO: option to apply only when existing value equals reference value
				String value = data.getNewValue();
				if (column.getColumnName().equals("ColumnName") && value != null && value.equals(MTable.COLUMNNAME_UUID))
					syncColumnImmediately = true;
				
				if (data.isNewNull()) {
					value = null;
				}
				//	
				if (value == null && column.isMandatory() && column.getDefaultValue() != null) {
					value = Env.parseVariable(column.getDefaultValue() ,  po, po.get_TrxName(), false);
				}
				// backup existing value
				if (!po.is_new()) {
					String oldDataType = "";
					if(isColumn
							&& column.getColumnName().equals(I_AD_Column.COLUMNNAME_AD_Reference_ID)) {
						oldDataType = DisplayType.getSQLDataType(
								po.get_ValueAsInt(I_AD_Column.COLUMNNAME_AD_Reference_ID), 
								column.getColumnName(), 
								po.get_ValueAsInt(I_AD_Column.COLUMNNAME_FieldLength), 
								po.get_ValueAsInt(I_AD_Column.COLUMNNAME_AD_Reference_Value_ID));
					}
					//	Backup value
					Object backupValue = po.get_Value(column.getColumnName());
					if (backupValue == null) {
						data.setIsBackupNull(true);
					} else {
						data.setBackupValue(backupValue.toString());
					}
					//	
					data.saveEx(get_TrxName());
					//	Verify if is necessary synchronize column
					if(isColumn
							&& !syncColumn) {
						if(!column.getColumnName().equals(I_AD_Column.COLUMNNAME_AD_Reference_ID)) {
							syncColumn = MColumn.isForSynchronizeColumn(column.getColumnName());
						} else {
							String newDataType = DisplayType.getSQLDataType(
									po.get_ValueAsInt(I_AD_Column.COLUMNNAME_AD_Reference_ID), 
									column.getColumnName(), 
									po.get_ValueAsInt(I_AD_Column.COLUMNNAME_FieldLength), 
									po.get_ValueAsInt(I_AD_Column.COLUMNNAME_AD_Reference_Value_ID));
							//	Validate
							syncColumn = !oldDataType.equals(newDataType);
						}
					}
				}
				// apply new values
				if (getAction().equals(MMigrationStep.ACTION_Insert) 
						|| getAction().equals(MMigrationStep.ACTION_Update)) {
					po.set_ValueNoCheck(column.getColumnName(), stringToObject(column, value));
				}
			}
			//	For Delete
			if (getAction().equals(MMigrationStep.ACTION_Delete)){
				if (po instanceof  MEntityType) {
					MEntityType entityType = (MEntityType) po;
					entityType.setIsDeleteForced(true);
					entityType.delete(true , get_TrxName());
				} else {
					po.deleteEx(false, get_TrxName());
				}
				// TODO unsync column?
			} else {
				if(po.get_TableName().endsWith("Trl")
						&& getAction().equals(MMigrationStep.ACTION_Insert)) {
					po.save(get_TrxName());
				} else {
					po.saveEx(get_TrxName());
				}
				//  Synchronize the AD_Column changes with the database.
				if (po instanceof MColumn) {
					MColumn col = (MColumn) po;
					if (!col.isVirtualColumn()
							&& syncColumn) {
						if(col.getAD_Table_ID() == I_AD_Table.Table_ID
								|| col.getAD_Table_ID() == I_AD_Column.Table_ID
								|| !isAllMigration) {
							log.log(Level.CONFIG, "Synchronizing column: " + col.toString() 
									+ " in table: " + MTable.get(Env.getCtx(), col.getAD_Table_ID()));
							col.syncDatabase();
						} else {	//	BR [ 425 ]
							getParent().addColumnToList(col.getAD_Column_ID());
						}
					}
				}
			}
		}
		
		catch (Exception e) {
			bailout("Application failed: " + e.toString(), e);
		}
		setStatusCode(MMigrationStep.STATUSCODE_Applied);
		setApply(MMigrationStep.APPLY_Rollback);
		setErrorMsg(null);
		saveEx();
		if (syncColumnImmediately)
			getParent().syncColumn();

		return "successfully applied";
	}
	
	/**
	 * Rollback PO
	 * @return
	 */
	private String rollbackPO() {
		
		if ( getAD_Table_ID() == 0 )
		{
			bailout("No table defined.");
		}
		try 
		{
			MTable table = MTable.get( getCtx(), getAD_Table_ID() );
			
			PO po = getPO(table);
			//  If the record was deleted, po will be null.  Recreate the record.
			if ( po == null && getAction().equals(MMigrationStep.ACTION_Delete) )
			{
				po = table.getPO(0, get_TrxName());
				// TODO: only works for single key tables
				po.set_ValueNoCheck(po.get_KeyColumns()[0], getRecord_ID() );
				po.setIsDirectLoad(true);

				// Recover the back up values of the deleted record
				for (MMigrationData data : m_migrationData )
				{
					if (!data.isActive())
						continue;

					String value = data.getBackupValue();
					if ( data.isBackupNull() )
						value = null;

					MColumn column = (MColumn) data.getAD_Column();
                    if(column == null)
                        continue;

					po.set_ValueNoCheck(column.getColumnName(), stringToObject(column, value));
				}
				//	Save PO BR [ 440 ]
				po.saveEx();
			}

			// If the record was inserted, delete it.
			if ( getAction().equals(MMigrationStep.ACTION_Insert) && po != null)  {
				//  Force delete to remove processed records and entity types
				if (po instanceof  MEntityType) {
					MEntityType entityType = (MEntityType) po;
					entityType.setIsDeleteForced(true);
					entityType.delete(true , get_TrxName());
				} else {
					po.deleteEx(true, get_TrxName());
				}
				//TODO column sync database?
			}
			// If the record was updated, set the values back to the old values.
			else if ( getAction().equals(MMigrationStep.ACTION_Update) && po != null) {
				boolean syncColumn = false;
				boolean isColumn = po instanceof MColumn;
				for (MMigrationData data : m_migrationData ) {
					String value = data.getOldValue();
					if ( data.isOldNull() )
						value = null;

					MColumn column = (MColumn) data.getAD_Column();
                    if(column == null)
                        continue;
                    //	Verify if is a column
                    String oldDataType = "";
                    if(isColumn
                    		&& column.getColumnName().equals(I_AD_Column.COLUMNNAME_AD_Reference_ID)) {
						oldDataType = DisplayType.getSQLDataType(
								po.get_ValueAsInt(I_AD_Column.COLUMNNAME_AD_Reference_ID), 
								column.getColumnName(), 
								po.get_ValueAsInt(I_AD_Column.COLUMNNAME_FieldLength), 
								po.get_ValueAsInt(I_AD_Column.COLUMNNAME_AD_Reference_Value_ID));
					}
                    //	
					po.set_ValueNoCheck(column.getColumnName(), stringToObject(column, value));
					//	For column
					if(isColumn
							&& !syncColumn) {
						if(!column.getColumnName().equals(I_AD_Column.COLUMNNAME_AD_Reference_ID)) {
							syncColumn = MColumn.isForSynchronizeColumn(column.getColumnName());
						} else {
							String newDataType = DisplayType.getSQLDataType(
									po.get_ValueAsInt(I_AD_Column.COLUMNNAME_AD_Reference_ID), 
									column.getColumnName(), 
									po.get_ValueAsInt(I_AD_Column.COLUMNNAME_FieldLength), 
									po.get_ValueAsInt(I_AD_Column.COLUMNNAME_AD_Reference_Value_ID));
							//	Validate
							syncColumn = !oldDataType.equals(newDataType);
						}
					}
				}
				po.saveEx();
				
				//  Synchronize the AD_Column changes with the database.
				if ( po instanceof MColumn ) {
					MColumn col = (MColumn) po;
					if (!col.isVirtualColumn()
							&& syncColumn) {
						log.log(Level.CONFIG, "Synchronizing column: " + col.toString() 
								+ " in table: " + MTable.get(Env.getCtx(), col.getAD_Table_ID()));
						col.syncDatabase();
					}
				}
			}
		} catch (Exception e) {
			bailout("Rollback failed. " + e.toString(), e);
		}
		setStatusCode(MMigrationStep.STATUSCODE_Unapplied);
		setApply(MMigrationStep.APPLY_Apply);
		setErrorMsg(null);
		saveEx();
		return "successfully rolled back";
	}
	
	private void getData() {
		String where = "AD_MigrationStep_ID = " + getAD_MigrationStep_ID();
		m_migrationData = MTable.get(getCtx(), MMigrationData.Table_ID)
		.createQuery(where, null)  // null trx to not lock the table
		.setOnlyActiveRecords(true)
		.list();
		if (m_migrationData.size() == 0)
			log.fine("No migration data for step: " + toString());
	}
	
	private List<MMigrationData> getKeyData() {
		String where = "AD_MigrationStep_ID = " + getAD_MigrationStep_ID();
		where += " AND EXISTS (SELECT 1 FROM AD_Column c " +
				" WHERE c.AD_Column_ID = AD_MigrationData.AD_Column_ID" +
				" AND (c.isKey = 'Y' OR c.isParent = 'Y'))";
		
		return MTable.get(getCtx(), MMigrationData.Table_ID)
		.createQuery(where, null) // Null trx to not lock the table
		.setOnlyActiveRecords(true)
		.list();
	}

	public Node toXmlNode(Document document) {

		Element step = document.createElement("Step");
		step.setAttribute("SeqNo", Integer.toString(getSeqNo()));
		step.setAttribute("StepType", getStepType());
		if ( getComments() != null ) 
		{
			Element comment = document.createElement("Comments");
			step.appendChild(comment);
			comment.appendChild(document.createTextNode(getComments()));
		} 
		if ( MMigrationStep.STEPTYPE_ApplicationDictionary.equals(getStepType()) )
		{
			MTable table = (MTable) getAD_Table();
			Element po = document.createElement("PO");
			step.appendChild(po);
			po.setAttribute("Table", table.getTableName());
			po.setAttribute("Action", getAction());
			po.setAttribute("AD_Table_ID", Integer.toString(getAD_Table_ID()));
			po.setAttribute("Record_ID", Integer.toString(getRecord_ID()));

			for ( MMigrationData datum : m_migrationData )
			{
				I_AD_Column column = datum.getAD_Column();
				if (column == null)
					continue;

				po.appendChild(datum.toXmlNode(this, document));
			}

		}
		else if ( MMigrationStep.STEPTYPE_SQLStatement.equals(getStepType()) )
		{
			step.setAttribute("DBType", getDBType());
			step.setAttribute("Parse", isParse() ? "Y":"N");
			
			if ( getSQLStatement() != null )
			{
				Element sql = document.createElement("SQLStatement");
				step.appendChild(sql);
				sql.appendChild(document.createTextNode(getSQLStatement()));
			}
			if ( getRollbackStatement() != null )
			{
				Element rollback = document.createElement("RollbackStatement");
				step.appendChild(rollback);
				rollback.appendChild(document.createTextNode(getRollbackStatement()));
			}
		}

		return step;
	}

	public static void fromXmlNode(MMigration parent, Node stepNode) {
		MMigrationStep mstep = new MMigrationStep(parent);
		Element step = (Element) stepNode;
		mstep.setSeqNo(Integer.parseInt(step.getAttribute("SeqNo")));
		mstep.setStepType(step.getAttribute("StepType"));
		mstep.setStatusCode(MMigrationStep.STATUSCODE_Unapplied);
		mstep.saveEx();
		
		Node comment = (Element) step.getElementsByTagName("Comments").item(0);
		if ( comment != null )
			mstep.setComments(comment.getTextContent());
		if ( MMigrationStep.STEPTYPE_ApplicationDictionary.equals(mstep.getStepType()) )
		{
			NodeList children = step.getElementsByTagName("PO");
			for ( int i = 0; i < children.getLength(); i++ )
			{
				Element element = (Element) children.item(i);
				mstep.setAction(element.getAttribute("Action"));
				mstep.setAD_Table_ID(Integer.parseInt(element.getAttribute("AD_Table_ID")));
				mstep.setRecord_ID(Integer.parseInt(element.getAttribute("Record_ID")));
				NodeList data = element.getElementsByTagName("Data");
				for ( int j =0 ; j < data.getLength(); j++ )
				{
					MMigrationData.fromXmlNode(mstep, data.item(j));
				}
			}
		}
		else if ( MMigrationStep.STEPTYPE_SQLStatement.equals(mstep.getStepType()) )
		{
			mstep.setDBType(step.getAttribute("DBType"));
			
			// If Parse is defined, set it accordingly, else, use the default or ignore
			if (!step.getAttribute("Parse").equals("")) {
				mstep.setParse("Y".equals(step.getAttribute("Parse")));
			}
			
			Node sql = step.getElementsByTagName("SQLStatement").item(0);
			if ( sql != null )
				mstep.setSQLStatement(sql.getTextContent());
			sql = step.getElementsByTagName("RollbackStatement").item(0);
			if ( sql != null )
				mstep.setRollbackStatement(sql.getTextContent());
		}
		
		mstep.saveEx();
		log.log(Level.CONFIG, mstep.getAD_Migration().toString() + ": Step " + mstep.getSeqNo() + " loaded");

	}

	/**
	 * get parent migration
	 */
	public MMigration getParent() {
		
		if (parent == null)
			parent = (MMigration) getAD_Migration();
		//	
		return parent;
	}
	
	/**
	 * Set Current Parent
	 * @param parent
	 */
	public void setParent(MMigration parent) {
		//	Validate Parent
		if(parent == null
				|| parent.getAD_Migration_ID() != getAD_Migration_ID())
			return;
		//	Set Parent
		this.parent = parent;
		isAllMigration = true;
	}
	
	/**
	 * 	Before Delete
	 *	@return true of it can be deleted
	 */
	protected boolean beforeDelete ()
	{
		for (MMigrationData data : m_migrationData) {
			data.deleteEx(true);
		}
		return true;
	}	//	beforeDelete

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
