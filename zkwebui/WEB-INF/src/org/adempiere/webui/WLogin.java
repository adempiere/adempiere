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

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.part.AbstractUIPart;
import org.adempiere.webui.theme.ITheme;
import org.adempiere.webui.theme.ThemeUtils;
import org.adempiere.webui.window.LoginWindow;
import org.zkoss.web.fn.ServletFns;
import org.zkoss.zhtml.Text;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.metainfo.PageDefinition;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Center;
import org.zkoss.zul.East;
import org.zkoss.zul.North;
import org.zkoss.zul.South;
import org.zkoss.zul.West;
import org.zkoss.zul.Div;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

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
	private Window browserWarningWindow;

    public WLogin(IWebClient app)
    {
        this.app = app;
        ThemeUtils.addSclass("ad-wlogin", this);
    }

    protected Component doCreatePart(Component parent)
    {
        layout = new Borderlayout();
        if (parent != null)
        	layout.setParent(parent);
        else
        	layout.setPage(page);
        ThemeUtils.addSclass("ad-wlogin-layout", layout);

        Center center = new Center();
        center.setParent(layout);
        center.setHflex("true");
        center.setVflex("true");
        center.setAutoscroll(true);
        ThemeUtils.addSclass("ad-wlogin-layout-center", center);

        Vbox vb = new Vbox();
        vb.setParent(center);
        vb.setPack("center");
        vb.setAlign("center");
        vb.setWidth("100%");
        vb.setHeight("100%");

        LoginWindow loginWindow = new LoginWindow(app);
        loginWindow.setParent(vb);

        if (!AEnv.isBrowserSupported())
        {
        	//TODO: localization
        	String msg = "You might experience slow performance and user interface anomalies using your current browser to access the application. We recommend the use of Firefox, Google Chrome or Apple Safari.";
        	browserWarningWindow = new Window();
        	ThemeUtils.addSclass("ad-wlogin-browser-not-supported", browserWarningWindow);
        	Div div = new Div();
        	div.appendChild(new Text(msg));
        	browserWarningWindow.setPosition("top,right");
        	browserWarningWindow.appendChild(div);
        	browserWarningWindow.setPage(page);
        	browserWarningWindow.doOverlapped();
        }
        
        try {
        	String right = ThemeUtils.getLoginRightPanel();
	        PageDefinition pageDefintion = Executions.getCurrent().getPageDefinition(right);
	    	East east = new East();
	    	ThemeUtils.addSclass("ad-wlogin-east-panel", east);
	    	addContent(east, pageDefintion);
        } catch (Exception e) {
        	//ignore page not found exception
        	if (e instanceof UiException) {
        		if (!(e.getMessage() != null && e.getMessage().startsWith("Page not found"))) {
        			e.printStackTrace();
        		}
        	} else {
        		e.printStackTrace();
        	}
        }

        try {
	        String left = ThemeUtils.getLoginLeftPanel();
	        PageDefinition pageDefintion = Executions.getCurrent().getPageDefinition(left);
	    	West west = new West();
	    	ThemeUtils.addSclass("ad-wlogin-west-panel", west);
	    	addContent(west, pageDefintion);
        } catch (Exception e){
        	//ignore page not found exception
        	if (e instanceof UiException) {
        		if (!(e.getMessage() != null && e.getMessage().startsWith("Page not found"))) {
        			e.printStackTrace();
        		}
        	} else {
        		e.printStackTrace();
        	}
        }

        try {
	        String top = ThemeUtils.getLoginTopPanel();
	        PageDefinition pageDefintion = Executions.getCurrent().getPageDefinition(top);
	    	North north = new North();
	    	ThemeUtils.addSclass("ad-wlogin-north-panel", north);
	    	addContent(north, pageDefintion);
        } catch (Exception e) {
        	//ignore page not found exception
        	if (e instanceof UiException) {
        		if (!(e.getMessage() != null && e.getMessage().startsWith("Page not found"))) {
        			e.printStackTrace();
        		}
        	} else {
        		e.printStackTrace();
        	}
        }

        try {
	        String bottom = ThemeUtils.getLoginBottomPanel();
	        PageDefinition pageDefintion = Executions.getCurrent().getPageDefinition(bottom);
	    	South south = new South();
	    	ThemeUtils.addSclass("ad-wlogin-south-panel", south);
	    	addContent(south, pageDefintion);
        } catch (Exception e) {
        	//ignore page not found exception
        	if (e instanceof UiException) {
        		if (!(e.getMessage() != null && e.getMessage().startsWith("Page not found"))) {
        			e.printStackTrace();
        		}
        	} else {
        		e.printStackTrace();
        	}
        }

        return layout;
    }

    private void addContent(Component parent, PageDefinition page) {
    	layout.appendChild(parent);
    	Executions.createComponents(page, parent, null);
    }

	public void detach() {
		layout.detach();
		layout = null;
		if (browserWarningWindow != null)
			browserWarningWindow.detach();
	}

	public Component getComponent() {
		return layout;
	}
}
