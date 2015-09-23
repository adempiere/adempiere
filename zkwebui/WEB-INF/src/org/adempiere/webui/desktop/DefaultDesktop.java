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

package org.adempiere.webui.desktop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;

import org.adempiere.webui.apps.graph.WGraph;
import org.adempiere.webui.apps.graph.WPerformanceDetail;
import org.adempiere.webui.component.Tabpanel;
import org.adempiere.webui.component.ToolBarButton;
import org.adempiere.webui.dashboard.DPActivities;
import org.adempiere.webui.dashboard.DashboardPanel;
import org.adempiere.webui.dashboard.DashboardRunnable;
import org.adempiere.webui.event.MenuListener;
import org.adempiere.webui.panel.HeaderPanel;
import org.adempiere.webui.panel.SidePanel;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.theme.ThemeUtils;
import org.adempiere.webui.util.IServerPushCallback;
import org.adempiere.webui.util.ServerPushTemplate;
import org.adempiere.webui.util.UserPreference;
import org.compiere.model.I_AD_Menu;
import org.compiere.model.MDashboardContent;
import org.compiere.model.MGoal;
import org.compiere.model.X_PA_DashboardContent;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.zkoss.web.fn.ServletFns;
import org.zkoss.zk.au.out.AuScript;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.OpenEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Anchorchildren;
import org.zkoss.zul.Anchorlayout;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Center;
import org.zkoss.zul.Html;
import org.zkoss.zul.North;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Panelchildren;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.West;

/**
 *
 * Default desktop implementation.
 * @author <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @author <a href="mailto:hengsin@gmail.com">Low Heng Sin</a>
 * @date Mar 2, 2007
 * @version $Revision: 0.10 $
 */
public class DefaultDesktop extends TabbedDesktop implements MenuListener, Serializable, EventListener<Event>, IServerPushCallback
{
	/**
	 * generated serial version ID 
	 */
	private static final long serialVersionUID = -8203958978173990301L;

	private static final CLogger logger = CLogger.getCLogger(DefaultDesktop.class);
	
	private static final String dynamic_Dashboard_zulFilepath = "/zul/DynamicDashBoard.zul";

    private Center windowArea;

	private Borderlayout layout;

	private Thread dashboardThread;

	private DashboardRunnable dashboardRunnable;

	private int noOfNotice;

	private int noOfRequest;

	private int noOfWorkflow;

    public DefaultDesktop()
    {
    	super();
    	ThemeUtils.addSclass("ad-defaultdesktop", this);
    }

    protected Component doCreatePart(Component parent)
    {
    	HeaderPanel pnlHead = new HeaderPanel();

    	SidePanel pnlSide = new SidePanel();
    	pnlSide.getMenuPanel().addMenuListener(this);
    	
    	
        layout = new Borderlayout();
    	ThemeUtils.addSclass("ad-defaultdesktop-layout", layout);
        if (parent != null)
        {
        	layout.setParent(parent);
        }
        else
        	layout.setPage(page);

        dashboardRunnable = new DashboardRunnable(layout.getDesktop(), this);
        
        North n = new North();
        layout.appendChild(n);
        n.setCollapsible(false);
        ThemeUtils.addSclass("desktop-north",n);
        pnlHead.setParent(n);
        
        West w = new West();
        w.setId("desktop-left-column");
        layout.appendChild(w);
        ThemeUtils.addSclass("desktop-left-column",w);
        w.setWidth("300px");
        w.setCollapsible(true);
        w.setSplittable(true);
        w.setTitle(Util.cleanAmp(Msg.getMsg(Env.getCtx(), "Menu")));
        //w.setHflex("min");
        w.addEventListener(Events.ON_OPEN, new EventListener<Event>() {			
			@Override
			public void onEvent(Event event) throws Exception {
				OpenEvent oe = (OpenEvent) event;
				UserPreference pref = SessionManager.getSessionApplication().getUserPreference();
				pref.setProperty(UserPreference.P_MENU_COLLAPSED, !oe.isOpen());
				pref.savePreference();
			}
		});
        UserPreference pref = SessionManager.getSessionApplication().getUserPreference();
        boolean menuCollapsed= pref.isPropertyBool(UserPreference.P_MENU_COLLAPSED);
        w.setOpen(!menuCollapsed);
        pnlSide.setParent(w);

        windowArea = new Center();
        windowArea.setParent(layout);
        ThemeUtils.addSclass("desktop-center",windowArea);
        
        windowContainer.createPart(windowArea);

        createHomeTab();

        return layout;
    }

	private void createHomeTab()
	{
        Tabpanel homeTab = new Tabpanel();
        ThemeUtils.addSclass("desktop-tabpanel", homeTab);

        windowContainer.addWindow(homeTab, Util.cleanAmp(Msg.getMsg(Env.getCtx(), "Home")), false);

        Anchorlayout anchorLayout = new Anchorlayout();
        
        homeTab.appendChild(anchorLayout);        

        // Dashboard content
        Anchorchildren anchorchildren = null;
        int currentColumnNo = 0;
        
        try
		{
            int noOfCols = MDashboardContent.getForSessionColumnCount();
            int width = noOfCols <= 0 ? 100 : 100 / noOfCols;
            for (final MDashboardContent dp : MDashboardContent.getForSession())
			{
	        	int columnNo = dp.getColumnNo();
	        	if(anchorchildren == null || currentColumnNo != columnNo)
	        	{
	        		// New column
                    anchorchildren = new Anchorchildren();
	        		anchorLayout.appendChild(anchorchildren);
                                anchorchildren.setWidth(width + "%");
                                currentColumnNo = columnNo;
                        }
    
	        	Panel panel = new Panel();
	        	panel.setBorder("rounded");
	        	ThemeUtils.addSclass("ad-defaultdesktop-layout-panel", panel);
	        	panel.setTitle(dp.get_Translation(MDashboardContent.COLUMNNAME_Name));

	        	String description = dp.getDescription();
            	if(description != null)
            		panel.setTooltiptext(description);

            	panel.setCollapsible(dp.isCollapsible());
            	panel.setOpen( dp.isOpenByDefault() );
	        	anchorchildren.appendChild(panel);
	        	
	            Panelchildren content = new Panelchildren();
	            panel.appendChild(content);

	            boolean panelEmpty = true;

	            // HTML content
	            String htmlContent = dp.getHTML();
	            if(htmlContent != null)
	            {
		            StringBuffer result = new StringBuffer("<html><head>");

		    		URL url = getClass().getClassLoader().getResource("org/compiere/images/PAPanel.css"); // TODO: make theme dependent
					InputStreamReader ins;
					try {
						ins = new InputStreamReader(url.openStream());
						BufferedReader bufferedReader = new BufferedReader( ins );
						String cssLine;
						while ((cssLine = bufferedReader.readLine()) != null)
							result.append(cssLine + "\n");
					} catch (IOException e1) {
						logger.log(Level.SEVERE, e1.getLocalizedMessage(), e1);
					}

					result.append("</head><body><div class=\"content\">\n");

//	            	if(description != null)
//	            		result.append("<h2>" + description + "</h2>\n");
	            	result.append(stripHtml(htmlContent, false) + "<br>\n");
	            	result.append("</div>\n</body>\n</html>\n</html>");

		            Html html = new Html();
		            html.setContent(result.toString());
		            content.appendChild(html);
		            panelEmpty = false;
	            }

	        	// Window
	        	int AD_Window_ID = dp.getAD_Window_ID();
	        	if(AD_Window_ID > 0)
	        	{
		        	int AD_Menu_ID = dp.getAD_Menu_ID();
					ToolBarButton btn = new ToolBarButton(String.valueOf(AD_Menu_ID));
					I_AD_Menu menu = dp.getAD_Menu();
					btn.setLabel(menu.getName());
					btn.addEventListener(Events.ON_CLICK, this);
					content.appendChild(btn);
					panelEmpty = false;
	        	}

	            
	            //SmartBrowse
	            int AD_Browse_ID = dp.getAD_Browse_ID();

	        	// [11-01-2013]

	        	/*To handle DynamicDashboard. Added new if block
	        	* If the configuration is dynamic dash board, It finds by using IsDynamicDashboard value. If it is “Y�, Control
	        	* forwards to the Dynamic Dashboard corresponding zul file path, interns it forwards to DynamicDashboard class.
	        	* For this few result set values are set to context. */
				
	        	if ( AD_Browse_ID > 0 ) {
	        		
	        		try {
	        			Env.setContext( Env.getCtx(), "#AD_Browse_ID", dp.getAD_Browse_ID());//setting Tab ID to context
	        			Env.setContext( Env.getCtx(), "#PageSize", dp.getPageSize().intValue());
	        			Env.setContext( Env.getCtx(), "#Zoom_Tab_ID", dp.getZoom_Tab_ID());
	        			Env.setContext( Env.getCtx(),"#Zoom_Window_ID", dp.getZoom_Window_ID());
	        			Env.setContext( Env.getCtx(), "#Zoom_Field_ID", dp.getZoom_Field_ID());
	        			Env.setContext( Env.getCtx(), "#OnEvent", dp.getonevent());

	        			Component component = Executions.createComponents(dynamic_Dashboard_zulFilepath, content, null);

	        			if (component != null) {
	        				if (component instanceof DashboardPanel) {
	        					DashboardPanel dashboardPanel = (DashboardPanel) component;
	        					if (!dashboardPanel.getChildren().isEmpty()) {
	        						content.appendChild(dashboardPanel);
	        						dashboardRunnable.add(dashboardPanel);

	        						panelEmpty = false;
	        					}
	        				} else {
	        					content.appendChild(component);
	        					panelEmpty = false;
	        				}
	        			}
	        		} catch (Exception e) {
	        		logger.log(Level.WARNING, "Failed to create components. zul=" + dynamic_Dashboard_zulFilepath, e);
	        		}
	        	}

	        	// Goal
	        	int PA_Goal_ID = dp.getPA_Goal_ID();
	        	if(PA_Goal_ID > 0)
	        	{
	        		//link to open performance detail
	        		Toolbarbutton link = new Toolbarbutton();
		            link.setImage(ServletFns.resolveThemeURL("~./images/Zoom16.png"));
		            link.setAttribute("PA_Goal_ID", PA_Goal_ID);
		            link.addEventListener(Events.ON_CLICK, new EventListener<Event>() {

						public void onEvent(Event event) throws Exception {
							int PA_Goal_ID = (Integer)event.getTarget().getAttribute("PA_Goal_ID");
							MGoal goal = new MGoal(Env.getCtx(), PA_Goal_ID, null);
							new WPerformanceDetail(goal);
						}

		            });
		            content.appendChild(link);

		            String goalDisplay = dp.getGoalDisplay();
		            MGoal goal = new MGoal(Env.getCtx(), PA_Goal_ID, null);
		            WGraph graph = new WGraph(goal, 55, false, true, 
		            		!(X_PA_DashboardContent.GOALDISPLAY_Chart.equals(goalDisplay)),
		            		X_PA_DashboardContent.GOALDISPLAY_Chart.equals(goalDisplay));
		            content.appendChild(graph);
		            panelEmpty = false;
	        	}

	            // ZUL file url
	        	String url = dp.getZulFilePath();
	        	if(url != null)
	        	{
		        	try {
		                Component component = Executions.createComponents(url, content, null);
		                if(component != null)
		                {
		                	if (component instanceof DashboardPanel)
		                	{
			                	DashboardPanel dashboardPanel = (DashboardPanel) component;
			                	if (!dashboardPanel.getChildren().isEmpty()) {
		    	        			content.appendChild(dashboardPanel);
			                		dashboardRunnable.add(dashboardPanel);
			                		panelEmpty = false;
			                	}
		                	}
		                	else
		                	{
		                		content.appendChild(component);
		                		panelEmpty = false;
		                	}
		                }
					} catch (Exception e) {
						logger.log(Level.WARNING, "Failed to create components. zul="+url, e);
					}
	        	}

	        	if (panelEmpty)
	        		panel.detach();
	        }
		}
        catch (Exception e)
        {
			logger.log(Level.WARNING, "Failed to create dashboard content", e);
		}
        finally
        {
		}
        //

        //register as 0
        registerWindow(homeTab);

        if (!anchorLayout.getDesktop().isServerPushEnabled())
        	anchorLayout.getDesktop().enableServerPush(true);

        dashboardRunnable.refreshDashboard();

        dashboardThread = new Thread(dashboardRunnable, "UpdateInfo");
        dashboardThread.setDaemon(true);
        dashboardThread.start();
	}

    public void onEvent(Event event)
    {
        Component comp = event.getTarget();
        String eventName = event.getName();

        if(eventName.equals(Events.ON_CLICK))
        {
            if(comp instanceof ToolBarButton)
            {
            	ToolBarButton btn = (ToolBarButton) comp;

            	int menuId = 0;
            	try
            	{
            		menuId = Integer.valueOf(btn.getName());
            	}
            	catch (Exception e) {

				}

            	if(menuId > 0) onMenuSelected(menuId);
            }
        }
    }

    public void onServerPush(ServerPushTemplate template)
	{
    	noOfNotice = DPActivities.getNoticeCount();
    	noOfRequest = DPActivities.getRequestCount();
    	noOfWorkflow = DPActivities.getWorkflowCount();

    	template.execute(this);
	}

	/**
	 *
	 * @param page
	 */
	public void setPage(Page page) {
		if (this.page != page) {
			layout.setPage(page);
			this.page = page;
			if (dashboardThread != null && dashboardThread.isAlive()) {
				dashboardRunnable.stop();
				dashboardThread.interrupt();

				DashboardRunnable tmp = dashboardRunnable;
				dashboardRunnable = new DashboardRunnable(tmp, layout.getDesktop(), this);
				dashboardThread = new Thread(dashboardRunnable, "UpdateInfo");
		        dashboardThread.setDaemon(true);
		        dashboardThread.start();
			}
		}
	}

	/**
	 * Get the root component
	 * @return Component
	 */
	public Component getComponent() {
		return layout;
	}

	public void logout() {
		if (dashboardThread != null && dashboardThread.isAlive()) {
			dashboardRunnable.stop();
			dashboardThread.interrupt();
		}
	}

	public void updateUI() {
		int total = noOfNotice + noOfRequest + noOfWorkflow;
		windowContainer.setTabTitle(0, Msg.getMsg(Env.getCtx(), "Home").replaceAll("&", "")
				+ " (" + total + ")",
				Msg.translate(Env.getCtx(), "AD_Note_ID") + " : " + noOfNotice
				+ ", " + Msg.translate(Env.getCtx(), "R_Request_ID") + " : " + noOfRequest
				+ ", " + Msg.getMsg (Env.getCtx(), "WorkflowActivities") + " : " + noOfWorkflow);
	}
	
	private void autoHideMenu() {
		if (layout.getWest().isCollapsible() && !layout.getWest().isOpen())
		{
				
			/* TODO-evenos: zk 6 */
			String id = layout.getWest().getUuid();
			String script = "jq(zk.Widget.$('"+id+"').$n('colled')).click();";
			
			AuScript aus = new AuScript(layout.getWest(), script);
			Clients.response("autoHideWest", aus);
		}
	}

	@Override
	protected void preOpenNewTab() 
	{
		autoHideMenu();
	}	
}
