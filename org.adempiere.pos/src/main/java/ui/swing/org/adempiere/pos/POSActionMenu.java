package org.adempiere.pos;

import org.adempiere.pos.search.POSQuery;
import org.adempiere.pos.search.QueryBPartner;
import org.adempiere.pos.service.I_POSQuery;
import org.adempiere.pos.service.POSQueryListener;
import org.compiere.model.*;
import org.compiere.process.ProcessInfo;
import org.compiere.util.Env;
import org.eevolution.service.dsl.ProcessBuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

/**
 * Created by e-Evolution on 29/12/15.
 */
public class POSActionMenu extends JPopupMenu implements  ActionListener , POSQueryListener{

    private VPOS pos;
    private Integer processId;
    private Integer partnerId;

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
            POSQuery query = new QueryBPartner(pos);
            query.setModal(true);
            query.addOptionListener(this);
            query.showView();
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
            ProcessInfo processInfo = ProcessBuilder.
                    create(pos.getCtx()).process("C_POS Generate Immediate Invoice")
                    .withTitle("Generate Immediate Invoice")
                    .withParameter("C_Order_ID", pos.getC_Order_ID())
                    .withParameter("DocSubTypeSO", X_C_DocType.DOCSUBTYPESO_OnCreditOrder)
                    .withParameter("IsIncludePayments",true)
                    .withParameter("IsAllocated",true)
                    .withParameter("IsShipConfirm", true)
                    .withParameter("Bill_BPartner_ID", partnerId)
                    .execute();

            if (processInfo.isError())
                throw new AdempierePOSException("@C_Order_ID@");

            afterExecutionProcess();
            return;
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
