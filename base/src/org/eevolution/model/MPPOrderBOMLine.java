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
 *****************************************************************************/
package org.eevolution.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MProduct;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *  PP Order BOM Line Model.
 *  
 *  @author Victor Perez www.e-evolution.com     
 *  @version $Id: MOrderLine.java,v 1.22 2004/03/22 07:15:03 vpj-cd Exp $
 * 
 * @author Teo Sarca, www.arhipac.ro
 */
public class MPPOrderBOMLine extends X_PP_Order_BOMLine
{
	private static final long serialVersionUID = 1L;


	/**
	 *  Default Constructor
	 *  @param ctx context
	 *  @param  C_OrderLine_ID  order line to load
	 */
	public MPPOrderBOMLine(Properties ctx, int PP_Order_BOMLine_ID,String trxName)
	{
		super (ctx, PP_Order_BOMLine_ID,trxName);  
		if (PP_Order_BOMLine_ID == 0)
		{
			setQtyDelivered(Env.ZERO);
			setQtyPost(Env.ZERO);
			setQtyReject(Env.ZERO);
			setQtyRequiered(Env.ZERO);
			setQtyReserved(Env.ZERO);
			setQtyScrap(Env.ZERO);
		}	
	}	//	PP_Order_BOMLine_ID


	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 */
	public MPPOrderBOMLine(Properties ctx, ResultSet rs,String trxName)
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
		
		this.setPP_Order_BOM_ID(PP_Order_BOM_ID);
		this.setPP_Order_ID(PP_Order_ID);
		this.setM_Warehouse_ID(M_Warehouse_ID);
		//
		this.setM_ChangeNotice_ID(bomLine.getM_ChangeNotice_ID());
		this.setDescription(bomLine.getDescription());
		this.setHelp(bomLine.getHelp());
		this.setAssay(bomLine.getAssay());
		this.setQtyBatch(bomLine.getQtyBatch());
		this.setQtyBOM(bomLine.getQtyBOM());
		this.setIsQtyPercentage(bomLine.isQtyPercentage());
		this.setComponentType(bomLine.getComponentType());
		this.setC_UOM_ID(bomLine.getC_UOM_ID());
		this.setForecast(bomLine.getForecast());
		this.setIsCritical(bomLine.isCritical());
		this.setIssueMethod(bomLine.getIssueMethod());
		this.setLeadTimeOffset(bomLine.getLeadTimeOffset());
		this.setM_AttributeSetInstance_ID(bomLine.getM_AttributeSetInstance_ID());
		this.setM_Product_ID(bomLine.getM_Product_ID());
		this.setScrap(bomLine.getScrap());
		this.setValidFrom(bomLine.getValidFrom());
		this.setValidTo(bomLine.getValidTo());
		this.setBackflushGroup(bomLine.getBackflushGroup());		
	}
	
	private MPPOrder m_parent = null;
	private MProduct m_product = null;
	/** Qty used for exploding this BOM Line */
	private BigDecimal m_qtyToExplode = null;

	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		//	Get Line No
		if (getLine() == 0)
		{
			String sql = "SELECT COALESCE(MAX("+COLUMNNAME_Line+"),0)+10 FROM "+Table_Name
							+" WHERE "+COLUMNNAME_PP_Order_ID+"=?";
			int ii = DB.getSQLValue (get_TrxName(), sql, getPP_Order_ID());
			setLine (ii);
		}

		// If Phantom, we need to explode this line (see afterSave):
		if(newRecord && COMPONENTTYPE_Phantom.equals(getComponentType()))
		{
			m_qtyToExplode = getQtyRequiered();
			setQtyRequiered(Env.ZERO);
		}
		
		
		return true;
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success)
	{
		if (!success)
			return false;
		log.fine(" Parent Product" +  getM_Product_ID() + " getQtyBatch" + getQtyBatch() + " getQtyRequiered"  + getQtyRequiered() + " QtyScrap" + getQtyScrap());
		// 
		if(m_qtyToExplode != null)
		{
			MProduct parent = MProduct.get(getCtx(), getM_Product_ID());
			int PP_Product_BOM_ID = MPPProductBOM.getBOMSearchKey(getCtx(), parent);
			if (PP_Product_BOM_ID <= 0)
				return true;

			MPPProductBOM bom = MPPProductBOM.get(getCtx(), PP_Product_BOM_ID);
			if (bom != null)
			{
				MPPProductBOMLine[] PP_Product_BOMline = bom.getLines();
				for(int i = 0 ; i < PP_Product_BOMline.length ; i++ )
				{
					MPPOrderBOMLine PP_Order_BOMLine = new MPPOrderBOMLine(PP_Product_BOMline[i],
																getPP_Order_ID(), getPP_Order_BOM_ID(),
																getM_Warehouse_ID(),
																get_TrxName());
					PP_Order_BOMLine.setQtyOrdered(m_qtyToExplode);
					PP_Order_BOMLine.saveEx();
				}
			}
			m_qtyToExplode = null;
		}
		return true;

	}

	/**
	 * 	Get Product
	 *	@return product or null
	 */
	public MProduct getProduct()
	{
		if (m_product == null && getM_Product_ID() != 0)
			m_product = new MProduct (getCtx(), getM_Product_ID() ,get_TrxName());
		return m_product;
	}

	/**
	 * 	Get Parent
	 *	@return parent
	 */
	public MPPOrder getParent()
	{
		if (m_parent == null)
			m_parent = new MPPOrder(getCtx(), getPP_Order_ID(), get_TrxName());
		return m_parent;
	}	//	getParent

	public void setQtyOrdered(BigDecimal QtyOrdered)
	{
		// Set Qty Required
		if (this.isQtyPercentage())
		{
			BigDecimal qty = this.getQtyBatch().multiply(QtyOrdered);
			if (this.getComponentType().equals(COMPONENTTYPE_Component)
					|| this.getComponentType().equals(COMPONENTTYPE_Phantom))
			{
				this.setQtyRequiered(qty.divide(Env.ONEHUNDRED, 8, BigDecimal.ROUND_UP));
			}
			else if (this.getComponentType().equals(COMPONENTTYPE_Packing))
			{
				this.setQtyRequiered(qty.divide(Env.ONEHUNDRED, 8, BigDecimal.ROUND_UP));
			}
			else if (this.getComponentType().equals(COMPONENTTYPE_Tools))
			{
				this.setQtyRequiered(this.getQtyBOM());
			}
			else
			{
				throw new AdempiereException("@NotSupported@ @ComponentType@ "+this.getComponentType());
			}
		}
		else
		{
			if (this.getComponentType().equals(COMPONENTTYPE_Component)
					|| this.getComponentType().equals(COMPONENTTYPE_Phantom))
			{
				this.setQtyRequiered(this.getQtyBOM().multiply(QtyOrdered));
			}
			else if (this.getComponentType().equals(COMPONENTTYPE_Packing))
			{
				this.setQtyRequiered(this.getQtyBOM().multiply(QtyOrdered));
			}
			else if (this.getComponentType().equals(COMPONENTTYPE_Tools))
			{
				this.setQtyRequiered(this.getQtyBOM());
			}
			else
			{
				throw new AdempiereException("@NotSupported@ @ComponentType@ "+this.getComponentType());
			}
		}
		
		// Set Scrap of Component
		BigDecimal Scrap = this.getScrap();
		if (Scrap.signum() != 0) {
			Scrap = Scrap.divide(Env.ONEHUNDRED, 8, BigDecimal.ROUND_UP);
			this.setQtyRequiered(this.getQtyRequiered().divide(Env.ONE.subtract(Scrap), 8, BigDecimal.ROUND_HALF_UP));
		}
	}
}
