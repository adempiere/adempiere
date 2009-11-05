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
 * Created on 27-Jul-2005 by alok
 *
 */
package org.posterita.businesslogic.administration;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.model.MOrg;
import org.compiere.model.MRole;
import org.compiere.model.MRoleMenu;
import org.compiere.model.MRoleOrgAccess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.posterita.Constants;
import org.posterita.beans.RoleBean;
import org.posterita.businesslogic.MenuManager;
import org.posterita.businesslogic.OrganisationManager;
import org.posterita.businesslogic.RoleMenuManager;
import org.posterita.core.MenuItem;
import org.posterita.exceptions.DuplicateRoleException;
import org.posterita.exceptions.NoAccessToEditObjectException;
import org.posterita.exceptions.NoCheckBoxSelectedException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.RoleAlreadyExistsException;
import org.posterita.exceptions.SystemException;
import org.posterita.lib.UdiConstants;
import org.compiere.model.MWebMenu;
import org.posterita.util.PoManager;


public class RoleManager
{
	
	
	
	public static MRole createRole(Properties ctx, int orgId, String roleName, boolean isAccessAllOrgs, BigDecimal userDiscount, boolean overwritePriceLimit, boolean isDiscountAllowedOnTotal, boolean isDiscountUptoLimitPrice, boolean canCreateOrder, boolean canAlterOrder, boolean canViewOrder, String trxName) throws OperationException, RoleAlreadyExistsException    
	{
		
	    String whereClause = "ad_client_id = " + Env.getAD_Client_ID(ctx) +" and ad_org_id in ("+Env.getContext(ctx,UdiConstants.ROLE_EDITABLE_ORGS_CTX_PARAM) + ") and name = '"+ roleName +"'";
	    
	    int id[] = MRole.getAllIDs(MRole.Table_Name,whereClause,trxName);
	    
	    if(id.length > 0) 
	    {
	        throw new RoleAlreadyExistsException("Please enter another name for the role. Role " + roleName + "already exists.");
	    }
		
		Properties newCtx = (Properties) ctx.clone();
        
		Env.setContext(newCtx, "#AD_User_ID", 100);
		MRole role = new MRole(newCtx, 0, trxName);

		role.setName(roleName);
		role.setUserDiscount(BigDecimal.valueOf(0.00));
        role.setIsPersonalAccess(true);
        role.setIsAccessAllOrgs(isAccessAllOrgs);
        role.setUserDiscount(userDiscount);
        role.setOverwritePriceLimit(overwritePriceLimit);
        role.setIsDiscountAllowedOnTotal(isDiscountAllowedOnTotal);
        role.setIsDiscountUptoLimitPrice(isDiscountUptoLimitPrice);
        role.setAD_Org_ID(orgId);
        role.setIsAccessAllOrgs(isAccessAllOrgs);
        
       
   		role.setUserLevel(MRole.USERLEVEL_Organization);
   		
   		PoManager.save(role);
        
        
        MRoleOrgAccess orgAccess = new MRoleOrgAccess(role,orgId);
        PoManager.save(orgAccess);
        
        return role;
    }
	
    
    
    
	public static MRole createRole(Properties ctx, int orgId, String roleName,Boolean isAccessAllOrgs, Integer[] menuId, BigDecimal userDiscount, Boolean overwriteLimitPrice, Boolean isDiscountAllowedOnTotal, Boolean isDiscountUptoLimitPrice, Boolean canCreateOrder, Boolean canAlterOrder, Boolean canViewOrder, String trxName) throws Exception
	{
	    MRole role = createRole(ctx, orgId, roleName, isAccessAllOrgs.booleanValue(), userDiscount, overwriteLimitPrice.booleanValue(), overwriteLimitPrice.booleanValue(), isDiscountAllowedOnTotal.booleanValue(), canCreateOrder.booleanValue(), canAlterOrder.booleanValue(), canViewOrder.booleanValue(), trxName);
	    
		if(menuId!=null)
		{
		    for(int i=0; i<menuId.length; i++)
		    	RoleMenuManager.createRoleMenu(ctx, role.get_ID(), menuId[i].intValue(), trxName);		    
		}
		

		//--> Add default menus e.g. LogOut
		int defMenuIds[] = MenuManager.getDefaultMenus(ctx);
		for(int i = 0; i < defMenuIds.length; i++)
		{
			if(!RoleMenuManager.isRoleMenuPresent(ctx, role.get_ID(), defMenuIds[i], trxName))
				RoleMenuManager.createRoleMenu(ctx, role.get_ID(), defMenuIds[i], trxName);
		}
		

		
	    return role;
	}
	
	public static RoleBean getRole(Properties ctx, int roleId) throws OperationException
	{
	    RoleBean bean = new RoleBean();
	    
	    MRole role = loadRole(ctx, roleId, null);
	    
	    bean.setRoleId(roleId);
	    bean.setName(role.getName());
	    bean.setIsActive(Boolean.valueOf(role.isActive()));
	    bean.setIsAccessAllOrgs(Boolean.valueOf(role.isAccessAllOrgs()));
	    bean.setUserDiscount(role.getUserDiscount());
	    bean.setIsOverwritePriceLimit(Boolean.valueOf(role.isOverwritePriceLimit()));
	    bean.setIsDiscountUptoLimitPrice(Boolean.valueOf(role.isDiscountUptoLimitPrice()));
	    bean.setIsDiscountAllowedOnTotal(role.isDiscountAllowedOnTotal());
	    bean.setOrgId(role.getAD_Org_ID());
	    
	    return bean;
	}
	
	public static ArrayList<RoleBean> getRoles(Properties ctx, String searchText)
	{
	    ArrayList<RoleBean> list = new ArrayList<RoleBean>();
	    int clientId = Env.getAD_Client_ID(ctx);
	 
	    String roleName = "";
	    
	    if (searchText != null)
	    	roleName = searchText;
	    
	    int id[] = MRole.getAllIDs(MRole.Table_Name,"upper(name) like '%" + roleName.toUpperCase() + "%' and ad_client_id ="+ clientId +" and isActive = 'Y'"+" and AD_ORG_ID in (" + Env.getContext(ctx,UdiConstants.ROLE_EDITABLE_ORGS_CTX_PARAM)+") order by name",null);
	    
	    if (id == null || id.length == 0)
	    {
	    	return list;
	    }
	    
	    RoleBean bean;
	    
	    for(int i=0;i<id.length;i++)
	    {
	        MRole role = new MRole(ctx,id[i],null);	        
	        bean = new RoleBean();
	        
	        bean.setRoleId(Integer.valueOf(role.get_ID()));
	        bean.setName(role.getName());
	        bean.setIsActive(Boolean.valueOf(role.isActive()));
	        bean.setIsAccessAllOrgs(Boolean.valueOf(role.isAccessAllOrgs()));
	        bean.setUserDiscount(role.getUserDiscount());
		    bean.setIsOverwritePriceLimit(Boolean.valueOf(role.isOverwritePriceLimit()));
		    bean.setIsDiscountUptoLimitPrice(Boolean.valueOf(role.isDiscountUptoLimitPrice()));
		    
	        list.add(bean);
	    }	   
	    
	    return list;
	}	
	
	public static ArrayList<RoleBean> getAllRoles(Properties ctx)
	{
	    ArrayList<RoleBean> list = new ArrayList<RoleBean>();
	    int clientId = Env.getAD_Client_ID(ctx);
	 
	    
	    int id[] = MRole.getAllIDs(MRole.Table_Name," ad_client_id ="+ clientId +" and isActive = 'Y'"+" and AD_ORG_ID in (" + Env.getContext(ctx,UdiConstants.ROLE_EDITABLE_ORGS_CTX_PARAM)+") order by name",null);
	    
	    if (id == null || id.length == 0)
	    {
	    	return list;
	    }
	    
	    RoleBean bean;
	    
	    for(int i=0;i<id.length;i++)
	    {
	        MRole role = new MRole(ctx,id[i],null);	        
	        bean = new RoleBean();
	        
	        bean.setRoleId(Integer.valueOf(role.get_ID()));
	        bean.setName(role.getName());
	        bean.setIsActive(Boolean.valueOf(role.isActive()));
	        bean.setIsAccessAllOrgs(Boolean.valueOf(role.isAccessAllOrgs()));
	        bean.setUserDiscount(role.getUserDiscount());
		    bean.setIsOverwritePriceLimit(Boolean.valueOf(role.isOverwritePriceLimit()));
		    bean.setIsDiscountUptoLimitPrice(Boolean.valueOf(role.isDiscountUptoLimitPrice()));
	        
	        list.add(bean);
	    }	   
	    
	    return list;
	}
	
	public static MRole loadRole(Properties ctx, int roleId, String trxName) throws OperationException
	{
		MRole role = new MRole(ctx, roleId, trxName);
		if(role.get_ID() == 0)
			throw new OperationException("Could not load role with id: " + roleId);
		
		return role;
	}
	

	public static void deleteRole(Properties ctx, int roleId) throws OperationException
	{
		
	    MRole adRole = MRole.get(ctx, roleId);
	    adRole.setIsActive(false);
	    PoManager.save(adRole);
	}
 

    public static MRole getOrCreateRole(Properties ctx, int orgId, String roleName,Boolean isAccessAllOrgs, BigDecimal userDiscount, Boolean overwriteLimitPrice, Boolean isDiscountAllowedOnTotal, Boolean isDicountUptoLimitPrice, Boolean canCreateOrder, Boolean canAlterOrder, Boolean canViewOrder, String trxName) throws OperationException
    {
    	int clientId = Env.getAD_Client_ID(ctx);
    	int adOrgId = Env.getAD_Org_ID(ctx);
    	try
    	{
    	String sqlStatement = " AD_Client_ID=" + clientId + " and AD_Org_ID=" + adOrgId + " and Name='" + roleName + "'";
    	int roleIds[] = MRole.getAllIDs(MRole.Table_Name, sqlStatement, null);
    	
    	if (roleIds!=null && roleIds.length > 0)
    	{
    		MRole role = new MRole(ctx, roleIds[0], null);
    		return role;
    	}
    	else
    		return createRole(ctx, orgId, roleName, isAccessAllOrgs, userDiscount, overwriteLimitPrice, isDiscountAllowedOnTotal, isDicountUptoLimitPrice ,canCreateOrder, canAlterOrder, canViewOrder, trxName);
    	}
    	catch(Exception ex)
    	{
    		throw new OperationException("Cannot create role for: " + roleName);
    	}
    }
    
    public static MRole getOrCreateRole(Properties ctx, int orgId, String roleName,Boolean isAccessAllOrgs, Integer[] menuId, BigDecimal userDiscount, Boolean overwriteLimitPrice, Boolean isDiscountAllowedOnTotal, Boolean isDicountUptoLimitPrice, Boolean canCreateOrder, Boolean canAlterOrder, Boolean canViewOrder, String trxName) throws OperationException
    {
    	int clientId = Env.getAD_Client_ID(ctx);
    	int adOrgId = Env.getAD_Org_ID(ctx);
    	try
    	{
    	String sqlStatement = " AD_Client_ID=" + clientId + " and AD_Org_ID=" + adOrgId + " and Name='" + roleName + "'";
    	int roleIds[] = MRole.getAllIDs(MRole.Table_Name, sqlStatement, null);
    	
    	if (roleIds!=null && roleIds.length > 0)
    	{
    		MRole role = new MRole(ctx, roleIds[0], null);
    		return role;
    	}
    	else
    		return createRole(ctx, orgId, roleName, isAccessAllOrgs, menuId, userDiscount, overwriteLimitPrice, isDiscountAllowedOnTotal, isDicountUptoLimitPrice, canCreateOrder, canAlterOrder, canViewOrder, trxName);
    	}
    	catch(Exception ex)
    	{
    		throw new OperationException("Cannot create role for: " + roleName);
    	}
    	
    }
    
    /**
     * Gets all the roles assigned to the user
     * @param ctx
     * @return an arraylist of keynamepairs
     * @throws OperationException
     */
    public static ArrayList<KeyNamePair> getMyRoles(Properties ctx) throws OperationException
    {
        int ad_user_id = Env.getAD_User_ID(ctx);
        int ad_client_id = Env.getAD_Client_ID(ctx);
        
        String sql = "select role.AD_ROLE_ID, role.NAME " +
        		" from AD_USER_ROLES usrRole, AD_USER usr, AD_ROLE role, AD_ORG org " +
        		" where org.AD_ORG_ID = role.AD_ORG_ID " +
        		" and usrRole.AD_USER_ID = usr.AD_USER_ID " +
        		" and usrRole.AD_ROLE_ID = role.AD_ROLE_ID " +
        		" and role.AD_CLIENT_ID = ? " +        		
        		" and usrRole.AD_USER_ID = ? " +
        		" order by role.NAME";
        
        PreparedStatement pstmt = DB.prepareStatement(sql, null);
        ResultSet rs = null;
        ArrayList<KeyNamePair> roleList = new ArrayList<KeyNamePair>();
        
        try 
        {            
            pstmt.setInt(1,ad_client_id);       
            pstmt.setInt(2,ad_user_id);
            
            rs = pstmt.executeQuery();
            
            while(rs.next())
            {
                KeyNamePair pair = new KeyNamePair(rs.getInt(1),rs.getString(2));
                
                roleList.add(pair);
            }       
            
            rs.close();
        } 
        catch (SQLException e) 
        {
            throw new OperationException(e);
        } 
        finally
        {
        	try
        	{
                pstmt.close();
        	}
        	catch(Exception e)
        	{}
        	
        	pstmt = null;
        }

        return roleList;

    }
       
    public static String getWhereClauseOnUserRolesAccess(Properties ctx, String tableAlias)
    {
        int  roleId = Env.getAD_Role_ID(ctx);
        Env.getContext(ctx,UdiConstants.ROLE_EDITABLE_ORGS_CTX_PARAM);
        //#User_Org=0,11,12,
        MRole userRole = new MRole(ctx,roleId,null);
        String whereClause= " and " + tableAlias+"AD_CLIENT_ID";
        
        if(userRole.getUserLevel().equals(MRole.USERLEVEL_Client) || userRole.getUserLevel().equals(MRole.USERLEVEL_ClientPlusOrganization))
        {
        	return whereClause = whereClause +" and isActive='Y'";
        }
        
        else
        {
            return  whereClause= whereClause +" and "+tableAlias+"AD_ORG_ID="+Env.getAD_Org_ID(ctx);
        }
       
    }
    
    private static boolean hasMenu(int menuId, ArrayList<MWebMenu> menus)
    {
    	boolean menuAvailable = false;
    	for(MWebMenu menu : menus)
    	{
    		if(menu.get_ID() == menuId)
    		{
    			menuAvailable = true;
    			break;
    		}
    	}
    	return menuAvailable;
    }
    
    private static void setRoleMenus(MenuItem menuItem, ArrayList<MWebMenu> menus)
    {
    	ArrayList<MenuItem> children = menuItem.getAllChildren();
    	for(MenuItem mItem : children)
    	{
    		if(mItem.hasSubMenu())
    			setRoleMenus(mItem, menus);
    		else
    		{
    			if(hasMenu(mItem.getMenuId(), menus))
    				mItem.setAvailable(true);
    		}
    	}
    }
    
    public static MenuItem getAvailableMenus(Properties ctx, int roleId) throws SystemException, OperationException
    {
    	ArrayList<MWebMenu> roleMenus = MenuManager.getMenus(ctx, roleId);
    	
    	ArrayList<MWebMenu> appMenus = MenuManager.getMenusForOrganisationType(ctx);
		MenuItem rootItem = MenuManager.buildMenuTree(ctx, appMenus);
    	
    	setRoleMenus(rootItem, roleMenus);
    	    	
    	return rootItem;
    }
    
    public static void editRole(Properties ctx, int roleId, String roleName, boolean isAccessAllOrgs, Integer[] menuId, BigDecimal userDiscount, boolean overwritePriceLimit, boolean isDiscountUptoLimitPrice, boolean isDiscountAllowedOnTotal, String trxName) throws OperationException, RoleAlreadyExistsException
    {
    	if(menuId==null)
    	{
    		throw new NoCheckBoxSelectedException("Please select a role");
    	}
    	String nameClause = "AD_Client_ID = " + Env.getAD_Client_ID(ctx) + " and Upper(name)='" + roleName.toUpperCase() + "' and AD_Role_ID <> " + roleId;
    	
    	int roleIds[] = MRole.getAllIDs(MRole.Table_Name, nameClause, null);
    	
    	if (roleIds!=null && roleIds.length > 0)
    		throw new RoleAlreadyExistsException("Role with name: " + roleName + " already exists");
    	
    	MRole role = loadRole(ctx, roleId, trxName);
    	
		Boolean isEditable = RoleManager.isEditable(ctx, role.getAD_Org_ID());
		
		if (!isEditable)
			throw new NoAccessToEditObjectException("You do not have the right organisational access for editing");			
    	
		if(userDiscount == null)
		{
		    userDiscount = Env.ZERO;
		}

		role.setName(roleName);
    	role.setIsAccessAllOrgs(isAccessAllOrgs);
    	role.setUserDiscount(userDiscount);
    	role.setOverwritePriceLimit(overwritePriceLimit);
    	role.setIsDiscountUptoLimitPrice(isDiscountUptoLimitPrice);
    	role.setIsDiscountAllowedOnTotal(isDiscountAllowedOnTotal);
    	
    	PoManager.save(role);
    	
    	String whereClause = " AD_Role_ID=" + roleId;
    	int roleMenuIds[] = MRoleMenu.getAllIDs(MRoleMenu.Table_Name, whereClause, trxName);
		
    	// Delete all associated role menus
		for(int i = 0; i < roleMenuIds.length; i++)
		{
			MRoleMenu roleMenu = new MRoleMenu(ctx, roleMenuIds[i], trxName);
			roleMenu.delete(true);
		}
		
		// Create role menus assigned
		for(int i = 0; i < menuId.length; i++)
		{
			MRoleMenu roleMenu = new MRoleMenu(ctx, 0, trxName);
			roleMenu.setAD_Role_ID(role.get_ID());
			roleMenu.setU_WebMenu_ID(menuId[i]);
			PoManager.save(roleMenu);
		}
		
		// Create role menus for all default menus
		int defMenuIds[] = MenuManager.getDefaultMenus(ctx);
		for(int i = 0; i < defMenuIds.length; i++)
		{
			MRoleMenu roleMenu = new MRoleMenu(ctx, 0, trxName);
			roleMenu.setAD_Role_ID(role.get_ID());
			roleMenu.setU_WebMenu_ID(defMenuIds[i]);
			PoManager.save(roleMenu);
		}
    }
    
    public static BigDecimal getDiscountAllowed(Properties ctx, int AD_Role_ID, String trxName) throws OperationException
    {
    	BigDecimal discount = BigDecimal.ZERO;
    	MRole role = MRole.get(ctx, AD_Role_ID);
    	
    	if (role != null)
    	{
    		discount = role.getUserDiscount();
    	}
    	
    	return discount;
    }
    
    public static boolean isOverridePriceLimitAllowed(Properties ctx, int AD_Role_ID, String trxName) throws OperationException
    {
    	MRole role = MRole.get(ctx, AD_Role_ID);  
    	
    	return role.isOverwritePriceLimit();
    }
    
    public static boolean isDiscountUptoPriceLimit(Properties ctx, int AD_Role_ID, String trxName) throws OperationException
    {
        MRole role = MRole.get(ctx, AD_Role_ID);  
        
        return role.isDiscountUptoLimitPrice();
    }
    
    public static boolean isDiscountAllowedOnTotal(Properties ctx, int AD_Role_ID, String trxName) throws OperationException
    {
        MRole role = MRole.get(ctx, AD_Role_ID);  
        
        return role.isDiscountAllowedOnTotal();
    }
    
    public static ArrayList<KeyNamePair> getRoleOrgPairs(Properties ctx, String trxName) throws OperationException
    {
    	int roleId = Env.getAD_Role_ID(ctx);
    	
    	RoleBean roleBean = getRole(ctx, roleId);
    	
    	ArrayList<KeyNamePair> orgPairs = new ArrayList<KeyNamePair>();
    	
    	if (roleBean.getIsAccessAllOrgs())
    	{
    		orgPairs = OrganisationManager.getAllOrgPairs(ctx, trxName);
    	}
    	else
    	{
    		MOrg org = MOrg.get(ctx, roleBean.getOrgId());
    		KeyNamePair pair = org.getKeyNamePair();

    		orgPairs.add(pair);
    	}
    		
    	
    	return orgPairs;
    	
    }
    
    
    public static ArrayList<RoleBean> getRoleOrgAccess(Properties ctx, int roleId) throws OperationException
    {
    	
		MRoleOrgAccess[] orgAccessArr = MRoleOrgAccess.getOfRole(ctx, Env.getAD_Role_ID(ctx));
		MRole role = new MRole(ctx, roleId, null); //trxName can be null here as we are just retrieving data that was previously committed and not doing any update
		
		ArrayList<RoleBean> list = new ArrayList<RoleBean>();
		
		for (int i = 0; i < orgAccessArr.length; i++) 
		{
			MRoleOrgAccess orgAccess = orgAccessArr[i];
			
			MOrg org = MOrg.get(ctx, orgAccess.getAD_Org_ID());
			
			RoleBean roleBean = new RoleBean();
			roleBean.setRoleId(orgAccess.getAD_Role_ID());
			roleBean.setName(role.getName());
			
			if (orgAccess.getAD_Org_ID() == 0)
				roleBean.setOrgName(Constants.ALL_ORGANISATIONS);
			else
				roleBean.setOrgName(org.getName());
			
			roleBean.setOrgId(orgAccess.getAD_Org_ID());
			

			String roleOrgAccessSQL = getRoleOrgAccessSQL();
			
	        PreparedStatement pstmt = DB.prepareStatement(roleOrgAccessSQL, null);
			
	        ResultSet rs = null;
	        int count = 0;
	        Boolean isActive = false;
	        
	        try 
	        {            
	            pstmt.setInt(1,Env.getAD_Client_ID(ctx));       
	            pstmt.setInt(2,org.get_ID());
	            pstmt.setInt(3,role.get_ID());
	            
	            rs = pstmt.executeQuery();
	            
	            
	            
	            while(rs.next())
	            {
	            	if (rs.getString(4).equals("Y"))
	            		isActive = true;
	            		
	            	count++;
	            }      
	            
	            rs.close();
	        } 
	        catch (SQLException e) 
	        {
	            throw new OperationException(e);
	        } 
	        finally
	        {
	        	try
	        	{
	                pstmt.close();
	        	}
	        	catch(Exception e)
	        	{}
	        	
	        	pstmt = null;
	        }
	        
	        if (count > 1)
	        	throw new DuplicateRoleException("Duplicate Role Organisation Access");
	        else
	        	if (count == 1)
		        {
		        	roleBean.setIsChecked(isActive);
		        }
	        	else
	        	{
	        		roleBean.setIsChecked(isActive);
	        	}
	        
	        list.add(roleBean);
	        
			  	
		}
		
		return list;
		
    }




	public static void editRoleOrgAccess(Properties ctx, ArrayList<RoleBean> roleBeanList, String trxName) throws OperationException 
	{
		for (int i = 0; i < roleBeanList.size(); i++) 
		{
			RoleBean bean = roleBeanList.get(i);
			
			MRole role = new MRole(ctx, bean.getRoleId(), trxName);
			
			String isChecked = "Y";
			
			if (bean.getIsChecked() == null)
				isChecked = "N";
			
			String roleOrgAccessSQL = getRoleOrgAccessSQL();
			
	        PreparedStatement pstmt = DB.prepareStatement(roleOrgAccessSQL, trxName);
			
	        ResultSet rs = null;
	        int count = 0;
	        
	        try 
	        {            
	            pstmt.setInt(1,Env.getAD_Client_ID(ctx));       
	            pstmt.setInt(2, bean.getOrgId());
	            pstmt.setInt(3, bean.getRoleId());
	            
	            rs = pstmt.executeQuery();
	            
	            
	            
	            while(rs.next())
	            {
	            	count++;
	            }      
	            
	            rs.close();
	        } 
	        catch (SQLException e) 
	        {
	            throw new OperationException(e);
	        } 
	        finally
	        {
	        	try
	        	{
	                pstmt.close();
	        	}
	        	catch(Exception e)
	        	{}
	        	
	        	pstmt = null;
	        }
	        
	        if (count > 1)
	        	throw new DuplicateRoleException("Duplicate Role Organisation Access");
	        else
	        	if (count == 1)
		        {
	    			StringBuffer strBuff = new StringBuffer();
	    			
		        	strBuff.append("UPDATE ").append(MRoleOrgAccess.Table_Name);
		        	strBuff.append(" SET isActive='").append(isChecked).append("'");
		        	strBuff.append(" WHERE ad_client_id=").append(Env.getAD_Client_ID(ctx));
		    		strBuff.append(" AND ad_org_id=").append(bean.getOrgId());
		    		strBuff.append(" AND ad_role_id=").append(bean.getRoleId());
		    				        	
			        PreparedStatement pstmt1 = DB.prepareStatement(strBuff.toString(), trxName);
			        
			        try 
			        {            
			            pstmt1.executeUpdate();
			        }
			        catch (SQLException e) 
			        {
			            throw new OperationException(e);
			        } 
			        finally
			        {
			        	try
			        	{
			                pstmt1.close();
			        	}
			        	catch(Exception e)
			        	{}
			        	
			        	pstmt1 = null;
			        }			        
		        	
		        }
	        	else
	        	{
	        		MRoleOrgAccess roleOrgAccess = new MRoleOrgAccess(role, bean.getOrgId());
	        		PoManager.save(roleOrgAccess);

	        	}
		}

		
	}
	
	private static String getRoleOrgAccessSQL()
	{
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("select ad_role_id, ad_org_id, ad_client_id, isactive from ");
		strBuff.append(MRoleOrgAccess.Table_Name);
		strBuff.append(" where ad_client_id=?");
		strBuff.append(" and ad_org_id=?");
		strBuff.append(" and ad_role_id=?");
		//strBuff.append(" and isActive='Y'");
		
		return strBuff.toString();
	}
	
    public static String getRoleEditableOrgAccess(Properties ctx)
    {
        StringBuffer orgIdsStr = new StringBuffer();
        
        int roleId = Env.getAD_Role_ID(ctx);
        
        MRoleOrgAccess[] roleOrgAccessArr = MRoleOrgAccess.getOfRole(ctx, roleId);
    	
        int count = 0;
    	
        if (roleOrgAccessArr==null || roleOrgAccessArr.length==0)
        {
        	return orgIdsStr.toString();
        }
        
        for (int i = 0; i < roleOrgAccessArr.length; i++) 
        {
        	MRoleOrgAccess roleOrgAccess = roleOrgAccessArr[i];
        	
        	if (roleOrgAccess.isActive() == true)
        	{
        		
        		if (roleOrgAccess.getAD_Org_ID() == 0)
        		{
        			int[] orgIds = MOrg.getAllIDs(MOrg.Table_Name, "ad_client_id=" + Env.getAD_Client_ID(ctx) + " and isActive='Y' order by name", null);
        			
        			StringBuffer allOrgIds = new StringBuffer();
        			
        			allOrgIds.append("0");

        			for (int j = 0; j < orgIds.length; j++) 
        			{
        				allOrgIds.append(",");
                    	allOrgIds.append(orgIds[j]);                    	
					}
        			
        			return allOrgIds.toString();
        		}
        		
        		else
        		{
        			orgIdsStr.append(roleOrgAccess.getAD_Org_ID());
        			orgIdsStr.append(",");
        		}            	
        	}        		
		}
        
        orgIdsStr.replace(orgIdsStr.length()-1, orgIdsStr.length()-1, "");
    	return orgIdsStr.toString();
    	
    }
    
    public static String getRoleViewableOrgAccess(Properties ctx)
    {
    	String roleEditableOrgAccess = getRoleEditableOrgAccess(ctx);
    	
    	if (roleEditableOrgAccess.length() == 0)
    		return "0";

    	if (roleEditableOrgAccess.indexOf(",0") == -1)
    		return roleEditableOrgAccess + ",0";
    	else
    		return roleEditableOrgAccess;
    		
    }    
    
    
    public static Boolean isEditable(Properties ctx, int orgId)
    {
    	int roleId = Env.getAD_Role_ID(ctx);
    	
        MRoleOrgAccess[] roleOrgAccessArr = MRoleOrgAccess.getOfRole(ctx, roleId);
    	
        if (roleOrgAccessArr!=null && roleOrgAccessArr.length >0)
        {
        	for (int i = 0; i < roleOrgAccessArr.length; i++) 
	        {
	        	MRoleOrgAccess roleOrgAccess = roleOrgAccessArr[i];
	
	        	if (roleOrgAccess.getAD_Org_ID() == 0)
	        		return true;
	        	
	        	if (roleOrgAccess.getAD_Org_ID() == orgId)
	        		return true;
			}
        }
		return false;
    }
    
}
