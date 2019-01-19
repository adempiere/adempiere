/** ****************************************************************************
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
 * Copyright (C) 2003-2016 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 * ****************************************************************************/

package org.adempiere.pos.process;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MOrder;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfo;
import org.compiere.util.ASyncProcess;
import org.eevolution.service.dsl.ProcessBuilder;

/**
 * This process call of create order based on another and reverse the sales transactions process
 * to implement the business case where is necessary to create a sales ticket and invoice next day on behalf of other business partner
 * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 28/12/15.
 */
public class GenerateImmediateInvoice extends GenerateImmediateInvoiceAbstract implements ASyncProcess {

    private MOrder order;
    @Override
    protected void prepare() {
        super.prepare();
        order = new MOrder(getCtx() , getOrderId() , get_TrxName());
    }

    @Override
    protected String doIt() throws Exception {

        //Create new Order for current date and Bill Partner
        ProcessInfo processInfo = ProcessBuilder
                .create(getCtx())
                .process(CreateOrderBasedOnAnother.getProcessId())
                .withTitle(CreateOrderBasedOnAnother.getProcessName())
                .withParentProcess(this)
                .withParameter(CreateOrderBasedOnAnother.C_OrderSource_ID, order.get_ID())
                .withParameter(CreateOrderBasedOnAnother.Bill_BPartner_ID , getInvoicePartnerId())
                .withParameter(CreateOrderBasedOnAnother.DocSubTypeSO , getSOSubType())
                .withParameter(CreateOrderBasedOnAnother.DocAction, DocAction.ACTION_Complete)
                .withParameter(CreateOrderBasedOnAnother.IsIncludePayments, isIncludePayments())
                .withParameter(CreateOrderBasedOnAnother.IsAllocated, isAllocated())
                .withoutTransactionClose()
                .execute(get_TrxName());

        if (processInfo.isError())
            throw new AdempiereException(processInfo.getTitle()  +  " @ProcessRunError@ " + processInfo.getSummary());

        addLog(processInfo.getLogInfo());

        int newOrderId = processInfo.getRecord_ID();
        //Reverse The Sales Transaction for Source Order
        processInfo = ProcessBuilder
                .create(getCtx())
                .process(ReverseTheSalesTransaction.getProcessId())
                .withTitle(ReverseTheSalesTransaction.getProcessName())
                .withParentProcess(this)
                .withParameter(ReverseTheSalesTransaction.C_Order_ID , order.get_ID())
                .withParameter(ReverseTheSalesTransaction.Bill_BPartner_ID , getInvoicePartnerId())
                .withParameter(ReverseTheSalesTransaction.IsShipConfirm, isipReceiptConfirmation())
                .withoutTransactionClose()
                .execute(get_TrxName());

        if (processInfo.isError())
            throw new AdempiereException(processInfo.getTitle()  +  " @ProcessRunError@ " + processInfo.getSummary());

        addLog(processInfo.getLogInfo());
        getProcessInfo().setRecord_ID(newOrderId);
        return "@Ok@";
    }

    @Override
    public void lockUI(ProcessInfo pi) {

    }

    @Override
    public void unlockUI(ProcessInfo pi) {

    }

    @Override
    public boolean isUILocked() {
        return false;
    }

    @Override
    public void executeASync(ProcessInfo pi) {

    }
}
