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

package org.spin.freight.process;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.compiere.model.MFreightCategory;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.eevolution.model.MDDFreightLine;

/** Generated Process for (Generate AP Invoice from Freight Order)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public class GenerateAPInvoiceFromFreightOrder extends GenerateAPInvoiceFromFreightOrderAbstract {
	private MInvoice invoice;
	private AtomicInteger created = new AtomicInteger();
	
	@SuppressWarnings("unchecked")
	@Override
	protected String doIt() throws Exception {
		invoice = new MInvoice(getCtx(), getRecord_ID(), get_TrxName());
		((List<MDDFreightLine>) getInstancesForSelection(get_TrxName()))
			.stream()
			.forEach(freightLine -> createInvoiceLine(freightLine));
		//	
		return "@Created@ " + created.get();
	}
	
	
	/**
	 * Create Invoice from Freight Line
	 * @param freightLine
	 */
	private void createInvoiceLine(MDDFreightLine freightLine) {
		MFreightCategory freightCategory = MFreightCategory.getById(getCtx(), freightLine.getM_FreightCategory_ID(), get_TrxName());
		BigDecimal qtyInvoiced = Env.ZERO;
		if(Util.isEmpty(freightCategory.getFreightCalculationType())
				|| freightCategory.getFreightCalculationType().equals(MFreightCategory.FREIGHTCALCULATIONTYPE_WeightBased)) {
			qtyInvoiced = freightLine.getWeight();
		} else {
			qtyInvoiced = freightLine.getVolume();
		}
		MInvoiceLine invoiceLine = new MInvoiceLine(invoice);
		// Freight values
		invoiceLine.setDD_FreightLine_ID(freightLine.getDD_FreightLine_ID());
		if(freightLine.getM_Product_ID() != 0) {
			invoiceLine.setM_Product_ID(freightLine.getM_Product_ID());
			invoiceLine.setQty(qtyInvoiced);
			invoiceLine.setPrice(freightLine.getFreightAmt().divide(qtyInvoiced, MathContext.DECIMAL128));
			invoiceLine.setTax();
		} else if(freightLine.getC_Charge_ID() != 0){
			invoiceLine.setC_Charge_ID(freightLine.getC_Charge_ID());
			invoiceLine.setQty(Env.ONE);
			invoiceLine.setPrice(freightLine.getFreightAmt());
			invoiceLine.setTax();
		} else {
			invoiceLine.setQty(Env.ONE);
			invoiceLine.setPrice(freightLine.getFreightAmt());
		}
		//	Add description from freight line
		invoiceLine.addDescription(Msg.getMsg(getCtx(), "FreightLineDetailForAP", new Object[] {
				freightLine.getParent().getDocumentNo(), 
				freightLine.getLine(), 
				freightLine.getWeight(), 
				freightLine.getVolume(), 
				freightLine.getFreightRate(), 
				freightLine.getFreightAmt()
				}));
		invoiceLine.saveEx();
	}
}