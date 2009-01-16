-- Jan 5, 2009 6:18:43 PM COT
-- Fix problem with ASP Generate Fields process
UPDATE AD_Process_Para SET DefaultValue='S',Updated=TO_DATE('2009-01-05 18:18:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53126
;

UPDATE AD_Process_Para SET DefaultValue='S',Updated=TO_DATE('2009-01-05 18:18:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53125
;

ALTER TABLE asp_window drop CONSTRAINT asp_window_key;

DROP index asp_window_key;

ALTER TABLE ASP_Tab drop CONSTRAINT ASP_Tab_key;

DROP index ASP_Tab_key;

ALTER TABLE ASP_Field drop CONSTRAINT ASP_Field_key;

DROP index ASP_Field_key;

ALTER TABLE ASP_Process drop CONSTRAINT ASP_Process_key;

DROP index ASP_Process_key;

ALTER TABLE ASP_Process_Para drop CONSTRAINT ASP_Process_Para_key;

DROP index ASP_Process_Para_key;

ALTER TABLE ASP_Form drop CONSTRAINT ASP_Form_key;

DROP index ASP_Form_key;

ALTER TABLE ASP_Task drop CONSTRAINT ASP_Task_key;

DROP index ASP_Task_key;

ALTER TABLE ASP_Workflow drop CONSTRAINT ASP_Workflow_key;

DROP index ASP_Workflow_key;

alter table ASP_Tab drop column asp_level_id;

alter table ASP_Tab drop column ad_window_id;

alter table ASP_Field drop column asp_level_id;

alter table ASP_Field drop column AD_Tab_ID;

alter table ASP_Process_Para drop column asp_level_id;

alter table ASP_Process_Para drop column AD_Process_ID;

INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53750,0,'ASP_Window_ID',TO_DATE('2009-01-16 01:10:47','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','ASP Window','ASP Window',TO_DATE('2009-01-16 01:10:47','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53750 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53751,0,'ASP_Tab_ID',TO_DATE('2009-01-16 01:12:36','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','ASP Tab','ASP Tab',TO_DATE('2009-01-16 01:12:36','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53751 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53752,0,'ASP_Field_ID',TO_DATE('2009-01-16 01:13:14','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','ASP Field','ASP Field',TO_DATE('2009-01-16 01:13:14','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53752 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53753,0,'ASP_Process_ID',TO_DATE('2009-01-16 01:13:32','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','ASP Process','ASP Process',TO_DATE('2009-01-16 01:13:32','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53753 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53754,0,'ASP_Process_Para_ID',TO_DATE('2009-01-16 01:14:04','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','ASP Process Parameter','ASP Process Parameter',TO_DATE('2009-01-16 01:14:04','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53754 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53755,0,'ASP_Form_ID',TO_DATE('2009-01-16 01:14:28','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','ASP Form','ASP Form',TO_DATE('2009-01-16 01:14:28','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53755 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53756,0,'ASP_Task_ID',TO_DATE('2009-01-16 01:15:35','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','ASP Task','ASP Task',TO_DATE('2009-01-16 01:15:35','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53756 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53757,0,'ASP_Workflow_ID',TO_DATE('2009-01-16 01:16:33','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','ASP Workflow','ASP Workflow',TO_DATE('2009-01-16 01:16:33','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53757 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56664,53750,0,13,53046,'ASP_Window_ID',TO_DATE('2009-01-16 01:17:55','YYYY-MM-DD HH24:MI:SS'),100,'D',10,'Y','N','N','N','Y','N','N','N','N','N','N','ASP Window',0,TO_DATE('2009-01-16 01:17:55','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56664 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

ALTER TABLE ASP_Window ADD ASP_Window_ID NUMBER(10)
;

ALTER TABLE ASP_Window ADD CONSTRAINT ASP_Window_Key PRIMARY KEY (ASP_Window_ID)
;

INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56665,53751,0,13,53047,'ASP_Tab_ID',TO_DATE('2009-01-16 01:55:54','YYYY-MM-DD HH24:MI:SS'),100,'D',10,'Y','N','N','N','Y','N','N','N','N','N','N','ASP Tab',0,TO_DATE('2009-01-16 01:55:54','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56665 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

ALTER TABLE ASP_Tab ADD ASP_Tab_ID NUMBER(10)
;

ALTER TABLE ASP_Tab ADD CONSTRAINT ASP_Tab_Key PRIMARY KEY (ASP_Tab_ID)
;

INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56666,53752,0,13,53048,'ASP_Field_ID',TO_DATE('2009-01-16 01:58:04','YYYY-MM-DD HH24:MI:SS'),100,'D',10,'Y','N','N','N','Y','N','N','N','N','N','N','ASP Field',0,TO_DATE('2009-01-16 01:58:04','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56666 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

ALTER TABLE ASP_Field ADD ASP_Field_ID NUMBER(10)
;

ALTER TABLE ASP_Field ADD CONSTRAINT ASP_Field_Key PRIMARY KEY (ASP_Field_ID)
;

INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56667,53753,0,13,53049,'ASP_Process_ID',TO_DATE('2009-01-16 01:58:48','YYYY-MM-DD HH24:MI:SS'),100,'D',10,'Y','N','N','N','Y','N','N','N','N','N','N','ASP Process',0,TO_DATE('2009-01-16 01:58:48','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56667 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

ALTER TABLE ASP_Process ADD ASP_Process_ID NUMBER(10)
;

ALTER TABLE ASP_Process ADD CONSTRAINT ASP_Process_Key PRIMARY KEY (ASP_Process_ID)
;

INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56668,53754,0,13,53050,'ASP_Process_Para_ID',TO_DATE('2009-01-16 01:59:14','YYYY-MM-DD HH24:MI:SS'),100,'D',10,'Y','N','N','N','Y','N','N','N','N','N','N','ASP Process Parameter',0,TO_DATE('2009-01-16 01:59:14','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56668 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

ALTER TABLE ASP_Process_Para ADD ASP_Process_Para_ID NUMBER(10)
;

ALTER TABLE ASP_Process_Para ADD CONSTRAINT ASP_Process_Para_Key PRIMARY KEY (ASP_Process_Para_ID)
;

INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56669,53755,0,13,53051,'ASP_Form_ID',TO_DATE('2009-01-16 01:59:38','YYYY-MM-DD HH24:MI:SS'),100,'D',10,'Y','N','N','N','Y','N','N','N','N','N','N','ASP Form',0,TO_DATE('2009-01-16 01:59:38','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56669 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

ALTER TABLE ASP_Form ADD ASP_Form_ID NUMBER(10)
;

ALTER TABLE ASP_Form ADD CONSTRAINT ASP_Form_Key PRIMARY KEY (ASP_Form_ID)
;

INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56670,53756,0,13,53052,'ASP_Task_ID',TO_DATE('2009-01-16 02:00:04','YYYY-MM-DD HH24:MI:SS'),100,'D',10,'Y','N','N','N','Y','N','N','N','N','N','N','ASP Task',0,TO_DATE('2009-01-16 02:00:04','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56670 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

ALTER TABLE ASP_Task ADD ASP_Task_ID NUMBER(10)
;

ALTER TABLE ASP_Task ADD CONSTRAINT ASP_Task_Key PRIMARY KEY (ASP_Task_ID)
;

INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56671,53757,0,13,53053,'ASP_Workflow_ID',TO_DATE('2009-01-16 02:00:27','YYYY-MM-DD HH24:MI:SS'),100,'D',10,'Y','N','N','N','Y','N','N','N','N','N','N','ASP Workflow',0,TO_DATE('2009-01-16 02:00:27','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56671 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

ALTER TABLE ASP_Workflow ADD ASP_Workflow_ID NUMBER(10)
;

ALTER TABLE ASP_Workflow ADD CONSTRAINT ASP_Workflow_Key PRIMARY KEY (ASP_Workflow_ID)
;

UPDATE AD_Tab SET AD_Column_ID=54196,Updated=TO_DATE('2009-01-16 02:02:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53065
;

UPDATE AD_Tab SET AD_Column_ID=54099,Updated=TO_DATE('2009-01-16 02:02:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53056
;

UPDATE AD_Tab SET AD_Column_ID=54133,Updated=TO_DATE('2009-01-16 02:03:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53059
;

UPDATE AD_Tab SET AD_Column_ID=54154,Updated=TO_DATE('2009-01-16 02:04:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53061
;

UPDATE AD_Tab SET AD_Column_ID=54164,Updated=TO_DATE('2009-01-16 02:04:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53062
;

UPDATE AD_Tab SET AD_Column_ID=54174,Updated=TO_DATE('2009-01-16 02:04:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53063
;

UPDATE AD_Column SET IsParent='N',Updated=TO_DATE('2009-01-16 02:05:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54108
;

UPDATE AD_Column SET IsParent='N',Updated=TO_DATE('2009-01-16 02:05:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54109
;

UPDATE AD_Column SET IsParent='N',Updated=TO_DATE('2009-01-16 02:05:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54110
;

INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56672,53750,0,19,53047,'ASP_Window_ID',TO_DATE('2009-01-16 02:05:56','YYYY-MM-DD HH24:MI:SS'),100,'U',10,'Y','N','N','N','N','N','Y','N','N','N','N','ASP Window',0,TO_DATE('2009-01-16 02:05:56','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56672 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

UPDATE AD_Column SET IsParent='Y', IsUpdateable='N',Updated=TO_DATE('2009-01-16 02:06:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54108
;

UPDATE AD_Column SET IsParent='Y', IsUpdateable='N',Updated=TO_DATE('2009-01-16 02:06:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54109
;

UPDATE AD_Column SET IsParent='Y', IsUpdateable='N',Updated=TO_DATE('2009-01-16 02:06:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54110
;

UPDATE AD_Column SET EntityType='D', IsUpdateable='N',Updated=TO_DATE('2009-01-16 02:07:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56672
;

ALTER TABLE ASP_Tab ADD ASP_Window_ID NUMBER(10)
;

INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56673,53751,0,19,53048,'ASP_Tab_ID',TO_DATE('2009-01-16 02:07:54','YYYY-MM-DD HH24:MI:SS'),100,'D',10,'Y','N','N','N','N','N','Y','N','N','N','N','ASP Tab',0,TO_DATE('2009-01-16 02:07:54','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56673 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

ALTER TABLE ASP_Field ADD ASP_Tab_ID NUMBER(10)
;

INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56674,53753,0,19,53050,'ASP_Process_ID',TO_DATE('2009-01-16 02:08:29','YYYY-MM-DD HH24:MI:SS'),100,'U',10,'Y','N','N','N','N','N','Y','N','N','N','N','ASP Process',0,TO_DATE('2009-01-16 02:08:29','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56674 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

UPDATE AD_Column SET EntityType='D', IsUpdateable='N',Updated=TO_DATE('2009-01-16 02:08:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56674
;

ALTER TABLE ASP_Process_Para ADD ASP_Process_ID NUMBER(10)
;

UPDATE AD_Tab SET AD_Column_ID=56672,Updated=TO_DATE('2009-01-16 02:09:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53057
;

UPDATE AD_Tab SET AD_Column_ID=56673,Updated=TO_DATE('2009-01-16 02:09:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53058
;

UPDATE AD_Tab SET AD_Column_ID=56674,Updated=TO_DATE('2009-01-16 02:09:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53060
;

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56664,56607,0,53056,TO_DATE('2009-01-16 02:09:36','YYYY-MM-DD HH24:MI:SS'),100,10,'D','Y','Y','N','N','N','N','N','N','ASP Window',TO_DATE('2009-01-16 02:09:36','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56607 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56665,56608,0,53057,TO_DATE('2009-01-16 02:09:44','YYYY-MM-DD HH24:MI:SS'),100,10,'D','Y','Y','N','N','N','N','N','N','ASP Tab',TO_DATE('2009-01-16 02:09:44','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56608 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56672,56609,0,53057,TO_DATE('2009-01-16 02:09:44','YYYY-MM-DD HH24:MI:SS'),100,10,'D','Y','Y','Y','N','N','N','N','N','ASP Window',TO_DATE('2009-01-16 02:09:44','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56609 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=56609
;

UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=54247
;

UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=54248
;

UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=54249
;

UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=54250
;

UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=54251
;

UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=54252
;

UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=54253
;

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56666,56610,0,53058,TO_DATE('2009-01-16 02:10:19','YYYY-MM-DD HH24:MI:SS'),100,10,'D','Y','Y','N','N','N','N','N','N','ASP Field',TO_DATE('2009-01-16 02:10:19','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56610 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56673,56611,0,53058,TO_DATE('2009-01-16 02:10:19','YYYY-MM-DD HH24:MI:SS'),100,10,'D','Y','Y','Y','N','N','N','N','N','ASP Tab',TO_DATE('2009-01-16 02:10:19','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56611 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=56611
;

UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=54256
;

UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=54257
;

UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=54258
;

UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=54259
;

UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=54260
;

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56667,56612,0,53059,TO_DATE('2009-01-16 02:10:31','YYYY-MM-DD HH24:MI:SS'),100,10,'D','Y','Y','N','N','N','N','N','N','ASP Process',TO_DATE('2009-01-16 02:10:31','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56612 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56674,56613,0,53060,TO_DATE('2009-01-16 02:10:36','YYYY-MM-DD HH24:MI:SS'),100,10,'D','Y','Y','Y','N','N','N','N','N','ASP Process',TO_DATE('2009-01-16 02:10:36','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56613 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56668,56614,0,53060,TO_DATE('2009-01-16 02:10:37','YYYY-MM-DD HH24:MI:SS'),100,10,'D','Y','Y','N','N','N','N','N','N','ASP Process Parameter',TO_DATE('2009-01-16 02:10:37','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56614 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=56613
;

UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=54269
;

UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=54270
;

UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=54271
;

UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=54272
;

UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=54273
;

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56669,56615,0,53061,TO_DATE('2009-01-16 02:10:51','YYYY-MM-DD HH24:MI:SS'),100,10,'D','Y','Y','N','N','N','N','N','N','ASP Form',TO_DATE('2009-01-16 02:10:51','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56615 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56670,56616,0,53062,TO_DATE('2009-01-16 02:10:55','YYYY-MM-DD HH24:MI:SS'),100,10,'D','Y','Y','N','N','N','N','N','N','ASP Task',TO_DATE('2009-01-16 02:10:55','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56616 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56671,56617,0,53063,TO_DATE('2009-01-16 02:11:01','YYYY-MM-DD HH24:MI:SS'),100,10,'D','Y','Y','N','N','N','N','N','N','ASP Workflow',TO_DATE('2009-01-16 02:11:01','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56617 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

UPDATE AD_Column SET IsIdentifier='Y', IsUpdateable='N', SeqNo=1,Updated=TO_DATE('2009-01-16 02:12:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54099
;

UPDATE AD_Column SET IsIdentifier='Y', IsUpdateable='N', SeqNo=2,Updated=TO_DATE('2009-01-16 02:12:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54098
;

UPDATE AD_Column SET IsIdentifier='Y', IsUpdateable='N', SeqNo=1,Updated=TO_DATE('2009-01-16 02:12:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54110
;

UPDATE AD_Column SET IsIdentifier='Y', IsUpdateable='N', SeqNo=2,Updated=TO_DATE('2009-01-16 02:12:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54109
;

UPDATE AD_Column SET IsIdentifier='Y', IsUpdateable='N', SeqNo=3,Updated=TO_DATE('2009-01-16 02:13:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54108
;

UPDATE AD_Column SET IsIdentifier='Y', IsUpdateable='N', SeqNo=1,Updated=TO_DATE('2009-01-16 02:13:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54133
;

UPDATE AD_Column SET IsIdentifier='Y', IsUpdateable='N', SeqNo=2,Updated=TO_DATE('2009-01-16 02:13:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54132
;

DELETE  FROM  AD_Field_Trl WHERE AD_Field_ID=54247
;

DELETE FROM AD_Field WHERE AD_Field_ID=54247
;

DELETE  FROM  AD_Field_Trl WHERE AD_Field_ID=54248
;

DELETE FROM AD_Field WHERE AD_Field_ID=54248
;

DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=54110
;

DELETE FROM AD_Column WHERE AD_Column_ID=54110
;

DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=54109
;

DELETE FROM AD_Column WHERE AD_Column_ID=54109
;

UPDATE AD_Column SET IsIdentifier='Y', IsUpdateable='N', SeqNo=1,Updated=TO_DATE('2009-01-16 02:25:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56672
;

UPDATE AD_Column SET IsUpdateable='N', MandatoryLogic=NULL, SeqNo=2,Updated=TO_DATE('2009-01-16 02:25:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54108
;

DELETE  FROM  AD_Field_Trl WHERE AD_Field_ID=54256
;

DELETE FROM AD_Field WHERE AD_Field_ID=54256
;

DELETE  FROM  AD_Field_Trl WHERE AD_Field_ID=54257
;

DELETE FROM AD_Field WHERE AD_Field_ID=54257
;

DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=54123
;

DELETE FROM AD_Column WHERE AD_Column_ID=54123
;

DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=54122
;

DELETE FROM AD_Column WHERE AD_Column_ID=54122
;

DELETE  FROM  AD_Field_Trl WHERE AD_Field_ID=54269
;

DELETE FROM AD_Field WHERE AD_Field_ID=54269
;

DELETE  FROM  AD_Field_Trl WHERE AD_Field_ID=54270
;

DELETE FROM AD_Field WHERE AD_Field_ID=54270
;

DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=54144
;

DELETE FROM AD_Column WHERE AD_Column_ID=54144
;

DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=54142
;

DELETE FROM AD_Column WHERE AD_Column_ID=54142
;

UPDATE AD_Field SET DisplayLength=40,Updated=TO_DATE('2009-01-16 02:36:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54303
;

UPDATE AD_Field SET DisplayLength=40,Updated=TO_DATE('2009-01-16 02:36:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54241
;

UPDATE AD_Field SET DisplayLength=40,Updated=TO_DATE('2009-01-16 02:36:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56609
;

UPDATE AD_Field SET DisplayLength=40,Updated=TO_DATE('2009-01-16 02:36:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56611
;

UPDATE AD_Field SET DisplayLength=40,Updated=TO_DATE('2009-01-16 02:36:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54263
;

UPDATE AD_Field SET DisplayLength=40,Updated=TO_DATE('2009-01-16 02:36:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56613
;

UPDATE AD_Field SET DisplayLength=40,Updated=TO_DATE('2009-01-16 02:37:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54276
;

UPDATE AD_Field SET DisplayLength=40,Updated=TO_DATE('2009-01-16 02:37:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54282
;

UPDATE AD_Field SET DisplayLength=40,Updated=TO_DATE('2009-01-16 02:37:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54288
;

ALTER TABLE ASP_Field ADD (CONSTRAINT ASPTab_ASPField FOREIGN KEY (ASP_Tab_ID) REFERENCES ASP_Tab);
                          
ALTER TABLE ASP_Process_Para ADD (CONSTRAINT ASPProcess_ASPProcessPara FOREIGN KEY (ASP_Process_ID) REFERENCES ASP_Process);
 
ALTER TABLE ASP_Tab ADD (CONSTRAINT ASPWindow_ASPTab FOREIGN KEY (ASP_Window_ID) REFERENCES ASP_Window);

