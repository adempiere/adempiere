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
import java.util.List;
import java.util.Properties;

import org.compiere.util.CCache;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;

/**
 *  Tax Model
 *
 *	@author Jorg Janke
 *	@version $Id: MTax.java,v 1.3 2006/07/30 00:51:02 jjanke Exp $
 * 	red1 - FR: [ 2214883 ] Remove SQL code and Replace for Query 
 */
public class MTax extends X_C_Tax
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4140382472528327237L;

	/**	Cache						*/
	private static CCache<Integer,MTax>		s_cache	= new CCache<Integer,MTax>(Table_Name, 5);
	/**	Cache of Client						*/
	private static CCache<Integer,MTax[]>	s_cacheAll = new CCache<Integer,MTax[]>(Table_Name, 5);
	
	/**	100					*/
	private static BigDecimal ONEHUNDRED = new BigDecimal(100);
	/**	Child Taxes			*/
	private MTax[]			m_childTaxes = null;
	/** Postal Codes		*/
	private MTaxPostal[]	m_postals = null;
	

	/**
	 * 	Get All Tax codes (for AD_Client)
	 *	@param ctx context
	 *	@return MTax
	 */
	public static MTax[] getAll (Properties ctx)
	{
		int AD_Client_ID = Env.getAD_Client_ID(ctx);
		MTax[] retValue = (MTax[])s_cacheAll.get(AD_Client_ID);
		if (retValue != null)
			return retValue;

		//	Create it
		//FR: [ 2214883 ] Remove SQL code and Replace for Query - red1
		String whereClause = "AD_Client_ID=?";
		List<MTax> list = new Query(ctx, MTax.Table_Name, whereClause, null)
								.setParameters(new Object[]{AD_Client_ID})
								.setOrderBy("C_Country_ID, C_Region_ID, To_Country_ID, To_Region_ID")
								.setOnlyActiveRecords(true)
								.list();
		for (MTax tax : list)
		{
			s_cache.put(tax.get_ID(), tax);
		}
		retValue = list.toArray(new MTax[list.size()]);
		s_cacheAll.put(AD_Client_ID, retValue);
		return retValue;
	}	//	getAll

	
	/**
	 * 	Get Tax from Cache
	 *	@param ctx context
	 *	@param C_Tax_ID id
	 *	@return MTax
	 */
	public static MTax get (Properties ctx, int C_Tax_ID)
	{
		Integer key = new Integer (C_Tax_ID);
		MTax retValue = (MTax) s_cache.get (key);
		if (retValue != null)
			return retValue;
		retValue = new MTax (ctx, C_Tax_ID, null);
		if (retValue.get_ID () != 0)
			s_cache.put (key, retValue);
		return retValue;
	}	//	get

	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_Tax_ID id
	 *	@param trxName transaction
	 */
	public MTax (Properties ctx, int C_Tax_ID, String trxName)
	{
		super (ctx, C_Tax_ID, trxName);
		if (C_Tax_ID == 0)
		{
		//	setC_Tax_ID (0);		PK
			setIsDefault (false);
			setIsDocumentLevel (true);
			setIsSummary (false);
			setIsTaxExempt (false);
		//	setName (null);
			setRate (Env.ZERO);
			setRequiresTaxCertificate (false);
		//	setC_TaxCategory_ID (0);	//	FK
			setSOPOType (SOPOTYPE_Both);
			setValidFrom (TimeUtil.getDay(1990,1,1));
			setIsSalesTax(false);
		}
	}	//	MTax

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MTax (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MTax

	/**
	 * 	New Constructor
	 *	@param ctx
	 *	@param Name
	 *	@param Rate
	 *	@param C_TaxCategory_ID
	 *	@param trxName transaction
	 */
	public MTax (Properties ctx, String Name, BigDecimal Rate, int C_TaxCategory_ID, String trxName)
	{
		this (ctx, 0, trxName);
		setName (Name);
		setRate (Rate == null ? Env.ZERO : Rate);
		setC_TaxCategory_ID (C_TaxCategory_ID);	//	FK
	}	//	MTax

	/**
	 * 	Get Child Taxes
	 * 	@param requery reload
	 *	@return array of taxes or null
	 */
	public MTax[] getChildTaxes (boolean requery)
	{
		if (!isSummary())
			return null;
		if (m_childTaxes != null && !requery)
			return m_childTaxes;
		//
		//FR: [ 2214883 ] Remove SQL code and Replace for Query - red1
		String whereClause = COLUMNNAME_Parent_Tax_ID+"=?";
		List<MTax> list = new Query(getCtx(), MTax.Table_Name, whereClause,  get_TrxName())
			.setParameters(new Object[]{getC_Tax_ID()})
			.setOnlyActiveRecords(true)
			.list();	
		//red1 - end -
	 
		m_childTaxes = new MTax[list.size ()];
		list.toArray (m_childTaxes);
		return m_childTaxes;
	}	//	getChildTaxes
	
	/**
	 * Get Postal Qualifiers
	 * @param requery requery
	 * @return array of postal codes
	 */
	public MTaxPostal[] getPostals (boolean requery)
	{
		if (m_postals != null && !requery)
			return m_postals;

		//FR: [ 2214883 ] Remove SQL code and Replace for Query - red1
		String whereClause = MTaxPostal.COLUMNNAME_C_Tax_ID+"=?";
		List<MTaxPostal> list = new Query(getCtx(), MTaxPostal.Table_Name, whereClause,  get_TrxName())
			.setParameters(new Object[]{getC_Tax_ID()})
			.setOnlyActiveRecords(true)
			.setOrderBy(MTaxPostal.COLUMNNAME_Postal+", "+MTaxPostal.COLUMNNAME_Postal_To)
			.list();	
		//red1 - end -

		if (list.size() > 0) { 
			m_postals = new MTaxPostal[list.size ()];
			list.toArray (m_postals);
		}
		return m_postals;
	}	//	getPostals
	
	/**
	 * Do we have Postal Codes
	 * @return true if postal codes exist
	 */
	public boolean isPostal()
	{
		if(getPostals(false) == null)
			return false;
		
		return getPostals(false).length > 0;
	}	//	isPostal
	
	/**
	 * Is Zero Tax
	 * @return true if tax rate is 0
	 */
	public boolean isZeroTax()
	{
		return getRate().signum() == 0;
	}	//	isZeroTax
	
	public String toString()
	{
		StringBuffer sb = new StringBuffer("MTax[")
			.append(get_ID())
			.append(", Name = ").append(getName())
			.append(", SO/PO=").append(getSOPOType())
			.append(", Rate=").append(getRate())
			.append(", C_TaxCategory_ID=").append(getC_TaxCategory_ID())
			.append(", Summary=").append(isSummary())
			.append(", Parent=").append(getParent_Tax_ID())
			.append(", Country=").append(getC_Country_ID()).append("|").append(getTo_Country_ID())
			.append(", Region=").append(getC_Region_ID()).append("|").append(getTo_Region_ID())
			.append("]");
		return sb.toString();
	}	//	toString

	
	/**
	 * 	Calculate Tax - no rounding
	 *	@param amount amount
	 *	@param taxIncluded if true tax is calculated from gross otherwise from net 
	 *	@param scale scale 
	 *	@return  tax amount
	 */
	public BigDecimal calculateTax (BigDecimal amount, boolean taxIncluded, int scale)
	{
		//	Null Tax
		if (isZeroTax())
			return Env.ZERO;
		
		BigDecimal multiplier = getRate().divide(ONEHUNDRED, 12, BigDecimal.ROUND_HALF_UP);		

		BigDecimal tax = null;		
		if (!taxIncluded)	//	$100 * 6 / 100 == $6 == $100 * 0.06
		{
			tax = amount.multiply (multiplier);
		}
		else			//	$106 - ($106 / (100+6)/100) == $6 == $106 - ($106/1.06)
		{
			multiplier = multiplier.add(Env.ONE);
			BigDecimal base = amount.divide(multiplier, 12, BigDecimal.ROUND_HALF_UP); 
			tax = amount.subtract(base);
		}
		BigDecimal finalTax = tax.setScale(scale, BigDecimal.ROUND_HALF_UP);
		log.fine("calculateTax " + amount 
			+ " (incl=" + taxIncluded + ",mult=" + multiplier + ",scale=" + scale 
			+ ") = " + finalTax + " [" + tax + "]");
		return finalTax;
	}	//	calculateTax

	@Override
	protected boolean beforeSave(boolean newRecord) {
		if (isDefault()) {
			// @Trifon - Ensure that only one tax rate is set as Default!
			String whereClause = MTax.COLUMNNAME_C_TaxCategory_ID+"=? AND IsDefault='Y'";
			List<MTax> list = new Query(getCtx(), MTax.Table_Name, whereClause,  get_TrxName())
				.setParameters(new Object[]{getC_TaxCategory_ID()})
				.setOnlyActiveRecords(true)
				.list();
			if (list.size() >= 1) {
				log.saveError("Error", Msg.parseTranslation(getCtx(), "Only one @C_Tax_ID@ per @C_TaxCategory_ID@ can be marked as Default!"));
				return false;
			}
		}
		return super.beforeSave(newRecord);
	}
	
	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (newRecord && success)
			insert_Accounting("C_Tax_Acct", "C_AcctSchema_Default", null);

		return success;
	}	//	afterSave

	/**
	 * 	Before Delete
	 *	@return true
	 */
	protected boolean beforeDelete ()
	{
		return delete_Accounting("C_Tax_Acct"); 
	}	//	beforeDelete

}	//	MTax
