/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (coffee) <Company or Author Name> All Rights Reserved.           *
 * This program is free software; you can redistribute it and/or              *
 * modify it under the terms of the GNU General Public License                *
 * as published by the Free Software Foundation; either version 2             *
 * of the License, or (at your option) any later version.                     *
 * This program is distributed in the hope that it will be useful,            *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of             *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                       *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *                                                                            *
 * @author Andrei Dragotel, a.dragotel@metas.ro METAS GROUP 				  *
 *				                                                              *
 *****************************************************************************/

package org.adempiere.ad.process;

import org.compiere.model.GridTab;

/**
 * Let your process implement this interface if you want to use it as related process in toolbar and you want to control when the process is applicable
 * 
 */
public interface ISvrProcessPrecondition
{
	/**
	 * Determines if a process should be displayed for the current tab.
	 * 
	 * @param gridTab
	 * @return true if the process will be displayed.
	 */
	boolean isPreconditionApplicable(GridTab gridTab);
}
