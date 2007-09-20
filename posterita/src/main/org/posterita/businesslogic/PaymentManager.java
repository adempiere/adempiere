/**
 *  Product: Posterita Web-Based POS and Adempiere Plugin
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
 * created on Aug 7, 2005 by alok
 *
 */
package org.posterita.businesslogic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MBankAccount;
import org.compiere.model.MCurrency;
import org.compiere.model.MDocType;
import org.compiere.model.MInvoice;
import org.compiere.model.MLocation;
import org.compiere.model.MOrder;
import org.compiere.model.MOrg;
import org.compiere.model.MPayment;
import org.compiere.model.MRefList;
import org.compiere.print.ReportEngine;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.posterita.Constants;
import org.posterita.beans.CommandBean;
import org.posterita.beans.OpenItemBean;
import org.posterita.beans.PaymentBean;
import org.posterita.beans.PaymentHistoryBean;
import org.posterita.beans.WebDocumentBean;
import org.posterita.beans.WebDocumentHeaderBean;
import org.posterita.core.UDIMap;
import org.posterita.exceptions.BPartnerNotFoundException;
import org.posterita.exceptions.DataException;
import org.posterita.exceptions.OperationException;
import org.posterita.lib.UdiConstants;
import org.posterita.model.UDIMInvoice;
import org.posterita.model.UDIMPayment;

public class PaymentManager extends AbstractDocumentManager
{
    private static final String PAYMENT_RECEIVED = "Payment Received";
    private static final String PAYMENT_MADE = "Payment Made";
    
    //who creates an AP Payment?
    //customer creates an AP Payment
    //from which invoice?
    //from a Vendor Invoice
    
    public static MPayment createAPPayment(Properties ctx, MInvoice vendorInvoice) throws OperationException
    {
        return createAPPayment(ctx, vendorInvoice, vendorInvoice.get_TrxName());
    }
    
    public static MPayment createAPPayment(Properties ctx, MInvoice vendorInvoice, String trxName) throws OperationException
    {
        return createPayment(ctx, vendorInvoice, MDocType.DOCBASETYPE_APPayment, trxName);
    }
    
    
    //who creates an AR Receipt?
    //vendor creates an AR Receipt
    //from which invoice?
    //from a Customer Invoice
    public static MPayment createARReceipt(Properties ctx, MInvoice invoice) throws OperationException
    {
    	
        return createPayment(ctx, invoice, MDocType.DOCBASETYPE_ARReceipt,invoice.get_TrxName());
    }
    
    
    public static MPayment createPayment(Properties ctx, MInvoice invoice, String docBaseType) throws OperationException
    {
        return createPayment(ctx, invoice, docBaseType, invoice.get_TrxName());
    }
    
    private static MPayment createPayment(Properties ctx, MInvoice invoice, String docBaseType, String trxName) throws OperationException
    {
        MPayment payment;
        
        
            int orgID = Env.getAD_Org_ID(ctx);
            //get org's default bank Account
            int bankAccIds[] = MBankAccount.getAllIDs(MBankAccount.Table_Name, " ad_org_id=" + orgID + " and ad_client_id=" + Env.getAD_Client_ID(ctx) + " and isDefault='Y'", null);
            
            if (bankAccIds.length == 0)
                throw new OperationException("No default bank account found for org");
            
            MBankAccount orgBankAc = new MBankAccount(ctx, bankAccIds[0],null);
            
            payment = new MPayment(ctx,0, trxName);
            payment.setC_BankAccount_ID(bankAccIds[0]);
            payment.setC_BPartner_ID(invoice.getC_BPartner_ID());
            payment.setC_Currency_ID(invoice.getC_Currency_ID());
            payment.setC_Order_ID(invoice.getC_Order_ID());
            payment.setIsPrepayment(false);
            
            MDocType[] docTypes = MDocType.getOfDocBaseType(ctx, docBaseType);
            
            if (docTypes.length == 0)
                throw new OperationException("not document type found for payment");
            payment.setC_Invoice_ID(invoice.get_ID());
            payment.setC_DocType_ID(docTypes[0].get_ID());
            
            if(invoice.getPaymentRule().equalsIgnoreCase(MInvoice.PAYMENTRULE_CreditCard))
                payment.setTenderType(MPayment.TENDERTYPE_CreditCard);
            else if(invoice.getPaymentRule().equalsIgnoreCase(MInvoice.PAYMENTRULE_DirectDebit))
                payment.setTenderType(MPayment.TENDERTYPE_DirectDebit);
            else if(invoice.getPaymentRule().equalsIgnoreCase(MInvoice.PAYMENTRULE_DirectDeposit))
                payment.setTenderType(MPayment.TENDERTYPE_DirectDeposit);
            else
                payment.setTenderType(MPayment.TENDERTYPE_Check);
            
            
            payment.setAccountNo(orgBankAc.getAccountNo());
            payment.setPayAmt(invoice.getGrandTotal());
            
            
            if (docBaseType.equals(MDocType.DOCBASETYPE_APPayment))
            {
                payment.setIsReceipt(false);
                payment.setDescription("(AP Payment) Payment Made");
            }
            
            else
            {
                payment.setIsReceipt(true);
                payment.setDescription("(AR Receipt) Payment Received");
            }
            
            //PaySelectionCreateFrom
            
            UDIMPayment udiPayment = new UDIMPayment(payment);
            udiPayment.save();
            
            invoice.setC_Payment_ID(payment.get_ID());
            UDIMInvoice udiInvoice = new UDIMInvoice(invoice);
            udiInvoice.save();
        
        return udiPayment.getMPayment();
    }
 
    public static MPayment createARReceiptForCreditOrder(Properties ctx, MInvoice invoice,OpenItemBean bean,String trxName) throws OperationException
    {
            MPayment payment;
            int orgID = Env.getAD_Org_ID(ctx);
            //get org's default bank Account
            int bankAccIds[] = MBankAccount.getAllIDs(MBankAccount.Table_Name, " ad_org_id=" + orgID + " and ad_client_id=" + Env.getAD_Client_ID(ctx) + " and isDefault='Y'", null);
            
            if (bankAccIds.length == 0)
                throw new OperationException("No default bank account found for org");
            
            MBankAccount orgBankAc = new MBankAccount(ctx, bankAccIds[0],null);
            
            payment = new MPayment(ctx,0, trxName);
            payment.setC_BankAccount_ID(bankAccIds[0]);
            payment.setC_BPartner_ID(invoice.getC_BPartner_ID());
            payment.setC_Currency_ID(invoice.getC_Currency_ID());
            payment.setC_Order_ID(invoice.getC_Order_ID());
            payment.setIsPrepayment(false);
            payment.setDateTrx(new Timestamp(System.currentTimeMillis()));
            payment.setDateAcct(new Timestamp(System.currentTimeMillis()));
            
            MDocType[] docTypes = MDocType.getOfDocBaseType(ctx, MDocType.DOCBASETYPE_ARReceipt);
            
            if (docTypes.length == 0)
                throw new OperationException("not document type found for payment");
            payment.setC_Invoice_ID(invoice.get_ID());
            payment.setC_DocType_ID(docTypes[0].get_ID());
            payment.setTenderType(bean.getTrxType());
            if(bean.getTrxType().equalsIgnoreCase(MPayment.TENDERTYPE_Check) && bean.getChequeNo()!=null)
            {
                payment.setCheckNo(bean.getChequeNo());
            }
            else if(bean.getTrxType().equalsIgnoreCase(MPayment.TENDERTYPE_CreditCard) && bean.getCreditCardNumber()!=null)
            {
                payment.setCreditCardNumber(bean.getCreditCardNumber());
            }
            payment.setAccountNo(orgBankAc.getAccountNo());
            payment.setPayAmt(bean.getPaymentAmt());
            payment.setIsReceipt(true);
            payment.setDescription("(AR Receipt) Payment Received");
            
            UDIMPayment udiPayment = new UDIMPayment(payment);
            udiPayment.save();
            invoice.setC_Payment_ID(payment.get_ID());
            UDIMInvoice udiInvoice = new UDIMInvoice(invoice);
            udiInvoice.save();
            
            if(bean.getOverUnderPayment()!=null && bean.getOverUnderPayment().doubleValue()!=0.0)
            {
                payment.setIsOverUnderPayment(true);
                payment.setOverUnderAmt(bean.getOverUnderPayment());
            }
            if(bean.getWriteOffAmt()!=null && bean.getWriteOffAmt().doubleValue()!=0.0)
            {
              payment.setWriteOffAmt(bean.getWriteOffAmt());   
            }
            
            if(bean.getDiscountAmt()!=null && bean.getDiscountAmt().doubleValue()!=0.0)
            {
              payment.setDiscountAmt(bean.getDiscountAmt());   
            }
            udiPayment.save();
        return udiPayment.getMPayment();
    }
 
    
    
    
    public static MPayment completeAPPayment(Properties ctx, MPayment apPayment) throws OperationException
    {
        apPayment = completePayment(ctx, apPayment);
        updateReceipt(ctx, apPayment);
        return apPayment;
    }
    
    public static MPayment completePayment(Properties ctx, MPayment payment) throws OperationException
    {
        
        
        if (payment.getDocStatus().equals(DocumentEngine.STATUS_Completed))
            throw new OperationException("Cannot complete a payment which is already completed");
        
        if (payment.getDocStatus().equals(DocumentEngine.STATUS_Voided))
            throw new OperationException("Cannot complete a payment which has been voided");
        UDIMPayment udipayment = new UDIMPayment(payment);
        
        udipayment.processIt(DocumentEngine.ACTION_Complete);
        
        return payment;
    }
    /*
     * payment is apPayment
     * invoice linked to this payment is a vendor invoice
     */
    private static void updateReceipt(Properties ctx, MPayment payment) throws OperationException
    {
        MInvoice vendorInvoice = new MInvoice(ctx, payment.getC_Invoice_ID(), payment.get_TrxName());
        
        
        //we don't have customer invoice here since bp is not linked to any org
        if (vendorInvoice.getRef_Invoice_ID() == 0)
            return;
        
        MPayment arReceipt = getCounterPayment(ctx, payment);
        
        arReceipt.setC_Invoice_ID(vendorInvoice.getRef_Invoice_ID());
        arReceipt.setDescription("AR Receipt(Payment received)");
        
        UDIMPayment udiPayment = new UDIMPayment(arReceipt);
        udiPayment.save();
    }
    
    
    public static ArrayList getPaymentHistory(Properties ctx, String docStatus, Integer partnerId, Integer month, Integer year)
    {
        
        StringBuffer select = new StringBuffer();
        StringBuffer from = new StringBuffer();
        StringBuffer where  = new StringBuffer();
        
        select.append("select pay.C_PAYMENT_ID," + //1
                " bp.name," + //2
                " pay.DOCUMENTNO," + //3
                " pay.DATETRX ," + //4
                " pay.ISRECEIPT, " + //5
                " pay.C_INVOICE_ID, " + //6
                " pay.CHECKNO," + //7
                " pay.PAYAMT," + //8
                " pay.DOCSTATUS," + //9
                " pay.C_ORDER_ID, " + //10
        " bp.C_BPARTNER_ID "); //11
        
        from.append("from C_PAYMENT pay,C_BPARTNER bp");
        
        where.append(" where pay.C_BPARTNER_ID=bp.C_BPARTNER_ID " +
                " and pay.AD_CLIENT_ID = " + Env.getAD_Client_ID(ctx)+
                " and pay.AD_ORG_ID = "+ Env.getAD_Org_ID(ctx));
        
        if(docStatus!=null)
        {
            where.append(" and pay.docstatus = '" + docStatus + "'");		    	    
        }
        
        if (partnerId!= null)
        {
            where.append(" and bp.c_bpartner_id = " + partnerId);
        }
        
        if (month!= null) 
        {
            where.append(" and to_char(pay.created, 'mm') = " + month);
        }	    	
        
        if (year!= null) 
        {
            where.append(" and to_char(pay.created, 'yyyy') = " + year);
        }
        
        String sql = "" + select + from + where;
        System.out.println("Query for payment history :" + sql);
        
        PreparedStatement pstmt = DB.prepareStatement(sql,null);
        ResultSet rs = null;
        PaymentHistoryBean bean;
        ArrayList<PaymentHistoryBean> list = new ArrayList<PaymentHistoryBean>();
        
		try
		{
			rs = pstmt.executeQuery();

	        while(rs.next())
	        {
	            bean= new PaymentHistoryBean();
	            bean.setPaymentId(Integer.valueOf(rs.getInt(1)));
	            bean.setPartnerName(rs.getString(2));
	            bean.setDocumentNo(rs.getString(3));
	            bean.setDateTrx(rs.getTimestamp(4));
	            bean.setIsReceipt(rs.getString(5));
	            bean.setInvoiceId(Integer.valueOf(rs.getInt(6)));
	            bean.setCheckNo(rs.getString(7));
	            bean.setPayAmt(Integer.valueOf(rs.getInt(8)));
	            bean.setDocStatus(UDIMap.docStatusMap.get(rs.getString(9)));
	            bean.setDocStatusCode(rs.getString(9));
	            bean.setOrderId(Integer.valueOf(rs.getInt(10)));
	            bean.setPartnerId(Integer.valueOf(rs.getInt(11)));
	            list.add(bean);
	        }
			
	        rs.close();
			
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
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
        
        return list;
    }

    public static MPayment generatePayments(Properties ctx, MInvoice invoice) throws OperationException
    {
        //the normal flow for payment should be that 
        //first customer creates an AP Payment
        //completes it
        //this generates a counter(AR payment) document on HSAF side
        
        //however we assume that the customer has paid for the goods
        //and generate his AP payment ourselves
        
        //get counter(vendor invoice) from customer Invoice
        
        MInvoice vendorInvoice = InvoiceManager.getCounterInvoice(ctx, invoice);
        Properties newCtx = (Properties) ctx.clone();
        newCtx.setProperty(UdiConstants.ORG_ID_CTX_PARAM, "" + vendorInvoice.getAD_Org_ID());
        
        //appayment is linked to vendor invoice
        MPayment apPayment = createAPPayment(newCtx, vendorInvoice);
        
        //ar receipt is linked to customer invoice
        MPayment completedAPPayment = completeAPPayment(newCtx, apPayment);
        
        //get generated AR receipt
        int arReceiptIds[] = MPayment.getAllIDs(MPayment.Table_Name, "ad_client_id=" + Env.getAD_Client_ID(ctx) + " and ref_payment_id=" + completedAPPayment.get_ID(),invoice.get_TrxName());
        
        if (arReceiptIds.length == 0)
            throw new RuntimeException("Expected an AR Receipt for AP Payment---" + apPayment.get_ID());
        
        PaymentManager.completePayment(ctx, new MPayment(ctx,arReceiptIds[0],invoice.get_TrxName()));
        return completedAPPayment;
    }
    
    //retrieve AR receipt
    public static MPayment getCounterPayment(Properties ctx, MPayment apPayment)
    {
        if (!apPayment.getDocStatus().equals(DocumentEngine.STATUS_Completed))
            throw new RuntimeException("Cannot get counter document of payment which has not been completed");
        
        int arReceipts[] = MPayment.getAllIDs(MPayment.Table_Name, "ad_client_id=" + Env.getAD_Client_ID(ctx) + " and ref_payment_id=" + apPayment.get_ID(), apPayment.get_TrxName());
        
        if (arReceipts.length == 0)
            throw new RuntimeException("counter payment document not found for payment--" + apPayment.get_ID());
        
        MPayment receipt = new MPayment(ctx, arReceipts[0], apPayment.get_TrxName());
        
        return receipt;
    }
    public static WebDocumentBean getWebPaymentBean(Properties ctx, MPayment payment) throws OperationException, DataException
    {
        if (payment == null)
            throw new OperationException("Invalid operation payment is null");
        
        if (payment.get_ID() == 0)
            throw new OperationException("You have deleted this payment. You cannot view this payment.");
        
        WebDocumentBean bean = new WebDocumentBean();
        
        bean.setPaymentId(Integer.valueOf(payment.get_ID()));
        
        int currencyId = payment.getC_Currency_ID();
        MCurrency currency = new MCurrency(ctx,currencyId,null);
        bean.setCurrencySymbole(currency.getCurSymbol());
        
        MOrg myOrg = OrganisationManager.getMyOrg(ctx);
        MBPartner me = new MBPartner(ctx, myOrg.getLinkedC_BPartner_ID(null), null);
        bean.setMe(me);
        //MOrg orderOrg = new MOrg(ctx, payment.getAD_Org_ID(), null);
       /* 
        if (orderOrg.getLinkedC_BPartner_ID() != myOrg.getLinkedC_BPartner_ID())
            throw new DocumentDoesNotBelongToYouException("This payment does not belong to you. You do not have access to it.");        
        */
        
		MBPartnerLocation meLocation[] =  MBPartnerLocation.getForBPartner(ctx,me.get_ID());
		if (meLocation.length  ==0)
			throw new OperationException("No location has been set for your organisation. Please ask your administrator to set one for you");
		
		MLocation location = new MLocation(ctx, meLocation[0].getC_Location_ID(), null);
		
		if (location.get_ID() == 0)
			throw new OperationException("You must have a location set for your business partner, Please ask your administrator to set one for you");
        
        bean.setMeLocation(location);
        
        MBPartner you = new MBPartner(ctx, payment.getC_BPartner_ID(), null);
        
		MBPartnerLocation youBPLocation[] = MBPartnerLocation.getForBPartner(ctx, you.get_ID());
		
		if (youBPLocation.length  ==0)
			throw new OperationException("No location has been set for the dealer organisation. Please ask your administrator to set one for you");
        
		MLocation youLocation = new MLocation(ctx, youBPLocation[0].getC_Location_ID(), null);
		
		bean.setYou(you);
		bean.setYoubpLocation(youBPLocation[0]);
        bean.setYouLocation(youLocation);
        
        WebDocumentHeaderBean headerBean = createWebDocumentHeader(ctx, payment.getAD_Org_ID(),payment.getC_BPartner_ID(), payment.getDocStatus(), payment.isReceipt(),payment.getTenderType());
        headerBean.setDocumentHeader(getDocumentHeader(payment));

        String tenderTypeName = MRefList.getListName(ctx, MPayment.TENDERTYPE_AD_Reference_ID,payment.getTenderType());
        
        if(payment.getCheckNo()!=null)
            {
                tenderTypeName=tenderTypeName+"  "+payment.getCheckNo();
            }
        headerBean.setPaymentType(tenderTypeName);
        
        bean.setHeaderBean(headerBean);
        
        bean.setPayment(payment);
        
        MInvoice invoice = new MInvoice(ctx, payment.getC_Invoice_ID(), null);
        
        //order linked to invoice
        MOrder order = new MOrder(ctx, invoice.getC_Order_ID(), null);
        
        bean.setOrder(order);
        bean.setInvoice(invoice);
        
        CommandBean cmdBean = getCustomerPaymentWebCommands(payment);
        bean.setSimpleCommand(cmdBean.getSimpleCommand());
        bean.setComplexCommand(cmdBean.getComplexCommand());
        
        return bean;
    }
    
    public static String getDocumentHeader(MPayment payment)
    {
        if (payment.isReceipt())
            return PAYMENT_RECEIVED;
        
        return PAYMENT_MADE;
    }
    
 
    
//    public static void paidAndShip(Properties ctx, MInvoice invoice) throws OperationException
//    {
//        MPayment apPayment = generatePayments(ctx, invoice);
//        MessageManager.sendPaymentMessage(apPayment,UDIDocActionValues.CONFIRM);
//        MinOutManager.generateMinOut(ctx, invoice);
//        
//    }
    
   
    
    private static CommandBean getCustomerPaymentWebCommands(MPayment payment) throws OperationException, DataException
    {
        
        //if (!payment.isReceipt())
         //   throw new OperationException("Invoice should be a AR Receipt");
        
        
        String[] simpleCommands = null;
        String[] complexCommands = null;  
        
        CommandBean bean = new CommandBean();
        
        if (payment.getDocStatus().equals(DocumentEngine.STATUS_Drafted))
        {
            simpleCommands = new String[]{Constants.PAYMENT_ACTION_COMPLETE};
            complexCommands = new String[]{Constants.PAYMENT_ACTION_CANCEL};
        }
        
        if (payment.getDocStatus().equals(DocumentEngine.STATUS_Voided))
        {
            complexCommands = new String[]{Constants.INVOICE_ACTION_DELETE};
        }
        
        if (payment.getDocStatus().equals(DocumentEngine.STATUS_Completed))
        {
            
            simpleCommands = new String[]{Constants.MINOUT_ACTION_SHIP};
        }
        
        
        bean.setSimpleCommand(simpleCommands);
        bean.setComplexCommand(complexCommands);
        return bean;
    }
    
    
    public static MPayment loadPayment(Properties ctx, int paymentId, String trxName) throws OperationException
    {
    	MPayment payment = new MPayment(ctx, paymentId, trxName);
    	if(payment.get_ID() == 0)
    		throw new OperationException("Could not load payment with id: " + paymentId);
    	
    	return payment;
    }
    
    public static void printInvoice(Properties ctx,MPayment payment) throws OperationException
    {
        PrintManager.print(ctx,ReportEngine.REMITTANCE,payment.get_ID());
    }
    
    
    public static MPayment createPayment(Properties ctx,PaymentBean bean,String trxName) throws OperationException
    {

    	if(bean.getBpartnerId()==null)
    	{
        		throw new BPartnerNotFoundException("customer not found");
    	}
        
        int bankAccIds[] = MBankAccount.getAllIDs(MBankAccount.Table_Name, " ad_org_id=" + Env.getAD_Org_ID(ctx) + " and ad_client_id=" + Env.getAD_Client_ID(ctx) + " and isDefault='Y'", null);
        if (bankAccIds.length == 0)
            throw new OperationException("No default bank account found for org");
        MBankAccount orgBankAc = new MBankAccount(ctx, bankAccIds[0],null);
        MDocType[] docTypes = MDocType.getOfDocBaseType(ctx, MDocType.DOCBASETYPE_ARReceipt);
        if (docTypes.length == 0)
            throw new OperationException("not document type found for payment");
        MPayment payment = new MPayment(ctx,0,trxName);
        payment.setC_DocType_ID(docTypes[0].get_ID());
        payment.setC_Currency_ID(POSTerminalManager.getCurrencyOfDefaultCashBook(ctx).get_ID());
        payment.setC_BankAccount_ID(bankAccIds[0]);
        payment.setC_BPartner_ID(bean.getBpartnerId());
        payment.setAccountNo(orgBankAc.getAccountNo());
        
        if(bean.getTrxType().equalsIgnoreCase("Card"))
        payment.setTenderType(MPayment.TENDERTYPE_CreditCard);
        
        else if(bean.getTrxType().equalsIgnoreCase("Cheque"))
            payment.setTenderType(MPayment.TENDERTYPE_Check);
        
        else throw new OperationException("invalid tender type");
        payment.setIsPrepayment(false);

        payment.setPayAmt(bean.getAmount());
        if(bean.getCreditCardNumber()!=null)
            payment.setCreditCardNumber(bean.getCreditCardNumber());
        if(bean.getChequeNo()!=null)
            payment.setCheckNo(bean.getChequeNo());
        payment.setIsReceipt(true);
        payment.setDescription("(AR Receipt) Payment Received");
        
        int posId=Env.getContextAsInt(ctx, UdiConstants.POS_ID);
        payment.setDescription(""+posId); //To get the proper pos info posId is saved into description
      
        UDIMPayment udiPayment = new UDIMPayment(payment);
        udiPayment.save();
      
        udiPayment.processIt(DocumentEngine.ACTION_Complete);
        
        return payment;
    }
}
