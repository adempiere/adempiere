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
 * Created on 26-Aug-2005 by alok
 *
 */
package org.posterita.struts.attachment;

import java.io.FileNotFoundException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.compiere.util.Trx;
import org.posterita.core.TmkJSPEnv;

import org.posterita.Constants;
import org.posterita.beans.AttachmentBean;
import org.posterita.businesslogic.AttachmentManager;
import org.posterita.core.TrxPrefix;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.OperationException;
import org.posterita.struts.core.BaseDispatchAction;
import org.posterita.struts.core.DefaultForm;

public class AttachmentAction extends BaseDispatchAction
{
	public static final String CREATE_ATTACHMENT = "createAttachment";
	public ActionForward createAttachment(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
	{
		ActionForward fwd= init(mapping,form,request,response);
		if(fwd!=null)
			return fwd;
		
		Properties ctx = TmkJSPEnv.getCtx(request);
		DefaultForm df = (DefaultForm) form;
		
		AttachmentBean bean = (AttachmentBean) df.getBean();
		
		FormFile logo  = (FormFile) df.getLogo();

		Trx trx = Trx.get(Trx.createTrxName(TrxPrefix.getPrefix()), true);

		
		try
		{
			trx.start();
			AttachmentManager.attachImage(ctx,logo,bean.getLogoName());
			trx.commit();
		}
		catch (FileNotFoundException e)
		{
			trx.rollback();
			postGlobalError("error.source.file.notpresent", request);
			mapping.getInputForward();
		}
		catch (OperationException e)
		{
			trx.rollback();
			postGlobalError("error.file.save", request);
			mapping.getInputForward();
		}
		finally
		{
			trx.close();
		}

		return mapping.findForward(CREATE_ATTACHMENT);
		
	}
	
	public static final String GET_ATTACHMENT = "getAttachment";
	
	public ActionForward getAttachment(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
	{
		ActionForward fwd = init(mapping,form,request,response);
		if(fwd!=null)
			return fwd;
		
		Properties ctx = TmkJSPEnv.getCtx(request);
		
		DefaultForm df = (DefaultForm) form;
		AttachmentBean bean = (AttachmentBean) df.getBean();
		String logo = AttachmentManager.getImage(ctx,bean.getLogoName());
		
		request.getSession().setAttribute(Constants.GET_LOGO,logo);
		
		return mapping.findForward(GET_ATTACHMENT);
		
	}
	
}














