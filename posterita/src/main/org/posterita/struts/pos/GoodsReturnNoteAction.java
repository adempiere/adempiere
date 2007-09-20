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
 * 22-Sep-2006 11:42:25 by praveen
 *
 */

package org.posterita.struts.pos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.posterita.exceptions.ApplicationException;

public class GoodsReturnNoteAction extends POSDispatchAction
{
	public static final String CREATE_GOODS_RETURN_NOTE = "createGoodsReturnNote";	
	public static final String ADD_TO_SHOPPING_CART = "addToShoppingCart";
	public static final String INCREMENT_QTY = "incrementQty";
	public static final String DECREMENT_QTY = "decrementQty";
	public static final String DELETE_ORDERLINE = "deleteOrderline";
	
	public ActionForward addToShoppingCart(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException
	{
		ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
		return mapping.findForward(ADD_TO_SHOPPING_CART);
	}
	
	public ActionForward incrementQty(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException
	{
		ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
		return mapping.findForward(INCREMENT_QTY);
	}
	
	public ActionForward decrementQty(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException
	{
		ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
		return mapping.findForward(DECREMENT_QTY);
	}
	
	public ActionForward deleteOrderline(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException
	{
		ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
		return mapping.findForward(DELETE_ORDERLINE);
	}
}
