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
import org.adempiere.pos.command.Command;
import org.adempiere.pos.command.CommandManager;
import org.adempiere.pos.command.CommandReceiver;
import org.adempiere.pos.search.QueryBPartner;
import org.adempiere.pos.search.WPOSQuery;
import org.adempiere.pos.search.WQueryBPartner;
import org.adempiere.pos.service.I_POSQuery;
import org.adempiere.pos.service.POSQueryListener;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.apps.BusyDialog;
import org.adempiere.webui.window.FDialog;
import org.compiere.apps.ADialog;
import org.compiere.model.MBPartner;
import org.compiere.model.MOrder;
import org.compiere.process.ProcessInfo;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;

/**
 * Class that execute business logic from POS
 * eEvolution author Victor Perez <victor.perez@e-evolution.com> 
 * Raul Muñoz, rmunoz@erpcya.com, ERPCYA http://www.erpcya.com
 */
public class WPOSActionMenu implements  POSQueryListener, EventListener{

    private WPOS pos;
    private Integer partnerId;
    private WPOSQuery queryPartner;
    private Menupopup popupMenu;
    private CommandManager commandManager;
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



    private void beforeExecutionCommand(Command commandAction) throws AdempierePOSException
    {
        if (commandAction.getCommand() == CommandManager.GENERATE_IMMEDIATE_INVOICE)
        {
            queryPartner = new WQueryBPartner(pos);
            AEnv.showWindow(queryPartner);
            queryPartner.addOptionListener(this);
            queryPartner.showView();
            return;
        }
        if (commandAction.getCommand() == CommandManager.GENERATE_REVERSE_SALES)
        {
            if (pos.isCompleted()) {
                queryPartner = new WQueryBPartner(pos);
                queryPartner.addOptionListener(this);
                queryPartner.showView();
                AEnv.showWindow(queryPartner);
                return;
            }
        }

        if (commandAction.getCommand() == CommandManager.GENERATE_RETURN)
        {
            executeCommand(commandAction);
            return;
        }

        if (commandAction.getCommand() == CommandManager.COMPLETE_DOCUMENT
        		&& (pos.isDrafted() || pos.isInProgress() || pos.isInvalid())) {
        	Trx.run(new TrxRunnable() {
        		public void run(String trxName) {
        			if (!pos.processOrder(trxName, false, false)) {
        				String errorMessage = Msg.parseTranslation(pos.getCtx(), " @ProcessRunError@. " 
        						+ "@order.no@: " + pos.getDocumentNo()+ ". @Process@: " + CommandManager.COMPLETE_DOCUMENT);
        				throw new AdempierePOSException(errorMessage);
        			}
        			pos.refreshHeader();
        		}
        	});
        	
        	// For certain documents, there is no further processing
        	String docSubTypeSO = pos.getM_Order().getC_DocTypeTarget().getDocSubTypeSO();
        	if((docSubTypeSO.equals(MOrder.DocSubTypeSO_Standard) ||
        		docSubTypeSO.equals(MOrder.DocSubTypeSO_OnCredit) ||
        		docSubTypeSO.equals(MOrder.DocSubTypeSO_Warehouse)) 
        		&& pos.getM_Order().getDocStatus().equals(MOrder.DOCSTATUS_Completed)) {        		
        		String message = Msg.parseTranslation(pos.getCtx(), " @DocProcessed@. " 
        				+ "@order.no@: " + pos.getDocumentNo()+ ". @Process@: " + CommandManager.COMPLETE_DOCUMENT);
        		FDialog.info(pos.getWindowNo(), popupMenu ,"DocProcessed",  message );
        	}
        	else
        		executeCommand(commandAction);
        }
            else
                FDialog.info(pos.getWindowNo(), popupMenu, "DocProcessed", pos.getDocumentNo());
    }

    private void afterExecutionCommand(Command command) throws AdempierePOSException
    {
    }

    private void executeCommand(Command commandAction) throws AdempierePOSException
    {
        CommandReceiver receiver = commandManager.getCommandReceivers(commandAction.getEvent());

        if (commandAction.getCommand() == CommandManager.GENERATE_IMMEDIATE_INVOICE
            &&  pos.getC_Order_ID() > 0
            &&  !pos.isVoided()) {
            receiver.setCtx(pos.getCtx());
            receiver.setPartnerId(queryPartner.getRecord_ID());
            receiver.setOrderId(pos.getC_Order_ID());
            MBPartner partner = MBPartner.get(pos.getCtx(), receiver.getPartnerId());
            Optional<String> taxId = Optional.ofNullable(partner.getTaxID());
            String processMessage = receiver.getName()
                    + " @order.no@ : " + pos.getDocumentNo()
                    + " @To@ @C_BPartner_ID@ : " + partner.getName()
                    + " @TaxID@ : " + taxId.orElse("");
            if (FDialog.ask(pos.getWindowNo(), popupMenu, "StartProcess?", Msg.parseTranslation(pos.getCtx(), processMessage))) {
            			BusyDialog waiting = new BusyDialog();
            			waiting.setPage(pos.v_Panel.getPage());
            			waiting.doHighlighted();
                        commandAction.execute(receiver);
                        ProcessInfo processInfo = receiver.getProcessInfo();

                        waiting.dispose();
                        waiting = null;
            			
                        if (processInfo.isError()) {
                            String errorMessage = Msg.parseTranslation(pos.getCtx(), processInfo.getTitle() + " @ProcessRunError@ " + processInfo.getSummary());
                            throw new AdempierePOSException(errorMessage);
                        } else {
                            afterExecutionCommand(commandAction);
                            showOkMessage(processInfo);
                            pos.setOrder(processInfo.getRecord_ID());
                            pos.refreshHeader();
                        }
                return;
            }
        }
        //Reverse The Sales Transaction
        if (commandAction.getCommand() == CommandManager.GENERATE_REVERSE_SALES
                && pos.getC_Order_ID() > 0
                && !pos.isReturnMaterial()
                && !pos.isVoided()
                && !pos.isClosed())
        {
            receiver.setCtx(pos.getCtx());
            receiver.setOrderId(pos.getC_Order_ID());
            receiver.setPartnerId(partnerId > 0 ? pos.getC_BPartner_ID() : pos.getC_BPartner_ID());
            String processMessage = receiver.getName()
                    + " @order.no@ : " + pos.getDocumentNo()
                    + " @To@ @C_BPartner_ID@ : " + pos.getBPName();

            if (FDialog.ask(pos.getWindowNo(), popupMenu, "StartProcess?", Msg.parseTranslation(pos.getCtx(), processMessage))) {
            	BusyDialog waiting = new BusyDialog();
    			waiting.setPage(pos.v_Panel.getPage());
    			waiting.doHighlighted();
                commandAction.execute(receiver);
                ProcessInfo processInfo = receiver.getProcessInfo();
                waiting.dispose();
                waiting = null;
                if (processInfo.isError()) {
                    showError(processInfo);
                }
                else
                {
                    afterExecutionCommand(commandAction);
                    showOkMessage(processInfo);
                }
                return;
            }
        }
        //Return product
        if (commandAction.getCommand() == CommandManager.GENERATE_RETURN  &&  pos.getC_Order_ID() > 0 && !pos.isReturnMaterial())
        {
            receiver.setCtx(pos.getCtx());
            receiver.setOrderId(pos.getC_Order_ID());
            receiver.setPartnerId(pos.getC_BPartner_ID());
            String processMessage = receiver.getName()
                    + " @DisplayDocumentInfo@ : " + pos.getDocumentNo()
                    + " @To@ @C_BPartner_ID@ : " + pos.getBPName();

            if (FDialog.ask(pos.getWindowNo(), popupMenu, "StartProcess?", Msg.parseTranslation(pos.getCtx(), processMessage))) {

            	BusyDialog waiting = new BusyDialog();
    			waiting.setPage(pos.v_Panel.getPage());
    			waiting.doHighlighted();
                commandAction.execute(receiver);
                ProcessInfo processInfo = receiver.getProcessInfo();
                waiting.dispose();
                waiting = null;
                if (processInfo.isError()) {
                    showError(processInfo);
                }
                else
                {
                    afterExecutionCommand(commandAction);
                    showOkMessage(processInfo);
                    //execute out transaction
                    if (processInfo.getRecord_ID() > 0) {
                        pos.setOrder(processInfo.getRecord_ID());
                        pos.refreshHeader();
                    }
                }

                return;
            }
        }
        if (commandAction.getCommand() == CommandManager.COMPLETE_DOCUMENT  &&  pos.getC_Order_ID() > 0)
        {
            if (pos.isReturnMaterial() && pos.isCompleted())
            {
                    receiver.setCtx(pos.getCtx());
                    receiver.setOrderId(pos.getC_Order_ID());
                    receiver.setWarehouseId(pos.getM_Warehouse_ID());
                	BusyDialog waiting = new BusyDialog();
        			waiting.setPage(pos.v_Panel.getPage());
        			waiting.doHighlighted();
                    commandAction.execute(receiver);
                    ProcessInfo processInfo = receiver.getProcessInfo();
                    waiting.dispose();
                    waiting = null;
                    if (processInfo.isError()) {
                       showError(processInfo);
                    }
                    afterExecutionCommand(commandAction);
                    showOkMessage(processInfo);
                    pos.setOrder(processInfo.getRecord_ID());
                    pos.refreshHeader();
            }
        }
    }

    public Menupopup getPopUp()
    {
    	return popupMenu;
    }

    @Override
    public void okAction(I_POSQuery query) {
        if (query.getRecord_ID() <= 0)
            return;
        //	For Ticket
        if(query instanceof QueryBPartner) {
          partnerId = query.getRecord_ID();
        }
    }

    @Override
    public void cancelAction(I_POSQuery query) {
    }

    private void showError(ProcessInfo processInfo) throws AdempierePOSException
    {
        String errorMessage = Msg.parseTranslation(pos.getCtx() , processInfo.getTitle() + " @ProcessRunError@ " + processInfo.getSummary() + " " + processInfo.getLogInfo());
        throw new AdempierePOSException(errorMessage);
    }

    private void showOkMessage(ProcessInfo processInfo)
    {
        pos.refreshHeader();
        FDialog.info(pos.getWindowNo(), popupMenu ,"ProcessOK", processInfo.getTitle() + " " + processInfo.getSummary() + " " + processInfo.getLogInfo());
    }

	@Override
	public void onEvent(Event actionEvent) throws Exception {
		 try {
//		        popupMenu.setVisible(false);
		        Command command = commandManager.getCommand((String)actionEvent.getTarget().getAttribute(EVENT_ATTRIBUTE));
		        beforeExecutionCommand(command);
		        } catch (AdempiereException exception) {
		            FDialog.error(pos.getWindowNo(), pos.getForm() , exception.getLocalizedMessage());
		        }
	}
}
