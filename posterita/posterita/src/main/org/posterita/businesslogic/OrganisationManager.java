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
import java.util.StringTokenizer;
import java.util.logging.Level;

import org.compiere.model.MAcctSchema;
import org.compiere.model.MAttachment;
import org.compiere.model.MAttachmentEntry;
import org.compiere.model.MBPartner;
import org.compiere.model.MBank;
import org.compiere.model.MBankAccount;
import org.compiere.model.MCashBook;
import org.compiere.model.MLocation;
import org.compiere.model.MOrg;
import org.compiere.model.MOrgInfo;
import org.compiere.model.MUser;
import org.compiere.model.MWarehouse;
import org.compiere.print.MPrintFormatItem;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.posterita.Constants;
import org.posterita.beans.OrgBean;
import org.posterita.businesslogic.administration.BPartnerManager;
import org.posterita.businesslogic.administration.WarehouseManager;
import org.posterita.businesslogic.core.AccountingManager;
import org.posterita.businesslogic.core.AttachmentManager;
import org.posterita.core.CheckDuplicateEntities;
import org.posterita.core.exception.IOOperationException;
import org.posterita.core.utils.IOUtil;
import org.posterita.exceptions.LogoException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.OrganisationAlreadyExistsException;
import org.posterita.exceptions.WarehouseAlreadyExistsException;
import org.posterita.lib.UdiConstants;
import org.posterita.util.PoManager;

public class OrganisationManager
{
    private static final CLogger logger = CLogger.getCLogger(OrganisationManager.class);
    
    public static final int COUNTRY_ID = UdiConstants.COUNTRY_MAURITIUS;

   
    public static MOrg editOrganisation(Properties ctx, Integer orgId, String orgName, String description, 
    		String address1, String address2, String city, Integer countryId, String postalAddress1,
    		boolean isActive, String receiptFooterMsg, String trxName) throws OperationException
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
        mOrg.setDescription(description);
      //  mOrg.setIsVisible(isVisible);
        
        PoManager.save(mOrg);
                
        MBPartner bp = new MBPartner(ctx, mOrg.getLinkedC_BPartner_ID(trxName), trxName);
        bp.setName(orgName);
        bp.setValue(orgName + "_" + orgId);
        
        PoManager.save(bp);
        
        MUser user = new MUser(ctx, bp.getPrimaryAD_User_ID(), trxName);
        user.setName(orgName);
        user.setPassword(orgName);        
        PoManager.save(user);
        
        MOrgInfo orgInfo = mOrg.getInfo();        
        MLocation location = updateOrCreateLocation(ctx, orgInfo.getC_Location_ID(), orgInfo.getAD_Org_ID(), address1,
        		address2, postalAddress1, city, countryId, trxName);		
	    
        orgInfo.setC_Location_ID(location.get_ID());
        orgInfo.setReceiptFooterMsg(receiptFooterMsg);
        
        PoManager.save(orgInfo);
        orgInfo.set_TrxName(trxName);
       
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
    

    
    public static OrgBean getOrganisation(Properties ctx, int orgId) throws OperationException
    {
        OrgBean bean = null;
        
        MOrg mOrg = new MOrg(ctx, orgId, null);
        
        MOrgInfo orgInfo = mOrg.getInfo();
        if (orgInfo != null)
        {
        	MLocation location = MLocation.get(ctx, orgInfo.getC_Location_ID(), null);
        
	        bean = new OrgBean();		    
	        bean.setOrgId(Integer.valueOf(mOrg.get_ID()));
	        bean.setOrgName(mOrg.getName());
	        bean.setCity(location.getCity());
	        bean.setPostalAddress(location.getPostal_Add());
	        bean.setAddress1(location.getAddress1());
	        bean.setAddress2(location.getAddress2());
	        bean.setRegion(location.getRegionName()); 	    
	        bean.setIsActive(Boolean.valueOf(mOrg.isActive()));
	        bean.setReceiptFooterMsg(orgInfo.getReceiptFooterMsg());
        }
        return bean;
    }
    
    public static void deactivateOrganisation(Properties ctx, int orgId) throws OperationException
    {
        MOrg mOrg = new MOrg(ctx,orgId,null);
        
        if(mOrg==null)
            throw new OperationException("Organisation does not exist");
        
        mOrg.setIsActive(false);
        
        mOrg.save();
        
    }
       
    public static MOrg getOrgByName(Properties ctx, String orgName, String trxName) throws OperationException
    {
        int orgIds[] = MOrg.getAllIDs(MOrg.Table_Name, "ad_client_id=" + Env.getAD_Client_ID(ctx) + " and name='" + orgName + "'", trxName);
        
        if (orgIds.length == 0)
            throw new OperationException("Org not found" + orgName);
        
        return new MOrg(ctx, orgIds[0], trxName);
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
    		PoManager.save(printFormatItem);
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
    
    
    
    
    //-----------------------------------------------------------------------------------------------------------------
    /**
     * Returns all organisations that match this organisation name or return all organisations if orgName is null.
     * 
     * @return arraylist of organisation beans
     * @param ctx ctx
     * @param orgName organisation Name to load
     * @param trxName transaction name
     * @throws OperationException 
     */
    public static ArrayList<OrgBean> getOrganisations(Properties ctx, String orgName, String trxName) throws OperationException
    {
    	ArrayList<OrgBean> list = new ArrayList<OrgBean>();
    	
    	if (orgName == null)
    	{
    		orgName = "";
    	}
    	orgName = orgName.toUpperCase();
    	String whereClause = "AD_CLIENT_ID = " + Env.getAD_Client_ID(ctx) + " AND UPPER(NAME) LIKE '%" +orgName + "%'";
    	
    	int[] ids = MOrg.getAllIDs(MOrg.Table_Name, whereClause, trxName);
    	
    	for(int id : ids)
    	{
    		OrgBean bean = getOrganisation(ctx, id);
    		list.add(bean);
    	}
    	
    	return list;
    }
    
    /**
     * Updates or Creates organisation
     * @param ctx ctx
     * @param bean organisation bean
     * @param trxName transaction name
     * 
     * @return organisation
     * @throws OperationException
     * @throws WarehouseAlreadyExistsException 
     */
    public static MOrg updateOrCreateOrganisation(Properties ctx, OrgBean bean, String trxName) throws OperationException, WarehouseAlreadyExistsException
    {
    	Integer ad_org_id = bean.getOrgId(); 
    	MOrg org = null;
    	String address1 = bean.getAddress1() == null? "" : bean.getAddress1();
    	String address2 = bean.getAddress2() == null? "" : bean.getAddress2();
    	    	
    	if (ad_org_id == null || ad_org_id == 0)
    	{
    		        	
    		org = createOrganisation(ctx, bean.getOrgName(),bean.getDescription(),bean.getIsActive(), trxName);
    	    
    		// set org location
    		MLocation location = LocationManager.createLocation(ctx, org.get_ID(),address1, address2, bean.getPostalAddress(),
    				bean.getCity(), 0, bean.getCountryId(), trxName);
    		
    		// create a linked business partner for that organisation
    		BPartnerManager.createLinkedBPartner(ctx, org.getAD_Org_ID(), 0, org.getName(),
    				"", true, true, false, false, location.getAddress1(),
    				location.getPostal(), location.getCity(), null, location.getC_Country_ID(), trxName);
    		
    		// create a sales representative
    		MBPartner salesRepBPartner = BPartnerManager.createBPartner(ctx,  org.get_ID(),
    				org.getLinkedC_BPartner_ID(trxName), org.getName() + " Sales Rep", " ",
    				false, false, true, true, location.getAddress1(), location.getPostal(), location.getCity(),
    				" ", location.getC_Country_ID(), trxName);
    		MUser salesRepUser = new MUser(ctx, 0, trxName);
    		salesRepUser.setC_BPartner_ID(salesRepBPartner.get_ID());
    		salesRepUser.setName(org.getName() + " " + "Sales Rep");
    		PoManager.save(salesRepUser);
    		  		
    		// warehouse with default locators
    		String warehouseName = org.getName() + " Warehouse";
    		MWarehouse warehouse = WarehouseManager.createWarehouse(ctx, org.get_ID(), warehouseName, address1, location.getPostal(),
    				location.getCity(), location.getC_Region_ID(), location.getC_Country_ID(), trxName);
    	
    		// web store
    		String hostUrl = "http://www." + org.getName() + ".com/";
    		StoreManager.createStore(ctx, org.getName(), org.getName()+" pos", org.getAD_Org_ID(), 
    				Env.getContextAsInt(ctx, UdiConstants.PRICELIST_CTX_PARAM), 
    				salesRepUser.getAD_User_ID(), warehouse.getM_Warehouse_ID(), "pos", hostUrl, trxName);
    		
    		// load orgInfo and set details
    		MOrgInfo orgInfo = org.getInfo();
    		orgInfo.set_TrxName(trxName);
    		orgInfo.setC_Location_ID(location.getC_Location_ID());
    		orgInfo.setM_Warehouse_ID(warehouse.getM_Warehouse_ID());
    		orgInfo.setReceiptFooterMsg(bean.getReceiptFooterMsg());
    		PoManager.save(orgInfo);    		    		
    		
    		// cashbook
    		String cashBookName = org.getName() + " Cash Book";    		
    		MAcctSchema[] acctSchema = MAcctSchema.getClientAcctSchema(ctx, org.getAD_Client_ID());
    		int currencyId = acctSchema[0].getC_Currency_ID();    		
    		MCashBook cashBook = CashManager.createCashBook(ctx, org.get_ID(), cashBookName, currencyId, trxName);
    		
    		MBPartner defCashBPartner = BPartnerManager.saveBPartner(ctx, org.getAD_Org_ID(), 0, org.getLinkedC_BPartner_ID(trxName), 
    				org.getName() + " Standard Customer", " ", true, false, false, false, " ", " ", 
    				location.getPostal(), location.getC_Region_ID(),location.getCity(), "", "", "", location.getC_Country_ID(),
    				true, true, trxName);
    		
    		MBank bank = new MBank(ctx, 0, trxName);
    		bank.setAD_Org_ID(org.getAD_Org_ID());
    		bank.setName(org.getName() + " Bank");
    		bank.setRoutingNo("123456789");
    		bank.setC_Location_ID(orgInfo.getC_Location_ID());
    		PoManager.save(bank);
    		
    		MBankAccount bankAccount = new MBankAccount(ctx, 0, trxName);
    		bankAccount.setAD_Org_ID(org.getAD_Org_ID());
    		bankAccount.setC_Bank_ID(bank.get_ID());
    		bankAccount.setBankAccountType(MBankAccount.BANKACCOUNTTYPE_Checking);
    		bankAccount.setC_Currency_ID(currencyId);
    		bankAccount.setAccountNo("123456789");
    		bankAccount.setIsDefault(true);
    		PoManager.save(bankAccount);
    		
    	}
    	
    	else
    	{
    		org = editOrganisation(ctx, ad_org_id, bean.getOrgName(), bean.getDescription(), address1, address2, bean.getCity(),
    				bean.getCountryId(), bean.getPostalAddress(), bean.getIsActive(), bean.getReceiptFooterMsg(), trxName);
    	}
    			
    	return org;
    }
    
  
	private static MOrg createOrganisation(Properties ctx, String orgName,
			String description, Boolean isActive,String trxName) throws OperationException 
	{
		
		MOrg org = new MOrg(ctx,0,trxName);
    	org.setName(orgName);  
    	org.setIsActive(isActive);
    	org.setDescription(description);
    	PoManager.save(org);
     
    	return org;
	}

	public static void activateOrganisation(Properties ctx, OrgBean bean, String trxName) throws OperationException
    {
    	int ad_org_id = bean.getOrgId(); 
    	
    	MOrg org = MOrg.get(ctx, ad_org_id);    	

    	boolean isActive = org.isActive();
    	org.setIsActive(!isActive);
    	
    	PoManager.save(org);    	
    }
	
	public static MLocation updateOrCreateLocation(Properties ctx,Integer locationId, Integer orgId, String address1,
			String address2, String postalAddress1,String city,int countryId, String trxName) throws OperationException
	{
		MLocation location = new MLocation(ctx,locationId,trxName);
		location.setAD_Org_ID(orgId);
		location.setAddress1(address1);
		location.setAddress2(address2);
		location.setCity(city);
		location.setPostal_Add(postalAddress1);
		location.setC_Country_ID(countryId);
		PoManager.save(location);
		
		return location;
	}
    public static ArrayList<KeyNamePair> getAllOrgPairs(Properties ctx, String trxName)
    {
    	ArrayList<KeyNamePair> pairList = new ArrayList<KeyNamePair>();
    	
    	int clientId = Env.getAD_Client_ID(ctx);
    	
    	int[] orgIds = MOrg.getAllIDs(MOrg.Table_Name, "ad_client_id=" + clientId, trxName);
    	
    	for (int i = 0; i < orgIds.length; i++) 
    	{
    		MOrg org = MOrg.get(ctx, orgIds[i]);
    		KeyNamePair pair = org.getKeyNamePair();
    		pairList.add(pair);
		}
    	
    	return pairList;
    }
    
    public static ArrayList<KeyNamePair> getUserOrgPairs(Properties ctx)
    {
    	return getUserOrgPairs(ctx, true);
    }
    
    public static ArrayList<KeyNamePair> getUserOrgPairs(Properties ctx, boolean viewStarOrg)
    {
    	ArrayList<KeyNamePair> pairList = new ArrayList<KeyNamePair>();

		String userOrg = Env.getContext(ctx, UdiConstants.ROLE_EDITABLE_ORGS_CTX_PARAM);
		
		StringTokenizer token = new StringTokenizer(userOrg, ",");
		
		while (token.hasMoreTokens())
		{
			String orgStrId = token.nextToken();
			int orgId = Integer.parseInt(orgStrId);
			
			if (orgId == 0)
			{
				if (viewStarOrg)
				{
					KeyNamePair allOrgPair = new KeyNamePair(0, Constants.ALL_ORGANISATIONS);
					pairList.add(allOrgPair);
				}
				
			}
			else
			{
				MOrg org = MOrg.get(ctx, orgId);
				pairList.add(org.getKeyNamePair());
			}
			
		}
    	
    	return pairList;
    }
    
    
    public static int[] getUserViewableOrganisations(Properties ctx)
    {
        int defaultOrganisations[] = new int[] {Env.getAD_Org_ID(ctx)};
        String viewableOrganisations = Env.getContext(ctx, UdiConstants.ROLE_VIEWABLE_ORGS_CTX_PARAM);
        
        if (viewableOrganisations == null || viewableOrganisations.trim().length() == 0)
        {
            return defaultOrganisations;
        }
        
        ArrayList<Integer> orgList = new ArrayList<Integer>();
        
        try
        {
            StringTokenizer tokenizer = new StringTokenizer(viewableOrganisations, ",");
            while (tokenizer.hasMoreTokens())
            {
                String org = tokenizer.nextToken();
                orgList.add(Integer.parseInt(org.trim()));
            }
        }
        catch (Exception ex)
        {
            logger.log(Level.SEVERE, "Could not retrieve organisations, cause: " + ex.getMessage(), ex);
        }
        
        if (orgList.size() > 0)
        {
            int organisations[] = new int[orgList.size()];
            System.arraycopy(orgList.toArray(), 0, organisations, 0, orgList.size());
            return organisations;
        }
        
        return defaultOrganisations;
    }
    
    
    public static int getCreateTransferBankAccount(Properties ctx, int adOrgId, int currencyId, String trxName) throws OperationException
    {
        String sqlStmt = "SELECT C_BankAccount_ID FROM C_BankAccount ba " 
                       + "INNER JOIN C_Bank b ON ba.C_Bank_ID=b.C_Bank_ID "
                       + "INNER JOIN AD_OrgInfo oi ON b.C_Bank_ID=oi.TransferBank_ID " 
                       + "WHERE oi.AD_Org_ID=? AND ba.C_Currency_ID=? ";
        
        int bankAccountId = DB.getSQLValue(trxName, sqlStmt, adOrgId, currencyId);
        
        if (bankAccountId != -1)
        {
            return bankAccountId;
        }
        
        MOrgInfo orgInfo = MOrgInfo.get(ctx, adOrgId, trxName);
        int bankId = orgInfo.getTransferBank_ID();
        
        if (bankId <= 0)
        {
            MBank bank = new MBank(ctx, 0, trxName);
            bank.setName("BANK ACCOUNT FOR TRANSFERS");
            bank.setDescription("** DO NOT USE **");
            bank.setRoutingNo("NOT USED");
            bank.setC_Location_ID(orgInfo.getC_Location_ID());
            bank.setIsOwnBank(true);
            
            PoManager.save(bank);
            bankId = bank.get_ID();
            
            orgInfo.set_TrxName(trxName);
            orgInfo.setTransferBank_ID(bankId);
            PoManager.save(orgInfo);
        }
        
        MBankAccount bankAccount = new MBankAccount(ctx, 0, trxName);
        bankAccount.setC_Bank_ID(bankId);
        bankAccount.setBankAccountType(MBankAccount.BANKACCOUNTTYPE_Checking); //  TODO Should consider to create a new type
        bankAccount.setAccountNo("NOT USED");
        bankAccount.setC_Currency_ID(currencyId);
        bankAccount.setIsDefault(false);
        bankAccount.setCurrentBalance(Env.ZERO);
        PoManager.save(bankAccount);
        
        return bankAccount.get_ID();
    }
    
    
    public static int getCreateTransferCashBook(Properties ctx, int adOrgId, int currencyId, String trxName) throws OperationException
    {
        MOrgInfo orgInfo = MOrgInfo.get(ctx, adOrgId, trxName);
        
        if (orgInfo.getTransferCashBook_ID() > 0)
        {
            return orgInfo.getTransferCashBook_ID();
        }
        
        MCashBook cashBook = new MCashBook(ctx, 0, trxName);
        cashBook.setAD_Org_ID(adOrgId);
        cashBook.setName("CASH BOOK FOR TRANSFERS");
        cashBook.setDescription("** DO NOT USE **");
        cashBook.setC_Currency_ID(currencyId);
        PoManager.save(cashBook);
        
        int dummyCashElementValue = AccountingManager.getCreateDummyCashElement(ctx, trxName);
        
        // No model defined for cashbook accounting
        StringBuffer updateStmt = new StringBuffer();
        
        updateStmt.append("UPDATE C_CashBook_Acct SET CB_Asset_Acct=");
        updateStmt.append(dummyCashElementValue);
        updateStmt.append(" WHERE C_CashBook_ID=");
        updateStmt.append(cashBook.get_ID());
        
        int updated = DB.executeUpdate(updateStmt.toString(), trxName);
        if (updated <= 0)
        {
            throw new OperationException("Could not update cash book accounting");
        }
        
        return cashBook.get_ID();
    }
}
