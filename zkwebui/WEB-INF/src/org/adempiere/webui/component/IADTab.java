package org.adempiere.webui.component;

import org.adempiere.webui.panel.ADTabpanel;
import org.adempiere.webui.part.UIPart;
import org.compiere.model.DataStatusEvent;
import org.compiere.model.GridTab;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;

/**
 * 
 * @author <a href="mailto:hengsin@gmail.com">Low Heng Sin</a>
 *
 */
public interface IADTab extends UIPart {

	public int getSelectedIndex();
	
	public boolean canNavigateTo(int fromIndex, int toIndex);

	public void addTab(GridTab tab, ADTabpanel tabPanel);

	public void setSelectedIndex(int i);

	public int getTabCount();

	public void evaluate(DataStatusEvent e);

	public boolean updateSelectedIndex(int oldTabIndex, int newTabIndex);

	public ADTabpanel getSelectedTabpanel();

	public String getPath();

	public void addSelectionEventListener(
			EventListener listener);

	public Component getTabSelectionComponent();
	
	public boolean isUseExternalSelection();
}
