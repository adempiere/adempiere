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
 * Created on 22-Jul-2005 by alok
 *
 */
package org.posterita.businesslogic;

import java.util.Properties;

import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MLocation;
import org.posterita.exceptions.OperationException;
import org.posterita.lib.UdiConstants;
import org.posterita.util.PoManager;


public class LocationManager
{
		
	
	public static MLocation saveLocation(Properties ctx, int orgId, int locationId, String address1,String address2, String postalAddress1, Integer regionId, String city,int countryId, String trxName) throws OperationException
	{	
		MLocation location = null;
		
		if (locationId == 0)
			location = new MLocation(ctx,locationId,trxName);
		else
			location = loadLocation(ctx, locationId, trxName);	
		
		location.setAddress1(address1);
		location.setAddress2(address2);
		location.setCity(city);
		location.setPostal_Add(postalAddress1);
        location.setC_Country_ID(countryId);
        location.setAD_Org_ID(orgId);
        PoManager.save(location);
		
		return location;
	}

	
	public static MLocation createLocation(Properties ctx, int orgId, String address1,String address2, String postalAddress1, String city,int regionId, int countryId, String trxName) throws OperationException
	{	
		MLocation location = new MLocation(ctx,0,trxName);
		location.setAD_Org_ID(orgId);
		location.setAddress1(address1);
		location.setAddress2(address2);
		location.setCity(city);
		location.setPostal_Add(postalAddress1);
		location.setC_Region_ID(regionId);
        location.setC_Country_ID(countryId);
        PoManager.save(location);
		
		return location;
	}
	
	public static MLocation editLocation(Properties ctx,int locationId,String address1,String postalAddress1, Integer regionId, String city, String trxName) throws OperationException
	{
		
	    if(locationId<=0)
	        throw new OperationException("Location does not exist");
	    
		MLocation location;
			
		try
		{
		    location = new MLocation(ctx,locationId,trxName);
		    
		    if(location==null)
		        throw new OperationException("Location does not exist");
			 
			
			location.setAddress1(address1);	
			location.setCity(city);		
			location.setPostal_Add(postalAddress1);
			
			if(regionId!=null)  
				location.setC_Region_ID(regionId);
			
			PoManager.save(location);
			
		}
		catch(OperationException e)
		{
			throw new OperationException("Could not edit location!!");
		}
		
		
		return location;
		
	}
	
	public static MBPartnerLocation createDefaultBPLocation(Properties ctx, MBPartner bpartner) throws OperationException
	{
		MLocation location = LocationManager.createLocation(ctx, bpartner.getAD_Org_ID(), "","","", "",0, UdiConstants.COUNTRY_MAURITIUS, null);
		
		
		MBPartnerLocation bplocation = new MBPartnerLocation(bpartner);
		bplocation.setC_Location_ID(location.get_ID());
		PoManager.save(bplocation);
		
		bpartner.setPrimaryC_BPartner_Location_ID(bplocation.get_ID());
		PoManager.save(bpartner);
		
		return bplocation;
	}
	
    public static MLocation loadLocation(Properties ctx, int locationId, String trxName) throws OperationException
    {
    	MLocation location = new MLocation(ctx, locationId, trxName);
    	if(location.get_ID() == 0)
    		throw new OperationException("Could not load location with id: " + locationId);
    	return location;
    }
	
}
