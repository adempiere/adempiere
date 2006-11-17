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

import org.compiere.model.MTemplate;

/**
 *  Template
 *  we create a cacheObject for the template.
 *
 *  @author Yves Sandfort
 *  @version  $Id$
 */
public class Template extends CO {
	/**
	 * get Template by ID
	 * @param ID
	 * @param CM_WebProject_ID
	 * @return MTemplate
	 */
	public MTemplate getCM_Template(int ID, int CM_WebProject_ID) {
		return getCM_Template(""+ ID, CM_WebProject_ID);
	}
	
	/**
	 * get Template by ID
	 * @param ID
	 * @param CM_WebProject_ID
	 * @return MTemplate
	 */
	public MTemplate getCM_Template(String ID, int CM_WebProject_ID) {
		if (cache.containsKey(ID)) {
			use(ID);
			return (MTemplate) cache.get(ID);
		} else {
			int[] tableKeys = MTemplate.getAllIDs("CM_Template", "CM_Template_ID=" + ID + " AND CM_WebProject_ID=" + CM_WebProject_ID, "WebCM");
			if (tableKeys.length==0) {
				// TODO Throw Template Exception
				return null;
			} else if (tableKeys.length==1) {
				MTemplate thisTemplate = new MTemplate(ctx, tableKeys[0], "WebCM");
				// We must prebuild the Template here, as we will not have Access to it later!
				thisTemplate.getPreBuildTemplate();
				put ("" + thisTemplate.getCM_Template_ID(),thisTemplate);
				return thisTemplate;
			} else {
				// More than one result, this is funny, normally this is not possible :-/
				return null;
			}
		}
	}
}
