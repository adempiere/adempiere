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

package org.spin.process;

import java.util.Arrays;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_AD_Attachment;
import org.compiere.model.MAttachment;
import org.compiere.model.MClient;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Util;
import org.spin.model.MADAppRegistration;
import org.spin.util.support.AppSupportHandler;
import org.spin.util.support.IAppSupport;
import org.spin.util.support.webdav.IWebDav;

/** Generated Process for (Setup External Storage for Files)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.2
 */
public class SetupExternalStorage extends SetupExternalStorageAbstract {

	@Override
	protected String doIt() throws Exception {
		IAppSupport supportedApi = AppSupportHandler.getInstance().getAppSupport(MADAppRegistration.getById(getCtx(), getAppRegistrationId(), get_TrxName()));
		if(supportedApi == null) {
			throw new AdempiereException("@AD_AppSupport_ID@ @NotFound@");
		}
		if(!(supportedApi instanceof IWebDav)) {
			throw new AdempiereException("@AD_AppSupport_ID@ @Unsupported@");
		}
		IWebDav webDavApi = (IWebDav) supportedApi;
		String clientDirectory = MClient.get(getCtx()).getUUID();
		if(Util.isEmpty(clientDirectory)) {
			throw new AdempiereException("@AD_Client_ID@ [@UUID@ @NotFound@]");
		}
		clientDirectory = getValidName(clientDirectory);
		//	Attachment Directory
		String attachmentFolder = clientDirectory + "/" + "attachment";
		String tmpFolder = clientDirectory + "/" + "tmp";
		//	Validate if exist
		if(!webDavApi.exists(clientDirectory)) {
			webDavApi.createDirectory(clientDirectory);
		}
		//	Validate if exist
		if(!webDavApi.exists(attachmentFolder)) {
			webDavApi.createDirectory(attachmentFolder);
		}
		//	Validate if exist
		if(!webDavApi.exists(tmpFolder)) {
			webDavApi.createDirectory(tmpFolder);
		}
		//	Add Attachment
		migrateAttachment(webDavApi, attachmentFolder);
		return "Ok";
	}
	
	/**
	 * Add attachment
	 * @param webDavApi
	 * @param attachmentFolder
	 */
	private void migrateAttachment(IWebDav webDavApi, String attachmentFolder) {
		new Query(getCtx(), I_AD_Attachment.Table_Name, null, get_TrxName())
				.setClient_ID()
				.<MAttachment>list()
				.forEach(attachment -> {
					Arrays.asList(attachment.getEntries())
						.forEach(entry -> {
							String fileName = DB.getUUID(get_TrxName());
							if(!Util.isEmpty(entry.getName())) {
								fileName = fileName + "-" + getValidName(entry.getName());
							}
							//	Put it
							String completeFileName = attachmentFolder + "/" + fileName;
							try {
								webDavApi.putResource(completeFileName, entry.getData());
								addLog(fileName + ": @Ok@");
							} catch (Exception e) {
								log.warning("Error: " + e.getLocalizedMessage());
								addLog("@ErroProcessingFile@ " + fileName + ": " + e.getLocalizedMessage());
							}
						});
				});
		
	}
	
	/**
	 * Get Valid Name for file
	 * @param name
	 * @return
	 */
	private String getValidName(String name) {
		if(Util.isEmpty(name)) {
			name = "";
		}
		return name.replaceAll("[+^:&áàäéèëíìïóòöúùñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ$()]", "")
				.replaceAll("[ ]", "-");
	}
}