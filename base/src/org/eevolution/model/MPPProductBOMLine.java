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
import java.sql.Timestamp;
import java.util.Hashtable;
import java.util.Properties;

import javax.swing.tree.DefaultMutableTreeNode;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.compiere.model.MProduct;
import org.compiere.model.MUOM;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *  PP Product BOM Line Model.
 * 	<code>
 * 			MPPProductBOMLine l = new MPPProductBOMLine(bom);
 * 			l.setM_Product_ID(wbl.getM_Product_ID());
 * 			l.setQty(wbl.getQuantity());
 * 			l.saveEx();
 *	</code>
 *
 * @author Victor Perez www.e-evolution.com     
 * @author Teo Sarca, www.arhipac.ro
 */
public class MPPProductBOMLine extends X_PP_Product_BOMLine
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6729103151164195906L;

	/**
	 *  Default Constructor
	 *  @param ctx context
	 *  @param PP_Product_BOMLine  BOM line to load
	 *  @param Transaction Line
	 */
	public MPPProductBOMLine(Properties ctx, int PP_Product_BOMLine, String trxName)
	{
		super(ctx, PP_Product_BOMLine, trxName);
	} //	MPPProductBOMLine

	/**
	 *  Parent Constructor.
	 *  @param  bom parent BOM
	 */
	public MPPProductBOMLine(MPPProductBOM bom)
	{
		super(bom.getCtx(), 0, bom.get_TableName());
		if (bom.get_ID() <= 0)
			throw new IllegalArgumentException("Header not saved");
		setPP_Product_BOM_ID(bom.getPP_Product_BOM_ID()); //	parent
	}

	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 */
	public MPPProductBOMLine(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	} //	 MPPProductBOMLine

	/**
	 * Calculate Low Level of a Product
	 * @param ID Product
	 * @return int low level
	 */
	public int getLowLevel()
	{
		return new ProductLowLevelCalculator(getCtx(), get_TrxName()).getLowLevel(getM_Product_ID());
	}

	/**
	 * Calculate Low Level of a Product
	 * @param ID Product
	 * @return int low level
	 */
	public static int getLowLevel(Properties ctx, int M_Product_ID, String trxName)
	{
		return new ProductLowLevelCalculator(ctx, trxName).getLowLevel(M_Product_ID);
	}

	
	@Override
	protected boolean beforeSave(boolean newRecord)
	{
		//
		// For Co/By Products, Qty should be always negative:
		if (isCoProduct() && getQty(false).signum() >= 0)
		{
			throw new AdempiereException("@Qty@ > 0");
		}
		//
		// Update Line#
		if (getLine() <= 0)
		{
			final String sql = "SELECT COALESCE(MAX("+COLUMNNAME_Line+"),0) + 10 FROM "+Table_Name
								+" WHERE "+COLUMNNAME_PP_Product_BOM_ID+"=?";
			int line = DB.getSQLValueEx(get_TrxName(), sql, getPP_Product_BOM_ID());
			setLine(line);
		}
		
		return true;
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success)
	{
		if (!success)
			return false;

		int lowlevel = getLowLevel();
		MProduct product = new MProduct(getCtx(), getM_Product_ID(), get_TrxName());
		product.setLowLevel(lowlevel); //update lowlevel
		product.saveEx();
		
		return true;
	}
	
	public boolean isValidFromTo(Timestamp date)
	{
		Timestamp validFrom = getValidFrom();
		Timestamp validTo = getValidTo();
		
		if (validFrom != null && date.before(validFrom))
			return false;
		if (validTo != null && date.after(validTo))
			return false;
		return true;
	}
	
	public boolean isByProduct()
	{
		String componentType = getComponentType();
		return COMPONENTTYPE_By_Product.equals(componentType);
	}
	
	public boolean isCoProduct()
	{
		String componentType = getComponentType();
		return COMPONENTTYPE_Co_Product.equals(componentType);
	}
	
	/**
	 * Return absolute (unified) quantity value.
	 * If IsQtyPercentage then QtyBatch / 100 will be returned.
	 * Else QtyBOM will be returned.
	 * @param includeScrapQty if true, scrap qty will be used for calculating qty 
	 * @return qty
	 */
	public BigDecimal getQty(boolean includeScrapQty)
	{
		int precision = getPrecision();
		BigDecimal qty;
		if (isQtyPercentage())
		{
			precision += 2;
			qty = getQtyBatch().divide(Env.ONEHUNDRED, precision, RoundingMode.HALF_UP);
		}
		else
		{
			qty = getQtyBOM();
		}
		//
		if (includeScrapQty)
		{
			BigDecimal scrapDec = getScrap().divide(Env.ONEHUNDRED, 12, BigDecimal.ROUND_UP);
			qty = qty.divide(Env.ONE.subtract(scrapDec), precision, BigDecimal.ROUND_HALF_UP);
		}
		//
		if (qty.scale() > precision)
		{
			qty = qty.setScale(precision, RoundingMode.HALF_UP);
		}
		//
		return qty;
	}

	/**
	 * Like {@link #getQty(boolean)}, includeScrapQty = false
	 */
	public BigDecimal getQty()
	{
		return getQty(false);
	}
	
	/**
	 * @return UOM precision
	 */
	public int getPrecision()
	{
		return MUOM.getPrecision(getCtx(), getC_UOM_ID());
	}
	
	/**
	 * @param fallback use QtyBOM/QtyPercentage if CostAllocationPerc is zero 
	 * @return co-product cost allocation percent (i.e. -1/qty)
	 */
	public BigDecimal getCostAllocationPerc(boolean fallback)
	{
		BigDecimal allocationPercent = super.getCostAllocationPerc();
		if (allocationPercent.signum() != 0)
			return allocationPercent;
		//
		// Fallback and try to calculate it from Qty
		if (fallback)
		{
			BigDecimal qty = getQty(false).negate();
			if (qty.signum() != 0)
			{
				allocationPercent = Env.ONE.divide(qty, 4, RoundingMode.HALF_UP);
			}
		}
		return allocationPercent;
	}
}

class ProductLowLevelCalculator
{
	private Hashtable<Integer, Integer> tableproduct = new Hashtable<Integer, Integer>();
	private Properties m_ctx = null;
	private String m_trxName = null;
	
	public ProductLowLevelCalculator(Properties ctx, String trxName)
	{
		m_ctx = ctx;
		m_trxName = trxName;
	}
	
	/**
	 * get low level of a Product
	 * @param ID Product
	 * @return int low level
	 */
	public int getLowLevel(int M_Product_ID)
	{
		int AD_Client_ID = Env.getAD_Client_ID(m_ctx);
		tableproduct.clear(); //reset tableproduct cache
		DefaultMutableTreeNode ibom = null;

		tableproduct.put(M_Product_ID, 0); //insert parent into cache
		ibom = iparent(AD_Client_ID, M_Product_ID, 0); //start traversing tree

		return ibom.getDepth();
	}
	
	/**
	 * get an implotion the product 
	 * @param ID Product
	 * @param ID BOM
	 * @return DefaultMutableTreeNode Tree with all parent product
	 */
	private DefaultMutableTreeNode iparent(int AD_Client_ID, int M_Product_ID, int PP_Product_BOM_ID)
	{

		DefaultMutableTreeNode parent = new DefaultMutableTreeNode(Integer.toString(M_Product_ID) + "|" + Integer.toString(PP_Product_BOM_ID));

		String sql = "SELECT PP_Product_BOMLine_ID FROM PP_Product_BOMLine" 
					+ " WHERE IsActive=? AND AD_Client_ID=? AND M_Product_ID=?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, m_trxName);
			DB.setParameters(pstmt, new Object[]{true, AD_Client_ID, M_Product_ID});
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				// If not the first bom line at this level
				if (rs.getRow() > 1)
				{
					//need to reset tableproduct cache
					tableproduct.clear();
					tableproduct.put(M_Product_ID, PP_Product_BOM_ID); //insert parent into cache
				}
				DefaultMutableTreeNode bom = icomponent(AD_Client_ID, rs.getInt(1), M_Product_ID, parent);
				if (bom != null)
				{
					parent.add(bom);
				}
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
		return parent;
	}

	/**
	 * get an implotion the product 
	 * @param ID Product
	 * @param ID BOM
	 * @return DefaultMutableTreeNode Tree with all parent product
	 */
	private DefaultMutableTreeNode icomponent(int AD_Client_ID, int PP_Product_BOMLine_ID, int M_Product_ID, DefaultMutableTreeNode bom)
	{
		final String sql = 
				"SELECT pbom.M_Product_ID , pbom.Value , pbom.PP_Product_BOM_ID FROM  PP_Product_BOMLine pboml"
				+ " INNER JOIN PP_Product_BOM pbom ON (pbom.PP_Product_BOM_ID = pboml.PP_Product_BOM_ID)"
				+ " WHERE pbom.IsActive=? AND pboml.IsActive=? AND pboml.AD_Client_ID=? AND pboml.PP_Product_BOMLine_ID=? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, m_trxName);
			DB.setParameters(pstmt, new Object[]{true, true, AD_Client_ID, PP_Product_BOMLine_ID});
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				if (M_Product_ID != rs.getInt(1))
				{
					//BOM Loop Error
					if (!tableproduct(rs.getInt(1), rs.getInt(3)))
					{
						bom.add(iparent(AD_Client_ID, rs.getInt(1), rs.getInt(3)));
					}
					else
					{
						throw new AdempiereException("Cycle BOM & Formula:" + rs.getString(2) + "(" + rs.getString(3) + ")");
					}
				}
				else
				{
					//Child = Parent error
					MProduct product = MProduct.get(m_ctx, M_Product_ID);
					throw new AdempiereException("Cycle BOM & Formula:" + rs.getString(2) + "(" + rs.getString(3) +")"
													+ " - Component: " + product.getValue() + "(" + product.getM_Product_ID() + ")");
				}
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
		return null;
	}

	/**
	 * find a product in cache
	 * @param ID Product
	 * @param ID BOM
	 * @return true if product is found
	 */
	private boolean tableproduct(int M_Product_ID, int PP_Product_BOM_ID)
	{
		if (tableproduct.containsKey(M_Product_ID))
		{
			return true;
		}
		tableproduct.put(M_Product_ID, PP_Product_BOM_ID);
		return false;
	}
}
