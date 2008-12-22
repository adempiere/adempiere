-- Sep 6, 2008 7:47:20 PM COT
-- Default comment for updating dictionary
UPDATE AD_SysConfig SET Value='[ 2074600 ] Housekeeping - Process to delete historic information',Updated=TO_DATE('2008-09-06 19:47:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_SysConfig_ID=50003
;

-- Sep 6, 2008 7:49:18 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Table (AccessLevel,AD_Client_ID,AD_Org_ID,AD_Table_ID,CopyColumnsFromTable,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,LoadSeq,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES ('4',0,0,53147,'N',TO_DATE('2008-09-06 19:49:15','YYYY-MM-DD HH24:MI:SS'),100,'D','N','Y','Y','Y','N','N','N',0,'House Keeping Configuration','L','AD_HouseKeeping',TO_DATE('2008-09-06 19:49:15','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 7:49:18 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53147 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Sep 6, 2008 7:49:30 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53168,TO_DATE('2008-09-06 19:49:18','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table AD_HouseKeeping',1,'Y','N','Y','Y','AD_HouseKeeping','N',1000000,TO_DATE('2008-09-06 19:49:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 7:50:23 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56327,102,0,19,53147,'AD_Client_ID',TO_DATE('2008-09-06 19:50:21','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Client_ID@','Client/Tenant for this installation.','D',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','Y','N','N','Client',0,TO_DATE('2008-09-06 19:50:21','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Sep 6, 2008 7:50:23 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56327 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2008 7:50:24 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56328,113,0,19,53147,104,'AD_Org_ID',TO_DATE('2008-09-06 19:50:23','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Org_ID@','Organizational entity within client','D',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','Y','N','N','Organization',0,TO_DATE('2008-09-06 19:50:23','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Sep 6, 2008 7:50:24 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56328 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2008 7:50:27 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56329,245,0,16,53147,'Created',TO_DATE('2008-09-06 19:50:24','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','D',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','Y','N','N','Created',0,TO_DATE('2008-09-06 19:50:24','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Sep 6, 2008 7:50:27 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56329 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2008 7:50:28 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56330,246,0,18,110,53147,'CreatedBy',TO_DATE('2008-09-06 19:50:27','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','D',22,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Created By',0,TO_DATE('2008-09-06 19:50:27','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Sep 6, 2008 7:50:28 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56330 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2008 7:50:29 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56331,275,0,10,53147,'Description',TO_DATE('2008-09-06 19:50:28','YYYY-MM-DD HH24:MI:SS'),100,'Optional short description of the record','D',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','Y','N','Y','Description',0,TO_DATE('2008-09-06 19:50:28','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Sep 6, 2008 7:50:29 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56331 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2008 7:50:31 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56332,326,0,14,53147,'Help',TO_DATE('2008-09-06 19:50:29','YYYY-MM-DD HH24:MI:SS'),100,'Comment or Hint','D',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','Y','N','Y','Comment/Help',0,TO_DATE('2008-09-06 19:50:29','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Sep 6, 2008 7:50:36 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56332 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2008 7:50:37 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56333,348,0,20,53147,'IsActive',TO_DATE('2008-09-06 19:50:36','YYYY-MM-DD HH24:MI:SS'),100,'Y','The record is active in the system','D',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','Y','N','Y','Active',0,TO_DATE('2008-09-06 19:50:36','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Sep 6, 2008 7:50:37 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56333 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2008 7:50:42 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53680,0,'AD_HouseKeeping_ID',TO_DATE('2008-09-06 19:50:37','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','House Keeping Configuration','House Keeping Configuration',TO_DATE('2008-09-06 19:50:37','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 7:50:42 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53680 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Sep 6, 2008 7:50:42 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56334,53680,0,13,53147,'AD_HouseKeeping_ID',TO_DATE('2008-09-06 19:50:37','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','Y','Y','N','N','Y','N','N','House Keeping Configuration',0,TO_DATE('2008-09-06 19:50:37','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Sep 6, 2008 7:50:42 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56334 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2008 7:50:47 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56335,469,0,10,53147,'Name',TO_DATE('2008-09-06 19:50:43','YYYY-MM-DD HH24:MI:SS'),100,'Alphanumeric identifier of the entity','D',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','Y','N','Y','Name',1,TO_DATE('2008-09-06 19:50:43','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Sep 6, 2008 7:50:47 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56335 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2008 7:50:47 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56336,607,0,16,53147,'Updated',TO_DATE('2008-09-06 19:50:47','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','D',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated',0,TO_DATE('2008-09-06 19:50:47','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Sep 6, 2008 7:50:47 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56336 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2008 7:50:48 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56337,608,0,18,110,53147,'UpdatedBy',TO_DATE('2008-09-06 19:50:47','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','D',22,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated By',0,TO_DATE('2008-09-06 19:50:47','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Sep 6, 2008 7:50:48 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56337 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2008 7:50:49 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56338,620,0,10,53147,'Value',TO_DATE('2008-09-06 19:50:48','YYYY-MM-DD HH24:MI:SS'),100,'Search key for the record in the format required - must be unique','D',40,'A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','N','N','Y','N','N','Y','N','Y','Search Key',0,TO_DATE('2008-09-06 19:50:48','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Sep 6, 2008 7:50:49 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56338 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2008 7:51:03 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Table SET EntityType='D',Updated=TO_DATE('2008-09-06 19:51:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=53147
;

-- Sep 6, 2008 7:57:16 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Element SET EntityType='D',Updated=TO_DATE('2008-09-06 19:57:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53680
;

-- Sep 6, 2008 7:59:57 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53681,0,'LastDeleted',TO_DATE('2008-09-06 19:59:38','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Last Deleted','Last Deleted',TO_DATE('2008-09-06 19:59:38','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 7:59:57 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53681 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Sep 6, 2008 8:00:44 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56339,53681,0,11,53147,'LastDeleted',TO_DATE('2008-09-06 20:00:43','YYYY-MM-DD HH24:MI:SS'),100,'D',10,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Last Deleted',0,TO_DATE('2008-09-06 20:00:43','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 6, 2008 8:00:44 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56339 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2008 8:02:01 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53682,0,'BackupFolder',TO_DATE('2008-09-06 20:02:00','YYYY-MM-DD HH24:MI:SS'),100,'Backup Folder','D','Y','Backup Folder','Backup Folder',TO_DATE('2008-09-06 20:02:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:02:01 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53682 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Sep 6, 2008 8:02:32 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56340,53682,0,38,53147,'BackupFolder',TO_DATE('2008-09-06 20:02:32','YYYY-MM-DD HH24:MI:SS'),100,'Backup Folder','D',255,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Backup Folder',0,TO_DATE('2008-09-06 20:02:32','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 6, 2008 8:02:32 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56340 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2008 8:03:28 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53683,0,'IsExportXMLBackup',TO_DATE('2008-09-06 20:03:27','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Expor tXML Backup','Export XML Backup',TO_DATE('2008-09-06 20:03:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:03:28 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53683 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Sep 6, 2008 8:03:57 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56341,53683,0,20,53147,'IsExportXMLBackup',TO_DATE('2008-09-06 20:03:57','YYYY-MM-DD HH24:MI:SS'),100,'D',1,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Expor tXML Backup',0,TO_DATE('2008-09-06 20:03:57','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 6, 2008 8:03:57 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56341 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2008 8:04:38 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53684,0,'IsSaveInHistoric',TO_DATE('2008-09-06 20:04:36','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Save In Historic','Save In Historic',TO_DATE('2008-09-06 20:04:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:04:38 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53684 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Sep 6, 2008 8:05:00 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56342,53684,0,20,53147,'IsSaveInHistoric',TO_DATE('2008-09-06 20:05:00','YYYY-MM-DD HH24:MI:SS'),100,'D',1,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Save In Historic',0,TO_DATE('2008-09-06 20:05:00','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 6, 2008 8:05:00 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56342 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2008 8:05:36 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53685,0,'LastRun',TO_DATE('2008-09-06 20:05:36','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Last Run','Last Run',TO_DATE('2008-09-06 20:05:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:05:36 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53685 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Sep 6, 2008 8:05:56 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56343,53685,0,16,53147,'LastRun',TO_DATE('2008-09-06 20:05:55','YYYY-MM-DD HH24:MI:SS'),100,'D',10,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Last Run',0,TO_DATE('2008-09-06 20:05:55','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 6, 2008 8:05:56 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56343 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2008 8:07:09 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56344,524,0,28,53147,'Processing',TO_DATE('2008-09-06 20:07:03','YYYY-MM-DD HH24:MI:SS'),100,'D',1,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Process Now',0,TO_DATE('2008-09-06 20:07:03','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 6, 2008 8:07:09 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56344 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2008 8:07:54 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56345,630,0,10,53147,'WhereClause',TO_DATE('2008-09-06 20:07:53','YYYY-MM-DD HH24:MI:SS'),100,'Fully qualified SQL WHERE clause','D',255,'The Where Clause indicates the SQL WHERE clause to use for record selection. The WHERE clause is added to the query. Fully qualified means "tablename.columnname".','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Sql WHERE',0,TO_DATE('2008-09-06 20:07:53','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 6, 2008 8:07:54 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56345 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2008 8:10:43 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56346,126,0,19,53147,'AD_Table_ID',TO_DATE('2008-09-06 20:10:37','YYYY-MM-DD HH24:MI:SS'),100,'Database Table information','D',10,'The Database Table provides the information of the table definition','Y','Y','N','N','N','N','N','Y','N','N','N','N','Y','Table',0,TO_DATE('2008-09-06 20:10:37','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 6, 2008 8:10:43 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56346 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 6, 2008 8:10:52 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
CREATE TABLE AD_HouseKeeping (AD_Client_ID NUMBER(10) DEFAULT  NULL  NOT NULL, AD_HouseKeeping_ID NUMBER(10) NOT NULL, AD_Org_ID NUMBER(10) DEFAULT  NULL  NOT NULL, AD_Table_ID NUMBER(10) NOT NULL, BackupFolder NVARCHAR2(255), Created DATE NOT NULL, CreatedBy NUMBER(10) NOT NULL, Description NVARCHAR2(255), Help NVARCHAR2(2000), IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, IsExportXMLBackup CHAR(1) CHECK (IsExportXMLBackup IN ('Y','N')), IsSaveInHistoric CHAR(1) CHECK (IsSaveInHistoric IN ('Y','N')), LastDeleted NUMBER(10), LastRun DATE, Name NVARCHAR2(60) NOT NULL, Processing CHAR(1), Updated DATE NOT NULL, UpdatedBy NUMBER(10) NOT NULL, Value NVARCHAR2(40) NOT NULL, WhereClause NVARCHAR2(255), CONSTRAINT AD_HouseKeeping_Key PRIMARY KEY (AD_HouseKeeping_ID))
;

-- Sep 6, 2008 8:12:13 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,EntityType,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WindowType,WinHeight,WinWidth) VALUES (0,0,53063,TO_DATE('2008-09-06 20:12:10','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','N','Y','House Keeping','N',TO_DATE('2008-09-06 20:12:10','YYYY-MM-DD HH24:MI:SS'),100,'M',0,0)
;

-- Sep 6, 2008 8:12:13 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53063 AND EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Window_ID!=t.AD_Window_ID)
;

-- Sep 6, 2008 8:12:13 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,50002,53063,TO_DATE('2008-09-06 20:12:13','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-09-06 20:12:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:12:14 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,0,53063,TO_DATE('2008-09-06 20:12:14','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-09-06 20:12:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:12:14 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,102,53063,TO_DATE('2008-09-06 20:12:14','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-09-06 20:12:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:12:14 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,103,53063,TO_DATE('2008-09-06 20:12:14','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-09-06 20:12:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:12:14 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,50001,53063,TO_DATE('2008-09-06 20:12:14','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-09-06 20:12:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:13:42 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,EntityType,HasTree,ImportFields,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53178,53147,53063,TO_DATE('2008-09-06 20:13:39','YYYY-MM-DD HH24:MI:SS'),100,'D','N','N','Y','N','N','Y','N','Y','N','N','House Keeping','N',10,0,TO_DATE('2008-09-06 20:13:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:13:42 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53178 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Sep 6, 2008 8:14:05 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56333,56353,0,53178,TO_DATE('2008-09-06 20:13:58','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system',1,'D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','N','Active',TO_DATE('2008-09-06 20:13:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:14:05 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56353 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2008 8:14:14 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56340,56354,0,53178,TO_DATE('2008-09-06 20:14:05','YYYY-MM-DD HH24:MI:SS'),100,'Backup Folder',255,'D','Y','Y','Y','N','N','N','N','N','Backup Folder',TO_DATE('2008-09-06 20:14:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:14:14 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56354 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2008 8:14:15 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56327,56355,0,53178,TO_DATE('2008-09-06 20:14:14','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.',22,'D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','N','Client',TO_DATE('2008-09-06 20:14:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:14:15 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56355 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2008 8:14:20 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56332,56356,0,53178,TO_DATE('2008-09-06 20:14:15','YYYY-MM-DD HH24:MI:SS'),100,'Comment or Hint',2000,'D','The Help field contains a hint, comment or help about the use of this item.','Y','Y','Y','N','N','N','N','N','Comment/Help',TO_DATE('2008-09-06 20:14:15','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:14:21 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56356 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2008 8:14:24 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56331,56357,0,53178,TO_DATE('2008-09-06 20:14:21','YYYY-MM-DD HH24:MI:SS'),100,'Optional short description of the record',255,'D','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','N','Description',TO_DATE('2008-09-06 20:14:21','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:14:24 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56357 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2008 8:14:25 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56341,56358,0,53178,TO_DATE('2008-09-06 20:14:24','YYYY-MM-DD HH24:MI:SS'),100,1,'D','Y','Y','Y','N','N','N','N','N','Expor tXML Backup',TO_DATE('2008-09-06 20:14:24','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:14:25 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56358 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2008 8:14:30 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56334,56359,0,53178,TO_DATE('2008-09-06 20:14:25','YYYY-MM-DD HH24:MI:SS'),100,22,'D','Y','Y','N','N','N','N','N','N','House Keeping Configuration',TO_DATE('2008-09-06 20:14:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:14:30 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56359 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2008 8:14:30 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56339,56360,0,53178,TO_DATE('2008-09-06 20:14:30','YYYY-MM-DD HH24:MI:SS'),100,10,'D','Y','Y','Y','N','N','N','N','N','Last Deleted',TO_DATE('2008-09-06 20:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:14:30 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56360 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2008 8:14:34 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56343,56361,0,53178,TO_DATE('2008-09-06 20:14:30','YYYY-MM-DD HH24:MI:SS'),100,10,'D','Y','Y','Y','N','N','N','N','N','Last Run',TO_DATE('2008-09-06 20:14:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:14:34 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56361 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2008 8:14:42 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56335,56362,0,53178,TO_DATE('2008-09-06 20:14:34','YYYY-MM-DD HH24:MI:SS'),100,'Alphanumeric identifier of the entity',60,'D','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','N','Name',TO_DATE('2008-09-06 20:14:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:14:42 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56362 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2008 8:14:45 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56328,56363,0,53178,TO_DATE('2008-09-06 20:14:42','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client',22,'D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','N','N','Organization',TO_DATE('2008-09-06 20:14:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:14:46 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56363 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2008 8:14:52 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56344,56364,0,53178,TO_DATE('2008-09-06 20:14:46','YYYY-MM-DD HH24:MI:SS'),100,1,'D','Y','Y','Y','N','N','N','N','N','Process Now',TO_DATE('2008-09-06 20:14:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:14:52 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56364 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2008 8:14:55 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56342,56365,0,53178,TO_DATE('2008-09-06 20:14:52','YYYY-MM-DD HH24:MI:SS'),100,1,'D','Y','Y','Y','N','N','N','N','N','Save In Historic',TO_DATE('2008-09-06 20:14:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:14:55 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56365 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2008 8:14:58 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56338,56366,0,53178,TO_DATE('2008-09-06 20:14:55','YYYY-MM-DD HH24:MI:SS'),100,'Search key for the record in the format required - must be unique',40,'D','A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','N','N','Search Key',TO_DATE('2008-09-06 20:14:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:14:58 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56366 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2008 8:15:00 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56345,56367,0,53178,TO_DATE('2008-09-06 20:14:58','YYYY-MM-DD HH24:MI:SS'),100,'Fully qualified SQL WHERE clause',255,'D','The Where Clause indicates the SQL WHERE clause to use for record selection. The WHERE clause is added to the query. Fully qualified means "tablename.columnname".','Y','Y','Y','N','N','N','N','N','Sql WHERE',TO_DATE('2008-09-06 20:14:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:15:00 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56367 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2008 8:15:01 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56346,56368,0,53178,TO_DATE('2008-09-06 20:15:01','YYYY-MM-DD HH24:MI:SS'),100,'Database Table information',10,'D','The Database Table provides the information of the table definition','Y','Y','Y','N','N','N','N','N','Table',TO_DATE('2008-09-06 20:15:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:15:01 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56368 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 6, 2008 8:16:54 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=56355
;

-- Sep 6, 2008 8:16:54 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=56363
;

-- Sep 6, 2008 8:16:54 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=56366
;

-- Sep 6, 2008 8:16:54 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=56362
;

-- Sep 6, 2008 8:16:54 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=56357
;

-- Sep 6, 2008 8:16:54 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=56356
;

-- Sep 6, 2008 8:16:54 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=56368
;

-- Sep 6, 2008 8:16:54 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=56367
;

-- Sep 6, 2008 8:16:54 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=56365
;

-- Sep 6, 2008 8:16:54 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=56358
;

-- Sep 6, 2008 8:16:54 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=56354
;

-- Sep 6, 2008 8:16:54 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=56364
;

-- Sep 6, 2008 8:16:54 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=56361
;

-- Sep 6, 2008 8:16:54 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=56360
;

-- Sep 6, 2008 8:16:54 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=56353
;

-- Sep 6, 2008 8:17:27 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Column SET Name='Expor XML Backup',Updated=TO_DATE('2008-09-06 20:17:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56341
;

-- Sep 6, 2008 8:17:27 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=56341
;

-- Sep 6, 2008 8:17:27 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Field SET Name='Expor XML Backup', Description=NULL, Help=NULL WHERE AD_Column_ID=56341 AND IsCentrallyMaintained='Y'
;

-- Sep 6, 2008 8:18:19 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Element SET Name='Export XML Backup',Updated=TO_DATE('2008-09-06 20:18:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53683
;

-- Sep 6, 2008 8:18:19 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53683
;

-- Sep 6, 2008 8:18:19 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Column SET ColumnName='IsExportXMLBackup', Name='Export XML Backup', Description=NULL, Help=NULL WHERE AD_Element_ID=53683
;

-- Sep 6, 2008 8:18:19 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Process_Para SET ColumnName='IsExportXMLBackup', Name='Export XML Backup', Description=NULL, Help=NULL, AD_Element_ID=53683 WHERE UPPER(ColumnName)='ISEXPORTXMLBACKUP' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Sep 6, 2008 8:18:19 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Process_Para SET ColumnName='IsExportXMLBackup', Name='Export XML Backup', Description=NULL, Help=NULL WHERE AD_Element_ID=53683 AND IsCentrallyMaintained='Y'
;

-- Sep 6, 2008 8:18:20 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Field SET Name='Export XML Backup', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53683) AND IsCentrallyMaintained='Y'
;

-- Sep 6, 2008 8:18:20 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_PrintFormatItem pi SET PrintName='Export XML Backup', Name='Export XML Backup' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53683)
;

-- Sep 6, 2008 8:18:53 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2008-09-06 20:18:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56363
;

-- Sep 6, 2008 8:19:20 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Field SET DisplayLength=20,Updated=TO_DATE('2008-09-06 20:19:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56366
;

-- Sep 6, 2008 8:21:45 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Field SET DisplayLogic='@IsExportXMLBackup@=Y',Updated=TO_DATE('2008-09-06 20:21:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56354
;

-- Sep 6, 2008 8:22:02 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2008-09-06 20:22:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56360
;

-- Sep 6, 2008 8:23:21 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Menu (Action,AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES ('W',0,53191,0,53063,TO_DATE('2008-09-06 20:23:20','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','N','N','House Keeping',TO_DATE('2008-09-06 20:23:20','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:23:21 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53191 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Sep 6, 2008 8:23:21 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', SysDate, 0, SysDate, 0,t.AD_Tree_ID, 53191, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53191)
;

-- Sep 6, 2008 8:23:52 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=218
;

-- Sep 6, 2008 8:23:52 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=153
;

-- Sep 6, 2008 8:23:52 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=263
;

-- Sep 6, 2008 8:23:52 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=166
;

-- Sep 6, 2008 8:23:52 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=203
;

-- Sep 6, 2008 8:23:52 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=236
;

-- Sep 6, 2008 8:23:52 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=183
;

-- Sep 6, 2008 8:23:52 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=160
;

-- Sep 6, 2008 8:23:52 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=278
;

-- Sep 6, 2008 8:23:52 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=345
;

-- Sep 6, 2008 8:23:52 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53014
;

-- Sep 6, 2008 8:23:52 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53108
;

-- Sep 6, 2008 8:23:52 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=456, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=450
;

-- Sep 6, 2008 8:23:52 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=456, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=200
;

-- Sep 6, 2008 8:23:53 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=456, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53191
;

-- Sep 6, 2008 8:23:53 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=456, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=439
;

-- Sep 6, 2008 8:23:53 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=456, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=440
;

-- Sep 6, 2008 8:23:53 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=456, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=594
;

-- Sep 6, 2008 8:23:53 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=456, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=50009
;

-- Sep 6, 2008 8:25:39 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Process (AccessLevel,AD_Client_ID,AD_Org_ID,AD_Process_ID,Classname,Created,CreatedBy,EntityType,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,IsServerProcess,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value) VALUES ('7',0,0,53154,'org.adempiere.process.HouseKeeping',TO_DATE('2008-09-06 20:25:38','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','N','N','N','HouseKeeping','Y',0,0,TO_DATE('2008-09-06 20:25:38','YYYY-MM-DD HH24:MI:SS'),100,'10000000')
;

-- Sep 6, 2008 8:25:39 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53154 AND EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_ID!=t.AD_Process_ID)
;

-- Sep 6, 2008 8:25:41 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53154,50002,TO_DATE('2008-09-06 20:25:41','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-09-06 20:25:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:25:41 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53154,0,TO_DATE('2008-09-06 20:25:41','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-09-06 20:25:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:25:41 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53154,102,TO_DATE('2008-09-06 20:25:41','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-09-06 20:25:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:25:41 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53154,103,TO_DATE('2008-09-06 20:25:41','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-09-06 20:25:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:25:41 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53154,50001,TO_DATE('2008-09-06 20:25:41','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-09-06 20:25:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:26:29 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,53680,0,53154,53257,19,'AD_HouseKeeping_ID',TO_DATE('2008-09-06 20:26:28','YYYY-MM-DD HH24:MI:SS'),100,'D',0,'Y','Y','N','N','AD_HouseKeeping_ID',10,TO_DATE('2008-09-06 20:26:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:26:29 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53257 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Sep 6, 2008 8:26:57 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Process SET AccessLevel='4',Updated=TO_DATE('2008-09-06 20:26:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_ID=53154
;

-- Sep 6, 2008 8:27:38 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Process (AccessLevel,AD_Client_ID,AD_Org_ID,AD_Process_ID,Created,CreatedBy,EntityType,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,IsServerProcess,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value) VALUES ('4',0,0,53155,TO_DATE('2008-09-06 20:27:38','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','N','N','N','House Keeping','Y',0,0,TO_DATE('2008-09-06 20:27:38','YYYY-MM-DD HH24:MI:SS'),100,'10000001')
;

-- Sep 6, 2008 8:27:38 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53155 AND EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_ID!=t.AD_Process_ID)
;

-- Sep 6, 2008 8:27:39 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53155,50002,TO_DATE('2008-09-06 20:27:39','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-09-06 20:27:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:27:39 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53155,0,TO_DATE('2008-09-06 20:27:39','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-09-06 20:27:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:27:39 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53155,102,TO_DATE('2008-09-06 20:27:39','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-09-06 20:27:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:27:39 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53155,103,TO_DATE('2008-09-06 20:27:39','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-09-06 20:27:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:27:39 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53155,50001,TO_DATE('2008-09-06 20:27:39','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-09-06 20:27:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:27:47 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Process SET Classname='org.adempiere.process.HouseKeeping',Updated=TO_DATE('2008-09-06 20:27:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_ID=53155
;

-- Sep 6, 2008 8:29:26 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Menu (Action,AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Process_ID,Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES ('P',0,53192,0,53154,TO_DATE('2008-09-06 20:29:25','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','N','N','House Keeping',TO_DATE('2008-09-06 20:29:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 6, 2008 8:29:26 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53192 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Sep 6, 2008 8:29:26 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', SysDate, 0, SysDate, 0,t.AD_Tree_ID, 53192, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53192)
;

-- Sep 6, 2008 8:29:37 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=218
;

-- Sep 6, 2008 8:29:37 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=153
;

-- Sep 6, 2008 8:29:37 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=263
;

-- Sep 6, 2008 8:29:37 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=166
;

-- Sep 6, 2008 8:29:37 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=203
;

-- Sep 6, 2008 8:29:37 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=236
;

-- Sep 6, 2008 8:29:37 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=183
;

-- Sep 6, 2008 8:29:37 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=160
;

-- Sep 6, 2008 8:29:37 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=278
;

-- Sep 6, 2008 8:29:37 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=345
;

-- Sep 6, 2008 8:29:37 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53014
;

-- Sep 6, 2008 8:29:37 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53108
;

-- Sep 6, 2008 8:29:37 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=456, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=450
;

-- Sep 6, 2008 8:29:37 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=456, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=200
;

-- Sep 6, 2008 8:29:37 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=456, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53191
;

-- Sep 6, 2008 8:29:37 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=456, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=439
;

-- Sep 6, 2008 8:29:37 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=456, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53192
;

-- Sep 6, 2008 8:29:37 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=456, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=440
;

-- Sep 6, 2008 8:29:37 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=456, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=594
;

-- Sep 6, 2008 8:29:37 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=456, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=50009
;

-- Sep 6, 2008 8:33:06 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Column SET AD_Process_ID=53155,Updated=TO_DATE('2008-09-06 20:33:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56344
;

-- Sep 6, 2008 8:38:16 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Process SET Value='HouseKeeping',Updated=TO_DATE('2008-09-06 20:38:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_ID=53154
;

-- Sep 6, 2008 8:38:27 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Process SET Value='HouseKeepingPara',Updated=TO_DATE('2008-09-06 20:38:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_ID=53154
;

-- Sep 6, 2008 8:38:40 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Process SET Value='HouseKeeping',Updated=TO_DATE('2008-09-06 20:38:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_ID=53155
;

-- Sep 6, 2008 8:39:06 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Process_Para SET IsMandatory='Y',Updated=TO_DATE('2008-09-06 20:39:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53257
;

-- Sep 6, 2008 8:39:14 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=456, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=450
;

-- Sep 6, 2008 8:39:15 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=456, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=200
;

-- Sep 6, 2008 8:39:15 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=456, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53191
;

-- Sep 6, 2008 8:39:15 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=456, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=439
;

-- Sep 6, 2008 8:39:15 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=456, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53192
;

-- Sep 6, 2008 8:39:15 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=456, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=440
;

-- Sep 6, 2008 8:39:15 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=456, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=594
;

-- Sep 6, 2008 8:39:15 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=456, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=50009
;

-- Sep 6, 2008 8:39:16 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=456, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=450
;

-- Sep 6, 2008 8:39:16 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=456, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=200
;

-- Sep 6, 2008 8:39:16 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=456, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53191
;

-- Sep 6, 2008 8:39:16 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=456, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53192
;

-- Sep 6, 2008 8:39:16 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=456, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=439
;

-- Sep 6, 2008 8:39:17 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=456, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=440
;

-- Sep 6, 2008 8:39:17 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=456, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=594
;

-- Sep 6, 2008 8:39:17 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_TreeNodeMM SET Parent_ID=456, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=50009
;

-- Sep 6, 2008 8:39:57 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Field SET EntityType='D',Updated=TO_DATE('2008-09-06 20:39:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56355
;

-- Sep 6, 2008 8:40:02 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Field SET EntityType='D',Updated=TO_DATE('2008-09-06 20:40:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56363
;

-- Sep 6, 2008 8:40:05 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Field SET EntityType='D',Updated=TO_DATE('2008-09-06 20:40:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56366
;

-- Sep 6, 2008 8:40:07 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Field SET EntityType='D',Updated=TO_DATE('2008-09-06 20:40:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56362
;

-- Sep 6, 2008 8:40:10 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Field SET EntityType='D',Updated=TO_DATE('2008-09-06 20:40:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56357
;

-- Sep 6, 2008 8:40:23 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Field SET EntityType='D',Updated=TO_DATE('2008-09-06 20:40:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56356
;

-- Sep 6, 2008 8:40:26 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Field SET EntityType='D',Updated=TO_DATE('2008-09-06 20:40:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56368
;

-- Sep 6, 2008 8:40:28 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Field SET EntityType='D',Updated=TO_DATE('2008-09-06 20:40:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56367
;

-- Sep 6, 2008 8:40:30 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Field SET EntityType='D',Updated=TO_DATE('2008-09-06 20:40:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56365
;

-- Sep 6, 2008 8:40:32 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Field SET EntityType='D',Updated=TO_DATE('2008-09-06 20:40:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56358
;

-- Sep 6, 2008 8:40:34 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Field SET EntityType='D',Updated=TO_DATE('2008-09-06 20:40:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56354
;

-- Sep 6, 2008 8:40:36 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Field SET EntityType='D',Updated=TO_DATE('2008-09-06 20:40:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56364
;

-- Sep 6, 2008 8:40:39 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Field SET EntityType='D',Updated=TO_DATE('2008-09-06 20:40:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56361
;

-- Sep 6, 2008 8:40:42 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Field SET EntityType='D',Updated=TO_DATE('2008-09-06 20:40:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56360
;

-- Sep 6, 2008 8:40:45 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Field SET EntityType='D',Updated=TO_DATE('2008-09-06 20:40:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56353
;

-- Sep 6, 2008 8:41:47 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2008-09-06 20:41:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56327
;

-- Sep 6, 2008 8:41:59 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Column SET EntityType='D', IsUpdateable='N',Updated=TO_DATE('2008-09-06 20:41:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56334
;

-- Sep 6, 2008 8:42:03 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2008-09-06 20:42:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56328
;

-- Sep 6, 2008 8:42:12 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2008-09-06 20:42:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56346
;

-- Sep 6, 2008 8:42:16 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2008-09-06 20:42:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56340
;

-- Sep 6, 2008 8:42:19 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2008-09-06 20:42:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56329
;

-- Sep 6, 2008 8:42:24 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2008-09-06 20:42:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56330
;

-- Sep 6, 2008 8:42:27 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2008-09-06 20:42:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56331
;

-- Sep 6, 2008 8:42:30 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2008-09-06 20:42:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56332
;

-- Sep 6, 2008 8:42:33 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2008-09-06 20:42:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56333
;

-- Sep 6, 2008 8:42:46 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2008-09-06 20:42:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56341
;

-- Sep 6, 2008 8:42:49 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2008-09-06 20:42:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56342
;

-- Sep 6, 2008 8:42:52 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2008-09-06 20:42:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56339
;

-- Sep 6, 2008 8:42:57 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2008-09-06 20:42:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56343
;

-- Sep 6, 2008 8:43:00 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2008-09-06 20:43:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56335
;

-- Sep 6, 2008 8:43:04 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2008-09-06 20:43:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56344
;

-- Sep 6, 2008 8:43:09 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2008-09-06 20:43:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56336
;

-- Sep 6, 2008 8:43:11 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2008-09-06 20:43:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56337
;

-- Sep 6, 2008 8:43:15 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2008-09-06 20:43:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56338
;

-- Sep 6, 2008 8:43:18 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2008-09-06 20:43:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56345
;

-- Sep 6, 2008 8:43:27 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2008-09-06 20:43:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56360
;

-- Sep 6, 2008 8:43:30 PM COT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2008-09-06 20:43:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56361
;

