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


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.adempiere.model.MUserAuthentication;
import org.compiere.model.MUser;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.spin.model.MADAppRegistration;

import com.nimbusds.oauth2.sdk.AuthorizationCode;
import com.nimbusds.oauth2.sdk.AuthorizationCodeGrant;
import com.nimbusds.oauth2.sdk.AuthorizationGrant;
import com.nimbusds.oauth2.sdk.AuthorizationRequest;
import com.nimbusds.oauth2.sdk.ParseException;
import com.nimbusds.oauth2.sdk.ResponseType;
import com.nimbusds.oauth2.sdk.Scope;
import com.nimbusds.oauth2.sdk.TokenRequest;
import com.nimbusds.oauth2.sdk.TokenResponse;
import com.nimbusds.oauth2.sdk.auth.ClientAuthentication;
import com.nimbusds.oauth2.sdk.auth.ClientSecretBasic;
import com.nimbusds.oauth2.sdk.auth.Secret;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import com.nimbusds.oauth2.sdk.id.ClientID;
import com.nimbusds.oauth2.sdk.id.State;
import com.nimbusds.oauth2.sdk.token.AccessToken;
import com.nimbusds.oauth2.sdk.token.BearerAccessToken;
import com.nimbusds.oauth2.sdk.token.RefreshToken;
import com.nimbusds.openid.connect.sdk.OIDCTokenResponse;
import com.nimbusds.openid.connect.sdk.OIDCTokenResponseParser;
import com.nimbusds.openid.connect.sdk.UserInfoRequest;
import com.nimbusds.openid.connect.sdk.UserInfoResponse;
import com.nimbusds.openid.connect.sdk.claims.UserInfo;


/**
 * Authentication with Open ID Connect
 * @author Carlos Parada, cparada@erpya.com, ERPCyA http://www.erpya.com
 */
public abstract class OpenIDConnect implements IOpenIDConnect {

	private static final CLogger log = CLogger.getCLogger(OpenIDConnect.class);
	/**Application Registration*/
	private MADAppRegistration applicationRegistration = null;
	/**	Registration Id	*/
	private int registrationId = 0;
	/**Parameters Valid*/
	private Boolean isValid = null;
	/**Access Token*/
	private String accessToken = null;
	/**Refresh Token*/
	private String refreshToken = null;
	/**Authorization End Point*/
	private URI authorizationEndPoint = null;
	/**Token End Point*/
	private URI tokenEndpoint = null;
	/**Redirect URL*/
	private URI redirectURL = null;
	/**User Information End Point*/
	private URI userInfoEndpoint = null;
	/**Client Identifier*/
	private ClientID clientID = null;
	/**Scope*/
	private Scope scope = null;
	/**Response Type*/
	private ResponseType responseType = null; 
	/**Client Secret*/
	private Secret clientSecret = null;
	
	HashMap<String, String> userInfoData = new HashMap<>();
	
	
	@Override
	public String testConnection() {
		if (validateSetting())
			return "@OK@";
		
		return "";
	}
	/**
	 * Validate Setting
	 * @return
	 */
	private boolean validateSetting() {
		AtomicReference<Boolean> valid = new AtomicReference<>(true);
		OpenIDUtil.PARAMETER_TAGS.forEach(tag ->{
			Optional.ofNullable(getApplicationRegistration().getParameterValue(tag))
			.orElseGet(() -> {
				valid.set(false); 
				return ""; 
			});	
		});
		return valid.get();
	}
	
	/**
	 * Get Application Registration
	 * @return
	 */
	public MADAppRegistration getApplicationRegistration() {
		applicationRegistration =  MADAppRegistration.getById(Env.getCtx(), getAppRegistrationId(), null);
		return applicationRegistration;
	}

	/**
	 * Is Valid
	 * @return
	 */
	public boolean isValid() {
		return Optional.ofNullable(isValid).orElseGet(() -> isValid = validateSetting());
	}
	
	/**
	 * Get Redirect URI
	 * @return
	 */
	public String getServiceURL() {
		String requestURI = null;
		if (isValid()) {
			State state  = new State(OpenIDUtil.getState(applicationRegistration));
			Optional<String> maybeScope = Optional.ofNullable(getApplicationRegistration().getParameterValue(OpenIDUtil.SCOPES));
			maybeScope.ifPresent(scope -> setScope(scope.split(",")));
			AuthorizationRequest request = new AuthorizationRequest.Builder(
					getResponseType(), getClientID())
					.scope(getScope())
					.state(state)
					.redirectionURI(getRedirectURL())
					.endpointURI(getAuthorizationEndPoint())
					.build();
			
			requestURI = request.toURI().toString();
		}
		return requestURI;
	}

	public void setAppRegistrationId(int registrationId) {
		this.registrationId = registrationId;
		isValid = null;
	}

	public int getAppRegistrationId() {
		return registrationId;
	}

	/**
	 * Get Access Token
	 * @param codeParameter
	 * @return
	 */
	private void accessToken(String codeParameter) {
		Optional<String> maybeCode = Optional.ofNullable(codeParameter);
		maybeCode.ifPresent(code ->{
			try {
				AuthorizationCode authorizationCode = new AuthorizationCode(code);
				AuthorizationGrant codeGrant = new AuthorizationCodeGrant(authorizationCode, getRedirectURL());
				ClientAuthentication clientAuth = new ClientSecretBasic(getClientID(), getClientSecret());
				TokenRequest request = new TokenRequest(getTokenEndpoint(), clientAuth, codeGrant);
				TokenResponse tokenResponse = parseTokenResponse(request.toHTTPRequest().send());
				if (! tokenResponse.indicatesSuccess()) {
				    log.severe(tokenResponse.toErrorResponse().getErrorObject().toString());
				    return;
				}
				OIDCTokenResponse successResponse = (OIDCTokenResponse)tokenResponse.toSuccessResponse();
				Optional<AccessToken> maybeAccessToken = Optional.ofNullable(successResponse.getOIDCTokens().getAccessToken());
				maybeAccessToken.ifPresent(token -> accessToken = token.getValue());
				Optional<RefreshToken> maybeRefreshToken = Optional.ofNullable(successResponse.getOIDCTokens().getRefreshToken());
				maybeRefreshToken.ifPresent(token -> refreshToken = token.getValue());
			} catch (ParseException e) {
				log.severe(e.getMessage());
			} catch (IOException e) {
				log.severe(e.getMessage());
			}
		});
	}
	
	@Override
	public void validateAuthentication(String codeParameter, MUser user) {
		Optional<MUser> maybeUser = Optional.ofNullable(user);
		accessToken(codeParameter);
		maybeUser = Optional.ofNullable(maybeUser.orElse(getUser()));
		
		maybeUser.ifPresent(userLogin -> {
			MUserAuthentication.createUserAuthentication()
								.withUserId(userLogin.get_ID())
								.withAppRegistrationId(getAppRegistrationId())
								.withAccessToken(accessToken)
								.withRefreshToken(refreshToken)
								.saveEx();
		});
	}
	
	@Override
	public Map<String, String> getUserInfo() {
		if (userInfoData.isEmpty()) {
			try {
				BearerAccessToken token = new BearerAccessToken(accessToken);
				HTTPResponse httpResponse = new UserInfoRequest(getUserInfoEndpoint(), token)
											    .toHTTPRequest()
											    .send();
				UserInfoResponse userInfoResponse =  parseUserInfo(httpResponse);
				
				if (!userInfoResponse.indicatesSuccess()) {
				    log.severe(userInfoResponse.toErrorResponse().getErrorObject().toString());
				    return userInfoData;
				}
					// Extract the claims
				UserInfo userInfo = userInfoResponse.toSuccessResponse().getUserInfo();
				userInfoData = userInfo.toJSONObject()
									  .entrySet()
									  .stream()
									  .collect(Collectors.toMap(map -> Optional.ofNullable(map.getKey()).orElse("").toString(), 
											  					map -> Optional.ofNullable(map.getValue()).orElse("").toString(), 
											  					(key, value) -> key, HashMap<String, String>::new));
			} catch (IOException e) {
				log.severe(e.getMessage());
			} catch (ParseException e) {
				log.severe(e.getMessage());
			}
		}
		
		return userInfoData;
	}
	
	/**
	 * Parse Token from HttpResponse
	 * @param httpResponse
	 * @return
	 * @throws ParseException
	 */
	protected TokenResponse parseTokenResponse(HTTPResponse httpResponse) throws ParseException {
		return OIDCTokenResponseParser.parse(httpResponse);
	}

	/**
	 * Parse UserInfo from Response
	 * @param httpResponse
	 * @return
	 * @throws ParseException
	 */
	protected UserInfoResponse parseUserInfo(HTTPResponse httpResponse) throws ParseException {
		return UserInfoResponse.parse(httpResponse);
	}
	/**
	 * Get User Authenticated if Exists User
	 * @return
	 */
	public MUser getUser() {
		AtomicReference<MUser> user = new AtomicReference<MUser>(null);
		Optional<String> maybeUserEmail = Optional.ofNullable(getUserInfo().get("email"));
		maybeUserEmail.ifPresent(email ->{
			user.set(MUser.getUsers(Env.getCtx(), email)
					.stream()
					.findFirst().orElse(null));
		});
		
		return user.get();
	}
	
	/**
	 * Get Client Identifier
	 * @return
	 */
	public ClientID getClientID() {
		clientID = Optional.ofNullable(clientID).orElse(new ClientID(applicationRegistration.getParameterValue(OpenIDUtil.CLIENT_ID)));
		return clientID;
	}
	
	
	/**
	 * Get Client Secret
	 * @return
	 */
	public Secret getClientSecret() {
		clientSecret = Optional.ofNullable(clientSecret).orElse(new Secret(applicationRegistration.getParameterValue(OpenIDUtil.CLIENT_SECRET)));
		return clientSecret;
	}

	/**
	 * Get Authorization End Point
	 * @return
	 */
	public URI getAuthorizationEndPoint() {
		return authorizationEndPoint;
	}
	
	/**
	 * Set Authorization End Point
	 * @param URL
	 */
	public void setAuthorizationEndPoint(String URL) {
		Optional<String> maybeURL = Optional.ofNullable(URL);
		maybeURL.ifPresent(url ->{
				this.authorizationEndPoint = getValidURL(URL);
		});
	}

	/**
	 * Get Token End Point
	 * @return
	 */
	public URI getTokenEndpoint() {
		return tokenEndpoint;
	}
	
	/**
	 * Set Token End Point
	 * @param URL
	 */
	public void setTokenEndpoint(String URL) {
		Optional<String> maybeURL = Optional.ofNullable(URL);
		maybeURL.ifPresent(url ->{
				this.tokenEndpoint = getValidURL(URL);
		});
	}
	
	public URI getUserInfoEndpoint() {
		return userInfoEndpoint;
	}
	
	public void setUserInfoEndpoint(String URL) {
		Optional<String> maybeURL = Optional.ofNullable(URL);
		maybeURL.ifPresent(url ->{
				this.userInfoEndpoint = getValidURL(URL);
		});
	}
	

	/**
	 * Get Redirect URL
	 * @return
	 */
	public URI getRedirectURL() {
		redirectURL = Optional.ofNullable(redirectURL).orElse(getValidURL(applicationRegistration.getParameterValue(OpenIDUtil.REDIRECT_URL)));
		return redirectURL;
	}
	
	/**
	 * Get Valid URL
	 * @param URL
	 * @return
	 */
	private URI getValidURL(String URL) {
		URI validURI = null;
		try {
			validURI = new URI(URL);
		} catch (URISyntaxException e) {
			log.severe(e.getMessage());
		}
		return validURI;
	}
	
	/**
	 * Set Scope
	 * @param scope
	 */
	public void setScope(String... scope) {
		this.scope = new Scope(scope);
	}
	
	/**
	 * Get Scope
	 * @return
	 */
	public Scope getScope() {
		return scope;
	}
	
	/**
	 * Set Response Type
	 * @param responseType
	 */
	public void setResponseType(ResponseType responseType) {
		this.responseType = responseType;
	}
	
	/**
	 * Get Response Type
	 * @return
	 */
	public ResponseType getResponseType() {
		return responseType;
	}
	
	@Override
	public void clearValues() {
		accessToken =null;
		refreshToken = null;
		userInfoData.clear();
	}
}