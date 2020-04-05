/**
 * Copyright (C) 2003-2020, e-Evolution Consultants S.A. , http://www.e-evolution.com
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
 * Created by e-Evolution
 */

package org.eevolution.process;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAllocationHdr;
import org.compiere.model.MAllocationLine;
import org.compiere.model.MDocType;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MPayment;
import org.compiere.model.MPriceList;
import org.compiere.util.Env;
import org.compiere.util.Msg;

import java.util.Arrays;
import java.util.Optional;

/**
 * Generated Process for (Generate Invoice from Payment)
 *
 * @author eEvolution author Victor Perez <victor.perez@e-evolution.com>
 * @version Release 3.9.3
 */
public class GenerateInvoiceFromPayment extends GenerateInvoiceFromPaymentAbstract {
    @Override
    protected void prepare() {
        super.prepare();
    }

    @Override
    protected String doIt() throws Exception {
        if (getRecord_ID() <= 0)
            throw new AdempiereException("@Record_ID@ @NotFound@");
        MPayment payment = new MPayment(getCtx(), getRecord_ID(), get_TrxName());
        if (payment.isAllocated())
            throw new AdempiereException("@C_Payment_ID@ @IsAllocated@");

        if (payment.getC_Charge_ID() > 0)
            throw new AdempiereException("@C_Payment_ID@ @AlreadyExists@ @C_Charge_ID@");

        if (getChargeId() <= 0)
            throw new AdempiereException("@C_Charge_ID@ @NotFound@");

        generateInvoice(payment);

        return "@Ok@";
    }

    private void generateInvoice(MPayment payment) {
        //Create Invoice
        MInvoice invoice = new MInvoice(getCtx(), 0, get_TrxName());
        invoice.setAD_Org_ID(payment.getAD_Org_ID());
        invoice.setDateInvoiced(getDateInvoiced());
        invoice.setDateAcct(getDateAcct());
        invoice.setC_BPartner_ID(payment.getC_BPartner_ID());
        invoice.setC_BPartner_Location_ID(getBPartnerLocationId());
        invoice.setAD_User_ID(getUserId());
        invoice.setDescription(getDescription());
        invoice.setDocStatus(MInvoice.STATUS_Drafted);
        invoice.setDocAction(MInvoice.DOCACTION_Complete);
        Optional<MPriceList> maybePriceList = Optional.ofNullable(MPriceList.getDefault(getCtx(), payment.isReversal()));
        maybePriceList.ifPresent(priceList -> invoice.setM_PriceList_ID(priceList.get_ID()));
        invoice.setC_Currency_ID(payment.getC_Currency_ID());
        invoice.setC_ConversionType_ID(payment.getC_ConversionType_ID());
        if (getDocTypeTargetId() > 0)
            invoice.setC_DocTypeTarget_ID(getDocTypeTargetId());
        else {
            String documentBaseType = payment.isReceipt() ? MDocType.DOCBASETYPE_ARInvoice : MDocType.DOCBASETYPE_APInvoice;
            Optional<MDocType> maybeDocumentType = Arrays.stream(MDocType.getOfDocBaseType(getCtx(), documentBaseType)).findFirst();
            maybeDocumentType.ifPresent(documentType -> invoice.setC_DocTypeTarget_ID());
        }
        invoice.setAD_OrgTrx_ID(payment.getAD_OrgTrx_ID());
        invoice.setC_Campaign_ID(payment.getC_Campaign_ID());
        invoice.setC_Activity_ID(payment.getC_Activity_ID());
        invoice.setC_Project_ID(payment.getC_Project_ID());
        invoice.setC_Payment_ID(payment.get_ID());
        invoice.saveEx();

        addLog(Msg.translate(getCtx(), "@C_Invoice@_ID : " + invoice.getDocumentInfo()));

        //Create Invoice Line
        MInvoiceLine invoiceLine = new MInvoiceLine(invoice);
        invoiceLine.setC_Charge_ID(getChargeId());
        invoiceLine.setQty(Env.ONE);
        invoiceLine.setPrice(payment.getPayAmt());
        invoiceLine.setAD_OrgTrx_ID(payment.getAD_OrgTrx_ID());
        invoiceLine.setC_Campaign_ID(payment.getC_Campaign_ID());
        invoiceLine.setC_Activity_ID(payment.getC_Activity_ID());
        invoiceLine.setC_Project_ID(payment.getC_Project_ID());
        Optional.ofNullable(getDescription()).ifPresent(invoiceLine::setDescription);

        invoice.processIt(MInvoice.DOCACTION_Complete);
        invoice.saveEx();

        MAllocationHdr allocationHdr = new MAllocationHdr(getCtx(), 0, get_TrxName());
        allocationHdr.setDateTrx(payment.getDateTrx());
        allocationHdr.setDateAcct(payment.getDateAcct());
        allocationHdr.setDocStatus(MAllocationHdr.DOCSTATUS_Drafted);
        allocationHdr.setDocAction(MAllocationHdr.DOCACTION_Complete);
        allocationHdr.saveEx();

        addLog(Msg.translate(getCtx(), "@C_AllocationHdr@ : " + allocationHdr.getDocumentInfo()));

        MAllocationLine allocationLine = new MAllocationLine(allocationHdr);
        allocationLine.setC_Payment_ID(payment.get_ID());
        allocationLine.setC_Invoice_ID(invoice.getC_Invoice_ID());
        allocationLine.saveEx();

        allocationHdr.processIt(MAllocationHdr.DOCACTION_Complete);
        allocationHdr.saveEx();
    }
}