/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/

package org.eevolution.model;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.model.GridTabWrapper;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.I_M_Movement;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MLocator;
import org.compiere.model.MOrg;
import org.compiere.model.MProduct;
import org.compiere.model.MStorage;
import org.compiere.model.MUOM;
import org.compiere.model.MUOMConversion;
import org.compiere.model.MWarehouse;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *	Distribution Order Callout
 *	
 *  @author Victor Perez
 *  @version $Id: CalloutOrder.java,v 1.23 2004/08/27 21:24:12 vpj-cd Exp $
 */
public class CalloutDistributionOrder extends CalloutEngine
{
	/**	Debug Steps			*/
	private boolean steps = false;

	/**
	 *	Order Line - Quantity.
	 *		- called from C_UOM_ID, QtyEntered, QtyOrdered
	 *		- enforces qty UOM relationship
	 *  @param ctx context
	 *  @param WindowNo current Window No
	 *  @param mTab Grid Tab
	 *  @param mField Grid Field
	 *  @param value New Value
	 *  @return null or error message
	 */
	public String qty (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (isCalloutActive() || value == null)
			return "";
		int M_Product_ID = Env.getContextAsInt(ctx, WindowNo, "M_Product_ID");
		if (steps) log.warning("init - M_Product_ID=" + M_Product_ID + " - " );
		BigDecimal QtyOrdered = Env.ZERO;
		BigDecimal QtyEntered;
		
		//	No Product
		if (M_Product_ID == 0)
		{
			return "";
		}
		//	UOM Changed - convert from Entered -> Product
		else if (mField.getColumnName().equals("C_UOM_ID"))
		{
			int C_UOM_To_ID = ((Integer)value).intValue();
			QtyEntered = (BigDecimal)mTab.getValue("QtyEntered");
			BigDecimal QtyEntered1 = QtyEntered.setScale(MUOM.getPrecision(ctx, C_UOM_To_ID), BigDecimal.ROUND_HALF_UP);
			if (QtyEntered.compareTo(QtyEntered1) != 0)
			{
				log.fine("Corrected QtyEntered Scale UOM=" + C_UOM_To_ID 
					+ "; QtyEntered=" + QtyEntered + "->" + QtyEntered1);  
				QtyEntered = QtyEntered1;
				mTab.setValue("QtyEntered", QtyEntered);
			}
			QtyOrdered = MUOMConversion.convertProductFrom (ctx, M_Product_ID, 
				C_UOM_To_ID, QtyEntered);
			if (QtyOrdered == null)
				QtyOrdered = QtyEntered;
			boolean conversion = QtyEntered.compareTo(QtyOrdered) != 0;
			Env.setContext(ctx, WindowNo, "UOMConversion", conversion ? "Y" : "N");
			mTab.setValue("QtyOrdered", QtyOrdered);
		}
		//	QtyEntered changed - calculate QtyOrdered
		else if (mField.getColumnName().equals("QtyEntered"))
		{
			int C_UOM_To_ID = Env.getContextAsInt(ctx, WindowNo, "C_UOM_ID");
			QtyEntered = (BigDecimal)value;
			BigDecimal QtyEntered1 = QtyEntered.setScale(MUOM.getPrecision(ctx, C_UOM_To_ID), BigDecimal.ROUND_HALF_UP);
			if (QtyEntered.compareTo(QtyEntered1) != 0)
			{
				log.fine("Corrected QtyEntered Scale UOM=" + C_UOM_To_ID 
					+ "; QtyEntered=" + QtyEntered + "->" + QtyEntered1);  
				QtyEntered = QtyEntered1;
				mTab.setValue("QtyEntered", QtyEntered);
			}
			QtyOrdered = MUOMConversion.convertProductFrom (ctx, M_Product_ID, 
				C_UOM_To_ID, QtyEntered);
			if (QtyOrdered == null)
				QtyOrdered = QtyEntered;
			boolean conversion = QtyEntered.compareTo(QtyOrdered) != 0;
			log.fine("UOM=" + C_UOM_To_ID 
				+ ", QtyEntered=" + QtyEntered
				+ " -> " + conversion 
				+ " QtyOrdered=" + QtyOrdered);
			Env.setContext(ctx, WindowNo, "UOMConversion", conversion ? "Y" : "N");
			mTab.setValue("QtyOrdered", QtyOrdered);
		}
		//	QtyOrdered changed - calculate QtyEntered (should not happen)
		else if (mField.getColumnName().equals("QtyOrdered"))
		{
			int C_UOM_To_ID = Env.getContextAsInt(ctx, WindowNo, "C_UOM_ID");
			QtyOrdered = (BigDecimal)value;
			int precision = MProduct.get(ctx, M_Product_ID).getUOMPrecision(); 
			BigDecimal QtyOrdered1 = QtyOrdered.setScale(precision, BigDecimal.ROUND_HALF_UP);
			if (QtyOrdered.compareTo(QtyOrdered1) != 0)
			{
				log.fine("Corrected QtyOrdered Scale " 
					+ QtyOrdered + "->" + QtyOrdered1);  
				QtyOrdered = QtyOrdered1;
				mTab.setValue("QtyOrdered", QtyOrdered);
			}
			QtyEntered = MUOMConversion.convertProductTo (ctx, M_Product_ID, 
				C_UOM_To_ID, QtyOrdered);
			if (QtyEntered == null)
				QtyEntered = QtyOrdered;
			boolean conversion = QtyOrdered.compareTo(QtyEntered) != 0;
			log.fine("UOM=" + C_UOM_To_ID 
				+ ", QtyOrdered=" + QtyOrdered
				+ " -> " + conversion 
				+ " QtyEntered=" + QtyEntered);
			Env.setContext(ctx, WindowNo, "UOMConversion", conversion ? "Y" : "N");
			mTab.setValue("QtyEntered", QtyEntered);
		}
		else
		{
		//	QtyEntered = (BigDecimal)mTab.getValue("QtyEntered");
			QtyOrdered = (BigDecimal)mTab.getValue("QtyOrdered");
		}
		
		//	Storage
		if (M_Product_ID != 0 
			&& Env.isSOTrx(ctx, WindowNo)
			&& QtyOrdered.signum() > 0)		//	no negative (returns)
		{
			MProduct product = MProduct.get (ctx, M_Product_ID);
			if (product.isStocked())
			{
				int M_Locator_ID = Env.getContextAsInt(ctx, WindowNo, "M_Locator_ID");
				int M_AttributeSetInstance_ID = Env.getContextAsInt(ctx, WindowNo, "M_AttributeSetInstance_ID");
				int M_Warehouse_ID = MLocator.get(ctx, M_Locator_ID).getM_Warehouse_ID();
				
				BigDecimal available = MStorage.getQtyAvailable
					(M_Warehouse_ID, 0, M_Product_ID, M_AttributeSetInstance_ID, null);
				if (available == null)
					available = Env.ZERO;
				if (available.signum() == 0)
					mTab.fireDataStatusEEvent ("NoQtyAvailable", "0", false);
				else if (available.compareTo(QtyOrdered) < 0)
					mTab.fireDataStatusEEvent ("InsufficientQtyAvailable", available.toString(), false);
				else
				{
					Integer DD_OrderLine_ID = (Integer)mTab.getValue("DD_OrderLine_ID");
					if (DD_OrderLine_ID == null)
						DD_OrderLine_ID = new Integer(0);
					BigDecimal notReserved = MDDOrderLine.getNotReserved(ctx, 
						M_Locator_ID, M_Product_ID, M_AttributeSetInstance_ID,
						DD_OrderLine_ID.intValue());
					if (notReserved == null)
						notReserved = Env.ZERO;
					BigDecimal total = available.subtract(notReserved);
					if (total.compareTo(QtyOrdered) < 0)
					{
						String info = Msg.parseTranslation(ctx, "@QtyAvailable@=" + available 
							+ "  -  @QtyNotReserved@=" + notReserved + "  =  " + total);
						mTab.fireDataStatusEEvent ("InsufficientQtyAvailable", 
							info, false);
					}
				}
			}
		}
		//
		return "";
	}	//	qty

	/**
	 * Validate that ConfirmedQty is minor that QtyToDeliver
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return 
	 */
	public String qtyConfirmed (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		I_DD_OrderLine line = GridTabWrapper.create(mTab, I_DD_OrderLine.class);		
			
		if (line.getConfirmedQty().compareTo(line.getQtyOrdered().subtract(line.getQtyInTransit()).subtract(line.getQtyDelivered())) > 0)
		{
			String info =Msg.parseTranslation(ctx, "@ConfirmedQty@ : "+line.getConfirmedQty()+" > @QtyToDeliver@ : " +  line.getQtyOrdered().subtract(line.getQtyInTransit()).subtract(line.getQtyDelivered()));
			mTab.fireDataStatusEEvent ("", info, false);
			line.setConfirmedQty(line.getQtyOrdered().subtract(line.getQtyInTransit()).subtract(line.getQtyDelivered()));
		}
		return "";		
	}	
	

	/**
	 * Set Default Locator To
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 */
	public String setLocatorTo (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{

		I_DD_OrderLine line = GridTabWrapper.create(mTab, I_DD_OrderLine.class);
		if(value != null)
		{
			MProduct product = MProduct.get(ctx, (Integer)value);
			if(line.getC_UOM_ID() <= 0)
			{
				line.setC_UOM_ID(product.getC_UOM_ID());
			}
		}
		
		MWarehouse[] ws = MWarehouse.getForOrg(ctx, line.getAD_Org_ID());
		if(ws == null && ws.length < 0)
		{
			return "";
		}
		MLocator locator_to = MLocator.getDefault(ws[0]);
		if(locator_to != null)
		{	
			line.setM_LocatorTo_ID(locator_to.getM_Locator_ID());
		}	
		return "";		
	}
	
	
/**
 * 
 * 
 * @param ctx
 * @param WindowNo
 * @param mTab
 * @param mField
 * @param value
 * @return
 */
	public String UOM(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		
		I_DD_OrderLine line = GridTabWrapper.create(mTab, I_DD_OrderLine.class);
		
		MProduct product = MProduct.get(ctx ,line.getM_Product_ID());
		if(product !=null)
		{
			line.setC_UOM_ID(product.getC_UOM_ID());
		}
		return "";		
	}
	
	public String bPartner (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		I_DD_Order order = GridTabWrapper.create(mTab, I_DD_Order.class);		
		MOrg org = MOrg.get(ctx,order.getAD_Org_ID());
		int C_BPartner_ID = org.getLinkedC_BPartner_ID(null);
		
		if(C_BPartner_ID > 0)
		{
			MBPartnerLocation[] locations = MBPartnerLocation.getForBPartner(ctx, C_BPartner_ID, null);		
			order.setC_BPartner_ID(C_BPartner_ID);
			if(locations.length > 0)
			{
				order.setC_BPartner_Location_ID(locations[0].getC_BPartner_Location_ID());
			}
		}
		return "";
	}	
}	//	Callout Distribution Order

