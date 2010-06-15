-- Mar 8, 2010 8:44:44 PM COT
-- FR2962094_Finish implementation of weighted average costing
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54132,0,'P_AverageCostVariance_Acct',TO_TIMESTAMP('2010-03-08 20:44:43','YYYY-MM-DD HH24:MI:SS'),100,'Average Cost Variance','D','The Average Cost Variance is used in weighted average costing to reflect differences when posting costs for negative inventory.','Y','Average Cost Variance','Average Cost Variance',TO_TIMESTAMP('2010-03-08 20:44:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 8, 2010 8:44:44 PM COT
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54132 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Mar 8, 2010 8:46:06 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59071,54132,0,25,315,'P_AverageCostVariance_Acct',TO_TIMESTAMP('2010-03-08 20:46:05','YYYY-MM-DD HH24:MI:SS'),100,'Average Cost Variance','D',10,'The Average Cost Variance is used in weighted average costing to reflect differences when posting costs for negative inventory.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Average Cost Variance',TO_TIMESTAMP('2010-03-08 20:46:05','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Mar 8, 2010 8:46:06 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59071 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Mar 8, 2010 8:46:19 PM COT
ALTER TABLE C_AcctSchema_Default ADD COLUMN P_AverageCostVariance_Acct NUMERIC(10) DEFAULT NULL 
;

-- Mar 8, 2010 8:46:45 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59072,54132,0,25,401,'P_AverageCostVariance_Acct',TO_TIMESTAMP('2010-03-08 20:46:45','YYYY-MM-DD HH24:MI:SS'),100,'Average Cost Variance','D',10,'The Average Cost Variance is used in weighted average costing to reflect differences when posting costs for negative inventory.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Average Cost Variance',TO_TIMESTAMP('2010-03-08 20:46:45','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Mar 8, 2010 8:46:45 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59072 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Mar 8, 2010 8:46:50 PM COT
ALTER TABLE M_Product_Category_Acct ADD COLUMN P_AverageCostVariance_Acct NUMERIC(10) DEFAULT NULL 
;

-- Mar 8, 2010 8:47:12 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59073,54132,0,25,273,'P_AverageCostVariance_Acct',TO_TIMESTAMP('2010-03-08 20:47:11','YYYY-MM-DD HH24:MI:SS'),100,'Average Cost Variance','D',10,'The Average Cost Variance is used in weighted average costing to reflect differences when posting costs for negative inventory.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Average Cost Variance',TO_TIMESTAMP('2010-03-08 20:47:11','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Mar 8, 2010 8:47:12 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59073 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Mar 8, 2010 8:47:17 PM COT
ALTER TABLE M_Product_Acct ADD COLUMN P_AverageCostVariance_Acct NUMERIC(10) DEFAULT NULL 
;

-- Mar 8, 2010 8:49:30 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59071,106,58783,0,252,TO_TIMESTAMP('2010-03-08 20:49:29','YYYY-MM-DD HH24:MI:SS'),100,'Average Cost Variance',26,'D','The Average Cost Variance is used in weighted average costing to reflect differences when posting costs for negative inventory.','Y','Y','Y','N','N','N','N','N','Average Cost Variance',770,0,TO_TIMESTAMP('2010-03-08 20:49:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 8, 2010 8:49:30 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58783 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Mar 8, 2010 8:50:01 PM COT
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=58783
;

-- Mar 8, 2010 8:50:01 PM COT
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=4861
;

-- Mar 8, 2010 8:50:01 PM COT
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=4862
;

-- Mar 8, 2010 8:50:01 PM COT
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=2663
;

-- Mar 8, 2010 8:50:01 PM COT
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=4863
;

-- Mar 8, 2010 8:50:01 PM COT
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=2662
;

-- Mar 8, 2010 8:50:01 PM COT
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=3824
;

-- Mar 8, 2010 8:50:01 PM COT
UPDATE AD_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Field_ID=2654
;

-- Mar 8, 2010 8:50:01 PM COT
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=3835
;

-- Mar 8, 2010 8:50:01 PM COT
UPDATE AD_Field SET SeqNo=370,IsDisplayed='Y' WHERE AD_Field_ID=56529
;

-- Mar 8, 2010 8:50:01 PM COT
UPDATE AD_Field SET SeqNo=380,IsDisplayed='Y' WHERE AD_Field_ID=56522
;

-- Mar 8, 2010 8:50:01 PM COT
UPDATE AD_Field SET SeqNo=390,IsDisplayed='Y' WHERE AD_Field_ID=56524
;

-- Mar 8, 2010 8:50:01 PM COT
UPDATE AD_Field SET SeqNo=400,IsDisplayed='Y' WHERE AD_Field_ID=56528
;

-- Mar 8, 2010 8:50:01 PM COT
UPDATE AD_Field SET SeqNo=410,IsDisplayed='Y' WHERE AD_Field_ID=56527
;

-- Mar 8, 2010 8:50:01 PM COT
UPDATE AD_Field SET SeqNo=420,IsDisplayed='Y' WHERE AD_Field_ID=56525
;

-- Mar 8, 2010 8:50:01 PM COT
UPDATE AD_Field SET SeqNo=430,IsDisplayed='Y' WHERE AD_Field_ID=56523
;

-- Mar 8, 2010 8:50:01 PM COT
UPDATE AD_Field SET SeqNo=440,IsDisplayed='Y' WHERE AD_Field_ID=56520
;

-- Mar 8, 2010 8:50:01 PM COT
UPDATE AD_Field SET SeqNo=450,IsDisplayed='Y' WHERE AD_Field_ID=56521
;

-- Mar 8, 2010 8:50:01 PM COT
UPDATE AD_Field SET SeqNo=460,IsDisplayed='Y' WHERE AD_Field_ID=56526
;

-- Mar 8, 2010 8:50:01 PM COT
UPDATE AD_Field SET SeqNo=470,IsDisplayed='Y' WHERE AD_Field_ID=56550
;

-- Mar 8, 2010 8:50:01 PM COT
UPDATE AD_Field SET SeqNo=480,IsDisplayed='Y' WHERE AD_Field_ID=56551
;

-- Mar 8, 2010 8:50:01 PM COT
UPDATE AD_Field SET SeqNo=490,IsDisplayed='Y' WHERE AD_Field_ID=3842
;

-- Mar 8, 2010 8:50:01 PM COT
UPDATE AD_Field SET SeqNo=500,IsDisplayed='Y' WHERE AD_Field_ID=3841
;

-- Mar 8, 2010 8:50:01 PM COT
UPDATE AD_Field SET SeqNo=510,IsDisplayed='Y' WHERE AD_Field_ID=3846
;

-- Mar 8, 2010 8:50:01 PM COT
UPDATE AD_Field SET SeqNo=520,IsDisplayed='Y' WHERE AD_Field_ID=5133
;

-- Mar 8, 2010 8:50:01 PM COT
UPDATE AD_Field SET SeqNo=530,IsDisplayed='Y' WHERE AD_Field_ID=5132
;

-- Mar 8, 2010 8:50:02 PM COT
UPDATE AD_Field SET SeqNo=540,IsDisplayed='Y' WHERE AD_Field_ID=3843
;

-- Mar 8, 2010 8:50:02 PM COT
UPDATE AD_Field SET SeqNo=550,IsDisplayed='Y' WHERE AD_Field_ID=3845
;

-- Mar 8, 2010 8:50:02 PM COT
UPDATE AD_Field SET SeqNo=560,IsDisplayed='Y' WHERE AD_Field_ID=3844
;

-- Mar 8, 2010 8:50:02 PM COT
UPDATE AD_Field SET SeqNo=570,IsDisplayed='Y' WHERE AD_Field_ID=3849
;

-- Mar 8, 2010 8:50:02 PM COT
UPDATE AD_Field SET SeqNo=580,IsDisplayed='Y' WHERE AD_Field_ID=3850
;

-- Mar 8, 2010 8:50:02 PM COT
UPDATE AD_Field SET SeqNo=590,IsDisplayed='Y' WHERE AD_Field_ID=5138
;

-- Mar 8, 2010 8:50:02 PM COT
UPDATE AD_Field SET SeqNo=600,IsDisplayed='Y' WHERE AD_Field_ID=3847
;

-- Mar 8, 2010 8:50:02 PM COT
UPDATE AD_Field SET SeqNo=610,IsDisplayed='Y' WHERE AD_Field_ID=3839
;

-- Mar 8, 2010 8:50:02 PM COT
UPDATE AD_Field SET SeqNo=620,IsDisplayed='Y' WHERE AD_Field_ID=3837
;

-- Mar 8, 2010 8:50:02 PM COT
UPDATE AD_Field SET SeqNo=630,IsDisplayed='Y' WHERE AD_Field_ID=3840
;

-- Mar 8, 2010 8:50:02 PM COT
UPDATE AD_Field SET SeqNo=640,IsDisplayed='Y' WHERE AD_Field_ID=3838
;

-- Mar 8, 2010 8:50:02 PM COT
UPDATE AD_Field SET SeqNo=650,IsDisplayed='Y' WHERE AD_Field_ID=3836
;

-- Mar 8, 2010 8:50:02 PM COT
UPDATE AD_Field SET SeqNo=660,IsDisplayed='Y' WHERE AD_Field_ID=3851
;

-- Mar 8, 2010 8:50:02 PM COT
UPDATE AD_Field SET SeqNo=670,IsDisplayed='Y' WHERE AD_Field_ID=3852
;

-- Mar 8, 2010 8:50:02 PM COT
UPDATE AD_Field SET SeqNo=680,IsDisplayed='Y' WHERE AD_Field_ID=3830
;

-- Mar 8, 2010 8:50:02 PM COT
UPDATE AD_Field SET SeqNo=690,IsDisplayed='Y' WHERE AD_Field_ID=3831
;

-- Mar 8, 2010 8:50:02 PM COT
UPDATE AD_Field SET SeqNo=700,IsDisplayed='Y' WHERE AD_Field_ID=3832
;

-- Mar 8, 2010 8:50:02 PM COT
UPDATE AD_Field SET SeqNo=710,IsDisplayed='Y' WHERE AD_Field_ID=3833
;

-- Mar 8, 2010 8:50:02 PM COT
UPDATE AD_Field SET SeqNo=720,IsDisplayed='Y' WHERE AD_Field_ID=4092
;

-- Mar 8, 2010 8:50:02 PM COT
UPDATE AD_Field SET SeqNo=730,IsDisplayed='Y' WHERE AD_Field_ID=4093
;

-- Mar 8, 2010 8:50:02 PM COT
UPDATE AD_Field SET SeqNo=740,IsDisplayed='Y' WHERE AD_Field_ID=5134
;

-- Mar 8, 2010 8:50:02 PM COT
UPDATE AD_Field SET SeqNo=750,IsDisplayed='Y' WHERE AD_Field_ID=4094
;

-- Mar 8, 2010 8:50:02 PM COT
UPDATE AD_Field SET SeqNo=760,IsDisplayed='Y' WHERE AD_Field_ID=4095
;

-- Mar 8, 2010 8:50:02 PM COT
UPDATE AD_Field SET SeqNo=770,IsDisplayed='Y' WHERE AD_Field_ID=3823
;

-- Mar 8, 2010 8:50:56 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59072,58784,0,324,TO_TIMESTAMP('2010-03-08 20:50:55','YYYY-MM-DD HH24:MI:SS'),100,'Average Cost Variance',26,'D','The Average Cost Variance is used in weighted average costing to reflect differences when posting costs for negative inventory.','Y','Y','Y','N','N','N','N','N','Average Cost Variance',310,0,TO_TIMESTAMP('2010-03-08 20:50:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 8, 2010 8:50:56 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58784 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Mar 8, 2010 8:51:06 PM COT
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=58784
;

-- Mar 8, 2010 8:51:06 PM COT
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=4872
;

-- Mar 8, 2010 8:51:06 PM COT
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=4873
;

-- Mar 8, 2010 8:51:06 PM COT
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=3944
;

-- Mar 8, 2010 8:51:06 PM COT
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=56539
;

-- Mar 8, 2010 8:51:06 PM COT
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=56532
;

-- Mar 8, 2010 8:51:06 PM COT
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=56534
;

-- Mar 8, 2010 8:51:06 PM COT
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=56538
;

-- Mar 8, 2010 8:51:06 PM COT
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=56537
;

-- Mar 8, 2010 8:51:06 PM COT
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=56535
;

-- Mar 8, 2010 8:51:06 PM COT
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=56533
;

-- Mar 8, 2010 8:51:06 PM COT
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=56530
;

-- Mar 8, 2010 8:51:06 PM COT
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=56531
;

-- Mar 8, 2010 8:51:06 PM COT
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=56536
;

-- Mar 8, 2010 8:51:06 PM COT
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=56552
;

-- Mar 8, 2010 8:51:06 PM COT
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=56553
;

-- Mar 8, 2010 8:51:06 PM COT
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=3945
;

-- Mar 8, 2010 8:51:50 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59073,58785,0,210,TO_TIMESTAMP('2010-03-08 20:51:49','YYYY-MM-DD HH24:MI:SS'),100,'Average Cost Variance',26,'D','The Average Cost Variance is used in weighted average costing to reflect differences when posting costs for negative inventory.','Y','Y','Y','N','N','N','N','N','Average Cost Variance',280,0,TO_TIMESTAMP('2010-03-08 20:51:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 8, 2010 8:51:50 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58785 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Mar 8, 2010 8:51:59 PM COT
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=58785
;

-- Mar 8, 2010 8:51:59 PM COT
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=4868
;

-- Mar 8, 2010 8:51:59 PM COT
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=4869
;

-- Mar 8, 2010 8:51:59 PM COT
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=2608
;

-- Mar 8, 2010 8:51:59 PM COT
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=56549
;

-- Mar 8, 2010 8:51:59 PM COT
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=56542
;

-- Mar 8, 2010 8:51:59 PM COT
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=56544
;

-- Mar 8, 2010 8:51:59 PM COT
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=56548
;

-- Mar 8, 2010 8:51:59 PM COT
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=56547
;

-- Mar 8, 2010 8:51:59 PM COT
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=56545
;

-- Mar 8, 2010 8:51:59 PM COT
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=56543
;

-- Mar 8, 2010 8:51:59 PM COT
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=56540
;

-- Mar 8, 2010 8:51:59 PM COT
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=56541
;

-- Mar 8, 2010 8:51:59 PM COT
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=56546
;

-- Mar 8, 2010 8:51:59 PM COT
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=56554
;

-- Mar 8, 2010 8:51:59 PM COT
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=56555
;

-- Mar 8, 2010 9:03:21 PM COT
UPDATE AD_Field SET IsMandatory='Y',Updated=TO_TIMESTAMP('2010-03-08 21:03:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58783
;

-- Mar 8, 2010 9:03:44 PM COT
UPDATE AD_Field SET IsMandatory='Y',Updated=TO_TIMESTAMP('2010-03-08 21:03:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58784
;

-- Mar 8, 2010 9:04:06 PM COT
UPDATE AD_Field SET IsMandatory='Y',Updated=TO_TIMESTAMP('2010-03-08 21:04:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58785
;

