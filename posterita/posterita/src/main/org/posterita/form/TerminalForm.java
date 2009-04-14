/******************************************************************************
 *  Product: Posterita Web-Based POS and Adempiere Plugin                     *
 *  Copyright (C) 2008  Posterita Ltd                                         *
 *  This file is part of POSterita                                            *
 *                                                                            *
 *  POSterita is free software; you can redistribute it and/or modify         *
 *  it under the terms of the GNU General Public License as published by      *
 *  the Free Software Foundation; either version 2 of the License, or         *
 *  (at your option) any later version.                                       *
 *                                                                            *
 *  This program is distributed in the hope that it will be useful,           *
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of            *
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the             *
 *  GNU General Public License for more details.                              *
 *                                                                            *
 *  You should have received a copy of the GNU General Public License along   *
 *  with this program; if not, write to the Free Software Foundation, Inc.,   *
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.               *
 *****************************************************************************/
package org.posterita.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.posterita.beans.TerminalBean;
import org.posterita.struts.core.DefaultForm;
import org.posterita.struts.pos.TerminalAction;

/**
 * @author Ashley G Ramdass
 * Apr 14, 2008
 */
public class TerminalForm extends DefaultForm
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4655061866594868989L;

	public TerminalForm()
    {
        super.setBean(new TerminalBean());
    }
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
    {
        if(TerminalAction.CREATE_TERMINAL.equals(action) || TerminalAction.SAVE_TERMINAL.equals(action))
        {
            addRequiredFields(new String[]{"name", "orgId", "bpartnerId", "purchasePriceListId", "salesPriceListId", "warehouseId", "cashBookId", "cardBankAccountId", "checkBankAccountId", "cashbookTransferType"});
        }
        
        return super.validate(mapping, request);
    }
}
