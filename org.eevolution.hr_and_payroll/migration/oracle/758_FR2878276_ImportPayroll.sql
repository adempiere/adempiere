-- Feb 8, 2010 6:18:56 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WindowType) VALUES (0,0,53108,TO_DATE('2010-02-08 18:18:55','YYYY-MM-DD HH24:MI:SS'),100,'Import Payroll Movement','EE02','Y','N','N','N','Import Payroll Movement','N',TO_DATE('2010-02-08 18:18:55','YYYY-MM-DD HH24:MI:SS'),100,'M')
;

-- Feb 8, 2010 6:18:56 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53108 AND NOT EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Window_ID=t.AD_Window_ID)
;

-- Feb 8, 2010 6:18:57 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Table (AccessLevel,AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES ('3',0,0,53259,53108,TO_DATE('2010-02-08 18:18:56','YYYY-MM-DD HH24:MI:SS'),100,'EE02','N','Y','N','Y','Y','N','N','Payroll Movement Import','L','I_HR_Movement',TO_DATE('2010-02-08 18:18:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:18:57 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53259 AND NOT EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Table_ID=t.AD_Table_ID)
;

-- Feb 8, 2010 6:18:58 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53368,TO_DATE('2010-02-08 18:18:57','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table I_HR_Movement',1,'Y','N','Y','Y','I_HR_Movement','N',1000000,TO_DATE('2010-02-08 18:18:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:18:58 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54113,0,'I_HR_Movement_ID',TO_DATE('2010-02-08 18:18:58','YYYY-MM-DD HH24:MI:SS'),100,'EE02','Y','Payroll Movement Import','Payroll Movement Import',TO_DATE('2010-02-08 18:18:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:18:58 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54113 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Feb 8, 2010 6:18:59 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58951,54113,0,13,53259,'I_HR_Movement_ID',TO_DATE('2010-02-08 18:18:59','YYYY-MM-DD HH24:MI:SS'),100,'EE02',10,'Y','N','N','N','Y','Y','N','N','Y','N','N','Payroll Movement Import',TO_DATE('2010-02-08 18:18:59','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Feb 8, 2010 6:19:00 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58951 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 8, 2010 6:19:00 PM COT
-- FR2878276-Importer for Payroll
CREATE TABLE I_HR_Movement (I_HR_Movement_ID NUMBER(10) NOT NULL, CONSTRAINT I_HR_Movement_Key PRIMARY KEY (I_HR_Movement_ID))
;

-- Feb 8, 2010 6:19:10 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58952,102,0,19,53259,129,'AD_Client_ID',TO_DATE('2010-02-08 18:19:01','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.','EE02',10,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','N','N','N','Y','N','N','Client',TO_DATE('2010-02-08 18:19:01','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Feb 8, 2010 6:19:10 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58952 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 8, 2010 6:19:10 PM COT
-- FR2878276-Importer for Payroll
ALTER TABLE I_HR_Movement ADD AD_Client_ID NUMBER(10) DEFAULT NULL 
;

-- Feb 8, 2010 6:19:14 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58953,113,0,19,53259,104,'AD_Org_ID',TO_DATE('2010-02-08 18:19:11','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client','EE02',10,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','N','N','N','Y','N','N','Organization',TO_DATE('2010-02-08 18:19:11','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Feb 8, 2010 6:19:14 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58953 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 8, 2010 6:19:14 PM COT
-- FR2878276-Importer for Payroll
ALTER TABLE I_HR_Movement ADD AD_Org_ID NUMBER(10) DEFAULT NULL 
;

-- Feb 8, 2010 6:19:15 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58954,1367,0,22,53259,'Amount',TO_DATE('2010-02-08 18:19:15','YYYY-MM-DD HH24:MI:SS'),100,'Amount in a defined currency','EE02',20,'The Amount indicates the amount for this document line.','Y','N','N','N','N','N','N','N','Y','N','Y','Amount',TO_DATE('2010-02-08 18:19:15','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Feb 8, 2010 6:19:15 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58954 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 8, 2010 6:19:15 PM COT
-- FR2878276-Importer for Payroll
ALTER TABLE I_HR_Movement ADD Amount NUMBER DEFAULT NULL 
;

-- Feb 8, 2010 6:19:16 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58955,187,0,30,53259,'C_BPartner_ID',TO_DATE('2010-02-08 18:19:16','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Business Partner','EE02',10,'A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','N','N','Y','N','N','N','Y','Y','N','Y','Business Partner ',2,TO_DATE('2010-02-08 18:19:16','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Feb 8, 2010 6:19:16 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58955 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 8, 2010 6:19:16 PM COT
-- FR2878276-Importer for Payroll
ALTER TABLE I_HR_Movement ADD C_BPartner_ID NUMBER(10) DEFAULT NULL 
;

-- Feb 8, 2010 6:19:18 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58956,245,0,16,53259,'Created',TO_DATE('2010-02-08 18:19:17','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','EE02',29,'The Created field indicates the date that this record was created.','Y','N','N','N','N','N','N','N','Y','N','N','Created',TO_DATE('2010-02-08 18:19:17','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Feb 8, 2010 6:19:18 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58956 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 8, 2010 6:19:18 PM COT
-- FR2878276-Importer for Payroll
ALTER TABLE I_HR_Movement ADD Created DATE DEFAULT NULL 
;

-- Feb 8, 2010 6:19:19 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58957,246,0,18,110,53259,'CreatedBy',TO_DATE('2010-02-08 18:19:19','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','EE02',10,'The Created By field indicates the user who created this record.','Y','N','N','N','N','N','N','N','Y','N','N','Created By',TO_DATE('2010-02-08 18:19:19','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Feb 8, 2010 6:19:19 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58957 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 8, 2010 6:19:19 PM COT
-- FR2878276-Importer for Payroll
ALTER TABLE I_HR_Movement ADD CreatedBy NUMBER(10) DEFAULT NULL 
;

-- Feb 8, 2010 6:19:20 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58958,53398,0,19,53259,'HR_Concept_ID',TO_DATE('2010-02-08 18:19:20','YYYY-MM-DD HH24:MI:SS'),100,'EE02',10,'Y','N','N','Y','N','N','N','N','Y','N','Y','Payroll Concept',3,TO_DATE('2010-02-08 18:19:20','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Feb 8, 2010 6:19:20 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58958 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 8, 2010 6:19:20 PM COT
-- FR2878276-Importer for Payroll
ALTER TABLE I_HR_Movement ADD HR_Concept_ID NUMBER(10) DEFAULT NULL 
;

-- Feb 8, 2010 6:19:21 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58959,53407,0,19,53259,'HR_Process_ID',TO_DATE('2010-02-08 18:19:21','YYYY-MM-DD HH24:MI:SS'),100,'EE02',10,'Y','N','N','Y','N','N','N','Y','Y','N','Y','Payroll Process',1,TO_DATE('2010-02-08 18:19:21','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Feb 8, 2010 6:19:21 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58959 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 8, 2010 6:19:21 PM COT
-- FR2878276-Importer for Payroll
ALTER TABLE I_HR_Movement ADD HR_Process_ID NUMBER(10) DEFAULT NULL 
;

-- Feb 8, 2010 6:19:22 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58960,348,0,20,53259,'IsActive',TO_DATE('2010-02-08 18:19:22','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system','EE02',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','N','N','N','Y','N','Y','Active',TO_DATE('2010-02-08 18:19:22','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Feb 8, 2010 6:19:22 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58960 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 8, 2010 6:19:22 PM COT
-- FR2878276-Importer for Payroll
ALTER TABLE I_HR_Movement ADD IsActive CHAR(1) DEFAULT NULL  CHECK (IsActive IN ('Y','N'))
;

-- Feb 8, 2010 6:19:23 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58961,1047,0,20,53259,'Processed',TO_DATE('2010-02-08 18:19:22','YYYY-MM-DD HH24:MI:SS'),100,'The document has been processed','EE02',1,'The Processed checkbox indicates that a document has been processed.','Y','N','N','N','N','N','N','N','Y','N','Y','Processed',TO_DATE('2010-02-08 18:19:22','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Feb 8, 2010 6:19:23 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58961 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 8, 2010 6:19:23 PM COT
-- FR2878276-Importer for Payroll
ALTER TABLE I_HR_Movement ADD Processed CHAR(1) DEFAULT NULL  CHECK (Processed IN ('Y','N'))
;

-- Feb 8, 2010 6:19:24 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58962,526,0,29,53259,'Qty',TO_DATE('2010-02-08 18:19:24','YYYY-MM-DD HH24:MI:SS'),100,'Quantity','EE02',10,'The Quantity indicates the number of a specific product or item for this document.','Y','N','N','N','N','N','N','N','Y','N','Y','Quantity',TO_DATE('2010-02-08 18:19:24','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Feb 8, 2010 6:19:24 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58962 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 8, 2010 6:19:24 PM COT
-- FR2878276-Importer for Payroll
ALTER TABLE I_HR_Movement ADD Qty NUMBER DEFAULT NULL 
;

-- Feb 8, 2010 6:19:25 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58963,1129,0,16,53259,'ServiceDate',TO_DATE('2010-02-08 18:19:24','YYYY-MM-DD HH24:MI:SS'),100,'Date service was provided','EE02',29,'The Service Date indicates the date that the service was provided.','Y','N','N','N','N','N','N','N','Y','N','Y','Service date',TO_DATE('2010-02-08 18:19:24','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Feb 8, 2010 6:19:25 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58963 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 8, 2010 6:19:25 PM COT
-- FR2878276-Importer for Payroll
ALTER TABLE I_HR_Movement ADD ServiceDate DATE DEFAULT NULL 
;

-- Feb 8, 2010 6:19:26 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58964,2438,0,10,53259,'TextMsg',TO_DATE('2010-02-08 18:19:25','YYYY-MM-DD HH24:MI:SS'),100,'Text Message','EE02',255,'Y','N','N','N','N','N','N','N','Y','N','Y','Text Message',TO_DATE('2010-02-08 18:19:25','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Feb 8, 2010 6:19:26 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58964 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 8, 2010 6:19:26 PM COT
-- FR2878276-Importer for Payroll
ALTER TABLE I_HR_Movement ADD TextMsg NVARCHAR2(255) DEFAULT NULL 
;

-- Feb 8, 2010 6:19:27 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58965,607,0,16,53259,'Updated',TO_DATE('2010-02-08 18:19:26','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','EE02',29,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','N','N','N','Y','N','N','Updated',TO_DATE('2010-02-08 18:19:26','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Feb 8, 2010 6:19:27 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58965 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 8, 2010 6:19:27 PM COT
-- FR2878276-Importer for Payroll
ALTER TABLE I_HR_Movement ADD Updated DATE DEFAULT NULL 
;

-- Feb 8, 2010 6:19:28 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58966,608,0,18,110,53259,'UpdatedBy',TO_DATE('2010-02-08 18:19:27','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','EE02',10,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','N','N','N','Y','N','N','Updated By',TO_DATE('2010-02-08 18:19:27','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Feb 8, 2010 6:19:28 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58966 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 8, 2010 6:19:28 PM COT
-- FR2878276-Importer for Payroll
ALTER TABLE I_HR_Movement ADD UpdatedBy NUMBER(10) DEFAULT NULL 
;

-- Feb 8, 2010 6:19:29 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58967,617,0,16,53259,'ValidFrom',TO_DATE('2010-02-08 18:19:28','YYYY-MM-DD HH24:MI:SS'),100,'Valid from including this date (first day)','EE02',29,'The Valid From date indicates the first day of a date range','Y','N','N','N','N','N','N','N','Y','N','Y','Valid from',TO_DATE('2010-02-08 18:19:28','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Feb 8, 2010 6:19:29 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58967 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 8, 2010 6:19:29 PM COT
-- FR2878276-Importer for Payroll
ALTER TABLE I_HR_Movement ADD ValidFrom DATE DEFAULT NULL 
;

-- Feb 8, 2010 6:19:29 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58968,913,0,10,53259,'I_IsImported',TO_DATE('2010-02-08 18:19:29','YYYY-MM-DD HH24:MI:SS'),100,'N','Has this import been processed','EE02',1,'The Imported check box indicates if this import has been processed.','Y','N','N','N','N','Y','N','N','Y','N','Y','Imported',TO_DATE('2010-02-08 18:19:29','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Feb 8, 2010 6:19:29 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58968 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 8, 2010 6:19:29 PM COT
-- FR2878276-Importer for Payroll
ALTER TABLE I_HR_Movement ADD I_IsImported NVARCHAR2(1) DEFAULT 'N' NOT NULL
;

-- Feb 8, 2010 6:19:30 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58969,912,0,10,53259,'I_ErrorMsg',TO_DATE('2010-02-08 18:19:30','YYYY-MM-DD HH24:MI:SS'),100,'Messages generated from import process','EE02',2000,'The Import Error Message displays any error messages generated during the import process.','Y','N','N','N','N','N','N','N','Y','N','Y','Import Error Message',TO_DATE('2010-02-08 18:19:30','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Feb 8, 2010 6:19:30 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58969 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 8, 2010 6:19:30 PM COT
-- FR2878276-Importer for Payroll
ALTER TABLE I_HR_Movement ADD I_ErrorMsg NVARCHAR2(2000) DEFAULT NULL 
;

-- Feb 8, 2010 6:19:31 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Process (AccessLevel,AD_Client_ID,AD_Org_ID,AD_Process_ID,Classname,Created,CreatedBy,Description,EntityType,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value,WorkflowValue) VALUES ('3',0,0,53197,'org.eevolution.process.ImportPayrollMovement',TO_DATE('2010-02-08 18:19:31','YYYY-MM-DD HH24:MI:SS'),100,'Import Payroll Movement','EE02','Y','N','N','N','Import Payroll Movement','Y',0,0,TO_DATE('2010-02-08 18:19:31','YYYY-MM-DD HH24:MI:SS'),100,'Import_PayrollMovement',NULL)
;

-- Feb 8, 2010 6:19:31 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53197 AND NOT EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_ID=t.AD_Process_ID)
;

-- Feb 8, 2010 6:19:32 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,1922,0,53197,53401,20,'DeleteOldImported',TO_DATE('2010-02-08 18:19:31','YYYY-MM-DD HH24:MI:SS'),100,'EE02',1,'Y','Y','N','N','Delete old imported records',10,TO_DATE('2010-02-08 18:19:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:19:32 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53401 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Feb 8, 2010 6:19:33 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58970,524,0,53197,28,53259,'Processing',TO_DATE('2010-02-08 18:19:32','YYYY-MM-DD HH24:MI:SS'),100,'EE02',1,'Y','N','N','N','N','N','N','N','Y','N','Y','Process Now',TO_DATE('2010-02-08 18:19:32','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Feb 8, 2010 6:19:33 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58970 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 8, 2010 6:19:33 PM COT
-- FR2878276-Importer for Payroll
ALTER TABLE I_HR_Movement ADD Processing CHAR(1) DEFAULT NULL 
;

-- Feb 8, 2010 6:19:33 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58971,1906,0,10,53259,'BPartner_Value',TO_DATE('2010-02-08 18:19:33','YYYY-MM-DD HH24:MI:SS'),100,'The Key of the Business Partner','EE02',40,'Y','N','N','N','N','N','N','N','Y','N','Y','Business Partner Key',TO_DATE('2010-02-08 18:19:33','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Feb 8, 2010 6:19:33 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58971 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 8, 2010 6:19:34 PM COT
-- FR2878276-Importer for Payroll
ALTER TABLE I_HR_Movement ADD BPartner_Value NVARCHAR2(40) DEFAULT NULL 
;

-- Feb 8, 2010 6:19:34 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54114,0,'ConceptValue',TO_DATE('2010-02-08 18:19:34','YYYY-MM-DD HH24:MI:SS'),100,'Value of the Concept','EE02','Y','Concept Value','Concept Value',TO_DATE('2010-02-08 18:19:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:19:34 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54114 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Feb 8, 2010 6:19:35 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58972,54114,0,10,53259,'ConceptValue',TO_DATE('2010-02-08 18:19:34','YYYY-MM-DD HH24:MI:SS'),100,'Value of the Concept','EE02',40,'Y','N','N','N','N','N','N','N','Y','N','Y','Concept Value',TO_DATE('2010-02-08 18:19:34','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Feb 8, 2010 6:19:35 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58972 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 8, 2010 6:19:35 PM COT
-- FR2878276-Importer for Payroll
ALTER TABLE I_HR_Movement ADD ConceptValue NVARCHAR2(40) DEFAULT NULL 
;

-- Feb 8, 2010 6:19:37 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58973,53417,0,19,53259,'HR_Movement_ID',TO_DATE('2010-02-08 18:19:35','YYYY-MM-DD HH24:MI:SS'),100,'EE02',10,'Y','N','N','N','N','N','N','N','Y','N','Y','Payroll Movement',TO_DATE('2010-02-08 18:19:35','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Feb 8, 2010 6:19:37 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58973 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 8, 2010 6:19:37 PM COT
-- FR2878276-Importer for Payroll
ALTER TABLE I_HR_Movement ADD HR_Movement_ID NUMBER(10) DEFAULT NULL 
;

-- Feb 8, 2010 6:19:37 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54115,0,'ProcessName',TO_DATE('2010-02-08 18:19:37','YYYY-MM-DD HH24:MI:SS'),100,'Name of the Process','EE02','Y','Process Name','Process Name',TO_DATE('2010-02-08 18:19:37','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:19:37 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54115 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Feb 8, 2010 6:19:38 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58974,54115,0,10,53259,'ProcessName',TO_DATE('2010-02-08 18:19:37','YYYY-MM-DD HH24:MI:SS'),100,'Name of the Process','EE02',60,'Y','N','N','N','N','N','N','N','Y','N','Y','Process Name',TO_DATE('2010-02-08 18:19:37','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Feb 8, 2010 6:19:38 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58974 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 8, 2010 6:19:38 PM COT
-- FR2878276-Importer for Payroll
ALTER TABLE I_HR_Movement ADD ProcessName NVARCHAR2(60) DEFAULT NULL 
;

-- Feb 8, 2010 6:19:39 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58975,275,0,10,53259,'Description',TO_DATE('2010-02-08 18:19:38','YYYY-MM-DD HH24:MI:SS'),100,'Optional short description of the record','EE02',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','Y','N','Y','Description',TO_DATE('2010-02-08 18:19:38','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Feb 8, 2010 6:19:39 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58975 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 8, 2010 6:19:39 PM COT
-- FR2878276-Importer for Payroll
ALTER TABLE I_HR_Movement ADD Description NVARCHAR2(255) DEFAULT NULL 
;

-- Feb 8, 2010 6:19:40 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,Description,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53306,53259,53108,NULL,TO_DATE('2010-02-08 18:19:39','YYYY-MM-DD HH24:MI:SS'),100,'Import Payroll Movement','EE02','N','Y','N','N','Y','N','Y','N','N','Import Payroll Movement','N',10,0,TO_DATE('2010-02-08 18:19:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:19:40 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53306 AND NOT EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Tab_ID=t.AD_Tab_ID)
;

-- Feb 8, 2010 6:19:43 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58951,58704,0,53306,TO_DATE('2010-02-08 18:19:40','YYYY-MM-DD HH24:MI:SS'),100,10,'EE02','Y','Y','N','N','N','N','N','Payroll Movement Import',0,0,TO_DATE('2010-02-08 18:19:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:19:44 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58704 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 8, 2010 6:19:44 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58960,58705,0,53306,TO_DATE('2010-02-08 18:19:44','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system',1,'EE02','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','N','Active',0,0,TO_DATE('2010-02-08 18:19:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:19:44 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58705 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 8, 2010 6:19:45 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Reference_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58968,58706,0,20,53306,TO_DATE('2010-02-08 18:19:44','YYYY-MM-DD HH24:MI:SS'),100,'Has this import been processed',1,'EE02','The Imported check box indicates if this import has been processed.','Y','Y','Y','N','N','Y','N','Imported',10,0,TO_DATE('2010-02-08 18:19:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:19:45 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58706 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 8, 2010 6:19:45 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58973,58707,0,53306,TO_DATE('2010-02-08 18:19:45','YYYY-MM-DD HH24:MI:SS'),100,10,'EE02','Y','Y','Y','N','N','Y','Y','Payroll Movement',20,0,TO_DATE('2010-02-08 18:19:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:19:45 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58707 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 8, 2010 6:19:46 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58969,58708,0,53306,TO_DATE('2010-02-08 18:19:45','YYYY-MM-DD HH24:MI:SS'),100,'Messages generated from import process',2000,'EE02','The Import Error Message displays any error messages generated during the import process.','Y','Y','Y','N','N','Y','N','Import Error Message',30,0,TO_DATE('2010-02-08 18:19:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:19:46 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58708 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 8, 2010 6:19:46 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58952,58709,0,53306,TO_DATE('2010-02-08 18:19:46','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.',10,'EE02','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',40,0,TO_DATE('2010-02-08 18:19:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:19:46 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58709 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 8, 2010 6:19:47 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58953,58710,0,53306,TO_DATE('2010-02-08 18:19:46','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client',10,'EE02','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',50,0,TO_DATE('2010-02-08 18:19:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:19:47 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58710 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 8, 2010 6:19:47 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58974,58711,0,53306,TO_DATE('2010-02-08 18:19:47','YYYY-MM-DD HH24:MI:SS'),100,'Name of the Process',20,'EE02','Y','Y','Y','N','N','N','N','Process Name',60,0,TO_DATE('2010-02-08 18:19:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:19:47 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58711 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 8, 2010 6:19:48 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58959,58712,0,53306,TO_DATE('2010-02-08 18:19:47','YYYY-MM-DD HH24:MI:SS'),100,10,'EE02','Y','Y','Y','N','N','N','Y','Payroll Process',70,0,TO_DATE('2010-02-08 18:19:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:19:48 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58712 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 8, 2010 6:19:49 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58972,58713,0,53306,TO_DATE('2010-02-08 18:19:48','YYYY-MM-DD HH24:MI:SS'),100,'Value of the Concept',20,'EE02','Y','Y','Y','N','N','N','N','Concept Value',80,0,TO_DATE('2010-02-08 18:19:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:19:49 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58713 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 8, 2010 6:19:49 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58958,58714,0,53306,TO_DATE('2010-02-08 18:19:49','YYYY-MM-DD HH24:MI:SS'),100,10,'EE02','Y','Y','Y','N','N','N','Y','Payroll Concept',90,0,TO_DATE('2010-02-08 18:19:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:19:49 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58714 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 8, 2010 6:19:50 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58971,58715,0,53306,TO_DATE('2010-02-08 18:19:49','YYYY-MM-DD HH24:MI:SS'),100,'The Key of the Business Partner',20,'EE02','Y','Y','Y','N','N','N','N','Business Partner Key',100,0,TO_DATE('2010-02-08 18:19:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:19:50 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58715 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 8, 2010 6:19:50 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58955,58716,0,53306,TO_DATE('2010-02-08 18:19:50','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Business Partner',10,'EE02','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Y','Y','N','N','N','Y','Business Partner ',110,0,TO_DATE('2010-02-08 18:19:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:19:50 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58716 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 8, 2010 6:19:51 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58967,58717,0,53306,TO_DATE('2010-02-08 18:19:50','YYYY-MM-DD HH24:MI:SS'),100,'Valid from including this date (first day)',29,'EE02','The Valid From date indicates the first day of a date range','Y','Y','Y','N','N','N','N','Valid from',120,0,TO_DATE('2010-02-08 18:19:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:19:51 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58717 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 8, 2010 6:19:51 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58962,58718,0,53306,TO_DATE('2010-02-08 18:19:51','YYYY-MM-DD HH24:MI:SS'),100,'Quantity',10,'EE02','The Quantity indicates the number of a specific product or item for this document.','Y','Y','Y','N','N','N','N','Quantity',130,0,TO_DATE('2010-02-08 18:19:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:19:51 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58718 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 8, 2010 6:19:52 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58954,58719,0,53306,TO_DATE('2010-02-08 18:19:51','YYYY-MM-DD HH24:MI:SS'),100,'Amount in a defined currency',20,'EE02','The Amount indicates the amount for this document line.','Y','Y','Y','N','N','N','N','Amount',140,0,TO_DATE('2010-02-08 18:19:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:19:52 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58719 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 8, 2010 6:19:53 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58963,58720,0,53306,TO_DATE('2010-02-08 18:19:52','YYYY-MM-DD HH24:MI:SS'),100,'Date service was provided',29,'EE02','The Service Date indicates the date that the service was provided.','Y','Y','Y','N','N','N','N','Service date',150,0,TO_DATE('2010-02-08 18:19:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:19:53 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58720 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 8, 2010 6:19:53 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58964,58721,0,53306,TO_DATE('2010-02-08 18:19:53','YYYY-MM-DD HH24:MI:SS'),100,'Text Message',255,'EE02','Y','Y','Y','N','N','N','N','Text Message',160,0,TO_DATE('2010-02-08 18:19:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:19:53 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58721 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 8, 2010 6:19:54 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58975,58722,0,53306,TO_DATE('2010-02-08 18:19:53','YYYY-MM-DD HH24:MI:SS'),100,'Optional short description of the record',0,'EE02','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',170,0,TO_DATE('2010-02-08 18:19:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:19:54 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58722 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 8, 2010 6:19:54 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58970,58723,0,53306,TO_DATE('2010-02-08 18:19:54','YYYY-MM-DD HH24:MI:SS'),100,1,'EE02','Y','Y','Y','N','N','N','N','Process Now',180,0,TO_DATE('2010-02-08 18:19:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:19:54 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58723 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 8, 2010 6:19:55 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58961,58724,0,53306,TO_DATE('2010-02-08 18:19:55','YYYY-MM-DD HH24:MI:SS'),100,'The document has been processed',1,'EE02','The Processed checkbox indicates that a document has been processed.','Y','Y','Y','N','N','Y','Y','Processed',190,0,TO_DATE('2010-02-08 18:19:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:19:55 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58724 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 8, 2010 6:19:56 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_ImpFormat (AD_Client_ID,AD_ImpFormat_ID,AD_Org_ID,AD_Table_ID,Created,CreatedBy,FormatType,IsActive,Name,Processing,Updated,UpdatedBy) VALUES (0,50002,0,53259,TO_DATE('2010-02-08 18:19:55','YYYY-MM-DD HH24:MI:SS'),100,'C','Y','Payroll Movement','N',TO_DATE('2010-02-08 18:19:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:19:56 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,58974,50002,50077,0,NULL,NULL,TO_DATE('2010-02-08 18:19:56','YYYY-MM-DD HH24:MI:SS'),100,NULL,'S','.','N',0,'Y','Process Name',NULL,10,1,TO_DATE('2010-02-08 18:19:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:19:57 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,58971,50002,50078,0,NULL,NULL,TO_DATE('2010-02-08 18:19:56','YYYY-MM-DD HH24:MI:SS'),100,NULL,'S','.','N',0,'Y','Business Partner Key',NULL,20,2,TO_DATE('2010-02-08 18:19:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:19:57 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,58972,50002,50079,0,NULL,NULL,TO_DATE('2010-02-08 18:19:57','YYYY-MM-DD HH24:MI:SS'),100,NULL,'S','.','N',0,'Y','Concept Value',NULL,30,3,TO_DATE('2010-02-08 18:19:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:19:58 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,58967,50002,50080,0,NULL,NULL,TO_DATE('2010-02-08 18:19:57','YYYY-MM-DD HH24:MI:SS'),100,'MMddyyyy','D','.','N',0,'Y','Valid from',NULL,40,4,TO_DATE('2010-02-08 18:19:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:19:58 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,58962,50002,50081,0,NULL,NULL,TO_DATE('2010-02-08 18:19:58','YYYY-MM-DD HH24:MI:SS'),100,NULL,'N','.','N',0,'Y','Quantity',NULL,50,5,TO_DATE('2010-02-08 18:19:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:20:03 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,58954,50002,50082,0,NULL,NULL,TO_DATE('2010-02-08 18:19:58','YYYY-MM-DD HH24:MI:SS'),100,NULL,'N','.','N',0,'Y','Amount',NULL,60,6,TO_DATE('2010-02-08 18:19:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:20:03 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,58975,50002,50083,0,NULL,NULL,TO_DATE('2010-02-08 18:20:03','YYYY-MM-DD HH24:MI:SS'),100,NULL,'S','.','N',0,'Y','Description',NULL,90,9,TO_DATE('2010-02-08 18:20:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:20:04 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,58963,50002,50084,0,NULL,NULL,TO_DATE('2010-02-08 18:20:03','YYYY-MM-DD HH24:MI:SS'),100,'MMddyyyy','D','.','N',0,'Y','Service date',NULL,70,7,TO_DATE('2010-02-08 18:20:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:20:07 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_ImpFormat_Row (AD_Client_ID,AD_Column_ID,AD_ImpFormat_ID,AD_ImpFormat_Row_ID,AD_Org_ID,Callout,ConstantValue,Created,CreatedBy,DataFormat,DataType,DecimalPoint,DivideBy100,EndNo,IsActive,Name,Script,SeqNo,StartNo,Updated,UpdatedBy) VALUES (0,58964,50002,50085,0,NULL,NULL,TO_DATE('2010-02-08 18:20:04','YYYY-MM-DD HH24:MI:SS'),100,NULL,'S','.','N',0,'Y','Text Message',NULL,80,8,TO_DATE('2010-02-08 18:20:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:20:08 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Menu (Action,AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES ('W',0,53263,0,53108,TO_DATE('2010-02-08 18:20:07','YYYY-MM-DD HH24:MI:SS'),100,'Import Payroll Movement','EE02','Y','N','N','N','Import Payroll Movement',TO_DATE('2010-02-08 18:20:07','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 8, 2010 6:20:08 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53263 AND NOT EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Menu_ID=t.AD_Menu_ID)
;

-- Feb 8, 2010 6:20:08 PM COT
-- FR2878276-Importer for Payroll
INSERT INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 163,14, 10, 53263)
;

