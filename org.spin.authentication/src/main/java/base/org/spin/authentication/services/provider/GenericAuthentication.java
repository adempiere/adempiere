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
 * Add support to login with Generic Open ID service
 */
public class GenericAuthentication extends OpenIDConnect{
	
	/**Default Scope*/
	private final static String defaultScope = "openid,email,profile";
	/**Authorization End point Tag*/
	private static final String AUTHORIZATION_ENDPOINT_TAG = "AUTHORIZATION_ENDPOINT";
	/**Token End point Tag*/
	private static final String TOKEN_ENDPOINT_TAG = "TOKEN_ENDPOINT";
	/**User Info End point Tag*/
	private static final String USERINFO_ENDPOINT_TAG = "USERINFO_ENDPOINT";
	/**Scope Parameter*/
	private static final String PARAMETER_SCOPE = "SCOPE";
	/**
	 * Constructor
	 */
	public GenericAuthentication() {
		super();
		setResponseType(new ResponseType(ResponseType.Value.CODE));
	}
	
	/***
	 * Set Default values from Application Registration 
	 */
	@Override
	public void setAppRegistrationId(int registrationId) {
		super.setAppRegistrationId(registrationId);
		Optional<MADAppRegistration> maybeApRegistration =  Optional.ofNullable(getApplicationRegistration());
		maybeApRegistration.ifPresent(appRegistration -> {
			String authorizationEndPoint = Optional.ofNullable(appRegistration.getParameterValue(AUTHORIZATION_ENDPOINT_TAG)).orElse("");
			String tokenEndPoint = Optional.ofNullable(appRegistration.getParameterValue(TOKEN_ENDPOINT_TAG)).orElse("");
			String userInfoEndPoint = Optional.ofNullable(appRegistration.getParameterValue(USERINFO_ENDPOINT_TAG)).orElse("");
			String scope = Optional.ofNullable(appRegistration.getParameterValue(PARAMETER_SCOPE)).orElse(defaultScope);
			setAuthorizationEndPoint(authorizationEndPoint);
			setTokenEndpoint(tokenEndPoint);
			setUserInfoEndpoint(userInfoEndPoint);
			setScope(scope.split(","));
		});
	}
}
