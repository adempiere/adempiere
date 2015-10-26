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

package org.eevolution.form;

import org.compiere.apps.ADialog;
import org.compiere.apps.ADialogDialog;
import org.compiere.model.MQuery;
import org.compiere.model.PO;
import org.compiere.model.PrintInfo;
import org.compiere.print.MPrintFormat;
import org.compiere.print.ReportEngine;
import org.compiere.print.Viewer;
import org.compiere.util.Env;
import org.compiere.util.Msg;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Cursor;

/**
 * Created by eEvolution author Victor Perez <victor.perez@e-evolution.com> on 25/01/15.
 */
public class VPrintDocument implements IPrintDocument {
    @Override
    public void print(PO document, String printFormantName, int windowNo) {
        JFrame window = Env.getWindow(windowNo);

        if (ADialog.ask(windowNo, window, "PrintShipments")) {
            window.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            int retValue = ADialogDialog.A_CANCEL;    //	see also ProcessDialog.printShipments/Invoices
            do {
                String keyColumnName = document.get_KeyColumns()[0];
                MPrintFormat format = MPrintFormat.get(
                        Env.getCtx(),
                        MPrintFormat.getPrintFormat_ID(printFormantName, document.get_Table_ID(), 0),
                        false);
                MQuery query = new MQuery(document.get_TableName());
                query.addRestriction(keyColumnName, MQuery.EQUAL, document.get_ValueAsInt(keyColumnName));

                //	Engine
                PrintInfo info = new PrintInfo(document.get_TableName(), document.get_Table_ID(), document.get_ValueAsInt(keyColumnName));
                ReportEngine re = new ReportEngine(Env.getCtx(), format, query, info);
                re.print();
                new Viewer(re);

                ADialogDialog d = new ADialogDialog(window,
                        Env.getHeader(Env.getCtx(), windowNo),
                        Msg.getMsg(Env.getCtx(), "PrintoutOK?"),
                        JOptionPane.QUESTION_MESSAGE);
                retValue = d.getReturnCode();
            }
            while (retValue == ADialogDialog.A_CANCEL);
            window.setCursor(Cursor.getDefaultCursor());
        }
    }
}
