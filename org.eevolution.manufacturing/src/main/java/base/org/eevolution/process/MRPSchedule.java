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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;

import org.compiere.model.MForecastLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MRequisition;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.eevolution.form.Browser;
import org.eevolution.model.I_PP_MRP;
import org.eevolution.model.MDDOrder;
import org.eevolution.model.MDDOrderLine;
import org.eevolution.model.MPPMRP;
import org.eevolution.model.MPPOrder;

/**
 * 
 * MRP Schedule
 * 
 * @author victor.perez@e-evolution.com , www.e-evolution.com
 */
public class MRPSchedule extends SvrProcess {

	/**
	 * Prepare - e.g., get Parameters.
	 */
	protected Integer p_Planner_ID = null;
	protected String p_Priority = null;
	protected Integer p_Line = null;
	protected LinkedHashMap<Integer, LinkedHashMap<String, Object>> m_values = null;
	protected List<MPPMRP> m_records = null;
	protected String DEMAND = "DEMAND";
	protected String SUPPLY = "SUPPLY";
	protected int processRecords = 0;

	protected void prepare() {
		for (ProcessInfoParameter para : getParameter()) {
			String name = para.getParameterName();
			if (para.getParameter() == null)
				;
			else if (name.equals(MPPMRP.COLUMNNAME_Planner_ID))
				p_Planner_ID = para.getParameterAsInt();
			else if (name.equals(MPPMRP.COLUMNNAME_Priority))
				p_Priority = (String) para.getParameter();
			else if (name.equals(MPPOrder.COLUMNNAME_Line))
				p_Line = para.getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
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

		for (MPPMRP mrp : getRecords()) {

			if (MPPMRP.TYPEMRP_Demand.equals(mrp.getTypeMRP()))
				saveBrowseValues(mrp, DEMAND);
			else if (MPPMRP.TYPEMRP_Supply.equals(mrp.getTypeMRP()))
				saveBrowseValues(mrp, SUPPLY);

			if (p_Priority != null)
				mrp.setPriority(p_Priority);

			if (p_Planner_ID != null)
				mrp.setPlanner_ID(p_Planner_ID);

			schedule(mrp);
		}

		return "@DocProcessed@ " + processRecords;
	} // doIt

	private void schedule(MPPMRP mrp) {

		if(!mrp.is_Changed())
			return;
		
		processRecords ++;
		
		if (MPPMRP.ORDERTYPE_Forecast.equals(mrp.getOrderType())) {

			MForecastLine forecastLine = (MForecastLine) mrp.getM_ForecastLine();
			if (forecastLine != null) {
				forecastLine.setDatePromised(mrp.getDatePromised());
				forecastLine.setSalesRep_ID(mrp.getPlanner_ID());
				forecastLine.saveEx();
			}
		}
		else if (MPPMRP.ORDERTYPE_MaterialRequisition.equals(mrp.getOrderType())) {

			MRequisition requisition = (MRequisition) mrp.getM_Requisition();
			if (requisition != null) {
				requisition.setDateDoc(mrp.getDateStartSchedule());
				requisition.setDateRequired(mrp.getDatePromised());
				requisition.setAD_User_ID(mrp.getPlanner_ID());
				requisition.setPriorityRule(mrp.getPriority());
				requisition.saveEx();
			}
		}
		else if (MPPMRP.ORDERTYPE_ManufacturingOrder.equals(mrp.getOrderType())) {
			MPPOrder order = (MPPOrder) mrp.getPP_Order();
			if (order != null) {
				order.setDateStartSchedule(mrp.getDateStartSchedule());
				order.setDateFinishSchedule(mrp.getDateFinishSchedule());
				order.setDatePromised(mrp.getDatePromised());
				order.setPlanner_ID(mrp.getPlanner_ID());
				if (mrp.getPriority() != null)
					order.setPriorityRule(mrp.getPriority());

				order.setLine(p_Line);
				order.saveEx();
			}
		}
		else if (MPPMRP.ORDERTYPE_DistributionOrder.equals(mrp.getOrderType())) {
			MDDOrderLine orderLine = (MDDOrderLine) mrp.getDD_OrderLine();
			if (orderLine != null) {
				orderLine.setDateOrdered(mrp.getDateStartSchedule());
				orderLine.setDatePromised(mrp.getDatePromised());
				orderLine.saveEx();

				MDDOrder order = orderLine.getParent();
				order.setSalesRep_ID(mrp.getPlanner_ID());
				order.setPriorityRule(mrp.getPriority());
				order.saveEx();
			}
		}
		else if (MPPMRP.ORDERTYPE_SalesOrder.equals(mrp.getOrderType())) {
			MOrderLine orderLine = (MOrderLine) mrp.getC_OrderLine();
			if (orderLine != null) {
				orderLine.setDateOrdered(mrp.getDateStartSchedule());
				orderLine.setDatePromised(mrp.getDatePromised());
				orderLine.saveEx();

				MOrder order = orderLine.getParent();
				order.setSalesRep_ID(mrp.getPlanner_ID());
				order.setPriorityRule(mrp.getPriority());
				order.saveEx();
			}
		}
		else if (MPPMRP.ORDERTYPE_PurchaseOrder.equals(mrp.getOrderType())) {
			MOrderLine orderLine = (MOrderLine) mrp.getC_OrderLine();
			if (orderLine != null) {
				orderLine.setDateOrdered(mrp.getDateStartSchedule());
				orderLine.setDatePromised(mrp.getDatePromised());
				orderLine.saveEx();

				MOrder order = orderLine.getParent();
				order.setSalesRep_ID(mrp.getPlanner_ID());
				order.setPriorityRule(mrp.getPriority());
				order.saveEx();
			}
		}

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

	private List<MPPMRP> getRecords() {
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

		for (MPPMRP record : getRecords()) {
			m_values.put(
					record.get_ID(),
					Browser.getBrowseValues(getAD_PInstance_ID(), null,
							record.get_ID(), null));
		}
		return m_values;
	}
}
