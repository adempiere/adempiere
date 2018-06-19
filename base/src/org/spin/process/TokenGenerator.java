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
 * Contributor(s): Yamel Senih www.erpconsultoresyasociados.com               *
 *****************************************************************************/
package org.spin.process;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.List;

import org.compiere.model.Query;
import org.compiere.util.Env;
import org.spin.model.MToken;
/**
 * @author Raul Muñoz, rMunoz@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/1446">
 * 		@see FR [ 1446 ] Smart Browse for Deposit from cash</a>
 *
 */
public class TokenGenerator implements ITokenGenerator {


    protected static SecureRandom random = new SecureRandom();
    private static TokenGenerator tokenHandler = null;
    
    private String token;
    private int userId;
    private MToken passReset;
    private Timestamp expireDate;
    
    public TokenGenerator() {
    }
	
    @Override
	public String generateToken(int userId) {
	    long longToken = Math.abs( random.nextLong() );
        String random = Long.toString( longToken, 16 );
        token = random+userId;
        this.userId = userId;
        expireDate = new Timestamp(System.currentTimeMillis()+5*60*1000);
        passReset = new MToken(Env.getCtx(), 0, null);
        passReset.setExpireDate(expireDate);
        passReset.setValue(token);
        passReset.setAD_User_ID(userId);
        passReset.save();
        return token;
	}

	
	@Override
	public boolean validateToken(String token, int userId) {
		String where = "Value=? and AD_User_ID= ?";
		
		MToken passReset = new 
				Query(Env.getCtx(), MToken.Table_Name, where, null).setParameters(token,userId).first();
		Timestamp current = new Timestamp(System.currentTimeMillis());
		if(passReset != null && passReset.getExpireDate().compareTo(current) > 0)
			return true;
		else
			return false;
	}

	public MToken getToken() {
		return passReset;
	}
	  
	public String getTokenValue() {
		return token;
	}

	
}
