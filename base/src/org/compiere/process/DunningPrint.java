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
import org.compiere.model.MClient;
import org.compiere.model.MDunningLevel;
import org.compiere.model.MDunningRun;
import org.compiere.model.MDunningRunEntry;
import org.compiere.model.MMailText;
import org.compiere.model.MQuery;
import org.compiere.model.MUser;
import org.compiere.model.MUserMail;
import org.compiere.model.PrintInfo;
import org.compiere.print.MPrintFormat;
import org.compiere.print.ReportEngine;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.EMail;

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
		MClient client = MClient.get(getCtx());
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
				else if (to.getEMail() == null || to.getEMail().length() == 0)
				{
					addLog (entry.get_ID(), null, null, "@NotFound@: @EMail@ - " + to.getName());
					errors++;
					continue;
				}
			}
			//	query
			MQuery query = new MQuery("C_Dunning_Header_v");
			query.addRestriction("C_DunningRunEntry_ID", MQuery.EQUAL, 
				new Integer(entry.getC_DunningRunEntry_ID()));

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
				EMail email = client.createEMail(to.getEMail(), null, null);
				if (!email.isValid()) {
					addLog (entry.get_ID(), null, null, 
						"@RequestActionEMailError@ Invalid EMail: " + to);
					internalError++;
					continue;
				}
				if(text == null) {
					continue;
				}
				text.setUser(to);	//	variable context
				text.setBPartner(bp);
				text.setPO(entry);
				String message = text.getMailText(true);
				if (text.isHtml()) {
					email.setMessageHTML(text.getMailHeader(), message);
				} else {
					email.setSubject (text.getMailHeader());
					email.setMessageText (message);
				}
				//
				if (re != null) {
					File attachment = re.getPDF();
					log.fine(to + " - " + attachment);
					email.addAttachment(attachment);
				}
				//
				String msg = email.send();
				MUserMail um = new MUserMail(text, entry.getAD_User_ID(), email);
				um.saveEx();
				if (msg.equals(EMail.SENT_OK)) {
					addLog (entry.get_ID(), null, null,
						bp.getName() + " @RequestActionEMailOK@");
					count++;
					printed = true;
				} else {
					addLog (entry.get_ID(), null, null,
						bp.getName() + " @RequestActionEMailError@ " + msg);
					internalError++;
				}
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
