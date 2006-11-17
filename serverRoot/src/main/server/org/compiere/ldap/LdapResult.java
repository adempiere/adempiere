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

import java.io.*;
import java.util.logging.*;
import org.compiere.util.*;
import com.sun.jndi.ldap.*;

/**
 * 	Ldap Wire Response
 *	
 *  @author Jorg Janke
 *  @version $Id: LdapResult.java,v 1.1 2006/10/09 00:23:16 jjanke Exp $
 */
public class LdapResult
{

	public LdapResult()
	{
		super ();
	}	//	LdapResult
	
	/** 
	 	LDAPResult ::= SEQUENCE {
	 		resultCode      ENUMERATED {
                     success                      (0),
                     operationsError              (1),
                     protocolError                (2),
                     timeLimitExceeded            (3),
                     sizeLimitExceeded            (4),
                     compareFalse                 (5),
                     compareTrue                  (6),

                     authMethodNotSupported       (7),
                     strongAuthRequired           (8),
                                -- 9 reserved --
                     referral                     (10),  -- new
                     adminLimitExceeded           (11),  -- new
                     unavailableCriticalExtension (12),  -- new
                     confidentialityRequired      (13),  -- new
                     saslBindInProgress           (14),  -- new
                     noSuchAttribute              (16),
                     undefinedAttributeType       (17),
                     inappropriateMatching        (18),
                     constraintViolation          (19),
                     attributeOrValueExists       (20),
                     invalidAttributeSyntax       (21),
                     noSuchObject                 (32),
                     aliasProblem                 (33),
                     invalidDNSyntax              (34),
                     -- 35 reserved for undefined isLeaf --
                     aliasDereferencingProblem    (36),
                                -- 37-47 unused --
                     inappropriateAuthentication  (48),
                     invalidCredentials           (49),
                     insufficientAccessRights     (50),
                     busy                         (51),
                     unavailable                  (52),
                     unwillingToPerform           (53),
                     loopDetect                   (54),
                                -- 55-63 unused --
                     namingViolation              (64),
                     objectClassViolation         (65),
                     notAllowedOnNonLeaf          (66),
                     notAllowedOnRDN              (67),
                     entryAlreadyExists           (68),
                     objectClassModsProhibited    (69),
                                -- 70 reserved for CLDAP --
                     affectsMultipleDSAs          (71), -- new
                                -- 72-79 unused --
                     other                        (80) },
                     -- 81-90 reserved for APIs --
        matchedDN       LDAPDN,
        errorMessage    LDAPString,
        referral        [3] Referral OPTIONAL }
    **/
	
	/** Encoder							*/
	private BerEncoder m_encoder = new BerEncoder();
	/**	Logger	*/
	private static CLogger log = CLogger.getCLogger (LdapResult.class);
	
	/**
	 * 	Bind Response
	 *	@return reponse
	 */
	public byte[] bindResponse() 
	{
		try
		{
/**
		m_encoder.beginSeq(Ber.ASN_SEQUENCE | Ber.ASN_CONSTRUCTOR);
		for (int i = 0; i < sortKeys.length; i++) {
		    ber.beginSeq(Ber.ASN_SEQUENCE | Ber.ASN_CONSTRUCTOR);
		    ber.encodeString(sortKeys[i].getAttributeID(), true); // v3
		    if ((matchingRule = sortKeys[i].getMatchingRuleID()) != null) {
			ber.encodeString(matchingRule, (Ber.ASN_CONTEXT | 0), true);
		    }
		    if (! sortKeys[i].isAscending()) {
			ber.encodeBoolean(true, (Ber.ASN_CONTEXT | 1));
		    }
		    ber.endSeq();
		} 
*/
			//	payload
			m_encoder.beginSeq(Ber.ASN_APPLICATION | LdapMessage.BIND_RESPONSE);
			//	Response
			m_encoder.encodeInt(0);	//	success
			m_encoder.encodeOctetString("cn=testCN".getBytes(), 0);	//	matched DN
			m_encoder.encodeOctetString("".getBytes(), 0);	//	error mag	
			//	referral
			//	sasl
			//
			m_encoder.endSeq();
			log.info("Success");
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
		return getResult();
	}	//	bindResponse
	
	/**
	 * 	Get BER Result as byte array
	 *	@return byte array
	 */
	public byte[] getResult()
	{
		return m_encoder.getTrimmedBuf();
	}	//	getResult
	
}	//	LdapResult
