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

import java.util.Map;

import javax.security.auth.callback.CallbackHandler;
import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslClientFactory;
import javax.security.sasl.SaslException;

/**
 * Sasl email client factory
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 423 ] Add support to oAuth EMail
 *		@see https://github.com/adempiere/adempiere/issues/423
 */
public class EMailOAuth2SaslFactory implements SaslClientFactory {

	@Override
	public SaslClient createSaslClient(String[] mechanisms,
			String authorizationId, String protocol, String serverName,
			Map<String, ?> props, CallbackHandler cbh) throws SaslException {
		boolean isMatched = false;
	    for (int i = 0; i < mechanisms.length; ++i) {
	    	if (mechanisms[i].equalsIgnoreCase(EMailOAuth2SaslClient.MECHANISM_NAME)) {
	    		isMatched = true;
	    		break;
	        }
	    }
	    //	Validate Match
	    if (!isMatched) {
	    	return null;
	    }
	    //	Default Return
	    return new EMailOAuth2SaslClient((String) props.get("mail.imaps.sasl.mechanisms.oauth2.oauthToken"), cbh);
	}

	@Override
	public String[] getMechanismNames(Map<String, ?> props) {
		return new String[] {EMailOAuth2SaslClient.MECHANISM_NAME};
	}
}
