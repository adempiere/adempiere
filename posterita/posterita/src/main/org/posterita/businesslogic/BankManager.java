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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MBankAccount;
import org.compiere.model.MClient;
import org.compiere.model.MLocation;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.posterita.beans.BankAccountBean;
import org.posterita.beans.BankBean;
import org.posterita.core.CheckDuplicateEntities;
import org.posterita.exceptions.BankAlreadyExistException;
import org.posterita.exceptions.OperationException;
import org.posterita.lib.UdiConstants;
import org.compiere.model.MBank;
import org.posterita.util.PoManager;


public class BankManager
{
    private static final CLogger log = CLogger.getCLogger(BankManager.class);
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
			
			PoManager.save(location);
			
			bank.setC_Location_ID(location.get_ID());
				
			PoManager.save(bank);
			
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
			
			
			PoManager.save(account);
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
        
        PoManager.save(bank);
               
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
    
    public static int getDefaultBankAccountId(Properties ctx, int adOrgId, String trxName) throws OperationException
    {
        String whereClause = "AD_Client_ID=" + Env.getAD_Client_ID(ctx) + " AND AD_Org_ID=" + adOrgId + " AND IsDefault='Y'";
        
        int bankAccountIds[] = MBankAccount.getAllIDs(MBankAccount.Table_Name, whereClause, trxName);
        
        if (bankAccountIds.length == 0)
        {
            throw new OperationException("No default bank account found");
        }
        else
        {
            return bankAccountIds[0];
        }
    }
    
    
    /**
     * Retrieves all the active bank accounts that the user present in the application context can
     * have access to
     * @param ctx Context
     * @param trxName Transaction
     * @return List of Bank Account model data
     * @throws OperationException If the data cannot be retrieved
     */
    public static ArrayList<BankAccountBean> getBankAccounts(Properties ctx, String trxName) throws OperationException
    {
        StringBuffer sqlStmt = new StringBuffer(); 
        sqlStmt.append("SELECT b.C_Bank_ID, b.Name, ba.C_BankAccount_ID, ba.bankAccountType, ba.AccountNo, ba.Description ");
        sqlStmt.append("FROM C_Bank b INNER JOIN C_BankAccount ba ON b.C_Bank_ID=ba.C_Bank_ID ");
        sqlStmt.append("WHERE b.AD_Client_ID=? AND b.IsActive='Y' AND ba.IsActive='Y' AND b.AD_Org_ID IN (");
        sqlStmt.append(Env.getContext(ctx, UdiConstants.ROLE_EDITABLE_ORGS_CTX_PARAM));
        sqlStmt.append(")");
        sqlStmt.append(" AND NOT EXISTS (SELECT * FROM AD_OrgInfo oi WHERE oi.TransferBank_ID=b.C_Bank_ID)");
        
        ArrayList<Object> paramList = new ArrayList<Object>();
        paramList.add(new Integer(Env.getAD_Client_ID(ctx)));
        
        return getBankAccounts(ctx, sqlStmt.toString(), paramList, trxName);
    }
    
    /**
     * Retrieves all the active bank accounts present for an organisation
     * @param ctx Context
     * @param trxName Transaction
     * @return List of Bank Account model data
     * @throws OperationException If the data cannot be retrieved
     */
    public static ArrayList<BankAccountBean> getBankAccounts (Properties ctx, int adOrgId, String trxName) throws OperationException 
	{
    	StringBuffer sqlStmt = new StringBuffer(); 
    	sqlStmt.append("SELECT b.C_Bank_ID, b.Name, ba.C_BankAccount_ID, ba.bankAccountType, ba.AccountNo, ba.Description ");
    	sqlStmt.append("FROM C_Bank b INNER JOIN C_BankAccount ba ON b.C_Bank_ID=ba.C_Bank_ID ");
    	sqlStmt.append("WHERE b.AD_Client_ID=? AND b.IsActive='Y' AND ba.IsActive='Y' AND ba.AD_Org_ID=?");
    	sqlStmt.append(" AND NOT EXISTS (SELECT * FROM AD_OrgInfo oi WHERE oi.TransferBank_ID=b.C_Bank_ID)");
    	
    	ArrayList<Object> paramList = new ArrayList<Object>();
        paramList.add(new Integer(Env.getAD_Client_ID(ctx)));
        paramList.add(new Integer(adOrgId));
        
        return getBankAccounts(ctx, sqlStmt.toString(), paramList, trxName);
	}
    
    /**
     * Retrieves list of bank accounts based on the criteria defined in the SQL Statement
     * @param ctx Context
     * @param sqlSmt SQL Statement
     * @param params Parameters to be used to build the SQL
     * @param trxName Transaction
     * @return List of Bank Account
     * @throws OperationException If data cannot be retrieved
     */
    private static ArrayList<BankAccountBean> getBankAccounts(Properties ctx, String sqlSmt, ArrayList<Object> params, String trxName) throws OperationException
    {
        ArrayList<BankAccountBean> list = new ArrayList<BankAccountBean>();
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try
        {
            int paramIndex = 1;
            pstmt = DB.prepareStatement(sqlSmt, trxName);
            if (params != null)
            {
                for (int i = 0; i < params.size(); i++)
                {
                    pstmt.setObject(paramIndex++, params.get(i));
                }
            }
            rs = pstmt.executeQuery();
            
            while (rs.next())
            {
                BankAccountBean bean = new BankAccountBean();
                bean.setBankId(rs.getInt(1));
                bean.setBankName(rs.getString(2));
                bean.setBankAccountId(rs.getInt(3));
                bean.setAccountType(rs.getString(4));
                bean.setAccountNo(rs.getString(5));
                list.add(bean);
            }
        }
        catch (Exception ex)
        {
            log.log(Level.SEVERE, "Could not get bank account information", ex);
            throw new OperationException("Could not get bank account information", ex);
        }
        finally
        {
            DB.close(rs, pstmt);
        }
        
        return list;
    }
}
