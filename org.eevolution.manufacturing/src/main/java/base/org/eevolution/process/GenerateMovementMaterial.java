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
import org.compiere.model.*;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.*;
import org.eevolution.form.Browser;
import org.eevolution.form.IPrintDocument;
import org.eevolution.model.I_DD_OrderLine;
import org.eevolution.model.MDDOrder;
import org.eevolution.model.MDDOrderLine;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Generate Movement Material
 * Created by eEvolution author Victor Perez <victor.perez@e-evolution.com> on 25/01/15.
 */
public class GenerateMovementMaterial extends SvrProcess {

    protected LinkedHashMap<Integer, LinkedHashMap<String, Object>> values = null;
    protected Timestamp movementDate;
    protected int orderId;
    protected MDDOrder distributionOrder;
    protected List<MDDOrderLine> distributionOrderLines;
    protected String result;

    /**
     *
     */
    protected void prepare() {
        // get parameters to process
        for (ProcessInfoParameter para : getParameter()) {
            String name = para.getParameterName();
            if (para.getParameter() == null)
                ;
            else if (I_M_Movement.COLUMNNAME_MovementDate.equals(name))
                movementDate = para.getParameterAsTimestamp();
        }

        int orderId = Env.getContextAsInt(getCtx(), getWindowNo(), "line.DD_Order_ID");
        if (orderId >= 0)
            distributionOrder = new MDDOrder(getCtx(), orderId, get_TrxName());
        if (distributionOrder == null)
            throw new AdempiereException("@DD_Order_ID@ @NotFound@");

        setColumnsValues();

    }    //	prepare

    /**
     * Execute of logic for this process
     *
     * @return
     * @throws Exception
     */
    protected String doIt() throws Exception {
        generateMovements();
        return result;
    }    //	doIt

    private List<MDDOrderLine> getRecords() {

        if (distributionOrderLines != null)
            return distributionOrderLines;

        String whereClause = "EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE  T_Selection.AD_PInstance_ID=? AND T_Selection.T_Selection_ID=DD_OrderLine.DD_OrderLine_ID)";
        distributionOrderLines = new Query(getCtx(), I_DD_OrderLine.Table_Name, whereClause,
                get_TrxName()).setClient_ID()
                .setParameters(getAD_PInstance_ID()).list();
        return distributionOrderLines;
    }

    private void generateMovements() {

        Trx.run(new TrxRunnable() {
                    public void run(String trxName) {
                        if (getRecords().size() <= 0)
                            return;

                        MMovement movement = new MMovement(getCtx(), 0, trxName);
                        movement.setDD_Order_ID(distributionOrder.getDD_Order_ID());
                        movement.setAD_User_ID(distributionOrder.getAD_User_ID());
                        movement.setPOReference(distributionOrder.getPOReference());
                        movement.setReversal_ID(0);
                        movement.setM_Shipper_ID(distributionOrder.getM_Shipper_ID());
                        movement.setDescription(distributionOrder.getDescription());
                        movement.setC_BPartner_ID(distributionOrder.getC_BPartner_ID());
                        movement.setC_BPartner_Location_ID(distributionOrder.getC_BPartner_Location_ID());
                        movement.setAD_Org_ID(distributionOrder.getAD_Org_ID());
                        movement.setAD_OrgTrx_ID(distributionOrder.getAD_OrgTrx_ID());
                        movement.setAD_User_ID(distributionOrder.getAD_User_ID());
                        movement.setC_Activity_ID(distributionOrder.getC_Activity_ID());
                        movement.setC_Campaign_ID(distributionOrder.getC_Campaign_ID());
                        movement.setC_Project_ID(distributionOrder.getC_Project_ID());
                        movement.setMovementDate(movementDate);
                        movement.setDeliveryRule(distributionOrder.getDeliveryRule());
                        movement.setDeliveryViaRule(distributionOrder.getDeliveryViaRule());
                        movement.setDocAction(MMovement.ACTION_Prepare);
                        movement.setDocStatus(MMovement.DOCSTATUS_Drafted);

                        //Look the document type for the organization
                        int docTypeDO_ID = MDocType.getDocType(MDocType.DOCBASETYPE_MaterialMovement, distributionOrder.getAD_Org_ID());
                        if (docTypeDO_ID > 0)
                            movement.setC_DocType_ID(docTypeDO_ID);
                        else
                            throw new AdempiereException("@C_DocType_ID@ @NotFound@");

                        movement.saveEx();

                        for (MDDOrderLine orderLine : getRecords()) {
                            MMovementLine line = new MMovementLine(movement);
                            line.setM_Product_ID(orderLine.getM_Product_ID());
                            BigDecimal QtyDeliver = (BigDecimal) getBrowseRowValue("LINE", I_DD_OrderLine.COLUMNNAME_QtyInTransit, orderLine.get_ID());
                            if (QtyDeliver == null | QtyDeliver.compareTo(orderLine.getQtyInTransit()) > 0)
                                throw new AdempiereException("Error @QtyInTransit@");

                            line.setOrderLine(orderLine, QtyDeliver, true);
                            line.saveEx();
                        }

                        movement.setDocAction(MMovement.DOCACTION_Close);
                        movement.setDocStatus(movement.completeIt());
                        movement.saveEx();
                        printDocument(movement, "Inventory Move Hdr (Example)");
                    }
                }
        );
    }    //	generateMovements

    private LinkedHashMap<Integer, LinkedHashMap<String, Object>> setColumnsValues() {
        if (values != null)
            return values;

        values = new LinkedHashMap<Integer, LinkedHashMap<String, Object>>();

        for (MDDOrderLine record : getRecords()) {
            values.put(
                    record.get_ID(),
                    Browser.getBrowseValues(getAD_PInstance_ID(), null,
                            record.get_ID(), null));
        }
        return values;
    }

    /**
     * get browse row value based on alias and column mane
     *
     * @param alias
     * @param columnName
     * @param recordId
     * @return
     */
    private Object getBrowseRowValue(String alias, String columnName, int recordId) {

        LinkedHashMap<String, Object> columns = values.get(recordId);

        for (Map.Entry<String, Object> entry : columns.entrySet()) {
            if (entry.getKey().contains(alias.toUpperCase() + "_" + columnName))
                return entry.getValue();
        }
        return null;
    }

    private int getWindowNo() {
        return getProcessInfo().getWindowNo();
    }

    /**
     * Print Document
     *
     * @param document
     * @param printFormantName
     */
    private void printDocument(PO document, String printFormantName) {
        //  Switch Tabs
        StringBuffer resultText = new StringBuffer(Msg.translate(Env.getCtx(), "DocumentNo") + " : " + document.get_ValueAsString("DocumentNo"));
        result = resultText.toString();

        IPrintDocument IPrintDocument;

        //	OK to print shipments
        if (Ini.isClient())
            IPrintDocument = getPrintDocument("org.eevolution.form.VPrintDocument");
        else
            IPrintDocument = getPrintDocument("org.eevolution.form.WPrintDocument");

        IPrintDocument.print(document, printFormantName, getWindowNo());
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

}
