/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/

package org.spin.process;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.adempiere.core.domains.models.I_AD_Role;
import org.adempiere.core.domains.models.I_AD_Token;
import org.adempiere.core.domains.models.I_AD_User;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;
import org.spin.model.MADToken;
import org.spin.model.MADTokenDefinition;
import org.spin.util.TokenGeneratorHandler;
import org.spin.util.IThirdPartyAccessGenerator;
import org.spin.util.ITokenGenerator;

/** 
 * 	Generate Token for Third Party Access
 *  @author Yamel Senih, ySenih@erpya.com, ERPCyA http://www.erpya.com 
 *  @version Release 3.9.3
 */
public class GenerateTokenForThirdPartyAccess extends GenerateTokenForThirdPartyAccessAbstract {

	@Override
	protected String doIt() throws Exception {
		if(getRecord_ID() > 0 && getTableName().equals(I_AD_Role.Table_Name)) {
			setRoleId(getRecord_ID());
		} else if(getRecord_ID() > 0 && getTableName().equals(I_AD_User.Table_Name)) {
			setUserId(getRecord_ID());
		}
		//	Validate user and password match
		boolean match = new Query(getCtx(), I_AD_Role.Table_Name, 
				"EXISTS(SELECT 1 FROM AD_User_Roles ur "
				+ "WHERE ur.AD_Role_ID = AD_Role.AD_Role_ID "
				+ "AND ur.AD_User_ID = ?)", get_TrxName())
				.setParameters(getUserId())
				.match();
		if(!match) {
			throw new AdempiereException("@AD_User_ID@ / @AD_Role_ID@ @Mismatched@");
		}
		//	For revoke
		if(isRevokeAllTokens()) {
			List<MADToken> tokens = new Query(getCtx(), I_AD_Token.Table_Name, "AD_User_ID = ? "
					+ "AND AD_Role_ID = ? "
					+ "AND EXISTS(SELECT 1 FROM AD_TokenDefinition td "
					+ "WHERE td.AD_TokenDefinition_ID = AD_Token.AD_TokenDefinition_ID "
					+ "AND td.TokenType = ?)", get_TrxName())
			.setParameters(getUserId(), getRoleId(), MADTokenDefinition.TOKENTYPE_ThirdPartyAccess)
			.setOnlyActiveRecords(true)
			.setClient_ID()
			.<MADToken>list();
			AtomicInteger counter = new AtomicInteger(0);
			if(tokens != null
					&& tokens.size() > 0) {
				tokens.forEach(token -> {
					token.setIsActive(false);
					token.saveEx();
					counter.incrementAndGet();
				});
			}
			//	Updated by default
			return "@Updated@: " + counter.get();
		} else {
			ITokenGenerator generator = TokenGeneratorHandler.getInstance().getTokenGenerator(MADTokenDefinition.TOKENTYPE_ThirdPartyAccess);
			if(generator == null) {
				throw new AdempiereException("@AD_TokenDefinition_ID@ @NotFound@");
			}
			//	No child of definition
			if(!IThirdPartyAccessGenerator.class.isAssignableFrom(generator.getClass())) {
				throw new AdempiereException("@AD_TokenDefinition_ID@ @Invalid@");	
			}
			//	Generate
			IThirdPartyAccessGenerator thirdPartyAccessGenerator = ((IThirdPartyAccessGenerator) generator);
			String token = thirdPartyAccessGenerator.generateToken(getUserId(), getRoleId());
			return "@TokenValue@: " + token;
		}
	}
}