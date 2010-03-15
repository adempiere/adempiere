-- Mar 15, 2010 2:26:39 PM CET
-- Import Order: Import Delivery Rule
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59074,555,0,17,151,591,'DeliveryRule',TO_DATE('2010-03-15 14:26:38','YYYY-MM-DD HH24:MI:SS'),100,'Defines the timing of Delivery','D',1,'The Delivery Rule indicates when an order should be delivered. For example should the order be delivered when the entire order is complete, when a line is complete or as the products become available.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Delivery Rule',0,TO_DATE('2010-03-15 14:26:38','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Mar 15, 2010 2:26:39 PM CET
-- Import Order: Import Delivery Rule
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59074 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Mar 15, 2010 2:26:47 PM CET
-- Import Order: Import Delivery Rule
ALTER TABLE I_Order ADD DeliveryRule CHAR(1) DEFAULT NULL 
;

-- Mar 15, 2010 2:29:10 PM CET
-- Import Order: Import Delivery Rule
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59074,58786,0,512,TO_DATE('2010-03-15 14:29:09','YYYY-MM-DD HH24:MI:SS'),100,'Defines the timing of Delivery',0,'D','The Delivery Rule indicates when an order should be delivered. For example should the order be delivered when the entire order is complete, when a line is complete or as the products become available.','Y','Y','Y','N','N','N','N','N','Delivery Rule',610,0,TO_DATE('2010-03-15 14:29:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 15, 2010 2:29:10 PM CET
-- Import Order: Import Delivery Rule
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58786 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=58786
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=7330
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=7339
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=7325
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=7341
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=7346
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=7345
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=7331
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=7317
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=7360
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=7354
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=7340
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=7645
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=7332
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=7353
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=7361
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=7358
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=7350
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=7640
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=7644
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=7641
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Field_ID=7326
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=7642
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=370,IsDisplayed='Y' WHERE AD_Field_ID=7643
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=380,IsDisplayed='Y' WHERE AD_Field_ID=7343
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=390,IsDisplayed='Y' WHERE AD_Field_ID=8262
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=400,IsDisplayed='Y' WHERE AD_Field_ID=8263
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=410,IsDisplayed='Y' WHERE AD_Field_ID=7329
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=420,IsDisplayed='Y' WHERE AD_Field_ID=7357
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=430,IsDisplayed='Y' WHERE AD_Field_ID=56402
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=440,IsDisplayed='Y' WHERE AD_Field_ID=56403
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=450,IsDisplayed='Y' WHERE AD_Field_ID=7351
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=460,IsDisplayed='Y' WHERE AD_Field_ID=7316
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=470,IsDisplayed='Y' WHERE AD_Field_ID=7321
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=480,IsDisplayed='Y' WHERE AD_Field_ID=7348
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=490,IsDisplayed='Y' WHERE AD_Field_ID=7320
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=500,IsDisplayed='Y' WHERE AD_Field_ID=7349
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=510,IsDisplayed='Y' WHERE AD_Field_ID=7314
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=520,IsDisplayed='Y' WHERE AD_Field_ID=7344
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=530,IsDisplayed='Y' WHERE AD_Field_ID=7342
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=540,IsDisplayed='Y' WHERE AD_Field_ID=7333
;

-- Mar 15, 2010 2:31:44 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=550,IsDisplayed='Y' WHERE AD_Field_ID=7693
;

-- Mar 15, 2010 2:31:45 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=560,IsDisplayed='Y' WHERE AD_Field_ID=7313
;

-- Mar 15, 2010 2:31:45 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=570,IsDisplayed='Y' WHERE AD_Field_ID=7352
;

-- Mar 15, 2010 2:31:45 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=580,IsDisplayed='Y' WHERE AD_Field_ID=58779
;

-- Mar 15, 2010 2:31:45 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=590,IsDisplayed='Y' WHERE AD_Field_ID=58780
;

-- Mar 15, 2010 2:31:45 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=600,IsDisplayed='Y' WHERE AD_Field_ID=7327
;

-- Mar 15, 2010 2:31:45 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET SeqNo=610,IsDisplayed='Y' WHERE AD_Field_ID=7335
;

-- Mar 15, 2010 2:32:18 PM CET
-- Import Order: Import Delivery Rule
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2010-03-15 14:32:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58786
;

