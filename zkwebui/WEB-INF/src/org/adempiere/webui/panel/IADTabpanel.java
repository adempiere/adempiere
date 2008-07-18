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

import org.compiere.model.GridTab;
import org.compiere.util.Evaluatee;
import org.zkoss.zk.ui.Component;

/**
 * Interface for UI component that edit/display record using ad_tab definitions
 * @author Low Heng Sin
 *
 */
public interface IADTabpanel extends Component, Evaluatee {

	public String getDisplayLogic();

	public int getTabLevel();

	public boolean isCurrent();

	public String getTitle();

	public void createUI();

	public GridTab getGridTab();

	public void activate(boolean b);

	public void query();

	public void refresh();

	public void query(boolean currentRows, int currentDays, int i);

	public void switchRowPresentation();

	public boolean isEditing();

	public void dynamicDisplay(int i);

	public void editRecord(boolean b);

}
