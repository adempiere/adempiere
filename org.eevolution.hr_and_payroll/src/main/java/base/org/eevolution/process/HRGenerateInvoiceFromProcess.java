/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2016 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
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

package org.eevolution.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import org.compiere.apps.AEnv;
import org.compiere.apps.AWindow;
import org.compiere.model.I_C_BP_Relation;
import org.compiere.model.I_C_BPartner_Location;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MDocType;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MPaymentTerm;
import org.compiere.model.MQuery;
import org.compiere.model.MSession;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.eevolution.model.I_HR_Employee;
import org.eevolution.model.I_HR_Process;
import org.eevolution.model.MHRMovement;

/** Generated Process for (Create Invoice for Business Partner)
 *  @author Susanne Calderon, Westfalia
 *  @author victor.perez@e-evolution.com, www-e-evolution.com
 */
public class HRGenerateInvoiceFromProcess extends HRGenerateInvoiceFromProcessAbstract
{
	@Override
	protected void prepare()
	{
		super.prepare();
	}

	private 	int count = 0;
	private HashMap <Integer, MInvoice> invoices  = new HashMap<>();

	@Override
	protected String doIt() throws Exception
	{
		MBPartner partner = null;
		if (getBPartnerId() > 0)
			partner = new MBPartner(getCtx(), getBPartnerId(), get_TrxName());

		List<MHRMovement> payrollMovements = (List<MHRMovement>) getInstancesForSelection(get_TrxName());
		for (MHRMovement payrollMovement : payrollMovements) {
			if (getBPartnerId() <= 0)
				partner = MBPartner.get(getCtx(), payrollMovement.getC_BPartner_ID());

			if (payrollMovement.getC_BP_Relation_ID() > 0) {
				I_C_BP_Relation partnerRelation = payrollMovement.getC_BP_Relation();
				partner = MBPartner.get(getCtx(), partnerRelation.getC_BPartnerRelation_ID());
			}

			I_HR_Employee employee = payrollMovement.getHR_Employee();
			if (!invoices.containsKey(partner.get_ID()))
			{
				MInvoice invoice = createInvoice(payrollMovement.getHR_Process(), partner, employee, getDocTypeId(), getDateInvoiced());
				invoices.put(invoice.getC_BPartner_ID(), invoice);
			}

			createInvoiceLine(invoices.get(partner.get_ID()), employee, payrollMovement, getChargeId());

		}

		if (DocAction.ACTION_Complete.equals(getDocAction()))
		{
			invoices.entrySet().forEach( entry -> {
				MInvoice invoice = entry.getValue();
				invoice.processIt(getDocAction());
				invoice.saveEx();
			});
		}

		int sessionId  = Env.getContextAsInt(getCtx(), "AD_Session_ID");
		MSession session = new MSession(getCtx(), sessionId, null);
		if (session.getWebSession() == null ||session.getWebSession().length() == 0)
		{
			if (invoices.size() > 0) {
				commitEx();
				StringBuilder whereClause = new StringBuilder();
				invoices.entrySet().forEach(entry -> whereClause.append(entry.getKey()).append(","));
				StringBuilder whereClauseWindow=new StringBuilder("C_Invoice_ID IN (");
				whereClauseWindow.append(whereClause.substring(0,whereClause.length()-1)).append(")");
				MTable table = new MTable(getCtx(), MInvoice.Table_ID, get_TrxName());
				MQuery query = new MQuery();
				query.addRestriction(whereClauseWindow.toString());
				query.setRecordCount(count);
				int windowId = table.getPO_Window_ID();
				zoom (windowId, query);
			}
		}
		return "@OK@";
	}
	
	 private MInvoice createInvoice(I_HR_Process process, MBPartner partner, I_HR_Employee employee, int docTypeId, Timestamp dateInvoice) {
		int partnerLocationId = 0;
		if (getBPartnerLocationId() > 0) {
			partnerLocationId = getBPartnerLocationId();
		}
		else{
			 MBPartnerLocation partnerLocation = getLocationBill(partner);
			 if (partnerLocation == null) {
				 log.log(Level.SEVERE, " @C_BPartner_Location_ID@ @NotFound@ : " + partner.getName());
				 addLog(0, process.getDateAcct(), null, "@Bill_Location_ID@ @NotFound@ "
						 + process.getDocumentNo() + " "
						 + partner.getName());
				 return null;
			 }
			 partnerLocationId = partnerLocation.get_ID();
		 }
		 String paymentRule = employee != null?  employee.getPaymentRule():null;
		 if (paymentRule == null || paymentRule.isEmpty())
			 paymentRule = partner.getPaymentRulePO();

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
		 MDocType docType = MDocType.get(getCtx(), docTypeId);
		 StringBuilder invoiceDescription = new StringBuilder();
		 invoiceDescription.append(Msg.parseTranslation(getCtx(), "@HR_Process_ID@ " + process.getName()
				 + " @DocumentNo@ " + process.getDocumentNo() + " @HR_Employee_ID@ " + employee.getName()));
		 MInvoice invoice = new MInvoice(getCtx(), 0, get_TrxName());
		 invoice.setAD_Org_ID(process.getAD_Org_ID());
		 invoice.setIsSOTrx(docType.isSOTrx());
		 invoice.setPaymentRule(paymentRule);
		 invoice.setC_DocTypeTarget_ID(docTypeId);
		 invoice.setC_DocType_ID(docTypeId);
		 invoice.setDescription(invoiceDescription.toString());
		 invoice.setDateOrdered(dateInvoice);
		 invoice.setDateInvoiced(dateInvoice);
		 invoice.setDateAcct(dateInvoice);
		 invoice.setBPartner(partner);
		 invoice.setC_BPartner_Location_ID(partnerLocationId);

		 MPaymentTerm paymentTerm = MPaymentTerm.getPaymentTermByDefault(getCtx(), partner.get_TrxName());
		 if (paymentTerm != null)
			 invoice.setC_PaymentTerm_ID(paymentTerm.getC_PaymentTerm_ID());

		 if (employee != null && employee.getC_Activity_ID() != 0)
			 invoice.setC_Activity_ID(employee.getC_Activity_ID());

		 invoice.setDocStatus(DocAction.STATUS_Drafted);
		 invoice.setDocAction(DocAction.ACTION_None);
		 invoice.setSalesRep_ID(getAD_User_ID());
		 invoice.saveEx();
		 count = count ++;
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
	    private MInvoiceLine createInvoiceLine(MInvoice invoice, I_HR_Employee employee, MHRMovement movement, int chargeId) {
			StringBuilder invoiceLineDescription = new StringBuilder();
			invoiceLineDescription.append(Msg.parseTranslation(invoice.getCtx() , "@HR_Employee_ID@ " + employee.getName()
					+  " @HR_Concept_ID@ " + movement.getHR_Concept().getName()));
	        MInvoiceLine invoiceLine = new MInvoiceLine(invoice);
	        invoiceLine.setC_Charge_ID(chargeId);
	        invoiceLine.setQty(BigDecimal.ONE); //
	        invoiceLine.setDescription(invoiceLineDescription.toString());
	        invoiceLine.setC_Activity_ID(movement.getC_Activity_ID());
	        invoiceLine.setC_Campaign_ID(movement.getC_Campaign_ID());
	        invoiceLine.setAD_OrgTrx_ID(movement.getAD_OrgTrx_ID());
	        invoiceLine.setC_Project_ID(movement.getC_Project_ID());
	        invoiceLine.setC_ProjectPhase_ID(movement.getC_ProjectPhase_ID());
	        invoiceLine.setC_ProjectTask_ID(movement.getC_ProjectTask_ID());
	        invoiceLine.setUser1_ID(movement.getUser1_ID());
	        invoiceLine.setUser2_ID(movement.getUser2_ID());
	        invoiceLine.setUser3_ID(movement.getUser3_ID());
	        invoiceLine.setUser4_ID(movement.getUser4_ID());
	        invoiceLine.setPrice(movement.getAmount());
	        invoiceLine.setTax();
	        invoiceLine.saveEx();
	        addLog(invoiceLine.getLine(), invoice.getDateInvoiced(), invoiceLine.getLineNetAmt(), invoiceLineDescription.toString());

	        movement.setC_InvoiceLine_ID(invoiceLine.getC_InvoiceLine_ID());
	        movement.saveEx();
	        return invoiceLine;
	    }
	    
	    private MBPartnerLocation getLocationBill(MBPartner partner) {
	        String whereClause = "C_BPartner_ID=? AND IsBillTo=? AND IsActive=?";
	        return new Query(partner.getCtx(), I_C_BPartner_Location.Table_Name, whereClause, partner.get_TrxName())
	                .setClient_ID()
	                .setParameters(partner.getC_BPartner_ID(), true, true)
	                .first();
	    }
	    
	    protected void zoom (int AD_Window_ID, MQuery zoomQuery)
		{
			final AWindow frame = new AWindow();
			if (!frame.initWindow(AD_Window_ID, zoomQuery))
				return;
			AEnv.addToWindowManager(frame);
			//	VLookup gets info after method finishes
			new Thread()
			{
				public void run()
				{
					try
					{
						sleep(50);
					}
					catch (Exception e)
					{
					}
					AEnv.showCenterScreen(frame);
				}
			}.start();
		}	//	zoom

}