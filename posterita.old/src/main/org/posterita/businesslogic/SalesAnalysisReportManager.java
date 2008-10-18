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
 * 05-Jul-2006 13:45:44 by praveen
 *
 */

package org.posterita.businesslogic;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.model.MAttribute;
import org.compiere.model.MElementValue;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.posterita.Constants;
import org.posterita.exceptions.OperationException;

public class SalesAnalysisReportManager 
{
	public static String getCVSReport(Properties ctx,String fromDate,String toDate) throws OperationException
	{		
		ArrayList reportDataSource = getReportData(ctx,fromDate,toDate);		
		
		return CSVReportManager.generateCSVReport(ctx,reportDataSource);
	}
	
	public static String getPDFReport(Properties ctx,String fromDate,String toDate) throws OperationException
	{		
		return null;
	}
		
	private static ArrayList getReportData(Properties ctx,String fromDate,String toDate) throws OperationException
	{
		ArrayList reportData = null;		
		String sql = getReportSQL();
		int ad_client_id = Env.getAD_Client_ID(ctx);
		int ad_org_id = Env.getAD_Org_ID(ctx);
		
		PreparedStatement pstmt = DB.prepareStatement(sql,null);		
		
		try 
		{
			pstmt.setInt(1,ad_client_id);
			pstmt.setInt(2,ad_org_id);
			pstmt.setString(3,fromDate);
			pstmt.setString(4,toDate);
			
			reportData = ReportManager.getReportData(ctx,pstmt);		
			
			return reportData;
			
		} 
		catch (SQLException e) 
		{
			throw new OperationException(e);
		} 
		finally 
		{
			pstmt=null;			
		}				
	}
	
	private static String getReportSQL()
	{
		String sql = "select bp.name as \"Supplier\"," +
				"pc.name as \"Category\"," +
				"attr_brand as \"Brand\"," +
				"attr_model as \"Model\"," +
				"attr_design as \"Design\"," +
				"attr_colour as \"Colour\"," +
				"attr_size as \"Size\"," +
				"sum(ol.qtyordered) as \"Quantity\"," +
				"sum(ol.LINENETAMT) as \"Amount\" "+
				"from c_orderline ol,c_order ord,u_tshirt_v v,c_bpartner bp,c_bpartner_product bpp,C_REVENUERECOGNITION pc,M_PRODUCT pr " +
				"where ord.orderType='POS Order' " +
				"and ol.c_order_id=ord.c_order_id " +
				"and v.m_product_id=ol.m_product_id " +
				"and ol.M_PRODUCT_ID=bpp.M_PRODUCT_ID " +
				"and bpp.c_bpartner_id=bp.c_bpartner_id " +
				"and ol.M_PRODUCT_ID=pr.M_PRODUCT_ID " +
				"and ol.AD_CLIENT_ID=? " +
				"and ol.AD_ORG_ID=? " +
				"and pr.C_REVENUERECOGNITION_ID=pc.C_REVENUERECOGNITION_ID " +
				"and ord.docstatus='CO' " +
				//"and ol.CREATED between to_date(?,'DD-MON-YYYY HH24:MI:SS')  " +
				//"and to_date(?,'DD-MON-YYYY HH24:MI:SS') " 
				"and ol.CREATED between ?  and ? "+ 
				"group by bp.name,pc.name,attr_brand,attr_model,attr_design,attr_colour,attr_size";
		
		return sql;
	}
	
	public static String getPieChartDataSetSQL(Properties ctx,int account_id,String fromDate,String toDate,String salesGroup) throws OperationException
	{
		return dataSetSQL(ctx,account_id,fromDate,toDate,salesGroup);
	}//getPieChartDataSetSQL
	
	public static String getBarChartDataSetSQL(Properties ctx,int account_id,String fromDate,String toDate,String salesGroup) throws OperationException
	{
		return dataSetSQL(ctx,account_id,fromDate,toDate,salesGroup);
	}//getBarChartDatasetSQL
		
	public static String getTabularDataSetSQL(Properties ctx,int account_id,String fromDate,String toDate,String salesGroup) throws OperationException
	{
		
		int ad_client_id = Env.getAD_Client_ID(ctx);
		String sql = null;
		
		
		
		//check account type
		boolean isTaxDue 	   		= (account_id == Constants.TAX_DUE);
        boolean isTaxCredit    	= (account_id == Constants.TAX_CREDIT);
        boolean isProfitMargin 	= (account_id == Constants.PROFIT_MARGIN);
        
        final int TRADE_REVENUE 	= Constants.TRADE_REVENUE.intValue();
        final int COGS					= Constants.COGS.intValue();
        
        if(isProfitMargin)
        {
        	String sql1 = getTabularDataSetSQL(ctx, TRADE_REVENUE, fromDate, toDate, salesGroup);
        	String sql2 = getTabularDataSetSQL(ctx, COGS, fromDate, toDate, salesGroup);
        	
        	sql2 = sql2.replace("SUM(0 - FACT.QTY)","SUM(FACT.QTY)");
        	
        	if(salesGroup.equalsIgnoreCase(Constants.REVENUE_RECOGNITION))
        	{
        		sql = "" +
    			"select rev.\"Revenue_Recognition\",rev.\"Date\",(rev.\"Value\" + co.\"Value\") as \"value\",rev.\"Qty\" from" +
    			"(" + sql1 + ") rev," +
    			"(" + sql2 + ") co " +
    			"where rev.\"Revenue_Recognition\" = co.\"Revenue_Recognition\" " +
    			"and co.\"Date\" = rev.\"Date\" " +
    			"and co.\"Qty\" = rev.\"Qty\"";
        	}
        	else if(salesGroup.equalsIgnoreCase(Constants.ATTRIBUTESET))
        	{
        		sql = "" +
    			"select rev.\"Attributeset\",rev.\"Date\",(rev.\"Value\" + co.\"Value\") as \"value\",rev.\"Qty\" from" +
    			"(" + sql1 + ") rev," +
    			"(" + sql2 + ") co " +
    			"where rev.\"Attributeset\" = co.\"Attributeset\" " +
    			"and co.\"Date\" = rev.\"Date\" " +
    			"and co.\"Qty\" = rev.\"Qty\"";
        	}
        	else if(salesGroup.equalsIgnoreCase(Constants.PRODUCT))
        	{
        		sql = "" +
    			"select rev.\"Product\",rev.\"Date\",(rev.\"Value\" + co.\"Value\") as \"value\",rev.\"Qty\" from" +
    			"(" + sql1 + ") rev," +
    			"(" + sql2 + ") co " +
    			"where rev.\"Product\" = co.\"Product\" " +
    			"and co.\"Date\" = rev.\"Date\" " +
    			"and co.\"Qty\" = rev.\"Qty\"";
        	}
        	else if(salesGroup.equalsIgnoreCase(Constants.GROUP1))
        	{
        		sql = "" +
    			"select rev.\"Group1\",rev.\"Date\",(rev.\"Value\" + co.\"Value\") as \"value\",rev.\"Qty\" from" +
    			"(" + sql1 + ") rev," +
    			"(" + sql2 + ") co " +
    			"where rev.\"Group1\" = co.\"Group1\" " +
    			"and co.\"Date\" = rev.\"Date\" " +
    			"and co.\"Qty\" = rev.\"Qty\"";
        	}
        	else if(salesGroup.equalsIgnoreCase(Constants.GROUP2))
        	{
        		sql = "" +
    			"select rev.\"Group2\",rev.\"Date\",(rev.\"Value\" + co.\"Value\") as \"value\",rev.\"Qty\" from" +
    			"(" + sql1 + ") rev," +
    			"(" + sql2 + ") co " +
    			"where rev.\"Group2\" = co.\"Group2\" " +
    			"and co.\"Date\" = rev.\"Date\" " +
    			"and co.\"Qty\" = rev.\"Qty\"";
        	}
        	else if(salesGroup.equalsIgnoreCase(Constants.CUSTOMER))
        	{
        		sql = "" +
    			"select rev.\"Customer\",rev.\"Date\",(rev.\"Value\" + co.\"Value\") as \"value\",rev.\"Qty\" from" +
    			"(" + sql1 + ") rev," +
    			"(" + sql2 + ") co " +
    			"where rev.\"Customer\" = co.\"Customer\" " +
    			"and co.\"Date\" = rev.\"Date\" " +
    			"and co.\"Qty\" = rev.\"Qty\"";
        	}
        	else
        	{
        		String[] s = salesGroup.split("_");
    			
    			if(s.length!=2)
    			{
    				throw new OperationException("Cannot generate sql. Unknown salesGroup parameter:"+salesGroup);
    			}
    			
    			int attributeId = Integer.parseInt(s[1]);
    			
    			String attributeName = new MAttribute(ctx,attributeId,null).getName();
    			
    			sql = "" +
    			"select rev.\""+ attributeName +"\",rev.\"Date\",(rev.\"Value\" + co.\"Value\") as \"value\",rev.\"Qty\" from" +
    			"(" + sql1 + ") rev," +
    			"(" + sql2 + ") co " +
    			"where rev.\""+ attributeName +"\" = co.\""+ attributeName +"\" " +
    			"and co.\"Date\" = rev.\"Date\" " +
    			"and co.\"Qty\" = rev.\"Qty\"";
        	}
        	
        	
        	
        	return sql;
        }
        
        if(isTaxCredit || isTaxDue)
        {
        	int[] ids = MElementValue.getAllIDs(MElementValue.Table_Name,"AD_CLIENT_ID ="+ Env.getAD_Client_ID(ctx) + " AND VALUE ='"+ account_id + "'",null);
        	MElementValue elementValue = new MElementValue(ctx,ids[0],null);
            String accountName = elementValue.getName();
            
        	sql = "" +
        	"select COALESCE(SUM(fact.AMTACCTDR - fact.AMTACCTCR), 0) as \""+ accountName +"\" " +
        	"from  FACT_ACCT FACT where fact.ACCOUNT_ID = " +
        	"(select C_ELEMENTVALUE_ID from C_ELEMENTVALUE where value = '" + account_id + "' and AD_CLIENT_ID = "+ ad_client_id +") " +
        	//"and fact.DATEACCT between to_date('" + fromDate + "','DD-MON-YYYY HH24:MI:SS') " +
        	//"and to_date('" + toDate + "','DD-MON-YYYY HH24:MI:SS') " +
        	"fact.DATEACCT between "+ fromDate  + " and "+ toDate + " "+
        	"and fact.AD_CLIENT_ID = " + ad_client_id;
        	
        	return sql;
        }
		
		if(salesGroup.equalsIgnoreCase(Constants.REVENUE_RECOGNITION))
		{
			sql = "" +
			//"select DECODE(rev.NAME, null, 'others', rev.Name) \"Revenue_Recognition\"," +	//1.Revenue Recognition
			"select CASE WHEN rev.NAME =  null THEN 'others' ELSE rev.Name END AS \"Revenue_Recognition\"," +	//1.Revenue Recognition
			"to_char(fact.DATEACCT,'DD-MON-YYYY') as \"Date\"," +			//2.Date
			"SUM(fact.AMTACCTCR-fact.AMTACCTDR) as \"Value\"," +			//3.Value
			" SUM(0 - FACT.QTY) as \"Qty\" " +								//4.Qty
			"from FACT_ACCT fact, (M_PRODUCT prod left outer join C_REVENUERECOGNITION rev on rev.C_REVENUERECOGNITION_ID = prod.C_REVENUERECOGNITION_ID ) " +
			"where fact.ACCOUNT_ID = " +
			"(select C_ELEMENTVALUE_ID from C_ELEMENTVALUE where value = '"+account_id+"' and AD_CLIENT_ID = "+ad_client_id+") " +
			"and fact.M_PRODUCT_ID = prod.M_PRODUCT_ID " +
			//"and rev.C_REVENUERECOGNITION_ID(+) = prod.C_REVENUERECOGNITION_ID " +
			"and fact.DATEACCT between " +
			//"to_date('"+fromDate+"','DD-MON-YYYY HH24:MI:SS') " +
			fromDate +
			"and " +
			//"to_date('"+toDate+"','DD-MON-YYYY HH24:MI:SS') " +
			toDate + " "+
			"and fact.AD_CLIENT_ID = "+ad_client_id +
			//" group by rev.NAME,to_char(fact.DATEACCT,'DD-MON-YYYY') " +
			//" order by to_char(fact.DATEACCT,'DD-MON-YYYY') desc,rev.NAME asc";
			" group by rev.NAME,fact.DATEACCT " +
			" order by fact.DATEACCT desc,rev.NAME asc";
		}
		else if(salesGroup.equalsIgnoreCase(Constants.ATTRIBUTESET))
		{
			sql = "" +
			//" select DECODE(attrSet.NAME, null, 'others', attrSet.NAME) \"Attributeset\"," +	//1.Attributeset
			" select CASE WHEN attrSet.NAME = null THEN 'others' ELSE attrSet.NAME END AS \"Attributeset\"," +	//1.Attributeset
			" to_char(fact.DATEACCT,'DD-MON-YYYY') as \"Date\"," +			//2.Date
			" SUM(fact.AMTACCTCR-fact.AMTACCTDR) as \"Value\"," +			//3.Value
			" SUM(0 - FACT.QTY) as \"Qty\" " +								//4.Qty
			" from FACT_ACCT fact, (M_PRODUCT prod left outer join C_REVENUERECOGNITION rev on rev.C_REVENUERECOGNITION_ID = prod.C_REVENUERECOGNITION_ID ),M_ATTRIBUTESETINSTANCE attrSetIns, M_ATTRIBUTESET attrSet " +
			" where fact.ACCOUNT_ID = " +
			" (select C_ELEMENTVALUE_ID from C_ELEMENTVALUE where value = '"+account_id+"' and AD_CLIENT_ID = "+ad_client_id +") " +
			" and fact.M_PRODUCT_ID = prod.M_PRODUCT_ID " +
			//" and rev.C_REVENUERECOGNITION_ID(+) = prod.C_REVENUERECOGNITION_ID " +
			" and prod.M_ATTRIBUTESETINSTANCE_ID = attrSetIns.M_ATTRIBUTESETINSTANCE_ID " +
			" and attrSet.M_ATTRIBUTESET_ID = attrSetIns.M_ATTRIBUTESET_ID " +
			" and fact.DATEACCT between " +
			//" to_date('"+fromDate+"','DD-MON-YYYY HH24:MI:SS') " +
			fromDate +
			" and " +
			//" to_date('"+toDate+"','DD-MON-YYYY HH24:MI:SS') " +
			toDate +
			" and fact.AD_CLIENT_ID = "+ad_client_id +
			//" group by attrSet.name,to_char(fact.DATEACCT,'DD-MON-YYYY') " +
			//" order by to_char(fact.DATEACCT,'DD-MON-YYYY') desc,attrSet.name asc";
			" group by attrSet.name,fact.DATEACCT " +
			" order by fact.DATEACCT, desc,attrSet.name asc";
		}
		else if(salesGroup.equalsIgnoreCase(Constants.PRODUCT))
		{
			sql = "" +
			" select PROD.NAME \"Product\"," +						//1.Product			
			" to_char(fact.DATEACCT,'DD-MON-YYYY') as \"Date\"," +		//3.Date
			" SUM(FACT.AMTACCTCR - FACT.AMTACCTDR) as \"Value\"," +	//4.Value
			" SUM(0 - FACT.QTY) as \"Qty\" " +							//5.Qty
			" from FACT_ACCT FACT, M_PRODUCT PROD " +
			" where FACT.ACCOUNT_ID = " +
			" (select C_ELEMENTVALUE_ID from C_ELEMENTVALUE where value = '"+account_id+"' and AD_CLIENT_ID = "+ad_client_id +") " +
			" AND FACT.M_PRODUCT_ID = PROD.M_PRODUCT_ID " +
			" and fact.DATEACCT between " +
			//" to_date('"+fromDate+"','DD-MON-YYYY HH24:MI:SS') " +
			fromDate + 
			" and " +
			//" to_date('"+toDate+"','DD-MON-YYYY HH24:MI:SS') " +
			toDate +
			" and fact.AD_CLIENT_ID = "+ad_client_id +
			" GROUP BY to_char(fact.DATEACCT,'DD-MON-YYYY'),PROD.NAME" +
			" Order by to_char(fact.DATEACCT,'DD-MON-YYYY') desc,PROD.NAME asc";
		}
		else if(salesGroup.equalsIgnoreCase(Constants.GROUP1))
		{
			sql = "" +
			//" select DECODE(PROD.GROUP1, null, 'Ungroup', PROD.GROUP1) \"Group1\"," +						//1.Product			
			" select CASE WHEN PROD.GROUP1 = null THEN 'Ungroup' ELSE PROD.GROUP1 END AS \"Group1\"," +						//1.Product			
			" to_char(fact.DATEACCT,'DD-MON-YYYY') as \"Date\"," +		//3.Date
			" SUM(FACT.AMTACCTCR - FACT.AMTACCTDR) as \"Value\"," +	//4.Value
			" SUM(0 - FACT.QTY) as \"Qty\" " +							//5.Qty
			" from FACT_ACCT FACT, M_PRODUCT PROD " +
			" where FACT.ACCOUNT_ID = " +
			" (select C_ELEMENTVALUE_ID from C_ELEMENTVALUE where value = '"+account_id+"' and AD_CLIENT_ID = "+ad_client_id +") " +
			" AND FACT.M_PRODUCT_ID = PROD.M_PRODUCT_ID " +
			" and fact.DATEACCT between " +
			//" to_date('"+fromDate+"','DD-MON-YYYY HH24:MI:SS') " +
			fromDate +
			" and " +
			//" to_date('"+toDate+"','DD-MON-YYYY HH24:MI:SS') " +
			toDate +
			" and fact.AD_CLIENT_ID = "+ad_client_id +
			" GROUP BY to_char(fact.DATEACCT,'DD-MON-YYYY'),PROD.GROUP1" +
			" Order by to_char(fact.DATEACCT,'DD-MON-YYYY') desc,PROD.GROUP1 asc";
		}
		else if(salesGroup.equalsIgnoreCase(Constants.GROUP2))
		{
			sql = "" +
			//" select DECODE(PROD.GROUP2, null, 'Ungroup', PROD.GROUP2) \"Group2\"," +						//1.Product			
			" select CASE WHEN PROD.GROUP2 = null THEN 'Ungroup' ELSE PROD.GROUP2 END AS \"Group2\"," +						//1.Product			
			" to_char(fact.DATEACCT,'DD-MON-YYYY') as \"Date\"," +		//3.Date
			" SUM(FACT.AMTACCTCR - FACT.AMTACCTDR) as \"Value\"," +	//4.Value
			" SUM(0 - FACT.QTY) as \"Qty\" " +							//5.Qty
			" from FACT_ACCT FACT, M_PRODUCT PROD " +
			" where FACT.ACCOUNT_ID = " +
			" (select C_ELEMENTVALUE_ID from C_ELEMENTVALUE where value = '"+account_id+"' and AD_CLIENT_ID = "+ad_client_id +") " +
			" AND FACT.M_PRODUCT_ID = PROD.M_PRODUCT_ID " +
			" and fact.DATEACCT between " +
			//" to_date('"+fromDate+"','DD-MON-YYYY HH24:MI:SS') " +
			fromDate + 
			" and " +
			//" to_date('"+toDate+"','DD-MON-YYYY HH24:MI:SS') " +
			toDate +
			" and fact.AD_CLIENT_ID = "+ad_client_id +
			" GROUP BY to_char(fact.DATEACCT,'DD-MON-YYYY'),PROD.GROUP2" +
			" Order by to_char(fact.DATEACCT,'DD-MON-YYYY') desc,PROD.GROUP2 asc";
		}
		else if(salesGroup.equalsIgnoreCase(Constants.CUSTOMER))
		{
			sql = "" +
			//" select BP.NAME||' '||BP.NAME2 \"Customer\"," +						//1.Customer
			" Select BP.NAME as \"Customer\","+
			" to_char(fact.DATEACCT,'DD-MON-YYYY') as \"Date\"," +			//2.Date
			" SUM(FACT.AMTACCTCR - FACT.AMTACCTDR) as \"Value\"," +	//3.Value
			" SUM(0 - FACT.QTY) as \"Qty\" " +												//4.Qty
			" from FACT_ACCT FACT, C_BPARTNER BP " +
			" where FACT.ACCOUNT_ID = " +
			" (select C_ELEMENTVALUE_ID from C_ELEMENTVALUE where value = '"+account_id+"' and AD_CLIENT_ID = "+ad_client_id +") " +
			" AND FACT.C_BPARTNER_ID = BP.C_BPARTNER_ID " +
			" and fact.DATEACCT between " +
			//" to_date('"+fromDate+"','DD-MON-YYYY HH24:MI:SS') " +
			fromDate + 
			" and " +
			//" to_date('"+toDate+"','DD-MON-YYYY HH24:MI:SS') " +
			toDate +
			" and fact.AD_CLIENT_ID = "+ad_client_id +
			//" having SUM(0 - FACT.QTY) <> 0 " +
			" GROUP BY to_char(fact.DATEACCT,'DD-MON-YYYY'),BP.NAME" +
			" Order by to_char(fact.DATEACCT,'DD-MON-YYYY') desc,BP.NAME asc";
			//" GROUP BY to_char(fact.DATEACCT,'DD-MON-YYYY'),BP.NAME||' '||BP.NAME2" +
			//" Order by to_char(fact.DATEACCT,'DD-MON-YYYY') desc,BP.NAME||' '||BP.NAME2 asc";
		}
		else
		{
			String[] s = salesGroup.split("_");
			
			if(s.length!=2)
			{
				throw new OperationException("Cannot generate sql. Unknown salesGroup parameter:"+salesGroup);
			}
			
			int attributeSetId = Integer.parseInt(s[0]);
			int attributeId = Integer.parseInt(s[1]);
			
			String attributeName = new MAttribute(ctx,attributeId,null).getName();
			
			sql = "select ATTRIBUTE_VALUE as \""+attributeName+ "\"," +
					"to_char(DATEACCT,'DD-MON-YYYY') as \"Date\"," +
					"sum(AMTACCTCR-AMTACCTDR) as \"Value\"," +
					"SUM(0 - QTY) as \"Qty\" " +
					"from " +
					"(" +
					" select attrIns.m_attribute_id,prod.m_product_category_id, attrIns.m_attributevalue_id,attr.name ATTRIBUTE_NAME,attrSet.name ATTRIBUTESET_NAME,attrSet.M_ATTRIBUTESET_ID,prod.m_product_id, attrVal.name ATTRIBUTE_VALUE, attrVal.description,fact.AMTACCTCR,fact.AMTACCTDR,fact.DATEACCT,fact.QTY" +
					" from M_PRODUCT prod,M_ATTRIBUTEINSTANCE attrIns ,M_attribute attr,M_attributeValue attrVal, M_ATTRIBUTESETINSTANCE attrSetIns, M_ATTRIBUTESET attrSet, FACT_ACCT fact" +
					" where prod.M_ATTRIBUTESETINSTANCE_id = attrIns.M_ATTRIBUTESETINSTANCE_id" +
					" and attr.m_attribute_id = attrIns.m_attribute_id" +
					" and attrIns.m_attributevalue_id = attrVal.m_attributevalue_id" +
					" and prod.M_ATTRIBUTESETINSTANCE_ID = attrSetIns.M_ATTRIBUTESETINSTANCE_ID" +
					" and attrSet.M_ATTRIBUTESET_ID = attrSetIns.M_ATTRIBUTESET_ID" +
					" and fact.ACCOUNT_ID = " +
					" (select C_ELEMENTVALUE_ID from C_ELEMENTVALUE where value = '"+account_id+"' and AD_CLIENT_ID = "+ad_client_id +") " +
					" and fact.M_PRODUCT_ID = prod.M_PRODUCT_ID" +
					" and fact.AD_CLIENT_ID = " + ad_client_id +
					" and attrSetIns.M_ATTRIBUTESET_ID = " + attributeSetId +
					" and attr.M_ATTRIBUTE_ID = " + attributeId +
					" and fact.DATEACCT between " +
					//" to_date('"+fromDate+"','DD-MON-YYYY HH24:MI:SS')" +
					fromDate +
					" and" +
					//" to_date('"+toDate+"','DD-MON-YYYY HH24:MI:SS')" +
					toDate +
					")" +
					"group by ATTRIBUTE_VALUE,to_char(DATEACCT,'DD-MON-YYYY') " +
					"order by to_char(DATEACCT,'DD-MON-YYYY') desc,ATTRIBUTE_VALUE asc";
		}
		
		return sql;	
		
	}//getTabularDataSetSQL
	
	public static String dataSetSQL(Properties ctx,int account_id,String fromDate,String toDate,String salesGroup) throws OperationException
	{
		int ad_client_id = Env.getAD_Client_ID(ctx);
		String sql = null;
		
		
		
		boolean isProfitMargin = (account_id == Constants.PROFIT_MARGIN);
        
        if(isProfitMargin)
        {
        	String innerSql = getTabularDataSetSQL(ctx, account_id, fromDate, toDate, salesGroup);
        	        	
        	if(salesGroup.equalsIgnoreCase(Constants.REVENUE_RECOGNITION))
        	{
        		sql = "select \"Revenue_Recognition\",sum(\"value\"),sum(\"Qty\") from ("+ innerSql +") as sub group by \"Revenue_Recognition\"";
        	}
        	else if(salesGroup.equalsIgnoreCase(Constants.ATTRIBUTESET))
        	{
           		sql = "select \"Attributeset\",sum(\"value\"),sum(\"Qty\") from ("+ innerSql +") group by \"Attributeset\"";
        	}
        	else if(salesGroup.equalsIgnoreCase(Constants.PRODUCT))
        	{
           		sql = "select \"Product\",sum(\"value\"),sum(\"Qty\") from ("+ innerSql +") group by \"Product\"";
        	}
        	else if(salesGroup.equalsIgnoreCase(Constants.CUSTOMER))
        	{
           		sql = "select \"Customer\",sum(\"value\"),sum(\"Qty\") from ("+ innerSql +") group by \"Customer\"";
        	}
        	else if(salesGroup.equalsIgnoreCase(Constants.GROUP1))
        	{
           		sql = "select \"Group1\",sum(\"value\"),sum(\"Qty\") from ("+ innerSql +") group by \"Group1\"";
        	}
        	else if(salesGroup.equalsIgnoreCase(Constants.GROUP2))
        	{
           		sql = "select \"Group2\",sum(\"value\"),sum(\"Qty\") from ("+ innerSql +") group by \"Group2\"";
        	}
        	else
        	{
        		String[] s = salesGroup.split("_");
    			
    			if(s.length!=2)
    			{
    				throw new OperationException("Cannot generate sql. Unknown salesGroup parameter:"+salesGroup);
    			}
    			
    			int attributeId = Integer.parseInt(s[1]);
    			
    			String attributeName = new MAttribute(ctx,attributeId,null).getName();
    			
           		sql = "select \""+ attributeName +"\",sum(\"value\"),sum(\"Qty\") from ("+ innerSql +") group by \""+ attributeName +"\"";
        	}        	
        	
        	return sql;
        }
		
		if(salesGroup.equalsIgnoreCase(Constants.REVENUE_RECOGNITION))
		{
			//sql = "select DECODE(rev.NAME, null, 'others', rev.Name) RevenueRecognition,SUM(fact.AMTACCTCR - fact.AMTACCTDR) as Revenue_Recognition,SUM(0-fact.QTY) as Qty " +
			sql = "select CASE WHEN rev.NAME = null THEN 'others' ELSE rev.Name END AS RevenueRecognition,SUM(fact.AMTACCTCR - fact.AMTACCTDR) as Revenue_Recognition,SUM(0-fact.QTY) as Qty " +
			"from FACT_ACCT fact, (M_PRODUCT prod right outer join C_REVENUERECOGNITION rev on rev.C_REVENUERECOGNITION_ID = prod.C_REVENUERECOGNITION_ID)  " +
			"where fact.ACCOUNT_ID = " +
			"(select C_ELEMENTVALUE_ID from C_ELEMENTVALUE where value = '"+account_id+"' and AD_CLIENT_ID = "+ad_client_id+") " +
			"and fact.M_PRODUCT_ID = prod.M_PRODUCT_ID " +
			//"and rev.C_REVENUERECOGNITION_ID(+) = prod.C_REVENUERECOGNITION_ID " +
			"and fact.DATEACCT between " +
			//"to_date('"+fromDate+"','DD-MON-YYYY HH24:MI:SS') " +
			fromDate +
			" and " +
			//"to_date('"+toDate+"','DD-MON-YYYY HH24:MI:SS') " +
			toDate+
			" and fact.AD_CLIENT_ID = "+ad_client_id +
			" group by rev.NAME";
		}
		else if(salesGroup.equalsIgnoreCase(Constants.ATTRIBUTESET))
		{
			sql = "select attrSet.name as attributeset,SUM(fact.AMTACCTCR - fact.AMTACCTDR) as Attribute_Set,SUM(0-fact.QTY) as Qty " +
			"from FACT_ACCT fact, (M_PRODUCT prod right outer join C_REVENUERECOGNITION rev on rev.C_REVENUERECOGNITION_ID = prod.C_REVENUERECOGNITION_ID),M_ATTRIBUTESETINSTANCE attrSetIns, M_ATTRIBUTESET attrSet " +
			"where fact.ACCOUNT_ID = " +
			"(select C_ELEMENTVALUE_ID from C_ELEMENTVALUE where value = '"+account_id+"' and AD_CLIENT_ID = "+ad_client_id +") " +
			"and fact.M_PRODUCT_ID = prod.M_PRODUCT_ID " +
			//"and rev.C_REVENUERECOGNITION_ID(+) = prod.C_REVENUERECOGNITION_ID " +
			"and prod.M_ATTRIBUTESETINSTANCE_ID = attrSetIns.M_ATTRIBUTESETINSTANCE_ID " +
			"and attrSet.M_ATTRIBUTESET_ID = attrSetIns.M_ATTRIBUTESET_ID " +
			"and fact.DATEACCT between " +
			//"to_date('"+fromDate+"','DD-MON-YYYY HH24:MI:SS') " +
			fromDate+
			" and " +
			//"to_date('"+toDate+"','DD-MON-YYYY HH24:MI:SS') " +
			toDate+
			" and fact.AD_CLIENT_ID = "+ad_client_id +
			" group by attrSet.name";
		}
		else if(salesGroup.equalsIgnoreCase(Constants.PRODUCT))
		{
			sql = "select PROD.NAME as \"Product\",SUM(FACT.AMTACCTCR - FACT.AMTACCTDR) as \"Value\",SUM(0 - FACT.QTY) as \"Qty\" " +
			"from FACT_ACCT FACT, M_PRODUCT PROD " +
			"where FACT.ACCOUNT_ID = " +
			"(select C_ELEMENTVALUE_ID from C_ELEMENTVALUE where value = '"+account_id+"' and AD_CLIENT_ID = "+ad_client_id +") " +
			"AND FACT.M_PRODUCT_ID = PROD.M_PRODUCT_ID " +
			"and fact.DATEACCT between " +
			//"to_date('"+fromDate+"','DD-MON-YYYY HH24:MI:SS') " +
			fromDate+
			" and " +
			//"to_date('"+toDate+"','DD-MON-YYYY HH24:MI:SS') " +
			toDate +
			" and fact.AD_CLIENT_ID = "+ad_client_id +
			" GROUP BY PROD.NAME";
		}
		else if(salesGroup.equalsIgnoreCase(Constants.GROUP1))
		{
			//sql = "select DECODE(PROD.GROUP1, null, 'Ungrouped', PROD.GROUP1) \"Group1\",SUM(FACT.AMTACCTCR - FACT.AMTACC//TDR) as \"Value\",SUM(0 - FACT.QTY) as \"Qty\" " +
			sql = "select CASE WHEN PROD.GROUP1 = null THEN 'Ungrouped' ELSE PROD.GROUP1 END AS \"Group1\",SUM(FACT.AMTACCTCR - FACT.AMTACCTDR) as \"Value\",SUM(0 - FACT.QTY) as \"Qty\" " +
			"from FACT_ACCT FACT, M_PRODUCT PROD " +
			"where FACT.ACCOUNT_ID = " +
			"(select C_ELEMENTVALUE_ID from C_ELEMENTVALUE where value = '"+account_id+"' and AD_CLIENT_ID = "+ad_client_id +") " +
			"AND FACT.M_PRODUCT_ID = PROD.M_PRODUCT_ID " +
			"and fact.DATEACCT between " +
			//"to_date('"+fromDate+"','DD-MON-YYYY HH24:MI:SS') " +
			fromDate+
			" and " +
			//"to_date('"+toDate+"','DD-MON-YYYY HH24:MI:SS') " +
			toDate+
			" and fact.AD_CLIENT_ID = "+ad_client_id +
			" GROUP BY PROD.GROUP1";
		}
		else if(salesGroup.equalsIgnoreCase(Constants.GROUP2))
		{
			//sql = "select DECODE(PROD.GROUP2, null, 'Ungrouped', PROD.GROUP2) \"Group2\",SUM(FACT.AMTACCTCR - FACT.AMTACCTDR) as \"Value\",SUM(0 - FACT.QTY) as \"Qty\" " +
			sql = "select CASE WHEN PROD.GROUP2 = null THEN 'Ungrouped' ELSE PROD.GROUP2 END AS \"Group2\",SUM(FACT.AMTACCTCR - FACT.AMTACCTDR) as \"Value\",SUM(0 - FACT.QTY) as \"Qty\" " +
			"from FACT_ACCT FACT, M_PRODUCT PROD " +
			"where FACT.ACCOUNT_ID = " +
			"(select C_ELEMENTVALUE_ID from C_ELEMENTVALUE where value = '"+account_id+"' and AD_CLIENT_ID = "+ad_client_id +") " +
			"AND FACT.M_PRODUCT_ID = PROD.M_PRODUCT_ID " +
			"and fact.DATEACCT between " +
			//"to_date('"+fromDate+"','DD-MON-YYYY HH24:MI:SS') " +
			fromDate+
			" and " +
			//"to_date('"+toDate+"','DD-MON-YYYY HH24:MI:SS') " +
			toDate+
			" and fact.AD_CLIENT_ID = "+ad_client_id +
			" GROUP BY PROD.GROUP2";
		}
		else if(salesGroup.equalsIgnoreCase(Constants.CUSTOMER))
		{
			//sql = "select BP.NAME || ' ' || BP.NAME2 as \"Customer\", " +
			sql = "Select BP.NAME as \"Customer\","+
			"SUM(FACT.AMTACCTCR - FACT.AMTACCTDR) as \"Value\", SUM(0 - FACT.QTY) as \"Qty\"  " +
			"from FACT_ACCT FACT, C_BPARTNER BP  " +
			"where FACT.ACCOUNT_ID = " +
			"(select C_ELEMENTVALUE_ID from C_ELEMENTVALUE where value = '"+account_id+"' and AD_CLIENT_ID = "+ad_client_id +") " +
			"AND FACT.C_BPARTNER_ID = BP.C_BPARTNER_ID  " +
			"and fact.DATEACCT between  " +
			//"to_date('"+fromDate+"','DD-MON-YYYY HH24:MI:SS') " +
			fromDate+
			" and  " +
			//"to_date('"+toDate+"','DD-MON-YYYY HH24:MI:SS') " +
			toDate+
			" and fact.AD_CLIENT_ID = "+ad_client_id +
			//" having SUM(0 - FACT.QTY) <> 0 " +
			"GROUP BY BP.NAME Order by BP.NAME asc";
			//"GROUP BY BP.NAME || ' ' || BP.NAME2 Order by BP.NAME || ' ' || BP.NAME2 asc";
		}
		else
		{
			String[] s = salesGroup.split("_");
			
			if(s.length!=2)
			{
				throw new OperationException("Cannot generate sql. Unknown salesGroup parameter:"+salesGroup);
			}
			
			int attributeSetId = Integer.parseInt(s[0]);
			int attributeId = Integer.parseInt(s[1]);
			
			String attributeName = new MAttribute(ctx,attributeId,null).getName();
			
			sql = "select ATTRIBUTE_VALUE, sum(AMTSOURCECR - AMTSOURCEDR) as \""+attributeName+ "\",SUM(0-QTY) as Qty from " +
					"(" +
					" select attrIns.m_attribute_id,prod.m_product_category_id, attrIns.m_attributevalue_id,attr.name ATTRIBUTE_NAME,attrSet.name ATTRIBUTESET_NAME,attrSet.M_ATTRIBUTESET_ID,prod.m_product_id, attrVal.name ATTRIBUTE_VALUE, attrVal.description,fact.AMTSOURCECR,fact.AMTSOURCEDR,fact.QTY" +
					" from M_PRODUCT prod,M_ATTRIBUTEINSTANCE attrIns ,M_attribute attr,M_attributeValue attrVal, M_ATTRIBUTESETINSTANCE attrSetIns, M_ATTRIBUTESET attrSet, FACT_ACCT fact" +
					" where prod.M_ATTRIBUTESETINSTANCE_id = attrIns.M_ATTRIBUTESETINSTANCE_id" +
					" and attr.m_attribute_id = attrIns.m_attribute_id" +
					" and attrIns.m_attributevalue_id = attrVal.m_attributevalue_id" +
					" and prod.M_ATTRIBUTESETINSTANCE_ID = attrSetIns.M_ATTRIBUTESETINSTANCE_ID" +
					" and attrSet.M_ATTRIBUTESET_ID = attrSetIns.M_ATTRIBUTESET_ID" +
					" and fact.M_PRODUCT_ID = prod.M_PRODUCT_ID" +
					" and fact.ACCOUNT_ID = " +
					" (select C_ELEMENTVALUE_ID from C_ELEMENTVALUE where value = '"+account_id+"' and AD_CLIENT_ID = "+ad_client_id +") " +
					" and fact.AD_CLIENT_ID = " + ad_client_id +
					" and attrSetIns.M_ATTRIBUTESET_ID = " + attributeSetId +
					" and attr.M_ATTRIBUTE_ID = " + attributeId +
					" and fact.DATEACCT between " +
					//" to_date('"+fromDate+"','DD-MON-YYYY HH24:MI:SS') " +
					fromDate+
					" and " +
					//" to_date('"+toDate+"','DD-MON-YYYY HH24:MI:SS') " +
					toDate+
					")" +
					"group by ATTRIBUTE_VALUE";
			
			
		}
		
		return sql;
	}//dataSetSQL
	
	public static String getTimeSeriesDataSetSQL(Properties ctx,int account_id,String fromDate,String toDate,String salesGroup) throws OperationException
	{
		
		int ad_client_id = Env.getAD_Client_ID(ctx);
		String sql = null;
		
		
		
		boolean isProfitMargin = (account_id == Constants.PROFIT_MARGIN);
		if(isProfitMargin)
		{
			return getTabularDataSetSQL(ctx, account_id, fromDate, toDate, salesGroup);
		}
		
		
		
		if(salesGroup.equalsIgnoreCase(Constants.REVENUE_RECOGNITION))
		{
			sql = "" +
			//"select DECODE(rev.NAME, null, 'others', rev.Name) RevenueRecognition," +	//1.Revenue Recognition
			"select CASE WHEN rev.NAME = null THEN 'others' ELSE rev.Name END AS RevenueRecognition," +	//1.Revenue Recognition
			//"to_char(fact.DATEACCT,'DD-MON-YYYY')," +				//2.Date
			"fact.DATEACCT,"+
			"SUM(fact.AMTACCTCR-fact.AMTACCTDR) as REVENUE," +			//3.Value
			"SUM(0-fact.QTY) " +									//4.Qty
			"from FACT_ACCT fact, (M_PRODUCT prod left outer join C_REVENUERECOGNITION rev on rev.C_REVENUERECOGNITION_ID = prod.C_REVENUERECOGNITION_ID ) " +
			"where fact.ACCOUNT_ID = " +
			"(select C_ELEMENTVALUE_ID from C_ELEMENTVALUE where value = '"+account_id+"' and AD_CLIENT_ID = "+ad_client_id+") " +
			"and fact.M_PRODUCT_ID = prod.M_PRODUCT_ID " +
			//"and rev.C_REVENUERECOGNITION_ID(+) = prod.C_REVENUERECOGNITION_ID " +
			"and fact.DATEACCT between " +
			//"to_date('"+fromDate+"','DD-MON-YYYY HH24:MI:SS') " +
			fromDate+
			" and " +
			//"to_date('"+toDate+"','DD-MON-YYYY HH24:MI:SS') " +
			toDate+
			" and fact.AD_CLIENT_ID = "+ad_client_id +
			//" group by rev.NAME,to_char(fact.DATEACCT,'DD-MON-YYYY')";
			" group by rev.NAME,fact.DATEACCT";
		}
		else if(salesGroup.equalsIgnoreCase(Constants.ATTRIBUTESET))
		{
			//sql = "select attrSet.name as attributeset,to_char(fact.DATEACCT,'DD-MON-YYYY'),SUM(fact.AMTACCTCR-fact.AMTACCTDR) as REVENUE,SUM(0-fact.QTY) " +
			sql = "select attrSet.name as attributeset,fact.DATEACCT,SUM(fact.AMTACCTCR-fact.AMTACCTDR) as REVENUE,SUM(0-fact.QTY) " +
			"from FACT_ACCT fact, (M_PRODUCT prod left outer join C_REVENUERECOGNITION rev on rev.C_REVENUERECOGNITION_ID = prod.C_REVENUERECOGNITION_ID ),M_ATTRIBUTESETINSTANCE attrSetIns, M_ATTRIBUTESET attrSet " +
			"where fact.ACCOUNT_ID = " +
			"(select C_ELEMENTVALUE_ID from C_ELEMENTVALUE where value = '"+account_id+"' and AD_CLIENT_ID = "+ad_client_id +") " +
			"and fact.M_PRODUCT_ID = prod.M_PRODUCT_ID " +
			//"and rev.C_REVENUERECOGNITION_ID(+) = prod.C_REVENUERECOGNITION_ID " +
			"and prod.M_ATTRIBUTESETINSTANCE_ID = attrSetIns.M_ATTRIBUTESETINSTANCE_ID " +
			"and attrSet.M_ATTRIBUTESET_ID = attrSetIns.M_ATTRIBUTESET_ID " +
			"and fact.DATEACCT between " +
			//"to_date('"+fromDate+"','DD-MON-YYYY HH24:MI:SS') " +
			fromDate+
			" and " +
			//"to_date('"+toDate+"','DD-MON-YYYY HH24:MI:SS') " +
			toDate+
			" and fact.AD_CLIENT_ID = "+ad_client_id +
			//" group by attrSet.name,to_char(fact.DATEACCT,'DD-MON-YYYY')";
			" group by attrSet.name,fact.DATEACCT";
		}
		else if(salesGroup.equalsIgnoreCase(Constants.PRODUCT))
		{
			sql = "" +
			" select PROD.NAME as \"Product\"," +						//1.Product
			//" to_char(fact.DATEACCT,'DD-MON-YYYY') as \"Date\"," +		//2.Date
			" fact.DATEACCT as \"Date\"," +		//2.Date
			" SUM(FACT.AMTACCTCR - FACT.AMTACCTDR) as \"Value\"," +	//3.Value
			" SUM(0 - FACT.QTY) as \"Qty\" " +							//4.Qty
			" from FACT_ACCT FACT, M_PRODUCT PROD " +
			" where FACT.ACCOUNT_ID = " +
			" (select C_ELEMENTVALUE_ID from C_ELEMENTVALUE where value = '"+account_id+"' and AD_CLIENT_ID = "+ad_client_id +") " +
			" AND FACT.M_PRODUCT_ID = PROD.M_PRODUCT_ID " +
			" and fact.DATEACCT between " +
			//" to_date('"+fromDate+"','DD-MON-YYYY HH24:MI:SS') " +
			fromDate+
			" and " +
			//" to_date('"+toDate+"','DD-MON-YYYY HH24:MI:SS') " +
			toDate+
			" and fact.AD_CLIENT_ID = "+ad_client_id +
			//" GROUP BY to_char(fact.DATEACCT,'DD-MON-YYYY'),PROD.NAME";	
			" GROUP BY fact.DATEACCT,PROD.NAME";
		}
		else if(salesGroup.equalsIgnoreCase(Constants.PRODUCT))
		{
			sql = "" +
			" select PROD.NAME as \"Product\"," +						//1.Product
			//" to_char(fact.DATEACCT,'DD-MON-YYYY') as \"Date\"," +		//2.Date
			" fact.DATEACCT as \"Date\"," +
			" SUM(FACT.AMTACCTCR - FACT.AMTACCTDR) as \"Value\"," +	//3.Value
			" SUM(0 - FACT.QTY) \"Qty\" " +							//4.Qty
			" from FACT_ACCT FACT, M_PRODUCT PROD " +
			" where FACT.ACCOUNT_ID = " +
			" (select C_ELEMENTVALUE_ID from C_ELEMENTVALUE where value = '"+account_id+"' and AD_CLIENT_ID = "+ad_client_id +") " +
			" AND FACT.M_PRODUCT_ID = PROD.M_PRODUCT_ID " +
			" and fact.DATEACCT between " +
			//" to_date('"+fromDate+"','DD-MON-YYYY HH24:MI:SS') " +
			fromDate+
			" and " +
			//" to_date('"+toDate+"','DD-MON-YYYY HH24:MI:SS') " +
			toDate+
			" and fact.AD_CLIENT_ID = "+ad_client_id +
			//" GROUP BY to_char(fact.DATEACCT,'DD-MON-YYYY'),PROD.NAME";
			" GROUP BY fact.DATEACCT,PROD.NAME";
		}
		else if(salesGroup.equalsIgnoreCase(Constants.GROUP1))
		{
			sql = "" +
			" select PROD.GROUP1 as \"Group1\"," +						//1.Product
			//" to_char(fact.DATEACCT,'DD-MON-YYYY') as \"Date\"," +		//2.Date
			" fact.DATEACCT as \"Date\"," +		//2.Date
			" SUM(FACT.AMTACCTCR - FACT.AMTACCTDR) as \"Value\"," +	//3.Value
			" SUM(0 - FACT.QTY) as \"Qty\" " +							//4.Qty
			" from FACT_ACCT FACT, M_PRODUCT PROD " +
			" where FACT.ACCOUNT_ID = " +
			" (select C_ELEMENTVALUE_ID from C_ELEMENTVALUE where value = '"+account_id+"' and AD_CLIENT_ID = "+ad_client_id +") " +
			" AND FACT.M_PRODUCT_ID = PROD.M_PRODUCT_ID " +
			" and fact.DATEACCT between " +
			//" to_date('"+fromDate+"','DD-MON-YYYY HH24:MI:SS') " +
			fromDate+
			" and " +
			//" to_date('"+toDate+"','DD-MON-YYYY HH24:MI:SS') " +
			toDate+
			" and fact.AD_CLIENT_ID = "+ad_client_id +
			//" GROUP BY to_char(fact.DATEACCT,'DD-MON-YYYY'),PROD.GROUP1";	
			" GROUP BY fact.DATEACCT,PROD.GROUP1";	
		}
		else if(salesGroup.equalsIgnoreCase(Constants.GROUP2))
		{
			sql = "" +
			" select PROD.GROUP2 as \"Group2\"," +						//1.Product
			//" to_char(fact.DATEACCT,'DD-MON-YYYY') as \"Date\"," +		//2.Date
			" DATEACCT as \"Date\"," +
			" SUM(FACT.AMTACCTCR - FACT.AMTACCTDR) as \"Value\"," +	//3.Value
			" SUM(0 - FACT.QTY) as \"Qty\" " +							//4.Qty
			" from FACT_ACCT FACT, M_PRODUCT PROD " +
			" where FACT.ACCOUNT_ID = " +
			" (select C_ELEMENTVALUE_ID from C_ELEMENTVALUE where value = '"+account_id+"' and AD_CLIENT_ID = "+ad_client_id +") " +
			" AND FACT.M_PRODUCT_ID = PROD.M_PRODUCT_ID " +
			" and fact.DATEACCT between " +
			//" to_date('"+fromDate+"','DD-MON-YYYY HH24:MI:SS') " +
			fromDate+
			" and " +
			//" to_date('"+toDate+"','DD-MON-YYYY HH24:MI:SS') " +
			toDate+
			" and fact.AD_CLIENT_ID = "+ad_client_id +
			//" GROUP BY to_char(fact.DATEACCT,'DD-MON-YYYY'),PROD.GROUP2";
			" GROUP BY fact.DATEACCT,PROD.GROUP2";
		}
		else if(salesGroup.equalsIgnoreCase(Constants.CUSTOMER))
		{
			sql = "" +
			//" select BP.NAME||' '||BP.NAME2 as \"Customer\"," +						//1.Customer
			"Select BP.NAME as \"Customer\","+
			//" to_char(fact.DATEACCT,'DD-MON-YYYY') as \"Date\"," +				//2.Date
			" fact.DATEACCT as \"Date\"," +				//2.Date
			" SUM(FACT.AMTACCTCR - FACT.AMTACCTDR) as \"Value\"," +	//3.Value
			" SUM(0 - FACT.QTY) as \"Qty\" " +												//4.Qty
			" from FACT_ACCT FACT, C_BPARTNER BP " +
			" where FACT.ACCOUNT_ID = " +
			" (select C_ELEMENTVALUE_ID from C_ELEMENTVALUE where value = '"+account_id+"' and AD_CLIENT_ID = "+ad_client_id +") " +
			" AND FACT.C_BPARTNER_ID = BP.C_BPARTNER_ID " +
			" and fact.DATEACCT between " +
			//" to_date('"+fromDate+"','DD-MON-YYYY HH24:MI:SS') " +
			fromDate+
			" and " +
			//" to_date('"+toDate+"','DD-MON-YYYY HH24:MI:SS') " +
			toDate+
			" and fact.AD_CLIENT_ID = "+ad_client_id +
			//" having SUM(0 - FACT.QTY) <> 0 " +
			//" GROUP BY to_char(fact.DATEACCT,'DD-MON-YYYY'),BP.NAME";
			" GROUP BY fact.DATEACCT,BP.NAME";
			//" GROUP BY to_char(fact.DATEACCT,'DD-MON-YYYY'),BP.NAME||' '||BP.NAME2";
			
		}
		else
		{
			String[] s = salesGroup.split("_");
			
			if(s.length!=2)
			{
				throw new OperationException("Cannot generate sql. Unknown salesGroup parameter:"+salesGroup);
			}
			
			int attributeSetId = Integer.parseInt(s[0]);
			int attributeId = Integer.parseInt(s[1]);
			
			//sql = "select ATTRIBUTE_VALUE,to_char(DATEACCT,'DD-MON-YYYY'),sum(AMTACCTCR-AMTACCTDR),SUM(0-QTY) from " +
			sql = "select ATTRIBUTE_VALUE,DATEACCT,sum(AMTACCTCR-AMTACCTDR),SUM(0-QTY) from " +
					"(" +
					" select attrIns.m_attribute_id,prod.m_product_category_id, attrIns.m_attributevalue_id,attr.name ATTRIBUTE_NAME,attrSet.name ATTRIBUTESET_NAME,attrSet.M_ATTRIBUTESET_ID,prod.m_product_id, attrVal.name ATTRIBUTE_VALUE, attrVal.description,fact.AMTACCTCR,fact.AMTACCTDR,fact.DATEACCT,fact.QTY" +
					" from M_PRODUCT prod,M_ATTRIBUTEINSTANCE attrIns ,M_attribute attr,M_attributeValue attrVal, M_ATTRIBUTESETINSTANCE attrSetIns, M_ATTRIBUTESET attrSet, FACT_ACCT fact" +
					" where prod.M_ATTRIBUTESETINSTANCE_id = attrIns.M_ATTRIBUTESETINSTANCE_id" +
					" and attr.m_attribute_id = attrIns.m_attribute_id" +
					" and attrIns.m_attributevalue_id = attrVal.m_attributevalue_id" +
					" and prod.M_ATTRIBUTESETINSTANCE_ID = attrSetIns.M_ATTRIBUTESETINSTANCE_ID" +
					" and attrSet.M_ATTRIBUTESET_ID = attrSetIns.M_ATTRIBUTESET_ID" +
					" and fact.ACCOUNT_ID = " +
					" (select C_ELEMENTVALUE_ID from C_ELEMENTVALUE where value = '"+account_id+"' and AD_CLIENT_ID = "+ad_client_id +") " +
					" and fact.M_PRODUCT_ID = prod.M_PRODUCT_ID" +
					" and fact.AD_CLIENT_ID = " + ad_client_id +
					" and attrSetIns.M_ATTRIBUTESET_ID = " + attributeSetId +
					" and attr.M_ATTRIBUTE_ID = " + attributeId +
					" and fact.DATEACCT between " +
					//" to_date('"+fromDate+"','DD-MON-YYYY HH24:MI:SS')" +
					fromDate+
					" and " +
					//" to_date('"+toDate+"','DD-MON-YYYY HH24:MI:SS')" +
					toDate+
					")" +
					//"group by ATTRIBUTE_VALUE,to_char(DATEACCT,'DD-MON-YYYY')";
					"group by ATTRIBUTE_VALUE,DATEACCT";
		}
		
		return sql;	
		
	}//getTimeSeriesDataSetSQL
		
}//SalesAnalysisReportManager
