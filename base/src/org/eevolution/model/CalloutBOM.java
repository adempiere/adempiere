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

import java.math.BigDecimal;
import java.util.Properties;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.GridTabWrapper;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MProduct;
import org.compiere.model.MUOMConversion;
import org.compiere.util.Env;


/**
 * BOM Callouts
 *	
 * @author Victor Perez www.e-evolution.com
 * @author Teo Sarca, www.arhipac.ro
 * 			<li>BF [ 2820743 ] CalloutBOM - apply ABP
 * 				https://sourceforge.net/tracker/?func=detail&aid=2820743&group_id=176962&atid=934929  
 */
public class CalloutBOM extends CalloutEngine
{
	/**
	 *	Parent cycle check and BOM Line defaults.
	 *  @param ctx      Context
	 *  @param WindowNo current Window No
	 *  @param mTab     Model Tab
	 *  @param mField   Model Field
	 *  @param value    The new value
	 */
	public String parent (Properties ctx, int WindowNo, GridTab mTab, GridField  mField, Object value)
	{
		if (isCalloutActive() || value == null)
			return "";
		final int M_Product_ID = (Integer)value;
		if (M_Product_ID <= 0)
			return "";

		I_PP_Product_BOMLine bomLine = GridTabWrapper.create(mTab, I_PP_Product_BOMLine.class);
		I_PP_Product_BOM bom = bomLine.getPP_Product_BOM();

		if( bom == null ) //Adempiere-272 changes
		{
			throw new AdempiereException("Please save header record first.");				
		}    

		if (bom.getM_Product_ID() ==  bomLine.getM_Product_ID())
		{                                                                               
			bomLine.setM_Product_ID(-1);
			return "";
		}
		// Set BOM Line defaults
		MProduct product = MProduct.get(ctx, M_Product_ID);  // May be the parent;
		bomLine.setDescription(product.getDescription());
		bomLine.setHelp(product.getHelp());
		bomLine.setC_UOM_ID(product.getC_UOM_ID());
		bomLine.setM_AttributeSetInstance_ID(product.getEnvAttributeSetInstance(ctx,WindowNo) == null ? 0 : product.getEnvAttributeSetInstance(ctx,WindowNo) );
		return "";
	}
        
    public String qtyLine (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (isCalloutActive() || value == null)
			return "";

		final I_PP_Order_BOMLine bomLine = GridTabWrapper.create(mTab, I_PP_Order_BOMLine.class);
		final int M_Product_ID = bomLine.getM_Product_ID();
		final String columnName = mField.getColumnName();
		
		//	No Product
		if (M_Product_ID <= 0)
		{
			BigDecimal QtyEntered = bomLine.getQtyEntered();
			bomLine.setQtyRequired(QtyEntered);
		}
		//	UOM Changed - convert from Entered -> Product
		//	QtyEntered changed - calculate QtyOrdered
		else if (I_PP_Order_BOMLine.COLUMNNAME_C_UOM_ID.equals(columnName)
			|| I_PP_Order_BOMLine.COLUMNNAME_QtyEntered.equals(columnName) )
		{
			final BigDecimal QtyEntered = bomLine.getQtyEntered();
			BigDecimal QtyRequired = MUOMConversion.convertProductFrom (ctx, M_Product_ID, 
					bomLine.getC_UOM_ID(), QtyEntered);
			if (QtyRequired == null) // NO Conversion Found
				QtyRequired = QtyEntered;
			boolean conversion = QtyEntered.compareTo(QtyRequired) != 0;
			Env.setContext(ctx, WindowNo, "UOMConversion", conversion);
			bomLine.setQtyRequired(QtyRequired);
		}
		return "";
	}	//	qty
    
	/**
	 *	getdefaults   
	 *  get defaults for Product (search key, name, description, help and UOM)
	 *  @param ctx      Context
	 *  @param WindowNo current Window No
	 *  @param mTab     Model Tab
	 *  @param mField   Model Field
	 *  @param value    The new value
	 */
	public String getdefaults (Properties ctx, int WindowNo, GridTab mTab, GridField  mField, Object value)
	{
		if (isCalloutActive() || value == null)
			return "";
		int M_Product_ID = (Integer)value;
		if (M_Product_ID <= 0)
			return "";
		
        MProduct product =  MProduct.get(ctx, M_Product_ID);
        I_PP_Product_BOM bom = GridTabWrapper.create(mTab, I_PP_Product_BOM.class);
        bom.setValue(product.getValue());
        bom.setName(product.getName());
        bom.setDescription(product.getDescription());
        bom.setHelp(product.getHelp());
        bom.setC_UOM_ID(product.getC_UOM_ID());
		if (product.getEnvAttributeSetInstance(ctx,WindowNo) != null)
			bom.setM_AttributeSetInstance_ID(product.getEnvAttributeSetInstance(ctx,WindowNo));
        
		return "";
	}	//	getdefaults
}	//	CalloutOrder

