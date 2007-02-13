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
 * 	@author 	Erwin Cortes
 * 	@version 	$Id: ALoginRes_es.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 */
public final class ALoginRes_es extends ListResourceBundle
{
	/** Translation Content     */
	static final Object[][] contents = new String[][]
	{
	{ "Connection", "Conexión" },
	{ "Defaults", "Valores por Defecto" },
	{ "Login", "Login" },
	{ "File", "Archivo" },
	{ "Exit", "Salir" },
	{ "Help", "Ayuda" },
	{ "About", "Acerca de" },
	{ "Host", "Servidor" },
	{ "Database", "Base de datos" },
	{ "User", "ID de usuario" },
	{ "EnterUser", "ID de aplicación de usuario" },
	{ "Password", "Contraseña" },
	{ "EnterPassword", "Ingrese Contraseña" },
	{ "Language", "Lenguaje" },
	{ "SelectLanguage", "Seleccione su lenguaje" },
	{ "Role", "Perfil" },
	{ "Client", "Cliente" },
	{ "Organization", "Organización" },
	{ "Date", "Fecha" },
	{ "Warehouse", "Depósito" },
	{ "Printer", "Impresora" },
	{ "Connected", "Conectado" },
	{ "NotConnected", "No Conectado" },
	{ "DatabaseNotFound", "Base de datos no encontrada" },
	{ "UserPwdError", "Usuario-Contraseña no coincide" },
	{ "RoleNotFound", "Perfil no encontrado" },
	{ "Authorized", "Autorizado" },
	{ "Ok", "Aceptar" },
	{ "Cancel", "Cancelar" },
	{ "VersionConflict", "Conflicto de versión:" },
	{ "VersionInfo", "Servidor <> Cliente" },
	{ "PleaseUpgrade", "Favor de ejecutar Programa de actualización" }
	};

	/**
	 *  Get Contents
	 *  @return context
	 */
	public Object[][] getContents()
	{
		return contents;
	}   //  getContents
}	//	ALoginRes_es
