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
import java.util.Collections;
import java.util.List;

import org.adempiere.webui.panel.WSortTab;
import org.adempiere.webui.panel.ADTabPanel;
import org.adempiere.webui.panel.AbstractADWindowPanel;
import org.adempiere.webui.part.AbstractUIPart;
import org.compiere.model.DataStatusEvent;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 *
 * @author  <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @author  <a href="mailto:hengsin@gmail.com">Low Heng Sin</a>
 * @author e-Evolution , victor.perez@e-evolution.com
 *    <li>Implement embedded or horizontal tab panel https://adempiere.atlassian.net/browse/ADEMPIERE-319
 *    <li>New ADempiere 3.8.0 ZK Theme Light  https://adempiere.atlassian.net/browse/ADEMPIERE-320
 * @date    Feb 25, 2007
 * @version $Revision: 0.10 $
 * @author Raul Muñoz , http://erpcya.com rmunoz@erpcya.com
 * 		<li> FR [ 1115 ] Look & Feel Corrections - When field is only read need other background to identify easily this difference
 *  	@see https://github.com/adempiere/adempiere/issues/1115
 */
public abstract class AbstractADTab extends AbstractUIPart implements IADTab
{
    /** Logger                  */
    private static CLogger  log = CLogger.getCLogger (AbstractADTab.class);
    /** List of dependent Variables     */
    private ArrayList<String>   m_dependents = new ArrayList<String>();
    
    /** Tabs associated to this tab box */
    protected List<org.adempiere.webui.panel.IADTabPanel> tabPanelList = new ArrayList<org.adempiere.webui.panel.IADTabPanel>();
	protected AbstractADWindowPanel adWindowPanel;
    
    public AbstractADTab()
    {
    }
    
    /**
     *  Add Tab
     *  @param tabName name
     *  @param gTab grid tab model
     *  @param tabElement GridController or VSortTab
     */
    public void addTab(GridTab gTab, org.adempiere.webui.panel.IADTabPanel tabPanel)
    {
    	tabPanelList.add(tabPanel);
        ArrayList<String>  dependents = gTab.getDependentOn();
        for (int i = 0; i < dependents.size(); i++)
        {
            String name = dependents.get(i);
            if (!m_dependents.contains(name))
            {
                m_dependents.add(name);
            }
        }
        
        doAddTab(gTab, tabPanel);                
    }//  addTab
    
    protected abstract void doAddTab(GridTab tab, org.adempiere.webui.panel.IADTabPanel tabPanel);

	/**
     * @param index of tab panel
     * @return
     */
    public boolean isEnabledAt(int index) 
    {
    	return true;
    }// isEnabledAt
	
    /**
     * Is Read Only
     * @param index
     * @return
     */
    private boolean isReadOnly(org.adempiere.webui.panel.IADTabPanel newTab)
    {
    	return newTab.getGridTab().isReadOnly();
    }
    
    private boolean isDisplay(org.adempiere.webui.panel.IADTabPanel newTab)
    {
    	return newTab.getGridTab().isDisplayed();
    	/*
        String logic = newTab.getDisplayLogic();
        if (logic != null && logic.length() > 0)
        {
        	
        	String parsed = Env.parseContext (, newTab.getWindowNo(), logic, false, false).trim(); //Add WindowNo
    		if (parsed.length() == 0)
    			return true;
        	
            boolean display = Evaluator.evaluateLogic(newTab, parsed);
            if (!display)
            {
                log.info("Not displayed - " + logic);
                return false;
            }
        }
        return true;*/
    }
    
    /**
     * 
     * @param oldIndex
     * @param newIndex
     * @return
     */
    public boolean updateSelectedIndex(int oldIndex, int newIndex)
    {
        org.adempiere.webui.panel.IADTabPanel newTab = tabPanelList.get(newIndex);
        
        if (!isDisplay(newTab))
        {
            return false;
        }
        
        boolean canJump = true;

        if (newIndex != oldIndex)
        {
            canJump = canNavigateTo(oldIndex, newIndex);
            if (canJump) 
            {
            	prepareContext(newIndex, newTab);
	            doTabSelectionChanged(oldIndex, newIndex);
            }
        }
        
        return canJump;
    }
    
    private void prepareContext(int newIndex, org.adempiere.webui.panel.IADTabPanel newTab) {
		//update context
		if (newTab != null && (adWindowPanel == null || !adWindowPanel.isEmbedded()))
		{
			List<Integer> parents = new ArrayList<Integer>();
			//get parent list
			if (newIndex > 0)
			{
				int currentLevel = newTab.getTabLevel();
				for (int i = newIndex - 1; i >= 0; i--)
				{
					org.adempiere.webui.panel.IADTabPanel adtab = tabPanelList.get(i);
					if (adtab.getGridTab() == null) continue;
					if (adtab instanceof WSortTab) continue;
					if (adtab.getTabLevel() < currentLevel || i == 0)
					{
						parents.add(i);
					}
				}
				Collections.reverse(parents);
			}
			else
			{
				parents.add(0);
			}

			//clear context
			for (int i = 0; i < tabPanelList.size(); i++)
			{
				org.adempiere.webui.panel.IADTabPanel adtab = tabPanelList.get(i);
				if (adtab.getGridTab() == null) continue;
				if (adtab instanceof WSortTab) continue;
				GridField[] fields = adtab.getGridTab().getFields();
				for (GridField gf : fields)
				{
					Env.setContext(Env.getCtx(), gf.getWindowNo(),  gf.getColumnName(), "");
				}
			}

			//add parent value to context
			if (!parents.isEmpty())
			{
				for(int i : parents)
				{
					org.adempiere.webui.panel.IADTabPanel adtab = tabPanelList.get(i);

					GridField[] fields = adtab.getGridTab().getFields();
					for (GridField gf : fields)
					{
						if (gf.getValue() != null) // preserve value of tab above if null on current tab
							gf.updateContext();
					}
				}
			}
		}
	}

    protected abstract void doTabSelectionChanged(int oldIndex, int newIndex);
    
    /**
     * Is Read Only
     * @param index
     * @return
     */
	public boolean isReadOnly(int index) {
		//   FR [ 1115 ]
		if (index >= tabPanelList.size())
    		return false;
    	
    	org.adempiere.webui.panel.IADTabPanel newTab = tabPanelList.get(index);
    	if (newTab instanceof ADTabPanel)
    	{
	    	if (!isReadOnly(newTab))
	        {
	            return false;
	        }
    	}
    	return true;
	}
	
    public boolean isDisplay(int index) {
    	if (index >= tabPanelList.size())
    		return false;
    	
    	org.adempiere.webui.panel.IADTabPanel newTab = tabPanelList.get(index);
    	if (newTab instanceof ADTabPanel)
    	{
	    	if (!isDisplay(newTab))
	        {
	            return false;
	        }
    	}
    	return true;
    }
    
	public boolean canNavigateTo(int fromIndex, int toIndex) {
    	org.adempiere.webui.panel.IADTabPanel newTab = tabPanelList.get(toIndex);
    	if (newTab instanceof ADTabPanel)
    	{
	    	if (!isDisplay(newTab))
	        {
	            return false;
	        }
    	}
        
        boolean canJump = true;

        if (toIndex != fromIndex)
        {
            org.adempiere.webui.panel.IADTabPanel oldTabpanel = fromIndex >= 0 ? tabPanelList.get(fromIndex) : null;
            if (oldTabpanel != null)
            {
                org.adempiere.webui.panel.IADTabPanel oldTab = oldTabpanel;
                if (newTab.getTabLevel() > oldTab.getTabLevel())
                {
                    int currentLevel = newTab.getTabLevel();
                    for (int i = toIndex - 1; i >= 0; i--)
                    {
                        org.adempiere.webui.panel.IADTabPanel tabPanel = tabPanelList.get(i);
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
        }        
        return canJump;
    }
    
    /**
     * 
     * @return full path
     */
    public String getPath() {
    	StringBuffer path = new StringBuffer();
    	int s = this.getSelectedIndex();
    	if (s <= 0 ) s = 0;
    	org.adempiere.webui.panel.IADTabPanel p = tabPanelList.get(s);
    	for (int i = 0; i <= s; i++) {
    		String n = null;
    		if (i == s)
    			n = p.getTitle();
    		else {
    			org.adempiere.webui.panel.IADTabPanel t = tabPanelList.get(i);
    			if (t.getTabLevel() < p.getTabLevel())
    				n = t.getTitle();
    		}
    		if (n != null) {
    			if (path.length() > 0) {
    				path.append(" > ");
    			}
    			path.append(n);
    		}
    	}
    	
    	return path.toString();
    }
    
    /**
     *  Evaluate Tab Logic
     *  @param e event
     */
    public void evaluate (DataStatusEvent e)
    {
    	
        boolean process = (e == null);
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
        	updateTabState();
        }
        
    } //  evaluate

    protected abstract void updateTabState();

	/**
     * @return the number of tab panels present
     */
    public int getTabCount()
    {
        return tabPanelList.size();
    }
    
    public org.adempiere.webui.panel.IADTabPanel getADTabpanel(int index)
    {
        try
        {
            org.adempiere.webui.panel.IADTabPanel tabPanel = tabPanelList.get(index);
            return tabPanel;
        }
        catch (Exception ex)
        {
            throw new IndexOutOfBoundsException(ex.getMessage());
        }
    }
    
    public void setSelectedIndex(int newIndex) {
    	int oldIndex = getSelectedIndex();
    	updateSelectedIndex(oldIndex, newIndex);
    }

	public void setADWindowPanel(AbstractADWindowPanel abstractADWindowPanel) {
		this.adWindowPanel = abstractADWindowPanel;
	}
}
