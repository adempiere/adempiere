/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2016 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/

package org.compiere.process;

import java.math.BigDecimal;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MLocator;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.MRMA;
import org.compiere.model.MRMALine;
import org.compiere.model.MWarehouse;

/** Generated Process for (In Out Create From)
 *  @author ADempiere (generated) 
 *  @version Release 3.8.0
 *  <li> FR [ 327 ] Create From in M_InOut change to Smart Browse
 *  @see https://github.com/adempiere/adempiere/issues/327
 */
public class InOutCreateFrom extends InOutCreateFromAbstract {

	/**	Create From Type of RMA		*/
	private static final String RMA = "A";
	/**	Create From Type of Order	*/
	private static final String ORDER = "O";
	/**	Create From Type of Invoice	*/
	private static final String INVOICE = "I";
	/**	Create From Type			*/
	private String 	createFromType = null;
	/**	Created						*/
	private int 	created = 0;
	/**	In Out						*/
	private MInOut 	inout = null;
	/**	Reference Identifier		*/
	private int 	reference_ID = 0;
	/**	Default Locator				*/
	private MLocator defaultLocator = null;
	
	
	@Override
	protected void prepare() {
		super.prepare();
	}
	
	/**
	 * Get a valid locator
	 * @param locatorId
	 * @return
	 */
	private int getValidLocator(int locatorId) {
		// If a locator is specified on the product, choose that otherwise default locator
		if(getLocator() != 0)
			locatorId = getLocator();
		//	Validate Locator
		if(locatorId != 0) {
			MLocator locator = MLocator.get(getCtx(), locatorId);
			//	Set from default if it is distinct
			if(locator == null) {
				locatorId = 0;
			} else if(defaultLocator != null 
					&& locator.getM_Warehouse_ID() != defaultLocator.getM_Warehouse_ID()) {
				locatorId = 0;
			}
		}
		//	Valid locator
		if(locatorId == 0) {
			if(defaultLocator != null)
				locatorId = defaultLocator.getM_Locator_ID();
			else
				throw new AdempiereException("@M_Locator_ID@ @NotFound@");
		}
		//	Return
		return locatorId;
	}
	
	@Override
	protected String doIt() throws Exception {
		// Valid Record Identifier
		if(getRecord_ID() == 0)
			return "";
		//	Get Shipment
		inout = new MInOut(getCtx(), getRecord_ID(), get_TrxName());
		log.config(inout + ", C_Locator_ID=" + getLocator());
		//	Get Default Locator
		defaultLocator = MLocator.getDefault((MWarehouse) inout.getM_Warehouse());
		//	Loop
		for(Integer key : getSelectionKeys()) {
			if(createFromType == null) {
				createFromType = getSelectionAsString(key, "CF_CreateFromType");
				//	Valid Mandatory Create From Type
				if(createFromType == null
						|| createFromType.length() == 0)
					throw new AdempiereException("@CreateFromType@ @NotFound@");
			}
			// variable values
			
			int m_M_Product_ID = getSelectionAsInt(key, "CF_M_Product_ID");
			int m_C_Charge_ID = getSelectionAsInt(key, "CF_C_Charge_ID");
			int m_C_UOM_ID = getSelectionAsInt(key, "CF_C_UOM_ID");
			int m_M_Locator_ID = getSelectionAsInt(key, "CF_M_Locator_ID");
			BigDecimal m_QtyEntered = getSelectionAsBigDecimal(key, "CF_QtyEntered"); // Qty
			// If a locator is specified on the product, choose that otherwise default locator
			m_M_Locator_ID = getValidLocator(m_M_Locator_ID);
			MInvoiceLine il = null;
			//	Precision of Qty UOM
			int precision = 2;
			if (m_M_Product_ID != 0) {
				MProduct product = MProduct.get(getCtx(), m_M_Product_ID);
				precision = product.getUOMPrecision();
			}
			m_QtyEntered = m_QtyEntered.setScale(precision, BigDecimal.ROUND_HALF_DOWN);
			//
			log.fine("Line QtyEntered=" + m_QtyEntered
					+ ", Product=" + m_M_Product_ID 
					+ ", CreateFromType=" + createFromType + ", Key=" + key);

			//	Create new InOut Line
			MInOutLine iol = new MInOutLine (inout);
			iol.setM_Product_ID(m_M_Product_ID, m_C_UOM_ID);	//	Line UOM
			iol.setQty(m_QtyEntered);							//	Movement/Entered
			//
			if(createFromType.equals(ORDER)) {
				MOrderLine ol = new MOrderLine (getCtx(), key, get_TrxName());
				//	Set reference
				if(reference_ID == 0)
					reference_ID = ol.getC_Order_ID();
				//	
				iol.setC_OrderLine_ID(key);
				if (ol.getQtyEntered().compareTo(ol.getQtyOrdered()) != 0) {
					iol.setMovementQty(m_QtyEntered
							.multiply(ol.getQtyOrdered())
							.divide(ol.getQtyEntered(), 12, BigDecimal.ROUND_HALF_UP));
					iol.setC_UOM_ID(ol.getC_UOM_ID());
				}
				iol.setM_AttributeSetInstance_ID(ol.getM_AttributeSetInstance_ID());
				iol.setDescription(ol.getDescription());
				//
				iol.setC_Project_ID(ol.getC_Project_ID());
				iol.setC_ProjectPhase_ID(ol.getC_ProjectPhase_ID());
				iol.setC_ProjectTask_ID(ol.getC_ProjectTask_ID());
				iol.setC_Activity_ID(ol.getC_Activity_ID());
				iol.setC_Campaign_ID(ol.getC_Campaign_ID());
				iol.setAD_OrgTrx_ID(ol.getAD_OrgTrx_ID());
				iol.setUser1_ID(ol.getUser1_ID());
				iol.setUser2_ID(ol.getUser2_ID());
			} else if(createFromType.equals(INVOICE)) {
				il = new MInvoiceLine (getCtx(), key, get_TrxName());
				MInvoice invoice = il.getParent();
				//	Set reference
				if(reference_ID == 0)
					reference_ID = invoice.getC_Invoice_ID();
				//	Credit Memo - negative Qty
				if (invoice.isCreditMemo()) {
					m_QtyEntered = m_QtyEntered.negate();
					iol.setQty(m_QtyEntered);
				}
				if (il.getQtyEntered().compareTo(il.getQtyInvoiced()) != 0) {
					iol.setMovementQty(m_QtyEntered
							.multiply(il.getQtyInvoiced())
							.divide(il.getQtyEntered(), 12, BigDecimal.ROUND_HALF_UP));
					iol.setC_UOM_ID(il.getC_UOM_ID());
				}
				iol.setDescription(il.getDescription());
				iol.setC_Project_ID(il.getC_Project_ID());
				iol.setC_ProjectPhase_ID(il.getC_ProjectPhase_ID());
				iol.setC_ProjectTask_ID(il.getC_ProjectTask_ID());
				iol.setC_Activity_ID(il.getC_Activity_ID());
				iol.setC_Campaign_ID(il.getC_Campaign_ID());
				iol.setAD_OrgTrx_ID(il.getAD_OrgTrx_ID());
				iol.setUser1_ID(il.getUser1_ID());
				iol.setUser2_ID(il.getUser2_ID());
			} else if(createFromType.equals(RMA)) {
				MRMALine rmal = new MRMALine(getCtx(), key, get_TrxName());
				//	Set reference
				if(reference_ID == 0)
					reference_ID = rmal.getM_RMA_ID();
				iol.setM_RMALine_ID(key);
				iol.setQtyEntered(m_QtyEntered);
				iol.setDescription(rmal.getDescription());
				iol.setM_AttributeSetInstance_ID(rmal.getM_AttributeSetInstance_ID());
				iol.setC_Project_ID(rmal.getC_Project_ID());
				iol.setC_ProjectPhase_ID(rmal.getC_ProjectPhase_ID());
				iol.setC_ProjectTask_ID(rmal.getC_ProjectTask_ID());
				iol.setC_Activity_ID(rmal.getC_Activity_ID());
				iol.setAD_OrgTrx_ID(rmal.getAD_OrgTrx_ID());
				iol.setUser1_ID(rmal.getUser1_ID());
				iol.setUser2_ID(rmal.getUser2_ID());
			}
			//	Set Charge
			if(m_C_Charge_ID != 0)
				iol.setC_Charge_ID(m_C_Charge_ID);
			// Set locator
			iol.setM_Locator_ID(m_M_Locator_ID);
			iol.saveEx();
			//	Create Invoice Line Link
			if (il != null) {
				il.setM_InOutLine_ID(iol.getM_InOutLine_ID());
				il.saveEx();
			}
			//	Add to created
			created ++;
		}
		//	Add reference to Order / Invoice / RMA
		addReference();
		//	
		return "@Created@ " + created;
	}
	
	/**
	 * Add Reference to Order / Invoice / RMA
	 */
	private void addReference() {
		//	Valid Reference
		if(reference_ID == 0)
			return;
		if(createFromType.equals(ORDER)) {
			MOrder p_order = new MOrder(getCtx(), reference_ID, get_TrxName());
			inout.setC_Order_ID (p_order.getC_Order_ID());
			inout.setAD_OrgTrx_ID(p_order.getAD_OrgTrx_ID());
			inout.setC_Project_ID(p_order.getC_Project_ID());
			inout.setC_Campaign_ID(p_order.getC_Campaign_ID());
			inout.setC_Activity_ID(p_order.getC_Activity_ID());
			inout.setUser1_ID(p_order.getUser1_ID());
			inout.setUser2_ID(p_order.getUser2_ID());
			//	For Drop Ship
			if(p_order.isDropShip()) {
				inout.setM_Warehouse_ID( p_order.getM_Warehouse_ID() );
				inout.setIsDropShip(p_order.isDropShip());
				inout.setDropShip_BPartner_ID(p_order.getDropShip_BPartner_ID());
				inout.setDropShip_Location_ID(p_order.getDropShip_Location_ID());
				inout.setDropShip_User_ID(p_order.getDropShip_User_ID());
			}
		} else if(createFromType.equals(INVOICE)) {
			MInvoice m_invoice = new MInvoice(getCtx(), reference_ID, get_TrxName());
			if(inout.getC_Order_ID() == 0)
				inout.setC_Order_ID (m_invoice.getC_Order_ID());
			inout.setC_Invoice_ID (m_invoice.getC_Invoice_ID());
			inout.setAD_OrgTrx_ID(m_invoice.getAD_OrgTrx_ID());
			inout.setC_Project_ID(m_invoice.getC_Project_ID());
			inout.setC_Campaign_ID(m_invoice.getC_Campaign_ID());
			inout.setC_Activity_ID(m_invoice.getC_Activity_ID());
			inout.setUser1_ID(m_invoice.getUser1_ID());
			inout.setUser2_ID(m_invoice.getUser2_ID());
		} else if(createFromType.equals(RMA)) {
			MRMA m_rma = new MRMA(getCtx(), reference_ID, get_TrxName());
			MInOut originalIO = m_rma.getShipment();
			inout.setIsSOTrx(m_rma.isSOTrx());
			inout.setC_Order_ID(0);
			inout.setC_Invoice_ID(0);
			inout.setM_RMA_ID(m_rma.getM_RMA_ID());
			inout.setAD_OrgTrx_ID(originalIO.getAD_OrgTrx_ID());
			inout.setC_Project_ID(originalIO.getC_Project_ID());
			inout.setC_Campaign_ID(originalIO.getC_Campaign_ID());
			inout.setC_Activity_ID(originalIO.getC_Activity_ID());
			inout.setUser1_ID(originalIO.getUser1_ID());
			inout.setUser2_ID(originalIO.getUser2_ID());
		}
		//	Save
		inout.saveEx();
	}
}