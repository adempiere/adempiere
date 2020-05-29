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
 * Copyright (C) 2003-2010 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): victor.perez@e-evolution.com http://www.e-evolution.com    *
 *****************************************************************************/
package org.eevolution.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.compiere.model.I_M_Product;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MCostElement;
import org.compiere.model.MCostType;
import org.compiere.model.MWarehouse;
import org.compiere.model.Query;
import org.compiere.util.DB;

/**
 * Regenerate Cost Detail
 * 
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 */
public class ValuationEffectiveDate extends ValuationEffectiveDateAbstract {

	private List<MAcctSchema> acctSchemas = new ArrayList<MAcctSchema>();
	private List<MCostType> costTypes = new ArrayList<MCostType>();
	private List<MCostElement> costElements = new ArrayList<MCostElement>();
	private List<MWarehouse> warehouses = new ArrayList<MWarehouse>();
	private int[] products = {};

	private StringBuffer whereClause1 = null;
	private StringBuffer whereClause2 = null;
	PreparedStatement pstmt = null;
	private int batchSize = 1000;
	private int count = 0;

	/**
	 * Prepare - e.g., get Parameters.
	 */
	protected void prepare() {
		super.prepare();
	} // prepare

	/**
	 * execute the Valuation Effective Date
	 */
	protected String doIt() throws Exception {
		setup();
		for (MAcctSchema acctSchema : acctSchemas)
			for (MWarehouse warehouse : warehouses)
				for (MCostType costType : costTypes)
					for (MCostElement costElement : costElements)
						for (int productId : products)
							generateInventoryValue(
									productId ,
									acctSchema.getC_AcctSchema_ID(),
									acctSchema.getC_Currency_ID(),
									costType.getM_CostType_ID(),
									costElement.getM_CostElement_ID(),
									warehouse.getM_Warehouse_ID()
							);

		pstmt.executeBatch();
		commitEx();
		DB.close(pstmt);
		updatePricePO();
		for (MAcctSchema acctSchema: acctSchemas) {
			int no = 0;
			String whereSeq = " (SELECT MAX(SeqNo) FROM M_CostDetail " + 
					" WHERE IsReversal='N' AND M_Product_ID=iv.M_Product_ID AND iv.M_CostType_ID=cd.M_CostType_ID AND iv.C_AcctSchema_ID=cd.C_AcctSchema_ID AND iv.M_CostElement_ID=cd.M_Costelement_ID AND DateAcct <=?))";
			if (acctSchema.getCostingLevel().equals(MAcctSchema.COSTINGLEVEL_Client)) {
				ArrayList<Object> params = new ArrayList<>();
				params.add(getDateValue());
				params.add(getDateValue());
				params.add(getDateValue());
				params.add(getAD_PInstance_ID());
				StringBuffer update1 = new StringBuffer( "UPDATE T_InventoryValue iv ")
						.append(" set costamt = (SELECT COALESCE(CurrentCostPrice,0) FROM RV_M_Transaction_Costing cd WHERE iv.M_Product_ID=cd.M_Product_ID AND cd.SeqNo= ")
						.append(whereSeq);
				update1.append(", costamtll = (SELECT COALESCE(CurrentCostPriceLL) FROM RV_M_Transaction_Costing cd WHERE iv.M_Product_ID=cd.M_Product_ID AND cd.SeqNo= ")
				.append(whereSeq);
				//update1.append(" ,cumulatedamt = (select endingqtybalance from rv_m_transaction_costing cd where iv.m_Product_ID=cd.m_Product_ID and cd.seqno= ")
				//.append(whereSeq);
				update1.append(", DateValue = ? WHERE iv.AD_PInstance_ID=?");
				no = DB.executeUpdateEx(update1.toString(), params.toArray(), get_TrxName());		
				int i = no;
				commitEx();
				String update2 = "UPDATE T_InventoryValue set CumulatedAmt = (CostAmt +CostAmtLL)*QtyonHand WHERE AD_PInstance_ID=?";
				DB.executeUpdate(update2, getAD_PInstance_ID(), get_TrxName());
			}
			if (acctSchema.getCostingLevel().equals(MAcctSchema.COSTINGLEVEL_Warehouse)) {
				no = DB.executeUpdate("UPDATE T_InventoryValue SET Cost = CASE WHEN QtyOnHand <> 0 THEN (CostAmt + CostAmtLL) / QtyOnHand ELSE  0 END  ,  CumulatedAmt = CASE WHEN QtyOnHand <> 0  THEN  CostAmt + CostAmtLL ELSE 0 END ,  DateValue = "
						+ DB.TO_DATE(getDateValue()) + " WHERE AD_PInstance_ID=?",
				getAD_PInstance_ID(), get_TrxName());
				int i=no;
			}
			
		}

		return "@Ok@ " + count;

	}

	/**
	 * Update Price PO
	 */
	private void updatePricePO() {
		//  Update Prices
		StringBuffer update = new StringBuffer("UPDATE T_InventoryValue iv "
			+ "SET PricePO = "
				+ "(SELECT MAX(currencyConvert (po.PricePO,po.C_Currency_ID,iv.C_Currency_ID,iv.DateValue,null, po.AD_Client_ID,po.AD_Org_ID))"
				+ " FROM M_Product_PO po WHERE po.M_Product_ID=iv.M_Product_ID"
				+ " AND po.IsCurrentVendor='Y') ");
			if(getPriceListVersionId() != 0) {
				update.append(", PriceList = "
				+ "(SELECT currencyConvert(pp.PriceList,pl.C_Currency_ID,iv.C_Currency_ID,iv.DateValue,null, pl.AD_Client_ID,pl.AD_Org_ID)"
				+ " FROM M_PriceList pl, M_PriceList_Version plv, M_ProductPrice pp"
				+ " WHERE pp.M_Product_ID=iv.M_Product_ID AND pp.M_PriceList_Version_ID=iv.M_PriceList_Version_ID"
				+ " AND pp.M_PriceList_Version_ID=plv.M_PriceList_Version_ID"
				+ " AND plv.M_PriceList_ID=pl.M_PriceList_ID), "
			+ "PriceStd = "
				+ "(SELECT currencyConvert(pp.PriceStd,pl.C_Currency_ID,iv.C_Currency_ID,iv.DateValue,null, pl.AD_Client_ID,pl.AD_Org_ID)"
				+ " FROM M_PriceList pl, M_PriceList_Version plv, M_ProductPrice pp"
				+ " WHERE pp.M_Product_ID=iv.M_Product_ID AND pp.M_PriceList_Version_ID=iv.M_PriceList_Version_ID"
				+ " AND pp.M_PriceList_Version_ID=plv.M_PriceList_Version_ID"
				+ " AND plv.M_PriceList_ID=pl.M_PriceList_ID), "
			+ "PriceLimit = "
				+ "(SELECT currencyConvert(pp.PriceLimit,pl.C_Currency_ID,iv.C_Currency_ID,iv.DateValue,null, pl.AD_Client_ID,pl.AD_Org_ID)"
				+ " FROM M_PriceList pl, M_PriceList_Version plv, M_ProductPrice pp"
				+ " WHERE pp.M_Product_ID=iv.M_Product_ID AND pp.M_PriceList_Version_ID=iv.M_PriceList_Version_ID"
				+ " AND pp.M_PriceList_Version_ID=plv.M_PriceList_Version_ID"
				+ " AND plv.M_PriceList_ID=pl.M_PriceList_ID)");
			}
			//	Add general where clause
			update.append(" WHERE iv.AD_PInstance_ID=").append(getAD_PInstance_ID());
		//	Update
		DB.executeUpdateEx (update.toString(), get_TrxName());
	}
	
	/**
	 * Setup the collections
	 */
	private void setup() {

		if (getAcctSchemaId() > 0)
			acctSchemas.add(MAcctSchema.get(getCtx(), getAcctSchemaId(), get_TrxName()));
		else
			acctSchemas = Arrays.asList(MAcctSchema.getClientAcctSchema(getCtx() , getAD_Client_ID()));
		
		if (getCostTypeId() > 0)
			costTypes.add(new MCostType(getCtx(), getCostTypeId(), get_TrxName()));
		else
			costTypes = MCostType.get(getCtx(), get_TrxName());

		if (getCostElementId() > 0)
			costElements.add(MCostElement.get(getCtx(), getCostElementId()));
		else
			costElements = MCostElement.getCostElement(getCtx(), get_TrxName());

		if (getWarehouseId() > 0)
			warehouses.add(MWarehouse.get(getCtx(), getWarehouseId(), get_TrxName()));
		else {
			warehouses = new Query(getCtx(), MWarehouse.Table_Name, "", get_TrxName()).setClient_ID().list();
		}
		
		if(getProductId() == 0)
			products = new Query (getCtx() , I_M_Product.Table_Name , "" , get_TrxName()).setClient_ID().getIDs();
		else
			products = new int[] {getProductId()};
		
		setWhere();
		
		StringBuffer insert = new StringBuffer();
		insert.append("INSERT INTO T_InventoryValue ")
				.append("(AD_PInstance_ID,DateValue,AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,M_CostElement_ID,M_CostType_ID,M_Warehouse_ID,")
				.append("M_Product_ID,M_Product_Category_ID,M_AttributeSetInstance_ID,Group1,Group2,QtyOnHand,CostAmt,CostAmtLL, M_PriceList_Version_ID, C_Currency_ID) ")
				.append("SELECT ")
				.append(getAD_PInstance_ID())
				.append(",")
				.append("tc.DateAcct")
				.append(",")
				.append("p.AD_Client_ID,p.AD_Org_ID, tc.C_AcctSchema_ID ,tc.M_CostElement_ID,tc.M_CostType_ID, tc.M_Warehouse_ID,p.M_Product_ID,")
				.append("p.M_Product_Category_ID,tc.M_AttributeSetInstance_ID,p.Group1,p.Group2,  tc.qty + tc.cumulatedqty AS QtyOnHand,")
				.append(" CASE WHEN tc.Qty < 0 OR (tc.qty = 0 AND tc.cumulatedqty < 0) THEN ((tc.costAmt + tc.costadjustment) * -1) + tc.CumulatedAmt ELSE ((tc.costAmt + tc.costadjustment) * 1) + tc.CumulatedAmt END  AS CostAmt,")
				.append(" CASE WHEN tc.Qty < 0 OR (tc.qty = 0 AND tc.cumulatedqty < 0) THEN ((tc.costAmtLL + tc.costadjustmentLL) * -1)  + tc.CumulatedAmtLL ELSE ((tc.costAmtLL + tc.costadjustmentLL) * 1) + tc.CumulatedAmtLL END AS CostAmtLL, ")
				.append(getPriceListVersionId() != 0? getPriceListVersionId(): "null").append(", ? ")
				.append(" FROM M_Product p ")
				.append(" INNER JOIN M_CostDetail tc ON (p.M_Product_ID=tc.M_Product_ID) ");
		insert.append(whereClause1).append(whereClause2);
		pstmt = DB.prepareStatement(insert.toString(),
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE, get_TrxName());
	}

	public void setWhere() {

		whereClause1 = new StringBuffer("WHERE tc.IsReversal='N' ");
		whereClause2 = new StringBuffer(
				" AND tc.SeqNo = (SELECT MAX(SeqNo) FROM M_CostDetail tc1")
				.append(" WHERE tc1.IsReversal='N' AND tc1.M_Product_ID=tc.M_Product_ID AND tc1.M_Warehouse_ID = tc.M_Warehouse_ID ");

		whereClause1.append("AND tc.DateAcct<= ").append(DB.TO_DATE(getDateValue()));
		whereClause2.append("AND tc1.DateAcct<= ").append(DB.TO_DATE(getDateValue()));

		whereClause1.append(" AND p.M_Product_ID=? ");

		whereClause1.append(" AND tc.C_AcctSchema_ID=? ");
		whereClause2.append(" AND tc1.C_AcctSchema_ID = tc.C_AcctSchema_ID");

		whereClause1.append(" AND tc.M_CostType_ID =?  ");
		whereClause2.append(" AND tc1.M_CostType_ID=tc.M_CostType_ID ");

		whereClause1.append(" AND tc.M_CostElement_ID=? ");
		whereClause2.append(" AND tc1.M_CostElement_ID = tc.M_CostElement_ID");
		
		whereClause1.append(" AND tc.M_Warehouse_ID=? ");
		whereClause2.append(")");
		//	
		if (getProductCategoryId() > 0) {
			whereClause1.append(" AND p.M_Product_Category_ID =? ");
		}

	}
	
	/**
	 * Generate the Inventory Valuation
	 * 
	 * @throws SQLException
	 */
	private void generateInventoryValue(
			int productId ,
			int accountSchemaId,
			int currencyId, 
			int costTypeId,
			int costElementId,
			int warehouseId) throws SQLException {
			
			int index = 1;
			pstmt.setInt(index++, currencyId);
			pstmt.setInt(index++, productId);
			pstmt.setInt(index++, accountSchemaId);
			pstmt.setInt(index++, costTypeId);
			pstmt.setInt(index++, costElementId);
			pstmt.setInt(index++, warehouseId);
			
			if(getProductCategoryId() > 0)
				pstmt.setInt(index++, getProductCategoryId());
			
			pstmt.addBatch();
			
			if(++count % batchSize == 0) {
				pstmt.executeBatch();
				commitEx();
		    }
	}
}
