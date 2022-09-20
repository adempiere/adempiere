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

import io.vavr.collection.List;
import io.vavr.control.Option;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.ResultSetIterable;

import static org.adempiere.core.domains.models.X_C_Period.PERIODTYPE_AdjustmentPeriod;
import static org.adempiere.core.domains.models.X_C_Period.PERIODTYPE_StandardCalendarPeriod;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;

/**
 *	GL Journal Callout
 *	
 *  @author Jorg Janke
 *  @version $Id: CalloutGLJournal.java,v 1.3 2006/07/30 00:51:05 jjanke Exp $
 */
public class CalloutGLJournal extends CalloutEngine
{
	/**
	 *  Journal - Period.
	 *  Check that selected period is in DateAcct Range or Adjusting Period
	 *  Called when C_Period_ID or DateAcct, DateDoc changed
	 *	@param ctx context
	 *	@param windowNo window no
	 *	@param gridTab tab
	 *	@param gridField field
	 *	@param value value
	 *	@return null or error message
	 */
	public String period (Properties ctx, int windowNo, GridTab gridTab, GridField gridField, Object value)
	{
		String colName = gridField.getColumnName();
			if (value == null)
				return "";

		int clientId = Env.getContextAsInt(ctx, windowNo, "AD_Client_ID");
		Timestamp dateAcct = null;
		if (colName.equals("DateAcct"))
			dateAcct = (Timestamp)value;
		else
			dateAcct = (Timestamp)gridTab.getValue("DateAcct");
		int periodId = 0;
		if (colName.equals("C_Period_ID"))
			periodId = (Integer) value;

		//  When DateDoc is changed, update DateAcct
		if (colName.equals("DateDoc")) {
			gridTab.setValue("DateAcct", value);
		} else if (colName.equals("DateAcct")) { //  When DateAcct is changed, set C_Period_ID
			final String sql = "SELECT C_Period_ID "
					+ "FROM C_Period "
					+ "WHERE C_Year_ID IN "
					+ "	(SELECT C_Year_ID FROM C_Year WHERE C_Calendar_ID ="
					+ "  (SELECT C_Calendar_ID FROM AD_ClientInfo WHERE AD_Client_ID=?))"
					+ " AND ? BETWEEN StartDate AND EndDate"
					+ " AND IsActive = ?"
					+ " AND PeriodType IN ( ? , ? )";
			DB.runResultSetFunction.apply(
					null,
					sql,
					List.of(clientId, dateAcct, "Y", PERIODTYPE_StandardCalendarPeriod, PERIODTYPE_AdjustmentPeriod),
					resultSet -> {
						new ResultSetIterable<>(resultSet, row -> {
							int periodIdBasedOnAcctDate = row.getInt("C_Period_ID");
							gridTab.setValue(MPeriod.COLUMNNAME_C_Period_ID, periodIdBasedOnAcctDate);
							return periodIdBasedOnAcctDate;
						}).toList();
					});
		} else { //  When C_Period_ID is changed, check if in DateAcct range and set to end date if not
			Option<MPeriod> maybePeriod = Option.of(MPeriod.get(ctx, periodId));
			Option<Timestamp> maybeDateAcct = Option.of(dateAcct);
			maybePeriod.peek(period -> {
				String periodType = period.getPeriodType();
				Timestamp startDate = period.getStartDate();
				Timestamp endDate = period.getEndDate();
				if (periodType.equals(PERIODTYPE_StandardCalendarPeriod) || periodType.equals(PERIODTYPE_AdjustmentPeriod)) { //  Standard Period
					maybeDateAcct.peek(finalDateAcct -> {
						if (finalDateAcct.before(startDate) || finalDateAcct.after(endDate)) {
							gridTab.setValue("DateAcct", endDate);
						}
					});
				}
			});
		}
		return "";
	}   //  	Journal_Period

	/**
	 * 	Journal/Line - rate.
	 * 	Set CurrencyRate from DateAcct, C_ConversionType_ID, C_Currency_ID
	 *	@param ctx context
	 *	@param windowNo window no
	 *	@param gridTab tab
	 *	@param gridField field
	 *	@param value value
	 *	@return null or error message
	 */
	public String rate (Properties ctx, int windowNo, GridTab gridTab, GridField gridField, Object value)
	{
		if (value == null)
			return "";
		
		//  Source info
		int currencyId = (Integer)gridTab.getValue("C_Currency_ID");
		int conversionTypeId = (Integer)gridTab.getValue("C_ConversionType_ID");
		Timestamp dateAcct = (Timestamp)gridTab.getValue("DateAcct");
		if (dateAcct == null)
			dateAcct = new Timestamp(System.currentTimeMillis());
		//
		int acctSchemaId = Env.getContextAsInt(ctx, windowNo, "C_AcctSchema_ID");
		MAcctSchema acctSchema = MAcctSchema.get (ctx, acctSchemaId);
		int clientId = Env.getContextAsInt(ctx, windowNo, "AD_Client_ID");
		int orgId = Env.getContextAsInt(ctx, windowNo, "AD_Org_ID");

		BigDecimal currencyRate = MConversionRate.getRate(
				currencyId,
				acctSchema.getC_Currency_ID(),
				dateAcct,
				conversionTypeId,
				clientId,
				orgId
		);
		log.fine("rate = " + currencyRate);
		if (currencyRate == null)
			currencyRate = Env.ZERO;
		gridTab.setValue("CurrencyRate", currencyRate);

		return "";
	}	//	rate
	
	/**
	 *  JournalLine - Amt.
	 *  Convert the source amount to accounted amount (AmtAcctDr/Cr)
	 *  Called when source amount (AmtSourceCr/Dr) or rate changes
	 *	@param ctx context
	 *	@param windowNo window no
	 *	@param gridTab tab
	 *	@param gridField field
	 *	@param value value
	 *	@return null or error message
	 */
	public String amt (Properties ctx, int windowNo, GridTab gridTab, GridField gridField, Object value)
	{
		String colName = gridField.getColumnName();
		if (value == null || isCalloutActive())
			return "";

		//  Get Target Currency & Precision from C_AcctSchema.C_Currency_ID
		int acctSchemaId = Env.getContextAsInt(ctx, windowNo, "C_AcctSchema_ID");
		MAcctSchema acctSchema = MAcctSchema.get(ctx, acctSchemaId);
		int precision = acctSchema.getStdPrecision();

		BigDecimal currencyRate = (BigDecimal)gridTab.getValue("CurrencyRate");
		if (currencyRate == null) {
			currencyRate = Env.ONE;
			gridTab.setValue("CurrencyRate", currencyRate);
		}
		//  AmtAcct = AmtSource * CurrencyRate  ==> Precision
		BigDecimal amtSourceDr = (BigDecimal)gridTab.getValue("AmtSourceDr");
		if (amtSourceDr == null)
			amtSourceDr = Env.ZERO;
		BigDecimal amtSourceCr = (BigDecimal)gridTab.getValue("AmtSourceCr");
		if (amtSourceCr == null)
			amtSourceCr = Env.ZERO;

		BigDecimal amtAcctDr = amtSourceDr.multiply(currencyRate);
		amtAcctDr = amtAcctDr.setScale(precision, BigDecimal.ROUND_HALF_UP);
		gridTab.setValue("AmtAcctDr", amtAcctDr);
		BigDecimal amtAcctCr = amtSourceCr.multiply(currencyRate);
		amtAcctCr = amtAcctCr.setScale(precision, BigDecimal.ROUND_HALF_UP);
		gridTab.setValue("AmtAcctCr", amtAcctCr);

		return "";
	}   //  amt
	
	/**
	 * 
	 * @param ctx
	 * @param windowNo
	 * @param gridTab
	 * @param gridField
	 * @param value
	 * @return
	 */
	public String account (Properties ctx, int windowNo, GridTab gridTab, GridField gridField, Object value) {
		String colName = gridField.getColumnName();
		if (value == null || isCalloutActive())
			return "";

		if (colName.equals("Account_ID")
				|| colName.equals("C_SubAcct_ID")
				|| colName.equals("M_Product_ID")
				|| colName.equals("C_BPartner_ID")
				|| colName.equals("AD_OrgTrx_ID")
				|| colName.equals("AD_Org_ID")
				|| colName.equals("C_LocFrom_ID")
				|| colName.equals("C_LocTo_ID")
				|| colName.equals("C_SalesRegion_ID")
				|| colName.equals("C_Project_ID")
				|| colName.equals("C_Campaign_ID")
				|| colName.equals("C_Activity_ID")
				|| colName.equals("User1_ID")
				|| colName.equals("User2_ID")
				|| colName.equals("User3_ID")
				|| colName.equals("User4_ID")
				|| colName.equals("UserElement1_ID")
				|| colName.equals("UserElement2_ID")
			) {
			gridTab.setValue("C_ValidCombination_ID", null);
			gridTab.setValue("Alias_ValidCombination_ID", null);
		}
		return "";
	} // account
	
	public String alias (Properties ctx, int windowNo, GridTab gridTab, GridField gridField, Object value) {
		String colName = gridField.getColumnName();
		if (value == null || isCalloutActive())
			return "";

		Integer validCombinationId = (Integer) value;
		if (colName.equals("Alias_ValidCombination_ID")) 
			gridTab.setValue("C_ValidCombination_ID", validCombinationId);
	
		if (colName.equals("C_ValidCombination_ID"))
			gridTab.setValue("Alias_ValidCombination_ID", validCombinationId);
		
		if (colName.equals("C_ValidCombination_ID") || colName.equals("Alias_ValidCombination_ID"))
		{
			MAccount validCombination = new MAccount(ctx, validCombinationId, null);
			gridTab.setValue("Account_ID", validCombination.getAccount_ID() != 0 ? validCombination.getAccount_ID() : null);
			gridTab.setValue("C_SubAcct_ID", validCombination.getC_SubAcct_ID() != 0 ? validCombination.getC_SubAcct_ID() : null);
			gridTab.setValue("M_Product_ID", validCombination.getM_Product_ID() != 0 ? validCombination.getM_Product_ID() : null);
			gridTab.setValue("C_BPartner_ID", validCombination.getC_BPartner_ID() != 0 ? validCombination.getC_BPartner_ID() : null);
			gridTab.setValue("AD_OrgTrx_ID", validCombination.getAD_OrgTrx_ID() != 0 ? validCombination.getAD_OrgTrx_ID() : null);
			gridTab.setValue("AD_Org_ID", validCombination.getAD_Org_ID() != 0 ? validCombination.getAD_Org_ID() : null);
			gridTab.setValue("C_LocFrom_ID", validCombination.getC_LocFrom_ID() != 0 ? validCombination.getC_LocFrom_ID() : null);
			gridTab.setValue("C_LocTo_ID", validCombination.getC_LocTo_ID() != 0 ? validCombination.getC_LocTo_ID() : null);
			gridTab.setValue("C_SalesRegion_ID", validCombination.getC_SalesRegion_ID() != 0 ? validCombination.getC_SalesRegion_ID() : null);
			gridTab.setValue("C_Project_ID", validCombination.getC_Project_ID() != 0 ? validCombination.getC_Project_ID() : null);
			gridTab.setValue("C_Campaign_ID", validCombination.getC_Campaign_ID() != 0 ? validCombination.getC_Campaign_ID() : null);
			gridTab.setValue("C_Activity_ID", validCombination.getC_Activity_ID() != 0 ? validCombination.getC_Activity_ID() : null);
			gridTab.setValue("User1_ID", validCombination.getUser1_ID() != 0 ? validCombination.getUser1_ID() : null);
			gridTab.setValue("User2_ID", validCombination.getUser2_ID()!= 0 ? validCombination.getUser2_ID() : null);
			gridTab.setValue("User3_ID", validCombination.getUser3_ID() != 0 ? validCombination.getUser3_ID() : null);
			gridTab.setValue("User4_ID", validCombination.getUser4_ID()!= 0 ? validCombination.getUser4_ID() : null);
			gridTab.setValue("UserElement1_ID", validCombination.getUserElement1_ID() != 0 ? validCombination.getUserElement1_ID() : null);
			gridTab.setValue("UserElement2_ID", validCombination.getUserElement2_ID() != 0 ? validCombination.getUserElement2_ID() : null);
		}
		return "";
	} // alias
}	//	CalloutGLJournal
