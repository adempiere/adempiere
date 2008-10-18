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
 * Created on 22-Mar-2006
 */


package org.posterita.struts.pos;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.compiere.model.MCurrency;
import org.compiere.util.Env;
import org.compiere.util.Trx;

import org.posterita.Constants;
import org.posterita.beans.CashBookDetailBean;
import org.posterita.beans.CurrentTillAmountBean;
import org.posterita.businesslogic.CashManager;
import org.posterita.businesslogic.POSManager;
import org.posterita.businesslogic.POSReportManager;
import org.posterita.businesslogic.POSTerminalManager;
import org.posterita.businesslogic.PrintManager;
import org.posterita.businesslogic.ReportManager;
import org.posterita.core.TmkJSPEnv;
import org.posterita.core.TrxPrefix;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.CanNotAjdustTillException;
import org.posterita.exceptions.CanNotCloseTillException;
import org.posterita.exceptions.NullTransferAmountException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.TransferAmountExceedsTotalAmountException;
import org.posterita.lib.UdiConstants;
import org.posterita.struts.core.BaseDispatchAction;
import org.posterita.struts.core.DefaultForm;


public class CashBookAction extends BaseDispatchAction
{
    
    public static final String GET_CASH_BOOK_DETAILS="getCashBookDetails";
    
    public ActionForward getCashBookDetails(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd=init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
                
        Properties ctx = TmkJSPEnv.getCtx(request);
        
        ArrayList list = CashManager.getCashBookDetails(ctx);
        
        request.getSession().setAttribute(Constants.CASH_BOOK,list);
        
        return mapping.findForward(GET_CASH_BOOK_DETAILS);
    }
    
    
    
    public static final String ADJUST_CASH_BOOK_ACTION="adjustCashBookAction";
    public ActionForward adjustCashBookAction(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd=init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        
       
        DefaultForm df = (DefaultForm)form;
        CashBookDetailBean bean =(CashBookDetailBean)df.getBean();
        Properties ctx = TmkJSPEnv.getCtx(request);
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        
        try
        {
        	trx.start();
            CashManager.adjustCashBook(ctx,bean,trx.getTrxName()); 
            trx.commit();
            
        }
        catch(NullTransferAmountException e)
        {
            
            trx.rollback();
            postGlobalError("error.no.transfer.amount",request); 
            return mapping.getInputForward();
            
        }
        
        catch(TransferAmountExceedsTotalAmountException e)
        {
            
            trx.rollback();
            postGlobalError("error.transferAmt.exceeds.endingBalance",request); 
            return mapping.getInputForward();
            
        }
        catch(CanNotAjdustTillException e)
        {
            
            trx.rollback();
            postGlobalError("error.cannot.adjust.till",request); 
            return mapping.getInputForward();
            
        }
        catch(NumberFormatException e)
        {
            
            trx.rollback();
            postGlobalError("error.invalid.number",request); 
            return mapping.getInputForward();
            
        }
        
        finally
        {
            trx.close();
        }
        
        ArrayList list = CashManager.getCashBookDetails(ctx); 
        request.getSession().setAttribute(Constants.CASH_BOOK,list);
        
        return mapping.findForward(ADJUST_CASH_BOOK_ACTION);
    }
    
    
    
    public static final String CLOSE_CASH_BOOK="closeCashBook";
    public ActionForward closeCashBook(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd=init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        
        
        DefaultForm df = (DefaultForm)form;
        CashBookDetailBean bean =(CashBookDetailBean)df.getBean();
        Properties ctx = TmkJSPEnv.getCtx(request);
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        
        try
        {
            trx.start();
            CashManager.CloseCashBook(ctx,bean,trx.getTrxName()); 
            trx.commit();
            
        }
        catch(NullTransferAmountException e)
        {
            
            trx.rollback();
            postGlobalError("error.no.transfer.amount",request); 
            return mapping.getInputForward();
            
        }
        
        catch(TransferAmountExceedsTotalAmountException e)
        {
            
            trx.rollback();
            postGlobalError("error.transferAmt.exceeds.endingBalance",request); 
            return mapping.getInputForward();
            
        }
        
        catch(CanNotCloseTillException e)
        {
            
            trx.rollback();
            postGlobalError("error.cannot.close.till",request); 
            return mapping.getInputForward();
            
        }
        
        catch(NumberFormatException e)
        {
            
            trx.rollback();
            postGlobalError("error.invalid.number",request); 
            return mapping.getInputForward();
            
        }
        catch(OperationException e)
        {
            trx.rollback();
            throw e;
            
            /**not thorwing the exception because, it caters for 
             * tranferring the card /cheque amt when there is no cash order*/ 
        }
        
        finally
        {
            trx.close();
        }
        
        ArrayList list = CashManager.getCashBookDetails(ctx); 
        request.getSession().setAttribute(Constants.CASH_BOOK,list);
        
        return mapping.findForward(CLOSE_CASH_BOOK);
    }
    
    
    public static final String INIT_CLOSE_TILL="initCloseTill";
    public ActionForward initCloseTill(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd=init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        Properties ctx = TmkJSPEnv.getCtx(request);
      
        CurrentTillAmountBean tillBean = POSManager.getCurrentTillAmount(ctx);
        request.setAttribute(Constants.CURRENT_TILL_AMOUNT_POS,tillBean);
        MCurrency currency = POSTerminalManager.getCurrencyOfDefaultCashBook(ctx);
        request.setAttribute(Constants.CURRENCY_SYMBOLE,currency.getCurSymbol());
        return mapping.findForward(INIT_CLOSE_TILL);
        
    }
    
    public static final String CLOSE_TILL="closeTill";
    public ActionForward closeTill(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException, SQLException
    {
        ActionForward fwd=init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        
      
        DefaultForm df = (DefaultForm)form;
        CashBookDetailBean bean =(CashBookDetailBean)df.getBean();
        if(bean.getTransferAmount()!=null)
            bean.setTransferAmount(bean.getTransferAmount().replaceAll(",",""));
        Properties ctx = TmkJSPEnv.getCtx(request);
        CurrentTillAmountBean tillBean=null;
       
        tillBean = POSManager.getCurrentTillAmount(ctx);
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        
        try
        {
        	trx.start();
            bean=CashManager.CloseTill(ctx,bean,trx.getTrxName()); 
            trx.commit();
            
        }
        catch(NullTransferAmountException e)
        {
            
            trx.rollback();
            postGlobalError("error.no.transfer.amount",request); 
            return mapping.getInputForward();
            
        }
        
       
        
        catch(CanNotCloseTillException e)
        {
            
            trx.rollback();
            postGlobalError("error.till.already.close",request); 
            return mapping.getInputForward();
            
        }
        
        catch(NumberFormatException e)
        {
            
            trx.rollback();
            postGlobalError("error.invalid.number",request); 
            return mapping.getInputForward();
            
        }
        catch(OperationException e)
        {
            trx.rollback();
            throw e;
        }
        
        finally
        {
            request.setAttribute(Constants.CURRENT_TILL_AMOUNT_POS,tillBean);
            trx.close();
        }
        
        String reportName = POSReportManager.endOfTheDayPDF(ctx,tillBean,bean);  //generates report 
        String reportURI = ReportManager.getReportURI(reportName,request); //resolves report path
        
                
        String data = POSReportManager.endOfTheDayReport(ctx,tillBean,bean);
        
        Timestamp ts = new Timestamp(System.currentTimeMillis()); 
        int posId = Env.getContextAsInt(ctx,UdiConstants.POS_ID);
        String salesData = POSReportManager.getDailySalesReport(ctx,ts,posId, null);
        
        request.getSession().setAttribute(Constants.CLOSE_TILL_PRINT_DATA, data + salesData);
        
        request.setAttribute(Constants.CURRENT_TILL_AMOUNT_POS,tillBean);
        request.setAttribute(Constants.END_OF_THE_DAY_DETAILS,bean);        
        request.setAttribute(Constants.REPORT_URL,reportURI);
        
        
        return mapping.findForward(CLOSE_TILL);
    }
    
    public static final String OPEN_CASH_DRAWER="openCashDrawer";
    
    public ActionForward openCashDrawer(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
    	ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
      
        PrintManager.openCashDrawer(ctx);

        return null;
             
    }
    
  //---------------------------------------------------------------
    public ActionForward getClosedTillData(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
    	ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        byte [] openCashDrawer= {10,27,112,48,55,1};
        String printData = (String)request.getSession().getAttribute(Constants.CLOSE_TILL_PRINT_DATA);
        
        try 
        {
			response.setContentType("application/octet-stream");
			OutputStream os = response.getOutputStream();
			os.write(openCashDrawer);
			os.write(printData.getBytes());
			os.flush();
			os.close();
		} 
        catch (IOException e) 
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        return null;
	}
}
