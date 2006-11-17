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
 *	Setup Resources for Finnish language
 *
 * 	@author 	Petteri Soininen (petteri.soininen@netorek.fi)
 * 	@version 	$Id: SetupRes_fi.java,v 1.2 2006/07/30 00:57:42 jjanke Exp $
 */
public class SetupRes_fi extends ListResourceBundle
{
	/**	
    * Translation Info
    */
	static final Object[][] contents = new String[][]{
	{ "AdempiereServerSetup", 	"Adempiere-palvelimen Asetukset" },
	{ "Ok", 					"Hyv�ksy" },
	{ "File", 					"Tiedosto" },
	{ "Exit", 					"Poistu" },
	{ "Help", 					"Help" },
	{ "PleaseCheck", 			"Ole hyv� ja valitse" },
	{ "UnableToConnect", 		"Yhteydenotto Adempieren Web-Help:in ei onnistu" },
	//
	{ "AdempiereHomeInfo", 		"Adempiere Home on p��kansio" },
	{ "AdempiereHome", 			"Adempiere Home" },
	{ "WebPortInfo", 			"Web (HTML) Portti" },
	{ "WebPort", 				"Web Portti" },
	{ "AppsServerInfo", 		"Sovelluspalvelimen Nimi" },
	{ "AppsServer", 			"Sovelluspalvelin" },
	{ "DatabaseTypeInfo", 		"Tietokantatyyppi" },
	{ "DatabaseType", 			"Tietokantatyyppi" },
	{ "DatabaseNameInfo", 		"Tietokannan Nimi" },
	{ "DatabaseName", 			"Tietokannan Nimi (SID)" },
	{ "DatabasePortInfo", 		"Tietokannan kuuntelijaportti" },
	{ "DatabasePort", 			"Tietokantaportti" },
	{ "DatabaseUserInfo", 		"Tietokannan Adempiere-k�ytt�j�tunnus" },
	{ "DatabaseUser", 			"Tietokannan k�ytt�j�tunnus" },
	{ "DatabasePasswordInfo", 	"Tietokannan Adempiere-salasana" },
	{ "DatabasePassword", 		"Tietokannan salasana" },
	{ "TNSNameInfo", 			"TNS tai Globaali Tietokannan Nimi" },
	{ "TNSName", 				"TNS Nimi" },
	{ "SystemPasswordInfo", 	"J�rjestelm�salasana" },
	{ "SystemPassword", 		"J�rjestelm�salasana" },
	{ "MailServerInfo", 		"S�hk�postipalvelin" },
	{ "MailServer", 			"S�hk�postipalvelin" },
	{ "AdminEMailInfo", 		"Adempiere-yll�pit�j�n S�hk�posti" },
	{ "AdminEMail", 			"Yll�pit�j�n S�hk�posti" },
	{ "DatabaseServerInfo", 	"Tietokantapalvelimen Nimi" },
	{ "DatabaseServer", 		"Tietokantapalvelin" },
	{ "JavaHomeInfo", 			"Java-kotihakemisto" },
	{ "JavaHome", 				"Java-koti" },
	{ "JNPPortInfo", 			"Sovelluspalvelimen JNP-portti" },
	{ "JNPPort", 				"JNP-portti" },
	{ "MailUserInfo", 			"Adempiere-s�hk�postik�ytt�j�" },
	{ "MailUser", 				"S�hk�postik�ytt�j�" },
	{ "MailPasswordInfo", 		"Adempiere-s�hk�postisalasana" },
	{ "MailPassword", 			"S�hk�postisalasana" },
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
	{ "TestInfo", 				"Testaa Asetukset" },
	{ "Test", 					"Testaa" },
	{ "SaveInfo", 				"Tallenna Asetukset" },
	{ "Save", 					"Tallenna" },
	{ "HelpInfo", 				"Hae Apua" },
	//
	{ "ServerError", 			"Palvelimen Asetusvirhe" },
	{ "ErrorJavaHome", 			"Java-kotivirhe" },
	{ "ErrorAdempiereHome", 		"Adempiere-kotivirhe" },
	{ "ErrorAppsServer", 		"Sovelluspalvelinvirhe (�l� k�yt� paikallisverkkoasemaa)" },
	{ "ErrorWebPort", 			"Web-porttivirhe" },
	{ "ErrorJNPPort", 			"JNP-porttivirhe" },
	{ "ErrorDatabaseServer", 	"Tietokantapalvelinvirhe (�l� k�yt� paikallisverkkoasemaa)" },
	{ "ErrorDatabasePort", 		"Tietokantaporttivirhe" },
	{ "ErrorJDBC", 				"JDBC-yhteysvirhe" },
	{ "ErrorTNS", 				"TNS-yhteysvirhe" },
	{ "ErrorMailServer", 		"S�hk�postipalvelinvirhe (�l� k�yt� paikallisverkkoasemaa)" },
	{ "ErrorMail", 				"S�hk�postivirhe" },
	{ "ErrorSave", 				"Tiedostontallennusvirhe" },

	{ "EnvironmentSaved", 		"Ymp�rist� tallennettu/Palvelin t�ytyy k�ynnist�� uudelleen." },

	{ "RMIoverHTTP", 			"Tunneloi objektit HTTP kautta" },
	{ "RMIoverHTTPInfo", 		"RMI HTTP:n yli mahdollistaa palomuurien l�p�isyn" }
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
