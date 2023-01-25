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
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.core.domains.models.I_GL_Distribution;
import org.adempiere.core.domains.models.I_GL_DistributionLine;
import org.adempiere.core.domains.models.X_GL_Distribution;
import org.compiere.util.CCache;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 *	GL Distribution Model
 *	
 *  @author Jorg Janke
 *  @author red1 FR: [ 2214883 ] Remove SQL code and Replace for Query
 *  @version $Id: MDistribution.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 */
public class MDistribution extends X_GL_Distribution
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -906547096682610205L;

	/**
	 * 	Get Distribution for combination
	 *	@param account account (ValidCombination)
	 *	@param postingType only posting type
	 *	@param docTypeId only document type
	 *	@return array of distributions
	 */
	public static List<MDistribution> get(MAccount account, String postingType,
			int docTypeId,Timestamp accountDate) {
		return get(account.getCtx(), account.getC_AcctSchema_ID(), postingType,
				docTypeId, account.getAD_Org_ID(), account.getAccount_ID(),
				account.getM_Product_ID(), account.getC_BPartner_ID(),
				account.getC_Project_ID(), account.getC_Campaign_ID(),
				account.getC_Activity_ID(), account.getAD_OrgTrx_ID(),
				account.getC_SalesRegion_ID(), account.getC_LocTo_ID(),
				account.getC_LocFrom_ID(), account.getUser1_ID(), account.getUser2_ID() ,
				account.getUser3_ID() , account.getUser4_ID() ,accountDate);
	} // get

	/**
	 * 	Get Distributions for combination
	 *	@param ctx context
	 *	@param acctSchemaId schema
	 *	@param postingType posting type
	 *	@param docTypeId document type
	 *	@param orgId org
	 *	@param accountId account
	 *	@param productId product
	 *	@param partnerId partner
	 *	@param projectId project
	 *	@param campaignId campaign
	 *	@param activityId activity
	 *	@param orgTrxId trx org
	 *	@param salesRegionId
	 *	@param locToId location to
	 *	@param locFromId location from
	 *	@param user1Id user 1
	 *	@param user2Id user 2
	 *  @param user3Id user 3
	 *  @param user4Id user 4
	 *	@return array of distributions or null
	 */
	public static List<MDistribution> get(Properties ctx, int acctSchemaId,
			String postingType, int docTypeId, int orgId,
			int accountId, int productId, int partnerId,
			int projectId, int campaignId, int activityId,
			int orgTrxId, int salesRegionId, int locToId,
			int locFromId, int user1Id, int user2Id, int user3Id, int user4Id,
			Timestamp accountDate) {
		List<MDistribution> distributions = get(ctx, accountId);
		if (distributions == null || distributions.size() == 0)
			return null;

		List<MDistribution> distributionList = new ArrayList<>();
		for (MDistribution distribution : distributions) {

			if(!distribution.isValidFromTo( accountDate))
				continue;
			if (!distribution.isActive() || !distribution.isValid())
				continue;
			//	Mandatory Acct Schema
			if (distribution.getC_AcctSchema_ID() != acctSchemaId)
				continue;
			//	Only Posting Type / DocType
			if (distribution.getPostingType() != null && !distribution.getPostingType().equals(postingType))
				continue;
			if (distribution.getC_DocType_ID() != 0 && distribution.getC_DocType_ID() != docTypeId)
				continue;
			//	Optional Elements - "non-Any"
			if (!distribution.isAnyOrg() && distribution.getOrg_ID() != orgId)
				continue;
			if (!distribution.isAnyAcct() && distribution.getAccount_ID() != accountId)
				continue;
			if (!distribution.isAnyProduct() && distribution.getM_Product_ID() != productId)
				continue;
			if (!distribution.isAnyBPartner() && distribution.getC_BPartner_ID() != partnerId)
				continue;
			if (!distribution.isAnyProject() && distribution.getC_Project_ID() != projectId)
				continue;
			if (!distribution.isAnyCampaign() && distribution.getC_Campaign_ID() != campaignId)
				continue;
			if (!distribution.isAnyActivity() && distribution.getC_Activity_ID() != activityId)
				continue;
			if (!distribution.isAnyOrgTrx() && distribution.getAD_OrgTrx_ID() != orgTrxId)
				continue;
			if (!distribution.isAnySalesRegion() && distribution.getC_SalesRegion_ID() != salesRegionId)
				continue;
			if (!distribution.isAnyLocTo() && distribution.getC_LocTo_ID() != locToId)
				continue;
			if (!distribution.isAnyLocFrom() && distribution.getC_LocFrom_ID() != locFromId)
				continue;
			if (!distribution.isAnyUser1() && distribution.getUser1_ID() != user1Id)
				continue;
			if (!distribution.isAnyUser2() && distribution.getUser2_ID() != user2Id)
				continue;
			if (!distribution.isAnyUser3() && distribution.getUser3_ID() != user3Id)
				continue;
			if (!distribution.isAnyUser4() && distribution.getUser4_ID() != user4Id)
				continue;
			//
			distributionList.add (distribution);
		}	//	 for all distributions with acct
		return distributionList;
	}	//	get
	
	/**
	 * 	Get Distributions for Account
	 *	@param ctx context
	 *	@param accountId Account Id
	 *	@return array of distributions
	 */
	public static List<MDistribution> get (Properties ctx, int accountId)
	{
		Integer key = accountId;
		List<MDistribution> distributions = distributionCache.get(key);
		if (distributions != null)
			return distributions;
		final String whereClause = "Account_ID=?";

		List<MDistribution> list = new Query(ctx,I_GL_Distribution.Table_Name,whereClause,null)
		.setClient_ID()
		.setOnlyActiveRecords(true)
		.setParameters(accountId)
		.list();
		if (list != null && list.size() > 0)
			distributionCache.put(key, list);
		return list;
	}	//	get
	
	/**	Static Logger	*/
	private static CLogger logger = CLogger.getCLogger (MDistribution.class);
	/**	Distributions by Account			*/
	private static CCache<Integer,List<MDistribution>> distributionCache = new CCache<>("GL_Distribution", 100);
	
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param distributionId id
	 *	@param trxName transaction
	 */
	public MDistribution (Properties ctx, int distributionId, String trxName)
	{
		super (ctx, distributionId, trxName);
		if (distributionId == 0)
		{
		//	setC_AcctSchema_ID (0);
		//	setName (null);
			//
			setAnyAcct (true);	// Y
			setAnyActivity (true);	// Y
			setAnyBPartner (true);	// Y
			setAnyCampaign (true);	// Y
			setAnyLocFrom (true);	// Y
			setAnyLocTo (true);	// Y
			setAnyOrg (true);	// Y
			setAnyOrgTrx (true);	// Y
			setAnyProduct (true);	// Y
			setAnyProject (true);	// Y
			setAnySalesRegion (true);	// Y
			setAnyUser1 (true);	// Y
			setAnyUser2 (true);	// Y
			//
			setIsValid (false);	// N
			setPercentTotal (Env.ZERO);
		}
	}	//	MDistribution

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MDistribution (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	} // MDistribution

	/** The Lines */
	private List<MDistributionLine> distributionLines = null;
	Boolean isPercentage = false;
	Boolean isAmount = false;
	/**
	 * 	Get Lines and calculate total
	 *	@param reload reload data
	 *	@return array of lines
	 */
	public List<MDistributionLine> getLines (boolean reload)
	{
		if (distributionLines != null && !reload) {
			distributionLines.forEach(distributionLine -> distributionLine.set_TrxName(get_TrxName()));
			return distributionLines;
		}
		BigDecimal PercentTotal = Env.ZERO;
		//red1 Query
		final String whereClause = I_GL_DistributionLine.COLUMNNAME_GL_Distribution_ID+"=?";
		distributionLines = new Query(getCtx(),I_GL_DistributionLine.Table_Name,whereClause,get_TrxName())
		.setClient_ID()
		.setOnlyActiveRecords(true)
		.setParameters(getGL_Distribution_ID())
		.setOrderBy("Line")
		.list();
		//red1 Query  -end-
		boolean hasNullRemainder = false;

		BigDecimal amountCreditTotal = BigDecimal.ZERO;
		BigDecimal amountDebitTotal = BigDecimal.ZERO;
		for (MDistributionLine distributionLine : distributionLines) {
			if (distributionLine.isActive()) {
				BigDecimal amountCredit = distributionLine.getAmtAcctCr();
				BigDecimal amountDebit = distributionLine.getAmtAcctDr();

				if (amountCredit != null && amountCredit.signum() != 0) {
					amountCreditTotal = amountCreditTotal.add(distributionLine.getAmtAcctCr());
					isAmount = true;
				} else if (amountDebit != null && amountDebit.signum() != 0) {
					amountDebitTotal = amountDebitTotal.add(distributionLine.getAmtAcctDr());
					isAmount = true;
				} else {

					PercentTotal = PercentTotal.add(distributionLine.getPercent());
					hasNullRemainder = Env.ZERO.compareTo(distributionLine.getPercent()) == 0;

					isPercentage = true;
				}
				distributionLine.setParent(this);
			}
		}
		// Update Ratio when saved and difference
		if (hasNullRemainder && isPercentage)
			PercentTotal = Env.ONEHUNDRED;
		if (get_ID() != 0 && (PercentTotal.compareTo(getPercentTotal()) != 0 || isAmount)) {
			setPercentTotal(PercentTotal);
			saveEx();
		}
		return this.distributionLines;
	}	//	getLines
	
	/**
	 * 	Validate Distribution
	 *	@return error message or null
	 */
	public String validate()
	{
		String retValue = null;
		getLines(true);
		if (distributionLines.size() == 0)
			retValue = "@NoLines@";
		else if (isPercentage && getPercentTotal().compareTo(Env.ONEHUNDRED) != 0)
			retValue = "@PercentTotal@ <> 100";
		else if (isAmount && getBalance().signum() !=0 ) {
				retValue = "Debit amount is not equal to credit amount";
		}

		Timestamp startDate = getValidFrom();
		Timestamp endDate = getValidTo();
		if (startDate != null && endDate!= null && startDate.after(endDate)) {
			retValue = "Invalid Time range";		
		}
		
		List<MDistribution> distributions = getSameDistributions(getCtx(), getC_AcctSchema_ID(),
				getPostingType(), getC_DocType_ID(), getAD_Org_ID(),
				getAccount_ID(), getM_Product_ID(), getC_BPartner_ID(),
				getC_Project_ID(), getC_Campaign_ID(), getC_Activity_ID(),
				getAD_OrgTrx_ID(), getC_SalesRegion_ID(), getC_LocTo_ID(),
				getC_LocFrom_ID(), getUser1_ID(), getUser2_ID() , getUser3_ID() , getUser4_ID());
		

	    if (distributions!= null && distributions.size() > 0) {
			for (int i = 0; i < distributions.size(); i++) {
				if (distributions.get(i).getGL_Distribution_ID() != get_ID()) {
					
					Timestamp curStartDate =  distributions.get(i).getValidFrom();
					Timestamp curEndDate = distributions.get(i).getValidTo();
					
					if (startDate != null && endDate != null && curStartDate != null && curEndDate!= null && !startDate.after(curEndDate) && !curStartDate.after(endDate)) {
						retValue = "Current date range Overlapping with GL Distribution :" + distributions.get(i).getName();
						break;
					}
				}
			}
		}

		setIsValid(retValue == null);
		return retValue;
	} // validate

	public BigDecimal getBalance()
	{
		BigDecimal totalDebit =  getLines(true).stream().map(MDistributionLine::getAmtAcctDr).reduce(BigDecimal.ZERO , BigDecimal::add);
		BigDecimal totalCredit =  getLines(true).stream().map(MDistributionLine::getAmtAcctCr).reduce(BigDecimal.ZERO , BigDecimal::add);
		return totalDebit.subtract(totalCredit);
	}
	
	
	public static List<MDistribution> getSameDistributions(Properties ctx, int acctSchemaId,
			String postingType, int docTypeId, int orgId,
			int accountId, int productId, int partnerId,
			int projectId, int campaignId, int activityId,
			int orgTrxId, int salesRegionId, int locToId,
			int locFromId, int user1Id, int user2Id ,  int user3Id, int user4Id ) {

		List<MDistribution> distributions = getDistributions(ctx, acctSchemaId);
		if (distributions == null || distributions.size() == 0)
			return null;
		//
		List<MDistribution> list = new ArrayList<MDistribution>();
		for (MDistribution distribution : distributions) {
			if (!distribution.isActive() || !distribution.isValid())
				continue;
						
			if (distribution.getPostingType() != null
					&& !distribution.getPostingType().equals(postingType))
				continue;
			if (distribution.getC_DocType_ID() != 0
					&& distribution.getC_DocType_ID() != docTypeId)
				continue;
			
			if (!distribution.isAnyOrg()
					&& distribution.getOrg_ID() != orgId)
				continue;
			if (!distribution.isAnyAcct()
					&& distribution.getAccount_ID() != accountId)
				continue;
			if (!distribution.isAnyProduct()
					&& distribution.getM_Product_ID() != productId)
				continue;
			if (!distribution.isAnyBPartner()
					&& distribution.getC_BPartner_ID() != partnerId)
				continue;
			if (!distribution.isAnyProject()
					&& distribution.getC_Project_ID() != projectId)
				continue;
			if (!distribution.isAnyCampaign()
					&& distribution.getC_Campaign_ID() != campaignId)
				continue;
			if (!distribution.isAnyActivity()
					&& distribution.getC_Activity_ID() != activityId)
				continue;
			if (!distribution.isAnyOrgTrx()
					&& distribution.getAD_OrgTrx_ID() != orgTrxId)
				continue;
			if (!distribution.isAnySalesRegion()
					&& distribution.getC_SalesRegion_ID() != salesRegionId)
				continue;
			if (!distribution.isAnyLocTo()
					&& distribution.getC_LocTo_ID() != locToId)
				continue;
			if (!distribution.isAnyLocFrom()
					&& distribution.getC_LocFrom_ID() != locFromId)
				continue;
			if (!distribution.isAnyUser1()
					&& distribution.getUser1_ID() != user1Id)
				continue;
			if (!distribution.isAnyUser2()
					&& distribution.getUser2_ID() != user2Id)
				continue;
			if (!distribution.isAnyUser3()
					&& distribution.getUser3_ID() != user3Id)
				continue;
			if (!distribution.isAnyUser4()
					&& distribution.getUser4_ID() != user4Id)

			if (!distribution.isAnyUser3()
						&& distribution.getUser3_ID() != user3Id)
					continue;
			if (!distribution.isAnyUser4()
					&& distribution.getUser4_ID() != user4Id)

			list.add(distribution);
		} // for all distributions with acct
		return list;
	} // get
	
	/**
	 * Get Distributions for accounting schema
	 * 
	 * @param ctx context
	 * @param acctSchemaId id
	 * @return array of distributions
	 */
	public static List<MDistribution> getDistributions(Properties ctx, int acctSchemaId) {
		Integer key = Integer.valueOf(acctSchemaId);
		List<MDistribution> retValue = distributionCache.get(key);
		if (retValue != null)
			return retValue;
		final String whereClause = "C_AcctSchema_ID=?";

		List<MDistribution> list = new Query(ctx, I_GL_Distribution.Table_Name,
				whereClause, null)
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.setParameters(acctSchemaId)
				.list();
		distributionCache.put(key, list);
		return retValue;
	} // get

	
	
	/**
	 * 	Distribute Amount to Lines
	 * 	@param account account
	 *	@param amount amount
	 * @param quantity
	 *	@param currencyId currency
	 */
	public void distribute (MAccount account, BigDecimal amount, BigDecimal quantity, int currencyId ,int sign)
	{
		log.info("distribute - Amt=" + amount + " - Qty=" + quantity + " - " + account);
		getLines(false);
		int precision = MCurrency.getStdPrecision(getCtx(), currencyId);
		//	First Round
		BigDecimal total = BigDecimal.ZERO;
		BigDecimal totalQty = BigDecimal.ZERO;
		int indexBiggest = -1;
		int indexZeroPercent = -1;
		boolean isPercentage = false;
		for (int i = 0; i < distributionLines.size(); i++)
		{
			MDistributionLine distributionLine = distributionLines.get(i);
			if (!distributionLine.isActive())
				continue;
			
			distributionLine.setAccount(account);
			
			// Calculate Amount
			if (distributionLine.getAmtAcctDr() != null &&  distributionLine.getAmtAcctDr().signum() !=0) {
				if( sign < 0)
					distributionLine.setAmt(distributionLine.getAmtAcctDr());
				else
					distributionLine.setAmt(distributionLine.getAmtAcctDr().negate());
			}
			else if (distributionLine.getAmtAcctCr() !=null && distributionLine.getAmtAcctCr().signum() != 0) {
				if( sign > 0)
					distributionLine.setAmt(distributionLine.getAmtAcctCr());
				else
					distributionLine.setAmt(distributionLine.getAmtAcctCr().negate());
			}
			else {
				if (distributionLine.getPercent().signum() != 0) {
					// Calculate Amount
					distributionLine.calculateAmt(amount, precision);
					// Calculate Quantity
					distributionLine.calculateQty(quantity);
					total = total.add(distributionLine.getAmt());
					totalQty = totalQty.add(distributionLine.getQty());
					// log.fine("distribute - Line=" + dl.getLine() + " - " +
					// dl.getPercent() + "% " + dl.getAmt() + " - Total=" + total);
					// Remainder
					if (distributionLine.getPercent().signum() == 0)
						indexZeroPercent = i;
					if (indexZeroPercent == -1) {
						if (indexBiggest == -1)
							indexBiggest = i;
						else if (distributionLine.getAmt().compareTo(distributionLines.get(indexBiggest).getAmt()) > 0)
							indexBiggest = i;
					}
					isPercentage = true;
				}
			}
		}
		
		if(isPercentage){
			//	Adjust Remainder
			BigDecimal difference = amount.subtract(total);
			if (difference.compareTo(Env.ZERO) != 0)
			{
				if (indexZeroPercent != -1)
				{
				//	log.fine("distribute - Difference=" + difference + " - 0%Line=" + distributionLines[indexZeroPercent]);
					distributionLines.get(indexZeroPercent).setAmt (difference);
				}
				else if (indexBiggest != -1)
				{
				//	log.fine("distribute - Difference=" + difference + " - MaxLine=" + distributionLines[indexBiggest] + " - " + distributionLines[indexBiggest].getAmt());
					distributionLines.get(indexBiggest).setAmt (distributionLines.get(indexBiggest).getAmt().add(difference));
				}
				else
					log.warning("distribute - Remaining Difference=" + difference); 
			}
			//	Adjust Remainder
			BigDecimal differenceQty = quantity.subtract(totalQty);
			if (differenceQty.compareTo(Env.ZERO) != 0)
			{
				if (indexZeroPercent != -1)
				{
				//	log.fine("distribute - Difference=" + difference + " - 0%Line=" + distributionLines[indexZeroPercent]);
					distributionLines.get(indexZeroPercent).setQty (differenceQty);
				}
				else if (indexBiggest != -1)
				{
				//	log.fine("distribute - Difference=" + difference + " - MaxLine=" + distributionLines[indexBiggest] + " - " + distributionLines[indexBiggest].getAmt());
					distributionLines.get(indexBiggest).setQty (distributionLines.get(indexBiggest).getQty().add(differenceQty));
				}
				else
					log.warning("distribute - Remaining Qty Difference=" + differenceQty); 
			}
		}
		else
		{
			distributionLines.stream()
			.filter(distributionLine -> distributionLine.isOverwritePostingType() && !distributionLine.getPostingType().equals(getPostingType()))
			.forEach(distributionLine -> {
				if (!distributionLine.isInvertAccountSign()) {
					distributionLine.setAmt(amount);
					distributionLine.setQty(quantity);
				}
				else {
					distributionLine.setAmt(amount.negate());
					distributionLine.setQty(quantity.negate());
				}
			});
		}

		if (CLogMgt.isLevelFinest())
		{
			for (MDistributionLine distributionLine: distributionLines)
			{
				if (distributionLine.isActive())
					log.fine("distribute = Amt=" +distributionLine.getAmt() + " - " + distributionLine.getAccount());
			}
		}
	}	//	distribute
	
	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		//	Reset not selected Any
		if (isAnyAcct() && getAccount_ID() != 0)
			setAccount_ID(0);
		if (isAnyActivity() && getC_Activity_ID() != 0)
			setC_Activity_ID(0);
		if (isAnyBPartner() && getC_BPartner_ID() != 0)
			setC_BPartner_ID(0);
		if (isAnyCampaign() && getC_Campaign_ID() != 0)
			setC_Campaign_ID(0);
		if (isAnyLocFrom() && getC_LocFrom_ID() != 0)
			setC_LocFrom_ID(0);
		if (isAnyLocTo() && getC_LocTo_ID() != 0)
			setC_LocTo_ID(0);
		if (isAnyOrg() && getOrg_ID() != 0)
			setOrg_ID(0);
		if (isAnyOrgTrx() && getAD_OrgTrx_ID() != 0)
			setAD_OrgTrx_ID(0);
		if (isAnyProduct() && getM_Product_ID() != 0)
			setM_Product_ID(0);
		if (isAnyProject() && getC_Project_ID() != 0)
			setC_Project_ID(0);
		if (isAnySalesRegion() && getC_SalesRegion_ID() != 0)
			setC_SalesRegion_ID(0);
		if (isAnyUser1() && getUser1_ID() != 0)
			setUser1_ID(0);
		if (isAnyUser2() && getUser2_ID() != 0)
			setUser2_ID(0);
		if (isAnyUser3() && getUser3_ID() != 0)
			setUser3_ID(0);
		if (isAnyUser4() && getUser4_ID() != 0)
			setUser4_ID(0);
		return true;
	}	//	beforeSave
	
	
	public int copyLinesFrom (MDistribution fromDistribution)
	{
		List<MDistributionLine> fromLines = fromDistribution.getLines(true);
		int count = 0;
		for (MDistributionLine distributionLine :  fromLines)
		{
			MDistributionLine toLine = new MDistributionLine (getCtx(),0,get_TrxName());
			PO.copyValues(distributionLine, toLine, getAD_Client_ID(), getAD_Org_ID());
			toLine.setGL_Distribution_ID(getGL_Distribution_ID());
			if (toLine.save())
				count++;
			
		}
		if (fromLines.size() != count)
			log.log(Level.SEVERE, "Line difference - From=" + fromLines.size() + " <> Saved=" + count);
		
		return count;
	}	//	copyLinesFrom


	public  boolean isValidFromTo(Timestamp date)
	{
		Timestamp validFrom = getValidFrom();
		Timestamp validTo = getValidTo();

		if (validFrom != null && date.before(validFrom))
			return false;
		if (validTo != null && date.after(validTo))
			return false;
		return true;
	}
		
} // MDistribution

