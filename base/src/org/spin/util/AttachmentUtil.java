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

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MClientInfo;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.spin.model.MADAppRegistration;
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
	/**	Context	*/
	private Properties context;
	/**	API	*/
	private IWebDav fileHandler;
	private int fileHandlerId;
	private String fileName;
	private byte[] data;
	private String note;
	private String description;
	private int attachmentId;
	private int attachmentReferenceId;
	private int imageId;
	private int archiveId;
	private String transactionName;
	private String baseFolder;
	private String imageFolder;
	private String attachmentFolder;
	private String archiveFolder;
	private String tmpFolder;
	
	private final String BASE_FOLDER_PARAMETER = "BaseFolder";
	private final String IMAGE_FOLDER_PARAMETER = "ImageFolder";
	private final String ATTACHMENT_FOLDER_PARAMETER = "AttachmentFolder";
	private final String ARCHIVE_FOLDER_PARAMETER = "ArchiveFolder";
	private final String TMP_FOLDER_PARAMETER = "TmpFolder";
	
	/**
	 * Private constructor
	 */
	private AttachmentUtil(Properties context) {
		if(context == null) {
			throw new AdempiereException("@ContextIsMandatory@");
		}
		this.context = context;
		this.clientId = Env.getAD_Client_ID(context);
	}
	
	/**
	 * Get instance for Attachment handler
	 * @param context
	 * @return
	 */
	public static AttachmentUtil getInstance(Properties context) {
		if(instance == null) {
			instance = new AttachmentUtil(context);
		}
		return instance;
	}
	
	/**
	 * Get Instance with default context
	 * @return
	 */
	public static AttachmentUtil getInstance() {
		return getInstance(Env.getCtx());
	}
	
	/**
	 * Add App registration
	 * @param fileHandlerId
	 * @return
	 */
	public AttachmentUtil withFileHandlerId(int fileHandlerId) {
		this.fileHandlerId = fileHandlerId;
		this.fileHandler = null;
		return this;
	}
	
	/**
	 * Is valid for client or setup was run for this client
	 * @param clientId
	 * @return
	 */
	public boolean isValidForClient(int clientId) {
		MClientInfo clientInfo = MClientInfo.get(context, clientId, transactionName);
		return clientInfo.getFileHandler_ID() > 0;
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
	 * Get file name
	 * @return
	 */
	public String getFileName() {
		//	Convert
		return getValidFileName(this.fileName);
	}
	
	/**
	 * Get valid file Name
	 * @param fileName
	 * @return
	 */
	private String getValidFileName(String fileName) {
		if(Util.isEmpty(fileName)) {
			return fileName;
		}
		int index = fileName.lastIndexOf("/");
		if(index == -1) {
			index = fileName.lastIndexOf("\\");
		}
		if(index != -1) {
			fileName = fileName.substring(index + 1);
		}
		return fileName.replaceAll("[+^:&áàäéèëíìïóòöúùñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ$()*#/><]", "")
				.replaceAll(" ", "-");
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
		this.fileHandlerId = 0;
		this.fileHandler = null;
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
	 * Get Note
	 * @return
	 */
	public String getNote() {
		return this.note;
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
	 * Get description
	 * @return
	 */
	public String getDescription() {
		return this.description;
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
		this.imageId = 0;
		this.archiveId = 0;
		this.attachmentReferenceId = 0;
		return this;
	}
	
	/**
	 * Set Attachment reference
	 * @param attachmentReferenceId
	 * @return
	 */
	public AttachmentUtil withAttachmentReferenceId(int attachmentReferenceId ) {
		this.attachmentReferenceId = attachmentReferenceId;
		this.attachmentId = 0;
		this.imageId = 0;
		this.archiveId = 0;
		return this;
	}
	
	/**
	 * Set Image reference
	 * @param imageId
	 * @return
	 */
	public AttachmentUtil withImageId(int imageId ) {
		this.imageId = imageId;
		this.archiveId = 0;
		this.attachmentId = 0;
		this.attachmentReferenceId = 0;
		return this;
	}
	
	/**
	 * Set archive reference
	 * @param archiveId
	 * @return
	 */
	public AttachmentUtil withArchiveId(int archiveId ) {
		this.archiveId = archiveId;
		this.imageId = 0;
		this.attachmentId = 0;
		this.attachmentReferenceId = 0;
		return this;
	}
	
	/**
	 * Clear
	 * @return
	 */
	public AttachmentUtil clear() {
		this.archiveId = 0;
		this.imageId = 0;
		this.attachmentId = 0;
		this.attachmentReferenceId = 0;
		this.fileName = null;
		this.description = null;
		this.note = null;
		this.transactionName = null;
		this.fileHandlerId = 0;
		this.fileHandler = null;
		return this;
	}
	
	/**
	 * Get Attachment reference
	 * @return
	 */
	private MADAttachmentReference getAttachmentReference() {
		MADAttachmentReference attachmentReference = null;
		if(attachmentReferenceId > 0) {
			attachmentReference = MADAttachmentReference.getById(context, attachmentReferenceId, transactionName);
		} else if(attachmentId > 0) {
			attachmentReference = MADAttachmentReference.getByAttachmentId(context, fileHandlerId, attachmentId, getFileName(), transactionName);
		} else if(imageId > 0) {
			attachmentReference = MADAttachmentReference.getByImageId(context, fileHandlerId, imageId, transactionName);
		} else if(archiveId > 0) {
			attachmentReference = MADAttachmentReference.getByArchiveId(context, fileHandlerId, archiveId, transactionName);
		}
		return attachmentReference;
	}
	
	/**
	 * Get Attachment from data
	 * @return
	 * @throws Exception
	 */
	public byte[] getAttachment() throws Exception {
		IWebDav handler = getFileHandler();
		MADAttachmentReference attachmentReference = getAttachmentReference();
		//	Validate
		if(attachmentReference == null) {
			throw new AdempiereException("@AD_AttachmentReference_ID@ @NotFound@");
		}
		//	Populate attributes
		fileName = attachmentReference.getFileName();
		description = attachmentReference.getDescription();
		note = attachmentReference.getTextMsg();
		//	Get data
		InputStream inputStream = handler.getResource(getCompleteFileName(attachmentReference));
		if(inputStream == null) {
			throw new AdempiereException("@FileName@ @NotFound@");
		}
		return readBytesFromAttachment(inputStream);
	}
	
	/**
	 * Get file name list for Attachments
	 * @return
	 */
	public List<String> getFileNameListFromAttachment() {
		try {
			getFileHandler();
		} catch (Exception e) {
			throw new AdempiereException(e);
		}
		List<MADAttachmentReference> list = MADAttachmentReference.getListByAttachmentId(context, fileHandlerId, attachmentId, transactionName);
		List<String> fileNameList = new ArrayList<String>();
		if(list != null) {
			list.stream().forEach(attachmentReference -> {
				fileNameList.add(attachmentReference.getFileName());
			});
		}
		return fileNameList;
	}
	
	/**
	 * Read bytes
	 * @param stream
	 * @return
	 * @throws Exception
	 */
	private byte[] readBytesFromAttachment(InputStream stream) throws Exception {
		if (stream == null) return new byte[] {};
	      byte[] buffer = new byte[1024];
	      ByteArrayOutputStream attachmentToWrite = new ByteArrayOutputStream();
	      boolean error = false;
	      try {
	          int numRead = 0;
	          while ((numRead = stream.read(buffer)) > -1) {
	              attachmentToWrite.write(buffer, 0, numRead);
	          }
	      } catch (Exception e) {
	          error = true;
	          throw e;
	      } finally {
	          try {
	              stream.close();
	          } catch (Exception e) {
	              if (!error) {
	            	  throw e;
	              }
	          }
	      }
	      attachmentToWrite.flush();
	      return attachmentToWrite.toByteArray();
	}
	
	/**
	 * Save Attachment
	 */
	public void saveAttachment() throws Exception {
		IWebDav handler = getFileHandler();
		MADAttachmentReference attachmentReference = getAttachmentReference();
		if(attachmentReference == null
				|| attachmentReference.getAD_AttachmentReference_ID() <= 0) {
			attachmentReference = new MADAttachmentReference(context, 0, transactionName);
		}
		try {
			attachmentReference.setFileHandler_ID(fileHandlerId);
			attachmentReference.setFileName(getFileName());
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
			//	Remove from cache
			MADAttachmentReference.resetAttachmentCacheFromId(fileHandlerId, attachmentId);
			if(data == null) {
				return;
			}
			//	Set file size
			attachmentReference.setFileSize(new BigDecimal(data.length));
			//	Save
			attachmentReference.saveEx();
			//	Save
			String validForlder = getValidFolder(attachmentReference);
			if(!Util.isEmpty(baseFolder)) {
				//	Validate if exist
				if(!handler.exists(baseFolder)) {
					handler.createDirectory(baseFolder);
				}
			}
			if(!Util.isEmpty(validForlder)) {
				//	Validate if exist
				if(!handler.exists(validForlder)) {
					handler.createDirectory(validForlder);
				}
			}
			handler.putResource(getCompleteFileName(attachmentReference), data);
		} catch (Exception e) {
			if(attachmentReference.getAD_AttachmentReference_ID() > 0) {
				attachmentReference.deleteEx(true);
			}
			throw new AdempiereException(e);
		}
	}
	
	/**
	 * Delete reference
	 * @throws Exception
	 */
	public void deleteAttachment() throws Exception {
		IWebDav handler = getFileHandler();
		MADAttachmentReference attachmentReference = getAttachmentReference();
		if(attachmentReference == null
				|| attachmentReference.getAD_AttachmentReference_ID() <= 0) {
			return;
		}
		try {
			//	Save
			handler.deleteResource(getCompleteFileName(attachmentReference));
			//	Remove from cache
			MADAttachmentReference.resetAttachmentReferenceCache(fileHandlerId, attachmentReference);
			//	Delete reference
			attachmentReference.deleteEx(true);
		} catch (Exception e) {
			throw new AdempiereException(e);
		}
	}
	
	/**
	 * Get complete path from attachment reference
	 * @param attachmentReference
	 * @return
	 */
	private String getCompleteFileName(MADAttachmentReference attachmentReference) {
		String fileName = attachmentReference.getValidFileName();
		String validForlder = getValidFolder(attachmentReference);
		String completePath = fileName;
		if(!Util.isEmpty(validForlder)) {
			completePath = addSubFolder(validForlder, fileName);
		}
		return completePath;
	}
	
	/**
	 * Get valid folder for all
	 * @param attachmentReference
	 * @return
	 */
	private String getValidFolder(MADAttachmentReference attachmentReference) {
		String validForlder = "";
		//	For attachment
		if(attachmentReference.getAD_Attachment_ID() > 0) {
			validForlder = addSubFolder(baseFolder, attachmentFolder);
		} else if(attachmentReference.getAD_Image_ID() > 0) {
			validForlder = addSubFolder(baseFolder, imageFolder);
		} else if(attachmentReference.getAD_Archive_ID() > 0) {
			validForlder = addSubFolder(baseFolder, archiveFolder);
		}
		if(Util.isEmpty(validForlder)) {
			validForlder = "";
		}
		return validForlder;
	}
	
	/**
	 * Get and Add sub-folder
	 * @param baseFolder
	 * @param subFolder
	 * @return
	 */
	private String addSubFolder(String baseFolder, String subFolder) {
		String completePath = "";
		if(!Util.isEmpty(baseFolder)) {
			completePath = baseFolder;
		}
		//	Sub-Folder
		if(!Util.isEmpty(completePath)) {
			completePath = completePath + "/" + subFolder;
		} else {
			completePath = subFolder;
		}
		return completePath;
	}
	
	/**
	 * Get File Handler from client and set current file handler Id
	 * @return
	 */
	private MADAppRegistration getFileHandlerFromClient() {
		MClientInfo clientInfo = MClientInfo.get(context, clientId, transactionName);
		fileHandlerId = clientInfo.getFileHandler_ID();
		return MADAppRegistration.getById(context, fileHandlerId, transactionName);
	}
	
	/**
	 * Get API
	 * @param fileHandlerId
	 * @return
	 */
	private IWebDav getFileHandler() throws Exception {
		if(fileHandler != null) {
			return fileHandler;
		}
		MADAppRegistration registration = null;
		if(fileHandlerId > 0) {
			registration = MADAppRegistration.getById(context, fileHandlerId, transactionName);
		} else if(clientId > 0) {
			if(isValidForClient(clientId)) {
				registration = getFileHandlerFromClient();
			}
		}
		if(registration == null) {
			throw new AdempiereException("@AD_AppRegistration_ID@ @NotFound@");
		}
		//	Process Folders
		processFolder(registration);
		//	Load
		IAppSupport supportedApi = AppSupportHandler.getInstance().getAppSupport(MADAppRegistration.getById(context, fileHandlerId, transactionName));
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
	
	/**
	 * Process folder for a valid name
	 * @param folder
	 * @return
	 */
	private void processFolder(MADAppRegistration registration) {
		baseFolder = getValidFolder(registration.getParameterValue(BASE_FOLDER_PARAMETER));
		imageFolder = getValidFolder(registration.getParameterValue(IMAGE_FOLDER_PARAMETER));
		if(Util.isEmpty(imageFolder)) {
			imageFolder = "Images";
		}
		attachmentFolder = getValidFolder(registration.getParameterValue(ATTACHMENT_FOLDER_PARAMETER));
		if(Util.isEmpty(attachmentFolder)) {
			attachmentFolder = "Attachments";
		}
		archiveFolder = getValidFolder(registration.getParameterValue(ARCHIVE_FOLDER_PARAMETER));
		if(Util.isEmpty(archiveFolder)) {
			archiveFolder = "Archives";
		}
		tmpFolder = getValidFolder(registration.getParameterValue(TMP_FOLDER_PARAMETER));
		if(Util.isEmpty(tmpFolder)) {
			tmpFolder = "Tmp";
		}
	}
	
	/**
	 * get valid Folder Name
	 * @param folder
	 * @return
	 */
	private String getValidFolder(String folder) {
		if(Util.isEmpty(folder)) {
			folder = "";
		} else if(folder.endsWith("/")) {
			folder.substring(0, folder.length() - 1);
		}
		return folder;
	}
}
