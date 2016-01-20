package org.adempiere.pos.process;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.*;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.ASyncProcess;
import org.eevolution.service.dsl.ProcessBuilder;

import java.sql.Timestamp;

/**
 * Created by e-Evolution on 28/12/15.
 */
public class GenerateImmediateInvoice extends SvrProcess implements ASyncProcess {
    private String  docSubTypeSO;
    private boolean isIncludePayments;
    private boolean isAllocated;
    private MOrder sourceOrder;
    private Integer billPartnerId;
    private boolean isShipConfirm = false;

    @Override
    protected void prepare() {

        ProcessInfoParameter[] params = getParameter();
        for (ProcessInfoParameter parameter : params) {
            String para = parameter.getParameterName();
            if (para.equals(I_C_Order.COLUMNNAME_C_Order_ID)) {
                sourceOrder = new MOrder(getCtx() , parameter.getParameterAsInt() , get_TrxName());
                if (DocAction.ACTION_Close.equals(sourceOrder.getDocStatus()))
                    throw new AdempiereException("@ProcessSubmitError@ @C_Order_ID@ : " + sourceOrder.getDocumentNo() + " @document.status.closed@ ");
            }
            if (para.equals(I_C_DocType.COLUMNNAME_DocSubTypeSO))
                docSubTypeSO = parameter.getParameterAsString();
            if (para.equals("IsIncludePayments"))
                isIncludePayments = parameter.getParameterAsBoolean();
            if (para.equals(I_C_Payment.COLUMNNAME_IsAllocated))
                isAllocated = parameter.getParameterAsBoolean();
            if (para.equals(I_C_Order.COLUMNNAME_Bill_BPartner_ID))
                billPartnerId =  parameter.getParameterAsInt();
            if ("IsShipConfirm".equals(para))
                isShipConfirm = parameter.getParameterAsBoolean();
        }
    }

    @Override
    protected String doIt() throws Exception {
        if (sourceOrder == null || sourceOrder.get_ID() <= 0)
            throw new AdempiereException("@C_Order_ID@ @NotFound@");


        //Create new Order for current date and Bill Partner
        ProcessInfo processInfo = ProcessBuilder
                .create(getCtx())
                .process("C_POS CreateOrderBasedOnAnother")
                .withTitle("Create Order based on another")
                .withParentProcess(this)
                .withParameter(I_C_Order.COLUMNNAME_C_Order_ID , sourceOrder.get_ID())
                .withParameter(I_C_Order.COLUMNNAME_Bill_BPartner_ID , billPartnerId)
                .withParameter(I_C_DocType.COLUMNNAME_DocSubTypeSO , MDocType.DOCSUBTYPESO_OnCreditOrder)
                .withParameter(I_C_Order.COLUMNNAME_DocAction, DocAction.ACTION_Complete)
                .withParameter("IsIncludePayments", true)
                .withParameter(I_C_Payment.COLUMNNAME_IsAllocated, true)
                .withoutTransactionClose()
                .execute(get_TrxName());

        if (processInfo.isError())
            throw new AdempiereException(processInfo.getTitle()  +  " @ProcessRunError@ " + processInfo.getSummary());

        int newOrderId = processInfo.getRecord_ID();
        //Reverse The Sales Transaction for Source Order
        processInfo = ProcessBuilder
                .create(getCtx())
                .process("C_POS ReverseTheSalesTransaction")
                .withTitle("Reverse The Sales Transaction")
                .withParentProcess(this)
                .withParameter(I_C_Order.COLUMNNAME_C_Order_ID , sourceOrder.get_ID())
                .withParameter(I_C_Order.COLUMNNAME_Bill_BPartner_ID , billPartnerId)
                .withParameter("IsShipConfirm", isShipConfirm)
                .withoutTransactionClose()
                .execute(get_TrxName());

        if (processInfo.isError())
            throw new AdempiereException(processInfo.getTitle()  +  " @ProcessRunError@ " + processInfo.getSummary());

        //sourceOrder.processIt(DocAction.ACTION_Close);
        //sourceOrder.saveEx(get_TrxName());

        getProcessInfo().setRecord_ID(newOrderId);
        return "@Ok@";
    }

    @Override
    public void lockUI(ProcessInfo pi) {

    }

    @Override
    public void unlockUI(ProcessInfo pi) {

    }

    @Override
    public boolean isUILocked() {
        return false;
    }

    @Override
    public void executeASync(ProcessInfo pi) {

    }
}
