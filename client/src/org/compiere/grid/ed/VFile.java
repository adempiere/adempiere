/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2019 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
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

package org.compiere.grid.ed;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JComponent;
import javax.swing.JFileChooser;

import org.compiere.swing.CTextField;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *	File/Path Selection
 *
 *  @author 	Initial: Jirimuto
 *  @version 	$Id: VFile.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 *  
 *  @author 	Teo Sarca
 *  @author Michael McKay, mckayERP@gmail.com
 *  	<li><a href="https://github.com/adempiere/adempiere/issues/2908">#2908</a>Updates to ADempiere Look and Feel
 *  
 *  @version 3.9.4
 */
public class VFile extends VEditorAbstract
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4665930745414194731L;

	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VFile.class);

	/**
	 * Define the ui class ID. It will be added to the 
	 * look and feel to define the ui class to use.
	 */
	private final static String uiClassID = "FileUI";

	@Override
    public String getUIClassID() {
        return uiClassID ;
    }
	
	/**
	 *	Constructor
	 *
	 * 	@param columnName column name
	 * 	@param mandatory mandatory
	 * 	@param isReadOnly read only
	 * 	@param isUpdateable updateable
	 * 	@param files Files only if false Directory only
	 */
	public VFile(String columnName, boolean mandatory, 
		boolean isReadOnly, boolean isUpdateable, int fieldLength, boolean files)
	{
		this(columnName, mandatory, isReadOnly, isUpdateable, fieldLength, false, files);

	}

	/**
	 *	Constructor
	 *
	 * 	@param columnName column name
	 * 	@param mandatory mandatory
	 * 	@param isReadOnly read only
	 * 	@param isUpdateable updateable
	 *  @param pathOrFile allow selection of either a directory or a file
	 * 	@param files Files only if false Directory only, ignored if pathOrFile is true.
	 */
	public VFile(String columnName, boolean mandatory, 
		boolean isReadOnly, boolean isUpdateable, int fieldLength, boolean pathOrFile, boolean files)
	{
		this(columnName, mandatory, isReadOnly, isUpdateable, fieldLength, pathOrFile, files, false);
	}
	
	/**
	 *	Constructor
	 *
	 * 	@param columnName column name
	 * 	@param mandatory mandatory
	 * 	@param isReadOnly read only
	 * 	@param isUpdateable updateable
	 *  @param pathOrFile allow selection of either a directory or a file
	 * 	@param files Files only if false Directory only, ignored if pathOrFile is true.
	 *  @param tableCellEditor true if the editor is to be used in a table cell
	 */
	public VFile(String columnName, boolean mandatory, 
		boolean isReadOnly, boolean isUpdateable, int fieldLength, boolean pathOrFile, boolean files,
		boolean tableCellEditor)
	{

		super(columnName, mandatory, isReadOnly, isUpdateable, tableCellEditor);
		
		textEditor = (CTextField) getEditorComponent();
		
		if (pathOrFile)
			m_selectionMode = JFileChooser.FILES_AND_DIRECTORIES;
		else
			if (files)	//	default Directories
				m_selectionMode = JFileChooser.FILES_ONLY;
		
		String col = columnName.toLowerCase();
		if (col.indexOf("open") != -1 || col.indexOf("load") != -1)
			m_dialogType = JFileChooser.OPEN_DIALOG;
		else if (col.indexOf("save") != -1)
			m_dialogType = JFileChooser.SAVE_DIALOG;

		
	}	//	VFile


	/** The Text Field                  */
	private CTextField			textEditor;
	
	/** Selection Mode					*/
	private int					m_selectionMode = JFileChooser.DIRECTORIES_ONLY;
	/** Save/Open						*/
	private int					m_dialogType = JFileChooser.CUSTOM_DIALOG;

	/**
	 *	ActionListener - Button - Start Dialog
	 *  @param e ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		super.actionPerformed(e);
		
		if (e.getSource().equals(this.getButtonComponent()))
			cmd_button();
	}
	
	private void cmd_button() 
	{
		String currentValue = textEditor.getText();
		//
		log.config(currentValue);
		//
		String fieldName = null;
		if (getField() != null)
			fieldName = getField().getHeader();
		else
			fieldName = Msg.getElement(Env.getCtx(), getColumnName()); 
		//
		JFileChooser chooser = new JFileChooser(currentValue);
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileSelectionMode(m_selectionMode);
		chooser.setDialogTitle(fieldName);
		chooser.setDialogType(m_dialogType);
		//	
		int returnVal = -1;
		if (m_dialogType == JFileChooser.SAVE_DIALOG)
			returnVal = chooser.showSaveDialog(this);
		else if (m_dialogType == JFileChooser.OPEN_DIALOG)
			returnVal = chooser.showOpenDialog(this);
		else //	if (m_dialogType == JFileChooser.CUSTOM_DIALOG)
			returnVal= chooser.showDialog(this, fieldName);
		if (returnVal != JFileChooser.APPROVE_OPTION)
			return;
		
		File selectedFile = chooser.getSelectedFile();
		textEditor.setText(selectedFile.getAbsolutePath());
		
	}	//	cmdButton

	@Override
	public JComponent getComponent() {
		
		return textEditor;
		
	}

	@Override
	public String getDisplay() {
		
		return textEditor.getDisplay();
		
	}

	@Override
	protected Object getCurrentValue() {
		
		String currentValue = textEditor.getDisplay();
		if (currentValue == null || currentValue.isEmpty())
			return null;
		else
			return currentValue;
		
	}

	@Override
	protected String setDisplayBasedOnValue(Object value) {
		
		if (value == null)
		{
			textEditor.setText("");
		}
		else if (value instanceof String)
		{
			
			if (((String) value).isEmpty())
				textEditor.setText("");
			else
				textEditor.setText((String) value);
			
		}
		else
		{
			
			textEditor.setText(value.toString());
			// If not a string, flag the value as invalid
			this.setCurrentValueValid(false);
			
		}
		
		return textEditor.getText();
	}

	@Override
	protected void handleInvalidValue() {

		cmd_button();
		
	}
	
}	//	VFile
