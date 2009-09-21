/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          * 
 * http://www.adempiere.org                                           * 
 *                                                                    * 
 * Copyright (C) Victor Perez	                                      * 
 * Copyright (C) Contributors                                         * 
 *                                                                    * 
 * This program is free software; you can redistribute it and/or      * 
 * modify it under the terms of the GNU General Public License        * 
 * as published by the Free Software Foundation; either version 2     * 
 * of the License, or (at your option) any later version.             * 
 *                                                                    * 
 * This program is distributed in the hope that it will be useful,    * 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of     * 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       * 
 * GNU General Public License for more details.                       * 
 *                                                                    * 
 * You should have received a copy of the GNU General Public License  * 
 * along with this program; if not, write to the Free Software        * 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         * 
 * MA 02110-1301, USA.                                                * 
 *                                                                    * 
 * Contributors:                                                      * 
 *  - Victor Perez (victor.perez@e-evolution.com	 )                *
 *                                                                    *
 * Sponsors:                                                          *
 *  - e-Evolution (http://www.e-evolution.com/)                       *
 **********************************************************************/

package org.eevolution.engines.Warehouse;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Properties;

import org.compiere.model.MLocator;
import org.compiere.model.MStorage;

public interface WMRuleInterface 
{
	public abstract Collection<MLocator> getLocator(Properties ctx,
			int M_Warehouse_ID,int M_Product_ID, int WM_Area_Type_ID,
			int WM_Section_Type_ID, String trxName);
	
	public abstract Collection<MStorage> getStorage(Properties ctx, int M_Warehouse_ID , int M_Product_ID, int M_AttributeSetInstance_ID, BigDecimal qtyToDelivery, int WM_Area_Type_ID , int WM_Section_Type_ID, String trxName);				
}
