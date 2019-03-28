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

import org.compiere.model.MField;
import org.compiere.model.MRefList;
import org.compiere.model.MTab;
import org.compiere.model.MWindow;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.compiere.util.Util;

/**
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * Documentation generator for Window entity
 * @see: https://github.com/adempiere/adempiere/issues/1934
 * For formst reference use: http://www.sphinx-doc.org/en/master/usage/restructuredtext/basics.html
 */
public class FunctionalDocsForWindow extends AbstractDocumentationSource {

	public FunctionalDocsForWindow() {
		//	 Constructor
	}

	/**	Document	*/
	private MWindow window;
	/**	Sub-Folder Name	*/
	public static final String SUB_FOLDER_NAME = "window";
	/**	Folder Name	*/
	public static final String FOLDER_NAME = "functional-guide" + File.separator + SUB_FOLDER_NAME;
	
	@Override
	public boolean createDocumentation(AbstractTextConverter textConverter, PO source) {
		window = (MWindow) source;
		//	Add link from internal reference
		textConverter.addHeaderIndexName((getFolderName() + "/" + getDocumentName()).toLowerCase());
		//	Add Name
		textConverter.addSection(window.getName());
		textConverter.newLine();
		//	Description
		if(!Util.isEmpty(window.getDescription())) {
			textConverter.addText(window.getDescription());
			textConverter.newLine();
		}
		//	Help
		if(!Util.isEmpty(window.getHelp())) {
			textConverter.addSubSection("Help");
			textConverter.addText(window.getHelp());
			textConverter.newLine();
		}
		//	Beta Functionality
		if(window.isBetaFunctionality()) {
			textConverter.addNote(getFeature(MWindow.COLUMNNAME_IsBetaFunctionality));
		}
		//	Window Type
		textConverter.addSubSubSection("Window Type");
		textConverter.addBold(MRefList.getListName(source.getCtx(), MWindow.WINDOWTYPE_AD_Reference_ID, window.getWindowType()));
		textConverter.newLine();
		//	Sales Transaction
		if(window.isSOTrx()) {
			textConverter.addNote(getFeature(MWindow.COLUMNNAME_IsSOTrx));
		}
		textConverter.newLine();
		//	
		MTab tabs[] = window.getTabs(false, source.get_TrxName());
		//	Get Parameters
		if(tabs != null
				&& tabs.length > 0) {
			textConverter.addSubSection("Tabs");
			//	
			for(MTab tab : tabs) {
				TableTextConverter table = new TableTextConverter();
				ArrayList<String> row = new ArrayList<String>();
				row.add("Attribute");
				row.add("Value");
				row.add("Description");
				table.addRow(row);
				//	Name
				textConverter.addSubSubSection(tab.getName());
				//	Description
				if(!Util.isEmpty(tab.getDescription())) {
					textConverter.addBold("Description");
					textConverter.newLine();
					textConverter.addText(" ");
					textConverter.addItalic(tab.getDescription());
					textConverter.newLine();
				}
				//	Help
				if(!Util.isEmpty(tab.getHelp())) {
					textConverter.addBold("Help");
					textConverter.newLine();
					textConverter.addText(" ");
					textConverter.addItalic(tab.getHelp());
					textConverter.newLine();
				}
				StringBuffer note = new StringBuffer();
				//	Single Row
				if(tab.isSingleRow()) {
					note.append(getFeature(MTab.COLUMNNAME_IsSingleRow));
				}
				//	Advanced Tab
				if(tab.isAdvancedTab()) {
					if(note.length() > 0) {
						note.append(Env.NL);
					}
					//	
					note.append(getFeature(MTab.COLUMNNAME_IsAdvancedTab));
				}
				//	Tree
				if(tab.isHasTree()) {
					if(note.length() > 0) {
						note.append(Env.NL);
					}
					//	
					note.append(getFeature("IsHasTree"));
				}
				//	Info
				if(tab.isInfoTab()) {
					if(note.length() > 0) {
						note.append(Env.NL);
					}
					//	
					note.append(getFeature(MTab.COLUMNNAME_IsInfoTab));
				}
				//	Sort Tab
				if(tab.isSortTab()) {
					if(note.length() > 0) {
						note.append(Env.NL);
					}
					//	
					note.append(getFeature(MTab.COLUMNNAME_IsSortTab));
				}
				//	Translation Tab
				if(tab.isTranslationTab()) {
					if(note.length() > 0) {
						note.append(Env.NL);
					}
					//	
					note.append(getFeature(MTab.COLUMNNAME_IsTranslationTab));
				}
				//	Read Only Tab
				if(tab.isReadOnly()) {
					if(note.length() > 0) {
						note.append(Env.NL);
					}
					//	
					note.append(getFeature(MTab.COLUMNNAME_IsReadOnly));
				}
				//	Insert Record Tab
				if(tab.isInsertRecord()) {
					if(note.length() > 0) {
						note.append(Env.NL);
					}
					//	
					note.append(getFeature(MTab.COLUMNNAME_IsInsertRecord));
				}
				//	Add as note
				if(note.length() > 0) {
					textConverter.addNote(note.toString());
				}
				//	For Fields
				MField fields[] = tab.getFields(false, source.get_TrxName());
				if(fields != null
						&& fields.length > 0) {
					textConverter.addSubSection("Fields");
					//	
					for(MField field : fields) {
						if(!field.isDisplayed()) {
							continue;
						}
						//	Name
						textConverter.addSubSubSection(field.getName());
						//	Description
						if(!Util.isEmpty(field.getDescription())) {
							textConverter.addBold("Description");
							textConverter.newLine();
							textConverter.addText(" ");
							textConverter.addItalic(field.getDescription());
							textConverter.newLine();
						}
						//	Help
						if(!Util.isEmpty(field.getHelp())) {
							textConverter.addBold("Help");
							textConverter.newLine();
							textConverter.addText(" ");
							textConverter.addItalic(field.getHelp());
							textConverter.newLine();
						}
						note = new StringBuffer();
						//	Read Only
						if(field.isDisplayedGrid()) {
							note.append(getFeature(MField.COLUMNNAME_IsDisplayedGrid));
						}
						//	Read Only
						if(field.isReadOnly()) {
							if(note.length() > 0) {
								note.append(Env.NL);
							}
							//	
							note.append(getFeature(MField.COLUMNNAME_IsReadOnly));
						}
						//	Allows Copy
						if(field.isAllowCopy()) {
							if(note.length() > 0) {
								note.append(Env.NL);
							}
							//	
							note.append(getFeature(MField.COLUMNNAME_IsAllowCopy));
						}
						//	Encrypted
						if(field.isEncrypted()) {
							if(note.length() > 0) {
								note.append(Env.NL);
							}
							//	
							note.append(getFeature(MField.COLUMNNAME_IsEncrypted));
						}
					}
				}
			}
		}
		return true;
	}
	
	@Override
	public boolean addIndex(AbstractTextConverter indexConverter, PO source) {
		window = (MWindow) source;
		((IIndex) indexConverter).addIndex(window.getName(), getDocumentName().toLowerCase(), getFolderName(), 0);
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
		if(window != null) {
			return getValidValue(SUB_FOLDER_NAME + "-" + window.getName());
		}
		//	
		return "";
	}

}
