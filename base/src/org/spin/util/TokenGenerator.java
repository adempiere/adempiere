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

import java.security.SecureRandom;
import java.sql.Timestamp;

import org.compiere.model.Query;
import org.compiere.util.Env;
import org.spin.model.MADToken;
/**
 * @author Raul Muñoz, rMunoz@erpya.com, ERPCyA http://www.erpya.com
 * @author Yamel Senih, ySenih@erpya.com, ERPCyA http://www.erpya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/1446">
 * 		@see FR [ 1446 ] Smart Browse for Deposit from cash</a>
 *
 */
public class TokenGenerator implements ITokenGenerator {


    protected static SecureRandom random = new SecureRandom();
    
    private String token;
    private MADToken passReset;
    private Timestamp expireDate;
    
    
    public TokenGenerator() {
    	//	
    }
	
    @Override
	public String generateToken(String tokenType, int userId) {
	    long longToken = Math.abs( random.nextLong());
        String random = Long.toString( longToken, 16);
        token = random + userId;
        expireDate = new Timestamp(System.currentTimeMillis()+5*60*1000);
        passReset = new MADToken(Env.getCtx(), 0, null);
        passReset.setTokenType(tokenType);
        passReset.setExpireDate(expireDate);
        passReset.setTokenValue(token);
        passReset.setAD_User_ID(userId);
        passReset.saveEx();
        return token;
	}

	
	@Override
	public boolean validateToken(String token, int userId) {
		MADToken passReset = new 
				Query(Env.getCtx(), MADToken.Table_Name, MADToken.COLUMNNAME_TokenValue + " = ? AND " + MADToken.COLUMNNAME_AD_User_ID + " = ?", null)
				.setParameters(token, userId).first();
		Timestamp current = new Timestamp(System.currentTimeMillis());
		if(passReset != null && passReset.getExpireDate().compareTo(current) > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public MADToken getToken() {
		return passReset;
	}
	
	@Override
	public String getTokenValue() {
		return token;
	}
}
