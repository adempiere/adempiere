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
package org.compiere.grid;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JTabbedPane;

import org.compiere.apps.ADialog;
import org.compiere.apps.APanel;
import org.compiere.model.DataStatusEvent;
import org.compiere.model.GridTab;
import org.compiere.swing.CTabbedPane;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Evaluator;
import org.compiere.util.Language;

/**
 *  Tabbed Pane - either Workbench or Window Tab
 *
 *  @author Jorg Janke
 *  @version $Id: VTabbedPane.java,v 1.3 2006/07/30 00:51:28 jjanke Exp $
 */
public class VTabbedPane extends CTabbedPane
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 716455372513747084L;

	/**
	 *  Constructor
	 *  @param isWorkbench is this a workbench tab (tabs on the left side)
	 */
	public VTabbedPane (boolean isWorkbench)
	{
		super();
		setTabLayoutPolicy (JTabbedPane.SCROLL_TAB_LAYOUT);
		setWorkbench (isWorkbench);
		setFocusable(false);
	}   //  VTabbedPane

	/**	Logger					*/
	private static CLogger	log = CLogger.getCLogger (VTabbedPane.class);
	/** Workbench 				*/
	private boolean			m_workbenchTab = false;
	/** List of dependent Variables		*/
	private ArrayList<String>	m_dependents = new ArrayList<String>();
	/** Disabled Icon			*/
	private static Icon			s_disabledIcon = null;
	
	private ArrayList<Component> components = new ArrayList<Component>();
	private ArrayList<GridTab> gTabs = new ArrayList<GridTab>();
	private ArrayList<String> tabNames = new ArrayList<String>();
	
	/**
	 *  toString
	 *  @return info
	 */
	public String toString()
	{
		return (m_workbenchTab ? "WorkbenchTab" : "WindowTab")
			+ " - selected " + getSelectedIndex() + " of " + getTabCount();
	}   //  toString

	/**
	 * 	Add Tab
	 *	@param tabName name
	 *	@param gTab grid tab model
	 *	@param tabElement GridController or VSortTab
	 */
	public void addTab(String tabName, GridTab gTab, Component tabElement)
	{
		int index = getTabCount();
		tabNames.add(tabName);
		gTabs.add(gTab);
		components.add(tabElement);
		
		super.addTab (tabName, gTab.getIcon(), 
				tabElement, gTab.getDescription());
		
		ArrayList<String>	dependents = gTab.getDependentOn();
		for (int i = 0; i < dependents.size(); i++)
		{
			String name = dependents.get(i);
			if (!m_dependents.contains(name))
				m_dependents.add(name);
		}
		if (s_disabledIcon == null)
			s_disabledIcon = Env.getImageIcon("Cancel10.gif");
		setDisabledIconAt(index, s_disabledIcon);
	}	//	addTab
	
	private void hideTab(String tabName)	{
		
		int tabIndex = indexOfTab(tabName);
		
		if ( tabIndex != -1 )
			removeTabAt(tabIndex);
	}

	private void showTab(String tabName)	{
		int insertAt = 0;
		
		if ( indexOfTab(tabName) != -1 )  // already visible
			return;
		
		for ( int i = 0; i < tabNames.size(); i++ )
		{
			String name = tabNames.get(i);
			if ( name.equals(tabName))
			{
				insertTab (tabName, gTabs.get(i).getIcon(), 
						components.get(i), gTabs.get(i).getDescription(), insertAt);
				break;
			}
			
			if ( indexOfTab(name) != -1 )  // tab is visible, insert after
				insertAt ++;
		}
		
	}

	/**
	 * @param gridTab
	 * @return tab index or -1 if not found
	 */
	public int findTabindex(GridTab gridTab) 
	{
		for (int i = 0; i < gTabs.size(); i++) 
		{
			if (gTabs.get(i) == gridTab)
			{
				return indexOfTab(tabNames.get(i));
			}
		}
		return -1;
	}
	
	/**
	 *  Set Workbench - or Window
	 *  @param isWorkbench
	 */
	public void setWorkbench (boolean isWorkbench)
	{
		m_workbenchTab = isWorkbench;
		if (m_workbenchTab)
			super.setTabPlacement(JTabbedPane.BOTTOM);
		else
			super.setTabPlacement(Language.getLoginLanguage().isLeftToRight() 
				? JTabbedPane.LEFT : JTabbedPane.RIGHT);
	}   //  setWorkbench

	/**
	 *  Tab is Workbench (not Window)
	 *  @return true if Workbench
	 */
	public boolean isWorkbench()
	{
		return m_workbenchTab;
	}   //  toString

	/**
	 *  Set Tab Placement.
	 *  Do not use - set via setWorkBench
	 *  @param notUsed
	 */
	public void setTabPlacement (int notUsed)
	{
		new java.lang.IllegalAccessError("Do not use VTabbedPane.setTabPlacement directly");
	}   //  setTabPlacement

	/**
	 *  Dispose all contained VTabbedPanes and GridControllers
	 *  @param aPanel
	 */
	public void dispose (APanel aPanel)
	{
		Component[] comp = getComponents();
		for (int i = 0; i < comp.length; i++)
		{
			if (comp[i] instanceof VTabbedPane)
			{
				VTabbedPane tp = (VTabbedPane)comp[i];
				tp.removeChangeListener(aPanel);
				tp.dispose(aPanel);
			}
			else if (comp[i] instanceof GridController)
			{
				GridController gc = (GridController)comp[i];
				gc.removeDataStatusListener(aPanel);
				gc.dispose();
			}
		}
		removeAll();
	}   //  dispose

	@Override
	//hengsin, bug [ 1637763 ]
	public boolean isEnabledAt(int index) {
		boolean enabled = super.isEnabledAt(index); 
		if (!enabled) return enabled;
		Component comp = getComponentAt(index);
		GridController gc = null;
		if (comp instanceof GridController)
			gc = (GridController)comp;
		//	Display
		if (gc != null)
		{
			enabled = isDisplay(gc);
		}
		return enabled;
	}

	//hengsin, bug [ 1637763 ]
	private boolean isDisplay(GridController gc)
	{
		String logic = gc.getDisplayLogic();
		if (logic != null && logic.length() > 0)
		{
			boolean display = Evaluator.evaluateLogic(gc, logic);
			if (!display)
			{
				log.info("Not displayed - " + logic);
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 	Set Selected Index.
	 * 	Register/Unregister Mnemonics
	 *	@param index index
	 */
	public void setSelectedIndex (int index)
	{
		Component newC = getComponentAt(index);
		GridController newGC = null;
		if (newC instanceof GridController)
			newGC = (GridController)newC;
		//	Display
		if (newGC != null)
		{
			//hengsin, bug [ 1637763 ]
			if(isDisplay(newGC) == false)
				return;
		}

		//
		int oldIndex = getSelectedIndex();
		if (newGC != null && oldIndex >= 0 && index != oldIndex)
		{
			Component oldC = getComponentAt(oldIndex);
			if (oldC != null && oldC instanceof GridController)
			{
				GridController oldGC = (GridController)oldC;
				if (newGC.getTabLevel() > oldGC.getTabLevel()+1)
				{
					//  validate
					//	Search for right tab
					GridController rightGC = null;
					boolean canJump = true;
					int currentLevel = newGC.getTabLevel();
					for (int i = index-1; i >=0; i--)
					{
						Component rightC = getComponentAt(i);
						if (rightC instanceof GridController)
						{
							GridController gc = (GridController)rightC;
							//can only skip level if all parent data are not stale
							if (gc.getTabLevel() < currentLevel)
							{
								if (gc.getTabLevel() == oldGC.getTabLevel()+1)
								{
									rightGC = gc;
								}							
								if (!gc.isCurrent())
									canJump = false;
								currentLevel = gc.getTabLevel();
							}
						}
					}
					if (canJump == false)
					{
						if (rightGC != null )
							ADialog.warn(0, this, "TabSwitchJumpGo", rightGC.getTitle());
						else
							ADialog.warn(0, this, "TabSwitchJump");
						return;
					}
				}
				oldGC.setMnemonics(false);
			}
		}
		//	Switch
		super.setSelectedIndex (index);
		if (newGC != null)
			newGC.setMnemonics(true);
	}	//	setSelectedIndex

	/**
	 * 	Evaluate Tab Logic
	 *	@param e event
	 */
	public void evaluate (DataStatusEvent e)
	{
		boolean process = e == null;
		String columnName = null;
		if (!process)
		{
			columnName = e.getColumnName();
			if (columnName != null)
				process = m_dependents.contains(columnName);
			else
				process = true;
		}
			
		if (process)
		{
			log.config(columnName == null ? "" : columnName);
			for (int i = 0; i < components.size(); i++)
			{
				Component c = components.get(i);
				if (c instanceof GridController)
				{
					GridController gc = (GridController)c;
					if (!gc.isDetailGrid())  //ignore included tabs
					{
						boolean display = isDisplay(gc);
						if ( display )
							showTab(tabNames.get(i));
						else
							hideTab(tabNames.get(i));
					}
				}
			}
		}
	}	//	evaluate
	
}   //  VTabbdPane
