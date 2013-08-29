-- Aug 28, 2013 7:49:18 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,67104,55492,0,19,208,'M_Product_Class_ID',TO_TIMESTAMP('2013-08-28 19:48:41','YYYY-MM-DD HH24:MI:SS'),100,'Class of a Product','D',22,'Identifies the Class which this product belongs to','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Product Class',0,TO_TIMESTAMP('2013-08-28 19:48:41','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 28, 2013 7:49:19 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=67104 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 28, 2013 7:49:33 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
ALTER TABLE M_Product ADD COLUMN M_Product_Class_ID NUMERIC(10) DEFAULT NULL 
;

-- Aug 28, 2013 7:50:04 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Column SET AD_Reference_ID=10, AD_Reference_Value_ID=NULL,Updated=TO_TIMESTAMP('2013-08-28 19:50:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=52061
;

-- Aug 28, 2013 7:51:39 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,67105,55494,0,19,208,'M_Product_Group_ID',TO_TIMESTAMP('2013-08-28 19:51:38','YYYY-MM-DD HH24:MI:SS'),100,'Group of a Product','D',22,'Identifies the Group which this product belongs to.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Product Group',0,TO_TIMESTAMP('2013-08-28 19:51:38','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 28, 2013 7:51:39 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=67105 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 28, 2013 7:51:43 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
ALTER TABLE M_Product ADD COLUMN M_Product_Group_ID NUMERIC(10) DEFAULT NULL 
;

-- Aug 28, 2013 7:52:10 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Column SET AD_Reference_ID=10, AD_Reference_Value_ID=NULL,Updated=TO_TIMESTAMP('2013-08-28 19:52:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=52062
;

-- Aug 28, 2013 7:53:07 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
INSERT INTO t_alter_column values('m_product','M_Product_Group_ID','NUMERIC(10)',null,'NULL')
;

-- Aug 28, 2013 7:54:03 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,67106,55490,0,19,208,'M_Product_Classification_ID',TO_TIMESTAMP('2013-08-28 19:54:02','YYYY-MM-DD HH24:MI:SS'),100,'Classification of a Product','D',22,'Identifies the classification which this product belongs to.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Product Classification',0,TO_TIMESTAMP('2013-08-28 19:54:02','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Aug 28, 2013 7:54:03 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=67106 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Aug 28, 2013 7:54:07 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
ALTER TABLE M_Product ADD COLUMN M_Product_Classification_ID NUMERIC(10) DEFAULT NULL 
;

-- Aug 28, 2013 7:54:54 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Column SET AD_Reference_ID=10, AD_Reference_Value_ID=NULL,Updated=TO_TIMESTAMP('2013-08-28 19:54:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=3016
;

-- Aug 28, 2013 7:55:07 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
INSERT INTO t_alter_column values('m_product','M_Product_Classification_ID','NUMERIC(10)',null,'NULL')
;

-- Aug 28, 2013 7:55:36 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,67104,68444,0,180,TO_TIMESTAMP('2013-08-28 19:55:35','YYYY-MM-DD HH24:MI:SS'),100,'Class of a Product',22,'D','Identifies the Class which this product belongs to','Y','Y','Y','N','N','N','N','N','Product Class',TO_TIMESTAMP('2013-08-28 19:55:35','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 28, 2013 7:55:36 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=68444 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 28, 2013 7:55:36 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,67106,68445,0,180,TO_TIMESTAMP('2013-08-28 19:55:36','YYYY-MM-DD HH24:MI:SS'),100,'Classification of a Product',22,'D','Identifies the classification which this product belongs to.','Y','Y','Y','N','N','N','N','N','Product Classification',TO_TIMESTAMP('2013-08-28 19:55:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 28, 2013 7:55:36 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=68445 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 28, 2013 7:55:38 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,67105,68446,0,180,TO_TIMESTAMP('2013-08-28 19:55:36','YYYY-MM-DD HH24:MI:SS'),100,'Group of a Product',22,'D','Identifies the Group which this product belongs to.','Y','Y','Y','N','N','N','N','N','Product Group',TO_TIMESTAMP('2013-08-28 19:55:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 28, 2013 7:55:38 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=68446 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=68446
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=68444
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=68445
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=2097
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=1041
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=3079
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=1025
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=2587
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=5888
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=6129
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=1032
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=1031
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=6841
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=10411
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=1026
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=7646
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=1319
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=1320
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=1321
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=1322
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=3743
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Field_ID=3746
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=3747
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=370,IsDisplayed='Y' WHERE AD_Field_ID=3744
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=380,IsDisplayed='Y' WHERE AD_Field_ID=3745
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=390,IsDisplayed='Y' WHERE AD_Field_ID=1027
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=400,IsDisplayed='Y' WHERE AD_Field_ID=1028
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=410,IsDisplayed='Y' WHERE AD_Field_ID=1568
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=420,IsDisplayed='Y' WHERE AD_Field_ID=1569
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=430,IsDisplayed='Y' WHERE AD_Field_ID=5381
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=440,IsDisplayed='Y' WHERE AD_Field_ID=5383
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=450,IsDisplayed='Y' WHERE AD_Field_ID=9286
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=460,IsDisplayed='Y' WHERE AD_Field_ID=12418
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=470,IsDisplayed='Y' WHERE AD_Field_ID=5910
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=480,IsDisplayed='Y' WHERE AD_Field_ID=5911
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=490,IsDisplayed='Y' WHERE AD_Field_ID=6130
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=500,IsDisplayed='Y' WHERE AD_Field_ID=8307
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=510,IsDisplayed='Y' WHERE AD_Field_ID=6343
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=520,IsDisplayed='Y' WHERE AD_Field_ID=6344
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=530,IsDisplayed='Y' WHERE AD_Field_ID=8608
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=540,IsDisplayed='Y' WHERE AD_Field_ID=8613
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=550,IsDisplayed='Y' WHERE AD_Field_ID=52015
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=560,IsDisplayed='Y' WHERE AD_Field_ID=52016
;

-- Aug 28, 2013 7:56:41 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=570,IsDisplayed='Y' WHERE AD_Field_ID=58973
;

-- Aug 28, 2013 7:57:00 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2013-08-28 19:57:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=68446
;

-- Aug 28, 2013 7:57:10 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2013-08-28 19:57:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=68445
;

-- Aug 28, 2013 7:57:13 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET IsSameLine='N',Updated=TO_TIMESTAMP('2013-08-28 19:57:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=2097
;

-- Aug 28, 2013 8:01:48 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Element SET Name='Group 1', PrintName='Group 1',Updated=TO_TIMESTAMP('2013-08-28 20:01:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=52018
;

-- Aug 28, 2013 8:01:48 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=52018
;

-- Aug 28, 2013 8:01:48 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Column SET ColumnName='Group1', Name='Group 1', Description=NULL, Help=NULL WHERE AD_Element_ID=52018
;

-- Aug 28, 2013 8:01:48 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Process_Para SET ColumnName='Group1', Name='Group 1', Description=NULL, Help=NULL, AD_Element_ID=52018 WHERE UPPER(ColumnName)='GROUP1' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Aug 28, 2013 8:01:49 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Process_Para SET ColumnName='Group1', Name='Group 1', Description=NULL, Help=NULL WHERE AD_Element_ID=52018 AND IsCentrallyMaintained='Y'
;

-- Aug 28, 2013 8:01:49 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET Name='Group 1', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=52018) AND IsCentrallyMaintained='Y'
;

-- Aug 28, 2013 8:01:49 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_PrintFormatItem SET PrintName='Group 1', Name='Group 1' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=52018)
;

-- Aug 28, 2013 8:02:25 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Element SET Name='Group 2', PrintName='Group 2',Updated=TO_TIMESTAMP('2013-08-28 20:02:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=52019
;

-- Aug 28, 2013 8:02:25 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=52019
;

-- Aug 28, 2013 8:02:25 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Column SET ColumnName='Group2', Name='Group 2', Description=NULL, Help=NULL WHERE AD_Element_ID=52019
;

-- Aug 28, 2013 8:02:25 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Process_Para SET ColumnName='Group2', Name='Group 2', Description=NULL, Help=NULL, AD_Element_ID=52019 WHERE UPPER(ColumnName)='GROUP2' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Aug 28, 2013 8:02:25 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Process_Para SET ColumnName='Group2', Name='Group 2', Description=NULL, Help=NULL WHERE AD_Element_ID=52019 AND IsCentrallyMaintained='Y'
;

-- Aug 28, 2013 8:02:25 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET Name='Group 2', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=52019) AND IsCentrallyMaintained='Y'
;

-- Aug 28, 2013 8:02:25 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_PrintFormatItem SET PrintName='Group 2', Name='Group 2' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=52019)
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=1041
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=2097
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=52015
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=52016
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=3079
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=1025
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=2587
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=5888
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=6129
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=1032
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=1031
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=6841
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=10411
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=1026
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=7646
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=1319
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=1320
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=1321
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Field_ID=1322
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=3743
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=370,IsDisplayed='Y' WHERE AD_Field_ID=3746
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=380,IsDisplayed='Y' WHERE AD_Field_ID=3747
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=390,IsDisplayed='Y' WHERE AD_Field_ID=3744
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=400,IsDisplayed='Y' WHERE AD_Field_ID=3745
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=410,IsDisplayed='Y' WHERE AD_Field_ID=1027
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=420,IsDisplayed='Y' WHERE AD_Field_ID=1028
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=430,IsDisplayed='Y' WHERE AD_Field_ID=1568
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=440,IsDisplayed='Y' WHERE AD_Field_ID=1569
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=450,IsDisplayed='Y' WHERE AD_Field_ID=5381
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=460,IsDisplayed='Y' WHERE AD_Field_ID=5383
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=470,IsDisplayed='Y' WHERE AD_Field_ID=9286
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=480,IsDisplayed='Y' WHERE AD_Field_ID=12418
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=490,IsDisplayed='Y' WHERE AD_Field_ID=5910
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=500,IsDisplayed='Y' WHERE AD_Field_ID=5911
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=510,IsDisplayed='Y' WHERE AD_Field_ID=6130
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=520,IsDisplayed='Y' WHERE AD_Field_ID=8307
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=530,IsDisplayed='Y' WHERE AD_Field_ID=6343
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=540,IsDisplayed='Y' WHERE AD_Field_ID=6344
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=550,IsDisplayed='Y' WHERE AD_Field_ID=8608
;

-- Aug 28, 2013 8:04:06 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET SeqNo=560,IsDisplayed='Y' WHERE AD_Field_ID=8613
;

-- Aug 28, 2013 8:04:22 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2013-08-28 20:04:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=2097
;

-- Aug 28, 2013 8:04:27 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2013-08-28 20:04:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=52016
;

-- Aug 28, 2013 8:16:28 PM CDT
-- feature ADEMPIERE-101-Data-Entity-for-Classification-Group1-Group2
UPDATE AD_Field SET DisplayLength=29,Updated=TO_TIMESTAMP('2013-08-28 20:16:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=52015
;

