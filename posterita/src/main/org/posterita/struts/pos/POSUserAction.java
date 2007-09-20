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
 * Created on Aug 29, 2005 by praveen
 *
 */
package org.posterita.struts.pos;

import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.compiere.model.MCountry;
import org.compiere.util.Trx;

import org.posterita.Constants;
import org.posterita.beans.UserBean;
import org.posterita.businesslogic.RoleManager;
import org.posterita.businesslogic.UserManager;
import org.posterita.core.TmkJSPEnv;
import org.posterita.core.TrxPrefix;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.DuplicateUserPinException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.UserAlreadyExistsException;
import org.posterita.form.UserForm;
import org.posterita.keyname.RegionKeyNamePair;
import org.posterita.struts.core.DefaultForm;

public class POSUserAction extends POSDispatchAction
{
    
    public static final String CREATE_USER = "createUser";    
    public ActionForward createUser(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping,form,request,response);
		
		if (fwd!=null)
			return fwd;
		
        DefaultForm df = (DefaultForm) form;
        
        UserBean bean = (UserBean) df.getBean();
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(), true);
        
        try
        {
        	trx.start();
        	MCountry country = MCountry.getDefault(ctx);
            UserManager.createUser(ctx,bean.getUsername(),bean.getUsername(), bean.getIsSalesRep().booleanValue(),bean.getAddress1(),bean.getPostalAddress(),bean.getCity(),bean.getPassword(),bean.getEmail(),bean.getIsActive().booleanValue(),bean.getPhone(),0, bean.getRoleId().intValue(), country.get_ID(),bean.getUserPIN(),bean.getUserDiscount(),bean.getDocBasisType(),bean.getAmtMultiplier(),bean.getFrequencyType(),bean.getSubtractAmt(),bean.getIsFullAccess(),trx.getTrxName());
            trx.commit();
        }
        catch(DuplicateUserPinException e)
        {
            trx.rollback();
            postGlobalError("error.duplicate.userpin", request);
            return mapping.getInputForward();
        }
        catch(UserAlreadyExistsException e)
        {
        	trx.rollback();
            postGlobalError("error.usernameAlreadyUsed", request);
            return mapping.getInputForward();
        }
        finally
        {
        	trx.close();
        }
                
        return mapping.findForward(CREATE_USER);        
    }
    
    public static final String EDIT_USER = "editUser";
    public ActionForward editUser(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException
    {
        ActionForward fwd = init(mapping,form,request,response);
		
		if (fwd!=null)
			return fwd;
		
		DefaultForm df = (DefaultForm) form;
        
        UserBean bean = (UserBean) df.getBean();
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(), true);
        
        try
		{
        	trx.start();
			UserManager.editUser(ctx,bean.getUserId().intValue(),bean.getRoleId().intValue(),bean.getPassword(),bean.getEmail(),bean.getPhone(),bean.getAddress1(),bean.getPostalAddress(),bean.getCity(),bean.getIsSalesRep().booleanValue(),bean.getIsActive().booleanValue(),bean.getUserPIN(),bean.getUserDiscount(),bean.getDocBasisType(),bean.getAmtMultiplier(),bean.getFrequencyType(),bean.getSubtractAmt(),bean.getIsFullAccess(),trx.getTrxName());
			trx.commit();
		}
        catch(DuplicateUserPinException e)
        {
            trx.rollback();
            postGlobalError("error.duplicate.userpin", request);
            return mapping.getInputForward();
        }
		catch (OperationException e)
		{
			trx.rollback();
			postGlobalError("error.editing.user", request);
			mapping.getInputForward();
		}		
		finally
		{
			trx.close();
		}
                
        return mapping.findForward(EDIT_USER);        
    }
    
    public static final String DELETE_USER = "deleteUser";
    @SuppressWarnings("unchecked")
	public ActionForward deleteUser(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping,form,request,response);
		
		if (fwd!=null)
			return fwd;
		
		UserForm uf = (UserForm) form;
		
		Properties ctx = TmkJSPEnv.getCtx(request);			
	
		int userId =  Integer.valueOf(uf.getUserId()).intValue();
		
		UserManager.deleteUser(ctx,userId);	
		
		ArrayList list = (ArrayList) request.getSession().getAttribute(Constants.ALL_USERS);
		
		ArrayList<UserBean> newList = UserManager.updateUserListStatus(list, userId, false);
		
		request.getSession().setAttribute(Constants.ALL_USERS,newList);			
		
        return mapping.findForward(DELETE_USER);        
    }
    
    public static final String ACTIVATE_USER = "activateUser";
    @SuppressWarnings("unchecked")
	public ActionForward activateUser(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping,form,request,response);
		
		if (fwd!=null)
			return fwd;
		
		UserForm uf = (UserForm) form;
		
		Properties ctx = TmkJSPEnv.getCtx(request);			
	
		int userId =  Integer.valueOf(uf.getUserId()).intValue();
		
		UserManager.activateUser(ctx,userId);	
		
		ArrayList list = (ArrayList) request.getSession().getAttribute(Constants.ALL_USERS);
		
		ArrayList<UserBean> newList = UserManager.updateUserListStatus(list, userId, true);
		
		request.getSession().setAttribute(Constants.ALL_USERS,newList);		
		
        return mapping.findForward(ACTIVATE_USER);        
    }
    
    public static final String INIT_CREATE_USER = "initCreateUser";
    public ActionForward initCreateUser(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping,form,request,response);
		
		if (fwd!=null)
			return fwd;
						
		Properties ctx = TmkJSPEnv.getCtx(request);
		
        ArrayList roles = RoleManager.getAllRoles(ctx);
		
		ArrayList regions = RegionKeyNamePair.getKeyNamePairs(ctx);
		
		request.getSession().setAttribute(Constants.ROLES,roles);
		
		request.getSession().setAttribute(Constants.REGIONS,regions);
		
        return mapping.findForward(INIT_CREATE_USER);        
    }
    
    public static final String INIT_EDIT_USER = "initEditUser";
    public ActionForward initEditUser(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping,form,request,response);
		
		if (fwd!=null)
			return fwd;
				
		Properties ctx = TmkJSPEnv.getCtx(request);
		        
        UserForm uf = (UserForm) form;
        
        int userId = Integer.valueOf(uf.getUserId()).intValue();
        
        UserBean bean = UserManager.getUser(ctx,userId);
        
        uf.populate(bean);
        
        ArrayList roles = RoleManager.getAllRoles(ctx);
        
        request.getSession().setAttribute(Constants.ROLES,roles);
        
        request.getSession().setAttribute(Constants.USER_DETAILS,bean);
        
        return mapping.findForward(INIT_EDIT_USER);        
    }

    public static final String LIST_USERS = "listUsers";
    public ActionForward listUsers(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping,form,request,response);
		
		if (fwd!=null)
			return fwd;
				
		Properties ctx = TmkJSPEnv.getCtx(request);
		
		DefaultForm df = (DefaultForm) form;
		
		ArrayList users = new ArrayList();
		
		if (df.getFirst() != null && df.getFirst().equals("1"))
		{
			request.getSession().setAttribute(Constants.ALL_USERS,users);
			return mapping.findForward(LIST_USERS);
		}
		else
			users = UserManager.getUsers(ctx, df.getName());
		
		request.getSession().setAttribute(Constants.ALL_USERS,users);
		
        return mapping.findForward(LIST_USERS);        
    }
        
    public static final String VIEW_USER = "viewUser";
    public ActionForward viewUser(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping,form,request,response);
		
		if (fwd!=null)
			return fwd;
				
		Properties ctx = TmkJSPEnv.getCtx(request);
		
		UserForm uf = (UserForm) form;
        
        int userId = Integer.valueOf(uf.getUserId()).intValue();
        
        UserBean userBean = UserManager.getUser(ctx,userId);		
		
		request.getSession().setAttribute(Constants.USER_DETAILS,userBean);
		
        return mapping.findForward(VIEW_USER);        
    }

}
