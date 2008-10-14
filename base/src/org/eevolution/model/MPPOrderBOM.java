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
//package org.compiere.mfg.model;
package org.eevolution.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 *  Order BOM Model.
 *
 *  @author Victor Perez www.e-evolution.com     
 *  @version $Id: MOrder.java,v 1.40 2004/04/13 04:19:30 vpj-cd Exp $
 *  
 *  @author Teo Sarca, www.arhipac.ro
 */
public class MPPOrderBOM extends X_PP_Order_BOM
{
	private static final long serialVersionUID = 1L;

	/**
	 *  Default Constructor
	 *  @param ctx context
	 *  @param  C_Order_ID    order to load, (0 create new order)
	 */
	public MPPOrderBOM(Properties ctx, int PP_Order_BOM_ID,String trxName)
	{
		super (ctx,  PP_Order_BOM_ID,trxName);
		//  New
		if ( PP_Order_BOM_ID == 0)
		{
			setProcessing(false);
		}
	}	//	MOrder

	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 */
	public MPPOrderBOM(Properties ctx, ResultSet rs,String trxName)
	{
		super (ctx, rs, trxName);
	}	//	MOrder
	
	/**
	 * Peer constructor
	 * @param bom
	 * @param PP_Order_ID
	 * @param trxName
	 */
	public MPPOrderBOM(MPPProductBOM bom, int PP_Order_ID, String trxName)
	{
		this(bom.getCtx(), 0, trxName);
		//
		setBOMType(bom.getBOMType());
		setBOMUse(bom.getBOMUse());
		setM_ChangeNotice_ID(bom.getM_ChangeNotice_ID());
		setHelp(bom.getHelp());
		//setCopyFrom(bom.getCopyFrom());
		setProcessing(bom.isProcessing());
		setHelp(bom.getHelp());
		setDescription(bom.getDescription());
		setM_AttributeSetInstance_ID(bom.getM_AttributeSetInstance_ID());
		setM_Product_ID(bom.getM_Product_ID());
		setName(bom.getName());
		setRevision(bom.getRevision());
		setValidFrom(bom.getValidFrom());
		setValidTo(bom.getValidTo());
		setValue(bom.getValue());
		setDocumentNo(bom.getDocumentNo());
		setC_UOM_ID(bom.getC_UOM_ID());
		//
		setPP_Order_ID(PP_Order_ID);
	}

	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MPPOrderBOM[")
		.append(get_ID()).append("-").append(getDocumentNo())
		.append ("]");
		return sb.toString ();
	}	//	toString
}	//	MPPOrderBOM
