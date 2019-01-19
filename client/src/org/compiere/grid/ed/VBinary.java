/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.grid.ed;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.logging.Level;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.Border;

import org.adempiere.exceptions.ValueChangeListener;
import org.compiere.model.GridField;
import org.compiere.swing.CButton;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * Binary Editor. Open and/or save binary data.
 * 
 * @author Jorg Janke
 * @author Jan Thielemann - evenos GmbH - jan.thielemann@evenos.de
 * @version $Id: VBinary.java,v 1.3 2012/10/24 09:35:22
 */
public class VBinary extends JComponent implements VEditor, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 298576564679201761L;

	private CButton m_OpenButton = new CButton();
	private CButton m_SaveButton = new CButton();

	/**
	 * Binary Editor
	 * 
	 * @param columnName
	 *            column name
	 * @param WindowNo
	 */
	public VBinary(String columnName, int WindowNo) {
		super();
		super.setName(columnName);
		m_columnName = columnName;

		setLayout(new BorderLayout());

		// JPanel which holds our buttons
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
		buttonPanel.setPreferredSize(new Dimension(500, 25));
		add(buttonPanel, BorderLayout.CENTER);

		// Configure the Call Button
		m_OpenButton.setIcon(Env.getImageIcon("Open16.gif"));
		m_OpenButton.addActionListener(this);
		m_OpenButton.setToolTipText(Msg.translate(Env.getCtx(), "Upload"));
		buttonPanel.add(m_OpenButton);

		// Configure the Edit button
		m_SaveButton.setIcon(Env.getImageIcon("Save16.gif"));
		m_SaveButton.addActionListener(this);
		m_SaveButton.setToolTipText(Msg.translate(Env.getCtx(), "Download"));
		buttonPanel.add(m_SaveButton);
	} // VBinary

	/**
	 * Dispose
	 */
	public void dispose() {
		m_data = null;
	} // dispose

	/** Column Name */
	private String m_columnName;
	/** Data */
	private Object m_data = null;

	/** Logger */
	private static CLogger log = CLogger.getCLogger(VBinary.class);

	/**
	 * Set Value
	 * 
	 * @param value
	 */
	public void setValue(Object value) {
		log.config("=" + value);
		m_data = value;

		m_SaveButton.setEnabled(value != null);
	} // setValue

	/**
	 * Get Value
	 * 
	 * @return value
	 */
	public Object getValue() {
		return m_data;
	} // getValue

	/**
	 * Get Display Value
	 * 
	 * @return image name
	 */
	public String getDisplay() {
		return (m_data == null ? null : "#" + ((byte[]) m_data).length);
	} // getDisplay

	/**
	 * Set ReadWrite
	 * 
	 * @param rw
	 */
	public void setReadWrite(boolean rw) {
		if (isEnabled() != rw)
			setEnabled(rw);
	} // setReadWrite

	/**
	 * Get ReadWrite
	 * 
	 * @return true if rw
	 */
	public boolean isReadWrite() {
		return super.isEnabled();
	} // getReadWrite

	/**
	 * Set Mandatory
	 * 
	 * @param mandatory
	 *            NOP
	 */
	public void setMandatory(boolean mandatory) {
	} // setMandatory

	/**
	 * Get Mandatory
	 * 
	 * @return false
	 */
	public boolean isMandatory() {
		return false;
	} // isMandatory

	/**
	 * Set Background - nop
	 * 
	 * @param color
	 */
	public void setBackground(Color color) {
	} // setBackground

	/**
	 * Set Background - nop
	 */
	public void setBackground() {
	} // setBackground

	/**
	 * Set Background - nop
	 * 
	 * @param error
	 */
	public void setBackground(boolean error) {
	} // setBackground

	/**
	 * Property Change
	 * 
	 * @param evt
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		log.info(evt.toString());
		if (evt.getPropertyName().equals(org.compiere.model.GridField.PROPERTY))
			setValue(evt.getNewValue());
	} // propertyChange

	/**
	 * ActionListener - start dialog and set value
	 * 
	 * @param e
	 *            event
	 */
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser("");
		fc.setMultiSelectionEnabled(false);
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

		boolean save = false;
		int option = 0;

		if (e.getSource() == m_OpenButton) {
			log.fine("Open Button");
			// Show open dialog
			option = fc.showOpenDialog(this);
			save = false;
		}
		if (e.getSource() == m_SaveButton) {
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
				byte[] buffer = (byte[]) m_data;
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
				m_data = data;
				log.config("Load from " + file + " #" + data.length);
				os.close();
			}
		} catch (Exception ex) {
			log.log(Level.WARNING, "Save=" + save, ex);
		}

		try {
			fireVetoableChange(m_columnName, null, m_data);
		} catch (PropertyVetoException pve) {
		}
	} // actionPerformed

	// Field for Value Preference
	private GridField m_mField = null;

	/**
	 * Set Field/WindowNo for ValuePreference (NOP)
	 * 
	 * @param mField
	 */
	public void setField(GridField mField) {
		m_mField = mField;
	} // setField

	public GridField getField() {
		return m_mField;
	}

	@Override
	public void setVisible(boolean visible) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName(String columnName) {
		// TODO Auto-generated method stub
	}

//	@Override
//	public void addVetoableChangeListener(VetoableChangeListener listener) {
		// TODO Auto-generated method stub
//	}


//	@Override
//	public void removeVetoableChangeListener(VetoableChangeListener listener) {
		// TODO Auto-generated method stub
//	}

	@Override
	public void addActionListener(ActionListener listener) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setFont(Font font) {
		// TODO Auto-generated method stub
	}

	@Override
    public void setBorder(Border border) {
            // TODO Auto-generated method stub
    }
    
	@Override
	public void setForeground(Color color) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addValueChangeListener(ValueChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

} // VBinary