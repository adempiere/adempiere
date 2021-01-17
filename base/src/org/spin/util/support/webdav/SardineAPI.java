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
package org.spin.util.support.webdav;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.spin.model.MADAppRegistration;

import com.github.sardine.DavResource;
import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;

/**
 * @author Yamel Senih, ySenih@erpya.com, ERPCyA http://www.erpya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/2109">
 * 		@see FR [ 2109 ] Add App Registration ADempiere</a>
 */
public class SardineAPI implements IWebDav {
	
	public SardineAPI() {
		super();
	}
	
	/**	Registration Id	*/
	private int registrationId = 0;
	/**	Connection	*/
	private Sardine connection = null;
	/**	Base URL	*/
	private String baseURL = null;
	
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
		//	Else
		if(baseURL.endsWith("/")) {
			path = path.substring(0, path.length() - 1);
		}
		if(relativePath.startsWith("/")) {
			relativePath = relativePath.substring(1);
		}
		//	Return
		return path + "/" + relativePath;
	}
	
	/**
	 * Load Connection from values
	 */
	private void loadConnection() {
		if(connection != null) {
			return;
		}
		if(getAppRegistrationId() <= 0) {
			throw new AdempiereException("@AD_AppRegistration_ID@ @NotFound@");
		}
		//	
		MADAppRegistration registration = MADAppRegistration.getById(Env.getCtx(), getAppRegistrationId(), null);
		String userName = registration.getParameterValue("UserName");
		String password = registration.getParameterValue("Password");
		baseURL = registration.getParameterValue("BaseURL");
		if(Util.isEmpty(userName)) {
			throw new AdempiereException("@AD_User_ID@ @NotFound@");
		}
		if(Util.isEmpty(password)) {
			throw new AdempiereException("@Password@ @NotFound@");
		}
		if(Util.isEmpty(baseURL)) {
			throw new AdempiereException("@URL@ @NotFound@");
		}
		//	
		connection = SardineFactory.begin(userName, password);
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
		loadConnection();
		return connection.get(getPath(relativePath));
	}

	@Override
	public void putResource(String relativePath, InputStream resource) throws Exception {
		if(Util.isEmpty(relativePath)
				|| resource == null) {
			return;
		}
		loadConnection();
		connection.put(getPath(relativePath), resource);
	}

	@Override
	public void deleteResource(String relativePath) throws Exception {
		if(Util.isEmpty(relativePath)) {
			return;
		}
		loadConnection();
		connection.delete(getPath(relativePath));
	}

	@Override
	public void createDirectory(String relativePathName) throws Exception {
		if(Util.isEmpty(relativePathName)) {
			return;
		}
		loadConnection();
		connection.createDirectory(getPath(relativePathName));
	}

	@Override
	public void moveResource(String relativeSourcePath, String relativeTargetPath) throws Exception {
		if(Util.isEmpty(relativeSourcePath)
				|| Util.isEmpty(relativeTargetPath)) {
			return;
		}
		loadConnection();
		connection.move(getPath(relativeSourcePath), getPath(relativeTargetPath));
	}

	@Override
	public void copyResource(String relativeSourcePath, String relativeTargetPath) throws Exception {
		if(Util.isEmpty(relativeSourcePath)
				|| Util.isEmpty(relativeTargetPath)) {
			return;
		}
		loadConnection();
		connection.copy(getPath(relativeSourcePath), getPath(relativeTargetPath));
	}

	@Override
	public boolean exists(String relativePath) throws Exception {
		if(Util.isEmpty(relativePath)) {
			return false;
		}
		loadConnection();
		return connection.exists(getPath(relativePath));
	}

	@Override
	public List<IWebDavResource> getResourceList(String relativePath) throws Exception {
		loadConnection();
		List<IWebDavResource> resources = new ArrayList<>();
		List<DavResource> davResources = connection.list(getPath(relativePath));
		for(DavResource resource : davResources) {
			resources.add(new SardineResource(resource));
		}
		return resources;
	}

	@Override
	public void putResource(String relativePath, byte[] resource) throws Exception {
		if(Util.isEmpty(relativePath)
				|| resource == null) {
			return;
		}
		loadConnection();
		connection.put(getPath(relativePath), resource);
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
}
