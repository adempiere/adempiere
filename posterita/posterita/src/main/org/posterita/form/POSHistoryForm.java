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
 * Created on May 15, 2006
 */


package org.posterita.form;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.posterita.beans.POSHistoryBean;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.OperationException;
import org.posterita.struts.core.DefaultForm;
import org.posterita.struts.pos.CashAction;


public class POSHistoryForm extends DefaultForm 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public POSHistoryForm() throws OperationException
    {
        setBean(new POSHistoryBean());
        initForm();
    }
    
    private void initForm() throws OperationException
    {
    	try 
    	{
			Calendar cal = Calendar.getInstance();
			POSHistoryBean bean =  (POSHistoryBean) getBean();
			
			bean.setMonth(Integer.valueOf(cal.get(Calendar.MONTH) + 1));
			bean.setYear(Integer.valueOf(cal.get(Calendar.YEAR)));
			
			month = bean.getMonth().toString();
			year = bean.getYear().toString();
		} 
    	catch (ApplicationException e)
    	{
    		throw new OperationException(e);
    	}
    }
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
    {
        if( CashAction.GET_CASH_DETAILS_HISTORY.equals(action))
        {
            addRequiredFields(new String[] {"cashBookId"});
        }
        return super.validate(mapping, request);
    }

}
