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
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.model.GridTabWrapper;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *	Physical Inventory Callouts
 *	
 *  @author Jorg Janke
 *  @version $Id: CalloutInventory.java,v 1.2 2006/07/30 00:51:03 jjanke Exp $
 */
public class CalloutInventory extends CalloutEngine
{
	/**
	 *  Product/Locator/ASI modified.
	 * 		Set Attribute Set Instance
	 *
	 *  @param ctx      Context
	 *  @param WindowNo current Window No
	 *  @param mTab     Model Tab
	 *  @param mField   Model Field
	 *  @param value    The new value
	 *  @return Error message or ""
	 */
	public String product (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (isCalloutActive())
			return "";
		Integer InventoryLine = (Integer)mTab.getValue("M_InventoryLine_ID");
		BigDecimal bd = null;
		
		if (InventoryLine != null && InventoryLine.intValue() != 0) {
			MInventoryLine _ILine = new MInventoryLine(ctx, InventoryLine, null);
			Integer M_Product_ID = (Integer)mTab.getValue("M_Product_ID");
			Integer M_Locator_ID = (Integer)mTab.getValue("M_Locator_ID");		
			Integer M_AttributeSetInstance_ID = 0;
			// if product or locator has changed recalculate Book Qty
			if ((M_Product_ID != null && M_Product_ID != _ILine.getM_Product_ID()) || 
					(M_Locator_ID !=null && M_Locator_ID != _ILine.getM_Locator_ID())) {

				// Check ASI - if product has been changed remove old ASI
				if (M_Product_ID == _ILine.getM_Product_ID()) {
					M_AttributeSetInstance_ID = (Integer)mTab.getValue("M_AttributeSetInstance_ID");
					if( M_AttributeSetInstance_ID == null )
						M_AttributeSetInstance_ID = 0;
				} else {
					mTab.setValue("M_AttributeSetInstance_ID", null);
				}
				try {
					bd = setQtyBook(M_AttributeSetInstance_ID, M_Product_ID, M_Locator_ID);
					mTab.setValue("QtyBook", bd);
				} catch (Exception e) {
					return mTab.setValue("QtyBook", bd);
				}
			}
			return "";
		}
			
		//	New Line - Get Book Value
		int M_Product_ID = 0;
		Integer Product = (Integer)mTab.getValue("M_Product_ID");
		if (Product != null)
			M_Product_ID = Product.intValue();
		if (M_Product_ID == 0)
			return "";
		int M_Locator_ID = 0;
		Integer Locator = (Integer)mTab.getValue("M_Locator_ID");
		if (Locator != null)
			M_Locator_ID = Locator.intValue();
		if (M_Locator_ID == 0)
			return "";
		
		//	Set Attribute
		int M_AttributeSetInstance_ID = 0; 
		Integer ASI = (Integer)mTab.getValue("M_AttributeSetInstance_ID");
		if (ASI != null)
			M_AttributeSetInstance_ID = ASI.intValue();
		//	Product Selection
		if (MInventoryLine.COLUMNNAME_M_Product_ID.equals(mField.getColumnName()))
		{
			if (Env.getContextAsInt(ctx, WindowNo, Env.TAB_INFO, "M_Product_ID") == M_Product_ID)
			{
				M_AttributeSetInstance_ID = Env.getContextAsInt(ctx, WindowNo, Env.TAB_INFO, "M_AttributeSetInstance_ID");
			}
			else
			{
				M_AttributeSetInstance_ID = 0;
			}
			if (M_AttributeSetInstance_ID != 0)
				mTab.setValue(MInventoryLine.COLUMNNAME_M_AttributeSetInstance_ID, M_AttributeSetInstance_ID);
			else
				mTab.setValue(MInventoryLine.COLUMNNAME_M_AttributeSetInstance_ID, null);
		}
			
		// Set QtyBook from first storage location
		// kviiksaar: Call's now the extracted function
		try {
			bd = setQtyBook(M_AttributeSetInstance_ID, M_Product_ID, M_Locator_ID);
			mTab.setValue("QtyBook", bd);
		} catch (Exception e) {
			return mTab.setValue("QtyBook", bd);
		}
		
		//
		log.info("M_Product_ID=" + M_Product_ID 
			+ ", M_Locator_ID=" + M_Locator_ID
			+ ", M_AttributeSetInstance_ID=" + M_AttributeSetInstance_ID
			+ " - QtyBook=" + bd);
		return "";
	}   //  product
	
	
	/**
	 * kviiksaar
	 * 
	 * Returns the current Book Qty for given parameters or 0
	 * 
	 * @param M_AttributeSetInstance_ID
	 * @param M_Product_ID
	 * @param M_Locator_ID
	 * @return
	 * @throws Exception
	 */
	private BigDecimal setQtyBook (int M_AttributeSetInstance_ID, int M_Product_ID, int M_Locator_ID) throws Exception {
		// Set QtyBook from first storage location
		BigDecimal bd = null;
		String sql = "SELECT QtyOnHand FROM M_Storage "
			+ "WHERE M_Product_ID=?"	//	1
			+ " AND M_Locator_ID=?"		//	2
			+ " AND M_AttributeSetInstance_ID=?";
		if (M_AttributeSetInstance_ID == 0)
			sql = "SELECT SUM(QtyOnHand) FROM M_Storage "
			+ "WHERE M_Product_ID=?"	//	1
			+ " AND M_Locator_ID=?";	//	2
		
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, M_Product_ID);
			pstmt.setInt(2, M_Locator_ID);
			if (M_AttributeSetInstance_ID != 0)
				pstmt.setInt(3, M_AttributeSetInstance_ID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
			{
				bd = rs.getBigDecimal(1);
				if (bd != null)
					return bd;
			} else {
				// gwu: 1719401: clear Booked Quantity to zero first in case the query returns no rows, 
				// for example when the locator has never stored a particular product.
				return new BigDecimal(0);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
			throw new Exception(e.getLocalizedMessage());
		}
		return new BigDecimal(0);
	}

    /**
     * Check if of qty Onhand
     * @param ctx
     * @param WindowNo
     * @param mTab
     * @param mField
     * @param value
     * @return
     */
    public String checkOnHand(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
        I_M_InventoryLine line = GridTabWrapper.create(mTab, I_M_InventoryLine.class);

        if (line.getM_Product_ID() == 0)
            return "";

        if (line.getM_Product().isStocked() && line.getQtyInternalUse().signum() > 0) {
            BigDecimal qtyInternalUse = line.getQtyInternalUse();
            BigDecimal qtyOnHand = DB.getSQLValueBD(null, "SELECT bomQtyOnHand(M_Product_ID,?,?) FROM M_Product WHERE M_Product_ID=?",line.getM_Inventory().getM_Warehouse_ID(), 0, line.getM_Product_ID());
            if (qtyOnHand == null)
                qtyOnHand = Env.ZERO;
            if (qtyOnHand.signum() == 0)
                mTab.fireDataStatusEEvent("NoQtyAvailable", "0", false);
            else if (qtyOnHand.compareTo(qtyInternalUse) < 0)
                mTab.fireDataStatusEEvent("InsufficientQtyAvailable", qtyOnHand.toString(), false);
        }
        return "";
    }


}	//	CalloutInventory
