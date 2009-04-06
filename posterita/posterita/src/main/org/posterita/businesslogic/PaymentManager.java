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

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
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
import org.posterita.beans.PaymentDetailsBean;
import org.posterita.beans.PaymentHistoryBean;
import org.posterita.beans.WebDocumentBean;
import org.posterita.beans.WebDocumentHeaderBean;
import org.posterita.core.UDIMap;
import org.posterita.exceptions.BPartnerNotFoundException;
import org.posterita.exceptions.DataException;
import org.posterita.exceptions.OperationException;
import org.posterita.lib.UdiConstants;
import org.posterita.util.PoManager;

public class PaymentManager extends AbstractDocumentManager
{
    private static final String PAYMENT_RECEIVED = "Payment Received";
    private static final String PAYMENT_MADE = "Payment Made";
    
    
    /**
     * Creates an AP Payment with the open amount on the invoice
     * @param ctx Context
     * @param invoice Vendor Invoice
     * @param trxName Transaction
     * @return AP Payment
     * @throws OperationException If Payment cannot be created
     */
    public static MPayment createAPPayment(Properties ctx, MInvoice invoice, String trxName) throws OperationException
    {
        String tenderType = getTenderType(invoice.getPaymentRule());
        return createPayment(ctx, invoice, MDocType.DOCBASETYPE_APPayment, tenderType, invoice.getOpenAmt(), trxName);
    }
    
    /**
     * 
     * @param ctx
     * @param invoice
     * @return
     * @throws OperationException
     */
    public static MPayment createARReceipt(Properties ctx, MInvoice invoice, String trxName) throws OperationException
    {
        String tenderType = getTenderType(invoice.getPaymentRule());
        return createPayment(ctx, invoice, MDocType.DOCBASETYPE_ARReceipt, tenderType, invoice.getOpenAmt(), trxName);
    }
    
    /**
     * Create AR Receipt
     * @param ctx
     * @param invoice
     * @param amount
     * @param trxName
     * @return
     * @throws OperationException
     */
    public static MPayment createARReceipt(Properties ctx, MInvoice invoice, BigDecimal amount, String trxName) throws OperationException
    {
        String tenderType = getTenderType(invoice.getPaymentRule());
        return createPayment(ctx, invoice, MDocType.DOCBASETYPE_ARReceipt, tenderType, amount, invoice.get_TrxName());
    }
    
    /**
     * Create AR Receipt
     * @param ctx Context
     * @param invoice Invoice
     * @param amount Amount
     * @param tenderType Tender Type
     * @param trxName Transaction
     * @return Payment Document
     * @throws OperationException
     */
    public static MPayment createARReceipt(Properties ctx, MInvoice invoice, String tenderType, BigDecimal amount, String trxName) throws OperationException
    {
        return createPayment(ctx, invoice, MDocType.DOCBASETYPE_ARReceipt, tenderType, amount, invoice.get_TrxName());
    }
        
    /**
     * Creates Payment based on Document Base Type
     * @param ctx Context
     * @param invoice Invoice
     * @param docBaseType Document Base Type
     * @param trxName Transaction
     * @return Payment Document
     * @throws OperationException If Payment cannot be created
     */
    public static MPayment createPayment(Properties ctx, MInvoice invoice, String docBaseType, String trxName) throws OperationException
    {
        String tenderType = getTenderType(invoice.getPaymentRule());
        return createPayment(ctx, invoice, docBaseType, tenderType, invoice.getOpenAmt(), invoice.get_TrxName());
    }
    
    /**
     * Creates a new payment
     * @param ctx Context
     * @param invoice Invoice
     * @param docBaseType Document Base Type
     * @param tenderType Tender Type
     * @param amount Payment Amount
     * @param trxName Transaction
     * @return Payment
     * @throws OperationException if payment cannot be created
     * @throws IllegalArgumentException if mandatory values are missing (Tender Type)
     *                                  if Document Base Type is not valid
     */
    public static MPayment createPayment(Properties ctx, MInvoice invoice, String docBaseType, String tenderType, BigDecimal amount, String trxName) throws OperationException
    {
        if (tenderType == null)
        {
            throw new IllegalArgumentException("Tender type is invalid");
        }
        if (docBaseType == null)
        {
            throw new IllegalArgumentException("Document Base Type is invalid");
        }
        
        MPayment payment = new MPayment(ctx,0, trxName);
        payment.setC_BPartner_ID(invoice.getC_BPartner_ID());
        payment.setC_Currency_ID(invoice.getC_Currency_ID());
        payment.setC_Order_ID(invoice.getC_Order_ID());
        
        // TODO Should get correct value for Document Type for payment
        MDocType[] docTypes = MDocType.getOfDocBaseType(ctx, docBaseType);
        
        if (docTypes.length == 0)
            throw new OperationException("not document type found for payment");
        
        payment.setC_Invoice_ID(invoice.get_ID());
        payment.setC_DocType_ID(docTypes[0].get_ID());
        payment.setTenderType(tenderType);
        payment.setPayAmt(amount);
        
        if (MDocType.DOCBASETYPE_APPayment.equals(docBaseType))
        {
            payment.setIsReceipt(false);
            payment.setDescription("(AP Payment) Payment Made");
        }
        else if (MDocType.DOCBASETYPE_ARReceipt.equals(docBaseType))
        {
            payment.setIsReceipt(true);
            payment.setDescription("(AR Receipt) Payment Received");
        }
        else
        {
            throw new IllegalArgumentException("Invalid Document Base Type, DocBaseType: " + docBaseType);
        }
        
        if(MPayment.TENDERTYPE_Cash.equals(tenderType))
        {
        	//cash payment
        	int cashbookId = POSTerminalManager.getCashBookId(ctx);
        	payment.setC_CashBook_ID(cashbookId);
        }
        else
        {
        	int bankAccountId = POSTerminalManager.getBankAccountId(ctx, payment.getTenderType());
        	payment.setC_BankAccount_ID(bankAccountId);
        }
        
        
        PoManager.save(payment);
        
        // setting the order automatically sets the payment as prepayment though invoice present
        // @see MPayment.beforeSave()
        payment.setIsPrepayment(false);
        PoManager.save(payment);
                              
        invoice.setC_Payment_ID(payment.get_ID());
        PoManager.save(invoice);
        
        return payment;
    }
 
    public static MPayment createPaymentForCreditOrder(Properties ctx, MInvoice invoice, OpenItemBean bean, String trxName) throws OperationException
    {
        MPayment payment = new MPayment(ctx,0, trxName);
        payment.setC_BPartner_ID(invoice.getC_BPartner_ID());
        payment.setC_Currency_ID(invoice.getC_Currency_ID());
        payment.setC_Order_ID(invoice.getC_Order_ID());
        payment.setIsPrepayment(false);
        payment.setDateTrx(new Timestamp(System.currentTimeMillis()));
        payment.setDateAcct(new Timestamp(System.currentTimeMillis()));
        
        MDocType[] docTypes = null;
        
        if(invoice.isSOTrx())
        	docTypes = MDocType.getOfDocBaseType(ctx, MDocType.DOCBASETYPE_ARReceipt);
        else
        	docTypes = MDocType.getOfDocBaseType(ctx, MDocType.DOCBASETYPE_APPayment);
        
        if (docTypes.length == 0)
            throw new OperationException("not document type found for payment");
        
        payment.setC_Invoice_ID(invoice.get_ID());
        payment.setC_DocType_ID(docTypes[0].get_ID());
        payment.setTenderType(bean.getTenderType());
        
        if(MPayment.TENDERTYPE_Cash.equalsIgnoreCase(bean.getTenderType()))
        {
        	int cashbookId = POSTerminalManager.getCashBookId(ctx);
        	payment.setC_CashBook_ID(cashbookId);
        }
        else
        {
        	int bankAccountId = POSTerminalManager.getBankAccountId(ctx, payment.getTenderType());
            payment.setC_BankAccount_ID(bankAccountId);
        }
        
        if(bean.getTenderType().equalsIgnoreCase(MPayment.TENDERTYPE_Check) && bean.getChequeNo()!=null)
        {
            payment.setCheckNo(bean.getChequeNo());
        }
        else if(bean.getTenderType().equalsIgnoreCase(MPayment.TENDERTYPE_CreditCard) && bean.getCreditCardNumber()!=null)
        {
            payment.setCreditCardNumber(bean.getCreditCardNumber());
        }

                
        payment.setPayAmt(bean.getPaymentAmt());
        payment.setIsReceipt(invoice.isSOTrx());
        
        PoManager.save(payment);
        invoice.setC_Payment_ID(payment.get_ID());
        PoManager.save(invoice);
        
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
        PoManager.save(payment);
        return payment;
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
       
        PoManager.processIt(payment, DocumentEngine.ACTION_Complete);
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
        
        PoManager.save(arReceipt);
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
        MPayment apPayment = createAPPayment(newCtx, vendorInvoice, vendorInvoice.get_TrxName());
        
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
    	
    	String docBaseType = MDocType.DOCBASETYPE_APPayment;
    	boolean isReceipt = false;
    	
    	if(bean.getIsSoTrx())
    	{
    		docBaseType = MDocType.DOCBASETYPE_ARReceipt;
    		isReceipt = true;
    	}
       
        MDocType[] docTypes = MDocType.getOfDocBaseType(ctx, docBaseType);
        if (docTypes.length == 0)
        {
            throw new OperationException("not document type found for payment");
        }
        
        MPayment payment = new MPayment(ctx,0,trxName);
        payment.setC_DocType_ID(docTypes[0].get_ID());
        payment.setC_Currency_ID(POSTerminalManager.getCurrencyOfTerminalCashBook(ctx).get_ID());
        payment.setC_BPartner_ID(bean.getBpartnerId());
        
        // @ashley TODO Fix inconsistent Transaction type in the codes 
        if(bean.getTrxType().equalsIgnoreCase("Card"))
        {
            payment.setTenderType(MPayment.TENDERTYPE_CreditCard);
        }
        else if(bean.getTrxType().equalsIgnoreCase("Cheque"))
        {
            payment.setTenderType(MPayment.TENDERTYPE_Check);
        }
        else if(bean.getTrxType().equalsIgnoreCase("Cash"))
        {
        	payment.setTenderType(MPayment.TENDERTYPE_Cash);
        }
        else
        {
            throw new OperationException("invalid tender type");
        }
        
        
        if(MPayment.TENDERTYPE_Cash.equals(payment.getTenderType()))
        {
        	int cashbookId = POSTerminalManager.getCashBookId(ctx);
        	payment.setC_CashBook_ID(cashbookId);
        }
        else
        {
        	int bankAccountId = POSTerminalManager.getBankAccountId(ctx, payment.getTenderType());
            payment.setC_BankAccount_ID(bankAccountId);
        }
        

        payment.setIsPrepayment(false);

        payment.setPayAmt(bean.getAmount());
        if(bean.getCreditCardNumber()!=null)
            payment.setCreditCardNumber(bean.getCreditCardNumber());
        if(bean.getChequeNo()!=null)
            payment.setCheckNo(bean.getChequeNo());
        
        payment.setIsReceipt(isReceipt);
        
        if(isReceipt)
        	payment.setDescription("(AR Receipt) Payment Received");
        else
        	payment.setDescription("(AP Payment) Payment Made");
        
        int posId = POSTerminalManager.getTerminalId(ctx);
        payment.setDescription(""+posId); //To get the proper pos info posId is saved into description
      
        PoManager.save(payment);
      
        PoManager.processIt(payment, DocumentEngine.ACTION_Complete);
        
        return payment;
    }
    
    /**
     * Returns the tender type for a payment rule
     * @param paymentRule Order/Invoice Payment Rule
     * @return Tender Type
     */
    public static String getTenderType(String paymentRule)
    {
        if(MInvoice.PAYMENTRULE_CreditCard.equals(paymentRule))
        {
            return MPayment.TENDERTYPE_CreditCard;
        }
        else if(MInvoice.PAYMENTRULE_DirectDebit.equals(paymentRule))
        {
            return MPayment.TENDERTYPE_DirectDebit;
        }
        else if(MInvoice.PAYMENTRULE_DirectDeposit.equals(paymentRule))
        {
            return MPayment.TENDERTYPE_DirectDeposit;
        }
        else if (MInvoice.PAYMENTRULE_Check.equals(paymentRule))
        {
            return MPayment.TENDERTYPE_Check;
        }
        else if (MInvoice.PAYMENTRULE_OnCredit.equals(paymentRule) 
                || MInvoice.PAYMENTRULE_Mixed.equals(paymentRule))
        {
            // Returns Check by default for Credit and Mixed payment rules.
            return MPayment.TENDERTYPE_Check;
        }
        else
        {
            throw new IllegalArgumentException("No tender type is defined for the payment rule: " + paymentRule);
        }
    }
        	
	/**
	 * @param ctx Context
	 * @param currencyId	CurrencyId whose roundOff Factor is required
	 * @param total	Total price
	 * @param trxName	Transaction Name	
	 * @return	writeOff Amount
	 */
	public static BigDecimal computeWriteOff(Properties ctx, Integer currencyId, BigDecimal total, String trxName)
	{
		BigDecimal writeoffAmt = Env.ZERO;
		BigDecimal roundOffFactor;
		
		MCurrency currency = new MCurrency(ctx, currencyId, trxName);
		roundOffFactor = currency.getRoundOffFactor();
		
		//Calculate write off amount
		if(roundOffFactor != null && roundOffFactor.doubleValue() != 0.0)
		{
			writeoffAmt = total.remainder(roundOffFactor);		
		}
			
		return writeoffAmt;
	}
		
	public static PaymentDetailsBean getPaymentDetails(Properties ctx, int c_order_id, String trxName) throws OperationException
	{
		PaymentDetailsBean bean = new PaymentDetailsBean();
		
		MOrder order = new MOrder(ctx, c_order_id, trxName);
		
		bean.setTenderedAmt(order.getAmountTendered());
		bean.setRefundedAmt(order.getAmountRefunded());
		
		String sql = " SELECT SUM(AMOUNT) AS AMOUNT, SUM(WRITEOFF) AS WRITEOFF, SUM(DISCOUNT) AS DISCOUNT FROM (" +
				" SELECT SUM(AMOUNT) AS AMOUNT, SUM(WRITEOFFAMT) AS WRITEOFF, SUM(DISCOUNTAMT) AS DISCOUNT FROM C_CASHLINE,C_INVOICE " +
				" WHERE C_INVOICE.C_INVOICE_ID = C_CASHLINE.C_INVOICE_ID " +
				" AND C_INVOICE.C_ORDER_ID = ? " +
				" UNION " +
				" SELECT SUM(PAYAMT) AS AMOUNT, SUM(WRITEOFFAMT) AS WRITEOFF, SUM(DISCOUNTAMT) AS DISCOUNT FROM C_PAYMENT,C_INVOICE " +
				" WHERE C_INVOICE.C_INVOICE_ID = C_PAYMENT.C_INVOICE_ID " +
				" AND C_INVOICE.C_ORDER_ID = ? " +
				" ) PAYMENT";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try 
		{
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1,	c_order_id);
			pstmt.setInt(2,	c_order_id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				BigDecimal amount = rs.getBigDecimal(1);
				BigDecimal writeOff = rs.getBigDecimal(2);
				BigDecimal discount = rs.getBigDecimal(3);
				
				amount = amount == null? Env.ZERO : amount;
				writeOff = writeOff == null? Env.ZERO : writeOff;
				discount = discount == null? Env.ZERO : discount;

				bean.setWriteOffAmt(writeOff);
				bean.setDiscountAmt(discount);
				bean.setPayAmt(amount.setScale(2, BigDecimal.ROUND_UP));
				
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
		return bean;
	}
}
