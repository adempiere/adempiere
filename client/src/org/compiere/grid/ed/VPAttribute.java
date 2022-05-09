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
import org.compiere.model.MColumn;
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
		 *  @param attribute attribute
		 */
		VPAttribute_mouseAdapter(VPAttribute attribute)
		{
			this.attribute = attribute;
		}	//	VPAttribute_mouseAdapter

		private VPAttribute attribute;

		/**
		 *	Mouse Listener
		 *  @param e event
		 */
		public void mouseClicked(MouseEvent e)
		{
			//	Double Click
			if (e.getClickCount() > 1)
				this.attribute.actionPerformed(new ActionEvent(e.getSource(), e.getID(), "Mouse"));
			//	popup menu
			if (SwingUtilities.isRightMouseButton(e))
				this.attribute.popupMenu.show((Component)e.getSource(), e.getX(), e.getY());
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
	 *  @param isUpdated isUpdated
	 * 	@param WindowNo WindowNo
	 * 	@param lookup Model Product Attribute
	 */
	public VPAttribute (boolean mandatory,
						boolean isReadOnly,
						boolean isUpdated,
						int WindowNo,
						MPAttributeLookup lookup,
						boolean searchOnly)
	{
		this(null, mandatory, isReadOnly, isUpdated, WindowNo, lookup, searchOnly);
	}

	/**
	 *	Create Product Attribute Set Instance Editor.
	 *  @param gridTab
	 *  @param mandatory mandatory
	 *  @param isReadOnly read only
	 *  @param isUpdatable updatable
	 * 	@param windowNo WindowNo
	 * 	@param lookup Model Product Attribute
	 *  @param searchOnly True if only used to search instances
	 */
	public VPAttribute (GridTab gridTab,
						boolean mandatory,
						boolean isReadOnly,
						boolean isUpdatable,
						int windowNo,
						MPAttributeLookup lookup,
						boolean searchOnly)
	{
		super();
		super.setName(columnName);
		this.text.setName("VPAttribute Text - " + columnName);
		this.button.setName("VPAttribute Button - " + columnName);
		this.value = 0;
		this.gridTabAttribute = gridTab; // added for processCallout
		this.windowNo = windowNo;
		this.attributeLookup = lookup;
		this.partnerId = Env.getContextAsInt(Env.getCtx(), windowNo, "C_BPartner_ID");
		this.isSearchOnly = searchOnly;
		LookAndFeel.installBorder(this, "TextField.border");
		this.setLayout(new BorderLayout());
		//  Size
		this.setPreferredSize(this.text.getPreferredSize());
		int height = this.text.getPreferredSize().height;

		//	***	Text	***
		this.text.setEditable(false);
		this.text.setFocusable(false);
		this.text.setBorder(null);
		this.text.setHorizontalAlignment(JTextField.LEADING);
		//	Background
		setMandatory(mandatory);
		this.add(this.text, BorderLayout.CENTER);

		//	***	Button	***
		this.button.setIcon(Env.getImageIcon("PAttribute10.gif"));
		this.button.setMargin(new Insets(0, 0, 0, 0));
		this.button.setPreferredSize(new Dimension(height, height));
		this.button.addActionListener(this);
		this.button.setFocusable(true);
		this.add(this.button, BorderLayout.EAST);

		//	Prefereed Size
		this.setPreferredSize(this.getPreferredSize());		//	causes r/o to be the same length
		//	ReadWrite
		if (isReadOnly || !isUpdatable)
			setReadWrite(false);
		else
			setReadWrite(true);

		//	Popup
		this.text.addMouseListener(new VPAttribute_mouseAdapter(this));
		menuEditor = new CMenuItem(Msg.getMsg(Env.getCtx(), "PAttribute"), Env.getImageIcon("Zoom16.gif"));
		menuEditor.addActionListener(this);
		popupMenu.add(menuEditor);

		set_oldValue();
	}	//	VPAttribute

	/**	Data Value				*/
	private Object 				value = new Object();
	/** Attribute Where Clause  */
	private String 				attributeWhere = null;
	/** Column Name - fixed		*/
	private String 				columnName = "M_AttributeSetInstance_ID";
	/** The Attribute Instance	*/
	private MPAttributeLookup 	attributeLookup;

	/** The Text Field          */
	private CTextField 			text = new CTextField();
	/** The Button              */
	private CButton 			button = new CButton();

	JPopupMenu          		popupMenu = new JPopupMenu();
	private CMenuItem 			menuEditor;

	private boolean 			isReadWrite;
	private boolean 			isMandatory;
	private int 				windowNo;
	private int 				partnerId;
	private boolean 			isSearchOnly;
	/** The Grid Tab * */
	private GridTab 			gridTabAttribute; // added for processCallout
	/** The Grid Field * */
	private GridField 			gridFieldAttribute; // added for processCallout
	
	/**	Calling Window Info				*/
	private int 				columnId = 0;
	/** record the value for comparison at a point in the future */
	private Integer 			oldValue = 0;
	private String 				oldText = "";
	private String 				oldWhere = "";
	/**	No Instance Key					*/
	private static Integer		NO_INSTANCE = Integer.valueOf(0);
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VPAttribute.class);
		

	/**
	 * 	Dispose resources
	 */
	public void dispose()
	{
		this.text = null;
		this.button = null;
		this.attributeLookup.dispose();
		this.attributeLookup = null;
		this.gridFieldAttribute = null;
		this.gridTabAttribute = null;
	}	//	dispose

	/**
	 * 	Set Mandatory
	 * 	@param mandatory mandatory
	 */
	public void setMandatory (boolean mandatory)
	{
		this.isMandatory = mandatory;
		this.button.setMandatory(mandatory);
		setBackground (false);
	}	//	setMandatory

	/**
	 * 	Get Mandatory
	 *  @return mandatory
	 */
	public boolean isMandatory()
	{
		return this.isMandatory;
	}	//	isMandatory

	/**
	 * 	Set ReadWrite
	 * 	@param rw read rwite
	 */
	public void setReadWrite (boolean rw)
	{
		this.isReadWrite = rw;
		this.button.setReadWrite(rw);
		setBackground (false);
	}	//	setReadWrite

	/**
	 * 	Is Read Write
	 * 	@return read write
	 */
	public boolean isReadWrite()
	{
		return this.isReadWrite;
	}	//	isReadWrite

	/**
	 * 	Set Foreground
	 * 	@param color color
	 */
	public void setForeground (Color color)
	{
		this.text.setForeground(color);
	}	//	SetForeground

	/**
	 * 	Set Background
	 * 	@param error Error
	 */
	public void setBackground (boolean error)
	{
		if (error)
			setBackground(AdempierePLAF.getFieldBackground_Error());
		else if (!this.isReadWrite)
			setBackground(AdempierePLAF.getFieldBackground_Inactive());
		else if (this.isMandatory)
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
		this.text.setBackground(color);
	}	//	setBackground

	
	/**************************************************************************
	 * 	Set/lookup Value
	 * 	@param value value
	 */
	public void setValue(Object value)
	{
		if (value == null || NO_INSTANCE.equals(value))
		{
			this.text.setText("");
			this.value = value;
			this.attributeWhere = "";
			return;
		}
		
		//	The same
		if (value.equals(this.value))
			return;
		//	new value
		log.fine("Value=" + value);
		this.value = value;
		this.text.setText(this.attributeLookup.getDisplay(value));	//	loads value
		// The text can be long.  Use the tooltip to help display the info.
		this.text.setToolTipText(this.text.getText());
		this.attributeWhere = "EXISTS (SELECT * FROM M_Storage s "
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
		if (this.value != null || NO_INSTANCE.equals(this.value)) {
			try {
				temp = (Integer) this.value;
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
		return this.attributeWhere;
	}	//	getAttributeWhere()

	/**
	 * 	Get Display Value
	 *	@return info
	 */
	public String getDisplay()
	{
		return this.text.getText();
	}	//	getDisplay

	
	/**************************************************************************
	 * 	Set Field
	 * 	@param mField MField
	 */
	public void setField(GridField mField)
	{
		//	To determine behaviour
		this.columnId = mField.getAD_Column_ID();
		this.gridFieldAttribute = mField;
		
		if (this.gridFieldAttribute != null)
			RecordInfo.addMenu(this, popupMenu);
	}	//	setField
	
	@Override
	public GridField getField() {
		return this.gridFieldAttribute;
	}

	/**
	 *  Action Listener Interface
	 *  @param listener listener
	 */
	public void addActionListener(ActionListener listener)
	{
		this.text.addActionListener(listener);
	}   //  addActionListener

	/**
	 * 	Action Listener - start dialog
	 * 	@param e Event
	 */
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals(RecordInfo.CHANGE_LOG_COMMAND))
		{
			RecordInfo.start(this.gridFieldAttribute);
			return;
		}
		
		if (!this.button.isEnabled ())
			return;
		this.button.setEnabled (false);
		//
		Integer oldValueEvent = 0;
		try
		{
			oldValueEvent = (Integer)getValue ();
		}
		catch(ClassCastException cce)
		{
			// Possible Invalid Cast exception if getValue() return new instance of Object.
		}
		int oldValueInt = oldValueEvent == null ? 0 : oldValueEvent.intValue ();
		int attributeSetInstanceId = oldValueInt;
		int productId= 0;
		int productBOMId = 0;
		if (this.gridTabAttribute != null) {
			productId = Env.getContextAsInt (Env.getCtx (), this.windowNo, this.gridTabAttribute.getTabNo(), "M_Product_ID");
			productBOMId = Env.getContextAsInt (Env.getCtx (), this.windowNo, this.gridTabAttribute.getTabNo(), "M_ProductBOM_ID");
		} else {
			productId = Env.getContextAsInt (Env.getCtx (), this.windowNo, "M_Product_ID");
			productBOMId = Env.getContextAsInt (Env.getCtx (), this.windowNo, "M_ProductBOM_ID");
		}
		int locatorId = -1;

		log.config("M_Product_ID=" + productId + "/" + productBOMId
			+ ",M_AttributeSetInstance_ID=" + attributeSetInstanceId
			+ ", AD_Column_ID=" + this.columnId);
		
		//	M_Product.M_AttributeSetInstance_ID = 8418
		boolean productWindow = this.columnId == MColumn.getColumn_ID(MProduct.Table_Name, MProduct.COLUMNNAME_M_AttributeSetInstance_ID);
		
		//	Exclude ability to enter ASI
		boolean exclude = false;
		
		if (productId != 0)
		{
			MProduct product = MProduct.get(Env.getCtx(), productId);
			int attributeSetId = product.getM_AttributeSet_ID();
			if (attributeSetId != 0)
			{
				int tableId = MColumn.getTable_ID(Env.getCtx(), this.columnId, null);
				MAttributeSet mas = MAttributeSet.get(Env.getCtx(), attributeSetId);
				exclude = mas.excludeEntry(tableId, Env.isSOTrx(Env.getCtx(), this.windowNo));
			}
		}
		
		boolean changed = false;
		if (productBOMId != 0)	//	Use BOM Component
			productId = productBOMId;

		// If the VPAttribute component is in a dialog, use the search
		if (this.isSearchOnly)
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
			this.attributeWhere = ia.getWhereClause();
			String oldText = this.text.getText();
			this.text.setText(ia.getDisplay());
			// The text can be long.  Use the tooltip to help display the info.
			this.text.setToolTipText(this.text.getText());

			
			ActionEvent ae = new ActionEvent(this.text, 1001, "updated");
			//  TODO not the generally correct way to fire an event
			((InfoProduct) me).actionPerformed(ae);
		}
		else	
			if (!productWindow && (productId == 0 || exclude))
			{
				changed = true;
				this.text.setText(null);
				attributeSetInstanceId = 0;
			}
			else
			{
				VPAttributeDialog vad = new VPAttributeDialog (Env.getFrame (this), 
					attributeSetInstanceId, productId, this.partnerId,
					productWindow,this.columnId, this.windowNo, isReadWrite());
				if (vad.isChanged())
				{
					this.text.setText(vad.getM_AttributeSetInstanceName());
					// The text can be long.  Use the tooltip to help display the info.
					this.text.setToolTipText(vad.getM_AttributeSetInstanceName());
					attributeSetInstanceId = vad.getM_AttributeSetInstance_ID();
					if (!productWindow && vad.getM_Locator_ID() > 0)
					{
						locatorId = vad.getM_Locator_ID();
					}
					changed = true;
				}
			}
		
		//	Set Value
		if (changed)
		{
			log.finest("Changed M_AttributeSetInstance_ID=" + attributeSetInstanceId);
			this.value = new Object();				//	force re-query display
			if (attributeSetInstanceId == 0)
				setValue(null);
			else {
				setValue(Integer.valueOf(attributeSetInstanceId));
			}
			// Change Locator
			if (this.gridTabAttribute != null && locatorId > 0)
			{
				log.finest("Change M_Locator_ID="+locatorId);
				this.gridTabAttribute.setValue("M_Locator_ID", locatorId);
			}
			//
			try
			{
				String columnName = "M_AttributeSetInstance_ID";
		 	 	if (this.gridFieldAttribute != null)
		 	 	{
		 	 		columnName = this.gridFieldAttribute.getColumnName();
		 	 	}
		 	 	fireVetoableChange(columnName, new Object(), getValue());
			}
			catch (PropertyVetoException pve)
			{
				log.log(Level.SEVERE, "", pve);
			}
			if (attributeSetInstanceId == oldValueInt && this.gridTabAttribute != null && this.gridFieldAttribute != null)
			{
				//  force Change - user does not realize that embedded object is already saved.
				this.gridTabAttribute.processFieldChange(this.gridFieldAttribute);
			}
		}	//	change
		this.button.setEnabled(true);
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
				this.oldValue = ((Integer) getValue());
			} 
			catch (ClassCastException e)
			{
				this.oldValue = null;
			}
		}
		else
			this.oldValue = null;
		if (this.text != null)
			this.oldText = this.text.getDisplay();
		else
			this.oldText = "";
		this.oldWhere = this.attributeWhere;
	}
	/**
	 * Get the old value of the field explicitly set in the past
	 * @return
	 */
	public Object get_oldValue() {
		return this.oldValue;
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

		if(this.text != null)
			if(this.oldText != null)
				return !this.oldText.equals(this.text.getDisplay());
			else
				return true;
		else  // m_text is null
			if(this.oldText != null)
				return true;

		if(this.attributeWhere != null)
			if(this.oldWhere != null)
				return !this.oldWhere.equals(this.attributeWhere);
			else
				return true;
		else  // m_pAttributeWhere is null
			if(this.oldWhere != null)
				return true;

		return false;

	
	}

	@Override
	public void addValueChangeListener(ValueChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

}	//	VPAttribute
