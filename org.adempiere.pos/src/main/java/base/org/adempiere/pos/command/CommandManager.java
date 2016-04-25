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
import org.adempiere.pos.process.CloseStatementPOS;
import org.adempiere.pos.process.GenerateWithdrawal;

import java.util.HashMap;

/**
 * Command Manager create the command for action process
 * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 23/01/16.
 */
public class CommandManager {

    public static String GENERATE_IMMEDIATE_INVOICE =  "C_POS Generate Immediate Invoice";
    public static String GENERATE_REVERSE_SALES = "C_POS ReverseTheSalesTransaction";
    public static String GENERATE_RETURN = "C_POS CreateOrderBasedOnAnother";
    public static String COMPLETE_DOCUMENT = "Complete Document";
    public static String GENERATE_WITHDRAWAL = GenerateWithdrawal.NAME;
    public static String CLOSE_STATEMENT = CloseStatementPOS.NAME;

    private HashMap<String , Command> commands = new HashMap<String , Command>();

    private HashMap<String , CommandReceiver> receivers = new HashMap<String , CommandReceiver>(){
        {
            CommandReceiver   commandReceiver = new CommandReceiver(null, GENERATE_IMMEDIATE_INVOICE, null);
            commands.put(GENERATE_IMMEDIATE_INVOICE, new CommandImmediateInvoice(GENERATE_IMMEDIATE_INVOICE ,commandReceiver.getEvent()));
            put(GENERATE_IMMEDIATE_INVOICE, commandReceiver);

            commandReceiver =  new CommandReceiver(null, GENERATE_REVERSE_SALES, null);
            commands.put(GENERATE_REVERSE_SALES, new CommandReverseSalesTransaction(GENERATE_REVERSE_SALES, commandReceiver.getEvent()));
            put(GENERATE_REVERSE_SALES,commandReceiver);

            commandReceiver = new CommandReceiver(null, GENERATE_RETURN, "Crear Devolución Parcial sin @M_RMA_ID@");
            commands.put(GENERATE_RETURN , new CommandGenerateReturn(GENERATE_RETURN, commandReceiver.getEvent()));
            put(GENERATE_RETURN, commandReceiver);

            commandReceiver = new CommandReceiver(null, COMPLETE_DOCUMENT, "@smenu.complete.prepared.order@");
            commands.put(COMPLETE_DOCUMENT , new CommandCompleteReturnMaterial(COMPLETE_DOCUMENT,commandReceiver.getEvent()));
            put(COMPLETE_DOCUMENT, commandReceiver);

            commandReceiver = new CommandReceiver(null, GENERATE_WITHDRAWAL, GENERATE_WITHDRAWAL);
            commands.put(GENERATE_WITHDRAWAL, new CommandWithdrawal(GENERATE_WITHDRAWAL,commandReceiver.getEvent()));
            put(GENERATE_WITHDRAWAL, commandReceiver);

            commandReceiver = new CommandReceiver(null, CLOSE_STATEMENT, CLOSE_STATEMENT);
            commands.put(CLOSE_STATEMENT, new CommandWithdrawal(CLOSE_STATEMENT,commandReceiver.getEvent()));
            put(CLOSE_STATEMENT, commandReceiver);
        }
    };

    public HashMap<String , CommandReceiver> getCommandReceivers() {
        return receivers;
    }

    public Command getCommand(String event)
    {
        return commands.values()
                .stream()
                .filter(action -> action.getEvent() == event)
                .findFirst()
                .orElseThrow(() -> new AdempierePOSException(event + " @NotFound@"));
    }

    public CommandReceiver getCommandReceivers(String event)
    {
        return receivers.values()
                .stream()
                .filter(action -> action.getEvent() == event)
                .findFirst()
                .orElseThrow(() -> new AdempierePOSException(event + " @NotFound@ "));
    }
}
