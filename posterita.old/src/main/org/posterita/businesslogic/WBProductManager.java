/**
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2007  Posterita Ltd
 *  This file is part of POSterita
 *  
 *  POSterita is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *  
 **/
package org.posterita.businesslogic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.compiere.model.MAttribute;
import org.compiere.model.MAttributeValue;
import org.compiere.model.MProduct;
import org.compiere.util.DB;
import org.compiere.util.KeyNamePair;
import org.posterita.beans.WBProductBean;
import org.posterita.core.KeyNamePairUtil;
import org.posterita.exceptions.OperationException;
import org.posterita.factory.GenericProductAttributeFactory;
import org.posterita.model.UDIMProduct;

public class WBProductManager extends ProductManager
{
	
	private static void updateProductAttributes(Properties ctx, int productId, WBProductBean wbProdBean, String trxName) throws OperationException
	{
		MProduct product = new MProduct(ctx, productId, trxName);
		if(product.get_ID() == 0)
			throw new OperationException("Could not load product with id: " + productId);
		
		String newProductName = getProductName(wbProdBean);
		
		product.setName(newProductName);
		product.setValue(newProductName);
		product.setDescription(wbProdBean.getDesignName());
		
		UDIMProduct udiProduct = new UDIMProduct(product);
		udiProduct.save();
	}
	

	public static void updateProductsAttribute(Properties ctx, int oldAttrValueId, int newAttrValueId, int attrId, String trxName) throws OperationException
	{
		String whereclause = "M_AttributeSetInstance_ID in ";
		whereclause += " (select M_AttributeSetInstance_ID from M_AttributeInstance where M_AttributeValue_ID=" + oldAttrValueId;
		whereclause += " and M_Attribute_ID=" + attrId + ")";
		
		MAttributeValue mOldAttributeValue = new MAttributeValue(ctx, oldAttrValueId, trxName);
		if(mOldAttributeValue.get_ID() == 0)
			throw new OperationException("Could not load Attribute Value with id: " + oldAttrValueId);
		
		MAttributeValue mNewAttributeValue = new MAttributeValue(ctx, newAttrValueId, trxName);
		if(mNewAttributeValue.get_ID() == 0)
			throw new OperationException("Could not load Attribute Value with id: " + newAttrValueId);
		MAttribute attribute = new MAttribute(ctx, attrId, trxName);
		if(attribute.get_ID() == 0)
			throw new OperationException("Could not load Attribute with id: " + attrId);
		
		
		String newAttrValue = mNewAttributeValue.getName();
		
		int brandAttrId = GenericProductAttributeFactory.getFactoryInstance().get(ctx, GenericProductAttributeFactory.ATTRIBUTE_BRAND_ID).getID();
		int designAttrId = GenericProductAttributeFactory.getFactoryInstance().get(ctx, GenericProductAttributeFactory.ATTRIBUTE_DESIGN_ID).getID();
		int modelAttrId = GenericProductAttributeFactory.getFactoryInstance().get(ctx, GenericProductAttributeFactory.ATTRIBUTE_MODEL_ID).getID();
		int colourAttrId = GenericProductAttributeFactory.getFactoryInstance().get(ctx, GenericProductAttributeFactory.ATTRIBUTE_COLOUR_ID).getID();
		int sizeAttrId = GenericProductAttributeFactory.getFactoryInstance().get(ctx, GenericProductAttributeFactory.ATTRIBUTE_SIZE_ID).getID();
		
		try
		{
			ArrayList productKeyNamePairList = KeyNamePairUtil.getData(ctx, MProduct.Table_Name, whereclause);
			
			Iterator productIter = productKeyNamePairList.iterator();
			while(productIter.hasNext())
			{
				KeyNamePair productKeyNamePair = (KeyNamePair)productIter.next();
				
				WBProductBean prodBean = getProductBean(productKeyNamePair.getName());
				
				if(attrId == brandAttrId)
					prodBean.setBrandName(newAttrValue);
				else if(attrId == designAttrId)
					prodBean.setDesignName(newAttrValue);
				else if(attrId == modelAttrId)
					prodBean.setModelName(newAttrValue);
				else if(attrId == colourAttrId)
					prodBean.setColourName(newAttrValue);
				else if(attrId == sizeAttrId)
					prodBean.setSizeName(newAttrValue);
				else
					throw new OperationException("Unknown Attribute with id: " + attrId);
				
				updateProductAttributes(ctx, productKeyNamePair.getKey(), prodBean, trxName);
			}
		}
		catch(SQLException ex)
		{
			throw new OperationException("Could not retrieve products with whereclause: " + whereclause, ex);
		}
		
		if(oldAttrValueId != newAttrValueId)
		{
			// Update statement used because cannot load MAttributeInstance with constructor...
			String updateStmt = "Update M_AttributeInstance set M_AttributeValue_ID=" + newAttrValueId;
			updateStmt += " where M_AttributeValue_ID=" + oldAttrValueId + " and M_Attribute_ID=" + attrId;
			
			int result = DB.executeUpdate(updateStmt, trxName);
			if(result == -1)
				throw new OperationException("Could not update AttributeInstance table with statement: " + updateStmt);
		}
		
	}
	
	
}
