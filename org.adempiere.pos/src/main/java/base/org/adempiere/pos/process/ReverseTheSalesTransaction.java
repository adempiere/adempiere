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
import org.compiere.model.*;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;

import java.sql.Timestamp;
import java.util.List;


/**
 * Process allows reverse the sales order using reverse documents and different dates
 * Created by e-Evolution on 23/12/15.
 */
public class ReverseTheSalesTransaction extends SvrProcess  {

    private int sourceOrderId;
    private int billPartnerId;
    private Timestamp today;


    @Override
    protected void prepare() {
        ProcessInfoParameter[] params = getParameter();
        for (ProcessInfoParameter parameter : params) {
            String para = parameter.getParameterName();
            if (para.equals(I_C_Order.COLUMNNAME_C_Order_ID))
                sourceOrderId = parameter.getParameterAsInt();
            if (para.equals(I_C_Order.COLUMNNAME_Bill_BPartner_ID))
                billPartnerId =  parameter.getParameterAsInt();
        }
    }

    @Override
    protected String doIt() throws Exception {
        if (sourceOrderId <= 0)
            throw new AdempiereException("@C_Order_ID@ @NotFound@");

        today = new Timestamp(System.currentTimeMillis());

        // Get Order
        MOrder sourceOrder = new MOrder(getCtx(), sourceOrderId, get_TrxName());
        // Get Invoices for ths order
        MInOut[] shipments = sourceOrder.getShipments();
        // If not exist invoice then only is necessary reverse shipment
        if (shipments.length > 0) {
            // Validate if partner not is POS partner standard then reverse shipment
            if (sourceOrder.getC_BPartner_ID() != billPartnerId)
                cancelShipments(shipments);

        }
        MInvoice[] invoices = sourceOrder.getInvoices();
        if(invoices.length > 0)
        {
            if (sourceOrder.getC_BPartner_ID() != billPartnerId)
                cancelInvoices(invoices);
        }

        //Cancel original payment
        cancelPayments(sourceOrder);
        return "@Ok@";
    }

    private void cancelShipments( MInOut[] sourceShipments)
    {
        for (MInOut sourceShipment : sourceShipments) {
            MRMA rma = new MRMA(getCtx(), 0 , get_TrxName());
            rma.setM_InOut_ID(sourceShipment.getM_InOut_ID());
            rma.setAD_Org_ID(sourceShipment.getAD_Org_ID());
            rma.setM_RMAType_ID(getRMATypeId());
            rma.setC_BPartner_ID(sourceShipment.getAD_Client_ID());
            rma.setC_DocType_ID(MDocType.getDocTypeBaseOnSubType(sourceShipment.getAD_Org_ID() , MDocType.DOCBASETYPE_SalesOrder , MDocType.DOCSUBTYPESO_ReturnMaterial));
            rma.setDocStatus(DocAction.STATUS_Drafted);
            rma.setDocAction(DocAction.ACTION_Complete);
            rma.saveEx();

            MInOut customerReturn = new MInOut(getCtx() , 0 , get_TrxName());
            PO.copyValues(sourceShipment, customerReturn);
            customerReturn.setM_RMA_ID(rma.getM_RMA_ID());
            customerReturn.setIsSOTrx(true);
            customerReturn.setC_DocType_ID(MDocType.getDocType(MDocType.DOCBASETYPE_MaterialReceipt));
            customerReturn.saveEx();

            for (MInOutLine sourceShimentLine : sourceShipment.getLines())
            {
                MRMALine rmaLine = new MRMALine(getCtx() , 0 , get_TrxName());
                rmaLine.setAD_Org_ID(sourceShimentLine.getAD_Org_ID());
                rmaLine.setM_InOutLine_ID(sourceShimentLine.getM_InOutLine_ID());
                rmaLine.setQty(sourceShimentLine.getMovementQty());
                rmaLine.saveEx();

                MInOutLine customerReturnLine = new MInOutLine(getCtx() , 0 , get_TrxName());
                customerReturnLine.setM_RMALine_ID(rmaLine.getM_RMALine_ID());
                customerReturnLine.setM_Product_ID(rmaLine.getM_Product_ID());
                customerReturnLine.setMovementQty(rmaLine.getQty());
                customerReturnLine.saveEx();
            }

            rma.processIt(DocAction.ACTION_Complete);
            rma.saveEx();

            customerReturn.processIt(DocAction.STATUS_Completed);
            customerReturn.saveEx();
        }
    }

    private void cancelInvoices( MInvoice[] sourceInvoices )
    {;
        for (MInvoice sourceInvoice : sourceInvoices)
        {
            MInvoice creditNote =  new MInvoice(getCtx() , 0 , get_TrxName());
            PO.copyValues(sourceInvoice , creditNote);
            creditNote.setC_DocTypeTarget_ID(MDocType.getDocType(MDocType.DOCBASETYPE_ARCreditMemo, sourceInvoice.getAD_Org_ID()));
            creditNote.setDateInvoiced(today);
            creditNote.setDateAcct(today);
            creditNote.setDocStatus(DocAction.STATUS_Drafted);
            creditNote.setDocAction(DocAction.ACTION_Complete);
            creditNote.saveEx();
            for (MInvoiceLine sourceInvoiceLine :  sourceInvoice.getLines())
            {
                MInvoiceLine creditNoteLine = new MInvoiceLine(getCtx() , 0 , get_TrxName());
                PO.copyValues(sourceInvoiceLine, creditNoteLine);
                creditNoteLine.setProcessed(false);
                creditNoteLine.saveEx();
            }

            creditNote.processIt(DocAction.ACTION_Complete);
            creditNote.saveEx();
        }

    }

    private void cancelPayments(MOrder sourceOrder)
    {
        List<MPayment> payments = getPayment(sourceOrder.get_ID());
        for (MPayment sourcePayment : payments)
        {
            MPayment payment = new MPayment(getCtx() ,  0 , get_TrxName());
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
        }
    }

    private List<MPayment> getPayment(int orderId)
    {
        return new Query(getCtx() , MPayment.Table_Name , MOrder.COLUMNNAME_C_Order_ID + "=?", get_TrxName())
                .setClient_ID()
                .setParameters(orderId)
                .list();
    }

    public int getRMATypeId() {
        return new Query(getCtx(), X_M_RMAType.Table_Name , null , get_TrxName()).setClient_ID().firstIdOnly();
    }
}
