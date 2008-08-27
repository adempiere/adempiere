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
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *  PP Product BOM Line Model.
 * 	<code>
 * 			MPPProductBOMLine l = new MPPProductBOMLine(bom);
 * 			l.setM_Product_ID(wbl.getM_Product_ID());
 * 			l.setQty(wbl.getQuantity());;
 * 			l.saveEx();
 *	</code>
 *  @author Victor Perez www.e-evolution.com     
 *  @version $Id: MOrderLine.java,v 1.22 2004/03/22 07:15:03 vpj-cd Exp $
 *  
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 */
public class MPPProductBOMLine extends X_PP_Product_BOMLine
{
	private static final long serialVersionUID = 1L;

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
		if (bom.get_ID() == 0)
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

	/**************************************************************************
	 * 	After Save
	 *	@param newRecord new
	 *	@return save
	 */
	protected boolean afterSave(boolean newRecord, boolean success)
	{
		if (success)
		{
			int lowlevel = getLowLevel();
			MProduct product = new MProduct(getCtx(), getM_Product_ID(), get_TrxName());
			product.setLowLevel(lowlevel); //update lowlevel
			product.saveEx();
		}
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
}

class ProductLowLevelCalculator {
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

		String sql = new String(
				"SELECT pboml.PP_Product_BOMLine_ID FROM  PP_Product_BOMLine pboml" 
				+ " WHERE pboml.IsActive= 'Y' AND pboml.AD_Client_ID = ? AND pboml.M_Product_ID = ? ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, m_trxName);
			pstmt.setInt(1, AD_Client_ID);
			pstmt.setInt(2, M_Product_ID);
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
			throw new DBException(e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
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
		String sql = 
				"SELECT pbom.M_Product_ID , pbom.Value , pbom.PP_Product_BOM_ID FROM  PP_Product_BOMLine pboml"
				+ " INNER JOIN PP_Product_BOM pbom ON (pbom.PP_Product_BOM_ID = pboml.PP_Product_BOM_ID)"
				+ " WHERE pbom.IsActive= 'Y' AND pboml.IsActive= 'Y' AND pboml.AD_Client_ID =? AND pboml.PP_Product_BOMLine_ID = ? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, m_trxName);
			pstmt.setInt(1, AD_Client_ID);
			pstmt.setInt(2, PP_Product_BOMLine_ID);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				if (M_Product_ID != rs.getInt(1))
				{
					//BOM Loop Error
					if (!tableproduct(rs.getInt(1), rs.getInt(3)))
						bom.add(iparent(AD_Client_ID, rs.getInt(1), rs.getInt(3)));
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
			throw new DBException(e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
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
		Integer p = new Integer(M_Product_ID);
		Integer bom = new Integer(PP_Product_BOM_ID);

		if (tableproduct.containsKey(p))
		{
			return true;
		}
		tableproduct.put(p, bom);
		return false;
	}
}
