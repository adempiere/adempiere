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

package org.adempiere.webui.window;

import java.util.Properties;

import org.adempiere.webui.component.Window;
import org.adempiere.webui.panel.ADWindowPanel;
import org.adempiere.webui.session.SessionManager;
import org.compiere.model.MQuery;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

/**
 *
 * @author  <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date    Feb 25, 2007
 * @version $Revision: 0.10 $
 */
public class ADWindow extends Window implements EventListener
{
    private static final long serialVersionUID = 1L;

    private ADWindowPanel windowPanel;
    private Properties ctx;
    private int adWindowId;
    private String _title;
    private int windowNo;
    
    public ADWindow(Properties ctx, int adWindowId)
    {
       this(ctx, adWindowId, null);
    }
    
    public ADWindow(Properties ctx, int adWindowId, MQuery query)
    {
    	 if(adWindowId <= 0)
             throw new IllegalArgumentException("Window Id is invalid");
         
         this.ctx = ctx;
         this.adWindowId = adWindowId;
         windowNo = SessionManager.getAppDesktop().registerWindow(this);
         init(query);
    }
    
    private void init(MQuery query)
    {
        windowPanel = new ADWindowPanel(ctx, windowNo);
        windowPanel.initPanel(adWindowId, query);

        this.appendChild(windowPanel);
        this.setWidth("850px");
        _title = windowPanel.getTitle();
    }
    
    public String getTitle()
    {
        return _title;
    }

    public boolean isAsap()
    {
        return false;
    }

    public void onEvent(Event event)
    {
      /*  if (restoreButton.equals(event.getTarget()))
        {
           String mode = this.getMode();
           if (ADWindow.MODE_EMBEDDED.equals(mode))
           {
               this.doOverlapped();
           }
           else
           {
               this.doEmbedded();
           }
        }*/
    }
    
}
