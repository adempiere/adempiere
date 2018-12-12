/**
 * Copyright (C) 2003-2017, e-Evolution Consultants S.A. , http://www.e-evolution.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 * Created by victor.perez@e-evolution.com , www.e-evolution.com
 */

package org.compiere.process;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_AD_Column;
import org.compiere.model.I_I_Budget;
import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MAcctSchemaElement;
import org.compiere.model.MActivity;
import org.compiere.model.MBPartner;
import org.compiere.model.MCampaign;
import org.compiere.model.MColumn;
import org.compiere.model.MElementValue;
import org.compiere.model.MJournal;
import org.compiere.model.MJournalBatch;
import org.compiere.model.MJournalLine;
import org.compiere.model.MOrg;
import org.compiere.model.MPeriod;
import org.compiere.model.MProduct;
import org.compiere.model.MProject;
import org.compiere.model.MSalesRegion;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.model.X_A_Asset;
import org.compiere.model.X_C_SubAcct;
import org.compiere.model.X_GL_Budget;
import org.compiere.model.X_I_Budget;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Import Budget
 * @author Nikunj Panelia
 * @author victor.perez@e-evolution.com , e-Evolution Consultant , wwww.e-evolution.com
 */
public class ImportBudget extends ImportBudgetAbstract {

    List<MPeriod> glPeriods = new ArrayList<>();
    List<Timestamp> glPeriodsDates = new ArrayList<>();

    protected void prepare() {
        super.prepare();
    }

    /**
     * Perform process.
     * @return Message
     * @throws Exception
     */
    protected String doIt() throws Exception {
        // Validate Budget
        X_I_Budget importBudgetCurrent = new X_I_Budget(getCtx(), getRecord_ID(), null);
        int budgetId = importBudgetCurrent.getGL_Budget_ID();
        if (budgetId <=  0)
            budgetId = getId(X_GL_Budget.Table_Name , X_GL_Budget.COLUMNNAME_Name + "=?", get_TrxName() , importBudgetCurrent.getBudgetCode());
        if (budgetId <= 0)
            throw new AdempiereException("@GL_Budget_ID@ @NotFound@");
        else {
            importBudgetCurrent.setGL_Budget_ID(budgetId);
            importBudgetCurrent.saveEx();
        }

        MAcctSchema acctSchema = MAcctSchema.get(getCtx(), getAccountingSchemaId());
        if (acctSchema == null || acctSchema.getC_AcctSchema_ID() == 0)
            throw new AdempiereException("@C_AcctSchema_ID@ @NotFound@");
        MPeriod period = MPeriod.get(getCtx(), acctSchema.getC_Period_ID());
        if (period == null)
            throw new AdempiereException("@C_Period_ID@ @NotFound@");

        MAcctSchemaElement[] acctSchemaElements = MAcctSchemaElement.getAcctSchemaElements(acctSchema);


        int glCategoryId = DB.getSQLValue(get_TrxName(), "SELECT GL_Category_ID FROM GL_Category WHERE Name = 'Manual'  AND AD_Client_ID =? ", Env.getAD_Client_ID(getCtx()));
        int currencyId = acctSchema.getC_Currency_ID();
        int docTypeId = DB.getSQLValue(get_TrxName(), "SELECT C_DocType_ID FROM C_DocType WHERE Name = 'GL Journal' AND AD_Client_ID =?", Env.getAD_Client_ID(getCtx()));
        int conversionTypeId = DB.getSQLValue(get_TrxName(), "SELECT C_ConversionType_ID FROM C_ConversionType WHERE Value =? ", "S");


        String documentNo = importBudgetCurrent.getBatchDocumentNo();
        int calendarId = period.getC_Calendar_ID();

        if (isDeleteoldimportedrecords())
            Arrays.stream(getBudget(documentNo, true, true)).forEach(importBudgetId -> {
                X_I_Budget importBudget = new X_I_Budget(getCtx(), importBudgetId, null);
                importBudget.deleteEx(true);
            });

        //Validation document
        int[] budgetLinesIds = getBudget(documentNo, false, false);
        validateBudget(budgetLinesIds);

        Arrays.stream(budgetLinesIds).forEach(importBudgetId -> {
            Trx.run(trxName -> {
                        X_I_Budget importBudget = new X_I_Budget(getCtx(), importBudgetId, trxName);
                        importBudget.setI_ErrorMsg(null);
                        fillIdValues(importBudget, acctSchemaElements , trxName);
                    }
            );
        });
        // Validate  have not Error
        Arrays.stream(budgetLinesIds).forEach(importBudgetId -> {
                        X_I_Budget importBudget = new X_I_Budget(getCtx(), importBudgetId, null);
                        if (importBudget.getI_ErrorMsg() != null)
                            throw new AdempiereException("@GL_BudgetID@ @ProcessFailed@");

        });

        Trx.run(trxName -> generateJournalBatch(trxName , documentNo, glCategoryId, docTypeId, currencyId, conversionTypeId, calendarId, importBudgetCurrent.getGL_Budget_ID()));
        return "@Ok@";
    }

    /**
     * Fill Id Values
     * @param importBudget
     * @param acctSchemaElements
     * @param trxName
     */
    private void fillIdValues(X_I_Budget importBudget,  MAcctSchemaElement[] acctSchemaElements , String trxName) {

        StringBuffer stringError = new StringBuffer("");
        // Set Organization
        Integer orgId = 0;
        if (importBudget.getAD_Org_ID() > 0)
            orgId = importBudget.getAD_Org_ID();
        if (orgId <= 0 && importBudget.getOrgValue() != null)
            orgId = getId(MOrg.Table_Name, MOrg.COLUMNNAME_Value + "=?", trxName, importBudget.getOrgValue());
        if (orgId > 0 && importBudget.getAD_Org_ID() <= 0)
            importBudget.setAD_Org_ID(orgId);
        if (importBudget.getAD_Org_ID() <= 0 && importBudget.getOrgValue() != null)
            stringError.append("@AD_Org_ID@ @NotFound@ ");


        // Set Organization Trx
        Integer orgTrxId = 0;
        if (importBudget.getAD_OrgTrx_ID() > 0)
            orgTrxId = importBudget.getAD_OrgTrx_ID();
        if (orgTrxId <= 0 && importBudget.getOrgTrxValue() != null)
            orgTrxId = getId(MOrg.Table_Name, MOrg.COLUMNNAME_Value + "=?", trxName, importBudget.getOrgTrxValue());
        if (orgTrxId > 0 && importBudget.getAD_OrgTrx_ID() <= 0)
            importBudget.setAD_OrgTrx_ID(orgTrxId);
       if (importBudget.getAD_OrgTrx_ID() <= 0 && importBudget.getOrgTrxValue() != null)
            stringError.append("@AD_OrgTrx_ID@ @NotFound@ ");

        // Set Account Schema
        Integer accountSchemaId = 0;
        if (importBudget.getC_AcctSchema_ID() > 0)
            accountSchemaId = importBudget.getC_AcctSchema_ID();
        if (accountSchemaId <= 0 && importBudget.getAcctSchemaName() != null)
            accountSchemaId = getId(MAcctSchema.Table_Name, MAcctSchema.COLUMNNAME_Name + "=?", trxName, importBudget.getAcctSchemaName());
        if (accountSchemaId > 0 && importBudget.getC_AcctSchema_ID() <= 0)
            importBudget.setC_AcctSchema_ID(accountSchemaId);
        else {
            accountSchemaId = getAccountingSchemaId();
            importBudget.setC_AcctSchema_ID(accountSchemaId);
        }

        // Set Budget
        Integer budgetId = 0;
        if (importBudget.getGL_Budget_ID() > 0)
            budgetId = importBudget.getGL_Budget_ID();
        if (budgetId <= 0 && importBudget.getBudgetCode() != null)
            budgetId = getId(X_GL_Budget.Table_Name, X_GL_Budget.COLUMNNAME_Name + "=?", trxName, importBudget.getBudgetCode());
        if (budgetId > 0 && importBudget.getGL_Budget_ID() <= 0)
            importBudget.setGL_Budget_ID(budgetId);
        if (importBudget.getGL_Budget_ID() <= 0 && importBudget.getBudgetCode() != null)
            stringError.append("@GL_Budget_ID@ @NotFound@ ");

        // Set Asset
        Integer assetId = 0;
        if (importBudget.getA_Asset_ID() > 0)
            assetId = importBudget.getA_Asset_ID();
        if (assetId <= 0 && importBudget.getAssetValue() != null)
            assetId = getId(X_A_Asset.Table_Name, X_A_Asset.COLUMNNAME_Value + "=?", trxName, importBudget.getAssetValue());
        if (assetId > 0 && importBudget.getA_Asset_ID() <= 0)
            importBudget.setA_Asset_ID(assetId);
        if (importBudget.getA_Asset_ID() <= 0 && importBudget.getAssetValue() != null)
            stringError.append("@A_Asset_ID@ @NotFound@ ");

        // Set Account
        int accountId = 0;
        if (importBudget.getAccount_ID() > 0)
            accountId = importBudget.getAccount_ID();
        if (accountId <= 0 && importBudget.getAccountValue() != null)
            accountId = getId(MElementValue.Table_Name, MElementValue.COLUMNNAME_Value + "=?", trxName, importBudget.getAccountValue());
        if (accountId > 0 && importBudget.getAccount_ID() <= 0 )
            importBudget.setAccount_ID(accountId);
        if (importBudget.getAccount_ID() <= 0 && importBudget.getAccountValue() != null)
            stringError.append("@Account_ID@ @NotFound@ ");

        int partnerId = 0;
        if (importBudget.getC_BPartner_ID() > 0)
            partnerId = importBudget.getC_BPartner_ID();
        if (partnerId <= 0 && importBudget.getBPartnerValue() != null) {
            MBPartner partner =  MBPartner.get(getCtx(), importBudget.getBPartnerValue());
            if (partner != null)
            partnerId = partner.getC_BPartner_ID();
        }
        if (partnerId > 0 && importBudget.getC_BPartner_ID() <= 0)
            importBudget.setC_BPartner_ID(partnerId);
        if (importBudget.getC_BPartner_ID() <= 0 && importBudget.getBPartnerValue() != null)
            stringError.append("@C_BPartner_ID@ @NotFound@ ");

        // Set Product
        Integer productId = 0;
        if (importBudget.getM_Product_ID() > 0)
            productId = importBudget.getM_Product_ID();
        if (productId <= 0 && importBudget.getProductValue() != null)
            productId = getId(MProduct.Table_Name, MProduct.COLUMNNAME_Value + "=?", trxName, importBudget.getProductValue());
        if (productId > 0 && importBudget.getM_Product_ID() <= 0)
            importBudget.setM_Product_ID(productId);
        if (importBudget.getM_Product_ID() <= 0 && importBudget.getProductValue() != null)
            stringError.append("@M_Product_ID@ @NotFound@ ");

        // Set Project
        Integer projectId = 0;
        if (importBudget.getC_Project_ID() > 0)
            projectId = importBudget.getC_Project_ID();
        if (projectId <= 0 && importBudget.getProjectValue() != null)
            projectId = getId(MProject.Table_Name, MProject.COLUMNNAME_Value + "=?", trxName, importBudget.getProjectValue());
        if (projectId > 0 && importBudget.getC_Project_ID() <= 0)
            importBudget.setC_Project_ID(projectId);
        if (importBudget.getC_Project_ID() <= 0 && importBudget.getProjectValue() != null)
            stringError.append("@C_Project_ID@ @NotFound@ ");

        // Set Campaign
        Integer campaignId = 0;
        if (importBudget.getC_Campaign_ID() > 0)
            campaignId = importBudget.getC_Campaign_ID();
        if (campaignId <= 0 && importBudget.getCampaignValue() != null )
            campaignId = getId(MCampaign.Table_Name, MCampaign.COLUMNNAME_Value + "=?", trxName, importBudget.getCampaignValue());
        if (campaignId > 0 && importBudget.getC_Campaign_ID() <= 0)
            importBudget.setC_Campaign_ID(campaignId);
        if (importBudget.getC_Campaign_ID() <= 0 && importBudget.getCampaignValue() != null)
            stringError.append("@C_Campaign_ID@ @NotFound@ ");

        // Set Sales Region
        Integer salesRegionId = 0;
        if (importBudget.getC_SalesRegion_ID() > 0)
            salesRegionId = importBudget.getC_SalesRegion_ID();
        if (salesRegionId <= 0 && importBudget.getSalesRegionValue() != null)
            salesRegionId = getId(MSalesRegion.Table_Name, MSalesRegion.COLUMNNAME_Value + "=?", trxName, importBudget.getSalesRegionValue());
        if (salesRegionId > 0 && importBudget.getC_SalesRegion_ID() <= 0)
            importBudget.setC_SalesRegion_ID(salesRegionId);
        if (importBudget.getC_SalesRegion_ID() <= 0 && importBudget.getSalesRegionValue() != null)
            stringError.append("@C_SalesRegion_ID@ @NotFound@");


        // Set Activity Id
        Integer activityId = 0;
        if (importBudget.getC_Activity_ID() > 0)
            activityId = importBudget.getC_Activity_ID();
        if (activityId <= 0 && importBudget.getActivityValue() != null)
            activityId = getId(MActivity.Table_Name, MActivity.COLUMNNAME_Value + "=?", trxName, importBudget.getActivityValue());
        if (accountId > 0 && importBudget.getC_Activity_ID() <= 0)
            importBudget.setC_Activity_ID(activityId);
        if (importBudget.getC_Activity_ID() <= 0 && importBudget.getActivityValue() != null)
            stringError.append("@C_SalesRegion_ID@ @NotFound@ ");

        // Set Sub Acct Id
        Integer subAcctountId = 0;
        if (importBudget.getC_SubAcct_ID() > 0)
            subAcctountId = importBudget.getC_SubAcct_ID();
        if (subAcctountId <= 0 && importBudget.getSubAcctValue() != null)
            subAcctountId = getId(X_C_SubAcct.Table_Name, X_C_SubAcct.COLUMNNAME_Value + "=?", trxName, importBudget.getSubAcctValue());
        if (subAcctountId > 0 && importBudget.getC_SubAcct_ID() <= 0)
            importBudget.setC_SubAcct_ID(subAcctountId);
        if (importBudget.getC_SubAcct_ID() <= 0 && importBudget.getSubAcctValue() != null)
            stringError.append("@C_SubAcct_ID@ @NotFound@ ");

        // Set User 1
        Integer user1Id = 0;
        if (importBudget.getUser1_ID() > 0)
            user1Id = importBudget.getUser1_ID();
        if (user1Id <= 0 && importBudget.getUser1_ID() <= 0 && importBudget.getUserValue1() != null ) {
            Arrays.stream(acctSchemaElements)
                    .filter(acctSchemaElement -> MAcctSchemaElement.ELEMENTTYPE_UserList1.equals(acctSchemaElement.getElementType()))
                    .forEach(acctSchemaElement -> {
                        String where = MElementValue.COLUMNNAME_C_Element_ID + "=? AND " + MElementValue.COLUMNNAME_Value + "=?";
                        importBudget.setUser1_ID(getId(MElementValue.Table_Name, where, trxName, acctSchemaElement.getC_Element_ID(), importBudget.getUserValue1()));
                    });
        }
        if (importBudget.getUser1_ID() <= 0 && importBudget.getUserValue1() != null)
            stringError.append("@User1_ID@ @NotFound@ ");
        // User 2
        Integer user2Id = 0;
        if (importBudget.getUser2_ID() > 0)
            user2Id = importBudget.getUser2_ID();
        if (user2Id <= 0 && importBudget.getUser2_ID() <=  0 && importBudget.getUserValue2() != null) {
            Arrays.stream(acctSchemaElements)
                    .filter(acctSchemaElement -> MAcctSchemaElement.ELEMENTTYPE_UserList2.equals(acctSchemaElement.getElementType()))
                    .forEach(acctSchemaElement -> {
                        String where = MElementValue.COLUMNNAME_C_Element_ID + "=? AND " + MElementValue.COLUMNNAME_Value + "=?";
                        importBudget.setUser2_ID(getId(MElementValue.Table_Name, where, trxName, acctSchemaElement.getC_Element_ID(), importBudget.getUserValue2()));
                    });
        }
        if (importBudget.getUser2_ID() <= 0 && importBudget.getUserValue2() != null)
            stringError.append("@User2_ID@ @NotFound@ ");

        Integer user3Id = 0;
        if (importBudget.getUser3_ID() > 0)
            user3Id = importBudget.getUser3_ID();
        if (user3Id <= 0 && importBudget.getUser3_ID() <= 0 && importBudget.getUserValue3() != null) {
            Arrays.stream(acctSchemaElements)
                    .filter(acctSchemaElement -> MAcctSchemaElement.ELEMENTTYPE_UserList3.equals(acctSchemaElement.getElementType()))
                    .forEach(acctSchemaElement -> {
                        String where = MElementValue.COLUMNNAME_C_Element_ID + "=? AND " + MElementValue.COLUMNNAME_Value + "=?";
                        importBudget.setUser3_ID(getId(MElementValue.Table_Name, where, trxName, acctSchemaElement.getC_Element_ID(), importBudget.getUserValue3()));
                    });
        }
        if (importBudget.getUser3_ID() <= 0 && importBudget.getUserValue3() != null)
            stringError.append("@User3_ID@ @NotFound@ ");


        Integer user4Id = 0;
        if (importBudget.getUser4_ID() > 0)
            user4Id = importBudget.getUser4_ID();
        if (user4Id <= 0 && importBudget.getUser4_ID() <= 0 && importBudget.getUserValue4() != null) {
            Arrays.stream(acctSchemaElements)
                    .filter(acctSchemaElement -> MAcctSchemaElement.ELEMENTTYPE_UserList4.equals(acctSchemaElement.getElementType()))
                    .forEach(acctSchemaElement -> {
                        String where = MElementValue.COLUMNNAME_C_Element_ID + "=? AND " + MElementValue.COLUMNNAME_Value + "=?";
                        importBudget.setUser4_ID(getId(MElementValue.Table_Name, where, trxName, acctSchemaElement.getC_Element_ID(), importBudget.getUserValue4()));
                    });
        }
        if (importBudget.getUser4_ID() <= 0 && importBudget.getUserValue4() != null)
            stringError.append("@User4_ID@ @NotFound@ ");

        Integer userElement1Id = 0;
        if (importBudget.getUserElement1_ID() > 0)
            userElement1Id = importBudget.getUserElement1_ID();
        if (userElement1Id <= 0 && importBudget.getUserElement1_ID() <= 0 && importBudget.getUserElementValue1() != null) {
            Arrays.stream(acctSchemaElements)
                    .filter(acctSchemaElement -> MAcctSchemaElement.ELEMENTTYPE_UserElement1.equals(acctSchemaElement.getElementType()))
                    .forEach(acctSchemaElement -> {
                        I_AD_Column column = acctSchemaElement.getAD_Column();
                        MTable table = (MTable) column.getAD_Table();
                        MColumn columnName = table.getColumn("Value");
                        if (columnName == null)
                            columnName = table.getColumn("Name");
                        if (columnName != null)
                            importBudget.setUserElement1_ID(getId(table.getTableName(), columnName.getColumnName() + "=?", trxName, importBudget.getUserElementValue1()));
                    });
        }
        if (importBudget.getUserElement1_ID() <= 0 && importBudget.getUserElementValue1() != null)
            stringError.append("@UserElement1_ID@ @NotFound@ ");

        Integer userElement2Id = 0;
        if (importBudget.getUserElement2_ID() > 0)
            userElement2Id = importBudget.getUserElement2_ID();
        if (userElement2Id <= 0 && importBudget.getUserElement2_ID() <= 0 &&  importBudget.getUserElementValue2() != null) {
            Arrays.stream(acctSchemaElements)
                    .filter(acctSchemaElement -> MAcctSchemaElement.ELEMENTTYPE_UserElement2.equals(acctSchemaElement.getElementType()))
                    .forEach(acctSchemaElement -> {
                        I_AD_Column column = acctSchemaElement.getAD_Column();
                        MTable table = (MTable) column.getAD_Table();
                        MColumn columnName = table.getColumn("Value");
                        if (columnName == null)
                                columnName = table.getColumn("Name");
                        if (columnName != null)
                            importBudget.setUserElement2_ID(getId(table.getTableName(), columnName.getColumnName() + "=?", trxName, importBudget.getUserElementValue2()));
                    });
        }
        if (importBudget.getUserElement2_ID() <= 0 && importBudget.getUserElementValue2() != null)
            stringError.append("@UserElement2_ID@ @NotFound@ ");

        if (accountSchemaId <= 0)
            stringError.append("@C_AcctSchema_ID@ @NotFound@ ");
        if (budgetId <= 0)
            stringError.append("@GL_Budget_ID@ @NotFound@ ");
        if (accountId <= 0)
            stringError.append("@Account_ID@ @NotFound@ ");

        if (!stringError.toString().isEmpty() && stringError.toString().length() > 0) {
            importBudget.setI_ErrorMsg(Msg.parseTranslation(getCtx(), stringError.toString()));
            importBudget.saveEx();
        }
        importBudget.saveEx();
    }

    /**
     * Validate Quantity Check
     * @param budgetLinesIds
     */
    private void validateBudget(int[] budgetLinesIds) {

		AtomicReference<BigDecimal> balance0 = new AtomicReference<>(BigDecimal.ZERO);
		AtomicReference<BigDecimal> balance1 = new AtomicReference<>(BigDecimal.ZERO);
		AtomicReference<BigDecimal> balance2 = new AtomicReference<>(BigDecimal.ZERO);
		AtomicReference<BigDecimal> balance3 = new AtomicReference<>(BigDecimal.ZERO);
		AtomicReference<BigDecimal> balance4 = new AtomicReference<>(BigDecimal.ZERO);
		AtomicReference<BigDecimal> balance5 = new AtomicReference<>(BigDecimal.ZERO);
		AtomicReference<BigDecimal> balance6 = new AtomicReference<>(BigDecimal.ZERO);
		AtomicReference<BigDecimal> balance7 = new AtomicReference<>(BigDecimal.ZERO);
		AtomicReference<BigDecimal> balance8 = new AtomicReference<>(BigDecimal.ZERO);
		AtomicReference<BigDecimal> balance9 = new AtomicReference<>(BigDecimal.ZERO);
		AtomicReference<BigDecimal> balance10 = new AtomicReference<>(BigDecimal.ZERO);
		AtomicReference<BigDecimal> balance11 = new AtomicReference<>(BigDecimal.ZERO);

        Arrays.stream(budgetLinesIds)
                .forEach(importBudgetId -> {
                    X_I_Budget importBudget = new X_I_Budget(getCtx(), importBudgetId, null);
                    if ((importBudget.getMonth_0_Amt().signum() != 0 && importBudget.getMonth_0_Qty().signum() != 0 && importBudget.getMonth_0_Amt().signum() != importBudget.getMonth_0_Qty().signum())
                     || (importBudget.getMonth_1_Amt().signum() != 0 && importBudget.getMonth_1_Qty().signum() != 0 && importBudget.getMonth_1_Amt().signum() != importBudget.getMonth_1_Qty().signum())
                     || (importBudget.getMonth_2_Amt().signum() != 0 && importBudget.getMonth_2_Qty().signum() != 0 && importBudget.getMonth_2_Amt().signum() != importBudget.getMonth_2_Qty().signum())
                     || (importBudget.getMonth_3_Amt().signum() != 0 && importBudget.getMonth_3_Qty().signum() != 0 && importBudget.getMonth_3_Amt().signum() != importBudget.getMonth_3_Qty().signum())
                     || (importBudget.getMonth_4_Amt().signum() != 0 && importBudget.getMonth_4_Qty().signum() != 0 && importBudget.getMonth_4_Amt().signum() != importBudget.getMonth_4_Qty().signum())
                     || (importBudget.getMonth_5_Amt().signum() != 0 && importBudget.getMonth_5_Qty().signum() != 0 && importBudget.getMonth_5_Amt().signum() != importBudget.getMonth_5_Qty().signum())
                     || (importBudget.getMonth_6_Amt().signum() != 0 && importBudget.getMonth_6_Qty().signum() != 0 && importBudget.getMonth_6_Amt().signum() != importBudget.getMonth_6_Qty().signum())
                     || (importBudget.getMonth_7_Amt().signum() != 0 && importBudget.getMonth_7_Qty().signum() != 0 && importBudget.getMonth_7_Amt().signum() != importBudget.getMonth_7_Qty().signum())
                     || (importBudget.getMonth_8_Amt().signum() != 0 && importBudget.getMonth_8_Qty().signum() != 0 && importBudget.getMonth_8_Amt().signum() != importBudget.getMonth_8_Qty().signum())
                     || (importBudget.getMonth_9_Amt().signum() != 0 && importBudget.getMonth_9_Qty().signum() != 0 && importBudget.getMonth_9_Amt().signum() != importBudget.getMonth_9_Qty().signum())
                     || (importBudget.getMonth_10_Amt().signum() != 0 && importBudget.getMonth_10_Qty().signum() != 0 && importBudget.getMonth_10_Amt().signum() != importBudget.getMonth_10_Qty().signum())
                     || (importBudget.getMonth_11_Amt().signum() != 0 && importBudget.getMonth_11_Qty().signum() != 0 && importBudget.getMonth_11_Amt().signum() != importBudget.getMonth_11_Qty().signum())
                    )
                        throw new AdempiereException("@X_I_Budget_ID@ " + importBudgetId + " ERR=Qty Mismatch. Credit Line must have negative qty and Debit line must have positive qty.");

					balance0.updateAndGet(balance -> balance.add(importBudget.getMonth_0_Amt()));
					balance1.updateAndGet(balance -> balance.add(importBudget.getMonth_1_Amt()));
					balance2.updateAndGet(balance -> balance.add(importBudget.getMonth_2_Amt()));
					balance3.updateAndGet(balance -> balance.add(importBudget.getMonth_3_Amt()));
					balance4.updateAndGet(balance -> balance.add(importBudget.getMonth_4_Amt()));
					balance5.updateAndGet(balance -> balance.add(importBudget.getMonth_5_Amt()));
					balance6.updateAndGet(balance -> balance.add(importBudget.getMonth_6_Amt()));
					balance7.updateAndGet(balance -> balance.add(importBudget.getMonth_7_Amt()));
					balance8.updateAndGet(balance -> balance.add(importBudget.getMonth_8_Amt()));
					balance9.updateAndGet(balance -> balance.add(importBudget.getMonth_9_Amt()));
					balance10.updateAndGet(balance -> balance.add(importBudget.getMonth_10_Amt()));
					balance11.updateAndGet(balance -> balance.add(importBudget.getMonth_11_Amt()));
                });

		if (balance0.get().compareTo(BigDecimal.ZERO) != 0
				|| balance1.get().compareTo(BigDecimal.ZERO) != 0
				|| balance2.get().compareTo(BigDecimal.ZERO) != 0
				|| balance3.get().compareTo(BigDecimal.ZERO) != 0
				|| balance4.get().compareTo(BigDecimal.ZERO) != 0
				|| balance5.get().compareTo(BigDecimal.ZERO) != 0
				|| balance6.get().compareTo(BigDecimal.ZERO) != 0
				|| balance7.get().compareTo(BigDecimal.ZERO) != 0
				|| balance8.get().compareTo(BigDecimal.ZERO) != 0
				|| balance9.get().compareTo(BigDecimal.ZERO) != 0
				|| balance10.get().compareTo(BigDecimal.ZERO) != 0
				|| balance11.get().compareTo(BigDecimal.ZERO) != 0)
			throw new AdempiereException("Amount balance(DR-CR) of all journals are not zero");

    }

    /**
     * Generate Journal Batch
     * @param documentNo
     * @param glCategoryId
     * @param documentTypeId
     * @param currencyId
     * @param conversionTypeId
     * @param calendarId
     * @param budgetId
     */
    private void generateJournalBatch(String trxName , String documentNo, int glCategoryId, int documentTypeId, int currencyId, int conversionTypeId, int calendarId, int budgetId) {
        MJournalBatch journalBatch = new MJournalBatch(getCtx(), 0, trxName);
        journalBatch.setDocumentNo(documentNo);
        journalBatch.setDescription(getBatchDescription());
        journalBatch.setPostingType(MJournalBatch.POSTINGTYPE_Budget);
        journalBatch.setDateAcct(getAccountDate());
        journalBatch.setDateDoc(getAccountDate());
        journalBatch.setGL_Category_ID(glCategoryId);
        journalBatch.setC_Currency_ID(currencyId);
        journalBatch.setC_DocType_ID(documentTypeId);
        journalBatch.setAD_Org_ID(getOrganizationId());
        //Current Period
        int periodId = MPeriod.getC_Period_ID(getCtx(), getAccountDate(), Env.getAD_Org_ID(getCtx()));
        journalBatch.setC_Period_ID(periodId);
        journalBatch.saveEx();
        //Create Journal
        createJournal(journalBatch, conversionTypeId, calendarId, budgetId);
    }

    /**
     * reate Journal
     * @param journalBatch
     * @param conversionTypeId
     * @param calendarId
     * @param budgetId
     */
    private void createJournal(MJournalBatch journalBatch, int conversionTypeId, int calendarId, int budgetId) {
        int noPeriods = 0;
        if (getNoOfPeriods() == 0 || getNoOfPeriods() > 12)
            noPeriods = 12;
        else
            noPeriods = getNoOfPeriods();

        definePeriods(calendarId);
        if (noPeriods > glPeriods.size())
            noPeriods = glPeriods.size();

        for (int periodNo = 0; periodNo < noPeriods; periodNo++) {
            MJournal journal = new MJournal(getCtx(), 0, journalBatch.get_TrxName());
            journal.setGL_JournalBatch_ID(journalBatch.getGL_JournalBatch_ID());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
            String formattedDate = dateFormat.format(glPeriods.get(periodNo).getStartDate());
            journal.setDocumentNo(journalBatch.getDocumentNo() + "-" + formattedDate);
            journal.setDescription(getBatchDescription());
            journal.setDateAcct(glPeriodsDates.get(periodNo));
            journal.setDateDoc(glPeriodsDates.get(periodNo));
            journal.setC_Period_ID(glPeriods.get(periodNo).getC_Period_ID());
            journal.setClientOrg(journal.getGL_JournalBatch().getAD_Client_ID(), journal.getGL_JournalBatch().getAD_Org_ID());
            journal.setPostingType(MJournalBatch.POSTINGTYPE_Budget);
            journal.setGL_Category_ID(journalBatch.getGL_Category_ID());
            journal.setC_Currency_ID(journalBatch.getC_Currency_ID());
            journal.setC_DocType_ID(journalBatch.getC_DocType_ID());
            journal.setCurrencyRate(BigDecimal.ONE);
            journal.setC_ConversionType_ID(conversionTypeId);
            journal.setGL_Budget_ID(budgetId);
            journal.setC_AcctSchema_ID(getAccountingSchemaId());
            journal.saveEx();

            createJournalLine(journal, journalBatch.getDocumentNo(), periodNo);
        }
    }

    /**
     * Create Journal Line
     * @param journal
     * @param documentNo
     * @param noOfMonth
     */
    private void createJournalLine(MJournal journal, String documentNo, int noOfMonth) {
        AtomicInteger lines = new AtomicInteger();
        Arrays.stream(getBudget(documentNo, false, false)).forEach(importBudgetId -> {
            X_I_Budget importBudget = new X_I_Budget(getCtx(), importBudgetId , journal.get_TrxName());
            BigDecimal amount = (BigDecimal) importBudget.get_Value("Month_" + noOfMonth + "_Amt");
            BigDecimal quantity = (BigDecimal) importBudget.get_Value("Month_" + noOfMonth + "_Qty");
            if (amount != null && amount.compareTo(BigDecimal.ZERO) != 0) {
                MJournalLine journalLine = new MJournalLine(getCtx(), 0, journal.get_TrxName());
                journalLine.setGL_Journal_ID(journal.getGL_Journal_ID());
                if (amount.compareTo(BigDecimal.ZERO) < 0)
                    journalLine.setAmtSourceCr(amount.abs());
                else
                    journalLine.setAmtSourceDr(amount);

                if (quantity != null)
                    journalLine.setQty(quantity);
                journalLine.setAD_Org_ID(journalLine.getParent().getAD_Org_ID());
                journalLine.setC_Currency_ID(journal.getC_Currency_ID());
                journalLine.setDateAcct(journalLine.getParent().getDateAcct());
                journalLine.setC_ConversionType_ID(journal.getC_ConversionType_ID());
                journalLine.setCurrencyRate(BigDecimal.ONE);
                journalLine.setLine(lines.get());
                journalLine.setDescription(importBudget.getJnl_Line_Description());
                lines.updateAndGet(line -> line + 1);
                if (importBudget.getA_Asset_ID() > 0) {
                    journalLine.setA_Asset_ID(importBudget.getA_Asset_ID());
                    journalLine.setA_CreateAsset(true);
                }
                MAccount account = MAccount.get(getCtx(),
                        importBudget.getAD_Client_ID(),
                        importBudget.getAD_Org_ID(),
                        importBudget.getC_AcctSchema_ID(),
                        importBudget.getAccount_ID(),
                        importBudget.getC_SubAcct_ID(),
                        importBudget.getM_Product_ID(),
                        importBudget.getC_BPartner_ID(),
                        importBudget.getAD_OrgTrx_ID(),
                        importBudget.getC_LocFrom_ID(),
                        importBudget.getC_LocTo_ID(),
                        importBudget.getC_SalesRegion_ID(),
                        importBudget.getC_Project_ID(),
                        importBudget.getC_Campaign_ID(),
                        importBudget.getC_Activity_ID(),
                        importBudget.getUser1_ID(),
                        importBudget.getUser2_ID(),
                        importBudget.getUser3_ID(),
                        importBudget.getUser4_ID(),
                        importBudget.getUserElement1_ID(),
                        importBudget.getUserElement2_ID(),
                        journal.get_TrxName());
                account.saveEx();
                journalLine.setAlias_ValidCombination_ID(account.getC_ValidCombination_ID());
                journalLine.setC_ValidCombination_ID(account.getC_ValidCombination_ID());
                journalLine.saveEx();

                importBudget.setI_IsImported(true);
                importBudget.setC_ValidCombination_ID(account.getC_ValidCombination_ID());
                importBudget.setGL_JournalBatch_ID(journal.getGL_JournalBatch_ID());
                importBudget.setGL_Journal_ID(journal.getGL_Journal_ID());
                importBudget.setGL_JournalLine_ID(journalLine.getGL_JournalLine_ID());
                importBudget.setProcessed(true);
                importBudget.saveEx();
            }
        });
    }

    /**
     * @param calendarId
     * @return
     */
    private List<MPeriod> definePeriods(int calendarId) {
        int offset = 0;
        MPeriod period = MPeriod.findByCalendar(getCtx(), getAccountDate(), calendarId, null);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(period.getEndDate().getTime());
        calendar.add(Calendar.DATE, 1);
        Timestamp startTime = new Timestamp(calendar.getTimeInMillis());
        Date startDateNextPeriod = new Date(startTime.getTime());
        offset = startDateNextPeriod.getMonth() - period.getStartDate().getMonth();
        glPeriods.add(period);
        glPeriodsDates.add(getAccountDate());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(getAccountDate().getTime());

        for (int i = 1; i < 12; i++) {
            cal.add(Calendar.MONTH, offset);
            Timestamp nextDate = new Timestamp(cal.getTimeInMillis());
            glPeriodsDates.add(nextDate);
            MPeriod acctPeriod = MPeriod.findByCalendar(getCtx(), nextDate, calendarId, null);
            cal.setTimeInMillis(nextDate.getTime());
            if (acctPeriod != null)
                glPeriods.add(acctPeriod);

        }
        return glPeriods;
    }

    /**
     * get ids based table name
     * @param tableName
     * @param whereClause
     * @param trxName
     * @param parameters
     * @return
     */
    private int getId(String tableName, String whereClause, String trxName, Object... parameters) {
        return new Query(getCtx(), tableName, whereClause, trxName)
                .setParameters(parameters)
                .setClient_ID()
                .firstId();
    }

    /**
     * Get budget lines by document no
     * @param documentNo
     * @param isImported
     * @param isProcessed
     * @return
     */
    private int[] getBudget(String documentNo, boolean isImported, boolean isProcessed) {
        StringBuilder whereClause = new StringBuilder();
        whereClause
                .append(I_I_Budget.COLUMNNAME_BatchDocumentNo).append("=? AND ")
                .append(I_I_Budget.COLUMNNAME_I_IsImported).append("=? AND ")
                .append(I_I_Budget.COLUMNNAME_Processed).append("=?");

        return new Query(getCtx(), X_I_Budget.Table_Name, whereClause.toString(), null)
                .setOnlyActiveRecords(true)
                .setParameters(documentNo, isImported, isProcessed)
                .setClient_ID()
                .getIDs();

    }
}