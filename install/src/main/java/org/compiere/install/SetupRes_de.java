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
package org.compiere.install;

import java.util.ListResourceBundle;

/**
 *	Setup Resources
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: SetupRes.java,v 1.3 2006/07/30 00:57:42 jjanke Exp $
 */
public class SetupRes_de extends ListResourceBundle
{
	/**	Translation Info	*/
	static final Object[][] contents = new String[][]{
	{ "AdempiereServerSetup", 	"ADempiere Server-Einrichtung" },
	{ "Ok", 					"Ok" },
	{ "File", 					"Datei" },
	{ "Exit", 					"Ende" },
	{ "Help", 					"Hilfe" },
	{ "PleaseCheck", 			"Bitte prüfen" },
	{ "UnableToConnect", 		"Keine Verbindung zur ADempiere-Webseite" },
	//
	{ "AdempiereHomeInfo", 		"ADempiere-Hauptverzeichnis" },
	{ "AdempiereHome", 			"ADempiere-Home" },
	{ "WebPortInfo", 			"Web-Port (HTML)" },
	{ "WebPort", 				"Web-Port" },
	{ "AppsServerInfo", 		"Name des Applikations-Servers" },
	{ "AppsServer", 			"Applikations-Server" },
	{ "DatabaseTypeInfo", 		"Bezeichnung Datenbank-Typ" },
	{ "DatabaseType", 			"Datenbank-Typ" },
	{ "DatabaseNameInfo", 		"Name der Datenbank (Service)" },
	{ "DatabaseName", 			"Datenbank-Name" },
	{ "DatabasePortInfo", 		"Listener-Port Datenbank" },
	{ "DatabasePort", 			"Datenbank-Port" },
	{ "DatabaseUserInfo", 		"Name ADempiere-DB-User" },
	{ "DatabaseUser", 			"ADempiere-DB-User" },
	{ "DatabasePasswordInfo", 	"Passwort ADempiere-DB-User" },
	{ "DatabasePassword", 		"DB-User-Passwort" },
	{ "DatabaseRestrictedInfo",	"Einstellung: DB-Zugriff ist eingeschränkt" },
	{ "DatabaseRestricted", 	"Zugriff eingeschränkt" },
	{ "TNSNameInfo", 			"Ermittelte Datenbanken" },
	{ "TNSName", 				"Datenbanken" },
	{ "SystemPasswordInfo", 	"Passwort Datenbank-Administrator" },
	{ "SystemPassword", 		"DB-Admin-Passwort" },
	{ "MailServerInfo", 		"Name E-Mail-Server" },
	{ "MailServer", 			"E-Mail-Server" },
	{ "AdminEMailInfo", 		"ADempiere Administrator-E-Mail" },
	{ "AdminEMail", 			"Admin-E-Mail" },
	{ "DatabaseServerInfo", 	"Name Datenbank-Server" },
	{ "DatabaseServer", 		"Datenbank-Server" },
	{ "JavaHomeInfo", 			"Java-Hauptverzeichnis " },
	{ "JavaHome", 				"Java-Home" },
	{ "JNPPortInfo", 			"JNP-Port Applikations-Server" },
	{ "JNPPort", 				"JNP-Port" },
	{ "MailUserInfo", 			"Name ADempiere-Mail-User" },
	{ "MailUser", 				"Mail-User" },
	{ "MailPasswordInfo", 		"Passwort ADempiere-Mail-User" },
	{ "MailPassword", 			"Mail-Password" },
	{ "KeyStorePassword",		"KeyStore-Passwort" },
	{ "KeyStorePasswordInfo",	"Passwort SSL-Key-Store" },
	//
	{ "JavaType",			"Java-VM"},
	{ "JavaTypeInfo",		"Typ Java-VM"},
	{ "AppsType",			"Server-Typ"},
	{ "AppsTypeInfo",		"J2EE-Applikations-Server-Typ"},
	{ "DeployDir",			"Deployment"},
	{ "DeployDirInfo",		"Verzeichnis J2EE-Deployment"},
	{ "ErrorDeployDir",		"Error-Deployment-Directory"},
	//
	{ "TestInfo", 			"Konfiguration testen" },
	{ "Test", 				"Test" },
	{ "SaveInfo", 			"Konfiguration speichern" },
	{ "Save", 				"Speichern" },
	{ "HelpInfo", 			"Hilfe" },
	//
	{ "ServerError", 		"Fehler bei Server-Konfiguration" },
	{ "ErrorJavaHome", 		"Fehler bei Java-Home" },
	{ "ErrorAdempiereHome", 	"Fehler bei ADempiere-Home" },
	{ "ErrorAppsServer", 		"Fehler bei Applikations-Server (verwenden Sie NICHT localhost)" },
	{ "ErrorWebPort", 		"Fehler bei Web-Port" },
	{ "ErrorJNPPort", 		"Fehler bei JNP-Port" },
	{ "ErrorDatabaseServer", 	"Fehler bei Datenbank-Server (verwenden Sie NICHT localhost)" },
	{ "ErrorDatabasePort", 		"Fehler bei Datenbank-Port" },
	{ "ErrorJDBC", 			"Fehler bei JDBC-Verbindung" },
	{ "ErrorTNS", 			"Fehler bei TNS-Verbindung" },
	{ "ErrorMailServer", 		"Fehler bei Mail-Server (verwenden Sie NICHT localhost)" },
	{ "ErrorMail", 			"Fehler bei Mail" },
	{ "ErrorSave", 			"Fehler bei Datei speichern" },

	{ "EnvironmentSaved", 		"Environment-Datei gespeichert .... starte Deployment\n"
		+ "Sie können den Applikations-Server nach Beendigung des Programmes neu starten.\n"
		+ "Achten Sie auf Fehlermeldungen in der Ausgabe.\n" }

	};

	/**
	 * 	Get Content
	 * 	@return content array
	 */
	public Object[][] getContents()
	{
		return contents;
	}

}