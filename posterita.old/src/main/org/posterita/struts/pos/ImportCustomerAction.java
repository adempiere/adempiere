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
 **/

package org.posterita.struts.pos;

import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.compiere.util.Trx;

import org.posterita.Constants;
import org.posterita.businesslogic.ImportCustomerManager;
import org.posterita.core.TmkJSPEnv;
import org.posterita.core.TrxPrefix;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.BarcodeAlreadyExistsException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.ProductAlreadyExistException;
import org.posterita.struts.core.BaseDispatchAction;
import org.posterita.struts.core.DefaultForm;


public class ImportCustomerAction extends BaseDispatchAction
{
    public static final String IMPORT_POS_CUSTOMER ="importPOSCustomer";
    public ActionForward importPOSCustomer(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        DefaultForm df = (DefaultForm) form;
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        ArrayList list = new ArrayList();
      try
      {
          list=ImportCustomerManager.importCustomer(ctx, df.getFile(), trx.getTrxName());
          trx.commit();
          trx.close();
          
      }
      catch(ProductAlreadyExistException e)
      {
          trx.rollback();
          trx.close();
          postGlobalError("error.duplicate.product",request);
          return mapping.getInputForward();
          
      }
      
      catch(BarcodeAlreadyExistsException e)
      {
          trx.rollback();
          trx.close();
          postGlobalError("error.duplicate.barcode",request);
          return mapping.getInputForward();
      }
      catch(NumberFormatException e)
      {
          trx.rollback();
          trx.close();
          postGlobalError("error.invalid.number",request);
          return mapping.getInputForward();
      }
      
        
        
        request.getSession().setAttribute(Constants.CUSTOMER_CREATED,list); 
        return mapping.findForward(IMPORT_POS_CUSTOMER);
        
    }
}