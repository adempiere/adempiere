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
package org.eevolution.wms.model;

import org.adempiere.core.domains.models.X_WM_Section_Detail;
import org.compiere.model.MLocator;
import org.compiere.model.Query;
import org.compiere.util.CLogger;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Class Model Warehouse Section Detail
 * @author victor.perez@e-evoluton.com, e-Evolution
 *
 */
public class MWMSectionDetail extends X_WM_Section_Detail
{


	/**
	 * 
	 */
	private static final long serialVersionUID = -180049576847612335L;
	
	/**
	 * get the Locator for this Section
	 * @param section
	 * @return Collection of the MLocator
	 */
	public  static List<MLocator> getLocatorBySection(MWMSection section)
	{
		ArrayList locators = new ArrayList();  
		String whereClause = "EXISTS (SELECT 1 FROM WM_Section_Detail sd WHERE sd.WM_Section_ID = ? AND sd.M_Locator_ID=M_Locator.M_Locator_ID) ";
		return new Query(section.getCtx(), MLocator.Table_Name, whereClause, section.get_TrxName())
			.setOnlyActiveRecords(true)
			.setParameters(new Object[]{section.getWM_Section_ID()})
			.list();	
	}
	
	
	/**	Logger	**/
	private static CLogger	s_log = CLogger.getCLogger (MWMSectionDetail.class);
	
	
	
	/**************************************************************************
	 * 	Warehouse Section Detail
	 *	@param ctx context
	 *	@param WM_Section_Detail_ID
	 *	@param trxName transaction name 
	 */
	public MWMSectionDetail (Properties ctx, int WM_Section_Detail_ID, String trxName)
	{
		super (ctx, WM_Section_Detail_ID, trxName);
		if (WM_Section_Detail_ID == 0)
		{
		}
	}

	/**
	 * 	Warehouse Section Detail
	 *	@param ctx context
	 *	@param WM_Section_Detail_ID Warehouse Section ID
	 */
	public MWMSectionDetail (Properties ctx, int WM_Section_Detail_ID)
	{
		this (ctx, WM_Section_Detail_ID, null);
	}

	
	
	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 *	@param trxName transaction
	 */
	public MWMSectionDetail (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MAsset

	/**
	 * 	String representation
	 *	@return info
	 */
	@Override
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MWMSectionDetail[")
			.append (get_ID ())
			.append ("]");
		return sb.toString ();
	}	//	toString
}	
