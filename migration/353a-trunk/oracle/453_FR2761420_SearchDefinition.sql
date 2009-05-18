-- Feb 18, 2009 12:44:58 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Table (AccessLevel,AD_Client_ID,AD_Org_ID,AD_Table_ID,CopyColumnsFromTable,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,LoadSeq,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES ('4',0,0,53169,'N',TO_DATE('2009-02-18 12:44:56','YYYY-MM-DD HH24:MI:SS'),100,'D','N','Y','N','Y','N','N','N',0,'AD_SearchDefinition','L','AD_SearchDefinition',TO_DATE('2009-02-18 12:44:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 18, 2009 12:44:58 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53169 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Feb 18, 2009 12:47:51 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56799,102,0,19,53169,'AD_Client_ID',TO_DATE('2009-02-18 12:47:50','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Client_ID@','Client/Tenant for this installation.','D',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','N','Y','N','N','N','N','N','Client',0,TO_DATE('2009-02-18 12:47:50','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Feb 18, 2009 12:47:51 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56799 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 18, 2009 12:51:43 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,Description,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52046,'AD_Column.AD_Table_ID=@AD_Table_ID@ AND AD_Column.AD_Reference_ID IN (10,11,13,14,22,36)',TO_DATE('2009-02-18 12:51:42','YYYY-MM-DD HH24:MI:SS'),100,'Only columns with text or number datatypes','D','Y','AD_Column in AD_Table with datatype int/string','S',TO_DATE('2009-02-18 12:51:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 18, 2009 12:52:32 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,MandatoryLogic,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56800,104,0,18,3,53169,52046,'AD_Column_ID',TO_DATE('2009-02-18 12:52:32','YYYY-MM-DD HH24:MI:SS'),100,'Column in the table','D',22,'Link to the database column of the table','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','@SearchType@ = T','Column',0,TO_DATE('2009-02-18 12:52:32','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Feb 18, 2009 12:52:32 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56800 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 18, 2009 12:53:41 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56801,113,0,19,53169,'AD_Org_ID',TO_DATE('2009-02-18 12:53:41','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Org_ID@','Organizational entity within client','D',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','N','Y','N','N','N','N','Y','Organization',0,TO_DATE('2009-02-18 12:53:41','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Feb 18, 2009 12:53:41 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56801 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 18, 2009 12:54:35 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53774,0,'AD_SearchDefinition_ID',TO_DATE('2009-02-18 12:54:34','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','AD_SearchDefinition_ID','AD_SearchDefinition_ID',TO_DATE('2009-02-18 12:54:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 18, 2009 12:54:35 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53774 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Feb 18, 2009 12:55:37 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56802,53774,0,13,53169,'AD_SearchDefinition_ID',TO_DATE('2009-02-18 12:55:37','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','Y','N','N','N','N','Y','Y','N','N','N','N','N','AD_SearchDefinition_ID',0,TO_DATE('2009-02-18 12:55:37','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Feb 18, 2009 12:55:37 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56802 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 18, 2009 12:55:54 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_Column SET IsUpdateable='N',Updated=TO_DATE('2009-02-18 12:55:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56801
;

-- Feb 18, 2009 12:57:48 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53290,TO_DATE('2009-02-18 12:57:48','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','AD_Table',TO_DATE('2009-02-18 12:57:48','YYYY-MM-DD HH24:MI:SS'),100,'T')
;

-- Feb 18, 2009 12:57:48 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53290 AND EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Reference_ID!=t.AD_Reference_ID)
;

-- Feb 18, 2009 12:58:36 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Ref_Table (AD_Client_ID,AD_Display,AD_Key,AD_Org_ID,AD_Reference_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,Updated,UpdatedBy) VALUES (0,107,100,0,53290,100,TO_DATE('2009-02-18 12:58:36','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N',TO_DATE('2009-02-18 12:58:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 18, 2009 12:58:46 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_Reference SET Name='AD_Table TableName',Updated=TO_DATE('2009-02-18 12:58:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53290
;

-- Feb 18, 2009 12:58:46 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53290
;

-- Feb 18, 2009 12:59:38 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56803,126,0,18,53290,53169,'AD_Table_ID',TO_DATE('2009-02-18 12:59:38','YYYY-MM-DD HH24:MI:SS'),100,'Database Table information','D',22,'The Database Table provides the information of the table definition','Y','Y','N','N','N','N','N','Y','N','N','N','N','Y','Table',0,TO_DATE('2009-02-18 12:59:38','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Feb 18, 2009 12:59:38 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56803 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 18, 2009 1:01:08 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56804,143,0,18,284,53169,'AD_Window_ID',TO_DATE('2009-02-18 13:01:07','YYYY-MM-DD HH24:MI:SS'),100,'Data entry or display window','D',22,'The Window field identifies a unique Window in the system.','Y','Y','N','N','N','N','N','Y','N','N','N','N','Y','Window',0,TO_DATE('2009-02-18 13:01:07','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Feb 18, 2009 1:01:08 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56804 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 18, 2009 1:02:37 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56805,245,0,16,53169,'Created',TO_DATE('2009-02-18 13:02:36','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','D',7,'The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','N','N','N','N','N','N','N','Created',0,TO_DATE('2009-02-18 13:02:36','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Feb 18, 2009 1:02:37 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56805 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 18, 2009 1:03:36 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56806,246,0,18,110,53169,'CreatedBy',TO_DATE('2009-02-18 13:03:36','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','D',22,'The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','N','N','N','N','N','N','N','Created By',0,TO_DATE('2009-02-18 13:03:36','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Feb 18, 2009 1:03:36 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56806 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 18, 2009 1:03:41 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2009-02-18 13:03:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56805
;

-- Feb 18, 2009 1:05:11 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56807,1315,0,10,53169,'DataType',TO_DATE('2009-02-18 13:05:10','YYYY-MM-DD HH24:MI:SS'),100,'Type of data','D',1,'Y','Y','N','N','N','N','N','Y','N','N','N','N','Y','Data Type',0,TO_DATE('2009-02-18 13:05:10','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Feb 18, 2009 1:05:11 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56807 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 18, 2009 1:06:02 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56808,275,0,10,53169,'Description',TO_DATE('2009-02-18 13:06:02','YYYY-MM-DD HH24:MI:SS'),100,'Optional short description of the record','D',255,'A description is limited to 255 characters.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Description',0,TO_DATE('2009-02-18 13:06:02','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Feb 18, 2009 1:06:02 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56808 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 18, 2009 1:06:57 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,ColumnSQL,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56809,348,0,20,53169,'IsActive',NULL,TO_DATE('2009-02-18 13:06:56','YYYY-MM-DD HH24:MI:SS'),100,'Y','The record is active in the system','D',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','N','Y','N','N','N','N','Y','Active',0,TO_DATE('2009-02-18 13:06:56','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Feb 18, 2009 1:06:57 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56809 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 18, 2009 1:07:50 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56810,469,0,10,53169,'Name',TO_DATE('2009-02-18 13:07:50','YYYY-MM-DD HH24:MI:SS'),100,'Alphanumeric identifier of the entity','D',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Name',0,TO_DATE('2009-02-18 13:07:50','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Feb 18, 2009 1:07:50 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56810 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 18, 2009 1:08:47 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53775,0,'Query',TO_DATE('2009-02-18 13:08:47','YYYY-MM-DD HH24:MI:SS'),100,'SQL','D','Y','Query','Query',TO_DATE('2009-02-18 13:08:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 18, 2009 1:08:47 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53775 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Feb 18, 2009 1:08:51 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_Element SET EntityType='D',Updated=TO_DATE('2009-02-18 13:08:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53775
;

-- Feb 18, 2009 1:09:44 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,MandatoryLogic,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56811,53775,0,14,53169,'Query',TO_DATE('2009-02-18 13:09:43','YYYY-MM-DD HH24:MI:SS'),100,'SQL','D',2000,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','@SearchType@ = Q','Query',0,TO_DATE('2009-02-18 13:09:43','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Feb 18, 2009 1:09:44 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56811 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 18, 2009 1:11:09 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53776,0,'SearchType',TO_DATE('2009-02-18 13:11:09','YYYY-MM-DD HH24:MI:SS'),100,'Which kind of search is used (Query or Table)','D','Y','Search Type','Search Type',TO_DATE('2009-02-18 13:11:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 18, 2009 1:11:09 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53776 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Feb 18, 2009 1:11:43 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56812,53776,0,10,53169,'SearchType',TO_DATE('2009-02-18 13:11:42','YYYY-MM-DD HH24:MI:SS'),100,'Which kind of search is used (Query or Table)','D',1,'Y','Y','N','N','N','N','N','Y','N','N','N','N','Y','Search Type',0,TO_DATE('2009-02-18 13:11:42','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Feb 18, 2009 1:11:43 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56812 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 18, 2009 1:13:42 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53777,0,'TransactionCode',TO_DATE('2009-02-18 13:13:42','YYYY-MM-DD HH24:MI:SS'),100,'The transaction code represents the search definition','D','Y','Transaction Code','Transaction Code',TO_DATE('2009-02-18 13:13:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 18, 2009 1:13:42 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53777 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Feb 18, 2009 1:15:35 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56813,53777,0,10,53169,'TransactionCode',TO_DATE('2009-02-18 13:15:24','YYYY-MM-DD HH24:MI:SS'),100,'The transaction code represents the search definition','D',8,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Transaction Code',0,TO_DATE('2009-02-18 13:15:24','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Feb 18, 2009 1:15:35 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56813 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 18, 2009 1:16:41 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56814,607,0,16,53169,'Updated',TO_DATE('2009-02-18 13:16:40','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','D',7,'The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','N','Y','N','N','N','N','Y','Updated',0,TO_DATE('2009-02-18 13:16:40','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Feb 18, 2009 1:16:41 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56814 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 18, 2009 1:18:26 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56815,608,0,18,110,53169,'UpdatedBy',TO_DATE('2009-02-18 13:18:25','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','D',22,'The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','N','Y','N','N','N','N','N','Updated By',0,TO_DATE('2009-02-18 13:18:25','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Feb 18, 2009 1:18:26 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56815 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 18, 2009 1:18:41 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_Column SET IsUpdateable='N',Updated=TO_DATE('2009-02-18 13:18:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56814
;

-- Feb 18, 2009 1:20:07 PM CET
-- Product Category QA - kthiemann@adempiere.org
CREATE TABLE AD_SearchDefinition (AD_Client_ID NUMBER(10) DEFAULT  NULL  NOT NULL, AD_Column_ID NUMBER(10), AD_Org_ID NUMBER(10) DEFAULT  NULL  NOT NULL, AD_SearchDefinition_ID NUMBER(10) NOT NULL, AD_Table_ID NUMBER(10) NOT NULL, AD_Window_ID NUMBER(10) NOT NULL, Created DATE, CreatedBy NUMBER(10), DataType NVARCHAR2(1) NOT NULL, Description NVARCHAR2(255), IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, Name NVARCHAR2(60), Query NVARCHAR2(2000), SearchType NVARCHAR2(1) NOT NULL, TransactionCode NVARCHAR2(8), Updated DATE NOT NULL, UpdatedBy NUMBER(10) NOT NULL, CONSTRAINT AD_SearchDefinition_Key PRIMARY KEY (AD_SearchDefinition_ID))
;

-- Feb 18, 2009 1:22:09 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WindowType,WinHeight,WinWidth) VALUES (0,0,53069,TO_DATE('2009-02-18 13:22:09','YYYY-MM-DD HH24:MI:SS'),100,'Define transactioncodes for the QuickSearch bar','D','Y','N','N','Y','Search Definition','N',TO_DATE('2009-02-18 13:22:09','YYYY-MM-DD HH24:MI:SS'),100,'M',0,0)
;

-- Feb 18, 2009 1:22:09 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53069 AND EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Window_ID!=t.AD_Window_ID)
;

-- Feb 18, 2009 1:22:09 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,50002,53069,TO_DATE('2009-02-18 13:22:09','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2009-02-18 13:22:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 18, 2009 1:22:09 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,0,53069,TO_DATE('2009-02-18 13:22:09','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2009-02-18 13:22:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 18, 2009 1:22:09 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,102,53069,TO_DATE('2009-02-18 13:22:09','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2009-02-18 13:22:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 18, 2009 1:22:09 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,103,53069,TO_DATE('2009-02-18 13:22:09','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2009-02-18 13:22:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 18, 2009 1:22:09 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,50001,53069,TO_DATE('2009-02-18 13:22:09','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2009-02-18 13:22:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 18, 2009 1:22:41 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_Window_Access SET IsReadWrite='N',Updated=TO_DATE('2009-02-18 13:22:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Role_ID=102 AND AD_Window_ID=53069
;

-- Feb 18, 2009 1:22:42 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_Window_Access SET IsReadWrite='N',Updated=TO_DATE('2009-02-18 13:22:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Role_ID=103 AND AD_Window_ID=53069
;

-- Feb 18, 2009 1:22:43 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_Window_Access SET IsReadWrite='N',Updated=TO_DATE('2009-02-18 13:22:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Role_ID=50001 AND AD_Window_ID=53069
;

-- Feb 18, 2009 1:22:44 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_Window_Access SET IsReadWrite='N',Updated=TO_DATE('2009-02-18 13:22:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Role_ID=50002 AND AD_Window_ID=53069
;

-- Feb 18, 2009 1:22:50 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_Window_Access SET IsActive='N',Updated=TO_DATE('2009-02-18 13:22:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Role_ID=50002 AND AD_Window_ID=53069
;

-- Feb 18, 2009 1:22:51 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_Window_Access SET IsActive='N',Updated=TO_DATE('2009-02-18 13:22:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Role_ID=50001 AND AD_Window_ID=53069
;

-- Feb 18, 2009 1:22:52 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_Window_Access SET IsActive='N',Updated=TO_DATE('2009-02-18 13:22:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Role_ID=103 AND AD_Window_ID=53069
;

-- Feb 18, 2009 1:22:54 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_Window_Access SET IsActive='N',Updated=TO_DATE('2009-02-18 13:22:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Role_ID=102 AND AD_Window_ID=53069
;

-- Feb 18, 2009 1:23:43 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,EntityType,HasTree,ImportFields,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53193,53169,53069,TO_DATE('2009-02-18 13:23:41','YYYY-MM-DD HH24:MI:SS'),100,'D','N','N','Y','N','N','Y','N','N','N','N','Searches','N',10,0,TO_DATE('2009-02-18 13:23:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 18, 2009 1:23:43 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53193 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Feb 18, 2009 1:23:46 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56809,56702,0,53193,TO_DATE('2009-02-18 13:23:45','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system',1,'D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','N','Active',TO_DATE('2009-02-18 13:23:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 18, 2009 1:23:46 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56702 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 18, 2009 1:23:47 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56802,56703,0,53193,TO_DATE('2009-02-18 13:23:46','YYYY-MM-DD HH24:MI:SS'),100,22,'D','Y','Y','N','N','N','N','N','N','AD_SearchDefinition_ID',TO_DATE('2009-02-18 13:23:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 18, 2009 1:23:47 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56703 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 18, 2009 1:23:48 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56799,56704,0,53193,TO_DATE('2009-02-18 13:23:47','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.',22,'D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','N','Client',TO_DATE('2009-02-18 13:23:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 18, 2009 1:23:48 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56704 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 18, 2009 1:23:48 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56800,56705,0,53193,TO_DATE('2009-02-18 13:23:48','YYYY-MM-DD HH24:MI:SS'),100,'Column in the table',22,'D','Link to the database column of the table','Y','Y','Y','N','N','N','N','N','Column',TO_DATE('2009-02-18 13:23:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 18, 2009 1:23:48 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56705 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 18, 2009 1:23:49 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56807,56706,0,53193,TO_DATE('2009-02-18 13:23:48','YYYY-MM-DD HH24:MI:SS'),100,'Type of data',1,'D','Y','Y','Y','N','N','N','N','N','Data Type',TO_DATE('2009-02-18 13:23:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 18, 2009 1:23:49 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56706 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 18, 2009 1:23:50 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56808,56707,0,53193,TO_DATE('2009-02-18 13:23:49','YYYY-MM-DD HH24:MI:SS'),100,'Optional short description of the record',255,'D','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','N','Description',TO_DATE('2009-02-18 13:23:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 18, 2009 1:23:50 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56707 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 18, 2009 1:23:50 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56810,56708,0,53193,TO_DATE('2009-02-18 13:23:50','YYYY-MM-DD HH24:MI:SS'),100,'Alphanumeric identifier of the entity',60,'D','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','N','Name',TO_DATE('2009-02-18 13:23:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 18, 2009 1:23:50 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56708 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 18, 2009 1:23:51 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56801,56709,0,53193,TO_DATE('2009-02-18 13:23:50','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client',22,'D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','N','N','Organization',TO_DATE('2009-02-18 13:23:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 18, 2009 1:23:51 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56709 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 18, 2009 1:23:52 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56811,56710,0,53193,TO_DATE('2009-02-18 13:23:51','YYYY-MM-DD HH24:MI:SS'),100,'SQL',2000,'D','Y','Y','Y','N','N','N','N','N','Query',TO_DATE('2009-02-18 13:23:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 18, 2009 1:23:52 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56710 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 18, 2009 1:23:53 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56812,56711,0,53193,TO_DATE('2009-02-18 13:23:52','YYYY-MM-DD HH24:MI:SS'),100,'Which kind of search is used (Query or Table)',1,'D','Y','Y','Y','N','N','N','N','N','Search Type',TO_DATE('2009-02-18 13:23:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 18, 2009 1:23:53 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56711 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 18, 2009 1:23:53 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56803,56712,0,53193,TO_DATE('2009-02-18 13:23:53','YYYY-MM-DD HH24:MI:SS'),100,'Database Table information',22,'D','The Database Table provides the information of the table definition','Y','Y','Y','N','N','N','N','N','Table',TO_DATE('2009-02-18 13:23:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 18, 2009 1:23:53 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56712 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 18, 2009 1:23:54 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56813,56713,0,53193,TO_DATE('2009-02-18 13:23:53','YYYY-MM-DD HH24:MI:SS'),100,'The transaction code represents the search definition',8,'D','Y','Y','Y','N','N','N','N','N','Transaction Code',TO_DATE('2009-02-18 13:23:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 18, 2009 1:23:54 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56713 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 18, 2009 1:23:55 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56804,56714,0,53193,TO_DATE('2009-02-18 13:23:54','YYYY-MM-DD HH24:MI:SS'),100,'Data entry or display window',22,'D','The Window field identifies a unique Window in the system.','Y','Y','Y','N','N','N','N','N','Window',TO_DATE('2009-02-18 13:23:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 18, 2009 1:23:55 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56714 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 18, 2009 1:25:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=56704
;

-- Feb 18, 2009 1:25:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=56709
;

-- Feb 18, 2009 1:25:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=56713
;

-- Feb 18, 2009 1:25:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=56708
;

-- Feb 18, 2009 1:25:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=56707
;

-- Feb 18, 2009 1:25:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=56702
;

-- Feb 18, 2009 1:25:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=56711
;

-- Feb 18, 2009 1:25:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=56712
;

-- Feb 18, 2009 1:25:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=56705
;

-- Feb 18, 2009 1:25:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=56710
;

-- Feb 18, 2009 1:25:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=56706
;

-- Feb 18, 2009 1:25:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=56714
;

-- Feb 18, 2009 1:26:39 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_Field SET Description='Full SQL query with search for the ID (PK) of the selected table', Help='Only search for the ID of the selected table, otherwise the search will fail',Updated=TO_DATE('2009-02-18 13:26:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56710
;

-- Feb 18, 2009 1:26:39 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56710
;

-- Feb 18, 2009 1:27:59 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_Field SET Help='Select the datatype of the field(s) you are looking for',Updated=TO_DATE('2009-02-18 13:27:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56706
;

-- Feb 18, 2009 1:27:59 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56706
;

-- Feb 18, 2009 1:30:31 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_Field SET DisplayLogic='@SearchType@ = Q', Help='Only search for the ID of the selected table, otherwise the search will fail.
Use ? for the variable that represents the searchstring.',Updated=TO_DATE('2009-02-18 13:30:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56710
;

-- Feb 18, 2009 1:30:31 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56710
;

-- Feb 18, 2009 1:30:49 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_Field SET DisplayLogic=NULL,Updated=TO_DATE('2009-02-18 13:30:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56706
;

-- Feb 18, 2009 1:31:14 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_Field SET DisplayLogic='@SearchType@ = T',Updated=TO_DATE('2009-02-18 13:31:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56705
;

-- Feb 18, 2009 1:39:32 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53291,TO_DATE('2009-02-18 13:39:31','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','SearchType',TO_DATE('2009-02-18 13:39:31','YYYY-MM-DD HH24:MI:SS'),100,'L')
;

-- Feb 18, 2009 1:39:32 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53291 AND EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Reference_ID!=t.AD_Reference_ID)
;

-- Feb 18, 2009 1:39:58 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Reference_ID,AD_Ref_List_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53291,53454,TO_DATE('2009-02-18 13:39:57','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Query',TO_DATE('2009-02-18 13:39:57','YYYY-MM-DD HH24:MI:SS'),100,'Q')
;

-- Feb 18, 2009 1:39:58 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53454 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Feb 18, 2009 1:40:12 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Reference_ID,AD_Ref_List_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53291,53455,TO_DATE('2009-02-18 13:40:10','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Table',TO_DATE('2009-02-18 13:40:10','YYYY-MM-DD HH24:MI:SS'),100,'T')
;

-- Feb 18, 2009 1:40:12 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53455 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Feb 18, 2009 1:40:38 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_Field SET AD_Reference_ID=17, AD_Reference_Value_ID=53291,Updated=TO_DATE('2009-02-18 13:40:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56711
;

-- Feb 18, 2009 1:43:16 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53292,TO_DATE('2009-02-18 13:43:16','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','AD_SearchDefinition_DataType',TO_DATE('2009-02-18 13:43:16','YYYY-MM-DD HH24:MI:SS'),100,'L')
;

-- Feb 18, 2009 1:43:16 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53292 AND EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Reference_ID!=t.AD_Reference_ID)
;

-- Feb 18, 2009 1:44:19 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Reference_ID,AD_Ref_List_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53292,53456,TO_DATE('2009-02-18 13:44:18','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Integer',TO_DATE('2009-02-18 13:44:18','YYYY-MM-DD HH24:MI:SS'),100,'I')
;

-- Feb 18, 2009 1:44:19 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53456 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Feb 18, 2009 1:44:36 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Reference_ID,AD_Ref_List_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53292,53457,TO_DATE('2009-02-18 13:44:36','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','String',TO_DATE('2009-02-18 13:44:36','YYYY-MM-DD HH24:MI:SS'),100,'S')
;

-- Feb 18, 2009 1:44:36 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53457 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Feb 18, 2009 1:46:51 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_Field SET AD_Reference_ID=17, AD_Reference_Value_ID=53292,Updated=TO_DATE('2009-02-18 13:46:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56706
;

-- Feb 18, 2009 1:49:15 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Menu (Action,AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES ('W',0,53203,0,53069,TO_DATE('2009-02-18 13:49:13','YYYY-MM-DD HH24:MI:SS'),100,'Define transactions for the QuickSearch bar','D','Y','N','N','N','Search Definition',TO_DATE('2009-02-18 13:49:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 18, 2009 1:49:15 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53203 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Feb 18, 2009 1:49:15 PM CET
-- Product Category QA - kthiemann@adempiere.org
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', SysDate, 0, SysDate, 0,t.AD_Tree_ID, 53203, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53203)
;

-- Feb 18, 2009 1:49:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=218
;

-- Feb 18, 2009 1:49:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=153
;

-- Feb 18, 2009 1:49:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=263
;

-- Feb 18, 2009 1:49:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=166
;

-- Feb 18, 2009 1:49:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=203
;

-- Feb 18, 2009 1:49:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=236
;

-- Feb 18, 2009 1:49:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=183
;

-- Feb 18, 2009 1:49:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=160
;

-- Feb 18, 2009 1:49:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=278
;

-- Feb 18, 2009 1:49:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=345
;

-- Feb 18, 2009 1:49:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53014
;

-- Feb 18, 2009 1:49:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53108
;

-- Feb 18, 2009 1:49:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53203
;

-- Feb 18, 2009 1:49:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=586
;

-- Feb 18, 2009 1:49:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=138
;

-- Feb 18, 2009 1:49:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=139
;

-- Feb 18, 2009 1:49:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=249
;

-- Feb 18, 2009 1:49:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=141
;

-- Feb 18, 2009 1:49:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=589
;

-- Feb 18, 2009 1:49:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=216
;

-- Feb 18, 2009 1:49:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=140
;

-- Feb 18, 2009 1:49:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=142
;

-- Feb 18, 2009 1:49:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53012
;

-- Feb 18, 2009 1:49:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=143
;

-- Feb 18, 2009 1:49:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=201
;

-- Feb 18, 2009 1:49:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=13, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=176
;

-- Feb 18, 2009 1:49:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=14, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53086
;

-- Feb 18, 2009 1:49:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=15, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=239
;

-- Feb 18, 2009 1:49:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=16, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=517
;

-- Feb 18, 2009 1:49:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=17, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=499
;

-- Feb 18, 2009 1:49:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=18, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53089
;

-- Feb 18, 2009 1:49:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=19, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53090
;

-- Feb 18, 2009 1:49:18 PM CET
-- Product Category QA - kthiemann@adempiere.org
UPDATE AD_TreeNodeMM SET Parent_ID=153, SeqNo=20, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=50001
;

-- Feb 18, 2009 2:15:40 PM CET
-- Advanced Search
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56817,2136,0,18,284,53169,'PO_Window_ID',TO_DATE('2009-02-18 14:15:39','YYYY-MM-DD HH24:MI:SS'),100,'Purchase Order Window','D',22,'Window for Purchase Order (AP) Zooms','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','PO Window',0,TO_DATE('2009-02-18 14:15:39','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Feb 18, 2009 2:15:40 PM CET
-- Advanced Search
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56817 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 18, 2009 2:15:45 PM CET
-- Advanced Search
ALTER TABLE AD_SearchDefinition ADD PO_Window_ID NUMBER(10)
;

-- Feb 18, 2009 2:16:26 PM CET
-- Advanced Search
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56817,56717,0,53193,TO_DATE('2009-02-18 14:16:25','YYYY-MM-DD HH24:MI:SS'),100,'Purchase Order Window',22,'D','Window for Purchase Order (AP) Zooms','Y','Y','Y','N','N','N','N','N','PO Window',TO_DATE('2009-02-18 14:16:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 18, 2009 2:16:26 PM CET
-- Advanced Search
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56717 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 18, 2009 3:24:22 PM CET
-- Advanced Search
UPDATE AD_Field SET DisplayLogic='@SearchType@ = T',Updated=TO_DATE('2009-02-18 15:24:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56717
;

-- Feb 18, 2009 5:06:52 PM CET
-- Advanced Search
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56818,1103,0,20,53169,'IsDefault',TO_DATE('2009-02-18 17:06:51','YYYY-MM-DD HH24:MI:SS'),100,'N','Default value','D',1,'The Default Checkbox indicates if this record will be used as a default value.','Y','Y','N','N','N','N','N','Y','N','N','N','N','Y','Default',0,TO_DATE('2009-02-18 17:06:51','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Feb 18, 2009 5:06:52 PM CET
-- Advanced Search
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56818 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 18, 2009 5:07:00 PM CET
-- Advanced Search
ALTER TABLE AD_SearchDefinition ADD IsDefault CHAR(1) DEFAULT 'N' CHECK (IsDefault IN ('Y','N')) NOT NULL
;

-- Feb 18, 2009 5:08:40 PM CET
-- Advanced Search
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56818,56718,0,53193,TO_DATE('2009-02-18 17:08:38','YYYY-MM-DD HH24:MI:SS'),100,'Default value',1,'D','The Default Checkbox indicates if this record will be used as a default value.','Y','Y','Y','N','N','N','N','N','Default',TO_DATE('2009-02-18 17:08:38','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 18, 2009 5:08:40 PM CET
-- Advanced Search
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56718 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 18, 2009 5:09:10 PM CET
-- Advanced Search
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=56718
;

-- Feb 18, 2009 5:09:10 PM CET
-- Advanced Search
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=56711
;

-- Feb 18, 2009 5:09:10 PM CET
-- Advanced Search
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=56712
;

-- Feb 18, 2009 5:09:10 PM CET
-- Advanced Search
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=56705
;

-- Feb 18, 2009 5:09:10 PM CET
-- Advanced Search
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=56710
;

-- Feb 18, 2009 5:09:10 PM CET
-- Advanced Search
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=56706
;

-- Feb 18, 2009 5:09:10 PM CET
-- Advanced Search
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=56714
;

-- Feb 18, 2009 5:09:10 PM CET
-- Advanced Search
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=56717
;

-- Feb 18, 2009 5:09:19 PM CET
-- Advanced Search
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2009-02-18 17:09:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56718
;

