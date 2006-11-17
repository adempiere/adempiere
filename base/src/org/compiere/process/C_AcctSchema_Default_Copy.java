/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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
 * Portions created by Carlos Ruiz are Copyright (C) 2005 QSS Ltda.
 * Contributor(s): Carlos Ruiz (globalqss)
 *****************************************************************************/
package org.compiere.process;

import java.sql.*;
import java.util.logging.*;

import org.compiere.util.*;

/**
 * Title:	Copy Acct Info
 * Description:
 *		Copy Accounting Schema information to elements 
 *		(Existing entities are overwritten) cascade all!
 *	
 *  @author Carlos Ruiz (globalqss)
 *  @version $Id: C_AcctSchema_Default_Copy.java,v 1.0 2005/10/31 14:09:00 globalqss Exp $
 */
public class C_AcctSchema_Default_Copy extends SvrProcess
{

	/*
	 * NOTE: The original oracle procedure C_AcctSchema_Default_Copy had a
	 *       Client_ID parameter for Direct Call not implemented in this class
	 */
	
	/** The Business Partner Group		*/
	private int		ad_Client_ID = -1;
	/** The Record						*/
	private int		p_Record_ID = 0;
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;			
			else if (name.equals("AD_Client_ID"))
				ad_Client_ID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		p_Record_ID = getRecord_ID();
	}	//	prepare

	/**
	 * 	Process
	 *	@return message
	 *	@throws Exception
	 */
	protected String doIt() throws Exception
	{
		String sql;
		String sqlupd;
		String sqlins;
		int cntu = 0;
		int cnti = 0;
		int totu = 0;
		int toti = 0;

		log.info("For all Accounting Schema");
		/**
		 *	For all Accounting Schema
		 */
		sql = "SELECT * FROM C_AcctSchema_Default WHERE AD_Client_ID = " + ad_Client_ID;
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				// Update existing Product Category
				sqlupd = "UPDATE M_Product_Category_Acct "
			           + "SET P_Revenue_Acct=" + rs.getInt("P_Revenue_Acct") + ", " 
			           + "P_Expense_Acct=" + rs.getInt("P_Expense_Acct") + ", "
			           + "P_Asset_Acct=" + rs.getInt("P_Asset_Acct") + ", "
			           + "P_CoGs_Acct=" + rs.getInt("P_CoGs_Acct") + ", "
			           + "P_PurchasePriceVariance_Acct=" + rs.getInt("P_PurchasePriceVariance_Acct") + ", "
			           + "P_InvoicePriceVariance_Acct=" + rs.getInt("P_InvoicePriceVariance_Acct") + ", "
			           + "P_TradeDiscountRec_Acct=" + rs.getInt("P_TradeDiscountRec_Acct") + ", "
			           + "P_TradeDiscountGrant_Acct=" + rs.getInt("P_TradeDiscountGrant_Acct") + ", "
			           + "Updated=SysDate, "
			           + "UpdatedBy=0 "
			           + "WHERE M_Product_Category_Acct.C_AcctSchema_ID=" + rs.getInt("C_AcctSchema_ID") + " " 
			           + "AND EXISTS (SELECT * FROM M_Product_Category p " 
			           + "WHERE p.M_Product_Category_ID=M_Product_Category_Acct.M_Product_Category_ID)";
				cntu = DB.executeUpdate(sqlupd, get_TrxName());
				totu += cntu;
				// Insert new Product Category
				sqlins = "INSERT INTO M_Product_Category_Acct "
					   + "(M_Product_Category_ID, C_AcctSchema_ID, "
					   + "AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy, "
					   + "P_Revenue_Acct, P_Expense_Acct, P_Asset_Acct, P_CoGs_Acct, " 
					   + "P_PurchasePriceVariance_Acct, P_InvoicePriceVariance_Acct, "
					   + "P_TradeDiscountRec_Acct, P_TradeDiscountGrant_Acct) "
					   + "SELECT p.M_Product_Category_ID, " + rs.getInt("C_AcctSchema_ID") + ", "
					   + "p.AD_Client_ID, p.AD_Org_ID, 'Y', SysDate, 0, SysDate, 0, "
					   + rs.getInt("P_Revenue_Acct") + ", " + rs.getInt("P_Expense_Acct") + ", " 
					   + rs.getInt("P_Asset_Acct") + ", " + rs.getInt("P_CoGs_Acct") + ", " 
					   + rs.getInt("P_PurchasePriceVariance_Acct") + ", " + rs.getInt("P_InvoicePriceVariance_Acct") + ", "
					   + rs.getInt("P_TradeDiscountRec_Acct") + ", " + rs.getInt("P_TradeDiscountGrant_Acct") + " "
					   + "FROM	M_Product_Category p "
					   + "WHERE AD_Client_ID=" + rs.getInt("AD_Client_ID") + " "
					   + "AND NOT EXISTS (SELECT * FROM M_Product_Category_Acct pa " 
					   + "WHERE pa.M_Product_Category_ID=p.M_Product_Category_ID " 
					   + "AND pa.C_AcctSchema_ID=" + rs.getInt("C_AcctSchema_ID") + ")";
				cnti = DB.executeUpdate(sqlins, get_TrxName());
				toti += cnti;
				log.info("Product Category = " + cntu + " / " + cnti);

				// Business Partner Group
				sqlupd = "UPDATE C_BP_Group_Acct "
					   + "SET C_Receivable_Acct=" + rs.getInt("C_Receivable_Acct") + ", "
					   + "C_PrePayment_Acct=" + rs.getInt("C_PrePayment_Acct") + ", " 
					   + "V_Liability_Acct=" + rs.getInt("V_Liability_Acct") + ", " 
					   + "V_Liability_Services_Acct=" + rs.getInt("V_Liability_Services_Acct") + ", " 
					   + "V_PrePayment_Acct=" + rs.getInt("V_PrePayment_Acct") + ", "
					   + "PayDiscount_Exp_Acct=" + rs.getInt("PayDiscount_Exp_Acct") + ", " 
					   + "PayDiscount_Rev_Acct=" + rs.getInt("PayDiscount_Rev_Acct") + ", " 
					   + "WriteOff_Acct=" + rs.getInt("WriteOff_Acct") + ", "
					   + "NotInvoicedReceipts_Acct=" + rs.getInt("NotInvoicedReceipts_Acct") + ", " 
					   + "UnEarnedRevenue_Acct=" + rs.getInt("UnEarnedRevenue_Acct") + ", " 
					   + "NotInvoicedRevenue_Acct=" + rs.getInt("NotInvoicedRevenue_Acct") + ", " 
					   + "NotInvoicedReceivables_Acct=" + rs.getInt("NotInvoicedReceivables_Acct") + ", "
					   + "Updated=SysDate, "
					   + "UpdatedBy=0 "
					   + "WHERE C_BP_Group_Acct.C_AcctSchema_ID=" + rs.getInt("C_AcctSchema_ID") + " " 
					   + "AND EXISTS (SELECT * FROM C_BP_Group_Acct x "
					   + "WHERE x.C_BP_Group_ID=C_BP_Group_Acct.C_BP_Group_ID)";
				cntu = DB.executeUpdate(sqlupd, get_TrxName());
				totu += cntu;
				// Insert new
				sqlins = "INSERT INTO C_BP_Group_Acct "
					   + "(C_BP_Group_ID, C_AcctSchema_ID, "
					   + "AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy, "
					   + "C_Receivable_Acct, C_PrePayment_Acct, " 
					   + "V_Liability_Acct, V_Liability_Services_Acct, V_PrePayment_Acct, " 
					   + "PayDiscount_Exp_Acct, PayDiscount_Rev_Acct, WriteOff_Acct, "
					   + "NotInvoicedReceipts_Acct, UnEarnedRevenue_Acct, " 
					   + "NotInvoicedRevenue_Acct, NotInvoicedReceivables_Acct) "
					   + "SELECT x.C_BP_Group_ID, " + rs.getInt("C_AcctSchema_ID") + ", "
					   + "x.AD_Client_ID, x.AD_Org_ID, 'Y', SysDate, 0, SysDate, 0, "
					   + rs.getInt("C_Receivable_Acct") + ", " + rs.getInt("C_PrePayment_Acct") + ", " 
					   + rs.getInt("V_Liability_Acct") + ", " + rs.getInt("V_Liability_Services_Acct") + ", " 
					   + rs.getInt("V_PrePayment_Acct") + ", " 
					   + rs.getInt("PayDiscount_Exp_Acct") + ", " + rs.getInt("PayDiscount_Rev_Acct") + ", " 
					   + rs.getInt("WriteOff_Acct") + ", "
					   + rs.getInt("NotInvoicedReceipts_Acct") + ", " + rs.getInt("UnEarnedRevenue_Acct") + ", " 
					   + rs.getInt("NotInvoicedRevenue_Acct") + ", " + rs.getInt("NotInvoicedReceivables_Acct") + " "
					   + "FROM	C_BP_Group x "
					   + "WHERE AD_Client_ID=" + rs.getInt("AD_Client_ID") + " " 
					   + "AND NOT EXISTS (SELECT * FROM C_BP_Group_Acct a " 
					   + "WHERE a.C_BP_Group_ID=x.C_BP_Group_ID " 
					   + "AND a.C_AcctSchema_ID=" + rs.getInt("C_AcctSchema_ID") + ")";
				cnti = DB.executeUpdate(sqlins, get_TrxName());
				toti += cnti;
				log.info("Business Group = " + cntu + " / " + cnti);

				// Business Partner Employee
				sqlupd = "UPDATE C_BP_Employee_Acct "
					   + "SET E_Expense_Acct=" + rs.getInt("E_Expense_Acct") + ", "
					   + "E_PrePayment_Acct=" + rs.getInt("E_PrePayment_Acct") + ", "
					   + "Updated=SysDate, "
					   + "UpdatedBy=0 "
					   + "WHERE C_BP_Employee_Acct.C_AcctSchema_ID=" + rs.getInt("C_AcctSchema_ID") + " " 
					   + "AND EXISTS (SELECT * FROM C_BP_Employee_Acct x "
					   + "WHERE x.C_BPartner_ID=C_BP_Employee_Acct.C_BPartner_ID)";
				cntu = DB.executeUpdate(sqlupd, get_TrxName());
				totu += cntu;
				// Insert new
				sqlins = "INSERT INTO C_BP_Employee_Acct "
					   + "(C_BPartner_ID, C_AcctSchema_ID, "
					   + "AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy, "
					   + "E_Expense_Acct, E_PrePayment_Acct) "
					   + "SELECT x.C_BPartner_ID, " + rs.getInt("C_AcctSchema_ID") + ", "
					   + "x.AD_Client_ID, x.AD_Org_ID, 'Y', SysDate, 0, SysDate, 0, "
					   + "" + rs.getInt("E_Expense_Acct") + ", " + rs.getInt("E_PrePayment_Acct") + " "
					   + "FROM	C_BPartner x "
					   + "WHERE 	AD_Client_ID=" + rs.getInt("AD_Client_ID") + " " 
					   + "AND NOT EXISTS (SELECT * FROM C_BP_Employee_Acct a " 
					   + "WHERE a.C_BPartner_ID=x.C_BPartner_ID " 
					   + "AND a.C_AcctSchema_ID=" + rs.getInt("C_AcctSchema_ID") + ")";
				cnti = DB.executeUpdate(sqlins, get_TrxName());
				toti += cnti;
				log.info("Employees = " + cntu + " / " + cnti);

				// Warehouse
				sqlupd = "UPDATE M_Warehouse_Acct "
					   + "SET W_Inventory_Acct=" + rs.getInt("W_Inventory_Acct") + ", "
					   + "W_Differences_Acct=" + rs.getInt("W_Differences_Acct") + ", "
					   + "W_Revaluation_Acct=" + rs.getInt("W_Revaluation_Acct") + ", "
					   + "W_InvActualAdjust_Acct=" + rs.getInt("W_InvActualAdjust_Acct") + ", "
					   + "Updated=SysDate, "
					   + "UpdatedBy=0 "
					   + "WHERE M_Warehouse_Acct.C_AcctSchema_ID=" + rs.getInt("C_AcctSchema_ID") + " " 
					   + "AND EXISTS (SELECT * FROM M_Warehouse_Acct x "
					   + "WHERE x.M_Warehouse_ID=M_Warehouse_Acct.M_Warehouse_ID)";
				cntu = DB.executeUpdate(sqlupd, get_TrxName());
				totu += cntu;
				// Insert new
				sqlins = "INSERT INTO M_Warehouse_Acct "
					   + "(M_Warehouse_ID, C_AcctSchema_ID, "
					   + "AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy, "
					   + "W_Inventory_Acct, W_Differences_Acct, W_Revaluation_Acct, W_InvActualAdjust_Acct) "
					   + "SELECT x.M_Warehouse_ID, " + rs.getInt("C_AcctSchema_ID") + ", "
					   + "x.AD_Client_ID, x.AD_Org_ID, 'Y', SysDate, 0, SysDate, 0, "
					   + "" + rs.getInt("W_Inventory_Acct") + ", " + rs.getInt("W_Differences_Acct") + ", " + rs.getInt("W_Revaluation_Acct") + ", " + rs.getInt("W_InvActualAdjust_Acct") + " "
					   + "FROM	M_Warehouse x "
					   + "WHERE 	AD_Client_ID=" + rs.getInt("AD_Client_ID") + " "
					   + "AND NOT EXISTS (SELECT * FROM M_Warehouse_Acct a " 
					   + "WHERE a.M_Warehouse_ID=x.M_Warehouse_ID " 
					   + "AND a.C_AcctSchema_ID=" + rs.getInt("C_AcctSchema_ID") + ")";
				cnti = DB.executeUpdate(sqlins, get_TrxName());
				toti += cnti;
				log.info("Warehouse = " + cntu + " / " + cnti);

				// Project
				sqlupd = "UPDATE C_Project_Acct "
					   + "SET PJ_Asset_Acct=" + rs.getInt("PJ_Asset_Acct") + ", " 
					   + "PJ_WIP_Acct=" + rs.getInt("PJ_Asset_Acct") + ", "
					   + "Updated=SysDate, "
					   + "UpdatedBy=0 "
					   + "WHERE C_Project_Acct.C_AcctSchema_ID=" + rs.getInt("C_AcctSchema_ID") + " " 
					   + "AND EXISTS (SELECT * FROM C_Project_Acct x "
					   + "WHERE x.C_Project_ID=C_Project_Acct.C_Project_ID)";
				cntu = DB.executeUpdate(sqlupd, get_TrxName());
				totu += cntu;
				// Insert new
				sqlins = "INSERT INTO C_Project_Acct "
					   + "(C_Project_ID, C_AcctSchema_ID, "
					   + "AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy, "
					   + "PJ_Asset_Acct, PJ_WIP_Acct) "
					   + "SELECT x.C_Project_ID, " + rs.getInt("C_AcctSchema_ID") + ", "
					   + "x.AD_Client_ID, x.AD_Org_ID, 'Y', SysDate, 0, SysDate, 0, "
					   + "" + rs.getInt("PJ_Asset_Acct") + ", " + rs.getInt("PJ_WIP_Acct") + " "
					   + "FROM	C_Project x "
					   + "WHERE 	AD_Client_ID=" + rs.getInt("AD_Client_ID") + " " 
					   + "AND NOT EXISTS (SELECT * FROM C_Project_Acct a " 
					   + "WHERE a.C_Project_ID=x.C_Project_ID " 
					   + "AND a.C_AcctSchema_ID=" + rs.getInt("C_AcctSchema_ID") + ")";
				cnti = DB.executeUpdate(sqlins, get_TrxName());
				toti += cnti;
				log.info("Project = " + cntu + " / " + cnti);

				// Tax
				sqlupd = "UPDATE C_Tax_Acct "
			           + "SET T_Due_Acct=" + rs.getInt("T_Due_Acct") + ", "
			           + "T_Liability_Acct=" + rs.getInt("T_Liability_Acct") + ", "
			           + "T_Credit_Acct=" + rs.getInt("T_Credit_Acct") + ", "
			           + "T_Receivables_Acct=" + rs.getInt("T_Receivables_Acct") + ", "
			           + "T_Expense_Acct=" + rs.getInt("T_Expense_Acct") + ", "
			           + "Updated=SysDate, "
			           + "UpdatedBy=0 "
			           + "WHERE C_Tax_Acct.C_AcctSchema_ID=" + rs.getInt("C_AcctSchema_ID") + " " 
			           + "AND EXISTS (SELECT * FROM C_Tax_Acct x "
			           + "WHERE x.C_Tax_ID=C_Tax_Acct.C_Tax_ID)";
				cntu = DB.executeUpdate(sqlupd, get_TrxName());
				totu += cntu;
				// Insert new
				sqlins = "INSERT INTO C_Tax_Acct "
					   + "(C_Tax_ID, C_AcctSchema_ID, "
					   + "AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy, "
					   + "T_Due_Acct, T_Liability_Acct, T_Credit_Acct, T_Receivables_Acct, T_Expense_Acct) "
					   + "SELECT x.C_Tax_ID, " + rs.getInt("C_AcctSchema_ID") + ", "
					   + "x.AD_Client_ID, x.AD_Org_ID, 'Y', SysDate, 0, SysDate, 0, "
					   + "" + rs.getInt("T_Due_Acct") + ", " + rs.getInt("T_Liability_Acct") + ", " + rs.getInt("T_Credit_Acct") + ", " + rs.getInt("T_Receivables_Acct") + ", " + rs.getInt("T_Expense_Acct") + " "
					   + "FROM	C_Tax x "
					   + "WHERE 	AD_Client_ID=" + rs.getInt("AD_Client_ID") + " " 
					   + "AND NOT EXISTS (SELECT * FROM C_Tax_Acct a  "
					   + "WHERE a.C_Tax_ID=x.C_Tax_ID " 
					   + "AND a.C_AcctSchema_ID=" + rs.getInt("C_AcctSchema_ID") + ")";
				cnti = DB.executeUpdate(sqlins, get_TrxName());
				toti += cnti;
				log.info("Tax = " + cntu + " / " + cnti);

				// BankAccount
				sqlupd = "UPDATE C_BankAccount_Acct "
					   + "SET B_InTransit_Acct=" + rs.getInt("B_InTransit_Acct") + ", " 
					   + "B_Asset_Acct=" + rs.getInt("B_Asset_Acct") + ", " 
					   + "B_Expense_Acct=" + rs.getInt("B_Expense_Acct") + ", " 
					   + "B_InterestRev_Acct=" + rs.getInt("B_InterestRev_Acct") + ", " 
					   + "B_InterestExp_Acct=" + rs.getInt("B_InterestExp_Acct") + ", "
					   + "B_UnIdentified_Acct=" + rs.getInt("B_UnIdentified_Acct") + ",  "
					   + "B_UnAllocatedCash_Acct=" + rs.getInt("B_UnAllocatedCash_Acct") + ", " 
					   + "B_PaymentSelect_Acct=" + rs.getInt("B_PaymentSelect_Acct") + ", " 
					   + "B_SettlementGain_Acct=" + rs.getInt("B_SettlementGain_Acct") + ", "
					   + "B_SettlementLoss_Acct=" + rs.getInt("B_SettlementLoss_Acct") + ", "
					   + "B_RevaluationGain_Acct=" + rs.getInt("B_RevaluationGain_Acct") + ",  "
					   + "B_RevaluationLoss_Acct=" + rs.getInt("B_RevaluationLoss_Acct") + ", " 
					   + "Updated=SysDate, "
					   + "UpdatedBy=0 "
					   + "WHERE C_BankAccount_Acct.C_AcctSchema_ID=" + rs.getInt("C_AcctSchema_ID") + " " 
					   + "AND EXISTS (SELECT * FROM C_BankAccount_Acct x "
					   + "WHERE x.C_BankAccount_ID=C_BankAccount_Acct.C_BankAccount_ID)";
				cntu = DB.executeUpdate(sqlupd, get_TrxName());
				totu += cntu;
				// Insert new
				sqlins = "INSERT INTO C_BankAccount_Acct "
					   + "(C_BankAccount_ID, C_AcctSchema_ID, "
					   + "AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy, "
					   + "B_InTransit_Acct, B_Asset_Acct, B_Expense_Acct, B_InterestRev_Acct, B_InterestExp_Acct, "
					   + "B_UnIdentified_Acct, B_UnAllocatedCash_Acct, B_PaymentSelect_Acct, "
					   + "B_SettlementGain_Acct, B_SettlementLoss_Acct, "
					   + "B_RevaluationGain_Acct, B_RevaluationLoss_Acct) "
					   + "SELECT x.C_BankAccount_ID, " + rs.getInt("C_AcctSchema_ID") + ", "
					   + "x.AD_Client_ID, x.AD_Org_ID, 'Y', SysDate, 0, SysDate, 0, "
					   + "" + rs.getInt("B_InTransit_Acct") + ", " + rs.getInt("B_Asset_Acct") + ", " + rs.getInt("B_Expense_Acct") + ", " + rs.getInt("B_InterestRev_Acct") + ", " + rs.getInt("B_InterestExp_Acct") + ", "
					   + "" + rs.getInt("B_UnIdentified_Acct") + ", " + rs.getInt("B_UnAllocatedCash_Acct") + ", " + rs.getInt("B_PaymentSelect_Acct") + ", "
					   + "" + rs.getInt("B_SettlementGain_Acct") + ", " + rs.getInt("B_SettlementLoss_Acct") + ", "
					   + "" + rs.getInt("B_RevaluationGain_Acct") + ", " + rs.getInt("B_RevaluationLoss_Acct") + " "
					   + "FROM	C_BankAccount x "
					   + "WHERE 	AD_Client_ID=" + rs.getInt("AD_Client_ID") + " " 
					   + "AND NOT EXISTS (SELECT * FROM C_BankAccount_Acct a " 
					   + "WHERE a.C_BankAccount_ID=x.C_BankAccount_ID " 
					   + "AND a.C_AcctSchema_ID=" + rs.getInt("C_AcctSchema_ID") + ")";
				cnti = DB.executeUpdate(sqlins, get_TrxName());
				toti += cnti;
				log.info("Bank Account = " + cntu + " / " + cnti);

				// Withholding
				sqlupd = "UPDATE C_Withholding_Acct "
					   + "SET Withholding_Acct=" + rs.getInt("Withholding_Acct") + ", "
					   + "Updated=SysDate, "
					   + "UpdatedBy=0 "
					   + "WHERE C_Withholding_Acct.C_AcctSchema_ID=" + rs.getInt("C_AcctSchema_ID") + " " 
					   + "AND EXISTS (SELECT * FROM C_Withholding_Acct x "
					   + "WHERE x.C_Withholding_ID=C_Withholding_Acct.C_Withholding_ID)";
				cntu = DB.executeUpdate(sqlupd, get_TrxName());
				totu += cntu;
				// Insert new
				sqlins = "INSERT INTO C_Withholding_Acct "
					   + "(C_Withholding_ID, C_AcctSchema_ID, "
					   + "AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy, "
					   + "Withholding_Acct) "
					   + "SELECT x.C_Withholding_ID, " + rs.getInt("C_AcctSchema_ID") + ", "
					   + "x.AD_Client_ID, x.AD_Org_ID, 'Y', SysDate, 0, SysDate, 0, "
					   + "" + rs.getInt("Withholding_Acct") + " "
					   + "FROM	C_Withholding x "
					   + "WHERE 	AD_Client_ID=" + rs.getInt("AD_Client_ID") + " "
					   + "AND NOT EXISTS (SELECT * FROM C_Withholding_Acct a " 
					   + "WHERE a.C_Withholding_ID=x.C_Withholding_ID " 
					   + "AND a.C_AcctSchema_ID=" + rs.getInt("C_AcctSchema_ID") + ")";
				cnti = DB.executeUpdate(sqlins, get_TrxName());
				toti += cnti;
				log.info("Withholding = " + cntu + " / " + cnti);

				// Charge
				sqlupd = "UPDATE C_Charge_Acct "
					   + "SET Ch_Expense_Acct=" + rs.getInt("Ch_Expense_Acct") + ", "
					   + "Ch_Revenue_Acct=" + rs.getInt("Ch_Revenue_Acct") + ", "
					   + "Updated=SysDate, "
					   + "UpdatedBy=0 "
					   + "WHERE C_Charge_Acct.C_AcctSchema_ID=" + rs.getInt("C_AcctSchema_ID") + " " 
					   + "AND EXISTS (SELECT * FROM C_Charge_Acct x "
					   + "WHERE x.C_Charge_ID=C_Charge_Acct.C_Charge_ID)";
				cntu = DB.executeUpdate(sqlupd, get_TrxName());
				totu += cntu;
				// Insert new
				sqlins = "INSERT INTO C_Charge_Acct "
					   + "(C_Charge_ID, C_AcctSchema_ID, "
					   + "AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy, "
					   + "Ch_Expense_Acct, Ch_Revenue_Acct) "
					   + "SELECT x.C_Charge_ID, " + rs.getInt("C_AcctSchema_ID") + ", "
					   + "x.AD_Client_ID, x.AD_Org_ID, 'Y', SysDate, 0, SysDate, 0, "
					   + "" + rs.getInt("Ch_Expense_Acct") + ", " + rs.getInt("Ch_Revenue_Acct") + " "
					   + "FROM	C_Charge x "
					   + "WHERE 	AD_Client_ID=" + rs.getInt("AD_Client_ID") + " "
					   + "AND NOT EXISTS (SELECT * FROM C_Charge_Acct a " 
					   + "WHERE a.C_Charge_ID=x.C_Charge_ID " 
					   + "AND a.C_AcctSchema_ID=" + rs.getInt("C_AcctSchema_ID") + ")";
				cnti = DB.executeUpdate(sqlins, get_TrxName());
				toti += cnti;
				log.info("Charge = " + cntu + " / " + cnti);

				// Cashbook
				sqlupd = "UPDATE C_Cashbook_Acct "
					   + "SET CB_Asset_Acct=" + rs.getInt("CB_Asset_Acct") + ", "
					   + "CB_Differences_Acct=" + rs.getInt("CB_Differences_Acct") + ", "
					   + "CB_CashTransfer_Acct=" + rs.getInt("CB_CashTransfer_Acct") + ", "
					   + "CB_Expense_Acct=" + rs.getInt("CB_Expense_Acct") + ", "
					   + "CB_Receipt_Acct=" + rs.getInt("CB_Receipt_Acct") + ", "
					   + "Updated=SysDate, "
					   + "UpdatedBy=0 "
					   + "WHERE C_Cashbook_Acct.C_AcctSchema_ID=" + rs.getInt("C_AcctSchema_ID") + " " 
					   + "AND EXISTS (SELECT * FROM C_Cashbook_Acct x "
					   + "WHERE x.C_Cashbook_ID=C_Cashbook_Acct.C_Cashbook_ID)";
				cntu = DB.executeUpdate(sqlupd, get_TrxName());
				totu += cntu;
				// Insert new
				sqlins = "INSERT INTO C_Cashbook_Acct "
					   + "(C_Cashbook_ID, C_AcctSchema_ID, "
					   + "AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy, "
					   + "CB_Asset_Acct, CB_Differences_Acct, CB_CashTransfer_Acct, "
					   + "CB_Expense_Acct, CB_Receipt_Acct) "
					   + "SELECT x.C_Cashbook_ID, " + rs.getInt("C_AcctSchema_ID") + ", "
					   + "x.AD_Client_ID, x.AD_Org_ID, 'Y', SysDate, 0, SysDate, 0, "
					   + "" + rs.getInt("CB_Asset_Acct") + ", " + rs.getInt("CB_Differences_Acct") + ", " + rs.getInt("CB_CashTransfer_Acct") + ", "
					   + "" + rs.getInt("CB_Expense_Acct") + ", " + rs.getInt("CB_Receipt_Acct") + " "
					   + "FROM	C_Cashbook x "
					   + "WHERE 	AD_Client_ID=" + rs.getInt("AD_Client_ID") + " " 
					   + "AND NOT EXISTS (SELECT * FROM C_Cashbook_Acct a " 
					   + "WHERE C_Cashbook_Acct.C_Cashbook_ID=x.C_Cashbook_ID " 
					   + "AND C_Cashbook_Acct.C_AcctSchema_ID=" + rs.getInt("C_AcctSchema_ID") + ")";
				cnti = DB.executeUpdate(sqlins, get_TrxName());
				toti += cnti;
				log.info("Cashbook = " + cntu + " / " + cnti);

			}
			rs.close();
			pstmt.close();
			pstmt = null;
			log.fine("Committing ...");
			DB.commit(true, get_TrxName());
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "adding missing elements", e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}

		return "@Created@=" + cnti + ", @Updated@=" + cntu;		
	}	//	doIt
	
}	//	C_AcctSchema_Default_Copy
