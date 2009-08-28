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
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.M_Element;
import org.compiere.model.Query;
import org.compiere.util.CLogger;

/**
 * Class Model for Browse Field
 * @author victor.perez@e-evoluton.com, e-Evolution
 *
 */
public class MBrowseField extends X_AD_Browse_Field
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -740931492029914957L;
	/**	Logger							*/
	private static CLogger	s_log = CLogger.getCLogger (MBrowseField.class);
	/** Element 						*/
	private M_Element m_element = null ;
	/** MViewColumn 						*/
	private MViewColumn m_view_column = null ;
	
	/**************************************************************************
	 * 	Asset Constructor
	 *	@param ctx context
	 *	@param AD_SmartBrowseField_ID  InOutBound ID
	 *	@param trxName transaction name 
	 */
	public MBrowseField (Properties ctx, int AD_SmartBrowseField_ID, String trxName)
	{
		super (ctx, AD_SmartBrowseField_ID, trxName);
		if (AD_SmartBrowseField_ID == 0)
		{
		}
	}

	/**
	 * 	Discontinued Asset Constructor - DO NOT USE (but don't delete either)
	 *	@param ctx context
	 *	@param AD_SmartBrowseField_ID Cahs Flow ID
	 */
	public MBrowseField (Properties ctx, int AD_SmartBrowseField_ID)
	{
		this (ctx, AD_SmartBrowseField_ID, null);
	}

	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 *	@param trxName transaction
	 */
	public MBrowseField (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	
	
	/**
	 * get MViewColumn base on MColumn
	 * @param column
	 */
	public MBrowseField (MViewColumn column)
	{
		super(column.getCtx(), 0 , column.get_TrxName());
		setAD_Element_ID(column.getAD_Element_ID());
		setName(column.getColumnName());
		setDescription(column.getDescription());
		setHelp(column.getHelp());
		setAD_View_Column_ID(column.get_ID());
		setIsActive(true);
		setIsIdentifier(column.isIdentifier());
		setAD_Reference_ID(column.getAD_Reference_ID());
		setIsKey(column.isKey());
		setIsDisplayed(false);
	}
	
	@Override
	protected boolean afterSave(boolean newRecord, boolean success)
	{
		if (!success)
		{
			return false;
		}
		
		if(getFieldKey() != null)
		{
			throw new AdempiereException("Only can have one field as key");
		}
		
		return success;
	}	

	public M_Element getElement()
	{
		if(m_element == null)
		{
			m_element =  new M_Element(getCtx(), getAD_Element_ID(), get_TrxName());			
		}
		return m_element;		
	}
	
	public MViewColumn getAD_View_Column()
	{
		if(m_view_column == null)
		{
			m_view_column = new MViewColumn(getCtx(), getAD_View_Column_ID(), get_TrxName());			
		}
		return m_view_column;		
	}
	
	/**
	 * 
	 * @return
	 */
	public MBrowseField getFieldKey()
	{
		final String whereClause = MBrowse.COLUMNNAME_AD_Browse_ID + "=? AND "
								 + MBrowseField.COLUMNNAME_IsKey + "=?";
		return new Query(getCtx(),MBrowse.Table_Name,whereClause, get_TrxName())
		.setParameters(new Object[]{this.getAD_Browse_ID(),"Y"})
		.firstOnly();
	}
	/**
	 * 	String representation
	 *	@return info
	 */
	@Override
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MSmartBrowseField")
			.append (get_ID ())
			.append ("-")
			.append (getName())
			.append ("]");
		return sb.toString ();
	}	//	toString
}	