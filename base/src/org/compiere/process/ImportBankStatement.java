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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;

import org.adempiere.core.domains.models.I_I_BankStatement;
import org.adempiere.core.domains.models.X_I_BankStatement;
import org.compiere.model.MBankAccount;
import org.compiere.model.MBankStatement;
import org.compiere.model.MBankStatementLine;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Util;

/**
 *	Import Bank Statement from I_BankStatement
 *
 *	author Eldir Tomassen
 *	@version $Id: ImportBankStatement.java,v 1.2 2006/07/30 00:51:01 jjanke Exp $
 *	@author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 *  <li> FR [ 1699 ] Add support view for Bank Statement
 *  <li> Minor change for functional programming instead SQL for insert
 *  @see https://github.com/adempiere/adempiere/issues/1699
 *  @author Nicolas Sarlabos, nicolas.sarlabos@openupsolutions.com, Openup Solutions http://openupsolutions.com/
 *  The process hangs because the statement with the transaction is not saved
 */
public class ImportBankStatement extends ImportBankStatementAbstract {
	
	/**	Bank Statement to Generate	*/
	private MBankStatement statement = null;
	private AtomicInteger lineNo = new AtomicInteger(10);
	private AtomicInteger noInsert = new AtomicInteger();
	private AtomicInteger noInsertLine = new AtomicInteger();
	
	/**
	 *  Perform process.
	 *  @return Message
	 *  @throws Exception
	 */
	protected String doIt() throws java.lang.Exception {
		log.info("AD_Org_ID=" + getOrgId() + ", C_BankAccount_ID" + getBankAccountId());
		StringBuffer sql = null;
		int no = 0;
		String clientCheck = " AND AD_Client_ID=" + getClientId();

		//	****	Prepare	****

		//	Delete Old Imported
		if (isDeleteOldImported()){
			sql = new StringBuffer ("DELETE I_BankStatement "
				  + "WHERE I_IsImported='Y'").append (clientCheck);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("Delete Old Impored =" + no);
		}

		//	Set Client, Org, IsActive, Created/Updated
		sql = new StringBuffer ("UPDATE I_BankStatement "
			  + "SET AD_Client_ID = COALESCE (AD_Client_ID,").append (getClientId()).append ("),"
			  + " AD_Org_ID = COALESCE (AD_Org_ID,").append (getOrgId()).append ("),");
		sql.append(" IsActive = COALESCE (IsActive, 'Y'),"
			  + " Created = COALESCE (Created, SysDate),"
			  + " CreatedBy = COALESCE (CreatedBy, 0),"
			  + " Updated = COALESCE (Updated, SysDate),"
			  + " UpdatedBy = COALESCE (UpdatedBy, 0),"
			  + " I_ErrorMsg = ' ',"
			  + " I_IsImported = 'N' "
			  + "WHERE I_IsImported<>'Y' OR I_IsImported IS NULL OR AD_Client_ID IS NULL OR AD_Org_ID IS NULL OR AD_Client_ID=0 OR AD_Org_ID=0");
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.info ("Reset=" + no);

		sql = new StringBuffer ("UPDATE I_BankStatement o "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Org, '"
			+ "WHERE (AD_Org_ID IS NULL OR AD_Org_ID=0"
			+ " OR EXISTS (SELECT * FROM AD_Org oo WHERE o.AD_Org_ID=oo.AD_Org_ID AND (oo.IsSummary='Y' OR oo.IsActive='N')))"
			+ " AND I_IsImported<>'Y'").append (clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.warning ("Invalid Org=" + no);
			
		//	Set Bank Account
		sql = new StringBuffer("UPDATE I_BankStatement i "
			+ "SET C_BankAccount_ID="
			+ "( "
			+ " SELECT C_BankAccount_ID "
			+ " FROM C_BankAccount a, C_Bank b "
			+ " WHERE b.IsOwnBank='Y' "
			+ " AND a.AD_Client_ID=i.AD_Client_ID "
			+ " AND a.C_Bank_ID=b.C_Bank_ID "
			+ " AND a.AccountNo=i.BankAccountNo "
			+ " AND b.RoutingNo=i.RoutingNo "
			+ " OR b.SwiftCode=i.RoutingNo "
			+ ") "
			+ "WHERE i.C_BankAccount_ID IS NULL "
			+ "AND i.I_IsImported<>'Y' "
			+ "OR i.I_IsImported IS NULL").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.info("Bank Account (With Routing No)=" + no);
		//
		sql = new StringBuffer("UPDATE I_BankStatement i " 
		 	+ "SET C_BankAccount_ID="
			+ "( "
			+ " SELECT C_BankAccount_ID "
			+ " FROM C_BankAccount a, C_Bank b "
			+ " WHERE b.IsOwnBank='Y' "
			+ " AND a.C_Bank_ID=b.C_Bank_ID " 
			+ " AND a.AccountNo=i.BankAccountNo "
			+ " AND a.AD_Client_ID=i.AD_Client_ID "
			+ ") "
			+ "WHERE i.C_BankAccount_ID IS NULL "
			+ "AND i.I_isImported<>'Y' "
			+ "OR i.I_isImported IS NULL").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.info("Bank Account (Without Routing No)=" + no);
		//
		sql = new StringBuffer("UPDATE I_BankStatement i "
			+ "SET C_BankAccount_ID=(SELECT C_BankAccount_ID FROM C_BankAccount a WHERE a.C_BankAccount_ID=").append(getBankAccountId());
		sql.append(" and a.AD_Client_ID=i.AD_Client_ID) "
			+ "WHERE i.C_BankAccount_ID IS NULL "
			+ "AND i.BankAccountNo IS NULL "
			+ "AND i.I_isImported<>'Y' "
			+ "OR i.I_isImported IS NULL").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.info("Bank Account=" + no);
		//	
		sql = new StringBuffer("UPDATE I_BankStatement "
			+ "SET I_isImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Bank Account, ' "
			+ "WHERE C_BankAccount_ID IS NULL "
			+ "AND I_isImported<>'Y' "
			+ "OR I_isImported IS NULL").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.warning("Invalid Bank Account=" + no);
		 
		//	Set Currency
		sql = new StringBuffer ("UPDATE I_BankStatement i "
			+ "SET C_Currency_ID=(SELECT C_Currency_ID FROM C_Currency c"
			+ " WHERE i.ISO_Code=c.ISO_Code AND c.AD_Client_ID IN (0,i.AD_Client_ID)) "
			+ "WHERE C_Currency_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.info("Set Currency=" + no);
		//
		sql = new StringBuffer("UPDATE I_BankStatement i "
			+ "SET C_Currency_ID=(SELECT C_Currency_ID FROM C_BankAccount WHERE C_BankAccount_ID=i.C_BankAccount_ID) "
			+ "WHERE i.C_Currency_ID IS NULL "
			+ "AND i.ISO_Code IS NULL").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.info("Set Currency=" + no);
		//
		sql = new StringBuffer ("UPDATE I_BankStatement "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Currency,' "
			+ "WHERE C_Currency_ID IS NULL "
			+ "AND I_IsImported<>'E' "
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.warning("Invalid Currency=" + no);
		
		 
		//	Set Amount
		 sql = new StringBuffer("UPDATE I_BankStatement "
		 	+ "SET ChargeAmt=0 "
			+ "WHERE ChargeAmt IS NULL "
			+ "AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.info("Charge Amount=" + no);
		//
		 sql = new StringBuffer("UPDATE I_BankStatement "
		 	+ "SET InterestAmt=0 "
			+ "WHERE InterestAmt IS NULL "
			+ "AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.info("Interest Amount=" + no);
		//
		 sql = new StringBuffer("UPDATE I_BankStatement "
		 	+ "SET TrxAmt=StmtAmt - InterestAmt - ChargeAmt "
			+ "WHERE TrxAmt IS NULL "
			+ "AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.info("Transaction Amount=" + no);
		//
		sql = new StringBuffer("UPDATE I_BankStatement "
			+ "SET I_isImported='E', I_ErrorMsg=I_ErrorMsg||'Err=Invalid Amount, ' "
			+ "WHERE TrxAmt + ChargeAmt + InterestAmt <> StmtAmt "
			+ "AND I_isImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.info("Invaid Amount=" + no);
		 
		 //	Set Valuta Date
		sql = new StringBuffer("UPDATE I_BankStatement "
		 	+ "SET ValutaDate=StatementLineDate "
			+ "WHERE ValutaDate IS NULL "
			+ "AND I_isImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.info("Valuta Date=" + no);
			
		//	Check Payment<->Invoice combination
		sql = new StringBuffer("UPDATE I_BankStatement "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'Err=Invalid Payment<->Invoice, ' "
			+ "WHERE I_BankStatement_ID IN "
				+ "(SELECT I_BankStatement_ID "
				+ "FROM I_BankStatement i"
				+ " INNER JOIN C_Payment p ON (i.C_Payment_ID=p.C_Payment_ID) "
				+ "WHERE i.C_Invoice_ID IS NOT NULL "
				+ " AND p.C_Invoice_ID IS NOT NULL "
				+ " AND p.C_Invoice_ID<>i.C_Invoice_ID) ")
			.append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.info("Payment<->Invoice Mismatch=" + no);
			
		//	Check Payment<->BPartner combination
		sql = new StringBuffer("UPDATE I_BankStatement "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'Err=Invalid Payment<->BPartner, ' "
			+ "WHERE I_BankStatement_ID IN "
				+ "(SELECT I_BankStatement_ID "
				+ "FROM I_BankStatement i"
				+ " INNER JOIN C_Payment p ON (i.C_Payment_ID=p.C_Payment_ID) "
				+ "WHERE i.C_BPartner_ID IS NOT NULL "
				+ " AND p.C_BPartner_ID IS NOT NULL "
				+ " AND p.C_BPartner_ID<>i.C_BPartner_ID) ")
			.append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.info("Payment<->BPartner Mismatch=" + no);
			
		//	Check Invoice<->BPartner combination
		sql = new StringBuffer("UPDATE I_BankStatement "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'Err=Invalid Invoice<->BPartner, ' "
			+ "WHERE I_BankStatement_ID IN "
				+ "(SELECT I_BankStatement_ID "
				+ "FROM I_BankStatement i"
				+ " INNER JOIN C_Invoice v ON (i.C_Invoice_ID=v.C_Invoice_ID) "
				+ "WHERE i.C_BPartner_ID IS NOT NULL "
				+ " AND v.C_BPartner_ID IS NOT NULL "
				+ " AND v.C_BPartner_ID<>i.C_BPartner_ID) ")
			.append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.info("Invoice<->BPartner Mismatch=" + no);
			
		//	Check Invoice.BPartner<->Payment.BPartner combination
		sql = new StringBuffer("UPDATE I_BankStatement "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'Err=Invalid Invoice.BPartner<->Payment.BPartner, ' "
			+ "WHERE I_BankStatement_ID IN "
				+ "(SELECT I_BankStatement_ID "
				+ "FROM I_BankStatement i"
				+ " INNER JOIN C_Invoice v ON (i.C_Invoice_ID=v.C_Invoice_ID)"
				+ " INNER JOIN C_Payment p ON (i.C_Payment_ID=p.C_Payment_ID) "
				+ "WHERE p.C_Invoice_ID<>v.C_Invoice_ID"
				+ " AND v.C_BPartner_ID<>p.C_BPartner_ID) ")
			.append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.info("Invoice.BPartner<->Payment.BPartner Mismatch=" + no);
			
		//	Detect Duplicates
		 sql = new StringBuffer("SELECT i.I_BankStatement_ID, l.C_BankStatementLine_ID, i.EftTrxID "
			+ "FROM I_BankStatement i, C_BankStatement s, C_BankStatementLine l "
			+ "WHERE i.I_isImported='N' "
			+ "AND s.C_BankStatement_ID=l.C_BankStatement_ID "
			+ "AND i.EftTrxID IS NOT NULL AND "
			//	Concatenate EFT Info
			+ "(l.EftTrxID||l.EftAmt||l.EftStatementLineDate||l.EftValutaDate||l.EftTrxType||l.EftCurrency||l.EftReference||s.EftStatementReference "
			+ "||l.EftCheckNo||l.EftMemo||l.EftPayee||l.EftPayeeAccount) "
			+ "= "
			+ "(i.EftTrxID||i.EftAmt||i.EftStatementLineDate||i.EftValutaDate||i.EftTrxType||i.EftCurrency||i.EftReference||i.EftStatementReference "
			+ "||i.EftCheckNo||i.EftMemo||i.EftPayee||i.EftPayeeAccount) ");
		
		StringBuffer updateSql = new StringBuffer("UPDATE I_Bankstatement "
				+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'Err=Duplicate['||?||']' "
				+ "WHERE I_BankStatement_ID=?").append(clientCheck);
		PreparedStatement pupdt = DB.prepareStatement(updateSql.toString(), get_TrxName());
		
		PreparedStatement pstmtDuplicates = null;
		no = 0;
		try
		{
			pstmtDuplicates = DB.prepareStatement(sql.toString(), get_TrxName());
			ResultSet rs = pstmtDuplicates.executeQuery();
			while (rs.next())
			{
				String info = "Line_ID=" + rs.getInt(2)		//	l.C_BankStatementLine_ID
				 + ",EDTTrxID=" + rs.getString(3);			//	i.EftTrxID
				pupdt.setString(1, info);	
				pupdt.setInt(2, rs.getInt(1));	//	i.I_BankStatement_ID
				pupdt.executeUpdate();
				no++;
			}
			rs.close();
			pstmtDuplicates.close();
			pupdt.close();
			
			rs = null;
			pstmtDuplicates = null;
			pupdt = null;
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "DetectDuplicates " + e.getMessage());
		}
		if (no != 0)
			log.info("Duplicates=" + no);
		
		commitEx();
		//	Import lines
		new Query(getCtx(), I_I_BankStatement.Table_Name, "I_IsImported='N'" + clientCheck, get_TrxName())
			.setOrderBy("C_BankAccount_ID, Name, EftStatementDate, EftStatementReference")
			.<X_I_BankStatement>list()
			.forEach(toImport -> {
				addToStatement(toImport);
			});
		
		//	Set Error to indicator to not imported
		sql = new StringBuffer ("UPDATE I_BankStatement "
			+ "SET I_IsImported='N', Updated=SysDate "
			+ "WHERE I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		addLog (0, null, new BigDecimal (no), "@Errors@");
		//
		addLog (0, null, new BigDecimal (noInsert.get()), "@C_BankStatement_ID@: @Inserted@");
		addLog (0, null, new BigDecimal (noInsertLine.get()), "@C_BankStatementLine_ID@: @Inserted@");
		return "";

	}	//	doIt
	
	/**
	 * Need a new bank Statement
	 * @param toImport
	 * @return
	 */
	private boolean isToCreate(X_I_BankStatement toImport) {
		boolean isToCreate = false;
		if(statement == null) {	//	First to create
			isToCreate = true;
		} else if(statement.getC_BankAccount_ID() != toImport.getC_BankAccount_ID()) {	//	Create a new Bank Statement for every account
			isToCreate = true;
		} else if(!Util.isEmpty(statement.getName()) 
				&& !Util.isEmpty(toImport.getName()) 
				&& !statement.getName().equals(toImport.getName())) {	//	Create a new Bank Statement for every statement name
			isToCreate = true;
		} else if(!Util.isEmpty(statement.getEftStatementReference()) 
				&& !Util.isEmpty(toImport.getEftStatementReference()) 
				&& !statement.getEftStatementReference().equals(toImport.getEftStatementReference())) {	//	Create a new Bank Statement for every statement reference
			isToCreate = true;
		} else if (statement.getStatementDate() != null 
				&& toImport.getStatementDate() != null
				&& !statement.getStatementDate().equals(toImport.getStatementDate())) {	//	Create a new Bank Statement for every statement date
			isToCreate = true;
		}
		//	
		return isToCreate;
	}
	
	/**
	 * Add Import line to Statement
	 * @param toImport
	 */
	private void addToStatement(X_I_BankStatement toImport) {
		//	Get the bank account for the first statement
		//	New Statement
		if(isToCreate(toImport)) {
			try {
				createStatement(toImport);
				noInsert.updateAndGet(count -> count + 1);
			} catch (Exception e) {
				statement = null;
			}
			lineNo.updateAndGet(count -> count + 10);
		}
		//	New StatementLine
		if(statement != null) {
			try {
				MBankStatementLine line = new MBankStatementLine(statement, toImport, lineNo.get());
				line.saveEx();
				toImport.setC_BankStatement_ID(statement.getC_BankStatement_ID());
				toImport.setC_BankStatementLine_ID(line.getC_BankStatementLine_ID());
				toImport.setI_IsImported(true);
				toImport.setProcessed(true);
				toImport.saveEx();
				noInsertLine.updateAndGet(count -> count + 1);
				lineNo.updateAndGet(count -> count + 10);
			} catch (Exception e) {
				
			}
		}
	}
	
	/**
	 * Create Statement
	 * @param toImport
	 */
	private void createStatement(X_I_BankStatement toImport) {
		MBankAccount account = MBankAccount.get(getCtx(), toImport.getC_BankAccount_ID());
		statement = new MBankStatement(account);
		statement.setEndingBalance(Env.ZERO);
		
		//	Copy statement data
		if (toImport.getName() != null) {
			statement.setName(toImport.getName());
		}
		if (toImport.getStatementDate() != null) {
			statement.setStatementDate(toImport.getStatementDate());
		}
		statement.setDescription(toImport.getDescription());
		statement.setEftStatementReference(toImport.getEftStatementReference());
		statement.setEftStatementDate(toImport.getEftStatementDate());
		statement.saveEx(get_TrxName());
		log.info("New Statement, Account=" + account.getAccountNo());
	}

}	//	ImportBankStatement
