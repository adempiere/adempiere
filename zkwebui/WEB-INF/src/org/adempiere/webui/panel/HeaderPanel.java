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
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.window.AboutWindow;
import org.compiere.model.MSysConfig;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.East;
import org.zkoss.zkex.zul.West;
import org.zkoss.zul.Image;
import org.zkoss.zul.Vbox;

/**
 *
 * @author  <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @author  <a href="mailto:hengsin@gmail.com">Low Heng Sin</a>
 * @date    Mar 2, 2007
 * @date    July 7, 2007
 * @version $Revision: 0.20 $
 */

public class HeaderPanel extends Panel implements EventListener
{
	private static final long serialVersionUID = -4293371180738797244L;

    private Image image = new Image();
    
    public HeaderPanel()
    {
        super();
        init();
    }
    
    private void init()
    {
    	LayoutUtils.addSclass("header", this);
    	
    	UserPanel userPanel = new UserPanel();    	

    	String logoURL = MSysConfig.getValue("WEBUI_LOGOURL", "/images/AD10030.png");
    	image.setSrc(logoURL);
    	image.addEventListener(Events.ON_CLICK, this);
    	image.setStyle("cursor: pointer;");

    	Borderlayout layout = new Borderlayout();
    	LayoutUtils.addSclass("header", layout);
    	layout.setParent(this);
    	West west = new West();
    	west.setParent(layout);
    	
    	Vbox vb = new Vbox();
        vb.setParent(west);
        vb.setHeight("100%");
        vb.setWidth("100%");
        vb.setPack("center");
        vb.setAlign("left");
        
    	image.setParent(vb);
    	    	
    	LayoutUtils.addSclass("header-left", west);
    	//the following doesn't work when declare as part of the header-left style
    	west.setStyle("background-color: transparent; border: none;");
    	
    	East east = new East();
    	east.setParent(layout);
    	userPanel.setParent(east);
    	userPanel.setHeight("100%");
    	east.setFlex(true);
    	LayoutUtils.addSclass("header-right", east);
    	//the following doesn't work when declare as part of the header-right style
    	east.setStyle("background-color: transparent; border: none;");
    }

	public void onEvent(Event event) throws Exception {
		if (Events.ON_CLICK.equals(event.getName())) {
			AboutWindow w = new AboutWindow();
			w.setPage(this.getPage());			
			w.doModal();
		}
		
	}
}
