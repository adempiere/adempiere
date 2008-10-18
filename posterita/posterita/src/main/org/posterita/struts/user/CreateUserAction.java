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
 * Created on Jul 29, 2005 by din
 */

package org.posterita.struts.user;

import java.io.FileNotFoundException;
import java.io.IOException;
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
import org.posterita.businesslogic.administration.RoleManager;
import org.posterita.businesslogic.administration.UserManager;
import org.posterita.core.TmkJSPEnv;
import org.posterita.core.TrxPrefix;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.DuplicateUserPinException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.UserAlreadyExistsException;
import org.posterita.keyname.RegionKeyNamePair;
import org.posterita.struts.core.BaseDispatchAction;
import org.posterita.struts.core.DefaultForm;

public class CreateUserAction extends BaseDispatchAction
{
    public static final String CREATE_USERS = "createUser";
    public ActionForward createUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, FileNotFoundException, OperationException, IOException
    {
        
        DefaultForm df = (DefaultForm) form;
        
        UserBean bean = (UserBean) df.getBean();
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(), true);
        trx.start();
        try
        {
            MCountry country = MCountry.getDefault(ctx);
            UserManager.createUser(ctx,bean.getOrgId(), bean.getUsername(),"",bean.getIsSalesRep().booleanValue(),bean.getAddress1(),bean.getPostalAddress(),bean.getCity(),bean.getPassword(),bean.getEmail(),bean.getIsActive().booleanValue(),bean.getPhone(),bean.getRegionId().intValue(), bean.getRoleId().intValue(),country.get_ID(),bean.getUserPIN(),bean.getUserDiscount(),bean.getDocBasisType(),bean.getAmtMultiplier(),bean.getFrequencyType(),bean.getSubtractAmt(),bean.getIsFullAccess(),trx.getTrxName());
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
        
        request.getSession().setAttribute(Constants.USER_DETAILS,bean);
        
        return mapping.findForward(CREATE_USERS);
        
    }
    
    public static final String INIT_USERS = "initUser";
    public ActionForward initUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, FileNotFoundException, OperationException, IOException
    {
        ActionForward fwd = init(mapping,form,request,response);
        if (fwd!=null)
            return fwd;
        
        //DefaultForm df = (DefaultForm) form;
        
       // UDIBean bean = (UDIBean) df.getBean();
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        
        ArrayList roles = RoleManager.getAllRoles(ctx);
        ArrayList regions = RegionKeyNamePair.getKeyNamePairs(ctx);
        
        request.getSession().setAttribute(Constants.ROLES, roles);
        request.getSession().setAttribute(Constants.REGIONS, regions);
        
        return mapping.findForward(INIT_USERS);
    }
}
