/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2003-2015 e-Evolution Consultants. All Rights Reserved.      *
 * Copyright (C) 2003-2015 Victor Pérez Juárez 								  *
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
 * Contributor(s): Victor Pérez Juárez  (victor.perez@e-evolution.com)		  *
 * Sponsors: e-Evolution Consultants (http://www.e-evolution.com/)            *
 *****************************************************************************/

package org.eevolution.process;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.apps.ProcessCtl;
import org.compiere.model.I_M_Movement;
import org.compiere.model.MLocator;
import org.compiere.model.MMovement;
import org.compiere.model.MPInstance;
import org.compiere.model.MPInstancePara;
import org.compiere.model.MProcess;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.eevolution.form.IPrintDocument;
import org.eevolution.model.I_DD_Order;
import org.eevolution.model.MDDOrder;
import org.eevolution.model.MPPOrder;

import java.lang.reflect.Constructor;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Generate Movement
 * Manual or Automatic
 * Created by eEvolution author Victor Perez <victor.perez@e-evolution.com> on 25/01/15.
 */
public class GenerateMovement extends SvrProcess {

    protected List<MPPOrder> records = new ArrayList<>();
    private Timestamp movementDate = null;
    private MLocator locator = null;
    private String result = null;

    /**
     * Prepare - e.g., get Parameters.
     */
    protected void prepare() {
        for (ProcessInfoParameter para : getParameter()) {
            String name = para.getParameterName();
            if (para.getParameter() == null)
                ;
            else if (name.equals(I_M_Movement.COLUMNNAME_MovementDate)) {
                movementDate = para.getParameterAsTimestamp();
                if (movementDate == null)
                    throw new AdempiereException("@MovementDate@ @NotFound@");
            }
        }

        int locatorId = Env.getContextAsInt(Env.getCtx(), getProcessInfo().getWindowNo(), "mc.M_Locator_ID");
        if (locatorId > 0)
            locator = new MLocator(getCtx(), locatorId, get_TrxName());

        if (locator == null)
            throw new AdempiereException("@M_Locator_ID@ @NotFound@");
    }    //	prepare


    /**
     * Generate Movements
     *
     * @return info
     * @throws Exception
     */
    protected String doIt() throws Exception {
        for (MDDOrder order : getRecords())
            generate(order);

        return result;
    }    //	generate

    private List<MDDOrder> getRecords() {

        String whereClause = "EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE  T_Selection.AD_PInstance_ID=? AND T_Selection.T_Selection_ID=DD_Order.DD_Order_ID)";
        return new Query(getCtx(), I_DD_Order.Table_Name, whereClause,
                get_TrxName()).setClient_ID()
                .setParameters(getAD_PInstance_ID()).list();
    }

    private void generate(MDDOrder order) {
        String trxName = Trx.createTrxName("IMG");
        Trx trx = Trx.get(trxName, true);

        String info = "";
        //	Prepare Process
        int AD_Process_ID = MProcess.getProcess_ID("M_Generate Movement", null);

        MPInstance instance = new MPInstance(Env.getCtx(), AD_Process_ID, 0);
        if (!instance.save()) {
            info = Msg.getMsg(Env.getCtx(), "ProcessNoInstance");
            addLog(info);
        }
        List<Integer> ordersId = new ArrayList<Integer>();
        ordersId.add(order.getDD_Order_ID());

        DB.createT_Selection(instance.getAD_PInstance_ID(), ordersId, null);

        //call process
        ProcessInfo pi = new ProcessInfo("Generate Movement", AD_Process_ID);
        pi.setAD_PInstance_ID(instance.getAD_PInstance_ID());

        //	Add Parameter - Selection=Y
        MPInstancePara ip = new MPInstancePara(instance, 10);
        ip.setParameter("Selection", "Y");
        ip.saveEx();

        //	Add Parameter - M_Warehouse_ID=x
        ip = new MPInstancePara(instance, 20);
        ip.setParameter("M_Warehouse_ID", locator.getM_Warehouse_ID());
        ip.saveEx();

        ip = new MPInstancePara(instance, 30);
        ip.setParameter("MovementDate", movementDate);
        ip.saveEx();

        ProcessCtl worker = new ProcessCtl(null, pi.getWindowNo(), pi, trx);
        worker.run();

        result = pi.getSummary();

        MMovement movement = new MMovement(getCtx(), pi.getRecord_ID(), trxName);
        if (movement != null && movement.get_ID() > 0)
            printDocument(movement, "Inventory Move Hdr (Example)", pi.getWindowNo());
        else
            throw new AdempiereException("@M_Movement_ID@ @NotFound@");
    }


    /**
     * Print Document
     *
     * @param document
     * @param printFormantName
     */
    private void printDocument(PO document, String printFormantName, int documentNo) {
        //  Switch Tabs
        StringBuffer resultText = new StringBuffer(Msg.translate(Env.getCtx(), "DocumentNo") + " : " + document.get_ValueAsString("DocumentNo"));
        result = result == null ? " " + resultText.toString() : result + " " + resultText.toString();

        IPrintDocument IPrintDocument;

        //	OK to print shipments
        if (Ini.isClient())
            IPrintDocument = getPrintDocument("org.eevolution.form.VPrintDocument");
        else
            IPrintDocument = getPrintDocument("org.eevolution.form.WPrintDocument");

        IPrintDocument.print(document, printFormantName, documentNo);
    }

    public IPrintDocument getPrintDocument(String className) throws RuntimeException {
        Class<?> clazz;
        IPrintDocument result = null;
        try {
            clazz = Class.forName(className);
            Constructor<?> constructor = null;
            constructor = clazz.getDeclaredConstructor();
            result = (IPrintDocument) constructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}    //	MovementGenerate
