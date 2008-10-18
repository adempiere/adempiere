/**
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

import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MCash;
import org.compiere.model.MCashBook;
import org.compiere.model.MCashLine;
import org.compiere.model.MCurrency;
import org.compiere.model.MInvoice;
import org.compiere.model.MLocation;
import org.compiere.model.MOrg;
import org.compiere.model.MPOS;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.posterita.beans.CashBean;
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
import org.posterita.exceptions.DataException;
import org.posterita.exceptions.NullTransferAmountException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.TransferAmountExceedsTotalAmountException;
import org.posterita.lib.UdiConstants;
import org.posterita.model.UDIMCash;
import org.posterita.model.UDIMCashBook;
import org.posterita.model.UDIMCashLine;




public class CashManager 
{
    public static MCash CloseCashBook(Properties ctx,CashBookDetailBean bean,String trxName) throws NullTransferAmountException,TransferAmountExceedsTotalAmountException,OperationException
    {

    	int posId = Env.getContextAsInt(ctx,UdiConstants.POS_ID);
        MPOS pos = new MPOS(ctx,posId,trxName);
        int cashBookId = pos.getC_CashBook_ID();
        MCash cash = MCash.get(ctx,cashBookId,JulianDate.getTodayDateOnly(),trxName);
        
        
        if(bean.getTransferAllAmount().booleanValue())
            bean.setTransferAmount(bean.getEndingBalanceAsString());
        
        if(bean.getTransferAmount()==null)
            throw new NullTransferAmountException("Transfer Amount can not be null"); 
        
        double transferAmt=Double.parseDouble(bean.getTransferAmount().replaceAll(",",""));
        
        if(transferAmt > Double.parseDouble(bean.getEndingBalanceAsString()))   
            throw new TransferAmountExceedsTotalAmountException("cannot transfer amount more than the ending balance");
        
        
        if( bean.getDocStatus().equals(DocumentEngine.STATUS_Completed) && transferAmt >0)
            throw new CanNotCloseTillException("Already closed,Cannot close till ");
        
        createCashLine(ctx,cash,new BigDecimal(transferAmt).negate(),MCashLine.CASHTYPE_BankAccountTransfer);
        if(transferAmt==0.0 && Double.parseDouble(bean.getEndingBalanceAsString())!=0.0)
        {
        	BigDecimal db = new BigDecimal(bean.getEndingBalanceAsString());
        	
        	cash.setBeginningBalance(db);
        }
            
        cash.save();
        UDIMCash udiCash = new UDIMCash(cash);
        
        udiCash.processIt(DocumentEngine.ACTION_Complete);
        return udiCash.getCash();
    }
    

    
    
    public static void adjustCashBook(Properties ctx,CashBookDetailBean bean,String trxName) throws OperationException
    {
        MCash cash = getMCashForPOS(ctx,trxName);
        if(bean.getAdjustmentAmount()==null)
            throw new NullTransferAmountException("Amount can not be null"); 
        if(cash==null)
        {
            int posId = Env.getContextAsInt(ctx,UdiConstants.POS_ID);
            MPOS pos = new MPOS(ctx,posId,trxName);
            int cashBookId = pos.getC_CashBook_ID();
            cash = MCash.get(ctx,cashBookId,JulianDate.getTodayDateOnly(),trxName);
            CashManager.updateBeginningBalance(ctx,posId,trxName);   
        }
           
            //throw new CanNotAjdustTillException("Already closed,Cannot close till ");
        
        if(bean.getTransferType().equalsIgnoreCase(MCashLine.CASHTYPE_GeneralReceipts))
            createCashLine(ctx,cash,new BigDecimal(bean.getAdjustmentAmount()),MCashLine.CASHTYPE_GeneralReceipts);
        
        else if(bean.getTransferType().equalsIgnoreCase(MCashLine.CASHTYPE_GeneralExpense))
        {
            if(Double.parseDouble(bean.getAdjustmentAmount()) > cash.getEndingBalance().doubleValue())  
                throw new TransferAmountExceedsTotalAmountException("cannot transfer amount more than the ending balance");
            createCashLine(ctx,cash,new BigDecimal(bean.getAdjustmentAmount()).negate(),MCashLine.CASHTYPE_GeneralExpense); 
        }
        
        else if(bean.getTransferType().equalsIgnoreCase(MCashLine.CASHTYPE_BankAccountTransfer))
        {
            if(Double.parseDouble(bean.getAdjustmentAmount()) > cash.getEndingBalance().doubleValue())  
                throw new TransferAmountExceedsTotalAmountException("cannot transfer amount more than the ending balance");
            createCashLine(ctx,cash,new BigDecimal(bean.getAdjustmentAmount()).negate(),MCashLine.CASHTYPE_BankAccountTransfer); 
        }
        
        PrintManager.printAdjustCashbookReport(ctx,bean);
        
    }
    
    private static MCashLine createCashLine(Properties ctx,MCash cash,BigDecimal transferAmount,String cashType) throws OperationException
    {
        int bankAccountId = BankManager.getorCreateMySingleBankAccount(ctx, cash.get_TrxName());
        
        transferAmount = FormatBigDecimal.currency(transferAmount);
        
        MCashLine cashLine = new MCashLine(ctx,0,cash.get_TrxName());
        cashLine.setC_Cash_ID(cash.get_ID());
        cashLine.setAmount(transferAmount);
        cashLine.setCashType(cashType);
        cashLine.setC_BankAccount_ID(bankAccountId);
        
        UDIMCashLine udiCashLine = new UDIMCashLine(cashLine);
        udiCashLine.save();
        
        
        return cashLine;
        
    }
    
    public static ArrayList<CashBookDetailBean> getCashBookDetails(Properties ctx) throws OperationException
    {
        
        Integer cashBookId= Integer.valueOf(POSTerminalManager.getPOSDefaultCashBook(ctx).get_ID());
        String sql ="select * from " + //0
        "(select cash.C_CASH_ID," +//1
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
        " and book.C_CashBook_ID = (SELECT Max(C_CashBook_ID)from C_CashBook WHERE"+
        " C_CASHBOOK_ID="+cashBookId+ ")" +
        " and book.ad_client_id="+Env.getAD_Client_ID(ctx)+
        " and book.AD_ORG_ID="+Env.getAD_Org_ID(ctx)+" order by cash.created desc) cashDets";
        
       
        
        CashBookDetailBean bean;
        ArrayList<CashBookDetailBean> list=new ArrayList<CashBookDetailBean>();
        PreparedStatement pstmt = DB.prepareStatement(sql,null);
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
                bean.setBeginingBalance( rs.getBigDecimal(6));
                bean.setEndingBalance(rs.getBigDecimal(7));
                bean.setStatementDifference(rs.getBigDecimal(8));
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
    
    
    public static ArrayList<CashBookDetailBean> getCashBookDetailsForTill(Properties ctx,int cashBookId,String fromDate,String toDate) throws OperationException
    {
        
       
        String sql1 ="select c_cash_id from C_CASH where C_CASHBOOK_ID="+cashBookId+
        //" and created between to_date('"+ fromDate+"','DD-MM-YYYY HH24:MI:SS') " +
        //" and to_date('" + toDate+"','DD-MON-YYYY HH24:MI:SS') " ;
        " and created between "+ fromDate + " and " + toDate ;
        
        String sql="select nvl(sum(AMOUNT),0)" +
        " from C_CASHLINE" +
        " where C_CASH_ID in (" +sql1+")"+
        " and AD_CLIENT_ID= " +Env.getAD_Client_ID(ctx)+
        " and CASHTYPE='I'"+
        //" and created between to_date('"+ fromDate+"','DD-MON-YYYY HH24:MI:SS') " +
        //" and to_date('" + toDate+"','DD-MON-YYYY HH24:MI:SS') " ;
        "and created between " + toDate + " and " + fromDate ;
        
    
        
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
        Integer cashBookId=Integer.valueOf(POSTerminalManager.getPOSDefaultCashBook(ctx).get_ID());
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
    
    
    public static void updateBeginningBalance(Properties ctx,int posId,String trxName) throws OperationException
    {
        
       // int posId = order.getPOSID();
        MPOS pos = new MPOS(ctx,posId,null);
        int cashBookId=pos.getC_CashBook_ID();
        int toJournalId=0;
        CashBean bean1 = getData(ctx,cashBookId,true,trxName);
        if(bean1!=null)
        {
            toJournalId = bean1.getCashJournalId().intValue();
        }
        CashBean bean2=getData(ctx,cashBookId,false,trxName);
        
        
        
        BigDecimal endingBalance=null;
        
        if (bean2!=null)   
            endingBalance = bean2.getEndingBalance();
        
        if (endingBalance==null)
            endingBalance=new BigDecimal(0);
        
        MCash cash = new MCash(ctx,toJournalId,trxName);
        
        endingBalance = FormatBigDecimal.currency(endingBalance);
        
        cash.setBeginningBalance(endingBalance);//ending balance of the previous one is the begining balance of the new one
        UDIMCash udiCash = new UDIMCash(cash);
        udiCash.save();
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
    
    public static MCashBook createCashBook(Properties ctx, String name, int currencyId, String trxName) throws OperationException
    {
        MCashBook cashBook = new MCashBook(ctx, 0, trxName);
        cashBook.setName(name);
        cashBook.setC_Currency_ID(currencyId);
        cashBook.setAD_Org_ID(Env.getAD_Org_ID(ctx));
        UDIMCashBook udiCashBook = new UDIMCashBook(cashBook);
        udiCashBook.save();
        return cashBook;
    }
    
    
    private static MCash getMCashForPOS(Properties ctx,String trxName) throws OperationException
    {
        int posId = Env.getContextAsInt(ctx,UdiConstants.POS_ID);
        MPOS pos = new MPOS(ctx,posId,null);
        int cashBookId = pos.getC_CashBook_ID();
        // MCashBook cashBook = new MCashBook(ctx,cashBookId,null);
        return getMcash(ctx,cashBookId,JulianDate.getTodayDateOnly(),trxName);
        
    }
    
    
    private static MCash getMcash(Properties ctx, int C_CashBook_ID, 
            Timestamp dateAcct, String trxName) throws OperationException
            {
        MCash retValue = null;
        
        String sql;
		
			sql = "SELECT * FROM C_Cash c "
				+ "WHERE c.C_CashBook_ID=?"					//	#1
				//+ " AND TRUNC(c.StatementDate)=?"			//	#2
				+ " AND c.Processed='N'"
                + " AND c.docstatus='DR'";
		
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            pstmt = DB.prepareStatement (sql, trxName);
          
				pstmt.setInt (1, C_CashBook_ID);
				//pstmt.setTimestamp (2, TimeUtil.getDay(dateAcct));
			
            rs = pstmt.executeQuery ();
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
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
        
        return retValue;
        
        
    }
    
    
    public static CashBookDetailBean CloseTill(Properties ctx,CashBookDetailBean bean,String trxName) throws NullTransferAmountException,TransferAmountExceedsTotalAmountException,OperationException
    {
        
        int posId = Env.getContextAsInt(ctx,UdiConstants.POS_ID);
        MPOS pos = new MPOS(ctx,posId,null);
        int cashBookId = pos.getC_CashBook_ID();
        MCash cash = getMcash(ctx,cashBookId,JulianDate.getTodayDateOnly(),trxName);
        if(cash==null)
            cash = MCash.get(ctx,cashBookId,JulianDate.getTodayDateOnly(),trxName);
            
        
        if(bean.getTransferAmount()==null)
           bean.setTransferAmount("0");
        
        ArrayList<CashBookDetailBean> list = getCashBookDetails(ctx);
        BigDecimal transferAmt=new BigDecimal(bean.getTransferAmount().replaceAll(",",""));
        BigDecimal differenceAmt =null;
        if(bean.getBeginingBalance()==null)
            bean.setBeginingBalance(new BigDecimal(0));
        BigDecimal beginingBalance=bean.getBeginingBalance();
        BigDecimal netTransfer=null;
        boolean changeInBeginingBalance=false;
     
        
        for(CashBookDetailBean dBean : list)
        {
            if(beginingBalance.doubleValue()!=dBean.getBeginingBalance().doubleValue()|| beginingBalance.doubleValue()!=0.0)
            {
                beginingBalance=beginingBalance.subtract(dBean.getBeginingBalance());
                changeInBeginingBalance=true;
            }
               
            
            bean.setBeginingBalance(dBean.getBeginingBalance());
            bean.setEndingBalance(dBean.getEndingBalance());
            bean.setStatementDifference(dBean.getStatementDifference());
            netTransfer=transferAmt.subtract(dBean.getBeginingBalance());
            differenceAmt = (bean.getStatementDifference().subtract(netTransfer)).negate(); 
            bean.setDifferenceAmt(differenceAmt+"");
            bean.setCashBookName(dBean.getCashBookName());
            bean.setDocStatus(dBean.getDocStatus());
            
        }
        
      
        if( bean.getDocStatus().equals(DocumentEngine.STATUS_Completed) && transferAmt.doubleValue() >0)
            throw new CanNotCloseTillException("Already closed,Cannot close till ");
        
        createCashLine(ctx,cash,netTransfer.negate(),MCashLine.CASHTYPE_BankAccountTransfer);
        if(changeInBeginingBalance)
        {
            MCashLine cashLine=createCashLine(ctx,cash,beginingBalance,MCashLine.CASHTYPE_BankAccountTransfer);
            cashLine.addDescription("Change In Begining Balance");
            cashLine.save();
        }
        createCashLine(ctx,cash,differenceAmt,MCashLine.CASHTYPE_Difference);
 
        if(transferAmt.doubleValue()==0.0 && bean.getEndingBalance().doubleValue()!=0.0 && bean.getStatementDifference().doubleValue()==0)
        {
        	BigDecimal endingBalance = FormatBigDecimal.currency(bean.getEndingBalance());
        	cash.setBeginningBalance(endingBalance);
        }
        	
     
        CurrentTillAmountBean tillBean = POSManager.getCurrentTillAmount(ctx);
        
        
        BigDecimal cardTotal= tillBean.getCardTotal();
        BigDecimal chequeTotal=tillBean.getChequeTotal();
      
        if(bean.getCardTotal()==null)
                bean.setCardTotal(new BigDecimal(0));
        if(bean.getChequeTotal()==null)
                bean.setChequeTotal(new BigDecimal(0));
        
        if(bean.getCardTotal()!=null && !cardTotal.equals(bean.getCardTotal()))
            bean.setCardDifference(bean.getCardTotal().subtract(cardTotal));
        else
            bean.setCardDifference(new BigDecimal(0));
        
        if(bean.getChequeTotal()!=null && !chequeTotal.equals(bean.getChequeTotal()))
            bean.setChequeDifference(bean.getChequeTotal().subtract(chequeTotal));
        else
            bean.setChequeDifference(new BigDecimal(0));

        cash.save();
         UDIMCash udiCash = new UDIMCash(cash);
         
         udiCash.processIt(DocumentEngine.ACTION_Complete);
         
         
       
       return bean;
    }
    
    
    public static void closePreviousDraftedCashjournals(Properties ctx, int cashBookId,String trxName) throws OperationException
    {
        String sql = "SELECT C_CASH_ID FROM C_Cash c "
            + "WHERE c.C_CashBook_ID=?"
            + " AND c.Processed='N'"
            + " AND c.docstatus='DR'"
            + " AND TRUNC(c.StatementDate)<?" 
            + " order by c.StatementDate asc";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MCash cash=null;
       BigDecimal endingBalance=null;
        try
        {
            pstmt = DB.prepareStatement (sql, trxName);
            pstmt.setInt (1, cashBookId);
            pstmt.setTimestamp (2, TimeUtil.getDay(JulianDate.getTodayDateOnly()));
           
            rs = pstmt.executeQuery ();
            while(rs.next())
            {
              cash=new MCash(ctx,rs.getInt(1),null);
              if(endingBalance!=null)
                  cash.setBeginningBalance(cash.getBeginningBalance().add(endingBalance));
              cash.save();
              endingBalance=cash.getEndingBalance();
              UDIMCash udiCash = new UDIMCash(cash);
              udiCash.processIt(DocumentEngine.ACTION_Complete);
            }
            
            rs.close ();
        }
        catch (Exception e)
        {
            throw new OperationException(e);
        }
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
        
    }
    
    
    public static ArrayList<CashBean> getCashDetails(Properties ctx, Integer month, Integer year, String trxName) throws OperationException
    {
    	ArrayList<CashBean> cashList = new ArrayList<CashBean>();

		int adClientId = Env.getAD_Client_ID(ctx);
		String userOrg = Env.getContext(ctx, UdiConstants.USER_ORG_CTX_PARAM);

		String sql = "Select C_Cash_ID, Name, Created, Updated, StatementDate, DateAcct, BeginningBalance, EndingBalance, DocStatus " 
			       + " from C_Cash" 
			       + " where AD_Client_ID=" + adClientId 
			       + " and AD_Org_ID in (" + userOrg + ")" 
			       + " and IsActive='Y'";

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
    	
    	return getCashSummary(ctx, sql, trxName);
    }
    
    public static CashSummaryBean getCashSummary(Properties ctx, String fromDate, String toDate, String trxName) throws OperationException
    {
    	int adClientId = Env.getAD_Client_ID(ctx);
    	String userOrg = Env.getContext(ctx, UdiConstants.USER_ORG_CTX_PARAM);
    	String sql = "select ca.CashType, sum(ca.Amount)"
    		       + " from C_CASHLINE ca where ca.AD_Client_ID=" + adClientId
    		       + " and ca.AD_Org_ID in (" + userOrg + ")"
    			   //+ " and ca.Created between to_date('" + fromDate + "','DD-MM-YYYY HH24:MI:SS')"
    			   //+ " and to_date('" + toDate + "','DD-MM-YYYY HH24:MI:SS') "
    		       + " and ca.Created between "+ fromDate  + " and "+ toDate
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
    	String userOrg = Env.getContext(ctx, UdiConstants.USER_ORG_CTX_PARAM);
    	
    	String sql = "select cl.C_CashLine_ID, cl.CashType, rl.name, cl.Created, cl.C_Cash_ID, cl.C_Invoice_ID, cl.Amount, cu.CurSymbol "
    			   + " from C_CashLine cl, AD_Ref_List rl, C_Currency cu "  
    			   + " where cl.AD_Client_ID= " + adClientId 
    			   + " and cl.AD_Org_ID in (" + userOrg + ") " 
    			   + " and rl.AD_Reference_ID = " + MCashLine.CASHTYPE_AD_Reference_ID
    			   + " and cl.CashType = rl.Value" 
    			   + " and cu.C_Currency_ID = cl.C_Currency_ID"
    			   //+ " and cl.Created between to_date('"+ fromDate+"','DD-MM-YYYY HH24:MI:SS') "
    			   //+ " and to_date('" + todate+"','DD-MM-YYYY HH24:MI:SS') "
    			   + " and cl.Created between "+fromDate + " and " + todate
    			   + " order by cl.created";
    	
    	return getCashLineHistory(ctx, sql, trxName);
    	
    }
    
    public static ArrayList<CashLineBean> getCashLineHistory(Properties ctx, int cashId, String trxName) throws OperationException
    {
    	int adClientId = Env.getAD_Client_ID(ctx);
    	String userOrg = Env.getContext(ctx, UdiConstants.USER_ORG_CTX_PARAM);
    	String sql = "select cl.C_CashLine_ID, cl.CashType, rl.name, cl.Created, cl.C_Cash_ID, cl.C_Invoice_ID, cl.Amount, cu.CurSymbol "
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
        int posId = Env.getContextAsInt(ctx,UdiConstants.POS_ID);
        MPOS pos = new MPOS(ctx,posId,trxName);
        int cashBookId = pos.getC_CashBook_ID();
        MCash cash = MCash.get(ctx,cashBookId,JulianDate.getTodayDateOnly(),trxName);
        
        MCashLine [] cashLines = cash.getLines(true);
        if(cashLines.length==0)
            updateBeginningBalance(ctx,pos.get_ID(),trxName);
        
        MCashLine cashLine = new MCashLine(ctx,0,trxName);
        cashLine.setC_Cash_ID(cash.get_ID());
        cashLine.setAmount(bean.getPaymentAmt());
        cashLine.setCashType(MCashLine.CASHTYPE_Invoice);
        cashLine.setC_Invoice_ID(bean.getInvoiceId());
        if(bean.getDiscountAmt()!=null && bean.getDiscountAmt().doubleValue()>0.0)
        {
          cashLine.setDiscountAmt(bean.getDiscountAmt());   
        }
        
        if(bean.getWriteOffAmt()!=null && bean.getWriteOffAmt().doubleValue()>0.0)
        {
          cashLine.setWriteOffAmt(bean.getWriteOffAmt());   
        }
        UDIMCashLine udiCashLine = new UDIMCashLine(cashLine);
        udiCashLine.save();
        return udiCashLine.getMCashLine();
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
    
    
}
