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
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************/
package org.spin.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.compiere.util.SecureEngine;
import org.compiere.util.Util;
import org.spin.model.MADToken;
import org.spin.model.MADTokenDefinition;

/**
 * A simple token generator for third party access
 * Note: You should generate your own token generator
 * @author Yamel Senih, ySenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class ThirdPartyAccess implements IThirdPartyAccessGenerator {

	/**	Default Token	*/
	private MADToken token = null;
	/**	User Token value	*/
	private String userTokenValue = null;
	 
    public ThirdPartyAccess() {
    	//	
    }
	
    @Override
	public String generateToken(String tokenType, int userId) {
		return generateToken(userId, 0);
	}

	
	@Override
	public boolean validateToken(String token, int userId) {
		return false;
	}
	
	@Override
	public MADToken getToken() {
		return token;
	}
	
	@Override
	public String getTokenValue() {
		return userTokenValue;
	}

	@Override
	public String generateToken(int userId, int roleId) {
		//	Validate user
		if(userId < 0) {
			throw new AdempiereException("@AD_User_ID@ @NotFound@");
		}
		//	Validate Role
		if(roleId < 0) {
			throw new AdempiereException("@AD_Role_ID@ @NotFound@");
		}
		MADToken token = new MADToken(Env.getCtx(), 0, null);
        token.setTokenType(MADTokenDefinition.TOKENTYPE_ThirdPartyAccess);
        if(token.getAD_TokenDefinition_ID() <= 0) {
        	throw new AdempiereException("@AD_TokenDefinition_ID@ @NotFound@");
        }
        MADTokenDefinition definition = MADTokenDefinition.getById(Env.getCtx(), token.getAD_TokenDefinition_ID(), null);
		String tokenValue = null;
		userTokenValue = null;
		try {
			String value = UUID.randomUUID().toString();
			// 
			byte[] saltValue = new byte[8];
			// Digest computation
			userTokenValue = SecureEngine.encrypt(value);
			tokenValue = SecureEngine.getSHA512Hash(1000, userTokenValue, saltValue);
		} catch (NoSuchAlgorithmException e) {
			new AdempiereException(e);
		} catch (UnsupportedEncodingException e) {
			new AdempiereException(e);
		}
		if(Util.isEmpty(tokenValue)) {
			throw new AdempiereException("@TokenValue@ @NotFound@");
		}
		//	Validate
        if(definition.isHasExpireDate()) {
        	BigDecimal expirationTime = Optional.ofNullable(definition.getExpirationTime()).orElse(new BigDecimal(5 * 60 * 1000));
        	token.setExpireDate(new Timestamp(System.currentTimeMillis() + expirationTime.longValue()));
        }
        token.setTokenValue(tokenValue);
        token.setAD_User_ID(userId);
        token.setAD_Role_ID(roleId);
        token.saveEx();
        return userTokenValue;
	}

	@Override
	public boolean validateToken(String tokenValue) {
		String encryptedValue = null;
		try {
			byte[] saltValue = new byte[8];
			encryptedValue = SecureEngine.getSHA512Hash(1000, tokenValue, saltValue);
		} catch (NoSuchAlgorithmException e) {
			new AdempiereException(e);
		} catch (UnsupportedEncodingException e) {
			new AdempiereException(e);
		}
		token = getToken(encryptedValue);
		if(token == null
				|| token.getAD_Token_ID() <= 0) {
			return false;
		}
		MADTokenDefinition definition = MADTokenDefinition.getById(Env.getCtx(), token.getAD_TokenDefinition_ID(), null);
		if(definition.isHasExpireDate()) {
			Timestamp current = new Timestamp(System.currentTimeMillis());
			if(token != null && token.getExpireDate().compareTo(current) > 0) {
				return true;
			} else {
				return false;
			}
		}
		//	Is Ok
		return true;
	}
	
	/**
	 * Get system token based on encrypted user token
	 * @param encryptedValue
	 * @return
	 */
	private MADToken getToken(String encryptedValue) {
		return new Query(Env.getCtx(), MADToken.Table_Name, "TokenValue = ? "
				+ "AND EXISTS(SELECT 1 FROM AD_User_Roles ur WHERE ur.AD_User_ID = AD_Token.AD_User_ID AND ur.AD_Role_ID = AD_Token.AD_Role_ID AND ur.IsActive = 'Y') "
				+ "AND EXISTS(SELECT 1 FROM AD_User u WHERE u.AD_User_ID = AD_Token.AD_User_ID AND u.IsActive = 'Y' AND u.IsLoginUser = 'Y') "
				+ "AND EXISTS(SELECT 1 FROM AD_Role r WHERE r.AD_Role_ID = AD_Token.AD_Role_ID AND r.IsActive = 'Y')", null)
				.setParameters(encryptedValue)
				.setOnlyActiveRecords(true)
				.first();
	}
}
