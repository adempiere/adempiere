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
 */
package org.posterita.order;


public class UDIOrderTypes
{
    private UDIOrderTypes()
    {
        
    }   
   
    public static final UDIOrderType CREDIT_ORDER = new CreditOrder();
    
    public static final UDIOrderType CREDIT_ORDER_NO_SHIPMENT = new CreditOrderNoShipment();
    
    public static final UDIOrderType CUSTOMER_RETURN_ORDER = new CustomerReturnOrder();
    
    public static final UDIOrderType POS_ORDER = new POSOrder();
    
    public static final UDIOrderType POS_GOODS_RECEIVE_NOTE = new POSGoodsReceiveNote();
    
    public static final UDIOrderType POS_GOODS_RETURN_NOTE = new POSGoodsReturnNote();
    
    public static final UDIOrderType WEBSTORE_ORDER = new WebstoreOrder();
    
    public static final UDIOrderType CREDIT_MEMO = new CreditMemo();
    
    
    
    public static class POSGoodsReceiveNote implements UDIOrderType
    {
        
        public String getOrderType() 
        {
            return "POS Goods Receive Note";
        }
        
    }
    
    public static class CreditMemo implements UDIOrderType
    {
        
        public String getOrderType() 
        {
            return "Credit Memo";
        }
        
    }
    
    public static class POSGoodsReturnNote implements UDIOrderType
    {
        
        public String getOrderType() 
        {
            return "POS Goods Returned Note";
        }
        
    }
    
    public static class POSOrder implements UDIOrderType
    {
        
        public String getOrderType() 
        {
            return "POS Order";
        }
        
    }
   
    public static class CustomerOrder implements UDIOrderType
    {
        
        public String getOrderType() 
        {
            return "Customer Order";
        }
        
    }
       
    public static class AllocationOrder implements UDIOrderType
    {
        public String getOrderType() 
        {
            return "Allocation Order";
        }
        
    }
    
    public static class CreditOrder implements UDIOrderType
    {
        public String getOrderType()
        {
            return "Credit Order";
        }
    }
    
    public static class CreditOrderNoShipment implements UDIOrderType
    {
        public String getOrderType()
        {
            return "Credit Order no Shipment";
        }
    }
    
    public static class CustomerReturnOrder implements UDIOrderType
    {
        
        public String getOrderType() 
        {
            return "Customer Returned Order";
        }
        
    }    
    
    public static class WebstoreOrder implements UDIOrderType
    {
        
        public String getOrderType() 
        {
            return "Webstore Order";
        }
        
    }       
}
