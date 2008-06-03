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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.webui.apps.ProcessDialog;
import org.adempiere.webui.component.VerticalBox;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.event.MenuListener;
import org.adempiere.webui.exception.ApplicationException;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.FooterPanel;
import org.adempiere.webui.panel.HeaderPanel;
import org.adempiere.webui.panel.MainPanel;
import org.adempiere.webui.panel.SidePanel;
import org.adempiere.webui.window.ADWindow;
import org.compiere.model.MClient;
import org.compiere.model.MMenu;
import org.compiere.model.MQuery;
import org.compiere.model.MSysConfig;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.WebDoc;
import org.zkoss.util.media.AMedia;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Iframe;

/**
 * 
 * @author <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date Mar 2, 2007
 * @version $Revision: 0.10 $
 */
public class Desktop extends Window implements MenuListener
{
	private static final long serialVersionUID = 9056511175189603883L;

	private static final CLogger logger = CLogger.getCLogger(Desktop.class);

    private HeaderPanel 	pnlHead;
    
    private SidePanel         pnlSide;

    private MainPanel         pnlMain;

    private FooterPanel       pnlFooter;
    
    private ClientInfo clientInfo;
    
    private List<Window> windows;
    
    private CCache<Integer, ADWindow> windowCache;

    public Desktop()
    {
    	windows = new ArrayList<Window>();
    	windowCache = new CCache<Integer, ADWindow>("ZKWindowCache", 20);
    	windowCache.setExpireMinutes(10); // Remove the cached window after 10 mins
    	init();
    }

    private void init()
    {
    	pnlSide = new SidePanel();
    	pnlMain = new MainPanel();
    	pnlFooter = new FooterPanel();
    	pnlHead = new HeaderPanel();
         
        pnlSide.getMenuPanel().addMenuListener(this);
        
        VerticalBox verticalBox = new VerticalBox();
        verticalBox.setWidth("1200px");
        
        Hbox hbox = new Hbox();
        
        hbox.setWidth("1200px");
        hbox.setWidths("300px, 900px");
        hbox.appendChild(pnlSide);
        hbox.appendChild(pnlMain);

		String homeURL = MSysConfig.getValue("WEBUI_HOMEURL", "http://www.adempiere.com/");
        showURL(homeURL, "Home", false);
        
        verticalBox.appendChild(pnlHead);
        verticalBox.appendChild(hbox);
        
        //this.setBorder("normal");
        this.setStyle("background-color: #FAFAFA");
        //this.setWidth("100%");
        //this.setHeight("100%");
        this.appendChild(verticalBox);        
    }
    
    /**
     * Retrieves the Client website url
     * @return website url
     */
    private String getClientWebsiteURL()
    {
    	MClient client = MClient.get(Env.getCtx());
    	String defaultUrl = "http://www.adempiere.com";
    	
    	/* [ 1983672 ] Column AD_Client.WebSiteURL doesn't exist
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
    	*/
    	String url = defaultUrl;
    	
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
        	ADWindow wndMain = windowCache.get(wMenuId);
        	if (wndMain == null)
        	{
        		wndMain = new ADWindow(Env.getCtx(), menu.getAD_Window_ID());
        		windowCache.put(wMenuId, wndMain);
        	}
            pnlMain.addWindow(wndMain, wndMain.getTitle(), true);           
        }
        else if(menu.getAction().equals(MMenu.ACTION_Process) ||
        		menu.getAction().equals(MMenu.ACTION_Report))
        {
        	ProcessDialog pd = new ProcessDialog (menu.getAD_Process_ID(), menu.isSOTrx());
        	if (pd.isValid()) {
	        	pd.setPage(this.getPage());
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
			pnlMain.addWindow(form, form.getTitle(), true);
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
        fr.setHeight("650px");
        Window wndUrl = new Window();
    	//wndUrl.setHeight("650px");
        //wndUrl.setWidth("800px");
        wndUrl.appendChild(fr);
        pnlMain.addWindow(wndUrl, title, closeable);
    }
    
   
    public void showZoomWindow(int AD_Window_ID, MQuery query)
    {
    	ADWindow wnd = new ADWindow(Env.getCtx(), AD_Window_ID, query);
        Window window = new Window();
        
        window.appendChild(wnd);
        window.setBorder("normal");
        pnlMain.addWindow(window,wnd.getTitle(),true);
        
	}
    
   	public void showWindow(Window win)
	{
   		win.setPage(this.getPage());		
		Object objMode = win.getAttribute("mode");
		String pos = win.getPosition();

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
		
		win.setVisible(true);
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
	
	public int registerWindow(Window win) {
		int retValue = windows.size();
		windows.add(win);
		return retValue;
	}
	
	public void unregisterWindow(int WindowNo) {
		if (WindowNo < windows.size())
			windows.set(WindowNo, null);
	}
   	
   
	public Window findWindow(int WindowNo) {
		if (WindowNo < windows.size())
			return windows.get(WindowNo);
		else
			return null;
	}
	
	public void removeWindow()
	{
		pnlMain.removeWindow();
	}
}
