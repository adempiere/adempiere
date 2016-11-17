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
package org.adempiere.pos;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Optional;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.MBrowse;
import org.adempiere.pos.command.Command;
import org.adempiere.pos.command.CommandManager;
import org.adempiere.pos.command.CommandReceiver;
import org.adempiere.pos.search.POSQuery;
import org.adempiere.pos.search.QueryBPartner;
import org.adempiere.pos.service.POSQueryInterface;
import org.adempiere.pos.service.POSQueryListener;
import org.compiere.apps.ADialog;
import org.compiere.apps.AEnv;
import org.compiere.apps.Waiting;
import org.compiere.apps.form.FormFrame;
import org.compiere.model.MBPartner;
import org.compiere.process.ProcessInfo;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.eevolution.form.VBrowser;

/**
 * Class that execute business logic from POS
 * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 29/12/15.
 * @contributor Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/670">
 * 		@see FR [ 670 ] Standard process for return material on POS</a>
 */
public class POSActionMenu implements  ActionListener , POSQueryListener{

    private VPOS pos;
    private POSQuery queryPartner;
    private JPopupMenu popupMenu;
    private CommandManager commandManager;
    private Command currentCommand;

    public POSActionMenu(VPOS pos)
    {
        this.pos = pos;
        this.popupMenu =  new JPopupMenu();
        commandManager =  new CommandManager();
        for (Map.Entry<String, CommandReceiver> receivers: commandManager.getCommandReceivers().entrySet()) {
            CommandReceiver commandReceiver = receivers.getValue();
            addMenuItem(commandReceiver.getEvent());
        }

    }

    private void addMenuItem(String optionName)
    {
        JMenuItem menuItem =  new JMenuItem(optionName);
        popupMenu.add(menuItem).addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
        popupMenu.setVisible(false);
        currentCommand = commandManager.getCommand(actionEvent.getActionCommand());
        beforeExecutionCommand(currentCommand);
        } catch (AdempiereException exception) {
            ADialog.error(pos.getWindowNo(), pos.getFrame() , exception.getLocalizedMessage());
        }
    }

    private void beforeExecutionCommand(Command command) {
        if (command.getCommand() == CommandManager.GENERATE_IMMEDIATE_INVOICE) {
            if (pos.isCompleted()) {
                queryPartner = new QueryBPartner(pos);
                queryPartner.setModal(true);
                queryPartner.addOptionListener(this);
                queryPartner.showView();
            }
        } else if (command.getCommand() == CommandManager.GENERATE_REVERSE_SALES) {
            if (pos.isCompleted())
                executeCommand(command);
        } else if (command.getCommand() == CommandManager.GENERATE_RETURN) {
        	if(pos.getC_Order_ID() > 0 
        			&& !pos.isReturnMaterial() 
        			&& pos.isCompleted()) {
        		executeCommand(command);
        	}
        } else if (command.getCommand() == CommandManager.GENERATE_WITHDRAWAL) {
            executeCommand(command);
        } else if (command.getCommand() == CommandManager.CLOSE_STATEMENT) {
            executeCommand(command);
        } else {
        	ADialog.info(pos.getWindowNo(), popupMenu, "DocProcessed", pos.getDocumentNo());
        }
    }

    private void afterExecutionCommand(Command command)
    {
    }

    private void executeCommand(Command command)
    {
        Waiting waiting = new Waiting(pos.getFrame(), Msg.parseTranslation(pos.getCtx(), "@Processing@"), false, 120);
        try {
            CommandReceiver receiver = commandManager.getCommandReceivers(command.getEvent());
            if (command.getCommand() == CommandManager.GENERATE_IMMEDIATE_INVOICE
                    && pos.getC_Order_ID() > 0
                    && pos.isCompleted()
                    && !pos.isVoided()) {
                receiver.setCtx(pos.getCtx());
                receiver.setPartnerId(queryPartner.getRecord_ID());
                receiver.setOrderId(pos.getC_Order_ID());
                receiver.setPOSId(pos.getC_POS_ID());
                receiver.setBankAccountId(pos.getC_BankAccount_ID());
                MBPartner partner = MBPartner.get(pos.getCtx(), receiver.getPartnerId());
                Optional<String> taxId = Optional.ofNullable(partner.getTaxID());
                String processMessage = receiver.getName()
                        + " @DisplayDocumentInfo@ : " + pos.getDocumentNo()
                        + " @To@ @C_BPartner_ID@ : " + partner.getName()
                        + " @TaxID@ : " + taxId.orElse("");
                if (ADialog.ask(pos.getWindowNo(), popupMenu, "StartProcess?", Msg.parseTranslation(pos.getCtx(), processMessage))) {
                    AEnv.showCenterScreen(waiting);
                    command.execute(receiver);
                    ProcessInfo processInfo = receiver.getProcessInfo();
                    waiting.setVisible(false);
                    if (processInfo != null && processInfo.isError()) {
                        showError(processInfo);
                    } else {
                        afterExecutionCommand(command);
                        showOkMessage(processInfo);
                        if(processInfo != null)
                        	pos.setOrder(processInfo.getRecord_ID());
                        pos.refreshHeader();
                        //	Print Ticket
                        pos.printTicket();
                    }
                }
            }
            //Reverse The Sales Transaction
            else if (command.getCommand() == CommandManager.GENERATE_REVERSE_SALES
                    && pos.getC_Order_ID() > 0
                    && !pos.isReturnMaterial()
                    && !pos.isVoided()
                    && !pos.isClosed()) {
                receiver.setCtx(pos.getCtx());
                receiver.setOrderId(pos.getC_Order_ID());
                receiver.setPOSId(pos.getC_POS_ID());
                receiver.setPartnerId(pos.getC_BPartner_ID());
                receiver.setBankAccountId(pos.getC_BankAccount_ID());
                String processMessage = receiver.getName()
                        + " @order.no@ : " + pos.getDocumentNo()
                        + " @To@ @C_BPartner_ID@ : " + pos.getBPName();

                if (ADialog.ask(pos.getWindowNo(), popupMenu, "StartProcess?", Msg.parseTranslation(pos.getCtx(), processMessage))) {
                    AEnv.showCenterScreen(waiting);
                    command.execute(receiver);
                    ProcessInfo processInfo = receiver.getProcessInfo();
                    waiting.setVisible(false);
                    if (processInfo != null && processInfo.isError()) {
                        showError(processInfo);
                    }
                    else
                    {
                        afterExecutionCommand(command);
                        showOkMessage(processInfo);
                    }
                    pos.printTicket();
                }
            }
            //Return product
            else if (command.getCommand() == CommandManager.GENERATE_RETURN 
            		&& pos.getC_Order_ID() > 0 
            		&& !pos.isReturnMaterial() 
            		&& pos.isCompleted()) {
                receiver.setCtx(pos.getCtx());
                receiver.setOrderId(pos.getC_Order_ID());
                receiver.setPOSId(pos.getC_POS_ID());
                receiver.setPartnerId(pos.getC_BPartner_ID());
                receiver.setBankAccountId(pos.getC_BankAccount_ID());
                String processMessage = receiver.getName()
                        + " @DisplayDocumentInfo@ : " + pos.getDocumentNo()
                        + " @To@ @C_BPartner_ID@ : " + pos.getBPName();

                if (ADialog.ask(pos.getWindowNo(), popupMenu, "StartProcess?", Msg.parseTranslation(pos.getCtx(), processMessage))) {
                    AEnv.showCenterScreen(waiting);
                    command.execute(receiver);
                    ProcessInfo processInfo = receiver.getProcessInfo();
                    waiting.setVisible(false);
                    if (processInfo != null && processInfo.isError()) {
                        showError(processInfo);
                    }
                    else
                    {
                        afterExecutionCommand(command);
                        showOkMessage(processInfo);
                        //execute out transaction
                        if (processInfo != null 
                        		&& processInfo.getRecord_ID() > 0) {
                            pos.setOrder(processInfo.getRecord_ID());
                            pos.refreshHeader();
                        }
                    }
                }
            } else if (command.getCommand() == CommandManager.GENERATE_WITHDRAWAL) {
                Env.setContext(pos.getCtx(), pos.getWindowNo() , "C_POS_ID" , pos.getC_POS_ID());
                Dimension size = new Dimension(1024, 768);
                FormFrame ff = new FormFrame(pos.getWindowNo());
                ff.setSize(size);
                MBrowse browse = new MBrowse(Env.getCtx(), 50056 , null);
                new VBrowser(ff, true , pos.getWindowNo(), "" , browse , "" , true, "", true);
                ff.pack();
                AEnv.showCenterScreen(ff);
            } else if (command.getCommand() == CommandManager.CLOSE_STATEMENT) {
                Env.setContext(pos.getCtx(), pos.getWindowNo() , "C_POS_ID" , pos.getC_POS_ID());
                Dimension size = new Dimension(1024, 768);
                FormFrame ff = new FormFrame(pos.getWindowNo());
                ff.setSize(size);
                MBrowse browse = new MBrowse(Env.getCtx(), 50057 , null);
                new VBrowser(ff, true , pos.getWindowNo(), "" , browse , "" , true, "", true);
                ff.pack();
                AEnv.showCenterScreen(ff);
            }
        }
        catch (Exception exception)
        {
            waiting.setVisible(false);
            ADialog.error(pos.getWindowNo(), pos.getFrame() , exception.getLocalizedMessage());
        }
        finally {
            waiting.setVisible(false);
            waiting = null;
        }
    }

    public void show(Component component , int x , int y)
    {
        popupMenu.show(component, x , y);
    }

    @Override
    public void okAction(POSQueryInterface query) {
        if (query.getRecord_ID() <= 0)
            return;
        //	For Ticket
        if(query instanceof QueryBPartner) {
            executeCommand(currentCommand);
        }
    }

    @Override
    public void cancelAction(POSQueryInterface query) {
    }

    private void showError(ProcessInfo processInfo) throws AdempierePOSException
    {
        Optional<String> summary = Optional.ofNullable(processInfo.getSummary());
        Optional<String> logs = Optional.ofNullable(processInfo.getLogInfo());
        String errorMessage = Msg.parseTranslation(pos.getCtx() , processInfo.getTitle() + " @ProcessRunError@ @Summary@ : " + summary.orElse("") + " @ProcessFailed@ : " + logs.orElse(""));
        throw new AdempierePOSException(errorMessage);
    }

    private void showOkMessage(ProcessInfo processInfo)
    {
        pos.refreshHeader();
        Optional<String> summary = Optional.ofNullable(processInfo.getSummary());
        Optional<String> logs = Optional.ofNullable(processInfo.getLogInfo());
        String okMessage = Msg.parseTranslation(pos.getCtx() , " @AD_Process_ID@ "+ processInfo.getTitle() +" @Summary@ : " + summary.orElse("") + " @ProcessOK@ : " + logs.orElse(""));
        ADialog.info(pos.getWindowNo(), popupMenu ,"ProcessOK",  okMessage );
    }
}
