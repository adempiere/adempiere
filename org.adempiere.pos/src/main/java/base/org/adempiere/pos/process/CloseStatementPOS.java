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
import org.compiere.process.DocAction;
import org.compiere.util.Msg;

import java.util.LinkedHashMap;
import java.util.List;


/**
 * Close Statement for POS
 * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 04/15/16.
 */
public class CloseStatementPOS extends CloseStatementPOSAbstract {

    protected LinkedHashMap<Integer, MBankStatement> baskStatements = null;

    @Override
    protected void prepare() {
        super.prepare();
    }

    protected String doIt() throws Exception {
        if (getBankAccountId() <= 0)
            throw new AdempiereException("@C_BankAccount_ID@ @NotFound@");
        if (getTransactionDate() == null || getTransactionDateTo() == null )
            throw new AdempiereException("@DateTrx@ @NotFound@");

        if (getDifference().signum() != 0 && !isOverUnderPayment())
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
        getBankStatements().entrySet().stream().forEach( entry -> {
            MBankStatement bankStatement =  entry.getValue();
            bankStatement.processIt(DocAction.ACTION_Complete);
            bankStatement.saveEx();
        });
    }

    private void generateLostOrProfit() {
        MBankStatement bankStatement = (MBankStatement) getBankStatements().entrySet().iterator().next();
        MBankStatementLine bankStatementLine = new MBankStatementLine(bankStatement);
        bankStatementLine.setDateAcct(getTransactionDate());
        bankStatementLine.setStatementLineDate(getTransactionDateTo());
        bankStatementLine.setStmtAmt(getDifference());
        bankStatementLine.setC_Charge_ID(getChargeId());
        bankStatementLine.setChargeAmt(getDifference());
        bankStatementLine.saveEx();
    }

    private LinkedHashMap<Integer, MBankStatement> getBankStatements()
    {
        if (baskStatements != null && baskStatements.size() > 0)
            return baskStatements;

        baskStatements = new LinkedHashMap<Integer, MBankStatement>();
        List<MPayment> payments = (List<MPayment>) getInstancesForSelection(get_TrxName());
        payments.stream().forEach( payment -> {
            Integer bankStatementLineId = getSelectionAsInt(payment.get_ID() , "BSL_C_BankStatementLine_ID");
            if (bankStatementLineId != null && bankStatementLineId > 0)
            {
                MBankStatementLine bankStatementLine = new MBankStatementLine(getCtx() , bankStatementLineId ,  get_TrxName());
                MBankStatement bankStatement = bankStatementLine.getParent();
                if (!baskStatements.containsKey(bankStatement.get_ID()))
                    baskStatements.put(bankStatement.get_ID() , bankStatement);
            }
        });
        return baskStatements;
    }
}
