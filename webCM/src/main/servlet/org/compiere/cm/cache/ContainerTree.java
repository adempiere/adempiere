/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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

import java.util.*;

import org.compiere.cm.utils.TreeXML;
import org.compiere.model.MWebProject;

/**
 *	Container Tree Cache
 *	
 *  @author Yves Sandfort
 *  @version $Id$
 */
public class ContainerTree extends CO {
	
	protected Hashtable cacheContainerURL = new Hashtable(cacheSize);
	
	/**
	 * 	getContainerTree
	 *	@param ID
	 *	@param trxName
	 *	@return XML StringBuffer
	 */
	public StringBuffer getContainerTree(int ID, String trxName) {
		StringBuffer xmlCode = new StringBuffer();
		if (cache.containsKey("" + ID)) {
			use("" + ID);
			return (StringBuffer) cache.get("" + ID);
		} else {
			MWebProject thisProject = new MWebProject(ctx, ID, trxName);
			TreeXML thisTree = new TreeXML(thisProject);
			xmlCode.append(thisTree.getContainerXML());
			xmlCode.append(thisTree.getTreeXML());
			put ("" + ID, xmlCode);
			return xmlCode;
		}
	}
}
