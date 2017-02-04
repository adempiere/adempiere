package org.adempiere.process;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.compiere.model.MReplenishPlan;
import org.compiere.model.MOrder;
import org.compiere.model.MProduct;
import org.compiere.model.MProduction;
import org.compiere.model.MRequisition;
import org.compiere.model.X_M_ReplenishPlanLine;
import org.compiere.process.ProcessInfoLog;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

import com.mchange.v2.c3p0.C3P0ProxyConnection;

/**
 * CalculateReplenishPlan.Java
 * 
 * @author hitesh.panchani, www.logilite.com
 */
public class CalculateReplenishPlan extends SvrProcess
{
	private static final String			TYPE_CLOSING_BALANCE_LINE				= "Closing Balance";
	private static final String			TYPE_OPENING_BALANCE_LINE				= "Opening Balance";
	private static final String			TYPE_SUPPLY_LINE_PO						= "Supply - Purchasing";
	private static final String			TYPE_SUPPLY_LINE_RQ						= "Supply - Requisition";
	private static final String			TYPE_SUPPLY_LINE_MO_PLANNED				= "Planned Production";
	private static final String			TYPE_SUPPLY_LINE_MO_NONPLANNED			= "Confirmed Production";
	private static final String			TYPE_TOTAL_DEMAND_LINE					= "Total Demand";
	private static final String			TYPE_TOTAL_SUPPLY_LINE					= "Total Supply";
	private static final String			TYPE_TOTAL_SUPPLY_LINE_PO				= "Total Supply - PO";
	private static final String			TYPE_TOTAL_SUPPLY_LINE_RQ				= "Total Supply - Requisition";
	private static final String			TYPE_TOTAL_SUPPLY_PLANNED_LINE			= "Total Planned Production";
	private static final String			TYPE_TOTAL_SUPPLY_NONPLANNED_LINE		= "Total Confirmed Production";
	private static final String			TYPE_PRODUCT_ORDER_DEMAND				= "Demand";
	private static final String			REPLENISH_TYPE_MRP_CALCULATED			= "4";
	private static final String			TYPE_RQ									= "RQ";
	private static final String			TYPE_PO									= "PO";
	private static final String			TYPE_MO									= "MO";
	private int							lineNo									= 10;
	int									countProd								= 0;
	int									countReq								= 0;
	private int							mrpRunID								= 0;
	private int							START_WEEK;
	private int							END_WEEK;																																											// Week
	private int							AD_Client_ID;
	private int							AD_Org_ID;
	private int							AD_User_ID;
	private int							M_WarehouseID;
	private int							M_LocatorID;
	private int							docType_PlannedOrder;
	private int							docType_ConfirmedOrder;
	private int							docType_PurchaseOrder;
	private int							docType_MRPRequisition;
	private int							M_PriceList_ID;
	private Timestamp					dateFrom;
	private Timestamp					dateTo;
	private Calendar					calendar								= Calendar.getInstance();

	private Properties					ctx;
	private String						trx;
	private int							p_M_Product_ID =0;
	
	// Available Inventory - ProductID, Qty
	private Map<Integer, BigDecimal>	availableInventory						= new TreeMap<Integer, BigDecimal>();

	// Map Demand for SalesOrder_DatePromise, ProductID, RequiredQTY.
	Map<Date, Map<Integer, BigDecimal>>	mapDemand								= new TreeMap<Date, Map<Integer, BigDecimal>>();

	// map use for DatePromise for create planned Production, Product reference,
	// planned qty [ProductID, RequiredDate, DemandQty]
	Map<Integer, Map<Date, BigDecimal>>	mapRequirePlanningQty					= new TreeMap<Integer, Map<Date, BigDecimal>>();

	StringBuilder						infoMsg									= new StringBuilder();
	StringBuilder						productionDocs							= new StringBuilder();
	StringBuilder						requisitionDocs							= new StringBuilder();

	private static String				SQL_PRODUCTWISE_CONFIRM_PRODUCTION_QTY	= " SELECT DatePromised, ProductionQty AS Qty, M_Production_ID FROM M_Production "
																						+ " WHERE Processed = 'N' AND M_Product_ID = ? AND DatePromised BETWEEN ? AND ? AND (C_DocType_ID IS NULL OR C_DocType_ID != ? )"
																						+ " ORDER BY DatePromised ";

	private static String				SQL_PRODUCTWISE_CONFIRM_PO_RQ_QTY		= " SELECT DateRequired AS DatePromised, SUM(Qty) AS Qty FROM (( "
																						+ " SELECT ol.DatePromised AS DateRequired, ol.QtyOrdered - QtyDelivered AS Qty	FROM C_Order o "
																						+ " INNER JOIN C_OrderLine ol ON (ol.C_Order_ID = o.C_Order_ID AND ol.M_Product_ID = ?) "
																						+ " WHERE o.DocStatus IN ('CO', 'CL') AND o.IsSoTrx = 'N' AND ol.QtyOrdered > ol.QtyDelivered AND o.DatePromised BETWEEN ? AND ? "
																						+ " ORDER BY o.DatePromised )		UNION ALL	( SELECT r.DateRequired, SUM(rl.Qty) AS QtyOrdered	FROM M_Requisition r "
																						+ " INNER JOIN M_RequisitionLine rl ON (r.M_Requisition_ID = rl.M_Requisition_ID AND rl.IsActive = 'Y' AND rl.M_Product_ID = ?) "
																						+ " INNER JOIN M_Product mp ON (mp.M_Product_ID = rl.M_Product_ID AND mp.IsActive = 'Y') "
																						+ " WHERE r.DocStatus IN ('DR') AND r.DateRequired BETWEEN ? AND ? AND mp.AD_Client_ID = ? AND r.C_DocType_ID = ? "
																						+ " GROUP BY mp.M_Product_ID, r.M_Requisition_ID	ORDER BY mp.M_Product_ID, r.DateRequired )) AS qtyOrdered	GROUP BY DateRequired ";

	private static String				SQL_GET_PRODUCTION_SUBPRODUCTWISE		= "SELECT DISTINCT p.M_Production_ID		FROM M_Production p "
																						+ " INNER JOIN M_ProductionLine pl ON (pl.M_Production_ID = p.M_Production_ID AND pl.IsEndProduct = 'N' AND pl.M_Product_ID = ?) "
																						+ " WHERE p.Processed = 'N' AND p.DatePromised BETWEEN ? AND ? AND (C_DocType_ID IS NULL OR C_DocType_ID != ?) ";

	public static String				SQL_GET_ISO_WEEKNO						= "SELECT ( CASE WHEN TO_CHAR(?::DATE, 'IYYY') <> TO_CHAR(?::DATE, 'IYYY') "
																						+ "	THEN EXTRACT(WEEK FROM (DATE_TRUNC('YEAR', ?::DATE) + interval '-1 day')) "
																						+ " ELSE 0 END )	+	EXTRACT(WEEK FROM ?::DATE) ";

	public static String				SQL_GET_PRODUCTIONLINE_INFO				= "SELECT p.M_Product_ID, SUM(QtyUsed) AS QtyUsed, mp.DatePromised	FROM M_Production mp "
																						+ " INNER JOIN M_ProductionLine pl ON (pl.M_Production_ID = mp.M_Production_ID AND pl.IsEndProduct = 'N') "
																						+ " INNER JOIN M_Product p ON (p.M_Product_ID = pl.M_Product_ID AND p.IsPhantom = 'N') "
																						+ " WHERE pl.IsActive='Y' AND pl.M_Production_ID = ? "
																						+ " GROUP BY mp.M_Production_ID, p.M_Product_ID	ORDER BY p.M_Product_ID ";

	public static String				SQL_PRODUCTWISE_INFO_FROM_PRODUCTION	= "SELECT p.M_Production_ID, p.DocumentNo, p.DatePromised, SUM(QtyUsed) AS QtyUsed	FROM M_Production p "
																						+ " INNER JOIN M_ProductionLine pl ON (pl.M_Production_ID = p.M_Production_ID AND pl.IsEndProduct = 'N' AND pl.M_Product_ID = ?) "
																						+ " WHERE p.Processed='N' AND p.C_DocType_ID=? AND DatePromised BETWEEN ? AND ? "
																						+ " GROUP BY p.M_Production_ID	ORDER BY p.DatePromised ";

	public static String				SQL_GET_BOMLINE_FOR_PROCESS				= "SELECT pb.M_Product_ID, pb.qtybom, p.M_Product_Category_ID, p.Name AS ProductName, p.IsBOM, p.IsVerified, p.IsPurchased, "
																						+ "		p.IsPhantom, mpo.C_BPartner_ID, COALESCE(ppr.pricelist,  0.00) AS PricePO, mpo.DeliveryTime_Promised, "
																						+ "		COALESCE(r.QtyBatchSize, 0) AS QtyBatchSize, COALESCE(r.Level_Min, 0) AS Level_Min, r.ReplenishType, SUM(ms.qtyonhand) AS Available "
																						+ " FROM pp_product_bomline pb "
																						+ " INNER JOIN pp_product_bom bom on (pb.pp_product_bom_ID = bom.pp_product_bom_ID and bom.isdefault = 'Y' and bom.m_product_ID = ?)"
																						+ " INNER JOIN M_Product p ON (p.M_Product_ID = pb.M_Product_ID and p.ad_Client_ID =?)"
																						+ " INNER JOIN M_Replenish r	ON (r.M_Product_ID = p.M_Product_ID AND r.M_Warehouse_ID = ?)  "
																						+ " LEFT JOIN M_Product_PO mpo ON (mpo.M_Product_ID = p.M_Product_ID AND mpo.C_BPartner_ID = "
																						+ "		(SELECT po.C_BPartner_ID FROM M_Product_PO po "
																						+ " WHERE (po.M_Product_ID = p.M_Product_ID) ORDER BY IsCurrentVendor DESC  FETCH FIRST ROW ONLY)) "
																						+ " LEFT JOIN M_ProductPrice ppr ON  (p.m_product_id = ppr.m_product_id)  AND  ppr.m_pricelist_version_id = "
																						+ " (SELECT plv.m_pricelist_version_id FROM m_pricelist_version plv "
																					    + " LEFT JOIN c_bpartner bpx ON plv.m_pricelist_id = bpx.po_pricelist_id "
																					    + " WHERE (bpx.c_bpartner_id = mpo.c_bpartner_id AND plv.ValidFrom <= now())  ORDER BY plv.m_pricelist_version_id DESC FETCH FIRST ROW ONLY) "
																					    + " LEFT OUTER JOIN M_Storage ms ON (ms.M_Product_ID = p.M_Product_ID) "
																						+ " GROUP BY	pb.M_Product_ID, pb.pp_product_bomline_ID, pb.qtybom, p.M_Product_Category_ID, p.Name, p.IsBOM, p.IsVerified, p.IsPurchased, p.IsPhantom, "
																						+ "		mpo.C_BPartner_ID, mpo.PricePO, mpo.DeliveryTime_Promised, r.M_Product_ID, r.M_Warehouse_ID, ppr.pricelist "
																						+ " ORDER BY pb.pp_product_bomline_ID ";

	SimpleDateFormat					requiredFormat							= new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat					sdf										= new SimpleDateFormat("HH:mm:ss.SSS");
	
	Map<Date, MRequisition> mapRequisition = new TreeMap<Date, MRequisition>();
	
	@Override
	protected void prepare()
	{
		ctx = getCtx();
		trx = get_TrxName();
		mrpRunID = getRecord_ID();

		AD_Client_ID = Env.getAD_Client_ID(ctx);
		M_WarehouseID = Env.getContextAsInt(ctx, "M_Warehouse_ID");
		M_LocatorID = Env.getContextAsInt(ctx, "M_Locator_ID");
		AD_User_ID = Env.getAD_User_ID(ctx);
		AD_Org_ID = Env.getAD_Org_ID(ctx);
		p_M_Product_ID = getParameterAsInt("M_Product_ID");


	}

	@Override
	protected String doIt() throws Exception
	{
		Timestamp t1 = new Timestamp(System.currentTimeMillis());
		log.config("Start DoIt: " + sdf.format(t1));

		MReplenishPlan run = new MReplenishPlan(ctx, mrpRunID, trx);
		StringBuilder error = new StringBuilder();
		dateFrom = run.getDateStart();
		dateTo = run.getDateFinish();
		M_PriceList_ID = run.getM_PriceList_ID();

		docType_PlannedOrder = run.getC_DocType_PlannedOrder();
		docType_ConfirmedOrder = run.getC_DocType_ConfirmedOrder();
		docType_PurchaseOrder = run.getC_DocType_PO();
		docType_MRPRequisition = run.getC_DocType_Requisition();
		
		if (docType_PlannedOrder <= 0)
			error.append("No Mfg Planned Order Document set. \n");
		if (docType_ConfirmedOrder <= 0)
			error.append("No Confirmed Mfg Order Document set. \n");
		if (docType_PurchaseOrder <= 0)
			error.append("No Purchase Order Document set. \n");
		if (docType_MRPRequisition <= 0)
			error.append("No MRP Requisition Document set. \n");

		if (error.length() > 0) {
			throw new Exception(error.toString());
		}
		String sql = "DELETE FROM M_ReplenishPlanLine WHERE M_ReplenishPlan_ID=? AND AD_Client_ID=?";
		int noOfLinesDeleted = DB.executeUpdateEx(sql, new Object[] { mrpRunID, Env.getAD_Client_ID(ctx) }, trx);
		log.fine("No. of MRP lines deleted : " + noOfLinesDeleted);

		if (dateFrom == null)
			throw new IllegalArgumentException(Msg.translate(ctx, "FillMandatory") + Msg.translate(ctx,
					"DatePosted - From"));
		if (dateTo == null)
			throw new IllegalArgumentException(Msg.translate(ctx, "FillMandatory") + Msg.translate(ctx,
					"DatePosted - To"));
		if (M_PriceList_ID == 0)
			throw new IllegalArgumentException(Msg.translate(ctx, "FillMandatory") + Msg.translate(ctx,
					"M_PriceList_ID"));

		int isAfterDate = dateTo.compareTo(dateFrom);

		if (isAfterDate > 0)
		{

			START_WEEK = DB.getSQLValue(trx, "SELECT EXTRACT( WEEK FROM ?::Timestamp )", dateFrom) - 2;
			END_WEEK = DB.getSQLValue(trx, SQL_GET_ISO_WEEKNO, dateFrom, dateTo, dateTo, dateTo) + 2;

			if (START_WEEK == 0)
			{
				START_WEEK = DB.getSQLValue(trx,
						"SELECT EXTRACT(WEEK FROM (DATE_TRUNC('YEAR', ?::DATE) + interval '-1 day')) ", dateFrom);
				END_WEEK += START_WEEK;
			}

			Calendar cal = Calendar.getInstance();
			cal.setFirstDayOfWeek(Calendar.MONDAY);
			cal.setTime(dateFrom);
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			cal.add(Calendar.WEEK_OF_YEAR, -2);

			dateFrom.setTime(cal.getTimeInMillis());

			int weekDifference = END_WEEK - START_WEEK;

			cal.setTime(dateFrom);
			cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			cal.add(Calendar.WEEK_OF_YEAR, weekDifference);

			dateTo.setTime(cal.getTimeInMillis());

			if (weekDifference > 24)
			{
				throw new IllegalArgumentException(Msg.translate(ctx,
						"Week difference should not be greater than 20 for selected horizon."));
			}
		}
		else
		{
			throw new IllegalArgumentException(Msg.translate(ctx, "ToDate must me greater than selected FromDate"));
		}

		if (run.isDeleteUnconfirmedProduction())
		{
			sql = "DELETE FROM M_ProductionLine	 WHERE M_Production_ID IN (SELECT M_Production_ID FROM M_Production WHERE Processed='N' AND C_DocType_ID = ?)";
			noOfLinesDeleted = DB.executeUpdate(sql, docType_PlannedOrder, trx);
			log.fine("No. of planned production line deleted : " + noOfLinesDeleted);

			sql = "DELETE FROM M_Production	WHERE Processed='N' AND C_DocType_ID = ? ";
			noOfLinesDeleted = DB.executeUpdate(sql, docType_PlannedOrder, trx);
			log.fine("No. of planned production deleted : " + noOfLinesDeleted);

			sql = "DELETE FROM m_productionbatch b  " +
					"WHERE b.c_doctype_id = ? " +
		            "AND   NOT EXISTS (SELECT * " +
		             "                  FROM m_production " +
		             "                  WHERE m_productionbatch_id = b.m_productionbatch_id)";
			noOfLinesDeleted = DB.executeUpdate(sql, docType_PlannedOrder, trx);
			log.fine("No. of Production Batch deleted " + noOfLinesDeleted);

			sql = "DELETE FROM M_MovementLine ml 	USING M_Movement m "
					+ " WHERE m.M_ProductionBatch_ID IS NOT NULL AND m.Processed = 'N' "
					+ " AND NOT EXISTS (SELECT * FROM M_ProductionBatch b WHERE b.M_ProductionBatch_ID = m.M_ProductionBatch_ID)";
			noOfLinesDeleted = DB.executeUpdate(sql, trx);
			log.fine("No. of Movement Lines cleaned : " + noOfLinesDeleted);

			sql = "DELETE FROM M_Movement m " + " WHERE m.M_ProductionBatch_ID IS NOT NULL AND m.Processed = 'N'"
					+ "	AND NOT EXISTS (SELECT * FROM M_ProductionBatch b WHERE b.M_ProductionBatch_ID = m.M_ProductionBatch_ID)";
			noOfLinesDeleted = DB.executeUpdate(sql, trx);
			log.fine("No. of Inventory Movements cleaned : " + noOfLinesDeleted);
		}

		if (run.isDeletePlannedPO())
		{
			String selectRequisition = " (SELECT M_Requisition_ID FROM M_Requisition WHERE DocStatus IN ('DR') AND Processed='N' AND AD_Client_ID = ? AND C_DocType_ID = ?) ";

			sql = "DELETE FROM PP_MRP	WHERE M_Requisition_ID IN " + selectRequisition;
			noOfLinesDeleted = DB.executeUpdateEx(sql, new Object[] { AD_Client_ID, docType_MRPRequisition }, trx);
			log.fine("No. of Material Requirement Planning (PP_MRP) Line deleted : " + noOfLinesDeleted);

			sql = "DELETE FROM M_RequisitionLine	WHERE M_Requisition_ID IN " + selectRequisition;
			noOfLinesDeleted = DB.executeUpdateEx(sql, new Object[] { AD_Client_ID, docType_MRPRequisition }, trx);
			log.fine("No. of MRP Requisition Line deleted : " + noOfLinesDeleted);

			sql = "DELETE FROM M_Requisition WHERE DocStatus IN ('DR') AND Processed='N' AND AD_Client_ID = ? AND C_DocType_ID = ? ";
			noOfLinesDeleted = DB.executeUpdateEx(sql, new Object[] { AD_Client_ID, docType_MRPRequisition }, trx);
			log.fine("No. of MRP Requisition deleted : " + noOfLinesDeleted);
		}

		Map<Integer, MiniMRPProduct> miniMrpProducts = new TreeMap<Integer, MiniMRPProduct>();
		Set<Integer> productIds = new TreeSet<Integer>();

		// Collect all the Products required to be processed.
		log.config("START generateProductInfo:" + sdf.format(new Date()));
		generateProductInfo(miniMrpProducts, productIds);
		log.config("END generateProductInfo:" + sdf.format(new Date()));

		// Process Demand
		log.config("START doRunProductsSO:" + sdf.format(new Date()));
		doRunProductsSO(miniMrpProducts, productIds);
		log.config("END doRunProductsSO:" + sdf.format(new Date()));

		// Creating Requisition Order and Planned Production Order
		log.config("START doRunCreatePOandProductionOrder:" + sdf.format(new Date()));
		doRunCreatePOandProductionOrder(miniMrpProducts);
		log.config("END doRunCreatePOandProductionOrder:" + sdf.format(new Date()));

		// Save Requisition Lines to Database
		for (Date date : mapRequisition.keySet())
		{
			MRequisition requisition = mapRequisition.get(date);
			//requisition.saveLineQueue();
			log.config("START: Write to DB Requisition Line " + requisition.toString() + " " + sdf.format(new Date()));
			log.config("END: Write to DB Requisition Line " + requisition.toString() + " " + sdf.format(new Date()));
		}

		// Process Supply
		log.config("START doRunOpenOrders:TYPE_PO" + sdf.format(new Date()));
		doRunOpenOrders(miniMrpProducts, productIds, TYPE_PO);
		log.config("END doRunOpenOrders:TYPE_MO" + sdf.format(new Date()));

		log.config("START doRunOpenOrders:TYPE_MO" + sdf.format(new Date()));
		doRunOpenOrders(miniMrpProducts, productIds, TYPE_MO);
		log.config("END doRunOpenOrders:TYPE_MO" + sdf.format(new Date()));

		log.config("START doRunOpenOrders:TYPE_RQ" + sdf.format(new Date()));
		doRunOpenOrders(miniMrpProducts, productIds, TYPE_RQ);
		log.config("END doRunOpenOrders:TYPE_RQ" + sdf.format(new Date()));

		log.config("START renderPeggingReport" + sdf.format(new Date()));
		renderPeggingReport(miniMrpProducts);
		log.config("END renderPeggingReport" + sdf.format(new Date()));

		log.config("START updateHasSupplyDemand" + sdf.format(new Date()));
		updateHasSupplyDemand();
		log.config("END updateHasSupplyDemand" + sdf.format(new Date()));

		Timestamp t2 = new Timestamp(System.currentTimeMillis());
		log.config("END DoIt: " + sdf.format(t2) + "\n\n Time Diff Millis: " + new DecimalFormat("###,###").format(
				t2.getTime() - t1.getTime()));

		return infoMsg.toString();
	}

	/*
	 * Set flag to show products where demand and/or supply exists in the MRP
	 * period, used to filter the MRP report to exclude products with no
	 * movement
	 */
	private void updateHasSupplyDemand()
	{

		String sql = "WITH HasSupply AS "
				+ "(SELECT M_Product_ID, M_ReplenishPlan_ID, AD_Client_ID, COUNT (M_Product_ID) "
				+ " FROM M_ReplenishPlanLine	WHERE M_ReplenishPlan_ID = ? AND AD_Client_ID = ? "
				+ " GROUP BY AD_Client_ID, M_ReplenishPlan_ID, M_Product_ID "
				+ " HAVING COUNT (M_Product_ID) > 2	"
				+ ") "
				+ "UPDATE M_ReplenishPlanLine rl SET HasSupplyDemand = 'Y' FROM HasSupply hs "
				+ "WHERE hs.M_ReplenishPlan_ID = rl.M_ReplenishPlan_ID AND hs.AD_Client_ID = rl.AD_Client_ID AND hs.M_Product_ID = rl.M_Product_ID";

		int updated = DB.executeUpdateEx(sql, new Object[] { mrpRunID, Env.getAD_Client_ID(ctx) }, trx);

		log.fine("Lines with supply/demand updated: " + updated);

	}

	/**
	 * This method will process to create Production Order and Purchase order as
	 * per required quantity.
	 * 
	 * @author Sachin Bhimani
	 * @param miniMrpProducts
	 */
	private void doRunCreatePOandProductionOrder(Map<Integer, MiniMRPProduct> miniMrpProducts)
	{
		MRequisition requisition = null;

		// Calculate Required and production QTY
		runProcessCalculatePlannedQty(miniMrpProducts);

		// Create Requisition Order with Lines
		for (Integer productID : mapRequirePlanningQty.keySet())
		{
			MiniMRPProduct mrp = miniMrpProducts.get(productID);
			Map<Date, BigDecimal> weeklyProductionQty = new TreeMap<Date, BigDecimal>(
					mapRequirePlanningQty.get(productID));
			for (Date date : weeklyProductionQty.keySet())
			{
				if (weeklyProductionQty.get(date).compareTo(Env.ZERO) == 0)
					continue;

				if (!mrp.isBOM() && mrp.isPurchased() && !mrp.isPhantom())
				{
					requisition = createRequisitionHeader(date);
					createRequisitionLine(requisition, mrp, weeklyProductionQty.get(date));
				}
				else if (mrp.isBOM() && !mrp.isPhantom())
				{
					createProductionOrder(mrp, weeklyProductionQty.get(date), new Timestamp(date.getTime()));
				}
			}
		}

		infoMsg.append(" No of Docs created: Requisition:" + countReq);
		infoMsg.append(" and Planned Production:" + countProd);
		infoMsg.append("\n Requisition Docs List: " + requisitionDocs);
		infoMsg.append("\n Planned Docs List: " + productionDocs);

		log.log(Level.INFO, infoMsg.toString());
	}

	private void runProcessCalculatePlannedQty(Map<Integer, MiniMRPProduct> miniMrpProducts)
	{
		for (Date date : mapDemand.keySet())
		{
			Map<Integer, BigDecimal> mapOrderQty = mapDemand.get(date);
			for (Integer productID : mapOrderQty.keySet())
			{
				MProduct product = new MProduct(getCtx(), productID, get_TrxName());
				if (!product.isStocked())
					return ;
				BigDecimal demandQty = mapOrderQty.get(productID);
				if (demandQty.compareTo(Env.ZERO) != 0)
				{
					MiniMRPProduct mrp = miniMrpProducts.get(productID);
					if (mrp == null) {
						MProduct p = MProduct.get(ctx, productID);
						String error = "Please check Product=" + p.getValue() + " replenishment parameters may not be setup properly.";
						log.severe(error);
						throw new AdempiereException(error);
					}
				
					Integer nonPhantomProduct = (mrp.isPhantom() && mrp.isBOM() ? 0 : productID);
					createPlannedQtyMap(miniMrpProducts, date, productID, demandQty, 0, nonPhantomProduct); // (MRP,DateOfDemand,PID,DQty,Level,NonPhontomPID)
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

				if (plannedDate.compareTo(dateFrom) < 0)
					plannedDate = dateFrom;

				if (!mrp.isPhantom() && nonPhantomProduct != 0 && mrp.isBOM())
				{
					if (mrp.isReplenishTypeMRPCalculated())
						demandQty = calculateBatchSizeWiseOrderCreation(mrp, demandQty, plannedDate);
					else
						setRequirePlanningQty(mrp, plannedDate, demandQty);
				}

				if (mrp.isBOM())
				{
					planBOMTree(miniMrpProducts, productID, date, demandQty, level, nonPhantomProduct);
				}
				else if (!mrp.isPhantom())
				{
					if (mrp.isReplenishTypeMRPCalculated())
						demandQty = calculateBatchSizeWiseOrderCreation(mrp, demandQty, plannedDate);
					else
						setRequirePlanningQty(mrp, plannedDate, demandQty);
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
		MProduction mProd = new MProduction(ctx, 0, trx);
		//mProd.setAD_Client_ID(AD_Client_ID);
		mProd.setAD_Org_ID(AD_Org_ID);
		mProd.setM_Product_ID(mrp.getM_Product_ID());
		mProd.setProductionQty(qty);
		mProd.setM_Locator_ID(M_LocatorID);
		mProd.setC_DocType_ID(docType_PlannedOrder);
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
			BigDecimal demandQty, int level, Integer nonPhantomProduct)
	{
		String sql = getQueryForBOMProductExplode();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, trx);
			pstmt.setBigDecimal(1, demandQty);
			pstmt.setInt(2, productID);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				BigDecimal requiredQty = rs.getBigDecimal("RequiredQty");
				if (requiredQty.compareTo(Env.ZERO) > 0)
					createPlannedQtyMap(miniMrpProducts, date, rs.getInt("M_ProductBOM_ID"), requiredQty, level,
							nonPhantomProduct);
			}
		}
		catch (SQLException e)
		{
			throw new AdempiereException("Could not process BOM product explode of requiring Qty.");
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
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
	private MRequisition createRequisition(Date date)
	{
		MRequisition requisition;
		requisition = new MRequisition(ctx, 0, trx);
		//requisition.setAD_Client_ID(AD_Client_ID);
		requisition.setM_Warehouse_ID(M_WarehouseID);
		requisition.setC_DocType_ID(docType_MRPRequisition);
		requisition.setAD_User_ID(AD_User_ID);
		requisition.setDescription("Created from MiniMRP process.");
		requisition.setDateRequired(new Timestamp(date.getTime()));
		requisition.setDateDoc(new Timestamp(date.getTime()));
		requisition.setM_PriceList_ID(M_PriceList_ID);
		requisition.saveEx();

		countReq++;
		requisitionDocs.append(requisition.getDocumentNo()).append(",");

		return requisition;
	}

	private void createRequisitionLine(MRequisition requisition, MiniMRPProduct mrp, BigDecimal qty)
	{
		//requisition.addLinetoQueue(mrp.getM_Product_ID(), mrp.getC_BPartner_ID(), qty, mrp.getPriceActual());
/*		MRequisitionLine rLine = new MRequisitionLine(requisition);
		rLine.setM_Product_ID(mrp.getM_Product_ID());
		rLine.setC_BPartner_ID(mrp.getC_BPartner_ID());
		rLine.setPriceActual(mrp.getPriceActual());
		rLine.setQty(qty);
		rLine.saveEx();
*/	}

	/**
	 * Product wise Get confirmed Qty. If Product is BOM then Production else
	 * from PO.
	 * 
	 * @param mrp
	 */
	private void setConfirmProductQty(MiniMRPProduct mrp)
	{
		int productID = mrp.getM_Product_ID();
		boolean isBOM = mrp.isBOM();

		Map<Date, BigDecimal> mapConfirmQty = new TreeMap<Date, BigDecimal>();

		String sql = (isBOM ? SQL_PRODUCTWISE_CONFIRM_PRODUCTION_QTY : SQL_PRODUCTWISE_CONFIRM_PO_RQ_QTY);

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try
		{
			pstmt = DB.prepareStatement(sql, trx);
			pstmt.setInt(1, productID);
			pstmt.setTimestamp(2, dateFrom);
			pstmt.setTimestamp(3, dateTo);

			if (isBOM)
				pstmt.setInt(4, docType_PlannedOrder);
			else
			{
				pstmt.setInt(4, productID);
				pstmt.setTimestamp(5, dateFrom);
				pstmt.setTimestamp(6, dateTo);
				pstmt.setInt(7, AD_Client_ID);
				pstmt.setInt(8, docType_MRPRequisition);
			}

			rs = pstmt.executeQuery();
			while (rs.next())
			{
				Date datePromised = rs.getDate("DatePromised");
				BigDecimal qty = rs.getBigDecimal("Qty");

				if (mapConfirmQty.containsKey(datePromised))
					mapConfirmQty.put(datePromised, mapConfirmQty.get(datePromised).add(qty));
				else
					mapConfirmQty.put(datePromised, qty);

				if (mrp.isBOM())
				{
					PreparedStatement pstatement = null;
					ResultSet rset = null;
					try
					{
						pstatement = DB.prepareStatement(SQL_GET_PRODUCTIONLINE_INFO, trx);
						pstatement.setInt(1, rs.getInt("M_Production_ID"));

						rset = pstatement.executeQuery();
						while (rset.next())
						{
							setQtyAsDemand(rset.getInt("M_Product_ID"), rset.getBigDecimal("QtyUsed"),
									rset.getTimestamp("DatePromised"));
						}
					}
					catch (SQLException e)
					{
						throw new AdempiereException("Could not process, Retrieve Confirm production line info.", e);
					}
					finally
					{
						DB.close(rset, pstatement);
						rset = null;
						pstatement = null;
					}
				}
			}
		}
		catch (SQLException e)
		{
			throw new AdempiereException("Could not process, Retrieve weekly confirm production qty of Product:"
					+ mrp.getName(), e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		mrp.setMapConfirmProductQty(mapConfirmQty);
	}

	public void addAvailableInventory(int mProductId, BigDecimal qty)
	{
		availableInventory.put(mProductId, qty);
	}

	/**
	 * @author Sachin Bhimani
	 * @param miniMrpProduct
	 * @param line
	 * @param type
	 * @return miniM_ReplenishPlanLine
	 */
	private X_M_ReplenishPlanLine getX_M_ReplenishPlanLine(MiniMRPProduct miniMrpProduct, int line, String type)
	{
		X_M_ReplenishPlanLine miniMRP = new X_M_ReplenishPlanLine(ctx, 0, trx);
		miniMRP.setM_Product_ID(miniMrpProduct.getM_Product_ID());
		miniMRP.setM_Product_Category_ID(miniMrpProduct.getM_Product_Category_ID());
		miniMRP.setProductName(miniMrpProduct.getName());
		miniMRP.setLine(line);
		miniMRP.setM_ReplenishPlan_ID(mrpRunID);
		miniMRP.setRecordType(type);
		miniMRP.setDateStart(dateFrom);
		miniMRP.setDateFinish(dateTo);
		return miniMRP;
	}


	public void calculateSuggestedOrders(MiniMRPProduct miniMrpProduct, Map<Integer, BigDecimal> totalDemandLine,
			Map<Integer, BigDecimal> totalSupplyLine, Map<Integer, BigDecimal> openingBalanceLine,
			Map<Integer, BigDecimal> closingBalanceLine)
	{
		int horizon = END_WEEK - START_WEEK + 1;

		for (int i = 0; i < horizon; i++)
		{
			int week = START_WEEK + i;

			BigDecimal totalDemand = Env.ZERO;
			BigDecimal totalSupply = Env.ZERO;
			BigDecimal openingBalance = Env.ZERO;

			if (totalDemandLine.containsKey(week))
			{
				totalDemand = totalDemandLine.get(week);
				if (totalDemand == null)
				{
					totalDemand = Env.ZERO;
				}
			}
			if (totalSupplyLine.containsKey(week))
			{
				totalSupply = totalSupplyLine.get(week);
				if (totalSupply == null)
				{
					totalSupply = Env.ZERO;
				}
			}
			if (openingBalanceLine.containsKey(week))
			{
				openingBalance = openingBalanceLine.get(week);
				if (openingBalance == null)
				{
					openingBalance = Env.ZERO;
				}
			}

			BigDecimal sugQty = totalDemand.subtract((totalSupply.add(openingBalance)));

			if (sugQty.compareTo(Env.ZERO) > 0)
			{
				openingBalanceLine.put(week + 1, Env.ZERO);
				closingBalanceLine.put(week, Env.ZERO);
			}
			else
			{
				openingBalanceLine.put(week + 1, (sugQty.negate()));
				closingBalanceLine.put(week, (sugQty.negate()));
			}
		}
	}

	public Map<Integer, BigDecimal> insertOrderDemands(MiniMRPProduct miniMrpProduct)
	{
		Map<Integer, Map<Integer, BigDecimal>> orderDemands = miniMrpProduct.getDemand();
		Map<Integer, BigDecimal> totalDemandLine = new HashMap<Integer, BigDecimal>();
		// From MRP Demand MAP
		if (!orderDemands.isEmpty())
		{
			for (Integer orderId : orderDemands.keySet())
			{
				Map<Integer, BigDecimal> weeklyDemand = orderDemands.get(orderId);
				if (weeklyDemand.size() > 0)
				{
					X_M_ReplenishPlanLine miniMRP = getX_M_ReplenishPlanLine(miniMrpProduct, lineNo, TYPE_PRODUCT_ORDER_DEMAND);
					miniMRP.setC_Order_ID(orderId);
					MOrder order = new MOrder(ctx, orderId, trx);

					String datePromised = requiredFormat.format(order.getDatePromised());

					miniMRP.setOrderInfo(order.getDocumentNo() + " - " + datePromised);
					for (Integer week : weeklyDemand.keySet())
					{
						BigDecimal qty = weeklyDemand.get(week);
						setWeeklyData(miniMRP, week, qty);
						if (totalDemandLine.containsKey(week))
						{
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
		try
		{
			pstmt = DB.prepareStatement(SQL_PRODUCTWISE_INFO_FROM_PRODUCTION, trx);
			pstmt.setInt(paramCount++, miniMrpProduct.getM_Product_ID());
			pstmt.setInt(paramCount++, docType_PlannedOrder);
			pstmt.setTimestamp(paramCount++, dateFrom);
			pstmt.setTimestamp(paramCount++, dateTo);

			rs = pstmt.executeQuery();
			while (rs.next())
			{
				insertProductionDemand(miniMrpProduct, totalDemandLine, rs.getInt("M_Production_ID"),
						rs.getString("DocumentNo"), rs.getTimestamp("DatePromised"), rs.getBigDecimal("QtyUsed"),
						"Planned");
			}
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, SQL_PRODUCTWISE_INFO_FROM_PRODUCTION, e);
			throw new AdempiereException(e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		// Add demand for SubLevel Product which is Confirmed production.
		int[] productionID = DB.getIDsEx(trx, SQL_GET_PRODUCTION_SUBPRODUCTWISE,
				miniMrpProduct.getM_Product_ID(), dateFrom, dateTo, docType_PlannedOrder);
		for (int mProdID : productionID)
		{
			MProduction production = new MProduction(ctx, mProdID, trx);
			BigDecimal qty = DB.getSQLValueBD(trx,
					"SELECT SUM(QtyUsed) FROM M_ProductionLine WHERE M_Production_ID = ? AND M_Product_ID = ? AND AD_Client_ID=?",
					mProdID, miniMrpProduct.getM_Product_ID(), AD_Client_ID);
			if (qty == null)
			{
				qty = Env.ZERO;
			}
			insertProductionDemand(miniMrpProduct, totalDemandLine, production.getM_Production_ID(),
					production.getDocumentNo(), production.getDatePromised(), qty, "Confirm");
		}

		if (totalDemandLine != null && !totalDemandLine.isEmpty())
			insertTotalLine(miniMrpProduct, totalDemandLine, TYPE_TOTAL_DEMAND_LINE);

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
			int mProduction_ID, String documentNo, Timestamp promisedDate, BigDecimal qtyUsed, String prodType)
	{
		X_M_ReplenishPlanLine miniMRP = getX_M_ReplenishPlanLine(miniMrpProduct, lineNo, TYPE_PRODUCT_ORDER_DEMAND);
		miniMRP.setM_Production_ID(mProduction_ID);
		String datePromised = null;
		int week = 0;

		datePromised = requiredFormat.format(promisedDate);
		week = DB.getSQLValue(trx, SQL_GET_ISO_WEEKNO, dateFrom, promisedDate, promisedDate, promisedDate);

		miniMRP.setProductionInfo(documentNo + " - " + datePromised + " - " + prodType);

		setWeeklyData(miniMRP, week, qtyUsed);
		if (totalDemandLine.containsKey(week))
		{
			BigDecimal totalDemand = totalDemandLine.get(week);
			if (totalDemand == null)
			{
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
	public void insertTotalLine(MiniMRPProduct miniMrpProduct, Map<Integer, BigDecimal> totalLine, String lineType)
	{
		if (!totalLine.isEmpty())
		{
			X_M_ReplenishPlanLine miniMRP = getX_M_ReplenishPlanLine(miniMrpProduct, lineNo, lineType);
			for (Integer week : totalLine.keySet())
			{
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
	public Map<Integer, BigDecimal> insertSupplyLines(MiniMRPProduct miniMrpProduct)
	{
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
		if (totalSupplyLine != null && !totalSupplyLine.isEmpty())
			insertTotalLine(miniMrpProduct, totalSupplyLine, TYPE_TOTAL_SUPPLY_LINE);

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
			Map<Integer, BigDecimal> subTotalSupplyLine, boolean isPlannedOrder)
	{
		Map<Integer, Map<Integer, BigDecimal>> supplyLineMO = new TreeMap<Integer, Map<Integer, BigDecimal>>(
				miniMrpProduct.getSupplyLineMO());

		if (!supplyLineMO.isEmpty())
		{
			for (Integer M_Production_ID : supplyLineMO.keySet())
			{
				Map<Integer, BigDecimal> weeklyDemand = supplyLineMO.get(M_Production_ID);
				MProduction production = new MProduction(ctx, M_Production_ID, trx);
				if ((isPlannedOrder == false && production.getC_DocType_ID() != docType_PlannedOrder)
						|| (isPlannedOrder && production.getC_DocType_ID() == docType_PlannedOrder))
				{
					if (weeklyDemand.size() > 0)
					{
						X_M_ReplenishPlanLine miniMRP = getX_M_ReplenishPlanLine(miniMrpProduct, lineNo, isPlannedOrder
								? TYPE_SUPPLY_LINE_MO_PLANNED : TYPE_SUPPLY_LINE_MO_NONPLANNED);
						miniMRP.setM_Production_ID(M_Production_ID);

						String datePromished = requiredFormat.format(production.getDatePromised());

						miniMRP.setProductionInfo(production.getDocumentNo() + " - " + datePromished);
						for (Integer week : weeklyDemand.keySet())
						{
							BigDecimal qty = weeklyDemand.get(week);
							setWeeklyData(miniMRP, week, qty);
							if (totalSupplyLine.containsKey(week))
							{
								qty = qty.add(totalSupplyLine.get(week));
							}
							totalSupplyLine.put(week, qty);

							BigDecimal qtyProd = weeklyDemand.get(week);
							if (subTotalSupplyLine.containsKey(week))
							{
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
			Map<Integer, BigDecimal> subTotalSupplyLine, boolean isPO)
	{
		Map<Integer, Map<Integer, BigDecimal>> supplyLine;
		String typeSupply;
		if (isPO)
		{
			supplyLine = miniMrpProduct.getSupplyLinePO();
			typeSupply = TYPE_SUPPLY_LINE_PO;
		}
		else
		{
			supplyLine = miniMrpProduct.getSupplyLineRQ();
			typeSupply = TYPE_SUPPLY_LINE_RQ;
		}
		if (!supplyLine.isEmpty())
		{
			// columnID is C_Order_ID or M_Requisition_ID
			for (Integer columnID : supplyLine.keySet())
			{
				Map<Integer, BigDecimal> weeklyDemand = supplyLine.get(columnID);
				if (weeklyDemand.size() > 0)
				{
					X_M_ReplenishPlanLine miniMRP = getX_M_ReplenishPlanLine(miniMrpProduct, lineNo, typeSupply);
					Timestamp date1;
					String docNo = null;
					if (isPO)
					{
						miniMRP.setC_Order_ID(columnID);
						MOrder order = new MOrder(ctx, columnID, trx);
						date1 = order.getDatePromised();
						docNo = order.getDocumentNo();
					}
					else
					{
						miniMRP.setM_Requisition_ID(columnID);
						MRequisition req = new MRequisition(ctx, columnID, trx);
						date1 = req.getDateRequired();
						docNo = req.getDocumentNo();
					}

					String datePromised = requiredFormat.format(date1);

					miniMRP.setOrderInfo(docNo + " - " + datePromised);
					for (Integer week : weeklyDemand.keySet())
					{
						BigDecimal qty = weeklyDemand.get(week);
						setWeeklyData(miniMRP, week, qty);
						if (totalSupplyLine.containsKey(week))
						{
							qty = qty.add(totalSupplyLine.get(week));
						}
						totalSupplyLine.put(week, qty);

						BigDecimal qtyProd = weeklyDemand.get(week);
						if (subTotalSupplyLine.containsKey(week))
						{
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
			boolean isPO)
	{
		if (!subTotalSupplyLine.isEmpty())
		{
			X_M_ReplenishPlanLine miniMRP = getX_M_ReplenishPlanLine(miniMrpProduct, lineNo, isPO ? TYPE_TOTAL_SUPPLY_LINE_PO
					: TYPE_TOTAL_SUPPLY_LINE_RQ);

			// Insert Total Supply line.
			for (Integer week : subTotalSupplyLine.keySet())
			{
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
			Map<Integer, BigDecimal> subTotalSupplyLine, boolean isPlanned)
	{
		if (!subTotalSupplyLine.isEmpty())
		{
			X_M_ReplenishPlanLine miniMRP = getX_M_ReplenishPlanLine(miniMrpProduct, lineNo, isPlanned ? TYPE_TOTAL_SUPPLY_PLANNED_LINE
					: TYPE_TOTAL_SUPPLY_NONPLANNED_LINE);

			// Insert Total Supply Production as per docType.
			for (Integer week : subTotalSupplyLine.keySet())
			{
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
	public void insertOpeningBalanceLine(MiniMRPProduct miniMrpProduct, Map<Integer, BigDecimal> openingBalanceLine)
	{
		if (!openingBalanceLine.isEmpty())
		{
			X_M_ReplenishPlanLine miniMRP = getX_M_ReplenishPlanLine(miniMrpProduct, 10, TYPE_OPENING_BALANCE_LINE
					+ (miniMrpProduct.isPhantom ? " - Phantom Product" : ""));
			for (Integer week : openingBalanceLine.keySet())
			{
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
	public void insertClosingBalanceLine(MiniMRPProduct miniMrpProduct, Map<Integer, BigDecimal> closingBalanceLine)
	{
		if (!closingBalanceLine.isEmpty())
		{
			X_M_ReplenishPlanLine miniMRP = getX_M_ReplenishPlanLine(miniMrpProduct, lineNo, TYPE_CLOSING_BALANCE_LINE);
			for (Integer week : closingBalanceLine.keySet())
			{
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
	private void renderPeggingReport(Map<Integer, MiniMRPProduct> miniMrpProducts)
	{

		for (MiniMRPProduct miniMrpProduct : miniMrpProducts.values())
		{
			lineNo = 20;
			Map<Integer, BigDecimal> openingBalanceLine = new HashMap<Integer, BigDecimal>();
			Map<Integer, BigDecimal> closingBalanceLine = new HashMap<Integer, BigDecimal>();
			if (availableInventory.containsKey(miniMrpProduct.getM_Product_ID()))
			{
				BigDecimal qty = availableInventory.get(miniMrpProduct.getM_Product_ID());
				openingBalanceLine.put(START_WEEK, qty);
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
	 * @author Sachin Bhimani
	 * @param miniMrpProducts
	 * @param productIds
	 * @param type
	 */
	private void doRunOpenOrders(Map<Integer, MiniMRPProduct> miniMrpProducts, Set<Integer> productIds, String type)
	{
		String sql = null;
		if (type.equals(TYPE_MO))
			sql = getQueryForOpenMO();
		else if (type.equals(TYPE_PO))
			sql = getQueryForOpenPO();
		else if (type.equals(TYPE_RQ))
			sql = getQueryForOpenRequisition();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int paramCount = 1;
		try
		{
			pstmt = DB.prepareStatement(sql, trx);
			pstmt.setTimestamp(paramCount++, dateFrom);
			pstmt.setTimestamp(paramCount++, dateFrom);
			pstmt.setTimestamp(paramCount++, dateTo);
			pstmt.setInt(paramCount++, AD_Client_ID);
			pstmt.setArray(paramCount++, getSqlArray(productIds.toArray(), "numeric", DB.getConnectionRW(false))); // Productids
			if (type.equals(TYPE_RQ))
				pstmt.setInt(paramCount++, docType_MRPRequisition);

			rs = pstmt.executeQuery();
			while (rs.next())
			{
				int M_Product_ID = rs.getInt("m_product_id");
				BigDecimal orderQty = rs.getBigDecimal("orderedqty");
				int weekPromised = rs.getInt("weekordered");

				MiniMRPProduct product = miniMrpProducts.get(M_Product_ID);
				if (type.equals(TYPE_PO))
				{
					int c_order_id = rs.getInt("c_order_id");
					product.setSupplyLinePO(c_order_id, weekPromised, orderQty);
				}
				else if (type.equals(TYPE_MO))
				{
					int m_production_id = rs.getInt("m_production_id");
					product.setSupplyLineMO(m_production_id, weekPromised, orderQty);
				}
				else if (type.equals(TYPE_RQ))
				{
					int M_Requisition_ID = rs.getInt("M_Requisition_ID");
					product.setSupplyLineRQ(M_Requisition_ID, weekPromised, orderQty);
				}
			}
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
			getProcessInfo().setError(true);
			getProcessInfo().addLog(
					new ProcessInfoLog(getProcessInfo().getAD_Process_ID(), new Timestamp(System.currentTimeMillis()),
							null, "Failed to fetch products for mini MRP >> " + e.getMessage()));
			throw new AdempiereException(e);

		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
	}

	/**
	 * @param data
	 * @param sqlTypeName
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	private Array getSqlArray(Object[] data, String sqlTypeName, Connection conn) throws SQLException
	{
		Array array;
		if (conn instanceof C3P0ProxyConnection)
		{
			C3P0ProxyConnection proxy = (C3P0ProxyConnection) conn;
			try
			{
				Method m = Connection.class.getMethod("createArrayOf", String.class, Object[].class);
				Object[] args = { sqlTypeName, data };
				array = (Array) proxy.rawConnectionOperation(m, C3P0ProxyConnection.RAW_CONNECTION, args);
			}
			catch (Exception e)
			{
				throw new SQLException(e);
			}
		}
		else
		{
			array = conn.createArrayOf(sqlTypeName, data);
		}
		return array;
	}

	/**
	 * @param miniMrpProducts
	 * @param productIds
	 * @param productSO
	 */
	@SuppressWarnings("resource")
	private void doRunProductsSO(Map<Integer, MiniMRPProduct> miniMrpProducts, Set<Integer> productIds)
	{
		String sql = getQueryForProductSO();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int paramCount = 1;
		try
		{
			pstmt = DB.prepareStatement(sql, trx);
			pstmt.setTimestamp(paramCount++, dateFrom);
			pstmt.setTimestamp(paramCount++, dateFrom);
			pstmt.setTimestamp(paramCount++, dateTo);
			pstmt.setInt(paramCount++, AD_Client_ID);
			pstmt.setArray(paramCount++, getSqlArray(productIds.toArray(), "numeric", DB.getConnectionRW(false))); // Productids
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				int mProductID = rs.getInt("m_product_id");
				int mOrderID = rs.getInt("c_order_id");
				BigDecimal orderQty = rs.getBigDecimal("orderedqty");
				int weekPromised = rs.getInt("weekordered");
				BigDecimal qty = rs.getBigDecimal("orderedqty");
				Date date = rs.getDate("datepromised");

				if (miniMrpProducts.containsKey(mProductID))
				{
					MiniMRPProduct mrpProduct = miniMrpProducts.get(mProductID);

					if (mrpProduct.isPhantom())
						continue;

					if (!mrpProduct.isPhantom)
						setQtyAsDemand(mProductID, qty, date);

					mrpProduct.explodeDemand(mOrderID, orderQty, weekPromised, miniMrpProducts);
				}
				else
				{
					log.log(Level.SEVERE, "Error in miniMrpProducts setup.");
					getProcessInfo().setError(true);
					getProcessInfo().addLog(
							new ProcessInfoLog(getProcessInfo().getAD_Process_ID(), new Timestamp(System
									.currentTimeMillis()), null, "Error in miniMrpProducts setup.>> "));
					throw new AdempiereException("Error in miniMrpProducts setup.");
				}
			}
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
			getProcessInfo().setError(true);
			getProcessInfo().addLog(new ProcessInfoLog(getProcessInfo().getAD_Process_ID(), new Timestamp(System
					.currentTimeMillis()), null, "Failed to fetch products for mini MRP >> " + e.getMessage()));
			throw new AdempiereException(e);

		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
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
	 * This method will fetch all the products based on selected category or the
	 * product. This will process BOMLine if product is finished good. It will
	 * fill the miniMRPProducts list & id of each product(including BOMlines) in
	 * productIds
	 * 
	 * @param miniMrpProducts
	 * @param productIds
	 */
	private void generateProductInfo(Map<Integer, MiniMRPProduct> miniMrpProducts, Set<Integer> productIds)
	{
		String sql = getQueryForProductDetails();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, trx);
			pstmt.setInt(1, M_WarehouseID);
			pstmt.setInt(2, AD_Client_ID);
			if (p_M_Product_ID !=0)
				pstmt.setInt(3, p_M_Product_ID);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				int mProductID = rs.getInt("m_product_id");

				if (!miniMrpProducts.containsKey(mProductID))
				{
					MiniMRPProduct miniMrpProduct = addProductToProcess(mProductID, rs, miniMrpProducts, productIds);

					/* If Product is FG(Finished Goods) calculate it's BOMlines */
					if (miniMrpProduct.isBOM() && miniMrpProduct.isVerified())
					{
						processBOMLines(miniMrpProducts, productIds, miniMrpProduct.getM_Product_ID());
					}
				}
			}
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
			getProcessInfo().setError(true);
			getProcessInfo().addLog(new ProcessInfoLog(getProcessInfo().getAD_Process_ID(), new Timestamp(System
					.currentTimeMillis()), null, "Failed to fetch products for mini MRP >> " + e.getMessage()));
			throw new AdempiereException(e);

		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
	}

	/**
	 * @param mProductID
	 * @param rs
	 * @param miniMrpProducts
	 * @param productIds
	 * @return
	 * @throws SQLException
	 */
	private MiniMRPProduct addProductToProcess(int mProductID, ResultSet rs,
			Map<Integer, MiniMRPProduct> miniMrpProducts, Set<Integer> productIds) throws SQLException
	{
		String productName = rs.getString("ProductName");
		int mProductCategoryID = rs.getInt("M_Product_Category_ID");
		int projectedLeadTime = 0; // rs.getInt("deliverytime_promised");

		boolean isBOM = "Y".equalsIgnoreCase(rs.getString("IsBOM")) ? true : false;
		boolean isVerified = "Y".equalsIgnoreCase(rs.getString("IsVerified")) ? true : false;
		boolean isPurchased = "Y".equalsIgnoreCase(rs.getString("IsPurchased")) ? true : false;
		boolean isPhantom = "Y".equalsIgnoreCase(rs.getString("IsPhantom")) ? true : false;

		BigDecimal Level_Min = rs.getBigDecimal("Level_Min");
		BigDecimal availableInventory = rs.getBigDecimal("Available");

		if (availableInventory == null)
			availableInventory = Env.ZERO;

		if (isPhantom)
		{
			Level_Min = Env.ZERO;
			availableInventory = Env.ZERO;
		}

		MiniMRPProduct miniMrpProduct = new MiniMRPProduct(mProductID);
		miniMrpProduct.setAvailableQty(availableInventory);
		miniMrpProduct.setName(productName);
		miniMrpProduct.setM_Product_Category_ID(mProductCategoryID);
		miniMrpProduct.setProjectedLeadTime(projectedLeadTime);
		miniMrpProduct.setBOM(isBOM);
		miniMrpProduct.setVerified(isVerified);
		miniMrpProduct.setPurchased(isPurchased);
		miniMrpProduct.setPhantom(isPhantom);
		miniMrpProduct.setC_BPartner_ID(rs.getInt("C_BPartner_ID"));
		miniMrpProduct.setPriceActual(rs.getBigDecimal("PricePO"));
		miniMrpProduct.setQtyBatchSize(rs.getBigDecimal("QtyBatchSize"));
		miniMrpProduct.setLevel_Min(Level_Min);
		miniMrpProduct.setReplenishTypeMRPCalculated(rs.getString("ReplenishType")
				.equals(REPLENISH_TYPE_MRP_CALCULATED));
		if (miniMrpProduct.isReplenishTypeMRPCalculated())
			miniMrpProduct.setQtyOnHand(availableInventory.subtract(Level_Min));
		else
			miniMrpProduct.setQtyOnHand(availableInventory);

		// Check product QTY is negative then create demand.
		if (miniMrpProduct.getQtyOnHand().compareTo(Env.ZERO) < 0 && !isPhantom)
		{
			setQtyAsDemand(mProductID, miniMrpProduct.getQtyOnHand().negate(), dateFrom);
			miniMrpProduct.setQtyOnHand(Env.ZERO);
		}

		// Manage Inventory & ProductId blueprint here.
		productIds.add(mProductID);
		miniMrpProducts.put(mProductID, miniMrpProduct);
		addAvailableInventory(mProductID, availableInventory);

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
	private void explodeRequiredMaterials(MiniMRPProduct miniMrpProduct, MiniMRPProduct parentProduct, BigDecimal qtyBom)
	{
		Map<Integer, BigDecimal> requiredProdMaterials = miniMrpProduct.getRequiredProdMaterials();
		for (Integer mProdId : requiredProdMaterials.keySet())
		{
			BigDecimal qty = requiredProdMaterials.get(mProdId);
			parentProduct.addMatireals(mProdId, qtyBom.multiply(qty));
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
	public void processBOMLines(Map<Integer, MiniMRPProduct> miniMrpProducts, Set<Integer> productIds, int M_Product_ID)
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			if (M_Product_ID > 0)
			{
				MProduct product = new MProduct(getCtx(), M_Product_ID, get_TrxName());
				if (!product.isStocked())
					return ;
				//MPPProductBOM bom = MPPProductBOM.getDefault(finishedProduct, get_TrxName());
				pstmt = DB.prepareStatement(SQL_GET_BOMLINE_FOR_PROCESS, trx);
				pstmt.setInt(1, M_Product_ID);
				pstmt.setInt(2, AD_Client_ID);
				pstmt.setInt(3, M_WarehouseID);

				rs = pstmt.executeQuery();
				while (rs.next())
				{
					int mProductID = rs.getInt(1);
					BigDecimal qtyBom = rs.getBigDecimal(2);

					MiniMRPProduct parentProduct = miniMrpProducts.get(M_Product_ID);
					parentProduct.addMatireals(mProductID, qtyBom);

					MiniMRPProduct miniMrpProduct = null;

					// If material is already exploded.
					if (miniMrpProducts.containsKey(mProductID))
					{
						miniMrpProduct = miniMrpProducts.get(mProductID);
						explodeRequiredMaterials(miniMrpProduct, parentProduct, qtyBom);
					}
					else
					{
						miniMrpProduct = addProductToProcess(mProductID, rs, miniMrpProducts, productIds);
						if (miniMrpProduct.isBOM() && miniMrpProduct.isVerified())
						{
							processBOMLines(miniMrpProducts, productIds, mProductID);
							explodeRequiredMaterials(miniMrpProduct, parentProduct, qtyBom);
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, SQL_GET_BOMLINE_FOR_PROCESS, e);
			getProcessInfo().setError(true);
			getProcessInfo().addLog(new ProcessInfoLog(getProcessInfo().getAD_Process_ID(), new Timestamp(System
					.currentTimeMillis()), null, "Failed to process BOMLine : >> " + e.getMessage()));
			throw new AdempiereException(e);

		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
	}

	/**
	 * @return
	 */
	private String getQueryForProductDetails()
	{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT	p.M_Product_ID, p.Name AS ProductName, p.M_Product_Category_ID, mpo.DeliveryTime_Promised, p.IsBOM, p.IsVerified, SUM(ms.QtyOnHand) AS Available, p.IsPurchased, "
				+ " mpo.C_BPartner_ID, COALESCE(ppr.pricelist, 0) AS PricePO, p.IsPhantom, COALESCE(r.QtyBatchSize, 0) AS QtyBatchSize, COALESCE(r.Level_Min, 0) AS Level_Min, r.ReplenishType ");
		sql.append(" FROM M_Product p ");
		sql.append(" INNER JOIN M_Product_Category mpc ON (p.M_Product_Category_ID = mpc.M_Product_Category_ID) ");
		sql.append(" INNER JOIN M_Replenish r ON (r.M_Product_ID = p.M_Product_ID AND r.M_Warehouse_ID = ? )");
		sql.append(" LEFT JOIN M_Product_PO mpo ON (mpo.M_Product_ID = p.M_Product_ID AND mpo.C_BPartner_ID = ");
		sql.append("		(SELECT po.C_BPartner_ID FROM M_Product_PO po WHERE po.M_Product_ID=p.M_Product_ID AND po.IsActive = 'Y' ORDER BY IsCurrentVendor Desc FETCH FIRST ROW ONLY)) ");
		sql.append(" LEFT JOIN M_ProductPrice ppr ON  (p.m_product_id = ppr.m_product_id)  AND  ppr.m_pricelist_version_id = ");
		sql.append(" (SELECT plv.m_pricelist_version_id FROM m_pricelist_version plv ");
		sql.append(" LEFT JOIN c_bpartner bpx ON plv.m_pricelist_id = bpx.po_pricelist_id ");
		sql.append(" WHERE (bpx.c_bpartner_id = mpo.c_bpartner_id AND plv.ValidFrom <= now())  ORDER BY plv.m_pricelist_version_id DESC FETCH FIRST ROW ONLY) ");
		sql.append(" LEFT OUTER JOIN M_Storage ms ON (ms.M_Product_ID = p.M_Product_ID) ");
		sql.append(" WHERE p.IsActive='Y' AND p.AD_Client_ID = ?  and p.isstocked = 'Y' ");
		if (p_M_Product_ID !=0)
			sql.append(" and p.M_Product_ID=? ");
		sql.append(" GROUP BY p.M_Product_ID, mpo.DeliveryTime_Promised, mpo.C_BPartner_ID, ppr.pricelist, r.M_Product_ID, r.M_Warehouse_ID ");
		sql.append(" ORDER BY p.M_Product_ID ");

		return sql.toString();
	}

	/**
	 * @return
	 */
	private String getQueryForProductSO()
	{
		StringBuilder sql = new StringBuilder(
				"SELECT col.c_order_id, mp.m_product_id, mp.name as productname, (col.qtyordered-col.qtydelivered) as orderedqty,");
		sql.append("(CASE WHEN TO_CHAR(?::DATE, 'IYYY') <> TO_CHAR(col.datepromised::DATE, 'IYYY')	THEN EXTRACT(WEEK FROM (DATE_TRUNC('YEAR', col.datepromised::DATE) + interval '-1 day')) ELSE 0 END) + EXTRACT(WEEK FROM col.datepromised::DATE) as weekordered,");
		sql.append(" co.datepromised FROM c_order co JOIN c_orderline col ON (co.c_order_id=col.c_order_id) JOIN m_product mp ON(mp.m_product_id=col.m_product_id) JOIN m_product_category mpc ON mp.m_product_category_id = mpc.m_product_category_id WHERE mp.isactive='Y'  AND co.issotrx='Y' AND col.datepromised BETWEEN ? AND ? ");
		sql.append(" AND co.DocStatus IN ('CO') ");
		sql.append(" AND (col.qtyordered - col.qtydelivered) != 0");
		sql.append(" AND mp.AD_Client_ID=?");
		sql.append(" AND col.m_product_id = ANY(?)");
		sql.append(" ORDER BY co.datepromised ");
		return sql.toString();
	}

	private String getQueryForOpenPO()
	{
		StringBuilder sql = new StringBuilder(
				"SELECT co.c_order_id,mp.m_product_id,mp.name as productname,sum(col.qtyordered - col.qtydelivered) as orderedqty,");
		sql.append(" (CASE WHEN TO_CHAR(?::DATE, 'IYYY') <> TO_CHAR(col.datepromised::DATE, 'IYYY')	THEN EXTRACT(WEEK FROM (DATE_TRUNC('YEAR', col.datepromised::DATE) + interval '-1 day')) ELSE 0 END) + EXTRACT(WEEK FROM col.datepromised::DATE) as weekordered ");
		sql.append(" FROM c_order co JOIN c_orderline col ON (co.c_order_id=col.c_order_id) JOIN m_product mp ON(mp.m_product_id=col.m_product_id) JOIN m_product_category mpc ON mp.m_product_category_id = mpc.m_product_category_id ");
		sql.append(" WHERE mp.isactive='Y' AND co.issotrx='N' AND docstatus IN ('CO','CL') AND col.qtyordered > col.qtydelivered AND col.datepromised BETWEEN ? AND ? ");
		sql.append(" AND mp.AD_Client_ID=?");
		sql.append(" AND col.m_product_id = ANY(?)");
		sql.append(" GROUP BY weekordered, mp.m_product_id,co.c_order_id");
		sql.append(" ORDER BY mp.m_product_id, co.datepromised");
		return sql.toString();
	}

	private String getQueryForOpenMO()
	{
		StringBuilder sql = new StringBuilder(
				"SELECT mprod.m_production_id, mp.m_product_id, mp.name as productname, sum(mprod.productionqty) as orderedqty, ");
		sql.append(" (CASE WHEN TO_CHAR(?::DATE, 'IYYY') <> TO_CHAR(mprod.datepromised::DATE, 'IYYY')	THEN EXTRACT(WEEK FROM (DATE_TRUNC('YEAR', mprod.datepromised::DATE) + interval '-1 day')) ELSE 0 END) + EXTRACT(WEEK FROM mprod.datepromised::DATE) as weekordered ");
		sql.append(" FROM m_production mprod JOIN m_product mp ON(mp.m_product_id=mprod.m_product_id) JOIN m_product_category mpc ON mp.m_product_category_id = mpc.m_product_category_id WHERE mp.isactive='Y' AND mprod.isactive='Y' AND mprod.processed='N'");
		sql.append(" AND mprod.datepromised BETWEEN ? AND ?");
		sql.append(" AND mprod.AD_Client_ID = ? AND mprod.m_product_id =ANY(?)");
		sql.append(" GROUP BY weekordered, mp.m_product_id, mprod.m_production_id");
		sql.append(" ORDER BY mp.m_product_id, mprod.datepromised ");
		return sql.toString();
	}

	private String getQueryForBOMProductExplode()
	{
		StringBuilder sql = new StringBuilder("SELECT M_ProductBOM_ID, BOMQty * ?::Numeric AS RequiredQty ")
				.append("FROM M_Product_BOM WHERE M_Product_ID = ?");

//		StringBuilder sql = new StringBuilder(" WITH RECURSIVE supplytree(M_Product_ID) AS ( ")
//				.append(" 	SELECT M_Product_ID, M_ProductBOM_ID, BOMQty, BOMQty * ?::Numeric AS RequiredQty, 1 AS Level ")
//				.append("	FROM M_Product_BOM	WHERE M_Product_ID = ? ")
//				.append(" UNION ALL ")
//				.append("	SELECT b.M_Product_ID, b.M_ProductBOM_ID, b.BOMQty, b.BOMQty * sp.RequiredQty AS RequiredQty, Level + 1 ")
//				.append("	FROM M_Product_BOM AS b INNER JOIN supplytree AS sp ON (sp.M_ProductBOM_ID = b.M_Product_ID)	WHERE b.IsActive='Y' ")
//				.append(" ) ")
//				.append(" SELECT  M_Product_ID, M_ProductBOM_ID, BOMQty, RequiredQty, Level		FROM supplytree	WHERE Level = 1ORDER BY Level ");

		return sql.toString();
	}


	private String getQueryForOpenRequisition()
	{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT r.M_Requisition_ID, mp.M_Product_ID, mp.Name AS ProductName, SUM(rl.Qty) AS OrderedQty,")
				.append(" (CASE WHEN TO_CHAR(?::DATE, 'IYYY') <> TO_CHAR(r.DateRequired::DATE, 'IYYY')	THEN EXTRACT(WEEK FROM (DATE_TRUNC('YEAR', r.DateRequired::DATE) + interval '-1 day')) ELSE 0 END) + EXTRACT(WEEK FROM r.DateRequired::DATE) as weekordered ")
				.append(", r.DateRequired ");
		sql.append(" FROM M_Requisition r ")
				.append(" INNER JOIN M_RequisitionLine rl ON (r.M_Requisition_ID = rl.M_Requisition_ID AND rl.IsActive = 'Y') ")
				.append(" INNER JOIN M_Product mp ON (mp.M_Product_ID = rl.M_Product_ID AND mp.IsActive = 'Y') ")
				.append(" WHERE  r.DocStatus IN ('DR') AND r.DateRequired BETWEEN ? AND ? AND mp.AD_Client_ID = ? AND rl.M_Product_ID =ANY(?) AND r.C_DocType_ID = ? ")
				.append(" GROUP BY WeekOrdered, mp.M_Product_ID, r.M_Requisition_ID ")
				.append(" ORDER BY mp.M_Product_ID, r.DateRequired ");
		return sql.toString();
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

		public int hashCode()
		{
			return new HashCodeBuilder(17, 31).append(M_Product_ID).toHashCode();
		}

		public boolean equals(Object obj)
		{
			if (!(obj instanceof MiniMRPProduct))
				return false;
			if (obj == this)
				return true;

			MiniMRPProduct rhs = (MiniMRPProduct) obj;
			return new EqualsBuilder().append(M_Product_ID, rhs.M_Product_ID).isEquals();
		}

		public void addMatireals(int productId, BigDecimal qty)
		{
			if (requiredProdMaterials.containsKey(productId))
				qty = qty.add(requiredProdMaterials.get(productId));
			requiredProdMaterials.put(productId, qty);
		}

		/**
		 * @param weekPromised
		 * @return
		 */
		private int getOrderProjectedWeek(int weekPromised)
		{
			int projectdOrderWeek = 0;
			if (START_WEEK > END_WEEK)
			{
				weekPromised = weekPromised + 52;
			}
			if (START_WEEK == weekPromised || (weekPromised - getProjectedLeadTimeInWeek()) < START_WEEK)
			{
				projectdOrderWeek = START_WEEK;
			}
			else
			{
				projectdOrderWeek = weekPromised - getProjectedLeadTimeInWeek();
			}

			return projectdOrderWeek;
		}

		public void addDemand(int mOrderID, BigDecimal netRequiredQty, int projectdOrderWeek)
		{
			Map<Integer, BigDecimal> weekDemand = getOrderDemand(mOrderID);
			weekDemand.put(projectdOrderWeek, netRequiredQty);
		}

		public void explodeDemand(int mOrderID, BigDecimal orderQty, int weekPromised,
				Map<Integer, MiniMRPProduct> miniMrpProducts)
		{
			BigDecimal netRequiredQty = orderQty; // explod(orderQty);
			// int projectdOrderWeek = getOrderProjectedWeek(weekPromised);
			addDemand(mOrderID, netRequiredQty, weekPromised);
			// explodMaterialDemand(mOrderID, orderQty, weekPromised,
			// miniMrpProducts);
		}

		public Map<Integer, Map<Integer, BigDecimal>> getDemand()
		{
			return demand;
		}

		public Map<Integer, BigDecimal> getOrderDemand(int mOrderID)
		{
			Map<Integer, BigDecimal> weekDemand = null;
			if (demand.containsKey(mOrderID))
			{
				weekDemand = demand.get(mOrderID);
			}
			else
			{
				weekDemand = new HashMap<Integer, BigDecimal>();
				demand.put(mOrderID, weekDemand);
			}
			return weekDemand;
		}

		public void setSupplyLinePO(int c_order_id, int week, BigDecimal openPOqty)
		{
			Map<Integer, BigDecimal> weekDemand = null;
			if (supplyLinePO.containsKey(c_order_id))
			{
				weekDemand = supplyLinePO.get(c_order_id);
				openPOqty = openPOqty.add(weekDemand.get(week));
				weekDemand.put(week, openPOqty);
			}
			else
			{
				weekDemand = new HashMap<Integer, BigDecimal>();
				weekDemand.put(week, openPOqty);
			}
			supplyLinePO.put(c_order_id, weekDemand);
		}

		public void setSupplyLineMO(int m_production_id, int week, BigDecimal openMOqty)
		{
			Map<Integer, BigDecimal> weekDemand = null;
			if (supplyLineMO.containsKey(m_production_id))
			{
				weekDemand = supplyLineMO.get(m_production_id);
				openMOqty = openMOqty.add(weekDemand.get(week));
				weekDemand.put(week, openMOqty);
			}
			else
			{
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

		/**
		 * @param mOrderID
		 * @param orderQty
		 * @param miniMrpProducts
		 * @param projectdOrderWeek
		 */
		public void explodMaterialDemand(int mOrderID, BigDecimal orderQty, int weekPromised,
				Map<Integer, MiniMRPProduct> miniMrpProducts)
		{
			if (!requiredProdMaterials.isEmpty())
			{
				for (Integer productId : requiredProdMaterials.keySet())
				{
					MiniMRPProduct materials = miniMrpProducts.get(productId);
					BigDecimal totalRequiredQty = requiredProdMaterials.get(productId).multiply(orderQty);
					int projectdOrderWeek = materials.getOrderProjectedWeek(weekPromised);
					materials.addDemand(mOrderID, totalRequiredQty, projectdOrderWeek);
				}
			}
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
		int wk = week - START_WEEK;

		switch (wk)
		{
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
