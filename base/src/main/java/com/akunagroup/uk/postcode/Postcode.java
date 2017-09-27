/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          * 
 * http://www.adempiere.org                                           * 
 *                                                                    * 
 * Copyright (C) Akuna Group Ltd.                                     * 
 * Copyright (C) Contributors                                         * 
 *                                                                    * 
 * This program is free software; you can redistribute it and/or      * 
 * modify it under the terms of the GNU General Public License        * 
 * as published by the Free Software Foundation; either version 2     * 
 * of the License, or (at your option) any later version.             * 
 *                                                                    * 
 * This program is distributed in the hope that it will be useful,    * 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of     * 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       * 
 * GNU General Public License for more details.                       * 
 *                                                                    * 
 * You should have received a copy of the GNU General Public License  * 
 * along with this program; if not, write to the Free Software        * 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         * 
 * MA 02110-1301, USA.                                                * 
 *                                                                    * 
 * Contributors:                                                      * 
 *  - Michael Judd (michael.judd@akunagroup.com)                      * 
 **********************************************************************/

package com.akunagroup.uk.postcode;

public class Postcode implements AddressInterface
{
	
	// required implementation by interface
	private String Street1;
	private String Street2;
	private String Street3;
	private String Street4;
	private String City;
	private String Region;
	private String Postcode;
	private String Country;

	// UK Postcode specific
	private String Addr;	// Full address in one variable
	private String CountryCode;	// Two Letter ISO Country Code
	private String TradCounty;	// Traditional County (Region)
	private String AdminCounty;	// Administrative County
	private String LonLocation;	// London Location
	
	public int size()
	{
		return 1;
	}
		
	public String getAddr()
	{
		return Addr;
	}
	
	public void setAddr(String newAddr)
	{
		Addr = newAddr;
	}
	
	public String getStreet1()
	{
		return Street1;
	}
	
	public void setStreet1(String newStreet1)
	{
		Street1 = newStreet1;
	}
	public String getStreet2()
	{
		return Street2;
	}
	
	public void setStreet2(String newStreet2)
	{
		Street2 = newStreet2;
	}
	public String getStreet3()
	{
		return Street3;
	}
	
	public void setStreet3(String newStreet3)
	{
		Street3 = newStreet3;
	}
	public String getStreet4()
	{
		return Street4;
	}
	
	public void setStreet4(String newStreet4)
	{
		Street4 = newStreet4;
	}
	
	public String getCity()
	{
		return City;
	}
	
	public void setCity(String newCity)
	{
		City = newCity;
	}

	public String getRegion()
	{
		return Region;
	}
	
	public void setRegion(String newRegion)
	{
		Region = newRegion;
	}
	public String getPostcode()
	{
		return Postcode;
	}
	
	public void setPostcode(String newPostcode)
	{
		Postcode = newPostcode;
	}
	public String getCountry()
	{
		return Country;
	}
	
	public void setCountry(String newCountry)
	{
		Country = newCountry;
	}
	public String getCountryCode()
	{
		return CountryCode;
	}
	
	public void setCountryCode(String newCountryCode)
	{
		CountryCode = newCountryCode;
	}
	public String getTradCounty()
	{
		return TradCounty;
	}
	
	public void setTradCounty(String newTradCounty)
	{
		TradCounty = newTradCounty;
	}
	public String getAdminCounty()
	{
		return AdminCounty;
	}
	
	public void setAdminCounty(String newAdminCounty)
	{
		AdminCounty = newAdminCounty;
	}
	public String getLonLocation()
	{
		return LonLocation;
	}
	
	public void setLonLocation(String newLonLocation)
	{
		LonLocation = newLonLocation;
	}

}
