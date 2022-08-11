/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/

package org.spin.freight.process;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MClientInfo;
import org.eevolution.distribution.model.I_DD_Freight;
import org.eevolution.distribution.model.MDDFreight;
import org.eevolution.distribution.model.MDDFreightLine;
import org.eevolution.wms.model.MWMInOutBound;

/** 
 * 	Generate Freight Order from Outbound
 * 	@author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com>
 * 	See: https://github.com/adempiere/adempiere/issues/2747
 */
public class GenerateFreightOrderFromOutbound extends GenerateFreightOrderFromOutboundAbstract {
	@Override
	protected String doIt() throws Exception {
		MDDFreight freightOrder = new MDDFreight(getCtx(), 0, get_TrxName());
		freightOrder.setWM_InOutBound_ID(getInOutBoundId());
		freightOrder.setDD_Driver_ID(getDriverId());
		freightOrder.setDD_Vehicle_ID(getVehicleId());
		freightOrder.setDateDoc(getDateDoc());
		freightOrder.setM_Shipper_ID(getShipperId());
		if(getDateOrdered() == null) {
			freightOrder.setDateOrdered(getDateDoc());
		} else {
			freightOrder.setDateOrdered(getDateOrdered());
		}
		if(getBPartnerId() > 0) {
			freightOrder.setC_BPartner_ID(getBPartnerId());
		}
		if(getDocTypeId() > 0) {
			freightOrder.setC_DocType_ID(getDocTypeId());
		}
		freightOrder.setDocAction(getDocAction());
		freightOrder.setFreightAmt(getFreightAmt());
		freightOrder.saveEx();
		//	Add lines from Outbound
		MWMInOutBound outbound = new MWMInOutBound(getCtx(), getInOutBoundId(), get_TrxName());
		int lineNo = 10;
		MDDFreightLine line = new MDDFreightLine(getCtx(), 0, get_TrxName());
		line.setDD_Freight_ID(freightOrder.getDD_Freight_ID());
		line.setLine(lineNo);
		line.setWeight(outbound.getWeight());
		line.setVolume(outbound.getVolume());
		//	Set from client
		MClientInfo clientInfo = MClientInfo.get(getCtx());
		//	Weight
		if(clientInfo.getC_UOM_Weight_ID() <= 0) {
			throw new AdempiereException("@C_UOM_Weight_ID@ @NotFound@ @SeeClientInfoConfig@");
		}
		//	Volume
		if(clientInfo.getC_UOM_Volume_ID() <= 0) {
			throw new AdempiereException("@C_UOM_Volume_ID@ @NotFound@ @SeeClientInfoConfig@");
		}
		//	Set values
		line.setWeight_UOM_ID(clientInfo.getC_UOM_Weight_ID());
		line.setVolume_UOM_ID(clientInfo.getC_UOM_Volume_ID());
		if(getFreightId() != 0) {
			line.setM_Freight_ID(getFreightId());
		}
		line.setFreightAmt(getFreightAmt());
		line.saveEx();
		//	Complete
		freightOrder.processIt(getDocAction());
		freightOrder.saveEx();
		//	Add to log
		addLog(freightOrder.getDD_Freight_ID(), freightOrder.getDateDoc(), null, "@Created@");
		openResult(I_DD_Freight.Table_Name);
		//	
		return "@DD_Freight_ID@ @Created@: " + freightOrder.getDocumentInfo();
	}
}