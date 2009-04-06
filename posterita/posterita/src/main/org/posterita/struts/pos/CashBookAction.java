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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.compiere.model.MAttachment;
import org.compiere.model.MCash;
import org.compiere.model.MCashBook;
import org.compiere.model.MCashLine;
import org.compiere.model.MCurrency;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Trx;
import org.posterita.Constants;
import org.posterita.beans.CashBookBean;
import org.posterita.beans.CashBookDetailBean;
import org.posterita.beans.CloseTillBean;
import org.posterita.beans.CurrentTillAmountBean;
import org.posterita.beans.UDIBean;
import org.posterita.businesslogic.CashManager;
import org.posterita.businesslogic.OrganisationManager;
import org.posterita.businesslogic.POSManager;
import org.posterita.businesslogic.POSTerminalManager;
import org.posterita.businesslogic.PrintManager;
import org.posterita.businesslogic.core.AccountingManager;
import org.posterita.businesslogic.performanceanalysis.POSReportManager;
import org.posterita.businesslogic.performanceanalysis.ReportManager;
import org.posterita.core.TmkJSPEnv;
import org.posterita.core.TrxPrefix;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.CanNotAjdustTillException;
import org.posterita.exceptions.CanNotCloseTillException;
import org.posterita.exceptions.CashBookAlreadyAssignedException;
import org.posterita.exceptions.NoCashJournalPresentException;
import org.posterita.exceptions.NullTransferAmountException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.TerminalLockedException;
import org.posterita.exceptions.TransferAmountExceedsTotalAmountException;
import org.posterita.form.CashBookForm;
import org.posterita.struts.core.BaseDispatchAction;
import org.posterita.struts.core.DefaultForm;
import org.posterita.util.PoManager;
import org.posterita.util.TmkPrinterConstants;


public class CashBookAction extends BaseDispatchAction
{
    /** Logger */
    private static final CLogger logger = CLogger.getCLogger(CashBookAction.class);

    public static final String GET_CASH_BOOK_DETAILS="getCashBookDetails";
    public ActionForward getCashBookDetails(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd=init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
                
        Properties ctx = TmkJSPEnv.getCtx(request);
        
        CashBookDetailBean bean = CashManager.getCashBookDetails(ctx, null);
        
        // Keeping compatibility with view
        ArrayList<CashBookDetailBean> list = new ArrayList<CashBookDetailBean>();
        list.add(bean);
        
        request.getSession().setAttribute(Constants.CASH_BOOK, list);
        
        return mapping.findForward(GET_CASH_BOOK_DETAILS);
    }
    
    
    
    public static final String ADJUST_CASH_BOOK_ACTION="adjustCashBookAction";
    public ActionForward adjustCashBookAction(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd=init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        DefaultForm df = (DefaultForm)form;
        CashBookDetailBean bean = (CashBookDetailBean)df.getBean();
        Properties ctx = TmkJSPEnv.getCtx(request);
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        MCashLine cashLine = null;
        
        try
        {
        	trx.start();
        	cashLine = CashManager.adjustCashBook(ctx, bean, trx.getTrxName()); 
            trx.commit();
        }
        catch(NullTransferAmountException e)
        {
            postGlobalError("error.no.transfer.amount",request); 
            return mapping.getInputForward();
        }
        
        catch(TransferAmountExceedsTotalAmountException e)
        {
            postGlobalError("error.transferAmt.exceeds.endingBalance",request); 
            return mapping.getInputForward();
        }
        catch(CanNotAjdustTillException e)
        {
            postGlobalError("error.cannot.adjust.till",request); 
            return mapping.getInputForward();
        }
        catch(NumberFormatException e)
        {
            postGlobalError("error.invalid.number",request); 
            return mapping.getInputForward();
        }
        finally
        {
            trx.rollback();
            trx.close();
        }
        
        // Keeping compatibility with view
        ArrayList<CashBookDetailBean> list = new ArrayList<CashBookDetailBean>();
        list.add(bean);
        
        // Printing
        if (cashLine != null)
        {
            PrintManager.printAdjustCashbookReport(ctx, cashLine);
        }
        
        request.getSession().setAttribute(Constants.CASH_BOOK,list);
        
        return mapping.findForward(ADJUST_CASH_BOOK_ACTION);
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
        MCurrency currency = POSTerminalManager.getCurrencyOfTerminalCashBook(ctx);
        request.setAttribute(Constants.CURRENCY_SYMBOLE,currency.getCurSymbol());
        return mapping.findForward(INIT_CLOSE_TILL);
        
    }
    
    public static final String CLOSE_TILL="closeTill";
    public ActionForward closeTill(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException, SQLException, IOException
    {
        ActionForward fwd=init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        DefaultForm df = (DefaultForm)form;
        CashBookDetailBean bean =(CashBookDetailBean)df.getBean();

        Properties ctx = TmkJSPEnv.getCtx(request);
        CurrentTillAmountBean tillBean=null;
       
        tillBean = POSManager.getCurrentTillAmount(ctx);
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        
        try
        {
        	trx.start();
            bean = CashManager.CloseTill(ctx, bean, trx.getTrxName()); 
            trx.commit();
        }
        catch (NoCashJournalPresentException exception)
        {
            trx.rollback();
            postGlobalError("error.no.cashjournal.present",request); 
            return mapping.getInputForward();
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
        catch (TerminalLockedException exception)
        {
            trx.rollback();
            postGlobalError("error.terminal.locked", request);
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
            trx.close();
        }
        
        String currency = POSTerminalManager.getDefaultSalesCurrency(ctx)
		.getCurSymbol();
        
        CloseTillBean closeTillBean = new CloseTillBean();
        closeTillBean.setTillName(tillBean.getPosName());
        closeTillBean.setCurrency(currency);
        closeTillBean.setBeginningBalance(bean.getBeginingBalance());
        closeTillBean.setNetCashTrx(bean.getStatementDifference());
        closeTillBean.setBalanceEntered(bean.getTransferAmount());
        closeTillBean.setDifference(bean.getDifferenceAmt());
        closeTillBean.setEndingBalance(bean.getEndingBalance());
        closeTillBean.setCashTotal(tillBean.getCashTotal());
        closeTillBean.setCardTotal(tillBean.getCardTotal());
        closeTillBean.setChequeTotal(tillBean.getChequeTotal());
        closeTillBean.setGrandTotal(tillBean.getTillGrandTotal());
        closeTillBean.setCardAmtEntered(bean.getCardTotal());
        closeTillBean.setCardDifference(bean.getCardDifference());
        closeTillBean.setChequeAmtEntered(bean.getChequeTotal());
        closeTillBean.setChequeDifference(bean.getChequeDifference()); 
        
        //save the CloseTillBean as attachment
        int cash_id = bean.getCash_id();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(closeTillBean);
        byte[] attachmentData = bos.toByteArray();
        MAttachment attachment = new MAttachment(ctx,MCash.Table_ID,cash_id,null);
        attachment.addEntry("Close Till Data", attachmentData);
        PoManager.save(attachment);
        
        String reportName = POSReportManager.endOfTheDayPDF(ctx,closeTillBean);  //generates report 
        String reportURI = ReportManager.getReportURI(reportName,request); //resolves report path
        
        request.getSession().setAttribute(Constants.END_OF_THE_DAY_DETAILS,closeTillBean); //session as being used for printing       
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
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        
        CloseTillBean bean = (CloseTillBean)request.getSession().getAttribute(Constants.END_OF_THE_DAY_DETAILS);
        
        if (bean == null)
        {
        	throw new OperationException("Till amount details not available");
        }
       
        request.getSession().setAttribute(Constants.END_OF_THE_DAY_DETAILS, null); // remove end of day details
        
        String printingType = POSManager.getPrintingTypeFromCookie(request);
        
        if (printingType == null)
        {
            printingType = TmkPrinterConstants.SLIP_PRINTER_9PIN;
        }
        
        if (TmkPrinterConstants.NORMAL_PRINTER.equalsIgnoreCase(printingType))
        {
            String reportName = POSReportManager.endOfTheDayPDF(ctx,bean);  //generates report 
            response.setContentType("application/pdf");
            ReportManager.writeReport(reportName, response);
            return null;
        } 
        else if (TmkPrinterConstants.SLIP_PRINTER_THERMAL.equals(printingType) 
                || TmkPrinterConstants.SLIP_PRINTER_9PIN.equals(printingType))
        {
            int length = TmkPrinterConstants.PRINTER_9PIN_WIDTH;
            if (TmkPrinterConstants.SLIP_PRINTER_THERMAL.equals(printingType))
            {
                length = TmkPrinterConstants.PRINTER_SLIP_WIDTH;
            }
            
            String printData = "";
            try
            {
               //Timestamp ts = new Timestamp(System.currentTimeMillis()); 
                String data = POSReportManager.endOfTheDayReport(ctx,bean);
               // int terminalId = POSTerminalManager.getTerminalId(ctx);
               // String salesData = POSReportManager.getDailySalesReport(ctx, ts, terminalId, length, null);
               // printData = data + salesData;
                printData = data;
            }
            catch (Exception ex)
            {
                printData = "Could not get data for the report";
            }
            
            try
            {
                response.setContentType("application/octet-stream");
                OutputStream os = response.getOutputStream();
                os.write(printData.getBytes());
                os.flush();
                os.close();
            }
            catch (Exception ex)
            {
                logger.log(Level.SEVERE, "Failed to write to response", ex);
                throw new OperationException(ex);
            }
        }
        else
        {
            logger.log(Level.SEVERE, "Printing Type not defined, PrintingType: " + printingType);
            return null;
        }
        return null;
	}
    
    
    //-----------------
    
    private void populateData(HttpServletRequest request, String trxName)
    {
        Properties ctx = TmkJSPEnv.getCtx(request);
        
        ArrayList<KeyNamePair> accessibleOrgs = OrganisationManager.getUserOrgPairs(ctx, false);
        request.getSession().setAttribute(Constants.ACCESSIBLE_ORGS, accessibleOrgs);
    }
    
    public static final String INIT_SEARCH_CASHBOOK = "initSearchCashBook";
    public ActionForward initSearchCashBook(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping,form,request,response);
        if(fwd != null)
            return fwd;
        
        request.getSession().setAttribute(Constants.CASHBOOKS, null);
        
        return mapping.findForward(INIT_SEARCH_CASHBOOK);
    }
    
    
    public static final String SEARCH_CASHBOOK = "searchCashBook";
    public ActionForward searchCashBook(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping,form,request,response);
        if(fwd != null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df = (DefaultForm)form;
        UDIBean bean = (UDIBean)df.getBean();
        
        String searchText = bean.getSearchText();
        
        ArrayList<CashBookBean> cashbookList = CashManager.getCashBooks(ctx, searchText, null);
        
        request.getSession().setAttribute(Constants.CASHBOOKS, cashbookList);
        
        return mapping.findForward(SEARCH_CASHBOOK);
    }
    
    public static final String VIEW_CASHBOOK = "viewCashBook";
    public ActionForward viewCashBook(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping,form,request,response);
        if(fwd != null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        CashBookForm cashBookForm = (CashBookForm)form;
        CashBookBean cashBookBean = (CashBookBean)cashBookForm.getBean();
        
        populateData(request, null);
        
        cashBookBean = CashManager.getCashBook(ctx, cashBookBean.getCashBookId(), null);
        
        cashBookForm.populate(cashBookBean);
        cashBookForm.setAction(VIEW_CASHBOOK);
        cashBookForm.setReadOnly(true);
        
        return mapping.findForward(VIEW_CASHBOOK);
    }
    
    public static final String INIT_CREATE_CASHBOOK = "initCreateCashBook";
    public ActionForward initCreateCashBook(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping,form,request,response);
        if(fwd != null)
            return fwd;
        
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        DefaultForm df = (DefaultForm)form;
        
        int defaultCurrencyId = AccountingManager.getCurrencyId(ctx, Env.getAD_Client_ID(ctx), null);
        
        CashBookBean cashBookBean = new CashBookBean();
        cashBookBean.setCurrencyId(defaultCurrencyId);
        cashBookBean.setIsDefault(false);
        cashBookBean.setIsActive(true);
        
        int orgId = Env.getAD_Org_ID(ctx);
        
        populateData(request, null);
        cashBookBean.setOrgId(orgId);
        df.populate(cashBookBean);
        df.setAction(CREATE_CASHBOOK);
        df.setReadOnly(false);
        
        return mapping.findForward(INIT_CREATE_CASHBOOK);
    }
    
    public static final String CREATE_CASHBOOK = "createCashBook";
    public ActionForward createCashBook(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping,form,request,response);
        if(fwd != null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        CashBookForm cashBookForm = (CashBookForm)form;
        CashBookBean cashBookBean = (CashBookBean)cashBookForm.getBean();
        
        Trx trx = Trx.get(Trx.createTrxName(), true);
        trx.start();
        
        try
        {
            if (cashBookBean.getCurrencyId() == null || cashBookBean.getCurrencyId() == 0)
            {
                // Needed till the ability to chose currency when creating a cash book is not enabled.
                // This is the case for the moment since there is no way to define conversion rates
                int defaultCurrencyId = AccountingManager.getCurrencyId(ctx, Env.getAD_Client_ID(ctx), null);
                cashBookBean.setCurrencyId(defaultCurrencyId);
            }
            
            MCashBook cashBook = CashManager.createUpdateCashBook(ctx, cashBookBean, trx.getTrxName());
            cashBookBean.setCashBookId(cashBook.get_ID());
            trx.commit();
        }
        catch (CashBookAlreadyAssignedException cashBookAlreadyAssignedException)
        {
            postGlobalError("error.deactivate.cashbook", request);
            return mapping.getInputForward();
        }
        catch (Exception ex)
        {
            postGlobalError("error.operation", request);
            return mapping.getInputForward();
        }
        finally
        {
            trx.rollback();
            trx.close();
        }
        
        cashBookForm.populate(cashBookBean);       
        viewCashBook(mapping, form, request, response);
        
        return mapping.findForward(CREATE_CASHBOOK);
    }
    
    public static final String EDIT_CASHBOOK = "editCashBook";
    public ActionForward editCashBook(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping,form,request,response);
        if(fwd != null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        CashBookForm cashBookForm = (CashBookForm)form;
        CashBookBean cashBookBean = (CashBookBean)cashBookForm.getBean();
        
        populateData(request, null);
        cashBookBean = CashManager.getCashBook(ctx, cashBookBean.getCashBookId(), null);
        cashBookForm.populate(cashBookBean);
        cashBookForm.setAction(SAVE_CASHBOOK);
        cashBookForm.setReadOnly(false);
        return mapping.findForward(EDIT_CASHBOOK);
    }
    
    public static final String SAVE_CASHBOOK = "saveCashBook";
    public ActionForward saveCashBook(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        return createCashBook(mapping, form, request, response);
    }
    
    public static final String COPY_CASHBOOK = "copyCashBook";
    public ActionForward copyCashBook(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping,form,request,response);
        if(fwd != null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        CashBookForm cashBookForm = (CashBookForm)form;
        CashBookBean cashBookBean = (CashBookBean)cashBookForm.getBean();
        
        populateData(request, null);
        
        cashBookBean = CashManager.getCashBook(ctx, cashBookBean.getCashBookId(), null);
        cashBookBean.setCashBookId(0);
        
        cashBookForm.populate(cashBookBean);
        cashBookForm.setAction(SAVE_CASHBOOK);
        cashBookForm.setReadOnly(false);
        
        return mapping.findForward(COPY_CASHBOOK);
    }
    
    
    public static final String ACTIVATE_CASHBOOK = "activateCashBook";
    public ActionForward activateCashBook(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping,form,request,response);
        if(fwd != null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        CashBookForm cashBookForm = (CashBookForm)form;
        CashBookBean cashBookBean = (CashBookBean)cashBookForm.getBean();
        
        Trx trx = Trx.get(Trx.createTrxName(), true);
        trx.start();
        
        try
        {
            CashManager.updateCashBookStatus(ctx, cashBookBean.getCashBookId(), true, trx.getTrxName());
            trx.commit();
        }
        catch (Exception ex)
        {
            postGlobalError("error.operation", request);
        }
        finally
        {
            trx.rollback();
            trx.close();
        }
        
        searchCashBook(mapping, form, request, response);
        
        return mapping.findForward(SEARCH_CASHBOOK);
    }
    
    
    public static final String DEACTIVATE_CASHBOOK = "deactivateCashBook";
    public ActionForward deactivateCashBook(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping,form,request,response);
        if(fwd != null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        CashBookForm cashBookForm = (CashBookForm)form;
        CashBookBean cashBookBean = (CashBookBean)cashBookForm.getBean();
        
        Trx trx = Trx.get(Trx.createTrxName(), true);
        trx.start();
        
        try
        {
            CashManager.updateCashBookStatus(ctx, cashBookBean.getCashBookId(), false, trx.getTrxName());
            trx.commit();
        }
        catch (CashBookAlreadyAssignedException cashBookAlreadyAssignedException)
        {
            postGlobalError("error.deactivate.cashbook", request);
        }
        catch (Exception ex)
        {
            postGlobalError("error.operation", request);
        }
        finally
        {
            trx.rollback();
            trx.close();
        }
        
        searchCashBook(mapping, form, request, response);
        
        return mapping.findForward(SEARCH_CASHBOOK);
    }
}
