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

package org.posterita.businesslogic;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.model.MBPartner;
import org.compiere.model.MCashLine;
import org.compiere.model.MInOut;
import org.compiere.model.MInvoice;
import org.compiere.model.MOrder;
import org.compiere.model.MPInstance;
import org.compiere.model.MPayment;
import org.compiere.process.Aging;
import org.compiere.process.DocumentEngine;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.posterita.Constants;
import org.posterita.beans.AgingBean;
import org.posterita.beans.CreditPaymentDetailsBean;
import org.posterita.beans.OpenItemBean;
import org.posterita.beans.OrderLineBean;
import org.posterita.beans.PaymentAllocationBean;
import org.posterita.beans.PaymentTypeBean;
import org.posterita.exceptions.BPartnerNotFoundException;
import org.posterita.exceptions.BPartnerOverCreditLimitException;
import org.posterita.exceptions.DiscountLimitException;
import org.posterita.exceptions.MandatoryException;
import org.posterita.exceptions.NoPaymentAmountException;
import org.posterita.exceptions.OperationException;
import org.posterita.lib.UdiConstants;
import org.posterita.model.UDIMInvoice;
import org.posterita.model.UDIMOrder;
import org.posterita.model.UDIMPayment;


public class CreditOrderManager 
{
    private static final int PROCESS_ID=238;
    public static MOrder createCreditOrder(Properties ctx,OrderLineBean bean,ArrayList cartBeanItems, String trxName) throws DiscountLimitException,BPartnerOverCreditLimitException,OperationException
    {
        bean.setTrxType(MOrder.PAYMENTRULE_OnCredit);
        MBPartner bPartner = new MBPartner(ctx,bean.getBpartnerId(),null);
        if(bPartner.getC_PaymentTerm_ID()==0)
            throw new MandatoryException("Customer does not has a Payment Term,Please  create one");
        MOrder  creditOrder=null;
        if(Boolean.parseBoolean(bean.getToBeShipped())==true)
        {
            creditOrder= POSManager.createCreditOrder(ctx,bean,cartBeanItems,trxName);
        }
        else
        {
            creditOrder= POSManager.createCreditOrderWithOutShipment(ctx,bean,cartBeanItems,trxName); 
        }
       
        return creditOrder;
        
    }
    
    
    public static MOrder completeCreditOrder(Properties ctx,int orderId) throws BPartnerOverCreditLimitException,OperationException
    {
        
        MOrder order = new MOrder(ctx,orderId,null);
        
        if(order.getC_DocTypeTarget_ID()==OrderManager.getDocTypeIDForStandardOrder(ctx))
        {
            return completeCreditOrderWithOutShipment(ctx,order);
        }
       
        else
        {
            return completeCreditOrder(ctx,order);  
        }
       
    }
    
    public static MOrder completeCreditOrderWithOutShipment(Properties ctx,MOrder order) throws BPartnerOverCreditLimitException, OperationException
    {
        MOrder completedOrder = OrderManager.completeOrder(ctx,order);
        MInvoice customerInvoice = POSManager.createCustomerInvoice(ctx, order);
        UDIMInvoice udiposInvoice = new UDIMInvoice(customerInvoice);
        udiposInvoice.processIt(DocumentEngine.ACTION_Complete);
        return completedOrder;
    }
    
    private static MOrder completeCreditOrder(Properties ctx,MOrder order) throws BPartnerOverCreditLimitException,OperationException
    {
       // POSManager.completePOSOrder2()
        MOrder completedOrder = OrderManager.completeOrder(ctx,order);
        int [] invoiceIds=InvoiceManager.getInvoiceIdsForOrder(ctx,order.get_ID(),null);
        if(invoiceIds.length<1)
            throw new OperationException("No invoice generated");
        if(invoiceIds.length>1)
            throw new OperationException("More than one invoice for the order has been generated");
        MInvoice invoice = new MInvoice(ctx,invoiceIds[0],null);
        InvoiceManager.printInvoice(ctx,invoice);
        return completedOrder;
    }
     
    
    public static PaymentTypeBean createPayment(Properties ctx,OpenItemBean bean,String trxName) throws NoPaymentAmountException,OperationException
    {
        MInvoice invoice = new MInvoice(ctx,bean.getInvoiceId(),trxName);
        MPayment payment=null;
        MCashLine cashLine=null;
        PaymentTypeBean pBean = null;
        if(bean.getPaymentAmt()==null)
            throw new NoPaymentAmountException("Payment amount can not be null");
        if(bean.getTrxType().equals(MInvoice.PAYMENTRULE_Cash))
        {
            cashLine=CashManager.createCashLineForInvoice(ctx,bean,trxName);
        }
        else
        {
            payment=PaymentManager.createARReceiptForCreditOrder(ctx,invoice,bean,trxName);
            int posId=Env.getContextAsInt(ctx, UdiConstants.POS_ID);
            payment.setUser1_ID(posId);
            UDIMPayment udiPayment = new UDIMPayment(payment);
            udiPayment.save();
        }
        
        if(cashLine!=null)
        {
            pBean=new PaymentTypeBean();
            pBean.setPaymentType("cash");
            pBean.setId(cashLine.get_ID());
            
        }
        else
        {
            pBean=new PaymentTypeBean();
            pBean.setPaymentType("Card_Cheque");
            pBean.setId(payment.get_ID());
        }
        
        return pBean;
        
    }
    
    
    public static ArrayList getOpenItems(Properties ctx,Integer bPartnerId) throws OperationException
    {
    
    	if(bPartnerId==null || BPartnerManager.isBPartnerPresent(ctx,bPartnerId,null) == false)
        {
    		throw new BPartnerNotFoundException("customer not found");
        }
    	
        String sql="select op.DOCUMENTNO," +//1
        "op.C_INVOICE_ID," +//2
        "op.C_ORDER_ID," +//3
        "op.ISSOTRX," +//4
        "op.DATEINVOICED," +//5
        "op.NETDAYS," +//6
        "op.DUEDATE," +//7
        "op.DAYSDUE," +//8
        "op.DISCOUNTDATE," +//9
        "op.DISCOUNTAMT ," +//10
        "ord.GRANDTOTAL ," +//11
        "op.PAIDAMT," +//12
        "op.OPENAMT," +//13
        "op.C_CURRENCY_ID," +//14
        "op.C_PAYMENTTERM_ID ," +//15
        "op.C_INVOICEPAYSCHEDULE_ID," +//16
        "ord.DOCUMENTNO,"+ //17
        "cr.ISO_CODE,"+ //18
        "pt.NAME,"+//19
        "bp.name|| ' ' || bp.name2,"+//20
        "op.C_BPARTNER_ID,"+//21
        //"DECODE(ord.GRANDTOTAL-nvl(sum(cl.AMOUNT),0),0,'"+Constants.PAID+"',ord.GRANDTOTAL,'"+Constants.UNPAID+"','"+Constants.PARTIALLY_PAID+"') status,"+//22
        " CASE WHEN ord.GRANDTOTAL-COALESCE(sum(cl.AMOUNT),0) = 0 THEN '"+Constants.PAID+"' WHEN ord.GRANDTOTAL-COALESCE(sum(cl.AMOUNT),0) = ord.GRANDTOTAL THEN '"+Constants.UNPAID+"' ELSE '"+Constants.PARTIALLY_PAID+"' END AS status,"+//22
        //" DECODE(sign(op.OPENAMT),-1,'"+Constants.OVER_PAID+"','NULL') sign"+//23
        " CASE WHEN sign(op.OPENAMT) = -1 THEN '"+Constants.OVER_PAID+"' ELSE 'NULL' END AS sign"+//23
        " from RV_OPENITEM op left outer join c_cashline cl on op.C_INVOICE_ID=cl.C_INVOICE_ID,C_ORDER ord,C_CURRENCY cr,C_PAYMENTTERM pt,c_bpartner bp " +
        " where op.C_ORDER_ID=ord.C_ORDER_ID"+
        " and op.C_CURRENCY_ID=cr.C_CURRENCY_ID"+
        " and ord.C_PAYMENTTERM_ID=pt.C_PAYMENTTERM_ID"+
        " and op.C_BPARTNER_ID=bp.C_BPARTNER_ID"+
        " and op.C_BPARTNER_ID="+bPartnerId+
        " and op.AD_CLIENT_ID="+Env.getAD_Client_ID(ctx);
  
        sql=sql+" group by"+
       " op.DOCUMENTNO," +//1
        "op.C_INVOICE_ID," +//2
        "op.C_ORDER_ID," +//3
        "op.ISSOTRX," +//4
        "op.DATEINVOICED," +//5
        "op.NETDAYS," +//6
        "op.DUEDATE," +//7
        "op.DAYSDUE," +//8
        "op.DISCOUNTDATE," +//9
        "op.DISCOUNTAMT ," +//10
        "ord.GRANDTOTAL ," +//11
        "op.PAIDAMT," +//12
        "op.OPENAMT," +//13
        "op.C_CURRENCY_ID," +//14
        "op.C_PAYMENTTERM_ID ," +//15
        "op.C_INVOICEPAYSCHEDULE_ID," +//16
        "ord.DOCUMENTNO,"+ //17
        "cr.ISO_CODE,"+ //18
        "pt.NAME,"+//19
        "bp.name|| ' ' || bp.name2,"+//20
        "op.C_BPARTNER_ID"+
        " order by op.DATEINVOICED desc ";
        
        PreparedStatement pstmt = DB.prepareStatement(sql,null);
      
        OpenItemBean bean=null;
        BigDecimal amt = BigDecimal.valueOf(0.0);
        
        ArrayList<OpenItemBean> list=new ArrayList<OpenItemBean>();
        try 
        {
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
                bean=new OpenItemBean();
                
                bean.setInvoiceNo(rs.getString(1));
                bean.setInvoiceId(rs.getInt(2));
                bean.setOrderId(rs.getInt(3));
                bean.setIsSoTrx(rs.getString(4));
                bean.setInvoiceDate(rs.getString(5));
                bean.setNetDays(rs.getInt(6));
                bean.setDueDate(rs.getString(7));
                bean.setDaysDue(rs.getInt(8));
                bean.setDiscountDate(rs.getString(9));
                bean.setDiscountAmt(rs.getBigDecimal(10));
                bean.setInvoiceGrandTotal(rs.getBigDecimal(11));
                amt = getNonProcessedCashAmt(ctx,rs.getInt(2));
                bean.setPaidAmt(rs.getBigDecimal(12).add(amt));
                bean.setOpenAmt(rs.getBigDecimal(13).subtract(amt));
                bean.setCurrencyId(rs.getInt(14));
                bean.setPaymentTermId(rs.getInt(15));
                bean.setInvoiceScheduleId(rs.getInt(16));
                bean.setDocumentNo(rs.getString(17));
                bean.setCurrencySymbole(rs.getString(18));
                bean.setPaymentTermName(rs.getString(19));
                bean.setPartnerName(rs.getString(20));
                bean.setBpartnerId(rs.getInt(21));
                if(rs.getString(23)==null)
                {
                	bean.setAllocationStatus(rs.getString(22));
                }
                else
                {
                	bean.setAllocationStatus(rs.getString(23));
                }
                 if (rs.getBigDecimal(12).doubleValue()>0)
                {
                	bean.setAllocationStatus(Constants.PARTIALLY_PAID);
                }
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
        return list;
    }
    
   private static BigDecimal getNonProcessedCashAmt(Properties ctx, int invoiceId) throws OperationException
   {
	  String sql = "select nvl(sum(cl.AMOUNT),0)" +
	  		" from c_cashline cl,C_CASH ca" +
	  		"  where cl.c_cash_id = ca.c_cash_id " +
	  		" and cl.c_invoice_id = " +invoiceId+
	  		"  and cl.AD_CLIENT_ID = " +Env.getAD_Client_ID(ctx)+
	  		"  and ca.docstatus = '"+DocumentEngine.STATUS_Drafted+"'";
	  
	  PreparedStatement pstmt = DB.prepareStatement(sql,null);
      ResultSet rs=null;
     BigDecimal amt = new BigDecimal(0);
      
     
      try 
      {
          rs = pstmt.executeQuery();
          
          while(rs.next())
          {
             amt=rs.getBigDecimal(1);
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
              rs.close();
              pstmt.close();
          } 
          catch (SQLException e)
          {
              
          }
          
      }
      return amt;
      
      
  }
  
   
    
    
    public static ArrayList getUnallocatedPayments(Properties ctx,int bPartnerId) throws OperationException
    {
       
    	
    	String sql="select C_PAYMENT_ID ," +//1
    			"DOCUMENTNO," +//2
    			" CREATED," +//3
    			"C_BPARTNER_ID," +//4
    			"C_INVOICE_ID," +//5
    			//"DECODE(TENDERTYPE,'K','Cheque','C','Card','Cash') tenderType," +//6
    			"CASE WHEN TENDERTYPE='K' THEN 'Cheque' WHEN TENDERTYPE= 'C' THEN 'Card' ELSE 'Cash' END AS tenderType," +//6
    			"PAYAMT," +//7
    			"DISCOUNTAMT,"+//8
    			"WRITEOFFAMT," +//9
    			"TAXAMT, " +//10
    			"OVERUNDERAMT," +//11
    			"ALLOCATEDAMT," +//12
    			"AVAILABLEAMT" + //13   			
    			" from RV_payment " +
    			" where C_BPARTNER_ID="+bPartnerId+
    			" and AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+
    			" AND DocStatus IN ('CO','CL') "+
    			" and AVAILABLEAMT>0";
        
        
        PreparedStatement pstmt = DB.prepareStatement(sql,null);
        ResultSet rs=null;
        OpenItemBean bean=null;
        
        ArrayList<OpenItemBean> list=new ArrayList<OpenItemBean>();
        try 
        {
            rs = pstmt.executeQuery();
            
            while(rs.next())
            {
                bean=new OpenItemBean();
                
                bean.setPaymentId(rs.getInt(1));
                bean.setPaymentNo(rs.getString(2));
                bean.setTrxDate(rs.getString(3));
                bean.setBpartnerId(rs.getInt(4));
                bean.setInvoiceId(rs.getInt(5));
                bean.setTenderType(rs.getString(6));
                bean.setPaymentAmt(rs.getBigDecimal(7));
                bean.setDiscountAmt(rs.getBigDecimal(8));
                bean.setWriteOffAmt(rs.getBigDecimal(9));
                bean.setTaxedAmt(rs.getBigDecimal(10));
                bean.setOverUnderPayment(rs.getBigDecimal(11));
                bean.setPaymentAllocatedAmt(rs.getBigDecimal(12));
                bean.setAvailableAmt(rs.getBigDecimal(13));
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
                rs.close();
                pstmt.close();
            } 
            catch (SQLException e)
            {
                
            }
            
        }
        return list;
        
        
    }
    
    
    public static ArrayList getAging(Properties ctx,int bPartnerId) throws OperationException
    {
        ProcessInfoParameter param[]={
                new ProcessInfoParameter("C_BPartner_ID",new BigDecimal(bPartnerId),null,null,null),
                new ProcessInfoParameter("IsSOTrx","Y",null,null,null)
        };
        
        MPInstance instance = new MPInstance(ctx,PROCESS_ID,bPartnerId);
        instance.save();
        
        ProcessInfo poInfo = new ProcessInfo("Aging",PROCESS_ID);
        poInfo.setParameter(param);
        poInfo.setAD_Process_ID(PROCESS_ID);
        poInfo.setAD_PInstance_ID(instance.get_ID());
        Aging aging = new Aging();
        boolean success= aging.startProcess(ctx, poInfo,null);
        ArrayList<AgingBean> list = new ArrayList<AgingBean>();
        if(success)
        {
            String sql="select C_CURRENCY_ID," +//1
            " DUEDATE ," +//2
            " C_BP_GROUP_ID," +//3
            " INVOICEDAMT," +//4
            " OPENAMT," +//5
            " PASTDUE91_PLUS," +//6
            " PASTDUE61_90," +//7
            " PASTDUE31_60," +//8
            " PASTDUE1_30 ," +//9
            " PASTDUE8_30," +//10
            " PASTDUE1_7," +//11
            " PASTDUEAMT," +//12
            " DUEAMT," +//13
            " DUE0," +//14
            " DUE1_7," +//15
            " DUE8_30," +//16
            " DUE0_30," +//17
            " DUE31_60," +//18
            " DUE61_90," +//19
            " DUE91_PLUS, " +//20
            " C_INVOICE_ID"+//21
            " from T_AGING"+
            " where C_BPARTNER_ID="+bPartnerId+
            " and AD_PINSTANCE_ID="+instance.get_ID(); 
            
            PreparedStatement pstmt = DB.prepareStatement(sql,null);
            ResultSet rs=null;
            
            AgingBean bean=null;
            try 
            {
                rs = pstmt.executeQuery();
                while(rs.next())
                {
                    bean=new AgingBean();
                    bean.setCurrencyId(rs.getInt(1));
                    bean.setDueDate(rs.getString(2));
                    bean.setInvoicedAmt(rs.getBigDecimal(4));
                    bean.setOpenAmt(rs.getBigDecimal(5));
                    bean.setPastDue91_plus(rs.getBigDecimal(6));
                    bean.setPastDue61_90(rs.getBigDecimal(7));
                    bean.setPastDue31_60(rs.getBigDecimal(8));
                    bean.setPastDue1_30(rs.getBigDecimal(9));
                    bean.setPastDue8_30(rs.getBigDecimal(10));
                    bean.setPastDue1_7(rs.getBigDecimal(11));
                    bean.setPastDueAmt(rs.getBigDecimal(12));
                    bean.setDueAmt(rs.getBigDecimal(13));
                    bean.setDue0(rs.getBigDecimal(14));
                    bean.setDue1_7(rs.getBigDecimal(15));
                    bean.setDue8_30(rs.getBigDecimal(16));
                    bean.setDue0_30(rs.getBigDecimal(17));
                    bean.setDue31_60(rs.getBigDecimal(18));
                    bean.setDue61_90(rs.getBigDecimal(19));
                    bean.setDue91_PLUS(rs.getBigDecimal(20));
                    bean.setInvoiceId(rs.getInt(21));
                    
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
                    rs.close();
                    pstmt.close();
                } 
                catch (SQLException e)
                {
                    throw new OperationException(e);
                }
            }
        }
        return list;
    }
    
    
    public static ArrayList getAllCashPaymentForInvoice(Properties ctx,Integer invoiceId) throws OperationException
    {
        String sql="select " +
        " C_CASHLINE_ID ," + //1
        " CREATED," + //2
        " CREATEDBY," + //3
        " C_CASH_ID," + //4
        " DESCRIPTION," + //5
        " CASHTYPE," + //6
        " C_INVOICE_ID," + //7
        " C_CURRENCY_ID," + //8
        " AMOUNT," +//9
        " DISCOUNTAMT," +//10
        " WRITEOFFAMT" +//11
        " from C_CASHLINE" +
        " where C_INVOICE_ID="+invoiceId+
        " and AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+
        " order by CREATED";
        
        ArrayList<CreditPaymentDetailsBean> list = new ArrayList<CreditPaymentDetailsBean>();
        PreparedStatement pstmt = DB.prepareStatement(sql,null);
        ResultSet rs=null;
        
        CreditPaymentDetailsBean bean=null;
        try 
        {
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                bean=new CreditPaymentDetailsBean();
                bean.setCashLineId(rs.getInt(1));
                bean.setDateCreated(rs.getTimestamp(2));
                bean.setUserID(rs.getInt(3));
                bean.setCashId(rs.getInt(4));
                bean.setDescription(rs.getString(5));
                bean.setCashType(rs.getString(6));
                bean.setInvoiceId(rs.getInt(7));
                bean.setCurrencyId(rs.getInt(8));
                bean.setPaidAmt(rs.getBigDecimal(9));
                bean.setDiscountAmt(rs.getBigDecimal(10));
                bean.setWriteOffAmt(rs.getBigDecimal(11));
                bean.setTenderType("Cash");
                
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
                rs.close();
                pstmt.close();
            } 
            catch (SQLException e)
            {
                throw new OperationException(e);
            }
        }
        
        return list;
    }
    
    public static ArrayList getAllPaymentForInvoice(Properties ctx,Integer invoiceId) throws OperationException
    {
        String sql="select C_PAYMENT_ID," + //1
        "CREATED," +//2
        "CREATEDBY," +//3
        "DOCUMENTNO," +//4
        "C_INVOICE_ID," +//5
        //"decode(TENDERTYPE,'"+MPayment.TENDERTYPE_Check+"','Cheque','" +
        "CASE WHEN TENDERTYPE='"+MPayment.TENDERTYPE_Check+"' THEN 'Cheque' " +
        //MPayment.TENDERTYPE_CreditCard+"','Card')," +//6
        "    WHEN TENDERTYPE='"+MPayment.TENDERTYPE_CreditCard+"' THEN 'Card' END ," +//6
        "CREDITCARDNUMBER," +//7
        "CHECKNO," +//8
        "PAYAMT," +//9
        "DISCOUNTAMT," +//110
        "WRITEOFFAMT," +//11
        "C_CURRENCY_ID" + //12
        " from c_payment"+
        " where C_INVOICE_ID="+invoiceId+
        " and AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+
        " order by CREATED";;
        
        ArrayList<CreditPaymentDetailsBean> list = new ArrayList<CreditPaymentDetailsBean>();
        PreparedStatement pstmt = DB.prepareStatement(sql,null);
        ResultSet rs=null;
        
        CreditPaymentDetailsBean bean=null;
        try 
        {
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                bean=new CreditPaymentDetailsBean();
                bean.setPaymentId(rs.getInt(1));
                bean.setDateCreated(rs.getTimestamp(2));
                bean.setUserID(rs.getInt(3));
                bean.setDocumentNo(rs.getString(4));
                bean.setInvoiceId(rs.getInt(5));
                bean.setTenderType(rs.getString(6));
                bean.setCreditCardNumber(rs.getString(7));
                bean.setChequeNo(rs.getString(8));
                bean.setPaidAmt(rs.getBigDecimal(9));
                bean.setDiscountAmt(rs.getBigDecimal(10));
                bean.setWriteOffAmt(rs.getBigDecimal(11));
                bean.setCurrencyId(rs.getInt(12));
                
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
    
    
    public static ArrayList viewAllocation(Properties ctx,String bpartnerName, String invoiceNumber) throws OperationException
    {
        String sql="select al.C_INVOICE_ID," +//1
        "inv.documentNo," +//2
        "al.C_BPARTNER_ID," +//3
        "al.C_ORDER_ID," +//4
        "ord.documentno," +//5
        "al.C_PAYMENT_ID," +//6
        "al.C_CASHLINE_ID," +//7
        "al.AMOUNT," +//8
        "al.DISCOUNTAMT," +//9
        "al.WRITEOFFAMT," +//10
        "bp.name||' ' ||bp.name2 ," +//11
        "al.OVERUNDERAMT, " +//12
        "al.C_ALLOCATIONHDR_ID"+//13
        " from C_ALLOCATIONLINE al,c_invoice inv,c_order ord,c_bpartner bp" +
        " where al.c_invoice_id = inv.c_invoice_id" +
        " and al.C_ORDER_ID=ord.C_ORDER_ID"+
        " and al.C_BPARTNER_ID=bp.C_BPARTNER_ID"+
        " and bp.isCustomer='Y'";
        
        if(bpartnerName!=null)
            sql=sql+ " and lower(bp.name||' ' ||bp.name2) like lower('%"+bpartnerName+"%')";
        
        if(invoiceNumber!=null)
            sql=sql+" and inv.documentNo='"+invoiceNumber+"'";
        
        ArrayList<PaymentAllocationBean> list = new ArrayList<PaymentAllocationBean>();
        PreparedStatement pstmt = DB.prepareStatement(sql,null);
        ResultSet rs=null;
        
        String invoiceNo=null;
        String documentNo=null;
        String partnerName=null;
        
        PaymentAllocationBean bean=null;
        try 
        {
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                
                bean=new PaymentAllocationBean();
                bean.setInvoiceId(rs.getInt(1));
                if(invoiceNo==null || !invoiceNo.equals(rs.getString(2))) 
                {
                    bean.setInvoiceNo(rs.getString(2));
                    invoiceNo=rs.getString(2);
                }
                bean.setBpartnerId(rs.getInt(3));
                bean.setOrderId(rs.getInt(4));
                if(documentNo==null || !documentNo.equals(rs.getString(5))) 
                {
                    bean.setDocumentNo(rs.getString(5));
                    documentNo=rs.getString(5);
                }
                
                if(rs.getInt(6)!=0)
                {
                    bean.setPaymentId(rs.getInt(6));   
                }
                
                if(rs.getInt(7)!=0)
                {
                    bean.setCashLineId(rs.getInt(7));
                }
                if(rs.getInt(6)==0 && rs.getInt(7)==0)
                {
                   getReturnedInvoice(ctx,bean,rs.getInt(1),rs.getInt(13),rs.getBigDecimal(8));
                }
                
                bean.setAmount(rs.getBigDecimal(8));
                bean.setDiscountAmt(rs.getBigDecimal(9));
                bean.setWriteOffAmt(rs.getBigDecimal(10));
                if(partnerName==null || !partnerName.equals(rs.getString(11))) 
                {
                    bean.setPartnerName(rs.getString(11));
                    partnerName=rs.getString(11);
                }
                bean.setOverUnderPayment(rs.getBigDecimal(12));
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
    
    
    private static void getReturnedInvoice(Properties ctx,PaymentAllocationBean bean,int invoiceId, int C_ALLOCATIONHDR_ID,BigDecimal amt) throws OperationException
    {
        String sql="select al.C_INVOICE_ID,inv.documentNo" +
                " from C_ALLOCATIONLINE al,c_invoice inv " +
                " where al.c_invoice_id = inv.c_invoice_id"+
                " and al.C_ALLOCATIONHDR_ID= "+C_ALLOCATIONHDR_ID+
                " and  al.C_INVOICE_ID<>"+invoiceId+
                " and al.AMOUNT="+amt.negate();
        
        PreparedStatement pstmt = DB.prepareStatement(sql,null);
        ResultSet rs=null;
        
   
        try 
        {
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                bean.setCreditMemoId(rs.getInt(1));
                bean.setCreditMemoNumber(rs.getString(2));
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
        
        
    }
    
    
    public static MInOut createAndCompleteShipment(Properties ctx,int invoiceId) throws OperationException
    {
        MInvoice invoice = new MInvoice(ctx,invoiceId,null);
        MOrder order = new MOrder(ctx,invoice.getC_Order_ID(),null);
        MInOut shipment=MinOutManager.createMInOut(ctx,invoice,order.getM_Warehouse_ID());
        MInOut completedShipment=  MinOutManager.completeShipment(ctx, shipment);
        order.setIsDelivered(true);
        UDIMOrder udiOrder = new UDIMOrder(order);
        udiOrder.save();
        return completedShipment;
    }
    
  
    
    
}


