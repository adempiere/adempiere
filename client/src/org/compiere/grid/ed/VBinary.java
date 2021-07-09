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
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.logging.Level;

import javax.swing.JComponent;
import javax.swing.JFileChooser;

import org.adempiere.plaf.AdempiereBinaryUI;
import org.compiere.swing.CButton;
import org.compiere.util.CLogger;

/**
 * Binary Editor. Open and/or save binary data.
 * 
 * @author Jorg Janke
 * @author Jan Thielemann - evenos GmbH - jan.thielemann@evenos.de
 * @author Michael McKay, mckayERP@gmail.com
 *  	<li><a href="https://github.com/adempiere/adempiere/issues/2908">#2908</a>Updates to ADempiere Look and Feel
 * 
 * @version 3.9.4
 */
public class VBinary extends VEditorAbstract 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 298576564679201761L;

	/**
	 * Define the ui class ID. It will be added to the 
	 * look and feel to define the ui class to use.
	 */
	private final static String uiClassID = "BinaryUI";
	
	@Override
    public String getUIClassID() {
        return uiClassID ;
    }
	
	private CButton openButton = new CButton();
	private CButton saveButton = new CButton();

	/**
	 * Binary Editor
	 * 
	 * @param columnName
	 *            column name
	 * @param WindowNo
	 */
	public VBinary(String columnName, int WindowNo) {
		this(columnName, WindowNo, false);
	}
	
	/**
	 * Binary Editor. While based on the VEditorAbstract, the binary editor consists
	 * of just two buttons - one to open a file and the other to save it.  There
	 * is no text field to display info about the file or data.  
	 * 
	 * @param columnName
	 *            column name
	 * @param WindowNo
	 */
	public VBinary(String columnName, int WindowNo, boolean tableCellEditor) {	
		super();
		super.setName(columnName);
		super.setTableCellEditor(tableCellEditor);

		//  The editor is configured in the PLAF UI AdempierBinaryUI
		
		// Configure the Call Button
		openButton = ((AdempiereBinaryUI) getUI()).getOpenButton();
		openButton.addActionListener(this);

		// Configure the Edit button
		saveButton = ((AdempiereBinaryUI) getUI()).getSaveButton();
		saveButton.addActionListener(this);
		
	} // VBinary

	/**
	 * Dispose
	 */
	@Override
	public void dispose() {
		
		super.dispose();
		currentData = null;
		
	} // dispose

	private Object currentData;

	/** Logger */
	private static CLogger log = CLogger.getCLogger(VBinary.class);


	/**
	 * Get Display Value
	 * 
	 * @return image name
	 */
	public String getDisplay() {
		return (currentData == null ? null : "#" + ((byte[]) currentData).length);
	} // getDisplay


	/**
	 * ActionListener - start dialog and set value
	 * 
	 * @param e
	 *            event
	 */
	public void actionPerformed(ActionEvent e) {
		
		super.actionPerformed(e);
		
		JFileChooser fc = new JFileChooser("");
		fc.setMultiSelectionEnabled(false);
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

		boolean save = false;
		int option = 0;

		if (e.getSource() == openButton) {
			log.fine("Open Button");
			// Show open dialog
			option = fc.showOpenDialog(this);
			save = false;
		}
		if (e.getSource() == saveButton) {
			log.fine("Save Button");
			// Show save dialog
			option = fc.showSaveDialog(this);
			save = true;
		}

		if (option != JFileChooser.APPROVE_OPTION)
			return;
		
		File file = fc.getSelectedFile();
		if (file == null)
			return;
		//
		log.info(file.toString());
		
		try {
			if (save) {
				FileOutputStream os = new FileOutputStream(file);
				byte[] buffer = (byte[]) currentData;
				os.write(buffer);
				os.flush();
				os.close();
				log.config("Save to " + file + " #" + buffer.length);
			} else // load
			{
				FileInputStream is = new FileInputStream(file);
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024 * 8]; // 8kB
				int length = -1;
				while ((length = is.read(buffer)) != -1)
					os.write(buffer, 0, length);
				is.close();
				byte[] data = os.toByteArray();
				currentData = data;
				log.config("Load from " + file + " #" + data.length);
				os.close();
			}
		} catch (Exception ex) {
			log.log(Level.WARNING, "Save=" + save, ex);
		}

		saveButton.setEnabled(currentData != null);

	} // actionPerformed

	@Override
	public JComponent getComponent() {
		return this;
	}

	@Override
	protected Object getCurrentValue() {
		return currentData;
	}

	@Override
	protected String setDisplayBasedOnValue(Object value) {
		
		currentData = value;
		saveButton.setEnabled(currentData != null);
		
		return null;
	}

	@Override
	protected void handleInvalidValue() {
		// No option when an invalid value is entered. 
	}

} // VBinary