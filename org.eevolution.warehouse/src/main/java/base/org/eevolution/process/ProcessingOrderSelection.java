/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2003-2016 e-Evolution Consultants. All Rights Reserved.      *
 * Copyright (C) 2003-2016 Victor Pèrez Juárez 								  *
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
 * Contributor(s): Victor Pérez Juúrez  (victor.perez@e-evolution.com)		  *
 * Sponsors: e-Evolution Consultants (http://www.e-evolution.com/)            *
 *****************************************************************************/

package org.eevolution.process;

import org.compiere.model.MOrder;
import org.eevolution.model.MWMInOutBound;
import org.eevolution.model.MWMInOutBoundLine;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/** Generated Process for (Purchase Order Selection)
 *  @author ADempiere (generated) 
 *  @version Release 3.8.2
 */
public class ProcessingOrderSelection extends ProcessingOrderSelectionAbstract
{
	@Override
	protected void prepare()
	{
		super.prepare();
	}

	@Override
	protected String doIt() throws Exception
	{
		List<MOrder> orders = (List<MOrder>) getInstancesForSelection(get_TrxName());
		if (getRecord_ID() > 0 && getSelectionKeys().size() > 0 && getTableSelectionId() == MWMInOutBound.Table_ID)
		{
			createInboundLineFrom(orders);
		}
		else if (getRecord_ID() == 0 && getSelectionKeys().size() > 0)
		{
			updatingOrderProperties(orders);
		}
		return "";
	}

	/**
	 * Updating Order properties
	 * @param orders
	 */
	private void updatingOrderProperties(List<MOrder> orders) {
		orders.stream().filter(order -> order != null)
				.forEach(order -> {
					Optional.ofNullable(getSelectionAsTimestamp(order.get_ID(), getPrefixAliasForTableSelection() + MOrder.COLUMNNAME_DatePromised))
							.ifPresent(datePromised -> order.setDatePromised(datePromised));
					if (order.is_Changed())
						order.saveEx();
				});


	}

	/**
	 * Create Inboud line form Order
	 * @param orders
	 */
	private void createInboundLineFrom(List<MOrder> orders) {
		MWMInOutBound inOutBound = (MWMInOutBound) getInstance(get_TrxName());
		AtomicInteger lineNo = new AtomicInteger(10);
		orders.stream()
				.filter(order -> order != null)
				.flatMap(order -> Arrays.stream(order.getLines()))
				.forEach(orderLine -> {
					MWMInOutBoundLine outBoundLine = MWMInOutBoundLine.getByOrderLine(orderLine);
					if (outBoundLine == null) {
						outBoundLine = new MWMInOutBoundLine(inOutBound, orderLine);
						outBoundLine.setLine(lineNo.get());
						outBoundLine.saveEx();
						lineNo.updateAndGet(line -> line  + 10);
					}
				});

	}
}