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

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_C_DocType;
import org.compiere.model.I_C_Order;
import org.compiere.model.I_C_Payment;
import org.compiere.model.MAllocationHdr;
import org.compiere.model.MAllocationLine;
import org.compiere.model.MDocType;
import org.compiere.model.MInvoice;
import org.compiere.model.MOrder;
import org.compiere.model.MPayment;
import org.compiere.model.PO;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * This process allows create a new sales order based on other and change the business partner
 * all payments and allocations can be replicated for new order with new business partner
 * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 23/12/15.
 */
public class CreateOrderBasedOnAnother extends SvrProcess {

    private Integer billPartnerId;
    private int sourceOrderId;
    private String docSubTypeSO;
    private String docAction;
    private boolean isIncludePayments;
    private boolean isAllocated;
    private MOrder targetOrder;
    private Timestamp today;


    @Override
    protected void prepare() {
        ProcessInfoParameter[] params = getParameter();
        for (ProcessInfoParameter parameter : params) {
            String para = parameter.getParameterName();
            if (para.equals(I_C_Order.COLUMNNAME_C_Order_ID))
                sourceOrderId = parameter.getParameterAsInt();
            if (para.equals(I_C_Order.COLUMNNAME_Bill_BPartner_ID))
                billPartnerId = parameter.getParameterAsInt();
            if (para.equals(I_C_Order.COLUMNNAME_DocAction))
                docAction = parameter.getParameterAsString();
            if (para.equals(I_C_DocType.COLUMNNAME_DocSubTypeSO))
                docSubTypeSO = parameter.getParameterAsString();
            if (para.equals("IsIncludePayments"))
                isIncludePayments = parameter.getParameterAsBoolean();
            if (para.equals(I_C_Payment.COLUMNNAME_IsAllocated))
                isAllocated = parameter.getParameterAsBoolean();
        }

    }

    @Override
    protected String doIt() throws Exception {
        if (sourceOrderId <= 0)
            throw new AdempiereException("@C_Order_ID@ @NotFound@");

        today = new Timestamp(System.currentTimeMillis());
        MOrder sourceOrder = new MOrder(getCtx(),  sourceOrderId , get_TrxName());
        if(billPartnerId == 0)
            billPartnerId = sourceOrder.getC_BPartner_ID();

        //Create new Order based on source order
        targetOrder = MOrder.copyFrom(
                sourceOrder,
                today ,
                sourceOrder.getC_DocTypeTarget_ID(),
                sourceOrder.isSOTrx(),
                false ,
                true ,
                get_TrxName());
        if (docSubTypeSO != null)
            targetOrder.setC_DocTypeTarget_ID(MDocType.getDocTypeBaseOnSubType(sourceOrder.getAD_Org_ID() , MDocType.DOCBASETYPE_SalesOrder , docSubTypeSO));

        targetOrder.setC_BPartner_ID(billPartnerId);
        targetOrder.setRef_Order_ID(sourceOrder.get_ID());
        targetOrder.setDocAction(docAction);
        targetOrder.setDocStatus(DocAction.STATUS_Drafted);
        targetOrder.setProcessed(false);
        targetOrder.saveEx();

        //Complete new Order
        targetOrder.processIt(docAction);
        targetOrder.saveEx();
        addLog(targetOrder.getDocumentInfo());

        if(isIncludePayments)
            createPayments(sourceOrder, targetOrder);
        if (isAllocated)
            createAllocations(targetOrder);

        getProcessInfo().setRecord_ID(targetOrder.get_ID());
        return "@C_Order_ID@ " + targetOrder.getDocumentInfo();
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
            allocation.setDocStatus(DocAction.STATUS_Drafted);
            allocation.setDocAction(DocAction.ACTION_Complete);
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

            allocation.processIt(DocAction.ACTION_Complete);
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
            payment.addDescription(" @From@ " + sourcePayment.getDocumentNo());
            payment.setIsReceipt(true);
            payment.setC_DocType_ID(MDocType.getDocType(MDocType.DOCBASETYPE_ARReceipt, sourceOrder.getAD_Org_ID()));
            payment.setIsPrepayment(true);
            payment.saveEx();

            payment.processIt(docAction);
            payment.saveEx();
            addLog(payment.getDocumentInfo());
        }
    }
}
