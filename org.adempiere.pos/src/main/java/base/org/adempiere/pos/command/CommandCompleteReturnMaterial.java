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

import org.adempiere.pos.AdempierePOSException;
import org.compiere.model.MDocType;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutConfirm;
import org.compiere.model.MInOutLineConfirm;
import org.compiere.model.MInvoice;
import org.compiere.model.MOrder;
import org.compiere.model.MPayment;
import org.compiere.model.Query;
import org.compiere.print.ReportCtl;
import org.compiere.print.ReportEngine;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfo;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
import org.eevolution.service.dsl.ProcessBuilder;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * execute Complete a return material command
 * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 23/01/16.
 */
public class CommandCompleteReturnMaterial extends CommandAbstract implements Command {
    public CommandCompleteReturnMaterial(String command,String event) {

        super.command = command;
        super.event = event;
    }

    @Override
    public void execute(CommandReceiver commandReceiver) {
        Trx.run( new TrxRunnable() {
            public void run(String trxName) {
                ProcessInfo processInfo = new ProcessInfo(commandReceiver.getEvent(), commandReceiver.getProcessId());
                MOrder returnOrder = new MOrder(commandReceiver.getCtx(), commandReceiver.getOrderId(), trxName);
                returnOrder.setInvoiceRule(MOrder.INVOICERULE_Immediate);
                returnOrder.setDeliveryRule(MOrder.DELIVERYRULE_Force);
                returnOrder.saveEx();
                List<Integer> selectionIds = new ArrayList<Integer>();
                selectionIds.add(returnOrder.get_ID());
                //Generate Return using InOutGenerate
                processInfo = ProcessBuilder
                        .create(commandReceiver.getCtx())
                        .process(199)
                        .withTitle(processInfo.getTitle())
                        .withParameter(MOrder.COLUMNNAME_M_Warehouse_ID, commandReceiver.getWarehouseId())
                        .withParameter("Selection", true)
                        .withSelectedRecordsIds(selectionIds)
                        .withoutTransactionClose()
                        .execute(trxName);

                if (processInfo.isError()) {
                    throw new AdempierePOSException(processInfo.getLogInfo());
                }
                //Force the confirmation
                for (MInOut customerReturn : returnOrder.getShipments()) {
                    customerReturn.processIt(DocAction.ACTION_Complete);
                    customerReturn.saveEx();

                    for (MInOutConfirm confirm : customerReturn.getConfirmations(true)) {
                        for (MInOutLineConfirm confirmLine : confirm.getLines(true)) {
                            confirmLine.setConfirmedQty(confirmLine.getTargetQty());
                            confirmLine.saveEx();
                        }
                        confirm.processIt(DocAction.ACTION_Complete);
                        confirm.saveEx();
                    }
                }

                MOrder sourceOrder = (MOrder) returnOrder.getRef_Order();
                if (sourceOrder != null && returnOrder.getC_Order_ID() > 0)
                {
                   if (sourceOrder.getInvoices().length > 0) {
                       //Generate Credit note InvoiceGenerate
                       processInfo = ProcessBuilder
                               .create(commandReceiver.getCtx())
                               .process(134)
                               .withTitle(processInfo.getTitle())
                               .withParameter("Selection", true)
                               .withSelectedRecordsIds(selectionIds)
                               .withParameter(MInvoice.COLUMNNAME_DocAction, MInvoice.DOCACTION_Complete)
                               .withoutTransactionClose()
                               .execute(trxName);

                       for (MInvoice invoice :  returnOrder.getInvoices()) {
                           ReportCtl.startDocumentPrint(
                                   ReportEngine.INVOICE,
                                   null  /* custom Print Format */,
                                   invoice.get_ID(),
                                   null /* Parent */,
                                   processInfo.getWindowNo(),
                                   false /* Is print direct */,
                                   null /* Printer Name */,
                                   processInfo);
                       }
                       commandReceiver.setProcessInfo(processInfo);
                   }
                   else // if not exist invoice then return of payment
                   {
                       Timestamp today = new Timestamp(System.currentTimeMillis());
                       // Create return payment
                       MPayment payment = new MPayment(returnOrder.getCtx() ,  0 , returnOrder.get_TrxName());
                       payment.setDateTrx(today);
                       payment.setC_Order_ID(returnOrder.getC_Order_ID());
                       payment.setC_BankAccount_ID(commandReceiver.getBankAccountId());
                       payment.setDateAcct(today);
                       payment.addDescription(Msg.parseTranslation(returnOrder.getCtx(), " @C_Order_ID@ " + returnOrder.getDocumentNo()));
                       payment.setIsReceipt(false);
                       payment.setC_DocType_ID(MDocType.getDocType(MDocType.DOCBASETYPE_APPayment));
                       payment.setAmount(returnOrder.getC_Currency_ID() , returnOrder.getGrandTotal());
                       payment.setDocAction(DocAction.ACTION_Complete);
                       payment.setDocStatus(DocAction.STATUS_Drafted);
                       payment.setIsPrepayment(true);
                       payment.saveEx();

                       payment.processIt(DocAction.ACTION_Complete);
                       payment.saveEx();

                       returnOrder.setC_POS_ID(commandReceiver.getPOSId());
                       returnOrder.saveEx();

                       processInfo.addLog(0,null,null,payment.getDocumentInfo());

                       ReportCtl.startDocumentPrint(
                               ReportEngine.ORDER,
                               null  /* custom Print Format */,
                               returnOrder.get_ID(),
                               null /* Parent */,
                               processInfo.getWindowNo(),
                               false /* Is print direct */,
                               null /* Printer Name */,
                               processInfo);

                   }
                }
                commandReceiver.setProcessInfo(processInfo);
            }
        });
    }

}
