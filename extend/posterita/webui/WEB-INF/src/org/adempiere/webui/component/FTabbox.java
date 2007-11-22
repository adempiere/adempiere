/******************************************************************************
 * Product: Posterita Ajax UI 												  *
 * Copyright (C) 2007 Posterita Ltd.  All Rights Reserved.                    *
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
 * Posterita Ltd., 3, Draper Avenue, Quatre Bornes, Mauritius                 *
 * or via info@posterita.org or http://www.posterita.org/                     *
 *****************************************************************************/

package org.adempiere.webui.component;

import java.util.ArrayList;

import org.adempiere.webui.panel.ADTabpanel;
import org.compiere.grid.VTabbedPane;
import org.compiere.model.DataStatusEvent;
import org.compiere.model.GridTab;
import org.compiere.util.CLogger;
import org.compiere.util.Evaluator;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;

/**
 *
 * @author  <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date    Feb 25, 2007
 * @version $Revision: 0.10 $
 */
public class FTabbox extends Tabbox
{
    private static final long serialVersionUID = 1L;
    
    /** Logger                  */
    private static CLogger  log = CLogger.getCLogger (VTabbedPane.class);
    /** List of dependent Variables     */
    private ArrayList<String>   m_dependents = new ArrayList<String>();
    /** Tab Panels for this tab box */
    private FTabpanels          tabpanels;
    /** Tabs associated to this tab box */
    private Tabs                tabs;
    
    public FTabbox()
    {
        super();
        init();
    }
    
    private void init()
    {
        tabpanels = new FTabpanels();
        tabs = new Tabs();
        //this.setSclass("lite");
        this.appendChild(tabs);
        this.appendChild(tabpanels);
        this.setOrient("vertical");
    }// init
    
    /**
     *  Add Tab
     *  @param tabName name
     *  @param gTab grid tab model
     *  @param tabElement GridController or VSortTab
     */
    public void addTab(GridTab gTab, Tabpanel tabPanel)
    {
        Tab tab = new Tab(gTab.getName());
        tabPanel.setEnabled(gTab.isDisplayed());
        tabs.appendChild(tab);
        tabpanels.appendChild(tabPanel);
        ArrayList<String>   dependents = gTab.getDependentOn();
        for (int i = 0; i < dependents.size(); i++)
        {
            String name = dependents.get(i);
            if (!m_dependents.contains(name))
            {
                m_dependents.add(name);
            }
        }
    }//  addTab
    
    /**
     * @param index of tab panel
     * @return
     */
    public boolean isEnabledAt(int index) 
    {
        Tabpanel tabpanel = tabpanels.getTabpanel(index);
        if (tabpanel == null)
        {
            return false;
        }
        return tabpanel.isEnabled();
    }// isEnabledAt

    private boolean isDisplay(ADTabpanel tabpanel)
    {
        String logic = tabpanel.getDisplayLogic();
        if (logic != null && logic.length() > 0)
        {
            boolean display = Evaluator.evaluateLogic(tabpanel, logic);
            if (!display)
            {
                log.info("Not displayed - " + logic);
                return false;
            }
        }
        return true;
    }
    
    /**
     * 
     * @param oldIndex
     * @param newIndex
     * @return
     */
    public boolean updateSelectedIndex(int oldIndex, int newIndex)
    {
        Tabpanel tabpanel = tabpanels.getTabpanel(newIndex);
        
        ADTabpanel newTab = (ADTabpanel)tabpanel;
        
        if (tabpanel == null || !isDisplay(newTab))
        {
            super.setSelectedIndex(oldIndex);
            return false;
        }
        
        boolean canJump = true;

        if (newIndex != oldIndex)
        {
            ADTabpanel oldTabpanel = tabpanels.getTabpanel(oldIndex);
            if (oldTabpanel != null)
            {
                ADTabpanel oldTab = oldTabpanel;
                if (newTab.getTabLevel() > oldTab.getTabLevel())
                {
                    int currentLevel = newTab.getTabLevel();
                    for (int i = newIndex - 1; i >= 0; i--)
                    {
                        ADTabpanel tabPanel = tabpanels.getTabpanel(i);
                        if (tabPanel.getTabLevel() < currentLevel)
                        {
                            if (!tabPanel.isCurrent())
                            {
                                canJump = false;
                                break;
                            }
                            currentLevel = tabPanel.getTabLevel();
                        }
                    }
                }
            }
            else
            {
                canJump = false;
            }
        }
        if (canJump)
        {
            super.setSelectedIndex(newIndex);
        }
        else
        {
            super.setSelectedIndex (oldIndex);
        }
        return canJump;
    }
    
    /**
     *  Evaluate Tab Logic
     *  @param e event
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
            for (int i = 0; i < this.getTabCount(); i++)
            {
                ADTabpanel tabPanel = tabpanels.getTabpanel(i);
                String logic = tabPanel.getDisplayLogic();
                boolean display = true;
                if (logic != null && logic.length() > 0)
                {
                    display = Evaluator.evaluateLogic(tabPanel, logic);
                }
                tabPanel.setEnabled(display);
                
            }
        }
        
    } //  evaluate

    /**
     * @return the number of tab panels present
     */
    public int getTabCount()
    {
        return tabpanels.getChildren().size();
    }
    
    public ADTabpanel getTabpanel(int index)
    {
        try
        {
            Tabpanels tabpanels = this.getTabpanels();
            ADTabpanel tabPanel = (ADTabpanel)tabpanels.getChildren().get(index);
            return tabPanel;
        }
        catch (Exception ex)
        {
            throw new IndexOutOfBoundsException(ex.getMessage());
        }
    }
    
    /**
     * Return the selected Tab Panel
     */
    public ADTabpanel getSelectedTabpanel()
    {
        return getTabpanel(this.getSelectedIndex());
    }
    
    /**
     * @return whether all events should be reported as they occur.
     */
    public boolean isAsap()
    {
        return true;
    }
    
    public boolean containsTab(Tab tab)
    {
    	return tabs.getChildren().contains(tab);
    }
}
