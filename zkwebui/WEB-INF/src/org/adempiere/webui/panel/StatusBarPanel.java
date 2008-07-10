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

import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.VerticalBox;
import org.compiere.model.DataStatusEvent;
import org.zkoss.zul.Hbox;

/**
 * This class is based on org.compiere.apps.StatusBar written by Jorg Janke.
 * @author Jorg Janke
 * 
 * @author  <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date    Mar 12, 2007
 * @version $Revision: 0.10 $
 */
public class StatusBarPanel extends Panel
{
    private static final long serialVersionUID = 1L;

    private Label statusDB;
    private Label statusLine;
    
    public StatusBarPanel()
    {
        super();
        init();
    }
    
    private void init()
    {
        statusLine = new Label();
        //statusLine.setWidth("100%");
        
        statusDB = new Label();
        statusDB.setWidth("200px");
        
        Hbox hbox = new Hbox();
        hbox.appendChild(statusLine);
        hbox.appendChild(statusDB);
        
        VerticalBox mainBox = new VerticalBox();
        mainBox.appendChild(hbox);
        
        this.appendChild(mainBox);
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
    }
    
    public void setStatusLine (String text, boolean error)
    {
        statusLine.setValue(text);
    }
}
