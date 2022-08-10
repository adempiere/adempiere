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
 * Copyright (C) 2003-2016 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/

package org.eevolution.process;

import org.compiere.model.I_C_BPartner;
import org.compiere.model.I_C_BPartner_Location;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MPaymentTerm;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.eevolution.model.MHRAttribute;
import org.eevolution.model.MHREmployee;
import org.eevolution.model.MHRMovement;
import org.eevolution.model.MHRProcess;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

/**
 * Created by eEvolution author Oscar Gomez <oscar.gomez@e-evolution.com>
 * @author eEvolution Victor Perez <victor.perez@e-evolution.com>
 */
public class HRCreateInvoice extends HRCreateInvoiceAbstract {

    /**
     * Prepare - e.g., get Parameters.
     */
    protected void prepare() {
        super.prepare();
    }    //	prepare

    /**
     * Process
     * @return message
     * @throws Exception
     */
    protected String doIt() throws Exception {
        MHRProcess process = new MHRProcess(getCtx(), getHRProcessId(), get_TrxName());
        final Timestamp dateInvoice = getDateInvoiced() != null ? getDateInvoiced() : process.getHR_Period_ID() > 0 ? process.getHR_Period().getStartDate() : process.getDateAcct();
        Arrays.stream(getEmployeeIds(process))
                .filter(partnerId -> partnerId > 0)
                .forEach(partnerId -> {
                    Trx.run(trxName -> {
                        int lastPartnerId = 0;
                        MHREmployee employee = null;
                        MInvoice invoice = null;
                        for (MHRMovement movement : getPayrollMovement(getHRProcessId(), partnerId, trxName)) {
                            MBPartner partner = new MBPartner(getCtx(), partnerId, trxName);
                            MHRAttribute attribute = MHRAttribute.getByConceptIdAndPartnerId(movement.getCtx(), movement.getHR_Concept_ID(), movement.getC_BPartner_ID(), movement.getValidFrom(), trxName);
                            if (attribute != null && attribute.getC_DocType_ID() == 0) {
                                log.log(Level.SEVERE, "@HR_Employee_ID@ " + partner.getName() + "  @HR_Concept_ID@  " + movement.getHR_Concept().getName() + " @C_DocType_ID@ @NotFound@ : ");
                                addLog(0, null, null, "@HR_Employee_ID@ " + partner.getName() + "  @HR_Concept_ID@  " + movement.getHR_Concept().getName() + " @C_DocType_ID@ @NotFound@ : ");
                                continue;
                            }

                            if (attribute != null && attribute.getC_Charge_ID() == 0) {
                                log.log(Level.SEVERE, "@HR_Employee_ID@ " + partner.getName() + "  @HR_Concept_ID@  " + movement.getHR_Concept().getName() + " @C_Charge_ID@ @NotFound@ : ");
                                addLog(0, null, null, "@HR_Employee_ID@ " + partner.getName() + "  @HR_Concept_ID@  " + movement.getHR_Concept().getName() + " @C_Charge_ID@ @NotFound@ : ");
                                continue;
                            }

                            if (movement.getC_BPartner_ID() != lastPartnerId) {
                                lastPartnerId = movement.getC_BPartner_ID();
                                employee = MHREmployee.getActiveEmployee(getCtx(), partner.getC_BPartner_ID(), trxName);
                                invoice = createInvoice(process, partner, employee, attribute.getC_DocType_ID(), dateInvoice);
                                if (invoice == null)
                                    continue;

                            }

                            if (invoice != null)
                                createInvoiceLine(invoice, employee, movement, attribute.getC_Charge_ID());

                        }
                    });
                });
        return "@OK@";
    }

    /**
     * Get Payroll movement with concept is invoiced
     * @param processId
     * @param partnerId
     * @param trxName
     * @return
     */
    private List<MHRMovement> getPayrollMovement(int processId, int partnerId, String trxName) {
        StringBuilder whereClause = new StringBuilder();
        whereClause.append("HR_Movement.HR_Process_ID = ? AND HR_Movement.C_BPartner_ID=? AND ")
                .append("EXISTS (SELECT 1 FROM HR_Concept c INNER JOIN HR_Attribute a ON (c.HR_Concept_ID = a.HR_Concept_ID) ")
                .append("WHERE HR_Movement.C_InvoiceLine_ID IS NULL AND c.HR_Concept_ID=HR_Movement.HR_Concept_ID AND  a.IsActive = ? AND c.IsInvoiced=? AND a.C_DocType_ID > 0 AND a.C_Charge_ID > 0)");

        return new Query(getCtx(), MHRMovement.Table_Name, whereClause.toString(), trxName)
                .setClient_ID()
                .setParameters(
                        processId,
                        partnerId,
                        true,
                        true)
                .list();
    }

    /**
     * ger Location Bill based on Business Partners
     * @param partner
     * @return Partner Location
     */
    private MBPartnerLocation getLocationBill(MBPartner partner) {
        String whereClause = "C_BPartner_ID=? AND IsBillTo=? AND IsActive=?";
        return new Query(partner.getCtx(), I_C_BPartner_Location.Table_Name, whereClause, partner.get_TrxName())
                .setClient_ID()
                .setParameters(partner.getC_BPartner_ID(), true, true)
                .first();
    }

    /**
     * Get employee based on payroll process
     * @param process
     * @return
     */
    public int[] getEmployeeIds(MHRProcess process) {
        StringBuilder whereClause = new StringBuilder();
        whereClause.append("EXISTS ( SELECT 1 FROM HR_Attribute a ")
                .append("INNER JOIN HR_Concept c ON c.HR_Concept_ID=a.HR_Concept_ID ")
                .append("WHERE a.IsActive = 'Y' AND a.C_Charge_ID > 0 AND c.IsInvoiced='Y' ")
                .append("AND a.C_DocType_ID > 0 AND EXISTS (SELECT 1 FROM HR_Movement m WHERE m.HR_Process_ID=? ")
                .append("AND m.C_BPartner_ID=C_BPartner.C_BPartner_ID AND m.HR_Concept_ID = c.HR_Concept_ID AND m.C_InvoiceLine_ID IS NULL))");

        return new Query(getCtx(), MBPartner.Table_Name, whereClause.toString(), get_TrxName())
                .setClient_ID()
                .setOnlyActiveRecords(true)
                .setParameters(process.getHR_Process_ID())
                .setOrderBy(I_C_BPartner.COLUMNNAME_Value)
                .getIDs();
    }

    /**
     * Create invoice for employee
     * @param process
     * @param partner
     * @param employee
     * @param docTypeId
     * @return
     */
    private MInvoice createInvoice(MHRProcess process, MBPartner partner, MHREmployee employee, int docTypeId, Timestamp dateInvoice) {
        MBPartnerLocation partnerLocation = getLocationBill(partner);
        if (partnerLocation == null) {
            log.log(Level.SEVERE, " @C_BPartner_Location_ID@ @NotFound@ : " + partner.getName());
            addLog(0, process.getDateAcct(), null, "@Bill_Location_ID@ @NotFound@ "
                    + process.getDocumentNo() + " "
                    + partner.getName());
            return null;
        }

        String paymentRule = employee.getPaymentRule();
        if (paymentRule == null || paymentRule.isEmpty())
            paymentRule = partner.getPaymentRule();

        if (paymentRule == null) {
            log.log(Level.SEVERE, " @PaymentRule@ @NotFound@ : " + partner.getName());
            addLog(0, process.getDateAcct(), null, "@PaymentRule@ @NotFound@ "
                    + process.getDocumentNo() + " "
                    + process.getName() + " "
                    + partner.getValue() + " "
                    + partner.getName());
            return null;
        }


        log.info("New Invoice for ");
        MInvoice invoice = new MInvoice(process.getCtx(), 0, partner.get_TrxName());
        invoice.setAD_Org_ID(process.getAD_Org_ID());
        invoice.setIsSOTrx(false);
        invoice.setPaymentRule(paymentRule);
        invoice.setC_DocTypeTarget_ID(docTypeId);
        invoice.setC_DocType_ID(docTypeId);
        invoice.setDescription(Msg.parseTranslation(process.getCtx(), "@HR_Process_ID@ " + process.getName() + " @DocumentNo@ " + process.getDocumentNo()));
        invoice.setDateOrdered(dateInvoice);
        invoice.setDateInvoiced(dateInvoice);
        invoice.setDateAcct(dateInvoice);
        invoice.setBPartner(partner);

        MPaymentTerm paymentTerm = MPaymentTerm.getPaymentTermByDefault(getCtx(), partner.get_TrxName());
        if (paymentTerm != null)
            invoice.setC_PaymentTerm_ID(paymentTerm.getC_PaymentTerm_ID());

        if (employee.getC_Activity_ID() != 0)
            invoice.setC_Activity_ID(employee.getC_Activity_ID());

        invoice.setDocStatus(DocAction.STATUS_Drafted);
        invoice.setDocAction(DocAction.ACTION_None);
        invoice.setSalesRep_ID(Env.getAD_User_ID(process.getCtx()));
        invoice.saveEx();
        addLog(0, invoice.getDateInvoiced(), invoice.getGrandTotal(), "@C_Invoice_ID@ " + invoice.getDocumentNo() + " @C_BPartner_ID@  @TaxId@ " + invoice.getC_BPartner().getValue() + " @Name@ " + invoice.getC_BPartner().getName());
        return invoice;
    }

    /**
     * Create Invoice Line to Employee
     * @param invoice
     * @param employee
     * @param movement
     * @param chargeId
     * @return
     */
    private MInvoiceLine createInvoiceLine(MInvoice invoice, MHREmployee employee, MHRMovement movement, int chargeId) {
        MInvoiceLine invoiceLine = new MInvoiceLine(invoice);
        invoiceLine.setC_Charge_ID(chargeId);
        invoiceLine.setQty(BigDecimal.ONE); //
        invoiceLine.setDescription(movement.getHR_Concept().getName());
        invoiceLine.setC_Activity_ID(employee.getC_Activity_ID());
        invoiceLine.setPrice(movement.getAmount());
        invoiceLine.setTax();
        invoiceLine.saveEx();
        addLog(invoiceLine.getLine(), invoice.getDateInvoiced(), invoiceLine.getLineNetAmt(), movement.getHR_Concept().getName());

        movement.setC_InvoiceLine_ID(invoiceLine.getC_InvoiceLine_ID());
        movement.saveEx();
        return invoiceLine;
    }


}    //	HRCreateInvoice