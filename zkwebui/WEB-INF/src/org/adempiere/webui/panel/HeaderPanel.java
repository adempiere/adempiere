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
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.theme.ThemeManager;
import org.adempiere.webui.window.AboutWindow;
import org.zkoss.zhtml.Table;
import org.zkoss.zhtml.Td;
import org.zkoss.zhtml.Tr;
import org.adempiere.webui.window.WContext;
import org.adempiere.webui.window.WPreference;
import org.compiere.model.MClient;
import org.compiere.model.MOrg;
import org.compiere.model.MRole;
import org.compiere.model.MUser;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.web.fn.ServletFns;
import org.zkoss.zhtml.Table;
import org.zkoss.zhtml.Td;
import org.zkoss.zhtml.Tr;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Separator;

/**
 *
 * @author  <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @author  <a href="mailto:hengsin@gmail.com">Low Heng Sin</a>
 * @date    Mar 2, 2007
 * @date    July 7, 2007
 * @version $Revision: 0.20 $
 * 
 * Updated to zk 7 - combined with UserPanel to simplify the layout.
 */

public class HeaderPanel extends Table implements EventListener<Event>
{
	private static final long serialVersionUID = -2351317624519209484L;

	private Properties ctx;

	private Image image = new Image();
    private ToolBarButton logout = new ToolBarButton();
    private ToolBarButton role = new ToolBarButton();
    private ToolBarButton preference = new ToolBarButton();
    private ToolBarButton context = new ToolBarButton();

    private Label lblUserNameValue = new Label();
	private WPreference preferencePopup;
	private WContext contextPopup;

    public HeaderPanel()
    {
        super();
        init();
    	this.ctx = Env.getCtx();
    }

    private void init()
    {
    	ThemeUtils.addSclass("ad-headerpanel", this);

    	// Keep this simple for speed
    	// Create a simple table to hold the header elements.
    	Table table = new Table();
    	ThemeUtils.addSclass("ad-headerpanel-table", table);
    	Tr tr = new Tr();
    	ThemeUtils.addSclass("ad-headerpanel-row", tr);

    	Td tdLeft = new Td();
    	ThemeUtils.addSclass("ad-headerpanel-left", tdLeft);
    	
    	Td tdRight = new Td();
    	Td links = new Td();
    	tr_bottom.appendChild(links);
    	ThemeUtils.addSclass("desktop-header-right-bottom", links);

    	lblUserNameValue.setValue(getUserName() + "@" + getClientName() + "." + getOrgName()+"/"+getRoleName());
    	lblUserNameValue.setId("loginUserAndRole");
    	
    	ThemeUtils.addSclass("desktop-header-font", lblUserNameValue);
    	ThemeUtils.addSclass("desktop-header-username", lblUserNameValue);
    	
    	userInfo.appendChild(lblUserNameValue);
    	
    	ThemeUtils.addSclass("ad-headerpanel-right", tdRight);
    	
    	this.appendChild(table);
    	table.appendChild(tr);
    	
    	tr.appendChild(tdLeft);
    	tr.appendChild(tdRight);
    	    	
    	image.setSrc(ThemeUtils.getSmallLogo());
    	image.addEventListener(Events.ON_CLICK, this);
    	ThemeUtils.addSclass("ad-headerpanel-logo", image);
        
    	image.setParent(tdLeft);

    	UserPanel userPanel = new UserPanel();
    	userPanel.setParent(tdRight);
    	//userPanel.setHeight("100%");
    	//userPanel.setAlign("right");
    	//userPanel.setStyle("position: absolute; text-align:right;");
    	//userPanel.setVflex("1");
    	//userPanel.setHflex("1");
    	//ThemeUtils.addSclass("desktop-header-right", center);
    	
    	//the following doesn't work when declare as part of the header-right style
    	//center.setStyle("background-color: transparent; border: none;");

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
		if (Events.ON_CLICK.equals(event.getName())) {
			if(image == event.getTarget())
			{
				AboutWindow w = new AboutWindow();
				w.setPage(this.getPage());
				w.doHighlighted();
			}
			else if (logout == event.getTarget())
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
		}
	}

}
