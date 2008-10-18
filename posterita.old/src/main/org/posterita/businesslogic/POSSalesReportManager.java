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
 * 17-Jul-2006 14:15:12 by praveen
 *
 */

package org.posterita.businesslogic;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.util.DB;
import org.compiere.util.Env;

import org.posterita.Constants;
import org.posterita.beans.ReportBean;
import org.posterita.beans.UDIPair;
import org.posterita.exceptions.OperationException;

public class POSSalesReportManager 
{
	public static ArrayList<UDIPair> getSalesGroupList(Properties ctx,ReportBean bean) throws OperationException
	{
		ArrayList<UDIPair> groupList = new ArrayList<UDIPair>();
        groupList.add(new UDIPair("Revenue Recognition",Constants.REVENUE_RECOGNITION));
        groupList.add(new UDIPair("Product",Constants.PRODUCT));
        groupList.add(new UDIPair("Customer",Constants.CUSTOMER));
        groupList.add(new UDIPair("AttributeSet",Constants.ATTRIBUTESET));
        groupList.add(new UDIPair("Group1",Constants.GROUP1));
        groupList.add(new UDIPair("Group2",Constants.GROUP2));
        
       // String fromDate = null;
       // String toDate = null; 
        
        if(bean.getDateRange().equalsIgnoreCase(Constants.FIXED_DATE_RANGE))
        {
        	if(bean.getTimePeriod() == null)
        	{
        		throw new OperationException("Invalid parameter: timeperiod is null");
        	}
        	
        	//Date startDate = 
        	ReportDateManager.getStartDateForPeriod(bean.getTimePeriod());
        	//Date endDate = 
        	ReportDateManager.getEndDateForPeriod(bean.getTimePeriod());
        	
        	//SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        	//fromDate = sdf.format(startDate);
        	//toDate = sdf.format(endDate);
        }
        else
        {
        	//fromDate = BirtReportManager.getFromDate(bean);
           // toDate = BirtReportManager.getToDate(bean); 
        }       
        
        int ad_client_id = Env.getAD_Client_ID(ctx);
        
        if(bean.getAccountId()==null)
        {
        	throw new OperationException("Invalid parameter value : accountId->" + bean.getAccountId());
        }
        
        int account_id = bean.getAccountId().intValue();
        
        String sql = "select distinct attrSet.NAME as ATTRIBUTESET_NAME,attr.NAME as ATTRIBUTE_NAME,attrSet.M_ATTRIBUTESET_ID as ATTRIBUTESET_ID,attr.M_ATTRIBUTE_ID as ATTRIBUTE_ID " +
        		"from M_ATTRIBUTESETINSTANCE attrSetIns,M_PRODUCT prod,M_ATTRIBUTESET attrSet,M_ATTRIBUTEUSE attrUse,M_ATTRIBUTE attr,FACT_ACCT fact " +
        		"where prod.M_ATTRIBUTESETINSTANCE_ID = attrSetIns.M_ATTRIBUTESETINSTANCE_ID " +
        		"and attrSet.M_ATTRIBUTESET_ID = attrSetIns.M_ATTRIBUTESET_ID " +
        		"and attrUse.M_ATTRIBUTESET_ID = attrSet.M_ATTRIBUTESET_ID " +
        		"and attr.M_ATTRIBUTE_ID = attrUse.M_ATTRIBUTE_ID " +
        		"and fact.M_PRODUCT_ID = prod.M_PRODUCT_ID " +
        		"and fact.account_id = " +
        		"(select C_ELEMENTVALUE_ID from C_ELEMENTVALUE where value = ? and AD_CLIENT_ID = ? ) " +        		
        		"and fact.AD_CLIENT_ID = ? ";
        
        PreparedStatement pstmt = DB.prepareStatement(sql,null);
        
        try 
        {
			pstmt.setString(1,account_id+"");
			pstmt.setInt(2,ad_client_id);			
			pstmt.setInt(3,ad_client_id);
			
			ArrayList<Object[]> data = ReportManager.getReportData(pstmt,false);
			String attributeSetName = null;
			String attributeSetId = null;
			String attributeName = null;
			String attributeId = null;
			
			for (Object[] objects : data) 
			{
				attributeSetName = objects[0]+"";
				attributeName = objects[1]+"";
				
				attributeSetId = objects[2]+"";
				attributeId = objects[3]+"";
				
				groupList.add(new UDIPair(attributeSetName+"_"+attributeName,attributeSetId+"_"+attributeId)); 
			}
		} 
        catch (SQLException e) 
        {
			throw new OperationException(e);
		}
        
        return groupList;
	}
	
	public static ArrayList<UDIPair> getPriceQtyFilter(Properties ctx)
	{
		ArrayList<UDIPair> filter = new ArrayList<UDIPair>();
		filter.add(new UDIPair("Price",Constants.PRICE));
		filter.add(new UDIPair("Quantity",Constants.QUANTITY));
		
		return filter;
	}
}
