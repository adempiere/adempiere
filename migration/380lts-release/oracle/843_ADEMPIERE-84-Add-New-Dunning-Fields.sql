-- May 3, 2012 6:20:47 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,63010,3092,0,17,394,598,'InvoiceCollectionType',TO_DATE('2012-05-03 18:19:56','YYYY-MM-DD HH24:MI:SS'),0,'Invoice Collection Status','D',1,'Status of the invoice collection process','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Collection Status',0,TO_DATE('2012-05-03 18:19:56','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 3, 2012 6:20:47 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63010 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 3, 2012 6:20:53 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
ALTER TABLE I_Invoice ADD InvoiceCollectionType CHAR(1) DEFAULT NULL 
;

-- May 3, 2012 6:22:55 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,63011,53223,0,15,598,'DunningGrace',TO_DATE('2012-05-03 18:22:48','YYYY-MM-DD HH24:MI:SS'),0,'D',7,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Dunning Grace Date',0,TO_DATE('2012-05-03 18:22:48','YYYY-MM-DD HH24:MI:SS'),0,1.000000000000)
;

-- May 3, 2012 6:22:55 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63011 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 3, 2012 6:23:07 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
ALTER TABLE I_Invoice ADD DunningGrace DATE DEFAULT NULL 
;

-- May 3, 2012 6:24:35 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,63012,1075,0,19,598,'C_DunningLevel_ID',TO_DATE('2012-05-03 18:24:33','YYYY-MM-DD HH24:MI:SS'),0,'D',22,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Dunning Level',0,TO_DATE('2012-05-03 18:24:33','YYYY-MM-DD HH24:MI:SS'),0,1.000000000000)
;

-- May 3, 2012 6:24:35 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63012 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 3, 2012 6:24:38 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
ALTER TABLE I_Invoice ADD C_DunningLevel_ID NUMBER(10) DEFAULT NULL 
;

-- May 3, 2012 6:26:48 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,63010,64230,0,510,TO_DATE('2012-05-03 18:26:44','YYYY-MM-DD HH24:MI:SS'),0,'Invoice Collection Status',1,'D','Status of the invoice collection process','Y','Y','Y','N','N','N','N','N','Collection Status',TO_DATE('2012-05-03 18:26:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 3, 2012 6:26:48 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64230 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 3, 2012 6:26:50 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,63011,64231,0,510,TO_DATE('2012-05-03 18:26:48','YYYY-MM-DD HH24:MI:SS'),0,7,'D','Y','Y','Y','N','N','N','N','N','Dunning Grace Date',TO_DATE('2012-05-03 18:26:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 3, 2012 6:26:50 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64231 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 3, 2012 6:26:52 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,63012,64232,0,510,TO_DATE('2012-05-03 18:26:50','YYYY-MM-DD HH24:MI:SS'),0,22,'D','Y','Y','Y','N','N','N','N','N','Dunning Level',TO_DATE('2012-05-03 18:26:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 3, 2012 6:26:52 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64232 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 3, 2012 6:28:12 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2012-05-03 18:28:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=64232
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=7210
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=7231
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=7213
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=7242
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=7245
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=7251
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=7226
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=7243
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=7246
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=7238
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=7216
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=7230
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=7248
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=7227
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=7223
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=7234
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=7236
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=7249
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=7221
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=7220
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=7218
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=7219
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=7637
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=7222
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=7229
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=7225
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=7235
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=7214
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=7635
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=7638
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=7634
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=7244
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=7639
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=7636
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=7212
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Field_ID=8261
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=8260
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=370,IsDisplayed='Y' WHERE AD_Field_ID=7240
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=380,IsDisplayed='Y' WHERE AD_Field_ID=7208
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=390,IsDisplayed='Y' WHERE AD_Field_ID=53253
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=400,IsDisplayed='Y' WHERE AD_Field_ID=53254
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=410,IsDisplayed='Y' WHERE AD_Field_ID=7233
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=420,IsDisplayed='Y' WHERE AD_Field_ID=7232
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=430,IsDisplayed='Y' WHERE AD_Field_ID=7207
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=440,IsDisplayed='Y' WHERE AD_Field_ID=7228
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=450,IsDisplayed='Y' WHERE AD_Field_ID=7241
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=460,IsDisplayed='Y' WHERE AD_Field_ID=7215
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=470,IsDisplayed='Y' WHERE AD_Field_ID=7250
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=480,IsDisplayed='Y' WHERE AD_Field_ID=7209
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=490,IsDisplayed='Y' WHERE AD_Field_ID=53252
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=500,IsDisplayed='Y' WHERE AD_Field_ID=7211
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=510,IsDisplayed='Y' WHERE AD_Field_ID=7239
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=520,IsDisplayed='Y' WHERE AD_Field_ID=53251
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=530,IsDisplayed='Y' WHERE AD_Field_ID=7237
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=540,IsDisplayed='Y' WHERE AD_Field_ID=7217
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=550,IsDisplayed='Y' WHERE AD_Field_ID=7247
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=560,IsDisplayed='Y' WHERE AD_Field_ID=64230
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=570,IsDisplayed='Y' WHERE AD_Field_ID=64231
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=580,IsDisplayed='Y' WHERE AD_Field_ID=64232
;

-- May 3, 2012 6:28:54 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=590,IsDisplayed='Y' WHERE AD_Field_ID=7224
;

-- May 3, 2012 6:29:41 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=550,IsDisplayed='Y' WHERE AD_Field_ID=64230
;

-- May 3, 2012 6:29:41 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=560,IsDisplayed='Y' WHERE AD_Field_ID=64231
;

-- May 3, 2012 6:29:41 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=570,IsDisplayed='Y' WHERE AD_Field_ID=64232
;

-- May 3, 2012 6:29:41 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Field SET SeqNo=580,IsDisplayed='Y' WHERE AD_Field_ID=7247
;

-- May 3, 2012 6:34:14 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,3092,0,171,53644,17,'InvoiceCollectionType',TO_DATE('2012-05-03 18:34:11','YYYY-MM-DD HH24:MI:SS'),0,'Invoice Collection Status','D',1,'Status of the invoice collection process','Y','Y','N','N','Collection Status',65,TO_DATE('2012-05-03 18:34:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 3, 2012 6:34:14 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53644 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- May 3, 2012 6:36:36 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,1075,0,171,53645,19,'C_DunningLevel_ID',TO_DATE('2012-05-03 18:36:33','YYYY-MM-DD HH24:MI:SS'),0,'Dunning Level','D',22,'Y','Y','N','N','Dunning Level',66,TO_DATE('2012-05-03 18:36:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 3, 2012 6:36:36 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53645 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- May 3, 2012 6:38:15 PM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Process_Para SET AD_Reference_Value_ID=394,Updated=TO_DATE('2012-05-03 18:38:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53644
;

-- May 4, 2012 10:17:22 AM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Process_Para SET DisplayLogic='@C_Invoice_ID@=0',Updated=TO_DATE('2012-05-04 10:17:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=700
;

-- May 4, 2012 10:17:25 AM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Process_Para SET DisplayLogic='@C_Invoice_ID@=0',Updated=TO_DATE('2012-05-04 10:17:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=220
;

-- May 4, 2012 10:17:32 AM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Process_Para SET DisplayLogic='@C_Invoice_ID@=0',Updated=TO_DATE('2012-05-04 10:17:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53644
;

-- May 4, 2012 10:17:35 AM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Process_Para SET DisplayLogic='@C_Invoice_ID@=0',Updated=TO_DATE('2012-05-04 10:17:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53645
;

-- May 4, 2012 10:23:07 AM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Process_Para SET SeqNo=95,Updated=TO_DATE('2012-05-04 10:23:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=221
;

-- May 4, 2012 10:27:29 AM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Process_Para SET DisplayLogic='@C_Invoice_ID@=0 & @C_BPartner_ID@=0',Updated=TO_DATE('2012-05-04 10:27:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=698
;

-- May 4, 2012 10:30:01 AM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Process_Para SET DisplayLogic='@C_BPartner_ID@=0 & @C_BP_Group_ID@=0',Updated=TO_DATE('2012-05-04 10:30:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=217
;

-- May 4, 2012 10:34:17 AM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Process_Para SET DisplayLogic='@C_Invoice_ID@=0 & @C_BP_Group_ID@=0',Updated=TO_DATE('2012-05-04 10:34:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=216
;

-- May 4, 2012 10:34:42 AM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Process_Para SET DefaultValue='-1',Updated=TO_DATE('2012-05-04 10:34:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=698
;

-- May 4, 2012 10:37:42 AM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Process_Para SET SeqNo=70,Updated=TO_DATE('2012-05-04 10:37:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=222
;

-- May 4, 2012 10:39:15 AM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Process_Para SET DisplayLogic='@IsSimulation@=''N''',Updated=TO_DATE('2012-05-04 10:39:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=701
;

-- May 4, 2012 10:42:55 AM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Process_Para SET SeqNo=25,Updated=TO_DATE('2012-05-04 10:42:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53644
;

-- May 4, 2012 10:42:58 AM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Process_Para SET SeqNo=26,Updated=TO_DATE('2012-05-04 10:42:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53645
;

-- May 4, 2012 10:46:06 AM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Process_Para SET DisplayLogic='@IsSimulation@=''N''',Updated=TO_DATE('2012-05-04 10:46:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=221
;

-- May 4, 2012 11:30:33 AM CDT
-- ADEMPIERE-84-Add-New-Dunning-Fields
UPDATE AD_Process_Para SET DisplayLogic='@IsSimulation@=''N'' & @CreatePayment@=''Y''',Updated=TO_DATE('2012-05-04 11:30:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=699
;
