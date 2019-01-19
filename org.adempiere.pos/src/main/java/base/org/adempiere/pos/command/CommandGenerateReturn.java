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

import org.adempiere.pos.process.CreateOrderBasedOnAnother;
import org.compiere.model.MDocType;
import org.compiere.process.DocAction;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
import org.eevolution.service.dsl.ProcessBuilder;


/**
 * Execute  Generate Return Command
 * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 23/01/16.
 */
public class CommandGenerateReturn extends CommandAbstract implements Command {

    public CommandGenerateReturn(String command, String event) {
        super.command = command;
        super.event = event;
    }

    @Override
    public void execute(CommandReceiver commandReceiver) {
        Trx.run(new TrxRunnable() {
            public void run(String trxName) {
                //Create partial return
                commandReceiver.setProcessInfo(ProcessBuilder
                        .create(commandReceiver.getCtx())
                        .process(commandReceiver.getProcessId())
                        .withTitle(commandReceiver.getName())
                        .withParameter(CreateOrderBasedOnAnother.C_OrderSource_ID, commandReceiver.getOrderId())
                        .withParameter(CreateOrderBasedOnAnother.Bill_BPartner_ID, commandReceiver.getPartnerId())
                        .withParameter(CreateOrderBasedOnAnother.DocSubTypeSO , MDocType.DOCSUBTYPESO_ReturnMaterial)
                        .withParameter(CreateOrderBasedOnAnother.DocAction, DocAction.ACTION_None)
                        .withParameter(CreateOrderBasedOnAnother.IsIncludePayments, false)
                        .withParameter(CreateOrderBasedOnAnother.IsAllocated, false)
                        .withoutTransactionClose()
                        .execute(trxName)
                );
            }
        });
     }
}
