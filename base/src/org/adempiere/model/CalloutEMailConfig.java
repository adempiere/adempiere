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
 * Copyright (C) 2003-2016 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpcya.com                                 *
 *****************************************************************************/
package org.adempiere.model;

import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MEMailConfig;

/**
 * 	CalloutEMailConfig
 * 	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *			<li> FR [ 402 ] Mail setup is hardcoded
 *			@see https://github.com/adempiere/adempiere/issues/402
 */
public class CalloutEMailConfig extends CalloutEngine {
	
	/**
	 * Set Default port
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 */
	public String port(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {
		//	Valid Value
		if(value == null)
			return "";
		//	Set Default
		String encryptionType = (String) mTab.getValue("EncryptionType");
		String protocol = (String) mTab.getValue("Protocol");
		//	Valid null
		if(encryptionType == null
				|| protocol == null)
			return "";
		int port = 0;
		if(protocol.equals(MEMailConfig.PROTOCOL_SMTP)) {
			if(encryptionType.equals(MEMailConfig.ENCRYPTIONTYPE_None))
				port = MEMailConfig.DEFAULT_SMTP_PORT;
			else
				port = MEMailConfig.DEFAULT_SMTP_SSL_PORT;
		} else if(protocol.equals(MEMailConfig.PROTOCOL_POP3)) {
			if(encryptionType.equals(MEMailConfig.ENCRYPTIONTYPE_None))
				port = MEMailConfig.DEFAULT_POP3_PORT;
			else
				port = MEMailConfig.DEFAULT_POP3_SSL_PORT;
		} else if(protocol.equals(MEMailConfig.PROTOCOL_IMAP)) {
			if(encryptionType.equals(MEMailConfig.ENCRYPTIONTYPE_None))
				port = MEMailConfig.DEFAULT_IMAP_PORT;
			else
				port = MEMailConfig.DEFAULT_IMAP_SSL_PORT;
		}
		//	Set Port
		mTab.setValue("Port", port);
		return "";
	}
}