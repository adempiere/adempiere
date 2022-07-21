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
package org.eevolution.wms.engine;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MLocator;
import org.compiere.model.MStorage;
import org.compiere.util.DB;
import org.eevolution.wms.model.MWMArea;
import org.eevolution.wms.model.MWMSection;
import org.eevolution.wms.model.MWMSectionDetail;

/**
 * victor.perez@e-evolution.com , www.e-evolution.com
 */
public class WMRuleFIFO implements WMRuleInterface {

	/**
	 * Get locator
	 * @param ctx
	 * @param warehouseId
	 * @param productId
	 * @param warehouseAreaTypeId
	 * @param warehouseSectionTypeId
	 * @param trxName
     * @return
     */
	public List<MLocator> getLocator(Properties ctx, int warehouseId,
			int productId, int warehouseAreaTypeId, int warehouseSectionTypeId,
			String trxName) {
		ArrayList<MLocator> locators = new ArrayList();
		List<MWMArea> areas = MWMArea.getByWarehouse(ctx, warehouseId,
				trxName);
		for (MWMArea area : areas) {
			List<MWMSection> sections = area
					.getBySectionType(warehouseSectionTypeId);
			for (MWMSection section : sections) {
				locators.addAll(MWMSectionDetail.getLocatorBySection(section));
			}
		}

		return locators;
	}

	/**
	 * Get Storages
	 * @param ctx
	 * @param warehouseId
	 * @param productId
	 * @param attributeSetInstanceId
	 * @param qtyToDelivery
	 * @param warehouseAreaTypeId
	 * @param warehouseSectionTypeId
     * @param trxName
     * @return
     */
	public List<MStorage> getStorage(Properties ctx, int warehouseId,
									 int productId, int attributeSetInstanceId,
									 BigDecimal qtyToDelivery, int warehouseAreaTypeId,
									 int warehouseSectionTypeId, String trxName) {
		ArrayList<MStorage> storage = new ArrayList();
		for (MWMArea area : MWMArea.getByWarehouse(ctx, warehouseId, trxName)) {
			for (MWMSection section : area.getBySectionType(warehouseSectionTypeId)) {
				for (MLocator locator : MWMSectionDetail.getLocatorBySection(section)) {
					storage.addAll(getWarehouse(ctx, warehouseId,
							productId, attributeSetInstanceId, true,
							true, locator.getM_Locator_ID(), trxName));
				}
			}
		}
		return storage;
	}

	/**
	 * Get Storage
	 * @param ctx
	 * @param warehouseId
	 * @param productId
	 * @param attributeSetInstanceId
	 * @param FiFo
	 * @param positiveOnly
	 * @param locatorId
     * @param trxName
     * @return
     */
	public List<MStorage> getWarehouse(Properties ctx,
			int warehouseId, int productId,
			int attributeSetInstanceId, boolean FiFo, boolean positiveOnly,
			int locatorId, String trxName) {
		Timestamp minGuaranteeDate = new Timestamp(System.currentTimeMillis());
		ArrayList<MStorage> list = new ArrayList<MStorage>();

		if ((warehouseId == 0 && locatorId == 0) || productId == 0)
			return list;

		boolean allAttributeInstances = false;
		if (attributeSetInstanceId == 0)
			allAttributeInstances = true;

		// Specific Attribute Set Instance
		String sql = "SELECT s.M_Product_ID,s.M_Locator_ID,s.M_AttributeSetInstance_ID,"
				+ "s.AD_Client_ID,s.AD_Org_ID,s.IsActive,s.Created,s.CreatedBy,s.Updated,s.UpdatedBy,"
				+ "s.QtyOnHand,s.QtyReserved,s.QtyOrdered,s.DateLastInventory "
				+ "FROM M_Storage s"
				+ " INNER JOIN M_Locator l ON (l.M_Locator_ID=s.M_Locator_ID) ";
		if (locatorId > 0)
			sql += "WHERE l.M_Locator_ID = ?";
		else
			sql += "WHERE l.M_Warehouse_ID=?";
		sql += " AND s.M_Product_ID=?"
				+ " AND COALESCE(s.M_AttributeSetInstance_ID,0)=? ";
		if (positiveOnly) {
			sql += " AND s.QtyOnHand > 0 ";
		} else {
			sql += " AND s.QtyOnHand <> 0 ";
		}
		sql += "ORDER BY l.PriorityNo DESC, M_AttributeSetInstance_ID";
		if (!FiFo)
			sql += " DESC";
		// All Attribute Set Instances
		if (allAttributeInstances) {
			sql = "SELECT s.M_Product_ID,s.M_Locator_ID,s.M_AttributeSetInstance_ID,"
					+ "s.AD_Client_ID,s.AD_Org_ID,s.IsActive,s.Created,s.CreatedBy,s.Updated,s.UpdatedBy,"
					+ "s.QtyOnHand,s.QtyReserved,s.QtyOrdered,s.DateLastInventory,s.UUID"
					+ " FROM M_Storage s"
					+ " INNER JOIN M_Locator l ON (l.M_Locator_ID=s.M_Locator_ID)"
					+ " LEFT OUTER JOIN M_AttributeSetInstance asi ON (s.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID) ";
			if (locatorId > 0)
				sql += "WHERE l.M_Locator_ID = ?";
			else
				sql += "WHERE l.M_Warehouse_ID=?";
			sql += " AND s.M_Product_ID=? ";
			if (positiveOnly) {
				sql += " AND s.QtyOnHand > 0 ";
			} else {
				sql += " AND s.QtyOnHand <> 0 ";
			}
			if (minGuaranteeDate != null) {
				sql += "AND (asi.GuaranteeDate IS NULL OR asi.GuaranteeDate>?) ";
				sql += "ORDER BY l.PriorityNo DESC, "
						+ "asi.GuaranteeDate, M_AttributeSetInstance_ID";
				if (!FiFo)
					sql += " DESC";
				sql += ", s.QtyOnHand DESC";
			} else {
				sql += "ORDER BY l.PriorityNo DESC, l.M_Locator_ID, s.M_AttributeSetInstance_ID";
				if (!FiFo)
					sql += " DESC";
				sql += ", s.QtyOnHand DESC";
			}
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, locatorId > 0 ? locatorId : warehouseId);
			pstmt.setInt(2, productId);
			if (!allAttributeInstances) {
				pstmt.setInt(3, attributeSetInstanceId);
			} else if (minGuaranteeDate != null) {
				pstmt.setTimestamp(3, minGuaranteeDate);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getBigDecimal(11).signum() != 0)
					list.add(new MStorage(ctx, rs, trxName));
			}
		} catch (Exception e) {
			// s_log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return list;
	} // getWarehouse
}
