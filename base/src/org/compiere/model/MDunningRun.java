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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.adempiere.core.domains.models.I_C_DunningLevel;
import org.adempiere.core.domains.models.I_C_DunningRun;
import org.adempiere.core.domains.models.I_C_DunningRunEntry;
import org.adempiere.core.domains.models.X_C_DunningRun;

/**
 *	Dunning Run Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MDunningRun.java,v 1.2 2006/07/30 00:51:03 jjanke Exp $
 *  
 *  FR 2872010 - Dunning Run for a complete Dunning (not just level) - Developer: Carlos Ruiz - globalqss - Sponsor: Metas
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/1494">
 * 		@see FR [ 1494 ] Translation is not considerated for Dunning Run</a>
 */
public class MDunningRun extends X_C_DunningRun
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6858939271415643483L;

	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_DunningRun_ID id
	 *	@param trxName transaction
	 */
	public MDunningRun (Properties ctx, int C_DunningRun_ID, String trxName)
	{
		super (ctx, C_DunningRun_ID, trxName);
		if (C_DunningRun_ID == 0)
		{
		//	setC_DunningLevel_ID (0);
			setDunningDate (new Timestamp(System.currentTimeMillis()));
			setProcessed (false);
		}	
	}	//	MDunningRun

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MDunningRun (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MDunningRun

	private List<MDunningRunEntry>	runEntries = new ArrayList<MDunningRunEntry>();
	private List<MDunningLevel> levels = new ArrayList<MDunningLevel>();
	
	/**
	 * 	Get Dunning Levels
	 *	@return array of level
	 */
	public List<MDunningLevel> getLevels() {
		if (levels != null
				&& levels.size() > 0)
			return levels;

		if (getC_DunningLevel_ID() > 0) {
			// just one level
			levels = new Query(
					getCtx(),
					I_C_DunningLevel.Table_Name,
					"C_Dunning_ID=? AND C_DunningLevel_ID=?",
					get_TrxName())
			.setOnlyActiveRecords(true)
			.setParameters(getC_Dunning_ID(), getC_DunningLevel_ID())
			.setOrderBy("DaysAfterDue DESC, C_DunningLevel_ID")
			.list();
		} else {
			// all levels of the dun
			levels = new Query(
					getCtx(),
					MDunningLevel.Table_Name,
					"C_Dunning_ID=?",
					get_TrxName())
			.setOnlyActiveRecords(true)
			.setParameters(getC_Dunning_ID())
			.setOrderBy("DaysAfterDue DESC, C_DunningLevel_ID")
			.list();
		}
		//	Return levels list
		return levels;
	}
	
	/**
	 * 	Get Entries
	 * 	@param requery requery
	 *	@return entries
	 */
	public List<MDunningRunEntry> getEntries (boolean requery) {
		return getEntries(requery, false);
	}
	
	/**
	 * 	Get Entries
	 * 	@param requery requery requery
	 *  @param onlyInvoices only invoices
	 *	@return entries
	 */
	public List<MDunningRunEntry> getEntries (boolean requery, boolean onlyInvoices) {
		if (runEntries != null
				&& runEntries.size() > 0
				&& !requery)
			return runEntries;
		//	Search Dunning Run Entry
		new Query(
				getCtx(),
				I_C_DunningRunEntry.Table_Name,
				"C_DunningRun_ID=?",
				get_TrxName())
		.setOnlyActiveRecords(true)
		.setParameters(getC_DunningRun_ID())
		.setOrderBy("C_DunningLevel_ID, C_DunningRunEntry_ID")
		.<MDunningRunEntry>list().stream().forEach(entry -> {
			if (!(onlyInvoices && entry.hasInvoices())) {
				runEntries.add(entry);
			}
		});
		return runEntries;
	}	//	getEntries
	
	/**
	 * Get dunning run
	 * @param onlyUnprocessed unprocessed dunning
	 * @param ctx
	 * @return
	 */
	public static List<MDunningRun> getDunningRunList(Properties ctx, boolean onlyUnprocessed) {
		//	Search Dunning Run Entry
		return new Query(
				ctx,
				I_C_DunningRun.Table_Name,
				(onlyUnprocessed? "Processed = 'N'": ""),
				null)
		.setOnlyActiveRecords(true)
		.setClient_ID()
		.setOrderBy(I_C_DunningRun.COLUMNNAME_DunningDate)
		.<MDunningRun>list();
	}	//	getEntries
	
	/**
	 * 	Delete all Entries
	 * 	@param force delete also processed records
	 *	@return true if deleted
	 */
	public boolean deleteEntries(boolean force) {
		getEntries(true);
		for (MDunningRunEntry entry : runEntries) {
			entry.deleteEx(force);
		}
		boolean ok = getEntries(true).size() == 0;
		if (ok)
			runEntries = new ArrayList<MDunningRunEntry>();
		return ok;
	}	//	deleteEntries
	
	/**
	 * 	Get/Create Entry for BPartner
	 *	@param bPartnerId business partner
	 *	@param currencyId currency
	 *	@param salesRepId sales rep
	 *	@param dunningLevelId dunning level
	 *	@return entry
	 */
	public MDunningRunEntry getEntry (int bPartnerId, int currencyId, int salesRepId, int dunningLevelId) {
		// TODO: Related BP
		int bPartnerRelatedId = bPartnerId;
		//
		getEntries(false);
		for (MDunningRunEntry entry : runEntries) {
			if (entry.getC_BPartner_ID() == bPartnerRelatedId 
					&& entry.getC_DunningLevel_ID() == dunningLevelId) {
				return entry;
			}
		}
		//	New Entry
		MDunningRunEntry entry = new MDunningRunEntry (this);
		MBPartner bp = new MBPartner (getCtx(), bPartnerRelatedId, get_TrxName());
		entry.setBPartner(bp, true);	//	AR hardcoded
		//
		if (entry.getSalesRep_ID() == 0) {
			entry.setSalesRep_ID (salesRepId);
		}
		entry.setC_Currency_ID (currencyId);
		entry.setC_DunningLevel_ID(dunningLevelId);
		//
		runEntries = new ArrayList<MDunningRunEntry>();
		return entry;
	}	//	getEntry

}	//	MDunningRun
