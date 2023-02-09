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
package org.spin.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MProduct;
import org.compiere.model.MUOM;
import org.compiere.model.MUOMConversion;
import org.compiere.util.Env;

/**
 *	Physical Inventory Callouts
 *	
 *  @author Yamel Senih
 *  @version $Id: CalloutInventory.java,v 1.2 2006/07/30 00:51:03 jjanke Exp $
 */
public class CalloutInventory extends CalloutEngine {
	/**
	 *  Product/Locator/ASI modified.
	 * 		Set Attribute Set Instance
	 *
	 *  @param ctx      Context
	 *  @param WindowNo current Window No
	 *  @param mTab     Model Tab
	 *  @param mField   Model Field
	 *  @param value    The new value
	 *  @return Error message or ""
	 */
	public String productUom(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
		if (isCalloutActive())
			return "";
		Integer productId = (Integer)value;
		//
		if (productId == null || productId.intValue() == 0) {
			//  If the product information is deleted, zero the other items as well
			mTab.setValue("M_AttributeSetInstance_ID", null);
			mTab.setValue("QtyCount", new BigDecimal(0));
			mTab.setValue("QtyCountEntered", new BigDecimal(0));
			mTab.setValue("QtyInternalUse", new BigDecimal(0));
			mTab.setValue("QtyInternalUseEntered", new BigDecimal(0));
			mTab.setValue("C_UOM_ID", null);
			return "";
		}
		//	
		MProduct product = MProduct.get (ctx, productId.intValue());
		mTab.setValue("C_UOM_ID", Integer.valueOf(product.getC_UOM_ID()));
		//	Default calculate quantity
		return quantity(ctx, WindowNo, mTab, mField, value);
	}   //  product
	
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
	public String quantity(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
		if (isCalloutActive() || value == null)
			return "";
		int productId = Env.getContextAsInt(ctx, WindowNo, "M_Product_ID");
		//	No Product
		if (productId == 0) {
			BigDecimal qtyCountEntered = (BigDecimal) mTab.getValue("QtyCountEntered");
			BigDecimal qtyInternalUseEntered = (BigDecimal) mTab.getValue("QtyInternalUseEntered");
			mTab.setValue("QtyCount", qtyCountEntered);
			mTab.setValue("QtyInternalUse", qtyInternalUseEntered);
			return "";
		}
		//	UOM Changed - convert from Entered -> Product
		else if (mField.getColumnName().equals("C_UOM_ID")) {
			int unitOfMeasureToId = ((Integer)value).intValue();
			String columnName = null;
			String targetColumnName = null;
			BigDecimal qtyCountEntered = (BigDecimal) mTab.getValue("QtyCountEntered");
			BigDecimal qtyInternalUseEntered = (BigDecimal) mTab.getValue("QtyInternalUseEntered");
			BigDecimal QtyEntered = Env.ZERO;
			if(Optional.ofNullable(qtyCountEntered).orElse(Env.ZERO).compareTo(Env.ZERO) > 0) {
				columnName = "QtyCountEntered";
				targetColumnName = "QtyCount";
				QtyEntered = qtyCountEntered;
			} else if(Optional.ofNullable(qtyInternalUseEntered).orElse(Env.ZERO).compareTo(Env.ZERO) > 0) {
				columnName = "QtyInternalUseEntered";
				targetColumnName = "QtyInternalUse";
				QtyEntered = qtyInternalUseEntered;
			}
			BigDecimal QtyEntered1 = QtyEntered.setScale(MUOM.getPrecision(ctx, unitOfMeasureToId), RoundingMode.HALF_UP);
			if (QtyEntered.compareTo(QtyEntered1) != 0)
			{
				log.fine("Corrected QtyEntered Scale UOM=" + unitOfMeasureToId 
					+ "; QtyEntered=" + QtyEntered + "->" + QtyEntered1);  
				QtyEntered = QtyEntered1;
				mTab.setValue(columnName, QtyEntered);
			}
			BigDecimal QtyOrdered = MUOMConversion.convertProductFrom (ctx, productId, 
				unitOfMeasureToId, QtyEntered);
			if (QtyOrdered == null)
				QtyOrdered = QtyEntered;
			boolean conversion = QtyEntered.compareTo(QtyOrdered) != 0;
			log.fine("UOM=" + unitOfMeasureToId 
				+ ", QtyEntered=" + QtyEntered
				+ " -> " + conversion 
				+ " QtyOrdered=" + QtyOrdered);
			Env.setContext(ctx, WindowNo, "UOMConversion", conversion ? "Y" : "N");
			mTab.setValue(targetColumnName, QtyOrdered);
		}
		//	QtyEntered changed - calculate QtyOrdered
		else if (mField.getColumnName().equals("QtyCountEntered")) {
			int unitOfMeasureToId = Env.getContextAsInt(ctx, WindowNo, "C_UOM_ID");
			BigDecimal QtyEntered = (BigDecimal)value;
			BigDecimal QtyEntered1 = QtyEntered.setScale(MUOM.getPrecision(ctx, unitOfMeasureToId), RoundingMode.HALF_UP);
			if (QtyEntered.compareTo(QtyEntered1) != 0)
			{
				log.fine("Corrected QtyEntered Scale UOM=" + unitOfMeasureToId 
					+ "; QtyEntered=" + QtyEntered + "->" + QtyEntered1);  
				QtyEntered = QtyEntered1;
				mTab.setValue("QtyEntered", QtyEntered);
			}
			BigDecimal QtyOrdered = MUOMConversion.convertProductFrom (ctx, productId, 
				unitOfMeasureToId, QtyEntered);
			if (QtyOrdered == null)
				QtyOrdered = QtyEntered;
			boolean conversion = QtyEntered.compareTo(QtyOrdered) != 0;
			log.fine("UOM=" + unitOfMeasureToId 
				+ ", QtyCount=" + QtyEntered
				+ " -> " + conversion 
				+ " QtyCount=" + QtyOrdered);
			Env.setContext(ctx, WindowNo, "UOMConversion", conversion ? "Y" : "N");
			mTab.setValue("QtyCount", QtyOrdered);
		}
		//	QtyEntered changed - calculate QtyOrdered
		else if (mField.getColumnName().equals("QtyInternalUseEntered")) {
			int C_UOM_To_ID = Env.getContextAsInt(ctx, WindowNo, "C_UOM_ID");
			BigDecimal QtyEntered = (BigDecimal)value;
			BigDecimal QtyEntered1 = QtyEntered.setScale(MUOM.getPrecision(ctx, C_UOM_To_ID), RoundingMode.HALF_UP);
			if (QtyEntered.compareTo(QtyEntered1) != 0)
			{
				log.fine("Corrected QtyEntered Scale UOM=" + C_UOM_To_ID 
					+ "; QtyEntered=" + QtyEntered + "->" + QtyEntered1);  
				QtyEntered = QtyEntered1;
				mTab.setValue("QtyEntered", QtyEntered);
			}
			BigDecimal QtyOrdered = MUOMConversion.convertProductFrom (ctx, productId, 
				C_UOM_To_ID, QtyEntered);
			if (QtyOrdered == null)
				QtyOrdered = QtyEntered;
			boolean conversion = QtyEntered.compareTo(QtyOrdered) != 0;
			log.fine("UOM=" + C_UOM_To_ID 
				+ ", QtyInternalUseEntered=" + QtyEntered
				+ " -> " + conversion 
				+ " QtyInternalUse=" + QtyOrdered);
			Env.setContext(ctx, WindowNo, "UOMConversion", conversion ? "Y" : "N");
			mTab.setValue("QtyInternalUse", QtyOrdered);
		}
		//	QtyOrdered changed - calculate QtyEntered (should not happen)
		else if (mField.getColumnName().equals("QtyCount")) {
			int unitOfMeasureToId = Env.getContextAsInt(ctx, WindowNo, "C_UOM_ID");
			BigDecimal QtyOrdered = (BigDecimal)value;
			int precision = MProduct.get(ctx, productId).getUOMPrecision(); 
			BigDecimal QtyOrdered1 = QtyOrdered.setScale(precision, RoundingMode.HALF_UP);
			if (QtyOrdered.compareTo(QtyOrdered1) != 0) {
				log.fine("Corrected QtyOrdered Scale " 
					+ QtyOrdered + "->" + QtyOrdered1);  
				QtyOrdered = QtyOrdered1;
				mTab.setValue("QtyOrdered", QtyOrdered);
			}
			BigDecimal QtyEntered = MUOMConversion.convertProductTo (ctx, productId, 
				unitOfMeasureToId, QtyOrdered);
			if (QtyEntered == null)
				QtyEntered = QtyOrdered;
			boolean conversion = QtyOrdered.compareTo(QtyEntered) != 0;
			log.fine("UOM=" + unitOfMeasureToId 
				+ ", QtyOrdered=" + QtyOrdered
				+ " -> " + conversion 
				+ " QtyEntered=" + QtyEntered);
			Env.setContext(ctx, WindowNo, "UOMConversion", conversion ? "Y" : "N");
			mTab.setValue("QtyCountEntered", QtyEntered);
		}
		//	For Internal used
		else if (mField.getColumnName().equals("QtyInternalUse")) {
			int unitOfMeasureToId = Env.getContextAsInt(ctx, WindowNo, "C_UOM_ID");
			BigDecimal QtyOrdered = (BigDecimal)value;
			int precision = MProduct.get(ctx, productId).getUOMPrecision(); 
			BigDecimal QtyOrdered1 = QtyOrdered.setScale(precision, RoundingMode.HALF_UP);
			if (QtyOrdered.compareTo(QtyOrdered1) != 0) {
				log.fine("Corrected QtyOrdered Scale " 
					+ QtyOrdered + "->" + QtyOrdered1);  
				QtyOrdered = QtyOrdered1;
				mTab.setValue("QtyOrdered", QtyOrdered);
			}
			BigDecimal QtyEntered = MUOMConversion.convertProductTo (ctx, productId, 
				unitOfMeasureToId, QtyOrdered);
			if (QtyEntered == null)
				QtyEntered = QtyOrdered;
			boolean conversion = QtyOrdered.compareTo(QtyEntered) != 0;
			log.fine("UOM=" + unitOfMeasureToId 
				+ ", QtyOrdered=" + QtyOrdered
				+ " -> " + conversion 
				+ " QtyEntered=" + QtyEntered);
			Env.setContext(ctx, WindowNo, "UOMConversion", conversion ? "Y" : "N");
			mTab.setValue("QtyInternalUseEntered", QtyEntered);
		}
		return "";
	}	//	qty
	
}	//	CalloutInventory
