-- Apr 22, 2009 7:57:47 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,CopyColumnsFromTable,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,LoadSeq,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53196,'3','N',TO_TIMESTAMP('2009-04-22 19:57:46','YYYY-MM-DD HH24:MI:SS'),0,'EE01','N','Y','N','N','N','N','Y',0,'Manufacturing Order Head *** Templete ***','L','PP_Order_Head_V',TO_TIMESTAMP('2009-04-22 19:57:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 22, 2009 7:57:47 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53196 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Apr 22, 2009 7:57:48 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53290,TO_TIMESTAMP('2009-04-22 19:57:47','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table PP_Order_Head_V',1,'Y','N','Y','Y','PP_Order_Head_V','N',1000000,TO_TIMESTAMP('2009-04-22 19:57:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 22, 2009 7:58:26 PM ECT
-- View Manufacturing Management
UPDATE AD_Table SET IsView='N',Updated=TO_TIMESTAMP('2009-04-22 19:58:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=53196
;

-- Apr 22, 2009 7:59:00 PM ECT
-- View Manufacturing Management
UPDATE AD_Table SET TableName='PP_ORDER_HEADER_V',Updated=TO_TIMESTAMP('2009-04-22 19:59:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=53196
;

-- Apr 22, 2009 7:59:01 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53291,TO_TIMESTAMP('2009-04-22 19:59:00','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table PP_ORDER_HEADER_V',1,'Y','N','Y','Y','PP_ORDER_HEADER_V','N',1000000,TO_TIMESTAMP('2009-04-22 19:59:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 22, 2009 7:59:08 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57271,102,0,19,53196,'AD_Client_ID',TO_TIMESTAMP('2009-04-22 19:59:07','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','EE01',10,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','N','N','N','N','N','Client',TO_TIMESTAMP('2009-04-22 19:59:07','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:08 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57271 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:09 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57272,113,0,19,53196,'AD_Org_ID',TO_TIMESTAMP('2009-04-22 19:59:08','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','EE01',10,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','N','N','N','N','N','Organization',TO_TIMESTAMP('2009-04-22 19:59:08','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:09 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57272 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:10 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57273,348,0,20,53196,'IsActive',TO_TIMESTAMP('2009-04-22 19:59:09','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','EE01',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','N','N','N','N','Y','Active',TO_TIMESTAMP('2009-04-22 19:59:09','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:10 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57273 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:10 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57274,245,0,16,53196,'Created',TO_TIMESTAMP('2009-04-22 19:59:10','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE01',29,'The Created field indicates the date that this record was created.','Y','N','N','N','N','N','N','N','N','N','Created',TO_TIMESTAMP('2009-04-22 19:59:10','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:10 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57274 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:11 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57275,246,0,18,110,53196,'CreatedBy',TO_TIMESTAMP('2009-04-22 19:59:10','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE01',10,'The Created By field indicates the user who created this record.','Y','N','N','N','N','N','N','N','N','N','Created By',TO_TIMESTAMP('2009-04-22 19:59:10','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:11 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57275 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:11 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57276,607,0,16,53196,'Updated',TO_TIMESTAMP('2009-04-22 19:59:11','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE01',29,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','N','N','N','N','N','Updated',TO_TIMESTAMP('2009-04-22 19:59:11','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:11 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57276 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:12 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57277,608,0,18,110,53196,'UpdatedBy',TO_TIMESTAMP('2009-04-22 19:59:11','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE01',10,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','N','N','N','N','N','Updated By',TO_TIMESTAMP('2009-04-22 19:59:11','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:12 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57277 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:13 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57278,109,0,14,53196,'AD_Language',TO_TIMESTAMP('2009-04-22 19:59:12','YYYY-MM-DD HH24:MI:SS'),0,'Language for this entity','EE01',2147483647,'The Language identifies the language to use for display and formatting','Y','N','N','N','N','N','N','N','N','Y','Language',TO_TIMESTAMP('2009-04-22 19:59:12','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:13 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57278 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:14 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57279,53276,0,19,53196,'PP_Order_ID',TO_TIMESTAMP('2009-04-22 19:59:13','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','N','N','N','N','N','Y','Manufacturing Order',TO_TIMESTAMP('2009-04-22 19:59:13','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:14 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57279 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:14 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57280,290,0,10,53196,'DocumentNo',TO_TIMESTAMP('2009-04-22 19:59:14','YYYY-MM-DD HH24:MI:SS'),0,'Document sequence number of the document','EE01',60,'The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','N','N','N','N','N','N','Y','Document No',TO_TIMESTAMP('2009-04-22 19:59:14','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:14 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57280 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:15 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57281,289,0,17,53196,'DocStatus',TO_TIMESTAMP('2009-04-22 19:59:14','YYYY-MM-DD HH24:MI:SS'),0,'The current status of the document','EE01',2,'The Document Status indicates the status of a document at this time.  If you want to change the document status, use the Document Action field','Y','N','N','N','N','N','N','N','N','Y','Document Status',TO_TIMESTAMP('2009-04-22 19:59:14','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:15 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57281 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:16 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57282,196,0,19,53196,'C_DocType_ID',TO_TIMESTAMP('2009-04-22 19:59:15','YYYY-MM-DD HH24:MI:SS'),0,'Document type or rules','EE01',10,'The Document Type determines document sequence and processing rules','Y','N','N','N','N','N','N','N','N','Y','Document Type',TO_TIMESTAMP('2009-04-22 19:59:15','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:16 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57282 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:16 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57283,1874,0,19,53196,'Org_Location_ID',TO_TIMESTAMP('2009-04-22 19:59:16','YYYY-MM-DD HH24:MI:SS'),0,'Organization Location/Address','EE01',10,'Y','N','N','N','N','N','N','N','N','Y','Org Address',TO_TIMESTAMP('2009-04-22 19:59:16','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:16 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57283 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:17 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57284,590,0,10,53196,'TaxID',TO_TIMESTAMP('2009-04-22 19:59:16','YYYY-MM-DD HH24:MI:SS'),0,'Tax Identification','EE01',20,'The Tax ID field identifies the legal Identification number of this Entity.','Y','N','N','N','N','N','N','N','N','Y','Tax ID',TO_TIMESTAMP('2009-04-22 19:59:16','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:17 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57284 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:18 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57285,459,0,19,53196,'M_Warehouse_ID',TO_TIMESTAMP('2009-04-22 19:59:17','YYYY-MM-DD HH24:MI:SS'),0,'Storage Warehouse and Service Point','EE01',10,'The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','N','N','N','N','N','N','N','N','Y','Warehouse',TO_TIMESTAMP('2009-04-22 19:59:17','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:18 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57285 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:18 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57286,1875,0,19,53196,'Warehouse_Location_ID',TO_TIMESTAMP('2009-04-22 19:59:18','YYYY-MM-DD HH24:MI:SS'),0,'Warehouse Location/Address','EE01',10,'Address of Warehouse','Y','N','N','N','N','N','N','N','N','Y','Warehouse Address',TO_TIMESTAMP('2009-04-22 19:59:18','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:18 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57286 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:19 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57287,1841,0,10,53196,'DocumentType',TO_TIMESTAMP('2009-04-22 19:59:18','YYYY-MM-DD HH24:MI:SS'),0,'Document Type','EE01',60,'Y','N','N','N','N','N','N','N','N','Y','Document Type',TO_TIMESTAMP('2009-04-22 19:59:18','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:19 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57287 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:20 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57288,1842,0,14,53196,'DocumentTypeNote',TO_TIMESTAMP('2009-04-22 19:59:19','YYYY-MM-DD HH24:MI:SS'),0,'Optional note of a document type','EE01',2000,'Y','N','N','N','N','N','N','N','N','Y','Document Type Note',TO_TIMESTAMP('2009-04-22 19:59:19','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:20 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57288 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:20 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57289,53269,0,19,53196,'Planner_ID',TO_TIMESTAMP('2009-04-22 19:59:20','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','N','N','N','N','N','Y','Planner',TO_TIMESTAMP('2009-04-22 19:59:20','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:20 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57289 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:22 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57290,1886,0,10,53196,'SalesRep_Name',TO_TIMESTAMP('2009-04-22 19:59:20','YYYY-MM-DD HH24:MI:SS'),0,'EE01',60,'Y','N','N','N','N','N','N','N','N','Y','Sales Representative',TO_TIMESTAMP('2009-04-22 19:59:20','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:22 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57290 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:22 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57291,53280,0,16,53196,'DateStart',TO_TIMESTAMP('2009-04-22 19:59:22','YYYY-MM-DD HH24:MI:SS'),0,'EE01',29,'Y','N','N','N','N','N','N','N','N','Y','DateStart',TO_TIMESTAMP('2009-04-22 19:59:22','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:22 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57291 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:23 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57292,53281,0,16,53196,'DateStartSchedule',TO_TIMESTAMP('2009-04-22 19:59:22','YYYY-MM-DD HH24:MI:SS'),0,'EE01',29,'Y','N','N','N','N','N','N','N','N','Y','DateStartSchedule',TO_TIMESTAMP('2009-04-22 19:59:22','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:23 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57292 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:23 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57293,53300,0,22,53196,'FloatAfter',TO_TIMESTAMP('2009-04-22 19:59:23','YYYY-MM-DD HH24:MI:SS'),0,'EE01',131089,'Y','N','N','N','N','N','N','N','N','Y','Float After',TO_TIMESTAMP('2009-04-22 19:59:23','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:23 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57293 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:24 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57294,53301,0,22,53196,'FloatBefored',TO_TIMESTAMP('2009-04-22 19:59:23','YYYY-MM-DD HH24:MI:SS'),0,'EE01',131089,'Y','N','N','N','N','N','N','N','N','Y','Float Befored',TO_TIMESTAMP('2009-04-22 19:59:23','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:24 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57294 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:25 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57295,439,0,11,53196,'Line',TO_TIMESTAMP('2009-04-22 19:59:24','YYYY-MM-DD HH24:MI:SS'),0,'Unique line for this document','EE01',10,'Indicates the unique line for a document.  It will also control the display order of the lines within a document.','Y','N','N','N','N','N','N','N','N','Y','Line No',TO_TIMESTAMP('2009-04-22 19:59:24','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:25 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57295 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:25 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57296,446,0,10,53196,'Lot',TO_TIMESTAMP('2009-04-22 19:59:25','YYYY-MM-DD HH24:MI:SS'),0,'Lot number (alphanumeric)','EE01',20,'The Lot Number indicates the specific lot that a product was part of.','Y','N','N','N','N','N','N','N','N','Y','Lot No',TO_TIMESTAMP('2009-04-22 19:59:25','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:25 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57296 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:26 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57297,568,0,10,53196,'SerNo',TO_TIMESTAMP('2009-04-22 19:59:25','YYYY-MM-DD HH24:MI:SS'),0,'Product Serial Number ','EE01',20,'The Serial Number identifies a tracked, warranted product.  It can only be used when the quantity is 1.','Y','N','N','N','N','N','N','N','N','Y','Serial No',TO_TIMESTAMP('2009-04-22 19:59:25','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:26 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57297 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:26 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57298,275,0,14,53196,'Description',TO_TIMESTAMP('2009-04-22 19:59:26','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE01',510,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','N','Y','Description',TO_TIMESTAMP('2009-04-22 19:59:26','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:27 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57298 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:28 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57299,454,0,19,53196,'M_Product_ID',TO_TIMESTAMP('2009-04-22 19:59:27','YYYY-MM-DD HH24:MI:SS'),0,'Product, Service, Item','EE01',10,'Identifies an item which is either purchased or sold in this organization.','Y','N','N','N','N','N','N','N','N','Y','Product',TO_TIMESTAMP('2009-04-22 19:59:27','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:28 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57299 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:28 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57300,2019,0,35,53196,'M_AttributeSetInstance_ID',TO_TIMESTAMP('2009-04-22 19:59:28','YYYY-MM-DD HH24:MI:SS'),0,'Product Attribute Set Instance','EE01',10,'The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.','Y','N','N','N','N','N','N','N','N','Y','Attribute Set Instance',TO_TIMESTAMP('2009-04-22 19:59:28','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:29 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57300 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:29 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57301,215,0,19,53196,'C_UOM_ID',TO_TIMESTAMP('2009-04-22 19:59:29','YYYY-MM-DD HH24:MI:SS'),0,'Unit of Measure','EE01',10,'The UOM defines a unique non monetary Unit of Measure','Y','N','N','N','N','N','N','N','N','Y','UOM',TO_TIMESTAMP('2009-04-22 19:59:29','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:29 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57301 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:30 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57302,1777,0,19,53196,'S_Resource_ID',TO_TIMESTAMP('2009-04-22 19:59:29','YYYY-MM-DD HH24:MI:SS'),0,'Resource','EE01',10,'Y','N','N','N','N','N','N','N','N','Y','Resource',TO_TIMESTAMP('2009-04-22 19:59:29','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:30 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57302 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:30 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57303,53245,0,19,53196,'PP_Product_BOM_ID',TO_TIMESTAMP('2009-04-22 19:59:30','YYYY-MM-DD HH24:MI:SS'),0,'BOM & Formula','EE01',10,'Y','N','N','N','N','N','N','N','N','Y','BOM & Formula',TO_TIMESTAMP('2009-04-22 19:59:30','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:30 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57303 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:30 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57304,144,0,19,53196,'AD_Workflow_ID',TO_TIMESTAMP('2009-04-22 19:59:30','YYYY-MM-DD HH24:MI:SS'),0,'Workflow or combination of tasks','EE01',10,'The Workflow field identifies a unique Workflow in the system.','Y','N','N','N','N','N','N','N','N','Y','Workflow',TO_TIMESTAMP('2009-04-22 19:59:30','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:31 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57304 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:31 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57305,53247,0,22,53196,'Assay',TO_TIMESTAMP('2009-04-22 19:59:31','YYYY-MM-DD HH24:MI:SS'),0,'Indicated the Quantity Assay to use into Quality Order','EE01',131089,'Indicated the Quantity Assay to use into Quality Order','Y','N','N','N','N','N','N','N','N','Y','Quantity Assay',TO_TIMESTAMP('2009-04-22 19:59:31','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:31 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57305 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:32 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57306,561,0,19,53196,'C_OrderLine_ID',TO_TIMESTAMP('2009-04-22 19:59:31','YYYY-MM-DD HH24:MI:SS'),0,'Sales Order Line','EE01',10,'The Sales Order Line is a unique identifier for a line in an order.','Y','N','N','N','N','N','N','N','N','Y','Sales Order Line',TO_TIMESTAMP('2009-04-22 19:59:31','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:32 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57306 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:33 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57307,522,0,20,53196,'PriorityRule',TO_TIMESTAMP('2009-04-22 19:59:32','YYYY-MM-DD HH24:MI:SS'),0,'Priority of a document','EE01',1,'The Priority indicates the importance (high, medium, low) of this document','Y','N','N','N','N','N','N','N','N','Y','Priority',TO_TIMESTAMP('2009-04-22 19:59:32','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:33 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57307 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:33 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57308,53243,0,29,53196,'QtyBatchSize',TO_TIMESTAMP('2009-04-22 19:59:33','YYYY-MM-DD HH24:MI:SS'),0,'EE01',131089,'Y','N','N','N','N','N','N','N','N','Y','Qty Batch Size',TO_TIMESTAMP('2009-04-22 19:59:33','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:33 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57308 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:34 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57309,53302,0,29,53196,'QtyBatchs',TO_TIMESTAMP('2009-04-22 19:59:33','YYYY-MM-DD HH24:MI:SS'),0,'EE01',131089,'Y','N','N','N','N','N','N','N','N','Y','Qty Batchs',TO_TIMESTAMP('2009-04-22 19:59:33','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:34 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57309 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:34 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57310,528,0,29,53196,'QtyDelivered',TO_TIMESTAMP('2009-04-22 19:59:34','YYYY-MM-DD HH24:MI:SS'),0,'Delivered Quantity','EE01',131089,'The Delivered Quantity indicates the quantity of a product that has been delivered.','Y','N','N','N','N','N','N','N','N','Y','Delivered Quantity',TO_TIMESTAMP('2009-04-22 19:59:34','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:34 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57310 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:35 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57311,2589,0,29,53196,'QtyEntered',TO_TIMESTAMP('2009-04-22 19:59:34','YYYY-MM-DD HH24:MI:SS'),0,'The Quantity Entered is based on the selected UoM','EE01',131089,'The Quantity Entered is converted to base product UoM quantity','Y','N','N','N','N','N','N','N','N','Y','Quantity',TO_TIMESTAMP('2009-04-22 19:59:34','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:35 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57311 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:35 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57312,531,0,29,53196,'QtyOrdered',TO_TIMESTAMP('2009-04-22 19:59:35','YYYY-MM-DD HH24:MI:SS'),0,'Ordered Quantity','EE01',131089,'The Ordered Quantity indicates the quantity of a product that was ordered.','Y','N','N','N','N','N','N','N','N','Y','Ordered Quantity',TO_TIMESTAMP('2009-04-22 19:59:35','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:35 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57312 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:36 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57313,53277,0,16,53196,'DateConfirm',TO_TIMESTAMP('2009-04-22 19:59:35','YYYY-MM-DD HH24:MI:SS'),0,'EE01',29,'Y','N','N','N','N','N','N','N','N','Y','DateConfirm',TO_TIMESTAMP('2009-04-22 19:59:35','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:36 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57313 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:37 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57314,264,0,16,53196,'DateDelivered',TO_TIMESTAMP('2009-04-22 19:59:36','YYYY-MM-DD HH24:MI:SS'),0,'Date when the product was delivered','EE01',29,'Y','N','N','N','N','N','N','N','N','Y','Date Delivered',TO_TIMESTAMP('2009-04-22 19:59:36','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:37 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57314 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:37 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57315,1557,0,16,53196,'DateFinish',TO_TIMESTAMP('2009-04-22 19:59:37','YYYY-MM-DD HH24:MI:SS'),0,'Finish or (planned) completion date','EE01',29,'The finish date is used to indicate when the project is expected to be completed or has been completed.','Y','N','N','N','N','N','N','N','N','Y','Finish Date',TO_TIMESTAMP('2009-04-22 19:59:37','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:37 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57315 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:38 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57316,53278,0,16,53196,'DateFinishSchedule',TO_TIMESTAMP('2009-04-22 19:59:37','YYYY-MM-DD HH24:MI:SS'),0,'EE01',29,'Y','N','N','N','N','N','N','N','N','Y','DateFinishSchedule',TO_TIMESTAMP('2009-04-22 19:59:37','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:38 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57316 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:38 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57317,268,0,16,53196,'DateOrdered',TO_TIMESTAMP('2009-04-22 19:59:38','YYYY-MM-DD HH24:MI:SS'),0,'Date of Order','EE01',29,'Indicates the Date an item was ordered.','Y','N','N','N','N','N','N','N','N','Y','Date Ordered',TO_TIMESTAMP('2009-04-22 19:59:38','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:38 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57317 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:39 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57318,269,0,16,53196,'DatePromised',TO_TIMESTAMP('2009-04-22 19:59:38','YYYY-MM-DD HH24:MI:SS'),0,'Date Order was promised','EE01',29,'The Date Promised indicates the date, if any, that an Order was promised for.','Y','N','N','N','N','N','N','N','N','Y','Date Promised',TO_TIMESTAMP('2009-04-22 19:59:38','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:39 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57318 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:40 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57319,53287,0,29,53196,'QtyReject',TO_TIMESTAMP('2009-04-22 19:59:39','YYYY-MM-DD HH24:MI:SS'),0,'EE01',131089,'Y','N','N','N','N','N','N','N','N','Y','Qty Reject',TO_TIMESTAMP('2009-04-22 19:59:39','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:40 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57319 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:40 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57320,532,0,29,53196,'QtyReserved',TO_TIMESTAMP('2009-04-22 19:59:40','YYYY-MM-DD HH24:MI:SS'),0,'Reserved Quantity','EE01',131089,'The Reserved Quantity indicates the quantity of a product that is currently reserved.','Y','N','N','N','N','N','N','N','N','Y','Reserved Quantity',TO_TIMESTAMP('2009-04-22 19:59:40','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:40 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57320 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:41 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57321,53289,0,29,53196,'QtyScrap',TO_TIMESTAMP('2009-04-22 19:59:40','YYYY-MM-DD HH24:MI:SS'),0,'Scrap Quantity for this componet','EE01',131089,'Scrap Quantity for this componet','Y','N','N','N','N','N','N','N','N','Y','QtyScrap',TO_TIMESTAMP('2009-04-22 19:59:40','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:41 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57321 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:41 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57322,53272,0,22,53196,'Yield',TO_TIMESTAMP('2009-04-22 19:59:41','YYYY-MM-DD HH24:MI:SS'),0,'The Yield is the percentage of a lot that is expected to be of acceptable wuality may fall below 100 percent','EE01',131089,'ADempiere Calculate the total yield for a product from the yield for each activity when the process Workflow Cost Roll-Up is executed.

The expected yield for an Activity can be expressed as:

Yield = Acceptable Units at Activity End x 100

The Total manufacturing yield for a product is determined by multiplying the yied percentage for each activity.

Manufacturing Yield = Yield % for Activity 10 x Yied % for Activity 20 , etc 

Take care when setting yield to anything but 100% particularly when yied is used for multiples activities

','Y','N','N','N','N','N','N','N','N','Y','Yield %',TO_TIMESTAMP('2009-04-22 19:59:41','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:41 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57322 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:42 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57323,550,0,19,53196,'C_Campaign_ID',TO_TIMESTAMP('2009-04-22 19:59:41','YYYY-MM-DD HH24:MI:SS'),0,'Marketing Campaign','EE01',10,'The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','N','N','N','N','N','N','N','N','Y','Campaign',TO_TIMESTAMP('2009-04-22 19:59:41','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:42 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57323 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:43 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57324,208,0,19,53196,'C_Project_ID',TO_TIMESTAMP('2009-04-22 19:59:42','YYYY-MM-DD HH24:MI:SS'),0,'Financial Project','EE01',10,'A Project allows you to track and control internal or external activities.','Y','N','N','N','N','N','N','N','N','Y','Project',TO_TIMESTAMP('2009-04-22 19:59:42','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:43 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57324 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 7:59:43 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57325,1005,0,19,53196,'C_Activity_ID',TO_TIMESTAMP('2009-04-22 19:59:43','YYYY-MM-DD HH24:MI:SS'),0,'Business Activity','EE01',10,'Activities indicate tasks that are performed and used to utilize Activity based Costing','Y','N','N','N','N','N','N','N','N','Y','Activity',TO_TIMESTAMP('2009-04-22 19:59:43','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 7:59:43 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57325 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:00:49 PM ECT
-- View Manufacturing Management
UPDATE AD_Table SET TableName='PP_Order_Head_v',Updated=TO_TIMESTAMP('2009-04-22 20:00:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=53196
;

-- Apr 22, 2009 8:00:49 PM ECT
-- View Manufacturing Management
UPDATE AD_Sequence SET Name='PP_Order_Head_v',Updated=TO_TIMESTAMP('2009-04-22 20:00:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=53290
;

-- Apr 22, 2009 8:00:53 PM ECT
-- View Manufacturing Management
UPDATE AD_Table SET IsView='Y',Updated=TO_TIMESTAMP('2009-04-22 20:00:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=53196
;

-- Apr 22, 2009 8:03:10 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,CopyColumnsFromTable,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,LoadSeq,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53197,'3','N',TO_TIMESTAMP('2009-04-22 20:03:10','YYYY-MM-DD HH24:MI:SS'),0,'EE01','N','Y','N','N','N','N','Y',0,'Manufacturing Order BOM  Head *** Templete ***','L','PP_ORDER_BOM_HEADER_V',TO_TIMESTAMP('2009-04-22 20:03:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 22, 2009 8:03:10 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53197 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Apr 22, 2009 8:03:11 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53292,TO_TIMESTAMP('2009-04-22 20:03:10','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table PP_ORDER_BOM_HEADER_V',1,'Y','N','Y','Y','PP_ORDER_BOM_HEADER_V','N',1000000,TO_TIMESTAMP('2009-04-22 20:03:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 22, 2009 8:03:17 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57326,102,0,19,53197,'AD_Client_ID',TO_TIMESTAMP('2009-04-22 20:03:16','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','EE01',10,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','N','N','N','N','N','Client',TO_TIMESTAMP('2009-04-22 20:03:16','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:17 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57326 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:17 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57327,113,0,19,53197,'AD_Org_ID',TO_TIMESTAMP('2009-04-22 20:03:17','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','EE01',10,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','N','N','N','N','N','Organization',TO_TIMESTAMP('2009-04-22 20:03:17','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:17 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57327 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:18 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57328,348,0,20,53197,'IsActive',TO_TIMESTAMP('2009-04-22 20:03:17','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','EE01',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','N','N','N','N','N','Active',TO_TIMESTAMP('2009-04-22 20:03:17','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:18 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57328 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:19 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57329,245,0,16,53197,'Created',TO_TIMESTAMP('2009-04-22 20:03:18','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE01',29,'The Created field indicates the date that this record was created.','Y','N','N','N','N','N','N','N','N','N','Created',TO_TIMESTAMP('2009-04-22 20:03:18','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:19 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57329 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:20 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57330,246,0,18,110,53197,'CreatedBy',TO_TIMESTAMP('2009-04-22 20:03:19','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE01',10,'The Created By field indicates the user who created this record.','Y','N','N','N','N','N','N','N','N','N','Created By',TO_TIMESTAMP('2009-04-22 20:03:19','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:20 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57330 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:20 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57331,607,0,16,53197,'Updated',TO_TIMESTAMP('2009-04-22 20:03:20','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE01',29,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','N','N','N','N','N','Updated',TO_TIMESTAMP('2009-04-22 20:03:20','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:20 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57331 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:26 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57332,608,0,18,110,53197,'UpdatedBy',TO_TIMESTAMP('2009-04-22 20:03:20','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE01',10,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','N','N','N','N','N','Updated By',TO_TIMESTAMP('2009-04-22 20:03:20','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:26 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57332 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:26 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57333,109,0,14,53197,'AD_Language',TO_TIMESTAMP('2009-04-22 20:03:26','YYYY-MM-DD HH24:MI:SS'),0,'Language for this entity','EE01',2147483647,'The Language identifies the language to use for display and formatting','Y','N','N','N','N','N','N','N','N','N','Language',TO_TIMESTAMP('2009-04-22 20:03:26','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:26 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57333 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:27 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57334,53276,0,19,53197,'PP_Order_ID',TO_TIMESTAMP('2009-04-22 20:03:26','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','N','N','N','N','N','N','Manufacturing Order',TO_TIMESTAMP('2009-04-22 20:03:26','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:27 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57334 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:27 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57335,290,0,10,53197,'DocumentNo',TO_TIMESTAMP('2009-04-22 20:03:27','YYYY-MM-DD HH24:MI:SS'),0,'Document sequence number of the document','EE01',60,'The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','N','N','N','N','N','N','N','Document No',TO_TIMESTAMP('2009-04-22 20:03:27','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:27 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57335 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:28 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57336,289,0,17,53197,'DocStatus',TO_TIMESTAMP('2009-04-22 20:03:27','YYYY-MM-DD HH24:MI:SS'),0,'The current status of the document','EE01',2,'The Document Status indicates the status of a document at this time.  If you want to change the document status, use the Document Action field','Y','N','N','N','N','N','N','N','N','N','Document Status',TO_TIMESTAMP('2009-04-22 20:03:27','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:28 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57336 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:29 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57337,196,0,19,53197,'C_DocType_ID',TO_TIMESTAMP('2009-04-22 20:03:28','YYYY-MM-DD HH24:MI:SS'),0,'Document type or rules','EE01',10,'The Document Type determines document sequence and processing rules','Y','N','N','N','N','N','N','N','N','N','Document Type',TO_TIMESTAMP('2009-04-22 20:03:28','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:29 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57337 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:29 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57338,1874,0,19,53197,'Org_Location_ID',TO_TIMESTAMP('2009-04-22 20:03:29','YYYY-MM-DD HH24:MI:SS'),0,'Organization Location/Address','EE01',10,'Y','N','N','N','N','N','N','N','N','N','Org Address',TO_TIMESTAMP('2009-04-22 20:03:29','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:29 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57338 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:30 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57339,590,0,10,53197,'TaxID',TO_TIMESTAMP('2009-04-22 20:03:29','YYYY-MM-DD HH24:MI:SS'),0,'Tax Identification','EE01',20,'The Tax ID field identifies the legal Identification number of this Entity.','Y','N','N','N','N','N','N','N','N','N','Tax ID',TO_TIMESTAMP('2009-04-22 20:03:29','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:30 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57339 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:30 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57340,459,0,19,53197,'M_Warehouse_ID',TO_TIMESTAMP('2009-04-22 20:03:30','YYYY-MM-DD HH24:MI:SS'),0,'Storage Warehouse and Service Point','EE01',10,'The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','N','N','N','N','N','N','N','N','N','Warehouse',TO_TIMESTAMP('2009-04-22 20:03:30','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:30 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57340 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:31 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57341,1875,0,19,53197,'Warehouse_Location_ID',TO_TIMESTAMP('2009-04-22 20:03:30','YYYY-MM-DD HH24:MI:SS'),0,'Warehouse Location/Address','EE01',10,'Address of Warehouse','Y','N','N','N','N','N','N','N','N','N','Warehouse Address',TO_TIMESTAMP('2009-04-22 20:03:30','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:31 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57341 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:31 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57342,1841,0,10,53197,'DocumentType',TO_TIMESTAMP('2009-04-22 20:03:31','YYYY-MM-DD HH24:MI:SS'),0,'Document Type','EE01',60,'Y','N','N','N','N','N','N','N','N','N','Document Type',TO_TIMESTAMP('2009-04-22 20:03:31','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:31 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57342 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:32 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57343,1842,0,14,53197,'DocumentTypeNote',TO_TIMESTAMP('2009-04-22 20:03:31','YYYY-MM-DD HH24:MI:SS'),0,'Optional note of a document type','EE01',2000,'Y','N','N','N','N','N','N','N','N','N','Document Type Note',TO_TIMESTAMP('2009-04-22 20:03:31','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:32 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57343 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:32 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57344,53269,0,19,53197,'Planner_ID',TO_TIMESTAMP('2009-04-22 20:03:32','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','N','N','N','N','N','N','Planner',TO_TIMESTAMP('2009-04-22 20:03:32','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:32 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57344 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:33 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57345,1886,0,10,53197,'SalesRep_Name',TO_TIMESTAMP('2009-04-22 20:03:32','YYYY-MM-DD HH24:MI:SS'),0,'EE01',60,'Y','N','N','N','N','N','N','N','N','N','Sales Representative',TO_TIMESTAMP('2009-04-22 20:03:32','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:33 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57345 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:34 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57346,53280,0,16,53197,'DateStart',TO_TIMESTAMP('2009-04-22 20:03:33','YYYY-MM-DD HH24:MI:SS'),0,'EE01',29,'Y','N','N','N','N','N','N','N','N','N','DateStart',TO_TIMESTAMP('2009-04-22 20:03:33','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:34 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57346 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:34 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57347,53281,0,16,53197,'DateStartSchedule',TO_TIMESTAMP('2009-04-22 20:03:34','YYYY-MM-DD HH24:MI:SS'),0,'EE01',29,'Y','N','N','N','N','N','N','N','N','N','DateStartSchedule',TO_TIMESTAMP('2009-04-22 20:03:34','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:34 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57347 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:35 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57348,53300,0,22,53197,'FloatAfter',TO_TIMESTAMP('2009-04-22 20:03:34','YYYY-MM-DD HH24:MI:SS'),0,'EE01',131089,'Y','N','N','N','N','N','N','N','N','N','Float After',TO_TIMESTAMP('2009-04-22 20:03:34','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:35 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57348 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:36 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57349,53301,0,22,53197,'FloatBefored',TO_TIMESTAMP('2009-04-22 20:03:35','YYYY-MM-DD HH24:MI:SS'),0,'EE01',131089,'Y','N','N','N','N','N','N','N','N','N','Float Befored',TO_TIMESTAMP('2009-04-22 20:03:35','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:36 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57349 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:36 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57350,439,0,11,53197,'Line',TO_TIMESTAMP('2009-04-22 20:03:36','YYYY-MM-DD HH24:MI:SS'),0,'Unique line for this document','EE01',10,'Indicates the unique line for a document.  It will also control the display order of the lines within a document.','Y','N','N','N','N','N','N','N','N','N','Line No',TO_TIMESTAMP('2009-04-22 20:03:36','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:36 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57350 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:37 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57351,446,0,10,53197,'Lot',TO_TIMESTAMP('2009-04-22 20:03:36','YYYY-MM-DD HH24:MI:SS'),0,'Lot number (alphanumeric)','EE01',20,'The Lot Number indicates the specific lot that a product was part of.','Y','N','N','N','N','N','N','N','N','N','Lot No',TO_TIMESTAMP('2009-04-22 20:03:36','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:37 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57351 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:37 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57352,568,0,10,53197,'SerNo',TO_TIMESTAMP('2009-04-22 20:03:37','YYYY-MM-DD HH24:MI:SS'),0,'Product Serial Number ','EE01',20,'The Serial Number identifies a tracked, warranted product.  It can only be used when the quantity is 1.','Y','N','N','N','N','N','N','N','N','N','Serial No',TO_TIMESTAMP('2009-04-22 20:03:37','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:37 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57352 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:38 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57353,215,0,19,53197,'C_UOM_ID',TO_TIMESTAMP('2009-04-22 20:03:37','YYYY-MM-DD HH24:MI:SS'),0,'Unit of Measure','EE01',10,'The UOM defines a unique non monetary Unit of Measure','Y','N','N','N','N','N','N','N','N','N','UOM',TO_TIMESTAMP('2009-04-22 20:03:37','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:38 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57353 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:39 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57354,1777,0,19,53197,'S_Resource_ID',TO_TIMESTAMP('2009-04-22 20:03:38','YYYY-MM-DD HH24:MI:SS'),0,'Resource','EE01',10,'Y','N','N','N','N','N','N','N','N','N','Resource',TO_TIMESTAMP('2009-04-22 20:03:38','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:39 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57354 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:40 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57355,53245,0,19,53197,'PP_Product_BOM_ID',TO_TIMESTAMP('2009-04-22 20:03:39','YYYY-MM-DD HH24:MI:SS'),0,'BOM & Formula','EE01',10,'Y','N','N','N','N','N','N','N','N','N','BOM & Formula',TO_TIMESTAMP('2009-04-22 20:03:39','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:40 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57355 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:40 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57356,144,0,19,53197,'AD_Workflow_ID',TO_TIMESTAMP('2009-04-22 20:03:40','YYYY-MM-DD HH24:MI:SS'),0,'Workflow or combination of tasks','EE01',10,'The Workflow field identifies a unique Workflow in the system.','Y','N','N','N','N','N','N','N','N','N','Workflow',TO_TIMESTAMP('2009-04-22 20:03:40','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:40 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57356 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:41 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57357,53247,0,22,53197,'Assay',TO_TIMESTAMP('2009-04-22 20:03:40','YYYY-MM-DD HH24:MI:SS'),0,'Indicated the Quantity Assay to use into Quality Order','EE01',131089,'Indicated the Quantity Assay to use into Quality Order','Y','N','N','N','N','N','N','N','N','N','Quantity Assay',TO_TIMESTAMP('2009-04-22 20:03:40','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:41 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57357 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:41 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57358,561,0,19,53197,'C_OrderLine_ID',TO_TIMESTAMP('2009-04-22 20:03:41','YYYY-MM-DD HH24:MI:SS'),0,'Sales Order Line','EE01',10,'The Sales Order Line is a unique identifier for a line in an order.','Y','N','N','N','N','N','N','N','N','N','Sales Order Line',TO_TIMESTAMP('2009-04-22 20:03:41','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:41 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57358 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:42 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57359,522,0,20,53197,'PriorityRule',TO_TIMESTAMP('2009-04-22 20:03:41','YYYY-MM-DD HH24:MI:SS'),0,'Priority of a document','EE01',1,'The Priority indicates the importance (high, medium, low) of this document','Y','N','N','N','N','N','N','N','N','N','Priority',TO_TIMESTAMP('2009-04-22 20:03:41','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:42 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57359 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:42 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57360,53243,0,29,53197,'QtyBatchSize',TO_TIMESTAMP('2009-04-22 20:03:42','YYYY-MM-DD HH24:MI:SS'),0,'EE01',131089,'Y','N','N','N','N','N','N','N','N','N','Qty Batch Size',TO_TIMESTAMP('2009-04-22 20:03:42','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:42 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57360 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:43 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57361,53302,0,29,53197,'QtyBatchs',TO_TIMESTAMP('2009-04-22 20:03:42','YYYY-MM-DD HH24:MI:SS'),0,'EE01',131089,'Y','N','N','N','N','N','N','N','N','N','Qty Batchs',TO_TIMESTAMP('2009-04-22 20:03:42','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:43 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57361 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:43 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57362,528,0,29,53197,'QtyDelivered',TO_TIMESTAMP('2009-04-22 20:03:43','YYYY-MM-DD HH24:MI:SS'),0,'Delivered Quantity','EE01',131089,'The Delivered Quantity indicates the quantity of a product that has been delivered.','Y','N','N','N','N','N','N','N','N','N','Delivered Quantity',TO_TIMESTAMP('2009-04-22 20:03:43','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:43 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57362 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:44 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57363,2589,0,29,53197,'QtyEntered',TO_TIMESTAMP('2009-04-22 20:03:43','YYYY-MM-DD HH24:MI:SS'),0,'The Quantity Entered is based on the selected UoM','EE01',131089,'The Quantity Entered is converted to base product UoM quantity','Y','N','N','N','N','N','N','N','N','N','Quantity',TO_TIMESTAMP('2009-04-22 20:03:43','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:44 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57363 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:45 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57364,531,0,29,53197,'QtyOrdered',TO_TIMESTAMP('2009-04-22 20:03:44','YYYY-MM-DD HH24:MI:SS'),0,'Ordered Quantity','EE01',131089,'The Ordered Quantity indicates the quantity of a product that was ordered.','Y','N','N','N','N','N','N','N','N','N','Ordered Quantity',TO_TIMESTAMP('2009-04-22 20:03:44','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:45 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57364 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:46 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57365,53277,0,16,53197,'DateConfirm',TO_TIMESTAMP('2009-04-22 20:03:45','YYYY-MM-DD HH24:MI:SS'),0,'EE01',29,'Y','N','N','N','N','N','N','N','N','N','DateConfirm',TO_TIMESTAMP('2009-04-22 20:03:45','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:46 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57365 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:47 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57366,264,0,16,53197,'DateDelivered',TO_TIMESTAMP('2009-04-22 20:03:46','YYYY-MM-DD HH24:MI:SS'),0,'Date when the product was delivered','EE01',29,'Y','N','N','N','N','N','N','N','N','N','Date Delivered',TO_TIMESTAMP('2009-04-22 20:03:46','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:47 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57366 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:47 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57367,1557,0,16,53197,'DateFinish',TO_TIMESTAMP('2009-04-22 20:03:47','YYYY-MM-DD HH24:MI:SS'),0,'Finish or (planned) completion date','EE01',29,'The finish date is used to indicate when the project is expected to be completed or has been completed.','Y','N','N','N','N','N','N','N','N','N','Finish Date',TO_TIMESTAMP('2009-04-22 20:03:47','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:47 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57367 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:48 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57368,53278,0,16,53197,'DateFinishSchedule',TO_TIMESTAMP('2009-04-22 20:03:47','YYYY-MM-DD HH24:MI:SS'),0,'EE01',29,'Y','N','N','N','N','N','N','N','N','N','DateFinishSchedule',TO_TIMESTAMP('2009-04-22 20:03:47','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:48 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57368 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:49 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57369,268,0,16,53197,'DateOrdered',TO_TIMESTAMP('2009-04-22 20:03:48','YYYY-MM-DD HH24:MI:SS'),0,'Date of Order','EE01',29,'Indicates the Date an item was ordered.','Y','N','N','N','N','N','N','N','N','N','Date Ordered',TO_TIMESTAMP('2009-04-22 20:03:48','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:49 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57369 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:49 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57370,269,0,16,53197,'DatePromised',TO_TIMESTAMP('2009-04-22 20:03:49','YYYY-MM-DD HH24:MI:SS'),0,'Date Order was promised','EE01',29,'The Date Promised indicates the date, if any, that an Order was promised for.','Y','N','N','N','N','N','N','N','N','N','Date Promised',TO_TIMESTAMP('2009-04-22 20:03:49','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:49 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57370 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:50 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57371,53287,0,29,53197,'QtyReject',TO_TIMESTAMP('2009-04-22 20:03:49','YYYY-MM-DD HH24:MI:SS'),0,'EE01',131089,'Y','N','N','N','N','N','N','N','N','N','Qty Reject',TO_TIMESTAMP('2009-04-22 20:03:49','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:50 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57371 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:50 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57372,532,0,29,53197,'QtyReserved',TO_TIMESTAMP('2009-04-22 20:03:50','YYYY-MM-DD HH24:MI:SS'),0,'Reserved Quantity','EE01',131089,'The Reserved Quantity indicates the quantity of a product that is currently reserved.','Y','N','N','N','N','N','N','N','N','N','Reserved Quantity',TO_TIMESTAMP('2009-04-22 20:03:50','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:50 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57372 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:51 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57373,53289,0,29,53197,'QtyScrap',TO_TIMESTAMP('2009-04-22 20:03:50','YYYY-MM-DD HH24:MI:SS'),0,'Scrap Quantity for this componet','EE01',131089,'Scrap Quantity for this componet','Y','N','N','N','N','N','N','N','N','N','QtyScrap',TO_TIMESTAMP('2009-04-22 20:03:50','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:51 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57373 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:51 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57374,53272,0,22,53197,'Yield',TO_TIMESTAMP('2009-04-22 20:03:51','YYYY-MM-DD HH24:MI:SS'),0,'The Yield is the percentage of a lot that is expected to be of acceptable wuality may fall below 100 percent','EE01',131089,'ADempiere Calculate the total yield for a product from the yield for each activity when the process Workflow Cost Roll-Up is executed.

The expected yield for an Activity can be expressed as:

Yield = Acceptable Units at Activity End x 100

The Total manufacturing yield for a product is determined by multiplying the yied percentage for each activity.

Manufacturing Yield = Yield % for Activity 10 x Yied % for Activity 20 , etc 

Take care when setting yield to anything but 100% particularly when yied is used for multiples activities

','Y','N','N','N','N','N','N','N','N','N','Yield %',TO_TIMESTAMP('2009-04-22 20:03:51','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:51 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57374 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:52 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57375,550,0,19,53197,'C_Campaign_ID',TO_TIMESTAMP('2009-04-22 20:03:51','YYYY-MM-DD HH24:MI:SS'),0,'Marketing Campaign','EE01',10,'The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','N','N','N','N','N','N','N','N','N','Campaign',TO_TIMESTAMP('2009-04-22 20:03:51','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:52 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57375 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:52 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57376,208,0,19,53197,'C_Project_ID',TO_TIMESTAMP('2009-04-22 20:03:52','YYYY-MM-DD HH24:MI:SS'),0,'Financial Project','EE01',10,'A Project allows you to track and control internal or external activities.','Y','N','N','N','N','N','N','N','N','N','Project',TO_TIMESTAMP('2009-04-22 20:03:52','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:52 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57376 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:53 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57377,1005,0,19,53197,'C_Activity_ID',TO_TIMESTAMP('2009-04-22 20:03:52','YYYY-MM-DD HH24:MI:SS'),0,'Business Activity','EE01',10,'Activities indicate tasks that are performed and used to utilize Activity based Costing','Y','N','N','N','N','N','N','N','N','N','Activity',TO_TIMESTAMP('2009-04-22 20:03:52','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:53 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57377 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:54 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57378,2030,0,20,53197,'BOMType',TO_TIMESTAMP('2009-04-22 20:03:53','YYYY-MM-DD HH24:MI:SS'),0,'Type of BOM','EE01',1,'The type of Bills of Materials determines the state','Y','N','N','N','N','N','N','N','N','N','BOM Type',TO_TIMESTAMP('2009-04-22 20:03:53','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:54 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57378 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:54 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57379,2784,0,20,53197,'BOMUse',TO_TIMESTAMP('2009-04-22 20:03:54','YYYY-MM-DD HH24:MI:SS'),0,'The use of the Bill of Material','EE01',1,'By default the Master BOM is used, if the alternatives are not defined','Y','N','N','N','N','N','N','N','N','N','BOM Use',TO_TIMESTAMP('2009-04-22 20:03:54','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:54 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57379 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:56 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57380,275,0,10,53197,'Description',TO_TIMESTAMP('2009-04-22 20:03:54','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE01',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','N','N','Description',TO_TIMESTAMP('2009-04-22 20:03:54','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:56 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57380 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:56 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57381,326,0,14,53197,'Help',TO_TIMESTAMP('2009-04-22 20:03:56','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint','EE01',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','N','N','Comment/Help',TO_TIMESTAMP('2009-04-22 20:03:56','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:56 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57381 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:57 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57382,2019,0,35,53197,'M_AttributeSetInstance_ID',TO_TIMESTAMP('2009-04-22 20:03:56','YYYY-MM-DD HH24:MI:SS'),0,'Product Attribute Set Instance','EE01',10,'The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.','Y','N','N','N','N','N','N','N','N','N','Attribute Set Instance',TO_TIMESTAMP('2009-04-22 20:03:56','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:57 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57382 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:58 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57383,454,0,19,53197,'M_Product_ID',TO_TIMESTAMP('2009-04-22 20:03:57','YYYY-MM-DD HH24:MI:SS'),0,'Product, Service, Item','EE01',10,'Identifies an item which is either purchased or sold in this organization.','Y','N','N','N','N','N','N','N','N','N','Product',TO_TIMESTAMP('2009-04-22 20:03:57','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:58 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57383 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:58 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57384,469,0,10,53197,'Name',TO_TIMESTAMP('2009-04-22 20:03:58','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE01',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','N','N','N','N','N','Name',1,TO_TIMESTAMP('2009-04-22 20:03:58','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:58 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57384 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:59 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57385,53244,0,10,53197,'Revision',TO_TIMESTAMP('2009-04-22 20:03:58','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','N','N','N','N','N','N','Revision',TO_TIMESTAMP('2009-04-22 20:03:58','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:59 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57385 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:03:59 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57386,617,0,16,53197,'ValidFrom',TO_TIMESTAMP('2009-04-22 20:03:59','YYYY-MM-DD HH24:MI:SS'),0,'Valid from including this date (first day)','EE01',29,'The Valid From date indicates the first day of a date range','Y','N','N','N','N','N','N','N','N','N','Valid from',TO_TIMESTAMP('2009-04-22 20:03:59','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:03:59 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57386 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:04:00 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57387,618,0,16,53197,'ValidTo',TO_TIMESTAMP('2009-04-22 20:03:59','YYYY-MM-DD HH24:MI:SS'),0,'Valid to including this date (last day)','EE01',29,'The Valid To date indicates the last day of a date range','Y','N','N','N','N','N','N','N','N','N','Valid to',TO_TIMESTAMP('2009-04-22 20:03:59','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:04:00 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57387 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:04:29 PM ECT
-- View Manufacturing Management
UPDATE AD_Table SET TableName='PP_Order_BOM_Header_v',Updated=TO_TIMESTAMP('2009-04-22 20:04:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=53197
;

-- Apr 22, 2009 8:04:29 PM ECT
-- View Manufacturing Management
UPDATE AD_Sequence SET Name='PP_Order_BOM_Header_v',Updated=TO_TIMESTAMP('2009-04-22 20:04:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=53292
;

-- Apr 22, 2009 8:05:24 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,CopyColumnsFromTable,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,LoadSeq,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53198,'3','N',TO_TIMESTAMP('2009-04-22 20:05:23','YYYY-MM-DD HH24:MI:SS'),0,'EE01','N','Y','N','N','N','N','N',0,'Manufacturing Order BOM Lines *** Templete ***','L','PP_ORDER_BOMLINE_V',TO_TIMESTAMP('2009-04-22 20:05:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 22, 2009 8:05:24 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53198 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Apr 22, 2009 8:05:24 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53293,TO_TIMESTAMP('2009-04-22 20:05:24','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table PP_ORDER_BOMLINE_V',1,'Y','N','Y','Y','PP_ORDER_BOMLINE_V','N',1000000,TO_TIMESTAMP('2009-04-22 20:05:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 22, 2009 8:05:30 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57388,102,0,19,53198,'AD_Client_ID',TO_TIMESTAMP('2009-04-22 20:05:30','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','EE01',10,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','N','N','N','N','N','Client',TO_TIMESTAMP('2009-04-22 20:05:30','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:30 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57388 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:31 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57389,113,0,19,53198,'AD_Org_ID',TO_TIMESTAMP('2009-04-22 20:05:30','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','EE01',10,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','N','N','N','N','N','Organization',TO_TIMESTAMP('2009-04-22 20:05:30','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:31 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57389 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:31 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57390,348,0,20,53198,'IsActive',TO_TIMESTAMP('2009-04-22 20:05:31','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','EE01',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','N','N','N','N','Y','Active',TO_TIMESTAMP('2009-04-22 20:05:31','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:31 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57390 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:32 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57391,245,0,16,53198,'Created',TO_TIMESTAMP('2009-04-22 20:05:31','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE01',29,'The Created field indicates the date that this record was created.','Y','N','N','N','N','N','N','N','N','N','Created',TO_TIMESTAMP('2009-04-22 20:05:31','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:32 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57391 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:32 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57392,246,0,18,110,53198,'CreatedBy',TO_TIMESTAMP('2009-04-22 20:05:32','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE01',10,'The Created By field indicates the user who created this record.','Y','N','N','N','N','N','N','N','N','N','Created By',TO_TIMESTAMP('2009-04-22 20:05:32','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:32 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57392 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:33 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57393,607,0,16,53198,'Updated',TO_TIMESTAMP('2009-04-22 20:05:32','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE01',29,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','N','N','N','N','N','Updated',TO_TIMESTAMP('2009-04-22 20:05:32','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:33 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57393 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:34 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57394,608,0,18,110,53198,'UpdatedBy',TO_TIMESTAMP('2009-04-22 20:05:33','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE01',10,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','N','N','N','N','N','Updated By',TO_TIMESTAMP('2009-04-22 20:05:33','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:34 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57394 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:34 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57395,275,0,10,53198,'Description',TO_TIMESTAMP('2009-04-22 20:05:34','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE01',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','N','Y','Description',TO_TIMESTAMP('2009-04-22 20:05:34','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:34 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57395 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:35 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57396,53246,0,10,53198,'Feature',TO_TIMESTAMP('2009-04-22 20:05:34','YYYY-MM-DD HH24:MI:SS'),0,'Indicated the Feature for Product Configure','EE01',30,'Indicated the Feature for Product Configure','Y','N','N','N','N','N','N','N','N','Y','Feature',TO_TIMESTAMP('2009-04-22 20:05:34','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:35 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57396 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:35 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57397,454,0,19,53198,'M_Product_ID',TO_TIMESTAMP('2009-04-22 20:05:35','YYYY-MM-DD HH24:MI:SS'),0,'Product, Service, Item','EE01',10,'Identifies an item which is either purchased or sold in this organization.','Y','N','N','N','N','N','N','N','N','Y','Product',TO_TIMESTAMP('2009-04-22 20:05:35','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:35 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57397 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:36 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57398,53248,0,10,53198,'BackflushGroup',TO_TIMESTAMP('2009-04-22 20:05:35','YYYY-MM-DD HH24:MI:SS'),0,'The Grouping Components to the Backflush','EE01',30,'When the components are deliver is possible to indicated The Backflush Group this way you only can deliver the components that are for this Backflush Group.','Y','N','N','N','N','N','N','N','N','Y','Backflush Group',TO_TIMESTAMP('2009-04-22 20:05:35','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:36 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57398 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:37 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57399,215,0,19,53198,'C_UOM_ID',TO_TIMESTAMP('2009-04-22 20:05:36','YYYY-MM-DD HH24:MI:SS'),0,'Unit of Measure','EE01',10,'The UOM defines a unique non monetary Unit of Measure','Y','N','N','N','N','N','N','N','N','Y','UOM',TO_TIMESTAMP('2009-04-22 20:05:36','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:37 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57399 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:38 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57400,53249,0,17,53198,'ComponentType',TO_TIMESTAMP('2009-04-22 20:05:37','YYYY-MM-DD HH24:MI:SS'),0,'Component Type for a Bill of Material or Formula','EE01',2,'The Component Type can be:

1.- By Product: Define a By Product as Component into BOM
2.- Component: Define a normal Component into BOM 
3.- Option: Define an Option for Product Configure BOM
4.- Phantom: Define a Phantom as Component into BOM
5.- Packing: Define a Packing as Component into BOM
6.- Planning : Define Planning as Component into BOM
7.- Tools: Define Tools as Component into BOM
8.- Variant: Define Variant  for Product Configure BOM
','Y','N','N','N','N','N','N','N','N','Y','Component Type',TO_TIMESTAMP('2009-04-22 20:05:37','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:38 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57400 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:38 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57401,264,0,16,53198,'DateDelivered',TO_TIMESTAMP('2009-04-22 20:05:38','YYYY-MM-DD HH24:MI:SS'),0,'Date when the product was delivered','EE01',29,'Y','N','N','N','N','N','N','N','N','Y','Date Delivered',TO_TIMESTAMP('2009-04-22 20:05:38','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:38 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57401 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:39 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57402,53250,0,22,53198,'Forecast',TO_TIMESTAMP('2009-04-22 20:05:38','YYYY-MM-DD HH24:MI:SS'),0,'Indicated the % of participation this component into a of the BOM Planning','EE01',131089,'The BOM of Planning Type are useful to Planning the Product family.

For example is possible create a BOM Planning for an Automobile

10% Automobile Red
35% Automobile Blue
45% Automobile Black
19% Automobile Green
1%  Automobile Orange

When Material Plan is calculated MRP generate a Manufacturing Order meet to demand to each  of the Automobile','Y','N','N','N','N','N','N','N','N','Y','Forecast',TO_TIMESTAMP('2009-04-22 20:05:38','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:39 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57402 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:39 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57403,326,0,14,53198,'Help',TO_TIMESTAMP('2009-04-22 20:05:39','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint','EE01',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','N','Y','Comment/Help',TO_TIMESTAMP('2009-04-22 20:05:39','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:39 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57403 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:40 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57404,53251,0,20,53198,'IsCritical',TO_TIMESTAMP('2009-04-22 20:05:39','YYYY-MM-DD HH24:MI:SS'),0,'Indicate that a Manufacturing Order can not begin without have this component','EE01',1,'Indicate that a Manufacturing Order can not begin without have this component','Y','N','N','N','N','N','N','N','N','Y','Is Critical Component',TO_TIMESTAMP('2009-04-22 20:05:39','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:40 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57404 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:40 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57405,53252,0,29,53198,'IsQtyPercentage',TO_TIMESTAMP('2009-04-22 20:05:40','YYYY-MM-DD HH24:MI:SS'),0,'Indicate that this component is based in % Quantity','EE01',1,'Indicate that this component is based in % Quantity','Y','N','N','N','N','N','N','N','N','Y','Is Qty Percentage',TO_TIMESTAMP('2009-04-22 20:05:40','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:40 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57405 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:41 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57406,53253,0,20,53198,'IssueMethod',TO_TIMESTAMP('2009-04-22 20:05:40','YYYY-MM-DD HH24:MI:SS'),0,'There are two methods for issue the components to Manufacturing Order','EE01',1,'Method Issue: The component are delivered one for one and is necessary indicate the delivered quantity for each component.

Method BackFlush: The component are delivered based in BOM, The  delivered quantity for each component is based in BOM or Formula and Manufacturing Order Quantity.

Use the field Backflush Group for grouping the component in a Backflush Method.','Y','N','N','N','N','N','N','N','N','Y','Issue Method',TO_TIMESTAMP('2009-04-22 20:05:40','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:41 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57406 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:42 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57407,2789,0,11,53198,'LeadTimeOffset',TO_TIMESTAMP('2009-04-22 20:05:41','YYYY-MM-DD HH24:MI:SS'),0,'Optional Lead Time offest before starting production','EE01',10,'Optional Lead Time offest before starting production','Y','N','N','N','N','N','N','N','N','Y','Lead Time Offset',TO_TIMESTAMP('2009-04-22 20:05:41','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:42 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57407 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:42 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57408,439,0,11,53198,'Line',TO_TIMESTAMP('2009-04-22 20:05:42','YYYY-MM-DD HH24:MI:SS'),0,'Unique line for this document','EE01',10,'Indicates the unique line for a document.  It will also control the display order of the lines within a document.','Y','N','N','N','N','N','N','N','N','Y','Line No',TO_TIMESTAMP('2009-04-22 20:05:42','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:42 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57408 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:43 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57409,2019,0,35,53198,'M_AttributeSetInstance_ID',TO_TIMESTAMP('2009-04-22 20:05:42','YYYY-MM-DD HH24:MI:SS'),0,'Product Attribute Set Instance','EE01',10,'The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.','Y','N','N','N','N','N','N','N','N','Y','Attribute Set Instance',TO_TIMESTAMP('2009-04-22 20:05:42','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:43 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57409 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:43 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57410,2783,0,19,53198,'M_ChangeNotice_ID',TO_TIMESTAMP('2009-04-22 20:05:43','YYYY-MM-DD HH24:MI:SS'),0,'Bill of Materials (Engineering) Change Notice (Version)','EE01',10,'Y','N','N','N','N','N','N','N','N','Y','Change Notice',TO_TIMESTAMP('2009-04-22 20:05:43','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:43 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57410 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:44 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57411,448,0,19,53198,'M_Locator_ID',TO_TIMESTAMP('2009-04-22 20:05:43','YYYY-MM-DD HH24:MI:SS'),0,'Warehouse Locator','EE01',10,'The Locator indicates where in a Warehouse a product is located.','Y','N','N','N','N','N','N','N','N','Y','Locator',TO_TIMESTAMP('2009-04-22 20:05:43','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:44 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57411 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:45 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57412,459,0,19,53198,'M_Warehouse_ID',TO_TIMESTAMP('2009-04-22 20:05:44','YYYY-MM-DD HH24:MI:SS'),0,'Storage Warehouse and Service Point','EE01',10,'The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','N','N','N','N','N','N','N','N','Y','Warehouse',TO_TIMESTAMP('2009-04-22 20:05:44','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:45 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57412 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:45 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57413,53298,0,19,53198,'PP_Order_BOM_ID',TO_TIMESTAMP('2009-04-22 20:05:45','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','N','N','N','N','N','Y','Manufacturing Order BOM',TO_TIMESTAMP('2009-04-22 20:05:45','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:45 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57413 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:46 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57414,53275,0,19,53198,'PP_Order_BOMLine_ID',TO_TIMESTAMP('2009-04-22 20:05:45','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','N','N','N','N','N','Y','Manufacturing Order BOM Line',TO_TIMESTAMP('2009-04-22 20:05:45','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:46 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57414 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:46 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57415,53276,0,19,53198,'PP_Order_ID',TO_TIMESTAMP('2009-04-22 20:05:46','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','N','N','N','N','N','Y','Manufacturing Order',TO_TIMESTAMP('2009-04-22 20:05:46','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:46 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57415 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:47 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57416,53255,0,29,53198,'QtyBOM',TO_TIMESTAMP('2009-04-22 20:05:46','YYYY-MM-DD HH24:MI:SS'),0,'Indicate the Quantity  use in this BOM','EE01',131089,'Exist two way the add a compenent to a BOM or Formula:

1.- Adding a Component based in quantity to use in this BOM
2.- Adding a Component based in % to use the Order Quantity of Manufacturing Order in this Formula.
','Y','N','N','N','N','N','N','N','N','Y','Quantity',TO_TIMESTAMP('2009-04-22 20:05:46','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:47 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57416 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:47 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57417,53256,0,29,53198,'QtyBatch',TO_TIMESTAMP('2009-04-22 20:05:47','YYYY-MM-DD HH24:MI:SS'),0,'Indicate the Quantity % use in this Formula','EE01',131089,'Exist two way the add a compenent to a BOM or Formula:

1.- Adding a Component based in quantity to use in this BOM
2.- Adding a Component based in % to use the Order Quantity of Manufacturing Order in this Formula.
','Y','N','N','N','N','N','N','N','N','Y','Quantity in %',TO_TIMESTAMP('2009-04-22 20:05:47','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:47 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57417 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:48 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57418,528,0,29,53198,'QtyDelivered',TO_TIMESTAMP('2009-04-22 20:05:47','YYYY-MM-DD HH24:MI:SS'),0,'Delivered Quantity','EE01',131089,'The Delivered Quantity indicates the quantity of a product that has been delivered.','Y','N','N','N','N','N','N','N','N','Y','Delivered Quantity',TO_TIMESTAMP('2009-04-22 20:05:47','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:48 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57418 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:48 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57419,53299,0,29,53198,'QtyPost',TO_TIMESTAMP('2009-04-22 20:05:48','YYYY-MM-DD HH24:MI:SS'),0,'EE01',131089,'Y','N','N','N','N','N','N','N','N','Y','Qty Post',TO_TIMESTAMP('2009-04-22 20:05:48','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:48 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57419 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:49 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57420,53287,0,29,53198,'QtyReject',TO_TIMESTAMP('2009-04-22 20:05:48','YYYY-MM-DD HH24:MI:SS'),0,'EE01',131089,'Y','N','N','N','N','N','N','N','N','Y','Qty Reject',TO_TIMESTAMP('2009-04-22 20:05:48','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:49 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57420 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:50 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57421,53288,0,29,53198,'QtyRequiered',TO_TIMESTAMP('2009-04-22 20:05:49','YYYY-MM-DD HH24:MI:SS'),0,'EE01',131089,'Y','N','N','N','N','N','N','N','N','Y','Qty Requiered',TO_TIMESTAMP('2009-04-22 20:05:49','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:50 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57421 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:50 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57422,532,0,29,53198,'QtyReserved',TO_TIMESTAMP('2009-04-22 20:05:50','YYYY-MM-DD HH24:MI:SS'),0,'Reserved Quantity','EE01',131089,'The Reserved Quantity indicates the quantity of a product that is currently reserved.','Y','N','N','N','N','N','N','N','N','Y','Reserved Quantity',TO_TIMESTAMP('2009-04-22 20:05:50','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:50 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57422 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:51 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57423,53289,0,29,53198,'QtyScrap',TO_TIMESTAMP('2009-04-22 20:05:50','YYYY-MM-DD HH24:MI:SS'),0,'Scrap Quantity for this componet','EE01',131089,'Scrap Quantity for this componet','Y','N','N','N','N','N','N','N','N','Y','QtyScrap',TO_TIMESTAMP('2009-04-22 20:05:50','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:51 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57423 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:51 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57424,53257,0,22,53198,'Scrap',TO_TIMESTAMP('2009-04-22 20:05:51','YYYY-MM-DD HH24:MI:SS'),0,'Indicate the % Scrap  for calculate the Scrap Quantity','EE01',131089,'Scrap is useful to determinate a rigth Standard Cost and management a good supply.','Y','N','N','N','N','N','N','N','N','Y','% Scrap',TO_TIMESTAMP('2009-04-22 20:05:51','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:51 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57424 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:52 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57425,617,0,16,53198,'ValidFrom',TO_TIMESTAMP('2009-04-22 20:05:51','YYYY-MM-DD HH24:MI:SS'),0,'Valid from including this date (first day)','EE01',29,'The Valid From date indicates the first day of a date range','Y','N','N','N','N','N','N','N','N','Y','Valid from',TO_TIMESTAMP('2009-04-22 20:05:51','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:52 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57425 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:53 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57426,618,0,16,53198,'ValidTo',TO_TIMESTAMP('2009-04-22 20:05:52','YYYY-MM-DD HH24:MI:SS'),0,'Valid to including this date (last day)','EE01',29,'The Valid To date indicates the last day of a date range','Y','N','N','N','N','N','N','N','N','Y','Valid to',TO_TIMESTAMP('2009-04-22 20:05:52','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:53 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57426 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:53 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57427,53247,0,22,53198,'Assay',TO_TIMESTAMP('2009-04-22 20:05:53','YYYY-MM-DD HH24:MI:SS'),0,'Indicated the Quantity Assay to use into Quality Order','EE01',131089,'Indicated the Quantity Assay to use into Quality Order','Y','N','N','N','N','N','N','N','N','Y','Quantity Assay',TO_TIMESTAMP('2009-04-22 20:05:53','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:53 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57427 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:05:54 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57428,138,0,19,53198,'AD_User_ID',TO_TIMESTAMP('2009-04-22 20:05:53','YYYY-MM-DD HH24:MI:SS'),0,'User within the system - Internal or Business Partner Contact','EE01',10,'The User identifies a unique user in the system. This could be an internal user or a business partner contact','Y','N','N','N','N','N','N','N','N','Y','User/Contact',TO_TIMESTAMP('2009-04-22 20:05:53','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:05:54 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57428 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:06:27 PM ECT
-- View Manufacturing Management
UPDATE AD_Table SET TableName='PP_Order_BOMLine_v',Updated=TO_TIMESTAMP('2009-04-22 20:06:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=53198
;

-- Apr 22, 2009 8:06:27 PM ECT
-- View Manufacturing Management
UPDATE AD_Sequence SET Name='PP_Order_BOMLine_v',Updated=TO_TIMESTAMP('2009-04-22 20:06:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=53293
;

-- Apr 22, 2009 8:07:45 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,CopyColumnsFromTable,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,LoadSeq,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53199,'3','N',TO_TIMESTAMP('2009-04-22 20:07:44','YYYY-MM-DD HH24:MI:SS'),0,'EE01','N','Y','N','N','N','N','N',0,'Manufacturing Order Workflow Header','L','PP_ORDER_Workflow_HEADER_V',TO_TIMESTAMP('2009-04-22 20:07:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 22, 2009 8:07:45 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53199 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Apr 22, 2009 8:07:46 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53294,TO_TIMESTAMP('2009-04-22 20:07:45','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table PP_ORDER_Workflow_HEADER_V',1,'Y','N','Y','Y','PP_ORDER_Workflow_HEADER_V','N',1000000,TO_TIMESTAMP('2009-04-22 20:07:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 22, 2009 8:07:54 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57429,102,0,19,53199,'AD_Client_ID',TO_TIMESTAMP('2009-04-22 20:07:53','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','EE01',10,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','N','N','N','N','N','Client',TO_TIMESTAMP('2009-04-22 20:07:53','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:07:54 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57429 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:07:54 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57430,113,0,19,53199,'AD_Org_ID',TO_TIMESTAMP('2009-04-22 20:07:54','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','EE01',10,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','N','N','N','N','N','Organization',TO_TIMESTAMP('2009-04-22 20:07:54','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:07:54 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57430 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:07:55 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57431,348,0,20,53199,'IsActive',TO_TIMESTAMP('2009-04-22 20:07:54','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','EE01',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','N','N','N','N','Y','Active',TO_TIMESTAMP('2009-04-22 20:07:54','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:07:55 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57431 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:07:55 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57432,245,0,16,53199,'Created',TO_TIMESTAMP('2009-04-22 20:07:55','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE01',29,'The Created field indicates the date that this record was created.','Y','N','N','N','N','N','N','N','N','N','Created',TO_TIMESTAMP('2009-04-22 20:07:55','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:07:55 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57432 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:07:56 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57433,246,0,18,110,53199,'CreatedBy',TO_TIMESTAMP('2009-04-22 20:07:55','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE01',10,'The Created By field indicates the user who created this record.','Y','N','N','N','N','N','N','N','N','N','Created By',TO_TIMESTAMP('2009-04-22 20:07:55','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:07:56 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57433 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:07:56 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57434,607,0,16,53199,'Updated',TO_TIMESTAMP('2009-04-22 20:07:56','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE01',29,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','N','N','N','N','N','Updated',TO_TIMESTAMP('2009-04-22 20:07:56','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:07:56 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57434 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:07:57 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57435,608,0,18,110,53199,'UpdatedBy',TO_TIMESTAMP('2009-04-22 20:07:56','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE01',10,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','N','N','N','N','N','Updated By',TO_TIMESTAMP('2009-04-22 20:07:56','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:07:57 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57435 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:07:58 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57436,109,0,14,53199,'AD_Language',TO_TIMESTAMP('2009-04-22 20:07:57','YYYY-MM-DD HH24:MI:SS'),0,'Language for this entity','EE01',2147483647,'The Language identifies the language to use for display and formatting','Y','N','N','N','N','N','N','N','N','Y','Language',TO_TIMESTAMP('2009-04-22 20:07:57','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:07:58 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57436 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:07:58 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57437,53276,0,19,53199,'PP_Order_ID',TO_TIMESTAMP('2009-04-22 20:07:58','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','N','N','N','N','N','Y','Manufacturing Order',TO_TIMESTAMP('2009-04-22 20:07:58','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:07:58 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57437 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:07:59 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57438,289,0,17,53199,'DocStatus',TO_TIMESTAMP('2009-04-22 20:07:58','YYYY-MM-DD HH24:MI:SS'),0,'The current status of the document','EE01',2,'The Document Status indicates the status of a document at this time.  If you want to change the document status, use the Document Action field','Y','N','N','N','N','N','N','N','N','Y','Document Status',TO_TIMESTAMP('2009-04-22 20:07:58','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:07:59 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57438 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:07:59 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57439,196,0,19,53199,'C_DocType_ID',TO_TIMESTAMP('2009-04-22 20:07:59','YYYY-MM-DD HH24:MI:SS'),0,'Document type or rules','EE01',10,'The Document Type determines document sequence and processing rules','Y','N','N','N','N','N','N','N','N','Y','Document Type',TO_TIMESTAMP('2009-04-22 20:07:59','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:07:59 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57439 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:00 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57440,1874,0,19,53199,'Org_Location_ID',TO_TIMESTAMP('2009-04-22 20:07:59','YYYY-MM-DD HH24:MI:SS'),0,'Organization Location/Address','EE01',10,'Y','N','N','N','N','N','N','N','N','Y','Org Address',TO_TIMESTAMP('2009-04-22 20:07:59','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:00 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57440 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:00 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57441,590,0,10,53199,'TaxID',TO_TIMESTAMP('2009-04-22 20:08:00','YYYY-MM-DD HH24:MI:SS'),0,'Tax Identification','EE01',20,'The Tax ID field identifies the legal Identification number of this Entity.','Y','N','N','N','N','N','N','N','N','Y','Tax ID',TO_TIMESTAMP('2009-04-22 20:08:00','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:00 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57441 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:01 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57442,459,0,19,53199,'M_Warehouse_ID',TO_TIMESTAMP('2009-04-22 20:08:00','YYYY-MM-DD HH24:MI:SS'),0,'Storage Warehouse and Service Point','EE01',10,'The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','N','N','N','N','N','N','N','N','Y','Warehouse',TO_TIMESTAMP('2009-04-22 20:08:00','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:01 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57442 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:01 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57443,1875,0,19,53199,'Warehouse_Location_ID',TO_TIMESTAMP('2009-04-22 20:08:01','YYYY-MM-DD HH24:MI:SS'),0,'Warehouse Location/Address','EE01',10,'Address of Warehouse','Y','N','N','N','N','N','N','N','N','Y','Warehouse Address',TO_TIMESTAMP('2009-04-22 20:08:01','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:01 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57443 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:02 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57444,1841,0,10,53199,'DocumentType',TO_TIMESTAMP('2009-04-22 20:08:01','YYYY-MM-DD HH24:MI:SS'),0,'Document Type','EE01',60,'Y','N','N','N','N','N','N','N','N','Y','Document Type',TO_TIMESTAMP('2009-04-22 20:08:01','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:02 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57444 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:03 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57445,1842,0,14,53199,'DocumentTypeNote',TO_TIMESTAMP('2009-04-22 20:08:02','YYYY-MM-DD HH24:MI:SS'),0,'Optional note of a document type','EE01',2000,'Y','N','N','N','N','N','N','N','N','Y','Document Type Note',TO_TIMESTAMP('2009-04-22 20:08:02','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:03 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57445 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:03 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57446,53269,0,19,53199,'Planner_ID',TO_TIMESTAMP('2009-04-22 20:08:03','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','N','N','N','N','N','Y','Planner',TO_TIMESTAMP('2009-04-22 20:08:03','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:03 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57446 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:04 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57447,1886,0,10,53199,'SalesRep_Name',TO_TIMESTAMP('2009-04-22 20:08:03','YYYY-MM-DD HH24:MI:SS'),0,'EE01',60,'Y','N','N','N','N','N','N','N','N','Y','Sales Representative',TO_TIMESTAMP('2009-04-22 20:08:03','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:04 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57447 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:05 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57448,53280,0,16,53199,'DateStart',TO_TIMESTAMP('2009-04-22 20:08:04','YYYY-MM-DD HH24:MI:SS'),0,'EE01',29,'Y','N','N','N','N','N','N','N','N','Y','DateStart',TO_TIMESTAMP('2009-04-22 20:08:04','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:05 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57448 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:05 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57449,53281,0,16,53199,'DateStartSchedule',TO_TIMESTAMP('2009-04-22 20:08:05','YYYY-MM-DD HH24:MI:SS'),0,'EE01',29,'Y','N','N','N','N','N','N','N','N','Y','DateStartSchedule',TO_TIMESTAMP('2009-04-22 20:08:05','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:05 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57449 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:06 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57450,53300,0,22,53199,'FloatAfter',TO_TIMESTAMP('2009-04-22 20:08:05','YYYY-MM-DD HH24:MI:SS'),0,'EE01',131089,'Y','N','N','N','N','N','N','N','N','Y','Float After',TO_TIMESTAMP('2009-04-22 20:08:05','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:06 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57450 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:06 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57451,53301,0,22,53199,'FloatBefored',TO_TIMESTAMP('2009-04-22 20:08:06','YYYY-MM-DD HH24:MI:SS'),0,'EE01',131089,'Y','N','N','N','N','N','N','N','N','Y','Float Befored',TO_TIMESTAMP('2009-04-22 20:08:06','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:06 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57451 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:07 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57452,439,0,11,53199,'Line',TO_TIMESTAMP('2009-04-22 20:08:06','YYYY-MM-DD HH24:MI:SS'),0,'Unique line for this document','EE01',10,'Indicates the unique line for a document.  It will also control the display order of the lines within a document.','Y','N','N','N','N','N','N','N','N','Y','Line No',TO_TIMESTAMP('2009-04-22 20:08:06','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:07 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57452 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:08 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57453,446,0,10,53199,'Lot',TO_TIMESTAMP('2009-04-22 20:08:07','YYYY-MM-DD HH24:MI:SS'),0,'Lot number (alphanumeric)','EE01',20,'The Lot Number indicates the specific lot that a product was part of.','Y','N','N','N','N','N','N','N','N','Y','Lot No',TO_TIMESTAMP('2009-04-22 20:08:07','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:08 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57453 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:09 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57454,568,0,10,53199,'SerNo',TO_TIMESTAMP('2009-04-22 20:08:08','YYYY-MM-DD HH24:MI:SS'),0,'Product Serial Number ','EE01',20,'The Serial Number identifies a tracked, warranted product.  It can only be used when the quantity is 1.','Y','N','N','N','N','N','N','N','N','Y','Serial No',TO_TIMESTAMP('2009-04-22 20:08:08','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:09 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57454 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:09 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57455,215,0,19,53199,'C_UOM_ID',TO_TIMESTAMP('2009-04-22 20:08:09','YYYY-MM-DD HH24:MI:SS'),0,'Unit of Measure','EE01',10,'The UOM defines a unique non monetary Unit of Measure','Y','N','N','N','N','N','N','N','N','Y','UOM',TO_TIMESTAMP('2009-04-22 20:08:09','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:09 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57455 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:09 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57456,1777,0,19,53199,'S_Resource_ID',TO_TIMESTAMP('2009-04-22 20:08:09','YYYY-MM-DD HH24:MI:SS'),0,'Resource','EE01',10,'Y','N','N','N','N','N','N','N','N','Y','Resource',TO_TIMESTAMP('2009-04-22 20:08:09','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:09 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57456 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:10 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57457,53245,0,19,53199,'PP_Product_BOM_ID',TO_TIMESTAMP('2009-04-22 20:08:09','YYYY-MM-DD HH24:MI:SS'),0,'BOM & Formula','EE01',10,'Y','N','N','N','N','N','N','N','N','Y','BOM & Formula',TO_TIMESTAMP('2009-04-22 20:08:09','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:10 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57457 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:11 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57458,144,0,19,53199,'AD_Workflow_ID',TO_TIMESTAMP('2009-04-22 20:08:10','YYYY-MM-DD HH24:MI:SS'),0,'Workflow or combination of tasks','EE01',10,'The Workflow field identifies a unique Workflow in the system.','Y','N','N','N','N','N','N','N','N','Y','Workflow',TO_TIMESTAMP('2009-04-22 20:08:10','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:11 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57458 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:11 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57459,53247,0,22,53199,'Assay',TO_TIMESTAMP('2009-04-22 20:08:11','YYYY-MM-DD HH24:MI:SS'),0,'Indicated the Quantity Assay to use into Quality Order','EE01',131089,'Indicated the Quantity Assay to use into Quality Order','Y','N','N','N','N','N','N','N','N','Y','Quantity Assay',TO_TIMESTAMP('2009-04-22 20:08:11','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:11 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57459 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:12 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57460,561,0,19,53199,'C_OrderLine_ID',TO_TIMESTAMP('2009-04-22 20:08:11','YYYY-MM-DD HH24:MI:SS'),0,'Sales Order Line','EE01',10,'The Sales Order Line is a unique identifier for a line in an order.','Y','N','N','N','N','N','N','N','N','Y','Sales Order Line',TO_TIMESTAMP('2009-04-22 20:08:11','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:12 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57460 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:12 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57461,522,0,20,53199,'PriorityRule',TO_TIMESTAMP('2009-04-22 20:08:12','YYYY-MM-DD HH24:MI:SS'),0,'Priority of a document','EE01',1,'The Priority indicates the importance (high, medium, low) of this document','Y','N','N','N','N','N','N','N','N','Y','Priority',TO_TIMESTAMP('2009-04-22 20:08:12','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:12 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57461 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:13 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57462,53302,0,29,53199,'QtyBatchs',TO_TIMESTAMP('2009-04-22 20:08:12','YYYY-MM-DD HH24:MI:SS'),0,'EE01',131089,'Y','N','N','N','N','N','N','N','N','Y','Qty Batchs',TO_TIMESTAMP('2009-04-22 20:08:12','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:13 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57462 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:14 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57463,528,0,29,53199,'QtyDelivered',TO_TIMESTAMP('2009-04-22 20:08:13','YYYY-MM-DD HH24:MI:SS'),0,'Delivered Quantity','EE01',131089,'The Delivered Quantity indicates the quantity of a product that has been delivered.','Y','N','N','N','N','N','N','N','N','Y','Delivered Quantity',TO_TIMESTAMP('2009-04-22 20:08:13','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:14 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57463 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:14 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57464,2589,0,29,53199,'QtyEntered',TO_TIMESTAMP('2009-04-22 20:08:14','YYYY-MM-DD HH24:MI:SS'),0,'The Quantity Entered is based on the selected UoM','EE01',131089,'The Quantity Entered is converted to base product UoM quantity','Y','N','N','N','N','N','N','N','N','Y','Quantity',TO_TIMESTAMP('2009-04-22 20:08:14','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:14 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57464 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:15 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57465,531,0,29,53199,'QtyOrdered',TO_TIMESTAMP('2009-04-22 20:08:14','YYYY-MM-DD HH24:MI:SS'),0,'Ordered Quantity','EE01',131089,'The Ordered Quantity indicates the quantity of a product that was ordered.','Y','N','N','N','N','N','N','N','N','Y','Ordered Quantity',TO_TIMESTAMP('2009-04-22 20:08:14','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:15 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57465 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:15 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57466,53277,0,16,53199,'DateConfirm',TO_TIMESTAMP('2009-04-22 20:08:15','YYYY-MM-DD HH24:MI:SS'),0,'EE01',29,'Y','N','N','N','N','N','N','N','N','Y','DateConfirm',TO_TIMESTAMP('2009-04-22 20:08:15','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:15 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57466 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:16 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57467,264,0,16,53199,'DateDelivered',TO_TIMESTAMP('2009-04-22 20:08:15','YYYY-MM-DD HH24:MI:SS'),0,'Date when the product was delivered','EE01',29,'Y','N','N','N','N','N','N','N','N','Y','Date Delivered',TO_TIMESTAMP('2009-04-22 20:08:15','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:16 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57467 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:16 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57468,1557,0,16,53199,'DateFinish',TO_TIMESTAMP('2009-04-22 20:08:16','YYYY-MM-DD HH24:MI:SS'),0,'Finish or (planned) completion date','EE01',29,'The finish date is used to indicate when the project is expected to be completed or has been completed.','Y','N','N','N','N','N','N','N','N','Y','Finish Date',TO_TIMESTAMP('2009-04-22 20:08:16','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:16 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57468 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:17 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57469,53278,0,16,53199,'DateFinishSchedule',TO_TIMESTAMP('2009-04-22 20:08:16','YYYY-MM-DD HH24:MI:SS'),0,'EE01',29,'Y','N','N','N','N','N','N','N','N','Y','DateFinishSchedule',TO_TIMESTAMP('2009-04-22 20:08:16','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:17 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57469 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:17 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57470,268,0,16,53199,'DateOrdered',TO_TIMESTAMP('2009-04-22 20:08:17','YYYY-MM-DD HH24:MI:SS'),0,'Date of Order','EE01',29,'Indicates the Date an item was ordered.','Y','N','N','N','N','N','N','N','N','Y','Date Ordered',TO_TIMESTAMP('2009-04-22 20:08:17','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:17 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57470 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:18 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57471,269,0,16,53199,'DatePromised',TO_TIMESTAMP('2009-04-22 20:08:17','YYYY-MM-DD HH24:MI:SS'),0,'Date Order was promised','EE01',29,'The Date Promised indicates the date, if any, that an Order was promised for.','Y','N','N','N','N','N','N','N','N','Y','Date Promised',TO_TIMESTAMP('2009-04-22 20:08:17','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:18 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57471 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:18 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57472,53287,0,29,53199,'QtyReject',TO_TIMESTAMP('2009-04-22 20:08:18','YYYY-MM-DD HH24:MI:SS'),0,'EE01',131089,'Y','N','N','N','N','N','N','N','N','Y','Qty Reject',TO_TIMESTAMP('2009-04-22 20:08:18','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:18 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57472 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:19 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57473,532,0,29,53199,'QtyReserved',TO_TIMESTAMP('2009-04-22 20:08:18','YYYY-MM-DD HH24:MI:SS'),0,'Reserved Quantity','EE01',131089,'The Reserved Quantity indicates the quantity of a product that is currently reserved.','Y','N','N','N','N','N','N','N','N','Y','Reserved Quantity',TO_TIMESTAMP('2009-04-22 20:08:18','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:19 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57473 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:19 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57474,53289,0,29,53199,'QtyScrap',TO_TIMESTAMP('2009-04-22 20:08:19','YYYY-MM-DD HH24:MI:SS'),0,'Scrap Quantity for this componet','EE01',131089,'Scrap Quantity for this componet','Y','N','N','N','N','N','N','N','N','Y','QtyScrap',TO_TIMESTAMP('2009-04-22 20:08:19','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:19 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57474 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:20 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57475,550,0,19,53199,'C_Campaign_ID',TO_TIMESTAMP('2009-04-22 20:08:19','YYYY-MM-DD HH24:MI:SS'),0,'Marketing Campaign','EE01',10,'The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','N','N','N','N','N','N','N','N','Y','Campaign',TO_TIMESTAMP('2009-04-22 20:08:19','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:20 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57475 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:21 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57476,208,0,19,53199,'C_Project_ID',TO_TIMESTAMP('2009-04-22 20:08:20','YYYY-MM-DD HH24:MI:SS'),0,'Financial Project','EE01',10,'A Project allows you to track and control internal or external activities.','Y','N','N','N','N','N','N','N','N','Y','Project',TO_TIMESTAMP('2009-04-22 20:08:20','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:21 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57476 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:21 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57477,1005,0,19,53199,'C_Activity_ID',TO_TIMESTAMP('2009-04-22 20:08:21','YYYY-MM-DD HH24:MI:SS'),0,'Business Activity','EE01',10,'Activities indicate tasks that are performed and used to utilize Activity based Costing','Y','N','N','N','N','N','N','N','N','Y','Activity',TO_TIMESTAMP('2009-04-22 20:08:21','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:21 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57477 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:22 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57478,469,0,10,53199,'Name',TO_TIMESTAMP('2009-04-22 20:08:21','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE01',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','N','N','N','N','Y','Name',1,TO_TIMESTAMP('2009-04-22 20:08:21','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:22 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57478 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:23 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57479,275,0,10,53199,'Description',TO_TIMESTAMP('2009-04-22 20:08:22','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE01',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','N','Y','Description',TO_TIMESTAMP('2009-04-22 20:08:22','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:23 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57479 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:23 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57480,326,0,14,53199,'Help',TO_TIMESTAMP('2009-04-22 20:08:23','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint','EE01',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','N','Y','Comment/Help',TO_TIMESTAMP('2009-04-22 20:08:23','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:23 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57480 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:24 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57481,2318,0,10,53199,'Author',TO_TIMESTAMP('2009-04-22 20:08:23','YYYY-MM-DD HH24:MI:SS'),0,'Author/Creator of the Entity','EE01',20,'Y','N','N','N','N','N','N','N','N','Y','Author',TO_TIMESTAMP('2009-04-22 20:08:23','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:24 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57481 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:25 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57482,2319,0,22,53199,'Cost',TO_TIMESTAMP('2009-04-22 20:08:24','YYYY-MM-DD HH24:MI:SS'),0,'Cost information','EE01',131089,'Y','N','N','N','N','N','N','N','N','Y','Cost',TO_TIMESTAMP('2009-04-22 20:08:24','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:25 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57482 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:26 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57483,290,0,10,53199,'DocumentNo',TO_TIMESTAMP('2009-04-22 20:08:25','YYYY-MM-DD HH24:MI:SS'),0,'Document sequence number of the document','EE01',32,'The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','N','N','N','N','N','N','Y','Document No',TO_TIMESTAMP('2009-04-22 20:08:25','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:26 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57483 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:26 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57484,2320,0,11,53199,'Duration',TO_TIMESTAMP('2009-04-22 20:08:26','YYYY-MM-DD HH24:MI:SS'),0,'Normal Duration in Duration Unit','EE01',10,'Expected (normal) Length of time for the execution','Y','N','N','N','N','N','N','N','N','Y','Duration',TO_TIMESTAMP('2009-04-22 20:08:26','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:26 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57484 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:27 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57485,2321,0,20,53199,'DurationUnit',TO_TIMESTAMP('2009-04-22 20:08:26','YYYY-MM-DD HH24:MI:SS'),0,'Unit of Duration','EE01',1,'Unit to define the length of time for the execution','Y','N','N','N','N','N','N','N','N','Y','Duration Unit',TO_TIMESTAMP('2009-04-22 20:08:26','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:27 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57485 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:28 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57486,624,0,11,53199,'Version',TO_TIMESTAMP('2009-04-22 20:08:27','YYYY-MM-DD HH24:MI:SS'),0,'Version of the table definition','EE01',10,'The Version indicates the version of this table definition.','Y','N','N','N','N','N','N','N','N','Y','Version',TO_TIMESTAMP('2009-04-22 20:08:27','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:28 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57486 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:28 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57487,617,0,16,53199,'ValidFrom',TO_TIMESTAMP('2009-04-22 20:08:28','YYYY-MM-DD HH24:MI:SS'),0,'Valid from including this date (first day)','EE01',29,'The Valid From date indicates the first day of a date range','Y','N','N','N','N','N','N','N','N','Y','Valid from',TO_TIMESTAMP('2009-04-22 20:08:28','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:28 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57487 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:29 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57488,618,0,16,53199,'ValidTo',TO_TIMESTAMP('2009-04-22 20:08:28','YYYY-MM-DD HH24:MI:SS'),0,'Valid to including this date (last day)','EE01',29,'The Valid To date indicates the last day of a date range','Y','N','N','N','N','N','N','N','N','Y','Valid to',TO_TIMESTAMP('2009-04-22 20:08:28','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:29 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57488 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:29 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57489,53240,0,11,53199,'MovingTime',TO_TIMESTAMP('2009-04-22 20:08:29','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','N','N','N','N','N','Y','Moving Time',TO_TIMESTAMP('2009-04-22 20:08:29','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:29 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57489 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:30 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57490,53241,0,22,53199,'OverlapUnits',TO_TIMESTAMP('2009-04-22 20:08:29','YYYY-MM-DD HH24:MI:SS'),0,'Overlap Units are number of units that must be completed before they are moved the next activity','EE01',131089,'When there are two consecutive avtivity, you can sometimes save time by moving partial quantites from one activity to the next before the first activity as been completed.','Y','N','N','N','N','N','N','N','N','Y','Overlap Units',TO_TIMESTAMP('2009-04-22 20:08:29','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:30 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57490 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:30 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57491,2338,0,20,53199,'PublishStatus',TO_TIMESTAMP('2009-04-22 20:08:30','YYYY-MM-DD HH24:MI:SS'),0,'Status of Publication','EE01',1,'Used for internal documentation','Y','N','N','N','N','N','N','N','N','Y','Publication Status',TO_TIMESTAMP('2009-04-22 20:08:30','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:30 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57491 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:31 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57492,53243,0,29,53199,'QtyBatchSize',TO_TIMESTAMP('2009-04-22 20:08:30','YYYY-MM-DD HH24:MI:SS'),0,'EE01',131089,'Y','N','N','N','N','N','N','N','N','Y','Qty Batch Size',TO_TIMESTAMP('2009-04-22 20:08:30','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:31 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57492 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:31 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57493,53234,0,11,53199,'QueuingTime',TO_TIMESTAMP('2009-04-22 20:08:31','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','N','N','N','N','N','Y','Queuing Time',TO_TIMESTAMP('2009-04-22 20:08:31','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:31 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57493 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:32 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57494,2777,0,11,53199,'SetupTime',TO_TIMESTAMP('2009-04-22 20:08:31','YYYY-MM-DD HH24:MI:SS'),0,'Setup time before starting Production','EE01',10,'Once per operation','Y','N','N','N','N','N','N','N','N','Y','Setup Time',TO_TIMESTAMP('2009-04-22 20:08:31','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:32 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57494 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:32 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57495,53239,0,22,53199,'UnitsCycles',TO_TIMESTAMP('2009-04-22 20:08:32','YYYY-MM-DD HH24:MI:SS'),0,'The Units by Cycles are defined for process type  Flow Repetitive Dedicated and  indicated the product to be manufactured on a production line for duration unit.','EE01',131089,'When Units by Cycles are defined the duration time is the total of time to manufactured the units','Y','N','N','N','N','N','N','N','N','Y','Units by Cycles',TO_TIMESTAMP('2009-04-22 20:08:32','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:32 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57495 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:33 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57496,2331,0,11,53199,'WaitingTime',TO_TIMESTAMP('2009-04-22 20:08:32','YYYY-MM-DD HH24:MI:SS'),0,'Workflow Simulation Waiting time','EE01',10,'Amount of time needed to prepare the performance of the task on Duration Units','Y','N','N','N','N','N','N','N','N','Y','Waiting Time',TO_TIMESTAMP('2009-04-22 20:08:32','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:33 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57496 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:33 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57497,2626,0,20,53199,'WorkflowType',TO_TIMESTAMP('2009-04-22 20:08:33','YYYY-MM-DD HH24:MI:SS'),0,'Type of Worflow','EE01',1,'The type of workflow determines how the workflow is started.','Y','N','N','N','N','N','N','N','N','Y','Workflow Type',TO_TIMESTAMP('2009-04-22 20:08:33','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:33 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57497 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:34 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57498,2333,0,11,53199,'WorkingTime',TO_TIMESTAMP('2009-04-22 20:08:33','YYYY-MM-DD HH24:MI:SS'),0,'Workflow Simulation Execution Time','EE01',10,'Amount of time the performer of the activity needs to perform the task in Duration Unit','Y','N','N','N','N','N','N','N','N','Y','Working Time',TO_TIMESTAMP('2009-04-22 20:08:33','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:34 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57498 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:34 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57499,53272,0,11,53199,'Yield',TO_TIMESTAMP('2009-04-22 20:08:34','YYYY-MM-DD HH24:MI:SS'),0,'The Yield is the percentage of a lot that is expected to be of acceptable wuality may fall below 100 percent','EE01',10,'ADempiere Calculate the total yield for a product from the yield for each activity when the process Workflow Cost Roll-Up is executed.

The expected yield for an Activity can be expressed as:

Yield = Acceptable Units at Activity End x 100

The Total manufacturing yield for a product is determined by multiplying the yied percentage for each activity.

Manufacturing Yield = Yield % for Activity 10 x Yied % for Activity 20 , etc 

Take care when setting yield to anything but 100% particularly when yied is used for multiples activities

','Y','N','N','N','N','N','N','N','N','Y','Yield %',TO_TIMESTAMP('2009-04-22 20:08:34','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:08:34 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57499 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:08:55 PM ECT
-- View Manufacturing Management
UPDATE AD_Table SET TableName='PP_Order_Workflow_Header_v',Updated=TO_TIMESTAMP('2009-04-22 20:08:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=53199
;

-- Apr 22, 2009 8:08:55 PM ECT
-- View Manufacturing Management
UPDATE AD_Sequence SET Name='PP_Order_Workflow_Header_v',Updated=TO_TIMESTAMP('2009-04-22 20:08:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=53294
;

-- Apr 22, 2009 8:10:10 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,CopyColumnsFromTable,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,LoadSeq,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53200,'3','N',TO_TIMESTAMP('2009-04-22 20:10:09','YYYY-MM-DD HH24:MI:SS'),0,'EE01','N','Y','N','N','N','N','N',0,'Manufacturing Order Node','L','PP_ORDER_NODE_V',TO_TIMESTAMP('2009-04-22 20:10:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 22, 2009 8:10:10 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53200 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Apr 22, 2009 8:10:10 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53295,TO_TIMESTAMP('2009-04-22 20:10:10','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table PP_ORDER_NODE_V',1,'Y','N','Y','Y','PP_ORDER_NODE_V','N',1000000,TO_TIMESTAMP('2009-04-22 20:10:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 22, 2009 8:11:00 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57500,102,0,19,53200,'AD_Client_ID',TO_TIMESTAMP('2009-04-22 20:10:59','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','EE01',10,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','N','N','N','N','N','Client',TO_TIMESTAMP('2009-04-22 20:10:59','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:00 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57500 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:00 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57501,113,0,19,53200,'AD_Org_ID',TO_TIMESTAMP('2009-04-22 20:11:00','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','EE01',10,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','N','N','N','N','N','Organization',TO_TIMESTAMP('2009-04-22 20:11:00','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:00 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57501 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:01 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57502,348,0,20,53200,'IsActive',TO_TIMESTAMP('2009-04-22 20:11:00','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','EE01',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','N','N','N','N','Y','Active',TO_TIMESTAMP('2009-04-22 20:11:00','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:01 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57502 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:02 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57503,245,0,16,53200,'Created',TO_TIMESTAMP('2009-04-22 20:11:01','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE01',29,'The Created field indicates the date that this record was created.','Y','N','N','N','N','N','N','N','N','N','Created',TO_TIMESTAMP('2009-04-22 20:11:01','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:02 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57503 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:02 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57504,246,0,18,110,53200,'CreatedBy',TO_TIMESTAMP('2009-04-22 20:11:02','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE01',10,'The Created By field indicates the user who created this record.','Y','N','N','N','N','N','N','N','N','N','Created By',TO_TIMESTAMP('2009-04-22 20:11:02','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:02 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57504 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:03 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57505,607,0,16,53200,'Updated',TO_TIMESTAMP('2009-04-22 20:11:02','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE01',29,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','N','N','N','N','N','Updated',TO_TIMESTAMP('2009-04-22 20:11:02','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:03 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57505 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:04 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57506,608,0,18,110,53200,'UpdatedBy',TO_TIMESTAMP('2009-04-22 20:11:03','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE01',10,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','N','N','N','N','N','Updated By',TO_TIMESTAMP('2009-04-22 20:11:03','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:04 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57506 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:04 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57507,469,0,10,53200,'Name',TO_TIMESTAMP('2009-04-22 20:11:04','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE01',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','N','N','N','N','Y','Name',1,TO_TIMESTAMP('2009-04-22 20:11:04','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:04 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57507 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:05 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57508,187,0,19,53200,'C_BPartner_ID',TO_TIMESTAMP('2009-04-22 20:11:04','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a Business Partner','EE01',10,'A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','N','N','N','N','N','N','N','N','Y','Business Partner ',TO_TIMESTAMP('2009-04-22 20:11:04','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:05 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57508 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:05 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57509,2319,0,22,53200,'Cost',TO_TIMESTAMP('2009-04-22 20:11:05','YYYY-MM-DD HH24:MI:SS'),0,'Cost information','EE01',131089,'Y','N','N','N','N','N','N','N','N','Y','Cost',TO_TIMESTAMP('2009-04-22 20:11:05','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:05 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57509 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:06 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57510,1557,0,16,53200,'DateFinish',TO_TIMESTAMP('2009-04-22 20:11:05','YYYY-MM-DD HH24:MI:SS'),0,'Finish or (planned) completion date','EE01',29,'The finish date is used to indicate when the project is expected to be completed or has been completed.','Y','N','N','N','N','N','N','N','N','Y','Finish Date',TO_TIMESTAMP('2009-04-22 20:11:05','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:06 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57510 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:06 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57511,53278,0,16,53200,'DateFinishSchedule',TO_TIMESTAMP('2009-04-22 20:11:06','YYYY-MM-DD HH24:MI:SS'),0,'EE01',29,'Y','N','N','N','N','N','N','N','N','Y','DateFinishSchedule',TO_TIMESTAMP('2009-04-22 20:11:06','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:06 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57511 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:07 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57512,53280,0,16,53200,'DateStart',TO_TIMESTAMP('2009-04-22 20:11:06','YYYY-MM-DD HH24:MI:SS'),0,'EE01',29,'Y','N','N','N','N','N','N','N','N','Y','DateStart',TO_TIMESTAMP('2009-04-22 20:11:06','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:07 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57512 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:07 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57513,53281,0,16,53200,'DateStartSchedule',TO_TIMESTAMP('2009-04-22 20:11:07','YYYY-MM-DD HH24:MI:SS'),0,'EE01',29,'Y','N','N','N','N','N','N','N','N','Y','DateStartSchedule',TO_TIMESTAMP('2009-04-22 20:11:07','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:07 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57513 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:08 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57514,275,0,10,53200,'Description',TO_TIMESTAMP('2009-04-22 20:11:07','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE01',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','N','Y','Description',TO_TIMESTAMP('2009-04-22 20:11:07','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:08 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57514 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:08 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57515,287,0,17,53200,'DocAction',TO_TIMESTAMP('2009-04-22 20:11:08','YYYY-MM-DD HH24:MI:SS'),0,'The targeted status of the document','EE01',2,'You find the current status in the Document Status field. The options are listed in a popup','Y','N','N','N','N','N','N','N','N','Y','Document Action',TO_TIMESTAMP('2009-04-22 20:11:08','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:08 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57515 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:09 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57516,2320,0,11,53200,'Duration',TO_TIMESTAMP('2009-04-22 20:11:08','YYYY-MM-DD HH24:MI:SS'),0,'Normal Duration in Duration Unit','EE01',10,'Expected (normal) Length of time for the execution','Y','N','N','N','N','N','N','N','N','Y','Duration',TO_TIMESTAMP('2009-04-22 20:11:08','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:09 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57516 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:09 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57517,53283,0,11,53200,'DurationReal',TO_TIMESTAMP('2009-04-22 20:11:09','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','N','N','N','N','N','Y','Duration Real',TO_TIMESTAMP('2009-04-22 20:11:09','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:09 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57517 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:10 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57518,53284,0,11,53200,'DurationRequiered',TO_TIMESTAMP('2009-04-22 20:11:09','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','N','N','N','N','N','Y','Duration Requiered',TO_TIMESTAMP('2009-04-22 20:11:09','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:10 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57518 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:10 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57519,326,0,14,53200,'Help',TO_TIMESTAMP('2009-04-22 20:11:10','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint','EE01',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','N','Y','Comment/Help',TO_TIMESTAMP('2009-04-22 20:11:10','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:10 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57519 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:11 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57520,53237,0,20,53200,'IsMilestone',TO_TIMESTAMP('2009-04-22 20:11:10','YYYY-MM-DD HH24:MI:SS'),0,'EE01',1,'Y','N','N','N','N','N','N','N','N','Y','Is Milestone',TO_TIMESTAMP('2009-04-22 20:11:10','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:11 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57520 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:11 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57521,53238,0,20,53200,'IsSubcontracting',TO_TIMESTAMP('2009-04-22 20:11:11','YYYY-MM-DD HH24:MI:SS'),0,'EE01',1,'Y','N','N','N','N','N','N','N','N','Y','Is Subcontracting',TO_TIMESTAMP('2009-04-22 20:11:11','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:11 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57521 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:12 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57522,53240,0,11,53200,'MovingTime',TO_TIMESTAMP('2009-04-22 20:11:11','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','N','N','N','N','N','Y','Moving Time',TO_TIMESTAMP('2009-04-22 20:11:11','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:12 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57522 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:12 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57523,53241,0,11,53200,'OverlapUnits',TO_TIMESTAMP('2009-04-22 20:11:12','YYYY-MM-DD HH24:MI:SS'),0,'Overlap Units are number of units that must be completed before they are moved the next activity','EE01',10,'When there are two consecutive avtivity, you can sometimes save time by moving partial quantites from one activity to the next before the first activity as been completed.','Y','N','N','N','N','N','N','N','N','Y','Overlap Units',TO_TIMESTAMP('2009-04-22 20:11:12','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:13 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57523 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:13 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57524,53276,0,19,53200,'PP_Order_ID',TO_TIMESTAMP('2009-04-22 20:11:13','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','N','N','N','N','N','Y','Manufacturing Order',TO_TIMESTAMP('2009-04-22 20:11:13','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:13 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57524 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:14 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57525,53286,0,19,53200,'PP_Order_Workflow_ID',TO_TIMESTAMP('2009-04-22 20:11:13','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','N','N','N','N','N','Y','Manufacturing Order Workflow',TO_TIMESTAMP('2009-04-22 20:11:13','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:14 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57525 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:14 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57526,1514,0,11,53200,'Priority',TO_TIMESTAMP('2009-04-22 20:11:14','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if this request is of a high, medium or low priority.','EE01',10,'The Priority indicates the importance of this request.','Y','N','N','N','N','N','N','N','N','Y','Priority',TO_TIMESTAMP('2009-04-22 20:11:14','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:14 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57526 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:15 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57527,528,0,29,53200,'QtyDelivered',TO_TIMESTAMP('2009-04-22 20:11:14','YYYY-MM-DD HH24:MI:SS'),0,'Delivered Quantity','EE01',131089,'The Delivered Quantity indicates the quantity of a product that has been delivered.','Y','N','N','N','N','N','N','N','N','Y','Delivered Quantity',TO_TIMESTAMP('2009-04-22 20:11:14','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:15 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57527 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:15 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57528,53288,0,29,53200,'QtyRequiered',TO_TIMESTAMP('2009-04-22 20:11:15','YYYY-MM-DD HH24:MI:SS'),0,'EE01',131089,'Y','N','N','N','N','N','N','N','N','Y','Qty Requiered',TO_TIMESTAMP('2009-04-22 20:11:15','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:15 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57528 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:16 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57529,53289,0,29,53200,'QtyScrap',TO_TIMESTAMP('2009-04-22 20:11:15','YYYY-MM-DD HH24:MI:SS'),0,'Scrap Quantity for this componet','EE01',131089,'Scrap Quantity for this componet','Y','N','N','N','N','N','N','N','N','Y','QtyScrap',TO_TIMESTAMP('2009-04-22 20:11:15','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:16 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57529 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:17 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57530,53234,0,11,53200,'QueuingTime',TO_TIMESTAMP('2009-04-22 20:11:16','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','N','N','N','N','N','Y','Queuing Time',TO_TIMESTAMP('2009-04-22 20:11:16','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:17 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57530 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:17 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57531,1777,0,19,53200,'S_Resource_ID',TO_TIMESTAMP('2009-04-22 20:11:17','YYYY-MM-DD HH24:MI:SS'),0,'Resource','EE01',10,'Y','N','N','N','N','N','N','N','N','Y','Resource',TO_TIMESTAMP('2009-04-22 20:11:17','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:17 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57531 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:18 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57532,2777,0,11,53200,'SetupTime',TO_TIMESTAMP('2009-04-22 20:11:17','YYYY-MM-DD HH24:MI:SS'),0,'Setup time before starting Production','EE01',10,'Once per operation','Y','N','N','N','N','N','N','N','N','Y','Setup Time',TO_TIMESTAMP('2009-04-22 20:11:17','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:18 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57532 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:19 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57533,53290,0,11,53200,'SetupTimeReal',TO_TIMESTAMP('2009-04-22 20:11:18','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','N','N','N','N','N','Y','Setup Time Real',TO_TIMESTAMP('2009-04-22 20:11:18','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:19 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57533 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:19 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57534,53239,0,11,53200,'UnitsCycles',TO_TIMESTAMP('2009-04-22 20:11:19','YYYY-MM-DD HH24:MI:SS'),0,'The Units by Cycles are defined for process type  Flow Repetitive Dedicated and  indicated the product to be manufactured on a production line for duration unit.','EE01',10,'When Units by Cycles are defined the duration time is the total of time to manufactured the units','Y','N','N','N','N','N','N','N','N','Y','Units by Cycles',TO_TIMESTAMP('2009-04-22 20:11:19','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:19 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57534 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:20 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57535,617,0,16,53200,'ValidFrom',TO_TIMESTAMP('2009-04-22 20:11:19','YYYY-MM-DD HH24:MI:SS'),0,'Valid from including this date (first day)','EE01',29,'The Valid From date indicates the first day of a date range','Y','N','N','N','N','N','N','N','N','Y','Valid from',TO_TIMESTAMP('2009-04-22 20:11:19','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:20 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57535 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:20 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57536,618,0,16,53200,'ValidTo',TO_TIMESTAMP('2009-04-22 20:11:20','YYYY-MM-DD HH24:MI:SS'),0,'Valid to including this date (last day)','EE01',29,'The Valid To date indicates the last day of a date range','Y','N','N','N','N','N','N','N','N','Y','Valid to',TO_TIMESTAMP('2009-04-22 20:11:20','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:20 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57536 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:21 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57537,620,0,10,53200,'Value',TO_TIMESTAMP('2009-04-22 20:11:20','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique','EE01',40,'A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','N','N','N','N','N','N','Y','Search Key',TO_TIMESTAMP('2009-04-22 20:11:20','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:21 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57537 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:21 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57538,2331,0,11,53200,'WaitingTime',TO_TIMESTAMP('2009-04-22 20:11:21','YYYY-MM-DD HH24:MI:SS'),0,'Workflow Simulation Waiting time','EE01',10,'Amount of time needed to prepare the performance of the task on Duration Units','Y','N','N','N','N','N','N','N','N','Y','Waiting Time',TO_TIMESTAMP('2009-04-22 20:11:21','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:21 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57538 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:22 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57539,2333,0,11,53200,'WorkingTime',TO_TIMESTAMP('2009-04-22 20:11:21','YYYY-MM-DD HH24:MI:SS'),0,'Workflow Simulation Execution Time','EE01',10,'Amount of time the performer of the activity needs to perform the task in Duration Unit','Y','N','N','N','N','N','N','N','N','Y','Working Time',TO_TIMESTAMP('2009-04-22 20:11:21','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:22 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57539 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:23 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57540,53272,0,11,53200,'Yield',TO_TIMESTAMP('2009-04-22 20:11:22','YYYY-MM-DD HH24:MI:SS'),0,'The Yield is the percentage of a lot that is expected to be of acceptable wuality may fall below 100 percent','EE01',10,'ADempiere Calculate the total yield for a product from the yield for each activity when the process Workflow Cost Roll-Up is executed.

The expected yield for an Activity can be expressed as:

Yield = Acceptable Units at Activity End x 100

The Total manufacturing yield for a product is determined by multiplying the yied percentage for each activity.

Manufacturing Yield = Yield % for Activity 10 x Yied % for Activity 20 , etc 

Take care when setting yield to anything but 100% particularly when yied is used for multiples activities

','Y','N','N','N','N','N','N','N','N','Y','Yield %',TO_TIMESTAMP('2009-04-22 20:11:22','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 22, 2009 8:11:23 PM ECT
-- View Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57540 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 22, 2009 8:11:48 PM ECT
-- View Manufacturing Management
UPDATE AD_Table SET TableName='PP_Order_Node_v',Updated=TO_TIMESTAMP('2009-04-22 20:11:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=53200
;

-- Apr 22, 2009 8:11:48 PM ECT
-- View Manufacturing Management
UPDATE AD_Sequence SET Name='PP_Order_Node_v',Updated=TO_TIMESTAMP('2009-04-22 20:11:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=53295
;

-- Apr 22, 2009 8:12:47 PM ECT
-- View Manufacturing Management
UPDATE AD_Column SET AD_Reference_ID=17, AD_Reference_Value_ID=154,Updated=TO_TIMESTAMP('2009-04-22 20:12:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=57526
;

-- Apr 22, 2009 8:13:30 PM ECT
-- View Manufacturing Management
UPDATE AD_Column SET AD_Reference_Value_ID=131,Updated=TO_TIMESTAMP('2009-04-22 20:13:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=57438
;

-- Apr 22, 2009 8:14:22 PM ECT
-- View Manufacturing Management
UPDATE AD_Column SET AD_Reference_ID=17, AD_Reference_Value_ID=299,Updated=TO_TIMESTAMP('2009-04-22 20:14:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=57485
;

-- Apr 22, 2009 8:15:03 PM ECT
-- View Manufacturing Management
UPDATE AD_Column SET AD_Reference_ID=17, AD_Reference_Value_ID=154,Updated=TO_TIMESTAMP('2009-04-22 20:15:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=57461
;

-- Apr 22, 2009 8:15:30 PM ECT
-- View Manufacturing Management
UPDATE AD_Column SET AD_Reference_ID=17, AD_Reference_Value_ID=328,Updated=TO_TIMESTAMP('2009-04-22 20:15:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=57497
;

-- Apr 22, 2009 8:15:52 PM ECT
-- View Manufacturing Management
UPDATE AD_Column SET AD_Reference_Value_ID=53225,Updated=TO_TIMESTAMP('2009-04-22 20:15:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=57400
;

-- Apr 22, 2009 8:16:13 PM ECT
-- View Manufacturing Management
UPDATE AD_Column SET AD_Reference_ID=17, AD_Reference_Value_ID=53226,Updated=TO_TIMESTAMP('2009-04-22 20:16:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=57406
;

-- Apr 22, 2009 8:16:47 PM ECT
-- View Manufacturing Management
UPDATE AD_Column SET AD_Reference_ID=17, AD_Reference_Value_ID=347,Updated=TO_TIMESTAMP('2009-04-22 20:16:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=57378
;

-- Apr 22, 2009 8:16:58 PM ECT
-- View Manufacturing Management
UPDATE AD_Column SET AD_Reference_ID=17, AD_Reference_Value_ID=348,Updated=TO_TIMESTAMP('2009-04-22 20:16:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=57379
;

-- Apr 22, 2009 8:17:13 PM ECT
-- View Manufacturing Management
UPDATE AD_Column SET AD_Reference_Value_ID=131,Updated=TO_TIMESTAMP('2009-04-22 20:17:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=57336
;

-- Apr 22, 2009 8:17:42 PM ECT
-- View Manufacturing Management
UPDATE AD_Column SET AD_Reference_ID=17, AD_Reference_Value_ID=154,Updated=TO_TIMESTAMP('2009-04-22 20:17:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=57359
;

-- Apr 22, 2009 8:18:01 PM ECT
-- View Manufacturing Management
UPDATE AD_Column SET AD_Reference_Value_ID=135,Updated=TO_TIMESTAMP('2009-04-22 20:18:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=57515
;

-- 28/04/2009 01:40:25 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET XSpace=0, YSpace=0, PrintNameSuffix=NULL,Updated=TO_TIMESTAMP('2009-04-28 13:40:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50076
;

-- 28/04/2009 01:40:25 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50076
;

-- 28/04/2009 01:46:47 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET AD_Column_ID=57280, XSpace=0, YSpace=0,Updated=TO_TIMESTAMP('2009-04-28 13:46:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50076
;

-- 28/04/2009 01:51:23 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET XSpace=0, YSpace=0, PrintNameSuffix=NULL,Updated=TO_TIMESTAMP('2009-04-28 13:51:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50076
;

-- 28/04/2009 01:51:23 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50076
;

-- 28/04/2009 01:51:31 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, IsGroupBy='N', AD_Column_ID=57289, XSpace=0, IsPageBreak='N', YSpace=0,Updated=TO_TIMESTAMP('2009-04-28 13:51:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50057
;

-- 28/04/2009 01:51:45 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, IsGroupBy='N', AD_Column_ID=57281, XSpace=0, IsPageBreak='N', YSpace=0,Updated=TO_TIMESTAMP('2009-04-28 13:51:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50078
;

-- 28/04/2009 01:52:03 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57320, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 13:52:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50080
;

-- 28/04/2009 01:52:12 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, IsGroupBy='N', AD_Column_ID=57286, XSpace=0, IsPageBreak='N', YSpace=0,Updated=TO_TIMESTAMP('2009-04-28 13:52:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50081
;

-- 28/04/2009 01:52:20 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, IsGroupBy='N', AD_Column_ID=57299, XSpace=0, IsPageBreak='N', YSpace=0,Updated=TO_TIMESTAMP('2009-04-28 13:52:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50082
;

-- 28/04/2009 01:52:34 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, IsGroupBy='N', AD_Column_ID=57312, XSpace=0, IsPageBreak='N', YSpace=0,Updated=TO_TIMESTAMP('2009-04-28 13:52:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50083
;

-- 28/04/2009 01:52:46 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, IsGroupBy='N', AD_Column_ID=57319, XSpace=0, IsPageBreak='N', YSpace=0,Updated=TO_TIMESTAMP('2009-04-28 13:52:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50084
;

-- 28/04/2009 01:52:56 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, IsGroupBy='N', AD_Column_ID=57321, XSpace=0, IsPageBreak='N', YSpace=0,Updated=TO_TIMESTAMP('2009-04-28 13:52:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50085
;

-- 28/04/2009 01:53:05 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, IsGroupBy='N', AD_Column_ID=57309, XSpace=0, IsPageBreak='N', YSpace=0,Updated=TO_TIMESTAMP('2009-04-28 13:53:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50086
;

-- 28/04/2009 01:53:16 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, IsGroupBy='N', AD_Column_ID=57308, XSpace=0, IsPageBreak='N', YSpace=0,Updated=TO_TIMESTAMP('2009-04-28 13:53:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50079
;

-- 28/04/2009 01:53:25 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, IsGroupBy='N', AD_Column_ID=57317, XSpace=0, IsPageBreak='N', YSpace=0,Updated=TO_TIMESTAMP('2009-04-28 13:53:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50087
;

-- 28/04/2009 01:53:34 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, IsGroupBy='N', AD_Column_ID=57318, XSpace=0, IsPageBreak='N', YSpace=0,Updated=TO_TIMESTAMP('2009-04-28 13:53:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50088
;

-- 28/04/2009 01:53:44 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, IsGroupBy='N', AD_Column_ID=57291, XSpace=0, IsPageBreak='N', YSpace=0,Updated=TO_TIMESTAMP('2009-04-28 13:53:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50089
;

-- 28/04/2009 01:53:53 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, IsGroupBy='N', AD_Column_ID=57292, XSpace=0, IsPageBreak='N', YSpace=0,Updated=TO_TIMESTAMP('2009-04-28 13:53:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50090
;

-- 28/04/2009 01:54:06 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, IsGroupBy='N', AD_Column_ID=57315, XSpace=0, IsPageBreak='N', YSpace=0,Updated=TO_TIMESTAMP('2009-04-28 13:54:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50091
;

-- 28/04/2009 01:54:16 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, IsGroupBy='N', AD_Column_ID=57316, XSpace=0, IsPageBreak='N', YSpace=0,Updated=TO_TIMESTAMP('2009-04-28 13:54:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50092
;

-- 28/04/2009 01:54:24 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, IsGroupBy='N', AD_Column_ID=57313, XSpace=0, IsPageBreak='N', YSpace=0,Updated=TO_TIMESTAMP('2009-04-28 13:54:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50093
;

-- 28/04/2009 01:54:32 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, IsGroupBy='N', AD_Column_ID=57314, XSpace=0, IsPageBreak='N', YSpace=0,Updated=TO_TIMESTAMP('2009-04-28 13:54:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50094
;

-- 28/04/2009 01:54:40 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57296, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 13:54:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50095
;

-- 28/04/2009 01:54:51 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57279, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 13:54:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50077
;

-- 28/04/2009 01:55:01 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57279, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 13:55:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50096
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=250,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50041
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=260,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50071
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=270,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50045
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=280,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50043
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=290,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50070
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=300,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50037
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=310,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50050
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=320,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50055
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=330,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50052
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=340,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50068
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=350,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50042
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=360,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50051
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=370,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50049
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=380,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50060
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=390,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50058
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=400,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50063
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=410,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50039
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=420,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50056
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=430,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50067
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=440,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50038
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=450,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50065
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=460,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50046
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=470,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50036
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=480,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50064
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=490,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50048
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=500,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50069
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=510,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50062
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=520,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50044
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=530,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50032
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=540,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50033
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=550,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50034
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=560,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50047
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=570,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50035
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=580,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50040
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=590,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50072
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=600,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50066
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=610,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50053
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=620,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50054
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=630,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50073
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=640,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50074
;

-- 28/04/2009 01:55:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SeqNo=650,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50061
;

-- 28/04/2009 01:55:51 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57273, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 13:55:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50041
;

-- 28/04/2009 01:55:59 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57325, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 13:55:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50071
;

-- 28/04/2009 01:56:25 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57300, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 13:56:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50043
;

-- 28/04/2009 01:56:31 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57323, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 13:56:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50070
;

-- 28/04/2009 01:56:38 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57271, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 13:56:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50037
;

-- 28/04/2009 01:56:56 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57274, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 13:56:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50055
;

-- 28/04/2009 01:57:02 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57275, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 13:57:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50052
;

-- 28/04/2009 01:57:15 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57310, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 13:57:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50068
;

-- 28/04/2009 01:57:24 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57298, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 13:57:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50042
;

-- 28/04/2009 01:57:53 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57287, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 13:57:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50049
;

-- 28/04/2009 01:58:02 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57293, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 13:58:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50060
;

-- 28/04/2009 01:58:11 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57294, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 13:58:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50058
;

-- 28/04/2009 01:58:31 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57295, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 13:58:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50039
;

-- 28/04/2009 01:58:39 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57283, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 13:58:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50056
;

-- 28/04/2009 01:58:57 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57312, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 13:58:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50067
;

-- 28/04/2009 01:59:06 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57272, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 13:59:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50038
;

-- 28/04/2009 01:59:28 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57307, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 13:59:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50036
;

-- 28/04/2009 01:59:51 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57324, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 13:59:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50069
;

-- 28/04/2009 02:00:11 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57305, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 14:00:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50062
;

-- 28/04/2009 02:00:20 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57302, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 14:00:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50044
;

-- 28/04/2009 02:00:32 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57306, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 14:00:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50032
;

-- 28/04/2009 02:00:59 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57297, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 14:00:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50035
;

-- 28/04/2009 02:01:35 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57301, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 14:01:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50066
;

-- 28/04/2009 02:01:46 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57276, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 14:01:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50053
;

-- 28/04/2009 02:01:54 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57277, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 14:01:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50054
;

-- 28/04/2009 02:02:28 PM ECT
-- Multi Language Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57322, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 14:02:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50061
;

-- 28/04/2009 02:08:53 PM ECT
-- Multi Language Format
DELETE FROM AD_PrintFormatItem_Trl WHERE AD_PrintFormatItem_ID=50047
;

-- 28/04/2009 02:08:53 PM ECT
-- Multi Language Format
DELETE FROM AD_PrintFormatItem WHERE AD_PrintFormatItem_ID=50047
;

-- 28/04/2009 02:16:04 PM ECT
-- Multi Lenguage Format
DELETE FROM AD_PrintFormatItem_Trl WHERE AD_PrintFormatItem_ID=50050
;

-- 28/04/2009 02:16:04 PM ECT
-- Multi Lenguage Format
DELETE FROM AD_PrintFormatItem WHERE AD_PrintFormatItem_ID=50050
;

-- 28/04/2009 02:19:01 PM ECT
-- Multi Lenguage Format
DELETE FROM AD_PrintFormatItem_Trl WHERE AD_PrintFormatItem_ID=50063
;

-- 28/04/2009 02:19:01 PM ECT
-- Multi Lenguage Format
DELETE FROM AD_PrintFormatItem WHERE AD_PrintFormatItem_ID=50063
;

-- 28/04/2009 02:19:59 PM ECT
-- Multi Lenguage Format
DELETE FROM AD_PrintFormatItem_Trl WHERE AD_PrintFormatItem_ID=50064
;

-- 28/04/2009 02:19:59 PM ECT
-- Multi Lenguage Format
DELETE FROM AD_PrintFormatItem WHERE AD_PrintFormatItem_ID=50064
;

-- 28/04/2009 02:20:09 PM ECT
-- Multi Lenguage Format
DELETE FROM AD_PrintFormatItem_Trl WHERE AD_PrintFormatItem_ID=50048
;

-- 28/04/2009 02:20:09 PM ECT
-- Multi Lenguage Format
DELETE FROM AD_PrintFormatItem WHERE AD_PrintFormatItem_ID=50048
;

-- 28/04/2009 02:22:53 PM ECT
-- Advanced Search
DELETE FROM AD_PrintFormatItem_Trl WHERE AD_PrintFormatItem_ID=50033
;

-- 28/04/2009 02:22:53 PM ECT
-- Advanced Search
DELETE FROM AD_PrintFormatItem WHERE AD_PrintFormatItem_ID=50033
;

UPDATE AD_PrintFormat SET AD_Table_ID=53196 WHERE AD_PrintFormat_ID=50001;
UPDATE AD_PrintFormat SET AD_Table_ID=53200 WHERE AD_PrintFormat_ID=50037;
UPDATE AD_PrintFormat SET AD_Table_ID=53198 WHERE AD_PrintFormat_ID=50002;
 
-- 28/04/2009 02:51:27 PM ECT
-- Multi Lenguage Format
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57541,613,0,19,53196,'User1_ID',TO_TIMESTAMP('2009-04-28 14:51:23','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #1','EE01',10,'The user defined element displays the optional elements that have been defined for this account combination.','Y','N','N','N','N','N','N','N','N','N','User List 1',TO_TIMESTAMP('2009-04-28 14:51:23','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- 28/04/2009 02:51:27 PM ECT
-- Multi Lenguage Format
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57541 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 28/04/2009 02:51:27 PM ECT
-- Multi Lenguage Format
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57542,614,0,19,53196,'User2_ID',TO_TIMESTAMP('2009-04-28 14:51:27','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #2','EE01',10,'The user defined element displays the optional elements that have been defined for this account combination.','Y','N','N','N','N','N','N','N','N','N','User List 2',TO_TIMESTAMP('2009-04-28 14:51:27','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- 28/04/2009 02:51:27 PM ECT
-- Multi Lenguage Format
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57542 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 28/04/2009 02:51:28 PM ECT
-- Multi Lenguage Format
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57543,112,0,19,53196,'AD_OrgTrx_ID',TO_TIMESTAMP('2009-04-28 14:51:27','YYYY-MM-DD HH24:MI:SS'),0,'Performing or initiating organization','EE01',10,'The organization which performs or initiates this transaction (for another organization).  The owning Organization may not be the transaction organization in a service bureau environment, with centralized services, and inter-organization transactions.','Y','N','N','N','N','N','N','N','N','N','Trx Organization',TO_TIMESTAMP('2009-04-28 14:51:27','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- 28/04/2009 02:51:28 PM ECT
-- Multi Lenguage Format
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57543 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 28/04/2009 02:51:29 PM ECT
-- Multi Lenguage Format
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57544,197,0,19,53196,'C_DocTypeTarget_ID',TO_TIMESTAMP('2009-04-28 14:51:28','YYYY-MM-DD HH24:MI:SS'),0,'Target document type for conversing documents','EE01',10,'You can convert document types (e.g. from Offer to Order or Invoice).  The conversion is then reflected in the current type.  This processing is initiated by selecting the appropriate Document Action.','Y','N','N','N','N','N','N','N','N','N','Target Document Type',TO_TIMESTAMP('2009-04-28 14:51:28','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- 28/04/2009 02:51:29 PM ECT
-- Multi Lenguage Format
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57544 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 28/04/2009 02:51:30 PM ECT
-- Multi Lenguage Format
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57545,2457,0,10,53196,'ScheduleType',TO_TIMESTAMP('2009-04-28 14:51:29','YYYY-MM-DD HH24:MI:SS'),0,'Type of schedule','EE01',1,'Define the method how the next occurance is calculated','Y','N','N','N','N','N','N','N','N','N','Schedule Type',TO_TIMESTAMP('2009-04-28 14:51:29','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- 28/04/2009 02:51:30 PM ECT
-- Multi Lenguage Format
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57545 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 28/04/2009 02:51:30 PM ECT
-- Multi Lenguage Format
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57546,351,0,20,53196,'IsApproved',TO_TIMESTAMP('2009-04-28 14:51:30','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if this document requires approval','EE01',1,'The Approved checkbox indicates if this document requires approval before it can be processed.','Y','N','N','N','N','N','N','N','N','N','Approved',TO_TIMESTAMP('2009-04-28 14:51:30','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- 28/04/2009 02:51:30 PM ECT
-- Multi Lenguage Format
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57546 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 28/04/2009 02:51:31 PM ECT
-- Multi Lenguage Format
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57547,287,0,17,53196,'DocAction',TO_TIMESTAMP('2009-04-28 14:51:30','YYYY-MM-DD HH24:MI:SS'),0,'The targeted status of the document','EE01',2,'You find the current status in the Document Status field. The options are listed in a popup','Y','N','N','N','N','N','N','N','N','N','Document Action',TO_TIMESTAMP('2009-04-28 14:51:30','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- 28/04/2009 02:51:31 PM ECT
-- Multi Lenguage Format
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57547 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 28/04/2009 02:51:32 PM ECT
-- Multi Lenguage Format
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57548,1308,0,20,53196,'Posted',TO_TIMESTAMP('2009-04-28 14:51:31','YYYY-MM-DD HH24:MI:SS'),0,'Posting status','EE01',1,'The Posted field indicates the status of the Generation of General Ledger Accounting Lines ','Y','N','N','N','N','N','N','N','N','N','Posted',TO_TIMESTAMP('2009-04-28 14:51:31','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- 28/04/2009 02:51:32 PM ECT
-- Multi Lenguage Format
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57548 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 28/04/2009 02:51:33 PM ECT
-- Multi Lenguage Format
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57549,399,0,20,53196,'IsPrinted',TO_TIMESTAMP('2009-04-28 14:51:32','YYYY-MM-DD HH24:MI:SS'),0,'Indicates if this document / line is printed','EE01',1,'The Printed checkbox indicates if this document or line will included when printing.','Y','N','N','N','N','N','N','N','N','N','Printed',TO_TIMESTAMP('2009-04-28 14:51:32','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- 28/04/2009 02:51:33 PM ECT
-- Multi Lenguage Format
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57549 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 28/04/2009 02:51:33 PM ECT
-- Multi Lenguage Format
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57550,52020,0,10,53196,'OrderType',TO_TIMESTAMP('2009-04-28 14:51:33','YYYY-MM-DD HH24:MI:SS'),0,'EE01',1,'Y','N','N','N','N','N','N','N','N','N','OrderType',TO_TIMESTAMP('2009-04-28 14:51:33','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- 28/04/2009 02:51:33 PM ECT
-- Multi Lenguage Format
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57550 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 28/04/2009 02:52:39 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57546, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 14:52:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50045
;

-- 28/04/2009 02:52:58 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57547, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 14:52:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50051
;

-- 28/04/2009 02:53:22 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57548, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 14:53:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50065
;

-- 28/04/2009 02:53:30 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57549, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 14:53:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50046
;

-- 28/04/2009 02:53:46 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57545, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 14:53:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50034
;

-- 28/04/2009 02:53:54 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57544, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 14:53:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50040
;

-- 28/04/2009 02:54:01 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57543, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 14:54:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50072
;

-- 28/04/2009 02:54:11 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57541, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 14:54:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50073
;

-- 28/04/2009 02:54:18 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57542, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 14:54:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50074
;

-- 28/04/2009 02:55:44 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, IsGroupBy='N', AD_Column_ID=57311, XSpace=0, IsPageBreak='N', YSpace=0,Updated=TO_TIMESTAMP('2009-04-28 14:55:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50083
;

-- 28/04/2009 02:57:15 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57550, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 14:57:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50056
;

-- 28/04/2009 02:57:56 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50041
;

-- 28/04/2009 02:57:56 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50071
;

-- 28/04/2009 02:57:56 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50045
;

-- 28/04/2009 02:57:56 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50043
;

-- 28/04/2009 02:57:56 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50070
;

-- 28/04/2009 02:57:56 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50037
;

-- 28/04/2009 02:57:56 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50055
;

-- 28/04/2009 02:57:56 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50052
;

-- 28/04/2009 02:57:56 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50068
;

-- 28/04/2009 02:57:56 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50042
;

-- 28/04/2009 02:57:56 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50051
;

-- 28/04/2009 02:57:56 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50049
;

-- 28/04/2009 02:57:56 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50060
;

-- 28/04/2009 02:57:56 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50058
;

-- 28/04/2009 02:57:56 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50039
;

-- 28/04/2009 02:57:56 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50056
;

-- 28/04/2009 02:57:56 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50067
;

-- 28/04/2009 02:57:56 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50038
;

-- 28/04/2009 02:57:56 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50065
;

-- 28/04/2009 02:57:56 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50046
;

-- 28/04/2009 02:57:56 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50036
;

-- 28/04/2009 02:57:56 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50069
;

-- 28/04/2009 02:57:56 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50062
;

-- 28/04/2009 02:57:56 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50044
;

-- 28/04/2009 02:57:56 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50032
;

-- 28/04/2009 02:57:57 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50034
;

-- 28/04/2009 02:57:57 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50035
;

-- 28/04/2009 02:57:57 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50040
;

-- 28/04/2009 02:57:57 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50072
;

-- 28/04/2009 02:57:57 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50066
;

-- 28/04/2009 02:57:57 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50053
;

-- 28/04/2009 02:57:57 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50054
;

-- 28/04/2009 02:57:57 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50073
;

-- 28/04/2009 02:57:57 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50074
;

-- 28/04/2009 02:57:57 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50061
;

-- 28/04/2009 05:44:05 PM ECT
-- Multi Lenguage Format
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=110,Updated=TO_TIMESTAMP('2009-04-28 17:44:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=57289
;

-- 28/04/2009 05:44:53 PM ECT
-- Multi Lenguage Format
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=133,Updated=TO_TIMESTAMP('2009-04-28 17:44:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=57286
;

-- 28/04/2009 06:18:47 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57531, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 18:18:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50980
;

-- 28/04/2009 06:18:54 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57537, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 18:18:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50981
;

-- 28/04/2009 06:19:00 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57507, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 18:19:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50984
;

-- 28/04/2009 06:19:07 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57514, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 18:19:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50985
;

-- 28/04/2009 06:19:14 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57511, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 18:19:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50969
;

-- 28/04/2009 06:19:27 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57511, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 18:19:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50968
;

-- 28/04/2009 06:19:36 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57538, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 18:19:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50991
;

-- 28/04/2009 06:19:48 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57532, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 18:19:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50992
;

-- 28/04/2009 06:19:55 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57516, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 18:19:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50972
;

-- 28/04/2009 06:20:02 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57518, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 18:20:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50974
;

-- 28/04/2009 06:20:10 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57522, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 18:20:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50989
;

-- 28/04/2009 06:20:16 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57530, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 18:20:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50990
;

-- 28/04/2009 06:20:48 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57521, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 18:20:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50988
;

-- 28/04/2009 06:20:56 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57508, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 18:20:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50986
;

-- 28/04/2009 06:25:45 PM ECT
-- Multi Lenguage Format
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57551,289,0,17,53200,'DocStatus',TO_TIMESTAMP('2009-04-28 18:25:42','YYYY-MM-DD HH24:MI:SS'),0,'The current status of the document','EE01',2,'The Document Status indicates the status of a document at this time.  If you want to change the document status, use the Document Action field','Y','N','N','N','N','N','N','N','N','Y','Document Status',TO_TIMESTAMP('2009-04-28 18:25:42','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- 28/04/2009 06:25:45 PM ECT
-- Multi Lenguage Format
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57551 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 28/04/2009 06:26:09 PM ECT
-- Multi Lenguage Format
UPDATE AD_Column SET AD_Reference_Value_ID=131,Updated=TO_TIMESTAMP('2009-04-28 18:26:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=57551
;

-- 28/04/2009 06:26:31 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57520, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 18:26:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50987
;

-- 28/04/2009 06:26:44 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57551, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 18:26:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50971
;

-- 28/04/2009 06:27:02 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=170,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50964
;

-- 28/04/2009 06:27:02 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=180,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50965
;

-- 28/04/2009 06:27:02 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=190,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50966
;

-- 28/04/2009 06:27:02 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=200,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50967
;

-- 28/04/2009 06:27:02 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=210,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50970
;

-- 28/04/2009 06:27:02 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=220,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50973
;

-- 28/04/2009 06:27:02 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=230,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50975
;

-- 28/04/2009 06:27:02 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=240,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50976
;

-- 28/04/2009 06:27:02 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=250,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50977
;

-- 28/04/2009 06:27:02 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=260,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50978
;

-- 28/04/2009 06:27:02 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=270,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50979
;

-- 28/04/2009 06:27:02 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=280,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50982
;

-- 28/04/2009 06:27:02 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=290,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50983
;

-- 28/04/2009 06:28:01 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57502, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 18:28:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50964
;

-- 28/04/2009 06:28:21 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57500, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 18:28:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50965
;

-- 28/04/2009 06:28:27 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57503, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 18:28:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50966
;

-- 28/04/2009 06:28:32 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57504, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 18:28:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50967
;

-- 28/04/2009 06:30:06 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57527, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 18:30:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50970
;

-- 28/04/2009 06:30:13 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57517, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 18:30:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50973
;

-- 28/04/2009 06:30:19 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57524, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 18:30:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50975
;

-- 28/04/2009 06:30:40 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57525, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 18:30:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50976
;

-- 28/04/2009 06:31:44 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57501, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 18:31:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50977
;

-- 28/04/2009 06:32:08 PM ECT
-- Multi Lenguage Format
DELETE FROM AD_PrintFormatItem_Trl WHERE AD_PrintFormatItem_ID=50978
;

-- 28/04/2009 06:32:08 PM ECT
-- Multi Lenguage Format
DELETE FROM AD_PrintFormatItem WHERE AD_PrintFormatItem_ID=50978
;

-- 28/04/2009 06:32:14 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57529, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 18:32:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50979
;

-- 28/04/2009 06:32:20 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57505, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 18:32:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50982
;

-- 28/04/2009 06:32:28 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57506, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 18:32:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50983
;

-- 28/04/2009 06:33:15 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50964
;

-- 28/04/2009 06:33:15 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50965
;

-- 28/04/2009 06:33:15 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50966
;

-- 28/04/2009 06:33:15 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50967
;

-- 28/04/2009 06:33:15 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50970
;

-- 28/04/2009 06:33:15 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50973
;

-- 28/04/2009 06:33:15 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50975
;

-- 28/04/2009 06:33:15 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50976
;

-- 28/04/2009 06:33:15 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50977
;

-- 28/04/2009 06:33:15 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50979
;

-- 28/04/2009 06:33:15 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50982
;

-- 28/04/2009 06:33:15 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50983
;

-- 28/04/2009 07:00:31 PM ECT
-- Multi Lenguage Format
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57552,2238,0,29,53198,'QtyAvailable',TO_TIMESTAMP('2009-04-28 19:00:29','YYYY-MM-DD HH24:MI:SS'),0,'Available Quantity (On Hand - Reserved)','EE01',131089,'Quantity available to promise = On Hand minus Reserved Quantity','Y','N','N','N','N','N','N','N','N','Y','Available Quantity',TO_TIMESTAMP('2009-04-28 19:00:29','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- 28/04/2009 07:00:31 PM ECT
-- Multi Lenguage Format
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57552 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 28/04/2009 07:00:33 PM ECT
-- Multi Lenguage Format
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57553,530,0,29,53198,'QtyOnHand',TO_TIMESTAMP('2009-04-28 19:00:31','YYYY-MM-DD HH24:MI:SS'),0,'On Hand Quantity','EE01',131089,'The On Hand Quantity indicates the quantity of a product that is on hand in a warehouse.','Y','N','N','N','N','N','N','N','N','Y','On Hand Quantity',TO_TIMESTAMP('2009-04-28 19:00:31','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- 28/04/2009 07:00:33 PM ECT
-- Multi Lenguage Format
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57553 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 28/04/2009 07:00:34 PM ECT
-- Multi Lenguage Format
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57554,53243,0,29,53198,'QtyBatchSize',TO_TIMESTAMP('2009-04-28 19:00:33','YYYY-MM-DD HH24:MI:SS'),0,'EE01',131089,'Y','N','N','N','N','N','N','N','N','Y','Qty Batch Size',TO_TIMESTAMP('2009-04-28 19:00:33','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- 28/04/2009 07:00:34 PM ECT
-- Multi Lenguage Format
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57554 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 28/04/2009 07:04:18 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57397, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 19:04:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50117
;

-- 28/04/2009 07:04:23 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57399, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 19:04:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50118
;

-- 28/04/2009 07:04:33 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57554, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 19:04:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50119
;

-- 28/04/2009 07:04:44 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57421, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 19:04:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50120
;

-- 28/04/2009 07:05:00 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=20,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50118
;

-- 28/04/2009 07:05:00 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=30,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50119
;

-- 28/04/2009 07:05:00 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=40,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50120
;

-- 28/04/2009 07:05:00 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=50,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50111
;

-- 28/04/2009 07:05:00 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=60,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50113
;

-- 28/04/2009 07:05:00 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=70,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50107
;

-- 28/04/2009 07:05:01 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=80,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50102
;

-- 28/04/2009 07:05:01 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=90,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50106
;

-- 28/04/2009 07:05:01 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=100,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50109
;

-- 28/04/2009 07:05:01 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=110,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50110
;

-- 28/04/2009 07:05:01 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=120,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50104
;

-- 28/04/2009 07:05:01 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=130,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50099
;

-- 28/04/2009 07:05:01 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=140,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50115
;

-- 28/04/2009 07:05:01 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=150,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50103
;

-- 28/04/2009 07:05:01 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=160,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50098
;

-- 28/04/2009 07:05:01 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=170,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50108
;

-- 28/04/2009 07:05:01 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=180,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50097
;

-- 28/04/2009 07:05:01 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=190,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50114
;

-- 28/04/2009 07:05:01 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=200,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50105
;

-- 28/04/2009 07:05:01 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=210,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50116
;

-- 28/04/2009 07:05:01 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=220,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50100
;

-- 28/04/2009 07:05:01 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=230,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50101
;

-- 28/04/2009 07:05:01 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=240,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50112
;

-- 28/04/2009 07:05:26 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57390, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 19:05:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50111
;

-- 28/04/2009 07:05:37 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57552, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 19:05:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50113
;

-- 28/04/2009 07:05:43 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57388, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 19:05:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50107
;

-- 28/04/2009 07:06:07 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57416, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 19:06:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50102
;

-- 28/04/2009 07:06:12 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57400, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 19:06:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50106
;

-- 28/04/2009 07:06:19 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57391, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 19:06:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50109
;

-- 28/04/2009 07:06:24 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57392, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 19:06:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50110
;

-- 28/04/2009 07:06:32 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57404, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 19:06:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50104
;

-- 28/04/2009 07:06:40 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57415, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 19:06:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50099
;

-- 28/04/2009 07:06:47 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57553, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 19:06:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50115
;

-- 28/04/2009 07:06:56 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57414, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 19:06:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50103
;

-- 28/04/2009 07:07:36 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57389, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 19:07:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50108
;

-- 28/04/2009 07:07:44 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57417, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 19:07:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50097
;

-- 28/04/2009 07:08:14 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57405, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 19:08:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50105
;

-- 28/04/2009 07:08:24 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57422, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 19:08:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50116
;

-- 28/04/2009 07:08:31 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57393, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 19:08:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50100
;

-- 28/04/2009 07:08:37 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57394, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 19:08:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50101
;

-- 28/04/2009 07:08:44 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57412, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 19:08:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50112
;

-- 28/04/2009 07:10:46 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', AD_Column_ID=57413, YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 19:10:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50098
;

-- 28/04/2009 07:11:07 PM ECT
-- Multi Lenguage Format
DELETE FROM AD_PrintFormatItem_Trl WHERE AD_PrintFormatItem_ID=50114
;

-- 28/04/2009 07:11:07 PM ECT
-- Multi Lenguage Format
DELETE FROM AD_PrintFormatItem WHERE AD_PrintFormatItem_ID=50114
;

-- 28/04/2009 07:11:55 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50111
;

-- 28/04/2009 07:11:55 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50113
;

-- 28/04/2009 07:11:55 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50107
;

-- 28/04/2009 07:11:55 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50102
;

-- 28/04/2009 07:11:55 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50106
;

-- 28/04/2009 07:11:55 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50109
;

-- 28/04/2009 07:11:55 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50110
;

-- 28/04/2009 07:11:55 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50104
;

-- 28/04/2009 07:11:55 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50099
;

-- 28/04/2009 07:11:55 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50115
;

-- 28/04/2009 07:11:55 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50103
;

-- 28/04/2009 07:11:55 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50098
;

-- 28/04/2009 07:11:55 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50108
;

-- 28/04/2009 07:11:55 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50097
;

-- 28/04/2009 07:11:55 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50105
;

-- 28/04/2009 07:11:55 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50116
;

-- 28/04/2009 07:11:55 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50100
;

-- 28/04/2009 07:11:55 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50101
;

-- 28/04/2009 07:11:55 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50112
;

-- 28/04/2009 07:13:43 PM ECT
-- Multi Lenguage Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,AD_PrintColor_ID,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,AD_PrintFont_ID,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsCentrallyMaintained,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,IsFilledRectangle,LineWidth,IsImageField,IsSuppressRepeats) VALUES (TO_TIMESTAMP('2009-04-28 19:13:42','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','Y','Y','C',50002,'N',0,100,'F','N',50,57395,'X',0,0,0,'D','Y','Description',0,130,0,'N','N','N','N','Y','N','Description','N',0,TO_TIMESTAMP('2009-04-28 19:13:42','YYYY-MM-DD HH24:MI:SS'),0,'N',51074,'Y',0,'N',20,'N','N','N','N','N',0,'N','N',1,'N','N')
;

-- 28/04/2009 07:13:43 PM ECT
-- Multi Lenguage Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51074 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- 28/04/2009 07:13:43 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=57395) WHERE AD_PrintFormatItem_ID = 51074 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=57395 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 51074) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- 28/04/2009 07:14:00 PM ECT
-- Multi Lenguage Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,AD_PrintColor_ID,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,PrintName,YPosition,AD_PrintFont_ID,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsCentrallyMaintained,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,IsFilledRectangle,LineWidth,IsImageField,IsSuppressRepeats) VALUES (TO_TIMESTAMP('2009-04-28 19:13:59','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','Y','Y','C',50002,'N',0,100,'F','N',60,57403,'X',0,0,0,'D','Y','Help',0,130,0,'N','N','N','N','Y','N','Help','N',0,TO_TIMESTAMP('2009-04-28 19:13:59','YYYY-MM-DD HH24:MI:SS'),0,'N',51075,'Y',0,'N',20,'N','N','N','N','N',0,'N','N',1,'N','N')
;

-- 28/04/2009 07:14:00 PM ECT
-- Multi Lenguage Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51075 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- 28/04/2009 07:14:00 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=57403) WHERE AD_PrintFormatItem_ID = 51075 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=57403 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 51075) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- 28/04/2009 07:14:12 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=20,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51074
;

-- 28/04/2009 07:14:12 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=30,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51075
;

-- 28/04/2009 07:14:12 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=40,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50118
;

-- 28/04/2009 07:14:12 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=50,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50119
;

-- 28/04/2009 07:14:12 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=60,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50120
;

-- 28/04/2009 07:16:58 PM ECT
-- Multi Lenguage Format
INSERT INTO AD_PrintFormatItem (Updated,AD_Org_ID,SortNo,XPosition,IsOrderBy,IsCounted,IsPrinted,IsHeightOneLine,PrintAreaType,AD_PrintFormat_ID,IsGroupBy,MaxHeight,AD_PrintColor_ID,PrintFormatType,ImageIsAttached,SeqNo,AD_Column_ID,LineAlignmentType,AD_Client_ID,UpdatedBy,XSpace,FieldAlignmentType,IsRelativePosition,YPosition,AD_PrintFont_ID,CreatedBy,IsSummarized,IsSuppressNull,IsPageBreak,IsFixedWidth,IsNextLine,IsMinCalc,Name,IsAveraged,BelowColumn,Created,YSpace,IsSetNLPosition,AD_PrintFormatItem_ID,IsActive,MaxWidth,IsNextPage,RunningTotalLines,IsMaxCalc,IsRunningTotal,IsCentrallyMaintained,IsVarianceCalc,IsDeviationCalc,ArcDiameter,ShapeType,IsFilledRectangle,LineWidth,IsImageField,IsSuppressRepeats) VALUES (TO_TIMESTAMP('2009-04-28 19:16:57','YYYY-MM-DD HH24:MI:SS'),0,0,0,'N','N','Y','Y','C',50037,'N',0,100,'F','N',170,57519,'X',0,0,0,'D','Y',0,130,0,'N','N','N','N','Y','N','Help','N',0,TO_TIMESTAMP('2009-04-28 19:16:57','YYYY-MM-DD HH24:MI:SS'),0,'N',51076,'Y',0,'N',20,'N','N','N','N','N',0,'N','N',1,'N','N')
;

-- 28/04/2009 07:16:58 PM ECT
-- Multi Lenguage Format
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=51076 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- 28/04/2009 07:17:10 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=50,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=51076
;

-- 28/04/2009 07:17:10 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=60,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50969
;

-- 28/04/2009 07:17:10 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=70,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50968
;

-- 28/04/2009 07:17:10 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=80,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50991
;

-- 28/04/2009 07:17:10 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=90,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50992
;

-- 28/04/2009 07:17:10 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=100,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50972
;

-- 28/04/2009 07:17:10 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=110,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50974
;

-- 28/04/2009 07:17:10 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=120,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50989
;

-- 28/04/2009 07:17:10 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=130,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50990
;

-- 28/04/2009 07:17:10 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=140,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50971
;

-- 28/04/2009 07:17:10 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=150,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50987
;

-- 28/04/2009 07:17:10 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=160,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50988
;

-- 28/04/2009 07:17:10 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SeqNo=170,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50986
;

-- 28/04/2009 07:19:53 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', PrintName='Help', YPosition=0, IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 19:19:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51076
;

-- 28/04/2009 07:19:53 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=51076
;

-- 28/04/2009 07:20:47 PM ECT
-- Multi Lenguage Format
UPDATE AD_PrintFormatItem SET SortNo=0, XPosition=0, IsGroupBy='N', YPosition=0, IsSuppressNull='Y', IsPageBreak='N',Updated=TO_TIMESTAMP('2009-04-28 19:20:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=51076
;

-- 28/04/2009 01:18:54 PM ECT
-- Multi Lenguage Format
INSERT INTO AD_ReportView (AD_Client_ID,AD_Org_ID,AD_ReportView_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,0,53026,53196,TO_TIMESTAMP('2009-04-28 13:18:52','YYYY-MM-DD HH24:MI:SS'),100,'EE01','Y','PP_Order_Head_v',TO_TIMESTAMP('2009-04-28 13:18:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- May 15, 2009 9:11:40 AM ECT
-- Multi Lenguage Format
UPDATE AD_ReportView SET AD_Table_ID=53198, Name='PP_Order_BOMLine Packing', WhereClause='ComponentType=''PK''',Updated=TO_TIMESTAMP('2009-05-15 09:11:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_ReportView_ID=53026
;

-- May 15, 2009 9:34:35 AM ECT
-- Multi Lenguage Format
UPDATE AD_ReportView SET WhereClause='ComponentType IN(''PK'',''TL'')',Updated=TO_TIMESTAMP('2009-05-15 09:34:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_ReportView_ID=53026
;

-- May 15, 2009 9:35:23 AM ECT
-- Multi Lenguage Format
UPDATE AD_Process SET Help='Once the planned orders of manufacture generated by MRP, have been aprobed, has been reached the date of liberation and has been verified that the required components are in existence, the orders are emitted to the plant for its manufacture. If you wish to print the warehouse and shop floor documentation you must tick the adequate check box:

If you tick the Print Pick List check box you will get a report with the required components and quantities to help the warehouse clerk to issue the material to the shop floor. The next report is an example of this:

If you tick the Print Pack List check box you will get a report with the required components and containing the material with a Component Type of Packing and Tools recorded in the BOM Window.

Another report which is part of the Manufacturing Order Release Package is the Workflow(Routing). 

You can print this report with tick in the Print Workflow check box and it shows to the shop floor personnel the necessary steps for the manufacture of the product. 

This Report contains the place where the product should be made, the standard times, the tools and necessary devices. 

If you wish to print this report tick the check box Print Workflow.',Updated=TO_TIMESTAMP('2009-05-15 09:35:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_ID=53034
;

-- May 15, 2009 9:35:23 AM ECT
-- Multi Lenguage Format
UPDATE AD_Process_Trl SET IsTranslated='N' WHERE AD_Process_ID=53034
;

-- May 15, 2009 9:35:38 AM ECT
-- Multi Lenguage Format
UPDATE AD_Process_Para SET Name='Is Print Pack & Tools List',Updated=TO_TIMESTAMP('2009-05-15 09:35:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53066
;

-- May 15, 2009 9:35:38 AM ECT
-- Multi Lenguage Format
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53066
;
