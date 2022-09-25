package org.compiere.asset.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.adempiere.core.domains.models.X_A_Depreciation_Exp;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;
import org.compiere.asset.exceptions.AssetException;
import org.compiere.asset.exceptions.AssetNotActiveException;
import org.compiere.model.MDocType;
import org.compiere.model.MPeriod;
import org.compiere.model.Query;


public class MDepreciationExp extends X_A_Depreciation_Exp
{
	private static final long serialVersionUID = 1L;
	
	private static CLogger s_log = CLogger.getCLogger(MDepreciationExp.class);
	private CLogger log = CLogger.getCLogger(this.getClass());
	
	/** Standard Constructor */
	public MDepreciationExp(Properties ctx, int depreciationExpId, String trxName)
	{
		super (ctx, depreciationExpId, trxName);
		/** 
		if (A_Depreciation_Exp_ID == 0)
		{
			setA_Account_Number (0);
			setA_Asset_ID (0);
			setA_Depreciation_Exp_ID (0);
			setA_Entry_Type (null);
			setA_Period (0);
			setDescription (null);
			setExpense (Env.ZERO);
			setIsDepreciated (false);
			setProcessed (false);
		}
		*/
	}
	
	/** Load Constructor */
	public MDepreciationExp (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}
	
	/** Gets depreciation expense 
	 *	@param ctx	context
	 *	@param depreciationExpId	depreciation expense id
	 *	@return depreciation expense or null if A_Depreciation_Exp_ID=0 or not found
	 */
	public static MDepreciationExp get(Properties ctx, int depreciationExpId) {
		if (depreciationExpId <= 0) {
			return null;
		}
		MDepreciationExp depexp = new MDepreciationExp(ctx, depreciationExpId, null);
		if (depexp.get_ID() != depreciationExpId) {
			depexp = null;
		}
		return depexp;
	}

	/**
	 *
	 * @param ctx
	 * @param entryType
	 * @param assetId
	 * @param assetPeriod
	 * @param dateAcct
	 * @param postingType
	 * @param debitAcct
	 * @param creditAcct
	 * @param expense
	 * @param description
	 * @param assetBalanace
	 * @return
	 */
	public static MDepreciationExp createEntry (Properties ctx, String entryType, int assetId
				, int assetPeriod, Timestamp dateAcct, String postingType
				, int debitAcct, int creditAcct, BigDecimal expense
				, String description
				, MDepreciationWorkfile assetBalanace)
	{
		MDepreciationExp depexp = new MDepreciationExp(ctx, 0, null);
		depexp.setA_Entry_Type(entryType);
		depexp.setA_Asset_ID(assetId);
		depexp.setDR_Account_ID(debitAcct);
		depexp.setCR_Account_ID(creditAcct);
		depexp.setA_Account_Number_Acct(debitAcct);	// TODO: DELETEME
		depexp.setPostingType(postingType);
		depexp.setExpense(expense);
		depexp.setDescription(Msg.parseTranslation(ctx, description));
		depexp.setA_Period(assetPeriod);
		depexp.setIsDepreciated(true);
		depexp.setDateAcct(dateAcct);
		//
		depexp.updateFrom(assetBalanace);
		//
		s_log.fine("depexp=" + depexp);
		return depexp;
	}
	
	/**
	 * Update fields from asset workfile
	 * @param assetBalance asset workfile
	 */
	public void updateFrom(MDepreciationWorkfile assetBalance)
	{
		setA_Asset_Cost(assetBalance.getA_Asset_Cost());
		setA_Accumulated_Depr(assetBalance.getA_Accumulated_Depr());
		setA_Accumulated_Depr_F(assetBalance.getA_Accumulated_Depr_F());
		setUseLifeMonths(assetBalance.getUseLifeMonths());
		setUseLifeMonths_F(assetBalance.getUseLifeMonths_F());
		setA_Asset_Remaining(assetBalance.getA_Asset_Remaining());
		setA_Asset_Remaining_F(assetBalance.getA_Asset_Remaining_F());
	}
	
	private MDepreciationWorkfile getA_Depreciation_Workfile()
	{
		return MDepreciationWorkfile.get(getCtx(), getA_Asset_ID(), getPostingType(), get_TrxName());
	}

	/**	Create Depreciation Entries
	 *	Produce record:
	 *	<pre>
	 *		68.. = 28..   depreciation value
	 *	</pre>
	 */
	public static Collection<MDepreciationExp> createDepreciation ( 
				MDepreciationWorkfile assetBalance,
				int periodNo, Timestamp dateAcct,
				BigDecimal amt, BigDecimal amtFiscal,
				BigDecimal accumAmt, BigDecimal accumAmtFiscal,
				String help, String trxName)
	{
		ArrayList<MDepreciationExp> list = new ArrayList<MDepreciationExp>();
		Properties ctx = assetBalance.getCtx();
		MAssetAcct assetAcct = assetBalance.getA_AssetAcct(dateAcct, trxName);
		MDepreciationExp depexp = null;
		
		depexp = createEntry (ctx, A_ENTRY_TYPE_Depreciation, assetBalance.getA_Asset_ID(), periodNo, dateAcct, assetBalance.getPostingType()
			, assetAcct.getA_Depreciation_Acct(), assetAcct.getA_Accumdepreciation_Acct()
			, amt
			, "@AssetDepreciationAmt@"
			, assetBalance);
		if(depexp != null) {
			MAsset asset = MAsset.get(ctx, assetBalance.getA_Asset_ID(), trxName);
			depexp.setAD_Org_ID(asset.getAD_Org_ID()); // added by zuhri
			if (accumAmt != null)
				depexp.setA_Accumulated_Depr(accumAmt);
			if (accumAmtFiscal != null)
				depexp.setA_Accumulated_Depr_F(accumAmtFiscal);
			if (help != null && help.length() > 0)
				depexp.setHelp(help);
			depexp.setExpense_F(amtFiscal);
			depexp.setA_Accumulated_Depr_Delta(amt);
			depexp.setA_Accumulated_Depr_F_Delta(amtFiscal);
			depexp.saveEx(assetBalance.get_TrxName());
			list.add(depexp);
		}
		return list;
	}

	/**
	 * Process this entry and save the modified workfile.
	 */
	public void process()
	{
		if(isProcessed())
		{
			log.fine("@AlreadyProcessed@");
			return;
		}
		
		//
		MDepreciationWorkfile assetBalance = getA_Depreciation_Workfile();
		if (assetBalance == null)
		{
			throw new AssetException("@NotFound@ @A_Depreciation_Workfile_ID@");
		}
		//
		String entryType = getA_Entry_Type();
		if (MDepreciationExp.A_ENTRY_TYPE_Depreciation.equals(entryType))
		{
			checkExistsNotProcessedEntries(getCtx(), getA_Asset_ID(), getDateAcct(), getPostingType(), get_TrxName());
			//
			// Check if the asset is Active:
			if (!assetBalance.getAsset().getA_Asset_Status().equals(MAsset.A_ASSET_STATUS_Activated))
			{
				throw new AssetNotActiveException(assetBalance.getAsset().get_ID());
			}
			//
			setDateAcct(assetBalance.getDateAcct());
			assetBalance.adjustAccumulatedDepr(getExpense(), getExpense_F(), false);
		}
		else
		{
			// nothing to do for other entry types
		}
		//
		setProcessed(true);
		updateFrom(assetBalance);
		saveEx();

		//
		// Update workfile
		assetBalance.setA_Current_Period();
		assetBalance.saveEx();
	}
	
	
	protected boolean beforeDelete()
	{
		if (isProcessed())
		{
			// TODO : check if we can reverse it (check period, check dateacct etc)
			MDepreciationWorkfile assetwk = getA_Depreciation_Workfile();
			assetwk.adjustAccumulatedDepr(getA_Accumulated_Depr().negate(), getA_Accumulated_Depr_F().negate(), false);
			assetwk.saveEx();
		}
		// Try to delete postings
		if (isPosted())
		{
			MPeriod.testPeriodOpen(getCtx(), getDateAcct(), MDocType.DOCBASETYPE_GLDocument, getAD_Org_ID());
			MDepreciationEntry.deleteFacts(this);
		}
		return true;
	}
	
	
	protected boolean afterDelete(boolean success)
	{
		if (!success)
		{
			return false;
		}
		//
		// If it was processed, we need to update workfile's current period
		if (isProcessed())
		{
			MDepreciationWorkfile wk = getA_Depreciation_Workfile();
			wk.setA_Current_Period();
			wk.saveEx();
		}
		//
		return true;
	}
	
	protected boolean isPosted()
	{
		return isProcessed() && getA_Depreciation_Entry_ID() > 0;
	}

	/**
	 *
	 * @param ctx
	 * @param assetId
	 * @param dateAcct
	 * @param postingType
	 * @param trxName
	 */
	public static void checkExistsNotProcessedEntries(
			Properties ctx,
			int assetId,
			Timestamp dateAcct,
			String postingType,
			String trxName)
	{
		final String whereClause = MDepreciationExp.COLUMNNAME_A_Asset_ID+"=?"
								+" AND TRUNC("+MDepreciationExp.COLUMNNAME_DateAcct+",'MONTH')<?"
								+" AND "+MDepreciationExp.COLUMNNAME_PostingType+"=?"
								+" AND "+MDepreciationExp.COLUMNNAME_Processed+"=?";
		boolean match = new Query(ctx, MDepreciationExp.Table_Name, whereClause, trxName)
					.setParameters(assetId, TimeUtil.getMonthFirstDay(dateAcct), postingType, false)
					.match();
		if (match)
			throw new AssetException("There are unprocessed records to date");
	}

	/**
	 *
	 * @param ctx
	 * @param assetId
	 * @param postingType
	 * @param trxName
	 * @return
	 */
	public static List<MDepreciationExp> getNotProcessedEntries(
			Properties ctx,
			int assetId,
			String postingType,
			String trxName)
	{
		final String whereClause = MDepreciationExp.COLUMNNAME_A_Asset_ID+"=?"
		              +" AND "+MDepreciationExp.COLUMNNAME_PostingType+"=?"
	             	  +" AND "+MDepreciationExp.COLUMNNAME_Processed+"=?";
		List<MDepreciationExp> list = new Query(ctx, MDepreciationExp.Table_Name, whereClause, trxName)
	                      	.setParameters(new Object[]{assetId, postingType, false})
		                    .list();
		return list;
	}

	/**
	 * Get Depreciation
	 * @param ctx
	 * @param assetId
	 * @param postingType
	 * @param trxName
	 * @return
	 */
	public static List<MDepreciationExp> getEntries(Properties ctx,
													int assetId, String postingType,
													String trxName)
	{
		final String whereClause = MDepreciationExp.COLUMNNAME_A_Asset_ID+"=?"
				+" AND "+MDepreciationExp.COLUMNNAME_PostingType+"=?";
		return new Query(ctx, MDepreciationExp.Table_Name, whereClause, trxName)
				.setParameters(assetId, postingType)
				.setOrderBy(MDepreciationExp.COLUMNNAME_A_Period)
				.list();
	}

	
	public void setProcessed(boolean Processed)
	{
		super.setProcessed(Processed);
		//
		if (get_ID() > 0)
		{
			final String sql = "UPDATE "+Table_Name+" SET Processed=? WHERE "+COLUMNNAME_A_Depreciation_Exp_ID+"=?";
			DB.executeUpdateEx(sql, new Object[]{Processed, get_ID()}, get_TrxName());
		}
	}
	
	
	public String toString()
	{
		return getClass().getSimpleName()+"["+get_ID()
			+",A_Asset_ID="+getA_Asset_ID()
			+",A_Period="+getA_Period()
			+",DateAcct="+getDateAcct()
			+",Expense="+getExpense()
			+",Entry_ID="+getA_Depreciation_Entry_ID()
			+"]";
	}
}
