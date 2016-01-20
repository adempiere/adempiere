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
import org.adempiere.pos.search.QueryTicket;
import org.adempiere.pos.service.I_POSQuery;
import org.adempiere.pos.service.POSQueryListener;
import org.compiere.apps.ADialog;
import org.compiere.model.*;
import org.compiere.process.ProcessInfo;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.eevolution.service.dsl.ProcessBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
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
    private POSQuery queryTicket;

    public POSActionMenu(VPOS pos)
    {
        this.pos = pos;
        addActionProcess("C_POS Generate Immediate Invoice");
    }

    private Hashtable<String,Integer> processList = new Hashtable<>();

    private void addActionProcess(String value)
    {
        Integer processId = MProcess.getProcess_ID(value,null);
        if (processId == null || processId <= 0)
            return;

        MProcess process = MProcess.get(pos.getCtx(), processId);
        processList.put(process.getName(), processId);
        JMenuItem menuItem =  new JMenuItem(process.getName());
        add(menuItem).addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);

        if (processList.containsKey(e.getActionCommand()))
            processId = processList.get(e.getActionCommand());

        beforeExecutionProcess();
    }

    private void beforeExecutionProcess()
    {
        if (processList.get("Generate Immediate Invoice") == processId)
        {
            queryPartner = new QueryBPartner(pos);
            queryPartner.setModal(true);
            queryPartner.addOptionListener(this);
            queryPartner.showView();
            return;
        }
    }

    private void afterExecutionProcess()
    {
        if (processList.get("Generate Immediate Invoice") == processId)
        {
        }
    }

    private void executeProcess()
    {
        if (processList.get("Generate Immediate Invoice") == processId
        &&  pos.getC_Order_ID() > 0
        &&  pos.getC_Order_ID() > 0 )
        {
            MProcess process = MProcess.get(pos.getCtx(), processId);
            partnerId = queryPartner.getRecord_ID();
            MBPartner partner = MBPartner.get(pos.getCtx(), partnerId);
            Optional<String> taxId = Optional.ofNullable(partner.getTaxID());
            String processMessage = process.getName()
                    + " @DisplayDocumentInfo@ : " + pos.getDocumentNo()
                    + " @To@ @C_BPartner_ID@ : " + partner.getName()
                    + " @TaxID@ : " + taxId.orElse("");
            if (ADialog.ask(pos.getWindowNo(), this, "StartProcess?", Msg.parseTranslation(pos.getCtx(), processMessage))) {

                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                ProcessInfo processInfo = ProcessBuilder.
                        create(pos.getCtx()).process(processId)
                        .withTitle(process.getName())
                        .withParameter("C_Order_ID", pos.getC_Order_ID())
                        .withParameter("DocSubTypeSO", X_C_DocType.DOCSUBTYPESO_OnCreditOrder)
                        .withParameter("IsIncludePayments", true)
                        .withParameter("IsAllocated", true)
                        .withParameter("IsShipConfirm", true)
                        .withParameter("Bill_BPartner_ID", partnerId)
                        .execute();
                setCursor(Cursor.getDefaultCursor());
                if (processInfo.isError()) {
                    String errorMessage = Msg.parseTranslation(pos.getCtx() , processInfo.getTitle() + " @ProcessRunError@ " + processInfo.getSummary());
                    throw new AdempierePOSException(errorMessage);
                }
                else
                {
                    ADialog.info(pos.getWindowNo(), this ,"ProcessOK", process.getName() + " " + processInfo.getSummary());
                    afterExecutionProcess();
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
          executeProcess();
        }
    }

    @Override
    public void cancelAction(I_POSQuery query) {

    }
}
