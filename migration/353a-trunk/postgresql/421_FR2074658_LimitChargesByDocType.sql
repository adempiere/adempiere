-- 26/08/2008 10:38:53 PM COT
-- [ 2074658 ] Limit usage of charges by Document Type
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,CopyColumnsFromTable,Created,CreatedBy,Description,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,LoadSeq,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53145,'3','N',TO_TIMESTAMP('2008-08-26 22:38:52','YYYY-MM-DD HH24:MI:SS'),100,'Charge Type','D','N','Y','N','Y','N','N','N',0,'Charge Type','L','C_ChargeType',TO_TIMESTAMP('2008-08-26 22:38:52','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53145 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53166,TO_TIMESTAMP('2008-08-26 22:38:53','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table C_ChargeType',1,'Y','N','Y','Y','C_ChargeType','N',1000000,TO_TIMESTAMP('2008-08-26 22:38:53','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56303,102,0,19,53145,129,'AD_Client_ID',TO_TIMESTAMP('2008-08-26 22:40:19','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Client_ID@','Client/Tenant for this installation.','D',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','N','N','N','Client',0,TO_TIMESTAMP('2008-08-26 22:40:19','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56303 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56304,113,0,19,53145,104,'AD_Org_ID',TO_TIMESTAMP('2008-08-26 22:40:24','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Org_ID@','Organizational entity within client','D',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','N','N','N','Organization',0,TO_TIMESTAMP('2008-08-26 22:40:24','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56304 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56305,245,0,16,53145,'Created',TO_TIMESTAMP('2008-08-26 22:40:30','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','D',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','N','N','N','Created',0,TO_TIMESTAMP('2008-08-26 22:40:30','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56305 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56306,246,0,18,110,53145,'CreatedBy',TO_TIMESTAMP('2008-08-26 22:40:37','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','D',22,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','N','N','N','Created By',0,TO_TIMESTAMP('2008-08-26 22:40:37','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56306 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56307,275,0,10,53145,'Description',TO_TIMESTAMP('2008-08-26 22:40:43','YYYY-MM-DD HH24:MI:SS'),100,'Optional short description of the record','D',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','N','N','Y','Description',0,TO_TIMESTAMP('2008-08-26 22:40:43','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56307 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56308,326,0,14,53145,'Help',TO_TIMESTAMP('2008-08-26 22:40:47','YYYY-MM-DD HH24:MI:SS'),100,'Comment or Hint','D',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','N','N','Y','Comment/Help',0,TO_TIMESTAMP('2008-08-26 22:40:47','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56308 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56309,348,0,20,53145,'IsActive',TO_TIMESTAMP('2008-08-26 22:40:52','YYYY-MM-DD HH24:MI:SS'),100,'Y','The record is active in the system','D',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','N','N','Y','Active',0,TO_TIMESTAMP('2008-08-26 22:40:52','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56309 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53677,0,'C_ChargeType_ID',TO_TIMESTAMP('2008-08-26 22:40:58','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Charge Type','Charge Type',TO_TIMESTAMP('2008-08-26 22:40:58','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53677 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56310,53677,0,13,53145,'C_ChargeType_ID',TO_TIMESTAMP('2008-08-26 22:40:58','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','Y','Y','N','N','N','N','N','Charge Type',0,TO_TIMESTAMP('2008-08-26 22:40:58','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56310 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56311,469,0,10,53145,'Name',TO_TIMESTAMP('2008-08-26 22:41:08','YYYY-MM-DD HH24:MI:SS'),100,'Alphanumeric identifier of the entity','D',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','N','N','Y','Name',1,TO_TIMESTAMP('2008-08-26 22:41:08','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56311 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56312,607,0,16,53145,'Updated',TO_TIMESTAMP('2008-08-26 22:41:13','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','D',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','N','N','N','Updated',0,TO_TIMESTAMP('2008-08-26 22:41:13','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56312 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56313,608,0,18,110,53145,'UpdatedBy',TO_TIMESTAMP('2008-08-26 22:41:19','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','D',22,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','N','N','N','Updated By',0,TO_TIMESTAMP('2008-08-26 22:41:19','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56313 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56314,620,0,10,53145,'Value',TO_TIMESTAMP('2008-08-26 22:41:25','YYYY-MM-DD HH24:MI:SS'),100,'Search key for the record in the format required - must be unique','D',40,'A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','N','N','Y','N','N','N','N','Y','Search Key',0,TO_TIMESTAMP('2008-08-26 22:41:25','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56314 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

CREATE TABLE C_ChargeType (AD_Client_ID NUMERIC(10) DEFAULT NULL NOT NULL, AD_Org_ID NUMERIC(10) DEFAULT NULL NOT NULL, C_ChargeType_ID NUMERIC(10) NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, Description VARCHAR(255), Help VARCHAR(2000), IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, Name VARCHAR(60) NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, Value VARCHAR(40) NOT NULL, CONSTRAINT C_ChargeType_Key PRIMARY KEY (C_ChargeType_ID))
;

INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56315,53677,0,19,313,'C_ChargeType_ID',TO_TIMESTAMP('2008-08-26 22:47:11','YYYY-MM-DD HH24:MI:SS'),100,'D',10,'Y','N','N','N','N','N','N','N','N','N','Y','Charge Type',0,TO_TIMESTAMP('2008-08-26 22:47:11','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56315 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

ALTER TABLE C_Charge ADD COLUMN C_ChargeType_ID NUMERIC(10)
;

INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,CopyColumnsFromTable,Created,CreatedBy,Description,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,LoadSeq,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53146,'3','N',TO_TIMESTAMP('2008-08-26 22:48:46','YYYY-MM-DD HH24:MI:SS'),100,'Configuration table for charges to be used by document type','D','N','Y','N','Y','N','N','N',0,'Charge Type by Doc Type','L','C_ChargeType_DocType',TO_TIMESTAMP('2008-08-26 22:48:46','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53146 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53167,TO_TIMESTAMP('2008-08-26 22:48:51','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table C_ChargeType_DocType',1,'Y','N','Y','Y','C_ChargeType_DocType','N',1000000,TO_TIMESTAMP('2008-08-26 22:48:51','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56316,102,0,19,53146,'AD_Client_ID',TO_TIMESTAMP('2008-08-26 22:49:17','YYYY-MM-DD HH24:MI:SS'),100,'@AD_Client_ID@','Client/Tenant for this installation.','D',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','N','N','N','Client',0,TO_TIMESTAMP('2008-08-26 22:49:17','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56316 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56317,113,0,19,53146,104,'AD_Org_ID',TO_TIMESTAMP('2008-08-26 22:49:25','YYYY-MM-DD HH24:MI:SS'),100,'@AD_Org_ID@','Organizational entity within client','D',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','N','N','N','Organization',0,TO_TIMESTAMP('2008-08-26 22:49:25','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56317 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56318,123,0,19,53146,'AD_Role_ID',TO_TIMESTAMP('2008-08-26 22:49:35','YYYY-MM-DD HH24:MI:SS'),100,'Responsibility Role','D',22,'The Role determines security and access a user who has this Role will have in the System.','Y','N','N','N','N','Y','Y','N','N','N','N','Role',0,TO_TIMESTAMP('2008-08-26 22:49:35','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56318 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56319,143,0,19,53146,'AD_Window_ID',TO_TIMESTAMP('2008-08-26 22:49:41','YYYY-MM-DD HH24:MI:SS'),100,'Data entry or display window','D',22,'The Window field identifies a unique Window in the system.','Y','N','N','N','N','Y','Y','N','N','N','N','Window',0,TO_TIMESTAMP('2008-08-26 22:49:41','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56319 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56320,245,0,16,53146,'Created',TO_TIMESTAMP('2008-08-26 22:49:46','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','D',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','N','N','N','Created',0,TO_TIMESTAMP('2008-08-26 22:49:46','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56320 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56321,246,0,18,110,53146,'CreatedBy',TO_TIMESTAMP('2008-08-26 22:49:52','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','D',22,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','N','N','N','Created By',0,TO_TIMESTAMP('2008-08-26 22:49:52','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56321 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56322,348,0,20,53146,'IsActive',TO_TIMESTAMP('2008-08-26 22:49:57','YYYY-MM-DD HH24:MI:SS'),100,'Y','The record is active in the system','D',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','N','N','Y','Active',0,TO_TIMESTAMP('2008-08-26 22:49:57','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56322 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56323,406,0,20,53146,'IsReadWrite',TO_TIMESTAMP('2008-08-26 22:50:03','YYYY-MM-DD HH24:MI:SS'),100,'Field is read / write','D',1,'The Read Write indicates that this field may be read and updated.','Y','N','N','N','N','Y','N','N','N','N','Y','Read Write',0,TO_TIMESTAMP('2008-08-26 22:50:03','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56323 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56324,607,0,16,53146,'Updated',TO_TIMESTAMP('2008-08-26 22:50:08','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','D',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','N','N','N','Updated',0,TO_TIMESTAMP('2008-08-26 22:50:08','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56324 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56325,608,0,18,110,53146,'UpdatedBy',TO_TIMESTAMP('2008-08-26 22:50:13','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','D',22,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','N','N','N','Updated By',0,TO_TIMESTAMP('2008-08-26 22:50:13','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56325 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

UPDATE AD_Column SET AD_Element_ID=53677, ColumnName='C_ChargeType_ID', Description=NULL, Help=NULL, IsUpdateable='N', Name='Charge Type',Updated=TO_TIMESTAMP('2008-08-26 22:51:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56318
;

UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=56318
;

UPDATE AD_Field SET Name='Charge Type', Description=NULL, Help=NULL WHERE AD_Column_ID=56318 AND IsCentrallyMaintained='Y'
;

UPDATE AD_Column SET AD_Element_ID=196, ColumnName='C_DocType_ID', Description='Document type or rules', Help='The Document Type determines document sequence and processing rules', IsUpdateable='N', Name='Document Type',Updated=TO_TIMESTAMP('2008-08-26 22:52:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56319
;

UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=56319
;

UPDATE AD_Field SET Name='Document Type', Description='Document type or rules', Help='The Document Type determines document sequence and processing rules' WHERE AD_Column_ID=56319 AND IsCentrallyMaintained='Y'
;

INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53678,0,'IsAllowPositive',TO_TIMESTAMP('2008-08-26 22:54:09','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Allow Positive','Allow Positive',TO_TIMESTAMP('2008-08-26 22:54:09','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53678 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

UPDATE AD_Column SET AD_Element_ID=53678, ColumnName='IsAllowPositive', Description=NULL, Help=NULL, Name='Allow Positive',Updated=TO_TIMESTAMP('2008-08-26 22:54:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56323
;

UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=56323
;

UPDATE AD_Field SET Name='Allow Positive', Description=NULL, Help=NULL WHERE AD_Column_ID=56323 AND IsCentrallyMaintained='Y'
;

INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53679,0,'IsAllowNegative',TO_TIMESTAMP('2008-08-26 22:55:35','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Allow Negative','Allow Negative',TO_TIMESTAMP('2008-08-26 22:55:35','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53679 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56326,53679,0,20,53146,'IsAllowNegative',TO_TIMESTAMP('2008-08-26 22:56:29','YYYY-MM-DD HH24:MI:SS'),100,'D',1,'Y','N','N','N','N','Y','N','N','N','N','Y','Allow Negative',0,TO_TIMESTAMP('2008-08-26 22:56:29','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56326 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

UPDATE AD_Column SET DefaultValue='Y',Updated=TO_TIMESTAMP('2008-08-26 22:56:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56326
;

UPDATE AD_Field SET Name='Allow Negative', Description=NULL, Help=NULL WHERE AD_Column_ID=56326 AND IsCentrallyMaintained='Y'
;

UPDATE AD_Column SET DefaultValue='Y',Updated=TO_TIMESTAMP('2008-08-26 22:56:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56323
;

UPDATE AD_Field SET Name='Allow Positive', Description=NULL, Help=NULL WHERE AD_Column_ID=56323 AND IsCentrallyMaintained='Y'
;

CREATE TABLE C_ChargeType_DocType (AD_Client_ID NUMERIC(10) DEFAULT NULL NOT NULL, AD_Org_ID NUMERIC(10) DEFAULT NULL NOT NULL, C_ChargeType_ID NUMERIC(10) NOT NULL, C_DocType_ID NUMERIC(10) NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, IsAllowNegative CHAR(1) DEFAULT 'Y' CHECK (IsAllowNegative IN ('Y','N')) NOT NULL, IsAllowPositive CHAR(1) DEFAULT 'Y' CHECK (IsAllowPositive IN ('Y','N')) NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, CONSTRAINT C_ChargeType_DocType_Key PRIMARY KEY (C_ChargeType_ID, C_DocType_ID))
;

INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,EntityType,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WinHeight,WinWidth,WindowType) VALUES (0,0,53062,TO_TIMESTAMP('2008-08-26 22:58:29','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','N','Y','Charge Type','N',TO_TIMESTAMP('2008-08-26 22:58:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,'M')
;

INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53062 AND EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Window_ID!=t.AD_Window_ID)
;

INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,0,53062,TO_TIMESTAMP('2008-08-26 22:58:30','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_TIMESTAMP('2008-08-26 22:58:30','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,102,53062,TO_TIMESTAMP('2008-08-26 22:58:30','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_TIMESTAMP('2008-08-26 22:58:30','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,103,53062,TO_TIMESTAMP('2008-08-26 22:58:30','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_TIMESTAMP('2008-08-26 22:58:30','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,50001,53062,TO_TIMESTAMP('2008-08-26 22:58:30','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_TIMESTAMP('2008-08-26 22:58:30','YYYY-MM-DD HH24:MI:SS'),100)
;

UPDATE AD_Table SET AD_Window_ID=53062,Updated=TO_TIMESTAMP('2008-08-26 22:59:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=53146
;

UPDATE AD_Table SET AD_Window_ID=53062,Updated=TO_TIMESTAMP('2008-08-26 22:59:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=53145
;

INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,EntityType,HasTree,ImportFields,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53176,53145,53062,TO_TIMESTAMP('2008-08-26 23:00:39','YYYY-MM-DD HH24:MI:SS'),100,'D','N','N','Y','N','N','Y','N','Y','N','N','Charge Type','N',10,0,TO_TIMESTAMP('2008-08-26 23:00:39','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53176 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56309,56337,0,53176,TO_TIMESTAMP('2008-08-26 23:00:55','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system',1,'D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','N','Active',TO_TIMESTAMP('2008-08-26 23:00:55','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56337 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56310,56338,0,53176,TO_TIMESTAMP('2008-08-26 23:01:01','YYYY-MM-DD HH24:MI:SS'),100,22,'D','Y','Y','N','N','N','N','N','N','Charge Type',TO_TIMESTAMP('2008-08-26 23:01:01','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56338 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56303,56339,0,53176,TO_TIMESTAMP('2008-08-26 23:01:07','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.',22,'D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','N','Client',TO_TIMESTAMP('2008-08-26 23:01:07','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56339 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56308,56340,0,53176,TO_TIMESTAMP('2008-08-26 23:01:13','YYYY-MM-DD HH24:MI:SS'),100,'Comment or Hint',2000,'D','The Help field contains a hint, comment or help about the use of this item.','Y','Y','Y','N','N','N','N','N','Comment/Help',TO_TIMESTAMP('2008-08-26 23:01:13','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56340 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56307,56341,0,53176,TO_TIMESTAMP('2008-08-26 23:01:19','YYYY-MM-DD HH24:MI:SS'),100,'Optional short description of the record',255,'D','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','N','Description',TO_TIMESTAMP('2008-08-26 23:01:19','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56341 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56311,56342,0,53176,TO_TIMESTAMP('2008-08-26 23:01:25','YYYY-MM-DD HH24:MI:SS'),100,'Alphanumeric identifier of the entity',60,'D','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','N','Name',TO_TIMESTAMP('2008-08-26 23:01:25','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56342 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56304,56343,0,53176,TO_TIMESTAMP('2008-08-26 23:01:31','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client',22,'D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','N','N','Organization',TO_TIMESTAMP('2008-08-26 23:01:31','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56343 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56314,56344,0,53176,TO_TIMESTAMP('2008-08-26 23:01:37','YYYY-MM-DD HH24:MI:SS'),100,'Search key for the record in the format required - must be unique',40,'D','A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','N','N','Search Key',TO_TIMESTAMP('2008-08-26 23:01:37','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56344 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=56339
;

UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=56343
;

UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=56344
;

UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=56342
;

UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=56341
;

UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=56340
;

UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=56337
;

UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2008-08-26 23:02:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56343
;

UPDATE AD_Field SET DisplayLength=20,Updated=TO_TIMESTAMP('2008-08-26 23:02:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56344
;

INSERT INTO AD_Tab (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,EntityType,HasTree,ImportFields,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,56318,0,53177,53146,53062,TO_TIMESTAMP('2008-08-26 23:04:10','YYYY-MM-DD HH24:MI:SS'),100,'D','N','N','Y','N','N','Y','N','N','N','N','Charge Type by Doc Type','N',20,1,TO_TIMESTAMP('2008-08-26 23:04:10','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53177 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56322,56345,0,53177,TO_TIMESTAMP('2008-08-26 23:04:13','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system',1,'D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','N','Active',TO_TIMESTAMP('2008-08-26 23:04:13','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56345 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56326,56346,0,53177,TO_TIMESTAMP('2008-08-26 23:04:14','YYYY-MM-DD HH24:MI:SS'),100,1,'D','Y','Y','Y','N','N','N','N','N','Allow Negative',TO_TIMESTAMP('2008-08-26 23:04:14','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56346 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56323,56347,0,53177,TO_TIMESTAMP('2008-08-26 23:04:15','YYYY-MM-DD HH24:MI:SS'),100,1,'D','Y','Y','Y','N','N','N','N','N','Allow Positive',TO_TIMESTAMP('2008-08-26 23:04:15','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56347 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56318,56348,0,53177,TO_TIMESTAMP('2008-08-26 23:04:16','YYYY-MM-DD HH24:MI:SS'),100,22,'D','Y','Y','Y','N','N','N','N','N','Charge Type',TO_TIMESTAMP('2008-08-26 23:04:16','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56348 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56316,56349,0,53177,TO_TIMESTAMP('2008-08-26 23:04:17','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.',22,'D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','N','Client',TO_TIMESTAMP('2008-08-26 23:04:17','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56349 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56319,56350,0,53177,TO_TIMESTAMP('2008-08-26 23:04:18','YYYY-MM-DD HH24:MI:SS'),100,'Document type or rules',22,'D','The Document Type determines document sequence and processing rules','Y','Y','Y','N','N','N','N','N','Document Type',TO_TIMESTAMP('2008-08-26 23:04:18','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56350 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56317,56351,0,53177,TO_TIMESTAMP('2008-08-26 23:04:19','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client',22,'D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','N','N','Organization',TO_TIMESTAMP('2008-08-26 23:04:19','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56351 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=56349
;

UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=56351
;

UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=56348
;

UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=56350
;

UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=56347
;

UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=56346
;

UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=56345
;

UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2008-08-26 23:05:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56351
;

UPDATE AD_Field SET DisplayLength=40,Updated=TO_TIMESTAMP('2008-08-26 23:05:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56350
;

UPDATE AD_Field SET DisplayLength=40,Updated=TO_TIMESTAMP('2008-08-26 23:06:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56348
;

UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2008-08-26 23:06:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56346
;

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56315,56352,0,237,TO_TIMESTAMP('2008-08-26 23:06:52','YYYY-MM-DD HH24:MI:SS'),100,10,'D','Y','Y','Y','N','N','N','N','N','Charge Type',TO_TIMESTAMP('2008-08-26 23:06:52','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56352 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

UPDATE AD_Field SET DisplayLength=40, SeqNo=110,Updated=TO_TIMESTAMP('2008-08-26 23:08:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56352
;

INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,"action",Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53189,0,53062,'W',TO_TIMESTAMP('2008-08-26 23:08:56','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','N','N','Charge Type',TO_TIMESTAMP('2008-08-26 23:08:56','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53189 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 53189, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53189)
;

UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=218
;

UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=153
;

UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=263
;

UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=166
;

UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=203
;

UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=236
;

UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=6, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=183
;

UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=7, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=160
;

UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=8, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=278
;

UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=9, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=345
;

UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=10, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53083
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=265
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=104
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=105
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=384
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=111
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=106
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=6, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=117
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=7, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=418
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=8, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=102
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=9, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=103
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=10, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=270
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=11, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=121
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=12, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=409
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=13, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=151
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=14, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53087
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=15, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=464
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=16, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=124
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=17, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=123
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=18, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=547
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=19, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=174
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=20, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53189
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=21, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=254
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=22, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=120
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=23, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=135
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=24, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=550
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=25, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=551
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=26, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=306
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=27, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=417
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=28, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=307
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=29, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=393
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=265
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=104
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=105
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=384
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=111
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=106
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=6, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=117
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=7, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=418
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=8, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=102
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=9, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=103
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=10, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=270
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=11, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=121
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=12, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=409
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=13, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=151
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=14, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53087
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=15, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=464
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=16, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=124
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=17, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=123
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=18, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=547
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=19, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=174
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=20, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53189
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=21, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=254
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=22, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=120
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=23, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=135
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=24, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=550
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=25, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=551
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=26, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=306
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=27, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=417
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=28, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=307
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=29, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=393
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=265
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=104
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=105
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=384
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=111
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=106
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=6, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=117
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=7, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=418
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=8, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=102
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=9, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=103
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=10, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=270
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=11, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=121
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=12, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=409
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=13, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=151
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=14, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53087
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=15, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=464
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=16, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=124
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=17, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=123
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=18, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=547
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=19, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53189
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=20, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=174
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=21, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=254
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=22, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=120
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=23, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=135
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=24, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=550
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=25, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=551
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=26, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=306
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=27, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=417
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=28, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=307
;

UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=29, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=393
;

INSERT INTO AD_Val_Rule (AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,AD_Client_ID,UpdatedBy) VALUES (0,52029,'C_Charge.C_Charge_ID IN (
	SELECT c.C_Charge_ID 
	FROM C_Charge c
	LEFT OUTER JOIN C_ChargeType ct ON (ct.C_ChargeType_ID = c.C_ChargeType_ID)
	LEFT OUTER JOIN C_ChargeType_Doctype ctd ON (ctd.C_ChargeType_ID = ct.C_ChargeType_ID)
	LEFT OUTER JOIN  C_DocType dt ON (dt.C_DocType_ID =ctd.C_DocType_ID)
	WHERE  ctd.C_DocType_ID = @C_DocType_ID@
	)',TO_TIMESTAMP('2008-09-19 12:50:08','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','C_Charge by Doc Type','S',TO_TIMESTAMP('2008-09-19 12:50:08','YYYY-MM-DD HH24:MI:SS'),0,100)
;

INSERT INTO AD_Val_Rule (AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,AD_Client_ID,UpdatedBy) VALUES (0,52030,'C_Charge.C_Charge_ID IN (
	SELECT c.C_Charge_ID 
	FROM C_Charge c
	LEFT OUTER JOIN C_ChargeType ct ON (ct.C_ChargeType_ID = c.C_ChargeType_ID)
	LEFT OUTER JOIN C_ChargeType_Doctype ctd ON (ctd.C_ChargeType_ID = ct.C_ChargeType_ID)
	LEFT OUTER JOIN  C_DocType dt ON (dt.C_DocType_ID =ctd.C_DocType_ID)
	WHERE  ctd.C_DocType_ID = @C_DocTypeTarget_ID@
	)',TO_TIMESTAMP('2008-09-19 12:52:00','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','C_Charge by Doc Type Target','S',TO_TIMESTAMP('2008-09-19 12:52:00','YYYY-MM-DD HH24:MI:SS'),0,100)
;

INSERT INTO AD_Val_Rule (AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,AD_Client_ID,UpdatedBy) VALUES (0,52031,'C_Charge.C_Charge_ID IN (
	SELECT c.C_Charge_ID 
	FROM C_Charge c
	LEFT OUTER JOIN C_ChargeType ct ON (ct.C_ChargeType_ID = c.C_ChargeType_ID)
	LEFT OUTER JOIN C_ChargeType_Doctype ctd ON (ctd.C_ChargeType_ID = ct.C_ChargeType_ID)
	LEFT OUTER JOIN  C_DocType dt ON (dt.C_DocType_ID =ctd.C_DocType_ID)
	WHERE  ctd.C_DocType_ID = 1000018
	)',TO_TIMESTAMP('2008-09-19 12:52:49','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','C_Charge by Doc Type Cash','S',TO_TIMESTAMP('2008-09-19 12:52:49','YYYY-MM-DD HH24:MI:SS'),0,100)
;

INSERT INTO AD_Val_Rule (AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,AD_Client_ID,UpdatedBy) VALUES (0,52032,'C_Charge.C_Charge_ID IN (
	SELECT c.C_Charge_ID 
	FROM C_Charge c
	LEFT OUTER JOIN C_ChargeType ct ON (ct.C_ChargeType_ID = c.C_ChargeType_ID)
	LEFT OUTER JOIN C_ChargeType_Doctype ctd ON (ctd.C_ChargeType_ID = ct.C_ChargeType_ID)
	LEFT OUTER JOIN  C_DocType dt ON (dt.C_DocType_ID =ctd.C_DocType_ID)
	WHERE  ctd.C_DocType_ID = 1000019
	)',TO_TIMESTAMP('2008-09-19 12:53:33','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','C_Charge by Doc Type Bank','S',TO_TIMESTAMP('2008-09-19 12:53:33','YYYY-MM-DD HH24:MI:SS'),0,100)
;

UPDATE AD_Column SET AD_Val_Rule_ID=52029,Updated=TO_TIMESTAMP('2008-09-19 12:57:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=8984
;

UPDATE AD_Column SET AD_Val_Rule_ID=52030,Updated=TO_TIMESTAMP('2008-09-19 12:57:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=3050
;

UPDATE AD_Column SET AD_Val_Rule_ID=52030,Updated=TO_TIMESTAMP('2008-09-19 12:59:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=3845
;

UPDATE AD_Column SET AD_Val_Rule_ID=52029,Updated=TO_TIMESTAMP('2008-09-19 12:59:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=9950
;

UPDATE AD_Column SET AD_Val_Rule_ID=52029,Updated=TO_TIMESTAMP('2008-09-19 13:00:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=13434
;

UPDATE AD_Column SET AD_Val_Rule_ID=52029,Updated=TO_TIMESTAMP('2008-09-19 13:01:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=52002
;

UPDATE AD_Column SET AD_Val_Rule_ID=52029,Updated=TO_TIMESTAMP('2008-09-19 13:01:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=15010
;

UPDATE AD_Column SET AD_Val_Rule_ID=52031,Updated=TO_TIMESTAMP('2008-09-19 13:02:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=5296
;

UPDATE AD_Column SET AD_Val_Rule_ID=52032,Updated=TO_TIMESTAMP('2008-09-19 13:03:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=4939
;

UPDATE AD_Column SET AD_Val_Rule_ID=NULL,Updated=TO_TIMESTAMP('2008-09-11 12:12:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=8984
;

UPDATE AD_Column SET AD_Val_Rule_ID=NULL,Updated=TO_TIMESTAMP('2008-09-11 12:13:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=3050
;

UPDATE AD_Column SET AD_Val_Rule_ID=NULL,Updated=TO_TIMESTAMP('2008-09-11 12:17:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=3845
;

UPDATE AD_Column SET AD_Val_Rule_ID=NULL,Updated=TO_TIMESTAMP('2008-09-11 12:34:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=9950
;

UPDATE AD_Column SET AD_Val_Rule_ID=NULL,Updated=TO_TIMESTAMP('2008-09-11 12:40:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=13434
;

UPDATE AD_Column SET AD_Val_Rule_ID=NULL,Updated=TO_TIMESTAMP('2008-09-11 12:52:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=52002
;

UPDATE AD_Column SET AD_Val_Rule_ID=NULL,Updated=TO_TIMESTAMP('2008-09-11 12:56:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=15010
;

UPDATE AD_Column SET AD_Val_Rule_ID=NULL,Updated=TO_TIMESTAMP('2008-09-11 13:13:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=5296
;

UPDATE AD_Column SET AD_Val_Rule_ID=NULL,Updated=TO_TIMESTAMP('2008-09-11 13:16:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=4939
;

delete from ad_val_rule where ad_val_rule_id = 52029
;

delete from ad_val_rule where ad_val_rule_id = 52030
;

delete from ad_val_rule where ad_val_rule_id = 52031
;

delete from ad_val_rule where ad_val_rule_id = 52032
;

INSERT INTO AD_Val_Rule (AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,AD_Client_ID,UpdatedBy) VALUES (0,52029,'C_Charge.C_Charge_ID IN (
	SELECT c.C_Charge_ID 
	FROM C_Charge c
	JOIN C_ChargeType ct ON (ct.C_ChargeType_ID = c.C_ChargeType_ID)
	JOIN C_ChargeType_Doctype ctd ON (ctd.C_ChargeType_ID = ct.C_ChargeType_ID)
	JOIN  C_DocType dt ON (dt.C_DocType_ID =ctd.C_DocType_ID)
	WHERE  ctd.C_DocType_ID = @C_DocType_ID@
	)',TO_TIMESTAMP('2008-09-19 12:50:08','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','C_Charge by Doc Type','S',TO_TIMESTAMP('2008-09-19 12:50:08','YYYY-MM-DD HH24:MI:SS'),0,100)
;

INSERT INTO AD_Val_Rule (AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,AD_Client_ID,UpdatedBy) VALUES (0,52030,'C_Charge.C_Charge_ID IN (
	SELECT c.C_Charge_ID 
	FROM C_Charge c
	JOIN C_ChargeType ct ON (ct.C_ChargeType_ID = c.C_ChargeType_ID)
	JOIN C_ChargeType_Doctype ctd ON (ctd.C_ChargeType_ID = ct.C_ChargeType_ID)
	JOIN  C_DocType dt ON (dt.C_DocType_ID =ctd.C_DocType_ID)
	WHERE  ctd.C_DocType_ID = @C_DocTypeTarget_ID@
	)',TO_TIMESTAMP('2008-09-19 12:52:00','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','C_Charge by Doc Type Target','S',TO_TIMESTAMP('2008-09-19 12:52:00','YYYY-MM-DD HH24:MI:SS'),0,100)
;

INSERT INTO AD_Val_Rule (AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,AD_Client_ID,UpdatedBy) VALUES (0,52031,'C_Charge.C_Charge_ID IN (
	SELECT c.C_Charge_ID 
	FROM C_Charge c
	JOIN C_ChargeType ct ON (ct.C_ChargeType_ID = c.C_ChargeType_ID)
	JOIN C_ChargeType_Doctype ctd ON (ctd.C_ChargeType_ID = ct.C_ChargeType_ID)
	JOIN  C_DocType dt ON (dt.C_DocType_ID =ctd.C_DocType_ID AND dt.AD_Client_ID=ctd.AD_Client_ID AND dt.DocBaseType=''CMC'')
	)',TO_TIMESTAMP('2008-09-19 12:52:49','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','C_Charge by Doc Type Cash','S',TO_TIMESTAMP('2008-09-19 12:52:49','YYYY-MM-DD HH24:MI:SS'),0,100)
;

INSERT INTO AD_Val_Rule (AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,AD_Client_ID,UpdatedBy) VALUES (0,52032,'C_Charge.C_Charge_ID IN (
	SELECT c.C_Charge_ID 
	FROM C_Charge c
	JOIN C_ChargeType ct ON (ct.C_ChargeType_ID = c.C_ChargeType_ID)
	JOIN C_ChargeType_Doctype ctd ON (ctd.C_ChargeType_ID = ct.C_ChargeType_ID)
	JOIN  C_DocType dt ON (dt.C_DocType_ID =ctd.C_DocType_ID AND dt.AD_Client_ID= ctd.AD_Client_ID  AND dt.DocBaseType= ''CMB'')
	)',TO_TIMESTAMP('2008-09-19 12:53:33','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','C_Charge by Doc Type Bank','S',TO_TIMESTAMP('2008-09-19 12:53:33','YYYY-MM-DD HH24:MI:SS'),0,100)
;

UPDATE AD_Column SET AD_Val_Rule_ID=52029,Updated=TO_TIMESTAMP('2008-09-19 12:57:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=8984
;

UPDATE AD_Column SET AD_Val_Rule_ID=52030,Updated=TO_TIMESTAMP('2008-09-19 12:57:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=3050
;

UPDATE AD_Column SET AD_Val_Rule_ID=52030,Updated=TO_TIMESTAMP('2008-09-19 12:59:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=3845
;

UPDATE AD_Column SET AD_Val_Rule_ID=52029,Updated=TO_TIMESTAMP('2008-09-19 12:59:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=9950
;

UPDATE AD_Column SET AD_Val_Rule_ID=52029,Updated=TO_TIMESTAMP('2008-09-19 13:00:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=13434
;

UPDATE AD_Column SET AD_Val_Rule_ID=52029,Updated=TO_TIMESTAMP('2008-09-19 13:01:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=52002
;

UPDATE AD_Column SET AD_Val_Rule_ID=52029,Updated=TO_TIMESTAMP('2008-09-19 13:01:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=15010
;

UPDATE AD_Column SET AD_Val_Rule_ID=52031,Updated=TO_TIMESTAMP('2008-09-19 13:02:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=5296
;

UPDATE AD_Column SET AD_Val_Rule_ID=52032,Updated=TO_TIMESTAMP('2008-09-19 13:03:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=4939
;

UPDATE AD_Val_Rule SET Code='(C_Charge.C_Charge_ID IN (
	SELECT c.C_Charge_ID 
	FROM C_Charge c
	JOIN C_ChargeType ct ON (ct.C_ChargeType_ID = c.C_ChargeType_ID)
	JOIN C_ChargeType_Doctype ctd ON (ctd.C_ChargeType_ID = ct.C_ChargeType_ID)
	JOIN  C_DocType dt ON (dt.C_DocType_ID =ctd.C_DocType_ID)
	WHERE  ctd.C_DocType_ID = @C_DocType_ID@
	) OR
 (SELECT COUNT(*) FROM C_ChargeType_DocType WHERE AD_Client_ID=@AD_Client_ID@) = 0)',Updated=TO_TIMESTAMP('2009-02-12 12:09:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Val_Rule_ID=52029
;

UPDATE AD_Val_Rule SET Code='(C_Charge.C_Charge_ID IN (
	SELECT c.C_Charge_ID 
	FROM C_Charge c
	JOIN C_ChargeType ct ON (ct.C_ChargeType_ID = c.C_ChargeType_ID)
	JOIN C_ChargeType_Doctype ctd ON (ctd.C_ChargeType_ID = ct.C_ChargeType_ID)
	JOIN  C_DocType dt ON (dt.C_DocType_ID =ctd.C_DocType_ID AND dt.AD_Client_ID= ctd.AD_Client_ID  AND dt.DocBaseType= ''CMB'')
	) OR
 (SELECT COUNT(*) FROM C_ChargeType_DocType WHERE AD_Client_ID=@AD_Client_ID@) = 0)',Updated=TO_TIMESTAMP('2009-02-12 12:10:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Val_Rule_ID=52032
;

UPDATE AD_Val_Rule SET Code='(C_Charge.C_Charge_ID IN (
	SELECT c.C_Charge_ID 
	FROM C_Charge c
	JOIN C_ChargeType ct ON (ct.C_ChargeType_ID = c.C_ChargeType_ID)
	JOIN C_ChargeType_Doctype ctd ON (ctd.C_ChargeType_ID = ct.C_ChargeType_ID)
	JOIN  C_DocType dt ON (dt.C_DocType_ID =ctd.C_DocType_ID AND dt.AD_Client_ID=ctd.AD_Client_ID AND dt.DocBaseType=''CMC'')
	) OR
 (SELECT COUNT(*) FROM C_ChargeType_DocType WHERE AD_Client_ID=@AD_Client_ID@) = 0)',Updated=TO_TIMESTAMP('2009-02-12 12:10:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Val_Rule_ID=52031
;

UPDATE AD_Val_Rule SET Code='(C_Charge.C_Charge_ID IN (
	SELECT c.C_Charge_ID 
	FROM C_Charge c
	JOIN C_ChargeType ct ON (ct.C_ChargeType_ID = c.C_ChargeType_ID)
	JOIN C_ChargeType_Doctype ctd ON (ctd.C_ChargeType_ID = ct.C_ChargeType_ID)
	JOIN  C_DocType dt ON (dt.C_DocType_ID =ctd.C_DocType_ID)
	WHERE  ctd.C_DocType_ID = @C_DocTypeTarget_ID@
	) OR
 (SELECT COUNT(*) FROM C_ChargeType_DocType WHERE AD_Client_ID=@AD_Client_ID@) = 0)',Updated=TO_TIMESTAMP('2009-02-12 12:10:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Val_Rule_ID=52030
;

