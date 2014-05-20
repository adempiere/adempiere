-- May 19, 2014 2:58:19 AM CDT
-- Add Messages ADEMPIERE-7
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgTip,MsgType,Updated,UpdatedBy,Value) VALUES (0,53262,0,TO_DATE('2014-05-19 02:57:35','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Expected Change','The expected change or delta in the amount.','I',TO_DATE('2014-05-19 02:57:35','YYYY-MM-DD HH24:MI:SS'),100,'ExpectedChange')
;

-- May 19, 2014 2:58:19 AM CDT
-- Add Messages ADEMPIERE-7
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53262 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- May 19, 2014 2:59:31 AM CDT
-- Add Messages ADEMPIERE-7
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53263,0,TO_DATE('2014-05-19 02:59:30','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Only Stock','I',TO_DATE('2014-05-19 02:59:30','YYYY-MM-DD HH24:MI:SS'),100,'OnlyStock')
;

-- May 19, 2014 2:59:31 AM CDT
-- Add Messages ADEMPIERE-7
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53263 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- May 19, 2014 3:00:58 AM CDT
-- Add Messages ADEMPIERE-7
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgTip,MsgType,Updated,UpdatedBy,Value) VALUES (0,53264,0,TO_DATE('2014-05-19 03:00:56','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Only Stock Tip','If selected, only display stocked items. Otherwise, display all items.','I',TO_DATE('2014-05-19 03:00:56','YYYY-MM-DD HH24:MI:SS'),100,'OnlyStockTip')
;

-- May 19, 2014 3:00:58 AM CDT
-- Add Messages ADEMPIERE-7
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53264 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- May 19, 2014 3:01:46 AM CDT
-- Add Messages ADEMPIERE-7
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53265,0,TO_DATE('2014-05-19 03:01:46','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Auto Refresh','I',TO_DATE('2014-05-19 03:01:46','YYYY-MM-DD HH24:MI:SS'),100,'AutoRefresh')
;

-- May 19, 2014 3:01:46 AM CDT
-- Add Messages ADEMPIERE-7
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53265 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- May 19, 2014 3:02:22 AM CDT
-- Add Messages ADEMPIERE-7
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53266,0,TO_DATE('2014-05-19 03:02:20','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Show Detail','I',TO_DATE('2014-05-19 03:02:20','YYYY-MM-DD HH24:MI:SS'),100,'ShowDetail')
;

-- May 19, 2014 3:02:22 AM CDT
-- Add Messages ADEMPIERE-7
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53266 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- May 19, 2014 3:02:58 AM CDT
-- Add Messages ADEMPIERE-7
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53267,0,TO_DATE('2014-05-19 03:02:58','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Show Attribute Details','I',TO_DATE('2014-05-19 03:02:58','YYYY-MM-DD HH24:MI:SS'),100,'ShowAttributeDetails')
;

-- May 19, 2014 3:02:58 AM CDT
-- Add Messages ADEMPIERE-7
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53267 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- May 19, 2014 3:04:00 AM CDT
-- Add Messages ADEMPIERE-7
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53268,0,TO_DATE('2014-05-19 03:03:57','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Contact and Address Information','I',TO_DATE('2014-05-19 03:03:57','YYYY-MM-DD HH24:MI:SS'),100,'ContactAndAddress')
;

-- May 19, 2014 3:04:00 AM CDT
-- Add Messages ADEMPIERE-7
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53268 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- May 19, 2014 3:04:38 AM CDT
-- Add Messages ADEMPIERE-7
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53269,0,TO_DATE('2014-05-19 03:04:37','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Only Receipts','I',TO_DATE('2014-05-19 03:04:37','YYYY-MM-DD HH24:MI:SS'),100,'OnlyReceipt')
;

-- May 19, 2014 3:04:38 AM CDT
-- Add Messages ADEMPIERE-7
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53269 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- May 19, 2014 3:05:10 AM CDT
-- Add Messages ADEMPIERE-7
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53270,0,TO_DATE('2014-05-19 03:05:09','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Only AP Payments','I',TO_DATE('2014-05-19 03:05:09','YYYY-MM-DD HH24:MI:SS'),100,'OnlyPayment')
;

-- May 19, 2014 3:05:10 AM CDT
-- Add Messages ADEMPIERE-7
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53270 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- May 19, 2014 3:06:07 AM CDT
-- Add Messages ADEMPIERE-7
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53271,0,TO_DATE('2014-05-19 03:06:06','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Received','I',TO_DATE('2014-05-19 03:06:06','YYYY-MM-DD HH24:MI:SS'),100,'Received')
;

-- May 19, 2014 3:06:07 AM CDT
-- Add Messages ADEMPIERE-7
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53271 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

