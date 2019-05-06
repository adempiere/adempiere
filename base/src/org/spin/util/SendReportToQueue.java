/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 or later of the                                  *
 * GNU General Public License as published                                    *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2015 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************/
package org.spin.util;

import java.io.File;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MUser;
import org.compiere.print.MPrintFormat;
import org.compiere.print.ReportEngine;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.spin.model.MADAppRegistration;
import org.spin.util.support.AppSupportHandler;
import org.spin.util.support.IAppSupport;
import org.spin.util.support.mq.AbstractMessageQueue;
import org.spin.util.support.mq.PrinterMessage;

/**
 * 	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/1400">
 * 		@see FR [ 1400 ] Dynamic report export</a>
 */
public class SendReportToQueue extends AbstractExportFormat {
	
	public SendReportToQueue(Properties ctx, ReportEngine reportEngine) {
		setCtx(ctx);
		setReportEngine(reportEngine);
	}
	
	@Override
	public String getExtension() {
		return "";
	}

	@Override
	public String getName() {
		return Msg.getMsg(Env.getCtx(), "SendPDFToQueue");
	}
	
	@Override
	public boolean sendTo(File file) {
		if(getReportEngine() == null
				|| getCtx() == null) {
			return false;
		}
		//	Export report
		ExportFormatXML xmlExporter = new ExportFormatXML(getCtx(), getReportEngine());
		if(xmlExporter.exportToFile(file)) {
			try {
				//	Cable
				int appRegisteredId = Env.getContextAsInt(getCtx(), "#LocalPrinter_ID@");
				if(appRegisteredId <= 0) {
					MUser currentUser = MUser.get(getCtx());
					appRegisteredId = currentUser.get_ValueAsInt("DefaultPrinter_ID");
				}
				if(appRegisteredId <= 0) {
					ReportEngine reportEngine = getReportEngine();
					if(reportEngine != null) {
						MPrintFormat format = reportEngine.getPrintFormat();
						//	Get from format
						if(format != null) {
							appRegisteredId = format.get_ValueAsInt("DefaultPrinter_ID");
						}
					}
				}
				if(appRegisteredId <= 0) {
					throw new AdempiereException("@AD_AppRegistration_ID@ @NotFound@");
				}
				MADAppRegistration registeredApplication = MADAppRegistration.getById(getCtx(), appRegisteredId, null);
				IAppSupport supportedApplication = AppSupportHandler.getInstance().getAppSupport(registeredApplication);
				//	Exists a Application available for it?
				if(supportedApplication != null
						&& AbstractMessageQueue.class.isAssignableFrom(supportedApplication.getClass())) {
					AbstractMessageQueue messageQueue = (AbstractMessageQueue) supportedApplication;
					//	Send message
					PrinterMessage messageToPrint = new PrinterMessage(file);
					String channel = registeredApplication.getParameterValue("TargetChannel");
					if(Util.isEmpty(channel)) {
						throw new AdempiereException("@PrintChannel@ @NotFound@");
					}
					messageQueue.connect();
					messageQueue.publish(channel, messageToPrint);
					messageQueue.disconnect();
					//	Report Sent
					setMessage("@Sent@");
					return true;
				}
			} catch (Exception e) {
				throw new AdempiereException(e);
			}
		}
		//	
		return false;
	}
	
	@Override
	public String getAction() {
		return SEND_FILE;
	}
}	//	AbstractBatchImport
