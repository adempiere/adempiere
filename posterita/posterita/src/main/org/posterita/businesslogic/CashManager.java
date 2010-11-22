/*
 *  Product: Posterita Web-Based POS (an Adempiere Plugin)
 *  Copyright (C) 2007  Posterita Ltd
 *  This file is part of POSterita
 *  
 *  POSterita is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

/**
	@author Alok Pathak
 */

package org.posterita.businesslogic;


import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MBankAccount;
import org.compiere.model.MCash;
import org.compiere.model.MCashBook;
import org.compiere.model.MCashLine;
import org.compiere.model.MCurrency;
import org.compiere.model.MDocType;
import org.compiere.model.MInvoice;
import org.compiere.model.MLocation;
import org.compiere.model.MOrg;
import org.compiere.model.MPayment;
import org.compiere.process.DocumentEngine;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.posterita.beans.CashBean;
import org.posterita.beans.CashBookBean;
import org.posterita.beans.CashBookDetailBean;
import org.posterita.beans.CashLineBean;
import org.posterita.beans.CashSummaryBean;
import org.posterita.beans.CurrentTillAmountBean;
import org.posterita.beans.OpenItemBean;
import org.posterita.beans.WebDocumentBean;
import org.posterita.beans.WebDocumentHeaderBean;
import org.posterita.core.JulianDate;
import org.posterita.core.utils.FormatBigDecimal;
import org.posterita.exceptions.CanNotCloseTillException;
import org.posterita.exceptions.CashBookAlreadyAssignedException;
import org.posterita.exceptions.DataException;
import org.posterita.exceptions.NoCashJournalPresentException;
import org.posterita.exceptions.NullTransferAmountException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.TransferAmountExceedsTotalAmountException;
import org.posterita.lib.UdiConstants;
import org.posterita.util.PoManager;

public class CashManager 
{
    private static final CLogger log = CLogger.getCLogger(CashManager.class);
    
    /**
     * Adjust cash book (i.e: Expense, Receipts or Bank Transfer)
     * @param ctx Context
     * @param bean Transaction Details
     * @param trxName Transaction
     * @return Cash Journal Entry created
     * @throws OperationException If entries could not be created or invalid transfer type
     */
    public static MCashLine adjustCashBook(Properties ctx, CashBookDetailBean bean, String trxName) throws OperationException
    {
        MCash cash = getCashJournal(ctx,trxName);
        
        if(bean.getAdjustmentAmount() == null)
        {
            throw new NullTransferAmountException("Amount can not be null");
        }
        
        MCashLine cashLine = null;
        
        if(bean.getTransferType().equalsIgnoreCase(MCashLine.CASHTYPE_GeneralReceipts))
        {
            cashLine = createCashLine(ctx, cash, bean.getAdjustmentAmount(), 
                    MCashLine.CASHTYPE_GeneralReceipts, bean.getDescription(), trxName);
        }
        
        else if(bean.getTransferType().equalsIgnoreCase(MCashLine.CASHTYPE_GeneralExpense))
        {
            if(bean.getAdjustmentAmount().compareTo(cash.getEndingBalance()) > 0)
            {
                throw new TransferAmountExceedsTotalAmountException("cannot transfer amount more than the ending balance");
            }
            cashLine = createCashLine(ctx, cash, bean.getAdjustmentAmount().negate(), 
                    MCashLine.CASHTYPE_GeneralExpense, bean.getDescription(), trxName); 
        }
        else if(bean.getTransferType().equalsIgnoreCase(MCashLine.CASHTYPE_BankAccountTransfer))
        {
            if(bean.getAdjustmentAmount().compareTo(cash.getEndingBalance()) > 0)
            {
                throw new TransferAmountExceedsTotalAmountException("cannot transfer amount more than the ending balance");
            }
            // @ashley TODO Refactor should be able to chose bank account on which to transfer
            int bankAccountId = POSTerminalManager.getBankAccountId(ctx, MPayment.TENDERTYPE_Check);
            cashLine = createBankTransferCashEntry(ctx, cash, bean.getAdjustmentAmount(), 
                    bankAccountId, bean.getDescription(), trxName); 
        }
        else
        {
            throw new OperationException("Invalid transfer type: " + bean.getTransferType());
        }
        
        return cashLine;
    }
    
    /**
     * Creates a new Journal entry
     * @param ctx Context
     * @param cashJournal Cash Journal
     * @param amount Amount
     * @param cashType Cash Type
     * @param trxName Transaction
     * @return The created journal entry (Cash Line)
     * @throws OperationException If the entry cannot be created
     */
    public static MCashLine createCashLine(Properties ctx, MCash cashJournal, BigDecimal amount, 
            String cashType, String trxName) throws OperationException
    {
        return createCashLine(ctx, cashJournal, amount, cashType, "", trxName);
    }
    
    /**
     * Creates a new Journal entry
     * @param ctx Context
     * @param cashJournal Cash Journal
     * @param amount Amount
     * @param cashType Cash Type
     * @param description Description of the entry
     * @param trxName Transaction
     * @return The created journal entry (Cash Line)
     * @throws OperationException If the entry cannot be created
     */
    public static MCashLine createCashLine(Properties ctx, MCash cashJournal, BigDecimal transferAmount, 
            String cashType, String description, String trxName) throws OperationException
    {
        MCashLine cashLine = new MCashLine(ctx, 0, trxName);
        cashLine.setC_Cash_ID(cashJournal.get_ID());
        cashLine.setAmount(transferAmount);
        cashLine.setCashType(cashType);
        cashLine.setDescription(description);
        PoManager.save(cashLine);
        return cashLine;
    }
    
    /**
     * Creates a new Bank Account transfer Journal entry
     * @param ctx Context
     * @param cashJournal Cash Journal
     * @param amount Amount to transfer
     * @param bankAccountId Bank Account
     * @param trxName Transaction
     * @return Cash Line entry
     * @throws OperationException If the entry cannot be created
     */
    public static MCashLine createBankTransferCashEntry(Properties ctx, MCash cashJournal, 
            BigDecimal transferAmount, int bankAccountId, String trxName) throws OperationException
    {
        return createBankTransferCashEntry(ctx, cashJournal, transferAmount, bankAccountId, null, trxName);
    }
    
    /**
     * Creates a new Bank Account transfer Journal entry
     * @param ctx Context
     * @param cashJournal Cash Journal
     * @param amount Amount to transfer
     * @param bankAccountId Bank Account
     * @param description Description of the line
     * @param trxName Transaction
     * @return Cash Line entry
     * @throws OperationException If the entry cannot be created
     */
    public static MCashLine createBankTransferCashEntry(Properties ctx, MCash cashJournal, 
            BigDecimal amount, int bankAccountId, String description, String trxName) throws OperationException
    {
        MBankAccount bankAccount = new MBankAccount(ctx, bankAccountId, trxName);
        
        StringBuffer descr = new StringBuffer();
        // +ve/zero indicates transfer to bankAccount
        // Though transfer of zero is meaningless
        if (amount.signum() >= 0)
        {
            descr.append(" -> ");
        }
        else
        {
            descr.append(" <- ");
        }
        descr.append(bankAccount.getAccountNo()).append(" (BA)");
        
        if (description != null && description.trim().length() > 0)
        {
            descr.append("  ").append(description);
        }
        
        MCashLine cashLine = new MCashLine(ctx, 0, trxName);
        cashLine.setC_Cash_ID(cashJournal.get_ID());
        cashLine.setDescription(descr.toString());
        cashLine.setAmount(amount.negate());
        cashLine.setCashType(MCashLine.CASHTYPE_BankAccountTransfer);
        cashLine.setC_BankAccount_ID(bankAccountId);
        PoManager.save(cashLine);
        return cashLine;
    }
    
    /**
     * @param ctx Context
     * @return List of Cash Book Details
     * @throws OperationException
     */
    public static CashBookDetailBean getCashBookDetails(Properties ctx, String trxName) throws OperationException
    {
        Integer cashBookId = POSTerminalManager.getCashBookId(ctx);
        CashManager.getCashJournal(ctx, cashBookId, trxName);
        
        String sql ="select cash.C_CASH_ID," +//1
        "cash.C_CASHBOOK_ID," +//2
        "cash.NAME," +//3
        "cash.DESCRIPTION," +//4
        "cash.DATEACCT," +//5
        "cash.BEGINNINGBALANCE," +//6
        "cash.ENDINGBALANCE," +//7
        "cash.STATEMENTDIFFERENCE," +//8
        "cash.DOCSTATUS," +//9
        "book.name as bookName" +//10
        " from C_CASH cash,C_CASHBOOK book " +
        " where cash.C_CASHBOOK_ID=book.C_CASHBOOK_ID"+
        " and book.C_CashBook_ID = " + cashBookId +
        " and book.ad_client_id=" +Env.getAD_Client_ID(ctx)+
        " and book.AD_ORG_ID=" +Env.getAD_Org_ID(ctx) + 
        " order by cash.created desc";
        
        CashBookDetailBean bean = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try 
        {
            pstmt = DB.prepareStatement(sql, trxName);
            rs = pstmt.executeQuery();
            if(rs.next())
            {
                bean = new CashBookDetailBean();
                bean.setCashJournalId(Integer.valueOf(rs.getInt(1)));
                bean.setCashBookId(Integer.valueOf(rs.getInt(2)));
                bean.setCashJournalName(rs.getString(3));
                bean.setCashJournalDisc(rs.getString(4));
                bean.setDateAcct(rs.getTimestamp(5));
                bean.setBeginingBalance( rs.getBigDecimal(6));
                bean.setEndingBalance(rs.getBigDecimal(7));
                bean.setStatementDifference(rs.getBigDecimal(8));
                bean.setDocStatus(rs.getString(9));
                bean.setCashBookName(rs.getString(10));
            }
        } 
        catch (SQLException e) 
        {
            throw new OperationException(e);
        }
        finally
        {
            DB.close(rs, pstmt);
        }
        
        if (bean == null)
        {
            throw new NoCashJournalPresentException("No Cash journal is present");
        }
        
        return bean;
    }
    
    
    public static ArrayList<CashBookDetailBean> getCashBookDetailsForTill(Properties ctx,int cashBookId, Timestamp fromDate,Timestamp toDate) throws OperationException
    {
        
       
        String sql1 ="select c_cash_id from C_CASH where C_CASHBOOK_ID="+cashBookId+
        " and created between "+DB.TO_DATE(fromDate, false) + " AND " + DB.TO_DATE(toDate, false);
        
        String sql="select nvl(sum(AMOUNT),0)" +
        " from C_CASHLINE" +
        " where C_CASH_ID in (" +sql1+")"+
        " and AD_CLIENT_ID= " +Env.getAD_Client_ID(ctx)+
        " and CASHTYPE='I'"+
        " and created between " + DB.TO_DATE(fromDate, false) +
        " and " + DB.TO_DATE(toDate, false) ;
    
        
        CashBookDetailBean bean;
        ArrayList<CashBookDetailBean> list=new ArrayList<CashBookDetailBean>();
        PreparedStatement pstmt = DB.prepareStatement(sql,null);
        try 
        {
            ResultSet rs=pstmt.executeQuery();
            while(rs.next())
            {
                bean=new CashBookDetailBean();
                bean.setStatementDifference(rs.getBigDecimal(1));
                list.add(bean);
            }
            rs.close();
        } 
        catch (SQLException e) 
        {
            throw new OperationException(e);
        }
        finally
        {
            try
            {
                if(pstmt != null)
                    pstmt.close();
            }
            catch(Exception ex){}
            
            pstmt = null;
        }
        
        return list;
    }
    
    
   
    
    public static ArrayList<CashBookDetailBean> getCashBookDetailsFyracle(Properties ctx) throws OperationException
    {
        Integer cashBookId=Integer.valueOf(POSTerminalManager.getCashBook(ctx).get_ID());
        String sql ="select cash.C_CASH_ID," +//1
        " cash.C_CASHBOOK_ID," +//2
        " cash.NAME," +//3
        " cash.DESCRIPTION," +//4
        " cash.DATEACCT," +//5
        " cash.BEGINNINGBALANCE," +//6
        " cash.ENDINGBALANCE," +//7
        " cash.STATEMENTDIFFERENCE," +//8
        " cash.DOCSTATUS," +//9
        " book.name bookName," +//10
        " cash.created, " +//11
        " cash.AD_CLIENT_ID," +//12
        " cash.AD_ORG_ID" +//13
        " from C_CASH cash,C_CASHBOOK book " +
        " where cash.C_CASHBOOK_ID=book.C_CASHBOOK_ID"+
        " and cash.C_CASHBOOK_ID="+cashBookId+ 
        " and cash.ad_client_id="+Env.getAD_Client_ID(ctx)+
        " and cash.ad_org_id="+Env.getAD_Org_ID(ctx) +
        " order by cash.created desc";
        
        CashBookDetailBean bean;
        ArrayList<CashBookDetailBean> list=new ArrayList<CashBookDetailBean>();
        PreparedStatement pstmt =DB.prepareStatement(sql,null);
        try 
        {
            ResultSet rs=pstmt.executeQuery();
            if(rs.next())
            {
                bean=new CashBookDetailBean();
                bean.setCashJournalId(Integer.valueOf(rs.getInt(1)));
                bean.setCashBookId(Integer.valueOf(rs.getInt(2)));
                bean.setCashJournalName(rs.getString(3));
                bean.setCashJournalDisc(rs.getString(4));
                bean.setDateAcct(rs.getTimestamp(5));
                bean.setBeginingBalance(new BigDecimal(rs.getString(6)));
                bean.setEndingBalance(new BigDecimal(rs.getString(7)));
                bean.setStatementDifference(new BigDecimal(rs.getString(8)));
                bean.setDocStatus(rs.getString(9));
                bean.setCashBookName(rs.getString(10));
                list.add(bean);
            }
            rs.close();
        } 
        catch (SQLException e) 
        {
            throw new OperationException(e);
        }
        finally
        {
            try
            {
                if(pstmt != null)
                    pstmt.close();
            }
            catch(Exception ex){}
            
            pstmt = null;
        }
        
        return list;
    }
    
    
    /**
     * Adjusts the beginning balance on the latest Cash journal available for the Cash Book
     * @param ctx Context
     * @param terminalId Terminal
     * @param trxName Transaction
     * @throws OperationException If Cash Journal could not be updated
     */
    public static void updateBeginningBalance(Properties ctx, int terminalId, String trxName) throws OperationException
    {
        int cashBookId = POSTerminalManager.getCashBookId(ctx, terminalId);
        
        updateCashBookBeginningBalance(ctx, cashBookId, trxName);
    }
    
    
    public static void updateCashBookBeginningBalance(Properties ctx, int cashBookId, String trxName) throws OperationException
    {
        int toJournalId=0;
        CashBean bean1 = getData(ctx,cashBookId,true,trxName);
        if(bean1!=null)
        {
            toJournalId = bean1.getCashJournalId().intValue();
        }
       
        CashBean bean2 = getData(ctx,cashBookId,false,trxName);
        
        BigDecimal endingBalance=null;
        
        if (bean2!=null)   
            endingBalance = bean2.getEndingBalance();
        
        if (endingBalance==null)
            endingBalance=new BigDecimal(0);
        
        MCash cash = new MCash(ctx,toJournalId,trxName);
        
        endingBalance = FormatBigDecimal.currency(endingBalance);
        
        cash.setBeginningBalance(endingBalance);//ending balance of the previous one is the begining balance of the new one
        PoManager.save(cash);
    }
    

    private static String getDataSQL(Properties ctx, int cashBookId)
    {
    	String sql="select * from " +
	        " (select cash.C_CASH_ID," +//1
	        "cash.C_CASHBOOK_ID," +//2
	        "cash.ENDINGBALANCE," +//3
	        "cash.DOCSTATUS," + //4
	        "cash.AD_CLIENT_ID," +//6
	        " cash.AD_ORG_ID," +//7
	        " cash.created"+//8
	        " from C_CASH cash,C_CASHBOOK book" +
	        " where cash.C_CASHBOOK_ID=book.C_CASHBOOK_ID" +
	        "@DOCSTATUS@" +
	        " and book.C_CashBook_ID="+cashBookId+
	        " and cash.ad_client_id=?"+
	        " and cash.AD_ORG_ID=? order by cash.Created Desc) cashDetails";      
        return sql;
    }
    
    public static CashBean getData(Properties ctx,int cashBookId,boolean isLatest,String trxName) throws OperationException
    {
        
        String sql = getDataSQL(ctx, cashBookId);
        
        if(isLatest)
            sql=sql.replaceAll("@DOCSTATUS@"," and cash.docstatus <> 'CO'");
        else
            sql=sql.replaceAll("@DOCSTATUS@"," and cash.docstatus = 'CO'");
        
        PreparedStatement pstmt = DB.prepareStatement(sql,trxName);
        CashBean bean=null;
        
        try 
        {
        	pstmt.setInt(1, Env.getAD_Client_ID(ctx));
        	pstmt.setInt(2, Env.getAD_Org_ID(ctx));
            ResultSet rs=pstmt.executeQuery();
            if(rs.next())
            {
                bean=new  CashBean();
                bean.setCashJournalId(Integer.valueOf(rs.getInt(1)));
                bean.setEndingBalance(rs.getBigDecimal(3));
                bean.setDocStatus(rs.getString(4));
            }
            
            rs.close();
        } 
        catch (SQLException e) 
        {
            throw new OperationException(e);
        }
        finally
        {
            try
            {
            	if (pstmt != null)
                	pstmt.close();
            }
            catch(Exception ex){}
            
            pstmt = null;
        }
        return bean;
    }
    
    public static MCashBook createCashBook(Properties ctx, int orgId, String name, int currencyId, String trxName) throws OperationException
    {
        MCashBook cashBook = new MCashBook(ctx, 0, trxName);
        cashBook.setName(name);
        cashBook.setC_Currency_ID(currencyId);
        cashBook.setAD_Org_ID(orgId);
        PoManager.save(cashBook);
        return cashBook;
    }
    
    
    private static MCash getCashJournal(Properties ctx, String trxName) throws OperationException
    {
        int cashBookId = POSTerminalManager.getCashBookId(ctx);
        return getCashJournal(ctx, cashBookId, trxName);
    }
    
    public static MCash getCashJournal(Properties ctx, int cashBookId, String trxName) throws OperationException
    {
        MCash cashJournal = getMcash(ctx, cashBookId, JulianDate.getTodayDateOnly(), trxName);
        
        if (cashJournal == null)
        {
            cashJournal = MCash.get(ctx, cashBookId, JulianDate.getTodayDateOnly(), trxName);
            CashManager.updateCashBookBeginningBalance(ctx, cashBookId, trxName);
        }
        
        return cashJournal;
    }
    
    
    private static MCash getMcash(Properties ctx, int C_CashBook_ID, 
            Timestamp dateAcct, String trxName) throws OperationException
    {
        MCash retValue = null;
        
        String sql;
		
			sql = "SELECT * FROM C_Cash c "
				+ "WHERE c.C_CashBook_ID=?"					//	#1
				//+ " AND TRUNC(c.StatementDate, 'DD')=?"			//	#2
				+ " AND c.Processed='N'"
                + " AND c.docstatus='DR'";
		
        PreparedStatement pstmt = null;
        try
        {
            pstmt = DB.prepareStatement (sql, trxName);
          
				pstmt.setInt (1, C_CashBook_ID);
				//pstmt.setTimestamp (2, TimeUtil.getDay(dateAcct));
			
            ResultSet rs = pstmt.executeQuery ();
            if (rs.next ())
                retValue = new MCash (ctx, rs, trxName);
            rs.close ();
        }
        catch (Exception e)
        {
            throw new OperationException(e);
        }
        finally
        {
            try
            {
                if (pstmt != null)
                    pstmt.close ();
                
            }
            catch (Exception e)
            {}
            
            pstmt = null;
        }
        
        return retValue;
        
        
    }
    
    
    /**
     * @param ctx
     * @param bean
     * @param trxName
     * @return
     * @throws NullTransferAmountException
     * @throws TransferAmountExceedsTotalAmountException
     * @throws OperationException
     */
    public static CashBookDetailBean CloseTill(Properties ctx, CashBookDetailBean bean, String trxName) 
        throws NullTransferAmountException, TransferAmountExceedsTotalAmountException, OperationException
    {
        int terminalId = POSTerminalManager.getTerminalId(ctx);
        MCash cashJournal = getCashJournal(ctx, trxName);
        
        bean.setCash_id(cashJournal.get_ID());
        
        BigDecimal transferAmt = (bean.getTransferAmount() == null ? Env.ZERO : bean.getTransferAmount());  
        BigDecimal differenceAmt = (bean.getDifferenceAmt() == null ? Env.ZERO : bean.getDifferenceAmt());
        BigDecimal beginingBalance = bean.getBeginingBalance();
        BigDecimal netTransfer = null;
        BigDecimal beginningBalanceDifference = Env.ZERO;
        
        CashBookDetailBean cbDetailsBean = getCashBookDetails(ctx, trxName);
        
        CurrentTillAmountBean currentAmountBean = POSManager.getCurrentTillAmount(ctx);

        beginningBalanceDifference = beginingBalance.subtract(cbDetailsBean.getBeginingBalance());

        bean.setBeginingBalance(cbDetailsBean.getBeginingBalance());
        bean.setEndingBalance(cbDetailsBean.getEndingBalance());
        bean.setStatementDifference(cbDetailsBean.getStatementDifference());
        netTransfer = transferAmt.subtract(cbDetailsBean.getBeginingBalance());
        differenceAmt = (bean.getStatementDifference().subtract(netTransfer)).negate(); 
        bean.setDifferenceAmt(differenceAmt);
        bean.setCashBookName(cbDetailsBean.getCashBookName());
        bean.setDocStatus(cbDetailsBean.getDocStatus());
      
        if( bean.getDocStatus().equals(DocumentEngine.STATUS_Completed))
        {
            throw new CanNotCloseTillException("Already closed,Cannot close till ");
        }
        
        netTransfer = netTransfer.subtract(beginningBalanceDifference);
        
        if (differenceAmt.compareTo(Env.ZERO) != 0)
        {
            createCashLine(ctx, cashJournal, differenceAmt, MCashLine.CASHTYPE_Difference, trxName);
        }
 
        BigDecimal cardTotal = currentAmountBean.getCardTotal();
        BigDecimal chequeTotal = currentAmountBean.getChequeTotal();
        
        // Do Transfer for CardTotal and Cheque total here 
        
        BigDecimal notifiedCardTotal = (bean.getCardTotal() == null ? Env.ZERO : bean.getCardTotal());
        BigDecimal notifiedChequeTotal = (bean.getChequeTotal() == null ? Env.ZERO : bean.getChequeTotal());
        bean.setCardDifference(Env.ZERO);
        bean.setChequeDifference(Env.ZERO);
        
        if(cardTotal.compareTo(notifiedCardTotal) != 0)
        {
            bean.setCardDifference(notifiedCardTotal.subtract(cardTotal));
        }

        if(chequeTotal.compareTo(notifiedChequeTotal) != 0)
        {
            bean.setChequeDifference(notifiedChequeTotal.subtract(chequeTotal));
        }

        PoManager.save(cashJournal);
        
        POSTerminalManager.closeTerminal(ctx, terminalId, cashJournal, netTransfer, chequeTotal, cardTotal, trxName);
        
        PoManager.processIt(cashJournal, DocumentEngine.ACTION_Complete);
                
       return bean;
    }
    
    
    public static void closePreviousDraftedCashjournals(Properties ctx, int cashBookId,String trxName) throws OperationException
    {
        String sql = "SELECT C_CASH_ID FROM C_Cash c "
            + "WHERE c.C_CashBook_ID=?"
            + " AND c.Processed='N'"
            + " AND c.docstatus='DR'"
            + " AND TRUNC(c.StatementDate, 'DD')<?" 
            + " order by c.StatementDate asc";
        PreparedStatement pstmt = null;
        
        MCash cash=null;
       BigDecimal endingBalance=null;
        try
        {
            pstmt = DB.prepareStatement (sql, trxName);
            pstmt.setInt (1, cashBookId);
            pstmt.setTimestamp (2, TimeUtil.getDay(JulianDate.getTodayDateOnly()));
           
            ResultSet rs = pstmt.executeQuery ();
            while(rs.next())
            {
                cash=new MCash(ctx,rs.getInt(1),null);
                if (endingBalance!=null)
                {
                    cash.setBeginningBalance(cash.getBeginningBalance().add(endingBalance));
                }
                  
                PoManager.save(cash);
                endingBalance=cash.getEndingBalance();
                 
                PoManager.processIt(cash, DocumentEngine.ACTION_Complete);
            }
            
            rs.close ();
        }
        catch (Exception e)
        {
            throw new OperationException(e);
        }
        finally
        {
            try
            {
            	pstmt.close ();
            }
            catch (Exception e)
            {}
              
            pstmt = null;
        	
        }
        
    }
    
    
    public static ArrayList<CashBean> getCashDetails(Properties ctx, int cashBookId, Integer month, Integer year, String trxName) throws OperationException
    {
    	ArrayList<CashBean> cashList = new ArrayList<CashBean>();

		int adClientId = Env.getAD_Client_ID(ctx);
		String userOrg = Env.getContext(ctx, UdiConstants.ROLE_EDITABLE_ORGS_CTX_PARAM);

		String sql = "Select C_Cash_ID, Name, Created, Updated, StatementDate, DateAcct, BeginningBalance, EndingBalance, DocStatus " 
			       + " from C_Cash" 
			       + " where AD_Client_ID=" + adClientId 
			       + " and AD_Org_ID in (" + userOrg + ")" 
			       + " and IsActive='Y'"
			       + " AND C_CashBook_ID=" + cashBookId;
		            

		if (month != null)
		{
			String mm = String.valueOf(month);
        	if (mm.length() == 1)
        	{
        		mm = "0" + mm;
        	}
			sql = sql + " and to_char(Created, 'mm') = '" + mm +"'";
		}

		if (year != null)
			sql = sql + " and to_char(Created, 'yyyy') = '" + year + "'";
		
		sql += " order by created desc";

		PreparedStatement pstmt = null;

		try
		{
			pstmt = DB.prepareStatement(sql, trxName);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next())
			{
				CashBean cashBean = new CashBean();
				cashBean.setCashId(rs.getInt(1));
				cashBean.setName(rs.getString(2));
				cashBean.setDateCreated(rs.getTimestamp(3));
				cashBean.setDateUpdated(rs.getTimestamp(4));
				cashBean.setStatmentDate(rs.getString(5));
				cashBean.setDateAcct(rs.getTimestamp(6));
				cashBean.setBeginingBalance(rs.getBigDecimal(7));
				cashBean.setEndingBalance(rs.getBigDecimal(8));
				cashBean.setDocStatus(rs.getString(9));

				cashList.add(cashBean);
			}
			
			rs.close();
		}
		catch (Exception ex)
		{
			throw new OperationException("Cannot retrieve Cash with sql: " + sql, ex);
		}
		finally
		{
			try
			{
				pstmt.close();
			}
			catch(Exception e)
			{}
			
			pstmt = null;
		}

		return cashList;
    }
    
    
    /**
     * Loads a cash book
     * @param ctx Context
     * @param cashBookId Cash Book ID to be loaded
     * @param trxName Transaction
     * @return Cash Book PO
     * @throws OperationException If teh cash book cannot be loaded
     */
    public static MCashBook loadCashBook(Properties ctx, int cashBookId, String trxName) throws OperationException
    {
        MCashBook cashBook = new MCashBook(ctx, cashBookId, trxName);
        if (cashBook.get_ID() <= 0)
        {
            throw new OperationException("Could not load cash book with id: " + cashBookId);
        }
        return cashBook;
    }
    
    public static MCash loadCash(Properties ctx, int cashId, String trxName) throws OperationException
    {
    	MCash cash = new MCash(ctx, cashId, trxName);
    	
    	if(cash.get_ID() == 0)
    		throw new OperationException("Could not load cash with i12,435.96d: " + cashId);
    	
    	return cash;
    }
    
    public static CashSummaryBean getCashSummary(Properties ctx, int cashId, String trxName) throws OperationException
    {
    	String sql = "select ca.CashType, sum(ca.Amount)"
    		       + " from C_CASHLINE ca where C_cash_ID=" + cashId
    		       + " group by ca.cashtype";
    	
    	MCash cashJournal = new MCash(ctx, cashId, trxName);
    	CashSummaryBean bean = getCashSummary(ctx, sql, trxName);
    	bean.setBeginingBalance(cashJournal.getBeginningBalance());
    	bean.setEndingBalance(cashJournal.getEndingBalance());
    	return bean;
    }
    
    public static CashSummaryBean getCashSummary(Properties ctx, String fromDate, String toDate, String trxName) throws OperationException
    {
    	int adClientId = Env.getAD_Client_ID(ctx);
    	String userOrg = Env.getContext(ctx, UdiConstants.ROLE_EDITABLE_ORGS_CTX_PARAM);
    	String sql = "select ca.CashType, sum(ca.Amount)"
    		       + " from C_CASHLINE ca where ca.AD_Client_ID=" + adClientId
    		       + " and ca.AD_Org_ID in (" + userOrg + ")"
    			   + " and ca.Created between to_date('" + fromDate + "','DD-MM-YYYY HH24:MI:SS')"
    			   + " and to_date('" + toDate + "','DD-MM-YYYY HH24:MI:SS') "
    			   + " group by ca.cashtype";
    	
    	return getCashSummary(ctx, sql, trxName);
    }
    
    private static CashSummaryBean getCashSummary(Properties ctx, String sql, String trxName) throws OperationException
    {
    	PreparedStatement pstmt = null;
    	CashSummaryBean cashSummaryBean = new CashSummaryBean();    	
    	try
    	{
    		pstmt = DB.prepareStatement(sql, trxName);
    		ResultSet rs = pstmt.executeQuery();
    		
    		while(rs.next())
    		{
    			String cashType = rs.getString(1);
    			BigDecimal amount = rs.getBigDecimal(2);
    			
    			if(MCashLine.CASHTYPE_BankAccountTransfer.equals(cashType))
    				cashSummaryBean.setBankAcctTransferAmount(amount);
    			else if(MCashLine.CASHTYPE_Charge.equals(cashType))
    				cashSummaryBean.setChargeAmount(amount);
    			else if(MCashLine.CASHTYPE_Difference.equals(cashType))
    				cashSummaryBean.setDifferenceAmount(amount);
    			else if(MCashLine.CASHTYPE_GeneralExpense.equals(cashType))
    				cashSummaryBean.setGeneralExpenseAmount(amount);
    			else if(MCashLine.CASHTYPE_GeneralReceipts.equals(cashType))
    				cashSummaryBean.setGeneralReceiptsAmount(amount);
    			else if(MCashLine.CASHTYPE_Invoice.equals(cashType))
    				cashSummaryBean.setInvoiceAmount(amount);
    			else
    				throw new OperationException("Unknown CashType: " + cashType);
    		}
    		
    		rs.close();
    	}
    	catch(Exception ex)
    	{
    		throw new OperationException("Could not retrieve cash summary with sql: " + sql, ex);
    	}
    	finally
    	{
    		try
    		{
    			pstmt.close();
    		}
    		catch(Exception e)
    		{}
    		
    		pstmt = null;
    	}
    	
    	return cashSummaryBean;
    }
    
    public static ArrayList<CashLineBean> getCashLineHistory(Properties ctx, String fromDate, String todate, String trxName) throws OperationException
    {
    	int adClientId = Env.getAD_Client_ID(ctx);
    	String userOrg = Env.getContext(ctx, UdiConstants.ROLE_EDITABLE_ORGS_CTX_PARAM);
    	
    	String sql = "select cl.C_CashLine_ID, cl.CashType, rl.name, cl.Created, cl.C_Cash_ID, cl.C_Invoice_ID, cl.Amount, cu.CurSymbol, cl.Description "
    			   + " from C_CashLine cl, AD_Ref_List rl, C_Currency cu "  
    			   + " where cl.AD_Client_ID= " + adClientId 
    			   + " and cl.AD_Org_ID in (" + userOrg + ") " 
    			   + " and rl.AD_Reference_ID = " + MCashLine.CASHTYPE_AD_Reference_ID
    			   + " and cl.CashType = rl.Value" 
    			   + " and cu.C_Currency_ID = cl.C_Currency_ID"
    			   + " and cl.Created between to_date('"+ fromDate+"','DD-MM-YYYY HH24:MI:SS') "
    			   + " and to_date('" + todate+"','DD-MM-YYYY HH24:MI:SS') "
    			   + " order by cl.created";
    	
    	return getCashLineHistory(ctx, sql, trxName);
    	
    }
    
    public static ArrayList<CashLineBean> getCashLineHistory(Properties ctx, int cashId, String trxName) throws OperationException
    {
    	int adClientId = Env.getAD_Client_ID(ctx);
    	String userOrg = Env.getContext(ctx, UdiConstants.ROLE_EDITABLE_ORGS_CTX_PARAM);
    	String sql = "select cl.C_CashLine_ID, cl.CashType, rl.name, cl.Created, cl.C_Cash_ID, cl.C_Invoice_ID, cl.Amount, cu.CurSymbol, cl.Description "
			   + " from C_CashLine cl, AD_Ref_List rl, C_Currency cu "  
			   + " where cl.AD_Client_ID= " + adClientId 
			   + " and cl.AD_Org_ID in (" + userOrg + ") " 
			   + " and rl.AD_Reference_ID = " + MCashLine.CASHTYPE_AD_Reference_ID
			   + " and cl.CashType = rl.Value" 
			   + " and cu.C_Currency_ID = cl.C_Currency_ID"
			   + " and cl.C_Cash_ID=" + cashId
			   + " order by cl.created";
    			
    	
    	return getCashLineHistory(ctx, sql, trxName);
    }
    
    private static ArrayList<CashLineBean> getCashLineHistory(Properties ctx, String sql, String trxName) throws OperationException
    {
    	ArrayList<CashLineBean> repSummaryList = new ArrayList<CashLineBean>();
    	
    	PreparedStatement pstmt = null;
    	
    	try
    	{
    		pstmt = DB.prepareStatement(sql, trxName);
    		ResultSet rs = pstmt.executeQuery();
    		
    		while(rs.next())
    		{
    			int i = 0;
    			CashLineBean repSummaryBean = new CashLineBean();
    			repSummaryBean.setCashLineId(rs.getInt(++i));
    			repSummaryBean.setCashType(rs.getString(++i));
    			repSummaryBean.setCashTypeName(rs.getString(++i));
    			repSummaryBean.setDateCreated(rs.getTimestamp(++i));
    			repSummaryBean.setCashId(rs.getInt(++i));
    			repSummaryBean.setInvoiceId(rs.getInt(++i));
    			repSummaryBean.setAmount(rs.getBigDecimal(++i));
    			repSummaryBean.setCurrency(rs.getString(++i));
    			repSummaryBean.setDescription(rs.getString(++i));
    			repSummaryList.add(repSummaryBean);
    		}
    		
    		rs.close();
    	}
    	catch(Exception ex)
    	{
    		throw new OperationException("Could not retrieve data for report summary with sql: " + sql, ex);
    	}
    	finally
    	{
    		try
    		{
    			pstmt.close();
    		}
    		catch(Exception ex){}
    		
    		pstmt = null;
    	}
    	
    	return repSummaryList;
    }
    
    
    public static MCashLine createCashLineForInvoice(Properties ctx,OpenItemBean bean,String trxName) throws OperationException
    {
        int cashBookId = POSTerminalManager.getCashBookId(ctx);
        MCash cash = MCash.get(ctx,cashBookId,JulianDate.getTodayDateOnly(),trxName);
        
        MCashLine [] cashLines = cash.getLines(true);
        if(cashLines.length==0)
        {
            updateCashBookBeginningBalance(ctx, cashBookId, trxName);
        }
        
        //Load invoice
        MInvoice invoice = new MInvoice(ctx, bean.getInvoiceId(), trxName);
        MDocType dt = MDocType.get(ctx, invoice.getC_DocType_ID());
        
        BigDecimal amt = bean.getPaymentAmt();
        BigDecimal discountAmt = bean.getDiscountAmt() == null ? Env.ZERO : bean.getDiscountAmt();
        BigDecimal writeOffAmt = bean.getWriteOffAmt() == null ? Env.ZERO : bean.getWriteOffAmt();
        
        
        if (MDocType.DOCBASETYPE_APInvoice.equals(dt.getDocBaseType())
    			|| MDocType.DOCBASETYPE_ARCreditMemo.equals(dt.getDocBaseType()) )
        {
        	amt = amt.negate();
        	discountAmt = discountAmt.negate();
        	writeOffAmt = writeOffAmt.negate();
        }
        
        MCashLine cashLine = new MCashLine(ctx,0,trxName);
        cashLine.setC_Cash_ID(cash.get_ID());       
        cashLine.setCashType(MCashLine.CASHTYPE_Invoice);
        cashLine.setC_Invoice_ID(bean.getInvoiceId());
        cashLine.setC_Currency_ID(invoice.getC_Currency_ID());
        cashLine.setAmount(amt);
        cashLine.setDiscountAmt(discountAmt); 
        cashLine.setWriteOffAmt(writeOffAmt); 
        
        PoManager.save(cashLine);
        return cashLine;
    }
    
    /**
     * Creates a new Cash Journal entry with the amount specified for the invoice
     * @param ctx Context
     * @param cashId Cash Journal
     * @param invoiceId Invoice
     * @param amount Cash Amount
     * @param trxName Transaction
     * @return Cash Journal Entry
     * @throws OperationException If Cash Line creation failed
     */
    public static MCashLine createCashLine(Properties ctx, int cashId, int invoiceId, 
            BigDecimal amount, BigDecimal writeOffAmout, BigDecimal discountAmt, 
            String trxName) throws OperationException
    {
        MInvoice invoice = new MInvoice(ctx, invoiceId, trxName);
        if (invoice.get_ID() == 0)
        {
            throw new OperationException("Could not load invoice!!!");
        }
        
        MCashLine cashLine = new MCashLine(ctx, 0, trxName);
        cashLine.setC_Cash_ID(cashId);
        cashLine.setInvoice(invoice);
        cashLine.setAmount(amount);
        cashLine.setWriteOffAmt(writeOffAmout);
        cashLine.setDiscountAmt(discountAmt);
        PoManager.save(cashLine);
        return cashLine;
    }
    
    public static WebDocumentBean getWebCashPaymentBean(Properties ctx, int cashLineId) throws OperationException, DataException
    {
        MCashLine cashLine = new MCashLine(ctx,cashLineId,null);
        MInvoice invoice = new MInvoice(ctx,cashLine.getC_Invoice_ID(),null);
        if (cashLine == null)
            throw new OperationException("Invalid operation payment is null");
        
        if (cashLine.get_ID() == 0)
            throw new OperationException("You have deleted this payment. You cannot view this payment.");
        
        WebDocumentBean bean = new WebDocumentBean();
        
        bean.setPaymentId(Integer.valueOf(cashLine.get_ID()));
        
        int currencyId = cashLine.getC_Currency_ID();
        MCurrency currency = new MCurrency(ctx,currencyId,null);
        bean.setCurrencySymbole(currency.getCurSymbol());
     
        
        MOrg myOrg = OrganisationManager.getMyOrg(ctx);
        MBPartner me = new MBPartner(ctx, myOrg.getLinkedC_BPartner_ID(null), null);
        bean.setMe(me);
       // MOrg orderOrg = new MOrg(ctx, cashLine.getAD_Org_ID(), null);
        
       /* if (orderOrg.getLinkedC_BPartner_ID() != myOrg.getLinkedC_BPartner_ID())
            throw new DocumentDoesNotBelongToYouException("This payment does not belong to you. You do not have access to it.");        
        */
        
        MBPartnerLocation meLocation[] =  MBPartnerLocation.getForBPartner(ctx,me.get_ID());
        if (meLocation.length  == 0)
            throw new OperationException("No location has been set for your organisation. Please ask your administrator to set one for you");
        
        MLocation location = new MLocation(ctx, meLocation[0].getC_Location_ID(), null);
        
        if (location == null)
            throw new OperationException("You must have a location set for your business partner, Please ask your administrator to set one for you");
        
        bean.setMeLocation(location);
        
        MBPartner you = new MBPartner(ctx, invoice.getC_BPartner_ID(), null);
        
        MBPartnerLocation youBPLocation[] = MBPartnerLocation.getForBPartner(ctx, you.get_ID());
        
        if (youBPLocation.length  ==0)
            throw new OperationException("No location has been set for the dealer organisation. Please ask your administrator to set one for you");
        
        MLocation youLocation = new MLocation(ctx, youBPLocation[0].getC_Location_ID(), null);
        
        bean.setYou(you);
        bean.setYoubpLocation(youBPLocation[0]);
        bean.setYouLocation(youLocation);
        bean.setCashLine(cashLine);
        
        WebDocumentHeaderBean headerBean = PaymentManager.createWebDocumentHeader(ctx, invoice.getAD_Org_ID(),invoice.getC_BPartner_ID(), "Completed", true,"Cash");
        String tenderTypeName = "Cash";
        
        
       
        headerBean.setPaymentType(tenderTypeName);
        
        bean.setHeaderBean(headerBean);
        bean.setInvoice(invoice);
        
        return bean;
    }
    
    /**
     * Retrieves all the cash books having a name like the search criteria
     * @param ctx Context
     * @param searchText Search criteria based on the name of cash book
     * @param trxName Transaction
     * @return List of Cash book data model
     * @throws OperationException If data could not retrieved
     */
    public static ArrayList<CashBookBean> getCashBooks(Properties ctx, String searchText, String trxName) throws OperationException
    {
        String whereClause = "C_CashBook.Name LIKE (?)";
        
        if (searchText == null)
        {
            searchText = "";
        }
        
        searchText = "%" + searchText.trim() + "%";
        
        ArrayList<Object> paramsList = new ArrayList<Object>();
        paramsList.add(searchText);
        
        return getCashBooks(ctx, whereClause, null, paramsList, trxName);
    }
    
    /**
     * Retrieves all the active Cash books
     * @param ctx Context
     * @param trxName Transaction
     * @return List of Cash book data model
     * @throws OperationException If data could not be retrieved
     */
    public static ArrayList<CashBookBean> getCashBooks(Properties ctx, String trxName) throws OperationException
    {
        return getCashBooks(ctx, "C_CashBook.IsActive='Y'", " ORDER BY C_CashBook.Name", null, trxName);
    }
    
    /**
     * Retrives all the cash book for a particular organisation
     * @param ctx Context
     * @param adOrgId Organisation Id
     * @param trxName Transaction
     * @return List of Cash book data model
     * @throws OperationException If data could not retrieved
     */
    public static ArrayList<CashBookBean> getAllCashbooks(Properties ctx, int adOrgId, String trxName) throws OperationException
    {
        String whereClause = "C_CashBook.IsActive='Y'AND C_CashBook.AD_Org_ID=?";
        
        ArrayList<Object> paramsList = new ArrayList<Object>();
        paramsList.add(new Integer(adOrgId));
        return getCashBooks(ctx, whereClause, null, paramsList, trxName);
    }
    
    /**
     * Retrieves all the cash book that can be assigned on a terminal (i.e: Not already present on another terminal).
     * @param ctx Context
     * @param adOrgId Organisation
     * @param trxName Transaction
     * @return List of Cash book data model
     * @throws OperationException If data could not be retrieved
     */
    public static ArrayList<CashBookBean> getCashBooksForTerminal(Properties ctx, int adOrgId, int terminalId, String trxName) throws OperationException
    {
        StringBuffer whereClause = new StringBuffer(); 
        whereClause.append("C_CashBook.IsActive='Y'AND C_CashBook.AD_Org_ID=? ");
        whereClause.append("AND NOT EXISTS (SELECT * FROM U_POSTerminal WHERE U_POSTerminal.U_POSTerminal_ID<>? ");
        whereClause.append("AND U_POSTerminal.C_CashBook_ID=C_CashBook.C_CashBook_ID AND U_POSTerminal.IsActive='Y')");

        ArrayList<Object> paramsList = new ArrayList<Object>();
        paramsList.add(new Integer(adOrgId));
        paramsList.add(new Integer(terminalId));
        
        return getCashBooks(ctx, whereClause.toString(), " ORDER BY C_CashBook.Name", paramsList, trxName);
    }
    
    /**
     * Retrieves all the cash books based on the criteria defined in the where clause statement.
     * @param ctx Context
     * @param whereClause SQL Where clause
     * @param orderByClause Order by clause
     * @param params Parameters to set for the queries
     * @param trxName Transaction
     * @return List of Cash Book data model
     * @throws OperationException If the data could not be retrieved
     */
    private static ArrayList<CashBookBean> getCashBooks(Properties ctx, String whereClause, 
            String orderByClause, ArrayList<Object> params, String trxName) throws OperationException
    {
        ArrayList<CashBookBean> cashBookList = new ArrayList<CashBookBean>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        StringBuffer sqlStmt = new StringBuffer();
        sqlStmt.append("SELECT * FROM C_CashBook WHERE C_CashBook.AD_Client_ID=? ");
        
        if (whereClause != null && whereClause.trim().length() > 0)
        {
            sqlStmt.append(" AND ");
            sqlStmt.append(whereClause);
        }
        sqlStmt.append(" AND C_CashBook.AD_Org_ID IN (")
               .append(Env.getContext(ctx, UdiConstants.ROLE_EDITABLE_ORGS_CTX_PARAM))
               .append(") ");
        
        if (orderByClause == null || orderByClause.length() == 0)
        {
            orderByClause = "ORDER BY Name";
        }
        sqlStmt.append(orderByClause);
        
        try
        {
            int paramIndex = 1;
            pstmt = DB.prepareStatement(sqlStmt.toString(), trxName);
            pstmt.setInt(paramIndex++, Env.getAD_Client_ID(ctx));
            
            if (params != null)
            {
                for (int i = 0; i < params.size(); i++)
                {
                    pstmt.setObject(paramIndex++, params.get(i));
                }
            }
            
            rs = pstmt.executeQuery();
            
            while (rs.next())
            {
                MCashBook cashBook = new MCashBook(ctx, rs, trxName);
                CashBookBean bean = new CashBookBean();
                bean.setCashBookId(cashBook.get_ID());
                bean.setCashBookName(cashBook.getName());
                bean.setDescription(cashBook.getDescription());
                bean.setOrgId(cashBook.getAD_Org_ID());
                bean.setIsActive(cashBook.isActive());
                bean.setIsDefault(cashBook.isDefault());
                bean.setCurrencyId(cashBook.getC_Currency_ID());
                bean.setCurrency(cashBook.getC_Currency().getISO_Code());
                cashBookList.add(bean);
            }
        }
        catch (Exception ex)
        {
            log.log(Level.SEVERE, "Could not get cash books", ex);
            throw new OperationException("Could not retrieve cash books", ex);
        }
        finally
        {
            DB.close(rs, pstmt);
        }
        
        return cashBookList;
    }
    
    /**
     * Returns the Cash book data model from its PO
     * @param cashBook Cash Book PO
     * @return Cash Book data model
     */
    public static CashBookBean getBean(MCashBook cashBook)
    {
        CashBookBean bean = new CashBookBean();
        bean.setOrgId(cashBook.getAD_Org_ID());
        bean.setCashBookId(cashBook.get_ID());
        bean.setCashBookName(cashBook.getName());
        bean.setCurrencyId(cashBook.getC_Currency_ID());
        bean.setDescription(cashBook.getDescription());
        bean.setIsActive(cashBook.isActive());
        bean.setIsDefault(cashBook.isDefault());
        
        return bean;
    }
    
    /**
     * Returns the PO for the Cash Book from it's data model
     * @param ctx Context
     * @param bean Cash Book data model
     * @param trxName Transaction
     * @return Cash Book PO
     * @throws OperationException If the Cash Book PO could not be loaded
     */
    public static MCashBook getPO(Properties ctx, CashBookBean bean, String trxName) throws OperationException
    {
        MCashBook cashBook = new MCashBook(ctx, bean.getCashBookId(), trxName);
        if (cashBook.get_ID() != bean.getCashBookId())
        {
            // Happens only when trying to load a cash book that is not present
            // and the PO model resets the Id to 0
            throw new OperationException("Could not load cash book");
        }
        cashBook.setAD_Org_ID(bean.getOrgId());
        cashBook.setC_Currency_ID(bean.getCurrencyId());
        cashBook.setDescription(bean.getDescription());
        cashBook.setName(bean.getCashBookName());
        cashBook.setIsActive(bean.getIsActive());
        cashBook.setIsDefault(bean.getIsDefault());
        
        return cashBook;
    }
    
    /**
     * Returns the data model of the Cash Book PO
     * @param ctx Context
     * @param cashBookId Cash Book ID
     * @param trxName Transaction
     * @return Cash Book data model
     * @throws OperationException If Cash book PO could not be loaded
     */
    public static CashBookBean getCashBook(Properties ctx, int cashBookId, String trxName) throws OperationException
    {
        MCashBook cashBook = new MCashBook(ctx, cashBookId, trxName);
        if (cashBook.get_ID() <= 0)
        {
            throw new OperationException("Could not load Cash Book with id: " + cashBookId);
        }
        
        return getBean(cashBook);
    }
    
    /**
     * Creates or updates a Cash Book. For creation the cash book id in the data model should be 0
     * otherwise it will try to load a cash book with the id provided
     * @param ctx Context
     * @param bean Data Model of the cash book
     * @param trxName Transaction
     * @return Cash Book PO
     * @throws OperationException If the Cash Book PO could not be loaded or updated
     */
    public static MCashBook createUpdateCashBook(Properties ctx, CashBookBean bean, String trxName) throws OperationException
    {
        MCashBook cashBook = getPO(ctx, bean, trxName);
        if (!cashBook.isActive() && POSTerminalManager.isCashbookPresentOnTerminal(ctx, cashBook.get_ID(), trxName))
        {
            throw new CashBookAlreadyAssignedException("Cannot deactivate the cash book as it has been assigned to a terminal");
        }
        PoManager.save(cashBook);
        bean.setCashBookId(cashBook.get_ID());
        return cashBook;
    }
    
    
    /**
     * Updates the active status of a cash book
     * @param ctx Context
     * @param cashBookId Cash book ID
     * @param active Whether to set the cash book active or not
     * @param trxName Transaction
     * @throws OperationException If Cash Book could not be loaded or updated
     */
    public static void updateCashBookStatus(Properties ctx, int cashBookId, boolean active, String trxName) throws OperationException
    {
        MCashBook cashBook = loadCashBook(ctx, cashBookId, trxName);
        
        if (!active && POSTerminalManager.isCashbookPresentOnTerminal(ctx, cashBookId, trxName))
        {
            throw new CashBookAlreadyAssignedException("Cannot deactivate the cash book as it has been assigned to a terminal");
        }
        
        cashBook.setIsActive(active);
        PoManager.save(cashBook);
    }
}
