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
import java.util.Vector;

import javax.management.relation.RoleNotFoundException;

import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MCommission;
import org.compiere.model.MCommissionLine;
import org.compiere.model.MCountry;
import org.compiere.model.MLocation;
import org.compiere.model.MOrg;
import org.compiere.model.MRole;
import org.compiere.model.MRoleOrgAccess;
import org.compiere.model.MUser;
import org.compiere.model.MUserRoles;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.posterita.beans.AddressBean;
import org.posterita.beans.UserBean;
import org.posterita.beans.WebstoreUserBean;
import org.posterita.businesslogic.LocationManager;
import org.posterita.businesslogic.OrganisationManager;
import org.posterita.core.KeyNamePairUtil;
import org.posterita.exceptions.DuplicateUserPinException;
import org.posterita.exceptions.NoAccessToEditObjectException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.UserAlreadyExistsException;
import org.posterita.exceptions.UserNotFoundException;
import org.posterita.lib.UdiConstants;
import org.posterita.util.PoManager;


public class UserManager
{
    
    public static MUser createUser(Properties ctx,Integer orgId, String userName,String userSurname,boolean isSalesRep,String address,String postalAddress,String city,String password,String email,boolean isActive,String phone,int regionId,int roleId,int countryId, String userPIN, BigDecimal userDiscount,String docBasisType,BigDecimal amtMultiplier,String frequency,BigDecimal subtractAmt,boolean isFullAccess,String trxName) throws DuplicateUserPinException,OperationException
    {
        int [] userId = MUser.getAllIDs("AD_USER","AD_CLIENT_ID = "+Env.getAD_Client_ID(ctx),trxName);
        
        for(int i=0;i<userId.length;i++)
        {
            MUser existUser = new MUser(ctx,userId[i],trxName);
            if (userName.equalsIgnoreCase(existUser.getName()))
                throw new UserAlreadyExistsException("The user already exists");
            
        }
        
        MOrg parentOrg = OrganisationManager.getMyOrg(ctx);
        MBPartner bpartner = BPartnerManager.createBPartner(ctx, orgId.intValue(), parentOrg.getLinkedC_BPartner_ID(trxName), userName,userSurname,false,false,true,isSalesRep,address, postalAddress,city,phone,countryId, trxName);
        
        
        MUser user = new MUser(bpartner);
        user.setIsFullBPAccess(isFullAccess);
        user.setName(userName);
        user.setAD_Org_ID(bpartner.getAD_Org_ID());
        
        if(userPIN!=null)
        {
            int id[] = MUser.getAllIDs(MUser.Table_Name," AD_Client_ID=" + Env.getAD_Client_ID(ctx) + " and userPin='"+userPIN + "'",null);
            
            if(id.length>0)
                throw new DuplicateUserPinException("User Pin Already exists");
            else
                user.setUserPIN(userPIN);
        }
        user.setPassword(password);
        user.setEMail(email);
        user.setIsActive(isActive);
        user.setPhone(phone);
        
        PoManager.save(user);
        
        if(isSalesRep==true && docBasisType!=null && amtMultiplier!=null)
        {
           CommissionManager.getCreateCommission(ctx,bpartner.getName(),bpartner.get_ID(),docBasisType,amtMultiplier,frequency,subtractAmt,trxName);
        }
        
        MUserRoles userRoles = new MUserRoles(ctx, user.get_ID(), roleId, trxName);
        userRoles.save();
        
        MOrg org = new MOrg(ctx, orgId, trxName);
        MRoleOrgAccess roleOrgAccess = new MRoleOrgAccess(org, roleId);
        
        return user;
    }
    
    
    public static Vector getAllOrgUsers(Properties ctx, int orgID)
    {
        int ids[] =  MUser.getAllIDs(MUser.Table_Name, " AD_CLIENT_ID = "+Env.getAD_Client_ID(ctx)+" and AD_Org_ID=" + orgID + " and ISACTIVE='Y' order by name",null);
        
        MUser user;
        
        Vector<MUser> thisOrgUsers = new Vector<MUser>();
        for (int i = 0; i<ids.length; i++)
        {
            user = new MUser(ctx, ids[i],null);
            thisOrgUsers.add(user);
        }
        
        return thisOrgUsers;
        
    }
    
    /**
     * @param ctx
     * @param userId
     * @throws OperationException
     */
    public static void deleteUser(Properties ctx, int userId) throws OperationException
    {
        MUser user = new MUser(ctx,userId,null);
        
        if ( user == null )
        {
            throw new OperationException("User ID does not exist");
        }
        
        user.setIsActive(false);
        
        PoManager.save(user);
        
    }
    
    public static void activateUser(Properties ctx, int userId) throws OperationException
    {
        MUser user = new MUser(ctx,userId,null);
        
        if ( user == null )
        {
            throw new OperationException("User ID does not exist");
        }
        
        user.setIsActive(true);
        
        PoManager.save(user);
        
    }
    
    public static WebstoreUserBean getWebstoreUser(Properties ctx,int userId) throws OperationException
    {
        UserBean userBean = getUser(ctx, userId);
        
        MUser user = new MUser(ctx,userId,null);
        
        int partnerId = user.getC_BPartner_ID(); 
        MBPartner partner = new MBPartner(ctx, partnerId, null);
        
        userBean.setUsername(partner.getName());
        userBean.setUserSurname(partner.getName2());
        
        AddressBean addrBean = new AddressBean();
        
        MBPartnerLocation[] partnerLocations = MBPartnerLocation.getForBPartner(ctx, partnerId);
        
        for (int i = 0; i < partnerLocations.length; i++)
        {
            MBPartnerLocation partnerLocation = partnerLocations[i];
            
            MLocation location = partnerLocation.getLocation(true);
            
            addrBean.setBpartnerId(partnerId);
            addrBean.setUsername(partner.getName());
            addrBean.setUserSurname(partner.getName2());
            addrBean.setAddress1(location.getAddress1());
            addrBean.setAddress2(location.getAddress2());
            addrBean.setPostalAddress(location.getPostal_Add());
            addrBean.setCity(location.getCity());
            addrBean.setCountryId(location.getC_Country_ID());
            addrBean.setCountryName(location.getCountryName());
            addrBean.setCountryCode(location.getCountry().getCountryCode());
        }
        
        WebstoreUserBean webstoreUserBean = new WebstoreUserBean();
        webstoreUserBean.setUserBean(userBean);
        webstoreUserBean.setShipToAddrBean(addrBean);
        
        return webstoreUserBean;
    }
    
    
    public static UserBean getUser(Properties ctx,int userId) throws OperationException
    {
        MUser user = new MUser(ctx,userId,null);
        
        if ( user == null )
        {
            throw new OperationException("User ID does not exist");
        } 
        
        UserBean bean = new UserBean();
        
        int partnerId = user.getC_BPartner_ID();       
        
        MBPartner partner = new MBPartner(ctx,partnerId,null);
        
        
        
        MBPartnerLocation partnerLocation = new MBPartnerLocation(ctx, partner.getPrimaryC_BPartner_Location_ID(), null);
        
        MLocation location = LocationManager.loadLocation(ctx, partnerLocation.getC_Location_ID(), null);         
        
        MUserRoles[] userRole = MUserRoles.getOfUser(ctx,userId);  
        
        MRole role = new MRole(ctx,userRole[0].getAD_Role_ID(),null);
        
        bean.setOrgId(user.getAD_Org_ID());
        bean.setUserId(Integer.valueOf(user.get_ID()));
        bean.setIsFullAccess(user.isFullBPAccess());
        bean.setPartnerId(partnerId);
        bean.setPassword(user.getPassword());
        bean.setUsername(partner.getName());
        bean.setUserSurname(partner.getName2());
        bean.setAddress1(location.getAddress1());
        bean.setCity(location.getCity());
        bean.setPassword(user.getPassword());
        bean.setConfirmPassword(user.getPassword());
        bean.setPhone(partnerLocation.getPhone());
        bean.setIsSalesRep(Boolean.valueOf(partner.isSalesRep()));
        bean.setPostalAddress(location.getPostal_Add());
        bean.setEmail(user.getEMail());
        bean.setFax(partnerLocation.getFax());
        bean.setIsActive(Boolean.valueOf(user.isActive()));
        bean.setUserPIN(user.getUserPIN());
        //bean.setRegionId(Integer.valueOf(location.getC_Region_ID()));
        //bean.setRegion(location.getRegionName());
        bean.setLocationId(location.get_ID());
        MCountry country = location.getCountry();
        bean.setCountryName(country.getName());
        bean.setCountryId(location.getCountry().get_ID());
        bean.setCountryCode(country.getCountryCode());
        bean.setRegionId(location.getC_Region_ID());
        bean.setAddress2(location.getAddress2());
        bean.setUserDiscount(role.getUserDiscount());
        
        
        int [] commissionIds = MCommission.getAllIDs(MCommission.Table_Name," AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+" and C_BPARTNER_ID="+partnerId,null);
        
        if(commissionIds!=null && commissionIds.length>1)
            throw new OperationException("Sales rep has more than one commission"+commissionIds.length);
        
        if(commissionIds!=null && commissionIds.length>0)
        {
            MCommission commission=new MCommission(ctx,commissionIds[0],null);
            bean.setDocBasisType(commission.getDocBasisType());
            bean.setFrequencyType(commission.getFrequencyType());
            
            MCommissionLine [] line =commission.getLines();
            if(line.length!=1)
                throw new OperationException("either No commissionLine or more than One commission Line has bean defined for this bPartner");
            bean.setAmtMultiplier(line[0].getAmtMultiplier().multiply(new BigDecimal(100)));
            bean.setSubtractAmt(line[0].getAmtSubtract());
            
        }
        if(userRole.length>0)
        {                        
            bean.setRoleName(role.getName());
            bean.setRoleId(Integer.valueOf(role.get_ID()));
        }
        
        return bean;
        
    }
    
    
    public static ArrayList<UserBean> getUsers(Properties ctx, String searchText) throws OperationException
    {
        ArrayList<UserBean> usersList = new ArrayList<UserBean>();
        
        int clientId = Env.getAD_Client_ID(ctx);
        int orgId = Env.getAD_Org_ID(ctx);
		String userOrg = Env.getContext(ctx, UdiConstants.ROLE_VIEWABLE_ORGS_CTX_PARAM);        
        
        String sql = "select u.AD_User_ID from AD_User u, AD_User_Roles ur " + 
        " where u.AD_Client_ID= " + clientId + 
        " and u.AD_Org_ID in (" + userOrg + ")" +
        " and (Select bp.IsEmployee from C_BPartner bp where u.C_BPartner_ID=bp.C_BPartner_ID) = 'Y'" +
        " and ur.ad_user_id= u.ad_user_id" +
        " and ur.ad_role_id is not null";
        
        if (searchText != null)
        	sql = sql + " and upper(u.name) like '%" + searchText.toUpperCase() + "%'";
        
        PreparedStatement pstmt = null;
        try
        {
            pstmt = DB.prepareStatement(sql, null);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                int userId = rs.getInt(1);
                UserBean bean = getUser(ctx, userId);
                
               	usersList.add(bean);
            }
            rs.close();
        }
        catch(SQLException ex)
        {
            throw new OperationException("Could not retrieve employees list with sql: " + sql, ex);
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
        return usersList;
    }
    
    
    public static ArrayList<UserBean> getAllEmployees(Properties ctx) throws OperationException
    {
        ArrayList<UserBean> usersList = new ArrayList<UserBean>();
        
        int clientId = Env.getAD_Client_ID(ctx);
        int orgId = Env.getAD_Org_ID(ctx);
        
        String sql = "select AD_User_ID from AD_User u " + 
        " where u.AD_Client_ID= " + clientId + 
        " and u.AD_Org_ID=" + orgId +
        " and (Select bp.IsEmployee from C_BPartner bp where u.C_BPartner_ID=bp.C_BPartner_ID) = 'Y'";
        
        PreparedStatement pstmt = null;
        try
        {
            pstmt = DB.prepareStatement(sql, null);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                int userId = rs.getInt(1);
                UserBean bean = getUser(ctx, userId);
                usersList.add(bean);
            }
            rs.close();
        }
        catch(SQLException ex)
        {
            throw new OperationException("Could not retrieve employees list with sql: " + sql, ex);
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
        return usersList;
    }
    
    public static ArrayList getAllUsers(Properties ctx) throws OperationException
    {
        ArrayList<UserBean> list = new ArrayList<UserBean>();
        
        int clientId = Env.getAD_Client_ID(ctx);
        int orgId = Env.getAD_Org_ID(ctx);
        
        int id[] = MUser.getAllIDs(MUser.Table_Name," ad_client_id = "+clientId+" and ad_org_id = "+orgId,null);
        // int id[] = MUser.getAllIDs(MUser.Table_Name," ad_client_id = "+clientId+" and ad_org_id = "+orgId+" and isactive = 'Y'",null);
        //int id[] = MUser.getAllIDs(MUser.Table_Name," ad_client_id = "+clientId+" and isactive = 'Y'",null);
        
        for(int i=0;i<id.length;i++)
        {
            UserBean bean = getUser(ctx,id[i]);
            list.add(bean);
        }
        
        return list;
    }
    
    public static MUser editUser(Properties ctx, int orgId, int userId, int roleId, String password,String email, String phoneNo, String address1, String postalAddress1, String city, boolean isSalesRep, boolean isActive,String userPin,BigDecimal userDiscount,String docBasisType,BigDecimal amtMultiplier,String frequency,BigDecimal subtractAmt,boolean isFullAccess, String trxName) throws DuplicateUserPinException,OperationException
    {
        MUser user = new MUser(ctx,userId,null);
        
        if ( user == null )
        {
            throw new OperationException("User ID does not exist");
        }
		
        Boolean isEditable = RoleManager.isEditable(ctx, user.getAD_Org_ID());
		
		if (!isEditable)
			throw new NoAccessToEditObjectException("You do not have the right organisational access for editing");			
        
        
        try
        {
        	user.setAD_Org_ID(orgId);
            user.setPassword(password);
            user.setIsActive(isActive);
            user.setEMail(email);
            user.setIsFullBPAccess(isFullAccess);
            
            if(userPin!=null)
            {
                int id[] = MUser.getAllIDs(MUser.Table_Name," AD_Client_ID=" + Env.getAD_Client_ID(ctx) + " and userPin='"+userPin+"'",null);
                
                if(id.length>0)
                {
                    for(int i=0;i<id.length;i++)
                    {
                        if(id[i]!=userId)
                            throw new DuplicateUserPinException("User Pin Already exists");
                    }
                    
                }
                else
                    user.setUserPIN(userPin);
                    
                
            }
            
            MBPartner partner = new MBPartner(ctx,user.getC_BPartner_ID(),trxName);
            partner.setIsSalesRep(isSalesRep);
            
            if(isSalesRep==true && docBasisType!=null && amtMultiplier!=null)
            {
                CommissionManager.getCreateCommission(ctx,partner.getName(),partner.get_ID(),docBasisType,amtMultiplier,frequency,subtractAmt,trxName);
            }
            
         
            PoManager.save(partner);
            
            PoManager.save(user);
            
            //editing location
            int locationId = getLocationId(ctx,userId);        
            LocationManager.editLocation(ctx,locationId,address1,postalAddress1,null,city, trxName);
            
            
            MBPartnerLocation bplocation = new MBPartnerLocation(ctx, partner.getPrimaryC_BPartner_Location_ID(), null);
            bplocation.setPhone(phoneNo);
            
            PoManager.save(bplocation);
            
            //delete existing roles
            MUserRoles[] userRoles = MUserRoles.getOfUser(ctx,userId); 
            for(int i=0;i<userRoles.length;i++)
            {            
                userRoles[i].delete(true);
            }
            
            MUserRoles userRole = new MUserRoles(ctx,userId,roleId,trxName);
            userRole.save(); 
        }
        catch(OperationException e)
        {
            throw e;
        }
        
        return user;
    }
    
    public static int getLocationId(Properties ctx,int userId) throws OperationException
    {
        
        MUser user = new MUser(ctx,userId,null);
        
        if ( user == null )
            throw new OperationException("User ID does not exist");
        
        MBPartner partner = new MBPartner(ctx, user.getC_BPartner_ID(), null);
        
        MBPartnerLocation bpLocation = new MBPartnerLocation(ctx, partner.getPrimaryC_BPartner_Location_ID(), null);
        
        if (bpLocation == null)
            throw new OperationException("Partner Location does not exists!!");
        
        
        
        return bpLocation.getC_Location_ID();
    }
    
    public static int getOriginalRoleID(Properties ctx)
    {
        
        int originalUserID = Env.getContextAsInt(ctx, "#AD_OriginalUser_ID");
        MUserRoles roles[] = MUserRoles.getOfUser(ctx, originalUserID);
        
        return roles[0].getAD_Role_ID();	
    }
    
    public static MUser getOrCreateUser(Properties ctx,int orgId, String userName,boolean isSalesRep,String address,String postalAddress,String city,String password,String email,boolean isActive,String phone,int regionId,int roleId,String userPin,String commDocbaseType,BigDecimal commRate,String frequency, BigDecimal subtractAmt,boolean isFullAccess,String trxName) throws OperationException
    {
        int [] userId = MUser.getAllIDs(MUser.Table_Name," AD_CLIENT_ID = "+Env.getAD_Client_ID(ctx) + " and UPPER(Name)=UPPER('" + userName + "')",null);
        MUser user;
        int countryId =UdiConstants.COUNTRY_MAURITIUS;
        if(userId.length > 0)
            user = new MUser(ctx, userId[0], trxName);
        else
            user = createUser(ctx, orgId, userName,null, isSalesRep, address, postalAddress, city, password, email, isActive, phone, regionId, roleId,countryId,userPin,null,commDocbaseType,commRate,frequency,subtractAmt,isFullAccess,trxName);
        
        return user;
    }
    
    public static MUser loadUser(Properties ctx, int adUserID, String trxName) throws OperationException
    {
        MUser user = new MUser(ctx, adUserID, trxName);
        if(user.get_ID() == 0)
            throw new OperationException("Could not load user with id: " + adUserID);
        
        return user;
    }
    
    public static ArrayList<KeyNamePair> getSalesReps(Properties ctx, String trxName) throws OperationException
    {
        int adClientId = Env.getAD_Client_ID(ctx);
        int adOrgId = Env.getAD_Org_ID(ctx);
        
        String whereClause = "IsActive='Y' and AD_Client_ID=" + adClientId + " and AD_Org_ID=" + adOrgId;
        whereClause += " and C_BPartner_ID in (select C_BPartner_ID from C_BPartner where IsActive='Y' and IsSalesRep='Y')";
        
        try
        {
            return KeyNamePairUtil.getData(ctx, MUser.Table_Name, whereClause);
        }
        catch(SQLException ex)
        {
            throw new OperationException("Could not retrieve sales rep with whereClause: " + whereClause, ex);
        }
    }
       
    
    public static ArrayList<UserBean> updateUserListStatus(ArrayList<UserBean> list, Integer userId, Boolean status)
    {
    	if (list == null)
    		return list;
    	
    	for (int i = 0; i < list.size(); i++)
		{
    		UserBean bean = (UserBean) list.get(i);
    		
    		if (bean.getUserId().equals(userId))
    		{
    			bean.setIsActive(status);
    			
    			list.remove(i);
    			
    			list.add(i, bean);
    			
    			break;
    		}
		}
    	
		return list;
    		
    } 
    
    public static Integer getRoleId(Properties ctx, String userPIN, String trxName)  throws UserNotFoundException, RoleNotFoundException, OperationException
    {
        int ids[] = MUser.getAllIDs(MUser.Table_Name, "IsActive = 'Y' AND AD_Client_ID=" + Env.getAD_Client_ID(ctx) + " AND userPin='"+userPIN + "'", trxName);
        if(ids == null || ids.length == 0)
        {
            throw new UserNotFoundException();
        }       
        
        MUserRoles[] userRoles = MUserRoles.getOfUser(ctx, ids[0]);
        if(userRoles == null || userRoles.length == 0)
        {
            throw new RoleNotFoundException();
        }
        
        MUserRoles userRole = userRoles[0];
        int ad_role_id = userRole.getAD_Role_ID();
        
        return ad_role_id;
    }
    
    public static BigDecimal getDiscountAllowed(Properties ctx, String userPIN, String trxName) throws UserNotFoundException, RoleNotFoundException, OperationException
    {
    	
    	int ad_role_id = getRoleId(ctx, userPIN, trxName);
    	BigDecimal discountAllowed = RoleManager.getDiscountAllowed(ctx, ad_role_id, trxName);    	
    	
    	return discountAllowed;
    }
    
    public static boolean isOverridePriceLimitAllowed(Properties ctx, String userPIN, String trxName) throws UserNotFoundException, RoleNotFoundException, OperationException
    {
        int ad_role_id = getRoleId(ctx, userPIN, trxName);    	
    	boolean isOverrideAllowed = RoleManager.isOverridePriceLimitAllowed(ctx, ad_role_id, trxName);    	
    	
    	return isOverrideAllowed;
    }
    
    public static boolean isDiscountAllowedOnTotal(Properties ctx, String userPIN, String trxName) throws UserNotFoundException, RoleNotFoundException, OperationException
    {
        int ad_role_id = getRoleId(ctx, userPIN, trxName);
        boolean isDisountAllowedOnTotal = RoleManager.isDiscountAllowedOnTotal(ctx, ad_role_id, trxName);      
        
        return isDisountAllowedOnTotal;
    }
    
    public static boolean isDiscountUptoPriceLimit(Properties ctx, String userPIN, String trxName) throws UserNotFoundException, RoleNotFoundException, OperationException
    {
        int ad_role_id = getRoleId(ctx, userPIN, trxName);
        boolean isDiscountUptoPriceLimit = RoleManager.isDiscountUptoPriceLimit(ctx, ad_role_id, trxName);      
        
        return isDiscountUptoPriceLimit;
    }
    
}




