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

import org.adempiere.webui.theme.ThemeUtils;
import org.adempiere.webui.window.AboutWindow;
import org.zkoss.zhtml.Table;
import org.zkoss.zhtml.Td;
import org.zkoss.zhtml.Tr;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Image;

/**
 *
 * @author  <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @author  <a href="mailto:hengsin@gmail.com">Low Heng Sin</a>
 * @date    Mar 2, 2007
 * @date    July 7, 2007
 * @version $Revision: 0.20 $
 * 
 * Updated to zk 7.
 */

public class HeaderPanel extends Table implements EventListener<Event>
{
	private static final long serialVersionUID = -2351317624519209484L;

	private Image image = new Image();

    public HeaderPanel()
    {
        super();
        init();
    }

    private void init()
    {
    	ThemeUtils.addSclass("ad-headerpanel", this);

    	// Keep this simple for speed
    	// Create a simple table to hold the header elements.
    	Tr tr = new Tr();
    	ThemeUtils.addSclass("ad-headerpanel-row", tr);

    	Td tdLeft = new Td();
    	ThemeUtils.addSclass("ad-headerpanel-left", tdLeft);
    	
    	Td tdRight = new Td();

    	ThemeUtils.addSclass("ad-headerpanel-right", tdRight);
    	
    	this.appendChild(tr);
    	
    	tr.appendChild(tdLeft);
    	tr.appendChild(tdRight);
    	    	
    	image.setSrc(ThemeUtils.getSmallLogo());
    	image.addEventListener(Events.ON_CLICK, this);
    	ThemeUtils.addSclass("ad-headerpanel-logo", image);
        
    	image.setParent(tdLeft);

    	UserPanel userPanel = new UserPanel();
    	userPanel.setParent(tdRight);
    }
    
	public void onEvent(Event event) throws Exception {
		if (event == null)
			return;
		if (Events.ON_CLICK.equals(event.getName())) {
			if(image == event.getTarget())
			{
				AboutWindow w = new AboutWindow();
				w.setPage(this.getPage());
				w.doHighlighted();
			}
		}
	}

}
