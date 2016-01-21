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
import org.compiere.model.MProcess;
import org.compiere.model.X_C_DocType;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfo;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.eevolution.service.dsl.ProcessBuilder;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Class that execute business logic from POS
 * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 29/12/15.
 */
public class POSActionMenu extends JPopupMenu implements  ActionListener , POSQueryListener{

    private VPOS pos;
    private Integer processId;
    private Integer partnerId;
    private POSQuery queryPartner;
    private Waiting waiting;

    static private String GENERATE_IMMEDIATE_INVOICE =  "C_POS Generate Immediate Invoice";
    static private String GENERATE_REVERSE_SALES_REVERSE = "C_POS ReverseTheSalesTransaction";
    static private String GENERATE_RETURN = "C_POS CreateOrderBasedOnAnother" ;

    private HashMap<String , ActionProcess> actionProcessList = new HashMap<String , ActionProcess>(){
        {
            put(GENERATE_IMMEDIATE_INVOICE, new ActionProcess(null, GENERATE_IMMEDIATE_INVOICE, null));
            put(GENERATE_REVERSE_SALES_REVERSE, new ActionProcess(null, GENERATE_REVERSE_SALES_REVERSE, null));
            put(GENERATE_RETURN, new ActionProcess(null, GENERATE_RETURN, "Crear Devolución Parcial"));
        }
    };

    class ActionProcess {

        protected Optional<Integer> processId;
        protected String value;
        protected String name;
        protected Optional<String> alias;

        ActionProcess(Integer processId , String value , String alias)
        {
            this.value = value;
            this.processId =  Optional.ofNullable(processId);
            this.alias = Optional.ofNullable(alias);
            if (value != null && value.length() > 0 ) {
                this.processId = Optional.of(MProcess.getProcess_ID(value, null));
                if (this.processId.isPresent())
                    this.name = MProcess.get(Env.getCtx(), this.processId.get()).getName();
                else
                    throw new AdempierePOSException("@AD_Process_ID@ @NotFound@");
            }
        }
    }

    private ActionProcess getActionProcess(String actionCommand)
    {
        return actionProcessList.values()
                .stream()
                .filter(actionProcess -> actionProcess.alias.orElse(actionProcess.name) == actionCommand)
                .findFirst()
                .orElseThrow(() -> new AdempierePOSException("@AD_Process_ID@ @NotFound@"));
    }

    public POSActionMenu(VPOS pos)
    {
        this.pos = pos;
        this.waiting = new Waiting(pos.getFrame(),"@Processing@",false, 120);
        waiting.setVisible(false);
        for (Map.Entry<String, ActionProcess> actionProcess : actionProcessList.entrySet())
            addActionProcess(actionProcess.getValue());

    }

    private void addActionProcess(ActionProcess processAction)
    {
        JMenuItem menuItem =  new JMenuItem(processAction.alias.orElse(processAction.name));
        add(menuItem).addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        beforeExecutionProcess(getActionProcess(e.getActionCommand()));
    }

    private void beforeExecutionProcess(ActionProcess actionProcess)
    {
        if (actionProcess.value == GENERATE_IMMEDIATE_INVOICE)
        {
            queryPartner = new QueryBPartner(pos);
            queryPartner.setModal(true);
            queryPartner.addOptionListener(this);
            queryPartner.showView();
            return;
        }
        if (actionProcess.value == GENERATE_REVERSE_SALES_REVERSE)
        {
            executeProcess(actionProcess);
            return;
        }

        if (actionProcess.value == GENERATE_RETURN)
        {
            executeProcess(actionProcess);
            return;
        }
    }

    private void afterExecutionProcess(ActionProcess actionProcess)
    {
    }

    private void executeProcess(ActionProcess actionProcess) throws AdempierePOSException
    {
        if (actionProcess.value == GENERATE_IMMEDIATE_INVOICE &&  pos.getC_Order_ID() > 0) {
            partnerId = queryPartner.getRecord_ID();
            MBPartner partner = MBPartner.get(pos.getCtx(), partnerId);
            Optional<String> taxId = Optional.ofNullable(partner.getTaxID());
            String processMessage = actionProcess.name
                    + " @DisplayDocumentInfo@ : " + pos.getDocumentNo()
                    + " @To@ @C_BPartner_ID@ : " + partner.getName()
                    + " @TaxID@ : " + taxId.orElse("");
            if (ADialog.ask(pos.getWindowNo(), this, "StartProcess?", Msg.parseTranslation(pos.getCtx(), processMessage))) {
                waiting.setVisible(true);
                ProcessInfo processInfo = ProcessBuilder.
                        create(pos.getCtx()).process(actionProcess.processId.get())
                        .withTitle(actionProcess.alias.get())
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
                    ADialog.info(pos.getWindowNo(), this, "ProcessOK", actionProcess.alias.get() + " " + processInfo.getSummary());
                    afterExecutionProcess(actionProcess);
                    pos.setOrder(processInfo.getRecord_ID());
                    pos.refreshHeader();
                    pos.refreshPanel();
                    return;
                }
            }
        }
        //Reverse The Sales Transaction
        if (actionProcess.value == GENERATE_REVERSE_SALES_REVERSE && pos.getC_Order_ID() > 0)
        {
            String processMessage = actionProcess.name
                    + " @DisplayDocumentInfo@ : " + pos.getDocumentNo()
                    + " @To@ @C_BPartner_ID@ : " + pos.getBPName();

            if (ADialog.ask(pos.getWindowNo(), this, "StartProcess?", Msg.parseTranslation(pos.getCtx(), processMessage))) {
                waiting.setVisible(true);
                //Reverse The Sales Transaction for Source Order
                ProcessInfo processInfo = ProcessBuilder
                        .create(pos.getCtx())
                        .process(actionProcess.processId.get())
                        .withTitle("Reverse The Sales Transaction")
                        .withParameter(I_C_Order.COLUMNNAME_C_Order_ID , pos.getC_Order_ID())
                        .withParameter(I_C_Order.COLUMNNAME_Bill_BPartner_ID , pos.getC_BPartner_ID())
                        .withParameter("IsShipConfirm", true)
                        .execute();
                waiting.setVisible(false);
                if (processInfo.isError()) {
                    String errorMessage = Msg.parseTranslation(pos.getCtx() , processInfo.getTitle() + " @ProcessRunError@ " + processInfo.getSummary());
                    throw new AdempierePOSException(errorMessage);
                }
                else
                {
                    ADialog.info(pos.getWindowNo(), this ,"ProcessOK", actionProcess.alias + " " + processInfo.getSummary());
                    afterExecutionProcess(actionProcess);
                    pos.refreshHeader();
                    pos.refreshPanel();
                    return;
                }
            }
        }
        //Return product
        if (actionProcess.value == GENERATE_RETURN  &&  pos.getC_Order_ID() > 0)
        {
            String processMessage = actionProcess.name
                    + " @DisplayDocumentInfo@ : " + pos.getDocumentNo()
                    + " @To@ @C_BPartner_ID@ : " + pos.getBPName();

            if (ADialog.ask(pos.getWindowNo(), this, "StartProcess?", Msg.parseTranslation(pos.getCtx(), processMessage))) {
                AEnv.showCenterScreen(waiting);
                waiting.setVisible(true);
                //Create partial return
                ProcessInfo processInfo = ProcessBuilder
                        .create(pos.getCtx())
                        .process(actionProcess.processId.get())
                        .withTitle(actionProcess.name)
                        .withParameter(I_C_Order.COLUMNNAME_C_Order_ID , pos.getC_Order_ID())
                        .withParameter(I_C_Order.COLUMNNAME_Bill_BPartner_ID , pos.getC_BPartner_ID())
                        .withParameter(I_C_DocType.COLUMNNAME_DocSubTypeSO , MDocType.DOCSUBTYPESO_ReturnMaterial)
                        .withParameter(I_C_Order.COLUMNNAME_DocAction, DocAction.ACTION_None)
                        .withParameter("IsIncludePayments", true)
                        .withParameter(I_C_Payment.COLUMNNAME_IsAllocated, false)
                        .execute();
                waiting.setVisible(false);
                if (processInfo.isError()) {
                    String errorMessage = Msg.parseTranslation(pos.getCtx() , processInfo.getTitle() + " @ProcessRunError@ " + processInfo.getSummary());
                    throw new AdempierePOSException(errorMessage);
                }
                else
                {
                    ADialog.info(pos.getWindowNo(), this ,"ProcessOK", actionProcess.name + " " + processInfo.getSummary());
                    afterExecutionProcess(actionProcess);
                    pos.setOrder(processInfo.getRecord_ID());
                    pos.refreshHeader();
                    pos.refreshPanel();
                    return;
                }
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
}
