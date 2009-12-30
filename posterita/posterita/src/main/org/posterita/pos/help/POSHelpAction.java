/**
 *
 * Copyright (c) 2008 Posterita. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Posterita. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Posterita.
 *
 * POSTERITA MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR
 * A PARTICULAR PURPOSE, OR NON-INFRINGEMENT. TAMAK ICT SHALL NOT
 * BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING,
 * MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 *
 * 6 Feb 2008 17:25:51 by shameem
 *
 */

package org.posterita.pos.help;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.posterita.beans.POSHelpBean;
import org.posterita.core.TmkJSPEnv;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.OperationException;
import org.posterita.form.POSHelpForm;
import org.posterita.struts.core.BaseDispatchAction;

import com.itextpdf.text.DocumentException;

public class POSHelpAction extends BaseDispatchAction
{
	public static final String INIT_CONTACT_US= "initContactUs";
    public ActionForward initContactUs(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException, ParseException, FileNotFoundException, DocumentException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        return mapping.findForward(INIT_CONTACT_US);
    }    
    
    public static final String SEND_EMAIL = "sendEmail";
    
    public ActionForward sendEmail(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException, ParseException, FileNotFoundException, DocumentException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx =TmkJSPEnv.getCtx(request);
        boolean Ok = false;
        
        POSHelpForm hf = (POSHelpForm) form;
        POSHelpBean bean = (POSHelpBean) hf.getBean();
        
		if(bean.getFromAddress().equals(""))
		{
			postGlobalError("error.required.email",request);
            return mapping.getInputForward();
		}
        
		if(bean.getSubject().equals(""))
		{
			postGlobalError("email.subject.error",request);
            return mapping.getInputForward();
		}
		
		if(bean.getTextMessage().equals(""))
		{
			postGlobalError("email.message.error",request);
            return mapping.getInputForward();
		}
		
		
        Ok = POSHelpManager.sendEmail(ctx, bean.getFromAddress(), bean.getToAddresses(), bean.getSubject(), bean.getTextMessage());
        
        if(Ok == false)
        {
        	postGlobalError("email.send.error",request);
            return mapping.getInputForward();
        }
        else
        {
        	return mapping.findForward(SEND_EMAIL);
        }
        
    }
}
