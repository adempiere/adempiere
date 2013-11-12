/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          * 
 * http://www.adempiere.org                                           * 
 *                                                                    * 
 * Copyright (C) Victor Perez	                                      * 
 * Copyright (C) Contributors                                         * 
 *                                                                    * 
 * This program is free software; you can redistribute it and/or      * 
 * modify it under the terms of the GNU General Public License        * 
 * as published by the Free Software Foundation; either version 2     * 
 * of the License, or (at your option) any later version.             * 
 *                                                                    * 
 * This program is distributed in the hope that it will be useful,    * 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of     * 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       * 
 * GNU General Public License for more details.                       * 
 *                                                                    * 
 * You should have received a copy of the GNU General Public License  * 
 * along with this program; if not, write to the Free Software        * 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         * 
 * MA 02110-1301, USA.                                                * 
 *                                                                    * 
 * Contributors:                                                      * 
 *  - Victor Perez (victor.perez@e-evolution.com	 )                *
 *                                                                    *
 * Sponsors:                                                          *
 *  - e-Evolution (http://www.e-evolution.com/)                       *
 **********************************************************************/
package org.eevolution.engines.Warehouse;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import java.util.logging.Level;


import org.compiere.model.MLocator;
import org.compiere.model.MStorage;
import org.compiere.model.Query;
import org.compiere.model.X_M_Storage;
import org.compiere.util.DB;
import org.eevolution.model.MWMArea;
import org.eevolution.model.MWMSection;
import org.eevolution.model.MWMSectionDetail;


public class WMRuleFIFO implements WMRuleInterface
{

	public Collection<MLocator> getLocator(Properties ctx,
			int M_Warehouse_ID, int M_Product_ID, int WM_Area_Type_ID,
			int WM_Section_Type_ID, String trxName) 
	{
		ArrayList<MLocator> locators = new ArrayList();		
		Collection<MWMArea> areas = MWMArea.getMWMArea(ctx, M_Warehouse_ID, trxName);
		for (MWMArea area : areas)
		{
			Collection<MWMSection> sections = area.getWMSection(WM_Section_Type_ID);
			for(MWMSection section : sections)
			{
				locators.addAll(MWMSectionDetail.getMLocators(section));
			}
		}
		
		return locators;
	}

	public Collection<MStorage> getStorage(Properties ctx, int M_Warehouse_ID , int M_Product_ID, int M_AttributeSetInstance_ID
			, BigDecimal qtyToDelivery, int WM_Area_Type_ID , int WM_Section_Type_ID, String trxName) 
	{		
		ArrayList<MStorage> storages = new ArrayList();		
		for (MWMArea area : MWMArea.getMWMArea(ctx, M_Warehouse_ID, trxName))
		{
			for(MWMSection section :  area.getWMSection(WM_Section_Type_ID))
			{
				for(MLocator locator : MWMSectionDetail.getMLocators(section))
				{		
				storages.addAll(getWarehouse(ctx, M_Warehouse_ID, M_Product_ID, M_AttributeSetInstance_ID, true, true, locator.getM_Locator_ID(), trxName));
				}
			}
		}		
		return storages;
	}

	

	public Collection<MStorage> getWarehouse (Properties ctx, int M_Warehouse_ID, 
		int M_Product_ID, int M_AttributeSetInstance_ID,
		boolean FiFo, boolean positiveOnly, int M_Locator_ID, String trxName)
	{
		Timestamp minGuaranteeDate = new Timestamp(System.currentTimeMillis());  
		ArrayList<MStorage> list = new ArrayList<MStorage>();
		
		if ((M_Warehouse_ID == 0 && M_Locator_ID == 0) || M_Product_ID == 0)
			return list;
		
		boolean allAttributeInstances = false;
		if (M_AttributeSetInstance_ID == 0)
			allAttributeInstances = true;		
		
		//	Specific Attribute Set Instance
		String sql = "SELECT s.M_Product_ID,s.M_Locator_ID,s.M_AttributeSetInstance_ID,"
			+ "s.AD_Client_ID,s.AD_Org_ID,s.IsActive,s.Created,s.CreatedBy,s.Updated,s.UpdatedBy,"
			+ "s.QtyOnHand,s.QtyReserved,s.QtyOrdered,s.DateLastInventory "
			+ "FROM M_Storage s"
			+ " INNER JOIN M_Locator l ON (l.M_Locator_ID=s.M_Locator_ID) ";
		if (M_Locator_ID > 0)
			sql += "WHERE l.M_Locator_ID = ?";
		else
			sql += "WHERE l.M_Warehouse_ID=?";
		sql += " AND s.M_Product_ID=?"
			 + " AND COALESCE(s.M_AttributeSetInstance_ID,0)=? ";
		if (positiveOnly)
		{
			sql += " AND s.QtyOnHand > 0 ";
		}
		else
		{
			sql += " AND s.QtyOnHand <> 0 ";
		}
		sql += "ORDER BY l.PriorityNo DESC, M_AttributeSetInstance_ID";
		if (!FiFo)
			sql += " DESC";
		//	All Attribute Set Instances
		if (allAttributeInstances)
		{
			sql = "SELECT s.M_Product_ID,s.M_Locator_ID,s.M_AttributeSetInstance_ID,"
				+ "s.AD_Client_ID,s.AD_Org_ID,s.IsActive,s.Created,s.CreatedBy,s.Updated,s.UpdatedBy,"
				+ "s.QtyOnHand,s.QtyReserved,s.QtyOrdered,s.DateLastInventory "
				+ "FROM M_Storage s"
				+ " INNER JOIN M_Locator l ON (l.M_Locator_ID=s.M_Locator_ID)"
				+ " LEFT OUTER JOIN M_AttributeSetInstance asi ON (s.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID) ";
			if (M_Locator_ID > 0)
				sql += "WHERE l.M_Locator_ID = ?";
			else
				sql += "WHERE l.M_Warehouse_ID=?";
			sql += " AND s.M_Product_ID=? ";
			if (positiveOnly)
			{
				sql += " AND s.QtyOnHand > 0 ";
			}
			else
			{
				sql += " AND s.QtyOnHand <> 0 ";
			}
			if (minGuaranteeDate != null)
			{
				sql += "AND (asi.GuaranteeDate IS NULL OR asi.GuaranteeDate>?) ";
				sql += "ORDER BY l.PriorityNo DESC, " +
					   "asi.GuaranteeDate, M_AttributeSetInstance_ID";
				if (!FiFo)
					sql += " DESC";
				sql += ", s.QtyOnHand DESC";
			}
			else
			{
				sql += "ORDER BY l.PriorityNo DESC, l.M_Locator_ID, s.M_AttributeSetInstance_ID";
				if (!FiFo)
					sql += " DESC";
				sql += ", s.QtyOnHand DESC";
			}
		} 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, M_Locator_ID > 0 ? M_Locator_ID : M_Warehouse_ID);
			pstmt.setInt(2, M_Product_ID);
			if (!allAttributeInstances)
			{
				pstmt.setInt(3, M_AttributeSetInstance_ID);
			}
			else if (minGuaranteeDate != null)
			{
				pstmt.setTimestamp(3, minGuaranteeDate);
			}
			rs = pstmt.executeQuery();
			while (rs.next())
			{	
				if(rs.getBigDecimal(11).signum() != 0)
				list.add (new MStorage (ctx, rs, trxName));
			}	
		}
		catch (Exception e)
		{
			//s_log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		return list;
	}	//	getWarehouse
}
