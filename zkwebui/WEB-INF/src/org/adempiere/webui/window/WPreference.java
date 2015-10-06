/******************************************************************************
 * Copyright (C) 2009 Low Heng Sin                                            *
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
package org.adempiere.webui.window;

import org.adempiere.webui.component.Combobox;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.ToolBar;
import org.adempiere.webui.component.ToolBarButton;
import org.adempiere.webui.editor.WTableDirEditor;
import org.adempiere.webui.editor.WYesNoEditor;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.theme.ThemeUtils;
import org.adempiere.webui.util.UserPreference;
import org.compiere.model.MColumn;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MTheme;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.web.fn.ServletFns;
import org.zkoss.web.fn.ServletFns;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.theme.Themes;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Div;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Space;

/**
 *
 * @author hengsin
 *
 */
public class WPreference extends Popup implements EventListener {

	private static final long serialVersionUID = 7163067116469715021L;
	private WYesNoEditor autoCommit;
	private WYesNoEditor autoNew;
	private WYesNoEditor tabCollapsible;
	private Combobox tabPlacement;
	private WTableDirEditor selectedTheme;
	private int m_preferredTheme_ID = 0;

	public WPreference() {
		super();
		init();
	}

	private void init() {
		ThemeUtils.addSclass("ad-wpreference", this);

		Div div = new Div();
		autoCommit = new WYesNoEditor("AutoCommit", Msg.getMsg(Env.getCtx(), "AutoCommit", true),
				null, false, false, true);
		div.appendChild(autoCommit.getComponent());
		autoCommit.getComponent().setTooltiptext(Msg.getMsg(Env.getCtx(), "AutoCommit", false));
		autoCommit.getComponent().setAttribute("zk_component_ID", "Preference_autoCommit");
		autoCommit.getComponent().setAttribute("zk_component_prefix", "Preference_");
		this.appendChild(div);

		autoNew = new WYesNoEditor("AutoNew", Msg.getMsg(Env.getCtx(), "AutoNew", true),
				null, false, false, true);
		autoNew.getComponent().setTooltiptext(Msg.getMsg(Env.getCtx(), "AutoNew", false));
		autoNew.getComponent().setAttribute("zk_component_ID", "Preference_autoNew");
		autoNew.getComponent().setAttribute("zk_component_prefix", "Preference_");
		div = new Div();
		div.appendChild(autoNew.getComponent());
		this.appendChild(div);

		tabCollapsible = new WYesNoEditor("WindowTabCollapsible", Msg.getMsg(Env.getCtx(), "WindowTabCollapsible", true),
				null, false, false, true);
		tabCollapsible.getComponent().setTooltiptext(Msg.getMsg(Env.getCtx(), "WindowTabCollapsible", false));
		tabCollapsible.getComponent().setAttribute("zk_component_ID", "Preference_tabCollapsible");
		tabCollapsible.getComponent().setAttribute("zk_component_prefix", "Preference_");
		div = new Div();
		div.appendChild(tabCollapsible.getComponent());
		this.appendChild(div);

		div = new Div();
		Label label = new Label(Msg.getMsg(Env.getCtx(), "WindowTabPlacement", true));
		label.setTooltiptext(Msg.getMsg(Env.getCtx(), "WindowTabPlacement", false));
		div.appendChild(label);
		div.appendChild(new Space());
		tabPlacement = new Combobox();
		tabPlacement.appendItem(Msg.getMsg(Env.getCtx(), "Left", true), "Left");
		tabPlacement.appendItem(Msg.getMsg(Env.getCtx(), "Right", true), "Right");
		tabPlacement.setAttribute("zk_component_ID", "Preference_tabPlacement");
		tabPlacement.setAttribute("zk_component_prefix", "Preference_");
		div.appendChild(tabPlacement);
		this.appendChild(div);

		div = new Div();
		label = new Label(Msg.getMsg(Env.getCtx(), "Theme", true));
		label.setTooltiptext(Msg.getMsg(Env.getCtx(), "SelectTheme", false));
		div.appendChild(label);
		div.appendChild(new Space());
		selectedTheme = new WTableDirEditor("AD_Theme_ID", true, false, true, 
				MLookupFactory.get(Env.getCtx(), 0, 0,  
						MColumn.getColumn_ID(MTheme.Table_Name, MTheme.COLUMNNAME_AD_Theme_ID), DisplayType.TableDir));
		selectedTheme.getComponent().addEventListener(Events.ON_CHANGE, this);
		selectedTheme.getComponent().setAttribute("zk_component_ID", "Preference_SetTheme");
		selectedTheme.getComponent().setAttribute("zk_component_prefix", "Preference_");
		div.appendChild(selectedTheme.getComponent());
		this.appendChild(div);
		
		Separator separator = new Separator();
		separator.setSpacing("20px");
		div = new Div();
		div.appendChild(separator);
		this.appendChild(div);

		ToolBar toolbar = new ToolBar();
		toolbar.setAlign("end");
		this.appendChild(toolbar);
		ToolBarButton btn = new ToolBarButton("");
        btn.setName("btnSave");
        btn.setImage(ServletFns.resolveThemeURL("~./images/Save24.png"));
        btn.setTooltiptext(Msg.getMsg(Env.getCtx(),"Save"));
        btn.addEventListener(Events.ON_CLICK, this);
        toolbar.appendChild(btn);

		UserPreference preference = SessionManager.getSessionApplication().getUserPreference();
		if (Integer.parseInt(preference.getProperty(UserPreference.P_ZK_THEME_PREFERENCE)) > 0) {
			m_preferredTheme_ID = Integer.parseInt(preference.getProperty(UserPreference.P_ZK_THEME_PREFERENCE));
		}
		autoCommit.setValue(preference.getProperty(UserPreference.P_AUTO_COMMIT));
		autoNew.setValue(preference.getProperty(UserPreference.P_AUTO_NEW));
		tabCollapsible.setValue(preference.getProperty(UserPreference.P_WINDOW_TAB_COLLAPSIBLE));
		tabPlacement.setValue(preference.getProperty(UserPreference.P_WINDOW_TAB_PLACEMENT));
		selectedTheme.setValue(m_preferredTheme_ID);
	}

	public void onEvent(Event event) throws Exception {
		if (Events.ON_CLICK.equals(event.getName())) {
			onSave();
		}
	}

	private void onSave() {
		
		UserPreference preference = SessionManager.getSessionApplication().getUserPreference();
		
		boolean changeTheme = false;
		int newTheme_ID = 0;
		
		// Set theme
		if (selectedTheme.getValue() != null && ((Integer) selectedTheme.getValue()).intValue() > 0) {
			newTheme_ID = ((Integer) selectedTheme.getValue()).intValue();
		}
			
		if (newTheme_ID != preference.getPropertyAsInt(UserPreference.P_ZK_THEME_PREFERENCE)) {
			changeTheme = true;
		}
		
		preference.setProperty(UserPreference.P_AUTO_COMMIT,
				(Boolean)autoCommit.getValue() ? "Y" : "N");
		preference.setProperty(UserPreference.P_AUTO_NEW,
				(Boolean)autoNew.getValue() ? "Y" : "N");
		preference.setProperty(UserPreference.P_WINDOW_TAB_COLLAPSIBLE,
				(Boolean)tabCollapsible.getValue() ? "Y" : "N");
		preference.setProperty(UserPreference.P_WINDOW_TAB_PLACEMENT,
				(String)tabPlacement.getSelectedItem().getValue());
		preference.setProperty(UserPreference.P_ZK_THEME_PREFERENCE,
				newTheme_ID);

		preference.savePreference();

		//update context
		Env.setAutoCommit(Env.getCtx(), "y".equalsIgnoreCase(preference.getProperty(UserPreference.P_AUTO_COMMIT)));
		Env.setAutoNew(Env.getCtx(), "y".equalsIgnoreCase(preference.getProperty(UserPreference.P_AUTO_NEW)));

		this.detach();
		
		if (changeTheme) {
			Executions.sendRedirect(null);  // Reload the page - it will use the new theme
		}
	}
}
