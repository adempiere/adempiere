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

import org.adempiere.core.domains.models.I_M_Requisition;
import org.adempiere.core.domains.models.I_M_RequisitionLine;
import org.adempiere.model.GridTabWrapper;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

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
	 * Business Partner
	 * @param ctx
	 * @param windowNo
	 * @param gridTab
	 * @param gridField
	 * @param value
	 * @return
	 */
	public String businessPartner (Properties ctx, int windowNo, GridTab gridTab, GridField gridField, Object value)
	{
		Integer partnerId = (Integer)value;
		if (partnerId == null || partnerId.intValue() == 0)
			return "";

		String sql = "SELECT lship.C_BPartner_Location_ID "
				+ "FROM C_BPartner p "
				+ "LEFT OUTER JOIN C_BPartner_Location lship ON (p.C_BPartner_ID=lship.C_BPartner_ID AND lship.IsShipTo=? AND lship.IsActive=?) "
				+ "WHERE p.C_BPartner_ID=? AND p.IsActive=?";

		boolean IsSOTrx = "Y".equals(Env.getContext(ctx, windowNo, "IsSOTrx"));
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try
		{
			preparedStatement = DB.prepareStatement(sql, null);
			preparedStatement.setString(1, "Y");
			preparedStatement.setString(2, "Y");
			preparedStatement.setInt(3, partnerId.intValue());
			preparedStatement.setString(4, "Y");
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
			{
				// Ship-To Location
				int shipToId = resultSet.getInt("C_BPartner_Location_ID");
				if (partnerId.toString().equals(Env.getContext(ctx, windowNo, Env.TAB_INFO, "C_BPartner_ID")))
				{
					String locationId = Env.getContext(ctx, windowNo, Env.TAB_INFO, "C_BPartner_Location_ID");
					if (locationId.length() > 0)
						shipToId = Integer.parseInt(locationId);
				}
				if (shipToId == 0)
					gridTab.setValue("C_BPartner_Location_ID", null);
				else
					gridTab.setValue("C_BPartner_Location_ID", Integer.valueOf(shipToId));
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
			return e.getLocalizedMessage();
		}
		finally
		{
			DB.close(resultSet, preparedStatement);
			resultSet = null; preparedStatement = null;
		}
		return "";
	}	//	bPartner


	/**
	 *	Requisition Line - Product.
	 *		- PriceStd
	 *  @param ctx context
	 *  @param windowNo current Window No
	 *  @param gridTab Grid Tab
	 *  @param gridField Grid Field
	 *  @param value New Value
	 *  @return null or error message
	 */
	public String product (Properties ctx, int windowNo, GridTab gridTab, GridField gridField, Object value)
	{
		Integer productId = (Integer)value;
		if (productId == null || productId.intValue() == 0)
			return "";
		final I_M_Requisition requisition = GridTabWrapper.create(gridTab.getParentTab(), I_M_Requisition.class);
		final I_M_RequisitionLine requisitionLine = GridTabWrapper.create(gridTab, I_M_RequisitionLine.class);
		setPrice(ctx, windowNo, requisition, requisitionLine);
		MProduct product = MProduct.get(ctx, productId);
		requisitionLine.setC_UOM_ID(product.getC_UOM_ID());
		requisitionLine.setM_AttributeSetInstance_ID(product.getM_AttributeSetInstance_ID());
		tax(ctx,windowNo, gridTab, gridField, value);
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
		
		final I_M_Requisition requisition = GridTabWrapper.create(mTab.getParentTab(), I_M_Requisition.class);
		final I_M_RequisitionLine requisitionLine = GridTabWrapper.create(mTab, I_M_RequisitionLine.class);
		//	Qty changed - recalc price
		if (mField.getColumnName().equals(I_M_RequisitionLine.COLUMNNAME_Qty) 
			&& "Y".equals(Env.getContext(ctx, WindowNo, "DiscountSchema")))
		{
			setPrice(ctx, WindowNo, requisition, requisitionLine);
		}

		int stdPrecision = Env.getContextAsInt(ctx, WindowNo, "StdPrecision");
		BigDecimal qty = requisitionLine.getQty();
		BigDecimal priceActual = requisitionLine.getPriceActual();
		log.fine("amt - Qty=" + qty + ", Price=" + priceActual + ", Precision=" + stdPrecision);

		//	Multiply
		BigDecimal lineNetAmt = qty.multiply(priceActual);
		if (lineNetAmt.scale() > stdPrecision)
			lineNetAmt = lineNetAmt.setScale(stdPrecision, RoundingMode.HALF_UP);
		requisitionLine.setLineNetAmt(lineNetAmt);
		log.info("amt - LineNetAmt=" + lineNetAmt);
		//
		return "";
	}	//	amt

	private void setPrice(Properties ctx, int WindowNo, I_M_Requisition req, I_M_RequisitionLine line)
	{
		int partnerId = line.getC_BPartner_ID();
		BigDecimal qty = line.getQty();
		boolean isSOTrx = false;
		MProductPricing productPricing = new MProductPricing (line.getM_Product_ID(), partnerId, qty, isSOTrx, null);
		//
		int priceListId = req.getM_PriceList_ID();
		productPricing.setM_PriceList_ID(priceListId);
		int priceListVersionId = Env.getContextAsInt(ctx, WindowNo, "M_PriceList_Version_ID");
		productPricing.setM_PriceList_Version_ID(priceListVersionId);
		Timestamp orderDate = req.getDateRequired();
		productPricing.setPriceDate(orderDate);
		//
		line.setPriceActual(productPricing.getPriceStd());
		Env.setContext(ctx, WindowNo, "EnforcePriceLimit", productPricing.isEnforcePriceLimit() ? "Y" : "N");	//	not used
		Env.setContext(ctx, WindowNo, "DiscountSchema", productPricing.isDiscountSchema() ? "Y" : "N");
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
		if (isCalloutActive() || value == null)
			return "";

		String column = gridField.getColumnName();
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
