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
package org.posterita.businesslogic;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.model.MRole;
import org.compiere.model.MRoleOrgAccess;
import org.compiere.model.MUser;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.posterita.beans.RoleBean;
import org.posterita.core.MenuItem;
import org.posterita.exceptions.NoCheckBoxSelectedException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.RoleAlreadyExistsException;
import org.posterita.exceptions.SystemException;
import org.posterita.lib.UdiConstants;
import org.posterita.model.MRoleMenu;
import org.posterita.model.UDIMRole;
import org.posterita.model.UDIU_Menu;
import org.posterita.model.UDIU_RoleMenu;


public class RoleManager
{
	
	public static MRole createRole(Properties ctx, String roleName, boolean isAccessAllOrgs, boolean canCreateOrder, boolean canAlterOrder, boolean canViewOrder, String trxName) throws OperationException, RoleAlreadyExistsException    
	{
		
	    String whereClause = "ad_client_id = " + Env.getAD_Client_ID(ctx) +" and ad_org_id in ("+Env.getContext(ctx,UdiConstants.USER_ORG_CTX_PARAM) + ") and name = '"+ roleName +"'";
	    
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
        
       
   		role.setUserLevel(MRole.USERLEVEL_Organization);
   		
        UDIMRole udiMrole = new UDIMRole(role);
        udiMrole.save();
        if(!isAccessAllOrgs)
            {
                MRoleOrgAccess orgAccess = new MRoleOrgAccess(role,Env.getAD_Org_ID(ctx));
                orgAccess.save();
            }
        
        return role;
    }
	
    
    
    
	public static MRole createRole(Properties ctx, String roleName,Boolean isAccessAllOrgs, Integer[] menuId, Boolean canCreateOrder, Boolean canAlterOrder, Boolean canViewOrder, String trxName) throws Exception
	{
	    MRole role = createRole(ctx, roleName, isAccessAllOrgs.booleanValue(), canCreateOrder.booleanValue(), canAlterOrder.booleanValue(), canViewOrder.booleanValue(), trxName);
	    
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
	    
	    return bean;
	}
	
	public static ArrayList<RoleBean> getRoles(Properties ctx, String searchText)
	{
	    ArrayList<RoleBean> list = new ArrayList<RoleBean>();
	    int clientId = Env.getAD_Client_ID(ctx);
	 
	    String roleName = "";
	    
	    if (searchText != null)
	    	roleName = searchText;
	    
	    int id[] = MRole.getAllIDs(MRole.Table_Name,"upper(name) like '%" + roleName.toUpperCase() + "%' and ad_client_id ="+ clientId +" and isActive = 'Y'"+" and AD_ORG_ID in (" + Env.getContext(ctx,UdiConstants.USER_ORG_CTX_PARAM)+") order by name",null);
	    
	    RoleBean bean;
	    
	    for(int i=0;i<id.length;i++)
	    {
	        MRole role = new MRole(ctx,id[i],null);	        
	        bean = new RoleBean();
	        
	        bean.setRoleId(Integer.valueOf(role.get_ID()));
	        bean.setName(role.getName());
	        bean.setIsActive(Boolean.valueOf(role.isActive()));
	        bean.setIsAccessAllOrgs(Boolean.valueOf(role.isAccessAllOrgs()));
	        
	        list.add(bean);
	    }	   
	    
	    return list;
	}	
	
	public static ArrayList<RoleBean> getAllRoles(Properties ctx)
	{
	    ArrayList<RoleBean> list = new ArrayList<RoleBean>();
	    int clientId = Env.getAD_Client_ID(ctx);
	 
	    
	    int id[] = MRole.getAllIDs(MRole.Table_Name," ad_client_id ="+ clientId +" and isActive = 'Y'"+" and AD_ORG_ID in (" + Env.getContext(ctx,UdiConstants.USER_ORG_CTX_PARAM)+") order by name",null);
	    
	    RoleBean bean;
	    
	    for(int i=0;i<id.length;i++)
	    {
	        MRole role = new MRole(ctx,id[i],null);	        
	        bean = new RoleBean();
	        
	        bean.setRoleId(Integer.valueOf(role.get_ID()));
	        bean.setName(role.getName());
	        bean.setIsActive(Boolean.valueOf(role.isActive()));
	        bean.setIsAccessAllOrgs(Boolean.valueOf(role.isAccessAllOrgs()));
	        
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
	    UDIMRole uRole = new UDIMRole(adRole);
	    uRole.save();
	}
 

    public static MRole getOrCreateRole(Properties ctx, String roleName,Boolean isAccessAllOrgs, Boolean canCreateOrder, Boolean canAlterOrder, Boolean canViewOrder, String trxName) throws OperationException
    {
    	int clientId = Env.getAD_Client_ID(ctx);
    	int adOrgId = Env.getAD_Org_ID(ctx);
    	try
    	{
    	String sqlStatement = " AD_Client_ID=" + clientId + " and AD_Org_ID=" + adOrgId + " and Name='" + roleName + "'";
    	int roleIds[] = MRole.getAllIDs(MRole.Table_Name, sqlStatement, null);
    	
    	if(roleIds.length > 0)
    	{
    		MRole role = new MRole(ctx, roleIds[0], null);
    		return role;
    	}
    	else
    		return createRole(ctx, roleName, isAccessAllOrgs, canCreateOrder, canAlterOrder, canViewOrder, trxName);
    	}
    	catch(Exception ex)
    	{
    		throw new OperationException("Cannot create role for: " + roleName);
    	}
    }
    
    public static MRole getOrCreateRole(Properties ctx, String roleName,Boolean isAccessAllOrgs, Integer[] menuId, Boolean canCreateOrder, Boolean canAlterOrder, Boolean canViewOrder, String trxName) throws OperationException
    {
    	int clientId = Env.getAD_Client_ID(ctx);
    	int adOrgId = Env.getAD_Org_ID(ctx);
    	try
    	{
    	String sqlStatement = " AD_Client_ID=" + clientId + " and AD_Org_ID=" + adOrgId + " and Name='" + roleName + "'";
    	int roleIds[] = MRole.getAllIDs(MRole.Table_Name, sqlStatement, null);
    	
    	if(roleIds.length > 0)
    	{
    		MRole role = new MRole(ctx, roleIds[0], null);
    		return role;
    	}
    	else
    		return createRole(ctx, roleName, isAccessAllOrgs, menuId, canCreateOrder, canAlterOrder, canViewOrder, trxName);
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
        Env.getContext(ctx,UdiConstants.USER_ORG_CTX_PARAM);
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
    
    private static boolean hasMenu(int menuId, ArrayList<UDIU_Menu> menus)
    {
    	boolean menuAvailable = false;
    	for(UDIU_Menu udiMenu : menus)
    	{
    		if(udiMenu.getMenuId() == menuId)
    		{
    			menuAvailable = true;
    			break;
    		}
    	}
    	return menuAvailable;
    }
    
    private static void setRoleMenus(MenuItem menuItem, ArrayList<UDIU_Menu> menus)
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
    	ArrayList<UDIU_Menu> roleMenus = MenuManager.getMenus(ctx, roleId);
    	
    	ArrayList<UDIU_Menu> appMenus = MenuManager.getMenusForOrganisationType(ctx);
		MenuItem rootItem = MenuManager.buildMenuTree(ctx, appMenus);
    	
    	setRoleMenus(rootItem, roleMenus);
    	    	
    	return rootItem;
    }
    
    public static void editRole(Properties ctx, int roleId, String roleName, boolean isAccessAllOrgs, Integer[] menuId, String trxName) throws OperationException, RoleAlreadyExistsException
    {
    	if(menuId==null)
    	{
    		throw new NoCheckBoxSelectedException("Please select a role");
    	}
    	String nameClause = "AD_Client_ID = " + Env.getAD_Client_ID(ctx) + " and Upper(name)='" + roleName.toUpperCase() + "' and AD_Role_ID <> " + roleId;
    	
    	int roleIds[] = MRole.getAllIDs(MRole.Table_Name, nameClause, null);
    	
    	if(roleIds.length > 0)
    		throw new RoleAlreadyExistsException("Role with name: " + roleName + " already exists");
    	
    	MRole role = loadRole(ctx, roleId, trxName);
    	role.setName(roleName);
    	role.setIsAccessAllOrgs(isAccessAllOrgs);
    	UDIMRole udiRole = new UDIMRole(role);
    	udiRole.save();
    	
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
			roleMenu.setU_Menu_ID(menuId[i]);
			UDIU_RoleMenu udiRoleMenu = new UDIU_RoleMenu(roleMenu);
			udiRoleMenu.save();
		}
		
		// Create role menus for all default menus
		int defMenuIds[] = MenuManager.getDefaultMenus(ctx);
		for(int i = 0; i < defMenuIds.length; i++)
		{
			MRoleMenu roleMenu = new MRoleMenu(ctx, 0, trxName);
			roleMenu.setAD_Role_ID(role.get_ID());
			roleMenu.setU_Menu_ID(defMenuIds[i]);
			UDIU_RoleMenu udiRoleMenu = new UDIU_RoleMenu(roleMenu);
			udiRoleMenu.save();
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
}
