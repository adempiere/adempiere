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
 *****************************************************************************/
package org.eevolution.wms.model.validator;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.eevolution.distribution.model.MDDOrderLine;
import org.eevolution.wms.model.MWMInOutBoundLine;

import java.math.BigDecimal;

/**
 * Libero Validator
 * 
 * @author Victor Perez
 */
public class WarehouseManagement implements ModelValidator {
	/** Context variable which says if libero manufacturing is enabled */
	public static final String CTX_IsLiberoWMEnabled = "#IsLiberoWMEnabled";

	/** Logger */
	private CLogger log = CLogger.getCLogger(getClass());
	/** Client */
	private int m_AD_Client_ID = -1;

	public void initialize(ModelValidationEngine engine, MClient client) {
		if (client != null) {
			m_AD_Client_ID = client.getAD_Client_ID();
		}
		engine.addModelChange(MDDOrderLine.Table_Name, this);
	} // initialize

	public String modelChange(PO po, int type) throws Exception {
		boolean IsLiberoEnabled = "Y".equals(Env.getContext(po.getCtx(),
				"#IsLiberoEnabled"));
		log.info(po.get_TableName() + " Type: " + type);
		if (!IsLiberoEnabled)
			return null;

		if (po instanceof MDDOrderLine
		&& (TYPE_AFTER_CHANGE == type && po.is_ValueChanged(MDDOrderLine.COLUMNNAME_QtyDelivered))) {
			MDDOrderLine orderLine = (MDDOrderLine) po;
			MWMInOutBoundLine outboundLine = new MWMInOutBoundLine(orderLine.getCtx(), orderLine.getWM_InOutBoundLine_ID(), orderLine.get_TrxName());
			if (outboundLine != null
			&& outboundLine.getWM_InOutBoundLine_ID() > 0
			&& orderLine.getQtyOrdered().compareTo(orderLine.getQtyDelivered()) >= 0) {
				BigDecimal pickedQuantity = outboundLine.getPickedQty();
				BigDecimal totalPickedQuantity = pickedQuantity.add(orderLine.getQtyDelivered());
				outboundLine.setPickedQty(totalPickedQuantity);
				outboundLine.saveEx();
			}
		}
		return null;
	} // modelChange

	public String docValidate(PO po, int timing) {
		log.info(po.get_TableName() + " Timing: " + timing);
		return null;
	} // docValidate

	/**
	 * User Login. Called when preferences are set
	 * @param AD_Org_ID org
	 * @param AD_Role_ID role
	 * @param AD_User_ID user
	 * @return error message or null
	 */
	public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) {
		Env.setContext(Env.getCtx(), CTX_IsLiberoWMEnabled, true);
		return null;
	} // login

	/**
	 * Get Client to be monitored
	 * @return AD_Client_ID client
	 */
	public int getAD_Client_ID() {
		return m_AD_Client_ID;
	} // getAD_Client_ID
} // LiberoWarehouseValidator
