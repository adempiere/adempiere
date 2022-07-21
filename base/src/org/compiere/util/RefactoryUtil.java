/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2003-2015 e-Evolution Consultants. All Rights Reserved.      *
 * Copyright (C) 2003-2015 Victor Pérez Juárez 								  *
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
 * Contributor(s): Victor Pérez Juárez  (victor.perez@e-evolution.com)		  *
 * Sponsors: e-Evolution Consultants (http://www.e-evolution.com/)            *
 *****************************************************************************/

package org.compiere.util;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MTable;
import org.compiere.model.PO;

/**
 * A util class for keep worked functionality
 */
public class RefactoryUtil {
	public static final int		DD_Order_Table_ID = 53037;
	public static final int		DD_OrderLine_Table_ID = 53038;
	public static final String	DD_Order_Table_Name = "DD_Order";
	public static final String	DD_OrderLine_Table_Name = "DD_OrderLine";
	
	/**
	 * Get a instance of Distribution Order
	 * @param context
	 * @param distributionOrderId
	 * @param transactionName
	 * @return
	 */
	public static PO getDistributionOrder(Properties context, int distributionOrderId, String transactionName) {
		return MTable.get(context, DD_Order_Table_ID).getPO(distributionOrderId, transactionName);
	}
	
	/**
	 * Get Instance of Distribution Order Line
	 * @param context
	 * @param distributionOrderLineId
	 * @param transactionName
	 * @return
	 */
	public static PO getDistributionOrderLine(Properties context, int distributionOrderLineId, String transactionName) {
		return MTable.get(context, DD_OrderLine_Table_ID).getPO(distributionOrderLineId, transactionName);
	}
	
	/**
	 * Get Instance of Distribution Order Line from Distribution Order
	 * @param distributionOrder
	 * @return
	 */
	public static PO getDistributionOrderLineInstanceFromParent(PO distributionOrder) {
		PO distributionOrderLine = getDistributionOrderLine(distributionOrder.getCtx(), 0, distributionOrder.get_TrxName());
		distributionOrderLine.set_ValueOfColumn("DD_Order_ID", distributionOrder.get_ID());
		distributionOrderLine.setAD_Org_ID(distributionOrder.getAD_Org_ID());
		distributionOrderLine.set_ValueOfColumn("DateOrdered", distributionOrder.get_Value("DateOrdered"));
		distributionOrderLine.set_ValueOfColumn("DatePromised", distributionOrder.get_Value("DatePromised"));
		return distributionOrderLine;
	}
	
	/**
	 * Set Business Partner Reference
	 * @param referenceToSet
	 * @param bp
	 */
	public static void setBusinessPartner(PO referenceToSet, MBPartner bp) {
		if (bp == null)
			return;

		referenceToSet.set_ValueOfColumn("C_BPartner_ID", bp.getC_BPartner_ID());
		//	Defaults Payment Term
		int ii = 0;
		if (referenceToSet.get_ValueAsBoolean("IsSOTrx"))
			ii = bp.getC_PaymentTerm_ID();
		else
			ii = bp.getPO_PaymentTerm_ID();
		
		//	Default Price List
		if (referenceToSet.get_ValueAsBoolean("IsSOTrx"))
			ii = bp.getM_PriceList_ID();
		else
			ii = bp.getPO_PriceList_ID();
		//	Default Delivery/Via Rule
		String ss = bp.getDeliveryRule();
		if (ss != null)
			referenceToSet.set_ValueOfColumn("DeliveryRule", ss);
		ss = bp.getDeliveryViaRule();
		if (ss != null)
			referenceToSet.set_ValueOfColumn("DeliveryViaRule", ss);
		//	Default Invoice/Payment Rule
		ss = bp.getInvoiceRule();

		if (referenceToSet.get_ValueAsInt("SalesRep_ID") == 0)
		{
			ii = Env.getAD_User_ID(referenceToSet.getCtx());
			if (ii != 0)
				referenceToSet.set_ValueOfColumn("SalesRep_ID", ii);
		}

		List<MBPartnerLocation> partnerLocations = Arrays.asList(bp.getLocations(false));
		// search the Ship To Location
		MBPartnerLocation partnerLocation = partnerLocations.stream() 			// create steam
				.filter( pl -> pl.isShipTo()).reduce((first , last ) -> last) 	// get of last Ship to location
				.orElseGet(() -> partnerLocations.stream() 								// if not exist Ship to location else get first partner location
							.findFirst()										// if not exist partner location then throw an exception
							.orElseThrow(() -> new AdempiereException("@IsShipTo@ @NotFound@"))
				);

		referenceToSet.set_ValueOfColumn("C_BPartner_Location_ID", partnerLocation.getC_BPartner_Location_ID());
		//	
		Arrays.asList(bp.getContacts(false))
				.stream()
				.findFirst()
				.ifPresent(user -> referenceToSet.set_ValueOfColumn("AD_User_ID", user.getAD_User_ID()));
	}
	
}    //	MovementGenerate
