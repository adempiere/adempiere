/******************************************************************************
 * The contents of this file are subject to the   Compiere License  Version 1.1
 * ("License"); You may not use this file except in compliance with the License
 * You may obtain a copy of the License at http://www.compiere.org/license.html
 * Software distributed under the License is distributed on an  "AS IS"  basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * The Original Code is             Compiere  ERP & CRM Smart Business Solution
 * The Initial Developer of the Original Code is Jorg Janke  and ComPiere, Inc.
 * Portions created by Jorg Janke are Copyright (C) 1999-2003 Jorg Janke, parts
 * created by ComPiere are Copyright (C) ComPiere, Inc.;   All Rights Reserved.
 * Contributor(s): ______________________________________.
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.apache.commons.collections.keyvalue.MultiKey;
import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.compiere.FA.feature.UseLife;
import org.compiere.FA.feature.UseLifeImpl;


/**
 *  Depreciation Workfile Model
 *	@author	Teo Sarca, SC ARHIPAC SERVICE SRL
 */
public class MDepreciationWorkfile extends X_A_Depreciation_Workfile
	implements UseLife
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3814417671427820714L;

	/**
	 * 	Default Constructor
	 *	@param ctx context
	 *	@param M_InventoryLine_ID line
	 */
	public MDepreciationWorkfile (Properties ctx, int A_Depreciation_Workfile_ID, String trxName)
	{
		super (ctx,A_Depreciation_Workfile_ID, trxName);
		if (A_Depreciation_Workfile_ID == 0)
		{
			setPostingType(POSTINGTYPE_Actual);
			setA_QTY_Current(Env.ZERO);
			setA_Asset_Cost(Env.ZERO);
			setA_Accumulated_Depr(Env.ZERO);
			setA_Period_Posted(0);
			setA_Current_Period(0);
		}
	}	//	MDepreciationWorkfile
	
	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 */
	public MDepreciationWorkfile (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}	//	MDepreciationWorkfile
	
	/**	Asset (parent)			*/
	private MAsset m_asset = null;
	
	/**	Get Asset					*/
	public MAsset getAsset() {
		return getAsset(false);
	}
	
	/**	Get asset using this trxName
	 *	@param	requery	requery asset
	 *	@return parent asset
	 */
	public MAsset getAsset(boolean requery)
	{
		if (m_asset == null || requery) {
			m_asset = MAsset.get(getCtx(), getA_Asset_ID(), get_TrxName());
		}
		if (m_asset.get_ID() <= 0) {
			m_asset = null;
		}
		return m_asset;
	}
	
	/**	Set asset
	 *	@param	asset
	 */
	public void setAsset(MAsset asset)
	{
		setA_Asset_ID(asset.get_ID());
		m_asset = asset;
	}
	
	/**	Gets asset's service date (commissioning)
	 *	@return asset service date
	 */
	
	public Timestamp getAssetServiceDate()
	{
		MAsset asset = getAsset();
		if (asset == null) {
			return null;
		}
		return asset.getAssetServiceDate();
	}
	
	
	/**	Gets asset's class
	 *	@return asset class id
	 */
	/* commented out by @win
	public int getA_Asset_Class_ID()
	{
		MAsset asset = getAsset();
		if (asset == null) {
			return 0;
		}
		return asset.getA_Asset_Class_ID();
	}
	*/ // end comment by @win
	
	/**	After save
	 *	@param	newRecord
	 *	@return true on success
	 */
	protected boolean afterSave (boolean newRecord)
	{
		if(m_buildDepreciation)
		{
			buildDepreciation();
		}
		return true;
	}
	
	
	protected boolean beforeSave (boolean newRecord)
	{
		log.info ("Entering: trxName=" + get_TrxName());
		
		// copy UseLife to A_Life
		if (newRecord) { //@win: should only update only if newrecord
			setA_Life_Period(getUseLifeMonths());
			setA_Asset_Life_Years(getUseLifeYears());
			setA_Life_Period_F(getUseLifeMonths_F());
			setA_Asset_Life_Years_F(getUseLifeYears_F());
		}
		
		// If it is fully amortized, change the state's FA
		MAsset asset = getAsset(true);
		if (MAsset.A_ASSET_STATUS_Activated.equals(asset.getA_Asset_Status())
			&& isFullyDepreciated())
		{
			asset.changeStatus(MAsset.A_ASSET_STATUS_Depreciated, null);
			asset.saveEx();
		}
		
		// Fix DateAcct
		if(is_ValueChanged(COLUMNNAME_DateAcct))
		{
			setDateAcct(TimeUtil.getMonthLastDay(getDateAcct()));
		}
		
		//
		BigDecimal cost = getA_Asset_Cost();
		BigDecimal accumDep_C = getA_Accumulated_Depr();
		setA_Asset_Remaining(cost.subtract(accumDep_C));
		BigDecimal accumDep_F = getA_Accumulated_Depr_F();
		setA_Asset_Remaining_F(cost.subtract(accumDep_F));
		
		// Financing
		{
			String mainColumnName = null;
			if (newRecord || is_ValueChanged(COLUMNNAME_A_Asset_Cost))
			{
				mainColumnName = COLUMNNAME_A_Asset_Cost;
			}
			else if (is_ValueChanged(COLUMNNAME_A_Valoare_Cofinantare))
			{
				mainColumnName = COLUMNNAME_A_Valoare_Cofinantare;
			}
			else if (is_ValueChanged(COLUMNNAME_A_Valoare_Tert))
			{
				mainColumnName = COLUMNNAME_A_Valoare_Tert;
			}
			updateFinantare(this, mainColumnName);
		}
		
		
		log.info("Leaving: trxName=" + get_TrxName() + " [RETURN TRUE]");
		return true;
	}	//	beforeSave
	
	/**	
	 * Asset is fully depreciated
	 * <ul>
	 * <li>If PostingType != ACTUAL then return false
	 * <li>Do not check your current asset
	 * </ul>
	 * @return true if the asset is fully depreciated, false otherwise
	 */
	public boolean isFullyDepreciated()
	{
		if(!getPostingType().equals(POSTINGTYPE_Actual))
		{
			return false;
		}
		
		// check if is fully depreciated
		BigDecimal remainingAmt_C = getRemainingCost(null, false);
		BigDecimal remainingAmt_F = getRemainingCost(null, true);
		if(remainingAmt_C.signum() == 0 && remainingAmt_F.signum() == 0)
		{
			//if A_Asset_Cost is 0 have a voided addition, in this case asset is not full depreciated 
			if (getA_Asset_Cost().signum() == 0)
			{
			 return false;	
			}
			//
			return true;
		}
		
		return false;
	}
	
	/**
	 *
	 */
	public MDepreciationWorkfile(MAsset asset, String postingType, MAssetGroupAcct assetgrpacct)
	{
		this(asset.getCtx(), 0, asset.get_TrxName());
		setA_Asset_ID(asset.getA_Asset_ID());
		setAD_Org_ID(asset.getAD_Org_ID()); //@win added
		setA_Asset_Cost(asset.getA_Asset_Cost());
		setA_Accumulated_Depr(asset.getA_Accumulated_Depr());
		setA_Accumulated_Depr_F(asset.getA_Accumulated_Depr_F());
		setA_Current_Period(asset.getA_Current_Period());
		
		setIsDepreciated(asset.isDepreciated());
		setPostingType(postingType);
		//
		// Copy UseLife values from asset group to workfile
		if (assetgrpacct == null)
		{
			assetgrpacct = MAssetGroupAcct.forA_Asset_Group_ID(asset.getCtx(), asset.getA_Asset_Group_ID(), postingType);
		}
		UseLifeImpl.copyValues(this, assetgrpacct);
		
		//
		// Set Date Acct from Asset
		Timestamp dateAcct = asset.getDateAcct();
		if (dateAcct != null)
		{
			dateAcct = TimeUtil.addMonths(dateAcct, 1);
			setDateAcct(dateAcct);
		}		
		//
		// Set UseLife values from asset (if any)
		if (asset.getUseLifeMonths() > 0)
		{
			UseLifeImpl.get(this, false).setUseLifeMonths(asset.getUseLifeMonths());
		}
		if (asset.getUseLifeMonths_F() > 0)
		{
			UseLifeImpl.get(this, true).setUseLifeMonths(asset.getUseLifeMonths_F());
		}
		//
		dump();
	}
	
	/** Logger										*/
	private CLogger log = CLogger.getCLogger(getClass());

	public static Collection<MDepreciationWorkfile> forA_Asset_ID(Properties ctx, int asset_id, String trxName)
	{
		return new Query(ctx, Table_Name, MDepreciationWorkfile.COLUMNNAME_A_Asset_ID+"=?", trxName)
					.setParameters(new Object[]{asset_id})
					.list();
	}
	
	/**
	 * 
	 * @param ctx
	 * @param A_Asset_ID
	 * @param postingType
	 * @return workfile
	 * @see #get(Properties, int, String, String)
	 */
	public static MDepreciationWorkfile get (Properties ctx, int A_Asset_ID, String postingType)
	{
		return get(ctx, A_Asset_ID, postingType, null);
	}
	
	/**
	 * Get/load workfile from cache (if trxName is null)
	 * @param ctx
	 * @param A_Asset_ID
	 * @param postingType
	 * @param trxName
	 * @return workfile
	 */
	public static MDepreciationWorkfile get (Properties ctx, int A_Asset_ID, String postingType, String trxName)
	{
		if (A_Asset_ID <= 0 || postingType == null)
		{
			return null;
		}
		
		final MultiKey key = new MultiKey(A_Asset_ID, postingType);
		if (trxName == null)
		{
			MDepreciationWorkfile wk = s_cacheAsset.get(key);
			if (wk != null)
				return wk;
		}
		/* @win temporary change as this code is causing duplicate create MDepreciationWorkfile on asset addition
		final String whereClause = COLUMNNAME_A_Asset_ID+"=?"
									+" AND "+COLUMNNAME_PostingType+"=? AND "+COLUMNNAME_A_QTY_Current+">?";
		MDepreciationWorkfile wk = new Query(ctx, MDepreciationWorkfile.Table_Name, whereClause, trxName)
											.setParameters(new Object[]{A_Asset_ID, postingType, 0})
											.firstOnly();
		*/
		final String whereClause = COLUMNNAME_A_Asset_ID+"=?"
									+" AND "+COLUMNNAME_PostingType+"=? ";
		MDepreciationWorkfile wk = new Query(ctx, MDepreciationWorkfile.Table_Name, whereClause, trxName)
				.setParameters(new Object[]{A_Asset_ID, postingType})
				.firstOnly();
		
		
		if (trxName == null && wk != null)
		{
			s_cacheAsset.put(key, wk);
		}
		return wk;
	}
	/** Static cache: Asset/PostingType -> Workfile */
	private static CCache<MultiKey, MDepreciationWorkfile>
	s_cacheAsset = new CCache<MultiKey, MDepreciationWorkfile>(Table_Name+"_Asset", 10); 
	
	/**	Returns the date of the last action
	 */
	public Timestamp getLastActionDate()
	{
		return TimeUtil.getMonthLastDay(TimeUtil.addMonths(getDateAcct(), -1));
	}
	
	/**	Check if the asset is depreciated at the specified date
	 *	@param date
	 *	@return true if you amortized until the specified date, otherwise false
	 */
	public boolean isDepreciated(Timestamp date)
	{
		Timestamp lastActionDate = getLastActionDate();
		boolean isDepr = !date.after(lastActionDate);		// date <= lastActionDate
		
		log.fine("LastActionDate=" + lastActionDate + ", GivenDate=" + date + " => isDepreciated=" + isDepr);
		return isDepr;
	}
	
	/**
	 * Get Asset Accounting for this workfile
	 * @return asset accounting model
	 */
	public MAssetAcct getA_AssetAcct(Timestamp dateAcct, String trxName)
	{
		return MAssetAcct.forA_Asset_ID(getCtx(), getA_Asset_ID(), getPostingType(), dateAcct, trxName);
	}

	/**	Returns the current cost of FAs. It is calculated as the difference between acquisition value and the value that you (A_Salvage_Value)
	 * @return the current cost of FAs
	 */
	public BigDecimal getActualCost()
	{
		return getActualCost(getA_Asset_Cost());
	}
	
	/**	*/
	public BigDecimal getActualCost(BigDecimal assetCost)
	{
		return assetCost.subtract(getA_Salvage_Value());
	}
	
	/**
	 * 
	 * @param deltaAmt
	 * @param deltaQty
	 * @param reset
	 */
	public void adjustCost(BigDecimal deltaAmt, BigDecimal deltaQty, boolean reset)
	{
		BigDecimal newCost = Env.ZERO;
		BigDecimal newQty = Env.ZERO;
		if (!reset)
		{
			newCost = getA_Asset_Cost();
			newQty = getA_QTY_Current();
		}
		newCost = newCost.add(deltaAmt);
		newQty = newQty.add(deltaQty);
		
		// TODO: crashes if I cancel an Issue:
//		if (newQty.signum() < 0) {
//			throw new ArhRuntimeException(getCtx(), "@A_QTY_Current@ < 0");
//		}

		//
		// There must be verified that the remaining value to be greater than the amount diminished
		// total devaluation because if the entire asset value (A_Asset_Cost) must be brought to 0. 
//		if (deltaAmt.signum() < 0)
//		{
//			BigDecimal remainingAmt_C = getRemainingCost(null, false);
//			if (remainingAmt_C.compareTo(deltaAmt.negate()) < 0)
//			{
//				throw new ArhRuntimeException(getCtx(), "@A_Asset_Remaining@ < @DeltaAmt@")
//					.addInfo("@A_Asset_Cost@=", getA_Asset_Cost())
//					.addInfo("@A_Accumulated_Depr@=", getA_Accumulated_Depr());
//			}
//			BigDecimal remainingAmt_F = getRemainingCost(null, true);
//			if (remainingAmt_F.compareTo(deltaAmt.negate()) < 0)
//			{
//				throw new ArhRuntimeException(getCtx(), "@A_Asset_Remaining_F@ < @DeltaAmt@")
//					.addInfo("@A_Asset_Cost=@", getA_Asset_Cost())
//					.addInfo("@A_Accumulated_Depr@=", getA_Accumulated_Depr_F());
//			}
//		}
		
		setA_Asset_Cost(newCost);
		setA_QTY_Current(newQty);
		
		if(CLogMgt.isLevelFine()) log.fine("adjustCost(" + deltaAmt + ", " + deltaQty + ", reset=" + reset + ") => amt=" + getA_Asset_Cost() + ", qty=" + getA_QTY_Current());
	}
	
	/**
	 * Adjust Accumulated depreciation
	 * @param amt
	 * @param amt_F
	 * @param reset
	 * @return
	 */
	public boolean adjustAccumulatedDepr(BigDecimal amt, BigDecimal amt_F, boolean reset)
	{
		if (amt == null)
		{
			amt = Env.ZERO;
		}
		if (amt_F == null)
		{
			amt_F = Env.ZERO;
		}
		setA_Accumulated_Depr(amt.add(reset ? Env.ZERO : getA_Accumulated_Depr()));
		setA_Accumulated_Depr_F(amt_F.add(reset ? Env.ZERO : getA_Accumulated_Depr_F()));
		return true;
	}

	/**
	 * Adjust use life years
	 */
	public void adjustUseLife(int deltaUseLifeYears, int deltaUseLifeYears_F, boolean reset)
	{
		if(CLogMgt.isLevelFine()) log.fine("Entering: deltaUseLifeYears=" + deltaUseLifeYears + ", deltaUseLifeYears_F=" + deltaUseLifeYears_F);
		//
		UseLifeImpl.get(this, false).adjustUseLifeYears(deltaUseLifeYears, reset);
		UseLifeImpl.get(this, true).adjustUseLifeYears(deltaUseLifeYears_F, reset);
		//
		if(CLogMgt.isLevelFine()) log.fine("Leaving");
	}
	
	/**	*/
	public int getUseLifeMonths(boolean fiscal)
	{
		return fiscal ? getUseLifeMonths_F() : getUseLifeMonths();
	}
	
	/**	*/
	public BigDecimal getA_Accumulated_Depr(boolean fiscal)
	{
		return fiscal ? getA_Accumulated_Depr_F() : getA_Accumulated_Depr();
	}
	
	/**	*/
	public BigDecimal getAccumulatedCost()
	{
		return getA_Accumulated_Depr(isFiscal());
	}
	
	/**	*/
	public BigDecimal getReevaluationCost()
	{
		return Env.ZERO;
	}
	
	/**
	 * Returns the residual (remaining) value
	 */
	public BigDecimal getRemainingCost(BigDecimal accumAmt, boolean fiscal)
	{
		BigDecimal cost = getActualCost();
		if (accumAmt == null) {
			accumAmt = getA_Accumulated_Depr(fiscal);
		}
		return cost.subtract(accumAmt);
	}
	
	/**
	 * Returns the residual (remaining) value
	 */
	public BigDecimal getRemainingCost(BigDecimal accumAmt)
	{
		return getRemainingCost(accumAmt, isFiscal());
	}
	
	/**	*/
	public int getRemainingPeriods(int A_Current_Period, MDepreciation method)
	{
		int useLifePeriods = getUseLifeMonths(isFiscal());
		if (method != null) {
			useLifePeriods += method.getFixMonthOffset();
		}
		int currentPeriod = (A_Current_Period >= 0 ? A_Current_Period : getA_Current_Period());
		return useLifePeriods - currentPeriod;
	}
	/**	*/
	public int getRemainingPeriods(int A_Current_Period)
	{
		return getRemainingPeriods(A_Current_Period, null);
	}
	
	/**	*/
	private boolean m_isFiscal = false;
	/**	*/
	public boolean isFiscal()
	{
		return m_isFiscal;
	}
	/**
	 * Set fiscal flag (temporary - is not modifing the workfile)
	 * @param fiscal
	 */
	public void setFiscal(boolean fiscal)
	{
		m_isFiscal = fiscal;
	}
	
	/**	Increment the current period (A_Current_Period) 1, and a month DateAcct */
	public void incA_Current_Period()
	{
		int old_period = getA_Current_Period();
		Timestamp old_date = getDateAcct();
		int new_period = old_period + 1;
		Timestamp new_date = TimeUtil.addMonths(getDateAcct(), 1);
		setA_Current_Period(new_period);
		setDateAcct(new_date);
		//
		if(CLogMgt.isLevelFine()) log.fine("(A_Current_Period, DateAcct)=(" + old_period + ", " + old_date + ")->(" + new_period + ", " + new_date + ")");
	}
	
	/**
	 * Set A Current Period (and Data Act) processed just after the last expense. 
	 * Do not save.
	 */
	public void setA_Current_Period()
	{
		String whereClause = MDepreciationExp.COLUMNNAME_A_Asset_ID+"=?"
					+" AND "+MDepreciationExp.COLUMNNAME_PostingType+"=?"
					+" AND "+MDepreciationExp.COLUMNNAME_Processed+"=? AND IsActive=?"
		;
		//
		MDepreciationExp depexp = new Query(getCtx(), MDepreciationExp.Table_Name, whereClause, get_TrxName())
									.setParameters(new Object[]{getA_Asset_ID(), getPostingType(), true, true})
									.setOrderBy(MDepreciationExp.COLUMNNAME_A_Period+" DESC"
												+","+MDepreciationExp.COLUMNNAME_DateAcct+" DESC")
									.first();
		if (depexp != null)
		{
			setA_Current_Period(depexp.getA_Period());
			setDateAcct(depexp.getDateAcct());
			incA_Current_Period();
		}
		else
		{
			log.info("There are no records from which to infer its");
		}

	}
	
	/** Build depreciation flag - if true, the depreciation should be built after save */ 
	private boolean m_buildDepreciation = false;
	
	/**
	 * Build depreciation (A_Depreciation_Exp) entries. More exactly, is deleting not Processed entries.
	 * and create new ones again.
	 * WARNING: IS NOT modifying workfile (this)
	 */

	public void buildDepreciation()
	{
		if (!isDepreciated())
		{
			return;
		}
		
		StringBuffer sb = new StringBuffer();
		load(get_TrxName()); // reload workfile
		MAssetAcct assetacct = getA_AssetAcct(null, get_TrxName()); 
		// TODO: teo_sarca: need to evaluate what happens when we change Depreciation method !!!
		MDepreciation depreciation_C = MDepreciation.get(getCtx(), assetacct.getA_Depreciation_ID());
		MDepreciation depreciation_F = MDepreciation.get(getCtx(), assetacct.getA_Depreciation_F_ID());
		//~ int offset_C = depreciation_C.getFixMonthOffset();
		//~ int offset_F = depreciation_F.getFixMonthOffset();
		int offset_C = 0, offset_F = 0;
		
		BigDecimal assetCost = getActualCost();
		BigDecimal accumDep_C = getA_Accumulated_Depr(false);
		BigDecimal accumDep_F = getA_Accumulated_Depr(true);
		int lifePeriods_C = getUseLifeMonths(false) + offset_C;
		int lifePeriods_F = getUseLifeMonths(true) + offset_F;
		int lifePeriods = (lifePeriods_C > lifePeriods_F ? lifePeriods_C : lifePeriods_F);
		BigDecimal exp_C = Env.ZERO;
		BigDecimal exp_F = Env.ZERO;
		
		//logging
		if(CLogMgt.isLevelFine())
		{
			sb.append("currentPeriod=" + getA_Current_Period() + ", AssetServiceDate=" + getAssetDepreciationDate() + "\n");
			sb.append("offset: C|F=" + offset_C + "|" + offset_F + "\n");
			sb.append("life: C|F=" + lifePeriods_C + "|" + lifePeriods_F + " + offset =" + lifePeriods + "\n");
		}
		
		truncDepreciation();
		int A_Current_Period = getA_Current_Period();
		for (int currentPeriod = A_Current_Period, cnt = 1; currentPeriod <= lifePeriods; currentPeriod++, cnt++)
		{
			exp_C = Env.ZERO;
			exp_F = Env.ZERO;
			
			String help = "" + accumDep_C + "|" + accumDep_F + " + ";
			
			if (lifePeriods_C > currentPeriod || !depreciation_C.requireLastPeriodAdjustment())
			{
				setFiscal(false);
				exp_C = depreciation_C.invoke(this, assetacct, currentPeriod, accumDep_C);
				accumDep_C = accumDep_C.add(exp_C);
			}
			else if (lifePeriods_C == currentPeriod)
			{	// last period
				exp_C = assetCost.subtract(accumDep_C);
				accumDep_C = assetCost;
			}
			
			if (lifePeriods_F > currentPeriod || !depreciation_F.requireLastPeriodAdjustment())
			{
				setFiscal(true);
				exp_F = depreciation_F.invoke(this, assetacct, currentPeriod, accumDep_F);
				accumDep_F = accumDep_F.add(exp_F);
			}
			else if (lifePeriods_F == currentPeriod)
			{	// last period (fiscal)
				exp_F = assetCost.subtract(accumDep_F);
				accumDep_F = assetCost;
			}
			
			help += "" + exp_C + "|" + exp_F + " = " + accumDep_C + "|" + accumDep_F;
			
			// added by zuhri
			int months = 0;
			
			months = months + (currentPeriod - A_Current_Period);
			Timestamp dateAcct = TimeUtil.getMonthLastDay(TimeUtil.addMonths(getDateAcct(), months));
			
			MDepreciationExp.createDepreciation (this, currentPeriod, dateAcct,
													exp_C, exp_F,
													accumDep_C, accumDep_F,
													help, get_TrxName());
			if(CLogMgt.isLevelFine())
			{
				String info = "" + cnt + ": period=" + currentPeriod + "/" + lifePeriods_C + "|" + lifePeriods_F
					+ ", exp=" + exp_C + "|" + exp_F + ", accumDep=" + accumDep_C + "|" + accumDep_F
					+ ", DateAcct=" + dateAcct;
				log.fine("=> " + info + Env.NL + Env.NL);
				sb.append(info + Env.NL);
			}
		} // for
		log.fine(sb.toString());
		
		m_buildDepreciation = false;
	}	//	buildDepreciation
	

	
	/**
	 * Truncate not processed depreciation entries.
	 * IS NOT modifying workfile.
	 */
	public void truncDepreciation()
	{
		String trxName = get_TrxName();
		
		int A_Current_Period = getA_Current_Period();
		final String sql = "DELETE FROM "+MDepreciationExp.Table_Name
						+" WHERE "
							+MDepreciationExp.COLUMNNAME_Processed+"=?"
							+" AND "+MDepreciationExp.COLUMNNAME_A_Period+">=?"
							+" AND "+MDepreciationExp.COLUMNNAME_A_Asset_ID+"=?"
							+" AND "+MDepreciationExp.COLUMNNAME_PostingType+"=?"
		;
		Object[] params = new Object[]{false, A_Current_Period, getA_Asset_ID(), getPostingType()};
		int no = DB.executeUpdateEx(sql, params, trxName);
		log.fine("sql=" + sql + "\nDeleted #" + no);
	}	//	truncDepreciation
	
	/**
	 * Update Founding Mode related fields
	 * @param m model
	 * @param changedColumnName column name that has been changed
	 */
	public static void updateFinantare(SetGetModel m, String changedColumnName)
	{
		//Own contribution:
		BigDecimal valCofinantare = SetGetUtil.get_AttrValueAsBigDecimal(m, COLUMNNAME_A_Valoare_Cofinantare);
		// Asset Value:
		BigDecimal assetCost = SetGetUtil.get_AttrValueAsBigDecimal(m, COLUMNNAME_A_Asset_Cost);
		// Third value:
		BigDecimal valTert = SetGetUtil.get_AttrValueAsBigDecimal(m, COLUMNNAME_A_Valoare_Tert);
		
		// Calculate values
		if (valCofinantare.signum() == 0 && valTert.signum() == 0)
		{
			// Values ​​have never been set, so put everything on their own financing
			valCofinantare = assetCost;
			valTert = Env.ZERO;
		}
		else if (COLUMNNAME_A_Asset_Cost.equals(changedColumnName))
		{
			valCofinantare = assetCost.subtract(valTert);
		}
		else if (COLUMNNAME_A_Valoare_Cofinantare.equals(changedColumnName))
		{
			valTert = assetCost.subtract(valCofinantare);
		}
		else if (COLUMNNAME_A_Valoare_Tert.equals(changedColumnName))
		{
			valCofinantare = assetCost.subtract(valTert);
		}
		else
		{
			valTert = assetCost.subtract(valCofinantare);
		}
		
		// Financing Type
		String tipFinantare = A_TIP_FINANTARE_Cofinantare;
		if (valTert.signum() == 0)
		{
			tipFinantare = A_TIP_FINANTARE_Proprie;
		}
		else if (valCofinantare.signum() == 0)
		{
			tipFinantare = A_TIP_FINANTARE_Terti;
		}
		//
		// Set values
		m.set_AttrValue(COLUMNNAME_A_Tip_Finantare, tipFinantare);
		m.set_AttrValue(COLUMNNAME_A_Valoare_Cofinantare, valCofinantare);
		m.set_AttrValue(COLUMNNAME_A_Valoare_Tert, valTert);
		//
		// If the method is invoked for a persistent object when reset mode of financing
		if (A_TIP_FINANTARE_Proprie.equals(tipFinantare) && SetGetUtil.isPersistent(m))
		{
			m.set_AttrValue(COLUMNNAME_A_FundingMode_ID, null);
		}
	}
	
	public boolean set_AttrValue(String ColumnName, Object value) {
		int index = get_ColumnIndex(ColumnName);
		if (index < 0)
			return false;
		return set_ValueNoCheck(ColumnName, value);
	}
	public Object get_AttrValue(String ColumnName) {
		int index = get_ColumnIndex(ColumnName);
		if (index < 0)
			return null;
		return get_Value(index);
	}
	public boolean is_AttrValueChanged(String ColumnName) {
		int index = get_ColumnIndex(ColumnName);
		if (index < 0)
			return false;
		return is_ValueChanged(index);
	}
}	//	MDepreciationWorkfile
