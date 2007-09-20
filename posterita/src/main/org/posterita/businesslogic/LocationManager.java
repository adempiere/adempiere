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

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MLocation;
import org.compiere.util.DB;

import org.posterita.exceptions.OperationException;
import org.posterita.lib.UdiConstants;
import org.posterita.model.UDIMBPartner;
import org.posterita.model.UDIMBPartnerLocation;
import org.posterita.model.UDIMLocation;


public class LocationManager
{
		
	public static MLocation createLocation(Properties ctx,String address1,String postalAddress1,String city,int countryId, String trxName) throws OperationException
	{
		return createLocation(ctx,address1,"", postalAddress1, null, city, countryId, trxName);
	}
	
	public static MLocation saveLocation(Properties ctx, int locationId, String address1,String address2, String postalAddress1, Integer regionId, String city,int countryId, String trxName) throws OperationException
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
		UDIMLocation udiLocation = new UDIMLocation(location);
		udiLocation.save();
		
		return location;
	}

	
	public static MLocation createLocation(Properties ctx,String address1,String address2, String postalAddress1, Integer regionId, String city,int countryId, String trxName) throws OperationException
	{	
		MLocation location = new MLocation(ctx,0,trxName);
		
		location.setAddress1(address1);
		location.setAddress2(address2);
		location.setCity(city);
		location.setPostal_Add(postalAddress1);
		
        location.setC_Country_ID(countryId);
		UDIMLocation udiLocation = new UDIMLocation(location);
		udiLocation.save();
		
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
			
			UDIMLocation udiLocation = new UDIMLocation(location);
			udiLocation.save();	
			
		}
		catch(OperationException e)
		{
			throw new OperationException("Could not edit location!!");
		}
		
		
		return location;
		
	}
	
	public static MLocation editLocation(Properties ctx,int c_location_id, String address1, String address2, String postalAddress, Integer regionId, String city, int countryId, String trxName) throws OperationException
	{
	    
	    //MRegion region = new MRegion(ctx, regionId, null);
	    
	   /* StringBuffer updateSQL = new StringBuffer("update C_LOCATION set" +
		" address1 = ? ," +		//1.address1
		" address2 = ? ," +		//2.address2
		" postal_add = ? ," +	//3.postalAddr
		" city = ? " );			//4.city
		
		if (countryId != 0)
			updateSQL = updateSQL.append(", c_country_id = ?");
										 //" c_region_id = ? ");

		updateSQL = updateSQL.append(" where C_LOCATION_ID = ? ");	//5.c_location_id
*/
		
		 StringBuffer updateSQL = new StringBuffer();
		 updateSQL.append("update C_LOCATION set ");
		 updateSQL.append("address1 ='").append(address1).append("', ");
		 updateSQL.append("address2 ='").append(address2).append("', ");
		 updateSQL.append("postal_add ='").append(postalAddress).append("', ");
		 updateSQL.append("city ='").append(city).append("', ");
		 updateSQL.append("c_country_id =").append(countryId).append(" ");
		 updateSQL.append("where C_LOCATION_ID=").append(c_location_id);
		 
		//PreparedStatement pstmt = DB.prepareStatement(updateSQL.toString(),trxName);
		int i = DB.executeUpdate(updateSQL.toString(), trxName);

		if (i == -1) 
		{
			throw new OperationException("Unable to update BPartner Location");
		}
		/*try 
		{               
		    
		    pstmt.setString(1,address1);
		    pstmt.setString(2,address2);
		    
		    //Truncate postal address
		    if (postalAddress != null)
		    {
			    int pos = (postalAddress.length() > 10) ? 10 : postalAddress.length();
			    postalAddress = postalAddress.substring(0,pos);
		    }
		    
		    
		    pstmt.setString(3,postalAddress);
		    pstmt.setString(4,city);
		    //pstmt.setString(5,region.getName());
		    pstmt.setInt(5, countryId);
		    //pstmt.setInt(6, regionId);
		    pstmt.setInt(6,c_location_id);
		    
		    if(pstmt.executeUpdate() == 0)
		    {
		        throw new OperationException("Unable to update Business Partner Location details");
		    }
		} 
		catch (SQLException e1) 
		{
		    throw new OperationException(e1);
		}
		finally
		{        
			try
			{
				pstmt.close();
			}
			catch(Exception e)
			{}
			
		    pstmt = null;
		}*/
		
        return new MLocation(ctx,c_location_id,trxName);
	    
	}
	
/*	public static boolean bpHasLocation(Properties ctx, int bpartnerId, String trxName)
	{
			MBPartnerLocation[] partnerLocation = MBPartnerLocation.getForBPartner(ctx, bpartnerId);
			
			return (partnerLocation.length > 0);
	}
	
    public static MLocation getLocation(Properties ctx, int bpartnerId, String trxName) throws OperationException
    {
			MBPartner vendor = new MBPartner(ctx, bpartnerId, null);
			
			if (vendor.get_ID() == 0) 
				throw new OperationException("Vendor does not exist id: " + bpartnerId);
			
			MBPartnerLocation[] partnerLocation = MBPartnerLocation.getForBPartner(ctx, bpartnerId);
			
			if (partnerLocation.length > 0)
			{
					int locationId = partnerLocation[0].get_ID();
					return loadLocation(ctx, locationId, trxName);
			}
			throw new NoLocationFoundException("Could not find location for Business Partner with id: " + bpartnerId);
    }
*/    
	public static MBPartnerLocation createDefaultBPLocation(Properties ctx, MBPartner bpartner) throws OperationException
	{
		MLocation location = LocationManager.createLocation(ctx, "", "", "", UdiConstants.COUNTRY_MAURITIUS, null);

		MBPartnerLocation bplocation = new MBPartnerLocation(bpartner);
		bplocation.setC_Location_ID(location.get_ID());
		UDIMBPartnerLocation udiBpLocation = new UDIMBPartnerLocation(bplocation);
		udiBpLocation.save();
		
		bpartner.setPrimaryC_BPartner_Location_ID(udiBpLocation.getID());
		UDIMBPartner udiBPartner_defaultLocation = new UDIMBPartner(bpartner);
		udiBPartner_defaultLocation.save();
		
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
