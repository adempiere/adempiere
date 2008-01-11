/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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
 * @contributor Victor Perez , e-Evolution.SC FR [ 1757088 ]
 *****************************************************************************/
package org.compiere.grid;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;

import java.util.*;
import java.util.List;
//
import org.adempiere.plaf.AdempierePLAF;
import org.compiere.grid.ed.*;
import org.compiere.model.*;
import org.compiere.swing.*;
import org.compiere.util.*;
import org.jdesktop.swingx.JXCollapsiblePane;
import org.jdesktop.swingx.border.DropShadowBorder;

/**
 *  Single Row Panel.
 *  Called from GridController
 *  <pre>
 *	Structure
 *		this (CPanel - Group Bag Layout)
 *			group
 *			label - field
 *
 *  Spacing:
 *  -------------------
 *  Total Top = 10+2
 *  Total Right = 0+12
 *  Total Left = 0+12
 *  Total Bottom = 3+9
 *  -------------------
 *       2
 *  12 Label 0+5 Field 0
 *       3+2=5
 *  12 Label 0+5 Field 0
 *       3
 *
 *  </pre>
 *  @author 	Jorg Janke
 *  @version 	$Id: VPanel.java,v 1.3 2006/07/30 00:51:28 jjanke Exp $
 *  @contributor Victor Perez , e-Evolution.SC FR [ 1757088 ]
 */
public final class VPanel extends CTabbedPane
{
	
	/**
	 *	Constructor
	 */
	public VPanel(String Name)
	{
		//set up map used for label and field alignment
		labels.put(0, new ArrayList<CLabel>());
		labels.put(2, new ArrayList<CLabel>());
		
		fields.put(1, new ArrayList<Component>());
		fields.put(3, new ArrayList<Component>());
		
		fillers.put(0, new ArrayList<CLabel>());
		fillers.put(2, new ArrayList<CLabel>());
		
        //[ 1757088 ]	
		m_main.setName(Name);
		setupMainPanelLayout(m_main);
		m_main.setBorder(new CompoundBorder(m_main.getBorder(), BorderFactory.createEmptyBorder(10, 12, 0, 12)));
		m_tablist.put("main", m_main);
		this.setBorder(marginBorder);
		
		CPanel dummy = new CPanel();
		dummy.setLayout(new BorderLayout());
		dummy.add(m_main, BorderLayout.NORTH);
		dummy.setName(m_main.getName());
		this.add(dummy);
		
		//	Set initial values of constraint
		m_gbc.anchor = GridBagConstraints.NORTHWEST;
		m_gbc.gridy = 0;			//	line
		m_gbc.gridx = 0;
		m_gbc.gridheight = 1;
		m_gbc.gridwidth = 1;
		m_gbc.insets = m_zeroInset;
		m_gbc.fill = GridBagConstraints.HORIZONTAL;
		m_gbc.weightx = 0;
		m_gbc.weighty = 0;
		m_gbc.ipadx = 0;
		m_gbc.ipady = 0;
		
		
	}	//	VPanel
	
	/** GridBag Constraint      */
	private GridBagConstraints	m_gbc = new GridBagConstraints();

	/** Orientation             */
	private final boolean       m_leftToRight = Language.getLoginLanguage().isLeftToRight();
	/** Label Inset             */
	private final Insets 		m_labelInset =
		m_leftToRight ? new Insets(1,12,1,0) : new Insets(1,5,1,0);     // 	top,left,bottom,right
	private final Insets 		m_firstLabelInset =
			m_leftToRight ? new Insets(1,0,1,0) : new Insets(1,5,1,0);     // 	top,left,bottom,right	
	/** Field Inset             */
	private final Insets 		m_fieldInset =
		m_leftToRight ? new Insets(1,5,1,0)  : new Insets(1,12,1,0);	// 	top,left,bottom,right
	/** Zero Inset              */
	private final Insets 		m_zeroInset = new Insets(0,0,0,0);
	//
	private int 				m_line = 0;
	private boolean 			m_hGapAdded = false;					//	only once
	/** Previous Field Group Header     */
	private String              m_oldFieldGroup = null;
	/** Previous Field Group Type */
	private String              m_oldFieldGroupType = null;
	//[ 1757088 ]  	
	private java.util.Hashtable m_tablist = new java.util.Hashtable();
	private java.util.Hashtable m_tabincludelist = new java.util.Hashtable();
	private CPanel m_main = new CPanel(org.compiere.plaf.CompiereColor.getDefaultBackground());		
	private DropShadowBorder marginBorder = new DropShadowBorder();

	private HashMap<Integer,Collection<CLabel>> labels = new HashMap<Integer, Collection<CLabel>>();
	
	private HashMap<Integer,Collection<Component>> fields = new HashMap<Integer, Collection<Component>>();

	private HashMap<Integer,Collection<CLabel>> fillers = new HashMap<Integer, Collection<CLabel>>();

	private HashMap<JXCollapsiblePane,Component> collapsibleEndFiller = new HashMap<JXCollapsiblePane, Component>();

	/**	Logger	*/
	private static CLogger log = CLogger.getCLogger (VPanel.class);
	

	/**
	 * 	Set Field Mnemonic
	 *	@param mField field
	 */
	public void setMnemonic (GridField mField)
	{
		if (mField.isCreateMnemonic())
			return;
		String text = mField.getHeader();
		int pos = text.indexOf('&');
		if (pos != -1 && text.length() > pos)	//	We have a nemonic - creates Ctrl_Shift_
		{
			char mnemonic = text.toUpperCase().charAt(pos+1);
			if (mnemonic != ' ')
			{
				if (!m_mnemonics.contains(mnemonic))
				{
					mField.setMnemonic(mnemonic);
					m_mnemonics.add(mnemonic);
				}
				else
					log.warning(mField.getColumnName() 
						+ " - Conflict - Already exists: " + mnemonic + " (" + text + ")");
			}
		}
	}	//	setMnemonic
	
	/**
	 *	Add Field and Label to Panel
	 *  @param editor editor
	 *  @param mField field model
	 */
	public void addField (VEditor editor, GridField mField)
	{
		//[ 1757088 ]
		int AD_Tab_ID = mField.getIncluded_Tab_ID();
		if(AD_Tab_ID != 0 )
		{
			m_gbc.gridx = 0;
			m_gbc.gridy = m_line++;
			CollapsiblePanel m_tab = new CollapsiblePanel("");
			m_tabincludelist.put(AD_Tab_ID, m_tab);
			m_gbc.anchor = GridBagConstraints.NORTHWEST;
			m_gbc.gridx = 0;
			m_gbc.gridheight = 1;
			m_gbc.insets = new Insets(2,0,0,0);
			m_gbc.gridy = m_line++;
			m_gbc.gridwidth = 5;
			m_gbc.fill = GridBagConstraints.HORIZONTAL;
			m_gbc.weightx = 1;			  
			m_gbc.ipadx = 0;					  			  
			m_main.add(m_tab,m_gbc);
			return;
		}		  
		CLabel label = VEditorFactory.getLabel(mField); 
		if (label == null && editor == null)
			return;

		boolean sameLine = mField.isSameLine();
		//[ 1757088 ]              		//	sets top
		String fieldGroup = mField.getFieldGroup();
		String fieldGroupType = mField.getFieldGroupType();
		if (fieldGroup == "")
		{	
			fieldGroup = m_oldFieldGroup;
			fieldGroupType = m_oldFieldGroupType;
		}
		
		if (addGroup(fieldGroup, fieldGroupType)) //	sets top														
			sameLine = false;		
		else
		{
			fieldGroup = m_oldFieldGroup;
			fieldGroupType = m_oldFieldGroupType;
		}

		if (sameLine)    							//	Set line #
			m_gbc.gridy = m_line-1;
		else
			m_gbc.gridy = m_line++;

		//	*** The Label ***
		if (label != null)
		{
			m_gbc.gridwidth = 1;
			m_gbc.insets = m_labelInset;
			m_gbc.fill = GridBagConstraints.HORIZONTAL;	//	required for right justified
			//	Set column #
			if (m_leftToRight)
				m_gbc.gridx = sameLine ? 2 : 0;
			else
				m_gbc.gridx = sameLine | mField.isLongField() ? 3 : 1;
			if (m_gbc.gridx == 0)
				m_gbc.insets = m_firstLabelInset;
			//	Weight factor for Label
			m_gbc.weightx = 0;
			//
			if (mField.isCreateMnemonic())
				setMnemonic(label, mField.getMnemonic());
			//  Add Label
			//[ 1757088 ]
			//this.add(label, m_gbc);
			if (fieldGroupType.equals(X_AD_FieldGroup.FIELDGROUPTYPE_Tab))
			{
				CPanel m_tab = (CPanel)m_tablist.get(fieldGroup);	
				m_tab.add(label, m_gbc);	

			}			
			else if (fieldGroupType.equals(X_AD_FieldGroup.FIELDGROUPTYPE_Collapse))
			{
				CollapsiblePanel m_tab = (CollapsiblePanel) m_tablist.get(fieldGroup);
				if (m_gbc.gridx == 0)
					m_gbc.insets = m_firstLabelInset;
				m_tab.getCollapsiblePane().getContentPane().add(label, m_gbc);
				labels.get(m_gbc.gridx).add(label);
				
				moveCollapsibleEndFiller(m_tab.getCollapsiblePane());				
			}
			else // Label or null
			{
				m_main.add(label, m_gbc);
				labels.get(m_gbc.gridx).add(label);
			}
		}

		//	*** The Field ***
		if (editor != null)
		{
			Component field = (Component)editor;
			//	Default Width
			m_gbc.gridwidth = mField.isLongField() ? 3 : 1;
			m_gbc.insets = m_fieldInset;
			//	m_gbc.fill = GridBagConstraints.NONE;
			m_gbc.fill = GridBagConstraints.HORIZONTAL;
			//	Set column #
			if (m_leftToRight)
				m_gbc.gridx = sameLine ? 3 : 1;
			else
				m_gbc.gridx = sameLine ? 2 : 0;
			//	Weight factor for Fields
			m_gbc.weightx = 1;
			//	Add Field
			//[ 1757088 ]
			if (fieldGroupType.equals(X_AD_FieldGroup.FIELDGROUPTYPE_Tab))
			{
				CPanel m_tab = (CPanel)m_tablist.get(fieldGroup);	
				m_tab.add(field, m_gbc);				
			}			
			else if (fieldGroupType.equals(X_AD_FieldGroup.FIELDGROUPTYPE_Collapse))
			{
				CollapsiblePanel m_tab = (CollapsiblePanel) m_tablist.get(fieldGroup);
				m_tab.getCollapsiblePane().getContentPane().add(field, m_gbc);
				if (!mField.isLongField())
					fields.get(m_gbc.gridx).add(field);
				
				moveCollapsibleEndFiller(m_tab.getCollapsiblePane());
			}
			else // Label or null
			{								
				m_main.add(field, m_gbc);
				if (!mField.isLongField())
					fields.get(m_gbc.gridx).add(field);
			}	
			//	Link Label to Field
			if (label != null)
				label.setLabelFor(field);
			else if (mField.isCreateMnemonic())
				setMnemonic(editor, mField.getMnemonic());
		}
	}	//	addField

	/**
	 * Move the end spacer of the collapsible pane
	 * @param collapsiblePane
	 */
	private void moveCollapsibleEndFiller(JXCollapsiblePane collapsiblePane) {
		Component end = collapsibleEndFiller.get(collapsiblePane);
		if (end != null) {
			collapsiblePane.getContentPane().remove(end);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridheight = 1;
			gbc.gridwidth = 1;
			gbc.gridx = 0;
			gbc.gridy = m_gbc.gridy + 1;
			collapsiblePane.getContentPane().add(end, gbc);
		}		
	}

	/**
	 *	Add Group
	 *  @param fieldGroup field group
	 *  @param fieldGroupType 
	 *  @return true if group added
	 */
	private boolean addGroup(String fieldGroup, String fieldGroupType)
	{
		//	First time - add top
		if (m_oldFieldGroup == null)
		{
			m_oldFieldGroup = "";
			m_oldFieldGroupType = "";
		}

		if (fieldGroup == null || fieldGroup.length() == 0 || fieldGroup.equals(m_oldFieldGroup))
			return false;

		//[ 1757088 ]
		if (m_tablist.get(fieldGroup) != null)
		{
			return false;
		}

		//[ 1757088 ]
		if (fieldGroupType.equals(X_AD_FieldGroup.FIELDGROUPTYPE_Tab))
		{

			CPanel m_tab = new CPanel();
			m_tab.setBackground(AdempierePLAF.getFormBackground());
			setupTabPanelLayout(m_tab);
			m_tab.setName(fieldGroup);
			CPanel dummy = new CPanel();
			dummy.setLayout(new BorderLayout());
			dummy.add(m_tab, BorderLayout.NORTH);
			dummy.setName(m_tab.getName());
			dummy.setBorder(BorderFactory.createEmptyBorder(10,12,0,12));
			this.add(dummy);			  
			m_tablist.put(fieldGroup, m_tab);
		}
		else if (fieldGroupType.equals(X_AD_FieldGroup.FIELDGROUPTYPE_Collapse))
		{				  
			CollapsiblePanel collapsibleSection = new CollapsiblePanel(fieldGroup);
			JXCollapsiblePane m_tab = collapsibleSection.getCollapsiblePane();
			m_tab.setAnimated(false);
			m_tab.getContentPane().setBackground(AdempierePLAF.getFormBackground());
			setupCollapsiblePaneLayout(m_tab);
			CompoundBorder border = (CompoundBorder)m_tab.getBorder();
			m_tab.setBorder(new CompoundBorder(border.getOutsideBorder(), BorderFactory.createEmptyBorder(0, 0, 2, 0)));
			m_tab.setName(fieldGroup);
			m_gbc.anchor = GridBagConstraints.NORTHWEST;
			//m_gbc.gridy = 0;			//	line
			m_gbc.gridx = 0;
			m_gbc.gridheight = 1;
			if (m_oldFieldGroup.length() > 0 && m_oldFieldGroupType.equals(X_AD_FieldGroup.FIELDGROUPTYPE_Collapse))
				m_gbc.insets = new Insets(0,0,3,0);
			else
				m_gbc.insets = new Insets(3,0,3,0);
			m_gbc.gridy = m_line++;
			m_gbc.gridwidth = 4;
			m_gbc.fill = GridBagConstraints.HORIZONTAL;
			m_gbc.weightx = 1;			  
			m_main.add(collapsibleSection,m_gbc);
			m_tablist.put(fieldGroup, collapsibleSection);
		}									
		else // Label or null
		{
			CPanel group = new CPanel();
			group.setBorder(new VLine(fieldGroup));
			group.add(Box.createVerticalStrut(VLine.SPACE));
			m_gbc.gridx = 0;
			m_gbc.gridy = m_line++;
			m_gbc.gridwidth = 4;
			m_main.add(group, m_gbc);
			//	reset
			m_gbc.gridwidth = 1;
		}
		m_oldFieldGroup = fieldGroup;
		m_oldFieldGroupType = fieldGroupType;
		return true;
	}	//	addGroup

	/**
	 * Set up the gridbaglayout for additonal field group tab
	 * @param m_tab
	 */
	private void setupMainPanelLayout(CPanel m_tab) {
		m_tab.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridy = 0;			//	line
		gbc.gridx = 0;
		gbc.gridheight = 0;
		gbc.gridwidth = 1;
		gbc.insets = m_firstLabelInset;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.ipadx = 0;
		gbc.ipady = 0;
		CLabel label = new CLabel("");
		fillers.get(0).add(label);
		m_tab.add(label, gbc);
		
		gbc.weightx = 1;
		gbc.gridx = 1;
		gbc.insets = m_fieldInset;
		label = new CLabel("");
		fields.get(1).add(label);
		m_tab.add(label, gbc);
		
		gbc.weightx = 0;
		gbc.gridx = 2;
		gbc.insets = m_labelInset;
		label = new CLabel("");
		fillers.get(2).add(label);
		m_tab.add(label, gbc);
		
		gbc.weightx = 1;
		gbc.gridx = 3;
		gbc.insets = m_fieldInset;
		label = new CLabel("");
		fields.get(3).add(label);
		m_tab.add(label, gbc);
		
		//right gap
		gbc.weightx = 1;
		gbc.gridx = 4;
		m_tab.add(Box.createHorizontalGlue(), gbc);
		
	}
	
	/**
	 * Set up the gridbaglayout for additonal field group tab
	 * @param m_tab
	 */
	private void setupTabPanelLayout(CPanel m_tab) {
		m_tab.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridy = 0;			//	line
		gbc.gridx = 0;
		gbc.gridheight = 0;
		gbc.gridwidth = 1;
		gbc.insets = m_firstLabelInset;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.ipadx = 0;
		gbc.ipady = 0;
		CLabel label = new CLabel("");
		m_tab.add(label, gbc);
		
		gbc.weightx = 1;
		gbc.gridx = 1;
		gbc.insets = m_fieldInset;
		label = new CLabel("");
		m_tab.add(label, gbc);
		
		gbc.weightx = 0;
		gbc.gridx = 2;
		gbc.insets = m_labelInset;
		label = new CLabel("");
		m_tab.add(label, gbc);
		
		gbc.weightx = 1;
		gbc.gridx = 3;
		gbc.insets = m_fieldInset;
		fields.get(3).add(label);
		m_tab.add(label, gbc);
		
		//right gap
		gbc.weightx = 1;
		gbc.gridx = 4;
		m_tab.add(Box.createHorizontalGlue(), gbc);
		
	}

	/**
	 * Set up the grid bag layout of collapsible pane
	 * @param m_tab
	 */
	private void setupCollapsiblePaneLayout(JXCollapsiblePane m_tab) {
		m_tab.getContentPane().setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridy = 0;			//	line
		gbc.gridx = 0;
		gbc.gridheight = 0;
		gbc.gridwidth = 1;
		gbc.insets = m_firstLabelInset;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.ipadx = 0;
		gbc.ipady = 0;
		CLabel label = new CLabel("");
		fillers.get(0).add(label);
		m_tab.getContentPane().add(label, gbc);
		
		gbc.weightx = 1;
		gbc.gridx = 1;
		gbc.insets = m_fieldInset;
		label = new CLabel("");
		fields.get(1).add(label);
		m_tab.getContentPane().add(label, gbc);
		
		gbc.weightx = 0;
		gbc.gridx = 2;
		gbc.insets = m_labelInset;
		label = new CLabel("");
		fillers.get(2).add(label);
		m_tab.getContentPane().add(label, gbc);
		
		gbc.weightx = 1;
		gbc.gridx = 3;
		gbc.insets = m_fieldInset;
		label = new CLabel("");
		fields.get(3).add(label);
		m_tab.getContentPane().add(label, gbc);
		
		//right gap
		/*
		gbc.weightx = 1;
		gbc.gridx = 4;
		m_tab.getContentPane().add(Box.createHorizontalGlue(), gbc);*/
		
		Component c = Box.createVerticalStrut(3);
		gbc.gridheight = 1;
		gbc.gridy = GridBagConstraints.RELATIVE;
		gbc.gridx = 0;
		gbc.weightx = 0;
		gbc.insets = m_zeroInset;		
		m_tab.getContentPane().add(c, gbc);
		collapsibleEndFiller.put(m_tab, c);
	}

	/**
	 *	Add End (9) of Form
	 */
	public void addEnd()
	{
		m_gbc.gridx = 0;
		m_gbc.gridy = m_line;
		m_gbc.gridwidth = 1;
		m_gbc.insets = m_zeroInset;
		m_gbc.fill = GridBagConstraints.HORIZONTAL;
		m_gbc.weightx = 0;
		//
        //[ 1757088 ]
		m_main.add(Box.createVerticalStrut(9), m_gbc);
	}	//	addEnd

	/**
	 * 	Set Mnemonic for Label CTRL_SHIFT_x
	 *	@param label label
	 *	@param predefinedMnemonic predefined Mnemonic
	 */
	private void setMnemonic (CLabel label, char predefinedMnemonic)
	{
		String text = label.getText();
		int pos = text.indexOf('&');
		if (pos != -1 && predefinedMnemonic != 0)
		{
			text = text.substring(0, pos) + text.substring(pos+1);
			label.setText(text);
			label.setSavedMnemonic(predefinedMnemonic);
			m_fields.add(label);
			log.finest(predefinedMnemonic + " - " + label.getName());
		}
		else
		{
			char mnemonic = getMnemonic(text, label);
			if (mnemonic != 0)
				label.setSavedMnemonic(mnemonic);
		//	label.setDisplayedMnemonic(mnemonic);
		}
	}	//	setMnemonic
	
	/**
	 * 	Set Mnemonic for Check Box or Button
	 *	@param editor check box or button - other ignored
	 *	@param predefinedMnemonic predefined Mnemonic
	 */
	private void setMnemonic (VEditor editor, char predefinedMnemonic)
	{
		if (editor instanceof VCheckBox)
		{
			VCheckBox cb = (VCheckBox)editor;
			String text = cb.getText();
			int pos = text.indexOf('&');
			if (pos != -1 && predefinedMnemonic != 0)
			{
				text = text.substring(0, pos) + text.substring(pos+1);
				cb.setText(text);
				cb.setSavedMnemonic(predefinedMnemonic);
				m_fields.add(cb);
				log.finest(predefinedMnemonic + " - " + cb.getName());
			}
			else
			{
				char mnemonic = getMnemonic(text, cb);
				if (mnemonic != 0)
					cb.setSavedMnemonic(mnemonic);
			//	cb.setMnemonic(mnemonic);
			}
		}
		//	Button
		else if (editor instanceof VButton)
		{
			VButton b = (VButton)editor;
			String text = b.getText();
			int pos = text.indexOf('&');
			if (pos != -1 && predefinedMnemonic != 0)
			{
				text = text.substring(0, pos) + text.substring(pos+1);
				b.setText(text);
				b.setSavedMnemonic(predefinedMnemonic);
				m_fields.add(b);
				log.finest(predefinedMnemonic + " - " + b.getName());
			}
			else if (b.getColumnName().equals("DocAction"))
			{
				b.getInputMap(WHEN_IN_FOCUSED_WINDOW)
					.put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, Event.SHIFT_MASK, false), "pressed");
				b.getInputMap(WHEN_IN_FOCUSED_WINDOW)
					.put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, Event.SHIFT_MASK, true), "released");
			//	Util.printActionInputMap(b);
			}
			else if (b.getColumnName().equals("Posted"))
			{
				b.getInputMap(WHEN_IN_FOCUSED_WINDOW)
					.put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, Event.SHIFT_MASK, false), "pressed");
				b.getInputMap(WHEN_IN_FOCUSED_WINDOW)
					.put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, Event.SHIFT_MASK, true), "released");
			//	Util.printActionInputMap(b);
			}
			else
			{
				char mnemonic = getMnemonic(text, b);
				if (mnemonic != 0)
					b.setSavedMnemonic(mnemonic);
			}
		}
	}	//	setMnemonic

	/**
	 * 	Get Mnemonic from text
	 *	@param text text
	 *	@param source component
	 *	@return Mnemonic or 0 if not unique
	 */
	private char getMnemonic (String text, Component source)
	{
		if (text == null || text.length() == 0)
			return 0;
		String oText = text;
		text = text.trim().toUpperCase();
		char mnemonic = text.charAt(0);
		if (m_mnemonics.contains(mnemonic))
		{
			mnemonic = 0;
			//	Beginning new word
			int index = text.indexOf(' ');
			while (index != -1 && text.length() > index)
			{
				char c = text.charAt(index+1);
				if (Character.isLetterOrDigit(c) && !m_mnemonics.contains(c))
				{
					mnemonic = c;
					break;
				}
				index = text.indexOf(' ', index+1);
			}
			//	Any character
			if (mnemonic == 0)
			{
				for (int i = 1; i < text.length(); i++)
				{
					char c = text.charAt(i);
					if (Character.isLetterOrDigit(c) && !m_mnemonics.contains(c))
					{
						mnemonic = c;
						break;
					}
				}
			}
			//	Nothing found
			if (mnemonic == 0)
			{
				log.finest("None for: " + oText);
				return 0;	//	 if first char would be returned, the first occurance is invalid.
			}
		}
		m_mnemonics.add(mnemonic);
		m_fields.add(source);
		log.finest(mnemonic + " - " + source.getName());
		return mnemonic;
	}	//	getMnemonic
	
	/** Used Mnemonics		*/
	private ArrayList<Character> m_mnemonics = new ArrayList<Character>(30);
	/** Mnemonic Fields		*/
	private ArrayList<Component> m_fields = new ArrayList<Component>(30);
	
	/**
	 * 	Set Window level Mnemonics
	 *	@param set true if set otherwise unregiser
	 */
	public void setMnemonics (boolean set)
	{
		int size = m_fields.size();
		for (int i = 0; i < size; i++)
		{
			Component c = m_fields.get(i);
			if (c instanceof CLabel)
			{
				CLabel l = (CLabel)c;
				if (set)
					l.setDisplayedMnemonic(l.getSavedMnemonic());
				else
					l.setDisplayedMnemonic(0);
			}
			else if (c instanceof VCheckBox)
			{
				VCheckBox cb = (VCheckBox)c;
				if (set)
					cb.setMnemonic(cb.getSavedMnemonic());
				else
					cb.setMnemonic(0);
			}
			else if (c instanceof VButton)
			{
				VButton b = (VButton)c;
				if (set)
					b.setMnemonic(b.getSavedMnemonic());
				else
					b.setMnemonic(0);
			}
		}
	}	//	setMnemonics
	
	/**************************************************************************
	 *  Set Background to AD_Color_ID (nop)
	 *  @param AD_Color_ID Color
	 */
	public void setBackground (int AD_Color_ID)
	{
	}   //  setBackground

	//[ 1757088 ]
	public CollapsiblePanel getIncludedSection(int AD_Tab_ID)
	{	
		return (CollapsiblePanel)m_tabincludelist.get(AD_Tab_ID);
	}  	
	
	private void findChildComponents(CPanel container, List list) 
	{
		Component[] comp = container.getComponents();
		for (int c = 0; c < comp.length; c++)
		{
			list.add(comp[c]);
			if ( comp [c] instanceof CollapsiblePanel)
			{
				CollapsiblePanel collapsiblePanel = (CollapsiblePanel)comp [c];     

				Component[] nestedComps = collapsiblePanel.getCollapsiblePane()
					.getContentPane().getComponents();

				for (int y = 0; y < nestedComps.length; y++)
				{

					if (  nestedComps [y] instanceof CPanel)
					{                        		  
						CPanel nestedPanel = (CPanel)nestedComps[y]; 
						Component[] nestedPanelComps  = nestedPanel.getComponents();

						for (int x = 0; x < nestedPanelComps.length; x++)
						{
							list.add(nestedPanelComps[x]);
						}
					}
					else
					{
						list.add(nestedComps[y]);
					}
				} 
			} else if (comp[c] instanceof CPanel) 
			{

				findChildComponents((CPanel)comp[c], list);
			}
		}
	}
	
	//[ 1757088 ]
	public Component[] getComponentsRecursive()
	{
		java.util.ArrayList list = new java.util.ArrayList();       

		for (int i = 0; i < this.getTabCount(); i++)
		{
			list.add(this.getComponentAt(i));
			if (this.getComponentAt(i) instanceof CPanel) 
			{    
				CPanel panel = (CPanel)this.getComponentAt(i);                    
				findChildComponents(panel, list);
			}
		}       

		Component[] result = new Component[list.size ()];
		list.toArray (result);             
		return result;
	}

	@Override
	public void doLayout() {
		//align labels
		int w = 0;
		Collection<CLabel> clabels = labels.get(0);
		for (CLabel l : clabels) {
			if (l.isVisible()) {
				int lw = l.getPreferredSize().width;
				if (lw > w) w = lw;
			}
		}
		
		for (CLabel l : clabels) {
			if (l.isVisible()) {
				Dimension d = l.getMinimumSize();
				if (d.width != w) {
					d.width = w;
					l.setMinimumSize(d);
				}
				
			}
		}
		
		clabels = fillers.get(0);
		for (CLabel l : clabels) {
			if (l.isVisible()) {
				Dimension d = l.getMinimumSize();
				if (d.width != w) {
					d.width = w;
					l.setMinimumSize(d);
				}
			}
		}
		
		w = 0;
		clabels = labels.get(2);
		for (CLabel l : clabels) {
			if (l.isVisible()) {
				int lw = l.getPreferredSize().width;
				if (lw > w) w = lw;
			}
		}
		
		for (CLabel l : clabels) {
			if (l.isVisible()) {
				Dimension d = l.getMinimumSize();
				if (d.width != w) {
					d.width = w;
					l.setMinimumSize(d);
				}
			}
		}
		
		clabels = fillers.get(2);
		for (CLabel l : clabels) {
			if (l.isVisible()) {
				Dimension d = l.getMinimumSize();
				if (d.width != w) {
					d.width = w;
					l.setMinimumSize(d);
				}
			}
		}
		
		//align fields
		w = 0;
		Collection<Component> collection = fields.get(1);
		for (Component c : collection) {
			if (c.isVisible()) {
				int cw = c.getPreferredSize().width;
				if ( cw > w )
					w = cw;
			}
		}
		
		for (Component c : collection) {
			if (c.isVisible()) {
				Dimension d = c.getMinimumSize();
				if ( d.width != w ) {
					d.width = w;
					c.setMinimumSize(d);
				}
				d = c.getPreferredSize();
				if (d.width != w) {
					d.width = w;
					c.setPreferredSize(d);
				}
			}
		}
		
		w = 0;
		collection = fields.get(3);
		for (Component c : collection) {
			if (c.isVisible()) {
				int cw = c.getPreferredSize().width;
				if ( cw > w )
					w = cw;
			}
		}
		
		for (Component c : collection) {
			if (c.isVisible()) {
				Dimension d = c.getMinimumSize();
				if ( d.width != w ) {
					d.width = w;
					c.setMinimumSize(d);
				}
				d = c.getPreferredSize();
				if (d.width != w) {
					d.width = w;
					c.setPreferredSize(d);
				}
			}
		}
		
		super.doLayout();
	}

	
}	//	VPanel