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
package org.adempiere.model;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.Properties;

import org.compiere.model.Query;
import org.compiere.util.CLogger;

/**
 * Class Model for Smart Browse
 * @author victor.perez@e-evoluton.com, e-Evolution
 *
 */
public class MBrowse extends X_AD_Browse 
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 3097647999717804581L;
	/**	Logger	*/
	private static CLogger	s_log = CLogger.getCLogger (MBrowse.class);
	private MView m_view = null;
	
	/**************************************************************************
	 * 	Asset Constructor
	 *	@param ctx context
	 *	@param AD_SmartBrowse_ID  InOutBound ID
	 *	@param trxName transaction name 
	 */
	public MBrowse (Properties ctx, int AD_SmartBrowse_ID, String trxName)
	{
		super (ctx, AD_SmartBrowse_ID, trxName);
		if (AD_SmartBrowse_ID == 0)
		{
		}
	}

	/**
	 * 	Discontinued Asset Constructor - DO NOT USE (but don't delete either)
	 *	@param ctx context
	 *	@param AD_SmartBrowse_ID Cahs Flow ID
	 */
	public MBrowse (Properties ctx, int AD_SmartBrowse_ID)
	{
		this (ctx, AD_SmartBrowse_ID, null);
	}

	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 *	@param trxName transaction
	 */
	public MBrowse (Properties ctx, ResultSet rs, String trxName)
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
		StringBuffer sb = new StringBuffer (" MSmartBrowse[")
			.append (get_ID ())
			.append ("-")
			.append (getName())
			.append ("]");
		return sb.toString ();
	}	//	toString
	
	/**
	 * get Criteria Fields
	 * @return collection Browse field
	 */
	public Collection <MBrowseField> getCriteriaFields()
	{
				
		String whereClause = MBrowseField.COLUMNNAME_AD_Browse_ID + "=? AND "
							+ MBrowseField.COLUMNNAME_IsDisplayed 	+ "=? AND "
							+ MBrowseField.COLUMNNAME_IsQueryCriteria 	+ "=?";
						
		
		return new Query(getCtx(),MBrowseField.Table_Name, whereClause, get_TrxName())
		.setParameters(new Object[]{get_ID(),"Y","Y"})
		.setOnlyActiveRecords(true)
		.setOrderBy(MBrowseField.COLUMNNAME_SeqNo)
		.list();		
	}
	
	/**
	 * get Criteria Fields
	 * @return Collection Fields
	 */
	public Collection <MBrowseField> getFields()
	{
				
		String whereClause = MBrowseField.COLUMNNAME_AD_Browse_ID + "=? AND "
							+ MBrowseField.COLUMNNAME_IsDisplayed 	+ "=? ";		
		return new Query(getCtx(),MBrowseField.Table_Name, whereClause, get_TrxName())
		.setParameters(new Object[]{get_ID(),"Y"})
		.setOnlyActiveRecords(true)
		.setOrderBy(MBrowseField.COLUMNNAME_SeqNo)
		.list();		
	}
	
	/**
	 * get field using name
	 * @param name field
	 * @return field 
	 */
	public MBrowseField getField(String name)
	{
		for (MBrowseField field : getFields())
		{
			if(field.getName().equals(name))
			{
				return field;
			}
		}
		return null;
	}
	
	/**
	 * get MView
	 */
	public MView getAD_View()
	{
		if(m_view == null)
		{
			m_view = new MView(getCtx(), getAD_View_ID(), get_TrxName());
		}
		return m_view;
	}
}	
