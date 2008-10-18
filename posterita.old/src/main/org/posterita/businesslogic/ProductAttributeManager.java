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
package org.posterita.businesslogic;

import java.util.ArrayList;
import java.util.Properties;

import org.compiere.model.MAttribute;
import org.compiere.model.MAttributeSet;
import org.compiere.model.MAttributeUse;
import org.compiere.model.MAttributeValue;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

import org.posterita.beans.AttributeValueDetailBean;
import org.posterita.beans.AttributeValuesBean;
import org.posterita.beans.ProductAttributeBean;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.ProductInStockException;
import org.posterita.exceptions.SystemException;
import org.posterita.factory.GenericProductAttributeFactory;
import org.posterita.factory.ProductAttributeFactory;
import org.posterita.factory.ProductAttributeSetFactory;
import org.posterita.factory.SystemObjectsFactory;
import org.posterita.model.UDIMAttribute;
import org.posterita.model.UDIMAttributeSet;
import org.posterita.model.UDIMAttributeUse;
import org.posterita.model.UDIMAttributeValue;
import org.posterita.model.UDIMProductCategory;


public class ProductAttributeManager 
{
   
    
  private static ArrayList<KeyNamePair> getProductAttributeSet(Properties ctx) throws Exception
  {          
      UDIMAttributeSet attributeSet=getAttributeSet(ctx);     
      
      KeyNamePair pair = new KeyNamePair(attributeSet.getID(), attributeSet.getAttributeSet().getName());
      
      ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
      list.add(pair);
      
      return list;
  }
    
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
            //UDIPair pair = new UDIPair(value.getName(),value.getDescription());
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
   
    /*
     * api used for populating web components(list boxes)
     * used when creating product attribute values
     * 
     * 
     * the first time the page is loaded,
     * no attribute set is selected
     * so by default we load the Car Attribute Set
     * and all the attributes pertaining to this set.
     * 
     * 
     * next when we change from attribute set to attribute set, 
     * this api is called and the attributes and attribute values
     * also changes
     * 
     */
    
    public static ProductAttributeBean populate(Properties ctx, Integer attributeSetId, Integer attributeId, Integer productCategoryId, Boolean showActiveOnly) throws Exception
    {
        String attributeSetKey;
        String attributeKey;
        ProductAttributeBean pAttributeBean = new ProductAttributeBean();
        ArrayList attributeSets = getProductAttributeSet(ctx);
        
        ArrayList attributes;
        
        //attribute exist only when there is an attribute set 
        //check
        if ((attributeSetId == null) && (attributeId!=null))
            throw new OperationException("An attribute cannot exist on its own. It should belong to an attributeSet");
            
        //no attribute set selected
        //by default load car attribute set
        //and a car attribute(model)
        
        
        if (attributeSetId == null)
        {          
            attributeSetKey = ProductAttributeSetFactory.ATTRIBUTE_SET_BIKE_ID;
            attributeKey = ProductAttributeFactory.ATTRIBUTE_BIKE_MODEL_ID;            
            attributeSetId =Integer.valueOf(ProductAttributeSetFactory.getFactoryInstance(ctx).get(ctx, attributeSetKey).getID());
            attributeId = Integer.valueOf(ProductAttributeFactory.getFactoryInstance().get(ctx, attributeKey).getID());
        }
        
    
        attributes = getProductAttributes(ctx, attributeSetId.intValue());
        
        ArrayList attributeValues = getProductAttributeValuesDescription(ctx, attributeId.intValue(), productCategoryId.intValue(),showActiveOnly.booleanValue());
        
        pAttributeBean.setAttributeSetId(attributeSetId);
        pAttributeBean.setAttributeId(attributeId);
        pAttributeBean.setAttributeSets(attributeSets);
        pAttributeBean.setAttributes(attributes);
        pAttributeBean.setAttributeValues(attributeValues);
        
        //remove make attribute
        UDIMAttribute makeAttribute = (UDIMAttribute) ProductAttributeFactory.getFactoryInstance().get(ctx, ProductAttributeFactory.ATTRIBUTE_CAR_MAKE_ID);
        
        UDIMProductCategory newCarPc = (UDIMProductCategory) SystemObjectsFactory.getFactoryInstance().get(ctx,SystemObjectsFactory.PRODUCT_CATEGORY_CAR_ID);
        
        if (newCarPc.getID() == productCategoryId.intValue())
        {
            KeyNamePair pairToBeRemoved = new KeyNamePair(makeAttribute.getID(), makeAttribute.getAttribute().getName());
            attributes.remove(pairToBeRemoved);
        }
                   
            
        return pAttributeBean;
    }
    
    public static AttributeValuesBean populateAttributeValues(Properties ctx, Integer productCategoryId) throws Exception
    {
        AttributeValuesBean attributeValuesBean = AttributeValuesManager.getProductAttributeValues(ctx, productCategoryId.intValue());
         
        return attributeValuesBean;
    }
    
    private static UDIMAttributeSet getAttributeSet(Properties ctx) throws Exception
    {
                
        ProductAttributeSetFactory productAttributeSetFactory = ProductAttributeSetFactory.getFactoryInstance(ctx);
        
        UDIMAttributeSet attributeSet;        
      
        attributeSet = (UDIMAttributeSet) productAttributeSetFactory.get(ctx, ProductAttributeSetFactory.ATTRIBUTE_SET_BIKE_ID);
 
        return attributeSet;
        
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
        
        UDIMAttributeValue uAttrVal = new UDIMAttributeValue(attrValue);
        
        uAttrVal.save();
    }
    
    public static UDIMAttribute createAttribute(Properties ctx, String name) throws OperationException
    {
        MAttribute attribute = new MAttribute(ctx, 0, null);
        attribute.setName(name);
        attribute.setAttributeValueType(MAttribute.ATTRIBUTEVALUETYPE_List);
        attribute.setIsMandatory(true);
        UDIMAttribute udiAttribute = new UDIMAttribute(attribute);
        return udiAttribute;
       
    }
    
    public  static void createAttributeUse(Properties ctx, UDIMAttributeSet attributeSet, String attributeKey) throws OperationException
    {
        UDIMAttribute attribute = (UDIMAttribute) GenericProductAttributeFactory.getFactoryInstance().get(ctx, attributeKey);
        
        int attributeUseIds[] = UDIMAttributeUse.getAllIDs(MAttributeUse.Table_Name, "ad_client_id=" + Env.getAD_Client_ID(ctx) + " and  m_attribute_id=" + attribute.getID() + " and m_attributeSet_id=" + attributeSet.getID(), null);
        
        if (attributeUseIds.length!=0)
            return;
        
        MAttributeUse attributeUse = new MAttributeUse(ctx, 0 , null);
        attributeUse.setM_AttributeSet_ID(attributeSet.getID());
        attributeUse.setM_Attribute_ID(attribute.getID());
        attributeUse.setSeqNo(10);
        attributeUse.save();
        
    }
    
    public  static void createAttributeUse(Properties ctx, UDIMAttributeSet attributeSet, UDIMAttribute attribute) throws OperationException
    {
        int attributeUseIds[] = UDIMAttributeUse.getAllIDs(MAttributeUse.Table_Name, "ad_client_id=" + Env.getAD_Client_ID(ctx) + " and  m_attribute_id=" + attribute.getID() + " and m_attributeSet_id=" + attributeSet.getID(), null);
        
        if (attributeUseIds.length!=0)
            return;
        
        MAttributeUse attributeUse = new MAttributeUse(ctx, 0 , null);
        attributeUse.setM_AttributeSet_ID(attributeSet.getID());
        attributeUse.setM_Attribute_ID(attribute.getID());
        attributeUse.setSeqNo(10);
        attributeUse.save();
        
    }
    
}
