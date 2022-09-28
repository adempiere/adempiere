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
package org.spin.authentication.services.provider;


import org.spin.authentication.services.OpenIDConnect;

import com.nimbusds.oauth2.sdk.ResponseType;

/**
 * @author Carlos Parada, cparada@erpya.com, ERPCyA http://www.erpya.com
 * Authentication with Google
 */
public class GoogleAuthentication extends OpenIDConnect{
	/**Default Scope*/
	private final static String[] defaultScope = new String[]{ "openid", "email", "profile"};
	
	/**Github Authorization End Point*/
	private final static String authorizationEndPoint = "https://accounts.google.com/o/oauth2/v2/auth";
	/**Github UserInfo End Point*/
	private final static String userInfoEndPoint = "https://openidconnect.googleapis.com/v1/userinfo";
	/**Github UserInfo End Point*/
	private final static String tokenEndPoint = "https://oauth2.googleapis.com/token";
	
	public GoogleAuthentication() {
		super();
		setResponseType(new ResponseType(ResponseType.Value.CODE));
		setAuthorizationEndPoint(authorizationEndPoint);
		setUserInfoEndpoint(userInfoEndPoint);
		setTokenEndpoint(tokenEndPoint);
		setScope(defaultScope);
	}
	
}
