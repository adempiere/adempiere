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
 * Class Model for Table Index
 * @author victor.perez@e-evoluton.com, e-Evolution
 *
 */
public class MTableIndex extends X_AD_TableIndex
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7860094120756441280L;
	/**	Logger							*/
	private static CLogger	s_log = CLogger.getCLogger (MTableIndex.class);
	
	/**************************************************************************
	 * 	Table Index
	 *	@param ctx context
	 *	@param AD_SmartView_ID  
	 *	@param trxName transaction name 
	 */
	public MTableIndex (Properties ctx, int AD_TableIndex_ID, String trxName)
	{
		super (ctx, AD_TableIndex_ID, trxName);
		if (AD_TableIndex_ID == 0)
		{
		}
	}

	/**
	 * 	Table Index
	 *	@param ctx context
	 *	@param AD_TableIndex_ID Cahs Flow ID
	 */
	public MTableIndex (Properties ctx, int AD_TableIndex_ID)
	{
		this (ctx, AD_TableIndex_ID, null);
	}

	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 *	@param trxName transaction
	 */
	public MTableIndex (Properties ctx, ResultSet rs, String trxName)
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
		StringBuffer sb = new StringBuffer ("MTableIndex[")
			.append (get_ID ())
			.append ("-")
			.append (getName())
			.append ("]");
		return sb.toString ();
	}	//	toString
		
}	
