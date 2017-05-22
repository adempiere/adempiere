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
 * Copyright (C) 2003-2015 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpcya.com                                 *
 *****************************************************************************/
package org.compiere.apps.form;

import org.compiere.minigrid.IMiniTable;

/**
 * 	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 114 ] Change "Create From" UI for Form like Dialog in window without "hardcode"
 *		<li> It is result from change CreateFrom to dynamic form
 *		@see https://github.com/adempiere/adempiere/issues/114
 */
public interface ICreateFrom {
	
	/**
	 * Get Window No from Form
	 * @return
	 */
	public int getWindowNo();

	/**
	 * Prepare Information
	 * Return false if it is not used
	 */
	public boolean info();

	/**
	 * Save Data from table
	 * @param miniTable
	 * @param trxName
	 * @return
	 */
	public boolean save(IMiniTable miniTable, String trxName);
	
	/**
	 * Close Window
	 */
	public void dispose();
}