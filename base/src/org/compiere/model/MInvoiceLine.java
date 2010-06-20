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

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;


/**
 *	Invoice Line Model
 *
 *  @author Jorg Janke
 *  @version $Id: MInvoiceLine.java,v 1.5 2006/07/30 00:51:03 jjanke Exp $
 * 
 * @author Teo Sarca, www.arhipac.ro
 * 			<li>BF [ 2804142 ] MInvoice.setRMALine should work only for CreditMemo invoices
 * 				https://sourceforge.net/tracker/?func=detail&aid=2804142&group_id=176962&atid=879332
 * @author Michael Judd, www.akunagroup.com
 * 			<li>BF [ 1733602 ] Price List including Tax Error - when a user changes the orderline or
 * 				invoice line for a product on a price list that includes tax, the net amount is
 * 				incorrectly calculated.
 * @author red1 FR: [ 2214883 ] Remove SQL code and Replace for Query
 */
public class MInvoiceLine extends X_C_InvoiceLine
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5113860437274708398L;

	/**
	 * 	Get Invoice Line referencing InOut Line
	 *	@param sLine shipment line
	 *	@return (first) invoice line
	 */
	public static MInvoiceLine getOfInOutLine (MInOutLine sLine)
	{
		if (sLine == null)
			return null;
		final String whereClause = I_M_InOutLine.COLUMNNAME_M_InOutLine_ID+"=?";
		List<MInvoiceLine> list = new Query(sLine.getCtx(),I_C_InvoiceLine.Table_Name,whereClause,sLine.get_TrxName())
		.setParameters(sLine.getM_InOutLine_ID())
		.list();
		
		MInvoiceLine retValue = null;
		if (list.size() > 0) {
			retValue = list.get(0);
			if (list.size() > 1)
				s_log.warning("More than one C_InvoiceLine of " + sLine);
		}

		return retValue;
	}	//	getOfInOutLine

	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MInvoiceLine.class);

	/** Tax							*/
	private MTax 		m_tax = null;
	
	
	/**************************************************************************
	 * 	Invoice Line Constructor
	 * 	@param ctx context
	 * 	@param C_InvoiceLine_ID invoice line or 0
	 * 	@param trxName transaction name
	 */
	public MInvoiceLine (Properties ctx, int C_InvoiceLine_ID, String trxName)
	{
		super (ctx, C_InvoiceLine_ID, trxName);
		if (C_InvoiceLine_ID == 0)
		{
			setIsDescription(false);
			setIsPrinted (true);
			setLineNetAmt (Env.ZERO);
			setPriceEntered (Env.ZERO);
			setPriceActual (Env.ZERO);
			setPriceLimit (Env.ZERO);
			setPriceList (Env.ZERO);
			setM_AttributeSetInstance_ID(0);
			setTaxAmt(Env.ZERO);
			//
			setQtyEntered(Env.ZERO);
			setQtyInvoiced(Env.ZERO);
		}
	}	//	MInvoiceLine

	/**
	 * 	Parent Constructor
	 * 	@param invoice parent
	 */
	public MInvoiceLine (MInvoice invoice)
	{
		this (invoice.getCtx(), 0, invoice.get_TrxName());
		if (invoice.get_ID() == 0)
			throw new IllegalArgumentException("Header not saved");
		setClientOrg(invoice.getAD_Client_ID(), invoice.getAD_Org_ID());
		setC_Invoice_ID (invoice.getC_Invoice_ID());
		setInvoice(invoice);
	}	//	MInvoiceLine


	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 *  @param trxName transaction
	 */
	public MInvoiceLine (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MInvoiceLine

	private int			m_M_PriceList_ID = 0;
	private Timestamp	m_DateInvoiced = null;
	private int			m_C_BPartner_ID = 0;
	private int			m_C_BPartner_Location_ID = 0;
	private boolean		m_IsSOTrx = true;
	private boolean		m_priceSet = false;
	private MProduct	m_product = null;
	/**	Charge					*/
	private MCharge 		m_charge = null;
	
	/**	Cached Name of the line		*/
	private String		m_name = null;
	/** Cached Precision			*/
	private Integer		m_precision = null;
	/** Product Pricing				*/
	private MProductPricing	m_productPricing = null;
	/** Parent						*/
	private MInvoice	m_parent = null;

	/**
	 * 	Set Defaults from Order.
	 * 	Called also from copy lines from invoice
	 * 	Does not set Parent !!
	 * 	@param invoice invoice
	 */
	public void setInvoice (MInvoice invoice)
	{
		m_parent = invoice;
		m_M_PriceList_ID = invoice.getM_PriceList_ID();
		m_DateInvoiced = invoice.getDateInvoiced();
		m_C_BPartner_ID = invoice.getC_BPartner_ID();
		m_C_BPartner_Location_ID = invoice.getC_BPartner_Location_ID();
		m_IsSOTrx = invoice.isSOTrx();
		m_precision = new Integer(invoice.getPrecision());
	}	//	setOrder

	/**
	 * 	Get Parent
	 *	@return parent
	 */
	public MInvoice getParent()
	{
		if (m_parent == null)
			m_parent = new MInvoice(getCtx(), getC_Invoice_ID(), get_TrxName());
		return m_parent;
	}	//	getParent

	/**
	 * 	Set values from Order Line.
	 * 	Does not set quantity!
	 *	@param oLine line
	 */
	public void setOrderLine (MOrderLine oLine)
	{
		setC_OrderLine_ID(oLine.getC_OrderLine_ID());
		//
		setLine(oLine.getLine());
		setIsDescription(oLine.isDescription());
		setDescription(oLine.getDescription());
		//
		if(oLine.getM_Product_ID() == 0)
		setC_Charge_ID(oLine.getC_Charge_ID());
		//
		setM_Product_ID(oLine.getM_Product_ID());
		setM_AttributeSetInstance_ID(oLine.getM_AttributeSetInstance_ID());
		setS_ResourceAssignment_ID(oLine.getS_ResourceAssignment_ID());
		setC_UOM_ID(oLine.getC_UOM_ID());
		//
		setPriceEntered(oLine.getPriceEntered());
		setPriceActual(oLine.getPriceActual());
		setPriceLimit(oLine.getPriceLimit());
		setPriceList(oLine.getPriceList());
		//
		setC_Tax_ID(oLine.getC_Tax_ID());
		setLineNetAmt(oLine.getLineNetAmt());
		//
		setC_Project_ID(oLine.getC_Project_ID());
		setC_ProjectPhase_ID(oLine.getC_ProjectPhase_ID());
		setC_ProjectTask_ID(oLine.getC_ProjectTask_ID());
		setC_Activity_ID(oLine.getC_Activity_ID());
		setC_Campaign_ID(oLine.getC_Campaign_ID());
		setAD_OrgTrx_ID(oLine.getAD_OrgTrx_ID());
		setUser1_ID(oLine.getUser1_ID());
		setUser2_ID(oLine.getUser2_ID());
		//
		setRRAmt(oLine.getRRAmt());
		setRRStartDate(oLine.getRRStartDate());
	}	//	setOrderLine

	/**
	 * 	Set values from Shipment Line.
	 * 	Does not set quantity!
	 *	@param sLine ship line
	 */
	public void setShipLine (MInOutLine sLine)
	{
		setM_InOutLine_ID(sLine.getM_InOutLine_ID());
		setC_OrderLine_ID(sLine.getC_OrderLine_ID());
		// Set RMALine ID if shipment/receipt is based on RMA Doc
        setM_RMALine_ID(sLine.getM_RMALine_ID());

		//
		setLine(sLine.getLine());
		setIsDescription(sLine.isDescription());
		setDescription(sLine.getDescription());
		//
		setM_Product_ID(sLine.getM_Product_ID());
		if (sLine.sameOrderLineUOM())
			setC_UOM_ID(sLine.getC_UOM_ID());
		else
			// use product UOM if the shipment hasn't the same uom than the order
			setC_UOM_ID(getProduct().getC_UOM_ID());
		setM_AttributeSetInstance_ID(sLine.getM_AttributeSetInstance_ID());
	//	setS_ResourceAssignment_ID(sLine.getS_ResourceAssignment_ID());
		if(getM_Product_ID() == 0)
		    setC_Charge_ID(sLine.getC_Charge_ID());
		//
		int C_OrderLine_ID = sLine.getC_OrderLine_ID();
		if (C_OrderLine_ID != 0)
		{
			MOrderLine oLine = new MOrderLine (getCtx(), C_OrderLine_ID, get_TrxName());
			setS_ResourceAssignment_ID(oLine.getS_ResourceAssignment_ID());
			//
			if (sLine.sameOrderLineUOM())
				setPriceEntered(oLine.getPriceEntered());
			else
				setPriceEntered(oLine.getPriceActual());
			setPriceActual(oLine.getPriceActual());
			setPriceLimit(oLine.getPriceLimit());
			setPriceList(oLine.getPriceList());
			//
			setC_Tax_ID(oLine.getC_Tax_ID());
			setLineNetAmt(oLine.getLineNetAmt());
			setC_Project_ID(oLine.getC_Project_ID());
		}
		// Check if shipment line is based on RMA
        else if (sLine.getM_RMALine_ID() != 0)
        {
        	// Set Pricing details from the RMA Line on which it is based
            MRMALine rmaLine = new MRMALine(getCtx(), sLine.getM_RMALine_ID(), get_TrxName());

            setPrice();
            setPrice(rmaLine.getAmt());
            setC_Tax_ID(rmaLine.getC_Tax_ID());
            setLineNetAmt(rmaLine.getLineNetAmt());
        }
		else
		{
			setPrice();
			setTax();
		}
		//
		setC_Project_ID(sLine.getC_Project_ID());
		setC_ProjectPhase_ID(sLine.getC_ProjectPhase_ID());
		setC_ProjectTask_ID(sLine.getC_ProjectTask_ID());
		setC_Activity_ID(sLine.getC_Activity_ID());
		setC_Campaign_ID(sLine.getC_Campaign_ID());
		setAD_OrgTrx_ID(sLine.getAD_OrgTrx_ID());
		setUser1_ID(sLine.getUser1_ID());
		setUser2_ID(sLine.getUser2_ID());
	}	//	setShipLine

	/**
	 * 	Add to Description
	 *	@param description text
	 */
	public void addDescription (String description)
	{
		String desc = getDescription();
		if (desc == null)
			setDescription(description);
		else
			setDescription(desc + " | " + description);
	}	//	addDescription

	/**
	 * 	Set M_AttributeSetInstance_ID
	 *	@param M_AttributeSetInstance_ID id
	 */
	public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID)
	{
		if (M_AttributeSetInstance_ID == 0)		//	 0 is valid ID
			set_Value("M_AttributeSetInstance_ID", new Integer(0));
		else
			super.setM_AttributeSetInstance_ID (M_AttributeSetInstance_ID);
	}	//	setM_AttributeSetInstance_ID


	/**************************************************************************
	 * 	Set Price for Product and PriceList.
	 * 	Uses standard SO price list of not set by invoice constructor
	 */
	public void setPrice()
	{
		if (getM_Product_ID() == 0 || isDescription())
			return;
		if (m_M_PriceList_ID == 0 || m_C_BPartner_ID == 0)
			setInvoice(getParent());
		if (m_M_PriceList_ID == 0 || m_C_BPartner_ID == 0)
			throw new IllegalStateException("setPrice - PriceList unknown!");
		setPrice (m_M_PriceList_ID, m_C_BPartner_ID);
	}	//	setPrice

	/**
	 * 	Set Price for Product and PriceList
	 * 	@param M_PriceList_ID price list
	 * 	@param C_BPartner_ID business partner
	 */
	public void setPrice (int M_PriceList_ID, int C_BPartner_ID)
	{
		if (getM_Product_ID() == 0 || isDescription())
			return;
		//
		log.fine("M_PriceList_ID=" + M_PriceList_ID);
		m_productPricing = new MProductPricing (getM_Product_ID(),
			C_BPartner_ID, getQtyInvoiced(), m_IsSOTrx);
		m_productPricing.setM_PriceList_ID(M_PriceList_ID);
		m_productPricing.setPriceDate(m_DateInvoiced);
		//
		setPriceActual (m_productPricing.getPriceStd());
		setPriceList (m_productPricing.getPriceList());
		setPriceLimit (m_productPricing.getPriceLimit());
		//
		if (getQtyEntered().compareTo(getQtyInvoiced()) == 0)
			setPriceEntered(getPriceActual());
		else
			setPriceEntered(getPriceActual().multiply(getQtyInvoiced()
				.divide(getQtyEntered(), 6, BigDecimal.ROUND_HALF_UP)));	//	precision
		//
		if (getC_UOM_ID() == 0)
			setC_UOM_ID(m_productPricing.getC_UOM_ID());
		//
		m_priceSet = true;
	}	//	setPrice

	/**
	 * 	Set Price Entered/Actual.
	 * 	Use this Method if the Line UOM is the Product UOM
	 *	@param PriceActual price
	 */
	public void setPrice (BigDecimal PriceActual)
	{
		setPriceEntered(PriceActual);
		setPriceActual (PriceActual);
	}	//	setPrice

	/**
	 * 	Set Price Actual.
	 * 	(actual price is not updateable)
	 *	@param PriceActual actual price
	 */
	public void setPriceActual (BigDecimal PriceActual)
	{
		if (PriceActual == null)
			throw new IllegalArgumentException ("PriceActual is mandatory");
		set_ValueNoCheck("PriceActual", PriceActual);
	}	//	setPriceActual


	/**
	 *	Set Tax - requires Warehouse
	 *	@return true if found
	 */
	public boolean setTax()
	{
		if (isDescription())
			return true;
		//
		int M_Warehouse_ID = Env.getContextAsInt(getCtx(), "#M_Warehouse_ID");
		//
		int C_Tax_ID = Tax.get(getCtx(), getM_Product_ID(), getC_Charge_ID() , m_DateInvoiced, m_DateInvoiced,
			getAD_Org_ID(), M_Warehouse_ID,
			m_C_BPartner_Location_ID,		//	should be bill to
			m_C_BPartner_Location_ID, m_IsSOTrx);
		if (C_Tax_ID == 0)
		{
			log.log(Level.SEVERE, "No Tax found");
			return false;
		}
		setC_Tax_ID (C_Tax_ID);
		return true;
	}	//	setTax


	/**
	 * 	Calculate Tax Amt.
	 * 	Assumes Line Net is calculated
	 */
	public void setTaxAmt ()
	{
		BigDecimal TaxAmt = Env.ZERO;
		if (getC_Tax_ID() == 0)
			return;
	//	setLineNetAmt();
		MTax tax = MTax.get (getCtx(), getC_Tax_ID());
		if (tax.isDocumentLevel() && m_IsSOTrx || getTaxAmt().signum() > 0)//	AR Inv Tax
			return;
		//
		TaxAmt = tax.calculateTax(getLineNetAmt(), isTaxIncluded(), getPrecision());
		if (isTaxIncluded())
			setLineTotalAmt(getLineNetAmt());
		else
			setLineTotalAmt(getLineNetAmt().add(TaxAmt));
		super.setTaxAmt (TaxAmt);
	}	//	setTaxAmt

	/**
	 * 	Calculate Extended Amt.
	 * 	May or may not include tax
	 */
	public void setLineNetAmt ()
	{
		//	Calculations & Rounding
		BigDecimal bd = getPriceActual().multiply(getQtyInvoiced());
		
		boolean documentLevel = getTax().isDocumentLevel();

		//	juddm: Tax Exempt & Tax Included in Price List & not Document Level - Adjust Line Amount
		//  http://sourceforge.net/tracker/index.php?func=detail&aid=1733602&group_id=176962&atid=879332
		if (isTaxIncluded() && !documentLevel)	{
			BigDecimal taxStdAmt = Env.ZERO, taxThisAmt = Env.ZERO;
			
			MTax invoiceTax = getTax();
			MTax stdTax = null;
			
			if (getProduct() == null)
			{
				if (getCharge() != null)	// Charge 
				{
					stdTax = new MTax (getCtx(), 
							((MTaxCategory) getCharge().getC_TaxCategory()).getDefaultTax().getC_Tax_ID(),
							get_TrxName());
				}
					
			}
			else	// Product
				stdTax = new MTax (getCtx(), 
							((MTaxCategory) getProduct().getC_TaxCategory()).getDefaultTax().getC_Tax_ID(), 
							get_TrxName());

			if (stdTax != null)
			{
				
				log.fine("stdTax rate is " + stdTax.getRate());
				log.fine("invoiceTax rate is " + invoiceTax.getRate());
				
				taxThisAmt = taxThisAmt.add(invoiceTax.calculateTax(bd, isTaxIncluded(), getPrecision()));
				taxStdAmt = taxStdAmt.add(stdTax.calculateTax(bd, isTaxIncluded(), getPrecision()));
				
				bd = bd.subtract(taxStdAmt).add(taxThisAmt);
				
				log.fine("Price List includes Tax and Tax Changed on Invoice Line: New Tax Amt: " 
						+ taxThisAmt + " Standard Tax Amt: " + taxStdAmt + " Line Net Amt: " + bd);	
			}
		}
		
		if (bd.scale() > getPrecision())
			bd = bd.setScale(getPrecision(), BigDecimal.ROUND_HALF_UP);
		super.setLineNetAmt (bd);
	}	//	setLineNetAmt
	/**
	 * 	Get Charge
	 *	@return product or null
	 */
	public MCharge getCharge()
	{
		if (m_charge == null && getC_Charge_ID() != 0)
			m_charge =  MCharge.get (getCtx(), getC_Charge_ID());
		return m_charge;
	}
	/**
	 * 	Get Tax
	 *	@return tax
	 */
	protected MTax getTax()
	{
		if (m_tax == null)
			m_tax = MTax.get(getCtx(), getC_Tax_ID());
		return m_tax;
	}	//	getTax

	/**
	 * 	Set Qty Invoiced/Entered.
	 *	@param Qty Invoiced/Ordered
	 */
	public void setQty (int Qty)
	{
		setQty(new BigDecimal(Qty));
	}	//	setQtyInvoiced

	/**
	 * 	Set Qty Invoiced
	 *	@param Qty Invoiced/Entered
	 */
	public void setQty (BigDecimal Qty)
	{
		setQtyEntered(Qty);
		setQtyInvoiced(getQtyEntered());
	}	//	setQtyInvoiced

	/**
	 * 	Set Qty Entered - enforce entered UOM
	 *	@param QtyEntered
	 */
	public void setQtyEntered (BigDecimal QtyEntered)
	{
		if (QtyEntered != null && getC_UOM_ID() != 0)
		{
			int precision = MUOM.getPrecision(getCtx(), getC_UOM_ID());
			QtyEntered = QtyEntered.setScale(precision, BigDecimal.ROUND_HALF_UP);
		}
		super.setQtyEntered (QtyEntered);
	}	//	setQtyEntered

	/**
	 * 	Set Qty Invoiced - enforce Product UOM
	 *	@param QtyInvoiced
	 */
	public void setQtyInvoiced (BigDecimal QtyInvoiced)
	{
		MProduct product = getProduct();
		if (QtyInvoiced != null && product != null)
		{
			int precision = product.getUOMPrecision();
			QtyInvoiced = QtyInvoiced.setScale(precision, BigDecimal.ROUND_HALF_UP);
		}
		super.setQtyInvoiced(QtyInvoiced);
	}	//	setQtyInvoiced

	/**
	 * 	Set Product
	 *	@param product product
	 */
	public void setProduct (MProduct product)
	{
		m_product = product;
		if (m_product != null)
		{
			setM_Product_ID(m_product.getM_Product_ID());
			setC_UOM_ID (m_product.getC_UOM_ID());
		}
		else
		{
			setM_Product_ID(0);
			setC_UOM_ID (0);
		}
		setM_AttributeSetInstance_ID(0);
	}	//	setProduct


	/**
	 * 	Set M_Product_ID
	 *	@param M_Product_ID product
	 *	@param setUOM set UOM from product
	 */
	public void setM_Product_ID (int M_Product_ID, boolean setUOM)
	{
		if (setUOM)
			setProduct(MProduct.get(getCtx(), M_Product_ID));
		else
			super.setM_Product_ID (M_Product_ID);
		setM_AttributeSetInstance_ID(0);
	}	//	setM_Product_ID

	/**
	 * 	Set Product and UOM
	 *	@param M_Product_ID product
	 *	@param C_UOM_ID uom
	 */
	public void setM_Product_ID (int M_Product_ID, int C_UOM_ID)
	{
		super.setM_Product_ID (M_Product_ID);
		super.setC_UOM_ID(C_UOM_ID);
		setM_AttributeSetInstance_ID(0);
	}	//	setM_Product_ID

	/**
	 * 	Get Product
	 *	@return product or null
	 */
	public MProduct getProduct()
	{
		if (m_product == null && getM_Product_ID() != 0)
			m_product =  MProduct.get (getCtx(), getM_Product_ID());
		return m_product;
	}	//	getProduct

	/**
	 * 	Get C_Project_ID
	 *	@return project
	 */
	public int getC_Project_ID()
	{
		int ii = super.getC_Project_ID ();
		if (ii == 0)
			ii = getParent().getC_Project_ID();
		return ii;
	}	//	getC_Project_ID

	/**
	 * 	Get C_Activity_ID
	 *	@return Activity
	 */
	public int getC_Activity_ID()
	{
		int ii = super.getC_Activity_ID ();
		if (ii == 0)
			ii = getParent().getC_Activity_ID();
		return ii;
	}	//	getC_Activity_ID

	/**
	 * 	Get C_Campaign_ID
	 *	@return Campaign
	 */
	public int getC_Campaign_ID()
	{
		int ii = super.getC_Campaign_ID ();
		if (ii == 0)
			ii = getParent().getC_Campaign_ID();
		return ii;
	}	//	getC_Campaign_ID

	/**
	 * 	Get User2_ID
	 *	@return User2
	 */
	public int getUser1_ID ()
	{
		int ii = super.getUser1_ID ();
		if (ii == 0)
			ii = getParent().getUser1_ID();
		return ii;
	}	//	getUser1_ID

	/**
	 * 	Get User2_ID
	 *	@return User2
	 */
	public int getUser2_ID ()
	{
		int ii = super.getUser2_ID ();
		if (ii == 0)
			ii = getParent().getUser2_ID();
		return ii;
	}	//	getUser2_ID

	/**
	 * 	Get AD_OrgTrx_ID
	 *	@return trx org
	 */
	public int getAD_OrgTrx_ID()
	{
		int ii = super.getAD_OrgTrx_ID();
		if (ii == 0)
			ii = getParent().getAD_OrgTrx_ID();
		return ii;
	}	//	getAD_OrgTrx_ID

	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MInvoiceLine[")
			.append(get_ID()).append(",").append(getLine())
			.append(",QtyInvoiced=").append(getQtyInvoiced())
			.append(",LineNetAmt=").append(getLineNetAmt())
			.append ("]");
		return sb.toString ();
	}	//	toString

	/**
	 * 	Get (Product/Charge) Name
	 * 	@return name
	 */
	public String getName ()
	{
		if (m_name == null)
		{
			String sql = "SELECT COALESCE (p.Name, c.Name) "
				+ "FROM C_InvoiceLine il"
				+ " LEFT OUTER JOIN M_Product p ON (il.M_Product_ID=p.M_Product_ID)"
				+ " LEFT OUTER JOIN C_Charge C ON (il.C_Charge_ID=c.C_Charge_ID) "
				+ "WHERE C_InvoiceLine_ID=?";
			PreparedStatement pstmt = null;
			try
			{
				pstmt = DB.prepareStatement(sql, get_TrxName());
				pstmt.setInt(1, getC_InvoiceLine_ID());
				ResultSet rs = pstmt.executeQuery();
				if (rs.next())
					m_name = rs.getString(1);
				rs.close();
				pstmt.close();
				pstmt = null;
				if (m_name == null)
					m_name = "??";
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, "getName", e);
			}
			finally
			{
				try
				{
					if (pstmt != null)
						pstmt.close ();
				}
				catch (Exception e)
				{}
				pstmt = null;
			}
		}
		return m_name;
	}	//	getName

	/**
	 * 	Set Temporary (cached) Name
	 * 	@param tempName Cached Name
	 */
	public void setName (String tempName)
	{
		m_name = tempName;
	}	//	setName

	/**
	 * 	Get Description Text.
	 * 	For jsp access (vs. isDescription)
	 *	@return description
	 */
	public String getDescriptionText()
	{
		return super.getDescription();
	}	//	getDescriptionText

	/**
	 * 	Get Currency Precision
	 *	@return precision
	 */
	public int getPrecision()
	{
		if (m_precision != null)
			return m_precision.intValue();

		String sql = "SELECT c.StdPrecision "
			+ "FROM C_Currency c INNER JOIN C_Invoice x ON (x.C_Currency_ID=c.C_Currency_ID) "
			+ "WHERE x.C_Invoice_ID=?";
		int i = DB.getSQLValue(get_TrxName(), sql, getC_Invoice_ID());
		if (i < 0)
		{
			log.warning("getPrecision = " + i + " - set to 2");
			i = 2;
		}
		m_precision = new Integer(i);
		return m_precision.intValue();
	}	//	getPrecision

	/**
	 *	Is Tax Included in Amount
	 *	@return true if tax is included
	 */
	public boolean isTaxIncluded()
	{
		if (m_M_PriceList_ID == 0)
		{
			m_M_PriceList_ID = DB.getSQLValue(get_TrxName(),
				"SELECT M_PriceList_ID FROM C_Invoice WHERE C_Invoice_ID=?",
				getC_Invoice_ID());
		}
		MPriceList pl = MPriceList.get(getCtx(), m_M_PriceList_ID, get_TrxName());
		return pl.isTaxIncluded();
	}	//	isTaxIncluded


	/**************************************************************************
	 * 	Before Save
	 *	@param newRecord
	 *	@return true if save
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		log.fine("New=" + newRecord);
		if (newRecord && getParent().isComplete()) {
			log.saveError("ParentComplete", Msg.translate(getCtx(), "C_InvoiceLine"));
			return false;
		}
		// Re-set invoice header (need to update m_IsSOTrx flag) - phib [ 1686773 ]
		setInvoice(getParent());
		//	Charge
		if (getC_Charge_ID() != 0)
		{
			if (getM_Product_ID() != 0)
				setM_Product_ID(0);
		}
		else	//	Set Product Price
		{
			if (!m_priceSet
				&&  Env.ZERO.compareTo(getPriceActual()) == 0
				&&  Env.ZERO.compareTo(getPriceList()) == 0)
				setPrice();
		}

		//	Set Tax
		if (getC_Tax_ID() == 0)
			setTax();

		//	Get Line No
		if (getLine() == 0)
		{
			String sql = "SELECT COALESCE(MAX(Line),0)+10 FROM C_InvoiceLine WHERE C_Invoice_ID=?";
			int ii = DB.getSQLValue (get_TrxName(), sql, getC_Invoice_ID());
			setLine (ii);
		}
		//	UOM
		if (getC_UOM_ID() == 0)
		{
			int C_UOM_ID = MUOM.getDefault_UOM_ID(getCtx());
			if (C_UOM_ID > 0)
				setC_UOM_ID (C_UOM_ID);
		}
		//	Qty Precision
		if (newRecord || is_ValueChanged("QtyEntered"))
			setQtyEntered(getQtyEntered());
		if (newRecord || is_ValueChanged("QtyInvoiced"))
			setQtyInvoiced(getQtyInvoiced());

		//	Calculations & Rounding
		setLineNetAmt();
		// TaxAmt recalculations should be done if the TaxAmt is zero
		// or this is an Invoice(Customer) - teo_sarca, globalqss [ 1686773 ]
		if (m_IsSOTrx || getTaxAmt().compareTo(Env.ZERO) == 0)
			setTaxAmt();
		//
		return true;
	}	//	beforeSave

	/**
	 * Recalculate invoice tax
	 * @param oldTax true if the old C_Tax_ID should be used
	 * @return true if success, false otherwise
	 *
	 * @author teo_sarca [ 1583825 ]
	 */
	private boolean updateInvoiceTax(boolean oldTax) {
		MInvoiceTax tax = MInvoiceTax.get (this, getPrecision(), oldTax, get_TrxName());
		if (tax != null) {
			if (!tax.calculateTaxFromLines())
				return false;
		
			// red1 - solving BUGS #[ 1701331 ] , #[ 1786103 ]
			if (tax.getTaxAmt().signum() != 0) {
				if (!tax.save(get_TrxName()))
					return false;
			}
			else {
				if (!tax.is_new() && !tax.delete(false, get_TrxName()))
					return false;
			}
		}
		return true;
	}

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
		if (!newRecord && is_ValueChanged("C_Tax_ID"))
		{
			//	Recalculate Tax for old Tax
			if (!updateInvoiceTax(true))
				return false;
		}
		return updateHeaderTax();
	}	//	afterSave

	/**
	 * 	After Delete
	 *	@param success success
	 *	@return deleted
	 */
	protected boolean afterDelete (boolean success)
	{
		if (!success)
			return success;

		// reset shipment line invoiced flag
		if ( getM_InOutLine_ID() > 0 )
		{
			MInOutLine sLine = new MInOutLine(getCtx(), getM_InOutLine_ID(), get_TrxName());
			sLine.setIsInvoiced(false);
			sLine.saveEx();
		}

		return updateHeaderTax();
	}	//	afterDelete

	/**
	 *	Update Tax & Header
	 *	@return true if header updated with tax
	 */
	private boolean updateHeaderTax()
	{
		// Update header only if the document is not processed - teo_sarca BF [ 2317305 ]
		if (isProcessed() && !is_ValueChanged(COLUMNNAME_Processed))
			return true;

		//	Recalculate Tax for this Tax
		if (!updateInvoiceTax(false))
			return false;

		//	Update Invoice Header
		String sql = "UPDATE C_Invoice i"
			+ " SET TotalLines="
				+ "(SELECT COALESCE(SUM(LineNetAmt),0) FROM C_InvoiceLine il WHERE i.C_Invoice_ID=il.C_Invoice_ID) "
			+ "WHERE C_Invoice_ID=?";
		int no = DB.executeUpdateEx(sql, new Object[]{getC_Invoice_ID()}, get_TrxName());
		if (no != 1)
			log.warning("(1) #" + no);

		if (isTaxIncluded())
			sql = "UPDATE C_Invoice i "
				+ " SET GrandTotal=TotalLines "
				+ "WHERE C_Invoice_ID=?";
		else
			sql = "UPDATE C_Invoice i "
				+ " SET GrandTotal=TotalLines+"
					+ "(SELECT COALESCE(SUM(TaxAmt),0) FROM C_InvoiceTax it WHERE i.C_Invoice_ID=it.C_Invoice_ID) "
					+ "WHERE C_Invoice_ID=?";
		no = DB.executeUpdateEx(sql, new Object[]{getC_Invoice_ID()}, get_TrxName());
		if (no != 1)
			log.warning("(2) #" + no);
		m_parent = null;

		return no == 1;
	}	//	updateHeaderTax


	/**************************************************************************
	 * 	Allocate Landed Costs
	 *	@return error message or ""
	 */
	public String allocateLandedCosts()
	{
		if (isProcessed())
			return "Processed";
		MLandedCost[] lcs = MLandedCost.getLandedCosts(this);
		if (lcs.length == 0)
			return "";
		String sql = "DELETE C_LandedCostAllocation WHERE C_InvoiceLine_ID=" + getC_InvoiceLine_ID();
		int no = DB.executeUpdate(sql, get_TrxName());
		if (no != 0)
			log.info("Deleted #" + no);

		int inserted = 0;
		//	*** Single Criteria ***
		if (lcs.length == 1)
		{
			MLandedCost lc = lcs[0];
			if (lc.getM_InOut_ID() != 0 && lc.getM_InOutLine_ID() == 0)
			{
				//	Create List
				ArrayList<MInOutLine> list = new ArrayList<MInOutLine>();
				MInOut ship = new MInOut (getCtx(), lc.getM_InOut_ID(), get_TrxName());
				MInOutLine[] lines = ship.getLines();
				for (int i = 0; i < lines.length; i++)
				{
					if (lines[i].isDescription() || lines[i].getM_Product_ID() == 0)
						continue;
					if (lc.getM_Product_ID() == 0
						|| lc.getM_Product_ID() == lines[i].getM_Product_ID())
						list.add(lines[i]);
				}
				if (list.size() == 0)
					return "No Matching Lines (with Product) in Shipment";
				//	Calculate total & base
				BigDecimal total = Env.ZERO;
				for (int i = 0; i < list.size(); i++)
				{
					MInOutLine iol = (MInOutLine)list.get(i);
					total = total.add(iol.getBase(lc.getLandedCostDistribution()));
				}
				if (total.signum() == 0)
					return "Total of Base values is 0 - " + lc.getLandedCostDistribution();
				//	Create Allocations
				for (int i = 0; i < list.size(); i++)
				{
					MInOutLine iol = (MInOutLine)list.get(i);
					MLandedCostAllocation lca = new MLandedCostAllocation (this, lc.getM_CostElement_ID());
					lca.setM_Product_ID(iol.getM_Product_ID());
					lca.setM_AttributeSetInstance_ID(iol.getM_AttributeSetInstance_ID());
					BigDecimal base = iol.getBase(lc.getLandedCostDistribution());
					lca.setBase(base);
					// MZ Goodwill
					// add set Qty from InOutLine
					lca.setQty(iol.getMovementQty());
					// end MZ
					if (base.signum() != 0)
					{
						double result = getLineNetAmt().multiply(base).doubleValue();
						result /= total.doubleValue();
						lca.setAmt(result, getPrecision());
					}
					if (!lca.save())
						return "Cannot save line Allocation = " + lca;
					inserted++;
				}
				log.info("Inserted " + inserted);
				allocateLandedCostRounding();
				return "";
			}
			//	Single Line
			else if (lc.getM_InOutLine_ID() != 0)
			{
				MInOutLine iol = new MInOutLine (getCtx(), lc.getM_InOutLine_ID(), get_TrxName());
				if (iol.isDescription() || iol.getM_Product_ID() == 0)
					return "Invalid Receipt Line - " + iol;
				MLandedCostAllocation lca = new MLandedCostAllocation (this, lc.getM_CostElement_ID());
				lca.setM_Product_ID(iol.getM_Product_ID());
				lca.setM_AttributeSetInstance_ID(iol.getM_AttributeSetInstance_ID());
				BigDecimal base = iol.getBase(lc.getLandedCostDistribution()); 
				lca.setBase(base);
				lca.setAmt(getLineNetAmt());
				// MZ Goodwill
				// add set Qty from InOutLine
				lca.setQty(iol.getMovementQty());
				// end MZ
				if (lca.save())
					return "";
				return "Cannot save single line Allocation = " + lc;
			}
			//	Single Product
			else if (lc.getM_Product_ID() != 0)
			{
				MLandedCostAllocation lca = new MLandedCostAllocation (this, lc.getM_CostElement_ID());
				lca.setM_Product_ID(lc.getM_Product_ID());	//	No ASI
				lca.setAmt(getLineNetAmt());
				if (lca.save())
					return "";
				return "Cannot save Product Allocation = " + lc;
			}
			else
				return "No Reference for " + lc;
		}

		//	*** Multiple Criteria ***
		String LandedCostDistribution = lcs[0].getLandedCostDistribution();
		int M_CostElement_ID = lcs[0].getM_CostElement_ID();
		for (int i = 0; i < lcs.length; i++)
		{
			MLandedCost lc = lcs[i];
			if (!LandedCostDistribution.equals(lc.getLandedCostDistribution()))
				return "Multiple Landed Cost Rules must have consistent Landed Cost Distribution";
			if (lc.getM_Product_ID() != 0 && lc.getM_InOut_ID() == 0 && lc.getM_InOutLine_ID() == 0)
				return "Multiple Landed Cost Rules cannot directly allocate to a Product";
			if (M_CostElement_ID != lc.getM_CostElement_ID())
				return "Multiple Landed Cost Rules cannot different Cost Elements";
		}
		//	Create List
		ArrayList<MInOutLine> list = new ArrayList<MInOutLine>();
		for (int ii = 0; ii < lcs.length; ii++)
		{
			MLandedCost lc = lcs[ii];
			if (lc.getM_InOut_ID() != 0 && lc.getM_InOutLine_ID() == 0)		//	entire receipt
			{
				MInOut ship = new MInOut (getCtx(), lc.getM_InOut_ID(), get_TrxName());
				MInOutLine[] lines = ship.getLines();
				for (int i = 0; i < lines.length; i++)
				{
					if (lines[i].isDescription()		//	decription or no product
						|| lines[i].getM_Product_ID() == 0)
						continue;
					if (lc.getM_Product_ID() == 0		//	no restriction or product match
						|| lc.getM_Product_ID() == lines[i].getM_Product_ID())
						list.add(lines[i]);
				}
			}
			else if (lc.getM_InOutLine_ID() != 0)	//	receipt line
			{
				MInOutLine iol = new MInOutLine (getCtx(), lc.getM_InOutLine_ID(), get_TrxName());
				if (!iol.isDescription() && iol.getM_Product_ID() != 0)
					list.add(iol);
			}
		}
		if (list.size() == 0)
			return "No Matching Lines (with Product)";
		//	Calculate total & base
		BigDecimal total = Env.ZERO;
		for (int i = 0; i < list.size(); i++)
		{
			MInOutLine iol = (MInOutLine)list.get(i);
			total = total.add(iol.getBase(LandedCostDistribution));
		}
		if (total.signum() == 0)
			return "Total of Base values is 0 - " + LandedCostDistribution;
		//	Create Allocations
		for (int i = 0; i < list.size(); i++)
		{
			MInOutLine iol = (MInOutLine)list.get(i);
			MLandedCostAllocation lca = new MLandedCostAllocation (this, lcs[0].getM_CostElement_ID());
			lca.setM_Product_ID(iol.getM_Product_ID());
			lca.setM_AttributeSetInstance_ID(iol.getM_AttributeSetInstance_ID());
			BigDecimal base = iol.getBase(LandedCostDistribution);
			lca.setBase(base);
			// MZ Goodwill
			// add set Qty from InOutLine
			lca.setQty(iol.getMovementQty());
			// end MZ
			if (base.signum() != 0)
			{
				double result = getLineNetAmt().multiply(base).doubleValue();
				result /= total.doubleValue();
				lca.setAmt(result, getPrecision());
			}
			if (!lca.save())
				return "Cannot save line Allocation = " + lca;
			inserted++;
		}

		log.info("Inserted " + inserted);
		allocateLandedCostRounding();
		return "";
	}	//	allocate Costs

	/**
	 * 	Allocate Landed Cost - Enforce Rounding
	 */
	private void allocateLandedCostRounding()
	{
		MLandedCostAllocation[] allocations = MLandedCostAllocation.getOfInvoiceLine(
			getCtx(), getC_InvoiceLine_ID(), get_TrxName());
		MLandedCostAllocation largestAmtAllocation = null;
		BigDecimal allocationAmt = Env.ZERO;
		for (int i = 0; i < allocations.length; i++)
		{
			MLandedCostAllocation allocation = allocations[i];
			if (largestAmtAllocation == null
				|| allocation.getAmt().compareTo(largestAmtAllocation.getAmt()) > 0)
				largestAmtAllocation = allocation;
			allocationAmt = allocationAmt.add(allocation.getAmt());
		}
		BigDecimal difference = getLineNetAmt().subtract(allocationAmt);
		if (difference.signum() != 0)
		{
			largestAmtAllocation.setAmt(largestAmtAllocation.getAmt().add(difference));
			largestAmtAllocation.save();
			log.config("Difference=" + difference
				+ ", C_LandedCostAllocation_ID=" + largestAmtAllocation.getC_LandedCostAllocation_ID()
				+ ", Amt" + largestAmtAllocation.getAmt());
		}
	}	//	allocateLandedCostRounding

	// MZ Goodwill
	/**
	 * 	Get LandedCost of InvoiceLine
	 * 	@param whereClause starting with AND
	 * 	@return landedCost
	 */
	public MLandedCost[] getLandedCost (String whereClause)
	{
		ArrayList<MLandedCost> list = new ArrayList<MLandedCost>();
		String sql = "SELECT * FROM C_LandedCost WHERE C_InvoiceLine_ID=? ";
		if (whereClause != null)
			sql += whereClause;
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getC_InvoiceLine_ID());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				MLandedCost lc = new MLandedCost(getCtx(), rs, get_TrxName());
				list.add(lc);
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "getLandedCost", e);
		}
		finally
		{
			try
			{
				if (pstmt != null)
					pstmt.close ();
			}
			catch (Exception e)
			{}
			pstmt = null;
		}

		//
		MLandedCost[] landedCost = new MLandedCost[list.size()];
		list.toArray(landedCost);
		return landedCost;
	}	//	getLandedCost

	/**
	 * 	Copy LandedCost From other InvoiceLine.
	 *	@param otherInvoiceLine invoiceline
	 *	@return number of lines copied
	 */
	public int copyLandedCostFrom (MInvoiceLine otherInvoiceLine)
	{
		if (otherInvoiceLine == null)
			return 0;
		MLandedCost[] fromLandedCosts = otherInvoiceLine.getLandedCost(null);
		int count = 0;
		for (int i = 0; i < fromLandedCosts.length; i++)
		{
			MLandedCost landedCost = new MLandedCost (getCtx(), 0, get_TrxName());
			MLandedCost fromLandedCost = fromLandedCosts[i];
			PO.copyValues (fromLandedCost, landedCost, fromLandedCost.getAD_Client_ID(), fromLandedCost.getAD_Org_ID());
			landedCost.setC_InvoiceLine_ID(getC_InvoiceLine_ID());
			landedCost.set_ValueNoCheck ("C_LandedCost_ID", I_ZERO);	// new
			if (landedCost.save(get_TrxName()))
				count++;
		}
		if (fromLandedCosts.length != count)
			log.log(Level.SEVERE, "LandedCost difference - From=" + fromLandedCosts.length + " <> Saved=" + count);
		return count;
	}	//	copyLinesFrom
	// end MZ

	/**
	 * @param rmaline
	 */
	public void setRMALine(MRMALine rmaLine)
	{
		// Check if this invoice is CreditMemo - teo_sarca [ 2804142 ]
		if (!getParent().isCreditMemo())
		{
			throw new AdempiereException("InvoiceNotCreditMemo");
		}
		setAD_Org_ID(rmaLine.getAD_Org_ID());
        setM_RMALine_ID(rmaLine.getM_RMALine_ID());
        setDescription(rmaLine.getDescription());
        setLine(rmaLine.getLine());
        setC_Charge_ID(rmaLine.getC_Charge_ID());
        setM_Product_ID(rmaLine.getM_Product_ID());
        setC_UOM_ID(rmaLine.getC_UOM_ID());
        setC_Tax_ID(rmaLine.getC_Tax_ID());
        setPrice(rmaLine.getAmt());
        BigDecimal qty = rmaLine.getQty();
        if (rmaLine.getQtyInvoiced() != null)
        	qty = qty.subtract(rmaLine.getQtyInvoiced());
        setQty(qty);
        setLineNetAmt();
        setTaxAmt();
        setLineTotalAmt(rmaLine.getLineNetAmt());
        setC_Project_ID(rmaLine.getC_Project_ID());
        setC_Activity_ID(rmaLine.getC_Activity_ID());
        setC_Campaign_ID(rmaLine.getC_Campaign_ID());
	}

	/**
	 * @return matched qty
	 */
	public BigDecimal getMatchedQty()
	{
		String sql = "SELECT COALESCE(SUM("+MMatchInv.COLUMNNAME_Qty+"),0)"
						+" FROM "+MMatchInv.Table_Name
						+" WHERE "+MMatchInv.COLUMNNAME_C_InvoiceLine_ID+"=?"
							+" AND "+MMatchInv.COLUMNNAME_Processed+"=?";
		return DB.getSQLValueBDEx(get_TrxName(), sql, getC_InvoiceLine_ID(), true);
	}

}	//	MInvoiceLine
