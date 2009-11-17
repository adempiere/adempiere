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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.logging.Level;

import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.FieldRecordInfo;
import org.compiere.apps.search.InfoSchedule;
import org.compiere.model.GridField;
import org.compiere.model.MResourceAssignment;
import org.compiere.swing.CButton;
import org.compiere.swing.CMenuItem;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *	Resource Assignment Entry
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: VAssignment.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 */
public class VAssignment extends JComponent
	implements VEditor, ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 796127013756416974L;

	/**
	 *	Mouse Listener
	 */
	final class VAssignment_mouseAdapter extends MouseAdapter
	{
		/**
		 *	Constructor
		 *  @param adaptee adaptee
		 */
		VAssignment_mouseAdapter(VAssignment adaptee)
		{
			this.adaptee = adaptee;
		}	//	VAssignment_mouseAdapter

		private VAssignment adaptee;

		/**
		 *	Mouse Listener
		 *  @param e event
		 */
		public void mouseClicked(MouseEvent e)
		{
			//	Double Click
			if (e.getClickCount() > 1)
				adaptee.actionPerformed(new ActionEvent(e.getSource(), e.getID(), "Mouse"));
			//	popup menu
			if (SwingUtilities.isRightMouseButton(e))
				adaptee.popupMenu.show((Component)e.getSource(), e.getX(), e.getY());
		}	//	mouse Clicked

	}	//	VAssignment_mouseAdapter
	
	/**
	 *	IDE Constructor
	 */
	public VAssignment()
	{
		this (false, false, true);
	}	//	VAssigment

	/**
	 *	Create Resource Assigment.
	 *  <pre>
	 * 		Resource DateTimeFrom Qty UOM Button
	 *  </pre>
	 *  @param mandatory mandatory
	 *  @param isReadOnly read only
	 *  @param isUpdateable updateable
	 */
	public VAssignment (boolean mandatory, boolean isReadOnly, boolean isUpdateable)
	{
		super();
	//	super.setName(columnName);
		LookAndFeel.installBorder(this, "TextField.border");
		this.setLayout(new BorderLayout());
		//  Size
		this.setPreferredSize(m_text.getPreferredSize());
		int height = m_text.getPreferredSize().height;

		//	***	Text	***
		m_text.setEditable(false);
		m_text.setFocusable(false);
		m_text.setBorder(null);
		m_text.setHorizontalAlignment(JTextField.LEADING);
		//	Background
		setMandatory(mandatory);
		this.add(m_text, BorderLayout.CENTER);

		//	***	Button	***
		m_button.setIcon(Env.getImageIcon("Assignment10.gif"));
		m_button.setMargin(new Insets(0, 0, 0, 0));
		m_button.setPreferredSize(new Dimension(height, height));
		m_button.addActionListener(this);
		m_button.setFocusable(true);
		this.add(m_button, BorderLayout.EAST);

		//	Prefereed Size
		this.setPreferredSize(this.getPreferredSize());		//	causes r/o to be the same length
		//	ReadWrite
		if (isReadOnly || !isUpdateable)
			setReadWrite(false);
		else
			setReadWrite(true);

		//	Popup
		m_text.addMouseListener(new VAssignment_mouseAdapter(this));
		menuEditor = new CMenuItem(Msg.getMsg(Env.getCtx(), "InfoResource"), Env.getImageIcon("Zoom16.gif"));
		menuEditor.addActionListener(this);
		popupMenu.add(menuEditor);
	}	//	VAssignment

	/**	Data Value				*/
	private Object				m_value = null;
	/** Get Info				*/
	private PreparedStatement	m_pstmt = null;

	/** The Text Field          */
	private JTextField			m_text = new JTextField (VLookup.DISPLAY_LENGTH);
	/** The Button              */
	private CButton				m_button = new CButton();

	JPopupMenu          		popupMenu = new JPopupMenu();
	private CMenuItem 			menuEditor;

	private boolean				m_readWrite;
	private boolean				m_mandatory;

	/**	The Format				*/
	private DateFormat			m_dateFormat = DisplayType.getDateFormat(DisplayType.DateTime);
	private NumberFormat		m_qtyFormat = DisplayType.getNumberFormat(DisplayType.Quantity);
	private GridField m_mField;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VAssignment.class);
	
	/**
	 * 	Dispose resources
	 */
	public void dispose()
	{
		try
		{
			if (m_pstmt != null)
				m_pstmt.close();
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "VAssignment.dispose");
		}
		m_text = null;
		m_button = null;
	}	//	dispose

	/**
	 * 	Set Mandatory
	 * 	@param mandatory mandatory
	 */
	public void setMandatory (boolean mandatory)
	{
		m_mandatory = mandatory;
		m_button.setMandatory(mandatory);
		setBackground (false);
	}	//	setMandatory

	/**
	 * 	Get Mandatory
	 *  @return mandatory
	 */
	public boolean isMandatory()
	{
		return m_mandatory;
	}	//	isMandatory

	/**
	 * 	Set ReadWrite
	 * 	@param rw read rwite
	 */
	public void setReadWrite (boolean rw)
	{
		m_readWrite = rw;
		m_button.setReadWrite(rw);
		setBackground (false);
	}	//	setReadWrite

	/**
	 * 	Is Read Write
	 * 	@return read write
	 */
	public boolean isReadWrite()
	{
		return m_readWrite;
	}	//	isReadWrite

	/**
	 * 	Set Foreground
	 * 	@param color color
	 */
	public void setForeground (Color color)
	{
		m_text.setForeground(color);
	}	//	SetForeground

	/**
	 * 	Set Background
	 * 	@param error Error
	 */
	public void setBackground (boolean error)
	{
		if (error)
			setBackground(AdempierePLAF.getFieldBackground_Error());
		else if (!m_readWrite)
			setBackground(AdempierePLAF.getFieldBackground_Inactive());
		else if (m_mandatory)
			setBackground(AdempierePLAF.getFieldBackground_Mandatory());
		else
			setBackground(AdempierePLAF.getInfoBackground());
	}	//	setBackground

	/**
	 * 	Set Background
	 * 	@param color Color
	 */
	public void setBackground (Color color)
	{
		m_text.setBackground(color);
	}	//	setBackground

	/**
	 * 	Request Focus
	 */
	public void requestFocus ()
	{
		m_text.requestFocus ();
	}	//	requestFocus

	/**************************************************************************
	 * 	Set/lookup Value
	 * 	@param value value
	 */
	public void setValue(Object value)
	{
		if (value == m_value)
			return;
		m_value = value;
		int S_ResourceAssignment_ID = 0;
		if (m_value != null && m_value instanceof Integer)
			S_ResourceAssignment_ID = ((Integer)m_value).intValue();
		//	Set Empty
		if (S_ResourceAssignment_ID == 0)
		{
			m_text.setText("");
			return;
		}

		//	Statement
		if (m_pstmt == null)
			m_pstmt = DB.prepareStatement("SELECT r.Name,ra.AssignDateFrom,ra.Qty,uom.UOMSymbol "
				+ "FROM S_ResourceAssignment ra, S_Resource r, S_ResourceType rt, C_UOM uom "
				+ "WHERE ra.S_ResourceAssignment_ID=?"
				+ " AND ra.S_Resource_ID=r.S_Resource_ID"
				+ " AND r.S_ResourceType_ID=rt.S_ResourceType_ID"
				+ " and rt.C_UOM_ID=uom.C_UOM_ID", null);
		//
		try
		{
			m_pstmt.setInt(1, S_ResourceAssignment_ID);
			ResultSet rs = m_pstmt.executeQuery();
			if (rs.next())
			{
				StringBuffer sb = new StringBuffer(rs.getString(1));
				sb.append(" ").append(m_dateFormat.format(rs.getTimestamp(2)))
					.append(" ").append(m_qtyFormat.format(rs.getBigDecimal(3)))
					.append(" ").append(rs.getString(4).trim());
				m_text.setText(sb.toString());
			}
			else
				m_text.setText("<" + S_ResourceAssignment_ID + ">");
			rs.close();
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
	}	//	setValue

	/**
	 * 	Get Value
	 * 	@return value
	 */
	public Object getValue()
	{
		return m_value;
	}	//	getValue

	/**
	 * 	Get Display Value
	 *	@return info
	 */
	public String getDisplay()
	{
		return m_text.getText();
	}	//	getDisplay

	/*************************************************************************/

	/**
	 * 	Set Field - NOP
	 * 	@param mField MField
	 */
	public void setField(GridField mField)
	{
		m_mField = mField;
		if (m_mField != null)
			FieldRecordInfo.addMenu(this, popupMenu);
	}	//	setField

	/**
	 *  Action Listener Interface
	 *  @param listener listener
	 */
	public void addActionListener(ActionListener listener)
	{
		//	m_text.addActionListener(listener);
	}   //  addActionListener

	/**
	 * 	Action Listener - start dialog
	 * 	@param e Event
	 */
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals(FieldRecordInfo.CHANGE_LOG_COMMAND))
		{
			FieldRecordInfo.start(m_mField);
			return;
		}
		if (!m_button.isEnabled())
			return;
		m_button.setEnabled(false);
		//
		Integer oldValue = (Integer)getValue();
		int S_ResourceAssignment_ID = oldValue == null ? 0 : oldValue.intValue();
		MResourceAssignment ma = new MResourceAssignment(Env.getCtx(), S_ResourceAssignment_ID, null);

		//	Start VAssignment Dialog
		if (S_ResourceAssignment_ID != 0)
		{
			VAssignmentDialog vad = new VAssignmentDialog (Env.getFrame(this), ma, true, true);
			ma = vad.getMResourceAssignment();
		}
		//	Start InfoSchedule directly
		else
		{
			InfoSchedule is = new InfoSchedule(Env.getFrame(this), ma, true);
			ma = is.getMResourceAssignment();
		}

		//	Set Value
		if (ma != null && ma.getS_ResourceAssignment_ID() != 0)
		{
			setValue(new Integer(ma.getS_ResourceAssignment_ID()));
			try
			{
				fireVetoableChange(this.getName(), new Object(), getValue());
			}
			catch (PropertyVetoException pve)
			{
				log.log(Level.SEVERE, "", pve);
			}
		}
		m_button.setEnabled(true);
		requestFocus();
	}	//	actionPerformed

	/**
	 *  Property Change Listener
	 *  @param evt event
	 */
	public void propertyChange (PropertyChangeEvent evt)
	{
		if (evt.getPropertyName().equals(org.compiere.model.GridField.PROPERTY))
			setValue(evt.getNewValue());
	}   //  propertyChange

}	//	VAssignment
