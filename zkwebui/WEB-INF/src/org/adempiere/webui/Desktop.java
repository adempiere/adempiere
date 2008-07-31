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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.apps.ProcessDialog;
import org.adempiere.webui.apps.graph.WPAPanel;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Tabpanel;
import org.adempiere.webui.component.ToolBarButton;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.event.MenuListener;
import org.adempiere.webui.exception.ApplicationException;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.HeaderPanel;
import org.adempiere.webui.panel.InfoPanel;
import org.adempiere.webui.panel.SidePanel;
import org.adempiere.webui.part.AbstractUIPart;
import org.adempiere.webui.part.WindowContainer;
import org.adempiere.webui.window.ADWindow;
import org.adempiere.webui.window.InfoSchedule;
import org.compiere.model.MClient;
import org.compiere.model.MMenu;
import org.compiere.model.MQuery;
import org.compiere.model.MRole;
import org.compiere.model.MTree;
import org.compiere.model.MTreeNode;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.WebDoc;
import org.zkoss.lang.Threads;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.East;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.West;
import org.zkoss.zul.Box;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Image;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;
import org.zkoss.zul.Vbox;

/**
 * 
 * @author <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @author <a href="mailto:hengsin@gmail.com">Low Heng Sin</a>
 * @date Mar 2, 2007
 * @version $Revision: 0.10 $
 */
public class Desktop extends AbstractUIPart implements MenuListener, Serializable, IDesktop, EventListener
{

	private static final long serialVersionUID = 9056511175189603883L;

	private static final CLogger logger = CLogger.getCLogger(Desktop.class);

    private transient ClientInfo clientInfo;
    
    private List<Object> windows;
    
    private Center windowArea;

	private Borderlayout layout;

	private WindowContainer windowContainer;

	private Button btnNotice, btnRequest, btnWorkflow;
	
	private int m_AD_Tree_ID;
	
	private Box bxFav;

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

        Hbox hbox = new Hbox();
        hbox.setStyle("margin: 5px");
        hbox.setSpacing("5px");
        hbox.setWidths("50%, 50%");

        Vbox vbCol1 = new Vbox();    
        hbox.appendChild(vbCol1);
        vbCol1.setWidth("100%");

        Groupbox gbxFav = new Groupbox();
        vbCol1.appendChild(gbxFav);
        Caption caption = new Caption("Favourites");
        // Elaine 2008/07/24
        Image img = new Image("/images/Delete24.gif");
        caption.appendChild(img);
        img.setAlign("right");
        img.setDroppable("deleteFav");
        img.addEventListener(Events.ON_DROP, this);
        //
        gbxFav.appendChild(caption);
        gbxFav.appendChild(createFavouritesPanel());
        gbxFav.setMold("3d");
        gbxFav.setClosable(true);
        // Elaine 2008/07/24
        gbxFav.setDroppable("favourite"); 
        gbxFav.addEventListener(Events.ON_DROP, this);
        //
        
        Groupbox gbxView = new Groupbox();
        vbCol1.appendChild(gbxView);
        gbxView.appendChild(new Caption("Views"));
        gbxView.appendChild(createViewPanel());
        gbxView.setMold("3d");
        gbxView.setClosable(true);

        Vbox vbCol2 = new Vbox();    
        hbox.appendChild(vbCol2);
        vbCol2.setWidth("100%");
        
        Groupbox gbxCalendar = new Groupbox();
        vbCol2.appendChild(gbxCalendar);
        gbxCalendar.appendChild(new Caption("Calendar"));
        Iframe iframe = new Iframe("http://www.google.com/calendar/embed?showTitle=0&showTabs=0&height=300&wkst=1&bgcolor=%23FFFFFF&color=%232952A3");
        iframe.setStyle("border-width: 0;");
        iframe.setScrolling("no");
        iframe.setWidth("300px");
        iframe.setHeight("300px");
        gbxCalendar.appendChild(iframe);
        gbxCalendar.setStyle("margin-left: 5px; margin-right: 5px; margin-top: 0px; margin-bottom: 0px;");
        gbxCalendar.setMold("3d");
        gbxCalendar.setClosable(true);
        
        final Groupbox gbxAct = new Groupbox();
        vbCol2.appendChild(gbxAct);
        gbxAct.appendChild(new Caption("Activities"));            
        gbxAct.appendChild(createActivitiesPanel());
        gbxAct.setStyle("margin-left: 5px; margin-right: 5px; margin-top: 0px; margin-bottom: 0px;");
        gbxAct.setMold("3d");
        gbxAct.setClosable(true);
        
        Borderlayout borderlayout = new Borderlayout();
        homeTab.appendChild(borderlayout);
        borderlayout.setWidth("100%");
        borderlayout.setHeight("100%");
        borderlayout.setStyle("position: absolute");
        
        WPAPanel paPanel = WPAPanel.get();
        if (paPanel != null) {
	        East east = new East();
	        borderlayout.appendChild(east);        
	        east.appendChild(paPanel);
	        east.setWidth("205px");
        }
        
        Center center = new Center();
        borderlayout.appendChild(center);
        center.appendChild(hbox);
        center.setFlex(true);
        
        //register as 0
        registerWindow(homeTab);
        
        // enable server push for this desktop
        if (!gbxAct.getDesktop().isServerPushEnabled())
        	gbxAct.getDesktop().enableServerPush(true);
        
        updateInfo();
        
        new Thread(new UpdateInfoRunnable(gbxAct.getDesktop())).start();
	}
	
	private class UpdateInfoRunnable implements Runnable {
		private org.zkoss.zk.ui.Desktop desktop;
		UpdateInfoRunnable(org.zkoss.zk.ui.Desktop desktop) {
			this.desktop = desktop;
		}
		public void run() 
		{
			try {
				// get full control of desktop
				Executions.activate(desktop);
				try {
					updateInfo();
					Threads.sleep(500);// Update each 0.5 seconds
				} catch (Error ex) {
					throw ex;
				} finally {
					// release full control of desktop
					Executions.deactivate(desktop);
				}
			} catch (Exception e) {
				logger.log(Level.WARNING, "Failed to run NRW", e);
			}
		}
	}
	
	private Box createActivitiesPanel()
	{
		Vbox vbox = new Vbox();
		
        btnNotice = new Button();
        vbox.appendChild(btnNotice);
        btnNotice.setLabel("Notice : 0");
        btnNotice.setTooltiptext("Notice");
        btnNotice.setImage("/images/GetMail16.gif");
        int AD_Menu_ID = DB.getSQLValue(null, "SELECT AD_Menu_ID FROM AD_Menu WHERE Name = 'Notice' AND IsSummary = 'N'");
        btnNotice.setName(String.valueOf(AD_Menu_ID));
        btnNotice.addEventListener(Events.ON_CLICK, this);
        
        btnRequest = new Button();
        vbox.appendChild(btnRequest);
        btnRequest.setLabel("Request : 0");
        btnRequest.setTooltiptext("Request");
        btnRequest.setImage("/images/Request16.gif");
        AD_Menu_ID = DB.getSQLValue(null, "SELECT AD_Menu_ID FROM AD_Menu WHERE Name = 'Request' AND IsSummary = 'N'");
        btnRequest.setName(String.valueOf(AD_Menu_ID));
        btnRequest.addEventListener(Events.ON_CLICK, this);
        
        btnWorkflow = new Button();
        vbox.appendChild(btnWorkflow);
        btnWorkflow.setLabel("Workflow Activities : 0");
        btnWorkflow.setTooltiptext("Workflow Activities");
        btnWorkflow.setImage("/images/Assignment16.gif");
        AD_Menu_ID = DB.getSQLValue(null, "SELECT AD_Menu_ID FROM AD_Menu WHERE Name = 'Workflow Activities' AND IsSummary = 'N'");
        btnWorkflow.setName(String.valueOf(AD_Menu_ID));
        btnWorkflow.addEventListener(Events.ON_CLICK, this);
        
        return vbox;
	}

	private Box createFavouritesPanel()
	{
		bxFav = new Vbox();
		
		int AD_Role_ID = Env.getAD_Role_ID(Env.getCtx());
		int AD_Tree_ID = DB.getSQLValue(null,
			"SELECT COALESCE(r.AD_Tree_Menu_ID, ci.AD_Tree_Menu_ID)" 
			+ "FROM AD_ClientInfo ci" 
			+ " INNER JOIN AD_Role r ON (ci.AD_Client_ID=r.AD_Client_ID) "
			+ "WHERE AD_Role_ID=?", AD_Role_ID);
		if (AD_Tree_ID <= 0)
			AD_Tree_ID = 10;	//	Menu
		
		m_AD_Tree_ID = AD_Tree_ID;
		
		MTree vTree = new MTree(Env.getCtx(), AD_Tree_ID, false, true, null);
		MTreeNode m_root = vTree.getRoot();
		Enumeration enTop = m_root.children();		
		while(enTop.hasMoreElements())
		{
			MTreeNode ndTop = (MTreeNode)enTop.nextElement();
			Enumeration en = ndTop.preorderEnumeration();
			while (en.hasMoreElements())
			{
				MTreeNode nd = (MTreeNode)en.nextElement();
				if (nd.isOnBar()) {				
					String label = nd.toString().trim();
					ToolBarButton btnFavItem = new ToolBarButton(String.valueOf(nd.getNode_ID()));
					btnFavItem.setLabel(label);
					btnFavItem.setDraggable("deleteFav");
					btnFavItem.addEventListener(Events.ON_CLICK, this);
					btnFavItem.addEventListener(Events.ON_DROP, this);
					bxFav.appendChild(btnFavItem);
				}
			}
		}
		
		return bxFav;
	}
	
	private Box createViewPanel()
	{
		Vbox vbox = new Vbox();
				
		if (MRole.getDefault().isAllow_Info_Product())
		{
			ToolBarButton btnViewItem = new ToolBarButton("InfoProduct");
			btnViewItem.setLabel(Msg.getMsg(Env.getCtx(), "InfoProduct"));
			btnViewItem.addEventListener(Events.ON_CLICK, this);
			vbox.appendChild(btnViewItem);
		}
		if (MRole.getDefault().isAllow_Info_BPartner())
		{
			ToolBarButton btnViewItem = new ToolBarButton("InfoBPartner");
			btnViewItem.setLabel(Msg.getMsg(Env.getCtx(), "InfoBPartner"));
			btnViewItem.addEventListener(Events.ON_CLICK, this);
			vbox.appendChild(btnViewItem);
		}
		if (MRole.getDefault().isShowAcct() && MRole.getDefault().isAllow_Info_Account())
		{
			ToolBarButton btnViewItem = new ToolBarButton("InfoAccount");
			btnViewItem.setLabel(Msg.getMsg(Env.getCtx(), "InfoAccount"));
			btnViewItem.addEventListener(Events.ON_CLICK, this);
			vbox.appendChild(btnViewItem);
		}
		if (MRole.getDefault().isAllow_Info_Schedule())
		{
			ToolBarButton btnViewItem = new ToolBarButton("InfoSchedule");
			btnViewItem.setLabel(Msg.getMsg(Env.getCtx(), "InfoSchedule"));
			btnViewItem.addEventListener(Events.ON_CLICK, this);
			vbox.appendChild(btnViewItem);
		}
		vbox.appendChild(new Separator("horizontal"));
		if (MRole.getDefault().isAllow_Info_Order())
		{
			ToolBarButton btnViewItem = new ToolBarButton("InfoOrder");
			btnViewItem.setLabel(Msg.getMsg(Env.getCtx(), "InfoOrder"));
			btnViewItem.addEventListener(Events.ON_CLICK, this);
			vbox.appendChild(btnViewItem);
		}
		if (MRole.getDefault().isAllow_Info_Invoice())
		{
			ToolBarButton btnViewItem = new ToolBarButton("InfoInvoice");
			btnViewItem.setLabel(Msg.getMsg(Env.getCtx(), "InfoInvoice"));
			btnViewItem.addEventListener(Events.ON_CLICK, this);
			vbox.appendChild(btnViewItem);
		}
		if (MRole.getDefault().isAllow_Info_InOut())
		{
			ToolBarButton btnViewItem = new ToolBarButton("InfoInOut");
			btnViewItem.setLabel(Msg.getMsg(Env.getCtx(), "InfoInOut"));
			btnViewItem.addEventListener(Events.ON_CLICK, this);
			vbox.appendChild(btnViewItem);
		}
		if (MRole.getDefault().isAllow_Info_Payment())
		{
			ToolBarButton btnViewItem = new ToolBarButton("InfoPayment");
			btnViewItem.setLabel(Msg.getMsg(Env.getCtx(), "InfoPayment"));
			btnViewItem.addEventListener(Events.ON_CLICK, this);
			vbox.appendChild(btnViewItem);
		}
		if (MRole.getDefault().isAllow_Info_CashJournal())
		{
			ToolBarButton btnViewItem = new ToolBarButton("InfoCashLine");
			btnViewItem.setLabel(Msg.getMsg(Env.getCtx(), "InfoCashLine"));
			btnViewItem.addEventListener(Events.ON_CLICK, this);
			vbox.appendChild(btnViewItem);
		}
		if (MRole.getDefault().isAllow_Info_Resource())
		{
			ToolBarButton btnViewItem = new ToolBarButton("InfoAssignment");
			btnViewItem.setLabel(Msg.getMsg(Env.getCtx(), "InfoAssignment"));
			btnViewItem.addEventListener(Events.ON_CLICK, this);
			vbox.appendChild(btnViewItem);
		}
		if (MRole.getDefault().isAllow_Info_Asset())
		{
			ToolBarButton btnViewItem = new ToolBarButton("InfoAsset");
			btnViewItem.setLabel(Msg.getMsg(Env.getCtx(), "InfoAsset"));
			btnViewItem.addEventListener(Events.ON_CLICK, this);
			vbox.appendChild(btnViewItem);
		}
		
		return vbox;
	}
	
	private int getNoticeCount()
	{
		String sql = "SELECT COUNT(1) FROM AD_Note "
			+ "WHERE AD_Client_ID=? AND AD_User_ID IN (0,?)"
			+ " AND Processed='N'";
		int retValue = DB.getSQLValue(null, sql, Env.getAD_Client_ID(Env.getCtx()), Env.getAD_User_ID(Env.getCtx()));
		return retValue;
	}
	
	private int getRequestCount()
	{
		String sql = MRole.getDefault().addAccessSQL ("SELECT COUNT(1) FROM R_Request "
				+ "WHERE (SalesRep_ID=? OR AD_Role_ID=?) AND Processed='N'"
				+ " AND (DateNextAction IS NULL OR TRUNC(DateNextAction) <= TRUNC(SysDate))"
				+ " AND (R_Status_ID IS NULL OR R_Status_ID IN (SELECT R_Status_ID FROM R_Status WHERE IsClosed='N'))",
					"R_Request", false, true);	//	not qualified - RW
		int retValue = DB.getSQLValue(null, sql, Env.getAD_User_ID(Env.getCtx()), Env.getAD_Role_ID(Env.getCtx())); 
		return retValue;
	}
	
	public int getWorkflowCount() 
	{
		int count = 0;
		
		String sql = "SELECT count(*) FROM AD_WF_Activity a "
			+ "WHERE a.Processed='N' AND a.WFState='OS' AND ("
			//	Owner of Activity
			+ " a.AD_User_ID=?"	//	#1
			//	Invoker (if no invoker = all)
			+ " OR EXISTS (SELECT * FROM AD_WF_Responsible r WHERE a.AD_WF_Responsible_ID=r.AD_WF_Responsible_ID"
			+ " AND COALESCE(r.AD_User_ID,0)=0 AND COALESCE(r.AD_Role_ID,0)=0 AND (a.AD_User_ID=? OR a.AD_User_ID IS NULL))"	//	#2
			// Responsible User
			+ " OR EXISTS (SELECT * FROM AD_WF_Responsible r WHERE a.AD_WF_Responsible_ID=r.AD_WF_Responsible_ID"
			+ " AND r.AD_User_ID=?)"		//	#3
			//	Responsible Role
			+ " OR EXISTS (SELECT * FROM AD_WF_Responsible r INNER JOIN AD_User_Roles ur ON (r.AD_Role_ID=ur.AD_Role_ID)"
			+ " WHERE a.AD_WF_Responsible_ID=r.AD_WF_Responsible_ID AND ur.AD_User_ID=?))";	//	#4
			//
			//+ ") ORDER BY a.Priority DESC, Created";
		int AD_User_ID = Env.getAD_User_ID(Env.getCtx());
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt (1, AD_User_ID);
			pstmt.setInt (2, AD_User_ID);
			pstmt.setInt (3, AD_User_ID);
			pstmt.setInt (4, AD_User_ID);
			rs = pstmt.executeQuery ();
			if (rs.next ()) {
				count = rs.getInt(1);
			}
		}
		catch (Exception e)
		{
			logger.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		
		return count;
	}
	
    private void updateInfo()
	{
    	int noOfNotice = getNoticeCount();
    	int noOfRequest = getRequestCount();
    	int noOfWorkflow = getWorkflowCount();
    	int total = noOfNotice + noOfRequest + noOfWorkflow;
    	
		btnNotice.setLabel("Notice : " + noOfNotice);
		btnRequest.setLabel("Request : " + noOfRequest);
		btnWorkflow.setLabel("Workflow Activities : " + noOfWorkflow);
		windowContainer.setTabTitle(0, "Home (" + total + ")", 
				"Notice : " + noOfNotice + ", Request : " + noOfRequest + ", Workflow Activities : " + noOfWorkflow);
	}
    
    public void addWindow2TabPanel(Window window)
    {
    	Tabpanel tabPanel = new Tabpanel();
    	window.setParent(tabPanel);
    	windowContainer.addWindow(tabPanel, window.getTitle(), true); 
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
            	else
            	{
            		String actionCommand = btn.getName();
            		int WindowNo = 0;
            		
            		if (actionCommand.equals("InfoProduct") && AEnv.canAccessInfo("PRODUCT"))
            		{
            			InfoPanel.showProduct(WindowNo);
            		}
            		else if (actionCommand.equals("InfoBPartner") && AEnv.canAccessInfo("BPARTNER"))
            		{
            			InfoPanel.showBPartner(WindowNo);
            		}
            		else if (actionCommand.equals("InfoAsset") && AEnv.canAccessInfo("ASSET"))
            		{
            			InfoPanel.showAsset(WindowNo);
            		}
            		else if (actionCommand.equals("InfoAccount") && 
            				  MRole.getDefault().isShowAcct() &&
            				  AEnv.canAccessInfo("ACCOUNT"))
            		{
            			new org.adempiere.webui.acct.WAcctViewer();
            		}
            		else if (actionCommand.equals("InfoSchedule") && AEnv.canAccessInfo("SCHEDULE"))
            		{
            			new InfoSchedule(null, false);
            		}
            		else if (actionCommand.equals("InfoOrder") && AEnv.canAccessInfo("ORDER"))
            		{
            			InfoPanel.showOrder(WindowNo, "");
            		}
            		else if (actionCommand.equals("InfoInvoice") && AEnv.canAccessInfo("INVOICE"))
            		{
            			InfoPanel.showInvoice(WindowNo, "");
            		}
            		else if (actionCommand.equals("InfoInOut") && AEnv.canAccessInfo("INOUT"))
            		{
            			InfoPanel.showInOut(WindowNo, "");
            		}
            		else if (actionCommand.equals("InfoPayment") && AEnv.canAccessInfo("PAYMENT"))
            		{
            			InfoPanel.showPayment(WindowNo, "");
            		}
            		else if (actionCommand.equals("InfoCashLine") && AEnv.canAccessInfo("CASHJOURNAL"))
            		{
            			InfoPanel.showCashLine(WindowNo, "");
            		}
            		else if (actionCommand.equals("InfoAssignment") && AEnv.canAccessInfo("RESOURCE"))
            		{
            			InfoPanel.showAssignment(WindowNo, "");
            		}
            	}
            }
            else if(comp instanceof Button)
            {
            	Button btn = (Button) comp;
            	
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
        // Elaine 2008/07/24
        else if(eventName.equals(Events.ON_DROP))
        {
        	DropEvent de = (DropEvent) event;
    		Component dragged = de.getDragged();
        	
        	if(comp instanceof Groupbox)
        	{
        		if(dragged instanceof Treerow)
        		{
        			Treerow treerow = (Treerow) dragged;
        			Treeitem treeitem = treerow.getTreeitem();
        			
        			Object value = treeitem.getValue();
        			if(value != null)
        			{
        				int Node_ID = Integer.valueOf(value.toString());
        				if(barDBupdate(true, Node_ID))
        				{
        					String label = treeitem.getLabel().trim();
        					ToolBarButton btnFavItem = new ToolBarButton(String.valueOf(Node_ID));
        					btnFavItem.setLabel(label);
        					btnFavItem.setDraggable("deleteFav");
        					btnFavItem.addEventListener(Events.ON_CLICK, this);
        					btnFavItem.addEventListener(Events.ON_DROP, this);
        					bxFav.appendChild(btnFavItem);
        					bxFav.invalidate();
        				}
        			}
        		}
        	}
        	else if(comp instanceof Image)
        	{
        		if(dragged instanceof ToolBarButton)
        		{
        			ToolBarButton btn = (ToolBarButton) dragged;
        			String value = btn.getName();
        			
        			if(value != null)
        			{
        				int Node_ID = Integer.valueOf(value.toString());
        				if(barDBupdate(false, Node_ID))
        				{
        					bxFav.removeChild(btn);
        					bxFav.invalidate();
        				}
        			}
        		}
        	}
        }
        //
	}
    
    /**
	 *	Make Bar add/remove persistent
	 *  @param add true if add - otherwise remove
	 *  @param Node_ID Node ID
	 *  @return true if updated
	 */
    private boolean barDBupdate(boolean add, int Node_ID)
	{
		int AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
		int AD_Org_ID = Env.getContextAsInt(Env.getCtx(), "#AD_Org_ID");
		int AD_User_ID = Env.getContextAsInt(Env.getCtx(), "#AD_User_ID");
		StringBuffer sql = new StringBuffer();
		if (add)
			sql.append("INSERT INTO AD_TreeBar "
				+ "(AD_Tree_ID,AD_User_ID,Node_ID, "
				+ "AD_Client_ID,AD_Org_ID, "
				+ "IsActive,Created,CreatedBy,Updated,UpdatedBy)VALUES (")
				.append(m_AD_Tree_ID).append(",").append(AD_User_ID).append(",").append(Node_ID).append(",")
				.append(AD_Client_ID).append(",").append(AD_Org_ID).append(",")
				.append("'Y',SysDate,").append(AD_User_ID).append(",SysDate,").append(AD_User_ID).append(")");
			//	if already exist, will result in ORA-00001: unique constraint (ADEMPIERE.AD_TREEBAR_KEY)
		else
			sql.append("DELETE AD_TreeBar WHERE AD_Tree_ID=").append(m_AD_Tree_ID)
				.append(" AND AD_User_ID=").append(AD_User_ID)
				.append(" AND Node_ID=").append(Node_ID);
		int no = DB.executeUpdate(sql.toString(), false, null);
		return no == 1;
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
    	windowContainer.addWindow(tabPanel, wnd.getTitle(), true);
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
