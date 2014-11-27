/******************************************************************************
 * Copyright (C) 2008 Low Heng Sin  All Rights Reserved.                      *
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

import java.util.Properties;

import org.adempiere.webui.component.Label;
import org.compiere.model.MSysConfig;
import org.compiere.model.MTheme;
import org.compiere.util.Env;
import org.zkoss.zhtml.impl.AbstractTag;
import org.zkoss.zk.au.out.AuScript;
import org.zkoss.zk.ui.AbstractComponent;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.sys.PageCtrl;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Div;
import org.zkoss.util.media.ContentTypes;
import org.zkoss.web.fn.ServletFns;
import org.zkoss.web.theme.StandardTheme.ThemeOrigin;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WebApps;
import org.zkoss.zul.theme.Themes;

/**
 * 
 * @author Low Heng Sin
 * @author Michael Mckay - modified for the ZK7  theme system
 *
 */
public final class ThemeUtils {

	/**
	 * @param layout
	 */
	public static void sendDeferLayoutEvent(Borderlayout layout, int timeout) {
		StringBuilder content = new StringBuilder();		
		content.append("ad_deferRenderBorderLayout('")
			   .append(layout.getUuid())
			   .append("',").append(timeout).append(");");
		
		AuScript as = new AuScript(null, content.toString());
		Clients.response("deferRenderBorderLayout", as);		
	}
	
	/**
	 * 
	 * @param cls
	 * @param target
	 */
	public static void addSclass(String cls,  Object target) {
		String sclass = getSclass(target);
		if (!hasSclass(cls, target)) {
			sclass = (sclass == null ? cls : sclass + " " + cls);
			setSclass(sclass, target);
		}
	}

	/**
	 * Removes the string cls from the Sclass if it is found.
	 * @param cls
	 * @param target
	 */
	public static void removeSclass(String cls,  Object target) {
		String sclass = " " + getSclass(target) + " ";
		if (hasSclass(cls, target)) {
			sclass = sclass.replace(cls, "").trim();
			setSclass(sclass, target);
		}
	}

	
	private static String getSclass(Object target) {
		String sclass = "";
		if (target instanceof HtmlBasedComponent) {
			sclass = ((HtmlBasedComponent) target).getSclass();
		}
		if (target instanceof AbstractTag) {
			sclass = ((AbstractTag) target).getSclass();
		}
		return sclass;
	}

	private static void setSclass(String sclass, Object target) {
		if (target instanceof HtmlBasedComponent) {
			((HtmlBasedComponent) target).setSclass(sclass);
		}
		if (target instanceof AbstractTag) {
			((AbstractTag) target).setSclass(sclass);
		}
	}

	
	/**
	 * 
	 * @param cls
	 * @param target
	 * @return boolean
	 */
	public static boolean hasSclass(String cls, Object target) {
		String sclass = getSclass(target);
		if (sclass == null)
			sclass = "";
		return cls == null
				|| ((" " + sclass + " ").indexOf(" " + cls + " ") > -1);
	}	
	
	public static Component makeRightAlign(Label label) {
		Div div = new Div();
		div.setStyle("text-align: right");
		div.appendChild(label);
		
		return div;
	}
	
	/**
	 * Set the current theme to the system default theme.
	 * @return True if a redirect has been requested.  Use this to stop loading during page setup.
	 */
	public static boolean setSystemDefaultTheme() {

		// Set the system default theme.  This will fall back to defaults
		// if the MTheme has no default themes.
		DefaultTheme dt = new DefaultTheme();		
		Execution exec = Executions.getCurrent();
		String themeName = dt.get_themeName();
		
		String currentTheme = Themes.getCurrentTheme();
		if (currentTheme.equals(themeName)) {
			//  No need to do anything
			return false;
		}

		Themes.setTheme(exec, themeName);
		Executions.sendRedirect(null);  // reload the current page
		return true;
	}

	/**
	 * Register this theme
	 */
	public static void register(MTheme theme) {

		ThemeOrigin themeOriginEnum = ThemeOrigin.valueOf(DefaultTheme.ZK_DEFAULT_THEME_ORIGIN);
		try
		{
			themeOriginEnum = ThemeOrigin.valueOf(theme.get_origin());  // Has to match exactly
		}
		catch (IllegalArgumentException 		// - if this enum type has no constant with the specified name 
			|  NullPointerException 			// - if the argument is null
				e)
		{
			// Discard
		}
		Themes.register(theme.get_themeName(), theme.get_displayName(), theme.get_priority(), themeOriginEnum);
        // Only ZK EE users could use tablet theme
        if ("EE".equals(WebApps.getEdition())) 
        {
            Themes.register("tablet:" + theme.get_themeName(), theme.get_displayName(), theme.get_priority(), themeOriginEnum);
        }   
	}
	
	/**
	 * Register all active themes
	 */
	public static void registerAllThemes(Properties ctx) {
		
		// Register the system default - needed in the case where there are no themes in
		// the AD_Theme table
		DefaultTheme dt = new DefaultTheme();
		dt.registerDefaultTheme();
		
		// Register everything else - duplications will only update the theme info.
		for( MTheme theme: MTheme.getAllThemes(ctx)) {
			register(theme);
		}
	}

	/**
	 * Make this theme the current theme.  Only applies to ZK
	 * @return  - true if a redirect/reload has been requested.  Use this to stop loading
	 * other elements of the page during setup.
	 */
	public static boolean makeCurrent(MTheme theme) {
		
		if (theme == null) {
			return false;
		}
		Execution exec = Executions.getCurrent();
		String themeName = "";
		
		if (theme.get_ID() <= 0) {
			// No theme was identified, use the default
			DefaultTheme dt = new DefaultTheme();
			themeName = dt.get_themeName();
		}
		else {
			themeName = theme.get_themeName();
		}
		String currentTheme = Themes.getCurrentTheme();
		if (!currentTheme.equals(themeName)) {
			Themes.setTheme(exec, themeName);
			Executions.sendRedirect(null);  // reload the current page
			return true;
		}
		return false;
	}

	/**
	 * @return theme resolved url for the large logo of the current theme
	 */
	public static String getLargeLogo() {
		String url = "";
		String currentTheme = Themes.getCurrentTheme();
		MTheme theme = MTheme.get(Env.getCtx(), currentTheme);
		url = theme.getLogo_Large_Image();
		if (url == null || url.length() == 0) {
			url = MSysConfig.getValue("ZK_LOGO_LARGE_IMAGE", DefaultTheme.ZK_LOGO_LARGE_IMAGE); 
			// For backwards compatibility
			if (url == null || url.length() == 0) {
				url = MSysConfig.getValue("ZK_LOGO_LARGE", DefaultTheme.ZK_LOGO_LARGE_IMAGE);
			}
		}
		return ServletFns.resolveThemeURL(url);
	}

	/**
	 * @return theme resolved url for the small logo of the current theme
	 */
	public static String getSmallLogo() {
		String url = "";
		String currentTheme = Themes.getCurrentTheme();
		MTheme theme = MTheme.get(Env.getCtx(), currentTheme);
		url = theme.getLogo_Small_Image();
		if (url == null || url.length() == 0) {
			url = MSysConfig.getValue("ZK_LOGO_SMALL_IMAGE", DefaultTheme.ZK_LOGO_SMALL_IMAGE); 
			// For backwards compatibility
			if (url == null || url.length() == 0) {
				url = MSysConfig.getValue("ZK_LOGO_SMALL", DefaultTheme.ZK_LOGO_SMALL_IMAGE); 
				if (url == null || url.length() == 0) {
					url = MSysConfig.getValue("WEBUI_LOGOURL", DefaultTheme.ZK_LOGO_SMALL_IMAGE);
				}
			}
		}
		return ServletFns.resolveThemeURL(url);
	}

	/**
	 * @return theme specific url for the small logo of the current theme
	 */
	public static String getBrowserTitle() {
		String title = "";
		String currentTheme = Themes.getCurrentTheme();
		MTheme theme = MTheme.get(Env.getCtx(), currentTheme);
		title = theme.getBrowser_Title();
		if (title == null || title.length() == 0) {
			title = MSysConfig.getValue("ZK_BROWSER_TITLE", DefaultTheme.ZK_BROWSER_TITLE); 
		}
		return title;
	}

	/**
	 * @return resolved url for browser icon
	 * Replaced by ThemeUtils.getBrowserIcon();
	 */
	public static String getBrowserIcon() {
		String url = "";
		String currentTheme = Themes.getCurrentTheme();
		MTheme theme = MTheme.get(Env.getCtx(), currentTheme);
		url = theme.getBrowser_Icon_Image();
		if (url == null || url.length() == 0) {
			url = MSysConfig.getValue("ZK_BROWSER_ICON", DefaultTheme.ZK_BROWSER_ICON_IMAGE); 
		}
		return ServletFns.resolveThemeURL(url);
	}

	public static String getFaviconLinks() {
		// Get the browser icon from the current theme.  It should be an image or ico file.
		String url = ThemeUtils.getBrowserIcon();
		if (url == null || url.length() == 0) {
			return "";
		}
		
		//  Find the mime_type of the icon. Assume the url ends with the file extension.
		//  This could be anything so at least verify that the mime_type starts with "image".
		String mime = ContentTypes.getContentType(url.substring(url.lastIndexOf(".")));
		if (mime == null || mime.length() == 0 || !mime.startsWith("image")) {
			return "";
		}
		
		// Build the html link statement
		StringBuffer link = new StringBuffer("<link rel=\"shortcut icon\" type=\"")
		.append(mime).append("\" href=\"/webui").append(url)
		.append("\"><link rel=\"icon\" type=\"")
		.append(mime).append("\" href=\"/webui").append(url)
		.append("\">"); 

		return link.toString();
	}

	/**
	 * Add an icon (favicon or image) and title to the browser based 
	 * on the current theme. The icon image has to be identified in 
	 * the AD_Theme table
	 * 
	 * @param page the page to which to add the icon and title
	 */
	public static void addBrowserIconAndTitle(Page page) {
    	
		// Set that page title from the current theme
        page.setTitle(ThemeUtils.getBrowserTitle());
        
        // Set the favicon according to the theme
		String link = ThemeUtils.getFaviconLinks();
		if (link != null && link.length() > 0) {
			((PageCtrl) page).addBeforeHeadTags(link);
		}
	}
	
	/**
	 * @return url for right panel
	 */
	public static String getLoginRightPanel() {
		String url = "";
		String currentTheme = Themes.getCurrentTheme();
		MTheme theme = MTheme.get(Env.getCtx(), currentTheme);
		url = theme.getLogin_Right_Panel_Zul();
		if (url == null || url.length() == 0) {
			url = MSysConfig.getValue("ZK_LOGIN_RIGHT_PANEL_ZUL", DefaultTheme.ZK_LOGIN_RIGHT_PANEL_ZUL); 
		}
		return ServletFns.resolveThemeURL(url);
	}

	/**
	 * @return url for left panel
	 */
	public static String getLoginLeftPanel() {
		String url = "";
		String currentTheme = Themes.getCurrentTheme();
		MTheme theme = MTheme.get(Env.getCtx(), currentTheme);
		url = theme.getLogin_Left_Panel_Zul();
		if (url == null || url.length() == 0) {
			url = MSysConfig.getValue("ZK_LOGIN_LEFT_PANEL_ZUL", DefaultTheme.ZK_LOGIN_LEFT_PANEL_ZUL); 
		}
		return ServletFns.resolveThemeURL(url);
	}

	/**
	 * @return url for top panel
	 */
	public static String getLoginTopPanel() {
		String url = "";
		String currentTheme = Themes.getCurrentTheme();
		MTheme theme = MTheme.get(Env.getCtx(), currentTheme);
		url = theme.getLogin_Top_Panel_Zul();
		if (url == null || url.length() == 0) {
			url = MSysConfig.getValue("ZK_LOGIN_TOP_PANEL_ZUL", DefaultTheme.ZK_LOGIN_TOP_PANEL_ZUL); 
		}
		return ServletFns.resolveThemeURL(url);
	}

	/**
	 * @return url for bottom panel
	 */
	public static String getLoginBottomPanel() {
		String url = "";
		String currentTheme = Themes.getCurrentTheme();
		MTheme theme = MTheme.get(Env.getCtx(), currentTheme);
		url = theme.getLogin_Bottom_Panel_Zul();
		if (url == null || url.length() == 0) {
			url = MSysConfig.getValue("ZK_LOGIN_BOTTOM_PANEL_ZUL", DefaultTheme.ZK_LOGIN_BOTTOM_PANEL_ZUL); 
		}
		return ServletFns.resolveThemeURL(url);
	}

}
