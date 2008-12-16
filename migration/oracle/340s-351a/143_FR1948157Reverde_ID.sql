-- 22-abr-2008 17:27:27 CDT
-- Implementing Reversal ID
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53457,0,'Reversal_ID',TO_DATE('2008-04-22 17:27:18','YYYY-MM-DD HH24:MI:SS'),0,'ID of document reversal','D','Y','Reversal ID','Reversal ID',TO_DATE('2008-04-22 17:27:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 22-abr-2008 17:27:27 CDT
-- Implementing Reversal ID
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53457 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 22-abr-2008 17:28:37 CDT
-- Implementing Reversal ID
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,55303,53457,0,18,337,319,'Reversal_ID',TO_DATE('2008-04-22 17:28:33','YYYY-MM-DD HH24:MI:SS'),0,'ID of document reversal','D',22,'Y','N','N','N','N','N','N','N','N','N','Y','Reversal ID',0,TO_DATE('2008-04-22 17:28:33','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- 22-abr-2008 17:28:37 CDT
-- Implementing Reversal ID
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=55303 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 22-abr-2008 17:30:13 CDT
-- Implementing Reversal ID
ALTER TABLE M_InOut ADD Reversal_ID NUMBER(10)
;

-- 22-abr-2008 17:32:10 CDT
-- Implementing Reversal ID
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53249,TO_DATE('2008-04-22 17:32:08','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','N','M_Inventory',TO_DATE('2008-04-22 17:32:08','YYYY-MM-DD HH24:MI:SS'),0,'T')
;

-- 22-abr-2008 17:32:10 CDT
-- Implementing Reversal ID
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53249 AND EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Reference_ID!=t.AD_Reference_ID)
;

-- 22-abr-2008 17:33:22 CDT
-- Implementing Reversal ID
INSERT INTO AD_Ref_Table (AD_Client_ID,AD_Display,AD_Key,AD_Org_ID,AD_Reference_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,Updated,UpdatedBy) VALUES (0,3550,3542,0,53249,321,TO_DATE('2008-04-22 17:33:22','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','N',TO_DATE('2008-04-22 17:33:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 22-abr-2008 17:33:44 CDT
-- Implementing Reversal ID
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,55304,53457,0,18,53249,321,'Reversal_ID',TO_DATE('2008-04-22 17:33:43','YYYY-MM-DD HH24:MI:SS'),0,'ID of document reversal','D',22,'Y','N','N','N','N','N','N','N','N','N','Y','Reversal ID',0,TO_DATE('2008-04-22 17:33:43','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- 22-abr-2008 17:33:45 CDT
-- Implementing Reversal ID
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=55304 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 22-abr-2008 17:33:50 CDT
-- Implementing Reversal ID
ALTER TABLE M_Inventory ADD Reversal_ID NUMBER(10)
;

-- 22-abr-2008 17:37:48 CDT
-- Implementing Reversal ID
ALTER TABLE M_Inventory MODIFY Reversal_ID NUMBER(10) DEFAULT  NULL 
;

-- 22-abr-2008 17:38:42 CDT
-- Implementing Reversal ID
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,55305,53457,0,18,336,318,'Reversal_ID',TO_DATE('2008-04-22 17:38:41','YYYY-MM-DD HH24:MI:SS'),0,'ID of document reversal','D',22,'Y','N','N','N','N','N','N','N','N','N','Y','Reversal ID',0,TO_DATE('2008-04-22 17:38:41','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- 22-abr-2008 17:38:42 CDT
-- Implementing Reversal ID
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=55305 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 22-abr-2008 17:38:45 CDT
-- Implementing Reversal ID
ALTER TABLE C_Invoice ADD Reversal_ID NUMBER(10)
;

-- 22-abr-2008 17:42:57 CDT
-- Implementing Reversal ID
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53250,TO_DATE('2008-04-22 17:42:55','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','N','GL_Journal',TO_DATE('2008-04-22 17:42:55','YYYY-MM-DD HH24:MI:SS'),0,'T')
;

-- 22-abr-2008 17:42:57 CDT
-- Implementing Reversal ID
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53250 AND EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Reference_ID!=t.AD_Reference_ID)
;

-- 22-abr-2008 17:43:35 CDT
-- Implementing Reversal ID
INSERT INTO AD_Ref_Table (AD_Client_ID,AD_Display,AD_Key,AD_Org_ID,AD_Reference_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,Updated,UpdatedBy) VALUES (0,1626,1617,0,53250,224,TO_DATE('2008-04-22 17:43:35','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','N',TO_DATE('2008-04-22 17:43:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 22-abr-2008 17:43:58 CDT
-- Implementing Reversal ID
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,55306,53457,0,18,53250,224,'Reversal_ID',TO_DATE('2008-04-22 17:43:57','YYYY-MM-DD HH24:MI:SS'),0,'ID of document reversal','D',22,'Y','N','N','N','N','N','N','N','N','N','Y','Reversal ID',0,TO_DATE('2008-04-22 17:43:57','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- 22-abr-2008 17:43:58 CDT
-- Implementing Reversal ID
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=55306 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 22-abr-2008 17:44:01 CDT
-- Implementing Reversal ID
ALTER TABLE GL_Journal ADD Reversal_ID NUMBER(10)
;

-- 22-abr-2008 17:47:15 CDT
-- Implementing Reversal ID
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53251,TO_DATE('2008-04-22 17:47:11','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','N','GL_JournalBatch',TO_DATE('2008-04-22 17:47:11','YYYY-MM-DD HH24:MI:SS'),0,'T')
;

-- 22-abr-2008 17:47:15 CDT
-- Implementing Reversal ID
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53251 AND EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Reference_ID!=t.AD_Reference_ID)
;

-- 22-abr-2008 17:47:42 CDT
-- Implementing Reversal ID
INSERT INTO AD_Ref_Table (AD_Client_ID,AD_Display,AD_Key,AD_Org_ID,AD_Reference_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,Updated,UpdatedBy) VALUES (0,1650,1641,0,53251,225,TO_DATE('2008-04-22 17:47:42','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','N',TO_DATE('2008-04-22 17:47:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 22-abr-2008 17:48:01 CDT
-- Implementing Reversal ID
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,55307,53457,0,18,53251,225,'Reversal_ID',TO_DATE('2008-04-22 17:48:00','YYYY-MM-DD HH24:MI:SS'),0,'ID of document reversal','D',22,'Y','N','N','N','N','N','N','N','N','N','Y','Reversal ID',0,TO_DATE('2008-04-22 17:48:00','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- 22-abr-2008 17:48:01 CDT
-- Implementing Reversal ID
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=55307 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 22-abr-2008 17:48:04 CDT
-- Implementing Reversal ID
ALTER TABLE GL_JournalBatch ADD Reversal_ID NUMBER(10)
;

-- 22-abr-2008 17:49:14 CDT
-- Implementing Reversal ID
ALTER TABLE GL_JournalBatch MODIFY Reversal_ID NUMBER(10) DEFAULT  NULL 
;

-- 22-abr-2008 17:50:43 CDT
-- Implementing Reversal ID
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53252,TO_DATE('2008-04-22 17:50:41','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','N','M_Movement',TO_DATE('2008-04-22 17:50:41','YYYY-MM-DD HH24:MI:SS'),0,'T')
;

-- 22-abr-2008 17:50:43 CDT
-- Implementing Reversal ID
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53252 AND EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Reference_ID!=t.AD_Reference_ID)
;

-- 22-abr-2008 17:51:11 CDT
-- Implementing Reversal ID
INSERT INTO AD_Ref_Table (AD_Client_ID,AD_Display,AD_Key,AD_Org_ID,AD_Reference_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,Updated,UpdatedBy) VALUES (0,3577,3569,0,53252,323,TO_DATE('2008-04-22 17:51:11','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','N',TO_DATE('2008-04-22 17:51:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 22-abr-2008 17:51:29 CDT
-- Implementing Reversal ID
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,55308,53457,0,18,53252,323,'Reversal_ID',TO_DATE('2008-04-22 17:51:28','YYYY-MM-DD HH24:MI:SS'),0,'ID of document reversal','D',22,'Y','N','N','N','N','N','N','N','N','N','Y','Reversal ID',0,TO_DATE('2008-04-22 17:51:28','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- 22-abr-2008 17:51:29 CDT
-- Implementing Reversal ID
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=55308 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 22-abr-2008 17:51:32 CDT
-- Implementing Reversal ID
ALTER TABLE M_Movement ADD Reversal_ID NUMBER(10)
;

-- 22-abr-2008 18:59:49 CDT
-- Implementing Reversal ID
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,55309,53457,0,18,343,335,'Reversal_ID',TO_DATE('2008-04-22 18:59:43','YYYY-MM-DD HH24:MI:SS'),0,'ID of document reversal','D',22,'Y','N','N','N','N','N','N','N','N','N','Y','Reversal ID',0,TO_DATE('2008-04-22 18:59:43','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- 22-abr-2008 18:59:49 CDT
-- Implementing Reversal ID
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=55309 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 22-abr-2008 19:03:28 CDT
-- Implementing Reversal ID
ALTER TABLE C_Payment ADD Reversal_ID NUMBER(10)
;

-- 22-abr-2008 19:07:04 CDT
-- Implementing Reversal ID
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53253,TO_DATE('2008-04-22 19:06:58','YYYY-MM-DD HH24:MI:SS'),0,'EE02','Y','N','HR_Process',TO_DATE('2008-04-22 19:06:58','YYYY-MM-DD HH24:MI:SS'),0,'T')
;

-- 22-abr-2008 19:07:04 CDT
-- Implementing Reversal ID
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53253 AND EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Reference_ID!=t.AD_Reference_ID)
;

-- 22-abr-2008 19:08:11 CDT
-- Implementing Reversal ID
INSERT INTO AD_Ref_Table (AD_Client_ID,AD_Display,AD_Key,AD_Org_ID,AD_Reference_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,Updated,UpdatedBy) VALUES (0,54870,54860,0,53253,53092,TO_DATE('2008-04-22 19:08:11','YYYY-MM-DD HH24:MI:SS'),0,'EE02','Y','N',TO_DATE('2008-04-22 19:08:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 22-abr-2008 19:08:33 CDT
-- Implementing Reversal ID
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,55310,53457,0,18,53253,53092,'Reversal_ID',TO_DATE('2008-04-22 19:08:32','YYYY-MM-DD HH24:MI:SS'),0,'ID of document reversal','EE02',22,'Y','N','N','N','N','N','N','N','N','N','Y','Reversal ID',0,TO_DATE('2008-04-22 19:08:32','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- 22-abr-2008 19:08:33 CDT
-- Implementing Reversal ID
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=55310 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 22-abr-2008 19:08:37 CDT
-- Implementing Reversal ID
ALTER TABLE HR_Process ADD Reversal_ID NUMBER(10)
;

-- 22-abr-2008 19:11:48 CDT
-- Implementing Reversal ID
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53254,TO_DATE('2008-04-22 19:11:47','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','N','PP_Cost_Collector',TO_DATE('2008-04-22 19:11:47','YYYY-MM-DD HH24:MI:SS'),0,'T')
;

-- 22-abr-2008 19:11:48 CDT
-- Implementing Reversal ID
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53254 AND EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Reference_ID!=t.AD_Reference_ID)
;

-- 22-abr-2008 19:12:26 CDT
-- Implementing Reversal ID
INSERT INTO AD_Ref_Table (AD_Client_ID,AD_Display,AD_Key,AD_Org_ID,AD_Reference_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,Updated,UpdatedBy) VALUES (0,53830,53828,0,53254,53035,TO_DATE('2008-04-22 19:12:26','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','N',TO_DATE('2008-04-22 19:12:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 22-abr-2008 19:12:45 CDT
-- Implementing Reversal ID
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,55311,53457,0,18,53254,53035,'Reversal_ID',TO_DATE('2008-04-22 19:12:44','YYYY-MM-DD HH24:MI:SS'),0,'ID of document reversal','EE01',22,'Y','N','N','N','N','N','N','N','N','N','Y','Reversal ID',0,TO_DATE('2008-04-22 19:12:44','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- 22-abr-2008 19:12:45 CDT
-- Implementing Reversal ID
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=55311 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 22-abr-2008 19:12:48 CDT
-- Implementing Reversal ID
ALTER TABLE PP_Cost_Collector ADD Reversal_ID NUMBER(10)
;

