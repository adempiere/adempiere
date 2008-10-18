/******************************************************************************
 *  Product: Posterita Web-Based POS and Adempiere Plugin                     *
 *  Copyright (C) 2008  Posterita Ltd                                         *
 *  This file is part of POSterita                                            *
 *                                                                            *
 *  POSterita is free software; you can redistribute it and/or modify         *
 *  it under the terms of the GNU General Public License as published by      *
 *  the Free Software Foundation; either version 2 of the License, or         *
 *  (at your option) any later version.                                       *
 *                                                                            *
 *  This program is distributed in the hope that it will be useful,           *
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of            *
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the             *
 *  GNU General Public License for more details.                              *
 *                                                                            *
 *  You should have received a copy of the GNU General Public License along   *
 *  with this program; if not, write to the Free Software Foundation, Inc.,   *
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.               *
 *****************************************************************************/
package org.posterita.businesslogic.core;

import java.util.Properties;

import org.compiere.model.MAcctSchema;
import org.compiere.model.MAcctSchemaElement;
import org.compiere.model.MElementValue;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.posterita.exceptions.OperationException;
import org.posterita.util.PoManager;

/**
 * @author ashley 
 * Jun 2, 2008
 */
public class AccountingManager
{
    public static final String ELEMENT_DUMMY_CASH = "11909";
    
    
    /**
     * Retrieves the account element value Id of a particular account element value from it's value
     * @param ctx Context
     * @param value Account Element Value
     * @param trxName Transaction
     * @return Account Elelement Value Id
     */
    public static int getElementValueId(Properties ctx, String value, String trxName)
    {
        String sqlStmt = "SELECT C_ElementValue_ID FROM C_ElementValue WHERE AD_Client_ID=? AND Value=?";
        
        int adClientId = Env.getAD_Client_ID(ctx);
        
        int elementValueId = DB.getSQLValue(trxName, sqlStmt, adClientId, value);
        
        return elementValueId;
    }
    
    /**
     * Get or Creates the dummy cash account element value needed 
     * to do transfers across Bank accounts of different organisations.
     * @param ctx Context
     * @param trxName Transaction
     * @return Account Element Value
     * @throws OperationException
     */
    public static int getCreateDummyCashElement(Properties ctx, String trxName) throws OperationException
    {
        int elementValueId = getElementValueId(ctx, ELEMENT_DUMMY_CASH, trxName);
        
        if (elementValueId > 0)
        {
            return elementValueId;
        }
        
        int acctSchemaId = Env.getContextAsInt(ctx, "$C_AcctSchema_ID");
        
        String sqlStmt = "SELECT C_Element_ID FROM C_AcctSchema_Element WHERE C_AcctSchema_ID=? AND ElementType=?";
        
        int elementId = DB.getSQLValue(trxName, sqlStmt, acctSchemaId, MAcctSchemaElement.ELEMENTTYPE_Account);
        
        if (elementId <= 0)
        {
            throw new OperationException("Could not get Element of type Account from the accounting schema!!!");
        }
        
        MElementValue elementValue = new MElementValue(ctx, 0, trxName);
        elementValue.setAD_Org_ID(0);
        elementValue.setC_Element_ID(elementId);
        elementValue.setValue(ELEMENT_DUMMY_CASH);
        elementValue.setName("Dummy Cash");
        elementValue.setAccountType(MElementValue.ACCOUNTTYPE_Asset);
        elementValue.setAccountSign(MElementValue.ACCOUNTSIGN_Natural);
        elementValue.setPostActual(true);
        elementValue.setPostBudget(true);
        elementValue.setPostStatistical(true);
        elementValue.setIsDocControlled(false);
        elementValue.setIsBankAccount(false);
        
        PoManager.save(elementValue);
        
        elementValueId = elementValue.get_ID();
        
        return elementValueId;
    }
    
    
    /**
     * Returns the first accounting schema defined for the client
     * @param ctx Context
     * @param adClientId Client 
     * @param trxName Transaction
     * @return Accouting Schema Id
     */
    public static int getAcctSchemaId(Properties ctx, int adClientId, String trxName)
    {
        String sql = "SELECT C_AcctSchema_ID "
            + "FROM C_AcctSchema a, AD_ClientInfo c "
            + "WHERE a.C_AcctSchema_ID=c.C_AcctSchema1_ID "
            + "AND c.AD_Client_ID=?";
        
        int acctSchemaId = DB.getSQLValue(trxName, sql, adClientId);
        return acctSchemaId;
    }
    
    /**
     * Returns the currency of the accounting schema
     * @param ctx Context
     * @param clientId Client
     * @param trxName Transaction
     * @return Currency Id
     */
    public static int getCurrencyId(Properties ctx, int clientId, String trxName)
    {
        int acctSchemaId = getAcctSchemaId(ctx, clientId, trxName);
        MAcctSchema acctSchema = MAcctSchema.get(ctx, acctSchemaId);
        return acctSchema.getC_Currency_ID();
    }
}
