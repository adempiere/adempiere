/******************************************************************************
 * Copyright (C) 2008 Low Heng Sin                                            *
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
package org.adempiere.webui.component;

import org.adempiere.webui.panel.IADTabPanel;
import org.adempiere.webui.part.UIPart;
import org.compiere.model.DataStatusEvent;
import org.compiere.model.GridTab;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

/**
 *
 * @author <a href="mailto:hengsin@gmail.com">Low Heng Sin</a>
 *
 */
public interface IADTab extends UIPart {

	/**
	 * 获取选择的Tab序号
	 * @return selected tab index
	 */
	public int getSelectedIndex();

	/**
	 * 设置选择的Tab序号
	 * @param tab index
	 */
	public void setSelectedIndex(int i);

	/**
	 * 更新选择的序号？？
	 * @param oldTabIndex
	 * @param newTabIndex
	 * @return
	 */
	public boolean updateSelectedIndex(int oldTabIndex, int newTabIndex);

	/**
	 * 获取被选中的Tab Panel
	 * @return selected tab panel reference
	 */
	public IADTabPanel getSelectedTabpanel();

	/**
	 * 是否可以导航
	 * @param fromIndex
	 * @param toIndex
	 * @return boolean
	 */
	public boolean canNavigateTo(int fromIndex, int toIndex);

	/**
	 * 是否显示
	 * @param index
	 * @return boolean
	 */
	public boolean isDisplay(int index);

	/**
	 * 添加Tab
	 * @param tab
	 * @param tabPanel
	 */
	public void addTab(GridTab tab, IADTabPanel tabPanel);

	/**
	 * 获取Tab数量
	 * @return Number of tab
	 */
	public int getTabCount();

	/**
	 * @param e
	 */
	public void evaluate(DataStatusEvent e);

	/**
	 * 获取路径
	 * @return path to the active tab
	 */
	public String getPath();

	/**
	 * 添加选择监听
	 * @param listener
	 */
	public void addSelectionEventListener(EventListener<Event> listener);

	/**
	 * 获取选中Tab的component
	 * @return tab selection component
	 */
	public Component getTabSelectionComponent();

	/**
	 * 是否使用额外的选择
	 * @return boolean
	 */
	public boolean isUseExternalSelection();

	/**
	 * 获取Tab Panel
	 * @param index
	 * @return IADTabpanel
	 */
	public IADTabPanel getADTabpanel(int index);

	/**
	 * constant for tab placement
	 */
	public final int LEFT = 0;
	public final int RIGHT = 1;

	/**
	 * 设置Tab 所在的位置
	 * Set tab placement ( left or right )
	 * @param tabPlacement
	 */
	public void setTabplacement(int tabPlacement);

	/**
	 * 根据GridTab寻找Tab Panel
	 * @param gTab
	 * @return IADTabpanel or null if not found
	 */
	public IADTabPanel findADTabpanel(GridTab gTab);
}
