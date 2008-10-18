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
 * Created on 02-Sep-2005
 */


package org.posterita.struts.customer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.compiere.model.MBPartner;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Trx;
import org.posterita.Constants;
import org.posterita.beans.CustomerBean;
import org.posterita.beans.PriceListBean;
import org.posterita.businesslogic.CustomerCart;
import org.posterita.businesslogic.PaymentTermManager;
import org.posterita.businesslogic.administration.BarcodeManager;
import org.posterita.businesslogic.administration.CustomerManager;
import org.posterita.businesslogic.administration.PriceListManager;
import org.posterita.businesslogic.performanceanalysis.ReportManager;
import org.posterita.core.EmailValidator;
import org.posterita.core.TmkJSPEnv;
import org.posterita.core.TrxPrefix;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.BPartnerNotFoundException;
import org.posterita.exceptions.NoAccessToEditObjectException;
import org.posterita.exceptions.NoCustomerFoundException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.SurnameNotPresentException;
import org.posterita.exceptions.SystemException;
import org.posterita.form.CustomerForm;
import org.posterita.form.InitCustomerForm;
import org.posterita.struts.core.BaseDispatchAction;
import org.posterita.struts.core.DefaultForm;


public class CustomerAction extends BaseDispatchAction
{
    public static final String CREATE_CUSTOMER = "createCustomer";
    public static final String RETURN_TO_ORDER = "returnToOrder";
    
    public ActionForward createCustomer(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping,form,request,response);
        if (fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);   	    
        
        CustomerBean bean = (CustomerBean)request.getSession().getAttribute(Constants.CUSTOMER_DETAILS);
        
        String email = bean.getEmail();
        
        if( email != null )
        {
        	boolean isValid = EmailValidator.isValidEmail(email);
        	if( !isValid )
        	{
        		postGlobalError("errors.invalid","email", request);
                return mapping.getInputForward();
        	}
        }
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(), true);
        trx.start();
        try
        {
            MBPartner partner = CustomerManager.saveCustomer(ctx,bean.getBpartnerId() ,bean, trx.getTrxName());
            Integer customerId = Integer.valueOf(partner.get_ID());
            request.getSession().setAttribute(Constants.CUSTOMER_ID, customerId);
            trx.commit();
        }
        catch (SurnameNotPresentException e)
        {
        	trx.rollback();
            postGlobalError("error.required.Surname", request);
            return mapping.getInputForward();
        }
        finally
        {
        	trx.close();
        }
        
        if(bean.getCreatingFromOrder())
            return new ActionForward("RETURN_TO_ORDER");
        else 
        return mapping.findForward(CREATE_CUSTOMER);    	    
    }

    public static final String CREATE_POS_ORDER = "createPOSOrder";
    public static final String CREATE_CREDIT_ORDER = "initCreateCreditOrder";
    public static final String CREATE_POS_ORDER2 = "createPOSOrder2";
    public static final String CREATE_POS_ORDER3 = "createPOSOrder3";
    public static final String CREATE_POS_ORDER_WA = "createPOSOrderWA";
    public static final String SAVE_POS_CUSTOMER = "savePOSCustomer";
    public static final String CREATE_PAYMENT = "createPayment";
    public ActionForward savePOSCustomer(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping,form,request,response);
        if (fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request); 
        
        DefaultForm df = (DefaultForm)form;
        String creatingForOrder=(String)request.getSession().getAttribute(Constants.CREATING_FROM_ORDER);
        CustomerBean bean = (CustomerBean)df.getBean();
        bean.setRegionId(Integer.valueOf(1000001));//FIXME why do we need regionId? Is it compulsory?
        
        String email = bean.getEmail();
        
        if( email != null )
        {
        	boolean isValid = EmailValidator.isValidEmail(email);
        	if( !isValid )
        	{
        		postGlobalError("errors.invalid","email", request);
                return mapping.getInputForward();
        	}
        }
        if(bean.getBpartnerId()==null)
        {
            bean.setBpartnerId(0);
        }
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(), true);
        trx.start();
        try
        {
             MBPartner partner = CustomerManager.saveCustomer(ctx, bean.getBpartnerId(), bean, trx.getTrxName());

            request.getSession().setAttribute(Constants.CUSTOMER_ID, partner.get_ID());
            trx.commit();
        }
        catch (SurnameNotPresentException e)
        {
        	trx.rollback();
            postGlobalError("error.required.Surname", request);
            return mapping.getInputForward();
        }
        catch (NoAccessToEditObjectException e)
        {
        	trx.rollback();
            postGlobalError("error.no.edit.access", request);
            return mapping.getInputForward();
        }        
        finally
        {
        	trx.close();
        }
        
        if (creatingForOrder == null)
        	return mapping.findForward(SAVE_POS_CUSTOMER); 
        
        if (creatingForOrder.equals(Constants.POS_ORDER))
            return mapping.findForward(CREATE_POS_ORDER); 
        else
        if (creatingForOrder.equals(Constants.CREDIT_ORDER))
            return mapping.findForward(CREATE_CREDIT_ORDER); 
        else
            if (creatingForOrder.equals(Constants.CREATE_PAYMENT))
                return mapping.findForward(CREATE_PAYMENT); 
            else
        if (creatingForOrder.equals(Constants.POS_ORDER_CUSTOMER_COMPULSORY))
            return mapping.findForward(CREATE_POS_ORDER2); 
        else
        if (creatingForOrder.equals(Constants.QUICK_POS_ORDER))
            return mapping.findForward(CREATE_POS_ORDER3);       
        else
        if (creatingForOrder.equals(Constants.POS_ORDER_WITHOUT_ADVANCED))
            return mapping.findForward(CREATE_POS_ORDER_WA);         
        
        return mapping.findForward(SAVE_POS_CUSTOMER);	   	    
    }
    
    public static final String GET_EXISTING_CUSTOMERS = "getExistingCustomers";
    public ActionForward getExistingCustomers(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException, SQLException
    {
        ActionForward fwd = init(mapping,form,request,response);
        if (fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        
        ArrayList customers = CustomerManager.getAllCustomers(ctx,false);    	    
        
        request.getSession().setAttribute(Constants.ALL_CUSTOMERS, customers);
        
        return mapping.findForward(GET_EXISTING_CUSTOMERS);    		
    }
    
    
    public static final String GET_EXISTING_POS_CUSTOMERS = "getExistingPOSCustomers";
    public ActionForward getExistingPOSCustomers(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException, SQLException
    {
        ActionForward fwd = init(mapping,form,request,response);
        if (fwd!=null)
            return fwd;
        
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        
        ArrayList customers = CustomerManager.getAllCustomers(ctx,true);    	    
        
        request.getSession().setAttribute(Constants.ALL_CUSTOMERS, customers);
        
        return mapping.findForward(GET_EXISTING_POS_CUSTOMERS);    		
    }
    
    
    public static final String GET_CUSTOMER_DETAILS = "getCustomerDetails";
    public ActionForward getCustomerDetails(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        ActionForward fwd = init(mapping,form,request,response);
        if (fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        
        DefaultForm df = (DefaultForm) form;
        
        Integer partnerId = Integer.valueOf(df.getBpartnerId());
        
        CustomerBean customerBean = CustomerManager.getCustomerDetails(ctx, partnerId.intValue());
        
        request.getSession().setAttribute(Constants.CUSTOMER_DETAILS, customerBean);
        
        return mapping.findForward(GET_CUSTOMER_DETAILS);
    }    	
    
    
    public static final String INIT_CREATE_POS_CUSTOMER_ACTION = "initCreatePOSCustomer";
    public ActionForward initCreatePOSCustomer(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException, SystemException
    {
        ActionForward fwd = init(mapping,form,request,response);
        
        if(fwd!=null)
            return fwd;

        Properties ctx = TmkJSPEnv.getCtx(request);
        
        InitCustomerForm cf = (InitCustomerForm) form;
		
		CustomerBean bean = (CustomerBean) cf.getBean();
		bean.setBpartnerId(0);
        String creatingFromOrder= cf.getCreatingFromOrder();   
		cf.populate(bean);
        
       
        ArrayList list = PaymentTermManager.getAllActivePaymentTerm(ctx);
        request.getSession().setAttribute (Constants.PAYMENT_TERM,list);

        request.getSession().setAttribute(Constants.CREATING_FROM_ORDER, creatingFromOrder);
        
        return mapping.findForward(INIT_CREATE_POS_CUSTOMER_ACTION);
    }
    
    public static final String GET_ALL_CUSTOMER = "getAllPOSCustomer";
	public 	ActionForward getAllPOSCustomer(ActionMapping mapping, ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		
		ActionForward fwd=init(mapping,form,request,response);
		if (fwd!=null)
			return fwd;
		Properties ctx = TmkJSPEnv.getCtx(request);
		
		ArrayList customerList = CustomerManager.searchPOSCustomer(ctx, "", "", "","", "");

		//ArrayList customerList = CustomerManager.getAllPosCustomer(ctx);
		
		request.getSession().setAttribute(Constants.ALL_CUSTOMERS,customerList);
		
		return mapping.findForward(GET_ALL_CUSTOMER);
	}
	
	
	public static final String INIT_EDIT_POS_CUSTOMER = "initEditPOSCustomer";
	public 	ActionForward initEditPOSCustomer(ActionMapping mapping, ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		
		ActionForward fwd=init(mapping,form,request,response);
		if (fwd!=null)
			return fwd;
		Properties ctx = TmkJSPEnv.getCtx(request);
		
		CustomerForm f = (CustomerForm) form;
		f.validate(mapping,request);
		
		CustomerBean bean = (CustomerBean) f.getBean();
		Integer bpartnerId = bean.getBpartnerId();
		if(bpartnerId==null)
		{
			throw new OperationException("Cannot edit customer. Cause customer id cannot be null!");
		}	
         
		
		bean = CustomerManager.getCustomerDetails(ctx,bpartnerId);
		f.populate(bean);
        ArrayList list = PaymentTermManager.getAllActivePaymentTerm(ctx);
        ArrayList<PriceListBean> activePriceLists = PriceListManager.getPriceLists(ctx,0,"",true,null,null,true,null);
        ArrayList<KeyNamePair> priceListsKNP = PriceListManager.getKeyNamePair(ctx, activePriceLists);
        
        request.getSession().setAttribute(Constants.USER_PRICE_LISTS, priceListsKNP);
        request.getSession().setAttribute (Constants.PAYMENT_TERM,list);
		
		return mapping.findForward(INIT_EDIT_POS_CUSTOMER);
	}

	
	public static final String VIEW_POS_CUSTOMER = "viewPOSCustomer";
	public 	ActionForward viewPOSCustomer(ActionMapping mapping, ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException,Exception
	{
		
		ActionForward fwd=init(mapping,form,request,response);
		if (fwd!=null)
			return fwd;
		Properties ctx = TmkJSPEnv.getCtx(request);
		CustomerForm f = (CustomerForm) form;
		f.validate(mapping,request);
		
		CustomerBean bean = (CustomerBean) f.getBean();
		Integer bpartnerId = bean.getBpartnerId();
		if(bpartnerId==null)
		{
			throw new OperationException("Cannot load customer details. Cause customer id cannot be null!");
		}
		
		bean = CustomerManager.getCustomerDetails(ctx,bpartnerId);
		request.getSession().setAttribute(Constants.CUSTOMER_DETAILS,bean);
		
		return mapping.findForward(VIEW_POS_CUSTOMER);
	}
	
	public static final String ACTIVATE_POS_CUSTOMER = "activatePOSCustomer";
	@SuppressWarnings("unchecked")
	public 	ActionForward activatePOSCustomer(ActionMapping mapping, ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
	{
		
		ActionForward fwd=init(mapping,form,request,response);
		if (fwd!=null)
			return fwd;
		Properties ctx = TmkJSPEnv.getCtx(request);
		CustomerForm f = (CustomerForm) form;
		f.validate(mapping,request);
		
		CustomerBean bean = (CustomerBean) f.getBean();
		Integer bpartnerId = bean.getBpartnerId();
		if(bpartnerId==null)
		{
			throw new OperationException("Cannot activate customer. Cause customer id cannot be null!");
		}
		
		//since customer is a business partner we call the BPartnerManager's activate method
		
		Trx trx = Trx.get(TrxPrefix.getPrefix(), true);
		
		
		try
		{
			trx.start();
			CustomerManager.activateCustomer(ctx,bpartnerId.intValue(), trx.getTrxName());
			trx.commit();
		}
		catch (OperationException e)
		{
			trx.rollback();
			postGlobalError("error.activate.customer", request);
			mapping.getInputForward();
		}
		finally
		{
			trx.rollback();
		}
		
		ArrayList list = (ArrayList) request.getSession().getAttribute(Constants.ALL_CUSTOMERS);
		//ArrayList<CustomerBean> customerBeanList =  list;
		
		ArrayList<CustomerBean> newList = CustomerManager.updateCustomerListStatus(list, bpartnerId, true);
		
		request.getSession().setAttribute(Constants.ALL_CUSTOMERS, newList);		
		
		
		return mapping.findForward(ACTIVATE_POS_CUSTOMER);
	}
	
	public static final String DEACTIVATE_POS_CUSTOMER = "deactivatePOSCustomer";
	@SuppressWarnings("unchecked")
	public 	ActionForward deactivatePOSCustomer(ActionMapping mapping, ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
	{
		
		ActionForward fwd=init(mapping,form,request,response);
		if (fwd!=null)
			return fwd;
		Properties ctx = TmkJSPEnv.getCtx(request);
		CustomerForm f = (CustomerForm) form;
		f.validate(mapping,request);
		
		CustomerBean bean = (CustomerBean) f.getBean();
		Integer bpartnerId = bean.getBpartnerId();
		if(bpartnerId==null)
		{
			throw new OperationException("Cannot deactivate customer. Cause customer id cannot be null!");
		}
		
		
		Trx trx = Trx.get(TrxPrefix.getPrefix(), true);
		
		//since customer is a business partner we call the BPartnerManager's deactivate method
		try
		{
			trx.start();
			CustomerManager.deactivateCustomer(ctx,bpartnerId.intValue(), trx.getTrxName());
			trx.commit();
		}
		catch (OperationException e)
		{
			trx.rollback();
			postGlobalError("error.deactivate.customer", request);
			mapping.getInputForward();
		}
		finally
		{
			trx.close();
		}
		
		ArrayList list = (ArrayList) request.getSession().getAttribute(Constants.ALL_CUSTOMERS);
		//ArrayList<CustomerBean> customerBeanList =  list;
		
		ArrayList<CustomerBean> newList = CustomerManager.updateCustomerListStatus(list, bpartnerId, false);
		
		request.getSession().setAttribute(Constants.ALL_CUSTOMERS, newList);	
		
		return mapping.findForward(DEACTIVATE_POS_CUSTOMER);
	}
	
	public static final String GENERATE_CARD ="generateFidelityCard";
	public ActionForward generateFidelityCard(ActionMapping mapping, ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException, IOException, Exception
	{
		ActionForward fwd = init(mapping,form,request,response);
		if (fwd!=null)
			return fwd;
		
		Properties ctx = TmkJSPEnv.getCtx(request);
		
		ArrayList<CustomerBean> customerList =null;
		CustomerCart cart = (CustomerCart) request.getSession().getAttribute(Constants.CUSTOMER_CART);
		
        if(cart != null)
        {
        	customerList = cart.getCustomers();    	
        	
        }
        else
        {
        	customerList = new ArrayList<CustomerBean>();
        }
		
		String reportName = null;
		try 
		{
			reportName = CustomerManager.fidelityCard(ctx,customerList);
		} 
		catch (NoCustomerFoundException e) 
		{
			postGlobalError("error.print.fidelity.card",request);
			return new ActionForward("/ViewAllCartCustomers.do");
		}
		
		String uri = ReportManager.getReportURI(reportName,request);
		
		response.sendRedirect(uri);
		//ReportManager.writeReport(reportName, response);
		
		
		return null;
	}

	public static final String INIT_SEARCH_POS_CUSTOMER = "initSearchPOSCustomer";
	public ActionForward initSearchPOSCustomer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
	{
		ActionForward fwd=init(mapping,form,request,response);
		if (fwd!=null)
			return fwd;

		request.getSession().removeAttribute(Constants.ALL_CUSTOMERS);
				
		return mapping.findForward(INIT_SEARCH_POS_CUSTOMER);
	}
	
	
	public static final String SEARCH_POS_CUSTOMER = "searchPOSCustomer";
	public ActionForward searchPOSCustomer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ActionForward fwd=init(mapping,form,request,response);
		if (fwd!=null)
			return fwd;
		Properties ctx = TmkJSPEnv.getCtx(request);
		
		DefaultForm df = (DefaultForm)form;
		
		String customerName = df.getPartnerName(); 
		
		ArrayList customerList = CustomerManager.searchPOSCustomer(ctx, customerName, df.getDay(), df.getMonth(),df.getYear(), df.getIsActive());
		
		request.getSession().setAttribute(Constants.ALL_CUSTOMERS,customerList);
		
		return mapping.findForward(SEARCH_POS_CUSTOMER);
	}
	
	public static final String ADD_ALL_CUSTOMERS = "addAllCustomers";
	public ActionForward addAllCustomers(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
	{
		ActionForward fwd=init(mapping,form,request,response);
		if (fwd!=null)
			return fwd;
		
		Properties ctx = TmkJSPEnv.getCtx(request);
		
		CustomerCart cart = (CustomerCart)request.getSession().getAttribute(Constants.CUSTOMER_CART);		
		
		ArrayList<CustomerBean> customerList = (ArrayList) request.getSession().getAttribute(Constants.ALL_CUSTOMERS);
		
		if(customerList==null)
		{
			return mapping.findForward(SEARCH_POS_CUSTOMER);
		}
		
		if(cart == null)
		{
			cart = new CustomerCart(ctx);
		}
		
		for(CustomerBean bean : customerList)
		{
			Integer customerId = bean.getBpartnerId();
			if(customerId == null) continue;
			
			if(! cart.hasCustomer(customerId.intValue()))
			{
				cart.addCustomer(customerId.intValue());
			}
			
		}
		
		request.getSession().setAttribute(Constants.CUSTOMER_CART, cart);		
		return mapping.findForward(SEARCH_POS_CUSTOMER);
	}
	
//	used for create pos order to get customer name from id
	public 	ActionForward getNameByID(ActionMapping mapping, ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException, Exception
	{
		
		ActionForward fwd=init(mapping,form,request,response);
		if (fwd!=null)
			return fwd;
		Properties ctx = TmkJSPEnv.getCtx(request);
		CustomerForm f = (CustomerForm) form;
		
		String errormsg = "";		
		String bpartner = f.getBpartnerId();
		
		
		
		if(bpartner == null||bpartner.length()==0)
		{
			errormsg = "alert('Invalid Customer ID. ID cannot be empty');";
			return sendData(errormsg,response);
		}
		
		Integer bpartnerId = null;
		try 
		{
			bpartnerId = Integer.valueOf(bpartner);
		} 
		catch (NumberFormatException e2) 
		{
			errormsg = "alert('Please enter numeric value for Customer ID!');defaultCustomer();";
			return sendData(errormsg,response);
		}

		String customerName = "";
				
		try 
		{
			CustomerBean bean = CustomerManager.getCustomerDetails(ctx,bpartnerId);
			
			String firstname = (bean.getPartnerName() == null) ? "" : bean.getPartnerName();
			String lastname = (bean.getSurname() == null) ? "" : bean.getSurname();
			
			customerName = firstname + " " + lastname;
			customerName = customerName.trim();
			
			String data = "setCustomerName('"+ customerName +"');";
			
			return sendData(data,response);
		} 
		catch (BPartnerNotFoundException e1) 
		{
			errormsg = "alert('Could not find customer! Please try again with proper Customer ID');defaultCustomer();";
			return sendData(errormsg,response);
		}		
		
	}
	
	private ActionForward sendData(String data,HttpServletResponse response) throws OperationException
	{
		try 
		{
			response.setContentType("text/plain");			
			PrintWriter out = response.getWriter();
			out.print(data);
						
			out.flush();
			out.close();
		} 
		catch (IOException e) 
		{
			throw new OperationException(e);
		}
		return null;
	}
	
	public static final String ADD_TO_CART = "addToCart";
    public ActionForward addToCart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException, IOException
    {
        Properties ctx = TmkJSPEnv.getCtx(request);
        DefaultForm df = (DefaultForm) form;
        //CustomerBean bean = (CustomerBean) df.getBean();        
        
        int customerId = Integer.parseInt(df.getBpartnerId());
      
        CustomerCart cart = (CustomerCart) request.getSession().getAttribute(Constants.CUSTOMER_CART);
        
        if( cart == null )
        {
        	cart = new CustomerCart( ctx );
        }
        
        if ( ! cart.hasCustomer (customerId ) )
    	{
    		cart.addCustomer( customerId );
    	}
         
        request.getSession().setAttribute(Constants.CUSTOMER_CART, cart); 
        
        String script = "setCartCounter("+ cart.getNoOfCustomers() +");customerAdded("+ customerId +")";
        
        PrintWriter writer = response.getWriter();        
        writer.print( script );
        writer.flush();
        writer.close();
        
        return null;
    }
    
    public ActionForward clearCart (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException, IOException
    {      
        HttpSession session = request.getSession();           
        session.removeAttribute(Constants.CUSTOMER_CART);   
        
        request.getSession().getAttribute(Constants.CUSTOMER_CART); 
        
        String script = "clearAll();";        
        PrintWriter writer = response.getWriter();        
        writer.print( script );
        writer.flush();
        writer.close();
    	
    	return null;
    }   
    
    public static final String VIEW_CART = "viewCart";
    public ActionForward viewCart (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException, IOException, Exception
    {      
        HttpSession session = request.getSession();        
        CustomerCart cart = (CustomerCart) session.getAttribute(Constants.CUSTOMER_CART);        
        
        ArrayList<CustomerBean> customerList =null;
        
        if(cart != null)
        {
        	customerList = cart.getCustomers();    	
        	
        }
        else
        {
        	customerList = new ArrayList<CustomerBean>();
        }
        
        session.setAttribute(Constants.ALL_CART_CUSTOMERS, customerList);
        
    	return mapping.findForward(VIEW_CART);
    } 
    
    public static final String REMOVE = "remove";
    public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException, IOException, Exception
    {
         DefaultForm df = (DefaultForm) form;
        
        int customerId = Integer.parseInt(df.getBpartnerId());      
        CustomerCart cart = (CustomerCart) request.getSession().getAttribute(Constants.CUSTOMER_CART);
        
        if( cart != null )
        {
        	cart.removeCustomer(customerId);
        	ArrayList<CustomerBean> customerList = cart.getCustomers(); 
        	request.getSession().setAttribute(Constants.CUSTOMER_CART, cart); 
        	request.getSession().setAttribute(Constants.ALL_CART_CUSTOMERS, customerList);
        }
                
        return mapping.findForward(VIEW_CART);
    }
    
    public static final String REMOVE_FROM_CART = "removeFromCart";
    public ActionForward removeFromCart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException, IOException
    {
        Properties ctx = TmkJSPEnv.getCtx(request);
        DefaultForm df = (DefaultForm) form;
        //CustomerBean bean = (CustomerBean) df.getBean();
        
        int customerId = Integer.parseInt(df.getBpartnerId());
      
        CustomerCart cart = (CustomerCart) request.getSession().getAttribute(Constants.CUSTOMER_CART);
        
        if( cart == null )
        {
        	cart = new CustomerCart( ctx );
        }
        
        if ( cart.hasCustomer (customerId ) )
    	{
    		cart.removeCustomer( customerId );
    	}
         
        request.getSession().setAttribute(Constants.CUSTOMER_CART, cart); 
        
        String script = "setCartCounter("+ cart.getNoOfCustomers() +");customerRemoved("+ customerId +")";
        
        PrintWriter writer = response.getWriter();        
        writer.print( script );
        writer.flush();
        writer.close();
        
        return null;
    }
    
    public static final String GENERATE_CUSTOMER_BARCODE ="generateCustomerBarcode";
	public ActionForward generateCustomerBarcode(ActionMapping mapping, ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException, IOException, Exception
	{
		ActionForward fwd = init(mapping,form,request,response);
		if (fwd!=null)
			return fwd;
		
		Properties ctx = TmkJSPEnv.getCtx(request);
		
		ArrayList<CustomerBean> customerList =null;
		CustomerCart cart = (CustomerCart) request.getSession().getAttribute(Constants.CUSTOMER_CART);
		
        if(cart != null)
        {
        	customerList = cart.getCustomers();    	
        	
        }
        else
        {
        	customerList = new ArrayList<CustomerBean>();
        }
		
        Trx trx = Trx.get(TrxPrefix.getPrefix(), true);
        byte printData[] = null;
        
        String barcodeData = BarcodeManager.printCustomerBarcode(ctx, cart, trx.getTrxName());
        
        response.setContentType("application/octet-stream");
        printData = barcodeData.getBytes();
        
        OutputStream os = response.getOutputStream();
        os.write(printData);
        os.flush();
        os.close();
		
        return mapping.findForward("/POSCustomerAction.do?action=viewCart");
	}

}
