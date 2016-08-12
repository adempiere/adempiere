-- Nov 5, 2015 1:35:40 AM VET
-- Add Changes and translation ADempiere POS
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53315,0,TO_DATE('2015-11-05 01:35:38','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Order is not Drafted nor Completed. Try to delete it other way','I',TO_DATE('2015-11-05 01:35:38','YYYY-MM-DD HH24:MI:SS'),100,'POS.OrderIsNotProcessed')
;

-- Nov 5, 2015 1:35:40 AM VET
-- Add Changes and translation ADempiere POS
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53315 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- Nov 5, 2015 1:36:44 AM VET
-- Add Changes and translation ADempiere POS
UPDATE AD_Message_Trl SET MsgText='La Orden no está completa ni en borrador, intente por otro medio',Updated=TO_DATE('2015-11-05 01:36:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Message_ID=53315 AND AD_Language='es_MX'
;

