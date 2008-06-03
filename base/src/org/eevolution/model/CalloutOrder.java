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
import java.math.RoundingMode;
import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MUOMConversion;
import org.compiere.util.Env;
import org.compiere.wf.MWorkflow;

/**
 *	Order CalloutOrder
 *	
 *  @author Victor Perez
 *  @version $Id: CalloutOrder.java,v 1.23 2004/08/27 21:24:12 vpj-cd Exp $
 *  
 *  @author Teo Sarca, SC ARHIPAC SERVICE SRL
 *  		<li>BF [ 1983657 ] "Data found" error on creating new MO (manually)
 */
public class CalloutOrder extends CalloutEngine
{
	/**	Debug Steps			*/
	private boolean steps = false;

	/**
	 *	Order Line - Quantity.
	 *		- called from C_UOM_ID, QtyEntered, QtyOrdered
	 *		- enforces qty UOM relationship
	 *  @param ctx      Context
	 *  @param WindowNo current Window No
	 *  @param mTab     Model Tab
	 *  @param mField   Model Field
	 *  @param value    The new value
	 */
	public String qty (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (value == null)
			return "";
		//setCalloutActive(true);

		int M_Product_ID = Env.getContextAsInt(ctx, WindowNo, "M_Product_ID");
		if (steps) log.warning("qty - init - M_Product_ID=" + M_Product_ID + " - " );
		BigDecimal QtyOrdered = Env.ZERO ; 
		BigDecimal QtyEntered = Env.ZERO ; //, PriceActual, PriceEntered;

		//	No Product
		if (M_Product_ID == 0)
		{
			QtyEntered = (BigDecimal)mTab.getValue("QtyEntered");
			mTab.setValue("QtyOrdered", QtyEntered);
		}
		//	UOM Changed - convert from Entered -> Product
		else if (mField.getColumnName().equals("C_UOM_ID"))
		{

			int C_UOM_To_ID = ((Integer)value).intValue();
			QtyEntered = (BigDecimal)mTab.getValue("QtyEntered");
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
			QtyOrdered = MUOMConversion.convertProductFrom (ctx, M_Product_ID, 
					C_UOM_To_ID, QtyEntered);
			if (QtyOrdered == null)
				QtyOrdered = QtyEntered;
			boolean conversion = QtyEntered.compareTo(QtyOrdered) != 0;
			log.fine("qty - UOM=" + C_UOM_To_ID 
					+ ", QtyEntered=" + QtyEntered
					+ " -> " + conversion 
					+ " QtyOrdered=" + QtyOrdered);
			Env.setContext(ctx, WindowNo, "UOMConversion", conversion ? "Y" : "N");
			mTab.setValue("QtyOrdered", QtyOrdered);
		}
		//	QtyOrdered changed - calculate QtyEntered
		else if (mField.getColumnName().equals("QtyOrdered"))
		{
			int C_UOM_To_ID = Env.getContextAsInt(ctx, WindowNo, "C_UOM_ID");
			QtyOrdered = (BigDecimal)value;
			QtyEntered = MUOMConversion.convertProductTo (ctx, M_Product_ID, 
					C_UOM_To_ID, QtyOrdered);
			if (QtyEntered == null)
				QtyEntered = QtyOrdered;
			boolean conversion = QtyOrdered.compareTo(QtyEntered) != 0;
			log.fine("qty - UOM=" + C_UOM_To_ID 
					+ ", QtyOrdered=" + QtyOrdered
					+ " -> " + conversion 
					+ " QtyEntered=" + QtyEntered);
			Env.setContext(ctx, WindowNo, "UOMConversion", conversion ? "Y" : "N");
			mTab.setValue("QtyEntered", QtyEntered);
		}

		/*String DocStatus = (String) mTab.getValue("DocStatus");
		if (!DocStatus.equals(MPPOrder.STATUS_Completed))
        {               

                    Integer PP_Order_ID = (Integer)mTab.getValue("PP_Order_ID");
                    if (PP_Order_ID==null)
                		return "";

                    QtyOrdered = ((BigDecimal)mTab.getValue("QtyOrdered"));
                    MPPOrder order = new  MPPOrder(ctx, PP_Order_ID,null);
                    MPPOrderBOMLine[] obl = MPPOrder.getLines(PP_Order_ID);
                    for (int i = 0 ; i < obl.length ; i ++) 
                    { 
                    	order.setBOMLineQtys(obl[i]);
                    	obl[i].save(null);
                    }                    
        }*/

		return qtyBatch(ctx,WindowNo,mTab,mField,value);
		//return "";
	}	//	qty

	public String qtyBatch (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		Integer AD_Workflow_ID = ((Integer)mTab.getValue(MPPOrder.COLUMNNAME_AD_Workflow_ID));
		// No workflow entered, or is just a new record:
		if (AD_Workflow_ID == null)
			return "";

		BigDecimal p_QtyEntered = (BigDecimal)mTab.getValue(MPPOrder.COLUMNNAME_QtyEntered);
		if (p_QtyEntered.equals(Env.ZERO))
			return ""; 

		MWorkflow wf = MWorkflow.get(ctx , AD_Workflow_ID.intValue());
		BigDecimal Qty = null;
		BigDecimal QtyBatchSize = wf.getQtyBatchSize().setScale(0, RoundingMode.UP); 

		if (QtyBatchSize.equals(Env.ZERO))
			Qty = Env.ONE;
		else   
			Qty = p_QtyEntered.divide(QtyBatchSize , 0, BigDecimal.ROUND_UP); 

		mTab.setValue(MPPOrder.COLUMNNAME_QtyBatchs, Qty);
		mTab.setValue(MPPOrder.COLUMNNAME_QtyBatchSize, p_QtyEntered.divide(Qty , BigDecimal.ROUND_HALF_UP));

		return "";
	}

}	//	CalloutOrder

