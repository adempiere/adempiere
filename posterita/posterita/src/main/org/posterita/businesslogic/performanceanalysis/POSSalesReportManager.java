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

package org.posterita.businesslogic.performanceanalysis;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.TreeMap;

import org.compiere.model.MBPartner;
import org.compiere.model.MCashLine;
import org.compiere.model.MInvoice;
import org.compiere.model.MOrder;
import org.compiere.model.MPayment;
import org.compiere.model.MPriceList;
import org.compiere.model.MTransaction;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.posterita.Constants;
import org.posterita.beans.POSHistoryBean;
import org.posterita.beans.ReportBean;
import org.posterita.beans.SalesDetailsBean;
import org.posterita.beans.UDIPair;
import org.posterita.beans.WebOrderLineBean;
import org.posterita.businesslogic.OrderManager;
import org.posterita.businesslogic.POSManager;
import org.posterita.businesslogic.POSTerminalManager;
import org.posterita.businesslogic.administration.PriceListManager;
import org.posterita.businesslogic.administration.WarehouseManager;
import org.posterita.exceptions.OperationException;
import org.posterita.lib.UdiConstants;
import org.posterita.order.UDIOrderTypes;

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
	
	//--------------------------------------------------------------------------------------
	public static ArrayList<Object[]> getSalesOrPurchaseReportData(Properties ctx, ReportBean bean, String trxName) throws Exception
	{
		Timestamp startDate = null;
		Timestamp endDate = null; 	
		boolean isFullDetails = false;
		
		String isSalesRPT = bean.getIsSalesReport();
        Boolean isSalesReport = Boolean.valueOf(isSalesRPT);
        String reportType = "SALES";
        String orderType = "POS Order";
        String returnedOrderType = "Customer Returned Order";
        String isSOTrx = "Y";
        
        if(!(isSalesReport))
        {
            reportType = "PURCHASES";
            orderType = "POS Goods Receive Note";
            returnedOrderType = "POS Goods Returned Note";
            isSOTrx = "N";
        }
		
		BigDecimal totalPayment = Env.ZERO;
		
		if (Constants.FULL_DETAILS.equals(bean.getFullDetails()))
		{
			isFullDetails = true;
			
			if(!(isSalesReport))
			{
			    isFullDetails = false;
			}
		}

		if(bean.getDateRange().equalsIgnoreCase(Constants.FIXED_DATE_RANGE))
        {
        	if(bean.getTimePeriod() == null)
        	{
        		throw new OperationException("Invalid parameter: timeperiod is null");
        	}
        	
        	startDate = ReportDateManager.getStartDateForPeriod(bean.getTimePeriod());
        	endDate = ReportDateManager.getEndDateForPeriod(bean.getTimePeriod()); 	
        	
        }
        else
        {
        	startDate = ReportDateManager.getFromDateAsDate(bean);
        	endDate = ReportDateManager.getEndDateAsDate(bean);
        }  
		int ad_client_id = Env.getAD_Client_ID(ctx);
		
		SalesDetailsBean sbean = null;
		ArrayList<SalesDetailsBean> salesList = new ArrayList<SalesDetailsBean>();
		
		//Purchase Price List
		int purchasePriceListId = POSTerminalManager.getPOPriceListId(ctx);
        Integer ppriceListVersionId = PriceListManager.getPriceListVersionID(ctx, purchasePriceListId, null);
        MPriceList ppriceList = new MPriceList(ctx, purchasePriceListId, null);
        StringBuffer sqlOrder = new StringBuffer();
        if(isSalesReport)
        {
            sqlOrder.append(" issotrx = 'Y'");
        }
        else
        {
            sqlOrder.append(" issotrx = 'N'");
        }
        
        sqlOrder.append(" and c_pos_id=").append(Env.getContextAsInt(ctx, UdiConstants.TERMINAL_ID));
        sqlOrder.append(" and docstatus in ('CO','CL')"); 
        
        if(isSalesReport)
        {        
            sqlOrder.append(" and ordertype in ('POS Order','Credit Order','Customer Returned Order')");
        }
        else
        {
            sqlOrder.append(" and ordertype in ('").append(orderType).append("','").append(returnedOrderType).append("') ");
        }
        
        sqlOrder.append(" and ad_client_id = ").append(ad_client_id);
        sqlOrder.append(" and dateacct between ").append(DB.TO_DATE(startDate, false)); 
        sqlOrder.append(" and ").append(DB.TO_DATE(endDate, false));
        sqlOrder.append(" and ad_org_id =").append(Env.getAD_Org_ID(ctx));
        sqlOrder.append(" order by dateacct");
        
        // Get All Orders
        int[] orderId = MOrder.getAllIDs(MOrder.Table_Name, sqlOrder.toString(), null);
        
        if(orderId != null)
        {
            
            for (int c_order_id : orderId)
            {   
                sbean = new SalesDetailsBean();
                BigDecimal productCost = Env.ZERO;
                BigDecimal totalProductCosts = Env.ZERO;
                BigDecimal taxAmt = Env.ZERO;
                BigDecimal totalTaxAmt = Env.ZERO;
                BigDecimal salesExempt = Env.ZERO;
                BigDecimal totalSalesExempt = Env.ZERO;
                BigDecimal salesExcVat = Env.ZERO;
                BigDecimal totalSalesExcVat = Env.ZERO;
                BigDecimal creditNoteExcVat = Env.ZERO;
                BigDecimal totalCreditNoteExcVat = Env.ZERO;
                BigDecimal creditNoteExempt = Env.ZERO;
                BigDecimal totalCreditNoteExempt = Env.ZERO;
                BigDecimal purchasesExempt = Env.ZERO;
                BigDecimal totalPurchasesExempt = Env.ZERO;
                BigDecimal purchasesExcVat = Env.ZERO;
                BigDecimal totalPurchasesExcVat = Env.ZERO;
                BigDecimal debitNoteExcVat = Env.ZERO;
                BigDecimal totalDebitNoteExcVat = Env.ZERO;
                BigDecimal debitNoteExempt = Env.ZERO;
                BigDecimal totalDebitNoteExempt = Env.ZERO;
                
                MOrder order = new MOrder(ctx, c_order_id, null);
                sbean.setOrderId(c_order_id);
                sbean.setOrderType(order.getOrderType());
                sbean.setLinesTotals(order.getTotalLines());
                sbean.setBPartnerId(order.getC_BPartner_ID());
                sbean.setDate(order.getDateAcct());
                
                ArrayList<WebOrderLineBean> list = POSManager.populateOrderLines(ctx, order);
                
                for (WebOrderLineBean webOrderLineBean : list)
                {
                    sbean.setGrandTotals(webOrderLineBean.getGrandTotal());
                    sbean.setLinesTotals(webOrderLineBean.getTotalAmt());
                    
                    taxAmt = webOrderLineBean.getTaxAmt();
                    
                    if(taxAmt.compareTo(Env.ZERO)==0)
                    {
                        
                        if(order.getOrderType().equals(UDIOrderTypes.POS_ORDER.getOrderType()))
                        {
                            salesExempt = webOrderLineBean.getLineTotalAmt().subtract(taxAmt);
                            salesExcVat = Env.ZERO;
                        }
                        
                        if(order.getOrderType().equals(UDIOrderTypes.CUSTOMER_RETURN_ORDER.getOrderType()))
                        {
                            salesExempt = webOrderLineBean.getLineTotalAmt().subtract(taxAmt).negate();
                            taxAmt = taxAmt.negate();
                            salesExcVat = Env.ZERO;
                        }
                        
                        if(order.getOrderType().equals(UDIOrderTypes.CREDIT_ORDER.getOrderType()))
                        {
                            creditNoteExempt = webOrderLineBean.getLineTotalAmt().subtract(taxAmt);
                            creditNoteExcVat = Env.ZERO;
                        }
                        
                        if(order.getOrderType().equals(UDIOrderTypes.POS_GOODS_RECEIVE_NOTE.getOrderType()))
                        {
                            purchasesExempt = webOrderLineBean.getLineTotalAmt().subtract(taxAmt);
                            purchasesExcVat = Env.ZERO;
                        }
                        
                        if(order.getOrderType().equals(UDIOrderTypes.POS_GOODS_RETURN_NOTE.getOrderType()))
                        {
                            debitNoteExempt = webOrderLineBean.getLineTotalAmt().subtract(taxAmt);
                            debitNoteExcVat = Env.ZERO;
                        }
                        
                    }
                    else
                    {
                        if(order.getOrderType().equals(UDIOrderTypes.POS_ORDER.getOrderType()))
                        {
                            salesExempt = Env.ZERO;
                            salesExcVat = webOrderLineBean.getLineTotalAmt().subtract(taxAmt);
                        }
                        
                        if(order.getOrderType().equals(UDIOrderTypes.CUSTOMER_RETURN_ORDER.getOrderType()))
                        {
                            salesExempt = Env.ZERO;
                            salesExcVat = webOrderLineBean.getLineTotalAmt().subtract(taxAmt).negate();
                            taxAmt = taxAmt.negate();
                        }
                        
                        if(order.getOrderType().equals(UDIOrderTypes.CREDIT_ORDER.getOrderType()))
                        {
                            creditNoteExempt = Env.ZERO;
                            creditNoteExcVat = webOrderLineBean.getLineTotalAmt().subtract(taxAmt);
                        }
                        
                        if(order.getOrderType().equals(UDIOrderTypes.POS_GOODS_RECEIVE_NOTE.getOrderType()))
                        {
                            purchasesExcVat = webOrderLineBean.getLineTotalAmt().subtract(taxAmt);
                            purchasesExempt = Env.ZERO;
                        }
                        
                        if(order.getOrderType().equals(UDIOrderTypes.POS_GOODS_RETURN_NOTE.getOrderType()))
                        {
                            debitNoteExcVat = webOrderLineBean.getLineTotalAmt().subtract(taxAmt);
                            debitNoteExempt = Env.ZERO;
                        }
                    }
                    
                    if(order.getOrderType().equals(UDIOrderTypes.CUSTOMER_RETURN_ORDER.getOrderType()))
                    {
                        productCost = webOrderLineBean.getPurchasePriceList().negate().multiply(webOrderLineBean.getQtyOrdered());
                    }
                    else
                    {
                        productCost = webOrderLineBean.getPurchasePriceList().multiply(webOrderLineBean.getQtyOrdered());
                    }
                    
                    totalTaxAmt = totalTaxAmt.add(taxAmt);
                    totalProductCosts = totalProductCosts.add(productCost);
                    totalSalesExempt = totalSalesExempt.add(salesExempt);
                    totalSalesExcVat = totalSalesExcVat.add(salesExcVat);
                    totalCreditNoteExcVat = totalCreditNoteExcVat.add(creditNoteExcVat);
                    totalCreditNoteExempt = totalCreditNoteExempt.add(creditNoteExempt);
                    totalPurchasesExcVat = totalPurchasesExcVat.add(purchasesExcVat);
                    totalPurchasesExempt = totalPurchasesExempt.add(purchasesExempt);
                }
                
                sbean.setTotalTaxAmount(totalTaxAmt);
                sbean.setTotalCosts(totalProductCosts);
                sbean.setSalesExempt(totalSalesExempt);
                sbean.setSalesExcVat(totalSalesExcVat);
                sbean.setCreditNoteExcVat(totalCreditNoteExcVat);
                sbean.setCreditNoteExempt(totalCreditNoteExempt);
                sbean.setPurchasesExcVat(totalPurchasesExcVat);
                sbean.setPurchasesExempt(totalPurchasesExempt);
                sbean.setDebitNoteExcVat(totalDebitNoteExcVat);
                sbean.setDebitNoteExempt(totalDebitNoteExempt);
                
                sbean.setCashAmount(Env.ZERO);
                sbean.setCardAmount(Env.ZERO);
                sbean.setChequeAmount(Env.ZERO);
                
                if (order.getPaymentRule().equals(MOrder.PAYMENTRULE_Cash) || order.getPaymentRule().equals(MOrder.PAYMENTRULE_Mixed))           
                {
                    MInvoice invoice = MInvoice.get(ctx, order.getC_Invoice_ID());
                    MCashLine cashLine = new MCashLine(ctx, invoice.getC_CashLine_ID(), null);
                    if (cashLine.getC_CashLine_ID() != 0)
                    {                
                        sbean.setCashAmount(cashLine.getAmount());
                    }
                }
                
                if(order.getPaymentRule().equals(MOrder.PAYMENTRULE_Check) || order.getPaymentRule().equals(MOrder.PAYMENTRULE_CreditCard) || order.getPaymentRule().equals(MOrder.PAYMENTRULE_Mixed))
                {
                    int[] payIds = MPayment.getAllIDs(MPayment.Table_Name, "C_Invoice_ID = " + order.getC_Invoice_ID(), null);
                    for (int payId : payIds)
                    {
                        MPayment payment = new MPayment(ctx, payId, null);
                        
                        if (payment.get_ID() != 0)
                        {
                            if(payment.getTenderType().equals(MPayment.TENDERTYPE_CreditCard))
                            {
                                sbean.setCardAmount(payment.getPayAmt());
                            }
                            
                            if(payment.getTenderType().equals(MPayment.TENDERTYPE_Check))
                            {
                                sbean.setChequeAmount(payment.getPayAmt());
                            }
                        }
                    }
                }
                
                totalPayment = sbean.getCardAmount().add(sbean.getCashAmount()).add(sbean.getChequeAmount());
                sbean.setTotal(totalPayment);
                
                salesList.add(sbean);
                
            }
            
            TreeMap<Timestamp, SalesDetailsBean> map = new TreeMap<Timestamp, SalesDetailsBean>();
            for(SalesDetailsBean salesBean : salesList)
            {
                Timestamp date = salesBean.getDate();
                SalesDetailsBean myDetailsBean = map.get(date);
                if(myDetailsBean == null)
                {
                    myDetailsBean = salesBean;
                }
                else
                {
                  //merge myDetailsBean and salesBean
                    BigDecimal salesExcVat = myDetailsBean.getSalesExcVat().add(salesBean.getSalesExcVat());
                    BigDecimal salesExempt = myDetailsBean.getSalesExempt().add(salesBean.getSalesExempt());
                    BigDecimal totalCosts = myDetailsBean.getTotalCosts().add(salesBean.getTotalCosts());
                    BigDecimal total = myDetailsBean.getTotal().add(salesBean.getTotal());
                    BigDecimal totalTaxAmount = myDetailsBean.getTotalTaxAmount().add(salesBean.getTotalTaxAmount());
                    BigDecimal creditNoteExcVat = myDetailsBean.getCreditNoteExcVat().add(salesBean.getCreditNoteExcVat());
                    BigDecimal creditNoteExempt = myDetailsBean.getCreditNoteExempt().add(salesBean.getCreditNoteExempt());
                    BigDecimal purchasesExcVat = myDetailsBean.getPurchasesExcVat().add(salesBean.getPurchasesExcVat());
                    BigDecimal purchasesExempt = myDetailsBean.getPurchasesExempt().add(salesBean.getPurchasesExempt());
                    BigDecimal debitNoteExcVat = myDetailsBean.getDebitNoteExcVat().add(salesBean.getDebitNoteExempt());
                    BigDecimal debitNoteExempt = myDetailsBean.getDebitNoteExempt().add(salesBean.getDebitNoteExempt());
                    
                    myDetailsBean.setDate(date);
                    myDetailsBean.setSalesExcVat(salesExcVat);
                    myDetailsBean.setSalesExempt(salesExempt);
                    myDetailsBean.setTotalCosts(totalCosts);
                    myDetailsBean.setTotal(total);
                    myDetailsBean.setTotalTaxAmount(totalTaxAmount);
                    myDetailsBean.setCreditNoteExcVat(creditNoteExcVat);
                    myDetailsBean.setCreditNoteExempt(creditNoteExempt);
                    myDetailsBean.setPurchasesExcVat(purchasesExcVat);
                    myDetailsBean.setPurchasesExempt(purchasesExempt);
                    myDetailsBean.setDebitNoteExcVat(debitNoteExcVat);
                    myDetailsBean.setDebitNoteExempt(debitNoteExempt);
                }
                
                map.put(date, myDetailsBean);                       
                
            }
            
            salesList = new ArrayList<SalesDetailsBean>();
            salesList.addAll(map.values());
        }
        
        
        
		ArrayList<Object[]> reportData = new ArrayList<Object[]>();
		
		String note = "";
		if(isSalesReport)
		{
		    note = "CREDIT NOTE ";
		}
		else
		{
		    note = "DEBIT NOTE ";
		}
		
		Object[] headers = {
				"DATE",
				reportType + " EXCL VAT",
				note + "EXCL VAT",
				"EXEMPT " + reportType,
				"EXEMPT " + note,
				"VAT",
				"NET TOTAL " + reportType
		};
		Object[] totals = {
				"TOTAL",
				Env.ZERO,
				Env.ZERO,
				Env.ZERO,
				Env.ZERO,
				Env.ZERO,
				Env.ZERO
		};
		
		if (isFullDetails)
		{
			headers = new Object[]{
					"DATE",
					reportType + " EXCL VAT",
					note + "EXCL VAT",
					"EXEMPT " + reportType,
					"EXEMPT " + note,
					"VAT",
					"NET TOTAL " + reportType,
					"COST", 
					"PROFIT",
					"GP %"
					};
			totals = new Object[]{
					"TOTAL",
					Env.ZERO,
					Env.ZERO,
					Env.ZERO,
					Env.ZERO,
					Env.ZERO,
					Env.ZERO,
					Env.ZERO,
					Env.ZERO,
					Env.ZERO
			};
			
		}
		
		// add header
		reportData.add(headers);
		
		
		BigDecimal grossProfit = Env.ZERO;
			
		for (SalesDetailsBean sdBean : salesList)
        {
            String date = sdBean.getDate().toString();
			BigDecimal salesOrPurchasesEV = Env.ZERO;
			
			if(isSalesReport)
            {			    
			    salesOrPurchasesEV = sdBean.getSalesExcVat();
            }
			else
			{
			    salesOrPurchasesEV = sdBean.getPurchasesExcVat();
			}
			BigDecimal creditOrDebitNEV = Env.ZERO;
			
			if(isSalesReport)
			{
			    creditOrDebitNEV = sdBean.getCreditNoteExcVat();
			}
			else
			{
			    creditOrDebitNEV = sdBean.getDebitNoteExcVat();
			}
			
			BigDecimal exemptSalesOrPurchases = Env.ZERO;
			
			if(isSalesReport)
			{
			    exemptSalesOrPurchases = sdBean.getSalesExempt();
			}
			else
			{
			    exemptSalesOrPurchases = sdBean.getPurchasesExempt();
			}
			
			BigDecimal exemptCNOrDN = Env.ZERO;
			if(isSalesReport)
			{
			    exemptCNOrDN = sdBean.getCreditNoteExempt();
			}
			else
			{
			    exemptCNOrDN = sdBean.getDebitNoteExempt();
			}
			
			BigDecimal vat = sdBean.getTotalTaxAmount();
			BigDecimal total = salesOrPurchasesEV.add(vat).add(exemptSalesOrPurchases).add(creditOrDebitNEV).add(exemptCNOrDN);
			BigDecimal cost = sdBean.getTotalCosts();
			BigDecimal payAmt = sdBean.getTotal();
			cost = cost.setScale(total.scale(), RoundingMode.HALF_DOWN);
			BigDecimal profit = total.subtract(cost);
			
			if (total.doubleValue() != 0.0f)
			{
				double prof = profit.doubleValue();
				double tots = total.doubleValue();
				grossProfit = new BigDecimal(prof/tots*100.0);
				grossProfit = grossProfit.setScale(2, RoundingMode.HALF_UP);
			}
			Object[] data = null;
			
			if (isFullDetails)
			{
				data = new Object[]{
						date,
						salesOrPurchasesEV,
						creditOrDebitNEV,
						exemptSalesOrPurchases,
						exemptCNOrDN,
						vat,
						total,
						cost,
						profit,
						grossProfit
				};					
			}
			else
			{
				data = new Object[]{
						date,
						salesOrPurchasesEV,
						creditOrDebitNEV,
						exemptSalesOrPurchases,
						exemptCNOrDN,
						vat,
						total							
				};					
			}			        
			
			reportData.add(data);
			
			//update totals				
			totals[1] = ((BigDecimal)totals[1]).add(salesOrPurchasesEV);
			totals[2] = ((BigDecimal)totals[2]).add(creditOrDebitNEV);
			totals[3] = ((BigDecimal)totals[3]).add(exemptSalesOrPurchases);
			totals[4] = ((BigDecimal)totals[4]).add(exemptCNOrDN);
			totals[5] = ((BigDecimal)totals[5]).add(vat);
			totals[6] = ((BigDecimal)totals[6]).add(total);
			
			if (isFullDetails)
			{
				totals[7] = ((BigDecimal)totals[7]).add(cost);
				totals[8] = ((BigDecimal)totals[8]).add(profit);
				totals[9] = ((BigDecimal)totals[9]).add(grossProfit);
			}
		}
		if (isFullDetails)
		{
			BigDecimal salesTotal = ((BigDecimal)totals[1]).add((BigDecimal)totals[2]).add((BigDecimal)totals[3]).add((BigDecimal)totals[4]).add((BigDecimal)totals[5]);
			
			if (salesTotal.doubleValue() != 0f)
			{
				double prof = ((BigDecimal)totals[8]).doubleValue();
				double tots = ((BigDecimal)salesTotal).doubleValue();
				grossProfit = new BigDecimal(prof/tots*100.0);
				totals[9] = grossProfit.setScale(2, RoundingMode.HALF_UP);
			}
			}
			
			
		reportData.add(totals);
		
		return reportData;
    	
	}
	
	public static ArrayList<POSHistoryBean> getDetailedSalesReport(Properties ctx, String salesType, boolean isSalesReport, Timestamp date1, Timestamp date2, String trxName) throws OperationException 
	{
		
		
		String sql = "select ord.C_ORDER_ID," + // 1
				" ord.dateordered," + // 2
				" ord.grandtotal," + // 3
				" ord.DOCUMENTNO," + // 4
				" (name || ' ' || COALESCE(name2,trim(' '))) as name, " + // 5
				" ord.docStatus, " + // 6
				" ord.paymentrule," + // 7
				" bp.C_BPARTNER_ID," + // 8
				" bp.isCustomer," + // 9
				" ord.ORDERTYPE " + // 10
				" from c_order ord,C_BPARTNER bp"
				+ " where ord.C_BPARTNER_ID=bp.C_BPARTNER_ID"
				+ " and ord.docStatus in ('CL', 'CO')"
				+ " and ord.AD_ORG_ID=" + Env.getAD_Org_ID(ctx)
				+ " and ord.AD_CLIENT_ID=" + Env.getAD_Client_ID(ctx)
				+ " and ord.isActive='Y'";	
		
		if("1".equals(salesType))
		{
			if(isSalesReport)
			{		    
			    sql = sql + " and ord.totallines <> ord.grandtotal  and ord.ordertype in ('POS Order','Credit Order')";
			}
			else
			{
			    sql = sql + " and ord.totallines <> ord.grandtotal  and ord.ordertype in ('POS Goods Receive Note')";
			}
		}
		
		if("2".equals(salesType))
		{
		    if(isSalesReport)
		    {
		        sql = sql + " and ord.totallines <> ord.grandtotal  and ord.ordertype in ('Customer Returned Order')";
		    }
		    else
		    {
		        sql = sql + " and ord.totallines <> ord.grandtotal  and ord.ordertype in ('POS Goods Returned Note')";
		    }
		}
		
		if("3".equals(salesType))
		{
		    if(isSalesReport)
            {           
                sql = sql + " and ord.totallines <> ord.grandtotal  and ord.ordertype in ('POS Order','Credit Order')";
            }
            else
            {
                sql = sql + " and ord.totallines <> ord.grandtotal  and ord.ordertype in ('POS Goods Receive Note')";
            }
		}
		
		if("4".equals(salesType))
		{
		    if(isSalesReport)
            {
                sql = sql + " and ord.totallines <> ord.grandtotal  and ord.ordertype in ('Customer Returned Order')";
            }
            else
            {
                sql = sql + " and ord.totallines <> ord.grandtotal  and ord.ordertype in ('POS Goods Returned Note')";
            }
		}
		
		sql = sql + " and ord.dateordered between "+ DB.TO_DATE(date1, false)+ " and "+ DB.TO_DATE(date2, false);

		sql = sql + " order by ord.created desc";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		POSHistoryBean bean = null;
		ArrayList<POSHistoryBean> list = new ArrayList<POSHistoryBean>();

		try 
		{
			pstmt = DB.prepareStatement(sql, trxName);
			rs = pstmt.executeQuery();

			while (rs.next()) 
			{
				int c_order_id = rs.getInt(1);
				bean = new POSHistoryBean();
				bean.setOrderId(c_order_id);
				bean.setDateAcct(rs.getTimestamp(2));
				bean.setOrderGrandTotal(rs.getBigDecimal(3));
				bean.setDocumentNo(rs.getString(4));
				bean.setPartnerName(rs.getString(5));
				bean.setDocStatus(rs.getString(6));
				bean.setPaymentRule(rs.getString(7));
				bean.setBpartnerId(rs.getInt(8));
				bean.setIsCustomer("Y".equals(rs.getString(9)) ? true : false);
				bean.setOrderType(rs.getString(10));
				
				MOrder order = new MOrder(ctx, c_order_id, trxName);
				MInvoice invoice = MInvoice.get(ctx, order.getC_Invoice_ID());
				BigDecimal amountPaid = Env.ZERO;
				if (invoice.getC_CashLine_ID() != 0)
				{
					MCashLine cashLine = new MCashLine(ctx, invoice.getC_CashLine_ID(), trxName);
					amountPaid = cashLine.getAmount();
				}
				int[] payIds = MPayment.getAllIDs(MPayment.Table_Name, "C_Invoice_ID = " + invoice.get_ID() + 
							" AND AD_Client_ID = " + Env.getAD_Client_ID(ctx), trxName);
				if (payIds != null && payIds.length != 0)
				{
					for (int payId : payIds)
					{
						MPayment payment = new MPayment(ctx, payId, trxName);
						amountPaid = amountPaid.add(payment.getPayAmt());
					}
				}
				amountPaid = amountPaid.setScale(2, RoundingMode.HALF_DOWN);
				bean.setAmountPaid(amountPaid);
				BigDecimal grossProfit = Env.ZERO.setScale(2, RoundingMode.HALF_DOWN);
				
				if ("1".equals(salesType) || "3".equals(salesType))
				{
					grossProfit = getGrossProfit(ctx, c_order_id, date1, date2, trxName);					
				}				
				
				if (grossProfit == null)
				{
					 grossProfit = Env.ZERO;
				}
				
				bean.setGrossProfit(grossProfit);
				list.add(bean);

			}
			
		} catch (SQLException e) {
			throw new OperationException(
					"Could not retrieve order history with sql: " + sql, e);
		}
		finally 
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return list;

	}

	private static BigDecimal getGrossProfit(Properties ctx, int c_order_id, Timestamp date1, Timestamp date2, String trxName) throws OperationException
	{
		
		int poPriceListId = POSTerminalManager.getPOPriceListId(ctx);
		int poPriceListVersionId = PriceListManager.getPriceListVersionID(ctx, poPriceListId, trxName);
		
		String sql = "select (l.qtyordered*(case when pp.pricelist is null then 0 else pp.pricelist end)) as cost, o.totallines as sales from" +
				" c_order o inner join (c_orderline l left outer join m_productprice pp on" +
				" (l.m_product_id = pp.m_product_id and pp.m_pricelist_version_id = " + poPriceListVersionId + ")) on" +
				" o.c_order_id = l.c_order_id" +
				" where o.issotrx = 'Y' " +
				" and o.c_order_id = " + c_order_id +
				" and o.docstatus in ('CO','CL')" +
				" and o.C_Doctype_ID IN ('"+ OrderManager.getDocTypeFromOrderType(ctx, UDIOrderTypes.POS_ORDER.getOrderType(), true)+"')" +
				" and o.ad_client_id = " + Env.getAD_Client_ID(ctx) +
				" and o.ad_org_id = "+ Env.getAD_Org_ID(ctx) + 
				" and o.dateacct between " + DB.TO_DATE(date1, true)+
				" and "+ DB.TO_DATE(date2, true);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BigDecimal grossProfit = Env.ZERO;
		BigDecimal cost = Env.ZERO;
		BigDecimal sales = Env.ZERO;
		
		try
		{
			pstmt = DB.prepareStatement(sql, trxName);
			rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				cost = cost.add(rs.getBigDecimal("cost"));
				sales = rs.getBigDecimal("sales");
			}
			
			
            
            if (sales.compareTo(Env.ZERO)!=0)
            {
                BigDecimal profit = sales.subtract(cost);
                
                grossProfit = new BigDecimal(profit.doubleValue() / sales.doubleValue() * 100.0f);
            }
            else
            {
                grossProfit = BigDecimal.valueOf(0.0f);
            }
		}
		catch (SQLException e)
		{
			throw new OperationException("Could not retrieve order history", e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}				
		
		return grossProfit;
	}
	
	
	
	public static HashMap<Integer, BigDecimal> getSalesByOrg(Properties ctx, Integer productId, Integer orgId, Timestamp fromDate, Timestamp toDate, String trxName) throws OperationException 
	{
		
		if (productId == null)
		{
			throw new OperationException("product Id is null, cannot search for sales by org for that product");
		}
				
		if (orgId == null)
		{
			throw new OperationException("org Id is null, cannot search for sales by org for that org");
		}
		if (fromDate == null || toDate == null)
		{
			throw new OperationException("date cannot be null");
		}
			
		String locatorIds = WarehouseManager.getLocatorIds(ctx, orgId);
		
		StringBuffer sql = new StringBuffer("SELECT SUM(trx.movementQty), loc.AD_Org_ID")
		.append(" FROM M_Transaction trx INNER JOIN M_Locator loc ON trx.M_Locator_ID = loc.M_Locator_ID")
		.append(" WHERE trx.M_Product_ID = ? AND trx.M_Locator_ID IN (" + locatorIds + ")")
		.append(" AND trx.movementDate BETWEEN " + DB.TO_DATE(fromDate, true) +" AND " + DB.TO_DATE(toDate, true))
		.append(" AND trx.movementType = ?")
		.append(" GROUP BY loc.AD_Org_ID");
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HashMap<Integer, BigDecimal> orgSales = new HashMap<Integer, BigDecimal>();
		
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, productId);
			pstmt.setString(2, MTransaction.MOVEMENTTYPE_CustomerShipment);
			rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				BigDecimal qty = rs.getBigDecimal(1);
				Integer org_Id = rs.getInt(2);
				
				orgSales.put(org_Id, qty);
						
			}
		}
		catch (SQLException e)
		{
			throw new OperationException("could not retrieve sales by org from sql" + sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
				
		return orgSales;
		
	}

	public static ArrayList<POSHistoryBean> getOrderDetailsPerProduct(Properties ctx,boolean isSales,
			String productId, String orgIds, Timestamp date1, Timestamp date2) throws OperationException 
	{
		StringBuffer payAmtSql = new StringBuffer();
	    payAmtSql.append(" (SELECT SUM(p.Amount) FROM");
	    payAmtSql.append(" ((SELECT payAmt as Amount FROM C_Payment p")
	    		 .append(" WHERE p.C_Invoice_ID=inv.C_Invoice_ID AND p.DocStatus IN ('CO', 'CL'))");
	    payAmtSql.append(" UNION");
	    payAmtSql.append(" (SELECT Amount FROM C_CashLine cl")
	             .append(" WHERE (cl.c_Invoice_ID=inv.C_Invoice_ID))) as p)");
		
		StringBuffer sql = new StringBuffer("select ord.C_ORDER_ID,")
									.append("inv.documentNo, ")
									.append("(CASE WHEN inv.C_Invoice_ID IS NULL THEN 0 ELSE ")
									.append(payAmtSql.toString()).append(" END) PayAmt")
									.append(" from (c_order ord inner join c_orderline ordl ")
									.append(" on ord.c_order_id = ordl.c_order_id) inner join c_invoice inv on")
									.append(" ord.c_order_id = inv.c_order_id")
									.append(" where ord.docStatus in ('CL', 'CO')");
		if (isSales)
		{
			sql = sql.append(" and ord.orderType = '"+UDIOrderTypes.POS_ORDER.getOrderType()+"'");
		}
		else
		{
			sql = sql.append(" and ord.orderType = '"+ UDIOrderTypes.POS_GOODS_RECEIVE_NOTE.getOrderType()+"'");
		}
		
		sql = sql.append(" and ord.AD_ORG_ID in (" + orgIds + ") and ord.AD_CLIENT_ID=" + Env.getAD_Client_ID(ctx))
				.append( " and ord.isActive='Y' and ordl.m_product_id = " + productId)	
				.append(" and ord.dateordered between "+DB.TO_DATE(date1, true)+" and " + DB.TO_DATE(date2, true))
				.append(" order by ord.created desc");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		POSHistoryBean bean = null;
		ArrayList<POSHistoryBean> list = new ArrayList<POSHistoryBean>();

		try 
		{
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();

			while (rs.next()) 
			{
				/*ReportBean bean = new ReportBean();*/
				bean = new POSHistoryBean();
				int orderId = rs.getInt(1);
				String invoiceDocumentNo = rs.getString(2);
				BigDecimal amountPaid = rs.getBigDecimal(3);
								
				MOrder order = new MOrder(ctx, orderId, null);
				MBPartner partner = MBPartner.get(ctx, order.getC_BPartner_ID());				
				bean.setOrderId(orderId);
				bean.setDateAcct(order.getDateAcct());
				bean.setDateOrdered(order.getDateOrdered());
				bean.setOrderGrandTotal(order.getGrandTotal());
				bean.setDocumentNo(order.getDocumentNo());
				bean.setDocStatus(order.getDocStatus());
				bean.setPaymentRule(order.getPaymentRule());
				bean.setOrderType(order.getOrderType());
				bean.setOrgId(order.getAD_Org_ID());
				bean.setInvoiceDocumentNo(invoiceDocumentNo);
				bean.setPartnerName(partner.getName());
				bean.setBpartnerId(partner.getC_BPartner_ID());
				bean.setIsCustomer(partner.isCustomer());
				bean.setAmountPaid(amountPaid);
				BigDecimal grossProfit = Env.ZERO.setScale(2, RoundingMode.HALF_UP);
				
				grossProfit = getGrossProfit(ctx, orderId, date1, date2, null);					
				
				if (grossProfit == null)
				{
					 grossProfit = Env.ZERO;
				}
				
				bean.setGrossProfit(grossProfit);
				list.add(bean);

			}
			
		} catch (SQLException e) {
			throw new OperationException(
					"Could not retrieve sales details per product", e);
		}
		finally 
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return list;
	}
		
}
