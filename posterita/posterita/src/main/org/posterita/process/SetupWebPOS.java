/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 * Autor: Victor Perez victor.perez@e-evolution.con www.e-evolution.com       *
 *****************************************************************************/ 

package org.posterita.process;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.logging.Level;

import org.compiere.model.MRole;
import org.compiere.model.MRoleMenu;
import org.compiere.model.MUser;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.posterita.factory.POSMenuFactory;
import org.compiere.model.MWebMenu;
import org.posterita.util.PoManager;




/**
 * Setup Web POS in Adempiere
 * @author  victor.perez@e-evolution
 */
public class SetupWebPOS extends SvrProcess
{
	
	
	private int p_AD_Org_ID = 0;
	private int p_C_CashBook_ID = 0;
	private int p_M_PriceList_Version_ID = 0;
	private int p_PriceList_Version_ID = 0;
	private int p_M_DiscountSchema_ID = 0;
	private int p_C_Bank_ID = 0;
	private int p_C_BankAccount_ID = 0 ;
	private int p_C_TaxCategory_ID = 0 ;
	private int p_M_Locator_ID = 0;
	private int p_C_BPartner_ID = 0;
	private int p_SalesRep_ID = 0;
	private int p_AD_Role_ID = 0;
	private String p_userPIN = "";
	private String p_POS_Name = "Terminal 1";
	private String p_HostAddress = "";
	
	private int AD_Client_ID = 0;
	
	
	
	/**
	 *	Prepare
	 */
	protected void prepare() 
	{
		AD_Client_ID = Env.getAD_Client_ID(getCtx());
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			
			if (para[i].getParameter() == null)
				;
			else if (name.equals("AD_Org_ID"))
			{
				p_AD_Org_ID = para[i].getParameterAsInt();
			}
			else if (name.equals("C_CashBook_ID"))
			{
				p_C_CashBook_ID = para[i].getParameterAsInt();
			}
			else if (name.equals("M_PriceList_Version_ID"))
			{
				p_M_PriceList_Version_ID = para[i].getParameterAsInt();
			}
			else if (name.equals("PriceList_Version_ID"))
			{
				p_PriceList_Version_ID = para[i].getParameterAsInt();
			}
			else if (name.equals("M_DiscountSchema_ID"))
			{
				p_M_DiscountSchema_ID = para[i].getParameterAsInt();
			}
			else if (name.equals("C_Bank_ID"))
			{
				p_C_Bank_ID = para[i].getParameterAsInt();
			}
			else if (name.equals("C_BankAccount_ID"))
			{
				p_C_BankAccount_ID = para[i].getParameterAsInt();
			}
			else if (name.equals("C_TaxCategory_ID"))
			{
				p_C_TaxCategory_ID = para[i].getParameterAsInt();
			}
			else if (name.equals("M_Locator_ID"))
			{
				p_M_Locator_ID = para[i].getParameterAsInt();
			}
			else if (name.equals("C_BPartner_ID"))
			{
				p_C_BPartner_ID = para[i].getParameterAsInt();
			}
			else if (name.equals("SalesRep_ID"))
			{
				p_SalesRep_ID = para[i].getParameterAsInt();
			}
			else if (name.equals("AD_Role_ID"))
			{
				p_AD_Role_ID = para[i].getParameterAsInt();
			}
			else if (name.equals("UserPIN"))
			{
				p_userPIN  = (String)para[i].getParameter();
			}
			else if (name.equals("Name"))
			{
				 p_POS_Name = (String)para[i].getParameter();
			}
			else if (name.equals("HostAddress"))
			{
				 p_HostAddress = (String)para[i].getParameter();
			}
			else
			{
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
			}
		}

	}
	
	
	/**
	 * 	Process create a new Terminal POS
	 *	@return info
	 *	@throws Exception
	 */
	protected String doIt() throws Exception 
	{
		CreatePOS();
		createAdmin();
		return "@OK@";
	}
	
	/**
	 * 	Create new terminal web POS
	 */
	public void CreatePOS() throws Exception 
	{	
	    // Need to refactor correctly with new terminal @ashley
		/*log.info("Creating POS Terminal...");
		MPriceListVersion purchasepricelistversion = new MPriceListVersion(getCtx(), p_PriceList_Version_ID, get_TrxName());
		MPOS posTerminal = null;
		int pos_id = DB.getSQLValue(get_TrxName(), "SELECT C_POS_ID FROM C_POS WHERE AD_Client_ID=" + AD_Client_ID + " AND Name='"+p_POS_Name + "'");
		if(pos_id != 0)
		{
			posTerminal = new MPOS(getCtx(), pos_id , get_TrxName());
		}
		else 
		{
			posTerminal = new MPOS(getCtx(), 0 , get_TrxName());
		}
			
			
		posTerminal = new MPOS(getCtx(), 0, get_TrxName());
		posTerminal.setName(p_POS_Name);
		posTerminal.setAD_Org_ID(p_AD_Org_ID);
		posTerminal.setC_CashBook_ID(p_C_CashBook_ID);
		MPriceListVersion pricelistversion = new MPriceListVersion(getCtx(), p_M_PriceList_Version_ID, get_TrxName());
		posTerminal.setM_PriceList_ID(pricelistversion.getM_PriceList_ID());
		MLocator locator = new MLocator (getCtx(), p_M_Locator_ID, get_TrxName());
		posTerminal.setM_Warehouse_ID(locator.getM_Warehouse_ID());
		posTerminal.setC_BPartnerCashTrx_ID(p_C_BPartner_ID);
		posTerminal.setSalesRep_ID(p_SalesRep_ID);
		posTerminal.setM_PriceList_ID(purchasepricelistversion.getM_PriceList_ID());
		if(!posTerminal.save())
			throw new IllegalArgumentException("can not create POS Terminal");
		
		log.info("Creating Web store configuration...");
		MClient client = new MClient(getCtx(), AD_Client_ID, get_TrxName());
		MStore store = null;
		int store_id = DB.getSQLValue(get_TrxName(), "SELECT W_STORE_ID FROM W_STORE WHERE AD_Client_ID=" + AD_Client_ID + " AND IsActive='Y' AND Name='"+client.getName() + " POS" + "'");
		if(store_id != 0)
		{
			store = new MStore(getCtx(), store_id , get_TrxName());
		}
		else 
		{
			store = new MStore(getCtx(), 0, get_TrxName());
		}
		
		store.setName(client.getName() + " POS");
		store.setM_PriceList_ID(purchasepricelistversion.getM_PriceList_ID());
		store.setWebContext(client.getName() + "pos");
		store.setSalesRep_ID(p_SalesRep_ID);
		store.setAD_Org_ID(p_AD_Org_ID);
		store.setWebParam5("posteritaWarmsCSS.jsp");
		store.setWebParam6("pos");
		store.setURL(p_HostAddress);
		store.setM_Warehouse_ID(locator.getM_Warehouse_ID());
		if(!store.save())
			throw new IllegalArgumentException("can not create Web Store");*/
		
		return ;

	}
	
	/**
	 * 	Create Admin Role POS web
	 */
	public void createAdmin() throws Exception 
	{
		
		MRole role = new MRole(getCtx(), p_AD_Role_ID, get_TrxName());
		role.setUserDiscount(BigDecimal.valueOf(0.00));
        role.setIsPersonalAccess(true);
        role.setIsAccessAllOrgs(false);
        role.setUserLevel(MRole.USERLEVEL_Organization);
        role.save();
        
        /*if(!role.isAccessAllOrgs())
            {
                MRoleOrgAccess orgAccess = new MRoleOrgAccess(role,Env.getAD_Org_ID(getCtx()));
                orgAccess.save();
            }*/
        
       
   		
		MUser user = new MUser(getCtx(), p_SalesRep_ID, get_TrxName());
		user.setIsFullBPAccess(true);
		if(p_userPIN!=null)
        {
            int id[] = MUser.getAllIDs(MUser.Table_Name," AD_Client_ID=" + Env.getAD_Client_ID(getCtx()) + " and userPin='"+p_userPIN + "'",null);
            
            if(id.length>0)
            	throw new IllegalArgumentException("User Pin Already exists");
            else
                user.setUserPIN(p_userPIN);
        }
		
		user.save();
		
		/*int [] commissionIds = MCommission.getAllIDs(MCommission.Table_Name," AD_CLIENT_ID="+Env.getAD_Client_ID(getCtx())+" and C_BPARTNER_ID="+user.getC_BPartner_ID()+" and isActive='Y'",null);
        if(commissionIds!=null && commissionIds.length>1)
        	throw new IllegalArgumentException("Sales rep has more than one commission"+ commissionIds.length);
        
        MCommission com;
        MCommissionLine comLine;
        if(commissionIds==null || commissionIds.length<1)
        {
            com = new MCommission(getCtx(),0,get_TrxName());
            comLine = new MCommissionLine(getCtx(),0,get_TrxName());
        }
        else
        {
            com = new MCommission(getCtx(),commissionIds[0],get_TrxName()); 
            MCommissionLine [] line =com.getLines();
            if(line.length!=1)
            	throw new IllegalArgumentException("either No commissionLine or more than One commission Line has bean defined for this bPartner");
            comLine = new MCommissionLine(getCtx(),line[0].get_ID(),get_TrxName());
        }
        int [] chargeIds = MCharge.getAllIDs(MCharge.Table_Name, " AD_Client_ID = " + AD_Client_ID + " AND IsDefault='Y'", get_TrxName());
        if(chargeIds.length<1)
        {
            MCharge charge = new MCharge(getCtx(),0,get_TrxName());
            charge.setName(Msg.translate(getCtx(), "Commission"));
            charge.setChargeAmt(new BigDecimal(0));
            //charge.setC_TaxCategory_ID();
            charge.save();
            com.setC_Charge_ID( charge.getC_Charge_ID());
        }
        else
        {
        	com.setC_Charge_ID( chargeIds[0]);
        }
        
        
        
        com.setC_Charge_ID( chargeIds[0]);
        com.setName(user.getName());
        com.setC_BPartner_ID(user.getC_BPartner_ID());
        com.setFrequencyType(com.FREQUENCYTYPE_Monthly);
        MClient client = new MClient(getCtx(), AD_Client_ID , get_TrxName());
        com.setC_Currency_ID(client.getAcctSchema().getC_Currency_ID());
        com.setDocBasisType(com.DOCBASISTYPE_Receipt);
        com.setListDetails(true);
        com.save();
        
               
        
        comLine.setC_Commission_ID(com.get_ID());
        comLine.setAmtMultiplier(Env.ZERO);
        comLine.setDescription(Msg.translate(getCtx(), "Commission"));
        comLine.setCommissionOrders(true);
        comLine.setIsPositiveOnly(true);
        comLine.setAmtSubtract(Env.ZERO);
        comLine.save();*/
	        
		
		String whereClause = " AD_Role_ID=" + role.get_ID();
		
		int roleMenuIds[] = MRoleMenu.getAllIDs(MRoleMenu.Table_Name, whereClause, get_TrxName());
		
		for(int i = 0; i < roleMenuIds.length; i++)
		{
			MRoleMenu roleMenu = new MRoleMenu(getCtx(), roleMenuIds[i], get_TrxName());
			roleMenu.delete(true);
		}
		
		
		POSMenuFactory posMFactory = POSMenuFactory.getFactoryInstance(getCtx());
		Iterator keyIter = posMFactory.getAllKeys(getCtx()).iterator();
		
		while(keyIter.hasNext())
		{
			String key = (String)keyIter.next();
			MWebMenu menu = (MWebMenu)posMFactory.get(getCtx(), key);
			MRoleMenu roleMenu = new MRoleMenu(getCtx(), 0, get_TrxName());
			roleMenu.setAD_Role_ID(role.get_ID());
			roleMenu.setU_WebMenu_ID(menu.get_ID());
			PoManager.save(roleMenu);
		}
		
		return;
	}
}
