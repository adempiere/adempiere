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
package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.core.domains.models.I_C_Dunning;
import org.adempiere.exceptions.BPartnerNoAddressException;
import org.compiere.model.MBPartner;
import org.compiere.model.MDunning;
import org.compiere.model.MDunningLevel;
import org.compiere.model.MDunningRun;
import org.compiere.model.MDunningRunEntry;
import org.compiere.model.MDunningRunLine;
import org.compiere.model.MInvoice;
import org.compiere.model.MPayment;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;


/**
 *	Create Dunning Run Entries/Lines
 *	
 *  @author Jorg Janke
 *  @version $Id: DunningRunCreate.java,v 1.2 2006/07/30 00:51:02 jjanke Exp $
 *  
 *  FR 2872010 - Dunning Run for a complete Dunning (not just level) - Developer: Carlos Ruiz - globalqss - Sponsor: Metas
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/1494">
 * 		@see FR [ 1494 ] Translation is not considerated for Dunning Run</a>
 */
public class DunningRunCreate extends DunningRunCreateAbstract {	
	
	private int entries = 0;
	/**
	 * 	Process
	 *	@return message
	 *	@throws Exception
	 */
	protected String doIt () throws Exception {
		log.info("C_DunningRun_ID=" + getRecord_ID()
			+ ", Dispute=" + isIncludeInDispute()
			+ ", C_BP_Group_ID=" + getBPGroupId()
			+ ", C_BPartner_ID=" + getBPartnerId());
		if(getRecord_ID() != 0) {
			MDunningRun dunningRun = new MDunningRun(getCtx(), getRecord_ID(), get_TrxName());
			processDunningRun(new MDunning(getCtx(), getDunningId(), get_TrxName()), dunningRun);
		} else {
			if(getDunningId() != 0) {
				MDunningRun dunningRun = new MDunningRun(getCtx(), 0, get_TrxName());
				processDunningRun(new MDunning(getCtx(), getDunningId(), get_TrxName()), dunningRun);
			} else {
				new Query(
						getCtx(),
						I_C_Dunning.Table_Name,
						null,
						get_TrxName())
				.setOnlyActiveRecords(true)
				.setClient_ID()
				.<MDunning>list().stream().forEach(dunning -> {
					processDunningRun(dunning, new MDunningRun(getCtx(), 0, get_TrxName()));
				});
			}
		}
		//	
		return "@C_DunningRunEntry_ID@ #" + entries;
	}	//	doIt
	
	/**
	 * Process dunning run
	 * @param dunning
	 * @param dunningRun
	 */
	private void processDunningRun(MDunning dunning, MDunningRun dunningRun) {
		if (dunningRun.getC_DunningRun_ID() == 0) {
			//	Set Date
			if(getDunningDate() == null) {
				dunningRun.setDunningDate(new Timestamp(System.currentTimeMillis()));
			}
		}
		//	set dunning
		if(dunning.getC_Dunning_ID() != 0) {
			dunningRun.setC_Dunning_ID(dunning.getC_Dunning_ID());
		}
		//	set dunning level
		if(getDunningLevelId() != 0) {
			dunningRun.setC_DunningLevel_ID(getDunningLevelId());
			if(dunning.getC_Dunning_ID() == 0) {
				MDunningLevel level = new MDunningLevel(getCtx(), getDunningLevelId(), get_TrxName());
				dunningRun.setC_Dunning_ID(level.getC_Dunning_ID());
			}
		}
		//	set dunning date
		if(getDunningDate() != null) {
			dunningRun.setDunningDate(getDunningDate());
		}
		//	Set org
		if(getOrgId() != 0) {
			dunningRun.setAD_Org_ID(getOrgId());
		}
		//	Save if exists changes
		dunningRun.saveEx();
		dunningRun.deleteEntries(true);
		//
		for (MDunningLevel level : dunningRun.getLevels()) {
			addInvoices(dunningRun, level);
			if(level.isIncludePayments()) {
				addPayments(dunningRun, level);
			}
			//	
			if (level.isChargeFee ()) {
				addFees(dunningRun, level);
			}

			// we need to check whether this is a statement or not and some other rules
			checkDunningEntry(dunningRun, level);
		}

		int localEntries = DB.getSQLValue(get_TrxName(), "SELECT COUNT(*) FROM C_DunningRunEntry WHERE C_DunningRun_ID=?", dunningRun.get_ID());
		
		entries += localEntries;
		addLog("@C_DunningRunEntry_ID@ #" + localEntries);
	}

	
	/**************************************************************************
	 * 	Add Invoices to Run
	 *  @param level  the Dunning level
	 *  @return no of invoices
	 */
	private int addInvoices(MDunningRun run, MDunningLevel level) {
		int count = 0;
		String sql = "SELECT i.C_Invoice_ID, i.C_Currency_ID,"
			+ " i.GrandTotal*i.MultiplierAP,"
			+ " invoiceOpen(i.C_Invoice_ID,i.C_InvoicePaySchedule_ID)*MultiplierAP,"
			+ " COALESCE(daysBetween(?,ips.DueDate),paymentTermDueDays(i.C_PaymentTerm_ID,i.DateInvoiced,?))," // ##1/2
			+ " i.IsInDispute, i.C_BPartner_ID, i.C_InvoicePaySchedule_ID, i.C_Order_ID "
			+ "FROM C_Invoice_v i "
			+ " LEFT OUTER JOIN C_InvoicePaySchedule ips ON (i.C_InvoicePaySchedule_ID=ips.C_InvoicePaySchedule_ID) "
			+ "WHERE i.IsPaid='N' AND i.AD_Client_ID=?"				//	##3
			+ " AND i.DocStatus IN ('CO','CL')"
			+ " AND (i.DunningGrace IS NULL OR i.DunningGrace<?) "
		//	Only BP(Group) with Dunning defined
			+ " AND EXISTS (SELECT 1 FROM C_DunningLevel dl "
				+ "WHERE dl.C_DunningLevel_ID=?"	//	//	##4
				+ " AND dl.C_Dunning_ID IN "
					+ "(SELECT COALESCE(bp.C_Dunning_ID, bpg.C_Dunning_ID) "
					+ "FROM C_BPartner bp"
					+ " INNER JOIN C_BP_Group bpg ON (bp.C_BP_Group_ID=bpg.C_BP_Group_ID) "
					+ "WHERE i.C_BPartner_ID=bp.C_BPartner_ID" +
							" AND (bp.DunningGrace IS NULL OR bp.DunningGrace<?)))";
		if (getBPartnerId() != 0)
			sql += " AND i.C_BPartner_ID=?";	//	##5
		else if (getBPGroupId() != 0)
			sql += " AND EXISTS (SELECT * FROM C_BPartner bp "
				+ "WHERE i.C_BPartner_ID=bp.C_BPartner_ID AND bp.C_BP_Group_ID=?)";	//	##5
		if (isOnlySOTrx())
			sql += " AND i.IsSOTrx='Y'";
		if (!isAllCurrencies()) 
			sql += " AND i.C_Currency_ID=" + getCurrencyId();
		if ( getOrgId() != 0 )
			sql += " AND i.AD_Org_ID=" + getOrgId();
		
		// if sequentially we must check for other levels with smaller days for
		// which this invoice is not yet included!
		if (level.getParent().isCreateLevelsSequentially()) {
			// Build a list of all topmost Dunning Levels
			String sqlAppend = "";
			for (MDunningLevel element : level.getPreviousLevels()) {
				sqlAppend += " AND EXISTS(SELECT 1 FROM C_DunningRunLine dl "
						+ "				WHERE dl.C_Invoice_ID = i.C_Invoice_ID "
						+ "				AND EXISTS(SELECT 1 FROM C_DunningRunEntry de "
						+ "				WHERE de.C_DunningRunEntry_ID = dl.C_DunningRunEntry_ID "
						+ "				AND EXISTS(SELECT 1 FROM C_DunningRunEntry dee "
						+ "							WHERE dee.C_DunningRun_ID = de.C_DunningRun_ID "
						+ "							AND dee.C_DunningLevel_ID=" + element.getC_DunningLevel_ID() + ")) "
						+ "	AND dl.Processed <> 'N')";
			}
			sql += sqlAppend;
		}
		
		BigDecimal daysAfterDue = level.getDaysAfterDue();
		int daysBetweenDunning = level.getDaysBetweenDunning();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setTimestamp(1, run.getDunningDate());
			pstmt.setTimestamp(2, run.getDunningDate());
			pstmt.setInt (3, run.getAD_Client_ID());
			pstmt.setTimestamp(4, run.getDunningDate());
			pstmt.setInt(5, level.getC_DunningLevel_ID());
			pstmt.setTimestamp(6, run.getDunningDate());
			if (getBPartnerId() != 0)
				pstmt.setInt (7, getBPartnerId());
			else if (getBPGroupId() != 0)
				pstmt.setInt (7, getBPGroupId());
			//
			rs = pstmt.executeQuery ();
			while (rs.next()) {
				int invoiceId = rs.getInt(1);
				int currencyId = rs.getInt(2);
				BigDecimal grandTotal = rs.getBigDecimal(3);
				BigDecimal open = rs.getBigDecimal(4);
				int daysDue = rs.getInt(5);
				boolean isInDispute = "Y".equals(rs.getString(6));
				int bPartnerId = rs.getInt(7);
				int invoicePayScheduleId = rs.getInt(8);
				int orderId = rs.getInt(9);
				log.fine("DaysAfterDue: " + daysAfterDue.intValue() + " isShowAllDue: " + level.isShowAllDue());
				log.fine("C_Invoice_ID - DaysDue - GrandTotal: " + invoiceId + " - " + daysDue + " - " + grandTotal);
				log.fine("C_InvoicePaySchedule_ID: " + invoicePayScheduleId);
				//
				// Check for Dispute
				if (!isIncludeInDispute() && isInDispute)
					continue;
				// Check the day again based on rulesets
				if (daysDue > 0 && daysDue < daysAfterDue.intValue() && !level.isShowAllDue ())
					continue;
				// Check for an open amount
				if (Env.ZERO.compareTo(open) == 0)
					continue;
				//
				int timesDunned = 0;
				int daysAfterLast = 0;
				if(level.isRange()) {
					//	if it don't have days from to
					if(level.getDaysFrom() == 0 && level.getDaysTo() == 0 && !level.isShowAllDue())
						continue;
					//	Days due are < that Days due from
					if(daysDue < level.getDaysFrom() && !level.isShowAllDue())
						continue;
					//	Days due are > that days to
					if(daysDue > level.getDaysTo() && level.getDaysTo() != 0 && !level.isShowAllDue())
						continue;
				} else {
					int []values = getDunningTime(run.getC_DunningRun_ID(), invoiceId);
					timesDunned = values[0];
					daysAfterLast = values[1];
					// Ensure that Daysbetween Dunning is enforced
					// Ensure Not ShowAllDue and Not ShowNotDue is selected
					// PROBLEM: If you have ShowAll activated then DaysBetweenDunning is not working, because we don't know whether
					//          there is something which we really must Dunn.
					if (daysBetweenDunning != 0 && timesDunned > 0 && daysAfterLast < daysBetweenDunning && !level.isShowAllDue () && !level.isShowNotDue ())
						continue;
					
					// We don't want to show non due documents
					if (daysDue<0 && !level.isShowNotDue ())
						continue;
								
					// We will minus the timesDunned if this is the DaysBetweenDunning is not fullfilled.
					// Remember in checkup later we must reset them!
					// See also checkDunningEntry()
					if (daysAfterLast < daysBetweenDunning)
						timesDunned = timesDunned*-1;
				}
				//
				if (createInvoiceLine(run, invoiceId, invoicePayScheduleId, currencyId, grandTotal, open,
						daysDue, isInDispute, bPartnerId, 
						timesDunned, daysAfterLast, level.getC_DunningLevel_ID(), orderId)) {
					count++;
				}
			}
 
		} catch (Exception e) {
			log.log(Level.SEVERE, "addInvoices", e);
			getProcessInfo().addLog(getProcessInfo().getAD_PInstance_ID(), null, null, e.getLocalizedMessage());
		} finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		return count;
	}	//	addInvoices
	
	/**
	 * Get from dunning time
	 * @param dunningRunId
	 * @param invoiceId
	 * @return
	 * @throws SQLException 
	 */
	private int[] getDunningTime(int dunningRunId, int invoiceId) throws SQLException {
		int values[] = new int[]{0, 0};
		// ensure that we do only dunn what's not yet dunned, so we lookup the max of last Dunn Date which was processed
		String sql = "SELECT COUNT(*), COALESCE(DAYSBETWEEN(MAX(dr2.DunningDate), MAX(dr.DunningDate)),0)"		
			+ "FROM C_DunningRun dr2, C_DunningRun dr"
			+ " INNER JOIN C_DunningRunEntry dre ON (dr.C_DunningRun_ID=dre.C_DunningRun_ID)"
			+ " INNER JOIN C_DunningRunLine drl ON (dre.C_DunningRunEntry_ID=drl.C_DunningRunEntry_ID) "
			+ "WHERE dr2.C_DunningRun_ID=? AND drl.C_Invoice_ID=?"; // ##1 ##2
		PreparedStatement pstmt = DB.prepareStatement (sql, get_TrxName());
		//	SubQuery
		pstmt.setInt (1, dunningRunId);
		pstmt.setInt (2, invoiceId);
		ResultSet rs = pstmt.executeQuery ();
		if (rs.next()) {
			values[0] = rs.getInt(1);
			values[1] = rs.getInt(2);
		}
		DB.close(rs, pstmt);
		//	
		return values;
	}
	
	/**
	 * 	Create Invoice Line
	 *	@param invoiceId
	 *	@param currencyId
	 *	@param grandTotal
	 *	@param open
	 *	@param daysDue
	 *	@param isInDispute
	 *	@param bPartnerId
	 *	@param timesDunned
	 *	@param daysAfterLast
	 *  @param dunningLevelId 
	 *  @param invoicePayScheduleId
	 *  @param orderId order reference
	 */
	private boolean createInvoiceLine (MDunningRun run, int invoiceId, int invoicePayScheduleId, int currencyId, 
		BigDecimal grandTotal, BigDecimal open, 
		int daysDue, boolean isInDispute, 
		int bPartnerId, int timesDunned, int daysAfterLast, int dunningLevelId, int orderId) {
		MDunningRunEntry entry = null;
		try  {
			entry = run.getEntry(bPartnerId, getCurrencyId(), getSalesRepId(), dunningLevelId);
		}  catch (BPartnerNoAddressException e) {
			String msg = "@Skip@ @C_Invoice_ID@ " + MInvoice.get(getCtx(), invoiceId).getDocumentInfo()
				+ ", @C_BPartner_ID@ " + MBPartner.get(getCtx(), bPartnerId).getName()
				+ " @No@ @IsActive@ @C_BPartner_Location_ID@";
			getProcessInfo().addLog(getProcessInfo().getAD_PInstance_ID(), null, null, msg);
			return false;
		}
		
		if (entry.get_ID() == 0) {
			entry.saveEx();
		}
		//
		MDunningRunLine line = new MDunningRunLine (entry);
		line.setInvoice(invoiceId, currencyId, grandTotal, open, 
			Env.ZERO, daysDue, isInDispute, timesDunned, 
			daysAfterLast, orderId);
		line.setC_InvoicePaySchedule_ID(invoicePayScheduleId);
		if(orderId != 0) {
			line.setC_Order_ID(orderId);
		}
		line.saveEx();
		return true;
	}	//	createInvoiceLine

	
	/**************************************************************************
	 * 	Add Payments to Run
	 *  @param level  the Dunning level
	 *	@return no of payments
	 */
	private int addPayments(MDunningRun run, MDunningLevel level) {
		String sql = "SELECT p.C_Payment_ID, p.C_Currency_ID, p.PayAmt,"
			+ " paymentAvailable(p.C_Payment_ID), p.C_BPartner_ID, p.C_Order_ID "
			+ "FROM C_Payment_v p "
			+ "WHERE AD_Client_ID=?"			//	##1
			+ " AND IsAllocated='N' AND C_BPartner_ID IS NOT NULL"
			+ " AND C_Charge_ID IS NULL"
			+ " AND DocStatus IN ('CO','CL')"
			//	Only BP(Group) with Dunning defined
			+ " AND EXISTS (SELECT 1 FROM C_DunningLevel dl "
				+ "WHERE dl.C_DunningLevel_ID=?"	//	//	##2
				+ " AND dl.C_Dunning_ID IN "
					+ "(SELECT COALESCE(bp.C_Dunning_ID, bpg.C_Dunning_ID) "
					+ "FROM C_BPartner bp"
					+ " INNER JOIN C_BP_Group bpg ON (bp.C_BP_Group_ID=bpg.C_BP_Group_ID) "
					+ "WHERE p.C_BPartner_ID=bp.C_BPartner_ID))";
		if (getBPartnerId() != 0)
			sql += " AND C_BPartner_ID=?";		//	##3
		else if (getBPGroupId() != 0)
			sql += " AND EXISTS (SELECT * FROM C_BPartner bp "
				+ "WHERE p.C_BPartner_ID=bp.C_BPartner_ID AND bp.C_BP_Group_ID=?)";	//	##3
		// If it is not a statement we will add lines only if InvoiceLines exists,
		// because we do not want to dunn for money we owe the customer!
		if (!level.isStatement())
			sql += " AND C_BPartner_ID IN (SELECT C_BPartner_ID FROM C_DunningRunEntry WHERE C_DunningRun_ID=" + run.get_ID() + ")";
		// show only receipts / if only Sales
		if (isOnlySOTrx())
			sql += " AND IsReceipt='Y'";
		if (getOrgId() != 0 )
			sql += " AND p.AD_Org_ID=" + getOrgId();
		
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setInt (1, getAD_Client_ID());
			pstmt.setInt (2, level.getC_DunningLevel_ID());
			if (getBPartnerId() != 0)
				pstmt.setInt (3, getBPartnerId());
			else if (getBPGroupId() != 0)
				pstmt.setInt (3, getBPGroupId());

			rs = pstmt.executeQuery ();
			while (rs.next ()) {
				int paymentId = rs.getInt(1);
				int currencyId = rs.getInt(2);
				BigDecimal payAmt = rs.getBigDecimal(3).negate();
				BigDecimal openAmt = rs.getBigDecimal(4).negate();
				int bPartnerId = rs.getInt(5);
				int orderId = rs.getInt(6);
				//
				if (Env.ZERO.compareTo(openAmt) == 0)
					continue;
				//
				if (createPaymentLine(run, paymentId, currencyId, payAmt, openAmt,
						bPartnerId, level.getC_DunningLevel_ID(), orderId)) {
					count++;
				}
			}
 		} catch (Exception e) {
			log.log(Level.SEVERE, sql, e);
			getProcessInfo().addLog(getProcessInfo().getAD_PInstance_ID(), null, null, e.getLocalizedMessage());
		} finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		return count;
	}	//	addPayments

	/**
	 * 	Create Payment Line
	 *	@param paymentId
	 *	@param currencyId
	 *	@param payAmt
	 *	@param openAmt
	 *	@param bPartnerId
	 *  @param dunningLevelId 
	 *  @param orderId
	 */
	private boolean createPaymentLine (MDunningRun run, int paymentId, int currencyId, 
		BigDecimal payAmt, BigDecimal openAmt, int bPartnerId, int dunningLevelId, int orderId) {
		MDunningRunEntry entry = null;
		try {
			entry = run.getEntry(bPartnerId, getCurrencyId(), getSalesRepId(), dunningLevelId);
		} catch (BPartnerNoAddressException e) {
			MPayment payment = new MPayment(getCtx(), paymentId, null);
			String msg = "@Skip@ @C_Payment_ID@ " + payment.getDocumentInfo()
				+ ", @C_BPartner_ID@ " + MBPartner.get(getCtx(), bPartnerId).getName()
				+ " @No@ @IsActive@ @C_BPartner_Location_ID@";
			getProcessInfo().addLog(getProcessInfo().getAD_PInstance_ID(), null, null, msg);
			return false;
		}
		if (entry.get_ID() == 0) {
			entry.saveEx();
		}
		//
		MDunningRunLine line = new MDunningRunLine(entry);
		line.setPayment(paymentId, currencyId, payAmt, openAmt, orderId);
		line.saveEx();
		return true;
	}	//	createPaymentLine

	/**
	 * 	Add Fees for every line
	 */
	private void addFees(MDunningRun run, MDunningLevel level) {
		// Only add a fee if it contains InvoiceLines and is not a statement
		boolean onlyInvoices = level.isStatement();
		List<MDunningRunEntry> entries = run.getEntries (true, onlyInvoices);
		for(MDunningRunEntry entry : entries) {
			if (level.isShowAllDue() && level.isShowNotDue() && entry.getAmt().compareTo(Env.ZERO) < 0) {
				// showing all the invoices and the amount of the entry is negative - don't generate a fee
				continue;
			}
			MDunningRunLine line = new MDunningRunLine(entry);
			line.setFee(getCurrencyId(), level.getFeeAmt());
			line.saveEx();
			entry.setQty(entry.getQty().subtract(new BigDecimal(1)));
		}
	}	//	addFees

	/**
	 * 	Check the dunning run
	 *  1) Check for following Rule: ShowAll should produce only a record if at least one new line is found
	 */
	private void checkDunningEntry(MDunningRun run, MDunningLevel level) {
		// Check rule 1)
		if (level.isShowAllDue ()) {
			for(MDunningRunEntry entry : run.getEntries(true)) {
				// We start with saying we delete this entry as long as we don't find something new
				boolean entryDelete = true;
				for (MDunningRunLine line : entry.getLines(true)) {
					if (line.getTimesDunned() < 0) {
						// We clean up the *-1 from line 255
						line.setTimesDunned (line.getTimesDunned()*-1);
						line.saveEx();
					} else {
						// We found something new, so we would not save anything...
						entryDelete = false;
					}
				}
				if(entryDelete) {
					entry.delete (false);
				}
			}
		}
	}	//	checkDunningEntry
	
}	//	DunningRunCreate
