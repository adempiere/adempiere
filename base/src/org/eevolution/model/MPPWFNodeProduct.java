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

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import org.compiere.model.Query;

/**
 *	Forcast Line Model
 *	
 *  @author Victor Perez www.e-evolution.com      
 *  @version $Id: MPPWFNodeProduct.java,v 1.11 2005/05/17 05:29:52 vpj-cd vpj-cd $
 */
public class MPPWFNodeProduct extends  X_PP_WF_Node_Product
{
	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param M_ForecastLine_ID id
	 */
	public MPPWFNodeProduct (Properties ctx, int PP_WF_Node_Product_ID, String trxName)
	{
		super (ctx, PP_WF_Node_Product_ID, trxName);
		if (PP_WF_Node_Product_ID == 0)
		{		
		}
		
	}	//	MQMSpecification

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 */
	public MPPWFNodeProduct (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MQMSpecification	
		
	/** Lines						*/
	private static Collection <MPPWFNodeProduct>		m_lines = new ArrayList<MPPWFNodeProduct>();
	
	/**
	 * 	Get Lines
	 *	@return array of lines
	 */
	public static Collection<MPPWFNodeProduct> get(Properties ctx, int AD_WF_Node_ID , String trxName)
	{
		if(!m_lines.isEmpty())
			return m_lines;
		
		String whereClause = "AD_WF_Node_ID=?";
		m_lines = new Query(ctx, MPPWFNodeProduct.Table_Name, whereClause, trxName)
											.setParameters(new Object[]{AD_WF_Node_ID})
											.setOnlyActiveRecords(true)
											.setOrderBy(MPPWFNodeProduct.COLUMNNAME_SeqNo)
											.list();		
		return m_lines;
	}	//	getMPPOrderNodeProduct
	
}	//	 MPPWFNodeProduct
