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
import org.compiere.model.MProcess;
import org.compiere.process.ProcessInfo;
import org.compiere.util.Env;
import org.compiere.util.Msg;

import java.util.Optional;
import java.util.Properties;

/**
 * Command Receiver DTO for management events
 * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 23/01/16.
 */
public class CommandReceiver {

    private Properties ctx;
    private Optional<Integer> processId;
    private Optional<Integer> bankAccountId;
    private Optional<Integer> posId;
    private Optional<String> value;
    private Optional<String> name;
    private Optional<Integer> orderId;
    private Optional<Integer> partnerId;
    private Optional<Integer> warehouseId;
    private Optional<String> event;
    private ProcessInfo processInfo;

    /**
     *
     * @param processId
     * @param value
     * @param event
     */
    public CommandReceiver(Integer processId, String value, String event) {
        this.setValue(value);
        //Command Based on Process
        if (processId != null && processId > 0) {
            this.setName(MProcess.get(Env.getCtx(), processId).getName());
            if (event != null)
                this.setEvent(Msg.parseTranslation(Env.getCtx(), event));
            else
                this.setEvent(getName());
        }
        else if (value != null && value.length() > 0) {
            this.setProcessId(MProcess.getProcess_ID(value, null));
            this.setName(MProcess.get(Env.getCtx(), this.getProcessId()).getName());
            if (event != null)
                this.setEvent(Msg.parseTranslation(Env.getCtx(), event));
            else
                this.setEvent(getName());
        }
        else if (value == null && event != null && event.length() > 0) //Command not based on procedure
        {
            this.setEvent(Msg.parseTranslation(Env.getCtx(), event));
        }
    }

    public Properties getCtx() {
        return ctx;
    }

    public void setCtx(Properties ctx) {
        this.ctx = ctx;
    }

    public Integer getProcessId() {
        return processId.orElseThrow(() -> new AdempierePOSException("@AD_Process_ID@ @NotFound@"));
    }

    public void setProcessId(Integer processId) {
        this.processId = Optional.ofNullable(processId);
    }

    public String getValue() {
        return value.orElseThrow(() -> new AdempierePOSException("@Value@ @NotFound@"));
    }

    public void setValue(String value) {
        this.value = Optional.ofNullable(value);
    }

    public String getName() {
        return name.orElseThrow(() -> new AdempierePOSException("@Name@ @NotFound@"));
    }

    public void setName(String name) {
        this.name = Optional.ofNullable(name);
    }

    public String getEvent() {
        return event.orElseThrow(() -> new AdempierePOSException("Event Name @NotFound@"));
    }

    public void setEvent(String event) {
        this.event = Optional.ofNullable(event);
    }

    public Integer getOrderId() {
        return orderId.orElseThrow(() -> new AdempierePOSException("@C_Order_ID@ @NotFound@"));
    }

    public void setOrderId(int orderId) {
        this.orderId = Optional.ofNullable(orderId);
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = Optional.ofNullable(partnerId);
    }

    public Integer getPartnerId() {
        return partnerId.orElseThrow(() -> new AdempierePOSException("@C_BPartner_ID@ @NotFound@"));
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = Optional.ofNullable(warehouseId);
    }

    public Integer getWarehouseId() {
        return warehouseId.orElseThrow(() -> new AdempierePOSException("@M_Warehouse_ID@ @NotFound@"));
    }

    public void setBankAccountId(Integer bankAccountId) {
        this.bankAccountId = Optional.ofNullable(bankAccountId);
    }

    public Integer getBankAccountId() {
        return bankAccountId.orElseThrow(() -> new AdempierePOSException("@C_BankAccount_ID@ @NotFound@"));
    }

    public void setPOSId(Integer posId) {
        this.posId = Optional.ofNullable(posId);
    }

    public Integer getPOSId() {
        return posId.orElseThrow(() -> new AdempierePOSException("@C_POS_ID@ @NotFound@"));
    }

    public void setProcessInfo(ProcessInfo processInfo) { this.processInfo = processInfo;}

    public ProcessInfo getProcessInfo() {return processInfo;}
}
