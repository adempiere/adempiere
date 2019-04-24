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

import org.adempiere.model.MBrowse;
import org.compiere.model.MForm;
import org.compiere.model.MProcess;
import org.compiere.model.MProcessPara;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.compiere.wf.MWorkflow;

/**
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * Documentation generator for Process entity
 * @see: https://github.com/adempiere/adempiere/issues/1934
 * For formst reference use: http://www.sphinx-doc.org/en/master/usage/restructuredtext/basics.html
 */
public class FunctionalDocsForProcess extends AbstractDocumentationSource {

	public FunctionalDocsForProcess() {
		//	 Constructor
	}

	/**	Document	*/
	private MProcess process;
	/**	Sub-Folder Name	*/
	public static final String SUB_FOLDER_NAME = "process";
	/**	Folder Name	*/
	public static final String FOLDER_NAME = "functional-guide" + File.separator + SUB_FOLDER_NAME;
	
	@Override
	public boolean createDocumentation(AbstractTextConverter textConverter, PO source) {
		process = (MProcess) source;
		//	Add link from internal reference
		textConverter.addHeaderIndexName((getFolderName() + "/" + getDocumentName()).toLowerCase());
		//	Add Name
		textConverter.addSection(process.getName());
		textConverter.newLine();
		//	Description
		if(!Util.isEmpty(process.getDescription())) {
			textConverter.addText(process.getDescription());
			textConverter.newLine();
		}
		//	Help
		if(!Util.isEmpty(process.getHelp())) {
			textConverter.addSubSection("Help");
			textConverter.addText(process.getHelp());
			textConverter.newLine();
		}
		//	Beta Functionality
		if(process.isBetaFunctionality()) {
			textConverter.addNote(getFeature(MProcess.COLUMNNAME_IsBetaFunctionality));
		}
		//	Workflow
		if(process.getAD_Workflow_ID() != 0) {
			MWorkflow workflow = MWorkflow.get(source.getCtx(), process.getAD_Workflow_ID());
			textConverter.addSubSubSection("Workflow");
			textConverter.addBold(workflow.getName());
			textConverter.newLine();
			textConverter.addText(" ");
			textConverter.addItalic(workflow.getDescription());
			textConverter.newLine();
		}
		//	Special Form
		if(process.getAD_Form_ID() != 0) {
			MForm form = (MForm) process.getAD_Form();
			textConverter.addSubSubSection("Special Form");
			textConverter.addBold(form.getName());
			textConverter.newLine();
			textConverter.addText(" ");
			textConverter.addItalic(form.getDescription());
			textConverter.newLine();
		}
		//	Smart Browse
		if(process.getAD_Browse_ID() != 0) {
			MBrowse smartBrowse = MBrowse.get(source.getCtx(), process.getAD_Browse_ID());
			textConverter.addSubSubSection("Smart Browse");
			textConverter.addBold(smartBrowse.getName());
			textConverter.newLine();
			textConverter.addText(" ");
			textConverter.addItalic(smartBrowse.getDescription());
			textConverter.newLine();
		}
		//	Get Parameters
		MProcessPara parameters[] = process.getParameters();
		if(parameters != null
				&& parameters.length > 0) {
			textConverter.addSubSection("Parameters");
			//	
			for(MProcessPara parameter : parameters) {
				//	Name
				textConverter.addSubSubSection(parameter.getName());
				//	Description
				if(!Util.isEmpty(parameter.getDescription())) {
					textConverter.addBold("Description");
					textConverter.newLine();
					textConverter.addText(" ");
					textConverter.addItalic(parameter.getDescription());
					textConverter.newLine();
				}
				//	Help
				if(!Util.isEmpty(parameter.getHelp())) {
					textConverter.addBold("Help");
					textConverter.newLine();
					textConverter.addText(" ");
					textConverter.addItalic(parameter.getHelp());
					textConverter.newLine();
				}
				StringBuffer note = new StringBuffer();
				//	Mandatory
				if(parameter.isMandatory()) {
					note.append(getFeature(MProcessPara.COLUMNNAME_IsMandatory));
				}
				//	Range
				if(parameter.isRange()) {
					if(note.length() > 0) {
						note.append(Env.NL);
					}
					//	
					note.append(getFeature(MProcessPara.COLUMNNAME_IsRange));
				}
				//	Is Info Only
				if(parameter.isInfoOnly()) {
					if(note.length() > 0) {
						note.append(Env.NL);
					}
					//	
					note.append(getFeature(MProcessPara.COLUMNNAME_IsInfoOnly));
				}
				//	Add as note
				if(note.length() > 0) {
					textConverter.addNote(note.toString());
				}
			}
		}
		return true;
	}

	@Override
	public boolean addIndex(AbstractTextConverter indexConverter, PO source) {
		process = (MProcess) source;
		((IIndex) indexConverter).addIndex(process.getName(), getDocumentName().toLowerCase(), getFolderName(), 0);
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
		if(process != null) {
			return getValidValue(SUB_FOLDER_NAME + "-" + process.getValue());
		}
		//	
		return null;
	}

}
