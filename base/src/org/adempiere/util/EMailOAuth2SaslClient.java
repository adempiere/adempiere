/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, see http://www.gnu.org/licenses or write to the * 
 * Free Software Foundation, Inc.,                                            *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2016                                                    *
 * All Rights Reserved.                                                       *
 *****************************************************************************/
package org.adempiere.util;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslException;

/**
 * Sasl email client
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 423 ] Add support to oAuth EMail
 *		@see https://github.com/adempiere/adempiere/issues/423
 */
public class EMailOAuth2SaslClient implements SaslClient {

	/**
	 * Default Constructor
	 * @param oauthToken
	 * @param callbackHandler
	 */
	public EMailOAuth2SaslClient(String oauthToken,
			CallbackHandler callbackHandler) {
		token = oauthToken;
		callback = callbackHandler;
	}
	
	/** Token				*/
	private final String token;
	/**	Callback Handler	*/
	private final CallbackHandler callback;
	/**	Is Complete			*/
	private boolean isComplete;
	/**	Constant Mechanism	*/
	public static final String MECHANISM_NAME = "XOAUTH2";



	@Override
	public String getMechanismName() {
		return MECHANISM_NAME;
	}

	@Override
	public boolean hasInitialResponse() {
		return true;
	}

	@Override
	public boolean isComplete() {
		return isComplete;
	}

	@Override
	public void dispose() throws SaslException {
		//	
	}

	@Override
	public Object getNegotiatedProperty(String propName) {
		if (!isComplete()) {
			throw new IllegalStateException();
		}
		//	Default
		return null;
	}

	@Override
	public byte[] wrap(byte[] outgoing, int offset, int len)
			throws SaslException {
		throw new IllegalStateException();
	}



	@Override
	public byte[] unwrap(byte[] incoming, int offset, int len)
			throws SaslException {
		throw new IllegalStateException();
	}

	@Override
	public byte[] evaluateChallenge(byte[] challenge) throws SaslException {
		if (isComplete) {
			return new byte[] {};
		}

		NameCallback nameCallback = new NameCallback("Enter name");
		Callback[] callbacks = new Callback[] { nameCallback };
		try {
			callback.handle(callbacks);
		} catch (UnsupportedCallbackException e) {
			throw new SaslException("Unsupported callback: " + e);
		} catch (IOException e) {
			throw new SaslException("Failed to execute callback: " + e);
		}
		String email = nameCallback.getName();
		
		byte[] response = String.format("user=%s\1auth=Bearer %s\1\1", email, token).getBytes();
		isComplete = true;
		return response;
	}
}
