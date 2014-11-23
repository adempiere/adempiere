/**
 * Copyright © Potix Corporation.
 * 
 * Based on http://books.zkoss.org/wiki/ZK_Developer%27s_Reference/Theming_and_Styling/Theme_Providers
 * 
 * Implements a cachable theme provider.  This is the mechanism to add css files to the theme and to
 * customize the behaviour of the theme.  
 * 
 * ADempiere implementation
 * @author Michael McKay
 *
 */
package org.adempiere.webui.theme;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

import org.compiere.model.MTheme;
import org.compiere.util.Env;
import org.zkoss.lang.Strings;
import org.zkoss.web.fn.ServletFns;
import org.zkoss.web.theme.StandardTheme;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.ThemeProvider;
import org.zkoss.zul.theme.Themes;

public class AdempiereThemeProvider implements ThemeProvider {
		
	/**
	 * Default theme css file
	 */
	public final static String DEFAULT_WCS = "~./zul/css/zk.wcs";
	
	protected static String getThemeFileSuffix() {
		String suffix = Themes.getCurrentTheme();
		return StandardTheme.DEFAULT_NAME.equals(suffix) ? null : suffix;
	}
	
	private void bypassURI(List<Object> uris, String suffix) {
		for (ListIterator<Object> it = uris.listIterator(); it.hasNext();) {
			Object o = it.next();
			if (o instanceof String) {
				final String uri = (String)o;
				if (uri.startsWith(DEFAULT_WCS)) {
					it.set(Aide.injectURI(uri, suffix));
					break;
				}
			}
		}
	}
	
	public Collection<Object> getThemeURIs(Execution exec, List<Object> uris) {
		String suffix = getThemeFileSuffix();
		
		if (!Strings.isEmpty(suffix)) {
			bypassURI(uris, suffix);
			
			MTheme theme = MTheme.get(Env.getCtx(), suffix);
			if (theme != null && theme.get_ID() > 0) {
				String themeURIs[] = theme.getResourcesURIs();
				for (String themeURI : themeURIs) {
		            uris.add(themeURI);
		        }
			}
			else {
				uris.add(DefaultTheme.ZK_DEFAULT_THEME_URI);
			}			
		}
        return uris;
    }
	
	
	public String beforeWCS(Execution exec, String uri) {
		return uri;
	}
	
	
	public String beforeWidgetCSS(Execution exec, String uri) {
		if (uri.startsWith("~./zul/css/") ||
			uri.startsWith("~./js/zul/")) {
			
			uri = ServletFns.resolveThemeURL(uri);
		}
		
		return uri;
	}
	 
    /**
     * Returns the number of hours that the specified WCS (Widget CSS descriptor) file 
     * won't be changed. In other words, the client is allowed to cache the file until 
     * the returned hours expires.
     * 
     * @param exec - the client execution 
     * @param uri - the URI of the WCS file, e.g., ~./zul/css/zk.wcs 
     * 
     * @return number of hours that the WCS file is allowed to cache. If it is never 
     * changed until next ZK upgrade, you could return 8760 (the default if ThemeProvider 
     * is not specified). If you don't want the client to cache, return a non-positive 
     * number.
     */
    public int getWCSCacheControl(Execution exec, String uri) {
    	
		String suffix = getThemeFileSuffix();
		
		if (!Strings.isEmpty(suffix)) {
			MTheme theme = MTheme.get(Env.getCtx(), suffix);
			if (theme != null && theme.get_ID() > 0) {
				return theme.getCacheHours();
			}
		}
		return 8760;
    }
}
