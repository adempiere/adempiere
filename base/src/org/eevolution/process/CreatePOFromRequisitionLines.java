/**
 * Copyright (C) 2003-2018, e-Evolution Consultants S.A. , http://www.e-evolution.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 * Created by victor.perez@e-evolution.com , www.e-evolution.com
 */

package org.eevolution.process;

import org.adempiere.core.domains.models.I_C_OrderLine;
import org.compiere.model.MOrder;
import org.compiere.model.MRequisition;
import org.compiere.model.MRequisitionLine;
import org.compiere.process.ProcessInfo;
import org.compiere.process.RequisitionPOCreate;
import org.eevolution.services.dsl.ProcessBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Generated Process for (Create PO from Requisition Lines)
 *
 * @author victor.perez@e-evolution.com , e-Evolution ,SC
 */
public class CreatePOFromRequisitionLines extends CreatePOFromRequisitionLinesAbstract {
    List<Integer> purchaseOrderIds = new ArrayList<Integer>();

    @Override
    protected void prepare() {
        super.prepare();
    }

    @Override
    protected String doIt() throws Exception {
        getSelectionKeys().stream().forEach(requisitionLineId -> {
            MRequisitionLine requisitionLine = new MRequisitionLine(getCtx(), requisitionLineId, get_TrxName());
            if (getPriceListId() > 0) {
                MRequisition requisition = (MRequisition) requisitionLine.getM_Requisition();
                requisition.setM_PriceList_ID(getPriceListId());
                requisition.saveEx();
            }

            if (getBPartnerId() > 0) {
                requisitionLine.setC_BPartner_ID(getBPartnerId());
                requisitionLine.saveEx();
            }
        });

        ProcessInfo createPORequisition = ProcessBuilder.create(getCtx())
                .process(RequisitionPOCreate.getProcessId()).withTitle(RequisitionPOCreate.getProcessName())
                .withSelectedRecordsIds(MRequisitionLine.Table_ID, getSelectionKeys())
                .withParameter(RequisitionPOCreate.CONSOLIDATEDOCUMENT, isConsolidateDocument())
                .withoutTransactionClose()
                .execute(get_TrxName());

        addLog(createPORequisition.getLogInfo());
        if (createPORequisition.isError())
            return "@Error@ " + createPORequisition.getLogInfo();

        getSelectionKeys().stream().forEach(requisitionLineId -> {
            MRequisitionLine requisitionLine = new MRequisitionLine(getCtx(), requisitionLineId, get_TrxName());
            if (requisitionLine.getC_OrderLine_ID() > 0) {
                I_C_OrderLine orderLine = requisitionLine.getC_OrderLine();
                if (!purchaseOrderIds.contains(orderLine.getC_Order_ID()))
                    purchaseOrderIds.add(orderLine.getC_Order_ID());
            }
        });


        purchaseOrderIds.stream().forEach(purchaseOrderId -> {
            MOrder purchaseOrder = new MOrder(getCtx(), purchaseOrderId, get_TrxName());
            Optional.ofNullable(getDateOrdered()).ifPresent(ordered -> purchaseOrder.setDateOrdered(ordered));
            Optional.ofNullable(getDatePromised()).ifPresent(promised -> purchaseOrder.setDatePromised(promised));
            Optional.ofNullable(getPriorityRule()).ifPresent(priority -> purchaseOrder.setPriorityRule(priority));
            Optional.ofNullable(getPOReference()).ifPresent(POReference -> purchaseOrder.setPOReference(POReference));
            //	Add document Type
            if(getDocTypeId() > 0) {
            	purchaseOrder.setC_DocTypeTarget_ID(getDocTypeId());
            }
            if (getBPartnerLocationId() > 0)
                purchaseOrder.setC_BPartner_Location_ID(getBPartnerLocationId());
            if (getPaymentTermId() > 0)
                purchaseOrder.setC_PaymentTerm_ID(getPaymentTermId());
            if (getSalesRepId() > 0)
                purchaseOrder.setSalesRep_ID(getSalesRepId());

            purchaseOrder.saveEx();
            if (MOrder.DOCACTION_Prepare.equals(getDocAction())
                    || MOrder.DOCACTION_Complete.equals(getDocAction())
                    || MOrder.DOCACTION_Approve.equals(getDocAction())
            ) {
                purchaseOrder.processIt(getDocAction());
                purchaseOrder.saveEx();
            }
        });

        return "@Ok@ " + getProcessInfo().getLogInfo();
    }
}