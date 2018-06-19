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

import org.compiere.model.MClient;
import org.compiere.model.MMailText;
import org.compiere.model.MSysConfig;
import org.compiere.model.MUser;
import org.compiere.model.MUserMail;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.EMail;
import org.compiere.util.Env;


/**
 * @author Raul Muñoz, rMunoz@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/1446">
 * 		@see FR [ 1446 ] Smart Browse for Deposit from cash</a>
 *
 */
public class GeneratePassword  {

	public String doIt(String userName) throws Exception {
		MClient client = MClient.get(Env.getCtx());
		MUser user = MUser.getUser(Env.getCtx(), userName);
		String msg = null;
		if(user != null) {
			TokenGeneratorHandler.getInstance().generateToken(user.getAD_User_ID());
			
			MMailText text = null;
			int mailTextId = MSysConfig.getIntValue("MailPasswordReset", 50001);
			text = new MMailText (Env.getCtx(), mailTextId, null);

				text.setPO(TokenGeneratorHandler.getInstance().getToken());
				if (user.getEMail() == null)
					throw new AdempiereUserError ("@NotFound@: @AD_User_Id@ - @Email@" );
			
				
				EMail email = client.createEMail(user.getEMail(), null, null);
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
		} else {
			throw new AdempiereUserError (
					" @NotFound@: @AD_User_Id@ ");
		}
  	  	return msg ;
	}

}
