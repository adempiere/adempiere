/******************************************************************************
 * Copyright (C) 2011 Adaxa Pty Ltd                                            *
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

import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.VerticalBox;
import org.adempiere.webui.component.Window;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Div;
import org.zkoss.zul.Html;
import org.zkoss.zul.Separator;

/**
 * 
 * @author Paul Bowden
 *
 */
public class WAlertDialog extends Window implements EventListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6973270705340750227L;
	private String text;

	/**
	 * 
	 * @param title
	 * @param text
	 * @param editable
	 * @param maxSize
	 */
	public WAlertDialog(String text) {
		super();
		setTitle(Msg.getMsg(Env.getCtx(), "Note"));
		this.text = text;
		
		init();
	}
	
	private void init() {
		setBorder("normal");
		
		VerticalBox vbox = new VerticalBox();
		appendChild(vbox);
		Div div = new Div();
		div.setHeight("500px");
		div.setWidth("700px");
		div.setStyle("overflow: auto; border: 1px solid");
		vbox.appendChild(div);
		Html html = new Html();
		div.appendChild(html);
		html.setContent(text);
		
		vbox.appendChild(new Separator());
		
		ConfirmPanel confirmPanel = new ConfirmPanel(false);
		vbox.appendChild(confirmPanel);
		confirmPanel.addActionListener(this);
		
	}

	/**
	 * @param event
	 */
	public void onEvent(Event event) throws Exception {
		if (event.getTarget().getId().equals(ConfirmPanel.A_OK)) {
			detach();
			}
	}
}
