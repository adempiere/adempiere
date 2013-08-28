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
 *                 Teo Sarca, www.arhipac.ro                                  *
 *****************************************************************************/
package org.eevolution.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Order Node Asset Model
 *	
 * @author Victor Perez www.e-evolution.com      
 * @author Teo Sarca, www.arhipac.ro
 */
public class MPPOrderNodeAsset extends  X_PP_Order_Node_Asset
{
	private static final long	serialVersionUID	= 1L;

	public MPPOrderNodeAsset (Properties ctx, int PP_Order_Node_Asset_ID, String trxName)
	{
		super (ctx, PP_Order_Node_Asset_ID, trxName);
		if (PP_Order_Node_Asset_ID == 0)
		{		
		}
	}	
	
	public MPPOrderNodeAsset (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	
		
	/**
	 * Create a new MPPOrderNodeAsset based in MPPWFNodeProduct
	 * @param na
	 * @param PP_Order_Node 
	 */
	public MPPOrderNodeAsset (MPPWFNodeAsset na, MPPOrderNode PP_Order_Node)
	{
		this(PP_Order_Node.getCtx(), 0, PP_Order_Node.get_TrxName());
		setClientOrg(PP_Order_Node);
		//setSeqNo(na.getSeqNo());
		setA_Asset_ID(na.getA_Asset_ID());
		//
		setPP_Order_ID(PP_Order_Node.getPP_Order_ID());
		setPP_Order_Workflow_ID(PP_Order_Node.getPP_Order_Workflow_ID());
		setPP_Order_Node_ID(PP_Order_Node.get_ID());
	}
}
