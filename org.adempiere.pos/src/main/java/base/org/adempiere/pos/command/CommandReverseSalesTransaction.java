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

import org.adempiere.pos.process.ReverseTheSalesTransaction;
import org.compiere.process.ProcessInfo;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
import org.eevolution.service.dsl.ProcessBuilder;


/**
 * Execute reversal sales transaction command
 * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 23/01/16.
 */
public class CommandReverseSalesTransaction extends CommandAbstract implements Command {

      public CommandReverseSalesTransaction(String command, String actionCommand) {
        super.command = command;
        super.event = actionCommand;
    }

    @Override
    public void execute(CommandReceiver commandReceiver) {
        Trx.run(new TrxRunnable() {
            public void run(String trxName) {
                //Reverse The Sales Transaction for Source Order
                ProcessInfo processInfo = new ProcessInfo(commandReceiver.getEvent(), commandReceiver.getProcessId());
                processInfo = ProcessBuilder
                        .create(commandReceiver.getCtx())
                        .process(processInfo.getAD_Process_ID())
                        .withTitle(processInfo.getTitle())
                        .withParameter(ReverseTheSalesTransaction.C_Order_ID, commandReceiver.getOrderId())
                        .withParameter(ReverseTheSalesTransaction.Bill_BPartner_ID, commandReceiver.getPartnerId())
                        .withParameter(ReverseTheSalesTransaction.IsCancelled, true)
                        .withParameter(ReverseTheSalesTransaction.IsShipConfirm, true)
                        .withoutTransactionClose()
                        .execute(trxName);
                commandReceiver.setProcessInfo(processInfo);
            }
        });
    }
}
