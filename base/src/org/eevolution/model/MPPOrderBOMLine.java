/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *                 Teo Sarca, www.arhipac.ro                                  *
 *****************************************************************************/
package org.eevolution.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.compiere.model.MLocator;
import org.compiere.model.MProduct;
import org.compiere.model.MStorage;
import org.compiere.model.MUOM;
import org.compiere.model.MUOMConversion;
import org.compiere.model.MWarehouse;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * PP Order BOM Line Model.
 *  
 * @author Victor Perez www.e-evolution.com     
 * @author Teo Sarca, www.arhipac.ro
 */
public class MPPOrderBOMLine extends X_PP_Order_BOMLine
{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -201479481618586562L;

	public static MPPOrderBOMLine forM_Product_ID(Properties ctx, int PP_Order_ID, int M_Product_ID, String trxName)
	{
		//TODO: vpj-cd What happen when a product it more the time in Order
		final String whereClause = COLUMNNAME_PP_Order_ID+"=? AND "+COLUMNNAME_M_Product_ID+"=?";
		return new Query(ctx, Table_Name, whereClause, trxName)
			.setParameters(new Object[]{PP_Order_ID, M_Product_ID})
			.firstOnly();
	}
	
	public MPPOrderBOMLine(Properties ctx, int PP_Order_BOMLine_ID, String trxName)
	{
		super (ctx, PP_Order_BOMLine_ID, trxName);  
		if (PP_Order_BOMLine_ID == 0)
		{
			setDefault();
		}	
	}	//	PP_Order_BOMLine_ID


	/**
	 * Constructor
	 * @param ctx
	 * @param rs
	 * @param trxName
     */
	public MPPOrderBOMLine(Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs,trxName);
	}	//	MOrderLine
	
	/**
	 * Peer constructor
	 * @param bomLine
	 * @param PP_Order_ID
	 * @param PP_Order_BOM_ID
	 * @param M_Warehouse_ID
	 * @param trxName
	 */
	public MPPOrderBOMLine(MPPProductBOMLine bomLine,
			int PP_Order_ID, int PP_Order_BOM_ID, int M_Warehouse_ID,
			String trxName)
	{
		this(bomLine.getCtx(), 0, trxName);
		
		setPP_Order_BOM_ID(PP_Order_BOM_ID);
		setPP_Order_ID(PP_Order_ID);
		setM_Warehouse_ID(M_Warehouse_ID);
		//
		setM_ChangeNotice_ID(bomLine.getM_ChangeNotice_ID());
		setDescription(bomLine.getDescription());
		setHelp(bomLine.getHelp());
		setAssay(bomLine.getAssay());
		setQtyBatch(bomLine.getQtyBatch());
		setQtyBOM(bomLine.getQtyBOM());
		setIsQtyPercentage(bomLine.isQtyPercentage());
		setComponentType(bomLine.getComponentType());
		setC_UOM_ID(bomLine.getC_UOM_ID());
		setForecast(bomLine.getForecast());
		setIsCritical(bomLine.isCritical());
		setIssueMethod(bomLine.getIssueMethod());
		setLeadTimeOffset(bomLine.getLeadTimeOffset());
		setM_AttributeSetInstance_ID(bomLine.getM_AttributeSetInstance_ID());
		setM_Product_ID(bomLine.getM_Product_ID());
		setScrap(bomLine.getScrap());
		setValidFrom(bomLine.getValidFrom());
		setValidTo(bomLine.getValidTo());
		setBackflushGroup(bomLine.getBackflushGroup());		
	}

	/**
	 * Parent (PP_Order)
	 */
	private MPPOrder m_parent = null;
	
	/**
	 * Do we need to explode this BOM line. Set when ComponentType is Phantom and m_qtyRequiredPhantom != null.
	 * If set, the line is exploded on after save
	 */
	private boolean m_isExplodePhantom = false;
	/**
	 * Qty used for exploding this BOM Line.
	 */
	private BigDecimal m_qtyRequiredPhantom = null;
	
	@Override
	protected boolean beforeSave(boolean newRecord)
	{
		// Victor Perez: The best practice in this case you do should change the component you need
		// adding a new line in Order BOM Line with new component so do not is right
		// delete or change a component because this information is use to calculate
		// the variances cost ( https://sourceforge.net/tracker/?func=detail&atid=934929&aid=2724579&group_id=176962 )
		if (!isActive())
		{
			throw new AdempiereException("De-Activating an BOM Line is not allowed"); // TODO: translate 
		}
		if (!newRecord && is_ValueChanged(COLUMNNAME_M_Product_ID))
		{
			throw new AdempiereException("Changing Product is not allowed"); // TODO: translate 
		}
		
		//	Get Line No
		if (getLine() == 0)
		{
			String sql = "SELECT COALESCE(MAX("+COLUMNNAME_Line+"),0)+10 FROM "+Table_Name
							+" WHERE "+COLUMNNAME_PP_Order_ID+"=?";
			int ii = DB.getSQLValueEx (get_TrxName(), sql, getPP_Order_ID());
			setLine (ii);
		}

		// If Phantom, we need to explode this line (see afterSave):
		if(newRecord && COMPONENTTYPE_Phantom.equals(getComponentType()))
		{
			m_qtyRequiredPhantom = getQtyRequired();
			m_isExplodePhantom = true;
			setQtyRequired(Env.ZERO);
		}
		
		if (newRecord
				|| is_ValueChanged(COLUMNNAME_C_UOM_ID)
				|| is_ValueChanged(COLUMNNAME_QtyEntered)
				|| is_ValueChanged(COLUMNNAME_QtyRequired)
			)
		{
			int precision = MUOM.getPrecision(getCtx(), getC_UOM_ID());
			setQtyEntered(getQtyEntered().setScale(precision, RoundingMode.UP));
			setQtyRequired(getQtyRequired().setScale(precision, RoundingMode.UP));
		}
		
		if( is_ValueChanged(MPPOrderBOMLine.COLUMNNAME_QtyDelivered)
				|| is_ValueChanged(MPPOrderBOMLine.COLUMNNAME_QtyRequired))
		{	
			reserveStock();
		}
		
		return true;
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success)
	{
		if (!success)
			return false;
		explodePhantom();
		return true;
	}
	
	@Override
	protected boolean beforeDelete()
	{
		// Release Reservation
		setQtyRequired(Env.ZERO);
		reserveStock();
		return true;
	}

	/**
	 * Explode Phantom Items
	 * TODO: check if BOM and BOM Lines are valid
	 */
	private void explodePhantom()
	{
		if(m_isExplodePhantom && m_qtyRequiredPhantom != null)
		{
			MProduct parent = MProduct.get(getCtx(), getM_Product_ID());
			int PP_Product_BOM_ID = MPPProductBOM.getBOMSearchKey(parent);
			if (PP_Product_BOM_ID <= 0)
			{
				return;
			}
			MPPProductBOM bom = MPPProductBOM.get(getCtx(), PP_Product_BOM_ID);
			if (bom != null)
			{
				for(MPPProductBOMLine PP_Product_BOMline : bom.getLines())
				{
					MPPOrderBOMLine PP_Order_BOMLine = new MPPOrderBOMLine(PP_Product_BOMline,
																getPP_Order_ID(), getPP_Order_BOM_ID(),
																getM_Warehouse_ID(),
																get_TrxName());
					PP_Order_BOMLine.setAD_Org_ID(getAD_Org_ID());
					PP_Order_BOMLine.setQtyOrdered(m_qtyRequiredPhantom);
					PP_Order_BOMLine.saveEx();
				}
			}
			m_isExplodePhantom = false;
		}
	}

	@Override
	public MProduct getM_Product()
	{
		return MProduct.get(getCtx(), getM_Product_ID());
	}

	@Override
	public MUOM getC_UOM()
	{
		return MUOM.get(getCtx(), getC_UOM_ID());
	}
	
	@Override
	public MWarehouse getM_Warehouse()
	{
		return MWarehouse.get(getCtx(), getM_Warehouse_ID());
	}
	
	/**
	 * Qty Required for a Phantom Component. The Qty that will be exploded after line is saved.
	 * @return
	 */
	public BigDecimal getQtyRequiredPhantom()
	{
		return m_qtyRequiredPhantom != null ? m_qtyRequiredPhantom : Env.ZERO;
	}

	/**
	 * 	Get Parent
	 *	@return PP_Order
	 */
	public MPPOrder getParent()
	{
		int id = getPP_Order_ID();
		if (id <= 0)
		{
			m_parent = null;
			return null;
		}
		if (m_parent == null || m_parent.get_ID() != id)
		{
			m_parent = new MPPOrder(getCtx(), id, get_TrxName());
		}
		return m_parent;
	}	//	getParent
	
	/**
	 * @return UOM precision
	 */
	public int getPrecision()
	{
		return MUOM.getPrecision(getCtx(), getC_UOM_ID());
	}
	
	/**
	 * Return Unified BOM Qty Multiplier 
	 * @return If is percentage then QtyBatch / 100 will be returned, else QtyBOM. 
	 */
	public BigDecimal getQtyMultiplier()
	{
		BigDecimal qty;
		if (isQtyPercentage())
		{
			qty = getQtyBatch().divide(Env.ONEHUNDRED, 8, RoundingMode.HALF_UP);
		}
		else
		{
			qty = getQtyBOM();
		}
		return qty;
	}


	public void setQtyOrdered(BigDecimal QtyOrdered)
	{
		BigDecimal multiplier = getQtyMultiplier();	
		BigDecimal qty = QtyOrdered.multiply(multiplier).setScale(8, RoundingMode.UP);
		
		if (isComponentType(COMPONENTTYPE_Component,COMPONENTTYPE_Phantom
							,COMPONENTTYPE_By_Product
							,COMPONENTTYPE_Co_Product))
		{
			BigDecimal qtyrequired = qty;
			if (getM_Product().getC_UOM_ID() != getC_UOM_ID())
			{
				BigDecimal rate = MUOMConversion.getProductRateFrom(getCtx(), getM_Product_ID(), getC_UOM_ID());
				if (rate == null)
					throw new AdempiereException("@PP_Product_BOMLine_ID@ @C_UOM_Conversion_ID@ @NotFound@  @M_Product_ID@ "
							+ getM_Product().getName()
							+ " @C_UOM_To_ID@ " + getC_UOM().getName());


				qtyrequired = qty.multiply(rate);
			}
			setQtyRequired(qtyrequired);
			setQtyEntered(qty);
		}
		else if (isComponentType(COMPONENTTYPE_Tools))
		{
			setQtyRequired(multiplier);
			setQtyEntered(multiplier);
		}
		else if (isComponentType(COMPONENTTYPE_Packing))
		{
			setQtyRequired(multiplier.multiply(getParent().getQtyBatchs()));
			setQtyEntered(multiplier.multiply(getParent().getQtyBatchs()));
		}
		else
		{
			throw new AdempiereException("@NotSupported@ @ComponentType@ "+getComponentType());
		}
		//
		// Set Scrap of Component
		BigDecimal qtyScrap = getScrap();
		if (qtyScrap.signum() != 0)
		{
			qtyScrap = qtyScrap.divide(Env.ONEHUNDRED, 8, BigDecimal.ROUND_UP);
			setQtyRequired(getQtyRequired().divide(Env.ONE.subtract(qtyScrap), 8, BigDecimal.ROUND_HALF_UP));
			setQtyEntered(getQtyEntered().divide(Env.ONE.subtract(qtyScrap), 8, BigDecimal.ROUND_HALF_UP));
		}
	}
	
	@Override
	public void setQtyRequired (BigDecimal QtyRequired)
	{
		if (QtyRequired != null && getC_UOM_ID() != 0)
		{
			int precision = getPrecision();
			QtyRequired = QtyRequired.setScale(precision, RoundingMode.HALF_UP);
		}
		super.setQtyRequired (QtyRequired);
	}	//	setQtyRequired
	
	@Override
	public void setQtyReserved (BigDecimal QtyReserved)
	{
		if (QtyReserved != null && getC_UOM_ID() != 0)
		{
			int precision = getPrecision();
			QtyReserved = QtyReserved.setScale(precision, RoundingMode.HALF_UP);
		}
		super.setQtyReserved (QtyReserved);
	}	//	setQtyReserved
	
	/**
	 * @return Qty Open (Entered - Delivered)
	 */
	public BigDecimal getQtyOpen()
	{
		return getQtyEntered().subtract(getQtyDelivered()); 
	}
	
	/** Storage Qty On Hand */
	private BigDecimal m_qtyOnHand = null;
	/** Storage Qty Available */
	private BigDecimal m_qtyAvailable = null;

	/**
	 * Load Storage Info
	 * @param reload
	 */
	private void loadStorage(boolean reload)
	{
		if (!reload && m_qtyOnHand != null && m_qtyAvailable != null)
		{
			return;
		}
		//
		final String sql = "SELECT "
							+" bomQtyAvailable("+COLUMNNAME_M_Product_ID+", "+COLUMNNAME_M_Warehouse_ID+", 0)"
							+",bomQtyOnHand("+COLUMNNAME_M_Product_ID+", "+COLUMNNAME_M_Warehouse_ID+", 0)"
							+" FROM "+Table_Name
							+" WHERE "+COLUMNNAME_PP_Order_BOMLine_ID+"=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			DB.setParameters(pstmt, new Object[]{get_ID()});
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				m_qtyAvailable = rs.getBigDecimal(1);
				m_qtyOnHand = rs.getBigDecimal(2);
			}
		}
		catch (SQLException e)
		{
			throw new DBException(e, sql);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
	}

	/**
	 * @return storage Available Qty
	 */
	public BigDecimal getQtyAvailable()
	{
		loadStorage(false);
		return m_qtyAvailable;
	}
	
	/**
	 * @return recorded Qty Usage Variance so far
	 */
	public BigDecimal getQtyVariance()
	{
		final String whereClause = I_PP_Cost_Collector.COLUMNNAME_PP_Order_BOMLine_ID+"=?"
		+" AND "+I_PP_Cost_Collector.COLUMNNAME_PP_Order_ID+"=?"
		+" AND "+I_PP_Cost_Collector.COLUMNNAME_DocStatus+" IN (?,?)"
		+" AND "+I_PP_Cost_Collector.COLUMNNAME_CostCollectorType+"=?"
		;
		BigDecimal qtyUsageVariance = new Query(getCtx(), I_PP_Cost_Collector.Table_Name, whereClause, get_TrxName())
		.setParameters(new Object[]{
				getPP_Order_BOMLine_ID(),
				getPP_Order_ID(),
				X_PP_Cost_Collector.DOCSTATUS_Completed,
				X_PP_Cost_Collector.DOCSTATUS_Closed,
				X_PP_Cost_Collector.COSTCOLLECTORTYPE_UsegeVariance
		})
		.sum(I_PP_Cost_Collector.COLUMNNAME_MovementQty);
		//
		return qtyUsageVariance;
	}

	/**
	 * @return recorded Qty Method Change Variance so far
	 */
	public BigDecimal getQtyMethodChangeVariance()
	{
		final String whereClause = I_PP_Cost_Collector.COLUMNNAME_PP_Order_BOMLine_ID+"=?"
		+" AND "+I_PP_Cost_Collector.COLUMNNAME_PP_Order_ID+"=?"
		+" AND "+I_PP_Cost_Collector.COLUMNNAME_DocStatus+" IN (?,?)"
		+" AND "+I_PP_Cost_Collector.COLUMNNAME_CostCollectorType+"=?"
		;
		BigDecimal qtyMethodChangeVariance = new Query(getCtx(), I_PP_Cost_Collector.Table_Name, whereClause, get_TrxName())
		.setParameters(new Object[]{
				getPP_Order_BOMLine_ID(),
				getPP_Order_ID(),
				X_PP_Cost_Collector.DOCSTATUS_Completed,
				X_PP_Cost_Collector.DOCSTATUS_Closed,
				X_PP_Cost_Collector.COSTCOLLECTORTYPE_MethodChangeVariance
		})
		.sum(I_PP_Cost_Collector.COLUMNNAME_MovementQty);
		//
		return qtyMethodChangeVariance;
	}
	/**
	 * @return storage Qty On Hand
	 */
	public BigDecimal getQtyOnHand()
	{
		loadStorage(false);
		return m_qtyOnHand;
	}
	
	/**
	 * @param componentTypes one or more component types
	 * @return true of Component Type is any of following types
	 */
	public boolean isComponentType(String ... componentTypes)
	{
		String currentType = getComponentType();
		for (String type : componentTypes)
		{
			if (currentType.equals(type))
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean isCoProduct()
	{
		return isComponentType(COMPONENTTYPE_Co_Product);
	}
	
	public boolean isByProduct()
	{
		return isComponentType(COMPONENTTYPE_By_Product);
	}
	
	public boolean isComponent()
	{
		return isComponentType(COMPONENTTYPE_Component, COMPONENTTYPE_Packing);
	}
	
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
	 * Set default values
	 */
	private void setDefault()
	{
		setDescription("");
		setQtyDelivered(Env.ZERO);
		setQtyPost(Env.ZERO);
		setQtyReject(Env.ZERO);
		setQtyRequired(Env.ZERO);
		setQtyReserved(Env.ZERO);
		setQtyScrap(Env.ZERO);
	}
	
	/**
	 * Reserve Inventory for this BOM Line
	 */
	protected void reserveStock()
	{
		final int header_M_Warehouse_ID = getParent().getM_Warehouse_ID();

		//	Check/set WH/Org
		if (header_M_Warehouse_ID != 0) //	enforce WH
		{
			if (header_M_Warehouse_ID != getM_Warehouse_ID())
				setM_Warehouse_ID(header_M_Warehouse_ID);
			if (getAD_Org_ID() != getAD_Org_ID())
				setAD_Org_ID(getAD_Org_ID());
		}
		//
		final BigDecimal target = getQtyRequired();
		final BigDecimal difference = target.subtract(getQtyReserved()).subtract(getQtyDelivered());
		log.fine("Line=" + getLine() + " - Target=" + target + ",Difference=" + difference + " - Requiered=" + getQtyRequired()
				+ ",Reserved=" + getQtyReserved() + ",Delivered=" + getQtyDelivered());
		if (difference.signum() == 0)
		{
			return;
		}

		//	Check Product - Stocked and Item
		MProduct product = getM_Product();
		if (!product.isStocked())
		{
			return;
		}
		BigDecimal reserved = difference;
		int M_Locator_ID = getM_Locator_ID(reserved);
		//	Update Storage
		if (!MStorage.add(getCtx(), getM_Warehouse_ID(), M_Locator_ID,
				getM_Product_ID(), getM_AttributeSetInstance_ID(),
				getM_AttributeSetInstance_ID(), Env.ZERO, reserved, Env.ZERO, get_TrxName()))
		{
			throw new AdempiereException();
		}
		//	update line
		setQtyReserved(getQtyReserved().add(difference));
	} //	reserveStock
	
	/**
	 * @param qty
	 * @return Storage locator for current product/asi/warehouse and qty
	 * @see MStorage#getM_Locator_ID(int, int, int, BigDecimal, String)
	 */
	private int getM_Locator_ID(BigDecimal qty)
	{
		int M_Locator_ID = 0;
		int M_ASI_ID = getM_AttributeSetInstance_ID();
		// Get existing Locator
		if (M_ASI_ID != 0)
		{
			M_Locator_ID = MStorage.getM_Locator_ID(getM_Warehouse_ID(), getM_Product_ID(), M_ASI_ID, qty, get_TrxName());
		}
		// Get Default
		if (M_Locator_ID == 0)
		{
			M_Locator_ID = getM_Locator_ID();
		}
		// Get Default Locator for Warehouse - teo_sarca [ 2724743 ]
		if (M_Locator_ID == 0)
		{
			MLocator locator = MWarehouse.get(getCtx(), getM_Warehouse_ID()).getDefaultLocator();
			if (locator != null)
			{
				M_Locator_ID = locator.get_ID();
			}
		}
		return M_Locator_ID;
	}
	
	@Override
	public String toString()
	{
		return getClass().getSimpleName() +"["+get_ID()
				+", Product="+getM_Product_ID()
				+", ComponentType="+getComponentType()
				+",QtyBatch="+getQtyBatch()
				+",QtyRequired="+getQtyRequired()
				+",QtyScrap="+getQtyScrap()
				+"]";
	}
}
