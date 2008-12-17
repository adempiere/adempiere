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

import java.util.ArrayList;
import java.util.List;

import org.adempiere.apps.graph.BarGraphColumn;
import org.adempiere.webui.ClientInfo;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.apps.graph.WBarGraph;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.exception.ApplicationException;
import org.adempiere.webui.part.AbstractUIPart;
import org.compiere.model.MAchievement;
import org.compiere.model.MGoal;
import org.compiere.model.MMeasureCalc;
import org.compiere.model.MMenu;
import org.compiere.model.MProjectType;
import org.compiere.model.MQuery;
import org.compiere.model.MRequestType;
import org.compiere.model.MRole;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.zkoss.zhtml.Button;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Panelchildren;

/**
 * Base class for desktop implementation
 * @author hengsin
 *
 */
public abstract class AbstractDesktop extends AbstractUIPart implements IDesktop {

	private transient ClientInfo clientInfo;
	
	private List<Object> windows = null; 
	
	private MGoal[] m_goals = null;
	
	private List<MQuery> queryZoom = null;
	
	private static final CLogger logger = CLogger.getCLogger(AbstractDesktop.class);
	
	private static final String ZOOM_KEY = "queryZoom";	
	
	public AbstractDesktop() {		
		windows = new ArrayList<Object>();
		queryZoom = new ArrayList<MQuery>();
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
   	
   	protected abstract void showEmbedded(Window win);

	/**
   	 * 
   	 * @param win
   	 */
   	protected void showModal(Window win)
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
   	protected void showPopup(Window win, String position)
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
   	protected void showOverlapped(Window win, String position)
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
   	protected void showHighlighted(Window win, String position)
   	{
		if (position == null)
			win.setPosition("center");
		else
			win.setPosition(position);
		
   		win.doHighlighted();
   	}
   	
   	protected String renderGoals(int AD_Table_ID, Panelchildren panel) 
	{
		String output = "";
		if (m_goals == null)
		{
			m_goals = MGoal.getUserGoals(Env.getCtx(), Env.getAD_User_ID(Env.getCtx()));
			if (m_goals == null)
				return output;
		}
		
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
						btn.setId(String.valueOf(ZOOM_KEY + queryZoom.size()));
						btn.addEventListener(Events.ON_CLICK, new EventListener() {
							public void onEvent(Event event) throws Exception {
								Component comp = event.getTarget();
								String id = comp.getId();
				            	if(id.startsWith(ZOOM_KEY))
				            	{
				            		String ss = id.substring(ZOOM_KEY.length());
					        		int index = Integer.parseInt(String.valueOf(ss));
					            	if ((index >= 0) && (index < queryZoom.size()))
					                	AEnv.zoom(queryZoom.get(index));
				            	}
							}
							
						});
						btn.setVisible(false);
						panel.appendChild(btn);

						output += "<a class=\"hrefZoom\" id=\"" + ZOOM_KEY +
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
   	
   	protected String stripHtml(String htmlString, boolean all) {
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
}
