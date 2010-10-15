-- Aug 16, 2010 12:01:58 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54264,0,'DatePromisedPrecision',TO_DATE('2010-08-16 12:01:55','YYYY-MM-DD HH24:MI:SS'),100,'Describes how exact the promised date is (date, week, part of month, month or unknown)','D','Y','Date Promised Precision','Date Promised Precision',TO_DATE('2010-08-16 12:01:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 16, 2010 12:01:58 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54264 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Aug 16, 2010 12:02:58 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54265,0,'DatePromisedUpdated',TO_DATE('2010-08-16 12:02:56','YYYY-MM-DD HH24:MI:SS'),100,'The date/time when the date promised date and/or date promised precision was updated.','D','Y','Date Promised Updated','Date Promised Updated',TO_DATE('2010-08-16 12:02:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 16, 2010 12:02:58 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54265 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Aug 16, 2010 12:05:07 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53369,TO_DATE('2010-08-16 12:05:02','YYYY-MM-DD HH24:MI:SS'),100,'List of date precision.','D','Describes how sure a certain date is (on date, in week, part of month, in month)','Y','N','DatePrecision',TO_DATE('2010-08-16 12:05:02','YYYY-MM-DD HH24:MI:SS'),100,'L')
;

-- Aug 16, 2010 12:05:07 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53369 AND NOT EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Reference_ID=t.AD_Reference_ID)
;

-- Aug 16, 2010 12:05:42 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Reference_ID,AD_Ref_List_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53369,53619,TO_DATE('2010-08-16 12:05:36','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','On date',TO_DATE('2010-08-16 12:05:36','YYYY-MM-DD HH24:MI:SS'),100,'D')
;

-- Aug 16, 2010 12:05:42 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53619 AND NOT EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Ref_List_ID=t.AD_Ref_List_ID)
;

-- Aug 16, 2010 12:05:58 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Reference_ID,AD_Ref_List_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53369,53620,TO_DATE('2010-08-16 12:05:53','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Week',TO_DATE('2010-08-16 12:05:53','YYYY-MM-DD HH24:MI:SS'),100,'W')
;

-- Aug 16, 2010 12:05:58 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53620 AND NOT EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Ref_List_ID=t.AD_Ref_List_ID)
;

-- Aug 16, 2010 12:06:27 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Reference_ID,AD_Ref_List_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53369,53621,TO_DATE('2010-08-16 12:06:25','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Part of month',TO_DATE('2010-08-16 12:06:25','YYYY-MM-DD HH24:MI:SS'),100,'P')
;

-- Aug 16, 2010 12:06:27 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53621 AND NOT EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Ref_List_ID=t.AD_Ref_List_ID)
;

-- Aug 16, 2010 12:06:41 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Reference_ID,AD_Ref_List_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53369,53622,TO_DATE('2010-08-16 12:06:40','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Month',TO_DATE('2010-08-16 12:06:40','YYYY-MM-DD HH24:MI:SS'),100,'M')
;

-- Aug 16, 2010 12:06:41 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53622 AND NOT EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Ref_List_ID=t.AD_Ref_List_ID)
;

-- Aug 16, 2010 12:06:53 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Reference_ID,AD_Ref_List_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53369,53623,TO_DATE('2010-08-16 12:06:53','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Unknown',TO_DATE('2010-08-16 12:06:53','YYYY-MM-DD HH24:MI:SS'),100,'U')
;

-- Aug 16, 2010 12:06:53 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53623 AND NOT EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Ref_List_ID=t.AD_Ref_List_ID)
;

-- Aug 16, 2010 12:10:07 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59663,54264,0,17,53369,260,'DatePromisedPrecision',TO_DATE('2010-08-16 12:10:06','YYYY-MM-DD HH24:MI:SS'),100,'D','Describes how exact the promised date is (date, week, part of month, month or unknown)','D',1,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Date Promised Precision',0,TO_DATE('2010-08-16 12:10:06','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Aug 16, 2010 12:10:07 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59663 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 16, 2010 12:10:16 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
ALTER TABLE C_OrderLine ADD DatePromisedPrecision CHAR(1) DEFAULT 'D'
;

-- Aug 16, 2010 12:13:09 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59664,54265,0,16,260,'DatePromisedUpdated',TO_DATE('2010-08-16 12:13:09','YYYY-MM-DD HH24:MI:SS'),100,'The date/time when the date promised date and/or date promised precision was updated.','D',10,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Date Promised Updated',0,TO_DATE('2010-08-16 12:13:09','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Aug 16, 2010 12:13:09 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59664 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 16, 2010 12:13:13 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
ALTER TABLE C_OrderLine ADD DatePromisedUpdated DATE DEFAULT NULL 
;

-- Aug 16, 2010 12:23:11 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59663,59590,0,293,TO_DATE('2010-08-16 12:23:10','YYYY-MM-DD HH24:MI:SS'),100,'Describes how exact the promised date is (date, week, part of month, month or unknown)',0,'D','Y','Y','Y','N','N','N','N','N','Date Promised Precision',350,0,TO_DATE('2010-08-16 12:23:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 16, 2010 12:23:11 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59590 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 16, 2010 12:24:12 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=3404
;

-- Aug 16, 2010 12:24:12 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=3405
;

-- Aug 16, 2010 12:24:12 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=59590
;

-- Aug 16, 2010 12:24:12 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=3388
;

-- Aug 16, 2010 12:24:12 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=3386
;

-- Aug 16, 2010 12:24:12 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=3413
;

-- Aug 16, 2010 12:24:12 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=6535
;

-- Aug 16, 2010 12:24:12 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=3398
;

-- Aug 16, 2010 12:24:12 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=10826
;

-- Aug 16, 2010 12:24:12 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=3387
;

-- Aug 16, 2010 12:24:12 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=3389
;

-- Aug 16, 2010 12:24:12 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=3391
;

-- Aug 16, 2010 12:24:12 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=3390
;

-- Aug 16, 2010 12:24:12 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=3392
;

-- Aug 16, 2010 12:24:12 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=3393
;

-- Aug 16, 2010 12:24:12 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=10827
;

-- Aug 16, 2010 12:24:12 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=12173
;

-- Aug 16, 2010 12:24:12 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=3396
;

-- Aug 16, 2010 12:24:12 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=3395
;

-- Aug 16, 2010 12:24:12 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=3412
;

-- Aug 16, 2010 12:24:12 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=3397
;

-- Aug 16, 2010 12:24:12 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=3383
;

-- Aug 16, 2010 12:24:12 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=12174
;

-- Aug 16, 2010 12:24:12 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=13652
;

-- Aug 16, 2010 12:24:12 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=13653
;

-- Aug 16, 2010 12:24:12 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=13690
;

-- Aug 16, 2010 12:24:12 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=13658
;

-- Aug 16, 2010 12:24:12 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=13659
;

-- Aug 16, 2010 12:24:12 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Field_ID=3416
;

-- Aug 16, 2010 12:24:26 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2010-08-16 12:24:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=3404
;

-- Aug 16, 2010 12:24:34 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2010-08-16 12:24:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=3405
;

-- Aug 16, 2010 12:24:44 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2010-08-16 12:24:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59590
;

-- Aug 16, 2010 12:25:09 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2010-08-16 12:25:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=3388
;

-- Aug 16, 2010 12:26:39 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Field SET IsFieldOnly='Y',Updated=TO_DATE('2010-08-16 12:26:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59590
;

-- Aug 16, 2010 12:27:51 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Field SET IsFieldOnly='N',Updated=TO_DATE('2010-08-16 12:27:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59590
;

-- Aug 20, 2010 11:23:04 AM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Column SET IsAlwaysUpdateable='Y',Updated=TO_DATE('2010-08-20 11:23:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=59663
;

-- Aug 20, 2010 11:23:13 AM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Column SET IsAlwaysUpdateable='Y',Updated=TO_DATE('2010-08-20 11:23:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=2217
;

-- Aug 20, 2010 11:51:29 AM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Column SET Callout='org.compiere.model.CalloutOrderLine.datePromised',Updated=TO_DATE('2010-08-20 11:51:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=2217
;

-- Aug 20, 2010 11:51:42 AM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_Column SET Callout='org.compiere.model.CalloutOrderLine.datePromised',Updated=TO_DATE('2010-08-20 11:51:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=59663
;

-- Aug 20, 2010 12:40:28 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59664,59593,0,293,TO_DATE('2010-08-20 12:40:26','YYYY-MM-DD HH24:MI:SS'),100,'The date/time when the date promised date and/or date promised precision was updated.',0,'D','Y','Y','N','N','N','N','N','N','Date Promised Updated',360,0,TO_DATE('2010-08-20 12:40:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2010 12:40:28 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59593 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 20, 2010 12:44:14 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59663,59594,0,187,TO_DATE('2010-08-20 12:44:12','YYYY-MM-DD HH24:MI:SS'),100,'Describes how exact the promised date is (date, week, part of month, month or unknown)',0,'D','Y','Y','N','N','N','N','N','N','Date Promised Precision',380,0,TO_DATE('2010-08-20 12:44:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2010 12:44:14 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59594 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 20, 2010 12:44:45 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59664,59595,0,187,TO_DATE('2010-08-20 12:44:44','YYYY-MM-DD HH24:MI:SS'),100,'The date/time when the date promised date and/or date promised precision was updated.',0,'D','Y','Y','N','N','N','N','N','N','Date Promised Updated',390,0,TO_DATE('2010-08-20 12:44:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2010 12:44:45 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59595 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 20, 2010 2:04:16 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_ModelValidator (AD_Client_ID,AD_ModelValidator_ID,AD_Org_ID,Created,CreatedBy,Description,EntityType,IsActive,ModelValidationClass,Name,SeqNo,Updated,UpdatedBy) VALUES (0,50005,0,TO_DATE('2010-08-20 14:04:12','YYYY-MM-DD HH24:MI:SS'),100,'Default validator for order lines','D','Y','org.adempiere.validator.OrderLineValidator','Order Line Validator',0,TO_DATE('2010-08-20 14:04:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2010 2:17:42 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53107,0,TO_DATE('2010-08-20 14:17:41','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','You have changed the promised date / precision of this
purchase order line. Do you want the system to update the sales 
order lines that depend on this purchase order line?
If you answer "no" at this stage, you can run the process 
"Update Date Promised" manually later.','I',TO_DATE('2010-08-20 14:17:41','YYYY-MM-DD HH24:MI:SS'),100,'DatePromisedUpdatedQuestion')
;

-- Aug 20, 2010 2:17:42 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53107 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- Aug 20, 2010 3:08:45 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Process (AccessLevel,AD_Client_ID,AD_Org_ID,AD_Process_ID,Classname,CopyFromProcess,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,IsServerProcess,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value) VALUES ('1',0,0,53219,'org.adempiere.process.DatePromisedUpdateProcess','N',TO_DATE('2010-08-20 15:08:42','YYYY-MM-DD HH24:MI:SS'),100,'Updates date promised and date precision on a product and supplier.','D','Updates date promised and date precision on a specific product and a
specific supplier (business partner). Purchase orders from the
specific BP is updated and depending sales orders are also updated.','Y','N','N','N','N','Update Date Promised','Y',0,0,TO_DATE('2010-08-20 15:08:42','YYYY-MM-DD HH24:MI:SS'),100,'UpdateDatePromised')
;

-- Aug 20, 2010 3:08:45 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53219 AND NOT EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_ID=t.AD_Process_ID)
;

-- Aug 20, 2010 3:09:52 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,187,0,53219,53424,30,'C_BPartner_ID',TO_DATE('2010-08-20 15:09:51','YYYY-MM-DD HH24:MI:SS'),100,'D',0,'Y','Y','Y','N','Business Partner (Vendor/Supplier)',10,TO_DATE('2010-08-20 15:09:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2010 3:09:52 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53424 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Aug 20, 2010 3:10:17 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,454,0,53219,53425,30,'M_Product_ID',TO_DATE('2010-08-20 15:10:16','YYYY-MM-DD HH24:MI:SS'),100,'D',0,'Y','Y','Y','N','Product',20,TO_DATE('2010-08-20 15:10:16','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2010 3:10:17 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53425 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Aug 20, 2010 3:10:55 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,269,0,53219,53426,15,'DatePromised',TO_DATE('2010-08-20 15:10:55','YYYY-MM-DD HH24:MI:SS'),100,'New date promised for the product from this supplier.','D',0,'Y','Y','Y','N','Date Promised',30,TO_DATE('2010-08-20 15:10:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2010 3:10:55 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53426 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Aug 20, 2010 3:11:45 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,0,53219,53427,17,53369,'DatePrecision',TO_DATE('2010-08-20 15:11:42','YYYY-MM-DD HH24:MI:SS'),100,'D','Date precision for this date','D',0,'Y','Y','Y','N','Date Precision',40,TO_DATE('2010-08-20 15:11:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2010 3:11:45 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53427 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Aug 20, 2010 3:12:39 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,526,0,53219,53428,22,'Qty',TO_DATE('2010-08-20 15:12:38','YYYY-MM-DD HH24:MI:SS'),100,'Number of items promised on the given date','D',10,'Y','Y','Y','N','Quantity',50,TO_DATE('2010-08-20 15:12:38','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2010 3:12:39 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53428 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Aug 20, 2010 3:14:39 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Menu (Action,AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Process_ID,Created,CreatedBy,Description,EntityType,IsActive,IsCentrallyMaintained,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES ('P',0,53286,0,53219,TO_DATE('2010-08-20 15:14:38','YYYY-MM-DD HH24:MI:SS'),100,'Update Date Promised on a specific product','D','Y','Y','N','N','N','Update Date Promised',TO_DATE('2010-08-20 15:14:38','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 20, 2010 3:14:39 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53286 AND NOT EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Menu_ID=t.AD_Menu_ID)
;

-- Aug 20, 2010 3:14:39 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID, 0, 'Y', SysDate, 100, SysDate, 100,t.AD_Tree_ID, 53286, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53286)
;

-- Aug 20, 2010 3:14:49 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_TreeNodeMM SET Parent_ID=203, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=452
;

-- Aug 20, 2010 3:14:49 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_TreeNodeMM SET Parent_ID=203, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=454
;

-- Aug 20, 2010 3:14:49 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_TreeNodeMM SET Parent_ID=203, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=466
;

-- Aug 20, 2010 3:14:49 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_TreeNodeMM SET Parent_ID=203, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=468
;

-- Aug 20, 2010 3:14:49 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_TreeNodeMM SET Parent_ID=203, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=467
;

-- Aug 20, 2010 3:14:49 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_TreeNodeMM SET Parent_ID=203, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=463
;

-- Aug 20, 2010 3:14:49 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_TreeNodeMM SET Parent_ID=203, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=549
;

-- Aug 20, 2010 3:14:49 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_TreeNodeMM SET Parent_ID=203, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=471
;

-- Aug 20, 2010 3:14:49 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_TreeNodeMM SET Parent_ID=203, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=205
;

-- Aug 20, 2010 3:14:49 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_TreeNodeMM SET Parent_ID=203, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=204
;

-- Aug 20, 2010 3:14:49 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_TreeNodeMM SET Parent_ID=203, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53286
;

-- Aug 20, 2010 3:14:49 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_TreeNodeMM SET Parent_ID=203, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=493
;

-- Aug 20, 2010 3:14:49 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_TreeNodeMM SET Parent_ID=203, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=206
;

-- Aug 20, 2010 3:14:49 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_TreeNodeMM SET Parent_ID=203, SeqNo=13, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=360
;

-- Aug 20, 2010 3:14:49 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_TreeNodeMM SET Parent_ID=203, SeqNo=14, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=516
;

-- Aug 20, 2010 3:14:49 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_TreeNodeMM SET Parent_ID=203, SeqNo=15, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=312
;

-- Aug 20, 2010 3:14:49 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_TreeNodeMM SET Parent_ID=203, SeqNo=16, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=316
;

-- Aug 20, 2010 3:14:49 PM CEST
-- FR 3046106 - Promised date precision on purchase orders
UPDATE AD_TreeNodeMM SET Parent_ID=203, SeqNo=17, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=315
;

-- Oct 14, 2010 2:28:10 PM CEST
-- FR 3036106 Promised Date
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53114,0,TO_DATE('2010-10-14 14:28:07','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Insufficient permissions. Try running Role Access Update. 
This will solve the problem if you''re trying to access a new feature.','I',TO_DATE('2010-10-14 14:28:07','YYYY-MM-DD HH24:MI:SS'),100,'PleaseRunRoleAccessUpdate')
;

-- Oct 14, 2010 2:28:10 PM CEST
-- FR 3036106 Promised Date
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53114 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

