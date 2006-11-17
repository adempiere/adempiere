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
 * Resource Bundle for Finnish language
 * 
 * @author Petteri Soininen (petteri.soininen@netorek.fi)
 * @version $Id: ALoginRes_fi.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 */
public final class ALoginRes_fi extends ListResourceBundle
{
	// TODO Run native2ascii to convert to plain ASCII !! 
	
	/** Translation Content */
	static final Object[][] contents = new String[][] {
		{ "Connection", "Yhteys"},
		{ "Defaults", "Oletusarvot"},
		{ "Login", "Adempiere Login"},
		{ "File", "Tiedosto"},
		{ "Exit", "Poistu"},
		{ "Help", "Ohje"},
		{ "About", "About"},
		{ "Host", "Host"},
		{ "Database", "Tietokanta"},
		{ "User", "K�ytt�j�tunnus"},
		{ "EnterUser", "Anna sovelluksen k�ytt�j�tunnus"},
		{ "Password", "Salasana"},
		{ "EnterPassword", "Anna sovelluksen salasana"},
		{ "Language", "Kieli"},
		{ "SelectLanguage", "Valitse kieli"},
		{ "Role", "Rooli"},
		{ "Client", "Client"},
		{ "Organization", "Organisaatio"},
		{ "Date", "P�iv�m��r�"},
		{ "Warehouse", "Tietovarasto"},
		{ "Printer", "Tulostin"},
		{ "Connected", "Yhdistetty"},
		{ "NotConnected", "Ei yhteytt�"},
		{ "DatabaseNotFound", "Tietokantaa ei l�ydy"},
		{ "UserPwdError", "K�ytt�j�tunnus ja salasana eiv�t vastaa toisiaan"},
		{ "RoleNotFound", "Roolia ei l�ydy tai se ei ole t�ydellinen"},
		{ "Authorized", "Valtuutettu"},
		{ "Ok", "Hyv�ksy"},
		{ "Cancel", "Peruuta"},
		{ "VersionConflict", "Versioristiriita:"},
		{ "VersionInfo", "Server <> Client"},
		{ "PleaseUpgrade", "Ole hyv� ja aja p�ivitysohjelma"}};

	/**
     * Get Contents
     * 
     * @return context
     */
	public Object[][] getContents ()
	{
		return contents;
	} // getContents
	
} // ALoginRes
