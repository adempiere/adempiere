/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.                                     *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net                                                  *
 * or https://github.com/adempiere/adempiere/blob/develop/license.html        *
 *****************************************************************************/

package org.adempiere.process;

import org.compiere.model.MSysConfig;
import org.compiere.util.Trx;

/**
 *  Generated Process for (Configure Centralized ID Management)
 *  @version Release 3.9.3
 * 
 * 	@author Edwin Betancourt, EdwinBetanc0urt@outlook.com
 * 		<li>FR [ 3910 ] Create a process for Configure Centralized ID Management</li>
 * 		@see https://github.com/adempiere/adempiere/issues/3910
 */
public class ConfigureCentralizedIdManagement extends ConfigureCentralizedIdManagementAbstract
{
	// System Confurator
	private String DICTIONARY_ID_WEBSITE = "DICTIONARY_ID_WEBSITE";
	private String DICTIONARY_ID_USER = "DICTIONARY_ID_USER";
	private String DICTIONARY_ID_PASSWORD = "DICTIONARY_ID_PASSWORD";
	private String DICTIONARY_ID_COMMENTS = "DICTIONARY_ID_COMMENTS";
	private String DICTIONARY_ID_USE_CENTRALIZED_ID = "DICTIONARY_ID_USE_CENTRALIZED_ID";

	@Override
	protected void prepare()
	{
		super.prepare();
	}

	@Override
	protected String doIt() throws Exception
	{
		Trx.run(transactionName -> {
			// set web site
			MSysConfig webSite = MSysConfig.get(getCtx(), DICTIONARY_ID_WEBSITE, transactionName);
			webSite.setValue(getDictionaryWebsite());
			webSite.saveEx();
			if (webSite.is_ValueChanged(MSysConfig.COLUMNNAME_Value)) {
				addLog("Changed Website " + webSite.get_ValueOld(MSysConfig.COLUMNNAME_Value) + " to " + getDictionaryWebsite());
			}

			// set user
			MSysConfig user = MSysConfig.get(getCtx(), DICTIONARY_ID_USER, transactionName);
			user.setValue(getDictionaryUser());
			if (user.is_ValueChanged(MSysConfig.COLUMNNAME_Value)) {
				addLog("Changed uuser " + user.get_ValueOld(MSysConfig.COLUMNNAME_Value) + " to " + getDictionaryUser());
			}
			user.saveEx();
			
			// set password
			MSysConfig password = MSysConfig.get(getCtx(), DICTIONARY_ID_PASSWORD, transactionName);
			password.setValue(getDictionaryPassword());
			if (password.is_ValueChanged(MSysConfig.COLUMNNAME_Value)) {
				addLog("Changed password " + password.get_ValueOld(MSysConfig.COLUMNNAME_Value) + " to " + getDictionaryPassword());
			}
			password.saveEx();
			
			// migration script comments
			MSysConfig comments = MSysConfig.get(getCtx(), DICTIONARY_ID_COMMENTS, transactionName);
			comments.setValue(getDictionaryComments());
			if (comments.is_ValueChanged(MSysConfig.COLUMNNAME_Value)) {
				addLog("Changed comments " + comments.get_ValueOld(MSysConfig.COLUMNNAME_Value) + " to " + getDictionaryComments());
			}
			comments.saveEx();
			
			// use centralized id
			MSysConfig useCentralized = MSysConfig.get(getCtx(), DICTIONARY_ID_USE_CENTRALIZED_ID, transactionName);
			String isUseCentralized = isDictionaryUseCentralizedID() ? "Y" : "N";
			useCentralized.setValue(isUseCentralized);
			if (useCentralized.is_ValueChanged(MSysConfig.COLUMNNAME_Value)) {
				addLog("Changed use centralized " + useCentralized.get_ValueOld(MSysConfig.COLUMNNAME_Value) + " to " + isUseCentralized);
			}
			useCentralized.saveEx();
		});
		
		return "";
	}
}
