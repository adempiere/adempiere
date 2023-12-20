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


import java.util.Optional;

import org.spin.authentication.services.OpenIDConnect;
import org.spin.model.MADAppRegistration;

import com.nimbusds.oauth2.sdk.ResponseType;

/**
 * @author Carlos Parada, cparada@erpya.com, ERPCyA http://www.erpya.com
 * Authentication with Microsoft Azure
 *
 * https://learn.microsoft.com/en-us/azure/active-directory/develop/active-directory-v2-protocols
 * NOTE: These are examples. Endpoint URI format may vary based on application type,
 *       sign-in audience, and Azure cloud instance (global or national cloud).
 *       The {issuer} value in the path of the request can be used to control who can sign into the application. 
 *       The allowed values are **common** for both Microsoft accounts and work or school accounts, 
 *       **organizations** for work or school accounts only, **consumers** for Microsoft accounts only, 
 *       and **tenant identifiers** such as the tenant ID or domain name.
 */
public class MicrosoftAuthentication extends OpenIDConnect{
	/**Default Scope*/
	private final static String[] defaultScope = new String[]{ "openid", "email", "profile"};
	/**Issuer Parameter*/
	private final static String issuerParameter = "ISSUER";
	/**Default Issuer Value*/
	private final static String defaultIssuerValue = "consumers"; 
	/**Issuer URI Parameter*/
	private final static String issuerURIParameter = "<issuer>";
	/**Microsoft Azure Authorization End Point*/
	private String authorizationEndPoint = "https://login.microsoftonline.com/".concat(issuerURIParameter).concat("/oauth2/v2.0/authorize");
	/**Microsoft Azure UserInfo End Point*/
	private final static String userInfoEndPoint = "https://graph.microsoft.com/oidc/userinfo";
	/**Microsoft Azure Token End Point*/
	private String tokenEndPoint = "https://login.microsoftonline.com/".concat(issuerURIParameter).concat("/oauth2/v2.0/token");
	
	public MicrosoftAuthentication() {
		super();
		setResponseType(new ResponseType(ResponseType.Value.CODE));
		setUserInfoEndpoint(userInfoEndPoint);
		setScope(defaultScope);
	}
	
	@Override
	public void setAppRegistrationId(int registrationId) {
		super.setAppRegistrationId(registrationId);
		Optional<MADAppRegistration> maybeApRegistration =  Optional.ofNullable(getApplicationRegistration());
		maybeApRegistration.ifPresent(appRegistration -> {
			Optional<String> maybeIssuer = Optional.ofNullable(appRegistration.getParameterValue(issuerParameter));
			String issuer = maybeIssuer.orElse(defaultIssuerValue);
			authorizationEndPoint = authorizationEndPoint.replace(issuerURIParameter, issuer);
			tokenEndPoint = tokenEndPoint.replace(issuerURIParameter, issuer);
			setAuthorizationEndPoint(authorizationEndPoint);
			setTokenEndpoint(tokenEndPoint);
		});
	}
}
