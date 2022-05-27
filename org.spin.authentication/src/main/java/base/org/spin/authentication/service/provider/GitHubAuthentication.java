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
package org.spin.authentication.service.provider;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import org.spin.authentication.service.OpenIDConnect;
import org.spin.authentication.service.OpenIDUtil;

import com.nimbusds.oauth2.sdk.ParseException;
import com.nimbusds.oauth2.sdk.ResponseType;
import com.nimbusds.oauth2.sdk.TokenErrorResponse;
import com.nimbusds.oauth2.sdk.TokenResponse;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import com.nimbusds.openid.connect.sdk.OIDCTokenResponse;
import com.nimbusds.openid.connect.sdk.UserInfoErrorResponse;
import com.nimbusds.openid.connect.sdk.UserInfoResponse;
import com.nimbusds.openid.connect.sdk.UserInfoSuccessResponse;
import com.nimbusds.openid.connect.sdk.claims.UserInfo;

import net.minidev.json.JSONObject;

/**
 * Authentication with Github
 * @author Carlos Parada, cparada@erpya.com, ERPCyA http://www.erpya.com
 */
public class GitHubAuthentication extends OpenIDConnect{
	/**Default Scope*/
	private final static String defaultScope = "user:email";
	
	/**Github Authorization End Point*/
	private final static String authorizationEndPoint = "https://github.com/login/oauth/authorize";
	/**Github UserInfo End Point*/
	private final static String userInfoEndPoint = "https://api.github.com/user";
	/**Github UserInfo End Point*/
	private final static String tokenEndPoint = "https://github.com/login/oauth/access_token";
	
	/**
	 * Constructor
	 */
	public GitHubAuthentication() {
		super();
		setResponseType(new ResponseType(ResponseType.Value.CODE));
		setAuthorizationEndPoint(authorizationEndPoint);
		setUserInfoEndpoint(userInfoEndPoint);
		setTokenEndpoint(tokenEndPoint);
		setScope(defaultScope);
	}

	@Override
	protected TokenResponse parseTokenResponse(HTTPResponse httpResponse) throws ParseException {
		JSONObject contentJSON = getContentAsJSONObject(httpResponse.getContent());
		if (httpResponse.getStatusCode() == HTTPResponse.SC_OK
				&& contentJSON.get("access_token")!=null) 
			return OIDCTokenResponse.parse(contentJSON);
		else
			return TokenErrorResponse.parse(contentJSON);
	}

	/**
	 * Get Content as JSON Object
	 * @param content
	 * @return
	 */
	private JSONObject getContentAsJSONObject(String content) {
		JSONObject contentJSON = new JSONObject();
		Optional<Map<String, String>> maybeMapContent = Optional.ofNullable(OpenIDUtil.getMapFromString(content, "&"));
		maybeMapContent
			.ifPresent(mapContent ->
						mapContent.entrySet()
							.forEach(map -> contentJSON.put(validData(map.getKey()), validData(map.getValue()))));
		
		return contentJSON;
	}

	@Override
	protected UserInfoResponse parseUserInfo(HTTPResponse httpResponse) throws ParseException {
		if (httpResponse.getStatusCode() == HTTPResponse.SC_OK)
			return parseJson(httpResponse.getContentAsJSONObject());
		else
			return UserInfoErrorResponse.parse(httpResponse);
	}
	
	/**
	 * Validate Data from Content
	 * @param data
	 * @return
	 */
	private String validData(String data) {
		return Optional.ofNullable(data).orElse("").replace("bearer", "Bearer").replace("\n", "");
	}
	
	/**
	 * Parse user Info JSONObject
	 * @param userInfoJson
	 * @return
	 * @throws ParseException
	 */
	private static UserInfoSuccessResponse parseJson(final JSONObject userInfoJson) throws ParseException{
		AtomicReference<UserInfoSuccessResponse> response = new AtomicReference<UserInfoSuccessResponse>(null);
		Optional<JSONObject> maybeUserInfoJson = Optional.ofNullable(userInfoJson);
		maybeUserInfoJson.ifPresent(userInfo ->{
			boolean existSub = userInfo.entrySet().stream().anyMatch(map -> map.getKey().equals("sub"));
			if (!existSub)
				userInfo.put("sub", "");
			UserInfo claimsSet = new UserInfo(userInfoJson);
			response.set(new UserInfoSuccessResponse(claimsSet));
		});
		
		return response.get();
	}
	
}
