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
package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;

import org.compiere.model.MInventory;
import org.compiere.model.MInventoryLine;
import org.compiere.model.MInventoryLineMA;
import org.compiere.model.MStorage;
import org.compiere.util.AdempiereSystemError;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *	Update existing Inventory Count List with current Book value
 *	
 *  @author Jorg Janke
 *  @version $Id: InventoryCountUpdate.java,v 1.2 2006/07/30 00:51:01 jjanke Exp $
 */
public class InventoryCountUpdate extends InventoryCountUpdateAbstract
{
/*
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		super.prepare();
	}	//	prepare

	
	/**
	 * 	Process
	 *	@return message
	 *	@throws Exception
	 */
	protected String doIt () throws Exception
	{
		log.info("M_Inventory_ID=" + getRecord_ID());
		MInventory inventory = new MInventory (getCtx(), getRecord_ID(), get_TrxName());
		if (inventory.get_ID() == 0)
			throw new AdempiereSystemError ("Not found: M_Inventory_ID=" + getRecord_ID());

		//	Multiple Lines for one item
		String sql = "UPDATE M_InventoryLine SET IsActive='N' "
			+ "WHERE M_Inventory_ID=" + getRecord_ID()
			+ " AND (M_Product_ID, M_Locator_ID, M_AttributeSetInstance_ID) IN "
				+ "(SELECT M_Product_ID, M_Locator_ID, M_AttributeSetInstance_ID "
				+ "FROM M_InventoryLine "
				+ "WHERE M_Inventory_ID=" + getRecord_ID()
				+ " GROUP BY M_Product_ID, M_Locator_ID, M_AttributeSetInstance_ID "
				+ "HAVING COUNT(*) > 1)";
		int multiple = DB.executeUpdate(sql, get_TrxName());
		log.info("Multiple=" + multiple);

		int deleteInventoryMA = MInventoryLineMA.deleteInventoryMA(getRecord_ID(), get_TrxName());
		log.info("DeletedMA=" + deleteInventoryMA);

		//	ASI
		sql = "UPDATE M_InventoryLine l "
			+ "SET (QtyBook,QtyCount) = "
				+ "(SELECT QtyOnHand, " 
			+ (isUpdateCountQty() ? "s.QtyOnHand" : "l.QtyCount") + " FROM M_Storage s "
				+ "WHERE s.M_Product_ID=l.M_Product_ID AND s.M_Locator_ID=l.M_Locator_ID"
				+ " AND s.M_AttributeSetInstance_ID=l.M_AttributeSetInstance_ID),"
			+ " Updated=SysDate,"
			+ " UpdatedBy=" + getAD_User_ID()
			//
			+ " WHERE M_Inventory_ID=" + getRecord_ID()
			+ " AND EXISTS (SELECT * FROM M_Storage s "
				+ "WHERE s.M_Product_ID=l.M_Product_ID AND s.M_Locator_ID=l.M_Locator_ID"
				+ " AND s.M_AttributeSetInstance_ID=l.M_AttributeSetInstance_ID)";
		int no = DB.executeUpdate(sql, get_TrxName());
		log.info("Update with ASI=" + no);

		//	No ASI
		int noMA = updateWithMA();

		//	Set Count to Zero
		if (isUpdateCountQty() && "Z".equals(getSetInventoryCountto()))
		{
			sql = "UPDATE M_InventoryLine l "
				+ "SET QtyCount=0 "
				+ "WHERE M_Inventory_ID=" + getRecord_ID();
			no = DB.executeUpdate(sql, get_TrxName());
			log.info("Set Count to Zero=" + no);
		}
		
		if (multiple > 0)
			return "@M_InventoryLine_ID@ - #" + (no + noMA) + " --> @InventoryProductMultiple@";
		
		return "@M_InventoryLine_ID@ - #" + no;
	}	//	doIt

	/**
	 * 	Update Inventory Lines With Material Allocation
	 *	@return no updated
	 */
	private int updateWithMA()
	{
		int no = 0;
		//
		String sql = "SELECT * FROM M_InventoryLine WHERE M_Inventory_ID=? AND M_AttributeSetInstance_ID=0";
		PreparedStatement preparedStatement = null;
		try
		{
			preparedStatement = DB.prepareStatement (sql, get_TrxName());
			preparedStatement.setInt (1, getRecord_ID());
			ResultSet resultSet = preparedStatement.executeQuery ();
			while (resultSet.next ())
			{
				MInventoryLine inventoryLine = new MInventoryLine (getCtx(), resultSet, get_TrxName());
				BigDecimal onHand = Env.ZERO;
				MStorage[] storages = MStorage.getAll(getCtx(), inventoryLine.getM_Product_ID(), inventoryLine.getM_Locator_ID(), get_TrxName());
				MInventoryLineMA inventoryLineMA = null;
				for (int i = 0; i < storages.length; i++)
				{
					MStorage storage = storages[i];
					if (storage.getQtyOnHand().signum() == 0)
						continue;
					onHand = onHand.add(storage.getQtyOnHand());
					//	No ASI
					if (storage.getM_AttributeSetInstance_ID() == 0 
						&& storages.length == 1)
						continue;
					//	Save ASI
					inventoryLineMA = new MInventoryLineMA (inventoryLine,
						storage.getM_AttributeSetInstance_ID(), storage.getQtyOnHand());
					if (!inventoryLineMA.save())
						;
				}
				inventoryLine.setQtyBook(onHand);
				if ( isUpdateCountQty() )
					inventoryLine.setQtyCount(onHand);
				if (inventoryLine.save())
					no++;
			}
			resultSet.close ();
			preparedStatement.close ();
			preparedStatement = null;
		}
		catch (Exception e)
		{
			log.log (Level.SEVERE, sql, e);
		}
		try
		{
			if (preparedStatement != null)
				preparedStatement.close ();
			preparedStatement = null;
		}
		catch (Exception e)
		{
			preparedStatement = null;
		}
		//
		log.info("#" + no);
		return no;
	}	//	updateWithMA
	
	
}	//	InventoryCountUpdate
