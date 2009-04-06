/*
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
 * @author Servansingh
 * */

package org.posterita.struts.user;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.posterita.Constants;
import org.posterita.beans.UserBean;
import org.posterita.core.TmkJSPEnv;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.InvalidEmailException;
import org.posterita.exceptions.OperationException;
import org.posterita.form.UserRegistrationForm;
import org.posterita.struts.core.BaseDispatchAction;
import org.posterita.util.EmailUtil;

public class UserRegistrationAction extends BaseDispatchAction
{
	public static final String INITREGISTER_USER = "initRegisterUser";
    public ActionForward initRegisterUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, FileNotFoundException, OperationException, IOException, InvalidEmailException
    {
       
    	UserBean user = (UserBean) request.getSession().getAttribute(Constants.USER_BEAN);
        
        UserRegistrationForm urf = (UserRegistrationForm) form;
        urf.validate(mapping, request);
        urf.populate(user);
		
        try
        {
        	EmailUtil.checkEMail(user.getEmail());
		}
        
        catch(InvalidEmailException ex)
        {
        	postGlobalError("errors.email", ex.getMessage(), request);
			return mapping.getInputForward();
        }
        
        return mapping.findForward(INITREGISTER_USER);
        
    }
    
    public static final String REGISTER_USER = "registerUser";
    public ActionForward registerUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, FileNotFoundException, OperationException, IOException, MessagingException, AddressException, InvalidEmailException
    {
        
    	UserRegistrationForm userForm = (UserRegistrationForm)form;
    	UserBean user = (UserBean)userForm.getBean();
    	
    	try
    	{
    		EmailUtil.checkEMail(user.getEmail());
    	}
    	
    	catch(InvalidEmailException ex)
    	{
    		postGlobalError("errors.email", ex.getMessage(), request);
			return mapping.getInputForward();
    	}
    	
    	Properties ctx = TmkJSPEnv.getCtx(request);
                
    
        boolean sent = EmailUtil.sendRegistrationEmail(ctx, user);
        
        if (!sent)
        {
        	postGlobalMessage("error.userRegistration.failed",request);
        }
       		
    	postGlobalMessage("success.userRegistration.successful",request);
    	return mapping.findForward("chooseApplication");
        
    }
}
