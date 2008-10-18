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
 * Created on 22-Jul-2005 by alok
 *
 */
package org.posterita.businesslogic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.compiere.model.MLocation;
import org.compiere.model.MLocator;
import org.compiere.model.MWarehouse;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.posterita.beans.ProductBean;
import org.posterita.beans.WarehouseBean;
import org.posterita.core.CheckDuplicateEntities;
import org.posterita.exceptions.CanNotMoveStockException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.WarehouseAlreadyExistsException;
import org.posterita.keyname.LocatorKeyNamePair;
import org.posterita.keyname.WarehouseKeyNamePair;
import org.posterita.model.UDIMLocator;
import org.posterita.model.UDIMWarehouse;
import org.posterita.order.UDIOrderType;


public class WarehouseManager
{
    
    public static MWarehouse createWarehouse(Properties ctx, String warehouseName, String address1,String postalAddress1,String city,int regionId, int countryId,String trxName) throws OperationException, WarehouseAlreadyExistsException
    {
        MWarehouse warehouse;
        
        if (CheckDuplicateEntities.checkDuplicateName(ctx, warehouseName, MWarehouse.Table_Name))
            throw new WarehouseAlreadyExistsException("Warehouse already exists");
        
        try
        {
            
            warehouse = new MWarehouse(ctx,0,trxName);
            warehouse.setName(warehouseName);
           
           
            MLocation location = LocationManager.createLocation(ctx,address1, "", postalAddress1,null,city,countryId, trxName);
            
            warehouse.setC_Location_ID(location.get_ID());
            
            UDIMWarehouse udiWarehouse = new UDIMWarehouse(warehouse);
            udiWarehouse.save();
              
            createLocator(ctx,warehouse.get_ID(), true,"0","0","0", trxName);
            
            
        }
        catch(OperationException e)
        {
            throw e;
        }
        
        return warehouse;
        
    }
    
    public static MLocator createLocator(Properties ctx,int warehouseId,boolean isDefault, String aisle,String bin,String level, String trxName) throws OperationException
    {
        
    	MLocator locator = new MLocator(ctx,0,trxName);
        
        try
        {
            locator.setM_Warehouse_ID(warehouseId);
            locator.setXYZ(aisle,bin,level);
            locator.setIsDefault(isDefault);
            
            UDIMLocator udiLocator = new UDIMLocator(locator);
            udiLocator.save();
        }
        catch(OperationException e)
        {
            throw new OperationException("Could not create locator!!");
        }
        
        return locator;
        
    }
    
    public static ArrayList getAllWarehouses(Properties ctx) throws OperationException
    {
        int adOrgID = Env.getAD_Org_ID(ctx);
        int adClientID = Env.getAD_Client_ID(ctx);
        StringBuffer sql = new StringBuffer();
        
        sql.append(" select mw.m_warehouse_id,"); //1
        sql.append(" mw.name,"); //2
        sql.append(" mw.ispublic,"); //3
        sql.append(" cl.address1,"); //4
        sql.append(" cl.city,"); //5
        sql.append(" cl.postal_add,");//6
        sql.append(" cl.regionname,"); //7
        sql.append(" mw.U_TYPE,");//8
        sql.append(" mw.description,");//9 
        sql.append(" mw.isactive");	//10
        sql.append(" from M_WAREHOUSE mw, c_location cl ");
        sql.append(" where cl.c_location_id=mw.c_location_id ");
        sql.append(" and mw.AD_ORG_ID = ? ");
        sql.append(" and mw.AD_CLIENT_ID = ? ");
        sql.append(" order by mw.name");
        
        
        WarehouseBean warehouse=null;
        ArrayList<WarehouseBean> warehouses = new ArrayList<WarehouseBean>();
        
        String isPublic;
        String isActive;
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        
        try
        {
            pstmt = DB.prepareStatement(sql.toString(), null);
            pstmt.setInt(1, adOrgID);
            pstmt.setInt(2, adClientID);
            rs = pstmt.executeQuery();
            
            
            while (rs.next())
            {
                warehouse = new WarehouseBean();
                warehouse.setWarehouseId(Integer.valueOf(rs.getInt(1)));
                warehouse.setWarehouseName(rs.getString(2));
                
                isPublic = rs.getString(3);
                
                if(isPublic == null)
                    isPublic = "N";
                
                
                if(isPublic.equals("Y"))
                {
                    warehouse.setIsPublic(Boolean.valueOf(true));
                }
                else
                    warehouse.setIsPublic(Boolean.valueOf(false));
                
                warehouse.setAddress1(rs.getString(4));
                warehouse.setCity(rs.getString(5));
                warehouse.setPostalAddress(rs.getString(6));
                warehouse.setRegionName(rs.getString(7));
                warehouse.setWarehouseType(rs.getString(8));
                warehouse.setDescription(rs.getString(9));
                
                isActive = rs.getString(10);
                
                if(isActive.equals("Y"))
                    warehouse.setIsActive(Boolean.valueOf(true));
                else
                    warehouse.setIsActive(Boolean.valueOf(false));
                
                warehouses.add(warehouse);
                
            }	
            
            rs.close();
            
        }
        
        catch(SQLException e)
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
            {} 
            
            pstmt = null;
        }
        
        return warehouses;
    }
    
    public static WarehouseBean getWarehouse(Properties ctx, Integer warehouseId, ArrayList warehouses)
    {
        Iterator iter = (warehouses.iterator());
        
        while (iter.hasNext())
        {
            WarehouseBean warehouseBean = (WarehouseBean) iter.next();
                
            if (warehouseBean.getWarehouseId().equals(warehouseId))  
                return warehouseBean; 
        }
        
        return new WarehouseBean();
    }
    
    
   
    
    public static ArrayList getOrganisationWarehouses(Properties ctx) throws OperationException
	{
		ArrayList warehouses;

		warehouses = WarehouseKeyNamePair.getKeyNamePair(ctx);

		return warehouses;
	}
    
    public static ArrayList getOrganisationWarehouses(Properties ctx,ArrayList products) throws OperationException
	{
		ArrayList warehouses;
		
		Iterator prodIter = products.iterator();
    	
    	ProductBean prodBean = null;
    	int warehouseId=0;

    	while(prodIter.hasNext())
    	{
    		prodBean = (ProductBean)prodIter.next();
    		if(warehouseId==0 || warehouseId==prodBean.getWarehouseId().intValue())
    		{
    		    warehouseId=prodBean.getWarehouseId().intValue();
    		}
    		else 
    		{
    		   throw new CanNotMoveStockException("Please select the products form single warehouse");
    		}
    	}
    	warehouses = WarehouseKeyNamePair.getKeyNamePair(ctx,warehouseId);

		return warehouses;
	}
    

	
	public static ArrayList getOrganisationLocators(Properties ctx) throws OperationException
	{
		ArrayList locators;
		
		locators = LocatorKeyNamePair.getKeyNamePair(ctx);
		
		return locators;
	}

    public static MWarehouse getStorageWarehouse(Properties ctx, UDIOrderType type) throws OperationException
    {
        return getDefaultWarehouse(ctx);
       
    }
    
    public static MWarehouse getDefaultWarehouse(Properties ctx) throws OperationException
    {
        int warehouseID = Env.getContextAsInt(ctx, "#M_Warehouse_ID");
        MWarehouse warehouse = new MWarehouse(ctx, warehouseID, null);
        if(warehouse.get_ID() != 0)
        	return warehouse;
        throw new OperationException(" Cannot find default warehouse for organisation");
    }
    
    public static MWarehouse editWarehouse(Properties ctx, WarehouseBean bean) throws OperationException
    {
        MWarehouse warehouse = new MWarehouse(ctx, bean.getWarehouseId().intValue(), null);
        
        warehouse.setName(bean.getWarehouseName());

        warehouse.setIsActive(bean.getIsActive().booleanValue());
        
        UDIMWarehouse udiWarehouse = new UDIMWarehouse(warehouse);
        udiWarehouse.save();
        
        return warehouse;
    }
}
