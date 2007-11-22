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

import java.util.Properties;

import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.session.SessionManager;
import org.compiere.model.MClient;
import org.compiere.model.MRole;
import org.compiere.model.MUser;
import org.compiere.util.Env;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Hbox;

/**
 *
 * @author  <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date    Feb 25, 2007
 * @version $Revision: 0.10 $
 */
public class UserPanel extends Hbox  implements EventListener
{
    private static final long serialVersionUID = 1L;

    private Properties ctx;
    private Grid grid;
    
    private Label lblPrefix = new Label("You are logged in as: ");
    private Label lblSeparator = new Label(" | ");
    private Label lblLogout = new Label("logoff");
    
    private Label lblUserNameValue = new Label();
    
    public UserPanel()
    {
        this.ctx = Env.getCtx();
        init();
    }
    
    private void init()
    {
    	lblLogout.setStyle("cursor: hand;");
    	
    	lblPrefix.setStyle("font-style: bold;");
    	
    	lblUserNameValue.setValue(getUserName());
    	
    	lblLogout.addEventListener(Events.ON_CLICK, this);
    	
    	//this.setWidth("200px");
    	
    	this.appendChild(lblPrefix);
    	this.appendChild(lblUserNameValue);
    	this.appendChild(lblSeparator);
    	this.appendChild(lblLogout);
    	
/*        grid = new Grid();
        grid.setWidth("200px");
        
        Rows rows = new Rows();
        
        Label lblUserName = new Label();
        Label lblUserNameValue = new Label();
        lblUserName.setValue("User Name");
        lblUserNameValue.setValue(getUserName());
        
        Row row = new Row();
        row.appendChild(lblUserName);
        row.appendChild(lblUserNameValue);
        rows.appendChild(row);
        
        Label lblRole = new Label();
        Label lblRoleValue = new Label();
        lblRole.setValue("Role");
        lblRoleValue.setValue(getRoleName());
        
        row = new Row();
        row.appendChild(lblRole);
        row.appendChild(lblRoleValue);
        rows.appendChild(row);
        
        Label lblClient = new Label();
        Label lblClientValue = new Label();
        lblRole.setValue("Client");
        lblRoleValue.setValue(getClientName());
        
        row = new Row();
        row.appendChild(lblClient);
        row.appendChild(lblClientValue);
        rows.appendChild(row);
        
        grid.appendChild(rows);
        this.appendChild(grid);*/
    }
    
    private String getUserName()
    {
        MUser user = MUser.get(ctx);
        return user.getName();
    }
    
    private String getRoleName()
    {
        MRole role = MRole.getDefault(ctx, false);
        return role.getName();
    }
    
    private String getClientName()
    {
        MClient client = MClient.get(ctx);
        return client.getName();
    }

	public void onEvent(Event event) throws Exception {
		if (event == null)
			return;
		
		if (lblLogout == event.getTarget())
        {
            SessionManager.logoutSession();
        }
		
	}
}
