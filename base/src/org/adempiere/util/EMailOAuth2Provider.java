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

import java.security.Provider;

/**
 * Provider class for oAuth2 method
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 423 ] Add support to oAuth EMail
 *		@see https://github.com/adempiere/adempiere/issues/423
 */
public class EMailOAuth2Provider extends Provider {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6217156374288531426L;

	/**
	 * Default Constructor
	 */
	public EMailOAuth2Provider() {
		super("ADempiere OAuth2 Provider", 1.0,
	            "Provides the XOAUTH2 SASL Mechanism");
	      put("SaslClientFactory.XOAUTH2",
	          "org.adempiere.util.EMailOAuth2SaslFactory");
	}

}
