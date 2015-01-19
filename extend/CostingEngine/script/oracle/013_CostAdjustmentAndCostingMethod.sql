SET SQLBLANKLINES ON
SET DEFINE OFF

-- Jul 9, 2010 6:31:26 PM CDT
-- Costing Engine
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54251,0,'CostAdjustment',TO_DATE('2010-07-09 18:31:20','YYYY-MM-DD HH24:MI:SS'),0,'Product Cost Adjustment','D','product cost adjustments','Y','Cost Adjustment','Cost Adjustment',TO_DATE('2010-07-09 18:31:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 9, 2010 6:31:26 PM CDT
-- Costing Engine
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54251 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Jul 9, 2010 6:31:59 PM CDT
-- Costing Engine
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59626,54251,0,37,808,'CostAdjustment',TO_DATE('2010-07-09 18:31:57','YYYY-MM-DD HH24:MI:SS'),0,'Product Cost Adjustment','D',22,'product cost adjustments','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Cost Adjustment',0,TO_DATE('2010-07-09 18:31:57','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 9, 2010 6:31:59 PM CDT
-- Costing Engine
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59626 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 9, 2010 6:32:03 PM CDT
-- Costing Engine
ALTER TABLE M_CostDetail ADD CostAdjustment NUMBER DEFAULT NULL 
;

-- Jul 9, 2010 6:33:44 PM CDT
-- Costing Engine
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54252,0,'CostAdjustmentDate',TO_DATE('2010-07-09 18:33:42','YYYY-MM-DD HH24:MI:SS'),0,'Product Cost Adjustment','D','product cost adjustments','Y','Cost Adjustment Date','Cost Adjustment Date',TO_DATE('2010-07-09 18:33:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 9, 2010 6:33:45 PM CDT
-- Costing Engine
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54252 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Jul 9, 2010 6:35:06 PM CDT
-- Costing Engine
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59627,54252,0,15,808,'CostAdjustmentDate',TO_DATE('2010-07-09 18:35:04','YYYY-MM-DD HH24:MI:SS'),0,'Product Cost Adjustment','D',8,'product cost adjustments','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Cost Adjustment Date',0,TO_DATE('2010-07-09 18:35:04','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 9, 2010 6:35:06 PM CDT
-- Costing Engine
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59627 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 9, 2010 6:35:12 PM CDT
-- Costing Engine
ALTER TABLE M_CostDetail ADD CostAdjustmentDate DATE DEFAULT NULL 
;

-- Jul 9, 2010 6:35:57 PM CDT
-- Costing Engine
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,59626,59533,0,748,TO_DATE('2010-07-09 18:35:55','YYYY-MM-DD HH24:MI:SS'),0,'Product Cost Adjustment',22,'D','product cost adjustments','Y','Y','Y','N','N','N','N','N','Cost Adjustment',TO_DATE('2010-07-09 18:35:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 9, 2010 6:35:57 PM CDT
-- Costing Engine
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59533 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jul 9, 2010 6:36:00 PM CDT
-- Costing Engine
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,59627,59534,0,748,TO_DATE('2010-07-09 18:35:57','YYYY-MM-DD HH24:MI:SS'),0,'Product Cost Adjustment',8,'D','product cost adjustments','Y','Y','Y','N','N','N','N','N','Cost Adjustment Date',TO_DATE('2010-07-09 18:35:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 9, 2010 6:36:00 PM CDT
-- Costing Engine
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59534 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jul 9, 2010 6:38:07 PM CDT
-- Costing Engine
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=59533
;

-- Jul 9, 2010 6:38:07 PM CDT
-- Costing Engine
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=59534
;

-- Jul 9, 2010 6:38:07 PM CDT
-- Costing Engine
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=58847
;

-- Jul 9, 2010 6:38:07 PM CDT
-- Costing Engine
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=12155
;

-- Jul 9, 2010 6:38:07 PM CDT
-- Costing Engine
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=12341
;

-- Jul 9, 2010 6:38:07 PM CDT
-- Costing Engine
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=58849
;

-- Jul 9, 2010 6:38:07 PM CDT
-- Costing Engine
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=12177
;

-- Jul 9, 2010 6:38:07 PM CDT
-- Costing Engine
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=12151
;

-- Jul 9, 2010 6:38:07 PM CDT
-- Costing Engine
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=12156
;

-- Jul 9, 2010 6:38:07 PM CDT
-- Costing Engine
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=12342
;

-- Jul 9, 2010 6:38:07 PM CDT
-- Costing Engine
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=12343
;

-- Jul 9, 2010 6:38:07 PM CDT
-- Costing Engine
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=12345
;

-- Jul 9, 2010 6:38:07 PM CDT
-- Costing Engine
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=12344
;

-- Jul 9, 2010 6:38:07 PM CDT
-- Costing Engine
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=59521
;

-- Jul 9, 2010 6:38:07 PM CDT
-- Costing Engine
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=59452
;

-- Jul 9, 2010 6:38:07 PM CDT
-- Costing Engine
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=58865
;

-- Jul 9, 2010 6:38:07 PM CDT
-- Costing Engine
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=12346
;

-- Jul 9, 2010 6:38:07 PM CDT
-- Costing Engine
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=12150
;

-- Jul 9, 2010 6:38:07 PM CDT
-- Costing Engine
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=58864
;

-- Jul 9, 2010 6:38:07 PM CDT
-- Costing Engine
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=12153
;

-- Jul 9, 2010 6:38:21 PM CDT
-- Costing Engine
UPDATE AD_Field SET AD_FieldGroup_ID=103,Updated=TO_DATE('2010-07-09 18:38:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=59533
;

-- Jul 9, 2010 6:38:31 PM CDT
-- Costing Engine
UPDATE AD_Field SET AD_FieldGroup_ID=103, IsReadOnly='Y', IsSameLine='Y',Updated=TO_DATE('2010-07-09 18:38:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=59534
;

-- Jul 9, 2010 6:38:34 PM CDT
-- Costing Engine
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2010-07-09 18:38:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=59533
;

-- Jul 9, 2010 6:44:56 PM CDT
-- Costing Engine
UPDATE AD_Field SET AD_FieldGroup_ID=116,Updated=TO_DATE('2010-07-09 18:44:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=59533
;

-- Jul 9, 2010 6:45:05 PM CDT
-- Costing Engine
UPDATE AD_Field SET AD_FieldGroup_ID=116,Updated=TO_DATE('2010-07-09 18:45:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=58846
;

-- Jul 9, 2010 6:45:10 PM CDT
-- Costing Engine
UPDATE AD_Field SET AD_FieldGroup_ID=116,Updated=TO_DATE('2010-07-09 18:45:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=59523
;

-- Jul 9, 2010 6:45:17 PM CDT
-- Costing Engine
UPDATE AD_Field SET AD_FieldGroup_ID=116,Updated=TO_DATE('2010-07-09 18:45:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12317
;

-- Jul 9, 2010 6:45:20 PM CDT
-- Costing Engine
UPDATE AD_Field SET AD_FieldGroup_ID=116,Updated=TO_DATE('2010-07-09 18:45:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12147
;

-- Jul 9, 2010 6:45:24 PM CDT
-- Costing Engine
UPDATE AD_Field SET AD_FieldGroup_ID=116,Updated=TO_DATE('2010-07-09 18:45:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12340
;

-- Jul 9, 2010 6:45:28 PM CDT
-- Costing Engine
UPDATE AD_Field SET AD_FieldGroup_ID=116,Updated=TO_DATE('2010-07-09 18:45:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=58848
;

-- Jul 9, 2010 6:45:31 PM CDT
-- Costing Engine
UPDATE AD_Field SET AD_FieldGroup_ID=116,Updated=TO_DATE('2010-07-09 18:45:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=59534
;

-- Jul 9, 2010 6:45:36 PM CDT
-- Costing Engine
UPDATE AD_Field SET AD_FieldGroup_ID=116,Updated=TO_DATE('2010-07-09 18:45:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=58847
;

-- Jul 9, 2010 6:45:41 PM CDT
-- Costing Engine
UPDATE AD_Field SET AD_FieldGroup_ID=116,Updated=TO_DATE('2010-07-09 18:45:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12155
;

-- Jul 9, 2010 6:45:44 PM CDT
-- Costing Engine
UPDATE AD_Field SET AD_FieldGroup_ID=116,Updated=TO_DATE('2010-07-09 18:45:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12341
;

-- Jul 9, 2010 6:45:48 PM CDT
-- Costing Engine
UPDATE AD_Field SET AD_FieldGroup_ID=116,Updated=TO_DATE('2010-07-09 18:45:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=58849
;

-- Jul 9, 2010 6:46:33 PM CDT
-- Costing Engine
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=58847
;

-- Jul 9, 2010 6:46:33 PM CDT
-- Costing Engine
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=58846
;

-- Jul 9, 2010 6:46:33 PM CDT
-- Costing Engine
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=59523
;

-- Jul 9, 2010 6:46:33 PM CDT
-- Costing Engine
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=12155
;

-- Jul 9, 2010 6:46:33 PM CDT
-- Costing Engine
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=12317
;

-- Jul 9, 2010 6:46:33 PM CDT
-- Costing Engine
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=12147
;

-- Jul 9, 2010 6:46:33 PM CDT
-- Costing Engine
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=12341
;

-- Jul 9, 2010 6:46:33 PM CDT
-- Costing Engine
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=12340
;

-- Jul 9, 2010 6:46:33 PM CDT
-- Costing Engine
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=58849
;

-- Jul 9, 2010 6:46:33 PM CDT
-- Costing Engine
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=58848
;

-- Jul 9, 2010 6:46:33 PM CDT
-- Costing Engine
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=59533
;

-- Jul 9, 2010 6:46:33 PM CDT
-- Costing Engine
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=59534
;

-- Jul 9, 2010 6:46:41 PM CDT
-- Costing Engine
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2010-07-09 18:46:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=58847
;

-- Jul 9, 2010 6:46:47 PM CDT
-- Costing Engine
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2010-07-09 18:46:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12155
;

-- Jul 9, 2010 6:46:57 PM CDT
-- Costing Engine
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2010-07-09 18:46:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12317
;

-- Jul 9, 2010 6:47:00 PM CDT
-- Costing Engine
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2010-07-09 18:47:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12147
;

-- Jul 9, 2010 6:47:21 PM CDT
-- Costing Engine
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2010-07-09 18:47:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12317
;

-- Jul 9, 2010 6:47:24 PM CDT
-- Costing Engine
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2010-07-09 18:47:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12147
;

-- Jul 9, 2010 6:47:33 PM CDT
-- Costing Engine
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2010-07-09 18:47:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12340
;

-- Jul 9, 2010 6:47:40 PM CDT
-- Costing Engine
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2010-07-09 18:47:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=58849
;

-- Jul 9, 2010 6:50:30 PM CDT
-- Costing Engine
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=12155
;

-- Jul 9, 2010 6:50:30 PM CDT
-- Costing Engine
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=58847
;

-- Jul 9, 2010 6:50:30 PM CDT
-- Costing Engine
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=12317
;

-- Jul 9, 2010 6:50:30 PM CDT
-- Costing Engine
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=12147
;

-- Jul 9, 2010 6:50:30 PM CDT
-- Costing Engine
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=58846
;

-- Jul 9, 2010 6:50:30 PM CDT
-- Costing Engine
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=59523
;

-- Jul 9, 2010 6:50:42 PM CDT
-- Costing Engine
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2010-07-09 18:50:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=58847
;

-- Jul 9, 2010 6:54:51 PM CDT
-- Costing Engine
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59628,241,0,17,122,586,'CostingMethod',TO_DATE('2010-07-09 18:54:47','YYYY-MM-DD HH24:MI:SS'),0,'Indicates how Costs will be calculated','D',1,'The Costing Method indicates how costs will be calculated (Standard, Average, Lifo, FiFo).  The default costing method is defined on accounting schema level and can be optionally overwritten in the product category.  The costing method cannot conflict with the Material Movement Policy (defined on Product Category).','Y','Y','N','N','N','N','N','Y','N','N','N','N','Y','Costing Method',0,TO_DATE('2010-07-09 18:54:47','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 9, 2010 6:54:51 PM CDT
-- Costing Engine
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59628 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 9, 2010 6:55:55 PM CDT
-- Costing Engine
UPDATE AD_Column SET DefaultValue='S',Updated=TO_DATE('2010-07-09 18:55:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=59628
;

-- Jul 9, 2010 6:56:01 PM CDT
-- Costing Engine
ALTER TABLE M_CostType ADD CostingMethod CHAR(1) DEFAULT 'S' NOT NULL
;

-- Jul 9, 2010 6:56:31 PM CDT
-- Costing Engine
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,59628,59535,0,489,TO_DATE('2010-07-09 18:56:22','YYYY-MM-DD HH24:MI:SS'),0,'Indicates how Costs will be calculated',1,'D','The Costing Method indicates how costs will be calculated (Standard, Average, Lifo, FiFo).  The default costing method is defined on accounting schema level and can be optionally overwritten in the product category.  The costing method cannot conflict with the Material Movement Policy (defined on Product Category).','Y','Y','Y','N','N','N','N','N','Costing Method',TO_DATE('2010-07-09 18:56:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 9, 2010 6:56:31 PM CDT
-- Costing Engine
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59535 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

