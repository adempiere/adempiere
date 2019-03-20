/*************************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                              *
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
package org.spin.util.docs;

import java.io.File;

import org.compiere.model.MForm;
import org.compiere.model.MWindow;
import org.compiere.model.PO;
import org.compiere.util.Util;

/**
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * Documentation generator for Form entity
 * @see: https://github.com/adempiere/adempiere/issues/1934
 * For formst reference use: http://www.sphinx-doc.org/en/master/usage/restructuredtext/basics.html
 */
public class FunctionalDocsForForm extends AbstractDocumentationSource {

	public FunctionalDocsForForm() {
		//	 Constructor
	}

	/**	Document	*/
	private MForm form;
	/**	Sub-Folder Name	*/
	public static final String SUB_FOLDER_NAME = "form";
	/**	Folder Name	*/
	public static final String FOLDER_NAME = "functional-guide" + File.separator + SUB_FOLDER_NAME;
	
	@Override
	public boolean createDocumentation(AbstractTextConverter textConverter, PO source) {
		form = (MForm) source;
		//	Add link from internal reference
		textConverter.addHeaderIndexName((getFolderName() + "/" + getDocumentName()).toLowerCase());
		//	Add Name
		textConverter.addSection(form.getName());
		textConverter.newLine();
		//	Description
		if(!Util.isEmpty(form.getDescription())) {
			textConverter.addText(form.getDescription());
			textConverter.newLine();
		}
		//	Help
		if(!Util.isEmpty(form.getHelp())) {
			textConverter.addSubSection("Help");
			textConverter.addText(form.getHelp());
			textConverter.newLine();
		}
		//	Beta Functionality
		if(form.isBetaFunctionality()) {
			textConverter.addNote(getFeature(MWindow.COLUMNNAME_IsBetaFunctionality));
		}
		return true;
	}
	
	@Override
	public boolean addIndex(AbstractTextConverter indexConverter, PO source) {
		form = (MForm) source;
		((IIndex) indexConverter).addIndex(form.getName(), getDocumentName().toLowerCase(), getFolderName(), 0);
		return true;
	}

	@Override
	public boolean createDocumentation(AbstractTextConverter textConverter) {
		return false;
	}

	@Override
	public String getFolderName() {
		return FOLDER_NAME;
	}

	@Override
	public String getDocumentName() {
		if(form != null) {
			return getValidValue(SUB_FOLDER_NAME + "-" + form.getName());
		}
		//	
		return "";
	}

}
