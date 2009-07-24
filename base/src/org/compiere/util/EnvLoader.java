/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.util;

import java.io.InputStream;
import java.util.Properties;
import java.util.StringTokenizer;

/**
 *  Environment Loader - loads system environment variables int System.properties
 *
 *  @author Jorg Janke
 *  @version  $Id: EnvLoader.java,v 1.3 2006/07/30 00:51:05 jjanke Exp $
 */
public class EnvLoader
{
	private static final boolean DEBUG = false;

	/**
	 *  Load System environment variables into System.properies
	 *  <p>
	 *  Prints error messages on System.err
	 *  @param prefix String to prefix variable names
	 *  @return true if success
	 */
	public static boolean load (String prefix)
	{
		Properties prop = getEnv(prefix);
		if (prop == null)
			return false;
		//  load
		Object[] pp = prop.keySet().toArray();
		for (int i = 0; i < pp.length; i++)
		{
			String key = pp[i].toString();
			String value = prop.getProperty(key);
			System.setProperty(key, value);
		}
		CLogMgt.printProperties(System.getProperties(), "System with Environment", false);
		return true;
	}   //  load

	/**
	 *  Ger Environment variables
	 *  @param prefix String to prefix variable names
	 *  @return Properties with prefixed system environment variables or null if not successful
	 */
	public static Properties getEnv (String prefix)
	{
		String cmd = "cmd /c set";     //   Windows
		if (!System.getProperty("os.name", "").startsWith("Win"))
			cmd = "set";                //  Unix/Linux
		String result = execCommand (cmd);
		if (result == null || result.length() == 0)
			return null;
		//
		if (prefix == null)
			prefix = "";
		return parseEnv (result, prefix);
	}   //  getEnv

	/**
	 *  Execute command and return output
	 *  @param command command
	 *  @return command output
	 */
	private static String execCommand (String command)
	{
		Process cmd;
		try
		{
			cmd = Runtime.getRuntime().exec(command);
		}
		catch (Exception e)
		{
			System.err.println("-- Error executing command: " + command + " - " + e.toString());
			return null;
		}
		if (DEBUG)
			System.out.println("** Command executed: " + command);

		StringBuffer bufOut = new StringBuffer();
		StringBuffer bufErr = new StringBuffer();
		try
		{
			InputStream in = cmd.getInputStream();
			InputStream err = cmd.getErrorStream();
			//
			int c;
			while ((c = in.read()) != -1)
				bufOut.append((char)c);
			in.close();
			//
			while ((c = err.read()) != -1)
				bufErr.append((char)c);
			err.close();
		}
		catch (Exception e)
		{
			System.err.println("-- Error reading output: " + e.toString());
			return null;
		}
		if (DEBUG)
		{
			System.out.println("** Command result: " + bufOut.toString());
			System.out.println("** Command error: " + bufErr.toString());
		}
		return bufOut.toString();
	}   //  execCommand

	/**
	 *  Parse Env and return it in properties
	 *  @param input environment info
	 *  @param prefix prefix for env
	 *  @return properties
	 */
	private static Properties parseEnv (String input, String prefix)
	{
		Properties prop = new Properties ();
		//
		String separator = System.getProperty("line.separator", "\n");
		StringTokenizer st = new StringTokenizer(input, separator);
		while (st.hasMoreTokens())
		{
			String s = st.nextToken();
		//	System.out.println(">" + s + "<");
			int pos = s.indexOf('=');       //  first "="
			if (pos > 0)
				prop.setProperty(prefix + s.substring(0, pos), s.substring(pos+1));
		}
		if (DEBUG)
			System.out.println("** Loaded " + prop.size() + " Properties");
		return prop;
	}   //  parseEnv

}   //  EnvLoader
