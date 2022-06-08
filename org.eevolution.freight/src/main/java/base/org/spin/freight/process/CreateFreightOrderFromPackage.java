/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.									  *
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

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MClientInfo;
import org.compiere.model.MDocType;
import org.compiere.model.MFreight;
import org.compiere.model.MFreightCategory;
import org.compiere.model.MPackage;
import org.compiere.util.Env;
import org.eevolution.model.I_DD_Freight;
import org.eevolution.model.MDDFreight;
import org.eevolution.model.MDDFreightLine;

/** 
 * 	Generated Process for (Create Freight Order From Package)
 *  @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 *  @version Release 3.9.3
 */
public class CreateFreightOrderFromPackage extends CreateFreightOrderFromPackageAbstract {
	@Override
	protected void prepare() {
		super.prepare();
		if(getFreightAmt() == null) {
			setFreightAmt(Env.ZERO);
		}
	}
	
	@Override
	protected String doIt() throws Exception {
		MDDFreight freightOrder = new MDDFreight(getCtx(), 0, get_TrxName());
		if(getDriverId() > 0) {
			freightOrder.setDD_Driver_ID(getDriverId());
		}
		if(getVehicleId() > 0) {
			freightOrder.setDD_Vehicle_ID(getVehicleId());
		}
		freightOrder.setDateDoc(getDateDoc());
		freightOrder.setM_Shipper_ID(getShipperId());
		if(getDateOrdered() == null) {
			freightOrder.setDateOrdered(getDateDoc());
		} else {
			freightOrder.setDateOrdered(getDateOrdered());
		}
		if(getDocTypeId() > 0) {
			freightOrder.setC_DocType_ID(getDocTypeId());
		} else {
			freightOrder.setC_DocType_ID(MDocType.getDocType(MDocType.DOCBASETYPE_FreightOrder, Env.getAD_Org_ID(getCtx())));
		}
		//	
		freightOrder.setDocAction(getDocAction());
		if(isOverwriteFreightCostRule()) {
			freightOrder.setFreightCostRule(getFreightCostRule());
		}
		freightOrder.setFreightAmt(getFreightAmt());
		freightOrder.saveEx();
		//	Created
		AtomicInteger lineNo = new AtomicInteger();
		AtomicInteger lines = new AtomicInteger();
		AtomicReference<BigDecimal> freightAmount = new AtomicReference<BigDecimal>(Env.ZERO);
		//	Add lines from Packages
		getSelectionKeys().forEach(key -> {
			MPackage selectedPackage = new MPackage(getCtx(), key, get_TrxName());
			MDDFreightLine freightLine = new MDDFreightLine(getCtx(), 0, get_TrxName());
			freightLine.setDD_Freight_ID(freightOrder.getDD_Freight_ID());
			freightLine.setLine(lineNo.addAndGet(10));
			freightLine.setWeight(selectedPackage.getWeight());
			freightLine.setVolume(selectedPackage.getVolume());
			freightLine.setFreightAmt(selectedPackage.getFreightAmt());
			freightLine.setFreightRate(selectedPackage.getFreightRate());
			if(selectedPackage.getM_Freight_ID() > 0) {
				freightLine.setM_Freight_ID(selectedPackage.getM_Freight_ID());
			}
			//	
			if(isOverwriteFreightCostRule()) {
				freightLine.setM_FreightCategory_ID(getFreightCategoryId());
				MFreight freight = new MFreight(getCtx(), getFreightCategoryId(), get_TrxName());
				freightLine.setM_FreightCategory_ID(freight.getM_FreightCategory_ID());
			} else {
				freightLine.setM_FreightCategory_ID(selectedPackage.getM_FreightCategory_ID());
			}
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
			freightLine.setWeight_UOM_ID(clientInfo.getC_UOM_Weight_ID());
			freightLine.setVolume_UOM_ID(clientInfo.getC_UOM_Volume_ID());
			freightLine.setM_Package_ID(selectedPackage.getM_Package_ID());
			if(selectedPackage.getC_BPartner_ID() > 0) {
				freightLine.setC_BPartner_ID(selectedPackage.getC_BPartner_ID());
			}
			freightLine.setIsInvoiced(selectedPackage.isInvoiced());
			if(selectedPackage.getM_FreightCategory_ID() <= 0) {
				throw new AdempiereException("@M_FreightCategory_ID@ @NotFound@");
			}
			MFreightCategory freightCategory = MFreightCategory.getById(getCtx(), selectedPackage.getM_FreightCategory_ID(), get_TrxName());
			if(selectedPackage.isInvoiced()) {
				if(freightCategory.getM_Product_ID() <= 0
						&& freightCategory.getC_Charge_ID() <= 0) {
					throw new AdempiereException(freightCategory.getName() + ": @IsInvoiced@ @M_Product_ID@ / @C_Charge_ID@ @NotFound@");
				}
			}
			//	Set Product and Charge
			if(freightCategory.getM_Product_ID() > 0) {
				freightLine.setM_Product_ID(freightCategory.getM_Product_ID());
			} else {
				freightLine.setC_Charge_ID(freightCategory.getC_Charge_ID());
			}
			freightLine.saveEx();
			lines.addAndGet(1);
			freightAmount.getAndUpdate(amount -> amount.add(selectedPackage.getFreightAmt()));
			//	Add to log
			addLog(freightOrder.getDD_Freight_ID(), freightOrder.getDateDoc(), null, "@Created@");
		});
		if(!isOverwriteFreightCostRule()) {
			freightOrder.setFreightAmt(freightAmount.get());
		}
		//	Complete
		freightOrder.processIt(getDocAction());
		freightOrder.saveEx();
		openResult(I_DD_Freight.Table_Name);
		//	Return
		return "@DD_Freight_ID@ @Created@: " + freightOrder.getDocumentInfo() + " @Lines@: " + lines.get();
	}
}