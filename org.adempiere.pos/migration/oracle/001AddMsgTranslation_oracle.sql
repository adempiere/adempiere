-- Oct 29, 2015 5:59:24 PM VET
-- Add Changes and translation ADempiere POS
UPDATE AD_Form SET Classname='org.adempiere.pos.VPOS',Updated=TO_DATE('2015-10-29 17:59:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Form_ID=113
;

-- Oct 29, 2015 6:16:58 PM VET
-- Add Changes and translation ADempiere POS
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53309,0,TO_DATE('2015-10-29 18:16:57','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Cancel Order','I',TO_DATE('2015-10-29 18:16:57','YYYY-MM-DD HH24:MI:SS'),100,'POS.IsCancel')
;

-- Oct 29, 2015 6:16:58 PM VET
-- Add Changes and translation ADempiere POS
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53309 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- Oct 29, 2015 6:17:17 PM VET
-- Add Changes and translation ADempiere POS
UPDATE AD_Message_Trl SET MsgText='Anular Orden',Updated=TO_DATE('2015-10-29 18:17:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Message_ID=53309 AND AD_Language='es_MX'
;

-- Oct 29, 2015 6:17:56 PM VET
-- Add Changes and translation ADempiere POS
DELETE FROM AD_UserQuery WHERE AD_UserQuery_ID=50004
;

-- Oct 29, 2015 6:20:34 PM VET
-- Add Changes and translation ADempiere POS
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53310,0,TO_DATE('2015-10-29 18:20:30','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','You must create an Order first','I',TO_DATE('2015-10-29 18:20:30','YYYY-MM-DD HH24:MI:SS'),100,'POS.MustCreateOrder')
;

-- Oct 29, 2015 6:20:34 PM VET
-- Add Changes and translation ADempiere POS
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53310 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- Oct 29, 2015 6:20:45 PM VET
-- Add Changes and translation ADempiere POS
UPDATE AD_Message_Trl SET MsgText='Debe crear la Orden Primero',Updated=TO_DATE('2015-10-29 18:20:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Message_ID=53310 AND AD_Language='es_MX'
;

-- Oct 29, 2015 6:21:44 PM VET
-- Add Changes and translation ADempiere POS
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53311,0,TO_DATE('2015-10-29 18:21:43','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Do you want to delete Order?','I',TO_DATE('2015-10-29 18:21:43','YYYY-MM-DD HH24:MI:SS'),100,'POS.DeleteOrder')
;

-- Oct 29, 2015 6:21:44 PM VET
-- Add Changes and translation ADempiere POS
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53311 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- Oct 29, 2015 6:22:01 PM VET
-- Add Changes and translation ADempiere POS
UPDATE AD_Message_Trl SET MsgText='Desea Eliminar la Orden?',Updated=TO_DATE('2015-10-29 18:22:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Message_ID=53311 AND AD_Language='es_MX'
;

-- Oct 29, 2015 6:22:34 PM VET
-- Add Changes and translation ADempiere POS
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53312,0,TO_DATE('2015-10-29 18:22:33','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','The order is already completed. Do you want to void it?','I',TO_DATE('2015-10-29 18:22:33','YYYY-MM-DD HH24:MI:SS'),100,'POS.OrderIsAlreadyCompleted')
;

-- Oct 29, 2015 6:22:34 PM VET
-- Add Changes and translation ADempiere POS
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53312 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- Oct 29, 2015 6:22:57 PM VET
-- Add Changes and translation ADempiere POS
UPDATE AD_Message_Trl SET MsgText='La Orden está completa. Desea Anularla?',Updated=TO_DATE('2015-10-29 18:22:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Message_ID=53312 AND AD_Language='es_MX'
;

-- Oct 29, 2015 6:29:15 PM VET
-- Add Changes and translation ADempiere POS
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53313,0,TO_DATE('2015-10-29 18:29:09','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Credit Sale','I',TO_DATE('2015-10-29 18:29:09','YYYY-MM-DD HH24:MI:SS'),100,'IsCreditSale')
;

-- Oct 29, 2015 6:29:15 PM VET
-- Add Changes and translation ADempiere POS
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53313 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- Oct 29, 2015 6:29:23 PM VET
-- Add Changes and translation ADempiere POS
UPDATE AD_Message_Trl SET MsgText='Venta a Crédito',Updated=TO_DATE('2015-10-29 18:29:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Message_ID=53313 AND AD_Language='es_MX'
;

-- Oct 29, 2015 6:31:17 PM VET
-- Add Changes and translation ADempiere POS
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53314,0,TO_DATE('2015-10-29 18:31:16','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Alternate Document Type','I',TO_DATE('2015-10-29 18:31:16','YYYY-MM-DD HH24:MI:SS'),100,'POS.AlternateDT')
;

-- Oct 29, 2015 6:31:17 PM VET
-- Add Changes and translation ADempiere POS
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53314 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- Oct 29, 2015 6:31:27 PM VET
-- Add Changes and translation ADempiere POS
UPDATE AD_Message_Trl SET MsgText='Tipo de Documento Alternativo',Updated=TO_DATE('2015-10-29 18:31:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Message_ID=53314 AND AD_Language='es_MX'
;

