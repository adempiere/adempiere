/**
 *  Product: Posterita Web-Based POS (an Adempiere Plugin)
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
 */

/**
	@author ashley
 */

package org.posterita.businesslogic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import org.compiere.model.MAttribute;
import org.compiere.model.MAttributeValue;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.posterita.beans.AttributeInstanceBean;
import org.posterita.beans.AttributeValuesPair;
import org.posterita.core.KeyNamePairUtil;
import org.posterita.exceptions.OperationException;
import org.posterita.keyname.ProductAttributeValueKeyNamePair;
import org.posterita.util.PoManager;

public class AttributeValuesManager 
{
    private static ArrayList<KeyNamePair> getAttributeValues(Properties ctx, int attributeId, int productCategoryId ) throws OperationException
    {
        ArrayList<KeyNamePair> attributeValues = ProductAttributeValueKeyNamePair.getKeyNamePairs(ctx, attributeId, productCategoryId);
        
        return attributeValues;
        
    }
    
    //retrieve the colour, model, transmission, year from an attributesetinstanceID
    //returns a bean containing a colour, model, year, transmission keynamepair

    public static AttributeValuesPair retrieveAttributeValues(Properties ctx,int attributeSetInstanceId) throws OperationException
    {
        if (attributeSetInstanceId == 0)
            return new AttributeValuesPair();
        
        ArrayList attributeInstances = getMAttributeInstances(ctx);
        
        return retrieveAttributeValues(ctx, attributeSetInstanceId, attributeInstances);
    }
    
    public static AttributeValuesPair retrieveAttributeValues(Properties ctx,int attributeSetInstanceId, ArrayList attributeInstances) throws OperationException
    {
        HashMap attributeValuesMap = getAttributeValues(ctx, attributeSetInstanceId, attributeInstances);
        
        AttributeValuesPair pair = new AttributeValuesPair();
        pair.setBrandAttributeValue((KeyNamePair)attributeValuesMap.get("Brand"));
        pair.setModelAttributeValue((KeyNamePair) attributeValuesMap.get("Model"));
        pair.setColourAttributeValue((KeyNamePair) attributeValuesMap.get("Colour"));
        pair.setTransmissionAttributeValue((KeyNamePair) attributeValuesMap.get("Transmission"));
        pair.setYearAttributeValue((KeyNamePair) attributeValuesMap.get("Year"));
        pair.setDesignAttributeValue((KeyNamePair) attributeValuesMap.get("Design"));
        pair.setSizeAttributeValue((KeyNamePair) attributeValuesMap.get("Size"));
        pair.setStyleAttributeValue((KeyNamePair) attributeValuesMap.get("Style"));
        
        return pair;
    }
    
    private static HashMap getAttributeValues(Properties ctx, int attributeSetInstanceId,  ArrayList attributeInstances) throws OperationException
    {
        ArrayList sameAttributeSetInstances = getAttributeInstancesByAttributeSetInstance(attributeSetInstanceId, attributeInstances);
        
        Iterator iter = sameAttributeSetInstances.iterator();
        
        HashMap attributeValuesMap = new HashMap();
        
        AttributeInstanceBean attributeInstanceBean;
        while (iter.hasNext())
        {
            attributeInstanceBean = (AttributeInstanceBean) iter.next();
            attributeValuesMap = getAttributeValue(ctx,attributeInstanceBean, attributeValuesMap);
            
        }
        
        return attributeValuesMap;
        
    }
    
    @SuppressWarnings("unchecked")
	private static HashMap getAttributeValue(Properties ctx, AttributeInstanceBean attributeInstanceBean, HashMap attributeValuesMap) throws OperationException
    {
        KeyNamePair pair = new KeyNamePair(attributeInstanceBean.getAttributeValueId(), attributeInstanceBean.getAttributeValue());
        attributeValuesMap.put(attributeInstanceBean.getAttribute(), pair);
        
        return attributeValuesMap;
    }
    
    public static ArrayList<AttributeInstanceBean> getMAttributeInstances(Properties ctx) throws OperationException
    {
//        MOrg org = OrganisationManager.getHsafOrg(ctx); // Honda customised stuff
        
    	int adOrgID = Env.getAD_Org_ID(ctx);
        String sql = "select ai.m_attributeSetInstance_id," 				+
        " ai.m_attributevalue_id," 						+
        " ai.value," 										+
        " attr.name,"										+
        " attr.M_Attribute_ID" 							+
        " from m_attributeInstance ai, M_Attribute attr " 	+
        " where ai.m_attribute_id=attr.m_attribute_id"      +
        " and ai.AD_CLIENT_ID=" + Env.getAD_Client_ID(ctx)	+
        " and ai.AD_ORG_ID="+adOrgID;
        
        PreparedStatement pstmt =null;
        
        ArrayList<AttributeInstanceBean> attributeInstances = new ArrayList<AttributeInstanceBean>();
        try
        {
            pstmt = DB.prepareStatement(sql, null);
            ResultSet rs = pstmt.executeQuery();
            
            AttributeInstanceBean bean;
            while (rs.next())
            {
                bean = new AttributeInstanceBean();
                bean.setAttributeSetInstanceId(rs.getInt(1));
                bean.setAttributeValueId(rs.getInt(2));
                bean.setAttributeValue(rs.getString(3));
                bean.setAttribute(rs.getString(4));
                bean.setAttributeId(rs.getInt(5));
                attributeInstances.add(bean);
            }
            
            rs.close();

        }
        catch (SQLException e)
        {
            throw new OperationException(e.getMessage());
        } 
        finally 
        {
            try 
            {
                pstmt.close();
            } 
            catch (Exception ex) 
            {
            } 
            pstmt = null;
        }
        
        return attributeInstances;
    }
    
    private static ArrayList getAttributeInstancesByAttributeSetInstance(int attributeSetInstanceId, ArrayList allAttributeInstances)
    {
        Iterator iter = allAttributeInstances.iterator();
        
        AttributeInstanceBean bean;
        ArrayList<AttributeInstanceBean> sameInstances = new ArrayList<AttributeInstanceBean>();
        while (iter.hasNext())
        {
            bean = (AttributeInstanceBean) iter.next();
            if (bean.getAttributeSetInstanceId() == attributeSetInstanceId)
                sameInstances.add(bean);
        }
        
        
        return sameInstances;
    }
    
    public static MAttributeValue createAttributeValue(Properties ctx, String value, int attributeId, int productCategoryId, String trxName) throws OperationException
    {    
        
        MAttributeValue attributeValue = new MAttributeValue(ctx, 0, trxName);
        attributeValue.setName(value);
        attributeValue.setValue(value);
//        attributeValue.setM_Product_Category_ID(productCategoryId);
        attributeValue.setM_Attribute_ID(attributeId);
        PoManager.save(attributeValue);
        return attributeValue;
        
    }
    
    public static MAttributeValue getOrCreateAttributeValue(Properties ctx, int attributeId, String value, int productCategoryId, String trxName) throws OperationException
    {
        //check is attribute value exist before creating new one
        int attributeValueIds[] = MAttributeValue.getAllIDs(MAttributeValue.Table_Name, "ad_client_id=" + Env.getAD_Client_ID(ctx) + " and Upper(name)=Upper('" + value + "')", trxName);
        
        MAttributeValue attributeValue;
        if (attributeValueIds.length!= 0)
            attributeValue = new MAttributeValue(ctx, attributeValueIds[0], trxName);
        else
            attributeValue =  AttributeValuesManager.createAttributeValue(ctx, value, attributeId, productCategoryId, trxName);
        
        return attributeValue;
    }
    
    public static MAttributeValue getAttributeValue(Properties ctx, int attributeId, int attributeSetInstanceId, String trxName) throws OperationException
    {
    	String selStmt = "Select M_AttributeValue_ID from M_AttributeInstance where ";
    	selStmt += " AD_Client_ID=" + Env.getAD_Client_ID(ctx);
    	selStmt += " and M_AttributeSetInstance_ID=" + attributeSetInstanceId;
    	selStmt += " and M_Attribute_ID=" + attributeId;
    	
    	PreparedStatement pstmt = null;
    	
    	try
    	{
    		pstmt = DB.prepareStatement(selStmt, trxName);
    		ResultSet rs = pstmt.executeQuery();
    		
    		if(!rs.next())
    			throw new OperationException("Could not find Attribute Value id for Attribute id: " + attributeId + " and Attribute Set Instance Id: " + attributeSetInstanceId);
    		
    		int attributeValueId = rs.getInt(1);
    		rs.close();
    		MAttributeValue attrValue = new MAttributeValue(ctx, attributeValueId, trxName);
    		return attrValue;
    	}
    	catch(SQLException sqlEx)
    	{
    		throw new OperationException("Could not execute query for getting attribute value: " + selStmt);
    	}
    	finally
    	{
    		try
    		{
    			pstmt.close();
    		}
    		catch(Exception ex)
    		{}
    	}
    }
    
	public static ArrayList<KeyNamePair> getAttributeValues(Properties ctx, int AttributeId) throws OperationException
    {
    	StringBuffer whereClause = new StringBuffer();
    	whereClause.append("M_AttributeValue_ID in ( ");
    	whereClause.append("select distinct M_AttributeValue_ID from M_AttributeInstance ");
    	whereClause.append("where AD_Client_ID=" + Env.getAD_Client_ID(ctx) + " ");
    	whereClause.append(" and AD_Org_ID=" + Env.getAD_Org_ID(ctx) + " ");
    	whereClause.append("and M_Attribute_ID=" + AttributeId + " ");
    	whereClause.append(")");
    	try
    	{
    		return KeyNamePairUtil.getData(ctx, MAttributeValue.Table_Name, whereClause.toString());
    	}
    	catch(SQLException ex)
    	{
    		throw new OperationException("Could not retrieve Attribute Values with WhereClause: " + whereClause, ex);
    	}
    }
    
    public static ArrayList<KeyNamePair> getAttributes(Properties ctx) throws OperationException
    {
    	String whereClause = "AD_Client_ID=" + Env.getAD_Client_ID(ctx) + " and IsActive='Y'";
    	
    	try
    	{
    		return KeyNamePairUtil.getData(ctx, MAttribute.Table_Name, whereClause);
    	}
    	catch(SQLException ex)
    	{
    		throw new OperationException("Could not retrieve attributes with WhereClause: " + whereClause, ex);
    	}
    }
    
    public static int changeAttributeValue(Properties ctx, int attributeValueId, String newValue, String trxName) throws OperationException
    {
    	int retAttributeValueId;
    	String whereClause = "AD_Client_ID=" + Env.getAD_Client_ID(ctx); 
    	whereClause += " and AD_Org_ID=" + Env.getAD_Org_ID(ctx) + " and Upper(Name)=Upper('" + newValue + "')";
    	
    	int attrValueIds[] = MAttributeValue.getAllIDs(MAttributeValue.Table_Name, whereClause, trxName);
    	
    	if(attrValueIds.length > 1)
    		throw new OperationException("Duplicate name found in Attribute Value: " + newValue);
    	
    	MAttributeValue attributeValue = new MAttributeValue(ctx, attributeValueId, trxName);    	
    	if(attributeValue.get_ID() == 0)
    		throw new OperationException("Could not load Attribute Value with ID: " + attributeValueId);
    	
    	if(attrValueIds.length == 0)
    	{
    		attributeValue.setName(newValue);
    		attributeValue.setValue(newValue);
    		retAttributeValueId = attributeValue.get_ID();
    	}
    	else if(attributeValueId == attrValueIds[0])
    	{
    		attributeValue.setIsActive(true);
    		attributeValue.setName(newValue);
    		attributeValue.setValue(newValue);
    		retAttributeValueId = attributeValue.get_ID();
    	}
    	else
    	{
    		attributeValue.setIsActive(false);
    		retAttributeValueId = attrValueIds[0];
    	}
    	
    	PoManager.save(attributeValue);
    	
    	return retAttributeValueId;
    }
}
