 /******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) <Company or Author Name> All Rights Reserved.                *
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
 *																		      *
 * @author Tobias Schöneberg, t.schoeneberg@metas.de, METAS GROUP			  *
 *  			                                                       		  *
 *****************************************************************************/
package org.adempiere.util;


/**
 * Interface implementations can be passed to business logic to perform high-level logging. The signature of this
 * interface's only method is chosen so that all {@link org.compiere.process.SvrProcess} subclasses can implement it without further code
 * changes.
 * 
 */
public interface ILoggable
{
	/**
	 * Add a log message.
	 * 
	 * @param msg
	 */
	public void addLog(String msg);
}
