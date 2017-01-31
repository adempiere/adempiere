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

import org.compiere.model.MInvoice;
import org.eevolution.model.MWMInOutBound;
import org.eevolution.model.MWMInOutBoundLine;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Generated Process for (Invoice Selection)
 *
 * @author ADempiere (generated)
 * @author eEvolution author Victor Perez <victor.perez@e-evolution.com>
 * @version Release 3.8.2
 */
public class ProcessingInvoiceSelection extends ProcessingInvoiceSelectionAbstract {

    @Override
    protected void prepare() {
        super.prepare();
    }

    @Override
    protected String doIt() throws Exception {
        List<MInvoice> invoices = (List<MInvoice>) getInstancesForSelection(get_TrxName());
        if (getRecord_ID() > 0 && getSelectionKeys().size() > 0 && getTableSelectionId() == MWMInOutBound.Table_ID) {
            createInboundLineFrom(invoices);
        } else if (getRecord_ID() == 0 && getSelectionKeys().size() > 0) {
            UpdatingInvoiceProperties(invoices);
        }
        return "";
    }

    /**
     * Updating Invoice Properties
     * @param invoices
     */
    private void UpdatingInvoiceProperties(List<MInvoice> invoices) {
        invoices.stream().filter(invoice -> invoice != null)
                .forEach(invoice -> {
                    Optional.ofNullable(getSelectionAsInt(invoice.get_ID(), getPrefixAliasForTableSelection() + MInvoice.COLUMNNAME_C_DunningLevel_ID))
                            .ifPresent(dunningLevelId -> invoice.setC_DunningLevel_ID(dunningLevelId));
                    Optional.ofNullable(getSelectionAsTimestamp(invoice.get_ID(), getPrefixAliasForTableSelection() + MInvoice.COLUMNNAME_DunningGrace))
                            .ifPresent(dunningGrace -> invoice.setDunningGrace(dunningGrace));
                    if (!invoice.isSOTrx())
                        Optional.ofNullable(getSelectionAsBoolean(invoice.get_ID(), getPrefixAliasForTableSelection() + MInvoice.COLUMNNAME_IsInDispute))
                                .ifPresent(inDispute -> invoice.setIsInDispute(inDispute));
                    if (invoice.is_Changed())
                        invoice.saveEx();
                });


    }

    /**
     * Create inboud lines from invoice
     * @param invoices
     */
    private void createInboundLineFrom(List<MInvoice> invoices) {
        MWMInOutBound inOutBound = (MWMInOutBound) getInstance(get_TrxName());
        AtomicInteger lineNo = new AtomicInteger(10);
        invoices.stream()
                .filter(invoice -> invoice != null)
                .flatMap(invoice -> Arrays.stream(invoice.getLines(true)))
                .forEach(invoiceLine -> {
                    MWMInOutBoundLine outBoundLine = MWMInOutBoundLine.getByInvoiceLine(invoiceLine);
                    if (outBoundLine == null) {
                        outBoundLine = new MWMInOutBoundLine(inOutBound, invoiceLine);
                        outBoundLine.setLine(lineNo.get());
                        outBoundLine.saveEx();
                        lineNo.updateAndGet(line -> line + 10);
                    }
                });

    }
}