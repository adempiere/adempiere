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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;


import org.compiere.model.MLocator;
import org.compiere.model.MStorage;
import org.compiere.model.Query;
import org.eevolution.model.MWMArea;
import org.eevolution.model.MWMSection;
import org.eevolution.model.MWMSectionDetail;

public class WMRuleFIFO implements WMRuleInterface
{

	public Collection<MLocator> getLocator(Properties ctx,
			int M_Warehouse_ID, int M_Product_ID, int WM_Area_Type_ID,
			int WM_Section_Type_ID, String trxName) 
	{
		ArrayList<MLocator> locators = new ArrayList();		
		Collection<MWMArea> areas = MWMArea.getMWMArea(ctx, M_Warehouse_ID, trxName);
		for (MWMArea area : areas)
		{
			Collection<MWMSection> sections = area.getWMSection(WM_Section_Type_ID);
			for(MWMSection section : sections)
			{
				locators.addAll(MWMSectionDetail.getMLocators(section));
			}
		}
		
		return locators;
	}

	public Collection<MStorage> getStorage(Properties ctx, int M_Product_ID,
			BigDecimal qtyToDelivery, String trxName) {
		// TODO Auto-generated method stub
		return null;
	}

}
