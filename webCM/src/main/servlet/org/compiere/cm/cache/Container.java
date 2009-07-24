/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.cm.cache;

import java.util.Hashtable;

import org.compiere.model.MContainer;

/**
 * Container Cache
 * 
 * @author Yves Sandfort
 * @version $ID$
 */
public class Container extends CO {
	
	protected Hashtable cacheContainerURL = new Hashtable(cacheSize);
	
	/**
	 * Get Container from cache by ID
	 * @param ID Container ID
	 * @param CM_WebProject_ID Web Project
	 * @return Container
	 */
	public MContainer getCM_Container(String ID, int CM_WebProject_ID) {
		return getCM_Container(Integer.parseInt(ID), CM_WebProject_ID);
	}
	
	/**
	 * Get Container from cache by ID
	 * @param ID Container ID
	 * @param CM_WebProject_ID Web Project
	 * @return Container
	 */
	public MContainer getCM_Container(int ID, int CM_WebProject_ID) {
		if (cache.containsKey(ID)) {
			use(ID);
			return (MContainer) cache.get(ID);
		} else {
			MContainer thisContainer = MContainer.get(ctx, ID, CM_WebProject_ID, "WebCM");
			if (thisContainer==null) 
			{
				return getCM_ContainerByURL("/error404.html", CM_WebProject_ID, true);
			} 
			else
			{
				put ("" + thisContainer.getCM_Container_ID(),thisContainer);
				cacheContainerURL.put (CM_WebProject_ID + "-" + thisContainer.getRelativeURL(),"" + thisContainer.getCM_Container_ID());
				return thisContainer;
			}
		}
	}
	
	/**
	 * Get Container from cache by URL
	 * @param URL URL to look for
	 * @param CM_WebProject_ID Web Project 
	 * @param resolveURLErrors 
	 * @return Container
	 */
	public MContainer getCM_ContainerByURL(String URL, int CM_WebProject_ID, boolean resolveURLErrors) {
		if (cacheContainerURL.containsKey(CM_WebProject_ID + "-" + URL)) {
			// Found a hit, so return it...
			// Found exactly one record, so we return it
			MContainer thisContainer = getCM_Container((String) cacheContainerURL.get(CM_WebProject_ID + "-" + URL), CM_WebProject_ID);
			if (thisContainer.isSummary ()) {
				thisContainer = getCM_ContainerByURL(URL + "/index.html", CM_WebProject_ID, resolveURLErrors);
			}
			return thisContainer;
		} else {
			// Let's try to find the URL...
			MContainer thisContainer = MContainer.get (ctx, URL, CM_WebProject_ID, "WebCM");
			if (thisContainer==null) {
				if (resolveURLErrors) {
					if (URL.equals("/error404.html")) {
						// Okay we are already been requested as the error message, so we try the index.html
						MContainer tempContainer = getCM_ContainerByURL("/index.html", CM_WebProject_ID, false);
						if (tempContainer!=null) 
							return tempContainer;
						else 
							return null;
					} else {
						return getCM_ContainerByURL("/error404.html", CM_WebProject_ID, true);
					}
				} else {
					return null;
				}
			} else {
				// Found exactly one record, so we return it
				if (thisContainer.isSummary ()) {
					thisContainer = getCM_ContainerByURL(URL + "/index.html", CM_WebProject_ID, resolveURLErrors);
				}
				return thisContainer;
			}
		}
	}
}
