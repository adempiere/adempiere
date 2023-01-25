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

import java.awt.Cursor;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.compiere.apps.ADialog;
import org.compiere.apps.ADialogDialog;
import org.compiere.model.MQuery;
import org.compiere.model.PO;
import org.compiere.model.PrintInfo;
import org.compiere.print.MPrintFormat;
import org.compiere.print.ReportEngine;
import org.compiere.print.Viewer;
import org.compiere.process.IPrintDocument;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.eevolution.services.dsl.ProcessBuilder;

/**
 * Created by eEvolution author Victor Perez <victor.perez@e-evolution.com> on 25/01/15.
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 * Improve definition
 */
public class VPrintDocument implements IPrintDocument {
    @Override
    public void print(PO document, int printFormatId, int windowNo, boolean askPrint) {
    	JFrame window = Env.getWindow(windowNo);
    	if(!askPrint) {
    		loopAndPrint(document, printFormatId, windowNo, window);
    	} else if(ADialog.ask(windowNo, window, "PrintDocument", document.getDisplayValue())) {
			loopAndPrint(document, printFormatId, windowNo, window);
		}
    }
    
    /**
     * Print each document
     * @param document
     * @param printFormatId
     * @param windowNo
     * @param current window
     */
    private void loopAndPrint(PO document, int printFormatId, int windowNo, JFrame window) {
    	window.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        int retValue = ADialogDialog.A_CANCEL;    //	see also ProcessDialog.printShipments/Invoices
        do {
            try {
            	printDocument(document, printFormatId, windowNo);
            } catch (Exception e) {

            } finally {
                ADialogDialog d = new ADialogDialog(window,
                        Env.getHeader(Env.getCtx(), windowNo),
                        Msg.getMsg(Env.getCtx(), "PrintoutOK?"),
                        JOptionPane.QUESTION_MESSAGE);
                retValue = d.getReturnCode();
            }
        }
        while (retValue == ADialogDialog.A_CANCEL);
        window.setCursor(Cursor.getDefaultCursor());
    }

	@Override
	public void print(List<PO> documentList, int printFormatId, int windowNo, boolean askPrint) {
		JFrame window = Env.getWindow(windowNo);
		StringBuffer documentLabels = new StringBuffer();
		documentList.stream().forEach(document -> {
			if(documentLabels.length() > 0) {
				documentLabels.append(Env.NL);
			}
			//	Add to String
			documentLabels.append(document.getDisplayValue());
		});
		if(!askPrint) {
			window.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            documentList.stream().forEach(document -> {
            	printDocument(document, printFormatId, windowNo);
            });
            window.setCursor(Cursor.getDefaultCursor());
		} else if(ADialog.ask(windowNo, window, "PrintAllDocuments", documentLabels.toString())) {
            window.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            documentList.stream().forEach(document -> {
            	printDocument(document, printFormatId, windowNo);
            });
            window.setCursor(Cursor.getDefaultCursor());
        }
	}
	
	/**
	 * Print document
	 * @param document
	 * @param printFormatId
	 */
	private void printDocument(PO document, int printFormatId, int windowNo) {
		String keyColumnName = document.get_KeyColumns()[0];
        MPrintFormat format = MPrintFormat.get(Env.getCtx(), printFormatId, false);
        if(format.getJasperProcess_ID() != 0) {
        	try {
        		ProcessBuilder.create(document.getCtx())
        			.process(format.getJasperProcess_ID())
        			.withRecordId(document.get_Table_ID(), document.get_ID())
        			.withoutPrintPreview()
        			.execute(document.get_TrxName());
        	} catch (Exception e) {
        		
        	}
        } else {
        	MQuery query = new MQuery(document.get_TableName());
            query.addRestriction(keyColumnName, MQuery.EQUAL, document.get_ValueAsInt(keyColumnName));

            //	Engine
            PrintInfo info = new PrintInfo(document.get_TableName(), document.get_Table_ID(), document.get_ValueAsInt(keyColumnName));
            ReportEngine reportEngine = new ReportEngine(Env.getCtx(), format, query, info, document.get_TrxName());
            reportEngine.print();
            new Viewer(reportEngine);
        }
	}
}
