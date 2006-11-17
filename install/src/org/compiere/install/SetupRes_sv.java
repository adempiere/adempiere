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
 *	Swedish Setup Resource Translation
 *
 * 	@author 	Thomas Dilts
 * 	@version 	$Id: SetupRes_sv.java,v 1.2 2006/07/30 00:57:42 jjanke Exp $
 */
public class SetupRes_sv extends ListResourceBundle
{
	/**	Translation Info	*/
	static final Object[][] contents = new String[][]{
	{ "AdempiereServerSetup", "Adempiere server installationsprogram" },
	{ "Ok", 				"Ok" },
	{ "File", 				"Fil" },
	{ "Exit", 				"Avsluta" },
	{ "Help", 				"Hj�lp" },
	{ "PleaseCheck", 		"Kolla" },
	{ "UnableToConnect",	"Kan inte f� hj�lp fr�n Adempiere Web Site" },

	{ "AdempiereHomeInfo", 	"Adempiere hem �r huvudkatalog" },
	{ "AdempiereHome", 		"Adempiere hem" },
	{ "WebPortInfo", 		"Web (HTML) port" },
	{ "WebPort", 			"Web port" },
	{ "AppsServerInfo", 	"Program server name" },
	{ "AppsServer", 		"Program server" },
	{ "DatabaseTypeInfo", 	"Databastyp" },
	{ "DatabaseType", 		"Databastyp" },
	{ "DatabaseNameInfo", 	"Databas namn " },
	{ "DatabaseName", 		"Databas namn (SID)" },
	{ "DatabasePortInfo", 	"Databas avlyssningsport" },
	{ "DatabasePort", 		"Databas port" },
	{ "DatabaseUserInfo", 	"Databas Adempiere anv�ndarnamn" },
	{ "DatabaseUser", 		"Databas anv�ndarnamn" },
	{ "DatabasePasswordInfo", "Databas Adempiere anv�ndare l�senord" },
	{ "DatabasePassword", 	"Databas l�senord" },
	{ "TNSNameInfo", 		"TNS eller global databas namn" },
	{ "TNSName", 			"TNS namn" },
	{ "SystemPasswordInfo", "System anv�ndare l�senord" },
	{ "SystemPassword", 	"System l�senord" },
	{ "MailServerInfo", 	"Post server" },
	{ "MailServer", 		"Post server" },
	{ "AdminEMailInfo", 	"Adempiere administrat�r e-post" },
	{ "AdminEMail", 		"Admin e-post" },
	{ "DatabaseServerInfo", "Databas server namn" },
	{ "DatabaseServer", 	"Databas server" },
	{ "JavaHomeInfo", 		"Java hemkatalog" },
	{ "JavaHome", 			"Java hem" },
	{ "JNPPortInfo", 		"Program server JNP port" },
	{ "JNPPort", 			"JNP port" },
	{ "MailUserInfo", 		"Adempiere post anv�ndare" },
	{ "MailUser", 			"Post anv�ndare" },
	{ "MailPasswordInfo", 	"Adempiere post anv�ndare l�senord" },
	{ "MailPassword", 		"Post l�senord" },
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
	{ "TestInfo", 			"Testa inst�llningar" },
	{ "Test", 				"Testa" },
	{ "SaveInfo", 			"Spara inst�llningar" },
	{ "Save", 				"Spara" },
	{ "HelpInfo", 			"Hj�lp" },

	{ "ServerError", 		"Server inst�llningsfel" },
	{ "ErrorJavaHome", 		"Fel Java hem" },
	{ "ErrorAdempiereHome", 	"Fel Adempiere hem" },
	{ "ErrorAppsServer", 	"Fel program server (anv�nd ej localhost)" },
	{ "ErrorWebPort", 		"Fel web port" },
	{ "ErrorJNPPort", 		"Fel JNP port" },
	{ "ErrorDatabaseServer", "Fel databas server (anv�nd ej localhost)" },
	{ "ErrorDatabasePort", 	"Fel databas port" },
	{ "ErrorJDBC", 			"Fel JDBC anslutning" },
	{ "ErrorTNS", 			"Fel TNS anslutning" },
	{ "ErrorMailServer", 	"Fel post server (anv�nd ej localhost)" },
	{ "ErrorMail", 			"Fel post" },
	{ "ErrorSave", 			"Fel swing fil" },

	{ "EnvironmentSaved",	"Inst�llningar sparad\nDu m�ste starta om servern." },
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
