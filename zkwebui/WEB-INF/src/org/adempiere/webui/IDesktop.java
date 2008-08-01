package org.adempiere.webui;

import org.adempiere.webui.component.Window;
import org.compiere.model.MQuery;
import org.compiere.util.WebDoc;

public interface IDesktop {

	public ClientInfo getClientInfo();

	public void onMenuSelected(int nodeId);

	public int registerWindow(Object window);

	public void removeWindow();

	public void showURL(String url, boolean closeable);

	public void showURL(WebDoc doc, String string, boolean closeable);

	public void showWindow(Window win);
	
	public void showWindow(Window win, String position);

	public void showZoomWindow(int window_ID, MQuery query);

	public void unregisterWindow(int windowNo);
	
	public void showWindowInTabPanel(Window win); // Elaine 2008/07/30
}
