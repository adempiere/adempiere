/******************************************************************************
 * Copyright (C) 2008 Low Heng Sin                                            *
 * Copyright (C) 2009 Idalica Corporation                                     *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/

package org.adempiere.webui;

import org.adempiere.webui.part.AbstractUIPart;
import org.adempiere.webui.theme.ITheme;
import org.adempiere.webui.window.LoginWindow;
import org.compiere.model.MSysConfig;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.metainfo.PageDefinition;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.East;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zkex.zul.West;
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

	private IWebClient app;
	private Borderlayout layout;

    public WLogin(IWebClient app)
    {
        this.app = app;
    }

    protected Component doCreatePart(Component parent)
    {
    	String theme = MSysConfig.getValue(ITheme.ZK_THEME, ITheme.ZK_THEME_DEFAULT);

        layout = new Borderlayout();
        if (parent != null)
        	layout.setParent(parent);
        else
        	layout.setPage(page);
        LayoutUtils.addSclass(ITheme.LOGIN_WINDOW_CLASS, layout);

        Center center = new Center();
        center.setParent(layout);
        center.setBorder("none");
        center.setFlex(true);
        center.setAutoscroll(true);
        center.setStyle("border: none; background-color: transparent;");

        Vbox vb = new Vbox();
        vb.setParent(center);
        vb.setHeight("100%");
        vb.setWidth("100%");
        vb.setPack("center");
        vb.setAlign("center");
        vb.setStyle("background-color: transparent;");

        LoginWindow loginWindow = new LoginWindow(app);
        loginWindow.setParent(vb);

        try {
        	String right = ITheme.THEME_PATH_PREFIX + theme + ITheme.LOGIN_RIGHT_PANEL_ZUL;
	        PageDefinition pageDefintion = Executions.getCurrent().getPageDefinition(right);
	    	East east = new East();
	    	east.setSclass(ITheme.LOGIN_EAST_PANEL_CLASS);
	    	addContent(east, pageDefintion);
        } catch (Exception e) {}

        try {
	        String left = ITheme.THEME_PATH_PREFIX + theme + ITheme.LOGIN_LEFT_PANEL_ZUL;
	        PageDefinition pageDefintion = Executions.getCurrent().getPageDefinition(left);
	    	West west = new West();
	    	west.setSclass(ITheme.LOGIN_WEST_PANEL_CLASS);
	    	addContent(west, pageDefintion);
        } catch (Exception e){}

        try {
	        String top = ITheme.THEME_PATH_PREFIX + theme + ITheme.LOGIN_TOP_PANEL_ZUL;
	        PageDefinition pageDefintion = Executions.getCurrent().getPageDefinition(top);
	    	North north = new North();
	    	north.setSclass(ITheme.LOGIN_NORTH_PANEL_CLASS);
	    	addContent(north, pageDefintion);
        } catch (Exception e) {}

        try {
	        String bottom = ITheme.THEME_PATH_PREFIX + theme + ITheme.LOGIN_BOTTOM_PANEL_ZUL;
	        PageDefinition pageDefintion = Executions.getCurrent().getPageDefinition(bottom);
	    	South south = new South();
	    	south.setSclass(ITheme.LOGIN_SOUTH_PANEL_CLASS);
	    	addContent(south, pageDefintion);
        } catch (Exception e) {}

        return layout;
    }

    private void addContent(Component parent, PageDefinition page) {
    	layout.appendChild(parent);
    	Executions.createComponents(page, parent, null);
    }

	public void detach() {
		layout.detach();
		layout = null;
	}

	public Component getComponent() {
		return layout;
	}
}
