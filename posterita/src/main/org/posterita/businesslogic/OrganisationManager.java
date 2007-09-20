
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
 * Created on 21-Jun-2005 by alok
 *
 */
package org.posterita.businesslogic;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.model.MAttachment;
import org.compiere.model.MAttachmentEntry;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MBankAccount;
import org.compiere.model.MLocation;
import org.compiere.model.MOrg;
import org.compiere.model.MOrgInfo;
import org.compiere.model.MRole;
import org.compiere.model.MUser;
import org.compiere.model.MUserRoles;
import org.compiere.model.MWarehouse;
import org.compiere.print.MPrintFormatItem;
import org.compiere.util.Env;
import org.posterita.beans.OrgBean;
import org.posterita.core.CheckDuplicateEntities;
import org.posterita.core.ThumbnailGenerator;
import org.posterita.core.exception.IOOperationException;
import org.posterita.core.utils.IOUtil;
import org.posterita.exceptions.ImageAttachmentException;
import org.posterita.exceptions.LogoException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.OrganisationAlreadyExistsException;
import org.posterita.exceptions.WarehouseAlreadyExistsException;
import org.posterita.factory.MenuGenerator;
import org.posterita.lib.UdiConstants;
import org.posterita.model.MBank;
import org.posterita.model.UDIMBPartner;
import org.posterita.model.UDIMBPartnerLocation;
import org.posterita.model.UDIMLocation;
import org.posterita.model.UDIMOrg;
import org.posterita.model.UDIMOrgInfo;
import org.posterita.model.UDIMPrintFormatItem;
import org.posterita.model.UDIMRole;
import org.posterita.model.UDIMUser;
import org.posterita.model.UDIMUserRoles;


public class OrganisationManager
{
    public static final int COUNTRY_ID = UdiConstants.COUNTRY_MAURITIUS;
    
    public static UDIMOrg createOrganisation(Properties ctx, String orgName, String address1, String address2, String city,String postalAddress1,int regionId, boolean isRetailer, boolean isWholesaler, boolean isAuto, boolean isMoto,boolean isVisible, String trxName) throws OperationException, WarehouseAlreadyExistsException
    {
        String phone = "";
        String fax = "";
        
        return createOrganisation(ctx, orgName, address1, address2, city,postalAddress1, regionId,COUNTRY_ID,  isRetailer, isWholesaler, isAuto, isMoto,isVisible, phone, fax, trxName);
    }
    
    public static UDIMOrg createOrganisation(Properties ctx, String orgName, String address1, String address2, String city,String postalAddress1, int regionId, int countryId, boolean isRetailer, boolean isWholesaler, boolean isAuto, boolean isMoto,boolean isVisible, String phone, String fax, String trxName) throws OperationException, WarehouseAlreadyExistsException
    {
        
		int parentOrgId = Env.getAD_Org_ID(ctx);

		MOrg org = new MOrg(ctx, 0, trxName);
		if (CheckDuplicateEntities.checkDuplicateName(ctx, orgName, "AD_ORG"))
			throw new OrganisationAlreadyExistsException("The Organisation already exists");


		org.setValue(orgName);
		org.setName(orgName);
		//org.setIsVisible(isVisible);

		UDIMOrg udiOrg = new UDIMOrg(org);
		udiOrg.save();

		// Cloning the ctx
		Properties orgCtx = (Properties) ctx.clone();
		Env.setContext(orgCtx, "#AD_Org_ID", org.get_ID());

		int parentBpId = 0;

		if (parentOrgId != 0)
		{
			MOrg parentOrg = new MOrg(ctx, parentOrgId, null);
			parentBpId = parentOrg.getLinkedC_BPartner_ID(trxName);

		}
		// create default bp and link it to org
		
		MBPartner bPartner = BPartnerManager.saveBPartner(orgCtx,0, parentBpId, orgName, null, true, false, false, false, address1, address2, postalAddress1, regionId, city, phone, "", fax, countryId, true, true, trxName);
		
		bPartner.setAD_OrgBP_ID(org.get_ID());

		UDIMBPartner udiPartner = new UDIMBPartner(bPartner);
		udiPartner.save();

		// create default user

		MUser user = new MUser(bPartner);
		user.setName(org.getName());
		user.setPassword(org.getName());

		UDIMUser udiUser = new UDIMUser(user);
		udiUser.save();

		// UDIMRole orgRole =
		// RoleManager.createDefaultRoleForOrganisation(orgCtx);

		Properties ctx2 = (Properties) ctx.clone();
		Env.setContext(ctx2, "#AD_User_ID", 0);
		Env.setContext(ctx2, "#AD_Org_ID", org.get_ID());

		MRole mRole = new MRole(ctx2, 0, trxName);
		
/*		if (isWholesaler)
			mRole.setName(TmkConstants.WHOLESALER_ADMIN);
		if (isRetailer)
			mRole.setName(TmkConstants.RETAILER_ADMIN);
*/
		mRole.setName(orgName);
		
		UDIMRole udiRole = new UDIMRole(mRole);
		udiRole.save();

		int menuIds[] = MenuManager.getMenuIdForOrganisation(orgCtx);

		RoleMenuManager.createRoleMenus(orgCtx, mRole.get_ID(), menuIds, null);

		MenuGenerator.createMenus(orgCtx, udiRole);

		MUserRoles userRoles = new MUserRoles(orgCtx, user.get_ID(), mRole.get_ID(), trxName);
		
		UDIMUserRoles udiUserRoles = new UDIMUserRoles(userRoles);
		udiUserRoles.save();

		// create default warehouse and default locator
		MWarehouse warehouse = WarehouseManager.createWarehouse(orgCtx, orgName + " Warehouse", address1, postalAddress1, city, regionId, countryId, trxName);
		// create location
		MLocation location = LocationManager.createLocation(orgCtx, address1, address2, postalAddress1, regionId, city, countryId, trxName);

		// MBank orgBank = BankManager.createBank(orgCtx, org.getName(),
		// location.getAddress1(),location.getAddress2(), location.getCity(),
		// org.getName() + "12345");
		// BankManager.createBankAccount(orgCtx, orgBank.getID(), "12345678",
		// MBankAccount.BANKACCOUNTTYPE_Checking,10000);
		MBank orgBank = BankManager.createBank(orgCtx, org.getName(), location.getAddress1(), location.getAddress2(), location.getCity(), org.getName() + "12345", trxName);
		BankManager.createBankAccount(orgCtx, orgBank.get_ID(), "12345678", MBankAccount.BANKACCOUNTTYPE_Checking, 10000, trxName);
		// we have to save the, org info here because compiere cannot see the
		// org because of transaction
		MOrgInfo orgInfo = org.getInfo();
		orgInfo.setC_Location_ID(location.get_ID());
		orgInfo.setM_Warehouse_ID(warehouse.get_ID());

		UDIMOrgInfo udiOrgInfo = new UDIMOrgInfo(org.getInfo());
		udiOrgInfo.save();

		return udiOrg;
        
    }
    
    public static MOrg editOrganisation(Properties ctx, String orgName, String address, String city, String postalAddress, String phone, String fax, String trxName) throws OperationException
    {
    	int orgId = Env.getAD_Org_ID(ctx);
    	return editOrganisation(ctx, Integer.valueOf(orgId), orgName, address, city, postalAddress, phone, fax, true, true, trxName);
    }
    
    public static MOrg editOrganisation(Properties ctx, Integer orgId, String orgName, String address1, String city, String postalAddress1, String phone, String fax, boolean isActive, boolean isVisible, String trxName) throws OperationException
    {
    	return editOrganisation(ctx, orgId, orgName, address1, "", city, postalAddress1, phone, fax, isActive, isVisible, trxName);
    }
    
    public static MOrg editOrganisation(Properties ctx, Integer orgId, String orgName, String address1, String address2, String city, String postalAddress1, String phone, String fax, boolean isActive, boolean isVisible, String trxName) throws OperationException
    {
        
        if(orgId==null)
        {
            throw new OperationException("Invalid organisation id");
        }
                        
        MOrg mOrg = loadOrganisation(ctx, orgId.intValue(), trxName);
        
        if (!mOrg.getName().equals(orgName))
        {
            if(CheckDuplicateEntities.checkDuplicateName(ctx,orgName,"AD_ORG"))
                throw new OrganisationAlreadyExistsException("The Organisation already exists");
        }
        
        mOrg.setName(orgName);
        mOrg.setValue(orgName);
        mOrg.setIsActive(isActive);
      //  mOrg.setIsVisible(isVisible);
        
        UDIMOrg udiOrg = new UDIMOrg(mOrg);
        udiOrg.save();
                
        MBPartner bp = new MBPartner(ctx, mOrg.getLinkedC_BPartner_ID(trxName), trxName);
        bp.setName(orgName);
        bp.setValue(orgName + "_" + orgId);
        
        UDIMBPartner udibp = new UDIMBPartner(bp);
        udibp.save();
        
        MUser user = new MUser(ctx, bp.getPrimaryAD_User_ID(), trxName);
        user.setName(orgName);
        user.setPassword(orgName);
        
        UDIMUser udiUser = new UDIMUser(user);
        udiUser.save();
                
        MBPartnerLocation bpLocation = BPartnerManager.getBPartnerSingleLocation(ctx, mOrg.getLinkedC_BPartner_ID(trxName));
        
		MLocation location = new MLocation(ctx, bpLocation.getC_Location_ID(), trxName);
		location.setAddress1(address1);
		location.setAddress2(address2);
	    location.setCity(city);
	    location.setPostal_Add(postalAddress1);
	    
	    UDIMLocation udiLocation = new UDIMLocation(location);
	    udiLocation.save();
	    
        bpLocation.setPhone(phone);
        bpLocation.setFax(fax);
        UDIMBPartnerLocation udiPartnerLocation = new UDIMBPartnerLocation(bpLocation);
        udiPartnerLocation.save();
        
        MOrgInfo orgInfo = mOrg.getInfo();
        orgInfo.setC_Location_ID(location.get_ID());
        
        UDIMOrgInfo udiOrgInfo = new UDIMOrgInfo(orgInfo);
        udiOrgInfo.save();
       
        return mOrg;
    }

    public static MOrg getMyOrg(Properties ctx) throws OperationException
    {
    	return getMyOrg(ctx, null);
    }
    
    
    public static MOrg getMyOrg(Properties ctx, String trxName) throws OperationException
    {
        MOrg org = new MOrg(ctx, Env.getAD_Org_ID(ctx), trxName);
        
        if (org == null)
            throw new OperationException("Organisation cannot be resolved");
        
        return org;
    }
    
    
    
    public static int getLinkedBusinessPartnerID(Properties ctx, int orgId) throws OperationException
    {
        
        MOrg org = new MOrg(ctx, orgId, null);
        
        return org.getLinkedC_BPartner_ID(null);
        
        
    }
    
    
    
    public static ArrayList<OrgBean> getAllOrganisations(Properties ctx, String active, String visible) throws OperationException
    {
        
        ArrayList<OrgBean> list = new ArrayList<OrgBean>();
        OrgBean bean;
        
        String orgType = " isAutomobile='Y' ";
        String activeFilter;
        String visibleFilter;
            
            
        if ((active == null) || (active.equals("")))
            activeFilter = "";
        else
            activeFilter = " and isactive ='" + active + "'";
          
        if ((visible == null) || (visible.equals("")))
            visibleFilter = "";
        else
            visibleFilter = " and isvisible='" + visible + "'";
         
        int[] orgIds = MOrg.getAllIDs(MOrg.Table_Name, orgType + " and ad_client_id=" 
                + Env.getAD_Client_ID(ctx) 
                + activeFilter
                + visibleFilter               
                + " order by name" , null);
        
        
        for (int i = 0; i < orgIds.length; i++)
        {
            bean = getOrganisation(ctx,orgIds[i]);
            
            list.add( bean);
        }
        
        
        return list;
    }	
    
    
    public static OrgBean getOrganisation(Properties ctx, int orgId) throws OperationException
    {
        OrgBean bean = null;
        
        MOrg mOrg = new MOrg(ctx, orgId, null);
        MBPartner bp = new MBPartner(ctx, mOrg.getLinkedC_BPartner_ID(null), null);
        
        MBPartnerLocation bpLocations[] =  MBPartnerLocation.getForBPartner(ctx,bp.get_ID());
		
		if (bpLocations.length == 0)
		    throw new OperationException("No location has been set for your organisation. Please ask your administrator to set one for you");
        
		MLocation location = new MLocation(ctx, bpLocations[0].getC_Location_ID(), null);
		    
        MBPartnerLocation bpLocation = bpLocations[0];
        
        bean = new OrgBean();		    
        bean.setOrgId(Integer.valueOf(mOrg.get_ID()));
        bean.setOrgName(mOrg.getName());
        bean.setCity(location.getCity());
        bean.setPostalAddress(location.getPostal_Add());
        bean.setAddress1(location.getAddress1());
        bean.setAddress2(location.getAddress2());
        bean.setRegion(location.getRegionName()); 	    
       /* bean.setIsAutomobile(Boolean.valueOf(mOrg.isAutomobile()));
        bean.setIsMotorcycle(Boolean.valueOf(mOrg.isMotorcycle() ));
        bean.setIsRetailer(Boolean.valueOf(mOrg.isRetailer()));
        bean.setIsWholesaler(Boolean.valueOf(mOrg.isWholesaler()));*/
        bean.setPhone(bpLocation.getPhone());
        bean.setFax(bpLocation.getFax());
        bean.setIsActive(Boolean.valueOf(mOrg.isActive()));
       /* bean.setIsVisible(Boolean.valueOf(mOrg.isVisible()));*/
        
        //setting the region id
        //int id[] = MRegion.getAllIDs(MRegion.Table_Name," name ='"+ location.getRegionName() + "' and c_country_id =" + location.getC_Country_ID(),null);
        
        /*	    if(id!=null)
         bean.setRegionId(Integer.valueOf(id[0]));*/
        
        return bean;
    }
    
    public static void deleteOrganisation(Properties ctx, int orgId) throws OperationException
    {
        MOrg mOrg = new MOrg(ctx,orgId,null);
        
        if(mOrg==null)
            throw new OperationException("Organisation does not exist");
        
        mOrg.setIsActive(false);
        
        mOrg.save();
        
    }
    
    
    public static ArrayList<OrgBean> getAllWholesalers(Properties ctx) throws OperationException
    {
        
        ArrayList<OrgBean> list = new ArrayList<OrgBean>();
        OrgBean bean;
        
        int[] orgIds = MOrg.getAllIDs(MOrg.Table_Name," ad_client_id=" + Env.getAD_Client_ID(ctx) + "and iswholesaler = 'Y' and isactive = 'Y'" , null);
        
        
        for (int i = 0; i < orgIds.length; i++)
        {
            
            bean = getOrganisation(ctx,orgIds[i]);
            
            list.add( bean);
        }
        
        
        return list;
    }
    
   
    
    public static MOrg getTamakWholesalerOrg(Properties ctx) throws OperationException
    {
        int orgIds[] = MOrg.getAllIDs(MOrg.Table_Name, "ad_client_id=" + Env.getAD_Client_ID(ctx) + " and iswholesaler='Y'", null);
        
        if (orgIds.length == 0)
            throw new OperationException("No wholesaler org found");
        
        if (orgIds.length > 1)
            throw new OperationException("More than 1 wholesaler org found");
        
        MOrg wholeSalerOrg = new MOrg(ctx, orgIds[0], null);
        return wholeSalerOrg;
        
        
    }
    

    
    public static MOrg getOrgByName(Properties ctx, String orgName) throws OperationException
    {
    	return getOrgByName(ctx, orgName, null);
    }
    
    public static MOrg getOrgByName(Properties ctx, String orgName, String trxName) throws OperationException
    {
        int orgIds[] = MOrg.getAllIDs(MOrg.Table_Name, "ad_client_id=" + Env.getAD_Client_ID(ctx) + " and name='" + orgName + "'", trxName);
        
        if (orgIds.length == 0)
            throw new OperationException("Org not found" + orgName);
        
        return new MOrg(ctx, orgIds[0], trxName);
    }
    
    public static UDIMOrg getOrCreateOrganisation(Properties ctx, String orgName, String address1,String address2, String city,String postalAddress1,int regionId, boolean isRetailer, boolean isWholesaler, boolean isAuto, boolean isMoto, boolean isVisible, String phone, String fax, String trxName) throws OperationException, WarehouseAlreadyExistsException
    {
        int orgIds[] = MOrg.getAllIDs(MOrg.Table_Name, "ad_client_id=" + Env.getAD_Client_ID(ctx) + " and name='" + orgName + "'", null);
        UDIMOrg org;
        if (orgIds.length != 0)
            org = new UDIMOrg(new MOrg(ctx, orgIds[0], trxName));
        else
            org = createOrganisation(ctx, orgName, address1, address2, city, postalAddress1, regionId, COUNTRY_ID, isRetailer, isWholesaler, isAuto, isMoto, isVisible, phone, fax, trxName);
        return org;
        
    }
    
    
    public static MOrg loadOrganisation(Properties ctx, int adOrgID, String trxName) throws OperationException
    {
    	MOrg org = new MOrg(ctx, adOrgID, trxName);
    	if(org.get_ID() == 0)
    		throw new OperationException("Cannot load Organisation with id: " + adOrgID);
    	
    	return org;
    }
    
    public static void uploadLogo(Properties ctx, InputStream inStream, String trxName) throws OperationException
    {
    	byte imageData[] = IOUtil.getByteArray(inStream);
    	
    	int adClientId = Env.getAD_Client_ID(ctx);
    	
    	String imageName = UdiConstants.PRINTFORMAT_ITEM_LOGO + UdiConstants.IMAGE_EXTENSION;
    	String whereClause = "AD_Client_ID=" + adClientId + " and Name = '" + UdiConstants.PRINTFORMAT_ITEM_LOGO + "'";
    	
    	int logoPrintFormatItems[] = MPrintFormatItem.getAllIDs(MPrintFormatItem.Table_Name, whereClause, trxName);
    	for(int i = 0; i < logoPrintFormatItems.length; i++)
    	{
    		MPrintFormatItem printFormatItem = new MPrintFormatItem(ctx, logoPrintFormatItems[i], trxName);
    		AttachmentManager.deleteAvailableAttachment(ctx, MPrintFormatItem.Table_ID, logoPrintFormatItems[i], trxName);
    		AttachmentManager.addAttachment(ctx, MPrintFormatItem.Table_ID, logoPrintFormatItems[i], imageName, imageData, trxName);
    		printFormatItem.setImageIsAttached(true);
    		UDIMPrintFormatItem udiPrintFormatItem = new UDIMPrintFormatItem(printFormatItem);
    		udiPrintFormatItem.save();
    	}
    	
    	int adOrgId = Env.getAD_Org_ID(ctx);
    	
    	MAttachment attachment = FileAttachmentManager.deleteAvailableAttachmentEntry(ctx, MOrg.Table_ID, adOrgId, imageName, null);
    	
    	//TODO Refactor
    	if(attachment == null)
    		FileAttachmentManager.addAttachment(ctx, MOrg.Table_ID, adOrgId, imageName, imageData, null);
    	else
    		FileAttachmentManager.addAttachmentEntry(ctx, attachment, imageName, imageData, null);
    }
    
    public static byte[] getLogo(Properties ctx, String trxName) throws LogoException
    {
    	int adOrgId = Env.getAD_Org_ID(ctx);
    	
    	MAttachment attachment = MAttachment.get(ctx, MOrg.Table_ID, adOrgId);
    	
    	if(attachment == null)
    		throw new LogoException("Could not load logo for Organisation with id: " + adOrgId);
    	
    	ArrayList<MAttachmentEntry> attachmentList = AttachmentManager.getAttachmentEntriesWithPrefix(ctx, attachment, UdiConstants.PRINTFORMAT_ITEM_LOGO, trxName);
    	
    	try
    	{
	    	if(attachmentList.size() == 0)
	    		throw new LogoException("Could not load logo for Organisation with id: " + adOrgId);
	    	else if(attachmentList.size() > 1)
	    		throw new LogoException("Ambiguity in locating organisation logo; " + attachmentList.size() + " possible logos found for Organisation with id: " + adOrgId);
	    	else
	    		return IOUtil.getByteArray(attachmentList.get(0).getInputStream());
    	}
    	catch(IOOperationException ex)
    	{
    		throw new LogoException("Could not retrieve logo for organisation with id: " + adOrgId);
    	}
    }
    
    public static byte[] getFidelityImage(Properties ctx, String trxName) throws ImageAttachmentException
    {
    	int adOrgId = Env.getAD_Org_ID(ctx);
    	
    	MAttachment attachment = MAttachment.get(ctx, MOrg.Table_ID, adOrgId);
    	
    	if(attachment == null)
    		throw new ImageAttachmentException("Could not load fidelity image for organisation with id: " + adOrgId);
    	
    	ArrayList<MAttachmentEntry> entryList = AttachmentManager.getAttachmentEntriesWithPrefix(ctx, attachment, UdiConstants.FIDELITY_BACKGROUND_IMAGE, trxName);
    	
    	try
    	{
	    	if(entryList.size() == 0)
	    		throw new ImageAttachmentException("Could not load Fidelity Image for Organisation with id: " + adOrgId);
	    	else if(entryList.size() > 1)
	    		throw new ImageAttachmentException("Ambiguity in locating organisation Fidelity Image; " + entryList.size() + " possible logos found for Organisation with id: " + adOrgId);
	    	else
	    		return IOUtil.getByteArray(entryList.get(0).getInputStream());
    	}
    	catch(IOOperationException ex)
    	{
    		throw new ImageAttachmentException("Could not retrieve logo for organisation with id: " + adOrgId);
    	}
    }
    
    public static void uploadFidelityImage(Properties ctx, InputStream inStream, String trxName) throws OperationException
    {
    	byte imageData[] = IOUtil.getByteArray(inStream);
    	
    	String imageName = UdiConstants.FIDELITY_BACKGROUND_IMAGE + UdiConstants.IMAGE_EXTENSION;
    	int adOrgId = Env.getAD_Org_ID(ctx);
    	
    	byte thumbImageData[] = ThumbnailGenerator.getThumbnail(imageData, 207, 133);
    	FileAttachmentManager.deleteAvailableAttachmentEntry(ctx, MOrg.Table_ID, adOrgId, imageName, trxName);
    	FileAttachmentManager.addAttachment(ctx, MOrg.Table_ID, adOrgId, imageName, thumbImageData, trxName);
    }
    
}


