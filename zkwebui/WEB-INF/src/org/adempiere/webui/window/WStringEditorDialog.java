/******************************************************************************
 * Copyright (C) 2008 Low Heng Sin                                            *
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
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.VerticalBox;
import org.adempiere.webui.component.Window;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;

/**
 * 
 * @author Low Heng Sin
 *
 */
public class WStringEditorDialog extends Window implements EventListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3852236029054284848L;
	private boolean editable;
	private int maxSize;
	private String text;
	private boolean cancelled;
	private Textbox textBox;
	private String originalText;

	
	/**
	 * 
	 * @param title
	 * @param text
	 * @param editable
	 * @param maxSize
	 */
	public WStringEditorDialog(String title, String text, boolean editable, int maxSize) {
		super();
		setTitle(title);
		this.setClosable(true);
		this.editable = editable;
		this.maxSize = maxSize;
		originalText = text;
		this.text = text;
		this.setTooltiptext(Msg.getMsg(Env.getCtx(), "WStringEditorDialog.tooltip"));
		init();
	}
	
	private void init() {
		setBorder("normal");
		
		VerticalBox vbox = new VerticalBox();
		appendChild(vbox);
				
		textBox = new Textbox(text);
		textBox.setCols(maxSize);
//		textBox.setRows(30);
		textBox.setEnabled(editable);
//		textBox.setWidth("100px");
//		textBox.setHeight("500px");
		textBox.addEventListener(Events.ON_CHANGE, this);
		textBox.addEventListener(Events.ON_OK, this);
		textBox.addEventListener(Events.ON_CANCEL, this);
		
		vbox.appendChild(textBox);
						
	}

	/**
	 * @param event
	 */
	public void onEvent(Event event) throws Exception {
		if (Events.ON_CANCEL.equals(event.getName())) {
			// Hit <Esc>
			cancelled = true;
			text = originalText;
			detach();
		} else if (Events.ON_CHANGE.equals(event.getName())) {
			if (editable) {
				if (textBox.getText() != null && !textBox.getText().isEmpty())
					text = textBox.getText();
				else
					text = originalText;
			}
		} else if (Events.ON_OK.equals(event.getName())) {
			detach();
		}
	}
	
	
	/**
	 * 
	 * @return boolean
	 */
	public boolean isCancelled() {
		return cancelled;
	}
	
	/**
	 * 
	 * @return text
	 */
	public String getText() {
		return text;
	}

}
