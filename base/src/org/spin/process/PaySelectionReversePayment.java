/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.									  *
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

import java.util.concurrent.atomic.AtomicInteger;

import org.adempiere.core.domains.models.I_C_Payment;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MPayment;
import org.compiere.model.Query;

/** 
 * 	Reverse Payment
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *	<a href="https://github.com/adempiere/adempiere/issues/2800">
 * 		@see FR [ 2800 ] [Feature Request] Add Reverse All Payments from Payment Selection</a>
 */
public class PaySelectionReversePayment extends PaySelectionReversePaymentAbstract {
	@Override
	protected void prepare() {
		super.prepare();
		if(getRecord_ID() == 0) {
			throw new AdempiereException("@C_PaySelection_ID@ @NotFound@");
		}
	}

	@Override
	protected String doIt() throws Exception {
		AtomicInteger voided = new AtomicInteger();
		new Query(getCtx(), I_C_Payment.Table_Name, 
				"EXISTS(SELECT 1 FROM C_PaySelectionCheck psc "
				+ "	WHERE psc.C_PaySelection_ID = ? "
				+ "	AND psc.C_Payment_ID = C_Payment.C_Payment_ID)"
				+ " AND DocStatus IN('CO', 'DR', 'IP', 'IN')", get_TrxName())
		.setParameters(getRecord_ID())
		.setClient_ID()
		.<MPayment>list()
		.stream()
		.forEach(payment -> {
			if(payment.getDocStatus().equals(MPayment.DOCSTATUS_Completed)) {
				payment.processIt(MPayment.DOCACTION_Reverse_Correct);
			} else {
				payment.processIt(MPayment.DOCACTION_Void);
			}
			payment.saveEx();
			voided.addAndGet(1);
		});
		//	
		return "@Voided@: " + voided.get();
	}
}