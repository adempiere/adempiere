/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 *	Product Price
 *	
 *  @author Jorg Janke
 *  @version $Id: MProductPrice.java,v 1.3 2006/07/30 00:51:02 jjanke Exp $
 */
public class MProductPrice extends X_M_ProductPrice
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9187555438223385521L;

	/**
	 * 	Get Product Price
	 *	@param ctx ctx
	 *	@param M_PriceList_Version_ID id
	 *	@param M_Product_ID id
	 *	@param trxName trx
	 *	@return product price or null
	 */
	public static MProductPrice get (Properties ctx, int M_PriceList_Version_ID, int M_Product_ID,
		String trxName)
	{
		final String whereClause = MProductPrice.COLUMNNAME_M_PriceList_Version_ID +"=? AND "+MProductPrice.COLUMNNAME_M_Product_ID+"=?";
		MProductPrice retValue = new Query(ctx,I_M_ProductPrice.Table_Name,  whereClause, trxName)
		.setParameters(M_PriceList_Version_ID, M_Product_ID)
		.first();
		return retValue;
	}	//	get
	
	/**	Logger	*/
	private static CLogger s_log = CLogger.getCLogger (MProductPrice.class);
	
	/**
	 * 	Persistency Constructor
	 *	@param ctx context
	 *	@param ignored ignored
	 *	@param trxName transaction
	 */
	public MProductPrice (Properties ctx, int ignored, String trxName)
	{
		super(ctx, 0, trxName);
		if (ignored != 0)
			throw new IllegalArgumentException("Multi-Key");
		setPriceLimit (Env.ZERO);
		setPriceList (Env.ZERO);
		setPriceStd (Env.ZERO);
	}	//	MProductPrice

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MProductPrice (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MProductPrice

	/**
	 * 	New Constructor
	 *	@param ctx context
	 *	@param M_PriceList_Version_ID Price List Version
	 *	@param M_Product_ID product
	 *	@param trxName transaction
	 */
	public MProductPrice (Properties ctx, int M_PriceList_Version_ID, int M_Product_ID, String trxName)
	{
		this (ctx, 0, trxName);
		setM_PriceList_Version_ID (M_PriceList_Version_ID);	//	FK
		setM_Product_ID (M_Product_ID);						//	FK
	}	//	MProductPrice

	/**
	 * 	New Constructor
	 *	@param ctx context
	 *	@param M_PriceList_Version_ID Price List Version
	 *	@param M_Product_ID product
	 *	@param PriceList list price
	 *	@param PriceStd standard price
	 *	@param PriceLimit limit price
	 *	@param trxName transaction
	 */
	public MProductPrice (Properties ctx, int M_PriceList_Version_ID, int M_Product_ID,
		BigDecimal PriceList, BigDecimal PriceStd, BigDecimal PriceLimit, String trxName)
	{
		this (ctx, M_PriceList_Version_ID, M_Product_ID, trxName);
		setPrices (PriceList, PriceStd, PriceLimit);
	}	//	MProductPrice

	/**
	 * 	Parent Constructor
	 *	@param plv price list version
	 *	@param M_Product_ID product
	 *	@param PriceList list price
	 *	@param PriceStd standard price
	 *	@param PriceLimit limit price
	 */
	public MProductPrice (MPriceListVersion plv, int M_Product_ID,
		BigDecimal PriceList, BigDecimal PriceStd, BigDecimal PriceLimit)
	{
		this (plv.getCtx(), 0, plv.get_TrxName());
		setClientOrg(plv);
		setM_PriceList_Version_ID(plv.getM_PriceList_Version_ID());
		setM_Product_ID(M_Product_ID);
		setPrices (PriceList, PriceStd, PriceLimit);
	}	//	MProductPrice
	
	/**
	 * 	Set Prices
	 *	@param PriceList list price
	 *	@param PriceStd standard price
	 *	@param PriceLimit limit price
	 */
	public void setPrices (BigDecimal PriceList, BigDecimal PriceStd, BigDecimal PriceLimit)
	{
		setPriceLimit (PriceLimit.setScale(this.getM_PriceList_Version().getM_PriceList().getPricePrecision(), BigDecimal.ROUND_HALF_UP)); 
		setPriceList (PriceList.setScale(this.getM_PriceList_Version().getM_PriceList().getPricePrecision(), BigDecimal.ROUND_HALF_UP)); 
		setPriceStd (PriceStd.setScale(this.getM_PriceList_Version().getM_PriceList().getPricePrecision(), BigDecimal.ROUND_HALF_UP));
	}	//	setPrice

	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer ("MProductPrice[");
		sb.append(getM_PriceList_Version_ID())
			.append(",M_Product_ID=").append (getM_Product_ID())
			.append(",PriceList=").append(getPriceList())
			.append("]");
		return sb.toString ();
	} //	toString
	
}	//	MProductPrice
