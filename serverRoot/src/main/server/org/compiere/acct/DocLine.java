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
package org.compiere.acct;

import java.math.*;
import java.sql.*;

import org.compiere.model.*;
import org.compiere.util.*;

/**
 *  Standard Document Line
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: DocLine.java,v 1.2 2006/07/30 00:53:33 jjanke Exp $
 */
public class DocLine implements IDocLine
{	
	/**
	 *	Create Document Line
	 *	@param po line persistent object
	 *	@param doc header
	 */
	public DocLine (PO po, Doc doc)
	{
		if (po == null)
			throw new IllegalArgumentException("PO is null");
		p_po = po;
		m_doc = doc;
		//
		//  Document Consistency
		if (p_po.getAD_Org_ID() == 0)
			p_po.setAD_Org_ID(m_doc.getAD_Org_ID());
	}	//	DocLine

	/** Persistent Object		*/
	protected PO				p_po = null;
	/** Parent					*/
	private Doc					m_doc = null;
	/**	 Log					*/
	protected CLogger			log = CLogger.getCLogger(getClass());

	/** Qty                     */
	private BigDecimal	 		m_qty = null;

	//  -- GL Amounts
	/** Debit Journal Amt   	*/
	private BigDecimal      	m_AmtSourceDr = Env.ZERO;
	/** Credit Journal Amt		*/
	private BigDecimal      	m_AmtSourceCr = Env.ZERO;
	/** Net Line Amt            */
	private BigDecimal			m_LineNetAmt = null;
	/** List Amount             */
	private BigDecimal			m_ListAmt = Env.ZERO;
	/** Discount Amount         */
	private BigDecimal 			m_DiscountAmt = Env.ZERO;

	/** Converted Amounts   	*/
	private BigDecimal      	m_AmtAcctDr = null;
	private BigDecimal      	m_AmtAcctCr = null;
	/** Acct Schema				*/
	private int             	m_C_AcctSchema_ID = 0;

	/**	Product Costs			*/
	private ProductCost			m_productCost = null;
	/** Production indicator	*/
	private boolean 			m_productionBOM = false;
	/** Account used only for GL Journal    */
	private MAccount 			m_account = null;

	/** Accounting Date				*/
	private Timestamp			m_DateAcct = null;
	/** Document Date				*/
	private Timestamp			m_DateDoc = null;
	/** Sales Region				*/
	private int					m_C_SalesRegion_ID = -1;
	/** Sales Region				*/
	private int					m_C_BPartner_ID = -1;
	/** Location From				*/
	private int					m_C_LocFrom_ID = 0;
	/** Location To					*/
	private int					m_C_LocTo_ID = 0;
	/** Item						*/
	private Boolean				m_isItem = null;
	/** Currency					*/
	private int					m_C_Currency_ID = -1;
	/** Conversion Type				*/
	private int					m_C_ConversionType_ID = -1;
	/** Period						*/
	private int					m_C_Period_ID = -1;

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getC_Currency_ID()
	 */
	public int getC_Currency_ID ()
	{
		if (m_C_Currency_ID == -1)
		{
			int index = p_po.get_ColumnIndex("C_Currency_ID");
			if (index != -1)
			{
				Integer ii = (Integer)p_po.get_Value(index);
				if (ii != null)
					m_C_Currency_ID = ii.intValue();
			}
			if (m_C_Currency_ID <= 0)
				m_C_Currency_ID = m_doc.getC_Currency_ID();
		}
		return m_C_Currency_ID;
	}   //  getC_Currency_ID

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getC_ConversionType_ID()
	 */
	public int getC_ConversionType_ID ()
	{
		if (m_C_ConversionType_ID == -1)
		{
			int index = p_po.get_ColumnIndex("C_ConversionType_ID");
			if (index != -1)
			{
				Integer ii = (Integer)p_po.get_Value(index);
				if (ii != null)
					m_C_ConversionType_ID = ii.intValue();
			}
			if (m_C_ConversionType_ID <= 0)
				m_C_ConversionType_ID = m_doc.getC_ConversionType_ID();
		}
		return m_C_ConversionType_ID;
	}   //  getC_ConversionType_ID

	/**
	 * 	Set C_ConversionType_ID
	 *	@param C_ConversionType_ID id
	 */
	protected void setC_ConversionType_ID(int C_ConversionType_ID)
	{
		m_C_ConversionType_ID = C_ConversionType_ID;
	}	//	setC_ConversionType_ID
	
	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#setAmount(java.math.BigDecimal)
	 */
	public void setAmount (BigDecimal sourceAmt)
	{
		m_AmtSourceDr = sourceAmt == null ? Env.ZERO : sourceAmt;
		m_AmtSourceCr = Env.ZERO;
	}   //  setAmounts

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#setAmount(java.math.BigDecimal, java.math.BigDecimal)
	 */
	public void setAmount (BigDecimal amtSourceDr, BigDecimal amtSourceCr)
	{
		m_AmtSourceDr = amtSourceDr == null ? Env.ZERO : amtSourceDr;
		m_AmtSourceCr = amtSourceCr == null ? Env.ZERO : amtSourceCr;
	}   //  setAmounts

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#setConvertedAmt(int, java.math.BigDecimal, java.math.BigDecimal)
	 */
	public void setConvertedAmt (int C_AcctSchema_ID, BigDecimal amtAcctDr, BigDecimal amtAcctCr)
	{
		m_C_AcctSchema_ID = C_AcctSchema_ID;
		m_AmtAcctDr = amtAcctDr;
		m_AmtAcctCr = amtAcctCr;
	}   //  setConvertedAmt

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getAmtSource()
	 */
	public BigDecimal getAmtSource()
	{
		return m_AmtSourceDr.subtract(m_AmtSourceCr);
	}   //  getAmount

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getAmtSourceDr()
	 */
	public BigDecimal getAmtSourceDr()
	{
		return m_AmtSourceDr;
	}   //  getAmtSourceDr

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getAmtSourceCr()
	 */
	public BigDecimal getAmtSourceCr()
	{
		return m_AmtSourceCr;
	}   //  getAmtSourceCr

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getAmtAcctDr()
	 */
	public BigDecimal getAmtAcctDr()
	{
		return m_AmtAcctDr;
	}   //  getAmtAcctDr

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getAmtAcctCr()
	 */
	public BigDecimal getAmtAcctCr()
	{
		return m_AmtAcctCr;
	}   //  getAmtAccrCr

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getChargeAmt()
	 */
	public BigDecimal getChargeAmt()
	{
		int index = p_po.get_ColumnIndex("ChargeAmt");
		if (index != -1)
		{
			BigDecimal bd = (BigDecimal)p_po.get_Value(index);
			if (bd != null)
				return bd;
		}
		return Env.ZERO;
	}   //  getChargeAmt

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#setAmount(java.math.BigDecimal, java.math.BigDecimal, java.math.BigDecimal)
	 */
	public void setAmount (BigDecimal LineNetAmt, BigDecimal PriceList, BigDecimal Qty)
	{
		m_LineNetAmt =  LineNetAmt == null ? Env.ZERO : LineNetAmt;

		if (PriceList != null && Qty != null)
			m_ListAmt = PriceList.multiply(Qty);
		if (m_ListAmt.equals(Env.ZERO))
			m_ListAmt = m_LineNetAmt;
		m_DiscountAmt = m_ListAmt.subtract(m_LineNetAmt);
		//
		setAmount (m_ListAmt, m_DiscountAmt);
	//	Log.trace(this,Log.l6_Database, "DocLine_Invoice.setAmount",
	//		"LineNet=" + m_LineNetAmt + ", List=" + m_ListAmt + ", Discount=" + m_DiscountAmt
	//		+ " => Amount=" + getAmount());
	}   //  setAmounts

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getDiscount()
	 */
	public BigDecimal getDiscount()
	{
		return m_DiscountAmt;
	}   //  getDiscount

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getListAmount()
	 */
	public BigDecimal getListAmount()
	{
		return m_ListAmt;
	}   //  getListAmount

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#setLineNetAmtDifference(java.math.BigDecimal)
	 */
	public void setLineNetAmtDifference (BigDecimal diff)
	{
		String msg = "Diff=" + diff
			+ " - LineNetAmt=" + m_LineNetAmt;
		m_LineNetAmt = m_LineNetAmt.subtract(diff);
		m_DiscountAmt = m_ListAmt.subtract(m_LineNetAmt);
		setAmount (m_ListAmt, m_DiscountAmt);
		msg += " -> " + m_LineNetAmt;
		log.fine(msg);
	}	//	setLineNetAmtDifference

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#setDateAcct(java.sql.Timestamp)
	 */
	public void setDateAcct (Timestamp dateAcct)
	{
		m_DateAcct = dateAcct;
	}   //  setDateAcct

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getDateAcct()
	 */
	public Timestamp getDateAcct ()
	{
		if (m_DateAcct != null)
			return m_DateAcct;
		int index = p_po.get_ColumnIndex("DateAcct");
		if (index != -1)
		{
			m_DateAcct = (Timestamp)p_po.get_Value(index);
			if (m_DateAcct != null)
				return m_DateAcct;
		}
		m_DateAcct = m_doc.getDateAcct();
		return m_DateAcct;
	}   //  getDateAcct

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#setDateDoc(java.sql.Timestamp)
	 */
	public void setDateDoc (Timestamp dateDoc)
	{
		m_DateDoc = dateDoc;
	}   //  setDateDoc

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getDateDoc()
	 */
	public Timestamp getDateDoc ()
	{
		if (m_DateDoc != null)
			return m_DateDoc;
		int index = p_po.get_ColumnIndex("DateDoc");
		if (index != -1)
		{
			m_DateDoc = (Timestamp)p_po.get_Value(index);
			if (m_DateDoc != null)
				return m_DateDoc;
		}
		m_DateDoc = m_doc.getDateDoc();
		return m_DateDoc;
	}   //  getDateDoc


	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#setAccount(org.compiere.model.MAccount)
	 */
	public void setAccount (MAccount acct)
	{
		m_account = acct;
	}   //  setAccount

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getAccount()
	 */
	public MAccount getAccount()
	{
		return m_account;
	}   //  getAccount

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getAccount(int, org.compiere.model.MAcctSchema)
	 */
	public MAccount getAccount (int AcctType, MAcctSchema as)
	{
		//	Charge Account
		if (getM_Product_ID() == 0 && getC_Charge_ID() != 0)
		{
			BigDecimal amt = new BigDecimal (-1);		//	Revenue (-)
			if (!m_doc.isSOTrx())
				amt = new BigDecimal (+1);				//	Expense (+)
			MAccount acct = getChargeAccount(as, amt);
			if (acct != null)
				return acct;
		}
		//	Product Account
		return getProductCost().getAccount (AcctType, as);
	}   //  getAccount

	/**
	 * 	Get Charge
	 * 	@return C_Charge_ID
	 */
	protected int getC_Charge_ID()
	{
		int index = p_po.get_ColumnIndex("C_Charge_ID");
		if (index != -1)
		{
			Integer ii = (Integer)p_po.get_Value(index);
			if (ii != null)
				return ii.intValue();
		}
		return 0;
	}	//	getC_Charge_ID

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getChargeAccount(org.compiere.model.MAcctSchema, java.math.BigDecimal)
	 */
	public MAccount getChargeAccount (MAcctSchema as, BigDecimal amount)
	{
		int C_Charge_ID = getC_Charge_ID();
		if (C_Charge_ID == 0)
			return null;
		return MCharge.getAccount(C_Charge_ID, as, amount);
	}   //  getChargeAccount

	/**
	 * 	Get Period
	 * 	@return C_Period_ID
	 */
	public int getC_Period_ID()
	{
		if (m_C_Period_ID == -1)
		{
			int index = p_po.get_ColumnIndex("C_Period_ID");
			if (index != -1)
			{
				Integer ii = (Integer)p_po.get_Value(index);
				if (ii != null)
					m_C_Period_ID = ii.intValue();
			}
			if (m_C_Period_ID == -1)
				m_C_Period_ID = 0;
		}
		return m_C_Period_ID;
	}	//	getC_Period_ID
	
	/**
	 * 	Set C_Period_ID
	 *	@param C_Period_ID id
	 */
	protected void setC_Period_ID (int C_Period_ID)
	{
		m_C_Period_ID = C_Period_ID;
	}	//	setC_Period_ID
	
	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getC_AcctSchema_ID()
	 */
	public int getC_AcctSchema_ID()
	{
		return m_C_AcctSchema_ID;
	}   //  getC_AcctSchema_ID

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#get_ID()
	 */
	public int get_ID()
	{
		return p_po.get_ID();
	}	//	get_ID
	
	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getAD_Org_ID()
	 */
	public int getAD_Org_ID()
	{
		return p_po.getAD_Org_ID();
	}	//	getAD_Org_ID
	
	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getOrder_Org_ID()
	 */
	public int getOrder_Org_ID()
	{
		int C_OrderLine_ID = getC_OrderLine_ID();
		if (C_OrderLine_ID != 0)
		{
			String sql = "SELECT AD_Org_ID FROM C_OrderLine WHERE C_OrderLine_ID=?";
			int AD_Org_ID = DB.getSQLValue(null, sql, C_OrderLine_ID);
			if (AD_Org_ID > 0)
				return AD_Org_ID;
		}
		return getAD_Org_ID();
	}	//	getOrder_Org_ID

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getM_Product_ID()
	 */
	public int getM_Product_ID()
	{
		int index = p_po.get_ColumnIndex("M_Product_ID");
		if (index != -1)
		{
			Integer ii = (Integer)p_po.get_Value(index);
			if (ii != null)
				return ii.intValue();
		}
		return 0;
	}   //  getM_Product_ID

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#isItem()
	 */
	public boolean isItem()
	{
		if (m_isItem != null)
			return m_isItem.booleanValue();

		m_isItem = Boolean.FALSE;
		if (getM_Product_ID() != 0)
		{
			MProduct product = MProduct.get(Env.getCtx(), getM_Product_ID());
			if (product.get_ID() == getM_Product_ID() && product.isItem())
				m_isItem = Boolean.TRUE;
		}
		return m_isItem.booleanValue();
	}	//	isItem

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getM_AttributeSetInstance_ID()
	 */
	public int getM_AttributeSetInstance_ID()
	{
		int index = p_po.get_ColumnIndex("M_AttributeSetInstance_ID");
		if (index != -1)
		{
			Integer ii = (Integer)p_po.get_Value(index);
			if (ii != null)
				return ii.intValue();
		}
		return 0;
	}   //  getM_AttributeSetInstance_ID

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getM_Locator_ID()
	 */
	public int getM_Locator_ID()
	{
		int index = p_po.get_ColumnIndex("M_Locator_ID");
		if (index != -1)
		{
			Integer ii = (Integer)p_po.get_Value(index);
			if (ii != null)
				return ii.intValue();
		}
		return 0;
	}   //  getM_Locator_ID

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getM_LocatorTo_ID()
	 */
	public int getM_LocatorTo_ID()
	{
		int index = p_po.get_ColumnIndex("M_LocatorTo_ID");
		if (index != -1)
		{
			Integer ii = (Integer)p_po.get_Value(index);
			if (ii != null)
				return ii.intValue();
		}
		return 0;
	}   //  getM_LocatorTo_ID

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#setProductionBOM(boolean)
	 */
	public void setProductionBOM(boolean productionBOM)
	{
		m_productionBOM = productionBOM;
	}	//	setProductionBOM
	
	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#isProductionBOM()
	 */
	public boolean isProductionBOM()
	{
		return m_productionBOM;
	}	//	isProductionBOM
	
	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getM_ProductionPlan_ID()
	 */
	public int getM_ProductionPlan_ID()
	{
		int index = p_po.get_ColumnIndex("M_ProductionPlan_ID");
		if (index != -1)
		{
			Integer ii = (Integer)p_po.get_Value(index);
			if (ii != null)
				return ii.intValue();
		}
		return 0;
	}   //  getM_ProductionPlan_ID

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getC_OrderLine_ID()
	 */
	public int getC_OrderLine_ID()
	{
		int index = p_po.get_ColumnIndex("C_OrderLine_ID");
		if (index != -1)
		{
			Integer ii = (Integer)p_po.get_Value(index);
			if (ii != null)
				return ii.intValue();
		}
		return 0;
	}   //  getC_OrderLine_ID

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getC_LocFrom_ID()
	 */
	public int getC_LocFrom_ID()
	{
		return m_C_LocFrom_ID;
	}	//	getC_LocFrom_ID
	
	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#setC_LocFrom_ID(int)
	 */
	public void setC_LocFrom_ID(int C_LocFrom_ID)
	{
		m_C_LocFrom_ID = C_LocFrom_ID;
	}	//	setC_LocFrom_ID

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getC_LocTo_ID()
	 */
	public int getC_LocTo_ID()
	{
		return m_C_LocTo_ID;
	}	//	getC_LocTo_ID

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#setC_LocTo_ID(int)
	 */
	public void setC_LocTo_ID(int C_LocTo_ID)
	{
		m_C_LocTo_ID = C_LocTo_ID;
	}	//	setC_LocTo_ID

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getProductCost()
	 */
	public ProductCost getProductCost()
	{
		if (m_productCost == null)
			m_productCost = new ProductCost (Env.getCtx(), 
				getM_Product_ID(), getM_AttributeSetInstance_ID(), p_po.get_TrxName());
		return m_productCost;
	}	//	getProductCost
	
	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getProductCosts(org.compiere.model.MAcctSchema, int, boolean)
	 */
	public BigDecimal getProductCosts (MAcctSchema as, int AD_Org_ID, boolean zeroCostsOK)
	{
		ProductCost pc = getProductCost();
		int C_OrderLine_ID = getC_OrderLine_ID();
		String costingMethod = null;
		BigDecimal costs = pc.getProductCosts(as, AD_Org_ID, costingMethod, 
			C_OrderLine_ID, zeroCostsOK);
		if (costs != null)
			return costs;
		return Env.ZERO;
	}   //  getProductCosts

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getProduct()
	 */
	public MProduct getProduct()
	{
		if (m_productCost == null)
			m_productCost = new ProductCost (Env.getCtx(), 
				getM_Product_ID(), getM_AttributeSetInstance_ID(), p_po.get_TrxName());
		if (m_productCost != null)
			return m_productCost.getProduct();
		return null;
	}	//	getProduct

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getC_RevenueRecognition_ID()
	 */
	public int getC_RevenueRecognition_ID()
	{
		MProduct product = getProduct();
		if (product != null)
			return product.getC_RevenueRecognition_ID();
		return 0;
	}   //  getC_RevenueRecognition_ID

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getC_UOM_ID()
	 */
	public int getC_UOM_ID()
	{
		//	Trx UOM
		int index = p_po.get_ColumnIndex("C_UOM_ID");
		if (index != -1)
		{
			Integer ii = (Integer)p_po.get_Value(index);
			if (ii != null)
				return ii.intValue();
		}
		//  Storage UOM
		MProduct product = getProduct();
		if (product != null)
			return product.getC_UOM_ID();
		//
		return 0;
	}   //  getC_UOM

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#setQty(java.math.BigDecimal, boolean)
	 */
	public void setQty (BigDecimal qty, boolean isSOTrx)
	{
		if (qty == null)
			m_qty = Env.ZERO;
		else if (isSOTrx)
			m_qty = qty.negate();
		else
			m_qty = qty;
		getProductCost().setQty (qty);
	}   //  setQty

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getQty()
	 */
	public BigDecimal getQty()
	{
		return m_qty;
	}   //  getQty

	
	
	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getDescription()
	 */
	public String getDescription()
	{
		int index = p_po.get_ColumnIndex("Description");
		if (index != -1)
			return (String)p_po.get_Value(index);
		return null;
	}	//	getDescription

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getC_Tax_ID()
	 */
	public int getC_Tax_ID()
	{
		int index = p_po.get_ColumnIndex("C_Tax_ID");
		if (index != -1)
		{
			Integer ii = (Integer)p_po.get_Value(index);
			if (ii != null)
				return ii.intValue();
		}
		return 0;
	}	//	getC_Tax_ID

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getLine()
	 */
	public int getLine()
	{
		int index = p_po.get_ColumnIndex("Line");
		if (index != -1)
		{
			Integer ii = (Integer)p_po.get_Value(index);
			if (ii != null)
				return ii.intValue();
		}
		return 0;
	}   //  getLine

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getC_BPartner_ID()
	 */
	public int getC_BPartner_ID()
	{
		if (m_C_BPartner_ID == -1)
		{
			int index = p_po.get_ColumnIndex("C_BPartner_ID");
			if (index != -1)
			{
				Integer ii = (Integer)p_po.get_Value(index);
				if (ii != null)
					m_C_BPartner_ID = ii.intValue();
			}
			if (m_C_BPartner_ID <= 0)
				m_C_BPartner_ID = m_doc.getC_BPartner_ID();
		}
		return m_C_BPartner_ID;
	}   //  getC_BPartner_ID

	/**
	 * 	Set C_BPartner_ID
	 *	@param C_BPartner_ID id
	 */
	protected void setC_BPartner_ID (int C_BPartner_ID)
	{
		m_C_BPartner_ID = C_BPartner_ID;
	}	//	setC_BPartner_ID
	
	
	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getC_BPartner_Location_ID()
	 */
	public int getC_BPartner_Location_ID()
	{
		int index = p_po.get_ColumnIndex("C_BPartner_Location_ID");
		if (index != -1)
		{
			Integer ii = (Integer)p_po.get_Value(index);
			if (ii != null)
				return ii.intValue();
		}
		return m_doc.getC_BPartner_Location_ID();
	}	//	getC_BPartner_Location_ID
	
	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getAD_OrgTrx_ID()
	 */
	public int getAD_OrgTrx_ID()
	{
		int index = p_po.get_ColumnIndex("AD_OrgTrx_ID");
		if (index != -1)
		{
			Integer ii = (Integer)p_po.get_Value(index);
			if (ii != null)
				return ii.intValue();
		}
		return 0;
	}   //  getAD_OrgTrx_ID

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getC_SalesRegion_ID()
	 */
	public int getC_SalesRegion_ID()
	{
		if (m_C_SalesRegion_ID == -1)	//	never tried
		{
			if (getC_BPartner_Location_ID() != 0)
			//	&& m_acctSchema.isAcctSchemaElement(MAcctSchemaElement.ELEMENTTYPE_SalesRegion))
			{
				String sql = "SELECT COALESCE(C_SalesRegion_ID,0) FROM C_BPartner_Location WHERE C_BPartner_Location_ID=?";
				m_C_SalesRegion_ID = DB.getSQLValue (null,
					sql, getC_BPartner_Location_ID());
				log.fine("C_SalesRegion_ID=" + m_C_SalesRegion_ID + " (from BPL)" );
				if (m_C_SalesRegion_ID == 0)
					m_C_SalesRegion_ID = -2;	//	don't try again
			}
			else
				m_C_SalesRegion_ID = -2;		//	don't try again
		}
		if (m_C_SalesRegion_ID < 0)				//	invalid
			return 0;
		return m_C_SalesRegion_ID;
	}   //  getC_SalesRegion_ID

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getC_Project_ID()
	 */
	public int getC_Project_ID()
	{
		int index = p_po.get_ColumnIndex("C_Project_ID");
		if (index != -1)
		{
			Integer ii = (Integer)p_po.get_Value(index);
			if (ii != null)
				return ii.intValue();
		}
		return 0;
	}   //  getC_Project_ID

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getC_Campaign_ID()
	 */
	public int getC_Campaign_ID()
	{
		int index = p_po.get_ColumnIndex("C_Campaign_ID");
		if (index != -1)
		{
			Integer ii = (Integer)p_po.get_Value(index);
			if (ii != null)
				return ii.intValue();
		}
		return 0;
	}   //  getC_Campaign_ID

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getC_Activity_ID()
	 */
	public int getC_Activity_ID()
	{
		int index = p_po.get_ColumnIndex("C_Activity_ID");
		if (index != -1)
		{
			Integer ii = (Integer)p_po.get_Value(index);
			if (ii != null)
				return ii.intValue();
		}
		return 0;
	}   //  getC_Activity_ID

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getUser1_ID()
	 */
	public int getUser1_ID()
	{
		int index = p_po.get_ColumnIndex("User1_ID");
		if (index != -1)
		{
			Integer ii = (Integer)p_po.get_Value(index);
			if (ii != null)
				return ii.intValue();
		}
		return 0;
	}   //  getUser1_ID

	/* (non-Javadoc)
	 * @see org.compiere.acct.IDocLine#getUser2_ID()
	 */
	public int getUser2_ID()
	{
		int index = p_po.get_ColumnIndex("User2_ID");
		if (index != -1)
		{
			Integer ii = (Integer)p_po.get_Value(index);
			if (ii != null)
				return ii.intValue();
		}
		return 0;
	}   //  getUser2_ID
        
        	/* (non-Javadoc)
			 * @see org.compiere.acct.IDocLine#getValue(java.lang.String)
			 */
	public int getValue(String ColumnName)
	{
		int index = p_po.get_ColumnIndex(ColumnName);
		if (index != -1)
		{
			Integer ii = (Integer)p_po.get_Value(index);
			if (ii != null)
				return ii.intValue();
		}
		return 0;
	}   //  getValue

	/**
	 *  String representation
	 *  @return String
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer("DocLine=[");
		sb.append(p_po.get_ID());
		if (getDescription() != null)
			sb.append(",").append(getDescription());
		if (getM_Product_ID() != 0)
			sb.append(",M_Product_ID=").append(getM_Product_ID());
		sb.append(",Qty=").append(m_qty)
			.append(",Amt=").append(getAmtSource())
			.append("]");
		return sb.toString();
	}	//	toString

}	//	DocumentLine
