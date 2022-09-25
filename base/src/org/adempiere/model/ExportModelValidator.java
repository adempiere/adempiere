/**********************************************************************
* This file is part of Adempiere ERP Bazaar                           *
* http://www.adempiere.org                                            *
*                                                                     *
* Copyright (C) Trifon Trifonov.                                      *
* Copyright (C) Contributors                                          *
*                                                                     *
* This program is free software; you can redistribute it and/or       *
* modify it under the terms of the GNU General Public License         *
* as published by the Free Software Foundation; either version 2      *
* of the License, or (at your option) any later version.              *
*                                                                     *
* This program is distributed in the hope that it will be useful,     *
* but WITHOUT ANY WARRANTY; without even the implied warranty of      *
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the        *
* GNU General Public License for more details.                        *
*                                                                     *
* You should have received a copy of the GNU General Public License   *
* along with this program; if not, write to the Free Software         *
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,          *
* MA 02110-1301, USA.                                                 *
*                                                                     *
* Contributors:                                                       *
* - Trifon Trifonov (trifonnt@users.sourceforge.net)                  *
*                                                                     *
* Sponsors:                                                           *
* - e-Evolution (http://www.e-evolution.com)                          *
***********************************************************************/

package org.adempiere.model;

import org.adempiere.core.domains.models.X_AD_ReplicationDocument;
import org.adempiere.core.domains.models.X_AD_ReplicationTable;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.process.rpl.exp.ExportHelper;
import org.compiere.model.MClient;
import org.compiere.model.MReplicationDocument;
import org.compiere.model.MReplicationStrategy;
import org.compiere.model.MReplicationTable;
import org.compiere.model.MTable;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import java.util.Properties;


/**
 *	Export Validator which is responsible to create XML document.
 *	
 *	@author Trifon Trifonov
 *	@author victor.perez@e-evolution.com, www.e-evolution.com
 * <li> BF2875989 Deactivate replication records are include to replication
 * <li> https://sourceforge.net/tracker/?func=detail&aid=2875989&group_id=176962&atid=879332
 * <li>[ 2195090 ] Stabilization of replication
 * <li>https://sourceforge.net/tracker/?func=detail&atid=879332&aid=2936561&group_id=176962
 * <li> BF2947615 The document recplicacion not working
 * <li> https://sourceforge.net/tracker/?func=detail&aid=2947615&group_id=176962&atid=879332
 * <li> The Replication should can use the Strategy from the org
 * <li> https://sourceforge.net/tracker/?func=detail&aid=3014094&group_id=176962&atid=879335
 *	@author Yamel Senih, ySenih@erpya.com, ERPCyA http://www.erpya.com
 *	<li> Add support to validate error fromreplication strategy
 *	@version $Id$
 */
public class ExportModelValidator implements ModelValidator {
    	/** Context variable which says if replication is enabled */
	public static final String CTX_IsReplicationEnabled = "#IsReplicationEnabled";
	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(ExportModelValidator.class);
	
	/** Client			*/
	private int clientId = -1;
	
	/** Organization	*/
	private int orgId = -1;
	
	/** Role			*/
	private int roleId = -1;
	
	/** User			*/
	private int userId = -1;

	/** Company**/
	MClient client = null;

	/** ModelValidationEngine engine **/
	ModelValidationEngine modelValidationEngine = null;
	
	/**
	 *	Constructor.
	 *	The class is instantiated when logging in and client is selected/known
	 */
	public ExportModelValidator () {
		super ();
	}
	
	/**
	 *	Initialize Validation
	 *	@param modelValidationEngine validation engine
	 *	@param client client
	 */
	public void initialize (ModelValidationEngine modelValidationEngine, MClient client) {
	    this.modelValidationEngine = modelValidationEngine;
	    this.client = client;
	    if (client != null)
	    {
			clientId = client.getAD_Client_ID();
			log.info(client.toString());
	    }
	    else 
	    {
			log.warning("Export Model Validator cannot be used as a global validator, it needs to be defined in a per-client (tenant) basis");
			return;
	    }		
	}

    /**
     *	Model Change of a monitored Table.
     *	Called after PO.beforeSave/PO.beforeDelete
     *	@param po persistent object
     *	@param type TYPE_
     *	@return error message or null
     *	@exception Exception if the recipient wishes the change to be not accept.
     */
	public String modelChange (PO po, int type) throws Exception {
		//String Mode = "Table";
		log.info("po.get_TableName() = " + po.get_TableName());
		if (   type == TYPE_AFTER_CHANGE 
				|| type == TYPE_AFTER_NEW
				|| type == TYPE_BEFORE_DELETE) // After Change or After New
				{
					MReplicationStrategy.getByOrgAndRole(po.getCtx() , orgId , roleId, po.get_TrxName()).stream().forEach( replicationStrategy -> {
						MReplicationTable replicationTable = MReplicationStrategy.getReplicationTable(po.getCtx(), replicationStrategy.get_ID(), po.get_Table_ID());
						if (replicationTable != null) {
							ExportHelper exportHelper = new ExportHelper(client, replicationStrategy);
							try {
								exportHelper.exportRecord(po, replicationTable, type);
							} catch (Exception exeption) {
								if(replicationStrategy.isValidateError()) {
									throw new AdempiereException(exeption);
								} else {
									log.warning(exeption.getLocalizedMessage());
								}
							}
						}
					});
				}
		return null;
	}
	
	/**
	 *	Validate Document.
	 *	Called as first step of DocAction.prepareIt 
     *	when you called addDocValidate for the table.
	 *	@param po persistent object
	 *	@param type see TIMING_ constants
     *	@return error message or null
	 * @throws Exception 
	 */
	public String docValidate (PO po, int type) 
	{
		log.info("Replicate the Document = " + po.get_TableName() + " with Type = " + type);
		String result = null;
			if (   type == TIMING_AFTER_COMPLETE 
				|| type == TIMING_AFTER_CLOSE 
				|| type == TIMING_AFTER_REVERSECORRECT 
				|| type == TIMING_AFTER_VOID
				|| type == TIMING_AFTER_REACTIVATE
				//|| type == TIMING_AFTER_PREPARE
			) {
				MReplicationStrategy.getByOrgAndRole(po.getCtx() , orgId , roleId, po.get_TrxName()).stream().forEach( replicationStrategy -> {
					MReplicationDocument replicationDocument = null;
					int C_DocType_ID = po.get_ValueAsInt("C_DocType_ID");
					if (C_DocType_ID > 0) {
						replicationDocument = MReplicationStrategy.getReplicationDocument(
								po.getCtx(), replicationStrategy.get_ID(), po.get_Table_ID(), C_DocType_ID);
					} else {
						replicationDocument = MReplicationStrategy.getReplicationDocument(
								po.getCtx(),  replicationStrategy.get_ID(), po.get_Table_ID());
					}
					//	
					if (replicationDocument != null) {
						ExportHelper exportHelper = new ExportHelper(client, replicationStrategy);
						try {
							exportHelper.exportRecord(po, replicationDocument, type);
						} catch (Exception exeption) {
							if(replicationStrategy.isValidateError()) {
								throw new AdempiereException(exeption);
							} else {
								log.warning(exeption.getLocalizedMessage());
							}
						}
					}
				});
			}
		return result;
	}

	/**
	 *	User Login.
	 *	Called when preferences are set
	 *	@param orgId org
	 *	@param roleId role
	 *	@param userId user
	 *	@return error message or null
	 */
	public String login (int orgId, int roleId, int userId) {
	    Env.setContext(Env.getCtx(), CTX_IsReplicationEnabled, true);
		this.orgId = orgId;
		this.roleId = roleId;
		this.userId = userId;
		
		log.info("AD_Org_ID  =" + this.orgId);
		log.info("AD_Role_ID =" + this.roleId);
		log.info("AD_User_ID =" + this.userId);
		loadReplicationStrategy(Env.getCtx());
		return null;
	}

	
	/**
	 *	Get Client to be monitored
	 *	@return AD_Client_ID client
	 */
	public int getAD_Client_ID() {
		return clientId;
	}
	
	public void loadReplicationStrategy(Properties ctx) {
		MReplicationStrategy.getByOrgAndRole(ctx , orgId , roleId, null ).stream()
				.filter(replicationStrategy -> replicationStrategy != null)
				.forEach(replicationStrategy -> {
			for (X_AD_ReplicationTable rplTable : replicationStrategy.getReplicationTables()) {
				if (X_AD_ReplicationTable.REPLICATIONTYPE_Merge.equals(rplTable.getReplicationType())
					|| X_AD_ReplicationTable.REPLICATIONTYPE_Broadcast.equals(rplTable.getReplicationType())
					|| X_AD_ReplicationTable.REPLICATIONTYPE_Reference.equals(rplTable.getReplicationType()))
				{
					String tableName = MTable.getTableName(replicationStrategy.getCtx(), rplTable.getAD_Table_ID());
					modelValidationEngine.addModelChange(tableName, this);
				}
			}
			// Add Documents
			// We want to be informed when Replication documents are created/updated/deleted!
			for (X_AD_ReplicationDocument rplDocument : replicationStrategy.getReplicationDocuments()) {
				if (X_AD_ReplicationDocument.REPLICATIONTYPE_Merge.equals(rplDocument.getReplicationType())
					|| X_AD_ReplicationDocument.REPLICATIONTYPE_Reference.equals(rplDocument.getReplicationType()))
				{
					String tableName = MTable.getTableName(replicationStrategy.getCtx(), rplDocument.getAD_Table_ID());
					modelValidationEngine.addDocValidate(tableName, this);
				}
			}
		});
	}
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString () {
		StringBuffer stringBuffer = new StringBuffer (ExportModelValidator.class.getName());
		return stringBuffer.toString();
	}
}
