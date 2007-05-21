package org.compiere.util;

import java.io.Serializable;
import java.security.CodeSource;
import java.security.cert.Certificate;

import org.compiere.Adempiere;

/**
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
	
	public final Certificate getCodeCertificate()
	{
		return codeCertificate;
	}
	
	public final String getCodeBaseHost()
	{
		return codeBaseHost;
	}
	
	/**
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
	 * Get the client security token for server validation
	 * @return SecurityToken
	 */
	public static SecurityToken getInstance()
	{
		return TOKEN;
	}
}
