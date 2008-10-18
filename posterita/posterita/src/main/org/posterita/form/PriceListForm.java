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
 * Created on Aug 19, 2005 by praveen
 *
 */
package org.posterita.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.posterita.beans.PriceListBean;
import org.posterita.struts.core.DefaultForm;
import org.posterita.struts.pos.PriceListAction;

public class PriceListForm extends DefaultForm
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PriceListForm()
    {
       setBean(new PriceListBean());
    }
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
	{
		if(PriceListAction.CREATE_OR_UPDATE_PRICE_LIST.equalsIgnoreCase(action))
		{
			addRequiredFields(new String[] {"name"}); 
		}
		
		return super.validate(mapping, request);
	}

}
