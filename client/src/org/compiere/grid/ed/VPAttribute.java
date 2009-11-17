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
import java.util.logging.Level;

import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.FieldRecordInfo;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MAttributeSet;
import org.compiere.model.MPAttributeLookup;
import org.compiere.model.MProduct;
import org.compiere.swing.CButton;
import org.compiere.swing.CMenuItem;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *  Product Attribute Set Instance Editor
 *
 *  @author Jorg Janke
 *  @version $Id: VPAttribute.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 *  
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 			<li>BF [ 1895041 ] NPE when move product with attribute set
 * 			<li>BF [ 1770177 ] Inventory Move Locator Error - integrated MGrigioni bug fix
 * 			<li>BF [ 2011222 ] ASI Dialog is reseting locator
 */
public class VPAttribute extends JComponent
	implements VEditor, ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 108156477716619163L;

	/**
	 *	Mouse Listener
	 */
	final class VPAttribute_mouseAdapter extends MouseAdapter
	{
		/**
		 *	Constructor
		 *  @param adaptee adaptee
		 */
		VPAttribute_mouseAdapter(VPAttribute adaptee)
		{
			m_adaptee = adaptee;
		}	//	VPAttribute_mouseAdapter

		private VPAttribute m_adaptee;

		/**
		 *	Mouse Listener
		 *  @param e event
		 */
		public void mouseClicked(MouseEvent e)
		{
			//	Double Click
			if (e.getClickCount() > 1)
				m_adaptee.actionPerformed(new ActionEvent(e.getSource(), e.getID(), "Mouse"));
			//	popup menu
			if (SwingUtilities.isRightMouseButton(e))
				m_adaptee.popupMenu.show((Component)e.getSource(), e.getX(), e.getY());
		}	//	mouse Clicked

	}	//	VPAttribute_mouseAdapter

	/**
	 *	IDE Constructor
	 */
	public VPAttribute()
	{
		this (null, false, false, true, 0, null);
	}	//	VAssigment

	/**
	 *	Create Product Attribute Set Instance Editor.
	 *  @param mandatory mandatory
	 *  @param isReadOnly read only
	 *  @param isUpdateable updateable
	 * 	@param WindowNo WindowNo
	 * 	@param lookup Model Product Attribute
	 */
	public VPAttribute (boolean mandatory, boolean isReadOnly, boolean isUpdateable, 
		int WindowNo, MPAttributeLookup lookup)
	{
		this(null, mandatory, isReadOnly, isUpdateable, WindowNo, lookup);
	}
	
	/**
	 *	Create Product Attribute Set Instance Editor.
	 *  @param gridTab
	 *  @param mandatory mandatory
	 *  @param isReadOnly read only
	 *  @param isUpdateable updateable
	 * 	@param WindowNo WindowNo
	 * 	@param lookup Model Product Attribute
	 */
	public VPAttribute (GridTab gridTab, boolean mandatory, boolean isReadOnly, boolean isUpdateable, 
		int WindowNo, MPAttributeLookup lookup)
	{
		super.setName("M_AttributeSetInstance_ID");
		m_GridTab = gridTab; // added for processCallout
		m_WindowNo = WindowNo;
		m_mPAttribute = lookup;
		m_C_BPartner_ID = Env.getContextAsInt(Env.getCtx(), WindowNo, "C_BPartner_ID");
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
		m_button.setIcon(Env.getImageIcon("PAttribute10.gif"));
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
		m_text.addMouseListener(new VPAttribute_mouseAdapter(this));
		menuEditor = new CMenuItem(Msg.getMsg(Env.getCtx(), "PAttribute"), Env.getImageIcon("Zoom16.gif"));
		menuEditor.addActionListener(this);
		popupMenu.add(menuEditor);
	}	//	VPAttribute

	/**	Data Value				*/
	private Object				m_value = new Object();
	/** The Attribute Instance	*/
	private MPAttributeLookup	m_mPAttribute;

	/** The Text Field          */
	private JTextField			m_text = new JTextField (VLookup.DISPLAY_LENGTH);
	/** The Button              */
	private CButton				m_button = new CButton();

	JPopupMenu          		popupMenu = new JPopupMenu();
	private CMenuItem 			menuEditor;

	private boolean				m_readWrite;
	private boolean				m_mandatory;
	private int					m_WindowNo;
	private int					m_C_BPartner_ID;
	/** The Grid Tab * */
	private GridTab m_GridTab; // added for processCallout
	/** The Grid Field * */
	private GridField m_GridField; // added for processCallout
	
	/**	Calling Window Info				*/
	private int					m_AD_Column_ID = 0;
	private GridField m_mField;
	/**	No Instance Key					*/
	private static Integer		NO_INSTANCE = new Integer(0);
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VPAttribute.class);
		

	/**
	 * 	Dispose resources
	 */
	public void dispose()
	{
		m_text = null;
		m_button = null;
		m_mPAttribute.dispose();
		m_mPAttribute = null;
		m_GridField = null;
		m_GridTab = null;
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

	
	/**************************************************************************
	 * 	Set/lookup Value
	 * 	@param value value
	 */
	public void setValue(Object value)
	{
		if (value == null || NO_INSTANCE.equals(value))
		{
			m_text.setText("");
			m_value = value;
			return;
		}
		
		//	The same
		if (value.equals(m_value)) 
			return;
		//	new value
		log.fine("Value=" + value);
		m_value = value;
		m_text.setText(m_mPAttribute.getDisplay(value));	//	loads value
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

	
	/**************************************************************************
	 * 	Set Field
	 * 	@param mField MField
	 */
	public void setField(GridField mField)
	{
		//	To determine behavior
		m_AD_Column_ID = mField.getAD_Column_ID();
		m_GridField = mField;
		
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
		
		if (!m_button.isEnabled ())
			return;
		m_button.setEnabled (false);
		//
		Integer oldValue = (Integer)getValue ();
		int oldValueInt = oldValue == null ? 0 : oldValue.intValue ();
		int M_AttributeSetInstance_ID = oldValueInt;
		int M_Product_ID = Env.getContextAsInt (Env.getCtx (), m_WindowNo, "M_Product_ID");
		int M_ProductBOM_ID = Env.getContextAsInt (Env.getCtx (), m_WindowNo, "M_ProductBOM_ID");
		int M_Locator_ID = -1;

		log.config("M_Product_ID=" + M_Product_ID + "/" + M_ProductBOM_ID
			+ ",M_AttributeSetInstance_ID=" + M_AttributeSetInstance_ID
			+ ", AD_Column_ID=" + m_AD_Column_ID);
		
		//	M_Product.M_AttributeSetInstance_ID = 8418
		boolean productWindow = m_AD_Column_ID == 8418;		//	HARDCODED
		
		//	Exclude ability to enter ASI
		boolean exclude = true;
		
		if (M_Product_ID != 0)
		{
			MProduct product = MProduct.get(Env.getCtx(), M_Product_ID);
			int M_AttributeSet_ID = product.getM_AttributeSet_ID();
			if (M_AttributeSet_ID != 0)
			{
				MAttributeSet mas = MAttributeSet.get(Env.getCtx(), M_AttributeSet_ID);
				exclude = mas.excludeEntry(m_AD_Column_ID, Env.isSOTrx(Env.getCtx(), m_WindowNo));
			}
		}
		
		boolean changed = false;
		if (M_ProductBOM_ID != 0)	//	Use BOM Component
			M_Product_ID = M_ProductBOM_ID;
		//	
		if (!productWindow && (M_Product_ID == 0 || exclude))
		{
			changed = true;
			m_text.setText(null);
			M_AttributeSetInstance_ID = 0;
		}
		else
		{
			VPAttributeDialog vad = new VPAttributeDialog (Env.getFrame (this), 
				M_AttributeSetInstance_ID, M_Product_ID, m_C_BPartner_ID,
				productWindow, m_AD_Column_ID, m_WindowNo);
			if (vad.isChanged())
			{
				m_text.setText(vad.getM_AttributeSetInstanceName());
				M_AttributeSetInstance_ID = vad.getM_AttributeSetInstance_ID();
				if (!productWindow && vad.getM_Locator_ID() > 0)
				{
					M_Locator_ID = vad.getM_Locator_ID();
				}
				changed = true;
			}
		}
		
		//	Set Value
		if (changed)
		{
			log.finest("Changed M_AttributeSetInstance_ID=" + M_AttributeSetInstance_ID);
			m_value = new Object();				//	force re-query display
			if (M_AttributeSetInstance_ID == 0)
				setValue(null);
			else
				setValue(new Integer(M_AttributeSetInstance_ID));
			// Change Locator
			if (m_GridTab != null && M_Locator_ID > 0)
			{
				log.finest("Change M_Locator_ID="+M_Locator_ID);
				m_GridTab.setValue("M_Locator_ID", M_Locator_ID);
			}
			//
			try
			{
				String columnName = "M_AttributeSetInstance_ID";
		 	 	if (m_GridField != null)
		 	 	{
		 	 		columnName = m_GridField.getColumnName();
		 	 	}
		 	 	fireVetoableChange(columnName, new Object(), getValue());
			}
			catch (PropertyVetoException pve)
			{
				log.log(Level.SEVERE, "", pve);
			}
			if (M_AttributeSetInstance_ID == oldValueInt && m_GridTab != null && m_GridField != null)
			{
				//  force Change - user does not realize that embedded object is already saved.
				m_GridTab.processFieldChange(m_GridField); 
			}
		}	//	change
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

}	//	VPAttribute
