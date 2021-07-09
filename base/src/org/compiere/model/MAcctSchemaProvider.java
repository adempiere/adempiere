/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2021 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
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
package org.compiere.model;

import java.util.Properties;

/**
 * A wrapper class for MAcctSchema to enable testing without having to create
 * all the baggage of a full AcctSchema
 */
public class MAcctSchemaProvider {

    /**
     *  Get AccountSchema of Client
     *  @param ctx context
     *  @param C_AcctSchema_ID schema id
     *  @param trxName optional trx
     *  @return Accounting schema
     */
    public MAcctSchema get(Properties ctx, int schemaId, String trxName) {

        return MAcctSchema.get(ctx, schemaId, trxName);

    }

    /**
     * Returns an array of MAcctSchema. If schemaID is zero, all schema
     * for the client specified in the context will be returned.  If non
     * zero, a specific schema will be returned. If none are found or there
     * is an error, the array will be empty.
     * @param ctx the Context specifying the default client
     * @param schemaId the C_AcctSchema_ID to search for or zero for all. 
     * @param trxName The transaction name to use
     * @return a non-null Array of MAcctSchema containing the relevant schema.
     */
    public MAcctSchema[] getAcctSchemas(Properties ctx, int schemaId, String trxName) {
        
        return MAcctSchema.getAcctSchema(ctx, schemaId, trxName);
    }

    /**
     * Returns an array of MAcctSchema. If schemaID is zero, all schema
     * for the client specified in the context will be returned.  If non
     * zero, a specific schema will be returned. If none are found or there
     * is an error, the array will be empty.
     * @param ctx the Context specifying the default client
     * @param specificClientId the id of a client. Set to zero to use the default 
     * in the context.
     * @param schemaId the C_AcctSchema_ID to search for or zero for all. 
     * @param trxName The transaction name to use
     * @return a non-null Array of MAcctSchema containing the relevant schema.
     */
    public MAcctSchema[] getAcctSchemas(Properties ctx, int specificClientId, 
            int schemaId, String trxName) {
        
        return MAcctSchema.getAcctSchema(ctx, specificClientId, schemaId, trxName);

    }

}
