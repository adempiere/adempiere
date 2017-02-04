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
		super.prepare();;
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
									acctSchema.getC_AcctSchema_ID() ,
									costType.getM_CostType_ID(),
									costElement.getM_CostElement_ID(),
									warehouse.getM_Warehouse_ID()
							);

		pstmt.executeBatch();
		commitEx();
		DB.close(pstmt);
		
		DB.executeUpdate("UPDATE T_InventoryValue SET Cost = CASE WHEN QtyOnHand <> 0 THEN (CostAmt + CostAmtLL) / QtyOnHand ELSE  0 END  ,  CumulatedAmt = CASE WHEN QtyOnHand <> 0  THEN  CostAmt + CostAmtLL ELSE 0 END ,  DateValue = "
						+ DB.TO_DATE(getValuationDate()) + " WHERE AD_PInstance_ID=?",
				getAD_PInstance_ID(), get_TrxName());

		return "@Ok@ " + count;

	}

	/**
	 * Setup the collections
	 */
	private void setup() {

		if (getAccountingSchemaId() > 0)
			acctSchemas.add(MAcctSchema.get(getCtx(), getAccountingSchemaId(), get_TrxName()));
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
				.append("M_Product_ID,M_Product_Category_ID,M_AttributeSetInstance_ID,Group1,Group2,QtyOnHand,CostAmt,CostAmtLL) ")
				.append("SELECT ")
				.append(getAD_PInstance_ID())
				.append(",")
				.append("tc.DateAcct")
				.append(",")
				.append("p.AD_Client_ID,p.AD_Org_ID, tc.C_AcctSchema_ID ,tc.M_CostElement_ID,tc.M_CostType_ID, tc.M_Warehouse_ID,p.M_Product_ID,")
				.append("p.M_Product_Category_ID,tc.M_AttributeSetInstance_ID,p.Group1,p.Group2,  tc.qty + tc.cumulatedqty AS QtyOnHand,")
				.append(" CASE WHEN tc.Qty < 0 OR (tc.qty = 0 AND tc.cumulatedqty < 0) THEN ((tc.costAmt + tc.costadjustment) * -1) + tc.CumulatedAmt ELSE ((tc.costAmt + tc.costadjustment) * 1) + tc.CumulatedAmt END  AS CostAmt,")
				.append(" CASE WHEN tc.Qty < 0 OR (tc.qty = 0 AND tc.cumulatedqty < 0) THEN ((tc.costAmtLL + tc.costadjustmentLL) * -1)  + tc.CumulatedAmtLL ELSE ((tc.costAmtLL + tc.costadjustmentLL) * 1) + tc.CumulatedAmtLL END AS CostAmtLL")
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

		whereClause1.append("AND tc.DateAcct<= ").append(
				DB.TO_DATE(getValuationDate()));
		whereClause2.append("AND tc1.DateAcct<= ").append(
				DB.TO_DATE(getValuationDate()));

		whereClause1.append(" AND p.M_Product_ID=? ");

		
		if (getProductCategoryId() > 0)
			whereClause1.append(" AND p.M_Product_Category_ID =? ");

		whereClause1.append(" AND tc.C_AcctSchema_ID=? ");
		whereClause2.append(" AND tc1.C_AcctSchema_ID = tc.C_AcctSchema_ID");

		whereClause1.append(" AND tc.M_CostType_ID =?  ");
		whereClause2.append(" AND tc1.M_CostType_ID=tc.M_CostType_ID ");

		whereClause1.append(" AND tc.M_CostElement_ID=? ");
		whereClause2.append(" AND tc1.M_CostElement_ID = tc.M_CostElement_ID");
		
		whereClause1.append(" AND tc.M_Warehouse_ID=? ");
		whereClause2.append(")");
	}
	
	/**
	 * Generate the Inventory Valuation
	 * 
	 * @throws SQLException
	 */
	private void generateInventoryValue(
			int productId ,
			int accountSchemaId ,
			int costTypeId,
			int costElementId,
			int warehouseId) throws SQLException {
			
			pstmt.setInt(1, productId);
			pstmt.setInt(2, accountSchemaId);
			pstmt.setInt(3, costTypeId);
			pstmt.setInt(4, costElementId);
			pstmt.setInt(5, warehouseId);
			
			if(getProductCategoryId() > 0)
				pstmt.setInt(6, getProductCategoryId());
			
			pstmt.addBatch();
			
			if(++count % batchSize == 0) {
				pstmt.executeBatch();
				commitEx();
		    }
	}
}
