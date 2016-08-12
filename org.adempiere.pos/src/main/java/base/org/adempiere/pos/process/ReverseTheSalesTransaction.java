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

import org.adempiere.pos.AdempierePOSException;
import org.compiere.model.MDocType;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutConfirm;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInOutLineConfirm;
import org.compiere.model.MInvoice;
import org.compiere.model.MOrder;
import org.compiere.model.MPayment;
import org.compiere.model.MRMA;
import org.compiere.model.MRMALine;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.model.X_M_RMAType;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfo;
import org.compiere.util.Msg;
import org.eevolution.service.dsl.ProcessBuilder;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * Process allows reverse the sales order using new documents with new dates and cancel of original effects
 * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 23/12/15.
 */
public class ReverseTheSalesTransaction extends ReverseTheSalesTransactionAbstract  {
    private Timestamp today;
    private List<MInOut> customerReturns = new ArrayList<MInOut>();


    @Override
    protected void prepare() {
        super.prepare();
    }

    @Override
    protected String doIt() throws Exception {
        today = new Timestamp(System.currentTimeMillis());
        // Get Order
        MOrder sourceOrder = new MOrder(getCtx(), getOrderId(), get_TrxName());
        // Get Invoices for ths order
        MInOut[] shipments = sourceOrder.getShipments();
        // If not exist invoice then only is necessary reverse shipment
        if (shipments.length > 0) {
            // Validate if partner not is POS partner standard then reverse shipment
            if (sourceOrder.getC_BPartner_ID() != getInvoicePartnerId() || isCancelled()) {
                cancelShipments(shipments);
            }
        }
        MInvoice[] invoices = sourceOrder.getInvoices();
        if(invoices.length > 0)
        {
            if (sourceOrder.getC_BPartner_ID() != getInvoicePartnerId() || isCancelled())
                cancelInvoices();
        }

        //Cancel original payment
        for (MPayment payment :cancelPayments(sourceOrder, today))
             addLog(payment.getDocumentInfo());

        sourceOrder.processIt(DocAction.ACTION_Close);
        sourceOrder.saveEx();
        return "@Ok@";
    }

    private void cancelShipments( MInOut[] sourceShipments)
    {
        for (MInOut sourceShipment : sourceShipments) {
            MRMA rma = new MRMA(getCtx(), 0 , get_TrxName());
            rma.setM_InOut_ID(sourceShipment.getM_InOut_ID());
            rma.setAD_Org_ID(sourceShipment.getAD_Org_ID());
            rma.setM_RMAType_ID(getRMATypeId());
            rma.setC_BPartner_ID(sourceShipment.getC_BPartner_ID());
            rma.setName(sourceShipment.getDocumentInfo());
            rma.setIsSOTrx(true);
            rma.setSalesRep_ID(sourceShipment.getSalesRep_ID());
            rma.setC_DocType_ID(MDocType.getDocTypeBaseOnSubType(sourceShipment.getAD_Org_ID() , MDocType.DOCBASETYPE_SalesOrder , MDocType.DOCSUBTYPESO_ReturnMaterial));
            rma.setDocStatus(DocAction.STATUS_Drafted);
            rma.setDocAction(DocAction.ACTION_Complete);
            rma.saveEx();

            MInOut customerReturn = new MInOut(getCtx() , 0 , get_TrxName());
            PO.copyValues(sourceShipment, customerReturn);
            customerReturn.setDocumentNo(null);
            customerReturn.setM_RMA_ID(rma.getM_RMA_ID());
            customerReturn.setIsSOTrx(true);
            customerReturn.setC_BPartner_ID(sourceShipment.getC_BPartner_ID());
            customerReturn.setC_Order_ID(-1);
            for (MDocType documentType : MDocType.getOfDocBaseType(getCtx() , MDocType.DOCBASETYPE_MaterialReceipt))
                if (documentType.isSOTrx())
                    customerReturn.setC_DocType_ID();

            customerReturn.setMovementType(MInOut.MOVEMENTTYPE_CustomerReturns);
            customerReturn.setDocStatus(DocAction.STATUS_Drafted);
            customerReturn.setDocAction(DocAction.ACTION_Complete);
            customerReturn.setProcessed(false);
            customerReturn.saveEx();

            for (MInOutLine sourceShipmentLine : sourceShipment.getLines())
            {
                MRMALine rmaLine = new MRMALine(getCtx() , 0 , get_TrxName());
                rmaLine.setM_RMA_ID(rma.getM_RMA_ID());
                rmaLine.setAD_Org_ID(sourceShipmentLine.getAD_Org_ID());
                rmaLine.setM_InOutLine_ID(sourceShipmentLine.getM_InOutLine_ID());
                rmaLine.setQty(sourceShipmentLine.getMovementQty());
                rmaLine.saveEx();

                MInOutLine customerReturnLine = new MInOutLine(getCtx() , 0 , get_TrxName());
                customerReturnLine.setM_InOut_ID(customerReturn.getM_InOut_ID());
                customerReturnLine.setM_RMALine_ID(rmaLine.getM_RMALine_ID());
                customerReturnLine.setM_Product_ID(rmaLine.getM_Product_ID());
                customerReturnLine.setM_Locator_ID(sourceShipmentLine.getM_Locator_ID());
                customerReturnLine.setMovementQty(rmaLine.getQty());
                customerReturnLine.saveEx();
            }

            rma.processIt(DocAction.ACTION_Complete);
            rma.saveEx();
            addLog(rma.getDocumentInfo());

            if (customerReturn.getC_DocType().isShipConfirm() && isShipReceiptConfirmation())
            {
                customerReturn.processIt(DocAction.STATUS_InProgress);
                customerReturn.saveEx();

                for (MInOutConfirm confirm : customerReturn.getConfirmations(true)) {
                    for (MInOutLineConfirm confirmLine : confirm.getLines(true)) {
                        confirmLine.setConfirmedQty(confirmLine.getTargetQty());
                        confirmLine.saveEx();
                    }

                    confirm.processIt(DocAction.ACTION_Complete);
                    confirm.saveEx();
                    addLog(confirm.getDocumentInfo());
                }
            }

            customerReturn.processIt(DocAction.STATUS_Completed);
            customerReturn.saveEx();
            addLog(customerReturn.getDocumentInfo());

            customerReturns.add(customerReturn);
        }
    }

    private void cancelInvoices()
    {
        for (MInOut customerReturn : customerReturns) {
            ProcessInfo processInfo = ProcessBuilder
                    .create(getCtx())
                    .process("M_InOut_CreateInvoice") // AD_Process_ID = 154
                    .withTitle("Generate Invoice from Receipt")
                    .withRecordId(MInOut.Table_ID , customerReturn.getM_InOut_ID())
                    .withoutTransactionClose()
                    .execute(get_TrxName());

            if (processInfo.isError()) {
                String errorMessage = Msg.parseTranslation(getCtx(), processInfo.getTitle() + " @ProcessRunError@ " + processInfo.getSummary() + " " + processInfo.getLogInfo());
                throw new AdempierePOSException(errorMessage);
            }

            addLog(processInfo.getLogInfo());

            for (MInvoice creditNote :  getCreditNotes(customerReturn.getM_RMA_ID())) {
                if (creditNote != null && creditNote.getC_Invoice_ID() > 0) {
                    creditNote.setDateInvoiced(today);
                    creditNote.setDateAcct(today);
                    creditNote.processIt(DocAction.ACTION_Complete);
                    creditNote.saveEx();
                    addLog(creditNote.getDocumentInfo());
                }
            }
        }
    }

    static public List<MPayment> cancelPayments(MOrder sourceOrder, Timestamp today)
    {
        List<MPayment> payments = new ArrayList<>();
        List<MPayment> sourcePayments = MPayment.getOfOrder(sourceOrder);
        for (MPayment sourcePayment : sourcePayments)
        {
            MPayment payment = new MPayment(sourceOrder.getCtx() ,  0 , sourceOrder.get_TrxName());
            PO.copyValues(sourcePayment, payment);
            payment.setDateTrx(today);
            payment.setC_Order_ID(sourceOrder.getC_Order_ID());
            payment.setDateAcct(today);
            payment.addDescription(" @From@ " + sourcePayment.getDocumentNo());
            payment.setIsReceipt(false);
            payment.setC_DocType_ID(MDocType.getDocType(MDocType.DOCBASETYPE_APPayment));
            payment.setDocAction(DocAction.ACTION_Complete);
            payment.setDocStatus(DocAction.STATUS_Drafted);
            payment.setIsPrepayment(true);
            payment.saveEx();

            payment.processIt(DocAction.ACTION_Complete);
            payment.saveEx();
            payments.add(payment);
        }
        return payments;
    }

    public int getRMATypeId() {
        return new Query(getCtx(), X_M_RMAType.Table_Name , null , get_TrxName()).setClient_ID().firstIdOnly();
    }

    public List<MInvoice> getCreditNotes(int Id)
    {
        StringBuilder where = new StringBuilder();
        where.append(MRMA.COLUMNNAME_M_RMA_ID).append("=?");
        return new Query(getCtx() , MInvoice.Table_Name , where.toString() , get_TrxName())
                .setClient_ID()
                .setParameters(Id)
                .list();

    }
}
