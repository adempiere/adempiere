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
 * @contributor Victor Perez , e-Evolution.SC FR [ 1757088 ]
 *****************************************************************************/
package org.compiere.grid;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Event;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import net.miginfocom.layout.BoundSize;
import net.miginfocom.layout.ComponentWrapper;
import net.miginfocom.layout.ConstraintParser;
import net.miginfocom.layout.LayoutCallback;
import net.miginfocom.layout.UnitValue;
import net.miginfocom.swing.MigLayout;
import net.miginfocom.swing.SwingComponentWrapper;
import net.miginfocom.swing.SwingContainerWrapper;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.APanel;
import org.compiere.grid.ed.VButton;
import org.compiere.grid.ed.VCheckBox;
import org.compiere.grid.ed.VEditor;
import org.compiere.grid.ed.VEditorFactory;
import org.compiere.grid.ed.VLine;
import org.compiere.model.GridField;
import org.compiere.model.X_AD_FieldGroup;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.swing.CTabbedPane;
import org.compiere.swing.CollapsiblePanel;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Language;
import org.compiere.util.Util;
import org.jdesktop.swingx.JXCollapsiblePane;
import org.jdesktop.swingx.border.DropShadowBorder;

import com.lowagie.text.Font;

/**
 *  Single Row Panel.
 *  Called from GridController
 *  
 *  Uses MigLayout
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: VPanel.java,v 1.3 2006/07/30 00:51:28 jjanke Exp $
 *  @contributor Victor Perez , e-Evolution.SC FR [ 1757088 ]
 *               Carlos Ruiz - globalqss / Fix bug 2307133 - Swing client hiding fields incorrectly
 */
public final class VPanel extends CTabbedPane
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8227080366665213627L;

	private int m_WindowNo;
	// show layout grid
	private boolean debug = false;
	private String defaultLayoutConstraints;
	private int labelMinWidth = 0;
	private int fieldMinWidth = 0;
	
	public VPanel(String Name) 
	{
		this(Name, 0);
	}
	
	/**
	 * 
	 * @param Name
	 * @param WindowNo
	 */
	public VPanel(String Name, int WindowNo)
	{
		m_WindowNo = WindowNo;
		
		defaultLayoutConstraints =  "hidemode 3";   // hidden fields take no part in grid
		defaultLayoutConstraints += debug ? ", debug" : "";
		
		//[ 1757088 ]	
		m_main.setName(Name);
		String constraints = defaultLayoutConstraints;
		MigLayout mainLayout = new MigLayout(constraints);
		mainLayout.addLayoutCallback(callback);
		m_main.setLayout(mainLayout);
		
		m_tablist.put("main", m_main);
		this.setBorder(marginBorder);
		
		CPanel dummy = new CPanel();
		dummy.setLayout(new BorderLayout());
		dummy.add(m_main, BorderLayout.NORTH);
		dummy.setName(m_main.getName());
		this.add(dummy);		
		
	}	//	VPanel
	
	/** Orientation             */
	private final boolean       m_leftToRight = Language.getLoginLanguage().isLeftToRight();
	/** Previous Field Group Header     */
	private String              m_oldFieldGroup = null;
	/** Previous Field Group Type */
	private String              m_oldFieldGroupType = null;
	//[ 1757088 ]  	
	private java.util.Hashtable<String, JPanel> m_tablist = new java.util.Hashtable<String, JPanel>();
	private java.util.Hashtable<Integer, CollapsiblePanel> m_tabincludelist = new java.util.Hashtable<Integer, CollapsiblePanel>();
	private CPanel m_main = new CPanel(org.compiere.plaf.CompiereColor.getDefaultBackground());		
	private DropShadowBorder marginBorder = new DropShadowBorder();


	/**
	 * Use layout callback to implement size groups across panels
	 * and to shrink hidden components vertically.
	 */
	private LayoutCallback callback = new LayoutCallback() {

		// Set the min and preferred sizes to match the largest component
		public BoundSize[] getSize(ComponentWrapper comp)
		{
			if (comp.getComponent() instanceof CLabel) {
				Component c = (Component) comp.getComponent();
				int w = labelMinWidth;
				int h = c.getPreferredSize().height;
				BoundSize width = ConstraintParser.parseBoundSize(w + ":" + w, false, true);  // min:pref
				BoundSize height = ConstraintParser.parseBoundSize(h + ":" + h, false, false);
				
				return new BoundSize[] {width, height};
			}
			else
				{
				Component c = (Component) comp.getComponent();
				int w = fieldMinWidth;
				int h = c.getPreferredSize().height;
				BoundSize width = ConstraintParser.parseBoundSize(w + ":" + w, false, true);
				BoundSize height = ConstraintParser.parseBoundSize(h + ":" + h, false, false);
				
				return new BoundSize[] {width, height};
			}
			
		}//

		public void correctBounds(ComponentWrapper comp)
		{
		}
	};

	private VEditor prevEditor = null;
	private GridField prevField = null;
	private boolean wrap = false;

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
		if (pos != -1 && text.length() > pos)	//	We have a mnemonic - creates Ctrl_Shift_
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
	 *	Add Field and Label to buffer and push buffered field to Panel
	 *  @param editor editor
	 *  @param mField field model
	 */
	public void addFieldBuffered (VEditor editor, GridField mField)
	{
		wrap = mField != null ? !mField.isSameLine() : false;
		
		if ( prevEditor != null && prevField != null)
			addField(prevEditor, prevField);
		prevEditor = editor;
		prevField = mField;
	}
	/**
	 *	Add the previous Field and Label to Panel
	 *  @param editor editor
	 *  @param mField field model
	 *  @param wrap move to next line after this field
	 */
	public void addField (VEditor editor, GridField mField)
	{
		//[ 1757088 ]
		int AD_Tab_ID = mField.getIncluded_Tab_ID();
		if(AD_Tab_ID != 0 )
		{
			CollapsiblePanel m_tab = new CollapsiblePanel("");
			m_tabincludelist.put(AD_Tab_ID, m_tab);
			m_main.add(m_tab,"newline, spanx, growx");
			m_tab.setName("IncludedTab#"+AD_Tab_ID);
			
			if (includedTabList.containsKey(AD_Tab_ID)) 
			{
				includeTab(includedTabList.get(AD_Tab_ID));
			}
			return;
		}		  
		
		CLabel label = VEditorFactory.getLabel(mField); 
		if (label == null && editor == null)
			return;

		boolean sameLine = mField.isSameLine();
		
		//[ 1757088 ]              		//	sets top
		String fieldGroup = mField.getFieldGroup();
		String fieldGroupType = mField.getFieldGroupType();
		if (Util.isEmpty(fieldGroup))
		{
			fieldGroup = m_oldFieldGroup;
			fieldGroupType = m_oldFieldGroupType;
		}
		
		if (addGroup(fieldGroup, fieldGroupType)) //	sets top			
		{
			if (X_AD_FieldGroup.FIELDGROUPTYPE_Collapse.equals(fieldGroupType))
			{
				CollapsiblePanel panel = (CollapsiblePanel) m_tablist.get(fieldGroup);
				panel.getCollapsiblePane().setCollapsed(mField.getIsCollapsedByDefault());
			}
			sameLine = false;
		}
		else
		{
			fieldGroup = m_oldFieldGroup;
			fieldGroupType = m_oldFieldGroupType;
		}
		
		// label constraints
		String constraints = "align trailing";
		if ( !sameLine )
		{
			constraints += ", newline";
		}
		
		//	*** The Label ***
		if ( label == null)
		{
			label = new CLabel("");
			label.setName(mField.getColumnName());
		}
		//
		if (mField.isCreateMnemonic())
			setMnemonic(label, mField.getMnemonic());

		if (fieldGroupType.equals(X_AD_FieldGroup.FIELDGROUPTYPE_Tab))
		{
			CPanel m_tab = (CPanel)m_tablist.get(fieldGroup);
			m_tab.add(label, constraints);	
		}			
		else if (fieldGroupType.equals(X_AD_FieldGroup.FIELDGROUPTYPE_Collapse))
		{
			CollapsiblePanel m_tab = (CollapsiblePanel) m_tablist.get(fieldGroup);
			m_tab.getCollapsiblePane().getContentPane().add(label, constraints);
		}
		else // Label
		{
			m_main.add(label, constraints);
		}

		int currentWidth = label.getPreferredSize().width;
		labelMinWidth = currentWidth > labelMinWidth ? currentWidth : labelMinWidth;

		//	*** The Field ***
		if (editor != null)
		{
			Component field = (Component)editor;
			//	field constraints
			//  long fields span all remaining columns
			constraints = "growx, pushx";
			if ( mField.isLongField() )
			{
				constraints += mField.isLongField() ? ",spanx" : "";
			}
			if ( wrap )
			{
				constraints += ", wrap";
			}
			
			//	Add Field
			//[ 1757088 ]
			if (fieldGroupType.equals(X_AD_FieldGroup.FIELDGROUPTYPE_Tab))
			{
				CPanel m_tab = (CPanel)m_tablist.get(fieldGroup);	
				m_tab.add(field, constraints);			  	
			}			
			else if (fieldGroupType.equals(X_AD_FieldGroup.FIELDGROUPTYPE_Collapse))
			{
				CollapsiblePanel m_tab = (CollapsiblePanel) m_tablist.get(fieldGroup);
				Component comp = (Component) editor;
				comp.setMinimumSize(comp.getPreferredSize());
				if (mField.getDisplayType() == DisplayType.Text 
					|| mField.getDisplayType() == DisplayType.Memo 
					||mField.getDisplayType() == DisplayType.TextLong )
				{
					Component component = (Component) editor;
					component.setMinimumSize(component.getPreferredSize());
				}
				if (!mField.isLongField())
					fieldMinWidth = field.getPreferredSize().width > fieldMinWidth ? field.getPreferredSize().width : fieldMinWidth;
				m_tab.getCollapsiblePane().getContentPane().add(field, constraints);
			}
			else // Label or null
			{								
				m_main.add(field, constraints);
			if (!mField.isLongField())
				fieldMinWidth = field.getPreferredSize().width > fieldMinWidth ? field.getPreferredSize().width : fieldMinWidth;
			}	
			//	Link Label to Field
			if (label != null)
				label.setLabelFor(field);
			else if (mField.isCreateMnemonic())
				setMnemonic(editor, mField.getMnemonic());
		}
	}	//	addField

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
			String tpConstraints = defaultLayoutConstraints ;
			MigLayout layout = new MigLayout(tpConstraints);
			layout.addLayoutCallback(callback);
			m_tab.setLayout(layout);
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
			
			String cpConstraints = defaultLayoutConstraints;
			// 0 inset left and right as this is a nested panel
			// 0 inset top because of the struts added below
			cpConstraints += ", ins 0 0 n 0";

			MigLayout layout = new MigLayout(cpConstraints);
			layout.addLayoutCallback(callback);
			
			collapsibleSection.setName(fieldGroup);
			m_main.add(collapsibleSection, "newline, spanx, growx"); 
			m_tab.setLayout(layout);
			/* for compatibility with old layout, force collapsible field groups
			 *  to have a minimum of two columns by inserting invisible components
			 */
			Component strut1 = Box.createVerticalStrut(1);
			strut1.setName("vstrut1"+fieldGroup);
			Component strut2 = Box.createVerticalStrut(1);
			strut2.setName("vstrut2"+fieldGroup);
			m_tab.add(new CLabel(""), "gap 0 0 0 0");
			m_tab.add(strut1, "pushx, growx, gap 0 0 0 0");
			m_tab.add(new CLabel(""), "");
			m_tab.add(strut2, "pushx, growx, gap 0 0 0 0, wrap");
			m_tablist.put(fieldGroup, collapsibleSection);
		}									
		else // Label or null
		{
			CLabel label = new CLabel(fieldGroup, CLabel.LEADING);
			label.setFont(AdempierePLAF.getFont_Label().deriveFont(Font.BOLDITALIC, AdempierePLAF.getFont_Label().getSize2D()));
			m_main.add(label, "newline, alignx leading");
			m_main.add(new JSeparator(), "newline, spanx, growx");
			//	reset
		}
		m_oldFieldGroup = fieldGroup;
		m_oldFieldGroupType = fieldGroupType;
		return true;
	}	//	addGroup

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
				return 0;	//	 if first char would be returned, the first occurrence is invalid.
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

	private HashMap<Integer, GridController> includedTabList = new HashMap<Integer, GridController>();
	
	/**
	 * 	Set Window level Mnemonics
	 *	@param set true if set otherwise unregister
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

	
	private void findChildComponents(CPanel container, List<Component> list) 
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
		java.util.ArrayList<Component> list = new java.util.ArrayList<Component>();       

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

	/**
	 * 
	 * @param detail
	 */
	public void includeTab(GridController detail) {
		CollapsiblePanel section = m_tabincludelist.get(detail.getMTab().getAD_Tab_ID());
		if(section != null)
		{				
			APanel panel = new APanel(detail, m_WindowNo);
			String name = detail.getMTab().getName() + "";		
			section.setTitle(name);
			section.getCollapsiblePane().getContentPane().setLayout(new BorderLayout());
			section.getCollapsiblePane().getContentPane().add(panel, BorderLayout.CENTER);
		}		

		//this can be call before addField
		if (!includedTabList.containsKey(detail.getMTab().getAD_Tab_ID()))
			includedTabList.put(detail.getMTab().getAD_Tab_ID(), detail);
	}

	
}	//	VPanel
