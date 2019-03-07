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

import org.adempiere.webui.LayoutUtils;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Messagebox;
import org.adempiere.webui.component.ToolBarButton;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.window.WContext;
import org.adempiere.webui.window.WPreference;
import org.compiere.model.MClient;
import org.compiere.model.MOrg;
import org.compiere.model.MRole;
import org.compiere.model.MUser;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Vbox;

/**
 *
 * @author  <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date    Feb 25, 2007
 * @version $Revision: 0.10 $
 * 
 * @author Michael Mckay
 * Add context viewer.  Configurable.
 */
public class UserPanel extends Vbox  implements EventListener
{

	private static final long serialVersionUID = -45350536628290540L;

	private Properties ctx;

    private ToolBarButton logout = new ToolBarButton();
    private ToolBarButton role = new ToolBarButton();
    private ToolBarButton preference = new ToolBarButton();
    private ToolBarButton changeRole = new ToolBarButton();
    private ToolBarButton context = new ToolBarButton();

    private Label lblUserNameValue = new Label();
	private WPreference preferencePopup;
	private WContext contextPopup;

    public UserPanel()
    {
        this.ctx = Env.getCtx();
        init();
    }

    private void init()
    {
    	this.setStyle("text-align:right");

    	// Elaine 2008/11/07 - fix the layout problem in IE7
    	this.setWidth("100%");
    	this.setAlign("right");
    	//

    	lblUserNameValue.setValue(getUserName() + "@" + getClientName() + "." + getOrgName());
    	lblUserNameValue.setStyle("text-align:right");
    	LayoutUtils.addSclass("desktop-header-font", lblUserNameValue);
    	this.appendChild(lblUserNameValue);

    	Hbox hbox = new Hbox();
    	
    	// TODO - make configurable
    	context.setLabel(Msg.getMsg(Env.getCtx(), "Context"));
    	context.addEventListener(Events.ON_CLICK, this);
    	context.setStyle("text-align:right");
    	LayoutUtils.addSclass("desktop-header-font", context);
    	context.setParent(hbox);    	

    	Separator sep = new Separator("vertical");
    	sep.setBar(true);
    	sep.setParent(hbox);

    	preference.setLabel(Msg.getMsg(Env.getCtx(), "Preference"));
    	preference.addEventListener(Events.ON_CLICK, this);
    	preference.setStyle("text-align:right");
    	LayoutUtils.addSclass("desktop-header-font", preference);
    	preference.setParent(hbox);

    	sep = new Separator("vertical");
    	sep.setBar(true);
    	sep.setParent(hbox);

    	role.setLabel(this.getRoleName());
    	role.addEventListener(Events.ON_CLICK, this);
    	role.setStyle("text-align:right");
    	LayoutUtils.addSclass("desktop-header-font", role);
    	role.setParent(hbox);

    	sep = new Separator("vertical");
    	sep.setBar(true);
    	sep.setParent(hbox);

    	changeRole.setLabel(Msg.getMsg(Env.getCtx(),"changeRole"));
    	changeRole.addEventListener(Events.ON_CLICK, this);
    	changeRole.setStyle("text-align:right");
    	LayoutUtils.addSclass("desktop-header-font", changeRole);
    	changeRole.setParent(hbox);
    	
    	sep = new Separator("vertical");
    	sep.setBar(true);
    	sep.setParent(hbox);

    	logout.setLabel(Msg.getMsg(Env.getCtx(),"Logout"));
    	logout.addEventListener(Events.ON_CLICK, this);
    	logout.setStyle("text-align:right");
    	LayoutUtils.addSclass("desktop-header-font", logout);
    	logout.setParent(hbox);

    	this.appendChild(hbox);
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

    private String getOrgName()
    {
    	int orgId = Env.getAD_Org_ID(ctx);
    	if (orgId > 0)
    	{
    		MOrg org = MOrg.get(ctx, orgId);
    		return org.getName();
    	}
    	else
    	{
    		return "*";
    	}
    }

	public void onEvent(Event event) throws Exception {
		if (event == null)
			return;

		if (logout == event.getTarget())
        {
            SessionManager.logoutSession();
        }
		else if (role == event.getTarget())
		{
			String roleInfo = MRole.getDefault().toStringX(Env.getCtx());
			roleInfo = roleInfo.replace(Env.NL, "<br>");
			Messagebox.showDialog(roleInfo, Msg.getMsg(ctx, "RoleInfo"), Messagebox.OK, Messagebox.INFORMATION);
		}
		else if (preference == event.getTarget())
		{
			if (preferencePopup != null)
			{
				preferencePopup.detach();
			}
			preferencePopup = new WPreference();
			preferencePopup.setPage(this.getPage());
			preferencePopup.open(preference);
		}
		else if (context == event.getTarget())
		{
			if (contextPopup != null)
			{
				contextPopup.detach();
			}
			contextPopup = new WContext();
			AEnv.showWindow(contextPopup);
		}
		else if (changeRole == event.getTarget())
		{
			MUser user = MUser.get(ctx);
			Clients.confirmClose(null);
			SessionManager.changeRole(user);
		}

	}
}
