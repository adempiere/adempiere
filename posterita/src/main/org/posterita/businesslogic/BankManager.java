/**
 *  Product: Posterita Web-Based POS (an Adempiere Plugin)
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
 */

/**
	@author Alok Pathak
 */

package org.posterita.businesslogic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.model.MBankAccount;
import org.compiere.model.MClient;
import org.compiere.model.MLocation;
import org.compiere.util.Env;

import org.posterita.beans.BankAccountBean;
import org.posterita.beans.BankBean;
import org.posterita.core.CheckDuplicateEntities;
import org.posterita.exceptions.BankAlreadyExistException;
import org.posterita.exceptions.OperationException;
import org.posterita.lib.UdiConstants;
import org.posterita.model.MBank;
import org.posterita.model.UDIMBank;
import org.posterita.model.UDIMBankAccount;
import org.posterita.model.UDIMLocation;


public class BankManager
{
	private static final String ROUTING_NUMBER="12345";
	
	public static MBank createBank(Properties ctx, String bankName,String address1,String postalAddress1,String city,String routingNumber, String trxName) throws OperationException
	{
			    
		MBank bank = new MBank(ctx,0,trxName);
		
		if(CheckDuplicateEntities.checkDuplicateName(ctx,bankName,MBank.Table_Name))
			throw new BankAlreadyExistException("Bank already exists");		
		
		try
		{
			bank.setName(bankName);
			bank.setRoutingNo(ROUTING_NUMBER);
			
			MLocation location = new MLocation(ctx,bank.getC_Location_ID(),trxName);
			location.setAddress1(address1);
			location.setPostal_Add(postalAddress1);
			location.setCity(city);
			location.setC_Country_ID(UdiConstants.COUNTRY_MAURITIUS);
			
			UDIMLocation udiLocation = new UDIMLocation(location);
			udiLocation.save();
			
			bank.setC_Location_ID(udiLocation.getID());
				
			UDIMBank udiBank = new UDIMBank(bank);
			udiBank.save();
			
		}
		catch(OperationException e)
		{
			throw new OperationException("Cannot create bank!!");
		}
		
		return bank;
		
	}
	
	public static MBankAccount createBankAccount(Properties ctx,int bankId,String accountNo,String accountType,double currentBalance, String trxName) throws OperationException
	{
		MBankAccount account;
	
		try
		{
		    account = new MBankAccount(ctx,0,trxName);
			account.setC_Bank_ID(bankId);
			account.setAccountNo(accountNo);
			account.setBankAccountType(accountType);
			account.setC_Currency_ID(MClient.get(ctx).getC_Currency_ID());
			account.setIsDefault(true);
			account.setCurrentBalance(new BigDecimal(currentBalance));
			
			
			UDIMBankAccount udiBankAccount = new UDIMBankAccount(account);
			udiBankAccount.save();
		}
		catch(OperationException e)
		{
			throw new OperationException("Cannot create bank account!!");
		}
		
		return account;
		
	}

 
    public static ArrayList getAllBanks(Properties ctx) throws OperationException
    {
        ArrayList<BankBean> list = new ArrayList<BankBean>();
        
        //load all banks
        int clientId = Env.getAD_Client_ID(ctx);
        int orgId = Env.getAD_Org_ID(ctx);
        
        int id[] = MBank.getAllIDs(MBank.Table_Name," ad_client_id = "+clientId+" and ad_org_id = "+orgId +" and isactive = 'Y'" ,null);
        BankBean bean;
        
        for(int i=0; i<id.length; i++)
        {
            bean = getBank(ctx, id[i], null);
            
            list.add(bean);
            
        }
        
        return list;
    }
    
    public static BankBean getBank(Properties ctx, int bankId, String trxName) throws OperationException
    {
        if (bankId== 0)
    		throw new OperationException("BankId is invalid!");
        
        BankBean bean = new BankBean();
        
        MBank bank = new MBank(ctx, bankId, trxName);
        int locationId = bank.getC_Location_ID();
        
        MLocation location = new MLocation(ctx, locationId, trxName);       
     
        
        bean.setBankId(Integer.valueOf(bank.get_ID()));
        bean.setLocationId(Integer.valueOf(location.get_ID()));
        bean.setBankName(bank.getName());
        bean.setRoutingNumber(bank.getRoutingNo());
        bean.setAddress1(location.getAddress1());
        bean.setCity(location.getCity());            
        bean.setPostalAddress(location.getPostal_Add());
        
        return bean;
    }

   
    public static MBank editBank(Properties ctx, int bankId,String address1,String postalAddress1,String city, String trxName) throws OperationException
    {
        MBank bank = new MBank(ctx,bankId,null);
        
        if(bank==null)
        {
            throw new OperationException("Bank does not exist!");
        }
        
        int locationId = bank.getC_Location_ID();
        
       LocationManager.editLocation(ctx,locationId,address1,postalAddress1, null, city, trxName);
        
        return bank;   
        
    }
    
    public static void deleteBank(Properties ctx, int bankId) throws OperationException 
    {
               
        MBank bank = new MBank(ctx,bankId,null);        
        
        if(bank==null)
        {
            throw new OperationException("Bank does not exist!");
        }
        
        bank.setIsActive(false); 
        
        UDIMBank udiBank = new UDIMBank(bank);
        udiBank.save();        
               
    }
    
    public static int getOrCreateBank(Properties ctx) throws OperationException
    {
    	ArrayList allBanks = getAllBanks(ctx);
    	Integer bankId;
    	if (allBanks.size() == 0)
    	{
    		MBank bank = createBank(ctx,"Default Bank","Default Address", "Default Postal Address","Default City", "Default Routing Number", null);
    		bankId = bank.get_ID();
    	}
    	else
    	{
    		if (allBanks.size()>1)
    		throw new OperationException("Too many banks, not currently supported");
    		
    		BankBean bean = (BankBean)allBanks.get(0);
    		bankId = bean.getBankId();
    	}
    	
    	return bankId.intValue();
    	
    }
    
    public static int getorCreateMySingleBankAccount(Properties ctx, String trxName) throws OperationException
    {
    	int bankAccIds[] = MBankAccount.getAllIDs(MBankAccount.Table_Name, " ad_org_id=" + Env.getAD_Org_ID(ctx)+ " and ad_client_id=" + Env.getAD_Client_ID(ctx) + " and isDefault='Y'", trxName);
        MBankAccount orgBankAc;
        if (bankAccIds.length == 0)
        {
        	orgBankAc = new MBankAccount(ctx,0,trxName);
        	orgBankAc.setC_Bank_ID(getOrCreateBank(ctx));
        	orgBankAc.setC_Currency_ID(POSTerminalManager.getPOSDefaultSellCurrency(ctx).get_ID());
        	orgBankAc.save();
        }
        else
        {
        	if (bankAccIds.length >1)
        		throw new OperationException("This Client contains more than 1 bank account " +
        				"and is not currently supported, No of Bank Account:" +bankAccIds.length);
        	
        	orgBankAc = new MBankAccount(ctx, bankAccIds[0], trxName);	
        }
        return orgBankAc.get_ID();
    }
    
    public static ArrayList<BankAccountBean> getMyBankAccounts (Properties ctx) 
	{
    	ArrayList<BankAccountBean> list = new ArrayList<BankAccountBean>();
    	
    	int[] bankAccountIds = MBankAccount.getAllIDs(MBankAccount.Table_Name, "ad_org_id=" + Env.getAD_Org_ID(ctx), null);
    	
    	for (int i = 0; i < bankAccountIds.length; i++)
		{
			MBankAccount bankAccount = new MBankAccount(ctx, bankAccountIds[i], null);
			
			MBank bank = new MBank(ctx, bankAccount.getC_Bank_ID(), null);
			
			BankAccountBean bean = new BankAccountBean();

			bean.setBankName(bank.getName());
			bean.setAccountNo(bankAccount.getAccountNo());
			bean.setAccountType(bankAccount.getBankAccountType());
			bean.setCurrentBalance(new Double(bankAccount.getCurrentBalance().doubleValue()));
			
			list.add(bean);
		}
    	
    	
    	return list;
	}

}
