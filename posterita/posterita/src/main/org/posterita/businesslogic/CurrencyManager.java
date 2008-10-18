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
 **/
package org.posterita.businesslogic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.model.MCurrency;
import org.compiere.model.MPriceList;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.posterita.Constants;
import org.posterita.exceptions.OperationException;

public class CurrencyManager
{

	/**
	 * Retrieves the currency symbol for the currency ID specified
	 * @param currencyId Currency ID
	 * @return Currency Symbol
	 */
	public static String getCurrencySymbol(int currencyId) throws OperationException
	{
		MCurrency cur = new MCurrency(new Properties(), currencyId, null);

		if (cur.get_ID() == 0)
			return "";	//Do not throw error here
		
		return cur.getCurSymbol();
	}
	
	public static int getCurrencyPrecision(Properties ctx) throws OperationException
	{
		int pricelistId = Env.getContextAsInt(ctx, Constants.PRICE_LIST);
		MPriceList priceList = MPriceList.get(ctx, pricelistId, null);
		   
		if (priceList == null)
		{
			throw new OperationException("Price list cannot be null!");
		}
		   
		MCurrency mCurrency = new MCurrency(ctx, priceList.getC_Currency_ID(), null);
		
		return mCurrency.getCostingPrecision();
	}
	
	public static MCurrency getCurrency(Properties ctx) throws OperationException
	{
		int pricelistId = POSTerminalManager.getSOPriceListId(ctx);
		MPriceList priceList = MPriceList.get(ctx, pricelistId, null);
		   
		if (priceList == null)
		{
			throw new OperationException("No price list set for POS Terminal");
		}
		   
		MCurrency mCurrency = MCurrency.get(ctx, priceList.getC_Currency_ID());
		
		return mCurrency;
	}
	
	public static ArrayList<KeyNamePair> getAllCurrencies() throws OperationException
	{
		ArrayList<KeyNamePair> knPairList = new ArrayList<KeyNamePair>();
		
		String sql = "Select C_Currency_ID, Description from C_Currency order by description";
		
		PreparedStatement pstmt =  DB.prepareStatement(sql, null);
		
		try
		{
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				int currencyId = rs.getInt(1);
				String currencyDescription = rs.getString(2);
				
				knPairList.add(new KeyNamePair(currencyId, currencyDescription));
			}
		}
		catch(SQLException ex)
		{
			throw new OperationException("Could not get currencies with sql: " + sql, ex);
		}
		
		return knPairList;
	}

}
