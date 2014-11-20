/**
 * 
 */
package org.adempiere.engine;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_AD_WF_Node;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MClient;
import org.compiere.model.MCost;
import org.compiere.model.MCostDetail;
import org.compiere.model.MCostElement;
import org.compiere.model.MCostType;
import org.compiere.model.MProduct;
import org.compiere.model.MTransaction;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.eevolution.model.I_PP_Order_BOMLine;
import org.eevolution.model.MPPCostCollector;
import org.eevolution.model.MPPOrderCost;
import org.eevolution.model.RoutingService;
import org.eevolution.model.RoutingServiceFactory;

/**
 * @author anca_bradau
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 * 
 */
public class StandardCostingMethod extends AbstractCostingMethod implements
		ICostingMethod {

	public void setCostingMethod(MAcctSchema accountSchema, MTransaction transaction, IDocumentLine model,
                                 MCost dimension, BigDecimal costThisLevel,
                                 BigDecimal costLowLevel, Boolean isSalesTransaction) {
		this.accountSchema = accountSchema;
		this.transaction = transaction;
		this.dimension = dimension;
		this.costThisLevel = (costThisLevel == null ? Env.ZERO : costThisLevel);
		this.costLowLevel = (costLowLevel == null ? Env.ZERO : costLowLevel);
		this.isSalesTransaction = isSalesTransaction;
		this.model = transaction.getDocumentLine();
		this.costingLevel = MProduct.get(this.transaction.getCtx(), this.transaction.getM_Product_ID())
				.getCostingLevel(this.accountSchema, this.transaction.getAD_Org_ID());
		this.costDetail = MCostDetail.getByTransaction(this.model, this.transaction,
				this.accountSchema.getC_AcctSchema_ID(), this.dimension.getM_CostType_ID(),
				this.dimension.getM_CostElement_ID());
	}

	private void calculate() {
		if (model.getReversalLine_ID() > 0)
			return;

		if (transaction.getMovementType().endsWith("-")) {
			currentCostPrice = dimension.getCurrentCostPrice();
			currentCostPriceLowerLevel = dimension.getCurrentCostPriceLL();
			amount = model.getMovementQty().multiply(currentCostPrice);
			amountLowerLevel = model.getMovementQty()
					.multiply(currentCostPriceLowerLevel);
			accumulatedQuantity = dimension.getCumulatedQty().add(
					transaction.getMovementQty());
			accumulatedAmount = dimension.getCumulatedAmt().add(amount);
			accumulatedAmountLowerLevel = dimension.getCumulatedAmtLL().add(amountLowerLevel);
			return;
		}

		if (costDetail != null) {
			amount = transaction.getMovementQty().multiply(
					costDetail.getCurrentCostPrice());
			amountLowerLevel = transaction.getMovementQty().multiply(
					costDetail.getCurrentCostPriceLL());
			accumulatedQuantity = costDetail.getCumulatedQty().add(
					transaction.getMovementQty());
			accumulatedAmount = costDetail.getCumulatedAmt().add(amount);
			accumulatedAmountLowerLevel = costDetail.getCumulatedAmtLL().add(amountLowerLevel);
			currentCostPrice = dimension.getCurrentCostPrice();
			currentCostPriceLowerLevel = dimension.getCurrentCostPriceLL();
			adjustCost = currentCostPrice.multiply(
					dimension.getCumulatedQty()).subtract(
					dimension.getCumulatedAmt());
			adjustCost = currentCostPriceLowerLevel.multiply(
					dimension.getCumulatedQty()).subtract(
					dimension.getCumulatedAmtLL());
			return;
		}

		amount = transaction.getMovementQty().multiply(
				dimension.getCurrentCostPrice());
		amountLowerLevel = transaction.getMovementQty().multiply(
				dimension.getCurrentCostPriceLL());
		accumulatedAmount = dimension.getCumulatedAmt().add(amount)
				.add(adjustCost);
		accumulatedAmountLowerLevel = dimension.getCumulatedAmtLL().add(amountLowerLevel)
				.add(adjustCostLowerLevel);
		accumulatedQuantity = dimension.getCumulatedQty().add(
				transaction.getMovementQty());
		currentCostPrice = dimension.getCurrentCostPrice();
		currentCostPriceLowerLevel = dimension.getCurrentCostPriceLL();
	}

	private void createCostDetail() {
		final String idColumnName = CostEngine.getIDColumnName(model);
		if (model.getReversalLine_ID() > 0) {
			createReversalCostDetail();
			return;
		}


		if (model instanceof MPPCostCollector)
		{
			MPPCostCollector cc = (MPPCostCollector) model;
			if (MPPCostCollector.COSTCOLLECTORTYPE_MethodChangeVariance
					.equals(cc.getCostCollectorType())) {
				createMethodVariances(cc);
			} else if (MPPCostCollector.COSTCOLLECTORTYPE_UsegeVariance
					.equals(cc.getCostCollectorType())) {
				createUsageVariances(cc);
			} else if (MPPCostCollector.COSTCOLLECTORTYPE_RateVariance
					.equals(cc.getCostCollectorType())) {
				createRateVariances(cc);
			} else if (MPPCostCollector.COSTCOLLECTORTYPE_MixVariance
					.equals(cc.getCostCollectorType())) {
				; // no implement
			}
		}
		
		if (costDetail == null) {
			costDetail = new MCostDetail(transaction, accountSchema.getC_AcctSchema_ID(),
					dimension.getM_CostType_ID(),
					dimension.getM_CostElement_ID(),
					currentCostPrice.multiply(transaction.getMovementQty()),
					currentCostPriceLowerLevel.multiply(transaction.getMovementQty()),
					transaction.getMovementQty(), transaction.get_TrxName());
			costDetail.setDateAcct(model.getDateAcct());
			costDetail.set_ValueOfColumn(idColumnName,
					CostEngine.getIDColumn(model));
		} else {
			if (!adjustCost.equals(Env.ZERO)) {
				costDetail.setCostAdjustment(adjustCost);
				costDetail.setProcessed(false);
				costDetail.setDescription("Adjust Cost");

			}
			if (!adjustCostLowerLevel.equals(Env.ZERO)) {
				costDetail.setCostAdjustmentLL(adjustCostLowerLevel);
				costDetail.setProcessed(false);
				costDetail.setDescription("Adjust Cost LL");

			}
			costDetail.set_ValueOfColumn(idColumnName,
					CostEngine.getIDColumn(model));
			costDetail.saveEx();
			return;
		}

		if (!costDetail.set_ValueOfColumnReturningBoolean(idColumnName,
				model.get_ID()))
			throw new AdempiereException("Cannot set " + idColumnName);

		if (isSalesTransaction != null)
			costDetail.setIsSOTrx(isSalesTransaction);
		else
			costDetail.setIsSOTrx(model.isSOTrx());

		costDetail.setCumulatedQty(dimension.getCumulatedQty());
		costDetail.setCumulatedAmt(dimension.getCumulatedAmt());
		costDetail.setCurrentCostPrice(dimension.getCurrentCostPrice());
		costDetail.setCumulatedAmtLL(dimension.getCumulatedAmtLL());
		costDetail.setCurrentCostPriceLL(dimension.getCurrentCostPriceLL());
		StringBuilder description = new StringBuilder();
		if (!Util.isEmpty(model.getDescription(), true))
			description.append(model.getDescription());
		if (isSalesTransaction != null) {
			description.append(isSalesTransaction ? "(|->)" : "(|<-)");
		}
		if (transaction != null)
			costDetail.setM_Transaction_ID(transaction.getM_Transaction_ID());
		costDetail.setDescription(description.toString());
		updateAmountCost();
		costDetail.saveEx();
		// CostAmt

		return;
	}

	public void createCostAdjutment() {
	}

	public MCostDetail process() {
		calculate();
		createCostDetail();
		updateInventoryValue();
		createCostAdjutment();
		return costDetail;
	}

	/**
	 * Average Invoice Get the New Current Cost Price This Level
	 * 
	 * @param cd
	 *            Cost Detail
	 * @param scale
	 *            Scale
	 * @param roundingMode
	 *            Rounding Mode
	 * @return New Current Cost Price This Level
	 */
	public BigDecimal getNewCurrentCostPrice(MCostDetail cd, int scale,
			int roundingMode) {
		if (getNewAccumulatedQuantity(cd).signum() != 0)
			return cd.getCurrentCostPrice();
		else
			return BigDecimal.ZERO;
	}

	/**
	 * Average Invoice Get the New Cumulated Amt This Level
	 * 
	 * @param cd
	 *            Cost Detail
	 * @return New Cumulated Amt This Level
	 */
	public BigDecimal getNewAccumulatedAmount(MCostDetail cd) {
		return cd.getCumulatedAmt().add(cd.getCostAmt())
				.add(cd.getCostAdjustment());
	}

	/**
	 * Average Invoice Get the New Current Cost Price low level
	 * 
	 * @param cd
	 *            Cost Detail
	 * @param scale
	 *            Scale
	 * @param roundingMode
	 *            Rounding Mode
	 * @return New Current Cost Price low level
	 */
	public BigDecimal getNewCurrentCostPriceLowerLevel(MCostDetail cd, int scale,
                                                       int roundingMode) {
		if (getNewAccumulatedQuantity(cd).signum() != 0)
			return cd.getCurrentCostPriceLL();
		else
			return BigDecimal.ZERO;
	}

	/**
	 * Average Invoice Get the new Cumulated Amt Low Level
	 * 
	 * @param cd
	 *            MCostDetail
	 * @return New Cumulated Am Low Level
	 */
	public BigDecimal getNewAccumulatedAmountLowerLevel(MCostDetail cd) {
		return cd.getCumulatedAmtLL().add(cd.getCostAmtLL())
				.add(cd.getCostAdjustmentLL());
	}

	/**
	 * Average Invoice Get the new Cumulated Qty
	 * 
	 * @param cd
	 *            Cost Detail
	 * @return New Cumulated Qty
	 */
	public BigDecimal getNewAccumulatedQuantity(MCostDetail cd) {
		return cd.getCumulatedQty().add(cd.getQty());
	}

	public void processCostDetail(MCostDetail costDetail) {
		if (!costDetail.isProcessed()) {
			MAcctSchema as = MAcctSchema.get(costDetail.getCtx(),
					costDetail.getC_AcctSchema_ID());
			MClient client = MClient.get(as.getCtx(), as.getAD_Client_ID());
			if (client.isCostImmediate())
				costDetail.process();
		}
	}

	@Override
	protected List<CostComponent> getCalculatedCosts() {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateAmountCost() {
		if (transaction.getMovementType().contains("+")) {
			costDetail.setCostAmt(costDetail.getAmt().subtract(
					costDetail.getCostAdjustment()));
			costDetail.setCostAmtLL(costDetail.getAmtLL().subtract(
                    adjustCostLowerLevel));
		}
		if (transaction.getMovementType().contains("-")) {
			costDetail.setCostAmt(costDetail.getAmt().add(adjustCost));
			costDetail.setCostAmtLL(costDetail.getAmtLL().add(
                    adjustCostLowerLevel));
		}
	}


    /**
     * Update the Inventory Value based in last transaction
     */
    public void updateInventoryValue() {
        dimension.setCumulatedAmt(accumulatedAmount);
        dimension.setCumulatedAmtLL(accumulatedAmountLowerLevel);
        dimension.setCumulatedQty(accumulatedQuantity);
        dimension.setCurrentQty(accumulatedQuantity);
        dimension.saveEx();
    }
	
	public BigDecimal getResourceStandardCostRate(MPPCostCollector cc,
			int S_Resource_ID, CostDimension d, String trxName) {
		final MProduct resourceProduct = MProduct.forS_Resource_ID(
				Env.getCtx(), S_Resource_ID, null);
		return getProductStandardCostPrice(cc, resourceProduct,
				MAcctSchema.get(Env.getCtx(), d.getC_AcctSchema_ID()),
				MCostElement.get(Env.getCtx(), d.getM_CostElement_ID()));
	}
	
	public BigDecimal getProductStandardCostPrice(MPPCostCollector cc,
			MProduct product, MAcctSchema as, MCostElement element) {
		CostDimension d = new CostDimension(product, as, as.getM_CostType_ID(),
				0, // AD_Org_ID,
				0,
				0, // M_ASI_ID,
				element.getM_CostElement_ID());
		MPPOrderCost oc = d.toQuery(MPPOrderCost.class,
				MPPOrderCost.COLUMNNAME_PP_Order_ID + "=?",
				new Object[] { cc.getPP_Order_ID() }, cc.get_TrxName())
				.firstOnly();
		if (oc == null) {
			return Env.ZERO;
		}
		BigDecimal costs = oc.getCurrentCostPrice().add(
				oc.getCurrentCostPriceLL());
		return CostEngine.roundCost(costs, as.getC_AcctSchema_ID());
	}

	public void createRateVariances(MPPCostCollector cc) {
		final MProduct product;
		if (cc.isCostCollectorType(MPPCostCollector.COSTCOLLECTORTYPE_ActivityControl)) {
			final I_AD_WF_Node node = cc.getPP_Order_Node().getAD_WF_Node();
			product = MProduct.forS_Resource_ID(cc.getCtx(),
					node.getS_Resource_ID(), null);
		} else if (cc
				.isCostCollectorType(MPPCostCollector.COSTCOLLECTORTYPE_ComponentIssue)) {
			final I_PP_Order_BOMLine bomLine = cc.getPP_Order_BOMLine();
			product = MProduct.get(cc.getCtx(), bomLine.getM_Product_ID());
		} else {
			return;
		}

		MPPCostCollector ccrv = null; // Cost Collector - Rate Variance
		for (MAcctSchema as : CostEngine.getAcctSchema(cc)) {
			for (MCostElement element : MCostElement.getCostElement(cc.getCtx(), cc.get_TrxName())) {
				final MCostDetail cd = MCostDetail.getCostDetail(cc,
						element.getM_CostElement_ID());
				if (cd == null)
					continue;
				//
				final BigDecimal qty = cd.getQty();
				final BigDecimal priceStd = getProductStandardCostPrice(cc,
						product, as, element);
				final BigDecimal priceActual = getProductActualCostPrice(cc,
						product, as, element, cc.get_TrxName());
				final BigDecimal amtStd = CostEngine.roundCost(priceStd.multiply(qty),
						as.getC_AcctSchema_ID());
				final BigDecimal amtActual = CostEngine.roundCost(
						priceActual.multiply(qty), as.getC_AcctSchema_ID());
				if (amtStd.compareTo(amtActual) == 0)
					continue;
				//
				if (ccrv == null) {
					ccrv = MPPCostCollector.createVarianceCostCollector(cc,
							MPPCostCollector.COSTCOLLECTORTYPE_RateVariance);
				}
				//
				createVarianceCostDetail(ccrv, amtActual.negate(),
						qty.negate(), cd, null, as, element);
				createVarianceCostDetail(ccrv, amtStd, qty, cd, null, as,
						element);
			}
		}
		//
		if (ccrv != null) {
			boolean ok = ccrv.processIt(MPPCostCollector.ACTION_Complete);
			ccrv.saveEx();
			if (!ok)
				throw new AdempiereException(ccrv.getProcessMsg());
		}
	}

	public void createMethodVariances(MPPCostCollector cc) {
		if (!cc.isCostCollectorType(MPPCostCollector.COSTCOLLECTORTYPE_ActivityControl))
			return;
		//
		final int std_resource_id = cc.getPP_Order_Node().getAD_WF_Node()
				.getS_Resource_ID();
		final int actual_resource_id = cc.getS_Resource_ID();
		if (std_resource_id == actual_resource_id) {
			return;
		}
		//
		MPPCostCollector ccmv = null; // Cost Collector - Method Change Variance
		final RoutingService routingService = RoutingServiceFactory.get()
				.getRoutingService(cc.getAD_Client_ID());
		for (MAcctSchema as : CostEngine.getAcctSchema(cc)) {
			for (MCostElement element : MCostElement.getCostElement(cc.getCtx(), cc.get_TrxName())) {
				final MProduct resourcePStd = MProduct.forS_Resource_ID(
						cc.getCtx(), std_resource_id, null);
				final MProduct resourcePActual = MProduct.forS_Resource_ID(
						cc.getCtx(), actual_resource_id, null);
				final BigDecimal priceStd = getProductActualCostPrice(cc,
						resourcePStd, as, element, cc.get_TrxName());
				final BigDecimal priceActual = getProductActualCostPrice(cc,
						resourcePActual, as, element, cc.get_TrxName());
				if (priceStd.compareTo(priceActual) == 0) {
					continue;
				}
				//
				if (ccmv == null) {
					ccmv = MPPCostCollector.createVarianceCostCollector(
							cc,
							MPPCostCollector.COSTCOLLECTORTYPE_MethodChangeVariance);
				}
				//
				final BigDecimal qty = routingService.getResourceBaseValue(
						cc.getS_Resource_ID(), cc);
				final BigDecimal amtStd = priceStd.multiply(qty);
				final BigDecimal amtActual = priceActual.multiply(qty);
				//
				createVarianceCostDetail(ccmv, amtActual, qty, null,
						resourcePActual, as, element);
				createVarianceCostDetail(ccmv, amtStd.negate(), qty.negate(),
						null, resourcePStd, as, element);
			}
		}
		//
		if (ccmv != null) {
			boolean ok = ccmv.processIt(MPPCostCollector.ACTION_Complete);
			ccmv.saveEx();
			if (!ok)
				throw new AdempiereException(ccmv.getProcessMsg());
		}
	}
	
	/**
	 * Create Cost Detail (Material Issue, Material Receipt)
	 * 
	 * @param model
	 * @param mtrx
	 *            Material Transaction
	 */
	/*public void createStandardCostDetail(IDocumentLine model, MTransaction mtrx) {
		final MPPCostCollector cc = (model instanceof MPPCostCollector ? (MPPCostCollector) model
				: null);
		for (MAcctSchema as : CostEngine.getAcctSchema(mtrx)) {
			// Cost Detail
			final MProduct product = MProduct.get(mtrx.getCtx(),
					mtrx.getM_Product_ID());
			final String costingMethod = product.getCostingMethod(as,
					mtrx.getAD_Org_ID());
			// Check costing method
			if (!getCostingMethod().equals(costingMethod)) {
				throw new AdempiereException("Costing method not supported - "
						+ costingMethod);
			}
			
			//
			for (MCostElement element : MCostElement.getCostElement(mtrx.getCtx(), mtrx.get_TrxName())) {
				//
				// Delete Unprocessed zero Differences
				CostEngine.deleteCostDetail(model, as, element.get_ID(),
						mtrx.getM_AttributeSetInstance_ID());
				//
				// Get Costs
				final BigDecimal qty = mtrx.getMovementQty();
				final BigDecimal price = getProductActualCostPrice(cc, product,
						as, element, mtrx.get_TrxName());
				final BigDecimal amt = CostEngine.roundCost(price.multiply(qty),
						as.getC_AcctSchema_ID());
				//
				// Create / Update Cost Detail
				MCostDetail cd = MCostDetail.getCostDetail(model, mtrx, as,
						element.get_ID());
				if (cd == null) // createNew
				{
					List<MCostType> costtypes = MCostType.get(as.getCtx(),
							as.get_TrxName());
					for (MCostType mc : costtypes) {
						int M_CostType_ID = mc.get_ID();
						cd = new MCostDetail(as, mtrx.getAD_Org_ID(),
								mtrx.getM_Product_ID(),
								mtrx.getM_AttributeSetInstance_ID(),
								element.get_ID(), amt, qty,
								model.getDescription(), mtrx.get_TrxName(),
								M_CostType_ID);
						// cd.setMovementDate(mtrx.getMovementDate());
						// if (cost != null)
						// {
						// cd.setCurrentCostPrice(cost.getCurrentCostPrice());
						// cd.setCurrentCostPriceLL(cost.getCurrentCostPriceLL());
						// }
						// else
						// {
						// cd.setCurrentCostPrice(Env.ZERO);
						// cd.setCurrentCostPriceLL(Env.ZERO);
						// }
						// cd.setM_CostType_ID(as.getM_CostType_ID());
						// //cd.setCostingMethod(element.getCostingMethod());
						// cd.setM_Transaction_ID(mtrx.get_ID());
						if (model instanceof MPPCostCollector)
							cd.setPP_Cost_Collector_ID(model.get_ID());
					}
				} else {
					cd.setDeltaAmt(amt.subtract(cd.getAmt()));
					cd.setDeltaQty(mtrx.getMovementQty().subtract(cd.getQty()));
					if (cd.isDelta()) {
						cd.setProcessed(false);
						cd.setAmt(amt);
						cd.setQty(mtrx.getMovementQty());
					}
				}
				cd.saveEx();
				processCostDetail(cd);
				log.config("" + cd);
			} // for ELements
		} // Account Schema
	}*/

	/**
	 * Create & Proce Cost Detail for Variances
	 * 
	 * @param ccv
	 * @param amt
	 * @param qty
	 * @param cd
	 *            (optional)
	 * @param product
	 * @param as
	 * @param element
	 * @return
	 */
	public MCostDetail createVarianceCostDetail(MPPCostCollector ccv,
			BigDecimal amt, BigDecimal qty, MCostDetail cd, MProduct product,
			MAcctSchema as, MCostElement element) {
		final MCostDetail cdv = new MCostDetail(ccv.getCtx(), 0,
				ccv.get_TrxName());
		if (cd != null) {
			MCostDetail.copyValues(cd, cdv);
			cdv.setProcessed(false);
		}
		if (product != null) {
			cdv.setM_Product_ID(product.getM_Product_ID());
			cdv.setM_AttributeSetInstance_ID(ccv.getM_AttributeSetInstance_ID());
		}
		if (as != null) {
			cdv.setC_AcctSchema_ID(as.getC_AcctSchema_ID());
		}
		if (element != null) {
			cdv.setM_CostElement_ID(element.getM_CostElement_ID());
		}
		//
		cdv.setPP_Cost_Collector_ID(ccv.getPP_Cost_Collector_ID());
		cdv.setAmt(amt);
		cdv.setQty(qty);
		cdv.setDateAcct(ccv.getDateAcct());
		cdv.saveEx();
		processCostDetail(cdv);
		return cdv;
	}

	public void createActivityControl(MPPCostCollector cc) {
		if (!cc.isCostCollectorType(MPPCostCollector.COSTCOLLECTORTYPE_ActivityControl))
			return;
		//
		final MProduct product = MProduct.forS_Resource_ID(cc.getCtx(),
				cc.getS_Resource_ID(), null);
		final RoutingService routingService = RoutingServiceFactory.get()
				.getRoutingService(cc.getAD_Client_ID());
		final BigDecimal qty = routingService.getResourceBaseValue(
				cc.getS_Resource_ID(), cc);
		for (MAcctSchema as : CostEngine.getAcctSchema(cc)) {
			for (MCostElement costElement : MCostElement.getCostElement(cc.getCtx(), cc.get_TrxName())) {
				if (!CostEngine.isActivityControlElement(costElement)) {
					continue;
				}
				final CostDimension d = new CostDimension(product, as,
						as.getM_CostType_ID(), 0, // AD_Org_ID,
						0,
						0, // M_ASI_ID
						costElement.getM_CostElement_ID());
				final BigDecimal price = getResourceActualCostRate(cc,
						cc.getS_Resource_ID(), d, cc.get_TrxName());
				BigDecimal costs = price.multiply(qty);
				if (costs.scale() > as.getCostingPrecision())
					costs = costs.setScale(as.getCostingPrecision(),
							RoundingMode.HALF_UP);
				//
				List<MCostType> costtypes = MCostType.get(as.getCtx(),
						as.get_TrxName());
				for (MCostType costType : costtypes) {
					//implementation only for standard cost
					if (!MCostType.COSTINGMETHOD_StandardCosting.equals(costType.getCostingMethod()))
						continue;
					
					MCostDetail cd = new MCostDetail(as, cc.getAD_Org_ID(), // AD_Org_ID,
							d.getM_Product_ID(), 0, // M_AttributeSetInstance_ID,
							costElement.getM_CostElement_ID(), costs.negate(),
							qty.negate(), costElement.getName() , // Description,
							cc.get_TrxName(), costType.getM_CostType_ID());
					cd.setPP_Cost_Collector_ID(cc.getPP_Cost_Collector_ID());
					// setCostingMethod(ct.getCostingMethod());
					cd.setDateAcct(cc.getDateAcct());
					cd.saveEx();
					processCostDetail(cd);
				}
			}
		}
	}

	public void createUsageVariances(MPPCostCollector usegeVariance) {
		// Apply only for material Usage Variance
		if (!usegeVariance
				.isCostCollectorType(MPPCostCollector.COSTCOLLECTORTYPE_UsegeVariance)) {
			throw new IllegalArgumentException(
					"Cost Collector is not Material Usage Variance");
		}
		//
		final MProduct product;
		final BigDecimal qty;
		if (usegeVariance.getPP_Order_BOMLine_ID() > 0) {
			product = MProduct.get(usegeVariance.getCtx(), usegeVariance.getM_Product_ID());
			qty = usegeVariance.getMovementQty();
		} else {
			product = MProduct.forS_Resource_ID(usegeVariance.getCtx(),
					usegeVariance.getS_Resource_ID(), null);
			final RoutingService routingService = RoutingServiceFactory.get()
					.getRoutingService(usegeVariance.getAD_Client_ID());
			qty = routingService.getResourceBaseValue(usegeVariance.getS_Resource_ID(),
					usegeVariance);
		}
		//
		for (MAcctSchema as : CostEngine.getAcctSchema(usegeVariance)) {
			for (MCostElement element : MCostElement.getCostElement(usegeVariance.getCtx(), usegeVariance.get_TrxName())) {
				final BigDecimal price = getProductActualCostPrice(usegeVariance,
						product, as, element, usegeVariance.get_TrxName());
				final BigDecimal amt = CostEngine.roundCost(price.multiply(qty),
						as.getC_AcctSchema_ID());
				//
				// Create / Update Cost Detail
				if (amt.compareTo(Env.ZERO) != 0)
					createVarianceCostDetail(usegeVariance, amt, qty, null, // no
																	// original
																	// cost
																	// detail
							product, as, element);
			} // for ELements
		} // Account Schema
	}
	
	public static BigDecimal getResourceActualCostRate(MPPCostCollector cc,
			int S_Resource_ID, CostDimension d, String trxName) {
		if (S_Resource_ID <= 0)
			return Env.ZERO;
		final MProduct resourceProduct = MProduct.forS_Resource_ID(
				Env.getCtx(), S_Resource_ID, null);
		return getProductActualCostPrice(cc, resourceProduct,
				MAcctSchema.get(Env.getCtx(), d.getC_AcctSchema_ID()),
				MCostElement.get(Env.getCtx(), d.getM_CostElement_ID()),
				trxName);
	}
	
	public static BigDecimal getProductActualCostPrice(MPPCostCollector cc,
			MProduct product, MAcctSchema as, MCostElement element,
			String trxName) {
		String CostingLevel = product.getCostingLevel(as);
		// Org Element
		int AD_Org_ID = cc.getAD_Org_ID();
		int M_Warehouse_ID = cc.getM_Warehouse_ID();
		
		int M_ASI_ID = cc.getM_AttributeSetInstance_ID();
		if (MAcctSchema.COSTINGLEVEL_Client.equals(CostingLevel)) {
			AD_Org_ID = 0;
			M_ASI_ID = 0;
			M_Warehouse_ID = 0;
		} 
		else if (MAcctSchema.COSTINGLEVEL_Organization.equals(CostingLevel))
		{	
			M_ASI_ID = 0;
			M_Warehouse_ID = 0;
		}	
		else if (MAcctSchema.COSTINGLEVEL_Warehouse.equals(CostingLevel))
		{	
			M_ASI_ID = 0;
		}	
		else if (MAcctSchema.COSTINGLEVEL_BatchLot.equals(CostingLevel))
		{
			AD_Org_ID = 0;
			M_Warehouse_ID = 0;
		}
			
		CostDimension d = new CostDimension(product, as, as.getM_CostType_ID(),
				AD_Org_ID, M_Warehouse_ID ,M_ASI_ID, // M_ASI_ID,
				element.getM_CostElement_ID());
		MCost cost = d.toQuery(MCost.class, trxName).firstOnly();
		if (cost == null)
			return Env.ZERO;
		BigDecimal price = cost.getCurrentCostPrice().add(
				cost.getCurrentCostPriceLL());
		return CostEngine.roundCost(price, as.getC_AcctSchema_ID());
	}

}
