/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/

package org.spin.process.rpl.exp;

import java.util.concurrent.atomic.AtomicInteger;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.process.rpl.exp.ExportHelper;
import org.compiere.model.MClient;
import org.compiere.model.MReplicationDocument;
import org.compiere.model.MReplicationStrategy;
import org.compiere.model.MReplicationTable;
import org.compiere.model.MTable;
import org.compiere.model.ModelValidator;
import org.compiere.model.Query;
import org.compiere.util.Env;

/** 
 * 	Generated Process for (Test Replication Strategy)
 *  @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com 
 *  @version Release 3.9.3
 */
public class TestReplicationStrategy extends TestReplicationStrategyAbstract {
	@Override
	protected String doIt() throws Exception {
		MReplicationStrategy replicationStrategy = new MReplicationStrategy(getCtx(), getReplicationStrategyId(), get_TrxName());
		//	Validate role access
		if(!replicationStrategy.validateRole(Env.getAD_Role_ID(getCtx()))) {
			throw new AdempiereException("@AD_ReplicationStrategy_ID@ @AD_ReplicationRoleAccess_ID@ @Notfound@");
		}
		StringBuffer orgAccess = new StringBuffer();
		replicationStrategy.getOrgAccess().forEach(organizationAccess -> {
			if(orgAccess.length() > 0) {
				orgAccess.append(", ");
			}
			//	Add to criteria
			orgAccess.append(organizationAccess.getAD_Org_ID());
		});
		//	
		if(orgAccess.length() == 0) {
			throw new AdempiereException("@AD_ReplicationStrategy_ID@ @AD_ReplicationOrgAccess_ID@ @IsMandatory@");
		}
		//	Get client
		MClient client = MClient.get(getCtx());
		AtomicInteger replicated = new AtomicInteger();
		//	Test It
		replicationStrategy.getReplicationTables().forEach(replicationTable -> {
			MTable table = MTable.get(getCtx(), replicationTable.getAD_Table_ID());
			new Query(getCtx(), table.getTableName(), "Updated >= ? AND Updated <= ? AND AD_Org_ID IN(" + orgAccess+ ")", get_TrxName())
				.setParameters(getUpdatedDate(), getUpdatedDateTo())
				.setClient_ID()
				.getIDsAsList()
				.forEach(entityId -> {
					if (replicationTable != null) {
						ExportHelper exportHelper = new ExportHelper(client, replicationStrategy);
						try {
							exportHelper.exportRecord(table.getPO(entityId, get_TrxName()), (MReplicationTable)replicationTable, ModelValidator.TYPE_AFTER_CHANGE);
							replicated.addAndGet(1);
						} catch (Exception exeption) {
							log.severe(exeption.getLocalizedMessage());
						}
					}
				});
		});
		//	Documents
		replicationStrategy.getReplicationDocuments().forEach(replicationDocument -> {
			MTable table = MTable.get(getCtx(), replicationDocument.getAD_Table_ID());
			new Query(getCtx(), table.getTableName(), "Updated >= ? AND Updated <= ? AND AD_Org_ID IN(" + orgAccess+ ")", get_TrxName())
				.setParameters(getUpdatedDate(), getUpdatedDateTo())
				.setClient_ID()
				.getIDsAsList()
				.forEach(entityId -> {
					if (replicationDocument != null) {
						ExportHelper exportHelper = new ExportHelper(client, replicationStrategy);
						try {
							exportHelper.exportRecord(table.getPO(entityId, get_TrxName()), (MReplicationDocument)replicationDocument, ModelValidator.TYPE_AFTER_CHANGE);
							replicated.addAndGet(1);
						} catch (Exception exeption) {
							log.severe(exeption.getLocalizedMessage());
						}
					}
				});
		});
		return "@Exported@: " + replicated.get();
	}
}