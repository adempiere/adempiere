/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.util.Env;


/**
 *	Shipper Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MShipper.java,v 1.3 2006/07/30 00:51:05 jjanke Exp $
 */
public class MShipper extends X_M_Shipper
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4026295839866634739L;


	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param M_Shipper_ID id
	 *	@param trxName transaction
	 */
	public MShipper (Properties ctx, int M_Shipper_ID, String trxName)
	{
		super (ctx, M_Shipper_ID, trxName);
	}	//	MShipper

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MShipper (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MShipper
	

	/**
	 * @param ctx
	 * @param FreightCategory_ID
	 * @param trxName
	 * @return      A list of shippers having the given freight category
	 */
	public static List<MShipper> getShippersForFreightCategory(Properties ctx, int FreightCategory_ID, String trxName) {
		Query q = new Query(ctx, I_M_Shipper.Table_Name,
				"M_Shipper.AD_Client_ID=? AND M_Shipper.AD_Org_ID IN (0,?) AND M_Shipper_ID " +
				"IN (SELECT M_Shipper_ID FROM M_Freight WHERE M_FreightCategory_ID=?)", trxName);
		q.setParameters(Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), FreightCategory_ID);
		List<MShipper> result = q.list();
		return(result);
	}

}	//	MShipper
