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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.window.FDialog;
import org.adempiere.webui.window.SimplePDFViewer;
import org.compiere.model.MQuery;
import org.compiere.model.PO;
import org.compiere.model.PrintInfo;
import org.compiere.print.MPrintFormat;
import org.compiere.print.ReportEngine;
import org.compiere.process.IPrintDocument;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.eevolution.services.dsl.ProcessBuilder;

/**
 * Created by eEvolution author Victor Perez <victor.perez@e-evolution.com> on 25/01/15.
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 * Improve definition
 */
public class WPrintDocument implements IPrintDocument {

    public void print(PO document, int printFormatId, int windowNo, boolean askPrint) {
        if(!askPrint) {
        	loopAndPrint(document, printFormatId, windowNo);
        } else if(FDialog.ask(windowNo, SessionManager.getAppDesktop().getComponent(), "PrintDocument", document.getDisplayValue())) {
        	loopAndPrint(document, printFormatId, windowNo);
        }
    }
    
    /**
     * Print each document
     * @param document
     * @param printFormatId
     * @param windowNo
     */
    private void loopAndPrint(PO document, int printFormatId, int windowNo) {
    	boolean retValue = true;
    	do {
            try {
            	printDocument(document, printFormatId, windowNo);
            } catch (Exception e) {
            	
            } finally {
                retValue = FDialog.ask(windowNo, SessionManager.getAppDesktop().getComponent(), Msg.getMsg(Env.getCtx(), "PrintoutOK?"));
            }

        } while (!retValue);
    }

	@Override
	public void print(List<PO> documentList, int printFormatId, int windowNo, boolean askPrint) {
		StringBuffer documentLabels = new StringBuffer();
		documentList.stream().forEach(document -> {
			if(documentLabels.length() > 0) {
				documentLabels.append(Env.NL);
			}
			//	Add to String
			documentLabels.append(document.getDisplayValue());
		});
		if(!askPrint) {
			documentList.stream().forEach(document -> {
        		try {
        			printDocument(document, printFormatId, windowNo);
                } catch (Exception e) {
                	
                }
        	});
		} else if (FDialog.ask(windowNo, SessionManager.getAppDesktop().getComponent(), "PrintAllDocuments", documentLabels.toString())) {
        	documentList.stream().forEach(document -> {
        		try {
        			printDocument(document, printFormatId, windowNo);
                } catch (Exception e) {
                	
                }
        	});
        }
	}
	
	/**
	 * Print document
	 * @param document
	 * @param printFormatId
	 * @throws FileNotFoundException 
	 */
	private void printDocument(PO document, int printFormatId, int windowNo) throws FileNotFoundException {
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
            PrintInfo info = new PrintInfo(
                    document.get_TableName(),
                    document.get_Table_ID(),
                    document.get_ValueAsInt(keyColumnName));
            ReportEngine reportEngine = new ReportEngine(Env.getCtx(), format, query, info, document.get_TrxName());
            if (reportEngine != null) {
                SimplePDFViewer win = new SimplePDFViewer(format.getName(), new FileInputStream(reportEngine.getPDF()));
                SessionManager.getAppDesktop().showWindow(win, "center");
            }
        }
	}
}
