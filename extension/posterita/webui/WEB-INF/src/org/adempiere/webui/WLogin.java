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

import org.adempiere.webui.part.AbstractUIPart;
import org.adempiere.webui.window.LoginWindow;
import org.zkoss.zk.ui.Component;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zul.Vbox;

/**
 *
 * @author  <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @author  Low Heng Sin
 * @date    Mar 3, 2007
 * @version $Revision: 0.10 $
 */
public class WLogin extends AbstractUIPart
{
    private static final long serialVersionUID = 1L;

    private IWebClient app;
	private Borderlayout layout;
    
    public WLogin(IWebClient app)
    {
        this.app = app;
    }
    
    protected Component doCreatePart(Component parent)
    {
        layout = new Borderlayout();
        if (parent != null)
        	layout.setParent(parent);
        else
        	layout.setPage(page);
        Center c = new Center();
        c.setParent(layout);
        c.setBorder("none");
        c.setFlex(true);
        c.setAutoscroll(true);
                
        Vbox vb = new Vbox();
        vb.setParent(c);
        vb.setHeight("100%");
        vb.setWidth("100%");
        vb.setPack("center");
        vb.setAlign("center");
        LayoutUtils.addSclass("login", vb);
        
        LoginWindow loginWindow = new LoginWindow(app);
        loginWindow.setParent(vb);
        
        return layout;
    }

	public void detach() {
		layout.detach();
		layout = null;
	}

	public Component getComponent() {
		return layout;
	}
}
