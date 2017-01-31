/** ****************************************************************************
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
 * Copyright (C) 2003-2016 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 * ****************************************************************************/
package org.adempiere.pos.process;

import org.compiere.model.MAllocationHdr;
import org.compiere.model.MAllocationLine;
import org.compiere.model.MDocType;
import org.compiere.model.MInvoice;
import org.compiere.model.MOrder;
import org.compiere.model.MPayment;
import org.compiere.model.PO;
import org.compiere.util.Msg;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * This process allows create a new sales order based on other and change the business partner
 * all payments and allocations can be replicated for new order with new business partner
 * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 23/12/15.
 * @contributor Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/670">
 * 		@see FR [ 670 ] Standard process for return material on POS</a>
 */
public class CreateOrderBasedOnAnother extends CreateOrderBasedOnAnotherAbstract {

    private MOrder targetOrder;
    private Timestamp today;


    @Override
    protected void prepare() {
        super.prepare();
    }

    @Override
    protected String doIt() throws Exception {
        today = new Timestamp(System.currentTimeMillis());
        MOrder sourceOrder = new MOrder(getCtx(),  getOrderSourceId() , get_TrxName());
        //Create new Order based on source order
        targetOrder = MOrder.copyFrom(
                sourceOrder,
                today ,
                sourceOrder.getC_DocTypeTarget_ID(),
                sourceOrder.isSOTrx(),
                false ,
                true ,
                get_TrxName());
        if (getSOSubType() != null)
            targetOrder.setC_DocTypeTarget_ID(MDocType.getDocTypeBaseOnSubType(sourceOrder.getAD_Org_ID() , MDocType.DOCBASETYPE_SalesOrder , getSOSubType()));

        targetOrder.setC_BPartner_ID(getInvoicePartnerId());
        targetOrder.setRef_Order_ID(sourceOrder.get_ID());
        targetOrder.setDocAction(getDocumentAction());
        targetOrder.setDocStatus(org.compiere.process.DocAction.STATUS_Drafted);
        targetOrder.setProcessed(false);
        targetOrder.saveEx();

        //Complete new Order
        targetOrder.processIt(getDocumentAction());
        targetOrder.saveEx();
        addLog(targetOrder.getDocumentNo());
        //	Set Record ID
        getProcessInfo().setRecord_ID(targetOrder.get_ID());
        String message = "@C_Order_ID@ " + targetOrder.getDocumentNo();
        //	Validate Document Action
        if(!targetOrder.isProcessed()) {
        	return message;
        }

        if(isIncludePayments())
            createPayments(sourceOrder, targetOrder);
        if (isAllocated())
            createAllocations(targetOrder);
        //	Default Return
        return message;
    }

    /**
     * Create Allocations for new order
     * @param targetOrder
     */
    private void createAllocations(MOrder targetOrder) {
        List<MPayment> payments = MPayment.getOfOrder(targetOrder);
        MInvoice[] invoices = targetOrder.getInvoices();
        BigDecimal totalPay = BigDecimal.ZERO;
        BigDecimal totalInvoiced =  BigDecimal.ZERO;
        for (MPayment payment : payments)
            totalPay = totalPay.add(payment.getPayAmt());

        for (MInvoice invoice : invoices)
        {
            totalInvoiced =  totalInvoiced.add(invoice.getGrandTotal());
        }

        if (totalInvoiced.signum() != 0
         && totalPay.signum() != 0
         && totalInvoiced.compareTo(totalPay) == 0)
        {
            MAllocationHdr allocation = new MAllocationHdr(
                    getCtx() ,
                    true ,
                    today ,
                    targetOrder.getC_Currency_ID() ,
                    targetOrder.getDescription() ,
                    get_TrxName());
            allocation.setDocStatus(org.compiere.process.DocAction.STATUS_Drafted);
            allocation.setDocAction(org.compiere.process.DocAction.ACTION_Complete);
            allocation.saveEx();
            addLog(allocation.getDocumentInfo());
            for (MInvoice invoice : invoices)
            {
                MAllocationLine allocationLine =  new MAllocationLine(allocation);
                allocationLine.setDocInfo(targetOrder.getC_BPartner_ID() , targetOrder.getC_Order_ID() , invoice.getC_Invoice_ID());
                allocationLine.setAmount(invoice.getGrandTotal());
                allocationLine.saveEx();
            }

            for (MPayment payment : payments)
            {
                MAllocationLine allocationLine = new MAllocationLine(allocation);
                allocationLine.setPaymentInfo(payment.get_ID() , 0 );
                allocationLine.setAmount(payment.getPayAmt());
                allocationLine.saveEx();
            }

            allocation.processIt(org.compiere.process.DocAction.ACTION_Complete);
            allocation.saveEx();
        }
    }

    /**
     * Create payment for new Order
     * @param sourceOrder
     * @param targetOrder
     */
    private void createPayments(MOrder sourceOrder , MOrder targetOrder)
    {
        for (MPayment sourcePayment : MPayment.getOfOrder(sourceOrder))
        {
            MPayment payment = new MPayment(getCtx() ,  0 , get_TrxName());
            PO.copyValues(sourcePayment, payment);
            payment.setDateTrx(today);
            payment.setDateAcct(today);
            payment.setC_Order_ID(targetOrder.getC_Order_ID());
            payment.setC_BPartner_ID(targetOrder.getC_BPartner_ID());
            payment.setC_Invoice_ID(-1);
            payment.addDescription(Msg.parseTranslation(sourceOrder.getCtx() , " @From@ ") + sourcePayment.getDocumentNo());
            payment.setIsReceipt(true);
            payment.setC_DocType_ID(MDocType.getDocType(MDocType.DOCBASETYPE_ARReceipt, sourceOrder.getAD_Org_ID()));
            payment.setIsPrepayment(true);
            payment.saveEx();

            payment.processIt(getDocumentAction());
            payment.saveEx();
            addLog(payment.getDocumentInfo());
        }
    }
}
