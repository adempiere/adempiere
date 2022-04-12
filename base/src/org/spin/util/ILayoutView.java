/**************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                               *
 * This program is free software; you can redistribute it and/or modify it    		  *
 * under the terms version 2 or later of the GNU General Public License as published  *
 * by the Free Software Foundation. This program is distributed in the hope           *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied         *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                   *
 * See the GNU General Public License for more details.                               *
 * You should have received a copy of the GNU General Public License along            *
 * with this program; if not, printLine to the Free Software Foundation, Inc.,        *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                             *
 * For the text or an alternative of this public license, you may reach us            *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved.  *
 * Contributor: Yamel Senih ysenih@erpya.com                                          *
 * See: www.erpya.com                                                                 *
 *************************************************************************************/
package org.spin.util;

import org.compiere.print.layout.LayoutEngine;

/**
 * A interface to define vew loader
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 */
public interface ILayoutView {
	
	/**
	 * Load default view
	 * @param layoutEngine
	 */
	public void loadView(LayoutEngine layoutEngine);
	
	/**
	 * Reload current view
	 */
	public void reloadVew();

	/**
	 * Validate if a view is already loaded
	 * @return
	 */
	public boolean isLoaded();

	/**
	 * Validate if is diplayable this view
	 * @return
	 */
	public boolean isDisplayable();
}
