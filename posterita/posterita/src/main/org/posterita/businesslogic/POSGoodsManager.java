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
 * Created on 27-Apr-2006 by alok
 */


package org.posterita.businesslogic;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.compiere.model.MBPartner;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutConfirm;
import org.compiere.model.MInvoice;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPayment;
import org.compiere.model.MWarehouse;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.posterita.beans.CreditCheckBean;
import org.posterita.beans.ItemBean;
import org.posterita.beans.OrderLineBean;
import org.posterita.beans.ProductBean;
import org.posterita.beans.ProductCategoryBean;
import org.posterita.businesslogic.creditsales.CreditOrderManager;
import org.posterita.exceptions.BPartnerOverCreditLimitException;
import org.posterita.exceptions.NoOrderLineFoundException;
import org.posterita.exceptions.OperationException;
import org.posterita.lib.UdiConstants;
import org.posterita.order.UDIOrderTypes;
import org.posterita.util.PoManager;


public class POSGoodsManager 
{
    public static final String GET_ALL_VENDORS="getAllVendors";
    public static final String GET_ALL_VENDORS_FOR_RETURN_NOTE="getAllVendorsForReturnNote";
    
    public static MOrder createCustomerReturn(Properties ctx,OrderLineBean bean,ArrayList cartBeanItems,Integer bPartnerId,String reason,String trxName) throws BPartnerOverCreditLimitException,OperationException
    {
        return createGoodsReceiveNote(ctx,bean,cartBeanItems,true,bPartnerId,reason,trxName);
    }
    
    public static MOrder createGoodsReceiveNote(Properties ctx,OrderLineBean bean,ArrayList cartBeanItems,Integer bPartnerId,String SupRef,String trxName)throws OperationException
    {
        return createGoodsReceiveNote(ctx,bean,cartBeanItems,false,bPartnerId,SupRef,trxName);
    }
    
    
    private static MOrder createGoodsReceiveNote(Properties ctx,OrderLineBean orderlineBean,ArrayList cartBeanItems,boolean isCustomerReturn,Integer bPartnerId,String desc,String trxName)throws BPartnerOverCreditLimitException,OperationException
    {        
        MOrder order = null;
        MWarehouse warehouse = POSTerminalManager.getWarehouse(ctx);
        int priceListId=0;
        Integer orderId = orderlineBean.getOrderId();
        String paymentRule=null;
        String orderType=null;
    
         
        if(orderId == null)
        {
        	if(isCustomerReturn)
            {
                priceListId=POSTerminalManager.getSOPriceListId(ctx);
                
                if(orderlineBean.getOrderType().equalsIgnoreCase(UDIOrderTypes.CREDIT_ORDER.getOrderType()))
                {
                    paymentRule=MOrder.PAYMENTRULE_OnCredit;
                    orderType=UDIOrderTypes.CREDIT_MEMO.getOrderType();
                }
                    
                else
                {
                    paymentRule = orderlineBean.getPaymentRule();
                    orderType=UDIOrderTypes.CUSTOMER_RETURN_ORDER.getOrderType();
                }
                
                order=OrderManager.createOrder(ctx,bPartnerId.intValue(),true,priceListId,orderType,
                       warehouse.get_ID(),paymentRule,trxName); 
                
              if(orderlineBean.getPaymentTermId()!=null)
                  order.setC_PaymentTerm_ID(orderlineBean.getPaymentTermId());
                    
                int terminalId = POSTerminalManager.getTerminalId(ctx);
                String posOrderDocNumber=orderlineBean.getDocumentNo();
                if(posOrderDocNumber!=null)
                {
                    String whereClause1="AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+
                    " and documentNo ='"+posOrderDocNumber+"'";
                    int orderids1 []= MOrder.getAllIDs(MOrder.Table_Name,whereClause1,null);
                    
                    String number="R"+posOrderDocNumber;
                    String whereClause=null;
                    if(orderlineBean.getOrderType().equalsIgnoreCase(UDIOrderTypes.CREDIT_ORDER.getOrderType()))  
                    {
                        whereClause="AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+" and orderType='"+UDIOrderTypes.CREDIT_MEMO.getOrderType()+
                        "' and documentNo like('%"+number+"%')"; 
                    }
                    else
                    {
                        whereClause="AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+" and orderType='"+UDIOrderTypes.CUSTOMER_RETURN_ORDER.getOrderType()+
                        "' and documentNo like('%"+number+"%')";
                    }
                    
                    int orderids []= MOrder.getAllIDs(MOrder.Table_Name,whereClause,null);
                    int noOfOrders = orderids.length;
                    String docNumber=number+"_"+(noOfOrders+1);
                    order.setDocumentNo(docNumber);
                    order.setRef_Order_ID(orderids1[0]);//using it when complete the credit memo to find whether shipment has to be done or not
                }
                
                if (terminalId == 0)
                	throw new OperationException("POS Id not present!!");
                
                //order.setC_POS_ID(terminalId);TODO - Trifon; order.setU_POSTerminal_ID(terminalId);
            }
            else
            {
                priceListId  = Env.getContextAsInt(ctx,UdiConstants.POS_PURCHASE_PL);
                order=OrderManager.createOrder(ctx,bPartnerId.intValue(),false,priceListId,UDIOrderTypes.POS_GOODS_RECEIVE_NOTE.getOrderType(),
                        warehouse.get_ID(),MOrder.PAYMENTRULE_Check,trxName); 
            }
        }
        else
        {
        	//load existing order
        	order = OrderManager.loadOrder(ctx, orderId, trxName);
        	
        	//delete previous orderlines
        	OrderManager.deleteOrderlines(ctx, order, trxName);
        }       
        
              
        boolean isDirty = false;
        
        if( bPartnerId != null )
        {
            order.setC_BPartner_ID(bPartnerId);
            isDirty = true;
        }
        
        
        if(desc!=null)
        {
        	order.setDescription(desc);      
            isDirty = true;                   
        }
        
        if( isDirty )
        {
        	PoManager.save(order);     
        }
          
      
        
        Iterator iter = cartBeanItems.iterator();
        ItemBean itemBean;
        while(iter.hasNext())
        {
            itemBean =(ItemBean)iter.next();
            
            int m_product_id = itemBean.getProductId();
            BigDecimal qty = itemBean.getQty();
            BigDecimal actualPrice = itemBean.getActualPrice();
            BigDecimal discount = itemBean.getDiscountPercent();
            BigDecimal price = itemBean.getPrice();
            
            OrderManager.createOrderLine(ctx, order, m_product_id, qty, discount, price);   
        }
        
        if(order.isSOTrx()==true)
        {
            CreditCheckBean crBean = OrderManager.checkBPartnerCreditLimit(ctx,order.getC_BPartner_ID(),order.get_ID(),order.get_TrxName());
            if(crBean.getValid()==false)
                throw new BPartnerOverCreditLimitException(crBean.getMsg());
        }
        
        return order;
        
    }
    
    public static MOrder createAndCompletePODocuments(Properties ctx, int purchaseOrderId,String trxName) throws OperationException
    {
        MOrder purchaseOrder = new MOrder(ctx,purchaseOrderId,trxName);
        
        MOrderLine [] lines=purchaseOrder.getLines();
        
        
        if(lines.length==0)
        {
           throw new NoOrderLineFoundException("No OrderLines For This Order");
        }
                
        MOrder completedPurchaseOrder = completePurchaseOrder(ctx,purchaseOrder,"GRN No "+purchaseOrder.get_ID());
         
        MInvoice draftedVendorInvoice = InvoiceManager.createVendorInvoice(ctx,purchaseOrder.get_ID(),trxName);
        
        MInvoice invoice = InvoiceManager.completeInvoice(ctx, draftedVendorInvoice);
        
        if(MInvoice.PAYMENTRULE_OnCredit.equals(invoice.getPaymentRule()))
        {
        	//do nothing
        }
        else
        {
        	 MPayment draftedAPPayment = PaymentManager.createAPPayment(ctx,draftedVendorInvoice, trxName);
             PaymentManager.completeAPPayment(ctx,draftedAPPayment);
        }       
        
        MWarehouse warehouse = new MWarehouse(ctx,purchaseOrder.getM_Warehouse_ID(),trxName);
       
        MInOut materialReceipt = MinOutManager.createMInOut(ctx,invoice,warehouse.get_ID());
        PoManager.save(materialReceipt);
       
        MInOutConfirm confirm=MinOutManager.createConfirmation(ctx,materialReceipt);
        MinOutManager.completeConfirmation(ctx, confirm);

        MinOutManager.completeShipment(ctx, materialReceipt);
        
        OrderManager.printOrder(ctx,completedPurchaseOrder);
        
        return completedPurchaseOrder;
    }
    
    public static MOrder completePurchaseOrder(Properties ctx, MOrder order,String POReference) throws BPartnerOverCreditLimitException,OperationException
	{
		
		if (order.getDocStatus().equals(DocumentEngine.STATUS_Completed))
			throw new OperationException("Order has already been completed");
		
		
		
		order.setPOReference(POReference);
		
		PoManager.processIt(order, DocumentEngine.ACTION_Complete);
				
		return order;
	}
    
    
    public static MOrder createGoodsReturnNote(Properties ctx,OrderLineBean orderlineBean,ArrayList cartBeanItems,Integer bPartnerId,String supRef,String trxName)throws OperationException
    {
        int  purchasePLID = POSTerminalManager.getPOPriceListId(ctx);
        MWarehouse warehouse = POSTerminalManager.getWarehouse(ctx);
        
        MOrder order = null;
        Integer orderId = orderlineBean.getOrderId();
        
        if(orderId == null)
        {
        	order = OrderManager.createOrder(ctx,bPartnerId.intValue(),true,purchasePLID,UDIOrderTypes.POS_GOODS_RETURN_NOTE.getOrderType(),
                    warehouse.get_ID(),MOrder.PAYMENTRULE_Check,trxName); 
        }
        else
        {
        	//load existing order
        	order = OrderManager.loadOrder(ctx, orderId, trxName);
        	
        	//delete previous orderlines
        	OrderManager.deleteOrderlines(ctx, order, trxName);
        }
        
        boolean isDirty = false;
        
        if( bPartnerId != null )
        {
        	MBPartner partner = new MBPartner(ctx, bPartnerId.intValue(), trxName);
            order.setBPartner(partner);
            isDirty = true;
        }
        
        
        if(supRef!=null)
        {
            order.setDescription(supRef);            
            isDirty = true;                   
        }
        
        if( isDirty )
        {
        	PoManager.save(order);        
        }
        
      
        Iterator iter = cartBeanItems.iterator();
        ItemBean itemBean;
        
        while(iter.hasNext())
        {
            itemBean =(ItemBean)iter.next();
            OrderManager.createOrderLine(ctx,order,itemBean.getProductId().intValue(),itemBean.getQty(),new BigDecimal(0),new BigDecimal(0)) ; 
        }
        return order;
    }
    
    
    public static ArrayList getAllSuppliers(Properties ctx) throws OperationException
    {
//        String sql = "select name||' '||name2 name," +//1
    	String sql = "select name," +//1 FIXME Postgresql Fix, need more investigation to get to above functionality
        "c_bpartner_id" +//2
        " from C_BPARTNER" +
        " where AD_CLIENT_ID="+Env.getAD_Client_ID(ctx) +
        " and ad_org_id in (" + Env.getContext(ctx,UdiConstants.ROLE_EDITABLE_ORGS_CTX_PARAM)+")"+
        " and ISVENDOR='Y'"+
        " and isactive='Y'"+
        " order by upper(name)";
        
        PreparedStatement pstmt = DB.prepareStatement(sql,null);
        
        ArrayList<OrderLineBean> list  = new ArrayList<OrderLineBean>();
        OrderLineBean bean = null;
        ResultSet rs = null;
        
        try
        {
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                bean = new OrderLineBean();
                bean.setPartnerName(rs.getString(1));
                bean.setBpartnerId(Integer.valueOf(rs.getInt(2)));
                list.add(bean);
            }
            
            rs.close();
        } 
        catch (SQLException e)
        {
            
            throw new OperationException(e);
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
        }
        
        return list;
    }
    
    public static String getForward(Properties ctx,String isSales)
    {
        if(Boolean.valueOf(isSales).booleanValue()==true)
            return GET_ALL_VENDORS;
        else
            return GET_ALL_VENDORS_FOR_RETURN_NOTE;
    }
    
    
    public static ArrayList getAllProductCategory(Properties ctx) throws OperationException
    {
        String sql="select name," +
        "m_product_category_id" +
        " from m_product_category"+
        " where AD_ORG_ID="+Env.getAD_Org_ID(ctx)+
        " and AD_CLIENT_ID="+Env.getAD_Client_ID(ctx);
        
        PreparedStatement pstmt = DB.prepareStatement(sql,null);
        ProductCategoryBean bean = null;
        ArrayList<ProductCategoryBean> list = new ArrayList<ProductCategoryBean>();
        ResultSet rs = null;
        
        try 
        {
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                bean = new ProductCategoryBean();
                bean.setName(rs.getString(1));
                bean.setProductCategoryId(Integer.valueOf(rs.getInt(2)));
                list.add(bean);
            }
            
            rs.close();
        }
        catch (SQLException e)
        {
            throw new OperationException(e);
        }
        finally
        {
        	try
        	{
               
                pstmt.close();
        	}
        	catch (Exception e)
        	{}
        	
        	pstmt = null;
        }
        
        return list;
        
    }
    
    
    public static ArrayList getAllRevenueRecos(Properties ctx) throws OperationException
    {
        String sql="select name," +
        "C_REVENUERECOGNITION_ID" +
        " from C_REVENUERECOGNITION"+
        " where AD_ORG_ID="+Env.getAD_Org_ID(ctx)+
        " and AD_CLIENT_ID="+Env.getAD_Client_ID(ctx);
        
        PreparedStatement pstmt = DB.prepareStatement(sql,null);
        ProductCategoryBean bean = null;
        ArrayList<ProductCategoryBean> list = new ArrayList<ProductCategoryBean>();
        ResultSet rs = null;
        try 
        {
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                bean = new ProductCategoryBean();
                bean.setName(rs.getString(1));
                bean.setProductCategoryId(Integer.valueOf(rs.getInt(2)));
                list.add(bean);
            }
            
            rs.close();
        }
        catch (SQLException e)
        {
            throw new OperationException(e);
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
        }
        
        return list;
        
    }
    
//    private static int getBPartnerForProduct(Properties ctx, int productId) throws OperationException
//    {
//        
//        String sql =" select c_bpartner_id from c_bpartner_product where "+
//        "AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+
//        " and AD_ORG_ID="+Env.getAD_Org_ID(ctx)+
//        " and M_PRODUCT_ID="+productId;
//        
//        int bPartnerId=0;
//        
//        PreparedStatement pstmt = DB.prepareStatement(sql,null);
//        
//        try 
//        {
//            ResultSet rs = pstmt.executeQuery();
//            while(rs.next())
//            {
//                bPartnerId=rs.getInt(1);
//            }
//        } 
//        catch (SQLException e) 
//        {
//            throw new OperationException(e);
//        }
//        
//        
//        return bPartnerId;
//    }
    
    public static ArrayList<ProductBean> getAllTaxCategory(Properties ctx) throws OperationException
    {
       // MProduct product = new MProduct(ctx,productId,null);
        
        
        String sql = "select tc.c_taxcategory_id, tc.name||' '||'Rate % '||tx.rate" +
                " from c_taxcategory tc,C_TAX tx" +
                " where tc.C_TAXCATEGORY_ID=tx.C_TAXCATEGORY_ID" +
                " and tx.AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+
                " and tx.isActive='Y'";
              
        
       // 
        PreparedStatement pstmt = DB.prepareStatement(sql,null);
        ProductBean bean=null;
        ArrayList<ProductBean> list = new ArrayList<ProductBean>();
        ResultSet rs = null;
        
        try 
        {
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                bean = new ProductBean();
                bean.setTaxCategoryId(Integer.valueOf(rs.getInt(1)));
                bean.setTaxCategoryName(rs.getString(2));
                list.add(bean);
            }
            
            rs.close();
        } 
        catch (SQLException e) 
        {
            throw new OperationException(e);
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
        }
        
        
        return list;
    }
    
    public static MOrder completeCustomerReturnOrder( Properties ctx,int orderId, String trxName) throws OperationException
    {
        MOrder order = new MOrder(ctx, orderId, trxName);
        
        //this is dne to support the simple customer return order so that the shipment takes place
        boolean delivered=true;
        
        if( order.getOrderType().equals(UDIOrderTypes.CUSTOMER_RETURN_ORDER.getOrderType())|| order.getOrderType().equals(UDIOrderTypes.CREDIT_MEMO.getOrderType()))
        {
        	if(order.getRef_Order_ID() != 0) //setting it while creating order
            {
                MOrder originalOrder = new MOrder(ctx,order.getRef_Order_ID(),trxName);
                delivered=compareOrders(ctx,originalOrder,order);
            }
        }
        if(delivered)
            return POSManager.completePOSOrder(ctx,orderId,trxName);
        else
           return CreditOrderManager.completeCreditOrderWithOutShipment(ctx,order, trxName);
    }
    
    private static boolean compareOrders(Properties ctx,MOrder orOrder,MOrder retOrder) throws OperationException
    {
        MOrderLine [] orOrderLine = orOrder.getLines();
        
        MOrderLine [] retOrderLine = retOrder.getLines();
        
        boolean delivered = false;
        
        for(int i=0;i<orOrderLine.length;i++)
        {
            int productId = orOrderLine[i].getM_Product_ID();
            BigDecimal qtyDelivered = orOrderLine[i].getQtyDelivered();
            
            if(qtyDelivered.equals(new BigDecimal(0)))
            {
                    continue;                    
            }
            else
            {
                for(int j=0;j<retOrderLine.length;j++)
                {
                    if(productId==retOrderLine[j].getM_Product_ID())
                    {
                        if(qtyDelivered.doubleValue()<retOrderLine[j].getQtyEntered().doubleValue())
                            throw new OperationException("qty return can not be more than qty delivered");
                    }
                }
                
                delivered = true;
            }
            
        }
        
        return delivered;
    }
    
    
}
