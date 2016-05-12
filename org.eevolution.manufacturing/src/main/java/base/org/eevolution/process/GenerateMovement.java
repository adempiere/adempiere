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
import org.compiere.model.MMovement;
import org.compiere.model.PO;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfo;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Msg;
import org.eevolution.form.IPrintDocument;
import org.eevolution.model.MDDOrder;
import org.eevolution.service.dsl.ProcessBuilder;

import java.lang.reflect.Constructor;
import java.util.Arrays;


/**
 * Generate Movement
 * Manual or Automatic
 * Created by eEvolution author Victor Perez <victor.perez@e-evolution.com> on 25/01/15.
 */
public class GenerateMovement extends GenerateMovementAbstract {

    private String result;
    /**
     * Prepare - e.g., get Parameters.
     */
    protected void prepare() {
        super.prepare();
    }    //	prepare

    /**
     * Generate Movements
     * @return info
     * @throws Exception
     */
    protected String doIt() throws Exception {
        ProcessInfo processInfo = ProcessBuilder.create(getCtx()).process(MovementGenerate.getProcessId())
                .withTitle(GenerateMovement.getProcessName())
                .withRecordId(MDDOrder.Table_ID, 0)
                .withSelectedRecordsIds(getSelectionKeys())
                .withParameter(MMovement.COLUMNNAME_MovementDate , getMovementDate())
                .withParameter(MMovement.COLUMNNAME_DocAction, DocAction.ACTION_Complete)
                .execute();

        if (processInfo.isError())
                throw new AdempiereException(processInfo.getSummary());

        result = processInfo.getSummary();

        int[] movementProcessedIds = processInfo.getIDs();
        if (movementProcessedIds != null && movementProcessedIds.length > 0)
        {
            Arrays.stream(movementProcessedIds).filter(movementId -> movementId > 0).forEach(movementId -> {
                MMovement movement = new MMovement(getCtx(), movementId, null);
                StringBuffer resultText = new StringBuffer(Msg.translate(Env.getCtx(), "DocumentNo") + " : " + movement.getDocumentNo());
                result = result == null ? " " + resultText.toString() : result + " " + resultText.toString();
                printDocument(movement, "Inventory Move Hdr (Example)", processInfo.getWindowNo());
            });
        }
        return result;
    }    //	generate

    /**
     * Print Document
     *
     * @param document
     * @param printFormantName
     */
    static public void printDocument(PO document, String printFormantName, int windowNo) {
        IPrintDocument IPrintDocument;
        //	OK to print shipments
        if (Ini.isClient())
            IPrintDocument = getPrintDocument("org.eevolution.form.VPrintDocument");
        else
            IPrintDocument = getPrintDocument("org.eevolution.form.WPrintDocument");

        IPrintDocument.print(document, printFormantName, windowNo);
    }

    static public IPrintDocument getPrintDocument(String className) throws RuntimeException {
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
