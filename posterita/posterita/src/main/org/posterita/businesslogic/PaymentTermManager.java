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
 * Created on Oct 31, 2006 by alok
 */


package org.posterita.businesslogic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.model.MBPartner;
import org.compiere.model.MPaymentTerm;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.posterita.beans.PaymentTermBean;
import org.posterita.exceptions.CannotDeactivatePaymentTermException;
import org.posterita.exceptions.InvalidNetDaysException;
import org.posterita.exceptions.MandatoryNameException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.PaymentTermAlreadyExistsException;
import org.posterita.util.PoManager;


public class PaymentTermManager 
{
    
    public static ArrayList getAllActivePaymentTerm(Properties ctx) throws OperationException
    {
        return getAllPaymentTerm(ctx,true); 
    }
    public static ArrayList getAllPaymentTerm(Properties ctx,boolean isActive) throws OperationException
    {
        String sql = "select " +
        " C_PAYMENTTERM_ID," +//1
        " NAME," +//2
        " NETDAYS,"+//3
        " CASE WHEN isActive = 'Y' THEN 'true' ELSE 'false' END AS active ," +
        //" DECODE(isActive,'Y','true','false') active,"+//4
        " CASE WHEN AFTERDELIVERY = 'Y' THEN 'true' ELSE 'false' END AS delivery," +
        //" DECODE(AFTERDELIVERY,'Y','true','false') delivery,"+//5
        " CASE WHEN ISDUEFIXED = 'Y' THEN 'true' ELSE 'false' END AS dueFixed," +
        //" DECODE(ISDUEFIXED,'Y','true','false') dueFixed,"+//
        " FIXMONTHCUTOFF,"+//7
        " FIXMONTHDAY,"+//8
        " FIXMONTHOFFSET"+//9
        " from C_PAYMENTTERM " +
        " where AD_CLIENT_ID="+Env.getAD_Client_ID(ctx);
        
        if(isActive==true)
            sql=sql+"and isActive = 'Y'";
        
        
        ArrayList<PaymentTermBean> list = new ArrayList<PaymentTermBean>();
        PreparedStatement pstmt = DB.prepareStatement(sql,null);
        ResultSet rs=null;
        
        
        PaymentTermBean bean=null;
        try 
        {
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                
                bean=new PaymentTermBean();
                bean.setPaymentTermId(rs.getInt(1));
                bean.setPaymentTermName(rs.getString(2));
                bean.setNetDays(rs.getInt(3));
                bean.setIsActive(Boolean.parseBoolean(rs.getString(4)));
                bean.setAfterDelivery(Boolean.parseBoolean(rs.getString(5)));
                bean.setFixedDueDate(Boolean.parseBoolean(rs.getString(6)));
                bean.setFiedMonthCutoff(rs.getInt(7));
                bean.setFixedMonthDay(rs.getInt(8));
                bean.setFixedMonthOffset(rs.getInt(9));
                
                list.add(bean);
            }
        } 
        catch (SQLException e) 
        {
            throw new OperationException(e);
        }
        finally
        {
            try 
            {
                if(rs!=null)
                    rs.close();
                pstmt.close();
            } 
            catch (SQLException e)
            {
                
            }
        }
        
        return list;
    }
    
    public static MPaymentTerm createEditPaymentTerm(Properties ctx,PaymentTermBean bean,String trxName ) throws OperationException
    {
        if(bean.getPaymentTermName()==null)
        {
            throw new MandatoryNameException(" name is mandatory");
        }
        
        
        MPaymentTerm paymentTerm=null;
        String whereClause="AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+" and name='"+bean.getPaymentTermName()+"'";
        int [] paymentTermIds=MPaymentTerm.getAllIDs(MPaymentTerm.Table_Name,whereClause,null);
        
        if(bean.getPaymentTermId()==null)
        {
            paymentTerm = new MPaymentTerm(ctx,0,trxName);
            if(paymentTermIds.length>0)
                throw new PaymentTermAlreadyExistsException("The Payment Term Name already exists");
        }
        else
        {
            paymentTerm = new MPaymentTerm(ctx,bean.getPaymentTermId(),trxName); 
            if(paymentTermIds.length>0 && !paymentTerm.getName().equalsIgnoreCase(bean.getPaymentTermName()))
                throw new PaymentTermAlreadyExistsException("The Payment Term Name already exists");
            
        }
        paymentTerm.setName(bean.getPaymentTermName());
        paymentTerm.setValue(bean.getPaymentTermName());
       
        if(bean.getIsActive()==false)
        {
	        String whereClause1="AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+" and C_PAYMENTTERM_ID="+bean.getPaymentTermId();
	 
	        int [] bPids = MBPartner.getAllIDs(MBPartner.Table_Name,whereClause1,null);
		 	   if(bPids!=null && bPids.length>0)
		 	   {
		 		   throw new CannotDeactivatePaymentTermException("cannot deActivate payment term,have associated bPartner");
		 		   
		 	   }
        }
        
        paymentTerm.setIsActive(bean.getIsActive());
        paymentTerm.setAfterDelivery(bean.getAfterDelivery().booleanValue());
        if(bean.getFixedDueDate().booleanValue()==true)
        {
            paymentTerm.setIsDueFixed(true);
            
            /**The Fix Month Cutoff indicates the last 
             * day invoices can have to be included in the current 
             * due date. This field only displays when the fixed due date checkbox has been
             *  selected.*/  
            paymentTerm.setFixMonthCutoff(bean.getFiedMonthCutoff().intValue());
            
            /**The Fix Month Day indicates the day of the month that invoices are due. 
            This field only displays if the fixed due date checkbox is selected.*/
            paymentTerm.setFixMonthDay(bean.getFixedMonthDay());
            
            /**The Fixed Month Offset indicates the number of 
             * months from the current month to indicate an invoice is due. 
             * A 0 indicates the same month, a 1 the following month.
             *  This field will only display if the fixed due date checkbox is selected.*/
            
            if(bean.getFixedMonthOffset()==null)
                bean.setFixedMonthOffset(0);
            paymentTerm.setFixMonthOffset(bean.getFixedMonthOffset());
            
        }
        else
        {
            paymentTerm.setIsDueFixed(false);
            if(bean.getNetDays()==null || bean.getNetDays().intValue()<0)
            {
                throw new InvalidNetDaysException(" net days cannot be negative");
            }
            paymentTerm.setNetDays(bean.getNetDays());
        }
        
        if(bean.getDiscountDay1()!=null)
        {
            paymentTerm.setDiscountDays(bean.getDiscountDay1());
        }
        if(bean.getDiscountAmt1()!=null)
        {
            paymentTerm.setDiscount(bean.getDiscountAmt1());  
        }
        if(bean.getDiscountDay2()!=null)
        {
            paymentTerm.setDiscountDays2(bean.getDiscountDay2());
        }
        if(bean.getDiscountAmt2()!=null)
        {
            paymentTerm.setDiscount2(bean.getDiscountAmt2()); 
        }
        PoManager.save(paymentTerm);
        return paymentTerm;
    }
    
    
    public static PaymentTermBean getPaymentTerm(Properties ctx,int paymentTermId) throws OperationException
    {
        String sql = "select " +
        " C_PAYMENTTERM_ID," +//1
        " NAME," +//2
        " NETDAYS,"+//3
        //" DECODE(isActive,'Y','true','false') active,"+//4
        " CASE WHEN isActive='Y' THEN 'true' ELSE 'false' END AS active,"+//4
        //" DECODE(AFTERDELIVERY,'Y','true','false') delivery,"+//5
        " CASE WHEN AFTERDELIVERY = 'Y' THEN 'true' ELSE 'false' END AS delivery,"+//5
        //" DECODE(ISDUEFIXED,'Y','true','false') dueFixed,"+//6
        " CASE WHEN ISDUEFIXED = 'Y' THEN 'true' ELSE 'false' END AS dueFixed,"+//6
        " FIXMONTHCUTOFF,"+//7
        " FIXMONTHDAY,"+//8
        " FIXMONTHOFFSET,"+//9
        " DISCOUNTDAYS,"+//10
        " DISCOUNT,"+//11
        " DISCOUNTDAYS2,"+//12
        " DISCOUNT2"+//13
        " from C_PAYMENTTERM " +
        " where AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+
        " and C_PAYMENTTERM_ID ="+paymentTermId;
        
        PreparedStatement pstmt = DB.prepareStatement(sql,null);
        ResultSet rs=null;
        
        PaymentTermBean bean=null;
        try 
        {
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                
                bean=new PaymentTermBean();
                bean.setPaymentTermId(rs.getInt(1));
                bean.setPaymentTermName(rs.getString(2));
                bean.setNetDays(rs.getInt(3));
                bean.setIsActive(Boolean.parseBoolean(rs.getString(4)));
                bean.setAfterDelivery(Boolean.parseBoolean(rs.getString(5)));
                bean.setFixedDueDate(Boolean.parseBoolean(rs.getString(6)));
                bean.setFiedMonthCutoff(rs.getInt(7));
                bean.setFixedMonthDay(rs.getInt(8));
                bean.setFixedMonthOffset(rs.getInt(9));
                bean.setDiscountDay1(rs.getInt(10));
                bean.setDiscountAmt1(rs.getBigDecimal(11));
                bean.setDiscountDay2(rs.getInt(12));
                bean.setDiscountAmt2(rs.getBigDecimal(13));
                
            }
        } 
        catch (SQLException e) 
        {
            throw new OperationException(e);
        }
        finally
        {
            try 
            {
                if(rs!=null)
                    rs.close();
                pstmt.close();
            } 
            catch (SQLException e)
            {
                
            }
        }
        
        return bean;
    }
    
    
    public static MPaymentTerm activatePaymentTerm(Properties ctx,int paymentTermId, boolean activate) throws OperationException
    {
       
        
        MPaymentTerm   paymentTerm = new MPaymentTerm(ctx,paymentTermId,null); 
       
       if(activate==true)
       {
    	   paymentTerm.setIsActive(true);
       }
        
       else
       {
    	   String whereClause="AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+" and C_PAYMENTTERM_ID="+paymentTermId;
    	   int [] bPids = MBPartner.getAllIDs(MBPartner.Table_Name,whereClause,null);
    	   if(bPids!=null && bPids.length>0)
    	   {
    		   throw new CannotDeactivatePaymentTermException("cannot deActivate payment term,have associated bPartner");
    	   }
    	   paymentTerm.setIsActive(false);
       }
      
        
       	PoManager.save(paymentTerm);
        return paymentTerm;
    } 
    
}
