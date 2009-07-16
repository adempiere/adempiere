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
package org.eevolution.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Iterator;
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
 * Class Model for Smart View
 * @author victor.perez@e-evoluton.com, e-Evolution
 *
 */
public class MView extends X_AD_View
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 2778788548664762049L;
	/**	Logger							*/
	private static CLogger	s_log = CLogger.getCLogger (MView.class);
	
	/**************************************************************************
	 * 	Asset Constructor
	 *	@param ctx context
	 *	@param AD_SmartView_ID  
	 *	@param trxName transaction name 
	 */
	public MView (Properties ctx, int AD_SmartView_ID, String trxName)
	{
		super (ctx, AD_SmartView_ID, trxName);
		if (AD_SmartView_ID == 0)
		{
		}
	}

	/**
	 * 	Discontinued Asset Constructor - DO NOT USE (but don't delete either)
	 *	@param ctx context
	 *	@param AD_SmartView_ID Cahs Flow ID
	 */
	public MView (Properties ctx, int AD_SmartView_ID)
	{
		this (ctx, AD_SmartView_ID, null);
	}

	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 *	@param trxName transaction
	 */
	public MView (Properties ctx, ResultSet rs, String trxName)
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
		StringBuffer sb = new StringBuffer ("MInOutBound[")
			.append (get_ID ())
			.append ("-")
			.append (getName())
			.append ("]");
		return sb.toString ();
	}	//	toString
	
	/**
	 * return the Smart View Joins
	 * @return
	 */
	public Collection<MViewJoin> getViewJoins()
	{
		final String whereClause = COLUMNNAME_AD_View_ID + "= ?";
		return new Query(getCtx(), MViewJoin.Table_Name, whereClause, get_TrxName()).
					setParameters(new Object[]{get_ID()})
					.setOrderBy(MViewJoin.COLUMNNAME_SeqNo)
					.setOnlyActiveRecords(true)
					.list();
	}
	
	/**
	 * return the Smart View Joins
	 * @return String 
	 */
	public String getJoinsTables()
	{
		final String whereClause = COLUMNNAME_AD_View_ID + "= ?";
		Iterator<MViewJoin> joins = new Query(getCtx(), MViewJoin.Table_Name, whereClause, get_TrxName()).
					setParameters(new Object[]{get_ID()})
					.setOrderBy(MViewJoin.COLUMNNAME_SeqNo)
					.setOnlyActiveRecords(true)
					.iterate();
		StringBuffer tables = new StringBuffer(""); 
		while(joins.hasNext())
		{
			MViewJoin join = joins.next();
			tables.append(join.getAD_Table().getTableName());
			if(joins.hasNext())
			{	
				tables.append(",");
			}	
		}
		return tables.toString();
	}
	
	public String getFromClause()
	{
		String fromClause = " ";
		String joinClause = " ";
		for (MViewJoin join : getViewJoins())
		{
			if(join.getJoinPhrase() == null)
			{
				fromClause = fromClause + join.getAD_Table().getTableName() + " " + join.getTableName();
			}
			else if (join.getJoinPhrase().length() > 0)
			{
				joinClause = joinClause + join.getJoinPhrase() + " ";
			}
		}
		
		return fromClause + joinClause;
	}
	
	public Collection<MViewColumn> getViewColumn(int AD_View_ID)
	{
		final String whereClause = MViewColumn.COLUMNNAME_AD_View_ID + "=?";
		return new Query(getCtx(), MViewColumn.Table_Name, whereClause, get_TrxName()).
					setParameters(new Object[]{AD_View_ID})
					.setOnlyActiveRecords(true)
					.list();
	}
	
	public MViewJoin getParentViewJoin()
	{
		String whereClause =  MViewJoin.COLUMNNAME_AD_View_ID + "=? AND "
							+ MViewJoin.COLUMNNAME_JoinPhrase +" IS NULL"; 
		return new Query(getCtx(),MViewJoin.Table_Name, whereClause, get_TrxName())
		.setParameters(new Object[]{getAD_View_ID()})
		.setOnlyActiveRecords(true)
		.setOrderBy( MViewJoin.COLUMNNAME_SeqNo)
		.firstOnly();
	}
	
	public String getParentEntityName()
	{
		return MTable.getTableName(getCtx(), getParentViewJoin().getAD_Table_ID());
	}
	
	public String getParentEntityAliasName()
	{
		 return getParentViewJoin().getTableName();
	}
}	
