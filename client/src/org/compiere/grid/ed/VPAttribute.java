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

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
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
import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;

import org.adempiere.exceptions.ValueChangeListener;
import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.RecordInfo;
import org.compiere.apps.search.InfoPAttribute;
import org.compiere.apps.search.InfoProduct;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MAttributeSet;
import org.compiere.model.MPAttributeLookup;
import org.compiere.model.MProduct;
import org.compiere.swing.CButton;
import org.compiere.swing.CDialog;
import org.compiere.swing.CMenuItem;
import org.compiere.swing.CTextField;
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
 * 
 * @author Michael McKay, 
 * 				<li>ADEMPIERE-72 VLookup and Info Window improvements
 * 					https://adempiere.atlassian.net/browse/ADEMPIERE-72
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 146 ] Remove unnecessary class, add support for info to specific column
 *		@see https://github.com/adempiere/adempiere/issues/146
 */
public class VPAttribute extends JComponent
	implements VEditor, ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1823370077523962901L;

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
		this (null, false, false, true, 0, null, false);
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
		int WindowNo, MPAttributeLookup lookup, boolean searchOnly)
	{
		this(null, mandatory, isReadOnly, isUpdateable, WindowNo, lookup, searchOnly);
	}
	
	/**
	 *	Create Product Attribute Set Instance Editor.
	 *  @param gridTab
	 *  @param mandatory mandatory
	 *  @param isReadOnly read only
	 *  @param isUpdateable updateable
	 * 	@param WindowNo WindowNo
	 * 	@param lookup Model Product Attribute
	 *  @param searchOnly True if only used to search instances
	 */
	public VPAttribute (GridTab gridTab, boolean mandatory, boolean isReadOnly, boolean isUpdateable, 
		int WindowNo, MPAttributeLookup lookup, boolean searchOnly)
	{
		super();
		super.setName(m_columnName);
		m_text.setName("VPAttribute Text - " + m_columnName);
		m_button.setName("VPAttribute Button - " + m_columnName);
		m_value = 0;
		m_GridTab = gridTab; // added for processCallout
		m_WindowNo = WindowNo;
		m_mPAttribute = lookup;
		m_C_BPartner_ID = Env.getContextAsInt(Env.getCtx(), WindowNo, "C_BPartner_ID");
		m_searchOnly = searchOnly;
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
		
		set_oldValue();
	}	//	VPAttribute

	/**	Data Value				*/
	private Object				m_value = new Object();
	/** Attribute Where Clause  */
	private String m_pAttributeWhere = null;
	/** Column Name - fixed		*/
	private String				m_columnName = "M_AttributeSetInstance_ID";
	/** The Attribute Instance	*/
	private MPAttributeLookup	m_mPAttribute;

	/** The Text Field          */
	private CTextField			m_text = new CTextField();
	/** The Button              */
	private CButton				m_button = new CButton();

	JPopupMenu          		popupMenu = new JPopupMenu();
	private CMenuItem 			menuEditor;

	private boolean				m_readWrite;
	private boolean				m_mandatory;
	private int					m_WindowNo;
	private int					m_C_BPartner_ID;
	private boolean 			m_searchOnly;
	/** The Grid Tab * */
	private GridTab m_GridTab; // added for processCallout
	/** The Grid Field * */
	private GridField m_GridField; // added for processCallout
	
	/**	Calling Window Info				*/
	private int					m_AD_Column_ID = 0;
	/** record the value for comparison at a point in the future */
	private Integer m_oldValue = 0;
	private String m_oldText = "";
	private String m_oldWhere = "";
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
			m_pAttributeWhere = "";
			return;
		}
		
		//	The same
		if (value.equals(m_value)) 
			return;
		//	new value
		log.fine("Value=" + value);
		m_value = value;
		m_text.setText(m_mPAttribute.getDisplay(value));	//	loads value
		// The text can be long.  Use the tooltip to help display the info.
		m_text.setToolTipText(m_text.getText());
		m_pAttributeWhere = "EXISTS (SELECT * FROM M_Storage s "
				+ "WHERE s.M_AttributeSetInstance_ID=" + value
				+ " AND s.M_Product_ID=p.M_Product_ID)";
	}	//	setValue

	/**
	 * 	Get Value
	 * 	@return value
	 */
	public Object getValue()
	{
		Integer temp = null;
		if (m_value != null || NO_INSTANCE.equals(m_value)) {
			try {
				temp = (Integer) m_value;
			}
			catch (ClassCastException cce)
			{
				temp = null;
			}
		}
		return temp;
	}	//	getValue

	/**
	 * Get Attribute Where clause
	 * @return String
	 */
	public String getAttributeWhere()
	{
		return m_pAttributeWhere;
	}	//	getAttributeWhere()

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
		//	To determine behaviour
		m_AD_Column_ID = mField.getAD_Column_ID();
		m_GridField = mField;
		
		if (m_GridField != null)
			RecordInfo.addMenu(this, popupMenu);
	}	//	setField
	
	@Override
	public GridField getField() {
		return m_GridField;
	}

	/**
	 *  Action Listener Interface
	 *  @param listener listener
	 */
	public void addActionListener(ActionListener listener)
	{
		m_text.addActionListener(listener);
	}   //  addActionListener

	/**
	 * 	Action Listener - start dialog
	 * 	@param e Event
	 */
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals(RecordInfo.CHANGE_LOG_COMMAND))
		{
			RecordInfo.start(m_GridField);
			return;
		}
		
		if (!m_button.isEnabled ())
			return;
		m_button.setEnabled (false);
		//
		Integer oldValue = 0;
		try
		{
			oldValue = (Integer)getValue ();			
		}
		catch(ClassCastException cce)
		{
			// Possible Invalid Cast exception if getValue() return new instance of Object.
		}
		int oldValueInt = oldValue == null ? 0 : oldValue.intValue ();
		int M_AttributeSetInstance_ID = oldValueInt;
		int M_Product_ID = 0;
		int M_ProductBOM_ID = 0;
		if (m_GridTab != null) {
			M_Product_ID = Env.getContextAsInt (Env.getCtx (), m_WindowNo, m_GridTab.getTabNo(), "M_Product_ID");
			M_ProductBOM_ID = Env.getContextAsInt (Env.getCtx (), m_WindowNo, m_GridTab.getTabNo(), "M_ProductBOM_ID");
		} else {
			M_Product_ID = Env.getContextAsInt (Env.getCtx (), m_WindowNo, "M_Product_ID");
			M_ProductBOM_ID = Env.getContextAsInt (Env.getCtx (), m_WindowNo, "M_ProductBOM_ID");
		}
		int M_Locator_ID = -1;

		log.config("M_Product_ID=" + M_Product_ID + "/" + M_ProductBOM_ID
			+ ",M_AttributeSetInstance_ID=" + M_AttributeSetInstance_ID
			+ ", AD_Column_ID=" + m_AD_Column_ID);
		
		//	M_Product.M_AttributeSetInstance_ID = 8418
		boolean productWindow = m_AD_Column_ID == 8418;		//	HARDCODED
		
		//	Exclude ability to enter ASI
		boolean exclude = false;
		
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

		// If the VPAttribute component is in a dialog, use the search
		if (m_searchOnly)
		{	
			// The component is an element in a CPanel, which is part of a JPanel
			// which is in a JLayeredPane which is in ...  the InfoProduct window
			Container me = ((Container) this).getParent();
			while (me != null)
			{
				if (me instanceof InfoProduct)
					break;
				me = me.getParent();
			}
			InfoPAttribute ia = new InfoPAttribute((CDialog) me);
			m_pAttributeWhere = ia.getWhereClause();
			String oldText = m_text.getText();
			m_text.setText(ia.getDisplay());
			// The text can be long.  Use the tooltip to help display the info.
			m_text.setToolTipText(m_text.getText());

			
			ActionEvent ae = new ActionEvent(m_text, 1001, "updated");
			//  TODO not the generally correct way to fire an event
			((InfoProduct) me).actionPerformed(ae);
		}
		else	
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
					productWindow, m_AD_Column_ID, m_WindowNo, isReadWrite());
				if (vad.isChanged())
				{
					m_text.setText(vad.getM_AttributeSetInstanceName());
					// The text can be long.  Use the tooltip to help display the info.
					m_text.setToolTipText(vad.getM_AttributeSetInstanceName());
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
	/**
	 * Set the old value of the field.  For use in future comparisons.
	 * The old value must be explicitly set though this call.
	 */
	public void set_oldValue() {
		if (getValue() != null) {
			try {
				this.m_oldValue = ((Integer) getValue());
			} 
			catch (ClassCastException e)
			{
				this.m_oldValue = null;
			}
		}
		else
			this.m_oldValue = null;
		if (m_text != null)
			this.m_oldText = m_text.getDisplay();
		else
			m_oldText = "";
		this.m_oldWhere = m_pAttributeWhere;
	}
	/**
	 * Get the old value of the field explicitly set in the past
	 * @return
	 */
	public Object get_oldValue() {
		return m_oldValue;
	}
	/**
	 * Has the field changed over time?
	 * @return true if the old value is different than the current.
	 */
	public boolean hasChanged() {
		// Both or either could be null
		
		// Don't think a test of Value is needed - the value is never set in this field internally
		//if(getValue() != null)
		//	if(m_oldValue != null)
		//		return !m_oldValue.equals(getValue());
		//	else
		//		return true;
		//else  // getValue() is null
		//	if(m_oldValue != null)
		//		return true;

		if(m_text != null)
			if(m_oldText != null)
				return !m_oldText.equals(m_text.getDisplay());
			else
				return true;
		else  // m_text is null
			if(m_oldText != null)
				return true;

		if(m_pAttributeWhere != null)
			if(m_oldWhere != null)
				return !m_oldWhere.equals(m_pAttributeWhere);
			else
				return true;
		else  // m_pAttributeWhere is null
			if(m_oldWhere != null)
				return true;

		return false;

	
	}

	@Override
	public void addValueChangeListener(ValueChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

}	//	VPAttribute
