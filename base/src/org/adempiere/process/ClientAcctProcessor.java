/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2021 ADempiere Foundation, All Rights Reserved.         *
 * Copyright (C) Carlos Ruiz - globalqss                                      *
 * Copyright (C) Contributors                                                 *
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

import org.compiere.acct.SessionPoster;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MAcctSchemaProvider;
import org.compiere.model.MClient;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.Msg;

/**
 * @author Carlos Ruiz
 * @author eEvolution author Victor Perez <victor.perez@e-evolution.com>
 * @see [ 1250 ] Client Accounting Processor error to get standard timestamp</a>
 *      <a href="https://github.com/adempiere/adempiere/issues/1250">
 */
public class ClientAcctProcessor extends ClientAcctProcessorAbstract {

    private SessionPoster sessionPoster = new SessionPoster();
    private MAcctSchemaProvider provider = new MAcctSchemaProvider();

    MAcctSchemaProvider getAcctSchemaProvider() {

        return provider;

    }

    SessionPoster getSessionPoster() {
    
        return sessionPoster;
    
    }

    void checkClientAccountingIsEnabled() throws AdempiereUserError {

        if (!isClientAccountingEnabled())
            throw new AdempiereUserError(
                    Msg.getMsg(getCtx(), "ClientAccountingNotEnabled"));

    }

    boolean isClientAccountingEnabled() {
    
        return MClient.isClientAccounting();
    
    } 

    MAcctSchema[] getAcctSchema() {
        
        return getAcctSchemaProvider().getAcctSchemas(getCtx(), getAcctSchemaId(), get_TrxName());
    
    }


    @Override
    protected String doIt() throws Exception {

        checkClientAccountingIsEnabled();

        String results =
                getSessionPoster()
                        .withTransactionName(get_TrxName())
                        .withAccountingSchemas(getAcctSchema())
                        .withTableId(getTableId())
                        .post();

        addLog(results);

        return "@OK@";

    }

}
