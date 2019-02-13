/*******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution * Copyright (C)
 * 1999-2009 Adempiere, Inc. All Rights Reserved. * This program is free
 * software; you can redistribute it and/or modify it * under the terms version
 * 2 of the GNU General Public License as published * by the Free Software
 * Foundation. This program is distributed in the hope * that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied * warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. * See the GNU General
 * Public License for more details. * You should have received a copy of the GNU
 * General Public License along * with this program; if not, write to the Free
 * Software Foundation, Inc., * 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307 USA. *
 * 
 ******************************************************************************/

package org.compiere.process;

import java.awt.HeadlessException;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.ProcessUtil;
import org.compiere.cm.StringUtil;
import org.compiere.model.MMigration;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 
 * Process to export an AD migration script as xml
 * 
 * @author Paul Bowden, Adaxa Pty Ltd
 *
 * @author Michael McKay, michael.mckay@mckayerp.com, 
 *	 <li>Bug [ <a href="https://github.com/adempiere/adempiere/issues/1926">#1926</a> ] ZK Exports migration XML files to 
 *       different location than what is selected in the dialogs.
 */
public class MigrationToXML extends SvrProcess {

	private int migrationId = 0;
	private String fileName;
	private File tempFile;

	@Override
	protected String doIt() throws Exception {
		MMigration migration = new MMigration(getCtx(), migrationId, get_TrxName());
		if ( migration == null || migration.is_new() )
			return "@NoMigrationFound@";  // No migration found
		
		log.log(Level.FINE, "Creating xml document for migration: " + migration);
		Document document = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		document = builder.newDocument();
		Element root = document.createElement("Migrations");
		document.appendChild(root);
		root.appendChild(migration.toXmlNode(document));
		
		  //set up a transformer
        TransformerFactory transfac = TransformerFactory.newInstance();
        transfac.setAttribute("indent-number", 2);
        Transformer trans;
        
		trans = transfac.newTransformer();
		
        trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        trans.setOutputProperty(OutputKeys.INDENT, "yes");
        trans.setOutputProperty(OutputKeys.STANDALONE, "yes");

       
		 //  Come up with a temporary filename.
		fileName = migration.getReleaseNo() + "_" + migration.getSeqNo() +
				"_" + StringUtil.replace(migration.getName().trim()," ","")
				+ ".xml";

		tempFile = null;
		
		//  #1926 - fix how the export process works.  The process needs to 
		//  ask the user for the target file name in SWING and ZK.  Programmatic 
		//  invocation of the process will default to a temporary file name 
		//  saved in the user's default directory.
		//  Interface type is set by the UI dialogs.
		
		// If this is the SWING interface, ask the user to select the file now
		if (ProcessInfo.INTERFACE_TYPE_SWING.equals(this.getProcessInfo().getInterfaceType()))
		{
			// Try to recover the last used path from the context.
			String path = Env.getContext(getCtx(), "Last Used Path");
			
			try 
			{
				FileFilter filter = new FileNameExtensionFilter(Msg.getMsg(getCtx(), "FileXML"), "xml");
				JFileChooser chooser = new JFileChooser(path);  // User's default if null or empty
				chooser.setMultiSelectionEnabled(false);
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooser.setDialogTitle(getProcessInfo().getTitle());
				chooser.setDialogType(JFileChooser.SAVE_DIALOG);
				chooser.setFileFilter(filter); // XML only
				chooser.setSelectedFile(tempFile);  // Doesn't seem to work - the selected file is blank
				int result = chooser.showSaveDialog(null);
			
				if (result == JFileChooser.APPROVE_OPTION)
				{
					tempFile = chooser.getSelectedFile();
					
		            // Need to create the file, even if empty.  This won't overwrite 
					// an existing file, but will make a new one if the file doesn't exist.
		            // Without this, tempFile.isFile() below will return false if no
		            // file exists when we need it to return true.
		            tempFile.createNewFile();
		            
		            // Save the path used to the context
					Env.setContext(getCtx(), "Last Used Path", tempFile.getAbsolutePath());
				}
				else
				{
					// Operation cancelled.
					return "@OperationCancelled@";
				}
				
			} catch (HeadlessException e) {
				e.printStackTrace();
				throw new AdempiereException("Can't create file for XML export. " + e.getMessage());
			}
		}
		
		// If tempFile has not been initialized or is not a file
		// then create a temporary file - this will happen if the
		// interface type is not Swing, in which case a temporary 
		// file is needed, or if something went wrong in the file 
		// chooser above. The later shouldn't happen but "Au cas où..."
		if (tempFile == null || !tempFile.isFile())
		{
			// Create the temporary file
			tempFile = File.createTempFile("adempiere_", "_"+ fileName);
		}		

		// The tempFile should now exist somewhere
		fileName = tempFile.getAbsolutePath();
		
        log.log(Level.FINE, "Writing xml to file " + fileName);
        //create string from xml tree
        FileWriter fw = new FileWriter(fileName);
        StreamResult result = new StreamResult(fw);
        DOMSource source = new DOMSource(document);
        trans.transform(source, result);
        fw.close();
        
        // For zk, send the temporary file for download after it has been created
		if (ProcessInfo.INTERFACE_TYPE_ZK.equals(this.getProcessInfo().getInterfaceType()))
		{
			// Force download of the file
			String className = "org.zkoss.zul.Filedownload";
			//Get Class
			Class<?> Filedownload = null;
			//use context classloader if available
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			if (classLoader == null)
				classLoader = ProcessUtil.class.getClassLoader();
			try
			{
				Filedownload = classLoader.loadClass(className);
			}
			catch (ClassNotFoundException ex)
			{
				log.log(Level.WARNING, className, ex);
				throw new AdempiereException("Can't load the necessary class '" + className + "' to download the file. " + ex.getMessage());
			}

			Method save = null;
			Object FileDownloader = Filedownload.newInstance();
			File file = new File(fileName);			
			try 
			{
				// Invoke the ZK Filedownload.save(file, contentType)
				save = Filedownload.getMethod("save", java.io.File.class, java.lang.String.class);
				save.invoke(FileDownloader, file, (String) null);
			} 
			catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) 
			{
				
				log.log(Level.WARNING, "Can't download the file.", e);
				throw new AdempiereException("Can't download the file. " + e.getMessage());
				
			}
			
			// Don't delete the temporary file right away!! 
			// The download thread needs to access it.
			tempFile.deleteOnExit();
			
			// "Migration exported to XML successfully. The download should start shortly."
			return "@XMLDownloadSuccessMessage@";
		}

		// "Exported migration to: " + fileName
		return "@ExportedMigrationXMLTo@: " + fileName;
	}

	@Override
	protected void prepare() {

		ProcessInfoParameter[] paras = getParameter();
		for ( ProcessInfoParameter para : paras )
		{
			if ( para.getParameterName().equals("AD_Migration_ID"))
				migrationId =  para.getParameterAsInt();
			
			// File name removed as a parameter - #1926

			
		}
		
		// if run from Migration window
		if ( migrationId == 0 )
			migrationId = getRecord_ID();
		
		
		log.log(Level.FINE, "AD_Migration_ID = " + migrationId);

	}
	
}
