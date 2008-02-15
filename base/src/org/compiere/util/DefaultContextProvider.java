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
 *
 */
public class DefaultContextProvider implements ContextProvider {

	/**	Logging								*/
	private static CLogger	  s_log = CLogger.getCLogger(DefaultContextProvider.class);
	
	private static Properties s_ctx = new Properties();
	
	private static String	UNIX_BROWSER = "netscape";
	
	private static String   MAC_BROWSER = "open";
	
	
	public Properties getContext() {
		return s_ctx;
	}

	public void showURL(String url) {
		/* JAVA5 */
		if (!Ini.isClient()) return;
	//  OS command
		String cmd = "rundll32 url.dll,FileProtocolHandler ";
		if (!Env.isWindows()){
			if(Env.isMac())
				cmd = MAC_BROWSER + " ";
			else
				cmd = UNIX_BROWSER + " ";
		}
		//
		String execute = cmd + url;
		try
		{
			Runtime.getRuntime().exec(execute);
			return;
		}
		catch (Exception e)
		{
			if (Env.isWindows())
				s_log.severe(execute + " - " + e);
		}
		
		//try firefox
		if (!Env.isWindows() && !("firefox".equals(UNIX_BROWSER)))
		{
			UNIX_BROWSER = "firefox";
			cmd = UNIX_BROWSER + " ";
			execute = cmd + url;
			try
			{
				Runtime.getRuntime().exec(execute);
			}
			catch (Exception e)
			{
				s_log.severe(execute + " - " + e);
			}
		}
		/* JAVA6 *
		try {
			java.net.URI uri = new java.net.URI(url);
			java.awt.Desktop.getDesktop().browse(uri);
		}
		catch (Exception e) {
			s_log.warning(e.getLocalizedMessage());
		}
		/**/
	}

}
