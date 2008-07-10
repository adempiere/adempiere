package org.adempiere.webui.component;

/**
 * 
 * @author <a href="mailto:hengsin@gmail.com">Low Heng Sin</a>
 *
 */
public interface IADTabList {

	int getSelectedIndex();

	void setSelectedIndex(int curTabIndex);

	void refresh();

}
