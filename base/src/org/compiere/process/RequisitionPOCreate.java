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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.adempiere.exceptions.NoVendorForProductException;
import org.apache.commons.collections.keyvalue.MultiKey;
import org.compiere.model.MBPartner;
import org.compiere.model.MCharge;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPO;
import org.compiere.model.MRequisition;
import org.compiere.model.MRequisitionLine;
import org.compiere.model.POResultSet;
import org.compiere.model.Query;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.DB;
import org.compiere.util.Msg;

/**
 * 	Create PO from Requisition 
 *	
 *	
 *  @author Jorg Janke
 *  @version $Id: RequisitionPOCreate.java,v 1.2 2006/07/30 00:51:01 jjanke Exp $
 *  
 *  @author Teo Sarca, www.arhipac.ro
 *  		<li>BF [ 2609760 ] RequisitionPOCreate not using DateRequired
 *  		<li>BF [ 2605888 ] CreatePOfromRequisition creates more PO than needed
 *  		<li>BF [ 2811718 ] Create PO from Requsition without any parameter teminate in NPE
 *  			http://sourceforge.net/tracker/?func=detail&atid=879332&aid=2811718&group_id=176962
 *  		<li>FR [ 2844074  ] Requisition PO Create - more selection fields
 *  			https://sourceforge.net/tracker/?func=detail&aid=2844074&group_id=176962&atid=879335
 *  @author victor.perez@e-evolution.com, e-Evolution.SC
 *  		<li> #1894 Converting a requisition to purchase order through a Smart Browser
 *  		<li> https://github.com/adempiere/adempiere/issues/1894
 */
public class RequisitionPOCreate extends RequisitionPOCreateAbstract
{
	/** Order				*/
	private MOrder purchaseOrder = null;
	/** Order Line			*/
	private MOrderLine purcaseOrderLine = null;
	/** Orders Cache : (C_BPartner_ID, DateRequired, M_PriceList_ID) -> MOrder */
	private HashMap<MultiKey, MOrder> purchaseOrdersCache = new HashMap<MultiKey, MOrder>();
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		super.prepare();
	}	//	prepare
	
	/**
	 * 	Process
	 *	@return info
	 *	@throws Exception
	 */
	protected String doIt() throws Exception
	{
		// Processing Requisition Lines from Smart Browser M_Requisition_Create_PO_From_Requisition
		if (getSelectionKeys()!=null && getSelectionKeys().size() > 0) {
			for ( int requisitionLineId : getSelectionKeys()) {
				MRequisitionLine requisitionLine = new MRequisitionLine(getCtx(), requisitionLineId, get_TrxName());
					process(requisitionLine);
			}
			//Close Orders
			closeOrder();
			return "";
		}

		//	Specific
		if (getRequisitionId() != 0)
		{
			log.info("M_Requisition_ID=" + getRequisitionId());
			MRequisition requisition = new MRequisition(getCtx(), getRequisitionId(), get_TrxName());
			if (!MRequisition.DOCSTATUS_Completed.equals(requisition.getDocStatus()))
			{
				throw new AdempiereUserError("@DocStatus@ = " + requisition.getDocStatus());
			}
			MRequisitionLine[] requisitionLines = requisition.getLines();
			for (int i = 0; i < requisitionLines.length; i++)
			{
				if (requisitionLines[i].getC_OrderLine_ID() == 0)
				{
					process (requisitionLines[i]);
				}
			}
			closeOrder();
			return "";
		}	//	single Requisition
		
		//	
		log.info("AD_Org_ID=" + getOrgId()
			+ ", M_Warehouse_ID=" + getWarehouseId()
			+ ", DateDoc=" + getDateDoc() + "/" + getDateDocTo()
			+ ", DateRequired=" + getDateRequired()+ "/" + getDateRequiredTo()
			+ ", PriorityRule=" + getPriorityRule()
			+ ", AD_User_ID=" + getUserId()
			+ ", M_Product_ID=" + getProductId()
			+ ", ConsolidateDocument" + isConsolidateDocument());
		
		ArrayList<Object> params = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer("C_OrderLine_ID IS NULL");
		if (getOrgId() > 0)
		{
			whereClause.append(" AND AD_Org_ID=?");
			params.add(getOrgId());
		}
		if (getProductId() > 0)
		{
			whereClause.append(" AND M_Product_ID=?");
			params.add(getProductId());
		}
		else if (getProductCategoryId() > 0)
		{
			whereClause.append(" AND EXISTS (SELECT 1 FROM M_Product p WHERE M_RequisitionLine.M_Product_ID=p.M_Product_ID")
				.append(" AND p.M_Product_Category_ID=?)");
			params.add(getProductCategoryId());
		}
		
		if (getBPGroupId() > 0)
		{
			whereClause.append(" AND (")
			.append("M_RequisitionLine.C_BPartner_ID IS NULL")
			.append(" OR EXISTS (SELECT 1 FROM C_BPartner bp WHERE M_RequisitionLine.C_BPartner_ID=bp.C_BPartner_ID AND bp.C_BP_Group_ID=?)")
			.append(")");
			params.add(getBPGroupId());
		}
		
		//
		//	Requisition Header
		whereClause.append(" AND EXISTS (SELECT 1 FROM M_Requisition r WHERE M_RequisitionLine.M_Requisition_ID=r.M_Requisition_ID")
			.append(" AND r.DocStatus=?");
		params.add(MRequisition.DOCSTATUS_Completed);
		if (getWarehouseId() > 0)
		{
			whereClause.append(" AND r.M_Warehouse_ID=?");
			params.add(getWarehouseId());
		}
		if (getDateDoc() != null)
		{
			whereClause.append(" AND r.DateDoc >= ?");
			params.add(getDateDoc());
		}
		if (getDateDocTo()!= null)
		{
			whereClause.append(" AND r.DateDoc <= ?");
			params.add(getDateDocTo());
		}
		if (getDateRequired() != null)
		{
			whereClause.append(" AND r.DateRequired >= ?");
			params.add(getDateRequired());
		}
		if (getDateRequiredTo() != null)
		{
			whereClause.append(" AND r.DateRequired <= ?");
			params.add(getDateRequiredTo());
		}
		if (getPriorityRule() != null)
		{
			whereClause.append(" AND r.PriorityRule >= ?");
			params.add(getPriorityRule());
		}
		if (getUserId() > 0)
		{
			whereClause.append(" AND r.AD_User_ID=?");
			params.add(getUserId());
		}
		whereClause.append(")"); // End Requisition Header
		//
		// ORDER BY clause
		StringBuffer orderClause = new StringBuffer();
		if (!isConsolidateDocument())
		{
			orderClause.append("M_Requisition_ID, ");
		}
		orderClause.append("(SELECT DateRequired FROM M_Requisition r WHERE M_RequisitionLine.M_Requisition_ID=r.M_Requisition_ID),");
		orderClause.append("M_Product_ID, C_Charge_ID, M_AttributeSetInstance_ID");
		
		POResultSet<MRequisitionLine> rs = new Query(getCtx(), MRequisitionLine.Table_Name, whereClause.toString(), get_TrxName())
											.setParameters(params)
											.setOrderBy(orderClause.toString())
											.setClient_ID()
											.scroll();
		try
		{
			while (rs.hasNext())
			{
				process(rs.next());
			}
		}
		finally
		{
			DB.close(rs); rs = null;
		}
		closeOrder();
		return "";
	}	//	doit
	
	private int requisitionId = 0;
	private int productId = 0;
	private int attributeSetInstanceId = 0;
	/** BPartner				*/
	private MBPartner businessPartner = null;
	
	/**
	 * 	Process Line
	 *	@param requisitionLine request line
	 * 	@throws Exception
	 */
	private void process (MRequisitionLine requisitionLine) throws Exception
	{
		if (requisitionLine.getM_Product_ID() == 0 && requisitionLine.getC_Charge_ID() == 0)
		{
			log.warning("Ignored Line" + requisitionLine.getLine()
				+ " " + requisitionLine.getDescription()
				+ " - " + requisitionLine.getLineNetAmt());
			return;
		}
		
		if (!isConsolidateDocument() && requisitionLine.getM_Requisition_ID() != requisitionId)
		{
			closeOrder();
		}
		if (purcaseOrderLine == null
			|| requisitionLine.getM_Product_ID() != productId
			|| requisitionLine.getM_AttributeSetInstance_ID() != attributeSetInstanceId
			|| requisitionLine.getC_Charge_ID() != 0		//	single line per charge
			|| purchaseOrder == null
			|| purchaseOrder.getDatePromised().compareTo(requisitionLine.getDateRequired()) != 0
			)
		{
			newLine(requisitionLine);
			// No Order Line was produced (vendor was not valid/allowed) => SKIP
			if (purcaseOrderLine == null)
				return;
		}

		//	Update Order Line
		purcaseOrderLine.setQty(purcaseOrderLine.getQtyOrdered().add(requisitionLine.getQty()));
		//	Update Requisition Line
		requisitionLine.setC_OrderLine_ID(purcaseOrderLine.getC_OrderLine_ID());
		requisitionLine.saveEx();
	}	//	process
	
	/**
	 * 	Create new Order
	 *	@param requisitionLine request line
	 *	@param partnerId b.partner
	 * 	@throws Exception
	 */
	private void newOrder(MRequisitionLine requisitionLine, int partnerId) throws Exception
	{
		if (purchaseOrder != null)
		{
			closeOrder();
		}
		
		//	BPartner
		if (businessPartner == null || partnerId != businessPartner.get_ID())
		{
			businessPartner = MBPartner.get(getCtx(), partnerId);
		}
		
		
		//	Order
		Timestamp dateRequired = requisitionLine.getDateRequired();
		int priceListId = requisitionLine.getParent().getM_PriceList_ID();
		MultiKey key = new MultiKey(partnerId, dateRequired, priceListId);
		purchaseOrder = purchaseOrdersCache.get(key);
		if (purchaseOrder == null)
		{
			purchaseOrder = new MOrder(getCtx(), 0, get_TrxName());
			purchaseOrder.setAD_Org_ID(requisitionLine.getAD_Org_ID());
			purchaseOrder.setM_Warehouse_ID(requisitionLine.getParent().getM_Warehouse_ID());
			purchaseOrder.setDatePromised(dateRequired);
			purchaseOrder.setIsSOTrx(false);
			purchaseOrder.setC_DocTypeTarget_ID();
			purchaseOrder.setBPartner(businessPartner);
			purchaseOrder.setM_PriceList_ID(priceListId);
			//	default po document type
			if (!isConsolidateDocument())
			{
				purchaseOrder.setDescription(Msg.getElement(getCtx(), "M_Requisition_ID")
					+ ": " + requisitionLine.getParent().getDocumentNo());
			}
			
			//	Prepare Save
			purchaseOrder.saveEx();
			// Put to cache
			purchaseOrdersCache.put(key, purchaseOrder);
		}
		requisitionId = requisitionLine.getM_Requisition_ID();
	}	//	newOrder

	/**
	 * 	Close Order
	 * 	@throws Exception
	 */
	private void closeOrder() throws Exception
	{
		if (purcaseOrderLine != null)
		{
			purcaseOrderLine.saveEx();
		}
		if (purchaseOrder != null)
		{
			purchaseOrder.load(get_TrxName());
			addLog(0, null, purchaseOrder.getGrandTotal(), purchaseOrder.getDocumentNo());
		}
		purchaseOrder = null;
		purcaseOrderLine = null;
	}	//	closeOrder

	
	/**
	 * 	New Order Line (different Product)
	 *	@param requisitionLine request line
	 * 	@throws Exception
	 */
	private void newLine(MRequisitionLine requisitionLine) throws Exception
	{
		if (purcaseOrderLine != null)
		{
			purcaseOrderLine.saveEx();
		}
		purcaseOrderLine = null;
		MProduct product = MProduct.get(getCtx(), requisitionLine.getM_Product_ID());

		//	Get Business Partner
		int partnerId = requisitionLine.getC_BPartner_ID();
		if (partnerId != 0)
		{
			;
		}
		else if (requisitionLine.getC_Charge_ID() != 0)
		{
			MCharge charge = MCharge.get(getCtx(), requisitionLine.getC_Charge_ID());
			partnerId = charge.getC_BPartner_ID();
			if (partnerId == 0)
			{
				throw new AdempiereUserError("No Vendor for Charge " + charge.getName());
			}
		}
		else
		{
			// Find Strategic Vendor for Product
			// TODO: refactor
			MProductPO[] pproductPurchaseInfo = MProductPO.getOfProduct(getCtx(), product.getM_Product_ID(), null);
			for (int i = 0; i < pproductPurchaseInfo.length; i++)
			{
				if (pproductPurchaseInfo[i].isCurrentVendor() && pproductPurchaseInfo[i].getC_BPartner_ID() != 0)
				{
					partnerId = pproductPurchaseInfo[i].getC_BPartner_ID();
					break;
				}
			}
			if (partnerId == 0 && pproductPurchaseInfo.length > 0)
			{
				partnerId = pproductPurchaseInfo[0].getC_BPartner_ID();
			}
			if (partnerId == 0)
			{
				throw new NoVendorForProductException(product.getName());
			}
		}
		
		if (!isGenerateForVendor(partnerId))
		{
			log.info("Skip for partner "+partnerId);
			return;
		}

		//	New Order - Different Vendor
		if (purchaseOrder == null
			|| purchaseOrder.getC_BPartner_ID() != partnerId
			|| purchaseOrder.getDatePromised().compareTo(requisitionLine.getDateRequired()) != 0
			)
		{
			newOrder(requisitionLine, partnerId);
		}
		
		//	No Order Line
		purcaseOrderLine = new MOrderLine(purchaseOrder);
		purcaseOrderLine.setDatePromised(requisitionLine.getDateRequired());
		if (product != null)
		{
			purcaseOrderLine.setProduct(product);
			purcaseOrderLine.setM_AttributeSetInstance_ID(requisitionLine.getM_AttributeSetInstance_ID());
		}
		else
		{
			purcaseOrderLine.setC_Charge_ID(requisitionLine.getC_Charge_ID());
			purcaseOrderLine.setPriceActual(requisitionLine.getPriceActual());
		}
		purcaseOrderLine.setAD_Org_ID(requisitionLine.getAD_Org_ID());
				
		
		//	Prepare Save
		productId = requisitionLine.getM_Product_ID();
		attributeSetInstanceId = requisitionLine.getM_AttributeSetInstance_ID();
		purcaseOrderLine.saveEx();
	}	//	newLine

	/**
	 * Do we need to generate Purchase Orders for given Vendor 
	 * @param partnerId
	 * @return true if it's allowed
	 */
	private boolean isGenerateForVendor(int partnerId)
	{
		// No filter group was set => generate for all vendors
		if (getBPGroupId() <= 0)
			return true;
		
		if (m_excludedVendors.contains(partnerId))
			return false;
		//
		boolean match = new Query(getCtx(), MBPartner.Table_Name, "C_BPartner_ID=? AND C_BP_Group_ID=?", get_TrxName())
		.setParameters(partnerId, getBPGroupId())
		.match();
		if (!match)
		{
			m_excludedVendors.add(partnerId);
		}
		return match;
	}
	private List<Integer> m_excludedVendors = new ArrayList<Integer>();
	
}	//	RequisitionPOCreate
