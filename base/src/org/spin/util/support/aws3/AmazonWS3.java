/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 or later of the GNU General Public License       *
 *  as published                                                              *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2020 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************/
package org.spin.util.support.aws3;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.Env;
import org.compiere.util.MimeType;
import org.compiere.util.Util;
import org.spin.model.MADAppRegistration;
import org.spin.util.support.webdav.IWebDav;
import org.spin.util.support.webdav.IWebDavResource;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

/**
 * This is a implementation of Amazon S3 API for ADempiere
 * @author Yamel Senih, ySenih@erpya.com, ERPCyA http://www.erpya.com
 *	@see https://docs.aws.amazon.com/AmazonS3/latest/dev/ObjectOperations.html
 */
public class AmazonWS3 implements IWebDav {
	
	public AmazonWS3() {
		super();
	}
	
	/**	Registration Id	*/
	private int registrationId = 0;
	/**	Bucket Name	*/
	private String bucketName = null;
	/**	Base URL	*/
	private String baseURL = "";
	private final String ACCESS_KEY = "ACCESS_KEY";
    private final String SECRET_KEY = "SECRET_KEY";
    private final String BUCKET_REGION = "BUCKET_REGION";
    private final String BUCKET_NAME = "BUCKET_NAME";
	
	/**
	 * Get Path from relative path and base path
	 * @param relativePath
	 * @return
	 */
	private String getPath(String relativePath) {
		String path = baseURL;
		if(Util.isEmpty(relativePath)) {
			return path;
		}
		if(Util.isEmpty(path)) {
			if(relativePath.lastIndexOf(".") == -1) {
				relativePath += "/";
			}
			return relativePath;
		}
		//	Else
		if(baseURL.endsWith("/")) {
			path = path.substring(0, path.length() - 1);
		}
		if(relativePath.startsWith("/")) {
			relativePath = relativePath.substring(1);
		}
		if(relativePath.lastIndexOf(".") == -1) {
			relativePath += "/";
		}
		//	Return
		return path + "/" + relativePath;
	}
	
	
	
	/**
	 * Load Connection from values
	 */
	private MADAppRegistration getRegistrationInstance() {
		if(getAppRegistrationId() <= 0) {
			throw new AdempiereException("@AD_AppRegistration_ID@ @NotFound@");
		}
		MADAppRegistration registration = MADAppRegistration.getById(Env.getCtx(), getAppRegistrationId(), null);
		//	Access Key
		if(Util.isEmpty(registration.getParameterValue(ACCESS_KEY))) {
			throw new AdempiereException(ACCESS_KEY + " @NotFound@");
		}
		//	Secret Key
		if(Util.isEmpty(registration.getParameterValue(SECRET_KEY))) {
			throw new AdempiereException(SECRET_KEY + " @NotFound@");
		}
		//	Bucket End Point
		if(Util.isEmpty(registration.getHost())) {
			throw new AdempiereException("@Host@ @NotFound@");
		}
		//	Region Name
		if(Util.isEmpty(registration.getParameterValue(BUCKET_REGION))) {
			throw new AdempiereException(BUCKET_REGION + " @NotFound@");
		}
		//	Bucket Name
		if(Util.isEmpty(registration.getParameterValue(BUCKET_NAME))) {
			throw new AdempiereException(BUCKET_NAME + " @NotFound@");
		}
		//	Set bucket name
		bucketName = registration.getParameterValue(BUCKET_NAME);
		//	Return registration
		return registration;
	}
	
	/**
	 * Get Bucket Name from configuration
	 * @return
	 */
	private String getBucketName() {
		if(Util.isEmpty(bucketName)) {
			getRegistrationInstance();
		}
		return bucketName;
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
	 * Get S3 Instance
	 * @return
	 */
	private AmazonS3 getS3Instance() {
		MADAppRegistration registration = getRegistrationInstance();
		return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(
                		new BasicAWSCredentials(
                				registration.getParameterValue(ACCESS_KEY), 
                				registration.getParameterValue(SECRET_KEY)
                				)
                		))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                		registration.getHost(), 
                		registration.getParameterValue(BUCKET_REGION)))
                .build();
	}
	
	@Override
	public void setAppRegistrationId(int registrationId) {
		this.registrationId = registrationId;
	}

	@Override
	public int getAppRegistrationId() {
		return registrationId;
	}
	
	@Override
	public InputStream getResource(String relativePath) throws Exception {
		if(Util.isEmpty(relativePath)) {
			return null;
		}
		AmazonS3 s3Client = getS3Instance();
		//	Retrieve object
		S3Object resource = null;
		resource = s3Client.getObject(new GetObjectRequest(getBucketName(), getPath(relativePath)));
		//	Prevent NPE
		if(resource == null) {
			return null;
		}
		return resource.getObjectContent();
	}

	@Override
	public void putResource(String relativePath, InputStream resource) throws Exception {
		if(Util.isEmpty(relativePath)
				|| resource == null) {
			return;
		}
		if(Util.isEmpty(relativePath)
				|| resource == null) {
			return;
		}
		AmazonS3 s3Client = getS3Instance();
		//	Set metadata
	    ObjectMetadata metadata = new ObjectMetadata();
	    metadata.setContentLength(resource.available());
	    String fileName = getValidFileName(relativePath);
	    metadata.setContentType(MimeType.getMimeType(fileName));
	    //	Put object for bucket
	    s3Client.putObject(getBucketName(), getPath(relativePath), resource, metadata);
	}

	@Override
	public void deleteResource(String relativePath) throws Exception {
		if(Util.isEmpty(relativePath)) {
			return;
		}
		AmazonS3 s3Client = getS3Instance();
		//	Delete object
		s3Client.deleteObject(new DeleteObjectRequest(getBucketName(), getPath(relativePath)));
	}

	@Override
	public void createDirectory(String relativePathName) throws Exception {
		if(Util.isEmpty(relativePathName)) {
			return;
		}
		AmazonS3 s3Client = getS3Instance();
		ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(0);
        //	
        InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
        PutObjectRequest putObjectRequest = new PutObjectRequest(getBucketName(), getPath(relativePathName), emptyContent, metadata);
        //	Create
        s3Client.putObject(putObjectRequest);
	}

	@Override
	public void moveResource(String relativeSourcePath, String relativeTargetPath) throws Exception {
		if(Util.isEmpty(relativeSourcePath)
				|| Util.isEmpty(relativeTargetPath)) {
			return;
		}
		copyResource(relativeSourcePath, relativeTargetPath);
		deleteResource(relativeSourcePath);
	}

	@Override
	public void copyResource(String relativeSourcePath, String relativeTargetPath) throws Exception {
		if(Util.isEmpty(relativeSourcePath)
				|| Util.isEmpty(relativeTargetPath)) {
			return;
		}
		AmazonS3 s3Client = getS3Instance();
		CopyObjectRequest copyObjRequest = new CopyObjectRequest(getBucketName(), getPath(relativeSourcePath), getBucketName(), getPath(relativeTargetPath));
        s3Client.copyObject(copyObjRequest);
	}

	@Override
	public boolean exists(String relativePath) throws Exception {
		if(Util.isEmpty(relativePath)) {
			return false;
		}
		//	Verify if exist a object
		return getS3Instance().doesObjectExist(getBucketName(), getPath(relativePath));
	}

	@Override
	public List<IWebDavResource> getResourceList(String relativePath) throws Exception {
		AmazonS3 s3Client = getS3Instance();
		List<IWebDavResource> resources = new ArrayList<>();
		s3Client
			.listObjects(getBucketName(), getPath(relativePath))
			.getObjectSummaries()
			.forEach(resource -> resources.add(new AmazonWS3Resource(resource)));
		return resources;
	}

	@Override
	public void putResource(String relativePath, byte[] resource) throws Exception {
		if(Util.isEmpty(relativePath)
				|| resource == null) {
			return;
		}
		AmazonS3 s3Client = getS3Instance();
		InputStream inputStream = new ByteArrayInputStream(resource);
		//	Set metadata
	    ObjectMetadata metadata = new ObjectMetadata();
	    metadata.setContentLength(resource.length);
	    String fileName = getValidFileName(relativePath);
	    metadata.setContentType(MimeType.getMimeType(fileName));
	    //	Put object for bucket
	    s3Client.putObject(getBucketName(), getPath(relativePath), inputStream, metadata);
	}
	
	@Override
	public String testConnection() {
		StringBuffer message = new StringBuffer();
		try {
			List<IWebDavResource> resources = getResourceList(null);
			for(IWebDavResource resource : resources) {
				if(message.length() > 0) {
					message.append(Env.NL);
				}
				message.append(resource);
			}
		} catch (Exception e) {
			message.append(e.getLocalizedMessage());
		}
		return message.toString();
	}
	
	public static void main(String[] args) {
		
	}
}
