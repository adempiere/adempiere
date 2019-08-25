/*************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                              *
 * This program is free software; you can redistribute it and/or modify it    		 *
 * under the terms version 2 or later of the GNU General Public License as published *
 * by the Free Software Foundation. This program is distributed in the hope   		 *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 		 *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           		 *
 * See the GNU General Public License for more details.                       		 *
 * You should have received a copy of the GNU General Public License along    		 *
 * with this program; if not, write to the Free Software Foundation, Inc.,    		 *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     		 *
 * For the text or an alternative of this public license, you may reach us    		 *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved. *
 * Contributor(s): Yamel Senih www.erpya.com				  		                 *
 *************************************************************************************/
package org.spin.process;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MRequisitionLine;
import org.compiere.model.MRfQ;
import org.compiere.model.MRfQLine;
import org.compiere.model.MRfQLineQty;
import org.compiere.util.Util;

/**
 * Class for create lines of Request for Quotation from material requisition
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * See: https://github.com/adempiere/adempiere/issues/2728
 */
public class RFQCreateFromRequisition extends RFQCreateFromRequisitionAbstract {
	
	@Override
	protected void prepare() {
		super.prepare();
		if(getRecord_ID() == 0) {
			throw new AdempiereException("@Record_ID@ @NotFound@");
		}
	}
	
	@Override
	protected String doIt() throws Exception {
		AtomicInteger created = new AtomicInteger();
		AtomicInteger line = new AtomicInteger(10);
		
		MRfQ requestForQuotation = new MRfQ(getCtx(), getRecord_ID(), get_TrxName());
		Optional<MRfQLine> lineToEvaluate = Arrays.asList(requestForQuotation.getLines())
				.stream()
				.sorted(Comparator.comparing(MRfQLine::getLine).reversed())
				.findFirst();
		if(lineToEvaluate.isPresent()) {
			line.set(lineToEvaluate.get().getLine() + 10);
		}
		getSelectionKeys().forEach(key -> {
			MRequisitionLine requisitionLine = new MRequisitionLine(getCtx(), getSelectionAsInt(key, "RL_M_RequisitionLine_ID"), get_TrxName());
			MRfQLine requiestForQuotationLine = new MRfQLine(requestForQuotation);
			requiestForQuotationLine.setLine(line.getAndAdd(10));
			requiestForQuotationLine.setM_Product_ID(requisitionLine.getM_Product_ID());
			if(!Util.isEmpty(requisitionLine.getDescription())) {
				requiestForQuotationLine.setDescription(requisitionLine.getDescription());
			}
			if(requisitionLine.getM_AttributeSetInstance_ID() != 0) {
				requiestForQuotationLine.setM_AttributeSetInstance_ID(requisitionLine.getM_AttributeSetInstance_ID());
			}
			requiestForQuotationLine.saveEx();
			//	Create Quantity
			MRfQLineQty requestForQuotationLineQuantity = new MRfQLineQty(requiestForQuotationLine);
			requestForQuotationLineQuantity.setQty(requisitionLine.getQty());
			requestForQuotationLineQuantity.setC_UOM_ID(requisitionLine.getC_UOM_ID());
			requestForQuotationLineQuantity.setIsPurchaseQty(true);
			requestForQuotationLineQuantity.saveEx(get_TrxName());
			//	Set reference
			requisitionLine.setC_RfQLine_ID(requestForQuotationLineQuantity.getC_RfQLine_ID());
			requisitionLine.saveEx();
		});
		return "@Created@ " + created;
	}
}
