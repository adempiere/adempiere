/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2021 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/

package org.adempiere.process;

import org.compiere.process.SvrProcess;

/**
 * Generated Process for (Client Accounting Processor)
 * 
 * @author ADempiere (generated)
 * @version Release 3.9.3
 */
public abstract class ClientAcctProcessorAbstract extends SvrProcess {

    /** Process Value */
    private static final String VALUE_FOR_PROCESS = 
            "Client_Acct_Processor";
    /** Process Name */
    private static final String NAME_FOR_PROCESS =
            "Client Accounting Processor";
    /** Process Id */
    private static final int ID_FOR_PROCESS = 53187;
    /** Parameter Name for Accounting Schema */
    public static final String C_ACCTSCHEMA_ID = "C_AcctSchema_ID";
    /** Parameter Name for Table */
    public static final String AD_TABLE_ID = "AD_Table_ID";
    /** Parameter Value for Accounting Schema */
    private int acctSchemaId;
    /** Parameter Value for Table */
    private int tableId;

    @Override
    protected void prepare() {

        acctSchemaId = getParameterAsInt(C_ACCTSCHEMA_ID);
        tableId = getParameterAsInt(AD_TABLE_ID);

    }

    /** Getter Parameter Value for Accounting Schema */
    protected int getAcctSchemaId() {

        return acctSchemaId;

    }

    /** Setter Parameter Value for Accounting Schema */
    protected void setAcctSchemaId(int acctSchemaId) {

        this.acctSchemaId = acctSchemaId;

    }

    /** Getter Parameter Value for Table */
    protected int getTableId() {

        return tableId;

    }

    /** Setter Parameter Value for Table */
    protected void setTableId(int tableId) {

        this.tableId = tableId;

    }

    /** Getter Parameter Value for Process ID */
    public static final int getProcessId() {

        return ID_FOR_PROCESS;

    }

    /** Getter Parameter Value for Process Value */
    public static final String getProcessValue() {

        return VALUE_FOR_PROCESS;

    }

    /** Getter Parameter Value for Process Name */
    public static final String getProcessName() {

        return NAME_FOR_PROCESS;

    }

}
