/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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
package org.compiere.db;

import java.util.*;

/**
 *  Swedish Connection Resource Strings
 *
 *  @author     Thomas Dilts
 *  @version    $Id: DBRes_sv.java,v 1.2 2006/07/30 00:55:13 jjanke Exp $
 */
public class DBRes_sv extends ListResourceBundle
{
	/** Data        */
	static final Object[][] contents = new String[][]
	{
	{ "CConnectionDialog",  "Adempiere anslutning" },
	{ "Name",               "Namn" },
	{ "AppsHost",           "Program v�rddator" },
	{ "AppsPort",           "Program port" },
	{ "TestApps",           "Test program" },
	{ "DBHost",             "Databas v�rddator" },
	{ "DBPort",             "Databas port" },
	{ "DBName",             "Databas namn" },
	{ "DBUidPwd",           "Anv�ndarnamn / l�senord" },
	{ "ViaFirewall",        "via Firewall" },
	{ "FWHost",             "Firewall v�rddator" },
	{ "FWPort",             "Firewall port" },
	{ "TestConnection",     "Test databas" },
	{ "Type",               "Databas typ" },
	{ "BequeathConnection", "Efterl�mna anslutning" },
	{ "Overwrite",          "Skriv �ver" },
	{ "ConnectionProfile",	"Connection" },
	{ "LAN",		 		"LAN" },
	{ "TerminalServer",		"Terminal Server" },
	{ "VPN",		 		"VPN" },
	{ "WAN", 				"WAN" },
	{ "ConnectionError",    "Anslutningsfel" },
	{ "ServerNotActive",    "Server ej activ" }
	};

	/**
	 * Get Contsnts
	 * @return contents
	 */
	public Object[][] getContents()
	{
		return contents;
	}   //  getContent
}   //  Res
