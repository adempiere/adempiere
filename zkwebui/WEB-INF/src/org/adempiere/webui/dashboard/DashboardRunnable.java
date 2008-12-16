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
package org.adempiere.webui.dashboard;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.webui.desktop.IDesktop;
import org.compiere.util.CLogger;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;

public class DashboardRunnable implements Runnable {
	private Desktop desktop;
	private boolean stop = false;
	private List<DashboardPanel> dashboardPanels;
	private IDesktop appDesktop;
	
	private static final CLogger logger = CLogger.getCLogger(DashboardRunnable.class);	
	
	public DashboardRunnable(Desktop desktop, IDesktop appDesktop) {
		this.desktop = desktop;
		this.appDesktop = appDesktop;
		
		dashboardPanels = new ArrayList<DashboardPanel>();
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
					refreshDashboard();						
				} catch (Error ex) {
					logger.log(Level.WARNING, "UpdateInfo Thread error="+ex.getLocalizedMessage(), ex);
					break;
				} finally {
					// release full control of desktop
					Executions.deactivate(desktop);
				}
			} catch (Throwable e) {
				logger.log(Level.WARNING, "UpdateInfo Thread error="+e.getLocalizedMessage(), e);
				break;
			}				
		}
	}
	
	public void refreshDashboard()
	{
    	for(int i = 0; i < dashboardPanels.size(); i++)
    		dashboardPanels.get(i).refresh();
    	
    	appDesktop.onServerPush();    	
	}

	public void stop() {
		stop = true;
	}

	public void add(DashboardPanel dashboardPanel) {
		dashboardPanels.add(dashboardPanel);
	}
}
