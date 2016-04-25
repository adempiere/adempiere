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

package org.adempiere.pos.process;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBankStatement;
import org.compiere.model.MBankStatementLine;
import org.compiere.model.MPayment;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.util.Msg;
import org.eevolution.grid.Browser;


import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * Close Statement for POS
 * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 04/15/16.
 */
public class CloseStatementPOS extends CloseStatementPOSAbstract {

    private BigDecimal lineTotalAmt = BigDecimal.ZERO;
    private BigDecimal paidAmt = BigDecimal.ZERO;
    private BigDecimal openAmt = BigDecimal.ZERO;
    private BigDecimal differenceAmt = BigDecimal.ZERO;

    protected LinkedHashMap<Integer, LinkedHashMap<String, Object>> values = null;
    protected LinkedHashMap<Integer, MBankStatement> baskStatements = null;
    protected List<MPayment> records = null;

    @Override
    protected void prepare() {
        setColumnsValues();
    }

    protected String doIt() throws Exception {
        if (getBankAccountId() <= 0)
            throw new AdempiereException("@C_BankAccount_ID@ @NotFound@");
        if (getTransactionDate() == null || getTransactionDateTo() == null )
            throw new AdempiereException("@DateTrx@ @NotFound@");

        if (differenceAmt.signum() != 0 && !isOverUnderPayment())
           return Msg.parseTranslation(getCtx() , "@C_BankStatement_ID@ @NotApproved@ @NotMatch@");
        else if (isOverUnderPayment())
        {
            if (getChargeId() <=0)
                throw new AdempiereException("@C_Charge_ID@ @NotFound@");
            // Generate Lost or Profit
            generateLostOrProfit();
        }

        // Close Bank Statement
        closeBankStatements();
        return "@Ok@";
    }

    private void closeBankStatements() {
        for (Map.Entry <Integer , MBankStatement> entry: getBankStatements().entrySet())
        {
            MBankStatement bankStatement =  entry.getValue();
            bankStatement.processIt(DocAction.ACTION_Complete);
            bankStatement.saveEx();
        }
    }

    private void generateLostOrProfit() {

        MBankStatement bankStatement = (MBankStatement) getBankStatements().entrySet().iterator().next();
        MBankStatementLine bankStatementLine = new MBankStatementLine(bankStatement);
        bankStatementLine.setDateAcct(getTransactionDate());
        bankStatementLine.setStatementLineDate(getTransactionDateTo());
        bankStatementLine.setStmtAmt(differenceAmt);
        bankStatementLine.setC_Charge_ID(getChargeId());
        bankStatementLine.setChargeAmt(differenceAmt);
        bankStatementLine.saveEx();
    }

    private LinkedHashMap<Integer, MBankStatement> getBankStatements()
    {
        if (baskStatements != null && baskStatements.size() > 0)
            return baskStatements;

        baskStatements = new LinkedHashMap<Integer, MBankStatement>();
        for (MPayment payment : getRecords()) {
            Integer bankStatementId = (Integer) getBrowseRowValue("bsl", "C_BankStatementLine_ID", payment.get_ID());
            if (bankStatementId != null && bankStatementId > 0)
            {
                MBankStatement bankStatement = new MBankStatement(getCtx(),bankStatementId , get_TrxName());
                if (!baskStatements.containsKey(bankStatementId))
                    baskStatements.put(bankStatementId , bankStatement);
            }
        }
        return baskStatements;
    }

    private List<MPayment> getRecords() {
        if (records != null)
            return records;

        String whereClause = "EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE  T_Selection.AD_PInstance_ID=? AND T_Selection.T_Selection_ID=C_BankStatementLine.C_BankStatementLine_ID)";
        records = new Query(getCtx(), MBankStatementLine.Table_Name, whereClause,
                get_TrxName()).setClient_ID()
                .setParameters(getAD_PInstance_ID())
                .list();
        return records;
    }

    private Object getBrowseRowValue(String alias, String columnName,
                                     int recordId) {

        LinkedHashMap<String, Object> valuesSave = values.get(recordId);

        for (Map.Entry<String, Object> entry : valuesSave.entrySet()) {
            if (entry.getKey().contains(alias.toUpperCase() + "_" + columnName))
                return entry.getValue();
        }
        return null;
    }

    private LinkedHashMap<Integer, LinkedHashMap<String, Object>> setColumnsValues() {
        if (values != null)
            return values;

        values = new LinkedHashMap<Integer, LinkedHashMap<String, Object>>();

        for (MPayment record : getRecords()) {
            values.put(
                    record.get_ID(),
                    Browser.getBrowseValues(getAD_PInstance_ID(), null,
                            record.get_ID(), null));
        }
        return values;
    }
}
