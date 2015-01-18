/**
 * 
 */
package org.adempiere.exceptions;

import java.math.BigDecimal;

import org.compiere.model.MProduct;
import org.compiere.util.Env;


/**
 * Throwed when the cost can't be calculated at this time
 * @author Teo Sarca
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 *
 */
public class CostInsufficientQtyException extends AdempiereException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8516844627809849649L;

	/**
	 * Exception to Cost Insufficient Qty
	 * @param product_id Product
	 * @param asi_id Attribute Set Instance
	 * @param qty Quantity
	 * @param remainingQty Remaining Qty
	 */
	public CostInsufficientQtyException(int product_id, int asi_id,
			BigDecimal qty, BigDecimal remainingQty)
	{
		super(createMessage(product_id, asi_id, qty, remainingQty));
	}
	
	/**
	 * Create Msg
	 * @param product_id Product
	 * @param asi_id Attribute Set Instance
	 * @param qty Quantity
	 * @param remainingQty Remaining Qty
	 * @return String with the Msg
	 */
	private static String createMessage(int product_id, int asi_id, BigDecimal qty, BigDecimal remainingQty)
	{
		MProduct product = MProduct.get(Env.getCtx(), product_id);
		String productValue =	product != null ? product.getValue() : "?";
		String productName =	product != null ? product.getName() : "?";
		//
		return "@M_Product_ID@ : " + productValue + " - " + productName
				+", @Qty@ : " + qty
				+", @RemainingQty@ : "+remainingQty
				+" (ASI:"+asi_id+")"
		;
	}

}
