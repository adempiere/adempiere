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
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MRefList;
import org.compiere.model.PO;
import org.compiere.util.CLogger;

/**
 *  Asset Addition Model
 *	@author Teo Sarca, SC ARHIPAC SERVICE SRL
 *
 *
 */
public class MAssetChange extends X_A_Asset_Change
{
	/**
	 *
	 */
	private static final long serialVersionUID = 5906751299228645904L;
	
	/**	Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MAssetChange.class);
	
	/**
	 * 	Default Constructor
	 *	@param ctx context
	 *	@param M_InventoryLine_ID line
	 */
	public MAssetChange (Properties ctx, int A_Asset_Change_ID, String trxName)
	{
		super (ctx, A_Asset_Change_ID, trxName);
	}	//	MAssetChange
	
	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 */
	public MAssetChange (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}	//	MInventoryLine

		/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		String textDetails = getTextDetails();
		if (textDetails == null || textDetails.length() == 0) {
			setTextDetails(MRefList.getListDescription (getCtx(),"A_Update_Type" , getChangeType()));
		}
		return true;
	}	//	beforeSave

	public static MAssetChange createAddition(MAssetAddition assetAdd, MDepreciationWorkfile assetwk) {
		MAssetChange change = new MAssetChange (assetAdd.getCtx(), 0, assetAdd.get_TrxName());
		change.setAD_Org_ID(assetAdd.getAD_Org_ID()); //@win added
		change.setA_Asset_ID(assetAdd.getA_Asset_ID());
		//change.setA_QTY_Current(assetAdd.getA_QTY_Current());
		change.setChangeType("ADD");
		change.setTextDetails(MRefList.getListDescription (assetAdd.getCtx(),"A_Update_Type" , "ADD"));
		change.setPostingType(assetwk.getPostingType());
		change.setAssetValueAmt(assetAdd.getAssetValueAmt());
		change.setA_QTY_Current(assetAdd.getA_QTY_Current());	            
		change.saveEx();
		
		return change;
	}
	
	// Goodwill - update history for Asset Disposal
	/**
	 * Create Disposal
	 * @param assetDisposed
	 * @param assetwk
	 * @return change
	 */
	public static MAssetChange createDisposal(MAssetDisposed assetDisposed, MDepreciationWorkfile assetwk) {
		MAssetChange change = new MAssetChange(assetDisposed.getCtx(), 0, assetDisposed.get_TrxName());
		change.setAD_Org_ID(assetDisposed.getAD_Org_ID());
		change.setA_Asset_ID(assetDisposed.getA_Asset_ID());
		change.setChangeType("DIS");
		change.setTextDetails(MRefList.getListDescription(assetDisposed.getCtx(), "A_Update_Type", "DIS"));
		change.setPostingType(assetwk.getPostingType());
		change.setAssetValueAmt(assetDisposed.getA_Disposal_Amt());
		change.setA_QTY_Current(assetwk.getA_QTY_Current());
		change.saveEx();
		
		return change;
	}
	
	public static MAssetChange create(Properties ctx, String changeType, PO[] pos, String trxName) {
		return create(ctx, changeType, pos, false, trxName);
	}
	
	/**
	 * TODO
	 * @param ctx
	 * @param changeType
	 * @param pos
	 * @param trxName
	 * @return
	 */
	public static MAssetChange createAndSave(Properties ctx, String changeType, PO[] pos, String trxName) {
		return null;
		//~ return create(ctx, changeType, pos, true, trxName);
	}
	
	public static MAssetChange create(Properties ctx, String changeType, PO[] pos, boolean save, String trxName) {
		if (s_log.isLoggable(Level.FINE)) s_log.fine("Entering: changeType=" + changeType);
		if (pos == null || pos.length == 0) {
			s_log.fine("Entering/Leaving: POs is empty");
			return null;
		}
		MAssetChange change = new MAssetChange (ctx, 0, trxName);
		change.setChangeType(changeType);
		for (PO po : pos) {
			change.addChanges(po);
		}
		if (save) {
			change.saveEx();
		}
		//
		if (s_log.isLoggable(Level.FINE)) s_log.fine("Leaving: change=" + change);
		return change;
	}
	
	public void addChanges(PO po) {
		if (log.isLoggable(Level.FINE)) log.fine("Entering: po=" + po);
		if (po == null) {
			return;
		}
		/* arhipac: teo_sarca: TODO need to integrate
		for(int idx = 0; idx < po.get_ColumnCount_P(); idx++) {
			//~ if(!po.is_ValueChanged(idx)) {
				//~ continue;
			//~ }
			String colName = po.get_ColumnName_P(idx);
			int idx2 = get_ColumnIndex(colName);
			if(idx2 < 0) {
				if(CLogMgt.isLevelFine()) log.fine("Setting " + colName + ": SKIP (idx2 < 0)");
				continue;
			}
			
			Object value = po.get_Value(idx2);
			set_Value(colName, value);
			if(CLogMgt.isLevelFine()) log.fine("Setting " + colName + "=" + value + " (from " + po.getClass() + ", idx=" + idx + ", idx2=" + idx2 + ")");
		}
		*/
		//
		if (log.isLoggable(Level.FINE)) log.fine("Leaving: po=" + po);
	}
	/** ARHIPAC: TEO: END ------------------------------------------------------------------ */
}	//	MAssetChange