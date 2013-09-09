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
 * Copyright (C) 2003-2012 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package org.eevolution.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MDocType;
import org.compiere.model.MLocator;
import org.compiere.model.MRequisition;
import org.compiere.model.MRequisitionLine;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.model.X_C_BP_Group;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.wf.MWorkflow;
import org.eevolution.form.Browser;
import org.eevolution.model.I_PP_MRP;
import org.eevolution.model.MDDOrder;
import org.eevolution.model.MDDOrderLine;
import org.eevolution.model.MPPMRP;
import org.eevolution.model.MPPOrder;
import org.eevolution.model.MPPProductBOM;

/**
 * 
 * 
 * @author victor.perez@e-evolution.com , www.e-evolution.com
 */
public class MRPApproval extends SvrProcess {

	/**
	 * Prepare - e.g., get Parameters.
	 */
	protected int Record_ID;
	protected String p_OrderType = null;
	protected String p_Priority = null;
	protected int p_C_BPartner_ID = 0;
	protected String p_ReferenceNo = null;
	protected int p_S_Resource_ID = 0;
	protected int p_AD_Workflow_ID = 0;
	protected int p_PP_Product_BOM_ID = 0;
	protected int p_M_Shipper_ID = 0;
	protected int p_M_Warehouse_ID = 0;
	protected int p_M_Locator_ID = 0;
	protected int p_M_LocatorTo_ID = 0;

	protected String EXECUTION_MODE = MPPMRP.ORDERTYPE_ManufacturingOrder;
	protected LinkedHashMap<Integer, LinkedHashMap<String, Object>> m_values = null;
	protected List<MPPMRP> m_records = null;

	protected void prepare() {
		for (ProcessInfoParameter para : getParameter()) {
			String name = para.getParameterName();
			if (para.getParameter() == null)
				;
			else if (name.equals(MPPMRP.COLUMNNAME_OrderType))
				p_OrderType = (String) para.getParameter();
			else if (name.equals("PriorityRule"))
				p_Priority = (String) para.getParameter();
			else if (name.equals(MPPMRP.COLUMNNAME_C_BPartner_ID))
				p_C_BPartner_ID = para.getParameterAsInt();
			else if (name.equals(MPPProductBOM.COLUMNNAME_PP_Product_BOM_ID))
				p_PP_Product_BOM_ID = para.getParameterAsInt();
			else if (name.equals(MWorkflow.COLUMNNAME_AD_Workflow_ID))
				p_AD_Workflow_ID = para.getParameterAsInt();
			else if (name.equals(MDDOrder.COLUMNNAME_M_Shipper_ID))
				p_M_Shipper_ID = para.getParameterAsInt();
			else if (name.equals("ReferenceNo"))
				p_ReferenceNo = (String) para.getParameter();
			else if (name.equals(MPPMRP.COLUMNNAME_M_Warehouse_ID))
				p_M_Warehouse_ID = para.getParameterAsInt();
			else if (name.equals(MDDOrderLine.COLUMNNAME_M_Locator_ID))
				p_M_Locator_ID = para.getParameterAsInt();
			else if (name.equals(MDDOrderLine.COLUMNNAME_M_LocatorTo_ID))
				p_M_LocatorTo_ID = para.getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		Record_ID = getRecord_ID();
		int AD_Process_ID = getProcessInfo().getAD_Process_ID();
		// Manufacturing Order Approval
		if (AD_Process_ID == 53322)
			EXECUTION_MODE = MPPMRP.ORDERTYPE_ManufacturingOrder;
		// Distribution Order Approval
		if (AD_Process_ID == 53323)
			EXECUTION_MODE = MPPMRP.ORDERTYPE_DistributionOrder;
		// Requisition Approval
		if (AD_Process_ID == 53321)
			EXECUTION_MODE = MPPMRP.ORDERTYPE_MaterialRequisition;

		setColumnsValues();
	} // prepare

	/**
	 * Perform process.
	 * 
	 * @return Message (clear text)
	 * @throws Exception
	 *             if not successful
	 */
	protected String doIt() throws Exception {

		for (MPPMRP mrp : getMRPRecords()) {

			saveBrowseValues(mrp, "MRP");

			if (p_Priority != null)
				mrp.setPriority(p_Priority);

			if (p_OrderType != null && !mrp.getOrderType().equals(p_OrderType)) {
				createSupply(mrp, p_OrderType);
				continue;
			}

			if (EXECUTION_MODE.equals(MPPMRP.ORDERTYPE_ManufacturingOrder))
				executeManufacturingOrderApproval(mrp);

			if (EXECUTION_MODE.equals(MPPMRP.ORDERTYPE_DistributionOrder)) 
				executeDistributionOrderApproval(mrp);

			if (EXECUTION_MODE.equals(MPPMRP.ORDERTYPE_MaterialRequisition)) {
				if (p_C_BPartner_ID > 0)
					mrp.setC_BPartner_ID(p_C_BPartner_ID);

				executeRequisitionApproval(mrp);
			}
		}

		return null;
	} // doIt

	private void createSupply(MPPMRP mrp, String orderType) {

		if (MPPMRP.ORDERTYPE_MaterialRequisition.equals(orderType)) {
			if (p_C_BPartner_ID <= 0)
				throw new AdempiereException("@BPartnerNotFound@");

			createRequisition(mrp);

		}
		if (MPPMRP.ORDERTYPE_ManufacturingOrder.equals(orderType)
				&& p_S_Resource_ID > 0 && p_PP_Product_BOM_ID > 0
				&& p_AD_Workflow_ID > 0) {

			createManufacturingOrder(mrp);
		}

		if (MPPMRP.ORDERTYPE_DistributionOrder.equals(orderType)
				&& p_M_Warehouse_ID > 0 && p_M_Shipper_ID > 0
				&& p_M_Locator_ID > 0 && p_M_LocatorTo_ID > 0
				&& p_C_BPartner_ID > 0) {

			createDistributionOrder(mrp);
		}

		if (MPPMRP.ORDERTYPE_MaterialRequisition.equals(mrp.getOrderType())) {
			MRequisition requisition = (MRequisition) mrp.getM_Requisition();
			requisition.deleteEx(true);
		}
		if (MPPMRP.ORDERTYPE_ManufacturingOrder.equals(mrp.getOrderType())) {
			MPPOrder order = (MPPOrder) mrp.getPP_Order();
			order.deleteEx(true);
		}
		if (MPPMRP.ORDERTYPE_DistributionOrder.equals(mrp.getOrderType())) {
			MDDOrder order = (MDDOrder) mrp.getDD_Order();
			order.deleteEx(true);
		}

	}

	private void createDistributionOrder(MPPMRP mrp) {

		MLocator locator = MLocator.get(mrp.getCtx(), p_M_Locator_ID);
		MLocator locator_to = MLocator.get(mrp.getCtx(), p_M_LocatorTo_ID);
		int docTypeDO_ID = MPPMRP.getDocType(getCtx(),
				MDocType.DOCBASETYPE_DistributionOrder,
				locator_to.getAD_Org_ID(), Env.getAD_User_ID(getCtx()),
				get_TrxName());
		MBPartner bp = MBPartner.get(mrp.getCtx(), p_C_BPartner_ID);

		MDDOrder order = new MDDOrder(getCtx(), 0, get_TrxName());
		order.setAD_Org_ID(mrp.getAD_Org_ID());
		order.addDescription("Generated by MRP");
		order.setC_BPartner_ID(p_C_BPartner_ID);
		order.setAD_User_ID(bp.getPrimaryAD_User_ID());
		order.setC_DocType_ID(docTypeDO_ID);
		order.setM_Warehouse_ID(p_M_Warehouse_ID);
		if(p_ReferenceNo != null)
			order.setPOReference(p_ReferenceNo);
		order.setDocStatus(MDDOrder.DOCSTATUS_Drafted);
		order.setDocAction(MDDOrder.DOCACTION_Complete);
		order.setDateOrdered(mrp.getDateFinishSchedule());
		order.setDatePromised(mrp.getDatePromised());
		order.setM_Shipper_ID(p_M_Shipper_ID);
		if (p_Priority != null)
			order.setPriorityRule(p_Priority);
		order.setIsInDispute(false);
		order.setIsInTransit(false);
		order.setSalesRep_ID(mrp.getPlanner_ID());
		order.saveEx();

		MDDOrderLine oline = new MDDOrderLine(getCtx(), 0, get_TrxName());
		oline.setDD_Order_ID(order.getDD_Order_ID());
		oline.setAD_Org_ID(locator_to.getAD_Org_ID());
		oline.setM_Locator_ID(locator.getM_Locator_ID());
		oline.setM_LocatorTo_ID(locator_to.getM_Locator_ID());
		oline.setM_Product_ID(mrp.getM_Product_ID());
		oline.setDateOrdered(order.getDateOrdered());
		oline.setDatePromised(mrp.getDatePromised());
		oline.setQtyEntered((BigDecimal) getBrowseRowValue("MRP",
				MPPMRP.COLUMNNAME_Qty, mrp.getPP_MRP_ID()));
		oline.setQtyOrdered((BigDecimal) getBrowseRowValue("MRP",
				MPPMRP.COLUMNNAME_Qty, mrp.getPP_MRP_ID()));
		oline.setConfirmedQty((BigDecimal) getBrowseRowValue("DL",
				MDDOrderLine.COLUMNNAME_ConfirmedQty, mrp.getPP_MRP_ID()));
		oline.setTargetQty(MPPMRP.getQtyReserved(getCtx(),
				locator_to.getM_Warehouse_ID(), mrp.getM_Product_ID(),
				mrp.getDateStartSchedule(), get_TrxName()));
		oline.setIsInvoiced(false);
		oline.saveEx();

		order.processIt(DocAction.ACTION_Prepare);
		order.saveEx();
	}

	private void createRequisition(MPPMRP mrp) {
		if (MPPMRP.ORDERTYPE_ManufacturingOrder.equals(mrp.getOrderType())) {
			int docTypeReq_ID = MPPMRP.getDocType(getCtx(),
					MDocType.DOCBASETYPE_PurchaseRequisition,
					mrp.getAD_Org_ID(), Env.getAD_User_ID(getCtx()),
					get_TrxName());
			// Get PriceList from BPartner/Group - teo_sarca, FR [ 2829476 ]
			int M_PriceList_ID = -1;
			final String sql = "SELECT COALESCE(bp."
					+ MBPartner.COLUMNNAME_PO_PriceList_ID
					+ ",bpg."
					+ X_C_BP_Group.COLUMNNAME_PO_PriceList_ID
					+ ")"
					+ " FROM C_BPartner bp"
					+ " INNER JOIN C_BP_Group bpg ON (bpg.C_BP_Group_ID=bp.C_BP_Group_ID)"
					+ " WHERE bp.C_BPartner_ID=?";
			M_PriceList_ID = DB.getSQLValueEx(get_TrxName(), sql,
					p_C_BPartner_ID);

			MRequisition requisition = new MRequisition(getCtx(), 0,
					get_TrxName());
			requisition.setAD_Org_ID(mrp.getAD_Org_ID());
			requisition.setAD_User_ID(mrp.getPlanner_ID());
			requisition.setDateDoc(mrp.getDateStartSchedule());
			requisition.setDateRequired(mrp.getDatePromised());
			// req.setDescription(""); // TODO: add translation
			requisition.setM_Warehouse_ID(mrp.getM_Warehouse_ID());
			requisition.setC_DocType_ID(docTypeReq_ID);
			if (M_PriceList_ID > 0)
				requisition.setM_PriceList_ID(M_PriceList_ID);
			requisition.saveEx();

			MRequisitionLine requisitionLine = new MRequisitionLine(requisition);
			requisitionLine.setLine(10);
			requisitionLine.setAD_Org_ID(mrp.getAD_Org_ID());
			requisitionLine.setC_BPartner_ID(mrp.getC_BPartner_ID());
			requisitionLine.setM_Product_ID(mrp.getM_Product_ID());
			requisitionLine.setPrice();
			if (p_ReferenceNo != null)
				requisitionLine.setDescription(p_ReferenceNo);
			requisitionLine.setPriceActual(Env.ZERO);
			requisitionLine.setQty(mrp.getQty());
			requisitionLine.saveEx();

			requisition.processIt(DocAction.ACTION_Prepare);
			requisition.saveEx();
		}
	}

	private void createManufacturingOrder(MPPMRP mrp) {
		int docTypeMO_ID = MPPMRP.getDocType(getCtx(),
				MDocType.DOCBASETYPE_ManufacturingOrder, mrp.getAD_Org_ID(),
				mrp.getPlanner_ID(), get_TrxName());

		MPPOrder order = new MPPOrder(getCtx(), 0, get_TrxName());
		if (p_ReferenceNo != null)
			order.addDescription(p_ReferenceNo);
		order.setAD_Org_ID(mrp.getAD_Org_ID());
		order.setLine(10);
		order.setC_DocTypeTarget_ID(docTypeMO_ID);
		order.setC_DocType_ID(docTypeMO_ID);
		if (p_Priority != null)
			order.setPriorityRule(p_Priority);

		order.setS_Resource_ID(p_S_Resource_ID);
		order.setM_Warehouse_ID(mrp.getM_Warehouse_ID());
		order.setM_Product_ID(mrp.getM_Product_ID());
		order.setM_AttributeSetInstance_ID(0);
		order.setPP_Product_BOM_ID(p_PP_Product_BOM_ID);
		order.setAD_Workflow_ID(p_AD_Workflow_ID);
		order.setPlanner_ID(mrp.getPlanner_ID());
		order.setDateOrdered(mrp.getDateOrdered());
		order.setDatePromised(mrp.getDatePromised());
		order.setDateStartSchedule(mrp.getDateStartSchedule());
		order.setDateFinishSchedule(mrp.getDateFinishSchedule());
		order.setQty((BigDecimal) getBrowseRowValue("MRP",
				MPPMRP.COLUMNNAME_Qty, mrp.getPP_MRP_ID()));
		// QtyBatchSize : do not set it, let the MO to take it from
		// workflow
		order.setC_UOM_ID(mrp.getM_Product().getC_UOM_ID());
		order.setYield(Env.ZERO);
		order.setScheduleType(MPPMRP.TYPEMRP_Demand);
		order.setPriorityRule(MPPOrder.PRIORITYRULE_Medium);
		order.setDocAction(MPPOrder.DOCACTION_Complete);
		order.saveEx();

		order.processIt(DocAction.ACTION_Prepare);
		order.saveEx();
	}

	private void executeRequisitionApproval(MPPMRP mrp) {
		MRequisition requisition = (MRequisition) mrp.getM_Requisition();
		Timestamp dateRequired = (Timestamp) getBrowseRowValue("R",
				MRequisition.COLUMNNAME_DateRequired, mrp.getPP_MRP_ID());
		if (dateRequired != null)
			requisition.setDateRequired(dateRequired);

		if (mrp.is_Changed())
			;
		{
			validateChanges(mrp, MPPMRP.COLUMNNAME_Priority, requisition,
					MRequisition.COLUMNNAME_PriorityRule);
			requisition.saveEx();

			MRequisitionLine requisitionLine = (MRequisitionLine) mrp
					.getM_RequisitionLine();
			validateChanges(mrp, MPPMRP.COLUMNNAME_C_BPartner_ID,
					requisitionLine, MRequisitionLine.COLUMNNAME_C_BPartner_ID);
			validateChanges(mrp, MPPMRP.COLUMNNAME_Qty, requisitionLine,
					MRequisitionLine.COLUMNNAME_Qty);
			requisitionLine.saveEx();
		}

		requisition.processIt(DocAction.ACTION_Prepare);
		requisition.saveEx();
	}

	private void executeDistributionOrderApproval(MPPMRP mrp) {
		MDDOrder order = (MDDOrder) mrp.getDD_Order();
		if (mrp.is_Changed()) {
			validateChanges(mrp, MPPMRP.COLUMNNAME_Priority, order,
					MDDOrder.COLUMNNAME_PriorityRule);
			order.saveEx();

			MDDOrderLine orderLine = (MDDOrderLine) mrp.getDD_OrderLine();
			orderLine.setQty((BigDecimal) getBrowseRowValue("MRP",
					MPPMRP.COLUMNNAME_Qty, mrp.getPP_MRP_ID()));
			Timestamp datePromised = (Timestamp) getBrowseRowValue("MRP",
					MDDOrder.COLUMNNAME_DatePromised, mrp.getPP_MRP_ID());
			if (datePromised != null)
				orderLine.setDatePromised(datePromised);
			orderLine.saveEx();
		}

		if (p_M_Shipper_ID > 0)
			order.setM_Shipper_ID(p_M_Shipper_ID);

		order.processIt(DocAction.ACTION_Prepare);
		order.saveEx();

	}

	private void executeManufacturingOrderApproval(MPPMRP mrp) {
		boolean createMO = false;
		if (p_PP_Product_BOM_ID > 0)
			createMO = true;
		if (p_AD_Workflow_ID > 0)
			createMO = true;
		MPPOrder currentOrder = (MPPOrder) mrp.getPP_Order();
		MPPOrder newOrder;
		if (createMO) {
			newOrder = new MPPOrder(mrp.getCtx(), 0, get_TrxName());
			newOrder.copyValues(currentOrder, newOrder);
			if (p_PP_Product_BOM_ID > 0)
				newOrder.setPP_Product_BOM_ID(p_PP_Product_BOM_ID);
			if (p_AD_Workflow_ID > 0)
				newOrder.setAD_Workflow_ID(p_AD_Workflow_ID);
			newOrder.saveEx();
			currentOrder.deleteEx(true);
			currentOrder = newOrder;

		}

		if (mrp.is_Changed()) {
			validateChanges(mrp, MPPMRP.COLUMNNAME_Priority, currentOrder,
					MPPOrder.COLUMNNAME_PriorityRule);
			validateChanges(mrp, MPPMRP.COLUMNNAME_DateStartSchedule,
					currentOrder, MPPOrder.COLUMNNAME_DateStartSchedule);
			validateChanges(mrp, MPPMRP.COLUMNNAME_DatePromised, currentOrder,
					MPPOrder.COLUMNNAME_DatePromised);
			currentOrder.setQty((BigDecimal) getBrowseRowValue("MRP",
					MPPMRP.COLUMNNAME_Qty, mrp.getPP_MRP_ID()));
			currentOrder.saveEx();
		}
		currentOrder.processIt(DocAction.ACTION_Prepare);
		currentOrder.saveEx();
	}

	private void saveBrowseValues(PO po, String alias) {

		LinkedHashMap<String, Object> values = m_values.get(po.get_ID());

		for (Entry<String, Object> entry : values.entrySet()) {
			String columnName = entry.getKey();
			if (columnName.contains(alias.toUpperCase() + "_")) {
				columnName = columnName.substring(columnName.indexOf("_") + 1);
				po.set_ValueOfColumn(columnName, entry.getValue());
			}
		}

	}

	private Object getBrowseRowValue(String alias, String columnName,
			int recordId) {

		LinkedHashMap<String, Object> values = m_values.get(recordId);

		for (Entry<String, Object> entry : values.entrySet()) {
			if (entry.getKey().contains(alias.toUpperCase() + "_" + columnName))
				return entry.getValue();
		}
		return null;
	}

	private void validateChanges(MPPMRP mrp, String columnSource, PO po,
			String columnTarget) {
		if (mrp.is_ValueChanged(columnSource))
			po.set_ValueOfColumn(columnTarget, mrp.get_Value(columnSource));
	}

	private List<MPPMRP> getMRPRecords() {
		if (m_records != null)
			return m_records;

		String whereClause = "EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE  T_Selection.AD_PInstance_ID=? AND T_Selection.T_Selection_ID=PP_MRP.PP_MRP_ID)";
		m_records = new Query(getCtx(), I_PP_MRP.Table_Name, whereClause,
				get_TrxName()).setClient_ID()
				.setParameters(getAD_PInstance_ID()).list();
		return m_records;
	}

	private LinkedHashMap<Integer, LinkedHashMap<String, Object>> setColumnsValues() {
		if (m_values != null)
			return m_values;

		m_values = new LinkedHashMap<Integer, LinkedHashMap<String, Object>>();

		for (MPPMRP record : getMRPRecords()) {
			m_values.put(
					record.get_ID(),
					Browser.getBrowseValues(getAD_PInstance_ID(), null,
							record.get_ID(), null));
		}
		return m_values;
	}
} // Create
