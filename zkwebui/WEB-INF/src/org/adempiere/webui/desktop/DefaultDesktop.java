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
import org.adempiere.webui.util.IServerPushCallback;
import org.adempiere.webui.util.ServerPushTemplate;
import org.adempiere.webui.util.UserPreference;
import org.compiere.model.I_AD_Menu;
import org.compiere.model.MDashboardContent;
import org.compiere.model.MGoal;
import org.compiere.model.X_PA_DashboardContent;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.au.out.AuScript;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.OpenEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.West;
import org.zkoss.zkmax.zul.Portalchildren;
import org.zkoss.zkmax.zul.Portallayout;
import org.zkoss.zul.Html;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Panelchildren;
import org.zkoss.zul.Toolbarbutton;

/**
 *
 * Default desktop implementation.
 * @author <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @author <a href="mailto:hengsin@gmail.com">Low Heng Sin</a>
 * @date Mar 2, 2007
 * @version $Revision: 0.10 $
 */
public class DefaultDesktop extends TabbedDesktop implements MenuListener, Serializable, EventListener, IServerPushCallback
{
	/**
	 * generated serial version ID 
	 */
	private static final long serialVersionUID = -8203958978173990301L;

	private static final CLogger logger = CLogger.getCLogger(DefaultDesktop.class);

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
    }

    protected Component doCreatePart(Component parent)
    {
    	SidePanel pnlSide = new SidePanel();
    	HeaderPanel pnlHead = new HeaderPanel();

        pnlSide.getMenuPanel().addMenuListener(this);

        layout = new Borderlayout();
        if (parent != null)
        {
        	layout.setParent(parent);
        	layout.setWidth("100%");
        	layout.setHeight("100%");
        	layout.setStyle("position: absolute");
        }
        else
        	layout.setPage(page);

        dashboardRunnable = new DashboardRunnable(layout.getDesktop(), this);

        North n = new North();
        layout.appendChild(n);
        n.setCollapsible(false);
        pnlHead.setParent(n);

        West w = new West();
        layout.appendChild(w);
        w.setWidth("300px");
        w.setCollapsible(true);
        w.setSplittable(true);
        w.setTitle("Menu");
        w.setFlex(true);
        w.addEventListener(Events.ON_OPEN, new EventListener() {			
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
        windowArea.setFlex(true);

        windowContainer.createPart(windowArea);

        createHomeTab();

        return layout;
    }

	private void createHomeTab()
	{
        Tabpanel homeTab = new Tabpanel();
        windowContainer.addWindow(homeTab, Msg.getMsg(Env.getCtx(), "Home").replaceAll("&", ""), false);

        Portallayout portalLayout = new Portallayout();
        portalLayout.setWidth("100%");
        portalLayout.setHeight("100%");
        portalLayout.setStyle("position: absolute; overflow: auto");
        homeTab.appendChild(portalLayout);

        // Dashboard content
        Portalchildren portalchildren = null;
        int currentColumnNo = 0;

        int noOfCols = 0;
        int width = 0;

        try
		{
            noOfCols = MDashboardContent.getForSessionColumnCount();
            width = noOfCols <= 0 ? 100 : 100 / noOfCols;
            for (final MDashboardContent dp : MDashboardContent.getForSession())
			{
	        	int columnNo = dp.getColumnNo();
	        	if(portalchildren == null || currentColumnNo != columnNo)
	        	{
	        		portalchildren = new Portalchildren();
	                portalLayout.appendChild(portalchildren);
	                portalchildren.setWidth(width + "%");
	                portalchildren.setStyle("padding: 5px");

	                currentColumnNo = columnNo;
	        	}

	        	Panel panel = new Panel();
	        	panel.setStyle("margin-bottom:10px");
	        	panel.setTitle(dp.getName());

	        	String description = dp.getDescription();
            	if(description != null)
            		panel.setTooltiptext(description);

            	panel.setCollapsible(dp.isCollapsible());

	        	panel.setBorder("normal");
	        	portalchildren.appendChild(panel);
	            Panelchildren content = new Panelchildren();
	            panel.appendChild(content);

	            boolean panelEmpty = true;

	            // HTML content
	            String htmlContent = dp.getHTML();
	            if(htmlContent != null)
	            {
		            StringBuffer result = new StringBuffer("<html><head>");

		    		URL url = getClass().getClassLoader().getResource("org/compiere/images/PAPanel.css");
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

	        	// Goal
	        	int PA_Goal_ID = dp.getPA_Goal_ID();
	        	if(PA_Goal_ID > 0)
	        	{
	        		//link to open performance detail
	        		Toolbarbutton link = new Toolbarbutton();
		            link.setImage("/images/Zoom16.png");
		            link.setAttribute("PA_Goal_ID", PA_Goal_ID);
		            link.addEventListener(Events.ON_CLICK, new EventListener() {

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

        if (!portalLayout.getDesktop().isServerPushEnabled())
        	portalLayout.getDesktop().enableServerPush(true);

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
			//using undocumented js api, need to be retested after every version upgrade
			String id = layout.getWest().getUuid() + "!real";
			String btn = layout.getWest().getUuid() + "!btn";
			String script = "zk.show('" + id + "', false);";
			script += "$e('"+id+"')._isSlide = false;";
			script += "$e('"+id+"')._lastSize = null;";
			script += "$e('"+btn+"').style.display = '';";
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
