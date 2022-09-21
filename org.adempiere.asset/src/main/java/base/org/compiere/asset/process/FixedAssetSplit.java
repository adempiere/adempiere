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

package org.compiere.asset.process;

import org.compiere.asset.model.MAssetSplit;
import org.compiere.model.MPeriod;
import org.compiere.process.DocAction;
import org.compiere.util.Trx;

/**
 * Generated Process for (Fixed Asset Split Process )
 */
public class FixedAssetSplit extends FixedAssetSplitAbstract {
    @Override
    protected void prepare() {
        super.prepare();
    }

    @Override
    protected String doIt() throws Exception {
        final StringBuilder message = new StringBuilder();
        getSelectionKeys().forEach(assetId -> {
            Trx.run(trxName -> {
                MAssetSplit assetSplit = new MAssetSplit(getCtx(), 0, trxName);
                assetSplit.setC_DocType_ID(getDocTypeId());
                assetSplit.setPostingType(getPostingType());
                assetSplit.setDateDoc(getDateDoc());
                assetSplit.setDateAcct(getDateAcct());
                assetSplit.setA_Asset_ID(assetId);
                assetSplit.setA_Split_Type(getSelectionAsString(assetId, MAssetSplit.COLUMNNAME_A_Split_Type));
                assetSplit.setA_QTY_Split(getSelectionAsBigDecimal(assetId, MAssetSplit.COLUMNNAME_A_QTY_Split));
                assetSplit.setA_Percent_Split(getSelectionAsBigDecimal(assetId, MAssetSplit.COLUMNNAME_A_Percent_Split));
                assetSplit.setA_Amount_Split(getSelectionAsBigDecimal(assetId, MAssetSplit.COLUMNNAME_A_Amount_Split));
                assetSplit.setA_Transfer_Balance_IS(getSelectionAsBoolean(assetId, MAssetSplit.COLUMNNAME_A_Transfer_Balance_IS));
                assetSplit.setDocStatus(DocAction.STATUS_Drafted);
                assetSplit.setDocAction(DocAction.ACTION_Complete);
                MPeriod period  = MPeriod.get(getCtx(), getDateAcct(), assetSplit.getAD_Org_ID(), null);
                if (period != null)
                    assetSplit.setC_Period_ID(period.getC_Period_ID());

                assetSplit.saveEx();
                addLog(assetSplit.getDocumentInfo());
                message.append(" ").append(assetSplit.getDocumentInfo());

                if (getDocAction() != null) {
                    assetSplit.processIt(getDocAction());
                    assetSplit.saveEx();
                }
            });
        });
        return message.toString();
    }
}