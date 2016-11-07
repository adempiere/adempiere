/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2016 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.util.Env;

public class MProductionBatchLine extends X_M_ProductionBatchLine
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8836637198723346713L;

	public MProductionBatchLine(Properties ctx, int M_PBatch_Line_ID, String trxName)
	{
		super(ctx, M_PBatch_Line_ID, trxName);
	}

	public MProductionBatchLine(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}
	
	public static MProductionBatchLine getbyProduct(int M_ProductionBatch_ID, int M_Product_ID, Properties ctx, String TrxName)
	{
		String whereClause = MProductionBatch.COLUMNNAME_M_ProductionBatch_ID + "=? and " +
				MProductionBatch.COLUMNNAME_M_Product_ID + " =?";
		ArrayList<Object> params = new ArrayList<>();
		params.add(M_ProductionBatch_ID);
		params.add(M_Product_ID);
		MProductionBatchLine pbLine = new Query(Env.getCtx()	, MProductionBatchLine.Table_Name, whereClause, TrxName)
				.setParameters(params)
				.first();
		return pbLine;
	}
	@Override
	protected boolean afterDelete(boolean success)
	{
		
		if (getQtyReserved().signum()==0)
		return true;

		BigDecimal diff = getQtyReserved();
		BigDecimal reservedQty = Env.ZERO;
		BigDecimal orderedQty = Env.ZERO;
		if (isEndProduct())
			orderedQty = diff;
		else
			reservedQty = diff.negate();
		if (!MStorage.add(getCtx(), getM_ProductionBatch().getM_Locator().getM_Warehouse_ID(), getM_ProductionBatch().getM_Locator_ID(),
				getM_Product_ID(), 0, 0, Env.ZERO, reservedQty,orderedQty , get_TrxName()))			
			return false;		
		return true;
	}

	protected boolean afterSave(boolean newRecord, boolean success)
	{
		//params
		//(Properties ctx, int M_Warehouse_ID, int M_Locator_ID, 
		//		int M_Product_ID, int M_AttributeSetInstance_ID, int reservationAttributeSetInstance_ID,
		//		BigDecimal diffQtyOnHand, 
		//		BigDecimal diffQtyReserved, BigDecimal diffQtyOrdered, String trxName)
		BigDecimal oldValue=	(BigDecimal)get_ValueOld(COLUMNNAME_QtyReserved);
		BigDecimal diff = newRecord?oldValue:getQtyReserved().subtract(oldValue);
		BigDecimal reservedQty = Env.ZERO;
		BigDecimal orderedQty = Env.ZERO;
		if (isEndProduct())
			orderedQty = diff;
		else
			reservedQty = diff;
		MStorage.add(getCtx(), getM_ProductionBatch().getM_Locator().getM_Warehouse_ID(), getM_ProductionBatch().getM_Locator_ID(),
				getM_Product_ID(), 0, 0, Env.ZERO, reservedQty,orderedQty , get_TrxName());
		return true;
	}
}
