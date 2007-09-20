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
 * 
 * Created on May 8, 2006 by alok
 */

package org.posterita.businesslogic;

import java.util.Properties;

import org.compiere.model.MBPartner;
import org.compiere.model.MCashBook;
import org.compiere.model.MCurrency;
import org.compiere.model.MPOS;
import org.compiere.model.MPriceList;
import org.compiere.model.MPriceListVersion;
import org.compiere.model.MWarehouse;
import org.compiere.util.Env;

import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.POSTerminalException;
import org.posterita.lib.UdiConstants;

public class POSTerminalManager 
{
	
	public static MCurrency getPOSDefaultSellCurrency(Properties ctx)
	{
        int salesPriceListId=POSTerminalManager.getSalesPriceListId(ctx);
        MPriceList priceList = new MPriceList(ctx,salesPriceListId,null);
		MCurrency currency = new MCurrency(ctx,priceList.getC_Currency_ID(),null);
		return currency;
	}
    
    public static MCurrency getPOSDefaultPurchaseCurrency(Properties ctx)
    {
        int purchasePriceListVersionId=Env.getContextAsInt(ctx,UdiConstants.POS_PURCHASE_PL_VERSION);
        MPriceListVersion priceListVersion = new MPriceListVersion(ctx,purchasePriceListVersionId,null);
        int purchasePriceListId=priceListVersion.getM_PriceList_ID();
        MPriceList priceList = new MPriceList(ctx,purchasePriceListId,null);
        MCurrency currency = new MCurrency(ctx,priceList.getC_Currency_ID(),null);
        return currency;
    }

	public static MBPartner getPOSDefaultBpartner(Properties ctx)
	{
	    int posId=Env.getContextAsInt(ctx, UdiConstants.POS_ID);
	    MPOS pos = new MPOS(ctx,posId,null);
	    return pos.getBPartner();
	    
	}
    
    public static String getPOSPrinter(Properties ctx)
    {
        int posId=Env.getContextAsInt(ctx, UdiConstants.POS_ID);
        MPOS pos = new MPOS(ctx,posId,null);
        return pos.getPrinterName();
    }

	public static MCashBook  getPOSDefaultCashBook(Properties ctx)
	{
	    int posId=Env.getContextAsInt(ctx,UdiConstants.POS_ID);
	    MPOS pos = new MPOS(ctx,posId,null);
	    int cashBookID=pos.getC_CashBook_ID();
	    MCashBook cashBook = new MCashBook(ctx, cashBookID, null);
	    return cashBook;
	}
    
    public static MCashBook  getPOSCashBook(Properties ctx,int posId)
    {
        MPOS pos = new MPOS(ctx,posId,null);
        int cashBookID=pos.getC_CashBook_ID();
        MCashBook cashBook = new MCashBook(ctx, cashBookID, null);
        return cashBook;
    }

	public static MWarehouse getPOSDefaultWarehouse(Properties ctx)
	{
	    int posId=Env.getContextAsInt(ctx,UdiConstants.POS_ID);
	    MPOS pos = new MPOS(ctx,posId,null);
	    int warehouseId = pos.getM_Warehouse_ID();
	    MWarehouse warehouse = new MWarehouse(ctx,warehouseId,null);
	    return warehouse;
	}
    
    public static MCurrency getCurrencyOfDefaultCashBook(Properties ctx)
    {
        MCashBook cashBook=getPOSDefaultCashBook(ctx);
        MCurrency currency = new MCurrency(ctx,cashBook.getC_Currency_ID(),null);
        return currency;
    }
    
    public static MCurrency getCurrencyOfCashBook(Properties ctx,int posId)
    {
        MCashBook cashBook=getPOSCashBook(ctx,posId);
        MCurrency currency = new MCurrency(ctx,cashBook.getC_Currency_ID(),null);
        return currency;
    }

	public static int getSalesPriceListVersionId(Properties ctx) throws OperationException
	{
	    int posid = Env.getContextAsInt(ctx,UdiConstants.POS_ID);
	    MPOS pos = new MPOS(ctx,posid,null);
	    int [] salesPriceListVersionIds = MPriceListVersion.getAllIDs(MPriceListVersion.Table_Name," AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+" and M_PRICELIST_ID="+pos.getM_PriceList_ID()+" and isActive='Y'",null);
	    
        if(salesPriceListVersionIds.length>1)
            throw new OperationException("More than one price list version for a single price list");
	    
	    
	    return salesPriceListVersionIds[0];
	    
	}

	public static int getSalesPriceListId(Properties ctx)
	{
	    int posid = Env.getContextAsInt(ctx,UdiConstants.POS_ID);
	    MPOS pos = new MPOS(ctx,posid,null);
	    
	    return pos.getM_PriceList_ID();
	    
	}
	
	
	public static int getPOSIdByName(Properties ctx, String posName, String trxName) throws POSTerminalException
	{
		int adClientId = Env.getAD_Client_ID(ctx);
		int adOrgId = Env.getAD_Org_ID(ctx);
		
		String whereClause = "AD_Client_ID=" + adClientId + " and AD_Org_ID=" + adOrgId + " and Name='" + posName + "'";
		
		int posIds[] = MPOS.getAllIDs(MPOS.Table_Name, whereClause, trxName);
		
		if(posIds.length == 0)
			throw new POSTerminalException("No POS Terminal found with Name: " + posName);
		else if(posIds.length > 1)
			throw new POSTerminalException(posIds.length + " POS Terminals found with Name: " + posName);
		else
			return posIds[0];
	}

}
