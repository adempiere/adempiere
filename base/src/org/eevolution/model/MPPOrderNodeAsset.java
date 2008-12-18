/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package org.eevolution.model;

import java.math.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;


import org.compiere.model.*;
import org.compiere.util.*;

/**
 *	Forcast Line Model
 *	
 *  @author Victor Perez www.e-evolution.com      
 *  @version $Id: MPPWFNodeAsset.java,v 1.11 2005/05/17 05:29:52 vpj-cd vpj-cd $
 */
public class MPPOrderNodeAsset extends  X_PP_Order_Node_Asset
{
	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param M_ForecastLine_ID id
	 */
	public MPPOrderNodeAsset (Properties ctx, int PP_Order_Node_Asset_ID, String trxName)
	{
		super (ctx, PP_Order_Node_Asset_ID, trxName);
		if (PP_Order_Node_Asset_ID == 0)
		{		
		}
		
	}	
	
	/**
	 * Create a new MPPOrderNodeAsset based in MPPWFNodeProduct
	 * @param np
	 */
	public MPPOrderNodeAsset (MPPWFNodeAsset na)
	{
		this(na.getCtx(), 0, na.get_TrxName());
		//setSeqNo(na.getSeqNo());
		setIsActive(na.isActive());
		setA_Asset_ID(na.getA_Asset_ID());
	}

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 */
	public MPPOrderNodeAsset (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	 MPPWFNodeAsset	
		
	/** Lines						*/
	private static Collection<MPPOrderNodeAsset>		m_lines = new ArrayList<MPPOrderNodeAsset>();
	
	/**
	 * 	Get Lines
	 *	@return array of lines
	 */
	public  Collection<MPPOrderNodeAsset> getNodeAsset()
	{
		if(!m_lines.isEmpty())
			return m_lines;
		
		String whereClause = "PP_Order_Node_Asset_ID=? ";
		m_lines = new Query(getCtx(), MPPOrderNodeAsset.Table_Name, whereClause, get_TrxName())
											.setParameters(new Object[]{get_ID()})
											.setOnlyActiveRecords(true)
											//.setOrderBy(MPPOrderNodeAsset.COLUMNNAME_SeqNo)
											.list();		
		return m_lines;
	}	//	getLines	
}	//	 MPPWFNodeAsset
