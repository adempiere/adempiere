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
package org.compiere.install;

import java.util.*;

/**
 *	Setup Resources
 *
 * 	@translator 	Jaume Teixi
 * 	@version 	$Id: SetupRes_ca.java,v 1.2 2006/07/30 00:57:42 jjanke Exp $
 */
public class SetupRes_ca extends ListResourceBundle
{
	/**	Translation Info	*/
	static final Object[][] contents = new String[][]{
	{ "AdempiereServerSetup", "Configuraci� Servidor Adempiere" },
	{ "Ok", 				"D'Acord" },
	{ "File", 				"Fitxer" },
	{ "Exit", 				"Sortir" },
	{ "Help", 				"Ajuda" },
	{ "PleaseCheck", 		"Sisplau Comproveu" },
	{ "UnableToConnect",	"No s'ha pogut obtenir l'ajuda de la web del Adempiere" },

	{ "AdempiereHomeInfo", 	"Adempiere Home �s la Carpeta Principal" },
	{ "AdempiereHome", 		"Adempiere Home" },
	{ "WebPortInfo", 		"Web (HTML) Port" },
	{ "WebPort", 			"Web Port" },
	{ "AppsServerInfo", 	"Nom Servidor Aplicaci�" },
	{ "AppsServer", 		"Servidor Aplicaci�" },
	{ "DatabaseTypeInfo", 	"Tipus Base de Dades" },
	{ "DatabaseType", 		"Tipus Base de Dades" },
	{ "DatabaseNameInfo", 	"Nom Base de Dades" },
	{ "DatabaseName", 		"Nom Base de Dades (SID)" },
	{ "DatabasePortInfo", 	"Port Listener Base de Dades" },
	{ "DatabasePort", 		"Port Base de Dades" },
	{ "DatabaseUserInfo", 	"ID Usuari Adempiere Base de Dades" },
	{ "DatabaseUser", 		"Usuari Base de Dades" },
	{ "DatabasePasswordInfo", "Contrasenya Usuari Adempiere Base de Dades" },
	{ "DatabasePassword", 	"Contrasenya Base de Dades" },
	{ "TNSNameInfo", 		"TNS o Nom Global Base de Dades" },
	{ "TNSName", 			"Nom TNS" },
	{ "SystemPasswordInfo", "Contrasenya Usuari System" },
	{ "SystemPassword", 	"Contrasenya System" },
	{ "MailServerInfo", 	"Servidor Correu" },
	{ "MailServer", 		"Servidor Correu" },
	{ "AdminEMailInfo", 	"Email Administrador Adempiere" },
	{ "AdminEMail", 		"Email Admin" },
	{ "DatabaseServerInfo", "Nom Servidor Base de Dades" },
	{ "DatabaseServer", 	"Servidor Base de Dades" },
	{ "JavaHomeInfo", 		"Carpeta Java Home" },
	{ "JavaHome", 			"Java Home" },
	{ "JNPPortInfo", 		"Port JNP Servidor Aplicaci�" },
	{ "JNPPort", 			"Port JNP" },
	{ "MailUserInfo", 		"Usuari Correu Adempiere" },
	{ "MailUser", 			"Usuari Correu" },
	{ "MailPasswordInfo", 	"Contrasenya Usuari Correu Adempiere" },
	{ "MailPassword", 		"Contrasenya Correu" },
	{ "KeyStorePassword",		"Key Store Password" },
	{ "KeyStorePasswordInfo",	"Password for SSL Key Store" },
	//
	{ "JavaType",				"Java VM"},
	{ "JavaTypeInfo",			"Java VM Vendor"},
	{ "AppsType",				"Server Type"},
	{ "AppsTypeInfo",			"J2EE Application Server Type"},
	{ "DeployDir",				"Deployment"},
	{ "DeployDirInfo",			"J2EE Deployment Directory"},
	{ "ErrorDeployDir",			"Error Deployment Directory"},
	//
	{ "TestInfo", 			"Provar Configuraci�" },
	{ "Test", 				"Provar" },
	{ "SaveInfo", 			"Guardar Configuraci�" },
	{ "Save", 				"Guardar" },
	{ "HelpInfo", 			"Obtenir Ajuda" },

	{ "ServerError", 		"Error Configuraci� Servidor" },
	{ "ErrorJavaHome", 		"Error Java Home" },
	{ "ErrorAdempiereHome", 	"Error Adempiere Home" },
	{ "ErrorAppsServer", 	"Error Servidor Aplicaci� (no emprar localhost)" },
	{ "ErrorWebPort", 		"Error Port Web" },
	{ "ErrorJNPPort", 		"Error Port JNP" },
	{ "ErrorDatabaseServer", "Error Servidor Base de Dades (no emprar localhost)" },
	{ "ErrorDatabasePort", 	"Error Port Base de Dades" },
	{ "ErrorJDBC", 			"Error Connexi� JDBC" },
	{ "ErrorTNS", 			"Error Connexi� TNS" },
	{ "ErrorMailServer", 	"Error Servidor Correu (no emprar localhost)" },
	{ "ErrorMail", 			"Error Correu" },
	{ "ErrorSave", 			"Error Guardant Fitxer" },

	{ "EnvironmentSaved",	"Entorn Guardat\nCal reiniciar el servidor." },
	{ "RMIoverHTTP", 		"Tunnel Objects via HTTP" },
	{ "RMIoverHTTPInfo", 	"RMI over HTTP allows to go through firewalls" }
	};

	/**
	 * 	Get Contents
	 * 	@return contents
	 */
	public Object[][] getContents()
	{
		return contents;
	}	//	getContents

}	//	SerupRes
