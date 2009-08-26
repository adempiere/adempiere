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
package org.compiere.apps;

import java.util.ListResourceBundle;

/**
 *  German Resource Bundle
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: ALoginRes_de.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 */
public final class ALoginRes_de extends ListResourceBundle
{
	// TODO Run native2ascii to convert to plain ASCII !! 
	
	/** Translation Content     */
	static final Object[][] contents = new String[][]
	{
	{ "Connection",     "Verbindung" },
	{ "Defaults",       "Standard Werte" },
	{ "Login",          "Adempiere Anmeldung" },
	{ "File",           "Datei" },
	{ "Exit",           "Beenden" },
	{ "Help",           "Hilfe" },
	{ "About",          "\u00dcber" },
	{ "Host",           "Rechner" },
	{ "Database",       "Datenbank" },
	{ "User",           "Benutzer" },
	{ "EnterUser",      "Answendungs-Benutzer eingeben" },
	{ "Password",       "Kennwort" },
	{ "EnterPassword",  "Anwendungs-Kennwort eingeben" },
	{ "Language",       "Sprache" },
	{ "SelectLanguage", "Anwendungs-Sprache ausw\u00e4hlen" },
	{ "Role",           "Rolle" },
	{ "Client",         "Mandant" },
	{ "Organization",   "Organisation" },
	{ "Date",           "Datum" },
	{ "Warehouse",      "Lager" },
	{ "Printer",        "Drucker" },
	{ "Connected",      "Verbunden" },
	{ "NotConnected",   "Nicht verbunden" },
	{ "DatabaseNotFound", "Datenbank nicht gefunden" },
	{ "UserPwdError",   "Benutzer und Kennwort stimmen nicht \u00fcberein" },
	{ "RoleNotFound",   "Rolle nicht gefunden/komplett" },
	{ "Authorized",     "Authorisiert" },
	{ "Ok",             "Ok" },
	{ "Cancel",         "Abbruch" },
	{ "VersionConflict", "Versions Konflikt:" },
	{ "VersionInfo",    "Server <> Arbeitsstation" },
	{ "PleaseUpgrade",  "Bitte das Aktualisierung-Programm (update) starten" }
	};

	/**
	 *  Get Contents
	 *  @return context
	 */
	public Object[][] getContents()
	{
		return contents;
	}   //  getContents
}   //  LoginRes_de
