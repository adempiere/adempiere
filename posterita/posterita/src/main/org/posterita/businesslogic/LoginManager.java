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
 **/

package org.posterita.businesslogic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;

import org.compiere.model.MClient;
import org.compiere.model.MOrg;
import org.compiere.model.MUser;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.WebInfo;
import org.compiere.util.WebUser;
import org.posterita.beans.ChangePasswordBean;
import org.posterita.beans.LoginBean;
import org.posterita.beans.OrgUsersBean;
import org.posterita.beans.UserBean;
import org.posterita.businesslogic.administration.RoleManager;
import org.posterita.businesslogic.administration.UserManager;
import org.posterita.exceptions.DataException;
import org.posterita.exceptions.DuplicatePINException;
import org.posterita.exceptions.EmailNotFoundException;
import org.posterita.exceptions.InvalidPINException;
import org.posterita.exceptions.InvalidPasswordLengthException;
import org.posterita.exceptions.InvalidRoleException;
import org.posterita.exceptions.NotLoggedInException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.SystemException;
import org.posterita.exceptions.UserInactiveException;
import org.posterita.exceptions.UserNotFoundException;
import org.posterita.exceptions.WrongPasswordException;
import org.posterita.lib.UdiConstants;
import org.posterita.pos.help.POSHelpManager;
import org.posterita.user.WebUserInfo;
import org.posterita.util.PoManager;


public class LoginManager  
{
  
    public WebUser checkLoginPassword(Properties ctx, LoginBean bean) throws UserNotFoundException, UserInactiveException, SQLException, OperationException, NotLoggedInException 
    {
        WebUser wu = get (ctx, bean.getUsername());
        
        wu.login(bean.getPassword());
        
        // Ensure that the right user id is found in ctx, not the 1 used by compiere cache.
        Env.setContext(ctx, "#AD_User_ID", wu.getAD_User_ID());
        setRoleContext(ctx);
 		
		if (!wu.isLoggedIn())
		{
		    throw new NotLoggedInException();
		}
		
		return wu;
    }
    
    
    public void setRoleContext(Properties ctx) throws SQLException, OperationException
    {
        ArrayList<KeyNamePair> list = RoleManager.getMyRoles(ctx);
        
       for (KeyNamePair pair : list) 
       {
           Env.setContext(ctx, "#AD_Role_ID", pair.getKey());
           break;
       }
        
       Env.setContext(ctx,UdiConstants.ROLE_EDITABLE_ORGS_CTX_PARAM, RoleManager.getRoleEditableOrgAccess(ctx));
       Env.setContext(ctx,UdiConstants.ROLE_VIEWABLE_ORGS_CTX_PARAM, RoleManager.getRoleViewableOrgAccess(ctx));
    }
    
    public WebUserInfo login (Properties ctx, WebUser wu) throws NotLoggedInException, InvalidRoleException, DataException, OperationException, SystemException
    {
        
		WebInfo info = new WebInfo(ctx, wu);
		WebUserInfo udiInfo = new WebUserInfo(ctx,wu,info);
		
//		MRole currentRole = new MRole(ctx, udiInfo.getRoleId().intValue(), null);
		
		return udiInfo;
    } 
    
    public WebUserInfo loginUser(Properties ctx, LoginBean bean) throws UserNotFoundException, UserInactiveException, NotLoggedInException, SQLException, OperationException, InvalidRoleException, DataException, SystemException  
    {
        
//    	int originalRoleID = UserManager.getOriginalRoleID(ctx);
    	
        MUser user = new MUser(ctx,bean.getUserId().intValue(),null);
        
        bean.setUsername(user.getName());
        bean.setPassword(user.getPassword());
        WebUser wu = checkLoginPassword(ctx, bean);
        
        WebUserInfo wuInfo = login(ctx, wu);
        
        return wuInfo;
    }
  
    public boolean sendPassword(Properties ctx, UserBean bean, String subject, String content) throws SQLException, UserNotFoundException, EmailNotFoundException 
    {
        MUser user = load(ctx, bean.getUsername());
        
        if (user == null)
            throw new UserNotFoundException();
        
        String email = user.getEMail();
        
        if (email == null || email.trim().length() == 0)
            throw new EmailNotFoundException();
        
        content  = content + user.getPassword();
        
        MClient client = new MClient(ctx,Env.getAD_Client_ID(ctx),null);

        String from = "Support <support@posterita.org>";
        
        return POSHelpManager.sendEmail(ctx, from, email, subject, content);
                

    }
    
    
    public static void changePassword(Properties ctx, ChangePasswordBean bean, WebUserInfo userInfo) throws WrongPasswordException, OperationException
    {
    	
    	MUser user = new MUser(ctx, userInfo.getUser().getAD_User_ID(),null);
    	    	
    	String oldPassword = bean.getOldPassword();
    	
    	if (!oldPassword.equals(userInfo.getUser().getPassword()))
    		throw new WrongPasswordException();
        
    	String newPassword = bean.getNewPassword();
        
        if(newPassword.length()<6)
            throw new InvalidPasswordLengthException("The password Should be at least six characters long");
        
    	
    	user.setPassword(newPassword);
    	
    	PoManager.save(user);
  
    	WebUser webUser = WebUser.get(ctx,user.getEMail(),user.getPassword(), false);
   	
    	userInfo.setUser(webUser);
    }
    
    
  
    protected static MUser load(Properties ctx, String username) throws SQLException
    {
        MUser m_bpc = null;
        String sql = "SELECT * "
						+ "FROM AD_User "
						+ "WHERE AD_Client_ID=?"
						+ " AND Name=?";
        
		if (username == null)
		    username = "";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			pstmt.setString(2, username);
			
			rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				m_bpc = new MUser (ctx, rs, null);
			}
			
			rs.close();

		}
		catch (SQLException e)
		{
		   throw e;
		}
		finally
		{
			try
			{
				pstmt.close ();
			}
			catch (Exception e)
			{
			    
			}
			
			pstmt = null;
		}
		
		return m_bpc;
    }
    
   
    
    private static WebUser get(Properties ctx, String username) throws SQLException, UserNotFoundException, UserInactiveException
    {
        MUser m_bpc = load(ctx, username);
        
        if (m_bpc == null)
            throw new UserNotFoundException();        
        
        if (!m_bpc.isActive())
            throw new UserInactiveException();
        
        WebUser wu = WebUser.get(ctx, m_bpc.getAD_User_ID());     

        return wu;
    }
    
    public static ArrayList<OrgUsersBean> getOrgUsers(Properties ctx)
    {
        ArrayList<OrgUsersBean> orgUsers = new ArrayList<OrgUsersBean>();
        Vector users = null;        
        MOrg org = null;
        OrgUsersBean bean = null;
        
        int ad_client_id = Env.getAD_Client_ID(ctx);
        
        StringBuffer whereClause = new StringBuffer("");
        whereClause.append(" AD_CLIENT_ID = " + ad_client_id);
        whereClause.append(" AND ISACTIVE = 'Y'");
        whereClause.append(" AND ISVISIBLE = 'Y'");
        
        
        whereClause.append(" ORDER BY NAME");
        
        int[] orgIds = MOrg.getAllIDs(MOrg.Table_Name,whereClause.toString(),null);
        
        for(int i=0; i<orgIds.length; i++)
        {
            org = new MOrg(ctx,orgIds[i],null);
            users = UserManager.getAllOrgUsers(ctx,orgIds[i]);
            
            bean = new OrgUsersBean();
            bean.setOrgId(Integer.valueOf(orgIds[i]));
            bean.setOrgName(org.getName());
            bean.setOrgUsers(users);
            
            orgUsers.add(bean);
        }
        
        return orgUsers;
    }
    
    
    public static ArrayList<KeyNamePair> getAccessibleOrgsKeyNamePair(Properties ctx) throws SQLException
    {
        int ad_user_id = Env.getAD_User_ID(ctx);
        int ad_client_id = Env.getAD_Client_ID(ctx);
        
        String sql = "select distinct(org.AD_ORG_ID), org.name " +
        		" from AD_USER_ROLES usrRole, AD_USER usr, AD_ROLE role, AD_ORG org " +
        		" where org.AD_ORG_ID = role.AD_ORG_ID " +
        		" and usrRole.AD_USER_ID = usr.AD_USER_ID " +
        		" and usrRole.AD_ROLE_ID = role.AD_ROLE_ID " +
        		" and role.AD_CLIENT_ID = ? " +
        		" and usrRole.AD_USER_ID = ? " +
        		" order by org.name";
        
        PreparedStatement pstmt = DB.prepareStatement(sql, null);
        ResultSet rs = null;
        ArrayList<KeyNamePair> orgList = new ArrayList<KeyNamePair>();
        
        try
		{
			pstmt.setInt(1,ad_client_id);
	        pstmt.setInt(2,ad_user_id);
	        
	        rs = pstmt.executeQuery();

	        while(rs.next())
	        {
	            KeyNamePair pair = new KeyNamePair(rs.getInt(1),rs.getString(2));
	            
	            orgList.add(pair);
	        }       
	        
	        rs.close();
		}
		catch (SQLException e)
		{
			throw e;
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
        
        
        return orgList;
    }
    
    public static ArrayList getUserRolesKeyNamePair(Properties ctx, int ad_org_id) throws SQLException
    {
        int ad_user_id = Env.getAD_User_ID(ctx);
        int ad_client_id = Env.getAD_Client_ID(ctx);
        
        String sql = "select role.AD_ROLE_ID, role.NAME " +
		        		" from AD_USER_ROLES usrRole, AD_USER usr, AD_ROLE role, AD_ORG org " +
		        		" where org.AD_ORG_ID = role.AD_ORG_ID " +
		        		" and usrRole.AD_USER_ID = usr.AD_USER_ID " +
		        		" and usrRole.AD_ROLE_ID = role.AD_ROLE_ID " +
		        		" and role.AD_CLIENT_ID = ? " +
		        		" and role.AD_ORG_ID = ? " +
		        		" and usrRole.AD_USER_ID = ? " +
		        		" order by role.NAME";
        
        PreparedStatement pstmt = DB.prepareStatement(sql, null);
        ResultSet rs = null;
        ArrayList<KeyNamePair> roleList = new ArrayList<KeyNamePair>();
        
        try
		{
			pstmt.setInt(1,ad_client_id);
	        pstmt.setInt(2,ad_org_id);
	        pstmt.setInt(3,ad_user_id);
	        
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
			throw e;
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
    
    
    
    private static String getallOrgsAccess(Properties ctx) throws SQLException
    {
        String sql = "SELECT o.AD_Org_ID,o.Name,o.IsSummary "   //  1..3
            + "FROM AD_Role r, AD_Client c"
            + " INNER JOIN AD_Org o ON (c.AD_Client_ID=o.AD_Client_ID OR o.AD_Org_ID=0) "
            + "WHERE r.AD_Role_ID=?"    //  #1
            + " AND c.AD_Client_ID=?"   //  #2
            + " AND o.IsActive='Y'"
            + " AND (r.IsAccessAllOrgs='Y' "
                + "OR (r.IsUseUserOrgAccess='N' AND o.AD_Org_ID IN (SELECT AD_Org_ID FROM AD_Role_OrgAccess ra "
                    + "WHERE ra.AD_Role_ID=r.AD_Role_ID AND ra.IsActive='Y')) "
                + "OR (r.IsUseUserOrgAccess='Y' AND o.AD_Org_ID IN (SELECT AD_Org_ID FROM AD_User_OrgAccess ua "
                    + "WHERE ua.AD_User_ID=? AND ua.IsActive='Y'))"     //  #3
                + ") "
            + "ORDER BY o.Name";
        
        String orgIds="";
        PreparedStatement pstmt = null;
        new ArrayList<KeyNamePair>();
        ResultSet rs = null;
        
        try
        {
            pstmt = DB.prepareStatement(sql, null);
            pstmt.setInt(1, Env.getAD_Role_ID(ctx));
            pstmt.setInt(2, Env.getAD_Client_ID(ctx));
            pstmt.setInt(3, Env.getAD_User_ID(ctx));
            rs = pstmt.executeQuery();
           
            //  load Orgs
            while (rs.next())
            {
                if(orgIds=="")
                    orgIds = orgIds+rs.getInt(1)+"";
                else
                    orgIds = orgIds+","+rs.getInt(1)+"";
            }

            rs.close();
        }
        catch (SQLException ex)
        {
            throw ex;
        }
        finally
        {
            try
            {
                pstmt.close();
            }
            catch (Exception e)
            {}
            
            pstmt = null;
        }
        
       return orgIds;
        
    }
    
    public  LoginBean getUserNameFromPIN(Properties ctx,String userPIN) throws DuplicatePINException, InvalidPINException
    {
        String whereClause="AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+
                            " and USERPIN='"+userPIN+"'";
      
        int [] userIds = MUser.getAllIDs(MUser.Table_Name,whereClause,null);
        
        if(userIds.length>1)
           throw new DuplicatePINException("More than one user with same pin");
        if(userIds.length<1)   
            throw new InvalidPINException("Invalid PIN");
        
        MUser user = new MUser(ctx,userIds[0],null);
        
        LoginBean bean = new LoginBean();
        bean.setUsername(user.getName());
        bean.setPassword(user.getPassword());
        
        return bean;
        
    }
}
