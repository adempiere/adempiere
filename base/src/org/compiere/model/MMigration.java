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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import javax.xml.parsers.ParserConfigurationException;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author Paul
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> BR [ 425 ] Table ad_reportview_trl with wrong primary key
 *		@see https://github.com/adempiere/adempiere/issues/425
 */
public class MMigration extends X_AD_Migration {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5145941967716336078L;
	
	/**	Column List to synchronizing		*/
	private ArrayList<Integer> columnList = new ArrayList<Integer>();

	/**	Logger	*/
	private CLogger	log	= CLogger.getCLogger (MMigration.class);

	/**
	 * is force
	 * @return
	 */
	public boolean isForce() {
		return isForce;
	}

	/**
	 * Set if is force
	 * @param isForce
	 */
	public void setIsForce(boolean isForce) {
		this.isForce = isForce;
	}
	
	/**	Is Force		*/
	private boolean isForce = true;

	public MMigration(Properties ctx, int AD_Migration_ID, String trxName) {
		super(ctx, AD_Migration_ID, trxName);		
	}

	public MMigration(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Apply or rollback a migration.
	 * @return A string indicating the result
	 */
	public String apply() {

		// TODO Translate the return values
		
		Boolean apply = false;
		
		// The state of the migration is determined by the status code and the apply action.
		// These values should have been set by default or by previous actions.  Valid
		// values are (status/action):
		//    STATUSCODE_APPLIED/APPLY_Rollback
		//    STATUSCODE_PartiallyApplied/APPLY_Rollback
		//    STATUSCODE_Unapplied/APPLY_Apply
		
		// The status code may have been changed by the user.  Check/reset it:
		updateStatus();
		
		// If status code is null for some reason
		if (getStatusCode() == null || getStatusCode().length() == 0)
			setStatusCode(X_AD_Migration.STATUSCODE_Unapplied);

		// If action code is null for some reason
		if (getApply() == null || getApply().length() == 0)
			setApply(X_AD_Migration.APPLY_Apply);

		// Fix improperly set status and actions based on the status code
		if ((getStatusCode().equals(X_AD_Migration.STATUSCODE_Applied)
			 || getStatusCode().equals(X_AD_Migration.STATUSCODE_PartiallyApplied)) 
			&& !getApply().equals(X_AD_Migration.APPLY_Rollback))
		{
			setApply(X_AD_Migration.APPLY_Rollback);
		}
		else if (getStatusCode().equals(X_AD_Migration.STATUSCODE_Unapplied) 
				&& !getApply().equals(X_AD_Migration.APPLY_Apply))
		{
			setApply(X_AD_Migration.APPLY_Apply);
		}
		
		// Determine whether to apply or rollback the migration
		if (getStatusCode().equals(X_AD_Migration.STATUSCODE_Unapplied))
			apply = true;

		String retVal = toString();

		try{
			//  Apply the Migration Steps
			//  Set a flag to prevent migration status updates
			Env.setContext(Env.getCtx() , "MigrationScriptBatchInProgress", "Y");
			
			// Get the set of active steps and apply each in order
			for (int stepId : getStepIds(!apply))
			{
				MMigrationStep step = new MMigrationStep(getCtx(), stepId, get_TrxName());
				step.setParent(this);
				// The migration will only be applied if all steps are unapplied.  Any partially
				// applied migration will need to be rolled back first.
				if (!apply && MMigrationStep.STATUSCODE_Unapplied.equals(step.getStatusCode())) 
				{
						log.log(Level.CONFIG, step.toString() + " ---> Migration Step unapplied - skipping.");
						continue;
				}
				//	Apply
				try {
					step.apply();
				} catch(Exception e) {
					if(!isForce) {
						throw e;
					}
				}
			}
			//	Synchronize Columns
			syncColumn();
		}
		catch (Exception e)
		{
			log.warning(e.getMessage());
			if (!isForce)    // abort on first error
			{			
				if (apply) // Try to rollback the transaction
				{
					// Set the status code to trigger a rollback
					setStatusCode(X_AD_Migration.STATUSCODE_Failed);
					apply();  // Apply/rollback
				}
				throw new AdempiereException(e.getMessage() , e);
			}
		}
		finally
		{			
			// Unset a flag to prevent migration status updates
			Env.setContext(Env.getCtx() , "MigrationScriptBatchInProgress", "");
			// Update the status of the migration
			updateStatus();
			
			// Determine the return value
			if (apply)
			{
				if ( getStatusCode().equals(STATUSCODE_Applied))
					retVal += "Migration successful";
				else if ( getStatusCode().equals(STATUSCODE_PartiallyApplied))
					retVal += "Migration partially applied. Please review migration steps for errors.";
				else if ( getStatusCode().equals(STATUSCODE_Failed) )
					retVal += "Migration failed. Please review migration steps for errors.";
				else if ( getStatusCode().equals(STATUSCODE_Unapplied) )
					retVal += "Migration not applied. Please review migration steps for errors.";
				else
					retVal += "Migration status unknown. Please review migration steps for errors.";
			}
			else 
			{
				if ( getStatusCode().equals(STATUSCODE_Unapplied))
					retVal += "Migration rollback successful.";
				else if ( getStatusCode().equals(STATUSCODE_PartiallyApplied) 
						|| getStatusCode().equals(STATUSCODE_Failed)
						|| getStatusCode().equals(STATUSCODE_Applied) )
					retVal += "Migration rollback failed. Please review migration steps for errors.";
				else 
					retVal += "Migration status unknown. Please review migration steps for errors.";				
			}
		}
		return retVal;
	}
	
	/**
	 * Update the status of a Migration to reflect the status of its steps.
	 * The status will be Applied, Partially Applied or Unapplied.
	 */
	public void updateStatus() {
		// Don't update in the middle of a batch
		if (Env.getContext(getCtx(), "MigrationScriptBatchInProgress").equals("Y") )
			return;

		StringBuilder whereBase = new StringBuilder();
		whereBase.append(MMigration.COLUMNNAME_AD_Migration_ID).append("=?");

		int total = new Query(getCtx() , MMigrationStep.Table_Name , whereBase.toString() , get_TrxName())
				.setOnlyActiveRecords(true)
				.setParameters(getAD_Migration_ID())
				.count();

		StringBuilder where = new StringBuilder(whereBase);
		where.append(" AND ").append(MMigrationStep.COLUMNNAME_StatusCode).append("=?");
		int applied = new Query(getCtx() , I_AD_MigrationStep.Table_Name , where.toString() , get_TrxName())
				.setOnlyActiveRecords(true)
				.setParameters(getAD_Migration_ID() , MMigration.STATUSCODE_Applied)
				.count();

		where = new StringBuilder(whereBase);
		where.append(" AND ").append(MMigrationStep.COLUMNNAME_StatusCode).append(" IN( ? , ? )");
		int unapplied  = new Query(getCtx() , I_AD_MigrationStep.Table_Name , where.toString() , get_TrxName())
				.setOnlyActiveRecords(true)
				.setParameters(getAD_Migration_ID() , MMigration.STATUSCODE_Failed ,  MMigration.STATUSCODE_Unapplied )
				.count();

		String status = "";
		
		if ( applied == total && applied > 0 )
		{
			setStatusCode(MMigration.STATUSCODE_Applied);
			setApply(MMigration.APPLY_Rollback);
			status = "Applied";
		}
		else if ( unapplied == total && unapplied > 0 )
		{
			setStatusCode(MMigration.STATUSCODE_Unapplied);
			setApply(MMigration.APPLY_Apply);
			status = "Unapplied";
		}
		else if ( total > applied && applied > 0 )
		{
			setStatusCode(MMigration.STATUSCODE_PartiallyApplied);
			setApply(MMigration.APPLY_Rollback);
			status = "Partially Applied";
		}

		saveEx();
		log.log(Level.CONFIG, this.toString() + " ---> " + status + " (" + getStatusCode() + ")");
	}
	
	private int[] getStepIds(boolean rollback) {
		String where = "AD_Migration_ID = " + getAD_Migration_ID();
		String order = rollback ? "SeqNo DESC" : "SeqNo ASC";
		return MTable.get(getCtx(), MMigrationStep.Table_ID)
		.createQuery(where, null)  // Use a null Trx to generate a readonly query
		.setOnlyActiveRecords(true)
		.setOrderBy(order)
		.getIDs();
	}

	public static List<MMigration> getMigrations(Properties ctx, Boolean processed, String trxName) {
		String where = "Processed = " + (processed ? "'Y'" : "'N'");
		return MTable.get(ctx, MMigration.Table_ID)
		.createQuery(where, trxName)
		.setOnlyActiveRecords(true)
		.list();
	}

	public static MMigration fromXmlNode(Properties ctx, Element element, String trxName) throws SQLException
	{		
		if ( !"Migration".equals(element.getLocalName() ) )
				return null;
		
		// Restrict the name field to the field length in case the xml has extra characters.
		MColumn col = MColumn.get(ctx, MColumn.getColumn_ID("AD_Migration", "Name"));
		int length = col.getFieldLength();
		String name = element.getAttribute("Name");
		if (name.length() > length)
			name = name.substring(0,length);
		name = name.trim();
		
		String seqNo = element.getAttribute("SeqNo").trim();
		String entityType = element.getAttribute("EntityType").trim();
		String releaseNo = element.getAttribute("ReleaseNo").trim();
		
		
		String where = "TRIM(both from Name) = ?"
			+ " AND SeqNo = ?"
			+ " AND TRIM(both from EntityType) = ?"
			+ " AND TRIM(both from ReleaseNo) = ?";
//		MMigration mmigration = new Query(ctx, MMigration.Table_Name, where, trxName) // Locks migrationtable
		MMigration mmigration = new Query(ctx, MMigration.Table_Name, where, null)
		.setParameters(name, Integer.parseInt(seqNo), entityType, releaseNo).firstOnly();
		if ( mmigration != null ) {
			return mmigration;  // already exists (TODO: update?)
		}
		mmigration = new MMigration(ctx, 0, trxName);
		mmigration.setName(name);
		mmigration.setSeqNo(Integer.parseInt(seqNo));
		mmigration.setEntityType(entityType);
		mmigration.setReleaseNo(releaseNo);

		Node comment = (Element) element.getElementsByTagName("Comments").item(0);
		if ( comment != null )
			mmigration.setComments(comment.getTextContent());

		mmigration.saveEx();

		
		NodeList children = element.getElementsByTagName("Step");
		for ( int i = 0; i < children.getLength(); i++ )
		{
			Element step = (Element) children.item(i);
			if ( "Step".equals(step.getTagName()))
				MMigrationStep.fromXmlNode(mmigration, step);
				Trx.get(trxName, false).commit(true);
		}
		
		return mmigration;
	}

	public Node toXmlNode(Document document) throws ParserConfigurationException, SAXException {

		Element migration = document.createElement("Migration");

		migration.setAttribute("SeqNo", Integer.toString(getSeqNo()));
		migration.setAttribute("Name", getName());
		migration.setAttribute("EntityType", getEntityType());
		migration.setAttribute("ReleaseNo", getReleaseNo());
		
		if ( getComments() != null ) 
		{
			Element comment = document.createElement("Comments");
			migration.appendChild(comment);
			comment.appendChild(document.createTextNode(getComments()));
		} 
		
		
		for ( int stepId : getStepIds(false) )
		{
			MMigrationStep step = new MMigrationStep(getCtx(), stepId, get_TrxName());
			log.log(Level.FINE, "Exporting step: " + step);
			migration.appendChild(step.toXmlNode(document));
		}
		
		return migration;
	}
	
	public void mergeMigration(MMigration from) {
		
		int lastSeq = DB.getSQLValue(get_TrxName(),
				"SELECT COALESCE(MAX(SeqNo),0) FROM AD_MigrationStep WHERE AD_Migration_ID = " + getAD_Migration_ID());
		
		String updateSql = "UPDATE AD_MigrationStep SET AD_Migration_ID = ?, SeqNo = SeqNo + ? WHERE AD_Migration_ID = ? ";
		Object[] params = new Object[] { getAD_Migration_ID(), lastSeq, from.getAD_Migration_ID() };
		DB.executeUpdateEx(updateSql, params, get_TrxName());
		
		try {
			DB.commit(false, get_TrxName());
			from.deleteEx(false, get_TrxName());
		} catch (IllegalStateException | SQLException e) {
			log.log(Level.SEVERE, "[" + get_TrxName() + "]", e);
		}
		
	}
	
	/**
	 * 	Before Delete
	 *	@return true if it can be deleted
	 */
	protected boolean beforeDelete ()
	{
		for ( int stepID : getStepIds(false) )
		{
			MMigrationStep step = new MMigrationStep(getCtx(), stepID, get_TrxName());
			step.deleteEx(true);
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
	
	// String representation of the Migration
	public String toString() {
		return "Migration " + getSeqNo() + " - " + getName() + " - " + this.getReleaseNo() + " (" + this.getEntityType() + ") ";
	}

	/**
	 * Clean the migration of data.  Only the header will be left.
	 * The migration should be applied and have the entity type for Dictionary 'D'.
	 * Steps and Step data will be deleted and the migration marked as "processed".
	 * The goal is to leave a record of the applied migration but reduce the database
	 * size.
	 */
	public void clean() {
		if ( getEntityType().equals("D") && getStatusCode().equals(MMigration.STATUSCODE_Applied) ) {
			log.log(Level.CONFIG, "Cleaning migration: " + this.toString());

			this.setProcessed(true);
			
			for ( int stepId : getStepIds(false) )
			{
				MMigrationStep step = new MMigrationStep(getCtx(), stepId, get_TrxName());
				log.log(Level.CONFIG, "   Deleting step: " + step.toString());
				step.deleteEx(true);
			}
			this.saveEx();
		}
	}
	
	/**
	 * Add column to array for next sync
	 * @param p_AD_Column_ID
	 */
	public void addColumnToList(int p_AD_Column_ID) {
		columnList.add(p_AD_Column_ID);
	}
	
	/**
	 * Synchronize the AD_Column changes with the database.
	 */
	public void syncColumn() {
		//	Validate size
		if(columnList.size() == 0)
			return;
		//	Iterate
		for(int columnId : columnList) {
			MColumn column = new MColumn(getCtx(), columnId, get_TrxName());
			log.log(Level.CONFIG, "Synchronizing column: " + column.toString() 
					+ " in table: " + MTable.get(Env.getCtx(), column.getAD_Table_ID()));
			column.syncDatabase();
		}
		//	flush column list
		columnList = new ArrayList<Integer>();
	}
}