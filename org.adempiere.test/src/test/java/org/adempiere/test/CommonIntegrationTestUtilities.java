/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2020 ADempiere Foundation, All Rights Reserved.         *
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
package org.adempiere.test;

import static org.adempiere.core.domains.models.X_C_PeriodControl.PERIODSTATUS_Closed;
import static org.adempiere.core.domains.models.X_C_PeriodControl.PERIODSTATUS_Open;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


import java.sql.Timestamp;
import java.util.Optional;
import java.util.Properties;

import org.compiere.model.MAcctSchema;
import org.compiere.model.MClient;
import org.compiere.model.MDocType;
import org.compiere.model.MPeriod;
import org.compiere.model.MPeriodControl;
import org.compiere.util.Env;
import org.compiere.util.Language;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;

public class CommonIntegrationTestUtilities {

    private Timestamp currentLoginDate =
            TimeUtil.getDay(System.currentTimeMillis());
    private Boolean initialAutoPeriodControl;
    private static final String DATE_PROPERTY = "#Date";

    public void resetLoginDate(Properties ctx) {

        ctx.setProperty(DATE_PROPERTY, currentLoginDate.toString());

    }

    public void setLoginDate(Properties ctx, Timestamp newLoginDate) {

        currentLoginDate = Env.getContextAsDate(ctx, DATE_PROPERTY);
        ctx.setProperty(DATE_PROPERTY, newLoginDate.toString());

    }

    public static void assertEnEsMessageTranslationsExist(String msgKey) {

        Language base = Language.getBaseLanguage();
        Language esMx = Language.getLanguage("es_MX");
        String translatedMsgBase = Msg.translate(base, msgKey);
        String translatedMsgEsMx = Msg.translate(esMx, msgKey);

        assertNotEquals(msgKey, translatedMsgBase,
                "Message not translated in " + base.getAD_Language() + ": " + msgKey);
        assertNotEquals(translatedMsgBase, translatedMsgEsMx,
                "Message not translated in " + esMx.getAD_Language() + ": " + msgKey);

    }
    
    public void turnOffAutoPeriodControl(Properties ctx, int ad_client_id) {

        MAcctSchema as = MClient.get(ctx, ad_client_id).getAcctSchema();
        initialAutoPeriodControl = Optional
                .ofNullable(initialAutoPeriodControl)
                .orElse(as.isAutoPeriodControl());
        as.setAutoPeriodControl(false);
        as.saveEx();

    }
    
    public void turnOnAutoPeriodControl(Properties ctx, int ad_client_id) {

        MAcctSchema as = MClient.get(ctx, ad_client_id).getAcctSchema();
        initialAutoPeriodControl = Optional
                .ofNullable(initialAutoPeriodControl)
                .orElse(as.isAutoPeriodControl());
        as.setAutoPeriodControl(true);
        as.saveEx();

    }

    public void closePeriod(Properties ctx, int ad_org_id,
            Timestamp dateInPeriod, String docBaseType, String trxName) {

        MPeriod period = MPeriod.get(ctx, dateInPeriod, ad_org_id, trxName);
        MPeriodControl periodControl = period.getPeriodControl(docBaseType);
        periodControl.setPeriodStatus(PERIODSTATUS_Closed);
        periodControl.saveEx();

    }

    public void closePeriod(Properties ctx, int adOrgId, MDocType docType,
            Timestamp date, String trxName) {

        String docBaseType = docType.getDocBaseType();
        closePeriod(ctx, adOrgId, date, docBaseType, trxName);

    }

    public void openPeriod(Properties ctx, int ad_org_id,
            Timestamp dateInPeriod,
            String docBaseType, String trxName) {

        MPeriod period = MPeriod.get(ctx, dateInPeriod, ad_org_id, trxName);
        MPeriodControl periodControl = period.getPeriodControl(docBaseType);
        periodControl.setPeriodStatus(PERIODSTATUS_Open);
        periodControl.saveEx();

    }

    public void openPeriod(Properties ctx, int adOrgId, MDocType docType,
            Timestamp date, String trxName) {

        String docBaseType = docType.getDocBaseType();
        openPeriod(ctx, adOrgId, date, docBaseType, trxName);

    }


    public void resetAutoPeriodControl(Properties ctx, int ad_client_id) {

        Optional.ofNullable(initialAutoPeriodControl)
                .ifPresent(initialSetting -> {
                    MAcctSchema as =
                            MClient.get(ctx, ad_client_id).getAcctSchema();
                    as.setAutoPeriodControl(initialSetting);
                    as.saveEx();
                });

    }

}
