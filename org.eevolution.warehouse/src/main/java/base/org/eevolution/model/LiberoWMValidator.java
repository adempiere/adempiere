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
package org.eevolution.model;

import org.compiere.model.MClient;
import org.compiere.model.MInOut;
import org.compiere.model.MInvoice;
import org.compiere.model.MMovement;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

import java.math.BigDecimal;
import java.util.List;

/**
 * Libero Validator
 * 
 * @author Victor Perez
 */
public class LiberoWMValidator implements ModelValidator {
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
		engine.addDocValidate(MInOut.Table_Name, this);
		engine.addDocValidate(MMovement.Table_Name, this);
		engine.addDocValidate(MInvoice.Table_Name, this);
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
			MWMInOutBoundLine outboundLine = (MWMInOutBoundLine) orderLine.getWM_InOutBoundLine();
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
		if(timing == TIMING_AFTER_COMPLETE) {
			if(po.get_TableName().equals(MInOut.Table_Name) 
					|| po.get_TableName().equals(MMovement.Table_Name)) {
				//	Set reference
				if(po.get_TableName().equals(MInOut.Table_Name)) {
					MInOut inOut = (MInOut) po;
					if(inOut.getReversal_ID() > 0) {
						return null;
					}
				} else if(po.get_TableName().equals(MMovement.Table_Name)) {
					MMovement movement = (MMovement) po;
					if(movement.getReversal_ID() > 0) {
						return null;
					}
				}
				verifyIsDelivered(getOutboundFromDocument(po.get_TableName(), po.get_ID(), po.get_TrxName()));
			}
		} else if(timing == TIMING_BEFORE_REVERSECORRECT
				|| timing == TIMING_BEFORE_VOID) {
			if(po.get_TableName().equals(MInOut.Table_Name) 
					|| po.get_TableName().equals(MMovement.Table_Name)
					|| po.get_TableName().equals(MInvoice.Table_Name)) {
				if(po.get_TableName().equals(MInOut.Table_Name)) {
					MInOut inOut = (MInOut) po;
					if(inOut.getReversal_ID() > 0) {
						return null;
					}
				} else if(po.get_TableName().equals(MMovement.Table_Name)) {
					MMovement movement = (MMovement) po;
					if(movement.getReversal_ID() > 0) {
						return null;
					}
				}
				//	
				unsetReference(po.get_TableName(), po.get_ID(), po.get_TrxName());
			}
		}
		return null;
	} // docValidate
	
	/**
	 * Unset reference from Movements and Shipments
	 * @param tableName
	 * @param recordId
	 * @param trxName
	 */
	private void unsetReference(String tableName, int recordId, String trxName) {
		List<MWMInOutBound> outboundList = getOutboundFromDocument(tableName, recordId, trxName);
		String whereClauseForTable = "WM_InOutBoundLine.M_InOut_ID = ?";
		if(tableName.equals(MMovement.Table_Name)) {
			whereClauseForTable = "WM_InOutBoundLine.M_Movement_ID = ?";
		} else if(tableName.equals(MInvoice.Table_Name)) {
			whereClauseForTable = "WM_InOutBoundLine.C_Invoice_ID = ?";
		}
		StringBuffer whereClause = new StringBuffer("EXISTS(SELECT 1 FROM WM_InOutBound b WHERE WM_InOutBound_ID = WM_InOutBoundLine.WM_InOutBound_ID AND b.DocStatus = 'CO') ")
				.append(" AND ").append(whereClauseForTable);
		//	Get from Query
		List<MWMInOutBoundLine> outboundLineList = new Query(Env.getCtx(), MWMInOutBoundLine.Table_Name, whereClause.toString(), trxName)
				.setParameters(recordId)
				.setClient_ID()
				.list();
		//	Validate null
		if(outboundLineList != null) {
			outboundLineList.stream().forEach(outboundLine -> {
				if(tableName.equals(MInvoice.Table_Name)) {
					outboundLine.setC_Invoice_ID(-1);
					outboundLine.setC_InvoiceLine_ID(-1);
				} else {
					outboundLine.setM_Movement_ID(-1);
					outboundLine.setM_MovementLine_ID(-1);
					outboundLine.setM_InOut_ID(-1);
					outboundLine.setM_InOutLine_ID(-1);
				}
				outboundLine.saveEx();
			});
		}
		//	Set reference
		verifyIsDelivered(outboundList);
	}
	
	/**
	 * Get list of Outbound orders from generated documents
	 * @param tableName
	 * @param recordId
	 * @param trxName
	 * @return
	 */
	private List<MWMInOutBound> getOutboundFromDocument(String tableName, int recordId, String trxName) {
		String whereClauseForTable = "bl.M_InOut_ID = ?";
		if(tableName.equals(MMovement.Table_Name)) {
			whereClauseForTable = "bl.M_Movement_ID = ?";
		} else if(tableName.equals(MInvoice.Table_Name)) {
			whereClauseForTable = "bl.C_Invoice_ID = ?";
		}
		//	
		StringBuffer whereClause = new StringBuffer("WM_InOutBound.DocStatus = 'CO' "
				+ "AND EXISTS(SELECT 1 FROM WM_InOutBoundLine bl WHERE WM_InOutBound_ID = WM_InOutBound.WM_InOutBound_ID")
				.append(" AND ").append(whereClauseForTable).append(")");
		//	Get from Query
		return new Query(Env.getCtx(), MWMInOutBound.Table_Name, whereClause.toString(), trxName)
				.setParameters(recordId)
				.setClient_ID()
				.list();
		//	Validate null
	}
	
	/**
	 * Verify check IsDelivered and set Y/N from in/out or movement
	 * @param tableName
	 * @param recordId
	 * @param trxName
	 */
	private void verifyIsDelivered(List<MWMInOutBound> outboundList) {
		if(outboundList == null) {
			return;
		}
		//	Do it
		outboundList.stream().forEach(outbound -> {
			//	Get delivered values
			int inoutBoundLineId = DB.getSQLValueEx(outbound.get_TrxName(), "SELECT WM_InOutBoundLine_ID FROM WM_InOutBoundLine WHERE WM_InOutBound_ID = ? "
					+ "AND (M_InOut_ID IS NULL AND M_Movement_ID IS NULL)", outbound.getWM_InOutBound_ID());
			//	Set
			outbound.setIsDelivered((inoutBoundLineId <= 0));
			outbound.saveEx();
		});
	}

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
