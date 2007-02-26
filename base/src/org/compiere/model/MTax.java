/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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

import java.math.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import org.compiere.util.*;

/**
 *  Tax Model
 *
 *	@author Jorg Janke
 *	@version $Id: MTax.java,v 1.3 2006/07/30 00:51:02 jjanke Exp $
 */
public class MTax extends X_C_Tax
{	
	/**
	 * 	Get All Tax codes (for AD_Client)
	 *	@param ctx context
	 *	@return MTax
	 */
	public static MTax[] getAll (Properties ctx)
	{
		int AD_Client_ID = Env.getAD_Client_ID(ctx);
		Integer key = new Integer (AD_Client_ID);
		MTax[] retValue = (MTax[])s_cacheAll.get(key);
		if (retValue != null)
			return retValue;
		
		//	Create it
		String sql = "SELECT * FROM C_Tax WHERE AD_Client_ID=?"
			+ "ORDER BY C_Country_ID, C_Region_ID, To_Country_ID, To_Region_ID";
		ArrayList<MTax> list = new ArrayList<MTax>();
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt (1, AD_Client_ID);
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				MTax tax = new MTax(ctx, rs, null);
				s_cache.put (new Integer(tax.getC_Tax_ID()), tax);
				list.add (tax);
			}
			rs.close ();
			pstmt.close ();
			pstmt = null;
		} 
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		} 
		catch (Exception e)
		{
			pstmt = null;
		}
		
		//	Create Array
		retValue = new MTax[list.size ()];
		list.toArray (retValue);
		//
		s_cacheAll.put(key, retValue);
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

	/**	Cache						*/
	private static CCache<Integer,MTax>		s_cache	= new CCache<Integer,MTax>("C_Tax", 5);
	/**	Cache of Client						*/
	private static CCache<Integer,MTax[]>	s_cacheAll = new CCache<Integer,MTax[]>("C_Tax", 5);
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MTax.class);

	
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

	/**	100					*/
	private static BigDecimal ONEHUNDRED = new BigDecimal(100);
	/**	Child Taxes			*/
	private MTax[]			m_childTaxes = null;
	/** Postal Codes		*/
	private MTaxPostal[]	m_postals = null;
	
	
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
		String sql = "SELECT * FROM C_Tax WHERE Parent_Tax_ID=?";
		ArrayList<MTax> list = new ArrayList<MTax>();
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setInt (1, getC_Tax_ID());
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add(new MTax(getCtx(), rs, get_TrxName()));
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		m_childTaxes = new MTax[list.size ()];
		list.toArray (m_childTaxes);
		return m_childTaxes;
	}	//	getChildTaxes
	
	/**
	 * 	Get Postal Qualifiers
	 *	@param requery requery
	 *	@return array of postal codes
	 */
	public MTaxPostal[] getPostals (boolean requery)
	{
		if (m_postals != null && !requery)
			return m_postals;
	
		String sql = "SELECT * FROM C_TaxPostal WHERE C_Tax_ID=? ORDER BY Postal, Postal_To";
		ArrayList<MTaxPostal> list = new ArrayList<MTaxPostal>();
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setInt (1, getC_Tax_ID());
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
			{
			}
			rs.close ();
			pstmt.close ();
			pstmt = null;
		} catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		} catch (Exception e)
		{
			pstmt = null;
		}
		
		m_postals = new MTaxPostal[list.size ()];
		list.toArray (m_postals);
		return m_postals;
	}	//	getPostals
	
	/**
	 * 	Do we have Postal Codes
	 *	@return true if postal codes exist
	 */
	public boolean isPostal()
	{
		return getPostals(false).length > 0;
	}	//	isPostal
	
	/**
	 * 	Is Zero Tax
	 *	@return true if tax rate is 0
	 */
	public boolean isZeroTax()
	{
		return Env.ZERO.compareTo(getRate()) == 0;
	}	//	isZeroTax
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer("MTax[");
		sb.append(get_ID()).append(",").append(getName())
			.append(", SO/PO=").append(getSOPOType())
			.append(",Rate=").append(getRate())
			.append(",C_TaxCategory_ID=").append(getC_TaxCategory_ID())
			.append(",Summary=").append(isSummary())
			.append(",Parent=").append(getParent_Tax_ID())
			.append(",Country=").append(getC_Country_ID()).append("|").append(getTo_Country_ID())
			.append(",Region=").append(getC_Region_ID()).append("|").append(getTo_Region_ID())
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
