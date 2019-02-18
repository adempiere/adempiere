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
import java.util.ArrayList;
import java.util.List;

import org.adempiere.model.MBrowse;
import org.compiere.model.MForm;
import org.compiere.model.MMenu;
import org.compiere.model.MProcess;
import org.compiere.model.MRefList;
import org.compiere.model.MTree;
import org.compiere.model.MWindow;
import org.compiere.model.PO;
import org.compiere.util.DB;
import org.compiere.util.Util;

/**
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * Documentation generator for Menu entity
 * @see: https://github.com/adempiere/adempiere/issues/1934
 * For formst reference use: http://www.sphinx-doc.org/en/master/usage/restructuredtext/basics.html
 */
public class FunctionalDocsForMenu extends AbstractDocumentationSource {

	public FunctionalDocsForMenu() {
		//	 Constructor
	}

	/**	Document	*/
	private MMenu menu;
	/**	Sub-Folder Name	*/
	public static final String SUB_FOLDER_NAME = "menu";
	/**	Folder Name	*/
	public static final String FOLDER_NAME = "functional-guide" + File.separator + SUB_FOLDER_NAME;
	
	@Override
	public boolean createDocumentation(AbstractTextConverter textConverter, PO source) {
		menu = (MMenu) source;
		//	Add link from internal reference
		textConverter.addHeaderIndexName((getFolderName() + "/" + getDocumentName()).toLowerCase());
		//	Add Name
		textConverter.addSection(menu.getName());
		textConverter.newLine();
		//	Description
		if(!Util.isEmpty(menu.getDescription())) {
			textConverter.addText(menu.getDescription());
			textConverter.newLine();
		}
		List<MMenu> menuList = new ArrayList<MMenu>();
		int currentMenuId = menu.getAD_Menu_ID();
		int parentId = 0;
		int treeId = MTree.getDefaultTreeIdFromTableName(menu.getAD_Client_ID(), "AD_Menu_ID");
		//	Add current
		menuList.add(menu);
		do {
			parentId = DB.getSQLValue(menu.get_TrxName(), "SELECT Parent_ID "
					+ "FROM AD_TreeNodeMM "
					+ "WHERE AD_Tree_ID = ? AND Node_ID = ?", treeId, currentMenuId);
			if(parentId > 0) {
				currentMenuId = parentId;
				menuList.add(MMenu.getFromId(menu.getCtx(), currentMenuId));
			}
		} while(parentId > 0);
		//	Write Path
		textConverter.addSubSection("Menu Path");
		for(int index = menuList.size() -1, level = 0; index >= 0; index--, level++) {
			MMenu menuItem = menuList.get(index);
			textConverter.addQuote(menuItem.getName(), level);
		}
		//	
		textConverter.newLine();
		//	Window Type
		textConverter.addSubSubSection("Menu Type");
		textConverter.addBold(MRefList.getListName(source.getCtx(), MMenu.ACTION_AD_Reference_ID, menu.getAction()));
		textConverter.newLine();
		//	Sales Transaction
		if(menu.isSOTrx()) {
			textConverter.addNote(getFeature(MWindow.COLUMNNAME_IsSOTrx));
		}
		textConverter.newLine();
		String internalReference = null;
		String validName = null;
		String showName = null;
		if(menu.getAction().equals(MMenu.ACTION_Process)) {
			MProcess process = MProcess.get(menu.getCtx(), menu.getAD_Process_ID());
			validName = getValidValue(process.getValue());
			showName = process.getName();
			internalReference = ".." + File.separator + ".." + File.separator + FunctionalDocsForProcess.FOLDER_NAME + File.separator + FunctionalDocsForProcess.SUB_FOLDER_NAME + "-" + validName;
		} else if(menu.getAction().equals(MMenu.ACTION_Window)) {
			MWindow window = (MWindow) menu.getAD_Window();
			validName = getValidValue(window.getName());
			showName = window.getName();
			internalReference = ".." + File.separator + ".." + File.separator + FunctionalDocsForWindow.FOLDER_NAME + File.separator + FunctionalDocsForWindow.SUB_FOLDER_NAME + "-" + validName;
		} else if(menu.getAction().equals(MMenu.ACTION_SmartBrowse)) {
			MBrowse browse = (MBrowse) menu.getAD_Browse();
			validName = getValidValue(browse.getName());
			showName = browse.getName();
			internalReference = ".." + File.separator + ".." + File.separator + FunctionalDocsForSmartBrowse.FOLDER_NAME + File.separator + FunctionalDocsForSmartBrowse.SUB_FOLDER_NAME + "-" + validName;
		} else if(menu.getAction().equals(MMenu.ACTION_Form)) {
			MForm form = (MForm) menu.getAD_Form();
			validName = getValidValue(form.getName());
			showName = form.getName();
			internalReference = ".." + File.separator + ".." + File.separator + FunctionalDocsForForm.FOLDER_NAME + File.separator + FunctionalDocsForForm.SUB_FOLDER_NAME + "-" + validName;
		}
		//	Validate null
		if(!Util.isEmpty(internalReference)) {
			textConverter.addSeeAlso(showName, internalReference.toLowerCase());
		}
		return true;
	}

	@Override
	public boolean createDocumentation(AbstractTextConverter textConverter) {
		return false;
	}
	
	@Override
	public boolean addIndex(AbstractTextConverter indexConverter, PO source) {
		menu = (MMenu) source;
		((IIndex) indexConverter).addIndex(menu.getName(), getDocumentName().toLowerCase(), getFolderName(), 0);
		return true;
	}

	@Override
	public String getFolderName() {
		return FOLDER_NAME;
	}

	@Override
	public String getDocumentName() {
		if(menu != null) {
			return getValidValue(SUB_FOLDER_NAME + "-" + menu.getName());
		}
		//	
		return null;
	}

}
