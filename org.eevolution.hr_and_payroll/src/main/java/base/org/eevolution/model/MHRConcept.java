/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms either version 2 of the  License, 						  *
 * or (at your option) any later version.									  *
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
 *****************************************************************************/
package org.eevolution.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MRule;
import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;

/**
 * Payroll Concept for HRayroll Module
 *
 * @author Oscar Gómez Islas
 * @author Cristina Ghita, www.arhipac.ro
 * @author victor.perez@e-evolution.com , www.e-Evolution.com
 */
public class MHRConcept extends X_HR_Concept {
    /**
     *
     */
    private static final long serialVersionUID = 7859469065116713767L;

    /**
     * Cache
     */
    private static CCache<Integer, MHRConcept> cache = new CCache<Integer, MHRConcept>(Table_Name, 100);
    /**
     * Cache by Value
     */
    private static CCache<String, MHRConcept> cacheValue = new CCache<String, MHRConcept>(Table_Name + "_Value", 100);

    /**
     * Standard Constructor
     *
     * @param ctx           context
     * @param HR_Concept_ID
     * @param trxName
     */
    public MHRConcept(Properties ctx, int HR_Concept_ID, String trxName) {
        super(ctx, HR_Concept_ID, trxName);
        if (HR_Concept_ID == 0) {
            setValue("");
            setName("");
            setDescription("");
            setIsEmployee(false);
            setIsPrinted(false);
            setHR_Payroll_ID(0);
            setHR_Job_ID(0);
            setHR_Department_ID(0);
        }
    }    //	HRConcept

    /**
     * Load Constructor
     *
     * @param ctx     context
     * @param rs      result set
     * @param trxName
     */
    public MHRConcept(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    public static MHRConcept get(Properties ctx, int conceptId) {
        if (conceptId <= 0)
            return null;
        //
        MHRConcept concept = cache.get(conceptId);
        if (concept != null)
            return concept;
        //
        concept = new MHRConcept(ctx, conceptId, null);
        if (concept.get_ID() == conceptId)
            cache.put(conceptId, concept);
        else
            concept = null;

        return concept;
    }

    /**
     * for value
     *
     * @param ctx
     * @param conceptValue
     * @return
     */
    @Deprecated
    public static MHRConcept forValue(Properties ctx, String conceptValue) {
        return getByValue(ctx, conceptValue);
    }

    /**
     * Get Concept by Value
     *
     * @param ctx
     * @param conceptValue
     * @return
     */
    public static MHRConcept getByValue(Properties ctx, String conceptValue) {
        if (Util.isEmpty(conceptValue, true))
            return null;

        int AD_Client_ID = Env.getAD_Client_ID(ctx);
        final String key = AD_Client_ID + "#" + conceptValue;
        MHRConcept concept = cacheValue.get(key);
        if (concept != null)
            return concept;

        final String whereClause = COLUMNNAME_Value + "=? AND AD_Client_ID IN (?,?)";
        concept = new Query(ctx, Table_Name, whereClause, null)
                .setParameters(conceptValue, 0, AD_Client_ID)
                .setOnlyActiveRecords(true)
                .setOrderBy("AD_Client_ID DESC")
                .first();
        if (concept != null) {
            cacheValue.put(key, concept);
            cache.put(concept.get_ID(), concept);
        }
        return concept;
    }

    /**
     * Get Employee's of Payroll Type
     *
     * @param payrollId    Payroll ID
     * @param departmentId Department ID
     * @param departmentId Employee_ID
     * @param sqlWhere     Clause SQLWhere
     * @return lines
     */
    public static MHRConcept[] getConcepts(int payrollId, int departmentId, String sqlWhere) {
        Properties ctx = Env.getCtx();
        List<Object> params = new ArrayList<Object>();
        StringBuffer whereClause = new StringBuffer();

        whereClause.append("AD_Client_ID in (?,?)");
        params.add(0);
        params.add(Env.getAD_Client_ID(Env.getCtx()));

        whereClause.append(" AND (" + COLUMNNAME_HR_Payroll_ID + " =? OR "
                + COLUMNNAME_HR_Payroll_ID + " IS NULL)");
        params.add(payrollId);

        if (departmentId != 0) {
            whereClause.append(" AND HR_Concept.HR_Department_ID=?");
            params.add(departmentId);
        }

        if (!Util.isEmpty(sqlWhere)) {
            whereClause.append(sqlWhere);
        }

        List<MHRConcept> list = new Query(ctx, Table_Name, whereClause.toString(), null)
                .setParameters(params)
                .setOnlyActiveRecords(true)
                .setOrderBy("COALESCE(" + COLUMNNAME_SeqNo + ",999999999999) DESC, " + COLUMNNAME_Value)
                .list();
        return list.toArray(new MHRConcept[list.size()]);
    }    //	getConcept

    public int getConceptAccountCR() {
        String sql = " HR_Expense_Acct FROM HR_Concept c " +
                " INNER JOIN HR_Concept_Acct ca ON (c.HR_Concept_ID=ca.HR_Concept_ID)" +
                " WHERE c.HR_Concept_ID " + getHR_Concept_ID();
        int result = DB.getSQLValue("ConceptCR", sql);
        if (result > 0)
            return result;
        return 0;
    }

    public int getConceptAccountDR() {
        String sql = " HR_Revenue_Acct FROM HR_Concept c " +
                " INNER JOIN HR_Concept_Acct ca ON (c.HR_Concept_ID=ca.HR_Concept_ID)" +
                " WHERE c.HR_Concept_ID " + getHR_Concept_ID();
        int result = DB.getSQLValue("ConceptCR", sql);
        if (result > 0)
            return result;
        return 0;
    }

    /**
     * Return Value + Name
     *
     * @return Value
     */
    public String toString() {
        return getValue() + " - " + getName();
    }   //  toString

    /**************************************************************************
     * 	Before Save
     *    @param newRecord new
     *	@return save
     */
    protected boolean beforeSave(boolean newRecord) {
        if (is_Changed() && is_ValueChanged(MHRConcept.COLUMNNAME_Value)) {
            final String errorMessage = validateRules((String) get_ValueOld(MHRConcept.COLUMNNAME_Value));
            if (errorMessage.length() > 0)
                throw new AdempiereException("@HR_Concept_ID@ @RecordFound@ " + errorMessage);
        }
        return true;
    }


    /**
     * Before Delete
     *
     * @return true of it can be deleted
     */
    protected boolean beforeDelete() {
        final String errorMessage = validateRules(getValue());
        if (errorMessage.length() > 0)
            throw new AdempiereException("@HR_Concept_ID@ @CannotDeleteUsed@ " + errorMessage);
        return true;
    }

    private String validateRules(String conceptValue) {
        AtomicReference<String> errorMessage = new AtomicReference<>("");
        List<MRule> rules = getRulesDependences(conceptValue);
        rules.forEach(rule -> {
            String error = Msg.parseTranslation(getCtx(), " @AD_Rule_ID@: ") + rule.getValue() + Msg.parseTranslation(getCtx(), " -> ") + rule.getName() + " , ";
            errorMessage.getAndUpdate(msg -> msg + error);
        });
        return errorMessage.get();
    }

    private List<MRule> getRulesDependences(String conceptValue) {
        final StringBuilder whereClause = new StringBuilder();
        whereClause
                .append(MRule.COLUMNNAME_EventType).append("=? AND ")
                .append(MRule.COLUMNNAME_Script)
                .append(" LIKE '%\"" + conceptValue + "\"%'");
        return new Query(getCtx(), MRule.Table_Name, whereClause.toString(), get_TrxName())
                .setParameters(MRule.EVENTTYPE_HumanResourcePayroll)
                .list();
    }

}    //	HRConcept