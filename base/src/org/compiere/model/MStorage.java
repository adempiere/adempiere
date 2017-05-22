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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Util;

/**
 * 	Inventory Storage Model
 *
 *	@author Jorg Janke
 *	@version $Id: MStorage.java,v 1.3 2006/07/30 00:51:05 jjanke Exp $
 */
public class MStorage extends X_M_Storage
{
	/**
	 * generated serialVersionUID
	 */
	private static final long serialVersionUID = 9086223702645715061L;

	/**
	 * 	Get Storage Info
	 *	@param ctx context
	 *	@param M_Locator_ID locator
	 *	@param M_Product_ID product
	 *	@param M_AttributeSetInstance_ID instance
	 *	@param trxName transaction
	 *	@return existing or null
	 */
	public static MStorage get (Properties ctx, int M_Locator_ID, 
		int M_Product_ID, int M_AttributeSetInstance_ID, String trxName)
	{
		MStorage retValue = null;
		String sql = "SELECT * FROM M_Storage "
			+ "WHERE M_Locator_ID=? AND M_Product_ID=? AND ";
		if (M_AttributeSetInstance_ID == 0)
			sql += "(M_AttributeSetInstance_ID=? OR M_AttributeSetInstance_ID IS NULL)";
		else
			sql += "M_AttributeSetInstance_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			pstmt.setInt (1, M_Locator_ID);
			pstmt.setInt (2, M_Product_ID);
			pstmt.setInt (3, M_AttributeSetInstance_ID);
			rs = pstmt.executeQuery ();
			if (rs.next ())
				retValue = new MStorage (ctx, rs, trxName);
		}
		catch (SQLException ex)
		{
			s_log.log(Level.SEVERE, sql, ex);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		if (retValue == null)
			s_log.fine("Not Found - M_Locator_ID=" + M_Locator_ID 
				+ ", M_Product_ID=" + M_Product_ID + ", M_AttributeSetInstance_ID=" + M_AttributeSetInstance_ID);
		else
			s_log.fine("M_Locator_ID=" + M_Locator_ID 
				+ ", M_Product_ID=" + M_Product_ID + ", M_AttributeSetInstance_ID=" + M_AttributeSetInstance_ID);
		return retValue;
	}	//	get


	/**
	 * get Qty Reserved
	 * @param ctx
	 * @param productId
	 * @param warehouseId
	 * @param attributeSetInstanceId
	 * @param trxName
	 * @return
	 */
	public static MStorage getQtyReserved (Properties ctx,
		int productId, int warehouseId, int attributeSetInstanceId, String trxName)
	{
		final StringBuilder whereClause = new StringBuilder();
		whereClause.append("EXISTS (SELECT 1 FROM M_Locator l WHERE l.M_Locator_ID=M_Storage.M_Locator_ID AND l.M_Warehouse_ID=? ) AND ");
		whereClause.append(MStorage.COLUMNNAME_M_Product_ID).append("=? AND ");
		if (attributeSetInstanceId == 0)
			whereClause.append(MStorage.COLUMNNAME_M_AttributeSetInstance_ID).append("=? OR ").append(MStorage.COLUMNNAME_M_AttributeSetInstance_ID).append(" IS NULL ");
		else
			whereClause.append(MStorage.COLUMNNAME_M_AttributeSetInstance_ID).append("=?");

		whereClause.append(" AND QtyReserved <> 0");

		return new Query(ctx,MStorage.Table_Name, whereClause.toString(), trxName).setClient_ID()
		.setParameters( warehouseId , productId , attributeSetInstanceId)
		.setOrderBy(MStorage.COLUMNNAME_QtyReserved + " Desc")
		.first();
	}	//	get


	/**
	 * get Qty Ordered
	 * @param ctx
	 * @param productId
	 * @param warehouseId
	 * @param attributeSetInstanceId
	 * @param trxName
	 * @return
	 */
	public static MStorage getQtyOrdered (Properties ctx,
		int productId, int warehouseId , int attributeSetInstanceId, String trxName)
	{
		final StringBuilder whereClause = new StringBuilder();
		whereClause.append("EXISTS (SELECT 1 FROM M_Locator l WHERE l.M_Locator_ID=M_Storage.M_Locator_ID AND l.M_Warehouse_ID=? ) AND ");
		whereClause.append(MStorage.COLUMNNAME_M_Product_ID).append("=? AND ");
		if (attributeSetInstanceId == 0)
			whereClause.append(MStorage.COLUMNNAME_M_AttributeSetInstance_ID).append("=? OR ").append(MStorage.COLUMNNAME_M_AttributeSetInstance_ID).append(" IS NULL ");
		else
			whereClause.append(MStorage.COLUMNNAME_M_AttributeSetInstance_ID).append("=?");

		whereClause.append(" AND QtyOrdered <> 0");

		return new Query(ctx,MStorage.Table_Name, whereClause.toString(), trxName).setClient_ID()
		.setParameters( warehouseId , productId , attributeSetInstanceId)
		.setOrderBy(MStorage.COLUMNNAME_QtyOrdered)
		.first();
	}	//	get

	/**
	 * 	Get all Storages for Product with ASI and QtyOnHand <> 0
	 *	@param ctx context
	 *	@param M_Product_ID product
	 *	@param M_Locator_ID locator
	 *	@param FiFo first in-first-out
	 *	@param trxName transaction
	 *	@return existing or null
	 */
	public static MStorage[] getAllWithASI (Properties ctx, int M_Product_ID, int M_Locator_ID, 
		boolean FiFo, String trxName)
	{
		ArrayList<MStorage> list = new ArrayList<MStorage>();
		String sql = "SELECT * FROM M_Storage "
			+ "WHERE M_Product_ID=? AND M_Locator_ID=?"
			+ " AND M_AttributeSetInstance_ID > 0 "
			+ " AND QtyOnHand <> 0 "			
			+ "ORDER BY M_AttributeSetInstance_ID";
		if (!FiFo)
			sql += " DESC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			pstmt.setInt (1, M_Product_ID);
			pstmt.setInt (2, M_Locator_ID);
			rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add(new MStorage (ctx, rs, trxName));
		}
		catch (SQLException ex)
		{
			s_log.log(Level.SEVERE, sql, ex);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		MStorage[] retValue = new MStorage[list.size()];
		list.toArray(retValue);
		return retValue;
	}	//	getAllWithASI

	/**
	 * 	Get all Storages for Product where QtyOnHand <> 0
	 *	@param ctx context
	 *	@param M_Product_ID product
	 *	@param M_Locator_ID locator
	 *	@param trxName transaction
	 *	@return existing or null
	 */
	public static MStorage[] getAll (Properties ctx, 
		int M_Product_ID, int M_Locator_ID, String trxName)
	{
		ArrayList<MStorage> list = new ArrayList<MStorage>();
		String sql = "SELECT * FROM M_Storage "
			+ "WHERE M_Product_ID=? AND M_Locator_ID=?"
			+ " AND QtyOnHand <> 0 "
			+ "ORDER BY M_AttributeSetInstance_ID";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			pstmt.setInt (1, M_Product_ID);
			pstmt.setInt (2, M_Locator_ID);
			rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add(new MStorage (ctx, rs, trxName));
		}
		catch (SQLException ex)
		{
			s_log.log(Level.SEVERE, sql, ex);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		MStorage[] retValue = new MStorage[list.size()];
		list.toArray(retValue);
		return retValue;
	}	//	getAll

	
	/**
	 * 	Get Storage Info for Product across warehouses
	 *	@param ctx context
	 *	@param M_Product_ID product
	 *	@param trxName transaction
	 *	@return existing or null
	 */
	public static MStorage[] getOfProduct (Properties ctx, int M_Product_ID, String trxName)
	{
		ArrayList<MStorage> list = new ArrayList<MStorage>();
		String sql = "SELECT * FROM M_Storage "
			+ "WHERE M_Product_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			pstmt.setInt (1, M_Product_ID);
			rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add(new MStorage (ctx, rs, trxName));
		}
		catch (SQLException ex)
		{
			s_log.log(Level.SEVERE, sql, ex);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		MStorage[] retValue = new MStorage[list.size()];
		list.toArray(retValue);
		return retValue;
	}	//	getOfProduct
	
	/**
	 * 	Get Storage Info for Warehouse
	 *	@param ctx context
	 *	@param M_Warehouse_ID 
	 *	@param M_Product_ID product
	 *	@param M_AttributeSetInstance_ID instance
	 *	@param M_AttributeSet_ID attribute set
	 *	@param allAttributeInstances if true, all attribute set instances
	 *	@param minGuaranteeDate optional minimum guarantee date if all attribute instances
	 *	@param FiFo first in-first-out
	 *	@param trxName transaction
	 *	@return existing - ordered by location priority (desc) and/or guarantee date
	 *
	 *  @deprecated
	 */
	public static MStorage[] getWarehouse (Properties ctx, int M_Warehouse_ID, 
		int M_Product_ID, int M_AttributeSetInstance_ID, int M_AttributeSet_ID,
		boolean allAttributeInstances, Timestamp minGuaranteeDate,
		boolean FiFo, String trxName)
	{
		return getWarehouse(ctx, M_Warehouse_ID, M_Product_ID, M_AttributeSetInstance_ID, 
				minGuaranteeDate, FiFo, false, 0, trxName);
	}
	
	/**
	 * 	Get Storage Info for Warehouse or locator
	 *	@param ctx context
	 *	@param M_Warehouse_ID ignore if M_Locator_ID > 0
	 *	@param M_Product_ID product
	 *	@param M_AttributeSetInstance_ID instance id, 0 to retrieve all instance
	 *	@param minGuaranteeDate optional minimum guarantee date if all attribute instances
	 *	@param FiFo first in-first-out
	 *  @param positiveOnly if true, only return storage records with qtyOnHand > 0
	 *  @param M_Locator_ID optional locator id
	 *	@param trxName transaction
	 *	@return existing - ordered by location priority (desc) and/or guarantee date
	 */
	public static MStorage[] getWarehouse (Properties ctx, int M_Warehouse_ID, 
		int M_Product_ID, int M_AttributeSetInstance_ID, Timestamp minGuaranteeDate,
		boolean FiFo, boolean positiveOnly, int M_Locator_ID, String trxName)
	{
		if ((M_Warehouse_ID == 0 && M_Locator_ID == 0) || M_Product_ID == 0)
			return new MStorage[0];
		
		boolean allAttributeInstances = false;
		if (M_AttributeSetInstance_ID == 0)
			allAttributeInstances = true;		
		
		ArrayList<MStorage> list = new ArrayList<MStorage>();
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
			s_log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		MStorage[] retValue = new MStorage[list.size()];
		list.toArray(retValue);
		return retValue;
	}	//	getWarehouse

	
	/**
	 * 	Create or Get Storage Info
	 *	@param ctx context
	 *	@param M_Locator_ID locator
	 *	@param M_Product_ID product
	 *	@param M_AttributeSetInstance_ID instance
	 *	@param trxName transaction
	 *	@return existing/new or null
	 */
	public static MStorage getCreate (Properties ctx, int M_Locator_ID, 
		int M_Product_ID, int M_AttributeSetInstance_ID, String trxName)
	{
		if (M_Locator_ID == 0)
			throw new IllegalArgumentException("M_Locator_ID=0");
		if (M_Product_ID == 0)
			throw new IllegalArgumentException("M_Product_ID=0");
		MStorage retValue = get(ctx, M_Locator_ID, M_Product_ID, M_AttributeSetInstance_ID, trxName);
		if (retValue != null)
			return retValue;
		
		//	Insert row based on locator
		MLocator locator = new MLocator (ctx, M_Locator_ID, trxName);
		if (locator.get_ID() != M_Locator_ID)
			throw new IllegalArgumentException("Not found M_Locator_ID=" + M_Locator_ID);
		//
		retValue = new MStorage (locator, M_Product_ID, M_AttributeSetInstance_ID);
		retValue.save(trxName);
		s_log.fine("New " + retValue);
		return retValue;
	}	//	getCreate

	
	/**
	 * 	Update Storage Info add.
	 * 	Called from MProjectIssue
	 *	@param ctx context
	 *	@param M_Warehouse_ID warehouse
	 *	@param M_Locator_ID locator
	 *	@param M_Product_ID product
	 *	@param M_AttributeSetInstance_ID AS Instance
	 *	@param reservationAttributeSetInstance_ID reservation AS Instance
	 *	@param diffQtyOnHand add on hand
	 *	@param diffQtyReserved add reserved
	 *	@param diffQtyOrdered add order
	 *	@param trxName transaction
	 *	@return true if updated
	 */
	public static boolean add (Properties ctx, int M_Warehouse_ID, int M_Locator_ID, 
		int M_Product_ID, int M_AttributeSetInstance_ID, int reservationAttributeSetInstance_ID,
		BigDecimal diffQtyOnHand, 
		BigDecimal diffQtyReserved, BigDecimal diffQtyOrdered, String trxName)
	{
		MStorage storage = null;
		StringBuffer diffText = new StringBuffer("(");

		//	Get Storage
		if (storage == null)
			storage = getCreate (ctx, M_Locator_ID, 
				M_Product_ID, M_AttributeSetInstance_ID, trxName);
		//	Verify
		if (storage.getM_Locator_ID() != M_Locator_ID 
			&& storage.getM_Product_ID() != M_Product_ID
			&& storage.getM_AttributeSetInstance_ID() != M_AttributeSetInstance_ID)
		{
			s_log.severe ("No Storage found - M_Locator_ID=" + M_Locator_ID 
				+ ",M_Product_ID=" + M_Product_ID + ",ASI=" + M_AttributeSetInstance_ID);
			return false;
		}
		
		// CarlosRuiz - globalqss - Fix [ 1725383 ] QtyOrdered wrongly updated
		MProduct prd = new MProduct(ctx, M_Product_ID, trxName);
		if (prd.getM_AttributeSet_ID() == 0) {
			// Product doesn't manage attribute set, always reserved with 0
			reservationAttributeSetInstance_ID = 0;
		}
		//		
		
		MStorage storage0 = null;
		if (M_AttributeSetInstance_ID != reservationAttributeSetInstance_ID)
		{
			//consumed the reserved qty storage
			if(diffQtyReserved != null && diffQtyReserved.signum() != 0)
				storage0 = getQtyReserved(ctx,
				M_Product_ID, M_Warehouse_ID , reservationAttributeSetInstance_ID, trxName);
			if(diffQtyOrdered.signum() != 0)
				storage0 = getQtyOrdered(ctx,
						M_Product_ID, M_Warehouse_ID, reservationAttributeSetInstance_ID, trxName);

			if (storage0 == null)	//	create if not existing - should not happen
			{
				MWarehouse wh = MWarehouse.get(ctx, M_Warehouse_ID);
				int xM_Locator_ID = wh.getDefaultLocator().getM_Locator_ID();
				storage0 = getCreate (ctx, xM_Locator_ID, 
					M_Product_ID, reservationAttributeSetInstance_ID, trxName);
			}
		}		
		boolean changed = false;
		if (diffQtyOnHand != null && diffQtyOnHand.signum() != 0)
		{
			storage.setQtyOnHand (storage.getQtyOnHand().add (diffQtyOnHand));
			diffText.append("OnHand=").append(diffQtyOnHand);
			changed = true;
		}
		if (diffQtyReserved != null && diffQtyReserved.signum() != 0)
		{
			if (storage0 == null)
			{
				storage.setQtyReserved(storage.getQtyReserved().add(diffQtyReserved));
				//Util.assume(storage.getQtyReserved().signum() >= 0, "QtyReserved should be >=0 for " + storage);
			}
			else
			{
				storage0.setQtyReserved(storage0.getQtyReserved().add(diffQtyReserved));
				//Util.assume(storage0.getQtyReserved().signum() >= 0, "QtyReserved should be >=0 for " + storage0);
			}
			diffText.append(" Reserved=").append(diffQtyReserved);
			changed = true;
			
		}
		if (diffQtyOrdered != null && diffQtyOrdered.signum() != 0)
		{
			if (storage0 == null)
				storage.setQtyOrdered (storage.getQtyOrdered().add (diffQtyOrdered));
			else
				storage0.setQtyOrdered (storage0.getQtyOrdered().add (diffQtyOrdered));
			diffText.append(" Ordered=").append(diffQtyOrdered);
			changed = true;
		}
		if (changed)
		{
			diffText.append(") -> ").append(storage.toString());
			s_log.fine(diffText.toString());
			if (storage0 != null)
				storage0.save(trxName);		//	No AttributeSetInstance (reserved/ordered)
			return storage.save (trxName);
		}
		
		return true;
	}	//	add

	
	/**************************************************************************
	 * 	Get Location with highest Locator Priority and a sufficient OnHand Qty
	 * 	@param M_Warehouse_ID warehouse
	 * 	@param M_Product_ID product
	 * 	@param M_AttributeSetInstance_ID asi
	 * 	@param Qty qty
	 *	@param trxName transaction
	 * 	@return id
	 */
	public static int getM_Locator_ID (int M_Warehouse_ID, 
		int M_Product_ID, int M_AttributeSetInstance_ID, BigDecimal Qty,
		String trxName)
	{
		int M_Locator_ID = 0;
		int firstM_Locator_ID = 0;
		String sql = "SELECT s.M_Locator_ID, s.QtyOnHand "
			+ "FROM M_Storage s"
			+ " INNER JOIN M_Locator l ON (s.M_Locator_ID=l.M_Locator_ID)"
			+ " INNER JOIN M_Product p ON (s.M_Product_ID=p.M_Product_ID)"
			+ " LEFT OUTER JOIN M_AttributeSet mas ON (p.M_AttributeSet_ID=mas.M_AttributeSet_ID) "
			+ "WHERE l.M_Warehouse_ID=?"
			+ " AND s.M_Product_ID=?"
			+ " AND (mas.IsInstanceAttribute IS NULL OR mas.IsInstanceAttribute='N' OR s.M_AttributeSetInstance_ID=?)"
			+ " AND l.IsActive='Y' "
			+ "ORDER BY l.PriorityNo DESC, s.QtyOnHand DESC";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, M_Warehouse_ID);
			pstmt.setInt(2, M_Product_ID);
			pstmt.setInt(3, M_AttributeSetInstance_ID);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				BigDecimal QtyOnHand = rs.getBigDecimal(2);
				if (QtyOnHand != null && Qty.compareTo(QtyOnHand) <= 0)
				{
					M_Locator_ID = rs.getInt(1);
					break;
				}
				if (firstM_Locator_ID == 0)
					firstM_Locator_ID = rs.getInt(1);
			}
		}
		catch (SQLException ex)
		{
			s_log.log(Level.SEVERE, sql, ex);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		if (M_Locator_ID != 0)
			return M_Locator_ID;
		return firstM_Locator_ID;
	}	//	getM_Locator_ID

	/**
	 * 	Get Available Qty.
	 * 	The call is accurate only if there is a storage record 
	 * 	and assumes that the product is stocked 
	 *	@param M_Warehouse_ID wh
	 *	@param M_Product_ID product
	 *	@param M_AttributeSetInstance_ID masi
	 *	@param trxName transaction
	 *	@return qty available (QtyOnHand-QtyReserved) or null
	 * @deprecated Since 331b. Please use {@link #getQtyAvailable(int, int, int, int, String)}.
	 */
	public static BigDecimal getQtyAvailable (int M_Warehouse_ID, 
		int M_Product_ID, int M_AttributeSetInstance_ID, String trxName)
	{
		return getQtyAvailable(M_Warehouse_ID, 0, M_Product_ID, M_AttributeSetInstance_ID, trxName);
	}
	
	/**
	 * Get Warehouse/Locator Available Qty.
	 * The call is accurate only if there is a storage record 
	 * and assumes that the product is stocked 
	 * @param M_Warehouse_ID wh (if the M_Locator_ID!=0 then M_Warehouse_ID is ignored)
	 * @param M_Locator_ID locator (if 0, the whole warehouse will be evaluated)
	 * @param M_Product_ID product
	 * @param M_AttributeSetInstance_ID masi
	 * @param trxName transaction
	 * @return qty available (QtyOnHand-QtyReserved) or null if error
	 */
	public static BigDecimal getQtyAvailable (int M_Warehouse_ID, int M_Locator_ID, 
		int M_Product_ID, int M_AttributeSetInstance_ID, String trxName)
	{
		ArrayList<Object> params = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("SELECT COALESCE(SUM(s.QtyOnHand-s.QtyReserved),0)")
								.append(" FROM M_Storage s")
								.append(" WHERE s.M_Product_ID=?");
		params.add(M_Product_ID);
		// Warehouse level
		if (M_Locator_ID == 0) {
			sql.append(" AND EXISTS (SELECT 1 FROM M_Locator l WHERE s.M_Locator_ID=l.M_Locator_ID AND l.M_Warehouse_ID=?)");
			params.add(M_Warehouse_ID);
		}
		// Locator level
		else {
			sql.append(" AND s.M_Locator_ID=?");
			params.add(M_Locator_ID);
		}
		// With ASI
		if (M_AttributeSetInstance_ID != 0) {
			sql.append(" AND s.M_AttributeSetInstance_ID=?");
			params.add(M_AttributeSetInstance_ID);
		}
		//
		BigDecimal retValue = DB.getSQLValueBD(trxName, sql.toString(), params);
		if (CLogMgt.isLevelFine())
			s_log.fine("M_Warehouse_ID=" + M_Warehouse_ID + ", M_Locator_ID=" + M_Locator_ID 
				+ ",M_Product_ID=" + M_Product_ID + " = " + retValue);
		return retValue;
	}	//	getQtyAvailable
	
	
	/**************************************************************************
	 * 	Persistency Constructor
	 *	@param ctx context
	 *	@param ignored ignored
	 *	@param trxName transaction
	 */
	public MStorage (Properties ctx, int ignored, String trxName)
	{
		super(ctx, 0, trxName);
		if (ignored != 0)
			throw new IllegalArgumentException("Multi-Key");
		//
		setQtyOnHand (Env.ZERO);
		setQtyOrdered (Env.ZERO);
		setQtyReserved (Env.ZERO);
	}	//	MStorage

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MStorage (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MStorage

	/**
	 * 	Full NEW Constructor
	 *	@param locator (parent) locator
	 *	@param M_Product_ID product
	 *	@param M_AttributeSetInstance_ID attribute
	 */
	private MStorage (MLocator locator, int M_Product_ID, int M_AttributeSetInstance_ID)
	{
		this (locator.getCtx(), 0, locator.get_TrxName());
		setClientOrg(locator);
		setM_Locator_ID (locator.getM_Locator_ID());
		setM_Product_ID (M_Product_ID);
		setM_AttributeSetInstance_ID (M_AttributeSetInstance_ID);
	}	//	MStorage

	/** Log								*/
	private static CLogger		s_log = CLogger.getCLogger (MStorage.class);
	/** Warehouse						*/
	private int		m_M_Warehouse_ID = 0;
	
	/**
	 * 	Change Qty OnHand
	 *	@param qty quantity
	 *	@param add add if true 
	 */
	public void changeQtyOnHand (BigDecimal qty, boolean add)
	{
		if (qty == null || qty.signum() == 0)
			return;
		if (add)
			setQtyOnHand(getQtyOnHand().add(qty));
		else
			setQtyOnHand(getQtyOnHand().subtract(qty));
	}	//	changeQtyOnHand

	/**
	 * 	Get M_Warehouse_ID of Locator
	 *	@return warehouse
	 */
	public int getM_Warehouse_ID()
	{
		if (m_M_Warehouse_ID == 0)
		{
			MLocator loc = MLocator.get(getCtx(), getM_Locator_ID());
			m_M_Warehouse_ID = loc.getM_Warehouse_ID();
		}
		return m_M_Warehouse_ID;
	}	//	getM_Warehouse_ID
	
	/**
	 *	String Representation
	 * 	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer("MStorage[")
			.append("M_Locator_ID=").append(getM_Locator_ID())
				.append(",M_Product_ID=").append(getM_Product_ID())
				.append(",M_AttributeSetInstance_ID=").append(getM_AttributeSetInstance_ID())
			.append(": OnHand=").append(getQtyOnHand())
			.append(",Reserved=").append(getQtyReserved())
			.append(",Ordered=").append(getQtyOrdered())
			.append("]");
		return sb.toString();
	}	//	toString

}	//	MStorage
