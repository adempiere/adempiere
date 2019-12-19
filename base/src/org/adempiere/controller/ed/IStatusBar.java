/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2019 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.adempiere.controller.ed;

import org.compiere.model.DataStatusEvent;

/**
 * Interface for a status bar in a window or form.
 *
 */
public interface IStatusBar 
{
	/**
	 *	Set Status DB Info
	 *  @param text text
	 */
	public void setStatusDB (String text);
	
	/**
	 *	Set Status DB Info
	 *  @param text text
	 *  @param dse data status event
	 */
	public void setStatusDB (String text, DataStatusEvent dse);
	
	/**
	 *	Set Standard Status Line (non error)
	 *  @param text text
	 */
	public void setStatusLine (String text);
	
	/**
	 *	Set Status Line
	 *  @param text text
	 *  @param error error
	 */
	public void setStatusLine (String text, boolean error);
	
	/**
	 *	Set Info Line
	 *  @param text text
	 */
	public void setInfo (String text);
}
