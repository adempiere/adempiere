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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.adempiere.core.domains.models.X_M_Product_PO;

/**
 *	Product PO Model
 *	
 *  @author Jorg Janke
 *  @author Víctor Pérez Juárez , victor.perez@e-evolution.com , http://www.e-evolution.com
 *  <a href="https://github.com/adempiere/adempiere/issues/1675">
 *  <li>Enhancement adding a button in project line to a generate a Purchase Order #1675
 */
public class MProductPO extends X_M_Product_PO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -747761340543484440L;

	/**
	 *
	 * @param ctx
	 * @param partnerId
	 * @param productId
	 * @param trxName
	 * @return
	 */
	public static List<MProductPO> getByPartner(Properties ctx , Integer partnerId, Integer productId, String trxName)
	{
		List<Object> parameters = new ArrayList<>();
		StringBuilder whereClause = new StringBuilder();
		Optional.ofNullable(partnerId)
				.filter(id -> id > 0)
				.ifPresent(Id -> {
					whereClause.append(MProductPO.COLUMNNAME_C_BPartner_ID).append("=? AND ");
					parameters.add(Id);
				});

		whereClause.append(MProductPO.COLUMNNAME_M_Product_ID).append("=?");
		parameters.add(productId);
		List<MProductPO> purchaseProducts = new Query(ctx, MProductPO.Table_Name, whereClause.toString() , trxName)
				.setClient_ID()
				.setParameters(parameters)
				.setOrderBy(MProductPO.COLUMNNAME_IsCurrentVendor)
				.list();
		if (purchaseProducts == null)
			return new ArrayList<>();
		else
			return purchaseProducts;

	}

	/**
	 * 	Get current PO of Product
	 * 	@param ctx context
	 *	@param M_Product_ID product
	 *	@param trxName transaction
	 *	@return PO - current vendor first
	 */
	public static MProductPO[] getOfProduct (Properties ctx, int M_Product_ID, String trxName)
	{
		final String whereClause = "M_Product_ID=?";
		List<MProductPO> list = new Query(ctx, Table_Name, whereClause, trxName)
									.setParameters(M_Product_ID)
									.setOnlyActiveRecords(true)
									.setOrderBy("IsCurrentVendor DESC")
									.list();
		return list.toArray(new MProductPO[list.size()]);
	}	//	getOfProduct

	/**
	 * 	Persistency Constructor
	 *	@param ctx context
	 *	@param ignored ignored
	 *	@param trxName transaction
	 */
	public MProductPO (Properties ctx, int ignored, String trxName)
	{
		super(ctx, 0, trxName);
		if (ignored != 0)
			throw new IllegalArgumentException("Multi-Key");
		else
		{
		//	setM_Product_ID (0);	// @M_Product_ID@
		//	setC_BPartner_ID (0);	// 0
		//	setVendorProductNo (null);	// @Value@
			setIsCurrentVendor (true);	// Y
		}
	}	//	MProduct_PO

	/**
	 * Load Constructor
	 * @param ctx
	 * @param productId
	 * @param partnerId
	 * @param trxName
	 */
	public MProductPO (Properties ctx , int productId , int partnerId , int currencyId , String trxName)
	{
		super(ctx, 0 , trxName);
		setM_Product_ID(productId);
		setC_BPartner_ID(partnerId);
		setC_Currency_ID(currencyId);
		setIsCurrentVendor (true);
	}
	
	
	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MProductPO(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MProductPO

}	//	MProductPO
