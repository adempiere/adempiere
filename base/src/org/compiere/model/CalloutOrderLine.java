package org.compiere.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Properties;

public class CalloutOrderLine extends CalloutEngine {

	/**
	 * Updates Date Promised Updated field if order line is not fully delivered. 
	 * 
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 */
	public String datePromised(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
		if (isCalloutActive() || value == null)
			return "";
		
		// Only do something if not all ordered are delivered
		BigDecimal qtyOrdered = (BigDecimal)mTab.getValue("QtyOrdered");
		BigDecimal qtyDelivered = (BigDecimal)mTab.getValue("QtyDelivered");
		if (qtyOrdered.subtract(qtyDelivered).signum()<1) {
			// Ordered less than Delivered. Do nothing.
			return "";
		}
		
		// Update DatePromisedUpdated
		mTab.setValue("DatePromisedUpdated", new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis()));
		
		return("");
		
	}
	
}
