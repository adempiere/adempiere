/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2007 Adempiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *
 * Copyright (C) 2007 Low Heng Sin hengsin@avantz.com
 * _____________________________________________
 *****************************************************************************/
package org.compiere.util;

import java.io.Serializable;
import java.security.CodeSource;
import java.security.cert.Certificate;

import org.compiere.Adempiere;

/**
 * Security token, send from client to server for security validation.
 * @author Low Heng Sin
 */
public class SecurityToken implements Serializable {

	private Certificate codeCertificate;
	private String codeBaseHost;
	private final static SecurityToken TOKEN = SecurityToken.getSecurityToken();
	
	private SecurityToken(Certificate cert, String host)
	{
		codeCertificate = cert;
		codeBaseHost = host;
	}
	
	/**
	 * Get the certificate that is use to sign the jar library.
	 * @return Certificate
	 */
	public final Certificate getCodeCertificate()
	{
		return codeCertificate;
	}
	
	/**
	 * Get the host that the code is loaded from.
	 * @return String
	 */
	public final String getCodeBaseHost()
	{
		return codeBaseHost;
	}
	
	/**
	 * Construct the application wide security token.
	 * @return SecurityToken
	 */
	private static SecurityToken getSecurityToken()
	{
		Certificate cert = null;
		String host = null;
		CodeSource cs 
			= SecurityToken.class.getProtectionDomain().getCodeSource();
		if (cs != null)
		{
			Certificate[] certs = cs.getCertificates();
			if (certs != null && certs.length > 0)
				cert = certs[0];
		}
		host = Adempiere.getCodeBaseHost();
		return new SecurityToken(cert, host);
	}
	
	/**
	 * Get the application wide security token.
	 * @return SecurityToken
	 */
	public static SecurityToken getInstance()
	{
		return TOKEN;
	}
}
