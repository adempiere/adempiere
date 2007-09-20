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

package org.posterita.struts.attributes;

import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Trx;
import org.posterita.core.TmkJSPEnv;

import org.posterita.Constants;
import org.posterita.beans.AttributeValueDetailBean;
import org.posterita.businesslogic.AttributeValuesManager;
import org.posterita.core.TrxPrefix;
import org.posterita.struts.core.BaseDispatchAction;
import org.posterita.struts.core.DefaultForm;
import org.posterita.businesslogic.WBProductManager;

public class AttributesAction extends BaseDispatchAction
{
	public static final String INIT_VIEW_ATTRIBUTEVALUES = "initViewAttributeValues";
	public ActionForward initViewAttributeValues(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		ActionForward fwd = init(mapping,form,request,response);
		if(fwd!=null)
			return fwd;
		
		Properties ctx = TmkJSPEnv.getCtx(request);
		
		ArrayList<KeyNamePair> attributeList = AttributeValuesManager.getAttributes(ctx);
		
		request.getSession().setAttribute(Constants.ATTRIBUTE_LIST, attributeList);
		
		return mapping.findForward(INIT_VIEW_ATTRIBUTEVALUES);
	}
	
	public static final String LIST_ATTRIBUTEVALUES = "listAttributeValues";
	public ActionForward listAttributeValues(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		ActionForward fwd = init(mapping,form,request,response);
		if(fwd!=null)
			return fwd;
		
		Properties ctx = TmkJSPEnv.getCtx(request);
		
		DefaultForm df = (DefaultForm)form;
		
		AttributeValueDetailBean attrValBean = (AttributeValueDetailBean)df.getBean();
		
		ArrayList<KeyNamePair> attributeValuesList =null;
		if(attrValBean.getAttributeId()!=null)
		{
			attributeValuesList= AttributeValuesManager.getAttributeValues(ctx, attrValBean.getAttributeId().intValue());
			request.setAttribute(Constants.ATTRIBUTE_VALUES_LIST, attributeValuesList);
		}
		
		return mapping.findForward(LIST_ATTRIBUTEVALUES);
	} 
	
	
		
	public static final String INITEDIT_ATTRIBUTEVALUE = "initEditAttributeValue";
	public ActionForward initEditAttributeValue(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		ActionForward fwd = init(mapping,form,request,response);
		if(fwd!=null)
			return fwd;
		
		return mapping.findForward(INITEDIT_ATTRIBUTEVALUE);
	} 
	
	public static final String EDIT_ATTRIBUTEVALUE = "editAttributeValue";
	public ActionForward editAttributeValue(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		ActionForward fwd = init(mapping,form,request,response);
		if(fwd!=null)
			return fwd;
		
		Properties ctx = TmkJSPEnv.getCtx(request);
		
		DefaultForm df = (DefaultForm)form;
		
		AttributeValueDetailBean attrBean = (AttributeValueDetailBean)df.getBean();
		
		Trx trx = Trx.get(TrxPrefix.getPrefix(), true);
		trx.start();
		
		try
		{
			int newAttrValueId = AttributeValuesManager.changeAttributeValue(ctx, attrBean.getAttributeValueId().intValue(), attrBean.getNewName(), trx.getTrxName());
			WBProductManager.updateProductsAttribute(ctx, attrBean.getAttributeValueId().intValue(), newAttrValueId, attrBean.getAttributeId().intValue(), trx.getTrxName());
			trx.commit();
		}
		catch(Exception ex)
		{
			trx.rollback();
			postGlobalError("error.updating.attribute", request);
			return mapping.getInputForward();
		}
		finally
		{
			trx.close();
		}
		
		
		return mapping.findForward(EDIT_ATTRIBUTEVALUE);
	} 
	
}
