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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.engine.CostEngineFactory;
import org.adempiere.engine.IDocumentLine;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *	Match PO Model.
 *	= Created when processing Shipment or Order
 *	- Updates Order (delivered, invoiced)
 *	- Creates PPV acct
 *	
 *  @author Jorg Janke
 *  @version $Id: MMatchPO.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 *  
 *  @author Bayu Cahya, Sistematika
 *  		<li>BF [ 2240484 ] Re MatchingPO, MMatchPO doesn't contains Invoice info
 *  
 *  @author Teo Sarca, www.arhipac.ro
 *  		<li>BF [ 2314749 ] MatchPO not considering currency PriceMatchDifference
 *
 *  @author Armen Rizal, Goodwill Consulting
 *  		<li>BF [ 2215840 ] MatchPO Bug Collection
 *  		<li>BF [ 2858043 ] Correct Included Tax in Average Costing
 *
 *  @author victor.perez@e-evolution.com, e-Evolution http://www.e-evolution.com
 * 			<li> FR [ 2520591 ] Support multiples calendar for Org 
 *			@see http://sourceforge.net/tracker2/?func=detail&atid=879335&aid=2520591&group_id=176962 
 */
public class MMatchPO extends X_M_MatchPO implements IDocumentLine
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7189366329684552916L;


	/**
	 * 	Get PO Match with order/invoice
	 *	@param ctx context
	 *	@param orderLineId order
	 *	@param invoiceLineId invoice
	 *	@param trxName transaction
	 *	@return array of matches
	 */
	public static MMatchPO[] get (Properties ctx, 
		int orderLineId, int invoiceLineId, String trxName)
	{
		if (orderLineId == 0 || invoiceLineId == 0)
			return new MMatchPO[]{};
		//
		String sql = "SELECT * FROM M_MatchPO WHERE C_OrderLine_ID=? AND C_InvoiceLine_ID=?";
		ArrayList<MMatchPO> list = new ArrayList<MMatchPO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			pstmt.setInt (1, orderLineId);
			pstmt.setInt (2, invoiceLineId);
			rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add (new MMatchPO (ctx, rs, trxName));
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, sql, e); 
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		MMatchPO[] retValue = new MMatchPO[list.size()];
		list.toArray (retValue);
		return retValue;
	}	//	get

    /**
     * get Match PO entity
     * @param ioLine
     * @return
     */
	public static List<MMatchPO> getInOutLine (MInOutLine ioLine)
	{
		return new Query(ioLine.getCtx(), MMatchPO.Table_Name,  MMatchPO.COLUMNNAME_M_InOutLine_ID + "=?" , ioLine.get_TrxName())
		.setClient_ID()
		.setParameters(ioLine.getM_InOutLine_ID())
		.list();
	}
	
	/**
	 * 	Get PO Matches of receipt
	 *	@param ctx context
	 *	@param M_InOut_ID receipt
	 *	@param trxName transaction
	 *	@return array of matches
	 */
	public static MMatchPO[] getInOut (Properties ctx, 
		int M_InOut_ID, String trxName)
	{
		if (M_InOut_ID == 0)
			return new MMatchPO[]{};
		//
		String sql = "SELECT * FROM M_MatchPO m"
			+ " INNER JOIN M_InOutLine l ON (m.M_InOutLine_ID=l.M_InOutLine_ID) "
			+ "WHERE l.M_InOut_ID=?"; 
		ArrayList<MMatchPO> list = new ArrayList<MMatchPO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			pstmt.setInt (1, M_InOut_ID);
			rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add (new MMatchPO (ctx, rs, trxName));
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, sql, e); 
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		MMatchPO[] retValue = new MMatchPO[list.size()];
		list.toArray (retValue);
		return retValue;
	}	//	getInOut

	/**
	 * 	Get PO Matches of Invoice
	 *	@param ctx context
	 *	@param C_Invoice_ID invoice
	 *	@param trxName transaction
	 *	@return array of matches
	 */
	public static MMatchPO[] getInvoice (Properties ctx, 
		int C_Invoice_ID, String trxName)
	{
		if (C_Invoice_ID == 0)
			return new MMatchPO[]{};
		//
		String sql = "SELECT * FROM M_MatchPO mi"
			+ " INNER JOIN C_InvoiceLine il ON (mi.C_InvoiceLine_ID=il.C_InvoiceLine_ID) "
			+ "WHERE il.C_Invoice_ID=?";
		ArrayList<MMatchPO> list = new ArrayList<MMatchPO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			pstmt.setInt (1, C_Invoice_ID);
			rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add (new MMatchPO (ctx, rs, trxName));
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, sql, e); 
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		MMatchPO[] retValue = new MMatchPO[list.size()];
		list.toArray (retValue);
		return retValue;
	}	//	getInvoice

	// MZ Goodwill
	/**
	 * 	Get PO Matches for OrderLine
	 *	@param ctx context
	 *	@param C_OrderLine_ID order
	 *	@param trxName transaction
	 *	@return array of matches
	 */
	public static MMatchPO[] getOrderLine (Properties ctx, int C_OrderLine_ID, String trxName)
	{
		if (C_OrderLine_ID == 0)
			return new MMatchPO[]{};
		//
		String sql = "SELECT * FROM M_MatchPO WHERE C_OrderLine_ID=?";
		ArrayList<MMatchPO> list = new ArrayList<MMatchPO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			pstmt.setInt (1, C_OrderLine_ID);
			rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add (new MMatchPO (ctx, rs, trxName));
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, sql, e); 
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		MMatchPO[] retValue = new MMatchPO[list.size()];
		list.toArray (retValue);
		return retValue;
	}	//	getOrderLine
	// end MZ
	
	/**
	 * 	Find/Create PO(Inv) Match
	 *	@param iLine invoice line
	 *	@param sLine receipt line
	 *	@param dateTrx date
	 *	@param qty qty
	 *	@return Match Record
	 */
	public static MMatchPO create (MInvoiceLine iLine, MInOutLine sLine,  
		Timestamp dateTrx, BigDecimal qty)
	{
		String trxName = null;
		Properties ctx = null;
		int C_OrderLine_ID = 0;
		if (iLine != null)
		{
			trxName = iLine.get_TrxName();
			ctx = iLine.getCtx();
			C_OrderLine_ID = iLine.getC_OrderLine_ID();
		}
		if (sLine != null)
		{
			trxName = sLine.get_TrxName();
			ctx = sLine.getCtx();
			C_OrderLine_ID = sLine.getC_OrderLine_ID();
		}
		
		MMatchPO retValue = null;
		String sql = "SELECT * FROM M_MatchPO WHERE C_OrderLine_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			pstmt.setInt (1, C_OrderLine_ID);
			rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				MMatchPO mpo = new MMatchPO (ctx, rs, trxName);
				if (qty.compareTo(mpo.getQty()) == 0)
				{
					if (iLine != null)
					{
						if (mpo.getC_InvoiceLine_ID() == 0 
							|| mpo.getC_InvoiceLine_ID() == iLine.getC_InvoiceLine_ID())
						{
							mpo.setC_InvoiceLine_ID(iLine);
							if (iLine.getM_AttributeSetInstance_ID() != 0)
							{
								if (mpo.getM_AttributeSetInstance_ID() == 0)
									mpo.setM_AttributeSetInstance_ID(iLine.getM_AttributeSetInstance_ID());
								else if (mpo.getM_AttributeSetInstance_ID() != iLine.getM_AttributeSetInstance_ID())
									continue;
							}
						}
						else
							continue;
					}
					if (sLine != null)
					{
						if (mpo.getM_InOutLine_ID() == 0 
							|| mpo.getM_InOutLine_ID() == sLine.getM_InOutLine_ID())
						{
							mpo.setM_InOutLine_ID(sLine.getM_InOutLine_ID());
							if (sLine.getM_AttributeSetInstance_ID() != 0)
							{
								if (mpo.getM_AttributeSetInstance_ID() == 0)
									mpo.setM_AttributeSetInstance_ID(sLine.getM_AttributeSetInstance_ID());
								else if (mpo.getM_AttributeSetInstance_ID() != sLine.getM_AttributeSetInstance_ID())
									continue;
							}
						}
						else
							continue;
					}
					retValue = mpo;
					break;
				}
			}
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, sql, e); 
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		
		//	Create New
		if (retValue == null)
		{
			if (sLine != null)
			{
				retValue = new MMatchPO (sLine, dateTrx, qty);
				if (iLine != null)
					retValue.setC_InvoiceLine_ID(iLine);
			}
			else if (iLine != null)
			{
				retValue = new MMatchPO (iLine, dateTrx, qty);
			}
		}
		
		return retValue;
	}	//	create
	
	
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MMatchPO.class);

	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param M_MatchPO_ID id
	 *	@param trxName transaction
	 */
	public MMatchPO (Properties ctx, int M_MatchPO_ID, String trxName)
	{
		super (ctx, M_MatchPO_ID, trxName);
		if (M_MatchPO_ID == 0)
		{
		//	setC_OrderLine_ID (0);
		//	setDateTrx (new Timestamp(System.currentTimeMillis()));
		//	setM_InOutLine_ID (0);
		//	setM_Product_ID (0);
			setM_AttributeSetInstance_ID(0);
		//	setQty (Env.ZERO);
			setPosted (false);
			setProcessed (false);
			setProcessing (false);
		}
	}	//	MMatchPO

	/**
	 * 	Load Construor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MMatchPO (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MMatchPO
	
	/**
	 * 	Shipment Line Constructor
	 *	@param sLine shipment line
	 *	@param dateTrx optional date
	 *	@param qty matched quantity
	 */
	public MMatchPO (MInOutLine sLine, Timestamp dateTrx, BigDecimal qty)
	{
		this (sLine.getCtx(), 0, sLine.get_TrxName());
		setClientOrg(sLine);
		setM_InOutLine_ID (sLine.getM_InOutLine_ID());
		setC_OrderLine_ID (sLine.getC_OrderLine_ID());
		if (dateTrx != null)
			setDateTrx (dateTrx);
		setM_Product_ID (sLine.getM_Product_ID());
		setM_AttributeSetInstance_ID(sLine.getM_AttributeSetInstance_ID());
		setQty (qty);
		setProcessed(true);		//	auto
	}	//	MMatchPO

	/**
	 * 	Invoice Line Constructor
	 *	@param iLine invoice line
	 *	@param dateTrx optional date
	 *	@param qty matched quantity
	 */
	public MMatchPO (MInvoiceLine iLine, Timestamp dateTrx, BigDecimal qty)
	{
		this (iLine.getCtx(), 0, iLine.get_TrxName());
		setClientOrg(iLine);
		setC_InvoiceLine_ID(iLine);
		if (iLine.getC_OrderLine_ID() != 0)
			setC_OrderLine_ID (iLine.getC_OrderLine_ID());
		if (dateTrx != null)
			setDateTrx (dateTrx);
		setM_Product_ID (iLine.getM_Product_ID());
		setM_AttributeSetInstance_ID(iLine.getM_AttributeSetInstance_ID());
		setQty (qty);
		setProcessed(true);		//	auto
	}	//	MMatchPO
	
	/** Invoice Changed			*/
	private boolean m_isInvoiceLineChange = false;
	/** InOut Changed			*/
	private boolean m_isInOutLineChange = false;
	/** Order Line				*/
	private MOrderLine		m_oLine = null;
	/** Invoice Line			*/
	private MInvoiceLine	m_iLine = null;
	
	
	/**
	 * 	Set C_InvoiceLine_ID
	 *	@param line line
	 */
	public void setC_InvoiceLine_ID (MInvoiceLine line)
	{
		m_iLine = line;
		if (line == null)
			setC_InvoiceLine_ID(0);
		else
			setC_InvoiceLine_ID(line.getC_InvoiceLine_ID());
	}	//	setC_InvoiceLine_ID

	/**
	 * 	Set C_InvoiceLine_ID
	 *	@param C_InvoiceLine_ID id
	 */
	public void setC_InvoiceLine_ID (int C_InvoiceLine_ID)
	{
		int old = getC_InvoiceLine_ID();
		if (old != C_InvoiceLine_ID)
		{
			super.setC_InvoiceLine_ID (C_InvoiceLine_ID);
			m_isInvoiceLineChange = true;
		}
	}	//	setC_InvoiceLine_ID

	/**
	 * 	Get Invoice Line
	 *	@return invoice line or null
	 */
	public MInvoiceLine getInvoiceLine()
	{
		if (m_iLine == null && getC_InvoiceLine_ID() != 0)
			m_iLine = new MInvoiceLine(getCtx(), getC_InvoiceLine_ID(), get_TrxName());
		return m_iLine;
	}	//	getInvoiceLine
	
	/**
	 * 	Set M_InOutLine_ID
	 *	@param M_InOutLine_ID id
	 */
	public void setM_InOutLine_ID (int M_InOutLine_ID)
	{
		int old = getM_InOutLine_ID();
		if (old != M_InOutLine_ID)
		{
			super.setM_InOutLine_ID (M_InOutLine_ID);
			m_isInOutLineChange = true;
		}
	}	//	setM_InOutLine_ID
	
	/**
	 * 	Set C_OrderLine_ID
	 *	@param line line
	 */
	public void setC_OrderLine_ID (MOrderLine line)
	{
		m_oLine = line;
		if (line == null)
			setC_OrderLine_ID(0);
		else
			setC_OrderLine_ID(line.getC_OrderLine_ID());
	}	//	setC_InvoiceLine_ID

	/**
	 * 	Get Order Line
	 *	@return order line or null
	 */
	public MOrderLine getOrderLine()
	{
		if ((m_oLine == null && getC_OrderLine_ID() != 0) 
			|| getC_OrderLine_ID() != m_oLine.getC_OrderLine_ID())
			m_oLine = new MOrderLine(getCtx(), getC_OrderLine_ID(), get_TrxName());
		return m_oLine;
	}	//	getOrderLine
	
	/**
	 * Get PriceActual from Invoice and convert it to Order Currency
	 * @return Price Actual in Order Currency
	 */
	public BigDecimal getInvoicePriceActual()
	{
		MInvoiceLine iLine = getInvoiceLine();
		MInvoice invoice = iLine.getParent();
		MOrder order = getOrderLine().getParent();

		BigDecimal priceActual = iLine.getPriceActual();
		int invoiceCurrency_ID = invoice.getC_Currency_ID();
		int orderCurrency_ID = order.getC_Currency_ID();
		if (invoiceCurrency_ID != orderCurrency_ID)
		{
			priceActual = MConversionRate.convert(getCtx(), priceActual, invoiceCurrency_ID, orderCurrency_ID,
										invoice.getDateInvoiced(), invoice.getC_ConversionType_ID(),
										getAD_Client_ID(), getAD_Org_ID());
		}
		return priceActual;
	}
	
	@Override
	protected boolean beforeSave (boolean newRecord)
	{
		//	Set Trx Date
		if (getDateTrx() == null)
			setDateTrx (new Timestamp(System.currentTimeMillis()));
		//	Set Acct Date
		if (getDateAcct() == null)
		{
			Timestamp ts = getNewerDateAcct();
			if (ts == null)
				ts = getDateTrx();
			setDateAcct (ts);
		}
		//	Set ASI from Receipt
		if (getM_AttributeSetInstance_ID() == 0 && getM_InOutLine_ID() != 0)
		{
			MInOutLine iol = new MInOutLine (getCtx(), getM_InOutLine_ID(), get_TrxName());
			setM_AttributeSetInstance_ID(iol.getM_AttributeSetInstance_ID());
		}
		
		// Bayu, Sistematika
		// BF [ 2240484 ] Re MatchingPO, MMatchPO doesn't contains Invoice info
		// If newRecord, set c_invoiceline_id while null
		if (newRecord && getC_InvoiceLine_ID() == 0) 
		{
			MInOutLine line =  (MInOutLine) getM_InOutLine();
			List<MMatchInv> matches = MMatchInv.getInOutLine(line);
			for (MMatchInv match : matches)
			{
				if (match.getC_InvoiceLine_ID() != 0 && 
						match.getM_AttributeSetInstance_ID() == getM_AttributeSetInstance_ID()) 
				{
					setC_InvoiceLine_ID(match.getC_InvoiceLine_ID());
					break;
				}
			}
		}
		// end Bayu
		
		//	Find OrderLine
		if (getC_OrderLine_ID() == 0)
		{
			MInvoiceLine il = null;
			if (getC_InvoiceLine_ID() != 0)
			{
				il = getInvoiceLine();
				if (il.getC_OrderLine_ID() != 0)
					setC_OrderLine_ID(il.getC_OrderLine_ID());
			}	//	get from invoice
			if (getC_OrderLine_ID() == 0 && getM_InOutLine_ID() != 0)
			{
				MInOutLine iol = new MInOutLine (getCtx(), getM_InOutLine_ID(), get_TrxName());
				if (iol.getC_OrderLine_ID() != 0)
				{
					setC_OrderLine_ID(iol.getC_OrderLine_ID());
					if (il != null)
					{
						il.setC_OrderLine_ID(iol.getC_OrderLine_ID());
						il.save();
					}
				}
			}	//	get from shipment
		}	//	find order line
		
		//	Price Match Approval
		if (getC_OrderLine_ID() != 0 
			&& getC_InvoiceLine_ID() != 0
			&& (newRecord || 
				is_ValueChanged("C_OrderLine_ID") || is_ValueChanged("C_InvoiceLine_ID")))
		{
			BigDecimal poPrice = getOrderLine().getPriceActual();
			BigDecimal invPrice = getInvoicePriceActual();
			BigDecimal difference = poPrice.subtract(invPrice);
			if (difference.signum() != 0)
			{
				difference = difference.multiply(getQty());
				setPriceMatchDifference(difference);
				//	Approval
				MBPGroup group = MBPGroup.getOfBPartner(getCtx(), getOrderLine().getC_BPartner_ID());
				BigDecimal mt = group.getPriceMatchTolerance();
				if (mt != null && mt.signum() != 0)
				{
					BigDecimal poAmt = poPrice.multiply(getQty());
					BigDecimal maxTolerance = poAmt.multiply(mt);
					maxTolerance = maxTolerance.abs()
						.divide(Env.ONEHUNDRED, 2, BigDecimal.ROUND_HALF_UP);
					difference = difference.abs();
					boolean ok = difference.compareTo(maxTolerance) <= 0;
					log.config("Difference=" + getPriceMatchDifference() 
						+ ", Max=" + maxTolerance + " => " + ok);
					setIsApproved(ok);
				}
			}
			else
			{
				setPriceMatchDifference(difference);
				setIsApproved(true);
			}
		}
		
		if (newRecord || m_isInOutLineChange)
		{	
			// Elaine 2008/6/20	
			/*String err = createMatchPOCostDetail();
			if(err != null && err.length() > 0) 
			{
				s_log.warning(err);
				return false;
			}*/
			
		}
		
		return true;
	}	//	beforeSave	
	
	/**
	 * 	After Save.
	 * 	Set Order Qty Delivered/Invoiced 
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	@Override
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		//	Purchase Order Delivered/Invoiced
		//	(Reserved in VMatch and MInOut.completeIt)
		MInOutLine inout_line = (MInOutLine) getM_InOutLine();
		for (MTransaction trx: MTransaction.getByInOutLine(inout_line))
		{
			if (!inout_line.getM_Product().getProductType().equals(MProduct.PRODUCTTYPE_Item) || trx==null)
				continue;
			CostEngineFactory.getCostEngine(getAD_Client_ID()).createCostDetail(trx,this);
		}
		
		if (success && getC_OrderLine_ID() != 0)
		{
			MOrderLine orderLine = getOrderLine();
			//
			if (m_isInOutLineChange && (newRecord || getM_InOutLine_ID() != get_ValueOldAsInt("M_InOutLine_ID")))
			{
				if (getM_InOutLine_ID() != 0)							//	new delivery
					orderLine.setQtyDelivered(orderLine.getQtyDelivered().add(getQty()));
				else //	if (getM_InOutLine_ID() == 0)					//	reset to 0
					orderLine.setQtyDelivered(orderLine.getQtyDelivered().subtract(getQty()));
				orderLine.setDateDelivered(getDateTrx());	//	overwrite=last
			}
			if (m_isInvoiceLineChange && (newRecord || getC_InvoiceLine_ID() != get_ValueOldAsInt("C_InvoiceLine_ID")))
			{
				if (getC_InvoiceLine_ID() != 0)						//	first time
					orderLine.setQtyInvoiced(orderLine.getQtyInvoiced().add(getQty()));
				else //	if (getC_InvoiceLine_ID() == 0)				//	set to 0
					orderLine.setQtyInvoiced(orderLine.getQtyInvoiced().subtract(getQty()));
				orderLine.setDateInvoiced(getDateTrx());	//	overwrite=last
			}
			
			//	Update Order ASI if full match
			if (orderLine.getM_AttributeSetInstance_ID() == 0
				&& getM_InOutLine_ID() != 0)
			{
				MInOutLine iol = new MInOutLine (getCtx(), getM_InOutLine_ID(), get_TrxName());
				if (iol.getMovementQty().compareTo(orderLine.getQtyOrdered()) == 0)
					orderLine.setM_AttributeSetInstance_ID(iol.getM_AttributeSetInstance_ID());
			}
			return orderLine.save();
		}
		//
		return success;
	}	//	afterSave

	
	/**
	 * 	Get the Date Acct from shipment
	 *	@return date or null
	 */
	public Timestamp getNewerDateAcct()
	{
		String sql = "SELECT io.DateAcct "
			+ "FROM M_InOutLine iol"
			+ " INNER JOIN M_InOut io ON (io.M_InOut_ID=iol.M_InOut_ID) "
			+ "WHERE iol.M_InOutLine_ID=?";
		Timestamp shipDate = DB.getSQLValueTS(null, sql, getM_InOutLine_ID());
		return shipDate;
	}	//	getNewerDateAcct

	
	/**
	 * 	Before Delete
	 *	@return true if acct was deleted
	 */
	@Override
	protected boolean beforeDelete ()
	{
		if (isPosted())
		{
			MPeriod.testPeriodOpen(getCtx(), getDateTrx(), MDocType.DOCBASETYPE_MatchPO, getAD_Org_ID());
			setPosted(false);
			MFactAcct.deleteEx (Table_ID, get_ID(), get_TrxName());
		}
		return true;
	}	//	beforeDelete

	
	/**
	 * 	After Delete.
	 * 	Set Order Qty Delivered/Invoiced 
	 *	@param success success
	 *	@return success
	 */
	@Override
	protected boolean afterDelete (boolean success)
	{
		//	Order Delivered/Invoiced
		//	(Reserved in VMatch and MInOut.completeIt)
		if (success && getC_OrderLine_ID() != 0)
		{
			// AZ Goodwill
			//deleteMatchPOCostDetail();
			// end AZ
			
			MOrderLine orderLine = new MOrderLine (getCtx(), getC_OrderLine_ID(), get_TrxName());
			if (getM_InOutLine_ID() != 0)
				orderLine.setQtyDelivered(orderLine.getQtyDelivered().subtract(getQty()));
			if (getC_InvoiceLine_ID() != 0)
				orderLine.setQtyInvoiced(orderLine.getQtyInvoiced().subtract(getQty()));
			return orderLine.save(get_TrxName());
		}
		return success;
	}	//	afterDelete
		
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MMatchPO[");
		sb.append (get_ID())
			.append (",Qty=").append (getQty())
			.append (",C_OrderLine_ID=").append (getC_OrderLine_ID())
			.append (",M_InOutLine_ID=").append (getM_InOutLine_ID())
			.append (",C_InvoiceLine_ID=").append (getC_InvoiceLine_ID())
			.append ("]");
		return sb.toString ();
	}	//	toString
	
	/**
	 * 	Consolidate MPO entries.
	 * 	(data conversion issue)
	 * 	@param ctx context
	 */
	public static void consolidate(Properties ctx)
	{
		String sql = "SELECT * FROM M_MatchPO po "
			+ "WHERE EXISTS (SELECT 1 FROM M_MatchPO x "
				+ "WHERE po.C_OrderLine_ID=x.C_OrderLine_ID AND po.Qty=x.Qty "
				+ "GROUP BY C_OrderLine_ID, Qty "
				+ "HAVING COUNT(*) = 2) "
			+ " AND AD_Client_ID=?"
			+ "ORDER BY C_OrderLine_ID, M_InOutLine_ID";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int success = 0;
		int errors = 0;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				MMatchPO po1 = new MMatchPO (ctx, rs, null);
				if (rs.next())
				{
					MMatchPO po2 = new MMatchPO (ctx, rs, null);
					if (po1.getM_InOutLine_ID() != 0 && po1.getC_InvoiceLine_ID() == 0 
						&& po2.getM_InOutLine_ID() == 0 && po2.getC_InvoiceLine_ID() != 0)
					{
						String s1 = "UPDATE M_MatchPO SET C_InvoiceLine_ID=" 
							+ po2.getC_InvoiceLine_ID() 
							+ " WHERE M_MatchPO_ID=" + po1.getM_MatchPO_ID();
						int no1 = DB.executeUpdate(s1, null);
						if (no1 != 1)
						{
							errors++;
							s_log.warning("Not updated M_MatchPO_ID=" + po1.getM_MatchPO_ID());
							continue;
						}
						//
						String s2 = "DELETE FROM Fact_Acct WHERE AD_Table_ID=473 AND Record_ID=?";
						int no2 = DB.executeUpdate(s2, po2.getM_MatchPO_ID(), null);
						String s3 = "DELETE FROM M_MatchPO WHERE M_MatchPO_ID=?";
						int no3 = DB.executeUpdate(s3, po2.getM_MatchPO_ID(), null);
						if (no2 == 0 && no3 == 1)
							success++;
						else
						{
							s_log.warning("M_MatchPO_ID=" + po2.getM_MatchPO_ID()
								+ " - Deleted=" + no2 + ", Acct=" + no3); 
							errors++;
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			s_log.log (Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		if (errors == 0 && success == 0)
			;
		else
			s_log.info("Success #" + success + " - Error #" + errors);
	}	//	consolidate

	@Override
	public int getM_Locator_ID() {
		return -1;
	}

	@Override
	public BigDecimal getMovementQty() {
		return getQty();
	}

	@Override
	public BigDecimal getPriceActual() {
		MOrderLine ol = getOrderLine();
		return MConversionRate.convertBase(getCtx(), getOrderLine().getPriceActual(),getC_Currency_ID(),
				getDateAcct(), getC_ConversionType_ID(),
				getAD_Client_ID(), getAD_Org_ID());
	}

	@Override
	public BigDecimal getPriceActualCurrency() {
		return getOrderLine().getPriceActual();
	}

	@Override
	public int getC_Currency_ID ()
	{
		return DB.getSQLValue(get_TrxName() ,
				"SELECT o.C_Currency_ID FROM C_OrderLine ol INNER JOIN C_Order o ON (ol.C_Order_ID=o.C_Order_ID) WHERE ol.C_OrderLine_ID = ? ",
				getC_OrderLine_ID());
	}

	@Override
	public int getC_ConversionType_ID()
	{
		return DB.getSQLValue(get_TrxName() ,
				"SELECT o.C_ConversionType_ID FROM C_OrderLine ol INNER JOIN C_Order o ON (ol.C_Order_ID=o.C_Order_ID) WHERE ol.C_OrderLine_ID = ? ",
				getC_OrderLine_ID());
	}


	@Override
	public int getReversalLine_ID() {
			return -1;
	}

	@Override
	public boolean isSOTrx() {
		return false;
	}

	@Override
	public void setM_Locator_ID(int M_Locator_ID) {
		;		
	}
	

	public IDocumentLine getReversalDocumentLine() {
		return null;
	}

	@Override
	public int getM_AttributeSetInstanceTo_ID() {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public int getM_LocatorTo_ID() {
		// TODO Auto-generated method stub
		return -1;
	}
	
	@Override
	public int getC_DocType_ID() {
		return -1;
	}
}	//	MMatchPO
