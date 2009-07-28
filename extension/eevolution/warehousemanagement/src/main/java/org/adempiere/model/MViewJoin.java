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
import java.util.Collection;
import java.util.Properties;

import org.compiere.model.MColumn;
import org.compiere.model.MDocType;
import org.compiere.model.MTable;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 * Class Model for Inbound & Outbound Operation
 * @author victor.perez@e-evoluton.com, e-Evolution
 *
 */
public class MViewJoin extends X_AD_ViewJoin
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 8557695983263069806L;

	public static  MViewJoin get(Properties ctx, int AD_ViewJoin_ID)
	{
		if (AD_ViewJoin_ID <= 0)
			return null;
		//
		MViewJoin join = s_cache.get(AD_ViewJoin_ID);
		if (join != null)
			return join;
		//
		join = new MViewJoin(ctx, AD_ViewJoin_ID, null);
		if (join.get_ID() == AD_ViewJoin_ID)
		{
			s_cache.put(AD_ViewJoin_ID, join);
		}
		else
		{
			join = null;	
		}
		return join; 
	}

	/**	Logger							*/
	private static CLogger	s_log = CLogger.getCLogger (MViewJoin.class);
	/** Cache */
	private static CCache<Integer, MViewJoin> s_cache= new CCache<Integer, MViewJoin>(Table_Name, 100);
	/** Parent */
	private MView m_view = null; 
	/**Join Table */
	private MTable m_table = null;
	
	/**************************************************************************
	 * 	Asset Constructor
	 *	@param ctx context
	 *	@param AD_ViewJoin_ID 
	 *	@param trxName transaction name 
	 */
	public MViewJoin (Properties ctx, int AD_ViewJoin_ID, String trxName)
	{
		super (ctx, AD_ViewJoin_ID, trxName);
		if (AD_ViewJoin_ID == 0)
		{
		}
	}

	/**
	 * 	Discontinued Asset Constructor - DO NOT USE (but don't delete either)
	 *	@param ctx context
	 *	@param AD_ViewJoin_ID 
	 */
	public MViewJoin (Properties ctx, int AD_ViewJoin_ID)
	{
		this (ctx, AD_ViewJoin_ID, null);
	}

	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 *	@param trxName transaction
	 */
	public MViewJoin (Properties ctx, ResultSet rs, String trxName)
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
		StringBuffer sb = new StringBuffer ("MViewJoin[")
			.append (get_ID ())
			.append ("-")
			.append ("")
			.append ("]");
		return sb.toString ();
	}	//	toString
	
	/**
	 * Get Parent View
	 * @return
	 */
	public MView getAD_View()
	{
		if (m_view == null)
		{
			m_view = (MView) getAD_View();
		}
		
	return m_view;	
	}

	/**
	 * Get Parent View
	 * @return
	 */
	public MTable getAD_Table()
	{
		if (m_table == null)
		{
			m_table = MTable.get(getCtx(), getAD_Table_ID());
		}
		
	return m_table;	
	}
	
	public String getSelectClause()
	{
		StringBuffer selectClause = new StringBuffer();
		Collection<MColumn> atts = getEntityAttributes();
		for (MColumn attr : atts)
		{
			 selectClause.append(getTableName())
			 			 .append(attr.getColumnName())
			 			 .append(",");
		}
		return selectClause.toString();
	}
	
	public String getJoinClause()
	{
		String joinEntityName = getAD_Table().getName();
		StringBuffer  joinClause = new StringBuffer("");
				      joinClause.append(joinEntityName)
				      			.append(" ")
								.append(getTableName())
								.append("ON (")
								.append("")
								.append(")");
	    return joinClause.toString();
	}
	
	
	public Collection<MColumn> getEntityAttributes()
	{
		final String whereClause = MColumn.COLUMNNAME_AD_Table_ID + "=?";
		
		return new Query(getCtx(),MColumn.Table_Name, whereClause, get_TrxName())
										.setParameters(new Object[]{getAD_Table_ID()})
										.setOnlyActiveRecords(true)
										.list();
	}
	
	public Collection<MViewColumn> getADViewColunms()
	{
		final String whereClause = MViewColumn.COLUMNNAME_AD_ViewJoin_ID + "=?";
		
		return new Query(getCtx(),MViewColumn.Table_Name, whereClause, get_TrxName())
										.setParameters(new Object[]{getAD_ViewJoin_ID()})
										.list();
	}
}	
