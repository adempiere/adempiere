package org.adempiere.webui.theme;

import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.WebApps;
import org.zkoss.zul.theme.Themes;

import org.zkoss.zk.ui.util.WebAppInit;
import org.adempiere.webui.theme.DefaultTheme;


public class DefaultThemeWebAppInit implements WebAppInit {
    
	@Override
    public void init(WebApp webapp) throws Exception {
		
		DefaultTheme dt = new DefaultTheme();
		dt.registerDefaultTheme();
    }
}

