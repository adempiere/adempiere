-- Apr 23, 2012 1:35:17 PM EDT
-- Adding Messages for Info Panel changes
INSERT INTO AD_SysConfig (AD_Client_ID,AD_Org_ID,AD_SysConfig_ID,ConfigurationLevel,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,1000005,'C',TO_DATE('2012-04-23 13:35:17','YYYY-MM-DD HH24:MI:SS'),0,'Info Windows - is the query performed automatically (Y) or does the user have to click the Refresh button.','U','Y','INFO_AUTO_QUERY',TO_DATE('2012-04-23 13:35:17','YYYY-MM-DD HH24:MI:SS'),0,'N')
;

-- Apr 23, 2012 1:39:31 PM EDT
-- Adding Messages for Info Panel changes
INSERT INTO AD_SysConfig (AD_Client_ID,AD_Org_ID,AD_SysConfig_ID,ConfigurationLevel,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,1000006,'S',TO_DATE('2012-04-23 13:39:31','YYYY-MM-DD HH24:MI:SS'),0,'Determines the wild card pattern to apply to search text as none, first, last or both.  The percent sign "%" is the wildcard.  The astrixs could be any text.  "*%" is the default - last only. * - is none.  %* is first only.  %*%, % or %% is both.','U','Y','INFO_AUTO_WILDCARD',TO_DATE('2012-04-23 13:39:31','YYYY-MM-DD HH24:MI:SS'),0,'*%')
;

-- Apr 23, 2012 1:43:39 PM EDT
-- Adding Messages for Info Panel changes
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgTip,MsgType,Updated,UpdatedBy,Value) VALUES (0,1000001,0,TO_DATE('2012-04-23 13:43:39','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Expected Change','The expected change or delta in the amount.','I',TO_DATE('2012-04-23 13:43:39','YYYY-MM-DD HH24:MI:SS'),0,'ExpectedChange')
;

-- Apr 23, 2012 1:43:39 PM EDT
-- Adding Messages for Info Panel changes
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=1000001 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- Apr 23, 2012 1:45:27 PM EDT
-- Adding Messages for Info Panel changes
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgTip,MsgType,Updated,UpdatedBy,Value) VALUES (0,1000002,0,TO_DATE('2012-04-23 13:45:27','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Only Stock',null,'I',TO_DATE('2012-04-23 13:45:27','YYYY-MM-DD HH24:MI:SS'),0,'OnlyStock')
;

-- Apr 23, 2012 1:45:27 PM EDT
-- Adding Messages for Info Panel changes
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=1000002 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- Apr 23, 2012 1:46:36 PM EDT
-- Adding Messages for Info Panel changes
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgTip,MsgType,Updated,UpdatedBy,Value) VALUES (0,1000003,0,TO_DATE('2012-04-23 13:46:36','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','If selected, only display stocked items. Otherwise, display all items.','If selected, only display stocked items. Otherwise, display all items.','I',TO_DATE('2012-04-23 13:46:36','YYYY-MM-DD HH24:MI:SS'),0,'OnlyStockTip')
;

-- Apr 23, 2012 1:46:36 PM EDT
-- Adding Messages for Info Panel changes
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=1000003 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- Apr 23, 2012 1:48:39 PM EDT
-- Adding Messages for Info Panel changes
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgTip,MsgType,Updated,UpdatedBy,Value) VALUES (0,1000004,0,TO_DATE('2012-04-23 13:48:39','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Auto Refresh',null,'I',TO_DATE('2012-04-23 13:48:39','YYYY-MM-DD HH24:MI:SS'),0,'AutoRefresh')
;

-- Apr 23, 2012 1:48:39 PM EDT
-- Adding Messages for Info Panel changes
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=1000004 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- Apr 23, 2012 1:50:21 PM EDT
-- Adding Messages for Info Panel changes
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgTip,MsgType,Updated,UpdatedBy,Value) VALUES (0,1000005,0,TO_DATE('2012-04-23 13:50:21','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Show Detail',null,'I',TO_DATE('2012-04-23 13:50:21','YYYY-MM-DD HH24:MI:SS'),0,'ShowDetail')
;

-- Apr 23, 2012 1:50:21 PM EDT
-- Adding Messages for Info Panel changes
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=1000005 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- Apr 23, 2012 1:51:22 PM EDT
-- Adding Messages for Info Panel changes
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgTip,MsgType,Updated,UpdatedBy,Value) VALUES (0,1000006,0,TO_DATE('2012-04-23 13:51:22','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Show Attribute Details',null,'I',TO_DATE('2012-04-23 13:51:22','YYYY-MM-DD HH24:MI:SS'),0,'ShowAttributeDetails')
;

-- Apr 23, 2012 1:51:22 PM EDT
-- Adding Messages for Info Panel changes
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=1000006 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- Apr 23, 2012 1:53:35 PM EDT
-- Adding Messages for Info Panel changes
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgTip,MsgType,Updated,UpdatedBy,Value) VALUES (0,1000007,0,TO_DATE('2012-04-23 13:53:35','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Contact and Address Information',null,'I',TO_DATE('2012-04-23 13:53:35','YYYY-MM-DD HH24:MI:SS'),0,'ContactAndAddress')
;

-- Apr 23, 2012 1:53:35 PM EDT
-- Adding Messages for Info Panel changes
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=1000007 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- Apr 23, 2012 2:03:33 PM EDT
-- Adding Messages for Info Panel changes
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgTip,MsgType,Updated,UpdatedBy,Value) VALUES (0,1000008,0,TO_DATE('2012-04-23 14:03:33','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Only Receipts',null,'I',TO_DATE('2012-04-23 14:03:33','YYYY-MM-DD HH24:MI:SS'),0,'OnlyReceipt')
;

-- Apr 23, 2012 2:03:33 PM EDT
-- Adding Messages for Info Panel changes
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=1000008 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- Apr 23, 2012 2:04:26 PM EDT
-- Adding Messages for Info Panel changes
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgTip,MsgType,Updated,UpdatedBy,Value) VALUES (0,1000009,0,TO_DATE('2012-04-23 14:04:26','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Only AP Payments',null,'I',TO_DATE('2012-04-23 14:04:26','YYYY-MM-DD HH24:MI:SS'),0,'OnlyPayment')
;

-- Apr 23, 2012 2:04:26 PM EDT
-- Adding Messages for Info Panel changes
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=1000009 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- Aug 15, 2013 7:23:34 AM EDT
-- Adding Messages for Info Panel changes
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,1000010,0,TO_DATE('2013-08-15 07:23:34','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Received','I',TO_DATE('2013-08-15 07:23:34','YYYY-MM-DD HH24:MI:SS'),0,'Received')
;

-- Aug 15, 2013 7:23:34 AM EDT
-- Adding Messages for Info Panel changes
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=1000010 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;
