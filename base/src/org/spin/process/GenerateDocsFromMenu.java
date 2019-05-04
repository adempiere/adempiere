/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.									  *
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
package org.spin.process;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.I_AD_Browse;
import org.adempiere.model.MBrowse;
import org.compiere.model.I_AD_Form;
import org.compiere.model.I_AD_Process;
import org.compiere.model.I_AD_Window;
import org.compiere.model.MForm;
import org.compiere.model.MMenu;
import org.compiere.model.MProcess;
import org.compiere.model.MWindow;
import org.compiere.model.Query;
import org.compiere.util.Util;
import org.spin.util.docs.AbstractDocumentationSource;
import org.spin.util.docs.AbstractTextConverter;
import org.spin.util.docs.FunctionalDocsForForm;
import org.spin.util.docs.FunctionalDocsForMenu;
import org.spin.util.docs.FunctionalDocsForProcess;
import org.spin.util.docs.FunctionalDocsForSmartBrowse;
import org.spin.util.docs.FunctionalDocsForWindow;
import org.spin.util.docs.IIndex;

/**
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * reStructuredText converter, it can be used for export a simple String to reStructuredText format
 * @see: https://github.com/adempiere/adempiere/issues/1934
 * For formst reference use: http://www.sphinx-doc.org/en/master/usage/restructuredtext/basics.html
 */
public class GenerateDocsFromMenu extends GenerateDocsFromMenuAbstract {
	@Override
	protected void prepare() {
		super.prepare();
	}
	
	/**	Converter	*/
	private AbstractTextConverter textConverter = null;
	/**	Index Converter	*/
	private AbstractTextConverter indexConverter = null;
	/**	Created	*/
	private int created = 0;
	/**	Folder Name	*/
	public static final String FOLDER_NAME = "functional-guide";

	@Override
	protected String doIt() throws Exception {
		Class<?> clazz = Class.forName(getDocsExportFormat());
		if (!AbstractTextConverter.class.isAssignableFrom(clazz)) {
			throw new AdempiereException("@DocsExportFormat@ @NotMatched@");
		}
		//	Instance
		textConverter = (AbstractTextConverter) clazz.newInstance();
		indexConverter = (AbstractTextConverter) clazz.newInstance();
		((IIndex) indexConverter).addGroup("Functional Guide", FOLDER_NAME, 1);
		loadMenu();
		loadProcess();
		loadWindow();
		loadForm();
		loadSmartBrowse();
		//	Save Main index
		if(indexConverter.getMainIndex() != null
				&& !Util.isEmpty(indexConverter.getMainIndexFileName())) {
			String folderName = getDirectory();
			String fileName = FOLDER_NAME + File.separator + textConverter.getMainIndexFileName();
			if(!Util.isEmpty(fileName)) {
				writeFile(folderName, fileName, indexConverter.getMainIndex().toString());
			}
		}
		return "@Created@ " + created;
	}
	
	/**
	 * Load Menu for documents
	 * @throws IOException 
	 */
	private void loadMenu() throws IOException {
		StringBuffer whereClause = new StringBuffer("AD_Client_ID = " + getAD_Client_ID() + " AND IsSummary = 'N'");
		if(getRecord_ID() > 0) {
			whereClause.append(" AND ").append("AD_Menu_ID = ").append(getRecord_ID());
		}
		//	Get Result
		MMenu menuList[] = MMenu.get(getCtx(), whereClause.toString(), get_TrxName());
		if(menuList == null
				|| menuList.length == 0) {
			return;
		}
		String folderName = getDirectory() + File.separator + FunctionalDocsForMenu.FOLDER_NAME;
		String fileName = textConverter.getIndexFileName();
		if(!Util.isEmpty(fileName)) {
			indexConverter.addHeaderIndexName(FunctionalDocsForMenu.FOLDER_NAME);
			indexConverter.newLine();
			indexConverter.addSection("Menu");
			indexConverter.newLine();
			((IIndex) indexConverter).addTreeDefinition(1, true);
			((IIndex) indexConverter).addGroup("Menu", FunctionalDocsForMenu.FOLDER_NAME, 2);
		}
		//	
		for(MMenu menu : menuList) {
			//	For Menu
			documentForMenu(menu, folderName);
			textConverter.clear();
		}
		if(!Util.isEmpty(fileName)) {
			writeFile(folderName, fileName, indexConverter.toString());
		}
		//	Clear
		indexConverter.clear();
	}
	
	/**
	 * Load Process for documents
	 * @throws IOException 
	 */
	private void loadProcess() throws IOException {
		List<MProcess> processList = new Query(getCtx(), I_AD_Process.Table_Name, null, get_TrxName())
				.setOnlyActiveRecords(true)
				.setClient_ID()
				.list();
		//	Get Result
		if(processList == null
				|| processList.size() == 0) {
			return;
		}
		String folderName = getDirectory() + File.separator + FunctionalDocsForProcess.FOLDER_NAME;
		String fileName = textConverter.getIndexFileName();
		if(!Util.isEmpty(fileName)) {
			indexConverter.addHeaderIndexName(FunctionalDocsForProcess.FOLDER_NAME);
			indexConverter.newLine();
			indexConverter.addSection("Process");
			indexConverter.newLine();
			((IIndex) indexConverter).addTreeDefinition(1, true);
			((IIndex) indexConverter).addGroup("Process", FunctionalDocsForProcess.FOLDER_NAME, 2);
		}
		//	
		for(MProcess process : processList) {
			//	For Process
			documentForProcess(process, folderName);
			textConverter.clear();
		}
		//	
		if(!Util.isEmpty(fileName)) {
			writeFile(folderName, fileName, indexConverter.toString());
		}
		//	Clear
		indexConverter.clear();
	}
	
	/**
	 * Load window for documents
	 * @throws IOException 
	 */
	private void loadWindow() throws IOException {
		List<MWindow> windowList = new Query(getCtx(), I_AD_Window.Table_Name, null, get_TrxName())
				.setOnlyActiveRecords(true)
				.setClient_ID()
				.list();
		//	Get Result
		if(windowList == null
				|| windowList.size() == 0) {
			return;
		}
		String folderName = getDirectory() + File.separator + FunctionalDocsForWindow.FOLDER_NAME;
		String fileName = textConverter.getIndexFileName();
		if(!Util.isEmpty(fileName)) {
			indexConverter.addHeaderIndexName(FunctionalDocsForWindow.FOLDER_NAME);
			indexConverter.newLine();
			indexConverter.addSection("Windows");
			indexConverter.newLine();
			((IIndex) indexConverter).addTreeDefinition(1, true);
			((IIndex) indexConverter).addGroup("Window", FunctionalDocsForProcess.FOLDER_NAME, 2);
		}
		//	
		for(MWindow window : windowList) {
			//	For Window
			documentForWindow(window, folderName);
			textConverter.clear();
		}
		if(!Util.isEmpty(fileName)) {
			writeFile(folderName, fileName, indexConverter.toString());
		}
		//	Clear
		indexConverter.clear();
	}
	
	/**
	 * Load Form for documents
	 * @throws IOException 
	 */
	private void loadForm() throws IOException {
		List<MForm> formList = new Query(getCtx(), I_AD_Form.Table_Name, null, get_TrxName())
				.setOnlyActiveRecords(true)
				.setClient_ID()
				.list();
		//	Get Result
		if(formList == null
				|| formList.size() == 0) {
			return;
		}
		String folderName = getDirectory() + File.separator + FunctionalDocsForForm.FOLDER_NAME;
		String fileName = textConverter.getIndexFileName();
		if(!Util.isEmpty(fileName)) {
			indexConverter.addHeaderIndexName(FunctionalDocsForForm.FOLDER_NAME);
			indexConverter.newLine();
			indexConverter.addSection("Forms");
			indexConverter.newLine();
			((IIndex) indexConverter).addTreeDefinition(1, true);
			((IIndex) indexConverter).addGroup("Form", FunctionalDocsForProcess.FOLDER_NAME, 2);
		}
		//	
		for(MForm form : formList) {
			//	For Window
			documentForForm(form, folderName);
			textConverter.clear();
		}
		if(!Util.isEmpty(fileName)) {
			writeFile(folderName, fileName, indexConverter.toString());
		}
		//	Clear
		indexConverter.clear();
	}
	
	/**
	 * Load Smart Browse for documents
	 * @throws IOException 
	 */
	private void loadSmartBrowse() throws IOException {
		List<MBrowse> smartBrowseList = new Query(getCtx(), I_AD_Browse.Table_Name, null, get_TrxName())
				.setOnlyActiveRecords(true)
				.setClient_ID()
				.list();
		//	Get Result
		if(smartBrowseList == null
				|| smartBrowseList.size() == 0) {
			return;
		}
		String folderName = getDirectory() + File.separator + FunctionalDocsForSmartBrowse.FOLDER_NAME;
		String fileName = textConverter.getIndexFileName();
		if(!Util.isEmpty(fileName)) {
			indexConverter.addHeaderIndexName(FunctionalDocsForSmartBrowse.FOLDER_NAME);
			indexConverter.newLine();
			indexConverter.addSection("Smart Browsers");
			indexConverter.newLine();
			((IIndex) indexConverter).addTreeDefinition(1, true);
			((IIndex) indexConverter).addGroup("Smart Browsers", FunctionalDocsForProcess.FOLDER_NAME, 2);
		}
		//	
		for(MBrowse smartBrowse : smartBrowseList) {
			//	For Window
			documentForSmartBrowse(smartBrowse, folderName);
			textConverter.clear();
		}
		if(!Util.isEmpty(fileName)) {
			writeFile(folderName, fileName, indexConverter.toString());
		}
		//	Clear
		indexConverter.clear();
	}
	
	/**
	 * Create Document for menu
	 * @param process
	 * @throws IOException 
	 */
	private void documentForMenu(MMenu menu, String folderName) throws IOException {
		AbstractDocumentationSource documentGenerator = new FunctionalDocsForMenu();
		boolean isOk = documentGenerator.createDocumentation(textConverter, menu);
		if(!isOk) { 
			return;
		}
		//	Get
		if(!Util.isEmpty(textConverter.getIndexFileName())) {
			documentGenerator.addIndex(indexConverter, menu);
		}
		//	
		String fileName = getValidName(documentGenerator.getDocumentName());
		//	Write
		writeFile(folderName, fileName, textConverter.toString());
		//	Add to list
		addLog("@AD_Menu_ID@ " + menu.getName());
		created++;
	}
	
	/**
	 * Create Document for process
	 * @param process
	 * @param folderName
	 * @throws IOException 
	 */
	private void documentForProcess(MProcess process, String folderName) throws IOException {
		AbstractDocumentationSource documentGenerator = new FunctionalDocsForProcess();
		boolean isOk = documentGenerator.createDocumentation(textConverter, process);
		if(!isOk) { 
			return;
		}
		//	Get
		if(!Util.isEmpty(textConverter.getIndexFileName())) {
			documentGenerator.addIndex(indexConverter, process);
		}
		String fileName = getValidName(documentGenerator.getDocumentName());
		//	Write
		writeFile(folderName, fileName, textConverter.toString());
		//	Add to list
		addLog("@AD_Process_ID@ " + process.getName());
		created++;
	}
	
	/**
	 * Create Document for Window
	 * @param window
	 * @param folderName
	 * @throws IOException 
	 */
	private void documentForWindow(MWindow window, String folderName) throws IOException {
		AbstractDocumentationSource documentGenerator = new FunctionalDocsForWindow();
		boolean isOk = documentGenerator.createDocumentation(textConverter, window);
		if(!isOk) { 
			return;
		}
		//	Get
		if(!Util.isEmpty(textConverter.getIndexFileName())) {
			documentGenerator.addIndex(indexConverter, window);
		}
		String fileName = getValidName(documentGenerator.getDocumentName());
		//	Write
		writeFile(folderName, fileName, textConverter.toString());
		//	Add to list
		addLog("@AD_Window_ID@ " + window.getName());
		created++;
	}
	
	/**
	 * Create Document for Form
	 * @param form
	 * @param folderName
	 * @throws IOException 
	 */
	private void documentForForm(MForm form, String folderName) throws IOException {
		AbstractDocumentationSource documentGenerator = new FunctionalDocsForForm();
		boolean isOk = documentGenerator.createDocumentation(textConverter, form);
		if(!isOk) { 
			return;
		}
		//	Get
		if(!Util.isEmpty(textConverter.getIndexFileName())) {
			documentGenerator.addIndex(indexConverter, form);
		}
		String fileName = getValidName(documentGenerator.getDocumentName());
		//	Write
		writeFile(folderName, fileName, textConverter.toString());
		//	Add to list
		addLog("@AD_Form_ID@ " + form.getName());
		created++;
	}
	
	/**
	 * Create Document for Smart Browse
	 * @param smartBrowse
	 * @param folderName
	 * @throws IOException 
	 */
	private void documentForSmartBrowse(MBrowse smartBrowse, String folderName) throws IOException {
		AbstractDocumentationSource documentGenerator = new FunctionalDocsForSmartBrowse();
		boolean isOk = documentGenerator.createDocumentation(textConverter, smartBrowse);
		if(!isOk) { 
			return;
		}
		//	Get
		if(!Util.isEmpty(textConverter.getIndexFileName())) {
			documentGenerator.addIndex(indexConverter, smartBrowse);
		}
		String fileName = getValidName(documentGenerator.getDocumentName());
		//	Write
		writeFile(folderName, fileName, textConverter.toString());
		//	Add to list
		addLog("@AD_Browse_ID@ " + smartBrowse.getName());
		created++;
	}
	
	/**
	 * Write file
	 * @param folderName
	 * @param fileName
	 * @param value
	 * @throws IOException
	 */
	private void writeFile(String folderName, String fileName, String value) throws IOException {
		File exportDir = new File(folderName);
		exportDir.mkdirs();
		//	Create File
		File exportFile = new File(folderName + File.separator + fileName);
		FileWriter writer = new FileWriter(exportFile);
		writer.write(value);
		writer.flush();
		writer.close();
	}
	
	/**
	 * 
	 * @param fileName
	 * @return
	 */
	private String getValidName(String fileName) {
		//	
		return (fileName + "." + textConverter.getExtension()).toLowerCase();
	}
}