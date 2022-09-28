/**
 * Copyright (C) 2003-2020, e-Evolution Consultants S.A. , http://www.e-evolution.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 * Created by victor.perez@e-evolution.com , www.e-evolution.com
 */

package org.eevolution.manufacturing.process;

import org.adempiere.core.domains.models.I_C_OrderLine;
import org.adempiere.core.domains.models.I_DD_OrderLine;
import org.adempiere.core.domains.models.I_M_Storage;
import org.adempiere.core.domains.models.I_PP_Order;
import org.adempiere.core.domains.models.I_PP_Order_BOMLine;
import org.compiere.model.MOrderLine;
import org.compiere.model.MOrg;
import org.compiere.model.MProduct;
import org.compiere.model.MStorage;
import org.compiere.model.MWarehouse;
import org.compiere.model.Query;
import org.compiere.util.Trx;
import org.eevolution.distribution.model.MDDOrderLine;
import org.eevolution.manufacturing.model.MPPOrder;
import org.eevolution.manufacturing.model.MPPOrderBOMLine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/** Generated Process for (Validate Ordered and Reserved Quantity)
 *  @author victor.perez@e-evolution.com , www.e-evolution.com
 */
public class ValidateOrderedAndReservedQuantity extends ValidateOrderedAndReservedQuantityAbstract
{
	List<Integer> organizationList = new ArrayList<>();
	List<Integer> warehouseList = new ArrayList<>();
	List<Integer> productList = new ArrayList<>();

	@Override
	protected void prepare()
	{
		super.prepare();
	}

	@Override
	protected String doIt() throws Exception
	{
		if (getOrgId() > 0)
			organizationList.add(getOrgId());
		else
			organizationList.addAll(getOrganizations(getAD_Client_ID()));

		if (getWarehouseId() > 0)
			warehouseList.add(getWarehouseId());
		else {
			organizationList
					.forEach(organizationId -> Arrays.stream(MWarehouse.getForOrg(getCtx(), organizationId))
					.forEach(warehouse -> warehouseList.add(warehouse.getM_Warehouse_ID())));
		}

		if (getProductId() > 0)
			productList.add(getProductId());
		else {
			warehouseList.forEach(warehouseId -> {
				StringBuilder whereClause = new StringBuilder("(AD_Org_ID=0 OR AD_Org_ID=" + getOrgId()).append(")");
				if (getProductCategoryId() > 0)
					whereClause.append(" AND M_Product_Category_ID=").append(getProductCategoryId());
				if (getProductGroupId() > 0 )
					whereClause.append(" AND M_Product_Group_ID=").append(getProductGroupId());
				if (getProductClassId() > 0 )
					whereClause.append(" AND M_Product_Class_ID=").append(getProductClassId());
				if (getProductClassificationId() > 0 )
					whereClause.append(" AND M_Product_Classification_ID=").append(getProductClassificationId());
				productList.addAll(
				Arrays.stream((Objects.requireNonNull(
						MProduct.getAllIDs(MProduct.Table_Name, whereClause.toString(), get_TrxName()
						)
				)
				)).boxed().collect(Collectors.toList()));
			});
		}

		warehouseList.forEach( warehouseId -> {
			MWarehouse warehouse = MWarehouse.get(getCtx(),warehouseId);
			addLog(" @M_Warehouse_ID@ : " +  " " + warehouse.getValue() + " - " + warehouse.getName());
			productList.forEach(productId -> {
				MProduct product = MProduct.get(getCtx(),productId);
				addLog(" -> @M_Product_ID@ : " + product.getValue() + " - " + product.getName());
				Trx.run(trxName -> {
					//Clean Inventory Storage
					List<MStorage> storageList = getStorage(warehouseId, productId, trxName);
					storageList.stream()
							.filter(
									storage -> storage.getQtyOrdered().signum() != 0 ||
									storage.getQtyReserved().signum() != 0
							)
							.forEach(storage -> {
								if (storage.getQtyOrdered().signum() != 0 )
									storage.setQtyOrdered(BigDecimal.ZERO);
								if (storage.getQtyReserved().signum() != 0)
									storage.setQtyReserved(BigDecimal.ZERO);
								storage.saveEx();
							});
					//Validate Ordered Quantity
					checkOrderStock(warehouseId,productId, trxName);
					checkDistributionOrderStock(warehouseId,productId, trxName);
					checkManufacturingOrderStock(warehouseId,productId, trxName);
				});
			});
		});
		return "";
	}

	private List<Integer> getOrganizations(int clientId) {
		return new Query(getCtx(),MOrg.Table_Name, "AD_Client_ID=?", get_TrxName())
				.setParameters(clientId)
				.getIDsAsList();
	}

	private List<MStorage> getStorage(int warehouseId ,int productId, String trxName) {
		StringBuilder whereClause = new StringBuilder();
		whereClause.append(I_M_Storage.COLUMNNAME_M_Product_ID)
				.append("=? AND ")
				.append(" ( ")
				.append(I_M_Storage.COLUMNNAME_QtyReserved)
				.append(" <> 0 OR ")
				.append(I_M_Storage.COLUMNNAME_QtyOrdered).append(" <> 0 ) AND ");
		whereClause.append("EXISTS (SELECT 1 FROM M_Locator l WHERE l.M_Locator_ID = M_Storage.M_Locator_ID AND l.M_Warehouse_ID = ? )");
		List<Object> parameters = new ArrayList<>();
		parameters.add(productId);
		parameters.add(warehouseId);
		List<MStorage> storageList = new Query(getCtx(), MStorage.Table_Name, whereClause.toString(), trxName)
				.setClient_ID()
				.setParameters(parameters)
				.list();
		return storageList;
	}

	private void checkManufacturingOrderStock(Integer warehouseId, Integer productId, String trxName) {
		List<Object> parameters = new ArrayList<>();
		StringBuilder whereClause = new StringBuilder();
		whereClause.append(I_PP_Order.COLUMNNAME_M_Warehouse_ID).append("=? AND ");
		whereClause.append(I_PP_Order.COLUMNNAME_M_Product_ID).append("=? AND ");
		whereClause.append("DocStatus IN ('IP','CO')");
		parameters.add(warehouseId);
		parameters.add(productId);
		List<MPPOrder> manufacturingOrderList =  new Query(getCtx(), MPPOrder.Table_Name, whereClause.toString(), trxName)
				.setClient_ID()
				.setParameters(parameters)
				.list();

		manufacturingOrderList.forEach(order -> {
			order.setQtyReserved(BigDecimal.ZERO);
			order.orderedStock();
			order.saveEx();
			String message = " --> @QtyOrdered@ : " + order.getQtyReserved() + " - " + order.getDocumentInfo();
			addLog(message);
		});

		parameters = new ArrayList<>();
		whereClause = new StringBuilder();
		whereClause.append(I_PP_Order_BOMLine.COLUMNNAME_M_Warehouse_ID).append("=? AND ");
		whereClause.append(I_PP_Order_BOMLine.COLUMNNAME_M_Product_ID).append("=? AND ");
		whereClause.append("EXISTS (SELECT 1 FROM PP_Order o WHERE o.PP_Order_ID = PP_Order_BOMLine.PP_Order_ID AND o.DocStatus IN ('IP','CO'))");
		parameters.add(warehouseId);
		parameters.add(productId);
		List<MPPOrderBOMLine> manufacturingOrderLineList =  new Query(getCtx(), MPPOrderBOMLine.Table_Name, whereClause.toString(), trxName)
				.setClient_ID()
				.setParameters(parameters)
				.list();
		manufacturingOrderLineList.forEach(orderBOMLine  -> {
			orderBOMLine.setQtyReserved(BigDecimal.ZERO);
			orderBOMLine.reservedStock();
			orderBOMLine.saveEx();
			String message = " --> @QtyReserved@ : " + orderBOMLine.getQtyReserved() + " - " + orderBOMLine.getParent().getDocumentInfo();
			addLog(message);
		});
	}

	private void checkDistributionOrderStock(Integer warehouseId, Integer productId, String trxName) {
		List<Object> parameters = new ArrayList<>();
		StringBuilder whereClause = new StringBuilder();
		whereClause.append(" EXISTS (SELECT 1 FROM M_Locator l WHERE l.M_Locator_ID = DD_OrderLine.M_LocatorTo_ID AND l.M_Warehouse_ID=?) ").append(" AND ");
		whereClause.append(I_DD_OrderLine.COLUMNNAME_M_Product_ID).append("=? AND ");
		whereClause.append(I_DD_OrderLine.COLUMNNAME_QtyReserved).append(" <> 0 AND ");
		whereClause.append("EXISTS (SELECT 1 FROM DD_Order o WHERE o.DD_Order_ID = DD_OrderLine.DD_Order_ID AND o.DocStatus IN ('IP','CO'))");
		parameters.add(warehouseId);
		parameters.add(productId);
		List<MDDOrderLine> distributionOrderOrderedLines =  new Query(getCtx(), MDDOrderLine.Table_Name, whereClause.toString(), trxName)
				.setClient_ID()
				.setParameters(parameters)
				.list();

		distributionOrderOrderedLines.forEach(orderLine -> {
			orderLine.setQtyReserved(BigDecimal.ZERO);
			orderLine.orderedStock();
			orderLine.saveEx();
			String message = " --> @QtyOrdered@ : " + orderLine.getQtyReserved() + " - " + orderLine.getParent().getDocumentInfo();
			addLog(message);
		});
		parameters = new ArrayList<>();
		whereClause = new StringBuilder();
		whereClause.append(" EXISTS (SELECT 1 FROM M_Locator l WHERE l.M_Locator_ID = DD_OrderLine.M_Locator_ID AND l.M_Warehouse_ID=?) ").append(" AND ");
		whereClause.append(I_DD_OrderLine.COLUMNNAME_M_Product_ID).append("=? AND ");
		whereClause.append(I_DD_OrderLine.COLUMNNAME_QtyReserved).append(" <> 0 AND ");
		whereClause.append("EXISTS (SELECT 1 FROM DD_Order o WHERE o.DD_Order_ID = DD_OrderLine.DD_Order_ID AND o.DocStatus IN ('IP','CO'))");
		parameters.add(warehouseId);
		parameters.add(productId);
		List<MDDOrderLine> distributionOrderReservedLines =  new Query(getCtx(), MDDOrderLine.Table_Name, whereClause.toString(), trxName)
				.setClient_ID()
				.setParameters(parameters)
				.list();
		distributionOrderReservedLines.forEach(orderLine -> {
			orderLine.setQtyReserved(BigDecimal.ZERO);
			orderLine.reserveStock();
			orderLine.saveEx();
			String message = " --> @QtyReserved@ : " + orderLine.getQtyReserved() + " - " + orderLine.getParent().getDocumentInfo();
			addLog(message);
		});
	}

	private void checkOrderStock(Integer warehouseId, Integer productId, String trxName) {
		List<Object> parameters = new ArrayList<>();
		StringBuilder whereClause = new StringBuilder();
		whereClause.append(I_C_OrderLine.COLUMNNAME_M_Warehouse_ID).append("=? AND ");
		whereClause.append(I_C_OrderLine.COLUMNNAME_M_Product_ID).append("=? AND ");
		whereClause.append(I_C_OrderLine.COLUMNNAME_QtyReserved).append(" <> 0 AND ");
		whereClause.append("EXISTS (SELECT 1 FROM C_Order o WHERE o.C_Order_ID = C_OrderLine.C_Order_ID AND o.DocStatus IN ('IP','CO'))");
		parameters.add(warehouseId);
		parameters.add(productId);
		List<MOrderLine> orderLines =  new Query(getCtx(), MOrderLine.Table_Name, whereClause.toString(), trxName)
				.setClient_ID()
				.setParameters(parameters)
				.list();
		orderLines.forEach(orderLine -> {
			orderLine.setQtyReserved(BigDecimal.ZERO);
			orderLine.reserveStock();
			orderLine.saveEx();
			String message;
			if (orderLine.isSOTrx())
				message = " --> @QtyReserved@ : " + orderLine.getQtyReserved() + " - " + orderLine.getParent().getDocumentInfo();
			else
				message = " --> @QtyOrdered@ : " + orderLine.getQtyReserved() + " - " + orderLine.getParent().getDocumentInfo();
			addLog(message);
		});
	}
}