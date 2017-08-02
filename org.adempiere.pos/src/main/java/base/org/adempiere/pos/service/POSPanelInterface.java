/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.               *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package org.adempiere.pos.service;

/**
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com Sep 22, 2015, 11:52:44 PM
 * <li> Interface for implement refresh and validate methods in POS Panel
 */
public interface POSPanelInterface {
	
	/**
	 * Refresh Panel for changes
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return void
	 */
	public void refreshPanel();
	
	/**
	 * Move Position up
	 * @author Raul Muñoz, rmunoz@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return void
	 */
	public void moveUp();
	
	/**
	 * Move Position Down
	 * @author Raul Muñoz, rmunoz@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return void
	 */
	public void moveDown();
	/**
	 * Validate Input values
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return String
	 */
	public String validatePayment();
	
	/**
	 * Change view from other action
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return void
	 */
	public void changeViewPanel();
}