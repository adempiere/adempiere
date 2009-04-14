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
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Tab;
import org.adempiere.webui.component.Tabbox;
import org.adempiere.webui.component.Tabpanel;
import org.adempiere.webui.component.Tabpanels;
import org.adempiere.webui.component.Tabs;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.VerticalBox;
import org.adempiere.webui.component.Window;
import org.zkforge.fckez.FCKeditor;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Div;
import org.zkoss.zul.Html;
import org.zkoss.zul.Separator;

/**
 * 
 * @author Low Heng Sin
 *
 */
public class WTextEditorDialog extends Window implements EventListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3852236029054284848L;
	private boolean editable;
	private int maxSize;
	private String text;
	private boolean cancelled;
	private Tabbox tabbox;
	private Textbox textBox;
	private FCKeditor editor;
	private Label status;

	/**
	 * 
	 * @param title
	 * @param text
	 * @param editable
	 * @param maxSize
	 */
	public WTextEditorDialog(String title, String text, boolean editable, int maxSize) {
		super();
		setTitle(title);
		this.editable = editable;
		this.maxSize = maxSize;
		this.text = text;
		
		init();
	}
	
	private void init() {
		setBorder("normal");
		
		VerticalBox vbox = new VerticalBox();
		appendChild(vbox);
		
		tabbox = new Tabbox();
		vbox.appendChild(tabbox);
		Tabs tabs = new Tabs();
		tabbox.appendChild(tabs);
		Tabpanels tabPanels = new Tabpanels();
		tabbox.appendChild(tabPanels);
		
		Tab tab = new Tab("Text");
		tabs.appendChild(tab);
		
		Tabpanel tabPanel = new Tabpanel();
		tabPanels.appendChild(tabPanel);
		textBox = new Textbox(text);
		textBox.setCols(80);
		textBox.setRows(30);
		textBox.setEnabled(editable);
		textBox.setWidth("700px");
		textBox.setHeight("500px");
		tabPanel.appendChild(textBox);
		
		tab = new Tab("HTML");
		tabs.appendChild(tab);
		
		tabPanel = new Tabpanel();
		tabPanels.appendChild(tabPanel);
		if (editable) {
			editor = new FCKeditor();
			tabPanel.appendChild(editor);
			editor.setWidth("700px");
			editor.setHeight("500px");
			editor.setValue(text);
		} else {
			Div div = new Div();
			div.setHeight("500px");
			div.setWidth("700px");
			div.setStyle("overflow: auto; border: 1px solid");
			tabPanel.appendChild(div);
			Html html = new Html();
			div.appendChild(html);
			html.setContent(text);
		}
		
		vbox.appendChild(new Separator());
		
		ConfirmPanel confirmPanel = new ConfirmPanel(true);
		vbox.appendChild(confirmPanel);
		confirmPanel.addButton(confirmPanel.createButton(ConfirmPanel.A_RESET));
		confirmPanel.addActionListener(this);
		
		if (maxSize > 0) {
			status = new Label();			
			appendChild(status);
			updateStatus(text.length());
			
			status.setStyle("margin-top:10px;");
			textBox.addEventListener(Events.ON_CHANGE, this);
			editor.addEventListener(Events.ON_CHANGE, this);
		}		
		
		tabbox.addEventListener(Events.ON_SELECT, this);
	}

	/**
	 * @param event
	 */
	public void onEvent(Event event) throws Exception {
		if (event.getTarget().getId().equals(ConfirmPanel.A_CANCEL)) {
			cancelled = true;
			detach();
		} else if (event.getTarget().getId().equals(ConfirmPanel.A_OK)) {
			if (editable) {
				if (tabbox.getSelectedIndex() == 0)
					text = textBox.getText();
				else
					text = editor.getValue();
			}
			detach();
		} else if (event.getTarget().getId().equals(ConfirmPanel.A_RESET)) {
			textBox.setText(text);
			editor.setValue(text);
		} else if (event.getName().equals(Events.ON_SELECT)) {
			if (editable) {
				if (tabbox.getSelectedIndex() == 0) {
					textBox.setText(editor.getValue());
					updateStatus(textBox.getText().length());
				} else {
					editor.setValue(textBox.getText());
					updateStatus(editor.getValue().length());
				}
			}
		} else if (event.getName().equals(Events.ON_CHANGE)) {
			if (event.getTarget() == textBox) {
				updateStatus(textBox.getText().length());
			} else if (event.getTarget() == editor) {
				updateStatus(editor.getValue().length());
			}
		}		
	}
	
	private void updateStatus(int newLength) {
		if (status != null && maxSize > 0) {
			StringBuffer msg = new StringBuffer();
			msg.append(newLength);
			if (newLength == maxSize)
				msg.append(" = ");
			else if (newLength < maxSize)
				msg.append(" < ");
			else
				msg.append(" > ");
			msg.append(maxSize);
			
			status.setValue(msg.toString());	
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
