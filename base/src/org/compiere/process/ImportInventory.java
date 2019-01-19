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
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MAcctSchema;
import org.compiere.model.MAttributeSet;
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MInventory;
import org.compiere.model.MInventoryLine;
import org.compiere.model.MProduct;
import org.compiere.model.X_I_Inventory;
import org.compiere.util.DB;
import org.compiere.util.TimeUtil;

/**
 *	Import Physical Inventory from I_Inventory
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: ImportInventory.java,v 1.2 2006/07/30 00:51:01 jjanke Exp $
 */
public class ImportInventory extends SvrProcess
{
	/**	Client to be imported to		*/
	private int				p_AD_Client_ID = 0;
	/**	Organization to be imported to	*/
	private int				p_AD_Org_ID = 0;
	/**	Location to be imported to		*/
	private int				p_M_Locator_ID = 0;
	/**	Default Date					*/
	private Timestamp		p_MovementDate = null;
	/**	Delete old Imported				*/
	private boolean			p_DeleteOldImported = false;
	
	//@Trifon
	/**	Update Costing					*/
	private boolean			p_UpdateCosting = false;
	/**	Accounting Schema in which costing to be updated	*/
	private int				p_C_AcctSchema_ID = 0;
	MAcctSchema acctSchema 	= null;
	/**	Cost Type for which costing to be updated		*/
	private int				p_M_CostType_ID = 0;
	/**	Cost Element for which costing to be updated	*/
	private int				p_M_CostElement_ID = 0;
	/**	Organization for which Costing record must be updated	*/
	private int				p_AD_OrgTrx_ID = 0;
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("AD_Client_ID"))
				p_AD_Client_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("AD_Org_ID"))
				p_AD_Org_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("M_Locator_ID"))
				p_M_Locator_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("MovementDate"))
				p_MovementDate = (Timestamp)para[i].getParameter();
			else if (name.equals("DeleteOldImported"))
				p_DeleteOldImported = "Y".equals(para[i].getParameter());
			else if (name.equals("IsUpdateCosting"))
				p_UpdateCosting = "Y".equals(para[i].getParameter());
			else if (name.equals("C_AcctSchema_ID"))
				p_C_AcctSchema_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("M_CostType_ID"))
				p_M_CostType_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("M_CostElement_ID"))
				p_M_CostElement_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("AD_OrgTrx_ID"))
				p_AD_OrgTrx_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}	//	prepare


	/**
	 *  Perform process.
	 *  @return Message
	 *  @throws Exception
	 */
	protected String doIt() throws java.lang.Exception
	{
		log.info("M_Locator_ID=" + p_M_Locator_ID + ",MovementDate=" + p_MovementDate);
		
		if (p_UpdateCosting) {
			if (p_C_AcctSchema_ID <= 0) {
				throw new IllegalArgumentException("Accounting Schema required!");
			}
			if (p_M_CostType_ID <= 0) {
				throw new IllegalArgumentException("Cost Type required!");
			}
			if (p_M_CostElement_ID <= 0 ) {
				throw new IllegalArgumentException("Cost Element required!");
			}
			if (p_AD_OrgTrx_ID < 0 ) {
				throw new IllegalArgumentException("AD_OrgTrx required!");
			}
			 acctSchema = MAcctSchema.get(getCtx(), p_C_AcctSchema_ID, get_TrxName());
		}
		
		StringBuffer sql = null;
		int no = 0;
		String clientCheck = " AND AD_Client_ID=" + p_AD_Client_ID;

		//	****	Prepare	****

		//	Delete Old Imported
		if (p_DeleteOldImported)
		{
			sql = new StringBuffer ("DELETE I_Inventory "
				  + "WHERE I_IsImported='Y'").append (clientCheck);
			no = DB.executeUpdate (sql.toString (), get_TrxName());
			log.fine("Delete Old Imported=" + no);
		}

		//	Set Client, Org, Location, IsActive, Created/Updated
		sql = new StringBuffer ("UPDATE I_Inventory "
			  + "SET AD_Client_ID = COALESCE (AD_Client_ID,").append (p_AD_Client_ID).append ("),"
			  + " AD_Org_ID = COALESCE (AD_Org_ID,").append (p_AD_Org_ID).append ("),");
		if (p_MovementDate != null)
			sql.append(" MovementDate = COALESCE (MovementDate,").append (DB.TO_DATE(p_MovementDate)).append ("),");
		sql.append(" IsActive = COALESCE (IsActive, 'Y'),"
			  + " Created = COALESCE (Created, SysDate),"
			  + " CreatedBy = COALESCE (CreatedBy, 0),"
			  + " Updated = COALESCE (Updated, SysDate),"
			  + " UpdatedBy = COALESCE (UpdatedBy, 0),"
			  + " I_ErrorMsg = ' ',"
			  + " M_Warehouse_ID = NULL,"	//	reset
			  + " I_IsImported = 'N' "
			  + "WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");
		no = DB.executeUpdate (sql.toString (), get_TrxName());
		log.info ("Reset=" + no);

		sql = new StringBuffer ("UPDATE I_Inventory o "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Org, '"
			+ "WHERE (AD_Org_ID IS NULL OR AD_Org_ID=0"
			+ " OR EXISTS (SELECT * FROM AD_Org oo WHERE o.AD_Org_ID=oo.AD_Org_ID AND (oo.IsSummary='Y' OR oo.IsActive='N')))"
			+ " AND I_IsImported<>'Y'").append (clientCheck);
		no = DB.executeUpdate (sql.toString (), get_TrxName());
		if (no != 0)
			log.warning ("Invalid Org=" + no);


		//	Location
		sql = new StringBuffer ("UPDATE I_Inventory i "
			+ "SET M_Locator_ID=(SELECT MAX(M_Locator_ID) FROM M_Locator l"
			+ " WHERE i.LocatorValue=l.Value AND i.AD_Client_ID=l.AD_Client_ID) "
			+ "WHERE M_Locator_ID IS NULL AND LocatorValue IS NOT NULL"
			+ " AND I_IsImported<>'Y'").append (clientCheck);
		no = DB.executeUpdate (sql.toString (), get_TrxName());
		log.fine("Set Locator from Value =" + no);
		sql = new StringBuffer ("UPDATE I_Inventory i "
			+ "SET M_Locator_ID=(SELECT MAX(M_Locator_ID) FROM M_Locator l"
			+ " WHERE i.X=l.X AND i.Y=l.Y AND i.Z=l.Z AND i.AD_Client_ID=l.AD_Client_ID) "
			+ "WHERE M_Locator_ID IS NULL AND X IS NOT NULL AND Y IS NOT NULL AND Z IS NOT NULL"
			+ " AND I_IsImported<>'Y'").append (clientCheck);
		no = DB.executeUpdate (sql.toString (), get_TrxName());
		log.fine("Set Locator from X,Y,Z =" + no);
		if (p_M_Locator_ID != 0)
		{
			sql = new StringBuffer ("UPDATE I_Inventory "
				+ "SET M_Locator_ID = ").append (p_M_Locator_ID).append (
				" WHERE M_Locator_ID IS NULL"
				+ " AND I_IsImported<>'Y'").append (clientCheck);
			no = DB.executeUpdate (sql.toString (), get_TrxName());
			log.fine("Set Locator from Parameter=" + no);
		}
		sql = new StringBuffer ("UPDATE I_Inventory "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No Location, ' "
			+ "WHERE M_Locator_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append (clientCheck);
		no = DB.executeUpdate (sql.toString (), get_TrxName());
		if (no != 0)
			log.warning ("No Location=" + no);


		//	Set M_Warehouse_ID
		sql = new StringBuffer ("UPDATE I_Inventory i "
			+ "SET M_Warehouse_ID=(SELECT M_Warehouse_ID FROM M_Locator l WHERE i.M_Locator_ID=l.M_Locator_ID) "
			+ "WHERE M_Locator_ID IS NOT NULL"
			+ " AND I_IsImported<>'Y'").append (clientCheck);
		no = DB.executeUpdate (sql.toString (), get_TrxName());
		log.fine("Set Warehouse from Locator =" + no);
		sql = new StringBuffer ("UPDATE I_Inventory "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No Warehouse, ' "
			+ "WHERE M_Warehouse_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append (clientCheck);
		no = DB.executeUpdate (sql.toString (), get_TrxName());
		if (no != 0)
			log.warning ("No Warehouse=" + no);


		//	Product
		sql = new StringBuffer ("UPDATE I_Inventory i "
			  + "SET M_Product_ID=(SELECT MAX(M_Product_ID) FROM M_Product p"
			  + " WHERE i.Value=p.Value AND i.AD_Client_ID=p.AD_Client_ID) "
			  + "WHERE M_Product_ID IS NULL AND Value IS NOT NULL"
			  + " AND I_IsImported<>'Y'").append (clientCheck);
		no = DB.executeUpdate (sql.toString (), get_TrxName());
		log.fine("Set Product from Value=" + no);
		sql = new StringBuffer ("UPDATE I_Inventory i "
				  + "SET M_Product_ID=(SELECT MAX(M_Product_ID) FROM M_Product p"
				  + " WHERE i.UPC=p.UPC AND i.AD_Client_ID=p.AD_Client_ID) "
				  + "WHERE M_Product_ID IS NULL AND UPC IS NOT NULL"
				  + " AND I_IsImported<>'Y'").append (clientCheck);
			no = DB.executeUpdate (sql.toString (), get_TrxName());
			log.fine("Set Product from UPC=" + no);
		sql = new StringBuffer ("UPDATE I_Inventory "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No Product, ' "
			+ "WHERE M_Product_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append (clientCheck);
		no = DB.executeUpdate (sql.toString (), get_TrxName());
		if (no != 0)
			log.warning ("No Product=" + no);

		//	No QtyCount
		sql = new StringBuffer ("UPDATE I_Inventory "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No Qty Count, ' "
			+ "WHERE QtyCount IS NULL"
			+ " AND I_IsImported<>'Y'").append (clientCheck);
		no = DB.executeUpdate (sql.toString (), get_TrxName());
		if (no != 0)
			log.warning ("No QtyCount=" + no);

		commitEx();
		
		/*********************************************************************/

		MInventory inventory = null;

		int noInsert = 0;
		int noInsertLine = 0;

		//	Go through Inventory Records
		sql = new StringBuffer ("SELECT * FROM I_Inventory "
			+ "WHERE I_IsImported='N'").append (clientCheck)
			.append(" ORDER BY M_Warehouse_ID, TRUNC(MovementDate), I_Inventory_ID");
		try
		{
			PreparedStatement preparedStatement = DB.prepareStatement (sql.toString (), get_TrxName());
			ResultSet resultSet = preparedStatement.executeQuery ();
			//
			int warehouseId = -1;
			Timestamp lastMovementDate = null;
			while (resultSet.next())
			{
				X_I_Inventory importInventory = new X_I_Inventory (getCtx (), resultSet, get_TrxName());
				Timestamp movementDate = TimeUtil.getDay(importInventory.getMovementDate());

				if (inventory == null
					|| importInventory.getM_Warehouse_ID() != warehouseId
					|| !movementDate.equals(lastMovementDate))
				{
					inventory = new MInventory (getCtx(), 0, get_TrxName());
					inventory.setClientOrg(importInventory.getAD_Client_ID(), importInventory.getAD_Org_ID());
					inventory.setDescription("I " + importInventory.getM_Warehouse_ID() + " " + movementDate);
					inventory.setM_Warehouse_ID(importInventory.getM_Warehouse_ID());
					inventory.setMovementDate(movementDate);
					//
					if (!inventory.save())
					{
						log.log(Level.SEVERE, "Inventory not saved");
						break;
					}
					warehouseId = importInventory.getM_Warehouse_ID();
					lastMovementDate = movementDate;
					noInsert++;
				}

				//	Line
				int attributeSetInstanceId = 0;
				if (importInventory.getLot() != null || importInventory.getSerNo() != null)
				{
					MProduct product = MProduct.get(getCtx(), importInventory.getM_Product_ID());
					if (product.isInstanceAttribute())
					{
						MAttributeSet attributeSet = product.getAttributeSet();
						MAttributeSetInstance attributeSetInstance = new MAttributeSetInstance(getCtx(), 0, attributeSet.getM_AttributeSet_ID(), get_TrxName());
						if (attributeSet.isLot() && importInventory.getLot() != null)
							attributeSetInstance.setLot(importInventory.getLot(), importInventory.getM_Product_ID());
						if (attributeSet.isSerNo() && importInventory.getSerNo() != null)
							attributeSetInstance.setSerNo(importInventory.getSerNo());
						attributeSetInstance.setDescription();
						attributeSetInstance.saveEx();
						attributeSetInstanceId = attributeSetInstance.getM_AttributeSetInstance_ID();
					}
				}
				MInventoryLine inventoryLine = new MInventoryLine (inventory,
					importInventory.getM_Locator_ID(), importInventory.getM_Product_ID(), attributeSetInstanceId,
					importInventory.getQtyBook(), importInventory.getQtyCount());
				inventoryLine.saveEx();

				importInventory.setI_IsImported(true);
				importInventory.setM_Inventory_ID(inventoryLine.getM_Inventory_ID());
				importInventory.setM_InventoryLine_ID(inventoryLine.getM_InventoryLine_ID());
				importInventory.setProcessed(true);
				importInventory.saveEx();
				
				noInsertLine++;
					//@Trifon update Product cost record if Update costing is enabled
				if (p_UpdateCosting) 
				{
						inventoryLine.setCurrentCostPrice(importInventory.getCurrentCostPrice());
						inventoryLine.setCurrentCostPriceLL(importInventory.getCurrentCostPriceLL());
						inventoryLine.saveEx();
				}			
			}
			resultSet.close();
			preparedStatement.close();
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
		}

		//	Set Error to indicator to not imported
		sql = new StringBuffer ("UPDATE I_Inventory "
			+ "SET I_IsImported='N', Updated=SysDate "
			+ "WHERE I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		addLog (0, null, new BigDecimal (no), "@Errors@");
		//
		addLog (0, null, new BigDecimal (noInsert), "@M_Inventory_ID@: @Inserted@");
		addLog (0, null, new BigDecimal (noInsertLine), "@M_InventoryLine_ID@: @Inserted@");
		return "";
	}	//	doIt

}	//	ImportInventory
