SET DEFINE OFF;

-- Apr 18, 2009 10:38:14 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_Window_ID,AccessLevel,CopyColumnsFromTable,Created,CreatedBy,Description,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,LoadSeq,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53189,113,'3','N',TO_DATE('2009-04-18 10:38:12','YYYY-MM-DD HH24:MI:SS'),0,'Workflow or combination of tasks','EE01','N','Y','N','Y','N','N','N',0,'Order Workflow Trl','L','PP_Order_Workflow_Trl',TO_DATE('2009-04-18 10:38:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 18, 2009 10:38:14 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53189 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Apr 18, 2009 10:38:15 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53284,TO_DATE('2009-04-18 10:38:14','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table PP_Order_Workflow_Trl',1,'Y','N','Y','Y','PP_Order_Workflow_Trl','N',1000000,TO_DATE('2009-04-18 10:38:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 18, 2009 10:38:50 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57188,102,0,19,53189,'AD_Client_ID',TO_DATE('2009-04-18 10:38:49','YYYY-MM-DD HH24:MI:SS'),0,'@AD_Client_ID@','Client/Tenant for this installation.','EE01',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','N','N','N','Client',0,TO_DATE('2009-04-18 10:38:49','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 18, 2009 10:38:50 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57188 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 10:38:50 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57189,109,0,18,106,53189,'AD_Language',TO_DATE('2009-04-18 10:38:50','YYYY-MM-DD HH24:MI:SS'),0,'Language for this entity','EE01',6,'The Language identifies the language to use for display and formatting','Y','N','N','N','N','Y','Y','N','N','N','N','Language',0,TO_DATE('2009-04-18 10:38:50','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 18, 2009 10:38:50 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57189 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 10:38:50 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57190,113,0,19,53189,104,'AD_Org_ID',TO_DATE('2009-04-18 10:38:50','YYYY-MM-DD HH24:MI:SS'),0,'@AD_Org_ID@','Organizational entity within client','EE01',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','N','N','N','Organization',0,TO_DATE('2009-04-18 10:38:50','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 18, 2009 10:38:50 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57190 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 10:38:51 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57191,143,0,19,53189,'AD_Window_ID',TO_DATE('2009-04-18 10:38:50','YYYY-MM-DD HH24:MI:SS'),0,'Data entry or display window','EE01',22,'The Window field identifies a unique Window in the system.','Y','N','N','N','N','Y','Y','N','N','N','N','Window',0,TO_DATE('2009-04-18 10:38:50','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 18, 2009 10:38:51 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57191 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 10:38:51 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57192,245,0,16,53189,'Created',TO_DATE('2009-04-18 10:38:51','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE01',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','N','N','N','Created',0,TO_DATE('2009-04-18 10:38:51','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 18, 2009 10:38:51 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57192 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 10:38:52 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57193,246,0,18,110,53189,'CreatedBy',TO_DATE('2009-04-18 10:38:51','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE01',22,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','N','N','N','Created By',0,TO_DATE('2009-04-18 10:38:51','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 18, 2009 10:38:52 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57193 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 10:38:52 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57194,275,0,10,53189,'Description',TO_DATE('2009-04-18 10:38:52','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE01',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','N','N','Y','Description',0,TO_DATE('2009-04-18 10:38:52','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 18, 2009 10:38:52 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57194 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 10:38:53 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57195,326,0,14,53189,'Help',TO_DATE('2009-04-18 10:38:52','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint','EE01',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','N','N','Y','Comment/Help',0,TO_DATE('2009-04-18 10:38:52','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 18, 2009 10:38:53 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57195 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 10:38:53 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57196,348,0,20,53189,'IsActive',TO_DATE('2009-04-18 10:38:53','YYYY-MM-DD HH24:MI:SS'),0,'Y','The record is active in the system','EE01',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','N','N','Y','Active',0,TO_DATE('2009-04-18 10:38:53','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 18, 2009 10:38:53 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57196 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 10:38:53 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57197,420,0,20,53189,'IsTranslated',TO_DATE('2009-04-18 10:38:53','YYYY-MM-DD HH24:MI:SS'),0,'This column is translated','EE01',1,'The Translated checkbox indicates if this column is translated.','Y','N','N','N','N','Y','N','N','N','N','Y','Translated',0,TO_DATE('2009-04-18 10:38:53','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 18, 2009 10:38:53 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57197 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 10:38:55 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57198,469,0,10,53189,'Name',TO_DATE('2009-04-18 10:38:53','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE01',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','N','N','Y','Name',0,TO_DATE('2009-04-18 10:38:53','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 18, 2009 10:38:55 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57198 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 10:38:55 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57199,607,0,16,53189,'Updated',TO_DATE('2009-04-18 10:38:55','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE01',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','N','N','N','Updated',0,TO_DATE('2009-04-18 10:38:55','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 18, 2009 10:38:55 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57199 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 10:38:56 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57200,608,0,18,110,53189,'UpdatedBy',TO_DATE('2009-04-18 10:38:55','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE01',22,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','N','N','N','Updated By',0,TO_DATE('2009-04-18 10:38:55','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 18, 2009 10:38:56 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57200 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 10:39:31 AM ECT
-- Views for Manufacturing Management
UPDATE AD_Table SET AD_Window_ID=53009,Updated=TO_DATE('2009-04-18 10:39:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=53189
;

-- Apr 18, 2009 10:40:37 AM ECT
-- Views for Manufacturing Management
UPDATE AD_Column SET AD_Element_ID=53286, ColumnName='PP_Order_Workflow_ID', Description=NULL, Help=NULL, IsUpdateable='N', Name='Manufacturing Order Workflow',Updated=TO_DATE('2009-04-18 10:40:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=57191
;

-- Apr 18, 2009 10:40:37 AM ECT
-- Views for Manufacturing Management
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=57191
;

-- Apr 18, 2009 10:40:37 AM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET Name='Manufacturing Order Workflow', Description=NULL, Help=NULL WHERE AD_Column_ID=57191 AND IsCentrallyMaintained='Y'
;

-- Apr 18, 2009 10:41:42 AM ECT
-- Views for Manufacturing Management
UPDATE AD_Column SET IsTranslated='Y',Updated=TO_DATE('2009-04-18 10:41:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53697
;

-- Apr 18, 2009 10:42:12 AM ECT
-- Views for Manufacturing Management
UPDATE AD_Column SET IsTranslated='Y',Updated=TO_DATE('2009-04-18 10:42:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53686
;

-- Apr 18, 2009 10:42:18 AM ECT
-- Views for Manufacturing Management
UPDATE AD_Column SET IsTranslated='Y',Updated=TO_DATE('2009-04-18 10:42:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53702
;

-- Apr 18, 2009 10:46:17 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,EntityType,HasTree,ImportFields,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53213,53189,53009,TO_DATE('2009-04-18 10:46:17','YYYY-MM-DD HH24:MI:SS'),0,'EE01','N','N','Y','N','N','N','N','Y','N','Y','Workflow Translation','N',45,0,TO_DATE('2009-04-18 10:46:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 18, 2009 10:46:17 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53213 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Apr 18, 2009 10:46:22 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57196,56954,0,53213,TO_DATE('2009-04-18 10:46:21','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE01','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','N','Active',TO_DATE('2009-04-18 10:46:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 18, 2009 10:46:22 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56954 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 18, 2009 10:46:22 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57188,56955,0,53213,TO_DATE('2009-04-18 10:46:22','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',22,'EE01','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','N','Client',TO_DATE('2009-04-18 10:46:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 18, 2009 10:46:22 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56955 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 18, 2009 10:46:23 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57195,56956,0,53213,TO_DATE('2009-04-18 10:46:22','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint',2000,'EE01','The Help field contains a hint, comment or help about the use of this item.','Y','Y','Y','N','N','N','N','N','Comment/Help',TO_DATE('2009-04-18 10:46:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 18, 2009 10:46:23 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56956 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 18, 2009 10:46:23 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57194,56957,0,53213,TO_DATE('2009-04-18 10:46:23','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',255,'EE01','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','N','Description',TO_DATE('2009-04-18 10:46:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 18, 2009 10:46:23 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56957 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 18, 2009 10:46:24 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57189,56958,0,53213,TO_DATE('2009-04-18 10:46:23','YYYY-MM-DD HH24:MI:SS'),0,'Language for this entity',6,'EE01','The Language identifies the language to use for display and formatting','Y','Y','Y','N','N','N','N','N','Language',TO_DATE('2009-04-18 10:46:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 18, 2009 10:46:24 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56958 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 18, 2009 10:46:25 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57191,56959,0,53213,TO_DATE('2009-04-18 10:46:24','YYYY-MM-DD HH24:MI:SS'),0,22,'EE01','Y','Y','Y','N','N','N','N','N','Manufacturing Order Workflow',TO_DATE('2009-04-18 10:46:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 18, 2009 10:46:25 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56959 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 18, 2009 10:46:26 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57198,56960,0,53213,TO_DATE('2009-04-18 10:46:25','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',60,'EE01','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','N','Name',TO_DATE('2009-04-18 10:46:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 18, 2009 10:46:26 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56960 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 18, 2009 10:46:26 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57190,56961,0,53213,TO_DATE('2009-04-18 10:46:26','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',22,'EE01','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','N','N','Organization',TO_DATE('2009-04-18 10:46:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 18, 2009 10:46:26 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56961 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 18, 2009 10:46:27 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57197,56962,0,53213,TO_DATE('2009-04-18 10:46:26','YYYY-MM-DD HH24:MI:SS'),0,'This column is translated',1,'EE01','The Translated checkbox indicates if this column is translated.','Y','Y','Y','N','N','N','N','N','Translated',TO_DATE('2009-04-18 10:46:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 18, 2009 10:46:27 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56962 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 18, 2009 10:47:09 AM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=56955
;

-- Apr 18, 2009 10:47:09 AM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=56961
;

-- Apr 18, 2009 10:47:09 AM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=56959
;

-- Apr 18, 2009 10:47:10 AM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=56958
;

-- Apr 18, 2009 10:47:10 AM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=56954
;

-- Apr 18, 2009 10:47:10 AM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=56960
;

-- Apr 18, 2009 10:47:10 AM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=56957
;

-- Apr 18, 2009 10:47:10 AM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=56956
;

-- Apr 18, 2009 10:47:10 AM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=56962
;

-- Apr 18, 2009 10:47:19 AM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2009-04-18 10:47:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56955
;

-- Apr 18, 2009 10:47:24 AM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2009-04-18 10:47:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56961
;

-- Apr 18, 2009 10:47:27 AM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2009-04-18 10:47:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56959
;

-- Apr 18, 2009 10:47:34 AM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2009-04-18 10:47:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56958
;

-- Apr 18, 2009 10:49:36 AM ECT
-- Views for Manufacturing Management
UPDATE AD_Tab SET TabLevel=2,Updated=TO_DATE('2009-04-18 10:49:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53213
;

-- Apr 18, 2009 10:51:06 AM ECT
-- Views for Manufacturing Management
CREATE TABLE PP_Order_Workflow_Trl (
AD_Client_ID NUMBER(10) NOT NULL, 
AD_Language VARCHAR2(6) NOT NULL, 
AD_Org_ID NUMBER(10) NOT NULL, 
Created DATE DEFAULT SYSDATE NOT NULL, 
CreatedBy NUMBER(10) NOT NULL, 
Description NVARCHAR2(255) DEFAULT NULL , 
Help NVARCHAR2(2000) DEFAULT NULL , 
IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, 
IsTranslated CHAR(1) CHECK (IsTranslated IN ('Y','N')) NOT NULL, 
Name NVARCHAR2(60) NOT NULL, 
PP_Order_Workflow_ID NUMBER(10) NOT NULL, 
Updated DATE DEFAULT SYSDATE NOT NULL, UpdatedBy NUMBER(10) NOT NULL, 
CONSTRAINT PP_Order_Workflow_Trl_Key PRIMARY KEY (AD_Language, PP_Order_Workflow_ID))
;

-- Apr 18, 2009 10:55:36 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_Window_ID,AccessLevel,CopyColumnsFromTable,Created,CreatedBy,Description,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,LoadSeq,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53190,53009,'3','N',TO_DATE('2009-04-18 10:54:46','YYYY-MM-DD HH24:MI:SS'),0,'Workflow Node (activity), step or process','EE01','N','Y','N','Y','N','N','N',0,'Order Node Trl','L','PP_Order_Node_Trl',TO_DATE('2009-04-18 10:54:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 18, 2009 10:55:36 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53190 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Apr 18, 2009 10:55:46 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53285,TO_DATE('2009-04-18 10:55:36','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table PP_Order_Node_Trl',1,'Y','N','Y','Y','PP_Order_Node_Trl','N',1000000,TO_DATE('2009-04-18 10:55:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 18, 2009 10:56:08 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57201,102,0,19,53190,'AD_Client_ID',TO_DATE('2009-04-18 10:56:04','YYYY-MM-DD HH24:MI:SS'),0,'@AD_Client_ID@','Client/Tenant for this installation.','EE01',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','Y','N','N','Client',0,TO_DATE('2009-04-18 10:56:04','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 18, 2009 10:56:08 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57201 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 10:56:08 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57202,109,0,18,106,53190,'AD_Language',TO_DATE('2009-04-18 10:56:08','YYYY-MM-DD HH24:MI:SS'),0,'Language for this entity','EE01',6,'The Language identifies the language to use for display and formatting','Y','N','N','N','N','Y','Y','N','Y','N','N','Language',0,TO_DATE('2009-04-18 10:56:08','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 18, 2009 10:56:08 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57202 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 10:56:09 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57203,113,0,19,53190,104,'AD_Org_ID',TO_DATE('2009-04-18 10:56:08','YYYY-MM-DD HH24:MI:SS'),0,'@AD_Org_ID@','Organizational entity within client','EE01',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','Y','N','N','Organization',0,TO_DATE('2009-04-18 10:56:08','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 18, 2009 10:56:09 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57203 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 10:56:09 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57204,142,0,19,53190,'AD_WF_Node_ID',TO_DATE('2009-04-18 10:56:09','YYYY-MM-DD HH24:MI:SS'),0,'Workflow Node (activity), step or process','EE01',22,'The Workflow Node indicates a unique step or process in a Workflow.','Y','N','N','N','N','Y','Y','N','Y','N','N','Node',0,TO_DATE('2009-04-18 10:56:09','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 18, 2009 10:56:09 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57204 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 10:56:09 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57205,245,0,16,53190,'Created',TO_DATE('2009-04-18 10:56:09','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE01',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','Y','N','N','Created',0,TO_DATE('2009-04-18 10:56:09','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 18, 2009 10:56:09 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57205 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 10:56:11 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57206,246,0,18,110,53190,'CreatedBy',TO_DATE('2009-04-18 10:56:09','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE01',22,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Created By',0,TO_DATE('2009-04-18 10:56:09','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 18, 2009 10:56:11 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57206 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 10:56:12 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57207,275,0,10,53190,'Description',TO_DATE('2009-04-18 10:56:11','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE01',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','Y','N','Y','Description',0,TO_DATE('2009-04-18 10:56:11','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 18, 2009 10:56:12 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57207 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 10:56:22 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57208,326,0,14,53190,'Help',TO_DATE('2009-04-18 10:56:12','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint','EE01',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','Y','N','Y','Comment/Help',0,TO_DATE('2009-04-18 10:56:12','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 18, 2009 10:56:22 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57208 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 10:56:23 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57209,348,0,20,53190,'IsActive',TO_DATE('2009-04-18 10:56:22','YYYY-MM-DD HH24:MI:SS'),0,'Y','The record is active in the system','EE01',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','Y','N','Y','Active',0,TO_DATE('2009-04-18 10:56:22','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 18, 2009 10:56:23 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57209 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 10:56:25 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57210,420,0,20,53190,'IsTranslated',TO_DATE('2009-04-18 10:56:23','YYYY-MM-DD HH24:MI:SS'),0,'This column is translated','EE01',1,'The Translated checkbox indicates if this column is translated.','Y','N','N','N','N','Y','N','N','Y','N','Y','Translated',0,TO_DATE('2009-04-18 10:56:23','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 18, 2009 10:56:25 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57210 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 10:56:25 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57211,469,0,10,53190,'Name',TO_DATE('2009-04-18 10:56:25','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE01',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','Y','N','Y','Name',1,TO_DATE('2009-04-18 10:56:25','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 18, 2009 10:56:25 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57211 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 10:56:25 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57212,607,0,16,53190,'Updated',TO_DATE('2009-04-18 10:56:25','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE01',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated',0,TO_DATE('2009-04-18 10:56:25','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 18, 2009 10:56:25 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57212 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 10:57:03 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57213,608,0,18,110,53190,'UpdatedBy',TO_DATE('2009-04-18 10:56:25','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE01',22,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated By',0,TO_DATE('2009-04-18 10:56:25','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 18, 2009 10:57:03 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57213 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 10:57:21 AM ECT
-- Views for Manufacturing Management
UPDATE AD_Column SET ColumnName='PP_Order_Node_ID', IsUpdateable='N',Updated=TO_DATE('2009-04-18 10:57:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=57204
;

-- Apr 18, 2009 10:58:58 AM ECT
-- Views for Manufacturing Management
UPDATE AD_Column SET IsTranslated='Y',Updated=TO_DATE('2009-04-18 10:58:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53468
;

-- Apr 18, 2009 10:59:12 AM ECT
-- Views for Manufacturing Management
UPDATE AD_Column SET IsTranslated='Y',Updated=TO_DATE('2009-04-18 10:59:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53476
;

-- Apr 18, 2009 10:59:17 AM ECT
-- Views for Manufacturing Management
UPDATE AD_Column SET IsTranslated='Y',Updated=TO_DATE('2009-04-18 10:59:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53445
;

-- Apr 18, 2009 11:01:19 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,HasTree,Help,ImportFields,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53214,53190,53009,TO_DATE('2009-04-18 11:01:15','YYYY-MM-DD HH24:MI:SS'),0,NULL,'EE01','N',NULL,'N','Y','N','N','N','N','Y','N','Y','Order Activity Translation','N',57,3,TO_DATE('2009-04-18 11:01:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 18, 2009 11:01:19 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53214 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Apr 18, 2009 11:01:25 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57209,56963,0,53214,TO_DATE('2009-04-18 11:01:21','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE01','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','N','Active',TO_DATE('2009-04-18 11:01:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 18, 2009 11:01:25 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56963 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 18, 2009 11:01:26 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57201,56964,0,53214,TO_DATE('2009-04-18 11:01:25','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',22,'EE01','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','N','Client',TO_DATE('2009-04-18 11:01:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 18, 2009 11:01:26 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56964 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 18, 2009 11:01:26 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57208,56965,0,53214,TO_DATE('2009-04-18 11:01:26','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint',2000,'EE01','The Help field contains a hint, comment or help about the use of this item.','Y','Y','Y','N','N','N','N','N','Comment/Help',TO_DATE('2009-04-18 11:01:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 18, 2009 11:01:26 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56965 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 18, 2009 11:01:27 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57207,56966,0,53214,TO_DATE('2009-04-18 11:01:26','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',255,'EE01','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','N','Description',TO_DATE('2009-04-18 11:01:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 18, 2009 11:01:27 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56966 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 18, 2009 11:01:28 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57202,56967,0,53214,TO_DATE('2009-04-18 11:01:27','YYYY-MM-DD HH24:MI:SS'),0,'Language for this entity',6,'EE01','The Language identifies the language to use for display and formatting','Y','Y','Y','N','N','N','N','N','Language',TO_DATE('2009-04-18 11:01:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 18, 2009 11:01:28 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56967 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 18, 2009 11:01:32 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57211,56968,0,53214,TO_DATE('2009-04-18 11:01:28','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',60,'EE01','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','N','Name',TO_DATE('2009-04-18 11:01:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 18, 2009 11:01:32 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56968 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 18, 2009 11:01:33 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57204,56969,0,53214,TO_DATE('2009-04-18 11:01:32','YYYY-MM-DD HH24:MI:SS'),0,'Workflow Node (activity), step or process',22,'EE01','The Workflow Node indicates a unique step or process in a Workflow.','Y','Y','Y','N','N','N','N','N','Node',TO_DATE('2009-04-18 11:01:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 18, 2009 11:01:33 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56969 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 18, 2009 11:01:34 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57203,56970,0,53214,TO_DATE('2009-04-18 11:01:33','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',22,'EE01','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','N','N','Organization',TO_DATE('2009-04-18 11:01:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 18, 2009 11:01:34 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56970 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 18, 2009 11:01:36 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57210,56971,0,53214,TO_DATE('2009-04-18 11:01:34','YYYY-MM-DD HH24:MI:SS'),0,'This column is translated',1,'EE01','The Translated checkbox indicates if this column is translated.','Y','Y','Y','N','N','N','N','N','Translated',TO_DATE('2009-04-18 11:01:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 18, 2009 11:01:36 AM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56971 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 18, 2009 11:08:15 AM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=56964
;

-- Apr 18, 2009 11:08:15 AM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=56970
;

-- Apr 18, 2009 11:08:15 AM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=56967
;

-- Apr 18, 2009 11:08:15 AM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=56969
;

-- Apr 18, 2009 11:08:15 AM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=56968
;

-- Apr 18, 2009 11:08:15 AM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=56966
;

-- Apr 18, 2009 11:08:15 AM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=56963
;

-- Apr 18, 2009 11:08:15 AM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=56965
;

-- Apr 18, 2009 11:08:15 AM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=56971
;

-- Apr 18, 2009 11:08:22 AM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2009-04-18 11:08:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56964
;

-- Apr 18, 2009 11:08:27 AM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2009-04-18 11:08:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56967
;

-- Apr 18, 2009 11:08:31 AM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2009-04-18 11:08:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56970
;

-- Apr 18, 2009 11:08:38 AM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2009-04-18 11:08:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56969
;

-- Apr 18, 2009 12:46:12 PM ECT
-- Views for Manufacturing Management
ALTER TABLE PP_Order_Workflow_Trl MODIFY AD_Client_ID NUMBER(10)
;

-- Apr 18, 2009 12:47:19 PM ECT
-- Views for Manufacturing Management
CREATE TABLE PP_Order_Node_Trl (
AD_Client_ID NUMBER(10) NOT NULL, 
AD_Language VARCHAR2(6) NOT NULL, 
AD_Org_ID NUMBER(10) NOT NULL, 
Created DATE DEFAULT SYSDATE NOT NULL, 
CreatedBy NUMBER(10) NOT NULL, 
Description NVARCHAR2(255) DEFAULT NULL , 
Help NVARCHAR2(2000) DEFAULT NULL , 
IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, 
IsTranslated CHAR(1) CHECK (IsTranslated IN ('Y','N')) NOT NULL, 
Name NVARCHAR2(60) NOT NULL, 
PP_Order_Node_ID NUMBER(10) NOT NULL, 
Updated DATE DEFAULT SYSDATE NOT NULL, UpdatedBy NUMBER(10) NOT NULL, 
CONSTRAINT PP_Order_Node_Trl_Key PRIMARY KEY (AD_Language, PP_Order_Node_ID)
)
;

-- Apr 18, 2009 12:52:05 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_Window_ID,AccessLevel,CopyColumnsFromTable,Created,CreatedBy,Description,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,LoadSeq,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53191,53006,'3','N',TO_DATE('2009-04-18 12:52:04','YYYY-MM-DD HH24:MI:SS'),0,'BOM & Formula','EE01','N','Y','N','Y','N','N','N',0,'BOM & Formula Translation','L','PP_Product_BOM_Trl',TO_DATE('2009-04-18 12:52:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 18, 2009 12:52:05 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53191 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Apr 18, 2009 12:52:05 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53286,TO_DATE('2009-04-18 12:52:05','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table PP_Product_BOM_Trl',1,'Y','N','Y','Y','PP_Product_BOM_Trl','N',1000000,TO_DATE('2009-04-18 12:52:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 18, 2009 12:52:36 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57214,102,0,19,53191,'AD_Client_ID',TO_DATE('2009-04-18 12:52:35','YYYY-MM-DD HH24:MI:SS'),0,'@AD_Client_ID@','Client/Tenant for this installation.','EE01',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','N','N','N','Client',0,TO_DATE('2009-04-18 12:52:35','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 18, 2009 12:52:36 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57214 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 12:52:38 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57215,109,0,18,106,53191,'AD_Language',TO_DATE('2009-04-18 12:52:36','YYYY-MM-DD HH24:MI:SS'),0,'Language for this entity','EE01',6,'The Language identifies the language to use for display and formatting','Y','N','N','N','N','Y','Y','N','N','N','N','Language',0,TO_DATE('2009-04-18 12:52:36','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 18, 2009 12:52:38 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57215 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 12:52:39 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57216,113,0,19,53191,104,'AD_Org_ID',TO_DATE('2009-04-18 12:52:38','YYYY-MM-DD HH24:MI:SS'),0,'@AD_Org_ID@','Organizational entity within client','EE01',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','N','N','N','Organization',0,TO_DATE('2009-04-18 12:52:38','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 18, 2009 12:52:39 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57216 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 12:52:39 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57217,245,0,16,53191,'Created',TO_DATE('2009-04-18 12:52:39','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE01',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','N','N','N','Created',0,TO_DATE('2009-04-18 12:52:39','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 18, 2009 12:52:39 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57217 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 12:52:40 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57218,246,0,18,110,53191,'CreatedBy',TO_DATE('2009-04-18 12:52:39','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE01',22,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','N','N','N','Created By',0,TO_DATE('2009-04-18 12:52:39','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 18, 2009 12:52:40 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57218 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 12:52:40 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57219,275,0,10,53191,'Description',TO_DATE('2009-04-18 12:52:40','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE01',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','N','N','Y','Description',0,TO_DATE('2009-04-18 12:52:40','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 18, 2009 12:52:40 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57219 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 12:52:41 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57220,326,0,14,53191,'Help',TO_DATE('2009-04-18 12:52:40','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint','EE01',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','N','N','Y','Comment/Help',0,TO_DATE('2009-04-18 12:52:40','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 18, 2009 12:52:41 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57220 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 12:52:42 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57221,348,0,20,53191,'IsActive',TO_DATE('2009-04-18 12:52:41','YYYY-MM-DD HH24:MI:SS'),0,'Y','The record is active in the system','EE01',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','N','N','Y','Active',0,TO_DATE('2009-04-18 12:52:41','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 18, 2009 12:52:42 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57221 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 12:52:42 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57222,420,0,20,53191,'IsTranslated',TO_DATE('2009-04-18 12:52:42','YYYY-MM-DD HH24:MI:SS'),0,'This column is translated','EE01',1,'The Translated checkbox indicates if this column is translated.','Y','N','N','N','N','Y','N','N','N','N','Y','Translated',0,TO_DATE('2009-04-18 12:52:42','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 18, 2009 12:52:42 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57222 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 12:52:42 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57223,469,0,10,53191,'Name',TO_DATE('2009-04-18 12:52:42','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE01',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','N','N','Y','Name',0,TO_DATE('2009-04-18 12:52:42','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 18, 2009 12:52:42 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57223 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 12:52:42 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57224,53286,0,19,53191,'PP_Order_Workflow_ID',TO_DATE('2009-04-18 12:52:42','YYYY-MM-DD HH24:MI:SS'),0,'EE01',22,'Y','N','N','N','N','Y','Y','N','N','N','N','Manufacturing Order Workflow',0,TO_DATE('2009-04-18 12:52:42','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 18, 2009 12:52:42 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57224 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 12:52:43 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57225,607,0,16,53191,'Updated',TO_DATE('2009-04-18 12:52:42','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE01',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','N','N','N','Updated',0,TO_DATE('2009-04-18 12:52:42','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 18, 2009 12:52:43 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57225 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 12:52:43 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57226,608,0,18,110,53191,'UpdatedBy',TO_DATE('2009-04-18 12:52:43','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE01',22,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','N','N','N','Updated By',0,TO_DATE('2009-04-18 12:52:43','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 18, 2009 12:52:43 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57226 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 18, 2009 12:58:14 PM ECT
-- Views for Manufacturing Management
UPDATE AD_Column SET AD_Element_ID=53245, ColumnName='PP_Product_BOM_ID', Description='BOM & Formula', Help=NULL, IsUpdateable='N', Name='BOM & Formula',Updated=TO_DATE('2009-04-18 12:58:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=57224
;

-- Apr 18, 2009 12:58:14 PM ECT
-- Views for Manufacturing Management
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=57224
;

-- Apr 18, 2009 12:58:14 PM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET Name='BOM & Formula', Description='BOM & Formula', Help=NULL WHERE AD_Column_ID=57224 AND IsCentrallyMaintained='Y'
;

-- Apr 18, 2009 1:02:12 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,HasTree,Help,ImportFields,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53215,53191,53006,TO_DATE('2009-04-18 12:59:48','YYYY-MM-DD HH24:MI:SS'),0,NULL,'EE01','N',NULL,'N','Y','N','N','N','N','Y','N','Y','Parent Product Translation','N',15,0,TO_DATE('2009-04-18 12:59:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 18, 2009 1:02:12 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53215 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Apr 18, 2009 1:02:12 PM ECT
-- Views for Manufacturing Management
UPDATE AD_Tab SET IsSortTab='Y',Updated=TO_DATE('2009-04-18 13:02:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53215
;

-- Apr 18, 2009 1:02:21 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57221,56972,0,53215,TO_DATE('2009-04-18 13:02:20','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE01','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','N','Active',TO_DATE('2009-04-18 13:02:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 18, 2009 1:02:21 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56972 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 18, 2009 1:02:22 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57224,56973,0,53215,TO_DATE('2009-04-18 13:02:21','YYYY-MM-DD HH24:MI:SS'),0,'BOM & Formula',22,'EE01','Y','Y','Y','N','N','N','N','N','BOM & Formula',TO_DATE('2009-04-18 13:02:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 18, 2009 1:02:22 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56973 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 18, 2009 1:02:23 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57214,56974,0,53215,TO_DATE('2009-04-18 13:02:22','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',22,'EE01','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','N','Client',TO_DATE('2009-04-18 13:02:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 18, 2009 1:02:23 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56974 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 18, 2009 1:02:24 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57220,56975,0,53215,TO_DATE('2009-04-18 13:02:23','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint',2000,'EE01','The Help field contains a hint, comment or help about the use of this item.','Y','Y','Y','N','N','N','N','N','Comment/Help',TO_DATE('2009-04-18 13:02:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 18, 2009 1:02:24 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56975 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 18, 2009 1:02:25 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57219,56976,0,53215,TO_DATE('2009-04-18 13:02:24','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',255,'EE01','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','N','Description',TO_DATE('2009-04-18 13:02:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 18, 2009 1:02:25 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56976 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 18, 2009 1:02:26 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57215,56977,0,53215,TO_DATE('2009-04-18 13:02:25','YYYY-MM-DD HH24:MI:SS'),0,'Language for this entity',6,'EE01','The Language identifies the language to use for display and formatting','Y','Y','Y','N','N','N','N','N','Language',TO_DATE('2009-04-18 13:02:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 18, 2009 1:02:26 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56977 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 18, 2009 1:02:27 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57223,56978,0,53215,TO_DATE('2009-04-18 13:02:26','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',60,'EE01','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','N','Name',TO_DATE('2009-04-18 13:02:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 18, 2009 1:02:27 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56978 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 18, 2009 1:02:28 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57216,56979,0,53215,TO_DATE('2009-04-18 13:02:27','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',22,'EE01','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','N','N','Organization',TO_DATE('2009-04-18 13:02:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 18, 2009 1:02:28 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56979 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 18, 2009 1:02:29 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57222,56980,0,53215,TO_DATE('2009-04-18 13:02:28','YYYY-MM-DD HH24:MI:SS'),0,'This column is translated',1,'EE01','The Translated checkbox indicates if this column is translated.','Y','Y','Y','N','N','N','N','N','Translated',TO_DATE('2009-04-18 13:02:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 18, 2009 1:02:29 PM ECT
-- Views for Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56980 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 18, 2009 1:04:29 PM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=56974
;

-- Apr 18, 2009 1:04:29 PM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=56979
;

-- Apr 18, 2009 1:04:29 PM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=56977
;

-- Apr 18, 2009 1:04:29 PM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=56973
;

-- Apr 18, 2009 1:04:29 PM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=56978
;

-- Apr 18, 2009 1:04:29 PM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=56976
;

-- Apr 18, 2009 1:04:29 PM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=56975
;

-- Apr 18, 2009 1:04:29 PM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=56972
;

-- Apr 18, 2009 1:04:29 PM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=56980
;

-- Apr 18, 2009 1:05:44 PM ECT
-- Views for Manufacturing Management
CREATE TABLE PP_Product_BOM_Trl (
AD_Client_ID NUMBER(10) NOT NULL, 
AD_Language VARCHAR2(6) NOT NULL, 
AD_Org_ID NUMBER(10) NOT NULL, 
Created DATE DEFAULT SYSDATE NOT NULL, 
CreatedBy NUMBER(10) NOT NULL, 
Description NVARCHAR2(255) DEFAULT NULL , 
Help NVARCHAR2(2000) DEFAULT NULL , 
IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, 
IsTranslated CHAR(1) CHECK (IsTranslated IN ('Y','N')) NOT NULL, 
Name NVARCHAR2(60) NOT NULL, 
PP_Product_BOM_ID NUMBER(10) NOT NULL, 
Updated DATE DEFAULT SYSDATE NOT NULL, 
UpdatedBy NUMBER(10) NOT NULL, 
CONSTRAINT PP_Product_BOM_Trl_Key PRIMARY KEY (AD_Language, PP_Product_BOM_ID))
;

-- Apr 18, 2009 3:16:47 PM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2009-04-18 15:16:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56974
;

-- Apr 18, 2009 3:16:50 PM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2009-04-18 15:16:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56979
;

-- Apr 18, 2009 3:16:54 PM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2009-04-18 15:16:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56977
;

-- Apr 18, 2009 3:16:57 PM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2009-04-18 15:16:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56973
;

-- Apr 18, 2009 3:18:21 PM ECT
-- Views for Manufacturing Management
UPDATE AD_Tab SET IsSortTab='N',Updated=TO_DATE('2009-04-18 15:18:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53215
;

-- Apr 18, 2009 3:19:07 PM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=56973
;

-- Apr 18, 2009 3:19:07 PM ECT
-- Views for Manufacturing Management
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=56977
;

-- Apr 18, 2009 3:19:32 PM ECT
-- Views for Manufacturing Management
ALTER TABLE PP_Product_BOM_Trl MODIFY AD_Client_ID NUMBER(10)
;


-- Apr 21, 2009 1:24:16 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_Window_ID,AccessLevel,CopyColumnsFromTable,Created,CreatedBy,Description,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,LoadSeq,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53193,53006,'3','N',TO_DATE('2009-04-21 13:23:29','YYYY-MM-DD HH24:MI:SS'),0,'BOM & Formula Line Translation','EE01','N','Y','N','Y','N','N','N',0,'BOM & Formula Line Translation','L','PP_Product_BOMLine_Trl',TO_DATE('2009-04-21 13:23:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 21, 2009 1:24:16 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53193 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Apr 21, 2009 1:24:35 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53287,TO_DATE('2009-04-21 13:24:16','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table PP_Product_BOMLine_Trl',1,'Y','N','Y','Y','PP_Product_BOMLine_Trl','N',1000000,TO_DATE('2009-04-21 13:24:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 21, 2009 1:25:30 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57230,102,0,19,53193,'AD_Client_ID',TO_DATE('2009-04-21 13:25:29','YYYY-MM-DD HH24:MI:SS'),0,'@AD_Client_ID@','Client/Tenant for this installation.','EE01',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','N','N','N','Client',0,TO_DATE('2009-04-21 13:25:29','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 21, 2009 1:25:30 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57230 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 21, 2009 1:25:32 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57231,109,0,18,106,53193,'AD_Language',TO_DATE('2009-04-21 13:25:30','YYYY-MM-DD HH24:MI:SS'),0,'Language for this entity','EE01',6,'The Language identifies the language to use for display and formatting','Y','N','N','N','N','Y','Y','N','N','N','N','Language',0,TO_DATE('2009-04-21 13:25:30','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 21, 2009 1:25:32 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57231 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 21, 2009 1:25:36 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57232,113,0,19,53193,104,'AD_Org_ID',TO_DATE('2009-04-21 13:25:32','YYYY-MM-DD HH24:MI:SS'),0,'@AD_Org_ID@','Organizational entity within client','EE01',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','N','N','N','Organization',0,TO_DATE('2009-04-21 13:25:32','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 21, 2009 1:25:36 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57232 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 21, 2009 1:25:38 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57233,245,0,16,53193,'Created',TO_DATE('2009-04-21 13:25:36','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE01',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','N','N','N','Created',0,TO_DATE('2009-04-21 13:25:36','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 21, 2009 1:25:38 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57233 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 21, 2009 1:25:39 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57234,246,0,18,110,53193,'CreatedBy',TO_DATE('2009-04-21 13:25:38','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE01',22,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','N','N','N','Created By',0,TO_DATE('2009-04-21 13:25:38','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 21, 2009 1:25:39 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57234 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 21, 2009 1:25:40 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57235,275,0,10,53193,'Description',TO_DATE('2009-04-21 13:25:39','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE01',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','N','N','Y','Description',0,TO_DATE('2009-04-21 13:25:39','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 21, 2009 1:25:40 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57235 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 21, 2009 1:25:53 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57236,326,0,14,53193,'Help',TO_DATE('2009-04-21 13:25:40','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint','EE01',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','N','N','Y','Comment/Help',0,TO_DATE('2009-04-21 13:25:40','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 21, 2009 1:25:53 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57236 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 21, 2009 1:25:55 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57237,348,0,20,53193,'IsActive',TO_DATE('2009-04-21 13:25:53','YYYY-MM-DD HH24:MI:SS'),0,'Y','The record is active in the system','EE01',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','N','N','Y','Active',0,TO_DATE('2009-04-21 13:25:53','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 21, 2009 1:25:55 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57237 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 21, 2009 1:25:58 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57238,420,0,20,53193,'IsTranslated',TO_DATE('2009-04-21 13:25:55','YYYY-MM-DD HH24:MI:SS'),0,'This column is translated','EE01',1,'The Translated checkbox indicates if this column is translated.','Y','N','N','N','N','Y','N','N','N','N','Y','Translated',0,TO_DATE('2009-04-21 13:25:55','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 21, 2009 1:25:58 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57238 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 21, 2009 1:26:00 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57239,469,0,10,53193,'Name',TO_DATE('2009-04-21 13:25:58','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE01',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','N','N','Y','Name',0,TO_DATE('2009-04-21 13:25:58','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 21, 2009 1:26:00 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57239 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 21, 2009 1:26:03 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57240,53245,0,19,53193,'PP_Product_BOM_ID',TO_DATE('2009-04-21 13:26:00','YYYY-MM-DD HH24:MI:SS'),0,'BOM & Formula','EE01',22,'Y','N','N','N','N','Y','Y','N','N','N','N','BOM & Formula',0,TO_DATE('2009-04-21 13:26:00','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 21, 2009 1:26:03 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57240 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 21, 2009 1:26:05 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57241,607,0,16,53193,'Updated',TO_DATE('2009-04-21 13:26:03','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE01',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','N','N','N','Updated',0,TO_DATE('2009-04-21 13:26:03','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 21, 2009 1:26:05 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57241 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 21, 2009 1:26:07 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57242,608,0,18,110,53193,'UpdatedBy',TO_DATE('2009-04-21 13:26:05','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE01',22,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','N','N','N','Updated By',0,TO_DATE('2009-04-21 13:26:05','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 21, 2009 1:26:07 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57242 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 21, 2009 1:29:50 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Column SET ColumnName='PP_Product_BOMLine_ID', IsUpdateable='N',Updated=TO_DATE('2009-04-21 13:29:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=57240
;

-- Apr 21, 2009 1:30:43 PM ECT
-- Implementing Trl Table for Manufacturing
DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=57239
;

-- Apr 21, 2009 1:30:43 PM ECT
-- Implementing Trl Table for Manufacturing
DELETE FROM AD_Column WHERE AD_Column_ID=57239
;

-- Apr 21, 2009 1:34:21 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,HasTree,Help,ImportFields,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53216,53019,53006,TO_DATE('2009-04-21 13:34:20','YYYY-MM-DD HH24:MI:SS'),0,'BOM & Formula Line Translation','EE01','N',NULL,'N','Y','N','N','N','N','Y','N','Y','BOM & Formula Line Translation','N',30,0,TO_DATE('2009-04-21 13:34:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 21, 2009 1:34:21 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53216 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Apr 21, 2009 1:34:45 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Tab SET TabLevel=1,Updated=TO_DATE('2009-04-21 13:34:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53215
;

-- Apr 21, 2009 1:34:54 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Tab SET TabLevel=2,Updated=TO_DATE('2009-04-21 13:34:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53216
;

-- Apr 21, 2009 1:35:03 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Tab SET AD_Table_ID=53193,Updated=TO_DATE('2009-04-21 13:35:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53216
;

-- Apr 21, 2009 1:35:07 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57237,56984,0,53216,TO_DATE('2009-04-21 13:35:05','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE01','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','N','Active',TO_DATE('2009-04-21 13:35:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 21, 2009 1:35:07 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56984 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 21, 2009 1:35:09 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57240,56985,0,53216,TO_DATE('2009-04-21 13:35:08','YYYY-MM-DD HH24:MI:SS'),0,'BOM & Formula',22,'EE01','Y','Y','Y','N','N','N','N','N','BOM & Formula',TO_DATE('2009-04-21 13:35:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 21, 2009 1:35:09 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56985 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 21, 2009 1:35:10 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57230,56986,0,53216,TO_DATE('2009-04-21 13:35:09','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',22,'EE01','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','N','Client',TO_DATE('2009-04-21 13:35:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 21, 2009 1:35:10 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56986 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 21, 2009 1:35:13 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57236,56987,0,53216,TO_DATE('2009-04-21 13:35:10','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint',2000,'EE01','The Help field contains a hint, comment or help about the use of this item.','Y','Y','Y','N','N','N','N','N','Comment/Help',TO_DATE('2009-04-21 13:35:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 21, 2009 1:35:13 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56987 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 21, 2009 1:35:14 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57235,56988,0,53216,TO_DATE('2009-04-21 13:35:13','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',255,'EE01','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','N','Description',TO_DATE('2009-04-21 13:35:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 21, 2009 1:35:14 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56988 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 21, 2009 1:35:15 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57231,56989,0,53216,TO_DATE('2009-04-21 13:35:14','YYYY-MM-DD HH24:MI:SS'),0,'Language for this entity',6,'EE01','The Language identifies the language to use for display and formatting','Y','Y','Y','N','N','N','N','N','Language',TO_DATE('2009-04-21 13:35:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 21, 2009 1:35:15 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56989 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 21, 2009 1:35:17 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57232,56990,0,53216,TO_DATE('2009-04-21 13:35:15','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',22,'EE01','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','N','N','Organization',TO_DATE('2009-04-21 13:35:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 21, 2009 1:35:17 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56990 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 21, 2009 1:35:18 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57238,56991,0,53216,TO_DATE('2009-04-21 13:35:17','YYYY-MM-DD HH24:MI:SS'),0,'This column is translated',1,'EE01','The Translated checkbox indicates if this column is translated.','Y','Y','Y','N','N','N','N','N','Translated',TO_DATE('2009-04-21 13:35:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 21, 2009 1:35:18 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56991 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 21, 2009 1:38:07 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=56986
;

-- Apr 21, 2009 1:38:07 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=56990
;

-- Apr 21, 2009 1:38:07 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=56985
;

-- Apr 21, 2009 1:38:07 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=56989
;

-- Apr 21, 2009 1:38:07 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=56988
;

-- Apr 21, 2009 1:38:07 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=56987
;

-- Apr 21, 2009 1:38:07 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=56984
;

-- Apr 21, 2009 1:38:07 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=56991
;

-- Apr 21, 2009 1:38:16 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2009-04-21 13:38:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56986
;

-- Apr 21, 2009 1:38:21 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2009-04-21 13:38:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56990
;

-- Apr 21, 2009 1:38:34 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2009-04-21 13:38:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56985
;

-- Apr 21, 2009 1:38:37 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2009-04-21 13:38:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56989
;

-- Apr 21, 2009 1:50:25 PM ECT
-- Implementing Trl Table for Manufacturing
CREATE TABLE PP_Product_BOMLine_Trl 
(AD_Client_ID NUMBER(10) NOT NULL, 
AD_Language VARCHAR2(6) NOT NULL, 
AD_Org_ID NUMBER(10) NOT NULL, 
Created DATE DEFAULT SYSDATE NOT NULL,
CreatedBy NUMBER(10) NOT NULL, 
Description NVARCHAR2(255) DEFAULT NULL ,
Help NVARCHAR2(2000) DEFAULT NULL , 
IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL,
IsTranslated CHAR(1) CHECK (IsTranslated IN ('Y','N')) NOT NULL, 
PP_Product_BOMLine_ID NUMBER(10) NOT NULL, 
Updated DATE DEFAULT SYSDATE NOT NULL, 
UpdatedBy NUMBER(10) NOT NULL, 
CONSTRAINT PP_Product_BOMLine_Trl_Key PRIMARY KEY (AD_Language, PP_Product_BOMLine_ID))
;

-- Apr 21, 2009 1:50:50 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Column SET IsTranslated='Y',Updated=TO_DATE('2009-04-21 13:50:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53355
;

-- Apr 21, 2009 1:50:55 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Column SET IsTranslated='Y',Updated=TO_DATE('2009-04-21 13:50:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53353
;

-- Apr 21, 2009 2:02:33 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET IsActive='N',Updated=TO_DATE('2009-04-21 14:02:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53479
;

-- Apr 21, 2009 2:04:05 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53479
;

-- Apr 21, 2009 2:04:05 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=53480
;

-- Apr 21, 2009 2:06:07 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Tab SET AD_Column_ID=53366,Updated=TO_DATE('2009-04-21 14:06:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53029
;

-- Apr 21, 2009 2:09:54 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=53479
;

-- Apr 21, 2009 2:09:54 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=53480
;

-- Apr 21, 2009 2:10:04 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET IsActive='Y',Updated=TO_DATE('2009-04-21 14:10:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53479
;

-- Apr 21, 2009 2:10:33 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=53482
;

-- Apr 21, 2009 2:10:42 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET Included_Tab_ID=53216,Updated=TO_DATE('2009-04-21 14:10:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53482
;

-- Apr 21, 2009 2:11:53 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Tab SET AD_Column_ID=NULL,Updated=TO_DATE('2009-04-21 14:11:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53029
;

-- Apr 21, 2009 2:12:05 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Tab SET AD_Column_ID=57240, IsSingleRow='N',Updated=TO_DATE('2009-04-21 14:12:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53216
;

-- Apr 21, 2009 2:14:53 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_Window_ID,AccessLevel,CopyColumnsFromTable,Created,CreatedBy,Description,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,LoadSeq,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53194,53006,'3','N',TO_DATE('2009-04-21 14:14:51','YYYY-MM-DD HH24:MI:SS'),0,'Order BOM & Formula','EE01','N','Y','N','Y','N','N','N',0,'Order BOM & Formula Translation','L','PP_Order_BOM_Trl',TO_DATE('2009-04-21 14:14:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 21, 2009 2:14:53 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53194 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Apr 21, 2009 2:14:55 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53288,TO_DATE('2009-04-21 14:14:53','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table PP_Order_BOM_Trl',1,'Y','N','Y','Y','PP_Order_BOM_Trl','N',1000000,TO_DATE('2009-04-21 14:14:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 21, 2009 2:15:08 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57243,102,0,19,53194,'AD_Client_ID',TO_DATE('2009-04-21 14:15:07','YYYY-MM-DD HH24:MI:SS'),0,'@AD_Client_ID@','Client/Tenant for this installation.','EE01',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','N','N','N','Client',0,TO_DATE('2009-04-21 14:15:07','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 21, 2009 2:15:08 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57243 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 21, 2009 2:15:10 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57244,109,0,18,106,53194,'AD_Language',TO_DATE('2009-04-21 14:15:08','YYYY-MM-DD HH24:MI:SS'),0,'Language for this entity','EE01',6,'The Language identifies the language to use for display and formatting','Y','N','N','N','N','Y','Y','N','N','N','N','Language',0,TO_DATE('2009-04-21 14:15:08','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 21, 2009 2:15:10 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57244 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 21, 2009 2:15:12 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57245,113,0,19,53194,104,'AD_Org_ID',TO_DATE('2009-04-21 14:15:10','YYYY-MM-DD HH24:MI:SS'),0,'@AD_Org_ID@','Organizational entity within client','EE01',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','N','N','N','Organization',0,TO_DATE('2009-04-21 14:15:10','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 21, 2009 2:15:12 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57245 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 21, 2009 2:15:14 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57246,245,0,16,53194,'Created',TO_DATE('2009-04-21 14:15:12','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE01',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','N','N','N','Created',0,TO_DATE('2009-04-21 14:15:12','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 21, 2009 2:15:14 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57246 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 21, 2009 2:15:15 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57247,246,0,18,110,53194,'CreatedBy',TO_DATE('2009-04-21 14:15:14','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE01',22,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','N','N','N','Created By',0,TO_DATE('2009-04-21 14:15:14','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 21, 2009 2:15:15 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57247 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 21, 2009 2:15:17 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57248,275,0,10,53194,'Description',TO_DATE('2009-04-21 14:15:15','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE01',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','N','N','Y','Description',0,TO_DATE('2009-04-21 14:15:15','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 21, 2009 2:15:17 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57248 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 21, 2009 2:15:19 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57249,326,0,14,53194,'Help',TO_DATE('2009-04-21 14:15:17','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint','EE01',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','N','N','Y','Comment/Help',0,TO_DATE('2009-04-21 14:15:17','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 21, 2009 2:15:19 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57249 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 21, 2009 2:15:21 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57250,348,0,20,53194,'IsActive',TO_DATE('2009-04-21 14:15:19','YYYY-MM-DD HH24:MI:SS'),0,'Y','The record is active in the system','EE01',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','N','N','Y','Active',0,TO_DATE('2009-04-21 14:15:19','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 21, 2009 2:15:21 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57250 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 21, 2009 2:15:22 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57251,420,0,20,53194,'IsTranslated',TO_DATE('2009-04-21 14:15:21','YYYY-MM-DD HH24:MI:SS'),0,'This column is translated','EE01',1,'The Translated checkbox indicates if this column is translated.','Y','N','N','N','N','Y','N','N','N','N','Y','Translated',0,TO_DATE('2009-04-21 14:15:21','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 21, 2009 2:15:22 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57251 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 21, 2009 2:15:24 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57252,469,0,10,53194,'Name',TO_DATE('2009-04-21 14:15:22','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE01',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','N','N','Y','Name',0,TO_DATE('2009-04-21 14:15:22','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 21, 2009 2:15:24 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57252 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 21, 2009 2:15:26 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57253,53245,0,19,53194,'PP_Product_BOM_ID',TO_DATE('2009-04-21 14:15:24','YYYY-MM-DD HH24:MI:SS'),0,'BOM & Formula','EE01',22,'Y','N','N','N','N','Y','Y','N','N','N','N','BOM & Formula',0,TO_DATE('2009-04-21 14:15:24','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 21, 2009 2:15:26 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57253 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 21, 2009 2:15:27 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57254,607,0,16,53194,'Updated',TO_DATE('2009-04-21 14:15:26','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE01',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','N','N','N','Updated',0,TO_DATE('2009-04-21 14:15:26','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 21, 2009 2:15:27 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57254 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 21, 2009 2:15:29 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57255,608,0,18,110,53194,'UpdatedBy',TO_DATE('2009-04-21 14:15:27','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE01',22,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','N','N','N','Updated By',0,TO_DATE('2009-04-21 14:15:27','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 21, 2009 2:15:29 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57255 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 21, 2009 2:15:52 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Column SET ColumnName='PP_Order_BOM_ID', IsUpdateable='N',Updated=TO_DATE('2009-04-21 14:15:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=57253
;

-- Apr 21, 2009 2:16:34 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_Window_ID,AccessLevel,CopyColumnsFromTable,Created,CreatedBy,Description,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,LoadSeq,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53195,53006,'3','N',TO_DATE('2009-04-21 14:16:33','YYYY-MM-DD HH24:MI:SS'),0,'Order BOM & Formula Line Translation','EE01','N','Y','N','Y','N','N','N',0,'Order BOM & Formula Line Translation','L','PP_Order_BOMLine_Trl',TO_DATE('2009-04-21 14:16:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 21, 2009 2:16:34 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53195 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Apr 21, 2009 2:16:36 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53289,TO_DATE('2009-04-21 14:16:34','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table PP_Order_BOMLine_Trl',1,'Y','N','Y','Y','PP_Order_BOMLine_Trl','N',1000000,TO_DATE('2009-04-21 14:16:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 21, 2009 2:16:50 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57256,102,0,19,53195,'AD_Client_ID',TO_DATE('2009-04-21 14:16:48','YYYY-MM-DD HH24:MI:SS'),0,'@AD_Client_ID@','Client/Tenant for this installation.','EE01',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','N','N','N','Client',0,TO_DATE('2009-04-21 14:16:48','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 21, 2009 2:16:50 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57256 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 21, 2009 2:16:51 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57257,109,0,18,106,53195,'AD_Language',TO_DATE('2009-04-21 14:16:50','YYYY-MM-DD HH24:MI:SS'),0,'Language for this entity','EE01',6,'The Language identifies the language to use for display and formatting','Y','N','N','N','N','Y','Y','N','N','N','N','Language',0,TO_DATE('2009-04-21 14:16:50','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 21, 2009 2:16:52 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57257 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 21, 2009 2:16:53 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57258,113,0,19,53195,104,'AD_Org_ID',TO_DATE('2009-04-21 14:16:52','YYYY-MM-DD HH24:MI:SS'),0,'@AD_Org_ID@','Organizational entity within client','EE01',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','N','N','N','Organization',0,TO_DATE('2009-04-21 14:16:52','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 21, 2009 2:16:53 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57258 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 21, 2009 2:16:55 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57259,245,0,16,53195,'Created',TO_DATE('2009-04-21 14:16:53','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE01',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','N','N','N','Created',0,TO_DATE('2009-04-21 14:16:53','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 21, 2009 2:16:55 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57259 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 21, 2009 2:16:57 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57260,246,0,18,110,53195,'CreatedBy',TO_DATE('2009-04-21 14:16:55','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE01',22,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','N','N','N','Created By',0,TO_DATE('2009-04-21 14:16:55','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 21, 2009 2:16:57 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57260 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 21, 2009 2:16:59 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57261,275,0,10,53195,'Description',TO_DATE('2009-04-21 14:16:57','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE01',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','N','N','Y','Description',0,TO_DATE('2009-04-21 14:16:57','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 21, 2009 2:16:59 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57261 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 21, 2009 2:17:00 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57262,326,0,14,53195,'Help',TO_DATE('2009-04-21 14:16:59','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint','EE01',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','N','N','Y','Comment/Help',0,TO_DATE('2009-04-21 14:16:59','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 21, 2009 2:17:00 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57262 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 21, 2009 2:17:02 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57263,348,0,20,53195,'IsActive',TO_DATE('2009-04-21 14:17:00','YYYY-MM-DD HH24:MI:SS'),0,'Y','The record is active in the system','EE01',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','N','N','Y','Active',0,TO_DATE('2009-04-21 14:17:00','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 21, 2009 2:17:02 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57263 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 21, 2009 2:17:04 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57264,420,0,20,53195,'IsTranslated',TO_DATE('2009-04-21 14:17:02','YYYY-MM-DD HH24:MI:SS'),0,'This column is translated','EE01',1,'The Translated checkbox indicates if this column is translated.','Y','N','N','N','N','Y','N','N','N','N','Y','Translated',0,TO_DATE('2009-04-21 14:17:02','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 21, 2009 2:17:04 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57264 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 21, 2009 2:17:05 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57265,53245,0,19,53195,'PP_Product_BOM_ID',TO_DATE('2009-04-21 14:17:04','YYYY-MM-DD HH24:MI:SS'),0,'BOM & Formula','EE01',22,'Y','N','N','N','N','Y','Y','N','N','N','N','BOM & Formula',0,TO_DATE('2009-04-21 14:17:04','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 21, 2009 2:17:05 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57265 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 21, 2009 2:17:06 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57266,607,0,16,53195,'Updated',TO_DATE('2009-04-21 14:17:05','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE01',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','N','N','N','Updated',0,TO_DATE('2009-04-21 14:17:05','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 21, 2009 2:17:06 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57266 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 21, 2009 2:17:08 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57267,608,0,18,110,53195,'UpdatedBy',TO_DATE('2009-04-21 14:17:06','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE01',22,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','N','N','N','Updated By',0,TO_DATE('2009-04-21 14:17:06','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Apr 21, 2009 2:17:08 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57267 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 21, 2009 2:17:36 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Column SET AD_Element_ID=53298, ColumnName='PP_Order_BOM_ID', Description=NULL, Help=NULL, IsUpdateable='N', Name='Manufacturing Order BOM',Updated=TO_DATE('2009-04-21 14:17:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=57265
;

-- Apr 21, 2009 2:17:36 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=57265
;

-- Apr 21, 2009 2:17:36 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET Name='Manufacturing Order BOM', Description=NULL, Help=NULL WHERE AD_Column_ID=57265 AND IsCentrallyMaintained='Y'
;

-- Apr 21, 2009 2:19:25 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,HasTree,Help,ImportFields,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53217,53194,53009,TO_DATE('2009-04-21 14:19:24','YYYY-MM-DD HH24:MI:SS'),0,'Translation','EE01','N','Translation','N','Y','N','N','N','N','Y','N','Y','Orden BOM Translation','N',25,2,TO_DATE('2009-04-21 14:19:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 21, 2009 2:19:25 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53217 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Apr 21, 2009 2:19:31 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57250,56992,0,53217,TO_DATE('2009-04-21 14:19:29','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE01','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','N','Active',TO_DATE('2009-04-21 14:19:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 21, 2009 2:19:31 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56992 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 21, 2009 2:19:32 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57253,56993,0,53217,TO_DATE('2009-04-21 14:19:31','YYYY-MM-DD HH24:MI:SS'),0,'BOM & Formula',22,'EE01','Y','Y','Y','N','N','N','N','N','BOM & Formula',TO_DATE('2009-04-21 14:19:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 21, 2009 2:19:32 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56993 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 21, 2009 2:19:34 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57243,56994,0,53217,TO_DATE('2009-04-21 14:19:32','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',22,'EE01','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','N','Client',TO_DATE('2009-04-21 14:19:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 21, 2009 2:19:34 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56994 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 21, 2009 2:19:36 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57249,56995,0,53217,TO_DATE('2009-04-21 14:19:34','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint',2000,'EE01','The Help field contains a hint, comment or help about the use of this item.','Y','Y','Y','N','N','N','N','N','Comment/Help',TO_DATE('2009-04-21 14:19:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 21, 2009 2:19:36 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56995 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 21, 2009 2:19:37 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57248,56996,0,53217,TO_DATE('2009-04-21 14:19:36','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',255,'EE01','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','N','Description',TO_DATE('2009-04-21 14:19:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 21, 2009 2:19:37 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56996 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 21, 2009 2:19:38 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57244,56997,0,53217,TO_DATE('2009-04-21 14:19:37','YYYY-MM-DD HH24:MI:SS'),0,'Language for this entity',6,'EE01','The Language identifies the language to use for display and formatting','Y','Y','Y','N','N','N','N','N','Language',TO_DATE('2009-04-21 14:19:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 21, 2009 2:19:38 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56997 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 21, 2009 2:19:40 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57252,56998,0,53217,TO_DATE('2009-04-21 14:19:38','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',60,'EE01','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','N','Name',TO_DATE('2009-04-21 14:19:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 21, 2009 2:19:40 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56998 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 21, 2009 2:19:42 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57245,56999,0,53217,TO_DATE('2009-04-21 14:19:40','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',22,'EE01','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','N','N','Organization',TO_DATE('2009-04-21 14:19:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 21, 2009 2:19:42 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56999 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 21, 2009 2:19:43 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57251,57000,0,53217,TO_DATE('2009-04-21 14:19:42','YYYY-MM-DD HH24:MI:SS'),0,'This column is translated',1,'EE01','The Translated checkbox indicates if this column is translated.','Y','Y','Y','N','N','N','N','N','Translated',TO_DATE('2009-04-21 14:19:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 21, 2009 2:19:43 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57000 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 21, 2009 2:20:20 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=56994
;

-- Apr 21, 2009 2:20:20 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=56999
;

-- Apr 21, 2009 2:20:20 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=56993
;

-- Apr 21, 2009 2:20:20 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=56997
;

-- Apr 21, 2009 2:20:20 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=56992
;

-- Apr 21, 2009 2:20:20 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=56998
;

-- Apr 21, 2009 2:20:20 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=56996
;

-- Apr 21, 2009 2:20:20 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=56995
;

-- Apr 21, 2009 2:20:20 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=57000
;

-- Apr 21, 2009 2:20:27 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2009-04-21 14:20:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56994
;

-- Apr 21, 2009 2:20:35 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2009-04-21 14:20:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56999
;

-- Apr 21, 2009 2:20:39 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2009-04-21 14:20:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56993
;

-- Apr 21, 2009 2:20:42 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2009-04-21 14:20:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56997
;

-- Apr 21, 2009 2:22:06 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,HasTree,Help,ImportFields,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy,WhereClause) VALUES (0,0,53218,53025,53009,TO_DATE('2009-04-21 14:22:04','YYYY-MM-DD HH24:MI:SS'),0,NULL,'EE01','N',NULL,'N','Y','N','N','N','N','Y','N','Y','Order Components Translation','N',35,0,TO_DATE('2009-04-21 14:22:04','YYYY-MM-DD HH24:MI:SS'),0,NULL)
;

-- Apr 21, 2009 2:22:06 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53218 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Apr 21, 2009 2:22:23 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Tab SET AD_Table_ID=53195,Updated=TO_DATE('2009-04-21 14:22:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53218
;

-- Apr 21, 2009 2:22:29 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57263,57001,0,53218,TO_DATE('2009-04-21 14:22:25','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE01','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','N','Active',TO_DATE('2009-04-21 14:22:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 21, 2009 2:22:30 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57001 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 21, 2009 2:22:32 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57256,57002,0,53218,TO_DATE('2009-04-21 14:22:30','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',22,'EE01','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','N','Client',TO_DATE('2009-04-21 14:22:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 21, 2009 2:22:32 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57002 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 21, 2009 2:22:34 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57262,57003,0,53218,TO_DATE('2009-04-21 14:22:32','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint',2000,'EE01','The Help field contains a hint, comment or help about the use of this item.','Y','Y','Y','N','N','N','N','N','Comment/Help',TO_DATE('2009-04-21 14:22:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 21, 2009 2:22:34 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57003 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 21, 2009 2:22:36 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57261,57004,0,53218,TO_DATE('2009-04-21 14:22:34','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',255,'EE01','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','N','Description',TO_DATE('2009-04-21 14:22:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 21, 2009 2:22:36 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57004 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 21, 2009 2:22:37 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57257,57005,0,53218,TO_DATE('2009-04-21 14:22:36','YYYY-MM-DD HH24:MI:SS'),0,'Language for this entity',6,'EE01','The Language identifies the language to use for display and formatting','Y','Y','Y','N','N','N','N','N','Language',TO_DATE('2009-04-21 14:22:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 21, 2009 2:22:37 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57005 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 21, 2009 2:22:39 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57265,57006,0,53218,TO_DATE('2009-04-21 14:22:37','YYYY-MM-DD HH24:MI:SS'),0,22,'EE01','Y','Y','Y','N','N','N','N','N','Manufacturing Order BOM',TO_DATE('2009-04-21 14:22:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 21, 2009 2:22:39 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57006 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 21, 2009 2:22:51 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57258,57007,0,53218,TO_DATE('2009-04-21 14:22:39','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',22,'EE01','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','N','N','Organization',TO_DATE('2009-04-21 14:22:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 21, 2009 2:22:51 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57007 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 21, 2009 2:22:53 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57264,57008,0,53218,TO_DATE('2009-04-21 14:22:51','YYYY-MM-DD HH24:MI:SS'),0,'This column is translated',1,'EE01','The Translated checkbox indicates if this column is translated.','Y','Y','Y','N','N','N','N','N','Translated',TO_DATE('2009-04-21 14:22:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 21, 2009 2:22:53 PM ECT
-- Implementing Trl Table for Manufacturing
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57008 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Apr 21, 2009 2:24:48 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=57002
;

-- Apr 21, 2009 2:24:48 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=57007
;

-- Apr 21, 2009 2:24:48 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=57006
;

-- Apr 21, 2009 2:24:48 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=57005
;

-- Apr 21, 2009 2:24:48 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=57004
;

-- Apr 21, 2009 2:24:48 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=57003
;

-- Apr 21, 2009 2:24:48 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=57001
;

-- Apr 21, 2009 2:24:48 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=57008
;

-- Apr 21, 2009 2:24:55 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2009-04-21 14:24:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=57002
;

-- Apr 21, 2009 2:24:58 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2009-04-21 14:24:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=57007
;

-- Apr 21, 2009 2:25:03 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2009-04-21 14:25:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=57006
;

-- Apr 21, 2009 2:25:05 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2009-04-21 14:25:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=57005
;

-- Apr 21, 2009 2:25:32 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Tab SET TabLevel=3,Updated=TO_DATE('2009-04-21 14:25:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53218
;

-- Apr 21, 2009 2:25:39 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=53747
;

-- Apr 21, 2009 2:26:11 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET Included_Tab_ID=53218,Updated=TO_DATE('2009-04-21 14:26:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53747
;

-- Apr 21, 2009 2:26:24 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Tab SET IsSingleRow='N',Updated=TO_DATE('2009-04-21 14:26:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53218
;

-- Apr 21, 2009 2:26:34 PM ECT
-- Implementing Trl Table for Manufacturing
CREATE TABLE PP_Order_BOMLine_Trl (
AD_Client_ID NUMBER(10) NOT NULL, 
AD_Language VARCHAR2(6) NOT NULL, 
AD_Org_ID NUMBER(10) NOT NULL, 
Created DATE DEFAULT SYSDATE NOT NULL, 
CreatedBy NUMBER(10) NOT NULL, 
Description NVARCHAR2(255) DEFAULT NULL , 
Help NVARCHAR2(2000) DEFAULT NULL , 
IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, 
IsTranslated CHAR(1) CHECK (IsTranslated IN ('Y','N')) NOT NULL, 
PP_Order_BOMLine_ID NUMBER(10) NOT NULL, 
Updated DATE DEFAULT SYSDATE NOT NULL, 
UpdatedBy NUMBER(10) NOT NULL, 
CONSTRAINT PP_Order_BOMLine_Trl_Key PRIMARY KEY (AD_Language, PP_Order_BOMLine_ID))
;

-- Apr 21, 2009 2:27:32 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Column SET AD_Element_ID=53275, ColumnName='PP_Order_BOMLine_ID', Description=NULL, Help=NULL, IsUpdateable='N', Name='Manufacturing Order BOM Line',Updated=TO_DATE('2009-04-21 14:27:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=57265
;

-- Apr 21, 2009 2:27:32 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=57265
;

-- Apr 21, 2009 2:27:32 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET Name='Manufacturing Order BOM Line', Description=NULL, Help=NULL WHERE AD_Column_ID=57265 AND IsCentrallyMaintained='Y'
;

-- Apr 21, 2009 2:28:00 PM ECT
-- Implementing Trl Table for Manufacturing
CREATE TABLE PP_Order_BOM_Trl (
AD_Client_ID NUMBER(10) NOT NULL, 
AD_Language VARCHAR2(6) NOT NULL, 
AD_Org_ID NUMBER(10) NOT NULL, 
Created DATE DEFAULT SYSDATE NOT NULL, 
CreatedBy NUMBER(10) NOT NULL, 
Description NVARCHAR2(255) DEFAULT NULL , 
Help NVARCHAR2(2000) DEFAULT NULL , 
IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, 
IsTranslated CHAR(1) CHECK (IsTranslated IN ('Y','N')) NOT NULL, 
Name NVARCHAR2(60) NOT NULL, 
PP_Order_BOM_ID NUMBER(10) NOT NULL, 
Updated DATE DEFAULT SYSDATE NOT NULL, 
UpdatedBy NUMBER(10) NOT NULL, 
CONSTRAINT PP_Order_BOM_Trl_Key PRIMARY KEY (AD_Language, PP_Order_BOM_ID))
;

-- Apr 21, 2009 2:33:27 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Tab SET AD_Column_ID=NULL,Updated=TO_DATE('2009-04-21 14:33:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53216
;

-- Apr 21, 2009 2:33:54 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Tab SET TabLevel=1,Updated=TO_DATE('2009-04-21 14:33:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53218
;

-- Apr 21, 2009 2:34:52 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Tab SET TabLevel=3,Updated=TO_DATE('2009-04-21 14:34:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53218
;

-- Apr 21, 2009 2:35:17 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET Included_Tab_ID=NULL,Updated=TO_DATE('2009-04-21 14:35:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53803
;

-- Apr 21, 2009 2:35:21 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53803
;

-- Apr 21, 2009 2:35:44 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET Included_Tab_ID=NULL,Updated=TO_DATE('2009-04-21 14:35:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53747
;

-- Apr 21, 2009 2:35:51 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53747
;

-- Apr 21, 2009 2:37:20 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET Included_Tab_ID=NULL,Updated=TO_DATE('2009-04-21 14:37:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53479
;

-- Apr 21, 2009 2:37:28 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53479
;

-- Apr 21, 2009 2:37:28 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=53480
;

-- Apr 21, 2009 2:37:40 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET Included_Tab_ID=NULL,Updated=TO_DATE('2009-04-21 14:37:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53482
;

-- Apr 21, 2009 2:37:54 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53482
;

-- Apr 21, 2009 2:44:58 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=53481
;

-- Apr 21, 2009 2:45:07 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53481
;

-- Apr 21, 2009 2:45:27 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Tab SET IsSingleRow='Y', Name='Components Translation',Updated=TO_DATE('2009-04-21 14:45:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53216
;

-- Apr 21, 2009 2:45:27 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=53216
;

-- Apr 21, 2009 2:49:22 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Column SET AD_Element_ID=53254, ColumnName='PP_Product_BOMLine_ID', Description='BOM Line', Help='The BOM Line is a unique identifier for a BOM line in an BOM.', IsUpdateable='N', Name='BOM Line',Updated=TO_DATE('2009-04-21 14:49:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=57240
;

-- Apr 21, 2009 2:49:22 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=57240
;

-- Apr 21, 2009 2:49:22 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET Name='BOM Line', Description='BOM Line', Help='The BOM Line is a unique identifier for a BOM line in an BOM.' WHERE AD_Column_ID=57240 AND IsCentrallyMaintained='Y'
;

-- Apr 21, 2009 2:49:25 PM ECT
-- Implementing Trl Table for Manufacturing
ALTER TABLE PP_Product_BOMLine_Trl MODIFY PP_Product_BOMLine_ID NUMBER(10)
;

-- Apr 21, 2009 2:52:13 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Column SET AD_Element_ID=53298, ColumnName='PP_Order_BOM_ID', Description=NULL, Help=NULL, IsUpdateable='N', Name='Manufacturing Order BOM',Updated=TO_DATE('2009-04-21 14:52:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=57253
;

-- Apr 21, 2009 2:52:13 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=57253
;

-- Apr 21, 2009 2:52:13 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET Name='Manufacturing Order BOM', Description=NULL, Help=NULL WHERE AD_Column_ID=57253 AND IsCentrallyMaintained='Y'
;

-- Apr 21, 2009 2:52:17 PM ECT
-- Implementing Trl Table for Manufacturing
ALTER TABLE PP_Order_BOM_Trl MODIFY PP_Order_BOM_ID NUMBER(10)
;

-- Apr 21, 2009 2:53:03 PM ECT
-- Implementing Trl Table for Manufacturing
ALTER TABLE PP_Product_BOM_Trl MODIFY PP_Product_BOM_ID NUMBER(10)
;

-- Apr 21, 2009 2:53:53 PM ECT
-- Implementing Trl Table for Manufacturing
ALTER TABLE PP_Product_BOMLine_Trl MODIFY PP_Product_BOMLine_ID NUMBER(10)
;

-- Apr 22, 2009 12:03:35 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Column SET IsTranslated='Y',Updated=TO_DATE('2009-04-22 12:03:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53325
;

-- Apr 22, 2009 12:03:48 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Column SET IsTranslated='Y',Updated=TO_DATE('2009-04-22 12:03:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53329
;

-- Apr 22, 2009 12:03:52 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Column SET IsTranslated='Y',Updated=TO_DATE('2009-04-22 12:03:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53322
;

-- Apr 22, 2009 12:04:48 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Column SET IsTranslated='Y',Updated=TO_DATE('2009-04-22 12:04:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53604
;

-- Apr 22, 2009 12:04:54 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Column SET IsTranslated='Y',Updated=TO_DATE('2009-04-22 12:04:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53606
;

-- Apr 22, 2009 12:04:59 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Column SET IsTranslated='Y',Updated=TO_DATE('2009-04-22 12:04:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53596
;

-- Apr 22, 2009 12:05:16 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Column SET IsTranslated='Y',Updated=TO_DATE('2009-04-22 12:05:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53554
;

-- Apr 22, 2009 12:05:33 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Column SET IsTranslated='Y',Updated=TO_DATE('2009-04-22 12:05:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53564
;

-- Apr 22, 2009 2:20:22 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Column SET IsIdentifier='Y',Updated=TO_DATE('2009-04-22 14:20:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53364
;

-- Apr 22, 2009 2:20:30 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Column SET IsIdentifier='Y', SeqNo=2,Updated=TO_DATE('2009-04-22 14:20:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53350
;

-- Apr 22, 2009 2:20:44 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Column SET SeqNo=1,Updated=TO_DATE('2009-04-22 14:20:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53364
;

-- Apr 22, 2009 2:32:41 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=53481
;

-- Apr 22, 2009 2:32:51 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET Included_Tab_ID=53216,Updated=TO_DATE('2009-04-22 14:32:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53481
;

-- Apr 22, 2009 2:34:17 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=53479
;

-- Apr 22, 2009 2:34:17 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=53480
;

-- Apr 22, 2009 2:34:26 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET Included_Tab_ID=53028,Updated=TO_DATE('2009-04-22 14:34:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53479
;

-- Apr 22, 2009 2:57:25 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET Included_Tab_ID=53029,Updated=TO_DATE('2009-04-22 14:57:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53479
;

-- Apr 22, 2009 2:58:29 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Tab SET IsSingleRow='N',Updated=TO_DATE('2009-04-22 14:58:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53216
;

-- Apr 22, 2009 3:01:22 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=53803
;

-- Apr 22, 2009 3:01:48 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET Included_Tab_ID=53217,Updated=TO_DATE('2009-04-22 15:01:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53803
;

-- Apr 22, 2009 3:02:12 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=53747
;

-- Apr 22, 2009 3:02:57 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET Included_Tab_ID=53218,Updated=TO_DATE('2009-04-22 15:02:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53747
;

-- Apr 22, 2009 3:19:53 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET Included_Tab_ID=53039,Updated=TO_DATE('2009-04-22 15:19:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53803
;

-- Apr 22, 2009 4:05:11 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=53747
;

-- Apr 22, 2009 4:05:11 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=53755
;

-- Apr 22, 2009 4:05:11 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=53756
;

-- Apr 22, 2009 4:05:11 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=53757
;

-- Apr 22, 2009 4:05:11 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=53758
;

-- Apr 22, 2009 4:05:11 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=53759
;

-- Apr 22, 2009 4:05:11 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=53760
;

-- Apr 22, 2009 4:05:11 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=53761
;

-- Apr 22, 2009 4:05:11 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=53762
;

-- Apr 22, 2009 4:05:11 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=53763
;

-- Apr 22, 2009 4:05:11 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=53764
;

-- Apr 22, 2009 4:05:11 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=53765
;

-- Apr 22, 2009 4:05:11 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=53766
;

-- Apr 22, 2009 4:05:11 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=53767
;

-- Apr 22, 2009 4:05:11 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=53768
;

-- Apr 22, 2009 4:05:11 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=53769
;

-- Apr 22, 2009 4:05:11 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=53770
;

-- Apr 22, 2009 4:05:11 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=53771
;

-- Apr 22, 2009 4:05:11 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=53772
;

-- Apr 22, 2009 4:05:11 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=53773
;

-- Apr 22, 2009 4:05:11 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=53774
;

-- Apr 22, 2009 4:05:11 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=53775
;

-- Apr 22, 2009 4:05:11 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=53776
;

-- Apr 22, 2009 4:05:11 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=53777
;

-- Apr 22, 2009 4:05:11 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=53778
;

-- Apr 22, 2009 4:05:11 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=53779
;

-- Apr 22, 2009 4:05:11 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=53780
;

-- Apr 22, 2009 4:05:11 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=53781
;

-- Apr 22, 2009 4:05:11 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=53782
;

-- Apr 22, 2009 4:06:10 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=53755
;

-- Apr 22, 2009 4:06:10 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=53756
;

-- Apr 22, 2009 4:06:10 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=53757
;

-- Apr 22, 2009 4:06:10 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=53758
;

-- Apr 22, 2009 4:06:10 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=53759
;

-- Apr 22, 2009 4:06:10 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=53760
;

-- Apr 22, 2009 4:06:10 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=53761
;

-- Apr 22, 2009 4:06:10 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=53762
;

-- Apr 22, 2009 4:06:10 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=53763
;

-- Apr 22, 2009 4:06:10 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=53764
;

-- Apr 22, 2009 4:06:10 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=53765
;

-- Apr 22, 2009 4:06:10 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=53766
;

-- Apr 22, 2009 4:06:10 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=53767
;

-- Apr 22, 2009 4:06:10 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=53768
;

-- Apr 22, 2009 4:06:10 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=53769
;

-- Apr 22, 2009 4:06:10 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=53770
;

-- Apr 22, 2009 4:06:10 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=53771
;

-- Apr 22, 2009 4:06:10 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=53772
;

-- Apr 22, 2009 4:06:10 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=53773
;

-- Apr 22, 2009 4:06:10 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=53774
;

-- Apr 22, 2009 4:06:10 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=53775
;

-- Apr 22, 2009 4:06:10 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=53776
;

-- Apr 22, 2009 4:06:10 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=53777
;

-- Apr 22, 2009 4:06:10 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=53778
;

-- Apr 22, 2009 4:06:10 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=53779
;

-- Apr 22, 2009 4:06:10 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=53780
;

-- Apr 22, 2009 4:06:10 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=53781
;

-- Apr 22, 2009 4:06:10 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=53782
;

-- Apr 22, 2009 4:06:10 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=53747
;

-- Apr 22, 2009 4:06:52 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2009-04-22 16:06:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53803
;

-- Apr 22, 2009 4:08:55 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET Included_Tab_ID=NULL,Updated=TO_DATE('2009-04-22 16:08:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53803
;

-- Apr 22, 2009 4:09:02 PM ECT
-- Implementing Trl Table for Manufacturing
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53803
;
