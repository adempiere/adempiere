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
import org.compiere.model.DataStatusEvent;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Vbox;

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
    private Label infoLine;
    
    public StatusBarPanel()
    {
        super();
        init();
    }
    
    private void init()
    {
        statusLine = new Label();
        statusDB = new Label();
        infoLine = new Label();
        
        Hbox hbox = new Hbox();
        hbox.setWidth("100%");
        hbox.setHeight("100%");
        hbox.setWidths("50%, 50%");
        Div div = new Div();
        div.setStyle("text-align: left; ");
        div.appendChild(statusLine);
        div.setWidth("100%");
        Vbox vbox = new Vbox();
        vbox.setPack("center");
        LayoutUtils.addSclass("status", vbox);        
        vbox.appendChild(div);
        hbox.appendChild(vbox);
        
        div = new Div();
        div.setWidth("100%");
        div.setStyle("text-align: right; ");
        div.appendChild(infoLine);
        div.appendChild(statusDB);
        statusDB.setStyle("");
        LayoutUtils.addSclass("status-db", statusDB);
        LayoutUtils.addSclass("status-info", infoLine);
        vbox = new Vbox();
        vbox.setPack("center");
        LayoutUtils.addSclass("status", vbox);        
        vbox.appendChild(div);
        hbox.appendChild(vbox);
        
        this.appendChild(hbox);
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
    }
    
    public void setStatusLine (String text)
    {
        setStatusLine(text, false);
    }
    
    public void setStatusLine (String text, boolean error)
    {
        statusLine.setValue(text);
    }
    
    /**
	 *	Set Info Line
	 *  @param text text
	 */
	public void setInfo (String text)
	{
		infoLine.setValue(text);
	}	//	setInfo
}
