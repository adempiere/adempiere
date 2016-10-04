/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2016 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpcya.com                                 *
 *****************************************************************************/
package org.compiere.apps;

import org.compiere.util.ASyncProcess;

/**
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/571">
 * 		@see FR [ 571 ] Process Dialog is not MVC</a>
 *
 */
public interface IProcessDialog {
	
	/**
	 * Print Screen
	 */
	public void printScreen();
	
	/**
	 * Validate Screen
	 */
	public void validateScreen();
	
	/**
	 * Show center screen
	 */
	public void showCenterScreen();
	
	/**
	 * Dispose Dialog
	 */
	public void dispose();
	
	/**
	 * Get Parent Container
	 * @return
	 */
	public Object getParentContainer();
	
	/**
	 * Get Parent Process for waiting method
	 * @return
	 */
	public ASyncProcess getParentProcess();
	
	/**
	 * Verify if is a Embedded dialog
	 * @return
	 */
	public boolean isEmbedded();
}
