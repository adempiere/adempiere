/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package org.eevolution.model;

import java.io.File;
import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.I_C_Payment;
import org.compiere.model.MBPBankAccount;
import org.compiere.model.MCurrency;
import org.compiere.model.MPayment;
import org.compiere.model.MPaymentBatch;
import org.compiere.model.Query;
import org.compiere.model.X_C_Order;
import org.compiere.model.X_C_Payment;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *  Payroll Payment Print/Export model.
 *
 * 	@author 	victor.perez@e-evolution.com, www.e-evolution.com
 * 	@author 	oscar.gomez@e-evolution.com, www.e-evolution.com
 */
public final class MHRPaySelectionCheck extends X_HR_PaySelectionCheck
{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -280580616570711570L;

	/**
	 * 	Get Check for Payment
	 *	@param ctx context
	 *	@param C_Payment_ID id
	 *	@param trxName transaction
	 *	@return pay selection check for payment or null
	 */
	public static MHRPaySelectionCheck getOfPayment (Properties ctx, int C_Payment_ID, String trxName)
	{
		final String where = I_C_Payment.COLUMNNAME_C_Payment_ID + "=?";
		Collection<MHRPaySelectionCheck> pscs = new Query(ctx, I_HR_PaySelectionCheck.Table_Name, where , trxName)
		.setParameters(new Object[]{C_Payment_ID})
		.list();
		
		MHRPaySelectionCheck retValue = null;
		int count = 0;
		for(MHRPaySelectionCheck psc : pscs)
		{
			if (retValue == null)
				retValue = psc;
			else if (!retValue.isProcessed() && psc.isProcessed())
				retValue = psc;
			count++;
		}
		
		if (count > 1)
			s_log.warning ("More then one for C_Payment_ID=" + C_Payment_ID);
		return retValue;
	}	//	getOfPayment
	
	/**************************************************************************
	 *  Payroll Get Checks of Payment Selection
	 *
	 *	@param ctx Context
	 *  @param HR_PaySelection_ID Payment Selection
	 *  @param PaymentRule Payment Rule
	 *  @param startDocumentNo start document no
	 *	@param trxName transaction
	 *  @return array of checks
	 */
	static public Collection<MHRPaySelectionCheck> get (Properties ctx, int HR_PaySelection_ID,
		String PaymentRule, int startDocumentNo, String trxName)
	{
		s_log.fine("HR_PaySelection_ID=" + HR_PaySelection_ID
			+ ", PaymentRule=" +  PaymentRule + ", startDocumentNo=" + startDocumentNo);
		
		final String where = I_HR_PaySelectionCheck.COLUMNNAME_HR_PaySelection_ID + "=? AND "
						   + I_HR_PaySelectionCheck.COLUMNNAME_PaymentRule + "=?";
		
		Collection<MHRPaySelectionCheck> pscs = new Query(ctx, I_HR_PaySelectionCheck.Table_Name, where , trxName)
		.setClient_ID()
		.setParameters(new Object[]{HR_PaySelection_ID, PaymentRule})
		.list();
		
		int docNo = startDocumentNo;
		
		for (MHRPaySelectionCheck psc : pscs)
		{		
			psc.setDocumentNo(String.valueOf(docNo++));
		}
		
		return pscs;
	}   //  get

	
	/**************************************************************************
	 *  Export to File
	 *  @param checks array of checks
	 *  @param file file to export checks
	 *  @return number of lines
	 */
	public static int exportToFile (Collection<MHRPaySelectionCheck> checks, File file)
	{
		if (checks == null || checks.size() == 0)
			return 0;
		//  Must be a file
		if (file.isDirectory())
		{
			s_log.log(Level.WARNING, "File is directory - " + file.getAbsolutePath());
			return 0;
		}
		//  delete if exists
		try
		{
			if (file.exists())
				file.delete();
		}
		catch (Exception e)
		{
			s_log.log(Level.WARNING, "Could not delete - " + file.getAbsolutePath(), e);
		}

		char x = '"';      //  ease
		int noLines = 0;
		StringBuffer line = null;
		try
		{
			FileWriter fw = new FileWriter(file);

			//  write header
			line = new StringBuffer();
			line.append(x).append("Value").append(x).append(",")
				.append(x).append("Name").append(x).append(",")
				.append(x).append("Contact").append(x).append(",")
				.append(x).append("Addr1").append(x).append(",")
				.append(x).append("Addr2").append(x).append(",")
				.append(x).append("City").append(x).append(",")
				.append(x).append("State").append(x).append(",")
				.append(x).append("ZIP").append(x).append(",")
				.append(x).append("Country").append(x).append(",")
				.append(x).append("ReferenceNo").append(x).append(",")
				.append(x).append("DocumentNo").append(x).append(",")
				.append(x).append("PayDate").append(x).append(",")
				.append(x).append("Currency").append(x).append(",")
				.append(x).append("PayAmount").append(x).append(",")
				.append(x).append("Comment").append(x)
				.append(Env.NL);
			fw.write(line.toString());
			noLines++;

			//  write lines
			for (MHRPaySelectionCheck mpp : checks)
			{
				if (mpp == null)
					continue;
				//  BPartner Info
				String bp[] = getBPartnerInfo(mpp.getC_BPartner_ID());
				line = new StringBuffer();
				line.append(x).append(bp[BP_VALUE]).append(x).append(",")   // Value
					.append(x).append(bp[BP_NAME]).append(x).append(",")    // Name
					.append(x).append(bp[BP_CONTACT]).append(x).append(",") // Contact
					.append(x).append(bp[BP_ADDR1]).append(x).append(",")   // Addr1
					.append(x).append(bp[BP_ADDR2]).append(x).append(",")   // Addr2
					.append(x).append(bp[BP_CITY]).append(x).append(",")    // City
					.append(x).append(bp[BP_REGION]).append(x).append(",")  // State
					.append(x).append(bp[BP_POSTAL]).append(x).append(",")  // ZIP
					.append(x).append(bp[BP_COUNTRY]).append(x).append(",") // Country
					.append(x).append(bp[BP_REFNO]).append(x).append(",")   // ReferenceNo
					//  Payment Info
					.append(x).append(mpp.getDocumentNo()).append(x).append(",")    // DocumentNo
					.append(mpp.getParent().getPayDate()).append(",")               // PayDate
					.append(x).append(MCurrency.getISO_Code(Env.getCtx(), mpp.getParent().getC_Currency_ID())).append(x).append(",")    // Currency
					.append(mpp.getPayAmt()).append(",")                // PayAmount
					//.append(x).append(comment.toString()).append(x)     // Comment
					.append(Env.NL);
				fw.write(line.toString());
				noLines++;
			}   //  write line

			fw.flush();
			fw.close();
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, "", e);
		}

		return noLines;
	}   //  exportToFile
	
	/**
	 *  Get Customer/Vendor Info.
	 *  Based on BP_ static variables
	 *  @param C_BPartner_ID BPartner
	 *  @return info array
	 */
	private static String[] getBPartnerInfo (int C_BPartner_ID)
	{
		String[] bp = new String[10];

		String sql = "SELECT bp.Value, bp.Name, c.Name AS Contact, "
			+ "a.Address1, a.Address2, a.City, r.Name AS Region, a.Postal, "
			+ "cc.Name AS Country, bp.ReferenceNo "
			+ "FROM C_BPartner bp, AD_User c, C_BPartner_Location l, C_Location a, C_Region r, C_Country cc "
			+ "WHERE bp.C_BPartner_ID=?"        // #1
			+ " AND bp.C_BPartner_ID=c.C_BPartner_ID(+)"
			+ " AND bp.C_BPartner_ID=l.C_BPartner_ID"
			+ " AND l.C_Location_ID=a.C_Location_ID"
			+ " AND a.C_Region_ID=r.C_Region_ID(+)"
			+ " AND a.C_Country_ID=cc.C_Country_ID "
			+ "ORDER BY l.IsBillTo DESC";
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, C_BPartner_ID);
			ResultSet rs = pstmt.executeQuery();
			//
			if (rs.next())
			{
				bp[BP_VALUE] = rs.getString(1);
				if (bp[BP_VALUE] == null)
					bp[BP_VALUE] = "";
				bp[BP_NAME] = rs.getString(2);
				if (bp[BP_NAME] == null)
					bp[BP_NAME] = "";
				bp[BP_CONTACT] = rs.getString(3);
				if (bp[BP_CONTACT] == null)
					bp[BP_CONTACT] = "";
				bp[BP_ADDR1] = rs.getString(4);
				if (bp[BP_ADDR1] == null)
					bp[BP_ADDR1] = "";
				bp[BP_ADDR2] = rs.getString(5);
				if (bp[BP_ADDR2] == null)
					bp[BP_ADDR2] = "";
				bp[BP_CITY] = rs.getString(6);
				if (bp[BP_CITY] == null)
					bp[BP_CITY] = "";
				bp[BP_REGION] = rs.getString(7);
				if (bp[BP_REGION] == null)
					bp[BP_REGION] = "";
				bp[BP_POSTAL] = rs.getString(8);
				if (bp[BP_POSTAL] == null)
					bp[BP_POSTAL] = "";
				bp[BP_COUNTRY] = rs.getString(9);
				if (bp[BP_COUNTRY] == null)
					bp[BP_COUNTRY] = "";
				bp[BP_REFNO] = rs.getString(10);
				if (bp[BP_REFNO] == null)
					bp[BP_REFNO] = "";
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			s_log.log(Level.SEVERE, sql, e);
		}
		return bp;
	}   //  getBPartnerInfo

	
	/**************************************************************************
	 * 	Confirm Print.
	 * 	Create Payments the first time 
	 * 	@param checks checks
	 * 	@param batch batch
	 * 	@return last Document number or 0 if nothing printed
	 */
	public static int confirmPrint (Collection<MHRPaySelectionCheck> checks , MPaymentBatch batch)
	{
		int lastDocumentNo = 0;
		for (MHRPaySelectionCheck check : checks)
		{

			String sqlConcept = "SELECT HR_Concept_ID FROM HR_Movement " +
									" WHERE HR_Movement_ID IN(SELECT HR_Movement_ID FROM HR_PaySelectionLine " +
									" WHERE C_BPartner_ID="+check.getC_BPartner_ID()+
									" AND HR_PaySelection_ID="+check.getHR_PaySelection_ID()+")";	
			System.err.println("Concept: " + sqlConcept);
			
			int HR_Concept_ID = DB.getSQLValue(check.get_TrxName(), sqlConcept);
			
			String sqlPayroll = "SELECT HR_Payroll_ID FROM HR_Process " +
									" WHERE HR_Process_ID IN(SELECT HR_Process_ID FROM HR_Movement "+
									" WHERE HR_Movement_ID IN(SELECT HR_Movement_ID FROM HR_PaySelectionLine " +
											" WHERE C_BPartner_ID="+check.getC_BPartner_ID()+
											" AND HR_PaySelection_ID="+check.getHR_PaySelection_ID()+") )";
			
			System.err.println("Payroll: " + sqlPayroll);
			int HR_Payroll_ID = DB.getSQLValue(check.get_TrxName(), sqlPayroll);
			MHRConcept concept = new MHRConcept(check.getCtx(),HR_Concept_ID,check.get_TrxName());
			MHRPayroll payroll = new MHRPayroll(check.getCtx(),HR_Payroll_ID,check.get_TrxName());
			
			
			MPayment payment = new MPayment(check.getCtx(), check.getC_Payment_ID(), check.get_TrxName());
			//	Existing Payment
			if (check.getC_Payment_ID() != 0)
			{
				//	Update check number
				if (check.getPaymentRule().equals(PAYMENTRULE_Check))
				{
					payment.setCheckNo(check.getDocumentNo());
					if (!payment.save())
						s_log.log(Level.SEVERE, "Payment not saved: " + payment);
				}
			}
			else	//	New Payment
			{
				payment = new MPayment(check.getCtx(), 0, null);
				// BEGIN e-Evolution ECISA
				//payment.setAD_Org_ID(check.getAD_Org_ID());
				// END e-Evolution ECISA
				//
				if (check.getPaymentRule().equals(PAYMENTRULE_Check))
					payment.setBankCheck (check.getParent().getC_BankAccount_ID(), false, check.getDocumentNo());
				else if (check.getPaymentRule().equals(PAYMENTRULE_CreditCard))
					payment.setTenderType(X_C_Payment.TENDERTYPE_CreditCard);
				else if (check.getPaymentRule().equals(PAYMENTRULE_DirectDeposit)
					|| check.getPaymentRule().equals(PAYMENTRULE_DirectDebit))
					payment.setTenderType(X_C_Payment.TENDERTYPE_DirectDebit);
				else
				{
					s_log.log(Level.SEVERE, "Unsupported Payment Rule=" + check.getPaymentRule());
					continue;
				}
				payment.setTrxType(X_C_Payment.TRXTYPE_CreditPayment);
				payment.setAmount(check.getParent().getC_Currency_ID(), check.getPayAmt());
				payment.setDiscountAmt(check.getDiscountAmt());
				payment.setDateTrx(check.getParent().getPayDate());
				payment.setDateAcct(payment.getDateTrx()); // globalqss [ 2030685 ]
				payment.setC_BPartner_ID(check.getC_BPartner_ID());
				
				/*
				//	Link to Batch
				if (batch != null)
				{
					if (batch.getC_PaymentBatch_ID() == 0)
						batch.save();	//	new
					payment.setC_PaymentBatch_ID(batch.getC_PaymentBatch_ID());
				}
				*/
				int C_Charge_ID = DB.getSQLValue(check.get_TrxName(), 
									"SELECT MAX(C_Charge_ID) FROM HR_Attribute WHERE IsActive='Y' AND HR_Concept_ID="+HR_Concept_ID);
				if(C_Charge_ID <= 0) // modify e-Evolution 25May2010  if(C_Charge_ID < 0)
					payment.setC_Charge_ID(payroll.getC_Charge_ID());
				else
					payment.setC_Charge_ID(C_Charge_ID);
				
				payment.setC_BankAccount_ID(check.getParent().getC_BankAccount_ID());
				payment.setWriteOffAmt(Env.ZERO);
				if (!payment.save())
					s_log.log(Level.SEVERE, "Payment not saved: " + payment);
				//
				int C_Payment_ID = payment.get_ID();
				if (C_Payment_ID < 1)
					s_log.log(Level.SEVERE, "Payment not created=" + check);
				else
				{
					check.setC_Payment_ID (C_Payment_ID);
					check.save();	//	Payment process needs it
					//	Should start WF
					payment.processIt(DocAction.ACTION_Complete);
					if (!payment.save())
						s_log.log(Level.SEVERE, "Payment not saved: " + payment);
					// BEGIN e-Evolution ECISA 25May2010
						payment.setAD_Org_ID(check.getAD_Org_ID());
						payment.setAD_OrgTrx_ID(check.getAD_Org_ID());
						payment.saveEx();
					// END e-Evolution 25May2010
				}
			}	//	new Payment

			//	Get Check Document No
			try
			{
				int no = Integer.parseInt(check.getDocumentNo());
				if (lastDocumentNo < no)
					lastDocumentNo = no;
			}
			catch (NumberFormatException ex)
			{
				s_log.log(Level.SEVERE, "DocumentNo=" + check.getDocumentNo(), ex);
			}
			check.setIsPrinted(true);
			check.setProcessed(true);
			if (!check.save ())
				s_log.log(Level.SEVERE, "Check not saved: " + check);
		}	//	all checks

		s_log.fine("Last Document No = " + lastDocumentNo);
		return lastDocumentNo;
	}	//	confirmPrint

	/** Logger								*/
	static private CLogger	s_log = CLogger.getCLogger (MHRPaySelectionCheck.class);
	/** BPartner Info Index for Value       */
	private static final int     BP_VALUE = 0;
	/** BPartner Info Index for Name        */
	private static final int     BP_NAME = 1;
	/** BPartner Info Index for Contact Name    */
	private static final int     BP_CONTACT = 2;
	/** BPartner Info Index for Address 1   */
	private static final int     BP_ADDR1 = 3;
	/** BPartner Info Index for Address 2   */
	private static final int     BP_ADDR2 = 4;
	/** BPartner Info Index for City        */
	private static final int     BP_CITY = 5;
	/** BPartner Info Index for Region      */
	private static final int     BP_REGION = 6;
	/** BPartner Info Index for Postal Code */
	private static final int     BP_POSTAL = 7;
	/** BPartner Info Index for Country     */
	private static final int     BP_COUNTRY = 8;
	/** BPartner Info Index for Reference No    */
	private static final int     BP_REFNO = 9;

	/**************************************************************************
	 *	Constructor
	 *  @param ctx context
	 *  @param C_PaySelectionCheck_ID C_PaySelectionCheck_ID
	 *	@param trxName transaction
	 */
	public MHRPaySelectionCheck (Properties ctx, int HR_PaySelectionCheck_ID, String trxName)
	{
		super(ctx, HR_PaySelectionCheck_ID, trxName);
		if (HR_PaySelectionCheck_ID == 0)
		{
			setPayAmt (Env.ZERO);
			setDiscountAmt(Env.ZERO);
			setIsPrinted (false);
			setIsReceipt (false);
			setQty (0);
		}
	}   //  MHRPaySelectionCheck

	/**
	 *	Load Constructor
	 *  @param ctx context
	 *  @param rs result set
	 *	@param trxName transaction
	 */
	public MHRPaySelectionCheck(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}   //  MHRPaySelectionCheck

	/**
	 * 	Create from Line
	 *	@param line payment selection
	 *	@param PaymentRule payment rule
	 */
	public MHRPaySelectionCheck (MHRPaySelectionLine line, String PaymentRule)
	{
		this (line.getCtx(), 0, line.get_TrxName());
		setClientOrg(line);
		setHR_PaySelection_ID (line.getHR_PaySelection_ID());
		setAD_Org_ID(line.getHR_Movement().getAD_Org_ID()); // add e-Evolution ECISA 24May2010
		int C_BPartner_ID = line.getHR_Movement().getC_BPartner_ID();
		setC_BPartner_ID (C_BPartner_ID);
		//
		if (X_C_Order.PAYMENTRULE_DirectDebit.equals(PaymentRule))
		{
			MBPBankAccount[] bas = MBPBankAccount.getOfBPartner (line.getCtx(), C_BPartner_ID); 
			for (int i = 0; i < bas.length; i++) 
			{
				MBPBankAccount account = bas[i];
				if (account.isDirectDebit())
				{
					setC_BP_BankAccount_ID(account.getC_BP_BankAccount_ID());
					break;
				}
			}
		}
		else if (X_C_Order.PAYMENTRULE_DirectDeposit.equals(PaymentRule))
		{
			MBPBankAccount[] bas = MBPBankAccount.getOfBPartner (line.getCtx(), C_BPartner_ID); 
			for (int i = 0; i < bas.length; i++) 
			{
				MBPBankAccount account = bas[i];
				if (account.isDirectDeposit())
				{
					setC_BP_BankAccount_ID(account.getC_BP_BankAccount_ID());
					break;
				}
			}
		}
		setPaymentRule (PaymentRule);
		//
		setIsReceipt(line.isSOTrx());
		setPayAmt (line.getPayAmt());
		setDiscountAmt(line.getDiscountAmt());
		setQty (1);
	}	//	MPaySelectionCheck

	/**
	 * 	Create from Pay Selection
	 *	@param ps payment selection
	 *	@param PaymentRule payment rule
	 */
	public MHRPaySelectionCheck (MHRPaySelection ps, String PaymentRule)
	{
		this (ps.getCtx(), 0, ps.get_TrxName());
		setClientOrg(ps);
		setHR_PaySelection_ID (ps.getHR_PaySelection_ID());
		setPaymentRule (PaymentRule);
	}	//	MHRPaySelectionCheck
	
	
	/**	Parent					*/
	private MHRPaySelection			m_parent = null;
	/**	Payment Selection lines of this check	*/
	private Collection<MHRPaySelectionLine>		m_lines = null;

	
	/**
	 * 	Add Payment Selection Line
	 *	@param line line
	 */
	public void addLine (MHRPaySelectionLine line)
	{
		if (getC_BPartner_ID() != line.getHR_Movement().getC_BPartner_ID())
			throw new IllegalArgumentException("Line for fifferent BPartner");
		//
		if (isReceipt() == line.isSOTrx())
		{
			setPayAmt (getPayAmt().add(line.getPayAmt()));
			setDiscountAmt(getDiscountAmt().add(line.getDiscountAmt()));
		}
		else
		{
			setPayAmt (getPayAmt().subtract(line.getPayAmt()));
			setDiscountAmt(getDiscountAmt().subtract(line.getDiscountAmt()));
		}
		setQty (getQty()+1);
	}	//	addLine
	
	/**
	 * 	Get Parent
	 *	@return parent
	 */
	public MHRPaySelection getParent()
	{
		if (m_parent == null)
			m_parent = new MHRPaySelection (getCtx(), getHR_PaySelection_ID(), get_TrxName());
		return m_parent;
	}	//	getParent

	/**
	 * 	Is this a valid Prepared Payment
	 *	@return true if valid
	 */
	public boolean isValid()
	{
		if (getC_BP_BankAccount_ID() != 0)
			return true;
		return !isDirect();
	}	//	isValid
	
	/**
	 * 	Is this a direct Debit or Deposit
	 *	@return true if direct
	 */
	public boolean isDirect()
	{
		return (X_C_Order.PAYMENTRULE_DirectDeposit.equals(getPaymentRule())
			|| X_C_Order.PAYMENTRULE_DirectDebit.equals(getPaymentRule()));
	}	//	isDirect
	
	/**
	 * 	String Representation
	 * 	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer("MHRPaymentCheck[");
		sb.append(get_ID()).append("-").append(getDocumentNo())
			.append("-").append(getPayAmt())
			.append(",PaymetRule=").append(getPaymentRule())
			.append(",Qty=").append(getQty())
			.append("]");
		return sb.toString();
	}	//	toString
	
	/**
	 * 	Get Payment Selection Lines of this check
	 *	@param requery requery
	 * 	@return array of peyment selection lines
	 */
	public Collection<MHRPaySelectionLine> getPaySelectionLines (boolean requery)
	{
		if (m_lines != null && !requery) {
			return m_lines;
		}		
		final String where = COLUMNNAME_HR_PaySelectionCheck_ID + "=?";
		Collection<MHRPaySelectionLine> list = new Query(getCtx(), I_HR_PaySelectionLine.Table_Name, where , get_TrxName())
		.setClient_ID()
		.setParameters(new Object[]{getHR_PaySelectionCheck_ID()})
		.setOrderBy(I_HR_PaySelectionLine.COLUMNNAME_Line)
		.list();		
		
		m_lines = list;
		return m_lines;
	}	//	getPaySelectionLines
	
	/**
	 *	Delete Payment Selection when generated as Draft (Print Preview) 
	 *	@param ctx context
	 *	@param C_Payment_ID id
	 *	@param trxName transaction
	 * @return
	 */
	public static boolean deleteGeneratedDraft(Properties ctx, int C_Payment_ID, String trxName)
	{
		
		MHRPaySelectionCheck mpsc = MHRPaySelectionCheck.getOfPayment (ctx, C_Payment_ID, trxName);
		
		if (mpsc != null && mpsc.isGeneratedDraft())  
		{
			MHRPaySelection mps = new MHRPaySelection(ctx, mpsc.getHR_PaySelection_ID(),trxName);
			MHRPaySelectionLine[] mpsl = mps.getLines(true);
			
			// Delete Pay Selection lines 
			for (int i = 0; i < mpsl.length; i++) 
			{
				if (!mpsl[i].delete(true, trxName))
					return false;
			}
			// Delete Pay Selection Check
			if (!mpsc.delete(true, trxName))
				return false;
			
			// Delete Pay Selection
			if (!mps.delete(true, trxName))
				return false;
		}
	return true;	
	}

}   //  MPaySelectionCheck
