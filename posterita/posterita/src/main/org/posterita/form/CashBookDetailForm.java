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
 * Created on May 5, 2006
 */


package org.posterita.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.posterita.beans.CashBookDetailBean;
import org.posterita.struts.core.DefaultForm;
import org.posterita.struts.pos.CashBookAction;


public class CashBookDetailForm extends DefaultForm
{
	private static final long serialVersionUID = 1L;

	public CashBookDetailForm()
    {
        setBean(new CashBookDetailBean());
    }
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
    {
	    // Add validation to the form for cash book adjustment
	    if(CashBookAction.ADJUST_CASH_BOOK_ACTION.equals(action))
        {
            addRequiredFields(new String[]{"adjustmentAmount", "transferType", "description"});
        }
        
        return super.validate(mapping, request);
    }

}
