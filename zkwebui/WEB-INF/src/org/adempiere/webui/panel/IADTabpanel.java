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

	/**
	 * 获取显示逻辑
	 * @return display logic
	 */
	public String getDisplayLogic();

	/**
	 * 获取Tab 的层次
	 * @return tab level
	 */
	public int getTabLevel();

	/**
	 * 是否需要刷新
	 * @return true if refresh is not needed
	 */
	public boolean isCurrent();

	/**
	 * 获取title
	 * @return title
	 */
	public String getTitle();

	/**
	 * 创建UI
	 * Render the panel
	 */
	public void createUI();

	/**
	 * 获取对应的GridTab
	 * @return GridTab
	 */
	public GridTab getGridTab();

	/**
	 * 激活/不激活panel
	 * activate/deactivate the panel
	 * @param b
	 */
	public void activate(boolean b);

	/**
	 * 查询
	 * retrieve data from db
	 */
	public void query();

	/**
	 * 刷新
	 * Refresh from db
	 */
	public void refresh();

	/**
	 * 查询
	 * retrieve data from db
	 * @param currentRows
	 * @param currentDays
	 * @param maxRows
	 */
	public void query(boolean currentRows, int currentDays, int maxRows);

	/**
	 * 表格切换
	 * Toggle between grid and form view
	 */
	public void switchRowPresentation();

	/**
	 * 动态更新列属性（可见，排序，强制）
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
}
