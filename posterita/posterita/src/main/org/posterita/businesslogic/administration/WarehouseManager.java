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
package org.posterita.businesslogic.administration;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MLocation;
import org.compiere.model.MLocator;
import org.compiere.model.MWarehouse;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.posterita.beans.ProductBean;
import org.posterita.beans.WarehouseBean;
import org.posterita.businesslogic.LocationManager;
import org.posterita.businesslogic.OrganisationManager;
import org.posterita.core.CheckDuplicateEntities;
import org.posterita.exceptions.CanNotMoveStockException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.WarehouseAlreadyExistsException;
import org.posterita.keyname.LocatorKeyNamePair;
import org.posterita.keyname.WarehouseKeyNamePair;
import org.posterita.lib.UdiConstants;
import org.posterita.order.UDIOrderType;
import org.posterita.util.PoManager;

public class WarehouseManager
{
    private static final CLogger log = CLogger.getCLogger(WarehouseManager.class);
    
    public static MWarehouse createWarehouse(Properties ctx, int orgId, String warehouseName, String address1,String postalAddress1,String city,int regionId, int countryId,String trxName) throws OperationException, WarehouseAlreadyExistsException
    {
        MWarehouse warehouse;
        
        if (CheckDuplicateEntities.checkDuplicateName(ctx, warehouseName, MWarehouse.Table_Name))
            throw new WarehouseAlreadyExistsException("Warehouse already exists");
        
        try
        {
        	MLocation location = LocationManager.createLocation(ctx, orgId, address1, "", postalAddress1, city, regionId, countryId, trxName);
        	warehouse = createWarehouse(ctx, orgId, warehouseName, location.getC_Location_ID(), trxName);
        }
        catch(OperationException e)
        {
            throw e;
        }
        
        return warehouse;
        
    }
    
    public static MWarehouse createWarehouse(Properties ctx, int orgId, String warehouseName, int c_location_id, String trxName) throws OperationException, WarehouseAlreadyExistsException
    {
        MWarehouse warehouse;
        
        if (CheckDuplicateEntities.checkDuplicateName(ctx, warehouseName, MWarehouse.Table_Name))
            throw new WarehouseAlreadyExistsException("Warehouse already exists");
        
        try
        {            
            warehouse = new MWarehouse(ctx,0,trxName);
            warehouse.setName(warehouseName);
            warehouse.setC_Location_ID(c_location_id);
            warehouse.setAD_Org_ID(orgId);
            PoManager.save(warehouse);
              
            createLocator(ctx, orgId, warehouse.get_ID(), true,"0","0","0", trxName);                        
        }
        catch(OperationException e)
        {
            throw e;
        }
        
        return warehouse;
        
    }
    
    public static MLocator createLocator(Properties ctx,int orgId, int warehouseId,boolean isDefault, String aisle,String bin,String level, String trxName) throws OperationException
    {
        
    	MLocator locator = new MLocator(ctx,0,trxName);
        
        try
        {
        	locator.setAD_Org_ID(orgId);
            locator.setM_Warehouse_ID(warehouseId);
            locator.setXYZ(aisle,bin,level);
            locator.setIsDefault(isDefault);
            
            PoManager.save(locator);
        }
        catch(OperationException e)
        {
            throw new OperationException("Could not create locator!!");
        }
        
        return locator;
        
    }
    
    /**
     * Returns all the warehouse present and viewable by the user
     * @param ctx context
     * @return List of Warehouses
     * @throws OperationException if problem with sql or DB
     */
    public static ArrayList<WarehouseBean> getAllWarehouses(Properties ctx) throws OperationException
    {
        int viewableOrgs[] = OrganisationManager.getUserViewableOrganisations(ctx);
        return getAllWarehouses(ctx, viewableOrgs);
    }
    
    /**
     * Returns all warehouses that is present for the organisation provided.
     * @param ctx Context
     * @param adOrgId Organisation
     * @return List of Warehouses
     * @throws OperationException if problem with sql or DB
     */
    public static ArrayList<WarehouseBean> getAllWarehouses(Properties ctx, int adOrgId) throws OperationException
    {
        int orgs[] = new int[] {adOrgId};
        return getAllWarehouses(ctx, orgs);
    }
    
    /**
     * Returns all the warehouse present for the organisations
     * @param ctx context
     * @param adOrgId organisation
     * @return List of warehouses
     * @throws OperationException if problem with sql or DB
     */
    public static ArrayList<WarehouseBean> getAllWarehouses(Properties ctx, int[] adOrgIds) throws OperationException
    {
        if (adOrgIds.length == 0)
        {
            throw new IllegalArgumentException("At least one organisation should be provided");
        }
        
        int adClientID = Env.getAD_Client_ID(ctx);
        StringBuffer sqlStmt = new StringBuffer();
        
        sqlStmt.append(" SELECT w.M_Warehouse_ID,"); //1
        sqlStmt.append(" w.Name,"); //2
        sqlStmt.append(" l.Address1,"); //3
        sqlStmt.append(" l.City,"); //4
        sqlStmt.append(" l.Postal_Add,");//5
        sqlStmt.append(" l.RegionName,"); //6
        sqlStmt.append(" w.Description,");//7 
        sqlStmt.append(" w.IsActive");	//8
        sqlStmt.append(" FROM M_Warehouse w, C_Location l ");
        sqlStmt.append(" WHERE l.C_Location_ID=W.C_Location_ID ");
        sqlStmt.append(" AND w.AD_Org_ID IN ( ");
        
        int i;
        for (i = 0; i < adOrgIds.length - 1; i++)
        {
            sqlStmt.append(adOrgIds[i]).append(",");
        }
        sqlStmt.append(adOrgIds[i]).append(")");
        
        sqlStmt.append(" AND w.AD_Client_ID = ? ");
        sqlStmt.append(" ORDER BY w.Name");
        
        WarehouseBean warehouse=null;
        ArrayList<WarehouseBean> warehouses = new ArrayList<WarehouseBean>();
        
        String isActive;
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        
        try
        {
            pstmt = DB.prepareStatement(sqlStmt.toString(), null);
            pstmt.setInt(1, adClientID);
            rs = pstmt.executeQuery();
            
            while (rs.next())
            {
                warehouse = new WarehouseBean();
                warehouse.setWarehouseId(Integer.valueOf(rs.getInt(1)));
                warehouse.setWarehouseName(rs.getString(2));
                
                warehouse.setAddress1(rs.getString(3));
                warehouse.setCity(rs.getString(4));
                warehouse.setPostalAddress(rs.getString(5));
                warehouse.setRegionName(rs.getString(6));
                warehouse.setDescription(rs.getString(7));
                isActive = rs.getString(8);
                warehouse.setIsActive("Y".equals(isActive));
                warehouses.add(warehouse);
            }	
        }
        catch(SQLException e)
        {
            throw new OperationException(e.getMessage());
        }
        finally 
        {
            DB.close(rs, pstmt);
            rs = null;
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
        
        PoManager.save(warehouse);
        
        return warehouse;
    }
    
    
    public static int getOrganisationWarehouse(Properties ctx, int adOrgId, String trxName) throws OperationException
    {
        int warehouseId = -1;
        String sqlStmt = "SELECT M_Warehouse_ID FROM AD_OrgInfo WHERE AD_Org_ID=?";
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try
        {
            pstmt = DB.prepareStatement(sqlStmt, trxName);
            pstmt.setInt(1, adOrgId);
            
            rs = pstmt.executeQuery();
            if (rs.next())
            {
                warehouseId = rs.getInt(1);
            }
        }
        catch (Exception ex)
        {
            log.log(Level.SEVERE, "Could not get organisation warehouse", ex);
            throw new OperationException("Could not get organisation warehouse", ex);
        }
        finally
        {
            DB.close(rs, pstmt);
        }
        
        return warehouseId;
    }
    
    public static String getLocatorIds(Properties ctx, Integer orgId) throws OperationException
	{
		String orgIds = orgId.toString();
		
		if (orgId == 0)
		{
			orgIds = Env.getContext(ctx,UdiConstants.ROLE_EDITABLE_ORGS_CTX_PARAM).toString();
		}
		
		int[] locatorIds =  MLocator.getAllIDs(MLocator.Table_Name, "AD_Client_ID = " + Env.getAD_Client_ID(ctx)+ " AND AD_Org_ID IN (" + orgIds + ")", null);
		
		StringBuffer locIds = new StringBuffer();
		if (locatorIds != null && locatorIds.length>0)
		{
			for (int i = 0; i<locatorIds.length; i++)
			{
				locIds.append(locatorIds[i]);
				
				if (i < locatorIds.length -1)
				{
					locIds.append(",");
				}
			}
		}
		
		return locIds.toString();
	}
}
