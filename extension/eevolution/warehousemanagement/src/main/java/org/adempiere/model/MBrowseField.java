/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.adempiere.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MColumn;
import org.compiere.model.MDocType;
import org.compiere.model.M_Element;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 * Class Model for Inbound & Outbound Operation
 * @author victor.perez@e-evoluton.com, e-Evolution
 *
 */
public class MBrowseField extends X_AD_Browse_Field
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4256005864457121353L;
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
