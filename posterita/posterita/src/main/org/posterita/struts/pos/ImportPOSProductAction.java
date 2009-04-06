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
 * Created on May 16, 2006
 */


package org.posterita.struts.pos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.posterita.Constants;
import org.posterita.beans.ProductBean;
import org.posterita.businesslogic.ImportPosProductManager;
import org.posterita.businesslogic.POSTerminalManager;
import org.posterita.businesslogic.StoreManager;
import org.posterita.businesslogic.performanceanalysis.ReportManager;
import org.posterita.core.TmkJSPEnv;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.OperationException;
import org.posterita.struts.core.BaseDispatchAction;
import org.posterita.struts.core.DefaultForm;


public class ImportPOSProductAction extends BaseDispatchAction
{
    public static final String IMPORT_POS_PRODUCT ="importPOSProducts";
    public ActionForward importPOSProducts(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException, IOException
    {
    	ActionForward fwd = init(mapping,form,request,response);
    	if (fwd!=null)
    	{
    		return fwd;
    	}

    	Properties ctx = TmkJSPEnv.getCtx(request);
    	request.getSession().removeAttribute(Constants.PRODUCT_CREATED); 
    	DefaultForm df = (DefaultForm) form;
    	ArrayList<ProductBean> list=new ArrayList<ProductBean>();
    	request.getSession().removeAttribute(Constants.IMPORT_FAIL_CSV_FILE);
    	String salesPListId = df.getSalesPriceListId();
    	String purchasePListId = df.getPurchasePriceListId();

    	int purchasePriceListId = StoreManager.getPriceListId(ctx);
    	int salesPriceListId = POSTerminalManager.getSOPriceListId(ctx);

    	if (salesPListId != null)
    	{
    		try
    		{
    			int soPriceListId = Integer.valueOf(salesPListId);
    			salesPriceListId = soPriceListId;
    		}
    		catch (NumberFormatException e)
    		{

    		}
    	}
    	if (purchasePListId != null)
    	{
    		try
    		{
    			int poPriceListId = Integer.valueOf(purchasePListId);
    			purchasePriceListId = poPriceListId;
    		}
    		catch (NumberFormatException e)
    		{
    		}
    	}
    	try
    	{
    		list=ImportPosProductManager.importSingleProducts(ctx,df.getFile(), salesPriceListId, purchasePriceListId, null);
    	}
    	catch(Exception e)
    	{
    		
    		String message = e.getMessage();
    		int index = message.indexOf("csv");
    		String filename = message.substring(0,index+3);
    		String error = message.substring(index+3);
    		postGlobalError("error.process", error, request);
    		String csvURI = ReportManager.getReportURI(filename,request); 
    		request.getSession().setAttribute(Constants.IMPORT_FAIL_CSV_FILE, csvURI);
    		return mapping.getInputForward();
    	}
    	finally
    	{
    	}

    	request.getSession().setAttribute(Constants.PRODUCT_CREATED,list); 
    	return mapping.findForward(IMPORT_POS_PRODUCT);

    }
}
