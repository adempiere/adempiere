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
package org.adempiere.webui.component;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.adempiere.webui.theme.ThemeUtils;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.theme.ThemeUtils;
import org.zkoss.zhtml.Table;
import org.zkoss.zhtml.Td;
import org.zkoss.zhtml.Tr;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Div;

/**
 * @author Low Heng Sin
 */
public class EditorBox extends Div {
	/**
	 *
	 */
	private static final long serialVersionUID = -3152111756471436612L;
	protected PropertyChangeSupport m_propertyChangeListeners = new PropertyChangeSupport(
			this);
	protected Textbox txt;
	protected Button btn;
	protected Td btnColumn;

	public EditorBox() {
		initComponents();
	}

	/**
	 * @param text
	 */
	public EditorBox(String text) {
		ThemeUtils.addSclass("ad-editorbox", this);
		initComponents();
		setText(text);
	}

	/**
	 * @param imageSrc
	 */
	public void setButtonImage(String imageSrc) {
		btn.setImage(imageSrc);
	}

	private void initComponents() {
		
		Table grid = new Table();
		ThemeUtils.addSclass("ad-editorbox-grid", grid);
		appendChild(grid);

		Tr tr = new Tr();
		ThemeUtils.addSclass("ad-editorbox-row", tr);
		grid.appendChild(tr);

		Td td = new Td();
		ThemeUtils.addSclass("ad-editorbox-cell-input", td);
		tr.appendChild(td);
		txt = new Textbox();
		ThemeUtils.addSclass("ad-editorbox-input", txt);
		td.appendChild(txt);

		btnColumn = new Td();
		tr.appendChild(btnColumn);
		ThemeUtils.addSclass("ad-editorbox-cell-button", btnColumn);
		btn = new Button();
		btn.setTabindex(-1);
		ThemeUtils.addSclass("ad-editorbox-button", btn);
		btnColumn.appendChild(btn);
	}

	/**
	 * @return textbox component
	 */
	public Textbox getTextbox() {
		return txt;
	}

	/**
	 * @param value
	 */
	public void setText(String value) {
		txt.setText(value);
	}

	/**
	 * @return text
	 */
	public String getText() {
		return txt.getText();
	}

	/**
	 * @param enabled
	 */
	public void setEnabled(boolean enabled) {
		txt.setReadonly(!enabled);
		
		btn.setEnabled(enabled);
		btn.setVisible(enabled);
		btnColumn.setVisible(enabled);
		if (enabled)
			ThemeUtils.addSclass("enabled", btnColumn);
		else
			ThemeUtils.removeSclass("enabled", btnColumn);
	}

	/**
	 * @param enabled
	 */
	public void setEnabled(boolean enabled, boolean keepButton) {
		txt.setReadonly(!enabled);
		
		btn.setEnabled(keepButton);
		btn.setVisible(keepButton);
		btnColumn.setVisible(keepButton);
		if (keepButton)
			ThemeUtils.addSclass("enabled", btnColumn);
		else
			ThemeUtils.removeSclass("enabled", btnColumn);
	}

	/**
	 * @return boolean
	 */
	public boolean isEnabled() {
		return !txt.isReadonly();
	}

	/**
	 * @param evtnm
	 * @param listener
	 */
	public boolean addEventListener(String evtnm, EventListener listener) {
		if (Events.ON_CLICK.equals(evtnm)) {
			return btn.addEventListener(evtnm, listener);
		} else {
			return txt.addEventListener(evtnm, listener);
		}
	}

	/**
	 * @param l
	 */
	public synchronized void addPropertyChangeListener(PropertyChangeListener l) {
		m_propertyChangeListeners.addPropertyChangeListener(l);
	}

	/**
	 * @param tooltiptext
	 */
	public void setToolTipText(String tooltiptext) {
		txt.setTooltiptext(tooltiptext);
	}
	
	/**
	 * @return Button
	 */
	public Button getButton() {
		return btn;
	}
}
