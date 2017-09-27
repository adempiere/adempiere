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

import java.io.File;
import java.io.FileOutputStream;
import java.util.logging.Level;

import org.adempiere.webui.AdempiereWebUI;
import org.compiere.model.MClient;
import org.compiere.model.MClientInfo;
import org.compiere.model.MImage;
import org.compiere.model.MSysConfig;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.zkoss.zk.ui.Executions;

/**
 *
 * @author hengsin
 *
 */
public final class ThemeManager {

    //--> Ashley
    /** Logo Cache           */
    private static CCache<String, String> logoCache = new CCache<String, String>("ZKLogo", 40, 0);
    /** Static Logger   */
    private static CLogger  logger   = CLogger.getCLogger(ThemeManager.class);
    
    private static String getLogo(int clientId, String type, String logoFile, String defaultValue)
    {
        MClientInfo clientInfo = MClientInfo.get(Env.getCtx(), clientId);
        int imageId = 0;
        String retLogoPath = defaultValue;
        
        if ("ZK_LOGO_LARGE".equals(type))
        {
            imageId = clientInfo.getLogoWeb_ID();
        }
        else if ("ZK_LOGO_SMALL".equals(type))
        {
            imageId = clientInfo.get_ValueAsInt("LogoWebHeader_ID");
        }
        else
        {
            return retLogoPath;
        }
        
        if (imageId <= 0)
        {
            if (clientId > 0)
            {
                // Return the System logo if configured
                return getLogo(0, type, logoFile, defaultValue);
            }
            else
            {
                return retLogoPath;
            }
        }
        
        try
        {
            MImage image = MImage.get(Env.getCtx(), imageId);
            String logoFilePath = Executions.getCurrent().getDesktop().getWebApp().getRealPath("") + File.separator + logoFile;
            FileOutputStream outStream = new FileOutputStream(logoFilePath);
            outStream.write(image.getBinaryData());
            outStream.close();
            retLogoPath = logoFile;
        }
        catch (Exception ex)
        {
            logger.log(Level.SEVERE, "Could not write logo file, using default", ex);
        }
        
        return retLogoPath;
    }
    
    private static String getLogo(String type, String defaultValue)
    {
        String key = MClient.get(Env.getCtx()).getValue();
        
        String logoFile = key + type + ".png";
        
        if (logoCache.get(logoFile) == null)
        {
            String loadedFile = getLogo(Env.getAD_Client_ID(Env.getCtx()), type, logoFile, defaultValue);
            logoCache.put(logoFile, loadedFile);
        }
        
        return logoCache.get(logoFile);
    }
    //<--

	/**
	 * @return url for large logo
	 */
	public static String getLargeLogo() {
		String theme = getTheme();
		String def = ITheme.THEME_PATH_PREFIX+theme+ITheme.LOGIN_LOGO_IMAGE;
        //--> Ashley
        //return MSysConfig.getValue("ZK_LOGO_LARGE", def);
        return getLogo("ZK_LOGO_LARGE", def);
        //<--
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
		//--> Ashley
		//return url;
		//def = MSysConfig.getValue("WEBUI_LOGOURL", def);
		return getLogo("ZK_LOGO_SMALL", url);
		//<--
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
	    //--> Ashley
	    String title = MSysConfig.getValue("ZK_BROWSER_TITLE", null, Env.getAD_Client_ID(Env.getCtx()));
	    
	    if (title != null)
	    {
	        return title;
	    }
	    //<--
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
	public static String getThemeResource(String name) {
		StringBuilder builder = new StringBuilder(ITheme.THEME_PATH_PREFIX);
		builder.append(getTheme());
		builder.append("/").append(name);
		String url = builder.toString().intern();
		return  url;
	}
}
