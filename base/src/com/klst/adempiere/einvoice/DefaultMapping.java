package com.klst.adempiere.einvoice;

import java.math.BigDecimal;

import org.compiere.model.I_C_Order;
import org.compiere.model.MInvoice;

import com.klst.einvoice.unece.uncefact.Quantity;

public class DefaultMapping implements InterfaceMapping {

	@Override
	public Quantity mapToQuantity(String unitCode, BigDecimal quantity) {
		return new Quantity(mapUoM(unitCode), quantity);
	}

	@Override
	public String mapUoM(String unitCode) {
		if(unitCode==null) return null;
		return unitCode.toUpperCase();
	}

	@Override
	public String mapBuyerReference(MInvoice mInvoice) {
		String mPOReference = mInvoice.getPOReference();
		if(mPOReference==null) {
			I_C_Order order = mInvoice.getC_Order();
			if(order.getPOReference()==null) {
				mPOReference = "Order " +order.getDocumentNo() + ", DateOrdered " +order.getDateOrdered().toString().substring(0, 10);
			} else {
				mPOReference = order.getPOReference();
			}
		}
		return mPOReference;
	}

	// Invoice note BT-22 is optional
	@Override
	public String mapNote(MInvoice mInvoice) {
		return null;
	}

}
