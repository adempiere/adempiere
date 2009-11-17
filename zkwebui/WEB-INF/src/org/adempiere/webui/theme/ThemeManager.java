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
package org.adempiere.webui.theme;

import org.adempiere.webui.AdempiereWebUI;
import org.compiere.model.MSysConfig;

/**
 *
 * @author hengsin
 *
 */
public final class ThemeManager {

	/**
	 * @return url for large logo
	 */
	public static String getLargeLogo() {
		String theme = getTheme();
		String def = ITheme.THEME_PATH_PREFIX+theme+ITheme.LOGIN_LOGO_IMAGE;
		return MSysConfig.getValue("ZK_LOGO_LARGE", def);
	}

	/**
	 * @return url for small logo
	 */
	public static String getSmallLogo() {
		String theme = getTheme();
		String def = ITheme.THEME_PATH_PREFIX+theme+ITheme.HEADER_LOGO_IMAGE;
		String url = MSysConfig.getValue("ZK_LOGO_SMALL", null);
		if (url == null)
			url = MSysConfig.getValue("WEBUI_LOGOURL", def);
		return url;
	}

	/**
	 * @return name of active theme
	 */
	public static String getTheme() {
		return MSysConfig.getValue(ITheme.ZK_THEME, ITheme.ZK_THEME_DEFAULT);
	}

	/**
	 * @return url of theme stylesheet
	 */
	public static String getStyleSheet() {
		return ITheme.THEME_PATH_PREFIX + getTheme() + ITheme.THEME_STYLESHEET;
	}

	/**
	 * @return url of theme stylesheet by browser
	 */
	public static String getStyleSheetByBrowser() {
		return ITheme.THEME_PATH_PREFIX + getTheme() + ITheme.THEME_STYLESHEET_BY_BROWSER;
	}

	/**
	 * @return title text for the browser window
	 */
	public static String getBrowserTitle() {
		return MSysConfig.getValue("ZK_BROWSER_TITLE", AdempiereWebUI.APP_NAME);
	}

	/**
	 * @return url for right panel
	 */
	public static String getLoginRightPanel() {
		String theme = getTheme();
		return ITheme.THEME_PATH_PREFIX + theme + ITheme.LOGIN_RIGHT_PANEL_ZUL;
	}

	/**
	 * @return url for left panel
	 */
	public static String getLoginLeftPanel() {
		String theme = getTheme();
		return ITheme.THEME_PATH_PREFIX + theme + ITheme.LOGIN_LEFT_PANEL_ZUL;
	}

	/**
	 * @return url for top panel
	 */
	public static String getLoginTopPanel() {
		String theme = getTheme();
		return ITheme.THEME_PATH_PREFIX + theme + ITheme.LOGIN_TOP_PANEL_ZUL;
	}

	/**
	 * @return url for bottom panel
	 */
	public static String getLoginBottomPanel() {
		String theme = getTheme();
		return ITheme.THEME_PATH_PREFIX + theme + ITheme.LOGIN_BOTTOM_PANEL_ZUL;
	}

	/**
	 * @return url for browser icon
	 */
	public static String getBrowserIcon() {
		String theme = getTheme();
		String def = ITheme.THEME_PATH_PREFIX + theme + ITheme.BROWSER_ICON_IMAGE;
		return MSysConfig.getValue("ZK_BROWSER_ICON", def);
	}
}
