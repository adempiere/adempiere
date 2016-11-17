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

import java.util.Map;
import java.util.Optional;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.MBrowse;
import org.adempiere.pos.command.Command;
import org.adempiere.pos.command.CommandManager;
import org.adempiere.pos.command.CommandReceiver;
import org.adempiere.pos.search.QueryBPartner;
import org.adempiere.pos.search.WPOSQuery;
import org.adempiere.pos.search.WQueryBPartner;
import org.adempiere.pos.service.POSQueryInterface;
import org.adempiere.pos.service.POSQueryListener;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.apps.BusyDialog;
import org.adempiere.webui.panel.CustomForm;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.MBPartner;
import org.compiere.process.ProcessInfo;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.eevolution.form.WBrowser;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;

/**
 * Class that execute business logic from POS
 * eEvolution author Victor Perez <victor.perez@e-evolution.com> 
 * Raul Muñoz, rmunoz@erpcya.com, ERPCYA http://www.erpcya.com
 * @contributor Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/670">
 * 		@see FR [ 670 ] Standard process for return material on POS</a>
 */
public class WPOSActionMenu implements  POSQueryListener, EventListener{

    private WPOS pos;
    private WPOSQuery queryPartner;
    private Menupopup popupMenu;
    private CommandManager commandManager;
    private Command currentCommand;
	public static final String EVENT_ATTRIBUTE = "EVENT";

    public WPOSActionMenu(WPOS pos)
    {
        this.pos = pos;
        this.popupMenu =  new Menupopup();
        this.popupMenu.setStyle("background: #E8E3E3 repeat-y scroll 0 0 !important;");
        commandManager =  new CommandManager();
        for (Map.Entry<String, CommandReceiver> reeceivers: commandManager.getCommandReceivers().entrySet()) {
            CommandReceiver commandReceiver = reeceivers.getValue();
            addMenuItem(commandReceiver.getEvent());
        }

    }

    private void addMenuItem(String optionName)
    {
        Menuitem menuItem =  new Menuitem(optionName, null);
        popupMenu.appendChild(menuItem);
        menuItem.setAttribute(EVENT_ATTRIBUTE, optionName);
        menuItem.addEventListener(Events.ON_CLICK, this);
    }

    @Override
    public void onEvent(Event actionEvent) throws Exception {
        try {
        //popupMenu.setVisible(false);
        currentCommand = commandManager.getCommand((String)actionEvent.getTarget().getAttribute(EVENT_ATTRIBUTE));
        beforeExecutionCommand(currentCommand);
        } catch (AdempiereException exception) {
            FDialog.error(pos.getWindowNo(), pos.getForm() , exception.getLocalizedMessage());
        }
    }

    private void beforeExecutionCommand(Command command) throws AdempierePOSException
    {
        if (command.getCommand() == CommandManager.GENERATE_IMMEDIATE_INVOICE) {
            if (pos.isCompleted()) {
                queryPartner = new WQueryBPartner(pos);
                AEnv.showWindow(queryPartner);
                queryPartner.addOptionListener(this);
                queryPartner.showView();
            }
        } else if (command.getCommand() == CommandManager.GENERATE_REVERSE_SALES) {
            if (pos.isCompleted())
                executeCommand(command);
        } else if (command.getCommand() == CommandManager.GENERATE_RETURN) {
            executeCommand(command);
        } else if (command.getCommand() == CommandManager.GENERATE_WITHDRAWAL) {
            executeCommand(command);
        } else if (command.getCommand() == CommandManager.CLOSE_STATEMENT) {
            executeCommand(command);
        } else {
        	FDialog.info(pos.getWindowNo(), popupMenu, "DocProcessed", pos.getDocumentNo());
        }
    }

    private void afterExecutionCommand(Command command) {
    	
    }

    private void executeCommand(Command command)
    {
        BusyDialog waiting = new BusyDialog();
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
                if (FDialog.ask(pos.getWindowNo(), popupMenu, "StartProcess?", Msg.parseTranslation(pos.getCtx(), processMessage))) {
                    waiting.setPage(pos.v_Panel.getPage());
                    waiting.doHighlighted();
                    command.execute(receiver);
                    ProcessInfo processInfo = receiver.getProcessInfo();
                    waiting.dispose();
                    if (processInfo!= null && processInfo.isError()) {
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

                if (FDialog.ask(pos.getWindowNo(), popupMenu, "StartProcess?", Msg.parseTranslation(pos.getCtx(), processMessage))) {
                    waiting.setPage(pos.v_Panel.getPage());
                    waiting.doHighlighted();
                    command.execute(receiver);
                    ProcessInfo processInfo = receiver.getProcessInfo();
                    waiting.dispose();
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

                if (FDialog.ask(pos.getWindowNo(), popupMenu, "StartProcess?", Msg.parseTranslation(pos.getCtx(), processMessage))) {
                    waiting.setPage(pos.v_Panel.getPage());
                    waiting.doHighlighted();
                    command.execute(receiver);
                    ProcessInfo processInfo = receiver.getProcessInfo();
                    waiting.dispose();
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
                Env.setContext(pos.getCtx(), pos.getWindowNo(), "C_POS_ID", pos.getC_POS_ID());
                MBrowse browse = new MBrowse(Env.getCtx(), 50056, null);
                WBrowser browser = new WBrowser(true, pos.getWindowNo(), "", browse, "", true, "", true);
                CustomForm ff = browser.getForm();
                ff.setAttribute(org.adempiere.webui.component.Window.MODE_KEY, org.adempiere.webui.component.Window.MODE_EMBEDDED);
                ff.setAttribute(org.adempiere.webui.component.Window.INSERT_POSITION_KEY, org.adempiere.webui.component.Window.INSERT_NEXT);
                ff.setTitle(browse.getTitle());
                SessionManager.getAppDesktop().showWindow(ff);
            } else if (command.getCommand() == CommandManager.CLOSE_STATEMENT) {
                Env.setContext(pos.getCtx(), pos.getWindowNo(), "C_POS_ID", pos.getC_POS_ID());
                MBrowse browse = new MBrowse(Env.getCtx(), 50057, null);
                WBrowser browser = new WBrowser(true, pos.getWindowNo(), "", browse, "", true, "" , true);
                CustomForm ff = browser.getForm();
                ff.setAttribute(org.adempiere.webui.component.Window.MODE_KEY, org.adempiere.webui.component.Window.MODE_EMBEDDED);
                ff.setAttribute(org.adempiere.webui.component.Window.INSERT_POSITION_KEY, org.adempiere.webui.component.Window.INSERT_NEXT);
                ff.setTitle(browse.getTitle());
                SessionManager.getAppDesktop().showWindow(ff);
            }
        }
        catch (Exception exception)
        {
            waiting.dispose();
            FDialog.error(pos.getWindowNo(), pos.getForm(), exception.getLocalizedMessage());
        }
        finally {
            waiting.dispose();
            waiting = null;
        }
    }

    public Menupopup getPopUp()
    {
    	return popupMenu;
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
        FDialog.info(pos.getWindowNo(), popupMenu ,"ProcessOK", okMessage);
    }
}
