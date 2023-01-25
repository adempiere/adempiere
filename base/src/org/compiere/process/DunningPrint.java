/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.process;

import java.io.File;
import java.util.List;

import org.compiere.model.MBPartner;
import org.compiere.model.MDunningLevel;
import org.compiere.model.MDunningRun;
import org.compiere.model.MDunningRunEntry;
import org.compiere.model.MMailText;
import org.compiere.model.MQuery;
import org.compiere.model.MUser;
import org.compiere.model.PrintInfo;
import org.compiere.print.MPrintFormat;
import org.compiere.print.ReportEngine;
import org.compiere.util.AdempiereUserError;
import org.spin.queue.notification.DefaultNotifier;
import org.spin.queue.util.QueueLoader;

/**
 *	Dunning Letter Print
 *	
 *  @author Jorg Janke
 *  @version $Id: DunningPrint.java,v 1.2 2006/07/30 00:51:02 jjanke Exp $
 *  
 *  FR 2872010 - Dunning Run for a complete Dunning (not just level) - Developer: Carlos Ruiz - globalqss - Sponsor: Metas
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/1494">
 * 		@see FR [ 1494 ] Translation is not considerated for Dunning Run</a>
 */
public class DunningPrint extends DunningPrintAbstract {
	/**	Count			*/
	private int count = 0;
	/**	Errors			*/
	private int errors = 0;
	
	/**
	 * Process
	 * @return info
	 * @throws AdempiereUserError 
	 * @throws Exception
	 */
	protected String doIt () throws AdempiereUserError {
		log.info("C_DunningRun_ID=" + getDunningRunId() + ",R_MailText_ID=" + getMailTextId() 
			+ ", EmailPDF=" + isEMailPDF() + ",IsOnlyIfBPBalance=" + isOnlyIfBPBalance() 
			+ ",PrintUnprocessedOnly=" + isPrintUnprocessedOnly());
		
		//	Need to have Template
		if (isEMailPDF() && getMailTextId() == 0) {
			throw new AdempiereUserError ("@NotFound@: @R_MailText_ID@");
		}
		MMailText text = null;
		if (isEMailPDF()) {
			text = new MMailText (getCtx(), getMailTextId(), get_TrxName());
			if (isEMailPDF() && text.get_ID() == 0)
				throw new AdempiereUserError ("@NotFound@: @R_MailText_ID@ - " + getMailTextId());
		}
		//	get Dunning
		if(getDunningRunId() != 0) {
			MDunningRun dunningRun = new MDunningRun (getCtx(), getDunningRunId(), get_TrxName());
			if (dunningRun.get_ID() == 0) {
				throw new AdempiereUserError ("@NotFound@: @C_DunningRun_ID@ - " + getDunningRunId());
			}
			//	Process
			processDunning(dunningRun, text);
		} else {
			List<MDunningRun> dunningList = MDunningRun.getDunningRunList(getCtx(), isPrintUnprocessedOnly());
			if(dunningList != null) {
				for(MDunningRun dunningRun : dunningList) {
					processDunning(dunningRun, text);
				}
			}
		}
		//	
		if (isEMailPDF()) {
			return "@Sent@=" + count + " - @Errors@=" + errors;
		}
		return "@Printed@=" + count;
	}	//	doIt
	
	/**
	 * Process Dunning for send or print
	 * @param dunningRun
	 * @param text
	 */
	private void processDunning(MDunningRun dunningRun, MMailText text) {
		int internalError = 0;
		for(MDunningRunEntry entry : dunningRun.getEntries(false)) {
			//	Print Format on Dunning Level
			MDunningLevel level = new MDunningLevel (getCtx(), entry.getC_DunningLevel_ID(), get_TrxName());
			MPrintFormat format = null;
			if (level.getDunning_PrintFormat_ID() > 0) {
				format = MPrintFormat.get (getCtx(), level.getDunning_PrintFormat_ID(), false);
			}
			if (isOnlyIfBPBalance() && entry.getAmt().signum() <= 0) {
				continue;
			}
			if (isPrintUnprocessedOnly() && entry.isProcessed()) {
				continue;
			}
			//	To BPartner
			MBPartner bp = new MBPartner (getCtx(), entry.getC_BPartner_ID(), get_TrxName());
			if (bp.get_ID() == 0) {
				addLog (entry.get_ID(), null, null, "@NotFound@: @C_BPartner_ID@ " + entry.getC_BPartner_ID());
				errors++;
				continue;
			}
			//	To User
			MUser to = new MUser (getCtx(), entry.getAD_User_ID(), get_TrxName());
			if (isEMailPDF()) {
				if (to.get_ID() == 0) {
					addLog (entry.get_ID(), null, null, "@NotFound@: @AD_User_ID@ - " + bp.getName());
					errors++;
					continue;
				}
			}
			//	query
			MQuery query = new MQuery("C_Dunning_Header_v");
			query.addRestriction("C_DunningRunEntry_ID", MQuery.EQUAL, 
				Integer.valueOf(entry.getC_DunningRunEntry_ID()));

			//	Engine
			PrintInfo info = new PrintInfo(
				bp.getName(),
				MDunningRunEntry.Table_ID,
				entry.getC_DunningRunEntry_ID(),
				entry.getC_BPartner_ID());
			info.setDescription(bp.getName() + ", Amt=" + entry.getAmt());
			ReportEngine re = null;
			if (format != null)
				re = new ReportEngine(getCtx(), format, query, info);
			boolean printed = false;
			if (isEMailPDF()) {
				if(text == null) {
					continue;
				}
				text.setUser(to);	//	variable context
				text.setBPartner(bp);
				text.setPO(entry);
				String message = text.getMailText(true);
				//	Get instance for notifier
				DefaultNotifier notifier = (DefaultNotifier) QueueLoader.getInstance().getQueueManager(DefaultNotifier.QUEUETYPE_DefaultNotifier)
						.withContext(getCtx())
						.withTransactionName(get_TrxName());
				//	Send notification to queue
				notifier
					.clearMessage()
					.withApplicationType(DefaultNotifier.DefaultNotificationType_UserDefined)
					.addRecipient(to.getAD_User_ID())
					.withText(message)
					.withDescription(text.getMailHeader())
					.withEntity(entry);
				//	Attachment
				if (re != null) {
					File attachment = re.getPDF();
					log.fine(to + " - " + attachment);
					notifier.addAttachment(attachment);
				}
				//	Add to queue
				notifier.addToQueue();
				addLog (entry.get_ID(), null, null, bp.getName() + " @RequestActionEMailOK@");
					count++;
					printed = true;
			} else {
				if (re != null) {
					re.print ();
					count++;
					printed = true;
				}
			}
			if (printed) {
				entry.setProcessed (true);
				entry.save ();
			}
		}	//	for all dunning letters
		//	Add Errors
		errors += internalError;
		if (internalError == 0) {
			dunningRun.setProcessed(true);
			dunningRun.saveEx();
		}
	}
	
}	//	DunningPrint
