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
 * Created on Aug 8, 2006 by alok
 */


package org.posterita.businesslogic;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Properties;

import org.compiere.model.MBPartner;
import org.compiere.model.MConversionRate;
import org.compiere.model.MDocType;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPriceList;
import org.compiere.model.MProduct;
import org.compiere.model.MTax;
import org.compiere.print.ReportEngine;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;

import org.posterita.beans.CreditCheckBean;
import org.posterita.beans.OrderLineBean;
import org.posterita.exceptions.BPartnerNoLocationException;
import org.posterita.exceptions.BPartnerOverCreditLimitException;
import org.posterita.exceptions.OperationException;
import org.posterita.model.UDIMOrder;
import org.posterita.model.UDIMOrderLine;
import org.posterita.order.UDIOrderTypes;
import org.posterita.core.utils.FormatBigDecimal;


public class OrderManager 
{
    
    public static MOrder createOrder(Properties ctx, int bPartnerId,boolean isSotrx,int priceListId, String orderType, int warehouseId,String paymentRule,String trxName) throws OperationException
    {
        MOrder order = new MOrder(ctx, 0, trxName);
        MBPartner bPartner = new MBPartner(ctx, bPartnerId, trxName);
        
        if(bPartner.getPrimaryC_BPartner_Location_ID() == 0)
        	throw new BPartnerNoLocationException("Business partner has no location");
        order.setAD_Org_ID(Env.getAD_Org_ID(ctx));
        order.setBPartner(bPartner);
        order.setC_BPartner_ID(bPartner.get_ID());
        order.setC_BPartner_Location_ID(bPartner.getPrimaryC_BPartner_Location_ID());
        order.setIsSOTrx(isSotrx);
        order.setM_PriceList_ID(priceListId);
        
        MPriceList priceList = new MPriceList(ctx,priceListId,null);
        order.setC_Currency_ID(priceList.getC_Currency_ID());
        order.setDocAction(DocumentEngine.ACTION_Complete);
        order.setSalesRep_ID(Env.getAD_User_ID(ctx));
        
        if(orderType.equalsIgnoreCase(UDIOrderTypes.CREDIT_ORDER_NO_SHIPMENT.getOrderType()))
        {
            /**because for credit orders, compiere generates shipment 
             * automatically, so we want a credit order with standard doc type*/
            
            order.setC_DocTypeTarget_ID(getDocTypeFromOrderType(ctx,UDIOrderTypes.POS_ORDER.getOrderType()));
            order.setOrderType(UDIOrderTypes.CREDIT_ORDER.getOrderType()); 
        }
        else
        {
            order.setC_DocTypeTarget_ID(getDocTypeFromOrderType(ctx,orderType));
            order.setOrderType(orderType); 
        }
        
        order.setM_Warehouse_ID(warehouseId);
        order.setIsDiscountPrinted(true);
        order.setPaymentRule(paymentRule); 
        order.setDateOrdered(new Timestamp(System.currentTimeMillis()));
       
        UDIMOrder udiOrder = new UDIMOrder(order);
        udiOrder.save();
        
        return udiOrder.getOrder();
    }
    
    

    
    
    public static MOrderLine createOrderLine(Properties ctx,MOrder order,int productID,Integer qty,BigDecimal discount,BigDecimal actualPrice) throws OperationException
    {
        int priceListVersionId=0;
        
        if(qty==null)
            qty=Integer.valueOf(1);
        MOrderLine orderLine = new MOrderLine(ctx, 0,order.get_TrxName());
        orderLine.setC_Order_ID(order.get_ID());
        orderLine.setHeaderInfo(order);
        
        MProduct product = new MProduct(ctx,productID,order.get_TrxName());
        MTax tax = TaxManager.getTaxFromCategory(ctx, product.getC_TaxCategory_ID(), order.get_TrxName());
        orderLine.setC_Tax_ID(tax.get_ID());
        orderLine.setC_UOM_ID(product.getC_UOM_ID());
        orderLine.setC_BPartner_ID(order.getC_BPartner_ID());
        orderLine.setQtyOrdered(new BigDecimal(qty.intValue()));
        orderLine.setQtyEntered(new BigDecimal(qty.intValue()));
        orderLine.setM_Product_ID(productID);
       
            
            if(discount==null)
                discount=new BigDecimal(0);
            orderLine.setDiscount(discount);
            BigDecimal newActualPrice=new BigDecimal(round(actualPrice.doubleValue() /qty.doubleValue()));
            BigDecimal lineTaxAmount = getLineTaxAmtAfterDis(newActualPrice, tax.getRate());
            
            orderLine.setPrice(new BigDecimal(round(lineTaxAmount.doubleValue())));
        
        UDIMOrderLine udiOrderLine = new UDIMOrderLine(orderLine);
        udiOrderLine.save();
        if(discount.equals(new BigDecimal(100)))
        {
            orderLine.setDiscount(new BigDecimal(100));
            orderLine.setPrice(new BigDecimal(0));
            
        }
        if(orderLine.getPriceList().equals(new BigDecimal(0)))
        {
            priceListVersionId=PriceListManager.getPriceListVersionID(ctx,order.getM_PriceList_ID(),null);
            BigDecimal listPrice=ProductManager.getPrice(ctx, priceListVersionId, productID,false,true,false, null);
            orderLine.setPriceList(listPrice);
        }
        udiOrderLine.save();   
        
        return udiOrderLine.getMOrderLine();
    }
    
    public static BigDecimal getLineTaxAmtAfterDis(BigDecimal amt,BigDecimal rate)
    {
        return new BigDecimal (amt.doubleValue()/(100+rate.doubleValue())*100);
        
    }
    
    
    public static MOrder completeOrder(Properties ctx,MOrder order) throws BPartnerOverCreditLimitException,OperationException
    {
        if(order.isSOTrx()==true)
        {
            CreditCheckBean crBean = OrderManager.checkBPartnerCreditLimit(ctx,order.getC_BPartner_ID(),order.get_ID(),order.get_TrxName());
            if(crBean.getValid()==false)
                throw new BPartnerOverCreditLimitException(crBean.getMsg());
        }
        UDIMOrder udiOrder = new UDIMOrder(order);
        udiOrder.processIt(DocumentEngine.ACTION_Complete);
        return order;
    }
    
    public static BigDecimal calculateActualPrice(Properties ctx,BigDecimal listPrice, BigDecimal discount) throws OperationException
    {
        double actualPrice;
        double div = (discount.doubleValue()/100)*listPrice.doubleValue();
        actualPrice=listPrice.doubleValue()-(div);
        
        BigDecimal formattedBigDecimal = FormatBigDecimal.currency(actualPrice);
        
        return formattedBigDecimal;
    }
    
    
    
    public static  double round(double value) 
    {
        DecimalFormat f = DisplayType.getNumberFormat(DisplayType.Number);
        
        return Double.parseDouble(f.format(value));
    }
    
    public static int getDocTypeFromOrderType(Properties ctx,String orderType) throws OperationException
    {
        
        MDocType[] docBaseTypes;
        
        if(orderType.equalsIgnoreCase(UDIOrderTypes.POS_ORDER.getOrderType()))
        {
            
            return getDocTypeIDForStandardOrder(ctx);
        }
        
        if(orderType.equalsIgnoreCase(UDIOrderTypes.CUSTOMER_RETURN_ORDER.getOrderType()))
        {
            return getDocTypeIDForCustomerReturnOrder(ctx);
        }
        
                
        if(orderType.equalsIgnoreCase(UDIOrderTypes.POS_GOODS_RETURN_NOTE.getOrderType()))
        {
            docBaseTypes =  MDocType.getOfDocBaseType(ctx, MDocType.DOCBASETYPE_APCreditMemo);
            if (docBaseTypes.length > 1)
                throw new OperationException("Expected one document type for AP Credit Memo but got more than 1");
            
            return docBaseTypes[0].get_ID();
        }
        
        
        if(orderType.equalsIgnoreCase(UDIOrderTypes.POS_GOODS_RECEIVE_NOTE.getOrderType()))
        {
            docBaseTypes =  MDocType.getOfDocBaseType(ctx, MDocType.DOCBASETYPE_PurchaseOrder);
            if (docBaseTypes.length > 1)
                throw new OperationException("Expected one document type for Purchase Order but got more than 1");
            
            return docBaseTypes[0].get_ID();
        }
        
        if(orderType.equalsIgnoreCase(UDIOrderTypes.WEBSTORE_ORDER.getOrderType()))
        {
            MDocType.getAllIDs(MDocType.Table_Name,"AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+" and DOCSUBTYPESO='"+MDocType.DOCSUBTYPESO_StandardOrder+"'",null);
            return getDocTypeIDForStandardOrder(ctx);
        }
        
        
        if(orderType.equalsIgnoreCase(UDIOrderTypes.CREDIT_ORDER.getOrderType()))
        {
            return getDocTypeIDForCreditOrder(ctx);
        }
        
        else
            
            return getDocTypeIDForStandardOrder(ctx);
        
    }
    
    
    
    public static int getDocTypeIDForStandardOrder(Properties ctx) throws OperationException
    {
        int [] docTypes =  MDocType.getAllIDs(MDocType.Table_Name,"AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+" and DOCSUBTYPESO='"+MDocType.DOCSUBTYPESO_StandardOrder+"'",null);
        if (docTypes.length > 1)
            throw new OperationException("Expected one document type for Standard order but got more than 1");
        
        return docTypes[0];
        
    }
    
    public static int getDocTypeIDForCreditOrder(Properties ctx) throws OperationException
    {
        int [] docTypes =  MDocType.getAllIDs(MDocType.Table_Name,"AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+" and DOCSUBTYPESO='"+MDocType.DOCSUBTYPESO_OnCreditOrder+"'",null);
        if (docTypes.length > 1)
            throw new OperationException("Expected one document type for Credit order but got more than 1");
        
        return docTypes[0];
        
    }
    
    public static int getDocTypeIDForCustomerReturnOrder(Properties ctx) throws OperationException
    {
        int [] docTypes =  MDocType.getAllIDs(MDocType.Table_Name,"AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+" and DOCSUBTYPESO='"+MDocType.DOCSUBTYPESO_ReturnMaterial+"'",null);
        if (docTypes.length > 1)
            throw new OperationException("Expected one document type for Return Material but got more than 1");
        
        return docTypes[0];
        
    }
    
    public static int[] getAllOrderlineIDs(Properties ctx,int orderId,String trxName)
    {
        String whereClause = "" +
        " C_ORDER_ID =" + orderId +
        " AND AD_CLIENT_ID =" + Env.getAD_Client_ID(ctx) +
        " AND ISACTIVE = 'Y'";
        
        return MOrderLine.getAllIDs(MOrderLine.Table_Name,whereClause,trxName);
    }
    
    
    /**
     * Checks the order to see whether it has some orderlines
     * @return true if empty else false is returned
     */    
    public static boolean isOrderEmpty(Properties ctx,int orderId,String trxName)
    {
        int[] orderlineIDs = getAllOrderlineIDs(ctx,orderId,trxName);
        
        if(orderlineIDs==null||orderlineIDs.length==0)
        {
            return true;
        }
        
        return false;
    }
    
    public static MOrder prepareOrder(Properties ctx,int orderId,String trxName) throws OperationException
    {
        MOrder order = new MOrder(ctx,orderId,trxName);
        UDIMOrder udiOrder = new UDIMOrder(order);
        udiOrder.processIt(DocumentEngine.ACTION_Prepare);
        return order;
    }
    
    public static MOrder loadOrder(Properties ctx, int orderId, String trxName) throws OperationException
    {
        MOrder order = new MOrder(ctx, orderId, trxName);
        
        if(order.get_ID() == 0)
            throw new OperationException("Could not load order with id: " + orderId);
        
        return order;
    }
    
    
    public static void deleteDraftedOrder(Properties ctx, int orderId, String trxName) throws OperationException
    {
        MOrder order = loadOrder(ctx, orderId, trxName);
        
        deleteDraftedOrder(ctx, order);
    }
    
    
    public static void deleteDraftedOrder(Properties ctx, MOrder order) throws OperationException
    {
        if(order.isProcessed())
            throw new OperationException("Cannot delete order that is already processed");
        
        if(!order.getDocStatus().equals(DocAction.STATUS_Drafted))
            throw new OperationException("The order is not a drafted order");
        
        deleteOrder(ctx, order);
    }
    
    private static void deleteOrder(Properties ctx, MOrder order) throws OperationException
    {
        if(order == null)
            throw new OperationException("Order cannot be null");
        
        if(order.isProcessed())
            throw new OperationException("Cannot delete order that is already processed");
        
        if(!order.delete(true))
            throw new OperationException("Could not delete order");
    }
    
    
    /**
     * Deletes all the orderlines from the order
     * @param ctx
     * @param order the order
     * @param trxName
     * @throws OperationException
     */
    public static void deleteOrderlines(Properties ctx, MOrder order, String trxName) throws OperationException
    {
        boolean isEmpty = OrderManager.isOrderEmpty(ctx, order.get_ID(), trxName);
        
        if(isEmpty)
        {
            return;
        }
        
        //checks the status of the order		 
        if(order.isProcessed())
        {
            throw new OperationException("Cannot delete order that is already processed");
        }
        
        String sql = "delete from C_ORDERLINE where C_ORDER_ID = " + order.get_ID();
        
        int no = DB.executeUpdate(sql, trxName);
        
        if (no == -1)
        {
        	throw new OperationException("Could not delete order lines");
        }
        
    }//deleteOrderlines
    
    
    public static void rePrintOrder(Properties ctx,int orderId) throws OperationException
    {
    	PrintManager.printOrder(ctx, orderId, null);
    }
    
    public static void printOrder(Properties ctx,MOrder order) throws OperationException
    {
        
        if(order.getOrderType().equals(UDIOrderTypes.POS_ORDER.getOrderType()))
        {
        	PrintManager.openCashDrawer(ctx);  
        }
        PrintManager.printOrder(ctx, order.get_ID(), null);
        
    }
    
    public static void printOrder(Properties ctx,MOrder order,OrderLineBean bean) throws OperationException
    {
        
        if(order.getOrderType().equals(UDIOrderTypes.POS_ORDER.getOrderType()))
        {
            PrintManager.openCashDrawer(ctx);  
        }
        PrintManager.print(ctx,ReportEngine.ORDER,order.get_ID(),bean);
    }
    
    
    public static CreditCheckBean checkBPartnerCreditLimit(Properties ctx,int bPartnerId,int orderId, String trxName)
    {
        MBPartner bp = new MBPartner(ctx,bPartnerId,trxName);
        String m_processMsg=null;
        
        MOrder order = new MOrder(ctx,orderId,trxName);
        CreditCheckBean bean= new CreditCheckBean();
        
        boolean valid = true;
        
        if (MBPartner.SOCREDITSTATUS_CreditStop.equals(bp.getSOCreditStatus()))
        {
            valid = false;         
            
            m_processMsg = "BPartnerCreditStop - TotalOpenBalance= " 
                + bp.getTotalOpenBalance()
                + ", CreditLimit= " + bp.getSO_CreditLimit();
            
        }
        if (MBPartner.SOCREDITSTATUS_CreditHold.equals(bp.getSOCreditStatus()))
        {
            
                
                valid = false;
                
                m_processMsg = "BPartnerCreditHold - TotalOpenBalance= " 
                    + bp.getTotalOpenBalance() 
                    + ", CreditLimit= " + bp.getSO_CreditLimit();
        }
        
        BigDecimal grandTotal = MConversionRate.convertBase(ctx, 
                order.getGrandTotal(),order.getC_Currency_ID(), order.getDateOrdered(), 
                order.getC_ConversionType_ID(), Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx));
        
        if (MBPartner.SOCREDITSTATUS_CreditHold.equals(bp.getSOCreditStatus(grandTotal)))
        {
            
                valid = false;
                
                m_processMsg = "BPartnerOverCreditHold - TotalOpenBalance= " 
                    + bp.getTotalOpenBalance() + ", GrandTotal = " + order.getGrandTotal()
                    + ", CreditLimit= " + bp.getSO_CreditLimit();
                
           
        }
        
        if (MBPartner.SOCREDITSTATUS_NoCreditCheck.equals(bp.getSOCreditStatus()))
        {
            
                valid = true;
                
                m_processMsg = "BPartnerCreditHold - TotalOpenBalance= " 
                    + bp.getTotalOpenBalance() 
                    + ", CreditLimit= " + bp.getSO_CreditLimit();
                
          
        }
        
        
        if (MBPartner.SOCREDITSTATUS_NoCreditCheck.equals(bp.getSOCreditStatus(order.getGrandTotal())))
        {
           
                valid = true;
                
                m_processMsg = "BPartnerOverCreditHold - TotalOpenBalance= " 
                    + bp.getTotalOpenBalance() + ", GrandTotal = " + order.getGrandTotal()
                    + ", CreditLimit= " + bp.getSO_CreditLimit();
                
          
        }
        bean.setValid(valid);
        bean.setMsg(m_processMsg);
        return bean;
    }
    
}

