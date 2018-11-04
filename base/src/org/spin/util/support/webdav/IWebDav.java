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
import java.util.List;

import org.spin.util.support.IAppSupport;

/**
 * @author Yamel Senih, ySenih@erpya.com, ERPCyA http://www.erpya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/2109">
 * 		@see FR [ 2109 ] Add App Registration ADempiere</a>
 */
public interface IWebDav extends IAppSupport {

	/**
	 * Get resource based on base URL + relative path
	 * @param relativePath
	 * @return
	 * @throws Exception
	 */
	public InputStream getResource(String relativePath) throws Exception;
	
	/**
	 * Put a resource
	 * @param relativePath
	 * @param resource
	 * @throws Exception
	 */
	public void putResource(String relativePath, InputStream resource) throws Exception;
	
	/**
	 * Put a resource as byte array
	 * @param relativePath
	 * @param resource
	 * @throws Exception
	 */
	public void putResource(String relativePath, byte[] resource) throws Exception;
	
	/**
	 * Delete a Resource
	 * @param relativePath
	 * @throws Exception
	 */
	public void deleteResource(String relativePath) throws Exception;
	
	/**
	 * Create a Directory
	 * @param relativePathName
	 * @throws Exception
	 */
	public void createDirectory(String relativePathName) throws Exception;
	
	/**
	 * Move File
	 * @param relativeSourcePath
	 * @param relativeTargetPath
	 */
	public void moveResource(String relativeSourcePath, String relativeTargetPath) throws Exception;
	
	/**
	 * Copy a File
	 * @param relativeSourcePath
	 * @param relativeTargetPath
	 */
	public void copyResource(String relativeSourcePath, String relativeTargetPath) throws Exception;
	
	
	/**
	 * Verify if a file exists
	 * @param relativePath
	 * @return
	 */
	public boolean exists(String relativePath) throws Exception;
	
	
	/**
	 * Get Resource List
	 * @param relativePath
	 * @return
	 */
	public List<IWebDavResource> getResourceList(String relativePath) throws Exception;
}
