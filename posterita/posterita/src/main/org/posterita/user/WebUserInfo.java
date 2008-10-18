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
 * Created on Aug 11, 2005 by Alok
 */

package org.posterita.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.model.MLocation;
import org.compiere.model.MOrg;
import org.compiere.model.MRole;
import org.compiere.model.MUser;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Secure;
import org.compiere.util.WebInfo;
import org.compiere.util.WebUser;

import org.posterita.exceptions.DataException;
import org.posterita.exceptions.OperationException;


/**
 * @author fred
 *
 *This Info is breaking convention because it is using Info that calls some business logic when
 *invoking it in the jsp. But should be fine for now.
 *
 */
public class WebUserInfo
{
    
    public static final String NAME = "webUserInfo"; 
    protected WebUser user;
    protected WebInfo info;
    protected Properties ctx;
    protected String orgName;
    protected String roleName;
    protected Integer roleId;
    protected Integer orgId;
    protected ArrayList menuItem;
    protected Integer unreadMessageCount;
    protected Boolean canAlterOrder;
    protected Boolean canCreateOrder;
    protected Boolean canViewOrder;
    
    
    
    public Boolean getCanAlterOrder() {
        return canAlterOrder;
    }
    public void setCanAlterOrder(Boolean canAlterOrder) {
        this.canAlterOrder = canAlterOrder;
    }
    public Boolean getCanCreateOrder() {
        return canCreateOrder;
    }
    public void setCanCreateOrder(Boolean canCreateOrder) {
        this.canCreateOrder = canCreateOrder;
    }
    public Boolean getCanViewOrder() {
        return canViewOrder;
    }
    public void setCanViewOrder(Boolean canViewOrder) {
        this.canViewOrder = canViewOrder;
    }
   
    public Integer getUnreadMessageCount()
    {
        return unreadMessageCount;
    }
    
    public void setUnreadMessageCount(Integer unreadMessageCount)
    {
        this.unreadMessageCount = unreadMessageCount;
    }
    
    public ArrayList getMenuItem()
    {
        return menuItem;
    }
    
    public void setMenuItem(ArrayList menuItem)
    {
        this.menuItem = menuItem;
    }
    
    public Integer getOrgId()
    {
        return orgId;
    }
    
    public void setOrgId(Integer orgId)
    {
        this.orgId = orgId;
    }
   
    
    public Integer getRoleId()
    {
        return roleId;
    }
   
    public void setRoleId(Integer roleId)
    {
        this.roleId = roleId;
        Env.setContext(ctx,"#AD_Role_ID",roleId.intValue());
    }
    public WebUserInfo(Properties ctx, WebUser user, WebInfo info) throws DataException, OperationException
    {
        this.user = user;
        this.ctx =ctx;
        this.info = info;
              
        MUser muser = new MUser(ctx, user.getAD_User_ID(), null);
        		
        // TODO think about this Little hack
//        Env.setContext(ctx, "#AD_Org_ID", muser.getAD_Org_ID());
        
//        setOriginalUserID(ctx, muser);
        
        orgId = Integer.valueOf(muser.getAD_Org_ID());
        
        KeyNamePair[] roles = getRoles(muser.getName(),muser.getPassword());
	    
        
	    if (roles == null || roles.length == 0)
	        throw new DataException("No role has been set for your user, Please check with your administrator to assign one for you.");
	    
	    /*	    
	    if (roles.length > 1 )
	        throw new DataException("You have been assigned many roles. You are permitted to have one role only. Please check with your administrator");
	     */
       
        setRoleId( Integer.valueOf(roles[0].getKey()));
        setRoleName();
        
    }
    
    /*public void setOriginalUserID(Properties ctx, MUser originalUser)
    {
    	int originalUserID = Env.getContextAsInt(ctx, "#AD_OriginalUser_ID");
    	
    	if (originalUserID == 0)
    		Env.setContext(ctx, "#AD_OriginalUser_ID", originalUser.getAD_User_ID());
    }*/
    
    private KeyNamePair[] getRoles (String app_user, String app_pwd) throws OperationException
    {
		
		if (app_user == null)
			return null;

		KeyNamePair[] retValue = null;
		ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
		//
		String sql = "SELECT AD_User.AD_User_ID, AD_User.Description,"
			+ " AD_Role.AD_Role_ID, AD_Role.Name "
			+ "FROM AD_User, AD_User_Roles, AD_Role "
			+ "WHERE AD_User.AD_User_ID=AD_User_Roles.AD_User_ID"
			+ " AND AD_User_Roles.AD_Role_ID=AD_Role.AD_Role_ID"
			+ " AND AD_User.Name=? AND AD_User.AD_CLIENT_ID=" + Env.getAD_Client_ID(ctx)		        							//	#1
			+ " AND AD_User.IsActive='Y' AND AD_Role.IsActive='Y' AND AD_User_Roles.IsActive='Y'";
		if (app_pwd != null)
			sql += " AND (AD_User.Password=? OR AD_User.Password=?)";   	//  #2/3
		sql += " ORDER BY AD_Role.Name";
		
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setString(1, app_user);
			if (app_pwd != null)
			{
				pstmt.setString(2, app_pwd);
				pstmt.setString(3, new Secure().getDigest(app_pwd));
			}
			//	execute a query
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next())
			{

				int AD_Role_ID = rs.getInt("AD_Role_ID");
				String Name = rs.getString("Name");
				KeyNamePair p = new KeyNamePair(AD_Role_ID, Name);
				list.add(p);
			}

			rs.close();
			pstmt.close();
			pstmt = null;
			//
			retValue = new KeyNamePair[list.size()];
			list.toArray(retValue);
		}
		catch (SQLException ex)
		{
		    ex.printStackTrace();
			retValue = null;
		}
		//
		try
		{
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		return retValue;
	}	//	getRoles

    
    /**
     * @return Returns the info.
     */
    public WebInfo getInfo()
    {
        return info;
    }
    /**
     * @param info The info to set.
     */
    public void setInfo(WebInfo info)
    {
        this.info = info;
    }
    /**
     * @return Returns the user.
     */
    public WebUser getUser()
    {
        return user;
    }
    /**
     * @param user The user to set.
     */
    public void setUser(WebUser user)
    {
        this.user = user;
    }
    
    public String getOrgName()
    {
        if (orgName ==null)
        {
            MUser muser = new MUser(ctx,user.getAD_User_ID(),null);
            MOrg org = new MOrg (ctx,muser.getAD_Org_ID(),null);
            orgName = org.getName();
        }

        return orgName;
        
    }
    
    public String getRoleName()
    {
        MRole role = new MRole(ctx, getRoleId().intValue(),null);
        return role.getName();
    }
    
    public MLocation getLocation()
    {
    	
    	MUser muser = new MUser(ctx, user.getAD_User_ID(),null); 
    	MOrg org = new MOrg(ctx,muser.getAD_Org_ID(),null);
    	
    	int locationId = org.getInfo().getC_Location_ID();
    	MLocation mlocation = MLocation.get(ctx, locationId, null);
    	
    	return mlocation;
    }
   
    public void setRoleName() 
    {
        this.roleName = getRoleName();
    }
}
