-- Apr 26, 2014 4:54:19 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Column SET FieldLength = 999999999 WHERE AD_Column_ID IN (57874, 57873) AND FieldLength = 2000
;

-- Apr 26, 2014 4:55:27 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Menu SET Name='GL Journal Batch',Updated=TO_DATE('2014-04-26 16:55:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Menu_ID=118
;

-- Apr 26, 2014 4:55:27 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=118
;

-- Apr 26, 2014 4:55:28 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Window SET Name='GL Journal Batch',Updated=TO_DATE('2014-04-26 16:55:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Window_ID=132
;

-- Apr 26, 2014 4:55:28 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Window_Trl SET IsTranslated='N' WHERE AD_Window_ID=132
;

-- Apr 26, 2014 4:55:29 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,69936,148,0,18,362,226,252,'Account_ID',TO_DATE('2013-11-23 16:44:10','YYYY-MM-DD HH24:MI:SS'),100,'Account used','U',10,'The (natural) account used','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Account',0,TO_DATE('2013-11-23 16:44:10','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 26, 2014 4:55:29 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=69936 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 26, 2014 4:55:31 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
ALTER TABLE GL_JournalLine ADD COLUMN Account_ID DECIMAL(10) DEFAULT NULL 
;

-- Apr 26, 2014 4:55:34 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Column SET Callout='org.compiere.model.CalloutGLJournal.account',Updated=TO_DATE('2014-04-26 16:55:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=69936
;

-- Apr 26, 2014 4:55:34 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
ALTER TABLE GL_JournalLine MODIFY Account_ID DECIMAL(10) DEFAULT NULL
;

-- Apr 26, 2014 4:55:34 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,Callout,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,69937,112,0,18,130,226,'org.compiere.model.CalloutGLJournal.account','AD_OrgTrx_ID',TO_DATE('2013-11-23 16:45:40','YYYY-MM-DD HH24:MI:SS'),100,'Performing or initiating organization','U',10,'The organization which performs or initiates this transaction (for another organization).  The owning Organization may not be the transaction organization in a service bureau environment, with centralized services, and inter-organization transactions.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Trx Organization',0,TO_DATE('2013-11-23 16:45:40','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 26, 2014 4:55:34 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=69937 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 26, 2014 4:55:34 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
ALTER TABLE GL_JournalLine ADD COLUMN AD_OrgTrx_ID DECIMAL(10) DEFAULT NULL 
;

-- Apr 26, 2014 4:55:35 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,56616,0,'Alias_ValidCombination_ID',TO_DATE('2013-11-23 16:46:54','YYYY-MM-DD HH24:MI:SS'),100,'U','Y','Alias','Alias',TO_DATE('2013-11-23 16:46:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:55:35 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=56616 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Apr 26, 2014 4:55:36 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53598,TO_DATE('2013-11-23 16:47:46','YYYY-MM-DD HH24:MI:SS'),100,'U','Y','N','C_ValidCombination (with Alias defined)',TO_DATE('2013-11-23 16:47:46','YYYY-MM-DD HH24:MI:SS'),100,'T')
;

-- Apr 26, 2014 4:55:36 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53598 AND NOT EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Reference_ID=t.AD_Reference_ID)
;

-- Apr 26, 2014 4:55:36 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Ref_Table (AD_Client_ID,AD_Display,AD_Key,AD_Org_ID,AD_Reference_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,IsAlert,IsDisplayIdentifier,IsValueDisplayed,Updated,UpdatedBy,WhereClause) VALUES (0,2399,1014,0,53598,176,TO_DATE('2013-11-23 16:48:26','YYYY-MM-DD HH24:MI:SS'),100,'U','Y','N','N','N',TO_DATE('2013-11-23 16:48:26','YYYY-MM-DD HH24:MI:SS'),100,'C_ValidCombination.Alias IS NOT NULL')
;

-- Apr 26, 2014 4:55:37 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,Callout,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,69938,56616,0,18,53598,226,'org.compiere.model.CalloutGLJournal.alias','Alias_ValidCombination_ID',TO_DATE('2013-11-23 16:49:21','YYYY-MM-DD HH24:MI:SS'),100,'U',10,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Alias',0,TO_DATE('2013-11-23 16:49:21','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 26, 2014 4:55:37 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=69938 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 26, 2014 4:55:37 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
ALTER TABLE GL_JournalLine ADD COLUMN Alias_ValidCombination_ID DECIMAL(10) DEFAULT NULL 
;

-- Apr 26, 2014 4:55:39 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,Callout,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,69939,1005,0,18,142,226,'org.compiere.model.CalloutGLJournal.account','C_Activity_ID',TO_DATE('2013-11-23 16:51:12','YYYY-MM-DD HH24:MI:SS'),100,'Business Activity','U',10,'Activities indicate tasks that are performed and used to utilize Activity based Costing','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Activity',0,TO_DATE('2013-11-23 16:51:12','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 26, 2014 4:55:39 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=69939 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 26, 2014 4:55:39 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
ALTER TABLE GL_JournalLine ADD COLUMN C_Activity_ID DECIMAL(10) DEFAULT NULL 
;

-- Apr 26, 2014 4:55:40 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,Callout,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,69940,187,0,30,138,226,'org.compiere.model.CalloutGLJournal.account','C_BPartner_ID',TO_DATE('2013-11-23 16:52:06','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Business Partner','U',10,'A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Business Partner ',0,TO_DATE('2013-11-23 16:52:06','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 26, 2014 4:55:40 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=69940 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 26, 2014 4:55:40 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
ALTER TABLE GL_JournalLine ADD COLUMN C_BPartner_ID DECIMAL(10) DEFAULT NULL 
;

-- Apr 26, 2014 4:55:43 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,Callout,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,69941,550,0,18,143,226,'org.compiere.model.CalloutGLJournal.account','C_Campaign_ID',TO_DATE('2013-11-23 16:52:49','YYYY-MM-DD HH24:MI:SS'),100,'Marketing Campaign','U',10,'The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Campaign',0,TO_DATE('2013-11-23 16:52:49','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 26, 2014 4:55:43 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=69941 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 26, 2014 4:55:43 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
ALTER TABLE GL_JournalLine ADD COLUMN C_Campaign_ID DECIMAL(10) DEFAULT NULL 
;

-- Apr 26, 2014 4:55:44 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,Callout,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,69942,200,0,30,133,226,'org.compiere.model.CalloutGLJournal.account','C_LocFrom_ID',TO_DATE('2013-11-23 16:53:51','YYYY-MM-DD HH24:MI:SS'),100,'Location that inventory was moved from','U',10,'The Location From indicates the location that a product was moved from.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Location From',0,TO_DATE('2013-11-23 16:53:51','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 26, 2014 4:55:44 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=69942 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 26, 2014 4:55:44 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
ALTER TABLE GL_JournalLine ADD COLUMN C_LocFrom_ID DECIMAL(10) DEFAULT NULL 
;

-- Apr 26, 2014 4:55:46 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,69943,201,0,30,133,226,'C_LocTo_ID',TO_DATE('2013-11-23 16:54:28','YYYY-MM-DD HH24:MI:SS'),100,'Location that inventory was moved to','U',10,'The Location To indicates the location that a product was moved to.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Location To',0,TO_DATE('2013-11-23 16:54:28','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 26, 2014 4:55:46 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=69943 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 26, 2014 4:55:46 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
ALTER TABLE GL_JournalLine ADD COLUMN C_LocTo_ID DECIMAL(10) DEFAULT NULL 
;

-- Apr 26, 2014 4:55:47 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,Callout,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,69944,208,0,19,141,226,'org.compiere.model.CalloutGLJournal.account','C_Project_ID',TO_DATE('2013-11-23 16:55:12','YYYY-MM-DD HH24:MI:SS'),100,'Financial Project','U',10,'A Project allows you to track and control internal or external activities.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Project',0,TO_DATE('2013-11-23 16:55:12','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 26, 2014 4:55:47 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=69944 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 26, 2014 4:55:47 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
ALTER TABLE GL_JournalLine ADD COLUMN C_Project_ID DECIMAL(10) DEFAULT NULL 
;

-- Apr 26, 2014 4:55:49 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,Callout,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,69945,210,0,19,144,226,'org.compiere.model.CalloutGLJournal.account','C_SalesRegion_ID',TO_DATE('2013-11-23 16:55:57','YYYY-MM-DD HH24:MI:SS'),100,'Sales coverage region','U',10,'The Sales Region indicates a specific area of sales coverage.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Sales Region',0,TO_DATE('2013-11-23 16:55:57','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 26, 2014 4:55:49 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=69945 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 26, 2014 4:55:49 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
ALTER TABLE GL_JournalLine ADD COLUMN C_SalesRegion_ID DECIMAL(10) DEFAULT NULL 
;

-- Apr 26, 2014 4:55:51 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,69946,2876,0,19,226,255,'C_SubAcct_ID',TO_DATE('2013-11-23 16:56:38','YYYY-MM-DD HH24:MI:SS'),100,'Sub account for Element Value','U',10,'The Element Value (e.g. Account) may have optional sub accounts for further detail. The sub account is dependent on the value of the account, so a further specification. If the sub-accounts are more or less the same, consider using another accounting dimension.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Sub Account',0,TO_DATE('2013-11-23 16:56:38','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 26, 2014 4:55:51 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=69946 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 26, 2014 4:55:51 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
ALTER TABLE GL_JournalLine ADD COLUMN C_SubAcct_ID DECIMAL(10) DEFAULT NULL 
;

-- Apr 26, 2014 4:55:52 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Column SET Callout='org.compiere.model.CalloutGLJournal.alias', IsMandatory='N',Updated=TO_DATE('2014-04-26 16:55:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=5955
;

-- Apr 26, 2014 4:55:52 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
ALTER TABLE GL_JournalLine MODIFY C_ValidCombination_ID DECIMAL(10) DEFAULT NULL
;

-- Apr 26, 2014 4:55:52 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
ALTER TABLE GL_JournalLine MODIFY C_ValidCombination_ID NULL
;

-- Apr 26, 2014 4:55:52 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,Callout,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,69947,454,0,19,162,226,'org.compiere.model.CalloutGLJournal.account','M_Product_ID',TO_DATE('2013-11-23 16:58:41','YYYY-MM-DD HH24:MI:SS'),100,'Product, Service, Item','U',10,'Identifies an item which is either purchased or sold in this organization.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Product',0,TO_DATE('2013-11-23 16:58:41','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 26, 2014 4:55:52 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=69947 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 26, 2014 4:55:52 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
ALTER TABLE GL_JournalLine ADD COLUMN M_Product_ID DECIMAL(10) DEFAULT NULL 
;

-- Apr 26, 2014 4:55:53 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,Callout,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,69948,613,0,18,134,226,'org.compiere.model.CalloutGLJournal.account','User1_ID',TO_DATE('2013-11-23 16:59:36','YYYY-MM-DD HH24:MI:SS'),100,'User defined list element #1','U',10,'The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','User List 1',0,TO_DATE('2013-11-23 16:59:36','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 26, 2014 4:55:53 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=69948 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 26, 2014 4:55:53 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
ALTER TABLE GL_JournalLine ADD COLUMN User1_ID DECIMAL(10) DEFAULT NULL 
;

-- Apr 26, 2014 4:55:54 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,AD_Val_Rule_ID,Callout,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,69949,614,0,18,137,226,139,'org.compiere.model.CalloutGLJournal.account','User2_ID',TO_DATE('2013-11-23 17:00:22','YYYY-MM-DD HH24:MI:SS'),100,'User defined list element #2','U',10,'The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','User List 2',0,TO_DATE('2013-11-23 17:00:22','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 26, 2014 4:55:54 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=69949 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 26, 2014 4:55:54 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
ALTER TABLE GL_JournalLine ADD COLUMN User2_ID DECIMAL(10) DEFAULT NULL 
;

-- Apr 26, 2014 4:55:54 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Column SET AD_Val_Rule_ID=138,Updated=TO_DATE('2014-04-26 16:55:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=69948
;

-- Apr 26, 2014 4:55:54 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
ALTER TABLE GL_JournalLine MODIFY User1_ID DECIMAL(10) DEFAULT NULL
;

-- Apr 26, 2014 4:55:55 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,Callout,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,69950,2877,0,19,226,'org.compiere.model.CalloutGLJournal.account','UserElement1_ID',TO_DATE('2013-11-23 17:01:18','YYYY-MM-DD HH24:MI:SS'),100,'User defined accounting Element','U',10,'A user defined accounting element refers to a Adempiere table. This allows to use any table content as an accounting dimension (e.g. Project Task).  Note that User Elements are optional and are populated from the context of the document (i.e. not requested)','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','User Element 1',0,TO_DATE('2013-11-23 17:01:18','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 26, 2014 4:55:55 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=69950 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 26, 2014 4:55:55 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
ALTER TABLE GL_JournalLine ADD COLUMN UserElement1_ID DECIMAL(10) DEFAULT NULL 
;

-- Apr 26, 2014 4:55:56 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Column SET IsActive='N',Updated=TO_DATE('2014-04-26 16:55:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=69950
;

-- Apr 26, 2014 4:55:56 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
ALTER TABLE GL_JournalLine MODIFY UserElement1_ID DECIMAL(10) DEFAULT NULL
;

-- Apr 26, 2014 4:55:56 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,69951,2878,0,19,226,'UserElement2_ID',TO_DATE('2013-11-23 17:01:40','YYYY-MM-DD HH24:MI:SS'),100,'User defined accounting Element','U',10,'A user defined accounting element refers to a Adempiere table. This allows to use any table content as an accounting dimension (e.g. Project Task).  Note that User Elements are optional and are populated from the context of the document (i.e. not requested)','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','User Element 2',0,TO_DATE('2013-11-23 17:01:40','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 26, 2014 4:55:56 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=69951 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 26, 2014 4:55:56 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
ALTER TABLE GL_JournalLine ADD COLUMN UserElement2_ID DECIMAL(10) DEFAULT NULL 
;

-- Apr 26, 2014 4:55:57 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Column SET IsActive='N',Updated=TO_DATE('2014-04-26 16:55:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=69951
;

-- Apr 26, 2014 4:55:57 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
ALTER TABLE GL_JournalLine MODIFY UserElement2_ID DECIMAL(10) DEFAULT NULL
;

-- Apr 26, 2014 4:55:58 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Column SET Callout='org.compiere.model.CalloutGLJournal.account', IsActive='Y',Updated=TO_DATE('2014-04-26 16:55:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=69951
;

-- Apr 26, 2014 4:55:58 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
ALTER TABLE GL_JournalLine MODIFY UserElement2_ID DECIMAL(10) DEFAULT NULL
;

-- Apr 26, 2014 4:55:58 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Column SET IsActive='N',Updated=TO_DATE('2014-04-26 16:55:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=69951
;

-- Apr 26, 2014 4:55:58 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
ALTER TABLE GL_JournalLine MODIFY UserElement2_ID DECIMAL(10) DEFAULT NULL
;

-- Apr 26, 2014 4:55:59 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Field SET SeqNo=270,Updated=TO_DATE('2014-04-26 16:55:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=895
;

-- Apr 26, 2014 4:56:00 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Field SET SeqNo=280,Updated=TO_DATE('2014-04-26 16:56:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=4724
;

-- Apr 26, 2014 4:56:00 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Field SET SeqNo=290,Updated=TO_DATE('2014-04-26 16:56:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56249
;

-- Apr 26, 2014 4:56:00 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Field SET SeqNo=300,Updated=TO_DATE('2014-04-26 16:56:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56250
;

-- Apr 26, 2014 4:56:00 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Field SET SeqNo=310,Updated=TO_DATE('2014-04-26 16:56:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56251
;

-- Apr 26, 2014 4:56:00 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Field SET SeqNo=320,Updated=TO_DATE('2014-04-26 16:56:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=728
;

-- Apr 26, 2014 4:56:00 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Field SET SeqNo=330,Updated=TO_DATE('2014-04-26 16:56:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=729
;

-- Apr 26, 2014 4:56:00 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Field SET SeqNo=340,Updated=TO_DATE('2014-04-26 16:56:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=730
;

-- Apr 26, 2014 4:56:01 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Field SET SeqNo=350,Updated=TO_DATE('2014-04-26 16:56:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=731
;

-- Apr 26, 2014 4:56:01 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Field SET SeqNo=360,Updated=TO_DATE('2014-04-26 16:56:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=1294
;

-- Apr 26, 2014 4:56:01 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Field SET SeqNo=370,Updated=TO_DATE('2014-04-26 16:56:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=1295
;

-- Apr 26, 2014 4:56:01 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Field SET AD_FieldGroup_ID=NULL,Updated=TO_DATE('2014-04-26 16:56:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=4724
;

-- Apr 26, 2014 4:56:01 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Field SET AD_FieldGroup_ID=104,Updated=TO_DATE('2014-04-26 16:56:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=4724
;

-- Apr 26, 2014 4:56:02 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_FieldGroup_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,69936,70457,104,0,161,TO_DATE('2013-11-23 17:09:17','YYYY-MM-DD HH24:MI:SS'),100,'Account used',10,'U','The (natural) account used','Y','Y','Y','N','N','N','N','N','N','Account',130,380,0,TO_DATE('2013-11-23 17:09:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:56:02 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70457 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:56:06 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,69940,70458,0,161,TO_DATE('2013-11-23 17:09:48','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Business Partner',0,'U','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Y','Y','N','N','N','N','N','N','Business Partner ',140,380,0,TO_DATE('2013-11-23 17:09:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:56:06 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70458 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:56:08 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,69937,70459,0,161,TO_DATE('2013-11-23 17:10:35','YYYY-MM-DD HH24:MI:SS'),100,'Performing or initiating organization',0,'@$Element_OT@=Y','U','The organization which performs or initiates this transaction (for another organization).  The owning Organization may not be the transaction organization in a service bureau environment, with centralized services, and inter-organization transactions.','Y','Y','Y','N','N','N','N','N','N','Trx Organization',150,380,0,TO_DATE('2013-11-23 17:10:35','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:56:08 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70459 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:56:12 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,69939,70460,0,161,TO_DATE('2013-11-23 17:11:04','YYYY-MM-DD HH24:MI:SS'),100,'Business Activity',0,'@$Element_AY@=Y','U','Activities indicate tasks that are performed and used to utilize Activity based Costing','Y','Y','Y','N','N','N','N','N','N','Activity',160,380,0,TO_DATE('2013-11-23 17:11:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:56:12 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70460 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:56:15 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,69941,70461,0,161,TO_DATE('2013-11-23 17:11:28','YYYY-MM-DD HH24:MI:SS'),100,'Marketing Campaign',0,'@$Element_MC@=Y','U','The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','Y','Y','N','N','N','N','N','N','Campaign',170,380,0,TO_DATE('2013-11-23 17:11:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:56:15 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70461 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:56:18 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,69945,70462,0,161,TO_DATE('2013-11-23 17:11:50','YYYY-MM-DD HH24:MI:SS'),100,'Sales coverage region',0,'@$Element_SR@=Y','U','The Sales Region indicates a specific area of sales coverage.','Y','Y','Y','N','N','N','N','N','N','Sales Region',180,380,0,TO_DATE('2013-11-23 17:11:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:56:18 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70462 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:56:22 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,69944,70463,0,161,TO_DATE('2013-11-23 17:12:15','YYYY-MM-DD HH24:MI:SS'),100,'Financial Project',0,'@$Element_PJ@=Y','U','A Project allows you to track and control internal or external activities.','Y','Y','Y','N','N','N','N','N','N','Project',190,380,0,TO_DATE('2013-11-23 17:12:15','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:56:22 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70463 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:56:27 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,69946,70464,0,161,TO_DATE('2013-11-23 17:12:45','YYYY-MM-DD HH24:MI:SS'),100,'Sub account for Element Value',0,'@$Element_SA@=Y','U','The Element Value (e.g. Account) may have optional sub accounts for further detail. The sub account is dependent on the value of the account, so a further specification. If the sub-accounts are more or less the same, consider using another accounting dimension.','Y','Y','Y','N','N','N','N','N','N','Sub Account',200,380,0,TO_DATE('2013-11-23 17:12:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:56:27 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70464 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:56:29 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,69947,70465,0,161,TO_DATE('2013-11-23 17:13:11','YYYY-MM-DD HH24:MI:SS'),100,'Product, Service, Item',0,'@$Element_PR@=Y','U','Identifies an item which is either purchased or sold in this organization.','Y','Y','Y','N','N','N','N','N','N','Product',210,380,0,TO_DATE('2013-11-23 17:13:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:56:29 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70465 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:56:32 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,69942,70466,0,161,TO_DATE('2013-11-23 17:13:48','YYYY-MM-DD HH24:MI:SS'),100,'Location that inventory was moved from',0,'@$Element_LF@=Y','U','The Location From indicates the location that a product was moved from.','Y','Y','Y','N','N','N','N','N','N','Location From',220,380,0,TO_DATE('2013-11-23 17:13:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:56:32 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70466 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:56:35 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,69943,70467,0,161,TO_DATE('2013-11-23 17:14:17','YYYY-MM-DD HH24:MI:SS'),100,'Location that inventory was moved to',0,'@$Element_LT@=Y','U','The Location To indicates the location that a product was moved to.','Y','Y','Y','N','N','N','N','N','N','Location To',225,380,0,TO_DATE('2013-11-23 17:14:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:56:35 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70467 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:56:35 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Column SET IsActive='Y',Updated=TO_DATE('2014-04-26 16:56:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=69950
;

-- Apr 26, 2014 4:56:35 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
ALTER TABLE GL_JournalLine MODIFY UserElement1_ID DECIMAL(10) DEFAULT NULL
;

-- Apr 26, 2014 4:56:36 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Column SET IsActive='Y',Updated=TO_DATE('2014-04-26 16:56:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=69951
;

-- Apr 26, 2014 4:56:36 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
ALTER TABLE GL_JournalLine MODIFY UserElement2_ID DECIMAL(10) DEFAULT NULL
;

-- Apr 26, 2014 4:56:40 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,69950,70468,0,161,TO_DATE('2013-11-23 17:15:36','YYYY-MM-DD HH24:MI:SS'),100,'User defined accounting Element',0,'@$Element_X1@=Y','U','A user defined accounting element refers to a Adempiere table. This allows to use any table content as an accounting dimension (e.g. Project Task).  Note that User Elements are optional and are populated from the context of the document (i.e. not requested)','Y','Y','Y','N','N','N','N','N','N','User Element 1',230,380,0,TO_DATE('2013-11-23 17:15:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:56:40 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70468 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:56:45 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,69951,70469,0,161,TO_DATE('2013-11-23 17:15:58','YYYY-MM-DD HH24:MI:SS'),100,'User defined accounting Element',0,'@$Element_X2@=Y','U','A user defined accounting element refers to a Adempiere table. This allows to use any table content as an accounting dimension (e.g. Project Task).  Note that User Elements are optional and are populated from the context of the document (i.e. not requested)','Y','Y','Y','N','N','N','N','N','N','User Element 2',235,380,0,TO_DATE('2013-11-23 17:15:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:56:45 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70469 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:56:51 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,69948,70470,0,161,TO_DATE('2013-11-23 17:16:38','YYYY-MM-DD HH24:MI:SS'),100,'User defined list element #1',0,'@$Element_U1@=Y','U','The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','Y','N','N','N','N','N','N','User List 1',240,380,0,TO_DATE('2013-11-23 17:16:38','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:56:51 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70470 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:56:54 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,69949,70471,0,161,TO_DATE('2013-11-23 17:17:01','YYYY-MM-DD HH24:MI:SS'),100,'User defined list element #2',0,'@$Element_U2@=Y','U','The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','Y','N','N','N','N','N','N','User List 2',250,380,0,TO_DATE('2013-11-23 17:17:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:56:54 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70471 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:56:57 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,69938,70472,0,161,TO_DATE('2013-11-23 17:17:32','YYYY-MM-DD HH24:MI:SS'),100,0,'U','Y','Y','Y','Y','N','N','N','N','N','Alias',260,380,0,TO_DATE('2013-11-23 17:17:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:56:57 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70472 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:56:57 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Column SET IsActive='N',Updated=TO_DATE('2014-04-26 16:56:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=69951
;

-- Apr 26, 2014 4:56:57 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
ALTER TABLE GL_JournalLine MODIFY UserElement2_ID DECIMAL(10) DEFAULT NULL
;

-- Apr 26, 2014 4:56:57 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Column SET IsActive='N',Updated=TO_DATE('2014-04-26 16:56:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=69950
;

-- Apr 26, 2014 4:56:57 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
ALTER TABLE GL_JournalLine MODIFY UserElement1_ID DECIMAL(10) DEFAULT NULL
;

-- Apr 26, 2014 4:56:59 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Menu (Action,AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES ('W',0,53690,0,132,TO_DATE('2013-11-23 17:19:33','YYYY-MM-DD HH24:MI:SS'),100,'U','Y','Y','N','N','N','GL Journal',TO_DATE('2013-11-23 17:19:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:56:59 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53690 AND NOT EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Menu_ID=t.AD_Menu_ID)
;

-- Apr 26, 2014 4:57:00 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WindowType,WinHeight,WinWidth) VALUES (0,0,53296,TO_DATE('2013-11-23 17:20:12','YYYY-MM-DD HH24:MI:SS'),100,'Enter and change Manual Journal Entries','U','The GL Journal Window allows you to enter and modify manual journal entries','Y','N','N','Y','GL Journal','N',TO_DATE('2013-11-23 17:20:12','YYYY-MM-DD HH24:MI:SS'),100,'M',0,0)
;

-- Apr 26, 2014 4:57:00 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53296 AND NOT EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Window_ID=t.AD_Window_ID)
;

-- Apr 26, 2014 4:57:06 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,EntityType,HasTree,ImportFields,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy,WhereClause) VALUES (0,0,53804,224,53296,TO_DATE('2013-11-23 17:20:47','YYYY-MM-DD HH24:MI:SS'),100,'U','N','N','Y','N','Y','Y','N','Y','N','N','Journal','N',10,0,TO_DATE('2013-11-23 17:20:47','YYYY-MM-DD HH24:MI:SS'),100,'GL_JournalBatch_ID IS NULL')
;

-- Apr 26, 2014 4:57:06 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53804 AND NOT EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Tab_ID=t.AD_Tab_ID)
;

-- Apr 26, 2014 4:57:13 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1618,70473,0,53804,TO_DATE('2013-11-23 17:21:28','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.',0,'U','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','Y','N','N','N','Y','N','Client',10,10,0,TO_DATE('2013-11-23 17:21:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:57:13 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70473 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:57:17 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1619,70474,0,53804,TO_DATE('2013-11-23 17:21:49','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client',0,'U','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','Y','N','N','N','N','Y','Organization',20,20,0,TO_DATE('2013-11-23 17:21:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:57:17 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70474 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:57:19 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1632,70475,0,53804,TO_DATE('2013-11-23 17:22:10','YYYY-MM-DD HH24:MI:SS'),100,'Rules for accounting',0,'U','An Accounting Schema defines the rules used in accounting such as costing method, currency and calendar','Y','Y','Y','Y','N','N','N','N','N','Accounting Schema',30,30,0,TO_DATE('2013-11-23 17:22:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:57:19 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70475 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:57:19 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Field SET DisplayLength=14,Updated=TO_DATE('2014-04-26 16:57:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=70475
;

-- Apr 26, 2014 4:57:21 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1638,70476,0,53804,TO_DATE('2013-11-23 17:22:52','YYYY-MM-DD HH24:MI:SS'),100,'General Ledger Journal Batch',140,'U','The General Ledger Journal Batch identifies a group of journals to be processed as a group.','Y','Y','Y','Y','N','N','N','Y','Y','Journal Batch',40,40,0,TO_DATE('2013-11-23 17:22:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:57:21 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70476 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:57:24 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1626,70477,0,53804,TO_DATE('2013-11-23 17:23:19','YYYY-MM-DD HH24:MI:SS'),100,'Document sequence number of the document',20,'U','The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','Y','N','N','N','N','N','Document No',50,50,0,TO_DATE('2013-11-23 17:23:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:57:24 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70477 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:57:28 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1630,70478,0,53804,TO_DATE('2013-11-23 17:23:36','YYYY-MM-DD HH24:MI:SS'),100,'Optional short description of the record',60,'U','A description is limited to 255 characters.','Y','Y','Y','Y','N','N','N','N','N','Description',60,60,0,TO_DATE('2013-11-23 17:23:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:57:28 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70478 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:57:30 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1620,70479,0,53804,TO_DATE('2013-11-23 17:24:05','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system',1,'U','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','Y','N','N','N','Y','Y','Active',70,70,0,TO_DATE('2013-11-23 17:24:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:57:30 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70479 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:57:31 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1631,70480,0,53804,TO_DATE('2013-11-23 17:24:26','YYYY-MM-DD HH24:MI:SS'),100,'The type of posted amount for the transaction',0,'U','The Posting Type indicates the type of amount (Actual, Budget, Reservation, Commitment, Statistical) the transaction.','Y','Y','Y','Y','N','N','N','N','N','PostingType',80,80,0,TO_DATE('2013-11-23 17:24:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:57:31 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70480 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:57:34 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,2334,70481,0,53804,TO_DATE('2013-11-23 17:24:50','YYYY-MM-DD HH24:MI:SS'),100,'General Ledger Budget',14,'@PostingType@=''B''','U','The General Ledger Budget identifies a user defined budget.  These can be used in reporting as a comparison against your actual amounts.','Y','Y','Y','Y','N','N','N','N','Y','Budget',90,90,0,TO_DATE('2013-11-23 17:24:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:57:34 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70481 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:57:37 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1625,70482,0,53804,TO_DATE('2013-11-23 17:25:32','YYYY-MM-DD HH24:MI:SS'),100,'Document type or rules',14,'U','The Document Type determines document sequence and processing rules','Y','Y','Y','Y','N','N','N','N','N','Document Type',100,100,0,TO_DATE('2013-11-23 17:25:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:57:37 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70482 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:57:39 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1633,70483,0,53804,TO_DATE('2013-11-23 17:26:04','YYYY-MM-DD HH24:MI:SS'),100,'General Ledger Category',0,'U','The General Ledger Category is an optional, user defined method of grouping journal lines.','Y','Y','Y','Y','N','N','N','N','Y','GL Category',110,110,0,TO_DATE('2013-11-23 17:26:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:57:39 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70483 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:57:42 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1634,70484,0,53804,TO_DATE('2013-11-23 17:26:30','YYYY-MM-DD HH24:MI:SS'),100,'Date of the Document',14,'U','The Document Date indicates the date the document was generated.  It may or may not be the same as the accounting date.','Y','Y','Y','Y','N','N','N','N','N','Document Date',120,120,0,TO_DATE('2013-11-23 17:26:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:57:42 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70484 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:57:48 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1635,70485,0,53804,TO_DATE('2013-11-23 17:26:50','YYYY-MM-DD HH24:MI:SS'),100,'Accounting Date',14,'U','The Accounting Date indicates the date to be used on the General Ledger account entries generated from this document. It is also used for any currency conversion.','Y','Y','Y','Y','N','N','N','N','Y','Account Date',130,130,0,TO_DATE('2013-11-23 17:26:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:57:48 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70485 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:57:51 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1636,70486,0,53804,TO_DATE('2013-11-23 17:27:08','YYYY-MM-DD HH24:MI:SS'),100,'Period of the Calendar',0,'U','The Period indicates an exclusive range of dates for a calendar.','Y','Y','Y','Y','N','N','N','N','N','Period',140,140,0,TO_DATE('2013-11-23 17:27:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:57:51 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70486 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:57:51 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Field SET DisplayLength=14,Updated=TO_DATE('2014-04-26 16:57:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=70486
;

-- Apr 26, 2014 4:57:54 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1637,70487,0,53804,TO_DATE('2013-11-23 17:27:36','YYYY-MM-DD HH24:MI:SS'),100,'The Currency for this record',14,'U','Indicates the Currency to be used when processing or reporting on this record','Y','Y','Y','Y','N','N','N','N','Y','Currency',150,150,0,TO_DATE('2013-11-23 17:27:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:57:54 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70487 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:57:59 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,10295,70488,0,53804,TO_DATE('2013-11-23 17:28:02','YYYY-MM-DD HH24:MI:SS'),100,'Currency Conversion Rate Type',14,'@$C_Currency_ID@!@C_Currency_ID@','U','The Currency Conversion Rate Type lets you define different type of rates, e.g. Spot, Corporate and/or Sell/Buy rates.','Y','Y','Y','Y','N','N','N','N','N','Currency Type',160,160,0,TO_DATE('2013-11-23 17:28:02','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:57:59 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70488 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:58:02 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1792,70489,0,53804,TO_DATE('2013-11-23 17:28:30','YYYY-MM-DD HH24:MI:SS'),100,'Currency Conversion Rate',26,'@$C_Currency_ID@!@C_Currency_ID@','U','The Currency Conversion Rate indicates the rate to use when converting the source currency to the accounting currency','Y','Y','Y','Y','N','N','N','N','Y','Rate',170,170,0,TO_DATE('2013-11-23 17:28:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:58:02 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70489 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:58:06 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_FieldGroup_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,5952,70490,101,0,53804,TO_DATE('2013-11-23 17:29:05','YYYY-MM-DD HH24:MI:SS'),100,'If not zero, the Debit amount of the document must be equal this amount',26,'U','If the control amount is zero, no check is performed.
Otherwise the total Debit amount must be equal to the control amount, before the document is processed.','Y','Y','Y','Y','N','N','N','N','N','Control Amount',180,180,0,TO_DATE('2013-11-23 17:29:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:58:06 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70490 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:58:11 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1790,70491,0,53804,TO_DATE('2013-11-23 17:29:28','YYYY-MM-DD HH24:MI:SS'),100,'Indicates if this document requires approval',0,'U','The Approved checkbox indicates if this document requires approval before it can be processed.','Y','Y','Y','Y','N','N','N','N','Y','Approved',190,190,0,TO_DATE('2013-11-23 17:29:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:58:11 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70491 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:58:14 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_FieldGroup_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1639,70492,101,0,53804,TO_DATE('2013-11-23 17:30:01','YYYY-MM-DD HH24:MI:SS'),100,'Total debit in document currency',26,'U','The Total Debit indicates the total debit amount for a journal or journal batch in the source currency','Y','Y','Y','Y','N','N','N','Y','N','Total Debit',200,200,0,TO_DATE('2013-11-23 17:30:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:58:14 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70492 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:58:22 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_FieldGroup_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1640,70493,101,0,53804,TO_DATE('2013-11-23 17:30:27','YYYY-MM-DD HH24:MI:SS'),100,'Total Credit in document currency',26,'U','The Total Credit indicates the total credit amount for a journal or journal batch in the source currency','Y','Y','Y','Y','N','N','N','Y','Y','Total Credit',210,210,0,TO_DATE('2013-11-23 17:30:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:58:22 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70493 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:58:25 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_FieldGroup_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1627,70494,101,0,53804,TO_DATE('2013-11-23 17:30:56','YYYY-MM-DD HH24:MI:SS'),100,'The current status of the document',14,'U','The Document Status indicates the status of a document at this time.  If you want to change the document status, use the Document Action field','Y','Y','Y','Y','N','N','N','Y','N','Document Status',220,220,0,TO_DATE('2013-11-23 17:30:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:58:25 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70494 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:58:28 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1789,70495,0,53804,TO_DATE('2013-11-23 17:31:22','YYYY-MM-DD HH24:MI:SS'),100,'The targeted status of the document',0,'U','You find the current status in the Document Status field. The options are listed in a popup','Y','Y','Y','Y','N','N','N','N','Y','Document Action',230,230,0,TO_DATE('2013-11-23 17:31:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:58:28 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70495 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:58:33 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1629,70496,0,53804,TO_DATE('2013-11-23 17:31:44','YYYY-MM-DD HH24:MI:SS'),100,'Posting status',23,'@Processed@=Y & @#ShowAcct@=Y','U','The Posted field indicates the status of the Generation of General Ledger Accounting Lines ','Y','Y','Y','Y','N','N','N','N','N','Posted',240,240,0,TO_DATE('2013-11-23 17:31:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:58:33 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70496 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:58:36 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,HasTree,ImportFields,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53805,226,53296,TO_DATE('2013-11-23 17:32:22','YYYY-MM-DD HH24:MI:SS'),100,'General Ledger Journal Line','U','N','N','Y','N','N','Y','N','Y','N','N','Line','N',20,0,TO_DATE('2013-11-23 17:32:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:58:36 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53805 AND NOT EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Tab_ID=t.AD_Tab_ID)
;

-- Apr 26, 2014 4:58:40 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1660,70497,0,53805,TO_DATE('2013-11-23 17:33:36','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.',14,'U','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','Y','N','N','N','Y','N','Client',10,10,0,TO_DATE('2013-11-23 17:33:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:58:40 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70497 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:58:42 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1661,70498,0,53805,TO_DATE('2013-11-23 17:34:28','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client',0,'U','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','Y','N','N','N','N','Y','Organization',20,20,0,TO_DATE('2013-11-23 17:34:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:58:42 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70498 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:58:44 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1667,70499,0,53805,TO_DATE('2013-11-23 17:34:59','YYYY-MM-DD HH24:MI:SS'),100,'General Ledger Journal',14,'U','The General Ledger Journal identifies a group of journal lines which represent a logical business transaction','Y','Y','Y','Y','N','N','N','Y','N','Journal',30,30,0,TO_DATE('2013-11-23 17:34:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:58:44 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70499 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:58:46 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1675,70500,0,53805,TO_DATE('2013-11-23 17:35:20','YYYY-MM-DD HH24:MI:SS'),100,'Unique line for this document',110,'U','Indicates the unique line for a document.  It will also control the display order of the lines within a document.','Y','Y','Y','Y','N','N','N','N','N','Line No',40,40,0,TO_DATE('2013-11-23 17:35:20','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:58:46 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70500 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:58:51 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1676,70501,0,53805,TO_DATE('2013-11-23 17:35:49','YYYY-MM-DD HH24:MI:SS'),100,'Optional short description of the record',60,'U','A description is limited to 255 characters.','Y','Y','Y','Y','N','N','N','N','N','Description',50,50,0,TO_DATE('2013-11-23 17:35:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:58:51 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70501 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:58:52 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1662,70502,0,53805,TO_DATE('2013-11-23 17:36:18','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system',1,'U','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','Y','N','N','N','Y','N','Active',60,60,0,TO_DATE('2013-11-23 17:36:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:58:52 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70502 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:58:53 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1795,70503,0,53805,TO_DATE('2013-11-23 17:36:45','YYYY-MM-DD HH24:MI:SS'),100,'This Line is generated',1,'U','The Generated checkbox identifies a journal line that was generated from a source document.  Lines could also be entered manually or imported.','Y','Y','Y','Y','N','N','N','Y','Y','Generated',70,70,0,TO_DATE('2013-11-23 17:36:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:58:53 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70503 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:58:57 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1678,70504,0,53805,TO_DATE('2013-11-23 17:37:05','YYYY-MM-DD HH24:MI:SS'),100,'The Currency for this record',14,'U','Indicates the Currency to be used when processing or reporting on this record','Y','Y','Y','Y','N','N','N','N','N','Currency',80,80,0,TO_DATE('2013-11-23 17:37:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:58:57 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70504 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:58:59 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,2336,70505,0,53805,TO_DATE('2013-11-23 17:37:31','YYYY-MM-DD HH24:MI:SS'),100,'Accounting Date',14,'@C_Currency_ID@!@$C_Currency_ID@','U','The Accounting Date indicates the date to be used on the General Ledger account entries generated from this document. It is also used for any currency conversion.','Y','Y','Y','Y','N','N','N','N','Y','Account Date',90,90,0,TO_DATE('2013-11-23 17:37:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:58:59 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70505 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:59:02 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,10296,70506,0,53805,TO_DATE('2013-11-23 17:37:59','YYYY-MM-DD HH24:MI:SS'),100,'Currency Conversion Rate Type',14,'@C_Currency_ID@!@$C_Currency_ID@','U','The Currency Conversion Rate Type lets you define different type of rates, e.g. Spot, Corporate and/or Sell/Buy rates.','Y','Y','Y','Y','N','N','N','N','N','Currency Type',100,100,0,TO_DATE('2013-11-23 17:37:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:59:02 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70506 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:59:03 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_FieldGroup_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,69936,70507,104,0,53805,TO_DATE('2013-11-23 17:38:27','YYYY-MM-DD HH24:MI:SS'),100,'Account used',10,'U','The (natural) account used','Y','Y','Y','Y','N','N','N','N','N','Account',110,110,0,TO_DATE('2013-11-23 17:38:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:59:03 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70507 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:59:03 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,69940,70508,0,53805,TO_DATE('2013-11-23 17:39:00','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Business Partner',10,'U','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Y','Y','Y','N','N','N','N','N','Business Partner ',120,120,0,TO_DATE('2013-11-23 17:39:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:59:03 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70508 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:59:07 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,69937,70509,0,53805,TO_DATE('2013-11-23 17:39:26','YYYY-MM-DD HH24:MI:SS'),100,'Performing or initiating organization',10,'@$Element_OT@=Y','U','The organization which performs or initiates this transaction (for another organization).  The owning Organization may not be the transaction organization in a service bureau environment, with centralized services, and inter-organization transactions.','Y','Y','Y','Y','N','N','N','N','N','Trx Organization',130,130,0,TO_DATE('2013-11-23 17:39:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:59:07 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70509 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:59:09 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,69939,70510,0,53805,TO_DATE('2013-11-23 17:40:17','YYYY-MM-DD HH24:MI:SS'),100,'Business Activity',10,'@$Element_AY@=Y','U','Activities indicate tasks that are performed and used to utilize Activity based Costing','Y','Y','Y','Y','N','N','N','N','N','Activity',140,140,0,TO_DATE('2013-11-23 17:40:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:59:09 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70510 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:59:17 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,69941,70511,0,53805,TO_DATE('2013-11-23 17:40:39','YYYY-MM-DD HH24:MI:SS'),100,'Marketing Campaign',10,'@$Element_MC@=Y','U','The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','Y','Y','Y','N','N','N','N','N','Campaign',150,150,0,TO_DATE('2013-11-23 17:40:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:59:17 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70511 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:59:27 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,69945,70512,0,53805,TO_DATE('2013-11-23 17:41:16','YYYY-MM-DD HH24:MI:SS'),100,'Sales coverage region',10,'@$Element_SR@=Y','U','The Sales Region indicates a specific area of sales coverage.','Y','Y','Y','Y','N','N','N','N','N','Sales Region',160,160,0,TO_DATE('2013-11-23 17:41:16','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:59:27 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70512 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:59:36 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,69944,70513,0,53805,TO_DATE('2013-11-23 17:41:37','YYYY-MM-DD HH24:MI:SS'),100,'Financial Project',20,'@$Element_PJ@=Y','U','A Project allows you to track and control internal or external activities.','Y','Y','Y','Y','N','N','N','N','N','Project',170,170,0,TO_DATE('2013-11-23 17:41:37','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:59:36 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70513 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:59:45 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,69946,70514,0,53805,TO_DATE('2013-11-23 17:41:59','YYYY-MM-DD HH24:MI:SS'),100,'Sub account for Element Value',18,'@$Element_SA@=Y','U','The Element Value (e.g. Account) may have optional sub accounts for further detail. The sub account is dependent on the value of the account, so a further specification. If the sub-accounts are more or less the same, consider using another accounting dimension.','Y','Y','Y','Y','N','N','N','N','N','Sub Account',180,180,0,TO_DATE('2013-11-23 17:41:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:59:45 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70514 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 4:59:54 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,69947,70515,0,53805,TO_DATE('2013-11-23 17:42:20','YYYY-MM-DD HH24:MI:SS'),100,'Product, Service, Item',10,'@$Element_PR@=Y','U','Identifies an item which is either purchased or sold in this organization.','Y','Y','Y','Y','N','N','N','N','N','Product',190,190,0,TO_DATE('2013-11-23 17:42:20','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 4:59:54 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70515 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 5:00:03 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,69942,70516,0,53805,TO_DATE('2013-11-23 17:42:51','YYYY-MM-DD HH24:MI:SS'),100,'Location that inventory was moved from',10,'@$Element_LF@=Y','U','The Location From indicates the location that a product was moved from.','Y','Y','Y','Y','N','N','N','N','N','Location From',200,200,0,TO_DATE('2013-11-23 17:42:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 5:00:03 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70516 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 5:00:12 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,69943,70517,0,53805,TO_DATE('2013-11-23 17:43:14','YYYY-MM-DD HH24:MI:SS'),100,'Location that inventory was moved to',10,'@$Element_LT@=Y','U','The Location To indicates the location that a product was moved to.','Y','Y','Y','Y','N','N','N','N','N','Location To',210,210,0,TO_DATE('2013-11-23 17:43:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 5:00:12 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70517 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 5:00:13 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Column SET IsActive='Y',Updated=TO_DATE('2014-04-26 17:00:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=69950
;

-- Apr 26, 2014 5:00:13 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
ALTER TABLE GL_JournalLine MODIFY UserElement1_ID DECIMAL(10) DEFAULT NULL
;

-- Apr 26, 2014 5:00:14 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Column SET IsActive='Y',Updated=TO_DATE('2014-04-26 17:00:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=69951
;

-- Apr 26, 2014 5:00:14 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
ALTER TABLE GL_JournalLine MODIFY UserElement2_ID DECIMAL(10) DEFAULT NULL
;

-- Apr 26, 2014 5:00:23 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,69950,70518,0,53805,TO_DATE('2013-11-23 17:44:14','YYYY-MM-DD HH24:MI:SS'),100,'User defined accounting Element',10,'@$Element_X1@=Y','U','A user defined accounting element refers to a Adempiere table. This allows to use any table content as an accounting dimension (e.g. Project Task).  Note that User Elements are optional and are populated from the context of the document (i.e. not requested)','Y','Y','Y','Y','N','N','N','N','N','User Element 1',220,220,0,TO_DATE('2013-11-23 17:44:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 5:00:23 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70518 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 5:00:34 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,69951,70519,0,53805,TO_DATE('2013-11-23 17:45:00','YYYY-MM-DD HH24:MI:SS'),100,'User defined accounting Element',10,'@$Element_X2@=Y','U','A user defined accounting element refers to a Adempiere table. This allows to use any table content as an accounting dimension (e.g. Project Task).  Note that User Elements are optional and are populated from the context of the document (i.e. not requested)','Y','Y','Y','Y','N','N','N','N','N','User Element 2',230,230,0,TO_DATE('2013-11-23 17:45:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 5:00:34 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70519 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 5:00:35 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Column SET IsActive='N',Updated=TO_DATE('2014-04-26 17:00:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=69951
;

-- Apr 26, 2014 5:00:35 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
ALTER TABLE GL_JournalLine MODIFY UserElement2_ID DECIMAL(10) DEFAULT NULL
;

-- Apr 26, 2014 5:00:35 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Column SET IsActive='N',Updated=TO_DATE('2014-04-26 17:00:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=69950
;

-- Apr 26, 2014 5:00:35 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
ALTER TABLE GL_JournalLine MODIFY UserElement1_ID DECIMAL(10) DEFAULT NULL
;

-- Apr 26, 2014 5:00:39 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,69948,70520,0,53805,TO_DATE('2013-11-23 17:45:36','YYYY-MM-DD HH24:MI:SS'),100,'User defined list element #1',10,'@$Element_U1@=Y','U','The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','Y','Y','N','N','N','N','N','User List 1',240,240,0,TO_DATE('2013-11-23 17:45:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 5:00:39 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70520 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 5:00:39 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,69949,70521,0,53805,TO_DATE('2013-11-23 17:46:06','YYYY-MM-DD HH24:MI:SS'),100,'User defined list element #2',10,'@$Element_U2@=Y','U','The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','Y','Y','N','N','N','N','N','User List 2',250,250,0,TO_DATE('2013-11-23 17:46:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 5:00:39 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70521 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 5:00:46 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,69938,70522,0,53805,TO_DATE('2013-11-23 17:46:33','YYYY-MM-DD HH24:MI:SS'),100,10,'U','Y','Y','Y','Y','N','N','N','N','N','Alias',260,260,0,TO_DATE('2013-11-23 17:46:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 5:00:46 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70522 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 5:00:53 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1806,70523,0,53805,TO_DATE('2013-11-23 17:47:04','YYYY-MM-DD HH24:MI:SS'),100,'Currency Conversion Rate',26,'@C_Currency_ID@!@$C_Currency_ID@','U','The Currency Conversion Rate indicates the rate to use when converting the source currency to the accounting currency','Y','Y','Y','Y','N','N','N','Y','N','Rate',270,270,0,TO_DATE('2013-11-23 17:47:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 5:00:53 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70523 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 5:01:01 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_FieldGroup_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,5955,70524,104,0,53805,TO_DATE('2013-11-23 17:47:32','YYYY-MM-DD HH24:MI:SS'),100,'Valid Account Combination',26,'U','The Combination identifies a valid combination of element which represent a GL account.','Y','Y','Y','Y','N','N','N','N','N','Combination',280,280,0,TO_DATE('2013-11-23 17:47:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 5:01:01 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70524 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 5:01:05 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,56070,70525,0,53805,TO_DATE('2013-11-23 17:47:57','YYYY-MM-DD HH24:MI:SS'),100,1,'U','Y','Y','Y','Y','N','N','N','N','Y','Asset Related?',290,290,0,TO_DATE('2013-11-23 17:47:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 5:01:05 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70525 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 5:01:15 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,56069,70526,0,53805,TO_DATE('2013-11-23 17:48:24','YYYY-MM-DD HH24:MI:SS'),100,'Asset used internally or by customers',22,'@A_CreateAsset@=''Y''','U','An asset is either created by purchasing or by delivering a product.  An asset can be used internally or be a customer asset.','Y','Y','Y','Y','N','N','N','N','N','Asset',300,300,0,TO_DATE('2013-11-23 17:48:24','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 5:01:15 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70526 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 5:01:21 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,56068,70527,0,53805,TO_DATE('2013-11-23 17:48:56','YYYY-MM-DD HH24:MI:SS'),100,'Group of Assets',0,'@A_Asset_ID@<1&@A_CreateAsset@=''Y''','U','The group of assets determines default accounts.  If an asset group is selected in the product category, assets are created when delivering the asset.','Y','Y','Y','Y','N','N','N','N','Y','Asset Group',310,310,0,TO_DATE('2013-11-23 17:48:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 5:01:21 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70527 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 5:01:27 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_FieldGroup_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1679,70528,103,0,53805,TO_DATE('2013-11-23 17:49:21','YYYY-MM-DD HH24:MI:SS'),100,'Source Debit Amount',26,'U','The Source Debit Amount indicates the credit amount for this line in the source currency.','Y','Y','Y','Y','N','N','N','N','N','Source Debit',320,320,0,TO_DATE('2013-11-23 17:49:21','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 5:01:27 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70528 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 5:01:35 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_FieldGroup_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1680,70529,103,0,53805,TO_DATE('2013-11-23 17:49:48','YYYY-MM-DD HH24:MI:SS'),100,'Source Credit Amount',26,'U','The Source Credit Amount indicates the credit amount for this line in the source currency.','Y','Y','Y','Y','N','N','N','N','Y','Source Credit',330,330,0,TO_DATE('2013-11-23 17:49:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 5:01:35 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70529 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 5:01:40 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_FieldGroup_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1681,70530,103,0,53805,TO_DATE('2013-11-23 17:50:11','YYYY-MM-DD HH24:MI:SS'),100,'Accounted Debit Amount',26,'U','The Account Debit Amount indicates the transaction amount converted to this organization''s accounting currency','Y','Y','Y','Y','N','N','N','Y','N','Accounted Debit',340,340,0,TO_DATE('2013-11-23 17:50:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 5:01:40 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70530 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 5:01:46 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_FieldGroup_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1682,70531,103,0,53805,TO_DATE('2013-11-23 17:50:53','YYYY-MM-DD HH24:MI:SS'),100,'Accounted Credit Amount',26,'U','The Account Credit Amount indicates the transaction amount converted to this organization''s accounting currency','Y','Y','Y','Y','N','N','N','Y','Y','Accounted Credit',350,350,0,TO_DATE('2013-11-23 17:50:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 5:01:46 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70531 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 5:01:51 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_FieldGroup_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,2337,70532,102,0,53805,TO_DATE('2013-11-23 17:51:20','YYYY-MM-DD HH24:MI:SS'),100,'Unit of Measure',14,'U','The UOM defines a unique non monetary Unit of Measure','Y','Y','Y','Y','N','N','N','N','N','UOM',360,360,0,TO_DATE('2013-11-23 17:51:20','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 5:01:51 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70532 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 5:01:55 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_FieldGroup_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,2338,70533,102,0,53805,TO_DATE('2013-11-23 17:51:46','YYYY-MM-DD HH24:MI:SS'),100,'Quantity',26,'U','The Quantity indicates the number of a specific product or item for this document.','Y','Y','Y','Y','N','N','N','N','Y','Quantity',370,370,0,TO_DATE('2013-11-23 17:51:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 5:01:55 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70533 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 5:01:55 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Menu SET AD_Window_ID=53296,Updated=TO_DATE('2014-04-26 17:01:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Menu_ID=53690
;

-- Apr 26, 2014 5:01:55 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Menu SET IsSOTrx='Y',Updated=TO_DATE('2014-04-26 17:01:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Menu_ID=53690
;

-- Apr 26, 2014 5:03:15 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Tab SET TabLevel=1,Updated=TO_DATE('2014-04-26 17:03:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53805
;

-- Apr 26, 2014 5:03:15 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Window SET WindowType='T',Updated=TO_DATE('2014-04-26 17:03:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Window_ID=53296
;

-- Apr 26, 2014 5:03:16 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Window SET AD_Image_ID=102,Updated=TO_DATE('2014-04-26 17:03:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Window_ID=53296
;

-- Apr 26, 2014 5:03:16 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Column SET IsActive='Y',Updated=TO_DATE('2014-04-26 17:03:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=69950
;

-- Apr 26, 2014 5:03:17 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
ALTER TABLE GL_JournalLine MODIFY UserElement1_ID DECIMAL(10) DEFAULT NULL
;

-- Apr 26, 2014 5:03:17 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Column SET IsActive='N',Updated=TO_DATE('2014-04-26 17:03:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=69950
;

-- Apr 26, 2014 5:03:17 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
ALTER TABLE GL_JournalLine MODIFY UserElement1_ID DECIMAL(10) DEFAULT NULL
;

-- Apr 26, 2014 5:03:17 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Column SET IsActive='Y',Updated=TO_DATE('2014-04-26 17:03:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=69951
;

-- Apr 26, 2014 5:03:17 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
ALTER TABLE GL_JournalLine MODIFY UserElement2_ID DECIMAL(10) DEFAULT NULL
;

-- Apr 26, 2014 5:03:18 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Column SET IsActive='N',Updated=TO_DATE('2014-04-26 17:03:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=69951
;

-- Apr 26, 2014 5:03:18 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
ALTER TABLE GL_JournalLine MODIFY UserElement2_ID DECIMAL(10) DEFAULT NULL
;

-- Apr 26, 2014 5:03:18 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Table SET AD_Window_ID=NULL,Updated=TO_DATE('2014-04-26 17:03:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=226
;

-- Apr 26, 2014 5:03:19 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Table SET AD_Window_ID=132,Updated=TO_DATE('2014-04-26 17:03:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=226
;

-- Apr 26, 2014 5:03:20 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Table SET AD_Window_ID=NULL,Updated=TO_DATE('2014-04-26 17:03:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=226
;

-- Apr 26, 2014 5:03:20 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Table SET AD_Window_ID=132,Updated=TO_DATE('2014-04-26 17:03:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=226
;

-- Apr 26, 2014 5:03:25 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1663,70535,0,53805,TO_DATE('2013-11-25 11:31:44','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created',0,'U','The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','N','N','N','Created',380,380,0,TO_DATE('2013-11-25 11:31:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 5:03:25 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70535 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 5:03:31 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1664,70536,0,53805,TO_DATE('2013-11-25 11:32:11','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records',0,'U','The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','N','N','N','Created By',390,390,0,TO_DATE('2013-11-25 11:32:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 5:03:31 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70536 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 5:03:36 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1665,70537,0,53805,TO_DATE('2013-11-25 11:32:34','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated',0,'U','The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','N','N','N','Updated',400,400,0,TO_DATE('2013-11-25 11:32:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 5:03:36 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70537 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 5:03:39 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1666,70538,0,53805,TO_DATE('2013-11-25 11:32:58','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records',0,'U','The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','N','N','N','Updated By',410,410,0,TO_DATE('2013-11-25 11:32:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 5:03:39 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70538 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 5:03:45 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1621,70539,0,53804,TO_DATE('2013-11-25 11:33:46','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created',0,'U','The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','N','N','N','Created',250,250,0,TO_DATE('2013-11-25 11:33:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 5:03:45 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70539 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 5:03:50 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1622,70540,0,53804,TO_DATE('2013-11-25 11:34:05','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records',0,'U','The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','N','N','N','Created By',260,260,0,TO_DATE('2013-11-25 11:34:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 5:03:50 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70540 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 5:03:54 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1623,70541,0,53804,TO_DATE('2013-11-25 11:34:37','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated',0,'U','The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','N','N','N','Updated',270,270,0,TO_DATE('2013-11-25 11:34:37','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 5:03:54 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70541 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 5:03:57 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1624,70542,0,53804,TO_DATE('2013-11-25 11:35:01','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records',0,'U','The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','N','N','N','Updated By',280,280,0,TO_DATE('2013-11-25 11:35:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 5:03:57 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70542 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 5:04:02 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1659,70543,0,53805,TO_DATE('2013-11-25 11:58:26','YYYY-MM-DD HH24:MI:SS'),100,'General Ledger Journal Line',0,'U','The General Ledger Journal Line identifies a single transaction in a journal.','Y','Y','N','N','N','N','N','N','N','Journal Line',420,420,0,TO_DATE('2013-11-25 11:58:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 5:04:02 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70543 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 5:04:06 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,12068,70544,0,53805,TO_DATE('2013-11-25 11:59:07','YYYY-MM-DD HH24:MI:SS'),100,'The document has been processed',0,'U','The Processed checkbox indicates that a document has been processed.','Y','Y','N','N','N','N','N','N','N','Processed',430,430,0,TO_DATE('2013-11-25 11:59:07','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 5:04:06 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70544 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 5:04:10 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1617,70545,0,53804,TO_DATE('2013-11-25 12:09:04','YYYY-MM-DD HH24:MI:SS'),100,'General Ledger Journal',0,'U','The General Ledger Journal identifies a group of journal lines which represent a logical business transaction','Y','Y','N','Y','N','N','N','N','N','Journal',290,290,0,TO_DATE('2013-11-25 12:09:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 5:04:10 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70545 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 5:04:14 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,5953,70546,0,53804,TO_DATE('2013-11-25 12:11:59','YYYY-MM-DD HH24:MI:SS'),100,'The document has been processed',0,'U','The Processed checkbox indicates that a document has been processed.','Y','Y','N','Y','N','N','N','N','N','Processed',300,300,0,TO_DATE('2013-11-25 12:11:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 5:04:14 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70546 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 5:04:17 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,1628,70547,0,53804,TO_DATE('2013-11-25 12:12:29','YYYY-MM-DD HH24:MI:SS'),100,'Indicates if this document / line is printed',0,'U','The Printed checkbox indicates if this document or line will included when printing.','Y','Y','N','Y','N','N','N','N','N','Printed',310,310,0,TO_DATE('2013-11-25 12:12:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 5:04:17 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70547 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 5:04:21 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,59042,70548,0,53804,TO_DATE('2013-11-25 12:12:55','YYYY-MM-DD HH24:MI:SS'),100,'The datetime (expressed in decimal format) when the document has been processed',0,'U','The ProcessedOn DateTime save the exact moment (nanoseconds precision if allowed by the DB) when a document has been processed.','Y','Y','N','Y','N','N','N','N','N','Processed On',320,320,0,TO_DATE('2013-11-25 12:12:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 5:04:21 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70548 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 5:04:24 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsDisplayedGrid,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SeqNoGrid,SortNo,Updated,UpdatedBy) VALUES (0,5954,70549,0,53804,TO_DATE('2013-11-25 12:13:31','YYYY-MM-DD HH24:MI:SS'),100,0,'U','Y','Y','N','Y','N','N','N','N','N','Process Now',330,330,0,TO_DATE('2013-11-25 12:13:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 26, 2014 5:04:24 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=70549 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 26, 2014 5:20:05 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
UPDATE AD_Column SET DefaultValue='@SQL=SELECT Description FROM GL_JournalLine WHERE Line=(SELECT COALESCE(MAX(Line),0)  FROM GL_JournalLine where GL_JournalLine_ID=@GL_JournalLine_ID@) AND GL_JournalLine_ID=@GL_JournalLine_ID@',Updated=TO_DATE('2014-04-26 17:20:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=1676
;

-- Apr 26, 2014 5:20:58 PM GMT05:30
-- Simplified GL Journal and Gl journal batch
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID,AD_Tree_ID,Created,CreatedBy,IsActive,Node_ID,SeqNo,Updated,UpdatedBy) VALUES (0,0,10,TO_DATE('2014-04-26 17:20:58','YYYY-MM-DD HH24:MI:SS'),100,'Y',53690,21,TO_DATE('2014-04-26 17:20:58','YYYY-MM-DD HH24:MI:SS'),100)
;
