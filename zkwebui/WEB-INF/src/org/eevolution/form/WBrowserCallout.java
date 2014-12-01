/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2003-2013 e-Evolution Consultants. All Rights Reserved.      *
 * Copyright (C) 2003-2013 Victor Pérez Juárez 								  * 
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
 * Contributor(s): Victor Pérez Juárez  (victor.perez@e-evolution.com)		  *
 * Sponsors: e-Evolution Consultants (http://www.e-evolution.com/)            *
 *****************************************************************************/
package org.eevolution.form;

import java.util.Properties;

import org.compiere.model.GridField;

/**
 *  BrowserCallout Interface for BrowserCallout.
 *  Used in Browser
 *  @author   eEvolution author Victor Perez<victor.perez@e-evolution.com>
 */ 
public interface WBrowserCallout
{

	/**
	 *	Start BrowserCallout.
	 *  <p>
	 *	Callout's are used for cross field validation and setting values in other fields
	 *	when returning a non empty (error message) string, an exception is raised
	 *  <p>
	 *	When invoked, the Tab model has the new value!
	 *
	 *  @param ctx      Context
	 *  @param method   Method name
	 *  @param WindowNo current Window No
	 *  @param mRow  	Row Browser
	 *  @param mField   Model Field
	 *  @param value    The new value
	 *  @param oldValue The old value
	 *  @return Error message or ""
	 */
	public String start (Properties ctx, String method, int WindowNo,
		WBrowserRows mRow, GridField mField, Object value, Object oldValue, int currentRow, int currentColumn);

	/**
	 *	Conversion Rules.
	 *	Convert a String
	 *
	 *	@param method   in notation User_Function
	 *  @param value    the value
	 *	@return converted String or Null if no method found
	 */
	public String convert (String method, String value);

}
