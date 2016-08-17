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
import java.util.Map.Entry;

import org.compiere.model.MForecastLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MRequisition;
import org.compiere.model.PO;
import org.eevolution.model.MDDOrder;
import org.eevolution.model.MDDOrderLine;
import org.eevolution.model.MPPMRP;
import org.eevolution.model.MPPOrder;

/**
 * 
 * MRP Schedule
 * @author victor.perez@e-evolution.com , www.e-evolution.com
 */
public class MRPSchedule extends MRPScheduleAbstract {


	protected Integer lineNo = null;
	protected String DEMAND = "DEMAND";
	protected String SUPPLY = "SUPPLY";
	protected Integer processRecords = 0;

	protected void prepare() {
		super.prepare();
	} // prepare

	/**
	 * Perform process.
	 * @return Message (clear text)
	 * @throws Exception if not successful
	 */
	protected String doIt() throws Exception {

		getSelectionKeys().stream().filter(mrpId -> mrpId > 0 ).forEach(mrpId -> {
			MPPMRP mrp = new MPPMRP(getCtx() , mrpId , get_TrxName());
			if (MPPMRP.TYPEMRP_Demand.equals(mrp.getTypeMRP()))
				saveBrowseValues(mrp, DEMAND);
			else if (MPPMRP.TYPEMRP_Supply.equals(mrp.getTypeMRP()))
				saveBrowseValues(mrp, SUPPLY);

			if (getPriority() != null)
				mrp.setPriority(getPriority());

			if (getPlannerId() > 0 )
				mrp.setPlanner_ID(getPlannerId());

			schedule(mrp);
		});

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

				order.setLine(lineNo);
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

		LinkedHashMap<String, Object> values = getSelectionValues().get(po.get_ID());

		for (Entry<String, Object> entry : values.entrySet()) {
			String columnName = entry.getKey();
			if (columnName.contains(alias.toUpperCase() + "_")) {
				columnName = columnName.substring(columnName.indexOf("_") + 1);
				po.set_ValueOfColumn(columnName, entry.getValue());
			}
		}

	}
}
