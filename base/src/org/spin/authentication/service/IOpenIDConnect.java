/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2015 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Carlos Parada www.erpya.com                                *
 *****************************************************************************/
package org.spin.authentication.service;

import java.util.Map;

import org.compiere.model.MUser;
import org.spin.util.support.IAppSupport;

/**
 * interface for Authenticated With Open ID Connect
 * @author Carlos Parada, cparada@erpya.com, ERPCyA http://www.erpya.com
 */
public interface IOpenIDConnect extends IAppSupport {

	/**Get Service URL*/
	public String getServiceURL();
	
	/**Get User Info*/
	public Map<String, String > getUserInfo();
	
	/**Get User*/
	public MUser getUser();
	
	/**Validate Authentication*/
	public void validateAuthentication(String codeParameter, MUser user);
	
	/**Clear Cache Values*/
	public void clearValues();
	
}
