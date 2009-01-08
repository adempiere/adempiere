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
 * Order Node Product Model
 *
 * @author Victor Perez www.e-evolution.com      
 * @author Teo Sarca, www.arhipac.ro
 */
public class MPPOrderNodeProduct extends  X_PP_Order_Node_Product
{
	private static final long	serialVersionUID	= 1L;

	public MPPOrderNodeProduct (Properties ctx, int PP_WF_Order_Product_ID, String trxName)
	{
		super (ctx, PP_WF_Order_Product_ID, trxName);
		if (PP_WF_Order_Product_ID == 0)
		{		
		}
	}
	
	public MPPOrderNodeProduct (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	

	/**
	 * Create a new MPPOrderNodeProduct based in MPPWFNodeProduct
	 * @param np
	 * @param PP_Order_Node order node
	 */
	public MPPOrderNodeProduct (MPPWFNodeProduct np, MPPOrderNode PP_Order_Node)
	{
		this(PP_Order_Node.getCtx(), 0, PP_Order_Node.get_TrxName());
		setClientOrg(PP_Order_Node);
		setSeqNo(np.getSeqNo());
		setIsActive(np.isActive());
		setM_Product_ID(np.getM_Product_ID());
		setQty(np.getQty());
		setIsSubcontracting(np.isSubcontracting());
		setYield(np.getYield());
		//
		setPP_Order_ID(PP_Order_Node.getPP_Order_ID());
		setPP_Order_Workflow_ID(PP_Order_Node.getPP_Order_Workflow_ID());
		setPP_Order_Node_ID(PP_Order_Node.get_ID());
	}
}
