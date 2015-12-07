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
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import javax.xml.parsers.ParserConfigurationException;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.MigrationStepApply;
import org.compiere.process.MigrationStepRollback;
import org.compiere.process.ProcessInfo;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Trx;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MMigration extends X_AD_Migration {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5145941967716336078L;

	/**	Logger	*/
	private CLogger	log	= CLogger.getCLogger (MMigration.class);
	private boolean isFailOnError = true;
	private boolean isMigrationScriptBatch = false;

	public boolean isFailOnError() {
		return isFailOnError;
	}

	public void setFailOnError(boolean isFailOnError) {
		this.isFailOnError = isFailOnError;
	}

	public void setMigrationScriptBatch(boolean isMigrationScriptBatch)
	{
		this.isMigrationScriptBatch =  isMigrationScriptBatch;
	}

	public boolean isMigrationScriptBatch()
	{
		return  this.isMigrationScriptBatch;
	}

	public MMigration(Properties ctx, int AD_Migration_ID, String trxName) {
		super(ctx, AD_Migration_ID, trxName);		
	}

	public MMigration(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public void apply() {

		try{
			for ( MMigrationStep step : getSteps(false) )
			{
				if (!step.isActive())
					continue;

				if (MMigrationStep.STATUSCODE_Applied.equals(step.getStatusCode())) {
					log.log(Level.CONFIG, step.toString() + " ---> Migration Step already applied - skipping.");
					continue;
				}

				stepApply(step);
			}
		}
		catch (Exception e)
		{
			if (isFailOnError())    // abort on first error
				throw new AdempiereException(e);

		}
	}

	public void stepApply(MMigrationStep step)
	{
		Trx.run(trxName -> {
			int processId = 53174; // Apply migration step
			MPInstance instance = new MPInstance(step.getCtx() , processId, step.getAD_MigrationStep_ID());
			instance.saveEx();

			MPInstancePara parameter = new MPInstancePara(instance,10);
			parameter.setParameter("MigrationScriptBatch", isMigrationScriptBatch());
			parameter.saveEx();

			ProcessInfo pi = new ProcessInfo("Apply migration step", processId);
			pi.setAD_PInstance_ID(instance.getAD_PInstance_ID());
			pi.setRecord_ID(step.getAD_MigrationStep_ID());


			MigrationStepApply migrationStepApply = new MigrationStepApply();
			migrationStepApply.startProcess(step.getCtx() , pi , Trx.get(trxName, false));
			log.log(Level.CONFIG, "Process=" + pi.getTitle() + " Error="+pi.isError() + " Summary=" + pi.getSummary());
		});

	}

	public void rollback() {
		try {
			for (MMigrationStep step : getSteps(true)) {
				stepRollback(step);
			}
		}
		catch (Exception e)
		{
			if (isFailOnError())    // abort on first error
				throw new AdempiereException(e);
		}
	}

	public void stepRollback(MMigrationStep step)
	{
		Trx.run(trxName -> {
			int processId = 0; // Apply migration stecp
			MPInstance instance = new MPInstance(step.getCtx() , processId, step.getAD_MigrationStep_ID());
			instance.saveEx();

			MPInstancePara parameter = new MPInstancePara(instance,10);
			parameter.setParameter("MigrationScriptBatch", isMigrationScriptBatch());
			parameter.saveEx();

			ProcessInfo pi = new ProcessInfo("Apply migration step", processId);
			pi.setAD_PInstance_ID(instance.getAD_PInstance_ID());
			pi.setRecord_ID(step.getAD_MigrationStep_ID());
			MigrationStepRollback migrationStepRollback = new MigrationStepRollback();
			migrationStepRollback.startProcess(step.getCtx() , pi , Trx.get(trxName, false));
			log.log(Level.CONFIG, "Process=" + pi.getTitle() + " Error="+pi.isError() + " Summary=" + pi.getSummary());
		});

	}
	
	public void updateStatus() {
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
		// overlaps with unapplied
		//else if ( applied <= 0 )
		//	setStatusCode(MMigration.STATUSCODE_Failed);

		saveEx(get_TrxName());
		log.log(Level.CONFIG, this.toString() + " ---> " + status + " (" + getStatusCode() + ")");
	}
	
	private List<MMigrationStep> getSteps(boolean rollback) {
		String where = "AD_Migration_ID = " + getAD_Migration_ID();
		String order = rollback ? "SeqNo DESC" : "SeqNo ASC";
		return MTable.get(getCtx(), MMigrationStep.Table_ID)
		//.createQuery(where, get_TrxName())  // locks the table
		.createQuery(where, null)  // won't lock the table
		.setOnlyActiveRecords(true)
		.setOrderBy(order)
		.list();
	}
	
	public static List<MMigration> getMigrations(Properties ctx, Boolean processed, String trxName) {
		String where = "Processed = " + (processed ? "'Y'" : "'N'");
		return MTable.get(ctx, MMigration.Table_ID)
		.createQuery(where, trxName)  // locks the table
		.setOnlyActiveRecords(true)
		.list();
	}

	//public static boolean updated = false;
	
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
		
		String seqNo = element.getAttribute("SeqNo");
		String entityType = element.getAttribute("EntityType");
		String releaseNo = element.getAttribute("ReleaseNo");
		
		
		String where = "Name = ?"
			+ " AND SeqNo = ?"
			+ " AND EntityType = ?"
			+ " AND ReleaseNo = ?";
		MMigration migration = new Query(ctx, MMigration.Table_Name, where, trxName)
		.setParameters(name, Integer.parseInt(seqNo), entityType, releaseNo).firstOnly();
		if ( migration != null && migration.getAD_Migration_ID() > 0)
			return migration;  // already exists (TODO: update?)

		migration = new MMigration(ctx, 0, trxName);
		migration.setName(name);
		migration.setSeqNo(Integer.parseInt(seqNo));
		migration.setEntityType(entityType);
		migration.setReleaseNo(releaseNo);
		migration.saveEx();

		Node comment = (Element) element.getElementsByTagName("Comments").item(0);
		if ( comment != null )
			migration.setComments(comment.getTextContent());
		
		NodeList children = element.getElementsByTagName("Step");
		for ( int i = 0; i < children.getLength(); i++ )
		{
			Element step = (Element) children.item(i);
			if ( "Step".equals(step.getTagName()))
				MMigrationStep.fromXmlNode(migration, step);
				Trx.get(trxName, false).commit(true);
		}
		
		migration.saveEx();
		return migration;
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
		
		
		for (MMigrationStep step : getSteps(false) )
		{
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
		for (MMigrationStep step : getSteps(false)) {
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
		return "Migration " + getSeqNo() + " - " + getName() + " - " + this.getReleaseNo() + " (" + this.getEntityType() + ")";
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
			
			for (MMigrationStep step : getSteps(false)) {
				log.log(Level.CONFIG, "   Deleting step: " + step.toString());
				step.deleteEx(true);
			}
			this.saveEx();
		}
	}
}