package org.eevolution.cashflow.controller;

import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.core.domains.models.I_C_CashFlow;
import org.adempiere.model.GridTabWrapper;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;

/**
 * 
 * @author teo.sarca@gmail.com
 *
 */
public class CalloutCashFlow extends CalloutEngine {
	public String DueDate (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		I_C_CashFlow model = GridTabWrapper.create(mTab, I_C_CashFlow.class);
		int paymentTerm_ID = model.getC_PaymentTerm_ID();
		Timestamp dateInvoiced = model.getDateInvoiced();
		if (paymentTerm_ID <= 0 || dateInvoiced == null)
		{
			model.setDueDate(null);
			return "";
		}
		Timestamp dueDate = DB.getSQLValueTSEx(null,
				"SELECT paymentTermDueDate(C_PaymentTerm_ID, ?) FROM C_PaymentTerm WHERE C_PaymentTerm_ID=?",
				dateInvoiced, paymentTerm_ID);
		model.setDueDate(dueDate);
		
		return "";
	}
}
