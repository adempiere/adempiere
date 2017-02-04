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
package org.compiere.db;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;

import org.adempiere.exceptions.ValueChangeListener;
import org.adempiere.plaf.AdempierePLAF;
import org.compiere.model.GridField;
import org.compiere.swing.CEditor;
import org.compiere.util.DB;

/**
 *  Connection Editor.
 *  A combo box and a button
 *
 *  @author     Jorg Janke
 *  @version    $Id: CConnectionEditor.java,v 1.2 2006/07/30 00:55:13 jjanke Exp $
 */
public class CConnectionEditor extends JComponent
	implements CEditor
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 259945316129032408L;

	/**
	 *  Connection Editor creating new Connection
	 */
	public CConnectionEditor()
	{
		super();
		setName("ConnectionEditor");
		CConnectionEditor_MouseListener ml = new CConnectionEditor_MouseListener();
		//  Layout
		m_text.setEditable(false);
		m_text.setBorder(null);
		m_text.addMouseListener(ml);
		m_server.setIcon(new ImageIcon(getClass().getResource("Server16.gif")));
		m_server.setFocusable(false);
		m_server.setBorder(null);
		m_server.setOpaque(true);
		m_server.addMouseListener(ml);
		m_db.setIcon(new ImageIcon(getClass().getResource("Database16.gif")));
		m_db.setFocusable(false);
		m_db.setBorder(null);
		m_db.setOpaque(true);
		m_db.addMouseListener(ml);
		LookAndFeel.installBorder(this, "TextField.border");
		//
		setLayout(new BorderLayout(0,0));
		add(m_server, BorderLayout.WEST);
		add(m_text, BorderLayout.CENTER);
		add(m_db, BorderLayout.EAST);
	}   //  CConnectionEditor

	/** Text Element        */
	private JTextField  m_text = new JTextField(10);
	/** DB Button Element   */
	private JLabel      m_db = new JLabel ();
	/** Host Button Element */
	private JLabel      m_server = new JLabel();
	/** The Value           */
	private CConnection m_value = null;
	/** ReadWrite           */
	private boolean     m_rw = true;
	/** Mandatory           */
	private boolean     m_mandatory = false;

	/**
	 *	Enable Editor
	 *  @param rw true, if you can enter/select data
	 */
	public void setReadWrite (boolean rw)
	{
		m_rw = rw;
		setBackground(false);
	}   //  setReadWrite

	/**
	 *	Is it possible to edit
	 *  @return true, if editable
	 */
	public boolean isReadWrite()
	{
		return m_rw;
	}   //  isReadWrite

	/**
	 *	Set Editor Mandatory
	 *  @param mandatory true, if you have to enter data
	 */
	public void setMandatory (boolean mandatory)
	{
		m_mandatory = mandatory;
	}   //  setMandatory

	/**
	 *	Is Field mandatory
	 *  @return true, if mandatory
	 */
	public boolean isMandatory()
	{
		return m_mandatory;
	}   //  isMandatory

	/**
	 *  Set Background based on editable / mandatory / error
	 *  @param error if true, set background to error color, otherwise mandatory/editable
	 */
	public void setBackground (boolean error)
	{
		Color c = null;
		if (error)
			c = AdempierePLAF.getFieldBackground_Error();
		else if (!m_rw)
			c = AdempierePLAF.getFieldBackground_Inactive();
		else if (m_mandatory)
			c = AdempierePLAF.getFieldBackground_Mandatory();
		else
			c = AdempierePLAF.getFieldBackground_Normal();
		setBackground(c);
	}   //  setBackground

	/**
	 *  Set Background color
	 *  @param color
	 */
	public void setBackground (Color color)
	{
		m_server.setBackground(color);
		m_text.setBackground(color);
		m_db.setBackground(color);
	}   //  setBackground

	/**
	 *  Set Visible
	 *  @param visible true if field is to be shown
	 */
	public void setVisible (boolean visible)
	{
		super.setVisible(visible);
	}

	/**
	 *	Set Editor to value
	 *  @param value value of the editor
	 */
	public void setValue (Object value)
	{
		if (value != null && value instanceof CConnection)
			m_value = (CConnection)value;
		setDisplay();
	}   //  setValue

	/**
	 *	Return Editor value
	 *  @return current value
	 */
	public Object getValue()
	{
		return m_value;
	}   //  getValue

	/**
	 *  Return Display Value
	 *  @return displayed String value
	 */
	public String getDisplay()
	{
		if (m_value == null)
			return "";
		return m_value.getName();
	}   //  getDisplay

	/**
	 *  Update Display with Connection info
	 */
	public void setDisplay()
	{
		m_text.setText(getDisplay());
		if (m_value == null)
			return;
		//  Text
		if (m_value.isAppsServerOK(false) || m_value.isDatabaseOK())
		{
			m_text.setForeground(AdempierePLAF.getTextColor_OK());
			setBackground(false);
			if (!m_value.isAppsServerOK(false))
				m_server.setBackground(AdempierePLAF.getFieldBackground_Error());
			if (!m_value.isDatabaseOK())
				m_db.setBackground(AdempierePLAF.getFieldBackground_Error());
		}
		else
		{
			m_text.setForeground(AdempierePLAF.getTextColor_Issue());
			setBackground(true);
		}
	}   //  setDisplay


	/**************************************************************************
	 *  Remove Action Listener
	 *  @param l
	 */
	public synchronized void removeActionListener(ActionListener l)
	{
		listenerList.remove(ActionListener.class, l);
	}   //  removeActionListener

	/**
	 *  Add Action Listener
	 *  @param l
	 */
	public synchronized void addActionListener(ActionListener l)
	{
	    listenerList.add(ActionListener.class, l);
	}   //  addActionListener

	/**
	 *  Fire Action Performed
	 */
	private void fireActionPerformed()
	{
		ActionEvent e = null;
		ActionListener[] listeners = listenerList.getListeners(ActionListener.class);
        for (int i = 0; i < listeners.length; i++) 
        {
       		if (e == null) 
       			e = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "actionPerformed");
       		listeners[i].actionPerformed(e);
        }
	}   //  fireActionPerformed

	
	/**************************************************************************
	 *  Test Method
	 *  @param args
	 */
	public static void main(String[] args)
	{
	//	System.out.println("CConnectionEditor");
		JFrame frame = new JFrame("CConnectionEditor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getRootPane().getContentPane().add(new CConnectionEditor());
		AdempierePLAF.showCenterScreen(frame);
	}   //  main


	/**
	 *  MouseListener
	 */
	public class CConnectionEditor_MouseListener extends MouseAdapter
	{
		/**
		 *  Mouse Clicked - Open Dialog
		 *  @param e
		 */
		public void mouseClicked(MouseEvent e)
		{
			if (!isEnabled() || !m_rw || m_active)
				return;
			m_active = true;
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			//
			CConnectionDialog cd = new CConnectionDialog(m_value);
			setValue(cd.getConnection());
			if (!cd.isCancel())
				fireActionPerformed();
			else {
				DB.closeTarget();
				DB.setDBTarget(m_value);
			}
			
			//
			setCursor(Cursor.getDefaultCursor());
			m_active = false;
		}   //  mouseClicked

		private boolean     m_active = false;
	}   //  CConnectionExitor_MouseListener


	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addValueChangeListener(ValueChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GridField getField() {
		// TODO Auto-generated method stub
		return null;
	}

}   //  CConnectionEditor
