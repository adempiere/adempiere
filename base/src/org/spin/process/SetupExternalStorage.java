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
import java.util.concurrent.atomic.AtomicInteger;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_AD_Attachment;
import org.compiere.model.MArchive;
import org.compiere.model.MAttachment;
import org.compiere.model.MClient;
import org.compiere.model.MImage;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.spin.model.MADAppRegistration;
import org.spin.model.MADAttachmentReference;
import org.spin.util.support.AppSupportHandler;
import org.spin.util.support.IAppSupport;
import org.spin.util.support.webdav.IWebDav;

/** Generated Process for (Setup External Storage for Files)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.2
 */
public class SetupExternalStorage extends SetupExternalStorageAbstract {

	/**	Processed Files	*/
	private AtomicInteger processed = new AtomicInteger();
	/**	Ignored Files	*/
	private AtomicInteger ignored = new AtomicInteger();
	/**	Errors	*/
	private AtomicInteger errors = new AtomicInteger();
	
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
		//	Attachment Directory
		String attachmentFolder = clientDirectory + "/" + "attachment";
		String imageFolder = clientDirectory + "/" + "image";
		String archiveFolder = clientDirectory + "/" + "archive";
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
		if(!webDavApi.exists(imageFolder)) {
			webDavApi.createDirectory(imageFolder);
		}
		//	Validate if exist
		if(!webDavApi.exists(archiveFolder)) {
			webDavApi.createDirectory(archiveFolder);
		}
		//	Validate if exist
		if(!webDavApi.exists(tmpFolder)) {
			webDavApi.createDirectory(tmpFolder);
		}
		//	Add Attachment
		migrateAttachment(webDavApi, attachmentFolder);
		//	Add Image
		migrateImage(webDavApi, imageFolder);
		//	Add Archive
		migrateArchive(webDavApi, archiveFolder);
		return "@Processed@ (" + processed.get() + ") @Ignored@ (" + ignored.get() + ") @Errors@ (" + errors.get() + ")";
	}
	
	/**
	 * Add attachment
	 * @param webDavApi
	 * @param attachmentFolder
	 */
	private void migrateAttachment(IWebDav webDavApi, String attachmentFolder) {
		addLog("@AD_Attachment_ID@");
		new Query(getCtx(), I_AD_Attachment.Table_Name, null, get_TrxName())
				.setClient_ID()
				.<MAttachment>list()
				.forEach(attachment -> {
					Arrays.asList(attachment.getEntries())
						.forEach(entry -> {
							//	Find existing reference
							int attachmentReferenceId = DB.getSQLValue(get_TrxName(), "SELECT AD_AttachmentReference_ID "
									+ "FROM AD_AttachmentReference "
									+ "WHERE AD_Attachment_ID = ? "
									+ "AND FileName = ?", attachment.getAD_Attachment_ID(), entry.getName());
							if(attachmentReferenceId < 0) {
								MADAttachmentReference attachmentReference = new MADAttachmentReference(getCtx(), 0, get_TrxName());
								try {
									attachmentReference.setAD_Attachment_ID(attachment.getAD_Attachment_ID());
									attachmentReference.setAD_AppRegistration_ID(getAppRegistrationId());
									attachmentReference.setFileName(entry.getName());
									attachmentReference.setDescription(Msg.getMsg(getCtx(), "CreatedFromSetupExternalStorage"));
									attachmentReference.saveEx();
									//	Save
									String fileName = attachmentReference.getValidFileName();
									//	Put it
									String completeFileName = attachmentFolder + "/" + fileName;
									webDavApi.putResource(completeFileName, entry.getData());
									addLog(fileName + ": @Ok@");
									processed.incrementAndGet();
								} catch (Exception e) {
									if(attachmentReference.getAD_AttachmentReference_ID() > 0) {
										attachmentReference.deleteEx(true);
									}
									log.warning("Error: " + e.getLocalizedMessage());
									addLog("@ErrorProcessingFile@ " + entry.getName() + ": " + e.getLocalizedMessage());
									errors.incrementAndGet();
								}
							} else {
								addLog(entry.getName() + ": @Ignored@");
								ignored.incrementAndGet();
							}
						});
				});
		
	}
	
	/**
	 * Add images
	 * @param webDavApi
	 * @param attachmentFolder
	 */
	private void migrateImage(IWebDav webDavApi, String attachmentFolder) {
		addLog("@AD_Image_ID@");
		KeyNamePair [] imageArray = DB.getKeyNamePairs("SELECT AD_Image_ID, Name "
				+ "FROM AD_Image "
				+ "WHERE AD_Client_ID = ? "
				+ "AND NOT EXISTS(SELECT 1 FROM AD_AttachmentReference ar WHERE ar.AD_Image_ID = AD_Image.AD_Image_ID)", false, getAD_Client_ID());
		Arrays.asList(imageArray)
			.forEach(imagePair -> {
				MImage image = MImage.get(getCtx(), imagePair.getKey());
				MADAttachmentReference attachmentReference = new MADAttachmentReference(getCtx(), 0, get_TrxName());
				try {
					attachmentReference.setAD_Image_ID(image.getAD_Image_ID());
					attachmentReference.setAD_AppRegistration_ID(getAppRegistrationId());
					attachmentReference.setFileName(image.getName());
					attachmentReference.setDescription(Msg.getMsg(getCtx(), "CreatedFromSetupExternalStorage"));
					attachmentReference.saveEx();
					//	Save
					String fileName = attachmentReference.getValidFileName();
					//	Put it
					String completeFileName = attachmentFolder + "/" + fileName;
					webDavApi.putResource(completeFileName, image.getData());
					addLog(fileName + ": @Ok@");
					processed.incrementAndGet();
				} catch (Exception e) {
					if(attachmentReference.getAD_AttachmentReference_ID() > 0) {
						attachmentReference.deleteEx(true);
					}
					log.warning("Error: " + e.getLocalizedMessage());
					addLog("@ErrorProcessingFile@ " + image.getName() + ": " + e.getLocalizedMessage());
					errors.incrementAndGet();
				}
			});
	}
	
	/**
	 * Add archive
	 * @param webDavApi
	 * @param attachmentFolder
	 */
	private void migrateArchive(IWebDav webDavApi, String attachmentFolder) {
		addLog("@AD_Archive_ID@");
		KeyNamePair [] archiveArray = DB.getKeyNamePairs("SELECT AD_Archive_ID, Name "
				+ "FROM AD_Archive "
				+ "WHERE AD_Client_ID = ? "
				+ "AND NOT EXISTS(SELECT 1 FROM AD_AttachmentReference ar WHERE ar.AD_Archive_ID = AD_Archive.AD_Archive_ID)", false, getAD_Client_ID());
		Arrays.asList(archiveArray)
			.forEach(archivePair -> {
				MArchive archive = new MArchive(getCtx(), archivePair.getKey(), get_TrxName());
				MADAttachmentReference attachmentReference = new MADAttachmentReference(getCtx(), 0, get_TrxName());
				try {
					attachmentReference.setAD_Archive_ID(archive.getAD_Archive_ID());
					attachmentReference.setAD_AppRegistration_ID(getAppRegistrationId());
					attachmentReference.setFileName(archive.getName());
					attachmentReference.setDescription(Msg.getMsg(getCtx(), "CreatedFromSetupExternalStorage"));
					attachmentReference.saveEx();
					//	Save
					String fileName = attachmentReference.getValidFileName() + ".pdf";
					//	Put it
					String completeFileName = attachmentFolder + "/" + fileName;
					webDavApi.putResource(completeFileName, archive.getBinaryData());
					addLog(fileName + ": @Ok@");
					processed.incrementAndGet();
				} catch (Exception e) {
					if(attachmentReference.getAD_AttachmentReference_ID() > 0) {
						attachmentReference.deleteEx(true);
					}
					log.warning("Error: " + e.getLocalizedMessage());
					addLog("@ErrorProcessingFile@ " + archive.getName() + ": " + e.getLocalizedMessage());
					errors.incrementAndGet();
				}
			});
	}
}