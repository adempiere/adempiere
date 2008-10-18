/******************************************************************************
 *  Product: Posterita Web-Based POS and Adempiere Plugin                     *
 *  Copyright (C) 2008  Posterita Ltd                                         *
 *  This file is part of POSterita                                            *
 *                                                                            *
 *  POSterita is free software; you can redistribute it and/or modify         *
 *  it under the terms of the GNU General Public License as published by      *
 *  the Free Software Foundation; either version 2 of the License, or         *
 *  (at your option) any later version.                                       *
 *                                                                            *
 *  This program is distributed in the hope that it will be useful,           *
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of            *
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the             *
 *  GNU General Public License for more details.                              *
 *                                                                            *
 *  You should have received a copy of the GNU General Public License along   *
 *  with this program; if not, write to the Free Software Foundation, Inc.,   *
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.               *
 *****************************************************************************/
package org.posterita.struts.pos;

import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.compiere.model.MPOSTerminal;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Trx;
import org.posterita.Constants;
import org.posterita.beans.BankAccountBean;
import org.posterita.beans.CashBookBean;
import org.posterita.beans.PriceListBean;
import org.posterita.beans.TerminalBean;
import org.posterita.beans.UDIBean;
import org.posterita.beans.WarehouseBean;
import org.posterita.businesslogic.BankManager;
import org.posterita.businesslogic.CashManager;
import org.posterita.businesslogic.OrganisationManager;
import org.posterita.businesslogic.POSTerminalManager;
import org.posterita.businesslogic.administration.PriceListManager;
import org.posterita.businesslogic.administration.WarehouseManager;
import org.posterita.core.TmkJSPEnv;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.InvalidTerminalCashBookException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.TerminalInactivateException;
import org.posterita.form.TerminalForm;
import org.posterita.struts.core.BaseDispatchAction;
import org.posterita.struts.core.DefaultForm;

/**
 * @author Ashley G Ramdass
 * Apr 14, 2008
 */
public class TerminalAction extends BaseDispatchAction
{
    private void populateData(HttpServletRequest request, TerminalBean terminalBean, int adOrgId, String trxName) 
    throws OperationException
    {
        Properties ctx = TmkJSPEnv.getCtx(request);
        
        ArrayList<KeyNamePair> accessibleOrgs = OrganisationManager.getUserOrgPairs(ctx, false);
        request.getSession().setAttribute(Constants.ACCESSIBLE_ORGS, accessibleOrgs);
        
        ArrayList<PriceListBean> purchasePriceLists = PriceListManager.getPriceLists(ctx, 0, false, trxName);
        request.getSession().setAttribute(Constants.PURCHASE_PRICELISTS, purchasePriceLists);
        
        ArrayList<PriceListBean> salesPriceLists = PriceListManager.getPriceLists(ctx, 0, true, trxName);
        request.getSession().setAttribute(Constants.SALES_PRICELISTS, salesPriceLists);
        
        ArrayList<WarehouseBean> warehouseList = WarehouseManager.getAllWarehouses(ctx, adOrgId);
        request.getSession().setAttribute(Constants.WAREHOUSES, warehouseList);
        
        ArrayList<CashBookBean> cashBookList = CashManager.getCashBooksForTerminal(ctx, adOrgId, terminalBean.getTerminalId(), trxName);
        request.getSession().setAttribute(Constants.CASH_BOOK, cashBookList);
        
        ArrayList<CashBookBean> accessibleCashBookList = CashManager.getCashBooks(ctx, trxName);
        request.getSession().setAttribute(Constants.ACCESSIBLE_CASHBOOKS, accessibleCashBookList);
        
        ArrayList<BankAccountBean> bankAccountList = BankManager.getBankAccounts(ctx, adOrgId, trxName);
        request.getSession().setAttribute(Constants.BANK_ACCOUNTS, bankAccountList);
        
        ArrayList<BankAccountBean> accessibleBankAccountList = BankManager.getBankAccounts(ctx, trxName);
        request.getSession().setAttribute(Constants.ACCESSIBLE_BANKACCOUNTS, accessibleBankAccountList);
    }
    
    public static final String INIT_SEARCH_TERMINAL = "initSearchTerminal";
    public ActionForward initSearchTerminal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping,form,request,response);
        if(fwd != null)
            return fwd;
        
        request.getSession().setAttribute(Constants.TERMINALS, null);
        
        return mapping.findForward(INIT_SEARCH_TERMINAL);
    }
    
    public static final String SEARCH_TERMINAL = "searchTerminal";
    public ActionForward searchTerminal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping,form,request,response);
        if(fwd != null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df = (DefaultForm)form;
        UDIBean bean = (UDIBean)df.getBean();
        
        String searchText = bean.getSearchText();
        
        ArrayList<TerminalBean> terminalList = POSTerminalManager.getTerminals(ctx, searchText, null);
        
        request.setAttribute(Constants.TERMINALS, terminalList);
        
        return mapping.findForward(SEARCH_TERMINAL);
    }
    
    public static final String INIT_CREATE_TERMINAL = "initCreateTerminal";
    public ActionForward initCreateTerminal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping,form,request,response);
        if(fwd != null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        DefaultForm df = (DefaultForm)form;
        TerminalBean terminalBean = new TerminalBean();
        terminalBean.setIsActive(true);
        
        int orgId = Env.getAD_Org_ID(ctx);
        
        populateData(request, terminalBean, orgId, null);
        int warehouseId = WarehouseManager.getOrganisationWarehouse(ctx, orgId, null);
        terminalBean.setOrgId(orgId);
        terminalBean.setWarehouseId(warehouseId);
        df.populate(terminalBean);
        df.setAction(CREATE_TERMINAL);
        df.setReadOnly(false);
        
        return mapping.findForward(INIT_CREATE_TERMINAL);
    }
    
    
    public static final String CREATE_TERMINAL = "createTerminal";
    public ActionForward createTerminal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping,form,request,response);
        if(fwd != null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        TerminalForm terminalForm = (TerminalForm)form;
        TerminalBean terminalBean = (TerminalBean)terminalForm.getBean();
        
        Trx trx = Trx.get(Trx.createTrxName(), true);
        trx.start();
        
        try
        {
            MPOSTerminal terminal = POSTerminalManager.createUpdatePOSTerminal(ctx, terminalBean, trx.getTrxName());
            terminalBean.setTerminalId(terminal.get_ID());
            trx.commit();
        }
        catch (InvalidTerminalCashBookException invalidTerminalCashBookException)
        {
            postGlobalError("error.terminal.cashbook", request);
            return mapping.getInputForward();
        }
        catch (TerminalInactivateException terminalInactivateException)
        {
            postGlobalError("error.deactivate.terminal", request);
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
        
        terminalForm.populate(terminalBean);       
        viewTerminal(mapping, form, request, response);
        
        return mapping.findForward(CREATE_TERMINAL);
    }
    
    public static final String EDIT_TERMINAL = "editTerminal";
    public ActionForward editTerminal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping,form,request,response);
        if(fwd != null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        TerminalForm terminalForm = (TerminalForm)form;
        TerminalBean terminalBean = (TerminalBean)terminalForm.getBean();
        
        terminalBean = POSTerminalManager.getTerminalBean(ctx, terminalBean.getTerminalId(), null);
        populateData(request, terminalBean, terminalBean.getOrgId(), null);
        terminalForm.populate(terminalBean);
        terminalForm.setAction(SAVE_TERMINAL);
        terminalForm.setReadOnly(false);
        return mapping.findForward(EDIT_TERMINAL);
    }
    
    public static final String SAVE_TERMINAL = "saveTerminal";
    public ActionForward saveTerminal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        return createTerminal(mapping, form, request, response);
    }
    
    public static final String VIEW_TERMINAL = "viewTerminal";
    public ActionForward viewTerminal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping,form,request,response);
        if(fwd != null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        TerminalForm terminalForm = (TerminalForm)form;
        TerminalBean terminalBean = (TerminalBean)terminalForm.getBean();
        
        terminalBean = POSTerminalManager.getTerminalBean(ctx, terminalBean.getTerminalId(), null);
        
        populateData(request, terminalBean, terminalBean.getOrgId(), null);
        
        terminalForm.populate(terminalBean);
        terminalForm.setAction(VIEW_TERMINAL);
        terminalForm.setReadOnly(true);
        
        return mapping.findForward(VIEW_TERMINAL);
    }
    
    public static final String COPY_TERMINAL = "copyTerminal";
    public ActionForward copyTerminal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping,form,request,response);
        if(fwd != null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        TerminalForm terminalForm = (TerminalForm)form;
        TerminalBean terminalBean = (TerminalBean)terminalForm.getBean();
        
        terminalBean = POSTerminalManager.getTerminalBean(ctx, terminalBean.getTerminalId(), null);
        terminalBean.setTerminalId(0);
        
        populateData(request, terminalBean, Env.getAD_Org_ID(ctx), null);
        
        terminalForm.populate(terminalBean);
        terminalForm.setAction(SAVE_TERMINAL);
        terminalForm.setReadOnly(false);
        
        return mapping.findForward(COPY_TERMINAL);
    }
    
    public static final String DEACTIVATE_TERMINAL = "deactivateTerminal";
    public ActionForward deactivateTerminal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping,form,request,response);
        if(fwd != null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        TerminalForm terminalForm = (TerminalForm)form;
        TerminalBean terminalBean = (TerminalBean)terminalForm.getBean();
        
        Trx trx = Trx.get(Trx.createTrxName(), true);
        trx.start();
        
        try
        {
            terminalBean = POSTerminalManager.updateTerminalStatus(ctx, terminalBean.getTerminalId(), false, trx.getTrxName());
            trx.commit();
        }
        catch (TerminalInactivateException terminalInactivateException)
        {
            postGlobalError("error.deactivate.terminal", request);
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
        
        searchTerminal(mapping, form, request, response);
                
        return mapping.findForward(SEARCH_TERMINAL);
    }
    
    public static final String ACTIVATE_TERMINAL = "activateTerminal";
    public ActionForward activateTerminal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping,form,request,response);
        if(fwd != null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        TerminalForm terminalForm = (TerminalForm)form;
        TerminalBean terminalBean = (TerminalBean)terminalForm.getBean();
        
        Trx trx = Trx.get(Trx.createTrxName(), true);
        trx.start();
        
        try
        {
            terminalBean = POSTerminalManager.updateTerminalStatus(ctx, terminalBean.getTerminalId(), true, trx.getTrxName());
            trx.commit();
        }
        catch (InvalidTerminalCashBookException invalidTerminalCashBookException)
        {
            postGlobalError("error.terminal.cashbook", request);
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
                
        searchTerminal(mapping, form, request, response);
        
        return mapping.findForward(SEARCH_TERMINAL);
    }
    
    public static final String RESET_CURRENT_TERMINAL = "resetCurrentTerminal";
    public ActionForward resetCurrentTerminal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping,form,request,response);
        if(fwd != null)
            return fwd;
        
        POSTerminalManager.resetTerminalInCookie(response);
        searchTerminal(mapping, form, request, response);   
        postGlobalMessage("current.terminal.reset", request);
        return mapping.findForward(SEARCH_TERMINAL);
    }
    
    public static final String UPDATE_CURRENT_TERMINAL = "updateCurrentTerminal";
    public ActionForward updateCurrentTerminal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping,form,request,response);
        if(fwd != null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        TerminalForm terminalForm = (TerminalForm)form;
        TerminalBean terminalBean = (TerminalBean)terminalForm.getBean();
        
        POSTerminalManager.setTerminalInCookie(response, terminalBean.getTerminalId());
        
        searchTerminal(mapping, form, request, response);
        postGlobalMessage("current.terminal.updated", request);
        return mapping.findForward(SEARCH_TERMINAL);
    }
    
    public static final String CHANGE_ORGANISATION = "changeOrganisation";
    public ActionForward changeOrganisation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping,form,request,response);
        if(fwd != null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        TerminalForm terminalForm = (TerminalForm)form;
        TerminalBean terminalBean = (TerminalBean)terminalForm.getBean();
        
        terminalBean.setBpartnerId(0);
        terminalBean.setCashBookId(0);
        terminalBean.setCashbookTransferBankAccountId(0);
        terminalBean.setCashbookTransferCashbookId(0);
        terminalBean.setCashbookTransferType(null);
        terminalBean.setCheckBankAccountId(0);
        terminalBean.setCheckTransferBankAccountId(0);
        terminalBean.setCheckTransferCashbookId(0);
        terminalBean.setCheckTransferType(null);
        terminalBean.setCardBankAccountId(0);
        terminalBean.setCardTransferBankAccountId(0);
        terminalBean.setCardTransferCashbookId(0);
        terminalBean.setCardTransferType(null);
        terminalBean.setLastLockTime(null);
        terminalBean.setPurchasePriceListId(0);
        terminalBean.setSalesPriceListId(0);
        terminalBean.setTemplateBPartnerId(0);
        terminalBean.setWarehouseId(0);
        
        populateData(request, terminalBean, terminalBean.getOrgId(), null);
        
        terminalForm.populate(terminalBean);
        terminalForm.setAction(CREATE_TERMINAL);
        terminalForm.setReadOnly(false);
        
        return mapping.getInputForward();
    }
    
    public static final String LOCK_TERMINAL = "lockTerminal";
    public ActionForward lockTerminal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping,form,request,response);
        if(fwd != null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        TerminalForm terminalForm = (TerminalForm)form;
        TerminalBean terminalBean = (TerminalBean)terminalForm.getBean();
        
        Trx trx = Trx.get(Trx.createTrxName(), true);
        trx.start();
        
        try
        {
            POSTerminalManager.setTerminalLockStatus(ctx, terminalBean.getTerminalId(), true, trx.getTrxName());
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
        
        return viewTerminal(mapping, form, request, response);
    }
    
    public static final String UNLOCK_TERMINAL = "unlockTerminal";
    public ActionForward unlockTerminal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping,form,request,response);
        if(fwd != null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        TerminalForm terminalForm = (TerminalForm)form;
        TerminalBean terminalBean = (TerminalBean)terminalForm.getBean();
        
        Trx trx = Trx.get(Trx.createTrxName(), true);
        trx.start();
        
        try
        {
            POSTerminalManager.setTerminalLockStatus(ctx, terminalBean.getTerminalId(), false, trx.getTrxName());
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
        
        return viewTerminal(mapping, form, request, response);
    }
}
