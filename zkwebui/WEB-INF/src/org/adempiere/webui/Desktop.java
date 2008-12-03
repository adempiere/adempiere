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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.apps.graph.BarGraphColumn;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.apps.ProcessDialog;
import org.adempiere.webui.apps.graph.WBarGraph;
import org.adempiere.webui.apps.wf.WFPanel;
import org.adempiere.webui.component.DesktopTabpanel;
import org.adempiere.webui.component.Tabbox;
import org.adempiere.webui.component.Tabpanel;
import org.adempiere.webui.component.ToolBarButton;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.event.MenuListener;
import org.adempiere.webui.exception.ApplicationException;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.HeaderPanel;
import org.adempiere.webui.panel.SidePanel;
import org.adempiere.webui.part.AbstractUIPart;
import org.adempiere.webui.part.WindowContainer;
import org.adempiere.webui.window.ADWindow;
import org.adempiere.webui.window.WTask;
import org.compiere.model.MAchievement;
import org.compiere.model.MGoal;
import org.compiere.model.MMeasureCalc;
import org.compiere.model.MMenu;
import org.compiere.model.MProjectType;
import org.compiere.model.MQuery;
import org.compiere.model.MRequestType;
import org.compiere.model.MRole;
import org.compiere.model.MTask;
import org.compiere.model.X_AD_Menu;
import org.compiere.model.X_PA_DashboardContent;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.WebDoc;
import org.zkoss.util.media.AMedia;
import org.zkoss.zhtml.Button;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.West;
import org.zkoss.zkmax.zul.Portalchildren;
import org.zkoss.zkmax.zul.Portallayout;
import org.zkoss.zul.Html;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Panelchildren;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabpanels;

/**
 * 
 * @author <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @author <a href="mailto:hengsin@gmail.com">Low Heng Sin</a>
 * @date Mar 2, 2007
 * @version $Revision: 0.10 $
 */
public class Desktop extends AbstractUIPart implements MenuListener, Serializable, IDesktop, EventListener
{

	public static final String WINDOWNO_ATTRIBUTE = "desktop.windowno";

	private static final long serialVersionUID = 9056511175189603883L;

	private static final CLogger logger = CLogger.getCLogger(Desktop.class);

    private transient ClientInfo clientInfo;
    
    private List<Object> windows;
    
    private Center windowArea;

	private Borderlayout layout;

	private WindowContainer windowContainer;
	
	private List<DashboardPanel> dashboardPanels;

	private MGoal[] m_goals = null;
	
	private List<MQuery> queryZoom = null;

	private Thread updateInfoThread;

	private UpdateInfoRunnable updateInfoRunnable;
	
	private static final String key = "queryZoom";

    public Desktop()
    {
    	windows = new ArrayList<Object>();
    	dashboardPanels = new ArrayList<DashboardPanel>();
    	m_goals = MGoal.getUserGoals(Env.getCtx(), Env.getAD_User_ID(Env.getCtx()));
    	queryZoom = new ArrayList<MQuery>();
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
        w.setTooltiptext("Application Menu");
        w.setFlex(true);
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

	private void createHomeTab() 
	{
        Tabpanel homeTab = new Tabpanel();
        windowContainer.addWindow(homeTab, Msg.getMsg(Env.getCtx(), "Home").replaceAll("&", ""), false);

        Portallayout layout = new Portallayout();
        layout.setWidth("100%");
        homeTab.appendChild(layout);
                
        // Dashboard content
        Portalchildren portalchildren = null;
        int currentColumnNo = 0;
        
        String sql = "SELECT COUNT(DISTINCT COLUMNNO) "
        	+ "FROM PA_DASHBOARDCONTENT "
        	+ "WHERE (AD_CLIENT_ID=0 OR AD_CLIENT_ID=?) AND ISACTIVE='Y'";
        
        int noOfCols = DB.getSQLValue(null, sql, 
        		Env.getAD_Client_ID(Env.getCtx()));
        
        int width = noOfCols <= 0 ? 100 : 100/noOfCols;

		sql =  "SELECT x.*, m.AD_MENU_ID "
			+ "FROM PA_DASHBOARDCONTENT x "
			+ "LEFT OUTER JOIN AD_MENU m ON x.AD_WINDOW_ID=m.AD_WINDOW_ID " 
			+ "WHERE (x.AD_CLIENT_ID=0 OR x.AD_CLIENT_ID=?) AND x.ISACTIVE='Y' "
			+ "ORDER BY x.COLUMNNO, x.AD_CLIENT_ID, x.LINE ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, Env.getAD_Client_ID(Env.getCtx()));
			rs = pstmt.executeQuery();
			
			while (rs.next()) 
			{	
	        	int columnNo = rs.getInt(X_PA_DashboardContent.COLUMNNAME_ColumnNo);
	        	if(portalchildren == null || currentColumnNo != columnNo)
	        	{
	        		portalchildren = new Portalchildren();
	                layout.appendChild(portalchildren);
	                portalchildren.setWidth(width + "%");
	                portalchildren.setStyle("padding: 5px");
	                
	                currentColumnNo = columnNo;
	        	}
	        	
	        	Panel panel = new Panel();
	        	panel.setStyle("margin-bottom:10px");
	        	panel.setTitle(rs.getString(X_PA_DashboardContent.COLUMNNAME_Name));
            	
	        	String description = rs.getString(X_PA_DashboardContent.COLUMNNAME_Description);
            	if(description != null)
            		panel.setTooltiptext(description);
	        	
            	panel.setCollapsible(true);
	        	panel.setBorder("normal");
	        	portalchildren.appendChild(panel);
	            Panelchildren content = new Panelchildren();
	            panel.appendChild(content);
	            
	            boolean panelEmpty = true;

	            // HTML content
	            String htmlContent = rs.getString(X_PA_DashboardContent.COLUMNNAME_HTML);
	            if(htmlContent != null)
	            {
		            StringBuffer result = new StringBuffer("<html><head>");
		            
		    		URL url = getClass().getClassLoader().
					getResource("org/compiere/images/PAPanel.css");
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
	        	int AD_Window_ID = rs.getInt(X_PA_DashboardContent.COLUMNNAME_AD_Window_ID);
	        	if(AD_Window_ID > 0)
	        	{
		        	int AD_Menu_ID = rs.getInt(X_AD_Menu.COLUMNNAME_AD_Menu_ID);
					ToolBarButton btn = new ToolBarButton(String.valueOf(AD_Menu_ID));
					MMenu menu = new MMenu(Env.getCtx(), AD_Menu_ID, null);
					btn.setLabel(menu.getName());
					btn.addEventListener(Events.ON_CLICK, this);
					content.appendChild(btn);
					panelEmpty = false;
	        	}
	            
	        	// Goal
	        	int PA_Goal_ID = rs.getInt(X_PA_DashboardContent.COLUMNNAME_PA_Goal_ID);
	        	if(PA_Goal_ID > 0)
	        	{
	        		StringBuffer result = new StringBuffer("<html><head>");
		            
		    		URL url = getClass().getClassLoader().
					getResource("org/compiere/images/PAPanel.css");
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
	        		result.append(goalsDetail(PA_Goal_ID, content));
	        		result.append("</div>\n</body>\n</html>\n</html>");

	            	Html html = new Html();
		            html.setContent(result.toString());
		            content.appendChild(html);
		            panelEmpty = false;
	        	}
	            
	            // ZUL file url
	        	String url = rs.getString(X_PA_DashboardContent.COLUMNNAME_ZulFilePath);
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
			                		dashboardPanels.add(dashboardPanel);
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
		} catch(Exception e) {
			logger.log(Level.WARNING, "Failed to create dashboard content", e);
		} finally {
			DB.close(rs, pstmt);
		}
        //
        
        //register as 0
        registerWindow(homeTab);
        
        if (!layout.getDesktop().isServerPushEnabled())
        	layout.getDesktop().enableServerPush(true);
        
        updateInfo();
        
        updateInfoRunnable = new UpdateInfoRunnable(layout.getDesktop());
        updateInfoThread = new Thread(updateInfoRunnable, "UpdateInfo");
        updateInfoThread.setDaemon(true);
        updateInfoThread.start();
	}
	
	private String goalsDetail(int AD_Table_ID, Panelchildren panel) 
	{
		String output = "";
		if (m_goals == null)
			return output;
		
		for (int i = 0; i < m_goals.length; i++) 
		{
			MMeasureCalc mc = MMeasureCalc.get(Env.getCtx(), m_goals[i].getMeasure().getPA_MeasureCalc_ID());
			
			if (AD_Table_ID == m_goals[i].getPA_Goal_ID()) 
			{
				output += "<table class=\"dataGrid\"><tr>\n<th colspan=\"3\" class=\"label\"><b>"
						+ m_goals[i].getName() + "</b></th></tr>\n";
				output += "<tr><td class=\"label\">Target</td><td colspan=\"2\" class=\"tdcontent\">"
						+ m_goals[i].getMeasureTarget() + "</td></tr>\n";
				output += "<tr><td class=\"label\">Actual</td><td colspan=\"2\" class=\"tdcontent\">"
						+ m_goals[i].getMeasureActual() + "</td></tr>\n";

				WBarGraph barPanel = new WBarGraph(m_goals[i]);
				BarGraphColumn[] bList = barPanel.getBarGraphColumnList();
				MQuery query = null;
				output += "<tr><td rowspan=\"" + bList.length
						+ "\" class=\"label\" valign=\"top\">"
						+ m_goals[i].getXAxisText() + "</td>\n";
				
				for (int k = 0; k < bList.length; k++) 
				{
					BarGraphColumn bgc = bList[k];
					if (k > 0)
						output += "<tr>";
					if (bgc.getAchievement() != null) // Single Achievement
					{
						MAchievement a = bgc.getAchievement();
						query = MQuery.getEqualQuery("PA_Measure_ID", a.getPA_Measure_ID());
					} 
					else if (bgc.getGoal() != null) // Multiple Achievements
					{
						MGoal goal = bgc.getGoal();
						query = MQuery.getEqualQuery("PA_Measure_ID", goal.getPA_Measure_ID());
					} 
					else if (bgc.getMeasureCalc() != null) // Document
					{
						mc = bgc.getMeasureCalc();
						query = mc.getQuery(m_goals[i].getRestrictions(false), bgc.getMeasureDisplay(), 
								bgc.getDate(), MRole.getDefault()); // logged in role
					} 
					else if (bgc.getProjectType() != null) // Document
					{
						MProjectType pt = bgc.getProjectType();
						query = pt.getQuery(m_goals[i].getRestrictions(false), bgc.getMeasureDisplay(), 
								bgc.getDate(), bgc.getID(), MRole.getDefault()); // logged in role
					} 
					else if (bgc.getRequestType() != null) // Document
					{
						MRequestType rt = bgc.getRequestType();
						query = rt.getQuery(m_goals[i].getRestrictions(false), bgc.getMeasureDisplay(), 
								bgc.getDate(), bgc.getID(), MRole.getDefault()); // logged in role
					}
					output += "<td class=\"tdcontent\">" + bgc.getLabel()
							+ "</td><td  class=\"tdcontent\">";
					if (query != null) {
						Button btn = new Button();
						btn.setId(String.valueOf(key + queryZoom.size()));
						btn.addEventListener(Events.ON_CLICK, this);
						btn.setVisible(false);
						panel.appendChild(btn);

						output += "<a class=\"hrefZoom\" id=\"" + key +
								+ queryZoom.size()
								+ "\" href=\"javascript:;\" onclick=\"$('" + btn.getUuid() + "').click()\">"
								+ bgc.getValue()
								+ "</a><br>\n";						
						
						queryZoom.add(query);
					} else {
						logger.info("Nothing to zoom to - " + bgc);
						output += bgc.getValue();
					}
					output += "</td></tr>";
				}
				output += "</tr>"
						+ "<tr><td colspan=\"3\">"
						+ m_goals[i].getDescription()
						+ "<br>"
						+ stripHtml(m_goals[i].getColorSchema()
								.getDescription(), true) + "</td></tr>"
						+ "</table>\n";
				bList = null;
				barPanel = null;
			}
		}
		
		return output;
	}
	
	private String stripHtml(String htmlString, boolean all) {
		htmlString = htmlString
		.replace("<html>", "")
		.replace("</html>", "")
		.replace("<body>", "")
		.replace("</body>", "")
		.replace("<head>", "")
		.replace("</head>", "");
		
		if (all)
			htmlString = htmlString
			.replace(">", "&gt;")
			.replace("<", "&lt;");
		return htmlString;
	}
	
	private class UpdateInfoRunnable implements Runnable {
		private org.zkoss.zk.ui.Desktop desktop;
		private boolean stop = false;
		UpdateInfoRunnable(org.zkoss.zk.ui.Desktop desktop) {
			this.desktop = desktop;
		}
		public void run() 
		{
			while(!stop) {
				try {
					Thread.sleep(60000); // Update every one minutes
				} catch (InterruptedException e1) {
					if (stop) break;
				}
				
				try {
					// get full control of desktop
					Executions.activate(desktop);
					try {
						updateInfo();						
					} catch (Error ex) {
						logger.log(Level.SEVERE, "UpdateInfo Thread error="+ex.getLocalizedMessage(), ex);
						break;
					} finally {
						// release full control of desktop
						Executions.deactivate(desktop);
					}
				} catch (Throwable e) {
					logger.log(Level.SEVERE, "UpdateInfo Thread error="+e.getLocalizedMessage(), e);
					break;
				}				
			}
		}
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
            else if(comp instanceof Button)
            {
            	String id = comp.getId();
            	String key = "queryZoom";
            	if(id.startsWith(key))
            	{
            		String ss = id.substring(key.length());
	        		int index = Integer.parseInt(String.valueOf(ss));
	            	if ((index >= 0) && (index < queryZoom.size()))
	                	AEnv.zoom(queryZoom.get(index));
            	}
            }
        }
    }

    private void updateInfo()
	{
    	for(int i = 0; i < dashboardPanels.size(); i++)
    		dashboardPanels.get(i).updateInfo();
    	
    	int noOfNotice = DPActivities.getNoticeCount();
    	int noOfRequest = DPActivities.getRequestCount();
    	int noOfWorkflow = DPActivities.getWorkflowCount();
    	int total = noOfNotice + noOfRequest + noOfWorkflow;
    	
		windowContainer.setTabTitle(0, "Home (" + total + ")", 
				"Notice : " + noOfNotice + ", Request : " + noOfRequest + ", Workflow Activities : " + noOfWorkflow);
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
        	openWindow(menu.getAD_Window_ID());
        }
        else if(menu.getAction().equals(MMenu.ACTION_Process) ||
        		menu.getAction().equals(MMenu.ACTION_Report))
        {
        	openProcessDialog(menu.getAD_Process_ID(), menu.isSOTrx());
        }
        else if(menu.getAction().equals(MMenu.ACTION_Form))
        {
        	openForm(menu.getAD_Form_ID());        	
        }
        else if(menu.getAction().equals(MMenu.ACTION_WorkFlow))
        {
        	openWorkflow(menu.getAD_Workflow_ID());
        }
        else if(menu.getAction().equals(MMenu.ACTION_Task))
        {
        	openTask(menu.getAD_Task_ID());
        }
        else
        {
            throw new ApplicationException("Menu Action not yet implemented: " + menu.getAction());
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
			windowContainer.addWindow(tabPanel, title, true);
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
		windowContainer.addWindow(tabPanel, form.getFormName(), true);
		
		return form;
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
		windowContainer.addWindow(tabPanel, p.getWorkflow().getName(), true);
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
			windowContainer.addWindow(tabPanel, adWindow.getTitle(), true);		
			return adWindow;
		} else {
			//user cancel 
			return null;
		}
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
    	wnd.createPart(tabPanel);
    	windowContainer.insertAfter(windowContainer.getSelectedTab(), tabPanel, wnd.getTitle(), true, true);
	}
    
    /**
     * @param win
     */
    public void showWindow(Window win) 
    {
    	String pos = win.getPosition();
    	this.showWindow(win, pos);
    }
    
    /**
     * @param win
     * @param pos
     */
   	public void showWindow(Window win, String pos)
	{
   		win.setPage(page);		
		Object objMode = win.getAttribute(Window.MODE_KEY);

		String mode = Window.MODE_MODAL;
		
		if (objMode != null)
		{
			mode = objMode.toString();
		}
		
		if (Window.MODE_MODAL.equals(mode))
		{
			showModal(win);
		}
		else if (Window.MODE_POPUP.equals(mode))
		{
			showPopup(win, pos);
		}
		else if (Window.MODE_OVERLAPPED.equals(mode))
		{
			showOverlapped(win, pos);
		}
		else if (Window.MODE_EMBEDDED.equals(mode))
		{
			showEmbedded(win);
		}
		else if (Window.MODE_HIGHLIGHTED.equals(mode))
		{
			showHighlighted(win, pos);
		}		
	}
   	
   	/**
   	 * 
   	 * @param win
   	 */
   	private void showModal(Window win)
   	{
		try
		{
			win.doModal();
		}
		catch(InterruptedException e)
		{
			
		}
			
	}
   	
   	/**
   	 * 
   	 * @param win
   	 * @param position
   	 */
   	private void showPopup(Window win, String position)
   	{
   		if (position == null)
   			win.setPosition("center");
   		else
   			win.setPosition(position);
   		
   		win.doPopup();
   	}
   	
   	/**
   	 * 
   	 * @param win
   	 * @param position
   	 */
	private void showOverlapped(Window win, String position)
   	{
		if (position == null)
			win.setPosition("center");
		else
			win.setPosition(position);
		
   		win.doOverlapped();
   	}
	
	/**
	 * 
	 * @param win
	 * @param position
	 */
	private void showHighlighted(Window win, String position)
   	{
		if (position == null)
			win.setPosition("center");
		else
			win.setPosition(position);
		
   		win.doHighlighted();
   	}

	/**
	 * 
	 * @param window
	 */
	private void showEmbedded(Window window)
   	{
		Tabpanel tabPanel = new Tabpanel();
    	window.setParent(tabPanel);
    	String title = window.getTitle();
    	window.setTitle(null);
    	if (Window.INSERT_NEXT.equals(window.getAttribute(Window.INSERT_POSITION_KEY)))
    		windowContainer.insertAfter(windowContainer.getSelectedTab(), tabPanel, title, true, true);
    	else
    		windowContainer.addWindow(tabPanel, title, true);
   	}
	
	/**
	 * @return clientInfo
	 */
	public ClientInfo getClientInfo() {
		return clientInfo;
	}

	/**
	 * 
	 * @param clientInfo
	 */
	public void setClientInfo(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}
	
	/**
	 * @param win
	 */
	public int registerWindow(Object win) {
		int retValue = windows.size();
		windows.add(win);
		return retValue;
	}
	
	/**
	 * @param WindowNo
	 */
	public void unregisterWindow(int WindowNo) {
		if (WindowNo < windows.size())
			windows.set(WindowNo, null);
		Env.clearWinContext(WindowNo);
	}
   	
    /**
     * 
     * @param WindowNo
     * @return Object
     */
	public Object findWindow(int WindowNo) {
		if (WindowNo < windows.size())
			return windows.get(WindowNo);
		else
			return null;
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
			Component component = panel.getFirstChild();
			Object att = component.getAttribute(WINDOWNO_ATTRIBUTE);
			if (att != null && (att instanceof Integer))
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
	 * 
	 * @param page
	 */
	public void setPage(Page page) {
		if (this.page != page) {
			layout.setPage(page);
			this.page = page;
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
		if (updateInfoThread != null && updateInfoThread.isAlive()) {
			updateInfoRunnable.stop = true;
			updateInfoThread.interrupt();
		}
	}
}
