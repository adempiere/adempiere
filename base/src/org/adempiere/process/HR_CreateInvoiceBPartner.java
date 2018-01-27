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

package org.adempiere.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;

import org.compiere.apps.AEnv;
import org.compiere.apps.AWindow;
import org.compiere.model.I_C_BPartner_Location;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
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
import org.eevolution.model.MHREmployee;
import org.eevolution.model.MHRProcess;
import org.eevolution.model.MHRMovement;

/** Generated Process for (Create Invoice for Business Partner)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public class HR_CreateInvoiceBPartner extends HR_CreateInvoiceBPartnerAbstract
{
	@Override
	protected void prepare()
	{
		super.prepare();
	}

	private 	String whereClauseWindow = "c_invoice_ID in (";
	private 	int count = 0;

	@Override
	protected String doIt() throws Exception
	{
		MBPartner partner = null;
		MInvoice invoice = null;
		int lastPartnerId = 0;
		if (getBusinessPartnerId() != 0)
			partner = new MBPartner(getCtx(), getBusinessPartnerId(), get_TrxName());
		List<MHRMovement> hrMovements = (List<MHRMovement>) getInstancesForSelection(get_TrxName());
		for (MHRMovement hrmovement : hrMovements) {
			if (getBusinessPartnerId() ==0)        	
				partner = new MBPartner(getCtx(), hrmovement.getC_BPartner_ID(),get_TrxName());

			MHREmployee employee = MHREmployee.getActiveEmployee(getCtx(), partner.getC_BPartner_ID(), get_TrxName());
			if (getBusinessPartnerId() == 0 && hrmovement.getC_BPartner_ID() != lastPartnerId) {

				lastPartnerId = hrmovement.getC_BPartner_ID();
				invoice = createInvoice((MHRProcess)hrmovement.getHR_Process(), partner, employee, getDocumentTypeId(), getDateInvoiced());
				if (invoice == null)
					continue;
			}
			else if (invoice == null)
			{
				invoice = createInvoice((MHRProcess)hrmovement.getHR_Process(), partner, employee, 
						getDocumentTypeId(), getDateInvoiced());
			}

			if (invoice != null)
				createInvoiceLine(invoice, employee, hrmovement, getChargeID());
		}
		whereClauseWindow = whereClauseWindow.substring(0, whereClauseWindow.length() -1) + ")";
		MQuery query = new MQuery("");
		MTable table = new MTable(getCtx(), MInvoice.Table_ID, get_TrxName());
		query.addRestriction(whereClauseWindow);
		query.setRecordCount(count);
		int AD_WindowNo = 0;		
		AD_WindowNo = table.getPO_Window_ID();

		int ad_session_ID  = Env.getContextAsInt(getCtx(), "AD_Session_ID");
		MSession session = new MSession(getCtx(), ad_session_ID, null);

		if (session.getWebSession() == null ||session.getWebSession().length() == 0)
		{
			commitEx();
			zoom (AD_WindowNo, query);
			return "";
		}

		return "@OK@";
	}
	
	 private MInvoice createInvoice(MHRProcess process, MBPartner partner, MHREmployee employee, int docTypeId, Timestamp dateInvoice) {
		 MBPartnerLocation partnerLocation = getLocationBill(partner);
		 if (partnerLocation == null) {
			 log.log(Level.SEVERE, " @C_BPartner_Location_ID@ @NotFound@ : " + partner.getName());
			 addLog(0, process.getDateAcct(), null, "@Bill_Location_ID@ @NotFound@ "
					 + process.getDocumentNo() + " "
					 + partner.getName());
			 return null;
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
		 MInvoice invoice = new MInvoice(process.getCtx(), 0, get_TrxName());
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

		 if (employee != null && employee.getC_Activity_ID() != 0)
			 invoice.setC_Activity_ID(employee.getC_Activity_ID());

		 invoice.setDocStatus(DocAction.STATUS_Drafted);
		 invoice.setDocAction(DocAction.ACTION_None);
		 invoice.setSalesRep_ID(Env.getAD_User_ID(process.getCtx()));
		 invoice.saveEx();			
		 whereClauseWindow = whereClauseWindow + invoice.getC_Invoice_ID() + ",";
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
	    private MInvoiceLine createInvoiceLine(MInvoice invoice, MHREmployee employee, MHRMovement movement, int chargeId) {
	        MInvoiceLine invoiceLine = new MInvoiceLine(invoice);
	        invoiceLine.setC_Charge_ID(chargeId);
	        invoiceLine.setQty(BigDecimal.ONE); //
	        invoiceLine.setDescription(movement.getHR_Concept().getName());
	        invoiceLine.setC_Activity_ID(movement.getC_Activity_ID());
	        invoiceLine.setUser1_ID(movement.getUser1_ID());
	        invoiceLine.setUser2_ID(movement.getUser2_ID());
	        invoiceLine.setUser3_ID(movement.getUser3_ID());
	        invoiceLine.setUser4_ID(movement.getUser4_ID());
	        invoiceLine.setPrice(movement.getAmount());
	        invoiceLine.setTax();
	        
	        invoiceLine.saveEx();
	        addLog(invoiceLine.getLine(), invoice.getDateInvoiced(), invoiceLine.getLineNetAmt(), movement.getHR_Concept().getName());

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