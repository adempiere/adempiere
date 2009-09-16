-- Jul 10, 2009 10:51:10 AM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53315,TO_DATE('2009-07-10 10:51:08','YYYY-MM-DD HH24:MI:SS'),100,'Chart Type','D','Y','N','PA_Goal ChartType',TO_DATE('2009-07-10 10:51:08','YYYY-MM-DD HH24:MI:SS'),100,'L')
;

-- Jul 10, 2009 10:51:10 AM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53315 AND EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Reference_ID!=t.AD_Reference_ID)
;

-- Jul 10, 2009 10:51:57 AM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53495,53315,TO_DATE('2009-07-10 10:51:56','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Bar Chart',TO_DATE('2009-07-10 10:51:56','YYYY-MM-DD HH24:MI:SS'),100,'BC')
;

-- Jul 10, 2009 10:51:57 AM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53495 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Jul 10, 2009 10:52:16 AM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53496,53315,TO_DATE('2009-07-10 10:52:15','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Pie Chart',TO_DATE('2009-07-10 10:52:15','YYYY-MM-DD HH24:MI:SS'),100,'PC')
;

-- Jul 10, 2009 10:52:16 AM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53496 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Jul 10, 2009 10:52:53 AM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53497,53315,TO_DATE('2009-07-10 10:52:52','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Ring Chart',TO_DATE('2009-07-10 10:52:52','YYYY-MM-DD HH24:MI:SS'),100,'RC')
;

-- Jul 10, 2009 10:52:53 AM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53497 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Jul 10, 2009 10:53:10 AM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53498,53315,TO_DATE('2009-07-10 10:53:09','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Line Chart',TO_DATE('2009-07-10 10:53:09','YYYY-MM-DD HH24:MI:SS'),100,'LC')
;

-- Jul 10, 2009 10:53:10 AM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53498 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Jul 10, 2009 10:53:23 AM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53499,53315,TO_DATE('2009-07-10 10:53:22','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Area Chart',TO_DATE('2009-07-10 10:53:22','YYYY-MM-DD HH24:MI:SS'),100,'AC')
;

-- Jul 10, 2009 10:53:23 AM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53499 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Jul 10, 2009 10:53:45 AM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53500,53315,TO_DATE('2009-07-10 10:53:43','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Waterfall Chart',TO_DATE('2009-07-10 10:53:43','YYYY-MM-DD HH24:MI:SS'),100,'WC')
;

-- Jul 10, 2009 10:53:45 AM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53500 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Jul 10, 2009 10:55:34 AM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53894,0,'ChartType',TO_DATE('2009-07-10 10:55:33','YYYY-MM-DD HH24:MI:SS'),100,'Type fo chart to render','D','Y','Chart Type','Chart Type',TO_DATE('2009-07-10 10:55:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 10, 2009 10:55:34 AM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53894 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jul 10, 2009 10:57:09 AM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57921,53894,0,17,53315,440,'ChartType',TO_DATE('2009-07-10 10:57:08','YYYY-MM-DD HH24:MI:SS'),100,'BC','Type fo chart to render','D',2,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Chart Type',0,TO_DATE('2009-07-10 10:57:08','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Jul 10, 2009 10:57:09 AM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57921 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 10, 2009 11:03:04 AM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
ALTER TABLE PA_Goal ADD ChartType NVARCHAR2(2) DEFAULT 'BC'
;

-- Jul 10, 2009 11:04:57 AM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57921,104,57343,0,367,TO_DATE('2009-07-10 11:04:55','YYYY-MM-DD HH24:MI:SS'),100,'Type fo chart to render',15,'D','Y','Y','Y','N','N','N','N','N','Chart Type',230,0,TO_DATE('2009-07-10 11:04:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 10, 2009 11:04:57 AM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57343 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jul 10, 2009 11:05:55 AM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=57343
;

-- Jul 10, 2009 11:05:55 AM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=4651
;

-- Jul 10, 2009 11:05:55 AM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=4653
;

-- Jul 10, 2009 11:05:55 AM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=4652
;

-- Jul 10, 2009 11:05:55 AM MYT
-- Add more chart type support to Performance indicator - ID: 2819356
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=4656
;

COMMIT;
