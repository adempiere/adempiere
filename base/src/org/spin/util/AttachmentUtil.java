/*************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                              *
 * This program is free software; you can redistribute it and/or modify it    		 *
 * under the terms version 2 or later of the GNU General Public License as published *
 * by the Free Software Foundation. This program is distributed in the hope   		 *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 		 *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           		 *
 * See the GNU General Public License for more details.                       		 *
 * You should have received a copy of the GNU General Public License along    		 *
 * with this program; if not, write to the Free Software Foundation, Inc.,    		 *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     		 *
 * For the text or an alternative of this public license, you may reach us    		 *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved. *
 * Contributor(s): Yamel Senih www.erpya.com				  		                 *
 *************************************************************************************/
package org.spin.util;

import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.spin.model.MADAppRegistration;
import org.spin.model.MADAppSupport;
import org.spin.model.MADAttachmentReference;
import org.spin.util.support.AppSupportHandler;
import org.spin.util.support.IAppSupport;
import org.spin.util.support.webdav.IWebDav;


/** Class for handle Attachment with a external storage
 * @author Yamel Senih, ySenih@erpya.com, ERPCyA http://www.erpya.com
 *		Add Support to external storage for attachment
 */
public class AttachmentUtil {
	
	/**	Instance	*/
	private static AttachmentUtil instance = null;
	/**	Client	*/
	private int clientId;
	/**	User	*/
	private int userId;
	/**	Context	*/
	private Properties context;
	/**	API	*/
	private IWebDav fileHandler;
	private int appRegistrationId;
	private String fileName;
	private byte[] data;
	private String note;
	private String description;
	private String path;
	private int attachmentId;
	private int imageId;
	private int archiveId;
	private String transactionName;
	private String baseFolder;
	
	private final String BASE_FOLDER_PARAMETER = "BaseFolder";
	
	/**
	 * Private constructor
	 */
	private AttachmentUtil(Properties context) {
		if(context == null) {
			throw new AdempiereException("@ContextIsMandatory@");
		}
		this.context = context;
	}
	
	/**
	 * Get instance from context
	 * @param context
	 * @return
	 */
	public static AttachmentUtil getInstance(Properties context) {
		return getInstance(context, Env.getAD_Client_ID(context), Env.getAD_User_ID(context));
	}
	
	/**
	 * Get instance for Attachment handler
	 * @param context
	 * @return
	 */
	public static AttachmentUtil getInstance(Properties context, int clientId, int userId) {
		if(instance == null) {
			instance = new AttachmentUtil(context);
		}
		//	
		return instance;
	}
	
	/**
	 * Get instance without context
	 * @return
	 */
	public static AttachmentUtil getInstance() {
		return getInstance(Env.getCtx());
	}
	
	/**
	 * Add App registration
	 * @param appRegistrationId
	 * @return
	 */
	public AttachmentUtil withAppRegistrationId(int appRegistrationId) {
		this.appRegistrationId = appRegistrationId;
		return this;
	}
	
	/**
	 * set file name
	 * @param fileName
	 * @return
	 */
	public AttachmentUtil withFileName(String fileName) {
		this.fileName = fileName;
		return this;
	}
	
	/**
	 * Set data for save
	 * @param data
	 * @return
	 */
	public AttachmentUtil withData(byte[] data) {
		this.data = data;
		return this;
	}
	
	/**
	 * Set client id
	 * @param clientId
	 * @return
	 */
	public AttachmentUtil withClientId(int clientId) {
		this.clientId = clientId;
		return this;
	}
	
	/**
	 * Set User id
	 * @param userId
	 * @return
	 */
	public AttachmentUtil withUserId(int userId) {
		this.userId = userId;
		return this;
	}
	
	/**
	 * Set note
	 * @param note
	 * @return
	 */
	public AttachmentUtil withNote(String note) {
		this.note = note;
		return this;
	}
	
	/**
	 * Set description
	 * @param description
	 * @return
	 */
	public AttachmentUtil withDescription(String description) {
		this.description = description;
		return this;
	}
	
	/**
	 * Set path for save file
	 * @param path
	 * @return
	 */
	public AttachmentUtil withPath(String path) {
		this.path = path;
		return this;
	}
	
	/**
	 * Add transaction name
	 * @param transactionName
	 * @return
	 */
	public AttachmentUtil withTansactionName(String transactionName) {
		this.transactionName = transactionName;
		return this;
	}
	
	/**
	 * Set Attachment reference
	 * @param attahcmentId
	 * @return
	 */
	public AttachmentUtil withAttachmentId(int attahcmentId ) {
		this.attachmentId = attahcmentId;
		return this;
	}
	
	/**
	 * Set Image reference
	 * @param imageId
	 * @return
	 */
	public AttachmentUtil withImageId(int imageId ) {
		this.imageId = imageId;
		return this;
	}
	
	/**
	 * Set archive reference
	 * @param archiveId
	 * @return
	 */
	public AttachmentUtil withArchiveId(int archiveId ) {
		this.archiveId = archiveId;
		return this;
	}
	
	/**
	 * Save Attachment
	 */
	public void saveAttachment() throws Exception {
		IWebDav handler = getFileHandler();
		MADAttachmentReference attachmentReference = new MADAttachmentReference(context, 0, transactionName);
		try {
			attachmentReference.setAD_AppRegistration_ID(appRegistrationId);
			attachmentReference.setFileName(fileName);
			//	Reference
			if(attachmentId > 0) {
				attachmentReference.setAD_Attachment_ID(attachmentId);
			}
			if(imageId > 0) {
				attachmentReference.setAD_Image_ID(imageId);
			}
			if(archiveId > 0) {
				attachmentReference.setAD_Archive_ID(archiveId);
			}
			//	Note
			if(!Util.isEmpty(note)) {
				attachmentReference.setTextMsg(note);
			}
			//	Description
			if(!Util.isEmpty(description)) {
				attachmentReference.setDescription(description);
			}
			//	Save reference
			attachmentReference.saveEx();
			if(data == null) {
				return;
			}
			//	Save
			String fileName = attachmentReference.getValidFileName();
			String validForlder = getValidFolder();
			String completePath = fileName;
			if(!Util.isEmpty(validForlder)) {
				//	Validate if exist
				if(!handler.exists(validForlder)) {
					handler.createDirectory(validForlder);
				}
				completePath = validForlder + "/" + fileName;
			}
			handler.putResource(completePath, data);
		} catch (Exception e) {
			if(attachmentReference.getAD_AttachmentReference_ID() > 0) {
				attachmentReference.deleteEx(true);
			}
			throw new AdempiereException(e);
		}
	}
	
	/**
	 * Get Valid Name
	 * @param attachmentFolder
	 * @param fileName
	 * @return
	 */
	private String getValidFolder() {
		String completePath = "";
		if(!Util.isEmpty(baseFolder)) {
			if(!Util.isEmpty(completePath)) {
				completePath = completePath + "/" + baseFolder;
			} else {
				completePath = baseFolder;
			}
		}
		if(!Util.isEmpty(path)) {
			if(!Util.isEmpty(completePath)) {
				completePath = completePath + "/" + path;
			} else {
				completePath = path;
			}
		}
		//	Return
		return completePath;
	}
	
	/**
	 * Get API
	 * @param appRegistrationId
	 * @return
	 */
	private IWebDav getFileHandler() throws Exception {
		if(fileHandler != null) {
			return fileHandler;
		}
		MADAppRegistration registration = null;
		if(appRegistrationId > 0) {
			registration = MADAppRegistration.getById(context, appRegistrationId, transactionName);
		} else if(userId > 0) {
			//	TODO: make support
		} else if(clientId > 0) {
			registration = MADAppRegistration.getByApplicationType(context, MADAppSupport.APPLICATIONTYPE_WebDavApplication, transactionName);
		}
		if(registration == null) {
			throw new AdempiereException("@AD_AppRegistration_ID@ @NotFound@");
		}
		baseFolder = registration.getParameterValue(BASE_FOLDER_PARAMETER);
		if(Util.isEmpty(baseFolder)) {
			baseFolder = "";
		} else if(baseFolder.endsWith("/")) {
			baseFolder.substring(0, baseFolder.length() - 1);
		}
		//	Load
		IAppSupport supportedApi = AppSupportHandler.getInstance().getAppSupport(MADAppRegistration.getById(context, appRegistrationId, transactionName));
		if(supportedApi == null) {
			throw new AdempiereException("@AD_AppSupport_ID@ @NotFound@");
		}
		if(!(supportedApi instanceof IWebDav)) {
			throw new AdempiereException("@AD_AppSupport_ID@ @Unsupported@");
		}
		//	
		fileHandler = (IWebDav) supportedApi;
		return fileHandler;
	}
}
