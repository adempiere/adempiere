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

import org.zkoss.zul.theme.Themes;

/**
 *
 * @author hengsin
 * 
 * Replaced by ThemeUtils.java, MTheme.java, DefaultTheme.java
 *
 */
@Deprecated
public final class ThemeManager {

	/**
	 * @return url for large logo
	 * Use ThemeUtils.getLargeLogo()
	 */
	@Deprecated
	public static String getLargeLogo() {
		return ThemeUtils.getLargeLogo();
	}

	/**
	 * @return url for small logo
	 * Use ThemeUtils.getSmallLogo()
	 */
	@Deprecated
	public static String getSmallLogo() {
		return ThemeUtils.getSmallLogo();
	}

	/**
	 * @return name of active theme
	 * Use Themes.getCurrentTheme();
	 */
	@Deprecated
	public static String getTheme() {
		return Themes.getCurrentTheme();
	}

	/**
	 * @return url of theme stylesheet
	 * See the AdempiereThemeProvider.java and MThemeResources.java
	 */
	@Deprecated
	public static String getStyleSheet() {
		return DefaultTheme.ZK_DEFAULT_THEME_URI;
	}

	/**
	 * @return url of theme stylesheet by browser
	 * See the AdempiereThemeProvider.java and MThemeResources.java
	 */
	@Deprecated
	public static String getStyleSheetByBrowser() {
		return DefaultTheme.ZK_DEFAULT_THEME_URI;
	}

	/**
	 * @return title text for the browser window
	 * Replaced by ThemeUtils function;
	 */
	@Deprecated
	public static String getBrowserTitle() {
		return DefaultTheme.ZK_BROWSER_TITLE;
	}

	/**
	 * @return url for right panel
	 * Replaced by ThemeUtils function;
	 */
	@Deprecated
	public static String getLoginRightPanel() {
		return ThemeUtils.getLoginRightPanel();
	}

	/**
	 * @return url for left panel
	 * Replaced by ThemeUtils function;
	 */
	@Deprecated
	public static String getLoginLeftPanel() {
		return ThemeUtils.getLoginLeftPanel();
	}

	/**
	 * @return url for top panel
	 * Replaced by ThemeUtils function;
	 */
	@Deprecated
	public static String getLoginTopPanel() {
		return ThemeUtils.getLoginTopPanel();
	}

	/**
	 * @return url for bottom panel
	 * Replaced by ThemeUtils function;
	 */
	@Deprecated
	public static String getLoginBottomPanel() {
		return ThemeUtils.getLoginBottomPanel();
	}

	/**
	 * @return url for browser icon
	 * Replaced by ThemeUtils.getBrowserIcon();
	 */
	@Deprecated
	public static String getBrowserIcon() {
		return ThemeUtils.getBrowserIcon();
	}
}
