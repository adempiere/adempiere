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
}
