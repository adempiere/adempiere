/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/

package org.spin.process;

import java.io.File;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_AD_PrintForm;
import org.compiere.model.I_C_Payment;
import org.compiere.model.MBPBankAccount;
import org.compiere.model.MBPartner;
import org.compiere.model.MMailText;
import org.compiere.model.MPayment;
import org.compiere.model.MQuery;
import org.compiere.model.MUser;
import org.compiere.model.PrintInfo;
import org.compiere.model.Query;
import org.compiere.model.X_AD_PrintForm;
import org.compiere.print.MPrintFormat;
import org.compiere.print.ReportEngine;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.spin.queue.notification.DefaultNotifier;
import org.spin.queue.util.QueueLoader;

/** 
 * 	Generated Process for (Remittance Advice Send Mail)
 *  @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class PaySelectionSendRemittance extends PaySelectionSendRemittanceAbstract {
	
	/**Mail Template for Project Processor */
	private MMailText mailText = null;
	private X_AD_PrintForm printForm = null;
	
	@Override
	protected void prepare() {
		super.prepare();
		if(getRecord_ID() == 0) {
			throw new AdempiereException("@C_PaySelection_ID@ @NotFound@");
		}
	}
	
	@Override
	protected String doIt() throws Exception {
		printForm = new Query(getCtx(), I_AD_PrintForm.Table_Name, null, get_TrxName())
				.setClient_ID()
				.first();
		if(printForm == null) {
			throw new AdempiereException("@Remittance_PrintFormat_ID@ @NotFound@");
		}
		if(printForm.getRemittance_MailText_ID() == 0
				&& getMailTextId() == 0) {
			throw new AdempiereException("@Remittance_MailText_ID@ @NotFound@");
		}
		if(getMailTextId() == 0) {
			setMailTextId(printForm.getRemittance_MailText_ID());
		}
		//	Get Mail template
		mailText = new MMailText (Env.getCtx(), getMailTextId(), get_TrxName());
		//	query
		String bpWhereClause = "";
		if(getBPartnerId() != 0) {
			bpWhereClause = " AND C_BPartner_ID = " + getBPartnerId();
		}
		AtomicInteger sent = new AtomicInteger();
		AtomicInteger all = new AtomicInteger();
		new Query(getCtx(), I_C_Payment.Table_Name, 
				"EXISTS(SELECT 1 FROM C_PaySelectionCheck psc "
				+ "	WHERE psc.C_PaySelection_ID = ? "
				+ "	AND psc.C_Payment_ID = C_Payment.C_Payment_ID)"
				+ " AND DocStatus IN('CO', 'CL')" + bpWhereClause, get_TrxName())
		.setParameters(getRecord_ID())
		.setClient_ID()
		.<MPayment>list()
		.stream()
		.forEach(payment -> {
			all.addAndGet(1);
			if(sendMail(payment)) {
				sent.addAndGet(1);
			}
		});
		//	
		return "@Processed@: " + all.get() + " - @Sent@: " + sent.get() + " - @Errors@: " + (all.get() - sent.get());
	}
	
	/**
	 * Send Mail for each payment
	 * @param payment
	 * @return
	 */
	private boolean sendMail(MPayment payment) {
		String businessPartnerBankAccountMail = null;
		MUser businessPartnerBankAccountContact = null;
		if(payment.getC_BP_BankAccount_ID() != 0) {
			MBPBankAccount bankAccount = (MBPBankAccount) payment.getC_BP_BankAccount();
			businessPartnerBankAccountMail = bankAccount.getA_EMail();
			if(bankAccount.getAD_User_ID() != 0) {
				businessPartnerBankAccountContact = MUser.get(getCtx(), bankAccount.getAD_User_ID());
				if(Util.isEmpty(businessPartnerBankAccountMail)
						&& !Util.isEmpty(businessPartnerBankAccountContact.getEMail())) {
					businessPartnerBankAccountMail = businessPartnerBankAccountContact.getEMail();
				}
			}
		}
		if(Util.isEmpty(businessPartnerBankAccountMail)) {
			MBPartner businessPartner = (MBPartner) payment.getC_BPartner();
			Optional<MUser> optionalUser = Arrays.asList(businessPartner.getContacts(false))
				.stream()
				.filter(contact -> contact.isActive())
				.findFirst();
			//	
			if(optionalUser.isPresent()) {
				businessPartnerBankAccountContact = optionalUser.get();
				businessPartnerBankAccountMail = businessPartnerBankAccountContact.getEMail();
			}
		}
		//	
		File paymentReport = getPDF(payment);
		//	
		if(paymentReport == null) {
			addLog(payment.getC_Payment_ID(), null, null, "@FilePDF@ @NotFound@");
			return false;
		}
		mailText.setPO(payment);
		if(businessPartnerBankAccountContact != null) {
			mailText.setUser(businessPartnerBankAccountContact);
		}
		mailText.setBPartner((MBPartner)payment.getC_BPartner());
		String message = mailText.getMailText(true);
		//	
		log.fine(businessPartnerBankAccountMail + " - " + paymentReport);
		//	Get instance for notifier
		DefaultNotifier notifier = (DefaultNotifier) QueueLoader.getInstance().getQueueManager(DefaultNotifier.QUEUETYPE_DefaultNotifier)
				.withContext(getCtx())
				.withTransactionName(get_TrxName());
		//	Send notification to queue
		notifier
			.clearMessage()
			.withApplicationType(DefaultNotifier.DefaultNotificationType_UserDefined)
			.withUserId(getAD_User_ID())
			.withText(message)
			.withDescription(mailText.getMailHeader())
			.withEntity(payment);
		if(businessPartnerBankAccountContact != null) {
			notifier.addRecipient(businessPartnerBankAccountContact.getAD_User_ID());
		} else if(!Util.isEmpty(businessPartnerBankAccountMail)) {
			notifier.withApplicationType(DefaultNotifier.DefaultNotificationType_EMail)
				.addRecipient(businessPartnerBankAccountMail);
		} else {
			addLog(payment.getC_Payment_ID(), null, null, "@RequestActionEMailNoTo@");
			return false;
		}
		//	Attachment
		notifier.addAttachment(paymentReport);
		//	Add to queue
		notifier.addToQueue();
		addLog (payment.getC_Payment_ID(), null, null, "@MessageAddedToQueue@");
		//	
		return true;
	}
	
	/**
	 * Get PDF for payment
	 * @param payment
	 * @return
	 */
	private File getPDF(MPayment payment) {
		//	
		MPrintFormat format = MPrintFormat.get (getCtx(), printForm.getRemittance_PrintFormat_ID(), false);
		MQuery query = new MQuery("C_PaySelection_Remittance_v");
		query.addRestriction(I_C_Payment.COLUMNNAME_C_Payment_ID, MQuery.EQUAL, Integer.valueOf(payment.getC_Payment_ID()));
		//	Engine
		PrintInfo info = new PrintInfo(payment.getDocumentNo(), payment.get_Table_ID(), payment.getC_Payment_ID(), payment.getC_BPartner_ID());
		info.setDescription(payment.getDocumentInfo());
		ReportEngine reportEngine = new ReportEngine(getCtx(), format, query, info);
		return reportEngine.getPDF();
	}
}