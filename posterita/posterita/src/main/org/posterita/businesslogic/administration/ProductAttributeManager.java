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
 * Created on Jul 28, 2005 by praveen
 *
 */
package org.posterita.businesslogic.administration;

import java.util.ArrayList;
import java.util.Properties;

import org.compiere.model.MAttribute;
import org.compiere.model.MAttributeSet;
import org.compiere.model.MAttributeUse;
import org.compiere.model.MAttributeValue;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.posterita.beans.AttributeValueDetailBean;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.ProductInStockException;
import org.posterita.exceptions.SystemException;
import org.posterita.util.PoManager;


public class ProductAttributeManager 
{
    public static ArrayList<KeyNamePair> getProductAttributes(Properties ctx, int attributeSetId) throws OperationException, SystemException
    {
        MAttributeSet attributeSet = new MAttributeSet(ctx, attributeSetId, null);
          
        MAttribute[] attributes = attributeSet.getMAttributes(false);
     
        KeyNamePair pair;
        ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
        for (int i = 0; i< attributes.length; i++)
        {
            pair = new KeyNamePair(attributes[i].get_ID(), attributes[i].getName());
            list.add(pair);
            
        }
             
        return list;
    }
    
    public static ArrayList<KeyNamePair> getProductAttributeValues(Properties ctx, int attributeId, int productCategoryId)
    {
        MAttribute attribute = new MAttribute(ctx, attributeId, null);
        MAttributeValue attributeValue[] = attribute.getMAttributeValues();
        
        int[] attributeValueIds = MAttributeValue.getAllIDs(MAttributeValue.Table_Name, "ad_client_id=" + Env.getAD_Client_ID(ctx) + " and m_attribute_id=" + attributeId + " and m_product_category_id=" + productCategoryId+" and isActive='Y' order by name", null);
        
        ArrayList<KeyNamePair> attributeValues = new ArrayList<KeyNamePair>(); 
        
        if (attributeValue == null)
            return attributeValues;
        
        MAttributeValue value;
        for(int i=0;i<attributeValueIds.length;i++)
        {
            value = new MAttributeValue(ctx, attributeValueIds[i], null);
            KeyNamePair pair = value.getKeyNamePair();            
            attributeValues.add(pair);
        }
        
        return attributeValues;
    }
    
    public static ArrayList<AttributeValueDetailBean> getProductAttributeValuesDescription(Properties ctx, int attributeId, int productCategoryId, boolean showActiveOnly)
    {
        MAttribute attribute = new MAttribute(ctx, attributeId, null);
        MAttributeValue attributeValue[] = attribute.getMAttributeValues();
        
        String isActiveSQL = " ";
        
        if(showActiveOnly)
        {
            isActiveSQL = " and isActive = 'Y' ";
        }       
        
        int[] attributeValueIds = MAttributeValue.getAllIDs(MAttributeValue.Table_Name, "ad_client_id=" + Env.getAD_Client_ID(ctx) + " and m_attribute_id=" + attributeId + " and m_product_category_id=" + productCategoryId+ isActiveSQL +" order by name", null);
        
        ArrayList<AttributeValueDetailBean> attributeValues = new ArrayList<AttributeValueDetailBean>(); 
        
        if (attributeValue == null)
            return attributeValues;
        
        MAttributeValue value;
        for(int i=0;i<attributeValueIds.length;i++)
        {
            value = new MAttributeValue(ctx, attributeValueIds[i], null);
            //KeyNamePair pair = value.getKeyNamePair();
            // Pair pair = new  Pair(value.getName(),value.getDescription());
            //attributeValues.add(pair);
            
            
            AttributeValueDetailBean bean = new AttributeValueDetailBean();
            
            bean.setId(value.get_ID());
            bean.setName(value.getName());
            bean.setDescription(value.getDescription());
            bean.setActive(value.isActive());
                        
            attributeValues.add(bean);
            
        }
        
        return attributeValues;
    }
   
    public static void updateProductAttribute(Properties ctx,int attributeValueId, String trxName) throws OperationException, SystemException
    {
        
        Integer[] productIds = ProductManager.getAllProductsHavingAttributeValue(ctx,attributeValueId);
        
        if(productIds.length > 0)
        {
            throw new ProductInStockException("Unable to perform action. There are some products that use the attribute value.");
        }
        
        MAttributeValue attrValue = new MAttributeValue(ctx,attributeValueId,trxName);
        
        if(attrValue.isActive())
        {
            attrValue.setIsActive(false);
        }
        else
        {
            attrValue.setIsActive(true);
        }
        
        PoManager.save(attrValue);
        
    }
    
    public static  MAttribute createAttribute(Properties ctx, String name) throws OperationException
    {
        MAttribute attribute = new MAttribute(ctx, 0, null);
        attribute.setName(name);
        attribute.setAttributeValueType(MAttribute.ATTRIBUTEVALUETYPE_List);
        attribute.setIsMandatory(true);
        PoManager.save(attribute);
        return  attribute;
       
    }
    
    /*public  static void createAttributeUse(Properties ctx,  MAttributeSet attributeSet, String attributeKey) throws OperationException
    {
         MAttribute attribute = ( MAttribute) GenericProductAttributeFactory.getFactoryInstance().get(ctx, attributeKey);
        
        int attributeUseIds[] =  MAttributeUse.getAllIDs(MAttributeUse.Table_Name, "ad_client_id=" + Env.getAD_Client_ID(ctx) + " and  m_attribute_id=" + attribute.get_ID() + " and m_attributeSet_id=" + attributeSet.get_ID(), null);
        
        if (attributeUseIds.length!=0)
            return;
        
        MAttributeUse attributeUse = new MAttributeUse(ctx, 0 , null);
        attributeUse.setM_AttributeSet_ID(attributeSet.get_ID());
        attributeUse.setM_Attribute_ID(attribute.get_ID());
        attributeUse.setSeqNo(10);
        PoManager.save(attributeUse);
        
    }*/
    
    public  static void createAttributeUse(Properties ctx,  MAttributeSet attributeSet,  MAttribute attribute) throws OperationException
    {
        int attributeUseIds[] =  MAttributeUse.getAllIDs(MAttributeUse.Table_Name, "ad_client_id=" + Env.getAD_Client_ID(ctx) + " and  m_attribute_id=" + attribute.get_ID() + " and m_attributeSet_id=" + attributeSet.get_ID(), null);
        
        if (attributeUseIds.length!=0)
            return;
        
        MAttributeUse attributeUse = new MAttributeUse(ctx, 0 , null);
        attributeUse.setM_AttributeSet_ID(attributeSet.get_ID());
        attributeUse.setM_Attribute_ID(attribute.get_ID());
        attributeUse.setSeqNo(10);
        PoManager.save(attributeUse);
        
    }
    
}
