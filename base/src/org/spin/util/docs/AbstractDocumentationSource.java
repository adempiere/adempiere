/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.spin.util.docs;

import org.compiere.model.M_Element;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.compiere.util.Util;

/**
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * It is a abstract class for used as contract of document converter
 * @see: https://github.com/adempiere/adempiere/issues/1934
 * For formst reference use: http://www.sphinx-doc.org/en/master/usage/restructuredtext/basics.html
 */
public abstract class AbstractDocumentationSource {
	
	/**
	 * Convert a simple PO in a textConverter
	 * @param textConverter
	 * @param source
	 * @return
	 */
	public abstract boolean createDocumentation(AbstractTextConverter textConverter, PO source);
	
	/**
	 * Create a Documentation
	 * @param textConverter
	 * @return
	 */
	public abstract boolean createDocumentation(AbstractTextConverter textConverter);
	
	/**
	 * Create index
	 * @param textConverter
	 * @param source
	 * @return
	 */
	public boolean addIndex(AbstractTextConverter textConverter, PO source) {
		return false;
	}
	
	/**
	 * Get Folder Name for entity
	 * @return
	 */
	public abstract String getFolderName();
	
	/**
	 * Get Document Name
	 * @return
	 */
	public abstract String getDocumentName();
	
	/**
	 * Add feature
	 * @param columnName
	 */
	public String getFeature(String columnName) {
		M_Element element = M_Element.get(Env.getCtx(), columnName);
		if(element != null
				&& !Util.isEmpty(element.getHelp())) {
			return element.getHelp().trim();
		}
		//	default
		return null;
	}
	
	/**
	 * Get Valid Name
	 */
	protected String getValidValue(String value) {
		String fileName = value;
		fileName = fileName.replaceAll("[+^:&áàäéèëíìïóòöúùñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ$,;*/()]", "");
		fileName = fileName.replace(" ", "-").trim();
		return fileName;
	}
}
