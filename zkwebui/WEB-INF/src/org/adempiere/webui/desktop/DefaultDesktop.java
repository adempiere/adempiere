/******************************************************************************
 * Product: Posterita Ajax UI 												  *
 * Copyright (C) 2007 Posterita Ltd.  All Rights Reserved.                    *
 * Copyright (C) 2008-2021 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/

package org.adempiere.webui.desktop;

import org.adempiere.core.domains.models.I_AD_Menu;
import org.adempiere.core.domains.models.X_PA_DashboardContent;
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
import org.compiere.model.MDashboardContent;
import org.compiere.model.MGoal;
import org.compiere.model.MRole;
import org.compiere.model.MSysConfig;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.zkoss.zk.au.out.AuScript;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.util.logging.Level;

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
	
	private static final String dynamic_Dashboard_zulFilepath = "/zul/DynamicDashBoard.zul";

    private Center windowArea;

	private Borderlayout layout;

	private Portallayout portalLayout;

	private DashboardRunnable dashboardRunnable;

	private int noOfNotice;

	private int noOfRequest;

	private int noOfWorkflow;

    public DefaultDesktop()
    {
    	super();
    }

    DashboardRunnable createDashboardRunnable() {
		return new DashboardRunnable(layout.getDesktop(), this);
    }

    HeaderPanel createHeaderPanel() {
    
        return new HeaderPanel();
    
    }

    SidePanel createSidePanel() {
    
        return new SidePanel();
    
    }

    UserPreference getUserPreference() {
    
        return SessionManager.getUserPreference();
    
    }

    protected Component doCreatePart(Component parent)
    {
    	SidePanel pnlSide = createSidePanel();
    	HeaderPanel pnlHead = createHeaderPanel();

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

        dashboardRunnable = createDashboardRunnable();

        North n = new North();
        n.setSplittable(true);
        n.setCollapsible(false);
        layout.appendChild(n);
        pnlHead.setParent(n);

        West w = new West();
        layout.appendChild(w);
        w.setWidth("300px");
        w.setCollapsible(true);
        w.setSplittable(true);
        w.setTitle(Util.cleanAmp(Msg.getMsg(Env.getCtx(), "Menu")));
        w.setFlex(true);
        w.addEventListener(Events.ON_OPEN, new EventListener() {			
			@Override
			public void onEvent(Event event) throws Exception {
				OpenEvent oe = (OpenEvent) event;
				UserPreference pref = getUserPreference();
				pref.setProperty(UserPreference.P_MENU_COLLAPSED, !oe.isOpen());
				pref.savePreference();
			}
		});
        UserPreference pref = getUserPreference();
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

	private void createHomeTab() {
		Tabpanel homeTab = new Tabpanel();
		windowContainer.addWindow(homeTab, Msg.getMsg(Env.getCtx(), "Home").replaceAll("&", ""), false);

		portalLayout = createPortalLayout();
		portalLayout.setWidth("100%");
		portalLayout.setHeight("100%");
		portalLayout.setStyle("position: absolute; overflow: auto");
		homeTab.appendChild(portalLayout);

		// Dashboard content
		Portalchildren portalChildren = null;
		int currentColumnNo = 0;
		int noOfColumns = 0;
		int width = 0;

		try {

			int counter = 0;
			String[] size = null;
			String proportion = getSysConfigValue("ZK_DASHBOARD_COLUMN_WIDTH_PROPORTION");
			if (!Util.isEmpty(proportion, true))
				size = proportion.split(",");

			noOfColumns = getSessionColumnCount();
			width = noOfColumns <= 0 ? 100 : 100 / noOfColumns;
			for (final MDashboardContent dashboardContent : getDashboardContent()) {
				MRole role = getDefaultRole();
				if (Boolean.FALSE.equals(role.getDashboardAccess(
				        dashboardContent.getPA_DashboardContent_ID())))
				    continue;
				
				int columnNo = dashboardContent.getColumnNo();
				if (portalChildren == null || currentColumnNo != columnNo) {
					String columnWidth = "" + width;
					if (size != null && size.length > 0 && size.length > counter && !Util.isEmpty(size[counter], true))
						columnWidth = size[counter];
					portalChildren = new Portalchildren();
					portalLayout.appendChild(portalChildren);
					portalChildren.setWidth(columnWidth.trim() + "%");
					portalChildren.setStyle("padding: 5px");

					currentColumnNo = columnNo;
					counter++;
				}


				Panel panel = new Panel();
				panel.setStyle("margin-bottom:10px");
				panel.setTitle(dashboardContent.get_Translation(MDashboardContent.COLUMNNAME_Name));

				String description = dashboardContent.get_Translation(MDashboardContent.COLUMNNAME_Description);
				if (description != null)
					panel.setTooltiptext(description);

				panel.setCollapsible(dashboardContent.isCollapsible());
				panel.setOpen(dashboardContent.isOpenByDefault());
				panel.setBorder("normal");
				portalChildren.appendChild(panel);
				Panelchildren content = new Panelchildren();
				panel.appendChild(content);
				boolean panelEmpty = true;

				// HTML content
				String htmlContent = dashboardContent.getHTML();
				if (htmlContent != null) {
					StringBuffer result = new StringBuffer("<html><head>");

					URL url = getClass().getClassLoader().getResource("org/compiere/images/PAPanel.css");
					InputStreamReader inputStreamReader = null;
					try {
						inputStreamReader = new InputStreamReader(url.openStream());
						BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
						String cssLine;
						result.append("<style type=\"text/css\">");
						while ((cssLine = bufferedReader.readLine()) != null)
							result.append(cssLine + "\n");
						result.append("</style>");
					} catch (IOException e1) {
						getLogger().log(Level.SEVERE, e1.getLocalizedMessage(), e1);
					} finally {
						if (inputStreamReader != null) {
							try {
								inputStreamReader.close();
							} catch (Exception e) {
							}
							inputStreamReader = null;
						}
					}
					result.append("</head><body><div class=\"content\">\n");
					result.append(stripHtml(htmlContent, false) + "<br>\n");
					result.append("</div>\n</body>\n</html>\n</html>");

					Html html = new Html();
					html.setContent(result.toString());
					content.appendChild(html);
					panelEmpty = false;
				}

				// Window
				int windowId = dashboardContent.getAD_Window_ID();
				if (windowId > 0) {
					int menuId = dashboardContent.getAD_Menu_ID();
					ToolBarButton btn = new ToolBarButton(String.valueOf(menuId));
					I_AD_Menu menu = dashboardContent.getAD_Menu();
					btn.setLabel(menu.getName());
					btn.addEventListener(Events.ON_CLICK, this);
					content.appendChild(btn);
					panelEmpty = false;
				}


				//SmartBrowse
				int browseId = dashboardContent.getAD_Browse_ID();
				/*To handle DynamicDashboard. Added new if block
				 * If the configuration is dynamic dash board, It finds by using IsDynamicDashboard value. If it is “Y”, Control
				 * forwards to the Dynamic Dashboard corresponding zul file path, interns it forwards to DynamicDashboard class.
				 * For this few result set values are set to context. */

				if (browseId > 0) {
					try {
						Env.setContext(Env.getCtx(), "#AD_Browse_ID", dashboardContent.getAD_Browse_ID());
						Env.setContext(Env.getCtx(), "#PageSize", dashboardContent.getPageSize().intValue());
						Env.setContext(Env.getCtx(), "#Zoom_Tab_ID", dashboardContent.getZoom_Tab_ID());
						Env.setContext(Env.getCtx(), "#Zoom_Window_ID", dashboardContent.getAD_Window_ID());
						Env.setContext(Env.getCtx(), "#Zoom_Field_ID", dashboardContent.getZoom_Field_ID());
						Env.setContext(Env.getCtx(), "#OnEvent", dashboardContent.getonevent());
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
						getLogger().log(Level.WARNING, "Failed to create components. zul=" + dynamic_Dashboard_zulFilepath, e);
					}
				}

				// Goal
				int goalId = dashboardContent.getPA_Goal_ID();
				if (goalId > 0) {
					//link to open performance detail
					Toolbarbutton link = new Toolbarbutton();
					link.setImage("/images/Zoom16.png");
					link.setAttribute("PA_Goal_ID", goalId);
					link.addEventListener(Events.ON_CLICK, new EventListener() {
						public void onEvent(Event event) throws Exception {
							int PA_Goal_ID = (Integer) event.getTarget().getAttribute("PA_Goal_ID");
							MGoal goal = new MGoal(Env.getCtx(), PA_Goal_ID, null);
							new WPerformanceDetail(goal);
						}
					});
					content.appendChild(link);

					String goalDisplay = dashboardContent.getGoalDisplay();
					MGoal goal = new MGoal(Env.getCtx(), goalId, null);
					WGraph graph = new WGraph(goal, 55, false, true,
							!(X_PA_DashboardContent.GOALDISPLAY_Chart.equals(goalDisplay)),
							X_PA_DashboardContent.GOALDISPLAY_Chart.equals(goalDisplay));
					content.appendChild(graph);
					panelEmpty = false;
				}

				// ZUL file url
				String url = dashboardContent.getZulFilePath();
				if (url != null) {
					try {
						Component component = Executions.createComponents(url, content, null);
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
						getLogger().log(Level.WARNING, "Failed to create components. zul=" + url, e);
					}
				}

				if (panelEmpty)
					panel.detach();
			}
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "Failed to create dashboard content", e);
		} finally {
		}
		//

		//register as 0
		registerWindow(homeTab);

		if (!portalLayout.getDesktop().isServerPushEnabled())
			portalLayout.getDesktop().enableServerPush(true);

		dashboardRunnable.refreshDashboard();
		dashboardRunnable.start();
	}

    Portallayout createPortalLayout() {
        return new Portallayout();
    }

    CLogger getLogger() {
        return logger;
    }

    MRole getDefaultRole() {
        return MRole.getDefault(Env.getCtx(), false);
    }

    MDashboardContent[] getDashboardContent() {
        return MDashboardContent.getForSession();
    }

    int getSessionColumnCount() {
        return MDashboardContent.getForSessionColumnCount();
    }

    String getSysConfigValue(String sysConfigName) {
        return MSysConfig.getValue(sysConfigName, Env.getAD_Client_ID(Env.getCtx()));
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
		}
		if (dashboardRunnable != null && dashboardRunnable.isRunning()) {
				dashboardRunnable.interrupt();
				DashboardRunnable tmp = dashboardRunnable;
				dashboardRunnable = new DashboardRunnable(tmp, layout.getDesktop(), this);
				dashboardRunnable.start();
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
		if (dashboardRunnable != null && dashboardRunnable.isRunning()) {
			dashboardRunnable.interrupt();
			if (portalLayout != null) {
				Desktop desktop = portalLayout.getDesktop();
				if (desktop != null) {
					desktop.enableServerPush(false);
					portalLayout = null;
				}
			}
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
