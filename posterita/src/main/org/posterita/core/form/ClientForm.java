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
* Created on Oct 30, 2006 by ashley
* 
*/

/**
	@author ashley
 */

package org.posterita.core.form;

import org.posterita.struts.core.DefaultForm;
import org.posterita.core.bean.ClientBean;

public class ClientForm extends DefaultForm
{
	private static final long serialVersionUID = 1L;

	public ClientForm()
	{
		this.setBean(new ClientBean());
		this.addRequiredFields(new String[]{"clientName", "address1", "countryId", "currencyId", "email", "orgName", "postalAddress", "confirmPassword", "password", "userPIN", "roleName", "username", "city","file"});
	}
}
