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

package org.adempiere.webui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.webui.apps.ProcessDialog;
import org.adempiere.webui.component.Tabpanel;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.event.MenuListener;
import org.adempiere.webui.exception.ApplicationException;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.HeaderPanel;
import org.adempiere.webui.panel.SidePanel;
import org.adempiere.webui.part.AbstractUIPart;
import org.adempiere.webui.part.WindowContainer;
import org.adempiere.webui.window.ADWindow;
import org.compiere.model.MClient;
import org.compiere.model.MMenu;
import org.compiere.model.MQuery;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.WebDoc;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.West;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Label;

/**
 * 
 * @author <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @author <a href="mailto:hengsin@gmail.com">Low Heng Sin</a>
 * @date Mar 2, 2007
 * @version $Revision: 0.10 $
 */
public class Desktop extends AbstractUIPart implements MenuListener, Serializable, IDesktop
{

	private static final long serialVersionUID = 9056511175189603883L;

	private static final CLogger logger = CLogger.getCLogger(Desktop.class);

    private transient ClientInfo clientInfo;
    
    private List<Object> windows;
    
    private Center windowArea;

	private Borderlayout layout;

	private WindowContainer windowContainer;

    public Desktop()
    {
    	windows = new ArrayList<Object>();
    }
    
    protected Component doCreatePart(Component parent)
    {
    	SidePanel pnlSide = new SidePanel();
    	HeaderPanel pnlHead = new HeaderPanel();
         
        pnlSide.getMenuPanel().addMenuListener(this);
        
        layout = new Borderlayout();
        if (parent != null)
        	layout.setParent(parent);
        else
        	layout.setPage(page);
        
        North n = new North();
        layout.appendChild(n);
        n.setCollapsible(false);
        pnlHead.setParent(n);
        
        West w = new West();
        layout.appendChild(w);
        w.setWidth("300px");
        w.setCollapsible(true);
        w.setSplittable(true);
//        w.setAutoscroll(true);
        pnlSide.setParent(w);
        
        windowArea = new Center();
        windowArea.setParent(layout);
        windowArea.setFlex(true);
//        windowArea.setAutoscroll(true);
        windowContainer = new WindowContainer();
        windowContainer.createPart(windowArea);        

        createHomeTab();
        
        return layout;
    }

	private void createHomeTab() {
		//TODO: dashboard
        Tabpanel homeTab = new Tabpanel();
        windowContainer.addWindow(homeTab, "Home", false);
        Label t = new Label();
        t.setValue("My Home!");
        t.setParent(homeTab);
        
        //register as 0
        registerWindow(homeTab);
	}
    
    /**
     * Retrieves the Client website url
     * @return website url
     */
    private String getClientWebsiteURL()
    {
    	MClient client = MClient.get(Env.getCtx());
    	String defaultUrl = "http://www.adempiere.com";
    	String url = (String)client.get_Value("WebSiteURL");
    	
    	if (url == null)
    	{
    		url = defaultUrl;
    	}
    	else if (!url.startsWith("http"))
    	{
    		logger.log(Level.SEVERE, "Website URL provided for the client is not valid!!!");
    		url = defaultUrl;
    	}
    	
    	return url;
    }
    
    /**
     * Event listener for menu item selection.
     * Identifies the action associated with the selected
     * menu item and acts accordingly.
     * 
     * @param	menuId	Identifier for the selected menu item
     * 
     * @throws	ApplicationException	If the selected menu action has yet 
     * 									to be implemented
     */
    public void onMenuSelected(int menuId)
    {
        MMenu menu = new MMenu(Env.getCtx(), menuId, null);
        if(menu == null)
        {
            return;
        }

        if(menu.getAction().equals(MMenu.ACTION_Window))
        {
        	Integer wMenuId = Integer.valueOf(menu.getAD_Window_ID());
        	ADWindow adWindow = new ADWindow(Env.getCtx(), menu.getAD_Window_ID());
        	
        	Tabpanel tabPanel = new Tabpanel();
        	adWindow.createPart(tabPanel);
        	windowContainer.addWindow(tabPanel, adWindow.getTitle(), true);
        }
        else if(menu.getAction().equals(MMenu.ACTION_Process) ||
        		menu.getAction().equals(MMenu.ACTION_Report))
        {
        	ProcessDialog pd = new ProcessDialog (menu.getAD_Process_ID(), menu.isSOTrx());
        	if (pd.isValid()) {
	        	pd.setPage(page);
	        	pd.setClosable(true);
	        	pd.setWidth("500px");
	        	try {
					pd.doModal();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }
        else if(menu.getAction().equals(MMenu.ACTION_Form))
        {
        	Window form = ADForm.openForm(menu.getAD_Form_ID());
        	
        	Tabpanel tabPanel = new Tabpanel();
        	form.setParent(tabPanel);
        	windowContainer.addWindow(tabPanel, form.getTitle(), true);        	
        }
        else
        {
            throw new ApplicationException("Menu Action not yet implemented: " + menu.getAction());
        }
    }
    
	public void showURL(String url, boolean closeable)
    {
    	showURL(url, url, closeable);
    }
    
    public void showURL(String url, String title, boolean closeable)
    {
    	Iframe iframe = new Iframe(url);
    	addWin(iframe, title, closeable);
    }
    
    public void showURL(WebDoc webDoc, String title, boolean closeable)
    {
    	Iframe iframe = new Iframe();
    	
    	AMedia media = new AMedia(title, "html", "text/html", webDoc.toString().getBytes());
    	iframe.setContent(media);
    	
    	addWin(iframe, title, closeable);
    }
    
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
    	windowContainer.addWindow(tabPanel, title, closeable);
    }
    
   
    public void showZoomWindow(int AD_Window_ID, MQuery query)
    {
    	ADWindow wnd = new ADWindow(Env.getCtx(), AD_Window_ID, query);
    	
    	Tabpanel tabPanel = new Tabpanel();
    	wnd.createPart(tabPanel);
    	windowContainer.addWindow(tabPanel, wnd.getTitle(), false);
	}
    
    public void showWindow(Window win) 
    {
    	String pos = win.getPosition();
    	this.showWindow(win, pos);
    }
    
   	public void showWindow(Window win, String pos)
	{
   		win.setPage(page);		
		Object objMode = win.getAttribute("mode");

		String mode = "modal";
		
		if (objMode != null)
		{
			mode = objMode.toString();
		}
		
		if ("modal".equals(mode))
		{
			showModal(win);
		}
		else if ("popup".equals(mode))
		{
			showPopup(win, pos);
		}
		else if ("overlapped".equals(mode))
		{
			showOverlapped(win, pos);
		}
		else if ("embedded".equals(mode))
		{
			showEmbedded(win, pos);
		}
		else if ("highlighted".equals(mode))
		{
			showHighlighted(win, pos);
		}
		
//		win.setVisible(true);
	}
   	
   	public void showModal(Window win)
   	{
		try
		{
			win.doModal();
		}
		catch(InterruptedException e)
		{
			
		}
			
	}
   	
   	public void showPopup(Window win, String position)
   	{
   		if (position == null)
   			win.setPosition("center");
   		else
   			win.setPosition(position);
   		
   		win.doPopup();
   	}
   	
	public void showOverlapped(Window win, String position)
   	{
		if (position == null)
			win.setPosition("center");
		else
			win.setPosition(position);
		
   		win.doOverlapped();
   	}
	
	public void showHighlighted(Window win, String position)
   	{
		if (position == null)
			win.setPosition("center");
		else
			win.setPosition(position);
		
   		win.doHighlighted();
   	}

	public void showEmbedded(Window win, String position)
   	{
		if (position == null)
			win.setPosition("center");
		else
			win.setPosition(position);
		
   		win.doEmbedded();
   	}
	
	public ClientInfo getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}
	
	public int registerWindow(Object win) {
		int retValue = windows.size();
		windows.add(win);
		return retValue;
	}
	
	public void unregisterWindow(int WindowNo) {
		if (WindowNo < windows.size())
			windows.set(WindowNo, null);
	}
   	
   
	public Object findWindow(int WindowNo) {
		if (WindowNo < windows.size())
			return windows.get(WindowNo);
		else
			return null;
	}
	
	public void removeWindow()
	{
		windowContainer.removeWindow();
	}
	
	public void setPage(Page page) {
		if (this.page != page) {
			layout.setPage(page);
			this.page = page;
		}
	}
	
	public Component getComponent() {
		return layout;
	}
}
