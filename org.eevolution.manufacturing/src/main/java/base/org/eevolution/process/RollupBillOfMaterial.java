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

package org.eevolution.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

import org.adempiere.engine.CostDimension;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MCost;
import org.compiere.model.MCostElement;
import org.compiere.model.MProduct;
import org.compiere.model.MUOMConversion;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
import org.compiere.wf.MWorkflow;
import org.eevolution.model.I_PP_Product_Planning;
import org.eevolution.model.MPPMRP;
import org.eevolution.model.MPPProductBOM;
import org.eevolution.model.MPPProductPlanning;

/**
 * Roll-UP Bill of Material 
 *	
 * @author victor.perez@e-evolution.com, e-Evolution, S.C.
 * 
 * @author Teo Sarca, www.arhipac.ro
 */
public class RollupBillOfMaterial extends RollupBillOfMaterialAbstract
{
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		super.prepare();
	}	//	prepare

	/**
	 * 	Generate Calculate Cost
	 *	@return info
	 *	@throws Exception
	 */   
	protected String doIt() throws Exception                
	{
		resetCostsLowLevel();
		//
		int maxLowLevel = MPPMRP.getMaxLowLevel(getCtx(), get_TrxName());
		// Cost Roll-up for all levels
		for (int lowLevel = maxLowLevel; lowLevel >= 0; lowLevel--)
		{
			Arrays.stream(getProductIds(lowLevel))
				.filter(productId -> productId > 0)
				.forEach(productId -> {
				MProduct product = MProduct.get(getCtx() , productId);
				I_PP_Product_Planning productPlanning = MPPProductPlanning.find(getCtx(), getOrganizationId(),
						getWarehouseId(), // M_Warehouse_ID
						getResourcePlantId(), // S_Resource_ID
						product.getM_Product_ID(),
						get_TrxName());
				int bomId = 0;
				if (productPlanning != null)
					bomId = productPlanning.getPP_Product_BOM_ID();
				else
					createNotice(product, Msg.parseTranslation(getCtx() , "@NotFound@ @PP_Product_Planning_ID@"));

				if (bomId <= 0)
					bomId = MPPProductBOM.getBOMSearchKey(product);

				MPPProductBOM bom = MPPProductBOM.get(getCtx(), bomId);
				if (bom == null)
					createNotice(product, Msg.parseTranslation(getCtx() , "@NotFound@ @PP_Product_BOM_ID@"));

				Trx.run(new TrxRunnable() {
					MProduct product;
					MPPProductBOM bom;
					public TrxRunnable setParameters(MProduct product , MPPProductBOM bom) {
						this.product = product;
						this.bom = bom;
						return this;
					}

					public void run(String trxName) {
						rollup(product, bom , trxName);
					}
				}.setParameters(product , bom));
			}); // Products List
		} // for Low Lever
		return "@OK@";
	}
	
	protected void rollup(MProduct product, MPPProductBOM bom, String trxName)
	{
		getCostElements(trxName).stream()
				.filter(costElement -> costElement != null)
				.forEach(costElement -> {
					getCosts(product, costElement.getM_CostElement_ID() , trxName).stream()
							.filter(cost -> cost != null )
							.forEach( cost -> {
						log.info("Calculate Lower Cost for : "+ bom);
						BigDecimal price = getCurrentCostPriceLowLevel(bom, costElement, trxName);
						log.info(" Product :" + product.getName()  + " Element Cost : " + costElement.getName() + " Cost Low Level : " + price);
						cost.setCurrentCostPriceLL(price);
						updateCoProductCosts(bom, cost ,trxName);
						cost.saveEx();
					}); // costs
		}); //Elements
	}

	/**
	 * Update costs for co-products on BOM Lines from given BOM
	 * @param bom product's BOM
	 * @param baseDimension base product cost (BOM Cost)
	 */
	private void updateCoProductCosts(MPPProductBOM bom, MCost baseDimension, String trxName)
	{
		// Skip if not BOM found
		if (bom == null)
			return;
		
		AtomicReference<BigDecimal>  costPriceTotal = new AtomicReference<>(Env.ZERO);
		//for (MPPProductBOMLine bomLine : bom.getLines())
		//{
		Arrays.stream(bom.getLines())
			.filter(bomLine -> bomLine != null && bomLine.isCoProduct())
			.forEach(bomLine -> {
			final BigDecimal costPrice = baseDimension.getCurrentCostPriceLL().multiply(bomLine.getCostAllocationPerc(true));
			// Get/Create Cost
            MCost dimension = MCost.getDimension(
                    (MProduct)bomLine.getM_Product(),
                    baseDimension.getC_AcctSchema_ID(),
                    baseDimension.getAD_Org_ID(),
                    baseDimension.getM_Warehouse_ID(),
                    0 , // ASI
                    baseDimension.getM_CostType_ID(),
                    baseDimension.getM_CostElement_ID());

			if (dimension == null)
			{
                dimension = new MCost (baseDimension.getCtx(), 0, trxName);
                dimension.setAD_Org_ID(baseDimension.getAD_Org_ID());
                dimension.setM_Product_ID(bomLine.getM_Product_ID());
				dimension.setM_Warehouse_ID(baseDimension.getM_Warehouse_ID());
                dimension.setM_CostType_ID(baseDimension.getM_CostType_ID());
                dimension.setC_AcctSchema_ID(baseDimension.getC_AcctSchema_ID());
                dimension.setM_CostElement_ID(baseDimension.getM_CostElement_ID());
                dimension.setM_AttributeSetInstance_ID(0);
			}
            dimension.setCurrentCostPriceLL(costPrice);
            dimension.saveEx();
			costPriceTotal.updateAndGet(costAmt -> costAmt.add(costPrice));
		});
		// Update Base Cost:
		if(costPriceTotal.get().signum() != 0)
			baseDimension.setCurrentCostPriceLL(costPriceTotal.get());
	}

	/**
	 * Get the sum Current Cost Price Level Low for this Cost Element
	 * @param bom MPPProductBOM
	 * @param costElement MCostElement
	 * @return Cost Price Lower Level
	 */
	private BigDecimal getCurrentCostPriceLowLevel(MPPProductBOM bom, MCostElement costElement,String trxName)
	{
		log.info("Element: "+ costElement);
		AtomicReference<BigDecimal> costPriceLowLevel =  new AtomicReference<>(Env.ZERO);
		if(bom == null)
			return costPriceLowLevel.get();

		Arrays.stream(bom.getLines())
			.filter(bomLine -> bomLine != null && !bomLine.isCoProduct())
			.forEach(bomline -> {
				MProduct component = MProduct.get(getCtx(), bomline.getM_Product_ID());
				// get the rate for this resource
				getCosts(component, costElement.get_ID() , trxName)
					.stream()
					.filter(cost -> cost != null)
					.forEach(cost -> {
						Boolean includingScrapQty = true;
						BigDecimal qty = bomline.getQty(includingScrapQty);
						// By Products
						if (bomline.isByProduct())
							cost.setCurrentCostPriceLL(Env.ZERO);

						BigDecimal costPrice = cost.getCurrentCostPrice().add(cost.getCurrentCostPriceLL());
						if (bomline.getM_Product().getC_UOM_ID() != bomline.getC_UOM_ID())
						{
							BigDecimal rate = MUOMConversion.getProductRateFrom(getCtx(), component.getM_Product_ID(), bomline.getC_UOM_ID());
							if (rate == null)
								costPrice = costPrice.multiply(BigDecimal.ONE);
							else
								costPrice = costPrice.multiply(rate);
						}

						if (bomline.isPacking()) {
							MAcctSchema acctSchema = MAcctSchema.get(getCtx(), cost.getC_AcctSchema_ID());
							int workflowId = 0;
							MProduct product = MProduct.get(getCtx(), bom.getM_Product_ID());
							MPPProductPlanning productPlanning = null;
							if (workflowId <= 0)
								workflowId = MWorkflow.getWorkflowSearchKey(product);
							if (workflowId <= 0) {
								productPlanning = MPPProductPlanning.find(getCtx(), getOrganizationId(), getWarehouseId(), getResourcePlantId(), product.get_ID(), get_TrxName());
								if (productPlanning != null)
									workflowId = productPlanning.getAD_Workflow_ID();
								else
									createNotice(product, "@NotFound@ @PP_Product_Planning_ID@");
							}
							if (workflowId <= 0)
								createNotice(product, "@NotFound@ @AD_Workflow_ID@");

							BigDecimal qtyBatchSize = DB.getSQLValueBD(trxName, "SELECT QtyBatchSize FROM AD_Workflow WHERE AD_Workflow_ID=?",workflowId);
							if (qtyBatchSize.signum() != 0)
								qty = qty.divide(qtyBatchSize , acctSchema.getCostingPrecision() , BigDecimal.ROUND_HALF_UP);
						}

						BigDecimal componentCost = costPrice.multiply(qty);
						costPriceLowLevel.updateAndGet(costAmt -> costAmt.add(componentCost));
						log.info("CostElement: "+costElement.getName()
								+ ", Component: "+component.getValue()
								+ ", CostPrice: "+costPrice
								+ ", Qty: " + qty
								+ ", Cost: " + componentCost
								+ " => Total Cost Element: " +  costPriceLowLevel.get());
					}); //cost
		}); // BOM line
		return costPriceLowLevel.get();
	}

	private List<MCost> getCosts(MProduct product, int costElementId, String trxName)
	{
		MAcctSchema acctSchema = MAcctSchema.get(getCtx(), getAccountingSchemaId());
		CostDimension costDimension = new CostDimension(product, acctSchema, getCostTypeId(), getOrganizationId(), getWarehouseId(), 0, costElementId);
		return costDimension.toQuery(MCost.class, trxName).list();
	}

	private int[] getProductIds(int lowLevel)
	{
		List<Object> params = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer("AD_Client_ID=?").append(" AND ").append(MProduct.COLUMNNAME_LowLevel).append("=?");
		params.add(getAD_Client_ID());
		params.add(lowLevel);
		whereClause.append(" AND ").append(MProduct.COLUMNNAME_IsBOM).append("=?");
		params.add(true);

		if (getProductId() > 0)
		{  
			whereClause.append(" AND ").append(MProduct.COLUMNNAME_M_Product_ID).append("=?");
			params.add(getProductId());
		}		
		else if (getProductCategoryId() > 0)
		{
			whereClause.append(" AND ").append(MProduct.COLUMNNAME_M_Product_Category_ID).append("=?");
			params.add(getProductCategoryId());
		}
		if (getProductId() <= 0 && getProductType() != null)
		{
			whereClause.append(" AND ").append(MProduct.COLUMNNAME_ProductType).append("=?");
			params.add(getProductType());
		}

		return new Query(getCtx(),MProduct.Table_Name, whereClause.toString(), get_TrxName())
		.setParameters(params)
		.getIDs();
	}
	
	/**
	 * Reset Low Level Cost for products with LowLevel=0 (items)
	 */
	private void resetCostsLowLevel()
	{
		List<Object> params = new ArrayList<Object>();
		StringBuffer productWhereClause = new StringBuffer();
		productWhereClause.append("AD_Client_ID=? AND "+MProduct.COLUMNNAME_LowLevel+"=?");
		params.add(getAD_Client_ID());
		params.add(0);
		if (getProductId() > 0)
		{  
			productWhereClause.append(" AND ").append(MProduct.COLUMNNAME_M_Product_ID).append("=?");
			params.add(getProductId());
		}		
		else if (getProductCategoryId() > 0)
		{
			productWhereClause.append(" AND ").append(MProduct.COLUMNNAME_M_Product_Category_ID).append("=?");
			params.add(getProductCategoryId());
		}
		//
		final String sql = "UPDATE M_Cost c SET "+MCost.COLUMNNAME_CurrentCostPriceLL+"=0"
		+" WHERE EXISTS (SELECT 1 FROM M_Product p WHERE p.M_Product_ID=c.M_Product_ID"
						+" AND "+productWhereClause+")";
		int no = DB.executeUpdateEx(sql, params.toArray(), null);
		log.info("Updated #"+no);
	}
	
	private List<MCostElement> costElements = null;
	private List<MCostElement> getCostElements(String trxName)
	{
		if (costElements == null)
			costElements = MCostElement.getCostElement(getCtx(),trxName);

		return costElements;
	}

	/**
	 * Create Cost Rollup Notice
	 * @param product
	 * @param msg
	 */
	private void createNotice(MProduct product, String msg)
	{
		String productValue = product != null ? product.getValue() : "-";
		addLog("WARNING: Product "+productValue+": "+msg);
	}
}
