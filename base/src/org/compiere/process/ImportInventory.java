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

import org.adempiere.core.domains.models.X_I_Inventory;
import org.compiere.model.MAttributeSet;
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MInventory;
import org.compiere.model.MInventoryLine;
import org.compiere.model.MProduct;
import org.compiere.util.DB;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;

/**
 *	Import Physical Inventory from I_Inventory
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: ImportInventory.java,v 1.2 2006/07/30 00:51:01 jjanke Exp $
 * 	@author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class ImportInventory extends ImportInventoryAbstract {
	/**
	 *  Perform process.
	 *  @return Message
	 *  @throws Exception
	 */
	protected String doIt() throws java.lang.Exception {
		log.info("M_Locator_ID=" + getLocatorId() + ",MovementDate=" + getMovementDate());
		
		if (isUpdateCosting()) {
			if (getAcctSchemaId() <= 0) {
				throw new IllegalArgumentException("Accounting Schema required!");
			}
			if (getCostTypeId() <= 0) {
				throw new IllegalArgumentException("Cost Type required!");
			}
			if (getCostElementId() <= 0 ) {
				throw new IllegalArgumentException("Cost Element required!");
			}
			if (getOrgTrxId() < 0 ) {
				throw new IllegalArgumentException("AD_OrgTrx required!");
			}
		}
		
		StringBuffer sql = null;
		int no = 0;
		String clientCheck = " AND AD_Client_ID=" + getClientId();

		//	****	Prepare	****

		//	Delete Old Imported
		if (isDeleteOldImported())
		{
			sql = new StringBuffer ("DELETE I_Inventory "
				  + "WHERE I_IsImported='Y'").append (clientCheck);
			no = DB.executeUpdate (sql.toString (), get_TrxName());
			log.fine("Delete Old Imported=" + no);
		}

		//	Set Client, Org, Location, IsActive, Created/Updated
		sql = new StringBuffer ("UPDATE I_Inventory "
			  + "SET AD_Client_ID = COALESCE (AD_Client_ID,").append (getClientId()).append ("),"
			  + " AD_Org_ID = COALESCE (AD_Org_ID,").append (getOrgId()).append ("),");
		if (getMovementDate() != null)
			sql.append(" MovementDate = COALESCE (MovementDate,").append (DB.TO_DATE(getMovementDate())).append ("),");
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
		if (getLocatorId() != 0)
		{
			sql = new StringBuffer ("UPDATE I_Inventory "
				+ "SET M_Locator_ID = ").append (getLocatorId()).append (
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

		//	Attribute Set Instance
		sql = new StringBuffer ("UPDATE I_Inventory i "
			+ "SET M_AttributeSetInstance_ID=(SELECT MAX(M_AttributeSetInstance_ID) "
			+ "FROM M_AttributeSetInstance ai "
			+ "WHERE (TRIM(ai.Description)=TRIM(i.AttributeSetInstanceValue) OR TRIM(ai.Lot) LIKE TRIM(i.AttributeSetInstanceValue) OR TRIM(ai.SerNo) LIKE TRIM(i.SerNo))"
			+ "AND ai.IsActive = 'Y' "
			+ "AND ai.AD_Client_ID IN(0, i.AD_Client_ID) "
			+ "AND EXISTS(SELECT 1 FROM M_Product p WHERE p.M_Product_ID = i.M_Product_ID AND (p.M_AttributeSet_ID = ai.M_AttributeSet_ID OR (p.M_AttributeSet_ID IS NULL AND ai.M_AttributeSet_ID = 0)))) "
			+ "WHERE AttributeSetInstanceValue IS NOT NULL"
			+ " AND I_IsImported<>'Y'").append (clientCheck);
		no = DB.executeUpdate (sql.toString (), get_TrxName());
		log.fine("Set Attribute Set =" + no);
		sql = new StringBuffer ("UPDATE I_Inventory "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'" + Msg.parseTranslation(getCtx(), "@Error@ @M_AttributeSetInstance_ID@ @NotFound@") + "' "
			+ "WHERE AttributeSetInstanceValue IS NOT NULL AND M_AttributeSetInstance_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append (clientCheck);
		no = DB.executeUpdate (sql.toString (), get_TrxName());
		if (no != 0)
			log.warning ("No Attribute set instance=" + no);
		
		//	No QtyCount
		sql = new StringBuffer ("UPDATE I_Inventory "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No Qty Count, ' "
			+ "WHERE QtyCount IS NULL"
			+ " AND I_IsImported<>'Y'").append (clientCheck);
		no = DB.executeUpdate (sql.toString (), get_TrxName());
		if (no != 0)
			log.warning ("No QtyCount=" + no);
		
		sql = new StringBuffer ("UPDATE I_Inventory "
				+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=" + Msg.parseTranslation(getCtx(), "@SQLErrorNotUnique@ (@M_Inventory_ID@, @M_Locator_ID@, @M_Product_ID@) @Qty@ = ") 
				+ " ' || (SELECT COUNT(i.I_Inventory_ID) "
						+ "FROM I_Inventory i "
						+ "INNER JOIN M_Product p ON (i.M_Product_ID = p.M_Product_ID) "
						+ "LEFT JOIN M_AttributeSet a ON (a.M_AttributeSet_ID = p.M_AttributeSet_ID) "
						+ "WHERE i.M_Product_ID = I_Inventory.M_Product_ID "
						+ "AND i.M_Locator_ID = I_Inventory.M_Locator_ID "
						+ "AND i.I_IsImported<>'Y' "
						+ "AND ((COALESCE(a.IsInstanceAttribute, 'N') = 'Y' "
								+ "AND (COALESCE(i.M_AttributeSetInstance_ID,0) = COALESCE(I_Inventory.M_AttributeSetInstance_ID,0)) "
								+ "AND (COALESCE(i.Lot, '') = COALESCE(I_Inventory.Lot, '')) "
								+ "AND (COALESCE(i.SerNo, '') = COALESCE(I_Inventory.SerNo, ''))"
								+ ")OR COALESCE(a.IsInstanceAttribute, 'N') = 'N')"
						+ "GROUP BY i.M_Locator_ID, i.M_Product_ID) "
				+ "WHERE EXISTS(SELECT 1 "
							 + "FROM I_Inventory i "
							 + "INNER JOIN M_Product p ON (i.M_Product_ID = p.M_Product_ID) "
							 + "LEFT JOIN M_AttributeSet a ON (a.M_AttributeSet_ID = p.M_AttributeSet_ID) "
							 + "WHERE i.M_Product_ID = I_Inventory.M_Product_ID AND i.M_Locator_ID = I_Inventory.M_Locator_ID AND i.I_IsImported<>'Y' "
				+ "AND ((COALESCE(a.IsInstanceAttribute, 'N') = 'Y' "
						+ "AND (COALESCE(i.M_AttributeSetInstance_ID,0) = COALESCE(I_Inventory.M_AttributeSetInstance_ID,0)) "
						+ "AND (COALESCE(i.Lot, '') = COALESCE(I_Inventory.Lot, '')) "
						+ "AND (COALESCE(i.SerNo, '') = COALESCE(I_Inventory.SerNo, ''))"
				+ ")OR COALESCE(a.IsInstanceAttribute, 'N') = 'N')"
				+ "HAVING(COUNT(i.I_Inventory_ID) > 1)) "
				+ " AND I_IsImported<>'Y'").append (clientCheck);
		no = DB.executeUpdate (sql.toString (), get_TrxName());
		if (no != 0)
			log.warning ("Duplicated lines=" + no);

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
					inventory.setIsStocktake(true);
					//
					inventory.saveEx();
					warehouseId = importInventory.getM_Warehouse_ID();
					lastMovementDate = movementDate;
					noInsert++;
				}

				//	Line
				int attributeSetInstanceId = 0;
				if(importInventory.getM_AttributeSetInstance_ID() != 0) {
					attributeSetInstanceId = importInventory.getM_AttributeSetInstance_ID();
				} else if (importInventory.getLot() != null || importInventory.getSerNo() != null) {
					MProduct product = MProduct.get(getCtx(), importInventory.getM_Product_ID());
					if (product.isInstanceAttribute()) {
						MAttributeSet attributeSet = product.getAttributeSet();
						MAttributeSetInstance attributeSetInstance = new MAttributeSetInstance(getCtx(), 0, attributeSet.getM_AttributeSet_ID(), get_TrxName());
						if (attributeSet.isLot() && importInventory.getLot() != null) {
							attributeSetInstance.setLot(importInventory.getLot(), importInventory.getM_Product_ID());
						}
						if (attributeSet.isSerNo() && importInventory.getSerNo() != null) {
							attributeSetInstance.setSerNo(importInventory.getSerNo());
						}
						if (attributeSet.isGuaranteeDate()) {
							attributeSetInstance.setGuaranteeDate(importInventory.getGuaranteeDate());
						}
						attributeSetInstance.setDescription();
						attributeSetInstance.saveEx();
						attributeSetInstanceId = attributeSetInstance.getM_AttributeSetInstance_ID();
					}
				}
				//	
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
				if (isUpdateCosting()) 
				{
						inventoryLine.setCurrentCostPrice(importInventory.getCurrentCostPrice());
						inventoryLine.setCurrentCostPriceLL(importInventory.getCurrentCostPriceLL());
						inventoryLine.saveEx();
				}			
			}
			resultSet.close();
			preparedStatement.close();
		} catch (Exception e) {
			addLog(e.getLocalizedMessage());
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
