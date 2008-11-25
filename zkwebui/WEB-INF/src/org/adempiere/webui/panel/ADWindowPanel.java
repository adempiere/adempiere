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

package org.adempiere.webui.panel;

import java.util.List;
import java.util.Properties;

import org.adempiere.webui.LayoutUtils;
import org.adempiere.webui.component.CompositeADTab;
import org.adempiere.webui.component.IADTab;
import org.adempiere.webui.component.Tabbox;
import org.adempiere.webui.component.Tabpanel;
import org.adempiere.webui.component.Tabs;
import org.adempiere.webui.session.SessionManager;
import org.compiere.model.GridWindow;
import org.compiere.model.MQuery;
import org.compiere.util.CLogger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zkex.zul.West;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Window;

/**
 * 
 * This class is based on org.compiere.apps.APanel written by Jorg Janke.
 * @author Jorg Janke
 * 
 * @author <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @author <a href="mailto:hengsin@gmail.com">Low Heng Sin</a>
 * @date Feb 25, 2007
 * @version $Revision: 0.10 $
 */
public class ADWindowPanel extends AbstractADWindowPanel
{
    private static final long    serialVersionUID = 1L;

    private static final CLogger logger;

    static
    {
        logger = CLogger.getCLogger(ADWindowPanel.class);
    }

	private Borderlayout layout;

	private Center contentArea;

	private West west;
	
    public ADWindowPanel(Properties ctx, int windowNo)
    {
        super(ctx, windowNo);
    }
    
    
	public ADWindowPanel(Properties ctx, int windowNo, GridWindow gridWindow,
			int tabIndex, IADTabpanel tabPanel) {
		super(ctx, windowNo, gridWindow, tabIndex, tabPanel);
	}


	protected Component doCreatePart(Component parent)
    {				
        layout = new Borderlayout();
        if (parent != null) {
	        layout.setParent(parent);
	        layout.setStyle("position:absolute");
	        layout.setHeight("100%");
	        layout.setWidth("100%");
        } else {
        	layout.setPage(page);
        }
        
        if (!isEmbedded())
        {
	        North n = new North();
	        n.setParent(layout);
	        n.setCollapsible(false);
	        n.setHeight("30px");
	        toolbar.setHeight("30px");
	        toolbar.setParent(n);	        	        
        }
        
        South s = new South();        
        layout.appendChild(s);
        s.setCollapsible(false);
        statusBar.setParent(s);
        LayoutUtils.addSclass("adwindow-status", statusBar);
        
        if (!isEmbedded() && adTab.isUseExternalSelection())
        {
	        west = new West();
	        layout.appendChild(west);
	        west.setSplittable(false);
	        west.setAutoscroll(true);
	        LayoutUtils.addSclass("adwindow-nav", west);
	        adTab.getTabSelectionComponent().setParent(west);
	        LayoutUtils.addSclass("adwindow-nav-content", (HtmlBasedComponent) adTab.getTabSelectionComponent());
        }
        
        contentArea = new Center();
        contentArea.setParent(layout);
        contentArea.setAutoscroll(true);
        contentArea.setFlex(true);
        adTab.createPart(contentArea);
        
        if (parent instanceof Tabpanel) {
        	TabOnCloseHanlder handler = new TabOnCloseHanlder();
        	((Tabpanel)parent).setOnCloseHandler(handler);
        }
        
        if (!isEmbedded() && adTab.getComponent() instanceof Window) {
        	Window w = (Window) adTab.getComponent();
        	w.addEventListener(Events.ON_CTRL_KEY, toolbar);
        }
        
        return layout;
    }

    protected IADTab createADTab()
    {
    	CompositeADTab composite = new CompositeADTab();
    	return composite;
    }

	public Borderlayout getComponent() {
		return layout;
	}
	
	
	
	@Override
	public boolean initPanel(int adWindowId, MQuery query) {
		boolean retValue = super.initPanel(adWindowId, query);
		if (adTab.getTabCount() == 1 && west != null)
			west.setVisible(false);
		return retValue;
	}



	class TabOnCloseHanlder implements ITabOnCloseHandler {
		
		public void onClose(Tabpanel tabPanel) {
			if (ADWindowPanel.this.onExit()) {
				Tab tab = tabPanel.getLinkedTab();
				Tabbox tabbox = (Tabbox) tab.getTabbox();
				if (tabbox.getSelectedTab() == tab) {
					Tabs tabs = (Tabs) tabbox.getTabs();
					List childs = tabs.getChildren();
					for(int i = 0; i < childs.size(); i++) {
						if (childs.get(i) == tab) {
							if (i > 0) 
								tabbox.setSelectedIndex((i-1));
							break;
						}
					}
				}
				tabPanel.detach();
				tab.detach();
				if (getWindowNo() > 0) 
					SessionManager.getAppDesktop().unregisterWindow(getWindowNo());
			}
		}
	}
}
