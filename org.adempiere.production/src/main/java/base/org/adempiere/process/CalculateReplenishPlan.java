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
 * Copyright (C) 2016 ADempiere Foundation All Rights Reserved.               *
 *****************************************************************************/

package org.adempiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_C_OrderLine;
import org.compiere.model.I_M_Product_BOM;
import org.compiere.model.I_M_Production;
import org.compiere.model.I_M_Replenish;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.MProductBOM;
import org.compiere.model.MProductPO;
import org.compiere.model.MProductPricing;
import org.compiere.model.MProduction;
import org.compiere.model.MReplenish;
import org.compiere.model.MReplenishPlan;
import org.compiere.model.MRequisition;
import org.compiere.model.MRequisitionLine;
import org.compiere.model.MStorage;
import org.compiere.model.Query;
import org.compiere.model.X_M_Replenish;
import org.compiere.model.X_M_ReplenishPlanLine;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;
import org.eevolution.model.MPPProductBOM;
import org.eevolution.model.MPPProductBOMLine;

/**
 * CalculateReplenishPlan.Java
 * 
 * @author hitesh.panchani, www.logilite.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/789">
 * 		@see FR [ 789 ] The Calculate Replenish Plan process not support SQL99</a>
 */
public class CalculateReplenishPlan extends CalculateReplenishPlanAbstract {
	private static final String			TYPE_RQ									= "RQ";
	private static final String			TYPE_PO									= "PO";
	private static final String			TYPE_MO									= "MO";
	private int							lineNo									= 10;
	private int							countProd								= 0;
	private int							countReq								= 0;
	private int							docTypePlannedOrder;
	private int							docTypeConfirmedOrder;
	private int							docTypePurchaseOrder;
	private int							docTypeMRPRequisition;
	private int							priceListId;
	private Calendar					calendar								= Calendar.getInstance();
	
	// Available Inventory - ProductID, Qty
	private Map<Integer, BigDecimal>	availableInventory						= new TreeMap<Integer, BigDecimal>();

	// Map Demand for SalesOrder_DatePromise, ProductID, RequiredQTY.
	Map<Date, Map<Integer, BigDecimal>>	mapDemand								= new TreeMap<Date, Map<Integer, BigDecimal>>();

	// map use for DatePromise for create planned Production, Product reference,
	// planned qty [ProductID, RequiredDate, DemandQty]
	Map<Integer, Map<Date, BigDecimal>>	mapRequirePlanningQty					= new TreeMap<Integer, Map<Date, BigDecimal>>();
	Map<Date, MRequisition> 			mapRequisition = new TreeMap<Date, MRequisition>();

	StringBuilder						infoMsg									= new StringBuilder();
	StringBuilder						productionDocs							= new StringBuilder();
	StringBuilder						requisitionDocs							= new StringBuilder();
	//	MRP Model
	private MReplenishPlan 				replenishPlan = null;
	
	@Override
	protected void prepare() {
		super.prepare();
	}


	@Override
	protected String doIt() throws Exception {
		replenishPlan = new MReplenishPlan(getCtx(), getRecord_ID(), get_TrxName());
		//	For Date Start
		if(getDateStart() == null) {
			setDateStart(replenishPlan.getDateStart());
		} else {
			replenishPlan.setDateStart(getDateStart());
		}
		//	For Date Finish
		if(getDateFinish() == null) {
			setDateFinish(replenishPlan.getDateFinish());
		} else {
			replenishPlan.setDateFinish(getDateFinish());
		}
		//	
		priceListId = replenishPlan.getM_PriceList_ID();

		docTypePlannedOrder = replenishPlan.getC_DocType_PlannedOrder();
		docTypeConfirmedOrder = replenishPlan.getC_DocType_ConfirmedOrder();
		docTypePurchaseOrder = replenishPlan.getC_DocType_PO();
		docTypeMRPRequisition = replenishPlan.getC_DocType_Requisition();
		
		//	Validate
		StringBuilder error = new StringBuilder();
		
		if (docTypePlannedOrder <= 0)
			error.append("@C_DocType_PlannedOrder@ @NotFound@\n");
		if (docTypeConfirmedOrder <= 0)
			error.append("@C_DocType_ConfirmedOrder@ @NotFound@\n");
		if (docTypePurchaseOrder <= 0)
			error.append("@C_DocType_PO@ @NotFound@\n");
		if (docTypeMRPRequisition <= 0)
			error.append("@C_DocType_Requisition@ @NotFound@\n");
		//	
		if (error.length() > 0) {
			throw new Exception(Msg.parseTranslation(getCtx(), error.toString()));
		}
		//	Validate Dates
		if (getDateStart() == null)
			throw new IllegalArgumentException(Msg.parseTranslation(getCtx(), "@FillMandatory@ @DateStart@"));
		if (getDateFinish() == null)
			throw new IllegalArgumentException(Msg.parseTranslation(getCtx(), "@FillMandatory@ @DateFinish@"));
		if (priceListId == 0)
			throw new IllegalArgumentException(Msg.parseTranslation(getCtx(), "@FillMandatory@ @M_PriceList_ID@"));
		//	Save changes
		replenishPlan.saveEx();
		//	Delete 
		String sql = "DELETE FROM M_ReplenishPlanLine WHERE M_ReplenishPlan_ID=?";
		int noOfLinesDeleted = DB.executeUpdateEx(sql, new Object[] {getRecord_ID()}, get_TrxName());
		log.fine("No. of MRP lines deleted : " + noOfLinesDeleted);
		//	
		if (replenishPlan.isDeleteUnconfirmedProduction()) {
			deleteUnconfirmedProduction();
		}
		//	
		if (replenishPlan.isDeletePlannedPO()) {
			deletePlannedPO();
		}

		Map<Integer, MiniMRPProduct> miniMrpProducts = new TreeMap<Integer, MiniMRPProduct>();
		Set<Integer> productIds = new TreeSet<Integer>();

		// Collect all the Products required to be processed.
		generateProductInfo(miniMrpProducts, productIds);
		// Process Demand
		doRunProductsSO(miniMrpProducts, productIds);
		// Creating Requisition Order and Planned Production Order
		doRunCreatePOandProductionOrder(miniMrpProducts);
		// Process Supply
		//	For Orders
		doRunOpenOrders(miniMrpProducts, productIds, TYPE_PO);
		//	For Productions
		doRunOpenOrders(miniMrpProducts, productIds, TYPE_MO);
		//	For Requisition
		doRunOpenOrders(miniMrpProducts, productIds, TYPE_RQ);
		//	
		renderPeggingReport(miniMrpProducts);
		//	
		updateHasSupplyDemand();
		//	
		return infoMsg.toString();
	}
	
	/**
	 * Delete unconfirmed productions
	 */
	private void deleteUnconfirmedProduction() {
		String sql = "DELETE FROM M_ProductionLine "
				+ "WHERE EXISTS(SELECT 1 FROM M_Production "
				+ "					WHERE M_Production_ID = M_ProductionLine.M_Production_ID "
				+ "					AND Processed='N' "
				+ "					AND C_DocType_ID = ?)";
		int noOfLinesDeleted = DB.executeUpdate(sql, docTypePlannedOrder, get_TrxName());
		log.fine("No. of planned production line deleted : " + noOfLinesDeleted);

		sql = "DELETE FROM M_Production	"
				+ "WHERE Processed='N' "
				+ "AND C_DocType_ID = ?";
		noOfLinesDeleted = DB.executeUpdate(sql, docTypePlannedOrder, get_TrxName());
		log.fine("No. of planned production deleted : " + noOfLinesDeleted);

		sql = "DELETE FROM M_ProductionBatch b  " +
				"WHERE b.C_DocType_ID = ? " +
	            "AND NOT EXISTS(SELECT 1 " +
	             "                  FROM M_Production " +
	             "                  WHERE M_ProductionBatch_ID = b.M_ProductionBatch_ID)";
		noOfLinesDeleted = DB.executeUpdate(sql, docTypePlannedOrder, get_TrxName());
		log.fine("No. of Production Batch deleted " + noOfLinesDeleted);

		sql = "DELETE FROM M_MovementLine ml "
				+ "WHERE EXISTS(SELECT 1 FROM M_Movement m "
				+ "				WHERE m.M_Movement_ID = ml.M_Movement_ID "
				+ "				AND m.M_ProductionBatch_ID IS NOT NULL "
				+ "				AND m.Processed = 'N' "
				+ "					AND NOT EXISTS(SELECT 1 "
				+ "							FROM M_ProductionBatch b "
				+ "							WHERE b.M_ProductionBatch_ID = m.M_ProductionBatch_ID))";
		noOfLinesDeleted = DB.executeUpdate(sql, get_TrxName());
		log.fine("No. of Movement Lines cleaned : " + noOfLinesDeleted);

		sql = "DELETE FROM M_Movement m "
				+ "WHERE m.M_ProductionBatch_ID IS NOT NULL "
				+ "AND m.Processed = 'N'"
				+ "AND NOT EXISTS(SELECT 1 FROM M_ProductionBatch b "
				+ "				WHERE b.M_ProductionBatch_ID = m.M_ProductionBatch_ID)";
		noOfLinesDeleted = DB.executeUpdate(sql, get_TrxName());
		log.fine("No. of Inventory Movements cleaned : " + noOfLinesDeleted);
	}
	
	/**
	 * Delete Planned PO
	 */
	private void deletePlannedPO() {
		String sql = "DELETE FROM PP_MRP "
				+ "WHERE EXISTS(SELECT 1 FROM M_Requisition "
				+ "					WHERE M_Requisition_ID = PP_MRP.M_Requisition_ID"
				+ "					AND DocStatus = 'DR' "
				+ "					AND Processed='N' "
				+ "					AND AD_Client_ID = ? "
				+ "					AND C_DocType_ID = ?)";
		int noOfLinesDeleted = DB.executeUpdateEx(sql, new Object[] {getAD_Client_ID(), docTypeMRPRequisition}, get_TrxName());
		log.fine("No. of Material Requirement Planning (PP_MRP) Line deleted : " + noOfLinesDeleted);

		sql = "DELETE FROM M_RequisitionLine "
				+ "WHERE EXISTS(SELECT 1 FROM M_Requisition "
				+ "					WHERE M_Requisition_ID = M_RequisitionLine.M_Requisition_ID"
				+ "					AND DocStatus = 'DR' "
				+ "					AND Processed='N' "
				+ "					AND AD_Client_ID = ? "
				+ "					AND C_DocType_ID = ?)";
		noOfLinesDeleted = DB.executeUpdateEx(sql, new Object[] {getAD_Client_ID(), docTypeMRPRequisition }, get_TrxName());
		log.fine("No. of MRP Requisition Line deleted : " + noOfLinesDeleted);
		
		sql = "DELETE FROM M_Requisition "
				+ "WHERE DocStatus = 'DR' "
				+ "AND Processed='N' "
				+ "AND AD_Client_ID = ? "
				+ "AND C_DocType_ID = ?";
		noOfLinesDeleted = DB.executeUpdateEx(sql, new Object[] {getAD_Client_ID(), docTypeMRPRequisition}, get_TrxName());
		log.fine("No. of MRP Requisition deleted : " + noOfLinesDeleted);
	}

	/**
	 * Set flag to show products where demand and/or supply exists in the MRP
	 * period, used to filter the MRP report to exclude products with no
	 * movement
	 */
	private void updateHasSupplyDemand() {
		String sql = new String("UPDATE M_ReplenishPlanLine SET HasSupplyDemand = 'Y' "
				+ "WHERE M_ReplenishPlan_ID = ? "
				+ "	AND EXISTS(SELECT 1 FROM M_ReplenishPlanLine rpl "
				+ "				WHERE rpl.M_ReplenishPlan_ID = M_ReplenishPlanLine.M_ReplenishPlan_ID "
				+ "				GROUP BY rpl.AD_Client_ID, rpl.M_ReplenishPlan_ID, rpl.M_Product_ID "
				+ "				HAVING COUNT(rpl.M_Product_ID) > 2)");
		int updated = DB.executeUpdateEx(sql, new Object[] {getRecord_ID()}, get_TrxName());
		//	
		log.fine("Lines with supply/demand updated: " + updated);
	}

	/**
	 * This method will process to create Production Order and Purchase order as
	 * per required quantity.
	 * 
	 * @author Sachin Bhimani
	 * @param miniMrpProducts
	 */
	private void doRunCreatePOandProductionOrder(Map<Integer, MiniMRPProduct> miniMrpProducts) {
		MRequisition requisition = null;

		// Calculate Required and production QTY
		runProcessCalculatePlannedQty(miniMrpProducts);

		// Create Requisition Order with Lines
		for (Integer productID : mapRequirePlanningQty.keySet()) {
			MiniMRPProduct mrp = miniMrpProducts.get(productID);
			Map<Date, BigDecimal> weeklyProductionQty = new TreeMap<Date, BigDecimal>(
					mapRequirePlanningQty.get(productID));
			for (Date date : weeklyProductionQty.keySet()) {
				if (weeklyProductionQty.get(date).compareTo(Env.ZERO) == 0)
					continue;

				if (!mrp.isBOM() && mrp.isPurchased() && !mrp.isPhantom()) {
					requisition = createRequisitionHeader(date);
					createRequisitionLine(requisition, mrp, weeklyProductionQty.get(date));
				} else if (mrp.isBOM() && !mrp.isPhantom()) {
					createProductionOrder(mrp, weeklyProductionQty.get(date), new Timestamp(date.getTime()));
				}
			}
		}
		//	
		infoMsg.append(" @M_Requisition_ID@ @Created@:" + countReq);
		infoMsg.append(" @M_Production_ID@:" + countProd);
		log.log(Level.INFO, infoMsg.toString());
	}

	private void runProcessCalculatePlannedQty(Map<Integer, MiniMRPProduct> miniMrpProducts) {
		for (Date date : mapDemand.keySet()) {
			Map<Integer, BigDecimal> mapOrderQty = mapDemand.get(date);
			for (Integer productID : mapOrderQty.keySet()) {
				MProduct product = new MProduct(getCtx(), productID, get_TrxName());
				if (!product.isStocked())
					return ;
				BigDecimal demandQty = mapOrderQty.get(productID);
				if (demandQty.compareTo(Env.ZERO) != 0) {
					MiniMRPProduct mrp = miniMrpProducts.get(productID);
					if (mrp == null) {
						MProduct p = MProduct.get(getCtx(), productID);
						String error = "Please check Product=" + p.getValue() + " replenishment parameters may not be setup properly.";
						log.severe(error);
						throw new AdempiereException(error);
					}
				
					Integer nonPhantomProduct = (mrp.isPhantom() && mrp.isBOM() ? 0 : productID);
					createPlannedQtyMap(miniMrpProducts, date, productID, demandQty, 0, nonPhantomProduct);
				}
			}
		}
	}

	private void createPlannedQtyMap(Map<Integer, MiniMRPProduct> miniMrpProducts, Date date, Integer productID,
			BigDecimal demandQty, int level, Integer nonPhantomProduct)
	{
		if (miniMrpProducts.containsKey(productID))
		{
			MiniMRPProduct mrp = miniMrpProducts.get(productID);

			// check QtyOnHand
			if (mrp.getQtyOnHand().compareTo(demandQty) >= 0)
			{
				mrp.setQtyOnHand(mrp.getQtyOnHand().subtract(demandQty));
				demandQty = Env.ZERO;
				return;
			}
			else if (mrp.getQtyOnHand().compareTo(Env.ZERO) != 0 && mrp.getQtyOnHand().compareTo(demandQty) < 0)
			{
				demandQty = demandQty.subtract(mrp.getQtyOnHand());
				mrp.setQtyOnHand(Env.ZERO);
			}

			Map<Date, BigDecimal> confirmProductQty = mrp.getMapConfirmProductQty();

			// Check Confirmed Production Order
			if (demandQty.compareTo(Env.ZERO) > 0 && confirmProductQty != null && !confirmProductQty.isEmpty())
			{
				for (Date dateProduction : confirmProductQty.keySet())
				{
					BigDecimal confirmQty = confirmProductQty.get(dateProduction);
					if (confirmQty.compareTo(Env.ZERO) > 0 && date.compareTo(dateProduction) >= 0)
					{
						if (confirmQty.compareTo(demandQty) >= 0)
						{
							confirmQty = confirmQty.subtract(demandQty);
							confirmProductQty.put(dateProduction, confirmQty);
							demandQty = Env.ZERO;
							break;
						}
						else
						{
							demandQty = demandQty.subtract(confirmQty);
							confirmProductQty.put(dateProduction, Env.ZERO);
						}
					}
				}
			}

			if (mrp.isReplenishTypeMRPCalculated() && mrp.getExtraQtyPlanned().compareTo(Env.ZERO) != 0)
			{
				if (mrp.getExtraQtyPlanned().compareTo(demandQty) < 0)
				{
					demandQty = demandQty.subtract(mrp.getExtraQtyPlanned());
					mrp.setExtraQtyPlanned(Env.ZERO);
				}
				else
				{
					mrp.setExtraQtyPlanned(mrp.getExtraQtyPlanned().subtract(demandQty));
					demandQty = Env.ZERO;
					return;
				}
			}

			if (demandQty.compareTo(Env.ZERO) > 0)
			{
				if (!mrp.isPhantom() && mrp.isBOM())
				{
					level++;
					nonPhantomProduct = mrp.getM_Product_ID();
				}
				else if (!mrp.isPhantom() && !mrp.isBOM() && nonPhantomProduct != 0)
				{
					level++;
				}

				calendar.setTimeInMillis(date.getTime());
				calendar.add(Calendar.DAY_OF_MONTH, (level * -7));
				calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				Timestamp plannedDate = new Timestamp(calendar.getTimeInMillis());

				if (plannedDate.compareTo(getDateStart()) < 0) {
					plannedDate = getDateStart();
				}

				if (!mrp.isPhantom() && nonPhantomProduct != 0 && mrp.isBOM()) {
					if (mrp.isReplenishTypeMRPCalculated()) {
						demandQty = calculateBatchSizeWiseOrderCreation(mrp, demandQty, plannedDate);
					} else {
						setRequirePlanningQty(mrp, plannedDate, demandQty);
					}
				}

				if (mrp.isBOM()) {
					planBOMTree(miniMrpProducts, productID, date, demandQty, level, nonPhantomProduct);
				} else if (!mrp.isPhantom()) {
					if (mrp.isReplenishTypeMRPCalculated()) {
						demandQty = calculateBatchSizeWiseOrderCreation(mrp, demandQty, plannedDate);
					} else {
						setRequirePlanningQty(mrp, plannedDate, demandQty);
					}
				}
			}
		}
	}

	/**
	 * Create Planned Production Order
	 * 
	 * @param mrp
	 * @param qty
	 * @param date
	 */
	private void createProductionOrder(MiniMRPProduct mrp, BigDecimal qty, Timestamp date)
	{
		MProduction mProd = new MProduction(getCtx(), 0, get_TrxName());
		//mProd.setAD_Client_ID(AD_Client_ID);
		mProd.setAD_Org_ID(replenishPlan.getAD_Org_ID());
		mProd.setM_Product_ID(mrp.getM_Product_ID());
		mProd.setProductionQty(qty);
		mProd.setM_Locator_ID(getLocatorId());
		mProd.setC_DocType_ID(docTypePlannedOrder);
		mProd.setName("Planned Production Order");
		mProd.setDescription("Creating from MiniMRP");
		mProd.setDatePromised(date);
		mProd.setMovementDate(date);
		mProd.setDocumentNo("<>");
		mProd.saveEx();

		mProd.createLines(false);
		mProd.setIsCreated(true);
		mProd.saveEx();

		countProd++;
		productionDocs.append(mProd.getDocumentNo()).append(",");
	}

	/**
	 * Create Planned Production / Requisition Order as per QtyBatchSize on
	 * Product.
	 * 
	 * @param mrp
	 * @param qty
	 * @param date
	 * @return qtyForPlan
	 */
	private BigDecimal calculateBatchSizeWiseOrderCreation(MiniMRPProduct mrp, BigDecimal qty, Timestamp date)
	{
		int createNoOfOrder = 1;
		BigDecimal QtyPlan = qty;

		if (mrp.getQtyBatchSize().compareTo(Env.ZERO) == 0)
		{
			setRequirePlanningQty(mrp, date, qty);
			return qty;
		}
		else
		{
			createNoOfOrder = qty.divide(mrp.getQtyBatchSize(), 0, BigDecimal.ROUND_CEILING).intValue();
			QtyPlan = mrp.getQtyBatchSize();
		}

		BigDecimal qtyPlanned = QtyPlan.multiply(new BigDecimal(createNoOfOrder));
		mrp.setExtraQtyPlanned(mrp.getExtraQtyPlanned().add(qtyPlanned).subtract(qty));

		for (int i = 0; i < createNoOfOrder; i++)
		{
			if (mrp.isBOM())
				createProductionOrder(mrp, QtyPlan, date);
			else
			{
//				MRequisition requisition = createRequisition(date);
				MRequisition requisition = createRequisitionHeader(date);
				createRequisitionLine(requisition, mrp, QtyPlan);
			}
		}

		return qtyPlanned; 
	}

	private void setRequirePlanningQty(MiniMRPProduct mrp, Date date, BigDecimal demandQty)
	{
		if (mrp.isPhantom())
			return;

		if (mapRequirePlanningQty.containsKey(mrp.getM_Product_ID()))
		{
			Map<Date, BigDecimal> weekly = mapRequirePlanningQty.get(mrp.getM_Product_ID());
			if (weekly.containsKey(date))
				weekly.put(date, weekly.get(date).add(demandQty));
			else
				weekly.put(date, demandQty);
		}
		else
		{
			Map<Date, BigDecimal> weekly = new HashMap<Date, BigDecimal>();
			weekly.put(date, demandQty);
			mapRequirePlanningQty.put(mrp.getM_Product_ID(), weekly);
		}
	}
	
	private void planBOMTree(Map<Integer, MiniMRPProduct> miniMrpProducts, Integer productID, Date date,
		BigDecimal demandQty, int level, Integer nonPhantomProduct) {
		//	Get BOM
		List<MProductBOM> bomList = new Query(getCtx(), I_M_Product_BOM.Table_Name, 
				I_M_Product_BOM.COLUMNNAME_M_Product_ID + "= ?", get_TrxName())
				.setParameters(productID)
				.setClient_ID()
				.list();
		//	Iterate
		bomList.stream()
				.forEach(productBOM -> {
					BigDecimal requiredQty = Env.ZERO;
					if(productBOM.getBOMQty() != null
							&& demandQty != null) {
						demandQty.multiply(productBOM.getBOMQty());
					}
					//	
					if(requiredQty.compareTo(Env.ZERO) > 0) {
						createPlannedQtyMap(miniMrpProducts, date, 
								productBOM.getM_ProductBOM_ID(), requiredQty, level, nonPhantomProduct);
					}
				});
	}

	/**
	 * Create Requisition without implementation of QtyBatchSize logic
	 * 
	 * @param mapRequisition
	 * @param date
	 * @return
	 */
	private MRequisition createRequisitionHeader(Date date)
	{
		MRequisition requisition = null;
		if (!mapRequisition.containsKey(date))
		{
			requisition = createRequisition(date);
			mapRequisition.put(date, requisition);
			log.severe("New Requisition Header created. " + requisition.toString());
		}
		else
			requisition = mapRequisition.get(date);
		return requisition;
	}

	/**
	 * Create Requisition Order
	 * 
	 * @param date
	 * @return
	 */
	private MRequisition createRequisition(Date date) {
		MRequisition requisition;
		requisition = new MRequisition(getCtx(), 0, get_TrxName());
		//requisition.setAD_Client_ID(AD_Client_ID);
		requisition.setM_Warehouse_ID(getWarehouseId());
		requisition.setC_DocType_ID(docTypeMRPRequisition);
		requisition.setAD_User_ID(getAD_User_ID());
		requisition.setDescription(Msg.parseTranslation(getCtx(), "@Created@ @from@ @M_ReplenishPlan_ID@"));
		requisition.setDateRequired(new Timestamp(date.getTime()));
		requisition.setDateDoc(new Timestamp(date.getTime()));
		requisition.setM_PriceList_ID(priceListId);
		requisition.saveEx();

		countReq++;
		requisitionDocs.append(requisition.getDocumentNo()).append(",");

		return requisition;
	}

	private void createRequisitionLine(MRequisition requisition, MiniMRPProduct mrp, BigDecimal qty) {
		//requisition.addLinetoQueue(mrp.getM_Product_ID(), mrp.getC_BPartner_ID(), qty, mrp.getPriceActual());
		MRequisitionLine rLine = new MRequisitionLine(requisition);
		rLine.setM_Product_ID(mrp.getM_Product_ID());
		rLine.setC_BPartner_ID(mrp.getC_BPartner_ID());
		rLine.setPriceActual(mrp.getPriceActual());
		rLine.setQty(qty);
		rLine.saveEx();
	}

	/**
	 * Product wise Get confirmed Qty. If Product is BOM then Production else
	 * from PO.
	 * 
	 * @param mrp
	 */
	private void setConfirmProductQty(MiniMRPProduct mrp) {
		int productID = mrp.getM_Product_ID();
		Map<Date, BigDecimal> mapConfirmQty = new TreeMap<Date, BigDecimal>();
		String sqlConfirmPORQ = new String(" SELECT DateRequired AS DatePromised, SUM(Qty) AS Qty "
				+ "FROM ("
				+ "(SELECT ol.DatePromised AS DateRequired, ol.QtyOrdered - QtyDelivered AS Qty	"
				+ "		FROM C_Order o "
				+ " 	INNER JOIN C_OrderLine ol ON (ol.C_Order_ID = o.C_Order_ID) "
				+ " 	WHERE o.DocStatus IN ('CO', 'CL') "
				+ "		AND o.IsSoTrx = 'N' "
				+ "		AND ol.M_Product_ID = ? "
				+ "		AND ol.QtyOrdered > ol.QtyDelivered "
				+ "		AND o.DatePromised BETWEEN ? AND ? "
				+ " ORDER BY o.DatePromised) "
				+ "UNION ALL	"
				+ "(SELECT r.DateRequired, SUM(rl.Qty) AS QtyOrdered "
				+ "		FROM M_Requisition r "
				+ " 	INNER JOIN M_RequisitionLine rl ON (r.M_Requisition_ID = rl.M_Requisition_ID) "
				+ " 	INNER JOIN M_Product mp ON (mp.M_Product_ID = rl.M_Product_ID) "
				+ " 	WHERE r.DocStatus = 'DR' "
				+ "		AND rl.M_Product_ID = ? "
				+ "		AND r.DateRequired BETWEEN ? AND ? "
				+ "		AND rl.IsActive = 'Y' "
				+ "		AND mp.AD_Client_ID = ? "
				+ "		AND r.C_DocType_ID = ? "
				+ "		AND mp.IsActive = 'Y' "
				+ " GROUP BY mp.M_Product_ID, r.M_Requisition_ID "
				+ "	ORDER BY mp.M_Product_ID, r.DateRequired)"
				+ ") AS QtyOrdered	"
				+ "GROUP BY DateRequired");
		//	Product quantity
		String sqlFromProduction = new String("SELECT DatePromised, ProductionQty AS Qty, M_Production_ID "
				+ " FROM M_Production "
				+ " WHERE Processed = 'N' "
				+ "	AND M_Product_ID = ? "
				+ "	AND DatePromised BETWEEN ? AND ? "
				+ "	AND (C_DocType_ID IS NULL OR C_DocType_ID != ?)"
				+ " ORDER BY DatePromised");
		String sql = (mrp.isBOM() 
				? sqlFromProduction 
						: sqlConfirmPORQ);

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//	
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, productID);
			pstmt.setTimestamp(2, getDateStart());
			pstmt.setTimestamp(3, getDateFinish());
			//	BOM query
			if (mrp.isBOM()) {
				pstmt.setInt(4, docTypePlannedOrder);
			} else {
				pstmt.setInt(4, productID);
				pstmt.setTimestamp(5, getDateStart());
				pstmt.setTimestamp(6, getDateFinish());
				pstmt.setInt(7, getAD_Client_ID());
				pstmt.setInt(8, docTypeMRPRequisition);
			}
			//	Query
			rs = pstmt.executeQuery();
			//	
			String productionsql = new String("SELECT p.M_Product_ID, SUM(QtyUsed) AS QtyUsed, mp.DatePromised "
					+ "FROM M_Production mp "
					+ "INNER JOIN M_ProductionLine pl ON (pl.M_Production_ID = mp.M_Production_ID) "
					+ "INNER JOIN M_Product p ON (p.M_Product_ID = pl.M_Product_ID) "
					+ "WHERE pl.IsActive='Y' "
					+ "AND pl.M_Production_ID = ? "
					+ "AND pl.IsEndProduct = 'N' "
					+ "AND p.IsPhantom = 'N' "
					+ "GROUP BY mp.M_Production_ID, p.M_Product_ID "
					+ "ORDER BY p.M_Product_ID");
			while (rs.next()) {
				Date datePromised = rs.getDate("DatePromised");
				BigDecimal qty = rs.getBigDecimal("Qty");

				if (mapConfirmQty.containsKey(datePromised)) {
					mapConfirmQty.put(datePromised, mapConfirmQty.get(datePromised).add(qty));
				} else {
					mapConfirmQty.put(datePromised, qty);
				}
				//	For BOM
				if (mrp.isBOM()) {
					PreparedStatement pstatement = null;
					ResultSet rset = null;
					try {
						pstatement = DB.prepareStatement(productionsql, get_TrxName());
						pstatement.setInt(1, rs.getInt("M_Production_ID"));
						//	
						rset = pstatement.executeQuery();
						while (rset.next()) {
							setQtyAsDemand(rset.getInt("M_Product_ID"), rset.getBigDecimal("QtyUsed"),
									rset.getTimestamp("DatePromised"));
						}
					} catch (SQLException e) {
						log.severe(e.getLocalizedMessage());
					} finally {
						DB.close(rset, pstatement);
						rset = null;
						pstatement = null;
					}
				}
			}
		} catch (SQLException e) {
			log.severe(e.getLocalizedMessage());
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		//	
		mrp.setMapConfirmProductQty(mapConfirmQty);
	}

	public void addAvailableInventory(int mProductId, BigDecimal qty) {
		availableInventory.put(mProductId, qty);
	}

	/**
	 * @author Sachin Bhimani
	 * @param miniMrpProduct
	 * @param line
	 * @param type
	 * @return miniM_ReplenishPlanLine
	 */
	private X_M_ReplenishPlanLine getX_M_ReplenishPlanLine(MiniMRPProduct miniMrpProduct, int line, String type) {
		X_M_ReplenishPlanLine miniMRP = new X_M_ReplenishPlanLine(getCtx(), 0, get_TrxName());
		miniMRP.setM_Product_ID(miniMrpProduct.getM_Product_ID());
		miniMRP.setM_Product_Category_ID(miniMrpProduct.getM_Product_Category_ID());
		miniMRP.setProductName(miniMrpProduct.getName());
		miniMRP.setLine(line);
		miniMRP.setM_ReplenishPlan_ID(getRecord_ID());
		miniMRP.setRecordType(type);
		miniMRP.setDateStart(getDateStart());
		miniMRP.setDateFinish(getDateFinish());
		return miniMRP;
	}


	public void calculateSuggestedOrders(MiniMRPProduct miniMrpProduct, Map<Integer, BigDecimal> totalDemandLine,
			Map<Integer, BigDecimal> totalSupplyLine, Map<Integer, BigDecimal> openingBalanceLine,
			Map<Integer, BigDecimal> closingBalanceLine) {
		//	
		int horizon = TimeUtil.getWeeksBetween(getDateStart(), getDateFinish());
		//	Loop
		for (int week = 1; week <= horizon; week++) {
			BigDecimal totalDemand = Env.ZERO;
			BigDecimal totalSupply = Env.ZERO;
			BigDecimal openingBalance = Env.ZERO;

			if (totalDemandLine.containsKey(week)) {
				totalDemand = totalDemandLine.get(week);
				if (totalDemand == null) {
					totalDemand = Env.ZERO;
				}
			}
			if (totalSupplyLine.containsKey(week)) {
				totalSupply = totalSupplyLine.get(week);
				if (totalSupply == null) {
					totalSupply = Env.ZERO;
				}
			}
			if (openingBalanceLine.containsKey(week)) {
				openingBalance = openingBalanceLine.get(week);
				if (openingBalance == null) {
					openingBalance = Env.ZERO;
				}
			}

			BigDecimal sugQty = totalDemand.subtract((totalSupply.add(openingBalance)));

			if (sugQty.compareTo(Env.ZERO) > 0) {
				openingBalanceLine.put(week + 1, Env.ZERO);
				closingBalanceLine.put(week, Env.ZERO);
			} else {
				openingBalanceLine.put(week + 1, (sugQty.negate()));
				closingBalanceLine.put(week, (sugQty.negate()));
			}
		}
	}

	/**
	 * Insert Order Demand
	 * @param miniMrpProduct
	 * @return
	 */
	public Map<Integer, BigDecimal> insertOrderDemands(MiniMRPProduct miniMrpProduct) {
		Map<Integer, Map<Integer, BigDecimal>> orderDemands = miniMrpProduct.getDemand();
		Map<Integer, BigDecimal> totalDemandLine = new HashMap<Integer, BigDecimal>();
		SimpleDateFormat format = DisplayType.getDateFormat(DisplayType.Date);
		// From MRP Demand MAP
		if (!orderDemands.isEmpty()) {
			for (Integer orderId : orderDemands.keySet()) {
				Map<Integer, BigDecimal> weeklyDemand = orderDemands.get(orderId);
				if (weeklyDemand.size() > 0) {
					X_M_ReplenishPlanLine miniMRP = getX_M_ReplenishPlanLine(miniMrpProduct, lineNo, X_M_ReplenishPlanLine.RECORDTYPE_Demand);
					miniMRP.setC_Order_ID(orderId);
					MOrder order = new MOrder(getCtx(), orderId, get_TrxName());
					//	
					miniMRP.setOrderInfo(order.getDocumentNo() + " - " + format.format(order.getDatePromised()));
					for (Integer week : weeklyDemand.keySet()) {
						BigDecimal qty = weeklyDemand.get(week);
						setWeeklyData(miniMRP, week, qty);
						if (totalDemandLine.containsKey(week)) {
							qty = qty.add(totalDemandLine.get(week));
						}
						totalDemandLine.put(week, qty);
					}
					miniMRP.saveEx();
				}
				lineNo += 10;
			}
		}
		// Get Planned Production Demand as per product wise.
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int paramCount = 1;
		try {
			String sql = new String("SELECT p.M_Production_ID, p.DocumentNo, p.DatePromised, SUM(QtyUsed) AS QtyUsed "
					+ "FROM M_Production p "
					+ "INNER JOIN M_ProductionLine pl ON (pl.M_Production_ID = p.M_Production_ID) "
					+ "WHERE p.Processed='N' "
					+ "AND pl.IsEndProduct = 'N' "
					+ "AND pl.M_Product_ID = ?"
					+ "AND p.C_DocType_ID=? "
					+ "AND DatePromised BETWEEN ? AND ? "
					+ "GROUP BY p.M_Production_ID, p.DocumentNo, p.DatePromised "
					+ "ORDER BY p.DatePromised");
			//	
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(paramCount++, miniMrpProduct.getM_Product_ID());
			pstmt.setInt(paramCount++, docTypePlannedOrder);
			pstmt.setTimestamp(paramCount++, getDateStart());
			pstmt.setTimestamp(paramCount++, getDateFinish());

			rs = pstmt.executeQuery();
			while (rs.next()) {
				insertProductionDemand(miniMrpProduct, totalDemandLine, rs.getInt("M_Production_ID"),
						rs.getString("DocumentNo"), rs.getTimestamp("DatePromised"), rs.getBigDecimal("QtyUsed"),
						"Planned");
			}
		} catch (Exception e) {
			log.severe(e.getLocalizedMessage());
			throw new AdempiereException(e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		//	
		List <MProduction> productionList = new Query(getCtx(), I_M_Production.Table_Name, "Processed = 'N' "
				+ "AND DatePromised BETWEEN ? AND ? "
				+ "AND (C_DocType_ID IS NULL OR C_DocType_ID != ?) "
				+ "AND EXISTS(SELECT 1 FROM M_ProductionLine pl "
				+ "			WHERE pl.M_Production_ID = M_Production.M_Production_ID"
				+ "			AND pl.IsEndProduct = 'N' "
				+ "			AND pl.M_Product_ID = ?)", get_TrxName())
				.setParameters(getDateStart(), getDateFinish(), docTypePlannedOrder, miniMrpProduct.getM_Product_ID())
				.setClient_ID()
				.list();
		//	Loop it
		productionList.stream().forEach(production -> {
			BigDecimal qty = DB.getSQLValueBD(get_TrxName(),
					"SELECT SUM(QtyUsed) "
					+ "FROM M_ProductionLine "
					+ "WHERE M_Production_ID = ? "
					+ "AND M_Product_ID = ?",
					production.getM_Product_ID(), miniMrpProduct.getM_Product_ID(), getAD_Client_ID());
			if (qty == null) {
				qty = Env.ZERO;
			}
			insertProductionDemand(miniMrpProduct, totalDemandLine, production.getM_Production_ID(),
					production.getDocumentNo(), production.getDatePromised(), qty, "Confirm");
		});
		//	
		if (!totalDemandLine.isEmpty())
			insertTotalLine(miniMrpProduct, totalDemandLine, X_M_ReplenishPlanLine.RECORDTYPE_TotalDemand);

		return totalDemandLine;
	}

	/**
	 * @param miniMrpProduct
	 * @param totalDemandLine
	 * @param mProduction_ID
	 * @param documentNo
	 * @param promisedDate
	 * @param qtyUsed
	 * @param prodType
	 */
	private void insertProductionDemand(MiniMRPProduct miniMrpProduct, Map<Integer, BigDecimal> totalDemandLine,
			int mProduction_ID, String documentNo, Timestamp promisedDate, BigDecimal qtyUsed, String prodType) {
		X_M_ReplenishPlanLine miniMRP = getX_M_ReplenishPlanLine(miniMrpProduct, lineNo, X_M_ReplenishPlanLine.RECORDTYPE_Demand);
		miniMRP.setM_Production_ID(mProduction_ID);
		String datePromised = null;
		int week = 0;

		datePromised = DisplayType.getDateFormat(DisplayType.Date).format(promisedDate);
		week = TimeUtil.getWeeksBetween(getDateStart(), promisedDate);
		//	
		miniMRP.setProductionInfo(documentNo + " - " + datePromised + " - " + prodType);

		setWeeklyData(miniMRP, week, qtyUsed);
		if (totalDemandLine.containsKey(week)) {
			BigDecimal totalDemand = totalDemandLine.get(week);
			if (totalDemand == null) {
				totalDemand = Env.ZERO;
			}
			qtyUsed = qtyUsed.add(totalDemand);
		}
		totalDemandLine.put(week, qtyUsed);
		miniMRP.saveEx();
		lineNo += 10;
	}

	/**
	 * Insert total line [Demand/Supply line]
	 * 
	 * @author Sachin Bhimani
	 * @param miniMrpProduct
	 * @param totalLine
	 * @param lineType
	 */
	public void insertTotalLine(MiniMRPProduct miniMrpProduct, Map<Integer, BigDecimal> totalLine, String lineType) {
		if (!totalLine.isEmpty()) {
			X_M_ReplenishPlanLine miniMRP = getX_M_ReplenishPlanLine(miniMrpProduct, lineNo, lineType);
			for (Integer week : totalLine.keySet()) {
				BigDecimal qty = totalLine.get(week);
				setWeeklyData(miniMRP, week, qty);
			}
			miniMRP.saveEx();
		}
		lineNo += 10;
	}

	/**
	 * Insert all supply lines
	 * 
	 * @author Sachin Bhimani
	 * @param miniMrpProduct
	 * @return totalSupplyLine
	 */
	public Map<Integer, BigDecimal> insertSupplyLines(MiniMRPProduct miniMrpProduct) {
		Map<Integer, BigDecimal> totalSupplyLine = new HashMap<Integer, BigDecimal>();
		Map<Integer, BigDecimal> subTotalSupplyLine = new HashMap<Integer, BigDecimal>();

		// Insert Production for Non Planned Orders (Confirmed) & Total Line
		insertSupplyLineMO(miniMrpProduct, totalSupplyLine, subTotalSupplyLine, false);
		insertTotalSupplyProductionLine(miniMrpProduct, subTotalSupplyLine, false);

		// Insert Production Supply for Planned Orders & Total Line
		subTotalSupplyLine.clear();
		insertSupplyLineMO(miniMrpProduct, totalSupplyLine, subTotalSupplyLine, true);
		insertTotalSupplyProductionLine(miniMrpProduct, subTotalSupplyLine, true);

		// Insert Purchase Order Confirmed Lines
		subTotalSupplyLine.clear();
		insertSupplyLinePO(miniMrpProduct, totalSupplyLine, subTotalSupplyLine, true);
		insertTotalSupplyLine(miniMrpProduct, subTotalSupplyLine, true);

		// Insert Requisition Lines
		subTotalSupplyLine.clear();
		insertSupplyLinePO(miniMrpProduct, totalSupplyLine, subTotalSupplyLine, false);
		insertTotalSupplyLine(miniMrpProduct, subTotalSupplyLine, false);

		// Insert Total Supply Line
		if (!totalSupplyLine.isEmpty()) {
			insertTotalLine(miniMrpProduct, totalSupplyLine, X_M_ReplenishPlanLine.RECORDTYPE_TotalSupply);
		}
		//	
		return totalSupplyLine;
	}

	/**
	 * Insert Production for Non Planned Orders (Confirmed) and Planned Orders
	 * 
	 * @author Sachin Bhimani
	 * @param miniMrpProduct
	 * @param totalSupplyLine
	 * @param subTotalSupplyLine
	 * @param isPlannedOrder
	 */
	public void insertSupplyLineMO(MiniMRPProduct miniMrpProduct, Map<Integer, BigDecimal> totalSupplyLine,
			Map<Integer, BigDecimal> subTotalSupplyLine, boolean isPlannedOrder) {
		Map<Integer, Map<Integer, BigDecimal>> supplyLineMO = new TreeMap<Integer, Map<Integer, BigDecimal>>(
				miniMrpProduct.getSupplyLineMO());
		SimpleDateFormat format = DisplayType.getDateFormat(DisplayType.Date);
		if (!supplyLineMO.isEmpty()) {
			for (Integer M_Production_ID : supplyLineMO.keySet()) {
				Map<Integer, BigDecimal> weeklyDemand = supplyLineMO.get(M_Production_ID);
				MProduction production = new MProduction(getCtx(), M_Production_ID, get_TrxName());
				if ((isPlannedOrder == false && production.getC_DocType_ID() != docTypePlannedOrder)
						|| (isPlannedOrder && production.getC_DocType_ID() == docTypePlannedOrder)) {
					if (weeklyDemand.size() > 0) {
						X_M_ReplenishPlanLine miniMRP = getX_M_ReplenishPlanLine(miniMrpProduct, lineNo, isPlannedOrder
								? X_M_ReplenishPlanLine.RECORDTYPE_PlannedProduction : X_M_ReplenishPlanLine.RECORDTYPE_ConfirmedProduction);
						miniMRP.setM_Production_ID(M_Production_ID);
						//	
						miniMRP.setProductionInfo(production.getDocumentNo() + " - " + format.format(production.getDatePromised()));
						for (Integer week : weeklyDemand.keySet()) {
							BigDecimal qty = weeklyDemand.get(week);
							setWeeklyData(miniMRP, week, qty);
							if (totalSupplyLine.containsKey(week)) {
								qty = qty.add(totalSupplyLine.get(week));
							}
							totalSupplyLine.put(week, qty);

							BigDecimal qtyProd = weeklyDemand.get(week);
							if (subTotalSupplyLine.containsKey(week)) {
								qtyProd = qtyProd.add(subTotalSupplyLine.get(week));
							}
							subTotalSupplyLine.put(week, qtyProd);
						}
						miniMRP.saveEx();
					}
					lineNo += 10;
				}
			}
		}
	}

	/**
	 * Insert Purchase Order and Requisition lines
	 * 
	 * @author Sachin Bhimani
	 * @param miniMrpProduct
	 * @param totalSupplyLine
	 * @param subTotalSupplyLine
	 * @param isPO
	 */
	public void insertSupplyLinePO(MiniMRPProduct miniMrpProduct, Map<Integer, BigDecimal> totalSupplyLine,
			Map<Integer, BigDecimal> subTotalSupplyLine, boolean isPO) {
		Map<Integer, Map<Integer, BigDecimal>> supplyLine;
		String typeSupply;
		if (isPO) {
			supplyLine = miniMrpProduct.getSupplyLinePO();
			typeSupply = X_M_ReplenishPlanLine.RECORDTYPE_Supply_Purchasing;
		} else {
			supplyLine = miniMrpProduct.getSupplyLineRQ();
			typeSupply = X_M_ReplenishPlanLine.RECORDTYPE_Supply_Requisition;
		}
		SimpleDateFormat format = DisplayType.getDateFormat(DisplayType.Date);
		if (!supplyLine.isEmpty()) {
			// columnID is C_Order_ID or M_Requisition_ID
			for (Integer columnID : supplyLine.keySet()) {
				Map<Integer, BigDecimal> weeklyDemand = supplyLine.get(columnID);
				if (weeklyDemand.size() > 0) {
					X_M_ReplenishPlanLine miniMRP = getX_M_ReplenishPlanLine(miniMrpProduct, lineNo, typeSupply);
					Timestamp datePromised;
					String docNo = null;
					if (isPO) {
						miniMRP.setC_Order_ID(columnID);
						MOrder order = new MOrder(getCtx(), columnID, get_TrxName());
						datePromised = order.getDatePromised();
						docNo = order.getDocumentNo();
					} else {
						miniMRP.setM_Requisition_ID(columnID);
						MRequisition req = new MRequisition(getCtx(), columnID, get_TrxName());
						datePromised = req.getDateRequired();
						docNo = req.getDocumentNo();
					}
					//	
					miniMRP.setOrderInfo(docNo + " - " + format.format(datePromised));
					for (Integer week : weeklyDemand.keySet()) {
						BigDecimal qty = weeklyDemand.get(week);
						setWeeklyData(miniMRP, week, qty);
						if (totalSupplyLine.containsKey(week)) {
							qty = qty.add(totalSupplyLine.get(week));
						}
						totalSupplyLine.put(week, qty);

						BigDecimal qtyProd = weeklyDemand.get(week);
						if (subTotalSupplyLine.containsKey(week)) {
							qtyProd = qtyProd.add(subTotalSupplyLine.get(week));
						}
						subTotalSupplyLine.put(week, qtyProd);
					}
					miniMRP.saveEx();
				}
				lineNo += 10;
			}
		}
	}

	/**
	 * Insert total Supply line, summation of confirmed purchase order and
	 * Requisition
	 * 
	 * @author Sachin Bhimani
	 * @param miniMrpProduct
	 * @param subTotalSupplyLine
	 * @param isPO
	 */
	public void insertTotalSupplyLine(MiniMRPProduct miniMrpProduct, Map<Integer, BigDecimal> subTotalSupplyLine,
			boolean isPO) {
		if (!subTotalSupplyLine.isEmpty()) {
			X_M_ReplenishPlanLine miniMRP = getX_M_ReplenishPlanLine(miniMrpProduct, lineNo, isPO 
					? X_M_ReplenishPlanLine.RECORDTYPE_TotalSupply_PO
					: X_M_ReplenishPlanLine.RECORDTYPE_TotalSupply_Requisition);

			// Insert Total Supply line.
			for (Integer week : subTotalSupplyLine.keySet()) {
				BigDecimal qty = subTotalSupplyLine.get(week);
				setWeeklyData(miniMRP, week, qty);
			}
			miniMRP.saveEx();
		}
		lineNo += 10;
	}

	/**
	 * Insert total supply of production line
	 * 
	 * @author Sachin Bhimani
	 * @param miniMrpProduct
	 * @param subTotalSupplyLine
	 * @param isPlanned
	 */
	public void insertTotalSupplyProductionLine(MiniMRPProduct miniMrpProduct,
			Map<Integer, BigDecimal> subTotalSupplyLine, boolean isPlanned) {
		if (!subTotalSupplyLine.isEmpty()) {
			X_M_ReplenishPlanLine miniMRP = getX_M_ReplenishPlanLine(miniMrpProduct, lineNo, isPlanned ? X_M_ReplenishPlanLine.RECORDTYPE_TotalPlannedProduction
					: X_M_ReplenishPlanLine.RECORDTYPE_TotalConfirmedProduction);

			// Insert Total Supply Production as per docType.
			for (Integer week : subTotalSupplyLine.keySet()) {
				BigDecimal qty = subTotalSupplyLine.get(week);
				setWeeklyData(miniMRP, week, qty);
			}
			miniMRP.saveEx();
		}
		lineNo += 10;
	}

	/**
	 * Insert Opening Balance Line
	 * 
	 * @author Sachin Bhimani
	 * @param miniMrpProduct
	 * @param openingBalanceLine
	 */
	public void insertOpeningBalanceLine(MiniMRPProduct miniMrpProduct, Map<Integer, BigDecimal> openingBalanceLine) {
		if (!openingBalanceLine.isEmpty()) {
			X_M_ReplenishPlanLine miniMRP = getX_M_ReplenishPlanLine(miniMrpProduct, 10, X_M_ReplenishPlanLine.RECORDTYPE_OpeningBalance
					+ (miniMrpProduct.isPhantom ? " - Phantom Product" : ""));
			for (Integer week : openingBalanceLine.keySet()) {
				setWeeklyData(miniMRP, week, openingBalanceLine.get(week));
			}
			miniMRP.saveEx();
		}
	}

	/**
	 * Insert closing Balance Line.
	 * 
	 * @author Sachin Bhimani
	 * @param miniMrpProduct
	 * @param closingBalanceLine
	 */
	public void insertClosingBalanceLine(MiniMRPProduct miniMrpProduct, Map<Integer, BigDecimal> closingBalanceLine) {
		if (!closingBalanceLine.isEmpty()) {
			X_M_ReplenishPlanLine miniMRP = getX_M_ReplenishPlanLine(miniMrpProduct, lineNo, X_M_ReplenishPlanLine.RECORDTYPE_ClosingBalance);
			for (Integer week : closingBalanceLine.keySet()) {
				setWeeklyData(miniMRP, week, closingBalanceLine.get(week));
			}
			miniMRP.saveEx();
		}
	}

	/**
	 * Create a MRP Lines as per product wise processing
	 * 
	 * @author Sachin Bhimani
	 * @param miniMrpProducts
	 */
	private void renderPeggingReport(Map<Integer, MiniMRPProduct> miniMrpProducts) {
		for (MiniMRPProduct miniMrpProduct : miniMrpProducts.values()) {
			lineNo = 20;
			Map<Integer, BigDecimal> openingBalanceLine = new HashMap<Integer, BigDecimal>();
			Map<Integer, BigDecimal> closingBalanceLine = new HashMap<Integer, BigDecimal>();
			if (availableInventory.containsKey(miniMrpProduct.getM_Product_ID())) {
				BigDecimal qty = availableInventory.get(miniMrpProduct.getM_Product_ID());
				openingBalanceLine.put(1, qty);// TODO view it
			}

			// Insert Demand lines & Calculate Total Demand line.
			Map<Integer, BigDecimal> totalDemandLine = insertOrderDemands(miniMrpProduct);

			// Insert SupplyLines - MO/PO & Total
			Map<Integer, BigDecimal> totalSupplyLine = insertSupplyLines(miniMrpProduct);

			calculateSuggestedOrders(miniMrpProduct, totalDemandLine, totalSupplyLine, openingBalanceLine,
					closingBalanceLine);

			// Insert Closing Balance Line
			insertClosingBalanceLine(miniMrpProduct, closingBalanceLine);

			// Insert Opening Balance Line
			insertOpeningBalanceLine(miniMrpProduct, openingBalanceLine);
		}
	}

	/**
	 * For Production
	 * @param miniMrpProducts
	 * @param productIds
	 */
	private void forOpenMO(Map<Integer, MiniMRPProduct> miniMrpProducts, Set<Integer> productIds) {
		//	SQL
		String sql = new String(
				"SELECT mprod.M_Production_ID, mprod.M_Product_ID, "
				+ "SUM(mprod.ProductionQty) AS OrderedQty, mprod.DatePromised "
				+ "FROM M_Production mprod "
				+ "WHERE mprod.IsActive='Y' "
				+ "AND mprod.Processed='N' "
				+ "AND mprod.DatePromised BETWEEN ? AND ? "
				+ "AND mprod.AD_Client_ID = ? "
				+ "AND mprod.M_Product_ID IN" + productIds.toString().replace('[','(').replace(']',')') + " "
				+ "GROUP BY mprod.M_Product_ID, mprod.M_Production_ID, mprod.DatePromised "
				+ "ORDER BY mprod.M_Product_ID, mprod.DatePromised");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int paramCount = 1;
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setTimestamp(paramCount++, getDateStart());
			pstmt.setTimestamp(paramCount++, getDateFinish());
			pstmt.setInt(paramCount++, getAD_Client_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int productionId = rs.getInt("M_Production_ID");
				int productId = rs.getInt("M_Product_ID");
				BigDecimal orderQty = rs.getBigDecimal("OrderedQty");
				Timestamp datePromised = rs.getTimestamp("DatePromised");
				//	
				int weekPromised = TimeUtil.getWeeksBetween(getDateStart(), datePromised);
				MiniMRPProduct product = miniMrpProducts.get(productId);
				product.setSupplyLineMO(productionId, weekPromised, orderQty);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
	}
	
	/**
	 * For Orders
	 * @param miniMrpProducts
	 * @param productIds
	 */
	private void forOpenPO(Map<Integer, MiniMRPProduct> miniMrpProducts, Set<Integer> productIds) {
		//	SQL
		String sql = new String(
				"SELECT co.C_Order_ID, col.M_Product_ID, SUM(col.QtyOrdered - col.QtyDelivered) AS OrderedQty, co.DatePromised "
				+ "FROM C_Order co "
				+ "INNER JOIN C_OrderLine col ON(co.C_Order_ID = col.C_Order_ID) "
				+ "WHERE co.IsSOTrx='N' "
				+ "AND co.DocStatus = 'CO' "
				+ "AND col.QtyOrdered > col.QtyDelivered "
				+ "AND col.DatePromised BETWEEN ? AND ? "
				+ "AND co.AD_Client_ID = ? "
				+ "AND col.M_Product_ID IN" + productIds.toString().replace('[','(').replace(']',')') + " "
				+ "GROUP BY col.M_Product_ID, co.C_Order_ID, co.DatePromised "
				+ "ORDER BY col.M_Product_ID, co.DatePromised");
		//	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int paramCount = 1;
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setTimestamp(paramCount++, getDateStart());
			pstmt.setTimestamp(paramCount++, getDateFinish());
			pstmt.setInt(paramCount++, getAD_Client_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int productId = rs.getInt("M_Product_ID");
				BigDecimal orderQty = rs.getBigDecimal("OrderedQty");
				Timestamp datePromised = rs.getTimestamp("DatePromised");
				//	
				int weekPromised = TimeUtil.getWeeksBetween(getDateStart(), datePromised);
				MiniMRPProduct product = miniMrpProducts.get(productId);
				int orderId = rs.getInt("C_Order_ID");
				product.setSupplyLinePO(orderId, weekPromised, orderQty);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
	}
	
	/**
	 * For Requisition
	 * @param miniMrpProducts
	 * @param productIds
	 */
	private void forOpenRQ(Map<Integer, MiniMRPProduct> miniMrpProducts, Set<Integer> productIds) {
		//	SQL
		String sql = new String("SELECT r.M_Requisition_ID, rl.M_Product_ID, SUM(rl.Qty) AS OrderedQty, r.DateRequired "
				+ "FROM M_Requisition r "
				+ "INNER JOIN M_RequisitionLine rl ON (r.M_Requisition_ID = rl.M_Requisition_ID) "
				+ "WHERE r.DocStatus = 'DR' "
				+ "AND r.DateRequired BETWEEN ? AND ? "
				+ "AND rl.IsActive = 'Y' "
				+ "AND r.AD_Client_ID = ? "
				+ "AND rl.M_Product_ID IN" + productIds.toString().replace('[','(').replace(']',')') + " "
				+ "AND r.C_DocType_ID = ? "
				+ "GROUP BY rl.M_Product_ID, r.M_Requisition_ID, r.DateRequired "
				+ "ORDER BY rl.M_Product_ID, r.DateRequired");
		//	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int paramCount = 1;
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setTimestamp(paramCount++, getDateStart());
			pstmt.setTimestamp(paramCount++, getDateFinish());
			pstmt.setInt(paramCount++, getAD_Client_ID());
			pstmt.setInt(paramCount++, docTypeMRPRequisition);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int productId = rs.getInt("M_Product_ID");
				BigDecimal orderQty = rs.getBigDecimal("OrderedQty");
				Timestamp dateRequired = rs.getTimestamp("DateRequired");
				//	
				int weekPromised = TimeUtil.getWeeksBetween(getDateStart(), dateRequired);
				MiniMRPProduct product = miniMrpProducts.get(productId);
				int requisitionId = rs.getInt("M_Requisition_ID");
				product.setSupplyLineRQ(requisitionId, weekPromised, orderQty);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
	}
	
	/**
	 * @author Sachin Bhimani
	 * @param miniMrpProducts
	 * @param productIds
	 * @param type
	 */
	private void doRunOpenOrders(Map<Integer, MiniMRPProduct> miniMrpProducts, Set<Integer> productIds, String type) {
		if (type.equals(TYPE_MO)) {
			forOpenMO(miniMrpProducts, productIds);
		} else if (type.equals(TYPE_PO)) {
			forOpenPO(miniMrpProducts, productIds);
		} else if (type.equals(TYPE_RQ)) {
			forOpenRQ(miniMrpProducts, productIds);
		}
	}

	/**
	 * @param miniMrpProducts
	 * @param productIds
	 * @param productSO
	 */
	private void doRunProductsSO(Map<Integer, MiniMRPProduct> miniMrpProducts, Set<Integer> productIds) {
		//	Get promised
		List<MOrderLine> lines = new Query(getCtx(), I_C_OrderLine.Table_Name, "DatePromised BETWEEN ? AND ?"
				+ " AND (COALESCE(QtyOrdered, 0) - COALESCE(QtyDelivered, 0)) > 0 "
				+ " AND M_Product_ID IN" + productIds.toString().replace('[','(').replace(']',')')
				+ " AND EXISTS(SELECT 1 FROM C_Order o "
				+ "				WHERE o.C_Order_ID = C_OrderLine.C_Order_ID"
				+ "				AND o.DocStatus = 'CO'"
				+ "				AND o.IsSOTrx = 'Y')", get_TrxName())
				.setParameters(getDateStart(), getDateFinish())
				.setClient_ID()
				.setOrderBy(I_C_OrderLine.COLUMNNAME_DatePromised)
				.list();
		//	Loop it
		lines.stream()
				.filter(orderLinePhantom -> !orderLinePhantom.getM_Product().isPhantom())
				.forEach(orderLine -> {
			if (miniMrpProducts.containsKey(orderLine.getM_Product_ID())) {
				MiniMRPProduct mrpProduct = miniMrpProducts.get(orderLine.getM_Product_ID());
				//	
				setQtyAsDemand(orderLine.getM_Product_ID(), orderLine.getQtyReserved(), orderLine.getDatePromised());
				int weekPromised = TimeUtil.getWeeksBetween(getDateStart(), orderLine.getDatePromised());
				mrpProduct.explodeDemand(orderLine.getC_Order_ID(), orderLine.getQtyReserved(), weekPromised, miniMrpProducts);
			} else {
				log.log(Level.SEVERE, "Error in miniMrpProducts setup.");
			}
		});
	}

	/**
	 * Create Product Demand map.
	 * 
	 * @param mProductID
	 * @param qty
	 * @param date
	 */
	private void setQtyAsDemand(int mProductID, BigDecimal qty, Date date)
	{
		if (mapDemand.containsKey(date))
		{
			Map<Integer, BigDecimal> mapOrderQty = mapDemand.get(date);
			if (mapOrderQty.containsKey(mProductID))
				mapOrderQty.put(mProductID, mapOrderQty.get(mProductID).add(qty));
			else
				mapOrderQty.put(mProductID, qty);
		}
		else
		{
			Map<Integer, BigDecimal> mapOrderQty = new HashMap<Integer, BigDecimal>();
			mapOrderQty.put(mProductID, qty);
			mapDemand.put(date, mapOrderQty);
		}
	}
	
	/**
	 * Get Replenish
	 * @param warehouseId
	 * @param productId
	 * @return
	 */
	private List<MReplenish> getReplenishList(int warehouseId, int productId) {
		StringBuffer whereClause = new StringBuffer("AD_Client_ID = ?");
		ArrayList<Object> param = new ArrayList<Object>();
		param.add(getAD_Client_ID());
		//	Warehouse
		whereClause.append(" AND M_Warehouse_ID = ?");
		param.add(warehouseId);
		//	Add Optional
		if(productId != 0) {
			whereClause.append(" AND M_Product_ID = ?");
			param.add(productId);
		}
		//	Add Exist for Replenish
		whereClause.append(" AND EXISTS(SELECT 1 FROM M_Product p "
				+ "WHERE p.M_Product_ID = M_Replenish.M_Product_ID "
				+ "AND p.IsActive = ? "
				+ "AND p.IsStocked = ?)");
		//	Active
		param.add("Y");
		//	Stocked
		param.add("Y");
		//	
		List<MReplenish> productReplenish = new Query(getCtx(), I_M_Replenish.Table_Name, whereClause.toString(), get_TrxName())
				.setParameters(param)
				.setOnlyActiveRecords(true)
				.list();
		//	Replenish
		return productReplenish;
	}

	/**
	 * This method will fetch all the products based on selected category or the
	 * product. This will process BOMLine if product is finished good. It will
	 * fill the miniMRPProducts list & id of each product(including BOMlines) in
	 * productIds
	 * 
	 * @param miniMrpProducts
	 * @param productIds
	 */
	private void generateProductInfo(Map<Integer, MiniMRPProduct> miniMrpProducts, Set<Integer> productIds) {
		List<MReplenish> productReplenish = getReplenishList(getWarehouseId(), getProductId());
		//	Validate Replenish
		if(productReplenish.size() == 0) {
			throw new AdempiereException("@M_Replenish_ID@ @NotFound@");
		}
		//	Add to list
		productReplenish.stream().forEach(replenish -> {
			if (!miniMrpProducts.containsKey(replenish.getM_Product_ID())) {
				MiniMRPProduct miniMrpProduct = addProductToProcess(replenish, miniMrpProducts, productIds);
				//	If Product is FG(Finished Goods) calculate it's BOMlines
				if (miniMrpProduct.isBOM() && miniMrpProduct.isVerified()) {
					processBOMLines(miniMrpProducts, productIds, miniMrpProduct.getM_Product_ID());
				}
			}
		});
	}

	/**
	 * @param replenish
	 * @param miniMrpProducts
	 * @return
	 * @throws SQLException
	 */
	private MiniMRPProduct addProductToProcess(MReplenish replenish, Map<Integer, MiniMRPProduct> miniMrpProducts, Set<Integer> productIds) {
		MProduct product = MProduct.get(getCtx(), replenish.getM_Product_ID());
		//	Get current vendor
		MProductPO[] productPO = MProductPO.getOfProduct(getCtx(), product.getM_Product_ID(), get_TrxName());
		int bPartnerId = 0;
		//	Get Business Partner
		if(productPO.length > 0) {
			bPartnerId = productPO[0].getC_BPartner_ID();
		}
		BigDecimal levelMin = replenish.getLevel_Min();
		BigDecimal availableInventory = MStorage.getQtyAvailable(getWarehouseId(), 0, product.getM_Product_ID(), 0, get_TrxName());
		if (availableInventory == null) {
			availableInventory = Env.ZERO;
		}
		//	Phantom
		if (product.isPhantom()) {
			levelMin = Env.ZERO;
			availableInventory = Env.ZERO;
		}
		//	Get Product Price
		MProductPricing price = new MProductPricing(product.getM_Product_ID(), bPartnerId, levelMin, false, get_TrxName());
		price.setM_PriceList_ID(priceListId);
		price.calculatePrice();
		//	
		MiniMRPProduct miniMrpProduct = new MiniMRPProduct(product.getM_Product_ID());
		miniMrpProduct.setAvailableQty(availableInventory);
		miniMrpProduct.setName(product.getName());
		miniMrpProduct.setM_Product_Category_ID(product.getM_Product_Category_ID());
		miniMrpProduct.setProjectedLeadTime(0);
		miniMrpProduct.setBOM(product.isBOM());
		miniMrpProduct.setVerified(product.isVerified());
		miniMrpProduct.setPurchased(product.isPurchased());
		miniMrpProduct.setPhantom(product.isPhantom());
		miniMrpProduct.setC_BPartner_ID(bPartnerId);
		miniMrpProduct.setPriceActual(price.getPriceList());
		miniMrpProduct.setQtyBatchSize(replenish.getQtyBatchSize());
		miniMrpProduct.setLevel_Min(levelMin);
		miniMrpProduct.setReplenishTypeMRPCalculated(replenish.getReplenishType()
				.equals(X_M_Replenish.REPLENISHTYPE_ReplenishPlanCalculated));
		if (miniMrpProduct.isReplenishTypeMRPCalculated())
			miniMrpProduct.setQtyOnHand(availableInventory.subtract(levelMin));
		else
			miniMrpProduct.setQtyOnHand(availableInventory);

		// Check product QTY is negative then create demand.
		if (miniMrpProduct.getQtyOnHand().compareTo(Env.ZERO) < 0 && !product.isPhantom()) {
			setQtyAsDemand(product.getM_Product_ID(), miniMrpProduct.getQtyOnHand().negate(), getDateStart());
			miniMrpProduct.setQtyOnHand(Env.ZERO);
		}

		// Manage Inventory & ProductId blueprint here.
		productIds.add(product.getM_Product_ID());
		miniMrpProducts.put(product.getM_Product_ID(), miniMrpProduct);
		addAvailableInventory(product.getM_Product_ID(), availableInventory);

		// Retrieve Confirmed Product QTY. If non BOM product then retrieve all
		// requisition data for docType is 'MRP Requisition'.
		setConfirmProductQty(miniMrpProduct);

		return miniMrpProduct;
	}

	/**
	 * @param miniMrpProduct
	 * @param parentProduct
	 * @param qtyBom
	 */
	private void explodeRequiredMaterials(MiniMRPProduct miniMrpProduct, MiniMRPProduct parentProduct, BigDecimal qtyBom) {
		Map<Integer, BigDecimal> requiredProdMaterials = miniMrpProduct.getRequiredProdMaterials();
		for (Integer mProdId : requiredProdMaterials.keySet()) {
			BigDecimal qty = requiredProdMaterials.get(mProdId);
			parentProduct.addMaterials(mProdId, qtyBom.multiply(qty));
		}
	}

	/**
	 * Process of BOM Product lines
	 * 
	 * @author Sachin Bhimani
	 * @param miniMrpProducts
	 * @param productIds
	 * @param M_Product_ID
	 */
	public void processBOMLines(Map<Integer, MiniMRPProduct> miniMrpProducts, Set<Integer> productIds, int M_Product_ID) {
		MProduct product = MProduct.get(getCtx(), M_Product_ID);
		//	
		List<MPPProductBOM> boms = MPPProductBOM.getProductBOMs(product);
		if(boms.size() > 0) {
			for(MPPProductBOMLine line : boms.get(0).getLines()) {
				List<MReplenish> productReplenish = getReplenishList(getWarehouseId(), getProductId());
				//	Loop it
				productReplenish.stream().forEach(replenish -> {
					BigDecimal qtyBom = line.getQtyBOM();

					MiniMRPProduct parentProduct = miniMrpProducts.get(M_Product_ID);
					parentProduct.addMaterials(line.getM_Product_ID(), qtyBom);

					MiniMRPProduct miniMrpProduct = null;

					// If material is already exploded.
					if (miniMrpProducts.containsKey(line.getM_Product_ID())) {
						miniMrpProduct = miniMrpProducts.get(line.getM_Product_ID());
						explodeRequiredMaterials(miniMrpProduct, parentProduct, qtyBom);
					} else {
						miniMrpProduct = addProductToProcess(replenish, miniMrpProducts, productIds);
						if (miniMrpProduct.isBOM() && miniMrpProduct.isVerified())
						{
							processBOMLines(miniMrpProducts, productIds, line.getM_Product_ID());
							explodeRequiredMaterials(miniMrpProduct, parentProduct, qtyBom);
						}
					}
				});
			}
		}
	}

	class MiniMRPProduct
	{
		private MiniMRPProduct							parent;

		private int										M_Product_ID;
		private int										projectedLeadTimeInWeek	= 1;
		private int										M_Product_Category_ID;
		private int										projectedLeadTime;
		private int										C_BPartner_ID;
		private String									name;
		private boolean									isBOM;
		private boolean									isVerified;
		private boolean									isPurchased;
		private boolean									isPhantom;
		private boolean									isReplenishTypeMRPCalculated;
		private BigDecimal								availableQty;
		private BigDecimal								qtyBal;
		private BigDecimal								qtyOnHand				= Env.ZERO;
		private BigDecimal								priceActual				= Env.ZERO;
		private BigDecimal								qtyBatchSize			= Env.ZERO;
		private BigDecimal								level_Min				= Env.ZERO;
		private BigDecimal								extraQtyPlanned			= Env.ZERO;
		private Map<Integer, BigDecimal>				requiredProdMaterials	= new HashMap<Integer, BigDecimal>();
		private Map<Integer, Map<Integer, BigDecimal>>	supplyLinePO			= new TreeMap<Integer, Map<Integer, BigDecimal>>();
		private Map<Integer, Map<Integer, BigDecimal>>	supplyLineMO			= new TreeMap<Integer, Map<Integer, BigDecimal>>();
		private Map<Integer, Map<Integer, BigDecimal>>	supplyLineRQ			= new TreeMap<Integer, Map<Integer, BigDecimal>>();
		private Map<Integer, Map<Integer, BigDecimal>>	demand					= new LinkedHashMap<Integer, Map<Integer, BigDecimal>>();
		private Map<Date, BigDecimal>					mapConfirmedProductQty	= new HashMap<Date, BigDecimal>();

		public String getName()
		{
			return name;
		}

		public void setName(String name)
		{
			this.name = name;
		}

		public Map<Integer, Map<Integer, BigDecimal>> getSupplyLinePO()
		{
			return supplyLinePO;
		}

		public Map<Integer, Map<Integer, BigDecimal>> getSupplyLineMO()
		{
			return supplyLineMO;
		}

		public Map<Integer, Map<Integer, BigDecimal>> getSupplyLineRQ()
		{
			return supplyLineRQ;
		}

		public boolean equals(Object obj)
		{
			if (!(obj instanceof MiniMRPProduct))
				return false;
			if (obj == this)
				return true;

			MiniMRPProduct rhs = (MiniMRPProduct) obj;
			return M_Product_ID == rhs.M_Product_ID;
		}

		public void addMaterials(int productId, BigDecimal qty)
		{
			if (requiredProdMaterials.containsKey(productId))
				qty = qty.add(requiredProdMaterials.get(productId));
			requiredProdMaterials.put(productId, qty);
		}

		public void addDemand(int mOrderID, BigDecimal netRequiredQty, int projectdOrderWeek) {
			Map<Integer, BigDecimal> weekDemand = getOrderDemand(mOrderID);
			weekDemand.put(projectdOrderWeek, netRequiredQty);
		}

		public void explodeDemand(int mOrderID, BigDecimal orderQty, int weekPromised,
				Map<Integer, MiniMRPProduct> miniMrpProducts) {
			BigDecimal netRequiredQty = orderQty; // explod(orderQty);
			// int projectdOrderWeek = getOrderProjectedWeek(weekPromised);
			addDemand(mOrderID, netRequiredQty, weekPromised);
			// explodMaterialDemand(mOrderID, orderQty, weekPromised,
			// miniMrpProducts);
		}

		public Map<Integer, Map<Integer, BigDecimal>> getDemand() {
			return demand;
		}

		public Map<Integer, BigDecimal> getOrderDemand(int mOrderID) {
			Map<Integer, BigDecimal> weekDemand = null;
			if (demand.containsKey(mOrderID)) {
				weekDemand = demand.get(mOrderID);
			} else {
				weekDemand = new HashMap<Integer, BigDecimal>();
				demand.put(mOrderID, weekDemand);
			}
			return weekDemand;
		}
		
		public void setSupplyLinePO(int c_order_id, int week, BigDecimal openPOqty) {
			Map<Integer, BigDecimal> weekDemand = null;
			if (supplyLinePO.containsKey(c_order_id)) {
				weekDemand = supplyLinePO.get(c_order_id);
				openPOqty = openPOqty.add(weekDemand.get(week));
				weekDemand.put(week, openPOqty);
			} else {
				weekDemand = new HashMap<Integer, BigDecimal>();
				weekDemand.put(week, openPOqty);
			}
			supplyLinePO.put(c_order_id, weekDemand);
		}

		public void setSupplyLineMO(int m_production_id, int week, BigDecimal openMOqty) {
			Map<Integer, BigDecimal> weekDemand = null;
			if (supplyLineMO.containsKey(m_production_id)) {
				weekDemand = supplyLineMO.get(m_production_id);
				openMOqty = openMOqty.add(weekDemand.get(week));
				weekDemand.put(week, openMOqty);
			} else {
				weekDemand = new HashMap<Integer, BigDecimal>();
				weekDemand.put(week, openMOqty);
			}
			supplyLineMO.put(m_production_id, weekDemand);
		}

		public void setSupplyLineRQ(int m_requisition_id, int week, BigDecimal openRQqty)
		{
			Map<Integer, BigDecimal> weekDemand = null;
			if (supplyLineRQ.containsKey(m_requisition_id))
			{
				weekDemand = supplyLineRQ.get(m_requisition_id);
				openRQqty = openRQqty.add(weekDemand.get(week));
				weekDemand.put(week, openRQqty);
			}
			else
			{
				weekDemand = new HashMap<Integer, BigDecimal>();
				weekDemand.put(week, openRQqty);
			}
			supplyLineRQ.put(m_requisition_id, weekDemand);
		}

		public BigDecimal explod(BigDecimal totalRequiredQty)
		{
			if (getQtyBal().compareTo(Env.ZERO) != 0)
			{
				BigDecimal avaibleInventory = getAvailableQty();
				if (avaibleInventory.compareTo(totalRequiredQty) > 0)
				{
					setAvailableQty(avaibleInventory.subtract(totalRequiredQty));
					totalRequiredQty = Env.ZERO;
				}
				else
				{
					totalRequiredQty = totalRequiredQty.subtract(avaibleInventory);
					setAvailableQty(Env.ZERO);
				}
			}
			return totalRequiredQty;
		}

		public boolean hasParent()
		{
			return parent != null;
		}

		public int getM_Product_ID()
		{
			return M_Product_ID;
		}

		public MiniMRPProduct(int M_Product_ID)
		{
			this.M_Product_ID = M_Product_ID;
		}

		public boolean isVerified()
		{
			return isVerified;
		}

		public void setVerified(boolean isVerified)
		{
			this.isVerified = isVerified;
		}

		public int getProjectedLeadTime()
		{
			return projectedLeadTime;
		}

		public int getProjectedLeadTimeInWeek()
		{
			return projectedLeadTimeInWeek;
		}

		public void setProjectedLeadTime(int projectedLeadTime)
		{
			this.projectedLeadTime = projectedLeadTime;

			if (!isBOM()) // For other "project Lead Time in Week" will be 1
							// week flat.
			{
				if (projectedLeadTime > 0)
				{
					float projectedLTimeInWeek = projectedLeadTime / 7f;
					if ((projectedLTimeInWeek - (int) projectedLTimeInWeek) > 0)
					{
						setProjectedLeadTimeInWeek(((int) projectedLTimeInWeek) + 1);
					}
					else
					{
						setProjectedLeadTimeInWeek(((int) projectedLTimeInWeek));
					}
				}
			}
		}

		public void setProjectedLeadTimeInWeek(int projectedLeadTimeWeek)
		{
			this.projectedLeadTimeInWeek = projectedLeadTimeWeek;
		}

		public BigDecimal getAvailableQty()
		{
			return availableQty;
		}

		public void setAvailableQty(BigDecimal availableQty)
		{
			this.availableQty = availableQty;
			setQtyBal(availableQty);
		}

		public int getM_Product_Category_ID()
		{
			return M_Product_Category_ID;
		}

		public void setM_Product_Category_ID(int m_Product_Category_ID)
		{
			M_Product_Category_ID = m_Product_Category_ID;
		}

		public Map<Integer, BigDecimal> getRequiredProdMaterials()
		{
			return requiredProdMaterials;
		}

		public boolean isBOM()
		{
			return isBOM;
		}

		public void setBOM(boolean isBOM)
		{
			this.isBOM = isBOM;
		}

		public BigDecimal getQtyBal()
		{
			return qtyBal;
		}

		public void setQtyBal(BigDecimal qtyBal)
		{
			this.qtyBal = qtyBal;
		}

		public BigDecimal getQtyOnHand()
		{
			return qtyOnHand;
		}

		public void setQtyOnHand(BigDecimal qtyOnHand)
		{
			this.qtyOnHand = qtyOnHand;
		}

		public Map<Date, BigDecimal> getMapConfirmProductQty()
		{
			return mapConfirmedProductQty;
		}

		public void setMapConfirmProductQty(Map<Date, BigDecimal> mapConfirmProductionQty)
		{
			this.mapConfirmedProductQty = mapConfirmProductionQty;
		}

		public void addConfirmProductionQty(Date date, BigDecimal qty)
		{
			if (mapConfirmedProductQty.containsKey(date))
				qty = mapConfirmedProductQty.get(date).add(qty);
			mapConfirmedProductQty.put(date, qty);
		}

		public boolean isPurchased()
		{
			return isPurchased;
		}

		public void setPurchased(boolean isPurchased)
		{
			this.isPurchased = isPurchased;
		}

		public int getC_BPartner_ID()
		{
			return C_BPartner_ID;
		}

		public void setC_BPartner_ID(int c_BPartner_ID)
		{
			C_BPartner_ID = c_BPartner_ID;
		}

		public BigDecimal getPriceActual()
		{
			return priceActual;
		}

		public void setPriceActual(BigDecimal priceActual)
		{
			this.priceActual = priceActual;
		}

		public boolean isPhantom()
		{
			return isPhantom;
		}

		public void setPhantom(boolean isPhantom)
		{
			this.isPhantom = isPhantom;
		}

		public BigDecimal getQtyBatchSize()
		{
			return qtyBatchSize;
		}

		public void setQtyBatchSize(BigDecimal qtyBatchSize)
		{
			this.qtyBatchSize = qtyBatchSize;
		}

		public BigDecimal getLevel_Min()
		{
			return level_Min;
		}

		public void setLevel_Min(BigDecimal level_Min)
		{
			this.level_Min = level_Min;
		}

		public BigDecimal getExtraQtyPlanned()
		{
			return extraQtyPlanned;
		}

		public void setExtraQtyPlanned(BigDecimal extraQtyPlanned)
		{
			this.extraQtyPlanned = extraQtyPlanned;
		}

		public boolean isReplenishTypeMRPCalculated()
		{
			return isReplenishTypeMRPCalculated;
		}

		public void setReplenishTypeMRPCalculated(boolean isReplenishTypeMRPCalculated)
		{
			this.isReplenishTypeMRPCalculated = isReplenishTypeMRPCalculated;
		}
	}

	/**
	 * @param week
	 * @param qty
	 */
	public void setWeeklyData(X_M_ReplenishPlanLine miniMRP, int week, BigDecimal qty)
	{
		switch (week) {
			case 0:
				miniMRP.setWeek1(qty);
				break;
			case 1:
				miniMRP.setWeek2(qty);
				break;
			case 2:
				miniMRP.setWeek3(qty);
				break;
			case 3:
				miniMRP.setWeek4(qty);
				break;
			case 4:
				miniMRP.setWeek5(qty);
				break;
			case 5:
				miniMRP.setWeek6(qty);
				break;
			case 6:
				miniMRP.setWeek7(qty);
				break;
			case 7:
				miniMRP.setWeek8(qty);
				break;
			case 8:
				miniMRP.setWeek9(qty);
				break;
			case 9:
				miniMRP.setWeek10(qty);
				break;
			case 10:
				miniMRP.setWeek11(qty);
				break;
			case 11:
				miniMRP.setWeek12(qty);
				break;
			case 12:
				miniMRP.setWeek13(qty);
				break;
			case 13:
				miniMRP.setWeek14(qty);
				break;
			case 14:
				miniMRP.setWeek15(qty);
				break;
			case 15:
				miniMRP.setWeek16(qty);
				break;
			case 16:
				miniMRP.setWeek17(qty);
				break;
			case 17:
				miniMRP.setWeek18(qty);
				break;
			case 18:
				miniMRP.setWeek19(qty);
				break;
			case 19:
				miniMRP.setWeek20(qty);
				break;
			case 20:
				miniMRP.setWeek21(qty);
				break;
			case 21:
				miniMRP.setWeek22(qty);
				break;
			case 22:
				miniMRP.setWeek23(qty);
				break;
			case 23:
				miniMRP.setWeek24(qty);
				break;
			default:
				break;
		}
	}

}
