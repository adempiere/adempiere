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
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
/**
 *	Requisition Line Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MRequisitionLine.java,v 1.2 2006/07/30 00:51:03 jjanke Exp $
 * 
 * @author Teo Sarca, www.arhipac.ro
 * 			<li>BF [ 2419978 ] Voiding PO, requisition don't set on NULL
 * 			<li>BF [ 2608617 ] Error when I want to delete a PO document
 * 			<li>BF [ 2609604 ] Add M_RequisitionLine.C_BPartner_ID
 * 			<li>FR [ 2841841 ] Requisition Improvements
 * 				https://sourceforge.net/tracker/?func=detail&aid=2841841&group_id=176962&atid=879335
 *  @author victor.perez@e-evolution.com, e-Evolution http://www.e-evolution.com
 *			<>[Feature Request] Add tax functionality to the requisition #3737</>
 *  		<li> https://github.com/adempiere/adempiere/issues/3737 </>	
 */
public class MRequisitionLine extends X_M_RequisitionLine
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2567343619431184322L;

	/**
	 * Get corresponding Requisition Line for given Order Line
	 * @param ctx
	 * @param C_OrderLine_ID order line
	 * @param trxName
	 * @return Requisition Line
	 */
	public static MRequisitionLine[] forC_Order_ID(Properties ctx, int C_Order_ID, String trxName)
	{
		final String whereClause = "EXISTS (SELECT 1 FROM C_OrderLine ol"
										+" WHERE ol.C_OrderLine_ID=M_RequisitionLine.C_OrderLine_ID"
										+" AND ol.C_Order_ID=?)";
		List<MRequisitionLine> list = new Query(ctx, I_M_RequisitionLine.Table_Name, whereClause, trxName)
			.setParameters(C_Order_ID)
			.list();
		return list.toArray(new MRequisitionLine[list.size()]);
	}
	
	/**
	 * UnLink Requisition Lines for given Order
	 * @param ctx
	 * @param C_Order_ID
	 * @param trxName
	 */
	public static void unlinkC_Order_ID(Properties ctx, int C_Order_ID, String trxName)
	{
		for (MRequisitionLine line : MRequisitionLine.forC_Order_ID(ctx, C_Order_ID, trxName))
		{
			line.setC_OrderLine_ID(0);
			line.saveEx();
		}
	}
	

	/**
	 * Get corresponding Requisition Line(s) for given Order Line
	 * @param ctx
	 * @param C_OrderLine_ID order line
	 * @param trxName
	 * @return array of Requisition Line(s)
	 */
	public static MRequisitionLine[] forC_OrderLine_ID(Properties ctx, int C_OrderLine_ID, String trxName)
	{
		final String whereClause = COLUMNNAME_C_OrderLine_ID+"=?";
		List<MRequisitionLine> list = new Query(ctx, I_M_RequisitionLine.Table_Name, whereClause, trxName)
			.setParameters(C_OrderLine_ID)
			.list();
		return list.toArray(new MRequisitionLine[list.size()]);
	}

	/**
	 * UnLink Requisition Lines for given Order Line
	 * @param ctx
	 * @param C_OrderLine_ID
	 * @param trxName
	 */
	public static void unlinkC_OrderLine_ID(Properties ctx, int C_OrderLine_ID, String trxName)
	{
		for (MRequisitionLine line : forC_OrderLine_ID(ctx, C_OrderLine_ID, trxName))
		{
			line.setC_OrderLine_ID(0);
			line.saveEx();
		}
	}


	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param M_RequisitionLine_ID id
	 *	@param trxName transaction
	 */
	public MRequisitionLine (Properties ctx, int M_RequisitionLine_ID, String trxName)
	{
		super (ctx, M_RequisitionLine_ID, trxName);
		if (M_RequisitionLine_ID == 0)
		{
		//	setM_Requisition_ID (0);
			setLine (0);	// @SQL=SELECT COALESCE(MAX(Line),0)+10 AS DefaultValue FROM M_RequisitionLine WHERE M_Requisition_ID=@M_Requisition_ID@
			setLineNetAmt (Env.ZERO);
			setPriceActual (Env.ZERO);
			setQty (Env.ONE);	// 1
		}
		
	}	//	MRequisitionLine

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MRequisitionLine (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MRequisitionLine

	/**
	 * 	Parent Constructor
	 *	@param requisition requisition
	 */
	public MRequisitionLine (MRequisition requisition)
	{
		this (requisition.getCtx(), 0, requisition.get_TrxName());
		setClientOrg(requisition);
		setM_Requisition_ID(requisition.getM_Requisition_ID());
		priceListId = requisition.getM_PriceList_ID();
		parent = requisition;
	}	//	MRequisitionLine

	/** Parent					*/
	private MRequisition parent = null;
	
	/**	PriceList				*/
	private int priceListId = 0;
	
	/**
	 * Get Ordered Qty
	 * @return Ordered Qty
	 */
	public BigDecimal getQtyOrdered()
	{
		if (getC_OrderLine_ID() > 0)
			return getQty();
		else
			return Env.ZERO;
	}
	
	/**
	 * 	Get Parent
	 *	@return parent
	 */
	public MRequisition getParent()
	{
		if (parent == null)
			parent = new MRequisition (getCtx(), getM_Requisition_ID(), get_TrxName());
		return parent;
	}	//	getParent
	
	@Override
	public I_M_Requisition getM_Requisition()
	{
		return getParent();
	}

	/**
	 * @return Date when this product is required by planner
	 * @see MRequisition#getDateRequired()
	 */
	public Timestamp getDateRequired()
	{
		return getParent().getDateRequired();
	}
	
	/**
	 * 	Set Price
	 */
	public void setPrice()
	{
		if (getC_Charge_ID() != 0)
		{
			MCharge charge = MCharge.get(getCtx(), getC_Charge_ID());
			setPriceActual(charge.getChargeAmt());
		}
		if (getM_Product_ID() == 0)
			return;
		if (priceListId == 0)
			priceListId = getParent().getM_PriceList_ID();
		if (priceListId == 0)
		{
			throw new AdempiereException("PriceList unknown!");
		}
		setPrice (priceListId);
	}	//	setPrice
	
	/**
	 * 	Set Price for Product and PriceList
	 * 	@param M_PriceList_ID price list
	 */
	public void setPrice (int M_PriceList_ID)
	{
		if (getM_Product_ID() == 0)
			return;
		//
		log.fine("M_PriceList_ID=" + M_PriceList_ID);
		boolean isSOTrx = false;
		MProductPricing pp = new MProductPricing (getM_Product_ID(), 
			getC_BPartner_ID(), getQty(), isSOTrx, null);
		pp.setM_PriceList_ID(M_PriceList_ID);
	//	pp.setPriceDate(getDateOrdered());
		//
		setPriceActual (pp.getPriceStd());
	}	//	setPrice

	/**
	 * 	Calculate Line Net Amt
	 */
	public void setLineNetAmt ()
	{
		BigDecimal lineNetAmt = getQty().multiply(getPriceActual());
		super.setLineNetAmt (lineNetAmt);
	}	//	setLineNetAmt
	
	
	/**************************************************************************
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		if (newRecord && getParent().isComplete()) {
			log.saveError("ParentComplete", Msg.translate(getCtx(), "M_RequisitionLine"));
			return false;
		}
		if (getLine() == 0)
		{
			String sql = "SELECT COALESCE(MAX(Line),0)+10 FROM M_RequisitionLine WHERE M_Requisition_ID=?";
			int ii = DB.getSQLValueEx (get_TrxName(), sql, getM_Requisition_ID());
			setLine (ii);
		}
		//	Product & ASI - Charge
		if (getM_Product_ID() != 0 && getC_Charge_ID() != 0)
			setC_Charge_ID(0);
		if (getM_AttributeSetInstance_ID() != 0 && getC_Charge_ID() != 0)
			setM_AttributeSetInstance_ID(0);
		// Product UOM
		if (getM_Product_ID() > 0 && getC_UOM_ID() <= 0)
		{
			setC_UOM_ID(getM_Product().getC_UOM_ID());
		}
		//
		if (getPriceActual().signum() == 0)
			setPrice();

		//	Set Tax
		if (getC_Tax_ID() == 0)
			setTax();

		if (getTaxAmt().compareTo(Env.ZERO) == 0)
			setTaxAmt();

		if (newRecord
				|| (!newRecord && is_ValueChanged(MRequisitionLine.COLUMNNAME_C_Tax_ID) && !getParent().isProcessed())
				|| (!newRecord && is_ValueChanged(MRequisitionLine.COLUMNNAME_Qty) && !getParent().isProcessed())
				|| (!newRecord && is_ValueChanged(MRequisitionLine.COLUMNNAME_PriceActual) && !getParent().isProcessed())
		) {
			setTaxAmt();
		}

		setLineNetAmt();
		return true;
	}	//	beforeSave

	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return saved
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (!success)
			return success;
		if (newRecord
				|| (!newRecord && is_ValueChanged(MRequisitionLine.COLUMNNAME_C_Tax_ID) && !getParent().isProcessed())
				|| (!newRecord && is_ValueChanged(MRequisitionLine.COLUMNNAME_Qty) && !getParent().isProcessed())
				|| (!newRecord && is_ValueChanged(MRequisitionLine.COLUMNNAME_PriceActual) && !getParent().isProcessed())
		) {
			setTaxAmt();
			return updateHeaderTax();
		}

		return true;
	}	//	afterSave
	
	/**
	 * 	After Delete
	 *	@param success
	 *	@return true/false
	 */
	protected boolean afterDelete (boolean success)
	{
		if (!success)
			return success;
		return updateHeader();
	}	//	afterDelete
	
	@Override
	public I_M_Product getM_Product()
	{
		return MProduct.get(getCtx(), getM_Product_ID());
	}

	/**
	 * 	Update Header
	 *	@return header updated
	 */
	private boolean updateHeader()
	{
		log.fine("");
		final String sql = "UPDATE M_Requisition r"
			+ " SET TotalLines="
				+ "(SELECT COALESCE(SUM(LineNetAmt),0) FROM M_RequisitionLine rl "
				+ "WHERE r.M_Requisition_ID=rl.M_Requisition_ID) "
			+ "WHERE M_Requisition_ID = ?";
		int no = DB.executeUpdateEx(sql, List.of(getM_Requisition_ID()).toArray(), get_TrxName());
		if (no != 1)
			log.log(Level.SEVERE, "Header update #" + no);
		parent = null;
		return no == 1;
	}	//	updateHeader

	/**
	 *	Set Tax
	 *	@return true if tax is set
	 */
	public boolean setTax()  {
		int taxId = Tax.get(getCtx(), getM_Product_ID(), getC_Charge_ID(), getParent().getDateDoc(), getParent().getDateDoc(),
				getAD_Org_ID(), getParent().getM_Warehouse_ID(),
				0,		//	should be bill to
				0, false, get_TrxName());
		if (taxId == 0)
		{
			log.log(Level.SEVERE, "No Tax found");
			return false;
		}
		setC_Tax_ID (taxId);
		return true;
	}	//	setTax

	/**
	 * 	Calculate Tax Amt.
	 * 	Assumes Line Net is calculated
	 */
	public void setTaxAmt ()
	{
		BigDecimal taxAmt = Env.ZERO;
		if (getC_Tax_ID() == 0)
			return;
		//	setLineNetAmt();
		MTax tax = MTax.get (getCtx(), getC_Tax_ID());
		if (tax.isDocumentLevel())	{	//	AR Inv Tax
			setLineTotalAmt(getLineNetAmt()); // @Trifon
			return;
		}
		//
		taxAmt = tax.calculateTax(getLineNetAmt(), getParent().isTaxIncluded(), getParent().getPrecision());
		if (getParent().isTaxIncluded())
			setLineTotalAmt(getLineNetAmt());
		else
			setLineTotalAmt(getLineNetAmt().add(taxAmt));
		super.setTaxAmt (taxAmt);
	}	//	setTaxAmt

	/**
	 *	Update Tax & Header
	 *	@return true if header updated
	 */
	private boolean updateHeaderTax()
	{
		//	Recalculate Tax for this Tax
		if (!getParent().isProcessed())
			getParent().calculateTaxTotal();

		//	Update Order Header
		final String updateRequisitionTotalLines = "UPDATE M_Requisition r"
				+ " SET TotalLines="
				+ "(SELECT COALESCE(SUM(LineNetAmt),0) FROM M_RequisitionLine rl WHERE r.M_Requisition_ID=rl.M_Requisition_ID) "
				+ "WHERE M_Requisition_ID = ? ";
		int no = DB.executeUpdateEx(updateRequisitionTotalLines, List.of(getM_Requisition_ID()).toArray(),get_TrxName());
		if (no != 1)
			log.warning("(1) #" + no);

		if (getParent().isTaxIncluded()) {
			final String updateRequisitionGrandTotal = "UPDATE M_Requisition r "
					+ " SET GrandTotal=TotalLines "
					+ "WHERE M_Requisition_ID = ? ";
			no = DB.executeUpdateEx(updateRequisitionGrandTotal , List.of(getM_Requisition_ID()).toArray(), get_TrxName());
		}
		else {
			final String updateRequisitionGrandTotal = "UPDATE M_Requisition r "
					+ " SET GrandTotal=TotalLines+"
					+ "(SELECT COALESCE(SUM(TaxAmt),0) FROM M_RequisitionTax rt WHERE rt.M_Requisition_ID=rt.M_Requisition_ID) "
					+ "WHERE M_Requisition_ID = ? ";
			no = DB.executeUpdateEx(updateRequisitionGrandTotal , List.of(getM_Requisition_ID()).toArray() , get_TrxName());
		}
		if (no != 1)
			log.warning("(2) #" + no);
		parent = null;
		return no == 1;
	}	//	updateHeaderTax

}	//	MRequisitionLine
