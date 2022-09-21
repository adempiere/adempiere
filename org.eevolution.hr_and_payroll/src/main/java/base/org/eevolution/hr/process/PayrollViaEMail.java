/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/

package org.eevolution.hr.process;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

import org.adempiere.core.domains.models.I_C_BPartner;
import org.adempiere.core.domains.models.X_HR_Process;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MClient;
import org.compiere.model.MMailText;
import org.compiere.model.MPInstance;
import org.compiere.model.MPInstancePara;
import org.compiere.model.MProcess;
import org.compiere.model.MUser;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.spin.queue.notification.DefaultNotifier;
import org.spin.queue.util.QueueLoader;

/**
 *  Send mail to employee
 *
 *  @author Antonio Canaveral, www.e-evolution.com 
 *  eEvolution author Victor Perez <victor.perez@e-evolution.com>
 *  eEvolution author Alberto Juarez <alberto.juarez@e-evolution.com>
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *			<li> FR [ 402 ] Mail setup is hardcoded
 *			@see https://github.com/adempiere/adempiere/issues/402
 */
public class PayrollViaEMail extends SvrProcess
{
	/** What to send			*/
	private int mailTextId = -1;	/**	Mail Text				*/
	private MMailText mailText = null;

	/**	From (sender)			*/
	private int bPartnerId = -1;
	/** Client Info				*/
	private MClient client = null;
	/**	From					*/
	private MBPartner employee = null;

	private int 			m_counter = 0;
	private int 			m_errors = 0;
	/**	To Subscribers 			*/
	private int payrollProcessId = -1;
	/** To Customer Type		*/
	private int bPartnerGroupId = -1;
	/** To Purchaser of Product	*/
	//	comes here
	private int reportProcessId =-1;


	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				log.fine("Null parameter: " + name);
			else if (name.equals("HR_Process_ID"))
				payrollProcessId = para[i].getParameterAsInt();
			else if (name.equals("R_MailText_ID"))
				mailTextId = para[i].getParameterAsInt();
			else if (name.equals("C_BP_Group_ID"))
				bPartnerGroupId = para[i].getParameterAsInt();
			else if (name.equals("C_BPartner_ID"))
				bPartnerId = para[i].getParameterAsInt();
			else if (name.equals("AD_Process_ID"))
				reportProcessId = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}	//	prepare

	/**
	 *  Perform process.
	 *  @return Message
	 *  @throws Exception
	 */
	protected String doIt() throws Exception
	{
		log.info("R_MailText_ID=" + mailTextId);
		//	Mail Test
		mailText = new MMailText(getCtx(), mailTextId, get_TrxName());
		if (mailText.getR_MailText_ID() == 0)
			throw new Exception("@R_MailText_ID@=" + mailTextId +  " @NotFound@ ");
		//	Client Info
		client = MClient.get(getCtx());
		if (client.getAD_Client_ID() == 0)
			throw new Exception(" @AD_Client_ID@  @NotFound@ ");
		if (client.getSMTPHost() == null || client.getSMTPHost().length() == 0)
			throw new Exception("@SMTPHost@  @NotFound@ ");
		//
		long start = System.currentTimeMillis();
		

		if (bPartnerId > 0)
		{
            employee = new MBPartner(getCtx(), bPartnerId, get_TrxName());
            if (employee == null)
                throw new Exception("@C_BPartner_ID@=" + bPartnerId + " @NotFound@");

				sendIndividualMail (bPartnerId, null);
		} else
			sendBPGroup();

		log.fine("From " + employee);
			

		return "@Created@=" + m_counter + ", @Errors@=" + m_errors + " - "
			+ (System.currentTimeMillis()-start) + "ms";
	}	//	doIt
	
	/**
	 * 	Send to BPGroup
	 */
	private void sendBPGroup()
	{

        List<Object> parameters = new ArrayList<Object>();
        StringBuffer whereClause = new StringBuffer();

        whereClause.append(I_C_BPartner.COLUMNNAME_IsActive).append("=? AND ")
                   .append(I_C_BPartner.COLUMNNAME_IsEmployee).append("=? AND ")
                   .append("EXISTS (SELECT 1 FROM HR_Movement m WHERE m.C_BPartner_ID=C_BPartner.C_BPartner_ID AND m.HR_Process_ID=?)");

        parameters.add(true);
        parameters.add(true);
        parameters.add(payrollProcessId);

        if (bPartnerGroupId > 0) {
            whereClause.append(" AND ").append(I_C_BPartner.COLUMNNAME_C_BP_Group_ID).append("=? ");
            parameters.add(bPartnerGroupId);
        }

        int[] employeeIds = new Query(getCtx() , I_C_BPartner.Table_Name, whereClause.toString() , get_TrxName())
                .setClient_ID()
                .setParameters(parameters)
                .getIDs();

        for (Integer employeeId : employeeIds)
        {
            boolean ok = sendIndividualMail (employeeId, null);
            if (ok) {
                m_counter++;
            } else {
                m_errors++;
            }
        }
	}	//	sendBPGroup
	
	/**
	 * 	Send Individual Mail
	 *	@param bPartnerId user
	 *	@param unSubscribe unsubscribe message
	 *	@return true if mail has been sent
	 */
	private boolean sendIndividualMail (int bPartnerId,String unSubscribe)
	{
		try
		{
			MBPartner employee = new MBPartner(getCtx(), bPartnerId, null);
			String message = mailText.getMailText(true);
			if (unSubscribe != null)
				message += unSubscribe;

            StringBuffer whereClause = new StringBuffer();
            whereClause.append(MBPartnerLocation.COLUMNNAME_C_BPartner_ID)
                    .append(" = ? AND ")
                    .append(MBPartnerLocation.COLUMNNAME_ContactType)
                    .append("=?");


			MBPartnerLocation location = new Query(getCtx(), MBPartnerLocation.Table_Name, whereClause.toString() , get_TrxName())
								.setOnlyActiveRecords(true)
								.setParameters(bPartnerId, MBPartnerLocation.CONTACTTYPE_Primary)
								.first();
			
			if(location == null) {
                addLog(0 ,null , null , employee.getName() +  " @Email@ @NotFound@" );
                return false;
            }
			//	Get instance for notifier
			DefaultNotifier notifier = (DefaultNotifier) QueueLoader.getInstance().getQueueManager(DefaultNotifier.QUEUETYPE_DefaultNotifier)
					.withContext(getCtx())
					.withTransactionName(get_TrxName());
			//	Send notification to queue
			notifier
				.clearMessage()
				.withUserId(getAD_User_ID())
				.addAttachment(CreatePDF(bPartnerId))
				.withText(message)
				.withDescription(mailText.getMailHeader());
			//	Validate contact
			Optional<MUser> maybeContact = Arrays.asList(MUser.getOfBPartner(getCtx(), bPartnerId, null)).stream().filter(user -> user.getC_BPartner_Location_ID() == location.getC_BPartner_Location_ID()).findFirst();
			if(maybeContact.isPresent()) {
				notifier.withApplicationType(DefaultNotifier.DefaultNotificationType_UserDefined)
				.addRecipient(maybeContact.get().getAD_User_ID());
			} else {
				notifier.withApplicationType(DefaultNotifier.DefaultNotificationType_EMail)
				.addRecipient(location.getEMail());
			}
			//	Add to queue
			notifier.addToQueue();
			return true;
		}catch(Exception e)
		{
			return Boolean.FALSE;
		}
	}	//	sendIndividualMail
	
	private File CreatePDF(int bPartnerId)
	{
		File attachment = null;
		int AD_Process_ID = reportProcessId;
		MPInstance instance = new MPInstance(Env.getCtx(), AD_Process_ID, bPartnerId);
		instance.saveEx();

		ProcessInfo pi = new ProcessInfo("PH_SendEmail", AD_Process_ID);
		pi.setAD_PInstance_ID (instance.getAD_PInstance_ID());

		//	Add Parameter - Selection=Y
		MPInstancePara ip = new MPInstancePara(instance, 10);
		ip.setParameter(X_HR_Process.COLUMNNAME_HR_Process_ID, payrollProcessId);
        ip.saveEx();

		pi.setRecord_ID(bPartnerId);
		pi.setIsBatch(true);
		MProcess worker = new MProcess(getCtx(),AD_Process_ID,get_TrxName());
		worker.processIt(pi, Trx.get(get_TrxName(), true));
		attachment=pi.getPDFReport();
		return attachment;
	}

}	//	SendMailText