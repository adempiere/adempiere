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
import org.adempiere.model.MBrowseField;
import org.compiere.model.MProcess;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.compiere.util.Util;

/**
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * Documentation generator for Smart Browse entity
 * @see: https://github.com/adempiere/adempiere/issues/1934
 * For formst reference use: http://www.sphinx-doc.org/en/master/usage/restructuredtext/basics.html
 */
public class FunctionalDocsForSmartBrowse extends AbstractDocumentationSource {

	public FunctionalDocsForSmartBrowse() {
		//	 Constructor
	}

	/**	Document	*/
	private MBrowse smartBrowse;
	/**	Sub-Folder Name	*/
	public static final String SUB_FOLDER_NAME = "smart-browse";
	/**	Folder Name	*/
	public static final String FOLDER_NAME = "functional-guide" + File.separator + SUB_FOLDER_NAME;
	
	@Override
	public boolean createDocumentation(AbstractTextConverter textConverter, PO source) {
		smartBrowse = (MBrowse) source;
		//	Add link from internal reference
		textConverter.addHeaderIndexName((getFolderName() + "/" + getDocumentName()).toLowerCase());
		//	Add Name
		textConverter.addSection(smartBrowse.getName());
		textConverter.newLine();
		//	Description
		if(!Util.isEmpty(smartBrowse.getDescription())) {
			textConverter.addText(smartBrowse.getDescription());
			textConverter.newLine();
		}
		//	Help
		if(!Util.isEmpty(smartBrowse.getHelp())) {
			textConverter.addSubSection("Help");
			textConverter.addText(smartBrowse.getHelp());
			textConverter.newLine();
		}
		//	Beta Functionality
		if(smartBrowse.isBetaFunctionality()) {
			textConverter.addNote(getFeature(MProcess.COLUMNNAME_IsBetaFunctionality));
		}
		//	window
		if(smartBrowse.getAD_Window_ID() != 0) {
			String name = smartBrowse.getAD_Window().getName();
			String internalReference = ".." + File.separator + ".." + File.separator + FunctionalDocsForWindow.FOLDER_NAME + File.separator + FunctionalDocsForWindow.SUB_FOLDER_NAME + "-" + getValidValue(name);
			textConverter.addSeeAlso(name, internalReference.toLowerCase());
			textConverter.newLine();
		}
		//	Process
		if(smartBrowse.getAD_Process_ID() != 0) {
			MProcess process = (MProcess) smartBrowse.getAD_Process();
			String internalReference = ".." + File.separator + ".." + File.separator + FunctionalDocsForProcess.FOLDER_NAME + File.separator + FunctionalDocsForProcess.SUB_FOLDER_NAME + "-" + getValidValue(process.getValue());
			textConverter.addSeeAlso(process.getName(), internalReference.toLowerCase());
			textConverter.newLine();
		}
		StringBuffer note = new StringBuffer();
		//	Updateable
		if(smartBrowse.isUpdateable()) {
			note.append(getFeature(MBrowse.COLUMNNAME_IsUpdateable));
		}
		//	Deleteable
		if(smartBrowse.isDeleteable()) {
			if(note.length() > 0) {
				note.append(Env.NL);
			}
			//	
			note.append(getFeature(MBrowse.COLUMNNAME_IsDeleteable));
		}
		//	Is Selected by default
		if(smartBrowse.isSelectedByDefault()) {
			if(note.length() > 0) {
				note.append(Env.NL);
			}
			//	
			note.append(getFeature(MBrowse.COLUMNNAME_IsSelectedByDefault));
		}
		//	Collapsible
		if(smartBrowse.isCollapsibleByDefault()) {
			if(note.length() > 0) {
				note.append(Env.NL);
			}
			//	
			note.append(getFeature(MBrowse.COLUMNNAME_IsCollapsibleByDefault));
		}
		//	Execute Query by default
		if(smartBrowse.isExecutedQueryByDefault()) {
			if(note.length() > 0) {
				note.append(Env.NL);
			}
			//	
			note.append(getFeature(MBrowse.COLUMNNAME_IsExecutedQueryByDefault));
		}
		//	Execute Query by default
		if(smartBrowse.isShowTotal()) {
			if(note.length() > 0) {
				note.append(Env.NL);
			}
			//	
			note.append(getFeature(MBrowse.COLUMNNAME_IsShowTotal));
		}
		//	Add as note
		if(note.length() > 0) {
			textConverter.addNote(note.toString());
		}
		
		//	Get Parameters
		List<MBrowseField> browseField = smartBrowse.getFields();
		if(browseField != null
				&& browseField.size() > 0) {
			textConverter.addSubSection("Fields");
			//	
			TableTextConverter fieldTable = new TableTextConverter();
			ArrayList<String> row = new ArrayList<>();
			row.add("Name");
			row.add("Description");
			row.add("Displayed");
			row.add("Query Criteria");
			row.add("Order By");
			row.add("Read Only");
			row.add("Mandatory");
			fieldTable.addRow(row);
			for(MBrowseField field : browseField) {
				if(!field.isDisplayed()
						&& !field.isQueryCriteria()) {
					continue;
				}
				row = new ArrayList<>();
				row.add(field.getName());
				row.add(field.getDescription());
				row.add(field.isDisplayed()? "Yes": "No");
				row.add(field.isQueryCriteria()? "Yes": "No");
				row.add(field.isOrderBy()? "Yes": "No");
				row.add(field.isReadOnly()? "Yes": "No");
				row.add(field.isMandatory()? "Yes": "No");
				fieldTable.addRow(row);
			}
			//	Add table
			textConverter.addTable(fieldTable);
		}
		return true;
	}
	
	@Override
	public boolean addIndex(AbstractTextConverter indexConverter, PO source) {
		smartBrowse = (MBrowse) source;
		((IIndex) indexConverter).addIndex(smartBrowse.getName(), getDocumentName().toLowerCase(), getFolderName(), 0);
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
		if(smartBrowse != null) {
			return getValidValue(SUB_FOLDER_NAME + "-" + smartBrowse.getValue());
		}
		//	
		return null;
	}

}
