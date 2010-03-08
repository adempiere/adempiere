-- Mar 8, 2010 4:20:41 PM CET
-- Import of Order Source
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59066,53942,0,19,591,'C_OrderSource_ID',TO_DATE('2010-03-08 16:20:39','YYYY-MM-DD HH24:MI:SS'),100,'D',10,'Y','N','N','N','N','N','N','N','N','N','N','N','Y','Order Source',0,TO_DATE('2010-03-08 16:20:39','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Mar 8, 2010 4:20:41 PM CET
-- Import of Order Source
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59066 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Mar 8, 2010 4:20:44 PM CET
-- Import of Order Source
ALTER TABLE I_Order ADD C_OrderSource_ID NUMBER(10) DEFAULT NULL 
;

-- Mar 8, 2010 4:23:40 PM CET
-- Import of Order Source
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54129,0,'C_OrderSourceValue',TO_DATE('2010-03-08 16:23:39','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Order Source Key','Order Source Key',TO_DATE('2010-03-08 16:23:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 8, 2010 4:23:40 PM CET
-- Import of Order Source
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54129 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Mar 8, 2010 4:27:20 PM CET
-- Import of Order Source
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59068,54129,0,10,591,'C_OrderSourceValue',TO_DATE('2010-03-08 16:27:19','YYYY-MM-DD HH24:MI:SS'),100,'D',40,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Order Source Key',0,TO_DATE('2010-03-08 16:27:19','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Mar 8, 2010 4:27:20 PM CET
-- Import of Order Source
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59068 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Mar 8, 2010 4:27:27 PM CET
-- Import of Order Source
ALTER TABLE I_Order ADD C_OrderSourceValue NVARCHAR2(40) DEFAULT NULL 
;



-- Mar 8, 2010 4:32:35 PM CET
-- Import of Order Source
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59068,58779,0,512,TO_DATE('2010-03-08 16:32:33','YYYY-MM-DD HH24:MI:SS'),100,0,'D','Y','Y','Y','N','N','N','N','N','Order Source Key',590,0,TO_DATE('2010-03-08 16:32:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 8, 2010 4:32:35 PM CET
-- Import of Order Source
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58779 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Mar 8, 2010 4:33:50 PM CET
-- Import of Order Source
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59066,58780,0,512,TO_DATE('2010-03-08 16:33:47','YYYY-MM-DD HH24:MI:SS'),100,0,'D','Y','Y','Y','N','N','N','N','N','Order Source',600,0,TO_DATE('2010-03-08 16:33:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 8, 2010 4:33:50 PM CET
-- Import of Order Source
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58780 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Mar 8, 2010 4:33:58 PM CET
-- Import of Order Source
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2010-03-08 16:33:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58780
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=7347
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=7334
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=7328
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=7319
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=7323
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=7324
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=7337
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=7315
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=7355
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=7356
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=7338
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=7322
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=7359
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=7336
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=7318
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=7330
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=7339
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=7325
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=7341
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=7346
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=7345
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=7331
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=7317
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=7360
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=7354
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=7340
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=7645
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=7332
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=7353
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=7361
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=7358
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=7350
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=7640
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=7644
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=7641
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=7326
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Field_ID=7642
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=7643
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=370,IsDisplayed='Y' WHERE AD_Field_ID=7343
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=380,IsDisplayed='Y' WHERE AD_Field_ID=8262
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=390,IsDisplayed='Y' WHERE AD_Field_ID=8263
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=400,IsDisplayed='Y' WHERE AD_Field_ID=7329
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=410,IsDisplayed='Y' WHERE AD_Field_ID=7357
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=420,IsDisplayed='Y' WHERE AD_Field_ID=56402
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=430,IsDisplayed='Y' WHERE AD_Field_ID=56403
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=570,IsDisplayed='Y' WHERE AD_Field_ID=58779
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=580,IsDisplayed='Y' WHERE AD_Field_ID=58780
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=590,IsDisplayed='Y' WHERE AD_Field_ID=7327
;

-- Mar 8, 2010 4:35:20 PM CET
-- Import of Order Source
UPDATE AD_Field SET SeqNo=600,IsDisplayed='Y' WHERE AD_Field_ID=7335
;
