/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.
 * This program is free software; you can redistribute it and/or modify it
 * under the terms version 2 of the GNU General Public License as published
 * by the Free Software Foundation. This program is distributed in the hope
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along 
 * with this program; if not, write to the Free Software Foundation, Inc., 
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
 * You may reach us at: ComPiere, Inc. - http://www.adempiere.org/license.html
 * 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA or info@adempiere.org 
 *****************************************************************************/
package org.compiere.ldap;

import java.util.logging.*;
import org.compiere.util.*;
import com.sun.jndi.ldap.*;

/**
 * 	Ldap Message 
 *	
 *  @author Jorg Janke
 *  @version $Id: LdapMessage.java,v 1.1 2006/10/09 00:23:16 jjanke Exp $
 */
public class LdapMessage
{
	/**
	 * 	Ldap Message
	 *	@param data BER data
	 *	@param length Ber data length
	 */
	public LdapMessage (byte[] data, int length)
	{
		try
		{
			decode(data, length);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, data.toString(), e);
		}
	}	//	LdapMessage
	
	/**
	 	LDAPMessage ::= SEQUENCE {
    	                messageID       MessageID,
    	                protocolOp      CHOICE {
    	                        bindRequest     BindRequest,
    	                        bindResponse    BindResponse,
    	                        unbindRequest   UnbindRequest,
    	                        searchRequest   SearchRequest,
    	                        searchResEntry  SearchResultEntry,
    	                        searchResDone   SearchResultDone,
    	                        searchResRef    SearchResultReference,
    	                        modifyRequest   ModifyRequest,
    	                        modifyResponse  ModifyResponse,
    	                        addRequest      AddRequest,
    	                        addResponse     AddResponse,
    	                        delRequest      DelRequest,
    	                        delResponse     DelResponse,
    	                        modDNRequest    ModifyDNRequest,
    	                        modDNResponse   ModifyDNResponse,
    	                        compareRequest  CompareRequest,
    	                        compareResponse CompareResponse,
    	                        abandonRequest  AbandonRequest,
    	                        extendedReq     ExtendedRequest,
    	                        extendedResp    ExtendedResponse },
    	                 controls       [0] Controls OPTIONAL }
    **/
	
	static public final int BIND_REQUEST = 0;
	static public final int BIND_RESPONSE = 1;
	static public final int UNBIND_REQUEST	= 2;
	static public final int SEARCH_REQUEST	= 3;
	static public final int SEARCH_RESENTRY	= 4;
	static public final int SEARCH_RESDONE	= 5;
	static public final int MODIFY_REQUEST	= 6;
	static public final int MODIFY_RESPONSE	= 7;
	static public final int ADD_REQUEST	= 8;
	static public final int ADD_RESPONSE = 9;
	static public final int DEL_REQUEST	= 10;
	static public final int DEL_RESPONSE = 11;
	static public final int MODDN_REQUEST = 12;
	static public final int MODDN_RESPONSE = 13;
	static public final int COMPARE_REQUEST = 14;
	static public final int COMPARE_RESPONSE = 15;
	static public final int ABANDON_REQUEST = 16;
	static public final int EXTENDED_REQUEST = 17;
	static public final int EXTENDED_RESPONSE = 18;

	static public final int[] PROTOCOL_OP = {
		BIND_REQUEST, BIND_RESPONSE, UNBIND_REQUEST, 
		SEARCH_REQUEST, SEARCH_RESENTRY, SEARCH_RESDONE,
		MODIFY_REQUEST, MODIFY_RESPONSE, ADD_REQUEST, ADD_RESPONSE,
		DEL_REQUEST, DEL_RESPONSE, MODDN_REQUEST, MODDN_RESPONSE,
		COMPARE_REQUEST, COMPARE_RESPONSE, ABANDON_REQUEST,
		EXTENDED_REQUEST, EXTENDED_RESPONSE};

	
	/**	Logger	*/
	private static CLogger log = CLogger.getCLogger (LdapMessage.class);
	/** Protocol Operation		*/
	private int		m_protocolOp = -1;

	
	/**
	 * 	Decode Message
	 *	@param data data
	 *	@param length length
	 *	@throws Exception
	 */
	private void decode (byte[] data, int length) throws Exception
	{
		BerDecoder decoder = new BerDecoder(data, 0, length);
		int left = decoder.bytesLeft();
		int pos = decoder.getParsePosition();
		//
		int seq = decoder.parseSeq(null);
		left = decoder.bytesLeft();
		pos = decoder.getParsePosition();
		//
		int messageID = decoder.parseInt();
		left = decoder.bytesLeft();
		pos = decoder.getParsePosition();
		//
		int peek = decoder.peekByte();
		m_protocolOp = decoder.parseSeq(PROTOCOL_OP);
		m_protocolOp -= Ber.ASN_APPLICATION;
		if (m_protocolOp - Ber.ASN_CONSTRUCTOR >= 0)
			m_protocolOp -= Ber.ASN_CONSTRUCTOR;
		left = decoder.bytesLeft();
		pos = decoder.getParsePosition();
		//
		//	Payload
		if (m_protocolOp == BIND_REQUEST)
		{
			int version = decoder.parseInt();
			left = decoder.bytesLeft();
			pos = decoder.getParsePosition();
			//
			byte[] dn = decoder.parseOctetString(Ber.ASN_OCTET_STR, null);
			left = decoder.bytesLeft();
			pos = decoder.getParsePosition();
			//
			byte[] authentification = decoder.parseOctetString(Ber.ASN_CONTEXT, null);
			left = decoder.bytesLeft();
			pos = decoder.getParsePosition();
			//
			log.info("#" + messageID + ": bind - version=" + version + ", dn=" + new String(dn)
				+ ", auth=" + new String (authentification));
		}
		else if (m_protocolOp == UNBIND_REQUEST)
			log.info("#" + messageID + ": unbind");
		else
		{
			log.warning("#" + messageID + ": Unknown Op + " + m_protocolOp);
		}
	}	//	decode
	
	/**
	 * 	Get Operation Code
	 *	@return protocolOp
	 */
	public int getOperation()
	{
		return m_protocolOp;
	}	//	getOperation
	
}	//	LdapMessage
