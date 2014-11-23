/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2014, Michael McKay, All Rights Reserved.                    *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *                                                                            *
 * @Author Michael McKay (mjmckay)                                            *
 *                                                                            *
 ******************************************************************************/

package org.adempiere.webui.theme;

import org.compiere.model.MSysConfig;
import org.compiere.model.MTheme;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.zkoss.web.theme.StandardTheme.ThemeOrigin;
import org.zkoss.web.theme.Theme;
import org.zkoss.zk.ui.WebApps;
import org.zkoss.zul.theme.Themes;

/**
 * Default Theme.  Holds custom information related to the admpiere_default theme
 *
 */
public class DefaultTheme extends Theme {

	/**
	 * 
	 */
	public DefaultTheme() {
		super();
		loadDefaultTheme(); // Based on system configurator
	}

	/**  
	 * Some of these functions have been copied from the 3.5 implementation of 
	 * ThemeManager.java and modified to work with the new version 7.0 theme 
	 * system.
	 * 
	 * Themes need to be registered.
	 * The user could switch to any registered themes by setting a cookie or a library property.
	 *	
	 *	Standard theme resolution checks the theme settings in the following order.
	 *		
	 *		1    Cookies
	 *		2    Library property
	 *		3    Theme priority 
	 *		
	 *		Dynamically switching themes using Cookies
	 *
	 *			Themes.setTheme(Executions.getCurrent(), "custom");
	 *			Executions.sendRedirect();
	 *		
	 *		Internally, CookieThemeResolver provides this functionality.
	 *
	 *		Dynamically switching themes using Library Property
	 *		
	 *		A Library property could be used to assign a preferred theme when the current theme setting could not be obtained from Cookies.
	 *		
	 *		Programmatically:
	 *			Library.setProperty("org.zkoss.theme.preferred", "custom");
	 *			Executions.sendRedirect();
	 *		
	 *		Declaratively: in (WEB-INF/zk.xml)
	 *			<library-property>
	 *		    	<name>org.zkoss.theme.preferred</name>
	 *		    	<value>custom</value>
	 *			</library-property>
	 *
	 *		Theme of the last resort
	 *		
	 *		If the previous two check points do not yield any result, the theme with the highest priority would be chosen. Theme priorities are usually assigned using the theme registration as well, but could also be changed dynamically.
	 *		
	 *	See http://books.zkoss.org/wiki/ZK%20Developer%27s%20Reference/Theming%20and%20Styling for more info. 
	 *  
	 * ADempiere System Configurations can be used to define special styles in themes.
	 * The following variables show the defaults that will be used if the configuration is not 
	 * defined.  System Configurator key values match the variable names.
	 * 
	 */
	
	//							Variable name					Default Value					Notes
	public final static String ZK_DEFAULT_THEME_NAME 		= "adempiere_default";			// Should match the folder name
	public final static String ZK_DEFAULT_THEME_DISPLAY 	= "Adempiere";					// Used as a title in about windows.
	public final static String ZK_DEFAULT_THEME_ORIGIN 		= "FOLDER";						// FOLDER or JAR. See http://www.zkoss.org/javadoc/7.0.1/zk/org/zkoss/web/theme/StandardTheme.ThemeOrigin.html
	public final static int    ZK_DEFAULT_THEME_PRIORITY 	= 1000;							// Integer priority
	
	// Other theme elements
	// These provide a fall-back default in case no system config or MThemeResources
	// are defined.
	/**
	 * URI for a single additional resource to add to the theme.
	 * Can be overridden in the System configurator "ZK_DEFAULT_THEME_URI".
	 * Multiple resources can be defined using the MThemeResources
	 * and the Theme Mangement window in the Application Dictionary.
	 */
	public final static String ZK_DEFAULT_THEME_URI		 	= "/css/adempiere_default.css.dsp"; 
	
	/**
	 * The default title that will appear in the Browser.
	 * Can be overridden in the System Configurator "ZK_BROWSER_TITLE"
	 * or in the MTheme / Theme Management window in the Application 
	 * Dictionary. 
	 */	
	public final static String ZK_BROWSER_TITLE				= "ADempiere";
	
	//  The following file locations have been moved to the AD_Theme table and MTheme model
	//  to make them variable with the theme. These are the fall-back defaults.
	public static final String ZK_LOGIN_TOP_PANEL_ZUL = "~./zul/login-top.zul";
	public static final String ZK_LOGIN_BOTTOM_PANEL_ZUL = "~./zul/login-bottom.zul";
	public static final String ZK_LOGIN_LEFT_PANEL_ZUL = "~./zul/login-left.zul";
	public static final String ZK_LOGIN_RIGHT_PANEL_ZUL = "~./zul/login-right.zul";

	// Defalut logo file names.  The "~." path element will be replaced
	// by the active theme folder/jar path through MTheme or through the 
	// system configurator.  See ThemeUtils.java
	// System Config ZK_LOGO_LARGE
	public static final String ZK_LOGO_LARGE_IMAGE = "~./images/logo-large.png";
	// System Config ZK_LOGO_SMALL or WEBUI_LOGOURL
	public static final String ZK_LOGO_SMALL_IMAGE = "~./images/logo-small.png";
	// System Config ZK_BROWSER_ICON
	public static final String ZK_BROWSER_ICON_IMAGE= "~./images/icon.png";

	
	//  Don't include in system configurator.  Use ZK_DEFAULT_THEME_ORIGIN instead.
	private final static ThemeOrigin ZK_DEFAULT_THEME_ORIGIN_ENUM = ThemeOrigin.FOLDER;
	
	//  live theme information
	private String 		p_themeName 		= ZK_DEFAULT_THEME_NAME;
	private String 		p_themeDisplay 		= ZK_DEFAULT_THEME_DISPLAY;
	private ThemeOrigin p_themeOriginEnum 	= ThemeOrigin.FOLDER;
	private String 		p_themeOrigin 		= ZK_DEFAULT_THEME_ORIGIN;
	private int    		p_themePriority		= ZK_DEFAULT_THEME_PRIORITY;
	
	private static final CLogger logger = CLogger.getCLogger(DefaultTheme.class);

	
	/**
	 * Load the system default theme info from the model with fall back to the System Configuration or
	 * the settings hardcoded in the DefaultTheme class.
	 */
	public void loadDefaultTheme() {

		// Load the default theme form the model/database
		MTheme defaultTheme = MTheme.getDefault(Env.getCtx());
		if (defaultTheme != null && defaultTheme.get_ID() > 0) {
			set_themeName(defaultTheme.get_themeName());
			set_themeDisplay(defaultTheme.get_displayName());
			set_themePriority(defaultTheme.get_priority());
			set_themeOrigin(defaultTheme.get_origin());
		}
		else { // If no model default theme, try the system configurator and fall back to the defaults
			set_themeName(MSysConfig.getValue("ZK_DEFAULT_THEME_NAME", ZK_DEFAULT_THEME_NAME));
			set_themeDisplay(MSysConfig.getValue("ZK_DEFAULT_THEME_DISPLAY", ZK_DEFAULT_THEME_DISPLAY));
			set_themePriority(MSysConfig.getIntValue("ZK_DEFAULT_THEME_PRIORITY", ZK_DEFAULT_THEME_PRIORITY));
			set_themeOrigin(MSysConfig.getValue("ZK_DEFAULT_THEME_ORIGIN", ZK_DEFAULT_THEME_ORIGIN));
		}
	}
	
	/**
	 * Register the default theme with the system.
	 */
	public void registerDefaultTheme() {
		
		Themes.register(get_themeName(), get_themeDisplay(), get_themePriority(), get_themeOriginEnum());
        // Only ZK EE users could use tablet theme
        if ("EE".equals(WebApps.getEdition())) 
        {
            Themes.register("tablet:" + get_themeName(), get_themeDisplay(), get_themePriority(), get_themeOriginEnum());
        }   

		
	}

	/**
	 * @return the themeName
	 */
	public String get_themeName() {
		return p_themeName;
	}

	/**
	 * @param themeName the Theme Name to set
	 */
	public void set_themeName(String themeName) {
		if (themeName == null 
			|| themeName.length() == 0)
		{
			// Theme name forms part of the path.
			throw new IllegalArgumentException("Theme name cannot be null or empty string.");
		}
	}

	/**
	 * @return the theme display name
	 */
	public String get_themeDisplay() {
		return p_themeDisplay;
	}

	/**
	 * @param themeDisplay the theme display name to set
	 */
	public void set_themeDisplay(String themeDisplay) {
		this.p_themeDisplay = themeDisplay;
	}

	/**
	 * @return the themeOrigin
	 */
	public String get_themeOrigin() {
		return p_themeOrigin;
	}

	/**
	 * @param themeOrigin the theme origin to set. See 
	 * http://www.zkoss.org/javadoc/latest/zk/org/zkoss/web/theme/StandardTheme.ThemeOrigin.html
	 */
	public void set_themeOrigin(String p_themeOrigin) {
		this.p_themeOrigin = p_themeOrigin;
		set_themeOriginEnum(p_themeOrigin);
	}

	/**
	 * @return the theme priority.
	 */
	public int get_themePriority() {
		return p_themePriority;
	}

	/**
	 * @param themePriority the theme priority to set.  Used by the system to 
	 * decide which theme to use after cookies and library properties have been
	 * tried and failed.  A lower number has a higher priority.  The theme with
	 * the lowest number will be used.
	 */
	public void set_themePriority(int p_themePriority) {
		this.p_themePriority = p_themePriority;
	}


	/**
	 * @return the p_themeOriginEnum
	 */
	public ThemeOrigin get_themeOriginEnum() {
		return p_themeOriginEnum;
	}

	/**
	 * @param p_themeOriginEnum the p_themeOriginEnum to set
	 * Do not set directly. Use set_themeOrigin().
	 */
	public void set_themeOriginEnum(String themeOrigin) {
		try
		{
			this.p_themeOriginEnum = ThemeOrigin.valueOf(themeOrigin);  // Has to match exactly
		}
		catch (IllegalArgumentException 		// - if this enum type has no constant with the specified name 
			|  NullPointerException 			// - if the argument is null
				e)
		{
			logger.warning("System Configurator entry ZK_DEFAULT_THEME_ORIGIN was null or did not match ZK ThemeOrigin enumerated values. " +
						   "See http://www.zkoss.org/javadoc/7.0.1/zk/org/zkoss/web/theme/StandardTheme.ThemeOrigin.html");
			this.p_themeOriginEnum = ZK_DEFAULT_THEME_ORIGIN_ENUM;
		}
	}
}
