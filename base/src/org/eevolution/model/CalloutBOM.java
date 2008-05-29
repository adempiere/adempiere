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
//package org.compiere.mfg.model;
package org.eevolution.model;

import java.math.*;
import java.util.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.tree.*;

import java.math.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import org.compiere.util.*;
import org.compiere.model.*;


/**
 *	Order Callouts.
 *	
 *  @author Victor Perez www.e-evolution.com  
 *  @version $Id: CalloutBOM.java,v 1.11 2004/03/22 07:15:03 vpj-cd Exp $
 */
public class CalloutBOM extends CalloutEngine
{
	/**	Debug Steps			*/
	private boolean steps = false;

	/**
	 *	Order Header Change - DocType.
	 *		- InvoiceRuld/DeliveryRule/PaymentRule
	 *		- temporary Document
	 *  Context:
	 *  	- DocSubTypeSO
	 *		- HasCharges
	 *	- (re-sets Business Partner info of required)
	 *
	 *  @param ctx      Context
	 *  @param WindowNo current Window No
	 *  @param mTab     Model Tab
	 *  @param mField   Model Field
	 *  @param value    The new value
	 *  @return Error message or ""
	 */



	/**
	 *	Parent cicle.
	 *  @param ctx      Context
	 *  @param WindowNo current Window No
	 *  @param mTab     Model Tab
	 *  @param mField   Model Field
	 *  @param value    The new value
	 */
	public String parent (Properties ctx, int WindowNo, GridTab mTab, GridField  mField, Object value)
	{
		Integer M_Product_ID = (Integer)value;
		if (M_Product_ID == null || M_Product_ID.intValue() == 0)
			return "";
		setCalloutActive(true);
		
		if (isCalloutActive() || value == null)
			return "";
		

		if (steps) log.warning("parent - init");
		
		setCalloutActive(true);

        int PP_Product_BOM_ID = Env.getContextAsInt(ctx, WindowNo, "PP_Product_BOM_ID");
        X_PP_Product_BOM PP_Product_BOM = new X_PP_Product_BOM(ctx, PP_Product_BOM_ID,"PP_Product_BOM");
        if (PP_Product_BOM.getM_Product_ID() ==  M_Product_ID.intValue())
        {                                                                               
             JOptionPane.showMessageDialog(null,"ValidComponent" , "Error Parent not be Componet" , JOptionPane.ERROR_MESSAGE);				
             return ""; 
        }
        setCalloutActive(false);
		return "";
	}	//	amt
        
    public String qty (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (isCalloutActive() || value == null)
			return "";
		setCalloutActive(true);

		int M_Product_ID = Env.getContextAsInt(ctx, WindowNo, "M_Product_ID");
		if (steps) log.warning("qty - init - M_Product_ID=" + M_Product_ID + " - " );
		BigDecimal QtyOrdered, QtyEntered; //, PriceActual, PriceEntered;
		
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
			//PriceActual = (BigDecimal)mTab.getValue("PriceActual");
			//PriceEntered = MUOMConversion.convertProductFrom (ctx, M_Product_ID, 
			//	C_UOM_To_ID, PriceActual);
			//if (PriceEntered == null)
			//	PriceEntered = PriceActual; 
		//	log.fine("qty - UOM=" + C_UOM_To_ID 
		//		+ ", QtyEntered/PriceActual=" + QtyEntered + "/" + PriceActual
		//		+ " -> " + conversion 
		//		+ " QtyOrdered/PriceEntered=" + QtyOrdered + "/" + PriceEntered);
			Env.setContext(ctx, WindowNo, "UOMConversion", conversion ? "Y" : "N");
			mTab.setValue("QtyOrdered", QtyOrdered);
		//	mTab.setValue("PriceEntered", PriceEntered);
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
		//
		setCalloutActive(false);
		return "";
	}	//	qty
        
    public String qtyLine (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (isCalloutActive() || value == null)
			return "";
		setCalloutActive(true);

		int M_Product_ID = Env.getContextAsInt(ctx, WindowNo, "M_Product_ID");
		if (steps) log.warning("qty - init - M_Product_ID=" + M_Product_ID + " - " );
		BigDecimal QtyRequiered, QtyEntered; //, PriceActual, PriceEntered;
		
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
			QtyRequiered = MUOMConversion.convertProductFrom (ctx, M_Product_ID, 
				C_UOM_To_ID, QtyEntered);
			if (QtyRequiered == null)
				QtyRequiered = QtyEntered;
			boolean conversion = QtyEntered.compareTo(QtyRequiered) != 0;
			//PriceActual = (BigDecimal)mTab.getValue("PriceActual");
			//PriceEntered = MUOMConversion.convertProductFrom (ctx, M_Product_ID, 
			//	C_UOM_To_ID, PriceActual);
			//if (PriceEntered == null)
			//	PriceEntered = PriceActual; 
		//	log.fine("qty - UOM=" + C_UOM_To_ID 
		//		+ ", QtyEntered/PriceActual=" + QtyEntered + "/" + PriceActual
		//		+ " -> " + conversion 
		//		+ " QtyOrdered/PriceEntered=" + QtyOrdered + "/" + PriceEntered);
			Env.setContext(ctx, WindowNo, "UOMConversion", conversion ? "Y" : "N");
			mTab.setValue("QtyOrdered", QtyRequiered);
		//	mTab.setValue("PriceEntered", PriceEntered);
		}
		//	QtyEntered changed - calculate QtyOrdered
		else if (mField.getColumnName().equals("QtyEntered"))
		{
			int C_UOM_To_ID = Env.getContextAsInt(ctx, WindowNo, "C_UOM_ID");
			QtyEntered = (BigDecimal)value;
			QtyRequiered = MUOMConversion.convertProductFrom (ctx, M_Product_ID, 
				C_UOM_To_ID, QtyEntered);
			if (QtyRequiered == null)
				QtyRequiered = QtyEntered;
			boolean conversion = QtyEntered.compareTo(QtyRequiered) != 0;
			log.fine("qty - UOM=" + C_UOM_To_ID 
				+ ", QtyEntered=" + QtyEntered
				+ " -> " + conversion 
				+ " QtyOrdered=" + QtyRequiered);
			Env.setContext(ctx, WindowNo, "UOMConversion", conversion ? "Y" : "N");
			mTab.setValue("QtyOrdered", QtyRequiered);
		}
		//	QtyOrdered changed - calculate QtyEntered
		else if (mField.getColumnName().equals("QtyOrdered"))
		{
			int C_UOM_To_ID = Env.getContextAsInt(ctx, WindowNo, "C_UOM_ID");
			QtyRequiered = (BigDecimal)value;
			QtyEntered = MUOMConversion.convertProductTo (ctx, M_Product_ID, 
				C_UOM_To_ID, QtyRequiered);
			if (QtyEntered == null)
				QtyEntered = QtyRequiered;
			boolean conversion = QtyRequiered.compareTo(QtyEntered) != 0;
			log.fine("qty - UOM=" + C_UOM_To_ID 
				+ ", QtyOrdered=" + QtyRequiered
				+ " -> " + conversion 
				+ " QtyEntered=" + QtyEntered);
			Env.setContext(ctx, WindowNo, "UOMConversion", conversion ? "Y" : "N");
			mTab.setValue("QtyEntered", QtyEntered);
		}
		//
		setCalloutActive(false);
		return "";
	}	//	qty

}	//	CalloutOrder

