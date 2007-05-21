package org.compiere.util;

import java.io.Serializable;
import java.security.cert.Certificate;

/**
 * @author Low Heng Sin
 */
public class SecurityToken implements Serializable {

	private Certificate codeCertificate;
	private String codeBaseHost;
	
	public SecurityToken(Certificate cert, String host)
	{
		codeCertificate = cert;
		codeBaseHost = host;
	}
	
	public Certificate getCodeCertificate()
	{
		return codeCertificate;
	}
	
	public String getCodeBaseHost()
	{
		return codeBaseHost;
	}
}
