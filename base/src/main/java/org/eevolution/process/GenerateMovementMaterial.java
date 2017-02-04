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
import org.compiere.model.MDocType;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Msg;
import org.eevolution.form.IPrintDocument;
import org.eevolution.model.MDDOrderLine;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.util.List;

/**
 * Generate Movement Material
 * Created by eEvolution author Victor Perez <victor.perez@e-evolution.com> on 25/01/15.
 */
public class GenerateMovementMaterial extends GenerateMovementMaterialAbstract {
    protected MMovement movement = null;
    protected String result;

    /**
     *
     */
    protected void prepare() {
        super.prepare();
    }

    /**
     * Execute of logic for this process
     *
     * @return
     * @throws Exception
     */
    protected String doIt() throws Exception {

        List<MDDOrderLine> orderLines = (List<MDDOrderLine>) getInstancesForSelection(get_TrxName());
        orderLines.stream().filter(orderLine -> orderLine != null).forEach( orderLine -> {
            createMovement(orderLine);
            MMovementLine line = new MMovementLine(movement);
            BigDecimal qtyDeliver = getSelectionAsBigDecimal(orderLine.get_ID(), "LINE_"+ MDDOrderLine.COLUMNNAME_QtyInTransit);
            if (qtyDeliver == null | qtyDeliver.compareTo(orderLine.getQtyInTransit()) > 0)
                throw new AdempiereException("Error @QtyInTransit@");

            line.setOrderLine(orderLine, qtyDeliver, true);
            line.saveEx();
        });

        movement.processIt(MMovement.DOCACTION_Complete);
        movement.saveEx();
        printDocument(movement, "Inventory Move Hdr (Example)");
        return result;
    }    //	generateMovements

    private void createMovement(MDDOrderLine orderLine) {
        if (movement != null && movement.get_ID() > 0)
            return;

        movement = new MMovement(orderLine.getParent() , getMovementDate());
        //Look the document type for the organization
        int docTypeDO_ID = MDocType.getDocType(MDocType.DOCBASETYPE_MaterialMovement, orderLine.getAD_Org_ID());
        if (docTypeDO_ID > 0)
            movement.setC_DocType_ID(docTypeDO_ID);
        else
            throw new AdempiereException("@C_DocType_ID@ @NotFound@");
        movement.saveEx();
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

        IPrintDocument.print(document, printFormantName, getProcessInfo().getWindowNo());
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
