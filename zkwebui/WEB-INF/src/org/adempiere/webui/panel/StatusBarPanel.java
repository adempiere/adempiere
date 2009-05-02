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

import org.adempiere.webui.LayoutUtils;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.window.WRecordInfo;
import org.compiere.model.DataStatusEvent;
import org.compiere.model.MRole;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Vbox;

/**
 * This class is based on org.compiere.apps.StatusBar written by Jorg Janke.
 * @author Jorg Janke
 * 
 * @author  <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date    Mar 12, 2007
 * @version $Revision: 0.10 $
 */
public class StatusBarPanel extends Panel implements EventListener
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1799438509126878684L;
	private Label statusDB;
    private Label statusLine;
    private Label infoLine;

	private DataStatusEvent m_dse;

	private String m_text;

	private Div east;

	private Div west;
    
    public StatusBarPanel()
    {
        super();
        init();
    }
    
    private void init()
    {
        statusLine = new Label();
        statusDB = new Label("  ");
        infoLine = new Label();
        
        Hbox hbox = new Hbox();
        hbox.setWidth("100%");
        hbox.setHeight("100%");
        hbox.setWidths("50%, 50%");
        west = new Div();
        west.setStyle("text-align: left; ");
        west.appendChild(statusLine);
        west.setWidth("100%");
        Vbox vbox = new Vbox();
        vbox.setPack("center");
        LayoutUtils.addSclass("status", vbox);        
        vbox.appendChild(west);
        hbox.appendChild(vbox);
        
        east = new Div();
        east.setWidth("100%");
        east.setStyle("text-align: right; ");
        east.appendChild(infoLine);
        east.appendChild(statusDB);
        
        LayoutUtils.addSclass("status-db", statusDB);
        LayoutUtils.addSclass("status-info", infoLine);
        vbox = new Vbox();
        vbox.setPack("center");
        LayoutUtils.addSclass("status", vbox);        
        vbox.appendChild(east);
        hbox.appendChild(vbox);
        
        this.appendChild(hbox);
        
        statusDB.addEventListener(Events.ON_CLICK, this);
        infoLine.setVisible(false);
    }
    
    public void setStatusDB (String text)
    {
        setStatusDB(text, null);
    }
    
    public void setStatusDB (String text, DataStatusEvent dse)
    {
        if (text == null || text.length() == 0)
        {
            statusDB.setValue("");
        }
        else
        {
            StringBuffer sb = new StringBuffer (" ");
            sb.append(text).append(" ");
            statusDB.setValue(sb.toString());
        }
        
        m_text = text;
        m_dse = dse;
    }
    
    public void setStatusLine (String text)
    {
        setStatusLine(text, false);
    }
    
    public void setStatusLine (String text, boolean error)
    {
        statusLine.setValue(text);
        statusLine.setTooltiptext(text);
    }
    
    /**
     * Add Component to East of StatusBar
     * 
     * @param component
     *            component
     */
    public final void addStatusComponent(final Component component)
    {
        east.appendChild(component);
    } // addStatusComponent
    
    /**
	 *	Set Info Line
	 *  @param text text
	 */
	public void setInfo (String text)
	{
		infoLine.setValue(text != null ? text : "");
		if (text == null || text.trim().length() == 0)
			infoLine.setVisible(false);
		else
			infoLine.setVisible(true);
	}	//	setInfo

	public void onEvent(Event event) throws Exception {
		if (Events.ON_CLICK.equals(event.getName()) && event.getTarget() == statusDB) {
			if (m_dse == null 
				|| m_dse.CreatedBy == null
				|| !MRole.getDefault().isShowPreference())
				return;
			
			String title = Msg.getMsg(Env.getCtx(), "Who") + m_text;
			new WRecordInfo (title, m_dse);
		}
		
	}
}
