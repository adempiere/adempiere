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


package org.adempiere.pos;

import org.adempiere.pos.search.POSQuery;
import org.adempiere.pos.search.QueryBPartner;
import org.adempiere.pos.service.I_POSQuery;
import org.adempiere.pos.service.POSQueryListener;
import org.compiere.apps.ADialog;
import org.compiere.apps.AEnv;
import org.compiere.apps.Waiting;
import org.compiere.model.I_C_DocType;
import org.compiere.model.I_C_Order;
import org.compiere.model.I_C_Payment;
import org.compiere.model.MBPartner;
import org.compiere.model.MDocType;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutConfirm;
import org.compiere.model.MInOutLineConfirm;
import org.compiere.model.MOrder;
import org.compiere.model.MProcess;
import org.compiere.model.X_C_DocType;
import org.compiere.print.ReportCtl;
import org.compiere.print.ReportEngine;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfo;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
import org.eevolution.service.dsl.ProcessBuilder;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Class that execute business logic from POS
 * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 29/12/15.
 */
public class POSActionMenu extends JPopupMenu implements  ActionListener , POSQueryListener{

    private VPOS pos;
    private Integer partnerId;
    private POSQuery queryPartner;

    static private String GENERATE_IMMEDIATE_INVOICE =  "C_POS Generate Immediate Invoice";
    static private String GENERATE_REVERSE_SALES = "C_POS ReverseTheSalesTransaction";
    static private String GENERATE_RETURN = "C_POS CreateOrderBasedOnAnother";
    static private String COMPLETE_DOCUMENT = "";

    private HashMap<String , ActionProcess> actionProcessList = new HashMap<String , ActionProcess>(){
        {
            put(GENERATE_IMMEDIATE_INVOICE, new ActionProcess(null, GENERATE_IMMEDIATE_INVOICE, null));
            put(GENERATE_REVERSE_SALES, new ActionProcess(null, GENERATE_REVERSE_SALES, null));
            put(GENERATE_RETURN, new ActionProcess(null, GENERATE_RETURN, "Crear Devolución Parcial sin @M_RMA_ID@"));
            put(COMPLETE_DOCUMENT, new ActionProcess(null, COMPLETE_DOCUMENT, "@smenu.complete.prepared.order@"));
        }
    };

    class ActionProcess {

        private Optional<Integer> processId;
        private String value;
        private String name;
        private Optional<String> alias;

        ActionProcess(Integer processId , String value , String alias)
        {
            this.setValue(value);
            this.setProcessId(Optional.ofNullable(processId));
            this.setAlias(Optional.ofNullable(Msg.parseTranslation(Env.getCtx() , alias)));
            if (value != null && value.length() > 0 ) {
                this.setProcessId(Optional.of(MProcess.getProcess_ID(value, null)));
                if (this.getProcessId().isPresent())
                    this.setName(MProcess.get(Env.getCtx(), this.getProcessId().get()).getName());
                else
                    throw new AdempierePOSException("@AD_Process_ID@ @NotFound@");
            }
           if (alias != null && alias.length() > 0 ) this.setName(alias);
        }

        public Optional<Integer> getProcessId() {
            return processId;
        }

        public void setProcessId(Optional<Integer> processId) {
            this.processId = processId;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Optional<String> getAlias() {
            return alias;
        }

        public void setAlias(Optional<String> alias) {
            this.alias = alias;
        }
    }

    private ActionProcess getActionProcess(String actionCommand)
    {
        return actionProcessList.values()
                .stream()
                .filter(actionProcess -> actionProcess.getAlias().orElse(actionProcess.getName()) == actionCommand)
                .findFirst()
                .orElseThrow(() -> new AdempierePOSException("@AD_Process_ID@ @NotFound@"));
    }

    public POSActionMenu(VPOS pos)
    {
        this.pos = pos;
        Waiting waiting = new Waiting(pos.getFrame(),"@Processing@",false, 120);
        waiting.setVisible(false);
        for (Map.Entry<String, ActionProcess> actionProcess : actionProcessList.entrySet())
            addActionProcess(actionProcess.getValue());

    }

    private void addActionProcess(ActionProcess processAction)
    {
        JMenuItem menuItem =  new JMenuItem(processAction.getAlias().orElse(processAction.getName()));
        add(menuItem).addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        beforeExecutionProcess(getActionProcess(e.getActionCommand()));
    }

    private void beforeExecutionProcess(ActionProcess actionProcess) throws AdempierePOSException
    {
        if (actionProcess.getValue() == GENERATE_IMMEDIATE_INVOICE)
        {
            queryPartner = new QueryBPartner(pos);
            queryPartner.setModal(true);
            queryPartner.addOptionListener(this);
            queryPartner.showView();
            return;
        }
        if (actionProcess.getValue() == GENERATE_REVERSE_SALES)
        {
            executeProcess(actionProcess);
            return;
        }

        if (actionProcess.getValue() == GENERATE_RETURN)
        {
            executeProcess(actionProcess);
            return;
        }

        if (actionProcess.getValue() == COMPLETE_DOCUMENT) {
            if (!pos.isCompleted()) {
                Trx.run(new TrxRunnable() {
                    public void run(String trxName) {
                        pos.processOrder(trxName, false, false);
                        pos.refreshHeader();
                    }
                });
                executeProcess(actionProcess);
            }
            else
                ADialog.info(pos.getWindowNo(), this, "DocProcessed", pos.getDocumentNo());
        }
    }

    private void afterExecutionProcess(ActionProcess actionProcess) throws AdempierePOSException
    {
    }

    private void executeProcess(ActionProcess actionProcess) throws AdempierePOSException
    {
        if (actionProcess.getValue() == GENERATE_IMMEDIATE_INVOICE &&  pos.getC_Order_ID() > 0) {
            partnerId = queryPartner.getRecord_ID();
            MBPartner partner = MBPartner.get(pos.getCtx(), partnerId);
            Optional<String> taxId = Optional.ofNullable(partner.getTaxID());
            String processMessage = actionProcess.getName()
                    + " @DisplayDocumentInfo@ : " + pos.getDocumentNo()
                    + " @To@ @C_BPartner_ID@ : " + partner.getName()
                    + " @TaxID@ : " + taxId.orElse("");
            if (ADialog.ask(pos.getWindowNo(), this, "StartProcess?", Msg.parseTranslation(pos.getCtx(), processMessage))) {
                Waiting waiting = new Waiting(pos.getFrame(),"@Processing@",false, 120);
                AEnv.showCenterScreen(waiting);
                ProcessInfo processInfo = ProcessBuilder.
                        create(pos.getCtx()).process(actionProcess.getProcessId().get())
                        .withTitle(actionProcess.getAlias().get())
                        .withParameter("C_Order_ID", pos.getC_Order_ID())
                        .withParameter("DocSubTypeSO", X_C_DocType.DOCSUBTYPESO_OnCreditOrder)
                        .withParameter("IsIncludePayments", true)
                        .withParameter("IsAllocated", true)
                        .withParameter("IsShipConfirm", true)
                        .withParameter("Bill_BPartner_ID", partnerId)
                        .execute();
                waiting.setVisible(false);
                if (processInfo.isError()) {
                    String errorMessage = Msg.parseTranslation(pos.getCtx(), processInfo.getTitle() + " @ProcessRunError@ " + processInfo.getSummary());
                    throw new AdempierePOSException(errorMessage);
                } else {
                    afterExecutionProcess(actionProcess);
                    pos.setOrder(processInfo.getRecord_ID());
                    showOkMessage(processInfo);
                    return;
                }
            }
        }
        //Reverse The Sales Transaction
        if (actionProcess.getValue() == GENERATE_REVERSE_SALES && pos.getC_Order_ID() > 0)
        {
            String processMessage = actionProcess.getName()
                    + " @DisplayDocumentInfo@ : " + pos.getDocumentNo()
                    + " @To@ @C_BPartner_ID@ : " + pos.getBPName();

            if (ADialog.ask(pos.getWindowNo(), this, "StartProcess?", Msg.parseTranslation(pos.getCtx(), processMessage))) {
                Waiting waiting = new Waiting(pos.getFrame(),"@Processing@",false, 120);
                AEnv.showCenterScreen(waiting);
                //Reverse The Sales Transaction for Source Order
                ProcessInfo processInfo = ProcessBuilder
                        .create(pos.getCtx())
                        .process(actionProcess.getProcessId().get())
                        .withTitle("Reverse The Sales Transaction")
                        .withParameter(I_C_Order.COLUMNNAME_C_Order_ID , pos.getC_Order_ID())
                        .withParameter(I_C_Order.COLUMNNAME_Bill_BPartner_ID , pos.getC_BPartner_ID())
                        .withParameter("IsShipConfirm", true)
                        .execute();
                waiting.setVisible(false);
                if (processInfo.isError()) {
                    showError(processInfo);
                }
                else
                {
                    afterExecutionProcess(actionProcess);
                    showOkMessage(processInfo);
                    return;
                }
            }
        }
        //Return product
        if (actionProcess.getValue() == GENERATE_RETURN  &&  pos.getC_Order_ID() > 0 && !pos.isReturnMaterial())
        {
            String processMessage = actionProcess.getName()
                    + " @DisplayDocumentInfo@ : " + pos.getDocumentNo()
                    + " @To@ @C_BPartner_ID@ : " + pos.getBPName();

            if (ADialog.ask(pos.getWindowNo(), this, "StartProcess?", Msg.parseTranslation(pos.getCtx(), processMessage))) {
                Waiting waiting = new Waiting(pos.getFrame(),"@Processing@",false, 120);
                AEnv.showCenterScreen(waiting);
                //Create partial return
                ProcessInfo processInfo = ProcessBuilder
                        .create(pos.getCtx())
                        .process(actionProcess.getProcessId().get())
                        .withTitle(actionProcess.getName())
                        .withParameter(I_C_Order.COLUMNNAME_C_Order_ID , pos.getC_Order_ID())
                        .withParameter(I_C_Order.COLUMNNAME_Bill_BPartner_ID , pos.getC_BPartner_ID())
                        .withParameter(I_C_DocType.COLUMNNAME_DocSubTypeSO , MDocType.DOCSUBTYPESO_ReturnMaterial)
                        .withParameter(I_C_Order.COLUMNNAME_DocAction, DocAction.ACTION_None)
                        .withParameter("IsIncludePayments", true)
                        .withParameter(I_C_Payment.COLUMNNAME_IsAllocated, false)
                        .execute();

                waiting.setVisible(false);
                if (processInfo.isError()) {
                    showError(processInfo);
                }
                else
                {
                    afterExecutionProcess(actionProcess);
                    pos.setOrder(processInfo.getRecord_ID());
                    showOkMessage(processInfo);
                    return;
                }
            }
        }
        if (actionProcess.getValue() == COMPLETE_DOCUMENT  &&  pos.getC_Order_ID() > 0)
        {
            if (pos.isReturnMaterial() && pos.isCompleted())
            {
                Trx.run(new TrxRunnable() {
                    public void run(String trxName) {
                    Waiting waiting = new Waiting(pos.getFrame(),"@Processing@",false, 120);
                    AEnv.showCenterScreen(waiting);
                    MOrder returnOrder = new MOrder(pos.getCtx() , pos.getC_Order_ID() , trxName);
                    returnOrder.setInvoiceRule(MOrder.INVOICERULE_Immediate);
                    returnOrder.setDeliveryRule(MOrder.DELIVERYRULE_Force);
                    returnOrder.saveEx();
                    List<Integer> selectionIds = new ArrayList<Integer>();
                    selectionIds.add(returnOrder.get_ID());
                    //Generate Return using InOutGenerate
                    ProcessInfo processReturnInfo = ProcessBuilder
                            .create(pos.getCtx())
                            .process(199)
                            .withTitle(actionProcess.getName())
                            .withParameter(I_C_Order.COLUMNNAME_M_Warehouse_ID, pos.getM_Warehouse_ID())
                            .withParameter("Selection", true)
                            .withSelectedRecordsIds(selectionIds)
                            .withoutTransactionClose()
                            .execute(trxName);

                    if (processReturnInfo.isError()) {
                        waiting.setVisible(false);
                        showError(processReturnInfo);
                    }

                    //Force the confirmation
                    for (MInOut customerReturn :  returnOrder.getShipments()) {
                        customerReturn.processIt(DocAction.ACTION_Complete);
                        customerReturn.saveEx();

                        for (MInOutConfirm confirm : customerReturn.getConfirmations(true)) {
                            for (MInOutLineConfirm confirmLine : confirm.getLines(true)) {
                                confirmLine.setConfirmedQty(confirmLine.getTargetQty());
                                confirmLine.saveEx();
                            }
                            confirm.processIt(DocAction.ACTION_Complete);
                            confirm.saveEx();
                        }
                    }

                    //Generate Credit note InvoiceGenerate
                    ProcessInfo processCreditNoteInfo = ProcessBuilder
                            .create(pos.getCtx())
                            .process(134)
                            .withTitle(actionProcess.getName())
                            .withParameter("Selection", true)
                            .withSelectedRecordsIds(selectionIds)
                            .withoutTransactionClose()
                            .execute(trxName);

                    ReportCtl.startDocumentPrint(ReportEngine.INVOICE, pos.getC_Order_ID(), false);
                    waiting.setVisible(false);

                    if (processCreditNoteInfo.isError()) {
                       showError(processCreditNoteInfo);
                    }
                    afterExecutionProcess(actionProcess);
                    pos.setOrder(processCreditNoteInfo.getRecord_ID());
                    showOkMessage(processReturnInfo);
                    showOkMessage(processCreditNoteInfo);
                    }
                });
            }
        }
    }

    @Override
    public void okAction(I_POSQuery query) {
        if (query.getRecord_ID() <= 0)
            return;
        //	For Ticket
        if(query instanceof QueryBPartner) {
          partnerId = query.getRecord_ID();
        }
    }

    @Override
    public void cancelAction(I_POSQuery query) {

    }

    private void showError(ProcessInfo processInfo) throws AdempierePOSException
    {
        String errorMessage = Msg.parseTranslation(pos.getCtx() , processInfo.getTitle() + " @ProcessRunError@ " + processInfo.getSummary());
        throw new AdempierePOSException(errorMessage);
    }

    private void showOkMessage(ProcessInfo processInfo)
    {
        pos.refreshHeader();
        ADialog.info(pos.getWindowNo(), this ,"ProcessOK", processInfo.getTitle() + " " + processInfo.getSummary());

    }
}
