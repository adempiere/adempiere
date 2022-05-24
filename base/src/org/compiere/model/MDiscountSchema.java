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
import java.math.MathContext;
import java.sql.ResultSet;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.compiere.util.CCache;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;

/**
 *	Discount Schema Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MDiscountSchema.java,v 1.3 2006/07/30 00:51:04 jjanke Exp $
 */
public class MDiscountSchema extends X_M_DiscountSchema
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3314884382853756019L;


	/**
	 * 	Get Discount Schema from Cache
	 *	@param ctx context
	 *	@param M_DiscountSchema_ID id
	 *	@return MDiscountSchema
	 */
	public static MDiscountSchema get (Properties ctx, int M_DiscountSchema_ID)
	{
		Integer key = Integer.valueOf(M_DiscountSchema_ID);
		MDiscountSchema retValue = (MDiscountSchema) s_cache.get (key);
		if (retValue != null)
			return retValue;
		retValue = new MDiscountSchema (ctx, M_DiscountSchema_ID, null);
		if (retValue.get_ID () != 0)
			s_cache.put (key, retValue);
		return retValue;
	}	//	get

	/**	Cache						*/
	private static CCache<Integer,MDiscountSchema>	s_cache
		= new CCache<Integer,MDiscountSchema>("M_DiscountSchema", 20);

	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param M_DiscountSchema_ID id
	 *	@param trxName transaction
	 */
	public MDiscountSchema (Properties ctx, int M_DiscountSchema_ID, String trxName)
	{
		super (ctx, M_DiscountSchema_ID, trxName);
		if (M_DiscountSchema_ID == 0)
		{
		//	setName();
			setDiscountType (DISCOUNTTYPE_FlatPercent);
			setFlatDiscount(Env.ZERO);
			setIsBPartnerFlatDiscount (false);
			setIsQuantityBased (true);	// Y
			setCumulativeLevel(CUMULATIVELEVEL_Line);
		//	setValidFrom (new Timestamp(System.currentTimeMillis()));
		}	
	}	//	MDiscountSchema

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MDiscountSchema (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MDiscountSchema

	/**	Breaks							*/
	private List<MDiscountSchemaBreak>	breaks  = null;
	/**	Lines							*/
	private List<MDiscountSchemaLine>	lines  = null;
	
	/**
	 * Get Break as List
	 * @param reload
	 * @return
	 */
	public List<MDiscountSchemaBreak> getBreaksAsList(boolean reload) {
		if (breaks != null && !reload) {
			return breaks;
		}
		//	Get from Query
		breaks = new Query(getCtx(), I_M_DiscountSchemaBreak.Table_Name, I_M_DiscountSchemaBreak.COLUMNNAME_M_DiscountSchema_ID + " = ?", get_TrxName())
				.setParameters(getM_DiscountSchema_ID())
				.setOrderBy(I_M_DiscountSchemaBreak.COLUMNNAME_SeqNo)
				.list();
		return breaks;
	}
	
	/**
	 * Get Lines as List
	 * @param reload
	 * @return
	 */
	public List<MDiscountSchemaLine> getLinesAsList(boolean reload) {
		if (lines != null && !reload) {
			return lines;
		}
		//	Get from Query
		lines = new Query(getCtx(), I_M_DiscountSchemaLine.Table_Name, I_M_DiscountSchemaLine.COLUMNNAME_M_DiscountSchema_ID + " = ?", get_TrxName())
				.setParameters(getM_DiscountSchema_ID())
				.setOrderBy(I_M_DiscountSchemaLine.COLUMNNAME_SeqNo)
				.list();
		return lines;
	}
	
	/**
	 * 	Get Breaks
	 *	@param reload reload
	 *	@return breaks
	 */
	public MDiscountSchemaBreak[] getBreaks(boolean reload) {
		getBreaksAsList(reload);
		MDiscountSchemaBreak [] breaksAsArray = new MDiscountSchemaBreak[breaks.size ()];
		breaks.toArray(breaksAsArray);
		set_TrxName(breaksAsArray, get_TrxName());
		return breaksAsArray;
	}	//	getBreaks
	
	/**
	 * 	Get Lines
	 *	@param reload reload
	 *	@return lines
	 */
	public MDiscountSchemaLine[] getLines(boolean reload) {
		getLinesAsList(reload);
		MDiscountSchemaLine[] linesAsArray = new MDiscountSchemaLine[lines.size ()];
		lines.toArray (linesAsArray);
		set_TrxName(linesAsArray, get_TrxName());
		return linesAsArray;
	}	//	getBreaks

	/**
	 * 	Calculate Discounted Price
	 *	@param Qty quantity
	 *	@param Price price
	 *	@param M_Product_ID product
	 *	@param M_Product_Category_ID category
	 *	@param BPartnerFlatDiscount flat discount
	 *	@return discount or zero
	 */
	public BigDecimal calculatePrice (BigDecimal Qty, BigDecimal Price,  
		int M_Product_ID, int M_Product_Category_ID,  
		BigDecimal BPartnerFlatDiscount)
	{
		log.fine("Price=" + Price + ",Qty=" + Qty +
		         ",M_Product_ID=" + M_Product_ID +
				 ",M_Product_Category_ID=" + M_Product_Category_ID +
				 ",BPartnerFlatDiscount=" + BPartnerFlatDiscount.doubleValue() );
		
		if (Price == null || Env.ZERO.compareTo(Price) == 0)
			return Price;
		//
		BigDecimal discount = calculateDiscount(Qty, Price, 
			M_Product_ID, M_Product_Category_ID, BPartnerFlatDiscount);
		log.fine("Discount=" + discount.doubleValue());
		//	nothing to calculate
		if (discount == null || discount.signum() == 0)
			return Price;
		//
		BigDecimal onehundred = new BigDecimal(100);
		BigDecimal multiplier = (onehundred).subtract(discount);
		multiplier = multiplier.divide(onehundred, MathContext.DECIMAL128);
		BigDecimal newPrice = Price.multiply(multiplier);
		log.fine("=>" + newPrice);
		return newPrice;
	}	//	calculatePrice

	/**
	 * 	Calculate Discount Percentage
	 *	@param quantity quantity
	 *	@param Price price
	 *	@param productId product
	 *	@param productCategoryId category
	 *	@param BPartnerFlatDiscount flat discount
	 *	@return discount or zero
	 */
	public BigDecimal calculateDiscount (BigDecimal quantity, BigDecimal Price,  
		int productId, int productCategoryId,
		BigDecimal BPartnerFlatDiscount)
	{
		if (BPartnerFlatDiscount == null)
			BPartnerFlatDiscount = Env.ZERO;
		
		//
		if (DISCOUNTTYPE_FlatPercent.equals(getDiscountType()))
		{
			if (isBPartnerFlatDiscount())
				return BPartnerFlatDiscount;
			return getFlatDiscount();
		}
		//	Not supported
		else if (DISCOUNTTYPE_Formula.equals(getDiscountType())
			|| DISCOUNTTYPE_Pricelist.equals(getDiscountType()))
		{
			log.info ("Not supported (yet) DiscountType=" + getDiscountType());
			return Env.ZERO;
		}
		
		//	Price Breaks
		getBreaksAsList(true);
		BigDecimal amount = Price.multiply(quantity);
		if (isQuantityBased())
			log.finer("Qty=" + quantity + ",M_Product_ID=" + productId + ",M_Product_Category_ID=" + productCategoryId);
		else
			log.finer("Amt=" + amount + ",M_Product_ID=" + productId + ",M_Product_Category_ID=" + productCategoryId);
		//	First for product
		Optional<MDiscountSchemaBreak> maybeDiscount = breaks.stream()
			.filter(breakLine -> breakLine.isActive() && breakLine.getM_Product_ID() > 0)
			.filter(breakLine -> breakLine.applies((isQuantityBased()? quantity: amount), productId, productCategoryId))
			.sorted(Comparator.comparing(MDiscountSchemaBreak::getSeqNo))
			.findFirst();
		//	for Category
		if(!maybeDiscount.isPresent()) {
			maybeDiscount = breaks.stream()
					.filter(breakLine -> breakLine.isActive() && breakLine.getM_Product_Category_ID() > 0)
					.filter(breakLine -> breakLine.applies((isQuantityBased()? quantity: amount), productId, productCategoryId))
					.sorted(Comparator.comparing(MDiscountSchemaBreak::getSeqNo))
					.findFirst();
		}
		//	For any
		if(!maybeDiscount.isPresent()) {
			maybeDiscount = breaks.stream()
					.filter(breakLine -> breakLine.isActive() && breakLine.getM_Product_Category_ID() == 0 && breakLine.getM_Product_ID() == 0)
					.filter(breakLine -> breakLine.applies((isQuantityBased()? quantity: amount), productId, productCategoryId))
					.sorted(Comparator.comparing(MDiscountSchemaBreak::getSeqNo))
					.findFirst();
		}
		//	If exist any match
		if(maybeDiscount.isPresent()) {
			//	Line applies
			BigDecimal discount = null;
			if (maybeDiscount.get().isBPartnerFlatDiscount()) {
				discount = BPartnerFlatDiscount;
			} else {
				discount = maybeDiscount.get().getBreakDiscount();
			}
			log.fine("Discount=>" + discount);
			return discount;
		}
		return Env.ZERO;
	}	//	calculateDiscount
	
	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		if (getValidFrom() == null)
			setValidFrom (TimeUtil.getDay(null));

		return true;
	}	//	beforeSave
	
	/**
	 * 	Renumber
	 *	@return lines updated
	 */
	public int reSeq()
	{
		int count = 0;
		//	Lines
		MDiscountSchemaLine[] lines = getLines(true);
		for (int i = 0; i < lines.length; i++)
		{
			int line = (i+1) * 10;
			if (line != lines[i].getSeqNo())
			{
				lines[i].setSeqNo(line);
				if (lines[i].save())
					count++;
			}
		}
		lines = null;
		
		//	Breaks
		MDiscountSchemaBreak[] breaks = getBreaks(true);
		for (int i = 0; i < breaks.length; i++)
		{
			int line = (i+1) * 10;
			if (line != breaks[i].getSeqNo())
			{
				breaks[i].setSeqNo(line);
				if (breaks[i].save())
					count++;
			}
		}
		breaks = null;
		return count;
	}	//	reSeq
	
}	//	MDiscountSchema
