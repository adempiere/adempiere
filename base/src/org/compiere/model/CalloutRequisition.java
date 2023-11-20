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
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.core.domains.models.I_M_Requisition;
import org.adempiere.core.domains.models.I_M_RequisitionLine;
import org.adempiere.model.GridTabWrapper;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 *	Requisition Callouts
 *	
 *  @author Jorg Janke
 *  @version $Id: CalloutRequisition.java,v 1.3 2006/07/30 00:51:05 jjanke Exp $
 *  @author Michael McKay (mjmckay)
 *          <li> BF3468429 Show attribute set instance field on the requisition line
 */
public class CalloutRequisition extends CalloutEngine
{
	/**
	 *	Requisition Line - Product.
	 *		- PriceStd
	 *  @param ctx context
	 *  @param WindowNo current Window No
	 *  @param mTab Grid Tab
	 *  @param mField Grid Field
	 *  @param value New Value
	 *  @return null or error message
	 */
	public String product (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		Integer M_Product_ID = (Integer)value;
		if (M_Product_ID == null || M_Product_ID.intValue() == 0)
			return "";
		final I_M_Requisition req = GridTabWrapper.create(mTab.getParentTab(), I_M_Requisition.class);
		final I_M_RequisitionLine line = GridTabWrapper.create(mTab, I_M_RequisitionLine.class);
		setPrice(ctx, WindowNo, req, line);
		MProduct product = MProduct.get(ctx, M_Product_ID);
		line.setC_UOM_ID(product.getC_UOM_ID());
		line.setM_AttributeSetInstance_ID(product.getM_AttributeSetInstance_ID());

		return "";
	}	//	product

	/**
	 * Requisition line - Qty
	 * 	- Price, LineNetAmt
	 *  @param ctx context
	 *  @param WindowNo current Window No
	 *  @param mTab Grid Tab
	 *  @param mField Grid Field
	 *  @param value New Value
	 *  @return null or error message
	 */
	public String amt (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (isCalloutActive() || value == null)
			return "";
		
		final I_M_Requisition req = GridTabWrapper.create(mTab.getParentTab(), I_M_Requisition.class);
		final I_M_RequisitionLine line = GridTabWrapper.create(mTab, I_M_RequisitionLine.class);
		//	Qty changed - recalc price
		if (mField.getColumnName().equals(I_M_RequisitionLine.COLUMNNAME_Qty) 
			&& "Y".equals(Env.getContext(ctx, WindowNo, "DiscountSchema")))
		{
			setPrice(ctx, WindowNo, req, line);
		}

		int StdPrecision = Env.getContextAsInt(ctx, WindowNo, "StdPrecision");
		BigDecimal Qty = line.getQty();
		BigDecimal PriceActual = line.getPriceActual();
		log.fine("amt - Qty=" + Qty + ", Price=" + PriceActual + ", Precision=" + StdPrecision);

		//	Multiply
		BigDecimal LineNetAmt = Qty.multiply(PriceActual);
		if (LineNetAmt.scale() > StdPrecision)
			LineNetAmt = LineNetAmt.setScale(StdPrecision, RoundingMode.HALF_UP);
		line.setLineNetAmt(LineNetAmt);
		log.info("amt - LineNetAmt=" + LineNetAmt);
		//
		return "";
	}	//	amt

	private void setPrice(Properties ctx, int WindowNo, I_M_Requisition req, I_M_RequisitionLine line)
	{
		int C_BPartner_ID = line.getC_BPartner_ID();
		BigDecimal Qty = line.getQty();
		boolean isSOTrx = false;
		MProductPricing pp = new MProductPricing (line.getM_Product_ID(), C_BPartner_ID, Qty, isSOTrx, null);
		//
		int M_PriceList_ID = req.getM_PriceList_ID();
		pp.setM_PriceList_ID(M_PriceList_ID);
		int M_PriceList_Version_ID = Env.getContextAsInt(ctx, WindowNo, "M_PriceList_Version_ID");
		pp.setM_PriceList_Version_ID(M_PriceList_Version_ID);
		Timestamp orderDate = req.getDateRequired();
		pp.setPriceDate(orderDate);
		//
		line.setPriceActual(pp.getPriceStd());
		Env.setContext(ctx, WindowNo, "EnforcePriceLimit", pp.isEnforcePriceLimit() ? "Y" : "N");	//	not used
		Env.setContext(ctx, WindowNo, "DiscountSchema", pp.isDiscountSchema() ? "Y" : "N");
	}

	/**
	 *
	 * @param ctx
	 * @param windowNo
	 * @param gridTab
	 * @param gridField
	 * @param value
	 * @return
	 */
	public String tax (Properties ctx, int windowNo, GridTab gridTab, GridField gridField, Object value)
	{
		String column = gridField.getColumnName();
		if (value == null)
			return "";

		//	Check Product
		int productId = 0;
		if (column.equals("M_Product_ID"))
			productId = ((Integer)value).intValue();
		else
			productId = Env.getContextAsInt(ctx, windowNo, "M_Product_ID");
		int chargeId = 0;
		if (column.equals("C_Charge_ID"))
			chargeId = ((Integer)value).intValue();
		else
			chargeId = Env.getContextAsInt(ctx, windowNo, "C_Charge_ID");
		log.fine("Product=" + productId + ", C_Charge_ID=" + chargeId);
		if (productId == 0 && chargeId == 0)
			return amt(ctx, windowNo, gridTab, gridField, value);		//

		//	Check Partner Location
		int shipPartnerLocationId = 0;
		if (column.equals("C_BPartner_Location_ID"))
			shipPartnerLocationId = ((Integer)value).intValue();
		else
			shipPartnerLocationId = Env.getContextAsInt(ctx, windowNo, "C_BPartner_Location_ID");
		if (shipPartnerLocationId == 0)
			return amt(ctx, windowNo, gridTab, gridField, value);		//
		log.fine("Ship BP_Location=" + shipPartnerLocationId);

		//
		Timestamp billDate = Env.getContextAsDate(ctx, windowNo, "DateOrdered");
		log.fine("Bill Date=" + billDate);

		Timestamp shipDate = Env.getContextAsDate(ctx, windowNo, "DatePromised");
		log.fine("Ship Date=" + shipDate);

		int orgId = Env.getContextAsInt(ctx, windowNo, "AD_Org_ID");
		log.fine("Org=" + orgId);

		int warehouseId = Env.getContextAsInt(ctx, windowNo, "M_Warehouse_ID");
		log.fine("Warehouse=" + warehouseId);

		int billPartnerLocationId = Env.getContextAsInt(ctx, windowNo, "Bill_Location_ID");
		if (billPartnerLocationId == 0)
			billPartnerLocationId = shipPartnerLocationId;
		log.fine("Bill BP_Location=" + billPartnerLocationId);

		//
		int taxId = Tax.get (ctx, productId, chargeId, billDate, shipDate,
				orgId, warehouseId, billPartnerLocationId, shipPartnerLocationId,
				"Y".equals(Env.getContext(ctx, windowNo, "IsSOTrx")), null);
		log.info("Tax ID=" + taxId);
		//
		if (taxId == 0)
			gridTab.fireDataStatusEEvent(CLogger.retrieveError());
		else
			gridTab.setValue("C_Tax_ID", Integer.valueOf(taxId));

		return amt(ctx, windowNo, gridTab, gridField, value);
	}	//	tax
}	//	CalloutRequisition
