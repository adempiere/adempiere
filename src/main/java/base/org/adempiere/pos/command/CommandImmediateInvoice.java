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

package org.adempiere.pos.command;

import org.adempiere.pos.process.GenerateImmediateInvoice;
import org.compiere.model.MDocType;
import org.compiere.process.ProcessInfo;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
import org.eevolution.service.dsl.ProcessBuilder;


/**
 * Execute immediate invoice command
 * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 23/01/16.
 */
public class CommandImmediateInvoice extends CommandAbstract implements Command {

    public CommandImmediateInvoice(String command,String event) {

        super.command = command;
        super.event = event;
    }

    @Override
    public void execute(CommandReceiver commandReceiver) throws Exception {
        Trx.run(new TrxRunnable() {
            public void run(String trxName) {
                ProcessInfo processInfo = ProcessBuilder.
                        create(commandReceiver.getCtx()).process(commandReceiver.getProcessId())
                        .withTitle(commandReceiver.getEvent())
                        .withParameter(GenerateImmediateInvoice.C_Order_ID,commandReceiver.getOrderId())
                        .withParameter(GenerateImmediateInvoice.DocSubTypeSO, MDocType.DOCSUBTYPESO_OnCreditOrder)
                        .withParameter(GenerateImmediateInvoice.IsIncludePayments, true)
                        .withParameter(GenerateImmediateInvoice.IsAllocated, true)
                        .withParameter(GenerateImmediateInvoice.IsShipConfirm, true)
                        .withParameter(GenerateImmediateInvoice.Bill_BPartner_ID, commandReceiver.getPartnerId())
                        .withoutTransactionClose()
                        .execute(trxName);
                commandReceiver.setProcessInfo(processInfo);
            }
        });
    }
}
