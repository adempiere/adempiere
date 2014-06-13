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

/**
 * Interface to hold global theme constant
 * @author hengsin
 *
 */
public interface ITheme {

	//theme key in MSysConfig
	public static final String ZK_THEME = "ZK_THEME";
	//default theme
	public static final String ZK_THEME_DEFAULT = "default";
	//theme resource url prefix
	public static final String THEME_PATH_PREFIX = "/theme/";

	//css for login window and box
	public static final String WLOGIN_LAYOUT = "wlogin-layout";
	public static final String WLOGIN_LAYOUT_CENTER = "wlogin-layout-center";
	public static final String LOGIN_WINDOW_CLASS = "login-window";
	public static final String LOGIN_BOX_CLASS = "login-box";
	public static final String LOGIN_BOX_HEADER_CLASS = "login-box-header";
	public static final String LOGIN_BOX_HEADER_TXT_CLASS = "login-box-header-text";
	public static final String LOGIN_BOX_HEADER_LOGO_CLASS = "login-box-header-logo";
	public static final String LOGIN_BOX_BODY_CLASS = "login-box-body";
	public static final String LOGIN_BOX_FOOTER_CLASS = "login-box-footer";
	public static final String LOGIN_BOX_FOOTER_PANEL_CLASS = "login-box-footer-pnl";

	//css for login control
	public static final String LOGIN_BUTTON_CLASS = "login-btn";
	public static final String LOGIN_LABEL_CLASS = "login-label";
	public static final String LOGIN_FIELD_CLASS = "login-field";

	//optional top, bottom, left, right content for the login page
	public static final String LOGIN_NORTH_PANEL_CLASS = "login-north-panel";
	public static final String LOGIN_SOUTH_PANEL_CLASS = "login-south-panel";
	public static final String LOGIN_WEST_PANEL_CLASS = "login-west-panel";
	public static final String LOGIN_EAST_PANEL_CLASS = "login-east-panel";

	//  The following file locations have been moved to the AD_Theme table and MTheme model
	//  to make them variable with the theme.
	//public static final String LOGIN_TOP_PANEL_ZUL = "~./zul/login-top.zul";
	//public static final String LOGIN_BOTTOM_PANEL_ZUL = "~./zul/login-bottom.zul";
	//public static final String LOGIN_LEFT_PANEL_ZUL = "~./zul/login-left.zul";
	//public static final String LOGIN_RIGHT_PANEL_ZUL = "~./zul/login-right.zul";

	// Defalut logo file names.  The "~." path element will be replaced
	// by the active theme folder/jar path through MTheme or through the 
	// system configurator.  See ThemeUtils.java
	// System Config ZK_LOGO_LARGE
	//public static final String LOGO_LARGE_IMAGE = "~./images/login-logo.png";
	// System Config ZK_LOGO_SMALL or WEBUI_LOGOURL
	//public static final String LOGO_SMALL_IMAGE = "~./images/header-logo.png";
	// System Config ZK_BROWSER_ICON
	//public static final String BROWSER_ICON_IMAGE= "~./images/icon.png";
}
