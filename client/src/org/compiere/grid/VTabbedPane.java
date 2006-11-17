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
 *****************************************************************************/
package org.compiere.grid;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import org.compiere.*;
import org.compiere.apps.*;
import org.compiere.model.*;
import org.compiere.swing.*;
import org.compiere.util.*;

/**
 *  Tabbed Pane - either Workbench or Window Tab
 *
 *  @author Jorg Janke
 *  @version $Id: VTabbedPane.java,v 1.3 2006/07/30 00:51:28 jjanke Exp $
 */
public class VTabbedPane extends CTabbedPane
{
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
				gc.addDataStatusListener(aPanel);
				gc.dispose();
			}
		}
		removeAll();
	}   //  dispose

	
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
			String logic = newGC.getDisplayLogic();
			if (logic != null && logic.length() > 0)
			{
				boolean display = Evaluator.evaluateLogic(newGC, logic);
				if (!display)
				{
					log.info("Not displayed - " + logic);
					return;
				}
			}
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
					//	Search for right tab
					for (int i = index-1; i >=0; i--)
					{
						Component rightC = getComponentAt(i);
						GridController rightGC = null;
						if (rightC instanceof GridController)
						{
							rightGC = (GridController)rightC;
							if (rightGC.getTabLevel() == oldGC.getTabLevel()+1)
							{
								ADialog.warn(0, this, "TabSwitchJumpGo", rightGC.getTitle());
								return;
							}
						}
					}
					ADialog.warn(0, this, "TabSwitchJump");
					return;
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
			for (int i = 0; i < getTabCount(); i++)
			{
				Component c = getComponentAt(i);
				if (c instanceof GridController)
				{
					GridController gc = (GridController)c;
					String logic = gc.getDisplayLogic();
					boolean display = true;
					if (logic != null && logic.length() > 0)
						display = Evaluator.evaluateLogic(gc, logic);
					setEnabledAt(i, display);
				}
			}
		}
	}	//	evaluate
	
}   //  VTabbdPane
