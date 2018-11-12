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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;

import org.compiere.model.MBPartner;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MOrgInfo;
import org.compiere.model.MProduct;
import org.compiere.model.MStorage;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.DB;

/**
 *	Generate PO from Sales Order
 *	
 *  @author Jorg Janke
 *  @version $Id: OrderPOCreate.java,v 1.2 2006/07/30 00:51:01 jjanke Exp $
 *  
 *  Contributor: Carlos Ruiz - globalqss
 *      Fix [1709952] - Process: "Generate PO from Sales order" bug
 */
public class OrderPOCreate extends OrderPOCreateAbstract
{
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		super.prepare();		
		// called from order window without parameters
		if ( getTable_ID() == MOrder.Table_ID && getRecord_ID() > 0 ) {
			super.setOrderId(getRecord_ID());		
		}
	}

	/**
	 *  Perform process.
	 *  @return Message 
	 *  @throws Exception if not successful
	 */
	protected String doIt() throws Exception
	{
		log.info(super.DATEORDERED+":"+super.getDateOrdered()+" - "+super.getDateOrderedTo()
			+", "+super.C_BPARTNER_ID+":"+super.getBPartnerId()
			+", "+super.VENDOR_ID+":"+super.getVendorId()
			+", "+super.ISDROPSHIP+":"+super.getIsDropShip()
			+", "+super.C_ORDER_ID+":"+super.getOrderId()
			);
		if (getOrderId() == 0
			&& getDateOrdered() == null && getDateOrderedTo() == null
			&& getBPartnerId() == 0 && getVendorId() == 0)
			throw new AdempiereUserError("You need to restrict selection");
		//
		String sql = "SELECT * FROM C_Order o "
			+ "WHERE o.IsSOTrx='Y'"
			//	No Duplicates
			//	" AND o.Link_Order_ID IS NULL"
			+ " AND NOT EXISTS (SELECT * FROM C_OrderLine ol WHERE o.C_Order_ID=ol.C_Order_ID AND ol.Link_OrderLine_ID IS NOT NULL)"
			; 
		if (getOrderId() != 0)
			sql += " AND o.C_Order_ID=?";
		else
		{
			if (getBPartnerId() != 0)
				sql += " AND o.C_BPartner_ID=?";
			if (getVendorId() != 0)
				sql += " AND EXISTS (SELECT * FROM C_OrderLine ol"
					+ " INNER JOIN M_Product_PO po ON (ol.M_Product_ID=po.M_Product_ID) "
						+ "WHERE o.C_Order_ID=ol.C_Order_ID AND po.C_BPartner_ID=?)"; 
			if (getDateOrdered() != null && getDateOrderedTo() != null)
				sql += "AND TRUNC(o.DateOrdered, 'DD') BETWEEN ? AND ?";
			else if (getDateOrdered() != null && getDateOrderedTo() == null)
				sql += "AND TRUNC(o.DateOrdered, 'DD') >= ?";
			else if (getDateOrdered() == null && getDateOrderedTo() != null)
				sql += "AND TRUNC(o.DateOrdered, 'DD') <= ?";
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int counter = 0;
		try
		{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			if (getOrderId() != 0)
				pstmt.setInt (1, getOrderId());
			else
			{
				int index = 1;
				if (getBPartnerId() != 0)
					pstmt.setInt (index++, getBPartnerId());
				if (getVendorId() != 0)
					pstmt.setInt (index++, getVendorId());
				if (getDateOrdered() != null && getDateOrderedTo() != null)
				{
					pstmt.setTimestamp(index++, getDateOrdered());
					pstmt.setTimestamp(index++, getDateOrderedTo());
				}
				else if (getDateOrdered() != null && getDateOrderedTo() == null)
					pstmt.setTimestamp(index++, getDateOrdered());
				else if (getDateOrdered() == null && getDateOrderedTo() != null)
					pstmt.setTimestamp(index++, getDateOrderedTo());
			}
			rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				counter += createPOFromSO (new MOrder (getCtx(), rs, get_TrxName()));
			}
 		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		if (counter == 0)
			log.fine(sql);
		return "@Created@ " + counter;
	}	//	doIt
	
	/**
	 * 	Create PO From SO
	 *	@param so sales order
	 *	@return number of POs created
	 * @throws Exception 
	 */
	private int createPOFromSO (MOrder so) throws Exception
	{
		log.info(so.toString());
		
		// see https://github.com/adempiere/adempiere/issues/1649
		if(DocAction.STATUS_InProgress.equals(so.getDocStatus()) || DocAction.STATUS_Completed.equals(so.getDocStatus())) {
			// OK - continue 
		} else {
			log.warning("DocStatus is " + so.getDocStatus());
			return 0;
		}
		
		MOrderLine[] soLines = so.getLines(true, null);
		if (soLines == null || soLines.length == 0)
		{
			log.warning("No Lines - " + so);
			return 0;
		}
		//
		int counter = 0;
		//	Order Lines with a Product which has a current vendor 
		String sql = "SELECT MIN(po.C_BPartner_ID), po.M_Product_ID "
			+ "FROM M_Product_PO po"
			+ " INNER JOIN C_OrderLine ol ON (po.M_Product_ID=ol.M_Product_ID) "
			+ "WHERE ol.C_Order_ID=? AND po.IsCurrentVendor='Y' "
			+ ((getVendorId() > 0) ? " AND po.C_BPartner_ID=? " : "")
			+ "GROUP BY po.M_Product_ID "
			+ "ORDER BY 1";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MOrder po = null;
		try
		{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setInt (1, so.getC_Order_ID());
			if (getVendorId() != 0)
				pstmt.setInt (2, getVendorId());
			rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				//	New Order
				int C_BPartner_ID = rs.getInt(1);
				if (po == null || po.getBill_BPartner_ID() != C_BPartner_ID)
				{
					po = createPOForVendor(rs.getInt(1), so);
					addLog(0, null, null, po.getDocumentNo());
					counter++;
				}

				//	Line
				int M_Product_ID = rs.getInt(2);
				for (int i = 0; i < soLines.length; i++)
				{
					if (soLines[i].getM_Product_ID() == M_Product_ID) {
						
						// see https://github.com/adempiere/adempiere/issues/1649
						boolean purchaseProduct = true;
						MProduct mProduct = soLines[i].getProduct();
						log.config("so OrderLine="+soLines[i] + " mProduct="+mProduct
								+ (mProduct.isStocked()   ? " isStocked"   : " notStocked")
								+ (mProduct.isPurchased() ? " isPurchased" : " notPurchased")
								+ (mProduct.isBOM()       ? " isBOM"       : " notBOM")
								+ (mProduct.isVerified()  ? " isVerified"  : " notVerified")
								);
						if(!mProduct.isPurchased()) { // the product cannot be purchased
							log.warning("cannot be purchased " + mProduct);
							purchaseProduct = false;
						}
						
						if(purchaseProduct && mProduct.isStocked()) { // check whether the product is onHand
							MStorage[] mStorages = MStorage.getOfProduct(getCtx(), M_Product_ID, get_TrxName());
							for(int s = 0; s < mStorages.length; s++) {
								log.config("mStorage="+mStorages[s]);
								if(mStorages[s].getQtyOnHand().compareTo(soLines[i].getQtyOrdered()) > -1 ) {
									log.warning(mProduct.toString() + " is onHand, will not be purchased");
									purchaseProduct = false;
								}
							}
						}

						if(purchaseProduct) {
							MOrderLine poLine = new MOrderLine (po);
							poLine.setLink_OrderLine_ID(soLines[i].getC_OrderLine_ID());
							poLine.setM_Product_ID(soLines[i].getM_Product_ID());
							poLine.setC_Charge_ID(soLines[i].getC_Charge_ID());
							poLine.setM_AttributeSetInstance_ID(soLines[i].getM_AttributeSetInstance_ID());
							poLine.setC_UOM_ID(soLines[i].getC_UOM_ID());
							poLine.setQtyEntered(soLines[i].getQtyEntered());
							poLine.setQtyOrdered(soLines[i].getQtyOrdered());
							poLine.setDescription(soLines[i].getDescription());
							poLine.setDatePromised(soLines[i].getDatePromised());
							poLine.setPrice();
							poLine.saveEx();
							
							soLines[i].setLink_OrderLine_ID(poLine.getC_OrderLine_ID());
							soLines[i].saveEx();
						}
						
					}
				}
			}
 		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
			throw e;
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		//	Set Reference to PO
		if (counter == 1 && po != null)
		{
			so.setLink_Order_ID(po.getC_Order_ID());
			so.saveEx();
		}
		return counter;
	}	//	createPOFromSO
	
	/**
	 *	Create PO for Vendor
	 *	@param C_BPartner_ID vendor
	 *	@param so sales order
	 */
	public MOrder createPOForVendor(int C_BPartner_ID, MOrder so)
	{
		MOrder po = new MOrder (getCtx(), 0, get_TrxName());
		po.setClientOrg(so.getAD_Client_ID(), so.getAD_Org_ID());
		po.setLink_Order_ID(so.getC_Order_ID());
		po.setIsSOTrx(false);
		po.setC_DocTypeTarget_ID();
		//
		po.setC_OrderSource_ID(so.getC_OrderSource_ID());
		po.setDescription(so.getDescription());
		po.setPOReference(so.getDocumentNo());
		po.setPriorityRule(so.getPriorityRule());
		po.setSalesRep_ID(so.getSalesRep_ID());
		po.setM_Warehouse_ID(so.getM_Warehouse_ID());
		//	Set Vendor
		MBPartner vendor = new MBPartner (getCtx(), C_BPartner_ID, get_TrxName());
		po.setBPartner(vendor);
		//	Drop Ship
		if ( "Y".equals(getIsDropShip()) )
		{
			po.setIsDropShip(true);
			
			if (so.isDropShip() && so.getDropShip_BPartner_ID() != 0 )	{
				po.setDropShip_BPartner_ID(so.getDropShip_BPartner_ID());
				po.setDropShip_Location_ID(so.getDropShip_Location_ID());
				po.setDropShip_User_ID(so.getDropShip_User_ID());
		}
			else {
				po.setDropShip_BPartner_ID(so.getC_BPartner_ID());
				po.setDropShip_Location_ID(so.getC_BPartner_Location_ID());
				po.setDropShip_User_ID(so.getAD_User_ID());
			}
			// get default drop ship warehouse
			MOrgInfo orginfo = MOrgInfo.get(getCtx(), po.getAD_Org_ID(), get_TrxName());
			if (orginfo.getDropShip_Warehouse_ID() != 0 )
				po.setM_Warehouse_ID(orginfo.getDropShip_Warehouse_ID());
			else
				log.log(Level.SEVERE, "Must specify drop ship warehouse in org info.");
		}
		//	References
		po.setC_Activity_ID(so.getC_Activity_ID());
		po.setC_Campaign_ID(so.getC_Campaign_ID());
		po.setC_Project_ID(so.getC_Project_ID());
		po.setUser1_ID(so.getUser1_ID());
		po.setUser2_ID(so.getUser2_ID());
		po.setUser3_ID(so.getUser3_ID());
		po.setUser4_ID(so.getUser4_ID());
		//
		po.saveEx();
		return po;
	}	//	createPOForVendor
	
}	//	doIt
