/**
 * 
 */
package org.adempiere.exceptions;

import java.text.DateFormat;

import org.compiere.model.MPriceList;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPricing;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;

/**
 * Throw when product price is not found in price list
 * @author teo.sarca@gmail.com
 *			<li>FR [ 2872255 ] Introduce ProductNotOnPriceListException
 *				https://sourceforge.net/tracker/?func=detail&aid=2872255&group_id=176962&atid=879335
 */
public class ProductNotOnPriceListException extends AdempiereException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3505579126676698444L;
	
	public static final String AD_Message = "ProductNotOnPriceList";
	
	public ProductNotOnPriceListException(MProductPricing productPricing, int documentLineNo)
	{
		super(buildMessage(productPricing, documentLineNo));
	}

	private static final String buildMessage (MProductPricing pp, int documentLineNo)
	{
		StringBuffer sb = new StringBuffer();
		if (documentLineNo > 0)
		{
			if (sb.length() > 0)
				sb.append(", ");
			sb.append("@Line@:").append(documentLineNo);
		}
		if (pp.getM_Product_ID() > 0)
		{
			MProduct p = MProduct.get(Env.getCtx(), pp.getM_Product_ID());
			if (sb.length() > 0)
				sb.append(", ");
			sb.append("@M_Product_ID@:").append(p == null ? "?" : p.get_Translation(MProduct.COLUMNNAME_Name));
		}
		if (pp.getM_PriceList_ID() > 0)
		{
			MPriceList pl = MPriceList.get(Env.getCtx(), pp.getM_PriceList_ID(), null);
			if (sb.length() > 0)
				sb.append(", ");
			sb.append("@M_PriceList_ID@:").append(pl == null ? "?" : pl.get_Translation(MPriceList.COLUMNNAME_Name));
		}
		if (pp.getPriceDate() != null)
		{
			DateFormat df = DisplayType.getDateFormat(DisplayType.Date);
			if (sb.length() > 0)
				sb.append(", ");
			sb.append("@Date@:").append(df.format(pp.getPriceDate()));
		}
		//
		sb.insert(0, "@"+AD_Message+"@ - ");
		return sb.toString();
	}
}
