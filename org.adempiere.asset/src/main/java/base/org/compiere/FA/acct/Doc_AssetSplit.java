/**
 * Copyright (C) 2003-2017, e-Evolution Consultants S.A. , http://www.e-evolution.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 * Created by victor.perez@e-evolution.com , www.e-evolution.com
 */

package org.compiere.FA.acct;

import org.compiere.FA.model.MAsset;
import org.compiere.FA.model.MAssetAcct;
import org.compiere.FA.model.MAssetSplit;
import org.compiere.FA.model.MDepreciationWorkfile;
import org.compiere.acct.Doc;
import org.compiere.acct.Fact;
import org.compiere.acct.FactLine;
import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MDocType;
import org.compiere.util.Env;
import org.compiere.util.Msg;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Accounting Asset Split Document
 */
public class Doc_AssetSplit extends Doc {

    public Doc_AssetSplit(MAcctSchema[] as, ResultSet rs, String trxName) {
        super(as, MAssetSplit.class, rs, MDocType.DOCBASETYPE_GLDocument, trxName);
    }


    protected String loadDocumentDetails() {
        return null;
    }


    public BigDecimal getBalance() {
        return Env.ZERO;
    }


    /**
     * Create Facts
     *
     * @param as accounting schema
     * @return
     */
    public ArrayList<Fact> createFacts(MAcctSchema as) {
        MAssetSplit assetSplit = (MAssetSplit) getPO();
        MAsset fixedAssetSource = MAsset.get(getCtx(), assetSplit.getA_Asset_ID(), getTrxName());
        ArrayList<Fact> facts = new ArrayList<Fact>();
        MAccount disposalLossAccount = getAccount(assetSplit.getA_Asset_ID(),
                assetSplit.getPostingType(),
                assetSplit.getDateAcct(),
                MAssetAcct.COLUMNNAME_A_Disposal_Loss_Acct);
        MAccount assetAccount = getAccount(assetSplit.getA_Asset_ID(),
                assetSplit.getPostingType(),
                assetSplit.getDateAcct(),
                MAssetAcct.COLUMNNAME_A_Asset_Acct);
        MAccount accumDepreciationAccount = getAccount(assetSplit.getA_Asset_ID(),
                assetSplit.getPostingType(),
                assetSplit.getDateAcct(),
                MAssetAcct.COLUMNNAME_A_Accumdepreciation_Acct);
        MAccount depreciationAccount = getAccount(assetSplit.getA_Asset_ID(),
                assetSplit.getPostingType(),
                assetSplit.getDateAcct(),
                MAssetAcct.COLUMNNAME_A_Depreciation_Acct);

        if (disposalLossAccount != null
                && assetAccount != null
                && accumDepreciationAccount != null
                && depreciationAccount != null)
            ;
        else
            return new ArrayList<Fact>();

        assetSplit.getAssetBalance().ifPresent(assetBalance -> {
            Fact fact = new Fact(this, as, assetSplit.getPostingType());
            facts.add(fact);
            StringBuilder description = new StringBuilder();
            description
                    .append(" @A_Asset_Split_ID@ ").append(getDocumentNo())
                    .append(" @A_Asset_ID@ ").append(fixedAssetSource.getValue())
                    .append(" @InventotyNo@ ").append(fixedAssetSource.getInventoryNo());

            if (assetSplit.getA_Asset_Cost().signum() > 0) {

                FactLine costLost = fact.createLine(null,
                        disposalLossAccount
                        , as.getC_Currency_ID()
                        , assetSplit.getA_Asset_Cost()
                        , Env.ZERO);
                costLost.setDescription(Msg.parseTranslation(getCtx(), description.toString()));
                costLost.setA_Asset_ID(assetSplit.getA_Asset_ID());
                costLost.saveEx();


                //Remove the Fixed Asset Cost
                FactLine cost = fact.createLine(null
                        , assetAccount
                        , as.getC_Currency_ID()
                        , Env.ZERO, assetSplit.getA_Asset_Cost());
                cost.setA_Asset_ID(assetSplit.getA_Asset_ID());
                cost.setDescription(Msg.parseTranslation(getCtx(), description.toString()));
                cost.saveEx();
            }


            if (assetBalance.getA_Accumulated_Depr().signum() > 0) {
                //Remove the Fixed Accum Depreciation
                FactLine depreciation = fact.createLine(null
                        , accumDepreciationAccount
                        , as.getC_Currency_ID()
                        , assetBalance.getA_Accumulated_Depr(), Env.ZERO);
                depreciation.setA_Asset_ID(assetSplit.getA_Asset_ID());
                depreciation.setDescription(Msg.parseTranslation(getCtx(), description.toString()));
                depreciation.save();

                FactLine depreciationLoss = fact.createLine(null
                        , depreciationAccount
                        , as.getC_Currency_ID()
                        , Env.ZERO, assetBalance.getA_Accumulated_Depr());

                depreciationLoss.setA_Asset_ID(assetSplit.getA_Asset_ID());
                depreciationLoss.setDescription(Msg.parseTranslation(getCtx(), description.toString()));
                depreciationLoss.saveEx();
            }

            assetSplit.getAssetsSplit().stream().forEach(asset -> {
                description
                        .append(" @A_Asset_Split_ID@ ").append(getDocumentNo())
                        .append(" @A_Asset_ID@ ").append(asset.getValue())
                        .append(" @InventotyNo@ ").append(asset.getInventoryNo());

                Optional.ofNullable(MDepreciationWorkfile.get(
                        getCtx(),
                        asset.getA_Asset_ID(),
                        assetSplit.getPostingType(),
                        assetSplit.get_TrxName()))
                        .ifPresent(assetSplitBalance -> {
                            if (assetSplitBalance.getA_Asset_Cost().signum() > 0) {
                                //Add the Fixed Asset Cost
                                FactLine splitCost = fact.createLine(null
                                        , assetAccount
                                        , as.getC_Currency_ID()
                                        , assetSplitBalance.getA_Asset_Cost(),
                                        Env.ZERO);
                                splitCost.setA_Asset_ID(asset.getA_Asset_ID());
                                splitCost.setDescription(Msg.parseTranslation(getCtx(), description.toString()));
                                splitCost.saveEx();

                                FactLine splitCostLost = fact.createLine(null
                                        , disposalLossAccount
                                        , as.getC_Currency_ID()
                                        , Env.ZERO
                                        , assetSplitBalance.getA_Asset_Cost());
                                splitCostLost.setA_Asset_ID(asset.getA_Asset_ID());
                                splitCostLost.setDescription(Msg.parseTranslation(getCtx(), description.toString()));
                                splitCostLost.saveEx();
                            }
                            if (assetSplitBalance.getA_Accumulated_Depr().signum() > 0) {
                                FactLine splitDepreciationLoss = fact.createLine(null
                                        , depreciationAccount
                                        , as.getC_Currency_ID()
                                        , assetSplitBalance.getA_Accumulated_Depr(), Env.ZERO);
                                splitDepreciationLoss.setA_Asset_ID(asset.getA_Asset_ID());
                                splitDepreciationLoss.setDescription(Msg.parseTranslation(getCtx(), description.toString()));
                                splitDepreciationLoss.saveEx();

                                //Add the Fixed Accum Depreciation
                                FactLine splitDepreciation = fact.createLine(null
                                        , accumDepreciationAccount
                                        , as.getC_Currency_ID()
                                        , Env.ZERO
                                        , assetSplitBalance.getA_Accumulated_Depr());
                                splitDepreciation.setA_Asset_ID(asset.getA_Asset_ID());
                                splitDepreciation.setDescription(Msg.parseTranslation(getCtx(), description.toString()));
                                splitDepreciation.saveEx();
                            }
                        });
            });
        });
        return facts;
    }

    /**
     * Get Account
     *
     * @param assetId
     * @param postingType
     * @param dateAcct
     * @param accountName
     * @return
     */
    private MAccount getAccount(int assetId, String postingType, Timestamp dateAcct, String accountName) {
        MAssetAcct assetAcct = MAssetAcct.forA_Asset_ID(getCtx(), assetId, postingType, dateAcct, getTrxName());
        if (assetAcct != null) {
            int accountId = (Integer) assetAcct.get_Value(accountName);
            return MAccount.get(getCtx(), accountId);
        } else return null;
    }
}
