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

import org.adempiere.engine.IDocumentLine;
import org.adempiere.exceptions.FillMandatoryException;
import org.adempiere.exceptions.WarehouseLocatorConflictException;
import org.compiere.util.*;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

/**
 * 	InOut Line
 *
 *  @author Jorg Janke
 *  @version $Id: MInOutLine.java,v 1.5 2006/07/30 00:51:03 jjanke Exp $
 *
 *  @author Teo Sarca, www.arhipac.ro
 *  		<li>BF [ 2784194 ] Check Warehouse-Locator conflict
 *  			https://sourceforge.net/tracker/?func=detail&aid=2784194&group_id=176962&atid=879332
 *  		<li>BF [ 2797938 ] Receipt should not allow lines with Qty=0
 *  			https://sourceforge.net/tracker/?func=detail&atid=879332&aid=2797938&group_id=176962
 */
public class MInOutLine extends X_M_InOutLine
implements IDocumentLine
{
	/**
	 *
	 */
	private static final long serialVersionUID = 8630611882798722864L;

	/**
	 * 	Get Ship lines Of Order Line
	 *	@param ctx context
	 *	@param C_OrderLine_ID line
	 *	@param where optional addition where clause
	 *  @param trxName transaction
	 *	@return array of receipt lines
	 */
	public static MInOutLine[] getOfOrderLine (Properties ctx,
		int C_OrderLine_ID, String where, String trxName)
	{
		String whereClause = "C_OrderLine_ID=?" + (!Util.isEmpty(where, true) ? " AND "+where : "");
		List<MInOutLine> list = new Query(ctx, Table_Name, whereClause, trxName)
									.setParameters(C_OrderLine_ID)
									.list();
		return list.toArray (new MInOutLine[list.size()]);
	}	//	getOfOrderLine

	/**
	 * 	Get Ship lines Of RMA Line
	 *	@param ctx context
	 *	@param M_RMALine_ID line
	 *	@param where optional addition where clause
	 *  @param trxName transaction
	 *	@return array of receipt lines
	 */
	public static MInOutLine[] getOfRMALine (Properties ctx,
		int M_RMALine_ID, String where, String trxName)
	{
		String whereClause = "M_RMALine_ID=? " + (!Util.isEmpty(where, true) ? " AND "+where : "");
		List<MInOutLine> list = new Query(ctx, Table_Name, whereClause, trxName)
									.setParameters(M_RMALine_ID)
									.list();
		return list.toArray (new MInOutLine[list.size()]);
	}	//	getOfRMALine

	/**
	 * 	Get Ship lines Of Order Line
	 *	@param ctx context
	 *	@param C_OrderLine_ID line
	 *  @param trxName transaction
	 *	@return array of receipt lines2
	 */
	public static MInOutLine[] get (Properties ctx, int C_OrderLine_ID, String trxName)
	{
		return getOfOrderLine(ctx, C_OrderLine_ID, null, trxName);
	}	//	get
	private static CCache<Integer,MInOutLine> s_cache	= new CCache<Integer,MInOutLine>(Table_Name, 40, 5);
	
	public static MInOutLine get (Properties ctx, int M_InOutLine_ID)
	{
		if (M_InOutLine_ID <= 0)
		{
			return null;
		}
		Integer key = new Integer (M_InOutLine_ID);
		MInOutLine retValue = (MInOutLine) s_cache.get (key);
		if (retValue != null)
		{
			return retValue;
		}
		retValue = new MInOutLine (ctx, M_InOutLine_ID, null);
		if (retValue.get_ID () != 0)
		{
			s_cache.put (key, retValue);
		}
		return retValue;
	}	//	get
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param M_InOutLine_ID id
	 *	@param trxName trx name
	 */
	public MInOutLine (Properties ctx, int M_InOutLine_ID, String trxName)
	{
		super (ctx, M_InOutLine_ID, trxName);
		if (M_InOutLine_ID == 0)
		{
		//	setLine (0);
		//	setM_Locator_ID (0);
		//	setC_UOM_ID (0);
		//	setM_Product_ID (0);
			setM_AttributeSetInstance_ID(0);
		//	setMovementQty (Env.ZERO);
			setConfirmedQty(Env.ZERO);
			setPickedQty(Env.ZERO);
			setScrappedQty(Env.ZERO);
			setTargetQty(Env.ZERO);
			setIsInvoiced (false);
			setIsDescription (false);
		}
	}	//	MInOutLine

	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 *  @param trxName transaction
	 */
	public MInOutLine (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MInOutLine

	/**
	 *  Parent Constructor
	 *  @param inout parent
	 */
	public MInOutLine (MInOut inout)
	{
		this (inout.getCtx(), 0, inout.get_TrxName());
		setClientOrg (inout);
		setM_InOut_ID (inout.getM_InOut_ID());
		setM_Warehouse_ID (inout.getM_Warehouse_ID());
		setC_Project_ID(inout.getC_Project_ID());
		m_parent = inout;
	}	//	MInOutLine

	/**	Product					*/
	private MProduct 		m_product = null;
	/** Warehouse				*/
	private int				m_M_Warehouse_ID = 0;
	/** Parent					*/
	private MInOut			m_parent = null;

	/**
	 * 	Get Parent
	 *	@return parent
	 */
	public MInOut getParent()
	{
		if (m_parent == null)
			m_parent = new MInOut (getCtx(), getM_InOut_ID(), get_TrxName());
		return m_parent;
	}	//	getParent

	/**
	 * 	Set Order Line.
	 * 	Does not set Quantity!
	 *	@param oLine order line
	 *	@param M_Locator_ID locator
	 * 	@param Qty used only to find suitable locator
	 */
	public void setOrderLine (MOrderLine oLine, int M_Locator_ID, BigDecimal Qty)
	{
		setC_OrderLine_ID(oLine.getC_OrderLine_ID());
		setLine(oLine.getLine());
		setC_UOM_ID(oLine.getC_UOM_ID());
		MProduct product = oLine.getProduct();
		if (product == null)
		{
			set_ValueNoCheck("M_Product_ID", null);
			set_ValueNoCheck("M_AttributeSetInstance_ID", null);
			set_ValueNoCheck("M_Locator_ID", null);
		}
		else
		{
			setM_Product_ID(oLine.getM_Product_ID());
			setM_AttributeSetInstance_ID(oLine.getM_AttributeSetInstance_ID());
			//
			if (product.isItem())
			{
				if (M_Locator_ID == 0)
					setM_Locator_ID(Qty);	//	requires warehouse, product, asi
				else
					setM_Locator_ID(M_Locator_ID);
			}
			else
				set_ValueNoCheck("M_Locator_ID", null);
		}
		setC_Charge_ID(oLine.getC_Charge_ID());
		setDescription(oLine.getDescription());
		setIsDescription(oLine.isDescription());
		//
		setC_Project_ID(oLine.getC_Project_ID());
		setC_ProjectPhase_ID(oLine.getC_ProjectPhase_ID());
		setC_ProjectTask_ID(oLine.getC_ProjectTask_ID());
		setC_Activity_ID(oLine.getC_Activity_ID());
		setC_Campaign_ID(oLine.getC_Campaign_ID());
		setAD_OrgTrx_ID(oLine.getAD_OrgTrx_ID());
		setUser1_ID(oLine.getUser1_ID());
		setUser2_ID(oLine.getUser2_ID());
	}	//	setOrderLine

	/**
	 * 	Set Invoice Line.
	 * 	Does not set Quantity!
	 *	@param iLine invoice line
	 *	@param M_Locator_ID locator
	 *	@param Qty qty only fo find suitable locator
	 */
	public void setInvoiceLine (MInvoiceLine iLine, int M_Locator_ID, BigDecimal Qty)
	{
		setC_OrderLine_ID(iLine.getC_OrderLine_ID());
		setLine(iLine.getLine());
		setC_UOM_ID(iLine.getC_UOM_ID());
		int M_Product_ID = iLine.getM_Product_ID();
		if (M_Product_ID == 0)
		{
			set_ValueNoCheck("M_Product_ID", null);
			set_ValueNoCheck("M_Locator_ID", null);
			set_ValueNoCheck("M_AttributeSetInstance_ID", null);
		}
		else
		{
			setM_Product_ID(M_Product_ID);
			setM_AttributeSetInstance_ID(iLine.getM_AttributeSetInstance_ID());
			if (M_Locator_ID == 0)
				setM_Locator_ID(Qty);	//	requires warehouse, product, asi
			else
				setM_Locator_ID(M_Locator_ID);
		}
		setC_Charge_ID(iLine.getC_Charge_ID());
		setDescription(iLine.getDescription());
		setIsDescription(iLine.isDescription());
		//
		setC_Project_ID(iLine.getC_Project_ID());
		setC_ProjectPhase_ID(iLine.getC_ProjectPhase_ID());
		setC_ProjectTask_ID(iLine.getC_ProjectTask_ID());
		setC_Activity_ID(iLine.getC_Activity_ID());
		setC_Campaign_ID(iLine.getC_Campaign_ID());
		setAD_OrgTrx_ID(iLine.getAD_OrgTrx_ID());
		setUser1_ID(iLine.getUser1_ID());
		setUser2_ID(iLine.getUser2_ID());
	}	//	setInvoiceLine

	/**
	 * 	Get Warehouse
	 *	@return Returns the m_Warehouse_ID.
	 */
	public int getM_Warehouse_ID()
	{
		if (m_M_Warehouse_ID == 0)
			m_M_Warehouse_ID = getParent().getM_Warehouse_ID();
		return m_M_Warehouse_ID;
	}	//	getM_Warehouse_ID

	/**
	 * 	Set Warehouse
	 *	@param warehouse_ID The m_Warehouse_ID to set.
	 */
	public void setM_Warehouse_ID (int warehouse_ID)
	{
		m_M_Warehouse_ID = warehouse_ID;
	}	//	setM_Warehouse_ID

	/**
	 * 	Set M_Locator_ID
	 *	@param M_Locator_ID id
	 */
	@Override
	public void setM_Locator_ID (int M_Locator_ID)
	{
		if (M_Locator_ID < 0)
			throw new IllegalArgumentException ("M_Locator_ID is mandatory.");
		//	set to 0 explicitly to reset
		set_Value (COLUMNNAME_M_Locator_ID, new Integer(M_Locator_ID));
	}	//	setM_Locator_ID

	/**
	 * 	Set (default) Locator based on qty.
	 * 	@param Qty quantity
	 * 	Assumes Warehouse is set
	 */
	public void setM_Locator_ID(BigDecimal Qty)
	{
		//	Locator established
		if (getM_Locator_ID() != 0)
			return;
		//	No Product
		if (getM_Product_ID() == 0)
		{
			set_ValueNoCheck(COLUMNNAME_M_Locator_ID, null);
			return;
		}

		//	Get existing Location
		int M_Locator_ID = MStorage.getM_Locator_ID (getM_Warehouse_ID(),
				getM_Product_ID(), getM_AttributeSetInstance_ID(),
				Qty, get_TrxName());
		//	Get default Location
		if (M_Locator_ID == 0)
		{
			MWarehouse wh = MWarehouse.get(getCtx(), getM_Warehouse_ID());
			M_Locator_ID = wh.getDefaultLocator().getM_Locator_ID();
		}
		setM_Locator_ID(M_Locator_ID);
	}	//	setM_Locator_ID

	/**
	 * 	Set Movement/Movement Qty
	 *	@param Qty Entered/Movement Qty
	 */
	public void setQty (BigDecimal Qty)
	{
		setQtyEntered(Qty);
		setMovementQty(getQtyEntered());
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
	 * 	Set Movement Qty - enforce Product UOM
	 *	@param MovementQty
	 */
	public void setMovementQty (BigDecimal MovementQty)
	{
		MProduct product = getProduct();
		if (MovementQty != null && product != null)
		{
			int precision = product.getUOMPrecision();
			MovementQty = MovementQty.setScale(precision, BigDecimal.ROUND_HALF_UP);
		}
		super.setMovementQty(MovementQty);
	}	//	setMovementQty

	/**
	 * 	Get Product
	 *	@return product or null
	 */
	public MProduct getProduct()
	{
		if (m_product == null && getM_Product_ID() != 0)
			m_product = MProduct.get (getCtx(), getM_Product_ID());
		return m_product;
	}	//	getProduct

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
	 *	@param setUOM also set UOM from product
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
		if (M_Product_ID != 0)
			super.setM_Product_ID (M_Product_ID);
		super.setC_UOM_ID(C_UOM_ID);
		setM_AttributeSetInstance_ID(0);
		m_product = null;
	}	//	setM_Product_ID

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

	/**************************************************************************
	 * 	Before Save
	 *	@param newRecord new
	 *	@return save
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		log.fine("");
		if (newRecord && getParent().isComplete()) {
			log.saveError("ParentComplete", Msg.translate(getCtx(), "M_InOutLine"));
			return false;
		}
		// Locator is mandatory if no charge is defined - teo_sarca BF [ 2757978 ]
		if(getProduct() != null && MProduct.PRODUCTTYPE_Item.equals(getProduct().getProductType()))
		{
			if (getM_Locator_ID() <= 0 && getC_Charge_ID() <= 0)
			{
				throw new FillMandatoryException(COLUMNNAME_M_Locator_ID);
			}
		}

        //AB 15-09-2015 Stock Qty Get in InOutLine
        String query="select sum(qtyonhand) from m_storage where m_product_id="+getM_Product_ID()+" and m_locator_id="+getM_Locator_ID()+" and "
                + "ad_org_id=(select ad_org_id from c_orderline where c_orderline_id="+getC_OrderLine_ID()+")";
        BigDecimal StockQty=DB.getSQLValueBD(get_TrxName(), query);
        setSTOCKQTY(StockQty);

        //AB 15-07-2015  Check for Confirmation Made , Prevent Updation of Qty once Confirmation is Made.
		query="select TARGETQTY from M_INOUTLINECONFIRM where M_INOUTLINE_ID="+getM_InOutLine_ID();
		BigDecimal targetQty=DB.getSQLValueBD(get_TrxName(), query);

        //AB Commented beacuse of AMSAT Client needs of no Stock Check on Generate Shipment Form
/*		if(isSOTrx() && getQtyEntered().compareTo(getSTOCKQTY())>0 && getM_Product_ID()!=0)
		{
			log.saveError("Could Not Save - Error", "Qty cannot be greater than Stock Qty,  Please Correct the values!");
			return false;
		}*/
		
		if(targetQty!=null && (targetQty.compareTo(getQtyEntered())>0 ||targetQty.compareTo(getQtyEntered())<0) && getM_Product_ID()!=0)
		{
			log.saveError("Could Not Save - Error", "Qty cannot be updated once Confirmation is Created, Please Delete the confirmation First!");
			return false;
		}
		
		if(isSOTrx() && getBALANCEORDERQTY()==Env.ZERO)		//AB Set Balance Order Qty, for In Out Line 16-07-2015
		{
			MOrderLine oLine = new MOrderLine(getCtx(), getC_OrderLine_ID(), get_TrxName());
			setBALANCEORDERQTY(oLine.getQtyReserved());
		}
	
		if(getQtyEntered().compareTo(getBALANCEORDERQTY())>0 && getM_RMALine_ID()==0 && getM_Product_ID()!=0)			//AB 14-07-2015  Check for MR Qty , less than Balance Order Qty
		{
			log.saveError("Could Not Save - Error", "Qty Recieved cannot be greater than the Balance Qty i.e "+getBALANCEORDERQTY()+" , Please correct the Qty and try Again!");
			return false;
		}
		
		//	Get Line No
		if (getLine() == 0)
		{
			String sql = "SELECT COALESCE(MAX(Line),0)+10 FROM M_InOutLine WHERE M_InOut_ID=?";
			int ii = DB.getSQLValueEx (get_TrxName(), sql, getM_InOut_ID());
			setLine (ii);
		}
		//	UOM
		if (getC_UOM_ID() == 0)
			setC_UOM_ID (Env.getContextAsInt(getCtx(), "#C_UOM_ID"));
		if (getC_UOM_ID() == 0)
		{
			int C_UOM_ID = MUOM.getDefault_UOM_ID(getCtx());
			if (C_UOM_ID > 0)
				setC_UOM_ID (C_UOM_ID);
		}
		//	Qty Precision
		if (newRecord || is_ValueChanged("QtyEntered"))
			setQtyEntered(getQtyEntered());
		if (newRecord || is_ValueChanged("MovementQty"))
			setMovementQty(getMovementQty());

		//	Order/RMA Line
		if (getC_OrderLine_ID() == 0 && getM_RMALine_ID() == 0)
		{
			if (getParent().isSOTrx())
			{
				log.saveError("FillMandatory", Msg.translate(getCtx(), "C_Order_ID"));
				return false;
			}
		}
		//RMA Line set C_OrderLine_ID
		if(getM_RMALine_ID() > 0)
		{
			setC_OrderLine_ID(getM_RMALine().getM_InOutLine().getC_OrderLine_ID());
		}
		
		// Validate Locator/Warehouse - teo_sarca, BF [ 2784194 ]
		if (getM_Locator_ID() > 0)
		{
			MLocator locator = MLocator.get(getCtx(), getM_Locator_ID());
			if (getM_Warehouse_ID() != locator.getM_Warehouse_ID())
			{
				throw new WarehouseLocatorConflictException(
						MWarehouse.get(getCtx(), getM_Warehouse_ID()),
						locator,
						getLine());
			}
		}

	//	if (getC_Charge_ID() == 0 && getM_Product_ID() == 0)
	//		;

		/**	 Qty on instance ASI
		if (getM_AttributeSetInstance_ID() != 0)
		{
			MProduct product = getProduct();
			int M_AttributeSet_ID = product.getM_AttributeSet_ID();
			boolean isInstance = M_AttributeSet_ID != 0;
			if (isInstance)
			{
				MAttributeSet mas = MAttributeSet.get(getCtx(), M_AttributeSet_ID);
				isInstance = mas.isInstanceAttribute();
			}
			//	Max
			if (isInstance)
			{
				MStorage storage = MStorage.get(getCtx(), getM_Locator_ID(),
					getM_Product_ID(), getM_AttributeSetInstance_ID(), get_TrxName());
				if (storage != null)
				{
					BigDecimal qty = storage.getQtyOnHand();
					if (getMovementQty().compareTo(qty) > 0)
					{
						log.warning("Qty - Stock=" + qty + ", Movement=" + getMovementQty());
						log.saveError("QtyInsufficient", "=" + qty);
						return false;
					}
				}
			}
		}	/**/

		return true;
	}	//	beforeSave

	/**
	 * 	Before Delete
	 *	@return true if drafted
	 */
	protected boolean beforeDelete ()
	{
		if (getParent().getDocStatus().equals(MInOut.DOCSTATUS_Drafted))
			return true;
		log.saveError("Error", Msg.getMsg(getCtx(), "CannotDelete"));
		return false;
	}	//	beforeDelete

	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MInOutLine[").append (get_ID())
			.append(",M_Product_ID=").append(getM_Product_ID())
			.append(",QtyEntered=").append(getQtyEntered())
			.append(",MovementQty=").append(getMovementQty())
			.append(",M_AttributeSetInstance_ID=").append(getM_AttributeSetInstance_ID())
			.append ("]");
		return sb.toString ();
	}	//	toString

	/**
	 * 	Get Base value for Cost Distribution
	 *	@param CostDistribution cost Distribution
	 *	@return base number
	 */
	public BigDecimal getBase (String CostDistribution)
	{
		if (MLandedCost.LANDEDCOSTDISTRIBUTION_Costs.equals(CostDistribution))
		{
			MInvoiceLine m_il = MInvoiceLine.getOfInOutLine(this);
			
			if(m_il == null)
			{	
				StringBuilder whereClause = new StringBuilder(I_M_MatchPO.COLUMNNAME_C_OrderLine_ID).append("=? AND ");
				whereClause.append(I_M_MatchPO.COLUMNNAME_M_Product_ID).append("=? AND ");
				whereClause.append(I_M_MatchPO.COLUMNNAME_C_InvoiceLine_ID).append("<> 0");
				MMatchPO matchPO = new Query(this.getCtx(),I_M_MatchPO.Table_Name,whereClause.toString(),this.get_TrxName())
				.setParameters(this.getC_OrderLine_ID() , this.getM_Product_ID())
				.first();
				if(matchPO != null)
					m_il = matchPO.getInvoiceLine();
			}	
			
			if (m_il == null)
			{
				log.severe("No Invoice Line for: " + this.toString());
				return Env.ZERO;
			}
			return this.getMovementQty().multiply(m_il.getPriceActual());  // Actual delivery
		}
		else if (MLandedCost.LANDEDCOSTDISTRIBUTION_Line.equals(CostDistribution))
			return Env.ONE;
		else if (MLandedCost.LANDEDCOSTDISTRIBUTION_Quantity.equals(CostDistribution))
			return getMovementQty();
		else if (MLandedCost.LANDEDCOSTDISTRIBUTION_Volume.equals(CostDistribution))
		{
			MProduct product = getProduct();
			if (product == null)
			{
				log.severe("No Product");
				return Env.ZERO;
			}
			return getMovementQty().multiply(product.getVolume());
		}
		else if (MLandedCost.LANDEDCOSTDISTRIBUTION_Weight.equals(CostDistribution))
		{
			MProduct product = getProduct();
			if (product == null)
			{
				log.severe("No Product");
				return Env.ZERO;
			}
			return getMovementQty().multiply(product.getWeight());
		}
		//
		log.severe("Invalid Criteria: " + CostDistribution);
		return Env.ZERO;
	}	//	getBase

	public boolean sameOrderLineUOM()
	{
		if (getC_OrderLine_ID() <= 0)
			return false;

		MOrderLine oLine = new MOrderLine(getCtx(), getC_OrderLine_ID(), get_TrxName());

		if (oLine.getC_UOM_ID() != getC_UOM_ID())
			return false;

		// inout has orderline and both has the same UOM
		return true;
	}

	/**
	 * get the Price Actual 
	 * @return BigDecimal with the Price Actual
	 */
	public BigDecimal getPriceActual()
	{
			// FIXME: ancabradau: we need to implement a real solution that will cover all cases
			BigDecimal price = null;
			if (getC_OrderLine_ID() > 0)
			{	
						price = DB.getSQLValueBDEx(get_TrxName(),
							"SELECT currencyBase(ol.PriceActual,o.C_Currency_ID,o.DateAcct,o.AD_Client_ID,o.AD_Org_ID) AS price " +
						    " FROM C_OrderLine ol INNER JOIN C_Order o ON (o.C_Order_ID=ol.C_Order_ID) " +
						    " WHERE "+MOrderLine.COLUMNNAME_C_OrderLine_ID+"=?",
						getC_OrderLine_ID());
				
					if (price == null || price.signum() == 0)
						price = DB.getSQLValueBDEx(get_TrxName(),
							" SELECT currencyBase(ol.PriceActual,o.C_Currency_ID,o.DateAcct,o.AD_Client_ID,o.AD_Org_ID) AS price" + 
							" FROM M_MatchPO mpo LEFT JOIN C_OrderLine ol ON ( mpo.C_OrderLine_ID=ol.C_OrderLine_ID) " +
							" INNER JOIN C_Order o ON (o.C_Order_ID=ol.C_Order_ID) " + 		
							" WHERE  mpo."+MMatchPO.COLUMNNAME_M_InOutLine_ID+"=?", getM_InOutLine_ID());					
				
					if (price == null || price.signum() == 0)				
						price = DB.getSQLValueBDEx(get_TrxName(), 
							" SELECT currencyBase(il.PriceActual,i.C_Currency_ID,i.DateAcct,i.AD_Client_ID,i.AD_Org_ID) AS price " +
							" FROM M_MatchInv mi LEFT JOIN C_InvoiceLine il ON (il.C_InvoiceLine_ID=mi.C_InvoiceLine_ID) " +
							" INNER JOIN C_Invoice i ON (i.C_Invoice_ID=il.C_Invoice_ID) " +
							" WHERE  mi."+MMatchInv.COLUMNNAME_M_InOutLine_ID+"=?", getM_InOutLine_ID());		
			}
			if (getM_RMALine_ID() > 0)
			{
				price = DB.getSQLValueBDEx(get_TrxName(),
						"SELECT "+MRMALine.COLUMNNAME_Amt+" FROM "+MRMALine.Table_Name
						+" WHERE "+MRMALine.COLUMNNAME_M_RMALine_ID+"=?",
						getM_RMALine_ID());
			}	
			if (price == null)
			{
				//throw new AdempiereException("Shipment: PriceActual not found");
				price = Env.ZERO;
			}
			return price;
		}
	
	/**
	 * get if this document line is the Sales transaction
	 * @return boolean
	 */
	public boolean isSOTrx()
	{
		return getParent().isSOTrx();
	}

	@Override
	public Timestamp getDateAcct() {
		return getParent().getDateAcct();
	}


	public IDocumentLine getReversalDocumentLine() {
		return (IDocumentLine) getReversalLine();
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
		return getParent().getC_DocType_ID();
	}

}	//	MInOutLine
