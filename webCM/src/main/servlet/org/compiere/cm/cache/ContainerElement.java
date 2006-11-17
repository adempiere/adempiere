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

import org.compiere.model.X_CM_Container_Element;

public class ContainerElement extends CO {
	public X_CM_Container_Element getCM_Container_Element(int ID, int CM_WebProject_ID) {
		return getCM_Container_Element(""+ ID, CM_WebProject_ID);
	}
	
	public X_CM_Container_Element getCM_Container_Element(String ID, int CM_WebProject_ID) {
		if (cache.containsKey(ID)) {
			use(ID);
			return (X_CM_Container_Element) cache.get(ID);
		} else {
			int[] tableKeys = X_CM_Container_Element.getAllIDs("CM_Container_Element", "CM_Container_Element_ID=" + ID, "WebCM");
			if (tableKeys==null || tableKeys.length==0) {
				// No Elements in DB found, needs to get handled
				return null;
			} else if (tableKeys.length==1) {
				X_CM_Container_Element thisContainerElement = new X_CM_Container_Element(ctx, tableKeys[0], "WebCM"); 
				put ("" + thisContainerElement.get_ID(),thisContainerElement);
				return thisContainerElement;
			} else {
				// More than one result, this is funny, normally this is not possible :-/
				return null;
			}
		}
	}

}
