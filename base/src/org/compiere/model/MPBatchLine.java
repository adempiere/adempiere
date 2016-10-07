package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.util.Env;

public class MPBatchLine extends X_M_PBatch_Line
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8836637198723346713L;

	public MPBatchLine(Properties ctx, int M_PBatch_Line_ID, String trxName)
	{
		super(ctx, M_PBatch_Line_ID, trxName);
	}

	public MPBatchLine(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}
	
	public static MPBatchLine getbyProduct(int M_ProductionBatch_ID, int M_Product_ID, Properties ctx, String TrxName)
	{
		String whereClause = MProductionBatch.COLUMNNAME_M_Production_Batch_ID + "=? and " +
				MProductionBatch.COLUMNNAME_M_Product_ID + " =?";
		ArrayList<Object> params = new ArrayList<>();
		params.add(M_ProductionBatch_ID);
		params.add(M_Product_ID);
		MPBatchLine pbLine = new Query(Env.getCtx()	, MPBatchLine.Table_Name, whereClause, TrxName)
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
		if (!MStorage.add(getCtx(), getM_Production_Batch().getM_Locator().getM_Warehouse_ID(), getM_Production_Batch().getM_Locator_ID(),
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
		MStorage.add(getCtx(), getM_Production_Batch().getM_Locator().getM_Warehouse_ID(), getM_Production_Batch().getM_Locator_ID(),
				getM_Product_ID(), 0, 0, Env.ZERO, reservedQty,orderedQty , get_TrxName());
		return true;
	}

	

}
