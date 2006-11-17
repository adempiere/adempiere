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
package org.compiere.apps;

import java.util.*;

/**
 *  Base Resource Bundle
 *
 * 	@author 	Marko Bubalo
 * 	@version 	$Id: ALoginRes_hr.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 */
public final class ALoginRes_hr extends ListResourceBundle
{
	// TODO Run native2ascii to convert to plain ASCII !! 
	
	/** Translation Content     */
	static final Object[][] contents = new String[][]
	{
	{ "Connection",         "Veza" },
	{ "Defaults",           "Uobi�ajeno" },
	{ "Login",              "Adempiere Login" },	
	{ "File",               "Datoteka" },
	{ "Exit",               "Izlaz" },
	{ "Help",               "Pomo�" },
	{ "About",              "O programu" },
	{ "Host",               "Host" },
	{ "Database",           "Baza podataka" },
	{ "User",               "Korisnik" },
	{ "EnterUser",          "Unos korisnika" },
	{ "Password",           "Lozinka" },
	{ "EnterPassword",      "Unos lozinke" },
	{ "Language",           "Jezika" },
	{ "SelectLanguage",     "Izbor jezika" },
	{ "Role",               "Uloga" },
	{ "Client",             "Klijent" },
	{ "Organization",       "Organizacija" },
	{ "Date",               "Datum" },
	{ "Warehouse",          "Skladi�te" },
	{ "Printer",            "Pisac" },
	{ "Connected",          "Spojeno" },
	{ "NotConnected",       "Nije spojeno" },
	{ "DatabaseNotFound",   "Baza podataka nije pronadena" },
	{ "UserPwdError",       "Lozinka ne odgovara korisniku" },
	{ "RoleNotFound",       "Uloga nije pronadena" },
	{ "Authorized",         "Autoriziran" },
	{ "Ok",                 "U redu" },
	{ "Cancel",             "Otkazati" },
	{ "VersionConflict",    "Konflikt verzija" },
	{ "VersionInfo",        "Server <> Klijent" },
	{ "PleaseUpgrade",      "Molim pokrenite nadogradnju programa" }
	};

	/**
	 *  Get Contents
	 *  @return context
	 */
	public Object[][] getContents()
	{
		return contents;
	}   //  getContents
}   //  ALoginRes

