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
 * Created on Dec 7, 2006 by alok
 */


package org.posterita.businesslogic;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.model.MAllocationHdr;
import org.compiere.model.MAllocationLine;
import org.compiere.model.MInvoice;
import org.compiere.model.MPayment;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;
import org.compiere.util.Env;

import org.posterita.beans.OpenItemBean;
import org.posterita.exceptions.AllocatedAmtMoreThanOpenAmtException;
import org.posterita.exceptions.CanNotCreatePaymentAllocationException;
import org.posterita.exceptions.NoAllocateeInvoiceException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.PayAmtNotEqualToAllocateAmtException;
import org.posterita.exceptions.TooManyAllocateInvoiceException;
import org.posterita.util.PoManager;


public class PaymentAllocationManager
{
    
    
    public static void allocate(Properties ctx, Integer[] invoiceIds, Integer[] paymentIds, String trxName) throws TooManyAllocateInvoiceException,CanNotCreatePaymentAllocationException,OperationException
    {
        
    	BigDecimal discountAmt = new BigDecimal(0);
        BigDecimal writeOffAmt = new BigDecimal(0);
        BigDecimal overUnderAmt = new BigDecimal(0);
        int paymentId = 0;
        int cashLineId=0;
        int creditInvoiceId=0;
        MInvoice invoice = null;
        BigDecimal appliedAmt=new BigDecimal(0);
        int count=0;        
        
        if(invoiceIds==null || invoiceIds.length<1)
        {
            throw new NoAllocateeInvoiceException("Please select an Invoice to allocate");
        }
        
        if(invoiceIds.length<2 && (paymentIds==null || paymentIds.length<1))
        {
            throw new NoAllocateeInvoiceException("Please select an Invoice to allocate");
        }
               
        
        for(int i=0;i<invoiceIds.length;i++)
        {
            invoice = new MInvoice(ctx,invoiceIds[i],null);
            if(invoice.getOpenAmt().signum()==-1)
            {
                appliedAmt=appliedAmt.add(invoice.getOpenAmt().abs());
            }
            else if(invoice.getOpenAmt().signum()==1)
            {
                count=count+1;
                if(count>1)
                    throw new TooManyAllocateInvoiceException("can match only one credit Invoice");
                creditInvoiceId=invoice.get_ID();
            }
            
        }
        if(count==0)
        {
            throw new NoAllocateeInvoiceException("Please select an Invoice to allocate");
        }
        
        MInvoice creditInvoice = new MInvoice(ctx,creditInvoiceId,null);
        if(appliedAmt.doubleValue()>0)
        {
            if((appliedAmt.doubleValue())>creditInvoice.getOpenAmt().doubleValue())
            {
                throw new CanNotCreatePaymentAllocationException("the allocation amt is more than open amt");
            }
        }
        
        MAllocationHdr alloc = new MAllocationHdr (ctx, true,  //  manual
                new Timestamp(System.currentTimeMillis()), creditInvoice.getC_Currency_ID(), Env.getContext(Env.getCtx(), "#AD_User_Name"), trxName);
        alloc.setAD_Org_ID(Env.getAD_Org_ID(ctx));
        alloc.save();
        
        if(invoiceIds.length>1)
        {
            for(int j=0;j<invoiceIds.length;j++)
            {
                invoice = new MInvoice(ctx,invoiceIds[j],null);
                if(invoice.getOpenAmt().signum()==-1)
                {
                    MAllocationLine aLine = new MAllocationLine (alloc, invoice.getOpenAmt(), discountAmt, writeOffAmt, overUnderAmt);
                    aLine.setDocInfo(invoice.getC_BPartner_ID(),invoice.getC_Order_ID(), invoice.get_ID());
                    aLine.setPaymentInfo(paymentId, cashLineId);
                    aLine.save();
                }
                else if(invoice.getOpenAmt().signum()==1)
                {
                    MAllocationLine aLine = new MAllocationLine (alloc, appliedAmt, discountAmt, writeOffAmt, overUnderAmt);
                    aLine.setDocInfo(invoice.getC_BPartner_ID(),invoice.getC_Order_ID(), invoice.get_ID());
                    aLine.setPaymentInfo(paymentId, cashLineId);
                    aLine.save();
                }
                
            }
        }
        
        if(paymentIds!=null && paymentIds.length>0)
        {
            MPayment payment=null;
            BigDecimal overUnderPaymentAmt = null;
            for(int k=0;k<paymentIds.length;k++)
            {
                
                payment = new MPayment(ctx,paymentIds[k],null);
                BigDecimal availableAmt = getAvailableAmt(ctx,paymentIds[k].intValue());
                
                if(!payment.isReceipt())
                {
                	availableAmt = availableAmt.negate();
                }
                
                //Bug fix                
                BigDecimal invoiceOpenAmt = creditInvoice.getOpenAmt();
                overUnderPaymentAmt  = Env.ZERO; //
                
                BigDecimal allocationAmt = availableAmt;
                if(invoiceOpenAmt.doubleValue() < availableAmt.doubleValue())
                {
                	allocationAmt = invoiceOpenAmt;
                }  
                
                if(!payment.isReceipt())
                {
                	allocationAmt = allocationAmt.negate();
                }
                
                /*
                if(availableAmt.doubleValue()>creditInvoice.getOpenAmt().doubleValue())
                {
                    overUnderPayment=availableAmt.subtract(creditInvoice.getOpenAmt());
                }
                else
                {
                    overUnderPayment=payment.getOverUnderAmt();
                }
                */                
                
                MAllocationLine aLine = new MAllocationLine (alloc, allocationAmt, 
                        payment.getDiscountAmt(), payment.getWriteOffAmt(),overUnderPaymentAmt );
                aLine.setDocInfo(creditInvoice.getC_BPartner_ID(),creditInvoice.getC_Order_ID(), creditInvoice.get_ID());
                aLine.setPaymentInfo(payment.get_ID(), cashLineId);
                aLine.save();
            }
        }
        
        
        String sql = "SELECT invoiceOpen("+creditInvoiceId+", 0) FROM C_Invoice WHERE C_Invoice_ID = ?";
        BigDecimal open = DB.getSQLValueBD(null, sql, creditInvoiceId);
        if (open != null && open.signum() == 0)
        {
            sql = "UPDATE C_Invoice SET IsPaid='Y' WHERE C_Invoice_ID=" + creditInvoiceId;
            DB.executeUpdate(sql,null);
            
        }
        
        alloc.processIt(DocumentEngine.ACTION_Complete);
        PoManager.save(alloc);
    }
    
    private static BigDecimal getAvailableAmt(Properties ctx, int paymentId) throws OperationException
    {
    	String sql = "select availableAmt from RV_payment where AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+
    	" and C_Payment_id="+paymentId;
    	
    	 PreparedStatement pstmt = DB.prepareStatement(sql,null);
         
      BigDecimal availableAmt=new BigDecimal(0.0);
         try 
         {
             ResultSet rs = pstmt.executeQuery();
             while(rs.next())
             {
               availableAmt=rs.getBigDecimal(1);
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
             catch (SQLException e)
             {
                 
             }
             
         }
         
         return availableAmt;
    }
    
    
    
    public static ArrayList getInvoiceToAllocate(Properties ctx,Integer [] invoiceIds,String trxName) throws TooManyAllocateInvoiceException,CanNotCreatePaymentAllocationException,OperationException
    {
    	if(invoiceIds==null)
    	{
    		throw new NoAllocateeInvoiceException("Please select an Invoice to allocate");
    	}
        ArrayList<OpenItemBean> list=new ArrayList<OpenItemBean>();
        for (int i=0;i<invoiceIds.length;i++)
        {
        
                String sql="select op.DOCUMENTNO," +//1
                "op.C_INVOICE_ID," +//2
                "op.DATEINVOICED," +//3
                "op.NETDAYS," +//4
                "op.DUEDATE," +//5  
                "op.DAYSDUE," +//6  
                "op.DISCOUNTAMT ," +//7
                "op.PAIDAMT," +//8
                "op.OPENAMT," +//9
                "op.GRANDTOTAL," +//10
                "op.C_BPARTNER_ID"+//11
                " from RV_OPENITEM op"+
                " where op.C_INVOICE_ID="+invoiceIds[i]+
                " and op.AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+
                " order by op.DAYSDUE,op.DUEDATE asc";
                
                PreparedStatement pstmt = DB.prepareStatement(sql,null);
              
                OpenItemBean bean=null;
                try 
                {
                    ResultSet rs = pstmt.executeQuery();
                    while(rs.next())
                    {
                        bean=new OpenItemBean();
                        bean.setInvoiceNo(rs.getString(1));
                        bean.setInvoiceId(rs.getInt(2));
                        bean.setInvoiceDate(rs.getString(3));
                        bean.setNetDays(rs.getInt(4));
                        bean.setDueDate(rs.getTimestamp(5));
                        bean.setDaysDue(rs.getInt(6));
                        bean.setDiscountAmt(rs.getBigDecimal(7));
                        bean.setPaidAmt(rs.getBigDecimal(8));
                        bean.setOpenAmt(rs.getBigDecimal(9));
                        bean.setInvoiceGrandTotal(rs.getBigDecimal(10));
                        bean.setBpartnerId(rs.getInt(11));
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
                    catch (SQLException e)
                    {
                        
                    }
                    
                    }
                }
        return list;
      
    }
    
    
    public static boolean checkValidityOfAllocateAmount(Properties ctx,BigDecimal payAmt,Integer [] invoiceIds,BigDecimal [] allocatedAmt,String trxName) throws OperationException
    {
        MInvoice invoice = null;
        double totalAllocatedAmt=0.0;
        boolean valid=true;
        if(invoiceIds!=null && invoiceIds.length>0)
        {
            for(int k=0;k<invoiceIds.length;k++)
            {
                invoice = new MInvoice(ctx,invoiceIds[k],null);
                if(invoice.getOpenAmt().doubleValue() < allocatedAmt[k].doubleValue())
                {
                    valid=false;
                    throw new AllocatedAmtMoreThanOpenAmtException("invoice no = "+invoice.getDocumentNo()+" allocated amt = "+ allocatedAmt[k]+ " Open Amount = "+invoice.getOpenAmt()); 
                }
                    
                
              totalAllocatedAmt=totalAllocatedAmt+allocatedAmt[k].doubleValue();
            }
        }
        
        if(totalAllocatedAmt!=payAmt.doubleValue())
            {
                valid = false;
                throw new PayAmtNotEqualToAllocateAmtException("payment amt ="+ payAmt+" total Allocated Amt= "+totalAllocatedAmt);
            }
        
        return valid;
    }
    
    public static void allocatepaymentWithInvoices(Properties ctx,Integer [] invoiceIds,BigDecimal [] allocatedAmt,int paymentId,String trxName)
    {
        int cashLineId=0;
        MPayment payment = new MPayment(ctx,paymentId,trxName);
        MAllocationHdr alloc = new MAllocationHdr (ctx, true,  //  manual
                new Timestamp(System.currentTimeMillis()), payment.getC_Currency_ID(), Env.getContext(Env.getCtx(), "#AD_User_Name"), trxName);
        alloc.setAD_Org_ID(Env.getAD_Org_ID(ctx));
        alloc.save();
        
        MInvoice invoice = null;
        if(invoiceIds!=null && invoiceIds.length>0)
        {
          
            for(int k=0;k<invoiceIds.length;k++)
            {
                invoice = new MInvoice(ctx,invoiceIds[k],null);
                
               MAllocationLine aLine = new MAllocationLine (alloc, allocatedAmt[k], 
                        payment.getDiscountAmt(), payment.getWriteOffAmt(), payment.getWriteOffAmt());
                aLine.setDocInfo(invoice.getC_BPartner_ID(),invoice.getC_Order_ID(), invoice.get_ID());
                aLine.setPaymentInfo(payment.get_ID(), cashLineId);
                aLine.save();
            }
        }
    }
    
    
}
