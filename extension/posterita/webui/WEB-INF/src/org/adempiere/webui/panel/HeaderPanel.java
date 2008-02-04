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

import org.adempiere.webui.component.Panel;
import org.adempiere.webui.session.SessionManager;
import org.compiere.model.MSysConfig;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Separator;

/**
 *
 * @author  <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date    Mar 2, 2007
 * @version $Revision: 0.10 $
 */

public class HeaderPanel extends Panel implements EventListener
{
	private static final long serialVersionUID = -4293371180738797244L;

    private Image image = new Image();
    private SideUserPanel pnlSideUser;
    
    public HeaderPanel()
    {
        super();
        init();
    }
    
    private void init()
    {
    	pnlSideUser = new SideUserPanel();
    	
    	Hbox hbox = new Hbox();
    	hbox.setWidth("100%");
    	hbox.setWidths("300px, 550px, 350px");
    	
    	Panel right = new Panel();
    	right.setWidth("100%");
    	right.setStyle("text-align:right");
    	    	
    	Panel left = new Panel();
    	left.setWidth("100%");
    	left.setStyle("text-align:center");

    	right.appendChild(pnlSideUser);

    	String homeURL = MSysConfig.getValue("WEBUI_LOGOURL", "/images/logo_ad.png");
    	image.setSrc(homeURL);
    	image.addEventListener(Events.ON_CLICK, this);
    	left.appendChild(image);
    	
    	hbox.appendChild(left);
    	hbox.appendChild(new Label(""));
    	hbox.appendChild(right);
    	
    	this.setWidth("100%");
    	this.appendChild(new Separator());
    	this.appendChild(hbox);
    	this.appendChild(new Separator());
    }

	public void onEvent(Event event) throws Exception {
		if (event == null)
			return;
		
		if (event.getTarget() == image){
			String homeURL = MSysConfig.getValue("WEBUI_HOMEURL", "http://www.adempiere.com/");
			SessionManager.getAppDesktop().showURL(homeURL, true);
		}
		
	}
}
