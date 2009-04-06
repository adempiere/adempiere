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

package org.posterita.businesslogic.administration;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.model.MBPBankAccount;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MCountry;
import org.compiere.model.MLocation;
import org.compiere.model.MOrg;
import org.compiere.model.MRegion;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.posterita.beans.AddressBean;
import org.posterita.beans.BPartnerBean;
import org.posterita.beans.StatementOfAccountBean;
import org.posterita.businesslogic.LocationManager;
import org.posterita.core.TrxPrefix;
import org.posterita.exceptions.NoAccessToEditObjectException;
import org.posterita.exceptions.OperationException;
import org.posterita.lib.UdiConstants;
import org.posterita.util.PoManager;


public class BPartnerManager
{
	
	
	public static MBPartner activateBPartner(Properties ctx, int bpartnerId, boolean isActive,String trxName) throws OperationException
    {

		MBPartner bpartner = new MBPartner(ctx,bpartnerId,trxName);
	    
	    if(bpartner == null)
	    	throw new OperationException("Business Partner does not exist!");
	   if(isActive == true)
	   {
		   bpartner.setIsActive(false); 
	   }
	   else
	   {
		   bpartner.setIsActive(true); 
	   }
	    
	    PoManager.save(bpartner);
	    
	    return bpartner;
            
    }
	
    public static MBPartner createBPartner(Properties ctx,int orgId, int parentBpId, String bPName, String name2, boolean isCustomer,boolean isVendor,boolean isEmployee,boolean isSalesRep,String address1, String address2, String postalAddress1, Integer regionId, String city,String bpPhone, String fax,int countryId, boolean isShipTo, boolean isBillTo, String trxName) throws OperationException
	{
		return saveBPartner(ctx, orgId, 0, parentBpId, bPName, name2, isCustomer, isVendor, isEmployee, isSalesRep, address1, address2, postalAddress1, regionId, city, bpPhone, "", fax, countryId, isShipTo, isBillTo, trxName);
	}	

	
	public static MBPartner createBPartner(Properties ctx,int orgId, int parentBpId, String bPName, String name2, boolean isCustomer,boolean isVendor,boolean isEmployee,boolean isSalesRep,String address1,String postalAddress1,String city,String bpPhone,int countryId, String trxName) throws OperationException
	{
		return saveBPartner( ctx, orgId, 0, parentBpId, bPName, name2, isCustomer, isVendor, isEmployee, isSalesRep, address1, "", postalAddress1, null, city, bpPhone, "", "", countryId, true, true, trxName);
	}
		
	public static MBPBankAccount createBPBankAcc(Properties  ctx,int bPartnerId,String aName,String city,String street, String trxName) throws OperationException
	{
		MBPBankAccount bankAcc = new MBPBankAccount(ctx,0,trxName);

		try
		{
				bankAcc.setC_BPartner_ID(bPartnerId);
				bankAcc.setA_Name(aName);//Name of Account holder
				bankAcc.setA_Street(street);
				bankAcc.setA_City(city);//city of bank account
				
				PoManager.save(bankAcc);
		}
		catch(OperationException e)
		{
			throw new OperationException("Cannot create BP Bank Account!");
		}
		
		
		return bankAcc;
	}
	
	public static MBPartner createLinkedBPartner(Properties ctx,int orgId, int parentBpId, String bPName, String name2, boolean isCustomer,boolean isVendor,boolean isEmployee,boolean isSalesRep,String address1,String postalAddress1,String city,String bpPhone,int countryId, String trxName) throws OperationException
	{
		MBPartner bpartner =  saveBPartner( ctx, orgId, 0, parentBpId, bPName, name2, isCustomer, isVendor, isEmployee, isSalesRep, address1, "", postalAddress1, null, city, bpPhone, "", "",countryId, true, true, trxName);
		
		bpartner.setAD_OrgBP_ID(orgId);
		
		PoManager.save(bpartner);
		return bpartner;
	}
	
	
	
	public static MBPartner createShippingBPartner(Properties ctx,int orgId, int parentBpId, String bPName, String name2, boolean isCustomer,boolean isVendor,boolean isEmployee,boolean isSalesRep,String address1,String postalAddress1,String city,String bpPhone,int countryId, boolean isShipTo, boolean isBillTo, String trxName) throws OperationException
	{
		return saveBPartner( ctx, orgId, 0, parentBpId, bPName, name2, isCustomer, isVendor, isEmployee, isSalesRep, address1, "", postalAddress1, null, city, bpPhone, "", "", countryId, isShipTo, isBillTo, trxName);
	
	}
	
	public static MBPartner deactivateBPartner(Properties ctx, int bpartnerId, String trxName) throws OperationException 
    {
		
    	MBPartner bpartner = new MBPartner(ctx,bpartnerId,trxName);
        
        if(bpartner == null)throw new OperationException("Business Partner does not exist!");
        
        bpartner.setIsActive(false);
        
        PoManager.save(bpartner);
        
        return bpartner;
            
    }
	

	public static ArrayList<BPartnerBean> getAllBpartners(Properties ctx, String trxName) throws  OperationException
	{
		
		int adClientID = Env.getAD_Client_ID(ctx);
		
	
		String sql;
		
		sql = " select bp.c_bpartner_id, " + //1
		" bp.name," + //2
		" bp.name2," + //3
		" bp.isactive,"+//4
		" bp.iscustomer," + //5
		" bp.isemployee," + //6
		" bp.isvendor, " + //7
		" bp.issalesrep," + //8
		" cl.address1," + //9
		" cl.address2," + //10
	    " cl.city," + //11
		" cl.postal_add," + //12
		" bpl.c_bpartner_id," + //13
		" cl.c_location_id," + //14
		" bpl.phone," +		//15
		" bpl.fax," + 		//16
		" cl.REGIONNAME " + //17
		" from  C_BPARTNER bp left outer join (c_bpartner_location bpl left outer join c_location cl on cl.c_location_id=bpl.c_location_id) on bpl.c_bpartner_id = bp.c_bpartner_id, AD_ORG org " +
		" where bp.ad_org_id = org.ad_org_id and " +
		" bp.AD_CLIENT_ID = " + adClientID + 
		" order by bp.name";
		
		PreparedStatement pstmt =null;
		
		System.out.println(sql);
		
		BPartnerBean bpartner = null;
		ResultSet rs = null;
		
		ArrayList<BPartnerBean> bpartners = new ArrayList<BPartnerBean>();
		
		try
		{
			pstmt = DB.prepareStatement(sql,trxName);
			rs = pstmt.executeQuery();
			
			
			while (rs.next())
			{
				
				bpartner = new BPartnerBean();
				bpartner.setBpartnerId(Integer.valueOf(rs.getInt(1)));
				bpartner.setPartnerName(rs.getString(2));
				bpartner.setName2(rs.getString(3));
				
				if(rs.getString(4).equals("Y"))
				{
				    bpartner.setIsActive(Boolean.valueOf(true));
				}
				else
				    bpartner.setIsActive(Boolean.valueOf(false));
				
				if(rs.getString(5).equals("Y"))
				{
					bpartner.setIsCustomer(Boolean.valueOf(true));
				}
				else
				    bpartner.setIsCustomer(Boolean.valueOf(false));
				
				if(rs.getString(6).equals("Y"))
				{
					bpartner.setIsEmployee(Boolean.valueOf(true));
				}
				else
					bpartner.setIsEmployee(Boolean.valueOf(false));
				
				if(rs.getString(7).equals("Y"))
				{
					bpartner.setIsVendor(Boolean.valueOf(true));
				}
				else
					bpartner.setIsVendor(Boolean.valueOf(false));
				
				if(rs.getString(8).equals("Y"))
				{
					bpartner.setIsSalesRep(Boolean.valueOf(true));
				}
				else
					bpartner.setIsSalesRep(Boolean.valueOf(false));
				
				
                
				bpartner.setAddress1(rs.getString(9));
				bpartner.setAddress2(rs.getString(10));
				bpartner.setCity(rs.getString(11));
				bpartner.setPostalAddress(rs.getString(12));
				bpartner.setPhone(rs.getString(15));
				bpartner.setFax(rs.getString(16));
				
				String regionName = rs.getString(17);
				
				int regionId = 0;
				
				int id[] = MRegion.getAllIDs(MRegion.Table_Name," name= '"+ regionName +"'",trxName);
				
				if(id.length != 0)
				{
				    regionId = id[0];
				}
				
				bpartner.setRegionId(Integer.valueOf(regionId));
				
				
				bpartners.add(bpartner);
			}	
			
			rs.close();
		
	  }
		catch (SQLException e)
		{
			throw new OperationException(e.getMessage());
		} 
        finally
   	 	{
	   		 try
	   		 {
	   			 if(pstmt != null)
	   				 pstmt.close();
	   		 }
	   		 catch(Exception ex)
	   		 {}
	   		 
	   		 pstmt = null;
   	 	}
		
		return bpartners;
	
		
	}	
	
	
    public static BPartnerBean getBpartner(Properties ctx, int bPartnerId, String trxName) throws OperationException 
    {
    	MBPartner bpartner = loadBPartner(ctx, bPartnerId, trxName);
    	
    	MBPartnerLocation bplocations[] = bpartner.getLocations(false);
    	
    	BPartnerBean bpBean = new BPartnerBean();
    	bpBean.setBpartnerId(bpartner.get_ID());
    	bpBean.setPartnerName(bpartner.getName());
    	bpBean.setName2(bpartner.getName2());
		bpBean.setIsActive(bpartner.isActive());
		bpBean.setIsCustomer(bpartner.isCustomer());
		bpBean.setIsEmployee(bpartner.isEmployee());
		bpBean.setIsVendor(bpartner.isVendor());
		bpBean.setIsSalesRep(bpartner.isSalesRep());
		bpBean.setOrgId(bpartner.getAD_Org_ID());
		if (bplocations.length == 0)
		{
			throw new OperationException("No location defined for BPartner");
		}
        
		MBPartnerLocation bplocation = bplocations[0];
		MLocation location = bplocation.getLocation(false);
		
		bpBean.setAddress1(location.getAddress1());
		bpBean.setAddress2(location.getAddress2());
		bpBean.setCity(location.getCity());
		bpBean.setPostalAddress(location.getPostal_Add());
		bpBean.setPhone(bplocation.getPhone());
		bpBean.setFax(bplocation.getFax());
		bpBean.setPostalCode(location.getPostal());
		MRegion region = location.getRegion();
		
		if (region != null && region.get_ID() > 0)
		{		
			bpBean.setRegionId(region.get_ID());
		}
		
		return bpBean;
    }	
    
	public static MBPartnerLocation getBPartnerSingleLocation(Properties ctx, int bpartnerId) throws OperationException
    {
    	MBPartnerLocation bpartnerLocations[] = MBPartnerLocation.getForBPartner(ctx, bpartnerId);
    	
    	if(bpartnerLocations.length == 0)
    		throw new OperationException("No location found business partner with id: " + bpartnerId);
    	else if(bpartnerLocations.length > 1)
    		throw new OperationException("More than 1 location found for business partner with id: " + bpartnerId);
    	else
    		return bpartnerLocations[0];
    }

     
    public static MBPartner getCreateLinkedBPartner(Properties ctx, MOrg org, String trxName) throws OperationException
    {
    	MBPartner linkedBPartner;
    	if(org == null)
    		throw new OperationException("Organisation cannot be null");
    	
    	int linkedBPId = org.getLinkedC_BPartner_ID(trxName);
    	if(linkedBPId != 0)
    	{
    		linkedBPartner = loadBPartner(ctx, linkedBPId, trxName);
    		return linkedBPartner;
    	}
    	else
    	{
    													
    		linkedBPartner = BPartnerManager.saveBPartner(ctx, org.get_ID(), 0, 0, org.getName(), "", false, false, false, false, " ", " ", " ", null, "", "", "", "", MCountry.getDefault(ctx).get_ID(), true, true, trxName);
    		
            //saveBPartner(ctx, partnerId, parentBpId, name, name2,  isCustomer, isVendor, isEmployee,isSalesRep, address1, address2, postalAddress1, regionId, city,bpPhone, String phone2, String fax,int countryId, boolean isShipTo, boolean isBillTo, String trxName) throws OperationException
    		
    		linkedBPartner.setAD_OrgBP_ID(org.get_ID());
    		    		
    		PoManager.save(linkedBPartner, trxName);
    		
    		return linkedBPartner;
    	}
    }   
	

	public static ArrayList <AddressBean> getMyBPartners(Properties ctx, int partnerId, String trxName) throws OperationException
	{
		
		ArrayList <AddressBean> bpartnerList = new ArrayList <AddressBean>();
		
		if (partnerId == 0)
			throw new OperationException("Parent business partner not present!");
		
		MBPartner me = new MBPartner(ctx, partnerId, trxName);
		AddressBean myAddressBean = populateAddressBean(ctx, me);
		bpartnerList.add(myAddressBean);		
		
		int[] partnerIds = MBPartner.getAllIDs(MBPartner.Table_Name, "BPARTNER_PARENT_ID=" + partnerId, null);
		
		for (int i = 0; i < partnerIds.length; i++)
		{
			MBPartner partner = new MBPartner(ctx, partnerIds[i], trxName);
			
			AddressBean bean = populateAddressBean(ctx, partner);
			
			bpartnerList.add(bean);
			
		}
		
		return bpartnerList;
	}	

	public static MBPartner getOrCreateBPartner(Properties ctx,int orgId, int parentBpId, String bPName, String name2, boolean isCustomer,boolean isVendor,boolean isEmployee,boolean isSalesRep,String address1,String postalAddress1,String city,String bpPhone,int countryId, String trxName) throws OperationException
	{
		return getOrCreateBPartner( ctx, orgId, parentBpId, bPName, name2, isCustomer, isVendor, isEmployee, isSalesRep, address1, "", postalAddress1, city, bpPhone, "",countryId, trxName);
	}	

	
	public static MBPartner getOrCreateBPartner(Properties ctx, int orgId, int bPartnerParentId, String bPName, String name2, boolean isCustomer,boolean isVendor,boolean isEmployee,boolean isSalesRep,String address1,String address2, String postalAddress1,String city,String bpPhone, String fax, int countryId, String trxName) throws OperationException
	{
		String sqlQuery = " upper(Name)=upper('" + bPName + "') and AD_CLIENT_ID=" + Env.getAD_Client_ID(ctx) + " and AD_ORG_ID=" + Env.getAD_Org_ID(ctx);
		
		int partners[] = MBPartner.getAllIDs(MBPartner.Table_Name, sqlQuery, trxName);
		MBPartner partner;
		if(partners.length != 0)
			partner = new MBPartner(ctx, partners[0], trxName);
		else
			partner = saveBPartner(ctx, orgId, 0, bPartnerParentId, bPName, name2, isCustomer, isVendor, isEmployee, isSalesRep, address1, address2, postalAddress1, null, city, bpPhone,"", fax,countryId, true, true, trxName);
		
		return partner;
	}
	
/*	public static int getOrgBPId(Properties ctx) throws OperationException
	{
		MOrg org = new MOrg(ctx, Env.getAD_Org_ID(ctx), null);

		int bpartnerId = org.getLinkedC_BPartner_ID();
		
		if (bpartnerId == 0)
			throw new OperationException("Organisation does not have Linked Business Partner");
		
		return bpartnerId;
	}*/
	
	public static AddressBean getShipmentAddressBPartner(Properties ctx, int partnerId) throws OperationException
	{
		if (partnerId == 0)
			throw new OperationException("Business partner not present!");
		
		MBPartner partner = new MBPartner(ctx, partnerId, null);
		
		AddressBean bean = populateAddressBean(ctx, partner);
	
		return bean;

	}
    
/*    public static String getVehicleTypeWhereStatement(Properties ctx, String trxName) throws OperationException
    {
		MOrg org = OrganisationManager.getMyOrg(ctx, trxName);
		
		if (org.isAutomobile())
		{
		    return (" and o.isautomobile='Y' ");
		}
		else if(org.isMotorcycle())
		{
			return (" and o.ismotorcycle='Y'  ");
		}
		else if (org.isMotorcycle() && org.isAutomobile())
		{
			return (" and (o.ismotorcycle='Y' or o.isautomobile='Y' ");
		}
		
		return "";
    }*/
    
    public static boolean isBPartnerPresent(Properties ctx, int c_bpartner_id, String trxName)
    {   
    	boolean isPresent = false;
    	
    	String sql = "SELECT C_BPARTNER_ID FROM C_BPARTNER WHERE ISACTIVE = 'Y' AND C_BPARTNER_ID = ?";
    	int value = DB.getSQLValue(trxName, sql, c_bpartner_id);
    	if(value != -1)
    	{
    		isPresent = true;
    	}
    
    	return isPresent;
    }
    
    public static MBPartner loadBPartner(Properties ctx, int bpartnerID, String trxName) throws OperationException
    {
    	MBPartner bPartner = new MBPartner(ctx, bpartnerID, trxName);
    	
    	if(bPartner.get_ID() == 0)
    		throw new OperationException("Cannot load Business Partner with id: " + bpartnerID);
    	
    	return bPartner;
    }
    
    private static AddressBean populateAddressBean(Properties ctx, MBPartner partner) throws OperationException
	{
		MBPartnerLocation[] partnerLocations = MBPartnerLocation.getForBPartner(ctx, partner.get_ID());
		
		if (partnerLocations.length == 0)
			throw new OperationException("Partner " + partner.getName() + " " + partner.getName2() + " (" + partner.get_ID() + ") " + "does not have any location!");
			
		MBPartnerLocation partnerLocation = partnerLocations[0];//We assume there is only 1 location for this partner
		MLocation location = new MLocation(ctx, partnerLocation.getC_Location_ID(), partner.get_TrxName());

		AddressBean bean = new AddressBean();
		bean.setBpartnerId(partner.get_ID());
		bean.setUsername(partner.getName());
		bean.setUserSurname(partner.getName2());
		bean.setAddress1(location.getAddress1());
		bean.setAddress2(location.getAddress2());
		bean.setPostalAddress(location.getPostal_Add());
		bean.setCity(location.getCity());
		bean.setCountryId(location.getC_Country_ID());
		bean.setCountryName(location.getCountryName());

		return bean;
	}  
    
    public static ArrayList<BPartnerBean> searchBpartners(Properties ctx, String name,  String trxName) throws  OperationException
	{		
		int adClientID = Env.getAD_Client_ID(ctx);		
	
		String sql;
		
		sql = " select bp.c_bpartner_id, " + //1
		" bp.name," + //2
		" bp.name2," + //3
		" bp.isactive,"+//4
		" bp.iscustomer," + //5
		" bp.isemployee," + //6
		" bp.isvendor, " + //7
		" bp.issalesrep," + //8
		" cl.address1," + //9
		" cl.address2," + //10
	    " cl.city," + //11
		" cl.postal_add," + //12
		" bpl.c_bpartner_id," + //13
		" cl.c_location_id," + //14
		" bpl.phone," +		//15
		" bpl.fax," + 		//16
		" cl.REGIONNAME " + //17
		" from  C_BPARTNER bp left outer join (c_bpartner_location bpl left outer join c_location cl on cl.c_location_id=bpl.c_location_id) on bpl.c_bpartner_id = bp.c_bpartner_id " +
		" where bp.ad_org_id in (" + Env.getContext(ctx,UdiConstants.ROLE_EDITABLE_ORGS_CTX_PARAM)+")"+ 
		" and bp.AD_CLIENT_ID = " + adClientID + 
		" and (upper(bp.name) like upper('%" + name + "%')" +
		" or upper(bp.name2) like upper('%" + name + "%'))" +
		" order by bp.name";
		
		PreparedStatement pstmt =null;
		
		BPartnerBean bpartner = null;
		ResultSet rs = null;
		
		ArrayList<BPartnerBean> bpartners = new ArrayList<BPartnerBean>();
		
		try
		{
			pstmt = DB.prepareStatement(sql,trxName);
			rs = pstmt.executeQuery();
			
			
			while (rs.next())
			{
				
				bpartner = new BPartnerBean();
				bpartner.setBpartnerId(Integer.valueOf(rs.getInt(1)));
				bpartner.setPartnerName(rs.getString(2));
				bpartner.setName2(rs.getString(3));
				
				if(rs.getString(4).equals("Y"))
				{
				    bpartner.setIsActive(Boolean.valueOf(true));
				}
				else
				    bpartner.setIsActive(Boolean.valueOf(false));
				
				if(rs.getString(5).equals("Y"))
				{
					bpartner.setIsCustomer(Boolean.valueOf(true));
				}
				else
				    bpartner.setIsCustomer(Boolean.valueOf(false));
				
				if(rs.getString(6).equals("Y"))
				{
					bpartner.setIsEmployee(Boolean.valueOf(true));
				}
				else
					bpartner.setIsEmployee(Boolean.valueOf(false));
				
				if(rs.getString(7).equals("Y"))
				{
					bpartner.setIsVendor(Boolean.valueOf(true));
				}
				else
					bpartner.setIsVendor(Boolean.valueOf(false));
				
				if(rs.getString(8).equals("Y"))
				{
					bpartner.setIsSalesRep(Boolean.valueOf(true));
				}
				else
					bpartner.setIsSalesRep(Boolean.valueOf(false));
				
				
                
				bpartner.setAddress1(rs.getString(9));
				bpartner.setAddress2(rs.getString(10));
				bpartner.setCity(rs.getString(11));
				bpartner.setPostalAddress(rs.getString(12));
				bpartner.setPhone(rs.getString(15));
				bpartner.setFax(rs.getString(16));
				
				String regionName = rs.getString(17);
				
				int regionId = 0;
				
				int id[] = MRegion.getAllIDs(MRegion.Table_Name," name= '"+ regionName +"'",trxName);
				
				if(id.length != 0)
				{
				    regionId = id[0];
				}
				
				bpartner.setRegionId(Integer.valueOf(regionId));
				
				
				bpartners.add(bpartner);
			}	
			
			rs.close();
		
	  }
		catch (SQLException e)
		{
			throw new OperationException(e.getMessage());
		} 
        finally
   	 	{
	   		 try
	   		 {
	   			 if(pstmt != null)
	   				 pstmt.close();
	   		 }
	   		 catch(Exception ex)
	   		 {}
	   		 
	   		 pstmt = null;
   	 	}
		
		return bpartners;	
	}
	
	public static MBPartner saveBPartner(Properties ctx, int orgId, int partnerId,int parentBpId, String name, String name2, 
			boolean isCustomer,boolean isVendor,boolean isEmployee,boolean isSalesRep,String address1, 
			String address2, String postalAddress1, Integer regionId, String city,String bpPhone, String phone2, 
			String fax,int countryId, boolean isShipTo, boolean isBillTo, String trxName) throws OperationException, NoAccessToEditObjectException
	{
		MBPartner bPartner;
		int locationId;
		
		if (partnerId == 0)
		{
			bPartner = new MBPartner(ctx, -1, trxName); // Using Template BPartner
			locationId = 0;
		}
		else
		{
			bPartner = loadBPartner(ctx, partnerId, trxName);

			Boolean isEditable = RoleManager.isEditable(ctx, bPartner.getAD_Org_ID());
			
			if (!isEditable)
				throw new NoAccessToEditObjectException("You do not have the right organisational access for editing");			
			
			MBPartnerLocation[] bplocations = MBPartnerLocation.getForBPartner(ctx, partnerId);
			
			if (bplocations.length > 1)
				throw new OperationException("Error: Business Partner has more than 1 location!");
			else if (bplocations.length == 0)
				locationId = 0;
			else
			{
				MBPartnerLocation bpLocation = bplocations[0];
				locationId = bpLocation.getC_Location_ID();
			}
		}
			
		
		bPartner.setValue(name + "_" + name2 + "_" + TrxPrefix.getPrefix() );
		bPartner.setName(name);

		bPartner.setName2(name2);
		bPartner.setIsCustomer(isCustomer);
		bPartner.setIsVendor(isVendor);
		bPartner.setIsEmployee(isEmployee);
		bPartner.setIsSalesRep(isSalesRep);
		bPartner.setBPartner_Parent_ID(parentBpId);
//		bPartner.setDescription(Constants.WEBSTORE_CUSTOMER);
		bPartner.setAD_Org_ID(orgId);
        bPartner.setSalesRep_ID(Env.getAD_User_ID(ctx));//required for dunning possible bug in Compiere
		
        PoManager.save(bPartner);
		
		MLocation location = LocationManager.saveLocation(ctx, orgId, locationId, address1, address2, postalAddress1,regionId, city,countryId, trxName);

		//We need to make the link of the billing address to the business partner
		MBPartnerLocation bplocation = null;
		
		if (bPartner.getPrimaryC_BPartner_Location_ID() == 0)
			bplocation = new MBPartnerLocation(bPartner);	
		else
			bplocation = new MBPartnerLocation(ctx, bPartner.getPrimaryC_BPartner_Location_ID(), trxName);
		
		bplocation.setPhone(bpPhone);
		bplocation.setPhone2(phone2);
		bplocation.setFax(fax);
		bplocation.setC_Location_ID(location.get_ID());
		bplocation.setIsShipTo(isShipTo);
		bplocation.setIsBillTo(isBillTo);
		bplocation.setAD_Org_ID(orgId);
		
		//UDIMBPartnerLocation udiBpLocation = new UDIMBPartnerLocation(bplocation);
		//udiBpLocation.save();
		
		PoManager.save(bplocation);
		
		
		//Set the default location for the business partner
		bPartner.setPrimaryC_BPartner_Location_ID(bplocation.get_ID());
		
		//UDIMBPartner udiBPartner_defaultLocation = new UDIMBPartner(bPartner);
		//udiBPartner_defaultLocation.save();
		PoManager.save(bPartner);
		
		return bPartner;

	}
	public static MBPartner editBPartner(Properties ctx, int orgId, int bpartnerId, String name, String name2, boolean isCustomer, boolean isVendor, boolean isEmployee, boolean isSalesRep,
            String address1, String address2, String postalAddress, String city, String phoneNo,String phone2, String fax, int regionId,String trxName) throws OperationException
    {
        
         	MBPartner bPartner = new MBPartner(ctx,bpartnerId,trxName);
            
            bPartner.setName(name);
            bPartner.setName2(name2);
            bPartner.setIsVendor(isVendor);
            bPartner.setIsCustomer(isCustomer);
            bPartner.setIsEmployee(isEmployee);
            bPartner.setIsSalesRep(isSalesRep);
            bPartner.setAD_Org_ID(orgId);
            
            if(bPartner.getSalesRep_ID()==0)
            {
                bPartner.setSalesRep_ID(Env.getAD_User_ID(ctx));
            }
             
            
            MBPartnerLocation bpLocation = editPhoneAndFax(ctx,bpartnerId,phoneNo,phone2,fax, trxName);            
            
            LocationManager.editLocation(ctx, bpLocation.getC_Location_ID(), address1, postalAddress, regionId, city, trxName);
            
            PoManager.save(bpLocation);
            
            PoManager.save(bPartner);
            
        return bPartner;
        
    }
	
	public static MBPartnerLocation editPhoneAndFax(Properties ctx, int bpId, String phone,String phone2, String fax, String trxName) throws OperationException
	{
		
	    if(bpId<=0)
	        throw new OperationException("BPartner does not exist");
	    
		MBPartner bp = new MBPartner(ctx, bpId, trxName);
		MBPartnerLocation bpLocation;
			
		try
		{
		    bpLocation = new MBPartnerLocation(ctx, bp.getPrimaryC_BPartner_Location_ID(),trxName);
		    
		    if(bpLocation==null)
		        throw new OperationException("BPartner Location does not exist");
			 
		    bpLocation.setPhone(phone);	
            bpLocation.setPhone2(phone2);
			bpLocation.setFax(fax);
			bpLocation.setAD_Org_ID(bp.getAD_Org_ID());
			
			PoManager.save(bpLocation);
				
			
		}
		catch(OperationException e)
		{
			throw new OperationException("Error: Could not edit phone and fax!!");
			
		}
		
		
		return bpLocation;
		
	}
	
	
	public static ArrayList<BPartnerBean> getCreditorsDebtors(Properties ctx, boolean queryCreditors, String trxName) throws OperationException
	{
		ArrayList<BPartnerBean> bpList = new ArrayList<BPartnerBean>();
		
		StringBuffer sql = new StringBuffer("SELECT C_BPARTNER_ID,NAME,NAME2,ISCUSTOMER,ISVENDOR,TOTALOPENBALANCE,ACTUALLIFETIMEVALUE,so_creditlimit,so_creditused " +
				" FROM C_BPARTNER WHERE ISACTIVE = 'Y' AND AD_CLIENT_ID = ?");
				
		if(queryCreditors)
			sql.append(" AND ISVENDOR = 'Y' " );			
		else
			sql.append(" AND ISCUSTOMER = 'Y' ");	
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try 
		{
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				BPartnerBean bean = new BPartnerBean();
				bean.setBpartnerId(rs.getInt(1));
				bean.setPartnerName(rs.getString(2));
				bean.setName2(rs.getString(3));
				bean.setIsCustomer("Y".equals(rs.getString(4)));
				bean.setIsVendor("Y".equals(rs.getString(5)));				
				bean.setActualLifetimeValue(rs.getBigDecimal(7));
				bean.setCreditLimit(rs.getBigDecimal(8));
				bean.setCreditUsed(rs.getBigDecimal(9));
				
				BigDecimal openBalance = rs.getBigDecimal(6);
				if(openBalance == null)
				{
					openBalance = Env.ZERO;
				}
				
				if(queryCreditors)
				{
					openBalance = openBalance.negate();
				}
				
				bean.setOpenBalance(openBalance);
				
				bpList.add(bean);
			}			
			
		} 
		catch (Exception e) 
		{
			throw new OperationException(e);
		}
		finally
		{
			DB.close(rs);
			DB.close(pstmt);
		}
		
		return bpList;
		
	}
	
	public static ArrayList<StatementOfAccountBean> getCreditorsHistory(Properties ctx, int c_bpartner_id, Timestamp startDate, Timestamp endDate, String trxName)
	{
		StringBuffer sb = new StringBuffer(
				" SELECT * FROM ( " +
				" SELECT C_INVOICE.CREATED AS DATETRX,C_INVOICE.DOCUMENTNO AS REFERENCE,C_DOCTYPE.NAME AS DETAILS,C_INVOICE.GRANDTOTAL AS AMT, C_INVOICE.C_ORDER_ID AS ID " +
				" FROM C_INVOICE " +
				" INNER JOIN C_DOCTYPE ON C_INVOICE.C_DOCTYPE_ID = C_DOCTYPE.C_DOCTYPE_ID " +
				" WHERE C_INVOICE.C_BPARTNER_ID = ? " +
				" AND C_INVOICE.DOCSTATUS IN ('CO','CL') " +
				" AND C_DOCTYPE.NAME = 'AP Invoice' " +
				" UNION " +
				" SELECT C_INVOICE.CREATED AS DATETRX,C_INVOICE.DOCUMENTNO AS REFERENCE,C_DOCTYPE.NAME AS DETAILS,C_INVOICE.GRANDTOTAL AS AMT, C_INVOICE.C_ORDER_ID AS ID " +
				" FROM C_INVOICE " +
				" INNER JOIN C_DOCTYPE ON C_INVOICE.C_DOCTYPE_ID = C_DOCTYPE.C_DOCTYPE_ID " +
				" WHERE C_INVOICE.C_BPARTNER_ID = ? " +
				" AND C_INVOICE.DOCSTATUS IN ('CO','CL') " +
				" AND C_DOCTYPE.NAME = 'AP CreditMemo' " +
				" UNION " +
				" SELECT C_PAYMENT.CREATED,C_PAYMENT.C_PAYMENT_ID || '' AS REFERENCE ,C_DOCTYPE.NAME AS DETAILS,C_PAYMENT.PAYAMT + C_PAYMENT.DISCOUNTAMT + C_PAYMENT.WRITEOFFAMT AS AMT,C_PAYMENT.C_PAYMENT_ID AS ID " +
				" FROM C_PAYMENT " +
				" INNER JOIN C_DOCTYPE ON C_PAYMENT.C_DOCTYPE_ID = C_DOCTYPE.C_DOCTYPE_ID " +
				" WHERE C_PAYMENT.C_BPARTNER_ID = ? " +
				" AND C_PAYMENT.DOCSTATUS IN ('CO','CL') " +
				" AND C_DOCTYPE.NAME = 'AP Payment' " +
				" UNION " +
				" SELECT C_INVOICE.CREATED AS DATETRX,C_CASHLINE.C_CASHLINE_ID || '' AS REFERENCE,'AP Payment - Cash' AS DETAILS,0 - (C_CASHLINE.AMOUNT + C_CASHLINE.DISCOUNTAMT + C_CASHLINE.WRITEOFFAMT) AS AMT,C_CASHLINE.C_CASHLINE_ID AS ID " +
				" FROM C_INVOICE " +
				" INNER JOIN C_CASHLINE ON (C_CASHLINE.C_INVOICE_ID = C_INVOICE.C_INVOICE_ID) " +
				" INNER JOIN C_CASH ON (C_CASH.C_CASH_ID = C_CASHLINE.C_CASH_ID AND C_CASH.DOCSTATUS IN ('CO','CL')) " +
				" INNER JOIN C_DOCTYPE ON C_INVOICE.C_DOCTYPE_ID = C_DOCTYPE.C_DOCTYPE_ID " +
				" WHERE C_INVOICE.C_BPARTNER_ID = ? " +
				" AND C_INVOICE.DOCSTATUS IN ('CO','CL') " +
				" AND C_DOCTYPE.NAME = 'AP Invoice' " +
				" ) AS HISTORY ORDER BY DATETRX ASC, DETAILS ASC");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		BigDecimal balance = Env.ZERO;		
		ArrayList<StatementOfAccountBean> reportData = new ArrayList<StatementOfAccountBean>();
				
		try 
		{
			pstmt = DB.prepareStatement(sb.toString(), trxName);
			pstmt.setInt(1, c_bpartner_id);
			pstmt.setInt(2, c_bpartner_id);
			pstmt.setInt(3, c_bpartner_id);
			pstmt.setInt(4, c_bpartner_id);
			
			rs = pstmt.executeQuery();
			
			//int previousMonth = -2; // 
			
			while(rs.next())
			{
				Timestamp date = rs.getTimestamp(1);
				String reference = rs.getString(2);
				String docType = rs.getString(3);
				BigDecimal amount = rs.getBigDecimal(4);
				BigDecimal credit = Env.ZERO;
				BigDecimal debit = Env.ZERO;	
				int docId = rs.getInt(5);
				
				
				if(docType.startsWith("AP Payment") || docType.equalsIgnoreCase("AP CreditMemo"))
				{
					credit = amount;
					balance = balance.subtract(amount);
				}
				else
				{
					debit = amount;
					balance = balance.add(amount);
				}
				
				String details = "";
				String table = "Order";
				
				if(docType.equalsIgnoreCase("AP Payment"))
				{
					details = "PAYMENT";
					table = "Payment";
				}
				else if(docType.equalsIgnoreCase("AP Payment - Cash"))
				{
					details = "PAYMENT";
					table = "CashLine";
				}
				else if(docType.equalsIgnoreCase("AP CreditMemo"))
				{
					details = "Account Returns";
				}
				else if(docType.equalsIgnoreCase("AP Invoice"))
				{
					details = "Account Purchases";
				}
				else
				{
					details = docType;
				}
				
				StatementOfAccountBean bean = new StatementOfAccountBean();				
				bean.setDate(date);
				bean.setReference(reference);
				bean.setDocType(table);
				bean.setDetails(details);
				bean.setDebit(debit);
				bean.setCredit(credit);
				bean.setBalance(balance);
				bean.setDocId(docId);
				
				reportData.add(bean);
			}			
			
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			DB.close(rs, pstmt);
		}
		
		return reportData;
	}
	
	public static ArrayList<StatementOfAccountBean> getDebtorsHistory(Properties ctx, int c_bpartner_id, Timestamp startDate, Timestamp endDate, String trxName)
	{
		StringBuffer sb = new StringBuffer(
				" SELECT * FROM ( " +
				" SELECT C_INVOICE.CREATED AS DATETRX,C_INVOICE.DOCUMENTNO AS REFERENCE,C_DOCTYPE.NAME AS DETAILS,C_INVOICE.GRANDTOTAL AS AMT, C_INVOICE.C_ORDER_ID AS ID " +
				" FROM C_INVOICE " +
				" INNER JOIN C_DOCTYPE ON C_INVOICE.C_DOCTYPE_ID = C_DOCTYPE.C_DOCTYPE_ID " +
				" WHERE C_INVOICE.C_BPARTNER_ID = ? " +
				" AND C_INVOICE.DOCSTATUS IN ('CO','CL') " +
				" AND C_DOCTYPE.NAME = 'AR Invoice' " +
				" UNION " +
				" SELECT C_INVOICE.CREATED AS DATETRX,C_INVOICE.DOCUMENTNO AS REFERENCE,C_DOCTYPE.NAME AS DETAILS,C_INVOICE.GRANDTOTAL AS AMT, C_INVOICE.C_ORDER_ID AS ID " +
				" FROM C_INVOICE " +
				" INNER JOIN C_DOCTYPE ON C_INVOICE.C_DOCTYPE_ID = C_DOCTYPE.C_DOCTYPE_ID " +
				" WHERE C_INVOICE.C_BPARTNER_ID = ? " +
				" AND C_INVOICE.DOCSTATUS IN ('CO','CL') " +
				" AND C_DOCTYPE.NAME = 'AR Credit Memo' " +
				" UNION " +
				" SELECT C_PAYMENT.CREATED,C_PAYMENT.C_PAYMENT_ID || '' AS REFERENCE ,C_DOCTYPE.NAME AS DETAILS,C_PAYMENT.PAYAMT + C_PAYMENT.DISCOUNTAMT + C_PAYMENT.WRITEOFFAMT AS AMT,C_PAYMENT.C_PAYMENT_ID AS ID " +
				" FROM C_PAYMENT " +
				" INNER JOIN C_DOCTYPE ON C_PAYMENT.C_DOCTYPE_ID = C_DOCTYPE.C_DOCTYPE_ID " +
				" WHERE C_PAYMENT.C_BPARTNER_ID = ? " +
				" AND C_PAYMENT.DOCSTATUS IN ('CO','CL') " +
				" AND C_DOCTYPE.NAME = 'AR Receipt' " +
				" UNION " +
				" SELECT C_INVOICE.CREATED AS DATETRX,C_CASHLINE.C_CASHLINE_ID || '' AS REFERENCE,'AR Receipt - Cash' AS DETAILS,C_CASHLINE.AMOUNT + C_CASHLINE.DISCOUNTAMT + C_CASHLINE.WRITEOFFAMT AS AMT,C_CASHLINE.C_CASHLINE_ID AS ID " +
				" FROM C_INVOICE " +
				" INNER JOIN C_CASHLINE ON (C_CASHLINE.C_INVOICE_ID = C_INVOICE.C_INVOICE_ID) " +
				" INNER JOIN C_CASH ON (C_CASH.C_CASH_ID = C_CASHLINE.C_CASH_ID AND C_CASH.DOCSTATUS IN ('CO','CL')) " +
				" INNER JOIN C_DOCTYPE ON C_INVOICE.C_DOCTYPE_ID = C_DOCTYPE.C_DOCTYPE_ID " +
				" WHERE C_INVOICE.C_BPARTNER_ID = ? " +
				" AND C_INVOICE.DOCSTATUS IN ('CO','CL') " +
				" AND C_DOCTYPE.NAME = 'AR Invoice' " +
				" ) AS HISTORY ORDER BY DATETRX ASC, DETAILS ASC");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		BigDecimal balance = Env.ZERO;		
		ArrayList<StatementOfAccountBean> reportData = new ArrayList<StatementOfAccountBean>();
				
		try 
		{
			pstmt = DB.prepareStatement(sb.toString(), trxName);
			pstmt.setInt(1, c_bpartner_id);
			pstmt.setInt(2, c_bpartner_id);
			pstmt.setInt(3, c_bpartner_id);
			pstmt.setInt(4, c_bpartner_id);
			
			rs = pstmt.executeQuery();
			
			//int previousMonth = -2; // 
			
			while(rs.next())
			{
				Timestamp date = rs.getTimestamp(1);
				String reference = rs.getString(2);
				String docType = rs.getString(3);
				BigDecimal amount = rs.getBigDecimal(4);
				BigDecimal credit = Env.ZERO;
				BigDecimal debit = Env.ZERO;
				int docId = rs.getInt(5);
				
				
				if(docType.startsWith("AR Receipt") || docType.equalsIgnoreCase("AR Credit Memo"))
				{
				    debit = amount;
                    balance = balance.subtract(amount);
				}
				else
				{
				    credit = amount;
                    balance = balance.add(amount);
				}
				
				String details = "";
				String table = "Order";
				
				if(docType.equalsIgnoreCase("AR Receipt"))
				{
					details = "PAYMENT";
					table = "Payment";
				}
				else if(docType.equalsIgnoreCase("AR Receipt - Cash"))
				{
					details = "PAYMENT";
					table = "CashLine";
				}
				else if(docType.equalsIgnoreCase("AR Credit Memo"))
				{
					details = "Account Returns";
				}
				else if(docType.equalsIgnoreCase("AR Invoice"))
				{
					details = "Account Sales";
				}
				else
				{
					details = docType;
				}
				
				StatementOfAccountBean bean = new StatementOfAccountBean();				
				bean.setDate(date);
				bean.setReference(reference);
				bean.setDocType(table);
				bean.setDetails(details);
				bean.setDebit(debit);
				bean.setCredit(credit);
				bean.setBalance(balance);
				bean.setDocId(docId);
				
				reportData.add(bean);
			}			
			
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			DB.close(rs, pstmt);
		}
		
		return reportData;
	}
		
}
