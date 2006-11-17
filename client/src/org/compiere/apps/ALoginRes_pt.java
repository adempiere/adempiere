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
 * 	@author 	Jesse Jr
 * 	@version 	$Id: ALoginRes_pt.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 */
public final class ALoginRes_pt extends ListResourceBundle
{
	// TODO Run native2ascii to convert to plain ASCII !! 
	
	/** Translation Content     */
	static final Object[][] contents = new String[][]
	{
	{ "Connection",         "Conex�o" },
	{ "Defaults",           "Padr�es" },
	{ "Login",              "Adempiere Login" },
	{ "File",               "Arquivo" },
	{ "Exit",               "Sair" },
	{ "Help",               "Ajuda" },
	{ "About",              "Sobre" },
	{ "Host",               "Servidor" },
	{ "Database",           "Banco de Dados" },
	{ "User",               "ID Usu�rio" },
	{ "EnterUser",          "Entre com o ID Usu�rio da Aplica��o" },
	{ "Password",           "Senha" },
	{ "EnterPassword",      "Entre com a Senha da Aplica��o" },
	{ "Language",           "Idioma" },
	{ "SelectLanguage",     "Selecione o idioma" },
	{ "Role",               "Regra" },
	{ "Client",             "Cliente" },
	{ "Organization",       "Organiza��o" },
	{ "Date",               "Data" },
	{ "Warehouse",          "Dep�sito" },
	{ "Printer",            "Impressora" },
	{ "Connected",          "Conectado" },
	{ "NotConnected",       "N�o conectado" },
	{ "DatabaseNotFound",   "Banco de Dados n�o encontrado" },
	{ "UserPwdError",       "Usu�rio/Senha inv�lidos" },
	{ "RoleNotFound",       "Regra n�o encontrada/incorreta" },
	{ "Authorized",         "Autorizado" },
	{ "Ok",                 "Ok" },
	{ "Cancel",             "Cancelar" },
	{ "VersionConflict",    "Conflito de Vers�es:" },
	{ "VersionInfo",        "Servidor <> Cliente" },
	{ "PleaseUpgrade",      "Favor executar o programa de atualiza��o" }
	};

	/**
	 *  Get Contents
	 *  @return context
	 */
	public Object[][] getContents()
	{
		return contents;
	}   //  getContents
}   //  ALoginRes_pt
