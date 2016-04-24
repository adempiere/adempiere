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
package org.eevolution.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.model.Query;
import org.compiere.util.CLogger;

/**
 * Class Model Warehouse Section
 * @author victor.perez@e-evoluton.com, e-Evolution
 *
 */
public class MWMSection extends X_WM_Section
{

	/**
	 * Get Section by warehouse id for Out Boud Type
	 * @param ctx
	 * @param warehouseAreaId
	 * @param trxName
     * @return
     */
	public static List<MWMSection> getByOutBoundType(Properties ctx, int warehouseAreaId, String trxName)
	{
		ArrayList <MWMSection> sections = new ArrayList();
		for (MWMSectionType sectionType: MWMSectionType.getByOutBoundType(ctx, trxName))
		{
			sections.addAll(MWMSection.getByAreaAndSectionType(ctx, warehouseAreaId, sectionType.getWM_Section_Type_ID(), trxName));
		}
		return sections;
	}

	/**
	 * Get Stection by warehouse id for In Bound Type
	 * @param ctx
	 * @param warehouseAreaID
	 * @param trxName
     * @return
     */
	public static List <MWMSection> getByInBoundType(Properties ctx, int warehouseAreaID, String trxName)
	{
		ArrayList <MWMSection> sections = new ArrayList();
		for (MWMSectionType sectionType: MWMSectionType.getByInBoundType(ctx, trxName))
		{
			sections.addAll(MWMSection.getByAreaAndSectionType(ctx, warehouseAreaID, sectionType.getWM_Section_Type_ID(), trxName));
		}
		return sections;
	}

	/**
	 * Get Section by Area Id and section type id
	 * @param ctx
	 * @param areaId
	 * @param warehouseSectionTypeId
	 * @param trxName
     * @return
     */
	public static List <MWMSection> getByAreaAndSectionType(Properties ctx, int areaId, int warehouseSectionTypeId, String trxName)
	{
		final String whereClause = 	MWMSection.COLUMNNAME_WM_Area_ID + "=? AND"
								 +	MWMSection.COLUMNNAME_WM_Section_Type_ID + "=?";
		return new Query(ctx, MWMSection.Table_Name, whereClause, trxName)
			.setClient_ID()
			.setParameters(areaId, warehouseSectionTypeId)
			.list(); 
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -44571198059439971L;
	/**	Logger	**/
	private static CLogger	s_log = CLogger.getCLogger (MWMSection.class);
	
	/**************************************************************************
	 * 	Warehouse Section
	 *	@param ctx context
	 *	@param WM_Section_ID
	 *	@param trxName transaction name 
	 */
	public MWMSection (Properties ctx, int WM_Section_ID, String trxName)
	{
		super (ctx, WM_Section_ID, trxName);
		if (WM_Section_ID == 0)
		{
		}
	}

	/**
	 * 	Warehouse Section
	 *	@param ctx context
	 *	@param WM_Section_ID Warehouse Section ID
	 */
	public MWMSection (Properties ctx, int WM_Section_ID)
	{
		this (ctx, WM_Section_ID, null);
	}

	
	
	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 *	@param trxName transaction
	 */
	public MWMSection (Properties ctx, ResultSet rs, String trxName)
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
		StringBuffer sb = new StringBuffer ("MWMSection[")
			.append (get_ID ())
			.append ("-")
			.append (getName())
			.append ("]");
		return sb.toString ();
	}	//	toString
}	
