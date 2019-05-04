/******************************************************************************
 * Copyright (C) 2008 Low Heng Sin  All Rights Reserved.                      *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package org.adempiere.webui.panel;

import java.util.List;

import org.adempiere.webui.component.CWindowToolbar;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridPanel;
import org.adempiere.webui.panel.ADTabPanel.EmbeddedPanel;
import org.adempiere.webui.panel.ADTabPanel.HorizontalEmbeddedPanel;
import org.compiere.model.GridTab;
import org.compiere.util.Evaluatee;
import org.zkoss.zk.ui.Component;

/**
 * Interface for UI component that edit/display record using ad_tab definitions
 * @author Low Heng Sin
 *
 * @author e-Evolution , victor.perez@e-evolution.com
 *    <li>Implement embedded or horizontal tab panel https://adempiere.atlassian.net/browse/ADEMPIERE-319
 *    <li>New ADempiere 3.8.0 ZK Theme Light  https://adempiere.atlassian.net/browse/ADEMPIERE-320
 *
 */
public interface IADTabPanel extends Component, Evaluatee {

	/**
	 * @return display logic
	 */
	public String getDisplayLogic();

	/**
	 * @return tab level
	 */
	public int getTabLevel();

	/**
	 * @return true if refresh is not needed
	 */
	public boolean isCurrent();

	/**
	 *
	 * @return title
	 */
	public String getTitle();

	/**
	 * Render the panel
	 */
	public void createUI();

	/**
	 *
	 * @return GridTab
	 */
	public GridTab getGridTab();

	/**
	 * activate/deactivate the panel
	 * @param b
	 */
	public void activate(boolean b);

	/**
	 * retrieve data from db
	 */
	public void query();

	/**
	 * Refresh from db
	 */
	public void refresh();

	/**
	 * retrieve data from db
	 * @param currentRows
	 * @param currentDays
	 * @param maxRows
	 */
	public void query(boolean currentRows, int currentDays, int maxRows);

	/**
	 * Toggle between grid and form view
	 */
	public void switchRowPresentation();

	/**
	 * Return if is Grid View
	 * @return
	 */
	public boolean isGridView();

	/**
	 * Dynamic update of field properties ( visibility, filter and mandatory )
	 * @param i
	 */
	public void dynamicDisplay(int i);

	/**
	 * After save event
	 * @param onSaveEvent
	 */
	public void afterSave(boolean onSaveEvent);

	/**
	 * Enter key event
	 * @return true if the event is process
	 */
	public boolean onEnterKey();
	
	public void setUnselected(IADTabPanel panel);
	
	public void setSelected(IADTabPanel panel);
	
	public Grid getGrid();
	
	public GridPanel getListPanel();
	
	public List<EmbeddedPanel> getIncludedPanel();

    public List<HorizontalEmbeddedPanel> getHorizontalIncludedPanel();
	
	public int getWindowNo();
	
	public void setGlobalToolbar (CWindowToolbar toolbar);
	
	public CWindowToolbar getGlobalToolbar();

    public void setIsEmbedded(boolean isEmbedded);

    public boolean isEmbedded() ;
	
}
