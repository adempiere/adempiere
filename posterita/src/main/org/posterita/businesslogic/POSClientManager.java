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

/**
	@author ashley
 */

package org.posterita.businesslogic;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Properties;

import org.compiere.model.MBPartner;
import org.compiere.model.MBankAccount;
import org.compiere.model.MCashBook;
import org.compiere.model.MClient;
import org.compiere.model.MCommission;
import org.compiere.model.MCurrency;
import org.compiere.model.MDiscountSchema;
import org.compiere.model.MLocation;
import org.compiere.model.MLocator;
import org.compiere.model.MOrg;
import org.compiere.model.MOrgInfo;
import org.compiere.model.MPOS;
import org.compiere.model.MPriceList;
import org.compiere.model.MPriceListVersion;
import org.compiere.model.MRole;
import org.compiere.model.MStore;
import org.compiere.model.MTaxCategory;
import org.compiere.model.MUser;
import org.compiere.model.MWarehouse;
import org.compiere.util.Env;

import org.posterita.exceptions.ClientAlreadyExistException;
import org.posterita.exceptions.OperationException;
import org.posterita.factory.GenericProductAttributeFactory;
import org.posterita.factory.GenericProductAttributeSetFactory;
import org.posterita.factory.GenericSystemObjectsFactory;
import org.posterita.factory.POSMenuFactory;
import org.posterita.factory.SystemObjectsFactory;
import org.posterita.lib.UdiConstants;
import org.posterita.model.MBank;
import org.posterita.model.MRoleMenu;
import org.posterita.model.UDIMBank;
import org.posterita.model.UDIMBankAccount;
import org.posterita.model.UDIMLocator;
import org.posterita.model.UDIMPOS;
import org.posterita.model.UDIMPriceListVersion;
import org.posterita.model.UDIMStore;
import org.posterita.model.UDIMUser;
import org.posterita.model.UDIMWarehouse;
import org.posterita.model.UDIU_Menu;
import org.posterita.model.UDIU_RoleMenu;
import org.posterita.core.bean.ClientBean;

public class POSClientManager extends ClientManager
{
	public static final String LOCATOR_SUFFIX = "Locator";
	public static final String SALES_REP_SUFFIX = "Sales Rep";
	public static final String DEFAULT_POS_TERMINAL = "Terminal 1";
	public static final String STORE_SUFFIX = "POS";
	public static final String taxCategoryName1 = "VAT";
	public static final String taxCategoryName2 = "Tax Exempt";
	public static final String CONTEXT_SUFFIX = "pos";
	public static final String STYLESHEET = "posteritaWarmsCSS.jsp";
	
	public static Properties createPOSDetails(Properties ctx, int currencyId, String currencyName, String hostURL, String trxName) throws OperationException
	{
		
		int adClientId = Env.getAD_Client_ID(ctx);
		int adOrgID = Env.getAD_Org_ID(ctx);
		
		MClient client = ClientManager.loadClient(ctx, adClientId, trxName);
		MOrg org = OrganisationManager.loadOrganisation(ctx, adOrgID, trxName);
		
		String clientName = client.getName();
		String orgName = org.getName();
		
		MOrgInfo orgInfo = org.getInfo();
		if(orgInfo == null)
			throw new OperationException("Organisation does not have an Organisation Info");
		
		int locationId = orgInfo.getC_Location_ID();
		if(locationId == 0)
			throw new OperationException("Organistion does not have a location");
		
		MLocation orgLocation = LocationManager.loadLocation(ctx, locationId, trxName);
		
		String city = orgLocation.getCity();
		int countryId = orgLocation.getCountry().get_ID();
		
		String whereClause = "AD_Client_ID=" + Env.getAD_Client_ID(ctx) + " and AD_Org_ID=" + Env.getAD_Org_ID(ctx);
		
		int discountSchemaIds[] = MDiscountSchema.getAllIDs(MDiscountSchema.Table_Name, whereClause, trxName);
		if(discountSchemaIds == null || discountSchemaIds.length == 0)
			throw new OperationException("No Discount schema defined");
		
		System.out.println("Creating Location...");
		
		String cashBookName = orgName + " Cash Book"; 
		String posTerminalName = DEFAULT_POS_TERMINAL;
		String salesPriceListName = orgName + " Sales Price List";
		String salesPriceListVersionName = orgName + " Sales Price List Version";
		String purchasePriceListName = orgName + " Purchase Price List";
		String purchasePriceListVersionName = orgName + " Purchase Price List Version";
		String warehouseName = orgName + " Warehouse";
		
		
		System.out.println("Creating Cash Book...");
		MCashBook cashBook = CashManager.createCashBook(ctx, cashBookName, currencyId, trxName);
		
		System.out.println("Creating Sales Pricelist...");
		MPriceList salesPriceList = PriceListManager.createPriceList(ctx, salesPriceListName, currencyId, new BigDecimal("2.0"), true, trxName);
		
		System.out.println("Creating Purchase Pricelist...");
		MPriceList purchasePriceList = PriceListManager.createPriceList(ctx, purchasePriceListName, currencyId, new BigDecimal("2.0"), false, trxName);
		
		MPriceListVersion salesPriceListVersion = new MPriceListVersion(salesPriceList);
		salesPriceListVersion.setName(salesPriceListVersionName);
		salesPriceListVersion.setM_DiscountSchema_ID(discountSchemaIds[0]);
		
		UDIMPriceListVersion udiSalesPriceListVersion = new UDIMPriceListVersion(salesPriceListVersion);
		udiSalesPriceListVersion.save();
		
		MPriceListVersion purchasePriceListVersion = new MPriceListVersion(purchasePriceList);
		purchasePriceListVersion.setName(purchasePriceListVersionName);
		purchasePriceListVersion.setM_DiscountSchema_ID(discountSchemaIds[0]);
		UDIMPriceListVersion udiPurchasePriceListVersion = new UDIMPriceListVersion(purchasePriceListVersion);
		udiPurchasePriceListVersion.save();
		
		System.out.println("Creating Bank...");
		MBank bank = new MBank(ctx, 0, trxName);
		bank.setName(orgName + " Bank");
		bank.setRoutingNo("123456789");
		bank.setC_Location_ID(orgLocation.get_ID());
		UDIMBank udiBank = new UDIMBank(bank);
		udiBank.save();
		
		System.out.println("Creating Bank Account...");
		MBankAccount bankAccount = new MBankAccount(ctx, 0, trxName);
		bankAccount.setC_Bank_ID(bank.get_ID());
		bankAccount.setBankAccountType(MBankAccount.BANKACCOUNTTYPE_Checking);
		bankAccount.setC_Currency_ID(currencyId);
		bankAccount.setAccountNo("123456789");
		bankAccount.setIsDefault(true);
		UDIMBankAccount udiBankAccount = new UDIMBankAccount(bankAccount);
		udiBankAccount.save();
		
		
		System.out.println("Creating Tax Categories...");
		MTaxCategory taxCategory15 = TaxManager.createTaxCategory(ctx, taxCategoryName1, trxName);
		MTaxCategory taxCategory0 = TaxManager.createTaxCategory(ctx, taxCategoryName2, trxName);
		
		System.out.println("Creating Tax...");
		TaxManager.createTax(ctx, taxCategoryName1, taxCategory15.get_ID(), countryId, new BigDecimal("15.0"),taxCategoryName1,false, trxName);
		TaxManager.createTax(ctx, taxCategoryName2, taxCategory0.get_ID(), countryId, new BigDecimal("0"), taxCategoryName1,true,trxName);
		
		System.out.println("Creating Warehouse...");
		MWarehouse warehouse = new MWarehouse(ctx, 0, trxName);
		warehouse.setName(warehouseName);
		warehouse.setC_Location_ID(orgLocation.get_ID());
		UDIMWarehouse udiWarehouse = new UDIMWarehouse(warehouse);
		udiWarehouse.save();
		
		System.out.println("Creating Locator...");
		MLocator locator = new MLocator(warehouse, warehouseName + " " + LOCATOR_SUFFIX);
		locator.setIsDefault(true);
		UDIMLocator udiLocator = new UDIMLocator(locator);
		udiLocator.save();
		
		System.out.println("Creating Business Partners...");
		// Create Cash Transaction Business Partner
		MBPartner defCashBPartner = BPartnerManager.createBPartner(ctx, org.getLinkedC_BPartner_ID(trxName), "Standard Customer", " ", true, false, false, false, " ", " ", city, " ", countryId, trxName);
		MBPartner salesRepBPartner = BPartnerManager.createBPartner(ctx, org.getLinkedC_BPartner_ID(trxName), orgName + " Sales Rep", " ", false, false, true, true, " ", " ", city, " ", countryId, trxName);
				
		MUser salesRepUser = new MUser(ctx, 0, trxName);
		salesRepUser.setC_BPartner_ID(salesRepBPartner.get_ID());
		salesRepUser.setName(orgName + " " + SALES_REP_SUFFIX);
		UDIMUser udiUser = new UDIMUser(salesRepUser);
		udiUser.save();
		
		
		System.out.println("Creating POS Terminal...");
		MPOS posTerminal = new MPOS(ctx, 0, trxName);
		posTerminal.setName(posTerminalName);
		posTerminal.setAD_Org_ID(Env.getAD_Org_ID(ctx));
		posTerminal.setC_CashBook_ID(cashBook.get_ID());
		posTerminal.setM_PriceList_ID(salesPriceList.get_ID());
		posTerminal.setM_Warehouse_ID(warehouse.get_ID());
		posTerminal.setC_BPartnerCashTrx_ID(defCashBPartner.get_ID());
		posTerminal.setSalesRep_ID(salesRepUser.get_ID());
		UDIMPOS udiMPos = new UDIMPOS(posTerminal);
		udiMPos.save();
		
		System.out.println("Creating Web store configuration...");
		MStore store = new MStore(ctx, 0, trxName);
		store.setName(clientName + " " + STORE_SUFFIX);
		store.setM_PriceList_ID(purchasePriceList.get_ID());
		store.setWebContext(clientName + "pos");
		store.setSalesRep_ID(salesRepUser.get_ID());
		store.setAD_Org_ID(org.get_ID());
		store.setWebParam5(STYLESHEET);
		store.setWebParam6("pos");
		store.setURL(hostURL);
		store.setM_Warehouse_ID(warehouse.get_ID());
		UDIMStore udiStore = new UDIMStore(store);
		udiStore.save();
		
		// Setting ctx with new configurations
		Env.setContext(ctx, UdiConstants.POS_ID, String.valueOf(posTerminal.get_ID()));
		Env.setContext(ctx, UdiConstants.PRICELIST_CTX_PARAM, String.valueOf(purchasePriceList.get_ID()));
		Env.setContext(ctx, UdiConstants.POS_PURCHASE_PL_VERSION, String.valueOf(purchasePriceListVersion.get_ID()));
		
		return ctx;
	}
	
	public static MUser createAdmin(Properties ctx, String userName, String roleName, String password, String userPin, String address, String emailAddress, String trxName) throws OperationException
	{
		Properties nCtx = (Properties)ctx.clone();
		nCtx.setProperty("#AD_Client_ID", "0");
		nCtx.setProperty("#AD_Org_ID", "0");
		
		MRole role = RoleManager.getOrCreateRole(ctx, roleName, true, true, true, true, trxName);
		MUser user = UserManager.getOrCreateUser(ctx, userName, true, address, address, address, password, emailAddress, true, "", 0, role.get_ID(), userPin,MCommission.DOCBASISTYPE_Invoice,new BigDecimal(10),MCommission.FREQUENCYTYPE_Monthly,new BigDecimal(0),false,trxName);
		
		String whereClause = " AD_Role_ID=" + role.get_ID();
		
		int roleMenuIds[] = MRoleMenu.getAllIDs(MRoleMenu.Table_Name, whereClause, trxName);
		
		for(int i = 0; i < roleMenuIds.length; i++)
		{
			MRoleMenu roleMenu = new MRoleMenu(ctx, roleMenuIds[i], trxName);
			roleMenu.delete(true);
		}
		
		POSMenuFactory posMFactory = POSMenuFactory.getFactoryInstance(nCtx);
		Iterator keyIter = posMFactory.getAllKeys(nCtx).iterator();
		
		while(keyIter.hasNext())
		{
			String key = (String)keyIter.next();
			UDIU_Menu udiMenu = (UDIU_Menu)posMFactory.get(nCtx, key);
			MRoleMenu roleMenu = new MRoleMenu(ctx, 0, trxName);
			roleMenu.setAD_Role_ID(role.get_ID());
			roleMenu.setU_Menu_ID(udiMenu.getID());
			UDIU_RoleMenu udiRoleMenu = new UDIU_RoleMenu(roleMenu);
			udiRoleMenu.save();
		}
		
		return user;
	}
	
	
	public static Properties getCtx(Properties ctx, String trxName) throws OperationException
	{
		int adClientId = Env.getAD_Client_ID(ctx);
				
		MClient client = ClientManager.loadClient(ctx, adClientId, trxName);
		
		String clientName = client.getName();
		int posId = POSTerminalManager.getPOSIdByName(ctx, DEFAULT_POS_TERMINAL, null);
		Env.setContext(ctx, UdiConstants.POS_ID, String.valueOf(posId));
		
		String storeName = clientName + " " + STORE_SUFFIX;
		int storeId = StoreManager.getStoreIdByName(ctx, storeName, null);
		
		MStore store = new MStore(ctx, storeId, null);
		String storeContext = store.getWebContext();
		storeContext = storeContext.replace("/", "");
		int pricelistId = store.getM_PriceList_ID();
		
		Env.setContext(ctx, UdiConstants.PRICELIST_CTX_PARAM, String.valueOf(pricelistId));
		
		Env.setContext(ctx, UdiConstants.POS_PURCHASE_PL_VERSION, PriceListManager.getPriceListVersionID(ctx, pricelistId, null));
		
		ApplicationManager.setApplicationParametersInContext(ctx, storeContext);
		return ctx; 
	}
	
	public static String createClient(ClientBean clientBean) throws ClientAlreadyExistException, OperationException
	{
		Properties ctx = Env.getCtx();
		Env.setContext(ctx, UdiConstants.USER_ID_CTX_PARAM, "100"); // SuperUser
		
		if(clientBean == null)
			throw new OperationException("Bean cannot be null!!!");
		
		if(ClientManager.isClientPresent(clientBean.getClientName()))
			throw new ClientAlreadyExistException("Client with name: " + clientBean.getClientName() + " already exist!!!");
		
		MCurrency currency = new MCurrency(ctx, clientBean.getCurrencyId(), null);
		if(currency.get_ID() == 0)
			throw new OperationException("Could not load currency with id: " + clientBean.getCurrencyId());
		
		String hostUrl = "http://www." + clientBean.getClientName() + ".com/";
		
		ClientManager.getCreateClient(ctx, clientBean.getClientName(), clientBean.getOrgName(), clientBean.getCurrencyId(), currency.getDescription(), clientBean.getCountryId(), clientBean.getCity(), clientBean.getAddress1(), clientBean.getPostalAddress(), "");
		ctx = ClientManager.getCtx(ctx, clientBean.getClientName(), clientBean.getOrgName());
		POSClientManager.createPOSDetails(ctx, clientBean.getCurrencyId(), currency.getDescription(), hostUrl, null);
		
		try
		{
			SystemObjectsFactory.reloadFactory(ctx);
			GenericSystemObjectsFactory.reloadFactory(ctx);
			GenericProductAttributeSetFactory.reloadFactory(ctx);
			GenericProductAttributeFactory.reloadFactory(ctx);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		POSClientManager.createAdmin(ctx, clientBean.getUsername(), clientBean.getRoleName(), clientBean.getPassword(), clientBean.getUserPIN(), clientBean.getAddress1(), clientBean.getEmail(), null);
		
		String storeContext = clientBean.getClientName() + "pos";
		return storeContext;
	}
	
	
	public static void createDemoData(int clientId, int adOrgId, int userId)
	{
		
	}
}
