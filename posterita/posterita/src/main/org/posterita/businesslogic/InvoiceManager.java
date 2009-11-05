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
 *
 * Created on 05-Jul-2005 by alok
 *
 */
package org.posterita.businesslogic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MCharge;
import org.compiere.model.MClient;
import org.compiere.model.MCurrency;
import org.compiere.model.MDocType;
import org.compiere.model.MInOut;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MLocation;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MOrg;
import org.compiere.model.MPInstance;
import org.compiere.model.MPInstancePara;
import org.compiere.model.MProcess;
import org.compiere.model.MProduct;
import org.compiere.model.MTax;
import org.compiere.print.ReportEngine;
import org.compiere.process.DocumentEngine;
import org.compiere.process.InvoiceGenerate;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.posterita.Constants;
import org.posterita.beans.AttributeValuesPair;
import org.posterita.beans.CommandBean;
import org.posterita.beans.InvoiceHistoryBean;
import org.posterita.beans.WebDocumentBean;
import org.posterita.beans.WebDocumentHeaderBean;
import org.posterita.beans.WebInvoiceLineBean;
import org.posterita.beans.WebOrderLineBean;
import org.posterita.businesslogic.administration.ProductManager;
import org.posterita.businesslogic.core.ChargeManager;
import org.posterita.core.UDIMap;
import org.posterita.exceptions.DataException;
import org.posterita.exceptions.NoOrderLineSelectedException;
import org.posterita.exceptions.OperationException;
import org.posterita.formatter.PrintFormatter;
import org.posterita.order.UDIOrderTypes;
import org.posterita.util.PoManager;

public class InvoiceManager extends AbstractDocumentManager
{
   
    private static final String CUSTOMER_INVOICE = "Customer Invoice";
    private static final String VENDOR_INVOICE = "Vendor Invoice";
    
    public static MInvoice createCustomerInvoice(Properties ctx, int salesOrderId, String trxName) throws OperationException
    {
        MOrder so = new MOrder(ctx, salesOrderId, trxName);
        
        MOrderLine lines[] = so.getLines();
        
        Integer soLinesIds[] = new Integer[lines.length];
        
        if (soLinesIds.length == 0)
            throw new NoOrderLineSelectedException("No order line selected");
        
        MInvoice returnInvoice = null;
        
        for (int i = 0; i<lines.length; i++)
        {
          	returnInvoice = createCustomerInvoice(ctx, salesOrderId, Integer.valueOf(lines[i].getC_OrderLine_ID()), trxName);
        }
        
        return returnInvoice;
    }

    public static MInvoice[] createAndCompleteInvoices(Properties ctx, int salesOrderId, String trxName) throws OperationException
    {  
    	
    	int[] orderlineIds = MOrderLine.getAllIDs(MOrderLine.Table_Name, "c_order_id=" + salesOrderId, trxName);
    	Integer[] salesOrderLineIdsInteger = new Integer[orderlineIds.length];
    	
		for (int i = 0; i < orderlineIds.length; i++) 
		{
			salesOrderLineIdsInteger[i] = Integer.valueOf(orderlineIds[i]);
		}
    	
    	return createAndCompleteInvoices(ctx, salesOrderId, salesOrderLineIdsInteger, trxName);
    }
    
    public static MInvoice[] createAndCompleteInvoices(Properties ctx, int salesOrderId,Integer []salesOrderLineIds, String trxName) throws OperationException
    {  
    	
    	MInvoice[] generatedInvoices = new MInvoice[salesOrderLineIds.length];
    	
    	for (int i = 0; i < salesOrderLineIds.length; i++) 
    	{
    		
    		boolean approved=true;
    		
    		if (approved)
    		{
    	
	    	    MInvoice draftCustomerInvoice = createCustomerInvoice(ctx, salesOrderId, salesOrderLineIds[i], trxName);	
	
	            MInvoice completedCustomerInvoice = InvoiceManager.completeInvoice(ctx, draftCustomerInvoice.get_ID(), trxName);
	            MInvoice counterInvoice = getCounterInvoice(ctx, completedCustomerInvoice.get_ID(), trxName);
	            //complete counter invoice also
	            InvoiceManager.completeInvoice(ctx, counterInvoice.get_ID(), trxName);
	            
	            
	            //completedCustomerInvoice.getC_DocType_ID()
	            
	            generatedInvoices[i] = completedCustomerInvoice;
	            
	            
	            MOrder salesOrder = new MOrder(ctx, salesOrderId, trxName);
	            
	            if (salesOrder.getOrderType().equals(UDIOrderTypes.WEBSTORE_ORDER.getOrderType()))
	            	return generatedInvoices;
	            
	            
    		}
    	}
        
        return generatedInvoices;
    }
    
    
    public static MInvoice createCustomerInvoice(Properties ctx, int salesOrderId,Integer[] salesOrderLineIds, String trxName) throws OperationException
    {
    	MInvoice invoice = null;
    	
    	for (int i = 0; i < salesOrderLineIds.length; i++) 
    	{
    		invoice = createCustomerInvoice(ctx, salesOrderId, salesOrderLineIds[i], trxName);
		}
    	
    	return invoice;
    }
    
    public static MInvoice createCustomerInvoice(Properties ctx, int salesOrderId,Integer salesOrderLineId, String trxName) throws OperationException
    {
        
       //vendor creates customer invoice
        //customer creates vendor Invoice
        //first we have to create a customer invoice from the sales order
        //complete the customer invoice
        //then complete customer invoice--this will generate a vendor invoice which is match
        // to the Purchase Order
        
        MOrder salesOrder = new MOrder(ctx,salesOrderId,trxName);
        MOrderLine orderLine = new MOrderLine(ctx, salesOrderLineId.intValue(), trxName);
        
        MInvoice invoice = null;

        int[] invoiceLineIds = MInvoiceLine.getAllIDs(MInvoiceLine.Table_Name, "c_orderline_id=" + salesOrderLineId, trxName);
        
        if (invoiceLineIds.length > 0)
        {
        	throw new OperationException("Orderline already invoiced!");
        }
        
        
        try
        {
            boolean isSOTrx = salesOrder.isSOTrx();
            
            if (!isSOTrx)
                throw new OperationException("Customer Invoice should be created from sales Order");
            
            MDocType [] docType;
            
            if (salesOrder.getOrderType().equals(UDIOrderTypes.CUSTOMER_RETURN_ORDER.getOrderType()))
            	docType = MDocType.getOfDocBaseType(ctx,MDocType.DOCBASETYPE_ARCreditMemo);
            else	
            	docType = MDocType.getOfDocBaseType(ctx,MDocType.DOCBASETYPE_ARInvoice); 
            
            Env.setContext(ctx, "#M_Warehouse_ID", salesOrder.getM_Warehouse_ID());
            invoice = new MInvoice(salesOrder,docType[0].get_ID(),salesOrder.getDateOrdered());
            
           
            invoice.setTotalLines(orderLine.getLineNetAmt());
            invoice.setGrandTotal(orderLine.getLineNetAmt());
            invoice.setIsSOTrx(true);
            invoice.setC_Order_ID(salesOrder.get_ID());
            
            invoice.setPaymentRule(salesOrder.getPaymentRule());
            invoice.setC_DocTypeTarget_ID(docType[0].get_ID());
            invoice.setC_BPartner_ID(salesOrder.getC_BPartner_ID());
            invoice.setC_Currency_ID(salesOrder.getC_Currency_ID());
            invoice.setIsTaxIncluded(false);
            
            PoManager.save(invoice);
            
            MTax tax;
                
            	MInvoiceLine invoiceLine = new MInvoiceLine(invoice);
                invoiceLine.setOrderLine(orderLine);
                invoiceLine.setC_OrderLine_ID(orderLine.get_ID());
                invoiceLine.setQtyEntered(orderLine.getQtyEntered());
                invoiceLine.setM_AttributeSetInstance_ID(orderLine.getM_AttributeSetInstance_ID());
                if(orderLine.getQtyInvoiced().equals (new BigDecimal(1)))
                {	
                    invoiceLine.setQty(0);
                    invoiceLine.setQtyEntered(new BigDecimal(0));
                    invoiceLine.setQtyInvoiced(new BigDecimal(0));
                }
                
                else 
                {
                    tax = new MTax(ctx,orderLine.getC_Tax_ID(),null);
                    invoiceLine.setQty(orderLine.getQtyEntered());
                    invoiceLine.setQtyEntered(orderLine.getQtyEntered());
                    invoiceLine.setQtyInvoiced(orderLine.getQtyEntered());
                    invoiceLine.setTaxAmt(tax.calculateTax(orderLine.getLineNetAmt(),false,3));
                    invoiceLine.setLineNetAmt(orderLine.getLineNetAmt());
                    invoiceLine.setLineTotalAmt(new BigDecimal(orderLine.getLineNetAmt().intValue()+tax.calculateTax(orderLine.getLineNetAmt(),false,3).intValue()));
                }
                PoManager.save(invoiceLine);
        }
        catch(OperationException e)
        {
            throw e;
        }
        return invoice;
    }
    
    public static MInvoice createVendorInvoice(Properties ctx, int purchaseOrderId,String trxName) throws OperationException
    {
        MOrder po = new MOrder(ctx, purchaseOrderId, trxName);
        
        MOrderLine lines[] = po.getLines();
        
        Integer poLinesIds[] = new Integer[lines.length];
        
        for (int i = 0; i<lines.length; i++)
        {
          poLinesIds[i] = Integer.valueOf(lines[i].getC_OrderLine_ID()); 
        }
        
        return createVendorInvoice(ctx, purchaseOrderId, poLinesIds,trxName);
    }
    
    public static MInvoice createVendorInvoice(Properties ctx, int purchaseOrderId,Integer[] purchaseOrderLineIds,String trxName) throws OperationException
    {
        
        if (purchaseOrderLineIds == null)
            throw new NoOrderLineSelectedException("No order line selected");
            
     
        
        //vendor creates customer invoice
        //customer creates vendor Invoice
        //first we have to create a customer invoice from the sales order
        //complete the customer invoice
        //then complete customer invoice--this will generate a vendor invoice which is match
        // to the Purchase Order
        
        MOrder purchaseOrder = new MOrder(ctx,purchaseOrderId,trxName);
        MInvoice invoice = null;
        
        MDocType [] docType=null;
        if(purchaseOrder.getOrderType().equals(UDIOrderTypes.POS_GOODS_RETURN_NOTE.getOrderType())) 
            docType  = MDocType.getOfDocBaseType(ctx,MDocType.DOCBASETYPE_APCreditMemo); 
        else
            docType  = MDocType.getOfDocBaseType(ctx,MDocType.DOCBASETYPE_APInvoice); 
            
            Env.setContext(ctx, "#M_Warehouse_ID", purchaseOrder.getM_Warehouse_ID());
            invoice = new MInvoice(purchaseOrder,docType[0].get_ID(),purchaseOrder.getDateOrdered());
            
            invoice.setTotalLines(purchaseOrder.getTotalLines());
            invoice.setGrandTotal(purchaseOrder.getGrandTotal());
            invoice.setIsSOTrx(false);
            invoice.setC_Order_ID(purchaseOrder.get_ID());
            invoice.setPaymentRule(purchaseOrder.getPaymentRule());
            invoice.setC_BPartner_ID(purchaseOrder.getC_BPartner_ID());
            invoice.setC_DocTypeTarget_ID(docType[0].get_ID());
            invoice.setC_DocType_ID(docType[0].get_ID());
        
           
            invoice.setC_Currency_ID(purchaseOrder.getC_Currency_ID());
            invoice.set_TrxName(trxName);
            invoice.setIsTaxIncluded(false);
             
            PoManager.save(invoice);
            
            MOrderLine [] myOrderLines = new MOrderLine[purchaseOrderLineIds.length] ;
            for(int i=0;i<purchaseOrderLineIds.length;i++)
            { 
                myOrderLines[i] = new MOrderLine(ctx,purchaseOrderLineIds[i].intValue(),trxName);
            }
            
            MInvoiceLine[] invoiceLine = new MInvoiceLine[myOrderLines.length];
            
            MTax tax;
            for( int i=0;i < invoiceLine.length;i++)
            {
                
                invoiceLine[i] = new MInvoiceLine(invoice);
                invoiceLine[i].setOrderLine(myOrderLines[i]);
                invoiceLine[i].setC_OrderLine_ID(myOrderLines[i].get_ID());
                invoiceLine[i].setQtyEntered(myOrderLines[i].getQtyEntered());
                invoiceLine[i].setM_AttributeSetInstance_ID(myOrderLines[i].getM_AttributeSetInstance_ID());
                if(myOrderLines[i].getQtyInvoiced().equals (new BigDecimal(1)))
                {	
                    invoiceLine[i].setQty(0);
                    invoiceLine[i].setQtyEntered(new BigDecimal(0));
                    invoiceLine[i].setQtyInvoiced(new BigDecimal(0));
                }
                
                else 
                {
                    tax = new MTax(ctx,myOrderLines[i].getC_Tax_ID(),null);
                    invoiceLine[i].setQty(1);
                    invoiceLine[i].setQtyEntered(myOrderLines[i].getQtyEntered());
                    invoiceLine[i].setQtyInvoiced(myOrderLines[i].getQtyEntered());
                    invoiceLine[i].setTaxAmt(tax.calculateTax(myOrderLines[i].getLineNetAmt(),false,3));
                    invoiceLine[i].setLineNetAmt(myOrderLines[i].getLineNetAmt());
                    invoiceLine[i].setLineTotalAmt(new BigDecimal(myOrderLines[i].getLineNetAmt().intValue()+tax.calculateTax(myOrderLines[i].getLineNetAmt(),false,3).intValue()));
                }
                PoManager.save(invoiceLine[i]);
            }
            
        return invoice;
    }
    
    
    public static MInvoice completeInvoice(Properties ctx, MInvoice invoice) throws OperationException
    {
        if (invoice.getDocStatus().equals(DocumentEngine.STATUS_Completed))
            throw new OperationException("Cannot complete a payment which is already completed");
        
        if (invoice.getDocStatus().equals(DocumentEngine.STATUS_Voided))
            throw new OperationException("Cannot complete a payment which has been voided");
        
        
        invoice.processIt(DocumentEngine.ACTION_Complete);
        
        return invoice;
    }

    
    public static MInvoice completeInvoice(Properties ctx, int invoiceId, String trxName) throws OperationException
    {
        MInvoice invoice = new MInvoice(ctx,invoiceId,trxName);
        
        String docStatus = invoice.getDocStatus();
        String docAction = invoice.getDocAction();
        
        if (!docStatus.equals(DocumentEngine.STATUS_Drafted))
            throw new OperationException("cannot complete invoice because invoice status is not DRAFTED");
        
        if (!docAction.equals(DocumentEngine.ACTION_Complete))
            throw new OperationException("invalid Doc Action, Doc Action needs to be Complete");
        
        MClient.getAll(ctx);
        
        invoice.processIt(DocumentEngine.ACTION_Complete);
           
        if(invoice.isSOTrx()==true)
        {
        	MInvoiceLine invoiceLines[]=invoice.getLines();
        }
        //.sendInvoiceMessage(invoiceLines[0],UDIDocActionValues.CONFIRM);
        
        updateVendorInvoice(ctx,invoice, trxName);
        
        return invoice;
    }
   
   
    
    private static void updateVendorInvoice(Properties ctx,MInvoice invoice, String trxName) throws OperationException
    {
    	int refInv = invoice.getRef_Invoice_ID();
    	
    	if(refInv==0)
    		return;
    	
    	MInvoice vendorInvoice = new MInvoice(ctx,refInv,trxName);
    	
    	MInvoiceLine [] VInvoiceLine = vendorInvoice.getLines();
    	MInvoiceLine [] CInvoiceLine = invoice.getLines();
    	
    	for(int i=0;i<CInvoiceLine.length;i++)
    	{
    		VInvoiceLine[i].setM_AttributeSetInstance_ID(CInvoiceLine[i].getM_AttributeSetInstance_ID());
    		
    		//Fixed
    		PoManager.save(VInvoiceLine[i]);
    	}
    }

    public static MInvoice getCounterInvoice(Properties ctx,int customerInvoiceId, String trxName) throws OperationException
    {
    	MInvoice invoice = new MInvoice(ctx, customerInvoiceId, trxName);
    	
    	return getCounterInvoice(ctx, invoice);
    }
    
    public static MInvoice getCounterInvoice(Properties ctx,MInvoice customerInvoice) throws OperationException
    {
        
        int counterInvoiceId[] = MInvoice.getAllIDs(MInvoice.Table_Name, " AD_CLIENT_ID=" + Env.getAD_Client_ID(ctx) + " and REF_INVOICE_ID=" + customerInvoice.get_ID(), customerInvoice.get_TrxName());
        
        if (counterInvoiceId.length!=1)
            throw new OperationException("should get only 1 customer invoice");
        
        MInvoice vendorInvoice = new MInvoice(ctx, counterInvoiceId[0], customerInvoice.get_TrxName());
        
        
        
        if (vendorInvoice.getC_DocType_ID() == 0)
        {
            MDocType[] docType = MDocType.getOfDocBaseType(ctx,MDocType.DOCBASETYPE_APInvoice);
            if (docType.length == 0)
                throw new OperationException("Cannot find doc type for AP Invoice");
            vendorInvoice.setC_DocTypeTarget_ID(MDocType.DOCBASETYPE_APInvoice);
            vendorInvoice.setDescription("Vendor Invoice");
            
            PoManager.save(vendorInvoice);
        }     
       
        return vendorInvoice;
    }
    
    public static ArrayList getInvoiceHistory(Properties ctx, String docStatus, Integer partnerId, Integer month, Integer year) throws SQLException
    {
        
        StringBuffer select = new StringBuffer();
        StringBuffer from = new StringBuffer();
        StringBuffer where = new StringBuffer();
        
        select.append("select inv.C_INVOICE_ID, " + //1
                " bp.NAME,"+				//	2
                " bp.C_BPARTNER_ID,"+		//3
                " inv.DOCUMENTNO,"+		//	4
                " inv.DOCSTATUS,"+ //5
                " inv.C_ORDER_ID,"+			//6
                " inv.DATEINVOICED,"+		//7
                " inv.GRANDTOTAL,"+			//8
        //" decode(inv.ISPAID,'N','No','Y','Yes') isPaid"); //9
        " CASE WHEN inv.ISPAID = 'N' THEN 'No' WHEN inv.ISPAID='Y' THEN 'Yes' ELSE  isPaid END"); //9
        
        
        from.append(" from C_INVOICE inv,C_BPARTNER bp");
        
        where.append(" where inv.C_BPARTNER_ID = bp.C_BPARTNER_ID" +
                " and inv.AD_CLIENT_ID = "+ Env.getAD_Client_ID(ctx) +
                " and inv.AD_ORG_ID = "+ Env.getAD_Org_ID(ctx) +
                " and inv.isActive='Y'");
        
        if(docStatus!=null)
        {
            where.append(" and inv.docstatus = '" + docStatus + "'");		    	    
        }
        
        if (partnerId!= null)
        {
            where.append(" and bp.c_bpartner_id = " + partnerId);
        }
        
        if (month!= null) 
        {
            where.append(" and to_char(inv.created, 'mm') = " + month);
        }	    	
        
        if (year!= null) 
        {
            where.append(" and to_char(inv.created, 'yyyy') = " + year);
        }
        
        String sql = ""+select+from+where;
       
        
        ArrayList<InvoiceHistoryBean> list = new ArrayList<InvoiceHistoryBean>();
        
        
        PreparedStatement pstmt = DB.prepareStatement(sql,null);		
        
        ResultSet rs = null;
        
        try
        {
            rs = pstmt.executeQuery();			
            InvoiceHistoryBean bean;
            while(rs.next())			    
            {
                bean = new InvoiceHistoryBean();			    
                bean.setInvoiceId(Integer.valueOf(rs.getInt(1)));
                bean.setPartnerName(rs.getString(2));
                bean.setPartnerId(Integer.valueOf(rs.getInt(3)));
                bean.setDocumentNo(rs.getString(4));				
                bean.setDocStatus(UDIMap.docStatusMap.get(rs.getString(5)));
                bean.setDocStatusCode(rs.getString(5));
                bean.setOrderId(Integer.valueOf(rs.getInt(6)));
                bean.setDateInvoiced(rs.getTimestamp(7));
                bean.setGrandTotal(Integer.valueOf(rs.getInt(8)));
                bean.setIsPaid(rs.getString(9));
                list.add(bean);
                
            }
    		
            rs.close();            
        } 
        catch (SQLException e)
        {
            throw new SQLException();
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
    
    public static ArrayList viewInvoice(Properties ctx, Integer invoiceId) throws OperationException
    {
        if (invoiceId.intValue() == 0)
            throw new OperationException("cannot view invoice with id 0");
        
        MInvoice invoice = new MInvoice(ctx, invoiceId.intValue(), null);
        
        if (invoice.get_ID() == 0)
            throw new OperationException("cannot view invoice with id 0");
        
        ArrayList invoiceLines = retrieveInvoiceLineValues(ctx, invoice.getLines()); 
        
        return invoiceLines;
        
    }
    public static ArrayList retrieveInvoiceLineValues(Properties ctx, MInvoiceLine[] lines) throws OperationException
    {
        MInvoiceLine line;
        MProduct product;
        AttributeValuesPair attributeValuesPair;
        WebInvoiceLineBean invoiceLinebean;
        ArrayList <WebInvoiceLineBean>invoiceLines = new ArrayList<WebInvoiceLineBean>();
        for (int i = 0; i < lines.length; i++)
        {
            line = lines[i];
            product = new MProduct(ctx, line.getM_Product_ID(), null);
            attributeValuesPair = AttributeValuesManager.retrieveAttributeValues(ctx, product.getM_AttributeSetInstance_ID());
            invoiceLinebean = new WebInvoiceLineBean();
            invoiceLinebean.setAttributeValuesPair(attributeValuesPair);
            invoiceLinebean.setSerno(OrderReferenceManager.getSerno(ctx, line.getM_AttributeSetInstance_ID()));
            invoiceLinebean.setLineNetAmt(line.getLineNetAmt());
            invoiceLinebean.setLineTotalAmt(line.getLineTotalAmt());
            invoiceLinebean.setTaxAmt(line.getTaxAmt());
            invoiceLines.add(invoiceLinebean);
        }
        
        return invoiceLines;
        
    }
    
    public static WebDocumentBean getWebInvoiceBean(MInvoice invoice) throws OperationException, DataException
    {
        if (invoice == null)
            throw new OperationException("Invalid operation invoice is null");
        
        if (invoice.get_ID() == 0)
            throw new OperationException("You have deleted this invoice. You cannot view this invoice.");
        
        WebDocumentBean bean = new WebDocumentBean();
        
        bean.setInvoiceId(Integer.valueOf(invoice.get_ID()));
        
        Properties ctx = invoice.getCtx();
        
        MOrg myOrg = OrganisationManager.getMyOrg(ctx);
        MBPartner me = new MBPartner(ctx, myOrg.getLinkedC_BPartner_ID(null), null);
        bean.setMe(me);

      //  MOrg orderOrg = new MOrg(ctx, invoice.getAD_Org_ID(), null);
        
        int currencyId = invoice.getC_Currency_ID();
        MCurrency currency = new MCurrency(ctx,currencyId,null);
        bean.setCurrencySymbole(currency.getCurSymbol());
        
        
        /*if (orderOrg.getLinkedC_BPartner_ID() != myOrg.getLinkedC_BPartner_ID())
        	throw new DocumentDoesNotBelongToYouException("This invoice does not belong to you. You do not have access to it.");        
        */
        MBPartnerLocation meLocation[] =  MBPartnerLocation.getForBPartner(ctx,me.get_ID());
        if (meLocation.length  ==0)
        	throw new OperationException("No location has been set for your organisation. Please ask your administrator to set one for you");
        
        
        MLocation location = new MLocation(ctx, meLocation[0].getC_Location_ID(), null);
        
        if (meLocation == null)
            throw new OperationException("You must have a location set for your business partner, Please ask your administrator to set one for you");
        
        bean.setMeLocation(location);
        
        MBPartner you = new MBPartner(ctx, invoice.getC_BPartner_ID(), null);
        bean.setYou(you);
        
        MBPartnerLocation youBPLocation[] =  MBPartnerLocation.getForBPartner(ctx,you.get_ID());
        MLocation youLocation = new MLocation(ctx, youBPLocation[0].getC_Location_ID(), null);
        
        bean.setYoubpLocation(youBPLocation[0]);
        bean.setYouLocation(youLocation);
        
        ArrayList lines = populateInvoiceLines(ctx , invoice.getLines());
        bean.setLines(lines);
        bean = calculateInvoiceTotals(lines, bean);
        
        
        WebDocumentHeaderBean headerBean = createWebDocumentHeader(ctx, invoice.getAD_Org_ID(),  invoice.getC_BPartner_ID(), invoice.getDocStatus(), invoice.isSOTrx(),invoice.getPaymentRule());
        headerBean.setDocumentHeader(getDocumentHeader(invoice));
        
        
       	int invoiceId = invoice.get_ID();

       	int[] shipmentIds = MInOut.getAllIDs(MInOut.Table_Name, "c_invoice_id=" + invoiceId, null);
    	
    	if (shipmentIds.length == 0)	
    		headerBean.setShipped("No");
    	else
    		headerBean.setShipped("Yes");
    	
        bean.setHeaderBean(headerBean);
        
        bean.setInvoice(invoice);
        
        MOrder order = new MOrder(ctx, invoice.getC_Order_ID(), null);
        bean.setOrder(order);
        
        CommandBean cmdBean = new CommandBean();
        if (!invoice.isSOTrx())
            cmdBean = getVendorInvoiceWebCommands(invoice);
        else
            cmdBean = getCustomerInvoiceWebCommands(invoice);
        
        bean.setSimpleCommand(cmdBean.getSimpleCommand());
        bean.setComplexCommand(cmdBean.getComplexCommand());
        
        
        return bean;
    }
    
    public static String getDocumentHeader(MInvoice invoice)
    {
        if (invoice.isSOTrx())
            return CUSTOMER_INVOICE;
        
        return VENDOR_INVOICE;
    }
    
    
    private static WebDocumentBean calculateInvoiceTotals(ArrayList webOrderLineList, WebDocumentBean webInvoiceLineBean)
    {
       Iterator iter = webOrderLineList.iterator();
       
       WebInvoiceLineBean bean;
       
       BigDecimal totalLines = new BigDecimal(0);
       BigDecimal totalTax = new BigDecimal(0);
       BigDecimal grandTotal;
       
       BigDecimal roundedTotalLines;
       BigDecimal roundedTotalTax;
       BigDecimal roundedGrandTotal;
       while(iter.hasNext())
       {
           bean = (WebInvoiceLineBean) iter.next();
           totalLines = totalLines.add(bean.getLineNetAmt());
           totalTax = totalTax.add(bean.getTaxAmt());
       }
       
       grandTotal = totalLines.add(totalTax);
       
       
       roundedTotalLines = round(totalLines, 2);
       roundedTotalTax = round(totalTax, 2);
       
       roundedGrandTotal = round(grandTotal, 2);
        
       webInvoiceLineBean.setTotalLines(roundedTotalLines);
       webInvoiceLineBean.setTotalTax(roundedTotalTax);
       webInvoiceLineBean.setGrandTotal(roundedGrandTotal);
       
       return webInvoiceLineBean;
    }
    private static BigDecimal round(BigDecimal number, int decimalPlaces)
    {
        BigDecimal roundedNumber = number.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
        
        return roundedNumber;
    }
    
    private static ArrayList<WebInvoiceLineBean> populateInvoiceLines(Properties ctx, MInvoiceLine[] lines) throws OperationException
    {
        MInvoiceLine line;
        MProduct product;
        AttributeValuesPair attributeValuesPair;
        ArrayList<WebInvoiceLineBean> invoiceLines = new ArrayList<WebInvoiceLineBean>();
        for (int i = 0; i < lines.length; i++)
        {
            line = lines[i];
            product = new MProduct(ctx, line.getM_Product_ID(), null);
            attributeValuesPair = AttributeValuesManager.retrieveAttributeValues(ctx, product.getM_AttributeSetInstance_ID());
            WebInvoiceLineBean invoiceLineBean = new WebInvoiceLineBean();
            invoiceLineBean.setAttributeValuesPair(attributeValuesPair);
            
            BigDecimal roundedLineNetAmount = round(line.getLineNetAmt(), 2);
           
            //line tax amount
            BigDecimal lineTaxAmount = getLineTaxAmt(line.getCtx(), line.getLineNetAmt(), line.getC_Tax_ID(), line.get_ID());
            BigDecimal roundedTaxAmount = round(lineTaxAmount, 2);
            invoiceLineBean.setTaxAmt(roundedTaxAmount);
            
            
            //lineTotalAmount = lineNetAmount + lineTaxAmount
            BigDecimal lineTotalAmount = line.getLineNetAmt().add(lineTaxAmount);
            BigDecimal roundedLineTotalAmount = round(lineTotalAmount, 2);
            
            invoiceLineBean.setLineTotalAmt(roundedLineTotalAmount);
            invoiceLineBean.setQtyOrdered(line.getQtyEntered());
            
            invoiceLineBean.setLineNetAmt(roundedLineNetAmount);
            invoiceLineBean.setPriceActual(line.getPriceActual());
            
            invoiceLineBean.setProductId(Integer.valueOf(line.getM_Product_ID()));
            invoiceLineBean.setOrderLineId(Integer.valueOf(line.get_ID()));
            
            invoiceLineBean.setSerno(getSerno(ctx, line.getM_AttributeSetInstance_ID()));
            
            if(line.getM_Product_ID() == 0)
            {
            	if(line.getC_Charge_ID() != 0)
            	{
            		MCharge charge = ChargeManager.loadCharge(ctx, line.getC_Charge_ID(), null);
            		invoiceLineBean.setDescription(charge.getName());
            	}
            	else 
                    invoiceLineBean.setDescription("Adjustment product");
            }
            else
            	invoiceLineBean.setDescription(ProductManager.getProductName(ctx, line.getM_Product_ID()));
            invoiceLines.add(invoiceLineBean);
        }
        
        return invoiceLines;
    }
    
    private static CommandBean getVendorInvoiceWebCommands(MInvoice invoice) throws OperationException
    {
        
        if (invoice.isSOTrx())
            throw new OperationException("Invoice should be a vendor Invoice");
        
        
        String[] simpleCommands = new String[0];
        String[] complexCommands = new String[0];  
       
        CommandBean bean = new CommandBean();
        bean.setSimpleCommand(simpleCommands);
        bean.setComplexCommand(complexCommands);
        return bean;
    }
    
    private static CommandBean getCustomerInvoiceWebCommands(MInvoice invoice) throws OperationException, DataException
    {
        
        if (!invoice.isSOTrx())
            throw new OperationException("Invoice should be a customer invoice");
       
        
        String[] simpleCommands = null;
        String[] complexCommands = null;  
       
        CommandBean bean = new CommandBean();
        
        if (invoice.getDocStatus().equals(DocumentEngine.STATUS_Drafted))
        {
            simpleCommands = new String[]{Constants.INVOICE_ACTION_COMPLETE};
            complexCommands = new String[]{Constants.INVOICE_ACTION_CANCEL};
        }
        
        if (invoice.getDocStatus().equals(DocumentEngine.STATUS_Voided))
        {
            complexCommands = new String[]{Constants.INVOICE_ACTION_DELETE};
        }
        
        if (invoice.getDocStatus().equals(DocumentEngine.STATUS_Completed))
        {
            bean = getCIWebCommands(invoice);
            return bean;
        }
               
       
        bean.setSimpleCommand(simpleCommands);
        bean.setComplexCommand(complexCommands);
        return bean;
    }
    
    public static CommandBean getCIWebCommands(MInvoice invoice) throws OperationException, DataException
    {
         MOrder order = new MOrder(invoice.getCtx(), invoice.getC_Order_ID(), null);
        
        int[] docTypes = MDocType.getAllIDs(MDocType.Table_Name, " ad_client_id="+ Env.getAD_Client_ID(invoice.getCtx()) + " and DOCSUBTYPESO='" + MDocType.DOCSUBTYPESO_POSOrder+ "'", null );
        
        String simpleCommands[] = null;
        String complexCommands[] = null;
        
        //POS order
        if (order.getC_DocType_ID() == docTypes[0])
        {
            simpleCommands = new String[]{};
            complexCommands = new String[]{};
        }
        
        if (invoice.isPaid() && (!MinOutManager.isShipped(invoice.getCtx(), invoice)))
        {
        	int[] shipmentIds = MInOut.getAllIDs(MInOut.Table_Name, "c_invoice_id=" + invoice.get_ID(), null);
        	
        	if (shipmentIds.length == 0)	
        		simpleCommands = new String[]{Constants.MINOUT_ACTION_SHIP};
        	else
        		if (shipmentIds.length > 1)	
        			throw new DataException("This invoice has been shipped more than once!");
        	
        }
        if (!invoice.isPaid() && (!MinOutManager.isShipped(invoice.getCtx(), invoice)))
        {
        	simpleCommands = new String[] {Constants.INVOICE_ACTION_PAID, Constants.INVOICE_ACTION_PAID_AND_SHIP};
        }
                
        CommandBean bean = new CommandBean();
        bean.setSimpleCommand(simpleCommands);
        bean.setComplexCommand(complexCommands);
        
        return bean;
    }
 
    public static MInvoice deleteInvoice(Properties ctx, MInvoice invoice) throws OperationException
    {
        invoice.setIsActive(false);
        PoManager.save(invoice);
        
        return invoice;
    }
    
    public static boolean allOrderLinesInvoiced(Properties ctx, MOrder order)
    {
        MOrderLine[] lines = order.getLines();
        
        MOrderLine line;
        for (int i = 0; i < lines.length; i ++)
        {
            line = lines[i];
            
            if (!line.getQtyInvoiced().equals(line.getQtyOrdered()))
                return false;
            	
        }
        
        return true;
    }
    
    public static MInvoice voidInvoice(Properties ctx, MInvoice invoice) throws OperationException
    {
       
        if (!invoice.isSOTrx())
            throw new OperationException("Only voiding of customer invoice is currently supported.");
        
        if (!invoice.getDocStatus().equals(DocumentEngine.STATUS_Drafted))
            throw new OperationException("The system only supports voiding of DRAFTED Invoice.");
       
        //we need to force void here because invoice has already been processed
        //invoice.isProcessed=true
        forceVoid(ctx, invoice);
        
        invoice = new MInvoice(ctx, invoice.get_ID(), invoice.get_TrxName());
        
        return invoice;
    }
    
    public static void forceVoid(Properties ctx, MInvoice invoice) throws OperationException
    {
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        
        String sql  = "UPDATE C_Invoice SET DOCSTATUS=" + "'" 
				        + DocumentEngine.STATUS_Voided + "'"
				        + " WHERE AD_CLIENT_ID=" + Env.getAD_Client_ID(ctx)
				        + " AND C_INVOICE_ID=" + invoice.get_ID();
				        
        String sql2  = "UPDATE C_Invoice SET DOCACTION=" + "'" 
				        + DocumentEngine.ACTION_None + "'"
				        + " WHERE AD_CLIENT_ID=" + Env.getAD_Client_ID(ctx)
				        + " AND C_INVOICE_ID=" + invoice.get_ID();
				        
        pstmt1 = DB.prepareStatement(sql,invoice.get_TrxName());
        pstmt2 = DB.prepareStatement(sql2,invoice.get_TrxName());
       
        try 
        {
            pstmt1.executeUpdate();
            pstmt2.executeUpdate();
        } 
        catch (SQLException e) 
        {
            throw new OperationException(e.getMessage());
        }
        finally
        {
        	try
        	{
        		pstmt1.close();
        		pstmt2.close();
        	}
        	catch (Exception e)
        	{}
        	
        	pstmt1 = null;
        	pstmt2 = null;
        }
        
    }
    
    public static int[] getInvoiceIdsForOrder(Properties ctx, int orderId, String trxName)
    {
    	String whereClause = "isACTIVE='Y' and C_Order_ID=" + orderId;
    	int invoiceIds[] = MInvoice.getAllIDs(MInvoice.Table_Name, whereClause, trxName);
    	return invoiceIds;
    }
    
    public static MInvoice loadInvoice(Properties ctx, int invoiceId, String trxName) throws OperationException
    {
    	MInvoice invoice = new MInvoice(ctx, invoiceId, trxName);
    	if(invoice.get_ID() == 0)
    		throw new OperationException("Could not load invoice with id: " + invoiceId);
    	
    	return invoice;
    }
    public static ArrayList<WebOrderLineBean> populateInvoiceLines(Properties ctx, MInvoice invoice,boolean abbr) throws  OperationException
    {
        MInvoiceLine [] lines=invoice.getLines();
        MInvoiceLine line;
        MProduct product;
        // AttributeValuesPair attributeValuesPair;
        WebOrderLineBean orderLineBean;
        BigDecimal qty = Env.ZERO;
      
        
        ArrayList<WebOrderLineBean> orderLines = new ArrayList<WebOrderLineBean>();
        for (int i = 0; i < lines.length; i++)
        {
            line = lines[i];
            
            product = new MProduct(ctx, line.getM_Product_ID(), null);
            
            int priceScale = line.getLineNetAmt().scale();
            
            orderLineBean = new WebOrderLineBean();
            orderLineBean.setLineNetAmt(line.getLineNetAmt());
            BigDecimal lineTaxAmount = POSManager.getLineTaxAmt(line.getCtx(), line.getLineNetAmt(), line.getC_Tax_ID(),line.getQtyEntered());
            lineTaxAmount = lineTaxAmount.setScale(priceScale, RoundingMode.HALF_UP);
            orderLineBean.setTaxAmt(lineTaxAmount);
            BigDecimal lineTotalAmount = line.getLineNetAmt().add(orderLineBean.getTaxAmt());
            lineTotalAmount = lineTotalAmount.setScale(priceScale, RoundingMode.HALF_UP);
            orderLineBean.setLineTotalAmt(lineTotalAmount);
            
            orderLineBean.setProductId(Integer.valueOf(line.getM_Product_ID()));
            
            
            if (line.getM_Product_ID() == 0)
            {
                if(line.getC_Charge_ID() != 0)
                {
                    MCharge charge = ChargeManager.loadCharge(ctx, line.getC_Charge_ID(), null);
                    orderLineBean.setProductName(charge.getName());
                }
                else
                    throw new OperationException("Unknown Order line type with id: " + line.get_ID()); 
            }
            else
            {
                if(abbr)
                    orderLineBean.setProductName(new PrintFormatter().format(product).toString());
                else     
                    orderLineBean.setProductName(product.getName().replaceAll("~"," "));
                orderLineBean.setDescription(product.getDescription());
            }
            
            orderLineBean.setOrderLineId(Integer.valueOf(line.get_ID()));
            orderLineBean.setIsinvoiced(Boolean.valueOf(false));
            orderLineBean.setIsQtyReserved(Boolean.valueOf(true));
            qty= qty.add(line.getQtyEntered());
            orderLineBean.setQtyTotal(qty);
           
            orderLineBean.setQtyOrdered(line.getQtyEntered());
            orderLineBean.setGrandTotal(invoice.getGrandTotal());
            
            
            
          
            orderLines.add(orderLineBean);
            
        }
        
        return orderLines;
    }
    
    public static void printInvoice(Properties ctx,MInvoice invoice) throws OperationException
    {
        PrintManager.print(ctx,ReportEngine.INVOICE,invoice.get_ID());
    }
    
    /**
     * 
     * @param ctx
     * @param dateInvoiced
     * @param orgId
     * @param bPartnerId
     * @param orderId
     * @param consolidateDoc
     * @param docAction
     * @param trx
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
    public static void generateInvoices(Properties ctx, Timestamp dateInvoiced, int orgId, 
    		int bPartnerId, int orderId, boolean isConsolidateDoc, String docAction, Trx trx) 
    throws InstantiationException, IllegalAccessException, ClassNotFoundException
    {
    	String trxName = trx.getTrxName();
    	int processId = MProcess.getProcess_ID("C_Invoice_Generate", trxName);
    	MProcess process = new MProcess(ctx, processId, trxName);
		
    	MPInstance processInstance = new MPInstance(ctx, processId, 0);
		processInstance.save(trxName);	
    	
		MPInstancePara paramDateInvoiced = new MPInstancePara(processInstance, 10);
    	paramDateInvoiced.setParameterName("DateInvoiced");
    	paramDateInvoiced.setP_Date(dateInvoiced);
    	paramDateInvoiced.save(trxName);
    	
    	MPInstancePara paramOrgId = new MPInstancePara(processInstance, 20);
    	paramOrgId.setParameterName("AD_Org_ID");
    	paramOrgId.setP_Number(orgId);
    	paramOrgId.save(trxName);
    	
    	MPInstancePara paramBPartnerId = new MPInstancePara(processInstance, 30);
    	paramBPartnerId.setParameterName("C_BPartner_ID");
    	paramBPartnerId.setP_Number(bPartnerId);
    	paramBPartnerId.save(trxName);
    	
    	MPInstancePara paramOrderId = new MPInstancePara(processInstance, 40);
    	paramOrderId.setParameterName("C_Order_ID");
    	paramOrderId.setP_Number(orderId);
    	paramOrderId.save(trxName);
    	
    	MPInstancePara paramConsolidateDoc = new MPInstancePara(processInstance, 50);
    	paramConsolidateDoc.setParameterName("ConsolidateDocument");
    	paramConsolidateDoc.setP_String(isConsolidateDoc?"Y":"N");
    	paramConsolidateDoc.save(trxName);
    	
    	MPInstancePara paramDocAction = new MPInstancePara(processInstance, 60);
    	paramDocAction.setParameterName("DocAction");
    	paramDocAction.setP_String(docAction);
    	paramDocAction.save(trxName);
    	
    	ProcessInfo pi = new ProcessInfo(process.getDescription(), processId);
    	ProcessInfoParameter piDateInvoiced = new ProcessInfoParameter("DateInvoiced", dateInvoiced, null, null, null);
    	ProcessInfoParameter piOrgId = new ProcessInfoParameter("AD_Org_ID", orgId, null, null, null);
    	ProcessInfoParameter piBPartnerId = new ProcessInfoParameter("C_BPartner_ID", bPartnerId, null, null, null);
    	ProcessInfoParameter piOrderId = new ProcessInfoParameter("C_Order_ID", orderId, null, null, null);
    	ProcessInfoParameter piConsolidateDoc = new ProcessInfoParameter("ConsolidateDocument", isConsolidateDoc?"Y":"N", null, null, null);
    	ProcessInfoParameter piDocAction = new ProcessInfoParameter("DocAction", docAction, null, null, null);
    	
    	ProcessInfoParameter[] piParameters = new ProcessInfoParameter[]{piDateInvoiced, piOrgId, piBPartnerId, piOrderId, piConsolidateDoc, piDocAction};
    	pi.setParameter(piParameters);
    	pi.setAD_PInstance_ID(processInstance.getAD_PInstance_ID());
    	pi.setAD_Process_ID(processInstance.getAD_Process_ID());
    	
    	ProcessManager.startProcess(ctx, InvoiceGenerate.class.getName(), pi, trx);
    }
    
}
