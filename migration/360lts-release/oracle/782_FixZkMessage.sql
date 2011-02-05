-- Dec 16, 2010 1:51:49 PM COT
-- Msg.getMsg: NOT found: Please save changes before closing
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53126,0,TO_DATE('2010-12-16 13:51:48','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Please save changes before closing','I',TO_DATE('2010-12-16 13:51:48','YYYY-MM-DD HH24:MI:SS'),100,'SaveBeforeClose')
;

-- Dec 16, 2010 1:51:49 PM COT
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53126 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- Dec 16, 2010 1:52:09 PM COT
UPDATE AD_Message_Trl SET IsTranslated='Y',MsgText='Por favor guarde los cambios antes de cerrar la ventana',Updated=TO_DATE('2010-12-16 13:52:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Message_ID=53126 AND AD_Language LIKE 'es_%'
;

-- 16-dic-2010 14:08:27 COT
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53127,0,TO_DATE('2010-12-16 14:08:26','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Credit','I',TO_DATE('2010-12-16 14:08:26','YYYY-MM-DD HH24:MI:SS'),100,'Credits')
;

-- 16-dic-2010 14:08:27 COT
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53127 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- 16-dic-2010 14:08:34 COT
UPDATE AD_Message_Trl SET IsTranslated='Y',MsgText='Créditos',Updated=TO_DATE('2010-12-16 14:08:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Message_ID=53127 AND AD_Language LIKE 'es_%'
;

-- 16-dic-2010 14:33:48 COT
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgTip,MsgType,Updated,UpdatedBy,Value) VALUES (0,53128,0,TO_DATE('2010-12-16 14:33:47','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Date/Time',' ','I',TO_DATE('2010-12-16 14:33:47','YYYY-MM-DD HH24:MI:SS'),100,'DateTime')
;

-- 16-dic-2010 14:33:48 COT
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53128 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- 16-dic-2010 14:33:56 COT
UPDATE AD_Message_Trl SET IsTranslated='Y',MsgText='Fecha/Hora',Updated=TO_DATE('2010-12-16 14:33:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Message_ID=53128 AND AD_Language LIKE 'es_%'
;

-- 16-dic-2010 14:34:00 COT
UPDATE AD_Message SET MsgTip=NULL,Updated=TO_DATE('2010-12-16 14:34:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Message_ID=53128
;

-- 16-dic-2010 14:34:00 COT
UPDATE AD_Message_Trl SET IsTranslated='N' WHERE AD_Message_ID=53128
;

-- 16-dic-2010 14:34:11 COT
UPDATE AD_Message_Trl SET MsgTip=NULL,Updated=TO_DATE('2010-12-16 14:34:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Message_ID=53128 AND AD_Language LIKE 'es_%'
;

-- 16-dic-2010 14:34:30 COT
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53129,0,TO_DATE('2010-12-16 14:34:29','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Class.Method','I',TO_DATE('2010-12-16 14:34:29','YYYY-MM-DD HH24:MI:SS'),100,'Class.Method')
;

-- 16-dic-2010 14:34:30 COT
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53129 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- 16-dic-2010 14:34:40 COT
UPDATE AD_Message_Trl SET IsTranslated='Y',MsgText='Clase.Método',Updated=TO_DATE('2010-12-16 14:34:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Message_ID=53129 AND AD_Language LIKE 'es_%'
;

-- 16-dic-2010 14:34:56 COT
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53130,0,TO_DATE('2010-12-16 14:34:55','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Message','I',TO_DATE('2010-12-16 14:34:55','YYYY-MM-DD HH24:MI:SS'),100,'Message')
;

-- 16-dic-2010 14:34:56 COT
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53130 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- 16-dic-2010 14:35:01 COT
UPDATE AD_Message_Trl SET IsTranslated='Y',MsgText='Mensaje',Updated=TO_DATE('2010-12-16 14:35:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Message_ID=53130 AND AD_Language LIKE 'es_%'
;

-- 16-dic-2010 14:35:30 COT
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53131,0,TO_DATE('2010-12-16 14:35:30','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Trace','I',TO_DATE('2010-12-16 14:35:30','YYYY-MM-DD HH24:MI:SS'),100,'Trace')
;

-- 16-dic-2010 14:35:30 COT
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53131 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- 16-dic-2010 14:35:35 COT
UPDATE AD_Message_Trl SET IsTranslated='Y',MsgText='Seguimiento',Updated=TO_DATE('2010-12-16 14:35:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Message_ID=53131 AND AD_Language LIKE 'es_%'
;

