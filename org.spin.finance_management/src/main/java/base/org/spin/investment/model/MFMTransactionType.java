/******************************************************************************
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
 * Copyright (C) 2003-2016 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************/

package org.spin.investment.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.core.domains.models.I_FM_TransactionType_Acct;
import org.adempiere.core.domains.models.X_FM_TransactionType;
import org.adempiere.core.domains.models.X_FM_TransactionType_Acct;
import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Util;
/**
 * Financial Management
 *
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 *      <li> FR [ 1583 ] New Definition for loan
 *		@see https://github.com/adempiere/adempiere/issues/1583
 */
public class MFMTransactionType extends X_FM_TransactionType {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3948984837847121881L;

	public MFMTransactionType(Properties ctx, int FM_TransactionType_ID, String trxName) {
		super(ctx, FM_TransactionType_ID, trxName);
	}

	public MFMTransactionType(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

    /**
     * Get Account configuration
     * @param acctSchemaId
     * @return
     */
    public X_FM_TransactionType_Acct getTransactionTypeAcct(int acctSchemaId) {
    	return new Query(getCtx(), I_FM_TransactionType_Acct.Table_Name, 
    			"FM_TransactionType_ID = ? AND C_AcctSchema_ID = ?", get_TrxName())
    		.setParameters(getFM_TransactionType_ID(), acctSchemaId)
    		.first();
    }
    
    public int getTransactionTypeAccountCR() {
        String sql = " FM_Expense_Acct FROM FM_TransactionType c " +
                " INNER JOIN FM_TransactionType_Acct ca ON (c.FM_TransactionType_ID=ca.FM_TransactionType_ID)" +
                " WHERE c.FM_TransactionType_ID " + getFM_TransactionType_ID();
        int result = DB.getSQLValue("TransactionTypeCR", sql);
        if (result > 0)
            return result;
        return 0;
    }

    public int getTransactionTypeAccountDR() {
        String sql = " FM_Revenue_Acct FROM FM_TransactionType c " +
                " INNER JOIN FM_TransactionType_Acct ca ON (c.FM_TransactionType_ID=ca.FM_TransactionType_ID)" +
                " WHERE c.FM_TransactionType_ID " + getFM_TransactionType_ID();
        int result = DB.getSQLValue("TransactionTypeCR", sql);
        if (result > 0)
            return result;
        return 0;
    }
    
    /** Static Cache */
    private static CCache<String, MFMTransactionType> transactionTypeCacheValue = new CCache<String, MFMTransactionType>(Table_Name + "_Value", 100);
    private static CCache<String, MFMTransactionType> transactionTypeCacheType = new CCache<String, MFMTransactionType>(Table_Name + "_Type", 100);
	private static CCache<Integer, MFMTransactionType> transactionTypeCacheIds = new CCache<Integer, MFMTransactionType>(Table_Name, 30);
    
    /**
     * Get transaction type from main type
     * @param type
     * @return
     */
    public static MFMTransactionType getTransactionTypeFromValue(Properties ctx, String transactionTypeValue) {
        if (Util.isEmpty(transactionTypeValue, true)) {
        	return null;
        }
        //	
        int clientId = Env.getAD_Client_ID(ctx);
        final String key = clientId + "#" + transactionTypeValue;
        MFMTransactionType transactionType = transactionTypeCacheValue.get(key);
        if (transactionType != null)
            return transactionType;

        final String whereClause = COLUMNNAME_Value + "=? AND AD_Client_ID IN (?,?)";
        transactionType = new Query(ctx, Table_Name, whereClause, null)
                .setParameters(transactionTypeValue, 0, clientId)
                .setOnlyActiveRecords(true)
                .setOrderBy("AD_Client_ID DESC")
                .first();
        if (transactionType != null) {
            transactionTypeCacheValue.put(key, transactionType);
            transactionTypeCacheIds.put(transactionType.get_ID(), transactionType);
        }
        return transactionType;
    }
    
    /**
     * Get transaction type from main type
     * @param type
     * @return
     */
    public static MFMTransactionType getTransactionTypeFromType(Properties ctx, String type) {
        if (Util.isEmpty(type, true)) {
        	return null;
        }
        //	
        int clientId = Env.getAD_Client_ID(ctx);
        final String key = clientId + "#" + type;
        MFMTransactionType transactionType = transactionTypeCacheType.get(key);
        if (transactionType != null)
            return transactionType;

        final String whereClause = COLUMNNAME_Type + "=? AND AD_Client_ID IN (?,?)";
        transactionType = new Query(ctx, Table_Name, whereClause, null)
                .setParameters(type, 0, clientId)
                .setOnlyActiveRecords(true)
                .setOrderBy("AD_Client_ID DESC")
                .first();
        if (transactionType != null) {
        	transactionTypeCacheType.put(key, transactionType);
            transactionTypeCacheIds.put(transactionType.get_ID(), transactionType);
        }
        return transactionType;
    }
    
	/**
	 * Get/Load Transaction Type [CACHED]
	 * @param ctx context
	 * @param transactionTypeId
	 * @return activity or null
	 */
	public static MFMTransactionType getById(Properties ctx, int transactionTypeId) {
		if (transactionTypeId <= 0)
			return null;

		MFMTransactionType transactionType = transactionTypeCacheIds.get(transactionTypeId);
		if (transactionType != null && transactionType.get_ID() > 0)
			return transactionType;

		transactionType = new Query(ctx , Table_Name , COLUMNNAME_FM_TransactionType_ID + "=?" , null)
				.setClient_ID()
				.setParameters(transactionTypeId)
				.first();
		if (transactionType != null && transactionType.get_ID() > 0) {
			transactionTypeCacheIds.put(transactionType.get_ID(), transactionType);
		}
		return transactionType;
	}
    
    /**
     * Return Value + Name
     *
     * @return Value
     */
    public String toString() {
        return getValue() + " - " + getName();
    }   //  toString
}
