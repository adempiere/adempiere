/******************************************************************************
 * Copyright (C) 2009 Low Heng Sin                                            *
 * Copyright (C) 2009 Idalica Corporation                                     *
 * Copyright (C) 2012 Michael McKay                                           *
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
package org.adempiere.webui.window;

import java.util.Arrays;

import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.session.SessionManager;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.South;

/**
 * Based on WPreference.java and Info.java
 * @author Michael McKay
 *
 */
public class WContext extends Window implements EventListener {

	
	// TODO - assign serial version ID
	private static final long serialVersionUID = 0;
	private ConfirmPanel confirmPanel;
	
	public WContext() {
		super();
		init();
	}

	private void init() {
		
		setAttribute(Window.MODE_KEY, Window.MODE_MODAL);
		setBorder("normal");
		setClosable(true);
		int height = SessionManager.getAppDesktop().getClientInfo().desktopHeight * 85 / 100;
		int width = SessionManager.getAppDesktop().getClientInfo().desktopWidth * 50 / 100;
		this.setWidth(width + "px");
		this.setHeight(height + "px");
		this.setContentStyle("overflow: auto");
        this.setSizable(true);      
        this.setMaximizable(true);
		this.setTitle(Msg.getMsg(Env.getCtx(), "Context"));

		Textbox contextText = new Textbox();
		contextText.setMultiline(true);
		contextText.setReadonly(true);
		contextText.setWidth("100%");
		contextText.setHeight("100%");

        confirmPanel = new ConfirmPanel(false, false, false, false, false, false);
        confirmPanel.addActionListener(Events.ON_CLICK, this);
        confirmPanel.setStyle("border-top: 2px groove #444; padding-top: 4px");
		
        Borderlayout mainPanel = new Borderlayout();
        //mainPanel.setWidth("100%");
        mainPanel.setHeight("100%");
        Center center = new Center();
        mainPanel.appendChild(center);
        center.appendChild(contextText);
        South south = new South();
        mainPanel.appendChild(south);
        south.appendChild(confirmPanel);

		this.appendChild(mainPanel);

		// Get the context
		String[] contextArray = Env.getEntireContext(Env.getCtx());
		Arrays.sort(contextArray);
		contextText.setText("");
		
		// 	Need a specific format to add the eol character.
		//  Otherwise, could use Arrays.toString(contextArray) 
		StringBuffer sb = new StringBuffer("");
		if (contextArray.length > 0) {
			String eol = System.getProperty("line.separator"); 
			for (int i=0; i < contextArray.length; i++ ) {
				sb.append(contextArray[i] + eol);				
			}
		}
		contextText.setText(sb.toString());
	}

	public void onEvent(Event event) throws Exception {
		if (event.getTarget().equals(confirmPanel.getButton(ConfirmPanel.A_OK))) {
			this.detach();
		}
	}

}
