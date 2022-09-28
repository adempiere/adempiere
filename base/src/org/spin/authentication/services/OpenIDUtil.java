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
package org.spin.authentication.services;

import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.compiere.model.MUser;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Language;
import org.compiere.util.Msg;
import org.spin.model.MADAppRegistration;
import org.spin.model.MADAppSupport;
import org.spin.util.support.AppSupportHandler;

/**
 * Utils for Authenticated With Open ID Connect
 * @author Carlos Parada, cparada@erpya.com, ERPCyA http://www.erpya.com
 *
 */
public class OpenIDUtil {
	/**Parameters Separator*/
	public final static String PARAMETER_SEPARATOR = "|";
	/**Display Name for User Selection*/
	public final static String DISPLAYNAME = "DISPLAYNAME";
	/**Redirect URI*/
	public final static String REDIRECT_URL = "REDIRECT_URL";
	/**Authentication Client Identifier*/
	public final static String CLIENT_ID = "CLIENT_ID";
	/**Authentication Client Secret*/
	public final static String CLIENT_SECRET = "CLIENT_SECRET";
	/**Authentication Client Secret*/
	public final static String ENDPOINT_Authorization_URI = "AUTHORIZATION_URI";
	/**Scopes*/
	public final static String SCOPES = "SCOPES";
	/**Open ID Connect Error Message*/
	public final static String OPENID_ERROR_MESSAGE = "OPENID_ERROR_MESSAGE";
	
	
	/**Authentication Parameters to be Validate*/
	public final static List<String> PARAMETER_TAGS = List.of(REDIRECT_URL, CLIENT_ID, CLIENT_SECRET);
	
	private static final CLogger log = CLogger.getCLogger(OpenIDUtil.class);
	
	/**
	 * Encode Base64 Text Value
	 * @param textToEncode
	 * @return
	 */
	public static String encodeText(String textToEncode) {
		return Base64.getEncoder().encodeToString(textToEncode.getBytes()); 
	}
	
	/**
	 * Decode Base64 Text Value
	 * @param textToDecode
	 * @return
	 */
	public static String decodeText(String textToDecode) {
		return new String(Base64.getDecoder().decode(textToDecode)); 
	}
	
	/**
	 * Get Key State on Base64
	 * @param applicationRegistration
	 * @return
	 */
	public static String getState(MADAppRegistration applicationRegistration) {
		AtomicReference<String> state = new AtomicReference<>("");
		Optional<MADAppRegistration> maybeAppRegistration = Optional.ofNullable(applicationRegistration);
		maybeAppRegistration.ifPresent(appRegistration -> {
			StringBuffer appRegistrationKey = new StringBuffer("");
			appRegistrationKey.append(MADAppRegistration.COLUMNNAME_AD_AppRegistration_ID + "="+ appRegistration.get_ID());
			appRegistrationKey.append(PARAMETER_SEPARATOR);
			appRegistrationKey.append(MADAppRegistration.COLUMNNAME_ApplicationType + "="+ appRegistration.getApplicationType());
			state.set(OpenIDUtil.encodeText(appRegistrationKey.toString()));
		});
		return state.get();
	}

	/**
	 * Get HashTable with Authentication Services
	 * @return
	 */
	public static Hashtable<Integer, Map<String, String>>  getAuthenticationServices(){
		
		Hashtable<Integer, Map<String, String>> authenticationServices = new Hashtable<>(); 
		MADAppRegistration.getAll(Env.getCtx(), false, null)
		.stream()
		.filter(applicationRegistration -> applicationRegistration.getApplicationType().equals(MADAppSupport.APPLICATIONTYPE_OpenIDConnectAuthentication) 
												&& applicationRegistration.isActive())
		.forEach(applicationRegistration -> {
			try {
				IOpenIDConnect authenticationService = (IOpenIDConnect) AppSupportHandler.getInstance().getAppSupport(applicationRegistration);
				Map<String, String> data = new HashMap<String, String>();
				data.put(OpenIDUtil.DISPLAYNAME, applicationRegistration.getName());
				data.put(OpenIDUtil.ENDPOINT_Authorization_URI, authenticationService.getServiceURL());
				authenticationServices.put(authenticationService.getAppRegistrationId(), data);
			} catch (Exception e) {
				log.severe(e.getMessage());
			}
		});
		return authenticationServices;
	}
	
	/**
	 * Get Map From String Key=Value Separate with a custom separator
	 * @param stringToMap
	 * @param separator
	 * @return
	 */
	public static Map<String, String> getMapFromString(String stringToMap, String separator){
		Optional<String> maybeStringToMap = Optional.ofNullable(stringToMap);
		AtomicReference<Map<String, String>> map = new AtomicReference<Map<String, String>>(new HashMap<>());
		maybeStringToMap.ifPresent(StringToMap -> {
			map.set(Arrays.stream(StringToMap.split(separator))
					             .map(stringValue -> stringValue.split("="))
							     .collect(Collectors.toMap(stringArray -> stringArray[0], stringArray -> stringArray[1])));
		});
		return map.get();
	}
	
	/**
	 * Get User Authenticated
	 * @param codeParameter
	 * @param stateParameter
	 * @return
	 */
	public static MUser getUserAuthenticated(String codeParameter,String stateParameter){
		Optional<String> maybeCode = Optional.ofNullable(codeParameter);
		Optional<String> maybeState = Optional.ofNullable(stateParameter);
		AtomicReference<MUser> user = new AtomicReference<>(null);
		maybeCode.ifPresent(code->{
			maybeState.ifPresent(state ->{
				Map<String, String> stateMap = getMapFromString(decodeText(state), Pattern.quote(PARAMETER_SEPARATOR));
				Optional<String> maybeAppRegistrationParameter = Optional.ofNullable(stateMap.get(MADAppRegistration.COLUMNNAME_AD_AppRegistration_ID));
				Optional<String> maybeApplicationType = Optional.ofNullable(stateMap.get(MADAppRegistration.COLUMNNAME_ApplicationType));
				maybeAppRegistrationParameter.ifPresent(appRegistrationParameter ->{
					maybeApplicationType.ifPresent(applicationType ->{
						if (applicationType.equals(MADAppSupport.APPLICATIONTYPE_OpenIDConnectAuthentication)) {
							try {
								int appRegistrationId = Integer.parseInt(appRegistrationParameter);
								MADAppRegistration appRegistration = MADAppRegistration.getById(Env.getCtx(), appRegistrationId, null);
								IOpenIDConnect authenticationService = (IOpenIDConnect) AppSupportHandler.getInstance().getAppSupport(appRegistration);
								//Get User from Environment
								MUser userEnvironment = null;
								if (Env.getAD_User_ID(Env.getCtx()) > 0 )
									userEnvironment = MUser.get(Env.getCtx());
									
								authenticationService.validateAuthentication(codeParameter, userEnvironment);
								Optional<MUser> maybeUser = Optional.ofNullable(authenticationService.getUser());
								authenticationService.clearValues();
								maybeUser.ifPresent(userAuthenticated -> {
									if (userAuthenticated.isLoginUser()
											&& userAuthenticated.isActive())
										user.set(userAuthenticated);
								});
								maybeUser.orElseGet(() -> {
									if (Env.getAD_User_ID(Env.getCtx()) == 0 ) 
										setErrorMessage(Env.getCtx(), Msg.getMsg(Language.getBaseAD_Language(), "UserNotVerified"));
									return null;
								});
							} catch (Exception e) {
								log.severe(e.getMessage());
							}
						}
					});
				});
			});
		});
		return user.get();
	}
	
	/**
	 * Set Error Messages to Context
	 * @param ctx
	 * @param message
	 */
	public static void setErrorMessage(Properties ctx,String message) {
		Env.setContext(ctx, OPENID_ERROR_MESSAGE, message);
	}
	
	/**
	 * Get Error Messages From Context
	 * @param ctx
	 * @return
	 */
	public static String getErrorMessage(Properties ctx) {
		return Env.getContext(ctx, OPENID_ERROR_MESSAGE);
	}
}
