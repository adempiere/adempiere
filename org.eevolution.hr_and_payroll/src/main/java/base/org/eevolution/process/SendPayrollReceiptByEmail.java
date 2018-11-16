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

package org.eevolution.process;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.*;
import org.compiere.process.ProcessInfo;
import org.compiere.util.EMail;
import org.compiere.util.Env;
import org.eevolution.service.dsl.ProcessBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Send mail to employee
 *
 * @author Antonio Canaveral, www.e-evolution.com
 * eEvolution author Victor Perez <victor.perez@e-evolution.com>
 * eEvolution author Alberto Juarez <alberto.juarez@e-evolution.com>
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * <li> FR [ 402 ] Mail setup is hardcoded
 * @see https://github.com/adempiere/adempiere/issues/402
 */
public class SendPayrollReceiptByEmail extends SendPayrollReceiptByEmailAbstract {

    private int counter = 0;
    private int errors = 0;


    /**
     * Prepare - e.g., get Parameters.
     */
    protected void prepare() {
        super.prepare();
    }    //	prepare

    /**
     * Perform process.
     *
     * @return Message
     * @throws Exception
     */
    protected String doIt() throws Exception {
        log.info("R_MailText_ID=" + getMailTextId());
        //	Mail Test
        MMailText mailText = new MMailText(getCtx(), getMailTextId(), get_TrxName());
        if (mailText.getR_MailText_ID() == 0)
            throw new Exception("@R_MailText_ID@=" + getMailTextId() + " @NotFound@ ");
        //	Client Info
        MClient client = MClient.get(getCtx());
        if (client.getAD_Client_ID() == 0)
            throw new Exception(" @AD_Client_ID@  @NotFound@ ");
        if (client.getSMTPHost() == null || client.getSMTPHost().length() == 0)
            throw new Exception("@SMTPHost@  @NotFound@ ");
        //
        long start = System.currentTimeMillis();
        sendEMail(mailText);
        return "@Created@=" + counter + ", @Errors@=" + errors + " - "
                + (System.currentTimeMillis() - start) + "ms";
    }    //	doIt

    /**
     * Send to BPGroup
     */
    private void sendEMail(MMailText mailText) {
        for (Integer paymentId : getPaymentIds()) {
            Boolean ok = sendIndividualMail(mailText, paymentId, null);
            if (ok == null) {
                //nothing to do
            } else if (ok.booleanValue()) {
                counter++;
            } else {
                errors++;
            }
        }
    }    //	sendEMail

    /**
     * Send Individual Mail
     *
     * @param mailText    Mail Test
     * @param paymentId   user
     * @param unSubscribe unsubscribe message
     * @return true if mail has been sent
     */
    private Boolean sendIndividualMail(MMailText mailText, int paymentId, String unSubscribe) {
        try {
            MPayment payment = new MPayment(getCtx(), paymentId, get_TrxName());
            MBPartner employee = (MBPartner) payment.getC_BPartner();

            String message = mailText.getMailText(true);
            if (unSubscribe != null)
                message += unSubscribe;

            StringBuffer whereClause = new StringBuffer();
            whereClause.append(MBPartnerLocation.COLUMNNAME_C_BPartner_ID)
                    .append(" = ? AND ")
                    .append(MBPartnerLocation.COLUMNNAME_ContactType)
                    .append("=?");

            MBPartnerLocation location = new Query(getCtx(), MBPartnerLocation.Table_Name, whereClause.toString(), get_TrxName())
                    .setOnlyActiveRecords(true)
                    .setParameters(employee.getC_BPartner_ID(), MBPartnerLocation.CONTACTTYPE_Primary)
                    .first();

            if (location == null) {
                addLog(0, null, null, employee.getName() + " @Email@ @NotFound@");
                return false;
            }

            MClient client = MClient.get(getCtx());
            MUser user = MUser.get(getCtx(), Env.getAD_User_ID(getCtx()));
            EMail email = client.createEMail(user, location.getEMail() , mailText.getMailHeader(), message);
            if (mailText.isHtml())
                email.setMessageHTML(mailText.getMailHeader(), message);
            else {
                email.setSubject(mailText.getMailHeader());
                email.setMessageText(message);
            }
            File attachment = getPDF(paymentId);
            if (attachment != null)
                email.addAttachment(attachment);

            if (!email.isValid() && !email.isValid(true)) {
                addLog(0, null, null, employee.getName() + " @Email@ @NotValid@ " + email);
                log.warning("NOT VALID - " + email);
                return Boolean.FALSE;
            }
            boolean ok = EMail.SENT_OK.equals(email.send());
            if (ok) {
                addLog(0, null, null, employee.getName() + " @Email@ @OK@");
                log.fine(employee.getURL());
            } else
                log.warning("FAILURE - " + employee.getURL());

            addLog(0, null, null, (ok ? "@OK@" : "@ERROR@") + " - " + email.getFrom().getAddress());
            return ok;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }    //	sendIndividualMail


    private int[] getPaymentIds() {
        List<Object> parameters = new ArrayList<>();
        StringBuffer whereClause = new StringBuffer();
        whereClause.append("EXISTS (SELECT 1 FROM C_PaySelectionCheck psc ")
                .append("INNER JOIN C_PaySelectionLine psl ON(psc.C_PaySelection_ID = psl.C_PaySelection_ID AND psl.C_BPartner_ID = psc.C_BPartner_ID) ")
                .append("INNER JOIN HR_Movement m ON (psl.HR_Movement_ID = m.HR_Movement_ID) ")
                .append("WHERE C_Payment.C_Payment_ID = psc.C_Payment_ID ")
                .append("AND m.HR_Process_ID = ? ");
        parameters.add(getHRProcessId());
        if (getBPartnerId() > 0) {
            whereClause.append("AND psc.C_BPartner_ID=? ");
            parameters.add(getBPartnerId());
        }
        if (getDepartmentId() > 0) {
            whereClause.append("AND m.HR_Department_ID=? ");
            parameters.add(getDepartmentId());
        }
        if (getJobId() > 0) {
            whereClause.append("AND m.HR_Job_ID=? ");
            parameters.add(getJobId());
        }
        if (getActivityId() > 0) {
            whereClause.append("AND m.C_Activity_ID=? ");
            parameters.add(getActivityId());
        }
        if (getBPGroupId() > 0) {
            whereClause.append("AND EXISTS (SELECT 1 FROM C_BPartner bp WHERE bp.C_BPartner_ID = psc.C_BPartner_ID AND bp.IsEmployee=? AND bp.C_BP_Group_ID = ?)");
            parameters.add(true);
            parameters.add(getBPGroupId());
        }

        whereClause.append(")");
        return new Query(getCtx(), I_C_Payment.Table_Name, whereClause.toString(), get_TrxName())
                .setClient_ID()
                .setParameters(parameters)
                .getIDs();

    }

    /**
     * Get PDF document
     * @param recordId
     * @return
     */
    private File getPDF(Integer recordId) {
        if (recordId <= 0)
            return null;

        ProcessInfo processInfo = ProcessBuilder.create(getCtx()).process(getADProcessId())
                .withTitle(getProcessName())
                .withRecordId(MPayment.Table_ID, recordId)
                .execute();

        return processInfo.getPDFReport();
    }

}    //	SendMailText