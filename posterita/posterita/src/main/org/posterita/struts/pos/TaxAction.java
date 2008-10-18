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
 * Created on Nov 28, 2006
 */


package org.posterita.struts.pos;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.compiere.model.MTax;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.posterita.Constants;
import org.posterita.beans.TaxBean;
import org.posterita.businesslogic.administration.TaxManager;
import org.posterita.core.TmkJSPEnv;
import org.posterita.core.TrxPrefix;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.CannotInactivateTaxException;
import org.posterita.exceptions.InvalidNetDaysException;
import org.posterita.exceptions.MandatoryException;
import org.posterita.exceptions.MandatoryNameException;
import org.posterita.exceptions.NoAccessToEditObjectException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.TaxNameAlreadyExistsException;
import org.posterita.exceptions.TaxRateAlreadyExistsException;
import org.posterita.form.TaxForm;
import org.posterita.struts.core.BaseDispatchAction;
import org.posterita.struts.core.DefaultForm;


public class TaxAction extends BaseDispatchAction
{
    public static final String VIEW_ALL_TAX = "viewAllTax";
    
    public  ActionForward viewAllTax(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
       
        ArrayList taxList = TaxManager.getAllTaxRates(ctx,false);
        request.setAttribute(Constants.ALL_TAX_RATE,taxList);
        return mapping.findForward(VIEW_ALL_TAX);
    }
    
    public static final String CREATE_TAX = "createTax";
    public  ActionForward createTax(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        DefaultForm df= (DefaultForm) form;
        TaxBean bean=(TaxBean) df.getBean();
        Properties ctx=TmkJSPEnv.getCtx(request);
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
       
        try 
        {
            trx.start();
            TaxManager.createEditTaxCategoryAndTax(ctx,bean,trx.getTrxName());
            trx.commit();
        }
        catch(MandatoryNameException e)
        {
            trx.rollback();
            postGlobalError("error.name.mandatory",request);
            return mapping.getInputForward();  
        }
        catch(MandatoryException e1)
        {
            trx.rollback();
            postGlobalError("error.required.field",e1.getMessage(),request);
            return mapping.getInputForward(); 
        }
        catch(TaxNameAlreadyExistsException e3)
        {
            trx.rollback();
            postGlobalError("error.taxname.duplicate",request);
            return mapping.getInputForward();
        }
        catch(TaxRateAlreadyExistsException e4)
        {
            trx.rollback();
            postGlobalError("error.taxrate.duplicate",request);
            return mapping.getInputForward();
        }
        catch(InvalidNetDaysException e5)
        {
            trx.rollback();
            postGlobalError("error.tax.rate.nagative",request);
            return mapping.getInputForward(); 
        }
        finally
        {
            trx.close();
        }
        ArrayList taxList = TaxManager.getAllTaxRates(ctx,false);
        request.setAttribute(Constants.ALL_TAX_RATE,taxList);
        return mapping.findForward(CREATE_TAX);
    }
    
    public static final String DEACTIVATE_TAX = "deActivateTax";
    public  ActionForward deActivateTax(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        DefaultForm df= (DefaultForm) form;
        TaxBean bean=(TaxBean) df.getBean();
        Properties ctx=TmkJSPEnv.getCtx(request);
        
        try
        {
            TaxManager.activateTax(ctx,bean.getTaxId(),false);  
        }
        
        catch(CannotInactivateTaxException e)
        {
            ArrayList taxList = TaxManager.getAllTaxRates(ctx,false);
            request.setAttribute(Constants.ALL_TAX_RATE,taxList);
            postGlobalError("cannot.inactivate.tax",request);
            return mapping.getInputForward();
        }
        ArrayList taxList = TaxManager.getAllTaxRates(ctx,false);
        request.setAttribute(Constants.ALL_TAX_RATE,taxList);
       
        return mapping.findForward(DEACTIVATE_TAX);
    }
    
    
    public static final String ACTIVATE_TAX = "activateTax";
    public  ActionForward activateTax(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        DefaultForm df= (DefaultForm) form;
        TaxBean bean=(TaxBean) df.getBean();
        Properties ctx=TmkJSPEnv.getCtx(request);
        
        TaxManager.activateTax(ctx,bean.getTaxId(),true);
        ArrayList taxList = TaxManager.getAllTaxRates(ctx,false);
        request.setAttribute(Constants.ALL_TAX_RATE,taxList);
        return mapping.findForward(ACTIVATE_TAX);
    }
    
    public static final String INIT_EDIT_TAX = "initEditTax";
    public  ActionForward initEditTax(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        TaxForm tf = (TaxForm) form;
        int taxId = new Integer (tf.getTaxId()).intValue();
        Properties ctx=TmkJSPEnv.getCtx(request);
        TaxBean bean= TaxManager.getTaxRate(ctx,taxId);
        tf.populate(bean);
        request.setAttribute(Constants.EDIT_TAX,bean);
        return mapping.findForward(INIT_EDIT_TAX);
    }
    
    public static final String EDIT_TAX = "editTax";
    public  ActionForward editTax(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        DefaultForm df= (DefaultForm) form;
        TaxBean bean=(TaxBean) df.getBean();
        Properties ctx=TmkJSPEnv.getCtx(request);
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
       
        try 
        {
            trx.start();
            TaxManager.createEditTaxCategoryAndTax(ctx,bean,trx.getTrxName());
            trx.commit();
        }
        catch(MandatoryException e1)
        {
            trx.rollback();
            postGlobalError("error.required.field",e1.getMessage(),request);
            return mapping.getInputForward(); 
        }
        catch (NoAccessToEditObjectException e)
        {
        	trx.rollback();
            postGlobalError("error.no.edit.access", request);
            return mapping.getInputForward();
        }          
        catch(MandatoryNameException e2)
        {
            trx.rollback();
            postGlobalError("error.name.required",request);
            return mapping.getInputForward();
        }
        catch(InvalidNetDaysException e3)
        {
            trx.rollback();
            postGlobalError("error.tax.rate.nagative",request);
            return mapping.getInputForward(); 
        }
        catch(TaxNameAlreadyExistsException e)
        {
            trx.rollback();
            postGlobalError("error.taxname.duplicate",request);
            return mapping.getInputForward();
        }
        catch(TaxRateAlreadyExistsException e)
        {
            trx.rollback();
            postGlobalError("error.taxrate.duplicate",request);
            return mapping.getInputForward();
        }
        finally
        {
            trx.close();
        }
        ArrayList taxList = TaxManager.getAllTaxRates(ctx,false);
        request.setAttribute(Constants.ALL_TAX_RATE,taxList);
        return mapping.findForward(EDIT_TAX);
    }
    
    public static final String GET_TAX_RATE = "getTaxRate";
    public  ActionForward getTaxRate(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException, IOException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        Properties ctx=TmkJSPEnv.getCtx(request);
        Object taxCatId = request.getParameter("taxCategoryId");
        Integer taxCategoryId = 0;
        BigDecimal taxRate = Env.ZERO;
        if (taxCatId != null)
        {
        	taxCategoryId = Integer.parseInt(taxCatId.toString());
        	
        	MTax tax = TaxManager.getTaxFromCategory(ctx, taxCategoryId, null);
        	
        	taxRate = tax.getRate();
        }
        
        String res = taxRate.toString();
        PrintWriter writer = response.getWriter();        
        writer.print(res);
        writer.flush();
        writer.close();
        
        return null;
    }
}
