/******************************************************************************
 * Copyright (C) 2008 Low Heng Sin                                            *
 * Copyright (C) 2008 Idalica Corporation                                     *
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
package org.adempiere.webui.desktop;

import java.util.List;

import org.adempiere.model.MBrowse;
import org.adempiere.webui.apps.ProcessDialog;
import org.adempiere.webui.apps.wf.WFPanel;
import org.adempiere.webui.component.DesktopTabpanel;
import org.adempiere.webui.component.Tabbox;
import org.adempiere.webui.component.Tabpanel;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.CustomForm;
import org.adempiere.webui.part.WindowContainer;
import org.adempiere.webui.window.ADWindow;
import org.adempiere.webui.window.WTask;
import org.compiere.model.MQuery;
import org.compiere.model.MTask;
import org.compiere.util.Env;
import org.compiere.util.WebDoc;
import org.compiere.wf.MWorkflow;
import org.eevolution.form.WBrowser;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabpanels;

/**
 * A Tabbed MDI implementation
 * @author hengsin
 * @author victor.perez@e-evoluton.com, www.e-evolution.com 
 * 	<li>FR [ 3426137 ] Smart Browser
 *  https://sourceforge.net/tracker/?func=detail&aid=3426137&group_id=176962&atid=879335
 *
 */
public abstract class TabbedDesktop extends AbstractDesktop {

	protected WindowContainer windowContainer;

	public TabbedDesktop() {
		super();
		windowContainer = new WindowContainer();
	}

	/**
     *
     * @param processId
     * @param soTrx
     * @return ProcessDialog
     */
	public ProcessDialog openProcessDialog(int processId, boolean soTrx) {
		ProcessDialog pd = new ProcessDialog (processId, soTrx);
		if (pd.isValid()) {
			DesktopTabpanel tabPanel = new DesktopTabpanel();
			pd.setParent(tabPanel);
			String title = pd.getTitle();
			pd.setTitle(null);
			preOpenNewTab();
			windowContainer.addWindow(tabPanel, title, true);
			pd.afterInit();
		}
		return pd;
	}

    /**
     *
     * @param formId
     * @return ADWindow
     */
	public ADForm openForm(int formId) {
		ADForm form = ADForm.openForm(formId);

		DesktopTabpanel tabPanel = new DesktopTabpanel();
		form.setParent(tabPanel);
		//do not show window title when open as tab
		form.setTitle(null);
		form.setAttribute(WINDOWNO_ATTRIBUTE, form.getWindowNo());
		preOpenNewTab();
		windowContainer.addWindow(tabPanel, form.getFormName(), true);

		return form;
	}
	
	public CustomForm openBrowse(int browseId, Boolean isSOTrx)
	{
		MBrowse browse = new MBrowse(Env.getCtx() ,browseId, null);
		CustomForm ff =  WBrowser.openBrowse(0 , browseId, "", isSOTrx );
		DesktopTabpanel tabPanel = new DesktopTabpanel();
        ff.setParent(tabPanel);
        preOpenNewTab();
        windowContainer.addWindow(tabPanel, browse.getTitle(), true);
		return  ff;
	}

	/**
	 *
	 * @param workflow_ID
	 */
	public void openWorkflow(int workflow_ID) {
		WFPanel p = new WFPanel();
		p.load(workflow_ID);

		DesktopTabpanel tabPanel = new DesktopTabpanel();
		p.setParent(tabPanel);
		preOpenNewTab();
		windowContainer.addWindow(tabPanel, p.getWorkflow().get_Translation(MWorkflow.COLUMNNAME_Name), true);
	}

	/**
	 *
	 * @param windowId
	 * @return ADWindow
	 */
	public ADWindow openWindow(int windowId) {
		ADWindow adWindow = new ADWindow(Env.getCtx(), windowId);

		DesktopTabpanel tabPanel = new DesktopTabpanel();
		if (adWindow.createPart(tabPanel) != null) {
			preOpenNewTab();
			windowContainer.addWindow(tabPanel, adWindow.getTitle(), true);
			return adWindow;
		} else {
			//user cancel
			return null;
		}
	}

	/**
	 *
	 * @param windowId
     * @param query
	 * @return ADWindow
	 */
	public ADWindow openWindow(int windowId, MQuery query) {
    	ADWindow adWindow = new ADWindow(Env.getCtx(), windowId, query);

		DesktopTabpanel tabPanel = new DesktopTabpanel();
		if (adWindow.createPart(tabPanel) != null) {
			preOpenNewTab();
			windowContainer.addWindow(tabPanel, adWindow.getTitle(), true);
			return adWindow;
		} else {
			//user cancel
			return null;
		}
	}

	/**
     *
     * @param taskId
     */
	public void openTask(int taskId) {
		MTask task = new MTask(Env.getCtx(), taskId, null);
		new WTask(task.getName(), task);
	}

	/**
	 * @param url
	 */
	public void showURL(String url, boolean closeable)
    {
    	showURL(url, url, closeable);
    }

	/**
	 *
	 * @param url
	 * @param title
	 * @param closeable
	 */
    public void showURL(String url, String title, boolean closeable)
    {
    	Iframe iframe = new Iframe(url);
    	addWin(iframe, title, closeable);
    }

    /**
     * @param webDoc
     * @param title
     * @param closeable
     */
    public void showURL(WebDoc webDoc, String title, boolean closeable)
    {
    	Iframe iframe = new Iframe();

    	AMedia media = new AMedia(title, "html", "text/html", webDoc.toString().getBytes());
    	iframe.setContent(media);

    	addWin(iframe, title, closeable);
    }

    /**
     *
     * @param fr
     * @param title
     * @param closeable
     */
    private void addWin(Iframe fr, String title, boolean closeable)
    {
    	fr.setWidth("100%");
        fr.setHeight("100%");
        fr.setStyle("padding: 0; margin: 0; border: none; position: absolute");
        Window window = new Window();
        window.setWidth("100%");
        window.setHeight("100%");
        window.setStyle("padding: 0; margin: 0; border: none");
        window.appendChild(fr);
        window.setStyle("position: absolute");

        Tabpanel tabPanel = new Tabpanel();
    	window.setParent(tabPanel);
    	preOpenNewTab();
    	windowContainer.addWindow(tabPanel, title, closeable);
    }

    /**
     * @param AD_Window_ID
     * @param query
     */
    public void showZoomWindow(int AD_Window_ID, MQuery query)
    {
    	ADWindow wnd = new ADWindow(Env.getCtx(), AD_Window_ID, query);

    	DesktopTabpanel tabPanel = new DesktopTabpanel();
    	if (wnd.createPart(tabPanel) != null)
    	{
    		preOpenNewTab();
    		windowContainer.insertAfter(windowContainer.getSelectedTab(), tabPanel, wnd.getTitle(), true, true);
    	}
	}

    /**
     * @param AD_Window_ID
     * @param query
     * @deprecated
     */
    public void showWindow(int AD_Window_ID, MQuery query)
    {
    	openWindow(AD_Window_ID, query);
	}

	/**
	 *
	 * @param window
	 */
	protected void showEmbedded(Window window)
   	{
		Tabpanel tabPanel = new Tabpanel();
    	window.setParent(tabPanel);
    	String title = window.getTitle();
    	window.setTitle(null);
    	preOpenNewTab();
    	if (Window.INSERT_NEXT.equals(window.getAttribute(Window.INSERT_POSITION_KEY)))
    		windowContainer.insertAfter(windowContainer.getSelectedTab(), tabPanel, title, true, true);
    	else
    		windowContainer.addWindow(tabPanel, title, true);
   	}

	/**
	 * Close active tab
	 * @return boolean
	 */
	public boolean closeActiveWindow()
	{
		if (windowContainer.getSelectedTab() != null)
		{
			Tabpanel panel = (Tabpanel) windowContainer.getSelectedTab().getLinkedPanel();
			if(panel != null) {
				Component component = panel.getFirstChild();
				Object att = component.getAttribute(WINDOWNO_ATTRIBUTE);
	
				if ( windowContainer.closeActiveWindow() )
				{
					if (att != null && (att instanceof Integer))
					{
						unregisterWindow((Integer) att);
					}
					return true;
				}
				else
				{
					return false;
				} 
			}
		}
		return false;
	}

	/**
	 * @return Component
	 */
	public Component getActiveWindow()
	{
		return windowContainer.getSelectedTab().getLinkedPanel().getFirstChild();
	}

	/**
	 *
	 * @param windowNo
	 * @return boolean
	 */
	public boolean closeWindow(int windowNo)
	{
		Tabbox tabbox = windowContainer.getComponent();
		Tabpanels panels = tabbox.getTabpanels();
		List<?> childrens = panels.getChildren();
		for (Object child : childrens)
		{
			Tabpanel panel = (Tabpanel) child;
			Component component = panel.getFirstChild();
			Object att = component.getAttribute(WINDOWNO_ATTRIBUTE);
			if (att != null && (att instanceof Integer))
			{
				if (windowNo == (Integer)att)
				{
					Tab tab = panel.getLinkedTab();
					panel.getLinkedTab().onClose();
					if (tab.getParent() == null)
					{
						unregisterWindow(windowNo);
						return true;
					}
					else
					{
						return false;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * invoke before a new tab is added to the desktop
	 */
	protected void preOpenNewTab()
	{
	}
}
