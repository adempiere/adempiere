-- Feb 19, 2010 5:22:38 PM MYT
-- adding cron4j to the Adempiere scheduler - ID: 2950261
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54123,0,'IsIgnoreProcessingTime',TO_DATE('2010-02-19 17:22:35','YYYY-MM-DD HH24:MI:SS'),100,'Do not include processing time for the DateNextRun calculation','D','When this is selected, the previous DateNextRun is always use as the source for the next DateNextRun calculation.','Y','Ignore Processing Time','Ignore Proccessing Time',TO_DATE('2010-02-19 17:22:35','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 19, 2010 5:22:38 PM MYT
-- adding cron4j to the Adempiere scheduler - ID: 2950261
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54123 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Feb 19, 2010 5:26:11 PM MYT
-- adding cron4j to the Adempiere scheduler - ID: 2950261
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54124,0,'CronPattern',TO_DATE('2010-02-19 17:26:04','YYYY-MM-DD HH24:MI:SS'),100,'Cron pattern to define when the process should be invoked.','D','Cron pattern to define when the process should be invoked. See http://en.wikipedia.org/wiki/Cron#crontab_syntax for cron scheduling syntax and example.','Y','Cron Scheduling Pattern','Cron Scheduling Pattern',TO_DATE('2010-02-19 17:26:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 19, 2010 5:26:11 PM MYT
-- adding cron4j to the Adempiere scheduler - ID: 2950261
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54124 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Feb 19, 2010 5:29:11 PM MYT
-- adding cron4j to the Adempiere scheduler - ID: 2950261
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59028,54123,0,20,688,'IsIgnoreProcessingTime',TO_DATE('2010-02-19 17:29:09','YYYY-MM-DD HH24:MI:SS'),100,'N','Do not include processing time for the DateNextRun calculation','D',1,'When this is selected, the previous DateNextRun is always use as the source for the next DateNextRun calculation.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Ignore Processing Time',0,TO_DATE('2010-02-19 17:29:09','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Feb 19, 2010 5:29:11 PM MYT
-- adding cron4j to the Adempiere scheduler - ID: 2950261
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59028 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 19, 2010 5:29:16 PM MYT
-- adding cron4j to the Adempiere scheduler - ID: 2950261
ALTER TABLE AD_Scheduler ADD IsIgnoreProcessingTime CHAR(1) DEFAULT 'N' CHECK (IsIgnoreProcessingTime IN ('Y','N'))
;

-- Feb 19, 2010 5:46:42 PM MYT
-- adding cron4j to the Adempiere scheduler - ID: 2950261
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59029,54124,0,10,688,'CronPattern',TO_DATE('2010-02-19 17:46:38','YYYY-MM-DD HH24:MI:SS'),100,'Cron pattern to define when the process should be invoked.','D',255,'Cron pattern to define when the process should be invoked. See http://en.wikipedia.org/wiki/Cron#crontab_syntax for cron scheduling syntax and example.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Cron Scheduling Pattern',0,TO_DATE('2010-02-19 17:46:38','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Feb 19, 2010 5:46:42 PM MYT
-- adding cron4j to the Adempiere scheduler - ID: 2950261
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59029 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 19, 2010 5:46:46 PM MYT
-- adding cron4j to the Adempiere scheduler - ID: 2950261
ALTER TABLE AD_Scheduler ADD CronPattern NVARCHAR2(255) DEFAULT NULL 
;

-- Feb 19, 2010 5:47:11 PM MYT
-- adding cron4j to the Adempiere scheduler - ID: 2950261
UPDATE AD_Column SET IsMandatory='N',Updated=TO_DATE('2010-02-19 17:47:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=11247
;

-- Feb 19, 2010 5:47:18 PM MYT
-- adding cron4j to the Adempiere scheduler - ID: 2950261
UPDATE AD_Column SET IsMandatory='N',Updated=TO_DATE('2010-02-19 17:47:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=11255
;

-- Feb 19, 2010 5:48:07 PM MYT
-- adding cron4j to the Adempiere scheduler - ID: 2950261
UPDATE AD_Ref_List SET IsActive='N',Updated=TO_DATE('2010-02-19 17:48:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=665
;

-- Feb 19, 2010 5:48:10 PM MYT
-- adding cron4j to the Adempiere scheduler - ID: 2950261
UPDATE AD_Ref_List SET IsActive='N',Updated=TO_DATE('2010-02-19 17:48:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=664
;

-- Feb 19, 2010 5:49:04 PM MYT
-- adding cron4j to the Adempiere scheduler - ID: 2950261
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Reference_ID,AD_Ref_List_ID,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,318,53574,TO_DATE('2010-02-19 17:49:02','YYYY-MM-DD HH24:MI:SS'),100,'Use cron style scheduling pattern','D','Y','Cron Scheduling Pattern',TO_DATE('2010-02-19 17:49:02','YYYY-MM-DD HH24:MI:SS'),100,'C')
;

-- Feb 19, 2010 5:49:04 PM MYT
-- adding cron4j to the Adempiere scheduler - ID: 2950261
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53574 AND NOT EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Ref_List_ID=t.AD_Ref_List_ID)
;

-- Feb 19, 2010 5:53:21 PM MYT
-- adding cron4j to the Adempiere scheduler - ID: 2950261
UPDATE AD_Field SET IsActive='N',Updated=TO_DATE('2010-02-19 17:53:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=10053
;

-- Feb 19, 2010 5:53:26 PM MYT
-- adding cron4j to the Adempiere scheduler - ID: 2950261
UPDATE AD_Field SET IsActive='N',Updated=TO_DATE('2010-02-19 17:53:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=10052
;

-- Feb 19, 2010 5:54:51 PM MYT
-- adding cron4j to the Adempiere scheduler - ID: 2950261
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59028,58773,0,589,TO_DATE('2010-02-19 17:54:49','YYYY-MM-DD HH24:MI:SS'),100,'Do not include processing time for the DateNextRun calculation',10,'@ScheduleType@=F','D','When this is selected, the previous DateNextRun is always use as the source for the next DateNextRun calculation.','Y','Y','Y','N','N','N','N','N','Ignore Processing Time',160,0,TO_DATE('2010-02-19 17:54:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 19, 2010 5:54:51 PM MYT
-- adding cron4j to the Adempiere scheduler - ID: 2950261
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58773 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 19, 2010 5:55:49 PM MYT
-- adding cron4j to the Adempiere scheduler - ID: 2950261
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsMandatory,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59029,58774,0,589,TO_DATE('2010-02-19 17:55:48','YYYY-MM-DD HH24:MI:SS'),100,'Cron pattern to define when the process should be invoked.',60,'@ScheduleType@=C','D','Cron pattern to define when the process should be invoked. See http://en.wikipedia.org/wiki/Cron#crontab_syntax for cron scheduling syntax and example.','Y','Y','Y','N','N','N','Y','N','N','Cron Scheduling Pattern',170,0,TO_DATE('2010-02-19 17:55:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 19, 2010 5:55:49 PM MYT
-- adding cron4j to the Adempiere scheduler - ID: 2950261
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58774 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 19, 2010 5:57:03 PM MYT
-- adding cron4j to the Adempiere scheduler - ID: 2950261
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=10053
;

-- Feb 19, 2010 5:57:03 PM MYT
-- adding cron4j to the Adempiere scheduler - ID: 2950261
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=10052
;

-- Feb 19, 2010 5:57:03 PM MYT
-- adding cron4j to the Adempiere scheduler - ID: 2950261
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=9437
;

-- Feb 19, 2010 5:57:03 PM MYT
-- adding cron4j to the Adempiere scheduler - ID: 2950261
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=9430
;

-- Feb 19, 2010 5:57:03 PM MYT
-- adding cron4j to the Adempiere scheduler - ID: 2950261
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=58773
;

-- Feb 19, 2010 5:57:03 PM MYT
-- adding cron4j to the Adempiere scheduler - ID: 2950261
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=58774
;

-- Feb 19, 2010 5:57:21 PM MYT
-- adding cron4j to the Adempiere scheduler - ID: 2950261
UPDATE AD_Field SET IsMandatory='Y',Updated=TO_DATE('2010-02-19 17:57:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=9430
;

-- Feb 19, 2010 5:57:28 PM MYT
-- adding cron4j to the Adempiere scheduler - ID: 2950261
UPDATE AD_Field SET IsMandatory='Y',Updated=TO_DATE('2010-02-19 17:57:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=9437
;

-- Feb 22, 2010 9:31:45 AM MYT
-- adding cron4j to the Adempiere scheduler - ID: 2950261
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgTip,MsgType,Updated,UpdatedBy,Value) VALUES (0,53095,0,TO_DATE('2010-02-22 09:31:41','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Invalid cron scheduling pattern','Invalid cron scheduling pattern - check syntax','E',TO_DATE('2010-02-22 09:31:41','YYYY-MM-DD HH24:MI:SS'),100,'InvalidCronPattern')
;

-- Feb 22, 2010 9:31:45 AM MYT
-- adding cron4j to the Adempiere scheduler - ID: 2950261
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53095 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

