package org.eevolution.manufacturing.exceptions;

import java.sql.Timestamp;

import org.adempiere.core.domains.models.I_PP_Product_BOM;
import org.adempiere.exceptions.AdempiereException;

/**
 * Thrown when BOM is not valid on given date
 * @author Teo Sarca
 */
public class BOMExpiredException extends AdempiereException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3084324343550833077L;

	public BOMExpiredException(I_PP_Product_BOM bom, Timestamp date)
	{
		super(buildMessage(bom, date));
	}
	
	private static final String buildMessage(I_PP_Product_BOM bom, Timestamp date)
	{
		return "@NotValid@ @PP_Product_BOM_ID@:"+bom.getValue()+" - @Date@:"+date;
	}
}
