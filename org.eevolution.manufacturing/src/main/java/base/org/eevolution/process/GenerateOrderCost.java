/**
 * Copyright (C) 2003-2017, e-Evolution Consultants S.A. , http://www.e-evolution.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 * Created by victor.perez@e-evolution.com , www.e-evolution.com
 */

package org.eevolution.process;

import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.eevolution.model.I_PP_Order;
import org.eevolution.model.I_PP_Order_Cost;
import org.eevolution.model.MPPOrder;
import org.eevolution.model.MPPOrderCost;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * Generate Order Cost
 */
public class GenerateOrderCost extends GenerateOrderCostAbstract
{
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		super.prepare();
	}	//	prepare


	/**
	 * 	Generate Movements
	 *	@return info
	 *	@throws Exception
	 */
	protected String doIt () throws Exception
	{

		for (MPPOrder order : getOrders()) {
            for (MPPOrderCost cost : getOrderCost(order))
                cost.deleteEx(true);

            order.createStandardCosts();
        }
		return "@Ok@";
	}	//	generate

    private List<MPPOrder> getOrders() {
        StringBuilder whereClause = new StringBuilder();
        List<Object> parameters = new ArrayList<Object>();
        whereClause.append(MPPOrder.COLUMNNAME_DocStatus).append("=? AND ");
        parameters.add(MPPOrder.DOCSTATUS_Completed);
        if (getManufacturingOrderId() > 0)
        {
            whereClause.append(I_PP_Order.COLUMNNAME_PP_Order_ID).append("=? AND ");
            parameters.add(getManufacturingOrderId());
        }

        whereClause.append("TRUNC(DateOrdered, 'DD') BETWEEN ")
                .append(DB.TO_DATE(getDateOrdered(), true)).append(" AND ")
                .append(DB.TO_DATE(getDateOrderedTo(), true));

        return new Query(getCtx() , MPPOrder.Table_Name , whereClause.toString() , getTableName())
				.setClient_ID()
                .setParameters(parameters)
                .list();
    }

	private List<MPPOrderCost> getOrderCost(MPPOrder order) {
		StringBuilder whereClause = new StringBuilder();
		List<Object> parameters = new ArrayList<Object>();
		whereClause.append(I_PP_Order_Cost.COLUMNNAME_PP_Order_ID).append("=? ");
		parameters.add(order.getPP_Order_ID());
		return new Query(getCtx() , MPPOrderCost.Table_Name , whereClause.toString() , getTableName())
				.setClient_ID()
				.setParameters(parameters)
				.list();
	}
}
