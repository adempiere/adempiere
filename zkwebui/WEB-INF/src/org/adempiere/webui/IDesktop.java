package org.adempiere.webui;

import org.adempiere.webui.apps.ProcessDialog;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.window.ADWindow;
import org.compiere.model.MQuery;
import org.compiere.util.WebDoc;

public interface IDesktop {

	/**
	 * 
	 * @return ClientInfo
	 */
	public ClientInfo getClientInfo();

	/**
	 * 
	 * @param nodeId
	 */
	public void onMenuSelected(int nodeId);

	/**
	 * 
	 * @param window
	 * @return windowNo
	 */
	public int registerWindow(Object window);
	
	/**
	 * 
	 * @param WindowNo
	 * @return Object
	 */
	public Object findWindow(int WindowNo);

	/**
	 * close active window
	 * @return boolean
	 */
	public boolean closeActiveWindow();
	
	/**
	 * 
	 * @param windowNo
	 * @return boolean
	 */
	public boolean closeWindow(int windowNo); 

	/**
	 * 
	 * @param url
	 * @param closeable
	 */
	public void showURL(String url, boolean closeable);

	/**
	 * 
	 * @param doc
	 * @param string
	 * @param closeable
	 */
	public void showURL(WebDoc doc, String string, boolean closeable);

	/**
	 * 
	 * @param win
	 */
	public void showWindow(Window win);
	
	/**
	 * 
	 * @param win
	 * @param position
	 */
	public void showWindow(Window win, String position);

	/**
	 * 
	 * @param window_ID
	 * @param query
	 */
	public void showZoomWindow(int window_ID, MQuery query);

	/**
	 * 
	 * @param windowNo
	 */
	public void unregisterWindow(int windowNo);
	
	/**
     * 
     * @param processId
     * @param soTrx
     * @return ProcessDialog
     */
	public ProcessDialog openProcessDialog(int processId, boolean soTrx);
	
	/**
     * 
     * @param formId
     * @return ADWindow
     */
	public ADForm openForm(int formId);
	
	/**
	 * 
	 * @param windowId
	 * @return ADWindow
	 */
	public ADWindow openWindow(int windowId);

	/**
	 * Open operating system task window
	 * @param task_ID
	 */
	public void openTask(int task_ID);

	/**
	 * 
	 * @param workflow_ID
	 */
	public void openWorkflow(int workflow_ID);
}
