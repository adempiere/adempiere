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

import java.util.List;

import org.compiere.model.MClient;
import org.compiere.model.MClientInfo;
import org.compiere.model.MMailText;
import org.compiere.model.MUser;
import org.compiere.model.MUserMail;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.EMail;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.spin.model.MADTokenDefinition;


/**
 * @author Raul Muñoz, rMunoz@erpya.com, ERPCyA http://www.erpya.com
 * @author Yamel Senih, ySenih@erpya.com, ERPCyA http://www.erpya.com
 * <li> FR [ 1769 ] Add option to restore the password from the login
 * @see https://github.com/adempiere/adempiere/issues/1769
 *
 */
public class GeneratePassword  {

	StringBuffer msg = new StringBuffer();
	StringBuffer msgException = new StringBuffer();
	
	public String doIt(String userName) {
		List<MUser> users = MUser.getUsers(Env.getCtx(), userName);
		users.stream()
			.filter(user -> user.isActive() && user.isLoginUser())
			.forEach(user -> {
				try {
					msg.append(generateToken(user));
				} catch (Exception e) {
					msg.append(e.getLocalizedMessage());
				}
		});
		return msg.toString();
		
	}
	
	/**
	 * Generate token
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String generateToken(MUser user) throws Exception {
		if(user == null) {
			throw new AdempiereUserError ("@AD_User_ID@ @NotFound@");
		}
		//	Validate EMail
		if (Util.isEmpty(user.getEMail())) {
			throw new AdempiereUserError ("@AD_User_ID@ - @Email@ @NotFound@");
		}
		MClient client = MClient.get(user.getCtx(), user.getAD_Client_ID());
		MClientInfo clientInfo = client.getInfo();
		//	
		TokenGeneratorHandler.getInstance().generateToken(MADTokenDefinition.TOKENTYPE_URLTokenUsedAsURL, user.getAD_User_ID());
		//	Get
		int mailTextId = clientInfo.getRestorePassword_MailText_ID();
		if(mailTextId <= 0) {
			throw new AdempiereUserError ("@RestorePassword_MailText_ID@ @NotFound@");
		}
		//	Set from mail template
		MMailText text = new MMailText (Env.getCtx(), mailTextId, null);
		text.setPO(TokenGeneratorHandler.getInstance().getToken(MADTokenDefinition.TOKENTYPE_URLTokenUsedAsURL));
		text.setUser(user);
		//	
		EMail email = client.createEMail(user.getEMail(), null, null);
		//	
		String msg = null;
		if (!email.isValid()) {
			msg = "@RequestActionEMailError@ Invalid EMail: " + user;
			throw new AdempiereUserError (
					"@RequestActionEMailError@ Invalid EMail: " + user);
		}
		//text.setUser(user);	//	variable context
		String message = text.getMailText(true);
		email.setMessageHTML(text.getMailHeader(), message);
		//
		msg = email.send();
		MUserMail um = new MUserMail(text, user.getAD_User_ID(), email);
		um.saveEx();
		if (!msg.equals(EMail.SENT_OK)) {
			throw new AdempiereUserError (
					user.getName() + " @RequestActionEMailError@ " + msg);
		}
  	  	return msg ;
	}

}
