/**
 * Copyright (C) 2003-2019, e-Evolution Consultants S.A. , http://www.e-evolution.com
 * This program is free software, you can redistribute it and/or modify it
 * under the terms version 2 of the GNU General Public License as published
 * or (at your option) any later version.
 * by the Free Software Foundation. This program is distributed in the hope
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along
 * with this program, if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
 * For the text or an alternative of this public license, you may reach us
 * or via info@adempiere.net or http://www.adempiere.net/license.html
 * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 * Created by victor.perez@e-evolution.com , www.e-evolution.com
 */

package org.adempiere.process;

import org.adempiere.core.domains.models.I_C_DocType;
import org.adempiere.core.domains.models.I_C_ElementValue;
import org.adempiere.core.domains.models.I_Fact_Acct;
import org.adempiere.core.domains.models.I_T_InvoiceGL;
import org.adempiere.core.domains.models.X_C_ElementValue;
import org.adempiere.core.domains.models.X_T_InvoiceGL;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.*;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.Trx;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;


import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toList;


/**
 * Generate Not Realized Gain / Loss for Multi-Currency Account
 * This process takes the accounting records until a closing date for all multi-currency accounting accounts of the balance
 * in the source currency of the transaction is converted to the currency of the accounting scheme at the exchange rate of the closing date,
 * the difference with the current balance balance in currency records a journal for the difference as loss or gain in exchange
 *
 * @author victor.perez@e-evolution.com , www.e-evolution.com
 */
public class GenerateNotRealizedGainLoss extends GenerateNotRealizedGainLossAbstract {
    private List<X_T_InvoiceGL> exchangeGainLossList = new ArrayList<>();
    private HashMap<Integer, MJournalBatch> journalBatches = new HashMap<>();
    private HashMap<String, MJournal> journals = new HashMap<>();

    @Override
    protected void prepare() {
        super.prepare();
    }

    @Override
    protected String doIt() {
        cleanTemporaryTable();
        getAccountsForeignCurrency()
                .forEach(accountId -> getAccountingSchemes().stream()
                        .forEach(accountingSchema -> getAccountBalanceByCurrency(accountingSchema.get_ID(), accountId).stream()
                                .forEach(balanceCurrency -> getAccountRecordsByCurrency(accountingSchema, accountId, balanceCurrency.getKey()).stream()
                                        .forEach(factAcctId -> Trx.run(trxName -> createExchangeGainLossReport(accountingSchema, factAcctId, trxName))
                                        )
                                )
                        )
                );

        if ("Y".equals(getIsCreateNewJournal())) {
            if (getDocTypeRevalId() <= 0)
                throw new AdempiereException("@C_ConversionTypeReval_ID@ @NotFound@");

            Trx.run(trxName -> exchangeGainLoss(trxName));
        }

        return "@Ok@";
    }

    // Define Account Balance
    class AccountBalance {
        Integer companyId;
        Integer accountSchemaId;
        Integer organizationId;
        Integer accountId;
        Integer currencyId;
        BigDecimal sourceBalance;
        BigDecimal accountBalance;
        BigDecimal debit;
        BigDecimal credit;
    }

    /**
     * Create Journal Batch
     *
     * @param trxName
     * @return
     */
    private void exchangeGainLoss(String trxName) {
        //grouping by Account id and grouping by Currency and create a Journal for each Currency with Exchange Gain Loss
        AtomicInteger lineNo = new AtomicInteger(10);
        exchangeGainLossList.stream()
                .collect(
                        groupingBy(X_T_InvoiceGL::getC_AcctSchema_ID,
                                groupingBy(X_T_InvoiceGL::getAD_Org_ID,
                                        groupingBy(X_T_InvoiceGL::getAccount_ID,
                                                groupingBy(X_T_InvoiceGL::getC_Currency_ID))))).entrySet().stream()
                .forEach(organizationEntry -> organizationEntry.getValue().entrySet().stream()
                        .forEach(accountSchemeEntry -> accountSchemeEntry.getValue().entrySet().stream()
                                .forEach(accountEntry -> accountEntry.getValue()
                                        .entrySet().stream().map(currencyEntry -> {
                                            BigDecimal sourceAmountBalance = currencyEntry.getValue().stream()
                                                    .collect(reducing(BigDecimal.ZERO, I_T_InvoiceGL::getAmtSourceBalance, BigDecimal::add));
                                            BigDecimal accountAmountBalance = currencyEntry.getValue().stream()
                                                    .collect(reducing(BigDecimal.ZERO, I_T_InvoiceGL::getAmtAcctBalance, BigDecimal::add));

                                            BigDecimal debit = currencyEntry.getValue().stream()
                                                    .collect(reducing(BigDecimal.ZERO, I_T_InvoiceGL::getAmtRevalDrDiff, BigDecimal::add));
                                            BigDecimal credit = currencyEntry.getValue().stream()
                                                    .collect(reducing(BigDecimal.ZERO, I_T_InvoiceGL::getAmtRevalCrDiff, BigDecimal::add));
                                            AccountBalance accountBalance = new AccountBalance();
                                            accountBalance.companyId = getAD_Client_ID();
                                            accountBalance.organizationId = organizationEntry.getKey();
                                            accountBalance.accountSchemaId = accountEntry.getKey();
                                            accountBalance.accountId = accountEntry.getKey();
                                            accountBalance.currencyId = currencyEntry.getKey();
                                            accountBalance.sourceBalance = sourceAmountBalance;
                                            accountBalance.accountBalance = accountAmountBalance;
                                            accountBalance.debit = debit;
                                            accountBalance.credit = credit;
                                            return accountBalance;
                                        }).collect(toList()).stream()
                                        .filter(accountBalance -> (accountBalance.debit.subtract(accountBalance.credit).signum() != 0))
                                        .forEach(accountBalance -> {
                                            MAcctSchema accountingSchema = MAcctSchema.get(getCtx(), accountSchemeEntry.getKey());
                                            MCurrency currency = MCurrency.get(Env.getCtx(), accountBalance.currencyId);
                                            MElementValue account = new MElementValue(getCtx(), accountBalance.accountId, trxName);
                                            MJournalBatch journalBatch = createJournalBatch(accountingSchema, trxName);
                                            MJournal journal = createJournal(journalBatch, accountingSchema, currency, accountBalance.organizationId);
                                            BigDecimal rate = MConversionRate.getRate(
                                                    accountBalance.currencyId,
                                                    accountingSchema.getC_Currency_ID(),
                                                    getDateReval(),
                                                    getConversionTypeRevalId(),
                                                    accountBalance.companyId,
                                                    accountBalance.organizationId);
                                            createJournalLine(journal, accountingSchema, account, accountBalance, currency, rate, lineNo);
                                        })
                                )
                        )
                );
    }

    /**
     * Create Journal Batch
     *
     * @param accountingSchema
     * @param trxName
     * @return
     */
    private MJournalBatch createJournalBatch(MAcctSchema accountingSchema, String trxName) {
        if (journalBatches.containsKey(accountingSchema.get_ID()))
            return journalBatches.get(accountingSchema.get_ID());

        Timestamp today = new Timestamp(System.currentTimeMillis());
        MJournalBatch journalBatch = new MJournalBatch(getCtx(), 0, trxName);
        StringBuilder journalBatchDescription = new StringBuilder();
        Optional.ofNullable(getBatchDescription()).ifPresent(batchDescription -> journalBatchDescription.append(batchDescription).append(" "));
        journalBatchDescription.append(getName()).append(" @DateAcct@ ").append(getDateReval());
        journalBatch.setDateAcct(getDateReval());
        journalBatch.setDateDoc(getDateReval());
        journalBatch.setDescription(Msg.parseTranslation(getCtx(), journalBatchDescription.toString()));
        journalBatch.setC_DocType_ID(getDocTypeRevalId());
        journalBatch.setDateDoc(today);
        journalBatch.setDateAcct(getDateReval());
        journalBatch.setC_Currency_ID(accountingSchema.getC_Currency_ID());
        journalBatch.saveEx();
        journalBatches.put(accountingSchema.get_ID(), journalBatch);
        return journalBatch;
    }

    /**
     * Create Journal
     *
     * @param journalBatch
     * @param accountingSchema
     * @param currency
     * @param organizationId
     * @return
     */
    private MJournal createJournal(MJournalBatch journalBatch, MAcctSchema accountingSchema, MCurrency currency, Integer organizationId) {
        String key = organizationId + "+" + currency.get_ID();
        if (journals.containsKey(key))
            return journals.get(key);

        I_C_DocType documentType = MDocType.get(getCtx(), getDocTypeRevalId());
        Integer glCategoryId = Optional.ofNullable(MGLCategory.getDefaultSystem(getCtx()).get_ID())
                .orElseGet(() -> documentType.getGL_Category_ID());
        MJournal journal = new MJournal(journalBatch);
        journal.setDateAcct(getDateReval());
        journal.setDateDoc(getDateReval());
        journal.setC_AcctSchema_ID(accountingSchema.get_ID());
        journal.setAD_Org_ID(organizationId);
        journal.setC_Currency_ID(accountingSchema.getC_Currency_ID());
        journal.setC_ConversionType_ID(getConversionTypeRevalId());
        journal.setGL_Category_ID(glCategoryId);
        journal.setGL_JournalBatch_ID(journalBatch.getGL_JournalBatch_ID());
        StringBuilder journalDescription = new StringBuilder();
        journalDescription.append("@C_AcctSchema_ID@ ").append(accountingSchema.getName()).append(" @C_Currency_ID@ ")
                .append(currency.getISO_Code());
        journal.setDescription(Msg.parseTranslation(Env.getCtx(), journalDescription.toString()));
        journal.saveEx();
        journals.put(key, journal);
        return journal;
    }

    /**
     * Create Journal Line
     *
     * @param journal
     * @param accountingSchema
     * @param account
     * @param accountBalance
     * @param currency
     * @param rate
     * @param lineNo
     */
    private void createJournalLine(
            MJournal journal,
            MAcctSchema accountingSchema,
            MElementValue account,
            AccountBalance accountBalance,
            MCurrency currency,
            BigDecimal rate,
            AtomicInteger lineNo) {
        MJournalLine journalLine = new MJournalLine(journal);
        journalLine.setLine(lineNo.getAndUpdate(no -> no + 10));
        journalLine.setAccount_ID(account.getC_ElementValue_ID());
        StringBuilder journalDescriptionLine = new StringBuilder();
        journalDescriptionLine.append("@Account_ID@ ").append(account.getName())
                .append(" @C_Currency_ID@ ").append(currency.getISO_Code()).append(" ")
                .append(accountBalance.sourceBalance)
                .append(" @C_Currency_ID@ ").append(accountingSchema.getC_Currency().getISO_Code())
                .append(" ").append(accountBalance.accountBalance)
                .append(" @C_Conversion_Rate_ID@ ").append(Optional.of(rate.toString()).orElseGet(() -> " @NotFound@ "));
        if (accountBalance.debit.compareTo(accountBalance.credit) > 0) {
            BigDecimal exchangeGain = accountBalance.debit.subtract(accountBalance.credit);
            journalLine.setAmtSourceDr(exchangeGain.abs());
            journalLine.setAmtAcctDr(exchangeGain.abs());
            journalLine.setAmtSourceCr(BigDecimal.ZERO);
            journalLine.setAmtAcctCr(BigDecimal.ZERO);
            journalDescriptionLine.append(" @UnrealizedGain_Acct@ ").append(exchangeGain.abs().toString());
        } else if (accountBalance.credit.compareTo(accountBalance.debit) > 0) {
            BigDecimal exchangeLoss = accountBalance.credit.subtract(accountBalance.debit);
            journalLine.setAmtSourceDr(BigDecimal.ZERO);
            journalLine.setAmtAcctDr(BigDecimal.ZERO);
            journalLine.setAmtSourceCr(exchangeLoss.abs());
            journalLine.setAmtAcctCr(exchangeLoss.abs());
            journalDescriptionLine.append(" @UnrealizedLoss_Acct@ ").append(exchangeLoss.abs().toString());
        }
        journalLine.setDescription(Msg.parseTranslation(getCtx(), journalDescriptionLine.toString()));
        journalLine.saveEx();
        MAcctSchemaDefault accountShemaDefault = Optional
                .ofNullable(MAcctSchemaDefault.get(getCtx(), accountingSchema.getC_AcctSchema_ID()))
                .orElseThrow(() -> new AdempiereException("@C_AcctSchema_Default@ @NotFound@"));
        createExchangeGainLoss(accountShemaDefault, journal, journalLine, lineNo);
    }

    /**
     * Create Journal Line for Exchange Gain Loss
     *
     * @param accountSchemaDefault
     * @param journal
     * @param journalLine
     * @param lineNo
     */
    private void createExchangeGainLoss(
            MAcctSchemaDefault accountSchemaDefault,
            MJournal journal,
            MJournalLine journalLine,
            AtomicInteger lineNo) {
        if (journalLine.getAmtAcctDr().compareTo(journalLine.getAmtAcctCr()) > 0) {
            MJournalLine exchangeGain = new MJournalLine(journal);
            exchangeGain.setLine(lineNo.getAndUpdate(no -> no + 10));
            exchangeGain.setDescription(Msg.getElement(getCtx(), "UnrealizedGain_Acct"));
            exchangeGain.setC_ValidCombination_ID(getUnrealizedGainLoss(accountSchemaDefault.getUnrealizedGain_Acct(), journalLine));
            BigDecimal exchangeGainAmount = journalLine.getAmtAcctDr().subtract(journalLine.getAmtAcctCr());
            exchangeGain.setAmtSourceCr(exchangeGainAmount.abs());
            exchangeGain.setAmtAcctCr(exchangeGainAmount.abs());
            exchangeGain.saveEx();
        } else if (journalLine.getAmtAcctCr().compareTo(journalLine.getAmtAcctDr()) > 0) {
            MJournalLine exchangeLoss = new MJournalLine(journal);
            exchangeLoss.setLine(lineNo.getAndUpdate(no -> no + 10));
            exchangeLoss.setDescription(Msg.getElement(getCtx(), "UnrealizedLoss_Acct"));
            exchangeLoss.setC_ValidCombination_ID(getUnrealizedGainLoss(accountSchemaDefault.getRealizedLoss_Acct(), journalLine));
            BigDecimal exchangeLossAmount = journalLine.getAmtAcctDr().subtract(journalLine.getAmtAcctCr());
            exchangeLoss.setAmtSourceDr(exchangeLossAmount.abs());
            exchangeLoss.setAmtAcctDr(exchangeLossAmount.abs());
            exchangeLoss.saveEx();
        }
    }

    /**
     * Create Exchange Gain or Loss Report
     *
     * @param acctSchema
     * @param factAcctId
     * @param trxName
     */
    private void createExchangeGainLossReport(MAcctSchema acctSchema, Integer factAcctId, String trxName) {
        MFactAcct factAcct = new MFactAcct(getCtx(), factAcctId, trxName);
        if (factAcct.getAmtAcctDr().subtract(factAcct.getAmtAcctCr()).signum() == 0)
            return;

        X_T_InvoiceGL exchangeGainLoss = new X_T_InvoiceGL(getCtx(), 0, trxName);
        exchangeGainLoss.setAD_PInstance_ID(getAD_PInstance_ID());
        exchangeGainLoss.setAD_Org_ID(factAcct.getAD_Org_ID());
        exchangeGainLoss.setC_AcctSchema_ID(acctSchema.get_ID());
        exchangeGainLoss.setDateReval(getDateReval());
        exchangeGainLoss.setC_ConversionTypeReval_ID(getConversionTypeRevalId());
        exchangeGainLoss.setC_DocTypeReval_ID(getDocTypeRevalId());
        exchangeGainLoss.setAccount_ID(factAcct.getAccount_ID());
        exchangeGainLoss.setC_Currency_ID(factAcct.getC_Currency_ID());
        exchangeGainLoss.setFact_Acct_ID(factAcct.getFact_Acct_ID());
        exchangeGainLoss.setAPAR(X_T_InvoiceGL.APAR_ReceivablesPayables);
        exchangeGainLoss.setAD_Table_ID(factAcct.getAD_Table_ID());
        exchangeGainLoss.setRecord_ID(factAcct.getRecord_ID());
        exchangeGainLoss.setAmtSourceBalance(factAcct.getAmtSourceDr().subtract(factAcct.getAmtSourceCr()));
        exchangeGainLoss.setAmtAcctBalance(factAcct.getAmtAcctDr().subtract(factAcct.getAmtAcctCr()));
        Optional<BigDecimal> debitRevaluation = Optional.empty();
        Optional<BigDecimal> creditRevaluation = Optional.empty();
        if (factAcct.getAmtSourceDr().signum() != 0) {
            debitRevaluation = Optional.ofNullable(MConversionRate.convert(getCtx(),
                    factAcct.getAmtSourceDr(),
                    factAcct.getC_Currency_ID(),
                    acctSchema.getC_Currency_ID(),
                    getDateReval(),
                    getConversionTypeRevalId(),
                    getAD_Client_ID(),
                    factAcct.getAD_Org_ID()));
        }
        if (factAcct.getAmtSourceCr().signum() != 0) {
            creditRevaluation = Optional.ofNullable(MConversionRate.convert(
                    getCtx(),
                    factAcct.getAmtSourceCr(),
                    factAcct.getC_Currency_ID(),
                    acctSchema.getC_Currency_ID(),
                    getDateReval(),
                    getConversionTypeRevalId(),
                    getAD_Client_ID(),
                    factAcct.getAD_Org_ID()));
        }
        if (debitRevaluation.orElseGet(() -> BigDecimal.ZERO).signum() == 0 && creditRevaluation.orElseGet(() -> BigDecimal.ZERO).signum() == 0) {
            StringBuilder errorMassage = new StringBuilder();
            MConversionType conversionType = new MConversionType(getCtx(), getConversionTypeRevalId(), trxName);
            errorMassage
                    .append(" @C_ConversionTypeReval_ID@ ").append(conversionType.getName())
                    .append(" @C_Conversion_Rate_ID@ @From@ @C_Currency_ID@ ")
                    .append(factAcct.getC_Currency().getISO_Code())
                    .append(" @to@ @C_Currency_ID@ ")
                    .append(acctSchema.getC_Currency().getISO_Code())
                    .append(" @DateReval@ ").append(getDateReval())
                    .append(" @NotFound@");
            throw new AdempiereException(errorMassage.toString());
        }

        exchangeGainLoss.setAmtRevalDr(debitRevaluation.orElseGet(() -> BigDecimal.ZERO));
        exchangeGainLoss.setAmtRevalCr(creditRevaluation.orElseGet(() -> BigDecimal.ZERO));
        exchangeGainLoss.setAmtRevalDrDiff(debitRevaluation.orElseGet(() -> BigDecimal.ZERO).subtract(factAcct.getAmtAcctDr()));
        exchangeGainLoss.setAmtRevalCrDiff(creditRevaluation.orElseGet(() -> BigDecimal.ZERO).subtract(factAcct.getAmtAcctCr()));
        exchangeGainLoss.saveEx();
        exchangeGainLossList.add(exchangeGainLoss);
    }

    /**
     * get Unrealized Gain or Loss
     *
     * @param validCombinationId
     * @param journalLine
     * @return
     */
    private MAccount getUnrealizedGainLoss(Integer validCombinationId, MJournalLine journalLine) {
        MAccount unrealizedGainLossBase = MAccount.getValidCombination(getCtx(), validCombinationId, journalLine.get_TrxName());
        return MAccount.get(getCtx(),
                journalLine.getAD_Client_ID(),
                journalLine.getAD_Org_ID(),
                unrealizedGainLossBase.getC_AcctSchema_ID(),
                unrealizedGainLossBase.getAccount_ID(),
                unrealizedGainLossBase.getC_SubAcct_ID(),
                unrealizedGainLossBase.getM_Product_ID(),
                unrealizedGainLossBase.getC_BPartner_ID(),
                unrealizedGainLossBase.getAD_OrgTrx_ID(),
                unrealizedGainLossBase.getC_LocFrom_ID(),
                unrealizedGainLossBase.getC_LocTo_ID(),
                unrealizedGainLossBase.getC_SalesRegion_ID(),
                unrealizedGainLossBase.getC_Project_ID(),
                unrealizedGainLossBase.getC_Campaign_ID(),
                unrealizedGainLossBase.getC_Activity_ID(),
                unrealizedGainLossBase.getUser1_ID(),
                unrealizedGainLossBase.getUser2_ID(),
                unrealizedGainLossBase.getUser3_ID(),
                unrealizedGainLossBase.getUser4_ID(),
                unrealizedGainLossBase.getUserElement1_ID(),
                unrealizedGainLossBase.getUserElement2_ID(),
                unrealizedGainLossBase.get_TrxName());
    }

    /**
     * get Accounting Schemes
     *
     * @return
     */
    private List<MAcctSchema> getAccountingSchemes() {
        List<MAcctSchema> accountingSchemes = new ArrayList<>();
        if (getAcctSchemaId() > 0)
            accountingSchemes.add(MAcctSchema.get(getCtx(), getAcctSchemaId()));
        else
            accountingSchemes = Arrays.asList(MAcctSchema.getClientAcctSchema(getCtx(), getAD_Client_ID()));

        return accountingSchemes.stream().collect(toList());
    }

    /**
     * Get Accounts Foreign Currency
     *
     * @return
     */
    private List<Integer> getAccountsForeignCurrency() {
        List<Object> parameters = new ArrayList<>();
        StringBuilder whereClause = new StringBuilder();
        whereClause.append(I_C_ElementValue.COLUMNNAME_IsForeignCurrency).append("=? ")
                .append(" AND ").append(I_C_ElementValue.COLUMNNAME_AccountType)
                .append(" IN('").append(X_C_ElementValue.ACCOUNTTYPE_Asset)
                .append("','").append(X_C_ElementValue.ACCOUNTTYPE_Liability)
                .append("','").append(X_C_ElementValue.ACCOUNTTYPE_OwnerSEquity).append("')");
        parameters.add("Y");
        int ids[] = new Query(getCtx(), I_C_ElementValue.Table_Name, whereClause.toString(), get_TrxName())
                .setParameters(parameters)
                .setOrderBy(I_C_ElementValue.COLUMNNAME_C_ElementValue_ID)
                .getIDs();
        List<Integer> accountsIds = Arrays.stream(ids).boxed().collect(toList());
        return accountsIds;
    }

    /**
     * get Key Name Pair Account Balance By Currency
     *
     * @param accountSchemaId
     * @param accountId
     * @return
     */
    private List<KeyNamePair> getAccountBalanceByCurrency(Integer accountSchemaId, Integer accountId) {
        List<Object> parameters = new ArrayList<>();
        StringBuilder sql = new StringBuilder(" SELECT C_Currency_ID , SUM ");
        sql.append("(acctbalance(" + accountId + ", AmtSourceDr , 0 ) - acctbalance(" + accountId + ", 0 , AmtSourceCr)) AS Balance FROM Fact_Acct WHERE ");
        sql.append(I_Fact_Acct.COLUMNNAME_C_AcctSchema_ID).append("=? ");
        parameters.add(accountSchemaId);
        sql.append(" AND ").append(I_Fact_Acct.COLUMNNAME_Account_ID).append("=?");
        parameters.add(accountId);
        sql.append(" AND ").append(I_Fact_Acct.COLUMNNAME_DateAcct).append("<=?");
        parameters.add(getDateReval());
        sql.append(" GROUP BY Account_ID , C_Currency_ID ");
        List<KeyNamePair> balances = Arrays.stream(DB.getKeyNamePairs(get_TrxName(), sql.toString(), false, parameters.toArray()))
                .filter(balanceCurrency -> balanceCurrency.getName() != null && (new BigDecimal(balanceCurrency.getName()).signum() != 0))
                .collect(toList());
        return balances;
    }

    /**
     * Get Account Records By Currency
     *
     * @param accountingSchema
     * @param accountId
     * @param currencyId
     * @return
     */
    private List<Integer> getAccountRecordsByCurrency(MAcctSchema accountingSchema, Integer accountId, Integer currencyId) {
        List<Object> parameters = new ArrayList<>();
        StringBuilder whereClause = new StringBuilder();
        whereClause.append(I_Fact_Acct.COLUMNNAME_C_AcctSchema_ID).append("=? ");
        parameters.add(accountingSchema.get_ID());
        whereClause.append(" AND ").append(I_Fact_Acct.COLUMNNAME_Account_ID).append("=?");
        parameters.add(accountId);
        whereClause.append(" AND ").append(I_Fact_Acct.COLUMNNAME_C_Currency_ID).append("=?");
        parameters.add(currencyId);
        whereClause.append(" AND ").append(I_Fact_Acct.COLUMNNAME_C_Currency_ID).append("<>?");
        parameters.add(accountingSchema.getC_Currency_ID());
        whereClause.append(" AND ").append(I_Fact_Acct.COLUMNNAME_DateAcct).append("<=?");
        parameters.add(getDateReval());
        int[] ids = new Query(getCtx(), I_Fact_Acct.Table_Name, whereClause.toString(), get_TrxName())
                .setParameters(parameters)
                .setOrderBy(I_Fact_Acct.COLUMNNAME_Account_ID + "," + I_Fact_Acct.COLUMNNAME_C_Currency_ID)
                .getIDs();
        List<Integer> factAccountIds = Arrays.stream(ids).boxed().collect(toList());
        return factAccountIds;
    }

    /**
     * Clean Temporary Table
     */
    private void cleanTemporaryTable() {
        Trx.run(trxName -> {
            StringBuilder deleteStatement = new StringBuilder("DELETE FROM ");
            deleteStatement.append(I_T_InvoiceGL.Table_Name).append(" WHERE ")
                    .append(I_T_InvoiceGL.COLUMNNAME_AD_PInstance_ID).append("=?");
            DB.executeUpdate(deleteStatement.toString(), getAD_PInstance_ID(), trxName);
        });
    }
}