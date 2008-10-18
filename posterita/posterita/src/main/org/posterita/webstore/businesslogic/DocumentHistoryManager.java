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
 **/
package org.posterita.webstore.businesslogic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.util.DB;
import org.compiere.util.Env;
import org.posterita.beans.DocumentHistoryBean;
import org.posterita.core.UDIMap;
import org.posterita.exceptions.OperationException;

public class DocumentHistoryManager
{
	
	private static Integer getIntegerValue(int value)
	{
		if (value == 0)
			return null;
		else
			return Integer.valueOf(value);
	}

	
	public static ArrayList<DocumentHistoryBean> getHistory(Properties ctx, Integer bpartnerId, String docStatus, Integer month, Integer year, String orderType) throws OperationException
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" select");
		sql.append(" ord.c_order_id, "); 		//1.orderID
		sql.append(" pay.c_payment_id, ");		//2.paymentID
		sql.append(" inv.c_invoice_id, ");		//3.invoiceID
		sql.append(" ship.m_inout_id, ");		//4.shipmentID
		sql.append(" partner.c_bpartner_id, ");	//5.partnerID
		sql.append(" partner.name, ");			//6.partnerName
		sql.append(" partner.name2, ");			//7.partnerName2
		sql.append(" ord.created, ");			//8.dateCreated
		sql.append(" ship.docstatus,");			//9/docStatus
		//sql.append(" DECODE(pay.docstatus, 'CO', 'Paid', 'Awaiting Payment') paymentStatus,");
		sql.append(" CASE WHEN pay.docstatus = 'CO' THEN 'Paid' ELSE  'Awaiting Payment' END AS paymentStatus,");
		//sql.append(" DECODE (ship.docstatus, 'CO', 'Shipped', 'Not Shipped') shipmentStatus,");
		sql.append(" CASE WHEN ship.docstatus= 'CO' THEN 'Shipped' ELSE  'Not Shipped' END AS shipmentStatus,");
		sql.append(" ord.documentNo,");
        sql.append(" inv.documentNo,");
        sql.append(" pay.documentNo,");
        sql.append(" ship.documentNo,");
        sql.append(" partner.isCustomer,");
        sql.append(" partner.isVendor from ");
		sql.append("(((c_order ord left outer join c_invoice inv on ord.C_Order_ID=inv.C_Order_ID) left outer join M_InOut ship on ord.C_Order_ID=ship.C_Order_ID) left outer join c_payment pay on inv.c_invoice_id = pay.c_invoice_id)");
		sql.append(", c_bpartner partner");
		sql.append(" where ord.ad_client_id=" + Env.getAD_Client_ID(ctx));
		sql.append(" and ord.ad_org_id=" + Env.getAD_Org_ID(ctx));
		sql.append(" and partner.c_bpartner_id=ord.c_bpartner_id");
		sql.append(" and ord.isactive='Y'");

		
		if(orderType!=null)
		{
			sql.append(" and ord.ordertype='" + orderType + "'");
		}
			
        if (month != null) 
        {
        	String mm = String.valueOf(month);
        	if (mm.length() == 1)
        	{
        		mm = "0" + mm;
        	}
        	sql.append(" and to_char(ord.dateOrdered, 'mm') ='" + mm + "'");
        }	    	
        
        if (year != null) 
        {
        	sql.append(" and to_char(ord.dateOrdered, 'yyyy') ='" + year+"'");
        }	
        
        if (bpartnerId != null)
        {
        	sql.append(" and ord.c_bpartner_id=" + bpartnerId);
        }
        
    	sql.append(" order by ord.c_order_id desc");
        
        
        
        PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);		
        
        ResultSet rs = null;
        DocumentHistoryBean bean;
        ArrayList <DocumentHistoryBean> list = new ArrayList<DocumentHistoryBean>();
        
        try 
        {
            rs = pstmt.executeQuery();
            
            while(rs.next())
            {	
            	bean = new DocumentHistoryBean();
		        bean.setOrderId(getIntegerValue(rs.getInt(1)));
		        bean.setPaymentId(getIntegerValue(rs.getInt(2)));
		        bean.setInvoiceId(getIntegerValue(rs.getInt(3)));
		        bean.setMinOutId(getIntegerValue(rs.getInt(4)));
		        bean.setBpartnerId(getIntegerValue(rs.getInt(5)));
		        bean.setPartnerName(rs.getString(6) + " " + rs.getString(7));
		        bean.setDateOrdered(rs.getTimestamp(8));

		        if (rs.getString(9) != null)
		        {
		        	bean.setDocStatus(UDIMap.docStatusMap.get(rs.getString(9)));
		        	bean.setDocStatusCode(rs.getString(9));
		        }
		        
		        bean.setPaymentStatus(rs.getString(10));
		        bean.setShipmentStatus(rs.getString(11));
                bean.setDocumentNo(rs.getString(12));
                bean.setInvoiceNo(rs.getString(13));
                bean.setPaymentNo(rs.getString(14));
                bean.setShipmentNo(rs.getString(15)); 
                
                if(rs.getString(16).equals("Y"))
                {
                	bean.setIsCustomer(Boolean.valueOf(true));
                }
                else
                {
                	bean.setIsCustomer(Boolean.valueOf(false));
                }
                
                if(rs.getString(17).equals("Y"))
                {
                	bean.setIsVendor(Boolean.valueOf(true));
                }
                else
                {
                	bean.setIsVendor(Boolean.valueOf(false));
                }
                
		        
		        list.add(bean);
            }
            
        } 
		catch (SQLException e)
		{
			throw new OperationException(e.getMessage());
		} 
		finally 
		{
			try 
			{
				rs.close();
				pstmt.close();				
			} 
			catch (Exception ex) 
			{
			} 
			rs = null;
			pstmt = null;
		}
        
		return list;
	}
	
	
}
