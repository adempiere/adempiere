/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2007 Adempiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *
 * Copyright (C) 2007 Low Heng Sin hengsin@avantz.com
 * _____________________________________________
 *****************************************************************************/
package org.compiere.util;

import java.util.Properties;

/**
 * 
 * @author Low Heng Sin
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 * <li>BF [ 3294610] The location should allow open a google map
 *        https://sourceforge.net/tracker/?func=detail&atid=879335&aid=3294610&group_id=176962
 *
 */
public class DefaultContextProvider implements ContextProvider {

	/**	Logging								*/
	private static CLogger	  s_log = CLogger.getCLogger(DefaultContextProvider.class);
	
	private static Properties s_ctx = new Properties();
	
	private static String	UNIX_BROWSER = "netscape";
	
	private static String   MAC_BROWSER = "open";
	
	public static String GOOGLE_MAPS_URL_PREFIX =
		"http://local.google.com/maps?q=";
	
	
	public Properties getContext() {
		return s_ctx;
	}

public void showURL(String url, String title) {
		
		try {
			java.net.URI uri = new java.net.URI(url);
			java.awt.Desktop.getDesktop().browse(uri);
		}
		catch (Exception e) {
			s_log.warning(e.getLocalizedMessage());
		}
	}

}
